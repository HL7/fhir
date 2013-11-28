Unit AdvIterators;


{! 12 !}


Interface


Uses
  DateSupport, CurrencySupport,
  AdvObjects, AdvPersistents, AdvExceptions;


Type
  TAdvIterator = Class(TAdvPersistent)
    Protected
      Function ErrorClass : EAdvExceptionClass; Overload; Override;

    Public
      Procedure First; Virtual;
      Procedure Last; Virtual;
      Procedure Next; Virtual;
      Procedure Back; Virtual;
      Procedure Previous; Virtual;

      Function More : Boolean; Virtual;
  End; 

  TAdvIteratorClass = Class Of TAdvIterator;

  TAdvObjectIterator = Class(TAdvIterator)
    Public
      Function Current : TAdvObject; Virtual;
  End; 

  TAdvStringIterator = Class(TAdvIterator)
    Public
      Function Current : String; Virtual;
  End; 

  TAdvIntegerIterator = Class(TAdvIterator)
    Public
      Function Current : Integer; Virtual;
  End; 

  TAdvRealIterator = Class(TAdvIterator)
    Public
      Function Current : Real; Virtual;
  End; 

  TAdvExtendedIterator = Class(TAdvIterator)
    Public
      Function Current : Extended; Virtual;
  End; 

  TAdvBooleanIterator = Class(TAdvIterator)
    Public
      Function Current : Boolean; Virtual;
  End; 

  TAdvLargeIntegerIterator = Class(TAdvIterator)
    Public
      Function Current : Int64; Virtual;
  End; 

  TAdvPointerIterator = Class(TAdvIterator)
    Public
      Function Current : Pointer; Virtual;
  End; 

  TAdvObjectClassIterator = Class(TAdvIterator)
    Public
      Function Current : TClass; Virtual;
  End; 

  TAdvDateTimeIterator = Class(TAdvIterator)
    Public
      Function Current : TDateTime; Virtual;
  End; 

  TAdvDurationIterator = Class(TAdvIterator)
    Public
      Function Current : TDuration; Virtual;
  End; 

  TAdvCurrencyIterator = Class(TAdvIterator)
    Public
      Function Current : TCurrency; Virtual;
  End; 

  EAdvIterator = Class(EAdvException);


Implementation


Function TAdvIterator.ErrorClass : EAdvExceptionClass;
Begin 
  Result := EAdvIterator;
End;  


Procedure TAdvIterator.First;
Begin 
End;


Procedure TAdvIterator.Last;
Begin
End;


Function TAdvIterator.More : Boolean;
Begin
  Result := False;
End;


Procedure TAdvIterator.Next;
Begin
End;


Procedure TAdvIterator.Back;
Begin
End;


Procedure TAdvIterator.Previous;
Begin
  Back;
End;


Function TAdvObjectIterator.Current : TAdvObject;
Begin
  Error('Current', 'Current not implemented.');

  Result := Nil;
End;  


Function TAdvStringIterator.Current : String;
Begin 
  Error('Current', 'Current not implemented.');
  
  Result := '';
End;  


Function TAdvIntegerIterator.Current : Integer;
Begin 
  Error('Current', 'Current not implemented.');
  
  Result := 0;
End;  


Function TAdvRealIterator.Current : Real;
Begin
  Error('Current', 'Current not implemented.');

  Result := 0;
End;


Function TAdvExtendedIterator.Current : Extended;
Begin
  Error('Current', 'Current not implemented.');

  Result := 0;
End;


Function TAdvBooleanIterator.Current : Boolean;
Begin
  Error('Current', 'Current not implemented.');
  
  Result := False;
End;  


Function TAdvLargeIntegerIterator.Current : Int64;
Begin 
  Error('Current', 'Current not implemented.');
  
  Result := 0;
End;  


Function TAdvPointerIterator.Current : Pointer;
Begin 
  Error('Current', 'Current not implemented.');
  
  Result := Nil;
End;  


Function TAdvObjectClassIterator.Current : TClass;
Begin 
  Error('Current', 'Current not implemented.');
  
  Result := Nil;
End;  


Function TAdvDateTimeIterator.Current : TDateTime;
Begin 
  Error('Current', 'Current not implemented.');

  Result := 0;
End;  


Function TAdvDurationIterator.Current : TDuration;
Begin 
  Error('Current', 'Current not implemented.');

  Result := 0;
End;  


Function TAdvCurrencyIterator.Current : TCurrency;
Begin 
  Error('Current', 'Current not implemented.');

  Result := 0;
End;  


End. // AdvIterators //
