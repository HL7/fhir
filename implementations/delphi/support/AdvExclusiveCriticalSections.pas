Unit AdvExclusiveCriticalSections;


{! 4 !}


Interface


Uses
  Windows,
  AdvObjects, AdvThreads;


Type
  TAdvExclusiveCriticalSection = Class(TAdvObject)
    Private
      FHandle : TRTLCriticalSection;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Lock; Virtual;
      Procedure Unlock; Virtual;
      Function TryLock : Boolean; Virtual;

      Property Handle : TRTLCriticalSection Read FHandle;
  End;

  TAdvExclusiveStateCriticalSection = Class(TAdvExclusiveCriticalSection)
    Private
      FThreadID : TAdvThreadHandle;
      FNested : Integer;

    Protected
      Procedure Enter;
      Procedure Leave;

    Public
      Procedure Lock; Override;
      Procedure Unlock; Override;
      Function TryLock : Boolean; Override;

      Function IsNestedLocked : Boolean;
      Function IsLocked : Boolean;
      Function IsLockedToThread(Const aThread : TAdvThreadHandle) : Boolean;
      Function IsLockedToCurrentThread : Boolean;
  End;


Implementation


Uses
  AdvSynchronizationRegistries;


Constructor TAdvExclusiveCriticalSection.Create;
Begin
  Inherited;

  InitializeCriticalSection(FHandle);
End;


Destructor TAdvExclusiveCriticalSection.Destroy;
Begin
  DeleteCriticalSection(FHandle);

  Inherited;
End;  


Procedure TAdvExclusiveCriticalSection.Lock;
Begin 
  EnterCriticalSection(FHandle);
End;


Function TAdvExclusiveCriticalSection.TryLock : Boolean;
Begin 
  Result := TryEnterCriticalSection(FHandle);
End;  


Procedure TAdvExclusiveCriticalSection.Unlock;
Begin
  LeaveCriticalSection(FHandle);
End;


Procedure TAdvExclusiveStateCriticalSection.Lock;
Begin 
  Inherited;

  Enter;
End;  


Procedure TAdvExclusiveStateCriticalSection.Unlock;
Begin 
  Assert(Condition(IsLockedToCurrentThread, 'Unlock', 'Cannot unlock as the critical section is not locked to the current thread.'));

  Leave;

  Inherited;
End;  


Function TAdvExclusiveStateCriticalSection.TryLock : Boolean;
Begin 
  Result := Inherited TryLock;

  If Result Then
    Enter;
End;  


Function TAdvExclusiveStateCriticalSection.IsLocked : Boolean;
Begin 
  Result := FThreadID <> INVALID_HANDLE_VALUE;
End;  


Function TAdvExclusiveStateCriticalSection.IsLockedToCurrentThread : Boolean;
Begin 
  Result := IsLockedToThread(GetCurrentThreadID);
End;  


Function TAdvExclusiveStateCriticalSection.IsLockedToThread(Const aThread: TAdvThreadHandle): Boolean;
Begin 
  Result := FThreadID = aThread;
End;  


Function TAdvExclusiveStateCriticalSection.IsNestedLocked : Boolean;
Begin 
  Result := FNested > 1;
End;  


Procedure TAdvExclusiveStateCriticalSection.Enter;
Begin 
  If FNested = 0 Then
    FThreadID := GetCurrentThreadID;

  Inc(FNested);
End;  


Procedure TAdvExclusiveStateCriticalSection.Leave;
Begin 
  Dec(FNested);

  If FNested = 0 Then
    FThreadID := INVALID_HANDLE_VALUE;
End;


End. // AdvExclusiveCriticalSections //
