Unit EncodeSupport;

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
  StringSupport, MathSupport;

Type
  TXmlEncodingMode = (xmlText, xmlAttribute);

  TEolnOption = (eolnIgnore, eolnCanonical, eolnEscape);

Function EncodeNYSIIS(Const sValue : String) : String;
{
Function EncodeBase64(Const sValue : TBytes) : AnsiString; Overload;
Function DecodeBase64(Const sValue : AnsiString) : TBytes; Overload;
}

Function EncodeXML(Const sValue : String; mode : TXmlEncodingMode; eoln : TEolnOption = eolnIgnore) : String; Overload;
Function DecodeXML(Const sValue : String) : String; Overload;
Function EncodeQuotedString(Const sValue : String; Const cQuote : Char) : String; Overload;
Function EncodeMIME(Const sValue : String) : String; Overload;
Function DecodeMIME(Const sValue : String) : String; Overload;
Function SizeOfDecodeHexadecimal(Const aBuffer; iSize : Cardinal) : Cardinal; Overload;
Function DecodeHexadecimal(Const cHigh, cLow : AnsiChar) : Byte; Overload;
Procedure DecodeHexadecimal(Const sValue : AnsiString; Var aBuffer; iCount : Integer); Overload;

Function SizeOfEncodeHexadecimal(Const aBuffer; iSize : Cardinal) : Cardinal; Overload;
Function EncodeHexadecimal(Const iValue : Byte) : AnsiString; Overload;
Function EncodeHexadecimal(Const aBuffer; iCount : Integer) : AnsiString; Overload;
Function EncodeHexadecimal(Const sValue : TBytes) : AnsiString; Overload;

Implementation

{$IFDEF VER130}
Type
  PByte = ^Byte;
{$ENDIF}

Const
  setMIME = setAlphanumeric + ['/', '?', ':', ';', '.', '+'];

  ENCODE_BASE64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/';
  DECODE_BASE64 : Array[AnsiChar] Of Byte =
    (
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 062, 255, 255, 255, 063,
    052, 053, 054, 055, 056, 057, 058, 059, 060, 061, 255, 255, 255, 255, 255, 255,
    255, 000, 001, 002, 003, 004, 005, 006, 007, 008, 009, 010, 011, 012, 013, 014,
    015, 016, 017, 018, 019, 020, 021, 022, 023, 024, 025, 255, 255, 255, 255, 255,
    255, 026, 027, 028, 029, 030, 031, 032, 033, 034, 035, 036, 037, 038, 039, 040,
    041, 042, 043, 044, 045, 046, 047, 048, 049, 050, 051, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
    255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255
    );

Function SizeOfEncodeBase64(Const aBuffer; iSize : Cardinal) : Cardinal; Overload;
Begin
  Result := (RealCeiling(iSize / 3)) * 4;
End;

Function SizeOfDecodeBase64(Const aBuffer; iSize : Cardinal) : Cardinal; Overload;
Begin
  Result := (RealCeiling(iSize / 4)) * 3;
End;

Function SizeOfDecodeBase64(Const sValue : AnsiString) : Cardinal; Overload;
Begin
  Result := SizeOfDecodeBase64(Pointer(sValue)^, Length(sValue));
End;

Function DecodeBase64(Const cValue : AnsiChar) : Byte; Overload;
Begin
  Result := DECODE_BASE64[cValue];
End;


Function DecodeBase64(Const aSource : AnsiString; Var aTarget; iCount : Cardinal) : Cardinal; Overload;
Var
  pSource  : PAnsiChar;
  iSource  : Cardinal;

  Function Decode : Byte;
  Begin
    Result := 255;

    While (Cardinal(pSource) < iSource) And (Result = 255) Do
    Begin
      Result := DecodeBase64(pSource^);
      Inc(pSource);
    End;
  End;

Var
  pTarget  : PByte;
  iTarget  : Cardinal;
  iData    : Integer;
Begin
  pSource := @aSource;
  pTarget := @aTarget;

  If iCount > 0 Then
  Begin
    iSource := Cardinal(@aSource) + iCount;
    iTarget := Cardinal(@aSource) + ((RealCeiling(iCount / 4) - 1) * 4);

    // Process all complete 4 byte elements.
    While (Cardinal(pSource) < iTarget) Do
    Begin
      iData := (Decode Shl 18) Or (Decode Shl 12) Or (Decode Shl 6) Or Decode;

      pTarget^ := Byte(iData Shr 16);
      Inc(pTarget);

      pTarget^ := Byte(iData Shr 8);
      Inc(pTarget);

      pTarget^ := Byte(iData);
      Inc(pTarget);
    End;

    If Cardinal(pSource) < iSource Then
    Begin
      // Read first byte and start of second byte.
      iData := (Decode Shl 18);
      iData := iData Or (Decode Shl 12);

      // Write first byte.
      pTarget^ := Byte(iData Shr 16);
      Inc(pTarget);

      If (Cardinal(pSource) < iSource) And (pSource^ <> '=') Then
      Begin
        // Read end of second and start of third byte.
        iData := iData Or (Decode Shl 6);

        // Write second byte.
        pTarget^ := Byte(iData Shr 8);
        Inc(pTarget);

        If (Cardinal(pSource) < iSource) And (pSource^ <> '=') Then
        Begin
          // Read end of third byte.
          iData := iData Or Decode;

          // Write third byte.
          pTarget^ := Byte(iData);
          Inc(pTarget);
        End;
      End;
    End;
  End;

  Result := Cardinal(pTarget) - Cardinal(@aTarget);
End;

Function DecodeBase64(Const sValue : AnsiString) : TBytes; Overload;
Begin
  SetLength(Result, SizeOfDecodeBase64(sValue));

  SetLength(Result, DecodeBase64(sValue, Pointer(Result)^, Length(sValue)));
End;

Function EncodeBase64(Const iValue : Byte) : AnsiChar; Overload;
Begin
  Result := AnsiChar(ENCODE_BASE64[iValue + 1]); // Array is 1-based.
End;


Function EncodeBase64(Const aSource; Var aTarget; iCount : Cardinal) : Cardinal; Overload;

  Function Flip(iValue : Integer) : Integer;
  Var
    pSource : PByte;
    pTarget : PByte;
  Begin
    pSource := PByte(@iValue);
    pTarget := PByte(Integer(@Result) + SizeOf(Result) - 1);

    pTarget^ := pSource^;
    Inc(pSource);
    Dec(pTarget);

    pTarget^ := pSource^;
    Inc(pSource);
    Dec(pTarget);

    pTarget^ := pSource^;
    Inc(pSource);
    Dec(pTarget);

    pTarget^ := pSource^;
  End;

Var
  pSource : ^Integer;
  pTarget : PAnsiChar;
  iSource : Integer;
  iLoop   : Integer;
  iTarget : Integer;
  iData   : Cardinal;
Begin
  pSource := @aSource;
  pTarget := @aTarget;

  iSource := (iCount Div 3);

  For iLoop := 0 To iSource - 1 Do
  Begin
    iData := Cardinal(Flip(pSource^));

    pTarget^ := EncodeBase64((iData Shr 26) And $3F);
    Inc(pTarget);

    pTarget^ := EncodeBase64((iData Shr 20) And $3F);
    Inc(pTarget);

    pTarget^ := EncodeBase64((iData Shr 14) And $3F);
    Inc(pTarget);

    pTarget^ := EncodeBase64((iData Shr 8) And $3F);
    Inc(pTarget);

    pSource := Pointer(NativeUInt(pSource) + 3);
  End;

  iTarget := SignedMod(iCount, 3);

  If iTarget >= 1 Then
  Begin
    iData := Cardinal(Flip(pSource^));

    If iTarget = 1 Then
      iData := iData And $FF000000
    Else If iTarget = 2 Then
      iData := iData And $FFFF0000;

    pTarget^ := EncodeBase64((iData Shr 26) And $3F);
    Inc(pTarget);

    pTarget^ := EncodeBase64((iData Shr 20) And $3F);
    Inc(pTarget);

    If iTarget >= 2 Then
      pTarget^ := EncodeBase64((iData Shr 14) And $3F)
    Else
      pTarget^ := '=';
    Inc(pTarget);

    pTarget^ := '=';
    Inc(pTarget);
  End;

  Result := Cardinal(pTarget) - Cardinal(@aTarget);
End;

Function EncodeBase64(Const sValue : TBytes) : AnsiString; Overload;
Begin
  SetLength(Result, SizeOfEncodeBase64(Pointer(sValue)^, Length(sValue)));

  SetLength(Result, EncodeBase64(Pointer(sValue)^, Pointer(Result)^, Length(sValue)));
End;

Function SizeOfDecodeHexadecimal(Const aBuffer; iSize : Cardinal) : Cardinal;
Begin
  Result := RealCeiling(iSize / 2);
End;

Function DecodeHexadecimal(Const cValue : AnsiChar; Out iValue : Byte) : Boolean; overload;
Begin
  Result := True;

  Case cValue Of
    '0'..'9' : iValue := Ord(cValue) - Ord('0');
    'A'..'F' : iValue := Ord(cValue) - Ord('A') + 10;
    'a'..'f' : iValue := Ord(cValue) - Ord('a') + 10;
  Else
    iValue := 0;

    Result := False;
  End;
End;

Procedure DecodeHexadecimal(Const cHigh, cLow : AnsiChar; Out iValue : Byte); overload;
Var
  iLow, iHigh : Byte;
Begin
  If DecodeHexadecimal(cHigh, iHigh) And DecodeHexadecimal(cLow, iLow) Then
    iValue := (iHigh Shl 4) Or iLow
  Else
    iValue := 0;
End;


Function DecodeHexadecimal(Const cHigh, cLow : AnsiChar) : Byte;
Begin
  DecodeHexadecimal(cHigh, cLow, Result);
End;
Procedure DecodeHexadecimal(Const sValue : AnsiString; Var aBuffer; iCount : Integer);
Type
  PByte = ^Byte;
Var
  pTarget : PByte;
  pSource : PAnsiChar;
  iLoop   : Integer;
Begin
  pTarget := @aBuffer;
  pSource := PAnsiChar(sValue);

  For iLoop := 0 To iCount - 1 Do
  Begin
    pTarget^ := DecodeHexadecimal(pSource^, (pSource + 1)^);

    Inc(pSource, 2);
    Inc(pTarget);
  End;
End;

Function SizeOfEncodeHexadecimal(Const aBuffer; iSize : Cardinal) : Cardinal;
Begin
  Result := iSize * 2;
End;

Function EncodeHexadecimal(Const iValue : Byte) : AnsiString;
Begin
  Result := EncodeHexadecimal(iValue, SizeOf(iValue));
End;

Function EncodeHexadecimal(Const sValue : TBytes) : AnsiString;
Begin
  Result := EncodeHexadecimal(Pointer(sValue)^, Length(sValue));
End;

Function EncodeHexadecimal(Const aBuffer; iCount : Integer) : AnsiString;
Const
  cHex : Array[0..15] Of AnsiChar = ('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');
Type
  PByte = ^Byte;
Var
  pBuffer : PByte;
  iResult : Integer;
  iLoop   : Integer;
Begin
  SetLength(Result, SizeOfEncodeHexadecimal(aBuffer, iCount));

  pBuffer := @aBuffer;
  iResult := 1;

  For iLoop := 0 To iCount - 1 Do
  Begin
    Result[iResult] := cHex[Byte(pBuffer^ Shr 4)];   // High Nibble
    Inc(iResult);

    Result[iResult] := cHex[Byte(pBuffer^ And $0F)]; // Low Nibble
    Inc(iResult);

    Inc(pBuffer);
  End;
End;


Function EncodeXML(Const sValue : String; mode : TXmlEncodingMode; eoln : TEolnOption = eolnIgnore) : String;
Var
  iLoop : Integer;
  cValue : Char;
Begin
  Result := sValue;
  iLoop := 1;
  While iLoop <= Length(Result) Do
  Begin
    cValue := Result[iLoop];

    Case cValue Of
      #10, #13:
      if (mode = xmlAttribute) or (cValue = #13) then
      Begin
        Delete(Result, iLoop, 1);
        Insert('&#x' + IntToHex(Ord(cValue), 1) + ';', Result, iLoop);
        Inc(iLoop, 4);
      End
      else
      case eoln of
        eolnIgnore : Inc(iLoop);
        eolnEscape :
          Begin
          Delete(Result, iLoop, 1);
          Insert('&#x' + IntToHex(Ord(cValue), 1) + ';', Result, iLoop);
          Inc(iLoop, 4);
          End
      else //
        if cValue = #13 then
          Delete(Result, iLoop, 1)
         else
           Inc(iLoop);
      end;

      #9 : if mode <> xmlAttribute then
            Inc(iLoop)
          else
          begin
            Delete(Result, iLoop, 1);
            Insert('&#x9;', Result, iLoop);
            Inc(iLoop, 4);
          End;

      #0..#8, #11..#12, #14..#31{, #127..#255} :
      Begin
        Delete(Result, iLoop, 1);
        Insert('&#x' + IntToHex(Ord(cValue), 2) + ';', Result, iLoop);
        Inc(iLoop, 5);
      End;

      '<':
      Begin
        Delete(Result, iLoop, 1);
        Insert('&lt;', Result, iLoop);
        Inc(iLoop, 4);
      End;

      '>': if mode = xmlAttribute then
             Inc(iLoop)
           else
           Begin
             Delete(Result, iLoop, 1);
             Insert('&gt;', Result, iLoop);
             Inc(iLoop, 4);
           End;

      '"' :if mode <> xmlAttribute then
             Inc(iLoop)
           else
           Begin
             Delete(Result, iLoop, 1);
             Insert('&quot;', Result, iLoop);
             Inc(iLoop, 6);
           End;

      '&':
      Begin
        // Preceding '&' already exists in string.
        Insert('amp;', Result, iLoop + 1);
        Inc(iLoop, 4);
      End;

      // Only need to encode &quot; and &apos; in XML attributes...
    Else if ord(cValue) > 255 then
      Begin
        Delete(Result, iLoop, 1);
        Insert('&#x' + IntToHex(Ord(cValue), 4) + ';', Result, iLoop);
        Inc(iLoop, 7);
      End
    Else
      Inc(iLoop);
    End;
  End;
End;

Function DecodeXML(Const sValue : String) : String;
Var
  iLoop : Integer;
  pValue : PChar;
  iValue : Byte;
  sPrefixedEncodedDec : String;
  sRemainder : String;
  sEncodedDec : String;
  iEncodedDec : Integer;
Begin
  Result := sValue;
  iLoop := 1;
  While iLoop <= Length(Result) Do
  Begin
    pValue := @Result[iLoop];

    If pValue^ = '&' Then
    Begin
      If StringEquals(pValue, '&lt;', 4) Then
      Begin
        Delete(Result, iLoop, 4);
        Insert('<', Result, iLoop);
      End
      Else If StringEquals(pValue, '&gt;', 4) Then
      Begin
        Delete(Result, iLoop, 4);
        Insert('>', Result, iLoop);
      End
      Else If StringEquals(pValue, '&amp;', 5) Then
      Begin
        Delete(Result, iLoop, 5);
        Insert('&', Result, iLoop);
      End
      Else If StringEquals(pValue, '&quot;', 6) Then
      Begin
        Delete(Result, iLoop, 6);
        Insert('"', Result, iLoop);
      End
      Else If StringEquals(pValue, '&apos;', 6) Then
      Begin
        Delete(Result, iLoop, 6);
        Insert('''', Result, iLoop);
      End
      Else If StringEquals(pValue, '&#x', 3) Then
      Begin
        StringSplit(pValue, ';', sPrefixedEncodedDec, sRemainder);
        sEncodedDec := '0x'+copy(sPrefixedEncodedDec, 4, length(sPrefixedEncodedDec));
        iEncodedDec := StringToInteger32(sEncodedDec);
        iValue := iEncodedDec;
        Delete(Result, iLoop, Length(sPrefixedEncodedDec) + 1);
        Insert(Char(iValue), Result, iLoop);
      End
      Else If StringEquals(pValue, '&#', 2) Then
      Begin
        StringSplit(pValue, ';', sPrefixedEncodedDec, sRemainder);
        sEncodedDec := sPrefixedEncodedDec.substring(2);

        iEncodedDec := StringToInteger32(sEncodedDec);

        If (iEncodedDec >= 0) And (iEncodedDec <= 65535) Then
        Begin
          iValue := iEncodedDec;

          Delete(Result, iLoop, Length(sPrefixedEncodedDec) + 1); // eg. '&#13;' or '&#220;'
          Insert(Char(iValue), Result, iLoop);
        End;
      End;
    End;

    Inc(iLoop);
  End;
End;

Function EncodeQuotedString(Const sValue : String; Const cQuote : Char) : String;
Begin
  Result := AnsiQuotedStr(sValue, cQuote);
End;

Function EncodeMIME(Const sValue : String) : String;  overload;
Var
  iLoop : Integer;
  cValue : Char;
Begin
  Result := sValue;
  iLoop := 1;

  While (iLoop <= Length(Result)) Do
  Begin
    cValue := Result[iLoop];

    If Not (CharInSet(cValue, setMIME)) or (cValue = ':') Then
    Begin
      Result[iLoop] := '%';
      Inc(iLoop);

      System.Insert(string(EncodeHexadecimal(Byte(cValue))), Result, iLoop);
      Inc(iLoop);
    End;

    Inc(iLoop);
  End;
End;


Function DecodeMIME(Const sValue : String) : String;  overload;
Var
  iLoop : Integer;
  cValue : Char;
Begin
  Result := sValue;
  iLoop := 1;

  While (iLoop <= Length(Result) - 2) Do
  Begin
    cValue := Result[iLoop];

    if cValue = '+' then
      Result[iLoop] := ' ';

    If (cValue = '%') Then
    Begin
      Result[iLoop] := Char(DecodeHexadecimal(AnsiChar(Result[iLoop + 1]), AnsiChar(Result[iLoop + 2])));
      Delete(Result, iLoop + 1, 2);
    End;

    Inc(iLoop);
  End;
End;


Function EncodeNYSIIS(Const sValue : String) : String;
// NYSIIS Phonetic Encoder
// Based on Pseudo code from http://www.dropby.com/NYSIIS.html
Var
  cFirst : Char;
Begin

  // Remove non-alpha characters
  Result := StringKeep(StringUpper(RemoveAccents(sValue)), setAlphabet);

  //Remove space and control characters from end
  Result := StringTrimWhitespaceRight(Result);

  //Remove trailing 'S' and 'Z' characters
  Result := StringTrimSetRight(Result, ['S', 'Z']);

  If Length(Result) = 0 Then
    Result := 'S';


  If Result <> '' Then
  Begin
    // Transcode initial strings MAC => MC; PF => F
    Result := StringReplaceBefore(Result, 'MAC', 'MC');
    Result := StringReplaceBefore(Result, 'PF', 'F');

    // Transcode trailing strings as follows : IX => IC; EX => EC; YE, EE, IE => Y; NT, ND => D
    Result := StringReplaceAfter(Result, 'IX',  'IC');
    Result := StringReplaceAfter(Result, 'EX',  'EE');
    Result := StringReplaceAfter(Result, 'YE',  'Y');
    Result := StringReplaceAfter(Result, 'EE',  'Y');
    Result := StringReplaceAfter(Result, 'IE',  'Y');
    Result := StringReplaceAfter(Result, 'NT',  'N');
    Result := StringReplaceAfter(Result, 'ND',  'N');
    // Next 3 are not in the pseudo code, but are in the javascript from site
    Result := StringReplaceAfter(Result, 'DT',  'D');
    Result := StringReplaceAfter(Result, 'RT',  'D');
    Result := StringReplaceAfter(Result, 'RD',  'D');


    // Transcode 'EV' to 'EF' if not at the start of string
    Result := StringReplaceAll(Result, 'EV', 'EF', 2);

    cFirst := Result[1];

    // Replace all vowels with 'A'
    Result := StringReplace(Result, ['E', 'I', 'O', 'U'], 'A');

    // Remove any 'W' that follows a vowel
    Result := StringReplaceAll(Result, 'AW', 'A');

    // Transcode 'GHT' => 'GT'; 'DG' => 'G'; 'PH' => 'F'
    Result := StringReplaceAll(Result, 'GHT', 'GT');
    Result := StringReplaceAll(Result, 'DG', 'G');
    Result := StringReplaceAll(Result, 'PH', 'F');

    // If not first character, eliminate all 'H' preceded or followed by a vowel
    Result := StringReplaceAll(Result, 'AH', 'A');
    Result := StringReplaceAll(Result, 'HA', 'A', 2);

    // Change 'KN' to 'N', else 'K' to 'C'
    Result := StringReplaceAll(Result, 'KN', 'N');
    Result := StringReplaceAll(Result, 'K', 'C');

    // If not first character, change 'M' to 'N' & 'Q' to 'G'
    Result := StringReplaceAll(Result, 'M', 'N', 2);
    Result := StringReplaceAll(Result, 'Q', 'G', 2);

    // Transcode 'SH' to 'S', 'SCH' to 'S', 'YW' to 'Y'
    Result := StringReplaceAll(Result, 'SH', 'S');
    Result := StringReplaceAll(Result, 'SCH', 'S');
    Result := StringReplaceAll(Result, 'YW', 'Y');

    // If not first or last character, change 'Y' to 'A'
    Result := StringReplaceAll(Result, 'Y', 'A', 2, 1);

    // Transcode 'WR' to 'R'
    Result := StringReplaceAll(Result, 'WR', 'R');

    // If not first character, change 'Z' to 'S'
    Result := StringReplaceAll(Result, 'Z', 'S', 2);

    // Transcode terminal 'AY' to 'Y'
    Result := StringReplaceAfter(Result, 'AY', 'Y');

    Result := StringStripDuplicates(StringTrimSetRight(Result, setVowels));

    // If first char is a vowel, use original character as first character instead of A
    Result := StringReplaceBefore(Result, 'A', cFirst);

    If Result = '' Then
      Result := cFirst;
  End;
End;

End. // EncodeSupport //
