Unit AdvObjects;

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
  Windows,     // Interlocked* API and HResult
  AdvExceptions;


Type
  TAdvObjectClass = Class Of TAdvObject;
  TAdvClass = TAdvObjectClass;

  TAdvReferenceCount = Integer;

  TAdvObject = Class(TObject)
    Private
      // Reference counted using Interlocked* Windows API functions.
      FAdvObjectReferenceCount : TAdvReferenceCount;

    Protected
      // Declared here for ease of implementing interfaces.
      Function _AddRef : Integer; Stdcall;
      Function _Release : Integer; Stdcall;
      Function QueryInterface(Const IID : TGUID; Out Obj): HResult; Virtual; Stdcall;

      Procedure FreezeChildren; Overload; Virtual;
      Procedure AllowDestructionChildren; Overload; Virtual;
      Procedure PreventDestructionChildren; Overload; Virtual;

      Procedure FreeReference; Overload; Virtual;

      // May be called from Nil or invalid references (so can't be virtual).
      Function Invariant(Const sMethod, sMessage : String) : Boolean; Overload;
      Function Invariants(Const sLocation : String; oObject : TObject; aClass : TClass; Const sObject : String) : Boolean; Overload;
      Function Invariants(Const sLocation : String; oObject : TAdvObject; aClass : TClass; Const sObject : String) : Boolean; Overload;
      Function Invariants(Const sLocation : String; aReference, aClass : TClass; Const sReference : String) : Boolean; Overload;

      Function Condition(bCorrect : Boolean; aException : EAdvExceptionClass; Const sMethod, sMessage : String) : Boolean; Overload;
      Function Condition(bCorrect : Boolean; Const sMethod, sMessage : String) : Boolean; Overload;

      // Override to introduce additional or alternate behaviour.
      Function Assignable(Const sLocation : String; oObject : TAdvObject; Const sObject : String) : Boolean; Overload; Virtual;
      Function Alterable(Const sMethod : String) : Boolean; Overload; Virtual;
      Function Destructable(Const sMethod : String) : Boolean; Overload; Virtual;
      Procedure Error(aException : EAdvExceptionClass; Const sMethod, sMessage : String); Overload; Virtual;
      Procedure Error(Const sMethod, sMessage : String); Overload; Virtual;

      Class Procedure ClassError(Const sMethod, sMessage : String); Overload;

      Function ErrorClass : EAdvExceptionClass; Overload; Virtual;

    Public
      Constructor Create; Overload; Virtual;
      Destructor Destroy; Override;

      Procedure AfterConstruction; Override;
      Procedure BeforeDestruction; Override;

      // Cannot be virtual as they are allowed to be called from Nil or invalid objects (but will assert).
      Procedure Free; Overload;
      Function Link : TAdvObject; Overload;
      Function Unlink : TAdvObject; Overload;
      Function Clone : TAdvObject; Overload;
      Function ClassType : TAdvObjectClass; Overload;

      // Destruction.
      Function Indestructable : Boolean; Overload; Virtual;
      Function AllowDestruction : Boolean; Overload;
      Function PreventDestruction : Boolean; Overload;

      // Assignment.
      Function Assignable : Boolean; Overload; Virtual;
      Function Duplicate : TAdvObject; Overload; Virtual;
      Procedure Assign(oObject : TAdvObject); Overload; Virtual;

      // Freezing - object can no longer be changed at all. (Cannot unfreeze because not expected to be any state where this would be safe)
      Function Freezable : Boolean; Overload; Virtual;
      Function Freeze : Boolean; Overload; Virtual;
      Function IsFrozen : Boolean; Overload; Virtual;

      // Determine if self is a valid reference of the specified class.
      Function Invariants(Const sLocation : String; aClass : TClass) : Boolean; Overload;

      Property AdvObjectReferenceCount : TAdvReferenceCount Read FAdvObjectReferenceCount;
  End;

  PAdvObject = ^TAdvObject;

  EAdvInvariant = Class(EAdvException)
    Public
      Constructor Create(Const sSender, sMethod, sReason : String); Overload; Override;
  End;

  EAdvExceptionClass = AdvExceptions.EAdvExceptionClass;
  EAdvException = AdvExceptions.EAdvException;


Implementation


Uses
  AdvFactories;


Constructor TAdvObject.Create;
Begin 
  Inherited;

  Assert(Factory.Construct(Self));
End;  


Destructor TAdvObject.Destroy;
Begin 
  Assert(Factory.Destruct(Self));

  Inherited;
End;  


Procedure TAdvObject.AfterConstruction;
Begin 
  Inherited;

  Assert(Condition(Factory.Valid(Self), 'AfterConstruction', 'Invalid object after construction (possibly missing call to inherited in Create).'));
End;  


Procedure TAdvObject.BeforeDestruction;
Begin 
  Assert(Condition(Factory.Valid(Self), 'BeforeDestruction', 'Invalid object before destruction (possibly too many calls to Free or not enough to Link).'));

  // TODO: really should always be -1, but SysUtils.FreeAndNil may bypass the correct Free method.
  Assert(Condition(FAdvObjectReferenceCount <= 0, 'BeforeDestruction', 'Attempted to destroy object before all references are released (possibly freed while cast as a TObject).'));

  Assert(Destructable('BeforeDestruction'));

  Inherited;
End;  


Procedure TAdvObject.AllowDestructionChildren;
Begin
End;


Procedure TAdvObject.PreventDestructionChildren;
Begin
End;


Procedure TAdvObject.FreezeChildren;
Begin
End;


Procedure TAdvObject.FreeReference;
Begin
  If (InterlockedDecrement(FAdvObjectReferenceCount) < 0) Then
    Destroy;
End;


Procedure TAdvObject.Free;
Begin
  If Assigned(Self) Then
  Begin
    Assert(Invariants('Free', TAdvObject));

    FreeReference;
  End;
End;  


Function TAdvObject.ClassType : TAdvObjectClass;
Begin 
  Result := TAdvObjectClass(Inherited ClassType);
End;  


Function TAdvObject.Unlink : TAdvObject;
Begin 
  Result := Self;

  If Assigned(Self) Then
  Begin 
    Assert(Invariants('Unlink', TAdvObject));

    If (InterlockedDecrement(FAdvObjectReferenceCount) < 0) Then
    Begin 
      Destroy;
      Result := Nil;
    End;  
  End;  
End;  


Function TAdvObject.Link : TAdvObject;
Begin 
  Result := Self;

  If Assigned(Self) Then
  Begin 
    Assert(Invariants('Link', TAdvObject));

    InterlockedIncrement(FAdvObjectReferenceCount);
  End;  
End;  


Function TAdvObject.Duplicate : TAdvObject;
Begin 
  Result := ClassType.Create;
End;  


Function TAdvObject.Clone : TAdvObject;
Begin 
  If Assigned(Self) Then
  Begin
    Assert(Invariants('Clone', TAdvObject));

    Result := Duplicate;
    Result.Assign(Self);

    Assert(Invariants('Clone', Result, ClassType, 'Result'));
  End
  Else
  Begin
    Result := Nil;
  End;
End;  


Function TAdvObject._AddRef : Integer;
Begin 
  If Assigned(Self) Then
  Begin 
    Assert(Invariants('_AddRef', TAdvObject));

    Result := InterlockedIncrement(FAdvObjectReferenceCount);
  End   
  Else
  Begin 
    Result := 0;
  End;
End;


Function TAdvObject._Release: Integer;
Begin
  If Assigned(Self) Then
  Begin
    Assert(Invariants('_Release', TAdvObject));

    Result := InterlockedDecrement(FAdvObjectReferenceCount);

    If Result < 0 Then
      Destroy;
  End
  Else
  Begin 
    Result := 0;
  End;  
End;  


Function TAdvObject.QueryInterface(Const IID: TGUID; Out Obj): HResult;
//Const
//  // Extra typecast to longint prevents a warning about subrange bounds
//  SUPPORT_INTERFACE : Array[Boolean] Of HResult = (Longint($80004002), 0);
Begin
//  Result := SUPPORT_INTERFACE[GetInterface(IID, Obj)];
  If GetInterface(IID, Obj) Then
    Result := S_OK
  Else
    Result := E_NOINTERFACE;
End;


Function TAdvObject.Assignable : Boolean;
Begin 
  Result := True;
End;  


Function TAdvObject.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvException;                                                        
End;  


Procedure TAdvObject.Error(aException : EAdvExceptionClass; Const sMethod, sMessage : String);
Begin 
  Raise aException.Create(Self, sMethod, sMessage);
End;


Procedure TAdvObject.Error(Const sMethod, sMessage : String);
Begin
  Error(ErrorClass, sMethod, sMessage);
End;  


Function TAdvObject.Assignable(Const sLocation : String; oObject : TAdvObject; Const sObject : String) : Boolean;
Begin 
  Invariants(sLocation, oObject, ClassType, sObject);

  If (Self = oObject) Then
    Invariant(sLocation, 'Cannot assign an object to itself.');

  Result := Alterable(sLocation);
End;


Procedure TAdvObject.Assign(oObject : TAdvObject);
Begin 
  Assert(Condition(Assignable, 'Assign', 'Object is not marked as assignable.'));
  Assert(Assignable('Assign', oObject, 'oObject'));

  // Override and inherit to assign the properties of your class.
End;  


Function TAdvObject.Invariants(Const sLocation: String; aReference, aClass: TClass; Const sReference : String): Boolean;
Begin 
  // Ensure class is assigned.
  If Not Assigned(aReference) Then
    Invariant(sLocation, sReference + ' was not assigned and was expected to have been of class type ' + aClass.ClassName);

  // Ensure class is of the expected class.
  If Not aReference.InheritsFrom(aClass) Then
    Invariant(sLocation, sReference + ' was of class type ' + aReference.ClassName + ' and should have been of class type ' + aClass.ClassName);

  Result := True;
End;  


Function TAdvObject.Invariants(Const sLocation : String; oObject : TObject; aClass: TClass; Const sObject : String) : Boolean;
Begin 
  If Not Assigned(aClass) Then
    Invariant('Invariants', 'aClass was not assigned.');

  // Ensure object is assigned.
  If Not Assigned(oObject) Then
    Invariant(sLocation, sObject + ' was not assigned and was expected to have been of class ' + aClass.ClassName);

  // Ensure object was found in the factory.
  If Not Factory.Valid(oObject) Then
    Invariant(sLocation, sObject + ' was an invalid reference and was expected to have been of class ' + aClass.ClassName);

  // Ensure object is of the expected class.
  If Factory.Active And Not oObject.InheritsFrom(aClass) Then
    Invariant(sLocation, sObject + ' was of class ' + oObject.ClassName + ' and should have been of class ' + aClass.ClassName);

  Result := True;
End;


Function TAdvObject.Invariants(Const sLocation : String; oObject: TAdvObject; aClass: TClass; Const sObject : String) : Boolean;
Begin
  Invariants(sLocation, TObject(oObject), aClass, sObject);

  Result := True;
End;


Function TAdvObject.Invariants(Const sLocation: String; aClass: TClass) : Boolean;
Begin
  Invariants(sLocation, TObject(Self), aClass, 'Self');

  Result := True;
End;


Function TAdvObject.Condition(bCorrect: Boolean; Const sMethod, sMessage: String): Boolean;
Begin 
  // Call this method as you would the Assert procedure to raise an exception if bCorrect is False.

  If Not bCorrect Then
    Invariant(sMethod, sMessage);

  Result := True;
End;  


Function TAdvObject.Condition(bCorrect : Boolean; aException : EAdvExceptionClass; Const sMethod, sMessage : String) : Boolean;
Begin 
  // Call this method as you would the Assert procedure to raise an exception if bCorrect is False.

  If Not bCorrect Then
    Error(aException, sMethod, sMessage);

  Result := True;
End;  


Function TAdvObject.Invariant(Const sMethod, sMessage: String): Boolean;
Begin 
  // Call this method as you would the Error method to raise an exception.
  // Use this when you are not sure if self is valid as it is a non-virtual method.

  Raise EAdvInvariant.Create(Self, sMethod, sMessage); // Can't use Error method here as it is virtual.

  Result := True;
End;  


Function TAdvObject.Alterable(Const sMethod: String): Boolean;
Begin 
  If Freezable And IsFrozen Then
    Invariant(sMethod, 'Object cannot be altered as it has been frozen.');

  Result := True;
End;  


Function TAdvObject.Freezable : Boolean;
Begin
  Result := True;
End;


Function TAdvObject.Freeze : Boolean;
Begin
  If Not Freezable Then
    Invariant('Freeze', 'Object is not marked as freezable.');

  If Not Factory.IsFrozen(Self) Then
  Begin
    Factory.Freeze(Self);

    FreezeChildren;
  End;

  Result := True;
End;


Function TAdvObject.IsFrozen : Boolean;
Begin
  Assert(Condition(Freezable, 'IsFrozen', 'Object is not marked as freezable.'));

{$IFOPT C+}
  Result := Factory.IsFrozen(Self);
{$ELSE}
  Result := False;
{$ENDIF}
End;


Function TAdvObject.AllowDestruction : Boolean;
Begin
  If Not Indestructable Then
    Invariant('AllowDestruction', 'Object is not marked as allowing indestruction.');

  If Factory.IsIndestructable(Self) Then
  Begin
    Factory.MarkDestructable(Self);

    AllowDestructionChildren;
  End;

  Result := True;
End;


Function TAdvObject.PreventDestruction : Boolean;
Begin
  If Not Indestructable Then
    Invariant('AllowDestruction', 'Object is not marked as allowing indestruction.');

  If Not Factory.IsIndestructable(Self) Then
  Begin
    Factory.MarkIndestructable(Self);

    PreventDestructionChildren;
  End;

  Result := True;
End;


Function TAdvObject.Destructable(Const sMethod: String): Boolean;
Begin
  If Indestructable And Factory.IsIndestructable(Self) Then
    Invariant(sMethod, 'Attempting to destroy a permanent object.');

  Result := True;
End;


Function TAdvObject.Indestructable : Boolean;
Begin
  Result := True;
End;


Class Procedure TAdvObject.ClassError(Const sMethod, sMessage: String);
Begin
  Raise EAdvException.Create(Nil, sMethod, sMessage);
End;


Constructor EAdvInvariant.Create(Const sSender, sMethod, sReason : String);
Begin
  Inherited;

  Message := Description;
End;  


End. // AdvObjects //
