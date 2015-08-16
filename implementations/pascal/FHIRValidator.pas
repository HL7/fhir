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

uses
  ActiveX, ComObj, SysUtils, Classes,
  kCritSct, StringSupport, IdGlobal,
  AdvObjects, AdvStringObjectMatches, AdvFiles, AdvZipReaders, AdvZipParts,
  AdvMemories, AdvVclStreams, AdvBuffers, AdvNameBuffers,
  IdSoapXml, IdSoapMsXml, AltovaXMLLib_TLB, MsXmlParser, IdUri,
  FHIRParser, FHIRBase, FHIRTypes, FHIRComponents, FHIRResources,
  FHIRUtilities, FHIRValueSetExpander, FHIRConstants, FHIRValueSetChecker,
  TerminologyServer, ProfileManager;

type
  TChildIterator = class (TAdvObject)
  private
    FParent : TIdSoapXmlElement;
    FBasePath : String;
    FLastCount : Integer;
    FChild : TIdSoapXmlElement;
  public
    Constructor Create(path : string; elem : TIdSoapXmlElement);
    function next : boolean;
    function name : string;
    property element : TIdSoapXmlElement read FChild;
    function path : string;
  end;

  TFHIRValidatorContext = class (TAdvObject)
  private
    FxmlApp: AltovaXMLLib_TLB.Application;
  end;

  TFHIRValidator = class (TAdvObject)
  private
    FSchematronSource : String;
    FTerminologyServer : TTerminologyServer;
    FProfiles : TProfileManager;

    FTypes : TAdvStringObjectMatch; // TFHIRProfile
    FSources : TAdvNameBufferList;
    FCache : IXMLDOMSchemaCollection;
    FsuppressLoincSnomedMessages: boolean;
    FChecks : TAdvStringObjectMatch; // TValueSetChecker


    procedure validateAtomEntry(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; specifiedprofile : TFHirStructureDefinition);
    procedure validate(op : TFhirOperationOutcome; path : string; elem : TIdSoapXmlElement; specifiedprofile : TFHirStructureDefinition; inBundle : boolean);
    function getProfileForType(localName : string; specifiedprofile : TFHirStructureDefinition) : TFHirStructureDefinition;
    procedure validateBinary(elem : TIdSoapXmlElement);
    procedure validateTag(path : string; element : TIdSoapXmlElement; onEntry : boolean);
    procedure validateElement(op : TFhirOperationOutcome; profile : TFHirStructureDefinition; structure : TFHirProfileStructureHolder; path : string; definition : TFHirElementDefinition; cprofile : TFHirStructureDefinition; context : TFHirElementDefinition; element : TIdSoapXmlElement; inBundle : boolean);
    function findElement(structure : TFHirProfileStructureHolder; name : string) : TFHirElementDefinition;
    function getChildren(structure : TFHirProfileStructureHolder; path : String) : TFHirElementDefinitionList;
    function getChild(children : TFHirElementDefinitionList; name : string) : TFHirElementDefinition;
    function getDefinitionByTailNameChoice(children : TFHirElementDefinitionList; name : string) : TFHirElementDefinition;
    function tail(path : string) : String;
    procedure validateContains(op : TFhirOperationOutcome; path : string; child : TFHirElementDefinition; context : TFHirElementDefinition; element : TIdSoapXmlElement; inBundle : boolean);
    function typeIsPrimitive(t : string) : boolean;
    procedure checkPrimitive(op : TFhirOperationOutcome; path : string; type_ : string; context : TFHirElementDefinition; e : TIdSoapXmlElement);
    procedure checkExtension(path : string; elementDefn : TFHirElementDefinition; context : TFHirElementDefinition; e : TIdSoapXmlElement);
    procedure checkReference(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition; inBundle : boolean);
    procedure checkIdentifier(path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition);
    procedure checkQuantity(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition);
    procedure checkCoding(op : TFhirOperationOutcome; profile : TFHirStructureDefinition; path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition);
    procedure checkCodeableConcept(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; profile : TFHirStructureDefinition; context : TFHirElementDefinition);
    function resolveBindingReference(ref : TFHIRType) : TValueSetChecker;
    function describeReference(ref : TFHIRType) : String;
    function codeInExpansion(list: TFHIRValueSetExpansionContainsList; system, code : string) : Boolean;


    procedure Load(feed : TFHIRBundle);
//    function LoadFile(name : String; isFree : boolean = false) : IXMLDomDocument2;
    function LoadDoc(name : String; isFree : boolean = false) : IXMLDomDocument2;
    procedure validateInstance(op : TFHIROperationOutcome; elem : TIdSoapXmlElement; specifiedprofile : TFHirStructureDefinition); overload;
    procedure processSchematron(op : TFhirOperationOutcome; source : String);
    procedure executeSchematron(context : TFHIRValidatorContext; op : TFhirOperationOutcome; source, name : String);
//    function transform(op: TFhirOperationOutcome; source: IXMLDOMDocument2; transform: IXSLTemplate): IXMLDOMDocument2;
    procedure SetTerminologyServer(const Value: TTerminologyServer);
    function getTargetByName(list: TFHirElementDefinitionList; name: String): TFHirElementDefinition;
    procedure SetProfiles(const Value: TProfileManager);
  public
    constructor Create; override;
    destructor Destroy; override;
    Property TerminologyServer : TTerminologyServer read FTerminologyServer write SetTerminologyServer;
    Property Profiles : TProfileManager read FProfiles write SetProfiles;
    Property suppressLoincSnomedMessages : boolean read FsuppressLoincSnomedMessages write FsuppressLoincSnomedMessages;
    procedure LoadFromDefinitions(filename : string);
    Property SchematronSource : String read FSchematronSource write FSchematronSource;

    function AcquireContext : TFHIRValidatorContext;
    procedure YieldContext(context : TFHIRValidatorContext);
    Function validateInstance(context : TFHIRValidatorContext; elem : TIdSoapXmlElement; opDesc : String; profile : TFHirStructureDefinition) : TFHIROperationOutcome; overload;
    Function validateInstance(context : TFHIRValidatorContext; resource : TFhirResource; opDesc : String; profile : TFHirStructureDefinition) : TFHIROperationOutcome; overload;
    Function validateInstance(context : TFHIRValidatorContext; source : TAdvBuffer; opDesc : String; profile : TFHirStructureDefinition) : TFHIROperationOutcome; overload;
  end;

implementation


Constructor TChildIterator.Create(path : string; elem : TIdSoapXmlElement);
begin
  inherited create;
  FParent := elem;
  FBasePath := path;
end;

function TChildIterator.next : boolean;
var
  LastName : string;
begin
  if (Fchild = nil) then
  begin
    FChild := FParent.FirstChild;
    FLastCount := 0;
  end
  else
  begin
    LastName := FChild.Name;
    FChild := FChild.NextSibling;
    if (FChild <> nil) and (FChild.Name = LastName) then
      inc(FLastCount)
    else
      FLastCount := 0;
  end;
  result := (Fchild <> nil);
end;

function TChildIterator.name : string;
begin
  result := FChild.Name;
end;

function TChildIterator.path : string;
var
  n : TIdSoapXmlElement;
begin
  result := '';
  n := FChild.NextSibling;
  if (n <> nil) and (n.Name = Fchild.Name) then
    result := '['+inttostr(FlastCount)+']';
   result := FBasePath+'/f:'+name+result;
end;

Function TFHIRValidator.validateInstance(context : TFHIRValidatorContext; elem : TIdSoapXmlElement; opDesc : String; profile : TFHirStructureDefinition) : TFHIROperationOutcome;
begin
  result := TFhirOperationOutcome.create;
  try
    validateInstance(result, elem, profile);
    BuildNarrative(result, opDesc);
    result.link;
  finally
    result.free;
  end;
end;

procedure TFHIRValidator.validateAtomEntry(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; specifiedprofile : TFHirStructureDefinition);
var
  ci : TChildIterator;
  r : TIdSoapXmlElement;
begin
  ci := TChildIterator.create(path, element);
  try
    while (ci.next) do
    begin
      if (ci.name = 'category') then
        validateTag(ci.path, ci.element, false)
      else if (ci.name = 'content') then
      begin
        r := ci.element.FirstChild;
        validate(op, ci.path+'/f:'+r.Name, r, specifiedProfile, true);
      end;
    end;
  finally
    ci.free;
  end;
end;

procedure TFHIRValidator.validate(op : TFhirOperationOutcome; path : string; elem : TIdSoapXmlElement; specifiedprofile : TFHirStructureDefinition; inBundle : boolean);
var
  p : TFHirStructureDefinition;
begin
  if (elem.Name = 'Binary') then
    validateBinary(elem)
  else
  begin
    p := getProfileForType(elem.Name, specifiedprofile);
    if (op.error('InstanceValidator', 'invalid', elem.Name, p <> nil, 'Unknown Resource Type '+elem.Name)) then
      validateElement(op, p, p.snapshot, path+'/f:'+elem.Name, p.snapshot.elementList[0], nil, nil, elem, inBundle);
  end;
end;

function TFHIRValidator.getProfileForType(localName : string; specifiedprofile : TFHirStructureDefinition) : TFHirStructureDefinition;
begin
  if specifiedprofile <> nil then
    result := specifiedprofile
  else
    result := FTypes.matches[localName] as TFHirStructureDefinition;
end;


procedure TFHIRValidator.validateBinary(elem : TIdSoapXmlElement);
begin
  // nothing yet
end;

procedure TFHIRValidator.validateTag(path : string; element : TIdSoapXmlElement; onEntry : boolean);
begin
  // nothing yet
end;

procedure TFHIRValidator.validateElement(op : TFhirOperationOutcome; profile : TFHirStructureDefinition; structure : TFHirProfileStructureHolder; path : string; definition : TFHirElementDefinition; cprofile : TFHirStructureDefinition; context : TFHirElementDefinition; element : TIdSoapXmlElement; inBundle : boolean);
var
  children : TFHirElementDefinitionList;
  ci : TChildIterator;
  child : TFHirElementDefinition;
  type_ : String;
  p : TFHirStructureDefinition;
  b : boolean;
  i : integer;
begin
  children := getChildren(structure, definition.path);
  try
    ci := TChildIterator.create(path, element);
    try
      while (ci.next) do
      begin
        child := getChild(children, ci.name);
        type_ := '';
        if (ci.name = 'extension') then
        begin
          type_ := 'Extension';
          child := definition; // it's going to be used as context below
        end
        else if (child = nil) then
        begin
          child := getDefinitionByTailNameChoice(children, ci.name);
          if (child <> nil) then
            type_ := copy(ci.name, length(tail(child.Path)) - 2, $FFFF);
          if ('Resource' = type_) then
            type_ := 'Reference';
        end
        else
        begin
          if (child.type_List.Count > 1) then
          begin
            // all references?
            b := true;
            for i := 0 to child.type_List.Count - 1 do
              if child.type_List[i].Code <> 'Reference' then
                b := false;
            if b then
              type_ := child.type_List[0].Code
            else
              raise Exception.Create('to do?');
          end
          else  if (child.type_List.Count = 1) then
            type_ := child.Type_List[0].code;
          if (type_ <> '') then
          begin
            if (StringStartsWith(type_, 'Reference(')) then
              type_ := 'Reference';
            if (StringStartsWith(type_, '@')) then
            begin
              child := findElement(structure, copy(type_, 2, $FFFF));
              type_ := '';
            end;
          end;
        end;

        if (type_ <> '') then
        begin
          if (typeIsPrimitive(type_)) then
            checkPrimitive(op, ci.path, type_, child, ci.element)
          else
          begin
            if (type_ = 'Identifier') then
              checkIdentifier(ci.path, ci.element, child)
            else if (type_ = 'Coding') then
              checkCoding(op, cprofile, ci.path, ci.element, child)
            else if (type_ = 'Reference') then
              checkReference(op, ci.path, ci.element, child, inBundle)
            else if (type_ = 'CodeableConcept') then
              checkCodeableConcept(op, ci.path, ci.element, profile, child);

            if (type_ = 'Resource') then
              validateContains(op, ci.path, child, definition, ci.element, inBundle)
            else
            begin
              if (type_ = 'Money') then
                type_ := 'Quantity';

              p := getProfileForType(type_, nil);
              if (op.error('InstanceValidator', 'structure', ci.path, p <> nil, 'Unknown type_ '+type_)) then
                validateElement(op, p, p.snapshot, ci.path, p.snapshot.elementList[0], profile, child, ci.element, inBundle);
            end;
          end;
        end
        else
        begin
          if (op.error('InstanceValidator', 'structure', path, child <> nil, 'Unrecognised Content '+ci.name)) then
            validateElement(op, profile, structure, ci.path, child, nil, nil, ci.element, inBundle);
        end;
      end;
    finally
      ci.free;
    end;
  finally
    children.free;
  end;
end;


function TFHIRValidator.validateInstance(context: TFHIRValidatorContext; resource: TFhirResource; opDesc: String; profile: TFHirStructureDefinition): TFHIROperationOutcome;
var
  stream : TBytesStream;
  xml : TFHIRXmlComposer;
  buf : TAdvBuffer;
begin
  stream := TBytesStream.Create(nil);
  try
    xml := TFHIRXmlComposer.Create('en');
    try
      xml.Compose(stream, resource, true, nil);
    finally
      xml.Free;
    end;
    buf := TAdvBuffer.Create;
    try
      buf.AsBytes := copy(stream.Bytes, 0, stream.Size);
      result := validateInstance(context, buf, opDesc, profile);
    finally
      buf.Free;
    end;
  finally
    stream.Free;
  end;
end;

function TFHIRValidator.findElement(structure : TFHirProfileStructureHolder; name : string) : TFHirElementDefinition;
var
  i : integer;
  c : TFHirElementDefinition;
begin
  result := nil;
  for i := 0 to structure.ElementList.Count - 1 do
  begin
    c := structure.ElementList[i];
    if (c.Path = name) then
    begin
      result := c;
      exit;
    end;
  end;
end;

function TFHIRValidator.getTargetByName(list : TFHirElementDefinitionList; name : String) : TFHirElementDefinition;
var
  i : integer;
begin
  result := nil;
  for i := 0 to list.Count - 1 do
    if list[i].name = name then
    begin
      result := list[i];
      exit;
    end;
end;

function TFHIRValidator.getChildren(structure : TFHirProfileStructureHolder; path : String) : TFHirElementDefinitionList;
var
  i : integer;
  e : TFHirElementDefinition;
  tail : string;
  p : String;
  res : TFHirElementDefinitionList;
  tgt : TFHirElementDefinition;
begin
  result := nil;
  res := TFHirElementDefinitionList.create;
  try
    for i := 0 to structure.elementList.Count - 1 do
    begin
      e := structure.elementList[i];
      p := e.path;
      if (e.nameReference <> '') and path.StartsWith(p) then
      begin
        tgt := getTargetByName(structure.elementList, e.nameReference);
        if (tgt = nil) then
          raise Exception.Create('Unable to find target for name "'+e.nameReference+'"');
        // The path we are navigating to is on or below this element, but the element defers its definition to another named part of the structure
        if (path.length > p.length) then
          // The path navigates further into the referenced element, so go ahead along the path over there
          result := getChildren(structure, tgt.path+'.'+path.substring(p.length+1))
        else
          // The path we are looking for is actually this element, but since it defers it definition, go get the referenced element
          result := getChildren(structure, tgt.path);
        break;
      end
      else if (p.startsWith(path+'.')) then
      begin
    	  // The path of the element is a child of the path we're looking for (i.e. the parent),
    	  // so add this element to the result.
        tail := copy(e.Path, length(path)+2, $FF);

        if pos('.', tail) = 0 then
          res.add(e.link);
      end;
    end;
    if result = nil then
      result := res.link;
  finally
    res.free;
  end;
end;


function TFHIRValidator.getChild(children : TFHirElementDefinitionList; name : string) : TFHirElementDefinition;
var
  i : integer;
  n : string;
begin
  result := nil;
  for i := 0 to children.count - 1 do
  begin
    n := children[i].path;
    if tail(n) = name then
    begin
      result := children[i];
      exit;
    end;
  end;
end;

function TFHIRValidator.getDefinitionByTailNameChoice(children : TFHirElementDefinitionList; name : string) : TFHirElementDefinition;
var
  i : integer;
  n : string;
begin
  result := nil;
  for i := 0 to children.count - 1 do
  begin
    n := tail(children[i].path);
    if n.EndsWith('[x]') and name.StartsWith(copy(n, 1, length(n)-3)) then
    begin
      result := children[i];
      exit;
    end;
  end;
end;


function TFHIRValidator.tail(path : string) : String;
begin
  result := copy(path, LastDelimiter('.', path)+1, $FF);
end;

procedure TFHIRValidator.validateContains(op : TFhirOperationOutcome; path : string; child : TFHirElementDefinition; context : TFHirElementDefinition; element : TIdSoapXmlElement; inBundle : boolean);
begin
  validate(op, path, element.FirstChild, nil, inBundle);
end;

function TFHIRValidator.typeIsPrimitive(t : string) : boolean;
begin
  t := lowercase(t);
  if 'boolean' = t then
    result := true
  else if ('integer' = t) then
    result := true
  else if ('decimal' = t) then
    result := true
  else if ('base64binary' = t) then
    result := true
  else if ('instant' = t) then
    result := true
  else if ('string' = t) then
    result := true
  else if ('uri' = t) then
    result := true
  else if ('date' = t) then
    result := true
  else if ('datetime' = t) then
    result := true
  else if ('date' = t) then
    result := true
  else if ('oid' = t) then
    result := true
  else if ('uuid' = t) then
    result := true
  else if ('code' = t) then
    result := true
  else if ('id' = t) then
    result := true
  else if ('xhtml' = t) then
    result := true
  else
    result := false;
end;

procedure TFHIRValidator.checkPrimitive(op : TFhirOperationOutcome; path : string; type_ : string; context : TFHirElementDefinition; e : TIdSoapXmlElement);
begin
  // for now. nothing to check
  if (type_ = 'uri') then
  begin
    op.error('InstanceValidator', 'invalid', path,  not StringStartsWith(e.getAttribute('', 'value'), 'oid:'), 'URI values cannot start with oid: (use urn:oid:)');
    op.error('InstanceValidator', 'invalid', path, not StringStartsWith(e.getAttribute('', 'value'), 'uuid:'), 'URI values cannot start with uuid: (use urn:uuid:)');
  end;
end;

Function getNamedChildValue(element : TIdSoapXmlElement; name : String) : string;
begin
  element := element.FirstElement(FHIR_NS, name);
  if element <> nil then
    result := element.getAttribute('', 'value')
  else
    result := '';
end;

procedure TFHIRValidator.checkExtension(path : string; elementDefn : TFHirElementDefinition; context : TFHirElementDefinition; e : TIdSoapXmlElement);
begin
  // for now, nothing to check yet
end;

function refError(r : String; inBundle : boolean):string;
var
  uri : TIdURI;
begin
  try
    uri := TIdURI.create(r);
    try
      if (uri.Protocol <> '') and not StringArrayExistsSensitive(['http', 'https'], uri.Protocol) then
        result := 'Unacceptable protocol'
      else if not inBundle and (StringStartsWith(r, 'cid:') or StringStartsWith(r, 'urn:')) then
        result := 'Logical Identifiers are not valid'
      else
        result := '';
    finally
      uri.free;
    end;
  except
    on e:exception do
      result := e.Message;
  end;
end;

procedure TFHIRValidator.checkReference(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition; inBundle : boolean);
var
  r, e : String;
begin
  r := getNamedChildValue(element,  'reference');
  if (r <> '') then
  begin
    e := refError(r, inBundle);
    op.error('InstanceValidator', 'value', path, e = '', 'The Resource reference "'+r+'" is not valid: '+e);
  end;
end;

procedure TFHIRValidator.checkIdentifier(path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition);
begin
  // nothing to do yet
end;

procedure TFHIRValidator.checkQuantity(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition);
var
  code, system, units :string;
begin
  code := getNamedChildValue(element,  'code');
  system := getNamedChildValue(element,  'system');
  units := getNamedChildValue(element,  'units');
  if (system <> '') and (code <> '') then
    FTerminologyServer.checkCode(op, path, code, system, units);
end;

procedure TFHIRValidator.checkCoding(op : TFhirOperationOutcome; profile : TFHirStructureDefinition; path : string; element : TIdSoapXmlElement; context : TFHirElementDefinition);
var
  code, system, display :string;
  binding : TFHirElementDefinitionBinding;
  vsc : TValueSetChecker;
begin
  code := getNamedChildValue(element,  'code');
  system := getNamedChildValue(element,  'system');
  display := getNamedChildValue(element,  'display');
  if (system <> '') and (code <> '') then
    if FTerminologyServer.checkCode(op, path, code, system, display) then
    begin
      if (context <> nil) and (context.binding <> nil) then
      begin
        binding := context.binding;
        if op.warning('InstanceValidator', 'code-unknown', path, binding <> nil, 'Binding not provided') then
        begin
          if binding.valueset is TFhirReference then
          begin
            vsc := resolveBindingReference(binding.valueset);
            try
              try
                if (op.warning('InstanceValidator', 'code-unknown', path, vsc <> nil, 'ValueSet '+describeReference(binding.valueset)+' not found')) then
                  op.warning('InstanceValidator', 'code-unknown', path, vsc.check(system, code), 'Code {'+system+'}'+code+' is not in value set '+context.Binding.name+' ('+vsc.id+')');
              Except
                on e : Exception do
                  if StringFind(e.Message, 'unable to find value set http://snomed.info/sct') > 0 then
                    op.hint('InstanceValidator', 'code-unknown', path, suppressLoincSnomedMessages, 'Snomed value set - not validated')
                  else if StringFind(e.Message, 'unable to find value set http://loinc.org') > 0 then
                    op.hint('InstanceValidator', 'code-unknown', path, suppressLoincSnomedMessages, 'Loinc value set - not validated')
                  else
                    op.warning('InstanceValidator', 'code-unknown', path, false, 'Exception opening value set '+vsc.id+' for '+context.Binding.name+': '+e.Message);
              end;
            finally
              vsc.Free;
            end;
          end;
        end;
      end;
    end;
end;

procedure TFHIRValidator.checkCodeableConcept(op : TFhirOperationOutcome; path : string; element : TIdSoapXmlElement; profile : TFHirStructureDefinition; context : TFHirElementDefinition);
var
  binding : TFHirElementDefinitionBinding;
  found, any : boolean;
  c : TIdSoapXmlElement;
  system, code : String;
  vsc : TValueSetChecker;
begin
  if (context <> nil) and (context.binding <> nil) then
  begin
    binding := context.binding;
    if binding.valueset is TFhirReference then
    begin
      try
        vsc := resolveBindingReference(binding.valueset);
        try
          if (op.warning('InstanceValidator', 'code-unknown', path, vsc <> nil, 'ValueSet '+describeReference(binding.valueset)+' not found')) then
          begin
            found := false;
            any := false;
            c := element.FirstChild;
            while (c <> nil) do
            begin
              if (c.NodeName = 'coding') then
              begin
                any := true;
                system := getNamedChildValue(c, 'system');
                code := getNamedChildValue(c, 'code');
                if (system <> '') and (code <> '') then
                  found := found or vsc.check(system, code);
              end;
              c := c.nextSibling;
            end;
            if not any and (binding.Strength = BindingStrengthRequired) then
              op.warning('InstanceValidator', 'code-unknown', path, false, 'No code provided, and value set '+context.Binding.name+' ('+vsc.id+') is required');
            if (any) then
              if (binding.Strength = BindingStrengthPreferred) then
                op.hint('InstanceValidator', 'code-unknown', path, found, 'None of the codes are in the example value set '+context.Binding.name+' ('+vsc.id+')')
              else if (binding.Strength = BindingStrengthExtensible) then
                op.warning('InstanceValidator', 'code-unknown', path, found, 'Code {'+system+'}'+code+' is not in value set '+context.Binding.name+' ('+vsc.id+')');
          end;
        finally
          vsc.free;
        end;
      Except
        on e : Exception do
          if StringFind(e.Message, 'unable to find value set http://snomed.info/sct') > 0 then
            op.hint('InstanceValidator', 'code-unknown', path, suppressLoincSnomedMessages, 'Snomed value set - not validated')
          else if StringFind(e.Message, 'unable to find value set http://loinc.org') > 0 then
            op.hint('InstanceValidator', 'code-unknown', path, suppressLoincSnomedMessages, 'Loinc value set - not validated')
          else if (vsc <> nil) then
            op.warning('InstanceValidator', 'code-unknown', path, false, 'Exception opening value set '+vsc.id+' for '+context.Binding.name+': '+e.Message)
          else
            op.warning('InstanceValidator', 'code-unknown', path, false, 'Exception opening value set '+TFhirReference(binding.valueset).reference+' for '+context.Binding.name+': '+e.Message);
      end;
    end;
  end;
  // todo: check primary
end;


procedure TFHIRValidator.LoadFromDefinitions(filename: string);
var
  b : TAdvBuffer;
  m : TAdvMemoryStream;
  r : TAdvZipReader;
  i : integer;
  mem : TAdvMemoryStream;
  vcl : TVclStream;
  xml : TFHIRXmlParser;
  v : Variant;
begin

  // read the zip, loading the resources we need
  b := TAdvBuffer.create;
  try
    b.LoadFromFileName(filename);
    m := TAdvMemoryStream.create;
    try
      m.Buffer := b.Link;
      r := TAdvZipReader.create;
      try
        r.Stream := m.Link;
        r.ReadZip;
        for i := 0 to r.Parts.count - 1 do
        begin
          if StringArrayExists(['.xsd', '.xsl', '.xslt', '.sch'], ExtractFileExt(r.Parts[i].Name)) then
            FSources.add(r.Parts[i].Link)
          else if ExtractFileExt(r.Parts[i].Name) = '.xml' then
          begin
            mem := TAdvMemoryStream.create;
            try
              mem.Buffer := r.Parts[i].Link;
              vcl := TVCLStream.create;
              try
                vcl.Stream := mem.link;
                xml := TFHIRXmlParser.create('en');
                try
                  xml.source := vcl;
                  xml.Parse;
                  Load(xml.resource as TFhirBundle);
                finally
                  xml.free;
                end;
              finally
                vcl.free;
              end;
            finally
              mem.free;
            end;
          end;
        end;
      finally
        r.free;
      end;
    finally
      m.free;
    end;
  finally
    b.free;
  end;

  // prep the schemas
  v := CreateOLEObject(GMsXmlProgId_SCHEMA);
  FCache := IUnknown(TVarData(v).VDispatch) as IXMLDOMSchemaCollection;
  FCache.add('http://www.w3.org/XML/1998/namespace', loadDoc('xml.xsd'));
  FCache.add('http://www.w3.org/1999/xhtml', loadDoc('fhir-xhtml.xsd'));
  FCache.add('http://www.w3.org/2000/09/xmldsig#', loadDoc('xmldsig-core-schema.xsd'));
  FCache.add('http://hl7.org/fhir', loadDoc('fhir-single.xsd'));
end;

procedure TFHIRValidator.Load(feed: TFHIRBundle);
var
  i : integer;
  r : TFhirResource;
  p : TFHirStructureDefinition;
  base : String;
begin
  for i := 0 to feed.entryList.count - 1 do
  begin
    if feed.entryList[i].base <> '' then
      base := feed.entryList[i].base
    else
      base := feed.base;

    r := feed.entryList[i].resource;
    if r is TFHirStructureDefinition then
    begin
      p := r as TFHirStructureDefinition;
      if FProfiles <> nil then
        FProfiles.SeeProfile(base, i, p);
      if (p.type_ = StructureDefinitionTypeType) or (p.type_ = StructureDefinitionTypeResource)  then
        FTypes.add(LowerCase(p.snapshot.elementList[0].path), p.link);
    end
    else if (r.ResourceType in [frtValueSet, frtConceptMap]) then
      FTerminologyServer.SeeSpecificationResource(fullResourceUri(base, r.ResourceType, r.id), r);
  end;
end;

{
procedure TFHIRValidator.loadSchema(buffer : TAdvBuffer);
var
  LSchema: Variant;
begin
  LSchema := CreateOLEObject(GMsXmlProgId_FTDOM);
  LSchema.async := False;
  LSchema.loadXML(BytesAsAnsiString(ASource));
  FSchemas.add(LSchema.documentElement, LSchema);
end
}

function TrimBof(const s : String):String;
begin
  result := s;
  while (result[1] <> '<') do
    delete(result, 1, 1);
end;

function TFHIRValidator.LoadDoc(name : String; isFree : boolean) : IXMLDomDocument2;
Var
  LVariant: Variant;
  buf : TAdvNameBuffer;
Begin
  buf := FSources.GetByName(name);
  LVariant := LoadMsXMLDomV(isfree);
  Result := IUnknown(TVarData(LVariant).VDispatch) as IXMLDomDocument2;
  result.async := false;
  if isFree then
    result.resolveExternals := true;
  if not result.loadXML(TrimBof(buf.AsUnicode)) then
    raise Exception.create('unable to parse XML because '+result.parseError.reason);
end;


{
function TFHIRValidator.LoadFile(name : String; isFree : boolean) : IXMLDomDocument2;
Var
  LVariant: Variant;
Begin
  LVariant := LoadMsXMLDomV(isfree);
  Result := IUnknown(TVarData(LVariant).VDispatch) as IXMLDomDocument2;
  result.async := false;
  if isFree then
    result.resolveExternals := true;
  if not result.load(name) then
    raise Exception.create('unable to parse XML because '+result.parseError.reason);
end;
}

function TFHIRValidator.validateInstance(context : TFHIRValidatorContext; source: TAdvBuffer; opDesc : String; profile : TFHirStructureDefinition): TFHIROperationOutcome;
var
  dom : TIdSoapMSXmlDom;
  procedure load;
  var
    mem : TAdvMemoryStream;
    vcl : TVCLStream;
  begin
    mem := TAdvMemoryStream.create;
    try
      mem.Buffer := source.Link;
      vcl := TVCLStream.create;
      try
        vcl.Stream := mem.Link;
        dom.Read(vcl);
      finally
        vcl.free;
      end;
    finally
      mem.free;
    end;
  end;
begin
  result := TFhirOperationOutcome.create;
  try
    // 1: load with schema validation

    dom := TIdSoapMSXmlDom.create;
    try
      dom.Schemas := FCache;
      load;
      if dom.Root = nil then
      begin
        result.error('Schema', 'invalid', 'line '+inttostr(dom.ParseError.line)+', Col '+inttostr(dom.ParseError.linepos), false, dom.ParseError.reason);
        dom.schemas := nil;
        try
          load;
        except
          result.issueList[0].severity := IssueSeverityFatal;
        end;
      end;

      if dom.Root <> nil then
      begin
        // well, we can actually load XML. try the schematrons
        executeSchematron(context, result, source.AsUnicode, lowercase(dom.Root.Name)+'.sch');
        validateInstance(result, dom.root, profile);
      end;
    finally
      dom.free;
    end;
    BuildNarrative(result, opDesc);
    result.Link;
  finally
    result.free;
  end;
end;

procedure TFHIRValidator.validateInstance(op: TFHIROperationOutcome; elem: TIdSoapXmlElement; specifiedprofile : TFHirStructureDefinition);
var
  ci : TChildIterator;
begin
  if (elem.Name = 'feed') then
  begin
    ci := TChildIterator.create('', elem);
    try
      while (ci.next()) do
      begin
        if (ci.name = 'category') then
          validateTag(ci.path, ci.element, false)
        else if (ci.name = 'entry') then
          validateAtomEntry(op, ci.path, ci.element, specifiedprofile);
      end;
    finally
      ci.free;
    end;
  end
  else
    validate(op, '', elem, specifiedprofile, elem.Name = 'Bundle');
end;

constructor TFHIRValidator.Create;
begin
  inherited;
  FTypes := TAdvStringObjectMatch.create;
  FTypes.PreventDuplicates;
  FSources := TAdvNameBufferList.create;
  FChecks := TAdvStringObjectMatch.create;
  FChecks.PreventDuplicates;
end;

destructor TFHIRValidator.Destroy;
begin
  FProfiles.Free;
  FSources.Free;
  FChecks.Free;
  FTypes.Free;
  FTerminologyServer.Free;
  inherited;
end;


procedure TFHIRValidator.processSchematron(op : TFhirOperationOutcome; source : String);
var
  e : IXMLDOMElement;
  issue : TFhirOperationOutcomeIssue;
  ex : TFhirExtension;
  v : Variant;
  doc : IXMLDOMDocument2;
Begin
  v := LoadMsXMLDomV(false);
  doc := IUnknown(TVarData(v).VDispatch) as IXMLDomDocument2;
  doc.async := false;
  if not doc.loadXML(source) then
    raise Exception.create('unable to parse schematron results because '+doc.parseError.reason);

  e := TMsXmlParser.FirstChild(doc.DocumentElement);
  while (e <> nil) do
  begin
    if e.baseName = 'failed-assert' then
    begin
      issue := TFhirOperationOutcomeIssue.create;
      try
        issue.severity := IssueSeverityError;
        issue.code := TFhirCodeableConcept.Create;
        with issue.code.codingList.Append do
        begin
          system := 'http://hl7.org/fhir/issue-type';
          code := 'invariant';
        end;
        issue.details := e.text;
        issue.locationList.Append.value := e.getAttribute('location');
        ex := issue.ExtensionList.Append;
        ex.url := 'http://hl7.org/fhir/tools#issue-source';
        ex.value := TFhirCode.create;
        TFhirCode(ex.value).value := 'Schematron';
        op.issueList.add(issue.link);
      finally
        issue.free;
      end;
    end;
    e := TMsXmlParser.NextSibling(e);
  end;
end;
{
function TFHIRValidator.transform(op : TFhirOperationOutcome; source: IXMLDOMDocument2; transform: IXSLTemplate): IXMLDOMDocument2;
var
  xml: Variant;
  proc : IXSLProcessor;
  iErr : Variant;
  err : IErrorInfo;
  src : WideString;
  desc : wideString;
begin
  proc := transform.createProcessor;
  proc.Input := source;
  iErr := proc.Transform;
  result := nil;
  if (iErr <> true) then
  begin
    getErrorInfo(0, err);
    err.GetSource(src);
    err.GetDescription(desc);
    op.error('schematron', 'exception', src, false, desc);
  end
  else
  begin
    src := proc.output;
    xml := LoadMsXMLDomV;
    result := IUnknown(TVarData(xml).VDispatch) as IXMLDomDocument2;
    result.async := false;
    if not op.error('schematron', 'exception', '??', result.loadXML(src), 'Unable to parse result of transform') then
      result := nil;
  end;
end;
}

procedure TFHIRValidator.executeSchematron(context : TFHIRValidatorContext; op : TFhirOperationOutcome; source, name: String);
var
  xslt2: AltovaXMLLib_TLB.XSLT2;
  src : String;
  app : AltovaXMLLib_TLB.Application;
begin
  if context <> nil then
    app := context.FxmlApp
  else
    app := AltovaXMLLib_TLB.CoApplication.Create;

  xslt2 := App.XSLT2;
  src := FSources.GetByName(name).AsUnicode;
  xslt2.InputXMLFromText := src;
  xslt2.XSLFileName := IncludeTrailingPathDelimiter(FSchematronSource)+'iso_svrl_for_xslt2.xsl';
  src := xslt2.ExecuteAndGetResultAsString;
  xslt2 := App.XSLT2;
  xslt2.InputXMLFromText := source;
  xslt2.XSLFromText := src;
  processSchematron(op, xslt2.ExecuteAndGetResultAsString);
end;

function TFHIRValidator.resolveBindingReference(ref: TFHIRType): TValueSetChecker;
begin
  if (ref is TFHIRUri) then
    result := FTerminologyServer.MakeChecker(TFHIRUri(ref).value)
  else if (ref is TFhirReference) then
    result := FTerminologyServer.MakeChecker(TFhirReference(ref).reference)
  else
    result := nil;
end;

procedure TFHIRValidator.SetProfiles(const Value: TProfileManager);
begin
  FProfiles.Free;
  FProfiles := Value;
end;

procedure TFHIRValidator.SetTerminologyServer(const Value: TTerminologyServer);
begin
  FTerminologyServer.Free;
  FTerminologyServer := Value;
end;

function TFHIRValidator.describeReference(ref: TFHIRType): String;
begin
  if (ref is TFHIRUri) then
    result := TFHIRUri(ref).value
  else if (ref is TFhirReference) then
    result := TFhirReference(ref).reference
  else
    result := '??';
end;

function TFHIRValidator.codeInExpansion(list: TFHIRValueSetExpansionContainsList; system, code: string): Boolean;
var
  i : integer;
  c : TFHIRValueSetExpansionContains;
begin
  result := false;
  for i := 0 to list.count - 1 do
  begin
    c := list[i];
    if (code = c.Code) and (system = c.System) or codeinExpansion(c.containsList, system, code) then
    begin
      result := true;
      exit;
    end;
  end;
end;


function TFHIRValidator.AcquireContext: TFHIRValidatorContext;
begin
  result := TFHIRValidatorContext.create;
  try
    result.FxmlApp := AltovaXMLLib_TLB.CoApplication.Create;
    result.link;
  finally
    result.free;
  end;
end;

procedure TFHIRValidator.YieldContext(context: TFHIRValidatorContext);
begin
  try
    context.FxmlApp := nil;
  finally
    context.free;
  end;
end;

end.


