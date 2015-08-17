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
  SysUtils,
  IdSoapMsXml, MsXmlParser,
  AdvObjects, AdvGenerics, AdvJSON, AdvObjectLists,
  FHIRResources, FHIRTypes;

Type
  TValidationMessage = class (TAdvObject);

  TWrapperElement = class (TAdvObject)
  private
    FOwned : TAdvObjectList;
    function own(obj : TWrapperElement) : TWrapperElement;
  public
    Constructor Create; Override;
    Destructor Destroy; Override;
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

  TBestPracticeWarningLevel = (bpwlIgnore, bpwlHint, bpwlWarning, bpwlError);
  TCheckDisplayOption = (cdoIgnore, cdopCheck, cdoCheckCaseAndSpace, cdoCheckCase, cdoCheckSpace);

  TFHIRInstanceValidator = class (TAdvObject)
  private
    // configuration items
    FCheckDisplay : TCheckDisplayOption;
    FBPWarnings : TBestPracticeWarningLevel;
    FSuppressLoincSnomedMessages : boolean;
    FRequireResourceId : boolean;
    FIsAnyExtensionsAllowed : boolean;
  public
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
begin
// writeln('  ..: '+path);
	// we''re going to make this look like the XML
	if (element = nil) then
 		raise Exception.create('not done yet');

    	if (element is TJsonValue) or (element is TJsonBoolean) then
      begin
    		// we may have an element_ too
    		if (_element !:= nil and _element is TJsonObject)
    		  for (Entry<String, JsonElement> t : ((TJsonObject) _element).entrySet())
    				processChild(t.getKey(), t.getValue());
    	end else if (element is TJsonObject) begin
    		for (Entry<String, JsonElement> t : ((TJsonObject) IXmlDomElement).entrySet())
    			if (!t.getKey().equals('resourceType')) begin
    				processChild(t.getKey(), t.getValue());
    			end
      end else if (element is JsonNull) begin
        // nothing to do
    	end else
    		raise Exception.create('unexpected condition: '+element.getClass().getName());
    end

		private void processChild(name : String, JsonElement e) throws Error begin
			if (name.startsWith('_')) begin
				name := name.substring(1);
				if (((TJsonObject) IXmlDomElement).has(name))
  				return; // it will get processed anyway
				e := nil;
			end
			JsonElement _e := IXmlDomElement is TJsonObject ? ((TJsonObject) IXmlDomElement).get('_'+name) : nil;

			if (e is JsonPrimitive or (e = nil and _e !:= nil and !(_e is JsonArray))) begin
  			children.add(new JsonWrapperElement(path, name, e, _e, this, children.size()));
			end else if (e is JsonArray or (e = nil and _e !:= nil)) begin
				JsonArray array := (JsonArray) e;
				JsonArray _array := (JsonArray) _e;
				Integer max := array !:= nil ? array.size() : 0;
				if (_array !:= nil and _array.size() > max)
					max := _array.size();
				for (Integer i := 0; i < max; i++) begin
					JsonElement a := array = nil or array.size() < i ? nil : array.get(i);
					JsonElement _a := _array = nil or _array.size() < i ? nil : _array.get(i);
  				children.add(new JsonWrapperElement(path, name, a, _a, this, children.size()));
				end
			end else if (e is TJsonObject) begin
				children.add(new JsonWrapperElement(path, name, e, nil, this, children.size()));
			end else
				raise Exception.create('not done yet: '+e.getClass().getName());
    end

    @Override
    function getNamedChild(name : String) : TWrapperElement; override;
    public TWrapperElement getNamedChild(name : String) begin
			for (JsonWrapperElement j : children)
				if (j.name.equals(name))
					return j;
			return nil;
    end

    @Override
    function getFirstChild() : TWrapperElement; override;
    public TWrapperElement getFirstChild() begin
    	if (children.isEmpty())
    		return nil;
    	else
      	return children.get(0);
    end

    @Override
    public TWrapperElement getNextSibling() begin
    	if (parent = nil)
    		return nil;
    	if (index >:= parent.children.size() - 1)
    		return nil;
    	return  parent.children.get(index+1);
    end

    @Override
    public String getName() begin
      return name;
    end

    @Override
    public String getNamedChildValue(name : String) begin
  		TWrapperElement c := getNamedChild(name);
      return c = nil ? nil : c.getAttribute('value');
    end

    @Override
    public void getNamedChildren(name : String, list : TAdvList<TWrapperElement>) begin
      for (JsonWrapperElement j : children)
        if (j.name.equals(name))
          list.add(j);
    end

    @Override
    public String getAttribute(name : String) begin
    	if (name.equals('value')) begin
    		if (IXmlDomElement = nil)
    			return nil;
    		if (IXmlDomElement is JsonPrimitive)
    			return ((JsonPrimitive) IXmlDomElement).getAsString();
        return nil;
    	end
    	if (name.equals('xml:id')) begin
    		TWrapperElement c := getNamedChild('id');
        return c = nil ? nil : c.getAttribute('value');
    	end
      if (name.equals('url')) begin
        TWrapperElement c := getNamedChild('url');
        return c = nil ? nil : c.getAttribute('value');
      end
      raise Exception.create('not done yet: '+name);
    end

    @Override
    public void getNamedChildrenWithWildcard(name : String, list : TAdvList<TWrapperElement>) begin
      raise Exception.create('not done yet');
    end

    @Override
    public boolean hasAttribute(name : String) begin
    	if (name.equals('value')) begin
    		if (IXmlDomElement = nil)
    			return false;
    		if (IXmlDomElement is JsonPrimitive)
    			return true;
        return false;
    	end
    	if (name.equals('xml:id')) begin
    		return getNamedChild('id') !:= nil;
    	end
      raise Exception.create('not done yet: '+name);
   end

    @Override
    public String getNamespace() begin
//      return IXmlDomElement.getNamespaceURI();
      raise Exception.create('not done yet');
   end

    @Override
    public boolean isXml() begin
      return false;
    end

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

    @Override
    public String getText() begin
      raise Exception.create('not done yet');
    end

    @Override
    public boolean hasNamespace(String ns) begin
      raise Exception.create('not done');
    end

    @Override
    public boolean hasProcessingInstruction() begin
      return false;
    end

		@Override
    public String getResourceType() begin
	    return resourceType;
    end

		@Override
    public Integer line() begin
	    return -1;
    end

		@Override
    public Integer col() begin
	    // TODO Auto-generated method stub
	    return -1;
    end

  end



(*

begin TFHIRInstanceValidator end
procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement);
var
  wrapper : TDomWrapperElement;
begin
  wrapper := TDomWrapperElement.create(element);
  try
    validateResource(errors, wrapper, nil, requiresResourceId, nil);
  finally
    wrapper.free;
  end;
end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject);
begin

end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement; profile : String);
begin

end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; element : IXmlDomElement; profile : TFHIRStructureDefinition);
begin

end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject; profile : TFHIRStructureDefinition);
begin

end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; obj : TJsonObject; profile : String);
begin

end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2);
begin

end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2; profile : String);
begin

end;

procedure TFHIRInstanceValidator.validate(errors : TAdvList<TValidationMessage>; document : IXmlDomDocument2; profile : TFHIRStructureDefinition);
begin

end;


  @Override
  public void validate(errors : TAdvList<ValidationMessage>; element : IXmlDomElement) throws Exception begin
  end
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; obj : TJsonObject) throws Exception begin
    validateResource(errors, new JsonWrapperElement(object), nil, requiresResourceId, nil);
  end
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; element : IXmlDomElement; profile : String) throws Exception begin
    TFHIRStructureDefinition p := context.getProfiles().get(profile);
    if (p = nil)
      raise Exception.create('TFHIRStructureDefinition '''+profile+''' not found');
    validateResource(errors, new DOMWrapperElement(IXmlDomElement), p, requiresResourceId, nil);
  end
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; element : IXmlDomElement; profile : TFHIRStructureDefinition) throws Exception begin
    validateResource(errors, new DOMWrapperElement(IXmlDomElement), profile, requiresResourceId, nil);
  end

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; obj : TJsonObject; profile : TFHIRStructureDefinition) throws Exception begin
    validateResource(errors, new JsonWrapperElement(object), profile, requiresResourceId, nil);
  end

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; obj : TJsonObject; profile : String) throws Exception begin
    TFHIRStructureDefinition p := context.getProfiles().get(profile);
    if (p = nil)
      raise Exception.create('TFHIRStructureDefinition '''+profile+''' not found');
    validateResource(errors, new JsonWrapperElement(object), p, requiresResourceId, nil);
  end

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2) throws Exception begin
  	checkForProcessingInstruction(errors, document);
    validateResource(errors, new DOMWrapperElement(document.getDocumentElement()), nil, requiresResourceId, nil);
  end
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2; profile : String) throws Exception begin
  	checkForProcessingInstruction(errors, document);
    TFHIRStructureDefinition p := context.getProfiles().get(profile);
    if (p = nil)
      raise Exception.create('TFHIRStructureDefinition '''+profile+''' not found');
    validateResource(errors, new DOMWrapperElement(document.getDocumentElement()), p, requiresResourceId, nil);
  end

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2; profile : TFHIRStructureDefinition) throws Exception begin
  	checkForProcessingInstruction(errors, document);
    validateResource(errors, new DOMWrapperElement(document.getDocumentElement()), profile, requiresResourceId, nil);
  end


  // implementation

  private void checkForProcessingInstruction(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2) begin
	  Node node := document.getFirstChild();
	  while (node !:= nil) begin
	  	rule(errors, IssueType.INVALID, -1, -1, '(document)', node.getNodeType() !:= Node.PROCESSING_INSTRUCTION_NODE, 'No processing instructions allowed in resources');
	  	node := node.getNextSibling();
	  end
  end


  public class ChildIterator begin
    private TWrapperElement parent;
    private String basePath;
    private Integer lastCount;
    private TWrapperElement child;

    public ChildIterator(String path, TWrapperElement IXmlDomElement) begin
      parent := IXmlDomElement;
      basePath := path;
    end

    public boolean next() begin
      if (child = nil) begin
        child := parent.getFirstChild();
        lastCount := 0;
      end else begin
        String lastName := child.getName();
        child := child.getNextSibling();
        if (child !:= nil and child.getName().equals(lastName))
          lastCount++;
        else
          lastCount := 0;
      end
      return child !:= nil;
    end

    public name : String() begin
      return child.getName();
    end

    public TWrapperElement IXmlDomElement() begin
      return child;
    end

    public String path() begin
      TWrapperElement n := child.getNextSibling();
      if (parent.isXml()) begin
      String sfx := '';
      if (n !:= nil and n.getName().equals(child.getName())) begin
        sfx := '['+Integer.toString(lastCount+1)+']';
      end
      return basePath+'/f:'+name()+sfx;
      end else begin
        String sfx := '';
      	if (n !:= nil and n.getName().equals(child.getName())) begin
      		sfx := '/'+Integer.toString(lastCount+1);
      	end
      	return basePath+'/'+name()+sfx;
      end
    end

    public Integer count() begin
      TWrapperElement n := child.getNextSibling();
      if (n !:= nil and n.getName().equals(child.getName())) begin
        return lastCount+1;
      end else
        return -1;
    end
  end

  private class NodeStack begin
  	private boolean xml;
    private NodeStack parent;
    private String literalPath; // xpath format
    private TAdvList<String> logicalPaths; // dotted format, various entry points
    private TWrapperElement IXmlDomElement;
    private ElementDefinition definition;
    private ElementDefinition type;
    private ElementDefinition extension;

    public NodeStack(boolean xml) begin
	    self.xml := xml;
    end

    private NodeStack push(TWrapperElement IXmlDomElement, Integer count, ElementDefinition definition, ElementDefinition type) begin
      NodeStack res := new NodeStack(IXmlDomElement.isXml());
      res.parent := this;
      res.IXmlDomElement := IXmlDomElement;
      res.definition := definition;
  	  if (IXmlDomElement.isXml()) begin
        res.literalPath := getLiteralPath() + (IXmlDomElement.getNamespace().equals(FormatUtilities.XHTML_NS) ? '/h:' : '/f:')+IXmlDomElement.getName();
      if (count > -1)
        res.literalPath := res.literalPath + '['+Integer.toString(count)+']';
  	  end else begin
  	  	if (IXmlDomElement.getName() = nil)
  	  		res.literalPath := '';
  	  	else
        res.literalPath := getLiteralPath() + '/' +IXmlDomElement.getName();
        if (count > -1)
          res.literalPath := res.literalPath + '/'+Integer.toString(count);
  	  end
      res.logicalPaths := new ArrayTAdvList<String>();
      if (type !:= nil) begin
        // type will be bull if we on a stitching point of a contained resource, or if....
        res.type := type;
        String t := tail(definition.getPath());
        for (String lp : getLogicalPaths()) begin
          res.logicalPaths.add(lp+'.'+t);
          if (t.endsWith('[x]'))
            res.logicalPaths.add(lp+'.'+t.substring(0, t.length()-3)+type.getPath());
        end
        res.logicalPaths.add(type.getPath());
      end else if (definition !:= nil) begin
        for (String lp : getLogicalPaths())
          res.logicalPaths.add(lp+'.'+IXmlDomElement.getName());
      end else
        res.logicalPaths.addAll(getLogicalPaths());
//      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
//      for (String lp : res.logicalPaths)
//        b.append(lp);
//      System.out.println(res.literalPath+' : '+b.toString());
      return res;
    end

    private String getLiteralPath() begin
      return literalPath = nil ? '' : literalPath;
    end
    private TAdvList<String> getLogicalPaths() begin
      return logicalPaths = nil ? new ArrayTAdvList<String>() : logicalPaths;
    end

    private TWrapperElement getElement() begin
      return IXmlDomElement;
    end

    private ElementDefinition getType() begin
      return type;
    end

    private ElementDefinition getDefinition() begin
      return definition;
    end

    private void setType(ElementDefinition type) begin
      self.type := type;
    end

		public String addToLiteralPath(String... path) begin
			StringBuilder b := new StringBuilder();
			b.append(getLiteralPath());
			if (xml) begin
				for (String p : path) begin
					if (p.startsWith(':')) begin
						b.append('[');
						b.append(p.substring(1));
						b.append(']');
					end else begin
						b.append('/f:');
						b.append(p);
					end
				end
			end else begin
				for (String p : path) begin
					b.append('/');
					if (p.startsWith(':')) begin
						b.append(p.substring(1));
					end else begin
						b.append(p);
					end
				end
			end
			return b.toString();
    end
  end

  private WorkerContext context;
  private ProfileUtilities utilities;
  private ValueSetExpansionCache cache;
  private boolean requiresResourceId;
	private TAdvList<String> extensionDomains := new ArrayTAdvList<String>();
	private boolean anyExtensionsAllowed;

  public InstanceValidator(WorkerContext context) throws Exception begin
    super();
    self.context := context;
    source := Source.InstanceValidator;
    cache := new ValueSetExpansionCache(context, nil);
    utilities := new ProfileUtilities(context);
  end


  public InstanceValidator(WorkerContext context, ValueSetExpansionCache cache) throws Exception begin
    super();
    self.context := context;
    source := Source.InstanceValidator;
    self.cache := cache;
    utilities := new ProfileUtilities(context);
  end


  public WorkerContext getContext() begin
		return context;
	end
  /*
   * The actual base entry point
   */
  private void validateResource(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, boolean needsId, NodeStack stack) throws Exception begin
    if (stack = nil)
      stack := new NodeStack(IXmlDomElement.isXml());

    // getting going - either we got a profile, or not.
    boolean ok := true;
    if (IXmlDomElement.isXml()) begin
      ok := rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), '/', IXmlDomElement.getNamespace().equals(FormatUtilities.FHIR_NS), 'Namespace mismatch - expected '''+FormatUtilities.FHIR_NS+''', found '''+IXmlDomElement.getNamespace()+'''');
    end
    if (ok) begin
        String resourceName := IXmlDomElement.getResourceType();
      if (profile = nil) begin
        profile := context.getProfiles().get('http://hl7.org/fhir/TFHIRStructureDefinition/'+resourceName);
          ok := rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.addToLiteralPath(resourceName), profile !:= nil, 'No profile found for resource type '''+resourceName+'''');
      end else begin
        String type := profile.hasConstrainedType() ? profile.getConstrainedType() : profile.getName();
          ok := rule(errors, IssueType.INVALID, -1, -1, stack.addToLiteralPath(resourceName), type.equals(resourceName), 'Specified profile type was '''+profile.getConstrainedType()+''', but resource type was '''+resourceName+'''');
      end
    end

    if (ok) begin
      stack := stack.push(IXmlDomElement, -1, profile.getSnapshot().getElement().get(0), profile.getSnapshot().getElement().get(0));
      if (needsId and (IXmlDomElement.getNamedChild('id') = nil))
        rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), false, 'Resource has no id');
      start(errors, IXmlDomElement, profile, stack); // root is both definition and type
    end
  end


  // we assume that the following things are true:
  // the instance at root is valid against the schema and schematron
  // the instance validator had no issues against the base resource profile
  private void start(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, NodeStack stack) throws Exception begin
    // profile is valid, and matches the resource name
    if (rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), profile.hasSnapshot(), 'TFHIRStructureDefinition has no snapshot - validation is against the snapshot, so it must be provided')) begin
      validateElement(errors, profile, profile.getSnapshot().getElement().get(0), nil, nil, IXmlDomElement, IXmlDomElement.getName(), stack);

      checkDeclaredProfiles(errors, IXmlDomElement, stack);

      // specific known special validations
      if (IXmlDomElement.getResourceType().equals('Bundle'))
        validateBundle(errors, IXmlDomElement, stack);
      if (IXmlDomElement.getResourceType().equals('Observation'))
        validateObservation(errors, IXmlDomElement, stack);
    end
  end

//	private String findProfileTag(TWrapperElement IXmlDomElement) begin
//  	String uri := nil;
//	  list : TAdvList<TWrapperElement> := new ArrayTAdvList<TWrapperElement>();
//	  IXmlDomElement.getNamedChildren('category', list);
//	  for (TWrapperElement c : list) begin
//	  	if ('http://hl7.org/fhir/tag/profile'.equals(c.getAttribute('scheme'))) begin
//	  		uri := c.getAttribute('term');
//	  	end
//	  end
//	  return uri;
//  end


  private void checkDeclaredProfiles(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, NodeStack stack) throws Exception begin
    TWrapperElement meta := IXmlDomElement.getNamedChild('meta');
    if (meta !:= nil) begin
      TAdvList<TWrapperElement> profiles := new ArrayTAdvList<InstanceValidator.TWrapperElement>();
      meta.getNamedChildren('profile', profiles);
      Integer i := 0;
      for (TWrapperElement profile : profiles) begin
        String ref := profile.getAttribute('value');
        String p := stack.addToLiteralPath('meta', 'profile', ':'+Integer.toString(i));
        if (rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), p, !Utilities.noString(ref), 'TFHIRStructureDefinition reference invalid')) begin
          TFHIRStructureDefinition pr := context.getProfiles().get(ref);
          if (warning(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), p, pr !:= nil, 'TFHIRStructureDefinition reference could not be resolved')) begin
            if (rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), p, pr.hasSnapshot(), 'TFHIRStructureDefinition has no snapshot - validation is against the snapshot, so it must be provided')) begin
              validateElement(errors, pr, pr.getSnapshot().getElement().get(0), nil, nil, IXmlDomElement, IXmlDomElement.getName(), stack);
            end
          end
          i++;
        end
      end
    end
  end

  private void validateBundle(errors : TAdvList<ValidationMessage>; TWrapperElement bundle, NodeStack stack) begin
    TAdvList<TWrapperElement> entries := new ArrayTAdvList<TWrapperElement>();
    bundle.getNamedChildren('entry', entries);
    String type := bundle.getNamedChildValue('type');
    if (entries.size() = 0) begin
      rule(errors, IssueType.INVALID, stack.getLiteralPath(), !(type.equals('document') or type.equals('message')), 'Documents or Messages must contain at least one entry');
    end else begin
      TWrapperElement firstEntry := entries.get(0);
      NodeStack firstStack := stack.push(firstEntry, 0, nil, nil);
      String fullUrl := firstEntry.getNamedChildValue('fullUrl');

      if (type.equals('document')) begin
        TWrapperElement res := firstEntry.getNamedChild('resource');
        NodeStack localStack := firstStack.push(res, -1, nil, nil);
        TWrapperElement resource := res.getFirstChild();
        String id := resource.getNamedChildValue('id');
        if (rule(errors, IssueType.INVALID, firstEntry.line(), firstEntry.col(), stack.addToLiteralPath('entry', ':0'), res !:= nil, 'No resource on first entry')) begin
          if (bundle.isXml())
            validateDocument(errors, entries, resource, localStack.push(resource, -1, nil, nil), fullUrl, id);
          else
            validateDocument(errors, entries, res, localStack, fullUrl, id);
        end
      end
      if (type.equals('message'))
        validateMessage(errors, bundle);
    end
  end

  private void validateMessage(errors : TAdvList<ValidationMessage>; TWrapperElement bundle) begin
    // TODO Auto-generated method stub

  end


  private void validateDocument(errors : TAdvList<ValidationMessage>; TAdvList<TWrapperElement> entries, TWrapperElement composition, NodeStack stack, String fullUrl, String id) begin
    // first entry must be a composition
    if (rule(errors, IssueType.INVALID, composition.line(), composition.col(), stack.getLiteralPath(), composition.getResourceType().equals('Composition'), 'The first entry in a document must be a composition')) begin
      // the composition subject and section references must resolve in the bundle
      validateBundleReference(errors, entries, composition.getNamedChild('subject'), 'Composition Subject', stack.push(composition.getNamedChild('subject'), -1, nil, nil), fullUrl, 'Composition', id);
      validateSections(errors, entries, composition, stack, fullUrl, id);
    end
  end
//rule(errors, IssueType.INVALID, bundle.line(), bundle.col(), 'Bundle', !'urn:guid:'.equals(base), 'The base ''urn:guid:'' is not valid (use urn:uuid:)');
//rule(errors, IssueType.INVALID, entry.line(), entry.col(), localStack.getLiteralPath(), !'urn:guid:'.equals(ebase), 'The base ''urn:guid:'' is not valid');
//rule(errors, IssueType.INVALID, entry.line(), entry.col(), localStack.getLiteralPath(), !Utilities.noString(base) or !Utilities.noString(ebase), 'entry does not have a base');
//String firstBase := nil;
//firstBase := ebase = nil ? base : ebase;

  private void validateSections(errors : TAdvList<ValidationMessage>; TAdvList<TWrapperElement> entries, TWrapperElement focus, NodeStack stack, String fullUrl, String id) begin
    TAdvList<TWrapperElement> sections := new ArrayTAdvList<TWrapperElement>();
    focus.getNamedChildren('entry', sections);
    Integer i := 0;
    for (TWrapperElement section : sections) begin
      NodeStack localStack := stack.push(section,  1, nil, nil);
			validateBundleReference(errors, entries, section.getNamedChild('content'), 'Section Content', localStack, fullUrl, 'Composition', id);
      validateSections(errors, entries, section, localStack, fullUrl, id);
      i++;
    end
  end

  private void validateBundleReference(errors : TAdvList<ValidationMessage>; TAdvList<TWrapperElement> entries, TWrapperElement ref, name : String, NodeStack stack, String fullUrl, String type, String id) begin
    if (ref !:= nil and !Utilities.noString(ref.getNamedChildValue('reference'))) begin
      TWrapperElement target := resolveInBundle(entries, ref.getNamedChildValue('reference'), fullUrl, type, id);
      rule(errors, IssueType.INVALID, target.line(), target.col(), stack.addToLiteralPath('reference'), target !:= nil, 'Unable to resolve the target of the reference in the bundle ('+name+')');
    end
  end

  private TWrapperElement resolveInBundle(TAdvList<TWrapperElement> entries, String ref, String fullUrl, String type, String id) begin
    if (Utilities.isAbsoluteUrl(ref)) begin
      // if the reference is absolute, then you resolve by fullUrl. No other thinking is required.
      for (TWrapperElement entry : entries) begin
        String fu := entry.getNamedChildValue('fullUrl');
        if (ref.equals(fu))
          return entry;
      end
      return nil;
    end else begin
      // split into base, type, and id
      String u := nil;
      if (fullUrl !:= nil and fullUrl.endsWith(type+'/'+id))
        // fullUrl := complex
        u := fullUrl.substring((type+'/'+id).length())+ref;
      String[] parts := ref.split('\\/');
      if (parts.length >:= 2) begin
        String t := parts[0];
        String i := parts[1];
        for (TWrapperElement entry : entries) begin
          String fu := entry.getNamedChildValue('fullUrl');
          if (u !:= nil and fullUrl.equals(u))
            return entry;
          if (u = nil) begin
            TWrapperElement res := entry.getNamedChild('resource');
            TWrapperElement resource := res.getFirstChild();
            String et := resource.getResourceType();
            String eid := resource.getNamedChildValue('id');
            if (t.equals(et) and i.equals(eid))
              return entry;
          end
        end
      end
      return nil;
    end
  end

  private TFHIRStructureDefinition getProfileForType(String type) throws Exception begin
    return context.getProfiles().get('http://hl7.org/fhir/TFHIRStructureDefinition/'+type);
  end

  private void validateObservation(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, NodeStack stack) begin
    // all observations should have a subject, a performer, and a time

    bpCheck(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), IXmlDomElement.getNamedChild('subject') !:= nil, 'All observations should have a subject');
    bpCheck(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), IXmlDomElement.getNamedChild('performer') !:= nil, 'All observations should have a performer');
    bpCheck(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), IXmlDomElement.getNamedChild('effectiveDateTime') !:= nil or IXmlDomElement.getNamedChild('effectivePeriod') !:= nil , 'All observations should have an effectiveDateTime or an effectivePeriod');
  end

  private void bpCheck(errors : TAdvList<ValidationMessage>; IssueType invalid, Integer line, Integer col, String literalPath, boolean test, String message) begin
  	if (bpWarnings !:= nil) begin
    switch (bpWarnings) begin
    case Error: rule(errors, invalid, line, col, literalPath, test, message);
    case Warning: warning(errors, invalid, line, col, literalPath, test, message);
    case Hint: hint(errors, invalid, line, col, literalPath, test, message);
    default: // do nothing
    end
  end
  end

  private void validateElement(errors : TAdvList<ValidationMessage>; TFHIRStructureDefinition profile, ElementDefinition definition, TFHIRStructureDefinition cprofile, ElementDefinition context, TWrapperElement IXmlDomElement, String actualType, NodeStack stack) throws Exception begin
    // irrespective of what IXmlDomElement it is, it cannot be empty
  	if (IXmlDomElement.isXml()) begin
      rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), FormatUtilities.FHIR_NS.equals(IXmlDomElement.getNamespace()), 'Namespace mismatch - expected '''+FormatUtilities.FHIR_NS+''', found '''+IXmlDomElement.getNamespace()+'''');
      rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), !IXmlDomElement.hasNamespace('http://www.w3.org/2001/XMLSchema-instance'), 'Schema Instance Namespace is not allowed in instances');
      rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), !IXmlDomElement.hasProcessingInstruction(), 'No Processing Instructions in resources');
  	end
    rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), !empty(IXmlDomElement), 'Elements must have some content (@value, extensions, or children elements)');

    // get the list of direct defined children, including slices
    TAdvList<ElementDefinition> childDefinitions := ProfileUtilities.getChildMap(profile, definition.getName(), definition.getPath(), definition.getNameReference());

    // 1. List the children, and remember their exact path (convenience)
    TAdvList<ElementInfo> children := new ArrayTAdvList<InstanceValidator.ElementInfo>();
    ChildIterator iter := new ChildIterator(stack.getLiteralPath(), IXmlDomElement);
    while (iter.next())
    	children.add(new ElementInfo(iter.name(), iter.IXmlDomElement(), iter.path(), iter.count()));

    // 2. assign children to a definition
    // for each definition, for each child, check whether it belongs in the slice
    ElementDefinition slice := nil;
    for (ElementDefinition ed : childDefinitions) begin
    	boolean process := true;
    	// where are we with slicing
    	if (ed.hasSlicing()) begin
    		if (slice !:= nil and slice.getPath().equals(ed.getPath()))
    			raise Exception.create('Slice encountered midway through path on '+slice.getPath());
    		slice := ed;
    		process := false;
    	end else if (slice !:= nil and !slice.getPath().equals(ed.getPath()))
    		slice := nil;

    	if (process) begin
    	for (ElementInfo ei : children) begin
    			boolean match := false;
    		if (slice = nil) begin
    			match := nameMatches(ei.name, tail(ed.getPath()));
    		end else begin
    				if (nameMatches(ei.name, tail(ed.getPath())))
    					match := sliceMatches(ei.IXmlDomElement, ei.path, slice, ed, profile);
    		end
    		if (match) begin
    				if (rule(errors, IssueType.INVALID, ei.line(), ei.col(), ei.path, ei.definition = nil, 'IXmlDomElement matches more than one slice'))
    				ei.definition := ed;
    		end
    	end
    end
    	end
    for (ElementInfo ei : children)
      if (ei.path.endsWith('.extension'))
        rule(errors, IssueType.INVALID, ei.line(), ei.col(), ei.path, ei.definition !:= nil, 'IXmlDomElement is unknown or does not match any slice (url:=\''+ei.IXmlDomElement.getAttribute('url')+'\')');
      else
        rule(errors, IssueType.INVALID, ei.line(), ei.col(), ei.path, ei.definition !:= nil, 'IXmlDomElement is unknown or does not match any slice');

    // 3. report any definitions that have a cardinality problem
    for (ElementDefinition ed : childDefinitions) begin
    	if (ed.getRepresentation().isEmpty()) begin // ignore xml attributes
    	Integer count := 0;
      for (ElementInfo ei : children)
      	if (ei.definition = ed)
      		count++;
  		if (ed.getMin() > 0) begin
  			rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), count >:= ed.getMin(), 'IXmlDomElement '''+stack.getLiteralPath()+'.'+tail(ed.getPath())+''': minimum required := '+Integer.toString(ed.getMin())+', but only found '+Integer.toString(count));
    		end
  		if (ed.hasMax() and !ed.getMax().equals('*')) begin
  			rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), count <:= Integer.parseInt(ed.getMax()), 'IXmlDomElement '+tail(ed.getPath())+' @ '+stack.getLiteralPath()+': max allowed := '+Integer.toString(ed.getMin())+', but found '+Integer.toString(count));
    		end

    	end
    end
    // 4. check order if any slices are orderd. (todo)

    // 5. inspect each child for validity
    for (ElementInfo ei : children) begin
    	if (ei.definition !:= nil) begin
      String type := nil;
      ElementDefinition typeDefn := nil;
    		if (ei.definition.getType().size() = 1 and !ei.definition.getType().get(0).getCode().equals('*') and !ei.definition.getType().get(0).getCode().equals('IXmlDomElement') and !ei.definition.getType().get(0).getCode().equals('BackboneElement') )
    			type := ei.definition.getType().get(0).getCode();
    		else if (ei.definition.getType().size() = 1 and ei.definition.getType().get(0).getCode().equals('*')) begin
          String prefix := tail(ei.definition.getPath());
          assert prefix.endsWith('[x]');
          type := ei.name.substring(prefix.length()-3);
          if (isPrimitiveType(type))
            type := Utilities.uncapitalize(type);
    		end else if (ei.definition.getType().size() > 1) begin

            String prefix := tail(ei.definition.getPath());
            assert prefix.endsWith('[x]');
            prefix := prefix.substring(0, prefix.length()-3);
            for (TypeRefComponent t : ei.definition.getType())
              if ((prefix+Utilities.capitalize(t.getCode())).equals(ei.name))
                type := t.getCode();
            if (type = nil) begin
        			TypeRefComponent trc := ei.definition.getType().get(0);
        			if(trc.getCode().equals('Reference'))
        				type := 'Reference';
              else
              	rule(errors, IssueType.STRUCTURE, ei.line(), ei.col(), stack.getLiteralPath(), false, 'The IXmlDomElement '+ei.name+' is illegal. Valid types at this point are '+describeTypes(ei.definition.getType()));
          end
    		end else if (ei.definition.getNameReference() !:= nil) begin
    			typeDefn := resolveNameReference(profile.getSnapshot(), ei.definition.getNameReference());
        end


        if (type !:= nil) begin
          if (type.startsWith('@')) begin
    				ei.definition := findElement(profile, type.substring(1));
            type := nil;
          end
        end
    		NodeStack localStack := stack.push(ei.IXmlDomElement, ei.count, ei.definition, type = nil ? typeDefn : resolveType(type));
    		assert(ei.path.equals(localStack.getLiteralPath()));

      if (type !:= nil) begin
        if (typeIsPrimitive(type))
    				checkPrimitive(errors, ei.path, type, ei.definition, ei.IXmlDomElement);
        else begin
          if (type.equals('Identifier'))
    					checkIdentifier(errors, ei.path, ei.IXmlDomElement, ei.definition);
          else if (type.equals('Coding'))
    					checkCoding(errors, ei.path, ei.IXmlDomElement, profile, ei.definition);
          else if (type.equals('CodeableConcept'))
    					checkCodeableConcept(errors, ei.path, ei.IXmlDomElement, profile, ei.definition);
          else if (type.equals('Reference'))
    					checkReference(errors, ei.path, ei.IXmlDomElement, profile, ei.definition, actualType, localStack);

          if (type.equals('Extension'))
            checkExtension(errors, ei.path, ei.IXmlDomElement, ei.definition, profile, localStack);
          else if (type.equals('Resource'))
    					validateContains(errors, ei.path, ei.definition, definition, ei.IXmlDomElement, localStack, !isBundleEntry(ei.path)); //    if (str.matches('.*([.,/])work\\1$'))
          else begin
            TFHIRStructureDefinition p := getProfileForType(type);
            if (rule(errors, IssueType.STRUCTURE, ei.line(), ei.col(), ei.path, p !:= nil, 'Unknown type '+type)) begin
    						validateElement(errors, p, p.getSnapshot().getElement().get(0), profile, ei.definition, ei.IXmlDomElement, type, localStack);
            end
          end
        end
      end else begin
    			if (rule(errors, IssueType.STRUCTURE, ei.line(), ei.col(), stack.getLiteralPath(), ei.definition !:= nil, 'Unrecognised Content '+ei.name))
    				validateElement(errors, profile, ei.definition, nil, nil, ei.IXmlDomElement, type, localStack);
    		end
      end
    end
  end

  /**
   *
   * @param IXmlDomElement - the candidate that might be in the slice
   * @param path - for reporting any errors. the XPath for the IXmlDomElement
   * @param slice - the definition of how slicing is determined
   * @param ed - the slice for which to test membership
   * @return
   * @throws Exception
   */
  private boolean sliceMatches(TWrapperElement IXmlDomElement, String path, ElementDefinition slice, ElementDefinition ed; profile : TFHIRStructureDefinition) throws Exception begin
  	if (!slice.getSlicing().hasDiscriminator())
  		return false; // cannot validate in this case
	  for (StringType s : slice.getSlicing().getDiscriminator()) begin
	  	String discriminator := s.getValue();
	  	ElementDefinition criteria := getCriteriaForDiscriminator(path, ed, discriminator, profile);
	  	if (discriminator.equals('url') and criteria.getPath().equals('Extension.url')) begin
	  		if (!IXmlDomElement.getAttribute('url').equals(((UriType) criteria.getFixed()).asStringValue()))
	  			return false;
	  	end else begin
	  		IXmlDomElement value := getValueForDiscriminator(IXmlDomElement, discriminator, criteria);
	  		if (!valueMatchesCriteria(value, criteria))
	  			return false;
	  	end
	  end
	  return true;
  end

	private boolean valueMatchesCriteria(IXmlDomElement value, ElementDefinition criteria) begin
		raise Exception.create('validation of slices not done yet');
  end

	private IXmlDomElement getValueForDiscriminator(TWrapperElement IXmlDomElement, String discriminator, ElementDefinition criteria) begin
		raise Exception.create('validation of slices not done yet');
  end

	private ElementDefinition getCriteriaForDiscriminator(String path, ElementDefinition ed, String discriminator; profile : TFHIRStructureDefinition) throws Exception begin
    TAdvList<ElementDefinition> childDefinitions := ProfileUtilities.getChildMap(profile, ed);
    TAdvList<ElementDefinition> snapshot := nil;
    if (childDefinitions.isEmpty()) begin
    	// going to look at the type
    	if (ed.getType().size() = 0)
    		raise Exception.create('Error in profile for '+path+' no children, no type');
    	if (ed.getType().size() > 1)
    		raise Exception.create('Error in profile for '+path+' multiple types defined in slice discriminator');
    	TFHIRStructureDefinition type;
    	if (ed.getType().get(0).hasProfile())
    		type := context.getExtensionStructure(profile, ed.getType().get(0).getProfile().get(0).getValue());
    	else
    		type := context.getExtensionStructure(profile, 'http://hl7.org/fhir/TFHIRStructureDefinition/'+ed.getType().get(0).getCode());
    	snapshot := type.getSnapshot().getElement();
    	ed := snapshot.get(0);
    end else begin
      snapshot := profile.getSnapshot().getElement();
    end
		String originalPath := ed.getPath();
		String goal := originalPath+'.'+discriminator;

		Integer index := snapshot.indexOf(ed);
		assert (index > -1);
		index++;
		while (index < snapshot.size() and !snapshot.get(index).getPath().equals(originalPath)) begin
			if (snapshot.get(index).getPath().equals(goal))
				return snapshot.get(index);
			index++;
		end
		raise Exception.create('Unable to find discriminator definition for '+goal+' in '+discriminator+' at '+path);
  end

  private boolean isPrimitiveType(String type) begin
    return
        type.equalsIgnoreCase('boolean') or type.equalsIgnoreCase('integer') or type.equalsIgnoreCase('string') or type.equalsIgnoreCase('decimal') or
        type.equalsIgnoreCase('uri') or type.equalsIgnoreCase('base64Binary') or type.equalsIgnoreCase('instant') or type.equalsIgnoreCase('date') or
        type.equalsIgnoreCase('dateTime') or type.equalsIgnoreCase('time') or type.equalsIgnoreCase('code') or type.equalsIgnoreCase('oid') or type.equalsIgnoreCase('id');
  end

  private boolean nameMatches(name : String, String tail) begin
	  if (tail.endsWith('[x]'))
	    return name.startsWith(tail.substring(0,  tail.length()-3));
	  else
	    return (name.equals(tail));
  end

  private ElementDefinition resolveNameReference(StructureDefinitionSnapshotComponent snapshot, name : String) begin
  	for (ElementDefinition ed : snapshot.getElement())
  		if (name.equals(ed.getName()))
  			return ed;
	  return nil;
  end

  private ElementDefinition resolveType(String type) begin
    String url := 'http://hl7.org/fhir/TFHIRStructureDefinition/'+type;
    TFHIRStructureDefinition sd := context.getProfiles().get(url);
    if (sd = nil or !sd.hasSnapshot())
      return nil;
    else
      return sd.getSnapshot().getElement().get(0);
  end

//  private String mergePath(String path1, String path2) begin
//    // path1 is xpath path
//    // path2 is dotted path
//    String[] parts := path2.split('\\.');
//    StringBuilder b := new StringBuilder(path1);
//    for (Integer i := 1; i < parts.length -1; i++)
//      b.append('/f:'+parts[i]);
//    return b.toString();
//  end

  private boolean isBundleEntry(String path) begin
    String[] parts := path.split('\\/');
    if (path.startsWith('/f:'))
      return parts.length > 2 and parts[parts.length-1].startsWith('f:resource') and (parts[parts.length-2].equals('f:entry') or parts[parts.length-2].startsWith('f:entry['));
    else
      return parts.length > 2 and parts[parts.length-1].equals('resource') and ((parts.length > 2 and parts[parts.length-3].equals('entry')) or parts[parts.length-2].equals('entry'));
  end

  private String describeTypes(TAdvList<TypeRefComponent> types) begin
    CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
    for (TypeRefComponent t : types) begin
      b.append(t.getCode());
    end
    return b.toString();
  end

  private void checkReference(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, ElementDefinition container, String parentType, NodeStack stack) throws Exception begin
    String ref := IXmlDomElement.getNamedChildValue('reference');
    if (Utilities.noString(ref)) begin
      // todo - what should we do in this case?
      hint(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, !Utilities.noString(IXmlDomElement.getNamedChildValue('display')), 'A Reference without an actual reference should have a display');
      return;
    end

    TWrapperElement we := resolve(ref, stack);
    String ft;
    if (we !:= nil)
      ft := we.getResourceType();
    else
      ft := tryParse(ref);
    if (hint(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, ft !:= nil, 'Unable to determine type of target resource')) begin
      boolean ok := false;
      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
      for (TypeRefComponent type : container.getType()) begin
        if (!ok and type.getCode().equals('Reference')) begin
          // we validate as much as we can. First, can we infer a type from the profile?
          if (!type.hasProfile() or type.getProfile().get(0).getValue().equals('http://hl7.org/fhir/TFHIRStructureDefinition/Resource'))
            ok := true;
          else begin
            String pr := type.getProfile().get(0).getValue();

            String bt := getBaseType(profile, pr);
            if (rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, bt !:= nil, 'Unable to resolve the profile reference '''+pr+'''')) begin
              b.append(bt);
              ok := bt.equals(ft);
            end else
              ok := true; // suppress following check
          end
        end
        if (!ok and type.getCode().equals('*')) begin
          ok := true; // can refer to anything
        end
      end
      rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, ok, 'Invalid Resource target type. Found '+ft+', but expected one of ('+b.toString()+')');
    end
  end

  private TWrapperElement resolve(String ref, NodeStack stack) begin
    if (ref.startsWith('#')) begin
      // work back through the contained list.
      // really, there should only be one level for this (contained resources cannot contain
      // contained resources), but we''ll leave that to some other code to worry about
      while (stack !:= nil and stack.getElement() !:= nil) begin
        TWrapperElement res := getContainedById(stack.getElement(), ref.substring(1));
        if (res !:= nil)
          return res;
        stack := stack.parent;
      end
      return nil;
    end else begin
      // work back through the contained list - if any of them are bundles, try to resolve
      // the resource in the bundle
      while (stack !:= nil and stack.getElement() !:= nil) begin
        if ('Bundle'.equals(stack.getElement().getResourceType())) begin
          TWrapperElement res := getFromBundle(stack.getElement(), ref.substring(1));
          if (res !:= nil)
            return res;
        end
        stack := stack.parent;
      end

      // todo: consult the external host for resolution
      return nil;

    end
  end

  private TWrapperElement getFromBundle(TWrapperElement bundle, String ref) begin
    TAdvList<TWrapperElement> entries := new ArrayTAdvList<TWrapperElement>();
    bundle.getNamedChildren('entry', entries);
    for (TWrapperElement we : entries) begin
      TWrapperElement res := we.getNamedChild('resource').getFirstChild();
      if (res !:= nil) begin
        String url := genFullUrl(bundle.getNamedChildValue('base'), we.getNamedChildValue('base'), res.getName(), res.getNamedChildValue('id'));
        if (url.endsWith(ref))
          return res;
      end
    end
    return nil;
  end

  private String genFullUrl(String bundleBase, String entryBase, String type, String id) begin
    String base := Utilities.noString(entryBase) ? bundleBase : entryBase;
    if (Utilities.noString(base)) begin
      return type+'/'+id;
    end else if ('urn:uuid'.equals(base) or 'urn:oid'.equals(base))
      return base+id;
    else
      return Utilities.appendSlash(base)+type+'/'+id;
  end

  private TWrapperElement getContainedById(TWrapperElement container, String id) begin
    TAdvList<TWrapperElement> contained := new ArrayTAdvList<TWrapperElement>();
    container.getNamedChildren('contained', contained);
    for (TWrapperElement we : contained) begin
    	TWrapperElement res := we.isXml() ? we.getFirstChild() : we;
      if (id.equals(res.getNamedChildValue('id')))
        return res;
    end
    return nil;
  end

  private String tryParse(String ref) begin
    String[] parts := ref.split('\\/');
    switch (parts.length) begin
    case 1:
      return nil;
    case 2:
      return checkResourceType(parts[0]);
    default:
      if (parts[parts.length-2].equals('_history'))
        return checkResourceType(parts[parts.length-4]);
      else
        return checkResourceType(parts[parts.length-2]);
    end
  end

  private String checkResourceType(String type) begin
    if (context.getProfiles().containsKey('http://hl7.org/fhir/TFHIRStructureDefinition/'+type))
      return type;
    else
      return nil;
  end
  private String getBaseType(TFHIRStructureDefinition profile, String pr) begin
//    if (pr.startsWith('http://hl7.org/fhir/TFHIRStructureDefinition/')) begin
//      // this just has to be a base type
//      return pr.substring(40);
//    end else begin
      TFHIRStructureDefinition p := resolveProfile(profile, pr);
      if (p = nil)
        return nil;
      else if (p.getKind() = StructureDefinitionKind.RESOURCE)
        return p.getSnapshot().getElement().get(0).getPath();
      else
        return p.getSnapshot().getElement().get(0).getType().get(0).getCode();
//    end
  end

  private TFHIRStructureDefinition resolveProfile(TFHIRStructureDefinition profile, String pr) begin
    if (pr.startsWith('#')) begin
      for (Resource r : profile.getContained()) begin
        if (r.getId().equals(pr.substring(1)) and r is TFHIRStructureDefinition)
          return (TFHIRStructureDefinition) r;
      end
      return nil;
    end
    else
      return context.getProfiles().get(pr);
  end

  private TFHIRStructureDefinition checkExtension(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement, ElementDefinition def; profile : TFHIRStructureDefinition, NodeStack stack) throws Exception begin
    String url := IXmlDomElement.getAttribute('url');
    boolean isModifier := IXmlDomElement.getName().equals('modifierExtension');

    TFHIRStructureDefinition ex := context.getExtensionStructure(profile, url);
    if (ex = nil) begin
      if (!rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, allowUnknownExtension(url), 'The extension '+url+' is unknown, and not allowed here'))
        warning(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, allowUnknownExtension(url), 'Unknown extension '+url);
    end else begin
      if (def.getIsModifier())
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+'[url:='''+url+''']', ex.getSnapshot().getElement().get(0).getIsModifier(), 'Extension modifier mismatch: the extension IXmlDomElement is labelled as a modifier, but the underlying extension is not');
      else
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+'[url:='''+url+''']', !ex.getSnapshot().getElement().get(0).getIsModifier(), 'Extension modifier mismatch: the extension IXmlDomElement is not labelled as a modifier, but the underlying extension is');

      // two questions
      // 1. can this extension be used here?
      checkExtensionContext(errors, IXmlDomElement, /*path+'[url:='''+url+''']',*/ ex, stack, ex.getUrl());

      if (isModifier)
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+'[url:='''+url+''']', ex.getSnapshot().getElement().get(0).getIsModifier(), 'The Extension '''+url+''' must be used as a modifierExtension');
      else
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+'[url:='''+url+''']', !ex.getSnapshot().getElement().get(0).getIsModifier(), 'The Extension '''+url+''' must not be used as an extension (it''s a modifierExtension)');

      // 2. is the content of the extension valid?

    end
    return ex;
  end

  private boolean allowUnknownExtension(String url) begin
    if (url.contains('example.org') or url.contains('acme.com') or url.contains('nema.org'))
    	return true;
    for (String s : extensionDomains)
    	if (url.startsWith(s))
    		return true;
    return anyExtensionsAllowed;
  end

  private boolean isKnownType(String code) begin
    return context.getProfiles().get(code.toLowerCase()) !:= nil;
  end

  private ElementDefinition getElementByPath(TFHIRStructureDefinition definition, String path) begin
    for (ElementDefinition e : definition.getSnapshot().getElement()) begin
      if (e.getPath().equals(path))
        return e;
    end
    return nil;
  end

  private boolean checkExtensionContext(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, TFHIRStructureDefinition definition, NodeStack stack, String extensionParent) begin
    String extUrl := definition.getUrl();
    CommaSeparatedStringBuilder p := new CommaSeparatedStringBuilder();
    for (String lp : stack.getLogicalPaths())
      p.append(lp);
	  if (definition.getContextType() = ExtensionContext.DATATYPE) begin
	    boolean ok := false;
	    CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
	    for (StringType ct : definition.getContext()) begin
	      b.append(ct.getValue());
	      if (ct.getValue().equals('*') or stack.getLogicalPaths().contains(ct.getValue()+'.extension'))
	        ok := true;
	    end
	    return rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), ok, 'The extension '+extUrl+' is not allowed to be used on the logical path set ['+p.toString()+'] (allowed: datatype:='+b.toString()+')');
	  end else if (definition.getContextType() = ExtensionContext.EXTENSION) begin
      boolean ok := false;
      for (StringType ct : definition.getContext())
        if (ct.getValue().equals('*') or ct.getValue().equals(extensionParent))
            ok := true;
      return rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), ok, 'The extension '+extUrl+' is not allowed to be used with the extension '''+extensionParent+'''');
	  end else if (definition.getContextType() = ExtensionContext.MAPPING) begin
  		raise Exception.create('Not handled yet (extensionContext)');
	  end else if (definition.getContextType() = ExtensionContext.RESOURCE) begin
      boolean ok := false;
//      String simplePath := container.getPath();
//      System.out.println(simplePath);
//      if (effetive.endsWith('.extension') or simplePath.endsWith('.modifierExtension'))
//        simplePath := simplePath.substring(0, simplePath.lastIndexOf(''.''));
      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
      for (StringType ct : definition.getContext()) begin
        String c := ct.getValue();
        b.append(c);
        if (c.equals('*') or stack.getLogicalPaths().contains(c+'.extension') or (c.startsWith('@') and stack.getLogicalPaths().contains(c.substring(1)+'.extension')));
            ok := true;
      end
      return rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), ok, 'The extension '+extUrl+' is not allowed to be used on the logical path set '+p.toString()+' (allowed: resource:='+b.toString()+')');
	  end else
  		raise Exception.create('Unknown context type');
  end
//
//  private String simplifyPath(String path) begin
//    String s := path.replace('/f:', '.');
//    while (s.contains('['))
//      s := s.substring(0, s.indexOf('['))+s.substring(s.indexOf(']')+1);
//    String[] parts := s.split('\\.');
//    Integer i := 0;
//    while (i < parts.length and !context.getProfiles().containsKey(parts[i].toLowerCase()))
//      i++;
//    if (i >:= parts.length)
//      raise Exception.create('Unable to process part '+path);
//    Integer j := parts.length - 1;
//    while (j > 0 and (parts[j].equals('extension') or parts[j].equals('modifierExtension')))
//        j--;
//    StringBuilder b := new StringBuilder();
//    boolean first := true;
//    for (Integer k := i; k <:= j; k++) begin
//      if (k = j or !parts[k].equals(parts[k+1])) begin
//        if (first)
//          first := false;
//        else
//        b.append('.');
//      b.append(parts[k]);
//    end
//    end
//    return b.toString();
//  end
//

  private boolean empty(TWrapperElement IXmlDomElement) begin
    if (IXmlDomElement.hasAttribute('value'))
      return false;
    if (IXmlDomElement.hasAttribute('xml:id'))
      return false;
    TWrapperElement child := IXmlDomElement.getFirstChild();
    while (child !:= nil) begin
      if (!child.isXml() or FormatUtilities.FHIR_NS.equals(child.getNamespace())) begin
        return false;
      end
      child := child.getNextSibling();
    end
    return true;
  end

  private ElementDefinition findElement(TFHIRStructureDefinition profile, name : String) begin
    for (ElementDefinition c : profile.getSnapshot().getElement()) begin
      if (c.getPath().equals(name)) begin
        return c;
      end
    end
    return nil;
  end

  private ElementDefinition getDefinitionByTailNameChoice(TAdvList<ElementDefinition> children, name : String) begin
    for (ElementDefinition ed : children) begin
    	String n := tail(ed.getPath());
      if (n.endsWith('[x]') and name.startsWith(n.substring(0, n.length()-3))) begin
        return ed;
      end
    end
    return nil;
  end

  private String tail(String path) begin
    return path.substring(path.lastIndexOf('.')+1);
  end

  private void validateContains(errors : TAdvList<ValidationMessage>; String path, ElementDefinition child, ElementDefinition context, TWrapperElement IXmlDomElement, NodeStack stack, boolean needsId) throws Exception begin
  	TWrapperElement e := IXmlDomElement.isXml() ? IXmlDomElement.getFirstChild() : IXmlDomElement;
  	String resourceName := e.getResourceType();
    TFHIRStructureDefinition profile := self.context.getProfiles().get('http://hl7.org/fhir/TFHIRStructureDefinition/'+resourceName);
    if (rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.addToLiteralPath(resourceName), profile !:= nil, 'No profile found for contained resource of type '''+resourceName+''''))
      validateResource(errors, e, profile, needsId, stack);
  end

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
  end

  private void checkPrimitive(errors : TAdvList<ValidationMessage>; String path, String type, ElementDefinition context, TWrapperElement e) begin
    if (type.equals('uri')) begin
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, !e.getAttribute('value').startsWith('oid:'), 'URI values cannot start with oid:');
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, !e.getAttribute('value').startsWith('uuid:'), 'URI values cannot start with uuid:');
    end
    if (!type.equalsIgnoreCase('string') and e.hasAttribute('value')) begin
      if (rule(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute('value').length() > 0, '@value cannot be empty')) begin
        warning(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute('value').trim().equals(e.getAttribute('value')), 'value should not start or finish with whitespace');
      end
    end
    if (type.equals('dateTime')) begin
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, yearIsValid(e.getAttribute('value')), 'The value '''+e.getAttribute('value')+''' does not have a valid year');
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute('value').matches('-?[0-9]begin4end(-(0[1-9]|1[0-2])(-(0[0-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?)?)?)?'), 'Not a valid date time');
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, !hasTime(e.getAttribute('value')) or hasTimeZone(e.getAttribute('value')), 'if a date has a time, it must have a timezone');

    end
    if (type.equals('instant')) begin
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute('value').matches('-?[0-9]begin4end-(0[1-9]|1[0-2])-(0[0-9]|[1-2][0-9]|3[0-1])T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))'), 'The instant '''+e.getAttribute('value')+''' is not valid (by regex)');
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, yearIsValid(e.getAttribute('value')), 'The value '''+e.getAttribute('value')+''' does not have a valid year');
    end

    // for nothing to check
  end

  private boolean yearIsValid(String v) begin
    if (v = nil) begin
        return false;
    end
    try begin
       Integer i := Integer.parseInt(v.substring(0, Math.min(4, v.length())));
       return i >:= 1800 and i <:= 2100;
    end catch (NumberFormatException e) begin
       return false;
    end
  end

  private boolean hasTimeZone(String fmt) begin
    return fmt.length() > 10 and (fmt.substring(10).contains('-') or fmt.substring(10).contains('-') or fmt.substring(10).contains('+') or fmt.substring(10).contains('Z'));
  end

  private boolean hasTime(String fmt) begin
    return fmt.contains('T');
  end

  private void checkIdentifier(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement, ElementDefinition context) begin
    String system := IXmlDomElement.getNamedChildValue('system');
    rule(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, isAbsolute(system), 'Identifier.system must be an absolute reference, not a local reference');
  end

  private boolean isAbsolute(String uri) begin
    return Utilities.noString(uri) or uri.startsWith('http:') or uri.startsWith('https:') or uri.startsWith('urn:uuid:') or uri.startsWith('urn:oid:') or
        uri.startsWith('urn:ietf:') or uri.startsWith('urn:iso:');
  end

  private void checkIdentifier(String path, element : IXmlDomElement, ElementDefinition context) begin

  end

  private void checkQuantity(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement, ElementDefinition context, boolean b) begin
    String code := IXmlDomElement.getNamedChildValue('code');
    String system := IXmlDomElement.getNamedChildValue('system');
    String units := IXmlDomElement.getNamedChildValue('units');

    if (system !:= nil and code !:= nil) begin
      checkCode(errors, IXmlDomElement, path, code, system, units);
    end
  end


  private void checkCoding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, ElementDefinition context) begin
    String code := IXmlDomElement.getNamedChildValue('code');
    String system := IXmlDomElement.getNamedChildValue('system');
    String display := IXmlDomElement.getNamedChildValue('display');
    rule(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, isAbsolute(system), 'Coding.system must be an absolute reference, not a local reference');

    if (system !:= nil and code !:= nil) begin
      if (checkCode(errors, IXmlDomElement, path, code, system, display))
        if (context !:= nil and context.getBinding() !:= nil) begin
          ElementDefinitionBindingComponent binding := context.getBinding();
          if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, binding !:= nil, 'Binding for '+path+' missing')) begin
            if (binding.hasValueSet() and binding.getValueSet() is Reference) begin
              ValueSet vs := resolveBindingReference(binding.getValueSet());
              if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, 'ValueSet '+describeReference(binding.getValueSet())+' not found')) begin
                try begin
                  vs := cache.getExpander().expand(vs).getValueset();
                  if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, 'Unable to expand value set for '+describeReference(binding.getValueSet()))) begin
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, codeInExpansion(vs, system, code), 'Code begin'+system+'end'+code+' is not in value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
                  end
                end catch (Exception e) begin
                  if (e.getMessage() = nil)
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': --Null--');
//                  else if (!e.getMessage().contains('unable to find value set http://snomed.info/sct'))
//                    hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, 'Snomed value set - not validated');
//                  else if (!e.getMessage().contains('unable to find value set http://loinc.org'))
//                    hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, 'Loinc value set - not validated');
                  else
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': '+e.getMessage());
                end
              end
            end else if (binding.hasValueSet())
              hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Binding by URI reference cannot be checked');
            else
              hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Binding has no source, so can''t be checked');
          end
        end
    end
  end


  private ValueSet resolveBindingReference(Type reference) begin
    if (reference is UriType)
      return context.getValueSets().get(((UriType) reference).getValue().toString());
    else if (reference is Reference)
      return context.getValueSets().get(((Reference) reference).getReference());
    else
      return nil;
  end

  private boolean codeInExpansion(ValueSet vs, String system, String code) begin
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) begin
      if (code.equals(c.getCode()) and (system = nil or system.equals(c.getSystem())))
        return true;
      if (codeinExpansion(c, system, code))
        return true;
    end
    return false;
  end

  private boolean codeinExpansion(ValueSetExpansionContainsComponent cnt, String system, String code) begin
    for (ValueSetExpansionContainsComponent c : cnt.getContains()) begin
      if (code.equals(c.getCode()) and system.equals(c.getSystem().toString()))
        return true;
      if (codeinExpansion(c, system, code))
        return true;
    end
    return false;
  end

  private void checkCodeableConcept(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, ElementDefinition context) begin
    if (context !:= nil and context.hasBinding()) begin
      ElementDefinitionBindingComponent binding := context.getBinding();
      if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, binding !:= nil, 'Binding for '+path+' missing (cc)')) begin
        if (binding.hasValueSet() and binding.getValueSet() is Reference) begin
          ValueSet vs := resolveBindingReference(binding.getValueSet());
          if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, 'ValueSet '+describeReference(binding.getValueSet())+' not found')) begin
            try begin
              ValueSetExpansionOutcome exp := cache.getExpander().expand(vs);
              vs := exp.getValueset();
              if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, 'Unable to expand value set for '+describeReference(binding.getValueSet()))) begin
                boolean found := false;
                boolean any := false;
                TWrapperElement c := IXmlDomElement.getFirstChild();
                while (c !:= nil) begin
                  if (c.getName().equals('coding')) begin
                    any := true;
                    String system := c.getNamedChildValue('system');
                    String code := c.getNamedChildValue('code');
                    if (system !:= nil and code !:= nil)
                      found := found or codeInExpansion(vs, system, code);
                  end
                  c := c.getNextSibling();
                end
                if (!any and binding.getStrength() = BindingStrength.REQUIRED)
                  warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, found, 'No code provided, and value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+') is required');
                if (any)
                  if (binding.getStrength() = BindingStrength.PREFERRED)
                    hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, found, 'None of the codes are in the example value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
                  else if (binding.getStrength() = BindingStrength.EXTENSIBLE)
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, found, 'None of the codes are in the expected value set '+describeReference(binding.getValueSet())+' ('+vs.getUrl()+')');
              end
            end catch (Exception e) begin
              if (e.getMessage() = nil) begin
                warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': --Null--');
//              end else if (!e.getMessage().contains('unable to find value set http://snomed.info/sct')) begin
//                hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, 'Snomed value set - not validated');
//              end else if (!e.getMessage().contains('unable to find value set http://loinc.org')) begin
//                hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, 'Loinc value set - not validated');
              end else
                warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Exception opening value set '+vs.getUrl()+' for '+describeReference(binding.getValueSet())+': '+e.getMessage());
            end
          end
        end else if (binding.hasValueSet())
          hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Binding by URI reference cannot be checked');
        else
          hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, 'Binding has no source, so can''t be checked');
      end
    end
  end

  private String describeReference(Type reference) begin
    if (reference = nil)
      return 'nil';
    if (reference is UriType)
      return ((UriType)reference).getValue();
    if (reference is Reference)
      return ((Reference)reference).getReference();
    return '??';
  end


  private boolean checkCode(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, String path, String code, String system, String display) begin
    if (context.getTerminologyServices() !:= nil and context.getTerminologyServices().verifiesSystem(system)) begin
      org.hl7.fhir.instance.terminologies.ITerminologyServices.ValidationResult s := context.getTerminologyServices().validateCode(system, code, display);
      if (s = nil)
        return true;
      if (s.getSeverity() = IssueSeverity.INFORMATION)
        hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, s = nil, s.getMessage());
      else if (s.getSeverity() = IssueSeverity.WARNING)
        warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, s = nil, s.getMessage());
      else
        return rule(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, s = nil, s.getMessage());
      return true;
    end else if (system.startsWith('http://hl7.org/fhir')) begin
      if (system.equals('http://hl7.org/fhir/sid/icd-10'))
        return true; // else don''t check ICD-10 (for now)
      else begin
        ValueSet vs := getValueSet(system);
        if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, 'Unknown Code System '+system)) begin
          ConceptDefinitionComponent def := getCodeDefinition(vs, code);
          if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, def !:= nil, 'Unknown Code ('+system+'#'+code+')'))
            return warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, display = nil or display.equals(def.getDisplay()), 'Display should be '''+def.getDisplay()+'''');
        end
        return false;
      end
    end else if (system.startsWith('http://loinc.org')) begin
      return true;
    end else if (system.startsWith('http://unitsofmeasure.org')) begin
      return true;
    end
    else
      return true;
  end

  private ConceptDefinitionComponent getCodeDefinition(ConceptDefinitionComponent c, String code) begin
    if (code.equals(c.getCode()))
      return c;
    for (ConceptDefinitionComponent g : c.getConcept()) begin
      ConceptDefinitionComponent r := getCodeDefinition(g, code);
      if (r !:= nil)
        return r;
    end
    return nil;
  end

  private ConceptDefinitionComponent getCodeDefinition(ValueSet vs, String code) begin
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
      ConceptDefinitionComponent r := getCodeDefinition(c, code);
      if (r !:= nil)
        return r;
    end
    return nil;
  end

  private ValueSet getValueSet(String system) begin
    return context.getCodeSystems().get(system);
  end


  public class ProfileStructureIterator begin

    private TFHIRStructureDefinition profile;
    private ElementDefinition elementDefn;
    private TAdvList<String> names := new ArrayTAdvList<String>();
    private Map<String, TAdvList<ElementDefinition>> children := new HashMap<String, TAdvList<ElementDefinition>>();
    private Integer cursor;

    public ProfileStructureIterator(TFHIRStructureDefinition profile, ElementDefinition elementDefn) begin
      self.profile := profile;
      self.elementDefn := elementDefn;
      loadMap();
      cursor := -1;
    end

    private void loadMap() begin
      Integer i := profile.getSnapshot().getElement().indexOf(elementDefn) + 1;
      String lead := elementDefn.getPath();
      while (i < profile.getSnapshot().getElement().size()) begin
        name : String := profile.getSnapshot().getElement().get(i).getPath();
        if (name.length() <:= lead.length())
          return; // cause we''ve got to the end of the possible matches
        String tail := name.substring(lead.length()+1);
        if (Utilities.isToken(tail) and name.substring(0, lead.length()).equals(lead)) begin
          TAdvList<ElementDefinition> list := children.get(tail);
          if (list = nil) begin
            list := new ArrayTAdvList<ElementDefinition>();
            names.add(tail);
            children.put(tail, list);
          end
          list.add(profile.getSnapshot().getElement().get(i));
        end
        i++;
      end
    end

    public boolean more() begin
      cursor++;
      return cursor < names.size();
    end

    public TAdvList<ElementDefinition> current() begin
      return children.get(name());
    end

    public name : String() begin
      return names.get(cursor);
    end

  end

  private void checkByProfile(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus; profile : TFHIRStructureDefinition, ElementDefinition elementDefn) throws Exception begin
    // we have an IXmlDomElement, and the structure that describes it.
    // we know that''s it''s valid against the underlying spec - is it valid against this one?
    // in the instance validator above, we assume that schema or schmeatron has taken care of cardinalities, but here, we have no such reliance.
    // so the walking algorithm is different: we''re going to walk the definitions
    String type;
  	if (elementDefn.getPath().endsWith('[x]')) begin
  		String tail := elementDefn.getPath().substring(elementDefn.getPath().lastIndexOf('.')+1, elementDefn.getPath().length()-3);
  		type := focus.getName().substring(tail.length());
  		rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, typeAllowed(type, elementDefn.getType()), 'The type '''+type+''' is not allowed at this point (must be one of '''+typeSummary(elementDefn)+')');
  	end else begin
  		if (elementDefn.getType().size() = 1) begin
  			type := elementDefn.getType().size() = 0 ? nil : elementDefn.getType().get(0).getCode();
  		end else
  			type := nil;
  	end
  	// constraints:
  	for (ElementDefinitionConstraintComponent c : elementDefn.getConstraint())
  		checkConstraint(errors, path, focus, c);
  	if (elementDefn.hasBinding() and type !:= nil)
  		checkBinding(errors, path, focus, profile, elementDefn, type);

  	// type specific checking:
  	if (type !:= nil and typeIsPrimitive(type)) begin
  		checkPrimitiveByProfile(errors, path, focus, elementDefn);
  	end else begin
  		if (elementDefn.hasFixed())
  			checkFixedValue(errors, path, focus, elementDefn.getFixed(), '');

  		ProfileStructureIterator walker := new ProfileStructureIterator(profile, elementDefn);
  		while (walker.more()) begin
  			// collect all the slices for the path
  			TAdvList<ElementDefinition> childset := walker.current();
  			// collect all the elements that match it by name
  			TAdvList<TWrapperElement> children := new ArrayTAdvList<TWrapperElement>();
  			focus.getNamedChildrenWithWildcard(walker.name(), children);

  			if (children.size() = 0) begin
  				// well, there''s no children - should there be?
  				for (ElementDefinition defn : childset) begin
  					if (!rule(errors, IssueType.REQUIRED, focus.line(), focus.col(), path, defn.getMin() = 0, 'Required IXmlDomElement '''+walker.name()+''' missing'))
  						break; // no point complaining about missing ones after the first one
  				end
  			end else if (childset.size() = 1) begin
  				// simple case: one possible definition, and one or more children.
  				rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, childset.get(0).getMax().equals('*') or Integer.parseInt(childset.get(0).getMax()) >:= children.size(),
  						'Too many elements for '''+walker.name()+''''); // todo: sort out structure
  				for (TWrapperElement child : children) begin
  					checkByProfile(errors, childset.get(0).getPath(), child, profile, childset.get(0));
  				end
  			end else begin
  				// ok, this is the full case - we have a list of definitions, and a list of candidates for meeting those definitions.
  				// we need to decide *if* that match a given definition
  			end
  		end
  	end
  end

	private void checkBinding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus; profile : TFHIRStructureDefinition, ElementDefinition elementDefn, String type) begin
	  ElementDefinitionBindingComponent bc := elementDefn.getBinding();

	  if (bc !:= nil and bc.hasValueSet() and bc.getValueSet() is Reference) begin
      String url := ((Reference) bc.getValueSet()).getReference();
	  	ValueSet vs := resolveValueSetReference(profile, (Reference) bc.getValueSet());
	  	if (vs = nil) begin
	      rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, false, 'Cannot check binding on type '''+type+''' as the value set '''+url+''' could not be located');
      end else if (type.equals('code'))
	  		checkBindingCode(errors, path, focus, vs);
	  	else if (type.equals('Coding'))
	  		checkBindingCoding(errors, path, focus, vs);
	  	else if (type.equals('CodeableConcept'))
	  		checkBindingCodeableConcept(errors, path, focus, vs);
	  	else
	  		rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, false, 'Cannot check binding on type '''+type+'''');
	  end
  end

	private ValueSet resolveValueSetReference(TFHIRStructureDefinition profile, Reference reference) begin
	  if (reference.getReference().startsWith('#')) begin
	  	for (Resource r : profile.getContained()) begin
	  		if (r is ValueSet and r.getId().equals(reference.getReference().substring(1)))
	  			return (ValueSet) r;
	  	end
	  	return nil;
	  end else
	  	return resolveBindingReference(reference);

  end

	private void checkBindingCode(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ValueSet vs) begin
	  // rule(errors, 'exception', path, false, 'checkBindingCode not done yet');
  end

	private void checkBindingCoding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ValueSet vs) begin
	  // rule(errors, 'exception', path, false, 'checkBindingCoding not done yet');
  end

	private void checkBindingCodeableConcept(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ValueSet vs) begin
	  // rule(errors, 'exception', path, false, 'checkBindingCodeableConcept not done yet');
  end

	private String typeSummary(ElementDefinition elementDefn) begin
	  StringBuilder b := new StringBuilder();
	  for (TypeRefComponent t : elementDefn.getType()) begin
	  	b.append('|'+t.getCode());
	  end
	  return b.toString().substring(1);
  end

	private boolean typeAllowed(String t, TAdvList<TypeRefComponent> types) begin
	  for (TypeRefComponent type : types) begin
	  	if (t.equals(Utilities.capitalize(type.getCode())))
	  		return true;
	  	if (t.equals('Resource') and Utilities.capitalize(type.getCode()).equals('Reference'))
	  	  return true;
	  end
	  return false;
  end

	private void checkConstraint(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ElementDefinitionConstraintComponent c) throws Exception begin

//		try
//   	begin
//			XPathFactory xpf := new net.sf.saxon.xpath.XPathFactoryImpl();
//      NamespaceContext context := new NamespaceContextMap('f', 'http://hl7.org/fhir', 'h', 'http://www.w3.org/1999/xhtml');
//
//			XPath xpath := xpf.newXPath();
//      xpath.setNamespaceContext(context);
//   		Boolean ok := (Boolean) xpath.evaluate(c.getXpath(), focus, XPathConstants.BOOLEAN);
//   		if (ok = nil or !ok) begin
//   			if (c.getSeverity() = ConstraintSeverity.warning)
//   				warning(errors, 'invariant', path, false, c.getHuman());
//   			else
//   				rule(errors, 'invariant', path, false, c.getHuman());
//   		end
//		end
//		catch (XPathExpressionException e) begin
//		  rule(errors, 'invariant', path, false, 'error executing invariant: '+e.getMessage());
//		end
  end

	private void checkPrimitiveByProfile(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ElementDefinition elementDefn) begin
		// two things to check - length, and fixed value
		String value := focus.getAttribute('value');
		if (elementDefn.hasMaxLengthElement()) begin
			rule(errors, IssueType.TOOLONG, focus.line(), focus.col(), path, value.length() <:= elementDefn.getMaxLength(), 'The value '''+value+''' exceeds the allow length limit of '+Integer.toString(elementDefn.getMaxLength()));
		end
		if (elementDefn.hasFixed()) begin
			checkFixedValue(errors, path, focus, elementDefn.getFixed(), '');
		end
  end

	private void checkFixedValue(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, org.hl7.fhir.instance.model.IXmlDomElement fixed, String propName) begin
		if (fixed = nil and focus = nil)
			; // this is all good
		else if (fixed = nil and focus !:= nil)
	  	rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, false, 'Unexpected IXmlDomElement '+focus.getName());
		else if (fixed !:= nil and focus = nil)
	  	rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, false, 'Mising IXmlDomElement '+propName);
		else begin
			String value := focus.getAttribute('value');
			if (fixed is org.hl7.fhir.instance.model.BooleanType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.IntegerType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.DecimalType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.Base64BinaryType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.InstantType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.InstantType) fixed).getValue().toString(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.InstantType) fixed).asStringValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.StringType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.StringType) fixed).getValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.StringType) fixed).getValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.UriType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.UriType) fixed).getValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.UriType) fixed).getValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.DateType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DateType) fixed).getValue().toString(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.DateType) fixed).getValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.DateTimeType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue().toString(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.OidType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.OidType) fixed).getValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.OidType) fixed).getValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.UuidType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.UuidType) fixed).getValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.UuidType) fixed).getValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.CodeType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.CodeType) fixed).getValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.CodeType) fixed).getValue()+'''');
			else if (fixed is org.hl7.fhir.instance.model.IdType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.IdType) fixed).getValue(), value), 'Value is '''+value+''' but must be '''+((org.hl7.fhir.instance.model.IdType) fixed).getValue()+'''');
			else if (fixed is Quantity)
				checkQuantity(errors, path, focus, (Quantity) fixed);
			else if (fixed is Address)
				checkAddress(errors, path, focus, (Address) fixed);
			else if (fixed is ContactPoint)
				checkContactPoint(errors, path, focus, (ContactPoint) fixed);
			else if (fixed is Attachment)
				checkAttachment(errors, path, focus, (Attachment) fixed);
			else if (fixed is Identifier)
				checkIdentifier(errors, path, focus, (Identifier) fixed);
			else if (fixed is Coding)
				checkCoding(errors, path, focus, (Coding) fixed);
			else if (fixed is HumanName)
				checkHumanName(errors, path, focus, (HumanName) fixed);
			else if (fixed is CodeableConcept)
				checkCodeableConcept(errors, path, focus, (CodeableConcept) fixed);
			else if (fixed is Timing)
				checkTiming(errors, path, focus, (Timing) fixed);
			else if (fixed is Period)
				checkPeriod(errors, path, focus, (Period) fixed);
			else if (fixed is Range)
				checkRange(errors, path, focus, (Range) fixed);
			else if (fixed is Ratio)
				checkRatio(errors, path, focus, (Ratio) fixed);
			else if (fixed is SampledData)
				checkSampledData(errors, path, focus, (SampledData) fixed);

			else
				 rule(errors, IssueType.EXCEPTION, focus.line(), focus.col(), path, false, 'Unhandled fixed value type '+fixed.getClass().getName());
			TAdvList<TWrapperElement> extensions := new ArrayTAdvList<TWrapperElement>();
			focus.getNamedChildren('extension', extensions);
			if (fixed.getExtension().size() = 0) begin
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, extensions.size() = 0, 'No extensions allowed');
			end else if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, extensions.size() = fixed.getExtension().size(), 'Extensions count mismatch: expected '+Integer.toString(fixed.getExtension().size())+' but found '+Integer.toString(extensions.size()))) begin
				for (Extension e : fixed.getExtension()) begin
				  TWrapperElement ex := getExtensionByUrl(extensions, e.getUrl());
					if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, ex !:= nil, 'Extension count mismatch: unable to find extension: '+e.getUrl())) begin
						checkFixedValue(errors, path, ex.getFirstChild().getNextSibling(), e.getValue(), 'extension.value');
					end
				end
			end
		end
  end

	private void checkAddress(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Address fixed) begin
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.getTextElement(), 'text');
	  checkFixedValue(errors, path+'.city', focus.getNamedChild('city'), fixed.getCityElement(), 'city');
	  checkFixedValue(errors, path+'.state', focus.getNamedChild('state'), fixed.getStateElement(), 'state');
	  checkFixedValue(errors, path+'.country', focus.getNamedChild('country'), fixed.getCountryElement(), 'country');
	  checkFixedValue(errors, path+'.zip', focus.getNamedChild('zip'), fixed.getPostalCodeElement(), 'postalCode');

		TAdvList<TWrapperElement> lines := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( 'line', lines);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, lines.size() = fixed.getLine().size(), 'Expected '+Integer.toString(fixed.getLine().size())+' but found '+Integer.toString(lines.size())+' line elements')) begin
			for (Integer i := 0; i < lines.size(); i++)
				checkFixedValue(errors, path+'.coding', lines.get(i), fixed.getLine().get(i), 'coding');
		end
  end

	private void checkContactPoint(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ContactPoint fixed) begin
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.getValueElement(), 'value');
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriod(), 'period');

  end

	private void checkAttachment(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Attachment fixed) begin
	  checkFixedValue(errors, path+'.contentType', focus.getNamedChild('contentType'), fixed.getContentTypeElement(), 'contentType');
	  checkFixedValue(errors, path+'.language', focus.getNamedChild('language'), fixed.getLanguageElement(), 'language');
	  checkFixedValue(errors, path+'.data', focus.getNamedChild('data'), fixed.getDataElement(), 'data');
	  checkFixedValue(errors, path+'.url', focus.getNamedChild('url'), fixed.getUrlElement(), 'url');
	  checkFixedValue(errors, path+'.size', focus.getNamedChild('size'), fixed.getSizeElement(), 'size');
	  checkFixedValue(errors, path+'.hash', focus.getNamedChild('hash'), fixed.getHashElement(), 'hash');
	  checkFixedValue(errors, path+'.title', focus.getNamedChild('title'), fixed.getTitleElement(), 'title');
  end

	private void checkIdentifier(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Identifier fixed) begin
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.label', focus.getNamedChild('type'), fixed.getType(), 'type');
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.getValueElement(), 'value');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriod(), 'period');
	  checkFixedValue(errors, path+'.assigner', focus.getNamedChild('assigner'), fixed.getAssigner(), 'assigner');
  end

	private void checkCoding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Coding fixed) begin
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.code', focus.getNamedChild('code'), fixed.getCodeElement(), 'code');
	  checkFixedValue(errors, path+'.display', focus.getNamedChild('display'), fixed.getDisplayElement(), 'display');
	  checkFixedValue(errors, path+'.userSelected', focus.getNamedChild('userSelected'), fixed.getUserSelectedElement(), 'userSelected');
  end

	private void checkHumanName(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, HumanName fixed) begin
	  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.getUseElement(), 'use');
	  checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.getTextElement(), 'text');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriod(), 'period');

		TAdvList<TWrapperElement> parts := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( 'family', parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), 'Expected '+Integer.toString(fixed.getFamily().size())+' but found '+Integer.toString(parts.size())+' family elements')) begin
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+'.family', parts.get(i), fixed.getFamily().get(i), 'family');
		end
		focus.getNamedChildren( 'given', parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), 'Expected '+Integer.toString(fixed.getFamily().size())+' but found '+Integer.toString(parts.size())+' given elements')) begin
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+'.given', parts.get(i), fixed.getFamily().get(i), 'given');
		end
		focus.getNamedChildren( 'prefix', parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), 'Expected '+Integer.toString(fixed.getFamily().size())+' but found '+Integer.toString(parts.size())+' prefix elements')) begin
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+'.prefix', parts.get(i), fixed.getFamily().get(i), 'prefix');
		end
		focus.getNamedChildren( 'suffix', parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), 'Expected '+Integer.toString(fixed.getFamily().size())+' but found '+Integer.toString(parts.size())+' suffix elements')) begin
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+'.suffix', parts.get(i), fixed.getFamily().get(i), 'suffix');
		end
  end

	private void checkCodeableConcept(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, CodeableConcept fixed) begin
		checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.getTextElement(), 'text');
		TAdvList<TWrapperElement> codings := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( 'coding', codings);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, codings.size() = fixed.getCoding().size(), 'Expected '+Integer.toString(fixed.getCoding().size())+' but found '+Integer.toString(codings.size())+' coding elements')) begin
			for (Integer i := 0; i < codings.size(); i++)
				checkFixedValue(errors, path+'.coding', codings.get(i), fixed.getCoding().get(i), 'coding');
		end
  end

	private void checkTiming(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Timing fixed) begin
	  checkFixedValue(errors, path+'.repeat', focus.getNamedChild('repeat'), fixed.getRepeat(), 'value');

		TAdvList<TWrapperElement> events := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( 'event', events);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, events.size() = fixed.getEvent().size(), 'Expected '+Integer.toString(fixed.getEvent().size())+' but found '+Integer.toString(events.size())+' event elements')) begin
			for (Integer i := 0; i < events.size(); i++)
				checkFixedValue(errors, path+'.event', events.get(i), fixed.getEvent().get(i), 'event');
		end
  end

	private void checkPeriod(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Period fixed) begin
	  checkFixedValue(errors, path+'.start', focus.getNamedChild('start'), fixed.getStartElement(), 'start');
	  checkFixedValue(errors, path+'.end', focus.getNamedChild('end'), fixed.getEndElement(), 'end');
  end

	private void checkRange(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Range fixed) begin
	  checkFixedValue(errors, path+'.low', focus.getNamedChild('low'), fixed.getLow(), 'low');
	  checkFixedValue(errors, path+'.high', focus.getNamedChild('high'), fixed.getHigh(), 'high');

  end

	private void checkRatio(errors : TAdvList<ValidationMessage>; String path,  TWrapperElement focus, Ratio fixed) begin
	  checkFixedValue(errors, path+'.numerator', focus.getNamedChild('numerator'), fixed.getNumerator(), 'numerator');
	  checkFixedValue(errors, path+'.denominator', focus.getNamedChild('denominator'), fixed.getDenominator(), 'denominator');
  end

	private void checkSampledData(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, SampledData fixed) begin
	  checkFixedValue(errors, path+'.origin', focus.getNamedChild('origin'), fixed.getOrigin(), 'origin');
	  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.getPeriodElement(), 'period');
	  checkFixedValue(errors, path+'.factor', focus.getNamedChild('factor'), fixed.getFactorElement(), 'factor');
	  checkFixedValue(errors, path+'.lowerLimit', focus.getNamedChild('lowerLimit'), fixed.getLowerLimitElement(), 'lowerLimit');
	  checkFixedValue(errors, path+'.upperLimit', focus.getNamedChild('upperLimit'), fixed.getUpperLimitElement(), 'upperLimit');
	  checkFixedValue(errors, path+'.dimensions', focus.getNamedChild('dimensions'), fixed.getDimensionsElement(), 'dimensions');
	  checkFixedValue(errors, path+'.data', focus.getNamedChild('data'), fixed.getDataElement(), 'data');
  end

	private void checkQuantity(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Quantity fixed) begin
	  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.getValueElement(), 'value');
	  checkFixedValue(errors, path+'.comparator', focus.getNamedChild('comparator'), fixed.getComparatorElement(), 'comparator');
	  checkFixedValue(errors, path+'.units', focus.getNamedChild('unit'), fixed.getUnitElement(), 'units');
	  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.getSystemElement(), 'system');
	  checkFixedValue(errors, path+'.code', focus.getNamedChild('code'), fixed.getCodeElement(), 'code');
  end

	private boolean check(String v1, String v2) begin
	  return v1 = nil ? Utilities.noString(v1) : v1.equals(v2);
  end

	private TWrapperElement getExtensionByUrl(TAdvList<TWrapperElement> extensions, String urlSimple) begin
	  for (TWrapperElement e : extensions) begin
	  	if (urlSimple.equals(e.getNamedChildValue('url')))
	  		return e;
	  end
		return nil;
  end




end
 *)

end.


