Unit AdvClassHashes;

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
  AdvObjects, AdvHashes, AdvStringHashes, AdvIterators;


Type
  TAdvObjectClassHashEntry = Class(TAdvStringHashEntry)
    Private
      FData : TClass;

    Public
      Procedure Assign(oSource : TAdvObject); Override;

      Property Data : TClass Read FData Write FData; // no set data as hashed classname may be different to FData.ClassName.
  End; 

  TAdvObjectClassHashTable = Class(TAdvStringHashTable)
    Protected
      Function ItemClass : TAdvHashEntryClass; Override;

    Public
      Function Iterator : TAdvIterator; Override;
  End; 

  TAdvObjectClassHashTableIterator = Class(TAdvObjectClassIterator)
    Private
      FInternal : TAdvStringHashTableIterator;

      Function GetHashTable: TAdvObjectClassHashTable;
      Procedure SetHashTable(Const Value: TAdvObjectClassHashTable);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure First; Override;
      Procedure Last; Override;
      Procedure Next; Override;
      Procedure Back; Override;

      Function More : Boolean; Override;
      Function Current : TClass; Override;

      Property HashTable : TAdvObjectClassHashTable Read GetHashTable Write SetHashTable;
  End; 


Implementation


Procedure TAdvObjectClassHashEntry.Assign(oSource: TAdvObject);
Begin 
  Inherited;

  FData := TAdvObjectClassHashEntry(oSource).Data;
End;  


Constructor TAdvObjectClassHashTableIterator.Create;
Begin 
  Inherited;

  FInternal := TAdvStringHashTableIterator.Create;
End;  


Destructor TAdvObjectClassHashTableIterator.Destroy;
Begin 
  FInternal.Free;

  Inherited;
End;  


Function TAdvObjectClassHashTable.ItemClass : TAdvHashEntryClass;
Begin 
  Result := TAdvObjectClassHashEntry;
End;  


Function TAdvObjectClassHashTable.Iterator : TAdvIterator;
Begin 
  Result := TAdvObjectClassHashTableIterator.Create;
  TAdvObjectClassHashTableIterator(Result).HashTable := TAdvObjectClassHashTable(Self.Link);
End;  


Function TAdvObjectClassHashTableIterator.Current : TClass;
Begin 
  Result := TAdvObjectClassHashEntry(FInternal.Current).Data;
End;  


Procedure TAdvObjectClassHashTableIterator.First;
Begin 
  Inherited;

  FInternal.First;
End;  


Procedure TAdvObjectClassHashTableIterator.Last;
Begin 
  Inherited;

  FInternal.Last;
End;  


Procedure TAdvObjectClassHashTableIterator.Next;
Begin 
  Inherited;

  FInternal.Next;
End;  


Procedure TAdvObjectClassHashTableIterator.Back;
Begin 
  Inherited;

  FInternal.Back;
End;  


Function TAdvObjectClassHashTableIterator.More : Boolean;
Begin 
  Result := FInternal.More;
End;  


Function TAdvObjectClassHashTableIterator.GetHashTable : TAdvObjectClassHashTable;
Begin 
  Result := TAdvObjectClassHashTable(FInternal.HashTable);
End;  


Procedure TAdvObjectClassHashTableIterator.SetHashTable(Const Value: TAdvObjectClassHashTable);
Begin 
  FInternal.HashTable := Value;
End;


End. // AdvClassHashes //
