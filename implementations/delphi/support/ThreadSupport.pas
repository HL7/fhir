Unit ThreadSupport;


{! 7 !}


Interface


Uses
  Windows;


Type
  TThreadID = Cardinal;
  TThreadHandle = Cardinal;


Procedure ThreadSleep(iTime : Cardinal); Overload;
Function ThreadID : TThreadID; Overload;
Function ThreadHandle : TThreadHandle; Overload;
Procedure ThreadYield; Overload;
Procedure ThreadBreakpoint; Overload;

threadvar
  DebugThreadName : String;

Implementation

Procedure ThreadSleep(iTime : Cardinal);
Begin
  Windows.Sleep(iTime);
End;

Function ThreadID : TThreadID;
Begin
  Result := Windows.GetCurrentThreadID;
End;


Function ThreadHandle : TThreadHandle;
Begin
  Result := Windows.GetCurrentThread;
End;



Procedure ThreadYield;
Begin
  ThreadSleep(0);
End;


Procedure ThreadBreakpoint;
Begin
  {$IFDEF FPC}
  // todo: how to do this?
  {$ELSE}
  Try
    ASM
      int $03
    End;
  Except
    // on some poorly configured Windows systems int $03 can cause unhandled
    // exceptions with improperly installed Dr Watsons etc....
  End;
  {$ENDIF}
End;

End.
