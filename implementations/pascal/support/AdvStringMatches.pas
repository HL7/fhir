Unit AdvStringMatches;

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
  MathSupport, MemorySupport, StringSupport,
  AdvObjects, AdvItems, AdvFilers;


Type
  TAdvStringMatchKey = String;
  TAdvStringMatchValue = String;

  TAdvStringMatchItem = Record
    Key : TAdvStringMatchKey;
    Value : TAdvStringMatchValue;
  End;

  PAdvStringMatchItem = ^TAdvStringMatchItem;

  TAdvStringMatchItems = Array[0..(MaxInt Div SizeOf(TAdvStringMatchItem)) - 1] Of TAdvStringMatchItem;
  PAdvStringMatchItems = ^TAdvStringMatchItems;

  TAdvStringMatch = Class(TAdvItems)
    Private
      FMatchArray : PAdvStringMatchItems;
      FDefaultValue : TAdvStringMatchValue;
      FSymbol : String;
      FSeparator : String;
      FForced : Boolean;

      Function GetKeyByIndex(iIndex: Integer): TAdvStringMatchKey;
      Procedure SetKeyByIndex(iIndex: Integer; Const aKey : TAdvStringMatchKey);

      Function GetValueByIndex(iIndex: Integer): TAdvStringMatchValue;
      Procedure SetValueByIndex(iIndex: Integer; Const aValue: TAdvStringMatchValue);

      Function GetMatchByIndex(Const iIndex : Integer) : TAdvStringMatchItem;
      Procedure SetMatchByIndex(Const iIndex : Integer; Const Value : TAdvStringMatchItem);

      Function GetMatch(Const aKey : TAdvStringMatchKey): TAdvStringMatchValue;
      Procedure SetMatch(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue);

    Protected
      Function CapacityLimit : Integer; Override;

      Function ErrorClass : EAdvExceptionClass; Override;

      Function GetItem(iIndex : Integer) : Pointer; Override;
      Procedure SetItem(iIndex : Integer; pValue: Pointer); Override;

      Procedure SaveItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure LoadItem(oFiler : TAdvFiler; iIndex : Integer); Override;
      Procedure AssignItem(oItems : TAdvItems; iIndex : Integer); Override;

      Procedure InternalTruncate(iValue : Integer); Override;
      Procedure InternalResize(iValue : Integer); Override;
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Override;
      Procedure InternalEmpty(iIndex, iLength : Integer); Override;
      Procedure InternalInsert(iIndex : Integer); Overload; Override;
      Procedure InternalDelete(iIndex : Integer); Overload; Override;
      Procedure InternalExchange(iA, iB : Integer); Overload; Override;

      Function CompareByCaseSensitiveKey(pA, pB : Pointer): Integer; Virtual;      
      Function CompareByKey(pA, pB : Pointer): Integer; Virtual;
      Function CompareByValue(pA, pB : Pointer): Integer; Virtual;
      Function CompareMatch(pA, pB: Pointer): Integer; Virtual;

      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Overload; Override;

      Function Find(Const aKey : TAdvStringMatchKey; Const aValue: TAdvStringMatchValue; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; Overload; 

      Function GetAsText : String; 
      Procedure SetAsText(Const sValue: String); 

      Function DefaultValue(Const aKey : TAdvStringMatchKey) : TAdvStringMatchValue; Virtual; 

    Public
      Constructor Create; Override;

      Procedure Assign(oObject : TAdvObject); Override;

      Function Link : TAdvStringMatch;
      Function Clone : TAdvStringMatch; 

      Procedure AddAll(oStringMatch : TAdvStringMatch);

      Function IndexByKey(Const aKey : TAdvStringMatchKey) : Integer;
      Function IndexByCaseSensitiveKey(Const aKey : TAdvStringMatchKey) : Integer;
      Function IndexByValue(Const aValue : TAdvStringMatchValue) : Integer;
      Function ForceByKey(Const aKey : TAdvStringMatchKey) : TAdvStringMatchValue;
      Function ExistsByKey(Const aKey : TAdvStringMatchKey) : Boolean;
      Procedure DeleteByKey(Const aKey : TAdvStringMatchKey);
      Function ExistsByValue(Const aValue : TAdvStringMatchValue) : Boolean;
      Function ExistsByKeyAndValue(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue) : Boolean;

      Function IndexOf(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue) : Integer;
      Function Add(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue): Integer;
      Procedure Insert(iIndex : Integer; Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue);

      Function EqualTo(oMatch : TAdvStringMatch) : Boolean;

      Function GetValueByKey(Const aKey : TAdvStringMatchKey): TAdvStringMatchValue;
      Procedure SetValueByKey(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue);

      Procedure SortedByCaseSensitiveKey;
      Procedure SortedByKey;
      Procedure SortedByValue;

      Property Matches[Const aKey : TAdvStringMatchKey] : TAdvStringMatchValue Read GetMatch Write SetMatch; Default;
      Property Forced : Boolean Read FForced Write FForced;
      Property Default : TAdvStringMatchValue Read FDefaultValue Write FDefaultValue;
      Property Symbol : String Read FSymbol Write FSymbol;
      Property Separator : String Read FSeparator Write FSeparator;
      Property MatchByIndex[Const iIndex : Integer] : TAdvStringMatchItem Read GetMatchByIndex Write SetMatchByIndex;
      Property KeyByIndex[iIndex : Integer] : TAdvStringMatchKey Read GetKeyByIndex Write SetKeyByIndex;
      Property ValueByIndex[iIndex : Integer] : TAdvStringMatchValue Read GetValueByIndex Write SetValueByIndex;
      Property Keys[iIndex : Integer] : TAdvStringMatchKey Read GetKeyByIndex Write SetKeyByIndex;
      Property Values[iIndex : Integer] : TAdvStringMatchValue Read GetValueByIndex Write SetValueByIndex;
      Property AsText : String Read GetAsText Write SetAsText;
  End;

  EAdvStringMatch = Class(EAdvItems);


Implementation


Constructor TAdvStringMatch.Create;
Begin 
  Inherited;

  FSymbol := cReturn;
  FSeparator := '=';
End;  


Procedure TAdvStringMatch.Assign(oObject: TAdvObject);
Begin 
  Inherited;

  FDefaultValue := TAdvStringMatch(oObject).FDefaultValue;
  FForced := TAdvStringMatch(oObject).FForced;
  FSymbol := TAdvStringMatch(oObject).FSymbol;
End;  


Function TAdvStringMatch.Link : TAdvStringMatch;
Begin 
  Result := TAdvStringMatch(Inherited Link);
End;  


Function TAdvStringMatch.Clone : TAdvStringMatch;
Begin 
  Result := TAdvStringMatch(Inherited Clone);
End;  


Procedure TAdvStringMatch.DefaultCompare(Out aCompare : TAdvItemsCompare);
Begin 
  aCompare := CompareByKey;
End;  


Function TAdvStringMatch.CompareMatch(pA, pB: Pointer): Integer;
Begin 
  Result := StringCompare(PAdvStringMatchItem(pA)^.Key, PAdvStringMatchItem(pB)^.Key);

  If Result = 0 Then
    Result := StringCompare(PAdvStringMatchItem(pA)^.Value, PAdvStringMatchItem(pB)^.Value);
End;  


Function TAdvStringMatch.CompareByKey(pA, pB: Pointer): Integer;
Begin 
  Result := StringCompareInsensitive(PAdvStringMatchItem(pA)^.Key, PAdvStringMatchItem(pB)^.Key);
End;


Function TAdvStringMatch.CompareByValue(pA, pB: Pointer): Integer;
Begin
  Result := StringCompareInsensitive(PAdvStringMatchItem(pA)^.Value, PAdvStringMatchItem(pB)^.Value);
End;


Function TAdvStringMatch.CompareByCaseSensitiveKey(pA, pB: Pointer): Integer;
Begin
  Result := StringCompareSensitive(PAdvStringMatchItem(pA)^.Key, PAdvStringMatchItem(pB)^.Key);
End;


Procedure TAdvStringMatch.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Var
  sKey : TAdvStringMatchKey;
  sValue : TAdvStringMatchValue;
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineString(sKey);
  oFiler['Value'].DefineString(sValue);

  oFiler['Match'].DefineEnd;

  Add(sKey, sValue);
End;  


Procedure TAdvStringMatch.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
  oFiler['Match'].DefineBegin;

  oFiler['Key'].DefineString(FMatchArray^[iIndex].Key);
  oFiler['Value'].DefineString(FMatchArray^[iIndex].Value);

  oFiler['Match'].DefineEnd;
End;  


Procedure TAdvStringMatch.AssignItem(oItems : TAdvItems; iIndex: Integer);
Begin 
  Inherited;

  FMatchArray^[iIndex] := TAdvStringMatch(oItems).FMatchArray^[iIndex];
End;  


Procedure TAdvStringMatch.InternalEmpty(iIndex, iLength : Integer);
Begin 
  Inherited;

  MemoryZero(Pointer(NativeUInt(FMatchArray) + NativeUInt(iIndex * SizeOf(TAdvStringMatchItem))), (iLength * SizeOf(TAdvStringMatchItem)));
End;  


Procedure TAdvStringMatch.InternalTruncate(iValue : Integer);
Begin
  Inherited;

  // finalize the strings that will be truncated.
  If iValue < Count Then
    Finalize(FMatchArray^[iValue], Count - iValue);
End;


Procedure TAdvStringMatch.InternalResize(iValue : Integer);
Begin
  Inherited;

  MemoryResize(FMatchArray, Capacity * SizeOf(TAdvStringMatchItem), iValue * SizeOf(TAdvStringMatchItem));
End;


Procedure TAdvStringMatch.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
  MemoryMove(@FMatchArray^[iSource], @FMatchArray^[iTarget], iCount * SizeOf(TAdvStringMatchItem));
End;  


Function TAdvStringMatch.IndexByKey(Const aKey: TAdvStringMatchKey): Integer;
Begin 
  If Not Find(aKey, '', Result, CompareByKey) Then
    Result := -1;
End;


Function TAdvStringMatch.IndexByCaseSensitiveKey(Const aKey: TAdvStringMatchKey): Integer;
Begin
  If Not Find(aKey, '', Result, CompareByCaseSensitiveKey) Then
    Result := -1;
End;  


Function TAdvStringMatch.IndexByValue(Const aValue: TAdvStringMatchValue): Integer;
Begin 
  If Not Find('', aValue, Result, CompareByValue) Then
    Result := -1;
End;  


Function TAdvStringMatch.IndexOf(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue): Integer;
Begin
  If Not Find(aKey, aValue, Result, CompareMatch) Then
    Result := -1;
End;


Function TAdvStringMatch.ExistsByKeyAndValue(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue) : Boolean;
Begin
  Result := IndexOf(aKey, aValue) >= 0;
End;


Function TAdvStringMatch.ExistsByKey(Const aKey: TAdvStringMatchKey): Boolean;
Begin
  Result := IndexByKey(aKey) >= 0;
End;


Procedure TAdvStringMatch.DeleteByKey(Const aKey : TAdvStringMatchKey);
Var
  iIndex : Integer;
Begin
  iIndex := IndexByKey(aKey);
  If ExistsByIndex(iIndex) Then
    DeleteByIndex(iIndex)
  Else If Not Forced Then
    Error('DeleteByKey', StringFormat('Unable to find a value for the specified key ''%s''.', [aKey]));
End;



Function TAdvStringMatch.ExistsByValue(Const aValue: TAdvStringMatchValue): Boolean;
Begin
  Result := IndexByValue(aValue) >= 0;
End;


Function TAdvStringMatch.Add(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue) : Integer;
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


Procedure TAdvStringMatch.Insert(iIndex : Integer; Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue);
Begin 
  InternalInsert(iIndex);

  FMatchArray^[iIndex].Key := aKey;
  FMatchArray^[iIndex].Value := aValue;
End;  


Procedure TAdvStringMatch.InternalInsert(iIndex : Integer);
Begin 
  Inherited;

  Pointer(FMatchArray^[iIndex].Key) := Nil;
  Pointer(FMatchArray^[iIndex].Value) := Nil;
End;  


Procedure TAdvStringMatch.InternalDelete(iIndex : Integer);
Begin 
  Inherited;

  Finalize(FMatchArray^[iIndex]);  
End;  


Procedure TAdvStringMatch.InternalExchange(iA, iB: Integer);
Var
  aTemp : TAdvStringMatchItem;
  pA    : PAdvStringMatchItem;
  pB    : PAdvStringMatchItem;
Begin 
  pA := @FMatchArray^[iA];
  pB := @FMatchArray^[iB];

  aTemp := pA^;
  pA^ := pB^;
  pB^ := aTemp;
End;  


Function TAdvStringMatch.GetItem(iIndex : Integer) : Pointer;
Begin 
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FMatchArray^[iIndex];
End;  


Procedure TAdvStringMatch.SetItem(iIndex: Integer; pValue: Pointer);
Begin 
  Assert(ValidateIndex('SetItem', iIndex));

  FMatchArray^[iIndex] := PAdvStringMatchItem(pValue)^;
End;  


Function TAdvStringMatch.GetKeyByIndex(iIndex : Integer): TAdvStringMatchKey;
Begin 
  Assert(ValidateIndex('GetKeyByIndex', iIndex));

  Result := FMatchArray^[iIndex].Key;
End;  


Procedure TAdvStringMatch.SetKeyByIndex(iIndex : Integer; Const aKey : TAdvStringMatchKey);
Begin 
  Assert(ValidateIndex('SetKeyByIndex', iIndex));

  FMatchArray^[iIndex].Key := aKey;
End;  


Function TAdvStringMatch.GetValueByIndex(iIndex : Integer): TAdvStringMatchValue;
Begin 
  Assert(ValidateIndex('GetValueByIndex', iIndex));

  Result := FMatchArray^[iIndex].Value;
End;  


Procedure TAdvStringMatch.SetValueByIndex(iIndex : Integer; Const aValue: TAdvStringMatchValue);
Begin 
  Assert(ValidateIndex('SetValueByIndex', iIndex));

  FMatchArray^[iIndex].Value := aValue;
End;  


Function TAdvStringMatch.GetValueByKey(Const aKey : TAdvStringMatchKey): TAdvStringMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else If FForced Then
    Result := DefaultValue(aKey)
  Else
  Begin
    Error('GetMatch', StringFormat('Unable to get the value for the specified key ''%s''.', [aKey]));
    Result := '';
  End;
End;  


Procedure TAdvStringMatch.SetValueByKey(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue);
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


Function TAdvStringMatch.GetAsText : String;
Var
  iLoop : Integer;
Begin 
  Result := '';
  For iLoop := 0 To Count - 1 Do
    Result := Result + KeyByIndex[iLoop] + FSeparator + ValueByIndex[iLoop] + FSymbol;
End;  


Procedure TAdvStringMatch.SetAsText(Const sValue: String);
Var
  sTemp   : String;
  sItem   : String;
  iPos    : Integer;
  iSymbol : Integer;
  sLeft   : String;
  sRight  : String;
Begin 
  Clear;

  sTemp := sValue;
  iPos := Pos(FSymbol, sTemp);
  iSymbol := Length(FSymbol);

  While (iPos > 0) Do
  Begin 
    sItem := System.Copy(sTemp, 1, iPos - 1);

    If StringSplit(sItem, FSeparator, sLeft, sRight) Then
      Add(sLeft, sRight)
    Else
      Add(sItem, '');

    System.Delete(sTemp, 1, iPos + iSymbol - 1);

    iPos := Pos(FSymbol, sTemp);
  End;

  If sTemp <> '' Then
  Begin
    If StringSplit(sTemp, FSeparator, sLeft, sRight) Then
      Add(sLeft, sRight)
    Else
      Add(sTemp, '');
  End;  
End;  


Function TAdvStringMatch.CapacityLimit : Integer;
Begin 
  Result := High(TAdvStringMatchItems);
End;  


Function TAdvStringMatch.Find(Const aKey : TAdvStringMatchKey; Const aValue: TAdvStringMatchValue; Out iIndex: Integer; aCompare: TAdvItemsCompare): Boolean;
Var
  aItem : TAdvStringMatchItem;
Begin 
  aItem.Key := aKey;
  aItem.Value := aValue;

  Result := Inherited Find(@aItem, iIndex, aCompare);
End;  


Function TAdvStringMatch.DefaultValue(Const aKey: TAdvStringMatchKey): TAdvStringMatchValue;
Begin 
  Result := FDefaultValue;
End;  


Function TAdvStringMatch.ForceByKey(Const aKey: TAdvStringMatchKey): TAdvStringMatchValue;
Var
  iIndex : Integer;
Begin 
  If Not Find(aKey, '', iIndex, CompareByKey) Then
    Insert(iIndex, aKey, DefaultValue(aKey));

  Result := ValueByIndex[iIndex];
End;  


Function TAdvStringMatch.EqualTo(oMatch: TAdvStringMatch): Boolean;
Var
  iLoop : Integer;
  iIndex : Integer;
Begin 
  Result := Count = oMatch.Count;
  iLoop := 0;

  If Not IsAllowDuplicates Then
  Begin 
    While Result And (iLoop < oMatch.Count) Do
    Begin 
      iIndex := IndexByKey(oMatch.KeyByIndex[iLoop]);

      Result := ExistsByIndex(iIndex) And StringEquals(oMatch.ValueByIndex[iLoop], ValueByIndex[iIndex]);

      Inc(iLoop);
    End;  
  End   
  Else
  Begin 
    Error('EqualTo', 'Equality checking not available to duplicate allowing string matches.');
  End;  
End;  


Procedure TAdvStringMatch.SortedByKey;
Begin
  SortedBy(CompareByKey);
End;


Procedure TAdvStringMatch.SortedByCaseSensitiveKey;
Begin
  SortedBy(CompareByCaseSensitiveKey);
End;


Procedure TAdvStringMatch.SortedByValue;
Begin
  SortedBy(CompareByValue);
End;


Function TAdvStringMatch.GetMatchByIndex(Const iIndex : Integer) : TAdvStringMatchItem;
Begin
  Result := PAdvStringMatchItem(Inherited ItemByIndex[iIndex])^;
End;


Procedure TAdvStringMatch.SetMatchByIndex(Const iIndex : Integer; Const Value : TAdvStringMatchItem);
Begin
  PAdvStringMatchItem(Inherited ItemByIndex[iIndex])^ := Value;
End;


Function TAdvStringMatch.ErrorClass: EAdvExceptionClass;
Begin
  Result := EAdvStringMatch;
End;


Procedure TAdvStringMatch.AddAll(oStringMatch: TAdvStringMatch);
Var
  iIndex : Integer;
Begin
  Assert(Condition(oStringMatch <> Self, 'AddAll', 'Cannot add all items from a list to itself.'));

  Capacity := IntegerMax(Count + oStringMatch.Count, Capacity);

  For iIndex := 0 To oStringMatch.Count - 1 Do
    Add(oStringMatch.KeyByIndex[iIndex], oStringMatch.ValueByIndex[iIndex]);
End;


Function TAdvStringMatch.GetMatch(Const aKey : TAdvStringMatchKey): TAdvStringMatchValue;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexByKey(aKey);

  If ExistsByIndex(iIndex) Then
    Result := ValueByIndex[iIndex]
  Else If FForced Then
    Result := DefaultValue(aKey)
  Else
  Begin
    Error('GetMatch', StringFormat('Unable to get the value for the specified key ''%s''.', [aKey]));
    Result := '';
  End;
End;


Procedure TAdvStringMatch.SetMatch(Const aKey : TAdvStringMatchKey; Const aValue : TAdvStringMatchValue);
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



End. // AdvStringMatches //
