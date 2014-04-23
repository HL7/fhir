Unit MemorySupport;

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
  SysUtils, Windows,
  StringSupport;

Type
  TAnsiCharSet = set of AnsiChar;
// Always pass the pointer
Procedure MemoryCreate(Var pBuffer; iSize : Integer);
Procedure MemoryResize(Var pBuffer; iOldSize, iNewSize : Integer);
Procedure MemoryDestroy(Var pBuffer; iSize : Integer);
Function MemoryCompare(pA, pB : Pointer; iLength : Integer) : Integer;
Procedure MemoryZero(Const pBuffer : Pointer; iSize : Integer);
Procedure MemoryFill(Const pBuffer : Pointer; iSize : Integer);
Procedure MemoryMove(Const aSource, aTarget : Pointer; iSize : Integer);
Function MemoryToString(pData : Pointer; iPosition, iLength : Integer) : AnsiString; Overload;
Function MemoryToString(pData : Pointer; iLength : Integer) : AnsiString; Overload;

Implementation

{$IFOPT C+}
Var
  gLiveMemorySize : Int64 = 0;
  gCriticalSection : TRTLCriticalSection;
  gActiveMemoryTracking : Boolean = False;
{$ENDIF}


Procedure MemorySet(Const pBuffer : Pointer; iSize : Integer; Const iValue : Byte);
Begin
  If (iSize > 0) And Assigned(pBuffer) Then
    FillChar(pBuffer^, iSize, iValue);
End;


Procedure MemoryZero(Const pBuffer : Pointer; iSize: Integer);
Begin
  MemorySet(pBuffer, iSize, $00);
End;


Procedure MemoryFill(Const pBuffer : Pointer; iSize : Integer);
Begin
  MemorySet(pBuffer, iSize, $FF);
End;


Procedure MemoryCreate(Var pBuffer; iSize : Integer);
Begin
  // Untyped because otherwise we'd have to pass in a literal Pointer type.

  GetMem(Pointer(pBuffer), iSize);

{$IFOPT C+}
  Assert(gActiveMemoryTracking, 'Memory tracking not available for call to MemoryCreate');
  EnterCriticalSection(gCriticalSection);
  Inc(gLiveMemorySize, iSize);
  LeaveCriticalSection(gCriticalSection);
{$ENDIF}
End;

Procedure MemoryResize(Var pBuffer; iOldSize, iNewSize : Integer);
Begin
  // Untyped because otherwise we'd have to pass in a literal Pointer type.

  ReAllocMem(Pointer(pBuffer), iNewSize);

{$IFOPT C+}
  Assert(gActiveMemoryTracking, 'Memory tracking not available for call to MemoryResize');
  EnterCriticalSection(gCriticalSection);
  Inc(gLiveMemorySize, iNewSize - iOldSize);
  LeaveCriticalSection(gCriticalSection);
{$ENDIF}
End;


Procedure MemoryDestroy(Var pBuffer; iSize : Integer);
Begin
  // Untyped because otherwise we'd have to pass in a literal Pointer type.

  FreeMem(Pointer(pBuffer), iSize);

{$IFOPT C+}
  Assert(gActiveMemoryTracking, 'Memory tracking not available for call to MemoryDestroy');
  EnterCriticalSection(gCriticalSection);
  Dec(gLiveMemorySize, iSize);
  LeaveCriticalSection(gCriticalSection);
{$ENDIF}
End;

Procedure MemoryMove(Const aSource, aTarget : Pointer; iSize : Integer);
Begin
  If (iSize > 0) And Assigned(aSource) And Assigned(aTarget) Then
    System.Move(aSource^, aTarget^, iSize);
End;

Function MemoryCompare(pA : Pointer; pB : Pointer; iLength : Integer) : Integer;
Begin
  Result := Integer(Not CompareMem(pA, pB, iLength));
End;


Function MemoryToString(pData : Pointer; iPosition, iLength : Integer) : AnsiString;
Begin
  SetString(Result, PAnsiChar(Integer(pData) + iPosition), iLength - iPosition);
End;


Function MemoryToString(pData : Pointer; iLength : Integer) : AnsiString;
Begin
  Result := MemoryToString(pData, 0, iLength);
End;


{$IFOPT C+}
Initialization
  InitializeCriticalSection(gCriticalSection);
  gActiveMemoryTracking := True;
Finalization
  EnterCriticalSection(gCriticalSection);
  Try
    Assert(gLiveMemorySize = 0);
  Except
    MessageBox(0, PChar(StringFormat('Memory has not been properly released (%d bytes). Please check your usage of Memory Create/Destroy/Resize routines.', [gLiveMemorySize])), 'MemorySupport', MB_OK);
  End;

  gActiveMemoryTracking := False;

  LeaveCriticalSection(gCriticalSection);

  DeleteCriticalSection(gCriticalSection);
{$ENDIF}
End. // MemorySupport //
