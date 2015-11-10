unit ProfileManager;


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
  SysUtils,
  kCritSct,
  StringSupport,
  AdvObjects, AdvStringMatches, AdvStringObjectMatches, AdvGenerics,
  FHIRResources, FHIRUtilities, FHIRConstants, FHIRTypes;


Type
  TProfileManager = class (TAdvObject)
  private
    lock : TCriticalSection;
    FProfilesById : TAdvMap<TFHIRStructureDefinition>; // all current profiles by identifier (ValueSet.identifier)
    FProfilesByURL : TAdvMap<TFHIRStructureDefinition>; // all current profiles by their URL
//    FExtensions : TAdvStringObjectMatch;
    function GetProfileByUrl(url: String): TFHirStructureDefinition;
    function GetProfileByType(aType: TFhirResourceType): TFHirStructureDefinition; // all profiles by the key they are known from (mainly to support drop)

  public
    constructor Create; override;
    destructor Destroy; override;
    function Link : TProfileManager; overload;

    procedure SeeProfile(key : Integer; profile : TFHirStructureDefinition);
    procedure DropProfile(aType: TFhirResourceType; id : String);
    procedure loadFromFeed(feed : TFHIRBundle);

    function getExtensionDefn(source : TFHirStructureDefinition; url : String; var profile : TFHirStructureDefinition; var extension : TFHirStructureDefinition) : boolean;
    function getProfileStructure(source : TFHirStructureDefinition; url : String; var profile : TFHirStructureDefinition) : boolean;
    function getLinks(non_resources : boolean) : TAdvStringMatch;

    property ProfileByURL[url : String] : TFHirStructureDefinition read GetProfileByUrl; default;
    property ProfileByType[aType : TFhirResourceType] : TFHirStructureDefinition read GetProfileByType;
    property ProfilesByURL : TAdvMap<TFHIRStructureDefinition> read FProfilesByURL;
  end;

  {
  This encapsulates a reference to an element definition within a structure.
  The path may be replace
  }
  TProfileDefinition = class (TAdvObject)
  private
    FProfiles : TProfileManager;
    FProfile : TFHirStructureDefinition;
    FElement : TFhirElementDefinition;
    statedPath : String;
    FType : TFhirElementDefinitionType;

    function GetTypes: TFhirElementDefinitionTypeList;
    function GetPath: String;
    function GetName: String;
    Property Types : TFhirElementDefinitionTypeList read GetTypes;
  public
    Constructor Create(profiles : TProfileManager; profile : TFHirStructureDefinition); overload;
    Destructor Destroy; override;

    procedure setType(t : TFhirElementDefinitionType);
    function statedType : TFhirElementDefinitionType;
    function hasTypeChoice : boolean;
    Property path : String read GetPath;
    Property name : String read GetName;
    function getById(id : String) : TProfileDefinition;
  end;

implementation

{ TProfileManager }

constructor TProfileManager.Create;
begin
  inherited;
  lock := TCriticalSection.Create('profiles');
  FProfilesById := TAdvMap<TFhirStructureDefinition>.create;
  FProfilesByURL := TAdvMap<TFhirStructureDefinition>.create;
end;

destructor TProfileManager.Destroy;
begin
  FProfilesById.free;
  FProfilesByURL.free;
  lock.Free;
  inherited;
end;

function TProfileManager.getExtensionDefn(source: TFHirStructureDefinition; url: String; var profile: TFHirStructureDefinition; var extension : TFHirStructureDefinition): boolean;
//var
//  id, code : String;
//  i : integer;
begin
  raise Exception.Create('not done yet');
{  result := false;
  if url.StartsWith('#') then
  begin
    profile := source;
    code := url.Substring(1);
  end
  else
  begin
    StringSplit(url, '#', id, code);
    lock.Lock;
    try
      profile := FProfilesByIdentifier.Matches[id] as TFHirStructureDefinition;
    finally
      lock.Unlock;
    end;
  end;

  if (profile <> nil) then
  begin
    extension := nil;
    for i := 0 to profile.extensionDefnList.Count - 1 do
      if profile.extensionDefnList[i].code = url.Substring(1) then
        extension := profile.extensionDefnList[i];
    result := extension <> nil;
  end;}

end;

function TProfileManager.getLinks(non_resources : boolean): TAdvStringMatch;
var
  p : TFHirStructureDefinition;
  url : String;
begin
  lock.Lock('getLinks');
  try
    result := TAdvStringMatch.Create;
    try
      for url in FProfilesByURL.Keys do
      begin
        if (not url.startsWith('http:')) then
        begin
          p := FProfilesByURL[url];
          if non_resources or StringArrayExistsSensitive(CODES_TFhirResourceType, p.snapshot.elementList[0].path) then
            result.Add(url, p.name);
        end;
      end;
      result.Link;
    finally
      result.Free;
    end;
  finally
    lock.Unlock;
  end;
end;

function TProfileManager.GetProfileByType(aType: TFhirResourceType): TFHirStructureDefinition;
begin
  result := GetProfileByUrl('http://hl7.org/fhir/Profile/'+CODES_TFHIRResourceType[aType]);
end;

function TProfileManager.GetProfileByUrl(url: String): TFHirStructureDefinition;
begin
  if FProfilesByURL.ContainsKey(url) then
    result := FProfilesByURL[url].Link
  else
    result := nil;
end;

function TProfileManager.getProfileStructure(source: TFHirStructureDefinition; url: String; var profile: TFHirStructureDefinition): boolean;
var
  id, code : String;
begin
  result := false;
  if url.StartsWith('#') then
  begin
    profile := source;
    code := url.Substring(1);
  end
  else
  begin
    StringSplit(url, '#', id, code);
    lock.Lock;
    try
      profile := FProfilesByURL[id].Link;
    finally
      lock.Unlock;
    end;
  end;

  if profile = nil then
    result := false
  else
  begin
    result := true;
  end;
  if (code <> '') then
    raise Exception.Create('Not Done Yet');
end;

function TProfileManager.Link: TProfileManager;
begin
  result := TProfileManager(inherited Link);
end;

procedure TProfileManager.loadFromFeed(feed: TFHIRBundle);
var
  i : integer;
begin
  for i := 0 to feed.entryList.Count - 1 do
  begin
    if feed.entryList[i].resource is TFHirStructureDefinition then
      SeeProfile(i, feed.entryList[i].resource as TFHirStructureDefinition);
  end;
end;

procedure TProfileManager.SeeProfile(key: Integer; profile: TFHirStructureDefinition);
begin
  lock.Lock('SeeProfile');
  try
    FProfilesById.AddOrSetValue(profile.id, profile.Link);
    FProfilesByURL.AddOrSetValue(profile.url, profile.Link);
  finally
    lock.Unlock;
  end;
end;


procedure TProfileManager.DropProfile(aType: TFhirResourceType; id : String);
var
  p : TFHirStructureDefinition;
begin
  lock.Lock('DropProfile');
  try
    if FProfilesById.ContainsKey(id) then
    begin
      p := FProfilesById[id];
      FProfilesByURL.Remove(p.url);
      FProfilesById.Remove(id);
    end;
  finally
    lock.Unlock;
  end;
end;

{ TProfileDefinition }

constructor TProfileDefinition.Create(profiles: TProfileManager; profile: TFHirStructureDefinition);
begin
  Create;
  FProfiles := profiles;
  FProfile := profile;
  FElement := profile.snapshot.elementList[0].link;
end;

destructor TProfileDefinition.Destroy;
begin
  FType.free;
  FProfiles.Free;
  FProfile.Free;
  FElement.Free;
  inherited;
end;

function TProfileDefinition.getById(id: String): TProfileDefinition;
var
  path : String;
  i : integer;
  profile : TFHirStructureDefinition;
  elements : TFhirElementDefinitionList;
begin
//  if FActualPath = '' then
//    path := id
//  else if not id.StartsWith(FStatedPath) then
//    raise Exception.Create('Bad Path "'+id+'"')
//  else
//   path := FActualPath+ id.Substring(FStatedPath.Length);

  if id.endsWith('/1') then
    id := id.subString(0, id.length-2);

  if (Types.Count = 0) or (Types[0].code = 'Resource') then
  begin
    path := id;
    profile := FProfile;
  end
  else if Types.Count = 1 then
  begin
    profile := FProfiles['http://hl7.org/fhir/Profile/'+Types[0].code];
    if (profile = nil) then
      raise Exception.Create('Unable to find profile for '+Types[0].code+' @ '+id);
    path := Types[0].code+id.Substring(statedPath.Length);
  end
  else if FType <> nil then
  begin
    profile := FProfiles['http://hl7.org/fhir/Profile/'+FType.code];
    if (profile = nil) then
      raise Exception.Create('Unable to find profile for '+FType.code+' @ '+id);
    if not id.startsWith(statedPath+'._'+FType.tags['type']) then
      raise Exception.Create('Internal logic error');
    path := Types[0].code+id.Substring(statedPath.Length+2+FType.tags['type'].length);
  end
  else
    raise Exception.Create('not handled - multiple types');
  elements := profile.snapshot.elementList;

  result := nil;
  for i := 0 to elements.Count - 1 do
    if elements[i].path = path then
    begin
      result := TProfileDefinition.Create(FProfiles.Link, profile.Link);
      try
        result.FElement := elements[i].Link;
        result.statedPath := id;
        result.link;
      finally
        result.free;
      end;
      break;
    end;

  if result = nil then
    raise Exception.Create('Unable to resolve path "'+id+'"');
end;

function TProfileDefinition.GetName: String;
begin
  result := path.substring(path.lastIndexOf('.')+1);
end;

function TProfileDefinition.GetPath: String;
begin
  result := FElement.path;
end;

function TProfileDefinition.GetTypes: TFhirElementDefinitionTypeList;
begin
  result := FElement.type_List;
end;

function TProfileDefinition.hasTypeChoice: boolean;
begin
  result := Types.Count > 1;
end;

procedure TProfileDefinition.setType(t: TFhirElementDefinitionType);
begin
  FType.Free;
  FType := t;
end;

function TProfileDefinition.statedType: TFhirElementDefinitionType;
begin
  if Types.Count = 0 then
    result := nil
  else if Types.Count = 1 then
    result := Types[0]
  else
    raise Exception.Create('Shouldn''t get here');
end;


end.


