Unit AdvMemories;

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
  SysUtils,
  MemorySupport, StringSupport,
  AdvObjects, AdvPersistents, AdvStreams, AdvExceptions, AdvBuffers;


Type
  PByte = ^Byte;

  TAdvMemoryStream = Class(TAdvAccessStream)
    Private
      FBuffer : TAdvBuffer;
      FCurrentPointer : PByte;
      FSize : Int64;
      FPosition : Int64;
      FExpand : Boolean;

      Function GetCapacity: Int64;
      Procedure SetCapacity(Const Value: Int64);

      Function GetDataPointer : Pointer;
      Procedure SetDataPointer(Const Value : Pointer);

      Procedure SetBuffer(Const Value: TAdvBuffer);

      Function GetAsText: String;
      Procedure SetAsText(Const Value: String);

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

      Function GetSize : Int64; Override;
      Procedure SetSize(Const Value : Int64); Override;

      Function GetPosition : Int64; Override;
      Procedure SetPosition(Const Value : Int64); Override;

      Function ValidPosition(Const iValue : Int64) : Boolean; 

      Procedure UpdateCurrentPointer;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Clone : TAdvMemoryStream;
      Function Link : TAdvMemoryStream;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler);

      Procedure Read(Var aBuffer; iSize : Cardinal); Override;
      Procedure Write(Const aBuffer; iSize : Cardinal); Override;

      Procedure DeleteRange(Const iFromPosition, iToPosition : Integer);

      Function Readable : Int64; Override;
      Function Writeable : Int64; Override;

      Function Assignable : Boolean; Override;

      Function Equal(oMemory : TAdvMemoryStream) : Boolean;

      Property Buffer : TAdvBuffer Read FBuffer Write SetBuffer;
      Property DataPointer : Pointer Read GetDataPointer Write SetDataPointer;
      Property CurrentPointer : PByte Read FCurrentPointer;
      Property Capacity : Int64 Read GetCapacity Write SetCapacity;
      Property Size; // declared in TAdvAccessStream.
      Property Expand : Boolean Read FExpand Write FExpand;
      Property AsText : String Read GetAsText Write SetAsText;
  End;

  EAdvMemoryStream = Class(EAdvStream);


Implementation


Constructor TAdvMemoryStream.Create;
Begin
  Inherited;

  FBuffer := TAdvBuffer.Create;
  FExpand := True;
End;


Destructor TAdvMemoryStream.Destroy;
Begin
  FBuffer.Free;

  Inherited;
End;


Function TAdvMemoryStream.Clone : TAdvMemoryStream;
Begin
  Result := TAdvMemoryStream(Inherited Clone);
End;  


Function TAdvMemoryStream.Link : TAdvMemoryStream;
Begin 
  Result := TAdvMemoryStream(Inherited Link);
End;  


Procedure TAdvMemoryStream.Assign(oObject: TAdvObject);
Begin 
  Inherited;

  FBuffer.Assign(TAdvMemoryStream(oObject).Buffer);
  FSize := TAdvMemoryStream(oObject).Size;
  FPosition := TAdvMemoryStream(oObject).Position;

  UpdateCurrentPointer;  
End;  


Procedure TAdvMemoryStream.Define(oFiler: TAdvFiler);
Begin 
  // Introduced to circumvent the inability of a TAdvStream descendent to be persistent.

  oFiler['Buffer'].DefineObject(FBuffer);
  oFiler['Size'].DefineInteger(FSize);
  oFiler['Position'].DefineInteger(FPosition);

  UpdateCurrentPointer;
End;  


Procedure TAdvMemoryStream.UpdateCurrentPointer;
Begin 
  FCurrentPointer := Pointer(NativeUInt(FBuffer.Data) + FPosition);
End;  


Procedure TAdvMemoryStream.Read(Var aBuffer; iSize : Cardinal);
Begin 
  If iSize > 0 Then
  Begin 
    Assert(Condition(FPosition + iSize <= FBuffer.Capacity, 'Read', 'Unable to read past the end of the buffer.'));
    Assert(Condition(FPosition + iSize <= FSize, 'Read', 'Unable to read past the end of the stream.'));
    Assert(Condition(Assigned(FCurrentPointer), 'Read', 'Current must be assigned.'));

    Move(FCurrentPointer^, aBuffer, iSize);

    Inc(FPosition, iSize);
    Inc(FCurrentPointer, iSize);
  End;  
End;  


Procedure TAdvMemoryStream.Write(Const aBuffer; iSize : Cardinal);
Begin
  If iSize > 0 Then
  Begin 
    If FExpand And (FPosition + iSize > FBuffer.Capacity) Then
      SetCapacity(FPosition + iSize);

    Assert(Condition(FPosition + iSize <= FBuffer.Capacity, 'Write', 'Unable to write past the end of the buffer.'));
    Assert(Condition(Assigned(FCurrentPointer), 'Read', 'Current must be assigned.'));

    Move(aBuffer, FCurrentPointer^, iSize);

    Inc(FPosition, iSize);
    Inc(FCurrentPointer, iSize);

    If FPosition > FSize Then
      FSize := FPosition;
  End;  
End;  


Function TAdvMemoryStream.Readable : Int64;
Begin 
  Result := FSize - FPosition;
End;  


Function TAdvMemoryStream.Writeable : Int64;
Begin 
  If FExpand Then
    Result := MaxInt
  Else
    Result := FBuffer.Capacity - FPosition;
End;  


Function TAdvMemoryStream.GetSize : Int64;
Begin 
  Result := FSize;
End;  


Procedure TAdvMemoryStream.SetSize(Const Value: Int64);
Begin 
  Assert(Condition(Value >= 0, 'SetSize', 'Attempted to set size to an invalid value.'));

  If FSize <> Value Then
  Begin
    If Value > FBuffer.Capacity Then
      SetCapacity(Value);

    FSize := Value;

    If FPosition > FSize Then
      FPosition := FSize;

    UpdateCurrentPointer;
  End;
End;


Function TAdvMemoryStream.GetPosition : Int64;
Begin 
  Result := FPosition;
End;  


Procedure TAdvMemoryStream.SetPosition(Const Value: Int64);
Begin 
  Assert(Condition((Value >= 0) And (Value <= FBuffer.Capacity), 'SetPosition', 'Attempted to set position outside of memory.'));

  FPosition := Value;
  UpdateCurrentPointer;
End;  


Function TAdvMemoryStream.GetCapacity : Int64;
Begin 
  Result := FBuffer.Capacity;
End;  


Procedure TAdvMemoryStream.SetCapacity(Const Value: Int64);
Begin 
  Assert(Condition((Value >= Size), 'SetCapacity', StringFormat('Unable to change the capacity to less than the size %d.', [Value])));

  FBuffer.Capacity := Value;
  UpdateCurrentPointer;
End;  


Function TAdvMemoryStream.GetDataPointer : Pointer;
Begin 
  Result := FBuffer.Data;
End;  


Procedure TAdvMemoryStream.SetDataPointer(Const Value: Pointer);
Begin 
  FBuffer.Data := Value;
  UpdateCurrentPointer;
End;  


Procedure TAdvMemoryStream.SetBuffer(Const Value: TAdvBuffer);
Begin 
  Assert(Not Assigned(Value) Or Invariants('SetBuffer', Value, TAdvBuffer, 'Value'));

  FBuffer.Free;
  FBuffer := Value;

  If Assigned(FBuffer) Then
  Begin
    FSize := FBuffer.Capacity;
    FPosition := 0;

    UpdateCurrentPointer;
  End
  Else
  Begin
    FSize := 0;
    FPosition := 0;

    FCurrentPointer := Nil;
  End;
End;


Function TAdvMemoryStream.GetAsText : String;
Begin
{$IFDEF VER130}
  Result := MemoryToString(DataPointer, Size);
{$ELSE}
  Result := SysUtils.TEncoding.ASCII.GetString(TBytes(DataPointer), 0, Size);
{$ENDIF}
End;


Procedure TAdvMemoryStream.SetAsText(Const Value: String);
{$IFNDEF VER130}
Var
  aBytes : TBytes;
{$ENDIF}
Begin
  Size := Length(Value);

{$IFDEF VER130}
  MemoryMove(Pointer(Value), DataPointer, Size);
{$ELSE}
  aBytes := SysUtils.TEncoding.ASCII.GetBytes(Value);
  MemoryMove(@aBytes[0], DataPointer, Size);
{$ENDIF}

  If Position > Size Then
    Position := Size;
End;  


Function TAdvMemoryStream.ErrorClass : EAdvExceptionClass;
Begin 
  Result := EAdvMemoryStream;
End;  


Function TAdvMemoryStream.Equal(oMemory: TAdvMemoryStream): Boolean;
Begin 
  Result := (Size = oMemory.Size) And (MemoryCompare(Buffer.Data, oMemory.Buffer.Data, Size) = 0);
End;  


Procedure TAdvMemoryStream.DeleteRange(Const iFromPosition, iToPosition: Integer);
Var
  iDifference : Integer;
Begin 
  Assert(Condition(ValidPosition(iFromPosition), 'DeleteRange', 'Delete from position is invalid.'));
  Assert(Condition(ValidPosition(iToPosition), 'DeleteRange', 'Delete to position is invalid.'));

  iDifference := iToPosition - iFromPosition + 1;

  Assert(Condition(iDifference > 0, 'DeleteRange', 'Delete from position <= to position.'));

  Buffer.Move(iToPosition, iFromPosition, iDifference);

  Size := Size - iDifference;
End;  


Function TAdvMemoryStream.ValidPosition(Const iValue: Int64): Boolean;
Begin
  Result := (iValue >= 0) And (iValue <= Size);
End;


Function TAdvMemoryStream.Assignable: Boolean;
Begin
  Result := True;
End;


End. // AdvMemories //
