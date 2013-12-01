Unit AdvFilers;

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
  StringSupport, DateSupport, ColourSupport, CurrencySupport,
  AdvObjects, AdvExceptions;


Type
  TAdvTag =
    (atUnknown, atBoolean, atInteger32, atLongString, atReal, atBinary, atEnumerated8,
     atObject, atClass, atBegin, atEnd, atNil, atInteger64, atInteger16, atInteger8,
     atShortString, atSet, atCharacter, atReference, atResource, atDateTime, atColour, atExtended,
     atDuration, atCurrency, atEnumerated16);

  TAdvFiler = Class(TAdvObject)
    Private
      FField : String;

    Protected
      Procedure Error(Const sMethod, sMessage : String); Override;

      Function GetField : String; Virtual;
      Function SetField(Const sField : String) : TAdvFiler; Virtual;
      Function UseField : String; Virtual;

    Public
      Procedure DefineValue(Value : TAdvTag); Virtual;
      Procedure DefineBegin; Virtual;
      Procedure DefineEnd; Virtual;

      Procedure DefineInteger(Var Value : Integer); Overload; Virtual;
      Procedure DefineInteger(Var Value : Int64); Overload; Virtual;
      Procedure DefineInteger(Var Value : Cardinal); Overload; Virtual;
      Procedure DefineInteger(Var Value : Word); Overload; Virtual;
      Procedure DefineInteger(Var Value : Byte); Overload; Virtual;

      Procedure DefineReal(Var Value : Real); Overload; Virtual;
      Procedure DefineReal(Var Value : Extended); Overload; Virtual;

      Procedure DefineBoolean(Var Value : Boolean); Virtual;

      Procedure DefineChar(Var Value : Char); Overload; Virtual;
      {$IFNDEF FPC}
      {$IFNDEF VER130}
      Procedure DefineString(Var Value : TLongString); Overload; Virtual;
      Procedure DefineString(Var Value : AnsiChar); Overload; Virtual;
      Procedure DefineString(Var Value : TShortString); Overload; Virtual;
      {$ENDIF}
      {$ENDIF}
      Procedure DefineString(Var Value : AnsiString); Overload; Virtual;

      Procedure DefineBinary(Var Value; iCount : Integer); Virtual;

      Procedure DefineEnumerated(Var Value; Const aNames : Array Of String; Const sEnumerationName : String = ''); Virtual;
      Procedure DefineSet(Var Value; Const aNames : Array Of String; Const sEnumerationName : String = ''); Virtual;

      Procedure DefineDateTime(Var Value : TDateTime); Virtual;
      Procedure DefineDuration(Var Value : TDuration); Virtual;
      Procedure DefineColour(Var Value : TColour); Virtual;
      Procedure DefineCurrency(Var Value : TCurrency); Virtual;

      Procedure DefineClass(Var Value; aClass : TAdvObjectClass = Nil); Virtual;
      Procedure DefineObject(Var Value; aClass : TAdvObjectClass = Nil); Virtual;
      Procedure DefineReference(Var Value; aClass : TAdvObjectClass = Nil); Virtual;
      Procedure DefineResource(Var Value; aClass : TAdvObjectClass = Nil); Virtual;

      Function Peek : TAdvTag; Overload; Virtual;
      Function PeekField : String; Overload; Virtual;

      Property Fields[Const sField : String] : TAdvFiler Read SetField; Default;
      Property Field : String Read GetField;
  End; 

  TAdvFilerClass = Class Of TAdvFiler;

  EAdvFiler = Class(EAdvException);

  TAdvObjectClass = AdvObjects.TAdvObjectClass;

  TLongString = StringSupport.TLongString;
  TShortString = StringSupport.TShortString;
  TColour = ColourSupport.TColour;
  TDateTime = DateSupport.TDateTime;
  TDuration = DateSupport.TDuration;
  TCurrency = CurrencySupport.TCurrency;


Function StringToTag(Const sValue : String) : TAdvTag;
Function TagToString(atType : TAdvTag) : String; 


Implementation


Const
  TAG_STRING : Array[TAdvTag] Of String =
    ('Unknown', 'Boolean', 'Integer', 'String', 'Real', 'Binary', 'Enumerated',
     'Object', 'Class', 'Begin', 'End', 'Nil', 'Integer64', 'Integer16', 'Integer8',
     'ShortString', 'Set', 'Character', 'Reference', 'Resource', 'DateTime', 'Colour',
     'Extended', 'Duration', 'Currency', 'Enumerated16');


Function StringToTag(Const sValue : String) : TAdvTag;
Begin
  // Returns atUnknown if it isn't a valid string.

  Result := High(TAdvTag);
  While (Result > Low(TAdvTag)) And Not StringEquals(TAG_STRING[Result], sValue) Do
    Dec(Result);
End;


Function TagToString(atType : TAdvTag) : String;
Begin
  If (Integer(atType) > Integer(High(TAdvTag))) Or (Integer(atType) < 0) Then
    Result := StringFormat('Invalid (%d)', [Integer(atType)])
  Else
    Result := TAG_STRING[atType];
End;  


Procedure TAdvFiler.Error(Const sMethod, sMessage: String);
Begin
  Error(EAdvFiler, sMethod, sMessage);
End;  


Procedure TAdvFiler.DefineValue(Value : TAdvTag);
Begin 
End;  


Procedure TAdvFiler.DefineBegin;
Begin 
  DefineValue(atBegin);
End;  


Procedure TAdvFiler.DefineEnd;
Begin 
  DefineValue(atEnd);
End;  


Procedure TAdvFiler.DefineBinary(Var Value; iCount : Integer);
Begin 
  DefineValue(atBinary);
End;  


Procedure TAdvFiler.DefineBoolean(Var Value : Boolean);
Begin 
  DefineValue(atBoolean);
End;  


Procedure TAdvFiler.DefineInteger(Var Value : Integer);
Begin 
  DefineValue(atInteger32);
End;  


Procedure TAdvFiler.DefineInteger(Var Value : Int64);
Begin 
  DefineValue(atInteger64);
End;  


Procedure TAdvFiler.DefineInteger(Var Value : Cardinal);
Begin 
  DefineValue(atInteger32);
End;  


Procedure TAdvFiler.DefineInteger(Var Value : Word);
Begin 
  DefineValue(atInteger16);
End;  


Procedure TAdvFiler.DefineInteger(Var Value : Byte);
Begin 
  DefineValue(atInteger8);
End;


Procedure TAdvFiler.DefineReal(Var Value : Real);
Begin 
  DefineValue(atReal);
End;  


Procedure TAdvFiler.DefineReal(Var Value : Extended);
Begin
  DefineValue(atExtended);
End;

Procedure TAdvFiler.DefineString(Var Value : AnsiString);
Begin
  DefineValue(atLongString);
End;

Procedure TAdvFiler.DefineChar(Var Value : Char);
Begin
  DefineValue(atCharacter);
End;

{$IFNDEF FPC}
{$IFNDEF VER130}
Procedure TAdvFiler.DefineString(Var Value : TLongString);
Begin
  DefineValue(atLongString);
End;



procedure TAdvFiler.DefineString(var Value: AnsiChar);
begin
  DefineValue(atCharacter);
end;



Procedure TAdvFiler.DefineString(Var Value : TShortString);
Begin
  DefineValue(atShortString);
End;
{$ENDIF}
{$ENDIF}

Procedure TAdvFiler.DefineEnumerated(Var Value; Const aNames : Array Of String; Const sEnumerationName : String = '');
Begin
  DefineValue(atEnumerated8);
End;


Procedure TAdvFiler.DefineSet(Var Value; Const aNames : Array Of String; Const sEnumerationName : String = '');
Begin 
  DefineValue(atSet);
End;  


Procedure TAdvFiler.DefineDateTime(Var Value : TDateTime);
Begin 
  DefineValue(atDateTime);
End;  


Procedure TAdvFiler.DefineDuration(Var Value : TDuration);
Begin 
  DefineValue(atDuration);
End;  


Procedure TAdvFiler.DefineColour(Var Value : TColour);
Begin 
  DefineValue(atColour);
End;  


Procedure TAdvFiler.DefineCurrency(Var Value : TCurrency);
Begin 
  DefineValue(atCurrency);
End;  


Procedure TAdvFiler.DefineClass(Var Value; aClass : TAdvObjectClass);
Begin 
  DefineValue(atClass);
End;  


Procedure TAdvFiler.DefineObject(Var Value; aClass : TAdvObjectClass);
Begin 
  DefineValue(atObject);
End;  


Procedure TAdvFiler.DefineReference(Var Value; aClass : TAdvObjectClass);
Begin 
  DefineValue(atReference);
End;  


Procedure TAdvFiler.DefineResource(Var Value; aClass: TAdvObjectClass);
Begin 
  DefineValue(atResource);
End;  


Function TAdvFiler.GetField : String;
Begin 
  Result := FField;
End;  


Function TAdvFiler.SetField(Const sField: String): TAdvFiler;
Begin 
  FField := sField;
  Result := Self;
End;  


Function TAdvFiler.UseField : String;
Begin 
  Result := FField;
  FField := '';
End;  


Function TAdvFiler.PeekField : String;
Begin 
  Peek;
  Result := Field;
End;  


Function TAdvFiler.Peek : TAdvTag;
Begin 
  Result := atUnknown;
End;  


End. // AdvFilers //
