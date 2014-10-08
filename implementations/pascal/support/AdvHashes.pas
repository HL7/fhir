Unit AdvHashes;

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
  HashSupport, StringSupport, MemorySupport, MathSupport,
  AdvObjects, AdvExceptions, AdvPersistents, AdvPersistentLists, AdvCollections, AdvFilers, AdvIterators;


Type
  TAdvHashEntryCode = Integer;

  TAdvHashEntry = Class(TAdvPersistent)
    Private
      FCode : TAdvHashEntryCode;
      FNextHashEntry : TAdvHashEntry;

    Protected
      Procedure Generate; Virtual;

      Property Code : TAdvHashEntryCode Read FCode Write FCode;

    Public
      Procedure Assign(oSource : TAdvObject); Override;
      Procedure Load(oFiler : TAdvFiler); Override;

      Function Link : TAdvHashEntry; 
      Function Clone : TAdvHashEntry; 
  End;

  PAdvHashEntry = ^TAdvHashEntry;
  TAdvHashEntryClass = Class Of TAdvHashEntry;

  TAdvHashEntryArray = Array [0..MaxInt Div SizeOf(TAdvHashEntry) - 1] Of TAdvHashEntry;
  PAdvHashEntryArray = ^TAdvHashEntryArray;

  TAdvHashTable = Class(TAdvCollection)
    Private
      FTable : PAdvHashEntryArray;
      FCount : Integer;
      FCapacity : Integer;
      FThreshold : Integer;
      FBalance : Real;
      FPreventRehash : Boolean;

      Procedure SetCapacity(Const Value : Integer);
      Procedure SetBalance(Const Value : Real);

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

      Function Resolve(oHashEntry : TAdvHashEntry) : Cardinal; 
      Function Duplicate(oHashEntry : TAdvHashEntry) : TAdvHashEntry; 

      Procedure Rehash; 

      Procedure InternalClear; Override;

      Function Find(oSource, oHashEntry : TAdvHashEntry) : TAdvHashEntry;
      Procedure Insert(iIndex : Integer; oHashEntry: TAdvHashEntry);

      Function Equal(oA, oB : TAdvHashEntry) : Integer; Virtual;
      
      Function ItemClass : TAdvHashEntryClass; Virtual;
      Function ItemNew : TAdvHashEntry; Virtual;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvHashTable; 
      Function Clone : TAdvHashTable; 

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;
      Procedure Load(oFiler : TAdvFiler); Override;
      Procedure Save(oFiler : TAdvFiler); Override;

      Procedure PreventRehash;
      Procedure AllowRehash;

      Procedure PredictCapacityByExpectedCount(Const iCount : Integer);

      Function ProduceHashEntry : TAdvHashEntry;
      Procedure ConsumeHashEntry(oHashEntry : TAdvHashEntry);

      Function Iterator : TAdvIterator; Override;

      Function IsEmpty : Boolean;

      Procedure Add(oHashEntry : TAdvHashEntry); Overload; Virtual;
      Function Delete(oHashEntry : TAdvHashEntry) : Boolean; Overload; Virtual;
      Function Force(oHashEntry : TAdvHashEntry) : TAdvHashEntry; Virtual;
      Function Replace(oHashEntry : TAdvHashEntry) : TAdvHashEntry; Overload; Virtual;
      Function Get(oHashEntry : TAdvHashEntry) : TAdvHashEntry; Virtual;
      Function Exists(oHashEntry : TAdvHashEntry) : Boolean; Overload; Virtual;

      Property Capacity : Integer Read FCapacity Write SetCapacity;
      Property Balance : Real Read FBalance Write SetBalance;
      Property Count : Integer Read FCount;
  End;

  TAdvHashTableList = Class(TAdvPersistentList)
    Private
      Function GetHash(iIndex: Integer): TAdvHashTable;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property Hashes[iIndex : Integer] : TAdvHashTable Read GetHash; Default;
  End;

  TAdvHashTableIterator = Class(TAdvObjectIterator)
    Private
      FHashTable : TAdvHashTable;
      FIndex : Integer;
      FCurrentHashEntry : TAdvHashEntry;
      FNextHashEntry : TAdvHashEntry;

      Function GetHashTable: TAdvHashTable;
      Procedure SetHashTable(Const Value: TAdvHashTable);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure First; Override;
      Procedure Next; Override;
      Function More : Boolean; Override;

      Function Count : Integer;
      Function Current : TAdvObject; Override;

      Property HashTable : TAdvHashTable Read GetHashTable Write SetHashTable;
  End;

  EAdvHashTable = Class(EAdvCollection);

  TAdvIterator = AdvIterators.TAdvIterator;
  TAdvObject = AdvObjects.TAdvObject;
  TAdvFiler = AdvFilers.TAdvFiler;

  
Implementation


Procedure TAdvHashEntry.Assign(oSource: TAdvObject);
Begin
  Inherited;

  FCode := TAdvHashEntry(oSource).Code;
End;


Procedure TAdvHashEntry.Load(oFiler: TAdvFiler);
Begin
  Inherited;

  Generate;
End;


Function TAdvHashEntry.Link : TAdvHashEntry;
Begin
  Result := TAdvHashEntry(Inherited Link);
End;  


Function TAdvHashEntry.Clone : TAdvHashEntry;
Begin 
  Result := TAdvHashEntry(Inherited Clone);
End;  


Procedure TAdvHashEntry.Generate;
Begin 
End;  


Constructor TAdvHashTable.Create;
Begin 
  Inherited;

  Balance := 0.85;
End;  


Destructor TAdvHashTable.Destroy;
Begin
  MemoryDestroy(FTable, FCapacity * SizeOf(TAdvHashEntry));

  Inherited;
End;  


Function TAdvHashTable.ErrorClass : EAdvExceptionClass;
Begin 
  Result := EAdvHashTable;
End;  


Function TAdvHashTable.Link : TAdvHashTable;
Begin 
  Result := TAdvHashTable(Inherited Link);
End;  


Function TAdvHashTable.Clone : TAdvHashTable;
Begin 
  Result := TAdvHashTable(Inherited Clone);
End;  


Procedure TAdvHashTable.Assign(oObject : TAdvObject);
Var
  oIterator : TAdvHashTableIterator;
Begin 
  Inherited;

  Clear;

  Capacity := TAdvHashTable(oObject).Capacity;
  Balance := TAdvHashTable(oObject).Balance;

  // TODO: Implement without iterator for optimal algorithm.

  oIterator := TAdvHashTableIterator(Iterator);
  Try
    oIterator.First;
    While oIterator.More Do
    Begin 
      Add(Duplicate(TAdvHashEntry(oIterator.Current)));

      oIterator.Next;
    End;  
  Finally
    oIterator.Free;
  End;  
End;


Procedure TAdvHashTable.Define(oFiler: TAdvFiler);
Begin 
  Inherited;

//oFiler['Balance'].DefineReal(FBalance);
End;  


Procedure TAdvHashTable.Load(oFiler: TAdvFiler);
Var
  oHashEntry : TAdvHashEntry;
Begin 
  Define(oFiler);

  oFiler['Items'].DefineBegin;

  While (oFiler.Peek <> atEnd) Do
  Begin 
    oHashEntry := Nil;
    Try
      oFiler['Item'].DefineObject(oHashEntry);

      Add(oHashEntry.Link);
    Finally
      oHashEntry.Free;
    End;
  End;  

  oFiler['Items'].DefineEnd;
End;  


Procedure TAdvHashTable.Save(oFiler: TAdvFiler);
Var
  oHashEntry : TAdvHashEntry;
  iLoop     : Integer;
Begin 
  Define(oFiler);

  oFiler['Items'].DefineBegin;

  For iLoop := 0 To FCapacity - 1 Do
  Begin 
    oHashEntry := FTable^[iLoop];

    While Assigned(oHashEntry) Do
    Begin 
      oFiler['Item'].DefineObject(oHashEntry);

      oHashEntry := oHashEntry.FNextHashEntry;
    End;  
  End;  

  oFiler['Items'].DefineEnd;
End;  


Function TAdvHashTable.ItemClass : TAdvHashEntryClass;
Begin 
  Result := TAdvHashEntry;
End;  


Function TAdvHashTable.ItemNew : TAdvHashEntry;
Begin 
  Result := ItemClass.Create;
End;  


Procedure TAdvHashTable.InternalClear;
Var
  iLoop : Integer;
  oHashEntry : TAdvHashEntry;
  oNext : TAdvHashEntry;
Begin 
  Inherited;

  If FCapacity > 0 Then
  Begin 
    For iLoop := 0 To FCapacity - 1 Do
    Begin 
      oHashEntry := FTable^[iLoop];
      FTable^[iLoop] := Nil;

      While Assigned(oHashEntry) Do
      Begin 
        Assert(Invariants('Clear', oHashEntry, ItemClass, 'oHashEntry'));

        oNext := oHashEntry.FNextHashEntry;
        oHashEntry.Free;
        oHashEntry := oNext;
      End;  
    End;  
  End;  

  FCount := 0;
End;


Function TAdvHashTable.Find(oSource, oHashEntry : TAdvHashEntry): TAdvHashEntry;
Begin
  Assert(Not Assigned(oSource) Or Invariants('Find', oSource, ItemClass, 'oSource'));
  Assert(Invariants('Find', oHashEntry, ItemClass, 'oHashEntry'));

  Result := oSource;
  While Assigned(Result) And (Equal(Result, oHashEntry) <> 0) Do
    Result := Result.FNextHashEntry;
End;


Function TAdvHashTable.Equal(oA, oB: TAdvHashEntry): Integer;
Begin
  Result := IntegerCompare(oA.Code, oB.Code);
End;  


Function TAdvHashTable.Resolve(oHashEntry: TAdvHashEntry): Cardinal;
Begin
  Assert(Condition(FCapacity > 0, 'Resolve', 'Capacity must be greater than zero'));

  Result := UnsignedMod(oHashEntry.Code, FCapacity);
End;


Procedure TAdvHashTable.Rehash;
Var
  pTable : PAdvHashEntryArray;
  oHashEntry : TAdvHashEntry;
  oNext : TAdvHashEntry;
  iCapacity : Integer;
  iLoop : Integer;
Begin
  Assert(Condition(Not FPreventRehash, 'Rehash', 'Rehash has been prevented on this hash table as you are required to set Capacity more appropriately in advance.'));

  pTable := FTable;
  FTable := Nil;
  iCapacity := FCapacity;
  FCapacity := 0;

  FCount := 0;
  Try
    Try
      Capacity := (iCapacity * 2) + 1;
    Except
      // Revert to hash table before out of memory exception.
      If ExceptObject.ClassType = EOutOfMemory Then
      Begin
        FTable := pTable;
        FCapacity := iCapacity;

        pTable := Nil;
        iCapacity := 0;
      End;

      Raise;
    End;

    For iLoop := 0 To iCapacity - 1 Do
    Begin
      oHashEntry := pTable^[iLoop];

      While Assigned(oHashEntry) Do
      Begin
        oNext := oHashEntry.FNextHashEntry;

        Insert(Resolve(oHashEntry), oHashEntry);

        oHashEntry := oNext;
      End;
    End;
  Finally
    MemoryDestroy(pTable, iCapacity * SizeOf(TAdvHashEntry));
  End;
End;


Procedure TAdvHashTable.Add(oHashEntry : TAdvHashEntry);
Begin
  Assert(Invariants('Add', oHashEntry, ItemClass, 'oHashEntry'));

  Try
    Assert(Condition(Not Exists(oHashEntry), 'Add', 'Object already exists in the hash.'));

    If FCount > FThreshold Then
      Rehash;

    Insert(Resolve(oHashEntry), oHashEntry.Link);
  Finally
    oHashEntry.Free;
  End;
End;


Procedure TAdvHashTable.Insert(iIndex : Integer; oHashEntry : TAdvHashEntry);
Var
  pFirst : PAdvHashEntry;
Begin
  Assert(Invariants('Insert', oHashEntry, TAdvHashEntry, 'oHashEntry'));
  Assert(Condition((iIndex >= 0) And (iIndex < FCapacity), 'Insert', 'Index must be within the hash table'));

  pFirst := @FTable^[iIndex];

  oHashEntry.FNextHashEntry := pFirst^;
  pFirst^ := oHashEntry;

  Inc(FCount);
End;


Function TAdvHashTable.Force(oHashEntry: TAdvHashEntry): TAdvHashEntry;
Var
  iIndex : Integer;
Begin
  Assert(Invariants('Force', oHashEntry, ItemClass, 'oHashEntry'));

  If FCount > FThreshold Then
    Rehash;

  iIndex := Resolve(oHashEntry);

  Result := Find(FTable^[iIndex], oHashEntry);

  If Not Assigned(Result) Then
  Begin
    Result := Duplicate(oHashEntry);

    Insert(iIndex, Result);
  End;
End;


Function TAdvHashTable.Replace(oHashEntry : TAdvHashEntry) : TAdvHashEntry;
Var
  iIndex : Integer;
Begin
  Assert(Invariants('Replace', oHashEntry, ItemClass, 'oHashEntry'));

  If FCount > FThreshold Then
    Rehash;

  iIndex := Resolve(oHashEntry);

  Result := Find(FTable^[iIndex], oHashEntry);

  If Assigned(Result) Then
  Begin
    Result.Assign(oHashEntry);
  End
  Else
  Begin
    Result := Duplicate(oHashEntry);

    Insert(iIndex, Result);
  End;
End;


Function TAdvHashTable.Delete(oHashEntry: TAdvHashEntry) : Boolean;
Var
  oLast  : TAdvHashEntry;
  oNext  : TAdvHashEntry;
  pFirst : PAdvHashEntry;
Begin
  Assert(Invariants('Delete', oHashEntry, ItemClass, 'oHashEntry'));

  pFirst := @(FTable^[Resolve(oHashEntry)]);

  Result := Assigned(pFirst^);

  If Result Then
  Begin 
    oLast := pFirst^;

    Assert(Invariants('Delete', oLast, ItemClass, 'oLast'));

    If (Equal(oLast, oHashEntry) = 0) Then
    Begin
      pFirst^ := oLast.FNextHashEntry;
      oLast.Free;
    End   
    Else
    Begin 
      oNext := oLast.FNextHashEntry;
      While Assigned(oNext) And (Equal(oNext, oHashEntry) <> 0) Do
      Begin 
        oLast := oNext;
        oNext := oLast.FNextHashEntry;
      End;  

      Result := Assigned(oNext);

      If Result Then
      Begin 
        oLast.FNextHashEntry := oNext.FNextHashEntry;
        oNext.Free;
      End   
    End;  

    If Result Then
      Dec(FCount);
  End;  
End;  


Function TAdvHashTable.Get(oHashEntry : TAdvHashEntry) : TAdvHashEntry;
Begin 
  Assert(Invariants('Get', oHashEntry, ItemClass, 'oHashEntry'));

  // Returns the hash entry in the hash table matching the parameter.
  // If there is no matching object in the hash table, Nil is returned.

  Result := Find(FTable^[Resolve(oHashEntry)], oHashEntry);
End;  


Function TAdvHashTable.Duplicate(oHashEntry: TAdvHashEntry): TAdvHashEntry;
Begin 
  Assert(Invariants('Duplicate', oHashEntry, ItemClass, 'oHashEntry'));

  Result := TAdvHashEntry(oHashEntry.Clone);
End;  


Function TAdvHashTable.Exists(oHashEntry: TAdvHashEntry): Boolean;
Begin 
  Assert(Invariants('Exists', oHashEntry, ItemClass, 'oHashEntry'));

  Result := Assigned(Get(oHashEntry));
End;  


Function TAdvHashTable.Iterator : TAdvIterator;
Begin
  Result := TAdvHashTableIterator.Create;
  TAdvHashTableIterator(Result).HashTable := Self.Link;
End;


Procedure TAdvHashTable.SetCapacity(Const Value: Integer);
Begin

  If Value <> FCapacity Then
  Begin
    Assert(Condition(FCount = 0, 'SetCapacity', StringFormat('Unable to change capacity to %d when there are entries in the hash table', [Value])));

    MemoryResize(FTable, FCapacity * SizeOf(TAdvHashEntry), Value * SizeOf(TAdvHashEntry));

    If Value > FCapacity Then
      MemoryZero(Pointer(NativeUInt(FTable) + NativeUInt(FCapacity * SizeOf(TAdvHashEntry))), (Value - FCapacity) * SizeOf(TAdvHashEntry));

    FCapacity := Value;
    FThreshold := Trunc(FCapacity * FBalance);
  End;
End;


Procedure TAdvHashTable.SetBalance(Const Value: Real);
Begin
  Assert(Condition((Value > 0.0) And (Value < 1.0), 'SetBalance', 'Balance must be set to valid positive percentage.'));

  FBalance := Value;
  FThreshold := Trunc(FCapacity * FBalance);
End;


Function TAdvHashTableList.GetHash(iIndex: Integer): TAdvHashTable;
Begin
  Result := TAdvHashTable(ObjectByIndex[iIndex]);
End;


Function TAdvHashTableList.ItemClass : TAdvObjectClass;
Begin 
  Result := TAdvHashTable;
End;  


Constructor TAdvHashTableIterator.Create;
Begin
  Inherited;

  FHashTable := Nil;
End;


Destructor TAdvHashTableIterator.Destroy;
Begin 
  FHashTable.Free;

  Inherited;
End;  


Procedure TAdvHashTableIterator.First;
Begin 
  FIndex := 0;
  FCurrentHashEntry := Nil;
  FNextHashEntry := Nil;

  If FHashTable.Count > 0 Then
    Next;
End;  


Function TAdvHashTableIterator.Count : Integer;
Begin 
  Result := FHashTable.Count;
End;  


Function TAdvHashTableIterator.Current : TAdvObject;
Begin 
  Assert(Invariants('Current', FCurrentHashEntry, FHashTable.ItemClass, 'FCurrentHashEntry'));

  Result := FCurrentHashEntry;
End;  


Function TAdvHashTableIterator.More : Boolean;
Begin 
  Result := Assigned(FCurrentHashEntry);
End;  


Procedure TAdvHashTableIterator.Next;
Begin 
  FCurrentHashEntry := FNextHashEntry;

  While Not Assigned(FCurrentHashEntry) And (FIndex < FHashTable.Capacity) Do
  Begin 
    FCurrentHashEntry := FHashTable.FTable^[FIndex];
    Inc(FIndex);
  End;  

  If Assigned(FCurrentHashEntry) Then
    FNextHashEntry := FCurrentHashEntry.FNextHashEntry
  Else
    FNextHashEntry := Nil;
End;


Function TAdvHashTableIterator.GetHashTable: TAdvHashTable;
Begin
  Result := FHashTable;
End;


Procedure TAdvHashTableIterator.SetHashTable(Const Value: TAdvHashTable);
Begin
  FHashTable.Free;
  FHashTable := Value;
End;


Function TAdvHashTable.ProduceHashEntry: TAdvHashEntry;
Begin
  Result := ItemNew;
End;


Procedure TAdvHashTable.ConsumeHashEntry(oHashEntry : TAdvHashEntry);
Begin
  oHashEntry.Free;
End;


Procedure TAdvHashTable.PredictCapacityByExpectedCount(Const iCount: Integer);
Begin
  Capacity := RealCeiling(iCount / Balance) + 1;

  Assert(Condition(FThreshold >= iCount, 'PredictCapacityByExpectedCount', StringFormat('Threshold %d was expected to be the same as the expected count %d.', [FThreshold, iCount])));
End;


Procedure TAdvHashTable.AllowRehash;
Begin
  FPreventRehash := False;
End;


Procedure TAdvHashTable.PreventRehash;
Begin
  FPreventRehash := True;
End;


Function TAdvHashTable.IsEmpty: Boolean;
Begin
  Result := Count = 0;
End;


End. // AdvHashes //
