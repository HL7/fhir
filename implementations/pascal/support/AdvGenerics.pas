unit AdvGenerics;

interface

uses
  Types, RTLConsts, Windows, SysUtils, Generics.Collections, Generics.Defaults,
  AdvObjects;

Type
  TAdvEnumerable<T : class> = class (TAdvObject)
  private
  {$HINTS OFF}
    function ToArrayImpl(Count: Integer): TArray<T>; // used by descendants
  {$HINTS ON}
  protected
    function DoGetEnumerator: TEnumerator<T>; virtual; abstract;
  public
    function GetEnumerator: TEnumerator<T>;
    function ToArray: TArray<T>; virtual;
  end;

  // Actually, T must be TAdvObject, but this doesn't work because of forwards class definitions
  TAdvList<T : class> = class (TAdvEnumerable<T>)
  private
  type
    arrayofT = array of T;
  var
    FItems: arrayofT;
    FCount: Integer;
    FComparer: IComparer<T>;
    FOnNotify: TCollectionNotifyEvent<T>;
    FArrayManager: TArrayManager<T>;

    function GetCapacity: Integer;
    procedure SetCapacity(Value: Integer);
    procedure SetCount(Value: Integer);
    function GetItem(Index: Integer): T;
    procedure SetItem(Index: Integer; const Value: T);
    procedure Grow(ACount: Integer);
    procedure GrowCheck(ACount: Integer); inline;
    procedure DoDelete(Index: Integer; Notification: TCollectionNotification);
  protected
    function DoGetEnumerator: TEnumerator<T>; override;
    procedure Notify(const Item: T; Action: TCollectionNotification); virtual;
  public
  type
    TDirection = System.Types.TDirection;
    TEmptyFunc = reference to function (const L, R: T): Boolean;
    TListCompareFunc = reference to function (const L, R: T): Integer;

    constructor Create; Overload; Override;
    constructor Create(const AComparer: IComparer<T>); overload;
    constructor Create(const Collection: TEnumerable<T>); overload;
    destructor Destroy; override;

    function link : TAdvList<t>; overload;

    class procedure Error(const Msg: string; Data: NativeInt); overload; virtual;
{$IFNDEF NEXTGEN}
    class procedure Error(Msg: PResStringRec; Data: NativeInt); overload;
{$ENDIF  NEXTGEN}

    function Add(const Value: T): Integer;

    procedure AddRange(const Values: array of T); overload;
    procedure AddRange(const Collection: IEnumerable<T>); overload;
    procedure AddRange(const Collection: TEnumerable<T>); overload;

    procedure Insert(Index: Integer; const Value: T);

    procedure InsertRange(Index: Integer; const Values: array of T); overload;
    procedure InsertRange(Index: Integer; const Collection: IEnumerable<T>); overload;
    procedure InsertRange(Index: Integer; const Collection: TEnumerable<T>); overload;

    procedure AddAll(list : TAdvList<T>);

    procedure Pack; overload;

    function Remove(const Value: T): Integer;
    function RemoveItem(const Value: T; Direction: TDirection): Integer;
    procedure Delete(Index: Integer);
    procedure DeleteRange(AIndex, ACount: Integer);
    function Extract(const Value: T): T;
    function ExtractItem(const Value: T; Direction: TDirection): T;

    procedure Exchange(Index1, Index2: Integer);
    procedure Move(CurIndex, NewIndex: Integer);

    function First: T;
    function Last: T;

    procedure Clear;

    function Expand: TAdvList<T>;

    function Contains(const Value: T): Boolean;
    function IndexOf(const Value: T): Integer;
    function IndexOfItem(const Value: T; Direction: TDirection): Integer;
    function LastIndexOf(const Value: T): Integer;

    procedure Reverse;

    procedure Sort; overload;
    procedure Sort(const AComparer: IComparer<T>); overload;
    function BinarySearch(const Item: T; out Index: Integer): Boolean; overload;
    function BinarySearch(const Item: T; out Index: Integer; const AComparer: IComparer<T>): Boolean; overload;

    procedure TrimExcess;

    function ToArray: TArray<T>; override; final;

    property Capacity: Integer read GetCapacity write SetCapacity;
    property Count: Integer read FCount write SetCount;
    property Items[Index: Integer]: T read GetItem write SetItem; default;
    property List: arrayofT read FItems;

    property OnNotify: TCollectionNotifyEvent<T> read FOnNotify write FOnNotify;

    type
      TAdvEnumerator = class(TEnumerator<T>)
      private
        FList: TAdvList<T>;
        FIndex: Integer;
        function GetCurrent: T;
      protected
        function DoGetCurrent: T; override;
        function DoMoveNext: Boolean; override;
      public
        constructor Create(const AList: TAdvList<T>);
        property Current: T read GetCurrent;
        function MoveNext: Boolean;
      end;

    function GetEnumerator: TAdvEnumerator; reintroduce;
  end;

  TAdvPair<T : TAdvObject> = record
    Key: String;
    Value: T;
    constructor Create(const AKey: String; const AValue: T);
  end;

  TAdvMap<T : TAdvObject> = class(TEnumerable<TAdvPair<T>>)
  private
    type
      TItem = record
        HashCode: Integer;
        Key: String;
        Value: T;
      end;
      TItemArray = array of TItem;
  private
    FItems: TItemArray;
    FCount: Integer;
    FGrowThreshold: Integer;

    procedure SetCapacity(ACapacity: Integer);
    procedure Rehash(NewCapPow2: Integer);
    procedure Grow;
    function GetBucketIndex(const Key: String; HashCode: Integer): Integer;
    function Hash(const Key: String): Integer;
    function GetItem(const Key: String): T;
    procedure SetItem(const Key: String; const Value: T);
    procedure RehashAdd(HashCode: Integer; const Key: String; const Value: T);
    procedure DoAdd(HashCode, Index: Integer; const Key: String; const Value: T);
    procedure DoSetValue(Index: Integer; const Value: T);
    function DoRemove(const Key: String; HashCode: Integer; Notification: TCollectionNotification): T;
    function InCircularRange(Bottom, Item, TopInc: Integer): Boolean;
  protected
    function DoGetEnumerator: TEnumerator<TAdvPair<T>>; override;
    procedure KeyNotify(const Key: String; Action: TCollectionNotification); virtual;
    procedure ValueNotify(const Value: T; Action: TCollectionNotification); virtual;
  public
    constructor Create(ACapacity: Integer = 0); overload;
    constructor Create(const Collection: TEnumerable<TAdvPair<T>>); overload;
    destructor Destroy; override;

    procedure Add(const Key: String; const Value: T);
    procedure Remove(const Key: String);
    procedure Clear;
    procedure TrimExcess;
    function TryGetValue(const Key: String; out Value: T): Boolean;
    procedure AddOrSetValue(const Key: String; const Value: T);
    function ContainsKey(const Key: String): Boolean;
    function ContainsValue(const Value: T): Boolean;
    function ToArray: TArray<TAdvPair<T>>; override; final;

    property Items[const Key: String]: T read GetItem write SetItem; default;
    property Count: Integer read FCount;

    type
      TAdvPairEnumerator = class(TEnumerator<TAdvPair<T>>)
      private
        FMap: TAdvMap<T>;
        FIndex: Integer;
        function GetCurrent: TAdvPair<T>;
      protected
        function DoGetCurrent: TAdvPair<T>; override;
        function DoMoveNext: Boolean; override;
      public
        constructor Create(const AMap: TAdvMap<T>);
        property Current: TAdvPair<T> read GetCurrent;
        function MoveNext: Boolean;
      end;

      TKeyEnumerator = class(TEnumerator<String>)
      private
        FMap: TAdvMap<T>;
        FIndex: Integer;
        function GetCurrent: String;
      protected
        function DoGetCurrent: String; override;
        function DoMoveNext: Boolean; override;
      public
        constructor Create(const AMap: TAdvMap<T>);
        property Current: String read GetCurrent;
        function MoveNext: Boolean;
      end;

      TValueEnumerator = class(TEnumerator<T>)
      private
        FMap: TAdvMap<T>;
        FIndex: Integer;
        function GetCurrent: T;
      protected
        function DoGetCurrent: T; override;
        function DoMoveNext: Boolean; override;
      public
        constructor Create(const AMap: TAdvMap<T>);
        property Current: T read GetCurrent;
        function MoveNext: Boolean;
      end;

      TValueCollection = class(TEnumerable<T>)
      private
        FMap: TAdvMap<T>;
        function GetCount: Integer;
      protected
        function DoGetEnumerator: TEnumerator<T>; override;
      public
        constructor Create(const AMap: TAdvMap<T>);
        function GetEnumerator: TValueEnumerator; reintroduce;
        function ToArray: TArray<T>; override; final;
        property Count: Integer read GetCount;
      end;

      TKeyCollection = class(TEnumerable<String>)
      private
        FMap: TAdvMap<T>;
        function GetCount: Integer;
      protected
        function DoGetEnumerator: TEnumerator<String>; override;
      public
        constructor Create(const AMap: TAdvMap<T>);
        function GetEnumerator: TKeyEnumerator; reintroduce;
        function ToArray: TArray<String>; override; final;
        property Count: Integer read GetCount;
      end;

  private
    FOnKeyNotify: TCollectionNotifyEvent<String>;
    FOnValueNotify: TCollectionNotifyEvent<T>;
    FKeyCollection: TKeyCollection;
    FValueCollection: TValueCollection;
    function GetKeys: TKeyCollection;
    function GetValues: TValueCollection;
  public
    function GetEnumerator: TAdvPairEnumerator; reintroduce;
    property Keys: TKeyCollection read GetKeys;
    property Values: TValueCollection read GetValues;
    property OnKeyNotify: TCollectionNotifyEvent<String> read FOnKeyNotify write FOnKeyNotify;
    property OnValueNotify: TCollectionNotifyEvent<T> read FOnValueNotify write FOnValueNotify;
  end;

  TAdvStringDictionary = class (TDictionary<String, String>)
  private
    FAdvObjectReferenceCount : TAdvReferenceCount;
  public
    // Cannot be virtual as they are allowed to be called from Nil or invalid objects (but will assert).
    Procedure Free; Overload;
    Function Link : TAdvStringDictionary; Overload;
  end;

implementation

{ TAdvEnumerable<T> }

function TAdvEnumerable<T>.GetEnumerator: TEnumerator<T>;
begin
  Result := DoGetEnumerator;
end;

function TAdvEnumerable<T>.ToArray: TArray<T>;
var
  buf: TAdvList<T>;
  x: T;
begin
  buf := TAdvList<T>.Create;
  try
    for x in Self do
      buf.Add(TAdvObject(x).Link);
    Result := buf.ToArray; // relies on TList<T>.ToArray override
  finally
    buf.Free;
  end;
end;

function TAdvEnumerable<T>.ToArrayImpl(Count: Integer): TArray<T>;
var
  x: T;
begin
  // We assume our caller has passed correct Count
  SetLength(Result, Count);
  Count := 0;
  for x in Self do
  begin
    Result[Count] := TAdvObject(x).Link;
    Inc(Count);
  end;
end;

{ TAdvList<T> }

function TAdvList<T>.GetCapacity: Integer;
begin
  Result := Length(FItems);
end;

procedure TAdvList<T>.SetCapacity(Value: Integer);
begin
  if Value < Count then
    Count := Value;
  SetLength(FItems, Value);
end;

procedure TAdvList<T>.SetCount(Value: Integer);
begin
  if Value < 0 then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);
  if Value > Capacity then
    SetCapacity(Value);
  if Value < Count then
    DeleteRange(Value, Count - Value);
  FCount := Value;
end;

function TAdvList<T>.GetItem(Index: Integer): T;
begin
  if (Index < 0) or (Index >= Count) then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);
  Result := FItems[Index];
end;

procedure TAdvList<T>.SetItem(Index: Integer; const Value: T);
var
  oldItem: T;
begin
  if (Index < 0) or (Index >= Count) then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);

  oldItem := FItems[Index];
  FItems[Index] := Value;
  try
    Notify(oldItem, cnRemoved);
    Notify(Value, cnAdded);
  finally
    oldItem.free;
  end;
end;

procedure TAdvList<T>.Grow(ACount: Integer);
var
  newCount: Integer;
begin
  newCount := Length(FItems);
  if newCount = 0 then
    newCount := ACount
  else
    repeat
      newCount := newCount * 2;
      if newCount < 0 then
        OutOfMemoryError;
    until newCount >= ACount;
  Capacity := newCount;
end;

procedure TAdvList<T>.GrowCheck(ACount: Integer);
begin
  if ACount > Length(FItems) then
    Grow(ACount)
  else if ACount < 0 then
    OutOfMemoryError;
end;

procedure TAdvList<T>.Notify(const Item: T; Action: TCollectionNotification);
begin
  if Assigned(FOnNotify) then
    FOnNotify(Self, Item, Action);
end;

procedure TAdvList<T>.Pack;
var
  PackedCount : Integer;
  StartIndex : Integer;
  EndIndex : Integer;
begin
  if FCount = 0 then
    Exit;

  PackedCount := 0;
  StartIndex := 0;
  repeat
    // Locate the first/next non-nil element in the list
//    while (StartIndex < FCount) and (FComparer.Compare(FItems[StartIndex], Default(T)) = 0) do
    while (StartIndex < FCount) and (FItems[StartIndex] = nil) do
      Inc(StartIndex);

    if StartIndex < FCount then // There is nothing more to do
    begin
      // Locate the next nil pointer
      EndIndex := StartIndex;
//      while (EndIndex < FCount) and (FComparer.Compare(FItems[EndIndex], Default(T)) <> 0) do
      while (EndIndex < FCount) and (FItems[EndIndex] <> nil) do
        Inc(EndIndex);
      Dec(EndIndex);

      // Move this block of non-null items to the index recorded in PackedToCount:
      // If this is a contiguous non-nil block at the start of the list then
      // StartIndex and PackedToCount will be equal (and 0) so don't bother with the move.
      if StartIndex > PackedCount then
        FArrayManager.Move(FItems, StartIndex, PackedCount, EndIndex - StartIndex + 1);

      // Set the PackedToCount to reflect the number of items in the list
      // that have now been packed.
      Inc(PackedCount, EndIndex - StartIndex + 1);

      // Reset StartIndex to the element following EndIndex
      StartIndex := EndIndex + 1;
    end;
  until StartIndex >= FCount;

  // Set Count so that the 'free' item
  FCount := PackedCount;
end;

constructor TAdvList<T>.Create;
begin
  Create(TComparer<T>.Default);
end;

constructor TAdvList<T>.Create(const AComparer: IComparer<T>);
begin
  inherited Create;
  FArrayManager := TMoveArrayManager<T>.Create;
  FComparer := AComparer;
  if FComparer = nil then
    FComparer := TComparer<T>.Default;
end;

constructor TAdvList<T>.Create(const Collection: TEnumerable<T>);
begin
  inherited Create;
  FArrayManager := TMoveArrayManager<T>.Create;
  FComparer := TComparer<T>.Default;
  InsertRange(0, Collection);
end;

destructor TAdvList<T>.Destroy;
begin
  Capacity := 0;
  FArrayManager.Free;
  inherited;
end;

class procedure TAdvList<T>.Error(const Msg: string; Data: NativeInt);
begin
  raise EListError.CreateFmt(Msg, [Data]) at ReturnAddress;
end;

class procedure TAdvList<T>.Error(Msg: PResStringRec; Data: NativeInt);
begin
  raise EListError.CreateFmt(LoadResString(Msg), [Data]) at ReturnAddress;
end;

function TAdvList<T>.DoGetEnumerator: TEnumerator<T>;
begin
  Result := GetEnumerator;
end;

function TAdvList<T>.Add(const Value: T): Integer;
begin
  GrowCheck(Count + 1);
  Result := Count;
  FItems[Count] := Value; // .link - no link here - the link has to be external, because the consumer of the list has to decide that the list owns the object
  Inc(FCount);
  Notify(Value, cnAdded);
end;

procedure TAdvList<T>.AddRange(const Values: array of T);
begin
  InsertRange(Count, Values);
end;

procedure TAdvList<T>.AddRange(const Collection: IEnumerable<T>);
begin
  InsertRange(Count, Collection);
end;

procedure TAdvList<T>.AddAll(list: TAdvList<T>);
var
  item: T;
begin
  for item in list do
    Add(TAdvObject(item).link); // yes we link here too
end;

procedure TAdvList<T>.AddRange(const Collection: TEnumerable<T>);
begin
  InsertRange(Count, Collection);
end;

function TAdvList<T>.BinarySearch(const Item: T; out Index: Integer): Boolean;
begin
  Result := TArray.BinarySearch<T>(FItems, Item, Index, FComparer, 0, Count);
end;

function TAdvList<T>.BinarySearch(const Item: T; out Index: Integer;
  const AComparer: IComparer<T>): Boolean;
begin
  Result := TArray.BinarySearch<T>(FItems, Item, Index, AComparer, 0, Count);
end;

procedure TAdvList<T>.Insert(Index: Integer; const Value: T);
begin
  if (Index < 0) or (Index > Count) then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);

  GrowCheck(Count + 1);
  if Index <> Count then
  begin
    FArrayManager.Move(FItems, Index, Index + 1, Count - Index);
    FArrayManager.Finalize(FItems, Index, 1);
  end;
  FItems[Index] := Value; // .link - no, see above
  Inc(FCount);
  Notify(Value, cnAdded);
end;

procedure TAdvList<T>.InsertRange(Index: Integer; const Values: array of T);
var
  I: Integer;
begin
  if (Index < 0) or (Index > Count) then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);

  GrowCheck(Count + Length(Values));
  if Index <> Count then
  begin
    FArrayManager.Move(FItems, Index, Index + Length(Values), Count - Index);
    FArrayManager.Finalize(FItems, Index, Length(Values));
  end;

  for I := 0 to Length(Values) - 1 do
    FItems[Index + I] := TAdvObject(Values[I]).Link; // yes, here we link. This means that the user cannot construct an array of objects and link them assuming this will respect that

  Inc(FCount, Length(Values));

  for I := 0 to Length(Values) - 1 do
    Notify(Values[I], cnAdded);
end;

procedure TAdvList<T>.InsertRange(Index: Integer; const Collection: IEnumerable<T>);
var
  item: T;
begin
  for item in Collection do
  begin
    Insert(Index, TAdvObject(item).link); // yes we link here too
    Inc(Index);
  end;
end;

procedure TAdvList<T>.InsertRange(Index: Integer; const Collection: TEnumerable<T>);
var
  item: T;
begin
  for item in Collection do
  begin
    Insert(Index, TAdvObject(item).Link);
    Inc(Index);
  end;
end;

procedure TAdvList<T>.Exchange(Index1, Index2: Integer);
var
  temp: T;
begin
  temp := FItems[Index1];
  FItems[Index1] := FItems[Index2];
  FItems[Index2] := temp;
end;

function TAdvList<T>.Extract(const Value: T): T;
begin
  Result := ExtractItem(Value, TDirection.FromBeginning);
end;

function TAdvList<T>.ExtractItem(const Value: T; Direction: TDirection): T;
var
  index: Integer;
begin
  index := IndexOfItem(Value, Direction);
  if index < 0 then
    Result := nil
  else
  begin
    Result := FItems[index];
    DoDelete(index, cnExtracted);
  end;
end;

function TAdvList<T>.First: T;
begin
  Result := Items[0];
end;

function TAdvList<T>.Remove(const Value: T): Integer;
begin
  Result := IndexOf(Value);
  if Result >= 0 then
    Delete(Result);
end;

function TAdvList<T>.RemoveItem(const Value: T; Direction: TDirection): Integer;
begin
  Result := IndexOfItem(Value, Direction);
  if Result >= 0 then
    Delete(Result);
end;

procedure TAdvList<T>.DoDelete(Index: Integer; Notification: TCollectionNotification);
var
  oldItem: T;
begin
  if (Index < 0) or (Index >= Count) then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);
  oldItem := FItems[Index];
  FItems[Index] := Default(T);
  Dec(FCount);
  if Index <> Count then
  begin
    FArrayManager.Move(FItems, Index + 1, Index, Count - Index);
    FArrayManager.Finalize(FItems, Count, 1);
  end;
  try
    Notify(oldItem, Notification);
  finally
    oldItem.free;
  end;
end;

procedure TAdvList<T>.Delete(Index: Integer);
begin
  DoDelete(Index, cnRemoved);
end;

procedure TAdvList<T>.DeleteRange(AIndex, ACount: Integer);
var
  oldItems: array of T;
  tailCount, I: Integer;
begin
  if (AIndex < 0) or (ACount < 0) or (AIndex + ACount > Count)
    or (AIndex + ACount < 0) then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);
  if ACount = 0 then
    Exit;

  SetLength(oldItems, ACount);
  FArrayManager.Move(FItems, oldItems, AIndex, 0, ACount);
  try
    tailCount := Count - (AIndex + ACount);
    if tailCount > 0 then
    begin
      FArrayManager.Move(FItems, AIndex + ACount, AIndex, tailCount);
      FArrayManager.Finalize(FItems, Count - ACount, ACount);
    end else
      FArrayManager.Finalize(FItems, AIndex, ACount);

    Dec(FCount, ACount);

    for I := 0 to Length(oldItems) - 1 do
      Notify(oldItems[I], cnRemoved);
  finally
    for I := 0 to Length(oldItems) - 1 do
      oldItems[I].free;
  end;
end;

procedure TAdvList<T>.Clear;
begin
  Count := 0;
  Capacity := 0;
end;

function TAdvList<T>.Expand: TAdvList<T>;
begin
  if FCount = Length(FItems) then
    GrowCheck(FCount + 1);
  Result := Self;
end;

function TAdvList<T>.Contains(const Value: T): Boolean;
begin
  Result := IndexOf(Value) >= 0;
end;

function TAdvList<T>.IndexOf(const Value: T): Integer;
var
  i: Integer;
begin
  for i := 0 to Count - 1 do
    if FComparer.Compare(FItems[i], Value) = 0 then
      Exit(i);
  Result := -1;
end;

function TAdvList<T>.IndexOfItem(const Value: T; Direction: TDirection): Integer;
var
  P: T;
  i: Integer;
begin
  if Direction = TDirection.FromBeginning then
    Result := IndexOf(Value)
  else
  begin
    if Count > 0 then
    begin
      for i := Count - 1 downto 0 do
        if FComparer.Compare(FItems[i], Value) = 0 then
          Exit(i);
    end;
    Result := -1;
  end;
end;

function TAdvList<T>.Last: T;
begin
  Result := Items[Count - 1];
end;

function TAdvList<T>.LastIndexOf(const Value: T): Integer;
var
  i: Integer;
begin
  for i := Count - 1 downto 0 do
    if FComparer.Compare(FItems[i], Value) = 0 then
      Exit(i);
  Result := -1;
end;

function TAdvList<T>.link: TAdvList<t>;
begin
  result := TAdvList<T>(inherited Link);
end;

procedure TAdvList<T>.Move(CurIndex, NewIndex: Integer);
var
  temp: T;
begin
  if CurIndex = NewIndex then
    Exit;
  if (NewIndex < 0) or (NewIndex >= FCount) then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);

  temp := FItems[CurIndex];
  FItems[CurIndex] := Default(T);
  if CurIndex < NewIndex then
    FArrayManager.Move(FItems, CurIndex + 1, CurIndex, NewIndex - CurIndex)
  else
    FArrayManager.Move(FItems, NewIndex, NewIndex + 1, CurIndex - NewIndex);

  FArrayManager.Finalize(FItems, NewIndex, 1);
  FItems[NewIndex] := temp;
end;

procedure TAdvList<T>.Reverse;
var
  tmp: T;
  b, e: Integer;
begin
  b := 0;
  e := Count - 1;
  while b < e do
  begin
    tmp := FItems[b];
    FItems[b] := FItems[e];
    FItems[e] := tmp;
    Inc(b);
    Dec(e);
  end;
end;

procedure TAdvList<T>.Sort;
begin
  TArray.Sort<T>(FItems, FComparer, 0, Count);
end;

procedure TAdvList<T>.Sort(const AComparer: IComparer<T>);
begin
  TArray.Sort<T>(FItems, AComparer, 0, Count);
end;

// no ownership on the array - it cannot be kept alive after the list is freed
function TAdvList<T>.ToArray: TArray<T>;
var
  i: Integer;
begin
  SetLength(Result, Count);
  for i := 0 to Count - 1 do
    Result[i] := Items[i];
end;

procedure TAdvList<T>.TrimExcess;
begin
  Capacity := Count;
end;

function TAdvList<T>.GetEnumerator: TAdvEnumerator;
begin
  Result := TAdvEnumerator.Create(Self);
end;

{ TAdvList<T>.TAdvEnumerator }

constructor TAdvList<T>.TAdvEnumerator.Create(const AList: TAdvList<T>);
begin
  inherited Create;
  FList := AList;
  FIndex := -1;
end;

function TAdvList<T>.TAdvEnumerator.DoGetCurrent: T;
begin
  Result := GetCurrent;
end;

function TAdvList<T>.TAdvEnumerator.DoMoveNext: Boolean;
begin
  Result := MoveNext;
end;

function TAdvList<T>.TAdvEnumerator.GetCurrent: T;
begin
  Result := FList[FIndex];
end;

function TAdvList<T>.TAdvEnumerator.MoveNext: Boolean;
begin
  if FIndex >= FList.Count then
    Exit(False);
  Inc(FIndex);
  Result := FIndex < FList.Count;
end;

{ TAdvPair<T> }

constructor TAdvPair<T>.Create(const AKey: String; const AValue: T);
begin
  Key := AKey;
  Value := AValue;
end;

{ TAdvMap<T> }
const
  EMPTY_HASH = -1;

procedure TAdvMap<T>.Rehash(NewCapPow2: Integer);
var
  oldItems, newItems: TItemArray;
  i: Integer;
begin
  if NewCapPow2 = Length(FItems) then
    Exit
  else if NewCapPow2 < 0 then
    OutOfMemoryError;

  oldItems := FItems;
  SetLength(newItems, NewCapPow2);
  for i := 0 to Length(newItems) - 1 do
    newItems[i].HashCode := EMPTY_HASH;
  FItems := newItems;
  FGrowThreshold := NewCapPow2 shr 1 + NewCapPow2 shr 2; // 75%

  for i := 0 to Length(oldItems) - 1 do
    if oldItems[i].HashCode <> EMPTY_HASH then
      RehashAdd(oldItems[i].HashCode, oldItems[i].Key, oldItems[i].Value);
end;

procedure TAdvMap<T>.SetCapacity(ACapacity: Integer);
var
  newCap: Integer;
begin
  if ACapacity < Count then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);

  if ACapacity = 0 then
    Rehash(0)
  else
  begin
    newCap := 4;
    while newCap < ACapacity do
      newCap := newCap shl 1;
    Rehash(newCap);
  end
end;

procedure TAdvMap<T>.Grow;
var
  newCap: Integer;
begin
  newCap := Length(FItems) * 2;
  if newCap = 0 then
    newCap := 4;
  Rehash(newCap);
end;

function TAdvMap<T>.GetBucketIndex(const Key: String; HashCode: Integer): Integer;
var
  start, hc: Integer;
begin
  if Length(FItems) = 0 then
    Exit(not High(Integer));

  start := HashCode and (Length(FItems) - 1);
  Result := start;
  while True do
  begin
    hc := FItems[Result].HashCode;

    // Not found: return complement of insertion point.
    if hc = EMPTY_HASH then
      Exit(not Result);

    // Found: return location.
    if (hc = HashCode) and (FItems[Result].Key = Key) then
      Exit(Result);

    Inc(Result);
    if Result >= Length(FItems) then
      Result := 0;
  end;
end;

function TAdvMap<T>.Hash(const Key: String): Integer;
var
  LResult: UInt32;
  I: Integer;
begin
  LResult := 0;
  for I := 0 to Key.Length - 1 do
  begin
    LResult := (LResult shl 5) or (LResult shr 27); //ROL Result, 5
    LResult := LResult xor UInt32(Key[I]);
  end;
  Result := LResult
end;

function TAdvMap<T>.GetItem(const Key: String): T;
var
  index: Integer;
begin
  index := GetBucketIndex(Key, Hash(Key));
  if index < 0 then
    raise EListError.CreateRes(@SGenericItemNotFound);
  Result := FItems[index].Value;
end;

procedure TAdvMap<T>.SetItem(const Key: String; const Value: T);
var
  index: Integer;
  oldValue: T;
begin
  index := GetBucketIndex(Key, Hash(Key));
  if index < 0 then
    raise EListError.CreateRes(@SGenericItemNotFound);

  oldValue := FItems[index].Value;
  FItems[index].Value := Value;
  try
    ValueNotify(oldValue, cnRemoved);
    ValueNotify(Value, cnAdded);
  finally
    oldValue.free;
  end;
end;

procedure TAdvMap<T>.RehashAdd(HashCode: Integer; const Key: String; const Value: T);
var
  index: Integer;
begin
  index := not GetBucketIndex(Key, HashCode);
  FItems[index].HashCode := HashCode;
  FItems[index].Key := Key;
  FItems[index].Value := Value;
end;

procedure TAdvMap<T>.KeyNotify(const Key: String; Action: TCollectionNotification);
begin
  if Assigned(FOnKeyNotify) then
    FOnKeyNotify(Self, Key, Action);
end;

procedure TAdvMap<T>.ValueNotify(const Value: T; Action: TCollectionNotification);
begin
  if Assigned(FOnValueNotify) then
    FOnValueNotify(Self, Value, Action);
end;

constructor TAdvMap<T>.Create(ACapacity: Integer = 0);
begin
  inherited Create;
  if ACapacity < 0 then
    raise EArgumentOutOfRangeException.CreateRes(@SArgumentOutOfRange);
  SetCapacity(ACapacity);
end;

constructor TAdvMap<T>.Create(const Collection: TEnumerable<TAdvPair<T>>);
var
  item: TAdvPair<T>;
begin
  Create(0);
  for item in Collection do
    AddOrSetValue(item.Key, item.Value);
end;

destructor TAdvMap<T>.Destroy;
begin
  Clear;
  FKeyCollection.Free;
  FValueCollection.Free;
  inherited;
end;

procedure TAdvMap<T>.Add(const Key: String; const Value: T);
var
  index, hc: Integer;
begin
  if Count >= FGrowThreshold then
    Grow;

  hc := Hash(Key);
  index := GetBucketIndex(Key, hc);
  if index >= 0 then
    raise EListError.CreateRes(@SGenericDuplicateItem);

  DoAdd(hc, not index, Key, Value);
end;

function TAdvMap<T>.InCircularRange(Bottom, Item, TopInc: Integer): Boolean;
begin
  Result := (Bottom < Item) and (Item <= TopInc) // normal
    or (TopInc < Bottom) and (Item > Bottom) // top wrapped
    or (TopInc < Bottom) and (Item <= TopInc) // top and item wrapped
end;

function TAdvMap<T>.DoRemove(const Key: String; HashCode: Integer; Notification: TCollectionNotification): T;
var
  gap, index, hc, bucket: Integer;
begin
  index := GetBucketIndex(Key, HashCode);
  if index < 0 then
    Exit(Default(T));

  // Removing item from linear probe hash table is moderately
  // tricky. We need to fill in gaps, which will involve moving items
  // which may not even hash to the same location.
  // Knuth covers it well enough in Vol III. 6.4.; but beware, Algorithm R
  // (2nd ed) has a bug: step R4 should go to step R1, not R2 (already errata'd).
  // My version does linear probing forward, not backward, however.

  // gap refers to the hole that needs filling-in by shifting items down.
  // index searches for items that have been probed out of their slot,
  // but being careful not to move items if their bucket is between
  // our gap and our index (so that they'd be moved before their bucket).
  // We move the item at index into the gap, whereupon the new gap is
  // at the index. If the index hits a hole, then we're done.

  // If our load factor was exactly 1, we'll need to hit this hole
  // in order to terminate. Shouldn't normally be necessary, though.
  FItems[index].HashCode := EMPTY_HASH;
  Result := FItems[index].Value;

  gap := index;
  while True do
  begin
    Inc(index);
    if index = Length(FItems) then
      index := 0;

    hc := FItems[index].HashCode;
    if hc = EMPTY_HASH then
      Break;

    bucket := hc and (Length(FItems) - 1);
    if not InCircularRange(gap, bucket, index) then
    begin
      FItems[gap] := FItems[index];
      gap := index;
      // The gap moved, but we still need to find it to terminate.
      FItems[gap].HashCode := EMPTY_HASH;
    end;
  end;

  FItems[gap].HashCode := EMPTY_HASH;
  FItems[gap].Key := Default(String);
  FItems[gap].Value := Default(T);
  Dec(FCount);

  KeyNotify(Key, Notification);
  ValueNotify(Result, Notification);
end;

procedure TAdvMap<T>.Remove(const Key: String);
begin
  DoRemove(Key, Hash(Key), cnRemoved).Free;
end;

procedure TAdvMap<T>.Clear;
var
  i: Integer;
  oldItems: TItemArray;
begin
  oldItems := FItems;
  FCount := 0;
  SetLength(FItems, 0);
  SetCapacity(0);
  FGrowThreshold := 0;

  for i := 0 to Length(oldItems) - 1 do
  begin
    if oldItems[i].HashCode = EMPTY_HASH then
      Continue;
    KeyNotify(oldItems[i].Key, cnRemoved);
    ValueNotify(oldItems[i].Value, cnRemoved);
    oldItems[i].Value.free;
  end;
end;

function TAdvMap<T>.ToArray: TArray<TAdvPair<T>>;
begin
  Result := ToArrayImpl(Count);
end;

procedure TAdvMap<T>.TrimExcess;
begin
  // Ensure at least one empty slot for GetBucketIndex to terminate.
  SetCapacity(Count + 1);
end;

function TAdvMap<T>.TryGetValue(const Key: String; out Value: T): Boolean;
var
  index: Integer;
begin
  index := GetBucketIndex(Key, Hash(Key));
  Result := index >= 0;
  if Result then
    Value := FItems[index].Value
  else
    Value := nil;
end;

procedure TAdvMap<T>.DoAdd(HashCode, Index: Integer; const Key: String; const Value: T);
begin
  FItems[Index].HashCode := HashCode;
  FItems[Index].Key := Key;
  FItems[Index].Value := Value;
  Inc(FCount);

  KeyNotify(Key, cnAdded);
  ValueNotify(Value, cnAdded);
end;

function TAdvMap<T>.DoGetEnumerator: TEnumerator<TAdvPair<T>>;
begin
  Result := GetEnumerator;
end;

procedure TAdvMap<T>.DoSetValue(Index: Integer; const Value: T);
var
  oldValue: T;
begin
  oldValue := FItems[Index].Value;
  FItems[Index].Value := Value;

  ValueNotify(oldValue, cnRemoved);
  ValueNotify(Value, cnAdded);
  oldValue.Free;
end;

procedure TAdvMap<T>.AddOrSetValue(const Key: String; const Value: T);
var
  hc: Integer;
  index: Integer;
begin
  if Count >= FGrowThreshold then
    Grow;
  hc := Hash(Key);
  index := GetBucketIndex(Key, hc);
  if index >= 0 then
    DoSetValue(index, Value)
  else
    DoAdd(hc, not index, Key, Value);
end;

function TAdvMap<T>.ContainsKey(const Key: String): Boolean;
begin
  Result := GetBucketIndex(Key, Hash(Key)) >= 0;
end;

function TAdvMap<T>.ContainsValue(const Value: T): Boolean;
var
  i: Integer;
begin
  for i := 0 to Length(FItems) - 1 do
    if (FItems[i].HashCode <> EMPTY_HASH) and (FItems[i].Value = Value) then
      Exit(True);
  Result := False;
end;

function TAdvMap<T>.GetEnumerator: TAdvPairEnumerator;
begin
  Result := TAdvPairEnumerator.Create(Self);
end;

function TAdvMap<T>.GetKeys: TKeyCollection;
begin
  if FKeyCollection = nil then
    FKeyCollection := TKeyCollection.Create(Self);
  Result := FKeyCollection;
end;

function TAdvMap<T>.GetValues: TValueCollection;
begin
  if FValueCollection = nil then
    FValueCollection := TValueCollection.Create(Self);
  Result := FValueCollection;
end;

// Pairs

constructor TAdvMap<T>.TAdvPairEnumerator.Create(const AMap: TAdvMap<T>);
begin
  inherited Create;
  FIndex := -1;
  FMap := AMap;
end;

function TAdvMap<T>.TAdvPairEnumerator.DoGetCurrent: TAdvPair<T>;
begin
  Result := GetCurrent;
end;

function TAdvMap<T>.TAdvPairEnumerator.DoMoveNext: Boolean;
begin
  Result := MoveNext;
end;

function TAdvMap<T>.TAdvPairEnumerator.GetCurrent: TAdvPair<T>;
begin
  Result.Key := FMap.FItems[FIndex].Key;
  Result.Value := FMap.FItems[FIndex].Value;
end;

function TAdvMap<T>.TAdvPairEnumerator.MoveNext: Boolean;
begin
  while FIndex < Length(FMap.FItems) - 1 do
  begin
    Inc(FIndex);
    if FMap.FItems[FIndex].HashCode <> EMPTY_HASH then
      Exit(True);
  end;
  Result := False;
end;

// Keys

constructor TAdvMap<T>.TKeyEnumerator.Create(const AMap : TAdvMap<T>);
begin
  inherited Create;
  FIndex := -1;
  FMap := AMap;
end;

function TAdvMap<T>.TKeyEnumerator.DoGetCurrent: String;
begin
  Result := GetCurrent;
end;

function TAdvMap<T>.TKeyEnumerator.DoMoveNext: Boolean;
begin
  Result := MoveNext;
end;

function TAdvMap<T>.TKeyEnumerator.GetCurrent: String;
begin
  Result := FMap.FItems[FIndex].Key;
end;

function TAdvMap<T>.TKeyEnumerator.MoveNext: Boolean;
begin
  while FIndex < Length(FMap.FItems) - 1 do
  begin
    Inc(FIndex);
    if FMap.FItems[FIndex].HashCode <> EMPTY_HASH then
      Exit(True);
  end;
  Result := False;
end;

// Values

constructor TAdvMap<T>.TValueEnumerator.Create(const AMap : TAdvMap<T>);
begin
  inherited Create;
  FIndex := -1;
  FMap := AMap;
end;

function TAdvMap<T>.TValueEnumerator.DoGetCurrent: T;
begin
  Result := GetCurrent;
end;

function TAdvMap<T>.TValueEnumerator.DoMoveNext: Boolean;
begin
  Result := MoveNext;
end;

function TAdvMap<T>.TValueEnumerator.GetCurrent: T;
begin
  Result := FMap.FItems[FIndex].Value;
end;

function TAdvMap<T>.TValueEnumerator.MoveNext: Boolean;
begin
  while FIndex < Length(FMap.FItems) - 1 do
  begin
    Inc(FIndex);
    if FMap.FItems[FIndex].HashCode <> EMPTY_HASH then
      Exit(True);
  end;
  Result := False;
end;

{ TAdvMap<T>.TValueCollection }

constructor TAdvMap<T>.TValueCollection.Create(const AMap : TAdvMap<T>);
begin
  inherited Create;
  FMap := AMap;
end;

function TAdvMap<T>.TValueCollection.DoGetEnumerator: TEnumerator<T>;
begin
  Result := GetEnumerator;
end;

function TAdvMap<T>.TValueCollection.GetCount: Integer;
begin
  Result := FMap.Count;
end;

function TAdvMap<T>.TValueCollection.GetEnumerator: TValueEnumerator;
begin
  Result := TValueEnumerator.Create(FMap);
end;

function TAdvMap<T>.TValueCollection.ToArray: TArray<T>;
begin
  Result := ToArrayImpl(FMap.Count);
end;

{ TAdvMap<T>.TKeyCollection }

constructor TAdvMap<T>.TKeyCollection.Create(const AMap : TAdvMap<T>);
begin
  inherited Create;
  FMap := AMap;
end;

function TAdvMap<T>.TKeyCollection.DoGetEnumerator: TEnumerator<String>;
begin
  Result := GetEnumerator;
end;

function TAdvMap<T>.TKeyCollection.GetCount: Integer;
begin
  Result := FMap.Count;
end;

function TAdvMap<T>.TKeyCollection.GetEnumerator: TKeyEnumerator;
begin
  Result := TKeyEnumerator.Create(FMap);
end;

function TAdvMap<T>.TKeyCollection.ToArray: TArray<String>;
begin
  Result := ToArrayImpl(FMap.Count);
end;


{ TAdvStringDictionary }

procedure TAdvStringDictionary.Free;
begin
  If Assigned(Self) and (InterlockedDecrement(FAdvObjectReferenceCount) < 0) Then
    Destroy;
end;

function TAdvStringDictionary.Link: TAdvStringDictionary;
begin
  Result := Self;
  If Assigned(Self) Then
    InterlockedIncrement(FAdvObjectReferenceCount);
end;

end.

