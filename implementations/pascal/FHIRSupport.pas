unit FHIRSupport;

{
Copyright (c) 2011+, HL7, Inc
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

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
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



{!Wrapper uses Classes,FHIRBase,FHIRResources,FHIRResources_Wrapper, FHIRTypes_Wrapper, FHIRTypes}

interface

uses
  Classes,
  SysUtils,
  IdGlobal, IdSoapMime,
  Parsemap, TextUtilities,
  StringSupport, DecimalSupport, GuidSupport,
  AdvObjects, AdvBuffers, AdvStringLists, AdvStringMatches,
  DateAndTime, JWT, SCIMObjects,
  FHirBase, FHirResources, FHIRConstants, FHIRComponents, FHIRTypes;

Const
   HTTP_OK_200 = 200;
   HTTP_OK_220 = 220;
   HTTP_CREATED = 201;
   HTTP_ACCEPTED = 202;
   HTTP_NO_CONTENT = 204;
   HTTP_REDIR_MULTIPLE_CHOICE = 300;
   HTTP_REDIR_MOVED_PERMANENT = 301;
   HTTP_REDIR_MOVED_TEMPORARY = 302;
   HTTP_REDIR_AFTER_POST = 303;
   HTTP_REDIR_NOT_MODIFIED = 304;
   HTTP_ERR_BAD_REQUEST = 400;
   HTTP_ERR_UNAUTHORIZED = 401;
   HTTP_ERR_FORBIDDEN = 403;
   HTTP_ERR_NOTFOUND = 404;
   HTTP_ERR_METHOD_ILLEGAL = 405;
   HTTP_ERR_CONFLICT = 409;
   HTTP_ERR_DELETED = 410;
   HTTP_ERR_PRECONDITION_FAILED = 412;
   HTTP_ERR_BUSINESS_RULES_FAILED = 422;
   HTTP_ERR_INTERNAL = 500;

Type
  {$M+}


  {@Class TFhirSession
    User session associated with a FHIR request.
    There is always a session, but it may only have ip address

    If the user has logged in using OAuth, then the user may have identifying details
    if the user login matches a User resource, that will be available too. The information
    may differ if the OAuth provided information differs from that in the resource (the
    resource is not updated by the OAuth information)
  }
  {!.Net HL7Connect.Fhir.Request}
  TFhirSession = class (TAdvObject)
  private
    FProvider : TFHIRAuthProvider;
    FId : String;
    FName : String;
    FCookie : String;
    FExpires : TDateTime;
    FOriginal: String;
    FKey: Integer;
    FEmail: String;
    FInnerToken, FOuterToken: String;
    FNextTokenCheck: TDateTime;
    FUseCount: integer;
    FRights: TStringList;
    FFirstCreated: TDateTime;
    FJwt : TJWT;
    FJwtPacked : String;
    FTaggedCompartments : TStringList;
    FPatientList: TStringList;
    FsessionLength: String;
    FUser : TSCIMUser;
    Fanonymous : boolean;

    procedure SetJwt(const Value: TJWT);
    procedure SetUser(const Value: TSCIMUser);
  public
    Constructor Create; Override;
    destructor Destroy; Override;
    function Link : TFhirSession; overload;

    {@member Key
      Primary database key for this session. Don't change!
    }
    Property Key : Integer read FKey write FKey;

    {@member Provider
      Which Authorization provider scopes the login (blank = direct login)
    }
    Property Provider : TFHIRAuthProvider read FProvider write FProvider;

   {@member InnerToken
      the OAuth authorization token (Don't change!)
      This is the OAuth token for the identity server
    }
    Property InnerToken : String read FInnerToken write FInnerToken;

    {@member OuterToken
      the OAuth authorization token (Don't change!)
      This is the OAuth token for this server
    }

    Property OuterToken : String read FOuterToken write FOuterToken;    {@member Id
      OAuth provided user id
    }
    Property Id : String read FId write FId;

    {@member Name
      OAuth provider User name
    }
    Property Name : String read FName write FName;

    {@member Email
      OAuth provided Users email address
    }
    Property Email : String read FEmail write FEmail;

    {@member Cookie
      the shared secret between client and server that identifies this session (i.e. cookie value). (Don't change!)
    }
    Property Cookie : String read FCookie write FCookie;

    {@member Expires
      When this session expires
    }
    Property Expires : TDateTime read FExpires write FExpires;

    Property FirstCreated : TDateTime read FFirstCreated;

    {@member NextTokenCheck
      When the token is next going to be checked with the OAuth server
    }
    Property NextTokenCheck : TDateTime read FNextTokenCheck write FNextTokenCheck;

    {@member OriginalUrl
      The url that the session started with (used through the login process)
    }
    Property OriginalUrl : String read FOriginal write FOriginal;

    {@member User
      User resource associated with this session (if a matching one exists)
    }
    Property User : TSCIMUser read FUser write SetUser;

    {@member JWT
      The JWT token (Open ID Connect token) associated with this session
    }
    Property JWT : TJWT read FJwt write SetJwt;

    {@member JWTPacked
      The JWT packed and signed using RSA
    }
    Property JWTPacked : string read FJWTPacked write FJWTPacked;

    Property useCount : integer read FUseCount write FUseCount;
    Property rights : TStringList read FRights;
    function HasRight(code : String):boolean;
    property TaggedCompartments : TStringList read FTaggedCompartments;
    property sessionLength : String read FsessionLength write FsessionLength;
    property PatientList : TStringList read FPatientList;
    Property anonymous : boolean read Fanonymous write Fanonymous;
  end;

  {@Class TFHIRRequest
    A FHIR request.

    The request may have been received over a FHIR RESTful interface, or by receiving
    a resource or an atom feed directly from some other kind of interface.

    The request may be modified by a script. HL7Connect will ignore changes to the following
    properties: url, baseURL, resourceType, and format. These properties should be treated as read-only,
    but the other properties can be changed as desired
  }
  {!.Net HL7Connect.Fhir.Request}
  TFHIRRequest = class (TAdvObject)
  Private
    FId: String;
    FSubId: String;
    FCommandType: TFHIRCommandType;
    FResourceType: TFhirResourceType;
    FFormat: TFHIRFormat;
    FResource: TFhirResource;
    FUrl: String;
    FBaseUrl: String;
    ForiginalId: String;
    FversionId: String;
    FlastModifiedDate: TDateTime;
    FParams: TParseMap;
    FSource: TAdvBuffer;
    FcontentLocation: String;
    FDefaultSearch: boolean;
    FLang: String;
    FSession: TFhirSession;
    FCategories : TFHIRCodingList;
    FContent : TAdvBuffer;
    FIp: string;
    FCompartments: String;
    FCompartmentId: String;
    FForm: TIdSoapMimeMessage;
    FOperationName: String;
    FIfMatch : String;
    FMeta: TFhirMeta;
    FProvenance: TFhirProvenance;
    procedure SetResource(const Value: TFhirResource);
    procedure SetSource(const Value: TAdvBuffer);
    procedure SetSession(const Value: TFhirSession);
    function GetFeed: TFhirBundle;
    procedure SetFeed(const Value: TFhirBundle);
    procedure SetMeta(const Value: TFhirMeta);
    procedure SetProvenance(const Value: TFhirProvenance);
  Public
    Constructor Create; Override;
    Destructor Destroy; Override;
    Function Link : TFHIRRequest; Overload;

    {!Script Hide}
    Function Compose : String;
    procedure LoadParams(s : String); overload;
    procedure LoadParams(form : TIdSoapMimeMessage); overload;
    Function LogSummary : String;
    function XMLSummary : String;
    Procedure CopyPost(stream : TStream);
    Property Source : TAdvBuffer read FSource write SetSource;
    Property Session : TFhirSession read FSession write SetSession;
    Property ip : string read FIp write FIp;
    Property form : TIdSoapMimeMessage read FForm write FForm;

    Property DefaultSearch : boolean read FDefaultSearch write FDefaultSearch;

    {@member Parameters
      any parameters associated with the request (part after the ? in the url). Use
      for search/update
    }
    property Parameters : TParseMap read FParams;

    Property Content : TAdvBuffer read FContent;
    {!Script Show}

  published
    {@member url
      The full URL of the original request, if the request was made on a RESTful interface (else empty)
    }
    property url : String read FUrl write FUrl;

    {@member baseUrl
      The baseURL (see the FHIR specification under "HTTP REST interface) of the interface,
      if the request was made on a RESTful interface (else empty)
    }
    property baseUrl : String read FBaseUrl write FBaseUrl;

    {@member ResourceType
      The type of the resource. Cannot be changed

      frtNull if this is a bundle
    }
    Property ResourceType : TFhirResourceType Read FResourceType write FResourceType;

    {@member CommandType
      The command (http transaction). This can be changed, though it is unusual to
      change the command (consequences can be unexpected and dramatic)

      fcmdUnknown if this is not on a RESTful interface
    }
    Property CommandType : TFHIRCommandType Read FCommandType Write FCommandType;

    {@member Id
      The resource id associated with the request, if one is identified as part of the request
    }
    Property Id : String Read FId write FId;

    {@member SubId
      A secondary id associated with the request (only used for the version id in a version specific request)
    }
    Property SubId : String Read FSubId write FSubId;

    {@member OperationName
      The name of an operation, if an operation was invoked
    }
    Property OperationName : String read FOperationName write FOperationName;

    {@member PostFormat
      The format of the request, if known and identified (xml, json, or xhtml). Derived
      from the content-type and/or extension in the url, or configuration
    }
    Property PostFormat : TFHIRFormat read FFormat write FFormat;

    {@member Resource
      the actual resource, if a resource was submitted as part of the request.

      Note that actual kind of the resource will be one of the ones defined as
      part of the FHIR specification
    }
    Property Resource : TFhirResource read FResource write SetResource;
    Property Feed : TFhirBundle read GetFeed write SetFeed;
    property meta : TFhirMeta read FMeta write SetMeta;

    {@member categories
      Tags on the request - if it's a resource directly
    }
    property categories : TFHIRCodingList read Fcategories;

    {@member originalId
      The specified originalId of the resource in the request (if present) (i.e. in a transaction)
    }
    Property originalId : String read ForiginalId write ForiginalId;

    {@member compartments
      If the user is limited to a set of compartments, this is the list (comma separated, with quotes)
    }
    Property compartments : String read FCompartments write FCompartments;

    {@member compartmentId
      if operation is limited to a patient compartment, the id in the compartment
    }
    Property compartmentId : String read FCompartmentId write FCompartmentId;

    {@member contentLocation
      Quoted Content location on request. Used for version aware updates. Only on RESTful interface
    }
    Property contentLocation : String read FcontentLocation write FcontentLocation;

//    {@member versionId
//      The ETag of the resource identified in the request (if present)
//    }
//    Property e_versionId : String read FversionId write FversionId;

    {@member lastModifiedDate
      The last modified date of the resource identified in the request (if present)
    }
    Property lastModifiedDate : TDateTime read FlastModifiedDate write FlastModifiedDate;

    {@member Lang
      Preferred language of the requester (used for error messages)
    }
    Property Lang : String read FLang write FLang;

    Property IfMatch : String read FIfMatch write FIfMatch;

    Property Provenance : TFhirProvenance read FProvenance write SetProvenance;
  End;

  {@Class TFHIRResponse
    A FHIR response.

    This is a response for a RESTful interface, or some other kind of response. The
    HTTP code is used as the logical outcome with other kinds of interfaces.

    A response may have only one of
      * a body
      * a resource
      * a feed

    The string body is used for error messages, or to return xhtml or schema etc.

    A script may modify any of the values of the response, though changing the
    type of the resource may have unexpected catastrophic outcomes.
  }
  {!.Net HL7Connect.Fhir.Response}
  TFHIRResponse = class (TAdvObject)
  private
    FHTTPCode: Integer;
    FBody: String;
    FMessage: String;
    FResource: TFhirResource;
    FversionId: String;
    ForiginalId: String;
    FlastModifiedDate: TDateTime;
    FContentType: String;
    FFormat: TFHIRFormat;
    FContentLocation: String;
    FLocation: String;
    FCategories : TFHIRCodingList;
    Flink_list : TFhirBundleLinkList;
    FOrigin: String;
    FId: String;
    FMeta: TFhirMeta;
    procedure SetResource(const Value: TFhirResource);
    function GetFeed: TFhirBundle;
    procedure SetFeed(const Value: TFhirBundle);
    procedure SetMeta(const Value: TFhirMeta);
  public
    Constructor Create; Override;
    Destructor Destroy; Override;
    {!Script Hide}
    Function Link : TFHIRResponse; Overload;
    {!Script Show}

    {@member HTTPCode
      The logical outcome of the request. Usual values are
        * 0 - the outcome of the transaction is not yet known
        * 200 - the operation completed successfully
        * 202 - the content was accepted (an http variation on 200)
        * 400 - the user made a bad request
        * 404 - the resource wasn't found
        * 500 - some general kind of error

      Any http status code may be used, including codes not defined in the
      http standard (i.e. return 299 to prevent a proxy from caching the response).
      HL7Connect will follow the http standard and use the first digit to determine
      the general outcome
    }
    Property HTTPCode : Integer read FHTTPCode write FHTTPCode;

    {@member Message
      a specific message to go in the HTTP response line. If left blank,
      HL7Connect will fill this out from the http specification
    }
    Property Message : String read FMessage write FMessage;

    {@member Body
      a series of characters that constitute the body of the response. Usually this is
      plain text, but other content (xhtml, schema) could be placed in here.
      If using other than plain text, set the @contentType
    }
    Property Body : String read FBody write FBody;

    {@member Resource
      the actual resource that is the result of the transaction.

      Note that actual kind of the resource will be one of the ones defined as
      part of the FHIR specification
    }
    Property Resource : TFhirResource read FResource write SetResource;
    Property Feed : TFhirBundle read GetFeed write SetFeed;

    {@member Format
      The format for the response, if known and identified (xml, or json). Derived
      from the requested content-type and/or extension in the url, or configuration
    }
    Property Format : TFHIRFormat read FFormat write FFormat;

    {@member ContentType
      The content type of the response. if left blank, this will be determined
      automatically (text/plain for body, and type as specifed in the FHIR
      specification for resources and feeds.
    }
    Property ContentType : String read FContentType write FContentType;

    {@member originalId
      The originalId of the resource - if known
    }
    Property originalId : String read ForiginalId write ForiginalId;

    {@member Id
      The underlying id, if there is one. Only used internally - not represented on the wire
    }
    Property Id : String read FId write FId;

    {@member versionId
      The ETag to go in the response
    }
    Property versionId : String read FversionId write FversionId;

    {@member lastModifiedDate
      The Last Modified Date to go in the response
    }
    Property lastModifiedDate : TDateTime read FlastModifiedDate write FlastModifiedDate;

    {@member ContentLocation
      Content-Location in HTTP response
    }
    Property ContentLocation : String read FContentLocation write FContentLocation;

    {@member Location
      Location in HTTP response (only used for Create operation)
    }
    Property Location : String read FLocation write FLocation;

    {@member categories
      Tags for the response
    }
    property categories : TFHIRCodingList read Fcategories;

    property meta : TFhirMeta read FMeta write SetMeta;

    {@member link_list
      link_list for the response
    }
    property link_list : TFhirBundleLinkList read Flink_list;

    {@member Origin
      HTTP Origin header - see http://en.wikipedia.org/wiki/Cross-origin_resource_sharing

      If this has a value when the response is returned, then it will be returned in the Access-Control-Allow-Origin header
    }
    Property Origin : String read FOrigin write FOrigin;
  end;

  ERestfulException = class (EAdvException)
  Private
    FStatus : word;
  Public
    Constructor Create(Const sSender, sMethod, sReason : String; aStatus : word); Overload; Virtual;

    Property Status : word read FStatus write FStatus;
  End;

  {@Class TFHIRFactory
    Creates FHIR types

    * new*: create a type with no values
    * make*: useful helper routines that take parameters and populate the type accordingly (mostly for data types)
  }
  {!.Net HL7Connect.Fhir.Factory}
  TFHIRFactory = class (TFhirResourceFactory)
  private
    FLang : String;
  public
    Constructor Create(lang : String);
    {@member makeAttachmentFromFile
      make a new Attachment, and load the contents from the file. The mime type will be filled out based on the systems interpretation of the file extension
    }
    {!script nolink}
    function makeAttachmentFromFile(filename : String) : TFhirAttachment;

    {@member makeAttachmentFromStream
      make a new Attachment From the stream
    }
    {!script nolink}
    function makeAttachmentFromStream(mimeType : String; stream : TStream) : TFhirAttachment;

    {@member makeAttachmentFromUrl
      make a new Attachment that references a url. The data will be copied into the attachment if specified.

      The URL can't be password protected etc.
    }
    {!script nolink}
    function makeAttachmentFromUrl(url : String; mimeType : String; inlineData : Boolean) : TFhirAttachment;

    {@member makeIdentifier
      make a new Identifier and use the provided parameters
    }
    {!script nolink}
    function makeIdentifier(system, key : String) : TFhirIdentifier;

    {@member makeIdentifierWithUse
      make a new Identifier and use the provided parameters
    }
    {!script nolink}
    function makeIdentifierWithUse(system, key, use, label_ : String) : TFhirIdentifier;

    {@member makeCodeableConcept
      make a new CodeableConcept and use the provided parameters
    }
    {!script nolink}
    function makeCodeableConcept(coding : TFhirCoding; text : String) : TFhirCodeableConcept;

    {@member makeCoding
      make a new Coding and use the provided parameters
    }
    {!script nolink}
    function makeCoding(system : String; code : string; display : String) : TFhirCoding;

    {@member makeQuantity
      make a new Quantity and use the provided parameters
    }
    {!script nolink}
    function makeQuantity(value, units : String):TFhirQuantity;

    {@member makeQuantityCoded
      make a new QuantityCoded and use the provided parameters
    }
    {!script nolink}
    function makeQuantityCoded(value, units, system, code : String):TFhirQuantity;

    {@member makeIntervalQuantity
      make a new IntervalQuantity and use the provided parameters
    }
    {!script nolink}
    function makeRange(low, high : TFhirQuantity):TFhirRange;

    {@member makeIntervalDateTime
      make a new IntervalDateTime and use the provided parameters
    }
    {!script nolink}
    function makePeriod(low, high : string):TFhirPeriod;

    {@member makeIdentifierWithLabel
      make a new HumanId and use the provided parameters
    }
    {!script nolink}
    function makeIdentifierWithLabel(use : string; label_, idSystem, id : String):TFhirIdentifier;

    {@member makeHumanName
      make a new HumanName and use the provided parameters
    }
    {!script nolink}
    function makeHumanName(use, family, given, prefix, suffix : String):TFhirHumanName;

    {@member makeHumanNameText
      make a new HumanName and fill out the text value
    }
    {!script nolink}
    function makeHumanNameText(use, text : String):TFhirHumanName;

    {@member makeAddress
      make a new HumanAddress and use the provided parameters
    }
    {!script nolink}
    function makeAddress(use, street, city, state, postalCode, country : String):TFhirAddress;

    {@member makeAddressText
      make a new HumanAddress and use the provided parameters
    }
    {!script nolink}
    function makeAddressText(use, text : String):TFhirAddress;

    {@member makeContactPoint
      make a new Contact and use the provided parameters
    }
    {!script nolink}
    function makeContactPoint(system, value, use : String):TFhirContactPoint;

    {@member makeReference
      make a new resource reference and use the provided parameters
    }
    {!script nolink}
    function makeReference(id : String) : TFhirReference;

    {@member makeReferenceText
      make a new resource reference and fill out the display only
    }
    {!script nolink}
    function makeReferenceText(s : String) : TFhirReference;

    {@member makeExtension
      make a new narrative with the provided status and html
    }
    {!script nolink}
    function makeExtension(url : String; value : TFhirType) : TFhirExtension;

    {@member makeNarrative
      make a new narrative with the provided status and html
    }
    {!script nolink}
    function makeNarrative(status, html : String; policy : TFHIRXhtmlParserPolicy) : TFhirNarrative;

    {@member parseHTML
      parse an html fragment into an html node (for use with narrative).

      the html fragment must begin and end with <xhtml></xhtml>
    }
    function parseHTML(source : String; policy : TFHIRXhtmlParserPolicy) : TFhirXHtmlNode;

    {@member makeBinary
      make a new Binary resource
    }
    {!script nolink}
    function makeBinary : TFhirBinary;

    {@member makeBinary
      make a new Binary resource
    }
    {!script nolink}
    function makeBinaryContent(source : TAdvBuffer; mimeType : String) : TFhirBinary;

    {@member makeRequest
      make a new Fhir request (for a conversion parameter)
    }
    {!script nolink}
    function makeRequest : TFhirRequest;

    {@member makeRequest
      make a new OperationOutcome that claims success
    }
    {!script nolink}
    function makeSuccessfulOperation : TFhirOperationOutcome;
  end;

Function IdTail(s : String):String;
Function IdHead(s : String):String;

implementation

uses
  FHIRParser,
  FHIRParserBase;

{ ERestfulException }

constructor ERestfulException.Create(const sSender, sMethod, sReason: String; aStatus : word);
begin
  Create(sSender, sMethod, sReason);
  FStatus := aStatus;
end;


{ TFHIRRequest }

function TFHIRRequest.Compose: String;
var
  comp : TFHIRXmlComposer;
  stream : TStringStream;
begin
  stream := TStringStream.Create('');
  try
    comp := TFHIRXmlComposer.create(lang);
    try
      comp.Compose(stream, resource, true, nil);
    finally
      comp.free;
    end;
    result := stream.DataString;
  finally
    stream.free;
  end;
end;

procedure TFHIRRequest.CopyPost(stream: TStream);
var
  p, t : integer;
  b : TBytes;
begin
  p := stream.Position;
  t := stream.size - stream.position;
  SetLength(b, t);
  Stream.Read(b[0], t);
  stream.position := p;
  FContent := TAdvBuffer.create;
  FContent.AsBytes := b;
end;

constructor TFHIRRequest.Create;
begin
  inherited;
  FCategories := TFHIRCodingList.create;
end;

destructor TFHIRRequest.Destroy;
begin
  FMeta.Free;
  FContent.Free;
  FCategories.free;
  FSession.Free;
  FSource.Free;
  FResource.Free;
  FProvenance.Free;
  inherited;
end;

function TFHIRRequest.GetFeed: TFhirBundle;
begin
  result := Resource as TFhirBundle;
end;

function TFHIRRequest.Link: TFHIRRequest;
begin
  result := TFHIRRequest(Inherited Link);
end;

procedure TFHIRRequest.LoadParams(s: String);
begin
  FParams := TParseMap.createSmart(s);
end;

procedure TFHIRRequest.LoadParams(form: TIdSoapMimeMessage);
var
  i : integer;
  p : TIdSoapMimePart;
  s, n : String;
begin
  for i := 0 to form.Parts.Count - 1 do
  begin
    p := form.Parts.PartByIndex[i];
    if (p.MediaType = '') then
    begin
      n := p.ParamName;
      s := UTF8StreamToString(p.Content).trimRight([#13, #10]);
      if (n <> '') and (not s.Contains(#10)) then
        FParams.addItem(n, s);
    end;
  end;
end;

function TFHIRRequest.LogSummary: String;
begin
  result := CODES_TFHIRCommandType[CommandType]+'\('+CODES_TFHIRFormat[PostFormat]+')'+CODES_TFhirResourceType[ResourceType]+'\'+Id;
  if SubId <> '' then
    result := result + '\'+SubId;
end;

procedure TFHIRRequest.SetFeed(const Value: TFhirBundle);
begin
  setresource(value);
end;

procedure TFHIRRequest.SetMeta(const Value: TFhirMeta);
begin
  FMeta.Free;
  FMeta := Value;
end;

procedure TFHIRRequest.SetProvenance(const Value: TFhirProvenance);
begin
  FProvenance.Free;
  FProvenance := Value;
end;

procedure TFHIRRequest.SetResource(const Value: TFhirResource);
begin
  FResource.Free;
  FResource := Value;
end;

procedure TFHIRRequest.SetSession(const Value: TFhirSession);
begin
  FSession.Free;
  FSession := Value;
end;

procedure TFHIRRequest.SetSource(const Value: TAdvBuffer);
begin
  FSource.Free;
  FSource := Value;
end;

function TFHIRRequest.XMLSummary: String;
  procedure addValue(n, v : String; t : boolean);
  begin
    if (t) then
      result := result+'  '+n+': '+v+#13#10;
  end;

begin
  result := #13#10;
  addValue('Command', CODES_TFHIRCommandType[CommandType], true);
  addValue('Url', FUrl, true);
  addValue('format', CODES_TFHIRFormat[PostFormat], true);
  addValue('type', CODES_TFhirResourceType[ResourceType], ResourceType <> frtNull);
  addValue('id', FId, true);
  addValue('subId', FSubId, FSubId <> '');
  addValue('baseUrl', FBaseUrl, FBaseUrl <> '');
  addValue('originalId', ForiginalId, ForiginalId <> '');
  addValue('versionId', FversionId, FversionId <> '');
  addValue('Last-Modified', FormatDateTime('c', FlastModifiedDate), FlastModifiedDate <> 0);
  addValue('Content-Location', FcontentLocation, FcontentLocation <> '');
  addValue('defaultSearch', BooleanToString(FDefaultSearch), CommandType = fcmdSearch);
  addValue('Language', FLang, FLang <> '');
  addValue('Category', FCategories.ToString, FCategories.count > 0);
  addValue('ip', FIp, FIp <> '');
  addValue('compartments', FCompartments, FCompartments <> '');
  addValue('compartmentId', FCompartmentId, FCompartmentId <> '');
end;

{ TFHIRResponse }

constructor TFHIRResponse.Create;
begin
  inherited;
  FCategories := TFHIRCodingList.create;
  Flink_list := TFhirBundleLinkList.create;
end;

destructor TFHIRResponse.Destroy;
begin
  Flink_list.Free;
  FCategories.free;
  FResource.Free;
  FMeta.Free;
  inherited;
end;

function TFHIRResponse.GetFeed: TFhirBundle;
begin
  result := FResource as TFhirBundle;
end;

function TFHIRResponse.Link: TFHIRResponse;
begin
  result := TFHIRResponse(Inherited Link);
end;

procedure TFHIRResponse.SetFeed(const Value: TFhirBundle);
begin
  SetResource(value);
end;

procedure TFHIRResponse.SetMeta(const Value: TFhirMeta);
begin
  FMeta.Free;
  FMeta := Value;
end;

procedure TFHIRResponse.SetResource(const Value: TFhirResource);
begin
  FResource.free;
  FResource := nil;
  FResource := Value;
end;

{ TFHIRFactory }

function CheckEnum(Names: array of String; value : String): TFhirEnum;
begin
  if value = '' then
    result := nil
  else if StringIsInteger32(value) then
    result := TFhirEnum.create(Names[StrToInt(value)])
  else if StringArrayIndexOfSensitive(Names, value) > -1 then
    result := TFhirEnum.create(value)
  else
    Raise Exception.create('Invalid enumeration value "'+value+'"');
end;


function TFHIRFactory.makeAttachmentFromFile(filename: String): TFhirAttachment;
begin
  raise Exception.Create('to do');
{  result := TFhirAttachment.create;
  try
    result.data := TFhirBase64Binary.create;
    result.data.value := BinToBase64(FileToString(filename));
    result.contentType := TFhirCode.Create(GetMIMETypeFromFile(filename));
    result.link;
  finally
    result.free;
  end;}
end;

function TFHIRFactory.makeAttachmentFromStream(mimeType: String; stream: TStream): TFhirAttachment;
begin
  raise Exception.Create('to do');
{  result := TFhirAttachment.create;
  try
    result.data := TFhirBase64Binary.create;
    result.data.value := BinToBase64(StreamToString(stream));
    result.contentType := TFhirCode.create(mimeType);
    result.link;
  finally
    result.free;
  end;}
end;

function TFHIRFactory.makeAttachmentFromUrl(url, mimeType: String; inlineData: Boolean): TFhirAttachment;
begin
  raise Exception.Create('to do');
{  result := nil;
  try
    todo;
    result.link;
  finally
    result.free;
  end;}
end;

function TFHIRFactory.makeCodeableConcept(coding: TFhirCoding; text: String): TFhirCodeableConcept;
begin
  result := TFhirCodeableConcept.create;
  try
    result.codingList.add(coding);
    result.text := text;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeCoding(system, code, display: String): TFhirCoding;
begin
  result := TFhirCoding.create;
  try
    result.code := code;
    result.system := system;
    result.display := display;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeContactPoint(system, value, use: String): TFhirContactPoint;
begin
  result := TFhirContactPoint.create;
  try
    result.useElement := CheckEnum(CODES_TFhirContactPointUse, use);
    result.systemElement := CheckEnum(CODES_TFhirContactPointSystem, system);
    result.value := value;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeAddress(use, street, city, state, postalCode, country : String): TFhirAddress;
begin
  result := TFhirAddress.create;
  try
    result.useElement := CheckEnum(CODES_TFhirAddressUse, use);
    result.lineList.AddItem(street);
    result.city := city;
    result.state := state;
    result.postalCode := postalCode;
    result.country := country;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeIdentifierWithLabel(use : string; label_, idSystem, id: String): TFhirIdentifier;
begin
  result := TFhirIdentifier.create;
  try
    result.useElement := CheckEnum(CODES_TFhirIdentifierUse, use);
    result.label_ := label_;
    result.system := idsystem;
    result.value := id;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeHumanName(use, family, given, prefix, suffix: String): TFhirHumanName;
begin
  result := TFhirHumanName.create;
  try
    result.useElement := CheckEnum(CODES_TFhirNameUse, use);
    result.familyList.addItem(family);
    result.givenList.addItem(given);
    result.prefixList.addItem(prefix);
    result.suffixList.addItem(suffix);
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeIdentifierWithUse(system, key, use, label_ : String) : TFhirIdentifier;
begin
  result := TFhirIdentifier.create;
  try
    result.system := system;
    result.value := key;
    result.label_ := label_;
    result.useElement := CheckEnum(CODES_TFhirIdentifierUse, use);
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeIdentifier(system, key: String): TFhirIdentifier;
begin
  result := TFhirIdentifier.create;
  try
    result.system := system;
    result.value := key;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makePeriod(low, high: string): TFhirPeriod;
begin
  result := TFhirPeriod.create;
  try
    if low <> '' then
      result.start := TDateAndTime.createXml(low);
    if high <> '' then
      result.end_ := TDateAndTime.createXml(high);
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeRange(low, high: TFhirQuantity): TFhirRange;
begin
  result := TFhirRange.create;
  try
    result.low := low.Link;
    result.high := high.Link;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeQuantity(value, units: String): TFhirQuantity;
begin
  result := TFhirQuantity.create;
  try
    result.value := value;
    result.units := units;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeQuantityCoded(value, units, system, code: String): TFhirQuantity;
begin
  result := TFhirQuantity.create;
  try
    result.value := value;
    result.units := units;
    result.system := system;
    result.code := code;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.parseHTML(source: String; policy : TFHIRXhtmlParserPolicy): TFhirXHtmlNode;
var
  parser : TFHIRXmlParserBase;
begin
  parser := TFHIRXmlParserBase.create(Flang);
  try
    parser.ParserPolicy := policy;
    parser.Source := TStringStream.create(source);
    result := parser.ParseHtml;
    try
      if result.Name <> 'div' then
        raise Exception.create('Wrong name "'+result.Name+'+ on the xhtml fragment');
      result.link;
    finally
      result.free;
    end;
  finally
    parser.source.free;
    parser.Free;
  end;
end;

function TFHIRFactory.makeBinary: TFhirBinary;
begin
  result := TFhirBinary.create;
end;

function TFHIRFactory.makeBinaryContent(source: TAdvBuffer; mimeType: String): TFhirBinary;
begin
  result := makeBinary;
  result.Content := source.AsBytes;
  result.ContentType := mimeType;
end;

function TFHIRFactory.makeReference(id: String): TFhirReference;
begin
  result := TFhirReference.create;
  try
    result.reference := id;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeNarrative(status, html: String; policy : TFHIRXhtmlParserPolicy): TFhirNarrative;
begin
  result := TFhirNarrative.create;
  try
    result.statusElement := CheckEnum(CODES_TFhirNarrativeStatus, status);
    result.div_ := parseHTML(html, policy);
    result.link;
  finally
    result.free;
  end;
end;

Function IdTail(s : String):String;
begin
  if (pos('/', s) = 0) then
    result := s
  else
  begin
    result := copy(s, LastDelimiter('/', s)+1, $FF);
    if result[1] = '@' then
      delete(result, 1, 1);
  end;
end;

Function IdHead(s : String):String;
begin
  if (pos('/', s) > 0) then
    result := copy(s, 1, LastDelimiter('/', s))
  else
    result := '';
end;

constructor TFHIRFactory.Create(lang: String);
begin
  Inherited Create;
  FLang := lang;
end;


function TFHIRFactory.makeReferenceText(s: String): TFhirReference;
begin
  result := TFhirReference.create;
  try
    result.display := s;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeRequest: TFhirRequest;
begin
  result := TFhirRequest.create;
end;

function TFHIRFactory.makeSuccessfulOperation: TFhirOperationOutcome;
begin
  result := TFhirOperationOutcome.create;
  try
    result.text := makeNarrative('generated', '<div>The operation was successful</div>', xppReject);
    result.link;
  finally
    result.Free;
  end;
end;

function TFHIRFactory.makeHumanNameText(use, text: String): TFhirHumanName;
begin
  result := TFhirHumanName.create;
  try
    result.useElement := CheckEnum(CODES_TFhirNameUse, use);
    result.text := text;
    result.link;
  finally
    result.free;
  end;

end;

function TFHIRFactory.makeAddressText(use, text: String): TFhirAddress;
begin
  result := TFhirAddress.create;
  try
    result.useElement := CheckEnum(CODES_TFhirAddressUse, use);
    result.text := text;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRFactory.makeExtension(url: String; value: TFhirType): TFhirExtension;
begin
  result := TFhirExtension.create;
  try
    result.url := url;
    result.value := value.Link;
    result.link;
  finally
    result.free;    
  end;

end;


{ TFhirSession }

constructor TFhirSession.Create;
begin
  inherited;
  FRights := TStringList.Create;
  FFirstCreated := now;
  FTaggedCompartments := TStringList.create;
  FPatientList := TStringList.create;
end;

destructor TFhirSession.Destroy;
begin
  FJwt.free;
  FRights.Free;
  FTaggedCompartments.Free;
  FPatientList.Free;
  FUser.Free;
  inherited;
end;

function TFhirSession.HasRight(code: String): boolean;
begin
  result :=  Rights.IndexOf(code) > -1;
end;

function TFhirSession.Link: TFhirSession;
begin
  result := TFhirSession(inherited Link);
end;


procedure TFhirSession.SetJwt(const Value: TJWT);
begin
  FJwt.free;
  FJwt := Value;
end;

procedure TFhirSession.SetUser(const Value: TSCIMUser);
begin
  FUser.Free;
  FUser := Value;
end;


end.
