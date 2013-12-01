Unit AdvSignals;

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
  ErrorSupport, MemorySupport, MathSupport, StringSupport,
  AdvObjects, AdvObjectLists;


Type
  TAdvSignalHandle = THandle;

  TAdvSignal = Class(TAdvObject)
    Private
      FName : String;
      FHandle : TAdvSignalHandle;
      FAutoHide : Boolean;

    Protected
      Procedure Open(bShow: Boolean);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvSignal;

      Procedure OpenShow;
      Procedure OpenHide;
      Procedure Close;

      Procedure Show;
      Procedure Hide;
      Procedure Flash;
      Function Wait : Boolean;
      Function WaitTimeout(Const iTimeout : Cardinal) : Boolean;

      Function Active : Boolean;

      Property Handle : TAdvSignalHandle Read FHandle Write FHandle;
      Property Name : String Read FName Write FName;
      Property AutoHide : Boolean Read FAutoHide Write FAutoHide;
  End;

  TAdvSignalList = Class(TAdvObjectList)
    Private
      Function GetSignalByIndex(iIndex: Integer): TAdvSignal;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property SignalByIndex[iIndex : Integer] : TAdvSignal Read GetSignalByIndex; Default;
  End;

  TAdvSignalManager = Class(TAdvObject)
    Private
      FSignalList : TAdvSignalList;
      FSignalHandleArray : TWOHandleArray;
      FSignalHandleCount : Integer;
      FActive : Boolean;

      Function WaitTimeout(Const iTimeout : Cardinal; Const bAll : Boolean) : Boolean;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Prepare;
      Procedure Terminate;

      Procedure AddSignal(oSignal : TAdvSignal);
      Procedure DeleteSignal(oSignal : TAdvSignal);
      Procedure DeleteAllSignals;

      Function WaitTimeoutForAll(Const iTimeout : Cardinal) : Boolean;
      Function WaitTimeoutForAny(Const iTimeout : Cardinal) : Boolean;
  End;

  TAdvObjectClass = AdvObjectLists.TAdvObjectClass;


Implementation


Constructor TAdvSignal.Create;
Begin
  Inherited;

  FHandle := 0;
End;


Destructor TAdvSignal.Destroy;
Begin
  If Active Then
    Close;

  Inherited;
End;


Function TAdvSignal.Link: TAdvSignal;
Begin
  Result := TAdvSignal(Inherited Link);
End;


Procedure TAdvSignal.Open(bShow : Boolean);
Var
  pName : PChar;
Begin
  Assert(Condition(Not Active, 'Open', 'Signal must not already be active.'));

  If FName = '' Then
    pName := Nil
  Else
    pName := PChar(FName);

  FHandle := CreateEvent(Nil, Not FAutoHide, bShow, pName);
End;


Procedure TAdvSignal.OpenHide;
Begin
  Open(False);
End;


Procedure TAdvSignal.OpenShow;
Begin
  Open(True);
End;


Procedure TAdvSignal.Close;
Begin
  Assert(Condition(Active, 'Close', 'Signal must be active.'));

  CloseHandle(FHandle);

  FHandle := 0;
End;


Procedure TAdvSignal.Hide;
Begin
  Assert(Condition(Active, 'Hide', 'Signal must be active.'));

  If Not ResetEvent(FHandle) Then
    Error('Hide', ErrorAsString);
End;


Procedure TAdvSignal.Show;
Begin
  Assert(Condition(Active, 'Show', 'Signal must be active.'));

  If Not SetEvent(FHandle) Then
    Error('Show', ErrorAsString);
End;


Procedure TAdvSignal.Flash;
Begin
  Assert(Condition(Active, 'Flash', 'Signal must be active.'));

  If Not PulseEvent(FHandle) Then
    Error('Flash', ErrorAsString);
End;


Function TAdvSignal.Wait : Boolean;
Begin
  Result := WaitTimeout(INFINITE);
End;


Function TAdvSignal.WaitTimeout(Const iTimeout: Cardinal) : Boolean;
Var
  iWaitResult : Cardinal;
Begin
  Assert(Condition(Active, 'WaitTimeout', 'Signal must be active.'));

  iWaitResult := WaitForSingleObject(FHandle, iTimeout);

  if (iWaitResult = WAIT_FAILED) Then
    Error('WaitTimeout', ErrorAsString);

  Result := iWaitResult = WAIT_OBJECT_0;
End;


Function TAdvSignalList.GetSignalByIndex(iIndex: Integer): TAdvSignal;
Begin
  Result := TAdvSignal(ObjectByIndex[iIndex]);
End;


Function TAdvSignalList.ItemClass: TAdvObjectClass;
Begin
  Result := TAdvSignal;
End;


Function TAdvSignal.Active: Boolean;
Begin
  Result := FHandle <> 0;
End;


Constructor TAdvSignalManager.Create;
Begin
  Inherited;

  FSignalList := TAdvSignalList.Create;
  FSignalList.SortedByReference;
  FSignalList.PreventDuplicates;
End;


Destructor TAdvSignalManager.Destroy;
Begin
  If FActive Then
    Terminate;

  FSignalList.Free;

  Inherited;
End;


Procedure TAdvSignalManager.AddSignal(oSignal: TAdvSignal);
Begin
  Assert(Condition(Not FActive, 'AddSignal', 'Cannot add a signal to a prepared signal manager.'));

  If (FSignalList.Count >= MAXIMUM_WAIT_OBJECTS) Then
  Begin
    oSignal.Free;

    Error('AddSignal', StringFormat('The signal manager only supports up to %d signals', [MAXIMUM_WAIT_OBJECTS]));
  End;

  FSignalList.Add(oSignal);
End;


Procedure TAdvSignalManager.DeleteSignal(oSignal: TAdvSignal);
Begin
  Assert(Condition(Not FActive, 'AddSignal', 'Cannot delete a signal to a prepared signal manager.'));

  FSignalList.DeleteByReference(oSignal);
End;


Procedure TAdvSignalManager.DeleteAllSignals;
Begin
  Assert(Condition(Not FActive, 'AddSignal', 'Cannot delete all signal with a prepared signal manager.'));

  FSignalList.Clear;
End;


Procedure TAdvSignalManager.Prepare;
Var
  iSignalIndex : Integer;
  oSignal : TAdvSignal;
Begin
  Assert(Condition(Not FActive, 'Prepare', 'Cannot double prepare a signal manager.'));

  If (FSignalList.Count > MAXIMUM_WAIT_OBJECTS) Then
    Error('AddSignal', StringFormat('The signal manager only supports up to %d signals', [MAXIMUM_WAIT_OBJECTS]));

  FActive := True;

  FSignalHandleCount := FSignalList.Count;

  For iSignalIndex := 0 To FSignalHandleCount - 1 Do
  Begin
    oSignal := FSignalList[iSignalIndex];

    If Not oSignal.Active Then
      Error('Prepare', 'All signals must be active.');

    FSignalHandleArray[iSignalIndex] := oSignal.Handle;
  End;
End;


Procedure TAdvSignalManager.Terminate;
Begin
  Assert(Condition(FActive, 'Terminate', 'Cannot double terminate a signal manager.'));

  FSignalHandleCount := 0;
  FActive := False;
End;


Function TAdvSignalManager.WaitTimeout(Const iTimeout: Cardinal; Const bAll: Boolean): Boolean;
Var
  iWaitResult : Cardinal;
Begin
  Assert(Condition(FActive, 'WaitTimeout', 'Cannot wait on a signal manager that has not been prepared.'));

  iWaitResult := WaitForMultipleObjects(FSignalHandleCount, @FSignalHandleArray, bAll, iTimeout);

  if (iWaitResult = WAIT_FAILED) Then
    Error('WaitTimeout', ErrorAsString);

  Result := (iWaitResult <> WAIT_TIMEOUT) And IntegerBetween(WAIT_OBJECT_0, iWaitResult, WAIT_OBJECT_0 + FSignalHandleCount - 1);
End;


Function TAdvSignalManager.WaitTimeoutForAll(Const iTimeout: Cardinal): Boolean;
Begin
  Result := WaitTimeout(iTimeout, True);
End;


Function TAdvSignalManager.WaitTimeoutForAny(Const iTimeout: Cardinal): Boolean;
Begin
  Result := WaitTimeout(iTimeout, False);
End;


End.
