Unit AdvZipParts;

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
  AdvNameBuffers,
  AdvFactories, AdvBuffers;


Type
  TAdvZipPart = Class(TAdvNameBuffer)
    Private
      FTimestamp: TDateTime;
      FComment : String;

    Public
      Function Link : TAdvZipPart;
      Function Clone : TAdvZipPart;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Property Timestamp : TDateTime Read FTimestamp Write FTimestamp;
      Property Comment : String Read FComment Write FComment;
  End;

  TAdvZipPartList = Class(TAdvNameBufferList)
    Private
      Function GetPart(iIndex : Integer) : TAdvZipPart;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Function Link : TAdvZipPartList;
      Function Clone : TAdvZipPartList;

      Function GetByName(Const sName : String) : TAdvZipPart;

      Property Part[iIndex : Integer] : TAdvZipPart Read GetPart; Default;
  End;


Implementation


Procedure TAdvZipPart.Assign(oObject : TAdvObject);
Begin
  Inherited;
  FTimestamp := TAdvZipPart(oObject).FTimestamp;
  FComment := TAdvZipPart(oObject).FComment;
End;


Procedure TAdvZipPart.Define(oFiler : TAdvFiler);
Begin
  Inherited;
  oFiler['Timestamp'].DefineDateTime(FTimestamp);
  oFiler['Comment'].DefineString(FComment);
End;


Function TAdvZipPart.Link : TAdvZipPart;
Begin
  Result := TAdvZipPart(Inherited Link);
End;


Function TAdvZipPart.Clone : TAdvZipPart;
Begin
  Result := TAdvZipPart(Inherited Clone);
End;


Function TAdvZipPartList.Clone : TAdvZipPartList;
Begin
  Result := TAdvZipPartList(Inherited Clone);
End;


Function TAdvZipPartList.Link : TAdvZipPartList;
Begin
  Result := TAdvZipPartList(Inherited Link);
End;


Function TAdvZipPartList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvZipPart;
End;



Function TAdvZipPartList.GetPart(iIndex : Integer) : TAdvZipPart;
Begin
  Result := TAdvZipPart(ObjectByIndex[iIndex]);
End;


Function TAdvZipPartList.GetByName(Const sName: String): TAdvZipPart;
Begin
  Result := TAdvZipPart(Inherited GetByName(sName));
End;

Initialization
  Factory.RegisterClassArray([TAdvZipPart, TAdvZipPartList]);
End. // AdvZipParts //

