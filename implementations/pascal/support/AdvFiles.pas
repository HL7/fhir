Unit AdvFiles;

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
  FileSupport, StringSupport, MathSupport, ErrorSupport,
  AdvStreams, AdvObjects, AdvExceptions;


Type
  TAdvFileShare = (AdvFileShareNone, AdvFileShareRead, AdvFileShareWrite, AdvFileShareAll);

  TAdvFileMode = (AdvFileModeRead, AdvFileModeWrite, AdvFileModeReadWrite, AdvFileModeCreate, AdvFileModeNew, AdvFileModeTruncate, AdvFileModeAppend);

  TAdvFileHandle = TFileHandle;
  TAdvFileAttributes = TFileAttributes;
  TAdvFileAttribute = TFileAttribute;
  TAdvFileTime = TFileTime;

  TAdvFile = Class(TAdvAccessStream)
    Private
      FHandle : TAdvFileHandle;
      FName : String;
      FDefaultMode : TAdvFileMode;
      FDefaultShare : TAdvFileShare;
      FDefaultAttributes : TAdvFileAttributes;

      Procedure SetName(Const Value : String);

      Function GetCreated : TAdvFileTime;
      Procedure SetCreated(Const aValue : TAdvFileTime);

      Function GetAccessed : TAdvFileTime;
      Procedure SetAccessed(Const aValue : TAdvFileTime);

      Function GetModified : TAdvFileTime;
      Procedure SetModified(Const aValue : TAdvFileTime);

      Function GetAttributes : TAdvFileAttributes;
      Procedure SetAttributes(Const Value : TAdvFileAttributes);

      Function GetAttributeWriteThrough: Boolean;
      Procedure SetAttributeWriteThrough(Const Value: Boolean);

      Function GetAttributeReadOnly: Boolean;
      Procedure SetAttributeReadOnly(Const Value: Boolean);

    Protected
      Function GetPosition : Int64; Override;
      Procedure SetPosition(Const Value : Int64); Override;

      Function GetSize : Int64; Override;
      Procedure SetSize(Const iValue : Int64); Override;

      Procedure AttributeToggle(Const aAttribute : TAdvFileAttribute; Const bValue : Boolean); 
      Procedure AttributeInclude(Const aAttribute : TAdvFileAttribute); 
      Procedure AttributeExclude(Const aAttribute : TAdvFileAttribute); 
      Function HasAttribute(Const aAttribute : TAdvFileAttribute) : Boolean;

      Procedure Error(aException : EAdvExceptionClass; Const sMethod, sMessage : String); Overload; Override;

      Function ErrorClass : EAdvExceptionClass; Override;

      Function TryOpenHandle(Const sName : String; aMode : TAdvFileMode; aShare : TAdvFileShare; aAttributes : TAdvFileAttributes) : Boolean;
      Procedure OpenHandle(Const sName : String; aMode : TAdvFileMode; aShare : TAdvFileShare; aAttributes : TAdvFileAttributes);

    Public
      Constructor Create; Override;
      Procedure BeforeDestruction; Override;

      Function Exists : Boolean;
      Function Active : Boolean;

      Function TryOpen : Boolean;
      Function TryOpenCreate : Boolean;

      Procedure OpenCreate;
      Procedure OpenNew;
      Procedure OpenRead;
      Procedure OpenWrite;
      Procedure OpenReadWrite;
      Procedure OpenTruncate;

      Procedure Append;
      Procedure Open;
      Procedure Close;
      Procedure Delete;
      Procedure Flush;

      Procedure Seek(iIndex : Int64); 

      Procedure Receive(Var aBuffer; iCount : Cardinal; Var iActual : Cardinal); Virtual;
      Procedure Send(Const aBuffer; iCount : Cardinal; Var iActual : Cardinal); Virtual;

      Procedure Read(Var aBuffer; iCount : Cardinal); Override;
      Procedure Write(Const aBuffer; iCount : Cardinal); Override;

      Procedure Lock(Const iStart, iFinish : Int64);
      Procedure Unlock(Const iStart, iFinish : Int64);

      Function Readable : Int64; Override;
      Function Writeable : Int64; Override;

      Function IsReadOnly : Boolean;

      Property Handle : TAdvFileHandle Read FHandle;
      Property Name : String Read FName Write SetName;
      Property Position : Int64 Read GetPosition Write SetPosition;
      Property Size : Int64 Read GetSize Write SetSize;
      Property Modified : TAdvFileTime Read GetModified Write SetModified;
      Property Created : TAdvFileTime Read GetCreated Write SetCreated;
      Property Accessed : TAdvFileTime Read GetAccessed Write SetAccessed;
      Property Attributes : TAdvFileAttributes Read GetAttributes Write SetAttributes;
      Property AttributeWriteThrough : Boolean Read GetAttributeWriteThrough Write SetAttributeWriteThrough;
      Property AttributeReadOnly : Boolean Read GetAttributeReadOnly Write SetAttributeReadOnly;
      Property DefaultMode : TAdvFileMode Read FDefaultMode Write FDefaultMode;
      Property DefaultShare : TAdvFileShare Read FDefaultShare Write FDefaultShare;
      Property DefaultAttributes : TAdvFileAttributes Read FDefaultAttributes Write FDefaultAttributes;
  End;

  EAdvFile = Class(EAdvStream);


Implementation


Type
  TLargeInteger = Record
    Low  : Cardinal;
    High : Cardinal;
  End;

  PLargeInteger = ^TLargeInteger;


Constructor TAdvFile.Create;
Begin
  Inherited;

  FHandle := FileHandleInvalid;
  FDefaultAttributes := [FileAttributeNormal];
  FDefaultMode := AdvFileModeRead;
  FDefaultShare := AdvFileShareNone;
End;


Procedure TAdvFile.BeforeDestruction;
Begin 
  Close;

  Inherited;
End;  


Procedure TAdvFile.Error(aException : EAdvExceptionClass; Const sMethod, sMessage : String);
Begin 
  Inherited Error(aException, sMethod, StringFormat('%s: ''%s''', [sMessage, FName]));
End;  


Function TAdvFile.ErrorClass : EAdvExceptionClass;
Begin 
  Result := EAdvFile;
End;  


Function TAdvFile.Exists : Boolean;
Begin
  Result := FileExists(FName);
End;


Function TAdvFile.Active : Boolean;
Begin
  Result := FileHandleIsValid(FHandle);
End;


Function TAdvFile.TryOpenHandle(Const sName : String; aMode : TAdvFileMode; aShare : TAdvFileShare; aAttributes : TAdvFileAttributes) : Boolean;
Begin
  FName := sName;
  FDefaultMode := aMode;
  FDefaultShare := aShare;
  FDefaultAttributes := aAttributes;

  Result := TryOpen;
End;


Procedure TAdvFile.OpenHandle(Const sName : String; aMode : TAdvFileMode; aShare : TAdvFileShare; aAttributes : TAdvFileAttributes);
Begin
  FName := sName;
  FDefaultMode := aMode;
  FDefaultShare := aShare;
  FDefaultAttributes := aAttributes;

  Open;
End;


Procedure TAdvFile.Open;
Begin
  If Not TryOpen Then
    Error('Open', StringFormat('Unable to open file [%s]', [ErrorAsString]));
End;


{$R-}
Function TAdvFile.TryOpen : Boolean;
Const
  FILE_MODE : Array[TAdvFileMode] Of Cardinal =
    (GENERIC_READ, GENERIC_WRITE, GENERIC_READ Or GENERIC_WRITE, GENERIC_READ Or GENERIC_WRITE, GENERIC_READ Or GENERIC_WRITE, GENERIC_WRITE, GENERIC_READ Or GENERIC_WRITE);

  FILE_SHARE : Array[TAdvFileShare] Of Cardinal =
    (0, FILE_SHARE_READ, FILE_SHARE_WRITE, FILE_SHARE_READ Or FILE_SHARE_WRITE);

  FILE_OPEN : Array[TAdvFileMode] Of Cardinal =
    (OPEN_EXISTING, OPEN_ALWAYS, OPEN_ALWAYS, CREATE_ALWAYS, CREATE_NEW, TRUNCATE_EXISTING, OPEN_ALWAYS);
Begin
  Close;

  FHandle := FileHandleOpen(CreateFile(PChar(FName), FILE_MODE[FDefaultMode], FILE_SHARE[FDefaultShare], Nil, FILE_OPEN[FDefaultMode], FileAttributesToCardinal(FDefaultAttributes), 0));

  Result := FileHandleIsValid(FHandle);

  If Result And (FDefaultMode = AdvFileModeAppend) Then
    Append;
End;


Function TAdvFile.TryOpenCreate : Boolean;
Begin
  Result := TryOpenHandle(Name, AdvFileModeCreate, AdvFileShareAll, [FileAttributeNormal]);
End;


Procedure TAdvFile.OpenCreate;
Begin
  OpenHandle(Name, AdvFileModeCreate, AdvFileShareAll, [FileAttributeNormal]);
End;


Procedure TAdvFile.OpenRead;
Begin
  OpenHandle(Name, AdvFileModeRead, AdvFileShareAll, [FileAttributeNormal]);
End;


Procedure TAdvFile.OpenTruncate;
Begin
  OpenHandle(Name, AdvFileModeTruncate, AdvFileShareAll, [FileAttributeNormal]);
End;


Procedure TAdvFile.OpenWrite;
Begin
  OpenHandle(Name, AdvFileModeWrite, AdvFileShareAll, [FileAttributeNormal]);
End;


Procedure TAdvFile.OpenReadWrite;
Begin
  OpenHandle(Name, AdvFileModeReadWrite, AdvFileShareAll, [FileAttributeNormal]);
End;


Procedure TAdvFile.OpenNew;
Begin
  OpenHandle(Name, AdvFileModeNew, AdvFileShareAll, [FileAttributeNormal]);
End;


Procedure TAdvFile.Close;
Begin
  If Active Then
  Begin
    FileHandleClose(FHandle);
  End;
End;


Procedure TAdvFile.Append;
Begin 
  SetFilePointer(FHandle.Value, 0, Nil, FILE_END);
End;  


Procedure TAdvFile.Delete;
Begin 
  Close;

  FileDelete(FName);
End;  


Procedure TAdvFile.Flush;
Begin 
  FlushFileBuffers(FHandle.Value);
End;  


Procedure TAdvFile.Read(Var aBuffer; iCount : Cardinal);
Var
  iActual : Cardinal;
Begin 
  Receive(aBuffer, iCount, iActual);

  If (iActual < iCount) Then
    Error('Read', 'Unable to read past end of file');
End;  


Procedure TAdvFile.Write(Const aBuffer; iCount : Cardinal);
Var
  iActual : Cardinal;
Begin 
  Send(aBuffer, iCount, iActual);

  If (iActual < iCount) Then
    Error('Read', 'Unable to write the entire buffer');
End;  


Procedure TAdvFile.Receive(Var aBuffer; iCount : Cardinal; Var iActual : Cardinal);
Begin 
  If Not ReadFile(FHandle.Value, aBuffer, iCount, iActual, Nil) Then
    Error('Read', StringFormat('Unable to read the requested number of bytes [%s]', [ErrorAsString]))
End;  


Procedure TAdvFile.Send(Const aBuffer; iCount : Cardinal; Var iActual : Cardinal);
Begin 
  If Not WriteFile(FHandle.Value, aBuffer, iCount, iActual, Nil) Then
    Error('Write', StringFormat('Unable to write the requested number of bytes [%s]', [ErrorAsString]));
End;  


Procedure TAdvFile.Lock(Const iStart, iFinish: Int64);
Var
  pStart  : PLargeInteger;
  pFinish : PLargeInteger;
Begin 
  pStart := PLargeInteger(@iStart);
  pFinish := PLargeInteger(@iFinish);

  If Not LockFile(FHandle.Value, pStart^.Low, pStart^.High, pFinish^.Low, pFinish^.High) Then
    Error('Lock', StringFormat('Unable to lock the specified region [%s]', [ErrorAsString]));
End;  


Procedure TAdvFile.Unlock(Const iStart, iFinish: Int64);
Var
  pStart  : PLargeInteger;
  pFinish : PLargeInteger;
Begin 
  pStart := PLargeInteger(@iStart);
  pFinish :=PLargeInteger(@iFinish);

  If Not UnlockFile(FHandle.Value, pStart^.Low, pStart^.High, pFinish^.Low, pFinish^.High) Then
    Error('Unlock', StringFormat('Unable to unlock the specified region [%s]', [ErrorAsString]));
End;  


Procedure TAdvFile.Seek(iIndex : Int64);
Var
  pValue : PLargeInteger;
Begin 
  pValue := PLargeInteger(@iIndex);

  SetFilePointer(FHandle.Value, pValue^.Low, @pValue^.High, FILE_CURRENT);
End;  


Function TAdvFile.GetCreated : TAdvFileTime;
Begin 
  If Not GetFileTime(FHandle.Value, @Result, Nil, Nil) Then
    Error('GetCreated', ErrorAsString);
End;


Procedure TAdvFile.SetCreated(Const aValue : TAdvFileTime);
Begin
  If Not SetFileTime(FHandle.Value, @aValue, Nil, Nil) Then
    Error('SetCreated', ErrorAsString);
End;


Function TAdvFile.GetModified : TAdvFileTime;
Begin
  If Not GetFileTime(FHandle.Value, Nil, Nil, @Result) Then
    Error('GetModified', ErrorAsString);
End;


Procedure TAdvFile.SetModified(Const aValue : TAdvFileTime);
Begin
  If Not SetFileTime(FHandle.Value, Nil, Nil, @aValue) Then
    Error('SetModified', ErrorAsString);
End;


Function TAdvFile.GetAccessed : TAdvFileTime;
Begin
  If Not GetFileTime(FHandle.Value, Nil, @Result, Nil) Then
    Error('GetAccessed', ErrorAsString);
End;


Procedure TAdvFile.SetAccessed(Const aValue : TAdvFileTime);
Begin
  If Not SetFileTime(FHandle.Value, Nil, @aValue, Nil) Then
    Error('SetAccessed', ErrorAsString);
End;  


Procedure TAdvFile.SetName(Const Value : String);
Begin 
  If Value <> FName Then
  Begin 
    Assert(Condition(Not Active, 'SetName', 'Cannot set the name when the file is open.'));

    FName := Value;
  End;
End;


Function TAdvFile.GetSize : Int64;
Var
  pResult : PLargeInteger;
Begin
  pResult := PLargeInteger(@Result);
  pResult^.Low := GetFileSize(FHandle.Value, @pResult^.High);

  If pResult^.Low = FileHandleInvalid.Value Then
    Error('GetSize', StringFormat('Unable to get the size of the file [%s]', [ErrorAsString]));
End;  


Procedure TAdvFile.SetSize(Const iValue : Int64);
Var
  iLast : Int64;
Begin 
  iLast := Position;
  Position := iValue;

  If Not SetEndOfFile(FHandle.Value) Then
    Error('SetSize', StringFormat('Unable to set the size of the file [%s]', [ErrorAsString]));

  Position := IntegerMin(iValue, iLast);
End;  


Function TAdvFile.GetPosition : Int64;
Var
  pResult : PLargeInteger;
Begin 
  // 9x does not support SetFilePointerEx

  pResult := PLargeInteger(@Result);
  pResult^.High := 0;
  pResult^.Low := SetFilePointer(FHandle.Value, 0, @pResult^.High, FILE_CURRENT);

  If pResult^.Low = FileHandleInvalid.Value Then
    Error('GetPosition', StringFormat('Unable to get the file position [%s]', [ErrorAsString]));
End;  


Procedure TAdvFile.SetPosition(Const Value : Int64);
Var
  pValue : PLargeInteger;
Begin 
  pValue := PLargeInteger(@Value);

  // 9x does not support SetFilePointerEx
  
  If SetFilePointer(FHandle.Value, pValue^.Low, @pValue^.High, FILE_BEGIN) = FileHandleInvalid.Value Then
    Error('SetPosition', StringFormat('Unable to set the file position [%s]', [ErrorAsString]));
End;  


Function TAdvFile.GetAttributes : TAdvFileAttributes;
Begin 
  Result := FileGetAttributes(FName);

  If Result = [] Then
    Result := FDefaultAttributes;
End;  


Procedure TAdvFile.SetAttributes(Const Value : TAdvFileAttributes);
Begin 
  FileSetAttributes(FName, Value);

  FDefaultAttributes := Value;
End;


Function TAdvFile.Readable : Int64;
Begin 
  Result := Size - Position;
End;  


Function TAdvFile.Writeable : Int64;
Begin 
  Result := MaxInt;
End;  


Procedure TAdvFile.AttributeToggle(Const aAttribute: TAdvFileAttribute; Const bValue: Boolean);
Begin
  If bValue Then
    AttributeInclude(aAttribute)
  Else
    AttributeExclude(aAttribute);
End;


Procedure TAdvFile.AttributeExclude(Const aAttribute: TAdvFileAttribute);
Begin
  Attributes := Attributes - [aAttribute];
End;


Procedure TAdvFile.AttributeInclude(Const aAttribute: TAdvFileAttribute);
Begin
  Attributes := Attributes + [aAttribute];
End;


Function TAdvFile.HasAttribute(Const aAttribute: TAdvFileAttribute): Boolean;
Begin
  Result := aAttribute In Attributes;
End;


Function TAdvFile.IsReadOnly : Boolean;
Begin 
  Result := HasAttribute(FileAttributeReadOnly);
End;


Function TAdvFile.GetAttributeWriteThrough: Boolean;
Begin
  Result := HasAttribute(FileAttributeWriteThrough);
End;


Procedure TAdvFile.SetAttributeWriteThrough(Const Value: Boolean);
Begin
  AttributeToggle(FileAttributeWriteThrough, Value);
End;


Function TAdvFile.GetAttributeReadOnly: Boolean;
Begin
  Result := HasAttribute(FileAttributeReadOnly);
End;


Procedure TAdvFile.SetAttributeReadOnly(Const Value: Boolean);
Begin
  AttributeToggle(FileAttributeReadOnly, Value);
End;


End. // AdvFiles //
