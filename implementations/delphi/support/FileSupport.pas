Unit FileSupport;


{! 57 !}


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
  hHandle : Cardinal;
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

End. // FileSupport //
