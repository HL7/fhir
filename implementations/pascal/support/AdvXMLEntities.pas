 Unit AdvXMLEntities;

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
  AdvObjects, AdvStringMatches, AdvExceptions, StringSupport, AdvPersistents, AdvPersistentLists, MathSupport, AdvFactories,
  AdvStringLists, AdvObjectLists;


Type
  EAdvXMLObject = Class(EAdvException);
  
  TAdvXMLParserNamespaces = Class (TAdvStringMatch)
    Private
      FDefaultNamespace : String;

    Public
      Constructor Create; Override;

      Function Clone : TAdvXMLParserNamespaces; Overload;

      Procedure Assign(oObject : TAdvObject); Override;

      Property DefaultNamespace : String Read FDefaultNamespace Write FDefaultNamespace;
  End;

  TAdvXMLAttribute = Class(TAdvPersistent)
    Private
      FNamespace : String;
      FName : String;
      FValue : String;
      FSortKey : String;

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Function Link : TAdvXMLAttribute;
      Function Clone : TAdvXMLAttribute;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Property Namespace : String Read FNamespace Write FNamespace;
      Property Name : String Read FName Write FName;
      Property Value : String Read FValue Write FValue;
      Property SortKey : String read FSortKey write FSortKey;
  End;

  TAdvXMLAttributeList = Class(TAdvPersistentList)
    Private
      Function GetElementByIndex(Const iIndex : Integer) : TAdvXMLAttribute;
      Procedure SetElementByIndex(Const iIndex : Integer; Const oValue : TAdvXMLAttribute);

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function CompareByNamespacedName(pA, pB : Pointer) : Integer;
      Function CompareByNamespace(pA, pB : Pointer) : Integer;
      Function CompareByName(pA, pB : Pointer) : Integer;
      Function CompareByValue(pA, pB : Pointer) : Integer;
      Function CompareBySortKey(pA, pB : Pointer) : Integer;

      Function Get(Const aValue : Integer) : TAdvXMLAttribute; Reintroduce;

    Public
      Function Link : TAdvXMLAttributeList;
      Function Clone : TAdvXMLAttributeList;

      Function New : TAdvXMLAttribute; Reintroduce;

      Function IndexByNamespacedName(Const aNamespace, aName : String) : Integer;
      Function IndexByNamespace(Const aValue : String) : Integer;
      Function IndexByName(Const aValue : String) : Integer;
      Function IndexByValue(Const aValue : String) : Integer;

      Function GetByNamespacedName(Const aNamespace, aName : String) : TAdvXMLAttribute;
      Function GetByNamespace(Const aValue : String) : TAdvXMLAttribute;
      Function GetByName(Const aValue : String) : TAdvXMLAttribute;
      Function GetByValue(Const aValue : String) : TAdvXMLAttribute;

      Function ExistsByNamespacedName(Const aNamespace, aName : String) : Boolean;
      Function ExistsByNamespace(Const aValue : String) : Boolean;
      Function ExistsByName(Const aValue : String) : Boolean;
      Function ExistsByValue(Const aValue : String) : Boolean;

      Procedure SortedByNamespacedName;
      Procedure SortedByNamespace;
      Procedure SortedByName;
      Procedure SortedByValue;
      Procedure SortedBySortKey;

      Function IsSortedByNamespacedName : Boolean;
      Function IsSortedByNamespace : Boolean;
      Function IsSortedByName : Boolean;
      Function IsSortedByValue : Boolean;
      Function IsSortedBySortKey : Boolean;

      Property ElementByIndex[Const iIndex : Integer] : TAdvXMLAttribute Read GetElementByIndex Write SetElementByIndex; Default;
  End;

  TAdvXMLAttributeMatch = Class(TAdvStringMatch)
    Private
      Function GetAttribute(Const sKey: String): String;
      Procedure SetAttribute(Const sKey, sValue: String);

    Public
      Constructor Create; Override;

      Property Attribute[Const sKey : String] : String Read GetAttribute Write SetAttribute; Default;
  End;

  TAdvXMLElementType = (AdvXMLElementTypeUnknown, AdvXMLElementTypeNode, AdvXMLElementTypeText, AdvXMLElementTypeComment);

  TAdvXMLElementIterator = Class;
  TAdvXMLElementList = Class;

  TAdvXMLElement = Class(TAdvPersistent)
    Private
      FElementType : TAdvXMLElementType;

      // if element
      FNamespace : String;
      FName : String;
      FID : String;
      FChildrenElementList : TAdvXMLElementList;
      FAttributeList : TAdvXMLAttributeList;

      // if comment or Text
      FContent : String;

      Function GetNamespace : String;
      Function GetName : String;
      Function GetId : String;
      Function GetChildren : TAdvXMLElementList;
      Function GetAttributes : TAdvXMLAttributeList;
      Function GetContent : String;

      Procedure SetElementType(Const Value : TAdvXMLElementType);
      Procedure SetNamespace(Const Value : String);
      Procedure SetName(Const Value : String);
      Procedure SetId(Const Value : String);
      Procedure SetChildren(Const Value : TAdvXMLElementList);
      Procedure SetAttributes(Const Value : TAdvXMLAttributeList);
      Procedure SetContent(Const Value : String);

    Protected
      Function ErrorClass : EAdvExceptionClass; Overload; Override;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvXMLElement;
      Function Clone : TAdvXMLElement; 

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Function Iterator(Const sNamespace, sName : String) : TAdvXMLElementIterator; Overload;
      Function Iterator(Const sName : String) : TAdvXMLElementIterator; Overload;
      Function Iterator(aElementType : TAdvXMLElementType) : TAdvXMLElementIterator; Overload;

      Procedure Clear;

      Function HasNamespace : Boolean;
      Function HasName : Boolean;
      Function HasId : Boolean;
      Function HasChildren : Boolean;
      Function HasAttributes : Boolean;
      Function HasContent : Boolean;
      Function HasText : Boolean;
      Function HasComment : Boolean;

      Property ElementType : TAdvXMLElementType Read FElementType Write SetElementType;
      Property Namespace : String Read GetNamespace Write SetNamespace;
      Property Name : String Read GetName Write SetName;
      Property Id : String Read GetId Write SetId;
      Property Children : TAdvXMLElementList Read GetChildren Write SetChildren;
      Property Attributes : TAdvXMLAttributeList Read GetAttributes Write SetAttributes;
      Property Content : String Read GetContent Write SetContent;
  End;

  TAdvXMLElementList = Class(TAdvPersistentList)
    Private
      Function GetElement(Const iIndex : Integer) : TAdvXMLElement;
      Procedure SetElement(Const iIndex : Integer; Const oValue : TAdvXMLElement);

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function CompareById(pA, pB : Pointer) : Integer;
      Function CompareByName(pA, pB : Pointer) : Integer;

      Function Get(Const aValue : Integer) : TAdvXMLElement; Reintroduce;

    Public
      Function Link : TAdvXMLElementList; 
      Function Clone : TAdvXMLElementList;

      Function New : TAdvXMLElement; Reintroduce;

      Function IndexById(Const aValue : String) : Integer;
      Function GetById(Const aValue : String) : TAdvXMLElement;
      Function ExistsById(Const aValue : String) : Boolean;

      Function IndexByName(Const aValue : String) : Integer;
      Function GetByName(Const aValue : String) : TAdvXMLElement;
      Function ExistsByName(Const aValue : String) : Boolean;

      Property Elements[Const iIndex : Integer] : TAdvXMLElement Read GetElement Write SetElement; Default;
  End;

  TAdvXMLElementIterator = Class(TAdvPersistentListIterator)
    Private
      FElementType : TAdvXMLElementType;
      FNamespace : String;
      FName : String;

    Protected
      Function Skip : Boolean; Overload; Override;

    Public
      Function Current : TAdvXMLElement; Reintroduce;
  End;

  TAdvXMLDocument = Class(TAdvPersistent)
    Private
      FRootElement : TAdvXMLElement;

      Function GetRootElement : TAdvXMLElement;
      Procedure SetRootElement(Const Value : TAdvXMLElement);

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvXMLDocument;
      Function Clone : TAdvXMLDocument;

      Procedure Assign(oObject : TAdvObject); Override;
      Procedure Define(oFiler : TAdvFiler); Override;

      Procedure Clear; 

      Function HasRootElement : Boolean;
      Property RootElement : TAdvXMLElement Read GetRootElement Write SetRootElement;
  End;

  TAdvXMLDocumentList = Class(TAdvPersistentList)
    Private
      Function GetElementByIndex(Const iIndex : Integer) : TAdvXMLDocument;
      Procedure SetElementByIndex(Const iIndex : Integer; Const oValue : TAdvXMLDocument);

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function Get(Const aValue : Integer) : TAdvXMLDocument; Reintroduce; 

    Public
      Function Link : TAdvXMLDocumentList;
      Function Clone : TAdvXMLDocumentList; 

      Function New : TAdvXMLDocument; Reintroduce;

      Property ElementByIndex[Const iIndex : Integer] : TAdvXMLDocument Read GetElementByIndex Write SetElementByIndex; Default;
  End;

  TAdvXMLNamespaceEntry = Class(TAdvObject)
    Private
      FKey : String;
      FValues : TAdvStringList;

      Function GetValue: String;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Push(Const Value: String);
      Procedure Pop;

      Function HasValue : Boolean;

      Property Key : String Read FKey Write FKey;
      Property Value : String Read GetValue;
  End;

  TAdvXMLNamespaceEntryList = Class(TAdvObjectList)
    Private
      Function GetEntryByIndex(Const iIndex: Integer): TAdvXMLNamespaceEntry;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

      Function CompareByKey(pA, pB : Pointer) : Integer;

    Public
      Function IndexByKey(Const sKey : String) : Integer;

      Procedure SortedByKey;

      Property EntryByIndex[Const iIndex : Integer] : TAdvXMLNamespaceEntry Read GetEntryByIndex; Default;
  End;

  TAdvXMLNamespaceLevel = Class(TAdvObject)
    Private
      FEntryList : TAdvXMLNamespaceEntryList;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Property EntryList : TAdvXMLNamespaceEntryList Read FEntryList;
  End;

  TAdvXMLNamespaceLevelList = Class(TAdvObjectList)
    Private
      Function GetLevelByIndex(Const iIndex: Integer): TAdvXMLNamespaceLevel;

    Protected
      Function ItemClass : TAdvObjectClass; Override;

    Public
      Property LevelByIndex[Const iIndex : Integer] : TAdvXMLNamespaceLevel Read GetLevelByIndex; Default;
  End;

  TAdvXMLNamespaceManager = Class(TAdvObject)
    Private
      FEntryList : TAdvXMLNamespaceEntryList;
      FLevelList : TAdvXMLNamespaceLevelList;

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Push(Const oAttributes : TAdvStringMatch);
      Procedure Pop;

      Function DefaultNamespace : String;
      Function NamespaceOfPrefix(Const sPrefix: String): String;
      Function LocalNameOf(Const sElementName: String) : String;
      Function PrefixOf(Const sElementName: String) : String;
      Function NamespaceOf(Const sElementName: String) : String;

      Procedure ListPrefixes(Const oPrefixNamespaces: TAdvStringMatch);
  End;

  EAdvXMLNamespaceManager = Class(EAdvException)
  End;


Const
  NAMES_ADVXMLELEMENTTYPE : Array [TAdvXMLElementType] Of String = ('Unknown', 'Element', 'Text', 'Comment');


Implementation


Constructor TAdvXMLParserNamespaces.Create;
Begin
  Inherited;

  Forced := True;
End;


Function TAdvXMLParserNamespaces.Clone : TAdvXMLParserNamespaces;
Begin
  Result := TAdvXMLParserNamespaces(Inherited Clone);
End;


Procedure TAdvXMLParserNamespaces.Assign(oObject : TAdvObject);
Begin
  Inherited;

  DefaultNamespace := TAdvXMLParserNamespaces(oObject).DefaultNamespace;
End;


Function TAdvXMLAttribute.Link : TAdvXMLAttribute;
Begin
  Result := TAdvXMLAttribute(Inherited Link);
End;


Function TAdvXMLAttribute.Clone : TAdvXMLAttribute;
Begin
  Result := TAdvXMLAttribute(Inherited Clone);
End;


Procedure TAdvXMLAttribute.Assign(oObject : TAdvObject);
Begin
  Inherited;

  Namespace := TAdvXMLAttribute(oObject).Namespace;
  Name := TAdvXMLAttribute(oObject).Name;
  Value := TAdvXMLAttribute(oObject).Value;
  SortKey := TAdvXMLAttribute(oObject).SortKey;
End;


Procedure TAdvXMLAttribute.Define(oFiler : TAdvFiler);
Begin
  Inherited;

  oFiler['Namespace'].DefineString(FNamespace);
  oFiler['Name'].DefineString(FName);
  oFiler['Value'].DefineString(FValue);
  oFiler['SortKey'].DefineString(FSortKey);
End;


Function TAdvXMLAttribute.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvXMLObject;
End;


Function TAdvXMLAttributeList.Link : TAdvXMLAttributeList;
Begin
  Result := TAdvXMLAttributeList(Inherited Link);
End;


Function TAdvXMLAttributeList.Clone : TAdvXMLAttributeList;
Begin
  Result := TAdvXMLAttributeList(Inherited Clone);
End;


Function TAdvXMLAttributeList.New : TAdvXMLAttribute;
Begin
  Result := TAdvXMLAttribute(Inherited New);
End;


Function TAdvXMLAttributeList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvXMLAttribute;
End;


Function TAdvXMLAttributeList.GetElementByIndex(Const iIndex : Integer) : TAdvXMLAttribute;
Begin
  Result := TAdvXMLAttribute(ObjectByIndex[iIndex]);
End;


Procedure TAdvXMLAttributeList.SetElementByIndex(Const iIndex : Integer; Const oValue : TAdvXMLAttribute);
Begin
  Inherited ObjectByIndex[iIndex] := oValue;
End;


Function TAdvXMLAttributeList.CompareByNamespacedName(pA, pB : Pointer) : Integer;
Begin
  Result := StringSupport.StringCompare(TAdvXMLAttribute(pA).Namespace, TAdvXMLAttribute(pB).Namespace);
  If Result = 0 Then
    Result := StringSupport.StringCompare(TAdvXMLAttribute(pA).Name, TAdvXMLAttribute(pB).Name);
End;


function TAdvXMLAttributeList.CompareBySortKey(pA, pB: Pointer): Integer;
begin
  Result := StringSupport.StringCompare(TAdvXMLAttribute(pA).SortKey, TAdvXMLAttribute(pB).SortKey);
end;

Function TAdvXMLAttributeList.CompareByNamespace(pA, pB : Pointer) : Integer;
Begin
  Result := StringSupport.StringCompare(TAdvXMLAttribute(pA).Namespace, TAdvXMLAttribute(pB).Namespace);
End;


Function TAdvXMLAttributeList.CompareByName(pA, pB : Pointer) : Integer;
Begin
  Result := StringSupport.StringCompare(TAdvXMLAttribute(pA).Name, TAdvXMLAttribute(pB).Name);
End;


Function TAdvXMLAttributeList.CompareByValue(pA, pB : Pointer) : Integer;
Begin
  Result := StringSupport.StringCompare(TAdvXMLAttribute(pA).Value, TAdvXMLAttribute(pB).Value);
End;


Function TAdvXMLAttributeList.IndexByNamespacedName(Const aNamespace, aName: String) : Integer;
Var
  oElement : TAdvXMLAttribute;
Begin
  oElement := New;
  Try
    oElement.Namespace := aNamespace;
    oElement.Name := aName;

    If Not Find(oElement, Result, CompareByNamespacedName) Then
    Begin
      Result := -1;
    End;
  Finally
    oElement.Free;
  End;
End;


Function TAdvXMLAttributeList.IndexByNamespace(Const aValue : String) : Integer;
Var
  oElement : TAdvXMLAttribute;
Begin
  oElement := New;
  Try
    oElement.Namespace := aValue;

    If Not Find(oElement, Result, CompareByNamespace) Then
    Begin
      Result := -1;
    End;
  Finally
    oElement.Free;
  End;
End;


Function TAdvXMLAttributeList.IndexByName(Const aValue : String) : Integer;
Var
  oElement : TAdvXMLAttribute;
Begin
  oElement := New;
  Try
    oElement.Name := aValue;

    If Not Find(oElement, Result, CompareByName) Then
    Begin
      Result := -1;
    End;
  Finally
    oElement.Free;
  End;
End;


Function TAdvXMLAttributeList.IndexByValue(Const aValue : String) : Integer;
Var
  oElement : TAdvXMLAttribute;
Begin
  oElement := New;
  Try
    oElement.Value := aValue;

    If Not Find(oElement, Result, CompareByValue) Then
    Begin
      Result := -1;
    End;
  Finally
    oElement.Free;
  End;
End;


Function TAdvXMLAttributeList.Get(Const aValue : Integer) : TAdvXMLAttribute;
Begin
  Result := TAdvXMLAttribute(Inherited Get(aValue));
End;


Function TAdvXMLAttributeList.GetByNamespacedName(Const aNamespace, aName : String) : TAdvXMLAttribute;
Begin
  Result := Get(IndexByNamespacedName(aNamespace, aName));
End;


Function TAdvXMLAttributeList.GetByNamespace(Const aValue : String) : TAdvXMLAttribute;
Begin
  Result := Get(IndexByNamespace(aValue));
End;


Function TAdvXMLAttributeList.GetByName(Const aValue : String) : TAdvXMLAttribute;
Begin
  Result := Get(IndexByName(aValue));
End;


Function TAdvXMLAttributeList.GetByValue(Const aValue : String) : TAdvXMLAttribute;
Begin
  Result := Get(IndexByValue(aValue));
End;


Function TAdvXMLAttributeList.ExistsByNamespacedName(Const aNamespace, aName : String) : Boolean;
Begin
  Result := ExistsByIndex(IndexByNamespacedName(aNamespace, aName));
End;


Function TAdvXMLAttributeList.ExistsByNamespace(Const aValue : String) : Boolean;
Begin
  Result := ExistsByIndex(IndexByNamespace(aValue));
End;


Function TAdvXMLAttributeList.ExistsByName(Const aValue : String) : Boolean;
Begin
  Result := ExistsByIndex(IndexByName(aValue));
End;


Function TAdvXMLAttributeList.ExistsByValue(Const aValue : String) : Boolean;
Begin
  Result := ExistsByIndex(IndexByValue(aValue));
End;


Procedure TAdvXMLAttributeList.SortedByNamespacedName;
Begin
  SortedBy(CompareByNamespacedName);
End;


procedure TAdvXMLAttributeList.SortedBySortKey;
begin
  SortedBy(CompareBySortKey);
end;

Procedure TAdvXMLAttributeList.SortedByNamespace;
Begin
  SortedBy(CompareByNamespace);
End;


Procedure TAdvXMLAttributeList.SortedByName;
Begin
  SortedBy(CompareByName);
End;


Procedure TAdvXMLAttributeList.SortedByValue;
Begin
  SortedBy(CompareByValue);
End;


Function TAdvXMLAttributeList.IsSortedByNamespacedName : Boolean;
Begin
  Result := IsSortedBy(CompareByNamespacedName);
End;


function TAdvXMLAttributeList.IsSortedBySortKey: Boolean;
begin
  Result := IsSortedBy(CompareBySortKey);
end;

Function TAdvXMLAttributeList.IsSortedByNamespace : Boolean;
Begin
  Result := IsSortedBy(CompareByNamespace);
End;


Function TAdvXMLAttributeList.IsSortedByName : Boolean;
Begin
  Result := IsSortedBy(CompareByName);
End;


Function TAdvXMLAttributeList.IsSortedByValue : Boolean;
Begin
  Result := IsSortedBy(CompareByValue);
End;


Constructor TAdvXMLAttributeMatch.Create;
Begin
  Inherited;

  Forced := True;
End;


Function TAdvXMLAttributeMatch.GetAttribute(Const sKey: String): String;
Begin
  Result := GetValueByKey(sKey);
End;


Procedure TAdvXMLAttributeMatch.SetAttribute(Const sKey, sValue: String);
Begin
  SetValueByKey(sKey, sValue);
End;


Constructor TAdvXMLElement.Create;
Begin
  Inherited;

  FChildrenElementList := Nil;
  FAttributeList := Nil;
End;


Destructor TAdvXMLElement.Destroy;
Begin
  FChildrenElementList.Free;
  FAttributeList.Free;

  Inherited;
End;


Function TAdvXMLElement.Link : TAdvXMLElement;
Begin
  Result := TAdvXMLElement(Inherited Link);
End;


Function TAdvXMLElement.Clone : TAdvXMLElement;
Begin
  Result := TAdvXMLElement(Inherited Clone);
End;


Procedure TAdvXMLElement.Assign(oObject : TAdvObject);
Begin
  Inherited;

  ElementType := TAdvXMLElement(oObject).ElementType;

  If HasNamespace Then
    Namespace := TAdvXMLElement(oObject).Namespace;

  If HasName Then
    Name := TAdvXMLElement(oObject).Name;

  If HasId Then
    Id := TAdvXMLElement(oObject).Id;

  If HasChildren Then
    Children := TAdvXMLElement(oObject).Children.Clone;

  If HasAttributes Then
    Attributes := TAdvXMLElement(oObject).Attributes.Clone;

  If HasContent Then
    Content := TAdvXMLElement(oObject).Content;
End;


Procedure TAdvXMLElement.Define(oFiler : TAdvFiler);
Begin
  Inherited;

  oFiler['ElementType'].DefineEnumerated(FElementType, NAMES_ADVXMLELEMENTTYPE);
  oFiler['Namespace'].DefineString(FNamespace);
  oFiler['Name'].DefineString(FName);
  oFiler['Id'].DefineString(FId);
  oFiler['Children'].DefineObject(FChildrenElementList, TAdvXMLElementList);
  oFiler['Attributes'].DefineObject(FAttributeList, TAdvXMLAttributeList);
  oFiler['Content'].DefineString(FContent);
End;


Procedure TAdvXMLElement.Clear;
Begin
  FNamespace := '';
  FName := '';
  FId := '';

  If Assigned(FChildrenElementList) Then
  Begin
    If HasChildren Then
      FChildrenElementList.Clear
    Else
    Begin
      FChildrenElementList.Free;
      FChildrenElementList := Nil;
    End;
  End
  Else If HasChildren Then
    FChildrenElementList := TAdvXMLElementList.Create;

  If Assigned(FAttributeList) Then
  Begin
    If HasAttributes Then
      FAttributeList.Clear
    Else
    Begin
      FAttributeList.Free;
      FAttributeList := Nil;
    End;
  End
  Else If HasAttributes Then
    FAttributeList := TAdvXMLAttributeList.Create;

  FContent := '';
End;


Procedure TAdvXMLElement.SetElementType(Const Value : TAdvXMLElementType);
Begin
  If Value <> FElementType Then
  Begin
    Assert(Condition(Value <> AdvXMLElementTypeUnknown, 'SetElementType', 'ElementType must not be unknown'));
    FElementType := Value;
    Clear;
  End;
End;


Function TAdvXMLElement.HasComment: Boolean;
Begin
  Result := FElementType = AdvXMLElementTypeComment;
End;


Function TAdvXMLElement.HasText: Boolean;
Begin
  Result := FElementType = AdvXMLElementTypeText;
End;


Function TAdvXMLElement.HasNamespace : Boolean;
Begin
  Assert(Condition(FElementType <> AdvXMLElementTypeUnknown, 'GetNamespace', 'Element has no type assigned'));
  Result := FElementType = AdvXMLElementTypeNode;
End;


Function TAdvXMLElement.HasName : Boolean;
Begin
  Assert(Condition(FElementType <> AdvXMLElementTypeUnknown, 'GetNamespace', 'Element has no type assigned'));
  Result := FElementType = AdvXMLElementTypeNode;
End;


Function TAdvXMLElement.HasId : Boolean;
Begin
  Assert(Condition(FElementType <> AdvXMLElementTypeUnknown, 'GetNamespace', 'Element has no type assigned'));
  Result := FElementType = AdvXMLElementTypeNode;
End;


Function TAdvXMLElement.HasChildren : Boolean;
Begin
  Assert(Condition(FElementType <> AdvXMLElementTypeUnknown, 'GetNamespace', 'Element has no type assigned'));
  Result := FElementType = AdvXMLElementTypeNode;
End;


Function TAdvXMLElement.HasAttributes : Boolean;
Begin
  Assert(Condition(FElementType <> AdvXMLElementTypeUnknown, 'GetNamespace', 'Element has no type assigned'));
  Result := FElementType = AdvXMLElementTypeNode;
End;


Function TAdvXMLElement.HasContent : Boolean;
Begin
  Assert(Condition(FElementType <> AdvXMLElementTypeUnknown, 'GetNamespace', 'Element has no type assigned'));
  Result := FElementType In [AdvXMLElementTypeText, AdvXMLElementTypeComment];
End;


Function TAdvXMLElement.GetNamespace : String;
Begin
  Assert(Condition(HasNamespace, 'GetNamespace', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Namespace'])));
  Result := FNamespace;
End;


Procedure TAdvXMLElement.SetNamespace(Const Value : String);
Begin
  Assert(Condition(HasNamespace, 'SetNamespace', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Namespace'])));
  FNamespace := Value;
End;


Function TAdvXMLElement.GetName : String;
Begin
  Assert(Condition(HasName, 'GetName', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Name'])));
  Result := FName;
End;


Procedure TAdvXMLElement.SetName(Const Value : String);
Begin
  Assert(Condition(HasName, 'SetName', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Name'])));
  FName := Value;
End;


Function TAdvXMLElement.GetId : String;
Begin
  Assert(Condition(HasId, 'GetId', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Id'])));
  Result := FId;
End;


Procedure TAdvXMLElement.SetId(Const Value : String);
Begin
  Assert(Condition(HasId, 'SetId', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Id'])));
  FId := Value;
End;


Function TAdvXMLElement.GetChildren : TAdvXMLElementList;
Begin
  Assert(Condition(HasChildren, 'GetChildren', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Children'])));
  Result := FChildrenElementList;
End;


Procedure TAdvXMLElement.SetChildren(Const Value : TAdvXMLElementList);
Begin
  Assert(Condition(HasChildren, 'SetChildren', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Children'])));

  FChildrenElementList.Free;
  FChildrenElementList := Value;
End;


Function TAdvXMLElement.GetAttributes : TAdvXMLAttributeList;
Begin
  Assert(Condition(HasAttributes, 'GetAttributes', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Attributes'])));
  Result := FAttributeList;
End;


Procedure TAdvXMLElement.SetAttributes(Const Value : TAdvXMLAttributeList);
Begin
  Assert(Condition(HasAttributes, 'SetAttributes', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Attributes'])));
  FAttributeList.Free;
  FAttributeList := Value;
End;


Function TAdvXMLElement.GetContent : String;
Var
  iLoop : Integer;
Begin
  If HasContent Then
    Result := FContent
  Else If Not HasChildren Or (Children.Count = 0) Then
    Result := ''
  Else
  Begin
    Result := Children[0].Content;
    For iLoop := 1 To Children.Count - 1 Do
      Result := Result + Children[iLoop].Content;
  End;
End;


Procedure TAdvXMLElement.SetContent(Const Value : String);
Begin
  Assert(Condition(HasContent, 'SetContent', StringFormat('Element of Type %s does not have property %s', [NAMES_ADVXMLELEMENTTYPE[FElementType], 'Content'])));
  FContent := Value;
End;


Function TAdvXMLElement.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvXMLObject;
End;


Function TAdvXMLElement.Iterator(Const sNamespace, sName : String) : TAdvXMLElementIterator;
Begin
  Result := Iterator(sName);
  Result.FNamespace := sNamespace;
End;


Function TAdvXMLElement.Iterator(Const sName : String) : TAdvXMLElementIterator;
Begin
  Result := Iterator(AdvXMLElementTypeNode);
  Result.FName := sName;
End;


Function TAdvXMLElement.Iterator(aElementType : TAdvXMLElementType) : TAdvXMLElementIterator;
Begin
  Result := TAdvXMLElementIterator.Create;
  Result.List := Children.Link;
  Result.FElementType := aElementType;
End;


Function TAdvXMLElementIterator.Current : TAdvXMLElement;
Begin
  Assert(Invariants('Current', Inherited Current, TAdvXMLElement, 'Current'));
  Result := TAdvXMLElement(Inherited Current);
End;


Function TAdvXMLElementIterator.Skip : Boolean;
Begin
  Result := False;
  If FElementType <> AdvXMLElementTypeUnknown Then
    Result := Current.ElementType <> FElementType;
  If FNamespace <> '' Then
    Result := Result Or (Current.Namespace <> FNamespace);
  If FName <> '' Then
    Result := Result Or (Current.Name <> FName);
End;


Function TAdvXMLElementList.Link : TAdvXMLElementList;
Begin
  Result := TAdvXMLElementList(Inherited Link);
End;


Function TAdvXMLElementList.Clone : TAdvXMLElementList;
Begin
  Result := TAdvXMLElementList(Inherited Clone);
End;


Function TAdvXMLElementList.New : TAdvXMLElement;
Begin
  Result := TAdvXMLElement(Inherited New);
End;


Function TAdvXMLElementList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvXMLElement;
End;


Function TAdvXMLElementList.GetElement(Const iIndex : Integer) : TAdvXMLElement;
Begin
  Result := TAdvXMLElement(ObjectByIndex[iIndex]);
End;


Procedure TAdvXMLElementList.SetElement(Const iIndex : Integer; Const oValue : TAdvXMLElement);
Begin
  Inherited ObjectByIndex[iIndex] := oValue;
End;


Function TAdvXMLElementList.CompareById(pA, pB : Pointer) : Integer;
Begin
  Result := StringSupport.StringCompare(TAdvXMLElement(pA).Id, TAdvXMLElement(pB).Id);
End;


Function TAdvXMLElementList.CompareByName(pA, pB : Pointer) : Integer;
Begin
  Result := IntegerCompare(Integer(TAdvXMLElement(pA).ElementType), Integer(TAdvXMLElement(pB).ElementType));

  If Result = 0 Then
    Result := StringCompare(TAdvXMLElement(pA).Name, TAdvXMLElement(pB).Name);
End;


Function TAdvXMLElementList.Get(Const aValue : Integer) : TAdvXMLElement;
Begin
  Result := TAdvXMLElement(Inherited Get(aValue));
End;


Function TAdvXMLElementList.IndexById(Const aValue : String) : Integer;
Var
  oElement : TAdvXMLElement;
Begin
  oElement := New;
  Try
    oElement.Id := aValue;

    If Not Find(oElement, Result, CompareById) Then
      Result := -1;
  Finally
    oElement.Free;
  End;
End;


Function TAdvXMLElementList.GetById(Const aValue : String) : TAdvXMLElement;
Begin
  Result := Get(IndexById(aValue));
End;


Function TAdvXMLElementList.ExistsById(Const aValue : String) : Boolean;
Begin
  Result := ExistsByIndex(IndexById(aValue));
End;


Function TAdvXMLElementList.IndexByName(Const aValue : String) : Integer;
Var
  oElement : TAdvXMLElement;
Begin
  oElement := New;
  Try
    oElement.ElementType := AdvXMLElementTypeNode;
    oElement.Name := aValue;

    If Not Find(oElement, Result, CompareByName) Then
      Result := -1;
  Finally
    oElement.Free;
  End;
End;


Function TAdvXMLElementList.GetByName(Const aValue : String) : TAdvXMLElement;
Begin
  Result := Get(IndexByName(aValue));
End;


Function TAdvXMLElementList.ExistsByName(Const aValue : String) : Boolean;
Begin
  Result := ExistsByIndex(IndexByName(aValue));
End;


Constructor TAdvXMLDocument.Create;
Begin
  Inherited;

  FRootElement := Nil;
End;


Destructor TAdvXMLDocument.Destroy;
Begin
  FRootElement.Free;

  Inherited;
End;


Function TAdvXMLDocument.HasRootElement : Boolean;
Begin
  Result := Assigned(FRootElement);
End;


Function TAdvXMLDocument.GetRootElement : TAdvXMLElement;
Begin
  Assert(Invariants('GetRootElement', FRootElement, TAdvXMLElement, 'FRootElement'));

  Result := FRootElement;
End;


Procedure TAdvXMLDocument.SetRootElement(Const Value : TAdvXMLElement);
Begin
  Assert(Not Assigned(Value) Or Invariants('SetRootElement', Value, TAdvXMLElement, 'Value'));

  FRootElement.Free;
  FRootElement := Value;
End;


Function TAdvXMLDocument.Link : TAdvXMLDocument;
Begin
  Result := TAdvXMLDocument(Inherited Link);
End;


Function TAdvXMLDocument.Clone : TAdvXMLDocument;
Begin
  Result := TAdvXMLDocument(Inherited Clone);
End;


Procedure TAdvXMLDocument.Assign(oObject : TAdvObject);
Begin
  Inherited;

  RootElement := TAdvXMLDocument(oObject).RootElement.Clone;
End;


Procedure TAdvXMLDocument.Define(oFiler : TAdvFiler);
Begin
  Inherited;

  oFiler['RootElement'].DefineObject(FRootElement, TAdvXMLElement);
End;


Function TAdvXMLDocument.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvXMLObject;
End;


Procedure TAdvXMLDocument.Clear;
Begin
  RootElement := Nil;
End;


Function TAdvXMLDocumentList.Link : TAdvXMLDocumentList;
Begin
  Result := TAdvXMLDocumentList(Inherited Link);
End;


Function TAdvXMLDocumentList.Clone : TAdvXMLDocumentList;
Begin
  Result := TAdvXMLDocumentList(Inherited Clone);
End;


Function TAdvXMLDocumentList.New : TAdvXMLDocument;
Begin
  Result := TAdvXMLDocument(Inherited New);
End;


Function TAdvXMLDocumentList.ItemClass : TAdvObjectClass;
Begin
  Result := TAdvXMLDocument;
End;


Function TAdvXMLDocumentList.GetElementByIndex(Const iIndex : Integer) : TAdvXMLDocument;
Begin
  Result := TAdvXMLDocument(ObjectByIndex[iIndex]);
End;


Procedure TAdvXMLDocumentList.SetElementByIndex(Const iIndex : Integer; Const oValue : TAdvXMLDocument);
Begin
  ObjectByIndex[iIndex] := oValue;
End;


Function TAdvXMLDocumentList.Get(Const aValue : Integer) : TAdvXMLDocument;
Begin
  Result := TAdvXMLDocument(Inherited Get(aValue));
End;


Constructor TAdvXMLNamespaceEntry.Create;
Begin
  Inherited;

  FValues := TAdvStringList.Create;
End;


Destructor TAdvXMLNamespaceEntry.Destroy;
Begin
  FValues.Free;

  Inherited;
End;


Function TAdvXMLNamespaceEntry.GetValue: String;
Begin
  Result := FValues.StringByIndex[FValues.Count - 1];
End;


Function TAdvXMLNamespaceEntry.HasValue : Boolean;
Begin
  Result := Not FValues.IsEmpty;
End;


Procedure TAdvXMLNamespaceEntry.Pop;
Begin
  FValues.DeleteByIndex(FValues.Count - 1);
End;


Procedure TAdvXMLNamespaceEntry.Push(Const Value: String);
Begin
  FValues.Add(Value);
End;


Function TAdvXMLNamespaceEntryList.CompareByKey(pA, pB: Pointer): Integer;
Begin
  Result := StringCompare(TAdvXMLNamespaceEntry(pA).Key, TAdvXMLNamespaceEntry(pB).Key);
End;


Function TAdvXMLNamespaceEntryList.IndexByKey(Const sKey: String): Integer;
Var
  oEntry : TAdvXMLNamespaceEntry;
Begin
  oEntry := TAdvXMLNamespaceEntry(ItemNew);
  Try
    oEntry.Key := sKey;

    Result := IndexBy(oEntry, CompareByKey);
  Finally
    oEntry.Free;
  End;
End;


Procedure TAdvXMLNamespaceEntryList.SortedByKey;
Begin
  SortedBy(CompareByKey);
End;


Function TAdvXMLNamespaceEntryList.ItemClass: TAdvObjectClass;
Begin
  Result := TAdvXMLNamespaceEntry;
End;


Function TAdvXMLNamespaceEntryList.GetEntryByIndex(Const iIndex: Integer): TAdvXMLNamespaceEntry;
Begin
  Result := TAdvXMLNamespaceEntry(ObjectByIndex[iIndex]);
End;


Constructor TAdvXMLNamespaceLevel.Create;
Begin
  Inherited;

  FEntryList := TAdvXMLNamespaceEntryList.Create;
  FEntryList.SortedByKey;
End;


Destructor TAdvXMLNamespaceLevel.Destroy;
Begin
  FEntryList.Free;

  Inherited;
End;


Function TAdvXMLNamespaceLevelList.ItemClass: TAdvObjectClass;
Begin
  Result := TAdvXMLNamespaceLevel;
End;


Function TAdvXMLNamespaceLevelList.GetLevelByIndex(Const iIndex: Integer): TAdvXMLNamespaceLevel;
Begin
  Result := TAdvXMLNamespaceLevel(ObjectByIndex[iIndex]);
End;


Constructor TAdvXMLNamespaceManager.Create;
Var
  oDefaultEntry : TAdvXMLNamespaceEntry;
Begin
  Inherited;

  FLevelList := TAdvXMLNamespaceLevelList.Create;
  FEntryList := TAdvXMLNamespaceEntryList.Create;

  // Add default namespace entry.
  oDefaultEntry := TAdvXMLNamespaceEntry.Create;
  oDefaultEntry.Key := '';
  oDefaultEntry.Push('');
  FEntryList.Add(oDefaultEntry);

  // Add "xml" default entry
  oDefaultEntry := TAdvXMLNamespaceEntry.Create;
  oDefaultEntry.Key := 'xml';
  oDefaultEntry.Push('http://www.w3.org/XML/1998/namespace');
  FEntryList.Add(oDefaultEntry);

  FEntryList.SortedByKey;
  FEntryList.PreventDuplicates;
End;


Destructor TAdvXMLNamespaceManager.Destroy;
Begin
  FEntryList.Free;
  FLevelList.Free;

  Inherited;
End;


Function TAdvXMLNamespaceManager.ErrorClass: EAdvExceptionClass;
Begin
  Result := EAdvXMLNamespaceManager;
End;


Function TAdvXMLNamespaceManager.DefaultNamespace: String;
Begin
  Result := NamespaceOfPrefix('');
End;


Procedure TAdvXMLNamespaceManager.ListPrefixes(Const oPrefixNamespaces: TAdvStringMatch);
Var
  iEntryIndex : Integer;
  oEntry : TAdvXMLNamespaceEntry;
Begin
  oPrefixNamespaces.Clear;

  For iEntryIndex := 0 To FEntryList.Count - 1 Do
  Begin
    oEntry := FEntryList[iEntryIndex];

    oPrefixNamespaces.Add(oEntry.Key, oEntry.Value);
  End;
End;


Function TAdvXMLNamespaceManager.LocalNameOf(Const sElementName: String): String;
Var
  iColonIndex : Integer;
Begin
  iColonIndex := StringSupport.StringFind(sElementName, ':');

  If iColonIndex <= 0  Then
    Result := sElementName
  Else
    Result := StringCopy(sElementName, iColonIndex + 1, MaxInt);
End;


Function TAdvXMLNamespaceManager.NamespaceOf(Const sElementName: String): String;
Begin
  Result := NamespaceOfPrefix(PrefixOf(sElementName));
End;


Function TAdvXMLNamespaceManager.NamespaceOfPrefix(Const sPrefix: String) : String;
Var
  iEntryIndex : Integer;
Begin
  iEntryIndex := FEntryList.IndexByKey(sPrefix);

  If iEntryIndex < 0 Then
    Error('NamespaceOfPrefix', StringFormat('The namespace prefix ''%s'' has not beed defined.', [sPrefix]));

  Result := FEntryList[iEntryIndex].Value;
End;


Procedure TAdvXMLNamespaceManager.Pop;
Var
  oLevel : TAdvXMLNamespaceLevel;
  oEntry : TAdvXMLNamespaceEntry;
  iEntryIndex : Integer;
Begin
  oLevel := TAdvXMLNamespaceLevel(FLevelList.RemoveLast);
  Try
    For iEntryIndex := 0 To oLevel.EntryList.Count - 1  Do
    Begin
      oEntry := oLevel.EntryList[iEntryIndex];

      oEntry.Pop;

      If Not oEntry.HasValue Then
        FEntryList.DeleteByReference(oEntry);
    End;
  Finally
    oLevel.Free;
  End;
End;


Function TAdvXMLNamespaceManager.PrefixOf(Const sElementName: String): String;
Var
  iColonIndex : Integer;
Begin
  iColonIndex := StringSupport.StringFind(sElementName, ':');

  If iColonIndex <= 0 Then
    Result := ''
  Else
    Result := StringCopy(sElementName, 1, iColonIndex - 1);
End;


Procedure TAdvXMLNamespaceManager.Push(Const oAttributes: TAdvStringMatch);
Const
  XMLNS_STRING = 'xmlns';
  XMLNS_LENGTH = Length(XMLNS_STRING);
Var
  oLevel : TAdvXMLNamespaceLevel;
  iAttributeIndex : Integer;
  sName : String;
  sPrefix : String;
  sNamespace : String;
  iEntryIndex : Integer;
  oEntry : TAdvXMLNamespaceEntry;
Begin
  oLevel := TAdvXMLNamespaceLevel.Create;
  Try
    For iAttributeIndex := 0 To oAttributes.Count - 1 Do
    Begin
      // Determine if this is a namespace name
      sName := oAttributes.KeyByIndex[iAttributeIndex];

      If StringSupport.StringStartsWith(sName, XMLNS_STRING) Then
      Begin
        // Obtain the prefix
        If Length(sName) = XMLNS_LENGTH Then
          sPrefix := ''
        Else
          sPrefix := StringCopy(sName, XMLNS_LENGTH + 2, MaxInt);

        // Obtain the value
        sNamespace := oAttributes.ValueByIndex[iAttributeIndex];

        // Add the name-value pair
        // Obtain a namespace value object...
        iEntryIndex := FEntryList.IndexByKey(sPrefix);

        If FEntryList.ExistsByIndex(iEntryIndex) Then
        Begin
          // ...either existing one...
          oEntry := FEntryList[iEntryIndex];
        End
        Else
        Begin
          // ...or new one.
          oEntry := TAdvXMLNamespaceEntry.Create;
          oEntry.Key := sPrefix;
          FEntryList.Add(oEntry);
        End;

        // Add the namespace to the level
        oEntry.Push(sNamespace);

        oLevel.EntryList.Add(oEntry.Link);
      End;
    End;

    FLevelList.Add(oLevel.Link);
  Finally
    oLevel.Free;
  End;
End;


End.
