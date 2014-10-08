unit AdvObjectLists;

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

interface


uses
  MathSupport, MemorySupport, StringSupport,
  AdvObjects, AdvExceptions, AdvItems, AdvFilers, AdvIterators;

type
  PAdvObject = ^TAdvObject;
  TAdvObjectArray = array[0..(MaxInt div SizeOf(TAdvObject)) - 1] of TAdvObject;
  PAdvObjectArray = ^TAdvObjectArray;

  TAdvObjectListClass = class of TAdvObjectList;
  TAdvObjectListIterator = class;
  TAdvObjectListIteratorClass = class of TAdvObjectListIterator;

  TAdvObjectList = class(TAdvItems)
  private
    FObjectArray: PAdvObjectArray;

    FItemClass: TAdvObjectClass;

    function GetObject(iIndex: integer): TAdvObject;
    procedure SetObject(iIndex: integer; const oValue: TAdvObject);

    function GetObjectByIndex(const iIndex: integer): TAdvObject;
    procedure SetObjectByIndex(const iIndex: integer; const Value: TAdvObject);

    function GetItemClass: TAdvObjectClass;
    procedure SetItemClass(const Value: TAdvObjectClass);

  protected
    function ErrorClass: EAdvExceptionClass; override;

    function GetItem(iIndex: integer): Pointer; override;
    procedure SetItem(iIndex: integer; pValue: Pointer); override;
    function AddressOfItem(iIndex: integer): PAdvObject;

    procedure LoadItem(oFiler: TAdvFiler; iIndex: integer); override;
    procedure SaveItem(oFiler: TAdvFiler; iIndex: integer); override;
    procedure AssignItem(oItems: TAdvItems; iIndex: integer); override;

    procedure InternalTruncate(iValue: integer); override;
    procedure InternalResize(iValue: integer); override;
    procedure InternalCopy(iSource, iTarget, iCount: integer); override;
    procedure InternalEmpty(iIndex, iLength: integer); override;
    procedure InternalInsert(iIndex: integer); override;
    procedure InternalExchange(iA, iB: integer); override;
    procedure InternalDelete(iIndex: integer); override;

    function ValidateIndex(const sMethod: string; iIndex: integer): boolean;
      override;
    function ValidateItem(const sMethod: string; oObject: TAdvObject;
      const sObject: string): boolean; virtual;

    function Find(pValue: Pointer; Out iIndex: integer;
      aCompare: TAdvItemsCompare = nil): boolean; overload; override;
    function Find(oValue: TAdvObject): integer; overload;

    function GetByIndex(iIndex: integer): TAdvObject; overload;

    function CompareByClass(pA, pB: Pointer): integer; overload;
    function CompareByReference(pA, pB: Pointer): integer; overload;

    function ItemNew: TAdvObject; virtual;
    function ItemClass: TAdvObjectClass; virtual;
    function IteratorClass: TAdvObjectListIteratorClass; virtual;
    function CapacityLimit: integer; override;

    procedure FreezeChildren; override;

    function Insertable(const sMethod: string; oObject: TAdvObject): boolean;
      overload;
    function Replaceable(const sMethod: string; oOld, oNew: TAdvObject): boolean;
      overload;
    function Deleteable(const sMethod: string; oObject: TAdvObject): boolean;
      overload;

    function Deleteable(const sMethod: string; iIndex: integer): boolean;
      overload; override;
    function Extendable(const sMethod: string; iCount: integer): boolean; override;

    procedure InternalAfterInclude(iIndex: integer); virtual;
    procedure InternalBeforeExclude(iIndex: integer); virtual;

    // Attribute
    function AllowUnassigned: boolean; virtual;

  public
    constructor Create; override;
    destructor Destroy; override;

    function Link: TAdvObjectList;
    function Clone: TAdvObjectList;

    procedure Collect(oList: TAdvObjectList);
    procedure AddAll(oList: TAdvObjectList);

    function Add(oValue: TAdvObject): integer; overload; virtual;
    procedure Insert(iIndex: integer; oValue: TAdvObject); overload;
    procedure Move(iSource, iTarget: integer); overload;

    procedure FreezeObjects; virtual;

    function IndexBy(oValue: TAdvObject; aCompare: TAdvItemsCompare): integer;
    function ExistsBy(oValue: TAdvObject; aCompare: TAdvItemsCompare): boolean;
    function GetBy(oValue: TAdvObject; aCompare: TAdvItemsCompare): TAdvObject;
    function ForceBy(oValue: TAdvObject; aCompare: TAdvItemsCompare): TAdvObject;
    procedure DeleteBy(oValue: TAdvObject; aCompare: TAdvItemsCompare);

    procedure SortedByClass;
    procedure OrderedByClass;
    function IsSortedByClass: boolean;
    function IsOrderedByClass: boolean;
    function IndexByClass(aClass: TAdvObjectClass): integer; overload;
    function IndexByClass(oValue: TAdvObject): integer; overload;
    function GetByClass(aClass: TAdvObjectClass): TAdvObject;
    function ExistsByClass(aClass: TAdvObjectClass): boolean;
    procedure DeleteByClass(aClass: TAdvObjectClass);

    procedure SortedByReference;
    procedure OrderedByReference;
    function IsSortedByReference: boolean;
    function IsOrderedByReference: boolean;
    function IndexByReference(oValue: TAdvObject): integer;
    function ExistsByReference(oValue: TAdvObject): boolean;
    procedure DeleteByReference(oValue: TAdvObject);
    procedure DeleteAllByReference(oValue: TAdvObjectList);

    function ContainsAllByReference(oObjectList: TAdvObjectList): boolean;
    function ContainsAnyByReference(oObjectList: TAdvObjectList): boolean;

    function ExistsByDefault(oValue: TAdvObject): boolean;
    function IndexByDefault(oValue: TAdvObject): integer;
    procedure DeleteByDefault(oValue: TAdvObject);

    function RemoveFirst: TAdvObject;
    function RemoveLast: TAdvObject;

    function Iterator: TAdvIterator; override;
    function ProduceIterator: TAdvObjectListIterator; overload;
    procedure ConsumeIterator(oIterator: TAdvObjectListIterator); overload;

    function Get(iIndex: integer): TAdvObject; overload;
    function New: TAdvObject; overload; virtual;

    property ObjectByIndex[const iIndex: integer]: TAdvObject
      read GetObjectByIndex write SetObjectByIndex; default;
    property Objects[const iIndex: integer]: TAdvObject
      read GetObjectByIndex write SetObjectByIndex;
    property NominatedClass: TAdvObjectClass read GetItemClass write SetItemClass;
  end;

  EAdvObjectList = class(EAdvItems);

  TAdvObjectListIterator = class(TAdvObjectIterator)
  private
    FList: TAdvObjectList;
    FIndex: integer;
    FDeleted: boolean;

    procedure SetList(const Value: TAdvObjectList);
    function GetList: TAdvObjectList;

  protected
    procedure StepBack;
    procedure StepNext;
    function Skip: boolean; virtual;

  public
    constructor Create; override;
    destructor Destroy; override;

    procedure First; override;
    procedure Last; override;
    procedure Next; override;
    procedure Back; override;
    function More: boolean; override;

    function Current: TAdvObject; override;
    procedure Delete;

    property Index: integer read FIndex write FIndex;
    property List: TAdvObjectList read GetList write SetList;
  end;

  TAdvObject = AdvObjects.TAdvObject;
  TAdvObjectClass = AdvObjects.TAdvObjectClass;
  TAdvItemsCompare = AdvItems.TAdvItemsCompare;
  TAdvIterator = AdvIterators.TAdvIterator;


implementation


constructor TAdvObjectList.Create;
begin
  inherited;

  FItemClass := ItemClass;
end;


destructor TAdvObjectList.Destroy;
begin
  inherited;
end;


function TAdvObjectList.Link: TAdvObjectList;
begin
  Result := TAdvObjectList(inherited Link);
end;


function TAdvObjectList.Clone: TAdvObjectList;
begin
  Result := TAdvObjectList(inherited Clone);
end;


function TAdvObjectList.ErrorClass: EAdvExceptionClass;
begin
  Result := EAdvObjectList;
end;


procedure TAdvObjectList.AssignItem(oItems: TAdvItems; iIndex: integer);
begin
  FObjectArray^[iIndex] := TAdvObjectList(oItems).FObjectArray^[iIndex].Clone;
end;


procedure TAdvObjectList.SaveItem(oFiler: TAdvFiler; iIndex: integer);
begin
  oFiler['Object'].DefineObject(FObjectArray^[iIndex]);
end;


procedure TAdvObjectList.LoadItem(oFiler: TAdvFiler; iIndex: integer);
var
  oObject: TAdvObject;
begin
  oObject := nil;
  try
    oFiler['Object'].DefineObject(oObject);

    Add(oObject.Link);
  finally
    oObject.Free;
  end;
end;


function TAdvObjectList.ValidateIndex(const sMethod: string; iIndex: integer): boolean;
begin
  Result := inherited ValidateIndex(sMethod, iIndex);

  ValidateItem(sMethod, FObjectArray^[iIndex], 'FObjectArray^[' +
    IntegerToString(iIndex) + ']');
end;


function TAdvObjectList.ValidateItem(const sMethod: string;
  oObject: TAdvObject; const sObject: string): boolean;
begin
  if Assigned(oObject) or not AllowUnassigned then
    Invariants(sMethod, oObject, FItemClass, sObject);

  Result := True;
end;


procedure TAdvObjectList.InternalTruncate(iValue: integer);
var
  oValue: TAdvObject;
  iLoop: integer;
begin
  inherited;

  for iLoop := iValue to Count - 1 do
  begin
    oValue := FObjectArray^[iLoop];

    InternalBeforeExclude(iLoop);

    FObjectArray^[iLoop] := nil;

    Assert(not Assigned(oValue) or Invariants('InternalResize', oValue,
      FItemClass, 'oValue'));

    oValue.Free;
  end;
end;


procedure TAdvObjectList.InternalResize(iValue: integer);
begin
  inherited;

  MemoryResize(FObjectArray, Capacity * SizeOf(TAdvObject), iValue * SizeOf(TAdvObject));
end;


procedure TAdvObjectList.InternalEmpty(iIndex, iLength: integer);
begin
  inherited;

  MemoryZero(Pointer(NativeUInt(FObjectArray) + NativeUInt((iIndex * SizeOf(TAdvObject)))),
    (iLength * SizeOf(TAdvObject)));
end;


procedure TAdvObjectList.InternalCopy(iSource, iTarget, iCount: integer);
begin
  inherited;

  MemoryMove(@FObjectArray^[iSource], @FObjectArray^[iTarget], iCount *
    SizeOf(TAdvObject));
end;


procedure TAdvObjectList.InternalInsert(iIndex: integer);
begin
  inherited;

  FObjectArray^[iIndex] := nil;
end;


procedure TAdvObjectList.InternalDelete(iIndex: integer);
begin
  inherited;

  InternalBeforeExclude(iIndex);

  FObjectArray^[iIndex].Free;
  FObjectArray^[iIndex] := nil;
end;


function TAdvObjectList.ItemClass: TAdvObjectClass;
begin
  Result := TAdvObject;
end;


function TAdvObjectList.ItemNew: TAdvObject;
begin
  Result := FItemClass.Create;
end;


procedure TAdvObjectList.Collect(oList: TAdvObjectList);
begin
  oList.Clear;
  oList.AddAll(Self);
end;


procedure TAdvObjectList.AddAll(oList: TAdvObjectList);
var
  iIndex: integer;
begin
  Assert(Condition(oList <> Self, 'AddAll',
    'Cannot addall items from a list to itself.'));

  Capacity := IntegerMax(Count + oList.Count, Capacity);
  for iIndex := 0 to oList.Count - 1 do
    Add(oList[iIndex].Link);
end;


procedure TAdvObjectList.DeleteByDefault(oValue: TAdvObject);
begin
  DeleteBy(oValue, DefaultComparison);
end;


procedure TAdvObjectList.DeleteBy(oValue: TAdvObject; aCompare: TAdvItemsCompare);
var
  iIndex: integer;
begin
  Assert(ValidateItem('DeleteBy', oValue, 'oValue'));

  if not Find(oValue, iIndex, aCompare) then
    Error('DeleteBy', 'Object not found in list.');

  DeleteByIndex(iIndex);
end;


function TAdvObjectList.IndexBy(oValue: TAdvObject; aCompare: TAdvItemsCompare): integer;
begin
  if not Find(oValue, Result, aCompare) then
    Result := -1;
end;


function TAdvObjectList.ExistsBy(oValue: TAdvObject;
  aCompare: TAdvItemsCompare): boolean;
var
  iIndex: integer;
begin
  Result := not IsEmpty and Find(oValue, iIndex, aCompare);
end;


function TAdvObjectList.GetBy(oValue: TAdvObject;
  aCompare: TAdvItemsCompare): TAdvObject;
var
  iIndex: integer;
begin
  if Find(oValue, iIndex, aCompare) then
    Result := ObjectByIndex[iIndex]
  else
    Result := nil;
end;


function TAdvObjectList.ForceBy(oValue: TAdvObject;
  aCompare: TAdvItemsCompare): TAdvObject;
var
  iIndex: integer;
begin
  if Find(oValue, iIndex, aCompare) then
    Result := ObjectByIndex[iIndex]
  else
  begin
    Result := oValue;

    Insert(iIndex, oValue.Link);
  end;
end;


function TAdvObjectList.CompareByReference(pA, pB: Pointer): integer;
begin
  Result := IntegerCompare(integer(pA), integer(pB));
end;


function TAdvObjectList.CompareByClass(pA, pB: Pointer): integer;
var
  aClassA: TAdvObjectClass;
  aClassB: TAdvObjectClass;
begin
  if Assigned(pA) then
    aClassA := TAdvObject(pA).ClassType
  else
    aClassA := nil;

  if Assigned(pB) then
    aClassB := TAdvObject(pB).ClassType
  else
    aClassB := nil;

  Result := IntegerCompare(integer(aClassA), integer(aClassB));
end;


function TAdvObjectList.Find(oValue: TAdvObject): integer;
begin
  Find(oValue, Result);
end;


function TAdvObjectList.IndexByClass(aClass: TAdvObjectClass): integer;
var
  oValue: TAdvObject;
begin
  Assert(Invariants('IndexByClass', aClass, FItemClass, 'aClass'));

  oValue := aClass.Create;
  try
    Result := IndexByClass(oValue);
  finally
    oValue.Free;
  end;
end;


function TAdvObjectList.IndexByClass(oValue: TAdvObject): integer;
begin
  Assert(ValidateItem('IndexByClass', oValue, 'oValue'));

  Result := IndexBy(oValue,
{$IFDEF FPC}
    @
{$ENDIF}
    CompareByClass);
end;


function TAdvObjectList.ExistsByClass(aClass: TAdvObjectClass): boolean;
begin
  Result := ExistsByIndex(IndexByClass(aClass));
end;


function TAdvObjectList.GetByClass(aClass: TAdvObjectClass): TAdvObject;
begin
  Result := Get(IndexByClass(aClass));
end;


function TAdvObjectList.IndexByReference(oValue: TAdvObject): integer;
begin
  Assert(ValidateItem('IndexByReference', oValue, 'oValue'));

  Result := IndexBy(oValue,
{$IFDEF FPC}
    @
{$ENDIF}
    CompareByReference);
end;


function TAdvObjectList.ExistsByReference(oValue: TAdvObject): boolean;
begin
  Result := ExistsByIndex(IndexByReference(oValue));
end;


function TAdvObjectList.IndexByDefault(oValue: TAdvObject): integer;
begin
  Assert(ValidateItem('IndexByDefault', oValue, 'oValue'));

  Result := IndexBy(oValue, DefaultComparison);
end;


function TAdvObjectList.ExistsByDefault(oValue: TAdvObject): boolean;
begin
  Result := ExistsByIndex(IndexByDefault(oValue));
end;


function TAdvObjectList.Add(oValue: TAdvObject): integer;
begin
  Assert(ValidateItem('Add', oValue, 'oValue'));

  Result := -1;

  if not IsAllowDuplicates and Find(oValue, Result) then
  begin
    oValue.Free; // free ignored object

    if IsPreventDuplicates then
      Error('Add', 'Object already exists in list.');
  end
  else
  begin
    if not IsSorted then
      Result := Count
    else if (Result < 0) then
      Find(oValue, Result);

    Insert(Result, oValue);
  end;
end;


procedure TAdvObjectList.Insert(iIndex: integer; oValue: TAdvObject);
begin
  Assert(ValidateItem('Insert', oValue, 'oValue'));
  Assert(Condition(not IsSorted or (Find(oValue) = iIndex), 'Insert',
    'Cannot insert into a sorted list unless the index is correct.'));
  Assert(Insertable('Insert', oValue));

  InternalInsert(iIndex);

  FObjectArray^[iIndex] := oValue;

  InternalAfterInclude(iIndex);
end;


procedure TAdvObjectList.DeleteByReference(oValue: TAdvObject);
begin
  DeleteBy(oValue,
{$IFDEF FPC}
    @
{$ENDIF}
    CompareByReference);
end;


procedure TAdvObjectList.DeleteAllByReference(oValue: TAdvObjectList);
var
  iIndex: integer;
begin
  for iIndex := 0 to oValue.Count - 1 do
    DeleteByReference(oValue[iIndex]);
end;


procedure TAdvObjectList.DeleteByClass(aClass: TAdvObjectClass);
var
  iIndex: integer;
begin
  Assert(Invariants('DeleteByClass', aClass, FItemClass, 'aClass'));

  iIndex := IndexByClass(aClass);

  if not ExistsByIndex(iIndex) then
    Error('DeleteByClass', StringFormat('Object of class ''%s'' not found in list.',
      [aClass.ClassName]));

  DeleteByIndex(iIndex);
end;


function TAdvObjectList.RemoveLast: TAdvObject;
begin
  if Count <= 0 then
    Result := nil
  else
  begin
    Result := FObjectArray^[Count - 1].Link;
    DeleteByIndex(Count - 1);

    Assert(ValidateItem('RemoveLast', Result, 'Result'));
  end;
end;


function TAdvObjectList.RemoveFirst: TAdvObject;
begin
  if Count <= 0 then
    Result := nil
  else
  begin
    Result := FObjectArray^[0].Link;
    try
      DeleteByIndex(0);
    except
      Result.Free;

      raise;
    end;

    Assert(ValidateItem('RemoveFirst', Result, 'Result'));
  end;
end;


procedure TAdvObjectList.InternalExchange(iA, iB: integer);
var
  iTemp: integer;
  pA: Pointer;
  pB: Pointer;
begin
  pA := @FObjectArray^[iA];
  pB := @FObjectArray^[iB];

  iTemp := integer(pA^);
  integer(pA^) := integer(pB^);
  integer(pB^) := iTemp;
end;


procedure TAdvObjectList.Move(iSource, iTarget: integer);
var
  oObject: TAdvObject;
begin
  Assert(Condition(iSource <> iTarget, 'Move', 'Can''t move the same index.'));

  oObject := ObjectByIndex[iSource].Link;
  try
    if iSource < iTarget then
    begin
      Insert(iTarget, oObject.Link);
      DeleteByIndex(iSource);
    end
    else
    begin
      DeleteByIndex(iSource);
      Insert(iTarget, oObject.Link);
    end;
  finally
    oObject.Free;
  end;
end;


function TAdvObjectList.Find(pValue: Pointer; Out iIndex: integer;
  aCompare: TAdvItemsCompare): boolean;
begin
  Assert(Invariants('Find', TAdvObject(pValue), FItemClass, 'pValue'));

  Result := inherited Find(pValue, iIndex, aCompare);
end;


function TAdvObjectList.AddressOfItem(iIndex: integer): PAdvObject;
begin
  Assert(ValidateIndex('AddressOfItem', iIndex));

  Result := @(FObjectArray^[iIndex]);
end;


function TAdvObjectList.GetItemClass: TAdvObjectClass;
begin
  Result := FItemClass;
end;


procedure TAdvObjectList.SetItemClass(const Value: TAdvObjectClass);
begin
  Assert(Condition(Count = 0, 'SetItemClass',
    'Cannot change ItemClass once objects are present in the list.'));

  FItemClass := Value;
end;


function TAdvObjectList.GetItem(iIndex: integer): Pointer;
begin
  Assert(ValidateIndex('GetItem', iIndex));

  Result := FObjectArray^[iIndex];
end;


procedure TAdvObjectList.SetItem(iIndex: integer; pValue: Pointer);
begin
  Assert(ValidateIndex('SetItem', iIndex));

  FObjectArray^[iIndex] := TAdvObject(pValue);
end;


function TAdvObjectList.GetObject(iIndex: integer): TAdvObject;
begin
  Assert(ValidateIndex('GetObject', iIndex));

  Result := FObjectArray^[iIndex];
end;


procedure TAdvObjectList.SetObject(iIndex: integer; const oValue: TAdvObject);
begin
  Assert(ValidateIndex('SetObject', iIndex));
  Assert(Replaceable('SetObject', iIndex));
  Assert(Replaceable('SetObject', FObjectArray^[iIndex], oValue));

  InternalBeforeExclude(iIndex);

  FObjectArray^[iIndex].Free;
  FObjectArray^[iIndex] := oValue;

  InternalAfterInclude(iIndex);
end;


function TAdvObjectList.CapacityLimit: integer;
begin
  Result := High(TAdvObjectArray);
end;


function TAdvObjectList.Get(iIndex: integer): TAdvObject;
begin
  if ExistsByIndex(iIndex) then
    Result := ObjectByIndex[iIndex]
  else
    Result := nil;
end;


function TAdvObjectList.GetByIndex(iIndex: integer): TAdvObject;
begin
  Result := ObjectByIndex[iIndex];
end;


function TAdvObjectList.Iterator: TAdvIterator;
begin
  Result := ProduceIterator;
end;


procedure TAdvObjectList.SortedByClass;
begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByClass);
end;


procedure TAdvObjectList.OrderedByClass;
begin
  OrderedBy({$IFDEF FPC}@{$ENDIF}CompareByClass);
end;


function TAdvObjectList.IsSortedByClass: boolean;
begin
  Result := IsSortedBy({$IFDEF FPC}@{$ENDIF}CompareByClass);
end;


function TAdvObjectList.IsOrderedByClass: boolean;
begin
  Result := IsOrderedBy({$IFDEF FPC}@{$ENDIF}CompareByClass);
end;


procedure TAdvObjectList.SortedByReference;
begin
  SortedBy({$IFDEF FPC}@{$ENDIF}CompareByReference);
end;


procedure TAdvObjectList.OrderedByReference;
begin
  OrderedBy({$IFDEF FPC}@{$ENDIF}CompareByReference);
end;


function TAdvObjectList.IsSortedByReference: boolean;
begin
  Result := IsSortedBy({$IFDEF FPC}@{$ENDIF}CompareByReference);
end;


function TAdvObjectList.IsOrderedByReference: boolean;
begin
  Result := IsOrderedBy({$IFDEF FPC}@{$ENDIF}CompareByReference);
end;


function TAdvObjectList.Deleteable(const sMethod: string; iIndex: integer): boolean;
begin
  Result := inherited Deleteable(sMethod, iIndex);

  Deleteable(sMethod, FObjectArray^[iIndex]);
end;


function TAdvObjectList.Deleteable(const sMethod: string; oObject: TAdvObject): boolean;
begin
  Alterable(sMethod);

  Result := True;
end;


function TAdvObjectList.Insertable(const sMethod: string; oObject: TAdvObject): boolean;
begin
  Alterable(sMethod);

  Result := True;
end;


function TAdvObjectList.Replaceable(const sMethod: string;
  oOld, oNew: TAdvObject): boolean;
begin
  Alterable(sMethod);
  ValidateItem(sMethod, oOld, 'oOld');
  ValidateItem(sMethod, oNew, 'oNew');

  Result := True;
end;


function TAdvObjectList.Extendable(const sMethod: string; iCount: integer): boolean;
begin
  Result := inherited Extendable(sMethod, iCount);

  if (iCount > Count) and not AllowUnassigned then
    Invariant(sMethod,
      'Unable to extend the Count of the list as it does not allow unassigned entries.');
end;


function TAdvObjectList.New: TAdvObject;
begin
  Result := ItemNew;
end;


procedure TAdvObjectList.FreezeChildren;
begin
  inherited;

  FreezeObjects;
end;


procedure TAdvObjectList.FreezeObjects;
var
  iLoop: integer;
  oObject: TAdvObject;
begin
  for iLoop := 0 to Count - 1 do
  begin
    oObject := ObjectByIndex[iLoop];

    if Assigned(oObject) then
      oObject.Freeze;
  end;
end;


procedure TAdvObjectList.InternalAfterInclude(iIndex: integer);
begin
end;


procedure TAdvObjectList.InternalBeforeExclude(iIndex: integer);
begin
end;


function TAdvObjectList.AllowUnassigned: boolean;
begin
  Result := True;
end;


function TAdvObjectList.IteratorClass: TAdvObjectListIteratorClass;
begin
  Result := TAdvObjectListIterator;
end;


procedure TAdvObjectList.ConsumeIterator(oIterator: TAdvObjectListIterator);
begin
  Assert(Invariants('ConsumeIterator', oIterator, IteratorClass, 'oIterator'));
  Assert(Condition(oIterator.List = Self, 'ConsumeIterator',
    'Iterator was not produced by this list.'));

  oIterator.Free;
end;


function TAdvObjectList.ProduceIterator: TAdvObjectListIterator;
begin
  Result := IteratorClass.Create;
  Result.List := Self.Link;
end;


function TAdvObjectList.GetObjectByIndex(const iIndex: integer): TAdvObject;
begin
  Result := GetObject(iIndex);
end;


procedure TAdvObjectList.SetObjectByIndex(const iIndex: integer;
  const Value: TAdvObject);
begin
  SetObject(iIndex, Value);
end;


constructor TAdvObjectListIterator.Create;
begin
  inherited;

  FList := nil;
end;


destructor TAdvObjectListIterator.Destroy;
begin
  FList.Free;

  inherited;
end;


procedure TAdvObjectListIterator.First;
begin
  inherited;

  FIndex := 0;
  FDeleted := False;

  StepNext;
end;


procedure TAdvObjectListIterator.Last;
begin
  inherited;

  FIndex := List.Count - 1;
  FDeleted := False;

  StepBack;
end;


procedure TAdvObjectListIterator.Back;
begin
  inherited;

  Dec(FIndex);
  StepBack;

  FDeleted := False;
end;


procedure TAdvObjectListIterator.Next;
begin
  inherited;

  if not FDeleted then
    Inc(FIndex);

  StepNext;

  FDeleted := False;
end;


function TAdvObjectListIterator.Current: TAdvObject;
begin
  Assert(Condition(not FDeleted, 'Current', 'Current element has been deleted'));

  Result := List[FIndex];
end;


function TAdvObjectListIterator.More: boolean;
begin
  Result := List.ExistsByIndex(FIndex);
end;


procedure TAdvObjectListIterator.Delete;
begin
  Assert(Condition(not FDeleted, 'Delete', 'Current element has already been deleted'));

  List.DeleteByIndex(FIndex);
  FDeleted := True;
end;


function TAdvObjectListIterator.GetList: TAdvObjectList;
begin
  Assert(Invariants('GetList', FList, TAdvObjectList, 'FList'));

  Result := FList;
end;


procedure TAdvObjectListIterator.SetList(const Value: TAdvObjectList);
begin
  Assert(not Assigned(FList) or Invariants('SetList', Value, TAdvObjectList, 'Value'));

  FList.Free;
  FList := Value;
end;


procedure TAdvObjectListIterator.StepBack;
begin
  while More and Skip do
    Dec(FIndex);
end;


procedure TAdvObjectListIterator.StepNext;
begin
  while More and Skip do
    Inc(FIndex);
end;


function TAdvObjectListIterator.Skip: boolean;
begin
  Result := False;
end;


function TAdvObjectList.ContainsAllByReference(oObjectList: TAdvObjectList): boolean;
var
  iObjectIndex: integer;
begin
  Result := True;
  iObjectIndex := 0;
  while (iObjectIndex < oObjectList.Count) and Result do
  begin
    Result := ExistsByReference(oObjectList[iObjectIndex]);
    Inc(iObjectIndex);
  end;
end;


function TAdvObjectList.ContainsAnyByReference(oObjectList: TAdvObjectList): boolean;
var
  iObjectIndex: integer;
begin
  Result := False;
  iObjectIndex := 0;
  while (iObjectIndex < oObjectList.Count) and not Result do
  begin
    Result := ExistsByReference(oObjectList[iObjectIndex]);
    Inc(iObjectIndex);
  end;
end;


end. // AdvObjectLists //
