unit FHIRLang;

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
