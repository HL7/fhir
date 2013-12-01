Unit AdvFactories;

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
  Windows, // MessageBox
  SysUtils, // Exceptions
  StringSupport, FileSupport, EncodeSupport, SystemSupport, ThreadSupport, DateSupport,
  AdvControllers, AdvObjects, AdvExceptions, AdvClassHashes, AdvProfilers,  AdvExclusiveCriticalSections,
  AdvBuffers, AdvIntegerMatches, AdvLargeIntegerMatches, AdvStringIntegerMatches, AdvStringLargeIntegerMatches, AdvStringLists;


Type
  TAdvFactorySerial = Cardinal;

  TAdvFactory = Class(TAdvObject)
    Private
      FClassHashTable : TAdvObjectClassHashTable;            // Class factory
      FLookupClassHashEntry : TAdvObjectClassHashEntry;  // Accessor to the class factory
      FObjectProfiler : TAdvProfiler;                   // Instance repository
      FCritical : TAdvExclusiveCriticalSection;                   // Critical section for instance repository
      FCriticalDepth : Integer;                   // Number of nested critical section locks.
      FProfiled : Boolean;                        // Save the profile report when the application finishes.
      FLaunched : Boolean;                        // Launch the profile   report when the application finishes.
      FFolder : String;                           // Filename location of leak profile report.

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Active : Boolean;
      Function Lives : Boolean;

      Function ActiveObjectTracking : Boolean;
      Function ActiveClassTracking : Boolean;

      // Called from Initialise/Finalise.
      Procedure Open;
      Procedure Close;

      // For locking before accessing property Objects.
      Procedure Lock;
      Procedure Unlock;
      Function Nested : Boolean;

      // Register Classes
      Procedure RegisterClass(Const aClass : TClass);
      Procedure RegisterClassArray(Const aClassArray : Array Of TClass);
      Procedure RegisterClasses(Const aClassArray : Array Of TClass);
      Procedure RegisterClassAlias(Const aClass : TClass; Const sName : String);
      Function IsEquivalentClass(Const aClass : TClass; Const sName : String) : Boolean;
      Function IsRegisteredClass(Const aClass : TClass) : Boolean;

      Procedure UnregisterClass(Const aClass : TClass);

      Function Get(Const sName : String) : TClass;
      Function Make(Const sName : String) : TObject; Overload;
      Function Make(Const aClass : TClass) : TObject; Overload;

      // Register Objects (Always returns True so can be called from Assert without raising an error and compiled out when $C-).
      Function Construct(oObject : TObject; Out iSerial : TAdvFactorySerial) : Boolean; Overload;
      Function Construct(oObject : TObject) : Boolean; Overload;
      Function Destruct(oObject : TObject) : Boolean; Overload;
      Function Valid(oObject : TObject) : Boolean;

      // Register Breakpoints
      Procedure Track(oObject : TObject);
      Procedure Notify(oObject : TObject);

      // Object freezing
      Function Freeze(oObject : TObject) : Boolean;
      Function IsFrozen(oObject : TObject) : Boolean;

      Function MarkDestructable(oObject : TObject) : Boolean;
      Function MarkIndestructable(oObject : TObject) : Boolean;
      Function IsIndestructable(oObject : TObject) : Boolean;

      Property ClassHashTable : TAdvObjectClassHashTable Read FClassHashTable;
      Property ObjectProfiler : TAdvProfiler Read FObjectProfiler;
      Property Profiled : Boolean Read FProfiled Write FProfiled;
      Property Launched : Boolean Read FLaunched Write FLaunched;
      Property Folder : String Read FFolder Write FFolder;
  End;

  EAdvFactory = Class(EAdvException);

  TAdvObjectClass = AdvObjects.TAdvObjectClass;


Const
  SERIAL_INVALID = High(TAdvFactorySerial);


Function Factory : TAdvFactory;
Function HasFactory : Boolean;


Implementation


Var
  gFactory : TAdvFactory;


Function Factory : TAdvFactory;
Begin
  Result := gFactory;
End;


Function HasFactory : Boolean;
Begin
  Result := Assigned(gFactory);
End;


Constructor TAdvFactory.Create;
Begin
  Inherited;

  FProfiled := True;
  FLaunched := True;
  FFolder := SystemTemp;

  FObjectProfiler := TAdvProfiler.Create;
  FClassHashTable := TAdvObjectClassHashTable.Create;
  FLookupClassHashEntry := TAdvObjectClassHashEntry.Create;

  FCritical := TAdvExclusiveCriticalSection.Create;

  // NOTE: these are prime numbers for best hash table performance.
{$IFOPT C+}
  FObjectProfiler.Capacity := 4256297; // 4.25 million objects; ~16MB of array pointers
{$ELSE}
  FObjectProfiler.Capacity := 1048583; // 1.04 million objects; ~4MB of array pointers.
{$ENDIF}


  FClassHashTable.Capacity := 50021; // 0.2MB of array pointers
End;


Destructor TAdvFactory.Destroy;
Begin
  FCritical.Free;
  FObjectProfiler.Free;
  FClassHashTable.Free;
  FLookupClassHashEntry.Free;

  Inherited;
End;


Function TAdvFactory.ActiveClassTracking: Boolean;
Begin
  Result := Active;
End;


Function TAdvFactory.ActiveObjectTracking: Boolean;
Begin
{$IFOPT C+}
  Result := Active;
{$ELSE}
  Result := False;
{$ENDIF}
End;


Function TAdvFactory.Active : Boolean;
Begin
  Result := Assigned(Self);
End;



Procedure TAdvFactory.Open;
Begin
  // Add the following objects to the Object hash.
  Assert(Construct(Self));
  Assert(Construct(FLookupClassHashEntry));
  Assert(Construct(FCritical));
  Assert(Construct(FObjectProfiler));
  Assert(Construct(FClassHashTable));

  // moved from AdvExceptions as it was causing problems.
  RegisterClass(EAdvFactory);
  RegisterClass(EAdvInvariant);  
  RegisterClass(EAdvException);
  RegisterClass(EAdvAbstract);
  RegisterClass(EAdvAssertion);
  RegisterClass(EAdvController);

  RegisterClass(EAbstractError);
  RegisterClass(EAccessViolation);
  RegisterClass(EAssertionFailed);
  RegisterClass(EControlC);
  RegisterClass(EDivByZero);
  RegisterClass(EExternal);
  RegisterClass(EExternalException);
  RegisterClass(ERangeError);
  RegisterClass(EIntError);
  RegisterClass(EIntOverflow);
  RegisterClass(EInvalidCast);
  RegisterClass(EIntfCastError);
  RegisterClass(EInvalidPointer);
  RegisterClass(EMathError);
  RegisterClass(EOverflow);
  RegisterClass(EOutOfMemory);
  RegisterClass(EPrivilege);
  RegisterClass(ESafeCallException);
  RegisterClass(EUnderflow);
  RegisterClass(EVariantError);
  RegisterClass(EZeroDivide);
{$IFDEF VER130}
  RegisterClass(EStackOverflow);
  RegisterClass(EWin32Error);
{$ENDIF}
  RegisterClass(TAdvStringList);
  RegisterClass(TAdvIntegerMatch);
  RegisterClass(TAdvLargeIntegerMatch);
  RegisterClass(TAdvStringIntegerMatch);
  RegisterClass(TAdvStringLargeIntegerMatch);
  RegisterClassArray([TAdvBuffer, TAdvBufferList]);

  RegisterClassAlias(TAdvStringList, 'TAdvStrings');
End;


Procedure TAdvFactory.Close;
Begin
  // Ensure the classes HashEntrys are destroyed.
  FClassHashTable.Clear;

  // Remove the following objects from the object hash.
  Assert(Destruct(FCritical));
  Assert(Destruct(FLookupClassHashEntry));
  Assert(Destruct(FObjectProfiler));
  Assert(Destruct(FClassHashTable));
  Assert(Destruct(Self));

  // If there are no memory leaks then the object hash should be empty.
End;


Function TAdvFactory.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvFactory;
End;


Procedure TAdvFactory.RegisterClassAlias(Const aClass: TClass; Const sName: String);
Begin
  // Pass a value for sName to alias a class as a different name.  This is
  // useful for backwards compatiblity programming.

  FLookupClassHashEntry.Name := sName;
  FLookupClassHashEntry.Data := aClass;

  FClassHashTable.Force(FLookupClassHashEntry);
End;


Procedure TAdvFactory.RegisterClass(Const aClass: TClass);
Begin
  RegisterClassAlias(aClass, aClass.ClassName);
End;


Procedure TAdvFactory.RegisterClassArray(Const aClassArray : Array Of TClass);
Var
  iClassIndex : Integer;
Begin
  For iClassIndex := Low(aClassArray) To High(aClassArray) Do
    RegisterClass(aClassArray[iClassIndex]);
End;


Procedure TAdvFactory.UnregisterClass(Const aClass : TClass);
Var
  oLookup : TAdvObjectClassHashEntry;
Begin
  oLookup := TAdvObjectClassHashEntry.Create;
  Try
    oLookup.Name := aClass.ClassName;
    oLookup.Data := aClass;
    
    FClassHashTable.Delete(oLookup);
  Finally
    oLookup.Free;
  End;
End;


Function TAdvFactory.Get(Const sName: String): TClass;
Var
  oLookup : TAdvObjectClassHashEntry;
  oClass  : TAdvObjectClassHashEntry;
Begin 
  oLookup := TAdvObjectClassHashEntry.Create;
  Try
    oLookup.Name := sName;

    oClass := TAdvObjectClassHashEntry(FClassHashTable.Get(oLookup));

    If Assigned(oClass) Then
      Result := oClass.Data
    Else
      Result := Nil;
  Finally
    oLookup.Free;
  End;
End;


Function TAdvFactory.Make(Const sName : String) : TObject;
Var
  aClass : TClass;
Begin
  aClass := Get(sName);

  If Not Assigned(aClass) Then
    Error('Make', StringFormat('Class ''%s'' was not registered with the factory.', [sName]));

  Result := Make(aClass);
End;  


Function TAdvFactory.Make(Const aClass: TClass): TObject;
Begin
  If aClass.InheritsFrom(TAdvObject) Then
    Result := TAdvObjectClass(aClass).Create
  Else
    Result := aClass.Create;
End;


Function TAdvFactory.IsEquivalentClass(Const aClass : TClass; Const sName : String): Boolean;
Begin
  Result := Assigned(aClass) And (StringEquals(aClass.ClassName, sName) Or (aClass = Get(sName)));
End;


Function TAdvFactory.IsRegisteredClass(Const aClass : TClass): Boolean;
Var
  oIterator : TAdvObjectClassHashTableIterator;
Begin
  Assert(Condition(Assigned(aClass), 'IsRegisteredClass', 'Class to be validated must be assigned.'));

  Result := ActiveClassTracking;

  If Result Then
  Begin
    // TODO: more optimal implementation for finding if a TClass is registered.

    oIterator := TAdvObjectClassHashTableIterator(FClassHashTable.Iterator);
    Try
      oIterator.First;

      While oIterator.More And Not Result Do
      Begin
        Result := aClass = oIterator.Current;

        oIterator.Next;
      End;
    Finally
      oIterator.Free;
    End;
  End;
End;


function TAdvFactory.Lives: Boolean;
begin
  Result := (FObjectProfiler.Count > 0);
end;

Procedure TAdvFactory.Lock;
Begin 
  FCritical.Lock;
  Inc(FCriticalDepth);
End;  


Procedure TAdvFactory.Unlock;
Begin 
  Dec(FCriticalDepth);
  FCritical.Unlock;
End;  


Function TAdvFactory.Nested : Boolean;
Begin
  Result := (FCriticalDepth > 1);
End;


Function TAdvFactory.Construct(oObject: TObject; Out iSerial : TAdvFactorySerial): Boolean;
Begin 
  // Add the object to the Object hash and return a unique serial number.

  Result := True;
  iSerial := SERIAL_INVALID;

  If ActiveObjectTracking Then
  Begin
    Lock;
    Try
      If Not Nested Then
      Begin
        FObjectProfiler.Add(oObject);
        iSerial := FObjectProfiler.Total;
      End;
    Finally
      Unlock;
    End;
  End;
End;


Function TAdvFactory.Construct(oObject: TObject) : Boolean;
Begin
  // Add the object to the Object hash.

  Result := True;

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
        FObjectProfiler.Add(oObject);
    Finally
      Unlock;
    End;  
  End;  
End;  


Function TAdvFactory.Destruct(oObject: TObject) : Boolean;
Begin 
  // Remove the object from the Object hash.

  Result := True;

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
      Begin 
        If Not FObjectProfiler.Delete(oObject) Then
          Error('Destruct', 'Object reference could not be found for destruction.');
      End;  
    Finally
      Unlock;
    End;  
  End;  
End;  


Function TAdvFactory.Valid(oObject: TObject): Boolean;
Begin 
  // Returns true if the object is assigned, valid and of the correct class.

  Result := True;

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
        Result := FObjectProfiler.Exists(oObject);
    Finally
      Unlock;
    End;  
  End;  
End;  


Procedure TAdvFactory.Track(oObject: TObject);
Begin 
  // Mark the Object as requiring a breakpoint if Notify is called with the same Object.

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
        FObjectProfiler.Breakpoint[oObject] := True;
    Finally
      Unlock;
    End;  
  End;  
End;  


Procedure TAdvFactory.Notify(oObject: TObject);
Begin 
  // Interrupt the application execution with a breakpoint if the Object has been tracked.

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
      Begin 
        If FObjectProfiler.Breakpoint[oObject] Then
          ThreadBreakpoint;
      End;  
    Finally
      Unlock;
    End;  
  End;  
End;


Function TAdvFactory.MarkDestructable(oObject: TObject) : Boolean;
Begin 
  // Mark the object as Destructable - not ever needing to be freed and won't turn up in leak profiler.

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
        FObjectProfiler.Permanent[oObject] := False;
    Finally
      Unlock;
    End;  
  End;  

  Result := True;
End;  


Function TAdvFactory.MarkIndestructable(oObject: TObject) : Boolean;
Begin 
  // Mark the object as Indestructable - not ever needing to be freed and won't turn up in leak profiler.

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
        FObjectProfiler.Permanent[oObject] := True;
    Finally
      Unlock;
    End;  
  End;  

  Result := True;
End;  


Function TAdvFactory.IsIndestructable(oObject: TObject): Boolean;
Begin 
  Result := False;

  If ActiveObjectTracking Then
  Begin 
    Lock;
    Try
      If Not Nested Then
        Result := FObjectProfiler.Permanent[oObject];
    Finally
      Unlock;
    End;  
  End;  
End;  


Function TAdvFactory.Freeze(oObject: TObject): Boolean;
Begin 
  Result := True;

  // Mark the object as frozen (immutable).
  If ActiveObjectTracking Then
  Begin
    Lock;
    Try
      If Not Nested Then
        FObjectProfiler.Frozen[oObject] := True;
    Finally
      Unlock;
    End;
  End;
End;


Function TAdvFactory.IsFrozen(oObject: TObject): Boolean;
Begin 
  Result := False;

  If ActiveObjectTracking Then
  Begin
    Lock;
    Try
      If Not Nested Then
        Result := FObjectProfiler.Frozen[oObject];
    Finally
      Unlock;
    End;  
  End;  
End;  



Procedure Initialise;
Begin 
  gFactory := TAdvFactory.Create;
  gFactory.Open;
End;  


Procedure Finalise;
Var
  oTemporary : TAdvFactory;
Begin 
  Try
    gFactory.Close;

    oTemporary := gFactory;
    Try
      gFactory := Nil;

//      If oTemporary.Profiled And oTemporary.Lives Then
 //       oTemporary.Report;
    Finally
      oTemporary.Free;
    End;  
  Except
    MessageBox(0, PChar(ExceptObject.Message), 'Finalise', MB_OK);
  End;  
End;


procedure TAdvFactory.RegisterClasses(const aClassArray: array of TClass);
Var
  iClassIndex : Integer;
Begin
  For iClassIndex := Low(aClassArray) To High(aClassArray) Do
    RegisterClass(aClassArray[iClassIndex]);
end;

Initialization
  Initialise;
Finalization
  Finalise;
End. // AdvFactories //

