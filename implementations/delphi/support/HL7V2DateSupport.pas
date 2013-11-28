{! 2 !}

Unit HL7V2DateSupport;

Interface

Uses
  SysUtils;

Function HL7DateToString(aValue : TDateTime; Const sFormat : String; bTimezone : Boolean) : String;
  (*
Function CheckDateFormat(Const sFormat, sContent : String; Var sError : String) : Boolean;
Function HL7StringToDate(Const sFormat, sValue : String; bCorrectForTimezone : Boolean) : TDateTime;
Procedure CheckDOBReal(Const sDate, sDescription: String);
Procedure CheckDODReal(Const sDate, sDescription: String);
    *)
Implementation

Uses
  DateSupport,
  StringSupport;

(*

Function CheckTimezone(bTimezone : Boolean; Const sContent, sOrigFormat : String; Var sError : String) : Boolean;
Begin
  If sContent <> '' Then
    Begin
    If bTimezone Then
      Begin
      Result := CheckDateFormat('HHNN', sContent, sError);
      If Not Result Then
        sError := sError + ' in the timezone portion';
      End
    Else
      Begin
      sError := 'The value "'+sContent+'" does not conform to the expected format for the date "'+sOrigFormat+'" because a timezone was found';
      Result := False;
      End
    End
  Else
    Result := True;
End;

Function Splice(Var sSource : String; sMatch : String) : Boolean; Overload;
Begin
  If Copy(sSource, 1, Length(sMatch)) = sMatch Then
    Begin
    Result := True;
    sSource := Copy(sSource, Length(sMatch)+ 1, $FF);
    End
  Else
    Result := False;
End;

Function Splice(Var sSource : String; iLength : Integer) : String; Overload;
Begin
  Result := Copy(sSource, 1, iLength);
  sSource := Copy(sSource, iLength + 1, $FF);
End;

Function CheckYear(sValue : String; Var sError : String; Var iYear : Integer) : Boolean;
Begin
  Result := False;
  If Length(sValue) <> 4 Then
    sError := 'Year Value '+sValue+' is not 4 digits in length'
  Else If Not StringIsInteger32(sValue) Then
    sError := 'Year Value '+sValue+' is not numerical'
  Else
    Begin
    iYear := StringToInteger32(sValue);
    If (iYear <= 0) Then
      sError := 'Year Value '+sValue+': negative numbers are not supported'
    Else
      Result := True;
    End;
End;

Function CheckMonth(sValue : String; Var sError : String; Var iMonth : Integer) : Boolean;
Begin
  Result := False;
  If Length(sValue) <> 2 Then
    sError := 'Month Value '+sValue+' is not 2 digits in length'
  Else If Not StringIsInteger32(sValue) Then
    sError := 'Month Value '+sValue+' is not numerical'
  Else
    Begin
    iMonth := StringToInteger32(sValue);
    If (iMonth <= 0) Or (iMonth > 12) Then
      sError := 'Month Value '+sValue+': month must be 1 - 12'
    Else
      Result := True;
    End;
End;

Function CheckDay(sValue : String; iYear, iMonth : Integer; Var sError : String) : Boolean;
Var
  iDay : Integer;
Begin
  Result := False;
  If Length(sValue) <> 2 Then
    sError := 'Day Value '+sValue+' is not 2 digits in length'
  Else If Not StringIsInteger32(sValue) Then
    sError := 'Day Value '+sValue+' is not numerical'
  Else
    Begin
    iDay := StringToInteger32(sValue);
    If (iDay <= 0) Then
      sError := 'Day Value '+sValue+': Day must be >= 1'
    Else If iMonth = 0 Then
      sError := 'Day Value '+sValue+': Month must be known'
    Else If iDay > MONTHS_DAYS[IsLeapYearByYear(iYear)][TMonthOfYear(iMonth-1)] Then
      sError := 'Day Value '+sValue+': Value is not valid for '+MONTHOFYEAR_SHORT[TMonthOfYear(iMonth-1)]+'-'+IntegerToString(iYear)
    Else
      Result := True;
    End;
End;

Function CheckHour(sValue : String; Var sError : String) : Boolean;
Var
  iHour : Integer;
Begin
  Result := False;
  If Length(sValue) <> 2 Then
    sError := 'Hour Value '+sValue+' is not 2 digits in length'
  Else If Not StringIsInteger32(sValue) Then
    sError := 'Hour Value '+sValue+' is not numerical'
  Else
    Begin
    iHour := StringToInteger32(sValue);
    If (iHour < 0) Or (iHour > 23) Then
      sError := 'Hour Value '+sValue+': Hour must be 0 and 23'
    Else
      Result := True;
    End;
End;

Function CheckMinute(sValue : String; Var sError : String) : Boolean;
Var
  iMinute : Integer;
Begin
  Result := False;
  If Length(sValue) <> 2 Then
    sError := 'Minute Value '+sValue+' is not 2 digits in length'
  Else If Not StringIsInteger32(sValue) Then
    sError := 'Minute Value '+sValue+' is not numerical'
  Else
    Begin
    iMinute := StringToInteger32(sValue);
    If (iMinute < 0) Or (iMinute > 59) Then
      sError := 'Minute Value '+sValue+': Minute must be 0 and 59'
    Else
      Result := True;
    End;
End;

Function CheckSecond(sValue : String; Var sError : String) : Boolean;
Var
  iSecond : Integer;
Begin
  Result := False;
  If Length(sValue) <> 2 Then
    sError := 'Second Value '+sValue+' is not 2 digits in length'
  Else If Not StringIsInteger32(sValue) Then
    sError := 'Second Value '+sValue+' is not numerical'
  Else
    Begin
    iSecond := StringToInteger32(sValue);
    If (iSecond < 0) Or (iSecond > 59) Then
      sError := 'Second Value '+sValue+': Second must be 0 and 59'
    Else
      Result := True;
    End;
End;

Function CheckDot(sValue : String; Var sError : String; Var bInFraction : Boolean) : Boolean;
Begin
  If sValue = '.' Then
    Begin
    Result := True;
    bInFraction := True;
    End
  Else
    Begin
    Result := False;
    sError := 'Expected "."';
    End;
End;

Function CheckFraction(sValue : String; Var sError : String) : Boolean;
Begin
  Result := False;
  If Not StringIsInteger32(sValue) Then
    sError := 'Fraction Value '+sValue+' is not numerical'
  Else
    Result := True;
End;


Function CheckSection(sFormat, sContent : String; Var iYear, iMonth : Integer; Var sError : String; Var bInFraction : Boolean) : Boolean;
Begin
  Result := True;

  If Splice(sFormat, 'YYYY') Then
    Result := CheckYear(splice(sContent, 4), sError, iYear);

  If Result And Splice(sFormat, 'MM') Then
    Result := CheckMonth(splice(sContent, 2), sError, iMonth);

  If Result And Splice(sFormat, 'DD') Then
    Result := CheckDay(splice(sContent, 2), iYear, iMonth, sError);

  If Result And Splice(sFormat, 'HH') Then
    Result := CheckHour(splice(sContent, 2), sError);

  If Result And Splice(sFormat, 'NN') Then
    Result := CheckMinute(splice(sContent, 2), sError);

  If Result And Not bInFraction And Splice(sFormat, 'SS') Then
    Result := CheckSecond(splice(sContent, 2), sError);

  If Result And Not bInFraction And Splice(sFormat, '.') Then
    Result := CheckDot(splice(sContent, 2), sError, bInFraction);

  While Result And bInFraction And Splice(sFormat, 'S') Do
    Result := CheckFraction(splice(sContent, 2), sError);

  If sFormat <> '' Then
    Begin
    Result := False;
    sError := 'The Date Format '+sFormat+' is not known';
    End;
End;

Function CheckSections(sFormat, sContent : String; Const  sOrigFormat, sOrigValue : String; Var sError : String) : Boolean;
Var
  bFirst : Boolean;
  sToken : String;
  sSection : String;
  bInFraction : Boolean;
  iYear : Integer;
  iMonth : Integer;
Begin
  Result := True;
  sFormat := StringStrip(sFormat, ']');
  bInFraction := False;
  bFirst := True;
  iYear := 0;
  iMonth := 0;
  Repeat
    StringSplit(sFormat, '[', sToken, sFormat);
    If sToken <> '' Then  // support use of [ at first point to make everything optional
      Begin
      sSection := Copy(sContent, 1, Length(sToken));
      If sSection = '' Then
        Begin
        If bFirst Then
          Begin
          Result := False;
          sError := StringFormat('The section %s in the Date format %s was not found in the value %s', [sToken, sOrigFormat, sOrigValue]);
          End;
        End
      Else If Length(sSection) < Length(sToken) Then
        Begin
        Result := False;
        sError := StringFormat('The section %s in the Date format %s was not completed in the value %s - value was %s', [sToken, sOrigFormat, sSection, sOrigValue]);
        End
      Else If Not CheckSection(sToken, sSection, iYear, iMonth, sError, bInFraction) Then
        Begin
        Result := False;
        sError := StringFormat('%s (in the Date format %s and the value %s)', [sError, sOrigFormat, sOrigValue]);
        End
      Else
        sContent := Copy(sContent, Length(sSection)+1, $FF);
      End;
    bFirst := False;
  Until Not Result Or (sFormat = '') Or (sContent = '');
End;

Function CheckDateFormat(Const sFormat, sContent : String; Var sError : String) : Boolean;
Var
  bTimezone : Boolean;
  sValue : String;
  sTimezone : String;
  sDateFormat : String;
Begin
  sDateFormat := sFormat;
  bTimezone := Pos('[+/-ZZZZ]', sDateFormat) > 0;
  If bTimezone Then
    Delete(sDateFormat, Pos('[+/-ZZZZ]', sDateFormat), 9);
  StringSplit(sContent, ['+', '-'], sValue, sTimezone);

  Result := CheckSections(sDateFormat, sValue, sFormat, sContent, sError);

  If Result Then
    Result := CheckTimezone(bTimezone, sTimezone, sFormat, sError);
End;

Function ReadIntegerSection(Const sValue : String; iStart, iLength : Integer; sformat : String; iPad : Integer = 0) : Integer;
Var
  sVal : String;
Begin
  if length(sFormat) < iStart + iLength - 1 then
    result := 0
  else
  begin
    sVal := Copy(sValue, iStart, iLength);
    If iPad > 0 Then
      sVal := StringPadLeft(sVal, '0', iPad);
    If (sVal <> '') And StringIsInteger32(sVal) Then
      Result := StringToInteger32(sVal)
    Else
      Result := 0;
  end;
End;

Type
  TTimezoneStatus = (tzNone, tzNeg, tzPos);

Procedure ReadDateSections(Const sFormat, sValue : String;
                 Var iYear, iMonth, iDay, iHour, iMin, iSec, iMSec : Integer;
                 Var aTZStatus : TTimezoneStatus;
                 Var iTZHour, iTZMin : Integer);
Var
  sContent : String;
  sTimezone : String;
  iHourStart : Integer;
Begin
  If Pos('+', sValue) > 0 Then
    aTZStatus := tzPos
  Else If Pos('-', sValue) > 0 Then
    aTZStatus := tzNeg
  Else
    aTZStatus := tzNone;

  Stringsplit(sValue, ['+', '-'], sContent, sTimezone);

  If (sFormat <> '') And (sFormat[1] = 'Y') Then
    Begin
    iHourStart := 8;
    iYear := ReadIntegerSection(sContent, 1, 4, sFormat);
    iMonth := ReadIntegerSection(sContent, 5, 2, sFormat);
    iDay := ReadIntegerSection(sContent, 7, 2, sFormat);
    End
  Else
    Begin
    iHourStart := 0;
    iYear := 0;
    iMonth := 0;
    iDay := 0;
    End;

  iHour := ReadIntegerSection(sContent, iHourStart + 1, 2, sFormat);
  iMin := ReadIntegerSection(sContent, iHourStart + 3, 2, sFormat);
  iSec := ReadIntegerSection(sContent, iHourStart + 5, 2, sFormat);
  iMSec := ReadIntegerSection(sContent, iHourStart + 8, 3, sFormat, 3);

  If sTimezone <> '' Then
    Begin
    iTZHour := ReadIntegerSection(sTimezone, 1, 2, 'YYMM');
    iTZMin := ReadIntegerSection(sTimezone, 3, 2, 'YYMM');
    End
  Else
    Begin
    iTZHour := 0;
    iTZMin := 0;
    End;
End;

Function HL7StringToDate(Const sFormat, sValue : String; bCorrectForTimezone : Boolean):TDateTime;
// this is much looser than the tight validation above
Var
  iYear : Integer;
  iMonth : Integer;
  iDay : Integer;
  iHour : Integer;
  iMin : Integer;
  iSec : Integer;
  iMSec : Integer;
  aTZStatus : TTimezoneStatus;
  iTZHour : Integer;
  iTZMin : Integer;
Begin
  ReadDateSections(sFormat, sValue, iYear, iMonth, iDay, iHour, iMin, iSec, iMSec, aTZStatus, iTZHour, iTZMin);

  if iYear+ iMonth + iDay = 0 then
    result := EncodeTime(iHour, iMin, iSec, iMSec)
  Else
    Result := EncodeDate(iYear, iMonth, iDay) + EncodeTime(iHour, iMin, iSec, iMSec);

  If bCorrectForTimezone And (aTZStatus <> tzNone) Then // if no timezone specified then local timezone is assumed
    Begin
    If aTZStatus = tzNeg Then
      Result := Result + EncodeTime(iTZHour, iTZMin, 0, 0) + TimezoneBias // or should it be minus?
    Else
      Result := Result - EncodeTime(iTZHour, iTZMin, 0, 0) + TimezoneBias // or should it be minus?
    End;
End;

*)
Function HL7DateToString(aValue : TDateTime; Const sFormat : String; bTimezone : Boolean) : String;
Begin
  Result := FormatDateTime(sFormat, aValue, FormatSettings);
  If bTimezone Then
    If TimezoneBias < 0 Then
      Result := Result+'-'+FormatDateTime('HHNN', TimezoneBias, FormatSettings)
    Else
      Result := Result+'+'+FormatDateTime('HHNN', TimezoneBias, FormatSettings)
End;
(*

Procedure CheckDOBReal(Const sDate, sDescription: String);
Var
  aValue : TDateTime;
  sError : String;
  iYear : Integer;
  iMonth : Integer;
  iDay : Integer;
  iHour : Integer;
  iMin : Integer;
  iSec : Integer;
  iMSec : Integer;
  aTZStatus : TTimezoneStatus;
  iTZHour : Integer;
  iTZMin : Integer;
Begin
  ReadDateSections('YYYYMMDD', sDate, iYear, iMonth, iDay, iHour, iMin, iSec, iMSec, aTZStatus, iTZHour, iTZMin);

  If Not CheckDateFormat('YYYY[MM[DD[HH[MM[SS[.S[S[S[S]]]]]]]]][+/-ZZZZ]', sDate, sError) Then
    Raise Exception.Create('DOB ' + sDescription + ' is not a valid Date: '+sError);

  ReadDateSections('YYYYMMDD', sDate, iYear, iMonth, iDay, iHour, iMin, iSec, iMSec, aTZStatus, iTZHour, iTZMin);

  If iYear < 1850 Then
    Raise Exception.Create('DOB ' + sDescription + ' is not a valid Date: Year ' + IntegerToString(iYear) + ' is not plausible');


  aValue := HL7StringToDate('YYYYMMDD', sDate, True);
  If aValue > LocalDateTime + 30 * DATETIME_MINUTE_ONE Then
    Raise Exception.Create('DOB ' + sDescription + ' is not a valid Date: Value '+sDate+'" is in the future');
End;


Procedure CheckDODReal(Const sDate, sDescription: String);
Var
  aValue : TDateTime;
  sError : String;
  iYear : Integer;
  iMonth : Integer;
  iDay : Integer;
  iHour : Integer;
  iMin : Integer;
  iSec : Integer;
  iMSec : Integer;
  aTZStatus : TTimezoneStatus;
  iTZHour : Integer;
  iTZMin : Integer;
Begin
  ReadDateSections('YYYYMMDD', sDate, iYear, iMonth, iDay, iHour, iMin, iSec, iMSec, aTZStatus, iTZHour, iTZMin);

  If Not CheckDateFormat('YYYY[MM[DD[HH[NN[SS[.S[S[S[S]]]]]]]]][+/-ZZZZ]', sDate, sError) Then
    Raise Exception.Create('DOD ' + sDescription + ' is not a valid Date: '+sError);

  ReadDateSections('YYYYMMDD', sDate, iYear, iMonth, iDay, iHour, iMin, iSec, iMSec, aTZStatus, iTZHour, iTZMin);

  If iYear < 1850 Then
    Raise Exception.Create('DOD ' + sDescription + ' is not a valid Date: Year ' + IntegerToString(iYear) + ' is not plausible');

  aValue := HL7StringToDate('YYYYMMDD', sDate, True);
  If aValue > LocalDateTime + 30 * DATETIME_MINUTE_ONE Then
    Raise Exception.Create('DOD ' + sDescription + ' is not a valid Date: Value '+sDate+'" is in the future');
End;

*)
End.
