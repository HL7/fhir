unit FHIRUtilities;

{
Copyright (c) 2011-2013, HL7, Inc
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
  SysUtils,
  classes,
  StringSupport,
  GuidSupport,
  DateSupport,

  TextUtilities,

  FHIRSupport,
  FHIRParserBase,
  FHIRParser,
  FHIRBase,
  FHIRTypes,
  FHIRAtomFeed,
  FHIRComponents,
  FHIRResources,
  FHIRConstants;

const
  MIN_DATE = DATETIME_MIN;
  MAX_DATE = DATETIME_MAX;

function HumanNameAsText(name : TFhirHumanName):String;
function GetEmailAddress(contacts : TFhirContactList):String;

Function RecogniseFHIRResourceName(Const sName : String; out aType : TFhirResourceType): boolean;
Function RecogniseFHIRResourceManagerName(Const sName : String; out aType : TFhirResourceType): boolean;
Function RecogniseFHIRFormat(Const sName : String): TFHIRFormat;
function MakeParser(lang : String; aFormat: TFHIRFormat; oContent: TStream): TFHIRParser;
Function FhirGUIDToString(aGuid : TGuid):String;
function ParseXhtml(lang : String; content : String):TFhirXHtmlNode;
function geTFhirResourceNarrativeAsText(resource : TFhirResource) : String;
function IsId(s : String) : boolean;
procedure listReferences(resource : TFhirResource; list : TFhirResourceReferenceList);
procedure listAttachments(resource : TFhirResource; list : TFhirAttachmentList);
Function FhirHtmlToText(html : TFhirXHtmlNode):String;
function FindContainedResource(resource : TFhirResource; ref : TFhirResourceReference) : TFhirResource;
function GetResourceFromFeed(feed : TFHIRAtomFeed; ref : TFhirResourceReference) : TFHIRResource;

function BuildOperationOutcome(lang : String; e : exception) : TFhirOperationOutcome; overload;
Function BuildOperationOutcome(lang, message : String) : TFhirOperationOutcome; overload;

function asUTCMin(value : TFhirInstant) : TDateTime; overload;
function asUTCMax(value : TFhirInstant) : TDateTime; overload;
function asUTCMin(value : TFhirDate) : TDateTime; overload;
function asUTCMax(value : TFhirDate) : TDateTime; overload;
function asUTCMin(value : TFhirDateTime) : TDateTime; overload;
function asUTCMax(value : TFhirDateTime) : TDateTime; overload;
function asUTCMin(value : TFhirPeriod) : TDateTime; overload;
function asUTCMax(value : TFhirPeriod) : TDateTime; overload;
function asUTCMin(value : TFhirSchedule) : TDateTime; overload;
function asUTCMax(value : TFhirSchedule) : TDateTime; overload;

function HasExtension(element : TFhirElement; url : string):Boolean;
function GetExtension(element : TFhirElement; url : string) : TFhirExtension;

procedure BuildNarrative(op: TFhirOperationOutcome; opDesc : String); overload;
procedure BuildNarrative(vs : TFhirValueSet); overload;

Function removeCaseAndAccents(s : String) : String;

implementation


function DetectFormat(oContent : TStream) : TFHIRParserClass;
var
  i : integer;
  s : String;
begin
  i := oContent.Position;
  setlength(s, ocontent.Size - oContent.Position);
  ocontent.Read(s[1], length(s));
  oContent.Position := i;
  if (pos('<', s) > 0) and ((pos('<', s) < 10)) then
    result := TFHIRXmlParser
  else
    result := TFHIRJsonParser;

end;

function MakeParser(lang : String; aFormat: TFHIRFormat; oContent: TStream): TFHIRParser;
begin
  if aFormat = ffJSON Then
    result := TFHIRJsonParser.Create(lang)
  else if aFormat = ffXhtml then
    result := DetectFormat(oContent).create(lang)
  else
    result := TFHIRXmlParser.Create(lang);
  try
    result.source := oContent;
    result.Parse;
    result.Link;
  finally
    result.free;
  end;
end;

Function FhirGUIDToString(aGuid : TGuid):String;
begin
  result := Copy(GUIDToString(aGuid), 2, 34).ToLower;
end;


Function RecogniseFHIRResourceName(Const sName : String; out aType : TFhirResourceType): boolean;
var
  iIndex : Integer;
Begin
  iIndex := StringArrayIndexOfSensitive(CODES_TFhirResourceType, sName);
  result := iIndex > -1;
  if result then
    aType := TFhirResourceType(iIndex);
End;

Function RecogniseFHIRResourceManagerName(Const sName : String; out aType : TFhirResourceType): boolean;
var
  iIndex : Integer;
Begin
  iIndex := StringArrayIndexOfInsensitive(CODES_TFhirResourceType, sName);
  result := iIndex > -1;
  if result then
    aType := TFhirResourceType(iIndex);
End;

Function RecogniseFHIRFormat(Const sName : String): TFHIRFormat;
Begin
  if (sName = '.xml') or (sName = 'xml') or (sName = '.xsd') or (sName = 'xsd') Then
    result := ffXml
  else if (sName = '.json') or (sName = 'json') then
    result := ffJson
  else if sName = '' then
    result := ffAsIs
  else
    raise ERestfulException.create('FHIRBase', 'RecogniseFHIRFormat', 'Unknown format '+sName, HTTP_ERR_BAD_REQUEST);
End;


function ParseXhtml(lang : String; content : String):TFhirXHtmlNode;
var
  parser : TFHIRXmlParser;
begin
  parser := TFHIRXmlParser.create(lang);
  try
    parser.source := TStringStream.Create(content);
    result := parser.ParseHtml;
  finally
    parser.free;
  end;
end;


function geTFhirResourceNarrativeAsText(resource : TFhirResource) : String;
begin
  result := resource.text.div_.Content;
end;

function IsId(s : String) : boolean;
var
  i : integer;
begin
  result := length(s) in [1..36];
  if result then
    for i := 1 to length(s) do
      result := result and (s[i] in ['0'..'9', 'a'..'z', 'A'..'Z', '-', '.']);
end;

procedure iterateReferences(node : TFHIRObject; list : TFhirResourceReferenceList);
var
  iter : TFHIRPropertyIterator;
  i : integer;
begin
  iter := node.createIterator(true);
  try
    while iter.More do
    begin
      if StringStartsWith(iter.Current.Type_, 'Resource(') and (iter.Current.Obj <> nil) then
      begin
        if not StringStartsWith(TFhirResourceReference(iter.current.obj).referenceST, '#') then
          list.add(iter.Current.Obj.Link)
      end
      else if StringStartsWith(iter.Current.Type_, 'Resource(') and (iter.Current.List <> nil) then
      begin
        for i := 0 to iter.Current.List.count - 1 do
          if not StringStartsWith(TFhirResourceReference(iter.current.list[i]).referenceST, '#') then
            list.add(iter.Current.list[i].Link)
      end
      else if iter.Current.Type_ = 'Resource' then
        iterateReferences(TFhirResource(iter.current.obj), list)
      else if iter.Current.Obj <> nil then
        iterateReferences(iter.Current.Obj, list)
      else if iter.Current.list <> nil then
        for i := 0 to iter.Current.list.Count - 1 Do
          iterateReferences(iter.Current.list[i], list);
      iter.Next;
    end;
  finally
    iter.free;
  end;
end;

procedure listReferences(resource : TFhirResource; list : TFhirResourceReferenceList);
begin
  iterateReferences(resource, list);
end;

procedure iterateAttachments(node : TFHIRObject; list : TFhirAttachmentList);
var
  iter : TFHIRPropertyIterator;
  i : integer;
begin
  iter := node.createIterator(true);
  try
    while iter.More do
    begin
      if (iter.Current.Type_ = 'Attachment') and (iter.Current.Obj <> nil) then
        list.add(iter.Current.Obj.Link)
      { 0.07todo
      else if StringStartsWith(iter.Current.Type_, 'Resource(') and (iter.Current.Obj <> nil) and (TFhirResourceReference(iter.current.obj).value <> nil) then
        iterateAttachments(TFhirResourceReference(iter.current.obj).value, list)
      }  
      else if iter.Current.Obj <> nil then
        iterateAttachments(iter.Current.Obj, list)
      else if iter.Current.list <> nil then
        for i := 0 to iter.Current.list.Count - 1 Do
          iterateAttachments(iter.Current.list[i], list);
      iter.Next;
    end;
  finally
    iter.free;
  end;
end;

procedure listAttachments(resource : TFhirResource; list : TFhirAttachmentList);
begin
  iterateAttachments(resource, list);
end;


function asUTCMin(value : TFhirInstant) : TDateTime;
begin
  if (value = nil) or (value.value = nil) then
    result := MIN_DATE
  else
    result := value.value.AsUTCDateTimeMin;
end;

function asUTCMax(value : TFhirInstant) : TDateTime;
begin
  if (value = nil) or (value.value = nil) then
    result := MAX_DATE
  else
    result := value.value.AsUTCDateTimeMax;
end;

function asUTCMin(value : TFhirDateTime) : TDateTime;
begin
  if (value = nil) or (value.value = nil) then
    result := MIN_DATE
  else
    result := value.value.AsUTCDateTimeMin;
end;

function asUTCMax(value : TFhirDateTime) : TDateTime;
begin
  if (value = nil) or (value.value = nil) then
    result := MAX_DATE
  else
    result := value.value.AsUTCDateTimeMax;
end;

function asUTCMin(value : TFhirDate) : TDateTime;
begin
  if (value = nil) or (value.value = nil) then
    result := MIN_DATE
  else
    result := value.value.AsUTCDateTimeMin;
end;

function asUTCMax(value : TFhirDate) : TDateTime;
begin
  if (value = nil) or (value.value = nil) then
    result := MAX_DATE
  else
    result := value.value.AsUTCDateTimeMax;
end;

function asUTCMin(value : TFhirPeriod) : TDateTime;
begin
  if (value = nil) or (value.startST = nil) then
    result := MIN_DATE
  else
    result := value.startST.AsUTCDateTimeMin;
end;

function asUTCMax(value : TFhirPeriod) : TDateTime;
begin
  if (value = nil) or (value.end_ST = nil) then
    result := MAX_DATE
  else
    result := value.end_ST.AsUTCDateTimeMax;
end;

function asUTCMin(value : TFhirSchedule) : TDateTime;
var
  i : integer;
begin
  if (value = nil) or (value.eventList.Count = 0) then
    result := MIN_DATE
  else
  begin
    result := MAX_DATE;
    for i := 0 to value.eventList.count - 1 do
      result := DateTimeMin(result, AsUTCMin(value.eventList[i]));
  end;
end;

function asUTCMax(value : TFhirSchedule) : TDateTime;
var
  duration : TDateTime;
  i : integer;
begin
  if (value = nil) then
    result := MAX_DATE
  else if (value.repeat_ = nil) then
  begin
    if value.eventList.Count = 0 then
      result := MAX_DATE
    else
      result := MIN_DATE;
      for i := 0 to value.eventList.count - 1 do
        result := DateTimeMax(result, AsUTCMax(value.eventList[i]));
  end
  else if (value.repeat_.end_ST <> nil) then
    result := asUTCMax(value.repeat_.end_)
  else if (value.repeat_.countST <> '') and (value.eventList.Count > 0) and
    (value.repeat_.frequencyST <> '') and (value.repeat_.durationST <> '') and (value.repeat_.unitsST <> UnitsOfTimeNull) then
  begin
    result := MIN_DATE;
    for i := 0 to value.eventList.count - 1 do
      result := DateTimeMax(result, AsUTCMax(value.eventList[i]));
    if result = MIN_DATE then
      result := MAX_DATE
    else
    begin
      case value.repeat_.unitsST of
        UnitsOfTimeS : duration := DATETIME_SECOND_ONE;
        UnitsOfTimeMin : duration := DATETIME_MINUTE_ONE;
        UnitsOfTimeH : duration := DATETIME_HOUR_ONE;
        UnitsOfTimeD : duration := 1;
        UnitsOfTimeWk : duration := 7;
        UnitsOfTimeMo : duration := 30;
        UnitsOfTimeA : duration := 365 // todo - how to correct for leap years?;
      else
        raise exception.create('unknown duration units "'+value.repeat_.units.value+'"');
      end;
      result := result + (StrToInt(value.repeat_.countST) * duration / StrToInt(value.repeat_.frequencyST));
    end;
  end
  else
    result := MAX_DATE;
end;

function GetResourceFromFeed(feed : TFHIRAtomFeed; ref : TFhirResourceReference) : TFHIRResource;
var
  i : integer;
begin
  result := nil;
  for i := 0 to feed.entries.count - 1 do
  begin
    if feed.entries[i].id = ref.referenceST then
    begin
      result := feed.entries[i].resource;
      break;
    end;
  end;
end;

function FindContainedResource(resource : TFhirResource; ref : TFhirResourceReference) : TFhirResource;
var
  i : integer;
begin
  result := nil;
  for i := 0 to resource.containedList.Count - 1 do
    if ('#'+resource.containedList[i].xmlId = ref.referenceST) then
    begin
      result := resource.containedList[i];
      exit;
    end;
end;

Function FhirHtmlToText(html : TFhirXHtmlNode):String;
begin
  result := html.AsPlainText;
end;

function BuildOperationOutcome(lang : String; e : exception) : TFhirOperationOutcome;
begin
  result := BuildOperationOutcome(lang, e.message);
end;

Function BuildOperationOutcome(lang, message : String) : TFhirOperationOutcome; overload;
var
  outcome : TFhirOperationOutcome;
  report :  TFhirOperationOutcomeIssue;
begin
  outcome := TFhirOperationOutcome.create;
  try
    outcome.text := TFhirNarrative.create;
    outcome.text.statusST := NarrativeStatusGenerated;
    outcome.text.div_ := ParseXhtml(lang, '<div><p>'+FormatTextToHTML(message)+'</p></div>');
    report := outcome.issueList.Append;
    report.severityST := issueSeverityError;
    report.details := TFHIRString.create(message);
    result := outcome.Link;
  finally
    outcome.free;
  end;
end;

function HasExtension(element : TFhirElement; url : string):Boolean;
begin
  result := GetExtension(element, url) <> nil;
end;

function GetExtension(element : TFhirElement; url : string) : TFhirExtension;
var
  i : integer;
  ex : TFhirExtension;
begin
  result := nil;
  for i := 0 to element.ExtensionList.count - 1 do
  begin
    ex := element.ExtensionList[i];
    if ex.urlST = url then
    begin
      result := ex;
      exit;
    end;
  end;
end;

function gen(coding : TFHIRCoding):String; overload;
begin
  if (coding = nil) then
     result := ''
  else if (coding.Display <> nil) then
    result := coding.DisplayST
  else if (coding.Code <> nil) then
    result := coding.CodeST
  else
    result := '';
end;

function gen(extension : TFHIRExtension):String; overload;
begin
  if extension = nil then
    result := ''
  else if (extension.Value is TFHIRCode) then
    result := TFHIRCode(extension.value).value
  else if (extension.value is TFHIRCoding) then
    result := gen(TFHIRCoding(extension.value))
  else
    raise Exception.create('Unhandled type '+extension.Value.ClassName);
end;

procedure BuildNarrative(op: TFhirOperationOutcome; opDesc : String);
var
  x, tbl, tr, td : TFhirXHtmlNode;
  hasSource, hasType, success, d : boolean;
  i, j : integer;
  issue : TFhirOperationOutcomeIssue;
  s : TFhirString;
begin
  x := TFhirXHtmlNode.create;
  try
    x.NodeType := fhntElement;
    x.Name := 'div';
    x.AddTag('p').addTag('b').addText('Operation Outcome for :'+opDesc);

    hasSource := false;
    hasType := false;
    success := true;
    for i := 0 to op.issueList.count - 1 do
    begin
      issue := op.issueList[i];
      success := success and (issue.SeverityST = IssueSeverityInformation);
      hasSource := hasSource or (hasExtension(issue, 'http://hl7.org/fhir/tools#issue-source'));
      hasType := hasType or (issue.Type_ <> nil);
    end;
    if (success) then
      x.AddChild('p').addText('All OK');
    if op.issueList.count > 0 then
    begin
      tbl := x.addTag('table');
      tbl.setAttribute('class', 'grid'); // on the basis that we'll most likely be rendered using the standard fhir css, but it doesn't really matter
      tr := tbl.addTag('tr');
      tr.addTag('td').addTag('b').addText('Severity');
      tr.addTag('td').addTag('b').addText('Location');
      tr.addTag('td').addTag('b').addText('Details');
      if (hasType) then
        tr.addTag('td').addTag('b').addText('Type');
      if (hasSource) then
        tr.addTag('td').addTag('b').addText('Source');
      for i := 0 to op.issueList.count - 1 do
      begin
        issue := op.issueList[i];
        tr := tbl.addTag('tr');
        tr.addTag('td').addText(CODES_TFhirIssueSeverity[issue.severityST]);
        td := tr.addTag('td');
        d := false;
        for j := 0 to issue.locationList.count -1 do
        begin
           s := issue.locationList[j];
           if (d) then
             td.addText(', ')
           else
             d := true;
           td.addText(s.Value);
        end;
        tr.addTag('td').addText(issue.detailsST);
        if (hasType) then
          tr.addTag('td').addText(gen(issue.Type_));
        if (hasSource) then
          tr.addTag('td').addText(gen(getExtension(issue, 'http://hl7.org/fhir/tools#issue-source')));
      end;
    end;
    if (op.Text = nil) then
      op.Text := TFhirNarrative.create;
    op.Text.div_ := x.link;
    if hasSource then
      op.Text.statusST := NarrativeStatusExtensions
    else
      op.Text.statusST := NarrativeStatusGenerated;
  finally
    x.free;
  end;
end;

procedure addTableHeaderRowStandard(t : TFhirXHtmlNode);
var
  tr, td, b : TFhirXHtmlNode;
begin
  tr := t.addTag('tr');
  td := tr.addTag('td');
  b := td.addTag('b');
  b.addText('Code');
  td := tr.addTag('td');
  b := td.addTag('b');
  b.addText('Display');
  td := tr.addTag('td');
  b := td.addTag('b');
  b.addText('Definition');
end;

procedure addTableHeaderRowExpansion(t : TFhirXHtmlNode);
var
  tr, td, b : TFhirXHtmlNode;
begin
  tr := t.addTag('tr');
  td := tr.addTag('td');
  b := td.addTag('b');
  b.addText('Code');
  td := tr.addTag('td');
  b := td.addTag('b');
  b.addText('System');
  td := tr.addTag('td');
  b := td.addTag('b');
  b.addText('Display');
end;


procedure addDefineRowToTable(t : TFhirXHtmlNode; c : TFHIRValueSetDefineConcept; indent : integer);
var
  tr, td : TFhirXHtmlNode;
  s : string;
  i : integer;
begin
  tr := t.addTag('tr');
  td := tr.addTag('td');
  s := StringpadLeft('', '.', indent*2);
  td.addText(s+c.CodeST);
  td := tr.addTag('td');
  td.addText(c.DisplayST);
  td := tr.addTag('td');
  td.addText(c.DefinitionST);
  for i := 0 to c.ConceptList.count - 1 do
    addDefineRowToTable(t, c.conceptList[i], indent+1);
end;

procedure addContainsRowToTable(t : TFhirXHtmlNode; c : TFhirValueSetExpansionContains; indent : integer);
var
  tr, td : TFhirXHtmlNode;
  s : string;
  i : integer;
begin
  tr := t.addTag('tr');
  td := tr.addTag('td');
  s := StringpadLeft('', '.', indent*2);
  if c.codeST = '' then
    td.addText(s+'+')
  else
    td.addText(s+c.CodeST);
  td := tr.addTag('td');
  td.addText(c.SystemST);
  td := tr.addTag('td');
  td.addText(c.DisplayST);
  for i := 0 to c.containsList.count - 1 do
    addContainsRowToTable(t, c.containsList[i], indent+1);
end;

procedure generateDefinition(x : TFhirXHtmlNode; vs : TFHIRValueSet);
var
  p, t : TFhirXHtmlNode;
  i : integer;
begin
  p := x.addTag('p');
  p.addText('This value set defines it''s own terms in the system '+vs.Define.SystemST);
  t := x.addTag('table');
  addTableHeaderRowStandard(t);
  for i := 0 to vs.Define.ConceptList.Count - 1 do
    addDefineRowToTable(t, vs.Define.ConceptList[i], 0);
end;


procedure generateExpansion(x : TFhirXHtmlNode; vs : TFhirValueSet);
var
  h, p, t : TFhirXHtmlNode;
  i : integer;
begin
  h := x.addTag('h2');
  h.addText('Expansion for '+vs.NameST);
  p := x.addTag('p');
  p.addText(vs.DescriptionST);
  p := x.addTag('p');
  p.addText('This value set is an expansion, and includes the following terms in the expansion');
  t := x.addTag('table');
  addTableHeaderRowExpansion(t);
  for i := 0 to vs.expansion.containsList.Count - 1 do
    addContainsRowToTable(t, vs.expansion.containsList[i], 0);
end;

procedure generateComposition(x : TFhirXHtmlNode; vs : TFhirValueSet);
begin
   raise Exception.create('todo');
end;

procedure BuildNarrative(vs : TFhirValueSet);
var
  x, h, p : TFhirXHtmlNode;
begin
  x := TFhirXHtmlNode.create;
  try
    x.NodeType := fhntElement;
    x.Name := 'div';

    if (vs.Expansion <> nil) then
      generateExpansion(x, vs)
    else
    begin
      h := x.addTag('h2');
      h.addText(vs.NameST);
      p := x.addTag('p');
      p.addText(vs.DescriptionST);
      if (vs.Define <> nil) then
        generateDefinition(x, vs);
      if (vs.Compose <> nil) then
        generateComposition(x, vs);
    end;

    if (vs.Text = nil) then
      vs.Text := TFhirNarrative.create;
    vs.Text.div_ := x.link;
    vs.Text.statusST := NarrativeStatusGenerated;
  finally
    x.free;
  end;
end;

function GetEmailAddress(contacts : TFhirContactList):String;
var
  i : integer;
begin
  result := '';
  if contacts <> nil then
    for i := 0 to contacts.Count - 1 do
      if contacts[i].systemST = ContactSystemEmail then
        result := contacts[i].valueST;
end;

function HumanNameAsText(name : TFhirHumanName):String;
var
  i : integer;
begin
  if name = nil then
    result := ''
  else if name.textST <> '' then
    result := name.textST
  else
  begin
    result := '';
    for i := 0 to name.givenList.Count - 1 do
      result := result + name.givenList[i].value+' ';
    for i := 0 to name.familyList.Count - 1 do
      result := result + name.familyList[i].value+' ';
  end;
end;


(*



  procedure generateComposition(x : TFhirTFhirXHtmlNode; vs : TFHIRValueSet, Map<String, AtomEntry> codeSystems) throws Exception begin
    TFhirXHtmlNode h := x.addTag('h2');
    h.addText(vs.NameST);
    TFhirXHtmlNode p := x.addTag('p');
    p.addText(vs.DescriptionST);
    p := x.addTag('p');
    p.addText('This value set includes terms defined in other code systems, using the following rules:');
    TFhirXHtmlNode ul := x.addTag('ul');
    TFhirXHtmlNode li;
    for (Uri imp : vs.Compose.Import) begin
      li := ul.addTag('li');
      li.addText('Import all the codes that are part of '+imp.Value);
    end;
    for (ConceptSetComponent inc : vs.Compose.Include) begin
      genInclude(ul, inc, 'Include', codeSystems);      
    end;
    for (ConceptSetComponent exc : vs.Compose.Exclude) begin
      genInclude(ul, exc, 'Exclude', codeSystems);      
    end;
  end;

  procedure genInclude(TFhirXHtmlNode ul, ConceptSetComponent inc, String type, Map<String, AtomEntry> codeSystems) throws Exception begin
    TFhirXHtmlNode li;
    li := ul.addTag('li');
    AtomEntry e := codeSystems.(inc.SystemST.toString);
    
    if (inc.Code.size :=:= 0 && inc.Filter.size :=:= 0) begin then 
      li.addText(type+' all codes defined in ');
      addCsRef(inc, li, e);
    end; else begin 
      if (inc.Code.size > 0) begin then
        li.addText(type+' these codes as defined in ');
        addCsRef(inc, li, e);
      
        TFhirXHtmlNode t := li.addTag('table');
        addTableHeaderRowStandard(t);
        for (Code c : inc.Code) begin
          TFhirXHtmlNode tr := t.addTag('tr');
          TFhirXHtmlNode td := tr.addTag('td');
          td.addText(c.Value);         
          ValueSetDefineConceptComponent cc := getConceptForCode(e, c.Value);
          if (cc <> nil) begin then
            td := tr.addTag('td');
            if (!Utilities.noString(cc.DisplayST)) then
              td.addText(cc.DisplayST);
            td := tr.addTag('td');
            if (!Utilities.noString(cc.DefinitionST)) then
              td.addText(cc.DefinitionST);
          end;
        end;
      end;
      for (ConceptSetFilterComponent f : inc.Filter) begin
        li.addText(type+' codes from ');
        addCsRef(inc, li, e);
        li.addText(' where '+f.PropertyST+' '+describe(f.OpST)+' ');
        if (e <> nil && codeExistsInValueSet(e, f.ValueST)) begin then
          TFhirXHtmlNode a := li.addTag('a');
          a.addTag(f.ValueST);
          a.setAttribute('href', getCsRef(e)+'#'+f.ValueST);
        end; else
          li.addText(f.ValueST);
      end;
    end;
  end;

  private String describe(FilterOperator opST) begin
    switch (opST) begin
    case equal: return ' := ';
    case isA: return ' is-a ';
    case isNotA: return ' is-not-a ';
    case regex: return ' matches (by regex) ';
    
    end;
    return nil;
  end;

  private ValueSetDefineConceptComponent getConceptForCode(AtomEntry e, String code) begin
    if (e :=:= nil) then
      return nil;
    vs : TFHIRValueSet := (ValueSet) e.Resource;
    if (vs.Define :=:= nil) then
      return nil;
    for (ValueSetDefineConceptComponent c : vs.Define.Concept) begin
      ValueSetDefineConceptComponent v := getConceptForCode(c, code);   
      if (v <> nil) then
        return v;
    end;
    return nil;
  end;
  
  
  
  private ValueSetDefineConceptComponent getConceptForCode(ValueSetDefineConceptComponent c, String code) begin
    if (code.equals(c.CodeST)) then
      return c;
    for (ValueSetDefineConceptComponent cc : c.Concept) begin
      ValueSetDefineConceptComponent v := getConceptForCode(cc, code);   
      if (v <> nil) then
        return v;
    end;
    return nil;
  end;

  procedure addCsRef(ConceptSetComponent inc, TFhirXHtmlNode li, AtomEntry cs) begin
    if (cs <> nil && cs.Links.('self') <> nil) begin then
      TFhirXHtmlNode a := li.addTag('a');
      a.setAttribute('href', cs.Links.('self').replace('\\', '/'));
      a.addText(inc.SystemST.toString);
    end; else 
      li.addText(inc.SystemST.toString);
  end;

  private String getCsRef(AtomEntry cs) begin
    return cs.Links.('self').replace('\\', '/');
  end;

  private boolean codeExistsInValueSet(AtomEntry cs, String code) begin
    vs : TFHIRValueSet := (ValueSet) cs.Resource;
    for (ValueSetDefineConceptComponent c : vs.Define.Concept) begin
      if (inConcept(code, c)) then
        return true;
    end;
    return false;
  end;

  private boolean inConcept(String code, ValueSetDefineConceptComponent c) begin
    if (c.CodeST <> nil && c.CodeST.equals(code)) then
      return true;
    for (ValueSetDefineConceptComponent g : c.Concept) begin
      if (inConcept(code, g)) then
        return true;
    end;
    return false;
  end;

*)

Function removeCaseAndAccents(s : String) : String;
begin
  result := lowercase(s);
end;

end.

