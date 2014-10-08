unit AdvXmlBuilders;

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

interface

Uses
  SysUtils, Classes, ActiveX, Variants,
  StringSupport, EncodeSupport,
  AdvStreams, AdvVCLStreams,  AdvBuffers, AdvObjects, AdvXmlFormatters, AdvMemories, AdvStringMatches, AdvGenerics,
  XmlBuilder, IdSoapMsXml, MsXmlParser, Xml.xmlintf, Xml.XMLDoc, Xml.adomxmldom;

Type
  TAdvXmlBuilder = class (TXmlBuilder)
  private
    mem : TAdvMemoryStream;
    buf : TAdvBuffer;
    xml : TAdvXMLFormatter;

    depth : integer;
    started : boolean;
    function getNSRep(uri, name : String):String;
    function nsIsUsed(elem : IXmlNode; ns : String) : boolean;
    Procedure defineNamespace(element, attribute : IXMLNode);
  Public
    destructor Destroy; override;

    procedure defineNS(abbrev, uri : String);

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
    Procedure WriteXmlNode(iNode : IXMLDOMNode); overload; override;
    procedure ProcessingInstruction(sName, sText : String); override;
    procedure DocType(sText : String); override;
    procedure CData(text : String);

    Procedure WriteXml(iElement : IXMLNode; first : boolean); override;
    Procedure WriteXmlNode(iNode : IXMLNode; first : boolean); override;
    Procedure WriteXmlDocument(iDoc : IXMLDocument); override;
  End;

  // http://www.w3.org/TR/2012/WD-xml-c14n2-testcases-20120105/
  TAdvXmlBuilderCanonicalizationTests = class (TAdvObject)
  private
    class procedure check(source : String; can : TXmlCanonicalisationMethodSet; target : String);
    class procedure Test1;
    class procedure Test2;
    class procedure Test3;
    class procedure Test4;
    class procedure Test5;

  public
    class procedure Test;
  end;

implementation

Procedure TAdvXmlBuilder.Start;
var
  i: Integer;
begin
  buf := TAdvBuffer.Create;
  mem := TAdvMemoryStream.Create;
  mem.Buffer := buf.Link;
  xml := TAdvXMLFormatter.Create;
  xml.HasWhitespace := IsPretty;
  xml.Stream := mem.Link;
  if Canonicalise <> [] then
  begin
    NoHeader := true;
    IsPretty := false;
    CharEncoding := 'UTF-8';
    xml.NoDense := true;
    xml.Attributes.SortedBySortKey;
  end;
  if not NoHeader then
  begin
    xml.AddAttribute('encoding', 'UTF-8');
    xml.ProduceHeader;
  end;
  depth := 0;

  for i := 0 to CurrentNamespaces.Count - 1 do
    xml.AddNamespace(CurrentNamespaces.ValueByIndex[i], CurrentNamespaces.KeyByIndex[i]);
end;

Procedure TAdvXmlBuilder.StartFragment;
begin
  raise Exception.Create('Not Supported yet');
end;

Procedure TAdvXmlBuilder.Finish;
begin
  xml.Free;
  xml := nil;
  mem.Free;
  mem := nil;
end;

function TAdvXmlBuilder.getNSRep(uri, name: String): String;
begin
  if (uri = CurrentNamespaces.DefaultNS) then
    result := name
  else if CurrentNamespaces.ExistsByKey(uri) then
    result := CurrentNamespaces.Matches[uri]+':'+name
  else
    raise Exception.Create('Unregistered namespace '+uri);
end;

function TAdvXmlBuilder.nsIsUsed(elem: IXmlNode; ns: String): boolean;
var
  i : integer;
begin
  result := false;
  if elem.NamespaceURI = ns then
    result := true
  else
    for i := 0 to elem.AttributeNodes.Count - 1 do
      result := result or (elem.AttributeNodes[i].NamespaceURI = ns);

  for i := 0 to elem.ChildNodes.Count - 1 do
    if elem.ChildNodes[i].NodeType = ntElement then
      result := result or nsIsUsed(elem.ChildNodes[i], ns);
end;

Procedure TAdvXmlBuilder.Build(oStream: TStream);
begin
  buf.SaveToStream(oStream);
end;

Procedure TAdvXmlBuilder.Build(oStream: TAdvStream);
begin
  buf.SaveToStream(oStream);
end;

Function TAdvXmlBuilder.Build : String;
begin
  result := buf.AsUnicode;
end;

Function TAdvXmlBuilder.SourceLocation : TSourceLocation;
begin
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

Procedure TAdvXmlBuilder.Comment(Const sContent : String);
begin
  if not (xcmCanonicalise in FCanonicalise) or (xcmComments in FCanonicalise) then
  begin
    if (xcmCanonicalise in FCanonicalise) and started and (depth = 0) then
      xml.Produce(#10);
    xml.ProduceComment(sContent);
    started := true;
  end;
end;


procedure TAdvXmlBuilder.defineNamespace(element, attribute: IXMLNode);
var
  ns : String;
begin
  if attribute.NodeValue = Null then
    ns := ''
  else
    ns := attribute.NodeValue;

  if not (xcmCanonicalise in FCanonicalise) then
    xml.AddAttribute(attribute.NodeName, ns)
  else if attribute.NodeName = 'xmlns' then
  begin
    if CurrentNamespaces.DefaultNS <> ns then
    begin
      CurrentNamespaces.DefaultNS := ns;
      CurrentNamespaces.DefaultSet := false; // duck the hook
      xml.AddAttribute(attribute.NodeName, ns);
    end;
  end
  else if nsIsUsed(element, ns) then
    xml.AddAttribute(attribute.NodeName, ns)
end;

procedure TAdvXmlBuilder.defineNS(abbrev, uri: String);
begin
  CurrentNamespaces.Add(uri, abbrev);
end;

destructor TAdvXmlBuilder.destroy;
begin
  buf.Free;
  inherited;
end;

procedure TAdvXmlBuilder.DocType(sText: String);
begin
  if not (xcmCanonicalise in FCanonicalise) then
    raise Exception.Create('Not supported');
end;

Procedure TAdvXmlBuilder.AddAttribute(Const sName, sValue : String);
begin
  if (sName = 'xmlns') and CurrentNamespaces.DefaultSet then
  begin
    if sValue <> CurrentNamespaces.DefaultNS then
      raise Exception.Create('Namespace mismatch');
    CurrentNamespaces.DefaultSet := false;
  end;

  xml.AddAttribute(sName, sValue);
end;

Procedure TAdvXmlBuilder.AddAttributeNS(Const sNamespace, sName, sValue : String);
begin
  xml.AddAttribute(getNSRep(sNamespace, sName), sValue, sNamespace);
end;

function TAdvXmlBuilder.Tag(Const sName : String) : TSourceLocation;
begin
  if (xcmCanonicalise in FCanonicalise) and started and (depth = 0) then
    xml.Produce(#10);
  if CurrentNamespaces.DefaultSet then
  begin
    xml.AddNamespace('', CurrentNamespaces.DefaultNS);
    CurrentNamespaces.DefaultSet := false;
  end;
  if xcmCanonicalise in FCanonicalise then
  begin
    xml.ProduceOpen(sName);
    xml.ProduceClose(sName);
  end
  else
    xml.ProduceTag(sName);
  started := true;
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

function TAdvXmlBuilder.Open(Const sName : String) : TSourceLocation;
begin
  if (xcmCanonicalise in FCanonicalise) and started and (depth = 0) then
    xml.Produce(#10);
  if CurrentNamespaces.DefaultSet then
  begin
    xml.AddNamespace('', CurrentNamespaces.DefaultNS);
    CurrentNamespaces.DefaultSet := false;
  end;
  xml.ProduceOpen(sName);
  inc(depth);
  started := true;
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

procedure TAdvXmlBuilder.ProcessingInstruction(sName, sText: String);
begin
  if (xcmCanonicalise in FCanonicalise) and started and (depth = 0) then
    xml.Produce(#10);
  xml.ProducePI(sName, sText);
  started := true;
end;

procedure TAdvXmlBuilder.CData(text: String);
begin
  if xcmCanonicalise in FCanonicalise then
    xml.ProduceText(text, eolnCanonical)
  else
    xml.produceCData(text)
end;

Procedure TAdvXmlBuilder.Close(Const sName : String);
begin
  xml.ProduceClose(sName);
  dec(depth);
end;

function HtmlTrim(s: String):String;
begin
  if s = '' then
    result := ''
  else
  begin
    result := StringTrimSet(s, [' ', #13, #10, #9]);
    if result = '' then
      result := ' ';
  end;
end;

function TAdvXmlBuilder.Text(Const sValue : String) : TSourceLocation;
begin
  if (xcmCanonicalise in FCanonicalise) then
  begin
    if (depth = 0) then
     // ignore
    else if xcmTrimWhitespace in FCanonicalise then
      xml.ProduceText(sValue.Trim, eolnCanonical)
    else
      xml.ProduceText(sValue, eolnCanonical);
  end
  else if IsPretty then
    xml.ProduceText(HtmlTrim(sValue))
  else
    xml.ProduceText(sValue);
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

procedure TAdvXmlBuilder.WriteXml(iElement: IXMLDomElement);
var
  attr : IXMLDOMNode;
  a: Integer;
begin
  if iElement.attributes <> nil then
    for a := 0 to iElement.attributes.length - 1 do
    begin
      attr := iElement.attributes[a];
      AddAttribute(attr.nodeName, attr.nodeValue);
    end;

  if iElement.childNodes.length = 0 then
    Tag(iElement.nodeName)
  else
  begin
    Open(iElement.nodeName);
    WriteXmlNode(iElement);
    Close(iElement.nodeName);
  end;
end;

procedure TAdvXmlBuilder.WriteXmlNode(iNode: IXMLDOMNode);
var
  n : IXMLDOMNode;
  i : integer;
begin
  for i := 0 to iNode.childNodes.length - 1  do
  begin
    n := iNode.childNodes[i];
    case n.nodeType of
      NODE_ELEMENT : WriteXml(n as IXMlDOmElement);
      NODE_COMMENT : Comment(n.text);
      NODE_TEXT : Text(n.text);
      NODE_PROCESSING_INSTRUCTION :
        begin
//        if n.attributes <> nil then
//          for a := 0 to n.attributes.length - 1 do
//          begin
//            attr := n.attributes[a];
//            AddAttribute(attr.nodeName, attr.nodeValue);
//          end;
        ProcessingInstruction(n.nodeName, n.text);
        end;
    else
      raise Exception.Create('Unhandled node type on document: '+inttostr(n.nodeType));
//  NODE_CDATA_SECTION = $00000004;
//  NODE_ENTITY_REFERENCE = $00000005;
//  NODE_ENTITY = $00000006;
//  NODE_PROCESSING_INSTRUCTION = $00000007;
//  NODE_DOCUMENT = $00000009;
//  NODE_DOCUMENT_TYPE = $0000000A;
//  NODE_DOCUMENT_FRAGMENT = $0000000B;
//  NODE_NOTATION = $0000000C;
//      NODE_ATTRIBUTE = $00000002;
//      NODE_INVALID = $00000000;
    end;
  end;
end;

function TAdvXmlBuilder.Entity(Const sValue : String) : TSourceLocation;
begin
  raise Exception.Create('entities not supported');
end;

function TAdvXmlBuilder.TagText(Const sName, sValue : String) : TSourceLocation;
begin
  if CurrentNamespaces.DefaultSet then
  begin
    xml.AddNamespace('', CurrentNamespaces.DefaultNS);
    CurrentNamespaces.DefaultSet := false;
  end;
  if IsPretty then
    xml.ProduceText(sName, StringTrimWhitespace(sValue))
  else
    xml.ProduceText(sName, sValue);
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

{ TAdvXmlBuilderCanonicalizationTests }

class procedure TAdvXmlBuilderCanonicalizationTests.check(source: String; can: TXmlCanonicalisationMethodSet; target: String);
var
  doc : IXMLDocument;
  dom : TXMLDocument;
  xb : TAdvXmlBuilder;
  s : String;
begin
  dom := TXMLDocument.Create(nil);
  doc := dom;
  dom.DOMVendor := OpenXML4Factory;
  dom.ParseOptions := [poPreserveWhiteSpace];
  dom.Options := [{doNodeAutoCreate, doNodeAutoIndent, doAttrNull,  doAutoPrefix, doAutoSave} doNamespaceDecl];
  doc.LoadFromXML(source);

  xb := TAdvXmlBuilder.Create;
  try
    xb.Canonicalise := can;
    xb.Start;
    xb.WriteXmlDocument(doc);
    xb.Finish;
    s := xb.Build;
  finally
    xb.Free;
  end;
  if s <> target then
    raise Exception.Create('Mismatch');
end;

class procedure TAdvXmlBuilderCanonicalizationTests.Test;
begin
  Test1;
  Test2;
  Test3;
  Test4;
  Test5;
end;

class procedure TAdvXmlBuilderCanonicalizationTests.Test1;
var
  s : String;
begin
  s :=
'<?xml version="1.0"?>'+#13#10+
''+#13#10+
'<?xml-stylesheet   href="doc.xsl"'+#13#10+
'   type="text/xsl"   ?>'+#13#10+
''+#13#10+
'<!DOCTYPE doc SYSTEM "doc.dtd">'+#13#10+
''+#13#10+
'<doc>Hello, world!<!-- Comment 1 --></doc>'+#13#10+
''+#13#10+
'<?pi-without-data     ?>'+#13#10+
''+#13#10+
'<!-- Comment 2 -->'+#13#10+
''+#13#10+
'<!-- Comment 3 -->'+#13#10+
''+#13#10;

check(s, [xcmCanonicalise],
'<?xml-stylesheet href="doc.xsl"'+#10+
'   type="text/xsl"   ?>'+#10+
'<doc>Hello, world!</doc>'+#10+
'<?pi-without-data?>');

check(s, [xcmCanonicalise, xcmComments],
'<?xml-stylesheet href="doc.xsl"'+#10+
'   type="text/xsl"   ?>'+#10+
'<doc>Hello, world!<!-- Comment 1 --></doc>'+#10+
'<?pi-without-data?>'+#10+
'<!-- Comment 2 -->'+#10+
'<!-- Comment 3 -->');
end;

class procedure TAdvXmlBuilderCanonicalizationTests.Test2;
var
  s : String;
begin
  s :=
'<doc>'+#13#10+
'   <clean>   </clean>'+#13#10+
'   <dirty>   A   B   </dirty>'+#13#10+
'   <mixed>'+#13#10+
'      A'+#13#10+
'      <clean>   </clean>'+#13#10+
'      B'+#13#10+
'      <dirty>   A   B   </dirty>'+#13#10+
'      C'+#13#10+
'   </mixed>'+#13#10+
'</doc>'+#13#10;

check(s, [xcmCanonicalise],
'<doc>'+#10+
'   <clean>   </clean>'+#10+
'   <dirty>   A   B   </dirty>'+#10+
'   <mixed>'+#10+
'      A'+#10+
'      <clean>   </clean>'+#10+
'      B'+#10+
'      <dirty>   A   B   </dirty>'+#10+
'      C'+#10+
'   </mixed>'+#10+
'</doc>');

check(s, [xcmCanonicalise, xcmTrimWhitespace],
'<doc><clean></clean><dirty>A   B</dirty><mixed>A<clean></clean>B<dirty>A   B</dirty>C</mixed></doc>');
end;

class procedure TAdvXmlBuilderCanonicalizationTests.Test3;
var
  s : String;
begin
  s :=
'<doc>'+#13#10+
'   <e1   />'+#13#10+
'   <e2   ></e2>'+#13#10+
'   <e3   name = "elem3"   id="elem3"   />'+#13#10+
'   <e4   name="elem4"   id="elem4"   ></e4>'+#13#10+
'   <e5 a:attr="out" b:attr="sorted" attr2="all" attr="I''m"'+#13#10+
'      xmlns:b="http://www.ietf.org"'+#13#10+
'      xmlns:a="http://www.w3.org"'+#13#10+
'      xmlns="http://example.org"/>'+#13#10+
'   <e6 xmlns="" xmlns:a="http://www.w3.org">'+#13#10+
'      <e7 xmlns="http://www.ietf.org">'+#13#10+
'         <e8 xmlns="" xmlns:a="http://www.w3.org">'+#13#10+
'            <e9 xmlns="" xmlns:a="http://www.ietf.org"/>'+#13#10+
'         </e8>'+#13#10+
'      </e7>'+#13#10+
'   </e6>'+#13#10+
'</doc>'+#13#10;

  check(s, [xcmCanonicalise],
'<doc>'+#10+
'   <e1></e1>'+#10+
'   <e2></e2>'+#10+
'   <e3 id="elem3" name="elem3"></e3>'+#10+
'   <e4 id="elem4" name="elem4"></e4>'+#10+
'   <e5 xmlns="http://example.org" xmlns:a="http://www.w3.org" xmlns:b="http://www.ietf.org" attr="I''m" attr2="all" b:attr="sorted" a:attr="out"></e5>'+#10+
'   <e6>'+#10+
'      <e7 xmlns="http://www.ietf.org">'+#10+
'         <e8 xmlns="">'+#10+
'            <e9></e9>'+#10+
'         </e8>'+#10+
'      </e7>'+#10+
'   </e6>'+#10+
'</doc>'
  );
//  check(s, [xcmCanonicalise, xcmPrefixRewrite],
//'<n0:doc xmlns:n0="">'+#10+
//'   <n0:e1></n0:e1>'+#10+
//'   <n0:e2></n0:e2>'+#10+
//'   <n0:e3 id="elem3" name="elem3"></n0:e3>'+#10+
//'   <n0:e4 id="elem4" name="elem4"></n0:e4>'+#10+
//'   <n1:e5 xmlns:n1="http://example.org" xmlns:n2="http://www.ietf.org" xmlns:n3="http://www.w3.org" attr="I''m" attr2="all" n2:attr="sorted" n3:attr="out"></n1:e5>'+#10+
//'   <n0:e6>'+#10+
//'      <n2:e7 xmlns:n2="http://www.ietf.org">'+#10+
//'         <n0:e8>'+#10+
//'            <n0:e9></n0:e9>'+#10+
//'         </n0:e8>'+#10+
//'      </n2:e7>'+#10+
//'   </n0:e6>'+#10+
//'</n0:doc>'+#10
//  );

check(s, [xcmCanonicalise, xcmTrimWhitespace],
'<doc><e1></e1><e2></e2><e3 id="elem3" name="elem3"></e3><e4 id="elem4" name="elem4"></e4><e5 xmlns="http://example.org" xmlns:a="http://www.w3.org" xmlns:b="http://www.ietf.org" attr="I''m" attr2="all" '+'b:attr="sorted" a:attr="out"></e5><e6><e7 xmlns="http://www.ietf.org"><e8 xmlns=""><e9></e9></e8></e7></e6></doc>'
);
end;

class procedure TAdvXmlBuilderCanonicalizationTests.Test4;
var
  s : String;
begin
  s :=
'<doc>'+#13#10+
'   <text>First line&#x0d;&#10;Second line</text>'+#13#10+
'   <value>&#x32;</value>'+#13#10+
'   <compute><![CDATA[value>"0" && value<"10" ?"valid":"error"]]></compute>'+#13#10+
'   <compute expr=''value>"0" &amp;&amp; value&lt;"10" ?"valid":"error"''>valid</compute>'+#13#10+
'   <norm attr='' &apos;   &#x20;&#13;&#xa;&#9;   &apos; ''/>'+#13#10+
'   <normNames attr=''   A   &#x20;&#13;&#xa;&#9;   B   ''/>'+#13#10+
'   <normId id='' &apos;&#x20;&#13;&#xa;&#9; &apos; ''/>'+#13#10+
'</doc>'+#13#10;

  check(s, [xcmCanonicalise],
'<doc>'+#10+
'   <text>First line&#xD;'+#10+
'Second line</text>'+#10+
'   <value>2</value>'+#10+
'   <compute>value&gt;"0" &amp;&amp; value&lt;"10" ?"valid":"error"</compute>'+#10+
'   <compute expr="value>&quot;0&quot; &amp;&amp; value&lt;&quot;10&quot; ?&quot;valid&quot;:&quot;error&quot;">valid</compute>'+#10+
'   <norm attr=" ''    &#xD;&#xA;&#x9;   '' "></norm>'+#10+
'   <normNames attr="   A    &#xD;&#xA;&#x9;   B   "></normNames>'+#10+
'   <normId id=" '' &#xD;&#xA;&#x9; '' "></normId>'+#10+
'</doc>');
check(s, [xcmCanonicalise, xcmTrimWhitespace],
'<doc><text>First line&#xD;'+#10+
'Second line</text><value>2</value><compute>value&gt;"0" &amp;&amp; value&lt;"10" ?"valid":"error"</compute><compute expr="value>&quot;0&quot; &amp;&amp; value&lt;&quot;10&quot; ?&quot;valid&quot;:&quot;error&quot;">'+
  'valid</compute><norm attr=" ''    &#xD;&#xA;&#x9;   '' "></norm><normNames attr="   A    &#xD;&#xA;&#x9;   B   "></normNames><normId id=" '' &#xD;&#xA;&#x9; '' "></normId></doc>'
);
end;

class procedure TAdvXmlBuilderCanonicalizationTests.Test5;
var
  s : String;
begin
  s :=
'<?xml version="1.0" encoding="ISO-8859-1"?>'+#13#10+
'<doc>&#169;</doc>'+#13#10;

  check(s, [xcmCanonicalise],
'<doc>©</doc>');

end;

procedure TAdvXmlBuilder.WriteXml(iElement: IXMLNode; first : boolean);
var
  attr : IXMLNode;
  a: Integer;
begin
  NSPush;

  if first then
  begin
    if (iELement.NamespaceURI <> '') and (iELement.LocalName = iELement.NodeName) then
      // we are inheriting a default namespaces
      CurrentNamespaces.DefaultNS := iELement.NamespaceURI;
  end;


  if iElement.AttributeNodes <> nil then
    for a := 0 to iElement.AttributeNodes.Count - 1 do
    begin
      attr := iElement.attributeNodes[a];
      if attr.nodeName.StartsWith('xmlns') then
        DefineNamespace(iElement, attr)
      else if attr.nodeValue = Null then
        AddAttribute(attr.nodeName, '')
      else

        AddAttribute(attr.nodeName, DecodeXML(attr.nodeValue));
    end;

  if iElement.childNodes.count = 0 then
    Tag(iElement.localName)
  else
  begin
    Open(iElement.localName);
    writeXmlNode(iElement, false);
    Close(iElement.localName);
  end;
  NSPop;
end;

procedure TAdvXmlBuilder.WriteXmlDocument(iDoc: IXMLDocument);
var
  n : IXMLNode;
  i : integer;
begin
  for i := 0 to iDoc.childNodes.count - 1 do
  begin
    n := iDoc.childNodes[i];
    case n.nodeType of
      ntElement : WriteXml(n, false);
      ntComment : Comment(n.text);
      ntText : Text(n.text);
      ntDocType : DocType(n.text);
      ntProcessingInstr : ProcessingInstruction(n.nodeName, n.text);
    else
      raise Exception.Create('Unhandled node type on document: '+inttostr(ord(n.nodeType)));
    end;
  end;
end;

{ntReserved, ntElement, ntAttribute, ntText, ntCData,
    ntEntityRef, ntEntity, ntProcessingInstr, ntComment, ntDocument,
    ntDocType, ntDocFragment, ntNotation);}


procedure TAdvXmlBuilder.WriteXmlNode(iNode : IXMLNode; first : boolean);
var
  n : IXMLNode;
  i : integer;
begin
  for i := 0 to inode.childNodes.count - 1 do
  begin
    n := inode.childNodes[i];
    case n.nodeType of
      ntElement : WriteXml(n, first);
      ntComment : Comment(n.text);
      ntText : Text(n.text);
      ntProcessingInstr : ProcessingInstruction(n.nodeName, n.Text);
      ntCData : CData(n.text);
    else
      raise Exception.Create('Unhandled node type on document: '+inttostr(ord(n.nodeType)));
    end;
  end;
end;


end.
