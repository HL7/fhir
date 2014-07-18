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
  SysUtils, Classes,
  StringSupport, EncodeSupport,
  AdvStreams, AdvVCLStreams,  AdvBuffers, AdvObjects, AdvXmlFormatters, AdvMemories, AdvStringMatches,
  XmlBuilder, IdSoapMsXml;

Type
  TAdvXmlBuilder = class (TXmlBuilder)
  private
    mem : TAdvMemoryStream;
    buf : TAdvBuffer;
    xml : TAdvXMLFormatter;
    wantDefineNS : boolean;
    namespaces : TAdvStringMatch;
    function getNSRep(uri, name : String):String;
  protected
    procedure SetNamespace(const Value: String); override;
  Public
    constructor Create; override;
    destructor Destroy; override;

    procedure defineNS(abbrev, uri : String);

    Procedure Start(); overload; override;
    Procedure StartFragment; override;
    Procedure Finish; override;
    Procedure Build(oStream: TStream);  Overload; override;
    Procedure Build(oStream: TAdvStream);  Overload; override;
    Function Build : String;  Overload; override;

    Function SourceLocation : TSourceLocation; override;
    Procedure Comment(Const sContent : WideString); override;
    Procedure AddAttribute(Const sName, sValue : WideString); override;
    Procedure AddAttributeNS(Const sNamespace, sName, sValue : WideString); override;
    function Tag(Const sName : WideString) : TSourceLocation; override;
    function Open(Const sName : WideString) : TSourceLocation; override;
    Procedure Close(Const sName : WideString); override;
    function Text(Const sValue : WideString) : TSourceLocation; override;
    function Entity(Const sValue : WideString) : TSourceLocation; override;
    function TagText(Const sName, sValue : WideString) : TSourceLocation; override;
    Procedure WriteXml(iElement : IXMLDomElement); override;
  End;


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
  if not NoHeader then
  begin
  xml.AddAttribute('encoding', 'UTF-8');
  xml.ProduceHeader;
  end;
  wantDefineNS := Namespace <> '';
  for i := 0 to namespaces.Count - 1 do
    xml.AddNamespace(namespaces.ValueByIndex[i], namespaces.KeyByIndex[i]);
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
  if (uri = namespace) then
    result := name
  else if namespaces.ExistsByKey(uri) then
    result := namespaces.Matches[uri]+':'+name
  else
    raise Exception.Create('Unregistered namespace '+uri);            
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

Procedure TAdvXmlBuilder.Comment(Const sContent : WideString); 
begin
  xml.ProduceComment(sContent);
end;

constructor TAdvXmlBuilder.create;
begin
  inherited;
  namespaces := TAdvStringMatch.Create;
end;

procedure TAdvXmlBuilder.defineNS(abbrev, uri: String);
begin
  namespaces.Add(uri, abbrev);
end;

destructor TAdvXmlBuilder.destroy;
begin
  namespaces.Free;
  buf.Free;
  inherited;
end;

Procedure TAdvXmlBuilder.AddAttribute(Const sName, sValue : WideString); 
begin
  xml.AddAttribute(sName, sValue);
end;

Procedure TAdvXmlBuilder.AddAttributeNS(Const sNamespace, sName, sValue : WideString); 
begin
  xml.AddAttribute(getNSRep(sNamespace, sName), sValue);
end;

function TAdvXmlBuilder.Tag(Const sName : WideString) : TSourceLocation; 
begin
  if wantDefineNS then
  begin
    xml.AddNamespace('', Namespace);
    wantDefineNS := false;
  end;
  xml.ProduceTag(sName);
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

function TAdvXmlBuilder.Open(Const sName : WideString) : TSourceLocation;
begin
  if wantDefineNS then
  begin
    xml.AddNamespace('', Namespace);
    wantDefineNS := false;
  end;
  xml.ProduceOpen(sName);
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

Procedure TAdvXmlBuilder.Close(Const sName : WideString);
begin
  wantDefineNS := false;
  xml.ProduceClose(sName);
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

function TAdvXmlBuilder.Text(Const sValue : WideString) : TSourceLocation;
begin
  if IsPretty then
    xml.ProduceText(HtmlTrim(sValue))
  else
    xml.ProduceText(sValue);
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;

procedure TAdvXmlBuilder.WriteXml(iElement: IXMLDomElement);
begin
  raise Exception.Create('Not supported yet');
end;

function TAdvXmlBuilder.Entity(Const sValue : WideString) : TSourceLocation;
begin
  raise Exception.Create('entities not supported');
end;

function TAdvXmlBuilder.TagText(Const sName, sValue : WideString) : TSourceLocation;
begin
  if wantDefineNS then
  begin
    xml.AddNamespace('', Namespace);
    wantDefineNS := false;
  end;
  if IsPretty then
    xml.ProduceText(sName, StringTrimWhitespace(sValue))
  else
    xml.ProduceText(sName, sValue);
  result.line := 0; //xml.line;
  result.col := 0; //xml.col;
end;


(*function TAdvXmlBuilder.setDefaultNS(uri: String): String;
begin
  result := defnamespace;
  defnamespace := uri;
  xml.AddNamespace('', uri);
end;

procedure TAdvXmlBuilder.unsetDefaultNS(uri: String);
begin
  defnamespace := uri;
end;
*)


procedure TAdvXmlBuilder.SetNamespace(const Value: String);
begin
  inherited;
  wantDefineNS := true;
end;

end.
