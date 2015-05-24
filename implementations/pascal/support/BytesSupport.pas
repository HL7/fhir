unit BytesSupport;

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

{$I bytes.inc}

Interface

Uses
  SysUtils, Classes,
  Math,
  StringSupport,
  AdvStringBuilders,
  AdvObjects,
  AdvStreams;

type
  TByte = Byte;

  TAdvBytesBuilder = Class (TAdvObject)
    Private
      FContent : TBytes;
      FLength : Integer;
      FBufferSize : integer;
    Public
      Constructor Create; Override;
      Function AsBytes : TBytes;

      Procedure Clear;

      Procedure Append(ch : AnsiChar); Overload;
      Procedure Append(b : Byte); Overload;
      Procedure Append(Const bytes : TBytes); Overload;

      Function EndsWith(aBytes : TBytes) : Boolean;
      Property Length : Integer Read FLength;

      Procedure AddWord(val : word);
      Procedure AddCardinal(val : cardinal);
      Procedure AddUInt64(val : UInt64);
      Procedure AddString(val : String);
      Procedure AddAnsiString(val : AnsiString);

      procedure Read(index : integer; var buffer; ilength : integer);

      Procedure WriteWord(index : integer; val : word);
      Procedure WriteCardinal(index : integer; val : cardinal);
      Procedure WriteUInt64(index : integer; val : UInt64);
      Procedure WriteString(index : integer; val : String);
  End;


Function BytesContains(bytes : TBytes; value : TByte): Boolean;
Function BytesAdd(bytes1, bytes2 : TBytes) : TBytes; Overload;
Function BytesAdd(bytes : TBytes; byte : TByte) : TBytes; Overload;
function CompareBytes(bytes1, bytes2 : TBytes) : Integer; Overload;
function SameBytes(bytes1, bytes2 : TBytes) : Boolean; Overload;
{$IFDEF UT}
function CompareBytes(bytes1 : TBytes; bytes2 : AnsiString) : Integer; Overload;
function SameBytes(bytes1 : TBytes; bytes2 : AnsiString) : Boolean; Overload;
{$ENDIF}
Function AnsiStringAsBytes(s : AnsiString) : TBytes;
Function StringAsBytes(s : String) : TBytes;
Function BytesAsString(a : TBytes) : String;
Function BytesAsAnsiString(a : TBytes) : AnsiString;
Function BytesAsMime(a : TBytes) : String;
Function BytesReplace(const a, OldPattern, NewPattern: TBytes): TBytes;
Function Bytes(a : Array of byte) : TBytes;
Function Fillbytes(b : byte; count : Integer): TBytes; Overload;
Function Fillbytes(init : TBytes; b : byte; count : Integer): TBytes; Overload;
Function BytesStartsWith(bytes, find : TBytes) : Boolean;
Function BytesSplit(Const sValue, sDelimiter : TBytes; Var sLeft, sRight: TBytes) : Boolean;
function StreamToBytes(AStream: TStream): TBytes;
function FileToBytes(filename : string) : TBytes;
procedure BytesToFile(bytes : TBytes; filename : string);

Implementation

{$IFNDEF UT}

function TAdvBytesBuilder.AsBytes : TBytes;
Begin
  result := AsString;
End;

Function BytesContains(bytes : TBytes; value : TByte): Boolean;
Begin
  result := pos(char(value), bytes) > 0;
End;

Function BytesAdd(bytes1, bytes2 : TBytes) : TBytes;
Begin
  result := bytes1 + bytes2;
End;

Function BytesAdd(bytes : TBytes; byte : TByte) : TBytes;
Begin
  result := bytes + byte;
End;

function CompareBytes(bytes1, bytes2 : TBytes) : Integer;
Begin
  result := CompareStr(bytes1, bytes2);
End;

function SameBytes(bytes1, bytes2 : TBytes) : Boolean;
Begin
  result := bytes1 = bytes2;
End;

Function StringAsBytes(s : AnsiString) : TBytes;
Begin
  result := s;
End;


Function BytesAsString(a : TBytes) : AnsiString;
Begin
  result := a;
End;

Function BytesAsMime(a : TBytes) : String;
var
  o : TAdvStringBuilder;
  i : integer;
begin
  o := TAdvStringBuilder.Create;
  try
    for i := 1 to length(a) do
      if (a[i] >= char(32)) or (a[i] <= char(127)) Then
        o.Append(a[i])
      else
        o.Append('#'+inttostr(ord(a[i])));
    result := o.AsString;
  Finally
    o.Free;
  End;
End;

Function BytesReplace(const a, OldPattern, NewPattern: TBytes): TBytes;
begin
  result := StringReplace(a, oldPattern, NewPattern, [rfReplaceAll]);
End;

Function Bytes(a : Array of byte) : TBytes;
var
  i : integer;
Begin
  SetLength(result, length(a));
  for i := Low(a) to high(a) do
    result[i+1] := char(a[i]);
End;

{$ELSE}

Uses
   MathSupport;

Const
  BUFFER_INCREMENT_SIZE = 1024;


  (*
Procedure TAdvBytesBuilder.Clear;
Begin
  FContent := '';
  FLength := 0;
End;


Function TAdvBytesBuilder.AsBytes : TBytes;
Begin
//  Result := Copy(FContent, 1, FLength);
  SetLength(result, FLength);
  move(FContent, result, FLength);
End;


Procedure TAdvBytesBuilder.WriteToStream(aStream : TAdvStream);
Begin
  If FLength > 0 Then
    aStream.Write(FContent[1], FLength);
End;


Procedure TAdvBytesBuilder.AppendPadded(Const sStr : String; iCount : Integer; cPad : Char = ' ');
Var
  iLen : Integer;
Begin
  iLen := IntegerMax(System.Length(sStr), iCount);

  If (iLen > 0) Then
  Begin
    If FLength + iLen > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, iLen));

    Move(sStr[1], FContent[FLength + 1], System.Length(sStr));

    If iLen = iCount Then
      FillChar(FContent[FLength + 1 + System.Length(sStr)], iCount - System.Length(sStr), cPad);

    Inc(FLength, iLen);
  End;
End;

Procedure TAdvBytesBuilder.AppendFixed(Const sStr : String; iCount : Integer; cPad : Char = ' ');
Begin
  If (iCount > 0) Then
  Begin
    If FLength + iCount > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, iCount));
    Move(sStr[1], FContent[FLength + 1], IntegerMin(System.Length(sStr), iCount));

    If System.Length(sStr) < iCount Then
      FillChar(FContent[FLength + 1 + System.Length(sStr)], iCount - System.Length(sStr), cPad);

    Inc(FLength, iCount);
  End;
End;


Procedure TAdvBytesBuilder.Append(ch : Char);
Begin
  If FLength + 1 > System.Length(FContent) Then
    SetLength(FContent, System.Length(FContent) + FBufferSize);

  Move(ch, FContent[FLength + 1], 1);
  Inc(FLength);
End;

Procedure TAdvBytesBuilder.Append(Const sStr : String);
Begin
  If (sStr <> '') Then
  Begin
    If FLength + System.Length(sStr) > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, System.Length(sStr)));

    Move(sStr[1], FContent[FLength + 1], System.Length(sStr));

    Inc(FLength, System.Length(sStr));
  End;
End;


Procedure TAdvBytesBuilder.Append(Const oStream : TAdvStream; iBytes : Integer);
Begin
  If (iBytes > 0) Then
  Begin
    If FLength + iBytes > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + Integermax(FBufferSize, iBytes));

    oStream.Read(FContent[FLength + 1], iBytes);

    Inc(FLength, iBytes);
  End;
End;


Procedure TAdvBytesBuilder.Append(Const oBuilder : TAdvBytesBuilder);
Begin
  Append(oBuilder.AsBytes);
End;


Procedure TAdvBytesBuilder.AppendEOL;
Begin
  Append(cReturn);
End;


Procedure TAdvBytesBuilder.Insert(Const sStr : String; iIndex : Integer);
Begin
  If (sStr <> '') Then
  Begin
    If FLength + System.Length(sStr) > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, System.Length(sStr)));

    If (iIndex) <> FLength Then
      Move(FContent[iIndex+1], FContent[iIndex+1 + System.Length(sStr)], FLength - iIndex);

    Move(sStr[1], FContent[iIndex+1], System.Length(sStr));

    Inc(FLength, System.Length(sStr));
  End;
End;


Procedure TAdvBytesBuilder.Insert(Const oStream : TAdvStream; iBytes : Integer; iIndex : Integer);
Begin
  If (iBytes > 0) Then
  Begin
    If FLength + iBytes > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, iBytes));

    If (iIndex) <> FLength Then
      Move(FContent[iIndex+1], FContent[iIndex+1 + iBytes], FLength - iIndex);

    oStream.Read(FContent[iIndex + 1], iBytes);

    Inc(FLength, iBytes);
  End;
End;


Procedure TAdvBytesBuilder.Insert(Const oBuilder : TAdvBytesBuilder; iIndex : Integer);
Begin
  Insert(oBuilder.AsBytes, iIndex);
End;


Procedure TAdvBytesBuilder.Delete(iIndex, iLength : Integer);
Begin
  System.Delete(FContent, iIndex+1, iLength);
  Dec(FLength, iLength);
End;



constructor TAdvBytesBuilder.Create;
begin
  inherited;
  FBufferSize := BUFFER_INCREMENT_SIZE;
end;

procedure TAdvBytesBuilder.CommaAdd(const sStr: String);
begin
  if Length > 0 Then
    Append(', ');
  Append(sStr);
end;

procedure TAdvBytesBuilder.AddCardinalAsBytes(iVal: Cardinal);
var
  s : String;
begin
  SetLength(s, 4);
  move(iVal, s[1], 4);
  Append(s);
end;

procedure TAdvBytesBuilder.AddWordAsBytes(iVal: word);
var
  s : String;
begin
  SetLength(s, 2);
  move(iVal, s[1], 2);
  Append(s);
end;

procedure TAdvBytesBuilder.AddInt64AsBytes(iVal: Int64);
var
  s : String;
begin
  SetLength(s, 8);
  move(iVal, s[1], 8);
  Append(s);
end;

procedure TAdvBytesBuilder.AddByteAsBytes(iVal: TByte);
var
  s : String;
begin
  SetLength(s, 1);
  move(iVal, s[1], 1);
  Append(s);
end;

procedure TAdvBytesBuilder.Append(const bBytes: array of TByte; amount: Integer);
var
  i : integer;
begin
  for i := 0 to amount - 1 Do
    Append(chr(bBytes[i]));

end;

procedure TAdvBytesBuilder.Append(const aBytes: TBytes);
begin

end;

procedure TAdvBytesBuilder.Insert(const aBytes: TBytes; iIndex: Integer);
begin

end;
    *)

Function BytesContains(bytes : TBytes; value : TByte): Boolean;
var
  i : integer;
Begin
  result := true;
  for i := Low(bytes) to high(bytes) do
    if bytes[i] = value then
      exit;
  result := false;
End;

Function BytesAdd(bytes1, bytes2 : TBytes) : TBytes;
Begin
  SetLength(result, length(bytes1) + length(bytes2));
  move(bytes1[0], result[0], length(bytes1));
  move(bytes2[0], result[length(bytes1)], length(bytes2));
End;

Function BytesAdd(bytes : TBytes; byte : TByte) : TBytes;
Begin
  SetLength(result, length(bytes) + 1);
  move(bytes[0], result[0], length(bytes));
  result[length(result)-1] := byte;
End;

(*
procedure TAdvBytesBuilder.Append(b: TByte);
begin
  Append(AnsiChar(b));
end;
  *)

function CompareBytes(bytes1, bytes2 : TBytes) : Integer;
begin
  result := length(bytes1) - length(bytes2);
  if result = 0 then
    result := integer(not compareMem(@bytes1[0], @bytes2[0], length(bytes1)));
End;

function SameBytes(bytes1, bytes2 : TBytes) : Boolean;
Begin
  result := CompareBytes(bytes1, bytes2) = 0;
End;

function CompareBytes(bytes1 : TBytes; bytes2 : AnsiString) : Integer;
Begin
  result := CompareBytes(bytes1, AnsiStringAsBytes(bytes2));
End;

function SameBytes(bytes1 : TBytes; bytes2 : AnsiString) : Boolean;
Begin
  result := SameBytes(bytes1, AnsiStringAsBytes(bytes2));
End;

Function AnsiStringAsBytes(s : AnsiString):TBytes;
Begin
  setLength(result, length(s));
  move(s[1], result[0], length(s));
End;

Function StringAsBytes(s : String):TBytes;
Begin
  {$IFDEF VER130}

  {$ELSE}
  result := TEncoding.UTF8.GetBytes(s);
  {$ENDIF}
End;

Function BytesAsAnsiString(a : TBytes) : AnsiString;
Begin
  setLength(result, length(a));
  move(a[0], result[1], length(a));
End;

Function BytesAsString(a : TBytes) : String;
var
  i : integer;
Begin
  setLength(result, length(a));
  for i := Low(a) to High(a) do
   result[i + 1] := Char(a[i]);
End;

Function BytesAsMime(a : TBytes) : String;
var
  o : TAdvStringBuilder;
  i : integer;
begin
  o := TAdvStringBuilder.Create;
  try
    for i := low(a) to high(a) do
      if (a[i] >= 32) or (a[i] <= 127) Then
        o.Append(char(a[i]))
      else
        o.Append('#'+inttostr(a[i]));
    result := o.AsString;
  Finally
    o.Free;
  End;
End;

Function Bytes(a : Array of TByte) : TBytes;
var
  i : integer;
Begin
  SetLength(result, length(a));
  for i := Low(result) to high(result) do
    result[i] := a[i];
End;

Function BytesReplace(const a, OldPattern, NewPattern: TBytes): TBytes;
var
  v : String;
begin
  v := SysUtils.StringReplace(BytesAsString(a), BytesAsString(OldPattern), BytesAsString(NewPattern), [rfReplaceAll]);
  result := StringAsBytes(v);
end;

{$ENDIF}

Function Fillbytes(b : byte; count : Integer): TBytes;
Begin
  SetLength(result, count);
  FillChar(result[1], count, b);
End;

Function Fillbytes(init : TBytes; b : byte; count : Integer): TBytes;
Begin
  SetLength(result, Max(count, length(init)));
  FillChar(result[1], count, b);
  if Length(init) > 0 Then
    move(init[1], result[1], length(init));
End;

Function BytesStartsWith(bytes, find : TBytes) : Boolean;
var
  i : integer;
Begin
  Result := length(bytes) >= length(find);
  i := 0;
  while result and (i < length(find)) do
  Begin
    result := bytes[i] = find[i];
    inc(i);
  End;
End;

constructor TAdvBytesBuilder.Create;
begin
  inherited;
  FBufferSize := BUFFER_INCREMENT_SIZE;
end;

function TAdvBytesBuilder.EndsWith(aBytes: TBytes): Boolean;
var
  i : integer;
begin
  result := (FLength >= System.Length(aBytes)) And (System.Length(aBytes) > 0);
  if result then
    for i := Low(aBytes) to High(aBytes) do
      result := result And (aBytes[i] = FContent[FLength - System.Length(aBytes) + i]);
end;

procedure TAdvBytesBuilder.Read(index : integer; var buffer; ilength: integer);
begin
  if index < 1 Then
    Error('Read', 'index < 1');
  if index + ilength > FLength Then
    Error('Read', 'index > length');
  Move(FContent[index], buffer, ilength);
end;

procedure TAdvBytesBuilder.WriteCardinal(index: integer; val: cardinal);
begin
  if index < 1 Then
    Error('Overwrite', 'index < 1');
  if index + 4 > FLength Then
    Error('Overwrite', 'index > length');
  Move(val, FContent[index], 4);
end;

procedure TAdvBytesBuilder.WriteString(index: integer; val: String);
begin
  if index < 1 Then
    Error('Overwrite', 'index < 1');
  if index + (val.Length*2) > FLength Then
    Error('Overwrite', 'index > length');
  Move(val[1], FContent[index], (val.Length*2));
end;

procedure TAdvBytesBuilder.WriteUInt64(index: integer; val: UInt64);
begin
  if index < 1 Then
    Error('Overwrite', 'index < 1');
  if index + 8 > FLength Then
    Error('Overwrite', 'index > length');
  Move(val, FContent[index], 8);
end;

procedure TAdvBytesBuilder.WriteWord(index: integer; val: word);
begin
  if index < 1 Then
    Error('Overwrite', 'index < 1');
  if index + 2 > FLength Then
    Error('Overwrite', 'index > length');
  Move(val, FContent[index], 2);
end;

Procedure TAdvBytesBuilder.Clear;
Begin
  FContent := nil;
  FLength := 0;
End;


Function TAdvBytesBuilder.AsBytes : TBytes;
Begin
  Result := Copy(FContent, 0, FLength);
End;


Procedure TAdvBytesBuilder.Append(ch : AnsiChar);
Begin
  If FLength + 1 > System.Length(FContent) Then
    SetLength(FContent, System.Length(FContent) + FBufferSize);

  Move(ch, FContent[FLength], 1);
  Inc(FLength);
End;

Procedure TAdvBytesBuilder.Append(b : Byte);
Begin
  If FLength + 1 > System.Length(FContent) Then
    SetLength(FContent, System.Length(FContent) + FBufferSize);

  Move(b, FContent[FLength], 1);
  Inc(FLength);
End;

procedure TAdvBytesBuilder.AddAnsiString(val: AnsiString);
Var
  s : TBytes;
Begin
  SetLength(s, System.Length(val));
  move(val[1], s[0], System.Length(val));
  Append(s);
end;

procedure TAdvBytesBuilder.AddCardinal(val: cardinal);
Var
  s : TBytes;
Begin
  SetLength(s, 4);
  move(val, s[0], 4);
  Append(s);
end;

procedure TAdvBytesBuilder.AddString(val: String);
Var
  s : TBytes;
Begin
  SetLength(s, val.Length*2);
  move(val[1], s[0], val.Length*2);
  Append(s);
end;

procedure TAdvBytesBuilder.AddUInt64(val: UInt64);
Var
  s : TBytes;
Begin
  SetLength(s, 8);
  move(val, s[0], 8);
  Append(s);
end;

procedure TAdvBytesBuilder.AddWord(val: word);
Var
  s : TBytes;
Begin
  SetLength(s, 2);
  move(val, s[0], 2);
  Append(s);
end;

Procedure TAdvBytesBuilder.Append(Const bytes : TBytes);
Begin
  If System.Length(bytes) > 0 Then
  Begin
    If FLength + System.Length(bytes) > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, System.Length(bytes)));

    Move(bytes[0], FContent[FLength], System.Length(bytes));

    Inc(FLength, System.Length(bytes));
  End;
End;

function BytesPos(const SubStr, Str: TBytes; offset : integer = 0): Integer;
var
  i, j: Integer;
  found : boolean;
begin
  Result := -1;
  if length(SubStr) > 0 then
    for I := Offset to Length(Str) - Length(SubStr) - 1 do
    begin
      found := true;
      for j := 0 to length(SubStr)-1 do
        if Str[I + J] <> SubStr[J] then
        begin
          found := false;
          break;
        end;
      if found then
      begin
        result := i;
        exit;
      end;
    end;
end;


Function BytesSplit(Const sValue, sDelimiter : TBytes; Var sLeft, sRight: TBytes) : Boolean;
Var
  iIndex : Integer;
  sA, sB : TBytes;
Begin
  // Find the delimiter within the source string
  iIndex := BytesPos(sDelimiter, sValue);
  Result := iIndex <> -1;

  If Not Result Then
  Begin
    sA := sValue;
    SetLength(sB, 0);
  End
  Else
  Begin
    sA := Copy(sValue, 1, iIndex - 1);
    sB := Copy(sValue, iIndex + Length(sDelimiter), MaxInt);
  End;

  sLeft := sA;
  sRight := sB;
End;

function StreamToBytes(AStream: TStream): TBytes;
begin
  SetLength(Result, AStream.Size);
  if AStream.Size > 0 then
    begin
    AStream.Position := 0;
    AStream.Read(Result[0], AStream.Size);
    end;
end;

procedure BytesToFile(bytes : TBytes; filename : string);
var
  f : TFileStream;
begin
  f :=  TFileStream.Create(filename, fmCreate);
  try
    f.write(bytes[0], length(bytes));
  finally
    f.Free;
  end;
end;

function FileToBytes(filename : string) : TBytes;
var
  f : TFileStream;
begin
  f :=  TFileStream.Create(filename, fmOpenRead + fmShareDenyWrite);
  try
    setLength(result, f.Size);
    f.Read(result[0], f.Size);
  finally
    f.Free;
  end;
end;

End.
