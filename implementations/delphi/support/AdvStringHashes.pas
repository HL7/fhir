Unit AdvStringHashes;


{! 17 !}


Interface


Uses
  StringSupport, HashSupport,
  AdvObjects, AdvHashes, AdvFilers, AdvIterators;


Type
  TAdvStringHashEntry = Class(TAdvHashEntry)
    Private
      FName : String;

      Procedure SetName(Const Value: String); 

    Protected
      Procedure Generate; Override;

    Public
      Procedure Assign(oSource : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Property Name : String Read FName Write SetName;
  End;

  TAdvStringHashEntryClass = Class Of TAdvStringHashEntry;

  TAdvStringHashTable = Class(TAdvHashTable)
    Protected
      Function Equal(oA, oB : TAdvHashEntry) : Integer; Override;

      Function ItemClass : TAdvHashEntryClass; Override;

    Public
      Function Link : TAdvStringHashTable;

      Function Iterator : TAdvIterator; Override;
  End; 

  TAdvStringHashTableIterator = Class(TAdvHashTableIterator)
    Public
      Function Current : TAdvStringHashEntry; Reintroduce;
  End; 

  TAdvHashEntryClass = AdvHashes.TAdvHashEntryClass;
  TAdvHashEntry = AdvHashes.TAdvHashEntry;

  TAdvIterator = AdvHashes.TAdvIterator;


Implementation


Procedure TAdvStringHashEntry.Generate;
Begin 
  Code := HashSupport.HashStringToCode32(FName);
End;


Procedure TAdvStringHashEntry.SetName(Const Value: String);
Begin 
  FName := Value;

  Generate;
End;  


Procedure TAdvStringHashEntry.Assign(oSource: TAdvObject);
Begin 
  Inherited;

  Name := TAdvStringHashEntry(oSource).Name;
End;  


Procedure TAdvStringHashEntry.Define(oFiler : TAdvFiler);
Begin 
  Inherited;

  oFiler['Name'].DefineString(FName);
End;  


Function TAdvStringHashTable.Equal(oA, oB: TAdvHashEntry): Integer;
Begin 
  Result := Inherited Equal(oA, oB);

  If Result = 0 Then
    Result := StringCompare(TAdvStringHashEntry(oA).Name, TAdvStringHashEntry(oB).Name);
End;  


Function TAdvStringHashTable.ItemClass : TAdvHashEntryClass;
Begin 
  Result := TAdvStringHashEntry;
End;  


Function TAdvStringHashTable.Iterator : TAdvIterator;
Begin 
  Result := TAdvStringHashTableIterator.Create;
  TAdvStringHashTableIterator(Result).HashTable := Self.Link;
End;  


Function TAdvStringHashTableIterator.Current : TAdvStringHashEntry;
Begin 
  Result := TAdvStringHashEntry(Inherited Current);
End;


Function TAdvStringHashTable.Link: TAdvStringHashTable;
Begin
  Result := TAdvStringHashTable(Inherited Link);
End;


End. // AdvStringHashes //
