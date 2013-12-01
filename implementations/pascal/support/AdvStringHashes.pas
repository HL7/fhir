Unit AdvStringHashes;

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
  StringSupport, HashSupport,
  AdvObjects, AdvHashes, AdvFilers, AdvIterators;


Type
  TAdvStringHashEntry = Class(TAdvHashEntry)
    Private
      FName : String;

      Procedure SetName(Const Value: String); 

    Protected
      Procedure Generate; Override;

    Public
      Procedure Assign(oSource : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Property Name : String Read FName Write SetName;
  End;

  TAdvStringHashEntryClass = Class Of TAdvStringHashEntry;

  TAdvStringHashTable = Class(TAdvHashTable)
    Protected
      Function Equal(oA, oB : TAdvHashEntry) : Integer; Override;

      Function ItemClass : TAdvHashEntryClass; Override;

    Public
      Function Link : TAdvStringHashTable;

      Function Iterator : TAdvIterator; Override;
  End; 

  TAdvStringHashTableIterator = Class(TAdvHashTableIterator)
    Public
      Function Current : TAdvStringHashEntry; Reintroduce;
  End; 

  TAdvHashEntryClass = AdvHashes.TAdvHashEntryClass;
  TAdvHashEntry = AdvHashes.TAdvHashEntry;

  TAdvIterator = AdvHashes.TAdvIterator;


Implementation


Procedure TAdvStringHashEntry.Generate;
Begin 
  Code := HashSupport.HashStringToCode32(FName);
End;


Procedure TAdvStringHashEntry.SetName(Const Value: String);
Begin 
  FName := Value;

  Generate;
End;  


Procedure TAdvStringHashEntry.Assign(oSource: TAdvObject);
Begin 
  Inherited;

  Name := TAdvStringHashEntry(oSource).Name;
End;  


Procedure TAdvStringHashEntry.Define(oFiler : TAdvFiler);
Begin 
  Inherited;

  oFiler['Name'].DefineString(FName);
End;  


Function TAdvStringHashTable.Equal(oA, oB: TAdvHashEntry): Integer;
Begin 
  Result := Inherited Equal(oA, oB);

  If Result = 0 Then
    Result := StringCompare(TAdvStringHashEntry(oA).Name, TAdvStringHashEntry(oB).Name);
End;  


Function TAdvStringHashTable.ItemClass : TAdvHashEntryClass;
Begin 
  Result := TAdvStringHashEntry;
End;  


Function TAdvStringHashTable.Iterator : TAdvIterator;
Begin 
  Result := TAdvStringHashTableIterator.Create;
  TAdvStringHashTableIterator(Result).HashTable := Self.Link;
End;  


Function TAdvStringHashTableIterator.Current : TAdvStringHashEntry;
Begin 
  Result := TAdvStringHashEntry(Inherited Current);
End;


Function TAdvStringHashTable.Link: TAdvStringHashTable;
Begin
  Result := TAdvStringHashTable(Inherited Link);
End;


End. // AdvStringHashes //
