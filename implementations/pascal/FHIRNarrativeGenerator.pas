unit FHIRNarrativeGenerator;


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

uses
  SysUtils, Generics.Collections, EncdDecd,
  StringSupport, EncodeSupport,
  AdvObjects, AdvGenerics,
  FHIRBase, FHIRResources, FHIRTypes, FHIRConstants, FHIRProfileUtilities, FHIRUtilities;

Const
  BooleanStrings : array [boolean] of String = ('false', 'true');
Type
  TBaseWrapper = class;

  TPropertyWrapper = { abstract } class(TAdvObject)
  public
    function getOwner(): TFHIRObject; virtual;
    function getName(): String; virtual;
    function hasValues(): boolean; virtual;
    function getValues(): TAdvList<TBaseWrapper>; virtual;
    function getTypeCode(): String; virtual;
    function getDefinition(): String; virtual;
    function getMinCardinality(): Integer; virtual;
    function getMaxCardinality(): Integer; virtual;
    function getStructure(): TFHIRStructureDefinition; virtual;
  end;

  TResourceWrapper = { abstract } class(TAdvObject)
  public
    function getContained(): TAdvList<TResourceWrapper>; virtual;
    function getId(): String; virtual;
    function getNarrative(): TFHIRXhtmlNode; virtual;
    function getName(): String; virtual;
    function children(): TAdvList<TPropertyWrapper>; virtual;
  end;

  TBaseWrapper = { abstract } class(TAdvObject)
  public
    function getBase(): TFHIRObject; virtual;
    function children(): TAdvList<TPropertyWrapper>; virtual;
    function getChildByName(tail: String): TPropertyWrapper; virtual;
  end;

  (*
    TBaseWrapperElement = class (TBaseWrapper)
    private
    FElement : TIdSoapXmlElement;
    FType : String;
    FStructure : TFHIRStructureDefinition;
    FDefinition : TFHIRElementDefinition;
    FChildren : TAdvList<TFHIRElementDefinition>;
    FList : TAdvList<TPropertyWrapper>;
    public
    Constructor create(element : TIdSoapXmlElement; type_ : String; structure : TFHIRStructureDefinition; definition : TFHIRElementDefinition);
    end;
  *)

  TResourceWrapperWithReference = record
    reference: String;
    resource: TResourceWrapper;
  end;

  TPropertyWrapperDirect = class(TPropertyWrapper)
  private
    FWrapped: TFHIRProperty;
    FList: TAdvList<TBaseWrapper>;
  public
    Constructor create(wrapped: TFHIRProperty);
    function getOwner(): TFHIRObject; override;
    function getName(): String; override;
    function hasValues(): boolean; override;
    function getValues(): TAdvList<TBaseWrapper>; override;
    function getTypeCode(): String; override;
    function getDefinition(): String; override;
    function getMinCardinality(): Integer; override;
    function getMaxCardinality(): Integer; override;
    function getStructure(): TFHIRStructureDefinition; override;
  end;

  TResourceWrapperDirect = class(TResourceWrapper)
  private
    FWrapped: TFHIRResource;
  public
    Constructor create(wrapped: TFHIRResource);
    function getContained(): TAdvList<TResourceWrapper>; override;
    function getId(): String; override;
    function getNarrative(): TFHIRXhtmlNode; override;
    function getName(): String; override;
    function children(): TAdvList<TPropertyWrapper>; override;
  end;

  TBaseWrapperDirect = class(TBaseWrapper)
  private
    FWrapped: TFHIRObject;
    FList: TAdvList<TPropertyWrapper>;
  public
    Constructor create(wrapped: TFHIRObject);
    function getBase(): TFHIRObject; override;
    function children(): TAdvList<TPropertyWrapper>; override;
    function getChildByName(tail: String): TPropertyWrapper; override;
  end;

  TFHIRNarrativeGenerator = class(TAdvObject)
  private
    FPrefix: String; // for links
    context: TValidatorServiceProvider;
    FBasePath: String;
    FTooCostlyNote: String;
    function describeSystem(system: TFHIRContactPointSystem): String; overload;
    function describeSystem(system: String): String; overload;
    function displayTimeUnits(units: TFHIRUnitsOfTime): String;
    function displayEventCode(when: TFHIREventTiming): String;

    function displayCodeableConcept(cc: TFHIRCodeableConcept): String;
    function displayIdentifier(ii: TFHIRIdentifier): String;
    function displayTiming(t: TFHIRTiming): String;
    function displayQuantity(q: TFHIRQuantity): String;
    function displayHumanName(name: TFHIRHumanName): String;
    function displayAddress(address: TFHIRAddress): String;
    function displayContactPoint(contact: TFHIRContactPoint): String;
    function displaySampledData(sd: TFHIRSampledData): String;
    procedure renderCodeableConcept(cc: TFHIRCodeableConcept; x: TFHIRXhtmlNode; showCodeDetails: boolean);
    procedure renderCoding(c: TFHIRCoding; x: TFHIRXhtmlNode; showCodeDetails: boolean);
    procedure renderQuantity(q: TFHIRQuantity; x: TFHIRXhtmlNode; showCodeDetails: boolean);
    procedure renderAnnotation(o: TFHIRAnnotation; x: TFHIRXhtmlNode; showCodeDetails: boolean);
    procedure renderIdentifier(ii: TFHIRIdentifier; x: TFHIRXhtmlNode);
    procedure renderHumanName(name: TFHIRHumanName; x: TFHIRXhtmlNode);
    procedure renderSampledData(o: TFHIRSampledData; x: TFHIRXhtmlNode);
    procedure renderAddress(address: TFHIRAddress; x: TFHIRXhtmlNode);
    procedure renderContactPoint(contact: TFHIRContactPoint; x: TFHIRXhtmlNode);
    procedure renderUri(uri: TFHIRUri; x: TFHIRXhtmlNode);
    procedure renderTiming(s: TFHIRTiming; x: TFHIRXhtmlNode);
    procedure renderRange(q: TFHIRRange; x: TFHIRXhtmlNode);

    function lookupCode(system, code: String): String;

    function getChildrenForPath(elements: TFHIRElementDefinitionList; path: String): TAdvList<TFHIRElementDefinition>;
    function splitExtensions(profile: TFHIRStructureDefinition; children: TAdvList<TPropertyWrapper>): TAdvList<TPropertyWrapper>;
    Function getElementDefinition(elements: TAdvList<TFHIRElementDefinition>; path: String; p: TPropertyWrapper): TFHIRElementDefinition; overload;
    Function getElementDefinition(elements: TFHIRElementDefinitionList; path: String; p: TPropertyWrapper): TFHIRElementDefinition; overload;
    Function exemptFromRendering(child: TFHIRElementDefinition): boolean;
    procedure filterGrandChildren(grandChildren: TAdvList<TFHIRElementDefinition>; s: String; prop: TPropertyWrapper);
    function isBase(code: String): boolean;
    function isPrimitive(e: TFHIRElementDefinition): boolean;
    function isDefaultValue(displayHints: TDictionary<String, String>; list: TAdvList<TBaseWrapper>): boolean; overload;
    function isDefault(displayHints: TDictionary<String, String>; primitiveType: TFHIRPrimitiveType): boolean; overload;
    Function renderAsList(child: TFHIRElementDefinition): boolean;
    function canDoTable(path: String; p: TPropertyWrapper; grandChildren: TAdvList<TFHIRElementDefinition>): boolean;
    procedure addColumnHeadings(tr: TFHIRXhtmlNode; grandChildren: TAdvList<TFHIRElementDefinition>);
    procedure addColumnValues(res: TResourceWrapper; tr: TFHIRXhtmlNode; grandChildren: TAdvList<TFHIRElementDefinition>; v: TBaseWrapper; showCodeDetails: boolean;
      displayHints: TDictionary<String, String>);
    function getValues(path: String; p: TPropertyWrapper; e: TFHIRElementDefinition): TAdvList<TPropertyWrapper>;
    function canCollapse(e: TFHIRElementDefinition): boolean;
    Function resolveReference(res: TResourceWrapper; url: String): TResourceWrapperWithReference;
    procedure generateResourceSummary(x: TFHIRXhtmlNode; res: TResourceWrapper; textAlready: boolean; showCodeDetails: boolean);
    function displayLeaf(res: TResourceWrapper; ew: TBaseWrapper; defn: TFHIRElementDefinition; x: TFHIRXhtmlNode; name: String; showCodeDetails: boolean): boolean;
    function includeInSummary(child: TFHIRElementDefinition): boolean;

    procedure generate(cm: TFHIRConceptMap); overload;
    procedure generate(vs: TFHIRValueSet; b: boolean); overload;
    procedure generate(oo: TFHIROperationOutcome); overload;
    procedure generate(conf: TFHIRConformance); overload;
    procedure generate(od: TFHIROperationDefinition); overload;

    procedure renderLeaf(res: TResourceWrapper; ew: TBaseWrapper; defn: TFHIRElementDefinition; x: TFHIRXhtmlNode; title: boolean; showCodeDetails: boolean;
      displayHints: TDictionary<String, String>);
    function readDisplayHints(defn: TFHIRElementDefinition): TDictionary<String, String>;

    procedure generateByProfile(res: TResourceWrapper; profile: TFHIRStructureDefinition; e: TBaseWrapper; allElements: TFHIRElementDefinitionList; defn: TFHIRElementDefinition;
      children: TAdvList<TFHIRElementDefinition>; x: TFHIRXhtmlNode; path: String; showCodeDetails: boolean); overload;
    procedure generateByProfile(res: TFHIRResource; profile: TFHIRStructureDefinition; e: TFHIRObject; allElements: TFHIRElementDefinitionList; defn: TFHIRElementDefinition;
      children: TAdvList<TFHIRElementDefinition>; x: TFHIRXhtmlNode; path: String; showCodeDetails: boolean); overload;
    procedure generateByProfile(r: TFHIRDomainResource; profile: TFHIRStructureDefinition; showCodeDetails: boolean); overload;
    procedure inject(r: TFHIRDomainResource; x: TFHIRXhtmlNode; status: TFhirNarrativeStatus);
  public
    Constructor create(cc: TValidatorServiceProvider);
    Destructor destroy; override;
    procedure generate(r: TFHIRDomainResource); overload;

    property Prefix: String read FPrefix write FPrefix; // for links
    property BasePath: String read FBasePath write FBasePath;
    property TooCostlyNote: String read FTooCostlyNote write FTooCostlyNote;
  end;

implementation

function tail(path: String): String;
begin
  result := path.substring(path.lastIndexOf('.') + 1);
end;

Constructor TPropertyWrapperDirect.create(wrapped: TFHIRProperty);
begin
  inherited create;
  if (wrapped = nil) then
    raise Exception.create('wrapped = nil');
  self.FWrapped := wrapped;
end;

function TPropertyWrapperDirect.getName(): String;
begin
  result := FWrapped.name;
end;

function TPropertyWrapperDirect.getOwner: TFHIRObject;
begin
  result := nil;
end;

function TPropertyWrapperDirect.hasValues(): boolean;
begin
  result := FWrapped.hasValue;
end;

function TPropertyWrapperDirect.getValues(): TAdvList<TBaseWrapper>;
var
  b: TFHIRObject;
begin
  if (FList = nil) then
  begin
    FList := TAdvList<TBaseWrapper>.create();
    for b in FWrapped.list do
      if b = nil then
        FList.add(nil)
      else
        FList.add(TBaseWrapperDirect.create(b));
  end;
  result := FList;
end;

function TPropertyWrapperDirect.getTypeCode(): String;
begin
  result := FWrapped.Type_;
end;

function TPropertyWrapperDirect.getDefinition(): String;
begin
  result := 'todo'; // FWrapped.Definition;
end;

function TPropertyWrapperDirect.getMinCardinality(): Integer;
begin
  result := 0; // FWrapped.minCard;
end;

function TPropertyWrapperDirect.getMaxCardinality(): Integer;
begin
  result := 1; // FWrapped.getMinCardinality();
end;

function TPropertyWrapperDirect.getStructure(): TFHIRStructureDefinition;
begin
  result := nil; // FWrapped.getStructure();
end;

Constructor TBaseWrapperDirect.create(wrapped: TFHIRObject);
begin
  inherited create;
  if (wrapped = nil) then
    raise Exception.create('wrapped = nil');
  self.FWrapped := wrapped;
end;

function TBaseWrapperDirect.getBase(): TFHIRObject;
begin
  result := FWrapped;
end;

function TBaseWrapperDirect.children(): TAdvList<TPropertyWrapper>;
var
  p: TFHIRProperty;
  list: TFHIRPropertyList;
begin
  if (FList = nil) then
  begin
    FList := TAdvList<TPropertyWrapper>.create();
    list := FWrapped.createPropertyList(false);
    try
      for p in list do
        FList.add(TPropertyWrapperDirect.create(p.Link as TFHIRProperty));
    finally
      list.Free;
    end;
  end;
  result := FList;
end;

function TBaseWrapperDirect.getChildByName(tail: String): TPropertyWrapper;
var
  p, pl: TFHIRProperty;
  list: TFHIRPropertyList;
begin
  pl := nil;
  if (FList = nil) then
  begin
    FList := TAdvList<TPropertyWrapper>.create();
    list := FWrapped.createPropertyList(false);
    try
      for p in list do
        if (p.name = tail) then
          pl := p;
    finally
      list.Free;
    end;
  end;
  if (pl = nil) then
    result := nil
  else
    result := TPropertyWrapperDirect.create(pl)
end;

Constructor TResourceWrapperDirect.create(wrapped: TFHIRResource);
begin
  inherited create;
  if (wrapped = nil) then
    raise Exception.create('wrapped = nil');
  self.FWrapped := wrapped;
end;

function TResourceWrapperDirect.getContained(): TAdvList<TResourceWrapper>;
var
  dr: TFHIRDomainResource;
  c: TFHIRResource;
  list: TAdvList<TResourceWrapper>;
begin
  list := TAdvList<TResourceWrapper>.create();
  try
    if (FWrapped is TFHIRDomainResource) then
    begin
      dr := TFHIRDomainResource(FWrapped);
      for c in dr.containedList do
        list.add(TResourceWrapperDirect.create(c));
    end;
    result := list.Link;
  finally
    result.Free;
  end;
end;

function TResourceWrapperDirect.getId(): String;
begin
  result := FWrapped.Id;
end;

function TResourceWrapperDirect.getNarrative(): TFHIRXhtmlNode;
var
  dr: TFHIRDomainResource;
begin
  result := nil;
  if (FWrapped is TFHIRDomainResource) then
  begin
    dr := TFHIRDomainResource(FWrapped);
    if (dr.Text <> nil) and (dr.Text.div_ <> nil) then
      result := dr.Text.div_;
  end;
end;

function TResourceWrapperDirect.getName(): String;
begin
  result := CODES_TFHIRREsourceType[FWrapped.ResourceType];
end;

function TResourceWrapperDirect.children(): TAdvList<TPropertyWrapper>;
var
  p: TFHIRProperty;
  list: TAdvList<TPropertyWrapper>;
  pList: TFHIRPropertyList;
begin
  pList := FWrapped.createPropertyList(false);
  try
    list := TAdvList<TPropertyWrapper>.create();
    try
      for p in pList do
        list.add(TPropertyWrapperDirect.create(p));
      result := list.Link;
    finally
      result.Free;
    end;
  finally
    pList.Free;
  end;
end;

{ TFHIRNarrativeGenerator }
procedure TFHIRNarrativeGenerator.generate(r: TFHIRDomainResource);
var
  p: TFHIRStructureDefinition;
  pu: TFHIRUri;
begin
  if (r is TFHIRConceptMap) then
    generate(TFHIRConceptMap(r))
  else if (r is TFHIRValueSet) then
    generate(TFHIRValueSet(r), true)
  else if (r is TFHIROperationOutcome) then
    generate(TFHIROperationOutcome(r))
  else if (r is TFHIRConformance) then
    generate(TFHIRConformance(r))
  else if (r is TFHIROperationDefinition) then
    generate(TFHIROperationDefinition(r))
  else
  begin
    p := nil;
    try
    if (r.Meta <> nil) then
      for pu in r.Meta.profileList do
        if (p = nil) then
          p := context.fetchResource(frtStructureDefinition, pu.value) as TFHIRStructureDefinition;
    if (p = nil) then
      p := context.fetchResource(frtStructureDefinition, CODES_TFHIRREsourceType[r.ResourceType]) as TFHIRStructureDefinition;
    if (p = nil) then
      p := context.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/' + CODES_TFHIRREsourceType[r.ResourceType]) as TFHIRStructureDefinition;
    if (p <> nil) then
      generateByProfile(r, p, true);
    finally
      p.Free;
    end;
  end;
end;

procedure TFHIRNarrativeGenerator.generate(oo: TFHIROperationOutcome);
begin
  raise Exception.create('Not done yet');
end;

procedure TFHIRNarrativeGenerator.generate(vs: TFHIRValueSet; b: boolean);
begin
  raise Exception.create('Not done yet');
end;

procedure TFHIRNarrativeGenerator.generate(cm: TFHIRConceptMap);
begin
  raise Exception.create('Not done yet');
end;

procedure TFHIRNarrativeGenerator.generate(od: TFHIROperationDefinition);
begin
  raise Exception.create('Not done yet');
end;

procedure TFHIRNarrativeGenerator.generate(conf: TFHIRConformance);
begin
  raise Exception.create('Not done yet');
end;

procedure TFHIRNarrativeGenerator.generateByProfile(r: TFHIRDomainResource; profile: TFHIRStructureDefinition; showCodeDetails: boolean);
var
  x: TFHIRXhtmlNode;
  c: TAdvList<TFHIRElementDefinition>;
begin
  x := TFHIRXhtmlNode.create('div');
  if showCodeDetails then
    x.addTag('p').addTag('b').addText('Generated Narrative with Details')
  else
    x.addTag('p').addTag('b').addText('Generated Narrative');
  try
    c := getChildrenForPath(profile.snapshot.ElementList, CODES_TFHIRREsourceType[r.ResourceType]);
    try
      generateByProfile(r, profile, r, profile.snapshot.ElementList, profile.snapshot.ElementList[0], c, x, CODES_TFHIRREsourceType[r.ResourceType], showCodeDetails);
    finally
      c.Free;
    end;
  except
    on e: Exception do
    begin
      x.addTag('p').addTag('b').setAttribute('style', 'color: maroon').addText('Exception generating Narrative: ' + e.Message);
    end;
  end;
  inject(r, x, NarrativeStatusGENERATED);
end;

procedure TFHIRNarrativeGenerator.generateByProfile(res: TFHIRResource; profile: TFHIRStructureDefinition; e: TFHIRObject; allElements: TFHIRElementDefinitionList;
  defn: TFHIRElementDefinition; children: TAdvList<TFHIRElementDefinition>; x: TFHIRXhtmlNode; path: String; showCodeDetails: boolean);
var
  r: TResourceWrapperDirect;
begin
  r := TResourceWrapperDirect.create(res);
  try
    generateByProfile(r, profile, TBaseWrapperDirect.create(e), allElements, defn, children, x, path, showCodeDetails);
  finally
    r.Free
  end;
end;

function camelCase(s: String): String;
begin

end;

function pluralizeMe(s: String): String;
begin

end;

procedure TFHIRNarrativeGenerator.generateByProfile(res: TResourceWrapper; profile: TFHIRStructureDefinition; e: TBaseWrapper; allElements: TFHIRElementDefinitionList;
  defn: TFHIRElementDefinition; children: TAdvList<TFHIRElementDefinition>; x: TFHIRXhtmlNode; path: String; showCodeDetails: boolean);
var
  p: TPropertyWrapper;
  pList: TAdvList<TPropertyWrapper>;
  child: TFHIRElementDefinition;
  displayHints: TDictionary<String, String>;
  grandChildren: TAdvList<TFHIRElementDefinition>;
  para, list, tbl, tr, bq: TFHIRXhtmlNode;
  name: String;
  v: TBaseWrapper;
  first: boolean;
begin
  if (children.Empty) then
  begin
    displayHints := readDisplayHints(defn);
    try
      renderLeaf(res, e, defn, x, false, showCodeDetails, displayHints);
    finally
      displayHints.Free;
    end;
  end
  else
  begin
    pList := splitExtensions(profile, e.children);
    try
      for p in pList do
      begin
        if (p.hasValues) then
        begin
          child := getElementDefinition(children, path + '.' + p.getName(), p);
          if (child <> nil) then
          begin
            displayHints := readDisplayHints(child);
            try
              if (not exemptFromRendering(child)) then
              begin
                grandChildren := getChildrenForPath(allElements, path + '.' + p.getName());
                try
                  filterGrandChildren(grandChildren, path + '.' + p.getName(), p);
                  if (p.getValues().Count > 0) and (child <> nil) then
                  begin
                    if (isPrimitive(child)) then
                    begin
                      para := x.addTag('p');
                      name := p.getName();
                      if (name.endsWith('[x]')) then
                        name := name.substring(0, name.length - 3);
                      if (showCodeDetails) or (not isDefaultValue(displayHints, p.getValues())) then
                      begin
                        para.addTag('b').addText(name);
                        para.addText(': ');
                        if (renderAsList(child)) and (p.getValues().Count > 1) then
                        begin
                          list := x.addTag('ul');
                          for v in p.getValues() do
                            renderLeaf(res, v, child, list.addTag('li'), false, showCodeDetails, displayHints);
                        end
                        else
                        begin
                          first := true;
                          for v in p.getValues() do
                            if (first) then
                              first := false
                            else
                              para.addText(', ');
                          renderLeaf(res, v, child, para, false, showCodeDetails, displayHints);
                        end;
                      end;
                    end;
                  end
                  else if (canDoTable(path, p, grandChildren)) then
                  begin
                    x.addTag('h3').addText(capitalize(camelCase(pluralizeMe(p.getName()))));
                    tbl := x.addTag('table').setAttribute('class', 'grid');
                    tr := tbl.addTag('tr');
                    tr.addTag('td').addText('-'); // work around problem with empty table rows
                    addColumnHeadings(tr, grandChildren);
                    for v in p.getValues() do
                    begin
                      if (v <> nil) then
                      begin
                        tr := tbl.addTag('tr');
                        tr.addTag('td').addText('*'); // work around problem with empty table rows
                        addColumnValues(res, tr, grandChildren, v, showCodeDetails, displayHints);
                      end;
                    end;
                  end
                  else
                  begin
                    for v in p.getValues() do
                    begin
                      if (v <> nil) then
                      begin
                        bq := x.addTag('blockquote');
                        bq.addTag('p').addTag('b').addText(p.getName());
                        generateByProfile(res, profile, v, allElements, child, grandChildren, bq, path + '.' + p.getName(), showCodeDetails);
                      end;
                    end;
                  end;
                finally
                  grandChildren.free;
                end;
              end;
            finally
              displayHints.Free;
            end;
          end;
        end;
      end;
    finally
      pList.Free;
    end;

  end;
end;

procedure TFHIRNarrativeGenerator.filterGrandChildren(grandChildren: TAdvList<TFHIRElementDefinition>; s: String; prop: TPropertyWrapper);
var
  toRemove: TAdvList<TFHIRElementDefinition>;
  b: TBaseWrapper;
  list: TAdvList<TFHIRElementDefinition>;
  ed: TFHIRElementDefinition;
  p: TPropertyWrapper;
begin
  toRemove := TAdvList<TFHIRElementDefinition>.create();
  try
    toRemove.addAll(grandChildren);
    for b in prop.getValues do
    begin
      list := TAdvList<TFHIRElementDefinition>.create;
      try
        for ed in toRemove do
        begin
          p := b.getChildByName(tail(ed.path));
          if (p <> nil) and (p.hasValues()) then
            list.add(ed.Link);
        end;
        toRemove.removeAll(list);
      finally
        list.Free;
      end;
    end;
    grandChildren.removeAll(toRemove);
  finally
    toRemove.Free;
  end;
end;

function TFHIRNarrativeGenerator.splitExtensions(profile: TFHIRStructureDefinition; children: TAdvList<TPropertyWrapper>): TAdvList<TPropertyWrapper>;
var
  results: TAdvList<TPropertyWrapper>;
  map: TAdvMap<TPropertyWrapper>;
  p, pe: TPropertyWrapper;
  v: TBaseWrapper;
  ex: TFHIRExtension;
  url: String;
  ed: TFHIRStructureDefinition;
  def: TFHIRElementDefinition;
begin
  results := TAdvList<TPropertyWrapper>.create;
  try
    map := TAdvMap<TPropertyWrapper>.create;
    try
      for p in children do
        if (p.getName() = 'extension') or (p.getName() = 'modifierExtension') then
        begin
          // we're going to split these up, and create a property for each url
          if (p.hasValues()) then
          begin
            for v in p.getValues() do
            begin
              ex := TFHIRExtension(v.getBase());
              url := ex.url;
              ed := context.fetchResource(frtStructureDefinition, url) as TFHIRStructureDefinition;
              try
              if (p.getName() = 'modifierExtension') and (ed = nil) then
                raise Exception.create('Unknown modifier extension ' + url);
              pe := map[p.getName() + '[' + url + ']'];
              if (pe = nil) then
              begin
                if (ed = nil) then
                begin
                  if (url.startsWith('http://hl7.org/fhir')) then
                    raise Exception.create('unknown extension ' + url);
                  // writeln('unknown extension '+url);
                  pe := TPropertyWrapperDirect.create(TFHIRProperty.create(p.getOwner(), p.getName() + '[' + url + ']', p.getTypeCode(), TFHIRExtension,
                    { p.getDefinition(), p.getMinCardinality(), p.getMaxCardinality(), } ex));
                end
                else
                begin
                  def := ed.snapshot.ElementList[0];
                  pe := TPropertyWrapperDirect.create(TFHIRProperty.create(p.getOwner(), p.getName() + '[' + url + ']', 'Extension', TFHIRExtension,
                    { def.getDefinition(), def.getMin(), def.getMax().equals('*') ? Integer.MAX_VALUE : Integer.parseInt(def.getMax()), } ex));
                  // TPropertyWrapperDirect(pe).Fwrapped.Structure := ed;
                end;
                results.add(pe);
              end
              else
                pe.getValues().add(v);
              finally
                ed.Free;
              end;
            end;
          end;
        end
        else
          results.add(p);
    finally
      map.Free;
    end;
    result := results.Link;
  finally
    results.Free;
  end;
end;

function TFHIRNarrativeGenerator.isDefaultValue(displayHints: TDictionary<String, String>; list: TAdvList<TBaseWrapper>): boolean;
begin
  if (list.Count <> 1) then
    result := false
  else if (list[0].getBase() is TFHIRPrimitiveType) then
    result := isDefault(displayHints, TFHIRPrimitiveType(list[0].getBase()))
  else
    result := false;
end;

function TFHIRNarrativeGenerator.isDefault(displayHints: TDictionary<String, String>; primitiveType: TFHIRPrimitiveType): boolean;
var
  v: String;
begin
  v := primitiveType.StringValue;
  result := (v <> '') and displayHints.containsKey('default') and (v = displayHints['default']);
end;

Function TFHIRNarrativeGenerator.exemptFromRendering(child: TFHIRElementDefinition): boolean;
begin
  if (child = nil) then
    result := false
  else if ('Composition.subject' = child.path) then
    result := true
  else if ('Composition.section' = child.path) then
    result := true
  else
    result := false;
end;

Function TFHIRNarrativeGenerator.renderAsList(child: TFHIRElementDefinition): boolean;
var
  t: String;
begin
  result := false;
  if (child.type_List.Count = 1) then
  begin
    t := child.type_List[0].code;
    if (t.equals('Address')) or (t.equals('Reference')) then
      result := true;
  end
end;

procedure TFHIRNarrativeGenerator.addColumnHeadings(tr: TFHIRXhtmlNode; grandChildren: TAdvList<TFHIRElementDefinition>);
var
  e: TFHIRElementDefinition;
begin
  for e in grandChildren do
    tr.addTag('td').addTag('b').addText(capitalize(tail(e.path)));
end;

procedure TFHIRNarrativeGenerator.addColumnValues(res: TResourceWrapper; tr: TFHIRXhtmlNode; grandChildren: TAdvList<TFHIRElementDefinition>; v: TBaseWrapper;
  showCodeDetails: boolean; displayHints: TDictionary<String, String>);
var
  e: TFHIRElementDefinition;
  p: TPropertyWrapper;
begin
  for e in grandChildren do
  begin
    p := v.getChildByName(e.path.substring(e.path.lastIndexOf('.') + 1));
    if (p = nil) or (p.getValues().Count = 0) or (p.getValues()[0] = nil) then
      tr.addTag('td').addText(' ')
    else
      renderLeaf(res, p.getValues()[0], e, tr.addTag('td'), false, showCodeDetails, displayHints);
  end;
end;

function TFHIRNarrativeGenerator.canDoTable(path: String; p: TPropertyWrapper; grandChildren: TAdvList<TFHIRElementDefinition>): boolean;
var
  e: TFHIRElementDefinition;
  values: TAdvList<TPropertyWrapper>;
begin
  result := true;
  for e in grandChildren do
  begin
    values := getValues(path, p, e);
    try
      if (values.Count > 1) or (not isPrimitive(e)) or (not canCollapse(e)) then
      begin
        result := false;
        exit;
      end;
    finally
      values.Free;
    end;
  end;
end;

constructor TFHIRNarrativeGenerator.create(cc: TValidatorServiceProvider);
begin
  inherited create;
  context := cc;
end;

function TFHIRNarrativeGenerator.getValues(path: String; p: TPropertyWrapper; e: TFHIRElementDefinition): TAdvList<TPropertyWrapper>;
var
  res: TAdvList<TPropertyWrapper>;
  v: TBaseWrapper;
  g: TPropertyWrapper;
begin
  res := TAdvList<TPropertyWrapper>.create();
  try
    for v in p.getValues do
    begin
      for g in v.children() do
      begin
        if (path + '.' + p.getName() + '.' + g.getName() = e.path) then
          res.add(p);
      end;
    end;
    result := res.Link;
  finally
    res.Free;
  end;
end;

function TFHIRNarrativeGenerator.canCollapse(e: TFHIRElementDefinition): boolean;
begin
  // we can collapse any data type
  result := not e.type_List.isEmpty();
end;

function TFHIRNarrativeGenerator.isPrimitive(e: TFHIRElementDefinition): boolean;
begin
  // we can tell if e is a primitive because it has types then
  if (e.type_List.isEmpty()) then
    result := false
  else if (e.type_List.Count = 1) and (isBase(e.type_List[0].code)) then
    result := false
  else
    result := true;
end;

function TFHIRNarrativeGenerator.isBase(code: String): boolean;
begin
  result := (code = 'Element') or (code = 'BackboneElement');
end;

Function TFHIRNarrativeGenerator.getElementDefinition(elements: TAdvList<TFHIRElementDefinition>; path: String; p: TPropertyWrapper): TFHIRElementDefinition;
var
  element: TFHIRElementDefinition;
begin
  result := nil;
  for element in elements do
    if (element.path = path) then
    begin
      result := element;
      exit;
    end;
  if (path.endsWith(''']')) and (p.getStructure() <> nil) then
    result := p.getStructure().snapshot.ElementList[0]
  else
    result := nil;
end;

Function TFHIRNarrativeGenerator.getElementDefinition(elements: TFHIRElementDefinitionList; path: String; p: TPropertyWrapper): TFHIRElementDefinition;
var
  element: TFHIRElementDefinition;
begin
  result := nil;
  for element in elements do
    if (element.path = path) then
    begin
      result := element;
      exit;
    end;
  if (path.endsWith(''']')) and (p.getStructure() <> nil) then
    result := p.getStructure().snapshot.ElementList[0]
  else
    result := nil;
end;

procedure TFHIRNarrativeGenerator.renderLeaf(res: TResourceWrapper; ew: TBaseWrapper; defn: TFHIRElementDefinition; x: TFHIRXhtmlNode; title: boolean; showCodeDetails: boolean;
  displayHints: TDictionary<String, String>);
var
  e: TFHIRObject;
  p: TFHIRPeriod;
  r: TFHIRReference;
  c: TFHIRXhtmlNode;
  tr: TResourceWrapperWithReference;
begin
  if (ew = nil) then
    exit;

  e := ew.getBase();

  if (e is TFHIRString) then
    x.addText(TFHIRString(e).value)
  else if (e is TFHIRCode) then
    x.addText(TFHIRCode(e).value)
  else if (e is TFHIRId) then
    x.addText(TFHIRId(e).value)
  else if (e is TFHIRExtension) then
    x.addText('Extensions: Todo')
  else if (e is TFHIRInstant) then
    x.addText(TFHIRInstant(e).value.AsString)
  else if (e is TFHIRDateTime) then
    x.addText(TFHIRDateTime(e).value.AsString)
    // else if (e is TFHIRBase64Binary) then
    // x.addText(EncodeBase64(TFHIRBase64Binary(e).value))
  else if (e is TFHIRDate) then
    x.addText(TFHIRDate(e).value.AsString)
  else if (e is TFHIRENum) then
    x.addText(TFHIRENum(e).value) // todo: look up a display name if there is one the
  else if (e is TFHIRBoolean) then
    x.addText(BooleanStrings[TFHIRBoolean(e).value])
  else if (e is TFHIRCodeableConcept) then
    renderCodeableConcept(TFHIRCodeableConcept(e), x, showCodeDetails)
  else if (e is TFHIRCoding) then
    renderCoding(TFHIRCoding(e), x, showCodeDetails)
  else if (e is TFHIRAnnotation) then
    renderAnnotation(TFHIRAnnotation(e), x, showCodeDetails)
  else if (e is TFHIRIdentifier) then
    renderIdentifier(TFHIRIdentifier(e), x)
  else if (e is TFHIRInteger) then
    x.addText(TFHIRInteger(e).value)
  else if (e is TFHIRDecimal) then
    x.addText(TFHIRDecimal(e).value)
  else if (e is TFHIRHumanName) then
    renderHumanName(TFHIRHumanName(e), x)
  else if (e is TFHIRSampledData) then
    renderSampledData(TFHIRSampledData(e), x)
  else if (e is TFHIRAddress) then
    renderAddress(TFHIRAddress(e), x)
  else if (e is TFHIRContactPoint) then
    renderContactPoint(TFHIRContactPoint(e), x)
  else if (e is TFHIRUri) then
    renderUri(TFHIRUri(e), x)
  else if (e is TFHIRTiming) then
    renderTiming(TFHIRTiming(e), x)
  else if (e is TFHIRRange) then
    renderRange(TFHIRRange(e), x)
  else if (e is TFHIRQuantity) then
    renderQuantity(TFHIRQuantity(e), x, showCodeDetails)
  else if (e is TFHIRRatio) then
  begin
    renderQuantity(TFHIRRatio(e).numerator, x, showCodeDetails);
    x.addText('/');
    renderQuantity(TFHIRRatio(e).denominator, x, showCodeDetails)
  end
  else if (e is TFHIRPeriod) then
  begin
    p := TFHIRPeriod(e);
    if (p.start <> nil) then
      x.addText(p.start.toString)
    else
      x.addText('??');
    x.addText(' --> ');
    if (p.end_ <> nil) then
      x.addText(p.end_.toString)
    else
      x.addText('??');
  end
  else if (e is TFHIRReference) then
  begin
    r := TFHIRReference(e);
    c := x;
    if (r.reference <> '') then
    begin
      tr := resolveReference(res, r.reference);
      if (not r.reference.startsWith('#')) then
      begin
        if (tr.reference <> '') then
          c := x.addTag('a').setAttribute('href', tr.reference)
        else
          c := x.addTag('a').setAttribute('href', r.reference);
      end;
    end;
    // what to display: if text is provided, then that. if the reference was resolved, then show the generated narrative then
    if (r.display <> '') then
    begin
      c.addText(r.display);
      if (tr.reference <> '') then
      begin
        c.addText('. Generated Summary: ');
        generateResourceSummary(c, tr.resource, true, r.reference.startsWith('#'));
      end;
    end
    else if (tr.reference <> '') then
      generateResourceSummary(c, tr.resource, r.reference.startsWith('#'), r.reference.startsWith('#'))
    else
      c.addText(r.reference);
  end
  else if (e is TFHIRResource) then
  else if (e is TFHIRElementDefinition) then
    x.addText('todo-bundle')
  else if (e <> nil) and not((e is TFHIRAttachment) or (e is TFHIRNarrative) or (e is TFHIRMeta)) then
    raise Exception.create('type ' + e.ClassName + ' not handled yet');
end;

function TFHIRNarrativeGenerator.displayLeaf(res: TResourceWrapper; ew: TBaseWrapper; defn: TFHIRElementDefinition; x: TFHIRXhtmlNode; name: String;
  showCodeDetails: boolean): boolean;
var
  e: TFHIRObject;
  displayHints: TDictionary<String, String>;
  p: TFHIRPeriod;
  r: TFHIRReference;
  tr: TResourceWrapperWithReference;
begin
  result := false;
  if (ew = nil) then
    exit;

  e := ew.getBase();
  displayHints := readDisplayHints(defn);
  try

    if (name.endsWith('[x]')) then
      name := name.substring(0, name.length - 3);

    if (not showCodeDetails) and (e is TFHIRPrimitiveType) and isDefault(displayHints, TFHIRPrimitiveType(e)) then
    begin
      result := false;
      exit;
    end;

    result := true;
    if (e is TFHIRString) then
      x.addText(name + ': ' + TFHIRString(e).value)
    else if (e is TFHIRCode) then
      x.addText(name + ': ' + TFHIRCode(e).value)
    else if (e is TFHIRId) then
      x.addText(name + ': ' + TFHIRId(e).value)
    else if (e is TFHIRDateTime) then
      x.addText(name + ': ' + TFHIRDateTime(e).value.toString)
    else if (e is TFHIRInstant) then
      x.addText(name + ': ' + TFHIRInstant(e).value.toString)
    else if (e is TFHIRExtension) then
      x.addText('Extensions: todo')
    else if (e is TFHIRDate) then
      x.addText(name + ': ' + TFHIRDate(e).value.toString)
    else if (e is TFHIRENum) then
      x.addText(TFHIRENum(e).value) // todo: look up a display name if there is one then
    else if (e is TFHIRBoolean) then
    begin
      if (TFHIRBoolean(e).value) then
        x.addText(name)
    end
    else if (e is TFHIRCodeableConcept) then
      renderCodeableConcept(TFHIRCodeableConcept(e), x, showCodeDetails)
    else if (e is TFHIRCoding) then
      renderCoding(TFHIRCoding(e), x, showCodeDetails)
    else if (e is TFHIRAnnotation) then
      renderAnnotation(TFHIRAnnotation(e), x, showCodeDetails)
    else if (e is TFHIRInteger) then
      x.addText(TFHIRInteger(e).value)
    else if (e is TFHIRDecimal) then
      x.addText(TFHIRDecimal(e).value)
    else if (e is TFHIRIdentifier) then
      renderIdentifier(TFHIRIdentifier(e), x)
    else if (e is TFHIRHumanName) then
      renderHumanName(TFHIRHumanName(e), x)
    else if (e is TFHIRSampledData) then
      renderSampledData(TFHIRSampledData(e), x)
    else if (e is TFHIRAddress) then
      renderAddress(TFHIRAddress(e), x)
    else if (e is TFHIRContactPoint) then
      renderContactPoint(TFHIRContactPoint(e), x)
    else if (e is TFHIRTiming) then
      renderTiming(TFHIRTiming(e), x)
    else if (e is TFHIRQuantity) then
      renderQuantity(TFHIRQuantity(e), x, showCodeDetails)
    else if (e is TFHIRRatio) then
    begin
      renderQuantity(TFHIRRatio(e).numerator, x, showCodeDetails);
      x.addText('/');
      renderQuantity(TFHIRRatio(e).denominator, x, showCodeDetails);
    end
    else if (e is TFHIRPeriod) then
    begin
      p := TFHIRPeriod(e);
      if (p.start <> nil) then
        x.addText(p.start.toString)
      else
        x.addText('??');
      x.addText(' --> ');
      if (p.end_ <> nil) then
        x.addText(p.end_.toString)
      else
        x.addText('??');
    end
    else if (e is TFHIRReference) then
    begin
      r := TFHIRReference(e);
      if (r.display <> '') then
        x.addText(r.display)
      else if (r.reference <> '') then
      begin
        tr := resolveReference(res, r.reference);
        if (tr.reference <> '') then
          x.addText(tr.reference)
        else
          x.addText(r.reference);
      end
      else
        x.addText('??');
    end
    else if (e is TFHIRNarrative) then
      result := false
    else if (e is TFHIRResource) then
      result := false
    else if (not(e is TFHIRAttachment)) then
      raise Exception.create('type ' + e.ClassName + ' not handled yet');

  finally
    displayHints.Free;
  end;
end;

function TFHIRNarrativeGenerator.readDisplayHints(defn: TFHIRElementDefinition): TDictionary<String, String>;
var
  displayHint, item: String;
  list, parts: TArray<String>;
begin
  result := TDictionary<String, String>.create();
  if (defn <> nil) then
  begin
    displayHint := defn.getExtensionString('http://hl7.org/fhir/StructureDefinition/structuredefinition-display-hint');
    if (displayHint <> '') then
    begin
      list := displayHint.split([';']);
      for item in list do
      begin
        parts := item.split([':']);
        if (length(parts) <> 2) then
          raise Exception.create('error reading display hint: "' + displayHint + '"');
        result.add(parts[0].trim(), parts[1].trim());
      end;
    end;
  end;
end;

// public static String displayPeriod(Period p) begin
// String s := !p.hasStart() ? '??' : p.getStartElement().toHumanDisplay();
// s := s + ' --> ';
// return s + (!p.hasEnd() ? '(ongoing)' : p.getEndElement().toHumanDisplay());
// end;

procedure TFHIRNarrativeGenerator.generateResourceSummary(x: TFHIRXhtmlNode; res: TResourceWrapper; textAlready: boolean; showCodeDetails: boolean);
var
  div_: TFHIRXhtmlNode;
  path: String;
  profile: TFHIRStructureDefinition;
  firstElement, last, first: boolean;
  p: TPropertyWrapper;
  child: TFHIRElementDefinition;
  v: TBaseWrapper;
begin
  if (not textAlready) then
  begin
    div_ := res.getNarrative();
    if (div_ <> nil) then
    begin
      if (div_.allChildrenAreText()) then
        x.ChildNodes.addAll(div_.ChildNodes)
      else if (div_.ChildNodes.Count = 1) and (div_.ChildNodes[0].allChildrenAreText()) then
        x.ChildNodes.addAll(div_.ChildNodes[0].ChildNodes);
    end;
    x.addText('Generated Summary: ');
  end;
  path := res.getName();
  profile := context.fetchResource(frtStructureDefinition, path) as TFHIRStructureDefinition;
  try
  if (profile = nil) then
    x.addText('unknown resource ' + path)
  else
  begin
    firstElement := true;
    last := false;
    for p in res.children() do
    begin
      child := getElementDefinition(profile.snapshot.ElementList, path + '.' + p.getName(), p);
      if (p.getValues().Count > 0) and (p.getValues()[0] <> nil) and (child <> nil) and (isPrimitive(child)) and (includeInSummary(child)) then
      begin
        if (firstElement) then
          firstElement := false
        else if (last) then
          x.addText('; ');
        first := true;
        last := false;
        for v in p.getValues() do
        begin
          if (first) then
            first := false
          else if (last) then
            x.addText(', ');
          last := displayLeaf(res, v, child, x, p.getName(), showCodeDetails) or (last);
        end;
      end;
    end;
  end;
  finally
    profile.Free;
  end;
end;

function TFHIRNarrativeGenerator.includeInSummary(child: TFHIRElementDefinition): boolean;
var
  t: String;
begin
  if (child.IsModifier) then
    result := true
  else if (child.MustSupport) then
    result := true
  else if (child.type_List.Count = 1) then
  begin
    t := child.type_List[0].code;
    result := not((t = 'TFHIRAddress') or (t = 'Contact') or (t = 'Reference') or (t = 'Uri'));
  end
  else
    result := true;
end;

procedure TFHIRNarrativeGenerator.inject(r: TFHIRDomainResource; x: TFHIRXhtmlNode; status: TFhirNarrativeStatus);
var
  n: TFHIRXhtmlNode;
begin
  if (r.Text = nil) or (r.Text.div_ = nil) or (r.Text.div_.ChildNodes.isEmpty) then
  begin
    r.Text := TFHIRNarrative.create;
    r.Text.div_ := x.Link;
    r.Text.status := status;
  end
  else
  begin
    n := r.Text.div_;
    n.addTag('hr');
    n.ChildNodes.addAll(x.ChildNodes);
  end;
end;

function TFHIRNarrativeGenerator.resolveReference(res: TResourceWrapper; url: String): TResourceWrapperWithReference;
var
  r: TResourceWrapper;
  ae: TFHIRResource;
begin
  result.reference := '';
  result.resource := nil;
  if (url <> '') then
  begin
    if (url.startsWith('#')) then
    begin
      for r in res.getContained() do
      begin
        if (r.getId = url.substring(1)) then
        begin
          result.reference := '';
          result.resource := r;
          exit;
        end;
      end;
      exit;
    end;
  end;

  ae := context.fetchResource(frtNull, url);
  try
  if (ae <> nil) then
  begin
    result.reference := url;
    result.resource := TResourceWrapperDirect.create(ae);
    end;
  finally
    ae.free;
  end;
end;

procedure TFHIRNarrativeGenerator.renderCodeableConcept(cc: TFHIRCodeableConcept; x: TFHIRXhtmlNode; showCodeDetails: boolean);
var
  s, sc: String;
  c: TFHIRCoding;
  sp: TFHIRXhtmlNode;
  first: boolean;
begin
  s := cc.Text;
  if (s = '') then
  begin
    for c in cc.codingList do
    begin
      if (c.display <> '') then
      begin
        s := c.display;
        break;
      end;
    end;
  end;

  if (s = '') then
  begin
    // still? ok, let's try looking it up
    for c in cc.codingList do
    begin
      if (c.code <> '') and (c.system <> '') then
      begin
        s := lookupCode(c.system, c.code);
        if s <> '' then
          break;
      end;
    end;
  end;

  if (s = '') then
  begin
    // still? ok, let's try looking it up
    if (cc.codingList.isEmpty()) then
      s := ''
    else
      s := cc.codingList[0].code;
  end;

  if (showCodeDetails) then
  begin
    x.addText(s + ' ');
    sp := x.addTag('span');
    sp.setAttribute('style', 'background: LightGoldenRodYellow ');
    sp.addText('(Details ');
    first := true;
    for c in cc.codingList do
    begin
      if (first) then
      begin
        sp.addText(': ');
        first := false;
      end
      else
        sp.addText('; ');
      if c.display <> '' then
        sp.addText('{' + describeSystem(c.system) + ' code "' + c.code + '" := "' + lookupCode(c.system, c.code) + '", given as "' + c.display + '"}')
      else
        sp.addText('{' + describeSystem(c.system) + ' code "' + c.code + '" := "' + lookupCode(c.system, c.code));
    end;
    sp.addText(')');
  end
  else
  begin
    sc := '';
    for c in cc.codingList do
    begin
      if (c.code <> '') and (c.system <> '') then
        CommaAdd(sc, '{' + c.system + ' ' + c.code + '}');

    end;

    x.addTag('span').setAttribute('title', 'Codes: ' + sc).addText(s);
  end;
end;

procedure TFHIRNarrativeGenerator.renderAnnotation(o: TFHIRAnnotation; x: TFHIRXhtmlNode; showCodeDetails: boolean);
var
  s: TStringBuilder;
begin
  s := TStringBuilder.create();
  try
    if (o.author <> nil) then
    begin
      s.append('Author: ');

      if (o.author is TFHIRReference) then
        s.append(TFHIRReference(o.author).reference);
      if (o.author is TFHIRString) then
        s.append(TFHIRString(o.author).value);
    end;

    if (o.time <> nil) then
    begin
      if (s.length > 0) then
        s.append('; ');

      s.append('Made: ').append(o.time.AsString);
    end;

    if (o.Text <> '') then
    begin
      if (s.length > 0) then
        s.append('; ');

      s.append('Annontation: ').append(o.Text);
    end;

    x.addText(s.toString());
  finally
    s.Free;
  end;
end;

procedure TFHIRNarrativeGenerator.renderCoding(c: TFHIRCoding; x: TFHIRXhtmlNode; showCodeDetails: boolean);
var
  s: String;
begin
  if (c.display <> '') then
    s := c.display;
  if (s = '') then
    s := lookupCode(c.system, c.code);

  if (s = '') then
    s := c.code;

  if (showCodeDetails) then
  begin
    x.addText(s + ' (Details: ' + describeSystem(c.system) + ' code ' + c.code + ' := "' + lookupCode(c.system, c.code) + '", stated as "' + c.display + '")');
  end
  else
    x.addTag('span').setAttribute('title', 'begin' + c.system + ' ' + c.code + 'end;').addText(s);
end;

destructor TFHIRNarrativeGenerator.destroy;
begin
  context.Free;
  inherited;
end;

function TFHIRNarrativeGenerator.describeSystem(system: String): String;
begin
  if (system = '') then
    result := '[not stated]'
  else if (system.equals('http://loinc.org')) then
    result := 'LOINC'
  else if (system.startsWith('http://snomed.info')) then
    result := 'SNOMED CT'
  else if (system.equals('http://www.nlm.nih.gov/research/umls/rxnorm')) then
    result := 'RxNorm'
  else if (system.equals('http://hl7.org/fhir/sid/icd-9')) then
    result := 'ICD-9'
  else
    result := system;
end;

function TFHIRNarrativeGenerator.lookupCode(system, code: String): String;
var
  t: TValidationResult;
begin
  t := context.validateCode(system, code, '');
  try
    if (t <> nil) and (t.display <> '') then
      result := t.display
    else
      result := code;
  finally
    t.Free;
  end;
end;

// function ConceptDefinitionComponent TFHIRNarrativeGenerator.findCode(String code, TAdvList<ConceptDefinitionComponent> list) begin
// for (ConceptDefinitionComponent t : list) begin
// if (code.equals(t.code)) then
// return t;
// ConceptDefinitionComponent c := findCode(code, t.getConcept());
// if (c <> nil) then
// return c;
// end;
// return nil;
// end;

function TFHIRNarrativeGenerator.displayCodeableConcept(cc: TFHIRCodeableConcept): String;
var
  s: String;
  c: TFHIRCoding;
begin
  s := cc.Text;
  if (s = '') then
  begin
    for c in cc.codingList do
    begin
      if (c.display <> '') then
      begin
        s := c.display;
        break;
      end;
    end;
  end;
  if (s = '') then
  begin
    // still? ok, l"s try looking it up
    for c in cc.codingList do
    begin
      if (c.code <> '') and (c.system <> '') then
      begin
        s := lookupCode(c.system, c.code);
        if (s <> '') then
          break;
      end;
    end;
  end;

  if (s = '') then
  begin
    if (cc.codingList.isEmpty()) then
      s := ''
    else
      s := cc.codingList[0].code;
  end;
  result := s;
end;

procedure TFHIRNarrativeGenerator.renderIdentifier(ii: TFHIRIdentifier; x: TFHIRXhtmlNode);
begin
  x.addText(displayIdentifier(ii));
end;

procedure TFHIRNarrativeGenerator.renderTiming(s: TFHIRTiming; x: TFHIRXhtmlNode);
begin
  x.addText(displayTiming(s));
end;

procedure TFHIRNarrativeGenerator.renderQuantity(q: TFHIRQuantity; x: TFHIRXhtmlNode; showCodeDetails: boolean);
var
  sp: TFHIRXhtmlNode;
begin
  if (q.comparator <> QuantityComparatorNull) then
    x.addText(q.comparatorElement.value);
  x.addText(q.value);
  if (q.unit_ <> '') then
    x.addText(' ' + q.unit_)
  else if (q.code <> '') then
    x.addText(' ' + q.code);
  if (showCodeDetails) and (q.code <> '') then
  begin
    sp := x.addTag('span');
    sp.setAttribute('style', 'background: LightGoldenRodYellow ');
    sp.addText(' (Details: ' + describeSystem(q.system) + ' code ' + q.code + ' := "' + lookupCode(q.system, q.code) + '")');
  end;
end;

procedure TFHIRNarrativeGenerator.renderRange(q: TFHIRRange; x: TFHIRXhtmlNode);
begin
  if (q.low <> nil) then
    x.addText(q.low.value)
  else
    x.addText('?');
  x.addText('-');
  if (q.high <> nil) then
    x.addText(q.high.value)
  else
    x.addText('?');
  if (q.low.unit_ <> '') then
    x.addText(' ' + q.low.unit_);
end;

procedure TFHIRNarrativeGenerator.renderHumanName(name: TFHIRHumanName; x: TFHIRXhtmlNode);
begin
  x.addText(displayHumanName(name));
end;

procedure TFHIRNarrativeGenerator.renderAddress(address: TFHIRAddress; x: TFHIRXhtmlNode);
begin
  x.addText(displayAddress(address));
end;

procedure TFHIRNarrativeGenerator.renderContactPoint(contact: TFHIRContactPoint; x: TFHIRXhtmlNode);
begin
  x.addText(displayContactPoint(contact));
end;

procedure TFHIRNarrativeGenerator.renderUri(uri: TFHIRUri; x: TFHIRXhtmlNode);
begin
  x.addTag('a').setAttribute('href', uri.value).addText(uri.value);
end;

procedure TFHIRNarrativeGenerator.renderSampledData(o: TFHIRSampledData; x: TFHIRXhtmlNode);
begin
  x.addText(displaySampledData(o));
end;

function TFHIRNarrativeGenerator.displaySampledData(sd: TFHIRSampledData): String;
var
  s: String;
begin
  s := '';
  if (sd.Origin <> nil) then
    CommaAdd(s, 'Origin: ' + displayQuantity(sd.Origin));

  if (sd.Period <> '') then
    CommaAdd(s, 'Period: ' + sd.Period);

  if (sd.Factor <> '') then
    CommaAdd(s, 'Factor: ' + sd.Factor);

  if (sd.LowerLimit <> '') then
    CommaAdd(s, 'Lower: ' + sd.LowerLimit);

  if (sd.UpperLimit <> '') then
    CommaAdd(s, 'Upper: ' + sd.UpperLimit);

  if (sd.Dimensions <> '') then
    CommaAdd(s, 'Dimensions: ' + sd.Dimensions);

  if (sd.Data <> '') then
    CommaAdd(s, 'Data: ' + sd.Data);

  result := s;
end;

function TFHIRNarrativeGenerator.displayQuantity(q: TFHIRQuantity): String;
var
  s: TStringBuilder;
begin
  s := TStringBuilder.create();
  try

    s.append('(system := "');
    s.append(describeSystem(q.system));
    s.append('" code ');
    s.append(q.code);
    s.append(' := "');
    s.append(lookupCode(q.system, q.code)).append('")');

    result := s.toString();
  finally
    s.Free;
  end;
end;

function TFHIRNarrativeGenerator.displayTiming(t: TFHIRTiming): String;
var
  s, sc, st: String;
  p: TFHIRDateTime;
  rep: TFhirTimingRepeat;
begin
  s := '';
  if (t.code <> nil) then
    CommaAdd(s, 'Code: ' + displayCodeableConcept(t.code));

  if (t.eventList.Count > 0) then
  begin
    sc := '';
    for p in t.eventList do
      CommaAdd(sc, p.toString);
    CommaAdd(s, 'Events: ' + sc);
  end;

  if (t.repeat_ <> nil) then
  begin
    rep := t.repeat_;
    if (rep.bounds <> nil) and (rep.bounds is TFHIRPeriod) and (TFHIRPeriod(rep.bounds).start <> nil) then
      CommaAdd(s, 'Starting ' + TFHIRPeriod(rep.bounds).start.toString());
    if (rep.Count <> '') then
      CommaAdd(s, 'Count ' + rep.Count + ' times');
    if (rep.Duration <> '') then
      CommaAdd(s, 'Duration ' + rep.Duration + displayTimeUnits(rep.periodUnits));

    if (rep.when <> EventTimingNull) then
    begin
      st := '';
      if (rep.Period <> '') then
      begin
        st := rep.Period;
        if (rep.PeriodMax <> '') then
          st := st + '-' + rep.PeriodMax;
        st := st + displayTimeUnits(rep.periodUnits);
      end;
      CommaAdd(s, 'Do ' + st + displayEventCode(rep.when));
    end
    else
    begin
      st := '';
      if (rep.Frequency = '') or ((rep.FrequencyMax = '') and (rep.Frequency = '1')) then
        st := 'Once'
      else
      begin
        st := rep.Frequency;
        if (rep.FrequencyMax <> '') then
          st := st + '-' + rep.FrequencyMax;
      end;
      if (rep.Period <> '') then
      begin
        st := st + ' per ' + rep.Period;
        if (rep.PeriodMax <> '') then
          st := st + '-' + rep.PeriodMax;
        st := st + ' ' + displayTimeUnits(rep.periodUnits);
      end;
      CommaAdd(s, 'Do ' + st);
    end;
    if (rep.bounds <> nil) and (rep.bounds is TFHIRPeriod) and (TFHIRPeriod(rep.bounds).end_ <> nil) then
      CommaAdd(s, 'Until ' + TFHIRPeriod(rep.bounds).end_.toString);
  end;
  result := sc;
end;

function TFHIRNarrativeGenerator.displayEventCode(when: TFHIREventTiming): String;
begin
  case (when) of
    EventTimingC:
      result := 'at meals';
    EventTimingCD:
      result := 'at lunch';
    EventTimingCM:
      result := 'at breakfast';
    EventTimingCV:
      result := 'at dinner';
    EventTimingAC:
      result := 'before meals';
    EventTimingACD:
      result := 'before lunch';
    EventTimingACM:
      result := 'before breakfast';
    EventTimingACV:
      result := 'before dinner';
    EventTimingHS:
      result := 'before sleeping';
    EventTimingPC:
      result := 'after meals';
    EventTimingPCD:
      result := 'after lunch';
    EventTimingPCM:
      result := 'after breakfast';
    EventTimingPCV:
      result := 'after dinner';
    EventTimingWAKE:
      result := 'after waking';
  else
    result := '??';
  end;
end;

function TFHIRNarrativeGenerator.displayTimeUnits(units: TFHIRUnitsOfTime): String;
begin
  case units of
    UnitsOfTimeA:
      result := 'years';
    UnitsOfTimeD:
      result := 'days';
    UnitsOfTimeH:
      result := 'hours';
    UnitsOfTimeMIN:
      result := 'minutes';
    UnitsOfTimeMO:
      result := 'months';
    UnitsOfTimeS:
      result := 'seconds';
    UnitsOfTimeWK:
      result := 'weeks';
  else
    result := '??';
  end;
end;

function TFHIRNarrativeGenerator.displayHumanName(name: TFHIRHumanName): String;
var
  s: TStringBuilder;
  p: TFHIRString;
begin
  s := TStringBuilder.create();
  try
    if (name.Text <> '') then
      s.append(name.Text)
    else
    begin
      for p in name.givenList do
      begin
        s.append(p.value);
        s.append(' ');
      end;
      for p in name.familyList do
      begin
        s.append(p.value);
        s.append(' ');
      end;
    end;
    if (name.Use <> NameUseNull) and (name.Use <> NameUseUSUAL) then
      s.append('(' + name.useElement.value + ')');
    result := s.toString();
  finally
    s.Free;
  end;
end;

function TFHIRNarrativeGenerator.displayAddress(address: TFHIRAddress): String;
var
  s: TStringBuilder;
  p: TFHIRString;
begin
  s := TStringBuilder.create();
  try
    if (address.Text <> '') then
      s.append(address.Text)
    else
    begin
      for p in address.lineList do
      begin
        s.append(p.value);
        s.append(' ');
      end;
      if (address.City <> '') then
      begin
        s.append(address.City);
        s.append(' ');
      end;
      if (address.State <> '') then
      begin
        s.append(address.State);
        s.append(' ');
      end;

      if (address.PostalCode <> '') then
      begin
        s.append(address.PostalCode);
        s.append(' ');
      end;

      if (address.Country <> '') then
      begin
        s.append(address.Country);
        s.append(' ');
      end;
    end;
    if (address.Use <> AddressUseNull) then
      s.append('(' + address.useElement.value + ')');
    result := s.toString();
  finally
    s.Free;
  end;
end;

function TFHIRNarrativeGenerator.displayContactPoint(contact: TFHIRContactPoint): String;
var
  s: TStringBuilder;
begin
  s := TStringBuilder.create();
  try
    s.append(describeSystem(contact.system));
    if (contact.value = '') then
      s.append('-unknown-')
    else
      s.append(contact.value);
    if (contact.Use <> ContactPointUseNull) then
      s.append('(' + contact.useElement.value + ')');
    result := s.toString();
  finally
    s.Free;
  end;
end;

function TFHIRNarrativeGenerator.describeSystem(system: TFHIRContactPointSystem): String;
begin
  case (system) of
    ContactPointSystemPhone:
      result := 'ph: ';
    ContactPointSystemFAX:
      result := 'fax: ';
  else
    result := '';
  end;
end;

function TFHIRNarrativeGenerator.displayIdentifier(ii: TFHIRIdentifier): String;
var
  s: String;
begin
  s := ii.value;
  if (s = '') then
    s := '??';

  if (ii.Type_ <> nil) then
  begin
    if (ii.Type_.Text <> '') then
      s := ii.Type_.Text + ' = ' + s
    else if (ii.Type_.codingList.Count > 0) and (ii.Type_.codingList[0].display <> '') then
      s := ii.Type_.codingList[0].display + ' = ' + s
    else if (ii.Type_.codingList.Count > 0) and (ii.Type_.codingList[0].code <> '') then
      s := lookupCode(ii.Type_.codingList[0].system, ii.Type_.codingList[0].code) + ' = ' + s;
  end;

  if (ii.Use <> IdentifierUseNull) then
    s := s + ' (' + ii.useElement.value + ')';
  result := s;
end;

function TFHIRNarrativeGenerator.getChildrenForPath(elements: TFHIRElementDefinitionList; path: String): TAdvList<TFHIRElementDefinition>;
var
  e, t, e1: TFHIRElementDefinition;
  name: String;
  results: TAdvList<TFHIRElementDefinition>;
begin
  // do we need to do a name reference substitution?
  for e in elements do
  begin
    if (e.path = path) and (e.NameReference <> '') then
    begin
      name := e.NameReference;
      t := nil;
      // now, resolve the name
      for e1 in elements do
      begin
        if (name = e1.name) then
          t := e1;
      end;
      if (t = nil) then
        raise Exception.create('Unable to resolve name reference ' + name + ' trying to resolve ' + path);
      path := t.path;
      break;
    end;
  end;

  results := TAdvList<TFHIRElementDefinition>.create();
  try
    for e in elements do
    begin
      if (e.path.startsWith(path + '.')) and not e.path.substring(path.length + 1).contains('.') then
        results.add(e.link);
    end;
    result := results.Link;
  finally
    results.Free;
  end;
end;

(* !
  public void generate(TFHIRConceptMap cm)begin
  TFHIRXhtmlNode x := new TFHIRXhtmlNode(NodeType.Element, 'div');
  x.addTag('h2').addText(cm.getName()+' ('+cm.getUrl()+')');

  TFHIRXhtmlNode p := x.addTag('p');
  p.addText('Mapping from ');
  AddVsRef(((Reference) cm.getSource())..reference, p);
  p.addText(' to ');
  AddVsRef(((Reference) cm.getTarget())..reference, p);

  p := x.addTag('p');
  if (cm.getExperimental()) then
  p.addText(Utilities.capitalize(cm.getStatus().toString())+' (not intended for production usage). ');
  else
  p.addText(Utilities.capitalize(cm.getStatus().toString())+'. ');
  p.addText('Published on '+cm.getDateElement().toHumanDisplay()+' by '+cm.getPublisher());
  if (!cm.getContact().isEmpty()) then begin
  p.addText(' (');
  boolean firsti := true;
  for (ConceptMapContactComponent ci : cm.getContact()) begin
  if (firsti) then
  firsti := false;
  else
  p.addText(', ');
  if (ci.hasName()) then
  p.addText(ci.getName()+': ');
  boolean first := true;
  for (TFHIRContactPoint c : ci.getTelecom()) begin
  if (first) then
  first := false;
  else
  p.addText(', ');
  addTelecom(p, c);
  end;
  p.addText('; ');
  end;
  p.addText(')');
  end;
  p.addText('. ');
  p.addText(cm.getCopyright());
  if (!Utilities.noString(cm.getDescription())) then
  x.addTag('p').addText(cm.getDescription());

  x.addTag('br');

  if (!cm.ElementList.isEmpty()) then begin
  SourceElementComponent cc := cm.ElementList[0];
  String src := cc.getCodeSystem();
  boolean comments := false;
  boolean ok := cc.getTarget().Count = 1;
  Map<String, HashSet<String>> sources := new HashMap<String, HashSet<String>>();
  sources.put('code', new HashSet<String>());
  Map<String, HashSet<String>> targets := new HashMap<String, HashSet<String>>();
  targets.put('code', new HashSet<String>());
  if (ok) then begin
  String dst := cc.getTarget()[0].getCodeSystem();
  for (SourceElementComponent ccl : cm.ElementList) begin
  ok := ok) and (src.equals(ccl.getCodeSystem())) and (ccl.getTarget().Count = 1) and (dst.equals(ccl.getTarget()[0].getCodeSystem())) and (ccl.getTarget()[0].getDependsOn().isEmpty()) and (ccl.getTarget()[0].getProduct().isEmpty();
  if (ccl.hasCodeSystem()) then
  sources.get('code').add(ccl.getCodeSystem());
  for (TargetElementComponent ccm : ccl.getTarget()) begin
  comments := comments) or (!Utilities.noString(ccm.getComments());
  for (OtherElementComponent d : ccm.getDependsOn()) begin
  if (!sources.containsKey(d.ElementList)) then
  sources.put(d.ElementList, new HashSet<String>());
  sources.get(d.ElementList).add(d.getCodeSystem());
  end;
  if (ccm.hasCodeSystem()) then
  targets.get('code').add(ccm.getCodeSystem());
  for (OtherElementComponent d : ccm.getProduct()) begin
  if (!targets.containsKey(d.ElementList)) then
  targets.put(d.ElementList, new HashSet<String>());
  targets.get(d.ElementList).add(d.getCodeSystem());
  end;

  end;
  end;
  end;

  String display;
  if (ok) then begin
  // simple
  TFHIRXhtmlNode tbl := x.addTag('table').setAttribute('class', 'grid');
  TFHIRXhtmlNode tr := tbl.addTag('tr');
  tr.addTag('td').addTag('b').addText('Source Code');
  tr.addTag('td').addTag('b').addText('Equivalence');
  tr.addTag('td').addTag('b').addText('Destination Code');
  if (comments) then
  tr.addTag('td').addTag('b').addText('Comments');
  for (SourceElementComponent ccl : cm.ElementList) begin
  tr := tbl.addTag('tr');
  TFHIRXhtmlNode td := tr.addTag('td');
  td.addText(ccl.code);
  display := getDisplayForConcept(ccl.getCodeSystem(), ccl.code);
  if (display <> nil) then
  td.addText(' ('+display+')');
  TargetElementComponent ccm := ccl.getTarget()[0];
  tr.addTag('td').addText(!ccm.hasEquivalence() ? '' : ccm.getEquivalence().toCode());
  td := tr.addTag('td');
  td.addText(ccm.code);
  display := getDisplayForConcept(ccm.getCodeSystem(), ccm.code);
  if (display <> nil) then
  td.addText(' ('+display+')');
  if (comments) then
  tr.addTag('td').addText(ccm.getComments());
  end;
  else begin
  TFHIRXhtmlNode tbl := x.addTag('table').setAttribute('class', 'grid');
  TFHIRXhtmlNode tr := tbl.addTag('tr');
  TFHIRXhtmlNode td;
  tr.addTag('td').setAttribute('colspan', Integer.toString(sources.Count)).addTag('b').addText('Source Concept');
  tr.addTag('td').addTag('b').addText('Equivalence');
  tr.addTag('td').setAttribute('colspan', Integer.toString(targets.Count)).addTag('b').addText('Destination Concept');
  if (comments) then
  tr.addTag('td').addTag('b').addText('Comments');
  tr := tbl.addTag('tr');
  if (sources.get('code').Count = 1) then
  tr.addTag('td').addTag('b').addText('Code '+sources.get('code').toString()+'');
  else
  tr.addTag('td').addTag('b').addText('Code');
  for (String s : sources.keySet()) begin
  if (!s.equals('code')) then begin
  if (sources.get(s).Count = 1) then
  tr.addTag('td').addTag('b').addText(getDescForConcept(s) +' '+sources.get(s).toString());
  else
  tr.addTag('td').addTag('b').addText(getDescForConcept(s));
  end;
  end;
  tr.addTag('td');
  if (targets.get('code').Count = 1) then
  tr.addTag('td').addTag('b').addText('Code '+targets.get('code').toString());
  else
  tr.addTag('td').addTag('b').addText('Code');
  for (String s : targets.keySet()) begin
  if (!s.equals('code')) then begin
  if (targets.get(s).Count = 1) then
  tr.addTag('td').addTag('b').addText(getDescForConcept(s) +' '+targets.get(s).toString()+'');
  else
  tr.addTag('td').addTag('b').addText(getDescForConcept(s));
  end;
  end;
  if (comments) then
  tr.addTag('td');

  for (SourceElementComponent ccl : cm.ElementList) begin
  tr := tbl.addTag('tr');
  td := tr.addTag('td');
  if (sources.get('code').Count = 1) then
  td.addText(ccl.code);
  else
  td.addText(ccl.getCodeSystem()+' / '+ccl.code);
  display := getDisplayForConcept(ccl.getCodeSystem(), ccl.code);
  if (display <> nil) then
  td.addText(' ('+display+')');

  TargetElementComponent ccm := ccl.getTarget()[0];
  for (String s : sources.keySet()) begin
  if (!s.equals('code')) then begin
  td := tr.addTag('td');
  td.addText(getCode(ccm.getDependsOn(), s, sources.get(s).Count <> 1));
  display := getDisplay(ccm.getDependsOn(), s);
  if (display <> nil) then
  td.addText(' ('+display+')');
  end;
  end;
  tr.addTag('td').addText(ccm.getEquivalence().toString());
  td := tr.addTag('td');
  if (targets.get('code').Count = 1) then
  td.addText(ccm.code);
  else
  td.addText(ccm.getCodeSystem()+' / '+ccm.code);
  display := getDisplayForConcept(ccm.getCodeSystem(), ccm.code);
  if (display <> nil) then
  td.addText(' ('+display+')');

  for (String s : targets.keySet()) begin
  if (!s.equals('code')) then begin
  td := tr.addTag('td');
  td.addText(getCode(ccm.getProduct(), s, targets.get(s).Count <> 1));
  display := getDisplay(ccm.getProduct(), s);
  if (display <> nil) then
  td.addText(' ('+display+')');
  end;
  end;
  if (comments) then
  tr.addTag('td').addText(ccm.getComments());
  end;
  end;
  end;

  inject(cm, x, NarrativeStatus.GENERATED);
  end;



  private void inject(DomainResource r; x : TFHIRXhtmlNode, NarrativeStatus status) begin
  if (!r.hasText()) or (!r.text.hasDiv()) or (r.text.getDiv().ChildNodes.isEmpty()) then begin
  r.setText(new Narrative());
  r.text.setDiv(x);
  r.text.setStatus(status);
  else begin
  TFHIRXhtmlNode n := r.text.getDiv();
  n.addTag('hr');
  n.ChildNodes.addAll(x.ChildNodes);
  end;
  end;

  public Element getNarrative(Element er) begin
  Element txt := XMLUtil.getNamedChild(er, 'text');
  if (txt = nil) then
  return nil;
  return XMLUtil.getNamedChild(txt, 'div');
  end;


  private void inject(Element er; x : TFHIRXhtmlNode, NarrativeStatus status) begin
  Element txt := XMLUtil.getNamedChild(er, 'text');
  if (txt = nil) then begin
  txt := er.getOwnerDocument().createElementNS(FormatUtilities.FHIR_NS, 'text');
  Element n := XMLUtil.getFirstChild(er);
  while (n <> nil) and ((n.getNodeName().equals('id')) or (n.getNodeName().equals('meta')) or (n.getNodeName().equals('implicitRules')) or (n.getNodeName().equals('language')))
  n := XMLUtil.getNextSibling(n);
  if (n = nil) then
  er.appendChild(txt);
  else
  er.insertBefore(txt, n);
  end;
  Element st := XMLUtil.getNamedChild(txt, 'status');
  if (st = nil) then begin
  st := er.getOwnerDocument().createElementNS(FormatUtilities.FHIR_NS, 'status');
  Element n := XMLUtil.getFirstChild(txt);
  if (n = nil) then
  txt.appendChild(st);
  else
  txt.insertBefore(st, n);
  end;
  st.setAttribute('value', status.toCode());
  Element div := XMLUtil.getNamedChild(txt, 'div');
  if (div = nil) then begin
  div := er.getOwnerDocument().createElementNS(FormatUtilities.XHTML_NS, 'div');
  div.setAttribute('xmlns', FormatUtilities.XHTML_NS);
  txt.appendChild(div);
  end;
  if (div.hasChildNodes()) then
  div.appendChild(er.getOwnerDocument().createElementNS(FormatUtilities.XHTML_NS, 'hr'));
  new XhtmlComposer().compose(div, x);
  end;

  private String getDisplay(TAdvList<OtherElementComponent> list, String s)begin
  for (OtherElementComponent c : list) begin
  if (s.equals(c.ElementList)) then
  return getDisplayForConcept(c.getCodeSystem(), c.code);
  end;
  return nil;
  end;

  private String getDisplayForConcept(String system, String code)begin
  if (code = nil) then
  return nil;
  ValidationResult cl := context.validateCode(system, code, nil);
  return cl = nil ? nil : cl.display;
  end;



  private String getDescForConcept(String s) begin
  if (s.startsWith('http://hl7.org/fhir/v2/element/')) then
  return 'v2 '+s.substring('http://hl7.org/fhir/v2/element/'.length());
  return s;
  end;

  private String getCode(TAdvList<OtherElementComponent> list, String s, boolean withSystem) begin
  for (OtherElementComponent c : list) begin
  if (s.equals(c.ElementList)) then
  if (withSystem) then
  return c.getCodeSystem()+' / '+c.code;
  else
  return c.code;
  end;
  return nil;
  end;

  private void addTelecom(TFHIRXhtmlNode p, TFHIRContactPoint c) begin
  if (c.system = ContactPointSystem.PHONE) then begin
  p.addText('Phone: '+c.value);
  else if (c.system = ContactPointSystem.FAX) then begin
  p.addText('Fax: '+c.value);
  else if (c.system = ContactPointSystem.EMAIL) then begin
  p.addTag('a').setAttribute('href',  'mailto:'+c.value).addText(c.value);
  else if (c.system = ContactPointSystem.OTHER) then begin
  if (c.value.length() > 30) then
  p.addTag('a').setAttribute('href', c.value).addText(c.value.substring(0, 30)+'...');
  else
  p.addTag('a').setAttribute('href', c.value).addText(c.value);
  end;
  end;

  /**
  * This generate is optimised for the FHIR build process itself in as much as it
  * generates hyperlinks in the narrative that are only going to be correct for
  * the purposes of the build. This is to be reviewed in the future.
  *
  * @param vs
  * @param codeSystems
  * n
  */
  public void generate(TFHIRValueSet vs, boolean header)begin
  generate(vs, nil, header);
  end;

  public void generate(TFHIRValueSet vs, TFHIRValueSet src, boolean header)begin
  TFHIRXhtmlNode x := new TFHIRXhtmlNode(NodeType.Element, 'div');
  if (vs.hasExpansion()) then begin
  // for now, we just accept an expansion if there is one then
  generateExpansion(x, vs, src, header);
  //    if (!vs.hasCodeSystem()) and (!vs.hasCompose()) then
  //    generateExpansion(x, vs, src, header);
  //    else
  //    raise Exception.create('Error: should not encounter value set expansion at this point');
  end;

  boolean hasExtensions := false;
  if (vs.hasCodeSystem()) then
  hasExtensions := generateDefinition(x, vs, header);
  if (vs.hasCompose()) then
  hasExtensions := generateComposition(x, vs, header)) or (hasExtensions;
  inject(vs, x, hasExtensions ? NarrativeStatus.EXTENSIONS :  NarrativeStatus.GENERATED);
  end;

  private Integer countMembership(TFHIRValueSet vs) begin
  Integer count := 0;
  if (vs.hasExpansion()) then
  count := count + conceptCount(vs.getExpansion().getContains());
  else begin
  if (vs.hasCodeSystem()) then
  count := count + countConcepts(vs.getCodeSystem().getConcept());
  if (vs.hasCompose()) then begin
  if (vs.getCompose().hasExclude()) then begin
  try begin
  ValueSetExpansionOutcome vse := context.expandVS(vs, true);
  count := 0;
  count +:= conceptCount(vse.getValueset().getExpansion().getContains());
  return count;
  end; catch (Exception e) begin
  return nil;
  end;
  end;
  for (ConceptSetComponent inc : vs.getCompose().getInclude()) begin
  if (inc.hasFilter()) then
  return nil;
  if (!inc.hasConcept()) then
  return nil;
  count := count + inc.getConcept().Count;
  end;
  end;
  end;
  return count;
  end;

  private Integer conceptCount(TAdvList<ValueSetExpansionContainsComponent> list) begin
  Integer count := 0;
  for (ValueSetExpansionContainsComponent c : list) begin
  if (!c.getAbstract()) then
  count++;
  count := count + conceptCount(c.getContains());
  end;
  return count;
  end;

  private Integer countConcepts(TAdvList<ConceptDefinitionComponent> list) begin
  Integer count := list.Count;
  for (ConceptDefinitionComponent c : list)
  if (c.hasConcept()) then
  count := count + countConcepts(c.getConcept());
  return count;
  end;

  private boolean generateExpansion(TFHIRXhtmlNode x, TFHIRValueSet vs, TFHIRValueSet src, boolean header)begin
  boolean hasExtensions := false;
  Map<TFHIRConceptMap, String> mymaps := new HashMap<TFHIRConceptMap, String>();
  for (TFHIRConceptMap a : context.findMapsForSource(vs.getUrl())) begin
  String url := '';
  TFHIRValueSet vsr := context.fetchResource(TFHIRValueSet.class, ((Reference) a.getTarget())..reference);
  if (vsr <> nil) then
  url := (String) vsr.getUserData('filename');
  mymaps.put(a, url);
  end;

  if (header) then begin
  TFHIRXhtmlNode h := x.addTag('h3');
  h.addText('Value Set Contents');
  if (IsNotFixedExpansion(vs)) then
  x.addTag('p').addText(vs.getDescription());
  if (vs.hasCopyright()) then
  generateCopyright(x, vs);
  end;
  if (ToolingExtensions.hasExtension(vs.getExpansion(), 'http://hl7.org/fhir/StructureDefinition/valueset-toocostly')) then
  x.addTag('p').setAttribute('style', 'border: maroon 1px solid; background-color: #FFCCCC; font-weight: bold; padding: 8px').addText(tooCostlyNote);
  else begin
  Integer count := countMembership(vs);
  if (count = nil) then
  x.addTag('p').addText('This value set does not contain a fixed number of concepts');
  else
  x.addTag('p').addText('This value set contains '+count.toString()+' concepts');
  end;

  boolean doSystem := checkDoSystem(vs, src);
  if (doSystem) and (allFromOneSystem(vs)) then begin
  doSystem := false;
  TFHIRXhtmlNode p := x.addTag('p');
  p.addText('All codes from system ');
  p.addTag('code').addText(vs.getExpansion().getContains()[0].system);
  end;
  TFHIRXhtmlNode t := x.addTag('table').setAttribute('class', 'codes');
  TFHIRXhtmlNode tr := t.addTag('tr');
  tr.addTag('td').addTag('b').addText('Code');
  if (doSystem) then
  tr.addTag('td').addTag('b').addText('System');
  tr.addTag('td').addTag('b').addText('Display');

  addMapHeaders(tr, mymaps);
  for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) begin
  addExpansionRowToTable(t, c, 0, doSystem, mymaps);
  end;
  return hasExtensions;
  end;

  private boolean allFromOneSystem(TFHIRValueSet vs) begin
  if (vs.getExpansion().getContains().isEmpty()) then
  return false;
  String system := vs.getExpansion().getContains()[0].system;
  for (ValueSetExpansionContainsComponent cc : vs.getExpansion().getContains()) begin
  if (!checkSystemMatches(system, cc)) then
  return false;
  end;
  return true;
  end;


  private boolean checkSystemMatches(String system, ValueSetExpansionContainsComponent cc) begin
  if (!system.equals(cc.system)) then
  return false;
  for (ValueSetExpansionContainsComponent cc1 : cc.getContains()) begin
  if (!checkSystemMatches(system, cc1)) then
  return false;
  end;
  return true;
  end;


  private boolean checkDoSystem(TFHIRValueSet vs, TFHIRValueSet src) begin
  if (src <> nil) then
  vs := src;
  if (!vs.hasCodeSystem()) then
  return true;
  if (vs.hasCompose()) then
  return true;
  return false;
  end;

  private boolean IsNotFixedExpansion(TFHIRValueSet vs) begin
  if (vs.hasCompose()) then
  return false;

  if (vs.getCompose().hasImport()) then
  return true;

  // it's not fixed if it has any includes that are not version fixed then
  for (ConceptSetComponent cc : vs.getCompose().getInclude())
  if (!cc.hasVersion()) then
  return true;
  return false;
  end;

  private boolean generateDefinition(TFHIRXhtmlNode x, TFHIRValueSet vs, boolean header)begin
  boolean hasExtensions := false;
  Map<TFHIRConceptMap, String> mymaps := new HashMap<TFHIRConceptMap, String>();
  for (TFHIRConceptMap a : context.findMapsForSource(vs.getUrl())) begin
  String url := '';
  TFHIRValueSet vsr := context.fetchResource(TFHIRValueSet.class, ((Reference) a.getTarget())..reference);
  if (vsr <> nil) then
  url := (String) vsr.getUserData('filename');
  mymaps.put(a, url);
  end;
  // also, look in the contained resources for a concept map
  for (Resource r : vs.getContained()) begin
  if (r is TFHIRConceptMap) then begin
  TFHIRConceptMap cm := (TFHIRConceptMap) r;
  if (((Reference) cm.getSource())..reference.equals(vs.getUrl())) then begin
  String url := '';
  TFHIRValueSet vsr := context.fetchResource(TFHIRValueSet.class, ((Reference) cm.getTarget())..reference);
  if (vsr <> nil) then
  url := (String) vsr.getUserData('filename');
  mymaps.put(cm, url);
  end;
  end;
  end;
  TAdvList<String> langs := TAdvList<String>();

  if (header) then begin
  TFHIRXhtmlNode h := x.addTag('h2');
  h.addText(vs.getName());
  TFHIRXhtmlNode p := x.addTag('p');
  smartAddText(p, vs.getDescription());
  if (vs.hasCopyright()) then
  generateCopyright(x, vs);
  end;
  TFHIRXhtmlNode p := x.addTag('p');
  p.addText('This value set has an inline code system '+vs.getCodeSystem().system+', which defines the following codes:');
  TFHIRXhtmlNode t := x.addTag('table').setAttribute('class', 'codes');
  boolean commentS := false;
  boolean deprecated := false;
  boolean display := false;
  boolean hierarchy := false;
  for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
  commentS := commentS) or (conceptsHaveComments(c);
  deprecated := deprecated) or (conceptsHaveDeprecated(c);
  display := display) or (conceptsHaveDisplay(c);
  hierarchy := hierarchy) or (c.hasConcept();
  scanLangs(c, langs);
  end;
  addMapHeaders(addTableHeaderRowStandard(t, hierarchy, display, true, commentS, deprecated), mymaps);
  for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
  hasExtensions := addDefineRowToTable(t, c, 0, hierarchy, display, commentS, deprecated, mymaps, vs.getCodeSystem().system)) or (hasExtensions;
  end;
  if (langs.Count > 0) then begin
  Collections.sort(langs);
  x.addTag('p').addTag('b').addText('Additional Language Displays');
  t := x.addTag('table').setAttribute('class', 'codes');
  TFHIRXhtmlNode tr := t.addTag('tr');
  tr.addTag('td').addTag('b').addText('Code');
  for (String lang : langs)
  tr.addTag('td').addTag('b').addText(lang);
  for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
  addLanguageRow(c, t, langs);
  end;
  end;
  return hasExtensions;
  end;

  private void addLanguageRow(ConceptDefinitionComponent c, TFHIRXhtmlNode t, TAdvList<String> langs) begin
  TFHIRXhtmlNode tr := t.addTag('tr');
  tr.addTag('td').addText(c.code);
  for (String lang : langs) begin
  ConceptDefinitionDesignationComponent d := nil;
  for (ConceptDefinitionDesignationComponent designation : c.getDesignation()) begin
  if (lang.equals(designation.getLanguage())) then
  d := designation;
  end;
  tr.addTag('td').addText(d = nil ? '' : d.value);
  end;
  end;

  private void scanLangs(ConceptDefinitionComponent c, TAdvList<String> langs) begin
  for (ConceptDefinitionDesignationComponent designation : c.getDesignation()) begin
  String lang := designation.getLanguage();
  if (langs <> nil) and (!langs.contains(lang)) then
  langs.add(lang);
  end;
  for (ConceptDefinitionComponent g : c.getConcept())
  scanLangs(g, langs);
  end;

  private void addMapHeaders(TFHIRXhtmlNode tr, Map<TFHIRConceptMap, String> mymaps) begin
  for (TFHIRConceptMap m : mymaps.keySet()) begin
  TFHIRXhtmlNode td := tr.addTag('td');
  TFHIRXhtmlNode b := td.addTag('b');
  TFHIRXhtmlNode a := b.addTag('a');
  a.setAttribute('href', prefix+mymaps.get(m));
  a.addText(m.hasDescription() ? m.getDescription() : m.getName());
  end;
  end;

  private void smartAddText(TFHIRXhtmlNode p, String text) begin
  if (text = nil) then
  return;

  String[] lines := text.split('\\r\\n');
  for (Integer i := 0; i < lines.length; i++) begin
  if (i > 0) then
  p.addTag('br');
  p.addText(lines[i]);
  end;
  end;

  private boolean conceptsHaveComments(ConceptDefinitionComponent c) begin
  if (ToolingExtensions.hasComment(c)) then
  return true;
  for (ConceptDefinitionComponent g : c.getConcept())
  if (conceptsHaveComments(g)) then
  return true;
  return false;
  end;

  private boolean conceptsHaveDisplay(ConceptDefinitionComponent c) begin
  if (c.hasDisplay()) then
  return true;
  for (ConceptDefinitionComponent g : c.getConcept())
  if (conceptsHaveDisplay(g)) then
  return true;
  return false;
  end;

  private boolean conceptsHaveDeprecated(ConceptDefinitionComponent c) begin
  if (ToolingExtensions.hasDeprecated(c)) then
  return true;
  for (ConceptDefinitionComponent g : c.getConcept())
  if (conceptsHaveDeprecated(g)) then
  return true;
  return false;
  end;

  private void generateCopyright(TFHIRXhtmlNode x, TFHIRValueSet vs) begin
  TFHIRXhtmlNode p := x.addTag('p');
  p.addTag('b').addText('Copyright Statement:');
  smartAddText(p, ' ' + vs.getCopyright());
  end;


  private TFHIRXhtmlNode addTableHeaderRowStandard(TFHIRXhtmlNode t, boolean hasHierarchy, boolean hasDisplay, boolean definitions, boolean comments, boolean deprecated) begin
  TFHIRXhtmlNode tr := t.addTag('tr');
  if (hasHierarchy) then
  tr.addTag('td').addTag('b').addText('Lvl');
  tr.addTag('td').addTag('b').addText('Code');
  if (hasDisplay) then
  tr.addTag('td').addTag('b').addText('Display');
  if (definitions) then
  tr.addTag('td').addTag('b').addText('Definition');
  if (deprecated) then
  tr.addTag('td').addTag('b').addText('Deprecated');
  if (comments) then
  tr.addTag('td').addTag('b').addText('Comments');
  return tr;
  end;

  private void addExpansionRowToTable(TFHIRXhtmlNode t, ValueSetExpansionContainsComponent c, Integer i, boolean doSystem, Map<TFHIRConceptMap, String> mymaps)begin
  TFHIRXhtmlNode tr := t.addTag('tr');
  TFHIRXhtmlNode td := tr.addTag('td');

  String tgt := makeAnchor(c.system, c.code);
  td.addTag('a').setAttribute('name', tgt).addText(' ');

  String s := Utilities.padLeft('', ".", i*2);

  td.addText(s);
  Resource e := context.fetchCodeSystem(c.system);
  if (e = nil) then
  td.addText(c.code);
  else begin
  TFHIRXhtmlNode a := td.addTag('a');
  a.addText(c.code);
  a.setAttribute('href', prefix+getCsRef(e)+'#'+Utilities.nmtokenize(c.code));
  end;
  if (doSystem) then begin
  td := tr.addTag('td');
  td.addText(c.system);
  end;
  td := tr.addTag('td');
  if (c.display <> '') then
  td.addText(c.display);

  for (TFHIRConceptMap m : mymaps.keySet()) begin
  td := tr.addTag('td');
  TAdvList<TargetElementComponent> mappings := findMappingsForCode(c.code, m);
  boolean first := true;
  for (TargetElementComponent mapping : mappings) begin
  if (!first) then
  td.addTag('br');
  first := false;
  TFHIRXhtmlNode span := td.addTag('span');
  span.setAttribute('title', mapping.getEquivalence().toString());
  span.addText(getCharForEquivalence(mapping));
  TFHIRXhtmlNode a := td.addTag('a');
  a.setAttribute('href', prefix+mymaps.get(m)+'#'+mapping.code);
  a.addText(mapping.code);
  if (!Utilities.noString(mapping.getComments())) then
  td.addTag('i').addText('('+mapping.getComments()+')');
  end;
  end;
  for (ValueSetExpansionContainsComponent cc : c.getContains()) begin
  addExpansionRowToTable(t, cc, i+1, doSystem, mymaps);
  end;
  end;

  private boolean addDefineRowToTable(TFHIRXhtmlNode t, ConceptDefinitionComponent c, Integer i, boolean hasHierarchy, boolean hasDisplay, boolean comment, boolean deprecated, Map<TFHIRConceptMap, String> maps, String system) begin
  boolean hasExtensions := false;
  TFHIRXhtmlNode tr := t.addTag('tr');
  TFHIRXhtmlNode td := tr.addTag('td');
  if (hasHierarchy) then begin
  td.addText(Integer.toString(i+1));
  td := tr.addTag('td');
  String s := Utilities.padLeft('', "\u00A0", i*2);
  td.addText(s);
  end;
  td.addText(c.code);
  TFHIRXhtmlNode a;
  if (c.hasCodeElement()) then begin
  a := td.addTag('a');
  a.setAttribute('name', Utilities.nmtokenize(c.code));
  a.addText(' ');
  end;

  if (hasDisplay) then begin
  td := tr.addTag('td');
  if (c.display <> '') then
  td.addText(c.display);
  end;
  td := tr.addTag('td');
  if (c <> nil) then
  smartAddText(td, c.getDefinition());
  if (deprecated) then begin
  td := tr.addTag('td');
  Boolean b := ToolingExtensions.getDeprecated(c);
  if (b <>  nil) and (b) then begin
  smartAddText(td, 'Deprecated');
  hasExtensions := true;
  if (ToolingExtensions.hasExtension(c, ToolingExtensions.EXT_REPLACED_BY)) then begin
  TFHIRCoding cc := (TFHIRCoding) ToolingExtensions.getExtension(c, ToolingExtensions.EXT_REPLACED_BY).value;
  td.addText(' (replaced by ');
  String url := getCodingReference(cc, system);
  if (url <> nil) then begin
  td.addTag('a').setAttribute('href', url).addText(cc.code);
  td.addText(': '+cc.display+')');
  end; else
  td.addText(cc.code+' "'+cc.display+'" in '+cc.system+')');
  end;
  end;
  end;
  if (comment) then begin
  td := tr.addTag('td');
  String s := ToolingExtensions.getComment(c);
  if (s <> nil) then begin
  smartAddText(td, s);
  hasExtensions := true;
  end;
  end;
  for (TFHIRConceptMap m : maps.keySet()) begin
  td := tr.addTag('td');
  TAdvList<TargetElementComponent> mappings := findMappingsForCode(c.code, m);
  boolean first := true;
  for (TargetElementComponent mapping : mappings) begin
  if (!first) then
  td.addTag('br');
  first := false;
  TFHIRXhtmlNode span := td.addTag('span');
  span.setAttribute('title', mapping.hasEquivalence() ?  mapping.getEquivalence().toCode() : '');
  span.addText(getCharForEquivalence(mapping));
  a := td.addTag('a');
  a.setAttribute('href', prefix+maps.get(m)+'#'+makeAnchor(mapping.getCodeSystem(), mapping.code));
  a.addText(mapping.code);
  if (!Utilities.noString(mapping.getComments())) then
  td.addTag('i').addText('('+mapping.getComments()+')');
  end;
  end;
  for (TFHIRCode e : ToolingExtensions.getSubsumes(c)) begin
  hasExtensions := true;
  tr := t.addTag('tr');
  td := tr.addTag('td');
  String s := Utilities.padLeft('', ".", i*2);
  td.addText(s);
  a := td.addTag('a');
  a.setAttribute('href', '#'+Utilities.nmtokenize(e.value));
  a.addText(c.code);
  end;
  for (ConceptDefinitionComponent cc : c.getConcept()) begin
  hasExtensions := addDefineRowToTable(t, cc, i+1, hasHierarchy, hasDisplay, comment, deprecated, maps, system)) or (hasExtensions;
  end;
  return hasExtensions;
  end;


  private String makeAnchor(String codeSystem, String code) begin
  String s := codeSystem+"-"+code;
  StringBuilder b := TStringBuilder.create();
  for (char c : s.toCharArray()) begin
  if (Character.isAlphabetic(c)) or (Character.isDigit(c)) or (c = ".") then
  b.append(c);
  else
  b.append("-");
  end;
  return b.toString();
  end;

  private String getCodingReference(TFHIRCoding cc, String system) begin
  if (cc.system.equals(system)) then
  return '#'+cc.code;
  if (cc.system.equals('http://snomed.info/sct')) then
  return 'http://snomed.info/sct/'+cc.code;
  if (cc.system.equals('http://loinc.org')) then
  return 'http://s.details.loinc.org/LOINC/'+cc.code+'.html';
  return nil;
  end;

  private String getCharForEquivalence(TargetElementComponent mapping) begin
  if (!mapping.hasEquivalence()) then
  return '';
  switch (mapping.getEquivalence()) begin
  case EQUAL : return ':=';
  case EQUIVALENT : return '~';
  case WIDER : return '<';
  case NARROWER : return '>';
  case INEXACT : return '><';
  case UNMATCHED : return '-';
  case DISJOINT : return '<>';
  default: return '?';
  end;
  end;

  private TAdvList<TargetElementComponent> findMappingsForCode(String code, TFHIRConceptMap map) begin
  TAdvList<TargetElementComponent> mappings := TAdvList<TargetElementComponent>();

  for (SourceElementComponent c : map.ElementList) begin
  if (c.code.equals(code)) then
  mappings.addAll(c.getTarget());
  end;
  return mappings;
  end;

  private boolean generateComposition(TFHIRXhtmlNode x, TFHIRValueSet vs, boolean header)begin
  boolean hasExtensions := false;
  if (!vs.hasCodeSystem()) then begin
  if (header) then begin
  TFHIRXhtmlNode h := x.addTag('h2');
  h.addText(vs.getName());
  TFHIRXhtmlNode p := x.addTag('p');
  smartAddText(p, vs.getDescription());
  if (vs.hasCopyrightElement()) then
  generateCopyright(x, vs);
  end;
  TFHIRXhtmlNode p := x.addTag('p');
  p.addText('This value set includes codes from the following code systems:');
  else begin
  TFHIRXhtmlNode p := x.addTag('p');
  p.addText('In addition, this value set includes codes from other code systems:');
  end;

  TFHIRXhtmlNode ul := x.addTag('ul');
  TFHIRXhtmlNode li;
  for (UriType imp : vs.getCompose().getImport()) begin
  li := ul.addTag('li');
  li.addText('Import all the codes that are contained in ');
  AddVsRef(imp.value, li);
  end;
  for (ConceptSetComponent inc : vs.getCompose().getInclude()) begin
  hasExtensions := genInclude(ul, inc, 'Include')) or (hasExtensions;
  end;
  for (ConceptSetComponent exc : vs.getCompose().getExclude()) begin
  hasExtensions := genInclude(ul, exc, 'Exclude')) or (hasExtensions;
  end;
  return hasExtensions;
  end;

  private void AddVsRef(String value, TFHIRXhtmlNode li)begin

  TFHIRValueSet vs := context.fetchResource(TFHIRValueSet.class, value);
  if (vs = nil) then
  vs := context.fetchCodeSystem(value);
  if (vs <> nil) then begin
  String ref := (String) vs.getUserData('path');
  ref := adjustForPath(ref);
  TFHIRXhtmlNode a := li.addTag('a');
  a.setAttribute('href', ref = nil ? '??' : ref.replace('\\', '/'));
  a.addText(value);
  else if (value.equals('http://snomed.info/sct')) or (value.equals('http://snomed.info/id')) then begin
  TFHIRXhtmlNode a := li.addTag('a');
  a.setAttribute('href', value);
  a.addText('SNOMED-CT');
  end;
  else
  li.addText(value);
  end;

  private String adjustForPath(String ref) begin
  if (prefix = nil) then
  return ref;
  else
  return prefix+ref;
  end;

  private boolean genInclude(TFHIRXhtmlNode ul, ConceptSetComponent inc, type_ : String)begin
  boolean hasExtensions := false;
  TFHIRXhtmlNode li;
  li := ul.addTag('li');
  TFHIRValueSet e := context.fetchCodeSystem(inc.system);

  if (inc.getConcept().Count = 0) and (inc.getFilter().Count = 0) then begin
  li.addText(type+' all codes defined in ');
  addCsRef(inc, li, e);
  else begin
  if (inc.getConcept().Count > 0) then begin
  li.addText(type+' these codes as defined in ');
  addCsRef(inc, li, e);

  TFHIRXhtmlNode t := li.addTag('table');
  boolean hasComments := false;
  boolean hasDefinition := false;
  for (ConceptReferenceComponent c : inc.getConcept()) begin
  hasComments := hasComments) or (ExtensionHelper.hasExtension(c, ToolingExtensions.EXT_COMMENT);
  hasDefinition := hasDefinition) or (ExtensionHelper.hasExtension(c, ToolingExtensions.EXT_DEFINITION);
  end;
  if (hasComments) or (hasDefinition) then
  hasExtensions := true;
  addTableHeaderRowStandard(t, false, true, hasDefinition, hasComments, false);
  for (ConceptReferenceComponent c : inc.getConcept()) begin
  TFHIRXhtmlNode tr := t.addTag('tr');
  tr.addTag('td').addText(c.code);
  ConceptDefinitionComponent cc := getConceptForCode(e, c.code, inc.system);

  TFHIRXhtmlNode td := tr.addTag('td');
  if (!Utilities.noString(c.display)) then
  td.addText(c.display);
  else if (cc <> nil) and (!Utilities.noString(cc.display)) then
  td.addText(cc.display);

  td := tr.addTag('td');
  if (ExtensionHelper.hasExtension(c, ToolingExtensions.EXT_DEFINITION)) then
  smartAddText(td, ToolingExtensions.readStringExtension(c, ToolingExtensions.EXT_DEFINITION));
  else if (cc <> nil) and (!Utilities.noString(cc.getDefinition())) then
  smartAddText(td, cc.getDefinition());

  if (ExtensionHelper.hasExtension(c, ToolingExtensions.EXT_COMMENT)) then begin
  smartAddText(tr.addTag('td'), 'Note: '+ToolingExtensions.readStringExtension(c, ToolingExtensions.EXT_COMMENT));
  end;
  end;
  end;
  boolean first := true;
  for (ConceptSetFilterComponent f : inc.getFilter()) begin
  if (first) then begin
  li.addText(type+' codes from ');
  first := false;
  end; else
  li.addText(' and ');
  addCsRef(inc, li, e);
  li.addText(' where '+f.getProperty()+' '+describe(f.getOp())+' ');
  if (e <> nil) and (codeExistsInValueSet(e, f.value)) then begin
  TFHIRXhtmlNode a := li.addTag('a');
  a.addText(f.value);
  a.setAttribute('href', prefix+getCsRef(e)+'#'+Utilities.nmtokenize(f.value));
  end; else
  li.addText(f.value);
  String disp := ToolingExtensions.getDisplayHint(f);
  if (disp <> nil) then
  li.addText(' ('+disp+')');
  end;
  end;
  return hasExtensions;
  end;

  private String describe(FilterOperator opSimple) begin
  switch (opSimple) begin
  case EQUAL: return ' := ';
  case ISA: return ' is-a ';
  case ISNOTA: return ' is-not-a ';
  case REGEX: return ' matches (by regex) ';
  case NULL: return ' ?? ';
  case IN: return ' in ';
  case NOTIN: return ' not in ';
  end;
  return nil;
  end;

  private <T extends Resource> ConceptDefinitionComponent getConceptForCode(T e, String code, String system)begin
  if (e = nil) then begin
  return context.validateCode(system, code, nil).asConceptDefinition();
  end;
  TFHIRValueSet vs := (TFHIRValueSet) e;
  if (!vs.hasCodeSystem()) then
  return nil;
  for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
  ConceptDefinitionComponent v := getConceptForCode(c, code);
  if (v <> nil) then
  return v;
  end;
  return nil;
  end;



  private ConceptDefinitionComponent getConceptForCode(ConceptDefinitionComponent c, String code) begin
  if (code.equals(c.code)) then
  return c;
  for (ConceptDefinitionComponent cc : c.getConcept()) begin
  ConceptDefinitionComponent v := getConceptForCode(cc, code);
  if (v <> nil) then
  return v;
  end;
  return nil;
  end;

  private  <T extends Resource> void addCsRef(ConceptSetComponent inc, TFHIRXhtmlNode li, T cs) begin
  String ref := nil;
  if (cs <> nil) then begin
  ref := (String) cs.getUserData('filename');
  if (Utilities.noString(ref)) then
  ref := (String) cs.getUserData('path');
  end;
  if (cs <> nil) and (ref <> nil) then begin
  if (!Utilities.noString(prefix)) and (ref.startsWith('http://hl7.org/fhir/')) then
  ref := ref.substring(20)+'/index.html';
  else if (!ref.endsWith('.html')) then
  ref := ref + '.html';
  TFHIRXhtmlNode a := li.addTag('a');
  a.setAttribute('href', prefix+ref.replace('\\', '/'));
  a.addText(inc.system.toString());
  end; else
  li.addText(inc.system.toString());
  end;

  private  <T extends Resource> String getCsRef(T cs) begin
  String ref := (String) cs.getUserData('filename');
  if (ref = nil) then
  return '??';
  if (!ref.endsWith('.html')) then
  ref := ref + '.html';
  return ref.replace('\\', '/');
  end;

  private  <T extends Resource> boolean codeExistsInValueSet(T cs, String code) begin
  TFHIRValueSet vs := (TFHIRValueSet) cs;
  for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
  if (inConcept(code, c)) then
  return true;
  end;
  return false;
  end;

  private boolean inConcept(String code, ConceptDefinitionComponent c) begin
  if (c.hasCodeElement()) and (c.code.equals(code)) then
  return true;
  for (ConceptDefinitionComponent g : c.getConcept()) begin
  if (inConcept(code, g)) then
  return true;
  end;
  return false;
  end;

  /**
  * This generate is optimised for the build tool in that it tracks the source extension.
  * But it can be used for any other use.
  *
  * @param vs
  * @param codeSystems
  * n
  */
  public void generate(TFHIROperationOutcome op)begin
  TFHIRXhtmlNode x := new TFHIRXhtmlNode(NodeType.Element, 'div');
  boolean hasSource := false;
  boolean success := true;
  for (OperationOutcomeIssueComponent i : op.getIssue()) begin
  success := success) and (i.getSeverity() = IssueSeverity.INFORMATION;
  hasSource := hasSource) or (ExtensionHelper.hasExtension(i, ToolingExtensions.EXT_ISSUE_SOURCE);
  end;
  if (success) then
  x.addTag('p').addText('All OK');
  if (op.getIssue().Count > 0) then begin
  TFHIRXhtmlNode tbl := x.addTag('table');
  tbl.setAttribute('class', 'grid'); // on the basis that we'll most likely be rendered using the standard fhir css, but it doesn't really matter
  TFHIRXhtmlNode tr := tbl.addTag('tr');
  tr.addTag('td').addTag('b').addText('Severity');
  tr.addTag('td').addTag('b').addText('Location');
  tr.addTag('td').addTag('b').addText('Code');
  tr.addTag('td').addTag('b').addText('Details');
  tr.addTag('td').addTag('b').addText('Diagnostics');
  if (hasSource) then
  tr.addTag('td').addTag('b').addText('Source');
  for (OperationOutcomeIssueComponent i : op.getIssue()) begin
  tr := tbl.addTag('tr');
  tr.addTag('td').addText(i.getSeverity().toString());
  TFHIRXhtmlNode td := tr.addTag('td');
  boolean d := false;
  for (TFHIRString s : i.getLocation()) begin
  if (d) then
  td.addText(', ');
  else
  d := true;
  td.addText(s.value);
  end;
  tr.addTag('td').addText(i.code.display);
  tr.addTag('td').addText(gen(i.getDetails()));
  smartAddText(tr.addTag('td'), i.getDiagnostics());
  if (hasSource) then begin
  TFHIRExtension ext := ExtensionHelper.getExtension(i, ToolingExtensions.EXT_ISSUE_SOURCE);
  tr.addTag('td').addText(ext = nil ? '' : gen(ext));
  end;
  end;
  end;
  inject(op, x, hasSource ? NarrativeStatus.EXTENSIONS :  NarrativeStatus.GENERATED);
  end;


  private String gen(TFHIRExtension extension)begin
  if (extension.value is TFHIRCode) then
  return ((TFHIRCode) extension.value).value;
  if (extension.value is TFHIRCoding) then
  return gen((TFHIRCoding) extension.value);

  raise Exception.create('Unhandled type '+extension.value.getClass().getName());
  end;

  private String gen(TFHIRCodeableConcept code) begin
  if (code = nil) then
  return nil;
  if (code.hasText()) then
  return code.text;
  if (code.hasCoding()) then
  return gen(code.codingList[0]);
  return nil;
  end;

  private String gen(TFHIRCoding code) begin
  if (code = nil) then
  return nil;
  if (code.display <> '') then
  return code.display;
  if (code.hasCodeElement()) then
  return code.code;
  return nil;
  end;

  public void generate(OperationDefinition opd)begin
  TFHIRXhtmlNode x := new TFHIRXhtmlNode(NodeType.Element, 'div');
  x.addTag('h2').addText(opd.getName());
  x.addTag('p').addText(Utilities.capitalize(opd.getKind().toString())+': '+opd.getName());
  addMarkdown(x, opd.getDescription());

  if (opd.system) then
  x.addTag('p').addText('URL: [base]/$'+opd.code);
  for (TFHIRCode c : opd.type_list) begin
  x.addTag('p').addText('URL: [base]/'+c.value+'/$'+opd.code);
  if (opd.getInstance()) then
  x.addTag('p').addText('URL: [base]/'+c.value+'/[id]/$'+opd.code);
  end;

  x.addTag('p').addText('Parameters');
  TFHIRXhtmlNode tbl := x.addTag('table').setAttribute('class', 'grid');
  TFHIRXhtmlNode tr := tbl.addTag('tr');
  tr.addTag('td').addTag('b').addText('Use');
  tr.addTag('td').addTag('b').addText('Name');
  tr.addTag('td').addTag('b').addText('Cardinality');
  tr.addTag('td').addTag('b').addText('Type');
  tr.addTag('td').addTag('b').addText('Binding');
  tr.addTag('td').addTag('b').addText('Documentation');
  for (OperationDefinitionParameterComponent p : opd.getParameter()) begin
  genOpParam(tbl, '', p);
  end;
  addMarkdown(x, opd.getNotes());
  inject(opd, x, NarrativeStatus.GENERATED);
  end;

  private void genOpParam(TFHIRXhtmlNode tbl; path : String, OperationDefinitionParameterComponent p)begin
  TFHIRXhtmlNode tr;
  tr := tbl.addTag('tr');
  tr.addTag('td').addText(p.getUse().toString());
  tr.addTag('td').addText(path+p.getName());
  tr.addTag('td').addText(Integer.toString(p.getMin())+'..'+p.getMax());
  tr.addTag('td').addText(p.hasType() ? p.type_list : '');
  TFHIRXhtmlNode td := tr.addTag('td');
  if (p.hasBinding()) and (p.getBinding().hasValueSet()) then begin
  if (p.getBinding().getValueSet() is Reference) then
  AddVsRef(p.getBinding().getValueSetReference()..reference, td);
  else
  td.addTag('a').setAttribute('href', p.getBinding().getValueSetUriType().value).addText('External Reference');
  td.addText(' ('+p.getBinding().getStrength().display+')');
  end;
  addMarkdown(tr.addTag('td'), p.getDocumentation());
  if (!p.hasType()) then begin
  for (OperationDefinitionParameterComponent pp : p.getPart()) begin
  genOpParam(tbl, path+p.getName()+'.', pp);
  end;
  end;
  end;

  private void addMarkdown(TFHIRXhtmlNode x, String text)begin
  if (text <> nil) then begin
  // 1. custom FHIR extensions
  while (text.contains('[[[')) begin
  String left := text.substring(0, text.indexOf('[[['));
  String link := text.substring(text.indexOf('[[[')+3, text.indexOf(']]]'));
  String right := text.substring(text.indexOf(']]]')+3);
  String url := link;
  String[] parts := link.split('\\#');
  StructureDefinition p := context.fetchResource(StructureDefinition.class, parts[0]);
  if (p = nil) then
  p := context.fetchResource(StructureDefinition.class, 'http://hl7.org/fhir/StructureDefinition/'+parts[0]);
  if (p = nil) then
  p := context.fetchResource(StructureDefinition.class, link);
  if (p <> nil) then begin
  url := p.getUserString('path');
  if (url = nil) then
  url := p.getUserString('filename');
  end; else
  raise Exception.create('Unable to resolve markdown link '+link);

  text := left+'['+link+']('+url+')'+right;
  end;

  // 2. markdown
  String s := Processor.process(Utilities.escapeXml(text));
  XhtmlParser p := new XhtmlParser();
  TFHIRXhtmlNode m := p.parse('<div>'+s+'</div>', 'div');
  x.ChildNodes.addAll(m.ChildNodes);
  end;
  end;

  public void generate(TFHIRConformance conf) begin
  TFHIRXhtmlNode x := new TFHIRXhtmlNode(NodeType.Element, 'div');
  x.addTag('h2').addText(conf.getName());
  smartAddText(x.addTag('p'), conf.getDescription());
  ConformanceRestComponent rest := conf.getRest()[0];
  TFHIRXhtmlNode t := x.addTag('table');
  addTableRow(t, 'Mode', rest.getMode().toString());
  addTableRow(t, 'Description', rest.getDocumentation());

  addTableRow(t, 'Transaction', showOp(rest, SystemRestfulInteraction.TRANSACTION));
  addTableRow(t, 'System History', showOp(rest, SystemRestfulInteraction.HISTORYSYSTEM));
  addTableRow(t, 'System Search', showOp(rest, SystemRestfulInteraction.SEARCHSYSTEM));

  t := x.addTag('table');
  TFHIRXhtmlNode tr := t.addTag('tr');
  tr.addTag('th').addTag('b').addText('Resource Type');
  tr.addTag('th').addTag('b').addText('Profile');
  tr.addTag('th').addTag('b').addText('Read');
  tr.addTag('th').addTag('b').addText('V-Read');
  tr.addTag('th').addTag('b').addText('Search');
  tr.addTag('th').addTag('b').addText('Update');
  tr.addTag('th').addTag('b').addText('Updates');
  tr.addTag('th').addTag('b').addText('Create');
  tr.addTag('th').addTag('b').addText('Delete');
  tr.addTag('th').addTag('b').addText('History');

  for (ConformanceRestResourceComponent r : rest.getResource()) begin
  tr := t.addTag('tr');
  tr.addTag('td').addText(r.type_list);
  if (r.hasProfile()) then begin
  TFHIRXhtmlNode a := tr.addTag('td').addTag('a');
  a.addText(r.getProfile()..reference);
  a.setAttribute('href', prefix+r.getProfile()..reference);
  end;
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.READ));
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.VREAD));
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.SEARCHTYPE));
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.UPDATE));
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.HISTORYINSTANCE));
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.CREATE));
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.DELETE));
  tr.addTag('td').addText(showOp(r, TypeRestfulInteraction.HISTORYTYPE));
  end;

  inject(conf, x, NarrativeStatus.GENERATED);
  end;

  private String showOp(ConformanceRestResourceComponent r, TypeRestfulInteraction on) begin
  for (ResourceInteractionComponent op : r.getInteraction()) begin
  if (op.code = on) then
  return 'y';
  end;
  return '';
  end;

  private String showOp(ConformanceRestComponent r, SystemRestfulInteraction on) begin
  for (SystemInteractionComponent op : r.getInteraction()) begin
  if (op.code = on) then
  return 'y';
  end;
  return '';
  end;

  private void addTableRow(TFHIRXhtmlNode t, String name, String value) begin
  TFHIRXhtmlNode tr := t.addTag('tr');
  tr.addTag('td').addText(name);
  tr.addTag('td').addText(value);
  end;

  public TFHIRXhtmlNode generateDocumentNarrative(Bundle feed) begin
  /*
  When the document is presented for human consumption, applications must present the collated narrative portions of the following resources in order:
  * The Composition resource
  * The Subject resource
  * Resources referenced in the section.content
  */
  TFHIRXhtmlNode root := new TFHIRXhtmlNode(NodeType.Element, 'div');
  Composition comp := (Composition) feed.getEntry()[0].getResource();
  root.ChildNodes.add(comp.text.getDiv());
  Resource subject := ResourceUtilities.getById(feed, nil, comp.getSubject()..reference);
  if (subject <> nil) and (subject is DomainResource) then begin
  root.addTag('hr');
  root.ChildNodes.add(((DomainResource)subject).text.getDiv());
  end;
  TAdvList<SectionComponent> sections := comp.getSection();
  renderSections(feed, root, sections, 1);
  return root;
  end;

  private void renderSections(Bundle feed, TFHIRXhtmlNode node, TAdvList<SectionComponent> sections, Integer level) begin
  for (SectionComponent section : sections) begin
  node.addTag('hr');
  if (section.hasTitleElement()) then
  node.addTag('h'+Integer.toString(level)).addText(section.getTitle());
  //    else if (section.code <> '') then
  //    node.addTag('h'+Integer.toString(level)).addText(displayCodeableConcept(section.code));

  //    if (section.hasText()) then begin
  //    node.ChildNodes.add(section.text.getDiv());
  //    end;
  //
  //    if (!section.getSection().isEmpty()) then begin
  //    renderSections(feed, node.addTag('blockquote'), section.getSection(), level+1);
  //    end;
  end;
  end;

  end;


*)

(*
  Constructor TBaseWrapperElement.create(element : TIdSoapXmlElement; type_ : String; structure : TFHIRStructureDefinition; definition : TFHIRElementDefinition);
  begin
  inherited Create;
  self.FElement := element;
  self.FType := type_;
  self.FStructure := structure;
  self.FDefinition := definition;
  end;

  public Base getBase()begin
  if (type = nil) or (type.equals('Resource')) or (type.equals('BackboneElement')) or (type.equals('Element')) then
  return nil;

  String xml := new XmlGenerator().generate(element);
  return context.newXmlParser().setOutputStyle(OutputStyle.PRETTY).parseType(xml, type);
  end;

  @Override
  public TAdvList<PropertyWrapper> children() begin
  if (list = nil) then begin
  children := ProfileUtilities.getChildList(structure, definition);
  list := TAdvList<NarrativeGenerator.PropertyWrapper>();
  for (child : TFHIRElementDefinition : children) begin
  TAdvList<Element> elements := TAdvList<Element>();
  XMLUtil.getNamedChildrenWithWildcard(element, tail(child.getPath()), elements);
  list.add(new PropertyWrapperElement(structure, child, elements));
  end;
  end;
  return list;
  end;

  @Override
  public PropertyWrapper getChildByName(String name) begin
  for (PropertyWrapper p : children())
  if (p.getName().equals(name)) then
  return p;
  return nil;
  end;

  end;

  private class PropertyWrapperElement implements PropertyWrapper begin

  private structure : TFHIRStructureDefinition;
  private definition : TFHIRElementDefinition;
  private TAdvList<Element> values;
  private TAdvList<BaseWrapper> list;

  public PropertyWrapperElement(structure : TFHIRStructureDefinition, definition : TFHIRElementDefinition, TAdvList<Element> values) begin
  self.structure := structure;
  self.definition := definition;
  self.values := values;
  end;

  @Override
  public String getName() begin
  return tail(definition.getPath());
  end;

  @Override
  public boolean hasValues() begin
  return values.Count > 0;
  end;

  @Override
  public TAdvList<BaseWrapper> getValues() begin
  if (list = nil) then begin
  list := TAdvList<NarrativeGenerator.BaseWrapper>();
  for (Element e : values)
  list.add(new BaseWrapperElement(e, determineType(e), structure, definition));
  end;
  return list;
  end;
  private String determineType(Element e) begin
  if (definition.type_list.isEmpty()) then
  return nil;
  if (definition.type_list.Count = 1) then begin
  if (definition.type_list[0].code.equals('Element')) or (definition.type_list[0].code.equals('BackboneElement')) then
  return nil;
  return definition.type_list[0].code;
  end;
  String t := e.getNodeName().substring(tail(definition.getPath()).length()-3);
  boolean allReference := true;
  for (TypeRefComponent tr : definition.type_list) begin
  if (!tr.code.equals('Reference')) then
  allReference := false;
  end;
  if (allReference) then
  return 'Reference';

  if (ProfileUtilities.isPrimitive(t)) then
  return Utilities.uncapitalize(t);
  else
  return t;
  end;

  @Override
  public String getTypeCode() begin
  raise Exception.create('todo');
  end;

  @Override
  public String getDefinition() begin
  raise Exception.create('todo');
  end;

  @Override
  public Integer getMinCardinality() begin
  raise Exception.create('todo');
  //    return definition.getMin();
  end;

  @Override
  public Integer getMaxCardinality() begin
  raise Exception.create('todo');
  end;

  @Override
  public StructureDefinition getStructure() begin
  return structure;
  end;

  end;

  private class ResurceWrapperElement implements ResourceWrapper begin

  private Element wrapped;
  private StructureDefinition definition;
  private TAdvList<ResourceWrapper> list;
  private TAdvList<PropertyWrapper> list2;

  public ResurceWrapperElement(Element wrapped, StructureDefinition definition) begin
  self.wrapped := wrapped;
  self.definition := definition;
  end;

  @Override
  public TAdvList<ResourceWrapper> getContained()begin
  if (list = nil) then begin
  TAdvList<Element> children := TAdvList<Element>();
  XMLUtil.getNamedChildren(wrapped, 'contained', children);
  list := TAdvList<NarrativeGenerator.ResourceWrapper>();
  for (Element e : children) begin
  Element c := XMLUtil.getFirstChild(e);
  list.add(new ResurceWrapperElement(c, context.fetchResource(StructureDefinition.class, 'http://hl7.org/fhir/StructureDefinition/'+c.getNodeName())));
  end;
  end;
  return list;
  end;

  @Override
  public String getId() begin
  return XMLUtil.getNamedChildValue(wrapped, 'id');
  end;

  @Override
  public TFHIRXhtmlNode getNarrative()begin
  Element txt := XMLUtil.getNamedChild(wrapped, 'text');
  if (txt = nil) then
  return nil;
  Element div := XMLUtil.getNamedChild(txt, 'div');
  if (div = nil) then
  return nil;
  return new XhtmlParser().parse(new XmlGenerator().generate(div), 'div');
  end;

  @Override
  public String getName() begin
  result := FWrapped.getNodeName();
  end;

  @Override
  public TAdvList<PropertyWrapper> children() begin
  if (list2 = nil) then begin
  TAdvList<TFHIRElementDefinition> children := ProfileUtilities.getChildList(definition, definition.snapshot.ElementList[0]);
  list2 := TAdvList<NarrativeGenerator.PropertyWrapper>();
  for (child : TFHIRElementDefinition : children) begin
  TAdvList<Element> elements := TAdvList<Element>();
  XMLUtil.getNamedChildrenWithWildcard(wrapped, tail(child.getPath()), elements);
  list2.add(new PropertyWrapperElement(definition, child, elements));
  end;
  end;
  return list2;
  end;
  end;
*)

{ TPropertyWrapper }

function TPropertyWrapper.getDefinition: String;
begin
  result := '';
end;

function TPropertyWrapper.getMaxCardinality: Integer;
begin
  result := 0;
end;

function TPropertyWrapper.getMinCardinality: Integer;
begin
  result := 0;
end;

function TPropertyWrapper.getName: String;
begin
  result := '';
end;

function TPropertyWrapper.getOwner: TFHIRObject;
begin
  result := nil;
end;

function TPropertyWrapper.getStructure: TFHIRStructureDefinition;
begin
  result := nil;
end;

function TPropertyWrapper.getTypeCode: String;
begin
  result := '';
end;

function TPropertyWrapper.getValues: TAdvList<TBaseWrapper>;
begin
  result := nil;
end;

function TPropertyWrapper.hasValues: boolean;
begin
  result := false;
end;

{ TResourceWrapper }

function TResourceWrapper.children: TAdvList<TPropertyWrapper>;
begin
  result := nil;
end;

function TResourceWrapper.getContained: TAdvList<TResourceWrapper>;
begin
  result := nil;
end;

function TResourceWrapper.getId: String;
begin
  result := '';
end;

function TResourceWrapper.getName: String;
begin
  result := '';
end;

function TResourceWrapper.getNarrative: TFHIRXhtmlNode;
begin
  result := nil;
end;

{ TBaseWrapper }

function TBaseWrapper.children: TAdvList<TPropertyWrapper>;
begin
  result := nil;
end;

function TBaseWrapper.getBase: TFHIRObject;
begin
  result := nil;
end;

function TBaseWrapper.getChildByName(tail: String): TPropertyWrapper;
begin
  result := nil;
end;

end.
