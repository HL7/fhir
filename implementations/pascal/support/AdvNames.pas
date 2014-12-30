Unit AdvNames;

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
  AdvStringLists, AdvPersistents, AdvPersistentLists;


Type
  TAdvName = Class(TAdvPersistent)
    Private
      FName : String;

    Protected
      Function GetName: String; Virtual;
      Procedure SetName(Const Value: String); Virtual;

    Public
      Function Link : TAdvName;
      Function Clone : TAdvName; 

      Procedure Assign(oSource : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Property Name : String Read GetName Write SetName;
  End; 

  TAdvNameClass = Class Of TAdvName;

  TAdvNameList = Class(TAdvPersistentList)
    Private
      FSymbol : String;

      Function GetName(iIndex : Integer) : TAdvName;
      Procedure SetName(iIndex : Integer; oName : TAdvName);

      Function GetAsText: String;
      Procedure SetAsText(Const Value: String);

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function CompareByName(pA, pB: Pointer): Integer; Virtual;

      Procedure DefaultCompare(Out aEvent : TAdvItemsCompare); Override;

      Function FindByName(Const sName: String; Out iIndex: Integer): Boolean; Overload;
      Function FindByName(oName : TAdvName; Out iIndex: Integer): Boolean; Overload;

    Public
      Constructor Create; Override;

      Function Link : TAdvNameList;
      Function Clone : TAdvNameList;

      Procedure SortedByName; 
      Function IsSortedByName : Boolean; 

      Function IndexByName(Const sName : String) : Integer; Overload;
      Function IndexByName(Const oName : TAdvName) : Integer; Overload;
      Function ExistsByName(Const oName : TAdvName) : Boolean; Overload;
      Function ExistsByName(Const sName : String) : Boolean; Overload;
      Function GetByName(Const sName : String) : TAdvName; Overload;
      Function GetByName(oName : TAdvName) : TAdvName; Overload;
      Function EnsureByName(Const sName : String) : TAdvName; Overload;
      Function ForceByName(Const sName : String) : TAdvName;
      Procedure RemoveByName(Const sName : String);
      Function AddByName(Const sName : String) : Integer;

      Property Names[iIndex : Integer] : TAdvName Read GetName Write SetName; Default;
      Property AsText : String Read GetAsText Write SetAsText;
      Property Symbol : String Read FSymbol Write FSymbol;
  End;

  TAdvObjectClass = AdvPersistents.TAdvObjectClass;
  TAdvObject = AdvPersistents.TAdvObject;
  TAdvFiler = AdvPersistents.TAdvFiler;


Implementation


Function TAdvName.Link : TAdvName;
Begin
  Result := TAdvName(Inherited Link);
End;


Function TAdvName.Clone : TAdvName;
Begin
  Result := TAdvName(Inherited Clone);
End;


Procedure TAdvName.Assign(oSource : TAdvObject);
Begin
  Inherited;

  FName := TAdvName(oSource).Name;
End;  


Procedure TAdvName.Define(oFiler : TAdvFiler);
Begin
  Inherited;

  oFiler['Name'].DefineString(FName);
End;


Function TAdvName.GetName: String;
Begin
  Result := FName;
End;


Procedure TAdvName.SetName(Const Value: String);
Begin 
  FName := Value;
End;  


Constructor TAdvNameList.Create;
Begin
  Inherited;

  FSymbol := cReturn;
End;


Function TAdvNameList.Link : TAdvNameList;
Begin
  Result := TAdvNameList(Inherited Link);
End;


Function TAdvNameList.Clone : TAdvNameList;
Begin
  Result := TAdvNameList(Inherited Clone);
End;


Function TAdvNameList.ItemClass : TAdvObjectClass;
Begin 
  Result := TAdvName;
End;  


Procedure TAdvNameList.DefaultCompare(Out aEvent: TAdvItemsCompare);
Begin 
  aEvent := CompareByName;
End;  


Function TAdvNameList.CompareByName(pA, pB : Pointer) : Integer;
Begin 
  Result := StringCompare(TAdvName(pA).Name, TAdvName(pB).Name);
End;  


Function TAdvNameList.FindByName(oName: TAdvName; Out iIndex: Integer): Boolean;
Begin 
  Result := Find(oName, iIndex, CompareByName);
End;  


Function TAdvNameList.FindByName(Const sName : String; Out iIndex : Integer) : Boolean;
Var
  oName : TAdvName;
Begin 
  oName := TAdvName(ItemNew);
  Try
    oName.Name := sName;

    Result := FindByName(oName, iIndex);
  Finally
    oName.Free;
  End;  
End;  


Function TAdvNameList.EnsureByName(Const sName : String) : TAdvName;
Begin
  Result := GetByName(sName);

  Assert(Invariants('EnsureByName', Result, ItemClass, 'Result'));
End;


Function TAdvNameList.GetByName(Const sName: String): TAdvName;
Var
  iIndex : Integer;
Begin 
  If FindByName(sName, iIndex) Then
    Result := Names[iIndex]
  Else
    Result := Nil;
End;  


Function TAdvNameList.IndexByName(Const sName : String) : Integer;
Begin 
  If Not FindByName(sName, Result) Then
    Result := -1;
End;  


Function TAdvNameList.ExistsByName(Const sName: String): Boolean;
Begin 
  Result := ExistsByIndex(IndexByName(sName));
End;  


Function TAdvNameList.IndexByName(Const oName : TAdvName) : Integer;
Begin 
  If Not FindByName(oName, Result) Then
    Result := -1;
End;  


Function TAdvNameList.ExistsByName(Const oName : TAdvName): Boolean;
Begin 
  Result := ExistsByIndex(IndexByName(oName));
End;  


Function TAdvNameList.ForceByName(Const sName: String): TAdvName;
Var
  oName  : TAdvName;
  iIndex : Integer;
Begin 
  oName := TAdvName(ItemNew);
  Try
    oName.Name := sName;

    If FindByName(oName, iIndex) Then
      Result := Names[iIndex]
    Else
    Begin 
      Insert(iIndex, oName.Link);
      Result := oName;
    End;  
  Finally
    oName.Free;
  End;  
End;  


Function TAdvNameList.GetByName(oName : TAdvName): TAdvName;
Var
  iIndex : Integer;
Begin 
  If FindByName(oName, iIndex) Then
    Result := Names[iIndex]
  Else
    Result := Nil;
End;  


Procedure TAdvNameList.RemoveByName(Const sName: String);
Var
  iIndex : Integer;
Begin 
  If Not FindByName(sName, iIndex) Then
    Error('RemoveByName', StringFormat('Object ''%s'' not found in list.', [sName]));

  DeleteByIndex(iIndex);
End;  


Function TAdvNameList.AddByName(Const sName: String): Integer;
Var
  oItem : TAdvName;
Begin 
  oItem := TAdvName(ItemNew);
  Try
    oItem.Name := sName;

    Result := Add(oItem.Link);
  Finally
    oItem.Free;
  End;  
End;  


Function TAdvNameList.IsSortedByName : Boolean;
Begin
  Result := IsSortedBy(CompareByName);
End;  


Procedure TAdvNameList.SortedByName;
Begin 
  SortedBy(CompareByName);
End;  


Function TAdvNameList.GetName(iIndex : Integer) : TAdvName;
Begin 
  Result := TAdvName(ObjectByIndex[iIndex]);
End;


Procedure TAdvNameList.SetName(iIndex : Integer; oName : TAdvName);
Begin
  ObjectByIndex[iIndex] := oName;
End;  


Function TAdvNameList.GetAsText : String;
Var
  oStrings : TAdvStringList;
  iLoop    : Integer;
Begin 
  oStrings := TAdvStringList.Create;
  Try
    oStrings.Symbol := FSymbol;

    For iLoop := 0 To Count - 1 Do
      oStrings.Add(Names[iLoop].Name);

    Result := oStrings.AsText;
  Finally
    oStrings.Free;
  End;  
End;  


Procedure TAdvNameList.SetAsText(Const Value: String);
Var
  oStrings : TAdvStringList;
  iLoop    : Integer;
  oItem    : TAdvName;
Begin 
  Clear;

  oStrings := TAdvStringList.Create;
  Try
    oStrings.Symbol := FSymbol;

    oStrings.AsText := Value;

    For iLoop := 0 To oStrings.Count - 1 Do
    Begin 
      oItem := TAdvName(ItemNew);
      Try
        oItem.Name := StringTrimWhitespace(oStrings[iLoop]);

        Add(oItem.Link);
      Finally
        oItem.Free;
      End;  
    End;  
  Finally
    oStrings.Free;
  End;  
End;  


End. // AdvNames //
