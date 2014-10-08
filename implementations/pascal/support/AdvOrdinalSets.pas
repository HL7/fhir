Unit AdvOrdinalSets;

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
  AdvObjects, AdvCollections, AdvFilers, AdvIterators;


Type
  TAdvOrdinalSetPart = Integer;
  PAdvOrdinalSetPart = ^TAdvOrdinalSetPart;
  TAdvOrdinalSetPartArray = Array[0..7] Of TAdvOrdinalSetPart;
  PAdvOrdinalSetPartArray = ^TAdvOrdinalSetPartArray;

  TAdvOrdinalSet = Class(TAdvCollection)
    Private
      FOwns : Boolean;
      FPartArray : PAdvOrdinalSetPartArray; // pointer to the block of memory associated with the set.
      FCount : Integer;             // number of used bits in the Parts data.
      FSize : Integer;

      Procedure SetCount(Const iValue : Integer);
      Procedure SetSize(Const Value: Integer);

      Function GetIsChecked(Const iIndex: Integer): Boolean;
      Procedure SetIsChecked(Const iIndex: Integer; Const Value: Boolean);

    Protected
      Procedure PartsNew;
      Procedure PartsDispose;

      Procedure Resize(Const iValue : Integer);

      Procedure Fill(bChecked : Boolean);

      Function ValidateIndex(Const sMethod : String; Const iIndex : Integer) : Boolean;

    Public
      Destructor Destroy; Override;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Function Iterator : TAdvIterator; Override;

      Procedure New(Const iCount : Integer);
      Procedure Hook(Const aValue; iCount : Integer); Virtual; // no overload as we have an untyped parameter.
      Procedure Unhook;

      Procedure CheckRange(iFromIndex, iToIndex : Integer);
      Procedure Check(iIndex : Integer);
      Procedure Uncheck(iIndex : Integer);
      Procedure UncheckRange(iFromIndex, iToIndex : Integer);
      Procedure Toggle(iIndex : Integer);
      Procedure CheckAll;
      Procedure UncheckAll;

      Function Checked(iIndex: Integer): Boolean;
      Function CheckedRange(iFromIndex, iToIndex : Integer): Boolean;      
      Function AnyChecked : Boolean;
      Function AllChecked : Boolean;
      Function NoneChecked : Boolean;

      Property Parts : PAdvOrdinalSetPartArray Read FPartArray Write FPartArray;
      Property Owns : Boolean Read FOwns Write FOwns;
      Property Count : Integer Read FCount Write SetCount;
      Property Size : Integer Read FSize Write SetSize;
      Property IsChecked[Const iIndex : Integer] : Boolean Read GetIsChecked Write SetIsChecked; Default;
  End;

  TAdvOrdinalSetIterator = Class(TAdvIterator)
    Private
      FOrdinalSet : TAdvOrdinalSet;
      FValue : PAdvOrdinalSetPart;
      FPart : Integer;
      FLoop : Integer;
      FIndex : Integer;

      Procedure SetOrdinalSet(Const Value: TAdvOrdinalSet);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure First; Overload; Override;
      Procedure Next; Overload; Override;
      Function More : Boolean; Overload; Override;
      Function Checked : Boolean;

      Procedure Check;
      Procedure Uncheck;

      Property Index : Integer Read FIndex;
      Property OrdinalSet : TAdvOrdinalSet Read FOrdinalSet Write SetOrdinalSet;
  End;


Implementation


Uses
  MemorySupport, MathSupport, StringSupport;


Destructor TAdvOrdinalSet.Destroy;
Begin
  PartsDispose;

  Inherited;
End;


Procedure TAdvOrdinalSet.PartsDispose;
Begin
  If FOwns Then
    MemoryDestroy(FPartArray, FSize);

  FOwns := False;
  FPartArray := Nil;
  FSize := 0;
  FCount := 0;
End;


Procedure TAdvOrdinalSet.PartsNew;
Begin
  If Not FOwns Then
  Begin
    FPartArray := Nil;
    MemoryCreate(FPartArray, FSize);
    MemoryZero(FPartArray, FSize);
    FOwns := True;
  End;
End;


Procedure TAdvOrdinalSet.SetCount(Const iValue: Integer);
Begin
  FCount := iValue;
  Resize(RealCeiling(Count / 8));
End;


Procedure TAdvOrdinalSet.SetSize(Const Value: Integer);
Begin
  Resize(Value);
  FCount := (FSize * 8);
End;  


Procedure TAdvOrdinalSet.Assign(oObject: TAdvObject);
Begin
  Inherited;

  PartsDispose;

  FSize := TAdvOrdinalSet(oObject).Size;
  FCount := TAdvOrdinalSet(oObject).Count;

  PartsNew;

  MemoryMove(TAdvOrdinalSet(oObject).FPartArray, FPartArray, FSize);
End;


Procedure TAdvOrdinalSet.Define(oFiler: TAdvFiler);
Begin
  Inherited;
End;


Function TAdvOrdinalSet.ValidateIndex(Const sMethod : String; Const iIndex : Integer) : Boolean;
Begin
  If Not IntegerBetweenInclusive(0, iIndex, FCount - 1) Then
    Invariant(sMethod, StringFormat('Invalid index (%d In [0..%d])', [iIndex, FCount - 1]));

  Result := True;
End;


Procedure TAdvOrdinalSet.Fill(bChecked : Boolean);
Begin
  If bChecked Then
    MemoryFill(FPartArray, FSize)
  Else
    MemoryZero(FPartArray, FSize);
End;


Procedure TAdvOrdinalSet.Resize(Const iValue: Integer);
Begin
  If FOwns Then
  Begin
    MemoryResize(FPartArray, FSize, iValue);
    MemoryZero(Pointer(NativeUInt(FPartArray) + NativeUInt(FSize)), iValue - FSize);
  End;

  FSize := iValue;
End;


Function TAdvOrdinalSet.Iterator : TAdvIterator;
Begin
  Result := TAdvOrdinalSetIterator.Create;
  TAdvOrdinalSetIterator(Result).OrdinalSet := TAdvOrdinalSet(Self.Link);
End;


Procedure TAdvOrdinalSet.New(Const iCount: Integer);
Begin
  PartsDispose;

  FCount := iCount;
  FSize := RealCeiling(iCount / 8);

  PartsNew;
End;


Procedure TAdvOrdinalSet.Hook(Const aValue; iCount : Integer);
Begin
  FPartArray := PAdvOrdinalSetPartArray(@aValue);
  FCount := iCount;
  FSize := RealCeiling(iCount / 8);
  FOwns := False;
End;


Procedure TAdvOrdinalSet.Unhook;
Begin
  FPartArray := Nil;
  FCount := 0;
  FSize := 0;
End;


Procedure TAdvOrdinalSet.Check(iIndex: Integer);
Var
  pPart : PAdvOrdinalSetPart;
Begin
  Assert(ValidateIndex('Check', iIndex));

  pPart := @FPartArray^[iIndex Div 32];

  pPart^ := pPart^ Or (1 Shl SignedMod(iIndex, 32));
End;


Procedure TAdvOrdinalSet.CheckRange(iFromIndex, iToIndex : Integer);
Var
  iLoop : Integer;
Begin
  For iLoop := iFromIndex To iToIndex Do
    Check(iLoop);
End;


Procedure TAdvOrdinalSet.UncheckRange(iFromIndex, iToIndex : Integer);
Var
  iLoop : Integer;
Begin
  For iLoop := iFromIndex To iToIndex Do
    Uncheck(iLoop);
End;


Procedure TAdvOrdinalSet.Uncheck(iIndex: Integer);
Var
  pPart : PAdvOrdinalSetPart;
Begin
  Assert(ValidateIndex('Uncheck', iIndex));

  pPart := @FPartArray^[iIndex Div 32];

  pPart^ := pPart^ And Not (1 Shl SignedMod(iIndex, 32));
End;


Procedure TAdvOrdinalSet.Toggle(iIndex: Integer);
Var
  pPart : PAdvOrdinalSetPart;
  iFlag : Integer;
Begin
  Assert(ValidateIndex('Toggle', iIndex));

  pPart := @FPartArray^[iIndex Div 32];

  iFlag := (1 Shl SignedMod(iIndex, 32));

  If (pPart^ And iFlag = 0) Then
    pPart^ := pPart^ Or iFlag
  Else
    pPart^ := pPart^ And Not iFlag;
End;


Function TAdvOrdinalSet.Checked(iIndex: Integer): Boolean;
Begin
  Assert(ValidateIndex('Checked', iIndex));

  Result := FPartArray^[iIndex Div 32] And (1 Shl SignedMod(iIndex, 32)) <> 0;
End;


Function TAdvOrdinalSet.CheckedRange(iFromIndex, iToIndex: Integer): Boolean;
Var
  iIndex : Integer;
Begin
  Assert(Condition(iFromIndex <= iToIndex, 'CheckedRange', 'From Index and To Index are not valid.'));

  iIndex := iFromIndex;
  Result := True;
  While Result And (iIndex <= iToIndex) Do
  Begin
    Result := Checked(iIndex);
    Inc(iIndex);
  End;
End;


Function TAdvOrdinalSet.AllChecked : Boolean;
Var
  iLoop : Integer;
Begin
  // TODO: optimisation possible?

  Result := True;
  iLoop := 0;
  While (iLoop < Count) And Result Do
  Begin
    Result := Checked(iLoop);
    Inc(iLoop);
  End;
End;


Function TAdvOrdinalSet.AnyChecked : Boolean;
Var
  iLoop : Integer;
Begin
  // TODO: optimisation possible?

  Result := False;
  iLoop := 0;
  While (iLoop < Count) And Not Result Do
  Begin
    Result := Checked(iLoop);
    Inc(iLoop);
  End;
End;


Function TAdvOrdinalSet.NoneChecked : Boolean;
Var
  iLoop : Integer;
Begin
  // TODO: optimisation possible?

  Result := True;
  iLoop := 0;
  While (iLoop < Count) And Result Do
  Begin
    Result := Not Checked(iLoop);
    Inc(iLoop);
  End;
End;


Procedure TAdvOrdinalSetIterator.First;
Begin
  FPart := Low(FOrdinalSet.FPartArray^);
  FIndex := 0;
  FLoop := 0;

  // TODO: can FLoop be cached as (1 Shl FLoop) for optimisation?

  If FPart <= High(FOrdinalSet.FPartArray^) Then
    FValue := @(FOrdinalSet.FPartArray^[FPart]);
End;


Function TAdvOrdinalSetIterator.Checked : Boolean;
Begin
  Result := (FValue^ And (1 Shl FLoop)) <> 0;
End;  


Procedure TAdvOrdinalSetIterator.Check;
Begin 
  FValue^ := FValue^ Or (1 Shl FLoop);
End;  


Procedure TAdvOrdinalSetIterator.Uncheck;
Begin 
  FValue^ := FValue^ And Not (1 Shl FLoop);
End;  


Procedure TAdvOrdinalSetIterator.Next;
Begin
  Inc(FLoop);
  Inc(FIndex);

  If FLoop >= 32 Then
  Begin
    Inc(FPart);

    If FPart <= High(FOrdinalSet.FPartArray^) Then
      FValue := @FOrdinalSet.FPartArray^[FPart];

    FLoop := 0;
  End;  
End;  


Function TAdvOrdinalSetIterator.More : Boolean;
Begin 
  Result := (FIndex < FOrdinalSet.Count) And (FPart <= High(FOrdinalSet.FPartArray^));
End;  


Constructor TAdvOrdinalSetIterator.Create;
Begin
  Inherited;

  OrdinalSet := Nil;
End;  


Destructor TAdvOrdinalSetIterator.Destroy;
Begin 
  FOrdinalSet.Free;

  Inherited;
End;  


Procedure TAdvOrdinalSetIterator.SetOrdinalSet(Const Value: TAdvOrdinalSet);
Begin 
  FOrdinalSet.Free;
  FOrdinalSet := Value;
End;  


Function TAdvOrdinalSet.GetIsChecked(Const iIndex: Integer): Boolean;
Begin
  Result := Checked(iIndex);
End;


Procedure TAdvOrdinalSet.SetIsChecked(Const iIndex: Integer; Const Value: Boolean);
Begin
  If Value Then
    Check(iIndex)
  Else
    Uncheck(iIndex);
End;


Procedure TAdvOrdinalSet.CheckAll;
Begin
  Fill(True);
End;


Procedure TAdvOrdinalSet.UncheckAll;
Begin
  Fill(False);
End;


End. // AdvOrdinalSets //
