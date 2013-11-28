Unit CurrencySupport;


{! 30 !}


Interface


Uses
  SysUtils,
  StringSupport, MathSupport;


Const
  CURRENCY_MINIMUM = -922337203685477.58;
  CURRENCY_MAXIMUM = 922337203685477.58;


Type
  TCurrency = Currency;
(*  TCurrencyCents = Int64;
  TCurrencyDollars = Int64;


Function CurrencyCompare(Const rA, rB : TCurrency) : Integer; Overload;
Function CurrencyCompare(Const rA, rB, rThreshold : TCurrency) : Integer; Overload;
Function CurrencyEquals(Const rA, rB, rThreshold : TCurrency) : Boolean; Overload;
Function CurrencyEquals(Const rA, rB : TCurrency) : Boolean; Overload;
Function CurrencyEqualsZero(Const rAmount : TCurrency) : Boolean;

Function CurrencyToString(Const rValue : TCurrency; iDigits : Integer = 2) : String;
Function StringToCurrency(Const sValue : String; Const rDefault : TCurrency) : TCurrency; Overload;
Function StringToCurrency(Const sValue : String) : TCurrency; Overload;

Function CentsToCurrency(Const iCents : TCurrencyCents) : TCurrency;
Function CurrencyToCents(Const iCurrency : TCurrency) : TCurrencyCents;

Function CurrencyDifference(Const iAmount1, iAmount2 : TCurrency) : TCurrency;
Function CurrencyRoundUp(Const iValue : TCurrency; Const iRoundCents : Integer) : TCurrency;
Function CurrencyRoundDown(Const iValue : TCurrency; Const iRoundCents : Integer) : TCurrency;
Function CurrencyRoundNearest(Const iValue : TCurrency; Const iRoundCents : Integer) : TCurrency;
Function CurrencyRoundBankers(Const rValue : TCurrency; Const iRoundCents : Integer = 1) : TCurrency;

Function StringToCents(Const sValue : String) : TCurrencyCents;
Function CentsToString(Const iCents : TCurrencyCents) : String;

Function StringIsCurrency(Const sValue : String) : Boolean;

Function CurrencySymbol : String;

Function CurrencyMin(Const rA, rB : TCurrency) : TCurrency;
Function CurrencyMax(Const rA, rB : TCurrency) : TCurrency;

Function CurrencyAdjusted(Const rAmount, rGap : TCurrency; Const rPercentage : Real) : TCurrency;
Function CurrencyApplyPercentages(Const rAmount : TCurrency; Const rPercentageBefore, rPercentageAfter : Real) : TCurrency;
Function CurrencyTruncateToCents(Const rAmount : TCurrency) : TCurrency;
Function CurrencyTruncateToDollars(Const rAmount : TCurrency) : TCurrency;

 *)
Implementation

(*
Function CurrencySymbol : String;
Begin
  Result := FormatSettings.CurrencyString;
End;


Function CurrencyToString(Const rValue : TCurrency; iDigits : Integer = 2) : String;
Begin
  If (iDigits < 0) Or (iDigits > 4) Then
    iDigits := 2;

  Result := SysUtils.CurrToStrF(rValue, ffFixed, iDigits);
End;


Function StringToCurrency(Const sValue : String) : TCurrency;
Begin
  Result := StrToCurr(StringReplace(sValue, FormatSettings.CurrencyString, ''));
End;


Function StringToCurrency(Const sValue : String; Const rDefault : TCurrency) : TCurrency;
Begin
  Try
    Result := StringToCurrency(sValue);
  Except
    Result := rDefault;
  End;
End;


Function StringIsCurrency(Const sValue : String) : Boolean;
Var
  rDummy : Currency;
Begin
  Result := TextToFloat(PChar(sValue), rDummy, fvCurrency);
End;


Function StringToCents(Const sValue : String) : TCurrencyCents;
Var
  sNormal : String;
  iPoint : Integer;
Begin 
  iPoint := Pos('.', sValue);
  If iPoint = 0 Then
    sNormal := sValue + '00'
  Else If iPoint = Length(sValue) Then
    sNormal := Copy(sValue, 1, Length(sValue) - 1) + '00'
  Else If iPoint = Length(sValue) - 1 Then
    sNormal := Copy(sValue, 1, Length(sValue) - 2) + sValue[Length(sValue)] + '0'
  Else
    sNormal := Copy(sValue, 1, iPoint - 1) + Copy(sValue, iPoint + 1, MaxInt);

  Result := MathSupport.Abs(StringToInteger32(sNormal));
End;  


Function CentsToString(Const iCents : TCurrencyCents) : String;
Begin
  Result := IntegerToString(Abs(iCents));

  While Length(Result) < 3 Do
    Result := '0' + Result;

  System.Insert('.', Result, Length(Result) - 1);

  If iCents < 0 Then
    Result := '(' + Result + ')';
End;


Function CurrencyRoundUp(Const iValue : TCurrency; Const iRoundCents : Integer) : TCurrency;
Var
  iIncrement : Currency;
  iDollars : Currency;
  iCents : Currency;
Begin
  iIncrement := iRoundCents / 100;
  iDollars := Trunc(iValue);
  iCents := iValue - iDollars;

  Result :=  iDollars + (iIncrement * RealCeiling(iCents / iIncrement));
End;


Function CurrencyRoundDown(Const iValue : TCurrency; Const iRoundCents : Integer) : TCurrency;
Var
  iIncrement : Currency;
  iDollars : Currency;
  iCents : Currency;
Begin
  iIncrement := iRoundCents / 100;
  iDollars := Trunc(iValue);
  iCents := iValue - iDollars;

  Result :=  iDollars + (iIncrement * RealFloor(iCents / iIncrement));
End;


Function CurrencyRoundNearest(Const iValue : TCurrency; Const iRoundCents : Integer) : TCurrency;
Var
  iIncrement : Currency;
  iDollars : Currency;
  iCents : Currency;
Begin
  iIncrement := iRoundCents / 100;
  iDollars := Trunc(iValue);
  iCents := iValue - iDollars;

  Result :=  iDollars + (iIncrement * RealFloor((iCents + (iIncrement / 2)) / iIncrement));
End;


Function CurrencyRoundBankers(Const rValue : TCurrency; Const iRoundCents : Integer) : TCurrency;
Var
  iWhole : Int64;
  rFraction : Extended;
Begin
  iWhole := Trunc(CurrencyToCents(rValue) / iRoundCents);
  rFraction := (CurrencyToCents(rValue) - (iWhole * iRoundCents)) / iRoundCents;

  If (rFraction > 0.5) Or ((rFraction = 0.5) And (SignedMod(iWhole, 2) = 1)) Then
    Result := iWhole + 1
  Else
    Result := iWhole;

  Result := (Result * iRoundCents) / 100;
End;


Function CurrencyToCents(Const iCurrency : TCurrency) : TCurrencyCents;
Begin
  Result := Trunc(iCurrency * 100);
End;


Function CentsToCurrency(Const iCents : TCurrencyCents) : TCurrency; Overload;
Begin
  Result := iCents;
  Result := Result / 100;
End;


Function CurrencyMin(Const rA, rB : TCurrency) : TCurrency;
Begin
  If rA < rB Then
    Result := rA
  Else
    Result := rB;
End;


Function CurrencyMax(Const rA, rB : TCurrency) : TCurrency;
Begin
  If rA > rB Then
    Result := rA
  Else
    Result := rB;
End;


Function CurrencyCompare(Const rA, rB : TCurrency) : Integer;
Begin 
  If rA < rB Then
    Result := -1
  Else If rA > rB Then
    Result := 1
  Else
    Result := 0
End;  


Function CurrencyDifference(Const iAmount1, iAmount2 : TCurrency) : TCurrency; Overload;
Begin
  If iAmount1 > iAmount2 Then
    Result := iAmount1 - iAmount2
  Else
    Result := iAmount2 - iAmount1;
End;  


Function CurrencyAdjusted(Const rAmount, rGap : TCurrency; Const rPercentage : Real) : TCurrency;
Begin 
  Result := CurrencyRoundUp(rAmount * rPercentage / 100, 5);

  If (Result <> 0) And (rPercentage < 100) And (rGap <> 0) And (Abs(rAmount - Result) > rGap) Then
    Result := rAmount - rGap;
End;  


Function CurrencyApplyPercentages(Const rAmount : TCurrency; Const rPercentageBefore, rPercentageAfter : Real):TCurrency;
Begin 
  Result := rAmount;

  If rPercentageBefore <> 100 Then
    Result := CurrencyAdjusted(Result, 0, rPercentageBefore);

  If rPercentageAfter <> 100 Then
    Result := CurrencyRoundUp(Result * rPercentageAfter / 100, 5);
End;  


Function CurrencyTruncateToCents(Const rAmount : TCurrency):TCurrency; Overload;
Begin
  Result := Trunc(rAmount * 100) / 100;
End;


Function CurrencyTruncateToDollars(Const rAmount : TCurrency): TCurrency; Overload;
Begin
  Result := Trunc(rAmount);
End;


Function CurrencyCompare(Const rA, rB, rThreshold : TCurrency) : Integer;
Begin
  If rA - rThreshold > rB Then
    Result := 1
  Else If rB - rThreshold > rA Then
    Result := -1
  Else
    Result := 0;
End;


Function CurrencyEquals(Const rA, rB, rThreshold : TCurrency) : Boolean;
Begin
  Result := CurrencyCompare(rA, rB, rThreshold) = 0;
End;


Function CurrencyEqualsZero(Const rAmount : TCurrency) : Boolean;
Begin
  Result := CurrencyEquals(rAmount, 0, 0.001);
End;


Function CurrencyEquals(Const rA, rB : TCurrency) : Boolean;
Begin
  Result := rA = rB;
End;

*)

End.
