unit FHIRLang;

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
interface

function GetFhirMessage(id, lang : String):String; overload;
function GetFhirMessage(id, lang, def : String):String; overload;

procedure LoadMessages;

implementation

{$R FHIRTranslations.res}

uses
  SysUtils, classes, ActiveX, Generics.Collections,
  StringSupport,
  AdvObjects, AdvGenerics, AdvExceptions, AfsResourceVolumes, AfsVolumes,
  MsXml, MsXmlParser;

Type
  TFHIRMessage = class (TAdvObject)
  private
    FMessages : TDictionary<String,String>;
  public
    constructor Create; override;
    Destructor Destroy; override;
  end;
var
  GMessages : TAdvMap<TFHIRMessage>;


Function LoadXml(stream : TStream) : IXmlDomDocument2;
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
    raise Exception.Create(sError);
  End;
  Result := iDom;
end;



Function LoadSource : TBytes;
var
  LRes : TAfsResourceVolume;
  LHnd : TAfsHandle;
begin
  LRes := TAfsResourceVolume.create;
  try
    LHnd := LRes.Open('FHIR_Translations,#10', amRead, asRead);
    try
      SetLength(result, LRes.GetSize(LHnd));
      LRes.Read(LHnd, result[0], length(result));
    finally
      LRes.Close(LHnd);
    end;
  finally
    LRes.Free;
  end;
end;

procedure LoadMessages;
var
  source : IXMLDOMDocument;
  child, lang : IXMLDOMElement;
  stream : TStream;
  msg : TFHIRMessage;
begin
  stream := TBytesStream.Create(LoadSource);
  try
    source := LoadXml(stream);
  finally
    stream.Free;
  end;
  GMessages := TAdvMap<TFHIRMessage>.create;
  child := TMsXmlParser.FirstChild(source.documentElement);
  while child <> nil do
  begin
    msg := TFHIRMessage.Create;
    GMessages.Add(child.getAttribute('id'), msg);
    lang := TMsXmlParser.FirstChild(child);
    while lang <> nil do
  begin
      msg.FMessages.Add(lang.getAttribute('lang'), lang.text);
      lang := TMsXmlParser.NextSibling(lang);
    end;
    child := TMsXmlParser.NextSibling(child);
  end;
end;

function GetFhirMessage(id, lang : String):String;
var
  msg : TFHIRMessage;
  l : string;
begin
  result := '';
  if GMessages = nil then
    LoadMessages;
  if not GMessages.ContainsKey(id) then
    result := 'Unknown message '+id
  else
  begin
    msg := GMessages[id];
    while (result = '') and (lang <> '') do
    begin
      StringSplit(lang, [';', ','], l, lang);
      if msg.FMessages.ContainsKey(l) then
        result := msg.FMessages[l];
    end;
    if result = '' then
      result := msg.FMessages['en'];
    if result = '' then
      result := '??';
  end;
end;

function GetFhirMessage(id, lang, def : String):String;
var
  msg : TFHIRMessage;
  l : string;
begin
  result := '';
  if GMessages = nil then
    LoadMessages;
  if not GMessages.ContainsKey(id) then
    result := def
  else
  begin
    msg := GMessages[id];
    while (result = '') and (lang <> '') do
    begin
      StringSplit(lang, [';', ','], l, lang);
      if msg.FMessages.ContainsKey(l) then
        result := msg.FMessages[l];
    end;
    if result = '' then
      result := msg.FMessages['en'];
    if result = '' then
      result := '??';
    if result = '' then
      result := def;
  end;
end;

{ TFHIRMessage }

constructor TFHIRMessage.Create;
begin
  inherited;
  FMessages := TDictionary<String,String>.create;
end;

destructor TFHIRMessage.Destroy;
begin
  FMessages.Free;
  inherited;
end;

initialization
  GMessages := nil;
finalization
  GMessages.Free;
end.
