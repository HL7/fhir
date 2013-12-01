Unit AdvControllers;

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
  AdvPersistents, AdvPersistentLists;


Type
  TAdvController = Class(TAdvPersistent)
    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Function Link : TAdvController;

      Procedure Open; Virtual;
      Procedure Close; Virtual;
  End;

  EAdvController = Class(EAdvException);

  TAdvControllerList = Class(TAdvPersistentList)
    Private
      Function GetControllerByIndex(Const iIndex : Integer) : TAdvController;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property ControllerByIndex[Const iIndex : Integer] : TAdvController Read GetControllerByIndex; Default;
  End;

  TAdvObjectClass = AdvPersistents.TAdvObjectClass;
  TAdvObject = AdvPersistents.TAdvObject;
  TAdvFiler = AdvPersistents.TAdvFiler;


Implementation


Function TAdvController.Link: TAdvController;
Begin
  Result := TAdvController(Inherited Link);
End;


Function TAdvController.ErrorClass: EAdvExceptionClass;
Begin
  Result := EAdvController;
End;


Procedure TAdvController.Open;
Begin
End;


Procedure TAdvController.Close;
Begin
End;


Function TAdvControllerList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvController;
End;


Function TAdvControllerList.GetControllerByIndex(Const iIndex : Integer) : TAdvController;
Begin
  Result := TAdvController(ObjectByIndex[iIndex]);
End;


End.
