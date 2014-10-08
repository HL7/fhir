Unit AdvStringIntegerMatches;

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
  TAdvStringIntegerMatchKey = String;
  TAdvStringIntegerMatchValue = Integer;

  TAdvStringIntegerMatchItem = Record
    Key : TAdvStringIntegerMatchKey;
    Value : TAdvStringIntegerMatchValue;
  End;

  PAdvStringIntegerMatchItem = ^TAdvStringIntegerMatchItem;

  TAdvStringIntegerMatchItemArray = Array[0..(MaxInt Div SizeOf(TAdvStringIntegerMatchItem)) - 1] Of TAdvStringIntegerMatchItem;
  PAdvStringIntegerMatchItemArray = ^TAdvStringIntegerMatchItemArray;

  TAdvStringCompareCallback = Function (Const sA, sB : String) : Integer;

  TAdvStringIntegerMatch = Class(TAdvItemList)
    Private
      FMatchArray : PAdvStringIntegerMatchItemArray;
      FDefaultKey : TAdvStringIntegerMatchKey;
      FDefaultValue : TAdvStringIntegerMatchValue;
      FForced : Boolean;
      FCompareKey : TAdvStringCompareCallback;
      FSymbol : String;

      Function GetKey(iIndex : Integer): String;
      Procedure SetKey(iIndex : Integer; Const aKey : TAdvStringIntegerMatchKey);

      Function GetValue(iIndex : Integer): Integer;
      Procedure SetValue(iIndex : Integer; Const aValue : TAdvStringIntegerMatchValue);

      Function GetMatch(Const aKey : TAdvStringIntegerMatchKey): TAdvStringIntegerMatchValue;
      Procedure SetMatch(Const aKey : TAdvStringIntegerMatchKey; Const aValue : TAdvStringIntegerMatchValue);

      Function GetAsText : String;
      Function GetKeysAsText : String;

      Function GetSensitive: Boolean;
      Procedure SetSensitive(Const Value: Boolean);

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

      Function GetItem(iIndex : Integer) : Pointer; Override;
      Procedure SetItem(iIndex: Integer; pValue: Pointer); Override;

      Procedure SaveItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure LoadItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure AssignItem(oItems : TAdvItems; iIndex : Integer); Override;

      Procedure InternalTruncate(iValue : Integer); Override;
      Procedure InternalResize(iValue : Integer); Override;
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Override;
      Procedure InternalEmpty(iIndex, iLength : Integer); Override;
      Procedure InternalInsert(iIndex : Integer); Overload; Override;
      Procedure InternalExchange(iA, iB : Integer); Overload; Override;
      Procedure InternalDelete(iIndex : Integer); Overload; Override;

      Function CompareKey(pA, pB : Pointer) : Integer; Virtual;
      Function CompareValue(pA, pB : Pointer) : Integer; Virtual;

      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Overload; Override;

      Function Find(Const aKey : TAdvStringIntegerMatchKey; Const aValue : TAdvStringIntegerMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; Overload;

      Function CapacityLimit : Integer; Override;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvStringIntegerMatch; 
      Function Clone : TAdvStringIntegerMatch;

      Procedure AddAll(oStringIntegerMatch : TAdvStringIntegerMatch);

      Function IndexByKey(Const aKey : TAdvStringIntegerMatchKey) : Integer; 
      Function ExistsByKey(Const aKey : TAdvStringIntegerMatchKey) : Boolean;
      Function IndexByValue(Const aValue : TAdvStringIntegerMatchValue) : Integer;
      Function ExistsByValue(Const aValue : TAdvStringIntegerMatchValue) : Boolean;
      Function Add(Const aKey : TAdvStringIntegerMatchKey; Const aValue : TAdvStringIntegerMatchValue) : Integer; Overload;
      Function ForceByKey(Const aKey : TAdvStringIntegerMatchKey) : TAdvStringIntegerMatchValue;
      Procedure Insert(iIndex : Integer; Const aKey : TAdvStringIntegerMatchKey; Const aValue : TAdvStringIntegerMatchValue); Overload;

      Function GetValueByKey(Const aKey : TAdvStringIntegerMatchKey) : TAdvStringIntegerMatchValue;
      Function GetKeyByValue(Const aValue : TAdvStringIntegerMatchValue) : TAdvStringIntegerMatchKey;

      Function IsSortedByKey: Boolean;
      Function IsSortedByValue: Boolean;

      Procedure SortedByKey;
      Procedure SortedByValue;

      Property Matches[Const sIndex : TAdvStringIntegerMatchKey] : TAdvStringIntegerMatchValue Read GetMatch Write SetMatch; Default;
      Property KeyByIndex[iIndex : Integer] : TAdvStringIntegerMatchKey Read GetKey Write SetKey;
      Property ValueByIndex[iIndex : Integer] : TAdvStringIntegerMatchValue Read GetValue Write SetValue;
      Property Forced : Boolean Read FForced Write FForced;
      Property DefaultKey : TAdvStringIntegerMatchKey Read FDefaultKey Write FDefaultKey;
      Property DefaultValue : TAdvStringIntegerMatchValue Read FDefaultValue Write FDefaultValue;
      Property KeysAsText : String Read GetKeysAsText;
      Property AsText : String Read GetAsText;
      Property Symbol : String Read FSymbol Write FSymbol;
      Property Sensitive : Boolean Read GetSensitive Write SetSensitive;
  End;

  EAdvStringIntegerMatch = Class(EAdvException);


Implementation


Constructor TAdvStringIntegerMatch.Create;
Begin
  Inherited;

  FSymbol := cReturn;
End;


Destructor TAdvStringIntegerMatch.Destroy;
Begin
  Inherited;
End;


Function TAdvStringIntegerMatch.ErrorClass: EAdvExceptionClass;
Begin
  Result := EAdvStringIntegerMatch;
End;


Function TAdvStringIntegerMatch.Clone: TAdvStringIntegerMatch;
Begin
  Result := TAdvStringIntegerMatch(Inherited Clone);
End;


Function TAdvStringIntegerMatch.Link: TAdvStringIntegerMatch;
Begin
  Result := TAdvStringIntegerMatch(Inherited Link);
End;


Procedure TAdvStringIntegerMatch.AssignItem(oItems: TAdvItems; iIndex: Integer);
Begin
  FMatchArray^[iIndex] := TAdvStringIntegerMatch(oItems).FMatchArray^[iIndex];
End;


Procedure TAdvStringIntegerMatch.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineString(FMatchArray^[iIndex].Key);
  oFiler['Value'].DefineInteger(FMatchArray^[iIndex].Value);

  oFiler['Match'].DefineEnd;
End;  


Procedure TAdvStringIntegerMatch.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Var
  sKey : TAdvStringIntegerMatchKey;
  iValue : TAdvStringIntegerMatchValue;
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineString(sKey);
  oFiler['Value'].DefineInteger(iValue);

  oFiler['Match'].DefineEnd;

  Add(sKey, iValue);  
End;  


Procedure TAdvStringIntegerMatch.InternalEmpty(iIndex, iLength: Integer);
Begin 
  Inherited;

  MemoryZero(Pointer(NativeUInt(FMatchArray) + NativeUInt(iIndex * SizeOf(TAdvStringIntegerMatchItem))), (iLength * SizeOf(TAdvStringIntegerMatchItem)));
End;  


Procedure TAdvStringIntegerMatch.InternalTruncate(iValue: Integer);
Begin
  Inherited;

  // finalize the strings that will be truncated
  If iValue < Count Then
    Finalize(FMatchArray^[iValue], Count - iValue);
End;


Procedure TAdvStringIntegerMatch.InternalResize(iValue : Integer);
Begin
  Inherited;

  MemoryResize(FMatchArray, Capacity * SizeOf(TAdvStringIntegerMatchItem), iValue * SizeOf(TAdvStringIntegerMatchItem));
End;  


Procedure TAdvStringIntegerMatch.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
  MemoryMove(@FMatchArray^[iSource], @FMatchArray^[iTarget], iCount * SizeOf(TAdvStringIntegerMatchItem));
End;  


Function TAdvStringIntegerMatch.CompareKey(pA, pB: Pointer): Integer;
Begin 
  Result := FCompareKey(PAdvStringIntegerMatchItem(pA)^.Key, PAdvStringIntegerMatchItem(pB)^.Key);
End;  


Function TAdvStringIntegerMatch.CompareValue(pA, pB: Pointer): Integer;
Begin 
  Result := IntegerCompare(PAdvStringIntegerMatchItem(pA)^.Value, PAdvStringIntegerMatchItem(pB)^.Value);
End;  


Procedure TAdvStringIntegerMatch.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin 
  aCompare := {$IFDEF FPC}@{$ENDIF}CompareKey;
  FCompareKey := {$IFDEF FPC}@{$ENDIF}StringCompareInsensitive;
End;  


Function TAdvStringIntegerMatch.Find(Const aKey: TAdvStringIntegerMatchKey; Const aValue: TAdvStringIntegerMatchValue; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Var
  aItem : TAdvStringIntegerMatchItem;
Begin 
  aItem.Key := aKey;
  aItem.Value := aValue;

  Result := Inherited Find(@aItem, iIndex, aCompare);
End;  


Function TAdvStringIntegerMatch.IndexByKey(Const aKey : TAdvStringIntegerMatchKey) : Integer;
Begin 
  If Not Find(aKey, 0, Result, {$IFDEF FPC}@{$ENDIF}CompareKey) Then
    Result := -1;
End;  


Function TAdvStringIntegerMatch.ExistsByKey(Const aKey : TAdvStringIntegerMatchKey) : Boolean;
Begin 
  Result := ExistsByIndex(IndexByKey(aKey));
End;


Function TAdvStringIntegerMatch.IndexByValue(Const aValue : TAdvStringIntegerMatchValue) : Integer;
Begin 
  If Not Find('', aValue, Result, {$IFDEF FPC}@{$ENDIF}CompareValue) Then
    Result := -1;
End;  


Function TAdvStringIntegerMatch.ExistsByValue(Const aValue : TAdvStringIntegerMatchValue) : Boolean;
Begin 
  Result := ExistsByIndex(IndexByValue(aValue));
End;  


Function TAdvStringIntegerMatch.Add(Const aKey : TAdvStringIntegerMatchKey; Const aValue : TAdvStringIntegerMatchValue) : Integer;
Begin 
  Result := -1;

  If Not IsAllowDuplicates And Find(aKey, aValue, Result) Then
  Begin 
    If IsPreventDuplicates Then
      Error('Add', StringFormat('Key already exists in list (%s=%d)', [aKey, aValue]));
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


Procedure TAdvStringIntegerMatch.Insert(iIndex : Integer; Const aKey : TAdvStringIntegerMatchKey; Const aValue : TAdvStringIntegerMatchValue);
Begin 
  InternalInsert(iIndex);

  FMatchArray^[iIndex].Key := aKey;
  FMatchArray^[iIndex].Value := aValue;
End;  


Procedure TAdvStringIntegerMatch.InternalInsert(iIndex : Integer);
Begin 
  Inherited;

  Pointer(FMatchArray^[iIndex].Key) := Nil;
  FMatchArray^[iIndex].Value := 0;
End;  


Procedure TAdvStringIntegerMatch.InternalDelete(iIndex : Integer);
Begin 
  Inherited;

  Finalize(FMatchArray^[iIndex]);  
End;  


Procedure TAdvStringIntegerMatch.InternalExchange(iA, iB : Integer);
Var
  aTemp : TAdvStringIntegerMatchItem;
  pA : Pointer;
  pB : Pointer;
Begin
  pA := @FMatchArray^[iA];
  pB := @FMatchArray^[iB];

  aTemp := PAdvStringIntegerMatchItem(pA)^;
  PAdvStringIntegerMatchItem(pA)^ := PAdvStringIntegerMatchItem(pB)^;
  PAdvStringIntegerMatchItem(pB)^ := aTemp;
End;


Function TAdvStringIntegerMatch.ForceByKey(Const aKey: TAdvStringIntegerMatchKey): TAdvStringIntegerMatchValue;
Var
  iIndex : Integer;
Begin
  If Not Find(aKey, 0, iIndex, {$IFDEF FPC}@{$ENDIF}CompareKey) Then
    Insert(iIndex, aKey, DefaultValue);

  Result := ValueByIndex[iIndex]
End;


Function TAdvStringIntegerMatch.GetItem(iIndex : Integer) : Pointer;
Begin 
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FMatchArray^[iIndex];
End;  


Procedure TAdvStringIntegerMatch.SetItem(iIndex : Integer; pValue : Pointer);
Begin 
  Assert(ValidateIndex('SetItem', iIndex));

  FMatchArray^[iIndex] := PAdvStringIntegerMatchItem(pValue)^;
End;  


Function TAdvStringIntegerMatch.GetKey(iIndex : Integer) : String;
Begin 
  Assert(ValidateIndex('GetKey', iIndex));

  Result := FMatchArray^[iIndex].Key;
End;  


Procedure TAdvStringIntegerMatch.SetKey(iIndex : Integer; Const aKey : TAdvStringIntegerMatchKey);
Begin 
  Assert(ValidateIndex('SetKey', iIndex));

  FMatchArray^[iIndex].Key := aKey;
End;  


Function TAdvStringIntegerMatch.GetValue(iIndex : Integer) : TAdvStringIntegerMatchValue;
Begin 
  Assert(ValidateIndex('GetValue', iIndex));

  Result := FMatchArray^[iIndex].Value;
End;  


Procedure TAdvStringIntegerMatch.SetValue(iIndex : Integer; Const aValue : TAdvStringIntegerMatchValue);
Begin 
  Assert(ValidateIndex('SetValue', iIndex));

  FMatchArray^[iIndex].Value := aValue;
End;  


Function TAdvStringIntegerMatch.GetMatch(Const aKey : TAdvStringIntegerMatchKey) : TAdvStringIntegerMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else
  Begin 
    Result := FDefaultValue;

    If Not FForced Then
      Error('GetMatch', 'Unable to get the value for the specified key.');
  End;  
End;  


Procedure TAdvStringIntegerMatch.SetMatch(Const aKey : TAdvStringIntegerMatchKey; Const aValue : TAdvStringIntegerMatchValue);
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    ValueByIndex[iIndex] := aValue
  Else If FForced Then
    Add(aKey, aValue)
  Else
    Error('SetMatch', 'Unable to set the value for the specified key.');
End;  


Function TAdvStringIntegerMatch.GetAsText : String;
Var
  iLoop : Integer;
Begin 
  Result := '';
  For iLoop := 0 To Count - 1 Do
    StringAppend(Result, KeyByIndex[iLoop] + '=' + IntegerToString(ValueByIndex[iLoop]), FSymbol);
End;  


Function TAdvStringIntegerMatch.GetKeysAsText : String;
Var
  iLoop : Integer;
Begin 
  Result := '';
  For iLoop := 0 To Count - 1 Do
    StringAppend(Result, KeyByIndex[iLoop], FSymbol);
End;  


Function TAdvStringIntegerMatch.CapacityLimit : Integer;
Begin 
  Result := High(TAdvStringIntegerMatchItemArray);
End;  


Function TAdvStringIntegerMatch.GetSensitive : Boolean;
Var
  aCompare : TAdvStringCompareCallback;
Begin 
  aCompare := {$IFDEF FPC}@{$ENDIF}StringCompareSensitive;

  Result := @FCompareKey = @aCompare;
End;


Procedure TAdvStringIntegerMatch.SetSensitive(Const Value: Boolean);
Begin
  If Value Then
    FCompareKey := {$IFDEF FPC}@{$ENDIF}StringCompareSensitive
  Else
    FCompareKey := {$IFDEF FPC}@{$ENDIF}StringCompareInsensitive;
End;  


Function TAdvStringIntegerMatch.GetKeyByValue(Const aValue: TAdvStringIntegerMatchValue): TAdvStringIntegerMatchKey;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByValue(aValue);

  If ExistsByIndex(iIndex) Then
    Result := KeyByIndex[iIndex]
  Else
    Result := DefaultKey;
End;


Function TAdvStringIntegerMatch.GetValueByKey(Const aKey : TAdvStringIntegerMatchKey): TAdvStringIntegerMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else
    Result := DefaultValue;
End;  


Function TAdvStringIntegerMatch.IsSortedByKey : Boolean;
Begin 
  Result := IsSortedBy({$IFDEF FPC}@{$ENDIF}CompareKey);
End;  


Procedure TAdvStringIntegerMatch.SortedByKey;
Begin 
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareKey);
End;  


Function TAdvStringIntegerMatch.IsSortedByValue : Boolean;
Begin 
  Result := IsSortedBy({$IFDEF FPC}@{$ENDIF}CompareValue);
End;  


Procedure TAdvStringIntegerMatch.SortedByValue;
Begin 
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareValue);
End;  


Procedure TAdvStringIntegerMatch.AddAll(oStringIntegerMatch: TAdvStringIntegerMatch);
Var
  iIndex : Integer;
Begin
  Assert(Condition(oStringIntegerMatch <> Self, 'AddAll', 'Cannot add all items from a list to itself.'));

  Capacity := IntegerMax(Count + oStringIntegerMatch.Count, Capacity);

  For iIndex := 0 To oStringIntegerMatch.Count - 1 Do
    Add(oStringIntegerMatch.KeyByIndex[iIndex], oStringIntegerMatch.ValueByIndex[iIndex]);
End;


End. // AdvStringIntegerMatches //






