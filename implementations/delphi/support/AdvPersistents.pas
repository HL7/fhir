Unit AdvPersistents;


{! 17 !}


Interface


Uses
  AdvObjects, AdvFilers;


Type
  {$TYPEINFO ON}
  TAdvPersistent = Class(TAdvObject)
    Protected
      Function Fileable(Const sLocation : String) : Boolean; Overload; Virtual;

    Public
      Function Link : TAdvPersistent; Overload;
      Function Clone : TAdvPersistent; Overload;

      Procedure Define(oFiler : TAdvFiler); Overload; Virtual;
      Procedure Load(oFiler : TAdvFiler); Overload; Virtual;
      Procedure Save(oFiler : TAdvFiler); Overload; Virtual;

      Function Fileable : Boolean; Overload; Virtual;
  End; 

  TAdvPersistentClass = Class Of TAdvPersistent;

  EAdvExceptionClass = AdvObjects.EAdvExceptionClass;
  EAdvException = AdvObjects.EAdvException;
  TAdvObject = AdvObjects.TAdvObject;
  TAdvObjectClass = AdvObjects.TAdvObjectClass;
  TAdvFiler = AdvFilers.TAdvFiler;

  
Implementation


Function TAdvPersistent.Clone : TAdvPersistent;
Begin 
  Result := TAdvPersistent(Inherited Clone);
End;  


Function TAdvPersistent.Link : TAdvPersistent;
Begin 
  Result := TAdvPersistent(Inherited Link);
End;  


Procedure TAdvPersistent.Define(oFiler: TAdvFiler);
Begin 
  Assert(Fileable('Define'));
End;  


Procedure TAdvPersistent.Save(oFiler : TAdvFiler);
Begin 
  Assert(Invariants('Save', oFiler, TAdvFiler, 'oFiler'));

  Define(oFiler);
End;  


Procedure TAdvPersistent.Load(oFiler : TAdvFiler);
Begin 
  Assert(Invariants('Load', oFiler, TAdvFiler, 'oFiler'));
  
  Define(oFiler);
End;  


Function TAdvPersistent.Fileable : Boolean;
Begin 
  Result := True;
End;  


Function TAdvPersistent.Fileable(Const sLocation: String): Boolean;
Begin 
  Invariants(sLocation, TAdvObject);

  If Not Fileable Then
    Invariant(sLocation, 'Object is marked as unfileable.');

  Result := True;
End;


End. // AdvPersistents //
