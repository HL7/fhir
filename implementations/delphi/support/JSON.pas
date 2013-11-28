Unit JSON;

Interface

uses
  SysUtils,
  Classes,
  AdvStreams,
  AdvVCLStreams,
  AdvObjects,
  AdvTextFormatters,
  AdvTextExtractors,
  AdvStringBuilders;

Function JSONString(const value : String) : String;

Type
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
    Procedure ValueInArray(value : Boolean); overload;
    Procedure ValueInArray(value : Integer); overload;
    Procedure ValueInArray(value : Int64); overload;
    Procedure ValueInArray(value : Double); overload;
    Procedure ValueDateInArray(aValue : TDateTime); overload;
    Procedure ValueNullInArray;
  End;


  TJSONLexType = (jltOpen, jltClose, jltString, jltNumber, jltColon, jltComma, jltOpenArray, jltCloseArray, jltEof);

  TJSONLexer = class (TAdvTextExtractor)
  Private
    FPeek : String;
    FValue: String;
    FLexType: TJSONLexType;
    FStates : TStringList;
    Function getNextChar : Char;
    Procedure Push(ch : Char);
    procedure ParseWord(sWord : String; ch : Char);
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

  TJsonParserItemType = (jpitObject, jpitSimple, jpitArray, jpitEnd, jpitEof);

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
  Public
    Constructor Create(oStream : TStream); Overload;
    Constructor Create(oStream : TAdvStream);  Overload;
    Destructor Destroy; Override;
    Property ItemType : TJsonParserItemType read FItemType;
    Property ItemName : String read FItemName;
    Property ItemValue : String read GetItemValue;
    Procedure Next;
    Procedure Skip;
    Procedure JsonError(sMsg : String);
    Procedure CheckState(aState : TJsonParserItemType);
  End;

Const
  Codes_TJsonParserItemType : Array[TJsonParserItemType] of String = ('Object', 'Simple', 'Array', 'End', 'EOF');
  Codes_TJSONLexType : Array[TJSONLexType] of String = ('Open', 'Close', 'String', 'Number', 'Colon', 'Comma', 'OpenArray', 'CloseArray', 'Eof');

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

procedure TJSONLexer.ParseWord(sWord : String; ch : Char);
Begin
  FLexType := jltString;
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
  Until Not More Or (not (ch in [' ', #13, #10, #9]));

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
    't' : ParseWord('true', ch);
    'f' : ParseWord('false', ch);
    'n' : ParseWord('null', ch);
    '0'..'9' :
      Begin
      FLexType := jltNumber;
      FValue := ch;
      while More and (ch in ['0'..'9', '.']) do
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
  Inherited Create(oStream);
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

function TJSONParser.GetItemValue: String;
begin
  if FItemType <> jpitSimple Then
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
    jpitSimple, jpitEnd :
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

procedure TJSONParser.ParseProperty;
Begin
  If FLex.FStates.Objects[0] = nil Then
  Begin
    FItemName := FLex.Consume(jltString);
    FItemValue := '';
    FLex.Consume(jltColon);
  End;
  case FLex.LexType of
    jltString :
      Begin
      FItemType := jpitSimple;
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
    FLex.JsonError('Unexpected content at start of JSON');
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

End.

