Unit MsXmlParser;

{
Copyright (c) 2001-2013, Kestral Computing Pty Ltd (http://www.kestral.com.au)
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

Uses
  Windows, SysUtils, Classes, ComObj,
  IdSoapMsXml,
  AdvObjects, Advmemories, AdvBuffers, AdvStreams, AdvStringLists,
  XmlBuilder;


Type
  TTextAction = (ttAsIs, ttTrim, ttTrimPad);

  TMsXmlSaxHandler = class (TinterfacedObject, IVBSAXContentHandler, IVBSAXErrorHandler)
  private
    FLocator : IVBSAXLocator;
    FLocation : TSourceLocation;
  protected
    FXmlComments : TAdvStringList;
    procedure startElement(sourceLocation : TSourceLocation; uri, localname : string; attrs : IVBSAXAttributes); overload; virtual;
    procedure endElement(sourceLocation : TSourceLocation); overload; virtual;
    procedure text(chars : String; sourceLocation : TSourceLocation); virtual;
  public
    Constructor create;
    destructor Destroy; override;
    { SAX }
   // IDispatch
    function GetTypeInfoCount(out Count: Integer): HResult; stdcall;
    function GetTypeInfo(Index, LocaleID: Integer; out TypeInfo): HResult; stdcall;
    function GetIDsOfNames(const IID: TGUID; Names: Pointer; NameCount, LocaleID: Integer; DispIDs: Pointer): HResult; stdcall;
    function Invoke(DispID: Integer; const IID: TGUID; LocaleID: Integer; Flags: Word; var Params; VarResult, ExcepInfo, ArgErr: Pointer): HResult; stdcall;
    procedure Set_documentLocator(const locator: IVBSAXLocator); safecall;
    procedure startDocument; safecall;
    procedure endDocument; safecall;
    procedure startPrefixMapping(var prefix, uri: widestring); safecall;
    procedure endPrefixMapping(var prefix: WideString); safecall;
    procedure startElement(var uri, localname, qname : widestring; const attrs: IVBSAXAttributes); overload; safecall;
    procedure endElement(var uri, localname, qname : WideString); overload; safecall;
    procedure characters(var chars: WideString); safecall;
    procedure ignorableWhitespace(var text: WideString); safecall;
    procedure processingInstruction(var target, data: WideString); safecall;
    procedure skippedEntity(var name: wideString); safecall;
    procedure error(const oLocator: IVBSAXLocator; var strErrorMessage: WideString; nErrorCode: Integer); safecall;
    procedure fatalError(const oLocator: IVBSAXLocator; var strErrorMessage: WideString; nErrorCode: Integer); safecall;
    procedure ignorableWarning(const oLocator: IVBSAXLocator; var strErrorMessage: WideString; nErrorCode: Integer); safecall;
  end;

  TMsXmlParser = class (TAdvObject)
  Private
  Public
    Function Parse(Const sFilename : String) : IXMLDomDocument2; Overload;
    Function Parse(Const oSource : TStream) : IXMLDomDocument2; Overload;
    Function Parse(Const oSource : TAdvStream) : IXMLDomDocument2; Overload;
    Function Parse(Const oSource : TAdvBuffer) : IXMLDomDocument2; Overload;
    Function ParseString(Const sSource : String) : IXMLDomDocument2; Overload;

    Class Function GetAttribute(oElement : IXMLDOMElement; Const sName : WideString) : WideString; overload;
    Class Function GetAttribute(oElement : IXMLDOMElement; Const sNamespace, sName : WideString) : WideString; overload;
    Class Function FirstChild(oElement : IXMLDOMNode) : IXMLDOMElement;
    Class Function NextSibling(oElement : IXMLDOMElement) : IXMLDOMElement;
    Class Function TextContent(oElement : IXMLDOMElement; aTextAction : TTextAction) : WideString;

    Procedure Parse(Const sFilename : String; handler : TMsXmlSaxHandler); Overload;
    Procedure Parse(Const oSource : TStream; handler : TMsXmlSaxHandler); Overload;
    Procedure Parse(Const oSource : TAdvStream; handler : TMsXmlSaxHandler); Overload;
    Procedure Parse(Const oSource : TAdvBuffer; handler : TMsXmlSaxHandler); Overload;
  End;

Implementation

Uses
  ActiveX,
  AdvWinInetClients,
  IdSoapXml,
  MsXmlBuilder,
  StringSupport,
  AdvVclStreams;

{ TMsXmlParser }

function TMsXmlParser.Parse(const sFilename: String): IXMLDomDocument2;
var
  oFile : TFileStream;
  oWeb : TAdvWinInetClient;
begin
  if StringStartsWith(sFilename, 'http:') or StringStartsWith(sFilename, 'https:') or StringStartsWith(sFilename, 'ftp:')  Then
  Begin
    oWeb := TAdvWinInetClient.Create;
    Try
//      oWeb.SetAddress(sFilename);
      oWeb.RequestMethod := 'GET';
      oWeb.Request := TAdvBuffer.Create;
      oWeb.Response := TAdvBuffer.Create;
      oWeb.Execute;
      if oWeb.ResponseCode <> '200' Then
        Raise Exception.Create('HTTP Error '+oWeb.ResponseCode);
      result := Parse(oWeb.Response);
    Finally
      oWeb.Free;
    End;
  End
  Else
  Begin
    oFile := TFileStream.Create(sFilename, fmOpenRead + fmShareDenyWrite);
    Try
      Result := Parse(oFile);
    Finally
      oFile.Free;
    End;
  End;
end;


function TMsXmlParser.Parse(const oSource: TStream): IXMLDomDocument2;
Var
  iDom : IXMLDomDocument2;
  vAdapter : Variant;
  sError : String;
begin
  CoInitializeEx(nil, COINIT_MULTITHREADED);
  iDom := LoadMsXMLDom;
  iDom.validateOnParse := False;
  iDom.preserveWhiteSpace := True;
  iDom.resolveExternals := False;
  iDom.setProperty('NewParser', True);
  vAdapter := TStreamAdapter.Create(oSource) As IStream;
  if not iDom.load(vAdapter) Then
  Begin
    sError := iDom.parseError.reason + ' at line '+IntToStr(iDom.parseError.line)+' row '+IntToStr(iDom.parseError.linepos);
    if iDom.parseError.url <> '' Then
      sError := sError + '. url="'+ iDom.parseError.url+'"';
    sError := sError + '. source = '+ iDom.parseError.srcText+'"';
    Error('Parse', sError);
  End;
  Result := iDom;
end;


function TMsXmlParser.Parse(const oSource: TAdvStream): IXMLDomDocument2;
Var
  oWrapper : TVCLStream;
begin
  oWrapper := TVCLStream.Create;
  Try
    oWrapper.Stream := oSource.Link;
    Result := Parse(oWrapper);
  Finally
    oWrapper.Free;
  End;
end;

Class Function TMsXmlParser.GetAttribute(oElement : IXMLDOMElement; Const sName : WideString) : WideString;
Var
  LAttr : IXMLDOMNamedNodeMap;
  LNode : IXMLDOMAttribute;
Begin
  LAttr := oElement.attributes;
  LNode := LAttr.getQualifiedItem(sName, '') As IXMLDOMAttribute;
  If Assigned(Lnode) Then
    Result := LNode.text
  Else
  Begin
    LNode := LAttr.getNamedItem(sName) As IXMLDOMAttribute;
    If Assigned(Lnode) Then
      Result := LNode.text;
  End;
End;

Class Function TMsXmlParser.GetAttribute(oElement : IXMLDOMElement; Const sNamespace, sName : WideString) : WideString;
Var
  LAttr : IXMLDOMNamedNodeMap;
  LNode : IXMLDOMAttribute;
Begin
  LAttr := oElement.attributes;
  LNode := LAttr.getQualifiedItem(sName, sNamespace) As IXMLDOMAttribute;
  If Assigned(Lnode) Then
    Result := LNode.text
  else
    Result := '';
End;


Class Function TMsXmlParser.FirstChild(oElement : IXMLDOMNode) : IXMLDOMElement;
Var
  oNode : IXMLDOMNode;
Begin
  result := Nil;
  oNode := oElement.firstChild;
  While Assigned(oNode) And not Assigned(result) Do
  Begin
    If oNode.nodeType = NODE_ELEMENT Then
      result := oNode as IXMLDOMElement;
    oNode := oNode.nextSibling;
  End;
End;


Class Function TMsXmlParser.NextSibling(oElement : IXMLDOMElement) : IXMLDOMElement;
Var
  oNode : IXMLDOMNode;
Begin
  result := Nil;
  oNode := oElement.nextSibling;
  While Assigned(oNode) And not Assigned(result) Do
  Begin
    If oNode.nodeType = NODE_ELEMENT Then
      result := oNode as IXMLDOMElement;
    oNode := oNode.nextSibling;
  End;
End;



procedure TMsXmlParser.Parse(const oSource: TStream; handler: TMsXmlSaxHandler);
var
  v : variant;
  sax : IVBSAXXMLReader ;
begin
  v := CreateOleObject(GMsXmlProgId_SAX);
  sax := IUnknown(TVarData(v).VDispatch) as IVBSAXXMLReader ;

  sax.contentHandler := handler;
  sax.errorHandler := handler;

  v := TStreamAdapter.Create(oSource) As IStream;
  sax.parse(v);
end;

procedure TMsXmlParser.Parse(const sFilename: String; handler: TMsXmlSaxHandler);
var
  oFile : TFileStream;
  oWeb : TAdvWinInetClient;
begin
  if StringStartsWith(sFilename, 'http:') or StringStartsWith(sFilename, 'https:') or StringStartsWith(sFilename, 'ftp:')  Then
  Begin
    oWeb := TAdvWinInetClient.Create;
    Try
//      oWeb.SetAddress(sFilename);
      oWeb.RequestMethod := 'GET';
      oWeb.Request := TAdvBuffer.Create;
      oWeb.Response := TAdvBuffer.Create;
      oWeb.Execute;
      if oWeb.ResponseCode <> '200' Then
        Raise Exception.Create('HTTP Error '+oWeb.ResponseCode);
      Parse(oWeb.Response, handler);
    Finally
      oWeb.Free;
    End;
  End
  Else
  Begin
    oFile := TFileStream.Create(sFilename, fmOpenRead + fmShareDenyWrite);
    Try
      Parse(oFile, handler);
    Finally
      oFile.Free;
    End;
  End;

end;

procedure TMsXmlParser.Parse(const oSource: TAdvBuffer; handler: TMsXmlSaxHandler);
var
  oMem : TAdvMemoryStream;
begin
  oMem := TAdvMemoryStream.Create;
  try
    oMem.Buffer := oSource.Link;
    Parse(oMem, handler);
  Finally
    oMem.Free;
  End;
end;

function TMsXmlParser.ParseString(const sSource: String): IXMLDomDocument2;
var
  oMem : TStringStream;
begin
  oMem := TStringStream.Create(sSource);
  try
    result := Parse(oMem);
  Finally
    oMem.Free;
  End;
end;

procedure TMsXmlParser.Parse(const oSource: TAdvStream; handler: TMsXmlSaxHandler);
Var
  oWrapper : TVCLStream;
begin
  oWrapper := TVCLStream.Create;
  Try
    oWrapper.Stream := oSource.Link;
    Parse(oWrapper, handler);
  Finally
    oWrapper.Free;
  End;

end;

Function Trim(Const sValue : WideString; bWhitespaceWithMeaning : Boolean):WideString;
Begin
  result := StringTrimWhitespace(sValue);
  If bWhitespaceWithMeaning And (Result = '') Then
    result := ' ';
End;


class function TMsXmlParser.TextContent(oElement: IXMLDOMElement; aTextAction: TTextAction): WideString;
Var
  oNode : IXMLDOMNode;
Begin
  result := '';
  if oElement <> nil Then
  Begin
    oNode := oElement.firstChild;
    While Assigned(oNode) Do
    Begin
      If (oNode.nodeType = NODE_TEXT) Then
        result := result + oNode.text;
      oNode := oNode.nextSibling;
    End;
    if (aTextAction <> ttAsIs) Then
      Result := Trim(result, aTextAction = ttTrimPad);
  End;
end;

function TMsXmlParser.Parse(const oSource: TAdvBuffer): IXMLDomDocument2;
var
  oMem : TAdvMemoryStream;
begin
  oMem := TAdvMemoryStream.Create;
  try
    oMem.Buffer := oSource.Link;
    result := Parse(oMem);
  Finally
    oMem.Free;
  End;
end;


{ TMsXmlSaxHandler }

procedure TMsXmlSaxHandler.characters(var chars: WideString);
begin
  FLocation.Line := FLocator.lineNumber;
  FLocation.col := FLocator.columnNumber;
  text(chars, FLocation);
end;

constructor TMsXmlSaxHandler.create;
begin
  inherited;
  FXmlComments := TAdvStringList.create;
end;

destructor TMsXmlSaxHandler.destroy;
begin
  FXmlComments.Free;
  inherited;
end;

procedure TMsXmlSaxHandler.endDocument;
begin
  // nothing
end;

procedure TMsXmlSaxHandler.endElement(var uri, localname, qname: WideString);
begin
  FLocation.Line := FLocator.lineNumber;
  FLocation.col := FLocator.columnNumber;
  endElement(FLocation);
end;

procedure TMsXmlSaxHandler.endElement(sourceLocation : TSourceLocation);
begin
  // nothing - override in descendent
end;

procedure TMsXmlSaxHandler.endPrefixMapping(var prefix: WideString);
begin
  // nothing
end;

procedure TMsXmlSaxHandler.error(const oLocator: IVBSAXLocator;
  var strErrorMessage: WideString; nErrorCode: Integer);
begin
  raise Exception.Create('todo');
end;

procedure TMsXmlSaxHandler.fatalError(const oLocator: IVBSAXLocator;
  var strErrorMessage: WideString; nErrorCode: Integer);
begin
  raise Exception.Create('todo');
end;

function TMsXmlSaxHandler.GetIDsOfNames(const IID: TGUID; Names: Pointer; NameCount, LocaleID: Integer; DispIDs: Pointer): HResult;
begin
  Result := E_NOTIMPL;
end;

function TMsXmlSaxHandler.GetTypeInfo(Index, LocaleID: Integer; out TypeInfo): HResult;
begin
  Result := E_NOTIMPL;
end;

function TMsXmlSaxHandler.GetTypeInfoCount(out Count: Integer): HResult;
begin
  Result := E_NOTIMPL;
end;

procedure TMsXmlSaxHandler.ignorableWarning(const oLocator: IVBSAXLocator;
  var strErrorMessage: WideString; nErrorCode: Integer);
begin
  raise Exception.Create('todo');
end;

procedure TMsXmlSaxHandler.ignorableWhitespace(var text: WideString);
begin
  // nothing
end;

function TMsXmlSaxHandler.Invoke(DispID: Integer; const IID: TGUID; LocaleID: Integer; Flags: Word; var Params; VarResult, ExcepInfo, ArgErr: Pointer): HResult;
begin
  Result := E_NOTIMPL;
end;

procedure TMsXmlSaxHandler.processingInstruction(var target, data: WideString);
begin
  // nothing
end;

procedure TMsXmlSaxHandler.Set_documentLocator(const locator: IVBSAXLocator);
begin
  FLocator := locator;
end;

procedure TMsXmlSaxHandler.skippedEntity(var name: wideString);
begin
  // ignore
end;

procedure TMsXmlSaxHandler.startDocument;
begin
  // ignore
end;

procedure TMsXmlSaxHandler.startElement(sourceLocation : TSourceLocation; uri, localname: string; attrs: IVBSAXAttributes);
begin
  // override in descendents
end;

procedure TMsXmlSaxHandler.startElement(var uri, localname, qname: widestring; const attrs: IVBSAXAttributes);
begin
  FLocation.Line := FLocator.lineNumber;
  FLocation.col := FLocator.columnNumber;
  startElement(FLocation, uri, localname, attrs);
end;

procedure TMsXmlSaxHandler.startPrefixMapping(var prefix, uri: widestring);
begin
  // ignore
end;

procedure TMsXmlSaxHandler.text(chars: String; sourceLocation : TSourceLocation);
begin
  // for descendents
end;

End.


