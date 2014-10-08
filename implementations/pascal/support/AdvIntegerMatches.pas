Unit AdvIntegerMatches;

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
  TAdvIntegerMatchKey = Integer;
  TAdvIntegerMatchValue = Integer;

  TAdvIntegerMatchItem = Record
    Key   : TAdvIntegerMatchKey;
    Value : TAdvIntegerMatchValue;
  End; 

  PAdvIntegerMatchItem = ^TAdvIntegerMatchItem;

  TAdvIntegerMatchItems = Array[0..(MaxInt Div SizeOf(TAdvIntegerMatchItem)) - 1] Of TAdvIntegerMatchItem;
  PAdvIntegerMatchItems = ^TAdvIntegerMatchItems;

  TAdvIntegerMatch = Class(TAdvItems)
    Private
      FMatches : PAdvIntegerMatchItems;
      FDefault : TAdvIntegerMatchValue;
      FForced  : Boolean;

      Function GetKey(iIndex: Integer): TAdvIntegerMatchKey;
      Procedure SetKey(iIndex: Integer; Const iValue: TAdvIntegerMatchKey);

      Function GetValue(iIndex: Integer): TAdvIntegerMatchValue;
      Procedure SetValue(iIndex: Integer; Const iValue: TAdvIntegerMatchValue);

      Function GetMatch(iKey : TAdvIntegerMatchKey): Integer;
      Procedure SetMatch(iKey: TAdvIntegerMatchKey; Const iValue: TAdvIntegerMatchValue);

      Function GetPair(iIndex: Integer): TAdvIntegerMatchItem;
      Procedure SetPair(iIndex: Integer; Const Value: TAdvIntegerMatchItem);

    Protected
      Function GetItem(iIndex : Integer) : Pointer; Override;
      Procedure SetItem(iIndex: Integer; pValue: Pointer); Override;

      Procedure LoadItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure SaveItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure AssignItem(oItems : TAdvItems; iIndex : Integer); Override;

      Procedure InternalResize(iValue : Integer); Override;
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Override;
      Procedure InternalEmpty(iIndex, iLength : Integer); Override;
      Procedure InternalInsert(iIndex : Integer); Overload; Override;
      Procedure InternalExchange(iA, iB : Integer); Overload; Override;

      Function CompareByKey(pA, pB : Pointer): Integer; 
      Function CompareByValue(pA, pB : Pointer): Integer; 
      Function CompareByKeyValue(pA, pB : Pointer) : Integer; 

      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Overload; Override;

      Function CapacityLimit : Integer; Override;

      Function Find(Const aKey : TAdvIntegerMatchKey; Const aValue: TAdvIntegerMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; 
      Function FindByKey(Const aKey : TAdvIntegerMatchKey; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; 

    Public
      Function Link : TAdvIntegerMatch; 

      Function Add(aKey : TAdvIntegerMatchKey; aValue : TAdvIntegerMatchValue): Integer; 
      Procedure Insert(iIndex : Integer; iKey : TAdvIntegerMatchKey; iValue : TAdvIntegerMatchValue); 

      Function IndexByKey(aKey : TAdvIntegerMatchKey) : Integer; 
      Function IndexByKeyValue(Const aKey : TAdvIntegerMatchKey; Const aValue : TAdvIntegerMatchValue) : Integer; 

      Function ExistsByKey(aKey : TAdvIntegerMatchKey) : Boolean; 
      Function ExistsByKeyValue(Const aKey : TAdvIntegerMatchKey; Const aValue : TAdvIntegerMatchValue) : Boolean; 

      Function EqualTo(Const oIntegerMatch : TAdvIntegerMatch) : Boolean;

      Procedure DeleteByKey(aKey : TAdvIntegerMatchKey); 

      Procedure ForceIncrementByKey(Const aKey : TAdvIntegerMatchKey); 

      Procedure SortedByKey; 
      Procedure SortedByValue; 
      Procedure SortedByKeyValue; 

      Property Matches[iKey : TAdvIntegerMatchKey] : TAdvIntegerMatchValue Read GetMatch Write SetMatch; Default;
      Property KeyByIndex[iIndex : Integer] : TAdvIntegerMatchKey Read GetKey Write SetKey;
      Property ValueByIndex[iIndex : Integer] : TAdvIntegerMatchValue Read GetValue Write SetValue;
      Property Pairs[iIndex : Integer] : TAdvIntegerMatchItem Read GetPair Write SetPair;
      Property Forced : Boolean Read FForced Write FForced;
      Property Default : TAdvIntegerMatchValue Read FDefault Write FDefault;
  End;


Implementation


Function TAdvIntegerMatch.Link : TAdvIntegerMatch;
Begin
  Result := TAdvIntegerMatch(Inherited Link);
End;


Function TAdvIntegerMatch.CompareByKey(pA, pB: Pointer): Integer;
Begin 
  Result := IntegerCompare(PAdvIntegerMatchItem(pA)^.Key, PAdvIntegerMatchItem(pB)^.Key);
End;


Function TAdvIntegerMatch.CompareByValue(pA, pB: Pointer): Integer;
Begin
  Result := IntegerCompare(PAdvIntegerMatchItem(pA)^.Value, PAdvIntegerMatchItem(pB)^.Value);
End;  


Function TAdvIntegerMatch.CompareByKeyValue(pA, pB: Pointer): Integer;
Begin
  Result := CompareByKey(pA, pB);

  If Result = 0 Then
    Result := CompareByValue(pA, pB);
End;


Procedure TAdvIntegerMatch.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin 
  aCompare := {$IFDEF FPC}@{$ENDIF}CompareByKey;
End;  


Procedure TAdvIntegerMatch.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Var
  iKey : TAdvIntegerMatchKey;
  iValue : TAdvIntegerMatchValue;
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineInteger(iKey);
  oFiler['Value'].DefineInteger(iValue);

  oFiler['Match'].DefineEnd;

  Add(iKey, iValue);
End;  


Procedure TAdvIntegerMatch.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineInteger(FMatches^[iIndex].Key);
  oFiler['Value'].DefineInteger(FMatches^[iIndex].Value);

  oFiler['Match'].DefineEnd;
End;  


Procedure TAdvIntegerMatch.AssignItem(oItems: TAdvItems; iIndex: Integer);
Begin 
  Inherited;

  FMatches^[iIndex] := TAdvIntegerMatch(oItems).FMatches^[iIndex];
End;  


Procedure TAdvIntegerMatch.InternalEmpty(iIndex, iLength: Integer);
Begin 
  Inherited;

  MemoryZero(Pointer(NativeUInt(FMatches) + NativeUInt(iIndex * SizeOf(TAdvIntegerMatchItem))), (iLength * SizeOf(TAdvIntegerMatchItem)));
End;  


Procedure TAdvIntegerMatch.InternalResize(iValue : Integer);
Begin 
  Inherited;

  MemoryResize(FMatches, Capacity * SizeOf(TAdvIntegerMatchItem), iValue * SizeOf(TAdvIntegerMatchItem));
End;  


Procedure TAdvIntegerMatch.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
  MemoryMove(@FMatches^[iSource], @FMatches^[iTarget], iCount * SizeOf(TAdvIntegerMatchItem));
End;  


Function TAdvIntegerMatch.IndexByKey(aKey : TAdvIntegerMatchKey): Integer;
Begin 
  If Not FindByKey(aKey, Result, {$IFDEF FPC}@{$ENDIF}CompareByKey) Then
    Result := -1;
End;  


Function TAdvIntegerMatch.IndexByKeyValue(Const aKey : TAdvIntegerMatchKey; Const aValue : TAdvIntegerMatchValue) : Integer;
Begin
  If Not Find(aKey, aValue, Result, {$IFDEF FPC}@{$ENDIF}CompareByKeyValue) Then
    Result := -1;
End;


Function TAdvIntegerMatch.ExistsByKey(aKey : TAdvIntegerMatchKey): Boolean;
Begin
  Result := ExistsByIndex(IndexByKey(aKey));
End;  


Function TAdvIntegerMatch.ExistsByKeyValue(Const aKey : TAdvIntegerMatchKey; Const aValue : TAdvIntegerMatchValue) : Boolean;
Begin
  Result := ExistsByIndex(IndexByKeyValue(aKey, aValue));
End;


Function TAdvIntegerMatch.Add(aKey : TAdvIntegerMatchKey; aValue : TAdvIntegerMatchValue): Integer;
Begin 
  Result := -1;

  If Not IsAllowDuplicates And Find(aKey, aValue, Result) Then
  Begin 
    If IsPreventDuplicates Then
      Error('Add', StringFormat('Key already exists in list (%s=%s)', [aKey, aValue]));

    // Result is the index of the existing key
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


Procedure TAdvIntegerMatch.Insert(iIndex: Integer; iKey : TAdvIntegerMatchKey; iValue : TAdvIntegerMatchValue);
Begin 
  InternalInsert(iIndex);

  FMatches^[iIndex].Key := iKey;
  FMatches^[iIndex].Value := iValue;
End;  


Procedure TAdvIntegerMatch.InternalInsert(iIndex: Integer);
Begin 
  Inherited;

  FMatches^[iIndex].Key := 0;
  FMatches^[iIndex].Value := 0;
End;  


Procedure TAdvIntegerMatch.InternalExchange(iA, iB : Integer);
Var
  aTemp : TAdvIntegerMatchItem;
  pA    : Pointer;
  pB    : Pointer;
Begin 
  pA := @FMatches^[iA];
  pB := @FMatches^[iB];

  aTemp := PAdvIntegerMatchItem(pA)^;
  PAdvIntegerMatchItem(pA)^ := PAdvIntegerMatchItem(pB)^;
  PAdvIntegerMatchItem(pB)^ := aTemp;
End;  


Function TAdvIntegerMatch.GetItem(iIndex: Integer): Pointer;
Begin 
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FMatches^[iIndex];
End;  


Procedure TAdvIntegerMatch.SetItem(iIndex: Integer; pValue: Pointer);
Begin 
  Assert(ValidateIndex('SetItem', iIndex));

  FMatches^[iIndex] := PAdvIntegerMatchItem(pValue)^;
End;  


Function TAdvIntegerMatch.GetKey(iIndex: Integer): TAdvIntegerMatchKey;
Begin 
  Assert(ValidateIndex('GetKey', iIndex));

  Result := FMatches^[iIndex].Key;
End;  


Procedure TAdvIntegerMatch.SetKey(iIndex: Integer; Const iValue: TAdvIntegerMatchKey);
Begin 
  Assert(ValidateIndex('SetKey', iIndex));

  FMatches^[iIndex].Key := iValue;
End;  


Function TAdvIntegerMatch.GetValue(iIndex: Integer): TAdvIntegerMatchValue;
Begin 
  Assert(ValidateIndex('GetValue', iIndex));

  Result := FMatches^[iIndex].Value;
End;  


Procedure TAdvIntegerMatch.SetValue(iIndex: Integer; Const iValue: TAdvIntegerMatchValue);
Begin 
  Assert(ValidateIndex('SetValue', iIndex));

  FMatches^[iIndex].Value := iValue;
End;  


Function TAdvIntegerMatch.GetPair(iIndex: Integer): TAdvIntegerMatchItem;
Begin 
  Assert(ValidateIndex('GetPair', iIndex));

  Result := FMatches^[iIndex];
End;  


Procedure TAdvIntegerMatch.SetPair(iIndex: Integer; Const Value: TAdvIntegerMatchItem);
Begin 
  Assert(ValidateIndex('SetPair', iIndex));

  FMatches^[iIndex] := Value;
End;  


Function TAdvIntegerMatch.GetMatch(iKey : TAdvIntegerMatchKey): TAdvIntegerMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(iKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else
  Begin 
    Result := FDefault;
    If Not FForced Then
      Error('GetMatch', 'Unable to get the value for the specified key.');
  End;  
End;  


Procedure TAdvIntegerMatch.SetMatch(iKey : TAdvIntegerMatchKey; Const iValue: TAdvIntegerMatchValue);
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(iKey);

  If ExistsByIndex(iIndex) Then
    ValueByIndex[iIndex] := iValue
  Else If FForced Then
    Add(iKey, iValue)
  Else
    Error('SetMatch', 'Unable to set the value for the specified key.');
End;  


Function TAdvIntegerMatch.CapacityLimit : Integer;
Begin
  Result := High(TAdvIntegerMatchItems);
End;


Function TAdvIntegerMatch.Find(Const aKey: TAdvIntegerMatchKey; Const aValue: TAdvIntegerMatchValue; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Var
  aItem : TAdvIntegerMatchItem;
Begin
  aItem.Key := aKey;
  aItem.Value := aValue;

  Result := Inherited Find(@aItem, iIndex, aCompare);
End;


Function TAdvIntegerMatch.FindByKey(Const aKey: TAdvIntegerMatchKey; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Begin
  Result := Find(aKey, 0, iIndex, aCompare);
End;


Procedure TAdvIntegerMatch.SortedByKey;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByKey);
End;


Procedure TAdvIntegerMatch.SortedByValue;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByValue);
End;


Procedure TAdvIntegerMatch.SortedByKeyValue;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByKeyValue);
End;


Procedure TAdvIntegerMatch.ForceIncrementByKey(Const aKey: TAdvIntegerMatchKey);
Var
  iIndex : Integer;
Begin
  If Not FindByKey(aKey, iIndex) Then
    Insert(iIndex, aKey, 1)
  Else
    ValueByIndex[iIndex] := ValueByIndex[iIndex] + 1;
End;


Procedure TAdvIntegerMatch.DeleteByKey(aKey: TAdvIntegerMatchKey);
Begin
  DeleteByIndex(IndexByKey(aKey));
End;


Function TAdvIntegerMatch.EqualTo(Const oIntegerMatch : TAdvIntegerMatch) : Boolean;
Var
  aPair : TAdvIntegerMatchItem;
  iIndex : Integer;
Begin
  Result := oIntegerMatch.Count = Count;

  iIndex := 0;

  While Result And ExistsByIndex(iIndex) Do
  Begin
    aPair := Pairs[iIndex];

    Result := oIntegerMatch.ExistsByKeyValue(aPair.Key, aPair.Value);

    Inc(iIndex);
  End;
End;


End. // AdvIntegerMatches //
