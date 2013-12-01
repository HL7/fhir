Unit AdvSynchronizationRegistries;

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
  ThreadSupport, MathSupport, StringSupport, DateSupport,
  AdvExclusiveCriticalSections, AdvObjects, AdvObjectLists, AdvTimeControllers, AdvIntegerMatches;


Type
  TAdvSynchronizationLockEventStatus = (AdvSynchronizationLockEventStatusWaitingForRead, AdvSynchronizationLockEventStatusWaitingForWrite,
    AdvSynchronizationLockEventStatusLockedForRead, AdvSynchronizationLockEventStatusLockedForWrite);

  TAdvSynchronizationLockEvent = Class(TAdvObject)
    Private
      FEventStatus : TAdvSynchronizationLockEventStatus;
      FEventTimestamp : TDateTime;
      FEventSequence : Int64;

    Public
      Function Link : TAdvSynchronizationLockEvent;

      Procedure Assign(oObject : TAdvObject); Override;

      Function IsWaiting : Boolean;

      Procedure EventStatusWaitingForRead;
      Procedure EventStatusWaitingForWrite;
      Procedure EventStatusLockedForRead;
      Procedure EventStatusLockedForWrite;

      Property EventSequence : Int64 Read FEventSequence Write FEventSequence;
      Property EventTimestamp : TDateTime Read FEventTimestamp Write FEventTimestamp;
      Property EventStatus : TAdvSynchronizationLockEventStatus Read FEventStatus Write FEventStatus;
  End;

  TAdvSynchronizationLockEventList = Class(TAdvObjectList)
    Private
      Function GetLockEventByIndex(Const iIndex : Integer) : TAdvSynchronizationLockEvent;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function CompareByEventSequence(pA, pB : Pointer) : Integer;

    Public
      Procedure SortedByEventSequence;

      Property LockEventByIndex[Const iIndex : Integer] : TAdvSynchronizationLockEvent Read GetLockEventByIndex; Default;
  End;

  TAdvSynchronizationThreadLock = Class(TAdvObject)
    Private
      FThreadIdentifier : TThreadID;
      FLockIdentifier : Integer;

      FLockEventList : TAdvSynchronizationLockEventList;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Assign(oObject : TAdvObject); Override;

      Procedure PushLockEvent(oLockEvent : TAdvSynchronizationLockEvent);
      Function PeekLockEvent : TAdvSynchronizationLockEvent;
      Function PopLockEvent : TAdvSynchronizationLockEvent;

      Property ThreadIdentifier : TThreadID Read FThreadIdentifier Write FThreadIdentifier;
      Property LockIdentifier : Integer Read FLockIdentifier Write FLockIdentifier;
      Property LockEventList : TAdvSynchronizationLockEventList Read FLockEventList;
  End;

  TAdvSynchronizationThreadLockList = Class(TAdvObjectList)
    Private
      Function GetThreadLockByIndex(Const iIndex : Integer) : TAdvSynchronizationThreadLock;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function CompareByThreadIdentifierAndLockIdentifier(pA, pB : Pointer) : Integer;

    Public
      Procedure SortedByThreadIdentifierAndLockIdentifier;

      Function IndexByThreadIdentifierAndLockIdentifier(Const iThreadIdentifier : TThreadID; Const iLockIdentifier : Integer) : Integer;

      Property ThreadLockByIndex[Const iIndex : Integer] : TAdvSynchronizationThreadLock Read GetThreadLockByIndex; Default;
  End;

  TAdvSynchronizationRegistry = Class(TAdvObject)
    Private
      FThreadLockListCriticalSection : TAdvExclusiveCriticalSection;
      FTimeController : TAdvTimeController;

      FThreadLockList : TAdvSynchronizationThreadLockList;

      FThreadIdentifierNextEventSequenceMatch : TAdvIntegerMatch;

      Function GetTimeController : TAdvTimeController;
      Procedure SetTimeController(Const Value : TAdvTimeController);

    Protected
      Procedure RegisterWaitingForLock(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);
      Procedure RegisterNoLongerWaitingForLock(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);
      Procedure RegisterLocked(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);
      Procedure RegisterUnlocked(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvSynchronizationRegistry; 

      Function Active : Boolean;

      Procedure Query(oThreadLockList : TAdvSynchronizationThreadLockList);

      Procedure RegisterWaitingForRead(Const iLockIdentifier : Integer);
      Procedure RegisterWaitingForWrite(Const iLockIdentifier : Integer);

      Procedure RegisterNoLongerWaitingForRead(Const iLockIdentifier : Integer);
      Procedure RegisterNoLongerWaitingForWrite(Const iLockIdentifier : Integer);

      Procedure RegisterLockedForRead(Const iLockIdentifier : Integer);
      Procedure RegisterLockedForWrite(Const iLockIdentifier : Integer);

      Procedure RegisterUnlockedForRead(Const iLockIdentifier : Integer);
      Procedure RegisterUnlockedForWrite(Const iLockIdentifier : Integer);

      Property TimeController : TAdvTimeController Read GetTimeController Write SetTimeController;
  End;


Procedure PrepareSynchronizationRegistry;
Procedure TerminateSynchronizationRegistry;

Function HasSynchronizationRegistry : Boolean;
Function SynchronizationRegistry : TAdvSynchronizationRegistry;


Implementation


Uses
  AdvExceptions,
  AdvParameters;


Var
  gUseSynchronizationRegistry : Boolean;
  gSynchronizationRegistry : TAdvSynchronizationRegistry;


Procedure PrepareSynchronizationRegistry;
Var
  oParameters : TAdvParameters;
Begin
  Assert(Not Assigned(gSynchronizationRegistry), 'gSynchronizationRegistry is already assigned.');

  gSynchronizationRegistry := TAdvSynchronizationRegistry.Create;

  oParameters := TAdvParameters.Create;
  Try
    gUseSynchronizationRegistry := Not oParameters.Switched('NoDeadlockDetection');
  Finally
    oParameters.Free;
  End;
End;


Procedure TerminateSynchronizationRegistry;
Begin
  Assert(Assigned(gSynchronizationRegistry), 'gSynchronizationRegistry is not assigned.');

  gSynchronizationRegistry.Free;
  gSynchronizationRegistry := Nil;
End;


Function HasSynchronizationRegistry : Boolean;
Begin
  Result := Assigned(gSynchronizationRegistry) And gSynchronizationRegistry.Active;
End;


Function SynchronizationRegistry : TAdvSynchronizationRegistry;
Begin
  Assert(Assigned(gSynchronizationRegistry), 'gSynchronizationRegistry was not assigned.');

  Result := gSynchronizationRegistry;
End;


Function TAdvSynchronizationLockEvent.Link : TAdvSynchronizationLockEvent;
Begin
  Result := TAdvSynchronizationLockEvent(Inherited Link);
End;


Procedure TAdvSynchronizationLockEvent.Assign(oObject : TAdvObject);
Begin
  Inherited;

  FEventStatus := TAdvSynchronizationLockEvent(oObject).EventStatus;
  FEventTimestamp := TAdvSynchronizationLockEvent(oObject).EventTimestamp;
  FEventSequence := TAdvSynchronizationLockEvent(oObject).EventSequence;
End;


Function TAdvSynchronizationLockEvent.IsWaiting : Boolean;
Begin
  Result := FEventStatus In [AdvSynchronizationLockEventStatusWaitingForRead, AdvSynchronizationLockEventStatusWaitingForWrite];
End;


Procedure TAdvSynchronizationLockEvent.EventStatusWaitingForRead;
Begin
  FEventStatus := AdvSynchronizationLockEventStatusWaitingForRead;
End;


Procedure TAdvSynchronizationLockEvent.EventStatusWaitingForWrite;
Begin
  FEventStatus := AdvSynchronizationLockEventStatusWaitingForWrite;
End;


Procedure TAdvSynchronizationLockEvent.EventStatusLockedForRead;
Begin
  FEventStatus := AdvSynchronizationLockEventStatusLockedForRead;
End;


Procedure TAdvSynchronizationLockEvent.EventStatusLockedForWrite;
Begin
  FEventStatus := AdvSynchronizationLockEventStatusLockedForWrite;
End;


Function TAdvSynchronizationLockEventList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvSynchronizationLockEvent;
End;


Function TAdvSynchronizationLockEventList.CompareByEventSequence(pA, pB : Pointer) : Integer;
Begin
  Result := DateTimeCompare(TAdvSynchronizationLockEvent(pA).EventSequence, TAdvSynchronizationLockEvent(pB).EventSequence);
End;


Procedure TAdvSynchronizationLockEventList.SortedByEventSequence;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByEventSequence);
End;


Function TAdvSynchronizationLockEventList.GetLockEventByIndex(Const iIndex : Integer) : TAdvSynchronizationLockEvent;
Begin
  Result := TAdvSynchronizationLockEvent(ObjectByIndex[iIndex]);
End;


Constructor TAdvSynchronizationThreadLock.Create;
Begin
  Inherited;

  FLockEventList := TAdvSynchronizationLockEventList.Create;
  FLockEventList.SortedByEventSequence;
End;


Destructor TAdvSynchronizationThreadLock.Destroy;
Begin
  FLockEventList.Free;

  Inherited;
End;


Procedure TAdvSynchronizationThreadLock.Assign(oObject: TAdvObject);
Begin
  Inherited;

  FThreadIdentifier := TAdvSynchronizationThreadLock(oObject).ThreadIdentifier;
  FLockIdentifier := TAdvSynchronizationThreadLock(oObject).LockIdentifier;
  FLockEventList.Assign(TAdvSynchronizationThreadLock(oObject).LockEventList);
End;


Procedure TAdvSynchronizationThreadLock.PushLockEvent(oLockEvent : TAdvSynchronizationLockEvent);
Begin
  Assert(Invariants('PushLockEvent', oLockEvent, TAdvSynchronizationLockEvent, 'oLockEvent'));

  FLockEventList.Add(oLockEvent);
End;


Function TAdvSynchronizationThreadLock.PeekLockEvent : TAdvSynchronizationLockEvent;
Begin
  Assert(Condition(Not FLockEventList.IsEmpty, 'PeekLockEvent', 'Cannot peek last lock event as there are no lock events for this context.'));

  Result := FLockEventList[FLockEventList.Count - 1];

  Assert(Invariants('PeekLockEvent', Result, TAdvSynchronizationLockEvent, 'Result'));
End;


Function TAdvSynchronizationThreadLock.PopLockEvent : TAdvSynchronizationLockEvent;
Begin
  Result := PeekLockEvent.Link;

  FLockEventList.DeleteByIndex(FLockEventList.Count - 1);
End;


Function TAdvSynchronizationThreadLockList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvSynchronizationThreadLock;
End;


Function TAdvSynchronizationThreadLockList.CompareByThreadIdentifierAndLockIdentifier(pA, pB : Pointer) : Integer;
Begin
  Result := IntegerCompare(TAdvSynchronizationThreadLock(pA).ThreadIdentifier, TAdvSynchronizationThreadLock(pB).ThreadIdentifier);

  If Result = 0 Then
    Result := IntegerCompare(TAdvSynchronizationThreadLock(pA).LockIdentifier, TAdvSynchronizationThreadLock(pB).LockIdentifier);
End;


Procedure TAdvSynchronizationThreadLockList.SortedByThreadIdentifierAndLockIdentifier;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByThreadIdentifierAndLockIdentifier);
End;


Function TAdvSynchronizationThreadLockList.IndexByThreadIdentifierAndLockIdentifier(Const iThreadIdentifier : TThreadID; Const iLockIdentifier : Integer) : Integer;
Var
  oThreadLock : TAdvSynchronizationThreadLock;
Begin
  oThreadLock := TAdvSynchronizationThreadLock.Create;
  Try
    oThreadLock.ThreadIdentifier := iThreadIdentifier;
    oThreadLock.LockIdentifier := iLockIdentifier;

    Result := IndexBy(oThreadLock, {$IFDEF FPC}@{$ENDIF}CompareByThreadIdentifierAndLockIdentifier);
  Finally
    oThreadLock.Free;
  End;
End;


Function TAdvSynchronizationThreadLockList.GetThreadLockByIndex(Const iIndex : Integer) : TAdvSynchronizationThreadLock;
Begin
  Result := TAdvSynchronizationThreadLock(ObjectByIndex[iIndex]);
End;


Constructor TAdvSynchronizationRegistry.Create;
Begin
  Inherited;

  FThreadLockListCriticalSection := TAdvExclusiveCriticalSection.Create;
  
  FTimeController := Nil;

  FThreadLockList := TAdvSynchronizationThreadLockList.Create;
  FThreadLockList.SortedByThreadIdentifierAndLockIdentifier;

  FThreadIdentifierNextEventSequenceMatch := TAdvIntegerMatch.Create;
  FThreadIdentifierNextEventSequenceMatch.SortedByKey;
  FThreadIdentifierNextEventSequenceMatch.Default := 1;
  FThreadIdentifierNextEventSequenceMatch.Forced := True;
End;


Destructor TAdvSynchronizationRegistry.Destroy;
Begin
  FThreadIdentifierNextEventSequenceMatch.Free;
  FThreadLockList.Free;
  FTimeController.Free;
  FThreadLockListCriticalSection.Free;

  Inherited;
End;


Function TAdvSynchronizationRegistry.Link : TAdvSynchronizationRegistry;
Begin
  Result := TAdvSynchronizationRegistry(Inherited Link);
End;


Function TAdvSynchronizationRegistry.Active : Boolean;
Begin
  Result := gUseSynchronizationRegistry;
End;


Procedure TAdvSynchronizationRegistry.Query(oThreadLockList : TAdvSynchronizationThreadLockList);
Begin
  Assert(Invariants('Query', oThreadLockList, TAdvSynchronizationThreadLockList, 'oThreadLockList'));

  FThreadLockListCriticalSection.Lock;
  Try
    oThreadLockList.Assign(FThreadLockList);
  Finally
    FThreadLockListCriticalSection.Unlock;
  End;
End;


Procedure TAdvSynchronizationRegistry.RegisterWaitingForLock(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);
Var
  iThreadIdentifier : Integer;
  iThreadLockIndex : Integer;
  iThreadNextSequenceMatchIndex : Integer;
  oThreadLock : TAdvSynchronizationThreadLock;
  oLockEvent : TAdvSynchronizationLockEvent;
Begin
  Assert(Condition(aEventStatus In [AdvSynchronizationLockEventStatusWaitingForRead, AdvSynchronizationLockEventStatusWaitingForWrite], 'RegisterWaitingForLock', 'Invalid waiting for lock event status.'));

  oLockEvent := TAdvSynchronizationLockEvent.Create;
  Try
    iThreadIdentifier := ThreadID;

    oLockEvent.EventTimestamp := TimeController.UniversalDateTime;
    oLockEvent.EventStatus := aEventStatus;

    FThreadLockListCriticalSection.Lock;
    Try
      iThreadLockIndex := FThreadLockList.IndexByThreadIdentifierAndLockIdentifier(iThreadIdentifier, iLockIdentifier);

      If Not FThreadLockList.ExistsByIndex(iThreadLockIndex) Then
      Begin
        oThreadLock := TAdvSynchronizationThreadLock.Create;
        oThreadLock.ThreadIdentifier := iThreadIdentifier;
        oThreadLock.LockIdentifier := iLockIdentifier;

        FThreadLockList.Add(oThreadLock);
      End
      Else
      Begin
        oThreadLock := FThreadLockList[iThreadLockIndex];
      End;

      Assert(Invariants('RegisterWaitingForLock', oThreadLock, TAdvSynchronizationThreadLock, 'oThreadLock'));

      iThreadNextSequenceMatchIndex := FThreadIdentifierNextEventSequenceMatch.IndexByKey(iThreadIdentifier);

      If FThreadIdentifierNextEventSequenceMatch.ExistsByIndex(iThreadNextSequenceMatchIndex) Then
      Begin
        oLockEvent.EventSequence := FThreadIdentifierNextEventSequenceMatch.ValueByIndex[iThreadNextSequenceMatchIndex];

        FThreadIdentifierNextEventSequenceMatch.ValueByIndex[iThreadNextSequenceMatchIndex] := oLockEvent.EventSequence + 1;
      End
      Else
      Begin
        oLockEvent.EventSequence := 1;

        FThreadIdentifierNextEventSequenceMatch.Add(iThreadIdentifier, 2);
      End;

      oThreadLock.PushLockEvent(oLockEvent.Link);
    Finally
      FThreadLockListCriticalSection.Unlock;
    End;
  Finally
    oLockEvent.Free;
  End;
End;


Procedure TAdvSynchronizationRegistry.RegisterNoLongerWaitingForLock(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);
Const
  AdvSynchronizationLockEventStatusRelevantWaitingForLockStatusArray : Array [TAdvSynchronizationLockEventStatus] Of TAdvSynchronizationLockEventStatus =
    (AdvSynchronizationLockEventStatusWaitingForRead, AdvSynchronizationLockEventStatusWaitingForWrite, AdvSynchronizationLockEventStatusWaitingForRead, AdvSynchronizationLockEventStatusWaitingForWrite);
Var
  iThreadIdentifier : TThreadID;
  iThreadLockIndex : Integer;
  oLastLockEvent : TAdvSynchronizationLockEvent;
  oThreadLock : TAdvSynchronizationThreadLock;
Begin
  Assert(Condition(aEventStatus In [AdvSynchronizationLockEventStatusWaitingForRead, AdvSynchronizationLockEventStatusWaitingForWrite], 'RegisterNoLongerWaitingForLock', 'Invalid locked event status.'));

  iThreadIdentifier := ThreadID;

  FThreadLockListCriticalSection.Lock;
  Try
    iThreadLockIndex := FThreadLockList.IndexByThreadIdentifierAndLockIdentifier(iThreadIdentifier, iLockIdentifier);

    If Not FThreadLockList.ExistsByIndex(iThreadLockIndex) Then
      Error('RegisterNoLongerWaitingForLock', 'Thread lock for thread ''' + IntegerToString(iThreadIdentifier) + ''' does not exist.');

    oThreadLock := FThreadLockList[iThreadLockIndex];

    oLastLockEvent := oThreadLock.PopLockEvent;
    Try
      If oLastLockEvent.EventStatus <> AdvSynchronizationLockEventStatusRelevantWaitingForLockStatusArray[aEventStatus] Then
        Error('RegisterNoLongerWaitingForLock', 'Last lock event should be relevant waiting for lock status.');
    Finally
      oLastLockEvent.Free;
    End;

    // Destroy the thread lock if it has no active lock events so that there is no way we can get
    // conflicting object references in the lifetime of the server. 
    If oThreadLock.LockEventList.IsEmpty Then
      FThreadLockList.DeleteByIndex(iThreadLockIndex);
  Finally
    FThreadLockListCriticalSection.Unlock;
  End;
End;


Procedure TAdvSynchronizationRegistry.RegisterLocked(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);
Const
  AdvSynchronizationLockEventStatusRelevantWaitingForLockStatusArray : Array [TAdvSynchronizationLockEventStatus] Of TAdvSynchronizationLockEventStatus =
    (AdvSynchronizationLockEventStatusLockedForRead, AdvSynchronizationLockEventStatusLockedForRead, AdvSynchronizationLockEventStatusWaitingForRead, AdvSynchronizationLockEventStatusWaitingForWrite);
Var
  iThreadIdentifier : TThreadID;
  iThreadLockIndex : Integer;
  oLastLockEvent : TAdvSynchronizationLockEvent;
  oLockEvent : TAdvSynchronizationLockEvent;
  oThreadLock : TAdvSynchronizationThreadLock;
Begin
  Assert(Condition(aEventStatus In [AdvSynchronizationLockEventStatusLockedForRead, AdvSynchronizationLockEventStatusLockedForWrite], 'RegisterLocked', 'Invalid locked event status.'));

  oLockEvent := TAdvSynchronizationLockEvent.Create;
  Try
    iThreadIdentifier := ThreadID;

    oLockEvent.EventTimestamp := TimeController.UniversalDateTime;
    oLockEvent.EventStatus := aEventStatus;

    FThreadLockListCriticalSection.Lock;
    Try
      iThreadLockIndex := FThreadLockList.IndexByThreadIdentifierAndLockIdentifier(iThreadIdentifier, iLockIdentifier);

      If Not FThreadLockList.ExistsByIndex(iThreadLockIndex) Then
        Error('RegisterLocked', 'Thread lock for thread ''' + IntegerToString(iThreadIdentifier) + ''' does not exist.');

      oThreadLock := FThreadLockList[iThreadLockIndex];

      oLastLockEvent := oThreadLock.PopLockEvent;
      Try
        If oLastLockEvent.EventStatus <> AdvSynchronizationLockEventStatusRelevantWaitingForLockStatusArray[aEventStatus] Then
          Error('RegisterLocked', 'Last lock event should be relevant waiting for lock status.');

        oLockEvent.EventSequence := oLastLockEvent.EventSequence;

        oThreadLock.PushLockEvent(oLockEvent.Link);
      Finally
        oLastLockEvent.Free;
      End;
    Finally
      FThreadLockListCriticalSection.Unlock;
    End;
  Finally
    oLockEvent.Free;
  End;
End;


Procedure TAdvSynchronizationRegistry.RegisterWaitingForRead(Const iLockIdentifier : Integer);
Begin
  RegisterWaitingForLock(iLockIdentifier, AdvSynchronizationLockEventStatusWaitingForRead);
End;


Procedure TAdvSynchronizationRegistry.RegisterWaitingForWrite(Const iLockIdentifier : Integer);
Begin
  RegisterWaitingForLock(iLockIdentifier, AdvSynchronizationLockEventStatusWaitingForWrite);
End;


Procedure TAdvSynchronizationRegistry.RegisterNoLongerWaitingForRead(Const iLockIdentifier : Integer);
Begin
  RegisterNoLongerWaitingForLock(iLockIdentifier, AdvSynchronizationLockEventStatusWaitingForRead);
End;


Procedure TAdvSynchronizationRegistry.RegisterNoLongerWaitingForWrite(Const iLockIdentifier : Integer);
Begin
  RegisterNoLongerWaitingForLock(iLockIdentifier, AdvSynchronizationLockEventStatusWaitingForWrite);
End;


Procedure TAdvSynchronizationRegistry.RegisterLockedForRead(Const iLockIdentifier : Integer);
Begin
  RegisterLocked(iLockIdentifier, AdvSynchronizationLockEventStatusLockedForRead);
End;


Procedure TAdvSynchronizationRegistry.RegisterLockedForWrite(Const iLockIdentifier : Integer);
Begin
  RegisterLocked(iLockIdentifier, AdvSynchronizationLockEventStatusLockedForWrite);
End;


Procedure TAdvSynchronizationRegistry.RegisterUnlocked(Const iLockIdentifier : Integer; Const aEventStatus : TAdvSynchronizationLockEventStatus);
Var
  iThreadIdentifier : TThreadID;
  iThreadLockIndex : Integer;
  iThreadNextSequenceMatchIndex : Integer;
  oThreadLock : TAdvSynchronizationThreadLock;
  oLastLockEvent : TAdvSynchronizationLockEvent;
Begin
  iThreadIdentifier := ThreadID;

  FThreadLockListCriticalSection.Lock;
  Try
    iThreadLockIndex := FThreadLockList.IndexByThreadIdentifierAndLockIdentifier(iThreadIdentifier, iLockIdentifier);

    If Not FThreadLockList.ExistsByIndex(iThreadLockIndex) Then
      Error('RegisterUnlocked', 'Thread lock for thread ''' + IntegerToString(iThreadIdentifier) + ''' does not exist.');

    oThreadLock := FThreadLockList[iThreadLockIndex];

    oLastLockEvent := oThreadLock.PopLockEvent;
    Try
      If oLastLockEvent.EventStatus <> aEventStatus Then
        Error('RegisterUnlocked', 'Last lock event should have same event status.');

      iThreadNextSequenceMatchIndex := FThreadIdentifierNextEventSequenceMatch.IndexByKey(iThreadIdentifier);

      Assert(Condition(FThreadIdentifierNextEventSequenceMatch.ExistsByIndex(iThreadNextSequenceMatchIndex), 'RegisterUnlocked', 'Could not find thread lock for this combination of thread and lock identifier.'));

      FThreadIdentifierNextEventSequenceMatch.ValueByIndex[iThreadNextSequenceMatchIndex] := FThreadIdentifierNextEventSequenceMatch.ValueByIndex[iThreadNextSequenceMatchIndex] - 1;
    Finally
      oLastLockEvent.Free;
    End;

    // Destroy the thread lock if it has no active lock events so that there is no way we can get
    // conflicting object references in the lifetime of the server. 
    If oThreadLock.LockEventList.IsEmpty Then
      FThreadLockList.DeleteByIndex(iThreadLockIndex);
  Finally
    FThreadLockListCriticalSection.Unlock;
  End;
End;


Procedure TAdvSynchronizationRegistry.RegisterUnlockedForRead(Const iLockIdentifier : Integer);
Begin
  RegisterUnlocked(iLockIdentifier, AdvSynchronizationLockEventStatusLockedForRead);
End;


Procedure TAdvSynchronizationRegistry.RegisterUnlockedForWrite(Const iLockIdentifier : Integer);
Begin
  RegisterUnlocked(iLockIdentifier, AdvSynchronizationLockEventStatusLockedForWrite);
End;


Function TAdvSynchronizationRegistry.GetTimeController : TAdvTimeController;
Begin
  Assert(Invariants('GetTimeController', FTimeController, TAdvTimeController, 'FTimeController'));

  Result := FTimeController;
End;


Procedure TAdvSynchronizationRegistry.SetTimeController(Const Value : TAdvTimeController);
Begin
  Assert(Invariants('SetTimeController', Value, TAdvTimeController, 'Value'));

  FTimeController.Free;
  FTimeController := Value;
End;


End. // AdvSynchronizationRegistries //
