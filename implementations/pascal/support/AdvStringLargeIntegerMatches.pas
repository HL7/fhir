Unit AdvStringLargeIntegerMatches;

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
  TAdvStringLargeIntegerMatchKey = String;
  TAdvStringLargeIntegerMatchValue = Int64;

  TAdvStringLargeIntegerMatchItem = Record
    Key : TAdvStringLargeIntegerMatchKey;
    Value : TAdvStringLargeIntegerMatchValue;
  End;

  PAdvStringLargeIntegerMatchItem = ^TAdvStringLargeIntegerMatchItem;

  TAdvStringLargeIntegerMatchItemArray = Array[0..(MaxInt Div SizeOf(TAdvStringLargeIntegerMatchItem)) - 1] Of TAdvStringLargeIntegerMatchItem;
  PAdvStringLargeIntegerMatchItemArray = ^TAdvStringLargeIntegerMatchItemArray;

  TAdvStringCompareCallback = Function (Const sA, sB : String) : Integer;

  TAdvStringLargeIntegerMatch = Class(TAdvItemList)
    Private
      FMatchArray : PAdvStringLargeIntegerMatchItemArray;
      FDefault : Integer;
      FForced : Boolean;
      FCompareKey : TAdvStringCompareCallback;
      FSymbol : String;

      Function GetKey(iIndex : Integer): String;
      Procedure SetKey(iIndex : Integer; Const aKey : TAdvStringLargeIntegerMatchKey);

      Function GetValue(iIndex : Integer): TAdvStringLargeIntegerMatchValue;
      Procedure SetValue(iIndex : Integer; Const aValue : TAdvStringLargeIntegerMatchValue);

      Function GetMatch(Const aKey : TAdvStringLargeIntegerMatchKey): TAdvStringLargeIntegerMatchValue;
      Procedure SetMatch(Const aKey : TAdvStringLargeIntegerMatchKey; Const aValue : TAdvStringLargeIntegerMatchValue);

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

      Function Find(Const aKey : TAdvStringLargeIntegerMatchKey; Const aValue : TAdvStringLargeIntegerMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; Overload;

      Function CapacityLimit : Integer; Override;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvStringLargeIntegerMatch; 
      Function Clone : TAdvStringLargeIntegerMatch;

      Function IndexByKey(Const aKey : TAdvStringLargeIntegerMatchKey) : Integer; 
      Function ExistsByKey(Const aKey : TAdvStringLargeIntegerMatchKey) : Boolean;
      Function IndexByValue(Const aValue : TAdvStringLargeIntegerMatchValue) : Integer;
      Function ExistsByValue(Const aValue : TAdvStringLargeIntegerMatchValue) : Boolean;
      Function Add(Const aKey : TAdvStringLargeIntegerMatchKey; Const aValue : TAdvStringLargeIntegerMatchValue) : Integer; Overload;
      Function Force(Const aKey : TAdvStringLargeIntegerMatchKey) : TAdvStringLargeIntegerMatchValue; Overload;
      Procedure Insert(iIndex : Integer; Const aKey : TAdvStringLargeIntegerMatchKey; Const aValue : TAdvStringLargeIntegerMatchValue); Overload;

      Function GetByKey(Const aKey : TAdvStringLargeIntegerMatchKey) : TAdvStringLargeIntegerMatchValue; 
      Function GetByValue(Const aValue : TAdvStringLargeIntegerMatchValue) : TAdvStringLargeIntegerMatchKey;

      Function IsSortedByKey: Boolean;
      Function IsSortedByValue: Boolean;

      Procedure SortedByKey;
      Procedure SortedByValue;

      Property Matches[Const sIndex : TAdvStringLargeIntegerMatchKey] : TAdvStringLargeIntegerMatchValue Read GetMatch Write SetMatch; Default;
      Property KeyByIndex[iIndex : Integer] : TAdvStringLargeIntegerMatchKey Read GetKey Write SetKey;
      Property ValueByIndex[iIndex : Integer] : TAdvStringLargeIntegerMatchValue Read GetValue Write SetValue;
      Property Forced : Boolean Read FForced Write FForced;
      Property Default : Integer Read FDefault Write FDefault;
      Property KeysAsText : String Read GetKeysAsText;
      Property AsText : String Read GetAsText;
      Property Symbol : String Read FSymbol Write FSymbol;
      Property Sensitive : Boolean Read GetSensitive Write SetSensitive;
  End;

  EAdvStringLargeIntegerMatch = Class(EAdvException);


Implementation


Constructor TAdvStringLargeIntegerMatch.Create;
Begin
  Inherited;

  FSymbol := cReturn;
End;


Destructor TAdvStringLargeIntegerMatch.Destroy;
Begin
  Inherited;
End;


Function TAdvStringLargeIntegerMatch.ErrorClass: EAdvExceptionClass;
Begin
  Result := EAdvStringLargeIntegerMatch;
End;


Function TAdvStringLargeIntegerMatch.Clone: TAdvStringLargeIntegerMatch;
Begin
  Result := TAdvStringLargeIntegerMatch(Inherited Clone);
End;


Function TAdvStringLargeIntegerMatch.Link: TAdvStringLargeIntegerMatch;
Begin
  Result := TAdvStringLargeIntegerMatch(Inherited Link);
End;


Procedure TAdvStringLargeIntegerMatch.AssignItem(oItems: TAdvItems; iIndex: Integer);
Begin
  FMatchArray^[iIndex] := TAdvStringLargeIntegerMatch(oItems).FMatchArray^[iIndex];
End;


Procedure TAdvStringLargeIntegerMatch.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineString(FMatchArray^[iIndex].Key);
  oFiler['Value'].DefineInteger(FMatchArray^[iIndex].Value);

  oFiler['Match'].DefineEnd;
End;  


Procedure TAdvStringLargeIntegerMatch.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Var
  sKey : TAdvStringLargeIntegerMatchKey;
  iValue : TAdvStringLargeIntegerMatchValue;
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineString(sKey);
  oFiler['Value'].DefineInteger(iValue);

  oFiler['Match'].DefineEnd;

  Add(sKey, iValue);  
End;  


Procedure TAdvStringLargeIntegerMatch.InternalEmpty(iIndex, iLength: Integer);
Begin 
  Inherited;

  MemoryZero(Pointer(NativeUInt(FMatchArray) + NativeUInt(iIndex * SizeOf(TAdvStringLargeIntegerMatchItem))), (iLength * SizeOf(TAdvStringLargeIntegerMatchItem)));
End;


Procedure TAdvStringLargeIntegerMatch.InternalTruncate(iValue: Integer);
Begin
  Inherited;

  // finalize the strings that will be truncated
  If iValue < Count Then
    Finalize(FMatchArray^[iValue], Count - iValue);
End;


Procedure TAdvStringLargeIntegerMatch.InternalResize(iValue : Integer);
Begin
  Inherited;

  MemoryResize(FMatchArray, Capacity * SizeOf(TAdvStringLargeIntegerMatchItem), iValue * SizeOf(TAdvStringLargeIntegerMatchItem));
End;


Procedure TAdvStringLargeIntegerMatch.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
  MemoryMove(@FMatchArray^[iSource], @FMatchArray^[iTarget], iCount * SizeOf(TAdvStringLargeIntegerMatchItem));
End;  


Function TAdvStringLargeIntegerMatch.CompareKey(pA, pB: Pointer): Integer;
Begin 
  Result := FCompareKey(PAdvStringLargeIntegerMatchItem(pA)^.Key, PAdvStringLargeIntegerMatchItem(pB)^.Key);
End;  


Function TAdvStringLargeIntegerMatch.CompareValue(pA, pB: Pointer): Integer;
Begin 
  Result := IntegerCompare(PAdvStringLargeIntegerMatchItem(pA)^.Value, PAdvStringLargeIntegerMatchItem(pB)^.Value);
End;  


Procedure TAdvStringLargeIntegerMatch.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin 
  aCompare := {$IFDEF FPC}@{$ENDIF}CompareKey;
  FCompareKey := {$IFDEF FPC}@{$ENDIF}StringCompareInsensitive;
End;  


Function TAdvStringLargeIntegerMatch.Find(Const aKey: TAdvStringLargeIntegerMatchKey; Const aValue: TAdvStringLargeIntegerMatchValue; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Var
  aItem : TAdvStringLargeIntegerMatchItem;
Begin 
  aItem.Key := aKey;
  aItem.Value := aValue;

  Result := Inherited Find(@aItem, iIndex, aCompare);
End;  


Function TAdvStringLargeIntegerMatch.IndexByKey(Const aKey : TAdvStringLargeIntegerMatchKey) : Integer;
Begin 
  If Not Find(aKey, 0, Result, {$IFDEF FPC}@{$ENDIF}CompareKey) Then
    Result := -1;
End;  


Function TAdvStringLargeIntegerMatch.ExistsByKey(Const aKey : TAdvStringLargeIntegerMatchKey) : Boolean;
Begin 
  Result := ExistsByIndex(IndexByKey(aKey));
End;


Function TAdvStringLargeIntegerMatch.IndexByValue(Const aValue : TAdvStringLargeIntegerMatchValue) : Integer;
Begin 
  If Not Find('', aValue, Result, {$IFDEF FPC}@{$ENDIF}CompareValue) Then
    Result := -1;
End;  


Function TAdvStringLargeIntegerMatch.ExistsByValue(Const aValue : TAdvStringLargeIntegerMatchValue) : Boolean;
Begin 
  Result := ExistsByIndex(IndexByValue(aValue));
End;  


Function TAdvStringLargeIntegerMatch.Add(Const aKey : TAdvStringLargeIntegerMatchKey; Const aValue : TAdvStringLargeIntegerMatchValue) : Integer;
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


Procedure TAdvStringLargeIntegerMatch.Insert(iIndex : Integer; Const aKey : TAdvStringLargeIntegerMatchKey; Const aValue : TAdvStringLargeIntegerMatchValue);
Begin 
  InternalInsert(iIndex);

  FMatchArray^[iIndex].Key := aKey;
  FMatchArray^[iIndex].Value := aValue;
End;  


Procedure TAdvStringLargeIntegerMatch.InternalInsert(iIndex : Integer);
Begin 
  Inherited;

  Pointer(FMatchArray^[iIndex].Key) := Nil;
  FMatchArray^[iIndex].Value := 0;
End;  


Procedure TAdvStringLargeIntegerMatch.InternalDelete(iIndex : Integer);
Begin 
  Inherited;

  Finalize(FMatchArray^[iIndex]);  
End;  


Procedure TAdvStringLargeIntegerMatch.InternalExchange(iA, iB : Integer);
Var
  aTemp : TAdvStringLargeIntegerMatchItem;
  pA : Pointer;
  pB : Pointer;
Begin
  pA := @FMatchArray^[iA];
  pB := @FMatchArray^[iB];

  aTemp := PAdvStringLargeIntegerMatchItem(pA)^;
  PAdvStringLargeIntegerMatchItem(pA)^ := PAdvStringLargeIntegerMatchItem(pB)^;
  PAdvStringLargeIntegerMatchItem(pB)^ := aTemp;
End;


Function TAdvStringLargeIntegerMatch.Force(Const aKey: TAdvStringLargeIntegerMatchKey): TAdvStringLargeIntegerMatchValue;
Var
  iIndex : Integer;
Begin
  If Not Find(aKey, 0, iIndex, {$IFDEF FPC}@{$ENDIF}CompareKey) Then
    Insert(iIndex, aKey, Default);

  Result := ValueByIndex[iIndex]
End;


Function TAdvStringLargeIntegerMatch.GetItem(iIndex : Integer) : Pointer;
Begin 
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FMatchArray^[iIndex];
End;  


Procedure TAdvStringLargeIntegerMatch.SetItem(iIndex : Integer; pValue : Pointer);
Begin 
  Assert(ValidateIndex('SetItem', iIndex));

  FMatchArray^[iIndex] := PAdvStringLargeIntegerMatchItem(pValue)^;
End;  


Function TAdvStringLargeIntegerMatch.GetKey(iIndex : Integer) : String;
Begin 
  Assert(ValidateIndex('GetKey', iIndex));

  Result := FMatchArray^[iIndex].Key;
End;  


Procedure TAdvStringLargeIntegerMatch.SetKey(iIndex : Integer; Const aKey : TAdvStringLargeIntegerMatchKey);
Begin 
  Assert(ValidateIndex('SetKey', iIndex));

  FMatchArray^[iIndex].Key := aKey;
End;  


Function TAdvStringLargeIntegerMatch.GetValue(iIndex : Integer) : TAdvStringLargeIntegerMatchValue;
Begin 
  Assert(ValidateIndex('GetValue', iIndex));

  Result := FMatchArray^[iIndex].Value;
End;  


Procedure TAdvStringLargeIntegerMatch.SetValue(iIndex : Integer; Const aValue : TAdvStringLargeIntegerMatchValue);
Begin 
  Assert(ValidateIndex('SetValue', iIndex));

  FMatchArray^[iIndex].Value := aValue;
End;  


Function TAdvStringLargeIntegerMatch.GetMatch(Const aKey : TAdvStringLargeIntegerMatchKey) : TAdvStringLargeIntegerMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else
  Begin 
    Result := FDefault;

    If Not FForced Then
      Error('GetMatch', 'Unable to get the value for the specified key.');
  End;  
End;  


Procedure TAdvStringLargeIntegerMatch.SetMatch(Const aKey : TAdvStringLargeIntegerMatchKey; Const aValue : TAdvStringLargeIntegerMatchValue);
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


Function TAdvStringLargeIntegerMatch.GetAsText : String;
Var
  iLoop : Integer;
Begin 
  Result := '';
  For iLoop := 0 To Count - 1 Do
    StringAppend(Result, KeyByIndex[iLoop] + '=' + IntegerToString(ValueByIndex[iLoop]), FSymbol);
End;  


Function TAdvStringLargeIntegerMatch.GetKeysAsText : String;
Var
  iLoop : Integer;
Begin 
  Result := '';
  For iLoop := 0 To Count - 1 Do
    StringAppend(Result, KeyByIndex[iLoop], FSymbol);
End;  


Function TAdvStringLargeIntegerMatch.CapacityLimit : Integer;
Begin 
  Result := High(TAdvStringLargeIntegerMatchItemArray);
End;  


Function TAdvStringLargeIntegerMatch.GetSensitive : Boolean;
Var
  aCompare : TAdvStringCompareCallback;
Begin 
  aCompare := {$IFDEF FPC}@{$ENDIF}StringCompareSensitive;

  Result := @FCompareKey = @aCompare;
End;


Procedure TAdvStringLargeIntegerMatch.SetSensitive(Const Value: Boolean);
Begin
  If Value Then
    FCompareKey := {$IFDEF FPC}@{$ENDIF}StringCompareSensitive
  Else
    FCompareKey := {$IFDEF FPC}@{$ENDIF}StringCompareInsensitive;
End;  


Function TAdvStringLargeIntegerMatch.GetByValue(Const aValue: TAdvStringLargeIntegerMatchValue): TAdvStringLargeIntegerMatchKey;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByValue(aValue);

  If ExistsByIndex(iIndex) Then
    Result := KeyByIndex[iIndex]
  Else
    Result := '';
End;  


Function TAdvStringLargeIntegerMatch.GetByKey(Const aKey : TAdvStringLargeIntegerMatchKey): TAdvStringLargeIntegerMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else
    Result := 0;
End;  


Function TAdvStringLargeIntegerMatch.IsSortedByKey : Boolean;
Begin 
  Result := IsSortedBy({$IFDEF FPC}@{$ENDIF}CompareKey);
End;  


Procedure TAdvStringLargeIntegerMatch.SortedByKey;
Begin 
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareKey);
End;  


Function TAdvStringLargeIntegerMatch.IsSortedByValue : Boolean;
Begin 
  Result := IsSortedBy({$IFDEF FPC}@{$ENDIF}CompareValue);
End;  


Procedure TAdvStringLargeIntegerMatch.SortedByValue;
Begin 
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareValue);
End;


End.
