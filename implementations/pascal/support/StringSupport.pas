Unit StringSupport;

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
  SysUtils, Character, SysConst,
  MathSupport;


Type
  TCharArray = Array Of WideChar;
  TCharSet = Set Of AnsiChar;

  TLongString = String;
  PLongString = ^TLongString;

  TShortString = String[255];
  PShortString = ^TShortString;

{$IFDEF VER130}
  TBytes = Array Of Byte;
{$ENDIF}
  

Const
  cNull = #0;
  cBackspace = #8;
  cTab = #9;
  cVerticalTab = #11;
  cFeed = #10;
  cEnter = #13;
  cEscape = #27;
  cSpace = ' ';
  cReturn = cEnter + cFeed;

  setUniversal = [#0..#255];
  setControls = [#0..#31];
  setVertical = [cEnter, cFeed, cVerticalTab];
  setHorizontal = [cTab, cSpace, cBackspace];
  setWhitespace = setHorizontal + setVertical;
  setSigns = ['-', '+'];
  setNumbers = ['0'..'9'];
  setLowerCase = ['a'..'z'];
  setUpperCase = ['A'..'Z'];
  setAlphabet = setLowerCase + setUpperCase;
  setVowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];
  setConsonants = setAlphabet - setVowels;
  setAlphanumeric = setNumbers + setAlphabet;
  setIntegers = setNumbers + setSigns;
  setReals = setNumbers + setSigns + ['.'];
  setHexadecimal = ['a'..'f', 'A'..'F'] + setNumbers;
  setSpecials = ['`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '+', '=', '/', '?', '|', '<', '>', ',', ';', ':', '\', '"', '.', '[', ']', '{', '}', '-', '_', ''''];
  setFilename = setAlphanumeric + setSpecials - ['<', '>', '?', '*', '|', '\', '/', ':'] + [' '];
  setKeyboard = setSpecials + setAlphanumeric;


// NOTE: declared in the initialization.
Var
  UnicodeWhitespaceArray : TCharArray;

Procedure StringAppend(Var sTarget : String; Const sSource : String; Const sDelimiter : String = ''); Overload;
Function StringCopy(Const sValue : String; iIndex, iCount : Integer) : String; Overload;
Function StringTruncateStart(Const sValue : String; iLength : Integer) : String; Overload;
Function StringCompare(Const sA, sB : String) : Integer; Overload;
Function StringCompare(Const sA, sB : String; Const iLength : Integer) : Integer; Overload;
Function StringCompareInsensitive(Const sA, sB : String) : Integer; Overload;
Function StringCompareSensitive(Const sA, sB : String) : Integer; Overload;
Function StringCompareInsensitive(Const sA, sB : String; Const iLength : Integer) : Integer; Overload;
Function StringCompareSensitive(Const sA, sB : String; Const iLength : Integer) : Integer; Overload;

Function StringEquals(Const sA, sB : String) : Boolean; Overload;
Function StringEquals(Const sA, sB : String; Const iLength : Integer) : Boolean; Overload;
Function StringEqualsSensitive(Const sA, sB : String) : Boolean; Overload;
Function StringEqualsSensitive(Const sA, sB : String; Const iLength : Integer) : Boolean; Overload;
Function StringEqualsInsensitive(Const sA, sB : String) : Boolean; Overload;
Function StringEqualsInsensitive(Const sA, sB : String; Const iLength : Integer) : Boolean; Overload;
Function StringFormat(Const sFormat : String; Const aArgs : Array Of Const) : String; Overload;
Function StringArrayIndexOfInsensitive(Const aNames : Array Of String; Const sName : String): Integer; Overload;
Function StringArrayIndexOfSensitive(Const aNames : Array Of String; Const sName : String): Integer; Overload;
Function StringArrayIndexOf(Const aNames : Array Of String; Const sName: String) : Integer; Overload;

Function StringArrayExistsInsensitive(Const aNames : Array Of String; Const sName : String) : Boolean; Overload;
Function StringArrayExistsSensitive(Const aNames : Array Of String; Const sName : String) : Boolean; Overload;
Function StringArrayExists(Const aNames : Array Of String; Const sName: String) : Boolean; Overload;
Function StringGet(Const sValue : String; iIndex : Integer) : Char; Overload;
Function StringStartsWith(Const sValue, sFind : String; sensitive : boolean = false) : Boolean; Overload;
Function StringStartsWithSensitive(Const sValue, sFind : String) : Boolean; Overload;
Function StringStartsWithInsensitive(Const sValue, sFind : String) : Boolean; Overload;
Function StringEndsWith(Const sValue, sFind : String; sensitive : boolean = false) : Boolean; Overload;
Function StringEndsWithSensitive(Const sValue, sFind : String) : Boolean; Overload;
Function StringEndsWithInsensitive(Const sValue, sFind : String) : Boolean; Overload;
Function StringStrip(Const sValue, sFind : String) : String; Overload;
Function StringStrip(Const sValue : String; Const aFind : TCharSet) : String; Overload;
Function StringStripDuplicates(Const sValue : String) : String; Overload;
Function StringCount(Const sValue : String; Const aFind : Char) : Integer; Overload;
Function StringSplit(Const sValue : String; Const aDelimiters : TCharSet; Var sLeft, sRight: String) : Boolean; Overload;
Function StringSplit(Const sValue, sDelimiter : String; Var sLeft, sRight: String) : Boolean; Overload;
Function StringSplitAnsi(Const sValue, sDelimiter : AnsiString; Var sLeft, sRight: AnsiString) : Boolean; Overload;
Function StringSplitRight(Const sValue : String; Const aDelimiters : TCharSet; Var sLeft, sRight: String) : Boolean; Overload;
Function StringSplitRight(Const sValue, sDelimiter : String; Var sLeft, sRight: String) : Boolean; Overload;
Function StringUpper(Const sValue : String) : String; Overload;
Function StringMultiply(cChar : Char; iCount : Integer) : String; Overload;

Function StringFind(Const sValue, sFind : String) : Integer; Overload;
Function StringFind(Const sValue : String; aFind : TCharSet) : Integer; Overload;
Function StringExistsInsensitive(Const sValue, sFind : String): Boolean; Overload;
Function StringExistsSensitive(Const sValue, sFind : String): Boolean; Overload;
Function StringKeep(Const sValue : String; Const aFind : TCharSet) : String; Overload;
Function StringReplace(Const sValue, sFind, sReplace : String) : String; Overload;
Function StringReplace(Const sValue : String; Const aFind : TCharSet; cReplace : Char) : String; Overload;
Function StringReplace(Const sValue : String; Const aFind : TCharSet; Const sReplace : String) : String; Overload;
Function StringReplaceBefore(Const sValue, sFind, sReplace : String) : String; Overload;
Function StringReplaceAfter(Const sValue, sFind, sReplace : String) : String; Overload;
Function StringReplaceAll(Const sValue, sFind, sReplace : String; Const iStartPos : Integer = 1; Const iEndPos : Integer = 0) : String; Overload;
Function StringIncludeAfter(Const sText, sSymbol : String) : String; Overload;
Function StringExcludeBefore(Const sText, sSymbol : String) : String; Overload;
Function StringPadLeft(Const sValue : String; cPad : Char; iLength : Integer) : String; Overload;
Function StringPadRight(Const sValue : String; cPad : Char; iLength : Integer) : String; Overload;

Function StringTrimWhitespace(Const sValue : String) : String; Overload;
Function StringTrimWhitespaceRight(Const sValue : String) : String; Overload;
Function StringTrimWhitespaceLeft(Const sValue : String) : String; Overload;
Function StringTrimSetRight(Const sValue : String; aChars : TCharSet) : String; Overload;
Function StringTrimSetLeft(Const sValue : String; aChars : TCharSet) : String; Overload;
Function StringTrimSet(Const sValue : String; aChars : TCharSet) : String; Overload;

Function StringToBoolean(Const sValue : String) : Boolean; Overload;
Function StringToReal(Const sValue : String) : Real; Overload;

Function IntegerToString(Value : Integer) : String; Overload;

Function RealToString(Value : Real) : String; Overload;
Function BooleanToString(Value : Boolean) : String; Overload;
Function LCBooleanToString(Value : Boolean) : String;

Function StringToInteger32(Const sValue : String) : Integer; Overload;
Function StringToInteger32(Const sValue : String; iDefault : Integer) : Integer; Overload;
Function StringToInteger64(Const sValue : String) : Int64; Overload;

Function StringContainsOnly(Const sValue : String; Const aSet : TCharSet) : Boolean; Overload;
Function StringContainsAny(Const sValue : String; Const aSet : TCharSet) : Boolean; Overload;
Function StringIsCardinal16(Const sValue : String) : Boolean; Overload;
Function StringIsInteger16(Const sValue : String) : Boolean; Overload;
Function StringIsInteger32(Const sValue : String) : Boolean; Overload;
Function StringIsInteger64(Const sValue : String) : Boolean; Overload;
Function StringIsAlphabetic(Const sValue : String) : Boolean; Overload;
Function StringIsWhitespace(Const sValue : String) : Boolean; Overload;
function GetStringCell(const ADelimitedString: String; ACell: Cardinal; ADelimiter: String): String; Overload;
function SQLWrapString(const AStr: String): String;
function SQLWrapStrings(const AStr: String): String;
function AppendForwardSlash(const AStr: String): String;
Function DescribeBytes(i : int64) : String;

procedure CommaAdd(var AStr: String; AStrToAdd: String);
function RemoveQuotes(AStr: String; AUpperCaseString: Boolean = false): String;
function IsNumericString(st: String): Boolean;
function RemoveAccents(const aStr: String): String;


{$IFDEF FPC}
Function CharInSet(C: Char; Const CharSet: TCharSet): Boolean;
{$ENDIF}
{$IFDEF VER130}
Function CharInSet(C: AnsiChar; Const CharSet: TCharSet): Boolean; Overload;
Function CharInSet(C: WideChar; Const CharSet: TCharSet): Boolean; Overload;
{$ENDIF}

function TryStrToUINT64(StrValue:String; var uValue:UInt64 ):Boolean;
function StrToUINT64(Value:String):UInt64;
function StrToUInt64Def(Value:String; def : UInt64):UInt64;

Implementation


Const
  STRING_BOOLEAN : Array[Boolean] Of String = ('False', 'True');
  PLURAL_EXCEPTIONS : Array [0..0] Of String = ('series');

{$IFDEF FPC}
Function CharInSet(C: Char; Const CharSet: TCharSet): Boolean;
Begin
  Result := C In CharSet;
End;
{$ENDIF}

{$IFDEF VER130}

Function CharInSet(C: AnsiChar; Const CharSet: TCharSet): Boolean;
Begin
  Result := C In CharSet;
End;


Function CharInSet(C: WideChar; Const CharSet: TCharSet): Boolean;
Begin
  Result := (C < #1#0) And (AnsiChar(C) In CharSet);
End;
{$ENDIF}



{$IFOPT C+}
Procedure CharArrayError(Const sConstant, sMessage : String);
Begin
  Raise Exception.Create('(StringSupport.' + sConstant + '): ' + sMessage);
End;


Procedure CharArrayVerify(Const CharArray : TCharArray; Const ConstantName : String);
Var
  CharIndex : Integer;
  Current : WideChar;
  Previous : WideChar;
Begin
  If (Length(CharArray) = 0) Then
    CharArrayError(ConstantName, 'Must have at least one element.');

  Previous := CharArray[Low(CharArray)];

  For CharIndex := Low(CharArray) + 1 To High(CharArray) Do
  Begin
    Current := CharArray[CharIndex];

    If (Previous > Current) Then
      CharArrayError(ConstantName, 'Must have all elements declared in ascending order.');
  End;
End;
{$ENDIF}

Procedure StringAppend(Var sTarget : String; Const sSource, sDelimiter : String);
Begin
  If sTarget = '' Then
    sTarget := sSource
  Else
    sTarget := sTarget + sDelimiter + sSource;
End;


Function StringTruncateStart(Const sValue : String; iLength : Integer) : String;
Var
  iCurrent : Integer;
Begin
  iCurrent := Length(sValue);

  If iLength >= iCurrent Then
    Result := sValue
  Else
    Result := Copy(sValue, iCurrent - iLength + 1, iLength);
End;

Function StringCopy(Const sValue : String; iIndex, iCount : Integer) : String;
Begin
  Result := System.Copy(sValue, iIndex, iCount);
End;


Function StringCompare(Const sA, sB : String) : Integer;
Begin
  Result := StringCompareInsensitive(sA, sB);
End;


Function StringCompareSensitive(Const sA, sB : String) : Integer;
Begin
  Result := CompareStr(sA, sB);
End;


Function StringCompareInsensitive(Const sA, sB : String; Const iLength : Integer) : Integer;
Begin
  Result := StrLIComp(PChar(sA), PChar(sB), iLength);
End;


Function StringCompareSensitive(Const sA, sB : String; Const iLength : Integer) : Integer;
Begin
  Result := StrLComp(PChar(sA), PChar(sB), iLength);
End;


Function StringCompare(Const sA, sB : String; Const iLength : Integer) : Integer;
Begin
  Result := StrLIComp(PChar(sA), PChar(sB), iLength);
End;

Function StringCompareInsensitive(Const sA, sB : String) : Integer;
Begin
  Result := CompareText(sA, sB);
End;


Function StringEqualsInsensitive(Const sA, sB : String) : Boolean;
Begin
  Result := StringCompareInsensitive(sA, sB) = 0;
End;

Function StringEquals(Const sA, sB : String) : Boolean;
Begin
  Result := StringEqualsInsensitive(sA, sB);
End;

Function StringEqualsSensitive(Const sA, sB : String; Const iLength : Integer) : Boolean;
Begin
  Result := StringCompareSensitive(sA, sB, iLength) = 0;
End;

Function StringEqualsInsensitive(Const sA, sB : String; Const iLength : Integer) : Boolean;
Begin
  Result := StringCompareInsensitive(sA, sB, iLength) = 0;
End;


Function StringEquals(Const sA, sB : String; Const iLength : Integer) : Boolean;
Begin
  Result := StringEqualsInsensitive(sA, sB, iLength);
End;

Function StringFormat(Const sFormat : String; Const aArgs : Array Of Const) : String;
Begin
  FmtStr(Result, sFormat, aArgs);
End;

Function StringStrip(Const sValue, sFind : String) : String;
Begin
  Result := StringReplace(sValue, sFind, '');
End;

Function StringStrip(Const sValue : String; Const aFind : TCharSet) : String;
Begin
  Result := StringReplace(sValue, aFind, '');
End;



Function StringStripWhitespace(Const sValue : String) : String; Overload;
Begin
  Result := StringStrip(sValue, setWhitespace);
End;



Function StringStripDuplicatesSensitive(Const sValue : String) : String;
Var
  cLast : Char;
  cCurrent : Char;
  iLoop : Integer;
Begin 
  Result := sValue;

  If Result <> '' Then
  Begin 
    cLast := Result[Length(Result)];

    For iLoop := Length(Result) - 1 DownTo 1 Do
    Begin 
      cCurrent := Result[iLoop];

      If cCurrent = cLast Then
        Delete(Result, iLoop + 1, 1);

      cLast := cCurrent;
    End;
  End;  
End;  


Function StringStripDuplicatesInsensitive(Const sValue : String) : String;
Var
  cLast : Char;
  cCurrent : Char;
  iLoop : Integer;
Begin 
  Result := sValue;

  If Result <> '' Then
  Begin
    cLast := upcase(Result[Length(Result)]);

    For iLoop := Length(Result) - 1 DownTo 1 Do
    Begin
      cCurrent := Upcase(Result[iLoop]);

      If cCurrent = cLast Then
        Delete(Result, iLoop + 1, 1);

      cLast := cCurrent;
    End;
  End;
End;

Function StringStripDuplicates(Const sValue : String) : String;
Begin
  Result := StringStripDuplicatesInsensitive(sValue);
End;

Function StringGet(Const sValue : String; iIndex : Integer) : Char;
Begin
  If (iIndex < 1) Or (iIndex > Length(sValue)) Then
    Result := #0
  Else
    Result := sValue[iIndex];
End;


Function StringSplit(Const sValue, sDelimiter : String; Var sLeft, sRight: String) : Boolean;
Var
  iIndex : Integer;
  sA, sB : String;
Begin
  // Find the delimiter within the source string
  iIndex := Pos(sDelimiter, sValue);
  Result := iIndex <> 0;

  If Not Result Then
  Begin
    sA := sValue;
    sB := '';
  End
  Else
  Begin
    sA := Copy(sValue, 1, iIndex - 1);
    sB := Copy(sValue, iIndex + Length(sDelimiter), MaxInt);
  End;

  sLeft := sA;
  sRight := sB;
End;

Function StringSplitAnsi(Const sValue, sDelimiter : AnsiString; Var sLeft, sRight: AnsiString) : Boolean;
Var
  iIndex : Integer;
  sA, sB : AnsiString;
Begin
  // Find the delimiter within the source string
  iIndex := Pos(sDelimiter, sValue);
  Result := iIndex <> 0;

  If Not Result Then
  Begin
    sA := sValue;
    sB := '';
  End
  Else
  Begin
    sA := Copy(sValue, 1, iIndex - 1);
    sB := Copy(sValue, iIndex + Length(sDelimiter), MaxInt);
  End;

  sLeft := sA;
  sRight := sB;
End;


Function StringSplit(Const sValue : String; Const aDelimiters : TCharSet; Var sLeft, sRight: String) : Boolean;
Var
  iIndex : Integer;
  sA, sB : String;
Begin 
  // Find the delimiter within the source string
  iIndex := StringFind(sValue, aDelimiters);
  Result := iIndex <> 0;

  If Not Result Then
  Begin 
    sA := sValue;
    sB := '';
  End   
  Else
  Begin 
    sA := Copy(sValue, 1, iIndex - 1);
    sB := Copy(sValue, iIndex + 1, MaxInt);
  End;

  sLeft := sA;
  sRight := sB;
End;


Function StringReversePos(Const cFind : Char; Const sValue : String) : Integer; overload;
Begin
  Result := Length(sValue);
  While (Result > 0) And (sValue[Result] <> cFind) Do
    Dec(Result);
End;


Function StringReversePos(Const aFinds : TCharSet; Const sValue : String) : Integer; overload;
Begin
  Result := Length(sValue);
  While (Result > 0) And Not CharInSet(sValue[Result], aFinds) Do
    Dec(Result);
End;


Function StringReversePos(Const sFind : String; Const sValue : String) : Integer; Overload;
Begin
  Result := Length(sValue);
  While (Result > 0) And Not StringEquals(Copy(sValue, Result, Length(sFind)), sFind) Do
    Dec(Result);
End;


Function StringSplitRight(Const sValue, sDelimiter : String; Var sLeft, sRight: String) : Boolean;
Var
  iIndex : Integer;
  sA, sB : String;
Begin
  // Find the delimiter within the source string
  iIndex := StringReversePos(sDelimiter, sValue);
  Result := iIndex <> 0;

  If Not Result Then
  Begin
    sA := '';
    sB := sValue;
  End
  Else
  Begin
    sA := Copy(sValue, 1, iIndex - 1);
    sB := Copy(sValue, iIndex + Length(sDelimiter), MaxInt);
  End;

  sLeft := sA;
  sRight := sB;
End;


Function StringSplitRight(Const sValue : String; Const aDelimiters : TCharSet; Var sLeft, sRight: String) : Boolean;
Var
  iIndex : Integer;
  sA, sB : String;
Begin
  // Find the delimiter within the source string
  iIndex := StringReversePos(aDelimiters, sValue);
  Result := iIndex <> 0;

  If Not Result Then
  Begin
    sA := '';
    sB := sValue;
  End
  Else
  Begin
    sA := Copy(sValue, 1, iIndex - 1);
    sB := Copy(sValue, iIndex + 1, MaxInt);
  End;

  sLeft := sA;
  sRight := sB;
End;

Function StringFindSensitive(Const sValue, sFind : String) : Integer;
Begin
  Result := Pos(sFind, sValue);
End;


Function StringExistsSensitive(Const sValue, sFind : String) : Boolean;
Begin
  Result := StringFindSensitive(sValue, sFind) > 0;
End;

Function StringFind(Const sValue, sFind : String) : Integer;
Begin
  Result := StringFindSensitive(sValue, sFind);
End;


Function StringFindInsensitive(Const sValue, sFind : String) : Integer;
Begin
  Result := StringFindSensitive(StringUpper(sValue), StringUpper(sFind));
End;


Function StringExistsInsensitive(Const sValue, sFind : String) : Boolean;
Begin
  Result := StringFindInsensitive(sValue, sFind) > 0;
End;

Function StringMultiply(cChar : Char; iCount : Integer) : String;
Var
  Index : Integer;
Begin
  Result := '';

  If iCount > 0 Then
  Begin
    SetLength(Result, iCount);

    For Index := 1 To iCount Do
      Result[Index] := cChar;
  End;
End;

Function StringCount(Const sValue : String; Const aFind : Char) : Integer;
Var
  iLoop : Integer;
Begin
  Result := 0;
  For iLoop := 1 To Length(sValue) Do
    Inc(Result, Integer(sValue[iLoop] = aFind));
End;

Function StringArrayIndexOfSensitive(Const aNames: Array Of String; Const sName: String): Integer;
Begin
  Result := High(aNames);
  While (Result >= Low(aNames)) And (StringCompareSensitive(sName, aNames[Result]) <> 0) Do
    Dec(Result);
End;


Function StringArrayIndexOfInsensitive(Const aNames: Array Of String; Const sName: String): Integer;
Begin
  Result := High(aNames);
  While (Result >= Low(aNames)) And (StringCompareInsensitive(sName, aNames[Result]) <> 0) Do
    Dec(Result);
End;


Function StringArrayIndexOf(Const aNames: Array Of String; Const sName: String): Integer;
Begin
  Result := StringArrayIndexOfInsensitive(aNames, sName);
End;

Function StringArrayExistsSensitive(Const aNames: Array Of String; Const sName: String): Boolean;
Begin
  Result := StringArrayIndexOfSensitive(aNames, sName) >= 0;
End;


Function StringArrayExistsInsensitive(Const aNames: Array Of String; Const sName: String): Boolean;
Begin
  Result := StringArrayIndexOfInsensitive(aNames, sName) >= 0;
End;


Function StringArrayExists(Const aNames: Array Of String; Const sName: String): Boolean;
Begin
  Result := StringArrayExistsInsensitive(aNames, sName);
End;

Function StringUpper(Const sValue : String) : String;
Begin
  Result := SysUtils.UpperCase(sValue);
End;


Function BooleanToString(Value : Boolean) : String;
Begin
  Result := STRING_BOOLEAN[Value];
End;

Function LCBooleanToString(Value : Boolean) : String;
Begin
  Result := Lowercase(STRING_BOOLEAN[Value]);
End;


Function IntegerToString(Value : Integer) : String;
Begin
  Result := IntToStr(Value);
End;

Function RealToString(Value : Real) : String;
Begin
  Result := FloatToStr(Value);
End;

Function StringIsInteger16(Const sValue : String) : Boolean;
Var
  iValue : Integer;
  iError : Integer;
Begin
  Result := sValue <> '';

  If Result Then
  Begin
    Val(sValue, iValue, iError);

    Result := (iError = 0) And (iValue <= 32767) And (iValue >= -32768);
  End;
End;

Function StringIsInteger32(Const sValue : String) : Boolean;
Var
  iValue : Integer;
  iError : Integer;
Begin
  Result := sValue <> '';

  If Result Then
  Begin
    Val(sValue, iValue, iError);

    Result := (iError = 0) And (iValue = iValue); // 2nd part to remove warning.
  End;
End;


Function StringIsInteger64(Const sValue : String) : Boolean;
Var
  iValue : Int64;
  iError : Integer;
Begin
  Result := sValue <> '';

  If Result Then
  Begin
    Val(sValue, iValue, iError);

    Result := (iError = 0) And (iValue = iValue); // 2nd part to remove warning.
  End;
End;


Function StringToInteger32(Const sValue : String) : Integer;
Begin
  Result := StrToInt(sValue);
End;

Function StringToInteger32(Const sValue : String; iDefault : Integer) : Integer;
Begin
  Result := StrToIntDef(sValue, iDefault);
End;

Function StringToReal(Const sValue : String) : Real;
Begin
  Result := StrToFloat(sValue);
End;

Function StringToBooleanCheck(Const sValue : String; Out bValue : Boolean) : Boolean;
Begin
  Result := True;

  If StringEquals(STRING_BOOLEAN[False], sValue) Then
    bValue := False
  Else If StringEquals(STRING_BOOLEAN[True], sValue) Then
    bValue := True
  Else
  Begin
    bValue := False;
    Result := False;
  End;
End;

Function StringToBoolean(Const sValue : String) : Boolean;
Begin
  StringToBooleanCheck(sValue, Result);
End;

Function StringToInteger64(Const sValue : String) : Int64;
Begin
  Result := StrToInt64(sValue);
End;


Function StringIsCardinal16(Const sValue : String) : Boolean;
Var
  iValue : Word;
  iError : Integer;
Begin
  Result := sValue <> '';

  If Result Then
  Begin
    Val(sValue, iValue, iError);

    Result := (iError = 0) And (iValue = iValue); // 2nd part to remove warning.
  End;
End;

Function StringIsAlphabetic(Const sValue : String) : Boolean;
Begin
  Result := StringContainsOnly(sValue, setAlphabet);
End;

Function StringIsWhitespace(Const sValue : String) : Boolean;
Begin
  Result := (sValue = '') Or StringContainsOnly(sValue, setWhitespace);
End;

Function StringKeep(Const sValue : String; Const aFind : TCharSet) : String;
Begin
  Result := StringStrip(sValue, setUniversal - aFind);
End;

Function StringFind(Const sValue : String; aFind : TCharSet) : Integer;
Var
  iLength : Integer;
Begin
  iLength := Length(sValue);
  Result := 1;
  While (Result <= iLength) And Not CharInSet(sValue[Result], aFind) Do
    Inc(Result);

  If Result > iLength Then
    Result := 0;
End;

Function StringPadLeft(Const sValue : String; cPad : Char; iLength : Integer) : String;
Begin
  Result := StringMultiply(cPad, iLength - Length(sValue)) + sValue;
End;


Function StringPadRight(Const sValue : String; cPad : Char; iLength : Integer) : String;
Begin
  Result := sValue + StringMultiply(cPad, iLength - Length(sValue));
End;

Function StringTrimWhitespaceLeft(Const sValue : String) : String;
Begin
  Result := SysUtils.TrimLeft(sValue);
End;


Function StringTrimWhitespaceRight(Const sValue : String) : String;
Begin
  Result := SysUtils.TrimRight(sValue);
End;


Function StringTrimSetLeft(Const sValue : String; aChars : TCharSet) : String;
Var
  iStart : Integer;
Begin
  iStart := 1;
  While (iStart < Length(sValue)) And (CharInSet(sValue[iStart], aChars)) Do
    Inc(iStart);

  Result := Copy(sValue, iStart, MaxInt);
End;

Function StringTrimSetRight(Const sValue : String; aChars : TCharSet) : String;
Var
  iFinish : Integer;
Begin
  iFinish := Length(sValue);
  While (iFinish >= 1) And CharInSet(sValue[iFinish], aChars) Do
    Dec(iFinish);

  Result := Copy(sValue, 1, iFinish);
End;


Function StringTrimWhitespace(Const sValue : String) : String;
Begin
  Result := SysUtils.Trim(sValue);
End;

Function StringTrimSet(Const sValue : String; aChars : TCharSet) : String;
Var
  iStart, iFinish : Integer;
Begin
  iStart := 1;
  While (iStart < Length(sValue)) And CharInSet(sValue[iStart], aChars) Do
    Inc(iStart);

  iFinish := Length(sValue);
  While (iFinish >= iStart) And CharInSet(sValue[iFinish], aChars) Do
    Dec(iFinish);

  Result := Copy(sValue, iStart, iFinish - iStart + 1);
End;


Function StringIncludeAfter(Const sText, sSymbol : String) : String;
Var
  iSymbol, iText : Integer;
Begin
  iSymbol := Length(sSymbol);
  iText := Length(sText);

  If (iText < iSymbol) Or (StringCompare(Copy(sText, iText - iSymbol + 1, iSymbol), sSymbol) <> 0) Then
    Result := sText + sSymbol
  Else
    Result := sText;
End;

Function StringExcludeBefore(Const sText, sSymbol : String) : String;
Begin
  If (StringCompare(sSymbol, sText, Length(sSymbol)) <> 0) Then
    Result := sText
  Else
    Result := Copy(sText, Length(sSymbol) + 1, MaxInt);
End;

Function StringExcludeAfter(Const sText, sSymbol : String) : String;
Var
  iSymbol, iText : Integer;
Begin
  iSymbol := Length(sSymbol);
  iText := Length(sText);

  If (iText >= iSymbol) And (StringCompare(Copy(sText, iText - iSymbol + 1, iSymbol), sSymbol) = 0) Then
    Result := Copy(sText, 1, iText - iSymbol)
  Else
    Result := sText;
End;

Function StringReplaceBefore(Const sValue, sFind, sReplace : String) : String;
Begin
  If StringStartsWith(sValue, sFind) Then
    Result := sReplace + StringExcludeBefore(sValue, sFind)
  Else
    Result := sValue;
End;


Function StringReplaceAfter(Const sValue, sFind, sReplace : String) : String;
Begin
  If StringEndsWith(sValue, sFind) Then
    Result := StringExcludeAfter(sValue, sFind) + sReplace
  Else
    Result := sValue;
End;

Function StringContainsOnly(Const sValue : String; Const aSet : TCharSet) : Boolean;
Var
  iLoop : Integer;
Begin
  Result := True;
  iLoop := 1;
  While (iLoop <= Length(sValue)) And Result Do
  Begin
    Result := CharInSet(sValue[iLoop], aSet);
    Inc(iLoop);
  End;
End;

Function StringContainsAny(Const sValue : String; Const aSet : TCharSet) : Boolean;
Var
  iLoop : Integer;
Begin
  Result := False;
  iLoop := 1;
  While (iLoop <= Length(sValue)) And Not Result Do
  Begin
    Result := CharInSet(sValue[iLoop], aSet);
    Inc(iLoop);
  End;
End;

Function StringStartsWith(Const sValue, sFind : String; sensitive : boolean = false) : Boolean;
Begin
  if sensitive then
    Result := StringStartsWithSensitive(sValue, sFind)
  else
    Result := StringStartsWithInsensitive(sValue, sFind);
End;



Function StringEqualsSensitive(Const sA, sB : String) : Boolean;
Begin
  Result := StringCompareSensitive(sA, sB) = 0;
End;



Function EqualsSensitive(Const sA, sB : String) : Boolean;
Begin
  Result := StringEqualsSensitive(sA, sB);
End;


Function StringStartsWithSensitive(Const sValue, sFind : String) : Boolean;
Begin
  Result := EqualsSensitive(Copy(sValue, 1, Length(sFind)), sFind);
End;


Function StringStartsWithInsensitive(Const sValue, sFind : String) : Boolean;
Begin
  Result := StringEqualsInsensitive(Copy(sValue, 1, Length(sFind)), sFind);
End;

Function StringReplaceInsensitive(Const sValue, sFind, sReplace : String) : String;
Begin
  Result := SysUtils.StringReplace(sValue, sFind, sReplace, [rfReplaceAll, rfIgnoreCase]);
End;


Function StringEndsWith(Const sValue, sFind : String; sensitive : boolean = false) : Boolean;
Begin
  if sensitive then
    Result := StringEndsWithSensitive(sValue, sFind)
  else
    Result := StringEndsWithInsensitive(sValue, sFind);
End;


Function StringEndsWithSensitive(Const sValue, sFind : String) : Boolean;
Begin
  Result := EqualsSensitive(Copy(sValue, Length(sValue) - Length(sFind) + 1, Length(sFind)), sFind);
End;


Function StringEndsWithInsensitive(Const sValue, sFind : String) : Boolean;
Begin
  Result := StringEqualsInsensitive(Copy(sValue, Length(sValue) - Length(sFind) + 1, Length(sFind)), sFind);
End;


Function StringReplace(Const sValue, sFind, sReplace : String) : String;
Begin
  Result := StringReplaceInsensitive(sValue, sFind, sReplace);
End;


Function StringReplace(Const sValue : String; Const aFind : TCharSet; cReplace : Char) : String;
Var
  cChar  : Char;
  iLoop  : Integer;
  iCount : Integer;
Begin
  iCount := Length(sValue);

  SetLength(Result, iCount);

  For iLoop := 1 To iCount Do
  Begin
    cChar := sValue[iLoop];

    If CharInSet(cChar, aFind) Then
      Result[iLoop] := cReplace
    Else
      Result[iLoop] := cChar;
  End;
End;

Function StringReplace(Const sValue : String; Const aFind : TCharSet; Const sReplace : String) : String;
Var
  cChar : Char;
  iLoop : Integer;
Begin
  Result := '';

  For iLoop := 1 To Length(sValue) Do
  Begin
    cChar := sValue[iLoop];

    If CharInSet(cChar, aFind) Then
      Result := Result + sReplace
    Else
      Result := Result + cChar;
  End;
End;


Function StringReplaceAll(Const sValue, sFind, sReplace : String; Const iStartPos : Integer = 1; Const iEndPos : Integer = 0) : String;
Var
  iFind : Integer;
  iIndex : Integer;
  iReplace : Integer;
Begin
  If (iStartPos = 1) And (iEndPos = 0) Then
    Result := StringReplace(sValue, sFind, sReplace)
  Else
  Begin
    Result := sValue;

    iFind := Length(sFind);
    iIndex := iStartPos;
    iReplace := Length(sReplace);

    While iIndex <= (Length(Result) - iEndPos) Do
    Begin
      If StringEquals(Copy(Result, iIndex, iFind), sFind) Then
      Begin
        Delete(Result, iIndex, iFind);
        Insert(sReplace, Result, iIndex);

        Inc(iIndex, iReplace - 1);
      End;

      Inc(iIndex);
    End;
  End;
End;

Function StringLength(Const sValue : String) : Integer;
Begin
  Result := System.Length(sValue);
End;

function GetStringCell(const ADelimitedString: String; ACell: Cardinal; ADelimiter: String): String; Overload;
  // returns the string corresponding to cell ACell in a delimited string
  // first cell is 0. returns '' if ACell > actual number
var
  j, k: Integer;
begin
  Result := ADelimitedString;
  for k := 1 to ACell do
    begin
    j := Pos(ADelimiter, Result);
    if j = 0 then
      begin
      Result := '';
      break;
      end;
    Result := copy(Result, j + length(ADelimiter), length(Result));
    end;
  j := Pos(ADelimiter, Result);
  if j <> 0 then
    Result := copy(Result, 1, j - 1);
end;

function SQLWrapString(const AStr: String): String;
var
  i: Integer;
begin
  Result := AStr;
  for i := Length(Result) downto 1 do
    if Result[i] = '''' then
      Insert('''', Result, i);
end;

function SQLWrapStrings(const AStr: String): String;
var
  sl : TArray<String>;
  b : TStringBuilder;
  s : String;
  first : boolean;
begin
  sl := aStr.Split([',']);
  b := TStringBuilder.Create;
  try
    first := true;
    for s in sl do
    begin
      if first then
        first := false
      else
        b.Append(',');
      b.Append('''');
      b.Append(sqlwrapstring(s));
      b.Append('''');
    end;
    result := b.ToString;
  finally
    b.free;
  end;
end;

function AppendForwardSlash(const AStr: String): String;
begin
  if length(AStr) = 0 then
    Result := '/'
  else if AStr[length(AStr)] = '/' then
    Result := AStr
  else
    Result := AStr + '/';
end;

Function DescribeBytes(i : int64) : String;
Begin
  Case i Of
    0..1000:
      Result := IntToStr(i) + ' bytes';
    1001..1000000:
      Result := floattostrF(i / 1024, ffFixed, 18, 2) + ' KB';
    1000001..1000000000:
      Result := floattostrF(i / 1048576, ffFixed, 18, 2) + ' MB';
  Else
    Result := floattostrF(i / 1073741824, ffFixed, 18, 2) + ' GB';
  End;
End;

procedure CommaAdd(var AStr: String; AStrToAdd: String);
begin
  if AStr = '' then
    AStr := AStrToAdd
  else
    AStr := AStr + ', ' + AStrToAdd;
end;

function RemoveQuotes(AStr: String; AUpperCaseString: Boolean = false): String;
begin
  if Length(AStr) >= 1 then
    if AStr[1] = '"' then
      Delete(AStr, 1, 1);
  if Length(AStr) >= 1 then
    if AStr[Length(AStr)] = '"' then
      Delete(AStr, Length(AStr), 1);
  if AUpperCaseString then
    Result := UpperCase(AStr)
  else
    Result := AStr;
end;

function isNumeric(AChar: Char): Boolean;
begin
  Result := CharInSet(AChar, ['0'..'9']);
end;


function IsNumericString(st: String): Boolean;
var
  i: Integer;
begin
  Result := True;
  for i := 1 to Length(st) do
    begin
    if not IsNumeric(st[i]) then
      begin
      Result := False;
      Exit;
      end;
    end;
end;

// http://stackoverflow.com/questions/1891196/convert-hi-ansi-chars-to-ascii-equivalent-e-e-in-delphi2007/1892432#1892432

function RemoveAccents(const aStr: String): String;
type
  USASCIIString = type AnsiString(20127);//20127 = us ascii
begin
  Result := String(USASCIIString(aStr));
end;

// http://stackoverflow.com/questions/6077258/theres-a-uinttostr-in-delphi-to-let-you-display-uint64-values-but-where-is-strt
function TryStrToUINT64(StrValue:String; var uValue:UInt64 ):Boolean;
var
  Start,Base,Digit:Integer;
  n:Integer;
  Nextvalue:UInt64;
begin
  result := false;
  Base := 10;
  Digit := 0;
  Start := 1;
  StrValue := Trim(UpperCase(StrValue));
  if StrValue='' then
    exit;
  if StrValue[1]='-' then
    exit;
  if StrValue[1]='$' then
  begin
    Base := 16;
    Start := 2;
    if Length(StrValue)>17 then // $+16 hex digits = max hex length.
        exit;
  end;
  uValue := 0;
  for n := Start to Length(StrValue) do
  begin
      if StrValue[n].IsDigit then
          Digit := Ord(StrValue[n])-Ord('0')
      else if  (Base=16) and (StrValue[n] >= 'A') and (StrValue[n] <= 'F') then
          Digit := (Ord(StrValue[n])-Ord('A'))+10
      else
          exit;// invalid digit.

      Nextvalue := (uValue*base)+digit;
      if (Nextvalue<uValue) then
          exit;
      uValue := Nextvalue;
  end;
  result := true; // success.
end;

function StrToUINT64(Value:String):UInt64;
begin
  if not TryStrToUINT64(Value,result) then
    raise EConvertError.Create('Invalid uint64 value');
end;

function StrToUInt64Def(Value:String; def : UInt64):UInt64;
begin
  if not TryStrToUINT64(Value,result) then
    result := def;
end;

Initialization
  SetLength(UnicodeWhitespaceArray, 26);

  UnicodeWhitespaceArray[0] := #$0009; //
  UnicodeWhitespaceArray[1] := #$000A; //
  UnicodeWhitespaceArray[2] := #$000B; //
  UnicodeWhitespaceArray[3] := #$000C; //
  UnicodeWhitespaceArray[4] := #$000D; //
  UnicodeWhitespaceArray[5] := #$0020; // SPACE
  UnicodeWhitespaceArray[6] := #$0085; // NEL (control character next line)
  UnicodeWhitespaceArray[7] := #$00A0; // NBSP (NO-BREAK SPACE)
  UnicodeWhitespaceArray[8] := #$1680; // OGHAM SPACE MARK
  UnicodeWhitespaceArray[9] := #$180E; // MONGOLIAN VOWEL SEPARATOR
  UnicodeWhitespaceArray[10] := #$2000; //
  UnicodeWhitespaceArray[11] := #$2001; //
  UnicodeWhitespaceArray[12] := #$2002; //
  UnicodeWhitespaceArray[13] := #$2003; //
  UnicodeWhitespaceArray[14] := #$2004; //
  UnicodeWhitespaceArray[15] := #$2005; //
  UnicodeWhitespaceArray[16] := #$2006; //
  UnicodeWhitespaceArray[17] := #$2007; //
  UnicodeWhitespaceArray[18] := #$2008; //
  UnicodeWhitespaceArray[19] := #$2009; //
  UnicodeWhitespaceArray[20] := #$200A; // different sorts of spaces)
  UnicodeWhitespaceArray[21] := #$2028; // LS (LINE SEPARATOR)
  UnicodeWhitespaceArray[22] := #$2029; // PS (PARAGRAPH SEPARATOR)
  UnicodeWhitespaceArray[23] := #$202F; // NNBSP (NARROW NO-BREAK SPACE)
  UnicodeWhitespaceArray[24] := #$205F; // MMSP (MEDIUM MATHEMATICAL SPACE)
  UnicodeWhitespaceArray[25] := #$3000; // IDEOGRAPHIC SPACE

{$IFOPT C+}
  CharArrayVerify(UnicodeWhitespaceArray, 'UnicodeWhitespaceArray');
{$ENDIF}
End. // StringSupport //
