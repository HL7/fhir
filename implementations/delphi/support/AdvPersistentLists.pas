Unit AdvPersistentLists;


{! 7 !}


Interface


Uses
  AdvObjectLists,
  AdvPersistents;


Type
  TAdvPersistentList = Class(TAdvObjectList)
    Private
      Function GetPersistentByIndex(Const iIndex : Integer) : TAdvPersistent;
      Procedure SetPersistentByIndex(Const iIndex : Integer; Const oValue : TAdvPersistent);

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property PersistentByIndex[Const iIndex : Integer] : TAdvPersistent Read GetPersistentByIndex Write SetPersistentByIndex; Default;
  End;

  TAdvPersistentListIterator = Class(TAdvObjectListIterator)
  End;

  TAdvPersistentListClass = Class Of TAdvPersistentList;

  TAdvObject = AdvPersistents.TAdvObject;
  TAdvObjectClass = AdvPersistents.TAdvObjectClass;
  TAdvFiler = AdvPersistents.TAdvFiler;
  TAdvPersistent = AdvPersistents.TAdvPersistent;
  TAdvItemsCompare = AdvObjectLists.TAdvItemsCompare;
  TAdvIterator = AdvObjectLists.TAdvIterator;


Implementation


Function TAdvPersistentList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvPersistent;
End;


Function TAdvPersistentList.GetPersistentByIndex(Const iIndex : Integer) : TAdvPersistent;
Begin
  Result := TAdvPersistent(ObjectByIndex[iIndex]);
End;


Procedure TAdvPersistentList.SetPersistentByIndex(Const iIndex : Integer; Const oValue : TAdvPersistent);
Begin
  ObjectByIndex[iIndex] := oValue;
End;


End.
