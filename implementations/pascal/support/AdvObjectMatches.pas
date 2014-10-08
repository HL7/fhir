Unit AdvObjectMatches;

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
  MathSupport, StringSupport, MemorySupport,
  AdvObjects, AdvItems, AdvFilers;


Type
  TAdvObjectMatchKey = TAdvObject;
  TAdvObjectMatchValue = TAdvObject;

  TAdvObjectMatchItem = Record
    Key : TAdvObjectMatchKey;
    Value : TAdvObjectMatchValue;
  End;

  PAdvObjectMatchItem = ^TAdvObjectMatchItem;

  TAdvObjectMatchItemArray = Array[0..(MaxInt Div SizeOf(TAdvObjectMatchItem)) - 1] Of TAdvObjectMatchItem;
  PAdvObjectMatchItemArray = ^TAdvObjectMatchItemArray;

  TAdvObjectMatch = Class(TAdvItems)
    Private
      FMatchArray : PAdvObjectMatchItemArray;
      FDefaultKey : TAdvObjectMatchKey;
      FDefaultValue : TAdvObjectMatchValue;
      FForced : Boolean;
      FNominatedKeyClass : TAdvObjectClass;
      FNominatedValueClass : TAdvObjectClass;

      FKeyComparisonDelegate : TAdvItemsCompare;
      FValueComparisonDelegate : TAdvItemsCompare;

      Function GetKeyByIndex(iIndex: Integer): TAdvObjectMatchKey;
      Procedure SetKeyByIndex(iIndex: Integer; Const aKey : TAdvObjectMatchKey);

      Function GetValueByIndex(iIndex: Integer): TAdvObjectMatchValue;
      Procedure SetValueByIndex(iIndex: Integer; Const aValue: TAdvObjectMatchValue);

      Function GetMatchByIndex(iIndex : Integer) : TAdvObjectMatchItem;

      Function GetAsText : String;

    Protected
      Function GetItem(iIndex : Integer) : Pointer; Override;
      Procedure SetItem(iIndex : Integer; aValue: Pointer); Override;

      Procedure LoadItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure SaveItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure AssignItem(oItems : TAdvItems; iIndex : Integer); Override;

      Procedure InternalTruncate(iValue : Integer); Override;
      Procedure InternalResize(iValue : Integer); Override;
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Override;
      Procedure InternalEmpty(iIndex, iLength : Integer); Override;
      Procedure InternalInsert(iIndex : Integer); Overload; Override;
      Procedure InternalDelete(iIndex : Integer); Overload; Override;
      Procedure InternalExchange(iA, iB : Integer); Overload; Override;

      Function ValidateIndex(Const sMethod : String; iIndex : Integer): Boolean; Overload; Override;

      Function CapacityLimit : Integer; Override;

      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Overload; Override;

      Function CompareByKeyReference(pA, pB : Pointer): Integer;
      Function CompareByValueReference(pA, pB : Pointer): Integer;

      Function Find(Const aKey : TAdvObjectMatchKey; Const aValue: TAdvObjectMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil): Boolean; Overload;

      Function ValidateKey(Const sMethod : String; oObject : TAdvObject; Const sObject : String) : Boolean; Virtual;
      Function ValidateValue(Const sMethod : String; oObject : TAdvObject; Const sObject : String) : Boolean; Virtual;

      Function ItemKeyClass : TAdvObjectClass; Virtual;
      Function ItemValueClass : TAdvObjectClass; Virtual;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvObjectMatch; Overload;

      Function IndexByValue(Const aValue : TAdvObjectMatchValue) : Integer;
      Function IndexByKey(Const aKey : TAdvObjectMatchKey) : Integer;
      Function ExistsByValue(Const aValue : TAdvObjectMatchValue) : Boolean;
      Function ExistsByKey(Const aKey : TAdvObjectMatchKey) : Boolean;
      Function Add(Const aKey : TAdvObjectMatchKey; Const aValue : TAdvObjectMatchValue): Integer;
      Procedure Insert(iIndex : Integer; Const aKey : TAdvObjectMatchKey; Const aValue : TAdvObjectMatchValue);
      Procedure Delete(Const aKey : TAdvObjectMatchKey; Const aValue : TAdvObjectMatchValue);
      Function GetKeyByValue(Const aValue : TAdvObjectMatchValue) : TAdvObjectMatchKey;

      Function GetValueByKey(Const aKey : TAdvObjectMatchKey): TAdvObjectMatchValue;
      Procedure SetValueByKey(Const aKey: TAdvObjectMatchKey; Const aValue: TAdvObjectMatchValue);

      Procedure Merge(oMatch : TAdvObjectMatch);

      Procedure SortedByKey;
      Procedure SortedByValue;

      Function IsSortedByKey : Boolean;
      Function IsSortedByValue : Boolean;

      Property Forced : Boolean Read FForced Write FForced;
      Property DefaultKey : TAdvObjectMatchKey Read FDefaultKey Write FDefaultKey;
      Property DefaultValue : TAdvObjectMatchValue Read FDefaultValue Write FDefaultValue;
      Property KeyComparisonDelegate : TAdvItemsCompare Read FKeyComparisonDelegate Write FKeyComparisonDelegate;
      Property ValueComparisonDelegate : TAdvItemsCompare Read FValueComparisonDelegate Write FValueComparisonDelegate;
      Property MatchByIndex[iIndex : Integer] : TAdvObjectMatchItem Read GetMatchByIndex;
      Property KeyByIndex[iIndex : Integer] : TAdvObjectMatchKey Read GetKeyByIndex Write SetKeyByIndex;
      Property ValueByIndex[iIndex : Integer] : TAdvObjectMatchValue Read GetValueByIndex Write SetValueByIndex;
      Property Keys[iIndex : Integer] : TAdvObjectMatchKey Read GetKeyByIndex Write SetKeyByIndex;
      Property Values[iIndex : Integer] : TAdvObjectMatchValue Read GetValueByIndex Write SetValueByIndex;
      Property NominatedKeyClass : TAdvObjectClass Read FNominatedKeyClass Write FNominatedKeyClass;
      Property NominatedValueClass : TAdvObjectClass Read FNominatedValueClass Write FNominatedValueClass;
      Property AsText : String Read GetAsText;
  End;


Implementation


Uses
  AdvFactories;


Constructor TAdvObjectMatch.Create;
Begin
  Inherited;

  FKeyComparisonDelegate := CompareByKeyReference;
  FValueComparisonDelegate := CompareByValueReference;

  FNominatedKeyClass := ItemKeyClass;
  FNominatedValueClass := ItemValueClass;
End;


Destructor TAdvObjectMatch.Destroy;
Begin
  Inherited;
End;


Function TAdvObjectMatch.Link : TAdvObjectMatch;
Begin
  Result := TAdvObjectMatch(Inherited Link);
End;


Function TAdvObjectMatch.CompareByKeyReference(pA, pB: Pointer): Integer;
Begin
  Result := IntegerCompare(Integer(PAdvObjectMatchItem(pA)^.Key), Integer(PAdvObjectMatchItem(pB)^.Key));
End;  


Function TAdvObjectMatch.CompareByValueReference(pA, pB: Pointer): Integer;
Begin 
  Result := IntegerCompare(Integer(PAdvObjectMatchItem(pA)^.Value), Integer(PAdvObjectMatchItem(pB)^.Value));
End;  


Procedure TAdvObjectMatch.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineObject(FMatchArray^[iIndex].Key);
  oFiler['Value'].DefineObject(FMatchArray^[iIndex].Value);

  oFiler['Match'].DefineEnd;
End;  


Procedure TAdvObjectMatch.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Var
  oKey : TAdvObjectMatchKey;
  oValue : TAdvObjectMatchValue;
Begin 
  oKey := Nil;
  oValue := Nil;
  Try
    oFiler['Match'].DefineBegin;

    oFiler['Key'].DefineObject(oKey);
    oFiler['Value'].DefineObject(oValue);

    oFiler['Match'].DefineEnd;

    Add(oKey.Link, oValue.Link);
  Finally
    oKey.Free;
    oValue.Free;
  End;  
End;  


Procedure TAdvObjectMatch.AssignItem(oItems : TAdvItems; iIndex: Integer);
Begin 
  Inherited;

  FMatchArray^[iIndex].Key := TAdvObjectMatch(oItems).FMatchArray^[iIndex].Key.Clone;
  FMatchArray^[iIndex].Value := TAdvObjectMatch(oItems).FMatchArray^[iIndex].Value.Clone;  
End;  


Procedure TAdvObjectMatch.InternalEmpty(iIndex, iLength : Integer);
Begin 
  Inherited;

  MemoryZero(Pointer(NativeUInt(FMatchArray) + NativeUInt(iIndex * SizeOf(TAdvObjectMatchItem))), (iLength * SizeOf(TAdvObjectMatchItem)));
End;


Procedure TAdvObjectMatch.InternalTruncate(iValue : Integer);
Var
  iLoop : Integer;
  oKey  : TAdvObjectMatchKey;
  oValue : TAdvObjectMatchValue;
Begin
  Inherited;

  For iLoop := iValue To Count - 1 Do
  Begin
    oKey := FMatchArray^[iLoop].Key;
    oValue := FMatchArray^[iLoop].Value;

    FMatchArray^[iLoop].Key := Nil;
    FMatchArray^[iLoop].Value := Nil;

    oKey.Free;
    oValue.Free;
  End;
End;


Procedure TAdvObjectMatch.InternalResize(iValue : Integer);
Begin
  Inherited;

  MemoryResize(FMatchArray, Capacity * SizeOf(TAdvObjectMatchItem), iValue * SizeOf(TAdvObjectMatchItem));
End;


Procedure TAdvObjectMatch.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
  MemoryMove(@FMatchArray^[iSource], @FMatchArray^[iTarget], iCount * SizeOf(TAdvObjectMatchItem));
End;  


Function TAdvObjectMatch.IndexByKey(Const aKey: TAdvObjectMatchKey): Integer;
Begin
  If Not Find(aKey, Nil, Result, FKeyComparisonDelegate) Then
    Result := -1;
End;


Function TAdvObjectMatch.IndexByValue(Const aValue: TAdvObjectMatchValue): Integer;
Begin
  If Not Find(Nil, aValue, Result, FValueComparisonDelegate) Then
    Result := -1;
End;


Function TAdvObjectMatch.ExistsByKey(Const aKey: TAdvObjectMatchKey): Boolean;
Begin
  Result := ExistsByIndex(IndexByKey(aKey));
End;


Function TAdvObjectMatch.ExistsByValue(Const aValue: TAdvObjectMatchValue): Boolean;
Begin
  Result := ExistsByIndex(IndexByValue(aValue));
End;


Function TAdvObjectMatch.Add(Const aKey : TAdvObjectMatchKey; Const aValue : TAdvObjectMatchValue) : Integer;
Begin 
  Assert(ValidateKey('Add', aKey, 'aKey'));
  Assert(ValidateValue('Add', aValue, 'aValue'));

  Result := -1;

  If Not IsAllowDuplicates And Find(aKey, aValue, Result) Then
  Begin 
    aKey.Free;
    aValue.Free;

    If IsPreventDuplicates Then
      Error('Add', StringFormat('Item already exists in match (%x=%x)', [Integer(aKey), Integer(aValue)]));
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


Procedure TAdvObjectMatch.Insert(iIndex : Integer; Const aKey : TAdvObjectMatchKey; Const aValue : TAdvObjectMatchValue);
Begin
  Assert(ValidateKey('Insert', aKey, 'aKey'));
  Assert(ValidateValue('Insert', aValue, 'aValue'));

  InternalInsert(iIndex);

  FMatchArray^[iIndex].Key := aKey;
  FMatchArray^[iIndex].Value := aValue;
End;


Procedure TAdvObjectMatch.InternalInsert(iIndex : Integer);
Begin
  Inherited;

  Pointer(FMatchArray^[iIndex].Key) := Nil;
  Pointer(FMatchArray^[iIndex].Value) := Nil;
End;


Procedure TAdvObjectMatch.InternalDelete(iIndex : Integer);
Begin
  Inherited;

  FMatchArray^[iIndex].Key.Free;
  FMatchArray^[iIndex].Key := Nil;

  FMatchArray^[iIndex].Value.Free;
  FMatchArray^[iIndex].Value := Nil;
End;


Procedure TAdvObjectMatch.InternalExchange(iA, iB: Integer);
Var
  aTemp : TAdvObjectMatchItem;
  pA : Pointer;
  pB : Pointer;
Begin
  pA := @FMatchArray^[iA];
  pB := @FMatchArray^[iB];

  aTemp := PAdvObjectMatchItem(pA)^;
  PAdvObjectMatchItem(pA)^ := PAdvObjectMatchItem(pB)^;
  PAdvObjectMatchItem(pB)^ := aTemp;
End;


Procedure TAdvObjectMatch.Delete(Const aKey: TAdvObjectMatchKey; Const aValue: TAdvObjectMatchValue);
Var
  iIndex : Integer;
Begin
  If Not Find(aKey, aValue, iIndex) Then
    Error('Delete', 'Key/Value pair could not be found in the match.');

  DeleteByIndex(iIndex);
End;


Function TAdvObjectMatch.GetItem(iIndex : Integer): Pointer;
Begin
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FMatchArray^[iIndex];
End;  


Procedure TAdvObjectMatch.SetItem(iIndex: Integer; aValue: Pointer);
Begin 
  Assert(ValidateIndex('SetItem', iIndex));

  FMatchArray^[iIndex] := PAdvObjectMatchItem(aValue)^;
End;  


Function TAdvObjectMatch.GetKeyByIndex(iIndex : Integer): TAdvObjectMatchKey;
Begin 
  Assert(ValidateIndex('GetKeyByIndex', iIndex));

  Result := FMatchArray^[iIndex].Key;
End;  


Procedure TAdvObjectMatch.SetKeyByIndex(iIndex : Integer; Const aKey : TAdvObjectMatchKey);
Begin 
  Assert(ValidateIndex('SetKeyByIndex', iIndex));
  Assert(ValidateKey('SetKeyByIndex', aKey, 'aKey'));

  FMatchArray^[iIndex].Key.Free;
  FMatchArray^[iIndex].Key := aKey;
End;  


Function TAdvObjectMatch.GetValueByIndex(iIndex : Integer): TAdvObjectMatchValue;
Begin 
  Assert(ValidateIndex('GetValueByIndex', iIndex));

  Result := FMatchArray^[iIndex].Value;
End;  


Procedure TAdvObjectMatch.SetValueByIndex(iIndex : Integer; Const aValue: TAdvObjectMatchValue);
Begin 
  Assert(ValidateIndex('SetValueByIndex', iIndex));
  Assert(ValidateValue('SetValueByIndex', aValue, 'aValue'));

  FMatchArray^[iIndex].Value.Free;
  FMatchArray^[iIndex].Value := aValue;
End;


Function TAdvObjectMatch.GetValueByKey(Const aKey : TAdvObjectMatchKey): TAdvObjectMatchValue;
Var
  iIndex : Integer;
Begin
  Assert(ValidateKey('GetValueByKey', aKey, 'aKey'));

  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else
  Begin 
    Result := FDefaultValue;

    If Not FForced Then
      Error('GetValueByKey', 'Unable to get the value for the specified key.');
  End;  
End;  


Procedure TAdvObjectMatch.SetValueByKey(Const aKey : TAdvObjectMatchKey; Const aValue: TAdvObjectMatchValue);
Var
  iIndex : Integer;
Begin 
  Assert(ValidateKey('SetValueByKey', aKey, 'aKey'));
  Assert(ValidateValue('SetValueByKey', aValue, 'aValue'));

  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    ValueByIndex[iIndex] := aValue
  Else If FForced Then
    Add(aKey.Link, aValue)
  Else
  Begin 
    aValue.Free;
    Error('SetValueByKey', 'Unable to set the value for the specified key.');
  End;  
End;


Function TAdvObjectMatch.GetAsText : String;
Var
  iLoop : Integer;
Begin 
  Result := '';
  For iLoop := 0 To Count - 1 Do
    Result := Result + KeyByIndex[iLoop].ClassName + '=' + ValueByIndex[iLoop].ClassName + #13#10;
End;  


Procedure TAdvObjectMatch.Merge(oMatch: TAdvObjectMatch);
Var
  iLoop : Integer;
Begin 
  For iLoop := 0 To oMatch.Count - 1 Do
    Add(oMatch.KeyByIndex[iLoop].Link, oMatch.ValueByIndex[iLoop].Link);
End;  


Function TAdvObjectMatch.CapacityLimit : Integer;
Begin 
  Result := High(TAdvObjectMatchItemArray);
End;  


Procedure TAdvObjectMatch.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin
  aCompare := CompareByKeyReference;
End;  


Function TAdvObjectMatch.Find(Const aKey : TAdvObjectMatchKey; Const aValue : TAdvObjectMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare) : Boolean;
Var
  aItem : TAdvObjectMatchItem;
Begin
  Assert(ValidateKey('Find', aKey, 'aKey'));
  Assert(ValidateValue('Find', aValue, 'aValue'));

  aItem.Key := aKey;
  aItem.Value := aValue;

  Result := Find(@aItem, iIndex, aCompare);
End;


Function TAdvObjectMatch.IsSortedByKey : Boolean;
Begin 
  Result := IsSortedBy(FKeyComparisonDelegate);
End;


Function TAdvObjectMatch.IsSortedByValue : Boolean;
Begin
  Result := IsSortedBy(FValueComparisonDelegate);
End;  


Function TAdvObjectMatch.ValidateIndex(Const sMethod : String; iIndex: Integer): Boolean;
Begin 
  Inherited ValidateIndex(sMethod, iIndex);

  ValidateKey(sMethod, FMatchArray^[iIndex].Key, 'FMatchArray^[' + IntegerToString(iIndex) + '].Key');
  ValidateValue(sMethod, FMatchArray^[iIndex].Value, 'FMatchArray^[' + IntegerToString(iIndex) + '].Value');

  Result := True;
End;  


Function TAdvObjectMatch.ValidateKey(Const sMethod : String; oObject : TAdvObject; Const sObject : String) : Boolean;
Begin 
  If Assigned(oObject) Then
    Invariants(sMethod, oObject, FNominatedKeyClass, sObject);

  Result := True;
End;  


Function TAdvObjectMatch.ValidateValue(Const sMethod : String; oObject : TAdvObject; Const sObject : String) : Boolean;
Begin 
  If Assigned(oObject) Then
    Invariants(sMethod, oObject, FNominatedValueClass, sObject);

  Result := True;
End;


Function TAdvObjectMatch.ItemKeyClass : TAdvObjectClass;
Begin
  Result := TAdvObject;
End;


Function TAdvObjectMatch.ItemValueClass : TAdvObjectClass;
Begin
  Result := TAdvObject;
End;


Procedure TAdvObjectMatch.SortedByKey;
Begin
  SortedBy(FKeyComparisonDelegate);
End;


Procedure TAdvObjectMatch.SortedByValue;
Begin
  SortedBy(FValueComparisonDelegate);
End;


Function TAdvObjectMatch.GetKeyByValue(Const aValue: TAdvObjectMatchValue): TAdvObjectMatchKey;
Var
  iIndex : Integer;
Begin
  iIndex := IndexByValue(aValue);

  If ExistsByIndex(iIndex) Then
    Result := KeyByIndex[iIndex]
  Else
    Result := FDefaultKey;
End;


Function TAdvObjectMatch.GetMatchByIndex(iIndex : Integer) : TAdvObjectMatchItem;
Begin
  Assert(ValidateIndex('GetMatchByIndex', iIndex));

  Result := FMatchArray^[iIndex];
End;


Initialization
  Factory.RegisterClass(TAdvObjectMatch);
End. // AdvObjectMatches //
