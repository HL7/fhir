Unit AdvControllers;


{! 27 !}


Interface


Uses
  AdvPersistents, AdvPersistentLists;


Type
  TAdvController = Class(TAdvPersistent)
    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Function Link : TAdvController;

      Procedure Open; Virtual;
      Procedure Close; Virtual;
  End;

  EAdvController = Class(EAdvException);

  TAdvControllerList = Class(TAdvPersistentList)
    Private
      Function GetControllerByIndex(Const iIndex : Integer) : TAdvController;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property ControllerByIndex[Const iIndex : Integer] : TAdvController Read GetControllerByIndex; Default;
  End;

  TAdvObjectClass = AdvPersistents.TAdvObjectClass;
  TAdvObject = AdvPersistents.TAdvObject;
  TAdvFiler = AdvPersistents.TAdvFiler;


Implementation


Function TAdvController.Link: TAdvController;
Begin
  Result := TAdvController(Inherited Link);
End;


Function TAdvController.ErrorClass: EAdvExceptionClass;
Begin
  Result := EAdvController;
End;


Procedure TAdvController.Open;
Begin
End;


Procedure TAdvController.Close;
Begin
End;


Function TAdvControllerList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvController;
End;


Function TAdvControllerList.GetControllerByIndex(Const iIndex : Integer) : TAdvController;
Begin
  Result := TAdvController(ObjectByIndex[iIndex]);
End;


End.
