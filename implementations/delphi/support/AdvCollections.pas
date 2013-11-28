Unit AdvCollections;


{! 7 !}


Interface


Uses
  AdvExceptions, AdvPersistents, AdvIterators;


Type
  TAdvCollection = Class(TAdvPersistent)
    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

      Procedure InternalClear; Virtual;

    Public
      Procedure BeforeDestruction; Override;

      Procedure Clear; Virtual;

      Function Iterator : TAdvIterator; Overload; Virtual;
  End; 

  EAdvCollection = Class(EAdvException);


Implementation


Procedure TAdvCollection.BeforeDestruction;
Begin 
  InternalClear;

  Inherited;
End;  


Procedure TAdvCollection.Clear;
Begin 
  InternalClear;
End;  


Procedure TAdvCollection.InternalClear;
Begin 
End;  


Function TAdvCollection.ErrorClass : EAdvExceptionClass;
Begin 
  Result := EAdvCollection;
End;  


Function TAdvCollection.Iterator : TAdvIterator;
Begin 
  Error('Iterator', 'No iterator specified.');

  Result := Nil;
End;  


End. // AdvCollections //
