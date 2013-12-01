Unit MathSupport;

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
  Math;


Function IntegerCompare(Const iA, iB : Byte) : Integer; Overload;
Function IntegerCompare(Const iA, iB : Word) : Integer; Overload;
Function IntegerCompare(Const iA, iB : Integer) : Integer; Overload;
Function IntegerCompare(Const iA, iB : Cardinal) : Integer; Overload;
Function IntegerCompare(Const iA, iB : Int64) : Integer; Overload;
Function IntegerCompare(Const iA, iB, iThreshold : Int64) : Integer; Overload;
Function BooleanCompare(Const bA, bB : Boolean) : Integer; Overload;
Function RealCompare(Const rA, rB : Extended) : Integer; Overload;
Function RealCompare(Const rA, rB : Real) : Integer; Overload;
Function RealCompare(Const rA, rB, rThreshold : Real) : Integer; Overload;

Function IntegerEquals(Const iA, iB : Byte) : Boolean; Overload;
Function IntegerEquals(Const iA, iB : Word) : Boolean; Overload;
Function IntegerEquals(Const iA, iB : Integer) : Boolean; Overload;
Function IntegerEquals(Const iA, iB : Cardinal) : Boolean; Overload;
Function IntegerEquals(Const iA, iB : Int64) : Boolean; Overload;
Function IntegerEquals(Const iA, iB, iThreshold : Int64) : Boolean; Overload;
Function BooleanEquals(Const bA, bB : Boolean) : Boolean; Overload;
Function RealEquals(Const rA, rB : Real) : Boolean; Overload;
Function RealEquals(Const rA, rB, rThreshold : Real) : Boolean; Overload;

Function IntegerBetweenInclusive(Const iLeft, iCheck, iRight : Integer) : Boolean; Overload;
Function IntegerBetweenExclusive(Const iLeft, iCheck, iRight : Integer) : Boolean; Overload;
Function IntegerBetween(Const iLeft, iCheck, iRight : Integer) : Boolean; Overload;

Function CardinalBetweenInclusive(Const iLeft, iCheck, iRight : Cardinal) : Boolean; Overload;
Function CardinalBetweenExclusive(Const iLeft, iCheck, iRight : Cardinal) : Boolean; Overload;

Function IntegerBetweenInclusive(Const iLeft, iCheck, iRight : Int64) : Boolean; Overload;
Function IntegerBetweenExclusive(Const iLeft, iCheck, iRight : Int64) : Boolean; Overload;
Function IntegerBetween(Const iLeft, iCheck, iRight : Int64) : Boolean; Overload;

Function RealBetweenInclusive(Const rLeft, rCheck, rRight : Real) : Boolean; Overload;
Function RealBetweenExclusive(Const rLeft, rCheck, rRight : Real) : Boolean; Overload;
Function RealBetween(Const rLeft, rCheck, rRight : Real) : Boolean; Overload;

Function RealBetweenInclusive(Const rLeft, rCheck, rRight : Extended) : Boolean; Overload;
Function RealBetweenExclusive(Const rLeft, rCheck, rRight : Extended) : Boolean; Overload;
Function RealBetween(Const rLeft, rCheck, rRight : Extended) : Boolean; Overload;

Function IntegerMin(Const A, B : Integer) : Integer; Overload;
Function IntegerMax(Const A, B : Integer) : Integer; Overload;
Function IntegerMin(Const A, B : Cardinal) : Cardinal; Overload;
Function IntegerMax(Const A, B : Cardinal) : Cardinal; Overload;
Function IntegerMin(Const A, B : Word) : Word; Overload;
Function IntegerMax(Const A, B : Word) : Word; Overload;
Function IntegerMin(Const A, B : Byte) : Byte; Overload;
Function IntegerMax(Const A, B : Byte) : Byte; Overload;
Function IntegerMin(Const A, B : Int64) : Int64; Overload;
Function IntegerMax(Const A, B : Int64) : Int64; Overload;
Function RealMin(Const A, B : Real) : Real; Overload;
Function RealMax(Const A, B : Real) : Real; Overload;

Function RealCeiling(Const rValue : Real) : Int64; Overload;
Function RealFloor(Const rValue : Real) : Int64; Overload;

Function Int64Round(Const iValue, iFactor : Int64) : Int64; Overload;

Function RealSquare(Const rValue : Extended) : Extended; Overload;
Function RealSquareRoot(Const rValue : Extended) : Extended; Overload;

Function IntegerArrayMax(Const aIntegers : Array Of Integer) : Integer; Overload;
Function IntegerArrayMin(Const aIntegers : Array Of Integer) : Integer; Overload;
Function IntegerArraySum(Const aIntegers : Array Of Integer) : Integer; Overload;
Function IntegerArrayIndexOf(Const aIntegers : Array Of Word; iValue : Word) : Integer; Overload;
Function IntegerArrayIndexOf(Const aIntegers : Array Of Integer; iValue : Integer) : Integer; Overload;
Function IntegerArrayIndexOf(Const aIntegers : Array Of Cardinal; iValue : Cardinal) : Integer; Overload;
Function IntegerArrayExists(Const aIntegers : Array Of Integer; iValue : Integer) : Boolean; Overload;
Function IntegerArrayValid(Const aIntegers : Array Of Integer; iIndex : Integer) : Boolean; Overload;

Function RealRoundToInteger(Const aValue : Extended) : Int64; Overload;

Function Abs(Const iValue : Int64) : Int64; Overload;
Function Abs(Const iValue : Integer) : Integer; Overload;
Function Abs(Const rValue : Real) : Real; Overload;
Function Sign(Const iValue : Integer) : Integer; Overload;

Function IntegerConstrain(Const iValue, iMin, iMax : Integer) : Integer; Overload;
Function RealConstrain(Const rValue, rMin, rMax : Real) : Real; Overload;

Function Sin(Const Theta : Extended) : Extended; Overload;
Function Cos(Const Theta : Extended) : Extended; Overload;
Function ArcTan(Const Theta : Extended) : Extended; Overload;
Function ArcTan2(Const X, Y : Extended) : Extended; Overload;

Function DegreesToRadians(Const rDegrees : Extended) : Extended; Overload;
Function RadiansToDegrees(Const rRadians : Extended) : Extended; Overload;

Function Power(Const rBase : Extended; Const iExponent : Integer) : Extended; Overload;
Function Power(Const rBase, rExponent : Extended) : Extended; Overload;

Function LnXP1(Const X : Extended) : Extended; Overload;
Function LogN(Const Base, X : Extended) : Extended; Overload;
Function Log10(Const X : Extended) : Extended; Overload;
Function Log2(Const X : Extended)  : Extended; Overload;
Function Hypotenuse(Const X, Y : Extended) : Extended; Overload;

Function Percentage(Const iPart, iTotal : Integer) : Real; Overload;

Function SignedMod(Const iValue : Integer; Const iRange : Integer) : Integer; Overload;
Function SignedMod(Const iValue : Int64; Const iRange : Int64) : Int64; Overload;
Function UnsignedMod(Const iValue : Cardinal; Const iRange : Cardinal) : Cardinal; Overload;
Function UnsignedMod(Const iValue : Integer; Const iRange : Integer) : Integer; Overload;
Function UnsignedMod(Const iValue : Int64; Const iRange : Int64) : Int64; Overload;

Function RemoveRemainder(Const iValue : Cardinal; Const iRange : Cardinal) : Cardinal; Overload;
Function RemoveRemainder(Const iValue : Integer; Const iRange : Integer) : Integer; Overload;
Function RemoveRemainder(Const iValue : Int64; Const iRange : Int64) : Int64; Overload;

Function GreatestCommonDivisor(Const iA, iB : Integer) : Integer;


Implementation


Function Percentage(Const iPart, iTotal : Integer) : Real;
Begin
  If (iTotal = 0) Then
    Result := 0
  Else
    Result := iPart / iTotal;
End;


Function IntegerBetweenInclusive(Const iLeft, iCheck, iRight : Integer) : Boolean;
Begin
  Result := (iLeft <= iCheck) And (iCheck <= iRight);
End;


Function IntegerBetweenExclusive(Const iLeft, iCheck, iRight : Integer) : Boolean;
Begin
  Result := (iLeft < iCheck) And (iCheck < iRight);
End;


Function IntegerBetween(Const iLeft, iCheck, iRight : Integer) : Boolean;
Begin
  Result := IntegerBetweenInclusive(iLeft, iCheck, iRight);
End;


Function CardinalBetweenInclusive(Const iLeft, iCheck, iRight : Cardinal) : Boolean;
Begin
  Result := (iLeft <= iCheck) And (iCheck <= iRight);
End;


Function CardinalBetweenExclusive(Const iLeft, iCheck, iRight : Cardinal) : Boolean;
Begin
  Result := (iLeft < iCheck) And (iCheck < iRight);
End;


Function IntegerBetweenInclusive(Const iLeft, iCheck, iRight : Int64) : Boolean;
Begin
  Result := (iLeft <= iCheck) And (iCheck <= iRight);
End;


Function IntegerBetweenExclusive(Const iLeft, iCheck, iRight : Int64) : Boolean;
Begin
  Result := (iLeft < iCheck) And (iCheck < iRight);
End;


Function IntegerBetween(Const iLeft, iCheck, iRight : Int64) : Boolean;
Begin
  Result := IntegerBetweenInclusive(iLeft, iCheck, iRight);
End;


Function RealBetweenInclusive(Const rLeft, rCheck, rRight : Real) : Boolean;
Begin
  Result := (rLeft <= rCheck) And (rCheck <= rRight);
End;


Function RealBetweenExclusive(Const rLeft, rCheck, rRight : Real) : Boolean;
Begin
  Result := (rLeft < rCheck) And (rCheck < rRight);
End;


Function RealBetween(Const rLeft, rCheck, rRight : Real) : Boolean;
Begin
  Result := RealBetweenInclusive(rLeft, rCheck, rRight);
End;


Function RealBetweenInclusive(Const rLeft, rCheck, rRight : Extended) : Boolean;
Begin
  Result := (rLeft <= rCheck) And (rCheck <= rRight);
End;


Function RealBetweenExclusive(Const rLeft, rCheck, rRight : Extended) : Boolean;
Begin
  Result := (rLeft < rCheck) And (rCheck < rRight);
End;


Function RealBetween(Const rLeft, rCheck, rRight : Extended) : Boolean;
Begin
  Result := RealBetweenInclusive(rLeft, rCheck, rRight);
End;


Function IntegerMin(Const A, B : Integer) : Integer;
Begin
  If A < B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMax(Const A, B : Integer) : Integer;
Begin
  If A > B Then
    Result := A
  Else
    Result := B;
End;


Function RealMin(Const A, B : Real) : Real;
Begin
  If A < B Then
    Result := A
  Else
    Result := B;
End;


Function RealMax(Const A, B : Real) : Real;
Begin
  If A > B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMin(Const A, B : Cardinal) : Cardinal;
Begin
  If A < B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMax(Const A, B : Cardinal) : Cardinal;
Begin
  If A > B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMin(Const A, B : Word) : Word;
Begin
  If A < B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMax(Const A, B : Word) : Word;
Begin
  If A > B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMin(Const A, B : Byte) : Byte;
Begin
  If A < B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMax(Const A, B : Byte) : Byte;
Begin
  If A > B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMin(Const A, B : Int64) : Int64;
Begin
  If A < B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerMax(Const A, B : Int64) : Int64;
Begin
  If A > B Then
    Result := A
  Else
    Result := B;
End;


Function IntegerArrayMin(Const aIntegers : Array Of Integer) : Integer;
Var
  iLoop : Integer;
Begin
  Result := aIntegers[Low(aIntegers)];

  For iLoop := Low(aIntegers) + 1 To High(aIntegers) Do
  Begin
    If Result > aIntegers[iLoop] Then
      Result := aIntegers[iLoop];
  End;
End;


Function IntegerArrayMax(Const aIntegers : Array Of Integer) : Integer;
Var
  iLoop : Integer;
Begin
  Result := aIntegers[Low(aIntegers)];

  For iLoop := Low(aIntegers) + 1 To High(aIntegers) Do
  Begin
    If Result < aIntegers[iLoop] Then
      Result := aIntegers[iLoop];
  End;
End;


Function IntegerArraySum(Const aIntegers : Array Of Integer) : Integer;
Var
  iLoop : Integer;
Begin
  Result := 0;
  For iLoop := 0 To Length(aIntegers) - 1 Do
    Inc(Result, aIntegers[iLoop]);
End;


Function RealCeiling(Const rValue : Real) : Int64;
Begin
  Result := Trunc(rValue);
  If Frac(rValue) > 0 Then
    Inc(Result);
End;


Function RealFloor(Const rValue : Real) : Int64;
Begin
  Result := Trunc(rValue);
  If Frac(rValue) < 0 Then
    Dec(Result);
End;


Function Int64Round(Const iValue, iFactor : Int64) : Int64;
Var
  iFulcrum : Int64;
  iRemain : Int64;
Begin 
  iFulcrum := iFactor Div 2;
  iRemain := SignedMod(iValue, iFactor);

  If iRemain < iFulcrum Then
    Result := iValue - iRemain
  Else If iRemain > iFulcrum Then
    Result := iValue + (iFactor - iRemain)
  Else
    Result := iValue;
End;  


Function RealSquare(Const rValue : Extended) : Extended;
Begin
  Result := rValue * rValue;
End;


Function RealSquareRoot(Const rValue : Extended) : Extended;
Begin
  Result := System.Sqrt(rValue);
End;


Function IntegerArrayIndexOf(Const aIntegers : Array Of Word; iValue : Word) : Integer;
Begin
  Result := High(aIntegers);
  While (Result >= 0) And (aIntegers[Result] <> iValue) Do
    Dec(Result);
End;


Function IntegerArrayIndexOf(Const aIntegers : Array Of Integer; iValue : Integer) : Integer;
Begin
  Result := High(aIntegers);
  While (Result >= 0) And (aIntegers[Result] <> iValue) Do
    Dec(Result);
End;


Function IntegerArrayIndexOf(Const aIntegers : Array Of Cardinal; iValue : Cardinal) : Integer;
Begin 
  Result := High(aIntegers);
  While (Result >= 0) And (aIntegers[Result] <> iValue) Do
    Dec(Result);
End;


Function IntegerArrayExists(Const aIntegers : Array Of Integer; iValue : Integer) : Boolean;
Begin
  Result := IntegerArrayValid(aIntegers, IntegerArrayIndexOf(aIntegers, iValue));
End;


Function IntegerArrayValid(Const aIntegers : Array Of Integer; iIndex : Integer) : Boolean;
Begin
  Result := IntegerBetweenInclusive(Low(aIntegers), iIndex, High(aIntegers));
End;


Function RealRoundToInteger(Const aValue : Extended) : Int64; 
Begin
  Result := System.Round(aValue);
End;


Function Sign(Const iValue : Integer) : Integer;
Begin 
  If iValue < 0 Then
    Result := -1
  Else If iValue > 0 Then
    Result := 1
  Else
    Result := 0;
End;  


Function IntegerConstrain(Const iValue, iMin, iMax : Integer) : Integer;
Begin
  If iValue < iMin Then
    Result := iMin
  Else If iValue > iMax Then
    Result := iMax
  Else
    Result := iValue;
End;


Function RealConstrain(Const rValue, rMin, rMax : Real) : Real;
Begin 
  If rValue < rMin Then
    Result := rMin
  Else If rValue > rMax Then
    Result := rMax
  Else
    Result := rValue;
End;  


Function Abs(Const iValue : Int64) : Int64;
Begin
  If iValue < 0 Then
    Result := -iValue
  Else
    Result := iValue;
End;


Function Abs(Const iValue : Integer) : Integer;
Begin
  If iValue < 0 Then
    Result := -iValue
  Else
    Result := iValue;
End;


Function Abs(Const rValue : Real) : Real;
Begin 
  If rValue < 0 Then
    Result := -rValue
  Else
    Result := rValue;
End;  


Function IntegerCompare(Const iA, iB : Byte) : Integer;
Begin
  If iA < iB Then
    Result := -1
  Else If iA > iB Then
    Result := 1
  Else
    Result := 0
End;


Function BooleanCompare(Const bA, bB : Boolean) : Integer;
Begin
  If bA < bB Then
    Result := -1
  Else If bA > bB Then
    Result := 1
  Else
    Result := 0
End;


Function IntegerCompare(Const iA, iB : Word) : Integer;
Begin
  If iA < iB Then
    Result := -1
  Else If iA > iB Then
    Result := 1
  Else
    Result := 0
End;


Function IntegerCompare(Const iA, iB, iThreshold : Int64) : Integer;
Begin
  If iA - iThreshold > iB Then
    Result := 1
  Else If iB - iThreshold > iA Then
    Result := -1
  Else
    Result := 0;
End;


Function IntegerCompare(Const iA, iB : Integer) : Integer;
Begin 
  If iA < iB Then
    Result := -1
  Else If iA > iB Then
    Result := 1
  Else
    Result := 0
End;


Function IntegerCompare(Const iA, iB : Cardinal) : Integer;
Begin
  If iA < iB Then
    Result := -1
  Else If iA > iB Then
    Result := 1
  Else
    Result := 0
End;


Function IntegerCompare(Const iA, iB : Int64) : Integer;
Begin
  If iA < iB Then
    Result := -1
  Else If iA > iB Then
    Result := 1
  Else
    Result := 0
End;


Function RealCompare(Const rA, rB : Real) : Integer;
Begin
  If rA > rB Then
    Result := 1
  Else If rA < rB Then
    Result := -1
  Else
    Result := 0;
End;


Function RealCompare(Const rA, rB : Extended) : Integer;
Begin
  If rA > rB Then
    Result := 1
  Else If rA < rB Then
    Result := -1
  Else
    Result := 0;
End;


Function RealCompare(Const rA, rB, rThreshold : Real) : Integer;
Begin
  If rA - rThreshold > rB Then
    Result := 1
  Else If rB - rThreshold > rA Then
    Result := -1
  Else
    Result := 0;
End;  


Function RealEquals(Const rA, rB, rThreshold : Real) : Boolean;
Begin 
  Result := RealCompare(rA, rB, rThreshold) = 0;
End;  


Function BooleanEquals(Const bA, bB : Boolean) : Boolean;
Begin 
  Result := BooleanCompare(bA, bB) = 0;
End;


Function IntegerEquals(Const iA, iB : Byte) : Boolean;
Begin
  Result := IntegerCompare(iA, iB) = 0;
End;


Function IntegerEquals(Const iA, iB : Word) : Boolean;
Begin
  Result := IntegerCompare(iA, iB) = 0;
End;


Function IntegerEquals(Const iA, iB : Integer) : Boolean;
Begin
  Result := IntegerCompare(iA, iB) = 0;
End;


Function IntegerEquals(Const iA, iB : Cardinal) : Boolean;
Begin
  Result := IntegerCompare(iA, iB) = 0;
End;


Function IntegerEquals(Const iA, iB : Int64) : Boolean;
Begin
  Result := IntegerCompare(iA, iB) = 0;
End;


Function IntegerEquals(Const iA, iB, iThreshold : Int64) : Boolean;
Begin
  Result := IntegerCompare(iA, iB, iThreshold) = 0;
End;


Function RealEquals(Const rA, rB : Real) : Boolean;
Begin
  Result := RealCompare(rA, rB) = 0;
End;


Function Sin(Const Theta : Extended) : Extended;
Begin
  Result := System.Sin(Theta);
End;


Function Cos(Const Theta : Extended) : Extended;
Begin 
  Result := System.Cos(Theta);
End;  


Function ArcTan(Const Theta : Extended) : Extended;
Begin 
  Result := System.ArcTan(Theta);
End;  


Function ArcTan2(Const X, Y : Extended) : Extended;
Begin 
  Result := Math.ArcTan2(X, Y);
End;  


Function DegreesToRadians(Const rDegrees : Extended) : Extended;
Begin 
  Result := Math.DegToRad(rDegrees);
End;  


Function RadiansToDegrees(Const rRadians : Extended) : Extended;
Begin 
  Result := Math.RadToDeg(rRadians);
End;  


Function Power(Const rBase : Extended; Const iExponent: Integer): Extended;
Begin 
  Result := Math.Power(rBase, iExponent);
End;  


Function Power(Const rBase, rExponent: Extended): Extended;
Begin 
  Result := Math.Power(rBase, rExponent);
End;  


Function LnXP1(Const X: Extended): Extended;
Begin 
  Result := Math.LnXP1(X);
End;  


Function LogN(Const Base, X: Extended): Extended;
Begin 
  Result := Math.LogN(Base, X);
End;  


Function Log10(Const X: Extended): Extended;
Begin 
  Result := Math.Log10(X);
End;  


Function Log2(Const X: Extended): Extended;
Begin 
  Result := Math.Log2(X);
End;  


Function Hypotenuse(Const X, Y: Extended): Extended;
Begin 
  Result := Math.Hypot(X, Y);
End;


Function SignedMod(Const iValue : Integer; Const iRange : Integer) : Integer;
Begin
  Result := iValue Mod iRange;
End;


Function SignedMod(Const iValue : Int64; Const iRange : Int64) : Int64;
Begin
  Result := iValue Mod iRange;
End;


Function UnsignedMod(Const iValue : Cardinal; Const iRange : Cardinal) : Cardinal;
Begin
  Result := iValue Mod iRange;
End;


Function UnsignedMod(Const iValue : Integer; Const iRange : Integer) : Integer;
Begin
  Result := iValue Mod iRange;

  If Result < 0 Then
    Result := Result + iRange;
End;


Function UnsignedMod(Const iValue : Int64; Const iRange : Int64) : Int64;
Begin
  Result := iValue Mod iRange;

  If Result < 0 Then
    Result := Result + iRange;
End;


Function RemoveRemainder(Const iValue : Cardinal; Const iRange : Cardinal) : Cardinal;
Begin
  Result := iValue - UnsignedMod(iValue, iRange);
End;


Function RemoveRemainder(Const iValue : Integer; Const iRange : Integer) : Integer;
Begin
  Result := iValue - UnsignedMod(iValue, iRange);
End;


Function RemoveRemainder(Const iValue : Int64; Const iRange : Int64) : Int64;
Begin
  Result := iValue - UnsignedMod(iValue, iRange);
End;


Function GreatestCommonDivisor(Const iA, iB : Integer) : Integer;
Var
  iMinValue : Integer;
  iMaxValue : Integer;
  iCurrentNumerator : Integer;
  iCurrentDenominator : Integer;
  iCurrentRemainder : Integer;
Begin
  If iA = iB Then
  Begin
    Result := iA;
  End
  Else
  Begin
    If iA > iB Then
    Begin
      iMaxValue := iA;
      iMinValue := iB;
    End
    Else
    Begin
      iMaxValue := iB;
      iMinValue := iA;
    End;

    If (iMinValue = 0) Then
    Begin
      Result := 0;
    End
    Else 
    Begin
      iCurrentNumerator := iMaxValue;
      iCurrentDenominator := iMinValue;

      Repeat
        iCurrentRemainder := iCurrentNumerator Mod iCurrentDenominator;

        iCurrentNumerator := iCurrentDenominator;
        iCurrentDenominator := iCurrentRemainder;
      Until iCurrentRemainder = 0;

      Result := iCurrentNumerator;
    End;
  End;
End;


End. // MathSupport //
