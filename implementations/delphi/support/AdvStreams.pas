Unit AdvStreams;

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
  AdvObjects, AdvObjectLists, AdvExceptions;


Type
  TAdvStream = Class(TAdvObject)
    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Function Link : TAdvStream; 

      Function Assignable : Boolean; Override;

      Procedure Read(Var Buffer; iCount : Cardinal); Virtual; // can't mark as overload
      Procedure Write(Const Buffer; iCount : Cardinal); Virtual; // can't mark as overload

      Function Readable : Int64; Virtual;
      Function Writeable : Int64; Virtual;
  End;

  TAdvStreamClass = Class Of TAdvStream;

  TAdvStreamList = Class(TAdvObjectList)
    Private
      Function GetStream(iIndex: Integer): TAdvStream;
      Procedure SetStream(iIndex: Integer; Const Value: TAdvStream);

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property Streams[iIndex : Integer] : TAdvStream Read GetStream Write SetStream; Default;
  End;

  TAdvStreamAdapter = Class(TAdvStream)
    Private
      FStream : TAdvStream;

    Protected
    {$IFOPT C+}
      Function GetStream: TAdvStream; Virtual;
    {$ENDIF}
      Procedure SetStream(oStream : TAdvStream); Virtual;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Read(Var Buffer; iCount : Cardinal); Override;
      Procedure Write(Const Buffer; iCount : Cardinal); Override;

      Function Readable : Int64; Override;
      Function Writeable : Int64; Override;

      Function HasStream : Boolean; Virtual;

      Property Stream : TAdvStream Read {$IFOPT C+}GetStream{$ELSE}FStream{$ENDIF} Write SetStream;
  End;

  TAdvAccessStream = Class(TAdvStream)
    Protected
      Function GetPosition : Int64; Virtual;
      Procedure SetPosition(Const Value : Int64); Virtual;

      Function GetSize : Int64; Virtual;
      Procedure SetSize(Const Value : Int64); Virtual;

    Public
      Function Link : TAdvAccessStream;

      Property Size : Int64 Read GetSize Write SetSize;
      Property Position : Int64 Read GetPosition Write SetPosition;
  End;

  TAdvAccessStreamList = Class(TAdvStreamList)
  End;

  TAdvAccessStreamClass = Class Of TAdvAccessStream;

  TAdvAccessStreamAdapter = Class(TAdvAccessStream)
    Private
      FStream : TAdvAccessStream;

    Protected
      Function GetPosition : Int64; Override;
      Procedure SetPosition(Const Value : Int64); Override;

      Function GetSize : Int64; Override;
      Procedure SetSize(Const Value : Int64); Override;

      Function GetStream: TAdvAccessStream; Virtual;
      Procedure SetStream(oStream : TAdvAccessStream); Virtual;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Read(Var Buffer; iCount : Cardinal); Override;
      Procedure Write(Const Buffer; iCount : Cardinal); Override;

      Function Readable : Int64; Override;
      Function Writeable : Int64; Override;

      Property Stream : TAdvAccessStream Read GetStream Write SetStream;
  End; 

  EAdvStream = Class(EAdvException);

  EAdvExceptionClass = AdvExceptions.EAdvExceptionClass;

  TAdvObjectClass = AdvObjects.TAdvObjectClass;


Implementation


Function TAdvStream.Link : TAdvStream;
Begin
  Result := TAdvStream(Inherited Link);
End;


Function TAdvStream.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvStream;
End;


Procedure TAdvStream.Read(Var Buffer; iCount: Cardinal);
Begin
End;


Procedure TAdvStream.Write(Const Buffer; iCount: Cardinal);
Begin
End;


Function TAdvStream.Readable : Int64;
Begin
  Result := 0;
End;


Function TAdvStream.Writeable : Int64;
Begin
  Result := 0;
End;


Function TAdvStream.Assignable: Boolean;
Begin
  Result := False;
End;


Function TAdvStreamList.ItemClass: TAdvObjectClass;
Begin
  Result := TAdvStream;
End;


Function TAdvStreamList.GetStream(iIndex: Integer): TAdvStream;
Begin
  Result := TAdvStream(ObjectByIndex[iIndex]);
End;


Procedure TAdvStreamList.SetStream(iIndex: Integer; Const Value: TAdvStream);
Begin
  ObjectByIndex[iIndex] := Value;
End;


Constructor TAdvStreamAdapter.Create;
Begin
  Inherited;

  FStream := Nil;
End;


Destructor TAdvStreamAdapter.Destroy;
Begin
  FStream.Free;
  FStream := Nil;

  Inherited;
End;

{$IFOPT C+}
Function TAdvStreamAdapter.GetStream: TAdvStream;
Begin
  Assert(Invariants('GetStream', FStream, TAdvStream, 'FStream'));

  Result := FStream;
End;
{$ENDIF}

Procedure TAdvStreamAdapter.SetStream(oStream : TAdvStream);
Begin
  Assert(Not Assigned(oStream) Or Invariants('SetStream', oStream, TAdvStream, 'oStream'));

  FStream.Free;
  FStream := oStream;
End;


Function TAdvStreamAdapter.HasStream: Boolean;
Begin
  Result := Assigned(FStream);
End;


Procedure TAdvStreamAdapter.Read(Var Buffer; iCount: Cardinal);
Begin 
  Stream.Read(Buffer, iCount);
End;


Procedure TAdvStreamAdapter.Write(Const Buffer; iCount: Cardinal);
Begin
  Stream.Write(Buffer, iCount);
End;


Function TAdvStreamAdapter.Readable : Int64;
Begin
  Result := Stream.Readable;
End;  


Function TAdvStreamAdapter.Writeable : Int64;
Begin 
  Result := Stream.Writeable;
End;  


Function TAdvAccessStream.Link : TAdvAccessStream;
Begin 
  Result := TAdvAccessStream(Inherited Link);
End;


Function TAdvAccessStream.GetPosition : Int64;
Begin 
  Result := 0;
End;  


Function TAdvAccessStream.GetSize : Int64;
Begin 
  Result := 0;
End;  


Procedure TAdvAccessStream.SetPosition(Const Value: Int64);
Begin 
End;  


Procedure TAdvAccessStream.SetSize(Const Value: Int64);
Begin 
End;


Function TAdvAccessStreamAdapter.GetPosition : Int64;
Begin
  Result := Stream.Position;
End;


Function TAdvAccessStreamAdapter.GetSize : Int64;
Begin 
  Result := Stream.Size;
End;  


Procedure TAdvAccessStreamAdapter.SetPosition(Const Value: Int64);
Begin 
  Stream.Position := Value;
End;  


Procedure TAdvAccessStreamAdapter.SetSize(Const Value: Int64);
Begin 
  Stream.Size := Value;
End;  


Procedure TAdvAccessStreamAdapter.Read(Var Buffer; iCount: Cardinal);
Begin 
  Stream.Read(Buffer, iCount);
End;  


Procedure TAdvAccessStreamAdapter.Write(Const Buffer; iCount: Cardinal);
Begin 
  Stream.Write(Buffer, iCount);
End;  


Function TAdvAccessStreamAdapter.Writeable : Int64;
Begin
  Result := Stream.Writeable;
End;


Function TAdvAccessStreamAdapter.Readable : Int64;
Begin
  Result := Stream.Readable;
End;


Function TAdvAccessStreamAdapter.GetStream: TAdvAccessStream;
Begin
  Assert(Invariants('GetStream', FStream, TAdvAccessStream, 'FStream'));

  Result := FStream;
End;


Procedure TAdvAccessStreamAdapter.SetStream(oStream: TAdvAccessStream);
Begin
  Assert(Not Assigned(oStream) Or Invariants('SetStream', oStream, TAdvAccessStream, 'oStream'));

  FStream.Free;
  FStream := oStream;
End;


Constructor TAdvAccessStreamAdapter.Create;
Begin
  Inherited;

  FStream := Nil;
End;


Destructor TAdvAccessStreamAdapter.Destroy;
Begin
  FStream.Free;
  FStream := Nil;

  Inherited;
End;


End. // AdvStreams //
