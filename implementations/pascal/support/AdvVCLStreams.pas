Unit AdvVCLStreams;

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
  AdvObjects, Windows,
  Classes, ActiveX,
  AdvStreams;


Type
  TAdvVCLStream = Class(TAdvStream)
    Private
      FStream : TStream;

      Function GetStream: TStream;
      Procedure SetStream(Const Value: TStream);

    Public
      Procedure Read(Var aBuffer; iCount : Cardinal); Override;
      Procedure Write(Const aBuffer; iCount : Cardinal); Override;

      Function Readable : Int64; Override;
      Function Writeable : Int64; Override;

      Property Stream : TStream Read GetStream Write SetStream;
  End;

  TVCLStream = Class(TStream)
    Private
      FStream : TAdvStream;

      Function GetStream: TAdvStream;
      Procedure SetStream(Const Value: TAdvStream);

    Protected
      Procedure SetSize(NewSize: LongInt); Override;

    Public
      Constructor Create; Overload; Virtual;
      Destructor Destroy; Override;

      Function Read(Var aBuffer; iCount: LongInt): LongInt; Override;
      Function Write(Const aBuffer; iCount: LongInt): LongInt; Override;
      Function Seek(iOffset: LongInt; iOrigin: Word): LongInt; Override;

      Property Stream : TAdvStream Read GetStream Write SetStream;
  End;

  TAdvStreamAdapter = Class(TStreamAdapter)
    Public
      {$IFDEF VER290}
      Function Stat(Out statstg: TStatStg; grfStatFlag: DWord): HResult; Override; Stdcall;
      {$ELSE}
      function Stat(out statstg: TStatStg; grfStatFlag: Longint): HResult; stdcall;
      {$ENDIF}
  End;

  TAdvIStreamAdapter = Class(TAdvObject, IStream)
    Private
      FStream : TAdvAccessStream;

      Function GetStream: TAdvAccessStream;
      Procedure SetStream(Const Value: TAdvAccessStream);
      
    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      {$IFDEF VER290}
      function Seek(dlibMove: Largeint; dwOrigin: DWORD; out libNewPosition: LargeUInt): HResult; stdcall;
      function SetSize(libNewSize: LargeUInt): HResult; stdcall;
      function CopyTo(stm: IStream; cb: LargeUInt; out cbRead: LargeUInt; out cbWritten: LargeUInt): HResult; stdcall;
      function Commit(grfCommitFlags: DWORD): HResult; stdcall;
      function Revert: HResult; stdcall;
      function LockRegion(libOffset: LargeUInt; cb: LargeUInt; dwLockType: DWORD): HResult; stdcall;
      function UnlockRegion(libOffset: LargeUInt; cb: LargeUInt; dwLockType: DWORD): HResult; stdcall;
      function Stat(out statstg: TStatStg; grfStatFlag: DWORD): HResult; stdcall;
      function Clone(out stm: IStream): HResult; stdcall;
      function Read(pv: Pointer; cb: FixedUInt; pcbRead: PFixedUInt): HResult; stdcall;
      function Write(pv: Pointer; cb: FixedUInt; pcbWritten: PFixedUInt): HResult; stdcall;
      {$ELSE}
      function Read(pv: Pointer; cb: Longint; pcbRead: PLongint): HResult; stdcall;
      function Write(pv: Pointer; cb: Longint; pcbWritten: PLongint): HResult; stdcall;
      function Seek(dlibMove: Largeint; dwOrigin: Longint; out libNewPosition: Largeint): HResult; stdcall;
      function SetSize(libNewSize: Largeint): HResult; stdcall;
      function CopyTo(stm: IStream; cb: Largeint; out cbRead: Largeint; out cbWritten: Largeint): HResult; stdcall;
      function Commit(grfCommitFlags: Longint): HResult; stdcall;
      function Revert: HResult; stdcall;
      function LockRegion(libOffset: Largeint; cb: Largeint; dwLockType: Longint): HResult; stdcall;
      function UnlockRegion(libOffset: Largeint; cb: Largeint; dwLockType: Longint): HResult; stdcall;
      function Stat(out statstg: TStatStg; grfStatFlag: Longint): HResult; stdcall;
      function Clone(out stm: IStream): HResult; stdcall;

      {$ENDIF}

      Property Stream: TAdvAccessStream Read GetStream Write SetStream;
  End;

  TStream = Classes.TStream;

  
Implementation


Procedure TAdvVCLStream.Read(Var aBuffer; iCount: Cardinal);
Begin
  Stream.Read(aBuffer, iCount);
End;


Procedure TAdvVCLStream.Write(Const aBuffer; iCount: Cardinal);
Begin
  Stream.Write(aBuffer, iCount);
End;


Function TAdvVCLStream.Readable : Int64;
Begin
  Result := Stream.Size - Stream.Position;
End;


Function TAdvVCLStream.Writeable : Int64;
Begin
  Result := Stream.Size - Stream.Position;
End;


Function TAdvVCLStream.GetStream: TStream;
Begin
  Assert(Condition(Assigned(FStream), 'GetStream', 'No VCL Stream available.'));

  Result := FStream;
End;


Procedure TAdvVCLStream.SetStream(Const Value: TStream);
Begin
  FStream := Value;
End;


Constructor TVCLStream.Create;
Begin
  Inherited;

  FStream := Nil;
End;


Destructor TVCLStream.Destroy;
Begin
  FStream.Free;

  Inherited;
End;


Function TVCLStream.Read(Var aBuffer; iCount: Integer): LongInt;
Var
  iReadable : Integer;
Begin
  iReadable := Stream.Readable;
  If iReadable > iCount Then
    iReadable := iCount;

  Stream.Read(aBuffer, iReadable);
  Result := iReadable;
End;


Function TVCLStream.Seek(iOffset: Integer; iOrigin: Word): LongInt;
Var
  oAccess : TAdvAccessStream;
Begin
  If Not (Stream Is TAdvAccessStream) Then
    Raise EAdvStream.Create(Self, 'Seek', 'Unable to seek in a non-access stream'); // Error is not available.

  oAccess := TAdvAccessStream(Stream);

  Case iOrigin Of
    soFromBeginning : Result := iOffset;
    soFromCurrent   : Result := oAccess.Position + iOffset;
    soFromEnd       : Result := oAccess.Size - iOffset;
  Else
    Result := iOffset;
  End;

  oAccess.Position := Result;
End;


Procedure TVCLStream.SetSize(NewSize: Integer);
Var
  oAccess : TAdvAccessStream;
Begin
  If Not (Stream Is TAdvAccessStream) Then
    Raise EAdvStream.Create(Self, 'SetSize', 'Unable to set the size of a non-access stream'); // Error is not available.

  oAccess := TAdvAccessStream(Stream);

  oAccess.Size := NewSize;
End;


Function TVCLStream.GetStream: TAdvStream;
Begin
  Result := FStream;
End;


Procedure TVCLStream.SetStream(Const Value: TAdvStream);
Begin
  FStream.Free;
  FStream := Value;
End;


Function TVCLStream.Write(Const aBuffer; iCount: Integer): LongInt;
Begin
  FStream.Write(aBuffer, iCount);
  Result := iCount;
End;

{$IFDEF VER290}
Function TAdvStreamAdapter.Stat(Out statstg: TStatStg; grfStatFlag: DWord): HResult;
{$ELSE}
function TAdvStreamAdapter.Stat(out statstg: TStatStg; grfStatFlag: Longint): HResult;
{$ENDIF}
Begin
  // TStreamAdapter.stat does not clear the STATSTG structure.
  // http://qc.embarcadero.com/wc/qcmain.aspx?d=45528

  FillChar(statstg, SizeOf(statstg), 0);
  Result := Inherited Stat(statstg, grfStatFlag);
End;


Constructor TAdvIStreamAdapter.Create;
Begin
  Inherited;

  FStream := TAdvAccessStream.Create;
End;

Destructor TAdvIStreamAdapter.Destroy;
Begin
  FStream.Free;

  Inherited;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.Read(pv: Pointer; cb: FixedUInt; pcbRead: PFixedUInt): HResult;
{$ELSE}
function TAdvIStreamAdapter.Read(pv: Pointer; cb: Longint; pcbRead: PLongint): HResult;
{$ENDIF}

Var
  iReadable : LongInt;
Begin
  Try
    If pv = Nil Then
    Begin
      Result := STG_E_INVALIDPOINTER;
      Exit;
    End;

    iReadable := Stream.Readable;
    If iReadable > cb Then
      iReadable := cb;

    FStream.Read(pv^, iReadable);

    If pcbRead <> Nil Then
      pcbRead^ := iReadable;

    Result := S_OK;
  Except
    Result := S_FALSE;
  End;
End;

{$IFDEF VER290}
Function TAdvIStreamAdapter.Write(pv: Pointer; cb: FixedUInt; pcbWritten: PFixedUInt): HResult;
{$ELSE}
function TAdvIStreamAdapter.Write(pv: Pointer; cb: Longint; pcbWritten: PLongint): HResult;
{$ENDIF}
Begin
  Try
    If pv = Nil Then
    Begin
      Result := STG_E_INVALIDPOINTER;
      Exit;
    End;

    FStream.Write(pv^, cb);

    If pcbWritten <> Nil Then
      pcbWritten^ := cb;

    Result := S_OK;
  Except
    Result := STG_E_CANTSAVE;
  End;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.Seek(dlibMove: Largeint; dwOrigin: DWORD; out libNewPosition: LargeUInt): HResult;
{$ELSE}
Function TAdvIStreamAdapter.Seek(dlibMove: Largeint; dwOrigin: Longint; out libNewPosition: Largeint): HResult;
{$ENDIF}
Var
  iNewPos: Integer;
Begin
  Try
    If (dwOrigin < STREAM_SEEK_SET) Or (dwOrigin > STREAM_SEEK_END) Then
    Begin
      Result := STG_E_INVALIDFUNCTION;
      Exit;
    End;

    Case dwOrigin Of
      STREAM_SEEK_SET : iNewPos := dlibMove;
      STREAM_SEEK_CUR : iNewPos := Stream.Position + dlibMove;
      STREAM_SEEK_END : iNewPos := Stream.Size - dlibMove;
    Else
      iNewPos := dlibMove;
    End;

    Stream.Position := iNewPos;

    If @libNewPosition <> Nil Then
      libNewPosition := iNewPos;

    Result := S_OK;
  Except
    Result := STG_E_INVALIDPOINTER;
  End;
End;


{$IFDEF VER290}
{$ELSE}
{$ENDIF}
Function TAdvIStreamAdapter.Revert: HResult;
Begin
  Result := STG_E_REVERTED;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.SetSize(libNewSize: LargeUInt): HResult;
{$ELSE}
function TAdvIStreamAdapter.SetSize(libNewSize: Largeint): HResult;
{$ENDIF}
Begin
  Try
    Stream.Size := LongInt(libNewSize);
    
    If libNewSize <> Stream.Size Then
      Result := E_FAIL
    Else
      Result := S_OK;
  Except
    Result := E_UNEXPECTED;
  End;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.Stat(out statstg: TStatStg; grfStatFlag: DWORD): HResult;
{$ELSE}
function TAdvIStreamAdapter.Stat(out statstg: TStatStg; grfStatFlag: Longint): HResult;
{$ENDIF}
Begin
  Result := S_OK;
  Try
    If (@statstg <> Nil) Then
    Begin
      FillChar(statstg, SizeOf(statstg), 0);

      statstg.dwType := STGTY_STREAM;
      statstg.cbSize := FStream.Size;
      statstg.grfLocksSupported := LOCK_WRITE;
    End;
  Except
    Result := E_UNEXPECTED;
  End;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.UnlockRegion(libOffset: LargeUInt; cb: LargeUInt; dwLockType: DWORD): HResult;
{$ELSE}
function TAdvIStreamAdapter.UnlockRegion(libOffset: Largeint; cb: Largeint; dwLockType: Longint): HResult;
{$ENDIF}
Begin
  Result := STG_E_INVALIDFUNCTION;
End;


{$IFDEF VER290}
{$ELSE}
{$ENDIF}
Function TAdvIStreamAdapter.Clone(Out stm: IStream): HResult;
Begin
  Result := E_NOTIMPL;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.Commit(grfCommitFlags: DWORD): HResult;
{$ELSE}
function TAdvIStreamAdapter.Commit(grfCommitFlags: Longint): HResult;
{$ENDIF}
Begin
  Result := S_OK;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.CopyTo(stm: IStream; cb: LargeUInt; out cbRead: LargeUInt; out cbWritten: LargeUInt): HResult;
{$ELSE}
function TAdvIStreamAdapter.CopyTo(stm: IStream; cb: Largeint; out cbRead: Largeint; out cbWritten: Largeint): HResult;
{$ENDIF}
Const
  MaxBufSize = 1024 * 1024;  // 1mb
Var
  Buffer: Pointer;
  BufSize, N, I: Integer;
  BytesRead, BytesWritten, W: LargeInt;
  iNumRead : Integer;
Begin
  Result := S_OK;
  BytesRead := 0;
  BytesWritten := 0;
  Try
    If cb > MaxBufSize Then
      BufSize := MaxBufSize
    Else
      BufSize := Integer(cb);

    GetMem(Buffer, BufSize);
    Try
      While cb > 0 Do
      Begin
        If cb > MaxInt Then
          I := MaxInt
        Else
          I := cb;

        While I > 0 Do
        Begin
          If I > BufSize Then
            N := BufSize
          Else
            N := I;

          Read(Buffer, N, @iNumRead);
          Inc(BytesRead, iNumRead);
          //Inc(BytesRead, FStream.Read(Buffer^, N));

          W := 0;
          Result := stm.Write(Buffer, N, @W);
          
          Inc(BytesWritten, W);

          If (Result = S_OK) And (Integer(W) <> N) Then
            Result := E_FAIL;

          If Result <> S_OK Then
            Exit;
            
          Dec(I, N);
        End;
        
        Dec(cb, I);
      End;
    Finally
      FreeMem(Buffer);
      If (@cbWritten <> Nil) Then
        cbWritten := BytesWritten;

      If (@cbRead <> Nil) Then
        cbRead := BytesRead;
    End;
  Except
    Result := E_UNEXPECTED;
  End;
End;


{$IFDEF VER290}
Function TAdvIStreamAdapter.LockRegion(libOffset: LargeUInt; cb: LargeUInt; dwLockType: DWORD): HResult;
{$ELSE}
function TAdvIStreamAdapter.LockRegion(libOffset: Largeint; cb: Largeint; dwLockType: Longint): HResult;
{$ENDIF}
Begin
  Result := STG_E_INVALIDFUNCTION;
End;


Procedure TAdvIStreamAdapter.SetStream(Const Value: TAdvAccessStream);
Begin
  FStream.Free;
  FStream := Value;
End;


Function TAdvIStreamAdapter.GetStream: TAdvAccessStream;
Begin
  Result := FStream;
End;



End. // AdvVCLStreams //
