Unit AdvIntegerMerges;

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
  MathSupport,
  AdvMerges;


Type
  TAdvIntegerMergableItem = Int64;

  TAdvIntegerMergable = Class(TAdvMergable)
    Private
      FFirst : TAdvIntegerMergableItem;
      FLast : TAdvIntegerMergableItem;

    Public
      Function Link : TAdvIntegerMergable;
      Function Clone : TAdvIntegerMergable;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Function EqualTo(oMergable : TAdvMergable) : Boolean; Override;
      Function Distance(oMergable : TAdvMergable) : Integer; Override;

      Procedure Union(oMergable : TAdvMergable); Override;
      Procedure Subtract(oMergable : TAdvMergable); Override;
      Procedure Intersect(oMergable : TAdvMergable); Override;
      Procedure Split(oMergable, oLeft, oRight : TAdvMergable); Override;

      Function ContainsValue(Const iItem : TAdvIntegerMergableItem) : Boolean;
      Function Contains(oMergable : TAdvMergable) : Boolean; Override;

      Function Empty : Boolean; Override;
      Function Length : Integer; 

      Property First : TAdvIntegerMergableItem Read FFirst Write FFirst;
      Property Last : TAdvIntegerMergableItem Read FLast Write FLast;
  End;

  TAdvIntegerMerge = Class(TAdvMerge)
    Private
      Function GetMergable(iIndex: Integer): TAdvIntegerMergable;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Function Link : TAdvIntegerMerge; Overload;
      Function Clone : TAdvIntegerMerge; Overload;

      Function AddValue(Const iValue : TAdvIntegerMergableItem) : Integer; 
      Function AddRange(Const iFirst, iLast : TAdvIntegerMergableItem) : Integer;
      Procedure RemoveRange(Const iFirst, iLast : TAdvIntegerMergableItem);
      Procedure RemoveValue(Const iValue : TAdvIntegerMergableItem);

      Function ExistsByValue(Const iValue : TAdvIntegerMergableItem) : Boolean; 
      Function GetByValue(Const iValue : TAdvIntegerMergableItem) : TAdvIntegerMergable; 
      Function IndexByValue(Const iValue : TAdvIntegerMergableItem) : Integer;

      Property MergableByIndex[iIndex : Integer] : TAdvIntegerMergable Read GetMergable; Default;
  End;

  TAdvObjectClass = AdvMerges.TAdvObjectClass;
  TAdvObject = AdvMerges.TAdvObject;
  TAdvFiler = AdvMerges.TAdvFiler;


Implementation


Function TAdvIntegerMergable.Clone: TAdvIntegerMergable;
Begin
  Result := TAdvIntegerMergable(Inherited Clone);
End;


Function TAdvIntegerMergable.Link: TAdvIntegerMergable;
Begin
  Result := TAdvIntegerMergable(Inherited Link);
End;


Procedure TAdvIntegerMergable.Assign(oObject: TAdvObject);
Begin
  Inherited;

  FFirst := TAdvIntegerMergable(oObject).First;
  FLast := TAdvIntegerMergable(oObject).Last;
End;


Procedure TAdvIntegerMergable.Define(oFiler: TAdvFiler);
Begin
  Inherited;

  oFiler['First'].DefineInteger(FFirst);
  oFiler['Last'].DefineInteger(FLast);
End;


Function TAdvIntegerMergable.Empty : Boolean;
Begin
  Result := FFirst > FLast;
End;


Function TAdvIntegerMergable.ContainsValue(Const iItem : TAdvIntegerMergableItem) : Boolean;
Begin
  Result := (iItem >= FFirst) And (iItem <= FLast);
End;


Function TAdvIntegerMergable.Contains(oMergable: TAdvMergable): Boolean;
Begin
  Result := (TAdvIntegerMergable(oMergable).First >= FFirst) And (TAdvIntegerMergable(oMergable).Last <= FLast);
End;


Function TAdvIntegerMergable.Length: Integer;
Begin
  Result := FLast - FFirst + 1;
End;


Function TAdvIntegerMergable.EqualTo(oMergable: TAdvMergable): Boolean;
Begin
  Result := (FFirst = TAdvIntegerMergable(oMergable).First) And (FLast = TAdvIntegerMergable(oMergable).Last);
End;


Function TAdvIntegerMergable.Distance(oMergable: TAdvMergable): Integer;
Begin
  If FFirst > TAdvIntegerMergable(oMergable).Last Then
    Result := FFirst - TAdvIntegerMergable(oMergable).Last
  Else If FLast < TAdvIntegerMergable(oMergable).First Then
    Result := FLast - TAdvIntegerMergable(oMergable).First
  Else
    Result := 0;
End;


Procedure TAdvIntegerMergable.Union(oMergable: TAdvMergable);
Begin
  Inherited;

  If TAdvIntegerMergable(oMergable).First < FFirst Then
    FFirst := TAdvIntegerMergable(oMergable).First;

  If TAdvIntegerMergable(oMergable).Last > FLast Then
    FLast := TAdvIntegerMergable(oMergable).Last;
End;


Procedure TAdvIntegerMergable.Subtract(oMergable: TAdvMergable);
Begin
  Inherited;

  If TAdvIntegerMergable(oMergable).First < FLast Then
    FLast := First;

  If TAdvIntegerMergable(oMergable).Last > FFirst Then
    FFirst := TAdvIntegerMergable(oMergable).Last;
End;


Procedure TAdvIntegerMergable.Intersect(oMergable: TAdvMergable);
Begin
  Inherited;

  If TAdvIntegerMergable(oMergable).First > FFirst Then
    FFirst := TAdvIntegerMergable(oMergable).First;

  If TAdvIntegerMergable(oMergable).Last < FLast Then
    FLast := TAdvIntegerMergable(oMergable).Last;
End;


Procedure TAdvIntegerMergable.Split(oMergable, oLeft, oRight : TAdvMergable);
Begin
  Inherited;

  TAdvIntegerMergable(oLeft).First := FFirst;
  TAdvIntegerMergable(oRight).Last := FLast;

  TAdvIntegerMergable(oLeft).Last := TAdvIntegerMergable(oMergable).First - 1;
  TAdvIntegerMergable(oRight).First := TAdvIntegerMergable(oMergable).Last + 1;
End;


Function TAdvIntegerMerge.AddValue(Const iValue: TAdvIntegerMergableItem): Integer;
Begin
  Result := AddRange(iValue, iValue);
End;


Function TAdvIntegerMerge.AddRange(Const iFirst, iLast: TAdvIntegerMergableItem): Integer;
Var
  oMergable : TAdvIntegerMergable;
Begin
  oMergable := TAdvIntegerMergable(ItemNew);
  Try
    oMergable.First := iFirst;
    oMergable.Last := iLast;

    Result := Union(oMergable);
  Finally
    oMergable.Free;
  End;
End;


Procedure TAdvIntegerMerge.RemoveRange(Const iFirst, iLast: TAdvIntegerMergableItem);
Var
  oMergable : TAdvIntegerMergable;
Begin
  oMergable := TAdvIntegerMergable(ItemNew);
  Try
    oMergable.First := iFirst;
    oMergable.Last := iLast;

    Subtract(oMergable);
  Finally
    oMergable.Free;
  End;
End;


Procedure TAdvIntegerMerge.RemoveValue(Const iValue : TAdvIntegerMergableItem);
Begin
  RemoveRange(iValue, iValue);
End;


Function TAdvIntegerMerge.GetMergable(iIndex : Integer): TAdvIntegerMergable;
Begin
  Result := TAdvIntegerMergable(ObjectByIndex[iIndex]);
End;


Function TAdvIntegerMerge.ItemClass: TAdvObjectClass;
Begin
  Result := TAdvIntegerMergable;
End;


Function TAdvIntegerMerge.Clone: TAdvIntegerMerge;
Begin
  Result := TAdvIntegerMerge(Inherited Clone);
End;


Function TAdvIntegerMerge.Link: TAdvIntegerMerge;
Begin
  Result := TAdvIntegerMerge(Inherited Link);
End;


Function TAdvIntegerMerge.GetByValue(Const iValue : TAdvIntegerMergableItem) : TAdvIntegerMergable;
Var
  iIndex : Integer;
Begin
  iIndex := IndexByValue(iValue);

  If ExistsByIndex(iIndex) Then
    Result := MergableByIndex[iIndex]
  Else
    Result := Nil;
End;


Function TAdvIntegerMerge.IndexByValue(Const iValue : TAdvIntegerMergableItem) : Integer;
Begin
  Result := 0;
  While (Result < Count) And Not MergableByIndex[Result].ContainsValue(iValue) Do
    Inc(Result);
End;


Function TAdvIntegerMerge.ExistsByValue(Const iValue : TAdvIntegerMergableItem) : Boolean;
Begin
  Result := ExistsByIndex(IndexByValue(iValue));
End;


End.
