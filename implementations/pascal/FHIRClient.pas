unit FHIRClient;


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


interface

uses
  SysUtils, Classes, Generics.Collections,
  StringSupport, EncodeSupport, GuidSupport,
  IdHTTP, IdSSLOpenSSL, MimeMessage,
  AdvObjects, AdvBuffers, AdvWinInetClients, AdvStringMatches,
  FHIRParser, FHIRResources, FHIRUtilities, DateAndTime,
  FHIRConstants, FHIRSupport, FHIRParserBase, FHIRBase, SmartOnFhirUtilities;

Type
  EFHIRClientException = class (Exception)
  private
    FIssue : TFhirOperationOutcome;
  public
    constructor Create(message : String; issue : TFhirOperationOutcome);
    destructor Destroy; override;

    property issue : TFhirOperationOutcome read FIssue;
  end;

  TFHIRClientHTTPVerb = (get, post, put, delete, options);

  TFHIRClientStatusEvent = procedure (client : TObject; details : String) of Object;

  // this is meant ot be used once, and then disposed of
  TFhirClient = class (TAdvObject)
  private
    FUrl : String;
    FJson : Boolean;
    http : TAdvWinInetClient;
    indy : TIdHTTP;
    ssl : TIdSSLIOHandlerSocketOpenSSL;
    FOnClientStatus : TFHIRClientStatusEvent;
    FSmartToken: TSmartOnFhirAccessToken;
    FTimeout: cardinal;
    FUseIndy: boolean;

//    FLastUpdated : TDateAndTime;
    procedure status(msg : String);
    function serialise(resource : TFhirResource):TStream; overload;
    function makeUrl(tail : String; params : TAdvStringMatch = nil) : String;
    function makeUrlPath(tail : String) : String;
    function CreateParser(stream : TStream) : TFHIRParser;
    function exchange(url : String; verb : TFHIRClientHTTPVerb; source : TStream; ct : String = '') : TStream;
    function fetchResource(url : String; verb : TFHIRClientHTTPVerb; source : TStream; ct : String = '') : TFhirResource;
    function makeMultipart(stream: TStream; streamName: string; params: TAdvStringMatch; var mp : TStream) : String;
    procedure SetSmartToken(const Value: TSmartOnFhirAccessToken);
    procedure SetTimeout(const Value: cardinal);
    procedure createClient;
    procedure setHeader(name, value : String);
    function GetHeader(name : String) : String;
    function exchangeIndy(url: String; verb: TFHIRClientHTTPVerb; source: TStream; ct: String): TStream;
    function exchangeHTTP(url: String; verb: TFHIRClientHTTPVerb; source: TStream; ct: String): TStream;
  public
    constructor Create(url : String; json : boolean); overload;
    destructor Destroy; override;
    property url : String read FUrl;

    property Json : boolean read FJson write FJson;

    function link : TFHIRClient; overload;
    property smartToken : TSmartOnFhirAccessToken read FSmartToken write SetSmartToken;
    property timeout : cardinal read FTimeout write SetTimeout;
    property UseIndy : boolean read FUseIndy write FUseIndy; // set this to true for a service, but you may have problems with SSL

//    procedure doRequest(request : TFHIRRequest; response : TFHIRResponse);
    procedure cancelOperation;

    function conformance(summary : boolean) : TFhirConformance;
    function transaction(bundle : TFHIRBundle) : TFHIRBundle;
    function createResource(resource : TFhirResource; var id : String) : TFHIRResource;
    function readResource(atype : TFhirResourceType; id : String) : TFHIRResource;
    function updateResource(resource : TFhirResource) : TFHIRResource; overload;
    procedure deleteResource(atype : TFhirResourceType; id : String);
    function search(atype : TFhirResourceType; allRecords : boolean; params : TAdvStringMatch) : TFHIRBundle; overload;
    function search(atype : TFhirResourceType; allRecords : boolean; params : string) : TFHIRBundle; overload;
    function searchPost(atype : TFhirResourceType; allRecords : boolean; params : TAdvStringMatch; resource : TFhirResource) : TFHIRBundle;
    function operation(atype : TFhirResourceType; opName : String; params : TFhirParameters) : TFHIRResource;
    function historyType(atype : TFhirResourceType; allRecords : boolean; params : TAdvStringMatch) : TFHIRBundle;

    property OnClientStatus : TFHIRClientStatusEvent read FOnClientStatus write FOnClientStatus;

  end;

  TFHIRClientTests = class (TAdvObject)
  private
    class procedure testClient(client : TFhirClient);
  public
    class procedure tests(url : String);
  end;

implementation

uses
  TextUtilities;

{ EFHIRClientException }

constructor EFHIRClientException.create(message: String; issue: TFhirOperationOutcome);
begin
  inherited create(message);
  FIssue := issue;
  end;

destructor EFHIRClientException.destroy;
begin
  FIssue.Free;
  inherited;
end;

{ TFhirClient }

constructor TFhirClient.create(url: String; json : boolean);
begin
  Create;
  FUrl := URL;
  FJson := json;

end;

destructor TFhirClient.destroy;
begin
  FSmartToken.Free;
  ssl.Free;
  indy.free;
  http.Free;
  inherited;
end;


function TFhirClient.link: TFHIRClient;
begin
  result := TFHIRClient(inherited Link);
end;

function TFhirClient.conformance(summary : boolean): TFhirConformance;
var
  params : TAdvStringMatch;
begin
  params := TAdvStringMatch.create;
  try
    if summary then
      params.Add('_summary', 'true');
    result := FetchResource(MakeUrl('metadata', params), get, nil) as TFhirConformance;
  finally
    params.Free;
  end;
end;


function TFhirClient.transaction(bundle : TFHIRBundle) : TFHIRBundle;
Var
  src : TStream;
begin
  src := serialise(bundle);
  try
    result := fetchResource(makeUrl(''), post, src) as TFhirBundle;
  finally
    src.free;
  end;
end;


function readIdFromLocation(location : String) : String;
var
  a : TArray<String>;
begin
  a := location.split(['/']);
  if length(a) < 4 then
    raise Exception.Create('Unable to process location header');
  result := a[length(a)-3]; // 1 for offset, 2 for _history and vers
end;

function TFhirClient.createResource(resource: TFhirResource; var id : String): TFHIRResource;
Var
  src : TStream;
begin
  src := serialise(resource);
  try
    result := nil;
    try
      result := fetchResource(MakeUrl(CODES_TFhirResourceType[resource.resourceType]), post, src);
      id := getHeader('Location');
      result.link;
    finally
      result.free;
    end;
  finally
    src.free;
  end;
end;

function TFhirClient.updateResource(resource : TFhirResource) : TFHIRResource;
Var
  src : TStream;
begin
  if (resource.meta <> nil) and (resource.meta.versionId <> '') then
    SetHeader('Content-Location', MakeUrlPath(CODES_TFhirResourceType[resource.resourceType]+'/'+resource.id+'/history/'+resource.meta.versionId));

  src := serialise(resource);
  try
    result := nil;
    try
      result := fetchResource(MakeUrl(CODES_TFhirResourceType[resource.resourceType]+'/'+resource.id), put, src);
      result.link;
    finally
      result.free;
    end;
  finally
    src.free;
  end;
end;

procedure TFhirClient.deleteResource(atype : TFhirResourceType; id : String);
begin
  exchange(MakeUrl(CODES_TFhirResourceType[aType]+'/'+id), delete, nil).free;
end;

//-- Worker Routines -----------------------------------------------------------


function TFhirClient.serialise(resource: TFhirResource): TStream;
var
  ok : boolean;
  comp : TFHIRComposer;
begin
  ok := false;
  result := TBytesStream.create;
  try
    if Fjson then
      comp := TFHIRJsonComposer.create('en')
    else
      comp := TFHIRXmlComposer.create('en');
    try
      comp.Compose(result, resource, false, nil);
    finally
      comp.free;
    end;
    ok := true;
  finally
    if not ok then
      result.free;
  end;
end;

procedure TFhirClient.SetSmartToken(const Value: TSmartOnFhirAccessToken);
begin
  FSmartToken.Free;
  FSmartToken := Value;
  // todo: set the header for the access token
end;

procedure TFhirClient.status(msg: String);
begin
  if assigned(FOnClientStatus) then
    FOnClientStatus(self, msg);
end;

function encodeParams(params : TAdvStringMatch) : String;
var
  i : integer;
begin
  result := '';
  for i := 0 to params.Count - 1 do
    result := result + params.KeyByIndex[i]+'='+EncodeMIME(params.ValueByIndex[i])+'&';
end;

function TFhirClient.search(atype: TFhirResourceType; allRecords: boolean; params: TAdvStringMatch): TFHIRBundle;
begin
  result := search(atype, allrecords, encodeParams(params));
end;

function TFhirClient.search(atype: TFhirResourceType; allRecords: boolean; params: string): TFHIRBundle;
var
  s : String;
  feed : TFHIRBundle;
begin
//    client.Request.RawHeaders.Values['Content-Location'] := MakeUrlPath(CODES_TFhirResourceType[resource.resourceType]+'/'+id+'/history/'+ver);
  result := fetchResource(makeUrl(CODES_TFhirResourceType[aType])+'?'+params, get, nil) as TFHIRBundle;
  try
    s := result.links['next'];
    while AllRecords and (s <> '') do
    begin
      feed := fetchResource(s, get, nil) as TFhirBundle;
      try
        result.entryList.AddAll(feed.entryList);
        s := feed.links['next'];
      finally
        feed.free;
      end;
    end;
    if allRecords then
      result.link_List.Clear;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFhirClient.searchPost(atype: TFhirResourceType; allRecords: boolean; params: TAdvStringMatch; resource: TFhirResource): TFHIRBundle;
var
  src, frm : TStream;
  ct : String;
begin
  src := serialise(resource);
  try
    src.Position := 0;
    ct := makeMultipart(src, 'src', params, frm);
    try
      result := fetchResource(makeUrl(CODES_TFhirResourceType[aType])+'/_search', post, frm) as TFhirBundle;
    finally
      frm.Free;
    end;
  finally
    src.free;
  end;
end;


function TFhirClient.operation(atype : TFhirResourceType; opName : String; params : TFhirParameters) : TFHIRResource;
Var
  src : TStream;
begin
  src := serialise(params);
  try
    src.Position := 0;
    if aType = frtNull then
      result := fetchResource(makeUrl('$'+opName), post, src)
    else
    result := fetchResource(makeUrl(CODES_TFhirResourceType[aType])+'/$'+opName, post, src);
  finally
    src.free;
  end;
end;

function TFhirClient.fetchResource(url: String; verb: TFHIRClientHTTPVerb; source: TStream; ct : String = ''): TFhirResource;
var
  ret : TStream;
  p : TFHIRParser;
begin
  ret := exchange(url, verb, source, ct);
  try
    if ret.Size = 0 then
      result := nil
    else
    begin
//      StreamToFile(ret, 'c:\temp\file.txt');
      p := CreateParser(ret);
      try
        p.parse;
        if (p.resource = nil) then
          raise Exception.create('No response bundle');
        result := p.resource.link;
      finally
        p.free;
      end;
    end;
  finally
    ret.free;
  end;
end;

function TFhirClient.makeMultipart(stream: TStream; streamName: string; params: TAdvStringMatch; var mp : TStream) : String;
var
  m : TMimeMessage;
  p : TMimePart;
  i : integer;
begin
  m := TMimeMessage.create;
  try
    p := m.AddPart(NewGuidURN);
    p.ContentDisposition := 'form-data; name="'+streamName+'"';
    p.Content.LoadFromStream(stream);
    for i := 0 to params.Count - 1 do
    begin
      p := m.AddPart(NewGuidURN);
      p.ContentDisposition := 'form-data; name="'+params.Keys[i]+'"';
      p.Content.AsBytes := TEncoding.UTF8.GetBytes(params.Matches[params.Keys[i]]);
    end;
    m.Boundary := '---'+AnsiString(copy(GUIDToString(CreateGUID), 2, 36));
    m.start := m.parts[0].Id;
    result := 'multipart/form-data; boundary='+String(m.Boundary);
    mp := TMemoryStream.Create;
    m.WriteToStream(mp, false);
  finally
    m.free;
  end;
end;

function TFhirClient.makeUrl(tail: String; params : TAdvStringMatch = nil): String;
begin
  result := FURL;
  if not result.EndsWith('/') then
    result := result + '/';
  result := result + tail;
  if params <> nil then
    result := result + '?' + encodeParams(params);
end;

function TFhirClient.makeUrlPath(tail: String): String;
var
  s : String;
begin
  StringSplit(FURL, '://', s, result);
  StringSplit(result, '://', s, result);
  if not result.EndsWith('/') then
    result := result + '/';
  result := result + tail;
end;

procedure StringSplitTrim(Const sValue, sDelimiter : String; Var sLeft, sRight: String);
begin
  StringSplit(sValue, sDelimiter, sLeft, sRight);
  sLeft := trim(sLeft);
  sRight := trim(sRight);
end;

function TFhirClient.readResource(atype: TFhirResourceType; id: String): TFHIRResource;
begin

  result := nil;
  try
    result := fetchResource(MakeUrl(CODES_TFhirResourceType[AType]+'/'+id), get, nil);
    result.link;
  finally
    result.free;
  end;
end;

function TFhirClient.CreateParser(stream: TStream): TFHIRParser;
begin
  if FJSon then
    result := TFHIRJsonParser.create('en')
  else
    result := TFHIRXmlParser.create('en');
  result.source := stream;
end;

function TFhirClient.historyType(atype: TFhirResourceType; allRecords: boolean; params: TAdvStringMatch): TFHIRBundle;
var
  s : String;
  feed : TFHIRBundle;
  i : integer;
begin
//    client.Request.RawHeaders.Values['Content-Location'] := MakeUrlPath(CODES_TFhirResourceType[resource.resourceType]+'/'+id+'/history/'+ver);
  status('Fetch History for '+PLURAL_CODES_TFhirResourceType[aType]);
  result := fetchResource(makeUrl(CODES_TFhirResourceType[aType])+'/_history?'+encodeParams(params), get, nil) as TFhirBundle;
  try
    s := result.links['next'];
    i := 1;
    while AllRecords and (s <> '') do
    begin
      inc(i);
      status('Fetch History for '+PLURAL_CODES_TFhirResourceType[aType]+' page '+inttostr(i));
      feed := fetchResource(s, get, nil) as TFhirBundle;
      try
        result.entryList.AddAll(feed.entryList);
        s := feed.links['next'];
      finally
        feed.free;
      end;
    end;
    if allRecords then
      result.link_List.Clear;
    result.Link;
  finally
    result.Free;
  end;
end;

procedure TFhirClient.createClient;
begin
  if FUseIndy then
  begin
    if (indy = nil) then
    begin
      indy := TIdHTTP.create(nil);
      indy.HandleRedirects := true;
      ssl := TIdSSLIOHandlerSocketOpenSSL.Create(nil);
      indy.IOHandler := ssl;
      ssl.SSLOptions.Mode := sslmClient;
      ssl.SSLOptions.SSLVersions := [sslvTLSv1_2]
    end;
  end
  else
  begin
    http := TAdvWinInetClient.Create;
    http.UseWindowsProxySettings := true;
  end;
end;

procedure TFhirClient.setHeader(name, value: String);
begin
  createClient;
  if FUseIndy then
    indy.Request.RawHeaders.Values[name] := value
  else
    http.Headers[name] := value;
end;

function TFhirClient.GetHeader(name: String): String;
begin
  createClient;
  if FUseIndy then
    result := indy.Request.RawHeaders.Values[name]
  else
    result := http.getResponseHeader(name);
end;


function TFhirClient.exchange(url : String; verb : TFHIRClientHTTPVerb; source : TStream; ct : String = '') : TStream;
begin
  createClient;
  if FUseIndy then
    result := exchangeIndy(url, verb, source, ct)
  else
    result := exchangeHTTP(url, verb, source, ct)
end;

function TFhirClient.exchangeHTTP(url: String; verb: TFHIRClientHTTPVerb; source: TStream; ct: String): TStream;
var
  comp : TFHIRParser;
  ok : boolean;
  cnt : String;
  op : TFHIROperationOutcome;
  code : integer;
begin
  if FJson then
begin
    http.RequestType := 'application/json+fhir; charset=utf-8';
    http.ResponseType := 'application/json+fhir; charset=utf-8';
  end
  else
  begin
    http.RequestType := 'application/xml+fhir; charset=utf-8';
    http.ResponseType := 'application/xml+fhir; charset=utf-8';
  end;
  if ct <> '' then
    http.RequestType := ct;

  http.SetAddress(url);
  ok := false;
  Try
    Try
      case verb of
        get :
          begin
          http.RequestType := 'GET';
          end;
        post :
          begin
          http.RequestType := 'POST';
          http.Request := TADvBuffer.create;
          http.Request.LoadFromStream(source);
          end;
        put :
          begin
          http.RequestType := 'PUT';
          http.Request.LoadFromStream(source);
          end;
        delete : raise Exception.Create('to do'); // client.Delete(url);
      end;

      http.Response := TAdvBuffer.create;
      http.Execute;

      code := StrToInt(http.ResponseCode);
      if (code < 200) or (code >= 300) Then
        raise exception.create('unexpected condition');
      ok := true;
      result := TMemoryStream.Create;
      http.Response.SaveToStream(result);
      result.Position := 0;
    except
      on E:EIdHTTPProtocolException do
      begin
        cnt := e.ErrorMessage;
        if StringFind(cnt, 'OperationOutcome') > 0 then
        begin
          removeBom(cnt);
          if FJson then
            comp := TFHIRJsonParser.create('en')
          else
            comp := TFHIRXmlParser.create('en');
  try
            comp.source := TStringStream.create(cnt);
            comp.Parse;
            if (comp.resource <> nil) and (comp.resource.ResourceType = frtOperationOutcome) then
            begin
              op := TFhirOperationOutcome(comp.resource);
              if (op.text <> nil) and (op.text.div_ <> nil) then
                Raise EFHIRClientException.create(FhirHtmlToText(op.text.div_), comp.resource.link as TFhirOperationOutcome)
              else if (op.issueList.Count > 0) and (op.issueList[0].diagnostics <> '') then
                Raise EFHIRClientException.create(op.issueList[0].diagnostics, comp.resource.link as TFhirOperationOutcome)
              else
                raise exception.Create(cnt)
            end
            else
              raise exception.Create(cnt)
          finally
            comp.source.free;
            comp.Free;
          end;
        end
        else
          raise exception.Create(cnt)
      end;
      on e : exception do
      begin
        raise exception.Create(e.Message)
      end;
    end;
  finally
    if not ok then
      result.free;
  end;
  end;

function TFhirClient.exchangeIndy(url : String; verb : TFHIRClientHTTPVerb; source : TStream; ct : String) : TStream;
var
  comp : TFHIRParser;
  ok : boolean;
  cnt : String;
  op : TFHIROperationOutcome;
begin
  if FJson then
  begin
    indy.Request.ContentType := 'application/json+fhir; charset=utf-8';
    indy.Request.Accept := 'application/json+fhir; charset=utf-8';
  end
  else
  begin
    indy.Request.ContentType := 'application/xml+fhir; charset=utf-8';
    indy.Request.Accept := 'application/xml+fhir; charset=utf-8';
  end;
  if ct <> '' then
    indy.Request.ContentType := ct;

  ok := false;
  result := TMemoryStream.create;
  Try
    Try
      case verb of
        get : indy.Get(url, result);
        post : indy.Post(url, source, result);
        put : indy.Put(url, source, result);
        delete : raise Exception.Create('to do'); // client.Delete(url);
      end;

      if (indy.ResponseCode < 200) or (indy.ResponseCode >= 300) Then
        raise exception.create('unexpected condition');
      ok := true;
      if (result <> nil) then
         result.Position := 0;
    except
      on E:EIdHTTPProtocolException do
      begin
        cnt := e.ErrorMessage;
        if StringFind(cnt, 'OperationOutcome') > 0 then
        begin
          removeBom(cnt);
          if FJson then
            comp := TFHIRJsonParser.create('en')
          else
            comp := TFHIRXmlParser.create('en');
          try
            comp.source := TStringStream.create(cnt);
            comp.Parse;
            if (comp.resource <> nil) and (comp.resource.ResourceType = frtOperationOutcome) then
            begin
              op := TFhirOperationOutcome(comp.resource);
              if (op.text <> nil) and (op.text.div_ <> nil) then
                Raise EFHIRClientException.create(FhirHtmlToText(op.text.div_), comp.resource.link as TFhirOperationOutcome)
              else if (op.issueList.Count > 0) and (op.issueList[0].diagnostics <> '') then
                Raise EFHIRClientException.create(op.issueList[0].diagnostics, comp.resource.link as TFhirOperationOutcome)
              else
                raise exception.Create(cnt)
            end
            else
              raise exception.Create(cnt)
          finally
            comp.source.free;
            comp.Free;
          end;
        end
        else
          raise exception.Create(cnt)
      end;
      on e : exception do
      begin
        raise exception.Create(e.Message)
      end;
    end;
  finally
    if not ok then
      result.free;
  end;
end;

procedure TFhirClient.SetTimeout(const Value: cardinal);
begin
  FTimeout := Value;
  createClient;
  if FUseIndy then
  begin
    indy.IOHandler.ReadTimeout := Value;
    indy.ReadTimeout := Value;
  end;
end;

procedure TFhirClient.cancelOperation;
begin
  if not FUseIndy then
    raise Exception.Create('Cancel not supported')
  else if indy <> nil then
    indy.Disconnect;
end;


{ TFHIRClientTests }

class procedure TFHIRClientTests.testClient(client: TFhirClient);
var
  conf : TFHIRConformance;
begin
  client.conformance(true).Free;
  client.conformance(false).Free;
end;

class procedure TFHIRClientTests.tests(url: String);
var
  client : TFhirClient;
begin
  client := TFhirClient.Create(url, true);
  try
    client.UseIndy := true;
    testClient(client);
  finally
    client.free;
  end;
  client := TFhirClient.Create(url, false);
  try
    client.UseIndy := true;
    testClient(client);
  finally
    client.free;
  end;
  client := TFhirClient.Create(url, true);
  try
    client.UseIndy := false;
    testClient(client);
  finally
    client.free;
  end;
  client := TFhirClient.Create(url, false);
  try
    client.UseIndy := false;
    testClient(client);
  finally
    client.free;
  end;
end;

end.

