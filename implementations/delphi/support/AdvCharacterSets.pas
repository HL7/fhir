Unit AdvCharacterSets;


{! 8 !}


// TODO: handle non-printable characters in AsText.


Interface


Uses
  SysUtils,
  StringSupport,
  AdvOrdinalSets, AdvStringLists;


Type
  TAdvCharacterSet = Class(TAdvOrdinalSet)
    Private
      FDataSet : TCharSet;

      Function GetAsText: String;
      Procedure SetAsText(Const Value: String);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure AddRange(Const aFromValue, aToValue : Char);
      Procedure AddValue(Const aValue : Char);
      Function ContainsValue(Const aValue : Char) : Boolean;

      Property AsText : String Read GetAsText Write SetAsText;
      Property Data : TCharSet Read FDataSet Write FDataSet;
  End;


Implementation


Constructor TAdvCharacterSet.Create;
Begin
  Inherited;

  Owns := False;
  Parts := @FDataSet;
  Size := SizeOf(FDataSet);
End;


Destructor TAdvCharacterSet.Destroy;
Begin
  Inherited;
End;


Procedure TAdvCharacterSet.AddRange(Const aFromValue, aToValue: Char);
Begin
  FDataSet := FDataSet + [aFromValue..aToValue];
End;


Procedure TAdvCharacterSet.AddValue(Const aValue: Char);
Begin
  FDataSet := FDataSet + [aValue];
End;


Function TAdvCharacterSet.ContainsValue(Const aValue: Char): Boolean;
Begin
  Result := CharInSet(aValue, FDataSet);
End;


Function TAdvCharacterSet.GetAsText : String;
Var
  iLoop : Integer;
  iStart : Integer;
Begin
  iLoop := 0;
  Result := '';

  While (iLoop < Count) Do
  Begin
    iStart := iLoop;
    While (iLoop < Count) And Checked(iLoop) Do
      Inc(iLoop);

    If iLoop = iStart + 1 Then
      StringAppend(Result, Char(iStart), ', ')
    Else If iLoop > iStart + 1 Then
      StringAppend(Result, Char(iStart) + '-' + Char(iLoop - 1), ', ');

    Inc(iLoop);
  End;  
End;  


Procedure TAdvCharacterSet.SetAsText(Const Value: String);
Var
  oStrings : TAdvStringList;
  iLoop    : Integer;
  sField   : String;
  sLeft    : String;
  sRight   : String;
Begin 
  Fill(False);

  oStrings := TAdvStringList.Create;
  Try
    oStrings.Symbol := ',';

    oStrings.AsText := Value;

    For iLoop := 0 To oStrings.Count - 1 Do
    Begin 
      sField := StringTrimWhitespace(oStrings[iLoop]);

      If sField <> '' Then
      Begin 
        If Length(sField) = 1 Then
          Check(Ord(sField[1]))
        Else If StringSplit(sField, '-', sLeft, sRight) And (Length(sLeft) = 1) And (Length(sRight) = 1) Then
          CheckRange(Ord(sLeft[1]), Ord(sRight[1]));
      End;  
    End;  
  Finally
    oStrings.Free;
  End;   
End;


End. // AdvCharacterSets //
