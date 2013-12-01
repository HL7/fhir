Unit AfsResourceVolumes;

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
  AdvObjects,
  AfsVolumes, AfsStreamManagers;


Type
  TAfsResourceVolume = Class(TAfsVolume)
  Public
    Procedure Open; Overload; Override;
    Procedure Format; Override;
    Procedure Close; Overload; Override;
    Procedure Delete; Overload; Override;

    Function Exists : Boolean; Overload; Override;
    Function Active : Boolean; Overload; Override;

    Function Open(Const sName : String; amMode : TAfsMode; asShare : TAfsShare) : TAfsHandle; Overload; Override;
    Procedure Read(oHandle : TAfsHandle; Var Buffer; iCount : Cardinal); Override;
    Procedure Write(oHandle : TAfsHandle; Const Buffer; iCount : Cardinal); Override;
    Procedure Close(oHandle : TAfsHandle); Overload; Override;

    Function GetSize(oHandle : TAfsHandle) : Int64; Override;
    Function GetPosition(oHandle : TAfsHandle) : Int64; Override;
    Procedure SetPosition(oHandle : TAfsHandle; Const iValue : Int64); Override;

    Function Exists(Const sName : String) : Boolean; Overload; Override;
    Procedure Rename(Const sSource, sDest : String); Override;
    Procedure Delete(Const sName : String); Overload; Override;

    Function OpenIterator : TAfsIterator; Override;
    Procedure CloseIterator(oIterator : TAfsIterator); Override;
  End; { TAfsResourceVolume }

  TAfsResourceManager = Class(TAfsStreamManager)
    Public
      Constructor Create(Const sName : String); Overload; Virtual;
      Constructor Create; Overload; Override;
  End; { TAfsResourceManager }


Implementation


Uses
  Windows,
  FileSupport, StringSupport, MathSupport, ErrorSupport,
  AdvMemories, AdvStringLists;


Type
  TAfsResourceFile = Class(TAdvMemoryStream)
  Private
    FMode : TAfsMode;
    FName : String;
    FType : PChar;
    FDirty : Boolean;
  Protected
    Procedure Error(Const sMethod, sException : String); Override;
  Public
    Procedure Write(Const Buffer; iSize : Cardinal); Override;

    Procedure SetName(Const sName : String);

    Property Mode : TAfsMode Read FMode Write FMode;
    Property Name : String Read FName;
    Property ResourceType : PChar Read FType;
    Property Dirty : Boolean Read FDirty Write FDirty;
  End; { TAfsResourceFile }

  TAfsResourceIterator = Class(TAfsIterator)
  Private
    FModule : THandle;
    FCurrent : TAfsFile;
    FItems : TAdvStringList;
    FIndex : Integer;
  Protected
    Function GetCurrent : TAfsEntity; Override;
  Public
    Constructor Create; Override;
    Destructor Destroy; Override;

    Procedure First; Override;
    Procedure Next; Override;
    Function More : Boolean; Override;

    Property Module : THandle Read FModule;
    Property Items : TAdvStringList Read FItems;
  End; { TAfsResourceIterator }


Const
  CAPACITY_INCREASE = 12; // 1 Shl 12 = 4096
  CAPACITY_INITIAL = 1 Shl CAPACITY_INCREASE;


Procedure ExpandName(Const sName : String; Var sResource : String; Var iType : PChar);
Var
  sTemp : String;
  iLoop : Integer;
Begin { Procedure ExpandName }
  iLoop := 1;
  If sName <> '' Then
  Begin { If }
    While (iLoop < Length(sName)) And (sName[iLoop] <> ',') Do
      Inc(iLoop);

    If sName[iLoop] = ',' Then
    Begin { If }
      sResource := Copy(sName, 1, iLoop - 1);

      sTemp := Copy(sName, iLoop + 1, Length(sName));
      If sTemp[1] = '#' Then
        iType := PChar(StringToInteger32(Copy(sTemp, 2, Length(sTemp))));
    End;  { If }
  End;  { If }
End;  { Procedure ExpandName }


Function EnumNames(hModule : Cardinal; sType, sName : PChar; lParam : Cardinal) : Boolean; Stdcall;
Begin { Function EnumNames }
  If Hi(Cardinal(sType)) = 0 Then // IsIntResource(lpszType)
    TAfsResourceIterator(lParam).Items.Add(StringFormat('%s,#%d', [sName, Integer(sType)]))
  Else
    TAfsResourceIterator(lParam).Items.Add(StringFormat('%s,%s', [sName, sType]));

  Result := True;
End;  { Function EnumNames }


Function EnumTypes(hModule : Cardinal; lpszType : PChar; lParam : Cardinal) : Boolean; Stdcall;
Var
  sName : String;
  iType : PChar;
Begin { Function EnumTypes }
  If Hi(Cardinal(lpszType)) = 0 Then // IsIntResource(lpszType)
    iType := lpszType
  Else
    ExpandName(',' + lpszType, sName, iType);

  EnumResourceNames(hModule, iType, @EnumNames, lParam);

  Result := True;
End;  { Function EnumTypes }


Procedure TAfsResourceVolume.Open;
Begin { Procedure TAfsResourceVolume.Open }
  Inherited;
End;  { Procedure TAfsResourceVolume.Open }


Procedure TAfsResourceVolume.Close;
Begin { Procedure TAfsResourceVolume.Close }
  Inherited;
End;  { Procedure TAfsResourceVolume.Close }


Procedure TAfsResourceVolume.Format;
Var
  hUpdate : Cardinal;
Begin { Procedure TAfsResourceVolume.Format }
  hUpdate := BeginUpdateResource(PChar(Name), True);
  If hUpdate <> 0 Then
    EndUpdateResource(hUpdate, False)
  Else
    Error('Format', 'Could not open update handle.');
End;  { Procedure TAfsResourceVolume.Format }


Procedure TAfsResourceVolume.Delete;
Begin { Procedure TAfsResourceVolume.Delete }
  FileDelete(Name);
End;  { Procedure TAfsResourceVolume.Delete }


Function TAfsResourceVolume.Exists : Boolean;
Begin { Function TAfsResourceVolume.Exists }
  Result := FileExists(Name);
End;  { Function TAfsResourceVolume.Exists }


Function TAfsResourceVolume.Active : Boolean;
Begin { Function TAfsResourceVolume.Active }
  Result := Exists;
End;  { Function TAfsResourceVolume.Active }


Function TAfsResourceVolume.Open(Const sName : String; amMode : TAfsMode; asShare : TAfsShare) : TAfsHandle;
Var
  oFile : TAfsResourceFile;
  hModule, hResource, iResource, hGlobal : Cardinal;
  pResource : Pointer;
Begin { Function TAfsResourceVolume.Open }
  If amMode > Mode Then
    Error('Open', StringSupport.StringFormat('Requested access mode denied on "%s"', [sName]));

  iResource := 0;
  pResource := Nil;

  oFile := TAfsResourceFile.Create;
  Try
    oFile.Mode := amMode;
    oFile.SetName(sName);

    Case amMode Of
      amRead, amWrite:
      Begin { amRead }
        hModule := LoadLibrary(PChar(Name));
        Try
          hResource := FindResource(hModule, PChar(oFile.Name), oFile.ResourceType);
          If hResource <> 0 Then
          Begin { If }
            iResource := SizeofResource(hModule, hResource);
            If iResource <> 0 Then
            Begin { If }
              hGlobal := LoadResource(hModule, hResource);
              If hGlobal <> 0 Then
              Begin { If }
                pResource := LockResource(hGlobal);
              End;  { If }
            End;  { If }
          End   { If }
          Else
            Error('Open', StringSupport.StringFormat('File not found "%s"', [sName]));

          oFile.Capacity := iResource;
          Move(pResource^, oFile.DataPointer^, iResource);
          oFile.Size := iResource;
        Finally
          FreeLibrary(hModule);
        End; { Try }
      End;  { amRead }

      amCreate:
      Begin { amCreate }
        oFile.Capacity := CAPACITY_INITIAL;
      End;  { amCreate }
    End;  { Case }

    Result := TAfsHandle(oFile);
  Except
    // Prevent object leaking if initialisation code raises an exception
    oFile.Free;
    Result := 0;
  End; { Try }
End;  { Function TAfsResourceVolume.Open }


Procedure TAfsResourceVolume.Read(oHandle : TAfsHandle; Var Buffer; iCount : Cardinal);
Begin { Procedure TAfsResourceVolume.Read }
  TAfsResourceFile(oHandle).Read(Buffer, iCount);
End;  { Procedure TAfsResourceVolume.Read }


Procedure TAfsResourceVolume.Write(oHandle : TAfsHandle; Const Buffer; iCount : Cardinal);
Begin { Procedure TAfsResourceVolume.Write }
  TAfsResourceFile(oHandle).Write(Buffer, iCount);
End;  { Procedure TAfsResourceVolume.Write }


Procedure TAfsResourceVolume.Close(oHandle : TAfsHandle);

Var
  oFile : TAfsResourceFile;
  hUpdate: Cardinal;

Begin { Procedure TAfsResourceVolume.Close }
  oFile := TAfsResourceFile(oHandle);
  Try
    If oFile.Dirty Then
    Begin { If }
      hUpdate := BeginUpdateResource(PChar(Name), False);
      If hUpdate <> 0 Then
      Begin { If }
        Try
          If Not UpdateResource(hUpdate, oFile.ResourceType, PChar(oFile.Name), 0 {iLanguage}, oFile.DataPointer, oFile.Size) Then
            Error('Close', ErrorAsString);
          EndUpdateResource(hUpdate, False);
        Except
          EndUpdateResource(hUpdate, True);
        End; { Try }
      End   { If }
      Else
        Error('Close', 'Could not open update handle.');
    End;  { If }
  Finally
    oFile.Free;
  End; { Try }
End;  { Procedure TAfsResourceVolume.Close }


Function TAfsResourceVolume.GetSize(oHandle : TAfsHandle) : Int64;
Begin { Function TAfsResourceVolume.GetSize }
  Result := TAfsResourceFile(oHandle).Size;
End;  { Function TAfsResourceVolume.GetSize }


Function TAfsResourceVolume.GetPosition(oHandle : TAfsHandle) : Int64;
Begin { Function TAfsResourceVolume.GetPosition }
  Result := TAfsResourceFile(oHandle).Position;
End;  { Function TAfsResourceVolume.GetPosition }


Procedure TAfsResourceVolume.SetPosition(oHandle : TAfsHandle; Const iValue : Int64);
Begin { Procedure TAfsResourceVolume.SetPosition }
  TAfsResourceFile(oHandle).Position := iValue;
End;  { Procedure TAfsResourceVolume.SetPosition }


Function TAfsResourceVolume.Exists(Const sName : String) : Boolean;
Var
  hModule : Cardinal;
  sResource : String;
  iType : PChar;
Begin { Function TAfsResourceVolume.Exists }
  ExpandName(sName, sResource, iType);
  hModule := LoadLibrary(PChar(Name));
  Try
    Result := FindResource(hModule, PChar(sResource), iType) <> 0;
  Finally
    FreeLibrary(hModule);
  End; { Try }
End;  { Function TAfsResourceVolume.Exists }


Procedure TAfsResourceVolume.Rename(Const sSource, sDest : String);
Var
  hSource, hDest : TAfsHandle;
Begin { Procedure TAfsResourceVolume.Rename }
  If Exists(sDest) Then
    Error('Rename', StringSupport.StringFormat('Cannot rename to "%s" - target filename already exists', [sDest]))
  Else
  Begin { If }
    hSource := Open(sSource, amRead, asWrite);
    hDest := Open(sDest, amCreate, asNone);
    Try
      // Have to call the Write method to update the dirty flag - still,
      // we bypass a buffer copy doing it this way.
      Write(hDest, TAfsResourceFile(hSource).DataPointer^, TAfsResourceFile(hSource).Size); 
    Finally
      Close(hSource);
      Close(hDest);
    End; { Try }
  End;  { If }
End;  { Procedure TAfsResourceVolume.Rename }


Procedure TAfsResourceVolume.Delete(Const sName : String);
Var
  hUpdate : Integer;
  sResource : String;
  iType : PChar;
Begin { Procedure TAfsResourceVolume.Delete }
  ExpandName(sName, sResource, iType);
  hUpdate := BeginUpdateResource(PChar(Name), False);
  If hUpdate <> 0 Then
  Begin { If }
    Try
      If Not UpdateResource(hUpdate, iType, PChar(sResource), 0 {iLanguage}, Nil, 0) Then
        Error('Delete', ErrorAsString);
      EndUpdateResource(hUpdate, False);
    Except
      EndUpdateResource(hUpdate, True);
    End; { Try }
  End   { If }
  Else
    Error('Close', 'Could not open update handle.');
End;  { Procedure TAfsResourceVolume.Delete }


Function TAfsResourceVolume.OpenIterator : TAfsIterator;
Begin { Function TAfsResourceVolume.OpenIterator }
  Result := TAfsResourceIterator.Create(Self.Link);
End;  { Function TAfsResourceVolume.OpenIterator }


Procedure TAfsResourceVolume.CloseIterator(oIterator : TAfsIterator);
Begin { Procedure TAfsResourceVolume.CloseIterator }
  oIterator.Free;
End;  { Procedure TAfsResourceVolume.CloseIterator }


Procedure TAfsResourceFile.Error(Const sMethod, sException : String);
Begin { Procedure TAfsResourceFile.Error }
  Error(EAfs, sMethod, sException);
End;  { Procedure TAfsResourceFile.Error }


Procedure TAfsResourceFile.Write(Const Buffer; iSize : Cardinal);
Begin { Procedure TAfsResourceFile.Write }
  If Mode = amRead Then
    Error('Write', 'File was opened read-only.');

  // Calculate required capacity increase - round up to next 4Kb
  If Writeable < iSize Then
    Capacity := (((Capacity + iSize) Shr CAPACITY_INCREASE) + 1) Shl CAPACITY_INCREASE;

  Inherited;
  FDirty := True;
End;  { Procedure TAfsResourceFile.Write }


Procedure TAfsResourceFile.SetName(Const sName : String);
Begin { Procedure TAfsResourceFile.SetName }
  ExpandName(sName, FName, FType);

  Assert(Condition((FName <> ''), 'SetName', 'Resource name must be of form <Name>,#<Type>'));
End;  { Procedure TAfsResourceFile.SetName }


Function TAfsResourceIterator.GetCurrent : TAfsEntity;
Begin { Function TAfsResourceIterator.GetCurrent }
  If FItems.ExistsByIndex(FIndex) Then
  Begin { If }
    FCurrent.Name := FItems[FIndex];
    Result := FCurrent;
  End   { If }
  Else
    Result := Nil;
End;  { Function TAfsResourceIterator.GetCurrent }


Constructor TAfsResourceIterator.Create;
Begin { Constructor TAfsResourceIterator.Create }
  Inherited Create;

  FCurrent := TAfsFile.Create;
  FItems := TAdvStringList.Create;
End;  { Constructor TAfsResourceIterator.Create }


Destructor TAfsResourceIterator.Destroy;
Begin { Destructor TAfsResourceIterator.Destroy }
  FItems.Free;
  FCurrent.Free;
  Inherited;
End;  { Destructor TAfsResourceIterator.Destroy }


Procedure TAfsResourceIterator.First;
Begin { Procedure TAfsResourceIterator.First }
  FItems.Clear;
  FCurrent.Volume := Volume.Link;
  FModule := LoadLibrary(PChar(Volume.Name));
  Try
    EnumResourceTypes(FModule, @EnumTypes, Integer(Self));
    FIndex := 0;
  Finally
    FreeLibrary(FModule);
    FModule := 0;
  End; { Try }
End;  { Procedure TAfsResourceIterator.First }


Procedure TAfsResourceIterator.Next;
Begin { Procedure TAfsResourceIterator.Next }
  Inc(FIndex);
End;  { Procedure TAfsResourceIterator.Next }


Function TAfsResourceIterator.More : Boolean;
Begin { Function TAfsResourceIterator.More }
  Result := FItems.ExistsByIndex(FIndex);
End;  { Function TAfsResourceIterator.More }


Constructor TAfsResourceManager.Create;
Begin { Constructor TAfsResourceManager.Create }
  Inherited;

  Volume := TAfsResourceVolume.Create;
  Volume.Mode := amRead;

  Mode := amRead;
End;  { Constructor TAfsResourceManager.Create }


Constructor TAfsResourceManager.Create(Const sName: String);
Begin { Constructor TAfsResourceManager.Create }
  Create;

  Volume.Name := sName;
End;  { Constructor TAfsResourceManager.Create }


End. // AfsResourceVolumes //
