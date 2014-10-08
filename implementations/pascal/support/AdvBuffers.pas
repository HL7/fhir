Unit AdvBuffers;

{$I bytes.inc}

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
  SysUtils, Classes, BytesSupport,
  FileSupport, StringSupport, MemorySupport, MathSupport,
  AdvPersistents, AdvPersistentLists, AdvStreams, AdvFiles;


Type
  {@class TAdvBuffer
    A list of bytes
  }
  {!.Net HL7Connect.Util.Buffer}

  TAdvBuffer = Class(TAdvPersistent)
    Private
      FData : Pointer;
      FCapacity : Integer;
      FOwned : Boolean;
      {$IFNDEF VER130}
      FEncoding: TEncoding;
      function GetAsUnicode: String;
      procedure SetAsUnicode(const Value: String);
      Function ExtractUnicode(Const iLength : Integer) : String;

      {$ENDIF}

      Procedure SetCapacity(Const Value : Integer);
      Procedure SetData(Const Value : Pointer);
      Procedure SetOwned(Const Value : Boolean);

      Function GetAsText: AnsiString;
      Procedure SetAsText(Const Value: AnsiString);

      Function ExtractAscii(Const iLength : Integer) : AnsiString;
      function GetAsBytes: TBytes;
      procedure SetAsBytes(const Value: TBytes);
    Public
      {!script hide}
      Constructor Create; Override;
{$IFNDEF UT}
      Constructor Create(sText : String); Overload;
{$ENDIF}
      Destructor Destroy; Override;

      Function Link : TAdvBuffer;
      Function Clone : TAdvBuffer;

      Procedure Define(oFiler : TAdvFiler); Override;
      Procedure Load(oFiler : TAdvFiler); Override;
      Procedure Save(oFiler : TAdvFiler); Override;
      Procedure Assign(oObject : TAdvObject); Override;

      {!script show}

      {@member Clear
        Make the buffer empty.

        note that valid buffers must have content
      }
      Procedure Clear;

      {@member LoadFromFileName
        Fill the buffer with contents from the named file
      }
      Procedure LoadFromFileName(Const sFilename : String);

      {@member SaveToFileName
        Save the buffer contents to the named file
      }
      Procedure SaveToFileName(Const sFilename : String);

      {!script hide}
      Function Equal(oBuffer : TAdvBuffer) : Boolean;
      Procedure Copy(oBuffer : TAdvBuffer);
      Procedure CopyRange(oBuffer : TAdvBuffer; Const iIndex, iLength : Integer);
      Function Compare(oBuffer : TAdvBuffer) : Integer;

      Procedure Move(Const iSource, iTarget, iLength : Integer);

      Function Offset(iIndex : Integer) : Pointer;
      Function StartsWith(Const sValue : String) : Boolean;

      Procedure LoadFromFile(oFile : TAdvFile);
      Procedure SaveToFile(oFile : TAdvFile);
      Procedure LoadFromStream(oStream : TAdvStream); overload;
      Procedure SaveToStream(oStream : TAdvStream); overload;
      Procedure LoadFromStream(oStream : TStream); overload;
      Procedure SaveToStream(oStream : TStream); overload;

      Property Data : Pointer Read FData Write SetData;
      Property Capacity : Integer Read FCapacity Write SetCapacity;
      Property Owned : Boolean Read FOwned Write SetOwned;
{$IFNDEF UT}
      Property AsText : String Read GetAsText Write SetAsText;
{$ENDIF}
      {$IFNDEF VER130}
      Property AsUnicode : String Read GetAsUnicode Write SetAsUnicode;
      Property Encoding : TEncoding read FEncoding write FEncoding;
      {$ENDIF}
      Property AsBytes : TBytes read GetAsBytes write SetAsBytes;
      {!script show}
  Published

      {@member Size
        The number of bytes in the buffer
      }
      Property Size : Integer Read FCapacity Write SetCapacity;

      {@member AsAscii
        The contents of the buffer as a string, one byte per character.
        The content may include the ascii characters with value < 32,
        including character 0.
      }
      Property AsAscii : AnsiString Read GetAsText Write SetAsText;

  End;

  TAdvBufferClass = Class Of TAdvBuffer;

  TAdvBufferList = Class(TAdvPersistentList)
    Private
      Function GetBuffer(iIndex: Integer): TAdvBuffer;

    Protected
      Function ItemClass: TAdvObjectClass; Override;

    Public
      Property Buffers[iIndex : Integer] : TAdvBuffer Read GetBuffer; Default;
  End;

  TAdvObject = AdvPersistents.TAdvObject;
  TAdvFiler = AdvPersistents.TAdvFiler;
  TAdvObjectClass = AdvPersistents.TAdvObjectClass;
  TAdvItemsCompare = AdvPersistentLists.TAdvItemsCompare;


Implementation


Constructor TAdvBuffer.Create;
Begin 
  Inherited;
  {$IFNDEF VER130}
  FEncoding := TEncoding.UTF8;
  {$ENDIF}
  FOwned := True;
End;  


Destructor TAdvBuffer.Destroy;
Begin 
  If FOwned Then
    MemoryDestroy(FData, FCapacity);

  Inherited;
End;  


Function TAdvBuffer.Clone : TAdvBuffer;
Begin 
  Result := TAdvBuffer(Inherited Clone);
End;  


Function TAdvBuffer.Link : TAdvBuffer;
Begin 
  Result := TAdvBuffer(Inherited Link);
End;  


Procedure TAdvBuffer.Assign(oObject : TAdvObject);
Begin 
  Inherited;

  Copy(TAdvBuffer(oObject));
End;  


Procedure TAdvBuffer.Define(oFiler : TAdvFiler);
Begin 
  Inherited;
End;


Procedure TAdvBuffer.Load(oFiler: TAdvFiler);
Var
  iCapacity : Integer;
Begin

  Define(oFiler);

  oFiler['Size'].DefineInteger(iCapacity);

  If Not FOwned Then
  Begin
    FData := Nil;
    FOwned := True;
  End;

  SetCapacity(iCapacity);

  oFiler['Data'].DefineBinary(FData^, iCapacity);
End;


Procedure TAdvBuffer.Save(oFiler: TAdvFiler);
Begin
  Define(oFiler);

  oFiler['Size'].DefineInteger(FCapacity);
  oFiler['Data'].DefineBinary(FData^, FCapacity);
End;


Procedure TAdvBuffer.LoadFromStream(oStream: TAdvStream);
Begin
  Assert(Invariants('LoadFromStream', oStream, TAdvStream, 'oStream'));

  oStream.Read(Data^, Capacity);
End;


Procedure TAdvBuffer.LoadFromStream(oStream: TStream);
Begin
//  Assert(Invariants('LoadFromStream', oStream, TStream, 'oStream'));

  Capacity := oStream.Size - oStream.Position;
  oStream.Read(Data^, Capacity);
End;


Procedure TAdvBuffer.SaveToStream(oStream: TAdvStream);
Begin
  Assert(Invariants('SaveToStream', oStream, TAdvStream, 'oStream'));

  oStream.Write(Data^, Capacity);
End;


Procedure TAdvBuffer.SaveToStream(oStream: TStream);
var
  i : integer;
Begin
 // Assert(Invariants('SaveToStream', oStream, TStream, 'oStream'));

   i := oStream.Position;
   oStream.Write(Data^, Capacity);
   assert(oStream.Position = i + Capacity);
End;


Procedure TAdvBuffer.LoadFromFile(oFile: TAdvFile);
Begin
  Assert(Invariants('LoadFromFile', oFile, TAdvFile, 'oFile'));

  Capacity := oFile.Size;

  LoadFromStream(oFile);
End;


Procedure TAdvBuffer.SaveToFile(oFile: TAdvFile);
Begin
  Assert(Invariants('SaveToFile', oFile, TAdvFile, 'oFile'));

  SaveToStream(oFile);
End;


Procedure TAdvBuffer.LoadFromFileName(Const sFilename: String);
Var
  oFile : TAdvFile;
Begin
  oFile := TAdvFile.Create;
  Try
    oFile.Name := sFilename;
    oFile.OpenRead;
    Try
      LoadFromFile(oFile);
    Finally
      oFile.Close;
    End;
  Finally
    oFile.Free;
  End;
End;


Procedure TAdvBuffer.SaveToFileName(Const sFilename: String);
Var
  oFile : TAdvFile;
Begin
  oFile := TAdvFile.Create;
  Try
    oFile.Name := sFilename;
    oFile.Attributes := [FileAttributeNormal];

    oFile.OpenCreate;
    Try
      SaveToFile(oFile);
    Finally
      oFile.Close;
    End;  
  Finally
    oFile.Free;
  End;  
End;  


Procedure TAdvBuffer.Clear;
Begin 

  Capacity := 0;
End;  


Function TAdvBuffer.Equal(oBuffer: TAdvBuffer): Boolean;
Begin
  Assert(Invariants('Equal', oBuffer, TAdvBuffer, 'oBuffer'));

  Result := Compare(oBuffer) = 0;
End;


Function TAdvBuffer.Compare(oBuffer: TAdvBuffer): Integer;
Begin
  Assert(Invariants('Compare', oBuffer, TAdvBuffer, 'oBuffer'));

  Result := IntegerCompare(Capacity, oBuffer.Capacity);

  If Result = 0 Then
    Result := MemoryCompare(Data, oBuffer.Data, Capacity);
End;  


Procedure TAdvBuffer.SetCapacity(Const Value: Integer);
Begin 
  If (Value <> Capacity) Then
  Begin 
    Assert(Condition(Value >= 0, 'SetCapacity', StringFormat('Unable to change the Capacity to %d', [Value])));

    If FOwned Then
      MemoryResize(FData, FCapacity, Value);

    FCapacity := Value;
  End;  
End;  


Procedure TAdvBuffer.SetData(Const Value: Pointer);
Begin 

  If FData <> Value Then
  Begin 
    SetCapacity(0);

    FData := Value;
    FOwned := False;
  End;  
End;  


Procedure TAdvBuffer.SetOwned(Const Value: Boolean);
Begin 

  FOwned := Value;
End;  


Function TAdvBuffer.GetAsText : AnsiString;
Begin
  Result := ExtractAscii(Capacity);
End;


{$IFNDEF VER130}

function TAdvBuffer.GetAsUnicode: String;
var
  chars: SysUtils.TCharArray;
begin
  chars := FEncoding.GetChars(AsBytes);
  SetString(Result, PChar(chars), Length(chars));
end;

procedure TAdvBuffer.SetAsUnicode(const Value: String);
begin
  AsBytes := FEncoding.GetBytes(Value);
end;
{$ENDIF}

Procedure TAdvBuffer.SetAsText(Const Value: AnsiString);
Begin

  Capacity := Length(Value);
  MemoryMove(Pointer(Value), Data, Capacity);
End;


Procedure TAdvBuffer.Copy(oBuffer: TAdvBuffer);
Begin
  CopyRange(oBuffer, 0, oBuffer.Capacity);
End;


Procedure TAdvBuffer.CopyRange(oBuffer: TAdvBuffer; Const iIndex, iLength : Integer);
Begin
  Assert(Invariants('CopyRange', oBuffer, TAdvBuffer, 'oBuffer'));
  Assert(Condition((iIndex >= 0) And (iIndex + iLength <= oBuffer.Capacity), 'CopyRange', 'Attempted to copy invalid part of the buffer.'));

  SetCapacity(iLength);

  MemoryMove(oBuffer.Offset(iIndex), Data, iLength);
End;  


Procedure TAdvBuffer.Move(Const iSource, iTarget, iLength : Integer);
Begin 
  Assert(Condition((iSource >= 0) And (iSource + iLength <= Capacity), 'Copy', 'Attempted to move from an invalid part of the buffer.'));
  Assert(Condition((iTarget >= 0) And (iTarget + iLength <= Capacity), 'Copy', 'Attempted to move to an invalid part of the buffer.'));

  MemoryMove(Offset(iSource), Offset(iTarget), iLength);
End;  


Function TAdvBuffer.Offset(iIndex: Integer): Pointer;
Begin 
  Assert(Condition((iIndex >= 0) And (iIndex <= Capacity), 'Offset', 'Attempted to access invalid offset in the buffer.'));

  Result := Pointer(NativeUInt(Data) + NativeUInt(iIndex));
End;  


Function TAdvBufferList.GetBuffer(iIndex: Integer): TAdvBuffer;
Begin 
  Result := TAdvBuffer(ObjectByIndex[iIndex]);
End;  


Function TAdvBufferList.ItemClass : TAdvObjectClass;
Begin 
  Result := TAdvBuffer;
End;


Function TAdvBuffer.ExtractAscii(Const iLength: Integer): AnsiString;
Begin
  Result := MemoryToString(Data, iLength);
End;

{$IFNDEF VER130}
Function TAdvBuffer.ExtractUnicode(Const iLength: Integer): String;
Begin
  result := System.copy(GetAsUnicode, 1, iLength);
End;
{$ENDIF}


Function TAdvBuffer.StartsWith(Const sValue: String): Boolean;
Begin
  {$IFDEF VER130}
  Result := (Length(sValue) <= Capacity) And StringStartsWith(ExtractAscii(Length(sValue)), sValue);
  {$ELSE}
  Result := (Length(sValue) <= Capacity) And StringStartsWith(ExtractUnicode(Length(sValue)), sValue);
  {$ENDIF}
End;


{$IFNDEF UT}
constructor TAdvBuffer.Create(sText: String);
begin
  Create;
  AsUnicode := stext;
end;
{$ENDIF}

function TAdvBuffer.GetAsBytes: TBytes;
begin
  SetLength(result, Capacity);
  if Capacity > 0 then
    System.move(FData^, result[0], capacity);
end;

procedure TAdvBuffer.SetAsBytes(const Value: TBytes);
begin
  SetCapacity(length(Value));
  if capacity > 0 then
    System.move(Value[0], FData^, Capacity);
end;

End. // AdvBuffers //
