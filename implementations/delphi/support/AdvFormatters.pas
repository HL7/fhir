Unit AdvFormatters;


{! 5 !}


Interface


Uses
  SysUtils, StringSupport,
  AdvExceptions, AdvStreams;


Type
  TAdvFormatter = Class(TAdvStreamAdapter)
    Protected
      Function ErrorClass : EAdvExceptionClass; Overload; Override;

      Procedure SetStream(oStream : TAdvStream); Override;

    Public
      Procedure Clear; Overload; Virtual;

      Procedure ProduceBytes(Const aBytes : TBytes); Overload; Virtual;
      Procedure Produce(Const sText: String); Overload; Virtual;
  End;

  EAdvFormatter = Class(EAdvStream);


Implementation


Function TAdvFormatter.ErrorClass : EAdvExceptionClass;
Begin 
  Result := EAdvFormatter;
End;


Procedure TAdvFormatter.Clear;
Begin
End;


Procedure TAdvFormatter.ProduceBytes(Const aBytes : TBytes);
Begin
  Write(aBytes[0], Length(aBytes));
End;

Procedure TAdvFormatter.Produce(Const sText: String);
{$IFDEF VER130}
Begin
  Write(Pointer(sText)^, Length(sText));
End;
{$ELSE}
Var
  Bytes : TBytes;
Begin
  Assert(Condition(sText <> '', 'Produce', 'Text must not be empty.'));

  Bytes := SysUtils.TEncoding.UTF8.GetBytes(sText);

  Write(Bytes[0], Length(Bytes));
End;
{$ENDIF}


Procedure TAdvFormatter.SetStream(oStream: TAdvStream);
Begin
  Inherited;

  Clear;
End;


End. // AdvFormatters //
