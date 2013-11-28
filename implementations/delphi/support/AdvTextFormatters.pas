Unit AdvTextFormatters;


{! 8 !}


Interface


Uses
  SysUtils,
  StringSupport,
  AdvFormatters;


Type
  TAdvTextFormatter = Class(TAdvFormatter)
    Private
      FLevel : Integer;
      FHasWhitespace : Boolean;
      FWhitespaceCharacter : Char;
      FWhitespaceMultiple : Integer;
      {$IFNDEF VER130}
      FEncoding: TEncoding;
      {$ENDIF}

    Protected
      Function BeforeWhitespace : String;
      Function AfterWhitespace : String;

    Public
      Constructor Create; Override;

      Function Link : TAdvTextFormatter;

      Procedure Clear; Override;

      Procedure ProduceNewLine; Virtual;
      Procedure ProduceLine(Const sValue : String);
      Procedure ProduceInline(Const sValue : String);

      Procedure ProduceFragment(Const sValue : String);

      Procedure LevelDown;
      Procedure LevelUp;

      Property HasWhitespace : Boolean Read FHasWhitespace Write FHasWhitespace;
      Property WhitespaceCharacter : Char Read FWhitespaceCharacter Write FWhitespaceCharacter;
      Property WhitespaceMultiple : Integer Read FWhitespaceMultiple Write FWhitespaceMultiple;
      Property Level : Integer Read FLevel;
      {$IFNDEF VER130}
      Property Encoding : TEncoding read FEncoding Write FEncoding;
      {$ENDIF}
  End;

  TAdvTextFormatterClass = Class Of TAdvTextFormatter;


Implementation


Constructor TAdvTextFormatter.Create;
Begin
  Inherited;

  FHasWhitespace := True;
  FWhitespaceCharacter := ' ';
  FWhitespaceMultiple := 2;
  {$IFNDEF VER130}
  Encoding := SysUtils.TEncoding.UTF8;
  {$ENDIF}
End;


Function TAdvTextFormatter.Link : TAdvTextFormatter;
Begin
  Result := TAdvTextFormatter(Inherited Link);
End;


Procedure TAdvTextFormatter.Clear;
Begin 
  Inherited;

  FLevel := 0;
End;  


Function TAdvTextFormatter.BeforeWhitespace : String;
Begin 
  // Multiply of the space character by FLevel * 2 is more efficient than Multiply of string '  ' by FLevel because it uses FillChar.

  If FHasWhitespace Then
    Result := StringMultiply(FWhitespaceCharacter, FLevel * FWhitespaceMultiple)
  Else
    Result := '';
End;  


Function TAdvTextFormatter.AfterWhitespace : String;
Begin 
  If FHasWhitespace Then
    Result := cReturn
  Else
    Result := '';
End;


Procedure TAdvTextFormatter.ProduceFragment(Const sValue: String);
Begin 
  Produce(sValue);
End;  


Procedure TAdvTextFormatter.ProduceLine(Const sValue: String);
Begin
  Produce(BeforeWhitespace + sValue + AfterWhitespace);
End;


Procedure TAdvTextFormatter.ProduceNewLine;
Begin 
  Produce(cReturn);
End;  


Procedure TAdvTextFormatter.LevelDown;
Begin 
  Inc(FLevel);
End;  


Procedure TAdvTextFormatter.LevelUp;
Begin 
  Dec(FLevel);
End;  


Procedure TAdvTextFormatter.ProduceInline(Const sValue: String);
Begin
  Produce(sValue);
End;


End.
