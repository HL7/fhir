Unit FHIRValidator;

{
Copyright (c) 2001-2013, Health Intersections Pty Ltd (http://www.healthintersections.com.au)
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

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 'AS IS' AND
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

Uses
  SysUtils, Classes,
  IdSoapMsXml, MsXmlParser,
  AdvObjects, AdvGenerics, AdvJSON, AdvObjectLists,
  FHIRBase, FHIRResources, FHIRTypes;

Type
  TContext = class
    function fetchResource<T : TFHIRResource, constructor>(url : String) : T; //virtual; abstract;
  end;
  
  TValidationMessage = class (TAdvObject);

  TWrapperElement = class (TAdvObject)
  private
    FOwned : TAdvObjectList;
    function own(obj : TWrapperElement) : TWrapperElement;
  public
    Constructor Create; Override;
    Destructor Destroy; Override;
    function Link : TWrapperElement; overload;
    function getNamedChild(name : String) : TWrapperElement; virtual; abstract;
    function getFirstChild() : TWrapperElement; virtual; abstract;
    function getNextSibling() : TWrapperElement; virtual; abstract;
    function getName() : String; virtual; abstract;
    function getResourceType() : String; virtual; abstract;
    function getNamedChildValue(name : String) : String; virtual; abstract;
    procedure getNamedChildren(name : String; list : TAdvList<TWrapperElement>); virtual; abstract;
    function getAttribute(name : String) : String; virtual; abstract;
    procedure getNamedChildrenWithWildcard(name : String; list : TAdvList<TWrapperElement>); virtual; abstract;
    function hasAttribute(name : String) : boolean; virtual; abstract;
    function getNamespace() : String; virtual; abstract;
    function isXml() : boolean; virtual; abstract;
    function getText() : String; virtual; abstract;
		function hasNamespace(s : String) : boolean; virtual; abstract;
		function hasProcessingInstruction() : boolean; virtual; abstract;
		function line : Integer; virtual; abstract;
		function col : Integer; virtual; abstract;
  end;

  TNodeStack = class (TAdvObject)
  private
    xml : boolean;
    parent : TNodeStack;
    literalPath : String; // xpath format
    logicalPaths : TStringList; // dotted format, various entry points
    element : TWrapperElement;
    definition : TFHIRElementDefinition;
    type_ : TFHIRElementDefinition;
    extension : TFHIRElementDefinition;
    function push(element : TWrapperElement; count : integer;  definition : TFHIRElementDefinition; type_ : TFHIRElementDefinition) : TNodeStack;
		function addToLiteralPath(path : Array of String) : String;
  public
    Constructor Create(xml : boolean);
  end;

	TElementInfo = class (TAdvObject)
  private
    name : String;
		element : TWrapperElement;
		path : String;
		definition : TFHIRElementDefinition;
    count : integer;
    function line : integer;
    function col : integer;
  public
		Constructor create(name : String; element : TWrapperElement; path : String; count : integer);
  end;

  TChildIterator = class (TAdvObject)
  private
  public
    constructor Create(path : String; element : TWrapperElement);
    function next : boolean;
    function name : String;
    function element : TWrapperElement;
    function path : String;
    function count : integer;
  end;

  TBestPracticeWarningLevel = (bpwlIgnore, bpwlHint, bpwlWarning, bpwlError);
  TCheckDisplayOption = (cdoIgnore, cdopCheck, cdoCheckCaseAndSpace, cdoCheckCase, cdoCheckSpace);

  TFHIRInstanceValidator = class (TAdvObject)
  private
    // configuration items
    FContext : TContext;
    FCheckDisplay : TCheckDisplayOption;
    FBPWarnings : TBestPracticeWarningLevel;
    FSuppressLoincSnomedMessages : boolean;
    FRequireResourceId : boolean;
    FIsAnyExtensionsAllowed : boolean;

    function rule(errors : TAdvList<TValidationMessage>; t : TFhirIssueType; line, col : integer; path : String; thePass : boolean; msg : String) : boolean; overload;
    function rule(errors : TAdvList<TValidationMessage>; t : TFhirIssueType; path : String; thePass : boolean; msg : String) : boolean; overload;
    function warning(errors : TAdvList<TValidationMessage>; t : TFhirIssueType; line, col : integer; path : String; thePass : boolean; msg : String) : boolean;
    function hint(errors : TAdvList<TValidationMessage>; t : TFhirIssueType; line, col : integer; path : String; thePass : boolean; msg : String) : boolean;
    procedure bpCheck(errors : TAdvList<TValidationMessage>; t : TFhirIssueType; line, col : integer; literalPath : String; test : boolean; message : String);

    function empty(element : TWrapperElement) : boolean;
    Function resolveInBundle(entries : TAdvList<TWrapperElement>; ref, fullUrl, type_, id : String) : TWrapperElement;
    function getProfileForType(type_ : String) : TFHIRStructureDefinition;
    function nameMatches(name, tail : String) : boolean;
    function sliceMatches(element : TWrapperElement; path : String; slice, ed: TFHIRElementDefinition; profile : TFHIRStructureDefinition) : boolean;

    procedure validateSections(errors : TAdvList<TValidationMessage>; entries : TAdvList<TWrapperElement>; focus : TWrapperElement; stack : TNodeStack; fullUrl, id : String);
    procedure validateBundleReference(errors : TAdvList<TValidationMessage>; entries : TAdvList<TWrapperElement>; ref : TWrapperElement; name : String; stack : TNodeStack; fullUrl, type_, id : String);
    procedure validateDocument(errors : TAdvList<TValidationMessage>; entries : TAdvList<TWrapperElement>; composition : TWrapperElement; stack : TNodeStack; fullUrl, id : String);
    procedure validateElement(errors : TAdvList<TValidationMessage>; profile : TFHIRStructureDefinition; definition : TFHIRElementDefinition; cprofile : TFHIRStructureDefinition;
                  context : TFHIRElementDefinition; element : TWrapperElement; actualType : String; stack : TNodeStack);
    procedure validateMessage(errors : TAdvList<TValidationMessage>; bundle : TWrapperElement);
    procedure validateBundle(errors : TAdvList<TValidationMessage>; bundle : TWrapperElement; stack : TNodeStack);
    procedure validateObservation(errors : TAdvList<TValidationMessage>; element : TWrapperElement; stack : TNodeStack);
    procedure checkDeclaredProfiles(errors : TAdvList<TValidationMessage>; element : TWrapperElement; stack : TNodeStack);
    procedure start(errors : TAdvList<TValidationMessage>; element : TWrapperElement; profile : TFHIRStructureDefinition; stack : TNodeStack);
    procedure validateResource(errors : TAdvList<TValidationMessage>; element : TWrapperElement; profile : TFHIRStructureDefinition; needsId :  boolean; stack : TNodeStack);
  public
    Constructor create(context : TContext);
    Destructor Destroy; Override;
    Property CheckDisplay : TCheckDisplayOption read FCheckDisplay write FCheckDisplay;
    Property BPWarnings : TBestPracticeWarningLevel read FBPWarnings write FBPWarnings;
    Property SuppressLoincSnomedMessages : boolean read FSuppressLoincSnomedMessages write FSuppressLoincSnomedMessages;
    Property RequireResourceId : boolean read FRequireResourceId write FRequireResourceId;
    Property IsAnyExtensionsAllowed : boolean read FIsAnyExtensionsAllowed write FIsAnyExtensionsAllowed;

    procedure validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement; profile : String); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement; profile : TFHIRStructureDefinition); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject; profile : TFHIRStructureDefinition); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject; profile : String); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2; profile : String); overload;
    procedure validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2; profile : TFHIRStructureDefinition); overload;
  end;

implementation

uses
  FHIRParserBase, FHIRUtilities;
  
{ TElementInfo }

Constructor TElementInfo.create(name : String; element : TWrapperElement; path : String; count : integer);
begin
  inherited create;
  self.name := name;
  self.element := element;
  self.path := path;
  self.count := count;
end;

function TElementInfo.line : integer;
begin
  result := element.line;
end;

function TElementInfo.col : integer;
begin
  result := element.col;
end;

{ TWrapperElement }

Constructor TWrapperElement.Create;
begin
  inherited Create;
  FOwned := TAdvObjectList.create;
end;

Destructor TWrapperElement.Destroy;
begin
  FOwned.Free;
  inherited Destroy;
end;

function TWrapperElement.Link: TWrapperElement;
begin
  result := TWrapperElement(inherited Link);
end;

function TWrapperElement.own(obj : TWrapperElement) : TWrapperElement;
begin
  Fowned.Add(Obj);
  result := obj;
end;


type
  TDOMWrapperElement = class (TWrapperElement)
  private
    FElement : IXmlDomElement;
  public
    Constructor Create(element : IXMLDOMElement);
    function getNamedChild(name : String) : TWrapperElement; override;
    function getFirstChild() : TWrapperElement; override;
    function getNextSibling() : TWrapperElement; override;
    function getName() : String; override;
    function getResourceType() : String; override;
    function getNamedChildValue(name : String) : String; override;
    procedure getNamedChildren(name : String; list : TAdvList<TWrapperElement>); override;
    function getAttribute(name : String) : String; override;
    procedure getNamedChildrenWithWildcard(name : String; list : TAdvList<TWrapperElement>); override;
    function hasAttribute(name : String) : boolean; override;
    function getNamespace() : String; override;
    function isXml() : boolean; override;
    function getText() : String; override;
		function hasNamespace(s : String) : boolean; override;
		function hasProcessingInstruction() : boolean; override;
		function line : Integer; override;
		function col : Integer; override;
  end;

{ TDOMWrapperElement }

constructor TDOMWrapperElement.Create(element: IXMLDOMElement);
begin
  inherited create;
  FElement := element;
end;

function TDOMWrapperElement.getAttribute(name: String): String;
begin
  result := FElement.getAttribute(name);
end;

function TDOMWrapperElement.getFirstChild: TWrapperElement;
var
  res : IXmlDomElement;
begin
  res := TMsXmlParser.FirstChild(FElement);
  if res = nil then
    result := nil
  else
    result := own(TDOMWrapperElement.Create(res));
end;

function TDOMWrapperElement.getName: String;
begin
  result := FElement.nodeName;
end;

function TDOMWrapperElement.getNamedChild(name: String): TWrapperElement;
var
  res : IXmlDomElement;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) and (res.nodeName <> name) and (res.tagName <> name) do
    res := TMsXmlParser.NextSibling(res);
  if res = nil then
    result := nil
  else
    result := own(TDOMWrapperElement.Create(res));
end;

procedure TDOMWrapperElement.getNamedChildren(name: String; list: TAdvList<TWrapperElement>);
var
  res : IXmlDomElement;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) do
  begin
    if (res.nodeName = name) OR (res.tagName = name) then
      list.Add(own(TDOMWrapperElement.Create(res)));
    res := TMsXmlParser.NextSibling(res);
  end;
end;

procedure TDOMWrapperElement.getNamedChildrenWithWildcard(name: String; list: TAdvList<TWrapperElement>);
var
  res : IXmlDomElement;
  n : String;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) do
  begin
    n := res.nodeName; // OR res.tagName
    if (n = name) or ((name.endsWith('[x]') and (n.startsWith(name.subString(0, name.Length - 3))))) then
      list.Add(own(TDOMWrapperElement.Create(res)));
    res := TMsXmlParser.NextSibling(res);
  end;
end;

function TDOMWrapperElement.getNamedChildValue(name: String): String;
var
  res : IXmlDomElement;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) and (res.nodeName <> name) and (res.tagName <> name) do
    res := TMsXmlParser.NextSibling(res);
  if res = nil then
    result := ''
  else
    result := res.getAttribute('value');
end;

function TDOMWrapperElement.getNamespace: String;
begin
  result := FElement.namespaceURI;
end;

function TDOMWrapperElement.getNextSibling: TWrapperElement;
var
  res : IXmlDomElement;
begin
  res := TMsXmlParser.NextSibling(FElement);
  if res = nil then
    result := nil
  else
    result := own(TDOMWrapperElement.Create(res));
end;

function TDOMWrapperElement.getResourceType: String;
begin
  result := FElement.nodeName;
end;

function TDOMWrapperElement.getText: String;
begin
  result := FElement.text;
end;

function TDOMWrapperElement.hasAttribute(name: String): boolean;
begin
  result := FElement.getAttribute(name) <> '''';
end;

function TDOMWrapperElement.hasNamespace(s: String): boolean;
var
  i : integer;
  a : IXMLDOMNode;
  n : String;
begin
  result := false;
  for i := 0 to FElement.attributes.length - 1 do
  begin
    a := FElement.attributes.item[i];
    n := a.nodeName;
  	if ((n = 'xmlns') or n.startsWith('xmlns:')) and (a.Text = s) then
    	result := true;
  end;
end;

function TDOMWrapperElement.hasProcessingInstruction: boolean;
var
  node : IXMLDOMNode;
begin
  result := false;
  node := FElement.FirstChild;
  while (node <> nil) do
  begin
   	if (node.NodeType = NODE_PROCESSING_INSTRUCTION) then
      result := true;
  	node := node.NextSibling;
  end;
end;

function TDOMWrapperElement.isXml: boolean;
begin
  result := true;
end;

function TDOMWrapperElement.line: Integer;
begin
  result := 0; // todo: FElement.;
end;


function TDOMWrapperElement.col: Integer;
begin
  result := 0; // todo: FElement.;
end;

type
  TJsonWrapperElement = class (TWrapperElement)
  private
    path : String;
    element : TJsonNode;
    _element : TJsonNode;
    name : String;
    resourceType : String;
		parent : TJsonWrapperElement; // not linked
		index : Integer;
		children : TAdvList<TJsonWrapperElement>;
		procedure processChild(name : String; e : TJsonNode);
    procedure createChildren;
  public
    Constructor Create(element : TJsonObject); overload;
    Constructor Create(path, name : String; element,_element : TJsonNode; parent :  TJsonWrapperElement; index : integer); overload;
    function getNamedChild(name : String) : TWrapperElement; override;
    function getFirstChild() : TWrapperElement; override;
    function getNextSibling() : TWrapperElement; override;
    function getName() : String; override;
    function getResourceType() : String; override;
    function getNamedChildValue(name : String) : String; override;
    procedure getNamedChildren(name : String; list : TAdvList<TWrapperElement>); override;
    function getAttribute(name : String) : String; override;
    procedure getNamedChildrenWithWildcard(name : String; list : TAdvList<TWrapperElement>); override;
    function hasAttribute(name : String) : boolean; override;
    function getNamespace() : String; override;
    function isXml() : boolean; override;
    function getText() : String; override;
		function hasNamespace(s : String) : boolean; override;
		function hasProcessingInstruction() : boolean; override;
		function line : Integer; override;
		function col : Integer; override;
  end;

{ TJsonWrapperElement }

Constructor TJsonWrapperElement.Create(path, name : String; element,_element : TJsonNode; parent :  TJsonWrapperElement; index : integer);
begin
  inherited Create;
  self.path := path+'/'+name;
  self.name := name;
  self.element := element;
  if (element is TJsonObject and TJsonObject(element).has('resourceType')) then
   	self.resourceType := TJsonObject(element).str['resourceType'];
  self._element := _element;
  self.parent := parent;
  self.index := index;
  createChildren();
end;

Constructor TJsonWrapperElement.Create(element : TJsonObject);
begin
  inherited Create;
  self.name := '';
  self.resourceType := element.str['resourceType'];
  self.element := element;
  self.path := '';
  createChildren();
end;

procedure TJsonWrapperElement.createChildren;
var
  i : integer;
  obj : TJsonObject;
begin
// writeln('  ..: '+path);
	// we''re going to make this look like the XML
	if (element = nil) then
 		raise Exception.create('not done yet');

  if (element is TJsonValue) or (element is TJsonBoolean) then
  begin
    // we may have an element_ too
    if (_element <> nil) and (_element is TJsonObject) then
    begin
      obj := TJsonObject(_element);
      for i := 0 to obj.properties.Count - 1 do
        processChild(obj.properties.Keys[i], obj.properties[obj.properties.Keys[i]]);
    end;
  end
  else if (element is TJsonObject) then
  begin
    obj := TJsonObject(element);
    for i := 0 to obj.properties.Count - 1 do
      if obj.properties.Keys[i] <> 'resourceType' then
        processChild(obj.properties.Keys[i], obj.properties[obj.properties.Keys[i]]);
  end
  else if (element is TJsonNull) then
  begin
    // nothing to do
  end
  else
    raise Exception.create('unexpected condition: '+element.ClassName);
end;

procedure TJsonWrapperElement.processChild(name : String; e : TJsonNode);
var
  _e : TJsonNode;
  arr, _arr : TJsonArray;
  a, _a : TJsonNode;
  max, i : integer;
begin
  _e := nil;
  if (name.startsWith('_')) then
  begin
    name := name.substring(1);
    if TJsonObject(element).has(name) then
      exit;  // it will get processed anyway
    e := nil;
  end;

  if element is TJsonObject  then
    _e := TJsonObject(element).obj['_'+name];

  if (e is TJsonValue or ((e = nil) and (_e <> nil) and not (_e is TJsonArray))) then
  begin
    children.add(TJsonWrapperElement.Create(path, name, e, _e, self, children.Count));
  end
  else if (e is TJsonArray) or ((e = nil) and (_e <> nil)) then
  begin
    arr := TJsonArray(e);
		_arr := TJsonArray(_e);
    if arr <> nil then
      max :=  arr.Count;
    if (_arr <> nil) and (_arr.Count > max) then
      max := _arr.Count;
      for i := 0 to max - 1 do
      begin
        a := nil;
        _a := nil;
        if not ((arr = nil) or (arr.Count < i)) then
          a := arr[i];
        if not ((_arr = nil) or (_arr.Count < i)) then
          a := _arr[i];
        children.add(TJsonWrapperElement.create(path, name, a, _a, self, children.Count));
      end
  end
  else if (e is TJsonObject) then
  begin
	  children.add(TJsonWrapperElement.create(path, name, e, nil, self, children.Count));
  end
  else
    raise Exception.create('not done yet: '+e.className);
end;

function TJsonWrapperElement.getNamedChild(name : String) : TWrapperElement;
var
  j : TJsonWrapperElement;
begin
  result := nil;
  for j in children do
    if (j.name = name) then
      result := j;
end;

function TJsonWrapperElement.getFirstChild() : TWrapperElement;
begin
  if (children.Count = 0) then
    result := nil
  else
    result := children[0];
end;

function TJsonWrapperElement.getNextSibling() : TWrapperElement;
begin
  if (parent = nil) then
    result := nil
  else if (index >= parent.children.Count - 1) then
    result := nil
  else
    result := parent.children[0];
end;

function TJsonWrapperElement.getName() : String;
begin
  result := name;
end;

function TJsonWrapperElement.getNamedChildValue(name : String) : String;
var
  c : TWrapperElement;
begin
  c := getNamedChild(name);
  if (c = nil) then
    result := ''
  else
    result := c.getAttribute('value');
end;

procedure TJsonWrapperElement.getNamedChildren(name : String; list : TAdvList<TWrapperElement>);
var
  j : TJsonWrapperElement;
begin
  for j in children do
    if (j.name = name) then
      list.Add(j.Link);
end;

function TJsonWrapperElement.getAttribute(name : String) : String;
var
  c : TWrapperElement;
begin
  if (name = 'value') then
  begin
    if (element = nil) then
      result := ''
    else if (element is TJsonValue) then
      result := TJsonValue(element).value
    else
      result := ''
  end
  else if (name = 'xml:id') then
  begin
    c := getNamedChild('id');
    if (c = nil) then
      result := ''
    else
      result := c.getAttribute('value');
  end
  else if (name = 'url') then
  begin
    c := getNamedChild('url');
    if (c = nil) then
      result := ''
    else
      result := c.getAttribute('value');
  end
  else
    raise Exception.create('not done yet: '+name);
end;

procedure TJsonWrapperElement.getNamedChildrenWithWildcard(name : String; list : TAdvList<TWrapperElement>);
begin
  raise Exception.create('not done yet');
end;

function TJsonWrapperElement.hasAttribute(name : String) : boolean;
begin
  if (name = 'value') then
  begin
    if (element = nil) then
      result := false
    else if (element is TJsonValue) then
      result := true
    else
      result := false;
  end
  else if (name = 'xml:id') then
  begin
    result := getNamedChild('id') <> nil;
  end
  else
    raise Exception.create('not done yet: '+name);
end;

function TJsonWrapperElement.getNamespace() : String;
begin
  raise Exception.create('not done yet');
end;

function TJsonWrapperElement.isXml() : boolean;
begin
  result := false;
end;

function TJsonWrapperElement.getText() : String;
begin
  raise Exception.create('not done yet');
end;

function TJsonWrapperElement.hasNamespace(s : String) : boolean;
begin
  raise Exception.create('not done');
end;

function TJsonWrapperElement.hasProcessingInstruction() : boolean;
begin
  result := false;
end;

function TJsonWrapperElement.getResourceType(): String;
begin
  result := resourceType;
end;

function TJsonWrapperElement.line() : Integer;
begin
  result := -1;
end;

function TJsonWrapperElement.col() : Integer;
begin
  result := -1;
end;


{ TNodeStack }

constructor TNodeStack.Create(xml: boolean);
begin
  inherited Create;
  Self.xml := xml;
end;


function tail(path : String) : String;
begin
  result := path.substring(path.lastIndexOf('.')+1);
end;

function TNodeStack.push(element : TWrapperElement; count : integer;  definition : TFHIRElementDefinition; type_ : TFHIRElementDefinition) : TNodeStack;
var
  t, lp : String;
begin
  result := TNodeStack.create(element.isXml());
  result.parent := self;
  result.element := element;
  result.definition := definition;
  if (element.isXml()) then
  begin
    if element.getNamespace() = XHTML_NS then
      result.literalPath := literalPath + '/h:' + element.getName()
    else
      result.literalPath := literalPath + '/f:' +element.getName();
    if (count > -1) then
      result.literalPath := result.literalPath + '['+Integer.toString(count)+']';
  end 
  else 
  begin
    if (element.getName() = '') then
      result.literalPath := ''
    else
      result.literalPath := literalPath + '/' +element.getName();
    if (count > -1) then
      result.literalPath := result.literalPath + '/'+inttostr(count);
  end;
  result.logicalPaths := TStringList.create;
  if (type_ <> nil) then
  begin
    // type will be bull if we on a stitching point of a contained resource, or if....
    result.type_ := type_;
    t := tail(definition.Path);
    for lp in logicalPaths do 
    begin
      result.logicalPaths.add(lp+'.'+t);
      if (t.endsWith('[x]')) then
        result.logicalPaths.add(lp+'.'+t.substring(0, t.length-3)+type_.Path);
    end;
    result.logicalPaths.add(type_.Path);
  end
  else if (definition <> nil) then
  begin
    for lp in LogicalPaths do
      result.logicalPaths.add(lp+'.'+element.getName());
  end
  else
    result.logicalPaths.AddStrings(logicalPaths);
end;

function TNodeStack.addToLiteralPath(path : Array of String) : String;
var
  b : TStringBuilder;
  p : String;
begin    
  b := TStringBuilder.Create;
  try
    b.append(literalPath);
    if (xml) then
    begin
      for p in path do
      begin
        if (p.startsWith(':')) then
        begin
          b.append('[');
          b.append(p.substring(1));
          b.append(']');
        end 
        else 
        begin
          b.append('/f:');
          b.append(p);
        end
      end
    end
    else 
    begin
      for p in path do
      begin
        b.append('/');
        if p.startsWith(':') then
        begin
          b.append(p.substring(1));
        end 
        else
        begin
          b.append(p);
        end
      end
    end;
    result := b.toString();
  finally
    b.free;
  end;
end;

{ TContext }

function TContext.fetchResource<T>(url: String): T;
var
  tc : TFhirResourceClass;
begin
  tc := t;
//  if tc = TFhirStructureDefinition then
  result := nil;
end;

{ TFHIRInstanceValidator }

constructor TFHIRInstanceValidator.create(context: TContext);
begin
  inherited Create;
  FContext := context;
end;

destructor TFHIRInstanceValidator.Destroy;
begin
  FContext.Free;
  inherited;
end;


procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement);
begin
  validate(errors, element, nil);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject);
begin
  validate(errors, obj, nil);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement; profile : String);
var
  p : TFHIRStructureDefinition;
begin
  p := FContext.fetchResource<TFHIRStructureDefinition>(profile);
  if (p = nil) then
    raise Exception.create('StructureDefinition "'+profile+'" not found');
  validate(errors, element, p);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement; profile : TFHIRStructureDefinition);
begin
  validateResource(errors, TDOMWrapperElement.Create(element), profile, FRequireResourceId, nil);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject; profile : TFHIRStructureDefinition);
begin
  validateResource(errors, TJsonWrapperElement.Create(obj), profile, FRequireResourceId, nil);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject; profile : String);
var
  p : TFHIRStructureDefinition;
begin
  p := FContext.fetchResource<TFHIRStructureDefinition>(profile);
  if (p = nil) then
    raise Exception.create('StructureDefinition "'+profile+'" not found');
  validate(errors, obj, p);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2);
begin
  validate(errors, document, nil);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2; profile : String);
var
  p : TFHIRStructureDefinition;
begin
  p := FContext.fetchResource<TFHIRStructureDefinition>(profile);
  if (p = nil) then
    raise Exception.create('StructureDefinition "'+profile+'" not found');
  validate(errors, document, p);
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2; profile : TFHIRStructureDefinition);
begin
  validateResource(errors, TDOMWrapperElement.Create(document.documentElement), profile, FRequireResourceId, nil);
end;

{
* The actual base entry point
}
procedure TFHIRInstanceValidator.validateResource(errors : TAdvList<TValidationMessage>; element : TWrapperElement; profile : TFHIRStructureDefinition; needsId : boolean; stack : TNodeStack);
var
  ok : boolean;
  resourceName : String;
  type_ : String;
begin
  if (stack = nil) then
    stack := TNodeStack.create(element.isXml());

// getting going - either we got a profile, or not.
   ok := true;
   if (element.isXml()) then
    ok := rule(errors, IssueTypeINVALID, element.line(), element.col(), '/', element.getNamespace().equals(FHIR_NS), 'Namespace mismatch - expected "'+FHIR_NS+'", found "'+element.getNamespace()+'"');
   if (ok) then
   begin
     resourceName := element.getResourceType();
     if (profile = nil) then
     begin
      profile := Fcontext.fetchResource<TFHIRStructureDefinition>('http://hl7.org/fhir/StructureDefinition/'+resourceName);
      ok := rule(errors, IssueTypeINVALID, element.line(), element.col(), stack.addToLiteralPath(resourceName), profile <> nil, 'No profile found for resource type "'+resourceName+'"');
     end
     else
     begin
       if profile.ConstrainedType <> '' then
         type_ :=  profile.ConstrainedType
        else
         type_ := profile.Name;
        ok := rule(errors, IssueTypeINVALID, -1, -1, stack.addToLiteralPath(resourceName), type_ = resourceName, 'Specified profile type was "'+profile.ConstrainedType+'", but resource type was "'+resourceName+'"');
     end;
   end;

  if (ok) then
  begin
    stack := stack.push(element, -1, profile.Snapshot.ElementList[0], profile.Snapshot.ElementList[0]);
    if (needsId) and ((element.getNamedChild('id') = nil)) then
      rule(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, false, 'Resource has no id');
    start(errors, element, profile, stack); // root is both definition and type
  end;
end;

// we assume that the following things are true:
// the instance at root is valid against the schema and schematron
// the instance validator had no issues against the base resource profile
// profile is valid, and matches the resource name
procedure TFHIRInstanceValidator.start(errors : TAdvList<TValidationMessage>; element : TWrapperElement; profile : TFHIRStructureDefinition; stack : TNodeStack);
begin
  if (rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), stack.literalPath, profile.Snapshot <> nil, 'StructureDefinition has no snapshot - validation is against the snapshot, so it must be provided')) then
  begin
    validateElement(errors, profile, profile.Snapshot.ElementList[0], nil, nil, element, element.getName(), stack);
    checkDeclaredProfiles(errors, element, stack);

    // specific known special validations
    if (element.getResourceType() = 'Bundle') then
      validateBundle(errors, element, stack);
    if (element.getResourceType() = 'Observation') then
      validateObservation(errors, element, stack);
  end;
end;

procedure TFHIRInstanceValidator.checkDeclaredProfiles(errors : TAdvList<TValidationMessage>; element : TWrapperElement; stack : TNodeStack);
var
  meta : TWrapperElement;
  profiles : TAdvList<TWrapperElement>;
  i : integer;
  profile : TWrapperElement;
  ref, p : String;
  pr : TFHIRStructureDefinition;
begin
  meta := element.getNamedChild('meta');
  if (meta <> nil) then
  begin
    profiles := TAdvList<TWrapperElement>.create();
    meta.getNamedChildren('profile', profiles);
    i := 0;
    for profile in profiles do
    begin
      ref := profile.getAttribute('value');
      p := stack.addToLiteralPath(['meta', 'profile', ':'+inttostr(i)]);
      if (rule(errors, IssueTypeINVALID, element.line(), element.col(), p, ref <> '', 'StructureDefinition reference invalid')) then
      begin
        pr := Fcontext.fetchResource<TFHIRStructureDefinition>(ref);
          if (warning(errors, IssueTypeINVALID, element.line(), element.col(), p, pr <> nil, 'StructureDefinition reference could not be resolved')) then
          begin
            if (rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), p, pr.Snapshot <> nil, 'StructureDefinition has no snapshot - validation is against the snapshot, so it must be provided')) then
            begin
              validateElement(errors, pr, pr.Snapshot.ElementList[0], nil, nil, element, element.getName, stack);
            end;
          end;
          inc(i);
        end;
      end;
    end;
  end;

procedure TFHIRInstanceValidator.validateBundle(errors : TAdvList<TValidationMessage>; bundle : TWrapperElement; stack : TNodeStack);
var
  entries : TAdvList<TWrapperElement>;
  type_, id : String;
  firstEntry : TWrapperElement;
  firstStack : TNodeStack;
  fullUrl : String;
  resource, res : TWrapperElement;
  localStack : TNodeStack;
begin
  entries := TAdvList<TWrapperElement>.create();
  bundle.getNamedChildren('entry', entries);
  type_ := bundle.getNamedChildValue('type');
  if (entries.Count = 0) then
  begin
    rule(errors, IssueTypeINVALID, stack.literalPath, (type_ <> 'document') and (type_ <> 'message'), 'Documents or Messages must contain at least one entry');
  end
  else
  begin
    firstEntry := entries[0];
    firstStack := stack.push(firstEntry, 0, nil, nil);
    fullUrl := firstEntry.getNamedChildValue('fullUrl');

    if (type_ = 'document') then
    begin
      res := firstEntry.getNamedChild('resource');
      localStack := firstStack.push(res, -1, nil, nil);
      resource := res.getFirstChild();
      id := resource.getNamedChildValue('id');
      if (rule(errors, IssueTypeINVALID, firstEntry.line(), firstEntry.col(), stack.addToLiteralPath(['entry', ':0']), res <> nil, 'No resource on first entry')) then
      begin
        if (bundle.isXml()) then
          validateDocument(errors, entries, resource, localStack.push(resource, -1, nil, nil), fullUrl, id)
        else
          validateDocument(errors, entries, res, localStack, fullUrl, id);
      end;
    end;
    if (type_ = 'message') then
      validateMessage(errors, bundle);
  end;
end;

procedure TFHIRInstanceValidator.validateMessage(errors : TAdvList<TValidationMessage>; bundle : TWrapperElement);
begin
end;


procedure TFHIRInstanceValidator.validateDocument(errors : TAdvList<TValidationMessage>; entries : TAdvList<TWrapperElement>; composition : TWrapperElement; stack : TNodeStack; fullUrl, id : String);
begin
  // first entry must be a composition
  if (rule(errors, IssueTypeINVALID, composition.line(), composition.col(), stack.literalPath, composition.getResourceType() = 'Composition', 'The first entry in a document must be a composition')) then
  begin
    // the composition subject and section references must resolve in the bundle
    validateBundleReference(errors, entries, composition.getNamedChild('subject'), 'Composition Subject', stack.push(composition.getNamedChild('subject'), -1, nil, nil), fullUrl, 'Composition', id);
    validateSections(errors, entries, composition, stack, fullUrl, id);
  end;
end;
//rule(errors, IssueTypeINVALID, bundle.line(), bundle.col(), 'Bundle', !'urn:guid:' = base), 'The base "urn:guid:" is not valid (use urn:uuid:)');
//rule(errors, IssueTypeINVALID, entry.line(), entry.col(), localStack.literalPath, !'urn:guid:' = ebase), 'The base "urn:guid:" is not valid');
//rule(errors, IssueTypeINVALID, entry.line(), entry.col(), localStack.literalPath, !Utilities.noString(base) ) or ( !Utilities.noString(ebase), 'entry does not have a base');
//String firstBase := nil;
//firstBase := ebase = nil ? base : ebase;

procedure TFHIRInstanceValidator.validateSections(errors : TAdvList<TValidationMessage>; entries : TAdvList<TWrapperElement>; focus : TWrapperElement; stack : TNodeStack; fullUrl, id : String);
var
  sections : TAdvList<TWrapperElement>;
  section : TWrapperElement;
  i : integer;
  localStack : TNodeStack;
begin
  sections := TAdvList<TWrapperElement>.create();
  focus.getNamedChildren('entry', sections);
  i := 0;
  for section in sections do
  begin
    localStack := stack.push(section,  1, nil, nil);
    validateBundleReference(errors, entries, section.getNamedChild('content'), 'Section Content', localStack, fullUrl, 'Composition', id);
    validateSections(errors, entries, section, localStack, fullUrl, id);
    inc(i);
  end;
end;

procedure TFHIRInstanceValidator.validateBundleReference(errors : TAdvList<TValidationMessage>; entries : TAdvList<TWrapperElement>; ref : TWrapperElement; name : String; stack : TNodeStack; fullUrl, type_, id : String);
var
  target : TWrapperElement;
begin
  if (ref <> nil) and (ref.getNamedChildValue('reference') <> '') then
  begin
    target := resolveInBundle(entries, ref.getNamedChildValue('reference'), fullUrl, type_, id);
    rule(errors, IssueTypeINVALID, target.line(), target.col(), stack.addToLiteralPath(['reference']), target <> nil, 'Unable to resolve the target of the reference in the bundle ('+name+')');
  end;
end;

function isAbsoluteUrl(s : String) : boolean;
begin
  result := false;
end;

Function TFHIRInstanceValidator.resolveInBundle(entries : TAdvList<TWrapperElement>; ref, fullUrl, type_, id : String) : TWrapperElement;
var
  entry, res, resource : TWrapperElement;
  fu, u, t, i, et, eid : String;
  parts : TArray<String>;
begin
  result := nil;
  if (isAbsoluteUrl(ref)) then
  begin
    // if the reference is absolute, then you resolve by fullUrl. No other thinking is required.
    for entry in entries do
    begin
      fu := entry.getNamedChildValue('fullUrl');
      if (ref = fu) then
      begin
        result := entry;
        exit;
      end;
    end;
  end
  else
  begin
    // split into base, type, and id
    u := '';
    if (fullUrl <> '') and (fullUrl.endsWith(type_+'/'+id)) then
      // fullUrl := complex
      u := fullUrl.substring((type_+'/'+id).length)+ref;
    parts := ref.split(['\\/']);
    if (length(parts) >= 2) then
    begin
      t := parts[0];
      i := parts[1];
      for entry in entries do
      begin
        fu := entry.getNamedChildValue('fullUrl');
        if (u <> '') and (fullUrl = u) then
        begin
          result := entry;
          exit;
        end;
        if (u = '') then
        begin
          res := entry.getNamedChild('resource');
          resource := res.getFirstChild();
          et := resource.getResourceType();
          eid := resource.getNamedChildValue('id');
          if (t = et) and (i = eid) then
            result := entry;
        end;
      end;
    end;
  end;
end;

function TFHIRInstanceValidator.getProfileForType(type_ : String) : TFHIRStructureDefinition;
begin
  result := Fcontext.fetchResource<TFHIRStructureDefinition>('http://hl7.org/fhir/StructureDefinition/'+type_);
end;

procedure TFHIRInstanceValidator.validateObservation(errors : TAdvList<TValidationMessage>; element : TWrapperElement; stack : TNodeStack);
begin
  // all observations should have a subject, a performer, and a time
  bpCheck(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, element.getNamedChild('subject') <> nil, 'All observations should have a subject');
  bpCheck(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, element.getNamedChild('performer') <> nil, 'All observations should have a performer');
  bpCheck(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, (element.getNamedChild('effectiveDateTime') <> nil) or (element.getNamedChild('effectivePeriod') <> nil), 'All observations should have an effectiveDateTime or an effectivePeriod');
end;

procedure TFHIRInstanceValidator.bpCheck(errors : TAdvList<TValidationMessage>; t: TFHIRIssueType; line, col : integer; literalPath : String; test : boolean; message : String);
 begin
  case bpWarnings of
    bpwlHint: hint(errors, t, line, col, literalPath, test, message);
    bpwlWarning: warning(errors, t, line, col, literalPath, test, message);
    bpwlError: rule(errors, t, line, col, literalPath, test, message);
    bpwlIgnore: ; // do nothing
  end;
end;

procedure TFHIRInstanceValidator.validateElement(errors : TAdvList<TValidationMessage>; profile : TFHIRStructureDefinition; definition : TFHIRElementDefinition; cprofile : TFHIRStructureDefinition;
                  context : TFHIRElementDefinition; element : TWrapperElement; actualType : String; stack : TNodeStack);
var
  childDefinitions : TAdvList<TFHIRElementDefinition>;
  children : TAdvList<TElementInfo>;
  iter : TChildIterator;
  slice, ed: TFHIRElementDefinition;
  process, match : boolean;
  ei : TElementInfo;
begin
  // irrespective of what element it is, it cannot be empty
	if (element.isXml()) then
  begin
    rule(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, FHIR_NS = element.getNamespace(), 'Namespace mismatch - expected "'+FHIR_NS+'", found "'+element.getNamespace()+'"');
    rule(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, not element.hasNamespace('http://www.w3.org/2001/XMLSchema-instance'), 'Schema Instance Namespace is not allowed in instances');
    rule(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, not element.hasProcessingInstruction(), 'No Processing Instructions in resources');
  end;
  rule(errors, IssueTypeINVALID, element.line(), element.col(), stack.literalPath, not empty(element), 'Elements must have some content (@value, extensions, or children elements)');

  // get the list of direct defined children, including slices
  childDefinitions := getChildMap(profile, definition.Name, definition.Path, definition.NameReference);

  // 1. List the children, and remember their exact path (convenience)
  children := TAdvList<TElementInfo>.create();
  iter := TChildIterator.create(stack.literalPath, element);
    while (iter.next()) do
    	children.add(TElementInfo.create(iter.name(), iter.element(), iter.path(), iter.count()));

    // 2. assign children to a definition
    // for each definition, for each child, check whether it belongs in the slice
    slice := nil;
    for ed in childDefinitions do
    begin
    	process := true;
    	// where are we with slicing
    	if (ed.Slicing <> nil) then
      begin
    		if (slice <> nil ) and (slice.Path = ed.Path) then
    			raise Exception.create('Slice encountered midway through path on '+slice.Path);
    		slice := ed;
    		process := false;
    	end
      else if (slice <> nil) and (slice.Path <> ed.Path) then
    		slice := nil;

    	if (process) then
      begin
    	  for ei in children do
        begin
    			match := false;
          if (slice = nil) then
          begin
      			match := nameMatches(ei.name, tail(ed.Path));
      		end
          else
          begin
    				if nameMatches(ei.name, tail(ed.Path)) then
    					match := sliceMatches(ei.element, ei.path, slice, ed, profile);
      		end;
    	  	if (match) then
          begin
      		  if (rule(errors, IssueTypeINVALID, ei.line(), ei.col(), ei.path, ei.definition = nil, 'Element matches more than one slice')) then
      				ei.definition := ed;
      		end;
    	  end;
      end;
  	end;
    !
    for (ElementInfo ei : children)
      if (ei.path.endsWith('.extension'))
        rule(errors, IssueTypeINVALID, ei.line(), ei.col(), ei.path, ei.definition <> nil, 'Element is unknown or does not match any slice (url:=\''+ei.element.getAttribute('url')+'\')');
      else
        rule(errors, IssueTypeINVALID, ei.line(), ei.col(), ei.path, (ei.definition <> nil) ) or ( (!ei.element.isXml() ) and ( ei.element.getName() = 'fhir_comments')), 'Element is unknown or does not match any slice');

    // 3. report any definitions that have a cardinality problem
    for (ElementDefinition ed : childDefinitions) begin
    	if (ed.getRepresentation().isEmpty()) begin // ignore xml attributes
    	int count := 0;
      for (ElementInfo ei : children)
      	if (ei.definition = ed)
      		count++;
  		if (ed.getMin() > 0) begin
  			rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), stack.literalPath, count >:= ed.getMin(), 'Element "'+stack.literalPath+'.'+tail(ed.getPath())+'": minimum required := '+inttostr(ed.getMin())+', but only found '+inttostr(count));
    		end;
  		if (ed.hasMax() ) and ( !ed.getMax() = '*')) begin
  			rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), stack.literalPath, count <:= Integer.parseInt(ed.getMax()), 'Element '+tail(ed.getPath())+' @ '+stack.literalPath+': max allowed := '+inttostr(ed.getMin())+', but found '+inttostr(count));
    		end;
      
    	end;
    end;
    // 4. check order if any slices are orderd. (todo)

    // 5. inspect each child for validity
    for (ElementInfo ei : children) begin
    	if (ei.definition <> nil) begin
      String type := nil;
      ElementDefinition typeDefn := nil;
    		if (ei.definition.getType().Count = 1 ) and ( !ei.definition.getType()[0).getCode() = '*') ) and ( !ei.definition.getType()[0).getCode() = 'Element') ) and ( !ei.definition.getType()[0).getCode() = 'BackboneElement') )
    			type := ei.definition.getType()[0).getCode();
    		else if (ei.definition.getType().Count = 1 ) and ( ei.definition.getType()[0).getCode() = '*')) begin
          String prefix := tail(ei.definition.getPath());
          assert prefix.endsWith('[x]');
          type := ei.name.substring(prefix.length()-3);
          if (isPrimitiveType(type))
            type := Utilities.uncapitalize(type);
    		end; else if (ei.definition.getType().Count > 1) begin

            String prefix := tail(ei.definition.getPath());
            assert prefix.endsWith('[x]');
            prefix := prefix.substring(0, prefix.length()-3);
            for (TypeRefComponent t : ei.definition.getType())
              if ((prefix+Utilities.capitalize(t.getCode())) = ei.name))
                type := t.getCode();
            if (type = nil) begin
        			TypeRefComponent trc := ei.definition.getType()[0);
        			if(trc.getCode() = 'Reference'))
        				type := 'Reference';
              else 
              	rule(errors, IssueTypeSTRUCTURE, ei.line(), ei.col(), stack.literalPath, false, 'The element '+ei.name+' is illegal. Valid types at this point are '+describeTypes(ei.definition.getType()));
          end;
    		end; else if (ei.definition.getNameReference() <> nil) begin
    			typeDefn := resolveNameReference(profile.getSnapshot(), ei.definition.getNameReference());
        end;


        if (type <> nil) begin
          if (type.startsWith('@')) begin
    				ei.definition := findElement(profile, type.substring(1));
            type := nil;
          end;
        end;
    		TNodeStack localStack := stack.push(ei.element, ei.count, ei.definition, type = nil ? typeDefn : resolveType(type));
    		assert(ei.path = localStack.literalPath));

      if (type <> nil) begin
        if (typeIsPrimitive(type)) 
    				checkPrimitive(errors, ei.path, type, ei.definition, ei.element);
        else begin
          if (type = 'Identifier'))
    					checkIdentifier(errors, ei.path, ei.element, ei.definition);
          else if (type = 'Coding'))
    					checkCoding(errors, ei.path, ei.element, profile, ei.definition);
          else if (type = 'CodeableConcept'))
    					checkCodeableConcept(errors, ei.path, ei.element, profile, ei.definition);
          else if (type = 'Reference'))
    					checkReference(errors, ei.path, ei.element, profile, ei.definition, actualType, localStack);

          if (type = 'Extension'))
            checkExtension(errors, ei.path, ei.element, ei.definition, profile, localStack);          
          else if (type = 'Resource'))
    					validateContains(errors, ei.path, ei.definition, definition, ei.element, localStack, !isBundleEntry(ei.path)); //    if (str.matches('.*([.,/])work\\1$'))
          else begin
            StructureDefinition p := getProfileForType(type);
            if (rule(errors, IssueTypeSTRUCTURE, ei.line(), ei.col(), ei.path, p <> nil, 'Unknown type '+type)) begin
    						validateElement(errors, p, p.Snapshot.ElementList[0), profile, ei.definition, ei.element, type, localStack);
            end;
          end;
        end;
      end; else begin
    			if (rule(errors, IssueTypeSTRUCTURE, ei.line(), ei.col(), stack.literalPath, ei.definition <> nil, 'Unrecognised Content '+ei.name))
    				validateElement(errors, profile, ei.definition, nil, nil, ei.element, type, localStack);
    		end;
      end;
    end;
  end;
    
  {*
   * 
   * @param element - the candidate that might be in the slice
   * @param path - for reporting any errors. the XPath for the element
   * @param slice - the definition of how slicing is determined
   * @param ed - the slice for which to test membership
   * @return
   * @;
   }
  private boolean sliceMatches(element : TWrapperElement, String path, ElementDefinition slice, ElementDefinition ed, profile : TFHIRStructureDefinition) ; begin
  	if (!slice.getSlicing().hasDiscriminator())
  		return false; // cannot validate in this case
	  for (StringType s : slice.getSlicing().getDiscriminator()) begin
	  	String discriminator := s.getValue();
	  	ElementDefinition criteria := getCriteriaForDiscriminator(path, ed, discriminator, profile);
	  	if (discriminator = 'url') ) and ( criteria.getPath() = 'Extension.url')) begin
	  		if (!element.getAttribute('url') = ((UriType) criteria.getFixed()).asStringValue()))
	  			return false;
	  	end; else begin
	  		Element value := getValueForDiscriminator(element, discriminator, criteria);
	  		if (!valueMatchesCriteria(value, criteria))
	  			return false;
	  	end;
	  end;
	  return true;
  end;
  
	private boolean valueMatchesCriteria(Element value, ElementDefinition criteria) begin
		throw new Error('validation of slices not done yet');
  end;
	
	private Element getValueForDiscriminator(element : TWrapperElement, String discriminator, ElementDefinition criteria) begin
		throw new Error('validation of slices not done yet');
  end;
	
	private ElementDefinition getCriteriaForDiscriminator(String path, ElementDefinition ed, String discriminator, profile : TFHIRStructureDefinition) ; begin
    List<ElementDefinition> childDefinitions := ProfileUtilities.getChildMap(profile, ed);
    List<ElementDefinition> snapshot := nil;
    if (childDefinitions.isEmpty()) begin
    	// going to look at the type
    	if (ed.getType().Count = 0)
    		raise Exception.create('Error in profile for '+path+' no children, no type');
    	if (ed.getType().Count > 1)
    		raise Exception.create('Error in profile for '+path+' multiple types defined in slice discriminator');
    	StructureDefinition type;
    	if (ed.getType()[0).hasProfile())
    		type := context.fetchResource(StructureDefinition.class, ed.getType()[0).getProfile()[0).getValue());
    	else
    		type := context.fetchResource(StructureDefinition.class, 'http://hl7.org/fhir/StructureDefinition/'+ed.getType()[0).getCode());
    	snapshot := type.getSnapshot().getElement();
    	ed := snapshot[0);
    end; else begin
      snapshot := profile.getSnapshot().getElement();
    end;
		String originalPath := ed.getPath();
		String goal := originalPath+'.'+discriminator;

		int index := snapshot.indexOf(ed);
		assert (index > -1);
		index++;
		while (index < snapshot.Count ) and ( !snapshot[index).getPath() = originalPath)) begin
			if (snapshot[index).getPath() = goal))
				return snapshot[index);
			index++;
		end;
		throw new Error('Unable to find discriminator definition for '+goal+' in '+discriminator+' at '+path);
  end;
	
  private boolean isPrimitiveType(String type) begin
    return
        type.equalsIgnoreCase('boolean') ) or ( type.equalsIgnoreCase('integer') ) or ( type.equalsIgnoreCase('string') ) or ( type.equalsIgnoreCase('decimal') ) or (
        type.equalsIgnoreCase('uri') ) or ( type.equalsIgnoreCase('base64Binary') ) or ( type.equalsIgnoreCase('instant') ) or ( type.equalsIgnoreCase('date') ) or (
        type.equalsIgnoreCase('dateTime') ) or ( type.equalsIgnoreCase('time') ) or ( type.equalsIgnoreCase('code') ) or ( type.equalsIgnoreCase('oid') ) or ( type.equalsIgnoreCase('id');
  end;
  
  private boolean nameMatches(String name, String tail) begin
	  if (tail.endsWith('[x]'))
	    return name.startsWith(tail.substring(0,  tail.length()-3)); 
	  else
	    return (name = tail));
  end;
  
  private ElementDefinition resolveNameReference(StructureDefinitionSnapshotComponent snapshot, String name) begin
  	for (ElementDefinition ed : snapshot.getElement())
  		if (name = ed.getName()))
  			return ed;
	  return nil;
  end;
  
  private ElementDefinition resolveType(String type) throws EOperationOutcome, Exception begin
    String url := 'http://hl7.org/fhir/StructureDefinition/'+type;
    StructureDefinition sd := context.fetchResource(StructureDefinition.class, url);
    if (sd = nil ) or ( !sd.hasSnapshot())
      return nil;
    else
      return sd.Snapshot.ElementList[0);
  end;
  
//  private String mergePath(String path1, String path2) begin
//    // path1 is xpath path
//    // path2 is dotted path 
//    TArray<String> parts := path2.split('\\.');
//    StringBuilder b := new StringBuilder(path1);
//    for (int i := 1; i < parts.length -1; i++)
//      b.append('/f:'+parts[i]);
//    return b.toString();
//  end;

  private boolean isBundleEntry(String path) begin
    TArray<String> parts := path.split('\\/');
    if (path.startsWith('/f:'))
      return parts.length > 2 ) and ( parts[parts.length-1].startsWith('f:resource') ) and ( (parts[parts.length-2] = 'f:entry') ) or ( parts[parts.length-2].startsWith('f:entry['));
    else
      return parts.length > 2 ) and ( parts[parts.length-1] = 'resource') ) and ( ((parts.length > 2 ) and ( parts[parts.length-3] = 'entry')) ) or ( parts[parts.length-2] = 'entry'));
  end;
  
  private String describeTypes(List<TypeRefComponent> types) begin
    CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
    for (TypeRefComponent t : types) begin
      b.append(t.getCode());
    end;
    return b.toString();
  end;

  procedure TFHIRInstanceValidator.checkReference(errors : TAdvList<TValidationMessage>, String path, element : TWrapperElement, profile : TFHIRStructureDefinition, ElementDefinition container, String parentType, stack : TNodeStack) ; begin
    String ref := element.getNamedChildValue('reference');
    if (Utilities.noString(ref)) begin
      // todo - what should we do in this case?
      hint(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path, !Utilities.noString(element.getNamedChildValue('display')), 'A Reference without an actual reference should have a display');
      return; 
    end;
    
    TWrapperElement we := resolve(ref, stack);
    String ft;
    if (we <> nil)
      ft := we.getResourceType();
    else
      ft := tryParse(ref);
    if (hint(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path, ft <> nil, 'Unable to determine type of target resource')) begin
      boolean ok := false;
      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
      for (TypeRefComponent type : container.getType()) begin
        if (!ok ) and ( type.getCode() = 'Reference')) begin
          // we validate as much as we can. First, can we infer a type from the profile? 
          if (!type.hasProfile() ) or ( type.getProfile()[0).getValue() = 'http://hl7.org/fhir/StructureDefinition/Resource'))
            ok := true;
          else begin
            String pr := type.getProfile()[0).getValue();

            String bt := getBaseType(profile, pr);
            if (rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path, bt <> nil, 'Unable to resolve the profile reference "'+pr+'"')) begin
              b.append(bt);
              ok := bt = ft);
            end; else
              ok := true; // suppress following check
          end;
        end;
        if (!ok ) and ( type.getCode() = '*')) begin
          ok := true; // can refer to anything
        end;
      end;
      rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path, ok, 'Invalid Resource target type. Found '+ft+', but expected one of ('+b.toString()+')');
    end;
  end;
  
  private TWrapperElement resolve(String ref, stack : TNodeStack) begin
    if (ref.startsWith('#')) begin
      // work back through the contained list.
      // really, there should only be one level for this (contained resources cannot contain 
      // contained resources), but we"ll leave that to some other code to worry about
      while (stack <> nil ) and ( stack.getElement() <> nil) begin
        TWrapperElement res := getContainedById(stack.getElement(), ref.substring(1));
        if (res <> nil)
          return res;
        stack := stack.parent;
      end;
      return nil;
    end; else begin
      // work back through the contained list - if any of them are bundles, try to resolve 
      // the resource in the bundle
      while (stack <> nil ) and ( stack.getElement() <> nil) begin
        if ('Bundle' = stack.getElement().getResourceType())) begin
          TWrapperElement res := getFromBundle(stack.getElement(), ref.substring(1));
          if (res <> nil)
            return res;
        end;
        stack := stack.parent;
      end;
      
      // todo: consult the external host for resolution 
      return nil;

    end;
  end;
  
  private TWrapperElement getFromBundle(TWrapperElement bundle, String ref) begin
    entries : TAdvList<TWrapperElement> := TAdvList<TWrapperElement>.create();
    bundle.getNamedChildren('entry', entries);
    for (TWrapperElement we : entries) begin
      TWrapperElement res := we.getNamedChild('resource').getFirstChild();
      if (res <> nil) begin
        String url := genFullUrl(bundle.getNamedChildValue('base'), we.getNamedChildValue('base'), res.getName(), res.getNamedChildValue('id'));
        if (url.endsWith(ref))
          return res;
      end;
    end;
    return nil;
  end;

  private String genFullUrl(String bundleBase, String entryBase, String type, String id) begin
    String base := Utilities.noString(entryBase) ? bundleBase : entryBase;
    if (Utilities.noString(base)) begin
      return type+'/'+id;
    end; else if ('urn:uuid' = base) ) or ( 'urn:oid' = base))
      return base+id;
    else 
      return Utilities.appendSlash(base)+type+'/'+id;
  end;
  
  private TWrapperElement getContainedById(TWrapperElement container, String id) begin
    TAdvList<TWrapperElement> contained := TAdvList<TWrapperElement>.create();
    container.getNamedChildren('contained', contained);
    for (TWrapperElement we : contained) begin
    	TWrapperElement res := we.isXml() ? we.getFirstChild() : we;
      if (id = res.getNamedChildValue('id')))
        return res;
    end;
    return nil;
  end;

  private String tryParse(String ref) throws EOperationOutcome, Exception begin
    TArray<String> parts := ref.split('\\/');
    switch (parts.length) begin
    case 1:
      return nil;
    case 2:
      return checkResourceType(parts[0]);
    default:
      if (parts[parts.length-2] = '_history'))
        return checkResourceType(parts[parts.length-4]);
      else
        return checkResourceType(parts[parts.length-2]);
    end;
  end;
  
  private String checkResourceType(String type) throws EOperationOutcome, Exception begin
    if (context.fetchResource(StructureDefinition.class, 'http://hl7.org/fhir/StructureDefinition/'+type) <> nil)
      return type;
    else
      return nil;
  end;
  private String getBaseType(profile : TFHIRStructureDefinition, String pr) throws EOperationOutcome, Exception begin
//    if (pr.startsWith('http://hl7.org/fhir/StructureDefinition/')) begin
//      // this just has to be a base type
//      return pr.substring(40);
//    end; else begin
      StructureDefinition p := resolveProfile(profile, pr);
      if (p = nil)
        return nil;
      else if (p.getKind() = StructureDefinitionKind.RESOURCE)
        return p.Snapshot.ElementList[0).getPath();
      else
        return p.Snapshot.ElementList[0).getType()[0).getCode();
//    end;
  end;
  
  private StructureDefinition resolveProfile(profile : TFHIRStructureDefinition, String pr) throws EOperationOutcome, Exception begin
    if (pr.startsWith('#')) begin
      for (Resource r : profile.getContained()) begin
        if (r.getId() = pr.substring(1)) ) and ( r instanceof StructureDefinition)
          return (StructureDefinition) r;
      end;
      return nil;
    end;
    else
      return context.fetchResource(StructureDefinition.class, pr);
  end;
  
  private StructureDefinition checkExtension(errors : TAdvList<TValidationMessage>, String path, element : TWrapperElement, ElementDefinition def, profile : TFHIRStructureDefinition, stack : TNodeStack) ; begin
    String url := element.getAttribute('url');
    boolean isModifier := element.getName() = 'modifierExtension');
    
    StructureDefinition ex := context.fetchResource(StructureDefinition.class, url);
    if (ex = nil) begin
      if (!rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path, allowUnknownExtension(url), 'The extension '+url+' is unknown, and not allowed here'))
        warning(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path, allowUnknownExtension(url), 'Unknown extension '+url);
    end; else begin
      if (def.getIsModifier()) 
        rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path+'[url:="'+url+'"]', ex.Snapshot.ElementList[0).getIsModifier(), 'Extension modifier mismatch: the extension element is labelled as a modifier, but the underlying extension is not');
      else
        rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path+'[url:="'+url+'"]', !ex.Snapshot.ElementList[0).getIsModifier(), 'Extension modifier mismatch: the extension element is not labelled as a modifier, but the underlying extension is');

      // two questions 
      // 1. can this extension be used here?
      checkExtensionContext(errors, element, {path+'[url:="'+url+'"]',} ex, stack, ex.getUrl());
    
      if (isModifier)
        rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path+'[url:="'+url+'"]', ex.Snapshot.ElementList[0).getIsModifier(), 'The Extension "'+url+'" must be used as a modifierExtension');
      else
        rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), path+'[url:="'+url+'"]', !ex.Snapshot.ElementList[0).getIsModifier(), 'The Extension "'+url+'" must not be used as an extension (it"s a modifierExtension)');
      
      // 2. is the content of the extension valid?

    end;
    return ex;
  end;

  private boolean allowUnknownExtension(String url) begin
    if (url.contains('example.org') ) or ( url.contains('acme.com') ) or ( url.contains('nema.org'))
    	return true;
    for (String s : extensionDomains)
    	if (url.startsWith(s))
    		return true;
    return anyExtensionsAllowed;
  end;
  
  private boolean isKnownType(String code) throws EOperationOutcome, Exception begin
    return context.fetchResource(StructureDefinition.class, code.toLowerCase()) <> nil;
  end;

  private ElementDefinition getElementByPath(StructureDefinition definition, String path) begin
    for (ElementDefinition e : definition.getSnapshot().getElement()) begin
      if (e.getPath() = path))
        return e;
    end;
    return nil;
  end;

  private boolean checkExtensionContext(errors : TAdvList<TValidationMessage>, element : TWrapperElement, StructureDefinition definition, stack : TNodeStack, String extensionParent) begin
    String extUrl := definition.getUrl();
    CommaSeparatedStringBuilder p := new CommaSeparatedStringBuilder();
    for (String lp : stack.getLogicalPaths())
      p.append(lp);
	  if (definition.getContextType() = ExtensionContext.DATATYPE) begin
	    boolean ok := false;
	    CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
	    for (StringType ct : definition.getContext()) begin
	      b.append(ct.getValue());
	      if (ct.getValue() = '*') ) or ( stack.getLogicalPaths().contains(ct.getValue()+'.extension'))
	        ok := true;
	    end;
	    return rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), stack.literalPath, ok, 'The extension '+extUrl+' is not allowed to be used on the logical path set ['+p.toString()+'] (allowed: datatype:='+b.toString()+')');
	  end; else if (definition.getContextType() = ExtensionContext.EXTENSION) begin
      boolean ok := false;
      for (StringType ct : definition.getContext()) 
        if (ct.getValue() = '*') ) or ( ct.getValue() = extensionParent))
            ok := true;
      return rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), stack.literalPath, ok, 'The extension '+extUrl+' is not allowed to be used with the extension "'+extensionParent+'"');
	  end; else if (definition.getContextType() = ExtensionContext.MAPPING) begin
  		throw new Error('Not handled yet (extensionContext)');
	  end; else if (definition.getContextType() = ExtensionContext.RESOURCE) begin
      boolean ok := false;
//      String simplePath := container.getPath();
//      System.out.println(simplePath);
//      if (effetive.endsWith('.extension') ) or ( simplePath.endsWith('.modifierExtension'))
//        simplePath := simplePath.substring(0, simplePath.lastIndexOf("."));
      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
      for (StringType ct : definition.getContext()) begin
        String c := ct.getValue();
        b.append(c);
        if (c = '*') ) or ( stack.getLogicalPaths().contains(c+'.extension') ) or ( (c.startsWith('@') ) and ( stack.getLogicalPaths().contains(c.substring(1)+'.extension')));
            ok := true;
      end;
      return rule(errors, IssueTypeSTRUCTURE, element.line(), element.col(), stack.literalPath, ok, 'The extension '+extUrl+' is not allowed to be used on the logical path set '+p.toString()+' (allowed: resource:='+b.toString()+')');
	  end; else
  		throw new Error('Unknown context type');
  end;
//
//  private String simplifyPath(String path) begin
//    String s := path.replace('/f:', '.');
//    while (s.contains('['))
//      s := s.substring(0, s.indexOf('['))+s.substring(s.indexOf(']')+1);
//    TArray<String> parts := s.split('\\.');
//    int i := 0;
//    while (i < parts.length ) and ( !context.getProfiles().containsKey(parts[i].toLowerCase()))
//      i++;
//    if (i >:= parts.length)
//      throw new Error('Unable to process part '+path);
//    int j := parts.length - 1;
//    while (j > 0 ) and ( (parts[j] = 'extension') ) or ( parts[j] = 'modifierExtension')))
//        j--;
//    StringBuilder b := new StringBuilder();
//    boolean first := true;
//    for (int k := i; k <:= j; k++) begin
//      if (k = j ) or ( !parts[k] = parts[k+1])) begin
//        if (first)
//          first := false;
//        else
//        b.append('.');
//      b.append(parts[k]);
//    end;
//    end;
//    return b.toString();
//  end;
//

  private boolean empty(element : TWrapperElement) begin
    if (element.hasAttribute('value'))
      return false;
    if (element.hasAttribute('xml:id'))
      return false;
    TWrapperElement child := element.getFirstChild();
    while (child <> nil) begin
      if (!child.isXml() ) or ( FormatUtilities.FHIR_NS = child.getNamespace())) begin
        return false;
      end;
      child := child.getNextSibling();
    end;
    return true;
  end;

  private ElementDefinition findElement(profile : TFHIRStructureDefinition, String name) begin
    for (ElementDefinition c : profile.getSnapshot().getElement()) begin
      if (c.getPath() = name)) begin
        return c;
      end;
    end;
    return nil;
  end;

  private ElementDefinition getDefinitionByTailNameChoice(List<ElementDefinition> children, String name) begin
    for (ElementDefinition ed : children) begin
    	String n := tail(ed.getPath());
      if (n.endsWith('[x]') ) and ( name.startsWith(n.substring(0, n.length()-3))) begin
        return ed;
      end;
    end;
    return nil;
  end;

  private String tail(String path) begin
    return path.substring(path.lastIndexOf('.')+1);
  end;

  procedure TFHIRInstanceValidator.validateContains(errors : TAdvList<TValidationMessage>, String path, ElementDefinition child, ElementDefinition context, element : TWrapperElement, stack : TNodeStack, needsId : boolean) ; begin
  	TWrapperElement e := element.isXml() ? element.getFirstChild() : element;
  	String resourceName := e.getResourceType();
    profile : TFHIRStructureDefinition := this.context.fetchResource(StructureDefinition.class, 'http://hl7.org/fhir/StructureDefinition/'+resourceName);
    if (rule(errors, IssueTypeINVALID, element.line(), element.col(), stack.addToLiteralPath(resourceName), profile <> nil, 'No profile found for contained resource of type "'+resourceName+'"'))
      validateResource(errors, e, profile, needsId, stack);    
  end;

  private boolean typeIsPrimitive(String t) begin
    if ('boolean'.equalsIgnoreCase(t)) return true;
    if ('integer'.equalsIgnoreCase(t)) return true;
    if ('decimal'.equalsIgnoreCase(t)) return true;
    if ('base64Binary'.equalsIgnoreCase(t)) return true;
    if ('instant'.equalsIgnoreCase(t)) return true;
    if ('string'.equalsIgnoreCase(t)) return true;
    if ('uri'.equalsIgnoreCase(t)) return true;
    if ('date'.equalsIgnoreCase(t)) return true;
    if ('dateTime'.equalsIgnoreCase(t)) return true;
    if ('date'.equalsIgnoreCase(t)) return true;
    if ('oid'.equalsIgnoreCase(t)) return true;
    if ('uuid'.equalsIgnoreCase(t)) return true;
    if ('code'.equalsIgnoreCase(t)) return true;
    if ('id'.equalsIgnoreCase(t)) return true;
    if ('xhtml'.equalsIgnoreCase(t)) return true;
    return false;
  end;

  procedure TFHIRInstanceValidator.checkPrimitive(errors : TAdvList<TValidationMessage>, String path, String type, ElementDefinition context, TWrapperElement e) ; begin
    if (type = 'uri')) begin
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, !e.getAttribute('value').startsWith('oid:'), 'URI values cannot start with oid:');
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, !e.getAttribute('value').startsWith('uuid:'), 'URI values cannot start with uuid:');
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, e.getAttribute('value') = e.getAttribute('value').trim()), 'URI values cannot have leading or trailing whitespace');
    end;
    if (!type.equalsIgnoreCase('string') ) and ( e.hasAttribute('value')) begin
      if (rule(errors, IssueTypeINVALID, e.line(), e.col(), path, e.getAttribute('value').length() > 0, '@value cannot be empty')) begin
        warning(errors, IssueTypeINVALID, e.line(), e.col(), path, e.getAttribute('value').trim() = e.getAttribute('value')), 'value should not start or finish with whitespace');
      end;
    end;
    if (type = 'dateTime')) begin
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, yearIsValid(e.getAttribute('value')), 'The value "'+e.getAttribute('value')+'" does not have a valid year');
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, e.getAttribute('value').matches('-?[0-9]begin4end;(-(0[1-9]"1[0-2])(-(0[0-9]"[1-2][0-9]"3[0-1])(T([01][0-9]"2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z"(\\+"-)((0[0-9]"1[0-3]):[0-5][0-9]"14:00))?)?)?)?'), 'Not a valid date time');
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, !hasTime(e.getAttribute('value')) ) or ( hasTimeZone(e.getAttribute('value')), 'if a date has a time, it must have a timezone');
      
    end;
    if (type = 'instant')) begin
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, e.getAttribute('value').matches('-?[0-9]begin4end;-(0[1-9]"1[0-2])-(0[0-9]"[1-2][0-9]"3[0-1])T([01][0-9]"2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z"(\\+"-)((0[0-9]"1[0-3]):[0-5][0-9]"14:00))'), 'The instant "'+e.getAttribute('value')+'" is not valid (by regex)');
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, yearIsValid(e.getAttribute('value')), 'The value "'+e.getAttribute('value')+'" does not have a valid year');
    end;
    
    if (type = 'code')) begin
      // Technically, a code is restricted to string which has at least one character and no leading or trailing whitespace, and where there is no whitespace other than single spaces in the contents 
      rule(errors, IssueTypeINVALID, e.line(), e.col(), path, passesCodeWhitespaceRules(e.getAttribute('value')), 'The code "'+e.getAttribute('value')+'" is not valid (whitespace rules)');
    end;

    if (context.hasBinding()) begin
      checkPrimitiveBinding(errors, path, type, context, e);
    end;
    // for nothing to check    
  end;

  private boolean passesCodeWhitespaceRules(String v) begin
    if (!v.trim() = v))
      return false;
    boolean lastWasSpace := true;
    for (char c : v.toCharArray()) begin
      if (c = " ") begin
        if (lastWasSpace)
          return false;
        else
          lastWasSpace := true;
      end; else if (Character.isWhitespace(c))
        return false;
      else
        lastWasSpace := false;
    end;
    return true;
  end;
  
  // note that we don"t check the type here; it could be string, uri or code.
  procedure TFHIRInstanceValidator.checkPrimitiveBinding(errors : TAdvList<TValidationMessage>, String path, String type, ElementDefinition context, element : TWrapperElement) ; begin
    if (!element.hasAttribute('value'))
      return;
    
    String value := element.getAttribute('value');

//    System.out.println('check '+value+' in '+path);
    
    // firstly, resolve the value set
    ElementDefinitionBindingComponent binding := context.getBinding();
    if (binding.hasValueSet() ) and ( binding.getValueSet() instanceof Reference) begin
      ValueSet vs := resolveBindingReference(binding.getValueSet());
      if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, vs <> nil, 'ValueSet '+describeReference(binding.getValueSet())+' not found')) begin
        try begin
          vs := cache.getExpander().expand(vs).getValueset();
          if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, vs <> nil, 'Unable to expand value set for '+describeReference(binding.getValueSet()))) begin
            boolean ok := codeInExpansion(vs, nil, value);
            if (binding.getStrength() = BindingStrength.REQUIRED)
              rule(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, ok, 'Coded value '+value+' is not in value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
            else if (binding.getStrength() = BindingStrength.EXTENSIBLE)
              warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, ok, 'Coded value '+value+' is not in value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
            else
              hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, ok, 'Coded value '+value+' is not in value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
          end;
        end; catch (ETooCostly e) begin
          if (e.getMessage() = nil)
            warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': --nil--');
          else
            warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': '+e.getMessage());
        end;
      end;
    end; else
      hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Binding has no source, so can"t be checked');
  end;
  
  private boolean yearIsValid(String v) begin
    if (v = nil) begin
        return false;
    end;
    try begin
       int i := Integer.parseInt(v.substring(0, Math.min(4, v.length())));
       return i >:= 1800 ) and ( i <:= 2100;
    end; catch (NumberFormatException e) begin
       return false;
    end;
  end;
    
  private boolean hasTimeZone(String fmt) begin
    return fmt.length() > 10 ) and ( (fmt.substring(10).contains('-') ) or ( fmt.substring(10).contains('-') ) or ( fmt.substring(10).contains('+') ) or ( fmt.substring(10).contains('Z'));
  end;
  
  private boolean hasTime(String fmt) begin
    return fmt.contains('T');
  end;
  
  procedure TFHIRInstanceValidator.checkIdentifier(errors : TAdvList<TValidationMessage>, String path, element : TWrapperElement, ElementDefinition context) begin
    String system := element.getNamedChildValue('system');
    rule(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, isAbsolute(system), 'Identifier.system must be an absolute reference, not a local reference');
  end;

  private boolean isAbsolute(String uri) begin
    return Utilities.noString(uri) ) or ( uri.startsWith('http:') ) or ( uri.startsWith('https:') ) or ( uri.startsWith('urn:uuid:') ) or ( uri.startsWith('urn:oid:') ) or (
        uri.startsWith('urn:ietf:') ) or ( uri.startsWith('urn:iso:');
  end;
  
  procedure TFHIRInstanceValidator.checkIdentifier(String path, Element element, ElementDefinition context) begin

  end;

  procedure TFHIRInstanceValidator.checkQuantity(errors : TAdvList<TValidationMessage>, String path, element : TWrapperElement, ElementDefinition context, boolean b) ; begin
    String code := element.getNamedChildValue('code');
    String system := element.getNamedChildValue('system');
    String units := element.getNamedChildValue('units');

    if (system <> nil ) and ( code <> nil) begin
      checkCode(errors, element, path, code, system, units);
    end;
  end;


  procedure TFHIRInstanceValidator.checkCoding(errors : TAdvList<TValidationMessage>, String path, element : TWrapperElement, profile : TFHIRStructureDefinition, ElementDefinition context) throws EOperationOutcome, Exception begin
    String code := element.getNamedChildValue('code');
    String system := element.getNamedChildValue('system');
    String display := element.getNamedChildValue('display');
    rule(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, isAbsolute(system), 'Coding.system must be an absolute reference, not a local reference');
    
    if (system <> nil ) and ( code <> nil) begin
      if (checkCode(errors, element, path, code, system, display)) 
        if (context <> nil ) and ( context.getBinding() <> nil) begin
          ElementDefinitionBindingComponent binding := context.getBinding();
          if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, binding <> nil, 'Binding for '+path+' missing')) begin
            if (binding.hasValueSet() ) and ( binding.getValueSet() instanceof Reference) begin
              ValueSet vs := resolveBindingReference(binding.getValueSet());
              if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, vs <> nil, 'ValueSet '+describeReference(binding.getValueSet())+' not found')) begin
                try begin
                  vs := cache.getExpander().expand(vs).getValueset();
                  if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, vs <> nil, 'Unable to expand value set for '+describeReference(binding.getValueSet()))) begin
                    if (binding.getStrength() = BindingStrength.REQUIRED)
                      rule(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, codeInExpansion(vs, system, code), 'Code begin'+system+'end;'+code+' is not in value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
                    else if (binding.getStrength() = BindingStrength.EXTENSIBLE)
                      warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, codeInExpansion(vs, system, code), 'Code begin'+system+'end;'+code+' is not in value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
                    else
                      hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, codeInExpansion(vs, system, code), 'Code begin'+system+'end;'+code+' is not in value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
                  end;
                end; catch (Exception e) begin
                  if (e.getMessage() = nil)
                    warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': --nil--');
                  else
                    warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': '+e.getMessage());
                end;
              end;
            end; else if (binding.hasValueSet())
              hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Binding by URI reference cannot be checked');
            else 
              hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Binding has no source, so can"t be checked');
          end;
        end;
    end;
  end;


  private ValueSet resolveBindingReference(Type reference) throws EOperationOutcome, Exception begin
    if (reference instanceof UriType)
      return context.fetchResource(ValueSet.class, ((UriType) reference).getValue().toString());
    else if (reference instanceof Reference)
      return context.fetchResource(ValueSet.class, ((Reference) reference).getReference());
    else
      return nil;
  end;

  private boolean codeInExpansion(ValueSet vs, String system, String code) begin
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) begin
      if (code = c.getCode()) ) and ( (system = nil ) or ( system = c.getSystem())))
        return true;
      if (codeinExpansion(c, system, code)) 
        return true;
    end;
    return false;
  end;

  private boolean codeinExpansion(ValueSetExpansionContainsComponent cnt, String system, String code) begin
    for (ValueSetExpansionContainsComponent c : cnt.getContains()) begin
      if (code = c.getCode()) ) and ( system = c.getSystem().toString()))
        return true;
      if (codeinExpansion(c, system, code)) 
        return true;
    end;
    return false;
  end;

  procedure TFHIRInstanceValidator.checkCodeableConcept(errors : TAdvList<TValidationMessage>, String path, element : TWrapperElement, profile : TFHIRStructureDefinition, ElementDefinition context) throws EOperationOutcome, Exception begin
    if (context <> nil ) and ( context.hasBinding()) begin
      ElementDefinitionBindingComponent binding := context.getBinding();
      if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, binding <> nil, 'Binding for '+path+' missing (cc)')) begin
        if (binding.hasValueSet() ) and ( binding.getValueSet() instanceof Reference) begin
          ValueSet vs := resolveBindingReference(binding.getValueSet());
          if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, vs <> nil, 'ValueSet '+describeReference(binding.getValueSet())+' not found')) begin
            try begin
              ValueSetExpansionOutcome exp := cache.getExpander().expand(vs);
              vs := exp.getValueset();
              if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, vs <> nil, 'Unable to expand value set for '+describeReference(binding.getValueSet()))) begin
                boolean found := false;
                boolean any := false;
                TWrapperElement c := element.getFirstChild();
                while (c <> nil) begin
                  if (c.getName() = 'coding')) begin
                    any := true;
                    String system := c.getNamedChildValue('system');
                    String code := c.getNamedChildValue('code');
                    if (system <> nil ) and ( code <> nil)
                      found := found ) or ( codeInExpansion(vs, system, code);
                  end;
                  c := c.getNextSibling();
                end;
                if (!any ) and ( binding.getStrength() = BindingStrength.REQUIRED)
                  warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, found, 'No code provided, and value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+') is required');
                if (any)
                  if (binding.getStrength() = BindingStrength.PREFERRED)
                    hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, found, 'None of the codes are in the example value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
                  else if (binding.getStrength() = BindingStrength.EXTENSIBLE)
                    warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, found, 'None of the codes are in the expected value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
              end;
            end; catch (Exception e) begin
              if (e.getMessage() = nil) begin
                warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': --nil--');
//              end; else if (!e.getMessage().contains('unable to find value set http://snomed.info/sct')) begin
//                hint(errors, IssueTypeCODEINVALID, path, suppressLoincSnomedMessages, 'Snomed value set - not validated');
//              end; else if (!e.getMessage().contains('unable to find value set http://loinc.org')) begin
//                hint(errors, IssueTypeCODEINVALID, path, suppressLoincSnomedMessages, 'Loinc value set - not validated');
              end; else
                warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': '+e.getMessage());
            end;
          end;
        end; else if (binding.hasValueSet())
          hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Binding by URI reference cannot be checked');
        else 
          hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, false, 'Binding has no source, so can"t be checked');
      end;
    end;
  end;

  private String describeReference(Type reference) begin
    if (reference = nil)
      return 'nil';
    if (reference instanceof UriType)
      return ((UriType)reference).getValue();
    if (reference instanceof Reference)
      return ((Reference)reference).getReference();
    return '??';
  end;


  private boolean checkCode(errors : TAdvList<TValidationMessage>, element : TWrapperElement, String path, String code, String system, String display) ; begin
    if (context.supportsSystem(system)) begin
      ValidationResult s := context.validateCode(system, code, display);
      if (s = nil ) or ( s.isOk())
        return true;
      if (s.getSeverity() = IssueSeverity.INFORMATION)
        hint(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, s = nil, s.getMessage());
      else if (s.getSeverity() = IssueSeverity.WARNING)
        warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, s = nil, s.getMessage());
      else
        return rule(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, s = nil, s.getMessage());
      return true;
    end; else if (system.startsWith('http://hl7.org/fhir')) begin
      if (system = 'http://hl7.org/fhir/sid/icd-10'))
        return true; // else don"t check ICD-10 (for now)
      else begin
        ValueSet vs := getValueSet(system);
        if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, vs <> nil, 'Unknown Code System '+system)) begin
          ConceptDefinitionComponent def := getCodeDefinition(vs, code);
          if (warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, def <> nil, 'Unknown Code ('+system+'#'+code+')'))
            return warning(errors, IssueTypeCODEINVALID, element.line(), element.col(), path, display = nil ) or ( display = def.getDisplay()), 'Display should be "'+def.getDisplay()+'"');
        end;
        return false;
      end;
    end; else if (system.startsWith('http://loinc.org')) begin
      return true;
    end; else if (system.startsWith('http://unitsofmeasure.org')) begin
      return true;
    end;
    else 
      return true;
  end;

  private ConceptDefinitionComponent getCodeDefinition(ConceptDefinitionComponent c, String code) begin
    if (code = c.getCode()))
      return c;
    for (ConceptDefinitionComponent g : c.getConcept()) begin
      ConceptDefinitionComponent r := getCodeDefinition(g, code);
      if (r <> nil)
        return r;
    end;
    return nil;
  end;

  private ConceptDefinitionComponent getCodeDefinition(ValueSet vs, String code) begin
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
      ConceptDefinitionComponent r := getCodeDefinition(c, code);
      if (r <> nil)
        return r;
    end;
    return nil;
  end;

  private ValueSet getValueSet(String system) ; begin
    return context.fetchCodeSystem(system);
  end;


  public class ProfileStructureIterator begin

    private profile : TFHIRStructureDefinition;
    private ElementDefinition elementDefn;
    private List<String> names := new ArrayList<String>();
    private Map<String, List<ElementDefinition>> children := new HashMap<String, List<ElementDefinition>>();
    private int cursor;

    public ProfileStructureIterator(profile : TFHIRStructureDefinition, ElementDefinition elementDefn) begin
      this.profile := profile;
      this.elementDefn := elementDefn;
      loadMap();
      cursor := -1;
    end;

    procedure TFHIRInstanceValidator.loadMap() begin
      int i := profile.getSnapshot().getElement().indexOf(elementDefn) + 1;
      String lead := elementDefn.getPath();
      while (i < profile.getSnapshot().getElement().Count) begin
        String name := profile.Snapshot.ElementList[i).getPath();
        if (name.length() <:= lead.length())
          return; // cause we"ve got to the end of the possible matches
        String tail := name.substring(lead.length()+1);
        if (Utilities.isToken(tail) ) and ( name.substring(0, lead.length()) = lead)) begin
          List<ElementDefinition> list := children[tail);
          if (list = nil) begin
            list := new ArrayList<ElementDefinition>();
            names.add(tail);
            children.put(tail, list);
          end;
          list.add(profile.Snapshot.ElementList[i));
        end;
        i++;
      end;
    end;

    public boolean more() begin
      cursor++;
      return cursor < names.Count;
    end;

    public List<ElementDefinition> current() begin
      return children[name());
    end;

    public String name() begin
      return names[cursor);
    end;

  end;

  procedure TFHIRInstanceValidator.checkByProfile(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, profile : TFHIRStructureDefinition, ElementDefinition elementDefn) ; begin
    // we have an element, and the structure that describes it. 
    // we know that"s it"s valid against the underlying spec - is it valid against this one?
    // in the instance validator above, we assume that schema or schmeatron has taken care of cardinalities, but here, we have no such reliance. 
    // so the walking algorithm is different: we"re going to walk the definitions
    String type;
  	if (elementDefn.getPath().endsWith('[x]')) begin
  		String tail := elementDefn.getPath().substring(elementDefn.getPath().lastIndexOf('.')+1, elementDefn.getPath().length()-3);
  		type := focus.getName().substring(tail.length());
  		rule(errors, IssueTypeSTRUCTURE, focus.line(), focus.col(), path, typeAllowed(type, elementDefn.getType()), 'The type "'+type+'" is not allowed at this point (must be one of "'+typeSummary(elementDefn)+')');
  	end; else begin
  		if (elementDefn.getType().Count = 1) begin
  			type := elementDefn.getType().Count = 0 ? nil : elementDefn.getType()[0).getCode();
  		end; else
  			type := nil;
  	end;
  	// constraints:
  	for (ElementDefinitionConstraintComponent c : elementDefn.getConstraint()) 
  		checkConstraint(errors, path, focus, c);
  	if (elementDefn.hasBinding() ) and ( type <> nil)
  		checkBinding(errors, path, focus, profile, elementDefn, type);
  	
  	// type specific checking:
  	if (type <> nil ) and ( typeIsPrimitive(type)) begin
  		checkPrimitiveByProfile(errors, path, focus, elementDefn);
  	end; else begin
  		if (elementDefn.hasFixed())
  			checkFixedValue(errors, path, focus, elementDefn.getFixed(), '');
  			 
  		ProfileStructureIterator walker := new ProfileStructureIterator(profile, elementDefn);
  		while (walker.more()) begin
  			// collect all the slices for the path
  			List<ElementDefinition> childset := walker.current();
  			// collect all the elements that match it by name
  			TAdvList<TWrapperElement> children := TAdvList<TWrapperElement>.create();
  			focus.getNamedChildrenWithWildcard(walker.name(), children);

  			if (children.Count = 0) begin
  				// well, there"s no children - should there be?
  				for (ElementDefinition defn : childset) begin
  					if (!rule(errors, IssueTypeREQUIRED, focus.line(), focus.col(), path, defn.getMin() = 0, 'Required Element "'+walker.name()+'" missing'))
  						break; // no point complaining about missing ones after the first one
  				end;
  			end; else if (childset.Count = 1) begin
  				// simple case: one possible definition, and one or more children. 
  				rule(errors, IssueTypeSTRUCTURE, focus.line(), focus.col(), path, childset[0).getMax() = '*') ) or ( Integer.parseInt(childset[0).getMax()) >:= children.Count,
  						'Too many elements for "'+walker.name()+'"'); // todo: sort out structure
  				for (TWrapperElement child : children) begin
  					checkByProfile(errors, childset[0).getPath(), child, profile, childset[0));
  				end;
  			end; else begin
  				// ok, this is the full case - we have a list of definitions, and a list of candidates for meeting those definitions. 
  				// we need to decide *if* that match a given definition
  			end;
  		end;
  	end;
  end;

	procedure TFHIRInstanceValidator.checkBinding(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, profile : TFHIRStructureDefinition, ElementDefinition elementDefn, String type) throws EOperationOutcome, Exception begin
	  ElementDefinitionBindingComponent bc := elementDefn.getBinding();

	  if (bc <> nil ) and ( bc.hasValueSet() ) and ( bc.getValueSet() instanceof Reference) begin
      String url := ((Reference) bc.getValueSet()).getReference();
	  	ValueSet vs := resolveValueSetReference(profile, (Reference) bc.getValueSet());
	  	if (vs = nil) begin
	      rule(errors, IssueTypeSTRUCTURE, focus.line(), focus.col(), path, false, 'Cannot check binding on type "'+type+'" as the value set "'+url+'" could not be located');
      end; else if (type = 'code'))
	  		checkBindingCode(errors, path, focus, vs);
	  	else if (type = 'Coding'))
	  		checkBindingCoding(errors, path, focus, vs);
	  	else if (type = 'CodeableConcept'))
	  		checkBindingCodeableConcept(errors, path, focus, vs);
	  	else 
	  		rule(errors, IssueTypeSTRUCTURE, focus.line(), focus.col(), path, false, 'Cannot check binding on type "'+type+'"');
	  end;
  end;

	private ValueSet resolveValueSetReference(profile : TFHIRStructureDefinition, Reference reference) throws EOperationOutcome, Exception begin
	  if (reference.getReference().startsWith('#')) begin
	  	for (Resource r : profile.getContained()) begin
	  		if (r instanceof ValueSet ) and ( r.getId() = reference.getReference().substring(1)))
	  			return (ValueSet) r;
	  	end;
	  	return nil;
	  end; else
	  	return resolveBindingReference(reference);
	   
  end;

	procedure TFHIRInstanceValidator.checkBindingCode(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, ValueSet vs) begin
	  // rule(errors, 'exception', path, false, 'checkBindingCode not done yet');
  end;

	procedure TFHIRInstanceValidator.checkBindingCoding(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, ValueSet vs) begin
	  // rule(errors, 'exception', path, false, 'checkBindingCoding not done yet');
  end;

	procedure TFHIRInstanceValidator.checkBindingCodeableConcept(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, ValueSet vs) begin
	  // rule(errors, 'exception', path, false, 'checkBindingCodeableConcept not done yet');
  end;

	private String typeSummary(ElementDefinition elementDefn) begin
	  StringBuilder b := new StringBuilder();
	  for (TypeRefComponent t : elementDefn.getType()) begin
	  	b.append('"'+t.getCode());
	  end;
	  return b.toString().substring(1);
  end;

	private boolean typeAllowed(String t, List<TypeRefComponent> types) begin
	  for (TypeRefComponent type : types) begin
	  	if (t = Utilities.capitalize(type.getCode())))
	  		return true;
	  	if (t = 'Resource') ) and ( Utilities.capitalize(type.getCode()) = 'Reference'))
	  	  return true;
	  end;
	  return false;
  end;

	procedure TFHIRInstanceValidator.checkConstraint(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, ElementDefinitionConstraintComponent c) ; begin
	  
//		try
//   	begin
//			XPathFactory xpf := new net.sf.saxon.xpath.XPathFactoryImpl();
//      NamespaceContext context := new NamespaceContextMap('f', 'http://hl7.org/fhir', 'h', 'http://www.w3.org/1999/xhtml');
//			
//			XPath xpath := xpf.newXPath();
//      xpath.setNamespaceContext(context);
//   		Boolean ok := (Boolean) xpath.evaluate(c.getXpath(), focus, XPathConstants.BOOLEAN);
//   		if (ok = nil ) or ( !ok) begin
//   			if (c.getSeverity() = ConstraintSeverity.warning)
//   				warning(errors, 'invariant', path, false, c.getHuman());
//   			else
//   				rule(errors, 'invariant', path, false, c.getHuman());
//   		end;
//		end;
//		catch (XPathExpressionException e) begin
//		  rule(errors, 'invariant', path, false, 'error executing invariant: '+e.getMessage());
//		end;
  end;

	procedure TFHIRInstanceValidator.checkPrimitiveByProfile(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, ElementDefinition elementDefn) begin
		// two things to check - length, and fixed value
		String value := focus.getAttribute('value');
		if (elementDefn.hasMaxLengthElement()) begin
			rule(errors, IssueTypeTOOLONG, focus.line(), focus.col(), path, value.length() <:= elementDefn.getMaxLength(), 'The value "'+value+'" exceeds the allow length limit of '+inttostr(elementDefn.getMaxLength()));
		end;
		if (elementDefn.hasFixed()) begin
			checkFixedValue(errors, path, focus, elementDefn.getFixed(), '');
		end;
  end;

	procedure TFHIRInstanceValidator.checkFixedValue(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, org.hl7.fhir.instance.model.Element fixed, String propName) begin
		if (fixed = nil ) and ( focus = nil)
			; // this is all good
		else if (fixed = nil ) and ( focus <> nil)
	  	rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, false, 'Unexpected element '+focus.getName());
		else if (fixed <> nil ) and ( focus = nil)
	  	rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, false, 'Mising element '+propName);
		else begin
			String value := focus.getAttribute('value');
			if (fixed instanceof org.hl7.fhir.instance.model.BooleanType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.IntegerType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.DecimalType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.Base64BinaryType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.InstantType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.InstantType) fixed).getValue().toString(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.InstantType) fixed).asStringValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.StringType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.StringType) fixed).getValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.StringType) fixed).getValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.UriType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.UriType) fixed).getValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.UriType) fixed).getValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.DateType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DateType) fixed).getValue().toString(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.DateType) fixed).getValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.DateTimeType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue().toString(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.OidType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.OidType) fixed).getValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.OidType) fixed).getValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.UuidType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.UuidType) fixed).getValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.UuidType) fixed).getValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.CodeType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.CodeType) fixed).getValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.CodeType) fixed).getValue()+'"');
			else if (fixed instanceof org.hl7.fhir.instance.model.IdType)
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.IdType) fixed).getValue(), value), 'Value is "'+value+'" but must be "'+((org.hl7.fhir.instance.model.IdType) fixed).getValue()+'"');
			else if (fixed instanceof Quantity)
				checkQuantity(errors, path, focus, (Quantity) fixed);
			else if (fixed instanceof Address)
				checkAddress(errors, path, focus, (Address) fixed);
			else if (fixed instanceof ContactPoint)
				checkContactPoint(errors, path, focus, (ContactPoint) fixed);
			else if (fixed instanceof Attachment)
				checkAttachment(errors, path, focus, (Attachment) fixed);
			else if (fixed instanceof Identifier)
				checkIdentifier(errors, path, focus, (Identifier) fixed);
			else if (fixed instanceof Coding)
				checkCoding(errors, path, focus, (Coding) fixed);
			else if (fixed instanceof HumanName)
				checkHumanName(errors, path, focus, (HumanName) fixed);
			else if (fixed instanceof CodeableConcept)
				checkCodeableConcept(errors, path, focus, (CodeableConcept) fixed);
			else if (fixed instanceof Timing)
				checkTiming(errors, path, focus, (Timing) fixed);
			else if (fixed instanceof Period)
				checkPeriod(errors, path, focus, (Period) fixed);
			else if (fixed instanceof Range)
				checkRange(errors, path, focus, (Range) fixed);
			else if (fixed instanceof Ratio)
				checkRatio(errors, path, focus, (Ratio) fixed);
			else if (fixed instanceof SampledData)
				checkSampledData(errors, path, focus, (SampledData) fixed);
	
			else
				 rule(errors, IssueTypeEXCEPTION, focus.line(), focus.col(), path, false, 'Unhandled fixed value type '+fixed.getClass().getName());
			TAdvList<TWrapperElement> extensions := TAdvList<TWrapperElement>.create();
			focus.getNamedChildren('extension', extensions);
			if (fixed.getExtension().Count = 0) begin
				rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, extensions.Count = 0, 'No extensions allowed');
			end; else if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, extensions.Count = fixed.getExtension().Count, 'Extensions count mismatch: expected '+inttostr(fixed.getExtension().Count)+' but found '+inttostr(extensions.Count))) begin
				for (Extension e : fixed.getExtension()) begin
				  TWrapperElement ex := getExtensionByUrl(extensions, e.getUrl());
					if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, ex <> nil, 'Extension count mismatch: unable to find extension: '+e.getUrl())) begin
						checkFixedValue(errors, path, ex.getFirstChild().getNextSibling(), e.getValue(), 'extension.value');
					end;
				end;
			end;
		end;
  end;

	procedure TFHIRInstanceValidator.checkAddress(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Address fixed) begin
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.getTextElement(), 'text');
	  checkFixedValue(errors, path+'.city', focus.getNamedChild('city'), fixed.getCityElement(), 'city');
	  checkFixedValue(errors, path+'.state', focus.getNamedChild('state'), fixed.getStateElement(), 'state');
	  checkFixedValue(errors, path+'.country', focus.getNamedChild('country'), fixed.getCountryElement(), 'country');
	  checkFixedValue(errors, path+'.zip', focus.getNamedChild('zip'), fixed.getPostalCodeElement(), 'postalCode');
	  
		TAdvList<TWrapperElement> lines := TAdvList<TWrapperElement>.create();
		focus.getNamedChildren( 'line', lines);
		if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, lines.Count = fixed.getLine().Count, 'Expected '+inttostr(fixed.getLine().Count)+' but found '+inttostr(lines.Count)+' line elements')) begin
			for (int i := 0; i < lines.Count; i++)
				checkFixedValue(errors, path+'.coding', lines[i), fixed.getLine()[i), 'coding');
		end;
  end;

	procedure TFHIRInstanceValidator.checkContactPoint(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, ContactPoint fixed) begin
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.getValueElement(), 'value');
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriod(), 'period');
	  
  end;

	procedure TFHIRInstanceValidator.checkAttachment(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Attachment fixed) begin
	  checkFixedValue(errors, path+'.contentType', focus.getNamedChild('contentType'), fixed.getContentTypeElement(), 'contentType');
	  checkFixedValue(errors, path+'.language', focus.getNamedChild('language'), fixed.getLanguageElement(), 'language');
	  checkFixedValue(errors, path+'.data', focus.getNamedChild('data'), fixed.getDataElement(), 'data');
	  checkFixedValue(errors, path+'.url', focus.getNamedChild('url'), fixed.getUrlElement(), 'url');
	  checkFixedValue(errors, path+'.size', focus.getNamedChild('size'), fixed.getSizeElement(), 'size');
	  checkFixedValue(errors, path+'.hash', focus.getNamedChild('hash'), fixed.getHashElement(), 'hash');
	  checkFixedValue(errors, path+'.title', focus.getNamedChild('title'), fixed.getTitleElement(), 'title');
  end;

	procedure TFHIRInstanceValidator.checkIdentifier(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Identifier fixed) begin
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.label', focus.getNamedChild('type'), fixed.getType(), 'type');
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.getValueElement(), 'value');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriod(), 'period');
	  checkFixedValue(errors, path+'.assigner', focus.getNamedChild('assigner'), fixed.getAssigner(), 'assigner');
  end;

	procedure TFHIRInstanceValidator.checkCoding(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Coding fixed) begin
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.code', focus.getNamedChild('code'), fixed.getCodeElement(), 'code');
	  checkFixedValue(errors, path+'.display', focus.getNamedChild('display'), fixed.getDisplayElement(), 'display');
	  checkFixedValue(errors, path+'.userSelected', focus.getNamedChild('userSelected'), fixed.getUserSelectedElement(), 'userSelected');
  end;

	procedure TFHIRInstanceValidator.checkHumanName(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, HumanName fixed) begin
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.getTextElement(), 'text');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriod(), 'period');
	  
		TAdvList<TWrapperElement> parts := TAdvList<TWrapperElement>.create();
		focus.getNamedChildren( 'family', parts);
		if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, parts.Count = fixed.getFamily().Count, 'Expected '+inttostr(fixed.getFamily().Count)+' but found '+inttostr(parts.Count)+' family elements')) begin
			for (int i := 0; i < parts.Count; i++)
				checkFixedValue(errors, path+'.family', parts[i), fixed.getFamily()[i), 'family');
		end;
		focus.getNamedChildren( 'given', parts);
		if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, parts.Count = fixed.getFamily().Count, 'Expected '+inttostr(fixed.getFamily().Count)+' but found '+inttostr(parts.Count)+' given elements')) begin
			for (int i := 0; i < parts.Count; i++)
				checkFixedValue(errors, path+'.given', parts[i), fixed.getFamily()[i), 'given');
		end;
		focus.getNamedChildren( 'prefix', parts);
		if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, parts.Count = fixed.getFamily().Count, 'Expected '+inttostr(fixed.getFamily().Count)+' but found '+inttostr(parts.Count)+' prefix elements')) begin
			for (int i := 0; i < parts.Count; i++)
				checkFixedValue(errors, path+'.prefix', parts[i), fixed.getFamily()[i), 'prefix');
		end;
		focus.getNamedChildren( 'suffix', parts);
		if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, parts.Count = fixed.getFamily().Count, 'Expected '+inttostr(fixed.getFamily().Count)+' but found '+inttostr(parts.Count)+' suffix elements')) begin
			for (int i := 0; i < parts.Count; i++)
				checkFixedValue(errors, path+'.suffix', parts[i), fixed.getFamily()[i), 'suffix');
		end;
  end;

	procedure TFHIRInstanceValidator.checkCodeableConcept(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, CodeableConcept fixed) begin
		checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.getTextElement(), 'text');
		TAdvList<TWrapperElement> codings := TAdvList<TWrapperElement>.create();
		focus.getNamedChildren( 'coding', codings);
		if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, codings.Count = fixed.getCoding().Count, 'Expected '+inttostr(fixed.getCoding().Count)+' but found '+inttostr(codings.Count)+' coding elements')) begin
			for (int i := 0; i < codings.Count; i++)
				checkFixedValue(errors, path+'.coding', codings[i), fixed.getCoding()[i), 'coding');
		end;
  end;

	procedure TFHIRInstanceValidator.checkTiming(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Timing fixed) begin
	  checkFixedValue(errors, path+'.repeat', focus.getNamedChild('repeat'), fixed.getRepeat(), 'value');

		TAdvList<TWrapperElement> events := TAdvList<TWrapperElement>.create();
		focus.getNamedChildren( 'event', events);
		if (rule(errors, IssueTypeVALUE, focus.line(), focus.col(), path, events.Count = fixed.getEvent().Count, 'Expected '+inttostr(fixed.getEvent().Count)+' but found '+inttostr(events.Count)+' event elements')) begin
			for (int i := 0; i < events.Count; i++)
				checkFixedValue(errors, path+'.event', events[i), fixed.getEvent()[i), 'event');
		end;
  end;

	procedure TFHIRInstanceValidator.checkPeriod(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Period fixed) begin
	  checkFixedValue(errors, path+'.start', focus.getNamedChild('start'), fixed.getStartElement(), 'start');
	  checkFixedValue(errors, path+'.end', focus.getNamedChild('end'), fixed.getEndElement(), 'end');
  end;

	procedure TFHIRInstanceValidator.checkRange(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Range fixed) begin
	  checkFixedValue(errors, path+'.low', focus.getNamedChild('low'), fixed.getLow(), 'low');
	  checkFixedValue(errors, path+'.high', focus.getNamedChild('high'), fixed.getHigh(), 'high');
	  
  end;

	procedure TFHIRInstanceValidator.checkRatio(errors : TAdvList<TValidationMessage>, String path,  focus : TWrapperElement, Ratio fixed) begin
	  checkFixedValue(errors, path+'.numerator', focus.getNamedChild('numerator'), fixed.getNumerator(), 'numerator');
	  checkFixedValue(errors, path+'.denominator', focus.getNamedChild('denominator'), fixed.getDenominator(), 'denominator');
  end;

	procedure TFHIRInstanceValidator.checkSampledData(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, SampledData fixed) begin
	  checkFixedValue(errors, path+'.origin', focus.getNamedChild('origin'), fixed.getOrigin(), 'origin');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriodElement(), 'period');
	  checkFixedValue(errors, path+'.factor', focus.getNamedChild('factor'), fixed.getFactorElement(), 'factor');
	  checkFixedValue(errors, path+'.lowerLimit', focus.getNamedChild('lowerLimit'), fixed.getLowerLimitElement(), 'lowerLimit');
	  checkFixedValue(errors, path+'.upperLimit', focus.getNamedChild('upperLimit'), fixed.getUpperLimitElement(), 'upperLimit');
	  checkFixedValue(errors, path+'.dimensions', focus.getNamedChild('dimensions'), fixed.getDimensionsElement(), 'dimensions');
	  checkFixedValue(errors, path+'.data', focus.getNamedChild('data'), fixed.getDataElement(), 'data');
  end;

	procedure TFHIRInstanceValidator.checkQuantity(errors : TAdvList<TValidationMessage>, String path, focus : TWrapperElement, Quantity fixed) begin
	  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.getValueElement(), 'value');
	  checkFixedValue(errors, path+'.comparator', focus.getNamedChild('comparator'), fixed.getComparatorElement(), 'comparator');
	  checkFixedValue(errors, path+'.units', focus.getNamedChild('unit'), fixed.getUnitElement(), 'units');
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.code', focus.getNamedChild('code'), fixed.getCodeElement(), 'code');
  end;

	private boolean check(String v1, String v2) begin
	  return v1 = nil ? Utilities.noString(v1) : v1 = v2);
  end;

	private TWrapperElement getExtensionByUrl(TAdvList<TWrapperElement> extensions, String urlSimple) begin
	  for (TWrapperElement e : extensions) begin
	  	if (urlSimple = e.getNamedChildValue('url')))
	  		return e;
	  end;
		return nil;
  end;
	


*)

end.


