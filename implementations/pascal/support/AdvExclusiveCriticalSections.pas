Unit AdvExclusiveCriticalSections;

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
