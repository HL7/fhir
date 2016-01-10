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
  Windows, SysUtils, Classes,
  BytesSupport, StringSupport,
  AdvObjects, AdvGenerics, AdvStreams, AdvVCLStreams, AdvTextFormatters, AdvTextExtractors, AdvObjectLists, AdvStringBuilders,
  TextUtilities, XMLBuilder;

Function JSONString(const value : String) : String;

Type
  TJsonObject = class;
  TJsonArray = class;

  TJsonNode = class (TAdvObject)
  private
    FPath: String;
  protected
    function nodeType : String; virtual;
    function compare(other : TJsonNode) : boolean; overload; virtual; abstract;
    function evaluatePointer(path : String) : TJsonNode; virtual;
  public
    LocationStart : TSourceLocation;
    LocationEnd : TSourceLocation;

    constructor create(path : String); overload;
    constructor create(path : String; locStart, locEnd : TSourceLocation); overload;
    Function Link : TJsonNode; Overload;
    property path : String read FPath write FPath;

    class function compare(n1, n2 : TJsonNode) : boolean; overload;
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
    function compare(other : TJsonNode) : boolean; override;
    function evaluatePointer(path : String) : TJsonNode; override;
  public
    constructor Create; override;
    destructor Destroy; override;
    Function Link : TJsonArray; Overload;

    Property Count : integer read GetCount;
    Property Item[i : integer] : TJsonNode read GetItem write SetItem;
    Property Obj[i : integer] : TJsonObject read GetObj write SetObj; default;
    Property Value[i : integer] : String read GetValue write SetValue;

    function add(value : String): TJsonArray; overload;
    function add(value : TJsonNode): TJsonArray; overload;
    function addObject : TJsonObject; overload;

    procedure remove(index : integer);
    procedure move(index, delta : integer);

    function GetEnumerator : TJsonArrayEnumerator; // can only use this when the array members are objects
  end;

  TJsonNull = class (TJsonNode)
  protected
    function nodeType : String; override;
    function compare(other : TJsonNode) : boolean; override;
  end;

  TJsonBoolean = class (TJsonNode)
  private
    FValue: boolean;
  protected
    function nodeType : String; override;
    function compare(other : TJsonNode) : boolean; override;
  public
    Constructor Create(path : String; value : boolean); overload;
    Constructor Create(path : String; locStart, locEnd : TSourceLocation; value : boolean); overload;
    Function Link : TJsonBoolean; Overload;
    property value : boolean read FValue write FValue;
  end;

  TJsonString = class (TJsonNode)
  private
    FValue: String;
  protected
    function nodeType : String; override;
    function compare(other : TJsonNode) : boolean; override;
  public
    Constructor Create(path : String; value : string); overload;
    Constructor Create(path : String; locStart, locEnd : TSourceLocation; value : string); overload;
    Function Link : TJsonString; Overload;
    property value : String read FValue write FValue;
  end;

  TJsonNumber = class (TJsonNode)
  private
    FValue: String;
  protected
    function nodeType : String; override;
    function compare(other : TJsonNode) : boolean; override;
  public
    Constructor Create(path : String; value : string); overload;
    Constructor Create(path : String; locStart, locEnd : TSourceLocation; value : string); overload;
    Function Link : TJsonNumber; Overload;
    property value : String read FValue write FValue;
  end;

  TJsonObject = class (TJsonNode)
  private
    FName : String;
    FProperties : TAdvMap<TJsonNode>;
    function GetString(name: String): String;
    function GetNumber(name: String): String;
    function GetArray(name: String): TJsonArray;
    function GetObject(name: String): TJsonObject;
    procedure SetString(name: String; const Value: String);
    procedure SetNumber(name: String; const Value: String);
    function GetBool(name: String): boolean;
    procedure SetBool(name: String; const Value: boolean);
    function GetForcedObject(name: String): TJsonObject;
    procedure SetArray(name: String; const Value: TJsonArray);
    procedure SetObject(name: String; const Value: TJsonObject);
    function GetForcedArray(name: String): TJsonArray;
  protected
    function nodeType : String; override;
    function compare(other : TJsonNode) : boolean; override;
    function evaluatePointer(path : String) : TJsonNode; override;
  public
    constructor Create; override;
    destructor Destroy; override;
    Function Link : TJsonObject; Overload;

    Function has(name : String) : Boolean;
    Function isNull(name : String) : Boolean;

    Property str[name : String] : String read GetString write SetString; default;
    Property num[name : String] : String read GetNumber write SetNumber;
    Property bool[name : String] : boolean read GetBool write SetBool;
    Property arr[name : String] : TJsonArray read GetArray write SetArray;
    Property obj[name : String] : TJsonObject read GetObject write SetObject;

//    // legacy, until the FHIR code is regenerated
    Property vStr[name : String] : String read GetString write SetString;
    Property vBool[name : String] : boolean read GetBool write SetBool;
    Property vArr[name : String] : TJsonArray read GetArray write SetArray;
    Property vObj[name : String] : TJsonObject read GetObject write SetObject;

    Property forceObj[name : String] : TJsonObject read GetForcedObject;
    Property forceArr[name : String] : TJsonArray read GetForcedArray;
    procedure clear(name : String = '');

    Property name : String read FName write FName;
    Property properties : TAdvMap<TJsonNode> read FProperties;
  end;

  TJsonPointerTerminalState = (tsNotFound, tsFound, tsAtEnd);

  TJsonPointerMatch = class (TAdvObject)
  private
    FName: String;
    FNode: TJsonNode;
    procedure SetNode(const Value: TJsonNode);
  public
    Constructor Create(name : String; node : TJsonNode);
    Destructor Destroy; Override;
    property name : String read FName write FName;
    property node : TJsonNode read FNode write SetNode;
  end;

  TJsonPointerQuery = class (TAdvObject)
  private
    FMatches : TAdvList<TJsonPointerMatch>;
    FTerminalState: TJsonPointerTerminalState;
    function GetLast: TJsonNode;
    function GetLastName: String;
    function GetSecondLast: TJsonNode;

    function unescape(s : String) : String;
  public
    constructor Create;
    Destructor Destroy; Override;
    procedure execute(focus : TJsonNode; path : string; terminalExtensions : boolean);

    property terminalState : TJsonPointerTerminalState read FTerminalState;
    property last : TJsonNode read GetLast;
    property lastName : String read GetLastName;
    property secondLast : TJsonNode read GetSecondLast;
  end;

  TJSONWriter = class (TAdvTextFormatter)
  private
    FBuilder : TAdvStringBuilder;
    FName : String;
    FCache : String;
    FProperty : Boolean;
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
    Procedure ValueBytes(Const name : String; bytes : TBytes);

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
    FLastLocationBWS : TSourceLocation;
    FLastLocationAWS : TSourceLocation;
    FLocation : TSourceLocation;
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

  TJsonParserItemType = (jpitObject, jpitString, jpitNumber, jpitBoolean, jpitArray, jpitEnd, jpitEof, jpitNull);

  TJSONParser = class (TAdvObject)
  Private
    FLex : TJSONLexer;
    FNameStart : TSourceLocation;
    FNameEnd : TSourceLocation;
    FValueStart : TSourceLocation;
    FValueEnd : TSourceLocation;
    FItemName: String;
    FItemValue: String;
    FItemType: TJsonParserItemType;
    FTimeToAbort : cardinal;
    Procedure ParseProperty;
    Procedure SkipInner;
    function GetItemValue: String;
    function GetItemNull: boolean;
    procedure readObject(obj : TJsonObject; root : boolean);
    procedure readArray(arr : TJsonArray; root : boolean);
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
    function readNode : TJsonNode;
    class Function Parse(stream : TAdvStream; timeToAbort : cardinal = 0): TJsonObject; overload;
    class Function Parse(stream : TStream; timeToAbort : cardinal = 0): TJsonObject; overload;
    class Function Parse(b : TBytes; timeToAbort : cardinal = 0): TJsonObject; overload;
    class Function Parse(s : String; timeToAbort : cardinal = 0): TJsonObject; overload;
    class Function ParseNode(stream : TAdvStream; timeToAbort : cardinal = 0): TJsonNode; overload;
    class Function ParseNode(stream : TStream; timeToAbort : cardinal = 0): TJsonNode; overload;
    class Function ParseNode(b : TBytes; timeToAbort : cardinal = 0): TJsonNode; overload;
    class Function ParseNode(s : String; timeToAbort : cardinal = 0): TJsonNode; overload;
  End;

  TJsonPatchEngine = class (TAdvObject)
  private
    FPatch: TJsonArray;
    FTarget: TJsonNode;
    procedure SetPatch(const Value: TJsonArray);
    procedure SetTarget(const Value: TJsonNode);
    procedure applyPatchOperation(patchOp : TJsonObject);
    procedure applyAdd(patchOp : TJsonObject; path : String);
    procedure applyAddInner(path : String; value : TJsonNode);
    procedure applyRemove(patchOp : TJsonObject; path : String);
    procedure applyReplace(patchOp : TJsonObject; path : String);
    procedure applyMove(patchOp : TJsonObject; path : String);
    procedure applyCopy(patchOp : TJsonObject; path : String);
    procedure applyTest(patchOp : TJsonObject; path : String);

    class procedure runtest(test : TJsonObject);
  public
    constructor Create; override;
    destructor Destroy; override;

    property patch : TJsonArray read FPatch write SetPatch;
    property target : TJsonNode read FTarget write SetTarget;

    procedure execute;

    class function applyPatch(target : TJsonObject; patch : TJsonArray) : TJsonObject;

    // source for tests: https://github.com/json-patch/json-patch-tests/blob/master/spec_tests.json
    class procedure tests(fileName : String);
  end;

Const
  Codes_TJsonParserItemType : Array[TJsonParserItemType] of String = ('Object', 'String', 'Number', 'Boolean', 'Array', 'End', 'EOF', 'Null');
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
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
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
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
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
    else if v is TJsonString then
      Value('', (v as TJsonString).FValue)
    else if v is TJsonNumber then
      Value('', (v as TJsonNumber).FValue)
    else // TJsonObject
      WriteObject('', v as TJsonObject);
  end;
  FinishArray;
end;

procedure TJSONWriter.WriteObjectInner(obj: TJsonObject);
var
  names : TStringList;
  n : String;
  v : TJsonNode;
begin
  names := TStringList.Create;
  try
    for n in obj.properties.Keys do
      names.add(n);
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
      else if v is TJsonString then
        Value(n, (v as TJsonString).FValue)
      else if v is TJsonNumber then
        ValueNumber(n, (v as TJsonNumber).FValue)
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

procedure TJSONWriter.ValueBytes(const name: String; bytes: TBytes);
begin
  if name = '' then
    raise Exception.Create('Injecting bytes not supported in an array');
  DoName(Name);
  produce(UseName);
  ProduceBytes(bytes);
  FProperty := true;
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
  hex : String;
begin
  FLastLocationBWS := FLocation;
  repeat
    ch := getNextChar;
  Until Not More Or not CharInSet(ch, [' ', #13, #10, #9]);
  FLastLocationAWS := FLocation;

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
            'u':
              begin
              setLength(hex, 4);
              hex[1] := getNextChar;
              hex[2] := getNextChar;
              hex[3] := getNextChar;
              hex[4] := getNextChar;
              FValue := FValue + chr(StrToInt('$'+hex));
              end
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
  begin
    result := ConsumeCharacter;
    if result = #10 then
    begin
      inc(FLocation.line);
      FLocation.col := 1;
    end
    else
      inc(FLocation.col);
  end;
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
  Inherited Create(oStream);
  FLocation.line := 1;
  FLocation.col := 1;
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
  FLex.Start;
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
  FLex.Start;
end;

function TJSONParser.GetItemNull: boolean;
begin
  result := false;
end;

function TJSONParser.GetItemValue: String;
begin
  if not (FItemType in [jpitBoolean, jpitString, jpitNumber]) Then
    FLex.JSONError('Attempt to read a simple value, but state is '+Codes_TJsonParserItemType[FItemType]);

  result := FItemValue;
end;

procedure TJSONParser.JsonError(sMsg: String);
begin
  FLex.JsonError(sMsg);
end;

procedure TJSONParser.Next;
begin
//  if (FTimeToAbort > 0) and (FTimeToAbort < GetTickCount) then
//    abort;

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
    jpitNull, jpitString, jpitNumber, jpitEnd, jpitBoolean :
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
    FLex.JsonError('not done yet (a): '+Codes_TJsonParserItemType[ItemType]);
  End;
end;

class function TJSONParser.Parse(stream: TAdvStream; timeToAbort : cardinal = 0): TJsonObject;
var
  p : TJSONParser;
begin
  p := TJSONParser.Create(stream);
  try
    p.FtimeToAbort := timeToAbort;
    result := TJsonObject.Create('$');
    try
      result.LocationStart := p.FLex.FLastLocationBWS;
      if p.FLex.LexType = jltOpen Then
      begin
        p.FLex.Next;
        p.FLex.FStates.InsertObject(0, '', nil);
      End
      Else
        p.FLex.JsonError('Unexpected content at start of JSON: '+Codes_TJSONLexType[p.FLex.LexType]);

      p.readObject(result, true);
      result.Link;
    finally
      result.Free;
    end;
  finally
    p.Free;
  end;
end;

class function TJSONParser.Parse(stream: TStream; timeToAbort : cardinal = 0): TJsonObject;
var
  p : TJSONParser;
begin
  p := TJSONParser.Create(stream);
  try
    p.FtimeToAbort := timeToAbort;
    result := TJsonObject.Create('$');
    try
      result.LocationStart := p.FLex.FLastLocationBWS;
      if p.FLex.LexType = jltOpen Then
      begin
        p.FLex.Next;
        p.FLex.FStates.InsertObject(0, '', nil);
      End
      Else
        p.FLex.JsonError('Unexpected content at start of JSON: '+Codes_TJSONLexType[p.FLex.LexType]);
      p.ParseProperty;
      p.readObject(result, true);
      result.Link;
    finally
      result.Free;
    end;
  finally
    p.Free;
  end;
end;

class function TJSONParser.Parse(s: String; timeToAbort : cardinal = 0): TJsonObject;
begin
  result := Parse(TEncoding.UTF8.GetBytes(s), timeToAbort);
end;

class function TJSONParser.ParseNode(stream: TAdvStream; timeToAbort : cardinal = 0): TJsonNode;
var
  p : TJSONParser;
begin
  p := TJSONParser.Create(stream);
  try
    p.FtimeToAbort := timeToAbort;
    result := p.readNode;
  finally
    p.Free;
  end;
end;

class function TJSONParser.ParseNode(stream: TStream; timeToAbort : cardinal = 0): TJsonNode;
var
  p : TJSONParser;
begin
  p := TJSONParser.Create(stream);
  try
    p.FtimeToAbort := timeToAbort;
    result := p.readNode;
  finally
    p.Free;
  end;
end;

class function TJSONParser.ParseNode(s: String; timeToAbort : cardinal = 0): TJsonNode;
begin
  result := ParseNode(TEncoding.UTF8.GetBytes(s), timeToAbort);
end;

class function TJSONParser.ParseNode(b: TBytes; timeToAbort : cardinal = 0): TJsonNode;
var
  s : TBytesStream;
begin
  s := TBytesStream.Create(b);
  try
    result := ParseNode(s, timeToAbort);
  finally
    s.Free;
  end;
end;

procedure TJSONParser.ParseProperty;
Begin
  If FLex.FStates.Objects[0] = nil Then
  Begin
    FNameStart := FLex.FLocation;
    FItemName := FLex.Consume(jltString);
    FNameEnd := FLex.FLocation;
    FItemValue := '';
    FLex.Consume(jltColon);
  End;
  case FLex.LexType of
    jltNull :
      Begin
      FItemType := jpitNull;
      FItemValue := FLex.FValue;
      FValueStart := FLex.FLastLocationAWS;
      FValueEnd := FLex.FLocation;
      FLex.Next;
      end;
    jltString :
      Begin
      FItemType := jpitString;
      FItemValue := FLex.FValue;
      FValueStart := FLex.FLastLocationAWS;
      FValueEnd := FLex.FLocation;
      FLex.Next;
      End;
    jltBoolean :
      Begin
      FItemType := jpitBoolean;
      FItemValue := FLex.FValue;
      FValueStart := FLex.FLastLocationAWS;
      FValueEnd := FLex.FLocation;
      FLex.Next;
      End;
    jltNumber :
      Begin
      FItemType := jpitNumber;
      FItemValue := FLex.FValue;
      FValueStart := FLex.FLastLocationAWS;
      FValueEnd := FLex.FLocation;
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
    FLex.JsonError('not done yet (b): '+Codes_TJSONLexType[FLex.LexType]);
  End;
End;


procedure TJSONParser.readArray(arr: TJsonArray; root : boolean);
var
  obj : TJsonObject;
  child : TJsonArray;
  i : integer;
begin
  i := 0;
  obj := nil;
  while not ((ItemType = jpitEnd) or (root and (ItemType = jpitEof))) do
  begin
    case ItemType of
      jpitObject:
        begin
          obj := TJsonObject.Create(arr.path+'['+inttostr(i)+']');
          arr.FItems.Add(obj);
          obj.LocationStart := FLex.FLocation;
          Next;
          readObject(obj, false);
        end;
      jpitString:
        arr.FItems.Add(TJsonString.Create(arr.path+'['+inttostr(i)+']', FValueStart, FValueEnd, ItemValue));
      jpitNumber:
        arr.FItems.Add(TJsonNumber.Create(arr.path+'['+inttostr(i)+']', FValueStart, FValueEnd, ItemValue));
      jpitNull :
        arr.FItems.Add(TJsonNull.Create(arr.path+'['+inttostr(i)+']', FValueStart, FValueEnd));
      jpitArray:
        begin
        child := TJsonArray.Create(arr.path+'['+inttostr(i)+']');
        arr.FItems.Add(child);
        child.LocationStart := FLex.FLocation;
        Next;
        readArray(child, false);
        end;
      jpitEof : raise Exception.Create('Unexpected End of File');
    end;
    arr.LocationEnd := FLex.FLocation;
    Next;
    inc(i);
  end;
end;

function TJSONParser.readNode: TJsonNode;
begin
  case FLex.LexType of
    jltOpen : 
      begin
        FLex.Next;
        FLex.FStates.InsertObject(0, '', nil);
        result := TJsonObject.Create('$');
        try
          result.LocationStart := FLex.FLastLocationBWS;
          readObject(result as TJsonObject, true);
          result.link;
        finally
          result.Free;
        end;
      end;
    jltString : raise Exception.Create('Not implemented yet');
    jltNumber : raise Exception.Create('Not implemented yet');
    jltOpenArray :
      begin
        FLex.Next;
        result := TJsonArray.Create('$');
        try
          FLex.FStates.InsertObject(0, '', result);
          result.LocationStart := FLex.FLastLocationBWS;
          readArray(result as TJsonArray, true);
          result.link;
        finally
          result.Free;
        end;
      end;
    jltNull : raise Exception.Create('Not implemented yet');
    jltBoolean : raise Exception.Create('Not implemented yet');
  else
    raise Exception.Create('Unexpected Token '+Codes_TJSONLexType[FLex.LexType]+' at start of Json Stream');  
  end;
end;

procedure TJSONParser.readObject(obj: TJsonObject; root : boolean);
var
  child : TJsonObject;
  arr : TJsonArray;
begin
  // this is a choice; in some senses, it's logical that the object ends where it actually ends.
  // but this looks weird because all the inner content of an object is identified. So we call the object
  // the area till it's properties start
  obj.LocationEnd := FLex.FLocation;

  while not ((ItemType = jpitEnd) or (root and (ItemType = jpitEof))) do
  begin
    if obj.FProperties.ContainsKey(itemName) then
      raise Exception.Create('DuplicateKey: '+itemName);

    case ItemType of
      jpitObject:
        begin
          child := TJsonObject.Create(obj.path+'.'+ItemName);
          obj.FProperties.Add(ItemName, child);
          child.LocationStart := FLex.FLocation;
          Next;
          readObject(child, false);
        end;
      jpitBoolean :
        obj.FProperties.Add(ItemName, TJsonBoolean.Create(obj.path+'.'+ItemName, FValueStart, FValueEnd, StrToBool(ItemValue)));
      jpitString:
        obj.FProperties.Add(ItemName, TJsonString.Create(obj.path+'.'+ItemName, FValueStart, FValueEnd, ItemValue));
      jpitNumber:
        obj.FProperties.Add(ItemName, TJsonNumber.Create(obj.path+'.'+ItemName, FValueStart, FValueEnd, ItemValue));
      jpitNull:
        obj.FProperties.Add(ItemName, TJsonNull.Create(obj.path+'.'+ItemName, FValueStart, FValueEnd));
      jpitArray:
        begin
        arr := TJsonArray.Create(obj.path+'.'+ItemName);
        obj.FProperties.Add(ItemName, arr);
        arr.LocationStart := FLex.FLocation;
        Next;
        readArray(arr, false);
        end;
      jpitEof : raise Exception.Create('Unexpected End of File');
    end;
    next;
  end;
end;

procedure TJSONParser.Skip;
begin
  if ItemType in [jpitString, jpitNumber] then
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
      jpitString : Next;
      jpitNumber : Next;
      jpitArray : SkipInner;
    End;
  End;
  Next;
end;


procedure TJSONWriter.ValueInArray(const value: String);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
  if value = '' then
    ValueNullInArray
  Else
    FCache := JSONString(value);
end;

procedure TJSONWriter.ValueNumberInArray(const value: String);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
  if value = '' then
    ValueNullInArray
  Else
    FCache := value;
end;

procedure TJSONWriter.ValueInArray(value: Boolean);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
  if value then
    FCache := 'true'
  else
    FCache := 'false';
end;

procedure TJSONWriter.ValueNullInArray;
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
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
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
  FCache := inttostr(value);
end;

procedure TJSONWriter.ValueInArray(value: Double);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
  FCache := FloatToStr(value);
end;

procedure TJSONWriter.ValueInArray(value: Integer);
begin
  if FCache <> '' Then
    ProduceLine(UseCache+',')
  else if FProperty then
  begin
    FProperty := false;
    ProduceLine(',')
  end;
  FCache := inttostr(value);
end;


destructor TJSONParser.Destroy;
begin
  FLex.free;
  inherited;
end;


class function TJSONParser.Parse(b: TBytes; timeToAbort : cardinal = 0): TJsonObject;
var
  s : TBytesStream;
begin
  s := TBytesStream.Create(b);
  try
    result := Parse(s, timeToAbort);
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

class function TJsonNode.compare(n1, n2: TJsonNode): boolean;
begin
  if (n1 = nil)  and (n2 = nil) then
    exit(true);
  if (n1 = nil) or (n2 = nil) then
    exit(false);

  result := n1.compare(n2);
end;

constructor TJsonNode.create(path: String; locStart, locEnd: TSourceLocation);
begin
  Create;
  self.path := path;
  LocationStart := locStart;
  LocationEnd := locEnd;
end;

function TJsonNode.evaluatePointer(path: String): TJsonNode;
begin
  result := nil;
end;

function TJsonNode.Link: TJsonNode;
begin
  result := TJsonNode(Inherited Link);
end;

function TJsonNode.nodeType: String;
begin
  result := copy(className, 6, $FF);
end;


{ TJsonArray }

function TJsonArray.add(value: String): TJsonArray;
begin
  FItems.Add(TJsonString.Create(path+'/'+inttostr(FItems.count), value));
  result := self;
end;

function TJsonArray.add(value: TJsonNode): TJsonArray;
begin
  FItems.Add(value);
  result := self;
end;

function TJsonArray.addObject: TJsonObject;
begin
  result := TJsonObject.Create;
  add(result);
end;

function TJsonArray.compare(other: TJsonNode): boolean;
var
  o : TJsonArray;
  i : integer;
begin
  if not (other is TJsonArray) then
    exit(false);

  o := other as TJsonArray;
  if Count <> o.Count then
    exit(false);

  for i := 0 to Count -1 do
    if not Item[i].compare(o.Item[i]) then
      exit(false);

  result := true;
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

function TJsonArray.evaluatePointer(path: String): TJsonNode;
begin
  if StringIsInteger32(path) then
    result := GetItem(StrToInt(path))
  else
    result := nil;
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
  else if FItems[i] is TJsonString then
    result := (FItems[i] as TJsonString).FValue
  else if FItems[i] is TJsonNumber then
    result := (FItems[i] as TJsonNumber).FValue
  else if FItems[i] is TJsonNull then
    result := ''
  else
    raise Exception.Create('Found a '+nodeType+' expecting a string property at '+path);
end;

function TJsonArray.Link: TJsonArray;
begin
  result := TJsonArray(Inherited Link);
end;

procedure TJsonArray.move(index, delta: integer);
begin
  FItems.Move(index, index+delta);
end;

function TJsonArray.nodeType: String;
begin
  result := 'array';
end;

procedure TJsonArray.remove(index: integer);
begin
  FItems.DeleteByIndex(index);
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
  FItems[i] := TJsonString.Create(Path+'['+inttostr(i)+']', Value);
end;

{ TJsonString }

constructor TJsonString.Create(path, value: string);
begin
  Create(path);
  self.value := value;
end;

function TJsonString.compare(other: TJsonNode): boolean;
begin
  if not (other is TJsonString) then
    result := false
  else
    result := FValue = (other as TJsonString).FValue;
end;

constructor TJsonString.Create(path: String; locStart, locEnd: TSourceLocation; value: string);
begin
  Create(path);
  self.value := value;
  LocationStart := locStart;
  LocationEnd := locEnd;
end;

function TJsonString.Link: TJsonString;
begin
  result := TJsonString(Inherited Link);
end;

function TJsonString.nodeType: String;
begin
  result := 'string';
end;

{ TJsonNumber }

constructor TJsonNumber.Create(path, value: string);
begin
  Create(path);
  self.value := value;
end;

function TJsonNumber.compare(other: TJsonNode): boolean;
begin
  if not (other is TJsonNumber) then
    result := false
  else
    result := FValue = (other as TJsonNumber).FValue;
end;

constructor TJsonNumber.Create(path: String; locStart, locEnd: TSourceLocation; value: string);
begin
  Create(path);
  self.value := value;
  LocationStart := locStart;
  LocationEnd := locEnd;
end;

function TJsonNumber.Link: TJsonNumber;
begin
  result := TJsonNumber(Inherited Link);
end;

function TJsonNumber.nodeType: String;
begin
  result := 'number';
end;

{ TJsonObject }

procedure TJsonObject.clear(name: String);
begin
  if name = '' then
    FProperties.Clear
  else
    FProperties.Remove(name);
end;

function TJsonObject.compare(other: TJsonNode): boolean;
var
  o : TJsonObject;
  s : String;
begin
  if not (other is TJsonObject) then
    exit(false);

  o := other as TJsonObject;
  if properties.Count <> o.properties.Count then
    exit(false);

  for s in properties.Keys do
    if not TJsonNode.compare(properties[s], o.properties[s]) then
      exit(false);

  result := true;
end;

constructor TJsonObject.create;
begin
  inherited Create;
  FProperties := TAdvMap<TJsonNode>.Create;
end;

destructor TJsonObject.destroy;
begin
  FProperties.Free;
  inherited;
end;

function TJsonObject.evaluatePointer(path: String): TJsonNode;
begin
  if has(path) then
    result := properties[path]
  else
    result := nil;
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
  if not properties.containsKey(name) or not (properties[name] is TJsonArray) then
    arr[name] := TJsonArray.Create;
  result := arr[name];
end;

function TJsonObject.GetForcedObject(name: String): TJsonObject;
begin
  if not properties.containsKey(name) or not (properties[name] is TJsonObject) then
    obj[name] := TJsonObject.Create;
  result := obj[name];
end;

function TJsonObject.GetNumber(name: String): String;
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
      if (node is TJsonString) and StringIsInteger32(TJsonString(node).FValue) then
        result := TJsonString(node).FValue
      else if node is TJsonNumber then
        result := TJsonNumber(node).FValue
      else if node is TJsonNull then
        result := ''
      else if node is TJsonBoolean then
        if (node as TJsonBoolean).FValue then
          result := '1'
        else
          result := '0'
      else
        raise Exception.Create('Found a property of type '+node.nodeType+' looking for a string at '+FPath+'.'+name);
    end
    else
      result := '';
  end;
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
      if node is TJsonString then
        result := TJsonString(node).FValue
      else if node is TJsonNumber then
        result := TJsonNumber(node).FValue
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
  result := FProperties.containsKey(name);
end;

function TJsonObject.isNull(name: String): Boolean;
begin
  result := has(name) and (FProperties[name] is TJsonNull);
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
  properties.AddOrSetValue(name, value);
end;

procedure TJsonObject.SetBool(name: String; const Value: boolean);
var
  v : TJsonBoolean;
begin
  v := TJsonBoolean.Create(path+'/'+name, Value);
  try
    properties.AddOrSetValue(name, v.Link);
  finally
    v.Free;
  end;
end;

procedure TJsonObject.SetObject(name: String; const Value: TJsonObject);
begin
  properties.AddOrSetValue(name, value);
end;

procedure TJsonObject.SetString(name: String; const Value: String);
var
  v : TJsonString;
begin
  v := TJsonString.Create(path+'/'+name, Value);
  try
    properties.AddOrSetValue(name, v.Link);
  finally
    v.Free;
  end;
end;

procedure TJsonObject.SetNumber(name: String; const Value: String);
var
  v : TJsonNumber;
begin
  v := TJsonNumber.Create(path+'/'+name, Value);
  try
    properties.AddOrSetValue(name, v.Link);
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

function TJsonBoolean.compare(other: TJsonNode): boolean;
begin
  if not (other is TJsonBoolean) then
    result := false
  else
    result := FValue = (other as TJsonBoolean).FValue;
end;

constructor TJsonBoolean.Create(path: String; locStart, locEnd: TSourceLocation; value: boolean);
begin
  create('path');
  FValue := value;
  LocationStart := locStart;
  LocationEnd := locEnd;
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


{ TJsonPatchEngine }

class function TJsonPatchEngine.applyPatch(target: TJsonObject; patch: TJsonArray) : TJsonObject;
var
  this : TJsonPatchEngine;
begin
  this := TJsonPatchEngine.Create;
  try
    this.target := target.Link;
    this.patch := patch.Link;
    this.execute;
    result := this.target.link as TJsonObject;
  finally
    this.free;
  end;
end;

constructor TJsonPatchEngine.Create;
begin
  inherited;
end;

destructor TJsonPatchEngine.Destroy;
begin
  FPatch.free;
  FTarget.Free;
  inherited;
end;

procedure TJsonPatchEngine.SetPatch(const Value: TJsonArray);
begin
  FPatch.free;
  FPatch := Value;
end;

procedure TJsonPatchEngine.SetTarget(const Value: TJsonNode);
begin
  FTarget.Free;
  FTarget := Value;
end;

class procedure TJsonPatchEngine.runtest(test: TJsonObject);
var
  cmt : string;
  outcome : TJsonObject;
  ok : boolean;
begin
  cmt := test['comment'];
  writeln('test: '+cmt);
  if test.has('error') then
  begin
    ok := true;
    try
      applyPatch(test.obj['doc'], test.arr['patch']).Free;
      ok := false;
    except
    end;
    if not ok then
      raise Exception.Create('Test failed: '+cmt);
  end
  else
  begin
    outcome := applyPatch(test.obj['doc'], test.arr['patch']);
    try
      if not TJsonNode.compare(outcome, test.obj['expected']) then
        raise Exception.Create('Test failed: '+cmt);
    finally
      outcome.Free;
    end;
  end;
end;

class procedure TJsonPatchEngine.tests(fileName: String);
var
  tests : TJsonArray;
  test : TJsonNode;
begin
  tests := TJSONParser.ParseNode(FileToBytes(filename)) as TJsonArray;
  try
    for test in tests do
      runtest(test as TJsonObject);
  finally
    tests.free;
  end;
end;

procedure TJsonPatchEngine.execute;
var
  op : TJsonNode;
begin
  assert(target <> nil);
  assert(patch <> nil);
  for op in patch do
    if op is TJsonObject then
      applyPatchOperation(op as TJsonObject)
    else
      raise Exception.Create('Unexpected JSON node type looking for operation: '+op.nodeType);
end;

procedure TJsonPatchEngine.applyPatchOperation(patchOp: TJsonObject);
var
  op, path : String;
begin
  op := patchOp['op'];
  path := patchOp['path'];
  if path = '' then
    raise Exception.Create('No patch path parameter found');

  if op = '' then
    raise Exception.Create('No patch op parameter found')
  else if op = 'add' then
    applyAdd(patchOp, path)
  else if op = 'remove' then
    applyRemove(patchOp, path)
  else if op = 'replace' then
    applyReplace(patchOp, path)
  else if op = 'move' then
    applyMove(patchOp, path)
  else if op = 'copy' then
    applyCopy(patchOp, path)
  else if op = 'test' then
    applyTest(patchOp, path)
  else
    raise Exception.Create('Unknown patch operation "'+op+'"');
end;

procedure TJsonPatchEngine.applyAdd(patchOp: TJsonObject; path : String);
var
  value : TJsonNode;
begin
  value := patchOp.properties['value'];
  if value = nil then
    raise Exception.Create('No patch value parameter found in add');
  applyAddInner(path, value);
end;

procedure TJsonPatchEngine.applyAddInner(path : String; value : TJsonNode);
var
  query : TJsonPointerQuery;
  index : integer;
begin
  query := TJsonPointerQuery.create;
  try
    query.execute(target, path, true);
    case query.terminalState of
      tsNotFound :
        if (query.secondLast is TJsonObject) then
          (query.secondLast as TJsonObject).properties.add(query.lastName, value.Link)
        else
          raise Exception.Create('Unexpected target type '+query.last.nodeType);
      tsFound :
        begin
        if (query.secondLast is TJsonArray) and StringIsInteger32(query.lastName) then
          (query.secondLast as TJsonArray).FItems.Insert((query.secondLast as TJsonArray).FItems.IndexByReference(query.last), value.link)
        else if query.secondlast is TJsonObject then
          (query.secondlast as TJsonObject).FProperties.AddOrSetValue(query.lastName, value.link)
        else
          raise Exception.Create('Unexpected target type '+query.last.nodeType);
        end;
      tsAtEnd :
        begin
        if (query.secondlast is TJsonArray) then
          (query.secondlast as TJsonArray).add(value.Link)
        else
          raise Exception.Create('Attempt to append content to a non-array ('+query.last.nodeType+')');
        end;
    end;
  finally
    query.free;
  end;
end;

procedure TJsonPatchEngine.applyRemove(patchOp: TJsonObject; path : String);
var
  query : TJsonPointerQuery;
begin
  query := TJsonPointerQuery.create;
  try
    query.execute(target, path, false);
    if (query.secondLast is TJsonArray) and StringIsInteger32(query.lastName) then
      (query.secondLast as TJsonArray).FItems.DeleteByIndex((query.secondLast as TJsonArray).FItems.IndexByReference(query.last))
    else if query.secondLast is TJsonObject then
      (query.secondLast as TJsonObject).FProperties.Remove(query.lastName)
    else
      raise Exception.Create('Unexpected target type '+query.last.nodeType);
  finally
    query.free;
  end;
end;

procedure TJsonPatchEngine.applyReplace(patchOp: TJsonObject; path : String);
var
  value : TJsonNode;
  query : TJsonPointerQuery;
  index : integer;
begin
  value := patchOp.properties['value'];
  if value = nil then
    raise Exception.Create('No patch value parameter found in add');
  query := TJsonPointerQuery.create;
  try
    query.execute(target, path, false);
    if (query.secondLast is TJsonArray) and StringIsInteger32(query.lastName) then
      (query.secondLast as TJsonArray).FItems[(query.secondLast as TJsonArray).FItems.IndexByReference(query.last)] := value.link
    else if query.secondLast is TJsonObject then
      (query.secondLast as TJsonObject).FProperties[query.lastName] := value.link
    else
      raise Exception.Create('Unexpected target type '+query.last.nodeType);
  finally
    query.free;
  end;
end;

procedure TJsonPatchEngine.applyTest(patchOp: TJsonObject; path : String);
var
  query : TJsonPointerQuery;
  value : TJsonNode;
begin
  value := patchOp.properties['value'];
  if value = nil then
    raise Exception.Create('No patch value parameter found in add');

  query := TJsonPointerQuery.create;
  try
    query.execute(target, path, false);
    if not TJsonNode.compare(query.last, value) then
      raise Exception.Create('Test Failed because nodes are not equal');
  finally
    query.free;
  end;
end;

procedure TJsonPatchEngine.applyCopy(patchOp: TJsonObject; path : String);
var
  from : string;
  qFrom, qPath : TJsonPointerQuery;
begin
  from := patchOp['from'];
  if from = '' then
    raise Exception.Create('No patch from parameter found');

  qFrom := TJsonPointerQuery.create;
  try
    qFrom.execute(target, from, false);
    applyAddInner(path, qFrom.last);
  finally
    qFrom.free;
  end;
end;

procedure TJsonPatchEngine.applyMove(patchOp: TJsonObject; path : String);
var
  from : string;
  qFrom, qPath : TJsonPointerQuery;
  focus : TJsonNode;
begin
  from := patchOp['from'];
  if from = '' then
    raise Exception.Create('No patch from parameter found');

  qFrom := TJsonPointerQuery.create;
  try
    qFrom.execute(target, from, false);
    focus := qFrom.last.Link;
    try
      if (qFrom.secondLast is TJsonArray) and StringIsInteger32(qFrom.lastName) then
        (qFrom.secondLast as TJsonArray).FItems.DeleteByIndex((qFrom.secondLast as TJsonArray).FItems.IndexByReference(qFrom.last))
      else if qFrom.secondLast is TJsonObject then
        (qFrom.secondLast as TJsonObject).FProperties.Remove(qFrom.lastName)
      else
        raise Exception.Create('Unexpected target type '+qFrom.last.nodeType);

      applyAddInner(path, focus);
    finally
      focus.Free
    end;

  finally
    qFrom.free;
  end;
end;


{ TJsonPointerMatch }

constructor TJsonPointerMatch.Create(name: String; node: TJsonNode);
begin
  inherited create;
  self.Name := name;
  self.Node := node;
end;

destructor TJsonPointerMatch.Destroy;
begin
  FNode.Free;
  inherited;
end;

procedure TJsonPointerMatch.SetNode(const Value: TJsonNode);
begin
  FNode.Free;
  FNode := Value;
end;

{ TJsonNull }

function TJsonNull.compare(other: TJsonNode): boolean;
begin
  result := other is TJsonNull;
end;

function TJsonNull.nodeType: String;
begin
  result := 'null'
end;

{ TJsonPointerQuery }

constructor TJsonPointerQuery.Create;
begin
  inherited Create;
  FMatches := TAdvList<TJsonPointerMatch>.create;
end;

destructor TJsonPointerQuery.Destroy;
begin
  FMatches.Free;
  inherited;
end;

procedure TJsonPointerQuery.execute(focus: TJsonNode; path: String; terminalExtensions: boolean);
var
  i : integer;
  pl : TArray<String>;
  p : String;
begin
  if (path.Trim = '') then
    raise Exception.Create('Path cannot be blank');

  FMatches.Add(TJsonPointerMatch.Create('$', focus.Link));
  FTerminalState := tsFound;
  pl  := path.Split(['/']);
  for i := 1 to length(pl) - 1 do
  begin
    p := unescape(pl[i].Trim);
    if terminalExtensions and (i = length(pl) - 1) then
    begin
      if (p = '-') and (focus is TJsonArray) then
      begin
        FTerminalState := tsAtEnd;
        FMatches.Add(TJsonPointerMatch.Create(p, nil));
      end
      else
      begin
        focus := focus.evaluatePointer(p);
        if focus = nil then
        begin
          if (last is TJsonArray) and StringIsInteger32(p) and (StrToInt(p) = (last as TJsonArray).count) then
            FTerminalState := tsAtEnd
          else
            FTerminalState := tsNotFound;

          FMatches.Add(TJsonPointerMatch.Create(p, nil));
        end
        else
          FMatches.Add(TJsonPointerMatch.Create(p, focus.Link));
      end;
    end
    else
    begin
      focus := focus.evaluatePointer(p);
      if focus = nil then
        raise Exception.Create('Pointer could not be resolved: "'+p+'"')
      else
        FMatches.Add(TJsonPointerMatch.Create(p, focus.Link));
    end;
  end;
end;

function TJsonPointerQuery.GetLast: TJsonNode;
begin
  result := FMatches[FMatches.Count - 1].FNode;
end;

function TJsonPointerQuery.GetLastName: String;
begin
  result := FMatches[FMatches.Count - 1].FName;
end;

function TJsonPointerQuery.GetSecondLast: TJsonNode;
begin
  result := FMatches[FMatches.Count - 2].FNode;

end;

function TJsonPointerQuery.unescape(s: String): String;
begin
  result := s.Replace('~1', '/').Replace('~0', '~');
end;

End.

