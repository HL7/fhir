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
  SysUtils, Classes, System.Character, RegExpr, ActiveX, ComObj,

  StringSupport, MathSupport, TextUtilities,
  AdvObjects, AdvGenerics, AdvObjectLists, Advbuffers, AdvMemories, AdvVCLStreams,

  MsXml, XmlBuilder, MsXmlParser, AdvXmlEntities, AdvJSON,

  FHIRBase, FHIRResources, FHIRTypes, FHIRParser, FHIRProfileUtilities, FHIRPath
  , AdvNameBuffers;

Type
  TWrapperElement = class (TAdvObject)
  private
    FMap : TAdvMap<TWrapperElement>;
    FOwnsMap : boolean;
    FProfile : TFHIRStructureDefinition;
    FDefinition : TFHIRElementDefinition;
  public
    Constructor Create(map : TAdvMap<TWrapperElement>); Virtual;
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
    function locStart: TSourceLocation; virtual; abstract;
    function locEnd: TSourceLocation; virtual; abstract;

    property Definition : TFhirElementDefinition read FDefinition write FDefinition; // no ownership
    property Profile : TFhirStructureDefinition read FProfile write FProfile;
  end;

  TFHIRBaseOnWrapper = class (TFHIRBase)
  private
    FServices : TValidatorServiceProvider;
    FWrapper : TWrapperElement;
    FElementList : TFHIRElementDefinitionList;
    FProfile: TFHIRStructureDefinition;
    FDefinition : TFHIRElementDefinition;
    FTypeName, FTypeProfile : String;
    childDefinitions: TFHIRElementDefinitionList;
    function getDefinition(name : String; var tn, tp : String) : TFhirElementDefinition;
//    function IsAbstractType(pn: String): Boolean;
    procedure log(msg : String);
  public
    Constructor Create(services : TValidatorServiceProvider; wrapper : TWrapperElement; profile: TFHIRStructureDefinition; definition : TFhirElementDefinition; TypeName, TypeProfile : String);
    Destructor Destroy; override;
    Procedure GetChildrenByName(child_name : string; list : TFHIRObjectList); override;
    function FhirType : string; override;
    function isPrimitive : boolean; override;
    function primitiveValue : string; override;
    function isMetaDataBased : boolean; override;
    function equalsDeep(other : TFHIRBase) : boolean; override;
  end;

  TFHIRResourceOnWrapper = class (TFHIRResource)
  private
    FServices : TValidatorServiceProvider;
    FWrapper : TWrapperElement;
    FElementList : TFHIRElementDefinitionList;
    FProfile: TFHIRStructureDefinition;
    FDefinition : TFHIRElementDefinition;
    childDefinitions: TFHIRElementDefinitionList;
    function getDefinition(name : String; var tn, tp : String) : TFhirElementDefinition;
  public
    Constructor Create(services : TValidatorServiceProvider; wrapper : TWrapperElement; profile: TFHIRStructureDefinition);
    Destructor Destroy; override;

    Procedure GetChildrenByName(child_name : string; list : TFHIRObjectList); override;
    function FhirType : string; override;
  end;

  TNodeStack = class (TAdvObject)
  private
    xml : boolean;
    parent : TNodeStack;
    literalPath: String; // fhir path format
    logicalPaths : TStringList; // dotted format, various entry points
    element : TWrapperElement;
    definition : TFHIRElementDefinition;
    type_ : TFHIRElementDefinition;
//    extension : TFHIRElementDefinition;
    function push(element : TWrapperElement; count : integer;  definition : TFHIRElementDefinition; type_ : TFHIRElementDefinition) : TNodeStack;
		function addToLiteralPath(path : Array of String) : String;
  public
    Constructor Create(xml : boolean);
    Destructor Destroy; Override;
  end;

	TElementInfo = class (TAdvObject)
  private
    name : String;
		element : TWrapperElement;
		path : String;
		definition : TFHIRElementDefinition;
    count : integer;
    function locStart: TSourceLocation;
    function locEnd: TSourceLocation;
  public
    Constructor Create(name: String; element: TWrapperElement; path: String; count: integer);
  end;

  TChildIterator = class (TAdvObject)
  private
    parent : TWrapperElement;
    basePath : String;
    lastName: String;
    lastCount : integer;
    child : TWrapperElement;

  public
    constructor Create(path : String; element : TWrapperElement);
    destructor Destroy; override;
    function next : boolean;
    function name : String;
    function element : TWrapperElement;
    function path : String;
    function count : integer;
  end;

  TBestPracticeWarningLevel = (bpwlIgnore, bpwlHint, bpwlWarning, bpwlError);
  TCheckDisplayOption = (cdoIgnore, cdopCheck, cdoCheckCaseAndSpace, cdoCheckCase, cdoCheckSpace);
  TResourceIdStatus = (risOptional, risRequired, risProhibited);

  TFHIRValidator = class(TAdvObject)
  private
    // configuration items
    FContext : TValidatorServiceProvider;
    FCheckDisplay : TCheckDisplayOption;
    FBPWarnings : TBestPracticeWarningLevel;
    FSuppressLoincSnomedMessages : boolean;
    FResourceIdRule: TResourceIdStatus;
    FIsAnyExtensionsAllowed : boolean;
    FExtensionDomains: TStringList;
    FPathEngine : TFHIRExpressionEngine;
    FCache : IXMLDOMSchemaCollection;
    Fowned : TAdvObjectList;

    procedure loadSchema;
    function LoadDoc(name: String; isFree: boolean = false): IXMLDomDocument2;
    function validateXml(source : TAdvBuffer; outcome : TFHIROperationOutcome) : boolean;

    function rule(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; path: String; thePass: boolean; msg: String): boolean; overload;
    function rule(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; path: String; thePass: boolean; msg: String): boolean; overload;
    function warning(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; path: String; thePass: boolean; msg: String): boolean;
    function hint(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; path: String; thePass: boolean; msg: String): boolean;
    procedure bpCheck(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; literalPath: String; test: boolean; message: String);

    // --- first phase : work with the json or xml directly ---------------------------------------------------------------------------------------------------------

//    function isKnownType(code : String) : boolean;
    function genFullUrl(bundleBase, entryBase, ty, id : String) : String;
    function empty(element : TWrapperElement) : boolean;
    Function resolveInBundle(entries : TAdvList<TWrapperElement>; ref, fullUrl, type_, id : String) : TWrapperElement;
    function getProfileForType(type_ : String) : TFHIRStructureDefinition;
    function sliceMatches(element : TWrapperElement; path : String; slice, ed: TFHIRElementDefinition; profile : TFHIRStructureDefinition) : boolean;
    function resolveType(t : String) : TFHIRElementDefinition;
	  function getCriteriaForDiscriminator(path : String; ed : TFHIRElementDefinition; discriminator : String; profile : TFHIRStructureDefinition) : TFHIRElementDefinition;
  	function getValueForDiscriminator(element : TWrapperElement; discriminator : String; criteria : TFHIRElementDefinition) : TFhirElement;
    function valueMatchesCriteria(value: TFhirElement; criteria: TFHIRElementDefinition): boolean;
    function resolve(ref : String ; stack : TNodeStack) : TWrapperElement;
    function tryParse(ref : String) : String;
    function checkResourceType(ty : String) : String;
    function getBaseType(profile : TFHIRStructureDefinition; pr : String) : String;
    function getFromBundle(bundle : TWrapperElement; ref : String) : TWrapperElement;
    function getContainedById(container : TWrapperElement; id : String) : TWrapperElement;
    function resolveProfile(profile : TFHIRStructureDefinition; pr : String): TFHIRStructureDefinition;
    function checkExtensionContext(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; definition: TFHIRStructureDefinition; stack: TNodeStack;
      extensionParent: String): boolean;
//    function getElementByPath(definition : TFHIRStructureDefinition; path : String) : TFHIRElementDefinition;
    function findElement(profile : TFHIRStructureDefinition; name : String) : TFHIRElementDefinition;
//    function getDefinitionByTailNameChoice(children : TFHIRElementDefinitionList; name : String) : TFHIRElementDefinition;
    function resolveBindingReference(context : TFHIRDomainResource; reference: TFHIRType): TFHIRValueSet;
    function getExtensionByUrl(extensions : TAdvList<TWrapperElement>; url : String) : TWrapperElement;

    procedure checkQuantityValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRQuantity);
    procedure checkAddressValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRAddress);
    procedure checkContactPointValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRContactPoint);
    procedure checkAttachmentValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRAttachment);
    procedure checkIdentifierValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRIdentifier);
    procedure checkCodingValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRCoding);
    procedure checkHumanNameValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRHumanName);
    procedure checkCodeableConceptValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRCodeableConcept);
    procedure checkTimingValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRTiming);
    procedure checkPeriodValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRPeriod);
    procedure checkRangeValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRRange);
    procedure checkRatioValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRRatio);
    procedure checkSampledDataValue(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement; fixed : TFHIRSampledData);

    procedure checkFixedValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFhirElement; propName: String);

    function checkCode(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; path: String; code, System, display: String): boolean;
    procedure checkQuantity(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; context: TFHIRElementDefinition);
    procedure checkPrimitiveBinding(errors: TFhirOperationOutcomeIssueList; path: String; ty: String; context: TFHIRElementDefinition; element: TWrapperElement; profile : TFhirStructureDefinition);
    procedure checkPrimitive(errors: TFhirOperationOutcomeIssueList; path, ty: String; context: TFHIRElementDefinition; e: TWrapperElement; profile : TFhirStructureDefinition);
    procedure checkIdentifier(errors : TFhirOperationOutcomeIssueList; path : String; element : TWrapperElement; context : TFHIRElementDefinition);
    procedure checkCoding(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; profile: TFHIRStructureDefinition; context: TFHIRElementDefinition; inCodeableConcept: boolean);
    procedure checkCodeableConcept(errors : TFhirOperationOutcomeIssueList; path : String; element : TWrapperElement; profile : TFHIRStructureDefinition; context : TFHIRElementDefinition);
    procedure checkReference(errors : TFhirOperationOutcomeIssueList; path : String; element : TWrapperElement; profile : TFHIRStructureDefinition; container : TFHIRElementDefinition; parentType : String; stack : TNodeStack);
    function checkExtension(errors : TFhirOperationOutcomeIssueList; path : String; element : TWrapperElement; def : TFHIRElementDefinition; profile : TFHIRStructureDefinition; stack : TNodeStack) : TFHIRStructureDefinition;
    procedure validateContains(errors: TFhirOperationOutcomeIssueList; path: String; child: TFHIRElementDefinition; context: TFHIRElementDefinition; resource, element: TWrapperElement; stack: TNodeStack; idRule: TResourceIdStatus);
    function allowUnknownExtension(url : String) : boolean;

    function idStatusForEntry(ep : TWrapperElement; ei : TElementInfo): TResourceIdStatus;
    procedure checkInvariants(errors: TFhirOperationOutcomeIssueList; path : String; profile: TFHIRStructureDefinition; ed: TFhirElementDefinition; typename, typeProfile : String; resource, element: TWrapperElement);
    procedure validateSections(errors : TFhirOperationOutcomeIssueList; entries : TAdvList<TWrapperElement>; focus : TWrapperElement; stack : TNodeStack; fullUrl, id : String);
    procedure validateBundleReference(errors : TFhirOperationOutcomeIssueList; entries : TAdvList<TWrapperElement>; ref : TWrapperElement; name : String; stack : TNodeStack; fullUrl, type_, id : String);
    procedure validateDocument(errors : TFhirOperationOutcomeIssueList; entries : TAdvList<TWrapperElement>; composition : TWrapperElement; stack : TNodeStack; fullUrl, id : String);
    procedure validateElement(errors: TFhirOperationOutcomeIssueList; profile: TFHIRStructureDefinition; definition: TFHIRElementDefinition; cprofile: TFHIRStructureDefinition; context: TFHIRElementDefinition; resource, element: TWrapperElement; actualType: String; stack: TNodeStack; inCodeableConcept: boolean);
    procedure validateMessage(errors : TFhirOperationOutcomeIssueList; bundle : TWrapperElement);
    procedure validateBundle(errors : TFhirOperationOutcomeIssueList; bundle : TWrapperElement; stack : TNodeStack);
    procedure validateObservation(errors : TFhirOperationOutcomeIssueList; element : TWrapperElement; stack : TNodeStack);

    function findQuestionnaireItem(qsrc : TFhirQuestionnaire; linkId : String; var qItem : TFhirQuestionnaireItem) : boolean;
    procedure validateAnswerCode(errors: TFhirOperationOutcomeIssueList; value: TWrapperElement; stack: TNodeStack; optionList : TFhirCodingList); overload;
    procedure validateAnswerCode(errors: TFhirOperationOutcomeIssueList; value: TWrapperElement; stack: TNodeStack; qSrc : TFhirQuestionnaire; vsRef : TFhirReference); overload;
    procedure validateAnswerCode(errors: TFhirOperationOutcomeIssueList; answer: TWrapperElement; stack: TNodeStack; qSrc : TFhirQuestionnaire; qitem : TFhirQuestionnaireItem); overload;
    procedure validateQuestionnaireResponseItemQuantity(errors: TFhirOperationOutcomeIssueList; answer: TWrapperElement; stack: TNodeStack);
    function validateQuestionnaireResponseItemType(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack; types: array of String) : string;
    procedure validateQuestionannaireResponseItem(qsrc : TFhirQuestionnaire; qItem : TFhirQuestionnaireItem; errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack; inProgress : boolean); overload;
    procedure validateQuestionannaireResponseItem(qsrc : TFhirQuestionnaire; qItem : TFhirQuestionnaireItem; errors: TFhirOperationOutcomeIssueList; elements: TAdvList<TWrapperElement>; stack: TNodeStack; inProgress : boolean); overload;
    procedure validateQuestionannaireResponseItems(qsrc : TFhirQuestionnaire; qItems : TFhirQuestionnaireItemList; errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack; inProgress : boolean);
    procedure validateQuestionannaireResponse(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack);

    procedure checkDeclaredProfiles(errors: TFhirOperationOutcomeIssueList; resource, element: TWrapperElement; stack: TNodeStack);
    procedure start(errors: TFhirOperationOutcomeIssueList; resource, element: TWrapperElement; profile: TFHIRStructureDefinition; stack: TNodeStack);
    procedure validateResource(errors: TFhirOperationOutcomeIssueList; resource, element: TWrapperElement; profile: TFHIRStructureDefinition; idRule: TResourceIdStatus; stack: TNodeStack);
  public
    Constructor Create(context: TValidatorServiceProvider);
    Destructor Destroy; Override;
    Property CheckDisplay : TCheckDisplayOption read FCheckDisplay write FCheckDisplay;
    Property BPWarnings : TBestPracticeWarningLevel read FBPWarnings write FBPWarnings;
    Property SuppressLoincSnomedMessages : boolean read FSuppressLoincSnomedMessages write FSuppressLoincSnomedMessages;
    Property ResourceIdRule : TResourceIdStatus read FResourceIdRule write FResourceIdRule;
    Property IsAnyExtensionsAllowed : boolean read FIsAnyExtensionsAllowed write FIsAnyExtensionsAllowed;
    Property Context : TValidatorServiceProvider read FContext;

    procedure validate(errors : TFhirOperationOutcomeIssueList; obj : TJsonObject); overload;
    procedure validate(errors : TFhirOperationOutcomeIssueList; obj : TJsonObject; profile : TFHIRStructureDefinition); overload;
    procedure validate(errors : TFhirOperationOutcomeIssueList; obj : TJsonObject; profile : String); overload;

    procedure validate(errors: TFhirOperationOutcomeIssueList; element: IXMLDOMElement); overload;
    procedure validate(errors: TFhirOperationOutcomeIssueList; element: IXMLDOMElement; profile: String); overload;
    procedure validate(errors: TFhirOperationOutcomeIssueList; element: IXMLDOMElement; profile: TFHIRStructureDefinition); overload;
    procedure validate(errors: TFhirOperationOutcomeIssueList; document: IXMLDOMDocument); overload;
    procedure validate(errors: TFhirOperationOutcomeIssueList; document: IXMLDOMDocument; profile: String); overload;
    procedure validate(errors: TFhirOperationOutcomeIssueList; document: IXMLDOMDocument; profile: TFHIRStructureDefinition); overload;

    Function validateInstance(source : TAdvBuffer; format : TFHIRFormat; idRule : TResourceIdStatus; opDesc : String; profile : TFHirStructureDefinition) : TFHIROperationOutcome; overload;
    Function validateInstance(resource : TFhirResource; idRule : TResourceIdStatus; opDesc : String; profile : TFHirStructureDefinition) : TFHIROperationOutcome; overload;
  end;

(*  TFHIRBaseValidator = class (TValidatorServiceProvider)
  private

    procedure Load(feed: TFHIRBundle);
    function LoadDoc(name : String; isFree : boolean = false) : IXMLDomDocument2;
    procedure validateInstance(op : TFHIROperationOutcome; elem : IXMLDOMElement; specifiedprofile : TFHirStructureDefinition); overload;
    function validateXml(source: TAdvBuffer; outcome: TFHIROperationOutcome): boolean;

  protected
    procedure SeeResource(r : TFhirResource); virtual;
  public
    Constructor Create; Override;
    Destructor Destroy; Override;

    Function Link : TFHIRBaseValidator; overload;

    Property Validator : TFHIRValidator read FValidator;



    function fetchResource(t : TFhirResourceType; url : String) : TFhirResource; override;
  end;
*)


implementation

uses
  FHIRParserBase, FHIRUtilities;

function nameMatches(name, tail : String) : boolean;
begin
  if (tail.endsWith('[x]')) then
    result := name.startsWith(tail.substring(0,  tail.length-3))
  else
    result := (name = tail);
end;


{ TElementInfo }

Constructor TElementInfo.Create(name: String; element: TWrapperElement; path: String; count: integer);
begin
  inherited Create;
  self.name := name;
  self.element := element;
  self.path := path;
  self.count := count;
end;

function TElementInfo.locStart: TSourceLocation;
begin
  result := element.locStart;
end;

function TElementInfo.locEnd: TSourceLocation;
begin
  result := element.locEnd;
end;

function codeInExpansion(cnt: TFhirValueSetExpansionContains; System, code: String): boolean; overload;
var
  c : TFhirValueSetExpansionContains;
begin
  for c in cnt.containsList do
  begin
    if (code = c.code) and ((System = '') or (System = c.System)) then
    begin
      result := true;
      exit;
    end;
    if (codeInExpansion(c, System, code)) then
    begin
      result := true;
      exit;
    end;
  end;
  result := false;
end;

function codeInExpansion(vs: TFHIRValueSet; System, code: String): boolean; overload;
var
  c : TFhirValueSetExpansionContains;
begin
  for c in vs.Expansion.containsList do
  begin
    if (code = c.code) and ((System = '') or (System = c.System)) then
    begin
      result := true;
      exit;
    end;
    if (codeInExpansion(c, System, code)) then
    begin
      result := true;
      exit;
    end;
  end;
  result := false;
end;

{ TWrapperElement }

Constructor TWrapperElement.Create;
begin
  inherited Create;
  FMap := map;
  if map = nil then
  begin
    FMap := TAdvMap<TWrapperElement>.create;
    FOwnsMap := true;
  end;
end;

Destructor TWrapperElement.Destroy;
begin
  if FOwnsMap then
    FMap.Free;
  inherited Destroy;
end;

function TWrapperElement.Link: TWrapperElement;
begin
  result := TWrapperElement(inherited Link);
end;

type
  TDOMWrapperElement = class (TWrapperElement)
  private
    FLocations : TAdvList<TSourceLocationObject>;  // special - not linked!
    FElement: IXMLDOMElement;
    function wrap(element: IXMLDOMElement) : TDOMWrapperElement;
  public
    Constructor Create(map : TAdvMap<TWrapperElement>; element: IXMLDOMElement);
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
    function locStart: TSourceLocation; override;
    function locEnd: TSourceLocation; override;
  end;

{ TDOMWrapperElement }

constructor TDOMWrapperElement.Create(map : TAdvMap<TWrapperElement>; element: IXMLDOMElement);
begin
  inherited Create(Map);
  FElement := element;
end;

function TDOMWrapperElement.wrap(element: IXMLDOMElement): TDOMWrapperElement;
var
  s : String;
begin
  s := inttostr(integer(pointer(element)));
  if FMap.ContainsKey(s) then
    result := TDOMWrapperElement(FMap[s])
  else
  begin
    result := TDOMWrapperElement.Create(FMap, element);
    result.FLocations := FLocations;
    FMap.Add(s, result); // takes ownership
  end;
end;

function TDOMWrapperElement.getAttribute(name: String): String;
begin
  if not hasAttribute(name) then
    result := ''
  else
  result := FElement.getAttribute(name);
end;

function TDOMWrapperElement.getFirstChild: TWrapperElement;
var
  res: IXMLDOMElement;
begin
  res := TMsXmlParser.FirstChild(FElement);
  if res = nil then
    result := nil
  else
    result := wrap(res);
end;

function TDOMWrapperElement.getName: String;
begin
  result := FElement.tagName;
end;

function TDOMWrapperElement.getNamedChild(name: String): TWrapperElement;
var
  res: IXMLDOMElement;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) and (res.tagName <> name) and (res.tagName <> name) do
    res := TMsXmlParser.NextSibling(res);
  if res = nil then
    result := nil
  else
    result := wrap(res);
end;

procedure TDOMWrapperElement.getNamedChildren(name: String; list: TAdvList<TWrapperElement>);
var
  res: IXMLDOMElement;
  n : String;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) do
  begin
    n := res.tagName;
    if (n = name) then
      list.Add(wrap(res).link);
    res := TMsXmlParser.NextSibling(res);
  end;
end;

procedure TDOMWrapperElement.getNamedChildrenWithWildcard(name: String; list: TAdvList<TWrapperElement>);
var
  res: IXMLDOMElement;
  n : String;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) do
  begin
    n := res.tagname; // OR res.tagName
    if (n = name) or ((name.endsWith('[x]') and (n.startsWith(name.substring(0, name.length - 3))))) then
      list.Add(wrap(res).Link);
    res := TMsXmlParser.NextSibling(res);
  end;
end;

function TDOMWrapperElement.getNamedChildValue(name: String): String;
var
  res: IXMLDOMElement;
begin
  res := TMsXmlParser.FirstChild(FElement);
  while (res <> nil) and (res.tagName <> name) and (res.tagName <> name) do
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
  res: IXMLDOMElement;
begin
  res := TMsXmlParser.NextSibling(FElement);
  if res = nil then
    result := nil
  else
    result := wrap(res);
end;

function TDOMWrapperElement.getResourceType: String;
begin
  result := FElement.tagname;
end;

function TDOMWrapperElement.getText: String;
begin
  result := FElement.text;
end;

function TDOMWrapperElement.hasAttribute(name: String): boolean;
begin
  result := FElement.getAttributeNode(name) <> nil;
end;

function TDOMWrapperElement.hasNamespace(s: String): boolean;
var
  i : integer;
  n, t: String;
begin
  result := false;
  for i := 0 to FElement.attributes.length - 1 do
  begin
    n := FElement.attributes.item[i].nodeName;
  	if ((n = 'xmlns') or n.startsWith('xmlns:')) and (t = s) then
    	result := true;
  end;
end;

function TDOMWrapperElement.hasProcessingInstruction: boolean;
//var
//  node : IXMLDOMNode;
begin
  result := false;
  // node := TMsXmlParser.FirstChild(FElement);
//  while (node <> nil) do
//  begin
//   	if (node.NodeType = NODE_PROCESSING_INSTRUCTION) then
//      result := true;
//  	node := node.NextSibling;
//  end;
end;

function TDOMWrapperElement.isXml: boolean;
begin
  result := true;
end;

function TDOMWrapperElement.locStart: TSourceLocation;
var
  i : integer;
begin
  i := StrToIntDef(Felement.getAttribute(MAP_ATTR_NAME), -1);
  if i = -1 then
begin
  result.line := -1;
  result.col := -1;
  end
  else
    result := FLocations[i].locationStart;
end;

function TDOMWrapperElement.locEnd: TSourceLocation;
var
  i : integer;
begin
  i := StrToIntDef(Felement.getAttribute(MAP_ATTR_NAME), -1);
  if i = -1 then
begin
  result.line := -1;
  result.col := -1;
  end
  else
    result := FLocations[i].locationEnd;
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
    index: integer;
		children : TAdvList<TJsonWrapperElement>;
		procedure processChild(name : String; e : TJsonNode);
    procedure createChildren;
  public
    Constructor Create(element : TJsonObject); overload;
    Constructor Create(map : TAdvMap<TWrapperElement>; path, name: String; element, _element: TJsonNode; parent: TJsonWrapperElement; index: integer); overload;
    Destructor Destroy; override;
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
    function locStart: TSourceLocation; override;
    function locEnd: TSourceLocation; override;
  end;

{ TJsonWrapperElement }

Constructor TJsonWrapperElement.Create(map : TAdvMap<TWrapperElement>; path, name: String; element, _element: TJsonNode; parent: TJsonWrapperElement; index: integer);
begin
  inherited Create(map);
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
  inherited Create(nil);
  self.name := '';
  self.resourceType := element.str['resourceType'];
  self.element := element;
  self.path := '';
  createChildren();
end;

procedure TJsonWrapperElement.createChildren;
var
  obj : TJsonObject;
  n : string;
begin
  children := TAdvList<TJsonWrapperElement>.create;
	// we''re going to make this look like the XML
  if (element <> nil) then
  begin

    if (element is TJsonString) or (element is TJsonNumber) or (element is TJsonBoolean) then
  begin
    // we may have an element_ too
    if (_element <> nil) and (_element is TJsonObject) then
    begin
      obj := TJsonObject(_element);
        for n in obj.properties.keys do
          processChild(n, obj.properties[n]);
    end;
  end
  else if (element is TJsonObject) then
  begin
    obj := TJsonObject(element);
        for n in obj.properties.keys do
          if n <> 'resourceType' then
            processChild(n, obj.properties[n]);
  end
  else if (element is TJsonNull) then
  begin
    // nothing to do
  end
  else
      raise Exception.Create('unexpected condition: ' + element.ClassName);
  end;
end;

destructor TJsonWrapperElement.Destroy;
begin
  element.Free;
  _element.Free;
  children.Free;
  inherited;
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
    if TJsonObject(element).has('_' + name) then
      _e := TJsonObject(element).properties['_' + name];

  if (((e is TJsonString) or (e is TJsonNumber) or (e is TJsonBoolean)) or ((e = nil) and (_e <> nil) and not(_e is TJsonArray))) then
  begin
    children.Add(TJsonWrapperElement.Create(FMap, path, name, e.link, _e.link, self, children.count));
  end
  else if (e is TJsonArray) or ((e = nil) and (_e <> nil)) then
  begin
    arr := TJsonArray(e);
		_arr := TJsonArray(_e);
    if arr <> nil then
      max := arr.count
    else
      max := 0;
    if (_arr <> nil) and (_arr.count > max) then
      max := _arr.count;
      for i := 0 to max - 1 do
      begin
        a := nil;
        _a := nil;
      if not((arr = nil) or (arr.count < i)) then
        a := arr.Item[i];
      if not((_arr = nil) or (_arr.count < i)) then
        a := _arr.Item[i];
      children.Add(TJsonWrapperElement.Create(FMap, path, name, a.link, _a.link, self, children.count));
      end
  end
  else if (e is TJsonObject) then
  begin
    children.Add(TJsonWrapperElement.Create(FMap, path, name, e.link, nil, self, children.count));
  end
  else
    raise Exception.Create('not done yet (1): ' + e.ClassName);
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
  if (children.count = 0) then
    result := nil
  else
    result := children[0];
end;

function TJsonWrapperElement.getNextSibling() : TWrapperElement;
begin
  if (parent = nil) then
    result := nil
  else if (index >= parent.children.count - 1) then
    result := nil
  else
    result := parent.children[index+1];
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
    else if (element is TJsonString) then
      result := TJsonString(element).value
    else if (element is TJsonNumber) then
      result := TJsonNumber(element).value
    else if (element is TJsonBoolean) then
      if TJsonBoolean(element).value then
        result := 'true'
      else
        result := 'false'
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
    raise Exception.Create('not done yet (2): ' + name);
end;

procedure TJsonWrapperElement.getNamedChildrenWithWildcard(name : String; list : TAdvList<TWrapperElement>);
var
  j: TJsonWrapperElement;
  n : String;
begin
  for j in children do
  begin
    n := j.name;
    if (n = name) or ((name.endsWith('[x]') and (n.startsWith(name.substring(0, name.length - 3))))) then
      list.Add(j.Link);
  end;
end;

function TJsonWrapperElement.hasAttribute(name : String) : boolean;
begin
  if (name = 'value') then
  begin
    if (element = nil) then
      result := false
    else if (element is TJsonString) or (element is TJsonNumber) then
      result := true
    else
      result := false;
  end
  else if (name = 'id') then
  begin
    result := getNamedChild('id') <> nil;
  end
  else
    raise Exception.Create('not done yet (3): ' + name);
end;

function TJsonWrapperElement.getNamespace() : String;
begin
  raise Exception.Create('not done yet');
end;

function TJsonWrapperElement.isXml() : boolean;
begin
  result := false;
end;

function TJsonWrapperElement.getText() : String;
begin
  raise Exception.Create('not done yet');
end;

function TJsonWrapperElement.hasNamespace(s : String) : boolean;
begin
  raise Exception.Create('not done');
end;

function TJsonWrapperElement.hasProcessingInstruction() : boolean;
begin
  result := false;
end;

function TJsonWrapperElement.getResourceType(): String;
begin
  result := resourceType;
end;

function TJsonWrapperElement.locStart: TSourceLocation;
begin
  if _element = nil then
    result := element.LocationStart
  else if element = nil then
    result := _element.LocationStart
  else
    result := minLoc(element.LocationStart, _element.LocationStart);
end;

function TJsonWrapperElement.locEnd: TSourceLocation;
begin
  if _element = nil then
    result := element.LocationEnd
  else if element = nil then
    result := _element.LocationEnd
  else
    result := maxLoc(element.LocationEnd, _element.LocationEnd);
end;


{ TNodeStack }

constructor TNodeStack.Create(xml: boolean);
begin
  inherited Create;
  self.xml := xml;
  logicalPaths := TStringList.Create;
end;


destructor TNodeStack.Destroy;
begin
  logicalPaths.Free;
  inherited;
end;

function tail(path : String) : String;
begin
  result := path.substring(path.lastIndexOf('.')+1);
end;

function TNodeStack.push(element : TWrapperElement; count : integer;  definition : TFHIRElementDefinition; type_ : TFHIRElementDefinition) : TNodeStack;
var
  t, lp : String;
  n : String;
begin
  result := TNodeStack.Create(element.isXml());
  try
  result.parent := self;
  result.element := element;
  result.definition := definition;
    n := element.getName();
    if n = '' then
      n := element.getResourceType;
    if LiteralPath = '' then
      result.literalPath := n
    else
      result.literalPath := literalPath + '.' + n;
    if (count > -1) then
      result.literalPath := result.literalPath + '.item(' + integer.toString(count-1) + ')';
  if (type_ <> nil) then
  begin
    // type will be bull if we on a stitching point of a contained resource, or if....
    result.type_ := type_;
    t := tail(definition.path);
    for lp in logicalPaths do 
    begin
      result.logicalPaths.Add(lp + '.' + t);
      if (t.endsWith('[x]')) then
        result.logicalPaths.Add(lp + '.' + t.substring(0, t.length - 3) + type_.path);
    end;
    result.logicalPaths.Add(type_.path);
  end
  else if (definition <> nil) then
  begin
    for lp in logicalPaths do
      result.logicalPaths.Add(lp + '.' + element.getName());
  end
  else
    result.logicalPaths.AddStrings(logicalPaths);
    result.Link;
  finally
    result.Free;
  end;
end;

function TNodeStack.addToLiteralPath(path : Array of String) : String;
var
  b : TStringBuilder;
  p : String;
begin    
  b := TStringBuilder.Create;
  try
    b.append(literalPath);
      for p in path do
      begin
        if (p.startsWith(':')) then
        begin
        b.append('.item(');
          b.append(p.substring(1));
        b.append(')');
        end 
        else
        begin
        b.append('.');
          b.append(p);
        end
    end;
    result := b.toString();
  finally
    b.Free;
  end;
end;

{ TFHIRValidator }

constructor TFHIRValidator.Create(context: TValidatorServiceProvider);
begin
  inherited Create;
  FContext := context;
  FPathEngine := TFHIRExpressionEngine.create(FContext.link);
  IsAnyExtensionsAllowed := true;
  FOwned := TAdvObjectList.create;
end;

destructor TFHIRValidator.Destroy;
begin
  FOwned.Free;
  FPathEngine.Free;
  FContext.Free;
  inherited;
end;

function TFHIRValidator.empty(element: TWrapperElement): boolean;
var
  child : TWrapperElement;
begin
  if (element.hasAttribute('value')) then
    result := false
  else if (element.hasAttribute('id')) then
    result := false
  else
  begin
    child := element.getFirstChild();
    while (child <> nil) do
    begin
      if (not child.isXml()) or (FHIR_NS = child.getNamespace()) then
      begin
        result := false;
        exit;
      end;
      child := child.getNextSibling();
    end;
    result := true;
  end;
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; element: IXMLDOMElement);
begin
  validate(errors, element, nil);
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; obj: TJsonObject);
begin
  validate(errors, obj, nil);
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; element: IXMLDOMElement; profile: String);
var
  p : TFHIRStructureDefinition;
begin
  p := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, profile));
  FOwned.add(p);
  if (p = nil) then
    raise Exception.Create('StructureDefinition "' + profile + '" not found');
  validate(errors, element, p);
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; element: IXMLDOMElement; profile: TFHIRStructureDefinition);
var
  w : TWrapperElement;
begin
  w := TDOMWrapperElement.create(nil, element);
  try
    validateResource(errors, w, nil, profile, ResourceIdRule, nil);
  finally
    w.Free;
end;
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; obj: TJsonObject; profile: TFHIRStructureDefinition);
var
  w : TWrapperElement;
begin
  w := TJsonWrapperElement.Create(obj.link);
  try
    validateResource(errors, w, nil, profile, ResourceIdRule, nil);
  finally
    w.Free;
  end;
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; obj: TJsonObject; profile: String);
var
  p : TFHIRStructureDefinition;
begin
  p := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, profile));
  FOwned.add(p);
  if (p = nil) then
    raise Exception.Create('StructureDefinition "' + profile + '" not found');
  validate(errors, obj, p);
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; document: IXMLDOMDocument);
begin
  validate(errors, document, nil);
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; document: IXMLDOMDocument; profile: String);
var
  p : TFHIRStructureDefinition;
begin
  p := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, profile));
  FOwned.add(p);
  if (p = nil) then
    raise Exception.Create('StructureDefinition "' + profile + '" not found');
  validate(errors, document, p);
end;

procedure TFHIRValidator.validate(errors: TFhirOperationOutcomeIssueList; document: IXMLDOMDocument; profile: TFHIRStructureDefinition);
begin
  validate(errors, document.documentElement, profile);
end;

function describeReference(reference: TFHIRType): String;
begin
  if (reference = nil) then
    result := 'nil'
  else if (reference is TFHIRUri) then
    result := TFHIRUri(reference).value
  else if (reference is TFHIRReference) then
    result := TFHIRReference(reference).reference
  else
    result := '??';
end;

function readAsCoding(item: TWrapperElement): TFHIRCoding;
var
  c: TFHIRCoding;
begin
  c := TFHIRCoding.Create;
  try
    c.System := item.getNamedChildValue('system');
    c.Version := item.getNamedChildValue('version');
    c.code := item.getNamedChildValue('code');
    c.display := item.getNamedChildValue('display');
    result := c.Link;
  finally
    c.Free;
  end;
end;

function TFHIRValidator.findQuestionnaireItem(qsrc: TFhirQuestionnaire; linkId: String; var qItem: TFhirQuestionnaireItem): boolean;
  procedure FindItem(list : TFhirQuestionnaireItemList);
  var
    item : TFhirQuestionnaireItem;
  begin
    for item in list do
    begin
      if item.linkId = linkId then
      begin
        result := true;
        qItem := item;
      end
      else
        FindItem(item.itemList);
      if result then
        break;
    end;
  end;
begin
  findItem(qsrc.itemList);
end;

procedure TFHIRValidator.validateAnswerCode(errors: TFhirOperationOutcomeIssueList; value: TWrapperElement; stack: TNodeStack; optionList: TFhirCodingList);
var
  system, code : String;
  found : boolean;
  c : TFhirCoding;
begin
  system := value.getNamedChildValue('system');
  code := value.getNamedChildValue('code');
  found := false;
  for c in optionList do
    if (c.system = system) and (c.code = code) then
    begin
      found := true;
      break;
    end;
  rule(errors, IssueTypeStructure, value.locStart, value.locEnd, stack.literalPath, found, 'The code '+system+'::'+code+' is not a valid option');
end;

procedure TFHIRValidator.validateAnswerCode(errors: TFhirOperationOutcomeIssueList; value: TWrapperElement; stack: TNodeStack; qSrc : TFhirQuestionnaire; vsRef: TFhirReference);
var
  vs : TFhirValueSet;
  c : TFHIRCoding;
  res: TValidationResult;
begin
  vs := resolveBindingReference(qSrc, vsRef);
  if (warning(errors, IssueTypeCODEINVALID, value.locStart(), value.locEnd(), stack.literalPath, vs <> nil, 'ValueSet ' + describeReference(vsRef) + ' not found')) then
  begin
    try
      c := readAsCoding(value);
      try
        res := FContext.validateCode(c, vs);
        try
          if (not res.isOk()) then
            rule(errors, IssueTypeCODEINVALID, value.locStart(), value.locEnd(), stack.literalPath, false, 'The value provided ('+c.system+'::'+c.code+') is not in the options value set in the questionnaire');
        finally
          res.free;
        end;
      finally
        c.Free;
      end;
    except
      on e: Exception do
        warning(errors, IssueTypeCODEINVALID, value.locStart(), value.locEnd(), stack.literalPath, false, 'Error ' + e.message + ' validating Coding against Questionnaire Options');
    end;
  end;
end;

procedure TFHIRValidator.validateAnswerCode(errors: TFhirOperationOutcomeIssueList; answer: TWrapperElement; stack: TNodeStack; qSrc : TFhirQuestionnaire; qitem: TFhirQuestionnaireItem);
var
  v : TWrapperElement;
  ns : TNodeStack;
begin
  v := answer.getNamedChild('valueCoding');
  ns := stack.push(v, -1, nil, nil);
  try
    if qItem.optionList.Count > 0 then
      validateAnswerCode(errors, v, stack, qitem.optionList)
    else if qItem.options <> nil then
      validateAnswerCode(errors, v, stack, qSrc, qitem.options)
    else
      hint(errors, IssueTypeStructure, v.locStart, v.locEnd, stack.literalPath, false, 'Cannot validate options because no option or options are provided');
  finally
    ns.free;
  end;
end;

procedure TFHIRValidator.validateQuestionannaireResponse(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack);
var
  q : TWrapperElement;
  qsrc : TFhirQuestionnaire;
  inProgress : boolean;
begin
  q := element.getNamedChild('questionnaire');
  if hint(errors, IssueTypeRequired, element.locStart, element.locEnd, stack.literalPath, q <> nil, 'No questionnaire is identified, so no validation can be performed against the base questionnaire') then
  begin
    qsrc := TFhirQuestionnaire(FContext.fetchResource(frtQuestionnaire, q.getNamedChildValue('reference')));
    try
      if warning(errors, IssueTypeRequired, q.locStart, q.locEnd, stack.literalPath, qsrc <> nil, 'The questionnaire could not be resolved, so no validation can be performed against the base questionnaire') then
      begin
        inProgress := element.getNamedChildValue('status') = 'in-progress';
        validateQuestionannaireResponseItems(qsrc, qsrc.itemList, errors, element, stack, inProgress);
      end;
    finally
      qsrc.free;
    end;
  end;
end;

procedure TFHIRValidator.validateQuestionannaireResponseItem(qsrc : TFhirQuestionnaire; qItem : TFhirQuestionnaireItem; errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack; inProgress : boolean);
var
  answers, items : TAdvList<TWrapperElement>;
  answer, item : TWrapperElement;
  ns : TNodeStack;
  text : String;
begin
  text := element.getNamedChildValue('text');
  rule(errors, IssueTypeInvalid, element.locStart, element.locEnd, stack.literalPath, (text = '') or (text = qItem.text), 'If text exists, it must match the questionnaire definition for linkId '+qItem.linkId);

  answers := TAdvList<TWrapperElement>.create;
  try
    element.getNamedChildren('answer', answers);
    if inProgress then
      warning(errors, IssueTypeRequired, element.locStart, element.locEnd, stack.literalPath, (answers.Count > 0) or not qItem.required, 'No response answer found for required item '+qItem.linkId)
    else
      rule(errors, IssueTypeRequired, element.locStart, element.locEnd, stack.literalPath, (answers.Count > 0) or not qItem.required, 'No response answer found for required item '+qItem.linkId);
    if (answers.Count > 1) then
      rule(errors, IssueTypeInvalid, answers[1].locStart, answers[1].locEnd, stack.literalPath, qItem.repeats, 'Only one response answer item with this linkId allowed');

    for answer in answers do
    begin
      ns := stack.push(answer, -1, nil, nil);
      try
        case qitem.type_ of
          ItemTypeGroup: rule(errors, IssueTypeStructure, answer.locStart, answer.locEnd, stack.literalPath, false, 'Items of type group should not have answers');
          ItemTypeDisplay: ; // nothing
          ItemTypeBoolean:       validateQuestionnaireResponseItemType(errors, answer, ns, ['boolean']);
          ItemTypeDecimal:       validateQuestionnaireResponseItemType(errors, answer, ns, ['decimal']);
          ItemTypeInteger:       validateQuestionnaireResponseItemType(errors, answer, ns, ['integer']);
          ItemTypeDate:          validateQuestionnaireResponseItemType(errors, answer, ns, ['date']);
          ItemTypeDateTime:      validateQuestionnaireResponseItemType(errors, answer, ns, ['dateTime']);
          ItemTypeInstant:       validateQuestionnaireResponseItemType(errors, answer, ns, ['instant']);
          ItemTypeTime:          validateQuestionnaireResponseItemType(errors, answer, ns, ['time']);
          ItemTypeString:        validateQuestionnaireResponseItemType(errors, answer, ns, ['string']);
          ItemTypeText:          validateQuestionnaireResponseItemType(errors, answer, ns, ['text']);
          ItemTypeUrl:           validateQuestionnaireResponseItemType(errors, answer, ns, ['uri']);
          ItemTypeAttachment:    validateQuestionnaireResponseItemType(errors, answer, ns, ['Attachment']);
          ItemTypeReference:     validateQuestionnaireResponseItemType(errors, answer, ns, ['Reference']);
          ItemTypeQuantity:   if validateQuestionnaireResponseItemType(errors, answer, ns, ['Quantity']) = 'Quantity' then
            if qItem.hasExtension('???') then
              validateQuestionnaireResponseItemQuantity(errors, answer, ns);
          ItemTypeChoice:     if validateQuestionnaireResponseItemType(errors, answer, ns, ['Coding']) = 'Coding' then
            validateAnswerCode(errors, answer, ns, qsrc, qitem);
          ItemTypeOpenChoice: if validateQuestionnaireResponseItemType(errors, answer, ns, ['Coding', 'string']) = 'Coding' then
            validateAnswerCode(errors, answer, ns, qsrc, qitem);
        end;
        validateQuestionannaireResponseItems(qsrc, qitem.itemList, errors, answer, stack, inProgress);
      finally
        ns.free;
      end;
    end;
  finally
    answers.Free;
  end;
  if qitem.type_ = ItemTypeGroup then
    validateQuestionannaireResponseItems(qsrc, qitem.itemList, errors, element, stack, inProgress)
  else
  begin
    items := TAdvList<TWrapperElement>.create;
    try
      element.getNamedChildren('item', items);
      for item in items do
      begin
        ns := stack.push(item, -1, nil, nil);
        try
          rule(errors, IssueTypeStructure, item.locStart, item.locEnd, stack.literalPath, false, 'Items not of type group should not have items');
        finally
          ns.free;
        end;
      end;
    finally
      answers.Free;
    end;
  end;
end;

procedure TFHIRValidator.validateQuestionannaireResponseItem(qsrc: TFhirQuestionnaire; qItem: TFhirQuestionnaireItem; errors: TFhirOperationOutcomeIssueList; elements: TAdvList<TWrapperElement>; stack: TNodeStack; inProgress : boolean);
var
  ns : TNodeStack;
  element : TWrapperElement;
begin
  if (elements.Count > 1) then
    rule(errors, IssueTypeInvalid, elements[1].locStart, elements[1].locEnd, stack.literalPath, qItem.repeats, 'Only one response item with this linkId allowed');
  for element in elements do
  begin
    ns := stack.push(element, -1, nil, nil);
    try
      validateQuestionannaireResponseItem(qsrc, qitem, errors, element, ns, inProgress);
    finally
      ns.free;
    end;
  end;
end;

procedure TFHIRValidator.validateQuestionannaireResponseItems(qsrc : TFhirQuestionnaire; qItems : TFhirQuestionnaireItemList; errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack; inProgress : boolean);
var
  items, mapItem : TAdvList<TWrapperElement>;
  map : TAdvMap<TAdvList<TWrapperElement>>;
  index, lastIndex : integer;
  item : TWrapperElement;
  ns : TNodeStack;
  linkId : String;
  qItem : TFhirQuestionnaireItem;
  function getLinkIdIndex(linkId : String) : integer;
  var
    i : integer;
  begin
    result := -1;
    for i := 0 to qItems.Count -1 do
      if (qItems[i].linkId = linkid) then
        exit(i);
  end;
begin
  items := TAdvList<TWrapperElement>.create;
  try
    element.getNamedChildren('item', items);
    // now, sort into stacks
    map := TAdvMap<TAdvList<TWrapperElement>>.create;
    try
      lastIndex := -1;
      for item in items do
      begin
        linkId := item.getNamedChildValue('linkId');
        if rule(errors, IssueTypeRequired, item.locStart, item.locEnd, stack.literalPath, linkId <> '', 'No LinkId, so can''t be validated') then
        begin
          index := getLinkIdIndex(linkId);
          if index = -1 then
          begin
            if findQuestionnaireItem(qsrc, linkId, qitem) then
            begin
              rule(errors, IssueTypeStructure, item.locStart, item.locEnd, stack.literalPath, index > -1, 'Structural Error: item is in the wrong place');
              ns := stack.push(item, -1, nil, nil);
              try
                validateQuestionannaireResponseItem(qsrc, qitem, errors, element, ns, inProgress);
              finally
                ns.free;
              end;
            end
            else
              rule(errors, IssueTypeNotFound, item.locStart, item.locEnd, stack.literalPath, index > -1, 'LinkId "'+linkId+'" not found in questionnaire');
          end
          else
          begin
            rule(errors, IssueTypeStructure, item.locStart, item.locEnd, stack.literalPath, index >= lastIndex, 'Structural Error: items are out of order');
            lastIndex := index;
            if not map.TryGetValue(linkId, mapItem) then
            begin
              mapItem := TAdvList<TWrapperElement>.create;
              map.Add(linkId, mapitem);
            end;
            mapItem.Add(item.Link);
          end;
        end;
      end;

      // ok, now we have a list of known items, grouped by linkId. We've made an error for anything out of order
      for qItem in qItems do
      begin
        if map.TryGetValue(qItem.linkId, mapItem) then
          validateQuestionannaireResponseItem(qsrc, qItem, errors, mapItem, stack, inProgress)
        else
          rule(errors, IssueTypeRequired, element.locStart, element.locEnd, stack.literalPath, not qItem.required, 'No response found for required item '+qItem.linkId);
      end;
    finally
      map.Free;
    end;
  finally
    items.Free;
  end;
end;

procedure TFHIRValidator.validateQuestionnaireResponseItemQuantity(errors: TFhirOperationOutcomeIssueList; answer: TWrapperElement; stack: TNodeStack);
begin

end;

function TFHIRValidator.validateQuestionnaireResponseItemType(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack; types: array of String) : string;
var
  values : TAdvList<TWrapperElement>;
  ns : TNodeStack;
  s, l : String;
begin
  result := '';
  values := TAdvList<TWrapperElement>.create;
  try
    element.getNamedChildrenWithWildcard('value[x]', values);
    if values.Count > 0 then
    begin
      ns := stack.push(values[0], -1, nil, nil);
      try
        l := '';
        for s in types do
        begin
          commaAdd(l, s);
          if values[0].getName = 'value'+capitalize(s) then
          begin
            result := s;
            break;
          end;
        end;
        if length(types) = 1 then
          rule(errors, IssueTypeStructure, values[0].locStart, values[0].locEnd, ns.literalPath, result <> '', 'Answer value must be of type '+types[0])
        else
          rule(errors, IssueTypeStructure, values[0].locStart, values[0].locEnd, ns.literalPath, result <> '', 'Answer value must be one of the types '+l);
      finally
        ns.free;
      end;
    end;
  finally
    values.Free;
  end;
end;

function getFirstEntry(bundle: TWrapperElement): TWrapperElement;
var
  list: TAdvList<TWrapperElement>;
  resource: TWrapperElement;
begin
  list := TAdvList<TWrapperElement>.Create;
  try
    bundle.getNamedChildren('entry', list);
    if (list.count = 0) then
      result := nil
    else
    begin
      resource := list[0].getNamedChild('resource');
      if (resource = nil) then
        result := nil
      else
        result := resource.getFirstChild();
    end;
  finally
    list.Free;
  end;
end;

{
* The actual base entry point
}
procedure TFHIRValidator.validateResource(errors: TFhirOperationOutcomeIssueList; resource, element: TWrapperElement; profile: TFHIRStructureDefinition; idRule: TResourceIdStatus; stack: TNodeStack);
var
  resourceName : String;
  type_ : String;
  first: TWrapperElement;
  result : boolean;
  stack1 : TNodeStack;
begin
  if resource = nil then
    resource := element;

  if (stack = nil) then
    stack := TNodeStack.Create(element.isXml())
  else
    stack.Link;
  try

// getting going - either we got a profile, or not.
  result := true;
   if (element.isXml()) then
    result := rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), '/', element.getNamespace().equals(FHIR_NS),
      'Namespace mismatch - expected "' + FHIR_NS + '", found "' + element.getNamespace() + '"');
  if (result) then
   begin
     resourceName := element.getResourceType();
     if (profile = nil) then
     begin
      profile := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/' + resourceName));
      Fowned.add(profile);
      rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.addToLiteralPath(resourceName), profile <> nil, 'No profile found for resource type "' + resourceName + '"');
    end;
    if (profile <> nil) then
     begin
       if profile.ConstrainedType <> '' then
         type_ :=  profile.ConstrainedType
        else
        type_ := profile.name;

      // special case: we have a bundle, and the profile is not for a bundle. We'll try the first entry instead
      if (type_ = resourceName) and (resourceName = 'Bundle') then
      begin
        first := getFirstEntry(element);
        if (first <> nil) and (first.getResourceType() = type_) then
        begin
          element := first;
          resourceName := element.getResourceType();
            idRule := risOptional; // why is this?
        end;
      end;

      result := rule(errors, IssueTypeINVALID, nullLoc, nullLoc, stack.addToLiteralPath(resourceName), type_ = resourceName, 'Specified profile type was "' + profile.ConstrainedType +
        '", but resource type was "' + resourceName + '"');
     end;

    if (result) then
  begin
        stack1 := stack.push(element, -1, profile.Snapshot.ElementList[0], profile.Snapshot.ElementList[0]);
        try
          if (idRule = risRequired) and ((element.getNamedChild('id') = nil)) then
            rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack1.literalPath, false, 'Resource requires an id, but none is present')
          else if (idRule = risProhibited) and ((element.getNamedChild('id') <> nil)) then
            rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack1.literalPath, false, 'Resource has an id, but none is allowed');
          start(errors, resource, element, profile, stack1); // root is both definition and type
        finally
          stack1.Free;
        end;
    end;
  end;
  finally
    stack.Free;
  end;
end;

// we assume that the following things are true:
// the instance at root is valid against the schema and schematron
// the instance validator had no issues against the base resource profile
// profile is valid, and matches the resource name
procedure TFHIRValidator.start(errors: TFhirOperationOutcomeIssueList; resource, element: TWrapperElement; profile: TFHIRStructureDefinition; stack: TNodeStack);
begin
  if (rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), stack.literalPath, profile.Snapshot <> nil,
    'StructureDefinition has no snapshot - validation is against the snapshot, so it must be provided')) then
  begin
    validateElement(errors, profile, profile.Snapshot.ElementList[0], nil, nil, resource, element, element.getName(), stack, false);
    checkDeclaredProfiles(errors, resource, element, stack);

    // specific known special validations
    if (element.getResourceType() = 'Bundle') then
      validateBundle(errors, element, stack);
    if (element.getResourceType() = 'Observation') then
      validateObservation(errors, element, stack);
    if (element.getResourceType() = 'QuestionnaireResponse') then
      validateQuestionannaireResponse(errors, element, stack);

  end;
end;

procedure TFHIRValidator.checkDeclaredProfiles(errors: TFhirOperationOutcomeIssueList; resource, element: TWrapperElement; stack: TNodeStack);
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
    profiles := TAdvList<TWrapperElement>.Create();
    try
    meta.getNamedChildren('profile', profiles);
    i := 0;
    for profile in profiles do
    begin
      ref := profile.getAttribute('value');
      p := stack.addToLiteralPath(['meta', 'profile', ':'+inttostr(i)]);
      if (rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), p, ref <> '', 'StructureDefinition reference invalid')) then
      begin
        pr := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, ref));
        FOwned.add(pr);
        if (warning(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), p, pr <> nil, 'StructureDefinition reference could not be resolved')) then
          begin
          if (rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), p, pr.Snapshot <> nil,
            'StructureDefinition has no snapshot - validation is against the snapshot, so it must be provided')) then
            begin
            validateElement(errors, pr, pr.Snapshot.ElementList[0], nil, nil, resource, element, element.getName, stack, false);
            end;
          end;
          inc(i);
        end;
      end;
    finally
      profiles.free;
    end;
    end;
  end;

procedure TFHIRValidator.validateBundle(errors: TFhirOperationOutcomeIssueList; bundle: TWrapperElement; stack: TNodeStack);
var
  entries : TAdvList<TWrapperElement>;
  type_, id : String;
  firstEntry : TWrapperElement;
  firstStack : TNodeStack;
  fullUrl : String;
  resource, res : TWrapperElement;
  localStack, t: TNodeStack;
begin
  entries := TAdvList<TWrapperElement>.Create();
  try
  bundle.getNamedChildren('entry', entries);
  type_ := bundle.getNamedChildValue('type');
  if (entries.count = 0) then
  begin
    rule(errors, IssueTypeINVALID, stack.literalPath, (type_ <> 'document') and (type_ <> 'message'), 'Documents or Messages must contain at least one entry');
  end
  else
  begin
    firstEntry := entries[0];
    firstStack := stack.push(firstEntry, 0, nil, nil);
      try
    fullUrl := firstEntry.getNamedChildValue('fullUrl');

    if (type_ = 'document') then
    begin
      res := firstEntry.getNamedChild('resource');
      localStack := firstStack.push(res, -1, nil, nil);
          try
      resource := res.getFirstChild();
      id := resource.getNamedChildValue('id');
      if (rule(errors, IssueTypeINVALID, firstEntry.locStart(), firstEntry.locEnd(), stack.addToLiteralPath(['entry', ':0']), res <> nil, 'No resource on first entry')) then
      begin
        if (bundle.isXml()) then
              begin
                t := localStack.push(resource, -1, nil, nil);
                try
                  validateDocument(errors, entries, resource, t, fullUrl, id);
                finally
                  t.Free;
                end;
              end
        else
          validateDocument(errors, entries, res, localStack, fullUrl, id);
      end;
          finally
            localstack.free;
          end;
    end;
    if (type_ = 'message') then
      validateMessage(errors, bundle);
      finally
        firstStack.Free;
      end;
    end;
  finally
    entries.free;
  end;
end;

procedure TFHIRValidator.validateMessage(errors: TFhirOperationOutcomeIssueList; bundle: TWrapperElement);
begin
end;

procedure TFHIRValidator.validateDocument(errors: TFhirOperationOutcomeIssueList; entries: TAdvList<TWrapperElement>; composition: TWrapperElement; stack: TNodeStack; fullUrl, id: String);
var
  ns : TNodeStack;
  elem : TWrapperElement;
begin
  // first entry must be a composition
  if (rule(errors, IssueTypeINVALID, composition.locStart(), composition.locEnd(), stack.literalPath, composition.getResourceType() = 'Composition',
    'The first entry in a document must be a composition')) then
  begin
    elem := composition.getNamedChild('subject');
    if rule(errors, IssueTypeINVALID, composition.locStart(), composition.locEnd(), stack.literalPath, elem <> nil, 'In a document, a compsosition must have a subject') then
    begin
    // the composition subject and section references must resolve in the bundle
      ns := stack.push(elem, -1, nil, nil);
    try
      validateBundleReference(errors, entries, composition.getNamedChild('subject'), 'Composition Subject', ns, fullUrl, 'Composition', id);
    finally
      ns.Free;
    end;
    validateSections(errors, entries, composition, stack, fullUrl, id);
  end;
end;
end;
// rule(errors, IssueTypeINVALID, bundle.locStart(), bundle.locEnd(), 'Bundle', !'urn:guid:' = base), 'The base "urn:guid:" is not valid (use urn:uuid:)');
// rule(errors, IssueTypeINVALID, entry.locStart(), entry.locEnd(), localStack.literalPath, !'urn:guid:' = ebase), 'The base "urn:guid:" is not valid');
// rule(errors, IssueTypeINVALID, entry.locStart(), entry.locEnd(), localStack.literalPath, !Utilities.noString(base) ) or ( !Utilities.noString(ebase), 'entry does not have a base');
//String firstBase := nil;
//firstBase := ebase = nil ? base : ebase;

procedure TFHIRValidator.validateSections(errors: TFhirOperationOutcomeIssueList; entries: TAdvList<TWrapperElement>; focus: TWrapperElement; stack: TNodeStack;
  fullUrl, id: String);
var
  sections : TAdvList<TWrapperElement>;
  section : TWrapperElement;
  i : integer;
  localStack : TNodeStack;
begin
  sections := TAdvList<TWrapperElement>.Create();
  try
  focus.getNamedChildren('entry', sections);
  i := 0;
  for section in sections do
  begin
    localStack := stack.push(section,  1, nil, nil);
      try
    validateBundleReference(errors, entries, section.getNamedChild('content'), 'Section Content', localStack, fullUrl, 'Composition', id);
    validateSections(errors, entries, section, localStack, fullUrl, id);
    inc(i);
      finally
        localStack.Free;
      end;
    end;
  finally
    sections.free;
  end;
end;

procedure TFHIRValidator.validateBundleReference(errors: TFhirOperationOutcomeIssueList; entries: TAdvList<TWrapperElement>; ref: TWrapperElement; name: String;
  stack: TNodeStack; fullUrl, type_, id: String);
var
  target : TWrapperElement;
begin
  if (ref <> nil) and (ref.getNamedChildValue('reference') <> '') then
  begin
    target := resolveInBundle(entries, ref.getNamedChildValue('reference'), fullUrl, type_, id);
    rule(errors, IssueTypeINVALID, target.locStart(), target.locEnd(), stack.addToLiteralPath(['reference']), target <> nil,
      'Unable to resolve the target of the reference in the bundle (' + name + ')');
  end;
end;

Function TFHIRValidator.resolveInBundle(entries: TAdvList<TWrapperElement>; ref, fullUrl, type_, id: String): TWrapperElement;
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

function TFHIRValidator.getProfileForType(type_: String): TFHIRStructureDefinition;
begin
  result := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/' + type_));
  FOwned.add(result);
end;

procedure TFHIRValidator.validateObservation(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; stack: TNodeStack);
begin
  // all observations should have a subject, a performer, and a time
  bpCheck(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.literalPath, element.getNamedChild('subject') <> nil, 'All observations should have a subject');
  bpCheck(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.literalPath, element.getNamedChild('performer') <> nil, 'All observations should have a performer');
  bpCheck(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.literalPath, (element.getNamedChild('effectiveDateTime') <> nil) or
    (element.getNamedChild('effectivePeriod') <> nil), 'All observations should have an effectiveDateTime or an effectivePeriod');
end;

procedure TFHIRValidator.bpCheck(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; literalPath: String; test: boolean; message: String);
begin
  case BPWarnings of
    bpwlHint:
      hint(errors, t, locStart, locEnd, literalPath, test, message);
    bpwlWarning:
      warning(errors, t, locStart, locEnd, literalPath, test, message);
    bpwlError:
      rule(errors, t, locStart, locEnd, literalPath, test, message);
    bpwlIgnore:
      ; // do nothing
  end;
end;

function isPrimitiveType(t : String) : boolean;
begin
  result := SameText(t, 'boolean') or SameText(t, 'integer') or SameText(t, 'string') or SameText(t, 'decimal') or SameText(t, 'uri') or SameText(t, 'base64Binary') or
    SameText(t, 'instant') or SameText(t, 'date') or SameText(t, 'uuid') or SameText(t, 'id') or SameText(t, 'xhtml') or SameText(t, 'markdown') or SameText(t, 'dateTime') or
    SameText(t, 'time') or SameText(t, 'code') or SameText(t, 'oid') or SameText(t, 'id');
end;

function describeTypes(types : TFhirElementDefinitionTypeList) : String;
var
  tc : TFhirElementDefinitionType;
begin
  result := '';
  for tc in types do
    CommaAdd(result, tc.code);
end;

function resolveNameReference(Snapshot: TFhirStructureDefinitionSnapshot; name: String): TFHIRElementDefinition;
var
  ed : TFHIRElementDefinition;
begin
  result := nil;
  for ed in Snapshot.ElementList do
    if (name = ed.name) then
    begin
  		result := ed;
      exit;
    end;
 end;

function findElement(profile : TFHIRStructureDefinition; name : String) : TFHIRElementDefinition;
var
  c : TFHIRElementDefinition;
begin
  result := nil;
  for c in profile.Snapshot.ElementList do
  begin
    if (c.path = name) then
    begin
  		result := c;
      exit;
    end;
  end;
end;

function TFHIRValidator.resolveType(t: String): TFHIRElementDefinition;
var
  url : String;
  sd: TFHIRStructureDefinition;
begin
  url := 'http://hl7.org/fhir/StructureDefinition/'+t;
  sd := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, url));
  FOwned.add(sd);
  if (sd = nil) or (sd.Snapshot = nil) then
      result := nil
    else
      result := sd.Snapshot.ElementList[0];
  end;

function TFHIRValidator.rule(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; path: String; thePass: boolean; msg: String): boolean;
var
  vm : TFhirOperationOutcomeIssue;
begin
  if not thePass then
  begin
    vm := TFhirOperationOutcomeIssue.Create;
    errors.Add(vm);
    vm.severity := IssueSeverityError;
    vm.locationList.append.value := path;
    vm.code := t;
    vm.details := TFHIRCodeableConcept.Create;
    vm.details.text := msg;
  end;
  result := thePass;
end;

function TFHIRValidator.rule(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; path: String; thePass: boolean; msg: String): boolean;
var
  vm : TFhirOperationOutcomeIssue;
begin
  if not thePass then
  begin
    vm := TFhirOperationOutcomeIssue.Create;
    errors.Add(vm);
    vm.Tags['s-l'] := inttostr(locStart.line);
    vm.Tags['s-c'] := inttostr(locStart.col);
    vm.Tags['e-l'] := inttostr(locEnd.line);
    vm.Tags['e-c'] := inttostr(locEnd.col);
    vm.severity := IssueSeverityError;
    vm.locationList.append.value := path + ' (@ line ' + inttostr(locStart.line) + '/ col ' + inttostr(locStart.col) + ')';
    vm.code := t;
    vm.details := TFHIRCodeableConcept.Create;
    vm.details.text := msg;
  end;
  result := thePass;
end;

function isBundleEntry(path : String) : boolean;
var
  parts : TArray<String>;
begin
  parts := path.split(['.']);
  result := (length(parts) > 3) and (parts[length(parts) - 1].startsWith('resource')) and ((parts[length(parts) - 3] = 'entry')) or (parts[length(parts) - 2] = 'entry');
end;

function isParametersEntry(path: String): boolean;
var
  parts: TArray<String>;
begin
  parts := path.split(['.']);
  result := (length(parts) > 2) and (parts[length(parts) - 1].startsWith('resource')) and ((parts[length(parts) - 2] = 'parameter')) or (parts[length(parts) - 2].startsWith('entry['));
end;

procedure TFHIRValidator.validateElement(errors: TFhirOperationOutcomeIssueList; profile: TFHIRStructureDefinition; definition: TFHIRElementDefinition;
  cprofile: TFHIRStructureDefinition; context: TFHIRElementDefinition; resource, element: TWrapperElement; actualType: String; stack: TNodeStack; inCodeableConcept: boolean);
var
  childDefinitions : TFHIRElementDefinitionList;
  children : TAdvList<TElementInfo>;
  iter : TChildIterator;
  slice, ed, td: TFHIRElementDefinition;
  process, match : boolean;
  ei : TElementInfo;
  count : integer;
  t, prefix : String;
  tc, trc : TFhirElementDefinitionType;
  p: TFHIRStructureDefinition;
  localStack : TNodeStack;
  thisIsCodeableConcept: boolean;
begin
  assert(profile.snapshot.elementList.ExistsByReference(definition));
  // for later re-use
  element.Definition := definition;
  element.Profile := profile;

  // irrespective of what element it is, it cannot be empty
	if (element.isXml()) then
  begin
    rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.literalPath, FHIR_NS = element.getNamespace(),
      'Namespace mismatch - expected "' + FHIR_NS + '", found "' + element.getNamespace() + '"');
    rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.literalPath, not element.hasNamespace('http://www.w3.org/2001/XMLSchema-instance'),
      'Schema Instance Namespace is not allowed in instances');
    rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.literalPath, not element.hasProcessingInstruction(), 'No Processing Instructions in resources');
  end;
  rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.literalPath, not empty(element),
    'Elements must have some content (@value, extensions, or children elements)');

  checkInvariants(errors, stack.literalPath, profile, definition, '', '', resource, element);

  // get the list of direct defined children, including slices
  children := TAdvList<TElementInfo>.Create();
  childDefinitions := getChildMap(profile, definition.name, definition.path, definition.NameReference);
  try

  // 1. List the children, and remember their exact path (convenience)
    iter := TChildIterator.Create(stack.literalPath, element.link);
    try
    while (iter.next()) do
    children.Add(TElementInfo.Create(iter.name(), iter.element(), iter.path(), iter.count()));
    finally
      iter.Free;
    end;

    // 2. assign children to a definition
    // for each definition, for each child, check whether it belongs in the slice
    slice := nil;
    for ed in childDefinitions do
    begin
    	process := true;
    	// where are we with slicing
    	if (ed.Slicing <> nil) then
      begin
      if (slice <> nil) and (slice.path = ed.path) then
        raise Exception.Create('Slice encountered midway through path on ' + slice.path);
    		slice := ed;
    		process := false;
    	end
    else if (slice <> nil) and (slice.path <> ed.path) then
    		slice := nil;

    	if (process) then
      begin
    	  for ei in children do
        begin
    			match := false;
          if (slice = nil) then
          begin
          match := nameMatches(ei.name, tail(ed.path));
      		end
          else
          begin
          if nameMatches(ei.name, tail(ed.path)) then
    					match := sliceMatches(ei.element, ei.path, slice, ed, profile);
      		end;
    	  	if (match) then
          begin
          if (rule(errors, IssueTypeINVALID, ei.locStart(), ei.locEnd(), ei.path, ei.definition = nil, 'Element matches more than one slice')) then
      				ei.definition := ed;
      		end;
    	  end;
      end;
  	end;

    for ei in children do
      if (ei.path.endsWith('.extension')) then
      rule(errors, IssueTypeINVALID, ei.locStart(), ei.locEnd(), ei.path, ei.definition <> nil, 'Element is unknown or does not match any slice (url:="' +
        ei.element.getAttribute('url') + '")')
      else
      rule(errors, IssueTypeINVALID, ei.locStart(), ei.locEnd(), ei.path, (ei.definition <> nil) or (not ei.element.isXml() and (ei.element.getName() = 'fhir_comments')),
        'Element is unknown or does not match any slice');

    // 3. report any definitions that have a cardinality problem
    for ed in childDefinitions do
    begin
    	if (ed.representation = []) then
      begin // ignore xml attributes
       	count := 0;
        for ei in children do
        	if (ei.definition = ed) then
        		inc(count);
    		if (ed.Min <> '0') then
        rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), stack.literalPath, count >= StrToInt(ed.Min), 'Element "' + stack.literalPath + '.' + tail(ed.path) +
          '": minimum required = ' + ed.Min + ', but only found ' + inttostr(count));
      if (ed.max <> '*') then
        rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), stack.literalPath, count <= StrToInt(ed.max), 'Element ' + tail(ed.path) + ' @ ' + stack.literalPath
          + ': max allowed = ' + ed.max + ', but found ' + inttostr(count));

    	end;
    end;
    // 4. check order if any slices are orderd. (todo)

    // 5. inspect each child for validity
    for ei in children do
    begin
    	if (ei.definition <> nil) then
      begin
      t := '';
      td := nil;
      if (ei.definition.Type_List.count = 1) and (ei.definition.Type_List[0].code <> '*') and (ei.definition.Type_List[0].code <> 'Element') and
        (ei.definition.Type_List[0].code <> 'BackboneElement') then
        t := ei.definition.Type_List[0].code
      else if (ei.definition.Type_List.count = 1) and (ei.definition.Type_List[0].code = '*') then
        begin
        prefix := tail(ei.definition.path);
          assert(prefix.endsWith('[x]'));
          t := ei.name.substring(prefix.length-3);
          if (isPrimitiveType(t)) then
            t := uncapitalize(t);
    		end
      else if (ei.definition.Type_List.count > 1) then
        begin
        prefix := tail(ei.definition.path);
          prefix := prefix.substring(0, prefix.length-3);
          for tc in ei.definition.Type_List do
          if ((prefix + capitalize(tc.code)) = ei.name) then
            t := tc.code;
            if (t = '') then
            begin
        			trc := ei.definition.Type_List[0];
          if (trc.code = 'Reference') then
        				t := 'Reference'
              else
              begin
                assert(prefix.endsWith('[x]'));
            rule(errors, IssueTypeSTRUCTURE, ei.locStart(), ei.locEnd(), stack.literalPath, false, 'The element ' + ei.name + ' is illegal. Valid types at this point are ' +
              describeTypes(ei.definition.Type_List));
          end;
          end;
    		end
        else if (ei.definition.NameReference <> '') then
    			td := resolveNameReference(profile.Snapshot, ei.definition.NameReference);

        if (t <> '') then
        begin
          if (t.startsWith('@')) then
          begin
    				ei.definition := findElement(profile, t.substring(1));
            t := '';
          end;
        end;
        if (t = '') then
          localStack := stack.push(ei.element, ei.count, ei.definition, td)
        else
      		localStack := stack.push(ei.element, ei.count, ei.definition, resolveType(t));
        try
        if ei.path <> localStack.literalPath then
        raise Exception.Create('paths differ: ' + ei.path + ' vs ' + localStack.literalPath);

    		assert(ei.path = localStack.literalPath);
      thisIsCodeableConcept := false;

        if (t <> '') then
        begin
          if (isPrimitiveType(t)) then
              checkPrimitive(errors, ei.path, t, ei.definition, ei.element, profile)
          else
          begin
            if (t = 'Identifier') then
              checkIdentifier(errors, ei.path, ei.element, ei.definition)
            else if (t = 'Coding') then
            checkCoding(errors, ei.path, ei.element, profile, ei.definition, inCodeableConcept)
              else if (t = 'Quantity') then
                checkQuantity(errors, ei.path, ei.element, ei.definition)
            else if (t = 'CodeableConcept') then
          begin
            checkCodeableConcept(errors, ei.path, ei.element, profile, ei.definition);
            thisIsCodeableConcept := true;
          end
            else if (t = 'Reference') then
              checkReference(errors, ei.path, ei.element, profile, ei.definition, actualType, localStack);

            if (t = 'Extension') then
              checkExtension(errors, ei.path, ei.element, ei.definition, profile, localStack)
            else if (t = 'Resource') then
                validateContains(errors, ei.path, ei.definition, definition, resource, ei.element, localStack, idStatusForEntry(element, ei))
            else
            begin
              p := getProfileForType(t);
            if (rule(errors, IssueTypeSTRUCTURE, ei.locStart(), ei.locEnd(), ei.path, p <> nil, 'Unknown type ' + t)) then
              validateElement(errors, p, p.Snapshot.ElementList[0], profile, ei.definition, resource, ei.element, t, localStack, thisIsCodeableConcept);
            end;
          end;
        end
        else
        begin
        if (rule(errors, IssueTypeSTRUCTURE, ei.locStart(), ei.locEnd(), stack.literalPath, ei.definition <> nil, 'Unrecognised Content ' + ei.name)) then
          validateElement(errors, profile, ei.definition, nil, nil, resource, ei.element, t, localStack, false);
        end;
        finally
          localStack.Free;
        end;
      end;
      end;
  finally
    childDefinitions.Free;
    children.Free;
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
function TFHIRValidator.sliceMatches(element: TWrapperElement; path: String; slice, ed: TFHIRElementDefinition; profile: TFHIRStructureDefinition): boolean;
var
  s : TFhirString;
  discriminator : String;
  criteria : TFHIRElementDefinition;
  value: TFhirElement;
begin
  result := true;
  if (slice.Slicing.DiscriminatorList.count = 0) then
    result := false // cannot validate in this case
  else
    for s in slice.Slicing.DiscriminatorList do
    begin
      discriminator := s.value;
	  	criteria := getCriteriaForDiscriminator(path, ed, discriminator, profile);
      if (discriminator = 'url') and criteria.path.endsWith('xtension.url') then
      begin
        if (element.getAttribute('url') <> TFHIRUri(criteria.fixed).value) then
        begin
	  			result := false;
          exit;
        end;
	  	end
      else
      begin
	  		value := getValueForDiscriminator(element, discriminator, criteria);
	  		if (not valueMatchesCriteria(value, criteria)) then
        begin
	  			result := false;
          exit;
        end;
	  	end;
	  end;
  end;

function TFHIRValidator.valueMatchesCriteria(value: TFhirElement; criteria: TFHIRElementDefinition): boolean;
begin
  result := false;
end;

function TFHIRValidator.warning(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; path: String; thePass: boolean; msg: String): boolean;
var
  vm : TFhirOperationOutcomeIssue;
begin
  if not thePass then
  begin
    vm := TFhirOperationOutcomeIssue.Create;
    errors.Add(vm);
    vm.Tags['s-l'] := inttostr(locStart.line);
    vm.Tags['s-c'] := inttostr(locStart.col);
    vm.Tags['e-l'] := inttostr(locEnd.line);
    vm.Tags['e-c'] := inttostr(locEnd.col);
    vm.severity := IssueSeverityWarning;
    vm.locationList.append.value := path + ' (@ line ' + inttostr(locStart.line) + '/ col ' + inttostr(locStart.col) + ')';
    vm.code := t;
    vm.details := TFHIRCodeableConcept.Create;
    vm.details.text := msg;
  end;
  result := thePass;
end;

function TFHIRValidator.getValueForDiscriminator(element: TWrapperElement; discriminator: String; criteria: TFHIRElementDefinition): TFhirElement;
begin
  result := nil;
end;

function TFHIRValidator.hint(errors: TFhirOperationOutcomeIssueList; t: TFhirIssueTypeEnum; locStart, locEnd: TSourceLocation; path: String; thePass: boolean; msg: String): boolean;
var
  vm : TFhirOperationOutcomeIssue;
begin
  if not thePass then
  begin
    vm := TFhirOperationOutcomeIssue.Create;
    errors.Add(vm);
    vm.Tags['s-l'] := inttostr(locStart.line);
    vm.Tags['s-c'] := inttostr(locStart.col);
    vm.Tags['e-l'] := inttostr(locEnd.line);
    vm.Tags['e-c'] := inttostr(locEnd.col);
    vm.severity := IssueSeverityInformation;
    vm.locationList.append.value := path + ' (@ line ' + inttostr(locStart.line) + '/ col ' + inttostr(locStart.col) + ')';
    vm.code := t;
    vm.details := TFHIRCodeableConcept.Create;
    vm.details.text := msg;
  end;
  result := thePass;
end;

function TFHIRValidator.idStatusForEntry(ep : TWrapperElement; ei: TElementInfo): TResourceIdStatus;
var
  e : TWrapperElement;
  s : String;
begin
  if (isBundleEntry(ei.path)) then
  begin
		e := ep.getNamedChild('request');
    if (e <> nil) then
      e := e.getNamedChild('method');
    if (e = nil) then
      result := risRequired
    else
    begin
      s := e.getAttribute('value');
      if (s = 'PUT') then
        result := risRequired
      else if (s = 'POST') then
        result := risProhibited
      else // actually, we should never get to here; a bundle entry with method get/delete should not have a resource
        result := risOptional;
    end
  end
  else if (isParametersEntry(ei.path)) then
		result := risOptional
  else
		result := risRequired;
end;

procedure TFHIRValidator.loadSchema;
var
  v : Variant;
begin
  // prep the schemas
  v := CreateOLEObject(GMsXmlProgId_SCHEMA);
  FCache := IUnknown(TVarData(v).VDispatch) as IXMLDOMSchemaCollection;
  FCache.add('http://www.w3.org/XML/1998/namespace', loadDoc('xml.xsd'));
  FCache.add('http://www.w3.org/1999/xhtml', loadDoc('fhir-xhtml.xsd'));
  FCache.add('http://www.w3.org/2000/09/xmldsig#', loadDoc('xmldsig-core-schema.xsd'));
  FCache.add('http://hl7.org/fhir', loadDoc('fhir-single.xsd'));
end;

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
  buf := FContext.GetSourceByName(name);
  LVariant := LoadMsXMLDomV(isfree);
  Result := IUnknown(TVarData(LVariant).VDispatch) as IXMLDomDocument2;
  result.async := false;
  if isFree then
    result.resolveExternals := true;
  if not result.loadXML(TrimBof(buf.AsUnicode)) then
    raise Exception.create('unable to parse XML because '+result.parseError.reason);
end;

function TFHIRValidator.getCriteriaForDiscriminator(path: String; ed: TFHIRElementDefinition; discriminator: String; profile: TFHIRStructureDefinition)
  : TFHIRElementDefinition;
var
  childDefinitions, Snapshot: TFHIRElementDefinitionList;
//  t : TFHIRStructureDefinition;
  originalPath, goal : String;
  ty : TFHIRStructureDefinition;
  index : integer;
begin
  result := nil;
  childDefinitions := getChildMap(profile, ed.name, ed.path, '');
  try
  Snapshot := nil;
  if (childDefinitions.count = 0) then
  begin
  	// going to look at the type
    if (ed.Type_List.count = 0) then
      raise Exception.Create('Error in profile for ' + path + ' no children, no type');
    if (ed.Type_List.count > 1) then
      raise Exception.Create('Error in profile for ' + path + ' multiple types defined in slice discriminator');
    if (ed.Type_List[0].profileList.count > 0) then
    begin
      // need to do some special processing for reference here...
      if (ed.Type_List[0].code = 'Reference') then
        discriminator := discriminator.substring(discriminator.indexOf('.') + 1);
      ty := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, ed.Type_List[0].profileList[0].value));
    end
    else
      ty := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/' + ed.Type_List[0].code));
    FOwned.add(ty);
    Snapshot := ty.Snapshot.ElementList;
    ed := Snapshot[0];
      index := 0;
  end
  else
  begin
      Snapshot := ChildDefinitions;
      index := -1;
  end;
  originalPath := ed.path;
  goal := originalPath+'.'+discriminator;

  inc(index);
  while (index < Snapshot.count) and (Snapshot[index].path <> originalPath) do
  begin
    if (Snapshot[index].path = goal) then
    begin
      result := Snapshot[index];
      exit;
    end;
    inc(index);
  end;
  raise Exception.Create('Unable to find discriminator definition for ' + goal + ' in ' + discriminator + ' at ' + path);
  finally
    childDefinitions.Free;
  end;
end;

function TFHIRValidator.checkResourceType(ty: String): String;
var
  t : TFHIRResource;
begin
  t := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/' + ty));
  FOwned.add(t);
  if (t <> nil) then
    result := ty
  else
    result := '';
  end;

function TFHIRValidator.tryParse(ref: String): String;
var
  parts : TArray<String>;
begin
  parts := ref.split(['/']);
  case (length(parts)) of
    1:
      result := '';
    2:
      result := checkResourceType(parts[0]);
  else
    if (parts[length(parts)-2] = '_history') then
      result := checkResourceType(parts[length(parts)-4])
    else
      result := checkResourceType(parts[length(parts)-2]);
  end;
end;

procedure TFHIRValidator.checkReference(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; profile: TFHIRStructureDefinition;
  container: TFHIRElementDefinition; parentType: String; stack: TNodeStack);
var
  ref : String;
  we : TWrapperElement;
  ft, pr, bt : String;
  ok : boolean;
  b : String;
  ty : TFhirElementDefinitionType;
begin
  ref := element.getNamedChildValue('reference');
  if (ref = '') then
  begin
    // todo - what should we do in this case?
    hint(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path, element.getNamedChildValue('display') <> '',
      'A Reference without an actual reference should have a display');
    exit;
  end;

  we := resolve(ref, stack);
  if (we <> nil) then
    ft := we.getResourceType()
  else
    ft := tryParse(ref);
  if (hint(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path, ft <> '', 'Unable to determine type of target resource')) then
  begin
    ok := false;
    b := '';
    for ty in container.Type_List do
    begin
      if (not ok) and (ty.code = 'Reference') then
      begin
        // we validate as much as we can. First, can we infer a type from the profile?
        if (ty.profileList.count = 0) or (ty.profileList[0].value = 'http://hl7.org/fhir/StructureDefinition/Resource') then
          ok := true
        else
        begin
          pr := ty.profileList[0].value;
          bt := getBaseType(profile, pr);
          if (rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path, bt <> '', 'Unable to resolve the profile reference "' + pr + '"')) then
          begin
            if (b <> '') then
              b := b + ', ';
            b := b + bt;
            ok := bt = ft;
          end
          else
            ok := true; // suppress following check
        end;
      end;
      if (not ok) and (ty.code = '*') then
      begin
        ok := true; // can refer to anything
      end;
    end;
    rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path, ok, 'Invalid Resource target type. Found ' + ft + ', but expected one of (' + b + ')');
  end;
end;

function TFHIRValidator.resolve(ref: String; stack: TNodeStack): TWrapperElement;
var
  res : TWrapperElement;
begin
  if (ref.startsWith('#')) then
  begin
    // work back through the contained list.
    // really, there should only be one level for this (contained resources cannot contain
    // contained resources), but we"ll leave that to some other code to worry about
    while (stack <> nil) and (stack.element <> nil) do
    begin
      res := getContainedById(stack.element, ref.substring(1));
      if (res <> nil) then
      begin
        result := res;
        exit;
      end;
      stack := stack.parent;
    end;
    result := nil;
  end
  else
  begin
    // work back through the contained list - if any of them are bundles, try to resolve
    // the resource in the bundle
    while (stack <> nil) and (stack.element <> nil) do
    begin
      if ('Bundle' = stack.element.getResourceType()) then
      begin
        res := getFromBundle(stack.element, ref.substring(1));
        if (res <> nil) then
        begin
          result := res;
          exit;
        end;
      end;
      stack := stack.parent;
    end;
    // todo: consult the external host for resolution
    result := nil;
  end;
end;

function TFHIRValidator.getFromBundle(bundle: TWrapperElement; ref: String): TWrapperElement;
var
  entries : TAdvList<TWrapperElement>;
  we, res : TWrapperElement;
  url : String;
begin
  entries := TAdvList<TWrapperElement>.Create();
  try
  bundle.getNamedChildren('entry', entries);
  for we in entries do
  begin
    res := we.getNamedChild('resource').getFirstChild();
    if (res <> nil) then
    begin
      url := genFullUrl(bundle.getNamedChildValue('base'), we.getNamedChildValue('base'), res.getName(), res.getNamedChildValue('id'));
      if (url.endsWith(ref)) then
      begin
        result := res;
        exit;
      end;
    end;
  end;
  result := nil;
  finally
    entries.free;
  end;
end;

function TFHIRValidator.genFullUrl(bundleBase, entryBase, ty, id: String): String;
var
  base : String;
begin
  if entryBase = '' then
    base := bundleBase
  else
    base := entryBase;
  if (base = '') then
    result := ty+'/'+id
  else if ('urn:uuid' = base) or ('urn:oid' = base) then
    result := base+id
  else
    result := base+'/'+ty+'/'+id;
end;

function TFHIRValidator.getContainedById(container: TWrapperElement; id: String): TWrapperElement;
var
  contained : TAdvList<TWrapperElement>;
  we, res : TWrapperElement;
begin
  contained := TAdvList<TWrapperElement>.Create();
  try
  container.getNamedChildren('contained', contained);
  for we in contained do
  begin
    if we.isXml() then
      res :=  we.getFirstChild()
    else
      res := we;
    if (id = res.getNamedChildValue('id')) then
    begin
      result := res;
      exit;
    end;
  end;
  result := nil;
  finally
    contained.free;
  end;
end;

function TFHIRValidator.getBaseType(profile: TFHIRStructureDefinition; pr: String): String;
var
  p : TFHIRStructureDefinition;
begin
//    if (pr.startsWith('http://hl7.org/fhir/StructureDefinition/')) begin
//      // this just has to be a base type
//      return pr.substring(40);
//    end; else begin
  p := resolveProfile(profile, pr);
  if (p = nil) then
    result := ''
  else if (p.Kind = StructureDefinitionKindRESOURCE) then
    result := p.Snapshot.ElementList[0].path
  else
    result := p.Snapshot.ElementList[0].Type_List[0].code;
//    end;
end;

function TFHIRValidator.resolveProfile(profile: TFHIRStructureDefinition; pr: String): TFHIRStructureDefinition;
var
  r : TFHIRResource;
begin
  if (pr.startsWith('#')) then
  begin
    for r in profile.containedList do
    begin
      if (r.id = pr.substring(1)) and (r is TFHIRStructureDefinition) then
      begin
        result := r as TFHIRStructureDefinition;
      end;
    end;
    result := nil;
  end
  else
  begin
    result := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, pr));
    FOwned.add(result);
  end;
end;

function TFHIRValidator.checkExtension(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; def: TFHIRElementDefinition;
  profile: TFHIRStructureDefinition; stack: TNodeStack): TFHIRStructureDefinition;
var
  url : String;
  isModifier : boolean;
  ex : TFHIRStructureDefinition;
begin
  url := element.getAttribute('url');
  isModifier := element.getName() = 'modifierExtension';

  ex := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, url));
  if (ex = nil) then
  begin
    if (not rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path, allowUnknownExtension(url), 'The extension ' + url + ' is unknown, and not allowed here'))
    then
      warning(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path, allowUnknownExtension(url), 'Unknown extension ' + url);
  end
  else
  begin
    FOwned.add(ex);
    if (def.isModifier) then
      rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path + '[url:="' + url + '"]', ex.Snapshot.ElementList[0].isModifier,
        'Extension modifier mismatch: the extension element is labelled as a modifier, but the underlying extension is not')
    else
      rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path + '[url:="' + url + '"]', not ex.Snapshot.ElementList[0].isModifier,
        'Extension modifier mismatch: the extension element is not labelled as a modifier, but the underlying extension is');

    // two questions
    // 1. can this extension be used here?
    checkExtensionContext(errors, element, { path+'[url:="'+url+'"]', } ex, stack, ex.url);

    if (isModifier) then
      rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path + '[url:="' + url + '"]', ex.Snapshot.ElementList[0].isModifier,
        'The Extension "' + url + '" must be used as a modifierExtension')
    else
      rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), path + '[url:="' + url + '"]', not ex.Snapshot.ElementList[0].isModifier,
        'The Extension "' + url + '" must not be used as an extension (it"s a modifierExtension)');

    // 2. is the content of the extension valid?

  end;
  result := ex;
end;

function TFHIRValidator.allowUnknownExtension(url: String): boolean;
var
  s : String;
begin
  result := FIsAnyExtensionsAllowed;
  if (url.contains('example.org')) or (url.contains('acme.com')) or (url.contains('nema.org')) then
    result := true
  else if FExtensionDomains <> nil then
    for s in FExtensionDomains do
      if (url.startsWith(s)) then
        result := true;
end;

// function TFHIRValidator.isKnownType(code : String) : boolean;
//begin
//  result := TFHIRStructureDefinition(Fcontext.fetchResource(frtStructureDefinition, code.toLower)) <> nil;
//end;

// function TFHIRValidator.getElementByPath(definition : TFHIRStructureDefinition; path : String) : TFHIRElementDefinition;
//var
//  e : TFHIRElementDefinition;
//begin
//  for e in definition.SnapShot.ElementList do
//   begin
//    if (e.Path = path) then
//    begin
//      result := e;
//      exit;
//    end;
//  end;
//  result := nil;
//end;

function TFHIRValidator.checkExtensionContext(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; definition: TFHIRStructureDefinition; stack: TNodeStack;
  extensionParent: String): boolean;
var
  extUrl : String;
  b, c, p, lp, pe: String;
  ct : TFhirString;
  ok : boolean;
begin
  extUrl := definition.url;
  if definition.snapshot.elementList[0].isModifier then
    pe := '.modifierExtension'
  else
    pe := '.extension';

  p := '';
  for lp in stack.logicalPaths do
  begin
    if p <> '' then
      p := p + ', ';
    p := p + lp;
  end;
  if (definition.ContextType = ExtensionContextDATATYPE) then
  begin
    ok := false;
    b := '';
    for ct in definition.contextList do
    begin
      if b <> '' then
        b := b + ', ';
      if ct.value = '*' then
        b := b + ct.value
      else
        b := b + ct.value+pe;
      if (ct.value = '*') or (stack.logicalPaths.indexOf(ct.value + pe) > -1) then
        ok := true;
    end;
    result := rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), stack.literalPath, ok,
      'The extension ' + extUrl + ' is not allowed to be used on the logical path set [' + p + '] (allowed: datatype:=' + b + ')');
  end
  else if (definition.ContextType = ExtensionContextEXTENSION) then
  begin
    ok := false;
    for ct in definition.contextList do
      if (ct.value = '*') or (ct.value = extensionParent) then
        ok := true;
    result := rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), stack.literalPath, ok,
      'The extension ' + extUrl + ' is not allowed to be used with the extension "' + extensionParent + '"');
  end
  else if (definition.ContextType = ExtensionContextMAPPING) then
  begin
    raise Exception.Create('Not handled yet (extensionContext)');
  end
  else if (definition.ContextType = ExtensionContextRESOURCE) then
  begin
    ok := false;
//      String simplePath := container.Path;
//      System.out.println(simplePath);
//      if (effetive.endsWith('.extension') ) or ( simplePath.endsWith('.modifierExtension'))
//        simplePath := simplePath.substring(0, simplePath.lastIndexOf("."));
    b := '';
    for ct in definition.contextList do
    begin
      if b <> '' then
        b := b + ', ';
      if ct.value = '*' then
        b := b + ct.value
      else
        b := b + ct.value+pe;
      c := ct.value;
      if (c = '*') or (stack.logicalPaths.indexOf(c + pe) >= 0) or (c.startsWith('@') and (stack.logicalPaths.indexOf(c.substring(1) + pe) >= 0)) then
          ok := true;
    end;
    result := rule(errors, IssueTypeSTRUCTURE, element.locStart(), element.locEnd(), stack.literalPath, ok,
      'The extension ' + extUrl + ' is not allowed to be used on the logical path set ' + p + ' (allowed: resource:=' + b + ')');
  end
  else
    raise Exception.Create('Unknown context type');
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
//    if (i >= parts.length)
//      raise Exception.create('Unable to process part '+path);
//    int j := parts.length - 1;
//    while (j > 0 ) and ( (parts[j] = 'extension') ) or ( parts[j] = 'modifierExtension')))
//        j--;
//    StringBuilder b := new StringBuilder();
//    boolean first := true;
//    for (int k := i; k <= j; k++) begin
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

function empty(element : TWrapperElement) : boolean;
var
  child : TWrapperElement;
begin
  if (element.hasAttribute('value')) then
    result := false
  else if (element.hasAttribute('xml:id')) then
    result := false
  else
  begin
    child := element.getFirstChild();
    while (child <> nil) do
    begin
      if (not child.isXml()) or (FHIR_NS = child.getNamespace())then
      begin
        result := false;
        exit;
      end;
      child := child.getNextSibling();
    end;
    result := true;
  end;
end;

function TFHIRValidator.findElement(profile: TFHIRStructureDefinition; name: String): TFHIRElementDefinition;
var
  c : TFHIRElementDefinition;
begin
  result := nil;
  for c in profile.Snapshot.ElementList do
  begin
    if (c.path = name) then
    begin
      result := c;
      exit;
    end;
  end;
end;



procedure TFHIRValidator.validateContains(errors: TFhirOperationOutcomeIssueList; path: String; child: TFHIRElementDefinition; context: TFHIRElementDefinition; resource, element: TWrapperElement; stack: TNodeStack; idRule: TResourceIdStatus);
var
  e : TWrapperElement;
  resourceName : String;
  profile : TFHIRStructureDefinition;
begin
  if element.isXml() then
    e := element.getFirstChild()
  else
    e := element;
  resourceName := e.getResourceType();
  profile := TFHIRStructureDefinition(FContext.fetchResource(frtStructureDefinition, 'http://hl7.org/fhir/StructureDefinition/' + resourceName));
  FOwned.add(profile);
  if (rule(errors, IssueTypeINVALID, element.locStart(), element.locEnd(), stack.addToLiteralPath(resourceName), profile <> nil,
    'No profile found for contained resource of type "' + resourceName + '"')) then
    validateResource(errors, resource, e, profile, idRule, stack);
end;


function passesCodeWhitespaceRules(v : String) : boolean;
var
  lastWasSpace : boolean;
  c : char;
begin
  if (v.trim() <> v) then
    result := false
  else
  begin
    lastWasSpace := true;
    for c in v do
    begin
     if (c = ' ') then
     begin
      if (lastWasSpace) then
      begin
        result := false;
        exit;
      end
      else
        lastWasSpace := true;
    end
      else if c.isWhitespace then
    begin
      result := false;
      exit;
    end
    else
      lastWasSpace := false;
    end;
  end;
  result := true;
end;

function yearIsValid(v : String) : boolean;
var
  i : integer;
begin
  if (v = '') then
    result := false
  else
  begin
    i := StrToIntDef(v.substring(0, Integermin(4, v.length)), 0);
    result := (i >= 1800) and ( i <= 2100);
  end;
end;

function hasTimeZone(fmt : String) : boolean;
begin
  result := (fmt.length > 10) and (fmt.substring(10).contains('-') or fmt.substring(10).contains('+') or fmt.substring(10).contains('Z'));
end;

function hasTime(fmt : String) : boolean;
begin
  result := fmt.contains('T');
end;

procedure TFHIRValidator.checkPrimitive(errors: TFhirOperationOutcomeIssueList; path: String; ty: String; context: TFHIRElementDefinition; e: TWrapperElement; profile : TFhirStructureDefinition);
var
  regex : TRegExpr;
begin
  if (ty = 'xhtml') then
    exit;

  if (ty = 'uri') then
  begin
    rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, not e.getAttribute('value').startsWith('oid:'), 'URI values cannot start with oid:');
    rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, not e.getAttribute('value').startsWith('uuid:'), 'URI values cannot start with uuid:');
    rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, e.getAttribute('value') = e.getAttribute('value').trim(), 'URI values cannot have leading or trailing whitespace');
  end;
  if (not SameText(ty, 'string') ) and (e.hasAttribute('value')) then
  begin
    if (rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, e.getAttribute('value').length > 0, '@value cannot be empty')) then
      warning(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, e.getAttribute('value').trim() = e.getAttribute('value'), 'value should not start or finish with whitespace');
  end;
  if (ty = 'dateTime') then
  begin
    rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, yearIsValid(e.getAttribute('value')), 'The value "' + e.getAttribute('value') + '" does not have a valid year');
    regex := TRegExpr.Create;
    try
      regex.Expression :=
        '-?[0-9]{4}(-(0[1-9]|1[0-2])(-(0[0-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)?(Z|(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?)?)?)?';
      rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, regex.Exec(e.getAttribute('value')), 'Not a valid date time');
    finally
      regex.Free;
    end;
    rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, not hasTime(e.getAttribute('value')) or hasTimeZone(e.getAttribute('value')),
      'if a date has a time, it must have a timezone');
  end;
  if (ty = 'instant') then
  begin
    regex := TRegExpr.Create;
    try
      regex.Expression := '-?[0-9]{4}-(0[1-9]|1[0-2])-(0[0-9]|[1-2][0-9]|3[0-1])T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)?(Z|(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))';
      rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, regex.Exec(e.getAttribute('value')), 'The instant "' + e.getAttribute('value') + '" is not valid (by regex)');
    finally
      regex.Free;
    end;
    rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, yearIsValid(e.getAttribute('value')), 'The value "' + e.getAttribute('value') + '" does not have a valid year');
  end;

  if (ty = 'code') then
  begin
    // Technically, a code is restricted to string which has at least one character and no leading or trailing whitespace, and where there is no whitespace other than single spaces in the contents
    rule(errors, IssueTypeINVALID, e.locStart(), e.locEnd(), path, passesCodeWhitespaceRules(e.getAttribute('value')),
      'The code "' + e.getAttribute('value') + '" is not valid (whitespace rules)');
  end;

  if (context.Binding <> nil) then
  begin
    checkPrimitiveBinding(errors, path, ty, context, e, profile);
  end;
  // for nothing to check
end;

// note that we don"t check the type here; it could be string, uri or code.
procedure TFHIRValidator.checkPrimitiveBinding(errors: TFhirOperationOutcomeIssueList; path: String; ty: String; context: TFHIRElementDefinition; element: TWrapperElement; profile : TFhirStructureDefinition);
var
  value : String;
  Binding: TFhirElementDefinitionBinding;
  vs : TFHIRValueSet;
  res: TValidationResult;
begin
  if (not element.hasAttribute('value')) then
    exit;
  value := element.getAttribute('value');

//    System.out.println('check '+value+' in '+path);

  // firstly, resolve the value set
  Binding := context.Binding;
  if (Binding.ValueSet <> nil) and (Binding.ValueSet is TFHIRReference) then
  begin
    vs := resolveBindingReference(profile, Binding.ValueSet);
    if (warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, vs <> nil, 'ValueSet ' + describeReference(Binding.ValueSet) + ' not found')) then
    begin
      res := FContext.validateCode(SYSTEM_NOT_APPLICABLE, value, '', vs);
      try
      if (not res.isOk()) then
        begin
        if (Binding.Strength = BindingStrengthREQUIRED) then
            warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'The value provided ('+value+') is not in the value set ' +
            describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code is required from this value set')
        else if (Binding.Strength = BindingStrengthEXTENSIBLE) then
            warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'The value provided ('+value+') is not in the value set ' +
            describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code should come from this value set unless it has no suitable code')
        else if (Binding.Strength = BindingStrengthPREFERRED) then
            hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'The value provided ('+value+') is not in the value set ' + describeReference(Binding.ValueSet)
            + ' (' + vs.url + ', and a code is recommended to come from this value set');
      end;
      finally
        res.free;
      end;
    end;
  end
  else
    hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, ty <> 'code', 'Binding has no source, so can''t be checked');
end;

function isValidFHIRUrn(uri : String) : boolean;
begin
  result := (uri = 'urn:x-fhir:uk:id:nhs-number');
end;


function isAbsolute(uri : String) : boolean;
begin
  result := (uri = '') or uri.startsWith('http:') or uri.startsWith('https:') or uri.startsWith('urn:uuid:') or uri.startsWith('urn:oid:') or uri.startsWith('urn:ietf:') or
    uri.startsWith('urn:iso:') or isValidFHIRUrn(uri);
end;

procedure TFHIRValidator.checkIdentifier(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; context: TFHIRElementDefinition);
var
  System: String;
begin
  System := element.getNamedChildValue('system');
  rule(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, isAbsolute(System), 'Identifier.system must be an absolute reference, not a local reference');
end;

procedure TFHIRValidator.checkQuantity(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; context: TFHIRElementDefinition);
var
  code : String;
  System: String;
  units : String;
begin
  code := element.getNamedChildValue('code');
  System := element.getNamedChildValue('system');
  units := element.getNamedChildValue('units');

  if (System <> '') and (code <> '') then
    checkCode(errors, element, path, code, System, units);
end;

procedure TFHIRValidator.checkCoding(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; profile: TFHIRStructureDefinition;
  context: TFHIRElementDefinition; inCodeableConcept: boolean);
var
  code : String;
  System: String;
  display : String;
  Binding: TFhirElementDefinitionBinding;
  vs : TFHIRValueSet;
  c: TFHIRCoding;
  res: TValidationResult;
begin
  code := element.getNamedChildValue('code');
  System := element.getNamedChildValue('system');
  display := element.getNamedChildValue('display');

  rule(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, isAbsolute(System), 'Coding.system must be an absolute reference, not a local reference');

  if (System <> '') and (code <> '') then
  begin
    if (checkCode(errors, element, path, code, System, display)) then
      if (context <> nil ) and ( context.Binding <> nil) then
      begin
        Binding := context.Binding;
        if (warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, Binding <> nil, 'Binding for ' + path + ' missing')) then
        begin
          if (Binding.ValueSet <> nil) then
          begin
            vs := resolveBindingReference(profile, Binding.ValueSet);
            if (warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, vs <> nil, 'ValueSet ' + describeReference(Binding.ValueSet) + ' not found')) then
            begin
              try
                c := readAsCoding(element);
                try
                res := FContext.validateCode(c, vs);
                try
                if (not res.isOk()) then
                  if (Binding.Strength = BindingStrengthREQUIRED) then
                        warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'The value provided ('+c.system+'::'+c.code+') is not in the value set ' +
                      describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code is required from this value set')
                  else if (Binding.Strength = BindingStrengthEXTENSIBLE) then
                        warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'The value provided ('+c.system+'::'+c.code+') is not in the value set ' +
                      describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code should come from this value set unless it has no suitable code')
                  else if (Binding.Strength = BindingStrengthPREFERRED) then
                        hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'The value provided ('+c.system+'::'+c.code+') is not in the value set ' +
                      describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code is recommended to come from this value set');
                finally
                  res.free;
                end;
                finally
                  c.Free;
                end;
              except
                on e: Exception do
                  warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'Error ' + e.message + ' validating Coding');
            end;
          end
            else if (Binding.ValueSet <> nil) then
              hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'Binding by URI TFHIRReference cannot be checked')
            else if not inCodeableConcept then
              hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'Binding has no source, so can''t be checked');
          end;
        end;
      end;
  end;
end;

function TFHIRValidator.resolveBindingReference(context : TFHIRDomainResource; reference: TFHIRType): TFHIRValueSet;
var
  s : String;
  c : TFHIRResource;
begin
  if (reference is TFHIRUri) then
    result := TFHIRValueSet(FContext.fetchResource(frtValueSet, TFHIRUri(reference).value))
  else if (reference is TFHIRReference) then
  begin
    s := TFHIRReference(reference).reference;
    if s.StartsWith('#') then
    begin
      for c in context.containedList do
        if (c.id = s.Substring(1)) and (c is TFHIRValueSet) then
          exit(TFHIRValueSet(c).link);
      result := nil;
    end
    else
      result := TFHIRValueSet(FContext.fetchResource(frtValueSet, s))
  end
  else
    result := nil;
  FOwned.add(result);
end;

function readAsCodeableConcept(element: TWrapperElement): TFHIRCodeableConcept;
var
  cc: TFHIRCodeableConcept;
  list: TAdvList<TWrapperElement>;
  item: TWrapperElement;
begin
  cc := TFHIRCodeableConcept.Create;
  list := TAdvList<TWrapperElement>.Create;
  try
    element.getNamedChildren('coding', list);
    for item in list do
      cc.CodingList.Add(readAsCoding(item));
    cc.text := element.getNamedChildValue('text');
    result := cc.Link;
  finally
    cc.Free;
    list.Free;
  end;
End;

procedure TFHIRValidator.checkCodeableConcept(errors: TFhirOperationOutcomeIssueList; path: String; element: TWrapperElement; profile: TFHIRStructureDefinition;
  context: TFHIRElementDefinition);
var
  Binding: TFhirElementDefinitionBinding;
  vs : TFHIRValueSet;
  res : TValidationResult;
  cc: TFHIRCodeableConcept;
begin
  if (context <> nil ) and ( context.Binding <> nil) then
  begin
    Binding := context.Binding;
    if (warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, Binding <> nil, 'Binding for ' + path + ' missing (cc)')) then
    begin
      if (Binding.ValueSet <> nil) and (Binding.ValueSet is TFHIRReference) then
      begin
        vs := resolveBindingReference(profile, Binding.ValueSet);
        if (warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, vs <> nil, 'ValueSet ' + describeReference(Binding.ValueSet) + ' not found')) then
        begin
          try
            cc := readAsCodeableConcept(element);
            try
            if (cc.CodingList.IsEmpty) then
                begin
              if (Binding.Strength = BindingStrengthREQUIRED) then
                rule(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'No code provided, and a code is required from the value set ' +
                  describeReference(Binding.ValueSet) + ' (' + vs.url)
              else if (Binding.Strength = BindingStrengthEXTENSIBLE) then
                warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'No code provided, and a code should be provided from the value set ' +
                  describeReference(Binding.ValueSet) + ' (' + vs.url);
            end
            else
              begin
              res := FContext.validateCode(cc, vs);
              try
              if (not res.isOk) then
              begin
                if (Binding.Strength = BindingStrengthREQUIRED) then
                  rule(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'None of the codes provided are in the value set ' +
                    describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code from this value set is required')
                else if (Binding.Strength = BindingStrengthEXTENSIBLE) then
                  warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'None of the codes provided are in the value set ' +
                    describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code should come from this value set unless it has no suitable code')
                else if (Binding.Strength = BindingStrengthPREFERRED) then
                  hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'None of the codes provided are in the value set ' +
                    describeReference(Binding.ValueSet) + ' (' + vs.url + ', and a code is recommended to come from this value set');
              end;
              finally
                res.free;
              end;
            end;
          except
            on e : Exception do
              warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'Error ' + e.message + ' validating CodeableConcept');
          end;
          finally
            cc.free;
          end;
        end;
      end
  else if (Binding.ValueSet <> nil) then
        hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'Binding by URI rReference cannot be checked')
      else
    hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, false, 'Binding has no source, so can''t be checked');
    end
  end;
    end;

function TFHIRValidator.checkCode(errors: TFhirOperationOutcomeIssueList; element: TWrapperElement; path: String; code, System, display: String): boolean;
var
  s : TValidationResult;
begin
  result := true;
  if (FContext.supportsSystem(System)) then
  begin
    s := FContext.validateCode(System, code, display);
    try
    if (s = nil ) or (s.isOk()) then
      result := true
      else if (s.severity = IssueSeverityInformation) then
        hint(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, s = nil, s.message)
      else if (s.severity = IssueSeverityWarning) then
        warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, s = nil, s.message)
    else
        result := rule(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, s = nil, s.message);
    finally
      s.Free;
    end;
  end
//  else if (system.startsWith('http://hl7.org/fhir')) then
//  begin
//    if (system = 'http://hl7.org/fhir/sid/icd-10') then
//      result := true // else don"t check ICD-10 (for now)
//    else
//    begin
//      vs := FContext.fetchCodeSystem(system);
//      if (vs <> nil) then
//        check vlaue set uri hasn't been used directly
  // if (warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, vs <> nil, 'Unknown Code System '+system))
//      else begin
//        ConceptDefinitionComponent def := getCodeDefinition(vs, code);
  // if (warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, def <> nil, 'Unknown Code ('+system+'#'+code+')'))
  // return warning(errors, IssueTypeCODEINVALID, element.locStart(), element.locEnd(), path, display = nil ) or ( display = def.getDisplay()), 'Display should be "'+def.getDisplay()+'"');
//      end;
//      return false;
//    end;
//  end; else if (system.startsWith('http://loinc.org')) begin
//    return true;
//  end; else if (system.startsWith('http://unitsofmeasure.org')) begin
//    return true;
//  end;
  else
    result := true;
end;

//function getCodeDefinition(c TFHIRConceptDefinition; code : String ) : TFHIRConceptDefinition;
//begin
//  if (code = c.Code))
//    return c;
//  for (ConceptDefinitionComponent g : c.getConcept()) begin
//    ConceptDefinitionComponent r := getCodeDefinition(g, code);
//    if (r <> nil)
//      return r;
//  end;
//  return nil;
//end;
//
//private ConceptDefinitionComponent getCodeDefinition(vs : TFHIRValueSet, String code) begin
//  for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) begin
//    ConceptDefinitionComponent r := getCodeDefinition(c, code);
//    if (r <> nil)
//      return r;
//  end;
//  return nil;
//end;



//public class ProfileStructureIterator begin
//
//  private profile : TFHIRStructureDefinition;
//  private TFHIRElementDefinition elementDefn;
//  private List<String> names := new ArrayList<String>();
//  private Map<String, List<TFHIRElementDefinition>> children := new HashMap<String, List<TFHIRElementDefinition>>();
//  private int cursor;
//
//  public ProfileStructureIterator(profile : TFHIRStructureDefinition, TFHIRElementDefinition elementDefn) begin
//    this.profile := profile;
//    this.elementDefn := elementDefn;
//    loadMap();
//    cursor := -1;
//  end;
//
// procedure TFHIRValidator.loadMap() begin
//    int i := profile.SnapShot.getElement().indexOf(elementDefn) + 1;
//    String lead := elementDefn.Path;
//    while (i < profile.SnapShot.getElement().Count) begin
//      String name := profile.Snapshot.ElementList[i).Path;
//      if (name.length() <= lead.length())
//        return; // cause we"ve got to the end of the possible matches
//      String tail := name.substring(lead.length()+1);
//      if (Utilities.isToken(tail) ) and ( name.substring(0, lead.length()) = lead)) begin
//        List<TFHIRElementDefinition> list := children[tail);
//        if (list = nil) begin
//          list := new ArrayList<TFHIRElementDefinition>();
//          names.add(tail);
//          children.put(tail, list);
//        end;
//        list.add(profile.Snapshot.ElementList[i));
//      end;
//      i++;
//    end;
//  end;
//
//  public boolean more() begin
//    cursor++;
//    return cursor < names.Count;
//  end;
//
//  public List<TFHIRElementDefinition> current() begin
//    return children[name());
//  end;
//
//  public String name() begin
//    return names[cursor);
//  end;
//
//end;
//
// procedure TFHIRValidator.checkByProfile(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement, profile : TFHIRStructureDefinition, TFHIRElementDefinition elementDefn) ; begin
//  // we have an element, and the structure that describes it.
//  // we know that"s it"s valid against the underlying spec - is it valid against this one?
//  // in the instance validator above, we assume that schema or schmeatron has taken care of cardinalities, but here, we have no such reliance.
//  // so the walking algorithm is different: we"re going to walk the definitions
//  String type;
//  if (elementDefn.Path.endsWith('[x]')) begin
//    String tail := elementDefn.Path.substring(elementDefn.Path.lastIndexOf('.')+1, elementDefn.Path.length()-3);
//    type := focus.getName().substring(tail.length());
// rule(errors, IssueTypeSTRUCTURE, focus.locStart(), focus.locEnd(), path, typeAllowed(type, elementDefn.Type_List), 'The type "'+type+'" is not allowed at this point (must be one of "'+typeSummary(elementDefn)+')');
//  end; else begin
//    if (elementDefn.Type_List.Count = 1) begin
//      type := elementDefn.Type_List.Count = 0 ? nil : elementDefn.Type_List[0].Code;
//    end; else
//      type := nil;
//  end;
//  // constraints:
//  for (ElementDefinitionConstraintComponent c : elementDefn.getConstraint())
//    checkConstraint(errors, path, focus, c);
//  if (elementDefn.Binding <> nil ) and ( type <> nil)
//    checkBinding(errors, path, focus, profile, elementDefn, type);
//
//  // type specific checking:
//  if (type <> nil ) and ( typeIsPrimitive(type)) begin
//    checkPrimitiveByProfile(errors, path, focus, elementDefn);
//  end; else begin
//    if (elementDefn.hasFixed())
//      checkFixedValue(errors, path, focus, elementDefn.getFixed(), '');
//
//    ProfileStructureIterator walker := new ProfileStructureIterator(profile, elementDefn);
//    while (walker.more()) begin
//      // collect all the slices for the path
//      List<TFHIRElementDefinition> childset := walker.current();
//      // collect all the elements that match it by name
//      TAdvList<TWrapperElement> children := TAdvList<TWrapperElement>.create();
//      focus.getNamedChildrenWithWildcard(walker.name(), children);
//
//      if (children.Count = 0) begin
//        // well, there"s no children - should there be?
//        for (TFHIRElementDefinition defn : childset) begin
// if (not rule(errors, IssueTypeREQUIRED, focus.locStart(), focus.locEnd(), path, defn.getMin() = 0, 'Required Element "'+walker.name()+'" missing'))
//            break; // no point complaining about missing ones after the first one
//        end;
//      end; else if (childset.Count = 1) begin
//        // simple case: one possible definition, and one or more children.
// rule(errors, IssueTypeSTRUCTURE, focus.locStart(), focus.locEnd(), path, childset[0).Max = '*') ) or ( StrToInt(childset[0).Max) >= children.Count,
//            'Too many elements for "'+walker.name()+'"'); // todo: sort out structure
//        for (TWrapperElement child : children) begin
//          checkByProfile(errors, childset[0).Path, child, profile, childset[0));
//        end;
//      end; else begin
//        // ok, this is the full case - we have a list of definitions, and a list of candidates for meeting those definitions.
//        // we need to decide *if* that match a given definition
//      end;
//    end;
//  end;
//end;
// procedure TFHIRValidator.checkBinding(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement, profile : TFHIRStructureDefinition, TFHIRElementDefinition elementDefn, String type) throws EOperationOutcome, Exception begin
//  ElementDefinitionBindingComponent bc := elementDefn.Binding;
//
//  if (bc <> nil ) and ( bc.ValueSet() <> nil ) and ( bc.ValueSet is TFHIRReference) begin
//    String url := ((TFHIRReference) bc.ValueSet).getReference();
//    vs : TFHIRValueSet := resolveValueSetReference(profile, (TFHIRReference) bc.ValueSet);
//    if (vs = nil) begin
// rule(errors, IssueTypeSTRUCTURE, focus.locStart(), focus.locEnd(), path, false, 'Cannot check binding on type "'+type+'" as the value set "'+url+'" could not be located');
//    end; else if (ty = 'code'))
//      checkBindingCode(errors, path, focus, vs);
//    else if (ty = 'Coding'))
//      checkBindingCoding(errors, path, focus, vs);
//    else if (ty = 'CodeableConcept'))
//      checkBindingCodeableConcept(errors, path, focus, vs);
//    else
// rule(errors, IssueTypeSTRUCTURE, focus.locStart(), focus.locEnd(), path, false, 'Cannot check binding on type "'+type+'"');
//  end;
//end;
//
//private ValueSet resolveValueSetReference(profile : TFHIRStructureDefinition, TFHIRReference TFHIRReference) throws EOperationOutcome, Exception begin
//  if (TFHIRReference.getReference().startsWith('#')) begin
//    for (Resource r : profile.getContained()) begin
//      if (r is ValueSet ) and ( r.getId() = TFHIRReference.getReference().substring(1)))
//        return (ValueSet) r;
//    end;
//    return nil;
//  end; else
//    return resolveBindingReference(TFHIRReference);
//
//end;

// procedure TFHIRValidator.checkBindingCode(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement, vs : TFHIRValueSet) begin
//  // rule(errors, 'exception', path, false, 'checkBindingCode not done yet');
//end;
//
// procedure TFHIRValidator.checkBindingCoding(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement, vs : TFHIRValueSet) begin
//  // rule(errors, 'exception', path, false, 'checkBindingCoding not done yet');
//end;
//
// procedure TFHIRValidator.checkBindingCodeableConcept(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement, vs : TFHIRValueSet) begin
//  // rule(errors, 'exception', path, false, 'checkBindingCodeableConcept not done yet');
//end;
//
//private String typeSummary(TFHIRElementDefinition elementDefn) begin
//  StringBuilder b := new StringBuilder();
//  for (TypeRefComponent t : elementDefn.Type_List) begin
//    b.append('"'+t.Code);
//  end;
//  return b.toString().substring(1);
//end;
//
//private boolean typeAllowed(String t, List<TypeRefComponent> types) begin
//  for (TypeRefComponent type : types) begin
//    if (t = Utilities.capitalize(ty.Code)))
//      return true;
//    if (t = 'Resource') ) and ( Utilities.capitalize(ty.Code) = 'TFHIRReference'))
//      return true;
//  end;
//  return false;
//end;
//
// procedure TFHIRValidator.checkConstraint(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement, ElementDefinitionConstraintComponent c) ; begin

//		try
//   	begin
//			XPathFactory xpf := new net.sf.saxon.xpath.XPathFactoryImpl();
//      NamespaceContext context := new NamespaceContextMap('f', 'http://hl7.org/fhir', 'h', 'http://www.w3.org/1999/xhtml');
//
//			XPath xpath := xpf.newXPath();
//      xpath.setNamespaceContext(context);
//   		ok : boolean := (Boolean) xpath.evaluate(c.getXpath(), focus, XPathConstants.BOOLEAN);
//   		if (ok = nil ) or ( not ok) begin
//   			if (c.getSeverity() = ConstraintSeverity.warning)
//   				warning(errors, 'invariant', path, false, c.getHuman());
//   			else
//   				rule(errors, 'invariant', path, false, c.getHuman());
//   		end;
//		end;
//		catch (XPathExpressionException e) begin
//		  rule(errors, 'invariant', path, false, 'error executing invariant: '+e.Message);
//		end;
//end;
//
// procedure TFHIRValidator.checkPrimitiveByProfile(errors : TFhirOperationOutcomeIssueList; path : String; focus : TWrapperElement, TFHIRElementDefinition elementDefn) begin
//  // two things to check - length, and fixed value
//  String value := focus.getAttribute('value');
//  if (elementDefn.hasMaxLengthElement()) begin
// rule(errors, IssueTypeTOOLONG, focus.locStart(), focus.locEnd(), path, value.length() <= elementDefn.getMaxLength(), 'The value "'+value+'" exceeds the allow length limit of '+inttostr(elementDefn.getMaxLength()));
//  end;
//  if (elementDefn.hasFixed()) begin
//    checkFixedValue(errors, path, focus, elementDefn.getFixed(), '');
//  end;
//end;
//

procedure TFHIRValidator.checkFixedValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFhirElement; propName: String);
var
  value : String;
  extensions : TAdvList<TWrapperElement>;
  e : TFhirExtension;
  ex : TWrapperElement;
begin
  if (fixed = nil ) and ( focus = nil) then
    exit; // this is all good

  if (fixed = nil) and (focus <> nil) then
    rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, false, 'Unexpected element ' + focus.getName())
  else if (fixed <> nil ) and ( focus = nil) then
    rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, false, 'Mising element ' + propName)
  else
  begin
    value := focus.getAttribute('value');
    if (fixed is TFHIRBoolean) then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRBoolean(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRBoolean(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRInteger)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRInteger(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRInteger(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRDecimal)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRDecimal(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRDecimal(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRBase64Binary)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRBase64Binary(fixed).StringValue = value,
        'Value is "' + value + '" but must be "' + TFHIRBase64Binary(fixed).StringValue + '"')
    else if (fixed is TFHIRInstant)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRInstant(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRInstant(fixed)
        .StringValue + '"')
    else if (fixed is TFhirString) then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFhirString(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFhirString(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRUri) then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRUri(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRUri(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRDate)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRDate(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRDate(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRDateTime)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRDateTime(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRDateTime(fixed)
        .StringValue + '"')
    else if (fixed is TFHIROid)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIROid(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIROid(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRUuid)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRUuid(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRUuid(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRCode)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRCode(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRCode(fixed)
        .StringValue + '"')
    else if (fixed is TFHIRId)  then
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, TFHIRId(fixed).StringValue = value, 'Value is "' + value + '" but must be "' + TFHIRId(fixed).StringValue + '"')
    else if (fixed is TFHIRQuantity) then
      checkQuantityValue(errors, path, focus, TFHIRQuantity(fixed))
    else if (fixed is TFHIRAddress) then
      checkAddressValue(errors, path, focus, TFHIRAddress(fixed))
    else if (fixed is TFHIRContactPoint) then
      checkContactPointValue(errors, path, focus, TFHIRContactPoint(fixed))
    else if (fixed is TFHIRAttachment) then
      checkAttachmentValue(errors, path, focus, TFHIRAttachment(fixed))
    else if (fixed is TFHIRIdentifier) then
      checkIdentifierValue(errors, path, focus, TFHIRIdentifier(fixed))
    else if (fixed is TFHIRCoding) then
      checkCodingValue(errors, path, focus, TFHIRCoding(fixed))
    else if (fixed is TFHIRHumanName) then
      checkHumanNameValue(errors, path, focus, TFHIRHumanName(fixed))
    else if (fixed is TFHIRCodeableConcept) then
      checkCodeableConceptValue(errors, path, focus, TFHIRCodeableConcept(fixed))
    else if (fixed is TFHIRTiming) then
      checkTimingValue(errors, path, focus, TFHIRTiming(fixed))
    else if (fixed is TFHIRPeriod) then
      checkPeriodValue(errors, path, focus, TFHIRPeriod(fixed))
    else if (fixed is TFHIRRange) then
      checkRangeValue(errors, path, focus, TFHIRRange(fixed))
    else if (fixed is TFHIRRatio) then
      checkRatioValue(errors, path, focus, TFHIRRatio(fixed))
    else if (fixed is TFHIRSampledData) then
      checkSampledDataValue(errors, path, focus, TFHIRSampledData(fixed))
    else
      rule(errors, IssueTypeException, focus.locStart(), focus.locEnd(), path, false, 'Unhandled fixed value type ' + fixed.ClassName);
    extensions := TAdvList<TWrapperElement>.Create();
    try
    focus.getNamedChildren('extension', extensions);
    if (fixed.extensionList.count = 0) then
    begin
      rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, extensions.count = 0, 'No extensions allowed');
    end
    else if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, extensions.count = fixed.extensionList.count,
      'Extensions count mismatch: expected ' + inttostr(fixed.extensionList.count) + ' but found ' + inttostr(extensions.count))) then
    begin
      for e in fixed.extensionList do
      begin
        ex := getExtensionByUrl(extensions, e.url);
        if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, ex <> nil, 'Extension count mismatch: unable to find extension: ' + e.url)) then
          checkFixedValue(errors, path, ex.getFirstChild().getNextSibling(), e.value, 'extension.value');
      end;
    end;
    finally
      extensions.free;
    end;
  end;
end;

procedure TFHIRValidator.checkAddressValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRAddress);
var
  lines : TAdvList<TWrapperElement>;
  i : integer;
begin
  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.UseElement, 'use');
  checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.TextElement, 'text');
  checkFixedValue(errors, path+'.city', focus.getNamedChild('city'), fixed.CityElement, 'city');
  checkFixedValue(errors, path+'.state', focus.getNamedChild('state'), fixed.StateElement, 'state');
  checkFixedValue(errors, path+'.country', focus.getNamedChild('country'), fixed.CountryElement, 'country');
  checkFixedValue(errors, path+'.zip', focus.getNamedChild('zip'), fixed.PostalCodeElement, 'postalCode');

  lines := TAdvList<TWrapperElement>.Create();
  try
  focus.getNamedChildren( 'line', lines);
  if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, lines.count = fixed.lineList.count, 'Expected ' + inttostr(fixed.lineList.count) + ' but found ' +
    inttostr(lines.count) + ' line elements')) then
  begin
    for i := 0 to lines.count - 1 do
      checkFixedValue(errors, path + '.coding', lines[i], fixed.lineList[i], 'coding');
  end;
  finally
    lines.free;
  end;
end;

procedure TFHIRValidator.checkContactPointValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRContactPoint);
begin
  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.SystemElement, 'system');
  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.ValueElement, 'value');
  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.UseElement, 'use');
  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.Period, 'period');
end;

procedure TFHIRValidator.checkAttachmentValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRAttachment);
begin
  checkFixedValue(errors, path+'.contentType', focus.getNamedChild('contentType'), fixed.ContentTypeElement, 'contentType');
  checkFixedValue(errors, path+'.language', focus.getNamedChild('language'), fixed.LanguageElement, 'language');
  checkFixedValue(errors, path+'.data', focus.getNamedChild('data'), fixed.DataElement, 'data');
  checkFixedValue(errors, path+'.url', focus.getNamedChild('url'), fixed.UrlElement, 'url');
  checkFixedValue(errors, path+'.size', focus.getNamedChild('size'), fixed.SizeElement, 'size');
  checkFixedValue(errors, path+'.hash', focus.getNamedChild('hash'), fixed.HashElement, 'hash');
  checkFixedValue(errors, path+'.title', focus.getNamedChild('title'), fixed.TitleElement, 'title');
end;

procedure TFHIRValidator.checkIdentifierValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRIdentifier);
begin
  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.UseElement, 'use');
  checkFixedValue(errors, path+'.label', focus.getNamedChild('type'), fixed.type_, 'type');
  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.SystemElement, 'system');
  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.ValueElement, 'value');
  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.Period, 'period');
  checkFixedValue(errors, path+'.assigner', focus.getNamedChild('assigner'), fixed.Assigner, 'assigner');
end;

procedure TFHIRValidator.checkInvariants(errors: TFhirOperationOutcomeIssueList; path : String; profile: TFHIRStructureDefinition; ed: TFhirElementDefinition; typename, typeProfile : String; resource, element: TWrapperElement);
var
  inv : TFhirElementDefinitionConstraint;
  ok : boolean;
  res : TFHIRResourceOnWrapper;
  e : TFHIRBaseOnWrapper;
begin
  for inv in ed.constraintList do
    if inv.hasExtension('http://hl7.org/fhir/StructureDefinition/structuredefinition-expression') then
    begin
      res := TFHIRResourceOnWrapper.create(FContext.link, resource.Link, resource.profile.Link);
      e := TFHIRBaseOnWrapper.create(FContext.link, element.Link, profile.Link, ed.Link, typename, typeProfile);
      try
        ok := FPathEngine.evaluateToBoolean(nil, res, e, inv.getExtensionString('http://hl7.org/fhir/StructureDefinition/structuredefinition-expression'));
      finally
        e.free;
        res.free;
      end;
      if not ok then
        case inv.severity of
          ConstraintSeverityError: rule(errors, IssueTypeInvariant, element.LocStart, element.LocEnd, path, ok, inv.human+FPathEngine.UseLog);
          ConstraintSeverityWarning: warning(errors, IssueTypeInvariant, element.LocStart, element.LocEnd, path, ok, inv.human+FPathEngine.UseLog);
        end;
    end;
end;

procedure TFHIRValidator.checkCodingValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRCoding);
begin
  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.SystemElement, 'system');
  checkFixedValue(errors, path+'.code', focus.getNamedChild('code'), fixed.CodeElement, 'code');
  checkFixedValue(errors, path+'.display', focus.getNamedChild('display'), fixed.DisplayElement, 'display');
  checkFixedValue(errors, path+'.userSelected', focus.getNamedChild('userSelected'), fixed.UserSelectedElement, 'userSelected');
end;

procedure TFHIRValidator.checkHumanNameValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRHumanName);
var
  parts : TAdvList<TWrapperElement>;
  i : integer;
begin
  checkFixedValue(errors, path+'.use', focus.getNamedChild('use'), fixed.UseElement, 'use');
  checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.TextElement, 'text');
  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.Period, 'period');

  parts := TAdvList<TWrapperElement>.Create();
  try
  focus.getNamedChildren( 'family', parts);
  if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, parts.count = fixed.familyList.count, 'Expected ' + inttostr(fixed.familyList.count) + ' but found ' +
    inttostr(parts.count) + ' family elements')) then
  begin
    for i := 0 to parts.count - 1 do
      checkFixedValue(errors, path+'.family', parts[i], fixed.familyList[i], 'family');
  end;
  focus.getNamedChildren( 'given', parts);
  if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, parts.count = fixed.GivenList.count, 'Expected ' + inttostr(fixed.GivenList.count) + ' but found ' +
    inttostr(parts.count) + ' given elements')) then
  begin
    for i := 0 to parts.count - 1 do
      checkFixedValue(errors, path+'.given', parts[i], fixed.GivenList[i], 'given');
  end;
  focus.getNamedChildren( 'prefix', parts);
  if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, parts.count = fixed.prefixList.count, 'Expected ' + inttostr(fixed.prefixList.count) + ' but found ' +
    inttostr(parts.count) + ' prefix elements')) then
  begin
    for i := 0 to parts.count - 1 do
      checkFixedValue(errors, path+'.prefix', parts[i], fixed.prefixList[i], 'prefix');
  end;
  focus.getNamedChildren( 'suffix', parts);
  if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, parts.count = fixed.suffixList.count, 'Expected ' + inttostr(fixed.suffixList.count) + ' but found ' +
    inttostr(parts.count) + ' suffix elements')) then
  begin
    for i := 0 to parts.count - 1 do
      checkFixedValue(errors, path+'.suffix', parts[i], fixed.suffixList[i], 'suffix');
  end;
  finally
    parts.free;
  end;
end;

procedure TFHIRValidator.checkCodeableConceptValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRCodeableConcept);
var
  codings : TAdvList<TWrapperElement>;
  i : integer;
begin
  checkFixedValue(errors, path+'.text', focus.getNamedChild('text'), fixed.TextElement, 'text');
  codings := TAdvList<TWrapperElement>.Create();
  try
  focus.getNamedChildren( 'coding', codings);
  if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, codings.count = fixed.CodingList.count, 'Expected ' + inttostr(fixed.CodingList.count) + ' but found ' +
    inttostr(codings.count) + ' coding elements')) then
  begin
    for i := 0 to codings.count - 1 do
      checkFixedValue(errors, path + '.coding', codings[i], fixed.CodingList[i], 'coding');
  end;
  finally
    codings.free;
  end;
end;

procedure TFHIRValidator.checkTimingValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRTiming);
var
  events : TAdvList<TWrapperElement>;
  i : integer;
begin
  checkFixedValue(errors, path+'.repeat', focus.getNamedChild('repeat'), fixed.repeat_, 'value');

  events := TAdvList<TWrapperElement>.Create();
  try
  focus.getNamedChildren( 'event', events);
  if (rule(errors, IssueTypeVALUE, focus.locStart(), focus.locEnd(), path, events.count = fixed.eventList.count, 'Expected ' + inttostr(fixed.eventList.count) + ' but found ' +
    inttostr(events.count) + ' event elements')) then
  begin
    for i := 0 to events.count - 1 do
      checkFixedValue(errors, path + '.event', events[i], fixed.eventList[i], 'event');
  end;
  finally
    events.free;
  end;
end;

procedure TFHIRValidator.checkPeriodValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRPeriod);
begin
  checkFixedValue(errors, path+'.start', focus.getNamedChild('start'), fixed.StartElement, 'start');
  checkFixedValue(errors, path+'.end', focus.getNamedChild('end'), fixed.End_Element, 'end');
end;

procedure TFHIRValidator.checkRangeValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRRange);
begin
  checkFixedValue(errors, path+'.low', focus.getNamedChild('low'), fixed.Low, 'low');
  checkFixedValue(errors, path+'.high', focus.getNamedChild('high'), fixed.High, 'high');
end;

procedure TFHIRValidator.checkRatioValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRRatio);
begin
  checkFixedValue(errors, path+'.numerator', focus.getNamedChild('numerator'), fixed.Numerator, 'numerator');
  checkFixedValue(errors, path+'.denominator', focus.getNamedChild('denominator'), fixed.Denominator, 'denominator');
end;

procedure TFHIRValidator.checkSampledDataValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRSampledData);
begin
  checkFixedValue(errors, path+'.origin', focus.getNamedChild('origin'), fixed.Origin, 'origin');
  checkFixedValue(errors, path+'.period', focus.getNamedChild('period'), fixed.PeriodElement, 'period');
  checkFixedValue(errors, path+'.factor', focus.getNamedChild('factor'), fixed.FactorElement, 'factor');
  checkFixedValue(errors, path+'.lowerLimit', focus.getNamedChild('lowerLimit'), fixed.LowerLimitElement, 'lowerLimit');
  checkFixedValue(errors, path+'.upperLimit', focus.getNamedChild('upperLimit'), fixed.UpperLimitElement, 'upperLimit');
  checkFixedValue(errors, path+'.dimensions', focus.getNamedChild('dimensions'), fixed.DimensionsElement, 'dimensions');
  checkFixedValue(errors, path+'.data', focus.getNamedChild('data'), fixed.DataElement, 'data');
end;

procedure TFHIRValidator.checkQuantityValue(errors: TFhirOperationOutcomeIssueList; path: String; focus: TWrapperElement; fixed: TFHIRQuantity);
begin
  checkFixedValue(errors, path+'.value', focus.getNamedChild('value'), fixed.ValueElement, 'value');
  checkFixedValue(errors, path+'.comparator', focus.getNamedChild('comparator'), fixed.ComparatorElement, 'comparator');
  checkFixedValue(errors, path+'.units', focus.getNamedChild('unit'), fixed.Unit_Element, 'units');
  checkFixedValue(errors, path+'.system', focus.getNamedChild('system'), fixed.SystemElement, 'system');
  checkFixedValue(errors, path+'.code', focus.getNamedChild('code'), fixed.CodeElement, 'code');
end;

function TFHIRValidator.getExtensionByUrl(extensions: TAdvList<TWrapperElement>; url: String): TWrapperElement;
var
  e : TWrapperElement;
begin
  result := nil;
  for e in extensions do
  begin
    if (url = e.getNamedChildValue('url')) then
    begin
      result := e;
    end;
  end;
end;

{ TChildIterator }

constructor TChildIterator.Create(path: String; element: TWrapperElement);
begin
  inherited Create;
  parent := element;
  basePath := path;
end;

destructor TChildIterator.Destroy;
begin
  parent.Free;
  inherited;
end;

function TChildIterator.count: integer;
var
  n : TWrapperElement;
begin
  n := child.getNextSibling();
  if (lastName = child.getName()) or ((n <> nil) and (n.getName() = child.getName())) then
    result := lastCount+1
  else
    result := -1;
end;

function TChildIterator.element: TWrapperElement;
begin
  result := child;
end;

function TChildIterator.name: String;
begin
  result := child.getName();
end;

function TChildIterator.next: boolean;
begin
  if (child = nil) then
  begin
    child := parent.getFirstChild();
    lastCount := 0;
    lastName := '';
  end
  else
  begin
    lastName := child.getName();
    child := child.getNextSibling();
    if (child <> nil) and (child.getName() = lastName) then
      inc(lastCount)
    else
      lastCount := 0;
  end;
  result := child <> nil;
end;

function TChildIterator.path: String;
var
  n: TWrapperElement;
begin
  n := child.getNextSibling();
  if basePath = '' then
    result := name()
  else if (lastName = child.getName()) or (n <> nil) and (n.getName() = child.getName()) then
    result := basePath + '.' + name() + '.item(' + inttostr(lastCount) + ')'
    else
    result := basePath + '.' + name();
end;

function TFHIRValidator.validateXml(source : TAdvBuffer; outcome : TFHIROperationOutcome) : boolean;
var
  mem : TAdvMemoryStream;
  vcl : TVCLStream;
  dom : IXMLDomDocument2;
  LAdapter : Variant;
  s, e : TSourceLocation;
begin
  if FCache = nil then
    loadSchema;

  dom := LoadMsXmlDom;
            mem := TAdvMemoryStream.create;
            try
    mem.Buffer := source.Link;
              vcl := TVCLStream.create;
              try
      vcl.Stream := mem.Link;
      dom.preserveWhiteSpace := True;
      dom.setProperty('NewParser', True);
      dom.schemas := FCache;
      dom.validateOnParse := true;
      LAdapter := TStreamAdapter.Create(vcl) As IStream;
      result := dom.load(LAdapter);
      if not result then
      begin
        s.line := dom.ParseError.line;
        s.col := dom.ParseError.linepos;
        e.line := dom.ParseError.line;
        e.col := dom.ParseError.linepos;
        rule(outcome.issueList, IssueTypeInvalid, s, e, 'line '+inttostr(dom.ParseError.line)+', Col '+inttostr(dom.ParseError.linepos), false, dom.ParseError.reason);
      end;
              finally
                vcl.free;
              end;
            finally
              mem.free;
            end;
          end;

function TFHIRValidator.validateInstance(source: TAdvBuffer; format : TFHIRFormat; idRule : TResourceIdStatus; opDesc : String; profile : TFHirStructureDefinition): TFHIROperationOutcome;
var
  json : TJsonObject;
  wrapper : TWrapperElement;
  sax : TFHIRSaxToDomParser;
  locations : TAdvList<TSourceLocationObject>;
  ms : TMsXmlParser;
  dom : IXMLDOMDocument2;
begin
  result := TFhirOperationOutcome.create;
  try
    if (format = ffXml) then
    begin
      // we have to parse twice: once for the schema check, and once with line tracking
      validateXml(source, result);
        ms := TMsXmlParser.Create;
        locations := TAdvList<TSourceLocationObject>.create;
          try
        sax := TFHIRSaxToDomParser.create(locations.Link, 0); // no try...finally..., this is interfaced
          dom := sax.DOM;
          ms.Parse(source, sax);
          wrapper := TDOMWrapperElement.Create(nil, dom.documentElement);
          try
            TDOMWrapperElement(wrapper).FLocations := locations;
            validateResource(result.issueList, wrapper, wrapper, profile, idRule, nil);
          finally
            wrapper.Free;
          end;
      finally
          locations.Free;
          ms.Free;
        end;
    end
    else if format = ffJson then
    begin
      json := TJSONParser.Parse(source.AsBytes);
      try
        wrapper := TJsonWrapperElement.Create(json.link);
        try
          validateResource(result.issueList, wrapper, wrapper, profile, idRule, nil);
        finally
          wrapper.Free;
        end;
      finally
        json.Free;
      end;
    end;
    BuildNarrative(result, opDesc);
    result.Link;
  finally
    result.free;
  end;
end;

//procedure TFHIRBaseValidator.validateInstance(op: TFHIROperationOutcome; elem: IXMLDOMElement; specifiedprofile : TFHirStructureDefinition);
//begin
//  FValidator.validate(op.issueList, elem, specifiedprofile);
//end;

function TFHIRValidator.validateInstance(resource: TFhirResource; idRule : TResourceIdStatus; opDesc: String; profile: TFHirStructureDefinition): TFHIROperationOutcome;
var
  x : TFHIRXmlComposer;
  b : TAdvBuffer;
begin
  b := TAdvBuffer.Create;
  try
    x := TFHIRXmlComposer.Create('en');
    try
      b.AsUnicode := x.Compose(resource, true);
    finally
      x.Free;
    end;
    result := validateInstance(b, ffXml, idRule, opDesc, profile);
    finally
    b.Free;
  end;
end;


{ TFHIRBaseOnWrapper }

constructor TFHIRBaseOnWrapper.Create(services : TValidatorServiceProvider; wrapper: TWrapperElement; profile: TFHIRStructureDefinition; definition : TFhirElementDefinition; TypeName, TypeProfile : String);
    begin
  inherited create;
  FServices := services;
  FWrapper := wrapper;
  FProfile := profile;
  FDefinition := definition;
  FTypeName := TypeName;
  FTypeProfile := TypeProfile;
end;

destructor TFHIRBaseOnWrapper.Destroy;
begin
  childDefinitions.Free;
  FElementList.Free;
  FProfile.Free;
  FDefinition.Free;
  FWrapper.Free;
  FServices.Free;
  inherited;
end;

function TFHIRBaseOnWrapper.equalsDeep(other: TFHIRBase): boolean;
var
  childList : TFHIRElementDefinitionList;
  thisList, otherList : TFHIRBaseList;
  ed : TFHIRElementDefinition;
  n, tn, tp : String;
begin
  if (not inherited equalsDeep(other) or (fhirType() <> other.fhirType())) then
    exit(false);
  // make sure we have child definitions
  getDefinition('xxxx', tn, tp);

  childList := childDefinitions.Link; //
  try
  // there's a problem here - we're going to iterate by the definition, where as equality should - probably - be
  // based on the underlying definitions. is it worth getting them? it's kind of complicated....
    for ed in childList do
    begin
      n := tail(ed.path);
      if (n = 'value') and isPrimitive then
      begin
        if primitiveValue <> other.primitiveValue then
          exit(false);
      end
      else
      begin
        thisList := TFHIRBaseList.Create;
        otherList := TFHIRBaseList.Create;
        try
          listChildrenByName(n, thisList);
          other.listChildrenByName(n, otherList);
          if (not compareDeep(thisList, otherList, true)) then
            exit(false);
        finally
          thisList.Free;
          otherList.Free;
        end;
      end;
    end;
    exit(true);
  finally
    childList.free;
  end;
end;

function TFHIRBaseOnWrapper.FhirType: string;
begin
  if FTypeName <> '' then
    result := FTypeName
  else
    result := FDefinition.type_List[0].code;
end;

procedure TFHIRBaseOnWrapper.GetChildrenByName(child_name: string; list: TFHIRObjectList);
var
  children : TAdvList<TWrapperElement>;
  child : TWrapperElement;
  definition : TFhirElementDefinition;
  tn, tp : String;
begin
  children := TAdvList<TWrapperElement>.create;
  try
    FWrapper.getNamedChildrenWithWildcard(child_name, children);
    for child in children do
    begin
      definition := getDefinition(child.getName, tn, tp);
      if (definition = nil) then
        log('no definition found for '+FDefinition.path+'.'+child_name+' (en= '+child.getName+')')
      else if definition.hasType('Resource') and FWrapper.isXml then // special case for DomainResource.contained and Bundle.entry
          list.Add(TFHIRBaseOnWrapper.Create(FServices.link, child.getFirstChild.Link, Fprofile.Link, definition.Link, tn, tp))
        else
          list.Add(TFHIRBaseOnWrapper.Create(FServices.link, child.Link, Fprofile.Link, definition.Link, tn, tp));
    end;
  finally
    children.Free;
  end;
end;

//function TFHIRBaseOnWrapper.IsAbstractType(pn : String) : Boolean;
//var
//  p : TFhirStructureDefinition;
//begin
//  p := FServices.fetchResource(frtStructureDefinition, pn) as TFhirStructureDefinition;
//  try
//    result := (p <> nil) and (p.abstract);
//  finally
//    p.free;
//  end;
//end;
//
function TFHIRBaseOnWrapper.isMetaDataBased: boolean;
begin
  result := true;
end;

function TFHIRBaseOnWrapper.getDefinition(name: String; var tn, tp: String): TFhirElementDefinition;
var
  ed : TFHIRElementDefinition;
  tail : String;
  profile : TFhirStructureDefinition;
  pn : String;
begin
  if childDefinitions = nil then
    childDefinitions := getChildMap(Fprofile, Fdefinition.name, Fdefinition.path, Fdefinition.NameReference);
  if (childDefinitions.Count = 0) then
  begin
    pn := FTypeProfile;
    if (pn = '') and (FTypeName <> '') then
      pn := 'http://hl7.org/fhir/StructureDefinition/'+FTypeName;
    if (pn = '') and (FDefinition.type_List.Count = 1) then
    begin
      if FDefinition.type_List[0].profileList.Count > 0 then
        pn := FDefinition.type_List[0].profileList[0].value
      else
        pn := 'http://hl7.org/fhir/StructureDefinition/'+ FDefinition.type_List[0].code;
    end;
    if (pn <> '') then
    begin
      profile := FServices.fetchResource(frtStructureDefinition, pn) as TFhirStructureDefinition;
      try
        if (profile <> nil) then
        begin
          FProfile.Free;
          FProfile := profile.link;
          childDefinitions.Free;
          childDefinitions := getChildMap(profile, '', profile.snapshot.elementList[0].path, '');
        end;
      finally
        profile.Free;
      end;
    end;
  end;

  for ed in childDefinitions do
  begin
    tail := ed.path.Substring(ed.path.LastIndexOf('.')+1);
    if tail = name then
      exit(ed);
    if tail.EndsWith('[x]') and (tail.Substring(0, tail.Length-3) = name.Substring(0, tail.Length-3)) and ed.hasType(name.Substring(tail.Length-3), tp) then
    begin
      tn := name.Substring(tail.Length-3);
      exit(ed);
    end;
  end;
  result := nil;
end;

function TFHIRBaseOnWrapper.IsPrimitive: boolean;
begin
  result := isPrimitiveType(fhirType);
end;

procedure TFHIRBaseOnWrapper.log(msg: String);
begin

end;

function TFHIRBaseOnWrapper.primitiveValue: string;
begin
  result := FWrapper.getAttribute('value');
end;

{ TFHIRResourceOnWrapper }

constructor TFHIRResourceOnWrapper.Create(services : TValidatorServiceProvider; wrapper : TWrapperElement; profile: TFHIRStructureDefinition);
begin
  inherited create;
  FServices := services;
  FWrapper := wrapper;
  FProfile := profile;
  FDefinition := profile.snapshot.elementList[0].Link;
end;

destructor TFHIRResourceOnWrapper.Destroy;
begin
  FServices.Free;
  childDefinitions.Free;
  FElementList.Free;
  FProfile.Free;
  FDefinition.Free;
  FWrapper.Free;
  inherited;
end;

function TFHIRResourceOnWrapper.FhirType: string;
begin
  result := FWrapper.getResourceType;
  end;

procedure TFHIRResourceOnWrapper.GetChildrenByName(child_name: string; list: TFHIRObjectList);
var
  children : TAdvList<TWrapperElement>;
  child : TWrapperElement;
  definition : TFhirElementDefinition;
  tn, tp : String;
begin
  children := TAdvList<TWrapperElement>.create;
  try
    FWrapper.getNamedChildren(child_name, children);
    for child in children do
  begin
      definition := getDefinition(child.getName, tn, tp);
      if (definition <> nil) then
        if definition.hasType('Resource') and FWrapper.isXml then // special case for DomainResource.contained and Bundle.entry
          list.Add(TFHIRBaseOnWrapper.Create(FServices.link, child.getFirstChild.Link, Fprofile.Link, definition.Link, tn, tp))
        else
          list.Add(TFHIRBaseOnWrapper.Create(FServices.link, child.Link, Fprofile.Link, definition.Link, tn, tp));
    end;
  finally
    children.Free;
  end;
end;

function TFHIRResourceOnWrapper.getDefinition(name: String; var tn, tp: String): TFhirElementDefinition;
var
  ed : TFHIRElementDefinition;
  tail : String;
begin
  if childDefinitions = nil then
    childDefinitions := getChildMap(Fprofile, Fdefinition.name, Fdefinition.path, Fdefinition.NameReference);
  for ed in childDefinitions do
  begin
    tail := ed.path.Substring(ed.path.LastIndexOf('.')+1);
    if tail = name then
      exit(ed);
    if tail.EndsWith('[x]') and (tail.Substring(0, tail.Length-3) = name.Substring(0, tail.Length-3)) and ed.hasType(name.Substring(tail.Length-3), tp) then
    begin
      tn := name.Substring(tail.Length-3);
      exit(ed);
    end;
  end;
  result := nil;
end;

end.
