Unit AdvStringBuilders;

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
  Classes,
  SysUtils,
  AdvObjects,
  AdvStreams;

{$IFDEF FPC}
  {$DEFINE NO_BUILDER}
{$ENDIF}

{$IFDEF VER130}
  {$DEFINE NO_BUILDER}
{$ENDIF}

Type
  {$IFDEF NO_BUILDER}
  TAdvStringBuilder = Class(TAdvObject)
    Private
      FContent : String;
      FLength : Integer;
      FBufferSize : integer;

      Procedure Append(Const oStream : TAdvStream; iBytes : Integer); Overload;
      Procedure Insert(Const oStream : TAdvStream; iBytes : Integer; iIndex : Integer); Overload;
    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function AsString : String;
      Function ToString : String;

      Procedure Clear;

      Procedure Append(ch : Char); Overload;
      Procedure Append(Const sStr : String); Overload;
      Procedure AppendLine(Const sStr : String); Overload;
      Procedure Append(Const iInt : Integer); Overload;
      Procedure AppendPadded(Const sStr : String; iCount : Integer; cPad : Char = ' ');
      Procedure AppendFixed(Const sStr : String; iCount : Integer; cPad : Char = ' ');
      Procedure Append(const bBytes : Array of Byte; amount : Integer); Overload;
      Procedure Append(Const oBuilder : TAdvStringBuilder); Overload;
      Procedure AppendEOL;

      Procedure CommaAdd(Const sStr : String); Overload;

      Procedure AddByteAsBytes(iVal : Byte);
      Procedure AddWordAsBytes(iVal : word);
      Procedure AddCardinalAsBytes(iVal : Cardinal);
      Procedure AddInt64AsBytes(iVal : Int64);

      // index is zero based. zero means insert before first character
      Procedure Insert(Const sStr : String; iIndex : Integer); Overload;
      Procedure Insert(Const oBuilder : TAdvStringBuilder; iIndex : Integer); Overload;

      Procedure Delete(iIndex, iLength : Integer);

      Property BufferSize : integer read FBufferSize write FBufferSize;
      Function IndexOf(Const sStr : String; bCase : Boolean = False) : Integer;
      Function LastIndexOf(Const sStr : String; bCase : Boolean = False) : Integer;

      Property Length : Integer Read FLength;
      Procedure Read(index : integer; var buffer; ilength : integer);
      Procedure Overwrite(index : integer; content : String);
  End;


  {$ELSE}
  TAdvStringBuilder = Class (TAdvObject)
    Private
      FBuilder : TStringBuilder;

      Function GetLength : Integer;
      function GetAsString: String;
    Public
      Constructor Create; Override;
      Destructor Destroy; Override;
      Property AsString : String read GetAsString;

      Procedure Clear;

      Procedure Append(ch : Char); Overload;
      Procedure Append(Const sStr : String); Overload;
      Procedure Append(Const sStr : AnsiString); Overload;
      Procedure AppendLine(Const sStr : String); Overload;
      Procedure AppendPadded(Const sStr : String; iCount : Integer; cPad : Char = ' ');
      Procedure AppendFixed(Const sStr : String; iCount : Integer; cPad : Char = ' ');
      Procedure Append(Const oBuilder : TAdvStringBuilder); Overload;
      Procedure AppendEOL;

      Procedure CommaAdd(Const sStr : String); Overload;

      Procedure AddByteAsBytes(iVal : Byte);
      Procedure AddWordAsBytes(iVal : word);
      Procedure AddCardinalAsBytes(iVal : Cardinal);
      Procedure AddInt64AsBytes(iVal : Int64);

      // index is zero based. zero means insert before first character
      Procedure Insert(Const sStr : String; iIndex : Integer); Overload;
      Procedure Insert(Const oBuilder : TAdvStringBuilder; iIndex : Integer); Overload;

      Procedure Delete(iIndex, iLength : Integer);

      Procedure WriteToStream(aStream : TAdvStream; encoding : TEncoding = nil); Overload;
      Procedure WriteToStream(aStream : TStream; encoding : TEncoding = nil); overload;

      Property Length : Integer Read GetLength;
  End;
  {$ENDIF}

Implementation


Uses
  MathSupport,
  StringSupport;

{$IFDEF NO_BUILDER}

Const
  BUFFER_INCREMENT_SIZE = 1024;


Constructor TAdvStringBuilder.Create;
Begin
  Inherited;

  FBufferSize := BUFFER_INCREMENT_SIZE;
End;


Destructor TAdvStringBuilder.Destroy;
Begin
  inherited;
End;


Procedure TAdvStringBuilder.Clear;
Begin
  FContent := '';
  FLength := 0;
End;


Function TAdvStringBuilder.ToString : String;
Begin
  Result := Copy(FContent, 1, FLength);
End;


Procedure TAdvStringBuilder.AppendPadded(Const sStr : String; iCount : Integer; cPad : Char = ' ');
Var
  iLen : Integer;
Begin
  iLen := IntegerMax(System.Length(sStr), iCount);

  If (iLen > 0) Then
  Begin
    If FLength + iLen > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, iLen));

    Move(sStr[1], FContent[FLength + 1], System.Length(sStr) * SizeOf(Char));

    If iLen = iCount Then
      FillChar(FContent[FLength + 1 + System.Length(sStr)], (iCount - System.Length(sStr)) * SizeOf(Char), cPad);

    Inc(FLength, iLen);
  End;
End;


Function TAdvStringBuilder.AsString: String;
Begin
  Result := ToString;
End;


Procedure TAdvStringBuilder.AppendFixed(Const sStr : String; iCount : Integer; cPad : Char = ' ');
Begin
  If (iCount > 0) Then
  Begin
    If FLength + iCount > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, iCount));
    Move(sStr[1], FContent[FLength + 1], IntegerMin(System.Length(sStr), iCount) * SizeOf(Char));

    If System.Length(sStr) < iCount Then
      FillChar(FContent[FLength + 1 + System.Length(sStr)], (iCount - System.Length(sStr)) * SizeOf(Char), cPad);

    Inc(FLength, iCount);
  End;
End;


Procedure TAdvStringBuilder.Append(ch : Char);
Begin
  If FLength + 1 > System.Length(FContent) Then
    SetLength(FContent, System.Length(FContent) + FBufferSize);

  Move(ch, FContent[FLength + 1], SizeOf(Char));
  Inc(FLength);
End;


Procedure TAdvStringBuilder.Append(Const sStr : String);
Begin
 If (sStr <> '') Then
  Begin
    If FLength + System.Length(sStr) > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, System.Length(sStr)));

    Move(sStr[1], FContent[FLength + 1], System.Length(sStr) * SizeOf(Char));

    Inc(FLength, System.Length(sStr));
  End;
End;


Procedure TAdvStringBuilder.Append(Const oBuilder : TAdvStringBuilder);
Begin
  Append(oBuilder.ToString);
End;


Procedure TAdvStringBuilder.AppendEOL;
Begin
  Append(cReturn);
End;


Procedure TAdvStringBuilder.Append(Const iInt : Integer);
Begin
  Append(IntegerToString(iInt));
End;


Procedure TAdvStringBuilder.Insert(Const sStr : String; iIndex : Integer);
Begin
  If (sStr <> '') Then
  Begin
    If FLength + System.Length(sStr) > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, System.Length(sStr)));

    If (iIndex) <> FLength Then
      Move(FContent[iIndex+1], FContent[iIndex+1 + System.Length(sStr)], (FLength - iIndex)  * SizeOf(Char));

    Move(sStr[1], FContent[iIndex+1], System.Length(sStr) * SizeOf(Char));

    Inc(FLength, System.Length(sStr));
  End;
End;


Procedure TAdvStringBuilder.Insert(Const oBuilder : TAdvStringBuilder; iIndex : Integer);
Begin
  Insert(oBuilder.ToString, iIndex);
End;


Procedure TAdvStringBuilder.Delete(iIndex, iLength : Integer);
Begin
  System.Delete(FContent, iIndex+1, iLength);
  Dec(FLength, iLength);
End;


Function TAdvStringBuilder.IndexOf(Const sStr : String; bCase : Boolean = False) : Integer;
Var
  iLoop : Integer;
  iUpper : Integer;
  iLen : Integer;
Begin
  Result := -1;
  iLoop := 1;
  iLen := System.Length(sStr);
  iUpper := FLength - iLen + 1;

  While (Result = -1) And (iLoop <= iUpper) Do
  Begin
    If (bCase And (Copy(FContent, iLoop, iLen) = sStr)) Or (Not bCase And StringEquals(Copy(FContent, iLoop, iLen), sStr)) Then
      Result := iLoop - 1;

    Inc(iLoop);
  End;
End;


Function TAdvStringBuilder.LastIndexOf(Const sStr : String; bCase : Boolean = False) : Integer;
Var
  iLoop : Integer;
  iUpper : Integer;
  iLen : Integer;
Begin
  Result := -1;
  iLen := System.Length(sStr);
  iUpper := FLength - iLen + 1;
  iLoop := iUpper;
  While (Result = -1) And (iLoop > 0) Do
  Begin
    If (bCase And (Copy(FContent, iLoop, iLen) = sStr)) Or (Not bCase And StringEquals(Copy(FContent, iLoop, iLen), sStr)) Then
      Result := iLoop - 1;

    Dec(iLoop);
  End;
End;


Procedure TAdvStringBuilder.Append(Const oStream : TAdvStream; iBytes : Integer);
Begin
  If (iBytes > 0) Then
  Begin
    If FLength + iBytes > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + Integermax(FBufferSize, iBytes));

    oStream.Read(FContent[FLength + 1], iBytes);

    Inc(FLength, iBytes);
  End;
End;


Procedure TAdvStringBuilder.Insert(Const oStream : TAdvStream; iBytes : Integer; iIndex : Integer);
Begin
  If (iBytes > 0) Then
  Begin
    If FLength + iBytes > System.Length(FContent) Then
      SetLength(FContent, System.Length(FContent) + IntegerMax(FBufferSize, iBytes));

    If (iIndex) <> FLength Then
      Move(FContent[iIndex+1], FContent[iIndex+1 + iBytes], (FLength - iIndex) * SizeOf(Char));

    oStream.Read(FContent[iIndex + 1], iBytes);

    Inc(FLength, iBytes);
  End;
End;



Procedure TAdvStringBuilder.CommaAdd(const sStr: String);
Begin
  if Length > 0 Then
    Append(', ');
  Append(sStr);
End;


Procedure TAdvStringBuilder.AddCardinalAsBytes(iVal: Cardinal);
Var
  s : AnsiString;
Begin
  SetLength(s, 4);
  move(iVal, s[1], 4);
  Append(s);
End;


Procedure TAdvStringBuilder.AddWordAsBytes(iVal: word);
Var
  s : AnsiString;
Begin
  SetLength(s, 2);
  move(iVal, s[1], 2);
  Append(s);
End;


Procedure TAdvStringBuilder.AddInt64AsBytes(iVal: Int64);
Var
  s : AnsiString;
Begin
  SetLength(s, 8);
  move(iVal, s[1], 8);
  Append(s);
End;


Procedure TAdvStringBuilder.AddByteAsBytes(iVal: Byte);
Var
  s : AnsiString;
Begin
  SetLength(s, 1);
  move(iVal, s[1], 1);
  Append(s);
End;


Procedure TAdvStringBuilder.AppendLine(const sStr: String);
Begin
  Append(sStr);
  AppendEOL;
End;

procedure TAdvStringBuilder.Append(const bBytes: array of Byte; amount: Integer);
var
  i : integer;
begin
  for i := 0 to amount - 1 Do
    Append(chr(bBytes[i]));

end;


procedure TAdvStringBuilder.Overwrite(index: integer; content: String);
begin
  if index < 1 Then
    Error('Overwrite', 'index < 1');
  if index + System.length(Content) > FLength Then
    Error('Overwrite', 'index > length');
  if content <> '' Then
    Move(Content[1], FContent[index], System.length(Content));
end;

procedure TAdvStringBuilder.Read(index: integer; var buffer; ilength: integer);
begin
  if index < 1 Then
    Error('Read', 'index < 1');
  if index + length > FLength Then
    Error('Read', 'index > length');
  Move(FContent[index], buffer, length);
end;

{$ELSE}

Const
  BUFFER_INCREMENT_SIZE = 1024;


Procedure TAdvStringBuilder.Clear;
Begin
  FBuilder.Clear;
End;


Function TAdvStringBuilder.GetAsString : String;
Begin
  Result := FBuilder.ToString;
End;



Procedure TAdvStringBuilder.AppendPadded(Const sStr : String; iCount : Integer; cPad : Char = ' ');
Begin
  FBuilder.Append(StringPadRight(sStr, cPad, iCount));
End;

Procedure TAdvStringBuilder.AppendFixed(Const sStr : String; iCount : Integer; cPad : Char = ' ');
Begin
  FBuilder.Append(StringPadRight(copy(sStr, 1, iCount), cPad, iCount));
End;


Procedure TAdvStringBuilder.Append(ch : Char);
Begin
  FBuilder.Append(ch);
End;

Procedure TAdvStringBuilder.Append(Const sStr : String);
Begin
  FBuilder.Append(sStr);
End;



Procedure TAdvStringBuilder.Append(Const oBuilder : TAdvStringBuilder);
Begin
  Append(oBuilder.AsString);
End;


procedure TAdvStringBuilder.Append(const sStr: AnsiString);
begin

end;

Procedure TAdvStringBuilder.AppendEOL;
Begin
  Append(cReturn);
End;


Procedure TAdvStringBuilder.Insert(Const sStr : String; iIndex : Integer);
Begin
  FBuilder.Insert(iIndex, sStr);
End;


Procedure TAdvStringBuilder.Insert(Const oBuilder : TAdvStringBuilder; iIndex : Integer);
Begin
  Insert(oBuilder.AsString, iIndex);
End;


procedure TAdvStringBuilder.WriteToStream(aStream: TAdvStream; encoding: TEncoding);
var
  a : TBytes;
Begin
  if encoding = nil then
    encoding := TEncoding.UTF8;
  a := encoding.GetBytes(FBuilder.ToString);
  if System.length(a) > 0 then
    aStream.Write(a[0], System.length(a));
end;

procedure TAdvStringBuilder.WriteToStream(aStream: TStream; encoding : TEncoding);
var
  a : TBytes;
Begin
  if encoding = nil then
    encoding := TEncoding.UTF8;
  a := encoding.GetBytes(FBuilder.ToString);
  if System.length(a) > 0 then
    aStream.Write(a[0], System.length(a));
End;

Procedure TAdvStringBuilder.Delete(iIndex, iLength : Integer);
Begin
  FBuilder.Remove(iIndex, iLength);
End;


destructor TAdvStringBuilder.Destroy;
begin
  FBuilder.Free;
  inherited;
end;

function TAdvStringBuilder.GetLength: Integer;
begin
  result := FBuilder.Length;
end;

constructor TAdvStringBuilder.Create;
begin
  inherited;
  FBuilder := TStringBuilder.Create;
end;

procedure TAdvStringBuilder.CommaAdd(const sStr: String);
begin
  if Length > 0 Then
    Append(', ');
  Append(sStr);
end;

procedure TAdvStringBuilder.AddCardinalAsBytes(iVal: Cardinal);
var
  s : AnsiString;
begin
  SetLength(s, 4);
  move(iVal, s[1], 4);
  Append(s);
end;

procedure TAdvStringBuilder.AddWordAsBytes(iVal: word);
var
  s : AnsiString;
begin
  SetLength(s, 2);
  move(iVal, s[1], 2);
  Append(s);
end;

procedure TAdvStringBuilder.AddInt64AsBytes(iVal: Int64);
var
  s : AnsiString;
begin
  SetLength(s, 8);
  move(iVal, s[1], 8);
  Append(s);
end;

procedure TAdvStringBuilder.AddByteAsBytes(iVal: Byte);
var
  s : AnsiString;
begin
  SetLength(s, 1);
  move(iVal, s[1], 1);
  Append(s);
end;

procedure TAdvStringBuilder.AppendLine(const sStr: String);
begin
  Append(sStr);
  AppendEOL;
end;
{$ENDIF}

End.
