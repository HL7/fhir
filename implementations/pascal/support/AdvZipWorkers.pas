Unit AdvZipWorkers;

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
  AdvStreams,
  AdvZipParts,
  AdvObjects;

Type
  TAdvZipWorker = Class (TAdvObject)
    Private
      FStream : TAdvStream;
      FParts : TAdvZipPartList;
      Function GetStream : TAdvStream;
      Function GetParts : TAdvZipPartList;
      Procedure SetStream(oValue : TAdvStream);
      Procedure SetParts(oValue : TAdvZipPartList);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function HasStream : Boolean;
      Function HasParts : Boolean;

      Property Stream : TAdvStream Read GetStream Write SetStream;
      Property Parts : TAdvZipPartList Read GetParts Write SetParts;
  End;

Implementation


Constructor TAdvZipWorker.Create;
Begin
  Inherited;
  FParts := TAdvZipPartList.Create;
End;

Destructor TAdvZipWorker.Destroy;
Begin
  FStream.Free;
  FParts.Free;
  Inherited;
End;

Function TAdvZipWorker.GetParts: TAdvZipPartList;
Begin
  Assert(Invariants('GetParts', FParts, TAdvZipPartList, 'Parts'));
  Result := FParts;
End;

Function TAdvZipWorker.GetStream: TAdvStream;
Begin
  Assert(Invariants('GetStream', FStream, TAdvStream, 'Stream'));
  Result := FStream;
End;

Function TAdvZipWorker.HasParts: Boolean;
Begin
  Result := FParts <> Nil;
End;

Function TAdvZipWorker.HasStream: Boolean;
Begin
  Result := FStream <> Nil;
End;

Procedure TAdvZipWorker.SetParts(oValue: TAdvZipPartList);
Begin
  FParts.Free;
  FParts := oValue;
End;

Procedure TAdvZipWorker.SetStream(oValue: TAdvStream);
Begin
  FStream.Free;
  FStream := oValue;
End;

End.

