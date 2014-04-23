Unit AdvTextFormatters;

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
