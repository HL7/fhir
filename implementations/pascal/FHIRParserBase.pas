Unit FHIRParserBase;

{
Copyright (c) 2011-2013, HL7, Inc
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

Interface

uses
  SysUtils, Classes, ActiveX, IdSoapMsXml, IdSoapXml, FHIRBase, FHIRResources, FHIRTypes, Math,
  BytesSupport, FHIRConstants, EncdDecd,
  FHIRSupport,
  MsXmlParser, AdvBuffers, AdvStringLists, StringSupport, DecimalSupport, EncodeSupport, DateAndTime,
  XmlBuilder, AdvXmlBuilders, TextUtilities, FHIRTags,
  DateSupport, MsXmlBuilder, JSON, AdvVCLStreams, FHIRAtomFeed, AdvStringStreams, AdvStringBuilders, FHIRLang;

const
  ATOM_NS = 'http://www.w3.org/2005/Atom';
  GDATA_NS = 'http://schemas.google.com/g/2005';
  XHTML_NS = 'http://www.w3.org/1999/xhtml';
  FHIR_JS =
    '<script type="text/javascript" src="/js/json2.js"></script>'+#13#10+
    '<script type="text/javascript" src="/js/statuspage.js"></script>'+#13#10+
    '<script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>'+#13#10+
    '<script type="text/javascript" src="/js/jquery-ui-1.8.16.custom.min.js"></script>'+#13#10+
    '<link rel="stylesheet" href="/css/jquery.ui.all.css">'+#13#10+
    '<script src="/js/jquery-1.6.2.js"></script>'+#13#10+
    '<script src="/js/jquery.ui.core.js"></script>'+#13#10+
    '<script src="/js/jquery.ui.widget.js"></script>'+#13#10+
    '<script src="/js/jquery.ui.mouse.js"></script>'+#13#10+
    '<script src="/js/jquery.ui.resizable.js"></script>'+#13#10+
    '<script src="/js/jquery.ui.draggable.js"></script>'+#13#10+
    '<script type="text/javascript" src="/js/jtip.js"></script>'+#13#10+
    '<script type="text/javascript" src="/js/jcookie.js"></script>'+#13#10+
    '<script type="text/javascript" src="/js/hl7connect.js"></script>'+#13#10+
    '<script type="text/javascript" src="/js/fhir-gw.js"></script>'+#13#10;

Type
  TFHIRParser = {abstract} class (TFHIRObject)
  private
    FAllowUnknownContent: Boolean;
    Ffeed: TFHIRAtomFeed;

    Fresource: TFhirResource;
    FSource: TStream;
    FLang: String;
    FTags: TFHIRAtomCategoryList;
    FParserPolicy : TFHIRXhtmlParserPolicy;
    procedure Setfeed(const Value: TFHIRAtomFeed);
    procedure SeTFhirResource(const Value: TFhirResource);
  protected
    procedure checkDateFormat(s : string);
    Function toTDateAndTime(s : String) : TDateAndTime;
    function StringArrayToCommaString(Const aNames : Array Of String) : String;
  public
    Constructor Create(lang : String); Virtual;
    Destructor Destroy; Override;
    property source : TStream read FSource write FSource;
    procedure Parse; Virtual; abstract;
    function ParseDT(rootName : String; type_ : TFHIRTypeClass) : TFHIRType; Virtual; abstract;
    property resource : TFhirResource read Fresource write SeTFhirResource;
    property feed : TFHIRAtomFeed read Ffeed write Setfeed;
    Property Tags : TFHIRAtomCategoryList read FTags;

    Property AllowUnknownContent : Boolean read FAllowUnknownContent write FAllowUnknownContent;
    Property Lang : String read FLang write FLang;
    property ParserPolicy : TFHIRXhtmlParserPolicy read FParserPolicy write FParserPolicy;
  end;

  TFHIRParserClass = class of TFHIRParser;

  TFHIRXmlParserBase = class (TFHIRParser)
  Private
    FElement: IXmlDomElement;
    FComments : TAdvStringList;
    Function LoadXml(stream : TStream) : IXmlDomDocument2;
    Function PathForElement(element : IXmlDomNode) : String;
    procedure SeTFhirElement(const Value: IXmlDomElement);

    function CheckHtmlElementOk(elem : IXMLDOMElement) : boolean;
    function CheckHtmlAttributeOk(elem, attr, value: String): boolean;
    function ParseAtomBase(child : IXmlDomElement; base : TFHIRAtomBase; path : String) : boolean;
    function ParseFeed(element : IXmlDomElement) : TFHIRAtomFeed;
    function ParseEntry(element : IXmlDomElement) : TFHIRAtomEntry;
    function ParseDeletedEntry(element : IXmlDomElement) : TFHIRAtomEntry;
    function ParseXHtmlXml(node: IXmlDomNode): TFhirXHtmlNode; overload;
    Procedure ParseTags(element : IXMLDOMElement);
  Protected
    Function GetAttribute(element : IXmlDomElement; const name : String) : String;
    function FirstChild(element : IXmlDomElement) : IXmlDomElement;
    function NextSibling(element : IXmlDomElement) : IXmlDomElement;
    procedure TakeCommentsStart(element : TFHIRBase);
    procedure TakeCommentsEnd(element : TFHIRBase);
    procedure closeOutElement(result : TFhirElement; element : IXmlDomElement);

    Function ParseXHtmlNode(element : IXmlDomElement; path : String) : TFhirXHtmlNode; overload;

    Procedure UnknownContent(element : IXmlDomElement; path : String);

    Procedure XmlError(const sPath, sMessage : String);

    Function ParseContained(element: IXmlDomElement; path : String) : TFhirResource;
    Function ParseResource(element : IXmlDomElement; path : String) : TFhirResource; Virtual;
    function parseBinary(element : IXmlDomElement; path : String) : TFhirBinary;
    Procedure checkOtherAttributes(value : IXmlDomElement; path : String);
    function ParseDataType(element : IXmlDomElement; name : String; type_ : TFHIRTypeClass) : TFHIRType; virtual;
  Public
    procedure Parse; Override;
    function ParseDT(rootName : String; type_ : TFHIRTypeClass) : TFHIRType; Override;
    property Element : IXmlDomElement read FElement write SeTFhirElement;
    Function ParseHtml(element : IXmlDomElement) : TFhirXHtmlNode; Overload;
    Function ParseHtml() : TFhirXHtmlNode; Overload;
    class function ParseFragment(fragment, lang : String) : TFHIRElement; overload;
  End;


  TJsonObjectHandler = procedure (jsn : TJsonObject; ctxt : TFHIRObjectList) of object;
  TJsonObjectPrimitiveHandler = procedure (value : String; jsn : TJsonObject; ctxt : TFHIRObjectList) of object;
  TJsonObjectEnumHandler = procedure (value : String; jsn : TJsonObject; ctxt : TFHIRObjectList; Const aNames : Array Of String) of object;

  TFHIRJsonParserBase = class (TFHIRParser)
  private
    procedure ParseAtomBase(base : TFHIRAtomBase; jsn : TJsonObject);
    procedure ParseTags(jsn : TJsonObject);

  Protected
    Function ParseXHtmlNode(path, value : String) : TFhirXHtmlNode;

    Function ParseResource(jsn : TJsonObject) : TFhirResource; Virtual;
    Function ParseFeed(jsn : TJsonObject) : TFHIRAtomFeed;
    function parseBinary(jsn : TJsonObject) : TFhirBinary;
    procedure ParseComments(base : TFHIRBase; jsn : TJsonObject);
    function ParseDataType(jsn : TJsonObject; name : String; type_ : TFHIRTypeClass) : TFHIRType; virtual;

    procedure iterateArray(arr : TJsonArray; ctxt : TFHIRObjectList; handler : TJsonObjectHandler);
    procedure iteratePrimitiveArray(arr1, arr2 : TJsonArray; ctxt : TFHIRObjectList; handler : TJsonObjectPrimitiveHandler);
    procedure iterateEnumArray(arr1, arr2 : TJsonArray; ctxt : TFHIRObjectList; handler : TJsonObjectEnumHandler; Const aNames : Array Of String);

    // handlers
    procedure ParseEntry(jsn : TJsonObject; ctxt : TFHIRObjectList);
    procedure ParseLink(jsn : TJsonObject; ctxt : TFHIRObjectList);
    procedure ParseCategory(jsn : TJsonObject; ctxt : TFHIRObjectList);
    procedure ParseContained(jsn : TJsonObject; ctxt : TFHIRObjectList);
  Public
    procedure Parse; Override;
    function ParseDT(rootName : String; type_ : TFHIRTypeClass) : TFHIRType; Override;
    class function ParseFragment(fragment, type_, lang : String) : TFHIRElement; overload;
  End;

  TFHIRComposer = {abstract} class (TFHIRObject)
  private
    FLang: String;
    FSummaryOnly: Boolean;
  protected
    Procedure ComposeResource(xml : TXmlBuilder; statedType, id, ver : String; oResource : TFhirResource; links : TFHIRAtomLinkList); overload; virtual;
    Procedure ComposeBinary(xml : TXmlBuilder; binary : TFhirBinary);
    procedure ComposeXHtmlNode(xml : TXmlBuilder; node: TFhirXHtmlNode; ignoreRoot : boolean); overload;
    procedure ComposeXHtmlNode(s : TAdvStringBuilder; node: TFhirXHtmlNode; indent, relativeReferenceAdjustment : integer); overload;
    function ResourceMediaType: String; virtual;

    function asString(value : TDateAndTime):String;
  public
    Constructor Create(lang : String); Virtual;
    Procedure Compose(stream : TStream; statedType, id, ver : String; oResource : TFhirResource; isPretty : Boolean; links : TFHIRAtomLinkList); Overload; Virtual; Abstract;
    Procedure Compose(stream : TStream; oFeed : TFHIRAtomFeed; isPretty : Boolean); Overload; Virtual; Abstract;
    Procedure Compose(stream : TStream; ResourceType : TFhirResourceType; statedType, id, ver : String; oTags : TFHIRAtomCategoryList; isPretty : Boolean); Overload; Virtual; Abstract;

    function Compose(statedType, id, ver : String; oResource : TFhirResource; isPretty : Boolean; links : TFHIRAtomLinkList) : String; Overload;
    function Compose(oFeed : TFHIRAtomFeed; isPretty : Boolean) : String; Overload;

    Function MimeType : String; virtual;
    Property Lang : String read FLang write FLang;
    Property SummaryOnly : Boolean read FSummaryOnly write FSummaryOnly;
  End;

  TFHIRXmlComposerBase = class (TFHIRComposer)
  private
    FComment: String;
    Procedure ComposeAtomBase(xml : TXmlBuilder; base : TFHIRAtomBase);
    Procedure ComposeEntry(xml : TXmlBuilder; entry : TFHIRAtomEntry);
    Procedure ComposeFeed(xml : TXmlBuilder; feed : TFHIRAtomFeed);
  Protected
//    xml : TXmlBuilder;
    procedure commentsStart(xml : TXmlBuilder; value : TFhirBase);
    procedure commentsEnd(xml : TXmlBuilder; value : TFhirBase);
    Procedure Attribute(xml : TXmlBuilder; name, value : String);
    Procedure Text(xml : TXmlBuilder; name, value : String);
    procedure closeOutElement(xml : TXmlBuilder; value : TFhirElement);
    Procedure ComposeContained(xml : TXmlBuilder; name : String; value : TFhirResource);
  Public
    Procedure Compose(stream : TStream; statedType, id, ver : String; oResource : TFhirResource; isPretty : Boolean; links : TFHIRAtomLinkList); Override;
    Procedure Compose(node : IXmlDomNode; statedType, id, ver : String; oResource : TFhirResource; links : TFHIRAtomLinkList); Overload;
    Procedure Compose(stream : TStream; ResourceType : TFhirResourceType; statedType, id, ver : String; oTags : TFHIRAtomCategoryList; isPretty : Boolean); Override;
    Procedure Compose(stream : TStream; oFeed : TFHIRAtomFeed; isPretty : Boolean); Overload; Override;
    Procedure ComposeXHtmlNode(xml : TXmlBuilder; name : String; value : TFhirXHtmlNode); overload;
    Function MimeType : String; Override;
    Property Comment : String read FComment write FComment;
  End;

  TFHIRJsonComposerBase = class (TFHIRComposer)
  private
    FComments : Boolean;
    Procedure ComposeAtomBase(json : TJSONWriter; base : TFHIRAtomBase);
    Procedure ComposeEntry(json : TJSONWriter; entry : TFHIRAtomEntry);
  Protected
    Procedure PropNull(json : TJSONWriter; name : String); overload;
    Procedure Prop(json : TJSONWriter; name, value : String); overload;
    Procedure Prop(json : TJSONWriter; name : String; value : boolean); overload;
    Procedure ComposeXHtmlNode(json : TJSONWriter; name : String; value : TFhirXHtmlNode); overload;

    Procedure composeComments(json : TJSONWriter; base : TFHIRBase);
    procedure composeContained(json : TJSONWriter; oResource : TFhirResource); overload; virtual;
    Procedure ComposeResource(json : TJSONWriter; statedType, id, ver : String; oResource : TFhirResource; links : TFHIRAtomLinkList); overload; virtual;
    Procedure ComposeResource(xml : TXmlBuilder; statedType, id, ver : String; oResource : TFhirResource; links : TFHIRAtomLinkList); overload; override;
    Procedure ComposeBinary(json : TJSONWriter; binary : TFhirBinary);
  Public
    Procedure Compose(stream : TStream; statedType, id, ver : String; oResource : TFhirResource; isPretty : Boolean; links : TFHIRAtomLinkList); Override;
    Procedure Compose(json: TJSONWriter; statedType, id, ver : String; oResource : TFhirResource; links : TFHIRAtomLinkList); Overload;
    Procedure Compose(stream : TStream; oFeed : TFHIRAtomFeed; isPretty : Boolean); Overload; Override;
    Procedure Compose(stream : TStream; ResourceType : TFhirResourceType; statedType, id, ver : String; oTags : TFHIRAtomCategoryList; isPretty : Boolean); Override;
    Function MimeType : String; Override;
    Property Comments : Boolean read FComments write FComments;
  End;

  TFHIRXhtmlComposerGetLink = procedure (resource : TFhirResource; base, statedType, id, ver : String; var link, text : String) of object;

  TFHIRXhtmlComposer = class (TFHIRComposer)
  private
    FBaseURL: String;
    FSession: TFhirSession;
    FTags : TFHIRAtomCategoryList;
    FrelativeReferenceAdjustment: integer;
    FOnGetLink: TFHIRXhtmlComposerGetLink;
    procedure SetSession(const Value: TFhirSession);
    function PresentTags(aType : TFhirResourceType; target : String; tags : TFHIRAtomCategoryList; c : integer):String;
    procedure SetTags(const Value: TFHIRAtomCategoryList);
    function PatchToWeb(url: String): String;
//    xml : TXmlBuilder;
//    procedure ComposeNode(node : TFhirXHtmlNode);
  protected
    function ResourceMediaType: String; override;
  public
    Constructor Create(lang, BaseURL : String); reintroduce; overload;
    Destructor Destroy; override;
    property BaseURL : String read FBaseURL write FBaseURL;
    Property Session : TFhirSession read FSession write SetSession;
    property Tags : TFHIRAtomCategoryList read FTags write SetTags;
    Procedure ComposeResource(xml : TXmlBuilder; statedType, id, ver : String; oResource : TFhirResource; links : TFHIRAtomLinkList); Override;
    Procedure Compose(stream : TStream; statedType, id, ver : String; oResource : TFhirResource; isPretty : Boolean; links : TFHIRAtomLinkList); Override;
    Procedure Compose(stream : TStream; oFeed : TFHIRAtomFeed; isPretty : Boolean); Override;
    Procedure Compose(stream : TStream; ResourceType : TFhirResourceType; statedType, id, ver : String; oTags : TFHIRAtomCategoryList; isPretty : Boolean); Override;
    Function MimeType : String; Override;

    Property relativeReferenceAdjustment : integer read FrelativeReferenceAdjustment write FrelativeReferenceAdjustment;
    Property OnGetLink : TFHIRXhtmlComposerGetLink read FOnGetLink write FOnGetLink;

    class function ResourceLinks(a : TFhirResourceType; lang, base : String; count : integer; bTable, bPrefixLinks : boolean): String;
    class function PageLinks : String;
    class function Header(Session : TFhirSession; base, lang : String) : String;
    class function Footer(base, lang : String; tail : boolean = true) : string;
  end;

Implementation

uses
  RegExpr,
  FHIRParser,
  FHIRUtilities;

Function TFHIRXmlParserBase.LoadXml(stream : TStream) : IXmlDomDocument2;
Var
  iDom : IXMLDomDocument2;
  vAdapter : Variant;
  sError : String;
begin
  // you have to call this elsewhere... CoInitializeEx(nil, COINIT_MULTITHREADED);
  iDom := LoadMsXMLDom;
  iDom.validateOnParse := False;
  iDom.preserveWhiteSpace := True;
  iDom.resolveExternals := False;
  iDom.setProperty('NewParser', True);
  vAdapter := TStreamAdapter.Create(stream) As IStream;
  if not iDom.load(vAdapter) Then
  Begin
    sError := iDom.parseError.reason + ' at line '+IntToStr(iDom.parseError.line)+' char '+IntToStr(iDom.parseError.linepos);
    if iDom.parseError.url <> '' Then
      sError := sError + '. url="'+ iDom.parseError.url+'"';
    sError := sError + '. source = "'+ iDom.parseError.srcText+'"';
    Error('Parse', sError);
  End;
  Result := iDom;
end;

procedure TFHIRXmlParserBase.Parse;
var
  xml : IXmlDomDocument2;
  root : IXmlDomElement;
begin
  FComments := TAdvStringList.create;
  try
    if (Element = nil) then
    begin
      xml := LoadXml(Source);
      root := xml.documenTElement;
    end
    else
      root := element;

    if root.namespaceURI = ATOM_NS Then
      feed := ParseFeed(root)
    else if root.namespaceURI = FHIR_NS Then
    begin
      if SameText(root.nodeName, 'TagList') then
        ParseTags(root)
      else
        resource := ParseResource(root, '')
    end
    else
      XmlError('/', StringFormat(GetFhirMessage('MSG_WRONG_NS', lang), [root.namespaceURI]));
  finally
    FComments.Free;
  end;
end;

function TFHIRXmlParserBase.ParseResource(element: IXmlDomElement; path : String): TFhirResource;
begin
  raise exception.create('don''t use TFHIRXmlParserBase directly - use TFHIRXmlParser');
end;

{ TFHIRJsonParserBase }


procedure TFHIRJsonParserBase.Parse;
var
  obj : TJsonObject;
  s : string;
begin
  obj := TJSONParser.Parse(source);
  try
    s := obj['resourceType'];
    if s = 'Bundle' then
      feed := ParseFeed(obj)
    else if SameText(s, 'TagList') then
      ParseTags(obj)
    else
      resource := ParseResource(obj);
  finally
    obj.Free;
  end;
end;


function TFHIRJsonParserBase.ParseResource(jsn : TJsonObject): TFhirResource;
begin
  raise exception.create('don''t use TFHIRJsonParserBase directly - use TFHIRJsonParser');
end;

function TFHIRXmlParserBase.PathForElement(element: IXmlDomNode): String;
begin
  result := '';
  while element <> nil Do
  Begin
    insert(element.baseName+'/', result, 1);
    element := element.parentNode;
  End;
  result := copy(result, 1, length(result)-1);
end;

procedure TFHIRXmlParserBase.UnknownContent(element: IXmlDomElement; path : String);
begin
  if Not AllowUnknownContent Then
    XmlError(PathForElement(element), StringFormat(GetFhirMessage('MSG_UNKNOWN_CONTENT', lang), [element.tagName, path]));
end;

procedure TFHIRXmlParserBase.XmlError(const sPath, sMessage: String);
begin
  Raise Exception.Create(StringFormat(GetFhirMessage('MSG_ERROR_PARSING', lang), [sMessage+' @ '+sPath]));
end;

function TFHIRJsonParserBase.ParseXHtmlNode(path, value : String): TFhirXHtmlNode;
var
  ss : TStringStream;
  parser : TFHIRXmlParserBase;
begin
  ss := TStringStream.create(value);
  try
    parser := TFHIRXmlParserBase.create(lang);
    try
      parser.source := ss;
      result := parser.ParseHtml;
    finally
      parser.free;
    end;
  finally
    ss.free;
  end;
end;


function TFHIRXmlParserBase.ParseXHtmlXml(node : IXmlDomNode): TFhirXHtmlNode;
var
  child : IXmlDomNode;
  elem : IXmlDomElement;
  res, c: TFhirXHtmlNode;
  i : integer;
begin
  result := nil;
  res := TFhirXHtmlNode.create;
  try
    case node.nodeType of
      NODE_INVALID : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['invalid']));
      NODE_ELEMENT : res.NodeType := fhntElement;
      NODE_ATTRIBUTE : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['cdata']));
      NODE_TEXT : res.NodeType := fhntText;
      NODE_CDATA_SECTION : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['cdata']));
      NODE_ENTITY_REFERENCE : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['entity reference']));
      NODE_ENTITY : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['entity']));
      NODE_PROCESSING_INSTRUCTION : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['processing instruction']));
      NODE_COMMENT : res.NodeType := fhntComment;
      NODE_DOCUMENT : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['document']));
      NODE_DOCUMENT_TYPE : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['type']));
      NODE_DOCUMENT_FRAGMENT : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['doc fragment']));
      NODE_NOTATION : raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['notation']));
    else
      raise exception.create(StringFormat(GetFhirMessage('MSG_UNHANDLED_NODE_TYPE', lang), ['??']));
    end;

    if res.NodeType = fhntElement then
    begin
      elem := node as IXmlDomElement;
      if checkHtmlELementOk(elem) then
      begin
        for i := 0 to elem.attributes.length - 1 Do
          if CheckHtmlAttributeOk(elem.nodeName, elem.attributes.item[i].baseName, elem.attributes.item[i].text) then
            res.Attributes.Add(TFHIRAttribute.create(elem.attributes.item[i].baseName, elem.attributes.item[i].text));
        res.Name := node.baseName;
        child := node.firstChild;
        while (child <> nil) do
        begin
          c := ParseXHtmlXml(child);
          if (c <> nil) then
            res.ChildNodes.add(c);
          child := child.nextSibling;
        end;
        result := res.link;
      end;
    end
    else
    begin
      res.Content := node.text;
      result := res.link;
    end;
  finally
    res.Free;
  end;
end;

function TFHIRXmlParserBase.ParseXHtmlNode(element: IXmlDomElement; path : String): TFhirXHtmlNode;
begin
  if not AllowUnknownContent and (element.namespaceURI <> XHTML_NS) Then
    XmlError(PathForElement(element), StringFormat(GetFhirMessage('MSG_WRONG_NS', lang), [element.namespaceURI]));
  result := ParseXHtmlXml(element);
end;


function TFHIRJsonParserBase.ParseFeed(jsn : TJsonObject): TFHIRAtomFeed;
begin
  result := TFHIRAtomFeed.create;
  try
    ParseAtomBase(result, jsn);
    if (jsn.has('totalResults')) then
      result.SearchTotal := StrToIntDef(jsn.vStr['totalResults'], 0);
    if (jsn.has('entry')) then
      iterateArray(jsn.vArr['entry'], result.entries, ParseEntry);
    result.link;
  finally
    result.free;
  end;
end;

procedure TFHIRJsonParserBase.ParseLink(jsn : TJsonObject; ctxt : TFHIRObjectList);
var
  link_ : TFHIRAtomLink;
begin
  link_ := TFHIRAtomLink.Create;
  try
    ParseComments(link_, jsn);
    if jsn.has('href') then
      link_.url:= jsn['href'];
    if jsn.has('rel') then
      link_.Rel:= jsn['rel'];
    ctxt.add(link_.link);
  finally
    link_.free;
  end;
end;

procedure TFHIRJsonParserBase.ParseCategory(jsn : TJsonObject; ctxt : TFHIRObjectList);
var
  cat : TFHIRAtomCategory;
begin
  cat := TFHIRAtomCategory.Create;
  try
    if jsn.has('term') then
      cat.term:= jsn['term'];
    if jsn.has('scheme') then
      cat.scheme:= jsn['scheme'];
    if jsn.has('label') then
      cat.label_:= jsn['label'];
    ParseComments(cat, jsn);
    ctxt.add(cat.link);
  finally
    cat.free;
  end;
end;


procedure TFHIRJsonParserBase.ParseAtomBase(base : TFHIRAtomBase; jsn : TJsonObject);
var
  aut : TJsonArray;
  i : integer;
begin
  ParseComments(base, jsn);
  if jsn.has('title') then
    base.title:= jsn['title'];
  if jsn.has('updated') then
    base.updated := TDateAndTime.CreateXml(jsn['updated']);
  if jsn.has('published') then
    base.published_ := TDateAndTime.CreateXml(jsn['published']);
  if jsn.has('id') then
    base.id:= jsn['id'];
  if jsn.has('link') then
    iterateArray(jsn.vArr['link'], base.links, ParseLink);
  if jsn.has('author') then
  begin
    aut := jsn.vArr['author'];
    for i := 0 to aut.Count - 1 do
    begin
      if (aut[i] as TJsonObject).has('name') then
        base.authorName := (aut[i] as TJsonObject)['name'];
      if (aut[i] as TJsonObject).has('uri') then
        base.authorUri := (aut[i] as TJsonObject)['uri'];
    end;
  end;
  if jsn.has('category') then
    iterateArray(jsn.vArr['category'], base.categories, ParseCategory);
end;

procedure TFHIRJsonParserBase.ParseEntry(jsn : TJsonObject; ctxt : TFHIRObjectList);
var
  e : TFHIRAtomEntry;
  cnt : TJsonObject;
begin
  e := TFHIRAtomEntry.create;
  try
    parseAtomBase(e, jsn);
    if jsn.has('deleted') then
    begin
      e.updated := TDateAndTime.CreateXml(jsn['deleted']);
      e.deleted := true;
    end;
    if jsn.has('content') then
    begin
      cnt := jsn.vObj['content'];
      if cnt['resourceType'] = 'Binary' then
        e.resource := parseBinary(cnt)
      else
        e.resource := ParseResource(cnt);
    end;
    if jsn.has('summary') then
      e.summary := ParseXHtmlNode('feed.entry.summary', jsn['summary']);

    ctxt.add(e.link);
  finally
    e.free;
  end;
end;


procedure TFHIRJsonParserBase.ParseTags(jsn : TJsonObject);
begin
  if jsn.has('category') then
  begin
    FTags := TFHIRAtomCategoryList.Create;
    iterateArray(jsn.vArr['category'], FTags, ParseCategory);
  end;
end;


function TFHIRJsonParserBase.parseBinary(jsn : TJsonObject): TFhirBinary;
begin
  result := TFhirBinary.create;
  try
    if jsn.has('contentType') then
      result.ContentType:= jsn['contentType'];
    if jsn.has('content') then
      result.content.AsBytes := DecodeBase64(AnsiString(jsn['content']));
    if jsn.has('id') then
      result.xmlId:= jsn['id'];
    result.link;
  finally
    result.free;
  end;
end;

procedure TFHIRJsonParserBase.ParseComments(base: TFHIRBase; jsn : TJsonObject);
begin
  if jsn.has('_xml_comments_start') then
    base.xml_commentsStart.AsText:= jsn['_xml_comments_start'];
  if jsn.has('_xml_comments_end') then
    base.xml_commentsEnd.AsText:= jsn['_xml_comments_end'];
end;

procedure TFHIRJsonParserBase.ParseContained(jsn : TJsonObject; ctxt : TFHIRObjectList);
begin
  ctxt.add(ParseResource(jsn));
end;

function TFHIRJsonParserBase.ParseDataType(jsn : TJsonObject; name : String; type_ : TFHIRTypeClass) : TFHIRType;
begin
  raise exception.create('don''t use TFHIRJsonParserBase directly - use TFHIRJsonParser');
end;

function TFHIRJsonParserBase.ParseDT(rootName: String; type_: TFHIRTypeClass): TFHIRType;
var
  obj : TJsonObject;
  s : string;
begin
  obj := TJSONParser.Parse(source);
  try
    result := ParseDataType(obj, rootName, type_);
  finally
    obj.Free;
  end;
end;

procedure TFHIRJsonParserBase.iterateArray(arr : TJsonArray; ctxt : TFHIRObjectList; handler : TJsonObjectHandler);
var
  i : integer;
begin
  if arr <> nil then
  begin
    for i := 0 to arr.Count - 1 do
      handler(arr.Obj[i], ctxt);
  end;
end;

procedure TFHIRJsonParserBase.iteratePrimitiveArray(arr1, arr2 : TJsonArray; ctxt : TFHIRObjectList; handler : TJsonObjectPrimitiveHandler);
var
  i : integer;
begin
  if (arr1 <> nil) or (arr2 <> nil) then
  begin
    for i := 0 to max(arr1.Count, arr2.Count) - 1 do
      handler(arr1.Value[i], arr2.Obj[i], ctxt);
  end;
end;

procedure TFHIRJsonParserBase.iterateEnumArray(arr1, arr2 : TJsonArray; ctxt : TFHIRObjectList; handler : TJsonObjectEnumHandler; Const aNames : Array Of String);
var
  i : integer;
begin
  if (arr1 <> nil) or (arr2 <> nil) then
  begin
    for i := 0 to max(arr1.Count, arr2.Count) - 1 do
      handler(arr1.Value[i], arr2.Obj[i], ctxt, aNames);
  end;
end;


{ TFHIRXmlComposerBase }

procedure TFHIRXmlComposerBase.Compose(stream: TStream; statedType, id, ver : String; oResource: TFhirResource; isPretty : Boolean; links : TFHIRAtomLinkList);
var
  xml : TXmlBuilder;
begin
  if oResource is TFhirBinary then
    TFhirBinary(oResource).Content.SaveToStream(stream)
  else
  begin
    xml := TAdvXmlBuilder.Create;
    try
      xml.IsPretty := isPretty;
      xml.Namespace := FHIR_NS;
      xml.Start;
      if FComment <> '' then
        xml.Comment(FComment);
      ComposeResource(xml, statedType, id, ver, oResource, links);
      xml.Finish;
      xml.Build(stream);
    finally
      xml.Free;
    end;
  end;
end;


procedure TFHIRXmlComposerBase.Attribute(xml : TXmlBuilder; name, value: String);
begin
  if value <> '' Then
    xml.AddAttribute(name, value);
end;

procedure TFHIRXmlComposerBase.Compose(node: IXmlDomNode; statedType, id, ver : String; oResource: TFhirResource; links : TFHIRAtomLinkList);
var
  xml : TXmlBuilder;
begin
  xml := TMsXmlBuilder.Create;
  try
    TMsXmlBuilder(xml).Start(node);
    xml.Namespace := FHIR_NS;
    if FComment <> '' then
      xml.Comment(FComment);
    ComposeResource(xml, statedType, id, ver, oResource, links);
    xml.Finish;
  finally
    xml.Free;
  end;
end;

procedure TFHIRXmlComposerBase.Text(xml : TXmlBuilder; name, value: String);
begin
  if value <> '' Then
    xml.TagText(name, value);
end;

procedure TFHIRXmlComposerBase.ComposeXHtmlNode(xml : TXmlBuilder; name: String; value: TFhirXHtmlNode);
var
  s : String;
begin
//   attribute('xmlns', XHTML_NS);
  s := xml.Namespace;
  xml.Namespace := XHTML_NS;
  if value <> nil then
    ComposeXhtmlNode(xml, value, false);
  xml.Namespace := s;
end;

function TFHIRXmlComposerBase.MimeType: String;
begin
  result := 'application/xml+fhir; charset=UTF-8';
end;

procedure TFHIRXmlComposerBase.commentsStart(xml: TXmlBuilder; value: TFhirBase);
var
  i : integer;
begin
  if not value.HasXmlCommentsStart then
    exit;

  for i := 0 to value.Xml_commentsStart.count - 1 do
    xml.Comment(value.Xml_commentsStart[i]);
end;

procedure TFHIRXmlComposerBase.commentsEnd(xml: TXmlBuilder; value: TFhirBase);
var
  i : integer;
begin
  if not value.HasXmlCommentsEnd then
    exit;

  for i := 0 to value.Xml_commentsEnd.count - 1 do
    xml.Comment(value.Xml_commentsEnd[i]);
end;

procedure TFHIRXmlComposerBase.closeOutElement(xml: TXmlBuilder; value: TFhirElement);
begin
  commentsEnd(xml, value);
end;

procedure TFHIRXmlComposerBase.ComposeContained(xml: TXmlBuilder; name: String; value: TFhirResource);
begin
  xml.open(name);
  ComposeResource(xml, '', '', '', value, nil);
  xml.close(name);

end;

procedure TFHIRXmlComposerBase.Compose(stream: TStream; ResourceType : TFhirResourceType; statedType, id, ver : String; oTags: TFHIRAtomCategoryList; isPretty: Boolean);
var
  xml : TXmlBuilder;
  i : integer;
begin
  xml := TAdvXmlBuilder.Create;
  try
    xml.IsPretty := isPretty;
    xml.Namespace := FHIR_NS;
    xml.Start;
    if FComment <> '' then
      xml.Comment(FComment);
    xml.Open('TagList');
    for i := 0 to oTags.Count - 1 do
    begin
      xml.AddAttribute('scheme', oTags[i].scheme);
      xml.AddAttribute('term', oTags[i].term);
      if oTags[i].label_ <> '' then
        xml.AddAttribute('label', oTags[i].label_);
      xml.Tag('category');
    end;

    xml.Close('TagList');
    xml.Finish;
    xml.Build(stream);
  finally
    xml.Free;
  end;
end;

{ TFHIRJsonComposerBase }


procedure TFHIRJsonComposerBase.Compose(stream: TStream; statedType, id, ver : String; oResource: TFhirResource; isPretty : Boolean; links : TFHIRAtomLinkList);
var
  oStream : TAdvVCLStream;
  json : TJSONWriter;
begin
  json := TJSONWriter.Create;
  try
    oStream := TAdvVCLStream.Create;
    json.Stream := oStream;
    oStream.Stream := stream;
    json.Start;
    if oResource is TFhirBinary then
      raise exception.create('not done yet')
    else
    begin
      json.HasWhitespace := isPretty;
      ComposeResource(json, statedType, id, ver, oResource, links);
    end;
    json.Finish;
  finally
    json.free;
  end;
end;

procedure TFHIRJsonComposerBase.Prop(json : TJSONWriter; name, value: String);
begin
  if value <> '' Then
    json.Value(name, value);
end;

procedure TFHIRJsonComposerBase.Compose(json : TJSONWriter; statedType, id, ver : String; oResource: TFhirResource; links : TFHIRAtomLinkList);
begin
  json := json.Link;
  ComposeResource(json, statedType, id, ver, oResource, links);
end;

procedure TFHIRJsonComposerBase.ComposeResource(json : TJSONWriter; statedType, id, ver : String; oResource: TFhirResource; links : TFHIRAtomLinkList);
begin
  raise exception.create('don''t use TFHIRJsonComposerBase directly - use TFHIRJsonComposer');
end;

Procedure TFHIRJsonComposerBase.ComposeResource(xml : TXmlBuilder; statedType, id, ver : String; oResource : TFhirResource; links : TFHIRAtomLinkList);
var
  s : TStringStream;
begin
  s := TStringStream.Create('');
  try
    compose(s, statedType, id, ver, oResource, false, links);
    xml.Text(s.DataString);
  finally
    s.free;
  end;
end;

procedure TFHIRJsonComposerBase.ComposeXHtmlNode(json : TJSONWriter; name: String; value: TFhirXHtmlNode);
var
  s : TStringStream;
  xml : TXmlBuilder;
begin
  s := TStringStream.Create('');
  try
    xml := TAdvXmlBuilder.Create;
    try
      xml.IsPretty := false;
      xml.CharEncoding := '';
      xml.Namespace := XHTML_NS;
      xml.NoHeader := true;
      {
      xml.StartFragment;
      ComposeXHtmlNode(xml, value, true);
      }
      xml.Start;
      ComposeXHtmlNode(xml, value, false);

      xml.Finish;
      xml.Build(s);
    finally
      xml.Free;
    end;
    json.value(name, s.DataString);
  finally
    s.free;
  end;
end;


function TFHIRJsonComposerBase.MimeType: String;
begin
 result := 'application/json+fhir; charset=UTF-8';
end;


procedure TFHIRJsonComposerBase.Compose(stream: TStream; oFeed: TFHIRAtomFeed; isPretty: Boolean);
var
  oStream : TAdvVCLStream;
  json : TJSONWriter;
  i : integer;
begin
  json := TJSONWriter.Create;
  try
    oStream := TAdvVCLStream.Create;
    json.Stream := oStream;
    oStream.Stream := stream;
//    json.IsPretty := isPretty;
    json.Start;
    json.value('resourceType', 'Bundle');
    ComposeAtomBase(json, oFeed);
    if oFeed.isSearch and ((oFeed.SearchTotal > 0) or (oFeed.entries.Count = 0)) then
      Prop(json, 'totalResults', inttostr(oFeed.SearchTotal));
    json.ValueArray('entry');
    for i := 0 to oFeed.entries.count - 1 Do
      ComposeEntry(json, oFeed.entries[i]);
    json.FinishArray;
//    json.finishObject;
    json.Finish;
  finally
    json.free;
  end;
end;

function tail(s : String):String;
begin
  result := copy(s, LastDelimiter('/', s)+1, $FF);
end;


procedure TFHIRJsonComposerBase.ComposeAtomBase(json: TJSONWriter; base : TFHIRAtomBase);
var
  i : integer;
  started : boolean;
begin
  prop(json, 'title', base.title);
  prop(json, 'id', base.id);
  started := false;
  for i := 0 to base.links.Count - 1 do
    if not base.links.GetItemN(i).Rel.StartsWith('z-') then
    begin
      if not started then
      begin
        started := true;
        json.ValueArray('link');
      end;
      json.ValueObject;
      composeComments(json, base);
      Prop(json, 'href', base.links.GetItemN(i).URL);
      Prop(json, 'rel', base.links.GetItemN(i).Rel);
      json.FinishObject;
    end;
  if started then
    json.FinishArray;

  if base.updated <> nil then
    prop(json, 'updated', base.updated.AsXML);

  if (base.authorUri <> '') or (base.authorName <> '') then
  begin
    json.ValueArray('author');
    json.ValueObject;
    if (base.authorName <> '') then
      prop(json, 'name', base.authorName);
    if (base.authorUri <> '') then
      prop(json, 'uri', base.authorUri);
    json.FinishObject;
    json.FinishArray;
  end;

  if base.categories.count > 0 then
  begin
    json.ValueArray('category');
    for i := 0 to base.categories.Count - 1 do
    begin
      json.ValueObject;
      composeComments(json, base);
      Prop(json, 'scheme', base.categories.GetItemN(i).scheme);
      Prop(json, 'term', base.categories.GetItemN(i).term);
      if base.categories.GetItemN(i).label_ <> '' then
        Prop(json, 'label', base.categories.GetItemN(i).label_);
      json.FinishObject;
    end;
    json.FinishArray;
  end;
end;

procedure TFHIRJsonComposerBase.ComposeEntry(json: TJSONWriter; entry: TFHIRAtomEntry);
var
  i : integer;
  started : boolean;
begin
  json.ValueObject();
  composeComments(json, entry);
  if (entry.deleted) then
  begin
    if entry.updated <> nil then
      prop(json, 'deleted', entry.updated.AsXML);
    prop(json, 'id', entry.id);
    started := false;
    for i := 0 to entry.links.Count - 1 do
      if not entry.links.GetItemN(i).Rel.StartsWith('z-') then
      begin
        if not started then
        begin
          started := true;
          json.ValueArray('link');
        end;
        json.ValueObject;
        composeComments(json, entry);
        Prop(json, 'href', entry.links.GetItemN(i).URL);
        Prop(json, 'rel', entry.links.GetItemN(i).Rel);
        json.FinishObject;
      end;
    if started then
      json.FinishArray;

    if (entry.authorUri <> '') or (entry.authorName <> '') then
    begin
      json.ValueArray('author');
      json.ValueObject;
      if (entry.authorName <> '') then
        prop(json, 'name', entry.authorName);
      if (entry.authorUri <> '') then
        prop(json, 'uri', entry.authorUri);
      json.FinishObject;
      json.FinishArray;
    end;
  end
  else
  begin
    ComposeAtomBase(json, entry);
    if (entry.published_ <> nil) Then
      prop(json, 'published', entry.published_.AsXML);

    if entry.resource <> nil then
    begin
      json.ValueObject('content');
      if entry.resource is TFhirBinary then
        ComposeBinary(json, TFhirBinary(entry.resource))
      else
        ComposeResource(json, '', entry.id, tail(entry.links.rel['self']), entry.resource, entry.links);
      json.FinishObject;
    end;

    if entry.summary <> nil then
      ComposeXHtmlNode(json, 'summary', entry.summary);
  end;
  json.FinishObject;
end;


procedure TFHIRJsonComposerBase.ComposeBinary(json: TJSONWriter; binary: TFhirBinary);
begin
  Prop(json, 'id', binary.xmlId);
  Prop(json, 'contentType', binary.ContentType);
  Prop(json, 'content', StringReplace(string(EncodeBase64(binary.Content.Data, binary.Content.Size)), #13#10, ''));
end;

procedure TFHIRJsonComposerBase.composeContained(json: TJSONWriter; oResource: TFhirResource);
begin
  json.ValueObject('');
  ComposeResource(json, '', '', '', oResource, nil);
  json.FinishObject;
end;

procedure TFHIRJsonComposerBase.composeComments(json: TJSONWriter; base: TFHIRBase);
begin
  if not FComments then
    exit;

  if base.HasXmlCommentsStart then
    json.Value('_xml_comments_start', base.xml_commentsStart.AsText);
  if base.HasXmlCommentsEnd then
    json.Value('_xml_comments_end', base.xml_commentsEnd.AsText);
end;

procedure TFHIRJsonComposerBase.Compose(stream: TStream; ResourceType : TFhirResourceType; statedType, id, ver : String; oTags: TFHIRAtomCategoryList; isPretty: Boolean);
var
  oStream : TAdvVCLStream;
  json : TJSONWriter;
  i : integer;
begin
  json := TJSONWriter.Create;
  try
    oStream := TAdvVCLStream.Create;
    json.Stream := oStream;
    oStream.Stream := stream;
    json.Start;
    json.Value('resourceType', 'TagList');
    json.ValueArray('category');
    for i := 0 to oTags.Count - 1 do
    begin
      json.ValueObject;
      Prop(json, 'scheme', oTags[i].scheme);
      Prop(json, 'term', oTags[i].term);
      if oTags[i].label_ <> '' then
        Prop(json, 'label', oTags[i].label_);
      json.FinishObject;
    end;
    json.FinishArray;
    json.Finish;
  finally
    json.free;
  end;
end;

procedure TFHIRJsonComposerBase.Prop(json: TJSONWriter; name: String; value: boolean);
begin
  json.Value(name, value);
end;

procedure TFHIRJsonComposerBase.PropNull(json: TJSONWriter; name: String);
begin
  json.ValueNull(name);
end;

{ TFHIRParser }

procedure TFHIRParser.checkDateFormat(s: string);
var
  ok : boolean;
begin
  ok := false;
  if (length(s) = 4) and StringIsCardinal16(s) then
    ok := true
  else if (length(s) = 7) and (s[5] = '-') and
          StringIsCardinal16(copy(s, 1, 4)) and StringIsCardinal16(copy(s, 5, 2)) then
    ok := true
  else if (length(s) = 10) and (s[5] = '-') and (s[8] = '-') and
          StringIsCardinal16(copy(s, 1, 4)) and StringIsCardinal16(copy(s, 6, 2)) and StringIsCardinal16(copy(s, 9, 2)) then
    ok := true
  else if (length(s) > 11) and (s[5] = '-') and (s[8] = '-') and (s[11] = 'T') and
          StringIsCardinal16(copy(s, 1, 4)) and StringIsCardinal16(copy(s, 6, 2)) and StringIsCardinal16(copy(s, 9, 2)) then
  begin
    if (length(s) = 16) and (s[14] = '-') and StringIsCardinal16(copy(s, 12, 2)) and StringIsCardinal16(copy(s, 15, 2)) then
      ok := true
    else if (length(s) = 19) and (s[14] = '-') and (s[17] = '-') and
          StringIsCardinal16(copy(s, 12, 2)) and StringIsCardinal16(copy(s, 15, 2)) and StringIsCardinal16(copy(s, 18, 2)) then
      ok := true;
  end;
  if not ok then
    raise exception.create('The Date value '+s+' is not in the correct format (Xml Date Format required)');
end;

constructor TFHIRParser.Create(lang: String);
begin
  Inherited Create;
  FLang := lang;
end;

destructor TFHIRParser.Destroy;
begin
  FTags.Free;
  Ffeed.Free;
  Fresource.Free;
  inherited;
end;

class function TFHIRJsonParserBase.ParseFragment(fragment, type_, lang: String): TFHIRElement;
var
  ss : TStringStream;
  p : TFHIRJsonParser;
  jsn : TJsonObject;
begin
  ss := TStringStream.Create(fragment, TEncoding.UTF8);
  try
    jsn := TJSONParser.Parse(ss);
    try
      p := TFHIRJsonParser.Create(lang);
      try
        result := p.ParseFragment(jsn, type_);
      finally
        p.Free;
      end;
    finally
      jsn.Free;
    end;
  finally
    ss.Free;
  end;
end;

class function TFHIRXmlParserBase.ParseFragment(fragment, lang: String): TFHIRElement;
var
  ss : TStringStream;
  p : TFHIRXmlParser;
  xml : IXMLDOMElement;
begin
  result := nil;
  ss := TStringStream.Create(fragment, TEncoding.UTF8);
  try
    p := TFHIRXmlParser.Create(lang);
    try
      p.source := ss;
      xml := p.LoadXml(ss).documentElement;
      if xml.namespaceURI <> FHIR_NS Then
        raise Exception.Create('Unknown namespace');
      result := p.ParseFragment(xml);
    finally
      p.free;
    end;
  finally
    ss.Free;
  end;

end;

procedure TFHIRParser.Setfeed(const Value: TFHIRAtomFeed);
begin
  Ffeed.Free;
  Ffeed := Value;
end;

procedure TFHIRParser.SeTFhirResource(const Value: TFhirResource);
begin
  Fresource.Free;
  Fresource := Value;
end;

procedure TFHIRXmlParserBase.SeTFhirElement(const Value: IXmlDomElement);
begin
  FElement := Value;
end;

function TFHIRXmlParserBase.ParseFeed(element : IXmlDomElement): TFHIRAtomFeed;
var
  child : IXMLDOMElement;
begin
  if element.baseName <> 'feed' then
    Raise Exception.create(StringFormat(GetFhirMessage('MSG_CANT_PARSE_ROOT', lang), [element.baseName]));

  result := TFHIRAtomFeed.create;
  try
    TakeCommentsStart(result);
    child := FirstChild(element);
    while (child <> nil) do
    begin
      if (child.baseName = 'totalResults') then
        result.SearchTotal := StrToIntDef(child.text, 0)
      else if (child.baseName = 'entry') then
        result.entries.Add(ParseEntry(child))
      else if (child.baseName = 'deleted-entry') then
        result.entries.add(ParseDeletedEntry(child))
      else if not ParseAtomBase(child, result, 'feed') then
         UnknownContent(child, '/feed');
      child := NextSibling(child);
    end;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRXmlParserBase.ParseEntry(element: IXmlDomElement): TFHIRAtomEntry;
var
  child : IXMLDOMElement;
  grandchild : IXMLDOMElement;
  s : String;
begin
  result := TFHIRAtomEntry.create;
  try
    TakeCommentsStart(result);
    child := FirstChild(element);
    while (child <> nil) do
    begin
      if (child.baseName = 'content') then
      begin
        s := TMsXmlParser.GetAttribute(child, 'type');
        if (s = 'text/xml') or (s = '') or (s = 'xml') then
          result.Resource := ParseResource(FirstChild(child), '/feed/entry/content')
        else
          raise exception.create(StringFormat(GetFhirMessage('MSG_CANT_PARSE_CONTENT', lang), [child.getAttribute('type')]));
      end
      else if (child.baseName = 'summary') then
      begin
        if (FirstChild(child) <> nil) then
          result.summary := ParseXHtmlNode(FirstChild(child), '/feed/entry/summary')
      end
      else if not ParseAtomBase(child, result, 'feed.entry') then
         UnknownContent(child, 'feed.entry');
      child := NextSibling(child);
    end;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRXmlParserBase.ParseAtomBase(child: IXmlDomElement; base : TFHIRAtomBase; path : string) : boolean;
var
  grandchild : IXMLDOMElement;
  link : TFHIRAtomLink;
  cat : TFHIRAtomCategory;
begin
  result := true;
  if (child.baseName = 'title') then
    base.title := child.text
  else if (child.baseName = 'link') then
  begin
    link := TFHIRAtomLink.Create;
    try
      TakeCommentsStart(link);
      link.URL := TMsXmlParser.GetAttribute(child, 'href');
      link.Rel := TMsXmlParser.GetAttribute(child, 'rel');
      base.Links.add(link.Link);
    finally
      link.Free;
    end;
  end
  else if (child.baseName = 'id') then
    base.id := child.text
  else if (child.baseName = 'updated') then
    base.updated := TDateAndTime.createXml(child.text)
  else if (child.baseName = 'published') then
    base.published_ := TDateAndTime.createXml(child.text)
  else if (child.baseName = 'category') then
  begin
    cat := TFHIRAtomCategory.create;
    try
      TakeCommentsStart(cat);
      cat.scheme := TMsXmlParser.GetAttribute(child, 'scheme');
      cat.term :=  TMsXmlParser.GetAttribute(child, 'term');
      cat.label_ :=  TMsXmlParser.GetAttribute(child, 'label');
      base.categories.Add(cat.link);
    finally
      cat.Free;
    end;
  end
  else if (child.baseName = 'author') then
  begin
    grandChild := FirstChild(child);
    while (grandchild <> nil) do
    begin
      TakeCommentsStart(base);
      if (grandchild.baseName = 'name') then
        base.authorName := grandchild.text
      else if (grandchild.baseName = 'uri') then
        base.authorUri := grandchild.text
      else
         UnknownContent(grandchild, path+'/author');
      grandchild := NextSibling(grandchild);
    end;
  end
  else
     result := false;
  TakeCommentsStart(base);
end;

function TFHIRXmlParserBase.ParseDataType(element: IXmlDomElement; name: String; type_: TFHIRTypeClass): TFHIRType;
begin
  raise exception.create('don''t use TFHIRXmlParserBase directly - use TFHIRXmlParser');
end;

function TFHIRXmlParserBase.ParseDeletedEntry(element: IXmlDomElement): TFHIRAtomEntry;
var
  child : IXMLDOMElement;
  grandchild : IXMLDOMElement;
begin
  result := TFHIRAtomEntry.create;
  try
    TakeCommentsStart(result);
    result.deleted := true;
    result.id := TMsXmlParser.GetAttribute(element, 'ref');
    result.updated := TDateAndTime.createXml(TMsXmlParser.GetAttribute(element, 'when'));
    child := FirstChild(element);
    while (child <> nil) do
    begin
      if (child.baseName = 'link') then
        result.Links.AddValue(TMsXmlParser.GetAttribute(child, 'href'), TMsXmlParser.GetAttribute(child, 'rel'))
      else if (child.baseName = 'by') then
      begin
        grandChild := FirstChild(child);
        while (grandchild <> nil) do
        begin
          if (grandchild.baseName = 'name') then
            result.authorName := grandchild.text
          else if (grandchild.baseName = 'uri') then
            result.authorUri := grandchild.text
          else
             UnknownContent(grandchild, '/feed/deleted-entry/by');
          grandchild := NextSibling(grandchild);
        end;
      end
      else
         UnknownContent(child, '/feed/entry');
      child := NextSibling(child);
    end;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRXmlParserBase.ParseDT(rootName: String; type_: TFHIRTypeClass): TFHIRType;
var
  xml : IXmlDomDocument2;
  root : IXmlDomElement;
begin
  FComments := TAdvStringList.create;
  try
    if (Element = nil) then
    begin
      xml := LoadXml(Source);
      root := xml.documenTElement;
    end
    else
      root := element;

    if root.namespaceURI <> FHIR_NS Then
      XmlError('/', StringFormat(GetFhirMessage('MSG_WRONG_NS', lang), [root.namespaceURI]));

    result := ParseDataType(root, rootName, type_);
  finally
    FComments.Free;
  end;
end;

function TFHIRXmlParserBase.ParseHtml(element: IXmlDomElement): TFhirXHtmlNode;
begin
  result := ParseXHtmlXml(element);
end;

function TFHIRXmlParserBase.ParseHtml(): TFhirXHtmlNode;
begin
  result := ParseHtml(LoadXml(Source).documentElement);
end;


{atom }

procedure TFHIRComposer.ComposeResource(xml : TXmlBuilder; statedType, id, ver : String; oResource: TFhirResource; links : TFHIRAtomLinkList);
begin
  raise exception.create('don''t use TFHIRXmlComposerBase directly - use TFHIRXmlComposer');
end;

procedure TFHIRXmlComposerBase.Compose(stream: TStream; oFeed: TFHIRAtomFeed; isPretty: Boolean);
var
  xml : TXmlBuilder;
begin
  xml := TAdvXmlBuilder.Create;
  try
    xml.IsPretty := isPretty;
    xml.Namespace := ATOM_NS;
    xml.Start;
    if FComment <> '' then
      xml.Comment(FComment);
    ComposeFeed(xml, oFeed);
    xml.Finish;
    xml.Build(stream);
  finally
    xml.free;
  end;
end;

procedure TFHIRXmlComposerBase.ComposeAtomBase(xml : TXmlBuilder; base : TFHIRAtomBase);
var
  i : integer;
begin
  commentsStart(xml, base);
  xml.TagText('title', base.title);
  xml.TagText('id', base.id);
  for i := 0 to base.links.count - 1 do
    if not base.links.GetItemN(i).Rel.StartsWith('z-') then
    begin
      xml.AddAttribute('href', base.links.GetItemN(i).URL);
      xml.AddAttribute('rel', base.links.GetItemN(i).Rel);
      xml.Tag('link');
    end;
  if base.updated <> nil then
    xml.TagText('updated', base.updated.AsXML);
  if (base.authorUri <> '') or (base.authorName <> '') then
  begin
    xml.Open('author');
    if base.authorName <> '' then
      xml.TagText('name', base.authorName);
    if base.authorUri <> '' then
      xml.TagText('uri', base.authorUri);
    xml.Close('author');
  end;
  for i := 0 to base.categories.Count - 1 do
  begin
    xml.AddAttribute('scheme', base.categories.GetItemN(i).scheme);
    xml.AddAttribute('term', base.categories.GetItemN(i).term);
    if base.categories.GetItemN(i).label_ <> '' then
      xml.AddAttribute('label', base.categories.GetItemN(i).label_);
    xml.Tag('category');
  end;
end;

procedure TFHIRXmlComposerBase.ComposeFeed(xml : TXmlBuilder; feed: TFHIRAtomFeed);
var
  i : integer;
begin
  xml.Open('feed');
  ComposeAtomBase(xml, feed);

  if (feed.isSearch) and ((feed.SearchTotal > 0) or (feed.entries.Count = 0)) then
  begin
    xml.Namespace := 'http://a9.com/-/spec/opensearch/1.1/';
    xml.TagText('totalResults', inttostr(feed.SearchTotal));
    xml.Namespace := ATOM_NS;
  end;

  for i := 0 to feed.entries.count - 1 Do
    ComposeEntry(xml, feed.entries[i]);
  commentsEnd(xml, feed);
  xml.Close('feed');
end;

procedure TFHIRXmlComposerBase.ComposeEntry(xml : TXmlBuilder; entry: TFHIRAtomEntry);
var
  i : integer;
begin
  if entry.deleted then
  begin
    xml.Namespace := 'http://purl.org/atompub/tombstones/1.0';
    xml.AddAttribute('ref', entry.id);
    xml.AddAttribute('when', entry.updated.AsXml);
    xml.Open('deleted-entry');
    for i := 0 to entry.links.count - 1 do
      if not entry.links.GetItemN(i).Rel.StartsWith('z-') then
      begin
        xml.AddAttribute('href', entry.links.GetItemN(i).URL);
        xml.AddAttribute('rel', entry.links.GetItemN(i).Rel);
        xml.Tag('link');
      end;
    if (entry.authorUri <> '') or (entry.authorName <> '') then
    begin
      xml.Open('by');
      if entry.authorName <> '' then
        xml.TagText('name', entry.authorName);
      if entry.authorUri <> '' then
        xml.TagText('uri', entry.authorUri);
      xml.Close('by');
    end;
    xml.Close('deleted-entry');
    xml.Namespace := ATOM_NS;
  end
  else
  begin
    if xml.Namespace <> ATOM_NS  then
      xml.Namespace := ATOM_NS;
    xml.Open('entry');
    composeAtomBase(xml, entry);
    if (entry.published_ <> nil) Then
      xml.TagText('published', entry.published_.AsXML);
    if entry.resource <> nil then
    begin
//      xml.AddAttribute('type', 'application/xml+fhir');
      xml.AddAttribute('type', 'text/xml');
      xml.Open('content');
      xml.Namespace := FHIR_NS;
      if entry.resource is TFhirBinary then
        ComposeBinary(xml, TFhirBinary(entry.resource))
      else
        ComposeResource(xml, '', entry.id, tail(entry.links.rel['self']), entry.resource, entry.Links);
      xml.Namespace := ATOM_NS;
      xml.Close('content');
    end;
    if entry.summary <> nil then
    begin
      xml.AddAttribute('type', 'xhtml');
      xml.Open('summary');
      xml.Namespace := XHTML_NS;
      ComposeXHtmlNode(xml, entry.summary, false);
      xml.Namespace := ATOM_NS;
      xml.Close('summary');
    end;
    commentsEnd(xml, entry);
    xml.Close('entry');
  end;
end;

procedure TFHIRComposer.ComposeXHtmlNode(xml : TXmlBuilder; node: TFhirXHtmlNode; ignoreRoot : boolean);
var
  i : Integer;
begin
  if node = nil then
    exit;
  If ignoreRoot then
  Begin
    if node.NodeType in [fhntElement, fhntDocument] then
        for i := 0 to node.ChildNodes.count - 1 do
          ComposeXHtmlNode(xml, node.ChildNodes[i], false);
  End
  else
  begin
    case node.NodeType of
      fhntText : xml.Text(node.Content);
      fhntComment : xml.Comment(node.Content);
      fhntElement :
        begin
        for i := 0 to node.Attributes.count - 1 do
          xml.AddAttribute(node.Attributes[i].Name, node.Attributes[i].Value);
        xml.Open(node.name);
        for i := 0 to node.ChildNodes.count - 1 do
          ComposeXHtmlNode(xml, node.ChildNodes[i], false);
        xml.Close(node.Name);
        end;
      fhntDocument:
        for i := 0 to node.ChildNodes.count - 1 do
          ComposeXHtmlNode(xml, node.ChildNodes[i], false);
    else
      raise exception.create('not supported');
    end;
  End;
end;




function TFHIRComposer.MimeType: String;
begin
  result := '??';
end;

{ TFHIRXhtmlComposer }

procedure TFHIRXhtmlComposer.Compose(stream: TStream; statedType, id, ver : String; oResource: TFhirResource; isPretty: Boolean; links : TFHIRAtomLinkList);
var
  s : TAdvStringBuilder;
  ss : TStringStream;
  xml : TFHIRXmlComposer;
  c : integer;
  title : String;
  link, text : String;
begin
  if (id = '') and (ver = '') then
    title := FormatTextToXml(GetFhirMessage(CODES_TFhirResourceType[oResource.resourceType], lang))
  else if (ver = '') then
    title := FormatTextToXml(GetFhirMessage('NAME_RESOURCE', lang)+' "'+id + '" ('+CODES_TFhirResourceType[oResource.ResourceType]+') ')
  else
    title := FormatTextToXml(GetFhirMessage('NAME_RESOURCE', lang)+' "'+id+'" '+GetFhirMessage('NAME_VERSION', lang)+' "'+ver + '" ('+CODES_TFhirResourceType[oResource.ResourceType]+') ');

  c := 0;
  s := TAdvStringBuilder.create;
  try
    s.append(
'<?xml version="1.0" encoding="UTF-8"?>'+#13#10+
'<!DOCTYPE HTML'+#13#10+
'       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">'+#13#10+
''+#13#10+
'<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">'+#13#10+
'<head>'+#13#10+
'    <title>'+title+'</title>'+#13#10+
PageLinks+
FHIR_JS+
'</head>'+#13#10+
''+#13#10+
'<body>'+#13#10+
''+#13#10+
Header(Session, FBaseURL, lang)+
'<h2>'+title+'</h2>'+#13#10);

    if oResource is TFhirBinary then
    begin
      if StringStartsWith(TFhirBinary(oResource).ContentType, 'image/') then
        s.append('<img src="'+CODES_TFhirResourceType[oResource.ResourceType]+'/'+id+'">'+#13#10)
      else
        s.append('<pre class="xml">'+#13#10+'('+GetFhirMessage('NAME_BINARY', lang)+')'+#13#10+'</pre>'+#13#10);
    end
    else
    begin
      inc(c);
      if assigned(FTags) then
        if ver <> '' then
          s.append('<p><a href="./_tags">'+GetFhirMessage('NAME_TAGS', lang)+'</a>: '+PresentTags(oResource.resourceType, FBaseURL+CODES_TFhirResourceType[oResource.ResourceType]+'/'+id+'/_history/'+ver+'/_tags', Ftags, c)+'</p>'+#13#10)
        else if id <> '' then
          s.append('<p><a href="./_tags">'+GetFhirMessage('NAME_TAGS', lang)+'</a>: '+PresentTags(oResource.resourceType, FBaseURL+CODES_TFhirResourceType[oResource.ResourceType]+'/'+id+'/_tags', Ftags, c)+'</p>'+#13#10);
      if id <> '' then
      begin
        if assigned(FOnGetLink) then
          FOnGetLink(oResource, BaseURL, statedType, id, ver, link, text)
        else
          link := '';
        if link <> '' then
          s.append('<p><a href="?_format=xml">XML</a> or <a href="?_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang)+'. <a href="'+link+'">'+FormatTextToHTML(text)+'</a>'+#13#10)
        else
          s.append('<p><a href="?_format=xml">XML</a> or <a href="?_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang)+#13#10);

        if (links <> nil) and (links.GetRel('z-edit-src') <> '') then
          s.append('. Edit this as <a href="'+patchToWeb(links.GetRel('z-edit-src'))+'?srcformat=xml">XML</a> or <a href="'+patchToWeb(links.GetRel('z-edit-src'))+'?srcformat=json">JSON</a>');
        {$IFNDEF FHIR-DSTU}
        if (links <> nil) and (links.GetRel('edit-form') <> '') then
          if (oResource is TFHIRQuestionnaireAnswers) then
          begin
            if (TFHIRQuestionnaireAnswers(oResource).questionnaire <> nil) then
              s.append('. <a href="'+patchToWeb(links.GetRel('edit-form'))+'">Edit this Resource</a> (or <a href="'+TFHIRQuestionnaireAnswers(oResource).questionnaire.referenceST+'">see the questionnaire</a>)')
          end
          else
            s.append('. <a href="'+patchToWeb(links.GetRel('edit-form'))+'">Edit this Resource</a> (or <a href="'+links.GetRel('edit-form')+'">see resources underlying that</a>)');
        if (links <> nil) and (links.GetRel('edit-post') <> '') then
          s.append('. Submit edited content by POST to '+links.GetRel('edit-post'));
        {$ENDIF}
        s.append('</p>'#13#10);
      end;

      if oResource.text <> nil then
        ComposeXHtmlNode(s, oResource.text.div_, 0, relativeReferenceAdjustment);
      s.append('<hr/>'+#13#10);
      xml := TFHIRXmlComposer.create(lang);
      ss := TStringStream.create('');
      try
        xml.Compose(ss, statedType, id, ver, oResource, true, links);
        s.append('<pre class="xml">'+#13#10+FormatXMLToHTML(ss.dataString)+#13#10+'</pre>'+#13#10);
      finally
        ss.free;
        xml.free;
      end;
    end;
    s.append(
'<p><br/>'+
Footer(FBaseURL, lang)
    );
    s.WriteToStream(stream);
  finally
    s.free;
  end;
end;

function Author(e : TFHIRAtomEntry; default : String) : String;
begin
  if e.authorUri <> '' then
    result := e.authorUri
  else if e.authorName <> '' then
    result := e.authorName
  else if default <> '' then
    result := default
  else
    result := '(unknown)';
end;

function TFHIRXhtmlComposer.PatchToWeb(url : String) : String;
begin
  result := FBaseURL+'_web/'+url.substring(FBaseURL.length);
end;

procedure TFHIRXhtmlComposer.Compose(stream: TStream; oFeed: TFHIRAtomFeed; isPretty: Boolean);
var
  s : TAdvStringBuilder;
  i : integer;
  a : string;
  e : TFHIRAtomEntry;
  ss : TStringStream;
  xml : TFHIRXmlComposer;
  link, text : String;
  u : string;
begin
  a := oFeed.authorUri;
  s := TAdvStringBuilder.create;
  try
    s.append(
'<?xml version="1.0" encoding="UTF-8"?>'+#13#10+
'<!DOCTYPE HTML'+#13#10+
'       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">'+#13#10+
''+#13#10+
'<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">'+#13#10+
'<head>'+#13#10+
'    <title>'+FormatTextToXml(oFeed.title)+'</title>'+#13#10+
PageLinks+
FHIR_JS+#13#10+
'</head>'+#13#10+
''+#13#10+
'<body>'+#13#10+
''+#13#10+
Header(Session, FBaseURL, lang)+
'<h1>'+FormatTextToXml(oFeed.title)+'</h1>'+#13#10);

  u := ofeed.links.getrel('self');
  if not u.contains('?') then
    u := u + '?'
  else
    u := u + '&';
  s.append('<p><a href="'+u+'_format=xml"><img src="/rss.png"> Atom (XML)</a> '+GetFhirMessage('OR', lang)+' <a href="'+u+'_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang)+'</p>'+#13#10);

    if (ofeed.isSearch) then
    begin
      s.append('<p>'+GetFhirMessage('NAME_LINKS', lang)+':&nbsp;');
      if (ofeed.links.getrel('first') <> '') then
        s.append('<a href="'+ofeed.links.getrel('first')+'">'+GetFhirMessage('NAME_FIRST', lang)+'</a>&nbsp;')
      else
        s.append('<span style="color: grey">'+GetFhirMessage('NAME_FIRST', lang)+'</span>&nbsp;');
      if (ofeed.links.getrel('previous') <> '') then
        s.append('<a href="'+ofeed.links.getrel('previous')+'">'+GetFhirMessage('NAME_PREVIOUS', lang)+'</a>&nbsp;')
      else
        s.append('<span style="color: grey">'+GetFhirMessage('NAME_PREVIOUS', lang)+'</span>&nbsp;');
      if (ofeed.links.getrel('next') <> '') then
        s.append('<a href="'+ofeed.links.getrel('next')+'">'+GetFhirMessage('NAME_NEXT', lang)+'</a>&nbsp;')
      else
        s.append('<span style="color: grey">'+GetFhirMessage('NAME_NEXT', lang)+'</span>&nbsp;');
      if (ofeed.links.getrel('last') <> '') then
        s.append('<a href="'+ofeed.links.getrel('last')+'">'+GetFhirMessage('NAME_LAST', lang)+'</a>&nbsp;')
      else
        s.append('<span style="color: grey">'+GetFhirMessage('NAME_LAST', lang)+'</span>&nbsp;');
      if oFeed.SearchTotal <> 0 then
        s.append(' ('+inttostr(oFeed.SearchTotal)+' '+GetFhirMessage('FOUND', lang)+'). ');
      s.append('<span style="color: grey">'+GetFhirMessage('NAME_SEARCH', lang)+': '+ofeed.links.getrel('self')+'</span>&nbsp;</p>');
      s.append('<p>SQL: <span style="color: maroon">'+FormatTextToXML(oFeed.sql)+'</span></p>');
    end;

    for i := 0 to oFeed.entries.Count - 1 do
    begin
      e := oFeed.entries[i];
      s.append('<h2>'+FormatTextToXml(e.title)+'</h2>'+#13#10);
      if (e.categories <> nil) and (e.Resource <> nil) then
        s.append('<p><a href="'+e.id+'/_tags">'+GetFhirMessage('NAME_TAGS', lang)+'</a>: '+PresentTags(e.resource.ResourceType, e.links.GetRel('self')+'/_tags', e.categories, i+1        )+'</p>'+#13#10);

      u := e.Links.rel['self'];
      if (u <> '')  then
      begin
        s.append('<p><a href="'+e.Links.rel['self']+'">'+GetFhirMessage('THIS_RESOURCE', lang)+'</a> ');
      if not (e.resource is TFhirBinary) then
        begin
        s.append(
          ', <a href="'+e.Links.rel['self']+'?_format=xml">XML</a> '+GetFhirMessage('OR', lang)+' '+
        '<a href="'+e.Links.rel['self']+'?_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang));
        s.append(
          ', '+GetFhirMessage('OR', lang)+' <a href="'+e.id+'/_history">'+GetFhirMessage('NAME_HISTORY', lang)+'</a>.');

        if (e.links <> nil) and (e.links.GetRel('z-edit-src') <> '') then
          s.append(' Edit this as <a href="'+patchToWeb(e.links.GetRel('z-edit-src'))+'?srcformat=xml">XML</a> or <a href="'+patchToWeb(e.links.GetRel('z-edit-src'))+'?srcformat=json">JSON</a>.');

        {$IFNDEF FHIR_DSTU}
        if e.links.GetRel('edit-form') <> '' then
          if (e.resource is TFHIRQuestionnaireAnswers) then
          begin
            if (TFHIRQuestionnaireAnswers(e.resource).questionnaire <> nil) then
              s.append(' <a href="'+patchToWeb(e.links.GetRel('edit-form'))+'">Edit this Resource</a> (or <a href="'+TFHIRQuestionnaireAnswers(e.resource).questionnaire.referenceST+'">see the questionnaire</a>)')
          end
          else
            s.append(' <a href="'+patchToWeb(e.links.GetRel('edit-form'))+'">Edit this Resource</a> (or just see <a href="'+e.links.GetRel('edit-form')+'">the Questionnaire</a>)');
        if e.links.GetRel('edit-post') <> '' then
          s.append(' Submit edited content by POST to '+e.links.GetRel('edit-post'));
        {$ENDIF}

        if assigned(FOnGetLink) then
        begin
          FOnGetLink(e.resource, BaseURL, '', tail(e.id), tail(e.Links.rel['self']), link, text);
          if (link <> '') then
            s.append(' <a href="'+link+'">'+FormatTextToHTML(text)+'</a>');
        end;
        s.append('</br> Updated: '+e.updated.AsXML+'; Author: '+Author(e, a)+'</p>'+#13#10);
        end;
      end;

      if e.deleted then
        s.append('<p>'+GetFhirMessage('MSG_DELETED', lang)+'</p>')
      else if e.resource = nil then
        s.append('<p>(--)</p>')
      else if e.resource is TFhirBinary then
      begin
        if StringStartsWith(TFhirBinary(e.resource).ContentType, 'image/') then
          s.append('<img src="'+CODES_TFhirResourceType[e.resource.resourcetype]+'/'+e.id+'">'+#13#10)
        else
          s.append('<pre class="xml">'+#13#10+'('+GetFhirMessage('NAME_BINARY', lang)+')'+#13#10+'</pre>'+#13#10);
      end
      else
      begin
        xml := TFHIRXmlComposer.create(lang);
        ss := TStringStream.create('');
        try
          if (e.resource.text <> nil) and (e.resource.text.div_ <> nil) then
            ComposeXHtmlNode(s, e.resource.text.div_, 2, relativeReferenceAdjustment);
          xml.Compose(ss, '', e.id, tail(e.links.rel['self']), e.resource, true, e.links);
          s.append('<hr/>'+#13#10+'<pre class="xml">'+#13#10+FormatXMLToHTML(ss.dataString)+#13#10+'</pre>'+#13#10);
        finally
          ss.free;
          xml.free;
        end;
      end;
    end;
    s.append(
'<p><br/>'
+footer(FBaseUrl, lang)
    );
    s.WriteToStream(stream);
  finally
    s.free;
  end;
end;

function paramForScheme(scheme : String): String;
begin
  if scheme = 'http://hl7.org/fhir/tag' then
    result := '_tag'
  else if scheme = 'http://hl7.org/fhir/tag/profile' then
    result := '_profile'
  else if scheme = 'http://hl7.org/fhir/tag/security' then
    result := '_security'
  else
    result := '_othertag';
end;

procedure TFHIRXhtmlComposer.Compose(stream: TStream; ResourceType: TFhirResourceType; statedType, id, ver: String; oTags: TFHIRAtomCategoryList; isPretty: Boolean);
var
  s : TAdvStringBuilder;
  i : integer;
begin
  s := TAdvStringBuilder.create;
  try
    s.append(
'<?xml version="1.0" encoding="UTF-8"?>'+#13#10+
'<!DOCTYPE HTML'+#13#10+
'       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">'+#13#10+
''+#13#10+
'<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">'+#13#10+
'<head>'+#13#10);
  if ResourceType = frtNull then
    s.append('    <title>'+FormatTextToXml(GetFhirMessage('SYSTEM_TAGS', lang))+'</title>'+#13#10)
  else if id = '' then
    s.append('    <title>'+FormatTextToXml(StringFormat(GetFhirMessage('RESOURCE_TYPE_TAGS', lang), [CODES_TFhirResourceType[ResourceType]]))+'</title>'+#13#10)
  else if ver = '' then
    s.append('    <title>'+FormatTextToXml(StringFormat(GetFhirMessage('RESOURCE_TAGS', lang), [CODES_TFhirResourceType[ResourceType], id]))+'</title>'+#13#10)
  else
    s.append('    <title>'+FormatTextToXml(StringFormat(GetFhirMessage('RESOURCE_VER_TAGS', lang), [CODES_TFhirResourceType[ResourceType], id, ver]))+'</title>'+#13#10);

    s.append(
PageLinks+#13#10+
FHIR_JS+#13#10+
'</head>'+#13#10+
''+#13#10+
'<body>'+#13#10+
''+#13#10+
Header(Session, FBaseURL, Lang));

  if ResourceType = frtNull then
    s.append('    <h2>'+FormatTextToXml(GetFhirMessage('SYSTEM_TAGS', lang))+'</title>'+#13#10+
     '<p></p><p>'+GetFhirMessage('NAME_LINKS', lang)+': <a href="?_format=xml">XML</a> or <a href="?_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang)+'. '+
     'Or: <a href="'+FBaseUrl+'"/>Home Page</a> </p>')
  else if id = '' then
    s.append('    <h2>'+FormatTextToXml(StringFormat(GetFhirMessage('RESOURCE_TYPE_TAGS', lang), [CODES_TFhirResourceType[ResourceType]]))+'</h2>'+#13#10+
     '<p></p><p>'+GetFhirMessage('NAME_LINKS', lang)+': <a href="?_format=xml">XML</a> or <a href="?_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang)+'. '+
     'Or: '+ResourceLinks(ResourceType, lang, FBaseURL, 0, false, false)+' </p>')
  else if ver = '' then
    s.append('    <h2>'+FormatTextToXml(StringFormat(GetFhirMessage('RESOURCE_TAGS', lang), [CODES_TFhirResourceType[ResourceType], id]))+'</h2>'+#13#10+
     '<p></p><p>'+GetFhirMessage('NAME_LINKS', lang)+': <a href="?_format=xml">XML</a> or <a href="?_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang)+'. '+
     'Or: <a href="../'+id+'">This Resource</a> </p>')
  else
    s.append('    <h2>'+FormatTextToXml(StringFormat(GetFhirMessage('RESOURCE_VER_TAGS', lang), [CODES_TFhirResourceType[ResourceType], id, ver]))+'</h2>'+#13#10+
     '<p></p><p>'+GetFhirMessage('NAME_LINKS', lang)+': <a href="?_format=xml">XML</a> or <a href="?_format=json">JSON</a> '+GetFhirMessage('NAME_REPRESENTATION', lang)+'. '+
     'Or: <a href="../'+ver+'">This Resource Version</a> </p>');

   s.append('<p></p>'+#13#10);
   if (oTags.Count = 0) then
     s.append('<p>'+GetFhirMessage('NO_TAGS', lang)+'</p>'+#13#10)
   else
   begin
     s.append('<table>'+#13#10);
     s.append(' <tr><td><b>URI</b></td><td></td><td><b>Label</b></td></tr>'+#13#10);
     for i := 0 to oTags.Count - 1 do
     begin
       s.append(' <tr><td>');
       if ResourceType = frtNull then
         s.append('<a href="'+FBaseUrl+'_search?'+paramForScheme(oTags[i].scheme)+'='+EncodeMIME(oTags[i].term)+'"/>'+oTags[i].term+'</a>')
       else
         s.append('<a href="'+FBaseUrl+CODES_TFhirResourceType[ResourceType]+'/_search?'+paramForScheme(oTags[i].scheme)+'='+EncodeMIME(oTags[i].term)+'"/>'+oTags[i].term+'</a>');
       s.append('</td><td></td><td>'+FormatTextToXml(oTags[i].label_)+'</td></tr>'+#13#10);
     end;
     s.append('</table>'+#13#10);
   end;
   s.append('<p></p>'+#13#10);

    s.append(
'<p><br/>'+Footer(FBaseURL, lang)
    );
    s.WriteToStream(stream);
  finally
    s.free;
  end;
end;

procedure TFHIRXhtmlComposer.ComposeResource(xml: TXmlBuilder; statedType, id, ver : String; oResource: TFhirResource; links : TFHIRAtomLinkList);
var
  oHtml : TFhirXHtmlNode;
  oDoc : TFhirXHtmlNode;
  oHead : TFhirXHtmlNode;
  oWork : TFhirXHtmlNode;
begin
  oHtml := TFhirXHtmlNode.create;
  try
    oHtml.NodeType := fhntDocument;
    oHtml.AddComment('Generated by Server automatically');
    oDoc := oHtml.AddChild('html');
    oHead := oDoc.AddChild('head');
    oWork := oHead.AddChild('title');
    oWork.AddText('test title');
    oWork := oHead.AddChild('link');
    oWork.SetAttribute('rel', 'Stylesheet');
    oWork.SetAttribute('href', '/css/fhir.css');
    oWork.SetAttribute('type', 'text/css');
    oWork.SetAttribute('media', 'screen');
    oWork := oDoc.AddChild('body');
    if (oResource.text <> nil) And (oResource.text.div_ <> nil) Then
    begin
      oWork.Attributes.addAll(oResource.text.div_.Attributes);
      oWork.ChildNodes.AddAll(oResource.text.div_.ChildNodes);
    end;
    ComposeXHtmlNode(xml, oHtml, false);
  finally
    oHtml.Free;
  end;
end;

constructor TFHIRXhtmlComposer.Create(lang, BaseURL: String);
begin
  Create(lang);
  FBaseURL := BaseURL;
end;


destructor TFHIRXhtmlComposer.Destroy;
begin
  FSession.free;
  FTags.Free;
  inherited;
end;

class function TFHIRXhtmlComposer.Footer(base, lang : String; tail : boolean = true): string;
begin
  result :=
'</div>'+#13#10+
''+#13#10+
''+#13#10+
'				</div>  <!-- /inner-wrapper -->'+#13#10+
'            </div>  <!-- /row -->'+#13#10+
'        </div>  <!-- /container -->'+#13#10+
'    </div>  <!-- /segment-content -->'+#13#10+
''+#13#10+
''+#13#10+
'	<div id="segment-footer" class="segment">  <!-- segment-footer -->'+#13#10+
'		<div class="container">  <!-- container -->'+#13#10+
'			<div class="inner-wrapper">'+#13#10+
'				<p>'+#13#10+
'        <a href="'+base+'" style="color: gold">'+GetFhirMessage('SERVER_HOME', lang)+'</a>.&nbsp;|&nbsp;FHIR &copy; HL7.org 2011 - 2013. &nbsp;|&nbsp; FHIR '+GetFhirMessage('NAME_VERSION', lang)+' <a href="/index.html" style="color: gold">'+FHIR_GENERATED_VERSION+'-'+FHIR_GENERATED_REVISION+'</a>'+#13#10+
'        </span>'+#13#10+
'        </p>'+#13#10+
'			</div>  <!-- /inner-wrapper -->'+#13#10+
'		</div>  <!-- /container -->'+#13#10+
'	</div>  <!-- /segment-footer -->'+#13#10+
''+#13#10+
''+#13#10+
'	<div id="segment-post-footer" class="segment hidden">  <!-- segment-post-footer -->'+#13#10+
'		<div class="container">  <!-- container -->'+#13#10+
'		</div>  <!-- /container -->'+#13#10+
'	</div>  <!-- /segment-post-footer -->'+#13#10+
''+#13#10+
''+#13#10+
''+#13#10+
''+#13#10+
''+#13#10+
'      <!-- JS and analytics only. -->'+#13#10+
'      <!-- Bootstrap core JavaScript'+#13#10+
'================================================== -->'+#13#10+
'  <!-- Placed at the end of the document so the pages load faster -->'+#13#10+
'<script src="/assets/js/jquery.js"/>'+#13#10+
'<script src="/dist/js/bootstrap.min.js"/>'+#13#10+
'<script src="/assets/js/respond.min.js"/>'+#13#10+
''+#13#10+
'<script src="/assets/js/fhir.js"/>'+#13#10+
''+#13#10+
'  <!-- Analytics Below'+#13#10+
'================================================== -->'+#13#10+
''+#13#10+
''+#13#10+
''+#13#10;
if tail then
  result := result +
'</body>'+#13#10+
'</html>'+#13#10;
end;

class function TFHIRXhtmlComposer.Header(Session : TFhirSession; base, lang : String): String;
begin
  result :=
'	<div id="segment-navbar" class="segment">  <!-- segment-breadcrumb -->'+#13#10+
'		<div id="stripe"> </div>'+#13#10+
'		<div class="container">  <!-- container -->'+#13#10+
'		<div style="background-color: #ad1f2f; padding: 6px; color: white;">  <!-- container -->'+#13#10;


  result := result +

  '  <a href="http://www.hl7.org/fhir" style="color: gold" title="'+GetFhirMessage('MSG_HOME_PAGE_TITLE', lang)+'"><img border="0" src="/icon-fhir-16.png" style="vertical-align: text-bottom"/> <b>FHIR</b></a>'#13#10+
  ''#13#10+
  '  &copy; HL7.org'#13#10+
  '  &nbsp;|&nbsp;'#13#10+
  '  <a href="'+base+'" style="color: gold">'+GetFhirMessage('SERVER_HOME', lang)+'</a> '+
  '  &nbsp;|&nbsp;'#13#10+
  '  <a href="http://www.healthintersections.com.au" style="color: gold">Health Intersections</a> '+GetFhirMessage('NAME_SERVER', lang)+''#13#10+
  '  &nbsp;|&nbsp;'#13#10+
  '  <a href="/index.html" style="color: gold">FHIR '+GetFhirMessage('NAME_VERSION', lang)+' '+FHIR_GENERATED_VERSION+'-'+FHIR_GENERATED_REVISION+'</a>'#13#10;

  if (session <> nil)  then
  begin
    result := result +'&nbsp;|&nbsp;';
    if session.rights.indexof('user') > -1 then
      result := result +'User: '+FormatTextToXml(Session.Name)
    else
      result := result +'User: [n/a]';
    if not session.anonymous then
      result := result +'&nbsp; <a href="'+base+'/logout" title="Log Out"><img src="/logout.png"></a>';
  end;

  result := result +
  '  &nbsp;'#13#10+
'		</div>  <!-- /container -->'+#13#10+
'		</div>  <!-- /container -->'+#13#10+
  '</div>'#13#10+
  ''#13#10;
//    if FFacebookLike and (FOauthUrl <> '') then
//      result := result + '<iframe src="https://www.facebook.com/plugins/like.php?href='+FOauthUrl+'" scrolling="no" frameborder="0" style="border:none; width:450px; height:30px"></iframe>'#13#10;

  result := result +
'	<!-- /segment-breadcrumb -->'+#13#10+
''+#13#10+
'	<div id="segment-content" class="segment">  <!-- segment-content -->'+#13#10+
'	<div class="container">  <!-- container -->'+#13#10+
'            <div class="row">'+#13#10+
'            	<div class="inner-wrapper">'+#13#10+
' <div id="div-cnt" class="col-9">'+#13#10+
''+#13#10+
''+#13#10;
end;

function TFHIRXhtmlComposer.MimeType: String;
begin
  result := 'text/html; charset=UTF-8';
end;

function TFHIRComposer.ResourceMediaType: String;
begin
  result := 'application/xml+fhir; charset=UTF-8';
end;

function URLTail(s : String):String;
var
  i : integer;
begin
  i := LastDelimiter('/', s);
  result := copy(s, i+1, $FFFF);
  i := Pos('?', result);
  if i > 0 then
    result := copy(result, 1, i-1);
end;

class function TFHIRXhtmlComposer.PageLinks: String;
begin
result :=
'  <meta charset="utf-8"/>'+#13#10+
'  <meta content="width=device-width, initial-scale=1.0" name="viewport"/>'+#13#10+
'  <meta content="http://hl7.org/fhir" name="author"/>'+#13#10+
''+#13#10+
'  <link rel="stylesheet" href="/fhir.css"/>'+#13#10+
''+#13#10+
''+#13#10+
'    <!-- Bootstrap core CSS -->'+#13#10+
'  <link rel="stylesheet" href="/dist/css/bootstrap.css"/>'+#13#10+
'  <link rel="stylesheet" href="/assets/css/bootstrap-fhir.css"/>'+#13#10+
''+#13#10+
'    <!-- Project extras -->'+#13#10+
'  <link rel="stylesheet" href="/assets/css/project.css"/>'+#13#10+
'  <link rel="stylesheet" href="/assets/css/pygments-manni.css"/>'+#13#10+
''+#13#10+
'    <!-- FHIR Server stuff -->'+#13#10+
'  <link rel="stylesheet" href="/css/tags.css"/>'+#13#10+
''+#13#10+
'    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->'+#13#10+
'    <!-- [if lt IE 9]>'+#13#10+
'  <script src="/assets/js/html5shiv.js"></script>'+#13#10+
'  <script src="/assets/js/respond.min.js"></script>'+#13#10+
'  <![endif] -->'+#13#10+
''+#13#10+
'    <!-- Favicons -->'+#13#10+
'  <link sizes="144x144" rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-144-precomposed.png"/>'+#13#10+
'  <link sizes="114x114" rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-114-precomposed.png"/>'+#13#10+
'  <link sizes="72x72" rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-72-precomposed.png"/>'+#13#10+
'  <link rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-57-precomposed.png"/>'+#13#10+
'  <link rel="shortcut icon" href="/assets/ico/favicon.png"/>'+#13#10;
end;

function TFHIRXhtmlComposer.PresentTags(aType : TFhirResourceType; target : String; tags: TFHIRAtomCategoryList; c : integer): String;
var
  i : integer;
  lbl : string;
  clss, typ : string;
begin
  if tags.count = 0 then
    result := '(no tags)'
  else
  begin
    result := '';
    for i := 0 to tags.count - 1 do
    begin
      lbl := tags[i].label_;
      if lbl = '' then
        lbl := URLTail(tags[i].term);
      if (length(lbl) > 20) then
        lbl := Copy(lbl, 1, 20)+'..';

      if tags[i].scheme = TAG_FHIR_SCHEME_PROFILE then
      begin
        clss := 'tag-profile';
        typ := 'Profile: ';
      end
      else if tags[i].scheme = TAG_FHIR_SCHEME_SECURITY then
      begin
        clss := 'tag-security';
        typ := 'Security: ';
      end
      else
        clss := 'tag';

      if aType = frtNull then
        result := result + '<a href="'+FBaseUrl+'_search?'+paramForScheme(tags[i].scheme)+'='+EncodeMIME(tags[i].term)+'" class="'+clss+'" title="'+typ+tags[i].term+'">'+lbl+'</a>'
      else
      begin
        result := result + '<a href="'+FBaseUrl+CODES_TFhirResourceType[aType]+'/_search?'+paramForScheme(tags[i].scheme)+'='+EncodeMIME(tags[i].term)+'" class="'+clss+'" title="'+typ+tags[i].term+'">'+lbl+'</a>';
        if (target <> '') then
          result := result + '<a href="javascript:deleteTag('''+target+'/_delete'', '''+tags[i].scheme+''', '''+tags[i].term+''')" class="tag-delete" title="Delete '+tags[i].term+'">-</a>'
      end;
      result := result + '&nbsp;';
    end;
  end;
  if target <> '' then
    result := result +'&nbsp; <a id="tb'+inttostr(c)+'" class="tag" title="Add a tag" href="javascript:addTag(''tb'+inttostr(c)+''', '''+FBaseUrl+''', '''+target+''')">+</a>';
end;

class function TFHIRXhtmlComposer.ResourceLinks(a : TFhirResourceType; lang, base: String; count : integer; bTable, bPrefixLinks : boolean): String;
var
  bef, aft, pfx, pfxp : String;
begin
  if bPrefixLinks then
  begin
    pfx := base+'/'+CODES_TFHIRResourceType[a]+'/';
    pfxp := base+'/'+'profile/'
  end
  else
  begin
    pfxp := '../profile/';
    pfx := '';
  end;

  if bTable then
  begin
    bef := '<td>';
    aft := '</td>';
  end
  else
  begin
    bef := '&nbsp;';
    aft := '';
  end;
  result := bef + CODES_TFHIRResourceType[a] + aft;
  if not bTable then
    result := result + ':';
  if count > -1 then
    result := result + bef + inttostr(count) + aft;
  if a = frtBinary then
    result := result + bef + 'n/a' + aft
  else
    result := result + bef + '<a class="button" href="'+pfxp+CODES_TFHIRResourceType[a]+'">'+GetFhirMessage('NAME_PROFILE', lang)+'</a>' + aft;
  result := result + bef + '<a class="button" href="'+pfx+'_history">'+GetFhirMessage('NAME_UPDATES', lang)+'</a>' + aft;
  if a = frtBinary then
    result := result + bef + 'n/a' + aft
  else
    result := result + bef + '<a class="button" href="'+pfx+'_search">'+GetFhirMessage('NAME_SEARCH', lang)+'</a>' + aft;
  if bTable then
    result := result + bef + '<a class="tag" href="'+pfx+'_tags">'+GetFhirMessage('NAME_TAGS', lang)+'</a>' + aft;
end;

function TFHIRXhtmlComposer.ResourceMediaType: String;
begin
  result := 'text/html; charset=UTF-8';
end;

function TFHIRXmlParserBase.GetAttribute(element: IXmlDomElement; const name : String): String;
begin
  result := TMsXmlParser.GetAttribute(element, name);
end;

function TFHIRXmlParserBase.FirstChild(element: IXmlDomElement): IXmlDomElement;
Var
  node : IXMLDOMNode;
Begin
  result := Nil;
  node := element.firstChild;
  While Assigned(node) And not Assigned(result) Do
  Begin
    If node.nodeType = NODE_ELEMENT Then
      result := node as IXMLDOMElement
    else if node.nodeType = NODE_COMMENT then
      FComments.add(node.text);
    node := node.nextSibling;
  End;
end;

function TFHIRXmlParserBase.NextSibling(element: IXmlDomElement): IXmlDomElement;
Var
  node : IXMLDOMNode;
Begin
  result := Nil;
  node := element.nextSibling;
  While Assigned(node) And not Assigned(result) Do
  Begin
    If node.nodeType = NODE_ELEMENT Then
      result := node as IXMLDOMElement
    else if node.nodeType = NODE_COMMENT then
      FComments.add(node.text);
    node := node.nextSibling;
  End;
end;


procedure TFHIRXmlParserBase.TakeCommentsStart(element: TFHIRBase);
begin
  if FComments.count > 0 then
  begin
    element.xml_commentsStart.assign(FComments);
    FComments.Clear;
  end;
end;

procedure TFHIRXmlParserBase.TakeCommentsEnd(element: TFHIRBase);
begin
  if FComments.count > 0 then
  begin
    element.xml_commentsEnd.assign(FComments);
    FComments.Clear;
  end;
end;

function TFHIRComposer.Compose(statedType, id, ver: String; oResource: TFhirResource; isPretty: Boolean; links: TFHIRAtomLinkList): String;
var
  stream : TBytesStream;
begin
  stream := TBytesStream.create;
  try
    compose(stream, statedType, id, ver, oResource, isPretty, links);
    result := TEncoding.UTF8.GetString(copy(stream.Bytes, 0, stream.position));
  finally
    stream.Free;
  end;
end;

function TFHIRComposer.Compose(oFeed: TFHIRAtomFeed; isPretty: Boolean): String;
var
  stream : TBytesStream;
begin
  stream := TBytesStream.create;
  try
    compose(stream, oFeed, isPretty);
    result := TEncoding.UTF8.GetString(stream.Bytes);
  finally
    stream.Free;
  end;
end;

procedure TFHIRComposer.ComposeBinary(xml: TXmlBuilder; binary: TFhirBinary);
begin
  if (xml.Namespace <> FHIR_NS) then
    xml.Namespace := FHIR_NS;
  xml.AddAttribute('id', binary.xmlId);
  xml.AddAttribute('contentType', binary.ContentType);
  xml.TagText('Binary', StringReplace(string(EncodeBase64(binary.Content.Data, binary.Content.Size)), #13#10, ''));
end;

function isRelativeReference(s : string) : boolean;
begin
  if s.StartsWith('http') then
    result := false
  else if s.StartsWith('https') then
    result := false
  else if s.StartsWith('/') then
    result := false
  else
    result := true;
end;

function FixRelativeReference(s : string; indent : integer) : String;
var
  i : integer;
begin
  result := '';
  for i := 1 to indent do
    result := result + '../';
  result := result + s;
end;


procedure TFHIRComposer.ComposeXHtmlNode(s: TAdvStringBuilder; node: TFhirXHtmlNode; indent, relativeReferenceAdjustment : integer);
var
  i : Integer;
begin
  if node = nil then
    exit;
  case node.NodeType of
    fhntText : s.append(FormatTexttoXml(node.Content));
    fhntComment : s.append('<!-- '+FormatTexttoXml(node.Content)+' -->');
    fhntElement :
      begin
      s.append('<'+node.name);
      for i := 0 to node.Attributes.count - 1 do
        if (node.name = 'a') and (node.Attributes[i].Name = 'href') and isRelativeReference(node.Attributes[i].Value) then
          s.append(' '+node.Attributes[i].Name+'="'+FixRelativeReference(node.Attributes[i].Value, relativeReferenceAdjustment)+'"')
        else
          s.append(' '+node.Attributes[i].Name+'="'+FormatTexttoXml(node.Attributes[i].Value)+'"');
      if node.ChildNodes.Count > 0 then
      begin
        s.append('>');
        for i := 0 to node.ChildNodes.count - 1 do
          ComposeXHtmlNode(s, node.ChildNodes[i], i+2, relativeReferenceAdjustment);
        s.append('</'+node.name+'>');
      end
      else
        s.append('/>');
      end;
    fhntDocument:
      for i := 0 to node.ChildNodes.count - 1 do
        ComposeXHtmlNode(s, node.ChildNodes[i], 0, relativeReferenceAdjustment);
  else
    raise exception.create('not supported');
  End;
end;

function TFHIRXmlParserBase.parseBinary(element: IXmlDomElement; path : String): TFhirBinary;
begin
  result := TFhirBinary.create;
  try
    result.ContentType := GetAttribute(element, 'contentType');
    result.xmlId := GetAttribute(element, 'id');
    result.Content.AsBytes := DecodeBase64(AnsiString(element.text));
    result.link;
  finally
    result.free;
  end;
end;


function TFHIRXmlParserBase.CheckHtmlElementOk(elem: IXMLDOMElement): boolean;
var
  bOk : boolean;
begin
  bOk := StringArrayExistsInsensitive(['p', 'br', 'div', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'a', 'span', 'b', 'em', 'i', 'strong',
    'small', 'big', 'tt', 'small', 'dfn', 'q', 'var', 'abbr', 'acronym', 'cite', 'blockquote', 'hr', 'address', 'bdo', 'kbd', 'q', 'sub', 'sup',
    'ul', 'ol', 'li', 'dl', 'dt', 'dd', 'pre', 'table', 'caption', 'colgroup', 'col', 'thead', 'tr', 'tfoot', 'tbody', 'th', 'td',
    'code', 'samp', 'img', 'map', 'area'], elem.nodeName);
  if bOk then
    result := true
  else case FParserPolicy of
    xppAllow: result := true;
    xppDrop: result := false;
    xppReject: raise Exception.Create('Illegal HTML element '+elem.nodeName);
  end;
//  attributes: a.href, a.name,  *.title, *.style, *.class, *.id, *.span,
end;

function TFHIRXmlParserBase.CheckHtmlAttributeOk(elem, attr, value : String): boolean;
var
  bOk : boolean;
begin
  bOk := StringArrayExistsInsensitive(['title', 'style', 'class', 'id', 'lang', 'xml:lang', 'dir', 'accesskey', 'tabindex',
                    // tables
                   'span', 'width', 'align', 'valign', 'char', 'charoff', 'abbr', 'axis', 'headers', 'scope', 'rowspan', 'colspan'], attr) or
         StringArrayExistsInsensitive(['a.href', 'a.name', 'img.src', 'img.border', 'div.xmlns', 'blockquote.cite', 'q.cite',
             'a.charset', 'a.type', 'a.name', 'a.href', 'a.hreflang', 'a.rel', 'a.rev', 'a.shape', 'a.coords', 'img.src',
             'img.alt', 'img.longdesc', 'img.height', 'img.width', 'img.usemap', 'img.ismap', 'map.name', 'area.shape',
             'area.coords', 'area.href', 'area.nohref', 'area.alt', 'table.summary', 'table.width', 'table.border',
             'table.frame', 'table.rules', 'table.cellspacing', 'table.cellpadding'], elem+'.'+attr);
  if bOk then
    result := true
  else case FParserPolicy of
    xppAllow: result := true;
    xppDrop: result := false;
    xppReject: raise Exception.Create('Illegal HTML attribute '+elem+'.'+attr);
  end;

  if (elem+'.'+attr = 'img.src') and not (StringStartsWith(value, '#') or StringStartsWith(value, 'data:') or StringStartsWith(value, 'http:') or StringStartsWith(value, 'https:')) then
    case FParserPolicy of
      xppAllow: result := true;
      xppDrop: result := false;
      xppReject: raise Exception.Create('Illegal Image Reference '+value);
  end;

end;

procedure TFHIRXmlParserBase.checkOtherAttributes(value: IXmlDomElement; path : String);
var
  i : integer;
  name : String;
begin
  if not AllowUnknownContent then
  begin
    for i := 0 to value.attributes.length - 1 do
    begin
      name := value.attributes.item[i].nodeName;
      if (name <> 'id') and // always ok
         (name <> 'value') and // value is ok (todo: only on primitives)
         ((name <> 'url')) and // url is ok on extensions which appear with various names
         (name <> 'xmlns') and // namespaces are ok
         (not name.StartsWith('xmlns:')) then // namespaces are ok
        XmlError(path+'/@'+name, StringFormat(GetFhirMessage('MSG_UNKNOWN_CONTENT', lang), [name, path]));
    end;
  end;
end;

procedure TFHIRXmlParserBase.closeOutElement(result: TFhirElement; element: IXmlDomElement);
begin
  TakeCommentsEnd(result);
end;

function TFHIRXmlParserBase.ParseContained(element: IXmlDomElement; path : String): TFhirResource;
var
  child : IXMLDOMElement;
begin
  child := FirstChild(element);
  result := ParseResource(child, path);
  try
    child := NextSibling(child);
    if (child <> nil) then
      UnknownContent(child, path);
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRParser.toTDateAndTime(s: String): TDateAndTime;
begin
  if s = '' then
    result := nil
  else
    result := TDateAndTime.createXml(s);
end;

function TFHIRComposer.asString(value: TDateAndTime): String;
begin
  if value = nil then
    result := ''
  else
    result := value.AsXML;
end;

constructor TFHIRComposer.Create(lang: String);
begin
  inherited Create;
  FLang := lang;
end;

procedure TFHIRXhtmlComposer.SetSession(const Value: TFhirSession);
begin
  FSession.free;
  FSession := Value;
end;


procedure TFHIRXmlParserBase.ParseTags(element: IXMLDOMElement);
var
  child : IXMLDOMElement;
begin
  if not sameText(element.baseName, 'taglist') then
    Raise Exception.create(StringFormat(GetFhirMessage('MSG_CANT_PARSE_ROOT', lang), [element.baseName]));

  FTags := TFHIRAtomCategoryList.create;
  child := TMsXmlParser.FirstChild(element);
  while (child <> nil) do
  begin
    if (child.baseName = 'category') then
      FTags.AddValue(TMsXmlParser.GetAttribute(child, 'scheme'), TMsXmlParser.GetAttribute(child, 'term'), TMsXmlParser.GetAttribute(child, 'label'))
    else
       UnknownContent(child, 'TagList');
    child := NextSibling(child);
  end;
end;

procedure TFHIRXhtmlComposer.SetTags(const Value: TFHIRAtomCategoryList);
begin
  FTags.free;
  FTags := Value;
end;

function TFHIRParser.StringArrayToCommaString(const aNames: array of String): String;
var
  i : integer;
begin
  result := '(';
  for i := 0 to Length(aNames) - 1 do
    if i = 0 then
      result := result + '"'+aNames[i]+'"'
    else
      result := result + ', "'+aNames[i]+'"';
  result := result + ')';
end;

End.
