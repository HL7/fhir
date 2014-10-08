Unit AdvStringLists;

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
  SysUtils, MemorySupport, StringSupport, MathSupport,
  AdvItems, AdvFilers, AdvIterators;


Type
  TAdvStringListItem = String;
  TAdvStringListItemArray = Array[0..(MaxInt Div SizeOf(TAdvStringListItem)) - 1] Of TAdvStringListItem;
  PAdvStringListItemArray = ^TAdvStringListItemArray;

  TAdvStringList = Class(TAdvItems)
    Private
      FStringArray : PAdvStringListItemArray;
      FSymbol : String;

      Function GetStringByIndex(iIndex : Integer) : TAdvStringListItem;
      Procedure SetStringByIndex(iIndex : Integer; Const sValue : TAdvStringListItem);

      Function GetSensitive : Boolean;
      Procedure SetSensitive(Const bValue: Boolean);

      Function GetAsCSV : String;
      Procedure SetAsCSV(Const sValue : String);

    Protected
      Function GetItem(iIndex : Integer) : Pointer; Override;
      Procedure SetItem(iIndex : Integer; pValue : Pointer); Override;

      Function GetAsText : String; Virtual;
      Procedure SetAsText(Const sValue : String); Virtual;

      Procedure LoadItem(Filer : TAdvFiler; iIndex : Integer); Override;
      Procedure SaveItem(Filer : TAdvFiler; iIndex : Integer); Override;
      Procedure AssignItem(Items : TAdvItems; iIndex : Integer); Override;

      Procedure InternalTruncate(iValue : Integer); Override;
      Procedure InternalResize(iValue : Integer); Override;
      Procedure InternalCopy(iSource, iTarget, iCount : Integer); Override;
      Procedure InternalEmpty(iIndex, iLength : Integer); Override;
      Procedure InternalInsert(iIndex : Integer); Overload; Override;
      Procedure InternalDelete(iIndex : Integer); Overload; Override;
      Procedure InternalExchange(iA, iB : Integer); Overload; Override;

      Procedure DefaultCompare(Out aCompare : TAdvItemsCompare); Overload; Override;

      Function CompareInsensitive(pA, pB : Pointer): Integer;
      Function CompareSensitive(pA, pB : Pointer): Integer;

      Function CapacityLimit : Integer; Override;

    Public
      Constructor Create; Override;

      Function Link : TAdvStringList;
      Function Clone : TAdvStringList;

      Procedure SaveToText(Const sFilename : String);
      Procedure LoadFromText(Const sFilename : String);

      Function Iterator : TAdvIterator; Override;

      Procedure AddAll(oStrings : TAdvStringList);
      Procedure AddAllStringArray(Const aValues : Array Of String);

      Function IndexByValue(Const sValue : TAdvStringListItem) : Integer;
      Function ExistsByValue(Const sValue : TAdvStringListItem) : Boolean;
      Function Add(Const sValue : TAdvStringListItem) : Integer;
      Procedure Insert(iIndex : Integer; Const sValue : TAdvStringListItem);
      Procedure DeleteByValue(Const sValue : TAdvStringListItem); 

      Function Equals(oStrings : TAdvStringList) : Boolean; Reintroduce;
      Function Compare(oStrings : TAdvStringList) : Integer;
      Function ExistsAny(oStrings : TAdvStringList) : Boolean;

      Function Find(Const sValue : TAdvStringListItem; Out iIndex : Integer; aCompare : TAdvItemsCompare = Nil) : Boolean; Overload;

      // script wrappers
      procedure SetItems(iIndex : integer; sValue : String);
      function GetItems(iIndex : integer) : String;
      procedure delete(iIndex : integer);
      procedure populate(iCount : integer);
      Function IndexOf(value : String): Integer;
      Property Items[iIndex : Integer] : TAdvStringListItem Read GetStringByIndex Write SetStringByIndex;
      Property Text : String read GetAsText write SetAsText;

      Property StringByIndex[iIndex : Integer] : TAdvStringListItem Read GetStringByIndex Write SetStringByIndex; Default;
      Property AsText : String Read GetAsText Write SetAsText;
      Property AsCSV : String Read GetAsCSV Write SetAsCSV;
      Property Symbol : String Read FSymbol Write FSymbol;
      Property Sensitive : Boolean Read GetSensitive Write SetSensitive;
  End;

  TAdvStringListIterator = Class(TAdvStringIterator)
    Private
      FStringList : TAdvStringList;
      FIndex : Integer;

      Procedure SetStringList(Const Value: TAdvStringList);

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure First; Override;
      Procedure Last; Override;
      Procedure Next; Override;
      Procedure Back; Override;

      Function More : Boolean; Override;
      Function Current : String; Override;

      Property StringList : TAdvStringList Read FStringList Write SetStringList;
  End;


Implementation


Uses
  AdvCSVFormatters, AdvCSVExtractors, AdvStringStreams;


Constructor TAdvStringList.Create;
Begin
  Inherited;

  FSymbol := cReturn;
End;


Function TAdvStringList.Clone: TAdvStringList;
Begin
  Result := TAdvStringList(Inherited Clone);
End;


Function TAdvStringList.Link: TAdvStringList;
Begin
  Result := TAdvStringList(Inherited Link);
End;


Procedure TAdvStringList.SaveItem(Filer: TAdvFiler; iIndex: Integer);
Begin
  Filer['String'].DefineString(FStringArray^[iIndex]);
End;


Procedure TAdvStringList.LoadItem(Filer: TAdvFiler; iIndex: Integer);
Var
  sValue : TAdvStringListItem;
Begin
  Filer['String'].DefineString(sValue);

  Add(sValue);
End;


Procedure TAdvStringList.AssignItem(Items: TAdvItems; iIndex: Integer);
Begin
  FStringArray^[iIndex] := TAdvStringList(Items).FStringArray^[iIndex];
End;


Function TAdvStringList.Find(Const sValue : TAdvStringListItem; Out iIndex : Integer; aCompare : TAdvItemsCompare): Boolean;
Begin
  Result := Inherited Find(@sValue, iIndex, aCompare);
End;


Procedure TAdvStringList.InternalEmpty(iIndex, iLength: Integer);
Begin
  Inherited;

  MemoryZero(Pointer(NativeUInt(FStringArray) + NativeUInt(iIndex * SizeOf(TAdvStringListItem))), (iLength * SizeOf(TAdvStringListItem)));
End;


Procedure TAdvStringList.InternalTruncate(iValue : Integer);
Begin
  Inherited;

  // finalize the strings that will be truncated.
  If iValue < Count Then
    Finalize(FStringArray^[iValue], Count - iValue);
End;


Procedure TAdvStringList.InternalResize(iValue : Integer);
Begin
  Inherited;

  MemoryResize(FStringArray, Capacity * SizeOf(TAdvStringListItem), iValue * SizeOf(TAdvStringListItem));
End;


Procedure TAdvStringList.InternalCopy(iSource, iTarget, iCount : Integer);
Begin
  Inherited;

  MemoryMove(@FStringArray^[iSource], @FStringArray^[iTarget], iCount * SizeOf(TAdvStringListItem));
End;


Function TAdvStringList.IndexByValue(Const sValue : TAdvStringListItem): Integer;
Begin
  If Not Find(sValue, Result) Then
    Result := -1;
End;


Function TAdvStringList.ExistsByValue(Const sValue : TAdvStringListItem): Boolean;
Begin
  Result := ExistsByIndex(IndexByValue(sValue));
End;


Procedure TAdvStringList.AddAll(oStrings: TAdvStringList);
Var
  iLoop : Integer;
Begin
  For iLoop := 0 To oStrings.Count - 1 Do
    Add(oStrings[iLoop]);
End;


Procedure TAdvStringList.AddAllStringArray(Const aValues : Array Of String);
Var
  iLoop : Integer;
Begin
  For iLoop := Low(aValues) To High(aValues) Do
    Add(aValues[iLoop]);
End;


Function TAdvStringList.Add(Const sValue : TAdvStringListItem) : Integer;
Begin
  Result := -1;

  If Not IsAllowDuplicates And Find(sValue, Result) Then
  Begin { If }
    If IsPreventDuplicates Then
      Error('Add', StringFormat('Item already exists in list (%s)', [sValue]));
  End   { If }
  Else
  Begin { Else }
    If Not IsSorted Then
      Result := Count
    Else If (Result < 0) Then
      Find(sValue, Result);

    Insert(Result, sValue);
  End;  { Else }
End;


Procedure TAdvStringList.InternalInsert(iIndex : Integer);
Begin
  Inherited;

  Pointer(FStringArray^[iIndex]) := Nil;
End;


Procedure TAdvStringList.Insert(iIndex : Integer; Const sValue : TAdvStringListItem);
Begin
  InternalInsert(iIndex);

  FStringArray^[iIndex] := sValue;
End;


Procedure TAdvStringList.InternalDelete(iIndex: Integer);
Begin
  Inherited;

  Finalize(FStringArray^[iIndex]);
End;


Procedure TAdvStringList.DeleteByValue(Const sValue : TAdvStringListItem);
Var
  iIndex : Integer;
Begin
  If Not Find(sValue, iIndex) Then
    Error('DeleteByValue', StringFormat('''%s'' not found in list', [sValue]));

  DeleteByIndex(iIndex);
End;


Procedure TAdvStringList.InternalExchange(iA, iB : Integer);
Var
  iTemp : Integer;
  pA  : Pointer;
  pB  : Pointer;
Begin
  pA := @FStringArray^[iA];
  pB := @FStringArray^[iB];

  iTemp := Integer(pA^);
  Integer(pA^) := Integer(pB^);
  Integer(pB^) := iTemp;
End;


Function TAdvStringList.GetItem(iIndex : Integer) : Pointer;
Begin
  Assert(ValidateIndex('GetItem', iIndex));

  Result := @FStringArray^[iIndex];
End;


Procedure TAdvStringList.SetItem(iIndex : Integer; pValue : Pointer);
Begin
  Assert(ValidateIndex('SetItem', iIndex));

  FStringArray^[iIndex] := String(pValue^);
End;


Function TAdvStringList.GetStringByIndex(iIndex : Integer) : String;
Begin
  Assert(ValidateIndex('GetStringByIndex', iIndex));

  Result := FStringArray^[iIndex];
End;


Procedure TAdvStringList.SetStringByIndex(iIndex : Integer; Const sValue : TAdvStringListItem);
Begin
  Assert(ValidateIndex('SetStringByIndex', iIndex));

  FStringArray^[iIndex] := sValue;
End;


Function TAdvStringList.GetAsText : String;
Var
  iItem, iSymbol : Integer;
  iLoop, iSize : Integer;
  pCurrent : PChar;
  sItem : String;
Begin
  If Count <= 0 Then
    Result := ''
  Else
  Begin
    iSymbol := Length(FSymbol);

    iSize := iSymbol * (Count - 1);
    For iLoop := 0 To Count - 1 Do
      Inc(iSize, Length(FStringArray^[iLoop]));

    SetLength(Result, iSize);

    pCurrent := PChar(Result);

    For iLoop := 0 To Count - 1 Do
    Begin
      sItem := FStringArray^[iLoop];
      iItem := Length(sItem){$IFDEF UNICODE} * 2{$ENDIF};

      If (iItem > 0) Then
      Begin
        MemoryMove(Pointer(sItem), pCurrent, iItem);
        Inc(pCurrent, Length(sItem));
      End;

      If iLoop < Count - 1 Then
      Begin
        MemoryMove(Pointer(FSymbol), pCurrent, iSymbol{$IFDEF UNICODE} * 2{$ENDIF});
        Inc(pCurrent, iSymbol);
      End;
    End;
  End;
End;


Procedure TAdvStringList.SetAsText(Const sValue: String);
Var
  sTemp : String;
  iPos : Integer;
  iSymbol : Integer;
Begin
  Clear;

  sTemp := sValue;
  iPos := Pos(FSymbol, sTemp);
  iSymbol := Length(FSymbol);

  While (iPos > 0) Do
  Begin
    Add(System.Copy(sTemp, 1, iPos - 1));
    System.Delete(sTemp, 1, iPos + iSymbol - 1);

    iPos := Pos(FSymbol, sTemp);
  End;

  If (sTemp <> '') Then
    Add(sTemp);
End;  


Procedure TAdvStringList.LoadFromText(Const sFilename: String);
Var
  aFile : TextFile;
  sTemp : String;
Begin
  AssignFile(aFile, sFilename);
  Try
    Reset(aFile);

    While Not EOF(aFile) Do
    Begin 
      ReadLn(aFile, sTemp);
      Add(sTemp);
    End;
  Finally
    CloseFile(aFile);
  End;
End;


Procedure TAdvStringList.SaveToText(Const sFilename : String);
Var
  aFile : TextFile;
  iLoop : Integer;
Begin
  AssignFile(aFile, sFilename);
  Try
    ReWrite(aFile);

    For iLoop := 0 To Count - 1 Do
      WriteLn(aFile, StringByIndex[iLoop]);
  Finally
    CloseFile(aFile);
  End;
End;


Function TAdvStringList.CapacityLimit : Integer;
Begin
  Result := High(TAdvStringListItemArray);
End;


Function TAdvStringList.CompareInsensitive(pA, pB: Pointer): Integer;
Begin
  Result := StringCompareInsensitive(String(pA^), String(pB^));
End;


Function TAdvStringList.CompareSensitive(pA, pB: Pointer): Integer;
Begin
  Result := StringCompareSensitive(String(pA^), String(pB^));
End;


Procedure TAdvStringList.DefaultCompare(Out aCompare: TAdvItemsCompare);
Begin
  aCompare := CompareInsensitive;
End;


Function TAdvStringList.GetSensitive : Boolean;
Begin
  Result := IsComparedBy(CompareSensitive);
End;


Procedure TAdvStringList.SetSensitive(Const bValue: Boolean);
Begin
  If bValue Then
    ComparedBy(CompareSensitive)
  Else
    ComparedBy(CompareInsensitive);
End;


Function TAdvStringList.Iterator : TAdvIterator;
Begin
  Result := TAdvStringListIterator.Create;
  TAdvStringListIterator(Result).StringList := TAdvStringList(Self.Link);
End;


Constructor TAdvStringListIterator.Create;
Begin
  Inherited;

  FStringList := Nil;
End;


Destructor TAdvStringListIterator.Destroy;
Begin
  FStringList.Free;

  Inherited;
End;


Procedure TAdvStringListIterator.First;
Begin
  Inherited;

  FIndex := 0;
End;


Procedure TAdvStringListIterator.Last;
Begin
  Inherited;

  FIndex := FStringList.Count - 1;
End;


Procedure TAdvStringListIterator.Next;
Begin
  Inherited;

  Inc(FIndex);
End;


Procedure TAdvStringListIterator.Back;
Begin
  Inherited;

  Dec(FIndex);
End;


Function TAdvStringListIterator.Current : String;
Begin
  Result := FStringList[FIndex];
End;


Function TAdvStringListIterator.More : Boolean;
Begin
  Result := FStringList.ExistsByIndex(FIndex);
End;


Procedure TAdvStringListIterator.SetStringList(Const Value : TAdvStringList);
Begin
  FStringList.Free;
  FStringList := Value;
End;


Function TAdvStringList.GetAsCSV : String;
Var
  oStream : TAdvStringStream;
  oFormatter : TAdvCSVFormatter;
  chars: SysUtils.TCharArray;
Begin
  oStream := TAdvStringStream.Create;
  Try
    oFormatter := TAdvCSVFormatter.Create;
    Try
      oFormatter.Stream := oStream.Link;

      oFormatter.ProduceEntryStringList(Self);
    Finally
      oFormatter.Free;
    End;

    chars := TEncoding.UTF8.GetChars(oStream.Bytes);
    SetString(Result, PChar(chars), Length(chars));
  Finally
    oStream.Free;
  End;
End;


Procedure TAdvStringList.SetAsCSV(Const sValue: String);
Var
  oStream : TAdvStringStream;
  oExtractor : TAdvCSVExtractor;
Begin
  oStream := TAdvStringStream.Create;
  Try
    oStream.Bytes := TEncoding.UTF8.GetBytes(sValue);
    oExtractor := TAdvCSVExtractor.Create(oStream.Link, TEncoding.UTF8);
    Try
      oExtractor.ConsumeEntries(Self);
    Finally
      oExtractor.Free;
    End;
  Finally
    oStream.Free;
  End;
End;


Function TAdvStringList.Compare(oStrings : TAdvStringList) : Integer;
Var
  iLoop : Integer;
Begin
  Assert(Invariants('Compare', oStrings, TAdvStringList, 'oStrings'));

  Result := IntegerCompare(Count, oStrings.Count);
  iLoop := 0;

  While (iLoop < oStrings.Count) And (Result = 0) Do
  Begin
    Result := StringCompare(StringByIndex[iLoop], oStrings[iLoop]);

    Inc(iLoop);
  End;
End; 


Function TAdvStringList.Equals(oStrings : TAdvStringList) : Boolean;
Begin
  Result := Compare(oStrings) = 0;
End;


Function TAdvStringList.ExistsAny(oStrings : TAdvStringList) : Boolean;
Var
  iLoop : Integer;
Begin
  Result := False;

  iLoop := 0;

  While Not Result And (iLoop < oStrings.Count) Do
  Begin
    Result := ExistsByValue(oStrings[iLoop]);

    Inc(iLoop);
  End;
End;


procedure TAdvStringList.SetItems(iIndex: integer; sValue: String);
begin
  StringByIndex[iIndex] := sValue;
end;

function TAdvStringList.GetItems(iIndex: integer): String;
begin
  result := StringByIndex[iIndex];
end;

procedure TAdvStringList.delete(iIndex: integer);
begin
  DeleteByIndex(iIndex);
end;

procedure TAdvStringList.populate(iCount: integer);
begin
  while Count < iCount Do
    Add('');
end;

function TAdvStringList.IndexOf(value: String): Integer;
begin
  result := IndexByValue(value);
end;

End. // AdvStringLists //
