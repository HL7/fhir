unit QuestionnaireBuilder;


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
  SysUtils, Classes, Generics.Collections,
  GUIDSupport, DateAndTime, AdvObjects, ShellSupport, StringSupport, AdvStringMatches, AdvExceptions,
  FHIRResources, FHIRTypes, FHIRConstants, FHIRBase, FHIRParser,
  FHIRUtilities, FHIRSupport, FHIRProfileUtilities;

Const
  TYPE_EXTENSION = 'http://www.healthintersections.com.au/fhir/StructureDefinition/questionnnaire-type';
  TYPE_REFERENCE = 'http://www.healthintersections.com.au/fhir/StructureDefinition/questionnnaire-reference';
  FLYOVER_REFERENCE = 'http://www.healthintersections.com.au/fhir/StructureDefinition/questionnnaire-flyover';
  EXTENSION_FILTER_ONLY = 'http://www.healthintersections.com.au/fhir/StructureDefinition/expandNeedsFilter';
  MaxListboxCodings = 20;


Type
  TGetValueSetExpansion = function(vs : TFHIRValueSet; ref : TFhirReference; limit, count, offset : integer; allowIncomplete : Boolean; dependencies : TStringList) : TFhirValueSet of object;
  TLookupCodeEvent = function(system, code : String) : String of object;
  TLookupReferenceEvent = function(Context : TFHIRRequest; uri : String) : TResourceWithReference of object;

  {
 * This class takes a StructureDefinition, and builds a questionnaire from it
 *
 * If you then convert this questionnaire to a form using the
 * XMLTools form builder, and then take the QuestionnaireResponse
 * this creates, you can use QuestionnaireInstanceConvert to
 * build an instance the conforms to the StructureDefinition
 *
 * FHIR context:
 *   conceptLocator, codeSystems, valueSets, maps, client, StructureDefinitions
 * You don't have to provide any of these, but
 * the more you provide, the better the conversion will be
 *
 * @author Grahame
  }
  TQuestionnaireBuilder = class (TAdvObject)
  private
    FProfiles : TProfileManager;
    lastid : integer;
    FResource: TFhirDomainResource;
    FProfile: TFHirStructureDefinition;
    FQuestionnaire: TFhirQuestionnaire;
    FAnswers: TFhirQuestionnaireResponse;
    FQuestionnaireId: String;
    FFactory : TFHIRFactory;
    FOnExpand : TGetValueSetExpansion;
    vsCache : TAdvStringMatch;
    FPrebuiltQuestionnaire: TFhirQuestionnaire;
    FOnLookupCode : TLookupCodeEvent;
    FOnLookupReference : TLookupReferenceEvent;
    FContext : TFHIRRequest;
    FDependencies: TList<String>;

    function nextId(prefix : string) : String;

    function getChildList(profile :TFHirStructureDefinition; path : String) : TFhirElementDefinitionList; overload;
    function getChildList(profile :TFHirStructureDefinition; element : TFhirElementDefinition) : TFhirElementDefinitionList; overload;
    function isExempt(element, child: TFhirElementDefinition) : boolean;

    function getSystemForCode(vs : TFHIRValueSet; code : String; path : String) : String;
    function resolveValueSet(profile : TFHirStructureDefinition; binding : TFhirElementDefinitionBinding) : TFHIRValueSet; overload;
    function resolveValueSet(url : String) : TFHIRValueSet; overload;
    function makeAnyValueSet : TFhirValueSet;

    function expandTypeList(types: TFhirElementDefinitionTypeList): TFhirElementDefinitionTypeList;
    function makeTypeList(profile : TFHirStructureDefinition; types : TFhirElementDefinitionTypeList; path : String) : TFHIRValueSet;
    function convertType(v: TFhirElement; t: string; path : String): TFhirElement; overload;
    function convertType(value : TFHIRObject; af : TFhirItemTypeEnum; vs : TFHIRValueSet; path : String) : TFhirType; overload;
    procedure selectTypes(profile : TFHirStructureDefinition; sub : TFHIRQuestionnaireItem; t: TFhirElementDefinitionType; source, dest: TFhirQuestionnaireResponseItemList);
    function instanceOf(t : TFhirElementDefinitionType; obj : TFHIRObject) : boolean;

    function addQuestion(group: TFHIRQuestionnaireItem; af: TFhirItemTypeEnum; path, id, name: String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList; vs : TFHIRValueSet = nil): TFHIRQuestionnaireItem;

    procedure addAddressQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addAgeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addAttachmentQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addBinaryQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addBooleanQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addCodeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addCodeableConceptQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addCodingQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addContactQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addDateTimeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addDecimalQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addDurationQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addExtensionQuestions(profile : TFHirStructureDefinition; group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; profileURL : String; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addHumanNameQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addIdRefQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addIdentifierQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addInstantQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addIntegerQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addPeriodQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addQuantityQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addRangeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addRatioQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addReferenceQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; profileURL : String; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addSampledDataQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addScheduleQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addStringQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addTimeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure addUriQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);

    function processAnswerGroup(group : TFhirQuestionnaireResponseItem; context : TFhirBase; defn :  TProfileDefinition) : boolean;

    procedure processDataType(profile : TFHirStructureDefinition; group: TFHIRQuestionnaireItem; element: TFhirElementDefinition; path: String; t: TFhirElementDefinitionType; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure buildQuestion(group : TFHIRQuestionnaireItem; profile : TFHirStructureDefinition; element : TFhirElementDefinition; path : String; answerGroups : TFhirQuestionnaireResponseItemList);
	  procedure buildGroup(group : TFHIRQuestionnaireItem; profile : TFHirStructureDefinition; element : TFhirElementDefinition; parents : TFhirElementDefinitionList; answerGroups : TFhirQuestionnaireResponseItemList);
    procedure processMetadata;
    procedure SetProfiles(const Value: TProfileManager);
    procedure SetProfile(const Value: TFHirStructureDefinition);
    procedure SetResource(const Value: TFhirDomainResource);
    procedure processExisting(path : String; answerGroups, nAnswers: TFhirQuestionnaireResponseItemList);
    procedure SetAnswers(const Value: TFhirQuestionnaireResponse);
    procedure SetPrebuiltQuestionnaire(const Value: TFhirQuestionnaire);
    procedure SetContext(const Value: TFHIRRequest);
  public
    Constructor Create; override;
    Destructor Destroy; override;

    Property Profiles : TProfileManager read FProfiles write SetProfiles;
    Property OnExpand : TGetValueSetExpansion read FOnExpand write FOnExpand;
    Property onLookupCode : TLookupCodeEvent read FonLookupCode write FonLookupCode;
    Property onLookupReference : TLookupReferenceEvent read FonLookupReference write FonLookupReference;
    Property Context : TFHIRRequest read FContext write SetContext;

    Property Profile : TFHirStructureDefinition read FProfile write SetProfile;
    Property Resource : TFhirDomainResource read FResource write SetResource;
    Property Questionnaire : TFhirQuestionnaire read FQuestionnaire;
    Property Answers : TFhirQuestionnaireResponse read FAnswers write SetAnswers;
    Property QuestionnaireId : String read FQuestionnaireId write FQuestionnaireId;
    Property Dependencies : TList<String> read FDependencies;

    // sometimes, when this is used, the questionnaire is already build and cached, and we are
    // processing the answers. for technical reasons, we still go through the process, but
    // we don't do the intensive parts of the work (save time)
    Property PrebuiltQuestionnaire : TFhirQuestionnaire read FPrebuiltQuestionnaire write SetPrebuiltQuestionnaire;

    procedure Build;
    procedure UnBuild;
  end;

implementation

Uses
  NarrativeGenerator;

Function tail(path : String) : String;
begin
  result := path.substring(path.lastIndexOf('.')+1);
end;

{ TQuestionnaireBuilder }

procedure TQuestionnaireBuilder.build;
var
  list : TFhirElementDefinitionList;
  answerGroups : TFhirQuestionnaireResponseItemList;
  item : TFHIRQuestionnaireItem;
begin
  if profile = nil then
    raise Exception.Create('QuestionnaireBuilder.build: No Profile provided');

  if resource <> nil then
    if profile.snapshot.elementList[0].path <> CODES_TFhirResourceType[resource.ResourceType] then
      raise Exception.Create('Wrong Type');

  if FPrebuiltQuestionnaire <> nil then
    FQuestionnaire := FPrebuiltQuestionnaire.Link
  else
    FQuestionnaire := TFHIRQuestionnaire.Create();
  if resource <> nil then
    FAnswers := TFhirQuestionnaireResponse.Create;
  processMetadata;


  list := TFhirElementDefinitionList.Create;
  answerGroups := TFhirQuestionnaireResponseItemList.create;
  try
    if resource <> nil then
      answerGroups.AddAll(FAnswers.itemList);
    if PrebuiltQuestionnaire <> nil then
    begin
     // give it a fake group to build
     item := TFHIRQuestionnaireItem.Create;
     try
      buildGroup(item, profile, profile.snapshot.elementList[0], list, answerGroups);
     finally
       item.free;
     end;
    end
    else
      for item in FQuestionnaire.itemList do
        buildGroup(item, profile, profile.snapshot.elementList[0], list, answerGroups);
  finally
    list.Free;
    answerGroups.Free;
  end;

  if FAnswers <> nil then
    FAnswers.collapseAllContained;
end;

procedure TQuestionnaireBuilder.processExisting(path : String; answerGroups, nAnswers: TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
  ans: TFhirQuestionnaireResponseItem;
  children: TFHIRObjectList;
  ch : TFHIRObject;
begin
  // processing existing data
  for ag in answerGroups do
  begin
    children := TFHIRObjectList.Create;
    try
      TFHIRObject(ag.tag).ListChildrenByName(tail(path), children);
      for ch in children do
        if ch <> nil then
        begin
          ans := ag.itemList.Append;
          ans.Tag := ch.Link;
          nAnswers.add(ans.link);
        end;
    finally
      children.Free;
    end;
  end;
end;

destructor TQuestionnaireBuilder.Destroy;
begin
  FDependencies.Free;
  vsCache.Free;
  FResource.Free;
  FProfile.Free;
  FQuestionnaire.Free;
  FAnswers.Free;
  FProfiles.Free;
  FPrebuiltQuestionnaire.Free;
  FContext.free;
  inherited;
end;

function convertStatus(status : TFhirConformanceResourceStatusEnum) : TFHIRQuestionnaireStatusEnum;
begin
  case (status) of
		ConformanceResourceStatusActive: result := QuestionnaireStatusPublished;
		ConformanceResourceStatusDraft: result := QuestionnaireStatusDraft;
		ConformanceResourceStatusRetired : result := QuestionnaireStatusRetired;
	else
  result := QuestionnaireStatusNull;
	end;
end;
procedure TQuestionnaireBuilder.processMetadata;
var
  id : TFhirIdentifier;
begin
  // todo: can we derive a more informative identifier from the questionnaire if we have a profile
  if FPrebuiltQuestionnaire = nil then
  begin
    id := FQuestionnaire.identifierList.Append;
    id.System := 'urn:ietf:rfc:3986';
    id.Value := FQuestionnaireId;
    FQuestionnaire.Version := profile.Version;
    FQuestionnaire.Status := convertStatus(profile.Status);
    FQuestionnaire.Date := profile.Date.link;
    FQuestionnaire.publisher := profile.Publisher;
    FQuestionnaire.ItemList.Add(TFHIRQuestionnaireItem.Create);
    FQuestionnaire.itemList[0].conceptList.AddAll(profile.codeList);
  end;

  if FAnswers <> nil then
  begin
    // no identifier - this is transient
    FQuestionnaire.xmlId := nextId('qs');
    FAnswers.questionnaire := TFhirReference.Create;
    FAnswers.questionnaire.reference := '#'+FQuestionnaire.xmlId;
    FAnswers.containedList.Add(FQuestionnaire.Link);
    FAnswers.status := QuestionnaireAnswersStatusInProgress;
    FAnswers.ItemList.Add(TFhirQuestionnaireResponseItem.Create);
    FAnswers.ItemList[0].Tag := FResource.Link;
  end;
end;

function TQuestionnaireBuilder.resolveValueSet(url: String): TFHIRValueSet;
var
  ref : TFhirReference;
  dependencies : TStringList;
  s : String;
begin
  result := nil;
  if PrebuiltQuestionnaire <> nil then
    exit; // we don't do anything with value sets in this case

  if vsCache.ExistsByKey(url) then
    result := FQuestionnaire.contained[vsCache.GetValueByKey(url)].link as TFhirValueSet
  else
  begin
    ref := TFhirReference.Create;
    dependencies := TStringList.create;
    try
      ref.reference := url;
      try
        result := OnExpand(nil, ref, MaxListboxCodings, 0, 0, false, dependencies);
        for s in dependencies do
          if not FDependencies.Contains(s) then
            FDependencies.Add(s);
      except
        on e: ETooCostly do
        begin
          result := TFhirValueSet.Create;
          try
            result.url := ref.reference;
            result.link;
          finally
            result.Free;
          end;
        end;
        on e : Exception do
        begin
          recordStack(e);
          raise;
        end;
      end;
    finally
      dependencies.Free;
      ref.Free;
    end;
  end;
end;

function TQuestionnaireBuilder.resolveValueSet(profile: TFHirStructureDefinition; binding: TFhirElementDefinitionBinding): TFHIRValueSet;
var
  ref : TFhirReference;
  vs : TFHIRValueSet;
  dependencies : TStringList;
  s : String;
begin
  result := nil;
  if PrebuiltQuestionnaire <> nil then
    exit; // we don't do anything with value sets in this case

  if (binding = nil) or not (binding.Valueset is TFhirReference) then
    exit;

  dependencies := TStringList.create;
  try
    ref := binding.valueset as TFhirReference;
    if ref.reference.StartsWith('#') then
    begin
      vs := TFhirValueSet(Fprofile.contained[ref.reference.Substring(1)]);
      try
        result := OnExpand(vs, nil, MaxListboxCodings, 0, 0, false, dependencies);
        for s in dependencies do
          if not FDependencies.Contains(s) then
            FDependencies.Add(s);
      except
        on e: ETooCostly do
        begin
          result := TFhirValueSet.Create;
          try
            result.url := ref.reference;
            result.link;
          finally
            result.Free;
          end;
        end;
        on e : Exception do
        begin
          recordStack(e);
          raise;
        end;
      end;

    end
    else if vsCache.ExistsByKey(ref.reference) then
      result := FQuestionnaire.contained[vsCache.GetValueByKey(ref.reference)].link as TFhirValueSet
    else
      try
        result := OnExpand(nil, ref, MaxListboxCodings, 0, 0,false, dependencies);
        for s in dependencies do
          if not FDependencies.Contains(s) then
            FDependencies.Add(s);
      except
        on e: ETooCostly do
        begin
          result := TFhirValueSet.Create;
          try
            result.url := ref.reference;
            result.link;
          finally
            result.Free;
          end;
        end;
        on e : Exception do
        begin
          recordStack(e);
          raise;
        end;
      end;
  finally
    dependencies.Free;
  end;
end;

procedure TQuestionnaireBuilder.SetAnswers(const Value: TFhirQuestionnaireResponse);
begin
  FAnswers.Free;
  FAnswers := Value;
end;

procedure TQuestionnaireBuilder.SetContext(const Value: TFHIRRequest);
begin
  FContext.Free;
  FContext := Value;
end;

procedure TQuestionnaireBuilder.SetPrebuiltQuestionnaire(const Value: TFhirQuestionnaire);
begin
  FPrebuiltQuestionnaire.Free;
  FPrebuiltQuestionnaire := Value;
end;

procedure TQuestionnaireBuilder.SetProfile(const Value: TFHirStructureDefinition);
begin
  FProfile.Free;
  FProfile := Value;
end;

procedure TQuestionnaireBuilder.SetProfiles(const Value: TProfileManager);
begin
  FProfiles.Free;
  FProfiles := Value;
end;

procedure TQuestionnaireBuilder.SetResource(const Value: TFhirDomainResource);
begin
  FResource.Free;
  FResource := Value;
end;

procedure TQuestionnaireBuilder.UnBuild;
var
  defn : TProfileDefinition;
  gen : TNarrativeGenerator;
begin
  if Profile = nil then
    raise Exception.Create('A Profile is required');

  if Answers = nil then
    raise Exception.Create('A set of answers is required');


  Resource := FFactory.makeByName(profile.snapshot.elementList[0].path) as TFhirDomainResource;

  defn := TProfileDefinition.create(profiles.Link, profile.link);
  try
    processAnswerGroup(Answers.itemList[0], resource, defn);
  finally
    defn.free;
  end;

  gen := TNarrativeGenerator.Create('', FProfiles.Link, OnLookupCode, onLookupReference, context.Link);
  try
    gen.generate(Resource, profile);
  finally
    gen.Free;
  end;
end;

function TQuestionnaireBuilder.convertType(v : TFhirElement; t : string; path : String) : TFhirElement;
var
  s : String;
begin
  s := v.FhirType;
  if (s = t) then
    result := v.link
  else if ((s = 'string') and (t = 'code')) then
    result := TFhirEnum.Create(TFHIRString(v).value)
  else if ((s = 'string') and (t = 'uri')) then
    result := TFhirUri.Create(TFHIRString(v).value)
  else if ((s = 'Coding') and (t = 'code')) then
    result := TFhirEnum.Create(TFHIRCoding(v).code)
  else if ((s = 'dateTime') and (t = 'date')) then
    result := TFhirDate.Create(TFhirDateTime(v).value.Link)
  else
    raise Exception.Create('Unable to convert from '+s+' to '+t+' at path = '+path);
end;

function isPrimitive(t : TFhirElementDefinitionType) : Boolean; overload;
begin
  result := (t <> nil) and (StringArrayExistsSensitive(['string', 'code', 'boolean', 'integer', 'decimal', 'date', 'dateTime', 'instant', 'time', 'ResourceReference'], t.code));
end;

function allTypesSame(types : TFhirElementDefinitionTypeList) : boolean;
var
  t : TFhirElementDefinitionType;
  s : String;
begin
  result := true;
  s := types[0].code;
  for t in types do
    if s <> t.code then
      result := false;
end;

function determineType(g : TFhirQuestionnaireResponseItem) : TFhirElementDefinitionType;
var
  q : TFhirQuestionnaireResponseItem;
  cc : TFhirCoding;
begin
  result := nil;
  if (g.itemList.Count <> 1) then
    exit;
  q := g.itemList[0];
  if (q.linkId <> g.linkId+'._type') then
    exit;
  if q.answerList.Count <> 1 then
    exit;
  if q.answerList[0].value is TFhirCoding then
  begin
    cc := TFhirCoding(q.answerList[0].value);
    result := TFhirElementDefinitionType.Create;
    try
      result.tags['type'] := cc.code;
      if cc.system = 'http://hl7.org/fhir/resource-types' then
      begin
        result.code := 'Reference';
        result.profileList.Add(TFHIRString.create('http://hl7.org/fhir/Profile/'+cc.code));
      end
      else // cc.system = 'http://hl7.org/fhir/data-types'
      begin
        result.code := cc.code;
      end;
      result.Link;
    finally
      result.Free;
    end;
  end;
end;

function selectTypeGroup(g : TFhirQuestionnaireResponseItem; t : TFhirElementDefinitionType) : TFhirQuestionnaireResponseItem;
var
  qg : TFhirQuestionnaireResponseItem;
begin
  result := nil;
  for qg in g.itemList[0].answerList[0].itemList do
    if qg.linkId = g.linkId+'._'+t.Tags['type'] then
      result := qg;
end;

function TQuestionnaireBuilder.processAnswerGroup(group : TFhirQuestionnaireResponseItem; context : TFhirBase; defn :  TProfileDefinition) : boolean;
var
  g, g1 : TFhirQuestionnaireResponseItem;
  q : TFhirQuestionnaireResponseItem;
  a : TFhirQuestionnaireResponseItemAnswer;
  d : TProfileDefinition;
  t : TFhirElementDefinitionType;
  o : TFHIRBase;
begin
  result := false;

  for g1 in group.itemList do
  begin
    g := g1;
    d := defn.getById(g.linkId);
    try
      t := nil;
      try
        if d.hasTypeChoice then
        begin
          t := determineType(g);
          d.setType(t.link);
          // now, select the group for the type
          g := selectTypeGroup(g, t);
        end
        else
          t := d.statedType.link;

        if ((g <> nil) and (t <> nil)) or not d.hasTypeChoice then
        begin
          if (isPrimitive(t)) then
          begin
            if (g.itemList.Count <> 1) then
              raise Exception.Create('Unexpected Condition: a group for a primitive type with more than one question @ '+g.linkId);
            if (g.itemList.Count > 0) then
              raise Exception.Create('Unexpected Condition: a group for a primitive type with groups @ '+g.linkId);
            q := g.itemList[0];
            for a in q.answerList do
            begin
              if a.value <> nil then
              begin
                context.setProperty(d.name, convertType(a.value, t.code, g.linkId));
                result := true;
              end
              else
                raise Exception.Create('Empty answer for '+g.linkId);
            end;
          end
          else
          begin
            if t = nil then
              o := FFactory.makeByName(d.path)
            else
              o := FFactory.makeByName(t.code);
            try
              if processAnswerGroup(g, o, d) then
              begin
                context.setProperty(d.name, o.Link);
                result := true;
              end;
            finally
              o.Free;
            end;
          end;
        end;
      finally
        t.Free;
      end;
    finally
      d.free;
    end;
  end;

  for q in group.itemList do
  begin
    d := defn.getById(q.linkId);
    try
      if d.hasTypeChoice then
        raise Exception.Create('not done yet - shouldn''t get here??');
      for a in q.answerList do
      begin
        context.setProperty(d.name, convertType(a.value, d.statedType.code, q.linkId));
        result := true;
      end;
    finally
      d.free;
    end;
  end;
end;

procedure TQuestionnaireBuilder.buildGroup(group: TFHIRQuestionnaireItem; profile: TFHirStructureDefinition; element: TFhirElementDefinition; parents: TFhirElementDefinitionList; answerGroups : TFhirQuestionnaireResponseItemList);
var
  list : TFhirElementDefinitionList;
  child : TFhirElementDefinition;
  nparents : TFhirElementDefinitionList;
  childGroup : TFHIRQuestionnaireItem;
  nAnswers : TFhirQuestionnaireResponseItemList;
  ag : TFhirQuestionnaireResponseItem;
begin
  group.LinkId := element.Path; // todo: this will be wrong when we start slicing
  group.Text := element.comments;
  group.SetExtensionString(FLYOVER_REFERENCE, element.definition);
  group.Required := element.Min > '0';
  group.Repeats := element.Max <> '1';

  for ag in answerGroups do
  begin
    ag.linkId := group.linkId;
    ag.Text := group.Text;
  end;

  // now, we iterate the children
  list := getChildList(profile, element);
  try
    for child in list do
    begin
      if (not isExempt(element, child)) and (not parents.ExistsByReference(child)) then
      begin
        nparents := TFhirElementDefinitionList.Create;
        try
          nparents.Assign(parents);
          nparents.add(child.link);
          childGroup := group.itemList.Append;

          nAnswers := TFhirQuestionnaireResponseItemList.Create;
          try
             processExisting(child.path, answerGroups, nAnswers);
            // if the element has a type, we add a question. else we add a group on the basis that
            // it will have children of it's own
            if (child.type_List.isEmpty) then
              buildGroup(childGroup, profile, child, nparents, nAnswers)
            else
              buildQuestion(childGroup, profile, child, child.path, nAnswers);
          finally
            nAnswers.Free;
          end;
        finally
          nparents.Free;
        end;
      end;
	  end;
  finally
    list.Free;
  end;
end;

function TQuestionnaireBuilder.getChildList(profile :TFHirStructureDefinition; path : String) : TFhirElementDefinitionList;
var
  e : TFhirElementDefinition;
  p, tail : String;
begin
  result := TFhirElementDefinitionList.Create;
  try
    for e in profile.snapshot.elementList do
    begin
      p := e.path;

      if (e.nameReference <> '') and path.startsWith(p) then
      begin
        result.Free;
        if (path.length > p.length) then
          result := getChildList(profile, e.NameReference+'.'+path.substring(p.length+1))
        else
          result := getChildList(profile, e.NameReference);
      end
      else if p.startsWith(path+'.') and (p <> path) then
      begin
        tail := p.substring(path.length+1);
        if (not tail.contains('.')) then
          result.add(e.Link);
      end;
    end;
    result.link;
  finally
    result.Free;
  end;
end;


function TQuestionnaireBuilder.getChildList(profile :TFHirStructureDefinition; element : TFhirElementDefinition) : TFhirElementDefinitionList;
begin
  result := getChildList(profile, element.Path);
end;

function TQuestionnaireBuilder.getSystemForCode(vs: TFHIRValueSet; code: String; path : String): String;
var
  r : TFhirResource;
  cc : TFhirValueSetExpansionContains;
begin
  if (vs = nil) then
  begin
    if FPrebuiltQuestionnaire <> nil then
    begin
      for r in FPrebuiltQuestionnaire.containedList do
        if r is TFhirValueSet then
        begin
          vs := TFhirValueSet(r);
          if (vs.expansion <> nil) then
          begin
            for cc in vs.expansion.containsList do
              if cc.code = code then
                if result = '' then
                  result := cc.system
                 else
                  raise Exception.Create('Multiple matches in '+vs.url+' for code '+code+' at path = '+path);
          end;
        end;
    end;
    raise Exception.Create('Logic error'+' at path = '+path);
  end;
  result := '';
  for cc in vs.expansion.containsList Do
  begin
    if cc.code = code then
      if result = '' then
        result := cc.system
      else
        raise Exception.Create('Multiple matches in '+vs.url+' for code '+code+' at path = '+path);
  end;
  if result = '' then
    raise Exception.Create('Unable to resolve code '+code+' at path = '+path);
end;

function TQuestionnaireBuilder.isExempt(element, child: TFhirElementDefinition) : boolean;
var
  n, t : string;
begin
  n := tail(child.Path);
  if not element.type_List.isEmpty then
    t :=  element.type_List[0].Code;

  // we don't generate questions for the base stuff in every element
	if (t = 'Resource') and
				((n = 'text') or (n = 'language') or (n = 'contained')) then
    result := true
		// we don't generate questions for extensions
	else if (n = 'extension') or (n = 'modifierExtension') then
  begin
    if (child.type_List.Count > 0) and (child.type_List[0].profile <> '') then
      result := false
    else
      result := true
  end
  else
	  result := false;
end;

function TQuestionnaireBuilder.expandTypeList(types: TFhirElementDefinitionTypeList): TFhirElementDefinitionTypeList;
var
  t : TFhirElementDefinitionType;
begin
  result := TFhirElementDefinitionTypeList.create;
  try
    for t in types do
    begin
      if (t.profileList.Count > 0) then
        result.Add(t.Link)
      else if (t.code = '*') then
      begin
        result.Append.code := 'boolean';
        result.Append.code := 'integer';
        result.Append.code := 'decimal';
        result.Append.code := 'base64Binary';
        result.Append.code := 'instant';
        result.Append.code := 'string';
        result.Append.code := 'uri';
        result.Append.code := 'date';
        result.Append.code := 'dateTime';
        result.Append.code := 'time';
        result.Append.code := 'code';
        result.Append.code := 'oid';
        result.Append.code := 'uuid';
        result.Append.code := 'id';
        result.Append.code := 'unsignedInt';
        result.Append.code := 'positiveInt';
        result.Append.code := 'markdown';
        result.Append.code := 'Annotation';
        result.Append.code := 'Attachment';
        result.Append.code := 'Identifier';
        result.Append.code := 'CodeableConcept';
        result.Append.code := 'Coding';
        result.Append.code := 'Quantity';
        result.Append.code := 'Range';
        result.Append.code := 'Period';
        result.Append.code := 'Ratio';
        result.Append.code := 'SampledData';
        result.Append.code := 'Signature';
        result.Append.code := 'HumanName';
        result.Append.code := 'Address';
        result.Append.code := 'ContactPoint';
        result.Append.code := 'Timing';
        result.Append.code := 'Reference';
        result.Append.code := 'Meta';
      end
      else
        result.Add(t.Link);
    end;
    result.Link;
  finally
    result.Free;
  end;
end;

function TQuestionnaireBuilder.makeAnyValueSet: TFhirValueSet;
begin
  if vsCache.ExistsByKey(ANY_CODE_VS) then
    result := FQuestionnaire.contained[vsCache.GetValueByKey(ANY_CODE_VS)].link as TFhirValueSet
  else
  begin
    result := TFhirValueSet.Create;
    try
      result.url := ANY_CODE_VS;
      result.name := 'All codes known to the system';
      result.description := 'All codes known to the system';
      result.status := ConformanceResourceStatusActive;
      result.compose := TFhirValueSetCompose.create;
      result.compose.includeList.Append.system := ANY_CODE_VS;
      result.link;
    finally
      result.Free;
    end;
  end;

end;

function TQuestionnaireBuilder.makeTypeList(profile : TFHirStructureDefinition; types: TFhirElementDefinitionTypeList; path : String): TFHIRValueSet;
var
  vs : TFhirValueset;
  t : TFhirElementDefinitionType;
  cc : TFhirValueSetExpansionContains;
begin
  vs := TFhirValueset.Create;
  try
    vs.url := NewGuidURN;
    vs.name := 'Type options for '+path;
    vs.description := vs.name;
    vs.status := ConformanceResourceStatusActive;
    vs.expansion := TFhirValueSetExpansion.Create;
    vs.expansion.timestamp := NowUTC;
    for t in types do
    begin
      cc := vs.expansion.containsList.Append;
      if (t.code = 'Reference') and (t.profile.startsWith('http://hl7.org/fhir/Profile/')) then
      begin
        cc.code := t.profile.Substring(28);
        cc.system := 'http://hl7.org/fhir/resource-types';
        cc.display := cc.code;
      end
      else if (t.profile <> '') and FProfiles.getProfileStructure(profile, t.profile, profile) then
      begin
        cc.code := t.profile;
        cc.display := profile.name;
        cc.system := 'http://hl7.org/fhir/resource-types';
      end
      else
      begin
        cc.code := t.code;
        cc.display := t.code;
        cc.system := 'http://hl7.org/fhir/data-types';
      end;
      t.Tags['code'] := cc.code;
    end;
    result := vs.Link;
  finally
    vs.Free;
  end;
end;

function TQuestionnaireBuilder.nextId(prefix : string): String;
begin
  inc(lastid);
  result := prefix+inttostr(lastid);
end;

function TQuestionnaireBuilder.instanceOf(t : TFhirElementDefinitionType; obj : TFHIRObject) : boolean;
var
  url : String;
begin
  if t.code = 'Reference' then
  begin
    if not (obj is TFhirReference) then
      result := false
    else
    begin
      url := TFhirReference(obj).reference;
      {
      there are several problems here around profile matching. This process is degenerative, and there's probably nothing we can do to solve it
      }
      if url.StartsWith('http:') or url.StartsWith('https:') then
        result := true
      else if (t.profile.startsWith('http://hl7.org/fhir/Profile/')) then
        result := url.StartsWith(t.profile.Substring(28)+'/')
      else
        result := true;
    end;
  end
  else if t.code = 'Quantity' then
    result := obj is TFHIRQuantity
  else
    raise Exception.Create('Not Done Yet');
end;

procedure TQuestionnaireBuilder.selectTypes(profile : TFHirStructureDefinition; sub : TFHIRQuestionnaireItem; t : TFhirElementDefinitionType; source, dest : TFhirQuestionnaireResponseItemList);
var
  temp : TFhirQuestionnaireResponseItemList;
  subg : TFhirQuestionnaireResponseItem;
  q : TFhirQuestionnaireResponseItem;
  cc : TFhirCoding;
  ag : TFhirQuestionnaireResponseItem;
begin
  temp := TFhirQuestionnaireResponseItemList.Create;
  try
    for ag in source do
      if instanceOf(t, ag.tag as TFHIRObject) then
        temp.add(ag.link);
    for ag in temp do
      source.DeleteByReference(ag);
    for ag in temp do
    begin
      // 1st the answer:
      assert(ag.itemList.count = 0); // it should be empty
      q := ag.itemList.Append;
      q.linkId := ag.linkId+'._type';
      q.text := 'type';

      cc := TFHIRCoding.Create;
      q.answerList.append.value := cc;
      if (t.code = 'Reference') and (t.profile.startsWith('http://hl7.org/fhir/Profile/')) then
      begin
        cc.code := t.profile.Substring(28);
        cc.system := 'http://hl7.org/fhir/resource-types';
      end
      else if (t.profile <> '') and FProfiles.getProfileStructure(profile, t.profile, profile) then
      begin
        cc.code := t.profile;
        cc.system := 'http://hl7.org/fhir/resource-types';
      end
      else
      begin
        cc.code := t.code;
        cc.system := 'http://hl7.org/fhir/data-types';
      end;

      // 1st: create the subgroup
      subg := q.answerList[0].itemList.Append;
      dest.Add(subg.Link);
      subg.linkId := sub.linkId;
      subg.text := sub.text;
      subg.Tag := ag.Tag.Link;

    end;
  finally
    temp.Free;
  end;
end;

// most of the types are complex in regard to the Questionnaire, so they are still groups
	// there will be questions for each component
procedure TQuestionnaireBuilder.buildQuestion(group : TFHIRQuestionnaireItem; profile : TFHirStructureDefinition; element : TFhirElementDefinition; path : String; answerGroups : TFhirQuestionnaireResponseItemList);
var
  t : TFhirElementDefinitionType;
  q : TFHIRQuestionnaireItem;
  types : TFhirElementDefinitionTypeList;
  sub : TFHIRQuestionnaireItem;
  selected : TFhirQuestionnaireResponseItemList;
  ag : TFhirQuestionnaireResponseItem;
begin
  group.LinkId := path;

  // in this context, we don't have any concepts to mark...
  group.Text := element.Short; // prefix with name?
  group.Required := element.Min > '0';
  group.Repeats := element.Max <> '1';

  for ag in answerGroups do
  begin
    ag.linkId := group.linkId;
    ag.Text := group.Text;
  end;

  if element.comments <> '' then
    group.setExtensionString(FLYOVER_REFERENCE, element.definition+' '+element.comments)
  else
    group.setExtensionString(FLYOVER_REFERENCE, element.definition);

  if (element.type_List.Count > 1) or (element.type_List[0].Code = '*') then
  begin
    types := expandTypeList(element.type_List);
    try
      q := addQuestion(group, itemTypeChoice, element.path, '_type', 'type', true, nil, makeTypeList(profile, types, element.path));
      for t in types do
      begin
        sub := q.itemList.Append;
        sub.LinkId := element.Path+'._'+t.tags['type'];
        sub.Text := t.tags['type'];
        // always optional, never repeats

        selected := TFhirQuestionnaireResponseItemList.create;
        try
          selectTypes(profile, sub, t, answerGroups, selected);
          processDataType(profile, sub, element, element.Path+'._'+t.tags['type'], t, group.required, selected);
        finally
          selected.free;
        end;
      end;
    finally
      types.free;
    end;
  end
  else
    // now we have to build the question panel for each different data type
    processDataType(profile, group, element, element.Path, element.Type_list[0], group.required, answerGroups);
end;

procedure TQuestionnaireBuilder.processDataType(profile : TFHirStructureDefinition; group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; t : TFhirElementDefinitionType; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  if (t.Code = 'code') then
    addCodeQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'string') or (t.Code = 'id') or (t.Code = 'oid') then
    addStringQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'uri') then
    addUriQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'boolean') then
    addBooleanQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'decimal') then
    addDecimalQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'dateTime') or (t.Code = 'date') then
    addDateTimeQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'instant') then
    addInstantQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'time') then
    addTimeQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'CodeableConcept') then
    addCodeableConceptQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Period') then
    addPeriodQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Ratio') then
    addRatioQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'HumanName') then
    addHumanNameQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Address') then
    addAddressQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Contact') then
    addContactQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Identifier') then
    addIdentifierQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'integer') then
    addIntegerQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Coding') then
    addCodingQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Quantity') then
    addQuantityQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Reference') then
    addReferenceQuestions(group, element, path, required, t.profile, answerGroups)
  else if (t.Code = 'idref') then
    addIdRefQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Duration') then
    addDurationQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'base64Binary') then
    addBinaryQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Attachment') then
    addAttachmentQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Age') then
    addAgeQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Range') then
    addRangeQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Schedule') then
    addScheduleQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'SampledData') then
    addSampledDataQuestions(group, element, path, required, answerGroups)
  else if (t.Code = 'Extension') then
    addExtensionQuestions(profile, group, element, path, required, t.profile, answerGroups)
  else if (t.Code <> 'Meta') and (t.Code <> 'Narrative') and (t.Code <> 'Resource') then
    raise Exception.create('Unhandled Data Type: '+t.Code+' on element '+element.Path);
end;

function isPrimitive(obj : TAdvObject) : boolean; overload;
begin
  result := (obj is TFHIRBoolean) or (obj is TFHIRInteger) or (obj is TFHIRDecimal) or (obj is TFHIRBase64Binary) or (obj is TFHIRInstant) or (obj is TFHIRString) or (obj is TFHIRUri) or
            (obj is TFHIRDate) or (obj is TFHIRDateTime) or (obj is TFHIRTime) or (obj is TFHIRCode) or (obj is TFHIROid) or (obj is TFHIRUuid) or (obj is TFHIRId) or (obj is TFhirReference);
end;

function TQuestionnaireBuilder.convertType(value : TFHIRObject; af : TFhirItemTypeEnum; vs : TFHIRValueSet; path : String) : TFhirType;
begin
  result := nil;
  case af of
    // simple cases
    itemTypeBoolean: if value is TFhirBoolean then result := value.link as TFhirType;
    itemTypeDecimal: if value is TFhirDecimal then result := value.link as TFhirType;
    itemTypeInteger: if value is TFhirInteger then result := value.link as TFhirType;
    itemTypeDate: if value is TFhirDate then result := value.link as TFhirType;
    itemTypeDateTime: if value is TFhirDateTime then result := value.link as TFhirType;
    itemTypeInstant: if value is TFhirInstant then result := value.link as TFhirType;
    itemTypeTime: if value is TFhirTime then result := value.link as TFhirType;
    itemTypeString:
      if value is TFhirString then
        result := value.link as TFhirType
      else if value is TFhirUri then
        result := TFHIRString.Create(TFhirUri(value).value);

    itemTypeText: if value is TFhirString then result := value.link as TFhirType;
    itemTypeQuantity: if value is TFhirQuantity then result := value.link as TFhirType;

    // complex cases:
    // ? itemTypeAttachment: ...?
    itemTypeChoice, itemTypeOpenChoice :
      if value is TFhirCoding then
        result := value.link as TFhirType
      else if value is TFHIREnum then
      begin
        result := TFhirCoding.create;
        TFhirCoding(result).code := TFHIREnum(value).value;
        TFhirCoding(result).system := getSystemForCode(vs, TFHIREnum(value).value, path);
      end
      else if value is TFHIRString then
      begin
        result := TFhirCoding.create;
        TFhirCoding(result).code := TFHIRString(value).value;
        TFhirCoding(result).system := getSystemForCode(vs, TFHIRString(value).value, path);
      end;

    itemTypeReference:
      if value is TFhirReference then
        result := value.link as TFhirType
      else if value is TFHIRString then
      begin
        result := TFhirReference.Create;
        TFhirReference(result).reference := TFHIRString(value).value;
      end;
  end;

  if (result = nil) then
    raise Exception.Create('Unable to convert from "'+value.className+'" for Answer Format '+CODES_TFHIRitemTypeEnum[af]+', path = '+path);
end;



constructor TQuestionnaireBuilder.create;
begin
  inherited;
  vsCache := TAdvStringMatch.create;
  FDependencies := TList<String>.create;
end;

function TQuestionnaireBuilder.addQuestion(group : TFHIRQuestionnaireItem; af : TFhirItemTypeEnum; path, id, name : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList; vs : TFHIRValueSet) : TFHIRQuestionnaireItem;
var
  ag : TFhirQuestionnaireResponseItem;
  aq : TFhirQuestionnaireResponseItem;
  children : TFHIRObjectList;
  child : TFHIRObject;
  vse : TFhirValueSet;
begin
  try
    result := group.itemList.Append;
    if vs <> nil then
    begin
      result.options := TFhirReference.Create;
      if (vs.expansion = nil) then
      begin
        result.options.reference := vs.url;
        result.options.addExtension(EXTENSION_FILTER_ONLY, TFhirBoolean.Create(true));
      end
      else
      begin
        if (vs.xmlId = '') then
        begin
          vse := vs.Clone;
          try
            vse.xmlId := nextId('vs');
            vsCache.Add(vse.url, vse.xmlId);
            vse.text := nil;
            vse.codeSystemElement := nil;
            vse.composeElement := nil;
            vse.contactList.Clear;
            vse.publisherElement := nil;
            vse.copyrightElement := nil;
            questionnaire.containedList.Add(vse.Link);
            result.options.reference := '#'+vse.xmlId;
          finally
            vse.Free;
          end;
        end
        else
          result.options.reference := '#'+vs.xmlId;
      end;
    end;

    result.LinkId := path+'.'+id;
    result.Text := name;
    result.Type_ := af;
    result.Required := required;
    result.Repeats := false;

    if (id.endsWith('/1')) then
      id := id.substring(0, id.length-2);

    if assigned(answerGroups) then
    begin
      for ag in answerGroups do
      begin
        children := TFHIRObjectList.Create;
        try
          aq := nil;

          if isPrimitive(ag.Tag) then
            children.add(ag.Tag.Link)
          else if ag.Tag is TFHIREnum then
            children.add(TFHIRString.create(TFHIREnum(ag.Tag).value))
          else
            TFHIRObject(ag.Tag).ListChildrenByName(id, children);

          for child in children do
            if child <> nil then
            begin
              if (aq = nil) then
              begin
                aq := ag.itemList.Append;
                aq.LinkId := result.linkId;
                aq.Text := result.text;
              end;
              aq.answerList.append.value := convertType(child, af, vs, result.linkId);
            end;
        finally
          children.Free;
        end;
      end;
    end;
  finally
    vs.Free;
  end;
end;

function UnCamelCase(s : String) : String;
var
  i, j : integer;
begin
  setLength(result, length(s) * 2);
  i := 1;
  j := 1;
  while (i <= length(s)) do
  begin
    if Upcase(s[i]) = s[i] then
    begin
      result[j] := ' ';
      inc(j);
    end;
    result[j] := s[i];
    inc(j);
    inc(i);
  end;
  setLength(result, j-1);
  result := Result.ToLower;
end;

procedure TQuestionnaireBuilder.addCodeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
  vs : TFHIRValueSet;
begin
  group.setExtensionString(TYPE_EXTENSION, 'code');
  vs := resolveValueSet(nil, element.Binding);
  if vs = nil then
    vs := makeAnyValueSet;
  addQuestion(group, itemTypeChoice, path, 'value', unCamelCase(Tail(element.path)), required, answerGroups, vs);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

// Primitives ------------------------------------------------------------------
procedure TQuestionnaireBuilder.addStringQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'string');
  addQuestion(group, itemTypeString, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addTimeQuestions(group: TFHIRQuestionnaireItem; element: TFhirElementDefinition; path: String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'time');
  addQuestion(group, itemTypeTime, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addUriQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'uri');
  addQuestion(group, itemTypeString, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;


procedure TQuestionnaireBuilder.addBooleanQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'boolean');
	addQuestion(group, itemTypeBoolean, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addDecimalQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'decimal');
  addQuestion(group, itemTypeDecimal, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addIntegerQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'integer');
  addQuestion(group, itemTypeInteger, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addDateTimeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'datetime');
  addQuestion(group, itemTypeDateTime, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addInstantQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'instant');
  addQuestion(group, itemTypeInstant, path, 'value', group.text, required, answerGroups);
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addBinaryQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'binary');
  // ? Lloyd: how to support binary content
end;

// Complex Types ---------------------------------------------------------------

function AnswerTypeForBinding(binding : TFhirElementDefinitionBinding) : TFhirItemTypeEnum;
begin
  if (binding = nil) then
    result := itemTypeOpenChoice
  else if (binding.Strength <> BindingStrengthRequired) then
    result := itemTypeOpenChoice
  else
    result := itemTypeChoice;
end;

procedure TQuestionnaireBuilder.addCodingQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
var
  ag : TFhirQuestionnaireResponseItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'Coding');
  addQuestion(group, AnswerTypeForBinding(element.Binding), path, 'value', group.text, required, answerGroups, resolveValueSet(nil, element.Binding));
  group.text := '';
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addCodeableConceptQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'CodeableConcept');
  addQuestion(group, AnswerTypeForBinding(element.Binding), path, 'coding', 'code:', false, answerGroups, resolveValueSet(nil, element.Binding));
  addQuestion(group, itemTypeOpenChoice, path, 'coding/1', 'other codes:', false, answerGroups, makeAnyValueSet).Repeats := true;
	addQuestion(group, itemTypeString, path, 'text', 'text:', required, answerGroups);
end;

procedure TQuestionnaireBuilder.addPeriodQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Period');
	addQuestion(group, itemTypeDateTime, path, 'start', 'start:', false, answerGroups);
	addQuestion(group, itemTypeDateTime, path, 'end', 'end:', false, answerGroups);
end;

procedure TQuestionnaireBuilder.addRatioQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Ratio');
	addQuestion(group, itemTypeDecimal, path, 'numerator', 'numerator:', false, answerGroups);
	addQuestion(group, itemTypeDecimal, path, 'denominator', 'denominator:', false, answerGroups);
	addQuestion(group, itemTypeString, path, 'units', 'units:', required, answerGroups);
end;

procedure TQuestionnaireBuilder.addHumanNameQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Name');
	addQuestion(group, itemTypeString, path, 'text', 'text:', false, answerGroups);
	addQuestion(group, itemTypeString, path, 'family', 'family:', required, answerGroups).Repeats := true;
	addQuestion(group, itemTypeString, path, 'given', 'given:', false, answerGroups).Repeats := true;
end;

procedure TQuestionnaireBuilder.addAddressQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Address');
  addQuestion(group, itemTypeString, path, 'text', 'text:', false, answerGroups);
  addQuestion(group, itemTypeString, path, 'line', 'line:', false, answerGroups).Repeats := true;
  addQuestion(group, itemTypeString, path, 'city', 'city:', false, answerGroups);
  addQuestion(group, itemTypeString, path, 'state', 'state:', false, answerGroups);
  addQuestion(group, itemTypeString, path, 'zip', 'zip:', false, answerGroups);
  addQuestion(group, itemTypeString, path, 'country', 'country:', false, answerGroups);
	addQuestion(group, itemTypeChoice, path, 'use', 'use:', false, answerGroups, resolveValueSet('http://hl7.org/fhir/vs/address-use'));
end;

procedure TQuestionnaireBuilder.addContactQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Contact');
	addQuestion(group, itemTypeChoice, path, 'system', 'type:', false, answerGroups, resolveValueSet('http://hl7.org/fhir/vs/contact-system'));
	addQuestion(group, itemTypeString, path, 'value', 'value:', required, answerGroups);
	addQuestion(group, itemTypeChoice, path, 'use', 'use:', false, answerGroups, resolveValueSet('http://hl7.org/fhir/vs/contact-use'));
end;

procedure TQuestionnaireBuilder.addIdentifierQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Identifier');
	addQuestion(group, itemTypeString, path, 'label', 'label:', false, answerGroups);
	addQuestion(group, itemTypeString, path, 'system', 'system:', false, answerGroups);
	addQuestion(group, itemTypeString, path, 'value', 'value:', required, answerGroups);
end;

procedure TQuestionnaireBuilder.addQuantityQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Quantity');
 	addQuestion(group, itemTypeChoice, path, 'comparator', 'comp:', false, answerGroups, resolveValueSet('http://hl7.org/fhir/vs/quantity-comparator'));
 	addQuestion(group, itemTypeDecimal, path, 'value', 'value:', required, answerGroups);
  addQuestion(group, itemTypeString, path, 'units', 'units:', required, answerGroups);
  addQuestion(group, itemTypeString, path, 'code', 'coded units:', false, answerGroups);
  addQuestion(group, itemTypeString, path, 'system', 'units system:', false, answerGroups);
end;

procedure TQuestionnaireBuilder.addAgeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Age');
 	addQuestion(group, itemTypeChoice, path, 'comparator', 'comp:', false, answerGroups, resolveValueSet('http://hl7.org/fhir/vs/quantity-comparator'));
 	addQuestion(group, itemTypeDecimal, path, 'value', 'value:', required, answerGroups);
  addQuestion(group, itemTypeChoice, path, 'units', 'units:', required, answerGroups, resolveValueSet('http://hl7.org/fhir/vs/duration-units'));
end;

procedure TQuestionnaireBuilder.addDurationQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Duration');
	addQuestion(group, itemTypeDecimal, path, 'value', 'value:', false, answerGroups);
	addQuestion(group, itemTypeString, path, 'units', 'units:', false, answerGroups);
end;

procedure TQuestionnaireBuilder.addAttachmentQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Attachment');
//  raise Exception.Create('addAttachmentQuestions not Done Yet');
end;

procedure TQuestionnaireBuilder.addRangeQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Range');
	addQuestion(group, itemTypeDecimal, path, 'low', 'low:', false, answerGroups);
	addQuestion(group, itemTypeDecimal, path, 'high', 'high:', false, answerGroups);
	addQuestion(group, itemTypeString, path, 'units', 'units:', required, answerGroups);
end;

procedure TQuestionnaireBuilder.addSampledDataQuestions(group: TFHIRQuestionnaireItem; element: TFhirElementDefinition; path: String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'SampledData');
end;

procedure TQuestionnaireBuilder.addScheduleQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
  group.setExtensionString(TYPE_EXTENSION, 'Schedule');
//  raise Exception.Create('addScheduleQuestions not Done Yet');
end;

// Special Types ---------------------------------------------------------------

procedure TQuestionnaireBuilder.addReferenceQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; profileURL : String; answerGroups : TFhirQuestionnaireResponseItemList);
var
  rn : String;
  ag : TFhirQuestionnaireResponseItem;
  q : TFHIRQuestionnaireItem;
begin
  group.setExtensionString(TYPE_EXTENSION, 'Reference');

  q := addQuestion(group, itemTypeReference, path, 'value', group.text, required, answerGroups);
  group.text := '';
  if profileURL.startsWith('http://hl7.org/fhir/Profile/') then
    rn := profileURL.Substring(28)
  else
    rn := 'Any';
  if (rn = 'Any') then
    q.setExtensionString(TYPE_REFERENCE, '/_search?subject=$subj&patient=$subj&encounter=$encounter')
  else
    q.setExtensionString(TYPE_REFERENCE, '/'+rn+'?subject=$subj&patient=$subj&encounter=$encounter');
  for ag in answerGroups do
    ag.text := '';
end;

procedure TQuestionnaireBuilder.addIdRefQuestions(group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; answerGroups : TFhirQuestionnaireResponseItemList);
begin
//  raise Exception.Create('not Done Yet');
end;

procedure TQuestionnaireBuilder.addExtensionQuestions(profile : TFHirStructureDefinition; group : TFHIRQuestionnaireItem; element : TFhirElementDefinition; path : String; required : boolean; profileURL : String; answerGroups : TFhirQuestionnaireResponseItemList);
var
  extension : TFhirStructureDefinition;
begin
  // is this a  profiled extension, then we add it
  if (profileURL <> '') and profiles.getExtensionDefn(profile, profileURL, profile, extension) then
  begin
    if answerGroups.count > 0 then
      raise Exception.Create('Debug this');
    if extension.snapshot.elementList.Count = 1 then
      buildQuestion(group, profile, extension.snapshot.elementList[0], path+'.extension['+profileURL+']', answerGroups)
    else
      raise Exception.Create('Not done yet');
  end;
end;

end.


