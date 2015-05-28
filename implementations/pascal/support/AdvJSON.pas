Unit AdvJSON;

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

uses
  SysUtils,
  Classes,
  AdvStreams,
  AdvVCLStreams,
  BytesSupport,
  AdvObjects,
  AdvTextFormatters,
  AdvTextExtractors,
  AdvStringObjectMatches,
  AdvObjectLists,
  AdvStringBuilders;

Function JSONString(const value : String) : String;

Type
  TJsonObject = class;
  TJsonArray = class;

  TJsonNode = class (TAdvObject)
  private
    FPath: String;
  protected
    function nodeType : String; virtual;
  public
    constructor create(path : String); overload;
    Function Link : TJsonNode; Overload;
    property path : String read FPath write FPath;
  end;

  TJsonProperties = class (TAdvStringObjectMatch)
  private
    function GetProp(const aKey: String): TJsonNode;
    procedure SetProp(const aKey: String; const Value: TJsonNode);
  protected
    Function ItemClass : TAdvObjectClass; override;
  public
    Function Link : TJsonProperties; Overload;
    Property Prop[Const aKey : String] : TJsonNode Read GetProp Write SetProp; Default;
  end;

  TJsonArrayEnumerator = class (TAdvObject)
  private
    FArray : TJsonArray;
    cursor : integer;
    function GetCurrent: TJsonObject;
  public
    Destructor Destroy; Override;
    function MoveNext() : boolean;
    Property Current : TJsonObject read GetCurrent;
  end;

  TJsonArray = class (TJsonNode)
  private
    FItems : TAdvObjectList;
    function GetCount: integer;
    function GetItem(i: integer): TJsonNode;
    function GetObj(i: integer): TJsonObject;
    function GetValue(i: integer): String;
    procedure SetItem(i: integer; const Value: TJsonNode);
    procedure SetObj(i: integer; const Value: TJsonObject);
    procedure SetValue(i: integer; const Value: String);
  protected
    function nodeType : String; override;
  public
    constructor Create; override;
    destructor Destroy; override;
    Function Link : TJsonArray; Overload;

    Property Count : integer read GetCount;
    Property Item[i : integer] : TJsonNode read GetItem write SetItem;
    Property Obj[i : integer] : TJsonObject read GetObj write SetObj; default;
    Property Value[i : integer] : String read GetValue write SetValue;

    function add(value : String): TJsonArray; overload;
    function add(value : TJsonObject): TJsonArray; overload;
    function addObject : TJsonObject; overload;

    function GetEnumerator : TJsonArrayEnumerator; // can only use this when the array members are objects
  end;

  TJsonNull = class (TJsonNode);

  TJsonBoolean = class (TJsonNode)
  private
    FValue: boolean;
  protected
    function nodeType : String; override;
  public
    Constructor Create(path : String; value : boolean); overload;
    Function Link : TJsonBoolean; Overload;
    property value : boolean read FValue write FValue;
  end;

  TJsonValue = class (TJsonNode)
  private
    FValue: String;
  protected
    function nodeType : String; override;
  public
    Constructor Create(path : String; value : string); overload;
    Function Link : TJsonValue; Overload;
    property value : String read FValue write FValue;
  end;

  TJsonObject = class (TJsonNode)
  private
    FName : String;
    FProperties : TJsonProperties;
    function GetString(name: String): String;
    function GetArray(name: String): TJsonArray;
    function GetObject(name: String): TJsonObject;
    procedure SetString(name: String; const Value: String);
    function GetBool(name: String): boolean;
    procedure SetBool(name: String; const Value: boolean);
    function GetForcedObject(name: String): TJsonObject;
    procedure SetArray(name: String; const Value: TJsonArray);
    procedure SetObject(name: String; const Value: TJsonObject);
    function GetForcedArray(name: String): TJsonArray;
  protected
    function nodeType : String; override;
  public
    constructor Create; override;
    destructor Destroy; override;
    Function Link : TJsonObject; Overload;

    Function has(name : String) : Boolean;
    Function isNull(name : String) : Boolean;

    Property str[name : String] : String read GetString write SetString; default;
    Property bool[name : String] : boolean read GetBool write SetBool;
    Property arr[name : String] : TJsonArray read GetArray write SetArray;
    Property obj[name : String] : TJsonObject read GetObject write SetObject;

    // legacy, until the FHIR code is regenerated
    Property vStr[name : String] : String read GetString write SetString;
    Property vBool[name : String] : boolean read GetBool write SetBool;
    Property vArr[name : String] : TJsonArray read GetArray write SetArray;
    Property vObj[name : String] : TJsonObject read GetObject write SetObject;

    Property forceObj[name : String] : TJsonObject read GetForcedObject;
    Property forceArr[name : String] : TJsonArray read GetForcedArray;
    procedure clear(name : String = '');

    Property name : String read FName write FName;
    Property properties : TJsonProperties read FProperties;
  end;

  TJSONWriter = class (TAdvTextFormatter)
  private
    FBuilder : TAdvStringBuilder;
    FName : String;
    FCache : String;
    Function UseName : String;
    Function UseCache : String;
    Function JSONString(const value : String) : String;
    procedure DoName(const name : String);
  Public
    Constructor Create; Override;
    Destructor Destroy; Override;
    Function Link: TJSONWriter; overload;
    Procedure Start;
    Procedure Finish;

    Procedure Value(Const name : String; Const avalue : String); overload;
    Procedure ValueNumber(Const name : String; Const avalue : String); overload;
    Procedure Value(Const name : String; avalue : Boolean); overload;
    Procedure Value(Const name : String; avalue : Integer); overload;
    Procedure Value(Const name : String; avalue : Int64); overload;
    Procedure Value(Const name : String; avalue : Double); overload;
    Procedure ValueDate(Const name : String; aValue : TDateTime); overload;
    Procedure ValueNull(Const name : String);

    Procedure ValueObject(Const name : String); Overload;
    Procedure ValueObject; Overload;
    Procedure FinishObject;

    Procedure ValueArray(Const name : String);
    Procedure FinishArray;

    Procedure ValueInArray(Const value : String); overload;
    Procedure ValueNumberInArray(Const value : String); overload;
    Procedure ValueInArray(value : Boolean); overload;
    Procedure ValueInArray(value : Integer); overload;
    Procedure ValueInArray(value : Int64); overload;
    Procedure ValueInArray(value : Double); overload;
    Procedure ValueDateInArray(aValue : TDateTime); overload;
    Procedure ValueNullInArray;

    Procedure WriteObject(name : String; obj : TJsonObject); overload;
    Procedure WriteObjectInner(obj : TJsonObject);
    Procedure WriteArray(name : String; arr : TJsonArray);

    class Function writeObject(obj : TJsonObject; pretty : boolean = false) : TBytes; overload;
    class Function writeObjectStr(obj : TJsonObject; pretty : boolean = false) : String; overload;
    class Procedure writeObject(stream : TStream; obj : TJsonObject; pretty : boolean = false); overload;
    class Procedure writeObject(stream : TAdvStream; obj : TJsonObject; pretty : boolean = false); overload;
  End;


  TJSONLexType = (jltOpen, jltClose, jltString, jltNumber, jltColon, jltComma, jltOpenArray, jltCloseArray, jltEof, jltNull, jltBoolean);

  TJSONLexer = class (TAdvTextExtractor)
  Private
    FPeek : String;
    FValue: String;
    FLexType: TJSONLexType;
    FStates : TStringList;
    Function getNextChar : Char;
    Procedure Push(ch : Char);
    procedure ParseWord(sWord : String; ch : Char; aType : TJSONLexType);
    Procedure JsonError(sMsg : String);
    Function Path : String;
  Public
    Constructor Create(oStream : TAdvStream); Overload;
    Destructor Destroy; Override;
    Procedure Start;
    Property LexType : TJSONLexType read FLexType;
    Property Value : String read FValue;
    Procedure Next;
    Function Consume(aType : TJsonLexType):String;

  End;

  TJsonParserItemType = (jpitObject, jpitSimple, jpitBoolean, jpitArray, jpitEnd, jpitEof, jpitNull);

  TJSONParser = class (TAdvObject)
  Private
    FLex : TJSONLexer;
    FItemName: String;
    FItemValue: String;
    FItemType: TJsonParserItemType;
    Procedure Start;
    Procedure ParseProperty;
    Procedure SkipInner;
    function GetItemValue: String;
    function GetItemNull: boolean;
    procedure readObject(obj : TJsonObject; root : boolean);
    procedure readArray(arr : TJsonArray);
  Public
    Constructor Create(oStream : TStream); Overload;
    Constructor Create(oStream : TAdvStream);  Overload;
    Destructor Destroy; Override;
    Property ItemType : TJsonParserItemType read FItemType;
    Property ItemName : String read FItemName;
    Property ItemValue : String read GetItemValue;
    Property ItemNull : boolean read GetItemNull;
    Procedure Next;
    Procedure Skip;
    Procedure JsonError(sMsg : String);
    Procedure CheckState(aState : TJsonParserItemType);
    class Function Parse(stream : TAdvStream): TJsonObject; overload;
    class Function Parse(stream : TStream): TJsonObject; overload;
    class Function Parse(b : TBytes): TJsonObject; overload;
    class Function Parse(s : String): TJsonObject; overload;
  End;

Const
  Codes_TJsonParserItemType : Array[TJsonParserItemType] of String = ('Object', 'Simple', 'Boolean', 'Array', 'End', 'EOF', 'Null');
  Codes_TJSONLexType : Array[TJSONLexType] of String = ('Open', 'Close', 'String', 'Number', 'Colon', 'Comma', 'OpenArray', 'CloseArray', 'Eof', 'Null', 'Boolean');

function JsonBoolToString(b : boolean) : String;
function JsonStringToBool(s : String; def : boolean = false) : boolean;

Implementation


{ TJSONWriter }

Constructor TJSONWriter.Create;
Begin
  Inherited ;
  FBuilder := TAdvStringBuilder.Create;
End;

Destructor TJSONWriter.Destroy;
Begin
  FBuilder.Free;
  Inherited;
End;

procedure TJSONWriter.Start;
begin
  ProduceLine('{');
  LevelDown;
end;

procedure TJSONWriter.Finish;
begin
  if FCache <> '' Then
    ProduceLine(UseCache);
  LevelUp;
  Assert(Level = 0);
  ProduceLine('}');
end;

Function TJSONWriter.JSONString(const value : String) : String;
var
  i : integer;
Begin
  FBuilder.Clear;
  FBuilder.Append('"');
  for i := 1 to length(value) do
    case value[i] of
      '"':FBuilder.Append('\"');
      '\':FBuilder.Append('\\');
      #13:FBuilder.Append('\r');
      #10:FBuilder.Append('\n');
      #09:FBuilder.Append('\t');
    else if ord(value[i]) < 32 Then
      FBuilder.Append('\u'+inttohex(ord(value[i]), 4))
    else
      FBuilder.Append(value[i]);
    End;
  FBuilder.Append('"');
  result := FBuilder.AsString;
End;

Function JSONString(const value : String) : String;
var
  i : integer;
Begin
  result := '';
  for i := 1 to length(value) do
    case value[i] of
      '"':result := result + '\"';
      '\':result := result + '\\';
      #13:result := result + '\r';
      #10:result := result + '\n';
      #09:result := result + '\t';
    else if ord(value[i]) < 32 Then
      result := result + '\u'+inttohex(ord(value[i]), 4)
    else
      result := result + value[i];
    End;
End;

procedure TJSONWriter.DoName(const name : String);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  FName := JSONString(name)+' : ';
end;

procedure TJSONWriter.Value(const name : String; const avalue: String);
begin
  if name = '' then
    valueInArray(avalue)
  else if avalue = '' then
    ValueNull(name)
  Else
  Begin
    DoName(Name);
    FCache := UseName + JSONString(avalue);
  End;
end;

procedure TJSONWriter.Value(const name : String; avalue: Boolean);
begin
  if name = '' then
    valueInArray(avalue)
  else
  begin
    DoName(name);
    if avalue then
      FCache := UseName + 'true'
    else
      FCache := UseName + 'false';
  end;
end;

procedure TJSONWriter.ValueNull(const name : String);
begin
  if name = '' then
    ValueNullInArray
  else
  begin
    DoName(name);
    FCache := UseName + 'null';
  end;
end;

procedure TJSONWriter.Value(const name : String; avalue: Int64);
begin
  if name = '' then
    valueInArray(avalue)
  else
  begin
    DoName(name);
    FCache := UseName + inttostr(avalue);
  end;
end;

procedure TJSONWriter.Value(const name : String; avalue: Double);
begin
  if name = '' then
    valueInArray(avalue)
  else
  begin
    DoName(name);
    FCache := UseName + FloatToStr(avalue);
  end;
end;

procedure TJSONWriter.Value(const name : String; avalue: Integer);
begin
  if name = '' then
    valueInArray(avalue)
  else
  begin
    DoName(name);
    FCache := UseName + inttostr(avalue);
  end;
end;


procedure TJSONWriter.ValueObject(const name : String);
begin
  if (name = '') then
    ValueObject
  else
  begin
    DoName(name);
    ProduceLine(UseName+ '{');
    LevelDown;
  end;
end;

procedure TJSONWriter.ValueObject;
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  ProduceLine(UseName+ '{');
  LevelDown;
end;

class procedure TJSONWriter.writeObject(stream: TAdvStream; obj: TJsonObject; pretty : boolean = false);
var
  this : TJSONWriter;
begin
  this := TJSONWriter.Create;
  try
    this.HasWhitespace := pretty;
    this.Stream := stream.Link;
    this.Start;
    this.writeObjectInner(obj);
    this.Finish;
  finally
    this.Free;
  end;
end;

procedure TJSONWriter.WriteArray(name: String; arr: TJsonArray);
var
  i : integer;
  v : TJsonNode;
begin
  ValueArray(name);
  for i := 0 to arr.FItems.Count - 1 do
  begin
    v := arr.Fitems[i] as TJsonNode;
    if v is TJsonArray then
      WriteArray('', v as TJsonArray)
    else if v is TJsonNull then
      ValueNull('')
    else if v is TJsonValue then
      Value('', (v as TJsonValue).FValue)
    else // TJsonObject
      WriteObject('', v as TJsonObject);
  end;
  FinishArray;
end;

procedure TJSONWriter.WriteObjectInner(obj: TJsonObject);
var
  names : TStringList;
  i : integer;
  n : String;
  v : TJsonNode;
begin
  names := TStringList.Create;
  try
    for i := 0 to obj.properties.Count - 1 do
      names.add(obj.properties.Keys[i]);
    names.sort;
    for n in names do
    begin
      v := obj.properties[n] as TJsonNode;
      if v is TJsonArray then
        WriteArray(n, v as TJsonArray)
      else if v is TJsonNull then
        ValueNull(n)
      else if v is TJsonBoolean then
        Value(n, TJsonBoolean(v).FValue)
      else if v is TJsonValue then
        Value(n, (v as TJsonValue).FValue)
      else if v is  TJsonObject then
        WriteObject(n, v as TJsonObject)
      else
        raise Exception.Create('Unexpected object type '+v.nodeType);
    end;
  finally
    names.free;
  end;
end;

procedure TJSONWriter.WriteObject(name : String; obj: TJsonObject);
begin
  ValueObject(name);
  WriteObjectInner(obj);
  FinishObject;
end;

class function TJSONWriter.writeObjectStr(obj: TJsonObject; pretty: boolean): String;
begin
  result := TEncoding.UTF8.GetString(writeObject(obj, pretty));
end;

class function TJSONWriter.writeObject(obj: TJsonObject; pretty: boolean): TBytes;
var
  mem : TBytesStream;
begin
  mem := TBytesStream.Create;
  try
    writeObject(mem, obj, pretty);
    result := mem.Bytes;
    SetLength(result, mem.size);
  finally
    mem.Free
  end;
end;

class procedure TJSONWriter.writeObject(stream: TStream; obj: TJsonObject; pretty: boolean);
var
  s : TAdvVCLStream;
begin
  s := TAdvVCLStream.Create;
  try
    s.Stream := stream;
    writeObject(s, obj, pretty);
  finally
    s.Free;
  end;
end;

procedure TJSONWriter.FinishObject;
begin
  if FCache <> '' Then
    ProduceLine(UseCache);
  LevelUp;
  Assert(Level >= 0);
  FCache := '}';
end;


procedure TJSONWriter.ValueArray(const name : String);
begin
  DoName(name);
  ProduceLine(UseName + '[');
  LevelDown;
end;

procedure TJSONWriter.FinishArray;
begin
  if FCache <> '' Then
    ProduceLine(UseCache);
  LevelUp;
  Assert(Level >= 0);
  FCache := ']';
end;


function TJSONWriter.UseName: String;
begin
  result := FName;
  FName := '';
end;

function TJSONWriter.UseCache: String;
begin
  result := FCache;
  FCache := '';
end;

procedure TJSONWriter.ValueDate(const name : String; aValue: TDateTime);
begin
  if aValue = 0 then
    ValueNull(Name)
  Else
    Value(name, FormatDateTime('yyyymmddhhnnss.zzz', aValue));
end;

function TJSONWriter.Link: TJSONWriter;
begin
  result := TJSONWriter(Inherited Link);
end;

procedure TJSONWriter.ValueDateInArray(aValue: TDateTime);
begin
  if aValue = 0 then
    ValueNullInArray
  Else
    ValueInArray(FormatDateTime('yyyymmddhhnnss.zzz', aValue));
end;

{ TJSONLexer }

procedure TJSONLexer.Start;
var
  ch : char;
begin
  ch := getNextChar();
  if ch = char($EF) then
  begin
    // skip BOM
    getNextChar();
    getNextChar();
  end
  else
    push(ch);
  Next;
end;

procedure TJSONLexer.ParseWord(sWord : String; ch : Char; aType : TJSONLexType);
Begin
  FLexType := aType;
  FValue := ch;
  While More and (Length(FValue) < length(sWord)) and (FValue = copy(sWord, 1, length(FValue))) Do
    FValue := FValue + getNextChar;
  if FValue <> sWord Then
    JsonError('Syntax error in json reading special word '+sWord);
End;

procedure TJSONLexer.Next;
var
  ch : Char;
begin
  repeat
    ch := getNextChar;
  Until Not More Or not CharInSet(ch, [' ', #13, #10, #9]);

  If Not More Then
    FLexType := jltEof
  Else case ch of
    '{' : FLexType := jltOpen;
    '}' : FLexType := jltClose;
    '"' :
      Begin
      FLexType := jltString;
      FValue := '';
      repeat
        ch := getNextChar;
        if (ch = '\') Then
        Begin
          if not More then
            JsonError('premature termination of json stream during a string');
          ch := getNextChar;
          case ch of
            '"':FValue := FValue + '"';
            '\':FValue := FValue + '\';
            '/':FValue := FValue + '/';
            'n':FValue := FValue + #10;
            'r':FValue := FValue + #13;
            't':FValue := FValue + #09;
          Else
            JsonError('not supported: \'+ch);
          End;
          ch := #0;
        End
        Else if (ch <> '"') then
          FValue := FValue + ch;
      until not More or (ch = '"');
      if ch <> '"' Then
        JsonError('premature termination of json stream during a string');
      End;
    ':' : FLexType := jltColon;
    ',' : FLexType := jltComma;
    '[' : FLexType := jltOpenArray;
    ']' : FLexType := jltCloseArray;
    't' : ParseWord('true', ch, jltBoolean);
    'f' : ParseWord('false', ch, jltBoolean);
    'n' : ParseWord('null', ch, jltNull);
    '0'..'9', '-' :
      Begin
      FLexType := jltNumber;
      FValue := '';
      while More and CharInSet(ch, ['0'..'9', '.', '-']) do
      Begin
        FValue := FValue + ch;
        ch := getNextChar;
      End;
      push(ch);
      End;
  Else
    JsonError('Unexpected char "'+ch+'" in json stream');
  End;
end;

function TJSONLexer.getNextChar: Char;
begin
  if FPeek <> '' Then
  Begin
    result := FPeek[1];
    Delete(FPeek, 1, 1);
  End
  Else
    result := ConsumeCharacter;
end;

function TJSONLexer.Consume(aType: TJsonLexType): String;
begin
  if FLexType <> aType Then
    JsonError('JSON syntax error - found '+Codes_TJSONLexType[FLexType]+' expecting '+Codes_TJSONLexType[aType]);
  result := FValue;
  Next;
end;

procedure TJSONLexer.Push(ch: Char);
begin
  insert(ch, FPeek, 1);
end;

constructor TJSONLexer.Create(oStream: TAdvStream);
begin
  Inherited Create(oStream, TEncoding.UTF8, true, 12000);
  FStates := TStringList.Create;
end;

destructor TJSONLexer.Destroy;
begin
  FStates.Free;
  inherited;
end;

procedure TJSONLexer.JsonError(sMsg: String);
begin
  Raise Exception.Create('Error parsing JSON source: '+sMsg+' at Line '+inttostr(Line)+' (path=['+Path+'])');
end;

function TJSONLexer.Path: String;
var
  i : integer;
begin
  if FStates.count = 0 then
    result := FValue
  else
  begin
    result := '';
    for i := FStates.count-1 downto 1 do
      result := result + '/'+FStates[i];
    result := result + FValue;
  end;
end;

{ TJSONParser }

constructor TJSONParser.Create(oStream: TStream);
var
  oVCLStream : TAdvVclStream;
begin
  inherited Create;
  oVCLStream := TAdvVCLStream.Create;
  Try
    oVCLStream.Stream := oStream;
    FLex := TJSONLexer.Create(oVCLStream.Link);
  Finally
    oVCLStream.Free;
  End;
  Start;
end;

procedure TJSONParser.CheckState(aState: TJsonParserItemType);
begin
  if FItemType <> aState Then
    JsonError('Unexpected state. Expected '+Codes_TJsonParserItemType[aState]+', but found '+Codes_TJsonParserItemType[FItemType]);
end;

constructor TJSONParser.Create(oStream: TAdvStream);
begin
  inherited Create;
  FLex := TJSONLexer.Create(oStream.Link);
  Start;
end;

function TJSONParser.GetItemNull: boolean;
begin
  result := false;
end;

function TJSONParser.GetItemValue: String;
begin
  if not (FItemType in [jpitBoolean, jpitSimple]) Then
    FLex.JSONError('Attempt to read a simple value, but state is '+Codes_TJsonParserItemType[FItemType]);

  result := FItemValue;
end;

procedure TJSONParser.JsonError(sMsg: String);
begin
  FLex.JsonError(sMsg);
end;

procedure TJSONParser.Next;
begin
  case FItemType of
    jpitObject :
      Begin
      FLex.Consume(jltOpen);
      FLex.FStates.InsertObject(0, ItemName, nil);
      if FLex.LexType = jltClose then
      begin
        FItemType := jpitEnd;
        FLex.Next;
      end
      else
        ParseProperty;
      End;
    jpitNull, jpitSimple, jpitEnd, jpitBoolean :
      Begin
      if FItemType = jpitEnd Then
        FLex.FStates.Delete(0);
      if FLex.LexType = jltComma then
      Begin
        FLex.Next;
        ParseProperty;
      End
      Else if FLex.LexType = jltClose Then
      Begin
        FItemType := jpitEnd;
        FLex.Next;
      End
      Else if FLex.LexType = jltCloseArray Then
      Begin
        FItemType := jpitEnd;
        FLex.Next;
      End
      Else if FLEx.LexType = jltEof then
        FItemType := jpitEof
      Else
        FLex.JsonError('JSON Syntax Error');
      End;
    jpitArray :
      Begin
      FLex.next;
      FLex.FStates.InsertObject(0, ItemName+'[]', Self);
      ParseProperty;
      End;
    jpitEof :
        FLex.JsonError('JSON Syntax Error - attempt to read past end of json stream');
  else
    FLex.JsonError('not done yet: '+Codes_TJsonParserItemType[ItemType]);
  End;
end;

class function TJSONParser.Parse(stream: TAdvStream): TJsonObject;
var
  p : TJSONParser;
begin
  p := TJSONParser.Create(stream);
  try
    result := TJsonObject.Create('$');
    try
      p.readObject(result, true);
      result.Link;
    finally
      result.Free;
    end;
  finally
    p.Free;
  end;
end;

class function TJSONParser.Parse(stream: TStream): TJsonObject;
var
  p : TJSONParser;
begin
  p := TJSONParser.Create(stream);
  try
    result := TJsonObject.Create('$');
    try
      p.readObject(result, true);
      result.Link;
    finally
      result.Free;
    end;
  finally
    p.Free;
  end;
end;

class function TJSONParser.Parse(s: String): TJsonObject;
begin
  result := Parse(TEncoding.UTF8.GetBytes(s));
end;

procedure TJSONParser.ParseProperty;
Begin
  If FLex.FStates.Objects[0] = nil Then
  Begin
    FItemName := FLex.Consume(jltString);
    FItemValue := '';
    FLex.Consume(jltColon);
  End;
  case FLex.LexType of
    jltNull :
      Begin
      FItemType := jpitNull;
      FItemValue := FLex.FValue;
      FLex.Next;
      end;
    jltString :
      Begin
      FItemType := jpitSimple;
      FItemValue := FLex.FValue;
      FLex.Next;
      End;
    jltBoolean :
      Begin
      FItemType := jpitBoolean;
      FItemValue := FLex.FValue;
      FLex.Next;
      End;
    jltNumber :
      Begin
      FItemType := jpitSimple;
      FItemValue := FLex.FValue;
      FLex.Next;
      End;
    jltOpen :
      Begin
      FItemType := jpitObject;
      End;
    jltOpenArray :
      Begin
      FItemType := jpitArray;
      End;
    jltCloseArray :
      begin
      FItemType := jpitEnd;
      End;
    // jltClose, , jltColon, jltComma, jltOpenArray,       !
  else
    FLex.JsonError('not done yet: '+Codes_TJSONLexType[FLex.LexType]);
  End;
End;


procedure TJSONParser.readArray(arr: TJsonArray);
var
  obj : TJsonObject;
  child : TJsonArray;
  i : integer;
begin
  i := 0;
  obj := nil;
  while (ItemType <> jpitEnd) do
  begin
    case ItemType of
      jpitObject:
        begin
          obj := TJsonObject.Create(arr.path+'['+inttostr(i)+']');
          arr.FItems.Add(obj);
          Next;
          readObject(obj, false);
        end;
      jpitSimple:
        arr.FItems.Add(TJsonValue.Create(arr.path+'['+inttostr(i)+']', ItemValue));
      jpitNull :
        arr.FItems.Add(TJsonNull.Create(arr.path+'['+inttostr(i)+']'));
      jpitArray:
        begin
        child := TJsonArray.Create(arr.path+'['+inttostr(i)+']');
        obj.FProperties.Add(ItemName, child);
        Next;
        readArray(child);
        end;
      jpitEof : raise Exception.Create('Unexpected End of File');
    end;
    Next;
    inc(i);
  end;
end;

procedure TJSONParser.readObject(obj: TJsonObject; root : boolean);
var
  child : TJsonObject;
  arr : TJsonArray;
begin
  while not ((ItemType = jpitEnd) or (root and (ItemType = jpitEof))) do
  begin
    case ItemType of
      jpitObject:
        begin
          child := TJsonObject.Create(obj.path+'.'+ItemName);
          obj.FProperties.Add(ItemName, child);
          Next;
          readObject(child, false);
        end;
      jpitBoolean :
        obj.FProperties.Add(ItemName, TJsonBoolean.Create(obj.path+'.'+ItemName, StrToBool(ItemValue)));
      jpitSimple:
        obj.FProperties.Add(ItemName, TJsonValue.Create(obj.path+'.'+ItemName, ItemValue));
      jpitNull:
        obj.FProperties.Add(ItemName, TJsonNull.Create(obj.path+'.'+ItemName));
      jpitArray:
        begin
        arr := TJsonArray.Create(obj.path+'.'+ItemName);
        obj.FProperties.Add(ItemName, arr);
        Next;
        readArray(arr);
        end;
      jpitEof : raise Exception.Create('Unexpected End of File');
    end;
    next;
  end;
end;

procedure TJSONParser.Skip;
begin
  if ItemType = jpitSimple then
    Next
  Else
    SkipInner;
end;

procedure TJSONParser.SkipInner;
begin
  Next;
  While ItemType <> jpitEnd do
  Begin
    Case ItemType of
      jpitObject : SkipInner;
      jpitSimple : Next;
      jpitArray : SkipInner;
    End;
  End;
  Next;
end;

Procedure TJSONParser.Start;
begin
  FLex.Start;
  if FLex.LexType = jltOpen Then
  begin
    FLex.Next;
    FLex.FStates.InsertObject(0, '', nil);
    ParseProperty;
  End
  Else
    FLex.JsonError('Unexpected content at start of JSON: '+Codes_TJSONLexType[FLex.LexType]);
End;

procedure TJSONWriter.ValueInArray(const value: String);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  if value = '' then
    ValueNullInArray
  Else
    FCache := JSONString(value);
end;

procedure TJSONWriter.ValueNumberInArray(const value: String);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  if value = '' then
    ValueNullInArray
  Else
    FCache := value;
end;

procedure TJSONWriter.ValueInArray(value: Boolean);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  if value then
    FCache := 'true'
  else
    FCache := 'false';
end;

procedure TJSONWriter.ValueNullInArray;
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  FCache := 'null';
end;

procedure TJSONWriter.ValueNumber(const name, avalue: String);
begin
  if name = '' then
    valueNumberInArray(avalue)
  else if avalue = '' then
    ValueNull(name)
  Else
  Begin
    DoName(Name);
    FCache := UseName + avalue;
  End;
end;

procedure TJSONWriter.ValueInArray(value: Int64);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  FCache := inttostr(value);
end;

procedure TJSONWriter.ValueInArray(value: Double);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  FCache := FloatToStr(value);
end;

procedure TJSONWriter.ValueInArray(value: Integer);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',');
  FCache := inttostr(value);
end;


destructor TJSONParser.Destroy;
begin
  FLex.free;
  inherited;
end;


class function TJSONParser.Parse(b: TBytes): TJsonObject;
var
  s : TBytesStream;
begin
  s := TBytesStream.Create(b);
  try
    result := Parse(s);
  finally
    s.Free;
  end;
end;

{ TJsonNode }

constructor TJsonNode.create(path: String);
begin
  Create;
  self.path := path;
end;

function TJsonNode.Link: TJsonNode;
begin
  result := TJsonNode(Inherited Link);
end;

function TJsonNode.nodeType: String;
begin
  result := copy(className, 6, $FF);
end;

{ TJsonProperties }

function TJsonProperties.GetProp(const aKey: String): TJsonNode;
begin
  result := Matches[aKey] as TJsonNode;
end;

function TJsonProperties.ItemClass: TAdvObjectClass;
begin
  result := TJsonNode;
end;

function TJsonProperties.Link: TJsonProperties;
begin
  result := TJsonProperties(Inherited Link);
end;

procedure TJsonProperties.SetProp(const aKey: String; const Value: TJsonNode);
begin
  Matches[aKey] := Value;
end;


{ TJsonArray }

function TJsonArray.add(value: String): TJsonArray;
begin
  FItems.Add(TJsonValue.Create(path+'/'+inttostr(FItems.count), value));
  result := self;
end;

function TJsonArray.add(value: TJsonObject): TJsonArray;
begin
  FItems.Add(value);
  result := self;
end;

function TJsonArray.addObject: TJsonObject;
begin
  result := TJsonObject.Create;
  add(result);
end;

constructor TJsonArray.create;
begin
  inherited Create;
  FItems := TAdvObjectList.Create;
end;

destructor TJsonArray.destroy;
begin
  FItems.Free;
  inherited;
end;

function TJsonArray.GetCount: integer;
begin
  if self = nil then
    result := 0
  else
    result := FItems.Count;
end;

function TJsonArray.GetEnumerator: TJsonArrayEnumerator;
begin
  result := TJsonArrayEnumerator.Create;
  result.FArray := self.Link;
  result.cursor := -1;
end;

function TJsonArray.GetItem(i: integer): TJsonNode;
begin
  if (self = nil) or (i >= Count) then
    result := nil
  else
    result := FItems[i] as TJsonNode;
end;

function TJsonArray.GetObj(i: integer): TJsonObject;
begin
  if (self = nil) or (i >= Count) then
    result := nil
  else if FItems[i] is TJsonObject then
    result := FItems[i] as TJsonObject
  else if FItems[i] is TJsonNull then
    result := nil
  else
    raise Exception.Create('Found a property of type '+TJsonNode(FItems[i]).nodeType+' looking for an object at '+path+'['+inttostr(i)+']');
end;

function TJsonArray.GetValue(i: integer): String;
begin
  if (self = nil) or (i >= Count)  then
    result := ''
  else if FItems[i] is TJsonValue then
    result := (FItems[i] as TJsonValue).FValue
  else if FItems[i] is TJsonNull then
    result := ''
  else
    raise Exception.Create('Found a '+nodeType+' expecting a string property at '+path);
end;

function TJsonArray.Link: TJsonArray;
begin
  result := TJsonArray(Inherited Link);
end;

function TJsonArray.nodeType: String;
begin
  result := 'array';
end;

procedure TJsonArray.SetItem(i: integer; const Value: TJsonNode);
begin
  FItems[i] := Value;
end;

procedure TJsonArray.SetObj(i: integer; const Value: TJsonObject);
begin
  FItems[i] := Value;
end;

procedure TJsonArray.SetValue(i: integer; const Value: String);
begin
  FItems[i] := TJsonValue.Create(Path+'['+inttostr(i)+']', Value);
end;

{ TJsonValue }

constructor TJsonValue.Create(path, value: string);
begin
  Create(path);
  self.value := value;
end;

function TJsonValue.Link: TJsonValue;
begin
  result := TJsonValue(Inherited Link);
end;

function TJsonValue.nodeType: String;
begin
  result := 'string/number';
end;

{ TJsonObject }

procedure TJsonObject.clear(name: String);
begin
  if name = '' then
    FProperties.Clear
  else
    FProperties.DeleteByKey(name);
end;

constructor TJsonObject.create;
begin
  inherited Create;
  FProperties := TJsonProperties.Create;
  FProperties.Forced := true;
end;

destructor TJsonObject.destroy;
begin
  FProperties.Free;
  inherited;
end;

function TJsonObject.GetArray(name: String): TJsonArray;
var
  node : TJsonNode;
begin
  if has(name) then
  begin
    node := FProperties[name];
    if node is TJsonArray then
      result := TJsonArray(node)
    else if node is TJsonNull then
      result := nil
    else
      raise Exception.Create('Found a property of '+node.nodeType+' looking for an array at '+path+'.'+name);
  end
  else
    result := nil;
end;

function TJsonObject.GetBool(name: String): boolean;
var
  node : TJsonNode;
begin
  if has(name) then
  begin
    node := FProperties[name];
    if node is TJsonNull then
      result := false
    else if node is TJsonBoolean then
      result := (node as TJsonBoolean).FValue
    else
      raise Exception.Create('Found a property of type '+node.nodeType+' looking for a boolean at '+path+'.'+name);
  end
  else
    result := false;
end;

function TJsonObject.GetForcedArray(name: String): TJsonArray;
begin
  if not properties.ExistsByKey(name) or not (properties[name] is TJsonArray) then
    arr[name] := TJsonArray.Create;
  result := arr[name];
end;

function TJsonObject.GetForcedObject(name: String): TJsonObject;
begin
  if not properties.ExistsByKey(name) or not (properties[name] is TJsonObject) then
    obj[name] := TJsonObject.Create;
  result := obj[name];
end;

function TJsonObject.GetObject(name: String): TJsonObject;
var
  node : TJsonNode;
begin
  if has(name) then
  begin
    node := FProperties[name];
    if node is TJsonObject then
      result := TJsonObject(node)
    else if node is TJsonNull then
      result := nil
    else
      raise Exception.Create('Found a property of type '+node.nodeType+' looking for an object at '+FPath+'.'+name);
  end
  else
    result := nil;
end;

function TJsonObject.GetString(name: String): String;
var
  node : TJsonNode;
begin
  if self = nil then
    result := ''
  else
  begin
    if has(name) then
    begin
      node := FProperties[name];
      if node is TJsonValue then
        result := TJsonValue(node).FValue
      else if node is TJsonNull then
        result := ''
      else if node is TJsonBoolean then
        if (node as TJsonBoolean).FValue then
          result := 'true'
        else
          result := 'false'
      else
        raise Exception.Create('Found a property of type '+node.nodeType+' looking for a string at '+FPath+'.'+name);
    end
    else
      result := '';
  end;
end;

function TJsonObject.has(name: String): Boolean;
begin
  result := FProperties.ExistsByKey(name);
end;

function TJsonObject.isNull(name: String): Boolean;
begin
  result := has(name) and (FProperties.Prop[name] is TJsonNull);
end;

function TJsonObject.Link: TJsonObject;
begin
  result := TJsonObject(Inherited Link);
end;

function TJsonObject.nodeType: String;
begin
  result := 'object';
end;

procedure TJsonObject.SetArray(name: String; const Value: TJsonArray);
begin
  properties.SetProp(name, value);
end;

procedure TJsonObject.SetBool(name: String; const Value: boolean);
var
  v : TJsonBoolean;
begin
  v := TJsonBoolean.Create(path+'/'+name, Value);
  try
    properties.SetProp(name, v.Link);
  finally
    v.Free;
  end;
end;

procedure TJsonObject.SetObject(name: String; const Value: TJsonObject);
begin
  properties.SetProp(name, value);
end;

procedure TJsonObject.SetString(name: String; const Value: String);
var
  v : TJsonValue;
begin
  v := TJsonValue.Create(path+'/'+name, Value);
  try
    properties.SetProp(name, v.Link);
  finally
    v.Free;
  end;
end;

{ TJsonBoolean }

constructor TJsonBoolean.Create(path: String; value: boolean);
begin
  create('path');
  FValue := value;
end;

function TJsonBoolean.Link: TJsonBoolean;
begin
  result := TJsonBoolean(inherited Link);
end;

function TJsonBoolean.nodeType: String;
begin
  result := 'boolean';
end;

function JsonBoolToString(b : boolean) : String;
begin
  if b then
    result := 'true'
  else
    result := 'false';

end;

function JsonStringToBool(s : String; def : boolean = false) : boolean;
begin
  if SameText(s, 'true') then
    result := true
  else if SameText(s, 'false') then
    result := false
  else if SameText(s, '0') then
    result := false
  else if SameText(s, 'no') then
    result := false
  else if SameText(s, '') then
    result := false
  else
    result := def;
end;

{ TJsonArrayEnumerator }

function TJsonArrayEnumerator.GetCurrent: TJsonObject;
begin
  result := FArray.GetObj(cursor);
end;

destructor TJsonArrayEnumerator.Destroy;
begin
  FArray.Free;
  inherited;
end;

function TJsonArrayEnumerator.MoveNext: boolean;
begin
  inc(cursor);
  result := cursor < FArray.GetCount;
end;


End.

