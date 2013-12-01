Unit AdvItems;

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
  MathSupport, StringSupport,
  AdvObjects, AdvExceptions, AdvFilers, AdvCollections;


Type
  TAdvItemsCompare = Function (pA, pB : Pointer) : Integer Of Object;
  PAdvItemsCompare = ^TAdvItemsCompare;

  TAdvItemsDuplicates = (dupAccept, dupIgnore, dupException);

  TAdvItemsDirection = Integer;

  TAdvItemList = Class(TAdvCollection)
    Private
      FCount : Integer;
      FCapacity : Integer;
      FSorted : Boolean;
      FComparison : TAdvItemsCompare;
      FDuplicates : TAdvItemsDuplicates;
      FDirection : TAdvItemsDirection;

    Protected
      Function ErrorClass : EAdvExceptionClass; Overload; Override;

      Function ValidateIndex(Const sMethod : String; iIndex : Integer) : Boolean; Virtual;

      Procedure SetCapacity(Const iValue: Integer); Virtual;
      Procedure SetCount(Const iValue : Integer); Virtual;

      Function GetItem(iIndex : Integer) : Pointer; Virtual; Abstract;
      Procedure SetItem(iIndex : Integer; pValue : Pointer); Virtual; Abstract;

      Procedure LoadItem(oFiler : TAdvFiler; iIndex : Integer); Virtual;
      Procedure SaveItem(oFiler : TAdvFiler; iIndex : Integer); Virtual;
      Procedure AssignItem(oItems : TAdvItemList; iIndex : Integer); Virtual;

      Function CompareItem(pA, pB : Pointer) : Integer; Virtual;
      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Virtual;
      Function Find(pValue : Pointer; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil): Boolean; Overload; Virtual; 

      Procedure DirectionBy(Const Value : TAdvItemsDirection); Virtual;
      Procedure DuplicateBy(Const Value : TAdvItemsDuplicates); Virtual;

      Procedure SortedBy(Const bValue : Boolean); Overload; Virtual;

      Procedure InternalGrow;
      Procedure InternalInsert(iIndex : Integer); Virtual;
      Procedure InternalResize(iValue : Integer); Virtual;
      Procedure InternalTruncate(iValue : Integer); Virtual;      
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Virtual;
      Procedure InternalEmpty(iIndex, iLength : Integer); Virtual;
      Procedure InternalExchange(iA, iB : Integer); Virtual;
      Procedure InternalDelete(iIndex : Integer); Virtual;

      Procedure InternalClear; Override;
      Procedure InternalSort; Overload;
      Procedure InternalSort(aCompare : TAdvItemsCompare; iDirection : TAdvItemsDirection = 0); Overload;

      Function Insertable(Const sMethod : String; iIndex : Integer) : Boolean; Overload; Virtual;
      Function Deleteable(Const sMethod : String; iIndex : Integer) : Boolean; Overload; Virtual;
      Function Deleteable(Const sMethod : String; iFromIndex, iToIndex : Integer) : Boolean; Overload; Virtual;
      Function Replaceable(Const sMethod : String; iIndex : Integer) : Boolean; Overload; Virtual;
      Function Extendable(Const sMethod : String; iCount : Integer) : Boolean; Overload; Virtual;

      // Attribute: items are allowed to be replaced with new items.
      Function Replacable : Boolean; Virtual;

      Function CapacityLimit : Integer; Virtual;
      Function CountLimit : Integer; Virtual;

      Property ItemByIndex[iIndex : Integer] : Pointer Read GetItem Write SetItem; Default;
      Property DefaultComparison : TAdvItemsCompare Read FComparison;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Assign(oSource : TAdvObject); Override;

      Procedure Load(oFiler : TAdvFiler); Override;
      Procedure Save(oFiler : TAdvFiler); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Procedure DeleteByIndex(iIndex : Integer); Virtual;
      Procedure DeleteRange(iFromIndex, iToIndex: Integer);
      Function ExistsByIndex(Const iIndex : Integer) : Boolean;
      Procedure Exchange(iA, iB : Integer); Virtual;

      Procedure IgnoreDuplicates;
      Procedure AllowDuplicates;
      Procedure PreventDuplicates;

      Function IsIgnoreDuplicates : Boolean;
      Function IsAllowDuplicates : Boolean;
      Function IsPreventDuplicates : Boolean;

      Function IsComparedBy(Const aCompare : TAdvItemsCompare) : Boolean;
      Procedure ComparedBy(Const Value : TAdvItemsCompare);

      Procedure Uncompared;
      Function IsCompared : Boolean;
      Function IsUnCompared : Boolean;

      Function IsSortedBy(Const aCompare : TAdvItemsCompare) : Boolean;
      Procedure SortedBy(Const aCompare : TAdvItemsCompare); Overload; Virtual;

      Procedure SortAscending;
      Procedure SortDescending;

      Function IsSortAscending : Boolean;
      Function IsSortDescending : Boolean;

      Procedure Sorted;
      Procedure Unsorted;
      Function IsSorted : Boolean;
      Function IsUnsorted : Boolean;

      Function IsOrderedBy(Const Value : TAdvItemsCompare) : Boolean;
      Procedure OrderedBy(Const Value : TAdvItemsCompare);
      Procedure Unordered;

      Function IsEmpty : Boolean;

      Property Count : Integer Read FCount Write SetCount;
      Property Capacity : Integer Read FCapacity Write SetCapacity;
  End;

  EAdvItemList = Class(EAdvCollection);

  TAdvItems = TAdvItemList;
  EAdvItems = EAdvItemList;


Implementation


Type
  TMethod = Record
    Code, Data : Pointer;
  End;


Constructor TAdvItemList.Create;
Begin
  Inherited;

  DefaultCompare(FComparison);

  FDirection := 1;
End;


Destructor TAdvItemList.Destroy;
Begin
  InternalResize(0);

  Inherited;
End;


Function TAdvItemList.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvItemList;
End;  


Procedure TAdvItemList.Assign(oSource: TAdvObject);
Var
  iLoop : Integer;
Begin 
  Inherited;

  Clear;

  Assert(Condition(Count = 0, 'Assign', 'Collection must be empty of all items after clear.'));   

  Count := TAdvItemList(oSource).Count;

  For iLoop := 0 To Count - 1 Do
    AssignItem(TAdvItemList(oSource), iLoop);

  If Count > 0 Then
  Begin 
    // TODO: below reflects what is currently happening due to AssignItem. This
    //       should be changed to use an 'Add' approach similar to LoadItem so that
    //       you can assign from one ordered list into another and retain individual ordering rules.

    // Comparison is set this way because we can't directly assign a method pointer off another object.
    TMethod(FComparison).Data := Self;
    TMethod(FComparison).Code := TMethod(TAdvItemList(oSource).FComparison).Code;

    FSorted := TAdvItemList(oSource).FSorted;
    FDirection := TAdvItemList(oSource).FDirection;
    FDuplicates := TAdvItemList(oSource).FDuplicates;
  End;  
End;  


Procedure TAdvItemList.Load(oFiler : TAdvFiler);
Begin 
  Assert(Invariants('Load', oFiler, TAdvFiler, 'oFiler'));

  Clear;

  Define(oFiler);

  oFiler['Items'].DefineBegin;

  While oFiler.Peek <> atEnd Do
    LoadItem(oFiler, Count);

  oFiler['Items'].DefineEnd;
End;  


Procedure TAdvItemList.Save(oFiler : TAdvFiler);
Var
  iLoop  : Integer;
Begin 
  Assert(Invariants('Save', oFiler, TAdvFiler, 'oFiler'));

  Define(oFiler);

  oFiler['Items'].DefineBegin;

  For iLoop := 0 To Count - 1 Do
    SaveItem(oFiler, iLoop);

  oFiler['Items'].DefineEnd;
End;  


Procedure TAdvItemList.Define(oFiler: TAdvFiler);
Begin 
  Inherited;
End;  


Procedure TAdvItemList.LoadItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
End;  


Procedure TAdvItemList.SaveItem(oFiler: TAdvFiler; iIndex: Integer);
Begin 
End;  


Procedure TAdvItemList.AssignItem(oItems : TAdvItemList; iIndex: Integer);
Begin 
End;  


Function TAdvItemList.ExistsByIndex(Const iIndex : Integer) : Boolean;
Begin
  Result := (iIndex >= 0) And (iIndex < FCount);
End;


Function TAdvItemList.ValidateIndex(Const sMethod: String; iIndex: Integer) : Boolean;
Begin 
  If Not ExistsByIndex(iIndex) Then
    Invariant(sMethod, StringFormat('Invalid index (%d In [0..%d])', [iIndex, FCount - 1]));

  Result := True;
End;  


Procedure TAdvItemList.InternalClear;
Begin
  Inherited;

  InternalTruncate(0);
  FCount := 0;
End;


Procedure TAdvItemList.InternalGrow;
Var
  iDelta : Integer;
Begin 
  If FCapacity > 64 Then           // FCapacity : iDelta
    iDelta := FCapacity Div 4      // >  64     : >16
  Else If FCapacity > 8 Then
    iDelta := 16                   // <= 64     : +16
  Else
    iDelta := 4;                   // <=  8     : +4

  SetCapacity(FCapacity + iDelta);
End;  


Procedure TAdvItemList.InternalEmpty(iIndex, iLength: Integer);
Begin 
End;  


Procedure TAdvItemList.InternalResize(iValue : Integer);
Begin
End;


Procedure TAdvItemList.InternalTruncate(iValue: Integer);
Begin
End;


Procedure TAdvItemList.InternalCopy(iSource, iTarget, iCount: Integer);
Begin 
End;  


Procedure TAdvItemList.InternalSort;

  Procedure QuickSort(L, R: Integer);
  Var
    I, J, K : Integer;
    pK : Pointer;
  Begin
    // QuickSort routine (Recursive)
    // * Items is the default indexed property that returns a pointer, subclasses
    //   specify these return values as their default type.
    // * The Compare routine used must be aware of what this pointer actually means.

    Repeat
      I := L;
      J := R;
      K := (L + R) Shr 1;

      Repeat
        // Keep pK pointed at the middle element as it might be moved.
        pK := ItemByIndex[K];

        While (FComparison(ItemByIndex[I], pK) * FDirection < 0) Do
          Inc(I);

        While (FComparison(ItemByIndex[J], pK) * FDirection > 0) Do
          Dec(J);

        If I <= J Then
        Begin
          InternalExchange(I, J);

          // Keep K as the index of the original middle element as it might get exchanged.
          If I = K Then
            K := J
          Else If J = K Then
            K := I;

          Inc(I);
          Dec(J);
        End;
      Until I > J;

      If L < J Then
        QuickSort(L, J);

      L := I;
    Until I >= R;
  End;  

Begin 
  Assert(Condition(Assigned(FComparison), 'Sort', 'Comparison property must be assigned.'));
  Assert(Condition(FDirection <> 0, 'Sort', 'Direction must be non-zero'));

  If (FCount > 1) Then
    QuickSort(0, FCount - 1);              // call the quicksort routine
End;  


Procedure TAdvItemList.InternalSort(aCompare: TAdvItemsCompare; iDirection : TAdvItemsDirection);
Begin 
  Assert(Condition(Assigned(aCompare), 'Sort', 'Comparison parameter must be assigned.'));

  If iDirection <> 0 Then
    FDirection := iDirection;

  FComparison := aCompare;

  FSorted := False;
  InternalSort;
  FSorted := True;
End;  


Function TAdvItemList.Find(pValue : Pointer; Out iIndex: Integer; aCompare : TAdvItemsCompare): Boolean;
Var
  L, H, I, C : Integer;
Begin
  // Ensure we have a compare event specified
  If Not Assigned(aCompare) Then
    aCompare := FComparison;

  If Not IsSortedBy(aCompare) Then
  Begin
    iIndex := 0;
    While (iIndex < FCount) And (aCompare(ItemByIndex[iIndex], pValue) <> 0) Do
      Inc(iIndex);

    Result := iIndex < FCount; // iIndex will be FCount if it doesn't find the Item
  End
  Else
  Begin
    Result := False;
    L := 0;
    H := FCount - 1;

    While L <= H Do
    Begin
      I := (L + H) Shr 1;
      C := aCompare(ItemByIndex[I], pValue) * FDirection;

      If C < 0 Then
        L := I + 1
      Else
      Begin 
        H := I - 1;

        If C = 0 Then
        Begin 
          Result := True;

          If FDuplicates <> dupAccept Then
            L := I;
        End;  
      End;  
    End;  

    iIndex := L;
  End;  
End;  


Function TAdvItemList.CompareItem(pA, pB : Pointer): Integer;
Begin 
  Result := IntegerCompare(Integer(pA), Integer(pB));
End;  


Procedure TAdvItemList.DefaultCompare(Out aCompare : TAdvItemsCompare);
Begin 
  aCompare := {$IFDEF FPC}@{$ENDIF}CompareItem;
End;  


Procedure TAdvItemList.InternalInsert(iIndex : Integer);
Begin 
  // Insert is allowed one element past the end of the items
  Assert(Insertable('InternalInsert', iIndex));

  If FCount >= CountLimit Then
    Invariant('InternalInsert', StringFormat('Cannot set count to %d as it not compatible with the range [0..%d]', [FCount+1, CountLimit]));

  If FCount >= FCapacity Then
    InternalGrow;

  If iIndex < FCount Then
    InternalCopy(iIndex, iIndex + 1, FCount - iIndex);

  Inc(FCount);
End;


Procedure TAdvItemList.InternalDelete(iIndex : Integer);
Begin 
End;  


Procedure TAdvItemList.DeleteByIndex(iIndex: Integer);
Begin
  Assert(Deleteable('DeleteByIndex', iIndex));

  InternalDelete(iIndex);

  Dec(FCount);

  If iIndex < FCount Then
    InternalCopy(iIndex + 1, iIndex, FCount - iIndex);
End;  


Procedure TAdvItemList.DeleteRange(iFromIndex, iToIndex : Integer);
Var
  iLoop : Integer;
Begin 
  Assert(Deleteable('DeleteRange', iFromIndex, iToIndex));

  For iLoop := iFromIndex To iToIndex Do
    InternalDelete(iLoop);

  If iToIndex < FCount Then
    InternalCopy(iToIndex + 1, iFromIndex, FCount - iToIndex - 1);

  Dec(FCount, iToIndex - iFromIndex + 1);
End;  


Procedure TAdvItemList.InternalExchange(iA, iB : Integer);
Var
  pTemp : Pointer;
Begin 
  pTemp := ItemByIndex[iA];
  ItemByIndex[iA] := ItemByIndex[iB];
  ItemByIndex[iB] := pTemp;
End;  


Procedure TAdvItemList.SetCapacity(Const iValue: Integer);
Begin 
  Assert(Condition((iValue >= FCount) And (iValue <= CapacityLimit), 'SetCapacity', StringFormat('Unable to change the capacity to %d', [iValue])));

  InternalResize(iValue);
  FCapacity := iValue;
End;  


Procedure TAdvItemList.SetCount(Const iValue: Integer);
Var
  iIndex : Integer;
Begin
  Assert(Extendable('SetCount', iValue));

  // Remove the lost items from the list
  For iIndex := FCount - 1 DownTo iValue Do
    InternalDelete(iIndex);

  If iValue > FCapacity Then
    SetCapacity(iValue);

  // Clear the items which are now in the list
  If iValue > FCount Then
    InternalEmpty(FCount, iValue - FCount);

  FCount := iValue;
End;


Procedure TAdvItemList.SortedBy(Const bValue : Boolean);
Begin

  If FSorted <> bValue Then
  Begin
    FSorted := bValue;

    If FSorted Then
      InternalSort;
  End;
End;


Procedure TAdvItemList.ComparedBy(Const Value: TAdvItemsCompare);
Begin
  Assert(Alterable('ComparedBy'));

  If Not IsComparedBy(Value) Then
  Begin
    FComparison := Value;
    FSorted := False;
  End;
End;


Procedure TAdvItemList.DirectionBy(Const Value: TAdvItemsDirection);
Begin
  Assert(Alterable('DirectionBy'));

  If FDirection <> Value Then
  Begin
    FDirection := Value;
    FSorted := False;
  End;
End;


Procedure TAdvItemList.DuplicateBy(Const Value: TAdvItemsDuplicates);
Begin
  Assert(Alterable('DuplicateBy'));

  FDuplicates := Value;
End;


Function TAdvItemList.CapacityLimit : Integer;
Begin
  Invariant('CapacityLimit', 'CapacityLimit not specified.');

  Result := 0;
End;


Function TAdvItemList.CountLimit : Integer;
Begin
  Result := CapacityLimit;
End;  


Function TAdvItemList.IsComparedBy(Const aCompare: TAdvItemsCompare) : Boolean;
Begin 
  Result := (TMethod(FComparison).Data = TMethod(aCompare).Data) And (TMethod(FComparison).Code = TMethod(aCompare).Code);
End;  


Function TAdvItemList.IsCompared : Boolean;
Begin
  Result := Assigned(@FComparison);
End;  


Function TAdvItemList.IsUncompared : Boolean;
Begin
  Result := Not IsCompared;
End;  


Function TAdvItemList.IsSortedBy(Const aCompare: TAdvItemsCompare) : Boolean;
Begin
  Result := FSorted And IsComparedBy(aCompare);
End;


Procedure TAdvItemList.SortedBy(Const aCompare: TAdvItemsCompare);
Begin 
  ComparedBy(aCompare);
  SortedBy(True);
End;  


Procedure TAdvItemList.AllowDuplicates;
Begin 
  DuplicateBy(dupAccept);
End;  


Procedure TAdvItemList.IgnoreDuplicates;
Begin 
  DuplicateBy(dupIgnore);

  // TODO: Delete duplicates?
End;  


Procedure TAdvItemList.PreventDuplicates;
Begin 
  DuplicateBy(dupException);

  // TODO: Assert that there are no duplicates?
End;  


Function TAdvItemList.IsAllowDuplicates : Boolean;
Begin 
  Result := FDuplicates = dupAccept;
End;  


Function TAdvItemList.IsIgnoreDuplicates : Boolean;
Begin 
  Result := FDuplicates = dupIgnore;
End;  


Function TAdvItemList.IsPreventDuplicates : Boolean;
Begin 
  Result := FDuplicates = dupException;
End;  


Procedure TAdvItemList.Sorted;
Begin 
  SortedBy(True);
End;  


Procedure TAdvItemList.Unsorted;
Begin
  SortedBy(False);
End;


Procedure TAdvItemList.Uncompared;
Begin
  FComparison := Nil;
  FSorted := False;
End;


Procedure TAdvItemList.SortAscending;
Begin 
  DirectionBy(1);
End;  


Procedure TAdvItemList.SortDescending;
Begin 
  DirectionBy(-1);
End;  


Function TAdvItemList.IsSortAscending : Boolean;
Begin 
  Result := FDirection > 0;
End;  


Function TAdvItemList.IsSortDescending : Boolean;
Begin 
  Result := FDirection < 0;
End;  


Function TAdvItemList.IsSorted : Boolean;
Begin 
  Result := FSorted;
End;  


Function TAdvItemList.IsUnsorted : Boolean;
Begin 
  Result := Not FSorted;
End;  


Function TAdvItemList.IsEmpty : Boolean;
Begin 
  Result := FCount <= 0;
End;  


Function TAdvItemList.Deleteable(Const sMethod: String; iIndex: Integer): Boolean;
Begin 
  Result := Alterable(sMethod);

  ValidateIndex(sMethod, iIndex);
End;  


Function TAdvItemList.Deleteable(Const sMethod: String; iFromIndex, iToIndex: Integer): Boolean;
Var
  iLoop : Integer;
Begin 
  Result := Alterable(sMethod);

  ValidateIndex(sMethod, iFromIndex);
  ValidateIndex(sMethod, iToIndex);

  If iFromIndex > iToIndex Then
    Invariant(sMethod, StringFormat('Invalid range for deletion (%d..%d)', [iFromIndex, iToIndex]));

  For iLoop := iFromIndex To iToIndex Do
    Deleteable(sMethod, iLoop);
End;  


Function TAdvItemList.Insertable(Const sMethod: String; iIndex: Integer): Boolean;
Begin 
  Result := Alterable(sMethod);

  If iIndex <> Count Then
    ValidateIndex(sMethod, iIndex);
End;  


Function TAdvItemList.Replaceable(Const sMethod: String; iIndex: Integer): Boolean;
Begin 
  Result := Alterable(sMethod);

  If Not Replacable Then
    Invariant(sMethod, 'List does not allow replacing of items.');
End;  


Function TAdvItemList.Extendable(Const sMethod: String; iCount: Integer): Boolean;
Begin 
  Result := Alterable(sMethod);

  If (iCount < 0) Or (iCount > CountLimit) Then
    Invariant(sMethod, StringFormat('Cannot set count to %d as it not compatible with the range [0..%d]', [iCount, CountLimit]));
End;  


Procedure TAdvItemList.Exchange(iA, iB: Integer);
Begin 
  Assert(ValidateIndex('Exchange', iA));
  Assert(ValidateIndex('Exchange', iB));
  Assert(Condition(iA <> iB, 'Exchange', 'Cannot exchange with the same index position.'));
  Assert(Condition(IsUnsorted, 'Exchange', 'Cannot exchange in sorted items.'));
  Assert(Alterable('Exchange'));

  InternalExchange(iA, iB);
End;  


Function TAdvItemList.Replacable : Boolean;
Begin 
  Result := True;
End; 


Function TAdvItemList.IsOrderedBy(Const Value: TAdvItemsCompare): Boolean;
Var
  iIndex : Integer;
Begin
  Result := True;
  iIndex := 0;
  While (iIndex < Count - 1) And Result Do
  Begin
    Result := Value(ItemByIndex[iIndex], ItemByIndex[iIndex + 1]) * FDirection <= 0;
    Inc(iIndex);
  End;
End;


Procedure TAdvItemList.OrderedBy(Const Value: TAdvItemsCompare);
Begin
  FComparison := Value;
  FSorted := False;
  InternalSort;
End;


Procedure TAdvItemList.Unordered;
Begin
  FComparison := Nil;
  FSorted := False;
End;


End. // AdvItems //
