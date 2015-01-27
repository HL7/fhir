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

implementation

{$R FHIRTranslations.res}

uses
  SysUtils, classes,
  StringSupport,
  AdvExceptions,
  AfsResourceVolumes,
  AfsVolumes,

  IdSoapXml;

var
  GMessages : TStringList;
  GSource : TIdSoapXmlDom;



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
  child : TIdSoapXmlElement;
  stream : TStream;
begin
  GSource := IdSoapDomFactory(xpCustom);
  stream := TBytesStream.Create(LoadSource);
  try
    GSource.Read(stream);
  finally
    stream.Free;
  end;
  GMessages := TStringList.create;
  GMessages.Sorted := true;
  child := GSource.Root.FirstChild;
  while child <> nil do
  begin
    GMessages.addObject(child.getAttribute('', 'id'), child);
    child := child.NextSibling;
  end;
end;

Function GetBylang(e : TIdSoapXmlElement; lang : String) : String;
var
  c : TIdSoapXmlElement;
begin
  c := e.FirstChild;
  while (result = '') and (c <> nil) do
  begin
    if (c.getAttribute('', 'lang') = lang) then
      result := c.TextContentA;
    c := c.NextSibling;
  end;
end;

function GetFhirMessage(id, lang : String):String;
var
  i : integer;
  e : TIdSoapXmlElement;
  l : string;
begin
  result := '';
  if GMessages = nil then
    LoadMessages;
  i := GMessages.indexof(id);
  if i = -1 then
    result := 'Unknown message '+id
  else
  begin
    e := GMessages.Objects[i] as TIdSoapXmlElement;
    while (result = '') and (lang <> '') do
    begin
      StringSplit(lang, [';', ','], l, lang);
      result := getBylang(e, l);
    end;
    if result = '' then
      result := getByLang(e, 'en');
    if result = '' then
      result := '??';
  end;
end;

function GetFhirMessage(id, lang, def : String):String;
var
  i : integer;
  e : TIdSoapXmlElement;
  l : string;
begin
  if GMessages = nil then
    LoadMessages;
  i := GMessages.indexof(id);
  if i = -1 then
    result := def
  else
  begin
    e := GMessages.Objects[i] as TIdSoapXmlElement;
    while (result = '') and (lang <> '') do
    begin
      StringSplit(lang, [';', ','], l, lang);
      result := getBylang(e, l);
    end;
    if result = '' then
      result := getByLang(e, 'en');
    if result = '' then
      result := def;
  end;
end;

initialization
  GMessages := nil;
  GSource := nil;
finalization
  GMessages.Free;
  GSource.Free;
end.
