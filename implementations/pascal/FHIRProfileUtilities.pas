unit FHIRProfileUtilities;


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
  SysUtils, Classes,
  StringSupport,
  AdvObjects, AdvGenerics,
  FHIRBase, FHIRResources, FHIRTypes, FHIRUtilities, FHIRConstants;

Const
  DERIVATION_EQUALS = 'derivation.equals';
  IS_DERIVED = 'derived.fact';

type
  TValidationResult = class (TAdvObject)
  private
    FSeverity : TFhirIssueSeverity;
    FMessage  : String;
    FDisplay: String;
  public
    constructor Create; overload; virtual;
    constructor Create(Severity : TFhirIssueSeverity; Message : String); overload; virtual;
    constructor Create(display : String); overload; virtual;
    Property Severity : TFhirIssueSeverity read FSeverity write FSeverity;
    Property Message : String read FMessage write FMessage;
    Property Display : String read FDisplay write FDisplay;
    function isOk : boolean;

  end;

  TValidatorServiceProvider = {abstract} class (TAdvObject)
  public
    function fetchResource(t : TFhirResourceType; url : String) : TFhirResource; virtual; abstract;
    function expand(vs : TFhirValueSet) : TFHIRValueSet; virtual; abstract;
    function supportsSystem(system : string) : boolean; virtual; abstract;
    function validateCode(system, code, display : String) : TValidationResult; overload; virtual; abstract;
    function validateCode(system, code, version : String; vs : TFhirValueSet) : TValidationResult; overload; virtual; abstract;
    function validateCode(code : TFHIRCoding; vs : TFhirValueSet) : TValidationResult; overload; virtual; abstract;
    function validateCode(code : TFHIRCodeableConcept; vs : TFhirValueSet) : TValidationResult; overload; virtual; abstract;
  end;

  { for when we add table generation }
//  TExtensionContext = class (TAdvObject)
//  private
//    FDefinition : TFhirStructureDefinition;
//    FElement : TFhirElementDefinition;
//
//  public
//    Constructor Create(definition : TFhirStructureDefinition; element : TFhirElementDefinition);
//    Destructor Destroy; override;
//
//    Property Element : TFhirElementDefinition read FElement;
//    Property Definition : TFhirStructureDefinition read FDefinition;
//  end;

  TProfileUtilities = class (TAdvObject)
  private
    context : TValidatorServiceProvider;
    messages : TFhirOperationOutcomeIssueList;
    procedure log(message : String);
    function fixedPath(contextPath, pathSimple : String) : String;
    function  getDiffMatches(context : TFhirStructureDefinitionDifferential; path : String; istart, iend : integer;  profileName : String) : TAdvList<TFhirElementDefinition>;
    function updateURLs(url : String; element : TFhirElementDefinition) : TFhirElementDefinition;
    procedure updateFromBase(derived, base : TFhirElementDefinition);
    procedure markDerived(outcome : TFhirElementDefinition);
    procedure updateFromDefinition(dest, source : TFhirElementDefinition; pn : String; trimDifferential : boolean; purl :  String);
    function isDataType(value : String) : boolean; overload;
    function isDataType(types : TFhirElementDefinitionTypeList) : boolean; overload;
    function typeCode(types : TFhirElementDefinitionTypeList) : String;
    function pathStartsWith(p1, p2 : String) : boolean;
    function getProfileForDataType(type_ : TFhirElementDefinitionType) : TFHIRStructureDefinition;
    function unbounded(definition : TFhirElementDefinition) : boolean;
    function isSlicedToOneOnly(definition : TFhirElementDefinition) : boolean;
    function isExtension(definition : TFhirElementDefinition) : boolean;
    function makeExtensionSlicing : TFhirElementDefinitionSlicing;
    function findEndOfElement(context : TFhirStructureDefinitionDifferential; cursor : integer) : integer; overload;
    function findEndOfElement(context : TFhirStructureDefinitionSnapshot; cursor : integer) : integer; overload;
    function orderMatches(diff, base : TFHIRBoolean) : boolean;
    function discriiminatorMatches(diff, base : TFhirStringList) : boolean;
    function ruleMatches(diff, base : TFhirResourceSlicingRules) : boolean;
    function summariseSlicing(slice : TFhirElementDefinitionSlicing) : String;
    procedure updateFromSlicing(dst, src : TFhirElementDefinitionSlicing);
    function getSiblings(list : TFhirElementDefinitionList; current : TFhirElementDefinition) : TAdvList<TFhirElementDefinition>;
    procedure processPaths(result, base : TFhirStructureDefinitionSnapshot; differential: TFhirStructureDefinitionDifferential; baseCursor, diffCursor, baseLimit, diffLimit : integer; url, profileName, contextPath : String; trimDifferential : boolean; contextName, resultPathBase : String; slicingHandled : boolean);
    function populate(profile: TFHIRStructureDefinition; item: TFHIRObject; definition: TFHIRElementDefinition; stack: TAdvList<TFhirElementDefinition>): TFHIRResource;
    function getFirstCode(ed: TFHIRElementDefinition): TFhirCoding;
  public
    Constructor create(context : TValidatorServiceProvider; messages : TFhirOperationOutcomeIssueList);
    {
       * Given a base (snapshot) profile structure, and a differential profile, generate a snapshot profile
       *
       * @param base - the base structure on which the differential will be applied
       * @param differential - the differential to apply to the base
       * @param url - where the base has relative urls for profile references, these need to be converted to absolutes by prepending this URL
       * @return
       * @throws Exception
       }
    procedure generateSnapshot(base, derived : TFHIRStructureDefinition; url, profileName : String);
    function populateByProfile(profile : TFHIRStructureDefinition) : TFHIRResource;
  end;


function uncapitalize(s : String) : string;
function capitalize(s : String) : string;

implementation

{ TProfileUtilities }

constructor TProfileUtilities.create(context : TValidatorServiceProvider; messages : TFhirOperationOutcomeIssueList);
begin
  inherited Create;
  self.context := context;
  self.messages := messages;
end;

procedure TProfileUtilities.generateSnapshot(base, derived : TFHIRStructureDefinition; url, profileName : String);
var
  baseCursor, diffCursor: Integer;
begin
  if (base = nil) then
    raise Exception.create('no base profile provided');
  if (derived = nil) then
    raise Exception.create('no derived structure provided');

  derived.Snapshot := TFhirStructureDefinitionSnapshot.create();

  // so we have two lists - the base list, and the differential list
  // the differential list is only allowed to include things that are in the base list, but
  // is allowed to include them multiple times - thereby slicing them

  // our approach is to walk through the base list, and see whether the differential
  // says anything about them.
  baseCursor := 0;
  diffCursor := 0; // we need a diff cursor because we can only look ahead, in the bound scoped by longer paths

  // we actually delegate the work to a subroutine so we can re-enter it with a different cursors
  processPaths(derived.Snapshot, base.Snapshot, derived.Differential, baseCursor, diffCursor, base.Snapshot.elementList.Count-1, derived.differential.elementList.Count-1, url, derived.Id, '', false, base.Url, '', false);

end;

function pathTail(d : TFHIRElementDefinition) : String;
var
  s : String;
begin
  if d.Path.contains('.') then
    s := d.Path.substring(d.Path.lastIndexOf('.')+1)
  else
    s := d.Path;
  if (d.type_List.Count > 0) and (d.type_List[0].profileList.Count > 0) then
    result := '.' + s
  else
    result := '.' + s + '['+d.type_List[0].profileList[0].value+']';
end;

procedure TProfileUtilities.processPaths(result, base : TFhirStructureDefinitionSnapshot; differential: TFhirStructureDefinitionDifferential; baseCursor, diffCursor, baseLimit, diffLimit : integer;
      url, profileName, contextPath : String; trimDifferential : boolean; contextName, resultPathBase : String; slicingHandled : boolean);
var
  currentBase : TFhirElementDefinition;
  cpath, path, p : String;
  diffMatches : TAdvList<TFhirElementDefinition>;
  outcome, original, baseItem, diffItem, template : TFhirElementDefinition;
  dt, sd : TFHIRStructureDefinition;
  nbl, ndc, ndl, start, i, diffpos : integer;
  closed, isExt : boolean;
  dSlice, bSlice : TFhirElementDefinitionSlicing;
  baseMatches : TAdvList<TFhirElementDefinition>;
begin
  // just repeat processing entries until we run out of our allowed scope (1st entry, the allowed scope is all the entries)
  while (baseCursor <= baseLimit) do
  begin
    // get the current focus of the base, and decide what to do
    currentBase := base.elementList[baseCursor];
    cpath := fixedPath(contextPath, currentBase.path);
    diffMatches := getDiffMatches(differential, cpath, diffCursor, diffLimit, profileName); // get a list of matching elements in scope

    // in the simple case, source is not sliced.
    if (currentBase.slicing = nil) then
    begin
      if (diffMatches.count = 0) then
      begin // the differential doesn't say anything about this item
        log(cpath+': no match in the differential');
        // so we just copy it in
        outcome := updateURLs(url, currentBase.Clone());
        outcome.path := fixedPath(contextPath, outcome.path);
        updateFromBase(outcome, currentBase);
        markDerived(outcome);
        if (resultPathBase = '') then
          resultPathBase := outcome.path
        else if (not outcome.path.startsWith(resultPathBase)) then
          raise Exception.create('Adding wrong path');
        result.elementList.add(outcome);
        inc(baseCursor);
      end
      else if (diffMatches.Count = 1) and (slicingHandled or (diffMatches[0].slicing = nil)) then
      begin // one matching element in the differential
        log(cpath+': single match in the differential at '+inttostr(diffCursor));
        template := nil;
        if (diffMatches[0].type_List.Count = 1) and (diffMatches[0].type_List[0].profileList.count > 0) and (diffMatches[0].type_List[0].Code <> 'Reference') then
        begin
          p := diffMatches[0].type_List[0].profileList[0].value;
          sd := context.fetchResource(frtStructureDefinition, p) as TFhirStructureDefinition;
          if (sd <> nil) then
          begin
            template := sd.Snapshot.elementList[0].Clone;
            template.Path := currentBase.path;
            if (diffMatches[0].type_List[0].Code <> 'Extension') then
            begin
              template.min := currentBase.min;
              template.max := currentBase.max;
            end;
          end;
        end;
        if (template = nil) then
          template := currentBase.Clone;
        outcome := updateURLs(url, template);
        outcome.path := fixedPath(contextPath, outcome.path);
        updateFromBase(outcome, currentBase);
        outcome.name := diffMatches[0].Name;
        outcome.slicing := nil;
        updateFromDefinition(outcome, diffMatches[0], profileName, trimDifferential, url);
        if (outcome.path.endsWith('[x]')) and (outcome.type_List.Count = 1 ) and (outcome.type_List[0].code <> '*') then
          // if the base profile allows multiple types, but the profile only allows one, rename it
          outcome.path := outcome.path.substring(0, outcome.path.length-3)+ capitalize(outcome.type_List[0].code);
        if (resultPathBase = '') then
          resultPathBase := outcome.path
        else if (not outcome.path.startsWith(resultPathBase)) then
          raise Exception.create('Adding wrong path');
        result.elementList.add(outcome);
        inc(baseCursor);
        diffCursor := differential.elementList.indexOf(diffMatches[0])+1;
        if (differential.elementList.Count > diffCursor ) and ( outcome.path.contains('.') ) and ( isDataType(outcome.type_List)) then
        begin  // don't want to do this for the root, since that's base, and we're already processing it
          if (pathStartsWith(differential.elementList[diffCursor].path, fixedPath(contextPath, diffMatches[0].path+'.'))) then
          begin
            if (outcome.type_List.Count > 1) then
              raise Exception.create(diffMatches[0].path+' has children ('+differential.elementList[diffCursor].path+') and multiple types ('+typeCode(outcome.type_List)+') in profile '+profileName);
            dt := getProfileForDataType(outcome.type_List[0]);
            if (dt = nil) then
              raise Exception.create(diffMatches[0].path+' has children ('+differential.elementList[diffCursor].path+') for type '+typeCode(outcome.type_List)+' in profile '+profileName+', but can''t find type');
            log(cpath+': now walk into the profile '+dt.url);
            contextName := dt.url;
            start := diffCursor;
            while (differential.elementList.Count > diffCursor ) and ( pathStartsWith(differential.elementList[diffCursor].path, diffMatches[0].path+'.')) do
              inc(diffCursor);
            processPaths(result, dt.snapshot, differential, 1 { starting again on the data type, but skip the root }, start-1, dt.Snapshot.elementList.Count-1,
                diffCursor - 1, url, profileName+pathTail(diffMatches[0]), diffMatches[0].path, trimDifferential, contextName, resultPathBase, false);
          end;
        end;
      end
      else
      begin
        log(cpath+': differential slices this');

        // ok, the differential slices the item. Let's check our pre-conditions to ensure that this is correct
        if (not unbounded(currentBase)) and (not isSlicedToOneOnly(diffMatches[0])) then
          // you can only slice an element that doesn't repeat if the sum total of your slices is limited to 1
          // (but you might do that in order to split up constraints by type)
          raise Exception.create('Attempt to a slice an element that does not repeat: '+currentBase.path+'/'+currentBase.name+' from '+contextName);
        if (diffMatches[0].slicing = nil) and (not isExtension(currentBase)) then // well, the diff has set up a slice, but hasn't defined it. this is an error
          raise Exception.create('differential does not have a slice: '+currentBase.path);

        // well, if it passed those preconditions then we slice the dest.
        // we're just going to accept the differential slicing at face value
        outcome := updateURLs(url, currentBase.clone());
        outcome.path := fixedPath(contextPath, outcome.path);
        updateFromBase(outcome, currentBase);

        if (diffMatches[0].slicing = nil) then
          outcome.slicing := makeExtensionSlicing()
        else
          outcome.slicing := diffMatches[0].slicing.clone();
        if (not outcome.path.startsWith(resultPathBase)) then
          raise Exception.create('Adding wrong path');
        result.elementList.add(outcome);

        // differential - if the first one in the list has a name, we'll process it. Else we'll treat it as the base definition of the slice.
        start := 0;
        if (diffMatches[0].name = '') then
        begin
          updateFromDefinition(outcome, diffMatches[0], profileName, trimDifferential, url);
          if (outcome.type_List.Count = 0) then
            raise Exception.create('not done yet');
          start := 1;
        end;

        // now, for each entry in the diff matches, we're going to process the base item
        // our processing scope for base is all the children of the current path
        nbl := findEndOfElement(base, baseCursor);
        ndc := diffCursor;
        ndl := diffCursor;
        for i := start to diffMatches.Count-1 do
        begin
          // our processing scope for the differential is the item in the list, and all the items before the next one in the list
          ndc := differential.elementList.indexOf(diffMatches[i]);
          ndl := findEndOfElement(differential, ndc);
          // now we process the base scope repeatedly for each instance of the item in the differential list
          processPaths(result, base, differential, baseCursor, ndc, nbl, ndl, url, profileName+pathTail(diffMatches[i]), contextPath, trimDifferential, contextName, resultPathBase, true);
        end;
        // ok, done with that - next in the base list
        baseCursor := nbl+1;
        diffCursor := ndl+1;
      end;
    end
    else
    begin
      // the item is already sliced in the base profile.
      // here's the rules
      //  1. irrespective of whether the slicing is ordered or not, the definition order must be maintained
      //  2. slice element names have to match.
      //  3. TFHIR.createslices must be introduced at the end
      // corallory: you can't re-slice existing slices. is that ok?

      // we're going to need this:
      path := currentBase.path;
      original := currentBase;

      if (diffMatches.count = 0) then
      begin // the differential doesn't say anything about this item
        // copy across the currentbase, and all of it's children and siblings
        while (baseCursor < base.elementList.Count ) and ( base.elementList[baseCursor].path.startsWith(path)) do
        begin
          outcome := updateURLs(url, base.elementList[baseCursor].clone());
          if (not outcome.path.startsWith(resultPathBase)) then
            raise Exception.create('Adding wrong path');
          result.elementList.add(outcome); // so we just copy it in
          inc(baseCursor);
        end;
      end
      else
      begin
        // first - check that the slicing is ok
        closed := currentBase.slicing.rules = ResourceSlicingRulesClosed;
        diffpos := 0;
        isExt := cpath.endsWith('.extension') or cpath.endsWith('.modifierExtension');
        if (diffMatches[0].slicing <> nil) then
        begin // it might be nil if the differential doesn't want to say anything about slicing
          if (not isExt) then
            inc(diffpos); // if there's a slice on the first, we'll ignore any content it has
          dSlice := diffMatches[0].slicing;
          bSlice := currentBase.slicing;
          if (not orderMatches(dSlice.orderedElement, bSlice.orderedElement)) then
            raise Exception.create('Slicing rules on differential ('+summariseSlicing(dSlice)+') do not match those on base ('+summariseSlicing(bSlice)+') - order @ '+path+' ('+contextName+')');
          if (not discriiminatorMatches(dSlice.discriminatorList, bSlice.discriminatorList)) then
            raise Exception.create('Slicing rules on differential ('+summariseSlicing(dSlice)+') do not match those on base ('+summariseSlicing(bSlice)+') - disciminator @ '+path+' ('+contextName+')');
          if (not ruleMatches(dSlice.rules, bSlice.rules)) then
           raise Exception.create('Slicing rules on differential ('+summariseSlicing(dSlice)+') do not match those on base ('+summariseSlicing(bSlice)+') - rule @ '+path+' ('+contextName+')');
        end;
        outcome := updateURLs(url, currentBase.clone());
        outcome.path := fixedPath(contextPath, outcome.path);
        updateFromBase(outcome, currentBase);
        if (diffMatches[0].slicing <> nil) and (not isExt) then
        begin
          updateFromSlicing(outcome.slicing, diffMatches[0].slicing);
          updateFromDefinition(outcome, diffMatches[0], profileName, closed, url); // if there's no slice, we don't want to update the unsliced description
        end;
        result.elementList.add(outcome);

        // now, we have two lists, base and diff. we're going to work through base, looking for matches in diff.
        baseMatches := getSiblings(base.elementList, currentBase);
        for baseItem in baseMatches do
        begin
          baseCursor := base.elementList.indexOf(baseItem);
          outcome := updateURLs(url, baseItem.clone());
          updateFromBase(outcome, currentBase);
          outcome.path := fixedPath(contextPath, outcome.path);
          outcome.slicing := nil;
          if (not outcome.path.startsWith(resultPathBase)) then
            raise Exception.create('Adding wrong path');
          if (diffMatches[diffpos].name = '') and (diffMatches[diffpos].slicing <> nil) then
          begin
            inc(diffpos);
            // todo: check slicing details match
          end;
          if (diffpos < diffMatches.Count) and (diffMatches[diffpos].name = outcome.name) then
          begin
            // if there's a diff, we update the outcome with diff
            // no? updateFromDefinition(outcome, diffMatches.get(diffpos), profileName, pkp, closed, url);
            //then process any children
            nbl := findEndOfElement(base, baseCursor);
            ndc := differential.elementList.indexOf(diffMatches[diffpos]);
            ndl := findEndOfElement(differential, ndc);
            // now we process the base scope repeatedly for each instance of the item in the differential list
            processPaths(result, base, differential, baseCursor, ndc, nbl, ndl, url, profileName+pathTail(diffMatches[diffpos]), contextPath, closed, contextName, resultPathBase, true);
            // ok, done with that - now set the cursors for if this is the end
            baseCursor := nbl+1;
            diffCursor := ndl+1;
            inc(diffpos);
          end
          else
          begin
            result.elementList.add(outcome);
            inc(baseCursor);
            // just copy any children on the base
            while (baseCursor < base.elementList.Count) and (base.elementList[baseCursor].path.startsWith(path)) and (base.elementList[baseCursor].path <> path) do
            begin
              outcome := updateURLs(url, currentBase.clone());
              outcome.path := fixedPath(contextPath, outcome.path);
              if (not outcome.path.startsWith(resultPathBase)) then
                raise Exception.create('Adding wrong path');
              result.elementList.add(outcome);
              inc(baseCursor);
            end;
          end;
        end;
        // finally, we process any remaining entries in diff, which are TFHIR.create(and which are only allowed if the base wasn't closed
        if (closed) and (diffpos < diffMatches.Count) then
          raise Exception.create('The base snapshot marks a slicing as closed, but the differential tries to extend it in '+profileName+' at '+path+' ('+cpath+')');
        while (diffpos < diffMatches.Count) do
        begin
          diffItem := diffMatches[diffpos];
          for baseItem in baseMatches do
            if (baseItem.name = diffItem.name) then
              raise Exception.create('Named items are out of order in the slice');
          outcome := updateURLs(url, original.clone());
          outcome.path := fixedPath(contextPath, outcome.path);
          updateFromBase(outcome, currentBase);
          outcome.slicing := nil;
          if (not outcome.path.startsWith(resultPathBase)) then
            raise Exception.create('Adding wrong path');
          result.elementList.add(outcome);
          updateFromDefinition(outcome, diffItem, profileName, trimDifferential, url);
          inc(diffpos);
        end;
      end;
    end;
  end;
end;

procedure TProfileUtilities.markDerived(outcome : TFhirElementDefinition);
//var
//  inv : TFhirElementDefinitionConstraint;
begin
//  for inv in outcome.conditionList do
//    inv.Tags[IS_DERIVED] := true;
end;

function TProfileUtilities.summariseSlicing(slice : TFhirElementDefinitionSlicing) : String;
var
  b : TStringBuilder;
  first : boolean;
  d : TFhirString;
begin
  b := TStringBuilder.Create;
  try
    first := true;
    for  d in slice.discriminatorList do
    begin
      if (first) then
        first := false
      else
        b.append(', ');
      b.append(d);
    end;
    b.append('(');
    if (slice.orderedElement <> nil) then
      b.append(slice.OrderedElement.StringValue);
    b.append('/');
    if (slice.rulesElement <> nil) then
      b.append(slice.rulesElement.StringValue);
    b.append(')');
    if (slice.description <> '') then
    begin
      b.append('"');
      b.append(slice.description);
      b.append('"');
    end;
    result := b.toString();
  finally
    b.Free;
  end;
end;

procedure TProfileUtilities.updateFromBase(derived, base : TFhirElementDefinition);
begin
  if (base.base <> nil) then
  begin
    derived.base := TFhirElementDefinitionBase.Create;
    derived.base.path := base.base.path;
    derived.base.Min := base.base.min;
    derived.base.Max := base.base.Max;
  end
  else
  begin
    derived.base := TFhirElementDefinitionBase.Create;
    derived.base.path := base.path;
    derived.base.Min := base.Min;
    derived.base.Max := base.Max;
  end;
end;

function TProfileUtilities.pathStartsWith(p1, p2 : String) : boolean;
begin
  result := p1.startsWith(p2);
end;

function GetProperty(props : TFHIRPropertyList; ed : TFhirElementDefinition) : TFhirProperty;
var
  t : String;
  i : integer;
begin
  if ed.path.Contains('.') then
    t := ed.path.Substring(ed.path.LastIndexOf('.')+1)
  else
    t := ed.path;
  for i := 0 to props.Count - 1 do
  begin
    if (props[i].Name = t) or ((props[i].Name.EndsWith('[x]') and t.StartsWith(props[i].Name.Substring(0, props[i].Name.Length-3)))) then
    begin
      result := props[i];
      exit;
    end;
  end;
  raise Exception.Create('Unable to find property for '+ed.path);
end;

function wantGenerate(name, path : String) : boolean;
begin
  if (name = 'language') and (path.CountChar('.') = 1) then // don't generate language for resources, but otherwise generate it
    result := false
  else
    result := StringArrayIndexOfInsensitive(['contained', 'extension', 'modifierExtension', 'resource', 'id', 'implicitRules'], name) = -1;
end;

function TProfileUtilities.getFirstCode(ed : TFHIRElementDefinition) : TFHIRCoding;
var
  vs : TFHIRValueSet;
begin
  if (ed.binding = nil) or (ed.binding.valueSet = nil) or (ed.binding.valueSet is TFHIRUri) then
    result := nil
  else
  begin
    vs := context.fetchResource(frtValueSet, (ed.binding.valueSet as TFhirReference).reference) as TFhirValueSet;
    if vs.codeSystem <> nil then
    begin
      result := TFhirCoding.Create;
      result.system := vs.codeSystem.system;
      result.code := vs.codeSystem.conceptList[0].code;
      result.display := vs.codeSystem.conceptList[0].display;
    end
    else
    begin
      vs := context.expand(vs);
      try
        if (vs = nil) then
          result := nil
        else
        begin
          result := TFhirCoding.Create;
          result.system := vs.expansion.containsList[0].system;
          result.code := vs.expansion.containsList[0].code;
          result.display := vs.expansion.containsList[0].display;
        end;
      finally
        vs.Free;
      end;
    end;
  end;
end;

function TProfileUtilities.populate(profile: TFHIRStructureDefinition; item : TFHIRObject; definition : TFHIRElementDefinition; stack : TAdvList<TFhirElementDefinition>): TFHIRResource;
var
  children : TFhirElementDefinitionList;
  ed : TFhirElementDefinition;
  pn, t, pr : String;
  props : TFHIRPropertyList;
  prop : TFHIRProperty;
  value : TFhirElement;
  coding : TFhirCoding;
begin
  if stack.Contains(definition) then
    exit; // prevent recursion
  stack.Add(definition.Link);
  try
    props := item.createPropertyList;
    try
      children := getChildMap(profile, definition.name, definition.path, definition.NameReference);
      try
        if children.Count = 0 then
        begin
          coding := getFirstCode(definition);
          try
            if item is TFhirElement then
              createBasicChildren(item as TFhirElement, coding);
          finally
            coding.Free;
          end;
        end
        else
          for ed in children do
            if ed.max <> '0' then
            begin
              prop := getProperty(props, ed);
              if (ed.fixed <> nil) then
                item.setProperty(prop.Name, ed.fixed.link)
              else if (ed.pattern <> nil) then
                item.setProperty(prop.Name, ed.pattern.link)
              else if (ed.defaultValue <> nil) then
                item.setProperty(prop.Name, ed.defaultValue.link)
              else if (wantGenerate(prop.name, ed.path) or (ed.min <> '0')) then
              begin
                case ed.type_List.Count of
                  0 : t := '';
                  1 : t := ed.type_List[0].code;
                else
                  t := ed.type_List[random(ed.type_List.Count)].code;
                end;
                if (t = '') or (t = 'Element') or (t = 'BackboneElement') then
                  value := TFHIRObjectClass(prop.Class_).Create as TFHIRElement
                else
                  value := CreateTypeByName(t);
                try
                  populate(profile, value, ed, stack);
                  item.setProperty(prop.Name, value.Link);
                finally
                  value.Free;
                end;
              end;
            end;
      finally
        children.Free;
      end;
    finally
      props.Free;
    end;
  finally
    stack.Delete(stack.Count - 1);
  end;
end;

function TProfileUtilities.populateByProfile(profile: TFHIRStructureDefinition): TFHIRResource;
var
  path : String;
  estack : TAdvList<TFhirElementDefinition>;
begin
  if profile.kind <> StructureDefinitionKindResource then
    raise Exception.Create('Unsuitable type of profile for creating a resource');
  if profile.snapshot = nil then
    raise Exception.Create('Unsuitable profile for creating a resource - no snapshot');

  if profile.constrainedType <> '' then
    path := profile.constrainedType
  else
    path := profile.name;
  estack := TAdvList<TFhirElementDefinition>.create;
  try
    result := CreateResourceByName(path);
    try
      populate(profile, result, profile.snapshot.elementList[0], estack);
      if profile.constrainedType <> '' then
      begin
        if result.meta = nil then
          result.meta := TFhirMeta.Create;
        result.meta.profileList.Append.value := profile.url;
      end;

      result.Link;
    finally
      result.Free;
    end;
  finally
    estack.free;
  end;
end;

function pathMatches(p1, p2 : string) : boolean;
begin
  result := (p1 = p2) or (p2.endsWith('[x]')) and (p1.startsWith(p2.substring(0, p2.length-3))) and (not p1.substring(p2.length-3).contains('.'));
end;


function TProfileUtilities.fixedPath(contextPath, pathSimple : String) : String;
begin
  if (contextPath = '') then
    result := pathSimple
  else
    result := contextPath+'.'+pathSimple.substring(pathSimple.indexOf('.')+1);
end;


function TProfileUtilities.getProfileForDataType(type_ : TFhirElementDefinitionType) : TFHIRStructureDefinition;
begin
  result := nil;
  if (type_.profileList.Count > 0) then
    result := context.fetchResource(frtStructureDefinition, type_.profileList[0].StringValue) as TFhirStructureDefinition;
  if (result = nil) then
    result := context.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/'+type_.code) as TFhirStructureDefinition;
  if (result = nil) then
    writeln('XX: failed to find profle for type: ' + type_.code); // debug GJM
end;

function TProfileUtilities.typeCode(types : TFhirElementDefinitionTypeList) : String;
var
  b : TStringBuilder;
  first : boolean;
  type_ : TFHIRElementDefinitionType;
begin
  b := TStringBuilder.Create;
  try
    first := true;
    for type_ in types do
    begin
      if (first) then
        first := false
      else
        b.append(', ');
      b.append(type_.code);
      if (type_.profileList.count > 1) then
        b.append('{'+type_.profileList[0].StringValue+'}');
    end;
    result := b.toString();
  finally
    b.Free;
  end;
end;


function TProfileUtilities.isDataType(value : String) : boolean;
begin
  result := StringArrayExistsSensitive(['Identifier', 'HumanName', 'Address', 'ContactPoint', 'Timing', 'SimpleQuantity', 'Quantity', 'Attachment', 'Range',
          'Period', 'Ratio', 'CodeableConcept', 'Coding', 'SampledData', 'Age', 'Distance', 'Duration', 'Count', 'Money'], value);
end;


function isPrimitive(value : String) : boolean;
begin
  result := (value = '') or StringArrayExistsInSensitive(['boolean', 'integer', 'decimal', 'base64Binary', 'instant', 'string', 'date', 'dateTime', 'code', 'oid', 'uuid', 'id', 'uri'], value);
end;


function TProfileUtilities.isDataType(types : TFhirElementDefinitionTypeList) : boolean;
var
  type_ : TFHIRElementDefinitionType;
  t : String;
begin
  if (types.count = 0) then
    result := false
  else
  begin
    result := true;
    for type_ in types do
    begin
    t := type_.code;
      if (not isDataType(t)) and (t <> 'Reference') and (t <> 'Narrative') and (t <> 'Extension') and (t <> 'ElementDefinition') and not isPrimitive(t) then
        result := false;
    end;
  end;
end;


{   * Finds internal references in an Element's Binding and StructureDefinition references (in TypeRef) and bases them on the given url
   * @param url - the base url to use to turn internal references into absolute references
   * @param element - the Element to update
   * @return - the updated Element
}

function TProfileUtilities.updateURLs(url : String; element : TFhirElementDefinition) : TFhirElementDefinition;
var
  defn : TFhirElementDefinition;
  t : TFhirElementDefinitionType;
  tp : TFhirUri;
begin
  if (element <> nil) then
  begin
    defn := element;
    if (defn.binding <> nil) and (defn.binding.valueSet is TFHIRReference) and (TFHIRReference(defn.binding.valueSet).reference.startsWith('#')) then
      TFHIRReference(defn.binding.valueSet).reference := url+TFHIRReference(defn.binding.valueSet).reference;
    for t in defn.type_List do
    begin
      for tp in t.profileList do
      begin
        if (tp.value.startsWith('#')) then
          tp.value := url+tp.value;
      end;
    end;
  end;
  result := element;
end;

function TProfileUtilities.getSiblings(list : TFhirElementDefinitionList; current : TFhirElementDefinition) : TAdvList<TFhirElementDefinition>;
var
  path : String;
  cursor : integer;
begin
  result := TAdvList<TFhirElementDefinition>.create;
  path := current.path;
  cursor := list.indexOf(current)+1;
  while (cursor < list.Count) and (list[cursor].path.length >= path.length) do
  begin
    if (pathMatches(list[cursor].path, path)) then
      result.add(list[cursor].Link);
    inc(cursor);
  end;
end;

procedure TProfileUtilities.updateFromSlicing(dst, src : TFhirElementDefinitionSlicing);
begin
  if (src.orderedElement <> nil) then
    dst.orderedElement := src.orderedElement.clone();
  if (src.discriminatorList.Count > 0) then
    dst.discriminatorList.addAll(src.discriminatorList);
  if (src.rulesElement <> nil) then
    dst.rulesElement := src.rulesElement.clone();
end;

function TProfileUtilities.orderMatches(diff, base : TFHIRBoolean) : boolean;
begin
  result := (diff = nil) or (base = nil) or (diff.value = base.value);
end;

function TProfileUtilities.discriiminatorMatches(diff, base : TFhirStringList) : boolean;
var
  i : integer;
begin
  if (diff.count = 0) or (base.count = 0) then
    result := true
  else if (diff.Count <> base.Count) then
    result := false
  else
  begin
    result := true;
    for i := 0 to diff.Count - 1 do
      if (diff[i].value <> base[i].value) then
        result := false;
  end;
end;

function TProfileUtilities.ruleMatches(diff, base : TFhirResourceSlicingRules) : boolean;
begin
  result := (diff = ResourceSlicingRulesNull) or (base = ResourceSlicingRulesNull) or (diff = base) or (diff = ResourceSlicingRulesOPEN) or
        ((diff = ResourceSlicingRulesOPENATEND) and (base = ResourceSlicingRulesCLOSED));
end;

function TProfileUtilities.isSlicedToOneOnly(definition : TFhirElementDefinition) : boolean;
begin
  result := (definition.slicing <> nil) and (definition.MaxElement <> Nil) and (definition.max = '1');
end;

procedure TProfileUtilities.log(message: String);
begin

end;

function TProfileUtilities.makeExtensionSlicing : TFhirElementDefinitionSlicing;
begin
  result := TFhirElementDefinitionSlicing.create;
  result.discriminatorList.Add(TFHIRString.create('url'));
  result.ordered := false;
  result.rules := ResourceSlicingRulesOpen;
end;

function TProfileUtilities.isExtension(definition : TFhirElementDefinition) : boolean;
begin
  result := definition.path.endsWith('.extension') or definition.path.endsWith('.modifierExtension');
end;

function  TProfileUtilities.getDiffMatches(context : TFhirStructureDefinitionDifferential; path : String; istart, iend : integer;  profileName : String) : TAdvList<TFhirElementDefinition>;
var
  i : integer;
  statedPath : String;
begin
  result := TAdvList<TFhirElementDefinition>.create;
  for i := istart to iend do
  begin
    statedPath := context.elementList[i].path;
    if (statedPath = path) or (path.endsWith('[x]') and (statedPath.length > path.length - 2) and (statedPath.substring(0, path.length-3) = path.substring(0, path.length-3)))
        and (not statedPath.substring(path.length).contains('.')) then
      result.add(context.elementList[i].Link)
    else if (result.count = 0) then
    begin
//        writeln('ignoring '+statedPath+' in differential of '+profileName);
    end;
  end;
end;

function TProfileUtilities.findEndOfElement(context : TFhirStructureDefinitionDifferential; cursor : integer) : integer;
var
  path : String;
begin
  result := cursor;
  path := context.elementList[cursor].path+'.';
  while (result < context.elementList.Count- 1) and (context.elementList[result+1].path.startsWith(path)) do
    inc(result);
end;

function TProfileUtilities.findEndOfElement(context : TFhirStructureDefinitionSnapshot; cursor : integer) : integer;
var
  path : String;
begin
  result := cursor;
  path := context.elementList[cursor].path+'.';
  while (result < context.elementList.Count- 1) and (context.elementList[result+1].path.startsWith(path)) do
    inc(result);
end;


function TProfileUtilities.unbounded(definition : TFhirElementDefinition) : boolean;
var
  max : String;
begin
  max := definition.max;
  if (max = '') then
    result := false // this is not valid
  else if (max = '1') then
    result := false
  else if (max = '0') then
    result := false
  else
    result := true;
end;

function  isLargerMax(derived, base : String) : boolean;
begin
  if ('*' = base) then
    result := false
  else if ('*' = derived) then
    result := true
  else
    result := StrToInt(derived) > StrToInt(base);
end;

function inExpansion(cc : TFHIRValueSetExpansionContains; contains : TFhirValueSetExpansionContainsList) : boolean;
var
  cc1 : TFhirValueSetExpansionContains;
begin
  result := false;
  for cc1 in contains do
  begin
    if (cc.system = cc1.system) and (cc.code = cc1.code) then
      result := true;
    if inExpansion(cc, cc1.containsList) then
      result := true;
  end;
end;

function  codesInExpansion(contains : TFhirValueSetExpansionContainsList; expansion : TFHIRValueSetExpansion) : boolean;
var
  cc : TFhirValueSetExpansionContains;
begin
  result := true;
  for cc in contains do
  begin
    if not inExpansion(cc, expansion.containsList) then
        result := false;
    if not codesInExpansion(cc.containsList, expansion) then
        result := false;
  end;
end;


function isResource(s : String) : boolean;
begin
  result := StringArrayExistsSensitive(CODES_TFHIRResourceType, s);
end;


function isSubset(expBase, expDerived : TFhirValueSet) : boolean;
begin
  result := codesInExpansion(expDerived.expansion.containsList, expBase.expansion);
end;


procedure TProfileUtilities.updateFromDefinition(dest, source : TFhirElementDefinition; pn : String; trimDifferential : boolean; purl :  String);
var
  base, derived : TFhirElementDefinition;
  isExtension, ok : boolean;
  s : TFHIRString;
  expBase, expDerived: TFHIRValueSet;
  ts, td : TFhirElementDefinitionType;
  b : TStringList;
  ms, md : TFhirElementDefinitionMapping;
  cs : TFhirElementDefinitionConstraint;
begin
  // we start with a clone of the base profile ('dest') and we copy from the profile ('source')
  // over the top for anything the source has
  base := dest;
  derived := source;
//  derived.Tags[DERIVATION_POINTER] := base;

  if (derived <> nil) then
  begin
    // see task 3970. For an extension, there's no point copying across all the underlying definitional stuff
    isExtension := (base.path = 'Extension') or base.path.endsWith('.extension') or base.path.endsWith('.modifierExtension');
    if (isExtension) then
    begin
      base.Definition := 'An Extension';
      base.Short := 'Extension';
      base.comments := '';
      base.requirements := '';
      base.aliasList.clear();
      base.mappingList.clear();
    end;

    if (derived.shortElement <> nil) then
    begin
      if not compareDeep(derived.shortElement, base.shortElement, false) then
        base.shortElement := derived.shortElement.clone()
      else if (trimDifferential) then
        derived.shortElement := nil
      else
        derived.shortElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.DefinitionElement <> nil) then
    begin
      if (derived.definition.startsWith('...')) then
        base.definition := base.definition+#13#10+derived.definition.substring(3)
      else if not compareDeep(derived.definitionElement, base.definitionElement, false) then
        base.definitionElement := derived.DefinitionElement.clone()
      else if (trimDifferential) then
        derived.DefinitionElement := nil
      else
        derived.DefinitionElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.CommentsElement <> nil) then
    begin
      if (derived.comments.startsWith('...')) then
        base.comments := base.comments+#13#10+derived.comments.substring(3)
      else if not compareDeep(derived.commentsElement, base.commentsElement, false) then
        base.CommentsElement := derived.CommentsElement.clone()
      else if (trimDifferential) then
        derived.CommentsElement := nil
      else
        derived.CommentsElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.RequirementsElement <> nil) then
    begin
      if (derived.requirements.startsWith('...')) then
        base.requirements := base.requirements+#13#10+derived.requirements.substring(3)
      else if not compareDeep(derived.requirementsElement, base.requirementsElement, false) then
        base.RequirementsElement := derived.requirementsElement.clone()
      else if (trimDifferential) then
        derived.RequirementsElement := nil
      else
        derived.requirementsElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    // sdf-9
    if (derived.requirements <> '') and (not base.path.contains('.')) then
      derived.requirements := '';
    if (base.requirements <> '') and (not base.path.contains('.')) then
      base.requirements := '';

    if (derived.aliasList.Count > 0) then
    begin
      if not compareDeep(derived.aliasList, base.aliasList, false) then
        for s in derived.aliasList do
        begin
          if not base.aliasList.hasValue(s.value) then
            base.aliasList.add(s.Clone);
        end
      else if (trimDifferential) then
        derived.aliasList.clear()
      else
        for s in derived.aliasList do
          s.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.MinElement <> nil) then
    begin
      if (not compareDeep(derived.minElement, base.minElement, false)) then
      begin
        if (derived.min < base.min) then
          messages.add(TFhirOperationOutcomeIssue.create(IssueSeverityERROR, IssueTypeBUSINESSRULE, pn+'.'+derived.path, 'Derived min  ('+derived.min+') cannot be less than base min ('+base.min+')'));
        base.MinElement := derived.MinElement.clone();
      end
      else if (trimDifferential) then
        derived.minElement := nil
      else
        derived.minElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.MaxElement <> nil) then
    begin
      if (not compareDeep(derived.maxElement, base.maxElement, false)) then
      begin
        if isLargerMax(derived.max, base.max) then
          messages.add(TFhirOperationOutcomeIssue.create(IssueSeverityERROR, IssueTypeBUSINESSRULE, pn+'.'+derived.path, 'Derived max ('+derived.max+') cannot be greater than base max ('+base.max+')'));
        base.maxElement := derived.maxElement.clone();
      end
      else if (trimDifferential) then
        derived.maxElement := nil
      else
        derived.maxElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.Fixed <> nil) then
    begin
      if not compareDeep(derived.fixed, base.fixed, true) then
        base.fixed := derived.fixed.clone()
      else if (trimDifferential) then
        derived.fixed := nil
      else
        derived.fixed.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.Pattern <> nil) then
    begin
      if not compareDeep(derived.pattern, base.pattern, false) then
        base.pattern := derived.pattern.clone()
      else if (trimDifferential) then
        derived.Pattern := nil
      else
        derived.pattern.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.Example <> nil) then
    begin
      if (not compareDeep(derived.example, base.example, false)) then
        base.example := derived.example.clone()
      else if (trimDifferential) then
        derived.example := nil
      else
        derived.example.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.MaxLengthElement <> nil) then
    begin
      if (not compareDeep(derived.maxLengthElement, base.maxLengthElement, false)) then
        base.maxLengthElement := derived.maxLengthElement.clone()
      else if (trimDifferential) then
        derived.maxLengthElement := nil
      else
        derived.maxLengthElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    // todo: what to do about conditions?
    // condition : id 0..*

    if (derived.MustSupportElement <> nil) then
    begin
      if (not compareDeep(derived.mustSupportElement, base.mustSupportElement, false)) then
        base.mustSupportElement := derived.mustSupportElement.clone()
      else if (trimDifferential) then
        derived.mustSupportElement := nil
      else
        derived.mustSupportElement.Tags[DERIVATION_EQUALS] := 'true';
    end;


    // profiles cannot change : isModifier, defaultValue, meaningWhenMissing
    // but extensions can change isModifier
    if (isExtension) then
    begin
      if (not compareDeep(derived.isModifierElement, base.isModifierElement, false)) then
        base.isModifierElement := derived.isModifierElement.clone()
      else if (trimDifferential) then
        derived.isModifierElement := nil
      else
        derived.isModifierElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.Binding <> nil) then
    begin
      if (not compareDeep(derived.binding, base.binding, false)) then
      begin
        if (base.binding <> nil ) and ( base.binding.strength = BindingStrengthREQUIRED ) and ( derived.binding.strength <> BindingStrengthREQUIRED) then
          messages.add(TFhirOperationOutcomeIssue.create(IssueSeverityERROR, IssueTypeBUSINESSRULE, pn+'.'+derived.path, 'illegal attempt to change a binding from '+CODES_TFhirBindingStrength[base.binding.strength]+' to '+CODES_TFhirBindingStrength[derived.binding.strength]))
//            raise Exception.create('StructureDefinition '+pn+' at '+derived.path+': illegal attempt to change a binding from '+base.binding.strength.toCode()+' to '+derived.binding.strength.toCode());
        else if (base.binding <> nil) and (derived.binding <> nil) and (base.binding.strength = BindingStrengthREQUIRED) then
        begin
          expBase := context.expand(context.fetchResource(frtValueSet, (base.binding.valueSet as TFhirReference).reference) as TFhirValueSet);
          expDerived := context.expand(context.fetchResource(frtValueSet, (base.binding.valueSet as TFhirReference).reference) as TFhirValueSet);
          if (expBase = nil) then
            messages.add(TFhirOperationOutcomeIssue.create(IssueSeverityWARNING, IssueTypeBUSINESSRULE, pn+'.'+base.path, 'Binding '+(base.binding.valueSet as TFhirReference).reference+' could not be expanded'))
          else if (expDerived = nil) then
            messages.add(TFhirOperationOutcomeIssue.create(IssueSeverityWARNING, IssueTypeBUSINESSRULE, pn+'.'+derived.path, 'Binding '+(derived.binding.valueSet as TFhirReference).reference+' could not be expanded'))
          else if not isSubset(expBase, expDerived) then
            messages.add(TFhirOperationOutcomeIssue.create(IssueSeverityERROR, IssueTypeBUSINESSRULE, pn+'.'+derived.path, 'Binding '+(derived.binding.valueSet as TFhirReference).reference+' is not a subset of binding '+(base.binding.valueSet as TFhirReference).reference));
        end;
        base.binding := derived.binding.clone();
      end
      else if (trimDifferential) then
        derived.binding := nil
      else
        derived.binding.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.IsSummaryElement <> nil) then
    begin
      if (not compareDeep(derived.isSummaryElement, base.isSummaryElement, false)) then
        base.isSummaryElement := derived.isSummaryElement.clone()
      else if (trimDifferential) then
        derived.isSummaryElement := nil
      else
        derived.isSummaryElement.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.type_List.Count > 0) then
    begin
      if not compareDeep(derived.type_List, base.type_List, false) then
      begin
        if (base.type_List.Count > 0) then
        begin
          for ts in derived.type_List do
          begin
            ok := false;
            b := TStringList.Create;
            try
              for td in base.type_List do
              begin
                b.add(td.code);
                if (td.code= ts.code) or (td.code = 'Extension') or (td.code = 'Element') or (td.code = '*') or
                    (((td.code = 'Resource') or (td.code = 'DomainResource')) and isResource(ts.code)) then
                  ok := true;
              end;
              if (not ok) then
                raise Exception.create('StructureDefinition '+pn+' at '+derived.path+': illegal constrained type '+ts.code+' from '+b.CommaText);
            finally
              b.Free;
            end;
          end;
        end;
        base.type_List.clear();
        for ts in derived.type_List do
        begin
          td := ts.clone();
//            tt.Tags[DERIVATION_EQUALS] := 'true';
          base.type_List.add(td);
        end;
      end
      else if (trimDifferential) then
        derived.type_List.clear()
      else
        for ts in derived.type_List do
          ts.Tags[DERIVATION_EQUALS] := 'true';
    end;

    if (derived.mappingList.Count > 0) then
    begin
      // todo: mappings are not cumulative - one replaces another
      if (not compareDeep(derived.mappingList, base.mappingList, false)) then
      begin
        for ms in derived.mappingList do
        begin
          ok := false;
          for md in base.mappingList do
            ok := ok or (md.identity = ms.identity) and (md.map = ms.map);
          if (not ok) then
            base.mappingList.add(ms.Clone);
        end;
      end
      else if (trimDifferential) then
        derived.mappingList.clear()
      else
        for ms in derived.mappingList do
          ms.Tags[DERIVATION_EQUALS] := 'true';
    end;

    // todo: constraints are cumulative. there is no replacing
    for cs in base.constraintList do
      cs.Tags[IS_DERIVED] := 'true';
    if derived.constraintList.Count > 0 then
      for cs in derived.constraintList do
        base.constraintList.add(cs.clone());
  end;
end;


function uncapitalize(s : String) : string;
begin
  result := Lowercase(s[1])+s.Substring(1);
end;

function capitalize(s : String) : string;
begin
  result := UpperCase(s[1])+s.Substring(1);
end;

{ TValidationResult }

constructor TValidationResult.Create(Severity: TFhirIssueSeverity; Message: String);
begin
  inherited create;
  FSeverity := Severity;
  FMessage := Message;
end;

constructor TValidationResult.Create;
begin
  Inherited Create;
end;

constructor TValidationResult.Create(display: String);
begin
  inherited Create;
  FDisplay := display;
end;

function TValidationResult.isOk: boolean;
begin
  result := message = '';
end;


//{ TExtensionContext }
//
//constructor TExtensionContext.Create(definition: TFhirStructureDefinition;
//  element: TFhirElementDefinition);
//begin
//  FDefinition := definition;
//  FElement := element;
//end;
//
//destructor TExtensionContext.Destroy;
//begin
//  FDefinition.Free;
//  FElement.Free;
//  inherited;
//end;

end.
