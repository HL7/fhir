Unit AdvClassHashes;


{! 9 !}


Interface


Uses
  AdvObjects, AdvHashes, AdvStringHashes, AdvIterators;


Type
  TAdvObjectClassHashEntry = Class(TAdvStringHashEntry)
    Private
      FData : TClass;

    Public
      Procedure Assign(oSource : TAdvObject); Override;

      Property Data : TClass Read FData Write FData; // no set data as hashed classname may be different to FData.ClassName.
  End; 

  TAdvObjectClassHashTable = Class(TAdvStringHashTable)
    Protected
      Function ItemClass : TAdvHashEntryClass; Override;

    Public
      Function Iterator : TAdvIterator; Override;
  End; 

  TAdvObjectClassHashTableIterator = Class(TAdvObjectClassIterator)
    Private
      FInternal : TAdvStringHashTableIterator;

      Function GetHashTable: TAdvObjectClassHashTable;
      Procedure SetHashTable(Const Value: TAdvObjectClassHashTable);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure First; Override;
      Procedure Last; Override;
      Procedure Next; Override;
      Procedure Back; Override;

      Function More : Boolean; Override;
      Function Current : TClass; Override;

      Property HashTable : TAdvObjectClassHashTable Read GetHashTable Write SetHashTable;
  End; 


Implementation


Procedure TAdvObjectClassHashEntry.Assign(oSource: TAdvObject);
Begin 
  Inherited;

  FData := TAdvObjectClassHashEntry(oSource).Data;
End;  


Constructor TAdvObjectClassHashTableIterator.Create;
Begin 
  Inherited;

  FInternal := TAdvStringHashTableIterator.Create;
End;  


Destructor TAdvObjectClassHashTableIterator.Destroy;
Begin 
  FInternal.Free;

  Inherited;
End;  


Function TAdvObjectClassHashTable.ItemClass : TAdvHashEntryClass;
Begin 
  Result := TAdvObjectClassHashEntry;
End;  


Function TAdvObjectClassHashTable.Iterator : TAdvIterator;
Begin 
  Result := TAdvObjectClassHashTableIterator.Create;
  TAdvObjectClassHashTableIterator(Result).HashTable := TAdvObjectClassHashTable(Self.Link);
End;  


Function TAdvObjectClassHashTableIterator.Current : TClass;
Begin 
  Result := TAdvObjectClassHashEntry(FInternal.Current).Data;
End;  


Procedure TAdvObjectClassHashTableIterator.First;
Begin 
  Inherited;

  FInternal.First;
End;  


Procedure TAdvObjectClassHashTableIterator.Last;
Begin 
  Inherited;

  FInternal.Last;
End;  


Procedure TAdvObjectClassHashTableIterator.Next;
Begin 
  Inherited;

  FInternal.Next;
End;  


Procedure TAdvObjectClassHashTableIterator.Back;
Begin 
  Inherited;

  FInternal.Back;
End;  


Function TAdvObjectClassHashTableIterator.More : Boolean;
Begin 
  Result := FInternal.More;
End;  


Function TAdvObjectClassHashTableIterator.GetHashTable : TAdvObjectClassHashTable;
Begin 
  Result := TAdvObjectClassHashTable(FInternal.HashTable);
End;  


Procedure TAdvObjectClassHashTableIterator.SetHashTable(Const Value: TAdvObjectClassHashTable);
Begin 
  FInternal.HashTable := Value;
End;


End. // AdvClassHashes //
