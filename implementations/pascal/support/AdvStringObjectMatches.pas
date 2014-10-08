Unit AdvStringObjectMatches;

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
  MemorySupport, StringSupport, MathSupport,
  AdvObjects, AdvItems, AdvFilers;


Type
  TAdvStringObjectMatchKey = String;
  TAdvStringObjectMatchValue = TAdvObject;

  TAdvStringObjectMatchItem = Record
    Key   : TAdvStringObjectMatchKey;
    Value : TAdvStringObjectMatchValue;
  End;

  PAdvStringObjectMatchItem = ^TAdvStringObjectMatchItem;

  TAdvStringObjectMatchItemArray = Array[0..(MaxInt Div SizeOf(TAdvStringObjectMatchItem)) - 1] Of TAdvStringObjectMatchItem;
  PAdvStringObjectMatchItemArray = ^TAdvStringObjectMatchItemArray;

  TAdvStringCompareCallback = Function (Const sA, sB : String) : Integer;

  TAdvStringObjectMatch = Class(TAdvItems)
    Private
      FMatchArray : PAdvStringObjectMatchItemArray;
      FDefaultKey : TAdvStringObjectMatchKey;
      FDefaultValue : TAdvStringObjectMatchValue;
      FCompareKey : TAdvStringCompareCallback;
      FNominatedValueClass : TAdvObjectClass;
      FSymbol : String;
      FForced : Boolean;

      Function GetKeyByIndex(iIndex: Integer): TAdvStringObjectMatchKey;
      Procedure SetKeyByIndex(iIndex: Integer; Const aKey : TAdvStringObjectMatchKey);

      Function GetValueByIndex(iIndex: Integer): TAdvStringObjectMatchValue;
      Procedure SetValueByIndex(iIndex: Integer; Const aValue: TAdvStringObjectMatchValue);

      Function GetMatch(Const aKey : TAdvStringObjectMatchKey): TAdvStringObjectMatchValue;
      Procedure SetMatch(Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue);

      Function GetMatchByIndex(iIndex: Integer): TAdvStringObjectMatchItem;

      Function GetAsText: String;
      Procedure SetAsText(Const Value: String);

      Function GetSensitive: Boolean;
      Procedure SetSensitive(Const Value: Boolean);

    Protected
      Function GetItem(iIndex : Integer) : Pointer; Override;
      Procedure SetItem(iIndex : Integer; pValue: Pointer); Override;

      Procedure LoadItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure SaveItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure AssignItem(oItems : TAdvItems; iIndex : Integer); Override;

      Function ValidateIndex(Const sMethod: String; iIndex: Integer): Boolean; Override;
      Function ValidateItem(Const sMethod: String; oObject: TAdvObject; Const sObject: String): Boolean; Virtual;

      Procedure InternalTruncate(iValue : Integer); Override;
      Procedure InternalResize(iValue : Integer); Override;
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Override;
      Procedure InternalEmpty(iIndex, iLength : Integer); Override;
      Procedure InternalInsert(iIndex : Integer); Override;
      Procedure InternalDelete(iIndex : Integer); Override;
      Procedure InternalExchange(iA, iB : Integer); Override;

      Function CompareByKey(pA, pB : Pointer): Integer; Virtual;
      Function CompareByValue(pA, pB : Pointer): Integer; Virtual;

      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Override;

      Function Find(Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean;

      Function CapacityLimit : Integer; Override;

      Function ItemClass : TAdvObjectClass; Virtual;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvStringObjectMatch; Overload;

      Function IndexByKey(Const aKey : TAdvStringObjectMatchKey) : Integer;
      Function IndexByValue(Const aValue : TAdvStringObjectMatchValue) : Integer;

      Function ExistsByKey(Const aKey : TAdvStringObjectMatchKey) : Boolean;
      Function ExistsByValue(Const aValue : TAdvStringObjectMatchValue) : Boolean;

      Procedure AddAll(oSourceStringObjectMatch : TAdvStringObjectMatch);

      Function Add(Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue): Integer; Overload;
      Procedure Insert(iIndex : Integer; Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue); Overload;
      Function FindByKey(Const aKey : TAdvStringObjectMatchKey; Out iIndex : Integer) : Boolean; Overload;
      Function FindByValue(Const aValue : TAdvStringObjectMatchValue; Out iIndex : Integer) : Boolean; Overload;

      Procedure SortedByKey;
      Procedure SortedByValue;

      Function IsSortedByKey : Boolean;
      Function IsSortedByValue : Boolean;

      Function GetKeyByValue(Const aValue : TAdvStringObjectMatchValue) : TAdvStringObjectMatchKey;
      Function GetValueByKey(Const aKey : TAdvStringObjectMatchKey) : TAdvStringObjectMatchValue;
      Procedure SetValueByKey(Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue);

      Procedure DeleteByKey(Const aKey : TAdvStringObjectMatchKey);
      Procedure DeleteByValue(Const aValue : TAdvStringObjectMatchValue);

      Property Matches[Const aKey : TAdvStringObjectMatchKey] : TAdvStringObjectMatchValue Read GetMatch Write SetMatch; Default;
      Property Forced : Boolean Read FForced Write FForced;
      Property DefaultKey : TAdvStringObjectMatchKey Read FDefaultKey Write FDefaultKey;
      Property DefaultValue : TAdvStringObjectMatchValue Read FDefaultValue Write FDefaultValue;
      Property AsText : String Read GetAsText Write SetAsText;
      Property Symbol : String Read FSymbol Write FSymbol;
      Property Sensitive : Boolean Read GetSensitive Write SetSensitive;
      Property NominatedValueClass : TAdvObjectClass Read FNominatedValueClass Write FNominatedValueClass;
      Property MatchByIndex[iIndex : Integer] : TAdvStringObjectMatchItem Read GetMatchByIndex;
      Property KeyByIndex[iIndex : Integer] : TAdvStringObjectMatchKey Read GetKeyByIndex Write SetKeyByIndex;
      Property Keys[iIndex : Integer] : TAdvStringObjectMatchKey Read GetKeyByIndex Write SetKeyByIndex;
      Property ValueByIndex[iIndex : Integer] : TAdvStringObjectMatchValue Read GetValueByIndex Write SetValueByIndex;
      Property Values[iIndex : Integer] : TAdvStringObjectMatchValue Read GetValueByIndex Write SetValueByIndex;
  End;


Implementation


Constructor TAdvStringObjectMatch.Create;
Begin
  Inherited;

  FNominatedValueClass := ItemClass;
End;


Destructor TAdvStringObjectMatch.Destroy;
Begin
  Inherited;
End;


Procedure TAdvStringObjectMatch.AssignItem(oItems : TAdvItems; iIndex: Integer);
Begin
  FMatchArray^[iIndex].Key := TAdvStringObjectMatch(oItems).FMatchArray^[iIndex].Key;
  FMatchArray^[iIndex].Value := TAdvStringObjectMatch(oItems).FMatchArray^[iIndex].Value.Clone;
End;


Procedure TAdvStringObjectMatch.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineString(FMatchArray^[iIndex].Key);
  oFiler['Value'].DefineObject(FMatchArray^[iIndex].Value);

  oFiler['Match'].DefineEnd;
End;


Procedure TAdvStringObjectMatch.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Var
  sKey   : TAdvStringObjectMatchKey;
  oValue : TAdvStringObjectMatchValue;
Begin 
  oValue := Nil;
  Try
    oFiler['Match'].DefineBegin;

    oFiler['Key'].DefineString(sKey);
    oFiler['Value'].DefineObject(oValue);

    oFiler['Match'].DefineEnd;

    Add(sKey, oValue.Link);
  Finally
    oValue.Free;
  End;  
End;  


Procedure TAdvStringObjectMatch.InternalEmpty(iIndex, iLength : Integer);
Begin 
  Inherited;

  MemoryZero(Pointer(NativeUInt(FMatchArray) + NativeUInt(iIndex * SizeOf(TAdvStringObjectMatchItem))), (iLength * SizeOf(TAdvStringObjectMatchItem)));
End;  


Procedure TAdvStringObjectMatch.InternalTruncate(iValue : Integer);
Var
  iLoop : Integer;
  oValue : TAdvObject;
Begin
  Inherited;

  // finalize the strings that will be truncated.
  If iValue < Count Then
    Finalize(FMatchArray^[iValue], Count - iValue);

  For iLoop := iValue To Count - 1 Do
  Begin
    oValue := FMatchArray^[iLoop].Value;
    FMatchArray^[iLoop].Value := Nil;
    oValue.Free;
  End;
End;


Procedure TAdvStringObjectMatch.InternalResize(iValue : Integer);
Begin
  Inherited;

  MemoryResize(FMatchArray, Capacity * SizeOf(TAdvStringObjectMatchItem), iValue * SizeOf(TAdvStringObjectMatchItem));
End;


Procedure TAdvStringObjectMatch.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
  MemoryMove(@FMatchArray^[iSource], @FMatchArray^[iTarget], iCount * SizeOf(TAdvStringObjectMatchItem));
End;  


Function TAdvStringObjectMatch.CompareByKey(pA, pB: Pointer): Integer;
Begin
  Result := FCompareKey(PAdvStringObjectMatchItem(pA)^.Key, PAdvStringObjectMatchItem(pB)^.Key);
End;


Function TAdvStringObjectMatch.CompareByValue(pA, pB: Pointer): Integer;
Begin
  Result := IntegerCompare(Integer(PAdvStringObjectMatchItem(pA)^.Value), Integer(PAdvStringObjectMatchItem(pB)^.Value));
End;


Function TAdvStringObjectMatch.Find(Const aKey: TAdvStringObjectMatchKey; Const aValue: TAdvStringObjectMatchValue; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Var
  aItem : TAdvStringObjectMatchItem;
Begin
  aItem.Key := aKey;
  aItem.Value := aValue;

  Result := Inherited Find(@aItem, iIndex, aCompare);
End;


Function TAdvStringObjectMatch.FindByKey(Const aKey: TAdvStringObjectMatchKey; Out iIndex: Integer): Boolean;
Begin
  Result := Find(aKey, Nil, iIndex, CompareByKey);
End;


Function TAdvStringObjectMatch.FindByValue(Const aValue: TAdvStringObjectMatchValue; Out iIndex: Integer) : Boolean;
Begin
  Result := Find('', aValue, iIndex, CompareByValue);
End;


Function TAdvStringObjectMatch.IndexByKey(Const aKey : TAdvStringObjectMatchKey) : Integer;
Begin
  If Not Find(aKey, Nil, Result, CompareByKey) Then
    Result := -1;
End;


Function TAdvStringObjectMatch.IndexByValue(Const aValue : TAdvStringObjectMatchValue) : Integer;
Begin
  If Not Find('', aValue, Result, CompareByValue) Then
    Result := -1;
End;


Function TAdvStringObjectMatch.Add(Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue) : Integer;
Begin 
  Assert(ValidateItem('Add', aValue, 'aValue'));

  Result := -1;

  If Not IsAllowDuplicates And Find(aKey, aValue, Result) Then
  Begin 
    aValue.Free; // free ignored object

    If IsPreventDuplicates Then
      Error('Add', StringFormat('Item already exists in list (%s=%x)', [aKey, Integer(aValue)]));
  End
  Else
  Begin
    If Not IsSorted Then
      Result := Count
    Else If (Result < 0) Then
      Find(aKey, aValue, Result);

    Insert(Result, aKey, aValue);
  End;  
End;  


Procedure TAdvStringObjectMatch.Insert(iIndex : Integer; Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue);
Begin 
  Assert(ValidateItem('Insert', aValue, 'aValue'));

  InternalInsert(iIndex);

  FMatchArray^[iIndex].Key := aKey;
  FMatchArray^[iIndex].Value := aValue;
End;  


Procedure TAdvStringObjectMatch.InternalInsert(iIndex : Integer);
Begin 
  Inherited;

  Pointer(FMatchArray^[iIndex].Key) := Nil;
  Pointer(FMatchArray^[iIndex].Value) := Nil;
End;  


Procedure TAdvStringObjectMatch.InternalDelete(iIndex : Integer);
Begin 
  Inherited;

  Finalize(FMatchArray^[iIndex].Key);
  FMatchArray^[iIndex].Value.Free;
  FMatchArray^[iIndex].Value := Nil;
End;  


Procedure TAdvStringObjectMatch.InternalExchange(iA, iB: Integer);
Var
  aTemp : TAdvStringObjectMatchItem;
  pA    : PAdvStringObjectMatchItem;
  pB    : PAdvStringObjectMatchItem;
Begin 
  pA := @FMatchArray^[iA];
  pB := @FMatchArray^[iB];

  aTemp := pA^;
  pA^ := pB^;
  pB^ := aTemp;
End;  


Function TAdvStringObjectMatch.GetItem(iIndex : Integer) : Pointer;
Begin 
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FMatchArray^[iIndex];
End;  


Procedure TAdvStringObjectMatch.SetItem(iIndex: Integer; pValue: Pointer);
Begin 
  Assert(ValidateIndex('SetItem', iIndex));

  FMatchArray^[iIndex] := PAdvStringObjectMatchItem(pValue)^;
End;  


Function TAdvStringObjectMatch.GetKeyByIndex(iIndex : Integer): TAdvStringObjectMatchKey;
Begin
  Assert(ValidateIndex('GetKeyByIndex', iIndex));

  Result := FMatchArray^[iIndex].Key;
End;  


Procedure TAdvStringObjectMatch.SetKeyByIndex(iIndex : Integer; Const aKey : TAdvStringObjectMatchKey);
Begin 
  Assert(ValidateIndex('SetKeyByIndex', iIndex));

  FMatchArray^[iIndex].Key := aKey;
End;  


Function TAdvStringObjectMatch.GetValueByIndex(iIndex : Integer): TAdvStringObjectMatchValue;
Begin
  Assert(ValidateIndex('GetValueByIndex', iIndex));

  Result := FMatchArray^[iIndex].Value;
End;


Procedure TAdvStringObjectMatch.SetValueByIndex(iIndex : Integer; Const aValue: TAdvStringObjectMatchValue);
Begin
  Assert(ValidateIndex('SetValueByIndex', iIndex));
  Assert(ValidateItem('SetValueByIndex', aValue, 'aValue'));

  FMatchArray^[iIndex].Value.Free;
  FMatchArray^[iIndex].Value := aValue;
End;


Procedure TAdvStringObjectMatch.SetValueByKey(Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue);
Var
  iIndex : Integer;
Begin
  Assert(ValidateItem('SetValueByKey', aValue, 'aValue'));

  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    ValueByIndex[iIndex] := aValue
  Else If FForced Then
    Add(aKey, aValue)
  Else
    Error('SetValueByKey', StringFormat('Unable to set the value for the specified key ''%s''.', [aKey]));
End;


Function TAdvStringObjectMatch.GetSensitive : Boolean;
Var
  aCompare : TAdvStringCompareCallback;
Begin
  aCompare := StringCompareSensitive;

  Result := @FCompareKey = @aCompare;
End;


Procedure TAdvStringObjectMatch.SetSensitive(Const Value: Boolean);
Begin
  If Value Then
    FCompareKey := StringCompareSensitive
  Else
    FCompareKey := StringCompareInsensitive;
End;


Function TAdvStringObjectMatch.GetAsText: String;
Var
  iItem, iSymbol : Integer;
  iLoop, iSize : Integer;
  Ptr : PChar;
  sItem : String;
Begin 
  iSymbol := Length(FSymbol);

  iSize := 0;
  For iLoop := 0 To Count - 1 Do
    Inc(iSize, Length(FMatchArray^[iLoop].Key) + iSymbol);

  SetLength(Result, iSize);

  Ptr := PChar(Result);
  For iLoop := 0 To Count - 1 Do
  Begin 
    sItem := FMatchArray^[iLoop].Key;
    iItem := Length(sItem);

    If (iItem > 0) Then
    Begin 
      MemoryMove(Pointer(sItem), Ptr, iItem);
      Inc(Ptr, iItem);

      MemoryMove(Pointer(FSymbol), Ptr, iSymbol);
      Inc(Ptr, iSymbol);
    End;  
  End;  
End;


Procedure TAdvStringObjectMatch.SetAsText(Const Value: String);
Var
  sTemp : String;
  iPos : Integer;
  SymbolLength : Integer;
Begin 
  Clear;

  sTemp := Value;
  iPos := Pos(FSymbol, sTemp);
  SymbolLength := Length(FSymbol);

  While (iPos > 0) Do
  Begin 
    Add(System.Copy(sTemp, 1, iPos - 1), Nil);
    System.Delete(sTemp, 1, iPos + SymbolLength - 1);

    iPos := Pos(FSymbol, sTemp);
  End;  

  If (sTemp <> '') Then
    Add(sTemp, Nil);
End;  


Function TAdvStringObjectMatch.CapacityLimit : Integer;
Begin 
  Result := High(TAdvStringObjectMatchItemArray);
End;  


Procedure TAdvStringObjectMatch.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin 
  aCompare := CompareByKey;
  FCompareKey := StringCompareInsensitive;
End;


Function TAdvStringObjectMatch.ItemClass : TAdvObjectClass;
Begin 
  Result := TAdvObject;
End;  


Function TAdvStringObjectMatch.ValidateIndex(Const sMethod : String; iIndex: Integer): Boolean;
Begin 
  Inherited ValidateIndex(sMethod, iIndex);

  ValidateItem(sMethod, FMatchArray^[iIndex].Value, 'FMatchArray^[' + IntegerToString(iIndex) + '].Value');

  Result := True;
End;  


Function TAdvStringObjectMatch.ValidateItem(Const sMethod : String; oObject : TAdvObject; Const sObject : String) : Boolean;
Begin 
  If Assigned(oObject) Then
    Invariants(sMethod, oObject, NominatedValueClass, sObject);

  Result := True;
End;  


Function TAdvStringObjectMatch.ExistsByKey(Const aKey : TAdvStringObjectMatchKey) : Boolean;
Begin
  Result := IndexByKey(aKey) >= 0;
End;


Function TAdvStringObjectMatch.ExistsByValue(Const aValue : TAdvStringObjectMatchValue) : Boolean;
Begin
  Result := IndexByValue(aValue) >= 0;
End;


Procedure TAdvStringObjectMatch.SortedByKey;
Begin 
  SortedBy(CompareByKey);
End;  


Procedure TAdvStringObjectMatch.SortedByValue;
Begin 
  SortedBy(CompareByValue);
End;  


Function TAdvStringObjectMatch.IsSortedByKey : Boolean;
Begin 
  Result := IsSortedBy(CompareByKey);
End;  


Function TAdvStringObjectMatch.IsSortedByValue : Boolean;
Begin 
  Result := IsSortedBy(CompareByValue);
End;  


Function TAdvStringObjectMatch.GetKeyByValue(Const aValue: TAdvStringObjectMatchValue): TAdvStringObjectMatchKey;
Var
  iIndex : Integer;
Begin
  If FindByValue(aValue, iIndex) Then
  Begin
    Result := KeyByIndex[iIndex]
  End
  Else If FForced Then
  Begin
    Result := DefaultKey;
  End
  Else
  Begin
    Error('GetKeyByValue', StringFormat('Unable to get the key for the specified value ''%d''.', [Integer(aValue)]));

    Result := '';
  End;
End;


Function TAdvStringObjectMatch.GetValueByKey(Const aKey: TAdvStringObjectMatchKey): TAdvStringObjectMatchValue;
Var
  iIndex : Integer;
Begin
  If FindByKey(aKey, iIndex) Then
  Begin
    Result := ValueByIndex[iIndex]
  End
  Else If FForced Then
  Begin
    Result := DefaultValue;
  End
  Else
  Begin
    Error('GetValueByKey', StringFormat('Unable to get the value for the specified key ''%s''.', [aKey]));

    Result := Nil;
  End;
End;


Procedure TAdvStringObjectMatch.AddAll(oSourceStringObjectMatch: TAdvStringObjectMatch);
Var
  iIndex : Integer;
Begin
  Assert(Condition(oSourceStringObjectMatch <> Self, 'AddAll', 'Cannot addall items from a list to itself.'));

  Capacity := IntegerMax(Count + oSourceStringObjectMatch.Count, Capacity);
  For iIndex := 0 To oSourceStringObjectMatch.Count - 1 Do
    Add(oSourceStringObjectMatch.KeyByIndex[iIndex], oSourceStringObjectMatch.ValueByIndex[iIndex].Link);
End;


Function TAdvStringObjectMatch.Link: TAdvStringObjectMatch;
Begin
  Result := TAdvStringObjectMatch(Inherited Link);
End;


Function TAdvStringObjectMatch.GetMatchByIndex(iIndex: Integer): TAdvStringObjectMatchItem;
Begin
  Assert(ValidateIndex('GetMatchByIndex', iIndex));

  Result := FMatchArray^[iIndex];
End;


Function TAdvStringObjectMatch.GetMatch(Const aKey : TAdvStringObjectMatchKey): TAdvStringObjectMatchValue;
Var
  iIndex : Integer;
Begin
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else If FForced Then
    Result := DefaultValue
  Else
  Begin
    Error('GetMatch', StringFormat('Unable to get the value for the specified key ''%s''.', [aKey]));
    Result := Nil;
  End;
End;


Procedure TAdvStringObjectMatch.SetMatch(Const aKey : TAdvStringObjectMatchKey; Const aValue : TAdvStringObjectMatchValue);
Var
  iIndex : Integer;
Begin
  Assert(ValidateItem('SetMatch', aValue, 'aValue'));

  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    ValueByIndex[iIndex] := aValue
  Else If FForced Then
    Add(aKey, aValue)
  Else
    Error('SetMatch', StringFormat('Unable to set the value for the specified key ''%s''.', [aKey]));
End;

Procedure TAdvStringObjectMatch.DeleteByKey(Const aKey: TAdvStringObjectMatchKey);
Begin
  DeleteByIndex(IndexByKey(aKey));
End;


Procedure TAdvStringObjectMatch.DeleteByValue(Const aValue: TAdvStringObjectMatchValue);
Begin
  DeleteByIndex(IndexByValue(aValue));
End;




End. // AdvStringObjectMatches //
