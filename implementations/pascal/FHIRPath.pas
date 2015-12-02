unit FHIRPath;

interface

uses
  SysUtils, Math, RegExpr, Generics.Collections, Character,
  StringSupport,
  AdvObjects, AdvGenerics,
  XmlBuilder,

  FHIRBase, FHIRTypes, FHIRResources, FHIRUtilities, FHIRProfileUtilities;

type
  EFHIRPath = class (Exception)
  public
     constructor create(path : String; offset : integer; problem : String);
  end;

  TFHIRPathExecutionContext = class (TAdvObject)
  private
    FAppInfo : TAdvObject;
    FResource : TFHIRResource;
    FContext : TFHIRBase;
  public
    Constructor Create(appInfo : TAdvObject; resource : TFHIRResource; context : TFHIRBase);
    destructor Destroy; override;
    function Link : TFHIRPathExecutionContext; overload;
    property appInfo : TAdvObject read FappInfo;
    property resource : TFHIRResource read FResource;
    property context : TFHIRBase read Fcontext;
  end;

  TFHIRPathExecutionTypeContext = class (TAdvObject)
  private
    FAppInfo : TAdvObject;
    FResourceType : String;
    FContext : String;
  public
    Constructor Create(appInfo : TAdvObject; resourceType, context : String);
    destructor Destroy; override;
    property appInfo : TAdvObject read FappInfo;
    property resourceType : String read FResourceType;
    property context : String read Fcontext;
  end;


  TFHIRPathLexer = class (TAdvObject)
  private
    FPath : String;
    FCursor : integer;
    FCurrentLocation : TSourceLocation;
    FCurrent : String;
    FCurrentStart : integer;
    FCurrentStartLocation : TSourceLocation;
    FId : integer;
    function replaceFixedConstant(const s : String) : String;
  public
    constructor Create(path : String); overload;
    destructor Destroy; override;
    procedure next;
    property current : String read FCurrent;
    property CurrentStart : integer read FCurrentStart;
    function done : boolean;
    function take : String;

    function nextId : integer;
    function error(msg : String) : Exception; overload;
    function error(msg : String; offset : integer) : Exception; overload;
    function isConstant : boolean;
    function isToken : boolean;
    function isOp : boolean;
  end;

  TFHIRPathDebugPackage = class (TAdvObject)
  private
    FSourceEnd: TSourceLocation;
    Fcontext: TFHIRPathExecutionContext;
    Finput2: TFHIRBaseList;
    Finput1: TFHIRBaseList;
    FExpression: TFHIRExpressionNode;
    FSourceStart: TSourceLocation;
    Foutcome: TFHIRBaseList;
    FIsOperation: boolean;
    procedure Setcontext(const Value: TFHIRPathExecutionContext);
    procedure SetExpression(const Value: TFHIRExpressionNode);
    procedure Setinput1(const Value: TFHIRBaseList);
    procedure Setinput2(const Value: TFHIRBaseList);
    procedure Setoutcome(const Value: TFHIRBaseList);
  public
    destructor destroy; override;
    function Link : TFHIRPathDebugPackage; overload;
    property SourceStart : TSourceLocation read FSourceStart write FSourceStart;
    property SourceEnd : TSourceLocation read FSourceEnd write FSourceEnd;
    property Expression : TFHIRExpressionNode read FExpression write SetExpression;
    property IsOperation : boolean read FIsOperation write FIsOperation;
    property context : TFHIRPathExecutionContext read Fcontext write Setcontext;
    property input1 : TFHIRBaseList read Finput1 write Setinput1;
    property input2 : TFHIRBaseList read Finput2 write Setinput2;
    property outcome : TFHIRBaseList read Foutcome write Setoutcome;
  end;

  TFHIRPathEvaluator = class;

  TFHIRPathDebugEvent = procedure (source : TFHIRPathEvaluator; package : TFHIRPathDebugPackage) of object;

  TFHIRPathEvaluator = class (TAdvObject)
  private
    worker : TValidatorServiceProvider;
    FOndebug : TFHIRPathDebugEvent;
    function parseExpression(lexer: TFHIRPathLexer; proximal : boolean): TFHIRExpressionNode;
    procedure checkParameters(lexer : TFHIRPathLexer; offset : Integer; exp : TFHIRExpressionNode);

    function execute(ctxt : TFHIRPathExecutionContext; focus : TFHIRBaseList; exp : TFHIRExpressionNode; atEntry : boolean) : TFHIRBaseList; overload;
    function execute(ctxt : TFHIRPathExecutionContext; item : TFHIRBase; exp : TFHIRExpressionNode; atEntry : boolean) : TFHIRBaseList; overload;
    procedure debug(ctxt : TFHIRPathExecutionContext; exp : TFHIRExpressionNode; op : boolean; input1, input2, outcome : TFHIRBaseList);

    function evaluateFunction(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
    function preOperate(left : TFHIRBaseList; op : TFHIRPathOperation) : TFHIRBaseList;
    function operate(left : TFHIRBaseList; op : TFHIRPathOperation; right : TFHIRBaseList) : TFHIRBaseList;
    function readConstant(constant : String) : TFHIRType;
    procedure ListAllChildren(item: TFHIRBase; results: TFHIRBaseList; recurse: boolean);

    function executeType(ctxt: TFHIRPathExecutionTypeContext; focus: TAdvStringSet; exp: TFHIRExpressionNode; atEntry : boolean) : TAdvStringSet; overload;
    function executeType(focus: String; exp: TFHIRExpressionNode; atEntry : boolean) : TAdvStringSet; overload;
    function evaluateFunctionType(ctxt: TFHIRPathExecutionTypeContext; context: TAdvStringSet; exp: TFHIRExpressionNode): TAdvStringSet;
    function operateTypes(left : TAdvStringSet; op : TFHIRPathOperation; right : TAdvStringSet) : TAdvStringSet;
    function readConstantType(appInfo : TAdvObject; constant : String) : string;

    procedure ListChildTypesByName(item, name : string; result : TAdvStringSet);
    function getElementDefinition(sd : TFHIRStructureDefinition; path : String; var specifiedType : String) : TFHIRElementDefinition;
    function getElementDefinitionByName(sd : TFHIRStructureDefinition; name : String) : TFHIRElementDefinition;
    function hasDataType(ed : TFhirElementDefinition) : boolean;
    function primitives(collection : TAdvStringSet) : TAdvStringSet;
    function isPrimitiveType(s : String) : boolean;

    function funcEmpty(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcItem(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcWhere(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcAll(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcAny(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcFirst(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcLast(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcTail(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcCount(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcAsInteger(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcLength(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcDistinct(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcNot(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcResolve(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcContains(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcMatches(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcStartsWith(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcSubString(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;
    function funcExtension(ctxt : TFHIRPathExecutionContext; context : TFHIRBaseList; exp : TFHIRExpressionNode) : TFHIRBaseList;

    function opEquals(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opNotEquals(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opLessThen(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opGreater(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opLessOrEqual(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opGreaterOrEqual(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opIn(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opPlus(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opMinus(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opEquivalent(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opNotEquivalent(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opUnion(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opAnd(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opOr(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opXor(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opImplies(left, right : TFHIRBaseList) : TFHIRBaseList;
    function opConcatenate(left, right : TFHIRBaseList) : TFHIRBaseList;
    function areDistinct(a1, a2: array of TFHIRBaseList): boolean;
    function organisePrecedence(lexer : TFHIRPathLexer; node: TFHIRExpressionNode) : TFHIRExpressionNode;
    function gatherPrecedence(lexer : TFHIRPathLexer; node: TFHIRExpressionNode; ops: TFHIRPathOperationSet): TFHIRExpressionNode;
    function newGroup(lexer : TFHIRPathLexer; next: TFHIRExpressionNode): TFHIRExpressionNode;

  public
    constructor create(context : TValidatorServiceProvider);
    destructor destroy; override;
    property Ondebug : TFHIRPathDebugEvent read FOndebug write FOndebug;

    // Parse a path for later use using execute
    function parse(path : String) : TFHIRExpressionNode;

    // check that paths referred to in the expression are valid
    function check(appInfo : TAdvObject; resourceType, context, path : String; expr : TFHIRExpressionNode; xPathStartsWithValueRef : boolean) : TAdvStringSet;

    // evaluate a path and return the matching elements
    function evaluate(appInfo : TAdvObject; base : TFHIRBase; path : String) : TFHIRBaseList; overload;
    function evaluate(appInfo : TAdvObject; base : TFHIRBase; expr : TFHIRExpressionNode) : TFHIRBaseList; overload;
    function evaluate(appInfo : TAdvObject; resource : TFHIRResource; base : TFHIRBase; path : String) : TFHIRBaseList; overload;
    function evaluate(appInfo : TAdvObject; resource : TFHIRResource; base : TFHIRBase; expr : TFHIRExpressionNode) : TFHIRBaseList; overload;

    // evaluate a path and return true or false
    function evaluateToBoolean(appInfo : TAdvObject; resource : TFHIRResource; base : TFHIRBase; path : String) : boolean;

    // evaluate a path and return a string describing the outcome
    function evaluateToString(appInfo : TAdvObject; base : TFHIRBase; path : String) : string;

    // worker routine for converting a set of objects to a string representation
    function convertToString(items : TFHIRBaseList) : String; overload;
    function convertToString(item : TFHIRBase) : String; overload;

    // worker routine for converting a set of objects to a boolean representation
    function convertToBoolean(items : TFHIRBaseList) : boolean;
  end;

  TFHIRPathTests = class (TAdvObject)
  private
    class procedure test(expr : String);
  public
    class procedure runTests;
  end;
implementation

{ TFHIRPathEvaluator }

function TFHIRPathEvaluator.check(appInfo : TAdvObject; resourceType, context, path : String; expr : TFHIRExpressionNode; xPathStartsWithValueRef : boolean) : TAdvStringSet;
var
  types : TAdvStringSet;
  ctxt : TFHIRPathExecutionTypeContext;
begin
  if (xPathStartsWithValueRef and context.contains('.') and path.startsWith(context.substring(context.lastIndexOf('.')+1))) then
    types := TAdvStringSet.Create(context.substring(0, context.lastIndexOf('.')))
  else
    types := TAdvStringSet.Create(context);
  try
    ctxt := TFHIRPathExecutionTypeContext.create(appInfo, resourceType, context);
    try
      result := executeType(ctxt, types, expr, true);
  finally
      ctxt.free;
    end;
  finally
    types.Free;
  end;

end;

procedure TFHIRPathEvaluator.checkParameters(lexer: TFHIRPathLexer; offset: Integer; exp: TFHIRExpressionNode);
  procedure CheckNoParams;
  begin
    if exp.Parameters.Count > 0 then
      raise lexer.error('The function "'+exp.name+'" can not have any parameters', offset);
  end;
  procedure CheckParamCount(c : integer);
  begin
    if exp.Parameters.Count <> c then
      raise lexer.error('The function "'+exp.name+'" requires '+inttostr(c)+' parameters', offset);
  end;
  procedure CheckParamRange(c1, c2 : integer);
  begin
    if (exp.Parameters.Count < c1) or (exp.Parameters.Count > c2) then
      raise lexer.error('The function "'+exp.name+'" requires between '+inttostr(c1)+' and '+inttostr(c2)+' parameters', offset);
  end;
begin
  case exp.FunctionId of
    pfEmpty: CheckNoParams;
    pfItem: CheckParamCount(1);
    pfWhere: CheckParamCount(1);
    pfAll: CheckParamCount(1);
    pfAny: CheckParamCount(1);
    pfFirst: CheckNoParams;
    pfLast: CheckNoParams;
    pfTail: CheckNoParams;
    pfCount: CheckNoParams;
    pfAsInteger: CheckNoParams;
    pfStartsWith: CheckParamCount(1);
    pfSubString: CheckParamRange(1, 2);
    pfLength: CheckNoParams;
    pfMatches: CheckParamCount(1);
    pfNot: CheckNoParams;
    pfResolve: CheckNoParams;
    pfContains: CheckParamCount(1);
    pfExtension: CheckParamCount(1);
    pfDistinct: {no chcek};
  end;
end;

function TFHIRPathEvaluator.convertToBoolean(items: TFHIRBaseList): boolean;
begin
  if (items = nil) then
    result := false
  else if (items.count = 1) and (items[0] is TFHIRBoolean) then
    result := TFHIRBoolean(items[0]).value
  else
    result := items.count > 0;
end;

function TFHIRPathEvaluator.convertToString(item: TFHIRBase): String;
begin
  if item = nil then
    result := ''
  else if item.isPrimitive then
    result := item.primitiveValue
  else if item is TFhirType then
    result := gen(item as TFHIRType)
  else
    result := '';
end;

constructor TFHIRPathEvaluator.create(context: TValidatorServiceProvider);
begin
  inherited Create;
  worker := context;
end;

procedure TFHIRPathEvaluator.debug(ctxt: TFHIRPathExecutionContext; exp: TFHIRExpressionNode; op : boolean; input1, input2, outcome: TFHIRBaseList);
var
  pack : TFHIRPathDebugPackage;
begin
  if assigned(FOndebug) then
  begin
    pack := TFHIRPathDebugPackage.Create;
    try
      if (input2 = nil) then
      begin
        pack.SourceStart := exp.SourceLocationStart;
        pack.SourceEnd := exp.SourceLocationEnd;
      end
      else
      begin
        pack.SourceStart := exp.OpSourceLocationStart;
        pack.SourceEnd := exp.OpSourceLocationEnd;
      end;
      pack.Expression := exp.Link;
      pack.IsOperation := op;
      pack.context := ctxt.Link;
      pack.input1 := input1.Link;
      pack.input2 := input2.Link;
      pack.outcome := outcome.Link;
      FOndebug(self, pack);
    finally
      pack.Free;
    end;
  end;
end;

destructor TFHIRPathEvaluator.destroy;
begin
  worker.Free;
  inherited;
end;

function TFHIRPathEvaluator.convertToString(items: TFHIRBaseList): String;
var
  b : TStringBuilder;
  first : boolean;
  item : TFHIRBase;
begin
  b := TStringBuilder.Create;
  try
    first := true;
    for item in items do
    begin
      if (first) then
        first := false
      else
        b.Append(',');
      b.Append(convertToString(item));
    end;
    result := b.ToString;
  finally
    b.Free;
  end;
end;

function TFHIRPathEvaluator.evaluate(appInfo : TAdvObject; base: TFHIRBase; path: String): TFHIRBaseList;
var
  exp : TFHIRExpressionNode;
  list : TFHIRBaseList;
  ctxt : TFHIRPathExecutionContext;
begin
  exp := parse(path);
  try
    list := TFHIRBaseList.Create(base.Link);
    try
      ctxt := TFHIRPathExecutionContext.Create(appInfo.Link, nil, base.Link);
      try
        result := execute(ctxt, list, exp, true);
      finally
        ctxt.free;
      end;
    finally
      list.Free;
    end;
  finally
    exp.free;
  end;
end;

function TFHIRPathEvaluator.evaluate(appInfo : TAdvObject; base: TFHIRBase; expr : TFHIRExpressionNode): TFHIRBaseList;
var
  list : TFHIRBaseList;
  ctxt : TFHIRPathExecutionContext;
begin
  list := TFHIRBaseList.Create(base.Link);
  try
    ctxt := TFHIRPathExecutionContext.Create(appInfo.Link, nil, base.Link);
  try
      result := execute(ctxt, list, expr, true);
    finally
      ctxt.Free;
    end;
  finally
    list.Free;
  end;
end;

function TFHIRPathEvaluator.evaluate(appInfo : TAdvObject; resource : TFHIRResource; base: TFHIRBase; path: String): TFHIRBaseList;
var
  exp : TFHIRExpressionNode;
  list : TFHIRBaseList;
  ctxt : TFHIRPathExecutionContext;
begin
  exp := parse(path);
  try
    list := TFHIRBaseList.Create(base.Link);
    try
      ctxt := TFHIRPathExecutionContext.Create(appInfo.Link, resource.Link, base.Link);
    try
        result := execute(ctxt, list, exp, true);
      finally
        ctxt.Free;
      end;
    finally
      list.Free;
    end;
  finally
    exp.free;
  end;
end;

function TFHIRPathEvaluator.evaluate(appInfo : TAdvObject; resource : TFHIRResource; base: TFHIRBase; expr : TFHIRExpressionNode): TFHIRBaseList;
var
  list : TFHIRBaseList;
  ctxt : TFHIRPathExecutionContext;
begin
  list := TFHIRBaseList.Create(base.Link);
  try
    ctxt := TFHIRPathExecutionContext.Create(appInfo.Link, resource.Link, base.Link);
    try
      result := execute(ctxt, list, expr, true);
    finally
      ctxt.Free;
    end;
  finally
    list.Free;
  end;
end;

function TFHIRPathEvaluator.evaluateToBoolean(appInfo : TAdvObject; resource : TFHIRResource; base: TFHIRBase; path: String): boolean;
var
  res : TFHIRBaseList;
begin
  res := evaluate(appInfo, resource, base, path);
  try
    result := convertToBoolean(res);
  finally
    res.Free;
  end;
end;

function TFHIRPathEvaluator.evaluateToString(appInfo : TAdvObject; base: TFHIRBase; path: String): string;
var
  res : TFHIRBaseList;
begin
  res := evaluate(appInfo, base, path);
  try
    result := convertToString(res);
  finally
    res.Free;
  end;
end;

function TFHIRPathEvaluator.execute(ctxt : TFHIRPathExecutionContext; item : TFHIRBase; exp : TFHIRExpressionNode; atEntry : boolean): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  try
    if atEntry and (CharInSet(exp.name[1], ['A'..'Z'])) then // special case for start up
    begin
      if item.FhirType = exp.name then
        result.Add(item.Link);
    end
    else if (exp.name = '**') then
      ListAllChildren(item, result, true)
    else if (exp.name = '*') then
      ListAllChildren(item, result, false)
    else
      item.ListChildrenByName(exp.name, result);
    result.link;
  finally
    result.free;
  end;
end;

procedure TFHIRPathEvaluator.ListAllChildren(item : TFHIRBase; results : TFHIRBaseList; recurse : boolean);
var
  pi : TFHIRPropertyIterator;
  b : TFHIRObject;
  s : String;
begin
  s := item.fhirtype;
  pi := item.createIterator(true, false);
  try
    while pi.More do
    begin
      if pi.Current.hasValue then
      begin
        for b in pi.Current.List do
        begin
          results.Add(b.Link as TFhirBase);
          if (recurse) then
            ListAllChildren(b as TFHIRBase, results, true);
        end;
      end;
      pi.Next;
    end;
  finally
    pi.free;
  end;
end;

function TFHIRPathEvaluator.executeType(ctxt: TFHIRPathExecutionTypeContext; focus: TAdvStringSet; exp: TFHIRExpressionNode; atEntry : boolean): TAdvStringSet;
var
  s : String;
  work, work2 : TAdvStringSet;
  next, last : TFHIRExpressionNode;
begin
  result := TAdvStringSet.Create;
  try
    case exp.kind of
      entName:
        if (exp.Name = '$context')  then
          result.add(ctxt.context)
        else if (exp.Name = '$resource') then
    begin
          if (ctxt.resourceType <> '') then
            result.add(ctxt.resourceType)
          else
            result.add('DomainResource');
    end
    else
        begin
          for s in focus do
          begin
            work := executeType(s, exp, atEntry);
            try
              result.addAll(work);
  finally
              work.Free;
            end;
          end;
          if (result.isEmpty()) then
            raise Exception.create('The name '+exp.Name+' was not valid for any of the possible types: '+focus.toString());
        end;
      entFunction:
        begin
          work := evaluateFunctionType(ctxt, focus, exp);
          try
            result.addAll(work)
          finally
            work.Free;
          end;
        end;
      entConstant:
        result.add(readConstantType(ctxt.appInfo, exp.Constant));
      entGroup:
        begin
          work := executeType(ctxt, focus, exp.Group, atEntry);
          try
            result.addAll(work)
          finally
            work.Free;
          end;
        end;
    end;

    exp.types := result.Link;

    if (exp.Inner <> nil) then
    begin
      work := executeType(ctxt, result, exp.Inner, false);
      result.Free;
      result := work;
    end;

    if (exp.proximal and (exp.Operation <> opNull)) then
    begin
      next := exp.OpNext;
      last := exp;
      while (next <> nil) do
      begin
        work := executeType(ctxt, focus, next, atEntry);
        try
          work2 := operateTypes(result, last.Operation, work);
          result.Free;
          result := work2;
        finally
          work.Free;
        end;
        last := next;
        next := next.OpNext;
      end;
      exp.opTypes := result.Link;
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.funcAll(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item : TFHIRBase;
  pc, res : TFHIRBaseList;
  all : boolean;
begin
  all := true;
  pc := TFHIRBaseList.Create;
  try
    for item in context do
    begin
      pc.Clear;
      pc.Add(item.Link);
      res := execute(ctxt, pc, exp.Parameters[0], false);
      try
        if not convertToBoolean(res) then
        begin
          all := false;
          break;
        end;
      finally
        res.Free;
      end;
    end;
  finally
    pc.Free;
  end;

  result := TFHIRBaseList.Create(TFhirBoolean.Create(all));
end;

function TFHIRPathEvaluator.funcAny(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item : TFHIRBase;
  pc, res : TFHIRBaseList;
  all : boolean;
begin
  all := false;
  pc := TFHIRBaseList.Create;
  try
    for item in context do
    begin
      pc.Clear;
      pc.Add(item.Link);
      res := execute(ctxt, pc, exp.Parameters[0], false);
      try
        if convertToBoolean(res) then
        begin
          all := true;
          break;
        end;
      finally
        res.Free;
      end;
    end;
  finally
    pc.Free;
  end;

  result := TFHIRBaseList.Create(TFHIRBoolean.Create(all));
end;

function TFHIRPathEvaluator.funcAsInteger(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  s : String;
begin
  s := convertToString(context);
  result := TFHIRBaseList.Create;
  if StringIsInteger32(s) then
    result.Add(TFhirInteger.Create(s));
end;

function TFHIRPathEvaluator.funcContains(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  raise Exception.Create('The function '+exp.name+' is not done yet');
end;

function TFHIRPathEvaluator.funcCount(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create(TFhirInteger.Create(inttostr(context.Count)));
end;

function TFHIRPathEvaluator.areDistinct(a1, a2 : array of TFHIRBaseList) : boolean;
var
  i : integer;
  res : TFHIRBaseList;
begin
  result := false;
  for i := 0 to length(a1) - 1 do
  begin
    res := opEquals(a1[i], a2[i]);
    try
      if not convertToBoolean(res) then
      begin
        result := true;
        exit;
      end;
    finally
      res.Free;
    end;
  end;
end;

function TFHIRPathEvaluator.funcDistinct(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  table : array of array of TFHIRBaseList;
  i, j : integer;
  base : TFHIRBase;
  distinct : boolean;
begin
  distinct := Context.Count <= 1;
  try
    SetLength(table, context.Count);
    for i := 0 to context.Count - 1 do
    begin
      SetLength(table[i], exp.Parameters.Count);
      base := context[i];
      for j := 0 to exp.Parameters.Count - 1 do
        table[i][j] := evaluate(ctxt, base, exp.Parameters[j]);
    end;
    for i := 0 to context.Count - 1 do
      for j := i+1 to context.Count - 1 do
        if areDistinct(table[i], table[j]) then
        begin
          distinct := true;
          break;
        end;
  finally
    for i := 0 to context.Count - 1 do
      for j := 0 to exp.Parameters.Count - 1 do
        table[i][j].Free;
  end;
  result := TFHIRBaseList.Create(TFhirBoolean.Create(distinct));
end;

function TFHIRPathEvaluator.funcEmpty(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create(TFhirBoolean.Create(context.Count = 0));
end;

function TFHIRPathEvaluator.funcFirst(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  if context.Count > 0 then
    result.Add(context[0].Link);
end;

function TFHIRPathEvaluator.funcItem(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  s : String;
  res : TFHIRBaseList;
begin
  res := execute(ctxt, context, exp.Parameters[0], false);
  try
		s := convertToString(res);
  finally
    res.Free;
  end;
  result := TFHIRBaseList.Create;
  if StringIsInteger16(s) and (context.Count > StrToInt(s)) then
    result.Add(context[StrToInt(s)].Link);
end;

function TFHIRPathEvaluator.funcLast(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  if context.Count > 0 then
    result.Add(context[context.Count - 1].Link);
end;

function TFHIRPathEvaluator.funcLength(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item : TFHIRBase;
  pc, res : TFHIRBaseList;
  s : String;
  l : integer;
begin
  l := 0;
  for item in context do
  begin
    s := convertToString(item);
    l := max(l, s.Length);
  end;
  result := TFHIRBaseList.Create(TFhirInteger.Create(inttostr(l)));
end;

function TFHIRPathEvaluator.funcMatches(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item : TFHIRBase;
  pc, res : TFHIRBaseList;
  s, p : String;
  reg : TRegExpr;
begin
  result := TFHIRBaseList.Create;
  try
    res := execute(ctxt, context, exp.Parameters[0], false);
    try
      p := convertToString(res);
    finally
      res.free;
    end;
    reg := TRegExpr.Create;
    try
      reg.Expression := p;
      for item in context do
      begin
        s := convertToString(item);
        if (reg.Exec(s)) then
          result.Add(item.Link);
      end;
    finally
      reg.Free;
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.funcNot(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create(TFhirBoolean.Create(not convertToBoolean(context)));
end;

function TFHIRPathEvaluator.funcResolve(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  raise Exception.Create('The function '+exp.name+' is not done yet');
end;

function TFHIRPathEvaluator.funcStartsWith(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item : TFHIRBase;
  pc, res : TFHIRBaseList;
  s, sw : String;
begin
  result := TFHIRBaseList.Create;
  try
    res := execute(ctxt, context, exp.Parameters[0], false);
    try
      sw := convertToString(res);
    finally
      res.free;
    end;

    for item in context do
    begin
      s := convertToString(item);
      if (s.StartsWith(sw)) then
        result.Add(item.Link);
    end;

    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.funcSubString(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item : TFHIRBase;
  pc, res : TFHIRBaseList;
  s, sw : String;
  n1, n2 : TFhirBaseList;
  i1, i2 : integer;
begin
  n1 := nil;
  n2 := nil;
  result := TFHIRBaseList.Create;
  try
    n1 := execute(ctxt, context, exp.Parameters[0], false);
    i1 := StrToInt(n1[0].primitiveValue);
    if (exp.ParameterCount = 2) then
    begin
      n2 := execute(ctxt, context, exp.Parameters[1], false);
      i2 := StrToInt(n2[0].primitiveValue);
    end;

    for item in context do
    begin
      sw := convertToString(item);
      if n2 <> nil then
        s := sw.Substring(i1, i2)
      else
        s := sw.Substring(i1);
      if (s <> '') then
        result.Add(TFhirString.Create(s));
    end;

    result.Link;
  finally
    n1.free;
    n2.free;
    result.Free;
  end;
end;

function TFHIRPathEvaluator.funcExtension(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item, ex : TFHIRBase;
  pc, res, vl : TFHIRBaseList;
  s, url : String;
  n1, ext, v : TFhirBaseList;
begin
  n1 := nil;
  result := TFHIRBaseList.Create;
  try
    n1 := execute(ctxt, context, exp.Parameters[0], false);
    url := n1[0].primitiveValue;

    for item in context do
    begin
      ext := TFHIRBaseList.Create;
      try
        item.ListChildrenByName('extension', ext);
        item.ListChildrenByName('modifierExtension', ext);
        for ex in ext do
        begin
          vl := TFHIRBaseList.Create;
          try
            ex.ListChildrenByName('url', vl);
            if convertToString(vl) = url then
              result.Add(ex.Link);
          finally
            vl.Free;
          end;
        end;
      finally
        ext.Free;
      end;
    end;

    result.Link;
  finally
    n1.free;
    result.Free;
  end;
end;

function TFHIRPathEvaluator.funcTail(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
   i : integer;
begin
  result := TFHIRBaseList.Create;
  for i := 1 to Context.Count -1 do
    result.Add(context[i].Link);
end;

function TFHIRPathEvaluator.funcWhere(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
var
  item : TFHIRBase;
  pc, res : TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  try
    pc := TFHIRBaseList.Create;
    try
      for item in context do
      begin
        pc.Clear;
        pc.Add(item.Link);
        res := execute(ctxt, pc, exp.Parameters[0], false);
        try
          if convertToBoolean(res) then
            result.Add(item.Link);
        finally
          res.Free;
        end;
      end;
    finally
      pc.Free;
    end;

    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.preOperate(left: TFHIRBaseList; op: TFHIRPathOperation): TFHIRBaseList;
begin
  result := nil;
  case op of
    poAnd: if (not convertToBoolean(left)) then
        result := TFHIRBaseList.Create(TFHIRBoolean.Create(false));
    poOr: if (convertToBoolean(left)) then
        result := TFHIRBaseList.Create(TFHIRBoolean.Create(true));
  end;
end;

function TFHIRPathEvaluator.operate(left: TFHIRBaseList; op: TFHIRPathOperation; right: TFHIRBaseList): TFHIRBaseList;
begin
  case op of
    opNull: raise Exception.create('An internal error has occurred');
    poEquals: result := opEquals(left, right);
    poNotEquals: result := opNotEquals(left, right);
    poLessThen: result := opLessThen(left, right);
    poGreater: result := opGreater(left, right);
    poLessOrEqual: result := opLessOrEqual(left, right);
    poGreaterOrEqual: result := opGreaterOrEqual(left, right);
    poIn: result := opIn(left, right);
    poPlus: result := opPlus(left, right);
    poMinus: result := opMinus(left, right);
    poEquivalent: result := opEquivalent(left, right);
    poNotEquivalent: result := opNotEquivalent(left, right);
    poUnion: result := opUnion(left, right);
    poAnd: result := opAnd(left, right);
    poOr: result := opOr(left, right);
    poXor: result := opXor(left, right);
    poImplies: result := opImplies(left, right);
    poConcatenate: result := opConcatenate(left, right);

  else
    result := nil;
  end;
end;


function TFHIRPathEvaluator.operateTypes(left: TAdvStringSet; op: TFHIRPathOperation; right: TAdvStringSet): TAdvStringSet;
begin
  case op of
    poEquals: result := TAdvStringSet.Create('boolean');
    poEquivalent: result := TAdvStringSet.Create('boolean');
    poNotEquals: result := TAdvStringSet.Create('boolean');
    poNotEquivalent: result := TAdvStringSet.Create('boolean');
    poLessThen: result := TAdvStringSet.Create('boolean');
    poGreater: result := TAdvStringSet.Create('boolean');
    poLessOrEqual: result := TAdvStringSet.Create('boolean');
    poGreaterOrEqual: result := TAdvStringSet.Create('boolean');
    poIn: result := TAdvStringSet.Create('boolean');
    poPlus: result := TAdvStringSet.Create('string');
    poMinus: result := TAdvStringSet.Create('string');
    poConcatenate: result := TAdvStringSet.Create('boolean');
    poOr: result := TAdvStringSet.Create('boolean');
    poAnd: result := TAdvStringSet.Create('boolean');
    poXor: result := TAdvStringSet.Create('boolean');
    poImplies: result := TAdvStringSet.Create('boolean');
    poUnion: result := TAdvStringSet.Create(left, right);
  else
    raise Exception.Create('not done yet');
  end;
end;

function TFHIRPathEvaluator.opAnd(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  result.Add(TFhirBoolean.Create(convertToBoolean(left) and convertToBoolean(right)));
end;

function TFHIRPathEvaluator.opConcatenate(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  raise Exception.Create('The operation Multiply is not done yet');
end;

function TFHIRPathEvaluator.opEquals(left, right: TFHIRBaseList): TFHIRBaseList;
var
  sl, sr : String;
  res : boolean;
  i : integer;
begin
  if left.count <> right.count then
    res := false
  else
  begin
    res := true;
    for i := 0 to left.count - 1 do
      if not compareDeep(left[i], right[i], false) then
      begin
        res := false;
        break;
      end;
  end;
  result := TFHIRBaseList.Create;
  result.Add(TFhirBoolean.Create(res));
end;

function TFHIRPathEvaluator.opEquivalent(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  raise Exception.Create('The operation Multiply is not done yet');
end;

function TFHIRPathEvaluator.opGreater(left, right: TFHIRBaseList): TFHIRBaseList;
var
  l, r : TFHIRBase;
begin
  result := TFHIRBaseList.create;
  try
    if (Left.Count = 1) and (right.count = 1) and (left[0].isPrimitive) and (right[0].isPrimitive) then
    begin
      l := left[0] as TFHIRBase;
      r := right[0] as TFHIRBase;
      if (l is TFhirString) and (r is TFhirString) then
        result.Add(TFhirBoolean.Create(l.primitiveValue > r.primitiveValue))
      else if ((l is TFhirInteger) or (l.FhirType = 'decimal')) and ((r is TFhirInteger) or (r.FhirType = 'decimal')) then
        result.Add(TFhirBoolean.Create(StrToFloat(l.primitiveValue) > StrToFloat(r.primitiveValue)))
      else if ((l.FhirType = 'date') or (l.FhirType = 'dateTime') or (l.FhirType = 'instant')) and ((r.FhirType = 'date') or (r.FhirType = 'dateTime') or (r.FhirType = 'instant')) then
        result.Add(TFhirBoolean.Create(l.primitiveValue > r.primitiveValue))
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.opGreaterOrEqual(left, right: TFHIRBaseList): TFHIRBaseList;
var
  l, r : TFHIRBase;
begin
  result := TFHIRBaseList.create;
  try
    if (Left.Count = 1) and (right.count = 1) and (left[0].isPrimitive) and (right[0].isPrimitive) then
    begin
      l := left[0] as TFHIRBase;
      r := right[0] as TFHIRBase;
      if (l.FhirType = 'string') and (r.FhirType = 'string') then
        result.Add(TFhirBoolean.Create(l.primitiveValue >= r.primitiveValue))
      else if ((l is TFhirInteger) or (l.FhirType = 'decimal')) and ((r is TFhirInteger) or (r.FhirType = 'decimal')) then
        result.Add(TFhirBoolean.Create(StrToFloat(l.primitiveValue) >= StrToFloat(r.primitiveValue)))
      else if ((l.FhirType = 'date') or (l.FhirType = 'dateTime') or (l.FhirType = 'instant')) and ((r.FhirType = 'date') or (r.FhirType = 'dateTime') or (r.FhirType = 'instant')) then
        result.Add(TFhirBoolean.Create(l.primitiveValue >= r.primitiveValue));
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.opIn(left, right: TFHIRBaseList): TFHIRBaseList;
var
  ans, f : boolean;
  l, r : TFHIRBase;
begin
  ans := true;
  for l in left do
  begin
    f := false;
    for r in right do
      if compareDeep(l, r, false) then
      begin
        f := true;
        break;
      end;
    if not f then
    begin
      ans := false;
      break;
    end;
  end;
  result := TFHIRBaseList.Create;
  result.Add(TFhirBoolean.Create(ans));
end;

function TFHIRPathEvaluator.opLessOrEqual(left, right: TFHIRBaseList): TFHIRBaseList;
var
  l, r : TFHIRBase;
begin
  result := TFHIRBaseList.create;
  try
    if (Left.Count = 1) and (right.count = 1) and (left[0].isPrimitive) and (right[0].isPrimitive) then
    begin
      l := left[0];
      r := right[0];
      if (l.FhirType = 'string') and (r.FhirType = 'string') then
        result.Add(TFhirBoolean.Create(l.primitiveValue <= r.primitiveValue))
      else if ((l.FhirType = 'integer') or (l.FhirType = 'decimal')) and ((r.FhirType = 'integer') or (r.FhirType = 'decimal')) then
        result.Add(TFhirBoolean.Create(StrToFloat(l.primitiveValue) <= StrToFloat(r.primitiveValue)))
      else if ((l.FhirType = 'date') or (l.FhirType = 'dateTime') or (l.FhirType = 'instant')) and ((r.FhirType = 'date') or (l.FhirType = 'dateTime') or (r.FhirType = 'instant')) then
        result.Add(TFhirBoolean.Create(l.primitiveValue <= r.primitiveValue))
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.opLessThen(left, right: TFHIRBaseList): TFHIRBaseList;
var
  l, r : TFHIRBase;
begin
  result := TFHIRBaseList.create;
  try
    if (Left.Count = 1) and (right.count = 1) and (left[0].isPrimitive) and (right[0].isPrimitive) then
    begin
      l := left[0] as TFHIRBase;
      r := right[0] as TFHIRBase;
      if (l.FhirType = 'string') and (r.FhirType = 'string') then
        result.Add(TFhirBoolean.Create(l.primitiveValue < r.primitiveValue))
      else if ((l.FhirType = 'integer') or (l.FhirType = 'decimal')) and ((r.FhirType = 'integer') or (r.FhirType = 'decimal')) then
        result.Add(TFhirBoolean.Create(StrToFloat(l.primitiveValue) < StrToFloat(r.primitiveValue)))
      else if ((l.FhirType = 'date') or (l.FhirType = 'dateTime') or (l.FhirType = 'instant')) and ((r.FhirType = 'date') or (r.FhirType = 'dateTime') or (r.FhirType = 'instant')) then
        result.Add(TFhirBoolean.Create(l.primitiveValue < r.primitiveValue))
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.opMinus(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  raise Exception.Create('The operation Minus is not done yet');
end;

function TFHIRPathEvaluator.opNotEquals(left, right: TFHIRBaseList): TFHIRBaseList;
var
  sl, sr : String;
  found : boolean;
  item : TFHIRBase;
begin
  found := false;
  sr := convertToString(right);
  for item in left do
  begin
    sl := convertToString(item);
    found := found or (sl = sr);
  end;
  result := TFHIRBaseList.Create;
  result.Add(TFhirBoolean.Create(not found));
end;

function TFHIRPathEvaluator.opNotEquivalent(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  raise Exception.Create('The operation Multiply is not done yet');
end;

function TFHIRPathEvaluator.opOr(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  result.Add(TFhirBoolean.Create(convertToBoolean(left) or convertToBoolean(right)));
end;

function TFHIRPathEvaluator.opPlus(left, right: TFHIRBaseList): TFHIRBaseList;
var
  l, r : TFHIRBase;
begin
  result := TFHIRBaseList.create;
  try
    if (Left.Count = 1) and (right.count = 1) and (left[0].isPrimitive) and (right[0].isPrimitive) then
    begin
      l := left[0] as TFHIRBase;
      r := right[0] as TFHIRBase;
      if (l.FhirType = 'string') and (r.FhirType = 'string') then
        result.Add(TFhirString.Create(l.primitiveValue + r.primitiveValue))
      else if ((l.FhirType = 'integer') or (l.FhirType = 'decimal')) and ((r.FhirType = 'integer') or (r.FhirType = 'decimal')) then
        if StringIsInteger32(l.primitiveValue) and StringIsInteger32(r.primitiveValue) then
          result.Add(TFhirInteger.Create(inttostr(StrToInt(l.primitiveValue) + StrToInt(r.primitiveValue))))
        else
          result.Add(TFhirDecimal.Create(FloatToStr(StrToFloat(l.primitiveValue) + StrToFloat(r.primitiveValue))));
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.opUnion(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  result := TFHIRBaseList.create;
  try
    result.AddAll(left);
    result.AddAll(right);
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.opXor(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  result.Add(TFhirBoolean.Create(convertToBoolean(left) xor convertToBoolean(right)));
end;

function TFHIRPathEvaluator.opImplies(left, right: TFHIRBaseList): TFHIRBaseList;
begin
  result := TFHIRBaseList.Create;
  if convertToBoolean(left) then
    result.Add(TFhirBoolean.Create(convertToBoolean(right)))
  else
    result.Add(TFhirBoolean.Create(true));
end;

function TFHIRPathEvaluator.execute(ctxt : TFHIRPathExecutionContext; focus: TFHIRBaseList; exp: TFHIRExpressionNode; atEntry : boolean): TFHIRBaseList;
var
  work, work2 : TFHIRBaseList;
  item, base : TFHIRBase;
  outcome : TFHIRBaseList;
  next, last : TFHIRExpressionNode;
begin
  work := TFHIRBaseList.Create;
  try
    case exp.kind of
      entName:
        if (exp.name = '$resource') then
          work.add(ctxt.resource.Link)
    else if (exp.name = '$context') then
          work.add(ctxt.context.Link)
    else
          for item in focus do
      begin
            outcome := execute(ctxt, item, exp, atEntry);
        try
              for base in outcome do
                if (base <> nil) then
                  work.Add(base.Link);
        finally
          outcome.Free;
        end;
      end;
      entFunction:
    begin
        work2 := evaluateFunction(ctxt, focus, exp);
        try
          work.addAll(work2);
        finally
          work2.Free;
        end;
end;
      entConstant:
        work.Add(readConstant(exp.constant));
      entGroup:
    begin
        work2 := execute(ctxt, focus, exp.group, atEntry);
      try
        work.addAll(work2);
      finally
        work2.Free;
      end;
        end;
      end;

    Debug(ctxt, exp, false, focus, nil, work);

    if (exp.Inner <> nil) then
    begin
      result := execute(ctxt, work, exp.Inner, false);
      work.Free;
      work := result;
    end;

    if (exp.proximal and (exp.Operation <> opNull)) then
    begin
      next := exp.OpNext;
      last := exp;
      while (next <> nil) do
      begin
        // and and or - may be able to avoid executing the right side
        work2 := preOperate(work, last.Operation);
        if work2 <> nil then
        begin
          Debug(ctxt, exp, true, work, nil, work2);
          work.Free;
          work := work2;
        end
        else
        begin
          work2 := execute(ctxt, focus, next, false);
        try
            result := operate(work, last.Operation, work2);
            try
              Debug(ctxt, exp, true, work, work2, result);
            finally
          work.Free;
          work := result;
            end;
        finally
          work2.Free;
        end;
        end;
        last := next;
        next := next.OpNext;
      end;
    end;
    result := work.Link;
  finally
    work.Free;
  end;
end;

function TFHIRPathEvaluator.executeType(focus: String; exp: TFHIRExpressionNode; atEntry : boolean): TAdvStringSet;
begin
  result := TAdvStringSet.create;
  try
    if (atEntry and isUpper(exp.Name[1])) then
    begin
      // special case for start up
      if (focus = exp.Name) then
        result.add(focus);
    end
    else
      ListChildTypesByName(focus, exp.name, result);
    result.Link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.evaluateFunction(ctxt : TFHIRPathExecutionContext; context: TFHIRBaseList; exp: TFHIRExpressionNode): TFHIRBaseList;
begin
  case exp.FunctionId of
    pfEmpty : result := funcEmpty(ctxt, context, exp);
    pfItem : result := funcItem(ctxt, context, exp);
    pfWhere : result := funcWhere(ctxt, context, exp);
    pfAll : result := funcAll(ctxt, context, exp);
    pfAny : result := funcAny(ctxt, context, exp);
    pfFirst : result := funcFirst(ctxt, context, exp);
    pfLast : result := funcLast(ctxt, context, exp);
    pfTail : result := funcTail(ctxt, context, exp);
    pfCount : result := funcCount(ctxt, context, exp);
    pfAsInteger : result := funcAsInteger(ctxt, context, exp);
    pfStartsWith : result := funcStartsWith(ctxt, context, exp);
    pfLength : result := funcLength(ctxt, context, exp);
    pfMatches : result := funcMatches(ctxt, context, exp);
    pfDistinct : result := funcDistinct(ctxt, context, exp);
    pfNot : result := funcNot(ctxt, context, exp);
    pfResolve : result := funcResolve(ctxt, context, exp);
    pfContains : result := funcContains(ctxt, context, exp);
    pfSubString : result := funcSubString(ctxt, context, exp);
  else
    raise Exception.Create('Unknown Function '+exp.name);
  end;
end;

function TFHIRPathEvaluator.primitives(collection : TAdvStringSet) : TAdvStringSet;
var
  s : String;
begin
  result := TAdvStringSet.create;
  try
    for s in collection do
      if (isPrimitiveType(s)) then
        result.add(s);
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRPathEvaluator.isPrimitiveType(s : String) : boolean;
begin
  result := (s = 'boolean') or (s = 'integer') or (s = 'decimal') or (s = 'base64Binary') or (s = 'instant') or (s = 'string') or (s = 'uri') or (s = 'date') or (s = 'dateTime') or (s = 'time') or (s = 'code') or (s = 'oid') or (s = 'id') or (s = 'unsignedInt') or (s = 'positiveInt') or (s = 'markdown');
end;


function TFHIRPathEvaluator.evaluateFunctionType(ctxt: TFHIRPathExecutionTypeContext; context: TAdvStringSet; exp: TFHIRExpressionNode): TAdvStringSet;
var
  expr : TFHIRExpressionNode;
begin
  for expr in exp.parameters do
    executeType(ctxt, context, expr, false).Free; // just checking...

  case exp.FunctionId of
    pfEmpty : result := TAdvStringSet.create('boolean');
    pfItem : result := context.Link;
    pfWhere : result := context.Link;
    pfAll : result := TAdvStringSet.create('boolean');
    pfAny : result := TAdvStringSet.create('boolean');
    pfFirst : result := context.Link;
    pfLast : result := context.Link;
    pfTail : result := context.Link;
    pfCount : result := TAdvStringSet.create('integer');
    pfAsInteger : result := TAdvStringSet.create('integer');
    pfStartsWith : result := primitives(context);
    pfLength : result := TAdvStringSet.create('integer');
    pfMatches : result := primitives(context);
    pfContains : result := primitives(context);
    pfSubstring : result := TAdvStringSet.create('integer');
    pfDistinct : result := TAdvStringSet.create('boolean');
    pfNot : result := TAdvStringSet.create('boolean');
    pfResolve : result := TAdvStringSet.create('DomainResource');
  end;
end;


function cleanConstant(s: String; lexer : TFHIRPathLexer) : String;
var
  b : TStringBuilder;
  e : boolean;
  i : integer;
  ch : char;
begin
  if (s.StartsWith('"') and s.EndsWith('"')) then
  begin
    b := TStringBuilder.Create;
    try
      e := false;
      for i := 2 to length(s)-1 do
      begin
        ch := s[i];
        if (e) then
        begin
          case ch of
            't': b.Append(#9);
            'r': b.Append(#13);
            'n': b.Append(#10);
            '\': b.Append('\');
            '''': b.Append('''');
            '"': b.Append('"');
          else
            raise lexer.error('Unknown character escape \\'+ch);
          end;
        end
        else if ch = '\' then
          e := true
        else
          b.Append(ch);
      end;
      result := b.toString;
    finally
      b.Free;
    end;
  end
  else
    result := s;
end;


function TFHIRPathEvaluator.parseExpression(lexer : TFHIRPathLexer; proximal : boolean): TFHIRExpressionNode;
var
  c : Integer;
  focus : TFHIRExpressionNode;
begin
  result := TFHIRExpressionNode.Create(lexer.nextId);
  try
    result.SourceLocationStart := lexer.FCurrentStartLocation;
    c := lexer.CurrentStart;
    if lexer.isConstant then
    begin
      result.Constant := cleanConstant(lexer.take, lexer);
      result.kind := entConstant;
      result.SourceLocationEnd := lexer.FCurrentLocation;
    end
    else if lexer.current = '(' then
      begin
        lexer.next;
      result.kind := entGroup;
      result.group := parseExpression(lexer, true);
        if lexer.current <> ')' then
          raise lexer.error('Found '+lexer.current+' expecting a ")"');
      result.SourceLocationEnd := lexer.FCurrentLocation;
        lexer.next;
      end
      else
      begin
        if not lexer.isToken then
          raise lexer.error('Found '+lexer.current+' expecting a token name');
      result.Name := lexer.take;
      result.SourceLocationEnd := lexer.FCurrentLocation;
        if not result.checkName then
          raise lexer.error('Found '+lexer.current+' expecting a valid token name');
        if (lexer.current = '(') then
        begin
        if not StringArrayExistsSensitive(CODES_TFHIRPathFunctions, result.Name) then
          raise lexer.error('The name '+result.Name+' is not a valid function name');
        result.kind := entFunction;
        result.FunctionId := TFHIRPathFunction(StringArrayIndexOfSensitive(CODES_TFHIRPathFunctions, result.Name));
          lexer.next;
          while lexer.current <> ')' do
          begin
            result.Parameters.add(parseExpression(lexer, true));
            if lexer.current = ',' then
              lexer.next
            else if lexer.current <> ')' then
              raise lexer.error('The token '+lexer.current+' is not expected here - either a "," or a ")" expected');
          end;
        result.SourceLocationEnd := lexer.FCurrentLocation;
          lexer.next;
          checkParameters(lexer, c, result);
        end;
      end;
      if lexer.current = '.' then
      begin
        lexer.next;
        result.Inner := parseExpression(lexer, false);
      end;
    result.Proximal := proximal;
    if (proximal) then
    begin
      focus := result;
      while lexer.isOp do
      begin
        focus.Operation := TFHIRPathOperation(StringArrayIndexOfSensitive(CODES_TFHIRPathOperation, lexer.current));
        focus.OpSourceLocationStart := lexer.FCurrentStartLocation;
        focus.OpSourceLocationEnd := lexer.FCurrentLocation;
        lexer.next;
        focus.opNext := parseExpression(lexer, false);
        focus := focus.OpNext;
      end;
      focus := organisePrecedence(lexer, result).Link;
      result.Free;
      result := focus;
    end;
    result.link;
  finally
    result.Free;
  end;
end;

function TFHIRPathEvaluator.newGroup(lexer : TFHIRPathLexer; next : TFHIRExpressionNode) : TFHIRExpressionNode;
begin
  result := TFHIRExpressionNode.Create(lexer.nextId);
  try
    result.kind := entGroup;
    result.Group := next.Link;
    result.link;
  finally
    result.free;
  end;
end;

function TFHIRPathEvaluator.gatherPrecedence(lexer : TFHIRPathLexer; node : TFHIRExpressionNode; ops : TFHIRPathOperationSet) : TFHIRExpressionNode;
var
  work : boolean;
  focus, group : TFHIRExpressionNode;
begin
  // is there anything to do?
  work := false;
  focus := node.OpNext;
  if node.Operation in ops then
    while (focus <> nil) do
    begin
      work := work and not (focus.Operation in Ops);
      focus := focus.OpNext;
    end
  else
    while (focus <> nil) do
    begin
      work := work or (focus.Operation in Ops);
      focus := focus.OpNext;
    end;
  if not work then
    exit(node);
  // entry point: tricky
  if node.Operation in ops then
  begin
    group := newGroup(lexer, node);
    result := group;
    focus := node;
  end
  else
  begin
    result := node;
    focus := node.OpNext;
    while not (focus.Operation in Ops) do
    begin
      node := focus;
      focus := focus.OpNext;
    end;
    group := newGroup(lexer, focus);
    node.OpNext := group;
  end;

  // now, at this point, group is the group we are adding to,
  // and it already has a .group property filled out. focus points
  // at the group.group
  repeat
    // run until we find the end of the sequence
    while (focus.Operation in ops) do
      focus := focus.OpNext;
    if (focus.Operation <> opNull) then
    begin
      group.Operation := focus.Operation;
      group.OpNext := focus.OpNext.Link;
      focus.Operation := opNull;
      group.OpNext := nil;
      // now look for another sequence, and start it
      node := group;
      focus := group.OpNext;
      while not (focus.Operation in Ops) do
      begin
        node := focus;
        focus := focus.OpNext;
      end;
      group := newGroup(lexer, focus);
      node.OpNext := group;
    end;
  until (focus.Operation = opNull);
end;

function TFHIRPathEvaluator.organisePrecedence(lexer : TFHIRPathLexer; node : TFHIRExpressionNode) : TFHIRExpressionNode;
begin
  // precedence:
  // #1 . (path/function invocation) - this has already been handled by the parsing
  // #2: *, /
  // #3: +, -, &, |
  // #4: =, ~, !=, !~, >, <, >=, <=, in
  // #5: and, xor, or, implies
  result := node;
  try
//  result := gatherPrecedence(result, [opMultiply, opDivide]);
    result := gatherPrecedence(lexer, result, [poPlus, poMinus, poConcatenate, poUnion]);
    result := gatherPrecedence(lexer, result, [poEquals, poEquivalent, poNotEquals, poNotEquivalent, poLessThen, poGreater, poLessOrEqual, poGreaterOrEqual, poIn]);
    result := gatherPrecedence(lexer, result, [poEquals, poEquivalent, poNotEquals, poNotEquivalent, poLessThen, poGreater, poLessOrEqual, poGreaterOrEqual, poIn]);
    // which just leaves poAnd, poOr, poXor, poImplies
    result.Link;
  finally
    result.Free;
  end;
end;


function TFHIRPathEvaluator.readConstant(constant: String): TFHIRType;
begin
  if (constant = 'true') then
    result := TFhirBoolean.Create(true)
  else if (constant = 'false') then
    result := TFhirBoolean.Create(false)
  else if StringIsInteger32(constant) then
    result := TFhirInteger.Create(constant)
  else if IsNumericString(constant) then
    result := TFhirDecimal.Create(constant)
  else
    result := TFhirString.Create(constant);
end;

function TFHIRPathEvaluator.readConstantType(appInfo : TAdvObject; constant: String): string;
begin
  if (constant = 'true') then
    result := 'boolean'
  else if (constant = 'false') then
    result := 'boolean'
  else if StringIsInteger32(constant) then
    result := 'integer'
  else if IsNumericString(constant) then
    result := 'decimal'
  else
    result := 'string';
end;

function TFHIRPathEvaluator.parse(path: String): TFHIRExpressionNode;
var
  lexer : TFHIRPathLexer;
  focus : TFHIRExpressionNode;
begin
  lexer := TFHIRPathLexer.Create(path);
  try
    if lexer.done then
      raise lexer.error('Path cannot be empty');
    result := parseExpression(lexer, true);
    try
      if not lexer.done then
        raise lexer.error('Premature expression termination at unexpected token "'+lexer.current+'"');
      result.Link;
    finally
      result.free;
    end;
  finally
    lexer.Free;
  end;
end;

procedure TFHIRPathEvaluator.ListChildTypesByName(item, name : String; result : TAdvStringSet);
var
  url, tail, specifiedType, path, tn : String;
  sd, dt, sdi : TFhirStructureDefinition;
  sdl : TAdvList<TFhirStructureDefinition>;
  ed : TFhirElementDefinition;
  t : TFhirElementDefinitionType;
begin
  if (item = '') then
    raise Exception.create('No type provided in BuildToolPathEvaluator.ListChildTypesByName');
  if (item.equals('xhtml')) then
    exit;
  if (item.contains('.')) then
    url := 'http://hl7.org/fhir/StructureDefinition/'+item.substring(0, item.indexOf('.'))
  else
    url := 'http://hl7.org/fhir/StructureDefinition/'+item;
  sd := worker.fetchResource(frtStructureDefinition, url) as TFhirStructureDefinition;
  if (sd = nil) then
    raise Exception.create('Unknown item '+item); // this really is an error, because we can only get to here if the internal infrastrucgture is wrong
  sdl := TAdvList<TFhirStructureDefinition>.create;
  try
    if (item.contains('.')) then
      ed := getElementDefinition(sd, item, specifiedType);
    if ((ed <> nil) and hasDataType(ed)) then
    begin
      if specifiedType <> '' then
      begin
        dt := worker.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/'+specifiedType) as TFhirStructureDefinition;
        if (dt = nil) then
          raise Exception.create('unknown data type '+specifiedType);
        sdl.add(dt);
      end
      else
        for t in ed.type_List do
        begin
          dt := worker.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/'+t.Code) as TFhirStructureDefinition;
          if (dt = nil) then
            raise Exception.create('unknown data type '+t.code);
          sdl.add(dt);
        end;
    end
    else
    begin
      sdl.add(sd.Link);
      if (item.contains('.')) then
        tail := item.substring(item.indexOf('.'));
    end;

    for sdi in sdl do
    begin
      path := sdi.snapshot.elementList[0].path+tail+'.';
      if (name = '**') then
      begin
        for ed in sdi.snapshot.elementList do
        begin
          if (ed.path.startsWith(path)) then
            for t in ed.type_List do
            begin
              if (t.code.equals('Element') or t.code.equals('BackboneElement')) then
                tn := ed.path
              else
                tn := t.code;
              if (not result.contains(tn)) and (tn <> '') then
              begin
                result.add(tn);
                ListChildTypesByName(tn, '**', result);
              end;
            end;
        end;
      end
      else if (name.equals('*')) then
      begin
        for ed in sdi.snapshot.elementList do
        begin
          if (ed.path.startsWith(path) and not ed.path.substring(path.length).contains('.')) then
            for t in ed.type_List do
              if (t.code.equals('Element') or t.code.equals('BackboneElement')) then
                result.add(ed.path)
              else if (t.code.equals('Resource')) then
                result.addAll(worker.getResourceNames())
              else
                result.add(t.code);
        end;
      end
      else
      begin
        if (name.endsWith('*')) then
          path := sdi.snapshot.elementList[0].path+tail+'.'+name.substring(0, name.length-1)
        else
          path := sdi.snapshot.elementList[0].path+tail+'.'+name;

        ed := getElementDefinition(sdi, path, specifiedType);
        if (ed <> nil) then
        begin
          if (specifiedType <> '') then
            result.add(specifiedType)
          else
          begin
            for t in ed.type_list do
            begin
              if (t.code = '') then
                raise Exception.create('Illegal reference to primative value attribute @ '+path);

              if (t.code.equals('Element') or t.code.equals('BackboneElement')) then
                result.add(path)
              else if (t.code.equals('Resource')) then
                result.addAll(worker.getResourceNames())
              else
                result.add(t.code);
            end;
          end;
        end;
      end;
    end;
  finally
    sdl.Free;
    sd.Free;
  end;
end;

function hasType(ed : TFhirElementDefinition; s : String) : boolean;
var
  t : TFhirElementDefinitionType;
begin
	result := false;
	for t in ed.type_List do
		if (s.equals(t.code)) then
			exit(true);
end;

function TFHIRPathEvaluator.getElementDefinition(sd : TFHIRStructureDefinition; path : String; var specifiedType : String) : TFHIRElementDefinition;
var
  ed, m : TFhirElementDefinition;
begin
  specifiedType := '';
  result := nil;
  for ed in sd.snapshot.elementList do
  begin
    if (ed.path.equals(path)) then
    begin
      if (ed.NameReference <> '') then
        exit(getElementDefinitionByName(sd, ed.NameReference))
      else
        exit(ed);
    end;

      if (ed.path.endsWith('[x]') and path.startsWith(ed.path.substring(0, ed.path.length-3)) and hasType(ed, path.Substring(ed.path.length-3))) then
      begin
        specifiedType := path.Substring(ed.path.length-3);
        exit(ed);
      end;
      if ((ed.NameReference <> '') and path.startsWith(ed.path+'.')) then
      begin
        m := getElementDefinitionByName(sd, ed.NameReference);
        exit(getElementDefinition(sd, m.path+path.substring(ed.path.length), specifiedType));
      end;
  end;
end;

function TFHIRPathEvaluator.hasDataType(ed : TFhirElementDefinition) : boolean;
begin
  result := (ed.type_List.Count > 0) and not (ed.type_list[0].code.equals('Element') or ed.type_list[0].code.equals('BackboneElement'));
end;

function TFHIRPathEvaluator.getElementDefinitionByName(sd : TFHIRStructureDefinition; name : String) : TFHIRElementDefinition;
var
  ed : TFhirElementDefinition;
begin
  for ed in sd.snapshot.elementList do
    if (name.equals(ed.name)) then
      exit(ed);
  result := nil;
end;

{ EFHIRPath }

constructor EFHIRPath.create(path: String; offset: integer; problem: String);
begin
  inherited create('FHIRPath error in "'+path+'" at position '+inttostr(offset)+': '+problem);
end;

{ TFHIRPathLexer }

constructor TFHIRPathLexer.Create(path: String);
begin
  inherited Create;
  FPath := path;
  FCursor := 1;
  FCurrentLocation.line := 1;
  FCurrentLocation.col := 1;
  next;
end;

destructor TFHIRPathLexer.Destroy;
begin

  inherited;
end;

function isWhitespace(ch : char) : Boolean;
begin
  result := CharInSet(ch, [#9, #10, #13, ' ']);
end;

procedure TFHIRPathLexer.next;
  procedure Grab(length : Integer);
  begin
    FCurrent := copy(FPath, FCurrentStart, length);
    inc(FCursor, length);
  end;
var
  ch : char;
  escape : boolean;
  flast13 : boolean;
begin
  FCurrent := '';
  flast13 := false;
  while (FCursor <= FPath.Length) and isWhitespace(FPath[FCursor]) do
  begin
    if FPath[FCursor] = #13 then
    begin
      inc(FCurrentLocation.line);
      FCurrentLocation.col := 1;
      flast13 := true;
    end
    else if not flast13 and (FPath[FCursor] = #10) then
    begin
      inc(FCurrentLocation.line);
      FCurrentLocation.col := 1;
      flast13 := false;
    end
    else
      flast13 := false;
    inc(FCursor);
  end;
  FCurrentStart := FCursor;
  FCurrentStartLocation := FCurrentLocation;
  if (FCursor <= FPath.Length) then
  begin
    ch := FPath[FCursor];
    if charInSet(ch, ['!', '>', '<']) then
    begin
      if (FCursor < FPath.Length) and (FPath[FCursor+1] = '=') then
        Grab(2)
      else
        Grab(1);
    end
    else if ch = '*' then
    begin
      if (FCursor < FPath.Length) and (FPath[FCursor+1] = '*') then
        Grab(2)
      else
        Grab(1);
    end
    else if CharInSet(ch, ['0'..'9']) then
    begin
      while (FCursor <= FPath.Length) and CharInSet(FPath[FCursor], ['0'..'9', '.']) do
        inc(FCursor);
      FCurrent := copy(FPath, FCurrentStart, FCursor-FCurrentStart);
    end
    else if CharInSet(ch, ['A'..'Z', 'a'..'z']) then
    begin
      while (FCursor <= FPath.Length) and CharInSet(FPath[FCursor], ['A'..'Z', 'a'..'z', '0'..'9', '[', ']', '*']) do
        inc(FCursor);
      FCurrent := copy(FPath, FCurrentStart, FCursor-FCurrentStart);
    end
    else if (ch = '%') then
    begin
      inc(FCursor);
      while (FCursor <= FPath.Length) and CharInSet(FPath[FCursor], ['A'..'Z', 'a'..'z', '0'..'9', ':', '-']) do
        inc(FCursor);
      FCurrent := replaceFixedConstant(copy(FPath, FCurrentStart, FCursor-FCurrentStart));
    end
    else if (ch = '$') then
    begin
      inc(FCursor);
      while (FCursor <= FPath.Length) and CharInSet(FPath[FCursor], ['a'..'z']) do
        inc(FCursor);
      FCurrent := copy(FPath, FCurrentStart, FCursor-FCurrentStart);
    end
    else if (ch = '"') or (ch = '''') then
    begin
      inc(FCursor);
      escape := false;
      while (FCursor <= FPath.length) and (escape or (FPath[FCursor] <> ch)) do
      begin
        if (escape) then
          escape := false
        else
          escape := (FPath[FCursor] = '\');
        if CharInSet(FPath[FCursor], [#13, #10, #9]) then
          raise Exception.Create('illegal character in string');
        inc(FCursor);
      end;
      if (FCursor > FPath.length) then
        raise error('Unterminated string');
      inc(FCursor);
      FCurrent := copy(FPath, FCurrentStart, FCursor-FCurrentStart);
      if (ch = '''') then
        FCurrent := '"'+copy(FCurrent, 2, FCurrent.Length - 2)+'"';
    end
    else // if CharInSet(ch, ['.', ',', '(', ')', '=']) then
      Grab(1);
  end;
  inc(FCurrentLocation.col, FCursor - FCurrentStart);
end;


function TFHIRPathLexer.nextId: integer;
begin
  inc(FId);
  result := FId;
end;

function TFHIRPathLexer.replaceFixedConstant(const s: String): String;
begin
  if s = '%sct' then
    result := '"http://snomed.info/sct"'
  else if s = '%loinc' then
    result := '"http://loinc.org"'
  else if s = '%ucum' then
    result := '"http://unitsofmeasure.org"'
  else if s = '%uz-zip' then
    result := '"[0-9]{5}(-[0-9]{4}){0,1}"'
  else if s.StartsWith('%vs-') then
    result := '"http://hl7.org/fhir/ValueSet/'+s.Substring(4)+'"'
  else if s.StartsWith('%ext-') then
    result := '"http://hl7.org/fhir/StructureDefinition/'+s.Substring(5)+'"'
  else
    raise error('Unknown fixed constant '+s);
end;

function TFHIRPathLexer.done: boolean;
begin
  result := FCurrentStart > FPath.Length;
end;

function TFHIRPathLexer.error(msg: String; offset: integer): Exception;
begin
  result := Exception.Create('Error in '+FPath+' at '+inttostr(offset)+': '+msg);
end;

function TFHIRPathLexer.error(msg: String): Exception;
begin
  result := error(msg, FCurrentStart);
end;

function TFHIRPathLexer.isConstant: boolean;
begin
  result := (FCurrent <> '') and (CharInSet(FCurrent[1], ['"', '0'..'9']) or (FCurrent = 'true') or (FCurrent = 'false'));
end;

function TFHIRPathLexer.isOp: boolean;
begin
  result := (current <> '') and StringArrayExistsSensitive(CODES_TFHIRPathOperation, current);
end;

function TFHIRPathLexer.isToken: boolean;
var
  i : integer;
begin
  if current = '' then
    result := false
  else if current.StartsWith('$') then
    result := true
  else if StringArrayExistsSensitive(['*', '**'], current) then
    result := true
  else if CharInSet(current[1], ['A'..'Z', 'a'..'z']) then
  begin
    result := true;
    for i := 1 to length(current) do
      result := result and (CharInSet(current[i], ['A'..'Z', 'a'..'z', '0'..'9', '[', ']']) or ((i = current.Length) and (current[i] = '*')));
  end
  else
    result := false;
end;

function TFHIRPathLexer.take: String;
begin
  result := current;
  next;
end;

{ TFHIRPathTests }

class procedure TFHIRPathTests.runTests;
begin
//  test('aggregation.empty() or (code = "Reference")');
//  test('binding.empty() or type.code.empty() or type.any((code = ''code'') or (code = ''Coding'') or (code=''CodeableConcept'') or (code = ''Quantity'') or (code = ''Extension'') or (code = ''string'') or (code = ''uri''))');
//  test('(low.empty() or ((low.code = "%") and (low.system = %ucum))) and (high.empty() or ((high.code = "%") and (high.system = %ucum)))');
//  test('kind != ''root'' or uniqueId in (''uuid'' | ''ruid'')');
  test('reference.startsWith("#").not() or $resource.contained.where(id = $context.reference.substring(1))');
  test('(name.item(1).family | name.item(2).family).count() < 4');
end;

class procedure TFHIRPathTests.test(expr: String);
var
  parser : TFHIRPathEvaluator;
begin
  parser := TFHIRPathEvaluator.create(nil);
  try
    parser.parse(expr).Free;
  finally
    parser.Free;
  end;
end;

{ TFHIRPathExecutionTypeContext }

constructor TFHIRPathExecutionTypeContext.Create(appInfo: TAdvObject; resourceType, context: String);
begin
  inherited Create;
  FAppInfo := appInfo;
  FResourceType := resourceType;
  FContext := context;
end;

destructor TFHIRPathExecutionTypeContext.Destroy;
begin
  FAppInfo.Free;
  inherited;
end;

{ TFHIRPathExecutionContext }

constructor TFHIRPathExecutionContext.Create(appInfo: TAdvObject; resource: TFHIRResource; context: TFHIRBase);
begin
  inherited Create;
  FAppInfo := appInfo;
  FResource := resource;
  FContext := context;
end;

destructor TFHIRPathExecutionContext.Destroy;
begin
  FAppInfo.Free;
  FResource.Free;
  FContext.Free;
  inherited;
end;

function TFHIRPathExecutionContext.Link: TFHIRPathExecutionContext;
begin
  result := TFHIRPathExecutionContext(inherited Link);
end;

{ TFHIRPathDebugPackage }

destructor TFHIRPathDebugPackage.destroy;
begin
  Fcontext.Free;
  Finput2.Free;
  Finput1.Free;
  FExpression.Free;
  Foutcome.Free;
  inherited;
end;

function TFHIRPathDebugPackage.Link: TFHIRPathDebugPackage;
begin
  result := TFHIRPathDebugPackage(inherited Link);
end;

procedure TFHIRPathDebugPackage.Setcontext(const Value: TFHIRPathExecutionContext);
begin
  Fcontext.Free;
  Fcontext := Value;
end;

procedure TFHIRPathDebugPackage.SetExpression(const Value: TFHIRExpressionNode);
begin
  FExpression.Free;
  FExpression := Value;
end;

procedure TFHIRPathDebugPackage.Setinput1(const Value: TFHIRBaseList);
begin
  Finput1.Free;
  Finput1 := Value;
end;

procedure TFHIRPathDebugPackage.Setinput2(const Value: TFHIRBaseList);
begin
  Finput2.Free;
  Finput2 := Value;
end;

procedure TFHIRPathDebugPackage.Setoutcome(const Value: TFHIRBaseList);
begin
  Foutcome.Free;
  Foutcome := Value;
end;

end.

(*
{ TFHIRQueryProcessor }

constructor TFHIRQueryProcessor.Create;
begin
  inherited;
  FResults := TFHIRObjectList.Create;
  FSource := TFHIRObjectList.Create;
end;

destructor TFHIRQueryProcessor.Destroy;
begin
  FSource.Free;
  FResults.Free;
  inherited;
end;

procedure TFHIRQueryProcessor.execute;
var
  src, seg : String;
  i : integer;
  first : boolean;
  list : TFhirReferenceList;
begin
  src := FPath;
  if (src = '*') and (FSource[0] is TFHIRResource) then
  begin
    list := TFhirReferenceList.Create;
    try
      listReferences(FSource[0] as TFHIRResource, list);
      FResults.AddAll(list);
    finally
      list.Free;
    end;
  end
  else
begin
  first := true;
  while (src <> '') do
  begin
    StringSplit(src, '.', seg, src);
    if (not IsValidIdent(seg)) Then
      raise exception.create('unable to parse path "'+FPath+'"');
    FResults.clear;
    if first then
      for i := 0 to FSource.count - 1 Do
      begin
        if FSource[i].ClassName = 'TFhir'+seg then
          FResults.add(FSource[i].Link);
      end
    else
      for i := 0 to FSource.count - 1 Do
        FSource[i].GetChildrenByName(seg, FResults);
    first := false;
    for i := FResults.count- 1 downto 0 do
      if (FResults[i] = nil) then
        FResults.DeleteByIndex(i);
    if src <> '' then
    begin
      FSource.Free;
      FSource := FResults;
      FResults := TFHIRObjectList.Create;
      end;
    end;
  end;
end;


*)
