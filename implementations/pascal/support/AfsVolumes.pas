Unit AfsVolumes;

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
  AdvObjects, AdvStringHashes, AdvStreams, AdvExceptions, AdvObjectLists, AdvIterators,
  AdvItems;

Type

  TAfsMode = (amRead, amWrite, amCreate);
  TAfsShare = (asNone, asRead, asWrite);

  // Simple types

  TAfsHandle = Cardinal;

  // Forward declarations

  TAfsVolume = Class;
  TAfsList = Class;
  TAfsEntity = Class;

  EAfs = Class(EAdvException);

  TAfsObject = Class(TAdvStringHashEntry)
  Protected
    Procedure Error(Const sMethod, sException : String); Override;
  End; { TAfsObject }

  TAfsClass = Class Of TAfsObject;

  TAfsIterator = Class(TAdvIterator)
  Private
    FVolume : TAfsVolume;
    Procedure SetVolume(Const Value: TAfsVolume);
  Protected
    Procedure Error(Const sMethod, sMessage : String); Override;
    Function GetCurrent : TAfsEntity; Virtual; Abstract;
  Public
    Constructor Create(oVolume : TAfsVolume); Overload; Virtual;
    Destructor Destroy; Override;

    Property Current : TAfsEntity Read GetCurrent;
    Property Volume : TAfsVolume Read FVolume Write SetVolume;
  End; { TAfsIterator }

  TAfsIteratorClass = Class Of TAfsIterator;

  TAfsVolume = Class(TAfsObject)
  Private
    FMode : TAfsMode;
  Protected
    Function GetAllocation : Cardinal; Virtual;
    Procedure SetAllocation(Value : Cardinal); Virtual;
  Public
    Function Link : TAfsVolume; Overload;
    Function Clone : TAfsVolume; Overload;    

    Procedure Open; Overload; Virtual;
    Procedure Format; Overload; Virtual;
    Procedure Close; Overload; Virtual;

    Procedure Delete; Overload; Virtual;
    Function Exists : Boolean; Overload; Virtual; Abstract;
    Function Active : Boolean; Overload; Virtual; Abstract;

    Function Open(Const sName : String; amMode : TAfsMode; asShare : TAfsShare) : TAfsHandle; Overload; Virtual; Abstract;
    Procedure Read(oHandle : TAfsHandle; Var Buffer; iCount : Cardinal); Virtual; Abstract;
    Procedure Write(oHandle : TAfsHandle; Const Buffer; iCount : Cardinal); Virtual; Abstract;
    Procedure Close(oHandle : TAfsHandle); Overload; Virtual; Abstract;

    Function GetSize(oHandle : TAfsHandle) : Int64; Virtual; Abstract;
    Function GetPosition(oHandle : TAfsHandle) : Int64; Virtual; Abstract;
    Procedure SetPosition(oHandle : TAfsHandle; Const iValue : Int64); Virtual; Abstract;

    Function Exists(Const sName : String) : Boolean; Overload; Virtual; Abstract;
    Procedure Rename(Const sSource, sDestination : String); Overload; Virtual; Abstract;
    Procedure Delete(Const sName : String); Overload; Virtual; Abstract;
                                
    Function OpenIterator : TAfsIterator; Virtual; Abstract;
    Procedure CloseIterator(oIterator : TAfsIterator); Virtual; Abstract;

    Property Allocation : Cardinal Read GetAllocation Write SetAllocation;
    Property Mode : TAfsMode Read FMode Write FMode;
  End; { TAfsVolume }

  TAfsVolumeClass = Class Of TAfsVolume;

  TAfsStream = Class(TAdvAccessStream)
  Private
    FVolume : TAfsVolume;
    FHandle : TAfsHandle;
    Procedure SetVolume(Const Value: TAfsVolume);
  Protected
    Function GetPosition : Int64; Override;
    Procedure SetPosition(Const Value : Int64); Override;

    Function GetSize : Int64; Override;
    Procedure SetSize(Const Value : Int64); Override;
  Public
    Constructor Create(oVolume : TAfsVolume; oHandle : TAfsHandle); Overload;
    Destructor Destroy; Override;

    Procedure Read(Var Buffer; iCount : Cardinal); Override;
    Procedure Write(Const Buffer; iCount : Cardinal); Override;

    Function Readable : Int64; Override;

    Property Volume : TAfsVolume Read FVolume Write SetVolume;
    Property Handle : TAfsHandle Read FHandle Write FHandle;
  End; { TAfsStream }
                                               
  TAfsEntity = Class(TAfsObject)
  Private
    FVolume : TAfsVolume;
    FStream : TAfsStream;
    FMode   : TAfsMode;
    FShare  : TAfsShare;

    Procedure SetMode(Value : TAfsMode);
    Procedure SetShare(Value : TAfsShare);
    Procedure SetVolume(Const Value: TAfsVolume);

  Public
    Constructor Create; Overload; Override;
    Destructor Destroy; Override;
    Constructor Create(oVolume : TAfsVolume; Const sName : String = ''); Overload;

    Procedure Assign(oSource : TAdvObject); Override;

    Procedure Open; Overload; Virtual;
    Procedure Open(amMode : TAfsMode; asShare : TAfsShare = asRead); Overload;
    Procedure Open(Const sName : String; amMode : TAfsMode; asShare : TAfsShare = asRead); Overload;
    Procedure Close;

    Function Valid : Boolean; Overload; Virtual;

    Property Volume : TAfsVolume Read FVolume Write SetVolume;
    Property Stream : TAfsStream Read FStream;
    Property Mode : TAfsMode Read FMode Write SetMode;
    Property Share : TAfsShare Read FShare Write SetShare;
  End; { TAfsEntity }

  TAfsEntityClass = Class Of TAfsEntity;

  TAfsEntities = Class(TAdvStringHashTable)
  End; { TAfsEntities }

  TAfsContainer = Class(TAfsEntity)
  Private
    FItems : TAfsEntities;
  Protected
    Property Items : TAfsEntities Read FItems;
  Public
    Constructor Create; Override;
    Destructor Destroy; Override;
  End; { TAfsContainer }

  TAfsRoot = Class(TAfsContainer)
  Public
    Property Items;
  End; { TAfsRoot }

  TAfsFolder = Class(TAfsContainer)
  Public
    Property Items;
  End; { TAfsFolder }

  TAfsFile = Class(TAfsEntity)
  End; { TAfsFile }

  TAfsList = Class(TAdvObjectList)
  Private
    Function GetEntities(Index : Integer) : TAfsEntity;
    Procedure SetEntities(Index : Integer; Const Value : TAfsEntity);
  Protected
    Function CompareName(pA, pB : Pointer) : Integer; Overload; Virtual;

    Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Overload; Override;

    Function ItemClass : TAdvClass; Override;
  Public
    Property Entities[Index : Integer] : TAfsEntity Read GetEntities Write SetEntities; Default;
  End; { TAfsList }

  TAfsFiles = Class(TAfsList)
  Private
    Function GetFiles(Index : Integer) : TAfsFile;
    Procedure SetFiles(Index : Integer; Const Value : TAfsFile);
  Public
    Property Files[Index : Integer] : TAfsFile Read GetFiles Write SetFiles; Default;
  End; { TAfsFiles }

Implementation

Uses
  StringSupport, MathSupport;


Procedure TAfsObject.Error(Const sMethod, sException : String);

Begin { Procedure TAfsObject.Error }
  Error(EAfs, sMethod, sException);
End;  { Procedure TAfsObject.Error }



Procedure TAfsIterator.Error(Const sMethod, sMessage : String);

Begin { Procedure TAfsIterator.Error }
  Error(EAfs, sMethod, sMessage);
End;  { Procedure TAfsIterator.Error }

Constructor TAfsIterator.Create(oVolume : TAfsVolume);

Begin { Constructor TAfsIterator.Create }
  Create;
  FVolume := oVolume;
End;  { Constructor TAfsIterator.Create }



Function TAfsVolume.GetAllocation : Cardinal;

Begin { Function TAfsVolume.GetAllocation }
  Result := 0;
End;  { Function TAfsVolume.GetAllocation }

Procedure TAfsVolume.SetAllocation(Value : Cardinal);

Begin { Procedure TAfsVolume.SetAllocation }
  Error('SetAllocation', 'Cannot set allocation unit size.');
End;  { Procedure TAfsVolume.SetAllocation }

Function TAfsVolume.Clone: TAfsVolume;
Begin
  Result := TAfsVolume(Inherited Clone);
End;

Function TAfsVolume.Link: TAfsVolume;
Begin
  Result := TAfsVolume(Inherited Link);
End;

Procedure TAfsVolume.Open;

Begin { Procedure TAfsVolume.Open }
End;  { Procedure TAfsVolume.Open }

Procedure TAfsVolume.Format;

Begin { Procedure TAfsVolume.Format }
End;  { Procedure TAfsVolume.Format }

Procedure TAfsVolume.Close;

Begin { Procedure TAfsVolume.Close }
End;  { Procedure TAfsVolume.Close }

Procedure TAfsVolume.Delete;

Begin { Procedure TAfsVolume.Delete }
End;  { Procedure TAfsVolume.Delete }



Function TAfsStream.GetPosition : Int64;

Begin { Function TAfsStream.GetPosition }
  Result := FVolume.GetPosition(FHandle);
End;  { Function TAfsStream.GetPosition }

Procedure TAfsStream.SetPosition(Const Value : Int64);

Begin { Procedure TAfsStream.SetPosition }
  FVolume.SetPosition(FHandle, Value);
End;  { Procedure TAfsStream.SetPosition }

Function TAfsStream.GetSize : Int64;

Begin { Function TAfsStream.GetSize }
  Result := FVolume.GetSize(FHandle);
End;  { Function TAfsStream.GetSize }

Procedure TAfsStream.SetSize(Const Value : Int64);

Begin { Procedure TAfsStream.SetSize }
  Error('SetSize', 'Not implemented');
//  FVolume.SetSize(FHandle, Value);
End;  { Procedure TAfsStream.SetSize }

Constructor TAfsStream.Create(oVolume : TAfsVolume; oHandle : TAfsHandle);

Begin { Constructor TAfsStream.Create }
  Create;
  FVolume := oVolume;
  FHandle := oHandle;
End;  { Constructor TAfsStream.Create }

Procedure TAfsStream.Read(Var Buffer; iCount : Cardinal);

Begin { Procedure TAfsStream.Read }
  FVolume.Read(FHandle, Buffer, iCount);
End;  { Procedure TAfsStream.Read }

Procedure TAfsStream.Write(Const Buffer; iCount : Cardinal);

Begin { Procedure TAfsStream.Write }
  FVolume.Write(FHandle, Buffer, iCount);
End;  { Procedure TAfsStream.Write }

Function TAfsStream.Readable : Int64;

Begin { Function TAfsStream.Readable }
  Result := Size - Position;
End;  { Function TAfsStream.Readable }



Procedure TAfsEntity.SetMode(Value : TAfsMode);

Begin { Procedure TAfsEntity.SetMode }
  FMode := Value;
End;  { Procedure TAfsEntity.SetMode }

Procedure TAfsEntity.SetShare(Value : TAfsShare);

Begin { Procedure TAfsEntity.SetShare }
  FShare := Value;
End;  { Procedure TAfsEntity.SetShare }

Constructor TAfsEntity.Create;

Begin { Constructor TAfsEntity.Create }
  Inherited Create;

  FStream := TAfsStream.Create;

  FShare := asRead;
End;  { Constructor TAfsEntity.Create }

Destructor TAfsEntity.Destroy;

Begin { Destructor TAfsEntity.Destroy }
  FStream.Free;
  FVolume.Free;

  Inherited;
End;  { Destructor TAfsEntity.Destroy }

Constructor TAfsEntity.Create(oVolume : TAfsVolume; Const sName : String);

Begin { Constructor TAfsEntity.Create }
  Create;

  FVolume := oVolume;
  Name := sName;
End;  { Constructor TAfsEntity.Create }

Procedure TAfsEntity.Assign(oSource : TAdvObject);

Begin { Procedure TAfsEntity.Assign }
  Inherited;
  
  FVolume := TAfsEntity(oSource).Volume;
  FMode := TAfsEntity(oSource).Mode;
  FShare := TAfsEntity(oSource).Share;
End;  { Procedure TAfsEntity.Assign }

Procedure TAfsEntity.Open;

Begin { Procedure TAfsEntity.Open }
  If Not Assigned(FVolume) Then
    Error('Open', 'AFS volume not assigned.')
  Else
  Begin { If }
    FStream.Volume := FVolume.Link;
    FStream.Handle := FVolume.Open(Name, FMode, FShare);

    If FStream.Handle = 0 Then
      Error('Open', StringFormat('Unable to open ''%s'' in volume ''%s''', [Name, FVolume.Name]));
  End   { If }
End;  { Procedure TAfsEntity.Open }

Procedure TAfsEntity.Open(amMode : TAfsMode; asShare : TAfsShare = asRead);
Begin { Procedure TAfsEntity.Open }
  FMode := amMode;
  FShare := asShare;
  Open;
End;  { Procedure TAfsEntity.Open }


Procedure TAfsEntity.Open(Const sName : String; amMode : TAfsMode; asShare : TAfsShare);
Begin { Procedure TAfsEntity.Open }
  Name := sName;
  Open(amMode, asShare);
End;  { Procedure TAfsEntity.Open }


Procedure TAfsEntity.Close;
Begin { Procedure TAfsEntity.Close }
  If FStream.Handle <> 0 Then
  Begin { If }
    FVolume.Close(FStream.Handle);
    FStream.Handle := 0;
    FStream.Volume := Nil;
  End;  { If }
End;  { Procedure TAfsEntity.Close }


Function TAfsEntity.Valid : Boolean;
Begin { Function TAfsEntity.Valid }
  Result := Assigned(FVolume) And Assigned(FStream);
End;  { Function TAfsEntity.Valid }


Procedure TAfsEntity.SetVolume(Const Value: TAfsVolume);
Begin { Procedure TAfsEntity.SetVolume }
  FVolume.Free;
  FVolume := Value;
End;  { Procedure TAfsEntity.SetVolume }


Constructor TAfsContainer.Create;
Begin { Constructor TAfsContainer.Create }
  Inherited Create;

  FItems := TAfsEntities.Create;
  FItems.Capacity := 37; // Arbitrary low prime
End;  { Constructor TAfsContainer.Create }


Destructor TAfsContainer.Destroy;
Begin { Destructor TAfsContainer.Destroy }
  FItems.Free;

  Inherited;
End;  { Destructor TAfsContainer.Destroy }


Function TAfsList.GetEntities(Index : Integer) : TAfsEntity;
Begin { Function TAfsList.GetEntities }
  Result := TAfsEntity(ItemByIndex[Index]);
End;  { Function TAfsList.GetEntities }


Procedure TAfsList.SetEntities(Index : Integer; Const Value : TAfsEntity);
Begin { Procedure TAfsList.SetEntities }
  ItemByIndex[Index] := Value;
End;  { Procedure TAfsList.SetEntities }


Function TAfsList.CompareName(pA, pB : Pointer) : Integer;
Begin { Function TAfsList.DefaultCompare }
  Result := IntegerCompare(Integer(TAfsEntity(pA).ClassType), Integer(TAfsEntity(pB).ClassType));

  If Result = 0 Then
    Result := StringCompare(TAfsEntity(pA).Name, TAfsEntity(pB).Name);
End;  { Function TAfsList.DefaultCompare }


Procedure TAfsList.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin { Procedure TAfsList.DefaultCompare }
  aCompare := CompareName;
End;  { Procedure TAfsList.DefaultCompare }


Function TAfsList.ItemClass : TAdvClass;
Begin { Function TAfsList.ItemClass }
  Result := TAfsEntity;
End;  { Function TAfsList.ItemClass }


Function TAfsFiles.GetFiles(Index : Integer) : TAfsFile;
Begin { Function TAfsFiles.GetFiles }
  Result := TAfsFile(Entities[Index]);
End;  { Function TAfsFiles.GetFiles }


Procedure TAfsFiles.SetFiles(Index : Integer; Const Value : TAfsFile);
Begin { Procedure TAfsFiles.SetFiles }
  Entities[Index] := Value;
End;  { Procedure TAfsFiles.SetFiles }


Destructor TAfsIterator.Destroy;
Begin { Destructor TAfsIterator.Destroy }
  FVolume.Free;

  Inherited;
End;  { Destructor TAfsIterator.Destroy }


Procedure TAfsIterator.SetVolume(Const Value: TAfsVolume);
Begin { Procedure TAfsIterator.SetVolume }
  FVolume.Free;
  FVolume := Value;
End;  { Procedure TAfsIterator.SetVolume }


Destructor TAfsStream.Destroy;
Begin { Destructor TAfsStream.Destroy }
  FVolume.Free;

  Inherited;
End;  { Destructor TAfsStream.Destroy }


Procedure TAfsStream.SetVolume(Const Value: TAfsVolume);
Begin { Procedure TAfsStream.SetVolume }
  FVolume.Free;
  FVolume := Value;
End;  { Procedure TAfsStream.SetVolume }


End. // AfsVolumes //
