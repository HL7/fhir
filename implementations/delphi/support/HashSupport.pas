Unit HashSupport;


{! 9 !}


Interface


Uses
  SysUtils;


Function HashStringToCode32(Const sValue : String) : Integer;
Function HashStringToCode64(Const sValue : String) : Int64;
Function HashIntegerToCode32(Const iValue : Integer) : Integer;
Function HashInteger64ToCode32(Const iValue : Int64) : Integer;


Implementation


{$RANGECHECKS OFF}
{$OVERFLOWCHECKS OFF}
Function HashStringToCode32(Const sValue : String) : Integer;
Var
  cFirst  : Char;
  cSecond : Char;
  iLength : Cardinal;
  iLoop   : Integer;
Begin
  Result := 0;
  iLength := Length(sValue);

  If iLength > 0 Then
  Begin
    cFirst := sValue[1];

    If (cFirst >= 'a') And (cFirst <= 'z') Then
      Dec(cFirst, 32);

    For iLoop := 2 To iLength Do
    Begin
      cSecond := sValue[iLoop];

      If (cSecond >= 'a') And (cSecond <= 'z') Then
        Dec(cSecond, 32);

      Inc(Result, Ord(cFirst) * Ord(cSecond) * iLoop);

      cFirst := cSecond;
    End;
  End;
End;
{$OVERFLOWCHECKS ON}
{$RANGECHECKS ON}


{$RANGECHECKS OFF}
{$OVERFLOWCHECKS OFF}
Function HashStringToCode64(Const sValue : String) : Int64;
Begin
  // TODO: implement.
  
  Raise Exception.Create('HashStringToCode64 is not implemented.');

  Result := 0;
End;
{$OVERFLOWCHECKS ON}
{$RANGECHECKS ON}


{$RANGECHECKS OFF}
{$OVERFLOWCHECKS OFF}
Function HashIntegerToCode32(Const iValue : Integer) : Integer;
Begin
  Result := iValue And $7FFFFFFF;
End;
{$OVERFLOWCHECKS ON}
{$RANGECHECKS ON}


{$RANGECHECKS OFF}
{$OVERFLOWCHECKS OFF}
Function HashInteger64ToCode32(Const iValue : Int64) : Integer;
Begin
  Result := (iValue Shr 32) Xor (iValue And $7FFFFFFF);
End;
{$OVERFLOWCHECKS ON}
{$RANGECHECKS ON}


End.
