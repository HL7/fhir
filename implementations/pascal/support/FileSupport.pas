Unit FileSupport;

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
  Windows, SysUtils,
  StringSupport, MathSupport, MemorySupport, DateSupport;

Type
  TFileAttribute =
    (FileAttributeArchive, FileAttributeHidden, FileAttributeNormal, FileAttributeOffline, FileAttributeCompressed, FileAttributeReadOnly,
     FileAttributeSystem, FileAttributeTemporary, FileAttributeFolder, FileAttributeWriteThrough);

  TFileAttributes = Set Of TFileAttribute;

  TFileHandle = Record
    Value : Cardinal;
  End;
Function FileGetAttributes(Const sFilename : String) : TFileAttributes; Overload;
Function FileSetAttributes(Const sFilename : String; iAttributes : TFileAttributes) : Boolean; Overload;
Function FileExists(Const sFilename : String) : Boolean; Overload;
Function FileDelete(Const sFilename : String) : Boolean; Overload;
Function FileHandleInvalid : TFileHandle; Overload;
Function FileHandleIsValid(Const aFileHandle : TFileHandle) : Boolean; Overload;
Function FileHandleOpen(Const aValue : Cardinal) : TFileHandle; Overload;
Function FileAttributesToCardinal(Const aAttributes : TFileAttributes) : Cardinal; Overload;
Function CardinalToFileAttributes(Const iValue : Cardinal) : TFileAttributes; Overload;
Procedure FileHandleClose(Var aFileHandle : TFileHandle); Overload;
Function FileGetReadOnlyAttribute(Const sFilename : String) : Boolean; Overload;
Procedure FileSetReadOnlyAttribute(Const sFilename : String; Const bReadOnly : Boolean); Overload;
Function PathFolder(Const sFilename : String) : String; Overload;
Function FileCopyAttempt(Const sSource, sDestination : String) : Boolean; Overload;
Function FileCopyForce(Const sSource, sDestination : String) : Boolean; Overload;

Implementation

Const
  FILEATTRIBUTE_CARDINALS : Array[TFileAttribute] Of Cardinal =
    (FILE_ATTRIBUTE_ARCHIVE, FILE_ATTRIBUTE_HIDDEN, FILE_ATTRIBUTE_NORMAL, FILE_ATTRIBUTE_OFFLINE, FILE_ATTRIBUTE_COMPRESSED,
     FILE_ATTRIBUTE_READONLY, FILE_ATTRIBUTE_SYSTEM, FILE_ATTRIBUTE_TEMPORARY, SysUtils.faDirectory, FILE_FLAG_WRITE_THROUGH);


Function FileAttributesToCardinal(Const aAttributes : TFileAttributes) : Cardinal;
Var
  aAttributeIndex : TFileAttribute;
Begin
  Result := 0;

  For aAttributeIndex := Low(aAttributeIndex) To High(aAttributeIndex) Do
  Begin
    If aAttributeIndex In aAttributes Then
      Result := Result Or FILEATTRIBUTE_CARDINALS[aAttributeIndex];
  End;
End;


Function CardinalToFileAttributes(Const iValue : Cardinal) : TFileAttributes;
Var
  aAttributeIndex : TFileAttribute;
Begin
  Result := [];

  For aAttributeIndex := Low(aAttributeIndex) To High(aAttributeIndex) Do
  Begin
    If (FILEATTRIBUTE_CARDINALS[aAttributeIndex] And iValue) > 0 Then
      Result := Result + [aAttributeIndex];
  End;
End;

Function FileGetAttributes(Const sFilename : String) : TFileAttributes;
Var
  iValue : Cardinal;
Begin
  iValue := GetFileAttributes(PChar(sFilename));

  If iValue = FileHandleInvalid.Value Then
    Result := []
  Else
    Result := CardinalToFileAttributes(iValue);
End;


Function FileSetAttributes(Const sFilename : String; iAttributes : TFileAttributes) : Boolean;
Begin
  Result := SetFileAttributes(PChar(sFilename), FileAttributesToCardinal(iAttributes));
End;

Function FileHandleInvalid : TFileHandle;
Begin
  Result.Value := $FFFFFFFF;
End;

Function FileHandleIsValid(Const aFileHandle : TFileHandle) : Boolean;
Begin
  Result := aFileHandle.Value <> FileHandleInvalid.Value;
End;

Function FileHandleOpen(Const aValue : Cardinal) : TFileHandle;
Begin
  Result.Value := aValue;
End;



Procedure FileHandleClose(Var aFileHandle : TFileHandle);
Begin
  Windows.CloseHandle(aFileHandle.Value);
  aFileHandle := FileHandleInvalid;
End;

Function PathFolder(Const sFilename : String) : String;
Var
  iIndex : Integer;
Begin
  iIndex := LastDelimiter('\:', sFilename);

  If (iIndex > 1) And (sFilename[iIndex] = '\:') And
     (Not CharInSet(sFilename[iIndex - 1], ['\', ':']) Or
     (ByteType(sFilename, iIndex - 1) = mbTrailByte)) Then
    Dec(iIndex);

  Result := StringIncludeAfter(Copy(sFilename, 1, iIndex), '\');
End;


Function FileExists(Const sFilename : String) : Boolean;
Var
  hHandle : NativeUInt;
  aFindData : TWin32FindData;
Begin
  hHandle := Windows.FindFirstFile(PChar(sFileName), aFindData);

  Result := (hHandle <> INVALID_HANDLE_VALUE) And (aFindData.dwFileAttributes And FILE_ATTRIBUTE_DIRECTORY = 0);

  Windows.FindClose(hHandle);
End;

Function FileDelete(Const sFilename : String) : Boolean;
Begin
  Result := Windows.DeleteFile(PChar(sFilename));
End;

Function FileGetReadOnlyAttribute(Const sFilename : String) : Boolean;
Begin
  Result := FileAttributeReadOnly In FileGetAttributes(sFilename);
End;


Procedure FileSetReadOnlyAttribute(Const sFilename : String; Const bReadOnly : Boolean);
Var
  aAttributes : TFileAttributes;
Begin
  If bReadOnly Then
    aAttributes := FileGetAttributes(sFilename) + [FileAttributeReadOnly]
  Else
    aAttributes := FileGetAttributes(sFilename) - [FileAttributeReadOnly];

  FileSetAttributes(sFilename, aAttributes);
End;

Function FileCopyAttempt(Const sSource, sDestination : String) : Boolean;
Begin
  Result := Windows.CopyFile(PChar(sSource), PChar(sDestination), True);
End;


Function FileCopyForce(Const sSource, sDestination : String) : Boolean;
Begin
  Result := Windows.CopyFile(PChar(sSource), PChar(sDestination), False);
End;




End. // FileSupport //
