Unit AdvStringStreams;


{! 7 !}


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
