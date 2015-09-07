Unit AdvNameBuffers;

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
  StringSupport,
  AdvObjects,
  AdvFactories, AdvBuffers;


Type
  TAdvNameBuffer = Class(TAdvBuffer)
    Private
      FName : String;

    Public
      Function Link : TAdvNameBuffer;
      Function Clone : TAdvNameBuffer;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Property Name : String Read FName Write FName;
  End;

  TAdvNameBufferList = Class(TAdvBufferList)
    Private
      Function GetBuffer(iIndex : Integer) : TAdvNameBuffer;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Procedure DefaultCompare(Out aEvent : TAdvItemsCompare); Override;

      Function CompareByName(pA, pB : Pointer) : Integer; Virtual;

    Public
      Function Link : TAdvNameBufferList;
      Function Clone : TAdvNameBufferList; 

      Function GetByName(Const sName : String) : TAdvNameBuffer;
      Function IndexByName(Const sName : String) : Integer;
      Function ExistsByName(Const sName : String) : Boolean;
      Procedure Merge(oBuffers : TAdvNameBufferList);

      Property Buffer[iIndex : Integer] : TAdvNameBuffer Read GetBuffer; Default;
  End;


Implementation


Procedure TAdvNameBuffer.Assign(oObject : TAdvObject);
Begin
  Inherited;

  FName := TAdvNameBuffer(oObject).FName;
End;


Procedure TAdvNameBuffer.Define(oFiler : TAdvFiler);
Begin
  Inherited;

  oFiler['Name'].DefineString(FName);
End;


Function TAdvNameBuffer.Link : TAdvNameBuffer;
Begin
  Result := TAdvNameBuffer(Inherited Link);
End;


Function TAdvNameBuffer.Clone : TAdvNameBuffer;
Begin
  Result := TAdvNameBuffer(Inherited Clone);
End;


Function TAdvNameBufferList.Clone : TAdvNameBufferList;
Begin
  Result := TAdvNameBufferList(Inherited Clone);
End;


Function TAdvNameBufferList.Link : TAdvNameBufferList;
Begin
  Result := TAdvNameBufferList(Inherited Link);
End;


Function TAdvNameBufferList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvNameBuffer;
End;


Procedure TAdvNameBufferList.Merge(oBuffers : TAdvNameBufferList);
Var
  iLoop : Integer;
Begin
  For iLoop := 0 To oBuffers.Count - 1 Do
    Add(oBuffers[iLoop].Clone);
End;


Function TAdvNameBufferList.GetBuffer(iIndex : Integer) : TAdvNameBuffer;
Begin
  Result := TAdvNameBuffer(ObjectByIndex[iIndex]);
End;


Function TAdvNameBufferList.GetByName(Const sName : String) : TAdvNameBuffer;
Begin
  Result := TAdvNameBuffer(Get(IndexByName(sName)));
End;


Function TAdvNameBufferList.ExistsByName(Const sName : String) : Boolean;
Begin
  Result := ExistsByIndex(IndexByName(sName));
End;


Function TAdvNameBufferList.CompareByName(pA, pB: Pointer): Integer;
Begin
  Result := StringCompare(TAdvNameBuffer(pA).Name, TAdvNameBuffer(pB).Name);
End;


Function TAdvNameBufferList.IndexByName(Const sName: String): Integer;
Var
  oBuffer : TAdvNameBuffer;
Begin
  oBuffer := TAdvNameBuffer(ItemNew);
  Try
    oBuffer.Name := sName;

    If Not Find(oBuffer, Result, CompareByName) Then
      Result := -1;
  Finally
    oBuffer.Free;
  End;
End;


Procedure TAdvNameBufferList.DefaultCompare(Out aEvent: TAdvItemsCompare);
Begin
  aEvent := CompareByName;
End;


Initialization
  Factory.RegisterClassArray([TAdvNameBuffer, TAdvNameBufferList]);
End. // AdvNameBuffers //
