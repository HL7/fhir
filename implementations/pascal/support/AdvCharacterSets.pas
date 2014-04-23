Unit AdvCharacterSets;

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
