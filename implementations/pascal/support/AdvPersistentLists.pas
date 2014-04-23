Unit AdvPersistentLists;

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
  AdvObjectLists,
  AdvPersistents;


Type
  TAdvPersistentList = Class(TAdvObjectList)
    Private
      Function GetPersistentByIndex(Const iIndex : Integer) : TAdvPersistent;
      Procedure SetPersistentByIndex(Const iIndex : Integer; Const oValue : TAdvPersistent);

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property PersistentByIndex[Const iIndex : Integer] : TAdvPersistent Read GetPersistentByIndex Write SetPersistentByIndex; Default;
  End;

  TAdvPersistentListIterator = Class(TAdvObjectListIterator)
  End;

  TAdvPersistentListClass = Class Of TAdvPersistentList;

  TAdvObject = AdvPersistents.TAdvObject;
  TAdvObjectClass = AdvPersistents.TAdvObjectClass;
  TAdvFiler = AdvPersistents.TAdvFiler;
  TAdvPersistent = AdvPersistents.TAdvPersistent;
  TAdvItemsCompare = AdvObjectLists.TAdvItemsCompare;
  TAdvIterator = AdvObjectLists.TAdvIterator;


Implementation


Function TAdvPersistentList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvPersistent;
End;


Function TAdvPersistentList.GetPersistentByIndex(Const iIndex : Integer) : TAdvPersistent;
Begin
  Result := TAdvPersistent(ObjectByIndex[iIndex]);
End;


Procedure TAdvPersistentList.SetPersistentByIndex(Const iIndex : Integer; Const oValue : TAdvPersistent);
Begin
  ObjectByIndex[iIndex] := oValue;
End;


End.
