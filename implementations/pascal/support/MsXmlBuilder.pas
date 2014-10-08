unit MsXmlBuilder;

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
  SysUtils, Classes,
  StringSupport, EncodeSupport, TextUtilities,
  AdvStreams, AdvVCLStreams,  AdvObjects,
  IdSoapMsXml, XmlBuilder, Xml.XmlIntf;

Type
  TMsXmlBuilder = class (TXmlBuilder)
  private
    FExternal : Boolean;
    FDom : IXMLDomDocument2;
    FFragment : IXMLDOMDocumentFragment;
    FStack : TInterfaceList;
    FAttributes : TInterfaceList;
    FSourceLocation : TSourceLocation;
    Function Pad(offset : integer = 0) : String;
    function ReadTextLength(s : string):String;
    function ReadTextLengthWithEscapes(pfx, s, sfx : string):String;
  Public
    Constructor Create; Override;
    Procedure Start(oNode : IXmlDomNode); overload;
    Procedure Start(); overload; override;
    Procedure StartFragment; override;
    Procedure Finish; override;
    Procedure Build(oStream: TStream);  Overload; override;
    Procedure Build(oStream: TAdvStream);  Overload; override;
    Function Build : String;  Overload; override;

    Function SourceLocation : TSourceLocation; override;
    Procedure Comment(Const sContent : String); override;
    Procedure AddAttribute(Const sName, sValue : String); override;
    Procedure AddAttributeNS(Const sNamespace, sName, sValue : String); override;
    function Tag(Const sName : String) : TSourceLocation; override;
    function Open(Const sName : String) : TSourceLocation; override;
    Procedure Close(Const sName : String); override;
    function Text(Const sValue : String) : TSourceLocation; override;
    function Entity(Const sValue : String) : TSourceLocation; override;
    function TagText(Const sName, sValue : String) : TSourceLocation; override;
    Procedure WriteXml(iElement : IXMLDomElement); override;
    procedure ProcessingInstruction(sName, sText : String); override;
    procedure DocType(sText : String); override;
    Procedure WriteXmlNode(iDoc : IXMLDOMNode); override;

    Procedure WriteXml(iElement : IXMLNode; first : boolean); override;
    Procedure WriteXmlNode(iDoc : IXMLNode; first : boolean); override;
    Procedure WriteXmlDocument(iDoc : IXMLDocument); overload; override;
  End;

Implementation

Uses
  ActiveX,
  IdSoapXml,
  ComObj;


{ TMsXmlBuilder }

function TMsXmlBuilder.SourceLocation: TSourceLocation;
begin
  result.line := 0;
  result.col := 0;
end;

procedure TMsXmlBuilder.Start(oNode : IXmlDomNode);
begin
  FStack := TInterfaceList.Create;
  FAttributes := TInterfaceList.Create;
  if oNode = nil Then
  Begin
    FDom := LoadMsXMLDom;
    if CharEncoding <> '' Then
      FDom.appendChild(FDom.createProcessingInstruction('xml', 'version="1.0" encoding="'+CharEncoding+'"'));
    FStack.Add(FDom);
  End
  else
  Begin
    FDom := oNode.ownerDocument as IXMLDomDocument2;
    FStack.Add(oNode);
  End;
  FSourceLocation.line := 0;
  FSourceLocation.col := 0;
end;

procedure TMsXmlBuilder.Build(oStream: TAdvStream);
var
  oVCL : TVCLStream;
Begin
  oVCL := TVCLStream.Create;
  Try
    oVCL.Stream := oStream.Link;
    Build(oVCL);
  Finally
    oVCL.Free;
  End;
End;

procedure TMsXmlBuilder.Finish;
Begin
  if FStack.Count > 1 Then
    Error('Close', 'Document is not finished');
  FStack.Free;
  FStack := nil;
  FAttributes.Free;
  FAttributes := nil;
End;

procedure TMsXmlBuilder.Build(oStream: TStream);
Var
  vAdapter : Variant;
  s : String;
begin
  assert(FAttributes = nil);
  assert(not FExternal);

  vAdapter := TStreamAdapter.Create(oStream) As IStream;
  if FFragment <> nil then
  begin
    s := FFragment.text;
    if s <> '' then
      oStream.write(s[1], length(s));
  end
  else
  begin
    FDom.save(vAdapter);
    FDom := nil;
  end;
end;


function HasElements(oElem : IXMLDOMElement) : Boolean;
var
  oChild : IXMLDOMNode;
Begin
  Result := False;
  oChild := oElem.firstChild;
  While Not result and (oChild <> nil) Do
  Begin
    result := oChild.nodeType = NODE_ELEMENT;
    oChild := oChild.nextSibling;
  End;
End;

function TMsXmlBuilder.Open(const sName: String) : TSourceLocation;
var
  oElem : IXMLDOMElement;
  oParent : IXMLDOMNode;
  iLoop : integer;
  len : integer;
begin
  if CurrentNamespaces.DefaultNS <> '' Then
  begin
    oElem := FDom.createNode(NODE_ELEMENT, sName, CurrentNamespaces.DefaultNS) as IXMLDOMElement;
    len := length(sName)+3
  end
  Else
  begin
    oElem := FDom.createElement(sName) as IXMLDOMElement;
    len := length(sName);
  end;
  if FStack.Count > 0 then
  Begin
    oParent := FStack[FStack.Count - 1] as IXMLDOMNode;
    if IsPretty and (oParent.NodeType = NODE_ELEMENT) Then
      oParent.appendChild(FDom.createTextNode(ReadTextLength(#13#10+pad)));
    oParent.appendChild(oElem);
  End
  else
    FDom.appendChild(oElem);
  inc(FSourceLocation.col, len+2);
  for iLoop := 0 to FAttributes.Count - 1 Do
    oElem.attributes.setNamedItem(FAttributes[iLoop] as IXMLDOMAttribute);
  FAttributes.Clear;
  FStack.Add(oElem);
  result.line := FSourceLocation.line;
  result.col := FSourceLocation.col;
end;

procedure TMsXmlBuilder.Close(const sName: String);
var
  oElem : IXMLDOMElement;
begin
  if IsPretty Then
  Begin
    oElem := FStack[FStack.Count - 1] as IXMLDOMElement;
    If HasElements(oElem) Then
      oElem.appendChild(FDom.createTextNode(readTextLength(#13#10+pad(-1))));
  End;
  FStack.Delete(FStack.Count - 1)
end;


procedure TMsXmlBuilder.AddAttribute(const sName, sValue: String);
var
  oAttr : IXMLDOMAttribute;
begin
  oAttr := FDom.createAttribute(sName);
  oAttr.value := sValue;
  FAttributes.Add(oAttr);
  ReadTextLengthWithEscapes(sName+'="', sValue, '"');
end;

function TMsXmlBuilder.Text(const sValue: String) : TSourceLocation;
var
  oElem : IXMLDOMNode;
begin
  oElem := FStack[FStack.Count - 1] as IXMLDOMNode;
  oElem.appendChild(FDom.createTextNode(ReadTextLengthWithEscapes('', sValue, '')));
  result.line := FSourceLocation.line;
  result.col := FSourceLocation.col;
end;

function TMsXmlBuilder.Entity(const sValue: String) : TSourceLocation;
var
  oElem : IXMLDOMElement;
begin
  oElem := FStack[FStack.Count - 1] as IXMLDOMElement;
  oElem.appendChild(FDom.createEntityReference(sValue));
  inc(FSourceLocation.col, length(sValue)+2);
  result.line := FSourceLocation.line;
  result.col := FSourceLocation.col;
end;

function TMsXmlBuilder.Tag(const sName: String) : TSourceLocation;
begin
  Open(sName);
  Close(sName);
  result.line := FSourceLocation.line;
  result.col := FSourceLocation.col;
end;

function TMsXmlBuilder.TagText(const sName, sValue: String) : TSourceLocation;
begin
  result := Open(sName);
  if (sValue <> '') Then
    Text(sValue);
  Close(sName);
end;

function TMsXmlBuilder.Pad(offset : integer = 0): String;
var
  iLoop : integer;
begin
  Setlength(result, ((FStack.Count - 1) + offset) * 2);
  For iLoop := 1 to Length(Result) Do
    result[iLoop] := ' ';
end;


procedure TMsXmlBuilder.ProcessingInstruction(sName, sText: String);
begin
  raise Exception.Create('Not supported yet');
end;

function TMsXmlBuilder.ReadTextLength(s: string): String;
var
  i : integer;
begin
  i := 1;
  while i <= length(s) do
  begin
    if CharInSet(s[i], [#10, #13]) then
    begin
      inc(FSourceLocation.line);
      FSourceLocation.col := 0;
      if (i < length(s)) and (s[i+1] <> s[i]) and CharInSet(s[i+1], [#10, #13]) then
        inc(i);
    end
    else
      inc(FSourceLocation.col);
    inc(i);
  end;
end;

function TMsXmlBuilder.ReadTextLengthWithEscapes(pfx, s, sfx: string): String;
begin
  ReadTextLength(pfx);
  ReadTextLength(EncodeXml(s, xmlText));
  ReadTextLength(sfx);
end;

Procedure TMsXmlBuilder.Comment(Const sContent : String);
var
  oElem : IXMLDOMNode;
begin
  oElem := FStack[FStack.Count - 1] as IXMLDOMNode;
  if IsPretty and (oElem.nodeType = NODE_ELEMENT) Then
    oElem.appendChild(FDom.createTextNode(ReadTextLength(#13#10+pad)));
  oElem.appendChild(FDom.createComment(sContent));
  ReadTextLength('<!--'+sContent+'-->');
End;


function TMsXmlBuilder.Build: String;
var
  oStream : TStringStream;
begin
  oStream := TStringStream.Create('');
  Try
    Build(oStream);
    Result := oStream.DataString;
  Finally
    oStream.Free;
  End;
end;

constructor TMsXmlBuilder.Create;
begin
  inherited;
  CurrentNamespaces.DefaultNS := 'urn:hl7-org:v3';
  CharEncoding := 'UTF-8';
end;

procedure TMsXmlBuilder.DocType(sText: String);
begin
  raise Exception.Create('Not supported yet');
end;

procedure TMsXmlBuilder.WriteXml(iElement: IXMLDomElement);
var
  i : integer;
  oElem : IXMLDOMElement;
begin
  oElem := FStack[FStack.Count - 1] as IXMLDOMElement;
  For i := 0 to iELement.childNodes.length - 1 do
    if (iElement.childNodes[i].nodeType <> NODE_TEXT) or not StringIsWhitespace(iElement.childNodes[i].text) Then
      oElem.appendChild(iElement.childNodes[i].CloneNode(true));
end;

procedure TMsXmlBuilder.AddAttributeNS(const sNamespace, sName, sValue: String);
var
  oAttr : IXMLDOMAttribute;
begin
  oAttr := FDom.createNode(NODE_ATTRIBUTE, sName, sNamespace) as IXMLDOMAttribute;
  oAttr.value := sValue;
  FAttributes.Add(oAttr);
  ReadTextLengthWithEscapes('s1:'+sName+'="', sValue, '"');
end;

procedure TMsXmlBuilder.Start;
begin
  FStack := TInterfaceList.Create;
  FAttributes := TInterfaceList.Create;
  FDom := LoadMsXMLDom;
  if CharEncoding <> '' Then
    FDom.appendChild(FDom.createProcessingInstruction('xml', 'version="1.0" encoding="'+CharEncoding+'"'));
  FStack.Add(FDom);
  FSourceLocation.line := 0;
  FSourceLocation.col := 0;
end;

procedure TMsXmlBuilder.StartFragment;
begin
  FStack := TInterfaceList.Create;
  FAttributes := TInterfaceList.Create;
  FDom := LoadMsXMLDom;
  FFragment := FDom.createDocumentFragment;
  FStack.Add(FFragment);
  FSourceLocation.line := 0;
  FSourceLocation.col := 0;
end;

procedure TMsXmlBuilder.WriteXml(iElement: IXMLNode; first : boolean);
begin
  raise Exception.Create('Not supported yet');
end;

procedure TMsXmlBuilder.WriteXmlDocument(iDoc: IXMLDocument);
begin
  raise Exception.Create('Not supported yet');
end;

procedure TMsXmlBuilder.WriteXmlNode(iDoc: IXMLDOMNode);
begin
  raise Exception.Create('Not supported yet');
end;

procedure TMsXmlBuilder.WriteXmlNode(iDoc: IXMLNode; first : boolean);
begin
  raise Exception.Create('Not supported yet');
end;

End.
