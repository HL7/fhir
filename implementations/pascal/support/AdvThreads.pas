Unit AdvThreads;

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
  ThreadSupport, MathSupport, ErrorSupport,
  AdvExceptions, AdvObjects, AdvObjectLists, AdvSignals;


Type
  TAdvThreadPriority = (atpIdle, atpLowest, atpBelow, atpNormal, atpAbove, atpHighest, atpCritical);

  TAdvThreadHandle = TThreadHandle;
  TAdvThreadID = TThreadID;

  TAdvThreadDelegate = Procedure Of Object;

  TAdvThread = Class(TAdvObject)
    Private
      FHandle : TAdvThreadHandle; // Handle to the Windows thread.
      FID : TAdvThreadID;         // Unique ID of the Windows thread.
      FActive : Boolean;          // Run thread has finished.
      FDelegate : TAdvThreadDelegate;

      Function GetPriority : TAdvThreadPriority;
      Procedure SetPriority(Const Value : TAdvThreadPriority);

//    Procedure SetProcessor(iProcessor : Cardinal);

    Protected
      Procedure Execute; Virtual;
      Procedure Interrupt; Virtual;
      Function Running : Boolean; Virtual;

      Procedure ExecuteYield(Const iTimeout : Cardinal);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvThread; 

      Procedure Invalidate;

      Procedure Open;
      Procedure Close;
      Procedure Stop; Virtual; 

      Procedure Wait;
      Function WaitTimeout(iTimeout : Cardinal) : Boolean;

      Procedure Kill;
      Procedure KillTimeout(iTimeout : Cardinal);

      Function Active : Boolean;

      Property Handle : TAdvThreadHandle Read FHandle Write FHandle;
      Property ID : TAdvThreadID Read FID Write FID;
      Property Priority : TAdvThreadPriority Read GetPriority Write SetPriority;
      Property Delegate : TAdvThreadDelegate Read FDelegate Write FDelegate;
//    Property Processor : Cardinal Write SetProcessor; // see comments in SetProcessor
  End;

  TAdvThreadClass = Class Of TAdvThread;

  TAdvThreadList = Class(TAdvObjectList)
    Private
      Function GetThread(iIndex: Integer): TAdvThread;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function CompareByThreadHandle(pA, pB : Pointer) : Integer;

    Public
      Function Active : Boolean;

      Function IndexByThreadHandle(Const iHandle : TAdvThreadHandle) : Integer;
      Function ExistsByThreadHandle(Const iHandle : TAdvThreadHandle) : Boolean;
      Function GetByThreadHandle(Const iHandle : TAdvThreadHandle) : TAdvThread;

      Procedure SortedByThreadHandle;
      Function IsSortedByThreadHandle : Boolean;

      Property Threads[iIndex : Integer] : TAdvThread Read GetThread; Default;
  End;

  TAdvObjectClass = AdvObjects.TAdvObjectClass;


Implementation


Const
  THREAD_PRIORITY : Array[TAdvThreadPriority] Of Integer =
    (THREAD_PRIORITY_IDLE, THREAD_PRIORITY_LOWEST, THREAD_PRIORITY_BELOW_NORMAL,THREAD_PRIORITY_NORMAL, THREAD_PRIORITY_ABOVE_NORMAL, THREAD_PRIORITY_HIGHEST, THREAD_PRIORITY_TIME_CRITICAL);


Function RunThread(pData : Pointer) : Integer; Stdcall;
Begin
  Result := 0;
  Try
    TAdvThread(pData).Execute;
  Except
    // ignore any further exceptions
    Result := -1;
  End;

  TAdvThread(pData).FActive := False;
End;  


Constructor TAdvThread.Create;
Begin 
  Inherited;

  Invalidate;
End;  


Destructor TAdvThread.Destroy;
Begin 
  Inherited;
End;


Function TAdvThread.Link: TAdvThread;
Begin
  Result := TAdvThread(Inherited Link);
End;


Procedure TAdvThread.Execute;
Begin
  If Assigned(FDelegate) Then
    FDelegate;
End;


Procedure TAdvThread.Interrupt;
Begin
End;


Function TAdvThread.Running: Boolean;
Begin
  Result := True;
End;


Procedure TAdvThread.Invalidate;
Begin
  FHandle := INVALID_HANDLE_VALUE;
End;


Procedure TAdvThread.Open;
Begin
  If FActive Then
    Error('Open', 'Thread is already active.');

  FActive := True;

  System.IsMultiThread := True;

  FHandle := CreateThread(Nil, 0, @RunThread, Pointer(Self), 0, FID);

  If FHandle = 0 Then
  Begin
    Invalidate;
    FActive := False;
    Error('Open', ErrorAsString);
  End;
End;


Procedure TAdvThread.Close;
Begin
  KillTimeout(Infinite);
End;


Procedure TAdvThread.KillTimeout(iTimeout : Cardinal);
Begin
  Stop;

  If FHandle <> INVALID_HANDLE_VALUE Then
  Begin
    If WaitForSingleObject(FHandle, iTimeout) = WAIT_TIMEOUT Then
      Kill
    Else
    Begin
      If Not CloseHandle(FHandle) Then
        Error('Kill-CloseHandle', ErrorAsString);

      Invalidate;
    End;
  End;
End;


Procedure TAdvThread.Kill;
Begin 
  TerminateThread(FHandle, 0);

  CloseHandle(FHandle);

  Invalidate;

  FActive := False;
End;


Procedure TAdvThread.Stop;
Begin
  FActive := False;

  Interrupt;
End;


Procedure TAdvThread.Wait;
Begin
  WaitTimeout(Infinite);
End;


Function TAdvThread.WaitTimeout(iTimeout : Cardinal) : Boolean;
Begin
  Result := (FHandle = INVALID_HANDLE_VALUE) Or (WaitForSingleObject(FHandle, iTimeout) = WAIT_OBJECT_0);
End;


Procedure TAdvThread.ExecuteYield(Const iTimeout: Cardinal);
Begin
  ThreadSupport.ThreadSleep(iTimeout);
End;


Function TAdvThread.Active : Boolean;
Begin
  Result := FActive And Running;
End;


Function TAdvThread.GetPriority : TAdvThreadPriority;
Begin
  Result := TAdvThreadPriority(IntegerArrayIndexOf(THREAD_PRIORITY, GetThreadPriority(FHandle)));
End;


Procedure TAdvThread.SetPriority(Const Value : TAdvThreadPriority);
Begin
  SetThreadPriority(FHandle, THREAD_PRIORITY[Value]);
End;

{
Procedure TAdvThread.SetProcessor(iProcessor : Cardinal);
Begin
  Error('SetProcessor', 'Unable to set the preferrer processor because the operation is not supported.');
  // Static link to SetThreadIdealProcessor causes software to fail on Windows 95.
  If Not SetThreadIdealProcessor(FHandle, iProcessor) Then
    Error('SetProcessor', StringFormat('Unable to set the preferred processor [%s]', [ErrorAsString]));
End;
}

Function TAdvThreadList.ItemClass : TAdvObjectClass;
Begin 
  Result := TAdvThread;
End;  


Function TAdvThreadList.GetThread(iIndex: Integer): TAdvThread;
Begin
  Result := TAdvThread(ObjectByIndex[iIndex]);
End;


Function TAdvThreadList.Active : Boolean;
Var
  iLoop : Integer;
Begin
  Result := False;
  iLoop := 0;
  While (iLoop < Count) And Not Result Do
  Begin
    Result := Threads[iLoop].Active;
    Inc(iLoop);
  End;
End;


Function TAdvThreadList.CompareByThreadHandle(pA, pB : Pointer) : Integer;
Begin
  Result := IntegerCompare(TAdvThread(pA).Handle, TAdvThread(pB).Handle);
End;


Procedure TAdvThreadList.SortedByThreadHandle;
Begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByThreadHandle);
End;


Function TAdvThreadList.IsSortedByThreadHandle : Boolean;
Begin
  Result := IsSortedBy({$IFDEF FPC}@{$ENDIF}CompareByThreadHandle);
End;


Function TAdvThreadList.ExistsByThreadHandle(Const iHandle : TAdvThreadHandle) : Boolean;
Begin
  Result := ExistsByIndex(IndexByThreadHandle(iHandle));
End;


Function TAdvThreadList.GetByThreadHandle(Const iHandle : TAdvThreadHandle) : TAdvThread;
Begin
  Result := TAdvThread(Get(IndexByThreadHandle(iHandle)));
End;


Function TAdvThreadList.IndexByThreadHandle(Const iHandle : TAdvThreadHandle) : Integer;
Var
  oTaskThread : TAdvThread;
Begin
  oTaskThread := TAdvThread(ItemNew);
  Try
    oTaskThread.Handle := iHandle;
    Try
      Result := IndexBy(oTaskThread, {$IFDEF FPC}@{$ENDIF}CompareByThreadHandle);
    Finally
      oTaskThread.Invalidate;
    End;
  Finally
    oTaskThread.Free;
  End;
End;


End. // AdvThreads //
