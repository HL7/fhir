Unit AdvLargeIntegerMatches;

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
  AdvObjects, AdvItems, AdvFilers;


Type
  TAdvLargeIntegerMatchKey = Int64;
  TAdvLargeIntegerMatchValue = Int64;

  TAdvLargeIntegerMatchItem = Record
    Key   : TAdvLargeIntegerMatchKey;
    Value : TAdvLargeIntegerMatchValue;
  End; 

  PAdvLargeIntegerMatchItem = ^TAdvLargeIntegerMatchItem;

  TAdvLargeIntegerMatchItems = Array[0..(MaxInt Div SizeOf(TAdvLargeIntegerMatchItem)) - 1] Of TAdvLargeIntegerMatchItem;
  PAdvLargeIntegerMatchItems = ^TAdvLargeIntegerMatchItems;

  TAdvLargeIntegerMatch = Class(TAdvItems)
    Private
      FMatches : PAdvLargeIntegerMatchItems;
      FDefault : TAdvLargeIntegerMatchValue;
      FForced  : Boolean;

      Function GetKey(iIndex: Integer): TAdvLargeIntegerMatchKey;
      Procedure SetKey(iIndex: Integer; Const iValue: TAdvLargeIntegerMatchKey);

      Function GetValue(iIndex: Integer): TAdvLargeIntegerMatchValue;
      Procedure SetValue(iIndex: Integer; Const iValue: TAdvLargeIntegerMatchValue);

      Function GetMatch(iKey : TAdvLargeIntegerMatchKey): TAdvLargeIntegerMatchValue;
      Procedure SetMatch(iKey: TAdvLargeIntegerMatchKey; Const iValue: TAdvLargeIntegerMatchValue);

      Function GetPair(iIndex: Integer): TAdvLargeIntegerMatchItem;
      Procedure SetPair(iIndex: Integer; Const Value: TAdvLargeIntegerMatchItem);

    Protected
      Function GetItem(iIndex : Integer) : Pointer; Override;
      Procedure SetItem(iIndex: Integer; pValue: Pointer); Override;

      Procedure LoadItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure SaveItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure AssignItem(oItems : TAdvItems; iIndex : Integer); Override;

      Procedure InternalResize(iValue : Integer); Override;
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Override;
      Procedure InternalEmpty(iIndex, iLength : Integer); Override;
      Procedure InternalInsert(iIndex : Integer); Override;
      Procedure InternalExchange(iA, iB : Integer); Override;

      Function CompareByKey(pA, pB : Pointer): Integer; 
      Function CompareByValue(pA, pB : Pointer): Integer; 
      Function CompareByKeyValue(pA, pB : Pointer) : Integer; 

      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Override;

      Function CapacityLimit : Integer; Override;

      Function Find(Const aKey : TAdvLargeIntegerMatchKey; Const aValue: TAdvLargeIntegerMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; 
      Function FindByKey(Const aKey : TAdvLargeIntegerMatchKey; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; 

    Public
      Function Link : TAdvLargeIntegerMatch;

      Function Add(aKey : TAdvLargeIntegerMatchKey; aValue : TAdvLargeIntegerMatchValue): Integer; 
      Procedure Insert(iIndex : Integer; iKey : TAdvLargeIntegerMatchKey; iValue : TAdvLargeIntegerMatchValue); 

      Function IndexByKey(aKey : TAdvLargeIntegerMatchKey) : Integer; 
      Function IndexByKeyValue(Const aKey : TAdvLargeIntegerMatchKey; Const aValue : TAdvLargeIntegerMatchValue) : Integer; 

      Function ExistsByKey(aKey : TAdvLargeIntegerMatchKey) : Boolean; 
      Function ExistsByKeyValue(Const aKey : TAdvLargeIntegerMatchKey; Const aValue : TAdvLargeIntegerMatchValue) : Boolean; 

      Function EqualTo(Const oIntegerMatch : TAdvLargeIntegerMatch) : Boolean;

      Procedure DeleteByKey(aKey : TAdvLargeIntegerMatchKey); 

      Procedure ForceIncrementByKey(Const aKey : TAdvLargeIntegerMatchKey); 

      Procedure SortedByKey; 
      Procedure SortedByValue; 
      Procedure SortedByKeyValue; 

      Property Matches[iKey : TAdvLargeIntegerMatchKey] : TAdvLargeIntegerMatchValue Read GetMatch Write SetMatch; Default;
      Property Keys[iIndex : Integer] : TAdvLargeIntegerMatchKey Read GetKey Write SetKey;
      Property Values[iIndex : Integer] : TAdvLargeIntegerMatchValue Read GetValue Write SetValue;
      Property Pairs[iIndex : Integer] : TAdvLargeIntegerMatchItem Read GetPair Write SetPair;
      Property Forced : Boolean Read FForced Write FForced;
      Property Default : TAdvLargeIntegerMatchValue Read FDefault Write FDefault;
  End;


Implementation


Uses
  MemorySupport, StringSupport, MathSupport;


Function TAdvLargeIntegerMatch.Link : TAdvLargeIntegerMatch;
Begin
  Result := TAdvLargeIntegerMatch(Inherited Link);
End;


Function TAdvLargeIntegerMatch.CompareByKey(pA, pB: Pointer): Integer;
Begin 
  Result := IntegerCompare(PAdvLargeIntegerMatchItem(pA)^.Key, PAdvLargeIntegerMatchItem(pB)^.Key);
End;


Function TAdvLargeIntegerMatch.CompareByValue(pA, pB: Pointer): Integer;
Begin
  Result := IntegerCompare(PAdvLargeIntegerMatchItem(pA)^.Value, PAdvLargeIntegerMatchItem(pB)^.Value);
End;  


Function TAdvLargeIntegerMatch.CompareByKeyValue(pA, pB: Pointer): Integer;
Begin
  Result := CompareByKey(pA, pB);

  If Result = 0 Then
    Result := CompareByValue(pA, pB);
End;


Procedure TAdvLargeIntegerMatch.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin 
  aCompare := {$IFDEF FPC}@{$ENDIF}CompareByKey;
End;  


Procedure TAdvLargeIntegerMatch.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Var
  iKey : TAdvLargeIntegerMatchKey;
  iValue : TAdvLargeIntegerMatchValue;
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineInteger(iKey);
  oFiler['Value'].DefineInteger(iValue);

  oFiler['Match'].DefineEnd;

  Add(iKey, iValue);
End;  


Procedure TAdvLargeIntegerMatch.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineInteger(FMatches^[iIndex].Key);
  oFiler['Value'].DefineInteger(FMatches^[iIndex].Value);

  oFiler['Match'].DefineEnd;
End;  


Procedure TAdvLargeIntegerMatch.AssignItem(oItems: TAdvItems; iIndex: Integer);
Begin 
  Inherited;

  FMatches^[iIndex] := TAdvLargeIntegerMatch(oItems).FMatches^[iIndex];
End;  


Procedure TAdvLargeIntegerMatch.InternalEmpty(iIndex, iLength: Integer);
Begin 
  Inherited;

  MemoryZero(Pointer(NativeUInt(FMatches) + NativeUInt(iIndex * SizeOf(TAdvLargeIntegerMatchItem))), (iLength * SizeOf(TAdvLargeIntegerMatchItem)));
End;  


Procedure TAdvLargeIntegerMatch.InternalResize(iValue : Integer);
Begin 
  Inherited;

  MemoryResize(FMatches, Capacity * SizeOf(TAdvLargeIntegerMatchItem), iValue * SizeOf(TAdvLargeIntegerMatchItem));
End;


Procedure TAdvLargeIntegerMatch.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
  MemoryMove(@FMatches^[iSource], @FMatches^[iTarget], iCount * SizeOf(TAdvLargeIntegerMatchItem));
End;  


Function TAdvLargeIntegerMatch.IndexByKey(aKey : TAdvLargeIntegerMatchKey): Integer;
Begin 
  If Not FindByKey(aKey, Result, {$IFDEF FPC}@{$ENDIF}CompareByKey) Then
    Result := -1;
End;  


Function TAdvLargeIntegerMatch.IndexByKeyValue(Const aKey : TAdvLargeIntegerMatchKey; Const aValue : TAdvLargeIntegerMatchValue) : Integer;
Begin
  If Not Find(aKey, aValue, Result, {$IFDEF FPC}@{$ENDIF}CompareByKeyValue) Then
    Result := -1;
End;


Function TAdvLargeIntegerMatch.ExistsByKey(aKey : TAdvLargeIntegerMatchKey): Boolean;
Begin
  Result := ExistsByIndex(IndexByKey(aKey));
End;  


Function TAdvLargeIntegerMatch.ExistsByKeyValue(Const aKey : TAdvLargeIntegerMatchKey; Const aValue : TAdvLargeIntegerMatchValue) : Boolean;
Begin
  Result := ExistsByIndex(IndexByKeyValue(aKey, aValue));
End;


Function TAdvLargeIntegerMatch.Add(aKey : TAdvLargeIntegerMatchKey; aValue : TAdvLargeIntegerMatchValue): Integer;
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


Procedure TAdvLargeIntegerMatch.Insert(iIndex: Integer; iKey : TAdvLargeIntegerMatchKey; iValue : TAdvLargeIntegerMatchValue);
Begin 
  InternalInsert(iIndex);

  FMatches^[iIndex].Key := iKey;
  FMatches^[iIndex].Value := iValue;
End;  


Procedure TAdvLargeIntegerMatch.InternalInsert(iIndex: Integer);
Begin 
  Inherited;

  FMatches^[iIndex].Key := 0;
  FMatches^[iIndex].Value := 0;
End;  


Procedure TAdvLargeIntegerMatch.InternalExchange(iA, iB : Integer);
Var
  aTemp : TAdvLargeIntegerMatchItem;
  pA    : Pointer;
  pB    : Pointer;
Begin 
  pA := @FMatches^[iA];
  pB := @FMatches^[iB];

  aTemp := PAdvLargeIntegerMatchItem(pA)^;
  PAdvLargeIntegerMatchItem(pA)^ := PAdvLargeIntegerMatchItem(pB)^;
  PAdvLargeIntegerMatchItem(pB)^ := aTemp;
End;  


Function TAdvLargeIntegerMatch.GetItem(iIndex: Integer): Pointer;
Begin 
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FMatches^[iIndex];
End;  


Procedure TAdvLargeIntegerMatch.SetItem(iIndex: Integer; pValue: Pointer);
Begin 
  Assert(ValidateIndex('SetItem', iIndex));

  FMatches^[iIndex] := PAdvLargeIntegerMatchItem(pValue)^;
End;  


Function TAdvLargeIntegerMatch.GetKey(iIndex: Integer): TAdvLargeIntegerMatchKey;
Begin 
  Assert(ValidateIndex('GetKey', iIndex));

  Result := FMatches^[iIndex].Key;
End;  


Procedure TAdvLargeIntegerMatch.SetKey(iIndex: Integer; Const iValue: TAdvLargeIntegerMatchKey);
Begin 
  Assert(ValidateIndex('SetKey', iIndex));

  FMatches^[iIndex].Key := iValue;
End;  


Function TAdvLargeIntegerMatch.GetValue(iIndex: Integer): TAdvLargeIntegerMatchValue;
Begin 
  Assert(ValidateIndex('GetValue', iIndex));

  Result := FMatches^[iIndex].Value;
End;  


Procedure TAdvLargeIntegerMatch.SetValue(iIndex: Integer; Const iValue: TAdvLargeIntegerMatchValue);
Begin 
  Assert(ValidateIndex('SetValue', iIndex));

  FMatches^[iIndex].Value := iValue;
End;  


Function TAdvLargeIntegerMatch.GetPair(iIndex: Integer): TAdvLargeIntegerMatchItem;
Begin 
  Assert(ValidateIndex('GetPair', iIndex));

  Result := FMatches^[iIndex];
End;  


Procedure TAdvLargeIntegerMatch.SetPair(iIndex: Integer; Const Value: TAdvLargeIntegerMatchItem);
Begin 
  Assert(ValidateIndex('SetPair', iIndex));

  FMatches^[iIndex] := Value;
End;  


Function TAdvLargeIntegerMatch.GetMatch(iKey : TAdvLargeIntegerMatchKey): TAdvLargeIntegerMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(iKey);

  If ExistsByIndex(iIndex) Then
    Result := Values[iIndex]
  Else
  Begin 
    Result := FDefault;
    If Not FForced Then
      Error('GetMatch', 'Unable to get the value for the specified key.');
  End;  
End;  


Procedure TAdvLargeIntegerMatch.SetMatch(iKey : TAdvLargeIntegerMatchKey; Const iValue: TAdvLargeIntegerMatchValue);
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(iKey);

  If ExistsByIndex(iIndex) Then
    Values[iIndex] := iValue
  Else If FForced Then
    Add(iKey, iValue)
  Else
    Error('SetMatch', 'Unable to set the value for the specified key.');
End;  


Function TAdvLargeIntegerMatch.CapacityLimit : Integer;
Begin
  Result := High(TAdvLargeIntegerMatchItems);
End;


Function TAdvLargeIntegerMatch.Find(Const aKey: TAdvLargeIntegerMatchKey; Const aValue: TAdvLargeIntegerMatchValue; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Var
  aItem : TAdvLargeIntegerMatchItem;
Begin
  aItem.Key := aKey;
  aItem.Value := aValue;

  Result := Inherited Find(@aItem, iIndex, aCompare);
End;


Function TAdvLargeIntegerMatch.FindByKey(Const aKey: TAdvLargeIntegerMatchKey; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Begin
  Result := Find(aKey, 0, iIndex, aCompare);
End;


Procedure TAdvLargeIntegerMatch.SortedByKey;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByKey);
End;


Procedure TAdvLargeIntegerMatch.SortedByValue;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByValue);
End;


Procedure TAdvLargeIntegerMatch.SortedByKeyValue;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByKeyValue);
End;


Procedure TAdvLargeIntegerMatch.ForceIncrementByKey(Const aKey: TAdvLargeIntegerMatchKey);
Var
  iIndex : Integer;
Begin
  If Not FindByKey(aKey, iIndex) Then
    Insert(iIndex, aKey, 1)
  Else
    Values[iIndex] := Values[iIndex] + 1;
End;


Procedure TAdvLargeIntegerMatch.DeleteByKey(aKey: TAdvLargeIntegerMatchKey);
Begin
  DeleteByIndex(IndexByKey(aKey));
End;


Function TAdvLargeIntegerMatch.EqualTo(Const oIntegerMatch : TAdvLargeIntegerMatch) : Boolean;
Var
  aPair : TAdvLargeIntegerMatchItem;
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


End.
