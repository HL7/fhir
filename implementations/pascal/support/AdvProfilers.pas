Unit AdvProfilers;


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
  MemorySupport, StringSupport, DateSupport, HashSupport,
  EncodeSupport, MathSupport,
  AdvObjects, AdvExceptions, AdvFilers, AdvCollections, AdvIterators, AdvStringIntegerMatches,
  AdvStreams, AdvPersistents, AdvParameters;


Type
  TAdvProfileCode = Cardinal;

  PAdvProfileItem = ^TAdvProfileItem;
  TAdvProfileItem = Record
    Key  : TObject;
    Next : PAdvProfileItem;

    // All fields must be initialised in TAdvProfiler.Add
    Frozen     : Boolean;
    Breakpoint : Boolean;
    Permanent  : Boolean;
  End;
  TAdvProfileItemArray = Array[0..(MaxInt Div SizeOf(TAdvProfileItem)) - 1] Of PAdvProfileItem;
  PAdvProfileItemArray = ^TAdvProfileItemArray;

  TAdvProfiler = Class(TAdvCollection)
    Private
      FProfileArray : PAdvProfileItemArray;
      FCount : Integer;
      FCapacity : Integer;
      FBalance : Real;
      FThreshold : Integer;
      FWatermark : Integer;
      FTotal : Integer;

      Procedure SetCapacity(Const Value: Integer);

      Function GetBreakpoint(oObject : TObject) : Boolean;
      Procedure SetBreakpoint(oObject: TObject; Const Value: Boolean);

      Function GetPermanent(oObject : TObject) : Boolean;
      Procedure SetPermanent(oObject: TObject; Const Value: Boolean);

      Function GetFrozen(oObject : TObject) : Boolean;
      Procedure SetFrozen(oObject: TObject; Const Value: Boolean);

    Protected
      Procedure Insert(iIndex : Integer; pProfile : PAdvProfileItem);

      Function Generate(oObject : TObject) : TAdvProfileCode;
      Function Resolve(iCode: TAdvProfileCode): Integer;
      Function Find(oObject : TObject) : PAdvProfileItem;

      Procedure InternalClear; Override;

      Procedure Rehash;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Function Iterator : TAdvIterator; Override;

      // Management
      Procedure Add(oObject : TObject);
      Function Delete(oObject : TObject) : Boolean;
      Function Exists(oObject : TObject) : Boolean;

      // Reporting
      Procedure CollectFrequencyMatch(oMatch : TAdvStringIntegerMatch); 
      Function FrequencyCountByClassName(Const sClass : String) : Integer; 

      Property Capacity : Integer Read FCapacity Write SetCapacity;
      Property Breakpoint[oObject : TObject] : Boolean Read GetBreakpoint Write SetBreakpoint;
      Property Permanent[oObject : TObject] : Boolean Read GetPermanent Write SetPermanent;
      Property Frozen[oObject : TObject] : Boolean Read GetFrozen Write SetFrozen;
      Property Count : Integer Read FCount;
      Property Watermark : Integer Read FWatermark;
      Property Total : Integer Read FTotal;
  End; 

  TAdvProfilerIterator = Class(TAdvIterator)
    Private
      FProfiler : TAdvProfiler;
      FIndex : Integer;
      FCurrent : PAdvProfileItem;
      FNext : PAdvProfileItem;

      Procedure SetProfiler(Const Value: TAdvProfiler);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure First; Override;
      Procedure Next; Override;
      Function More : Boolean; Override;

      Function Count : Integer; 
      Function Current : TObject; 

      Property Profiler : TAdvProfiler Read FProfiler Write SetProfiler;
  End;


Implementation



Constructor TAdvProfiler.Create;
Begin 
  Inherited;

  FBalance := 0.85;
End;  


Destructor TAdvProfiler.Destroy;
Begin
  Clear;

  MemoryDestroy(FProfileArray, FCapacity * SizeOf(PAdvProfileItem));
  FProfileArray := Nil;

  Inherited;
End;  


Procedure TAdvProfiler.Assign(oObject: TAdvObject);
Begin 
  Inherited;

  FWatermark := TAdvProfiler(oObject).Watermark;
  FTotal := TAdvProfiler(oObject).Total;
End;  


Procedure TAdvProfiler.Define(oFiler: TAdvFiler);
Begin 
  Inherited;

  oFiler['Watermark'].DefineInteger(FWatermark);
  oFiler['Total'].DefineInteger(FTotal);
End;  


Function TAdvProfiler.Iterator : TAdvIterator;
Begin 
  Result := TAdvProfilerIterator.Create;
  TAdvProfilerIterator(Result).Profiler := TAdvProfiler(Self.Link);
End;  


Procedure TAdvProfiler.InternalClear;
Var
  pProfile : PAdvProfileItem;
  pNext    : PAdvProfileItem;
  iLoop    : Integer;
Begin 
  Inherited;

  For iLoop := 0 To FCapacity - 1 Do
  Begin 
    pProfile := FProfileArray^[iLoop];
    FProfileArray^[iLoop] := Nil;

    While Assigned(pProfile) Do
    Begin
      pNext := pProfile^.Next;
      Dispose(pProfile);
      pProfile := pNext;
    End;  
  End;  

  FCount := 0;
  FTotal := 0;
  FWatermark := 0;
End;  


Function TAdvProfiler.Generate(oObject: TObject): TAdvProfileCode;
Begin 
  Result := HashSupport.HashIntegerToCode32(Integer(oObject));
End;  


Function TAdvProfiler.Resolve(iCode : TAdvProfileCode) : Integer;
Begin
  Result := SignedMod(Integer(iCode), FCapacity);
End;  


Function TAdvProfiler.Find(oObject: TObject): PAdvProfileItem;
Begin 
  Result := FProfileArray^[Resolve(Generate(oObject))];

  While Assigned(Result) And (Result^.Key <> oObject) Do
    Result := Result^.Next;
End;  


Procedure TAdvProfiler.Insert(iIndex: Integer; pProfile: PAdvProfileItem);
Var
  pFirst : ^PAdvProfileItem;
Begin 
  // Insert new object to start of linked list.
  pFirst := @(FProfileArray^[iIndex]);
  pProfile^.Next := pFirst^;
  pFirst^ := pProfile;
End;  


Procedure TAdvProfiler.Add(oObject : TObject);
Var
  pNew : PAdvProfileItem;
Begin 
  Assert(Condition(Not Exists(oObject), 'Add', 'Object reference already registered for construction (possibly missing call to inherited in Destroy and the reference has been reused).'));

  If FCount >= FThreshold Then
    Rehash;

  New(pNew);

  // All fields of the record must be initialised (otherwise will be undefined memory)

  pNew^.Key := oObject;
  pNew^.Breakpoint := False;
  pNew^.Permanent := False;
  pNew^.Frozen := False;

  // Next is set by Insert.

  Insert(Resolve(Generate(oObject)), pNew);

  // Insert is used by rehash, so the measurements must be calculated in Add.
  Inc(FCount);

  If FCount > FWatermark Then
    FWatermark := FCount;

  Inc(FTotal);
End;  


Function TAdvProfiler.Delete(oObject: TObject): Boolean;
Var
  pProfile : ^PAdvProfileItem;
  pLast    : PAdvProfileItem;
  pCurrent : PAdvProfileItem;
Begin
  pProfile := @(FProfileArray^[Resolve(Generate(oObject))]);

  Result := Assigned(pProfile^);

  If Result Then
  Begin 
    pLast := pProfile^;

    If pProfile^.Key = oObject Then
    Begin 
      pProfile^ := pProfile^^.Next;

      Dispose(pLast);
    End   
    Else
    Begin 
      pCurrent := pLast.Next;

      While Assigned(pCurrent) And (pCurrent^.Key <> oObject) Do
      Begin 
        pLast := pCurrent;
        pCurrent := pCurrent^.Next;
      End;  

      Result := Assigned(pCurrent);

      If Result Then
      Begin 
        pLast^.Next := pCurrent^.Next;

        Dispose(pCurrent);
      End;  
    End;  

    If Result Then
      Dec(FCount);
  End;
End;  


Function TAdvProfiler.Exists(oObject: TObject): Boolean;
Begin 
  Result := Assigned(Find(oObject));
End;  


Procedure TAdvProfiler.Rehash;
Var
  pProfileArray : PAdvProfileItemArray;
  pProfile : PAdvProfileItem;
  pNext : PAdvProfileItem;
  iCapacity : Cardinal;
  iLoop : Integer;
Begin
  pProfileArray := FProfileArray;
  FProfileArray := Nil;
  iCapacity := FCapacity;
  FCapacity := 0;
  // FCount is not incremented by method Insert.
  Try
    Try
      Capacity := (iCapacity * 2) + 1;
    Except
      // Revert to hash table before out of memory exception.
      If ExceptObject.ClassType = EOutOfMemory Then
      Begin
        FProfileArray := pProfileArray;
        FCapacity := iCapacity;

        pProfileArray := Nil;
        iCapacity := 0;
      End;

      Raise;
    End;

    For iLoop := 0 To iCapacity - 1 Do
    Begin
      pProfile := pProfileArray^[iLoop];

      While Assigned(pProfile) Do
      Begin
        pNext := pProfile^.Next;

        Insert(Resolve(Generate(pProfile^.Key)), pProfile);

        pProfile := pNext;
      End;
    End;
  Finally
    MemoryDestroy(pProfileArray, iCapacity * SizeOf(PAdvProfileItem));
  End;
End;


Procedure TAdvProfiler.SetCapacity(Const Value: Integer);
Begin
  MemoryResize(FProfileArray, FCapacity * SizeOf(PAdvProfileItem), Value * SizeOf(PAdvProfileItem));

  If Value > FCapacity Then
    MemoryZero(Pointer(NativeUInt(FProfileArray) + NativeUInt(FCapacity * SizeOf(PAdvProfileItem))), (Value - FCapacity) * SizeOf(PAdvProfileItem));

  FCapacity := Value;
  FThreshold := Trunc(FCapacity * FBalance);
End;  


Function TAdvProfiler.GetBreakpoint(oObject : TObject): Boolean;
Var
  pProfile : PAdvProfileItem;
Begin 
  pProfile := Find(oObject);

  Result := Assigned(pProfile) And (pProfile^.Breakpoint);
End;  


Procedure TAdvProfiler.SetBreakpoint(oObject: TObject; Const Value: Boolean);
Var
  pProfile : PAdvProfileItem;
Begin 
  pProfile := Find(oObject);

  If Not Assigned(pProfile) Then
    Error('SetBreakpoint', 'Object is invalid or is not registered.');

  pProfile^.Breakpoint := Value;
End;  


Function TAdvProfiler.GetPermanent(oObject : TObject): Boolean;
Var
  pProfile : PAdvProfileItem;
Begin 
  pProfile := Find(oObject);

  Result := Assigned(pProfile) And (pProfile^.Permanent);
End;  


Procedure TAdvProfiler.SetPermanent(oObject: TObject; Const Value: Boolean);
Var
  pProfile : PAdvProfileItem;
Begin 
  pProfile := Find(oObject);

  If Not Assigned(pProfile) Then
    Error('SetPermanent', 'Object is invalid or is not registered.');

  If pProfile^.Permanent <> Value Then
    pProfile^.Permanent := Value;
End;  


Function TAdvProfiler.GetFrozen(oObject : TObject): Boolean;
Var
  pProfile : PAdvProfileItem;
Begin 
  pProfile := Find(oObject);

  Result := Assigned(pProfile) And (pProfile^.Frozen);
End;  


Procedure TAdvProfiler.SetFrozen(oObject: TObject; Const Value: Boolean);
Var
  pProfile : PAdvProfileItem;
Begin 
  pProfile := Find(oObject);

  If Not Assigned(pProfile) Then
    Error('SetFrozen', 'Object is invalid or is not registered.');

  pProfile^.Frozen := Value;
End;  


Function TAdvProfiler.FrequencyCountByClassName(Const sClass : String) : Integer;
Var
  oIterator : TAdvProfilerIterator;
  oObject   : TObject;
Begin 
  Result := 0;

  oIterator := TAdvProfilerIterator(Iterator);
  Try
    oIterator.First;

    While oIterator.More Do
    Begin 
      oObject := oIterator.Current;

      If StringEquals(oObject.ClassName, sClass) Then
        Inc(Result);

      oIterator.Next;
    End;  
  Finally
    oIterator.Free;
  End;  
End;  


Procedure TAdvProfiler.CollectFrequencyMatch(oMatch : TAdvStringIntegerMatch);
Var
  oIterator : TAdvProfilerIterator;
  oObject   : TObject;
  iIndex    : Integer;
Begin 
  // Return a frequency table of class name to number of instances.
  // oMatch should be sorted for improved performance.

  oIterator := TAdvProfilerIterator(Iterator);
  Try
    oIterator.First;

    While oIterator.More Do
    Begin
      oObject := oIterator.Current;

      iIndex := oMatch.IndexByKey(oObject.ClassName);

      If Not oMatch.ExistsByIndex(iIndex) Then
        iIndex := oMatch.Add(oObject.ClassName, 0);

      oMatch.ValueByIndex[iIndex] := oMatch.ValueByIndex[iIndex] + 1;

      oIterator.Next;
    End;
  Finally
    oIterator.Free;
  End;
End;



Constructor TAdvProfilerIterator.Create;
Begin
  Inherited;

  FProfiler := Nil;
End;


Destructor TAdvProfilerIterator.Destroy;
Begin
  FProfiler.Free;

  Inherited;
End;  


Procedure TAdvProfilerIterator.First;
Begin 
  FIndex := 0;
  FCurrent := Nil;
  FNext := Nil;

  If FProfiler.Count > 0 Then
    Next;
End;  


Function TAdvProfilerIterator.Count : Integer;
Begin 
  Result := FProfiler.Count;
End;  


Function TAdvProfilerIterator.Current : TObject;
Begin 
  Assert(Condition(Assigned(FCurrent), 'Current', 'Current pointer must be assigned.'));

  Result := FCurrent^.Key;
End;  


Function TAdvProfilerIterator.More : Boolean;
Begin 
  Result := Assigned(FCurrent);
End;  


Procedure TAdvProfilerIterator.Next;
Begin 
  FCurrent := FNext;

  While Not Assigned(FCurrent) And (FIndex < FProfiler.Capacity) Do
  Begin 
    FCurrent := FProfiler.FProfileArray^[FIndex];
    Inc(FIndex);
  End;  

  If Assigned(FCurrent) Then
    FNext := FCurrent^.Next
  Else
    FNext := Nil;
End;  


Procedure TAdvProfilerIterator.SetProfiler(Const Value: TAdvProfiler);
Begin 
  FProfiler.Free;
  FProfiler := Value;
End;


End. // AdvProfilers //
