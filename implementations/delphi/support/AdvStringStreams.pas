Unit AdvStringStreams;

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
  SysUtils, BytesSupport, AdvStreams;


Type
  TAdvStringStream = Class(TAdvAccessStream)
    Private
      FData : AnsiString;
      FIndex : Cardinal;

      Procedure SetData(Const Value: AnsiString);
    function GetBytes: TBytes;
    procedure SetBytes(const Value: TBytes);

    Protected
      Function GetPosition : Int64; Override;
      Procedure SetPosition(Const iValue : Int64); Override;

      Function GetSize : Int64; Override;
      Procedure SetSize(Const iValue : Int64); Override;

    Public
      Procedure Read(Var aBuffer; iCount : Cardinal); Override;
      Procedure Write(Const aBuffer; iCount : Cardinal); Override;

      Function Readable : Int64; Override;
      Function Writeable : Int64; Override;

      Property Data : AnsiString Read FData Write SetData;
      Property Bytes : TBytes Read GetBytes Write SetBytes;
  End;


Implementation


Procedure TAdvStringStream.Read(Var aBuffer; iCount: Cardinal);
Begin
  If FIndex + iCount > Size Then
    Error('Read', 'Unable to read past end of string.');

  Move((PAnsiChar(FData) + FIndex)^, aBuffer, iCount);
  Inc(FIndex, iCount);
End;


Procedure TAdvStringStream.Write(Const aBuffer; iCount: Cardinal);
Begin
  If FIndex + iCount > Size Then
    Size := FIndex + iCount;

  Move(aBuffer, (PAnsiChar(FData) + FIndex)^, iCount);
  Inc(FIndex, iCount);
End;


Function TAdvStringStream.Writeable : Int64;
Begin
  Result := High(Result);
End;


Function TAdvStringStream.Readable : Int64;
Begin
  Result := Size - Position;
End;


function TAdvStringStream.GetBytes: TBytes;
begin
  result := AnsiStringAsBytes(FData);
end;

Function TAdvStringStream.GetPosition : Int64;
Begin
  Result := FIndex;
End;


Procedure TAdvStringStream.SetPosition(Const iValue: Int64);
Begin
  FIndex := iValue;
End;


Function TAdvStringStream.GetSize : Int64;
Begin
  Result := Length(FData);
End;


Procedure TAdvStringStream.SetSize(Const iValue: Int64);
Begin
  SetLength(FData, iValue);
  If FIndex > Cardinal(Length(FData)) Then
    FIndex := Length(FData);
End;


procedure TAdvStringStream.SetBytes(const Value: TBytes);
begin
  FData := BytesAsAnsiString(value);
end;

Procedure TAdvStringStream.SetData(Const Value: AnsiString);
Begin
  FData := Value;
  If FIndex > Cardinal(Length(FData)) Then
    FIndex := Length(FData)
  Else
    FIndex := 0;
End;


End. // AdvStringStreams //
