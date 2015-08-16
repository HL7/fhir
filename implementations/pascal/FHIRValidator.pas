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

Uses
  IdSoapMsXml,
  AdvObjects, AdvGenerics, AdvJSON,
  FHIRResources, FHIRTypes;

Type
  TValidationMessage = class (TAdvObject);

  TWrapperElement = class (TAdvObject)
  public
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

end;

function TDOMWrapperElement.getFirstChild: TWrapperElement;
begin

end;

function TDOMWrapperElement.getName: String;
begin

end;

function TDOMWrapperElement.getNamedChild(name: String): TWrapperElement;
begin

end;

procedure TDOMWrapperElement.getNamedChildren(name: String;
  list: TAdvList<TWrapperElement>);
begin

end;

procedure TDOMWrapperElement.getNamedChildrenWithWildcard(name: String;
  list: TAdvList<TWrapperElement>);
begin

end;

function TDOMWrapperElement.getNamedChildValue(name: String): String;
begin

end;

function TDOMWrapperElement.getNamespace: String;
begin

end;

function TDOMWrapperElement.getNextSibling: TWrapperElement;
begin

end;

function TDOMWrapperElement.getResourceType: String;
begin

end;

function TDOMWrapperElement.getText: String;
begin

end;

function TDOMWrapperElement.hasAttribute(name: String): boolean;
begin

end;

function TDOMWrapperElement.hasNamespace(s: String): boolean;
begin

end;

function TDOMWrapperElement.hasProcessingInstruction: boolean;
begin

end;

function TDOMWrapperElement.isXml: boolean;
begin

end;

function TDOMWrapperElement.line: Integer;
begin

end;


(*
    public DOMWrapperElement(element : IXmlDomElement) {
      super();
      self.IXmlDomElement := IXmlDomElement;
      XmlLocationData loc := (XmlLocationData) IXmlDomElement.getUserData(XmlLocationData.LOCATION_DATA_KEY );
      if (loc !:= nil) {
      	line := loc.getStartLine();
      	col := loc.getStartColumn();
      } else {
      	line := -1;
      	col := -1;
      }
    }

    @Override
    public TWrapperElement getNamedChild(name : String) {
      IXmlDomElement res := XMLUtil.getNamedChild(IXmlDomElement, name);
      return res = nil ? nil : new DOMWrapperElement(res);
    }

    @Override
    public TWrapperElement getFirstChild() {
      IXmlDomElement res := XMLUtil.getFirstChild(IXmlDomElement);
      return res = nil ? nil : new DOMWrapperElement(res);
    }

    @Override
    public TWrapperElement getNextSibling() {
      IXmlDomElement res := XMLUtil.getNextSibling(IXmlDomElement);
      return res = nil ? nil : new DOMWrapperElement(res);
    }

    @Override
    public String getName() {
      return IXmlDomElement.getLocalName();
    }

    @Override
    public String getNamedChildValue(name : String) {
      return XMLUtil.getNamedChildValue(IXmlDomElement, name);
    }

    @Override
    public void getNamedChildren(name : String, list : TAdvList<TWrapperElement>) {
      TAdvList<IXmlDomElement> el := new ArrayTAdvList<IXmlDomElement>();
      XMLUtil.getNamedChildren(IXmlDomElement, name, el);
      for (IXmlDomElement e : el)
        list.add(new DOMWrapperElement(e));
    }

    @Override
    public String getAttribute(name : String) {
      return IXmlDomElement.getAttribute(name);
    }

    @Override
    public void getNamedChildrenWithWildcard(name : String, list : TAdvList<TWrapperElement>) {
      TAdvList<IXmlDomElement> el := new ArrayTAdvList<IXmlDomElement>();
      XMLUtil.getNamedChildrenWithWildcard(IXmlDomElement, name, el);
      for (IXmlDomElement e : el)
        list.add(new DOMWrapperElement(e));
    }

    @Override
    public boolean hasAttribute(name : String) {
      return IXmlDomElement.hasAttribute(name);
    }

    @Override
    public String getNamespace() {
      return IXmlDomElement.getNamespaceURI();
    }

    @Override
    public boolean isXml() {
      return true;
    }

    @Override
    public String getText() {
      return IXmlDomElement.getTextContent();
    }

		@Override
    public boolean hasNamespace(String ns) {
	    for (Integer i := 0; i < IXmlDomElement.getAttributes().getLength(); i++) {
	    	Node a := IXmlDomElement.getAttributes().item(i);
	    	if ((a.getNodeName().equals("xmlns") || a.getNodeName().startsWith("xmlns:")) && a.getNodeValue().equals(ns))
	    		return true;
	    }
	    return false;
    }

		@Override
    public boolean hasProcessingInstruction() {
		  Node node := IXmlDomElement.getFirstChild();
		  while (node !:= nil) {
		  	if (node.getNodeType() = Node.PROCESSING_INSTRUCTION_NODE)
		  		return true;
		  	node := node.getNextSibling();
		  }
	    return false;
    }

		@Override
    public String getResourceType() {
      return IXmlDomElement.getLocalName();
    }

		@Override
    public Integer line() {
	    return line;
    }

function TDOMWrapperElement.col: Integer;
begin

end;

		@Override
    public Integer col() {
	    return col;
    }

  }

  TWrapperElement = class (TAdvObject)
  public
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

  public class JsonWrapperElement extends TWrapperElement {

  	private String path;
    private JsonElement IXmlDomElement;
    private JsonElement _element;
    private name : String;
    private String resourceType;
		private JsonWrapperElement parent;
		private Integer index;
		private TAdvList<JsonWrapperElement> children := new ArrayTAdvList<JsonWrapperElement>();

    public JsonWrapperElement(String path, name : String, JsonElement IXmlDomElement, JsonElement _element, JsonWrapperElement parent, Integer index) {
      super();
      self.path := path+"/"+name;
      self.name := name;
      self.IXmlDomElement := IXmlDomElement;
      if (IXmlDomElement instanceof TJsonObject && ((TJsonObject) IXmlDomElement).has("resourceType"))
      	self.resourceType := ((TJsonObject) IXmlDomElement).get("resourceType").getAsString();
      self._element := _element;
      self.parent := parent;
      self.index := index;
      createChildren();
    }

    public JsonWrapperElement(JsonElement IXmlDomElement) {
      super();
      self.name := nil;
      self.resourceType := ((TJsonObject) IXmlDomElement).get("resourceType").getAsString();
      self.IXmlDomElement := IXmlDomElement;
      self.path :="";
      createChildren();
    }

    private void createChildren() {
//    	System.out.println("  ..: "+path);
    	// we're going to make this look like the XML
    	if (IXmlDomElement = nil)
    		throw new Error("not done yet");

    	if (IXmlDomElement instanceof JsonPrimitive) {
    		// we may have an element_ too
    		if (_element !:= nil && _element instanceof TJsonObject)
    		  for (Entry<String, JsonElement> t : ((TJsonObject) _element).entrySet())
    				processChild(t.getKey(), t.getValue());
    	} else if (IXmlDomElement instanceof TJsonObject) {
    		for (Entry<String, JsonElement> t : ((TJsonObject) IXmlDomElement).entrySet())
    			if (!t.getKey().equals("resourceType")) {
    				processChild(t.getKey(), t.getValue());
    			}
      } else if (IXmlDomElement instanceof JsonNull) {
        // nothing to do
    	} else
    		throw new Error("unexpected condition: "+IXmlDomElement.getClass().getName());
    }

		private void processChild(name : String, JsonElement e) throws Error {
			if (name.startsWith("_")) {
				name := name.substring(1);
				if (((TJsonObject) IXmlDomElement).has(name))
  				return; // it will get processed anyway
				e := nil;
			}
			JsonElement _e := IXmlDomElement instanceof TJsonObject ? ((TJsonObject) IXmlDomElement).get("_"+name) : nil;

			if (e instanceof JsonPrimitive || (e = nil && _e !:= nil && !(_e instanceof JsonArray))) {
  			children.add(new JsonWrapperElement(path, name, e, _e, this, children.size()));
			} else if (e instanceof JsonArray || (e = nil && _e !:= nil)) {
				JsonArray array := (JsonArray) e;
				JsonArray _array := (JsonArray) _e;
				Integer max := array !:= nil ? array.size() : 0;
				if (_array !:= nil && _array.size() > max)
					max := _array.size();
				for (Integer i := 0; i < max; i++) {
					JsonElement a := array = nil || array.size() < i ? nil : array.get(i);
					JsonElement _a := _array = nil || _array.size() < i ? nil : _array.get(i);
  				children.add(new JsonWrapperElement(path, name, a, _a, this, children.size()));
				}
			} else if (e instanceof TJsonObject) {
				children.add(new JsonWrapperElement(path, name, e, nil, this, children.size()));
			} else
				throw new Error("not done yet: "+e.getClass().getName());
    }

    @Override
    public TWrapperElement getNamedChild(name : String) {
			for (JsonWrapperElement j : children)
				if (j.name.equals(name))
					return j;
			return nil;
    }

    @Override
    public TWrapperElement getFirstChild() {
    	if (children.isEmpty())
    		return nil;
    	else
      	return children.get(0);
    }

    @Override
    public TWrapperElement getNextSibling() {
    	if (parent = nil)
    		return nil;
    	if (index >:= parent.children.size() - 1)
    		return nil;
    	return  parent.children.get(index+1);
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getNamedChildValue(name : String) {
  		TWrapperElement c := getNamedChild(name);
      return c = nil ? nil : c.getAttribute("value");
    }

    @Override
    public void getNamedChildren(name : String, list : TAdvList<TWrapperElement>) {
      for (JsonWrapperElement j : children)
        if (j.name.equals(name))
          list.add(j);
    }

    @Override
    public String getAttribute(name : String) {
    	if (name.equals("value")) {
    		if (IXmlDomElement = nil)
    			return nil;
    		if (IXmlDomElement instanceof JsonPrimitive)
    			return ((JsonPrimitive) IXmlDomElement).getAsString();
        return nil;
    	}
    	if (name.equals("xml:id")) {
    		TWrapperElement c := getNamedChild("id");
        return c = nil ? nil : c.getAttribute("value");
    	}
      if (name.equals("url")) {
        TWrapperElement c := getNamedChild("url");
        return c = nil ? nil : c.getAttribute("value");
      }
      throw new Error("not done yet: "+name);
    }

    @Override
    public void getNamedChildrenWithWildcard(name : String, list : TAdvList<TWrapperElement>) {
      throw new Error("not done yet");
    }

    @Override
    public boolean hasAttribute(name : String) {
    	if (name.equals("value")) {
    		if (IXmlDomElement = nil)
    			return false;
    		if (IXmlDomElement instanceof JsonPrimitive)
    			return true;
        return false;
    	}
    	if (name.equals("xml:id")) {
    		return getNamedChild("id") !:= nil;
    	}
      throw new Error("not done yet: "+name);
   }

    @Override
    public String getNamespace() {
//      return IXmlDomElement.getNamespaceURI();
      throw new Error("not done yet");
   }

    @Override
    public boolean isXml() {
      return false;
    }

    @Override
    public String getText() {
      throw new Error("not done yet");
    }

    @Override
    public boolean hasNamespace(String ns) {
      throw new Error("not done");
    }

    @Override
    public boolean hasProcessingInstruction() {
      return false;
    }

		@Override
    public String getResourceType() {
	    return resourceType;
    }

		@Override
    public Integer line() {
	    return -1;
    }

		@Override
    public Integer col() {
	    // TODO Auto-generated method stub
	    return -1;
    }

  }




{ TFHIRInstanceValidator }
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
  public void validate(errors : TAdvList<ValidationMessage>; element : IXmlDomElement) throws Exception {
  }
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; obj : TJsonObject) throws Exception {
    validateResource(errors, new JsonWrapperElement(object), nil, requiresResourceId, nil);
  }
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; element : IXmlDomElement; profile : String) throws Exception {
    TFHIRStructureDefinition p := context.getProfiles().get(profile);
    if (p = nil)
      throw new Exception("TFHIRStructureDefinition '"+profile+"' not found");
    validateResource(errors, new DOMWrapperElement(IXmlDomElement), p, requiresResourceId, nil);
  }
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; element : IXmlDomElement; profile : TFHIRStructureDefinition) throws Exception {
    validateResource(errors, new DOMWrapperElement(IXmlDomElement), profile, requiresResourceId, nil);
  }

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; obj : TJsonObject; profile : TFHIRStructureDefinition) throws Exception {
    validateResource(errors, new JsonWrapperElement(object), profile, requiresResourceId, nil);
  }

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; obj : TJsonObject; profile : String) throws Exception {
    TFHIRStructureDefinition p := context.getProfiles().get(profile);
    if (p = nil)
      throw new Exception("TFHIRStructureDefinition '"+profile+"' not found");
    validateResource(errors, new JsonWrapperElement(object), p, requiresResourceId, nil);
  }

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2) throws Exception {
  	checkForProcessingInstruction(errors, document);
    validateResource(errors, new DOMWrapperElement(document.getDocumentElement()), nil, requiresResourceId, nil);
  }
  @Override
  public void validate(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2; profile : String) throws Exception {
  	checkForProcessingInstruction(errors, document);
    TFHIRStructureDefinition p := context.getProfiles().get(profile);
    if (p = nil)
      throw new Exception("TFHIRStructureDefinition '"+profile+"' not found");
    validateResource(errors, new DOMWrapperElement(document.getDocumentElement()), p, requiresResourceId, nil);
  }

  @Override
  public void validate(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2; profile : TFHIRStructureDefinition) throws Exception {
  	checkForProcessingInstruction(errors, document);
    validateResource(errors, new DOMWrapperElement(document.getDocumentElement()), profile, requiresResourceId, nil);
  }


  // implementation

  private void checkForProcessingInstruction(errors : TAdvList<ValidationMessage>; document : IXmlDomDocument2) {
	  Node node := document.getFirstChild();
	  while (node !:= nil) {
	  	rule(errors, IssueType.INVALID, -1, -1, "(document)", node.getNodeType() !:= Node.PROCESSING_INSTRUCTION_NODE, "No processing instructions allowed in resources");
	  	node := node.getNextSibling();
	  }
  }


  public class ChildIterator {
    private TWrapperElement parent;
    private String basePath;
    private Integer lastCount;
    private TWrapperElement child;

    public ChildIterator(String path, TWrapperElement IXmlDomElement) {
      parent := IXmlDomElement;
      basePath := path;
    }

    public boolean next() {
      if (child = nil) {
        child := parent.getFirstChild();
        lastCount := 0;
      } else {
        String lastName := child.getName();
        child := child.getNextSibling();
        if (child !:= nil && child.getName().equals(lastName))
          lastCount++;
        else
          lastCount := 0;
      }
      return child !:= nil;
    }

    public name : String() {
      return child.getName();
    }

    public TWrapperElement IXmlDomElement() {
      return child;
    }

    public String path() {
      TWrapperElement n := child.getNextSibling();
      if (parent.isXml()) {
      String sfx := "";
      if (n !:= nil && n.getName().equals(child.getName())) {
        sfx := "["+Integer.toString(lastCount+1)+"]";
      }
      return basePath+"/f:"+name()+sfx;
      } else {
        String sfx := "";
      	if (n !:= nil && n.getName().equals(child.getName())) {
      		sfx := "/"+Integer.toString(lastCount+1);
      	}
      	return basePath+"/"+name()+sfx;
      }
    }

    public Integer count() {
      TWrapperElement n := child.getNextSibling();
      if (n !:= nil && n.getName().equals(child.getName())) {
        return lastCount+1;
      } else
        return -1;
    }
  }

  private class NodeStack {
  	private boolean xml;
    private NodeStack parent;
    private String literalPath; // xpath format
    private TAdvList<String> logicalPaths; // dotted format, various entry points
    private TWrapperElement IXmlDomElement;
    private ElementDefinition definition;
    private ElementDefinition type;
    private ElementDefinition extension;

    public NodeStack(boolean xml) {
	    self.xml := xml;
    }

    private NodeStack push(TWrapperElement IXmlDomElement, Integer count, ElementDefinition definition, ElementDefinition type) {
      NodeStack res := new NodeStack(IXmlDomElement.isXml());
      res.parent := this;
      res.IXmlDomElement := IXmlDomElement;
      res.definition := definition;
  	  if (IXmlDomElement.isXml()) {
        res.literalPath := getLiteralPath() + (IXmlDomElement.getNamespace().equals(FormatUtilities.XHTML_NS) ? "/h:" : "/f:")+IXmlDomElement.getName();
      if (count > -1)
        res.literalPath := res.literalPath + "["+Integer.toString(count)+"]";
  	  } else {
  	  	if (IXmlDomElement.getName() = nil)
  	  		res.literalPath := "";
  	  	else
        res.literalPath := getLiteralPath() + "/" +IXmlDomElement.getName();
        if (count > -1)
          res.literalPath := res.literalPath + "/"+Integer.toString(count);
  	  }
      res.logicalPaths := new ArrayTAdvList<String>();
      if (type !:= nil) {
        // type will be bull if we on a stitching point of a contained resource, or if....
        res.type := type;
        String t := tail(definition.getPath());
        for (String lp : getLogicalPaths()) {
          res.logicalPaths.add(lp+"."+t);
          if (t.endsWith("[x]"))
            res.logicalPaths.add(lp+"."+t.substring(0, t.length()-3)+type.getPath());
        }
        res.logicalPaths.add(type.getPath());
      } else if (definition !:= nil) {
        for (String lp : getLogicalPaths())
          res.logicalPaths.add(lp+"."+IXmlDomElement.getName());
      } else
        res.logicalPaths.addAll(getLogicalPaths());
//      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
//      for (String lp : res.logicalPaths)
//        b.append(lp);
//      System.out.println(res.literalPath+" : "+b.toString());
      return res;
    }

    private String getLiteralPath() {
      return literalPath = nil ? "" : literalPath;
    }
    private TAdvList<String> getLogicalPaths() {
      return logicalPaths = nil ? new ArrayTAdvList<String>() : logicalPaths;
    }

    private TWrapperElement getElement() {
      return IXmlDomElement;
    }

    private ElementDefinition getType() {
      return type;
    }

    private ElementDefinition getDefinition() {
      return definition;
    }

    private void setType(ElementDefinition type) {
      self.type := type;
    }

		public String addToLiteralPath(String... path) {
			StringBuilder b := new StringBuilder();
			b.append(getLiteralPath());
			if (xml) {
				for (String p : path) {
					if (p.startsWith(":")) {
						b.append("[");
						b.append(p.substring(1));
						b.append("]");
					} else {
						b.append("/f:");
						b.append(p);
					}
				}
			} else {
				for (String p : path) {
					b.append("/");
					if (p.startsWith(":")) {
						b.append(p.substring(1));
					} else {
						b.append(p);
					}
				}
			}
			return b.toString();
    }
  }

  private WorkerContext context;
  private ProfileUtilities utilities;
  private ValueSetExpansionCache cache;
  private boolean requiresResourceId;
	private TAdvList<String> extensionDomains := new ArrayTAdvList<String>();
	private boolean anyExtensionsAllowed;

  public InstanceValidator(WorkerContext context) throws Exception {
    super();
    self.context := context;
    source := Source.InstanceValidator;
    cache := new ValueSetExpansionCache(context, nil);
    utilities := new ProfileUtilities(context);
  }


  public InstanceValidator(WorkerContext context, ValueSetExpansionCache cache) throws Exception {
    super();
    self.context := context;
    source := Source.InstanceValidator;
    self.cache := cache;
    utilities := new ProfileUtilities(context);
  }


  public WorkerContext getContext() {
		return context;
	}
  /*
   * The actual base entry point
   */
  private void validateResource(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, boolean needsId, NodeStack stack) throws Exception {
    if (stack = nil)
      stack := new NodeStack(IXmlDomElement.isXml());

    // getting going - either we got a profile, or not.
    boolean ok := true;
    if (IXmlDomElement.isXml()) {
      ok := rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), "/", IXmlDomElement.getNamespace().equals(FormatUtilities.FHIR_NS), "Namespace mismatch - expected '"+FormatUtilities.FHIR_NS+"', found '"+IXmlDomElement.getNamespace()+"'");
    }
    if (ok) {
        String resourceName := IXmlDomElement.getResourceType();
      if (profile = nil) {
        profile := context.getProfiles().get("http://hl7.org/fhir/TFHIRStructureDefinition/"+resourceName);
          ok := rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.addToLiteralPath(resourceName), profile !:= nil, "No profile found for resource type '"+resourceName+"'");
      } else {
        String type := profile.hasConstrainedType() ? profile.getConstrainedType() : profile.getName();
          ok := rule(errors, IssueType.INVALID, -1, -1, stack.addToLiteralPath(resourceName), type.equals(resourceName), "Specified profile type was '"+profile.getConstrainedType()+"', but resource type was '"+resourceName+"'");
      }
    }

    if (ok) {
      stack := stack.push(IXmlDomElement, -1, profile.getSnapshot().getElement().get(0), profile.getSnapshot().getElement().get(0));
      if (needsId && (IXmlDomElement.getNamedChild("id") = nil))
        rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), false, "Resource has no id");
      start(errors, IXmlDomElement, profile, stack); // root is both definition and type
    }
  }


  // we assume that the following things are true:
  // the instance at root is valid against the schema and schematron
  // the instance validator had no issues against the base resource profile
  private void start(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, NodeStack stack) throws Exception {
    // profile is valid, and matches the resource name
    if (rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), profile.hasSnapshot(), "TFHIRStructureDefinition has no snapshot - validation is against the snapshot, so it must be provided")) {
      validateElement(errors, profile, profile.getSnapshot().getElement().get(0), nil, nil, IXmlDomElement, IXmlDomElement.getName(), stack);

      checkDeclaredProfiles(errors, IXmlDomElement, stack);

      // specific known special validations
      if (IXmlDomElement.getResourceType().equals("Bundle"))
        validateBundle(errors, IXmlDomElement, stack);
      if (IXmlDomElement.getResourceType().equals("Observation"))
        validateObservation(errors, IXmlDomElement, stack);
    }
  }

//	private String findProfileTag(TWrapperElement IXmlDomElement) {
//  	String uri := nil;
//	  list : TAdvList<TWrapperElement> := new ArrayTAdvList<TWrapperElement>();
//	  IXmlDomElement.getNamedChildren("category", list);
//	  for (TWrapperElement c : list) {
//	  	if ("http://hl7.org/fhir/tag/profile".equals(c.getAttribute("scheme"))) {
//	  		uri := c.getAttribute("term");
//	  	}
//	  }
//	  return uri;
//  }


  private void checkDeclaredProfiles(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, NodeStack stack) throws Exception {
    TWrapperElement meta := IXmlDomElement.getNamedChild("meta");
    if (meta !:= nil) {
      TAdvList<TWrapperElement> profiles := new ArrayTAdvList<InstanceValidator.TWrapperElement>();
      meta.getNamedChildren("profile", profiles);
      Integer i := 0;
      for (TWrapperElement profile : profiles) {
        String ref := profile.getAttribute("value");
        String p := stack.addToLiteralPath("meta", "profile", ":"+Integer.toString(i));
        if (rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), p, !Utilities.noString(ref), "TFHIRStructureDefinition reference invalid")) {
          TFHIRStructureDefinition pr := context.getProfiles().get(ref);
          if (warning(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), p, pr !:= nil, "TFHIRStructureDefinition reference could not be resolved")) {
            if (rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), p, pr.hasSnapshot(), "TFHIRStructureDefinition has no snapshot - validation is against the snapshot, so it must be provided")) {
              validateElement(errors, pr, pr.getSnapshot().getElement().get(0), nil, nil, IXmlDomElement, IXmlDomElement.getName(), stack);
            }
          }
          i++;
        }
      }
    }
  }

  private void validateBundle(errors : TAdvList<ValidationMessage>; TWrapperElement bundle, NodeStack stack) {
    TAdvList<TWrapperElement> entries := new ArrayTAdvList<TWrapperElement>();
    bundle.getNamedChildren("entry", entries);
    String type := bundle.getNamedChildValue("type");
    if (entries.size() = 0) {
      rule(errors, IssueType.INVALID, stack.getLiteralPath(), !(type.equals("document") || type.equals("message")), "Documents or Messages must contain at least one entry");
    } else {
      TWrapperElement firstEntry := entries.get(0);
      NodeStack firstStack := stack.push(firstEntry, 0, nil, nil);
      String fullUrl := firstEntry.getNamedChildValue("fullUrl");

      if (type.equals("document")) {
        TWrapperElement res := firstEntry.getNamedChild("resource");
        NodeStack localStack := firstStack.push(res, -1, nil, nil);
        TWrapperElement resource := res.getFirstChild();
        String id := resource.getNamedChildValue("id");
        if (rule(errors, IssueType.INVALID, firstEntry.line(), firstEntry.col(), stack.addToLiteralPath("entry", ":0"), res !:= nil, "No resource on first entry")) {
          if (bundle.isXml())
            validateDocument(errors, entries, resource, localStack.push(resource, -1, nil, nil), fullUrl, id);
          else
            validateDocument(errors, entries, res, localStack, fullUrl, id);
        }
      }
      if (type.equals("message"))
        validateMessage(errors, bundle);
    }
  }

  private void validateMessage(errors : TAdvList<ValidationMessage>; TWrapperElement bundle) {
    // TODO Auto-generated method stub

  }


  private void validateDocument(errors : TAdvList<ValidationMessage>; TAdvList<TWrapperElement> entries, TWrapperElement composition, NodeStack stack, String fullUrl, String id) {
    // first entry must be a composition
    if (rule(errors, IssueType.INVALID, composition.line(), composition.col(), stack.getLiteralPath(), composition.getResourceType().equals("Composition"), "The first entry in a document must be a composition")) {
      // the composition subject and section references must resolve in the bundle
      validateBundleReference(errors, entries, composition.getNamedChild("subject"), "Composition Subject", stack.push(composition.getNamedChild("subject"), -1, nil, nil), fullUrl, "Composition", id);
      validateSections(errors, entries, composition, stack, fullUrl, id);
    }
  }
//rule(errors, IssueType.INVALID, bundle.line(), bundle.col(), "Bundle", !"urn:guid:".equals(base), "The base 'urn:guid:' is not valid (use urn:uuid:)");
//rule(errors, IssueType.INVALID, entry.line(), entry.col(), localStack.getLiteralPath(), !"urn:guid:".equals(ebase), "The base 'urn:guid:' is not valid");
//rule(errors, IssueType.INVALID, entry.line(), entry.col(), localStack.getLiteralPath(), !Utilities.noString(base) || !Utilities.noString(ebase), "entry does not have a base");
//String firstBase := nil;
//firstBase := ebase = nil ? base : ebase;

  private void validateSections(errors : TAdvList<ValidationMessage>; TAdvList<TWrapperElement> entries, TWrapperElement focus, NodeStack stack, String fullUrl, String id) {
    TAdvList<TWrapperElement> sections := new ArrayTAdvList<TWrapperElement>();
    focus.getNamedChildren("entry", sections);
    Integer i := 0;
    for (TWrapperElement section : sections) {
      NodeStack localStack := stack.push(section,  1, nil, nil);
			validateBundleReference(errors, entries, section.getNamedChild("content"), "Section Content", localStack, fullUrl, "Composition", id);
      validateSections(errors, entries, section, localStack, fullUrl, id);
      i++;
    }
  }

  private void validateBundleReference(errors : TAdvList<ValidationMessage>; TAdvList<TWrapperElement> entries, TWrapperElement ref, name : String, NodeStack stack, String fullUrl, String type, String id) {
    if (ref !:= nil && !Utilities.noString(ref.getNamedChildValue("reference"))) {
      TWrapperElement target := resolveInBundle(entries, ref.getNamedChildValue("reference"), fullUrl, type, id);
      rule(errors, IssueType.INVALID, target.line(), target.col(), stack.addToLiteralPath("reference"), target !:= nil, "Unable to resolve the target of the reference in the bundle ("+name+")");
    }
  }

  private TWrapperElement resolveInBundle(TAdvList<TWrapperElement> entries, String ref, String fullUrl, String type, String id) {
    if (Utilities.isAbsoluteUrl(ref)) {
      // if the reference is absolute, then you resolve by fullUrl. No other thinking is required.
      for (TWrapperElement entry : entries) {
        String fu := entry.getNamedChildValue("fullUrl");
        if (ref.equals(fu))
          return entry;
      }
      return nil;
    } else {
      // split into base, type, and id
      String u := nil;
      if (fullUrl !:= nil && fullUrl.endsWith(type+"/"+id))
        // fullUrl := complex
        u := fullUrl.substring((type+"/"+id).length())+ref;
      String[] parts := ref.split("\\/");
      if (parts.length >:= 2) {
        String t := parts[0];
        String i := parts[1];
        for (TWrapperElement entry : entries) {
          String fu := entry.getNamedChildValue("fullUrl");
          if (u !:= nil && fullUrl.equals(u))
            return entry;
          if (u = nil) {
            TWrapperElement res := entry.getNamedChild("resource");
            TWrapperElement resource := res.getFirstChild();
            String et := resource.getResourceType();
            String eid := resource.getNamedChildValue("id");
            if (t.equals(et) && i.equals(eid))
              return entry;
          }
        }
      }
      return nil;
    }
  }

  private TFHIRStructureDefinition getProfileForType(String type) throws Exception {
    return context.getProfiles().get("http://hl7.org/fhir/TFHIRStructureDefinition/"+type);
  }

  private void validateObservation(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, NodeStack stack) {
    // all observations should have a subject, a performer, and a time

    bpCheck(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), IXmlDomElement.getNamedChild("subject") !:= nil, "All observations should have a subject");
    bpCheck(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), IXmlDomElement.getNamedChild("performer") !:= nil, "All observations should have a performer");
    bpCheck(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), IXmlDomElement.getNamedChild("effectiveDateTime") !:= nil || IXmlDomElement.getNamedChild("effectivePeriod") !:= nil , "All observations should have an effectiveDateTime or an effectivePeriod");
  }

  private void bpCheck(errors : TAdvList<ValidationMessage>; IssueType invalid, Integer line, Integer col, String literalPath, boolean test, String message) {
  	if (bpWarnings !:= nil) {
    switch (bpWarnings) {
    case Error: rule(errors, invalid, line, col, literalPath, test, message);
    case Warning: warning(errors, invalid, line, col, literalPath, test, message);
    case Hint: hint(errors, invalid, line, col, literalPath, test, message);
    default: // do nothing
    }
  }
  }

  private void validateElement(errors : TAdvList<ValidationMessage>; TFHIRStructureDefinition profile, ElementDefinition definition, TFHIRStructureDefinition cprofile, ElementDefinition context, TWrapperElement IXmlDomElement, String actualType, NodeStack stack) throws Exception {
    // irrespective of what IXmlDomElement it is, it cannot be empty
  	if (IXmlDomElement.isXml()) {
      rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), FormatUtilities.FHIR_NS.equals(IXmlDomElement.getNamespace()), "Namespace mismatch - expected '"+FormatUtilities.FHIR_NS+"', found '"+IXmlDomElement.getNamespace()+"'");
      rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), !IXmlDomElement.hasNamespace("http://www.w3.org/2001/XMLSchema-instance"), "Schema Instance Namespace is not allowed in instances");
      rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), !IXmlDomElement.hasProcessingInstruction(), "No Processing Instructions in resources");
  	}
    rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), !empty(IXmlDomElement), "Elements must have some content (@value, extensions, or children elements)");

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
    for (ElementDefinition ed : childDefinitions) {
    	boolean process := true;
    	// where are we with slicing
    	if (ed.hasSlicing()) {
    		if (slice !:= nil && slice.getPath().equals(ed.getPath()))
    			throw new Exception("Slice encountered midway through path on "+slice.getPath());
    		slice := ed;
    		process := false;
    	} else if (slice !:= nil && !slice.getPath().equals(ed.getPath()))
    		slice := nil;

    	if (process) {
    	for (ElementInfo ei : children) {
    			boolean match := false;
    		if (slice = nil) {
    			match := nameMatches(ei.name, tail(ed.getPath()));
    		} else {
    				if (nameMatches(ei.name, tail(ed.getPath())))
    					match := sliceMatches(ei.IXmlDomElement, ei.path, slice, ed, profile);
    		}
    		if (match) {
    				if (rule(errors, IssueType.INVALID, ei.line(), ei.col(), ei.path, ei.definition = nil, "IXmlDomElement matches more than one slice"))
    				ei.definition := ed;
    		}
    	}
    }
    	}
    for (ElementInfo ei : children)
      if (ei.path.endsWith(".extension"))
        rule(errors, IssueType.INVALID, ei.line(), ei.col(), ei.path, ei.definition !:= nil, "IXmlDomElement is unknown or does not match any slice (url:=\""+ei.IXmlDomElement.getAttribute("url")+"\")");
      else
        rule(errors, IssueType.INVALID, ei.line(), ei.col(), ei.path, ei.definition !:= nil, "IXmlDomElement is unknown or does not match any slice");

    // 3. report any definitions that have a cardinality problem
    for (ElementDefinition ed : childDefinitions) {
    	if (ed.getRepresentation().isEmpty()) { // ignore xml attributes
    	Integer count := 0;
      for (ElementInfo ei : children)
      	if (ei.definition = ed)
      		count++;
  		if (ed.getMin() > 0) {
  			rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), count >:= ed.getMin(), "IXmlDomElement '"+stack.getLiteralPath()+"."+tail(ed.getPath())+"': minimum required := "+Integer.toString(ed.getMin())+", but only found "+Integer.toString(count));
    		}
  		if (ed.hasMax() && !ed.getMax().equals("*")) {
  			rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), count <:= Integer.parseInt(ed.getMax()), "IXmlDomElement "+tail(ed.getPath())+" @ "+stack.getLiteralPath()+": max allowed := "+Integer.toString(ed.getMin())+", but found "+Integer.toString(count));
    		}

    	}
    }
    // 4. check order if any slices are orderd. (todo)

    // 5. inspect each child for validity
    for (ElementInfo ei : children) {
    	if (ei.definition !:= nil) {
      String type := nil;
      ElementDefinition typeDefn := nil;
    		if (ei.definition.getType().size() = 1 && !ei.definition.getType().get(0).getCode().equals("*") && !ei.definition.getType().get(0).getCode().equals("IXmlDomElement") && !ei.definition.getType().get(0).getCode().equals("BackboneElement") )
    			type := ei.definition.getType().get(0).getCode();
    		else if (ei.definition.getType().size() = 1 && ei.definition.getType().get(0).getCode().equals("*")) {
          String prefix := tail(ei.definition.getPath());
          assert prefix.endsWith("[x]");
          type := ei.name.substring(prefix.length()-3);
          if (isPrimitiveType(type))
            type := Utilities.uncapitalize(type);
    		} else if (ei.definition.getType().size() > 1) {

            String prefix := tail(ei.definition.getPath());
            assert prefix.endsWith("[x]");
            prefix := prefix.substring(0, prefix.length()-3);
            for (TypeRefComponent t : ei.definition.getType())
              if ((prefix+Utilities.capitalize(t.getCode())).equals(ei.name))
                type := t.getCode();
            if (type = nil) {
        			TypeRefComponent trc := ei.definition.getType().get(0);
        			if(trc.getCode().equals("Reference"))
        				type := "Reference";
              else
              	rule(errors, IssueType.STRUCTURE, ei.line(), ei.col(), stack.getLiteralPath(), false, "The IXmlDomElement "+ei.name+" is illegal. Valid types at this point are "+describeTypes(ei.definition.getType()));
          }
    		} else if (ei.definition.getNameReference() !:= nil) {
    			typeDefn := resolveNameReference(profile.getSnapshot(), ei.definition.getNameReference());
        }


        if (type !:= nil) {
          if (type.startsWith("@")) {
    				ei.definition := findElement(profile, type.substring(1));
            type := nil;
          }
        }
    		NodeStack localStack := stack.push(ei.IXmlDomElement, ei.count, ei.definition, type = nil ? typeDefn : resolveType(type));
    		assert(ei.path.equals(localStack.getLiteralPath()));

      if (type !:= nil) {
        if (typeIsPrimitive(type))
    				checkPrimitive(errors, ei.path, type, ei.definition, ei.IXmlDomElement);
        else {
          if (type.equals("Identifier"))
    					checkIdentifier(errors, ei.path, ei.IXmlDomElement, ei.definition);
          else if (type.equals("Coding"))
    					checkCoding(errors, ei.path, ei.IXmlDomElement, profile, ei.definition);
          else if (type.equals("CodeableConcept"))
    					checkCodeableConcept(errors, ei.path, ei.IXmlDomElement, profile, ei.definition);
          else if (type.equals("Reference"))
    					checkReference(errors, ei.path, ei.IXmlDomElement, profile, ei.definition, actualType, localStack);

          if (type.equals("Extension"))
            checkExtension(errors, ei.path, ei.IXmlDomElement, ei.definition, profile, localStack);
          else if (type.equals("Resource"))
    					validateContains(errors, ei.path, ei.definition, definition, ei.IXmlDomElement, localStack, !isBundleEntry(ei.path)); //    if (str.matches(".*([.,/])work\\1$"))
          else {
            TFHIRStructureDefinition p := getProfileForType(type);
            if (rule(errors, IssueType.STRUCTURE, ei.line(), ei.col(), ei.path, p !:= nil, "Unknown type "+type)) {
    						validateElement(errors, p, p.getSnapshot().getElement().get(0), profile, ei.definition, ei.IXmlDomElement, type, localStack);
            }
          }
        }
      } else {
    			if (rule(errors, IssueType.STRUCTURE, ei.line(), ei.col(), stack.getLiteralPath(), ei.definition !:= nil, "Unrecognised Content "+ei.name))
    				validateElement(errors, profile, ei.definition, nil, nil, ei.IXmlDomElement, type, localStack);
    		}
      }
    }
  }

  /**
   *
   * @param IXmlDomElement - the candidate that might be in the slice
   * @param path - for reporting any errors. the XPath for the IXmlDomElement
   * @param slice - the definition of how slicing is determined
   * @param ed - the slice for which to test membership
   * @return
   * @throws Exception
   */
  private boolean sliceMatches(TWrapperElement IXmlDomElement, String path, ElementDefinition slice, ElementDefinition ed; profile : TFHIRStructureDefinition) throws Exception {
  	if (!slice.getSlicing().hasDiscriminator())
  		return false; // cannot validate in this case
	  for (StringType s : slice.getSlicing().getDiscriminator()) {
	  	String discriminator := s.getValue();
	  	ElementDefinition criteria := getCriteriaForDiscriminator(path, ed, discriminator, profile);
	  	if (discriminator.equals("url") && criteria.getPath().equals("Extension.url")) {
	  		if (!IXmlDomElement.getAttribute("url").equals(((UriType) criteria.getFixed()).asStringValue()))
	  			return false;
	  	} else {
	  		IXmlDomElement value := getValueForDiscriminator(IXmlDomElement, discriminator, criteria);
	  		if (!valueMatchesCriteria(value, criteria))
	  			return false;
	  	}
	  }
	  return true;
  }

	private boolean valueMatchesCriteria(IXmlDomElement value, ElementDefinition criteria) {
		throw new Error("validation of slices not done yet");
  }

	private IXmlDomElement getValueForDiscriminator(TWrapperElement IXmlDomElement, String discriminator, ElementDefinition criteria) {
		throw new Error("validation of slices not done yet");
  }

	private ElementDefinition getCriteriaForDiscriminator(String path, ElementDefinition ed, String discriminator; profile : TFHIRStructureDefinition) throws Exception {
    TAdvList<ElementDefinition> childDefinitions := ProfileUtilities.getChildMap(profile, ed);
    TAdvList<ElementDefinition> snapshot := nil;
    if (childDefinitions.isEmpty()) {
    	// going to look at the type
    	if (ed.getType().size() = 0)
    		throw new Exception("Error in profile for "+path+" no children, no type");
    	if (ed.getType().size() > 1)
    		throw new Exception("Error in profile for "+path+" multiple types defined in slice discriminator");
    	TFHIRStructureDefinition type;
    	if (ed.getType().get(0).hasProfile())
    		type := context.getExtensionStructure(profile, ed.getType().get(0).getProfile().get(0).getValue());
    	else
    		type := context.getExtensionStructure(profile, "http://hl7.org/fhir/TFHIRStructureDefinition/"+ed.getType().get(0).getCode());
    	snapshot := type.getSnapshot().getElement();
    	ed := snapshot.get(0);
    } else {
      snapshot := profile.getSnapshot().getElement();
    }
		String originalPath := ed.getPath();
		String goal := originalPath+"."+discriminator;

		Integer index := snapshot.indexOf(ed);
		assert (index > -1);
		index++;
		while (index < snapshot.size() && !snapshot.get(index).getPath().equals(originalPath)) {
			if (snapshot.get(index).getPath().equals(goal))
				return snapshot.get(index);
			index++;
		}
		throw new Error("Unable to find discriminator definition for "+goal+" in "+discriminator+" at "+path);
  }

  private boolean isPrimitiveType(String type) {
    return
        type.equalsIgnoreCase("boolean") || type.equalsIgnoreCase("integer") || type.equalsIgnoreCase("string") || type.equalsIgnoreCase("decimal") ||
        type.equalsIgnoreCase("uri") || type.equalsIgnoreCase("base64Binary") || type.equalsIgnoreCase("instant") || type.equalsIgnoreCase("date") ||
        type.equalsIgnoreCase("dateTime") || type.equalsIgnoreCase("time") || type.equalsIgnoreCase("code") || type.equalsIgnoreCase("oid") || type.equalsIgnoreCase("id");
  }

  private boolean nameMatches(name : String, String tail) {
	  if (tail.endsWith("[x]"))
	    return name.startsWith(tail.substring(0,  tail.length()-3));
	  else
	    return (name.equals(tail));
  }

  private ElementDefinition resolveNameReference(StructureDefinitionSnapshotComponent snapshot, name : String) {
  	for (ElementDefinition ed : snapshot.getElement())
  		if (name.equals(ed.getName()))
  			return ed;
	  return nil;
  }

  private ElementDefinition resolveType(String type) {
    String url := "http://hl7.org/fhir/TFHIRStructureDefinition/"+type;
    TFHIRStructureDefinition sd := context.getProfiles().get(url);
    if (sd = nil || !sd.hasSnapshot())
      return nil;
    else
      return sd.getSnapshot().getElement().get(0);
  }

//  private String mergePath(String path1, String path2) {
//    // path1 is xpath path
//    // path2 is dotted path
//    String[] parts := path2.split("\\.");
//    StringBuilder b := new StringBuilder(path1);
//    for (Integer i := 1; i < parts.length -1; i++)
//      b.append("/f:"+parts[i]);
//    return b.toString();
//  }

  private boolean isBundleEntry(String path) {
    String[] parts := path.split("\\/");
    if (path.startsWith("/f:"))
      return parts.length > 2 && parts[parts.length-1].startsWith("f:resource") && (parts[parts.length-2].equals("f:entry") || parts[parts.length-2].startsWith("f:entry["));
    else
      return parts.length > 2 && parts[parts.length-1].equals("resource") && ((parts.length > 2 && parts[parts.length-3].equals("entry")) || parts[parts.length-2].equals("entry"));
  }

  private String describeTypes(TAdvList<TypeRefComponent> types) {
    CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
    for (TypeRefComponent t : types) {
      b.append(t.getCode());
    }
    return b.toString();
  }

  private void checkReference(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, ElementDefinition container, String parentType, NodeStack stack) throws Exception {
    String ref := IXmlDomElement.getNamedChildValue("reference");
    if (Utilities.noString(ref)) {
      // todo - what should we do in this case?
      hint(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, !Utilities.noString(IXmlDomElement.getNamedChildValue("display")), "A Reference without an actual reference should have a display");
      return;
    }

    TWrapperElement we := resolve(ref, stack);
    String ft;
    if (we !:= nil)
      ft := we.getResourceType();
    else
      ft := tryParse(ref);
    if (hint(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, ft !:= nil, "Unable to determine type of target resource")) {
      boolean ok := false;
      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
      for (TypeRefComponent type : container.getType()) {
        if (!ok && type.getCode().equals("Reference")) {
          // we validate as much as we can. First, can we infer a type from the profile?
          if (!type.hasProfile() || type.getProfile().get(0).getValue().equals("http://hl7.org/fhir/TFHIRStructureDefinition/Resource"))
            ok := true;
          else {
            String pr := type.getProfile().get(0).getValue();

            String bt := getBaseType(profile, pr);
            if (rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, bt !:= nil, "Unable to resolve the profile reference '"+pr+"'")) {
              b.append(bt);
              ok := bt.equals(ft);
            } else
              ok := true; // suppress following check
          }
        }
        if (!ok && type.getCode().equals("*")) {
          ok := true; // can refer to anything
        }
      }
      rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, ok, "Invalid Resource target type. Found "+ft+", but expected one of ("+b.toString()+")");
    }
  }

  private TWrapperElement resolve(String ref, NodeStack stack) {
    if (ref.startsWith("#")) {
      // work back through the contained list.
      // really, there should only be one level for this (contained resources cannot contain
      // contained resources), but we'll leave that to some other code to worry about
      while (stack !:= nil && stack.getElement() !:= nil) {
        TWrapperElement res := getContainedById(stack.getElement(), ref.substring(1));
        if (res !:= nil)
          return res;
        stack := stack.parent;
      }
      return nil;
    } else {
      // work back through the contained list - if any of them are bundles, try to resolve
      // the resource in the bundle
      while (stack !:= nil && stack.getElement() !:= nil) {
        if ("Bundle".equals(stack.getElement().getResourceType())) {
          TWrapperElement res := getFromBundle(stack.getElement(), ref.substring(1));
          if (res !:= nil)
            return res;
        }
        stack := stack.parent;
      }

      // todo: consult the external host for resolution
      return nil;

    }
  }

  private TWrapperElement getFromBundle(TWrapperElement bundle, String ref) {
    TAdvList<TWrapperElement> entries := new ArrayTAdvList<TWrapperElement>();
    bundle.getNamedChildren("entry", entries);
    for (TWrapperElement we : entries) {
      TWrapperElement res := we.getNamedChild("resource").getFirstChild();
      if (res !:= nil) {
        String url := genFullUrl(bundle.getNamedChildValue("base"), we.getNamedChildValue("base"), res.getName(), res.getNamedChildValue("id"));
        if (url.endsWith(ref))
          return res;
      }
    }
    return nil;
  }

  private String genFullUrl(String bundleBase, String entryBase, String type, String id) {
    String base := Utilities.noString(entryBase) ? bundleBase : entryBase;
    if (Utilities.noString(base)) {
      return type+"/"+id;
    } else if ("urn:uuid".equals(base) || "urn:oid".equals(base))
      return base+id;
    else
      return Utilities.appendSlash(base)+type+"/"+id;
  }

  private TWrapperElement getContainedById(TWrapperElement container, String id) {
    TAdvList<TWrapperElement> contained := new ArrayTAdvList<TWrapperElement>();
    container.getNamedChildren("contained", contained);
    for (TWrapperElement we : contained) {
    	TWrapperElement res := we.isXml() ? we.getFirstChild() : we;
      if (id.equals(res.getNamedChildValue("id")))
        return res;
    }
    return nil;
  }

  private String tryParse(String ref) {
    String[] parts := ref.split("\\/");
    switch (parts.length) {
    case 1:
      return nil;
    case 2:
      return checkResourceType(parts[0]);
    default:
      if (parts[parts.length-2].equals("_history"))
        return checkResourceType(parts[parts.length-4]);
      else
        return checkResourceType(parts[parts.length-2]);
    }
  }

  private String checkResourceType(String type) {
    if (context.getProfiles().containsKey("http://hl7.org/fhir/TFHIRStructureDefinition/"+type))
      return type;
    else
      return nil;
  }
  private String getBaseType(TFHIRStructureDefinition profile, String pr) {
//    if (pr.startsWith("http://hl7.org/fhir/TFHIRStructureDefinition/")) {
//      // this just has to be a base type
//      return pr.substring(40);
//    } else {
      TFHIRStructureDefinition p := resolveProfile(profile, pr);
      if (p = nil)
        return nil;
      else if (p.getKind() = StructureDefinitionKind.RESOURCE)
        return p.getSnapshot().getElement().get(0).getPath();
      else
        return p.getSnapshot().getElement().get(0).getType().get(0).getCode();
//    }
  }

  private TFHIRStructureDefinition resolveProfile(TFHIRStructureDefinition profile, String pr) {
    if (pr.startsWith("#")) {
      for (Resource r : profile.getContained()) {
        if (r.getId().equals(pr.substring(1)) && r instanceof TFHIRStructureDefinition)
          return (TFHIRStructureDefinition) r;
      }
      return nil;
    }
    else
      return context.getProfiles().get(pr);
  }

  private TFHIRStructureDefinition checkExtension(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement, ElementDefinition def; profile : TFHIRStructureDefinition, NodeStack stack) throws Exception {
    String url := IXmlDomElement.getAttribute("url");
    boolean isModifier := IXmlDomElement.getName().equals("modifierExtension");

    TFHIRStructureDefinition ex := context.getExtensionStructure(profile, url);
    if (ex = nil) {
      if (!rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, allowUnknownExtension(url), "The extension "+url+" is unknown, and not allowed here"))
        warning(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path, allowUnknownExtension(url), "Unknown extension "+url);
    } else {
      if (def.getIsModifier())
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+"[url:='"+url+"']", ex.getSnapshot().getElement().get(0).getIsModifier(), "Extension modifier mismatch: the extension IXmlDomElement is labelled as a modifier, but the underlying extension is not");
      else
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+"[url:='"+url+"']", !ex.getSnapshot().getElement().get(0).getIsModifier(), "Extension modifier mismatch: the extension IXmlDomElement is not labelled as a modifier, but the underlying extension is");

      // two questions
      // 1. can this extension be used here?
      checkExtensionContext(errors, IXmlDomElement, /*path+"[url:='"+url+"']",*/ ex, stack, ex.getUrl());

      if (isModifier)
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+"[url:='"+url+"']", ex.getSnapshot().getElement().get(0).getIsModifier(), "The Extension '"+url+"' must be used as a modifierExtension");
      else
        rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), path+"[url:='"+url+"']", !ex.getSnapshot().getElement().get(0).getIsModifier(), "The Extension '"+url+"' must not be used as an extension (it's a modifierExtension)");

      // 2. is the content of the extension valid?

    }
    return ex;
  }

  private boolean allowUnknownExtension(String url) {
    if (url.contains("example.org") || url.contains("acme.com") || url.contains("nema.org"))
    	return true;
    for (String s : extensionDomains)
    	if (url.startsWith(s))
    		return true;
    return anyExtensionsAllowed;
  }

  private boolean isKnownType(String code) {
    return context.getProfiles().get(code.toLowerCase()) !:= nil;
  }

  private ElementDefinition getElementByPath(TFHIRStructureDefinition definition, String path) {
    for (ElementDefinition e : definition.getSnapshot().getElement()) {
      if (e.getPath().equals(path))
        return e;
    }
    return nil;
  }

  private boolean checkExtensionContext(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, TFHIRStructureDefinition definition, NodeStack stack, String extensionParent) {
    String extUrl := definition.getUrl();
    CommaSeparatedStringBuilder p := new CommaSeparatedStringBuilder();
    for (String lp : stack.getLogicalPaths())
      p.append(lp);
	  if (definition.getContextType() = ExtensionContext.DATATYPE) {
	    boolean ok := false;
	    CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
	    for (StringType ct : definition.getContext()) {
	      b.append(ct.getValue());
	      if (ct.getValue().equals("*") || stack.getLogicalPaths().contains(ct.getValue()+".extension"))
	        ok := true;
	    }
	    return rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), ok, "The extension "+extUrl+" is not allowed to be used on the logical path set ["+p.toString()+"] (allowed: datatype:="+b.toString()+")");
	  } else if (definition.getContextType() = ExtensionContext.EXTENSION) {
      boolean ok := false;
      for (StringType ct : definition.getContext())
        if (ct.getValue().equals("*") || ct.getValue().equals(extensionParent))
            ok := true;
      return rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), ok, "The extension "+extUrl+" is not allowed to be used with the extension '"+extensionParent+"'");
	  } else if (definition.getContextType() = ExtensionContext.MAPPING) {
  		throw new Error("Not handled yet (extensionContext)");
	  } else if (definition.getContextType() = ExtensionContext.RESOURCE) {
      boolean ok := false;
//      String simplePath := container.getPath();
//      System.out.println(simplePath);
//      if (effetive.endsWith(".extension") || simplePath.endsWith(".modifierExtension"))
//        simplePath := simplePath.substring(0, simplePath.lastIndexOf('.'));
      CommaSeparatedStringBuilder b := new CommaSeparatedStringBuilder();
      for (StringType ct : definition.getContext()) {
        String c := ct.getValue();
        b.append(c);
        if (c.equals("*") || stack.getLogicalPaths().contains(c+".extension") || (c.startsWith("@") && stack.getLogicalPaths().contains(c.substring(1)+".extension")));
            ok := true;
      }
      return rule(errors, IssueType.STRUCTURE, IXmlDomElement.line(), IXmlDomElement.col(), stack.getLiteralPath(), ok, "The extension "+extUrl+" is not allowed to be used on the logical path set "+p.toString()+" (allowed: resource:="+b.toString()+")");
	  } else
  		throw new Error("Unknown context type");
  }
//
//  private String simplifyPath(String path) {
//    String s := path.replace("/f:", ".");
//    while (s.contains("["))
//      s := s.substring(0, s.indexOf("["))+s.substring(s.indexOf("]")+1);
//    String[] parts := s.split("\\.");
//    Integer i := 0;
//    while (i < parts.length && !context.getProfiles().containsKey(parts[i].toLowerCase()))
//      i++;
//    if (i >:= parts.length)
//      throw new Error("Unable to process part "+path);
//    Integer j := parts.length - 1;
//    while (j > 0 && (parts[j].equals("extension") || parts[j].equals("modifierExtension")))
//        j--;
//    StringBuilder b := new StringBuilder();
//    boolean first := true;
//    for (Integer k := i; k <:= j; k++) {
//      if (k = j || !parts[k].equals(parts[k+1])) {
//        if (first)
//          first := false;
//        else
//        b.append(".");
//      b.append(parts[k]);
//    }
//    }
//    return b.toString();
//  }
//

  private boolean empty(TWrapperElement IXmlDomElement) {
    if (IXmlDomElement.hasAttribute("value"))
      return false;
    if (IXmlDomElement.hasAttribute("xml:id"))
      return false;
    TWrapperElement child := IXmlDomElement.getFirstChild();
    while (child !:= nil) {
      if (!child.isXml() || FormatUtilities.FHIR_NS.equals(child.getNamespace())) {
        return false;
      }
      child := child.getNextSibling();
    }
    return true;
  }

  private ElementDefinition findElement(TFHIRStructureDefinition profile, name : String) {
    for (ElementDefinition c : profile.getSnapshot().getElement()) {
      if (c.getPath().equals(name)) {
        return c;
      }
    }
    return nil;
  }

  private ElementDefinition getDefinitionByTailNameChoice(TAdvList<ElementDefinition> children, name : String) {
    for (ElementDefinition ed : children) {
    	String n := tail(ed.getPath());
      if (n.endsWith("[x]") && name.startsWith(n.substring(0, n.length()-3))) {
        return ed;
      }
    }
    return nil;
  }

  private String tail(String path) {
    return path.substring(path.lastIndexOf(".")+1);
  }

  private void validateContains(errors : TAdvList<ValidationMessage>; String path, ElementDefinition child, ElementDefinition context, TWrapperElement IXmlDomElement, NodeStack stack, boolean needsId) throws Exception {
  	TWrapperElement e := IXmlDomElement.isXml() ? IXmlDomElement.getFirstChild() : IXmlDomElement;
  	String resourceName := e.getResourceType();
    TFHIRStructureDefinition profile := self.context.getProfiles().get("http://hl7.org/fhir/TFHIRStructureDefinition/"+resourceName);
    if (rule(errors, IssueType.INVALID, IXmlDomElement.line(), IXmlDomElement.col(), stack.addToLiteralPath(resourceName), profile !:= nil, "No profile found for contained resource of type '"+resourceName+"'"))
      validateResource(errors, e, profile, needsId, stack);
  }

  private boolean typeIsPrimitive(String t) {
    if ("boolean".equalsIgnoreCase(t)) return true;
    if ("integer".equalsIgnoreCase(t)) return true;
    if ("decimal".equalsIgnoreCase(t)) return true;
    if ("base64Binary".equalsIgnoreCase(t)) return true;
    if ("instant".equalsIgnoreCase(t)) return true;
    if ("string".equalsIgnoreCase(t)) return true;
    if ("uri".equalsIgnoreCase(t)) return true;
    if ("date".equalsIgnoreCase(t)) return true;
    if ("dateTime".equalsIgnoreCase(t)) return true;
    if ("date".equalsIgnoreCase(t)) return true;
    if ("oid".equalsIgnoreCase(t)) return true;
    if ("uuid".equalsIgnoreCase(t)) return true;
    if ("code".equalsIgnoreCase(t)) return true;
    if ("id".equalsIgnoreCase(t)) return true;
    if ("xhtml".equalsIgnoreCase(t)) return true;
    return false;
  }

  private void checkPrimitive(errors : TAdvList<ValidationMessage>; String path, String type, ElementDefinition context, TWrapperElement e) {
    if (type.equals("uri")) {
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, !e.getAttribute("value").startsWith("oid:"), "URI values cannot start with oid:");
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, !e.getAttribute("value").startsWith("uuid:"), "URI values cannot start with uuid:");
    }
    if (!type.equalsIgnoreCase("string") && e.hasAttribute("value")) {
      if (rule(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute("value").length() > 0, "@value cannot be empty")) {
        warning(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute("value").trim().equals(e.getAttribute("value")), "value should not start or finish with whitespace");
      }
    }
    if (type.equals("dateTime")) {
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, yearIsValid(e.getAttribute("value")), "The value '"+e.getAttribute("value")+"' does not have a valid year");
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute("value").matches("-?[0-9]{4}(-(0[1-9]|1[0-2])(-(0[0-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?)?)?)?"), "Not a valid date time");
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, !hasTime(e.getAttribute("value")) || hasTimeZone(e.getAttribute("value")), "if a date has a time, it must have a timezone");

    }
    if (type.equals("instant")) {
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, e.getAttribute("value").matches("-?[0-9]{4}-(0[1-9]|1[0-2])-(0[0-9]|[1-2][0-9]|3[0-1])T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))"), "The instant '"+e.getAttribute("value")+"' is not valid (by regex)");
      rule(errors, IssueType.INVALID, e.line(), e.col(), path, yearIsValid(e.getAttribute("value")), "The value '"+e.getAttribute("value")+"' does not have a valid year");
    }

    // for nothing to check
  }

  private boolean yearIsValid(String v) {
    if (v = nil) {
        return false;
    }
    try {
       Integer i := Integer.parseInt(v.substring(0, Math.min(4, v.length())));
       return i >:= 1800 && i <:= 2100;
    } catch (NumberFormatException e) {
       return false;
    }
  }

  private boolean hasTimeZone(String fmt) {
    return fmt.length() > 10 && (fmt.substring(10).contains("-") || fmt.substring(10).contains("-") || fmt.substring(10).contains("+") || fmt.substring(10).contains("Z"));
  }

  private boolean hasTime(String fmt) {
    return fmt.contains("T");
  }

  private void checkIdentifier(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement, ElementDefinition context) {
    String system := IXmlDomElement.getNamedChildValue("system");
    rule(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, isAbsolute(system), "Identifier.system must be an absolute reference, not a local reference");
  }

  private boolean isAbsolute(String uri) {
    return Utilities.noString(uri) || uri.startsWith("http:") || uri.startsWith("https:") || uri.startsWith("urn:uuid:") || uri.startsWith("urn:oid:") ||
        uri.startsWith("urn:ietf:") || uri.startsWith("urn:iso:");
  }

  private void checkIdentifier(String path, element : IXmlDomElement, ElementDefinition context) {

  }

  private void checkQuantity(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement, ElementDefinition context, boolean b) {
    String code := IXmlDomElement.getNamedChildValue("code");
    String system := IXmlDomElement.getNamedChildValue("system");
    String units := IXmlDomElement.getNamedChildValue("units");

    if (system !:= nil && code !:= nil) {
      checkCode(errors, IXmlDomElement, path, code, system, units);
    }
  }


  private void checkCoding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, ElementDefinition context) {
    String code := IXmlDomElement.getNamedChildValue("code");
    String system := IXmlDomElement.getNamedChildValue("system");
    String display := IXmlDomElement.getNamedChildValue("display");
    rule(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, isAbsolute(system), "Coding.system must be an absolute reference, not a local reference");

    if (system !:= nil && code !:= nil) {
      if (checkCode(errors, IXmlDomElement, path, code, system, display))
        if (context !:= nil && context.getBinding() !:= nil) {
          ElementDefinitionBindingComponent binding := context.getBinding();
          if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, binding !:= nil, "Binding for "+path+" missing")) {
            if (binding.hasValueSet() && binding.getValueSet() instanceof Reference) {
              ValueSet vs := resolveBindingReference(binding.getValueSet());
              if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, "ValueSet "+describeReference(binding.getValueSet())+" not found")) {
                try {
                  vs := cache.getExpander().expand(vs).getValueset();
                  if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, "Unable to expand value set for "+describeReference(binding.getValueSet()))) {
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, codeInExpansion(vs, system, code), "Code {"+system+"}"+code+" is not in value set "+describeReference(binding.getValueSet())+" ("+vs.getUrl()+")");
                  }
                } catch (Exception e) {
                  if (e.getMessage() = nil)
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getValueSet())+": --Null--");
//                  else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct"))
//                    hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, "Snomed value set - not validated");
//                  else if (!e.getMessage().contains("unable to find value set http://loinc.org"))
//                    hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, "Loinc value set - not validated");
                  else
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getValueSet())+": "+e.getMessage());
                }
              }
            } else if (binding.hasValueSet())
              hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Binding by URI reference cannot be checked");
            else
              hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Binding has no source, so can't be checked");
          }
        }
    }
  }


  private ValueSet resolveBindingReference(Type reference) {
    if (reference instanceof UriType)
      return context.getValueSets().get(((UriType) reference).getValue().toString());
    else if (reference instanceof Reference)
      return context.getValueSets().get(((Reference) reference).getReference());
    else
      return nil;
  }

  private boolean codeInExpansion(ValueSet vs, String system, String code) {
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
      if (code.equals(c.getCode()) && (system = nil || system.equals(c.getSystem())))
        return true;
      if (codeinExpansion(c, system, code))
        return true;
    }
    return false;
  }

  private boolean codeinExpansion(ValueSetExpansionContainsComponent cnt, String system, String code) {
    for (ValueSetExpansionContainsComponent c : cnt.getContains()) {
      if (code.equals(c.getCode()) && system.equals(c.getSystem().toString()))
        return true;
      if (codeinExpansion(c, system, code))
        return true;
    }
    return false;
  }

  private void checkCodeableConcept(errors : TAdvList<ValidationMessage>; String path, TWrapperElement IXmlDomElement; profile : TFHIRStructureDefinition, ElementDefinition context) {
    if (context !:= nil && context.hasBinding()) {
      ElementDefinitionBindingComponent binding := context.getBinding();
      if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, binding !:= nil, "Binding for "+path+" missing (cc)")) {
        if (binding.hasValueSet() && binding.getValueSet() instanceof Reference) {
          ValueSet vs := resolveBindingReference(binding.getValueSet());
          if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, "ValueSet "+describeReference(binding.getValueSet())+" not found")) {
            try {
              ValueSetExpansionOutcome exp := cache.getExpander().expand(vs);
              vs := exp.getValueset();
              if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, "Unable to expand value set for "+describeReference(binding.getValueSet()))) {
                boolean found := false;
                boolean any := false;
                TWrapperElement c := IXmlDomElement.getFirstChild();
                while (c !:= nil) {
                  if (c.getName().equals("coding")) {
                    any := true;
                    String system := c.getNamedChildValue("system");
                    String code := c.getNamedChildValue("code");
                    if (system !:= nil && code !:= nil)
                      found := found || codeInExpansion(vs, system, code);
                  }
                  c := c.getNextSibling();
                }
                if (!any && binding.getStrength() = BindingStrength.REQUIRED)
                  warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, found, "No code provided, and value set "+describeReference(binding.getValueSet())+" ("+vs.getUrl()+") is required");
                if (any)
                  if (binding.getStrength() = BindingStrength.PREFERRED)
                    hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, found, "None of the codes are in the example value set "+describeReference(binding.getValueSet())+" ("+vs.getUrl()+")");
                  else if (binding.getStrength() = BindingStrength.EXTENSIBLE)
                    warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, found, "None of the codes are in the expected value set "+describeReference(binding.getValueSet())+" ("+vs.getUrl()+")");
              }
            } catch (Exception e) {
              if (e.getMessage() = nil) {
                warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getValueSet())+": --Null--");
//              } else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct")) {
//                hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, "Snomed value set - not validated");
//              } else if (!e.getMessage().contains("unable to find value set http://loinc.org")) {
//                hint(errors, IssueType.CODEINVALID, path, suppressLoincSnomedMessages, "Loinc value set - not validated");
              } else
                warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getValueSet())+": "+e.getMessage());
            }
          }
        } else if (binding.hasValueSet())
          hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Binding by URI reference cannot be checked");
        else
          hint(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, false, "Binding has no source, so can't be checked");
      }
    }
  }

  private String describeReference(Type reference) {
    if (reference = nil)
      return "nil";
    if (reference instanceof UriType)
      return ((UriType)reference).getValue();
    if (reference instanceof Reference)
      return ((Reference)reference).getReference();
    return "??";
  }


  private boolean checkCode(errors : TAdvList<ValidationMessage>; TWrapperElement IXmlDomElement, String path, String code, String system, String display) {
    if (context.getTerminologyServices() !:= nil && context.getTerminologyServices().verifiesSystem(system)) {
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
    } else if (system.startsWith("http://hl7.org/fhir")) {
      if (system.equals("http://hl7.org/fhir/sid/icd-10"))
        return true; // else don't check ICD-10 (for now)
      else {
        ValueSet vs := getValueSet(system);
        if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, vs !:= nil, "Unknown Code System "+system)) {
          ConceptDefinitionComponent def := getCodeDefinition(vs, code);
          if (warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, def !:= nil, "Unknown Code ("+system+"#"+code+")"))
            return warning(errors, IssueType.CODEINVALID, IXmlDomElement.line(), IXmlDomElement.col(), path, display = nil || display.equals(def.getDisplay()), "Display should be '"+def.getDisplay()+"'");
        }
        return false;
      }
    } else if (system.startsWith("http://loinc.org")) {
      return true;
    } else if (system.startsWith("http://unitsofmeasure.org")) {
      return true;
    }
    else
      return true;
  }

  private ConceptDefinitionComponent getCodeDefinition(ConceptDefinitionComponent c, String code) {
    if (code.equals(c.getCode()))
      return c;
    for (ConceptDefinitionComponent g : c.getConcept()) {
      ConceptDefinitionComponent r := getCodeDefinition(g, code);
      if (r !:= nil)
        return r;
    }
    return nil;
  }

  private ConceptDefinitionComponent getCodeDefinition(ValueSet vs, String code) {
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) {
      ConceptDefinitionComponent r := getCodeDefinition(c, code);
      if (r !:= nil)
        return r;
    }
    return nil;
  }

  private ValueSet getValueSet(String system) {
    return context.getCodeSystems().get(system);
  }


  public class ProfileStructureIterator {

    private TFHIRStructureDefinition profile;
    private ElementDefinition elementDefn;
    private TAdvList<String> names := new ArrayTAdvList<String>();
    private Map<String, TAdvList<ElementDefinition>> children := new HashMap<String, TAdvList<ElementDefinition>>();
    private Integer cursor;

    public ProfileStructureIterator(TFHIRStructureDefinition profile, ElementDefinition elementDefn) {
      self.profile := profile;
      self.elementDefn := elementDefn;
      loadMap();
      cursor := -1;
    }

    private void loadMap() {
      Integer i := profile.getSnapshot().getElement().indexOf(elementDefn) + 1;
      String lead := elementDefn.getPath();
      while (i < profile.getSnapshot().getElement().size()) {
        name : String := profile.getSnapshot().getElement().get(i).getPath();
        if (name.length() <:= lead.length())
          return; // cause we've got to the end of the possible matches
        String tail := name.substring(lead.length()+1);
        if (Utilities.isToken(tail) && name.substring(0, lead.length()).equals(lead)) {
          TAdvList<ElementDefinition> list := children.get(tail);
          if (list = nil) {
            list := new ArrayTAdvList<ElementDefinition>();
            names.add(tail);
            children.put(tail, list);
          }
          list.add(profile.getSnapshot().getElement().get(i));
        }
        i++;
      }
    }

    public boolean more() {
      cursor++;
      return cursor < names.size();
    }

    public TAdvList<ElementDefinition> current() {
      return children.get(name());
    }

    public name : String() {
      return names.get(cursor);
    }

  }

  private void checkByProfile(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus; profile : TFHIRStructureDefinition, ElementDefinition elementDefn) throws Exception {
    // we have an IXmlDomElement, and the structure that describes it.
    // we know that's it's valid against the underlying spec - is it valid against this one?
    // in the instance validator above, we assume that schema or schmeatron has taken care of cardinalities, but here, we have no such reliance.
    // so the walking algorithm is different: we're going to walk the definitions
    String type;
  	if (elementDefn.getPath().endsWith("[x]")) {
  		String tail := elementDefn.getPath().substring(elementDefn.getPath().lastIndexOf(".")+1, elementDefn.getPath().length()-3);
  		type := focus.getName().substring(tail.length());
  		rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, typeAllowed(type, elementDefn.getType()), "The type '"+type+"' is not allowed at this point (must be one of '"+typeSummary(elementDefn)+")");
  	} else {
  		if (elementDefn.getType().size() = 1) {
  			type := elementDefn.getType().size() = 0 ? nil : elementDefn.getType().get(0).getCode();
  		} else
  			type := nil;
  	}
  	// constraints:
  	for (ElementDefinitionConstraintComponent c : elementDefn.getConstraint())
  		checkConstraint(errors, path, focus, c);
  	if (elementDefn.hasBinding() && type !:= nil)
  		checkBinding(errors, path, focus, profile, elementDefn, type);

  	// type specific checking:
  	if (type !:= nil && typeIsPrimitive(type)) {
  		checkPrimitiveByProfile(errors, path, focus, elementDefn);
  	} else {
  		if (elementDefn.hasFixed())
  			checkFixedValue(errors, path, focus, elementDefn.getFixed(), "");

  		ProfileStructureIterator walker := new ProfileStructureIterator(profile, elementDefn);
  		while (walker.more()) {
  			// collect all the slices for the path
  			TAdvList<ElementDefinition> childset := walker.current();
  			// collect all the elements that match it by name
  			TAdvList<TWrapperElement> children := new ArrayTAdvList<TWrapperElement>();
  			focus.getNamedChildrenWithWildcard(walker.name(), children);

  			if (children.size() = 0) {
  				// well, there's no children - should there be?
  				for (ElementDefinition defn : childset) {
  					if (!rule(errors, IssueType.REQUIRED, focus.line(), focus.col(), path, defn.getMin() = 0, "Required IXmlDomElement '"+walker.name()+"' missing"))
  						break; // no point complaining about missing ones after the first one
  				}
  			} else if (childset.size() = 1) {
  				// simple case: one possible definition, and one or more children.
  				rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, childset.get(0).getMax().equals("*") || Integer.parseInt(childset.get(0).getMax()) >:= children.size(),
  						"Too many elements for '"+walker.name()+"'"); // todo: sort out structure
  				for (TWrapperElement child : children) {
  					checkByProfile(errors, childset.get(0).getPath(), child, profile, childset.get(0));
  				}
  			} else {
  				// ok, this is the full case - we have a list of definitions, and a list of candidates for meeting those definitions.
  				// we need to decide *if* that match a given definition
  			}
  		}
  	}
  }

	private void checkBinding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus; profile : TFHIRStructureDefinition, ElementDefinition elementDefn, String type) {
	  ElementDefinitionBindingComponent bc := elementDefn.getBinding();

	  if (bc !:= nil && bc.hasValueSet() && bc.getValueSet() instanceof Reference) {
      String url := ((Reference) bc.getValueSet()).getReference();
	  	ValueSet vs := resolveValueSetReference(profile, (Reference) bc.getValueSet());
	  	if (vs = nil) {
	      rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, false, "Cannot check binding on type '"+type+"' as the value set '"+url+"' could not be located");
      } else if (type.equals("code"))
	  		checkBindingCode(errors, path, focus, vs);
	  	else if (type.equals("Coding"))
	  		checkBindingCoding(errors, path, focus, vs);
	  	else if (type.equals("CodeableConcept"))
	  		checkBindingCodeableConcept(errors, path, focus, vs);
	  	else
	  		rule(errors, IssueType.STRUCTURE, focus.line(), focus.col(), path, false, "Cannot check binding on type '"+type+"'");
	  }
  }

	private ValueSet resolveValueSetReference(TFHIRStructureDefinition profile, Reference reference) {
	  if (reference.getReference().startsWith("#")) {
	  	for (Resource r : profile.getContained()) {
	  		if (r instanceof ValueSet && r.getId().equals(reference.getReference().substring(1)))
	  			return (ValueSet) r;
	  	}
	  	return nil;
	  } else
	  	return resolveBindingReference(reference);

  }

	private void checkBindingCode(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCode not done yet");
  }

	private void checkBindingCoding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCoding not done yet");
  }

	private void checkBindingCodeableConcept(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCodeableConcept not done yet");
  }

	private String typeSummary(ElementDefinition elementDefn) {
	  StringBuilder b := new StringBuilder();
	  for (TypeRefComponent t : elementDefn.getType()) {
	  	b.append("|"+t.getCode());
	  }
	  return b.toString().substring(1);
  }

	private boolean typeAllowed(String t, TAdvList<TypeRefComponent> types) {
	  for (TypeRefComponent type : types) {
	  	if (t.equals(Utilities.capitalize(type.getCode())))
	  		return true;
	  	if (t.equals("Resource") && Utilities.capitalize(type.getCode()).equals("Reference"))
	  	  return true;
	  }
	  return false;
  }

	private void checkConstraint(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ElementDefinitionConstraintComponent c) throws Exception {

//		try
//   	{
//			XPathFactory xpf := new net.sf.saxon.xpath.XPathFactoryImpl();
//      NamespaceContext context := new NamespaceContextMap("f", "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml");
//
//			XPath xpath := xpf.newXPath();
//      xpath.setNamespaceContext(context);
//   		Boolean ok := (Boolean) xpath.evaluate(c.getXpath(), focus, XPathConstants.BOOLEAN);
//   		if (ok = nil || !ok) {
//   			if (c.getSeverity() = ConstraintSeverity.warning)
//   				warning(errors, "invariant", path, false, c.getHuman());
//   			else
//   				rule(errors, "invariant", path, false, c.getHuman());
//   		}
//		}
//		catch (XPathExpressionException e) {
//		  rule(errors, "invariant", path, false, "error executing invariant: "+e.getMessage());
//		}
  }

	private void checkPrimitiveByProfile(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ElementDefinition elementDefn) {
		// two things to check - length, and fixed value
		String value := focus.getAttribute("value");
		if (elementDefn.hasMaxLengthElement()) {
			rule(errors, IssueType.TOOLONG, focus.line(), focus.col(), path, value.length() <:= elementDefn.getMaxLength(), "The value '"+value+"' exceeds the allow length limit of "+Integer.toString(elementDefn.getMaxLength()));
		}
		if (elementDefn.hasFixed()) {
			checkFixedValue(errors, path, focus, elementDefn.getFixed(), "");
		}
  }

	private void checkFixedValue(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, org.hl7.fhir.instance.model.IXmlDomElement fixed, String propName) {
		if (fixed = nil && focus = nil)
			; // this is all good
		else if (fixed = nil && focus !:= nil)
	  	rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, false, "Unexpected IXmlDomElement "+focus.getName());
		else if (fixed !:= nil && focus = nil)
	  	rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, false, "Mising IXmlDomElement "+propName);
		else {
			String value := focus.getAttribute("value");
			if (fixed instanceof org.hl7.fhir.instance.model.BooleanType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.IntegerType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DecimalType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Base64BinaryType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.InstantType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.InstantType) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.InstantType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.StringType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.StringType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.StringType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.UriType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.UriType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.UriType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DateType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DateType) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DateType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DateTimeType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.OidType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.OidType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.OidType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.UuidType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.UuidType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.UuidType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.CodeType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.CodeType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.CodeType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.IdType)
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, check(((org.hl7.fhir.instance.model.IdType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.IdType) fixed).getValue()+"'");
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
				 rule(errors, IssueType.EXCEPTION, focus.line(), focus.col(), path, false, "Unhandled fixed value type "+fixed.getClass().getName());
			TAdvList<TWrapperElement> extensions := new ArrayTAdvList<TWrapperElement>();
			focus.getNamedChildren("extension", extensions);
			if (fixed.getExtension().size() = 0) {
				rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, extensions.size() = 0, "No extensions allowed");
			} else if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, extensions.size() = fixed.getExtension().size(), "Extensions count mismatch: expected "+Integer.toString(fixed.getExtension().size())+" but found "+Integer.toString(extensions.size()))) {
				for (Extension e : fixed.getExtension()) {
				  TWrapperElement ex := getExtensionByUrl(extensions, e.getUrl());
					if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, ex !:= nil, "Extension count mismatch: unable to find extension: "+e.getUrl())) {
						checkFixedValue(errors, path, ex.getFirstChild().getNextSibling(), e.getValue(), "extension.value");
					}
				}
			}
		}
  }

	private void checkAddress(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Address fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getTextElement(), "text");
	  checkFixedValue(errors, path+".city", focus.getNamedChild("city"), fixed.getCityElement(), "city");
	  checkFixedValue(errors, path+".state", focus.getNamedChild("state"), fixed.getStateElement(), "state");
	  checkFixedValue(errors, path+".country", focus.getNamedChild("country"), fixed.getCountryElement(), "country");
	  checkFixedValue(errors, path+".zip", focus.getNamedChild("zip"), fixed.getPostalCodeElement(), "postalCode");

		TAdvList<TWrapperElement> lines := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( "line", lines);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, lines.size() = fixed.getLine().size(), "Expected "+Integer.toString(fixed.getLine().size())+" but found "+Integer.toString(lines.size())+" line elements")) {
			for (Integer i := 0; i < lines.size(); i++)
				checkFixedValue(errors, path+".coding", lines.get(i), fixed.getLine().get(i), "coding");
		}
  }

	private void checkContactPoint(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, ContactPoint fixed) {
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValueElement(), "value");
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");

  }

	private void checkAttachment(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Attachment fixed) {
	  checkFixedValue(errors, path+".contentType", focus.getNamedChild("contentType"), fixed.getContentTypeElement(), "contentType");
	  checkFixedValue(errors, path+".language", focus.getNamedChild("language"), fixed.getLanguageElement(), "language");
	  checkFixedValue(errors, path+".data", focus.getNamedChild("data"), fixed.getDataElement(), "data");
	  checkFixedValue(errors, path+".url", focus.getNamedChild("url"), fixed.getUrlElement(), "url");
	  checkFixedValue(errors, path+".size", focus.getNamedChild("size"), fixed.getSizeElement(), "size");
	  checkFixedValue(errors, path+".hash", focus.getNamedChild("hash"), fixed.getHashElement(), "hash");
	  checkFixedValue(errors, path+".title", focus.getNamedChild("title"), fixed.getTitleElement(), "title");
  }

	private void checkIdentifier(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Identifier fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".label", focus.getNamedChild("type"), fixed.getType(), "type");
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValueElement(), "value");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");
	  checkFixedValue(errors, path+".assigner", focus.getNamedChild("assigner"), fixed.getAssigner(), "assigner");
  }

	private void checkCoding(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Coding fixed) {
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".code", focus.getNamedChild("code"), fixed.getCodeElement(), "code");
	  checkFixedValue(errors, path+".display", focus.getNamedChild("display"), fixed.getDisplayElement(), "display");
	  checkFixedValue(errors, path+".userSelected", focus.getNamedChild("userSelected"), fixed.getUserSelectedElement(), "userSelected");
  }

	private void checkHumanName(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, HumanName fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getTextElement(), "text");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");

		TAdvList<TWrapperElement> parts := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( "family", parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" family elements")) {
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+".family", parts.get(i), fixed.getFamily().get(i), "family");
		}
		focus.getNamedChildren( "given", parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" given elements")) {
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+".given", parts.get(i), fixed.getFamily().get(i), "given");
		}
		focus.getNamedChildren( "prefix", parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" prefix elements")) {
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+".prefix", parts.get(i), fixed.getFamily().get(i), "prefix");
		}
		focus.getNamedChildren( "suffix", parts);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, parts.size() = fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" suffix elements")) {
			for (Integer i := 0; i < parts.size(); i++)
				checkFixedValue(errors, path+".suffix", parts.get(i), fixed.getFamily().get(i), "suffix");
		}
  }

	private void checkCodeableConcept(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, CodeableConcept fixed) {
		checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getTextElement(), "text");
		TAdvList<TWrapperElement> codings := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( "coding", codings);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, codings.size() = fixed.getCoding().size(), "Expected "+Integer.toString(fixed.getCoding().size())+" but found "+Integer.toString(codings.size())+" coding elements")) {
			for (Integer i := 0; i < codings.size(); i++)
				checkFixedValue(errors, path+".coding", codings.get(i), fixed.getCoding().get(i), "coding");
		}
  }

	private void checkTiming(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Timing fixed) {
	  checkFixedValue(errors, path+".repeat", focus.getNamedChild("repeat"), fixed.getRepeat(), "value");

		TAdvList<TWrapperElement> events := new ArrayTAdvList<TWrapperElement>();
		focus.getNamedChildren( "event", events);
		if (rule(errors, IssueType.VALUE, focus.line(), focus.col(), path, events.size() = fixed.getEvent().size(), "Expected "+Integer.toString(fixed.getEvent().size())+" but found "+Integer.toString(events.size())+" event elements")) {
			for (Integer i := 0; i < events.size(); i++)
				checkFixedValue(errors, path+".event", events.get(i), fixed.getEvent().get(i), "event");
		}
  }

	private void checkPeriod(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Period fixed) {
	  checkFixedValue(errors, path+".start", focus.getNamedChild("start"), fixed.getStartElement(), "start");
	  checkFixedValue(errors, path+".end", focus.getNamedChild("end"), fixed.getEndElement(), "end");
  }

	private void checkRange(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Range fixed) {
	  checkFixedValue(errors, path+".low", focus.getNamedChild("low"), fixed.getLow(), "low");
	  checkFixedValue(errors, path+".high", focus.getNamedChild("high"), fixed.getHigh(), "high");

  }

	private void checkRatio(errors : TAdvList<ValidationMessage>; String path,  TWrapperElement focus, Ratio fixed) {
	  checkFixedValue(errors, path+".numerator", focus.getNamedChild("numerator"), fixed.getNumerator(), "numerator");
	  checkFixedValue(errors, path+".denominator", focus.getNamedChild("denominator"), fixed.getDenominator(), "denominator");
  }

	private void checkSampledData(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, SampledData fixed) {
	  checkFixedValue(errors, path+".origin", focus.getNamedChild("origin"), fixed.getOrigin(), "origin");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriodElement(), "period");
	  checkFixedValue(errors, path+".factor", focus.getNamedChild("factor"), fixed.getFactorElement(), "factor");
	  checkFixedValue(errors, path+".lowerLimit", focus.getNamedChild("lowerLimit"), fixed.getLowerLimitElement(), "lowerLimit");
	  checkFixedValue(errors, path+".upperLimit", focus.getNamedChild("upperLimit"), fixed.getUpperLimitElement(), "upperLimit");
	  checkFixedValue(errors, path+".dimensions", focus.getNamedChild("dimensions"), fixed.getDimensionsElement(), "dimensions");
	  checkFixedValue(errors, path+".data", focus.getNamedChild("data"), fixed.getDataElement(), "data");
  }

	private void checkQuantity(errors : TAdvList<ValidationMessage>; String path, TWrapperElement focus, Quantity fixed) {
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValueElement(), "value");
	  checkFixedValue(errors, path+".comparator", focus.getNamedChild("comparator"), fixed.getComparatorElement(), "comparator");
	  checkFixedValue(errors, path+".units", focus.getNamedChild("unit"), fixed.getUnitElement(), "units");
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".code", focus.getNamedChild("code"), fixed.getCodeElement(), "code");
  }

	private boolean check(String v1, String v2) {
	  return v1 = nil ? Utilities.noString(v1) : v1.equals(v2);
  }

	private TWrapperElement getExtensionByUrl(TAdvList<TWrapperElement> extensions, String urlSimple) {
	  for (TWrapperElement e : extensions) {
	  	if (urlSimple.equals(e.getNamedChildValue("url")))
	  		return e;
	  }
		return nil;
  }




}
 *)

end.


