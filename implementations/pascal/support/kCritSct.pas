unit kCritSct;

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

interface

{$OVERFLOWCHECKS OFF}

// Do not change the uses clause of this unit without consulting NDM

uses
  Windows;

{--- CLR types - dealing with CLR and win32 differences ----}
const
  NO_THREAD = 0;

type
  Thread = Cardinal;

  TCriticalSection = class(TObject)
  Private
    FCritSect: TRTLCriticalSection;

    // Pointers in the linked list of critical sections. Link list maintained
    // so as that we can track and report status of each TCriticalSection
    // instance in the system
    FNext, FPrev: TCriticalSection;

    FOwnID: Integer;                 // unique serial number assigned to all critical sections
    FCategory: String;                // category in the lock list
    FName: String;                   // Name of the critical section object
    FLockName: Array of String;      // Name of the current Lock (first one to grab)
    FDelayCount: Integer;            // Number of times there has been a failed attempt to lock a critical section
    FUseCount: Integer;              // The amount of times there has been a succesful attempt to lock a critical section
    FCurrLockTime: Int64;            // Time which the owning thread obtained the lock for the thread
    FTimeLocked: Int64;              // Total length of time which the critical section has been locked for
    FDelayTime: Int64;               // Total length of time that threads have been waiting to obtain a lock after a failed attempt
    FEntryCount: Integer;            // Amount of times the thread owning the critical section has called Lock without calling UnLock. Used for recursion
    FLockThread : Thread;

    procedure MarkEntered;
    procedure MarkLeft;
    Function DebugSummary : String;
  Public
    constructor Create; Overload;
    constructor Create(AName: String); Overload;
    destructor Destroy; Override;

    // core functionality
    procedure Lock; Overload;
    procedure Lock(const Name: String); Overload;
    procedure Unlock;
    procedure Enter; Overload;
    procedure Enter(const AName: String); Overload;
    procedure Leave;
    function Trylock: Boolean;
    function LockedToMe: Boolean; // mainly for assertion support
    procedure changeName(aName : String);


    // debugging support
    property Category: String Read FCategory Write FCategory;
    class function CurrentCount: Integer;
    // use with caution - not thread safe
    property OwnID: Integer Read FOwnID;
    property Name: String Read FName;
//    property LockName: String Read FLockName;
    property DelayCount: Integer Read FDelayCount;
    property UseCount: Integer Read FUseCount;
    property CurrLockTime: Int64 Read FCurrLockTime;
    property TimeLocked: Int64 Read FTimeLocked;
    property DelayTime: Int64 Read FDelayTime;
    property EntryCount: Integer Read FEntryCount;
    property LockThread: Thread Read FLockThread;
  end;

{$IFNDEF CLR}
  TWaitResult = (wrSignaled, wrTimeout, wrAbandoned, wrError);

  // Event Object: Linux OK
  TEvent = class(TObject)
  Private
    FLastError: Integer;
    Event: THandle;
  Public
    constructor Create(AutoReset: Boolean; Name: String);
    destructor Destroy; Override;
    function Wait(timeout: Cardinal): Boolean;
    function WaitRes(timeout: Cardinal): TWaitResult;
    procedure Signal;
    procedure UnSignal;

    property LastError: Integer Read FLastError;
    property Handle: THandle Read event; // exposed for call to WaitForMultipleEvents
  end;

  TSemaphore = class(TObject)
  Private
    FSem: THandle;
  Public
    constructor Create(CurrCount: Integer);
    destructor Destroy; Override;
    function Wait(timeout: Cardinal): TWaitResult;
    procedure Release;
    property Handle: THandle Read FSem;
  end;

function WaitForEvents(list: array of TEvent; TimeOut: Cardinal; WaitAll: Boolean): DWord;
  {$ENDIF}

Function CriticalSectionChecksPass(Var sMessage : String) : Boolean;

function DumpLocks : String;

implementation

uses
  SysUtils;

const
  ASSERT_UNIT = 'kCritSct';
  crlf = #13#10;
var
  GHaveCritSect : Boolean = False;
  GCritSct: TRTLCriticalSection;
  GQPFrequency : Int64;

  GFirst: TCriticalSection = NIL;
  GCount: Integer = 0;
  GTotal: Integer = 0;


procedure InitUnit;
begin
  QueryPerformanceFrequency(GQPFrequency);
  GQPFrequency := GQPFrequency div 1000; // in milliseconds
  InitializeCriticalSection(GCritSct);
  GHaveCritSect := true;
end;

{ TCriticalSection }
constructor TCriticalSection.Create;
begin
  inherited Create;
  FCategory := '';
  FName := ClassName;
  SetLength(FLockName, 0);
  FDelayCount := 0;
  FUseCount := 0;
  FCurrLockTime := 0;
  FTimeLocked := 0;
  FDelayTime := 0;
  FLockThread := NO_THREAD;
  FEntryCount := 0;
  if not GHaveCritSect then
    InitUnit;
  InitializeCriticalSection(FCritSect);
  EnterCriticalSection(GCritSct);
  try
    inc(GCount);
    inc(GTotal);
    FOwnID := GTotal;
    if GFirst = NIL then
      begin
      FNext := NIL;
      end
    else
      begin
      FNext := GFirst;
      FNext.FPrev := self;
      end;
    FPrev := NIL;
    GFirst := self;
  finally
    LeaveCriticalSection(GCritSct);
    end;
end;

procedure TCriticalSection.changeName(aName: String);
begin
  if LockedToMe then
    FName := aName;
end;

constructor TCriticalSection.Create(AName: String);
begin
  Create;
  FName := AName;
end;

destructor TCriticalSection.Destroy;
begin
  EnterCriticalSection(GCritSct);
  try
    dec(GCount);
    if FPrev = NIL then
      GFirst := FNext
    else
      FPrev.FNext := FNext;
    if FNext <> NIL then
      FNext.FPrev := FPrev;
  finally
    LeaveCriticalSection(GCritSct);
    end;
  DeleteCriticalSection(FCritSect);
  inherited;
end;


procedure TCriticalSection.MarkEntered;
begin
  assert((FLockThread = NO_THREAD) or (FLockThread = GetCurrentThreadId),
     'Thread '+inttostr(GetCurrentThreadId)+' entering critical section '+inttohex(integer(Self), 8)+'/'+inttohex(integer(@FCritSect), 8)+' owned '+inttostr(FEntryCount)+' times by '+inttostr(FLockThread));
  if FLockThread = GetCurrentThreadid then
    inc(FEntryCount)
  else
  begin
    FLockThread := GetCurrentThreadId;
    FEntryCount := 1;
    inc(FUseCount);
    QueryPerformanceCounter(FCurrLockTime);
  end;
  SetLength(FLockName, FEntryCount);
end;

procedure TCriticalSection.MarkLeft;
var
  LEndTime: Int64;
begin
  assert(FLockThread = GetCurrentThreadID);
  dec(FEntryCount);
  SetLength(FLockName, FEntryCount);
  if FEntryCount = 0 then
    begin
    FLockThread := NO_THREAD;
    QueryPerformanceCounter(LEndTime);
    FTimeLocked := FTimeLocked + (LEndTime - FCurrLockTime);
    FCurrLockTime := 0;
    end;
end;

function TCriticalSection.LockedToMe: Boolean;
begin
  Result := FLockThread = GetCurrentThreadId;
end;

procedure TCriticalSection.Lock;
var
  LStartTime: Int64;
begin
  // the current time is set by a successful trylock.
  if not TryLock then
    begin
    QueryPerformanceCounter(LStartTime);
    EnterCriticalSection(FCritSect);
    MarkEntered;
    FDelayTime := FDelayTime + (FCurrLockTime - LStartTime);
    inc(FDelayCount);
    end;
end;

procedure TCriticalSection.Lock(const Name: String);
begin
  Lock;
  FLockName[FEntryCount - 1] := Name;
end;

function TCriticalSection.TryLock: Boolean;
begin
  Result := TryEnterCriticalSection(FCritSect);
  if Result then
    begin
    MarkEntered;
    end;
end;

procedure TCriticalSection.Unlock;
begin
  If not LockedToMe then
  begin
    ChangeName('not locked to this thread');
  end
  else
  begin
    MarkLeft;
    LeaveCriticalSection(FCritSect);
  end;
end;

procedure TCriticalSection.Enter;
begin
  Lock;
end;

procedure TCriticalSection.Enter(const AName: String);
begin
  Lock(AName);
end;

procedure TCriticalSection.Leave;
begin
  UnLock;
end;

class function TCriticalSection.CurrentCount: Integer;
begin
  Result := GCount;
end;

function ms(count : int64) : integer;
begin
  result := count div GQPFrequency;
end;

function TCriticalSection.DebugSummary: String;
var
  i : integer;
begin
  Result := IntToStr(FOwnID)+' "'+FCategory+'" "'+FName+'" '+IntToStr(FDelayCount)+' '+
     IntToStr(FUseCount)+' ' +IntToStr(ms(FCurrLockTime))+' '+IntToStr(ms(FTimeLocked))+' '+IntToStr(ms(FDelayTime))+' '+
     IntToStr(FEntryCount)+' '+IntToStr(FLockThread)+' ';
  for i := 0 to High(FLockName) do
    result := result + '/' + FLockName[i];
end;

{ TEvent }
constructor TEvent.Create(AutoReset: Boolean; Name: String);
begin
  inherited Create;
  event := CreateEvent(NIL, not AutoReset, False, NIL {unnamed event});
end;

destructor TEvent.Destroy;
begin
  closehandle(event);
  inherited Destroy;
end;

function TEvent.WaitRes(timeout: Cardinal): TWaitResult;
begin
  case WaitForSingleObject(Handle, Timeout) of
    WAIT_ABANDONED:
      Result := wrAbandoned;
    WAIT_OBJECT_0:
      Result := wrSignaled;
    WAIT_TIMEOUT:
      Result := wrTimeout;
    WAIT_FAILED:
      begin
      Result := wrError;
      FLastError := GetLastError;
      end;
    else
      Result := wrError;
    end;
end;

function TEvent.Wait(timeout: Cardinal): Boolean;
begin
  Result := WaitRes(TimeOut) = wrSignaled;
end;

procedure TEvent.Signal;
begin
  SetEvent(event);
end;

procedure TEvent.UnSignal;
begin
  ResetEvent(event);
end;

function WaitForEvents(list: array of TEvent; TimeOut: Cardinal; WaitAll: Boolean): DWord;
var
  WaitObjectList: array [0..MAXIMUM_WAIT_OBJECTS - 1] of DWord;
  i, j: Integer;
begin
  j := 0;
  for i := 0 to High(List) do
    begin
    if (list[i] <> NIL) then
      begin
      WaitObjectList[j] := list[i].event;
      inc(j);
      end;
    end;
  Result := WaitForMultipleObjects(j, @WaitObjectList, WaitAll, TimeOut);
end;

{ TSemaphore }

constructor TSemaphore.Create(CurrCount: Integer);
begin
  inherited Create;
  FSem := CreateSemaphore(NIL, CurrCount, $FFFF, NIL); // arbitrarily large number
end;

destructor TSemaphore.Destroy;
begin
  closehandle(FSem);
  inherited Destroy;
end;

function TSemaphore.Wait(timeout: Cardinal): TWaitResult;
var
  r: Cardinal;
begin
  r := WaitForSingleObject(FSem, timeout);
  case r of
    WAIT_OBJECT_0:
      Result := wrSignaled;
    WAIT_TIMEOUT:
      Result := wrTimeOut;
    WAIT_ABANDONED:
      Result := wrAbandoned;
    else
      Result := wrError;
    end;
end;

procedure TSemaphore.Release;
begin
  releaseSemaphore(FSem, 1, NIL);
end;

Function CriticalSectionChecksPass(Var sMessage : String) : Boolean;
var
  oCrit : TCriticalSection;
  LCheckTime: Int64;
Begin
  result := true;
  QueryPerformanceCounter(LCheckTime);
  LCheckTime := LCheckTime - (30 * GQPFrequency * 1000);
  EnterCriticalSection(GCritSct);
  Try
    oCrit := GFirst;
    While result And (oCrit <> nil) Do
    Begin
      if oCrit.FEntryCount > 0 Then
      Begin
        if LCheckTime > oCrit.FCurrLockTime Then
        Begin
          sMessage := 'Critical Section has been locked more than 30 seconds ('+oCrit.DebugSummary+')';
          result := False;
        End;
      End;
      oCrit := oCrit.FNext;
    End;
  Finally
    LeaveCriticalSection(GCritSct);
  End;
End;

function DumpLocks : String;
var
  oCrit : TCriticalSection;
  aTime : Int64;
Begin
  QueryPerformanceCounter(aTime);
  Result := IntToStr(TCriticalSection.CurrentCount) + ' Critical Sections (@'+InttoStr(ms(aTime))+')'+crlf;
  oCrit := GFirst;
  While oCrit <> nil Do
  Begin
//    if oCrit.EntryCount > 0 Then
      Result := Result + oCrit.DebugSummary + crlf;
    oCrit := oCrit.FNext;
  End;
End;

initialization
  InitUnit;
finalization
  DeleteCriticalSection(GCritSct);
end.

