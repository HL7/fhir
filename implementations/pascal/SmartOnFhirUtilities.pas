unit SmartOnFhirUtilities;


{
Copyright (c) 2001-2013, Health Intersections Pty Ltd (http://www.healthintersections.com.au)
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to
   endorse or promote products derived from this software without specific
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 'AS IS' AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
}


{
Using the SMART on FHIR client library

Using SMART on FHIR consists of 2 main phases:

1. registration
- you must register with the specific SMART on FHIR Server. You give it a name, and a redirect URL, and it will return a client id

- for this library, the redirect URL must take the form of http://localhost:[port]/done where post is any number
  between 1 and 65535. There is no flexibility associated with this

- if you are a confidential application (e.g. server side) you can also ask for a client secret. This library supports secrets,
  though it is a server side library that is not associated with actual secret applications (but it's supported anyway for
  development assistance.

- you also need to know the authorization and token end points on the server. These are advertised in the
  server's conformance statement, and the function usesSmartOnFHIR below can extract them for you

- at the end of the registration process, you have all the information in the TRegisteredServer record

2. Operational Use
- Create a TSmartOnFhirLoginForm. give it:
  * logoPath : the path to a PNG describing your application (used by some servers)
  * server: the server details from the registration phase
  * scopes: the scopes you are asking. See Smart on FHIR documentation for what are valid scopes
  * handle error:
       if this is false, and an error occurs, the form will close without telling
       the user about the error, and the host can handle the user. If this is true,
       the form will handle it (this is generally a more comfortable UI for the user)

- Important: you may need to set the following registry key to get OAuth to work:
  // HKEY_LOCAL_MACHINE\SOFTWARE\(Wow6432Node\)Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BROWSER_EMULATION\[your exe name] = 10000
  see https://msdn.microsoft.com/en-us/library/ee330730(v=vs.85).aspx or http://blogs.msdn.com/b/patricka/archive/2015/01/12/controlling-webbrowser-control-compatibility.aspx

- Show the form (.showmodal).

- the form will create an HTTP Server that only binds to the local host. This
  SHOULD avoid problems with firewalls, etc, but this is not guaranteed.
  Note that a localhost server is required because the POX IE control doesn't inform
  the host application about redirects, and OAuth finishes with a redirect.
  (And why not use DCEF? - it's not maintained to the current version of webkit,
  it's a distibution nightmare, and you can't use it in dlls)

- the modal result of the form will have one of the 3 values
  mrOK: Authorization succeeded. The outcome information will be able in .Token on the form - a TSmartOnFhirAccessToken
  mrCancel: the user cancelled the process
  mrAbort: something went wrong. if HandleError is false, a description of what went wrong will be in ErrorMessage

- note that the OAuth process in the browser could go wrong for many reasons that are not known to the
  Smart on FHIR client. In these cases, the user has no choice but to process cancel, so you should not
  assume that cancel means the user chose not to succeed - just that they already know it failed.
}

interface

uses
  SysUtils, Classes,
  StringSupport, EncodeSupport, DateSupport, AdvObjects, AdvJson, JWT,
  IdHTTP, IdSSLOpenSSL,
  FHIRResources, FHIRTypes, FHIRUtilities;

type
  // information about a server required to get SMART on FHIR working
  TRegisteredServer = record
    // user casual name for the server
    name : String;

    // the FHIR Base endpoint for the server
    fhirEndpoint : String;

    // whether the server needs SMART on FHIR
    SmartOnFHIR: boolean;

    // you can get these 2 endpoints from the fhirEndPoint using usesSmartOnFHIR below:

    // where to point the browser to authorise a user
    authorizeEndpoint : String;

    // where to get the access token after authorization has completed
    tokenEndpoint : String;

    // registered client id
    clientid : String;

    // client secret, if we're pretending we're a confidential application/
    // this is for testing purposes; the notepad++ plug-in is not a confidential app
    // (nor any other application using this library)
    clientsecret : String;

    // the port for redirecting to this server
    redirectport : integer;
  end;

  // result of a SMART on FHIR authorization
  TSmartOnFhirAccessToken = class (TAdvObject)
  private
    FidToken: TJWT;
    Fscopes: String;
    FaccessToken: String;
    Fexpires: TDateTime;
    procedure SetidToken(const Value: TJWT);
  public
    Destructor Destroy; override;

    // the bearer access token to add to the HTTP calls. If you assign the
    // access token to a TFHIRClient, it will do this
    property accessToken : String read FaccessToken write FaccessToken;

    // when this token expires (or 0 if expiry is unknown) (in local time)
    property expires : TDateTime read Fexpires write Fexpires;

    // the scopes that were actualyl granted (may be more or less than originally asked for)
    property scopes : String read Fscopes write Fscopes;

    // if an openID token was returned, the details in it (note: unverified)
    property idToken : TJWT read FidToken write SetidToken;

    // convenient username for the user, if any appropriate information is available
    function username : String;
  end;

// given a conformance statement, check the server is using Smart on FHIR, and extract the end points
function usesSmartOnFHIR(conf : TFhirConformance; var authorize, token: String): Boolean;

// build the launch token (used by the form)
function buildAuthUrl(server : TRegisteredServer; scopes, state : String) : String;

// called after the authorization part finishes to get an actual access token
function getSmartOnFhirAuthToken(server : TRegisteredServer; authcode : String) : TSmartOnFhirAccessToken;

implementation

function buildAuthUrl(server : TRegisteredServer; scopes, state : String) : String;
begin
  result := server.authorizeEndpoint+'?response_type=code&client_id='+server.clientid+'&redirect_uri=http://localhost:'+inttostr(server.redirectport)+'/done&scope='+EncodeMIME(scopes)+'&state='+state+'&aud='+server.fhirEndpoint;
end;

function usesSmartOnFHIR(conf : TFhirConformance; var authorize, token: String): Boolean;
var
  cc : TFhirCodeableConcept;
  ex1, ex2 : TFhirExtension;
begin
  result := false;
  authorize := '';
  token := '';
  if conf.restList.Count <> 1 then
    raise Exception.Create('Unable to find rest entry in conformance statement');
  if (conf.restList[0].security <> nil) then
  begin
    for cc in conf.restList[0].security.serviceList do
      if cc.hasCode('http://hl7.org/fhir/restful-security-service', 'SMART-on-FHIR') or cc.hasCode('http://hl7.org/fhir/restful-security-service', 'OAuth2') or
        // temporary work around for Josh's server
         cc.hasCode('http://hl7.org/fhir/vs/restful-security-service', 'SMART-on-FHIR') or cc.hasCode('http://hl7.org/fhir/vs/restful-security-service', 'OAuth2') then
      begin
        for ex1 in conf.restList[0].security.extensionList do
          if ex1.url = 'http://fhir-registry.smarthealthit.org/StructureDefinition/oauth-uris' then
            for ex2 in ex1.extensionList do
              if ex2.url = 'authorize' then
                authorize := TFHIRUri(ex2.value).value
              else if ex2.url = 'token' then
                token := TFHIRUri(ex2.value).value;

      end;
  end;
  result := (token <> '') and (authorize <> '');
end;

function getSmartOnFhirAuthToken(server : TRegisteredServer; authcode : String) : TSmartOnFhirAccessToken;
var
  http: TIdHTTP;
  ssl : TIdSSLIOHandlerSocketOpenSSL;
  post, resp : TBytesStream;
  json : TJSONObject;
  s : String;
begin
  result := nil;

  if server.clientsecret = '' then
    s := 'code='+authcode+'&grant_type=authorization_code&client_id='+server.clientid+'&redirect_uri='+EncodeMime('http://localhost:'+inttostr(server.redirectport)+'/done')
  else
    s := 'code='+authcode+'&grant_type=authorization_code&redirect_uri='+EncodeMime('http://localhost:'+inttostr(server.redirectport)+'/done');
  post := TBytesStream.create(TEncoding.UTF8.getBytes(s));
  try
    http := TIdHTTP.Create(nil);
    Try
      if server.clientsecret <> '' then
      begin
        http.Request.BasicAuthentication := True;
        http.Request.Username := server.clientid;
        http.Request.Password := server.clientsecret;
      end;
      ssl := TIdSSLIOHandlerSocketOpenSSL.Create(Nil);
      Try
        http.IOHandler := ssl;
        ssl.SSLOptions.Mode := sslmClient;
        ssl.SSLOptions.Method := sslvTLSv1_2;
        http.Request.ContentType := 'application/x-www-form-urlencoded; charset=UTF-8';
        resp := TBytesStream.create;
        try
          try
          http.Post(server.tokenEndpoint, post, resp);
          resp.position := 0;
          json := TJSONParser.Parse(resp);
          try
              result := TSmartOnFhirAccessToken.Create;
              try
                if json.vStr['token_type'] <> 'Bearer' then
                  raise Exception.Create('token type is not "Bearer"');
            result.accesstoken := json.vStr['access_token'];
            result.scopes := json.vStr['scope'];
                s := json.vStr['expires_in'];
                if (s <> '') then
                begin
                  if not StringIsInteger16(s) then
                    raise Exception.Create('expires_in is not an integer');
                  result.expires := now + StrToInt(s) * DATETIME_SECOND_ONE;
                end;
                result.idToken := TJWTUtils.unpack(json.vStr['id_token'], false, nil);
                result.Link;
              finally
                result.Free;
              end;
          finally
            json.free;
          end;
          except
            on e : EIdHTTPProtocolException do
              raise Exception.Create(e.message+' : '+e.ErrorMessage);
            on e:Exception do
              raise;
          end;
        finally
          resp.free;
        end;
      finally
        ssl.free;
      end;
    finally
      http.free;
    end;
  finally
    post.free;
  end;
end;

{ TSmartOnFhirAccessToken }

destructor TSmartOnFhirAccessToken.Destroy;
begin
  FidToken.Free;
  inherited;
end;

procedure TSmartOnFhirAccessToken.SetidToken(const Value: TJWT);
begin
  FidToken.Free;
  FidToken := Value;
end;

function TSmartOnFhirAccessToken.username: String;
begin
  if FidToken = nil then
    result := '??'
  else
    result := idtoken.name
end;

end.
