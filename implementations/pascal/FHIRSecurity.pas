unit FHIRSecurity;

interface

uses
  SysUtils, Classes, IniFiles,
  AdvObjects,
  FHIRResources, FHIRConstants,
  SCIMObjects;

Const
//  SECURITY_BASE_URI = 'http://www.healthintersections.com.au/scim/entitlement/';
  // can admin SCIM
  SCIM_ADMINISTRATOR = 'http://www.healthintersections.com.au/scim/administer';

  // SMART ON FHIR Scopes
  SCIM_SMART_PREFIX = 'http://smarthealthit.org/fhir/scopes/';
  SCIM_OPENID_PREFIX = 'http://openid.net/specs/openid-connect-core-1_0#';

var
  nonSecureTypes : TFhirResourceTypeSet;

type
  TFHIRSecurityRights = class (TAdvObject)
  private
    FSource : String;
    FUserInfo : boolean;
    FAdministerUsers : boolean;
    FReadAll : boolean;
    FReadAllowed : array [TFhirResourceType] of boolean;
    FWriteAllowed : array [TFhirResourceType] of boolean;

    procedure init;
    procedure processScopes(scopes: TStringList; base : TFHIRSecurityRights; secure : boolean);
  public
//    constructor create(config : TIniFile); overload;
    constructor create(user : TSCIMUser; secure : boolean); overload;
    constructor create(base : TSCIMUser; choice : String; secure : boolean); overload;
    constructor create(base : TSCIMUser; choice : TStringList; secure : boolean); overload;
    destructor destroy; override;

    property canGetUserInfo : boolean read FUserInfo;
    function canRead(aType : TFHIRResourceType) : boolean;
    function canWrite(aType : TFHIRResourceType) : boolean;
    property canReadAll : boolean read FReadAll;
    property canAdministerUsers : boolean read FAdministerUsers;
    procedure allowAll;

    property source : String read FSource;

    class function allScopes : String;
    class function allScopesAsUris : TStringList;
  end;

function UriForScope(scope : String): String;
function prefixScope(uri : String): String;

implementation

{ TFHIRSecurityRights }

constructor TFHIRSecurityRights.create(user: TSCIMUser; secure : boolean);
var
  list : TStringList;
  i : integer;
begin
  init;
  list := TStringList.Create;
  try
    for i := 0 to user.entitlementCount - 1 do
      if user.entitlement[i].StartsWith(SCIM_SMART_PREFIX) then
        list.Add(user.entitlement[i].Substring(SCIM_SMART_PREFIX.Length))
      else if user.entitlement[i].StartsWith(SCIM_OPENID_PREFIX) then
        list.Add(user.entitlement[i].Substring(SCIM_OPENID_PREFIX.Length))
      else
        list.add(user.entitlement[i]);
    processScopes(list, nil, secure);
  finally
    list.Free;
  end;
end;

constructor TFHIRSecurityRights.create(base: TSCIMUser; choice: String; secure : boolean);
var
  user : TFHIRSecurityRights;
  list : TStringList;
begin
  init;
  user := TFHIRSecurityRights.create(base, secure);
  try
    list := TStringList.Create;
    try
      list.CommaText := choice.Replace(' ', ',');
      processScopes(list, user, false);
    finally
      list.Free;
    end;
  finally
    user.free;
  end;
end;

constructor TFHIRSecurityRights.create(base: TSCIMUser; choice: TStringList; secure : boolean);
var
  user : TFHIRSecurityRights;
begin
  init;
  user := TFHIRSecurityRights.create(base, secure);
  try
    processScopes(choice, user, false);
  finally
    user.free;
  end;
end;

destructor TFHIRSecurityRights.destroy;
begin
  inherited;
end;

procedure TFHIRSecurityRights.init;
var
  a : TFhirResourceType;
begin
  for a := Low(TFhirResourceType) to High(TFhirResourceType) do
  begin
    FReadAllowed[a] := false;
    FWriteAllowed[a] := false;
  end;
end;

procedure TFHIRSecurityRights.allowAll;
var
  list : TStringList;
begin
  list := TStringList.Create;
  try
    list.CommaText := allScopes.Replace(' ', ',');
    processScopes(list, nil, true);
  finally
    list.Free;
  end;
end;

class function TFHIRSecurityRights.allScopes: String;
begin
  result := 'openid profile user/*.*';
end;

class function TFHIRSecurityRights.allScopesAsUris: TStringList;
begin
  result := TStringList.create;
  result.add(SCIM_SMART_PREFIX+'openid');
  result.add(SCIM_SMART_PREFIX+'profile');
  result.add(SCIM_SMART_PREFIX+'user/*.*');
end;

function TFHIRSecurityRights.canRead(aType: TFHIRResourceType): boolean;
begin
  result := FReadAllowed[aType];
end;


function TFHIRSecurityRights.canWrite(aType: TFHIRResourceType): boolean;
begin
  result := FWriteAllowed[aType];
end;

procedure TFHIRSecurityRights.processScopes(scopes: TStringList; base : TFHIRSecurityRights; secure : boolean);
var
  a : TFhirResourceType;
  s : String;
begin
  FSource := scopes.CommaText.replace(',', ' ');
  if (scopes.IndexOf('openid') > -1) and (scopes.IndexOf('profile') > -1) and ((base = nil)  or (base.canGetUserInfo)) then
    FUserInfo := true;
  if (scopes.IndexOf(SCIM_ADMINISTRATOR) > -1) and ((base = nil)  or (base.canAdministerUsers)) then
    FAdministerUsers := true;

  FReadAll := true;
  for a := Low(TFhirResourceType) to High(TFhirResourceType) do
  begin
    if (base <> nil) and not base.canRead(a) then
      FReadAllowed[a] := false
    else if not (assigned(base) or secure or (a in nonSecureTypes)) then
      FReadAllowed[a] := true
    else if scopes.IndexOf('user/*.*') > -1 then
      FReadAllowed[a] := true
    else if scopes.IndexOf('patient/*.*') > -1 then
      FReadAllowed[a] := true
    else if scopes.IndexOf('user/'+CODES_TFHIRResourceType[a]+'.*') > -1 then
      FReadAllowed[a] := true
    else if scopes.IndexOf('patient/'+CODES_TFHIRResourceType[a]+'.*') > -1 then
      FReadAllowed[a] := true
    else if scopes.IndexOf('user/'+CODES_TFHIRResourceType[a]+'.read') > -1 then
      FReadAllowed[a] := true
    else if scopes.IndexOf('patient/'+CODES_TFHIRResourceType[a]+'.read') > -1 then
      FReadAllowed[a] := true
    else
      FReadAllowed[a] := false;
    if (a <> frtNull) then
      FReadAll := FReadAll and FReadAllowed[a];

    if (base <> nil) and not base.canWrite(a) then
      FWriteAllowed[a] := false
    else if not (assigned(base) or secure or (a in nonSecureTypes)) then
      FWriteAllowed[a] := true
    else if scopes.IndexOf('user/*.*') > -1 then
      FWriteAllowed[a] := true
    else if scopes.IndexOf('patient/*.*') > -1 then
      FWriteAllowed[a] := true
    else if scopes.IndexOf('user/'+CODES_TFHIRResourceType[a]+'.*') > -1 then
      FWriteAllowed[a] := true
    else if scopes.IndexOf('patient/'+CODES_TFHIRResourceType[a]+'.*') > -1 then
      FWriteAllowed[a] := true
    else if scopes.IndexOf('user/'+CODES_TFHIRResourceType[a]+'.write') > -1 then
      FWriteAllowed[a] := true
    else if scopes.IndexOf('patient/'+CODES_TFHIRResourceType[a]+'.write') > -1 then
      FWriteAllowed[a] := true
    else
      FWriteAllowed[a] := false;
  end;
end;


function UriForScope(scope : String): String;
begin
  if (scope.StartsWith('http:') or scope.StartsWith('https:')) then
    result := scope
  else if (scope = 'openid') or (scope = 'profile') then
    result := SCIM_OPENID_PREFIX+scope
  else
    result := SCIM_SMART_PREFIX+scope;
end;

function prefixScope(uri : String): String;
begin
  if uri.StartsWith(SCIM_SMART_PREFIX) then
    result := 'smart:'+uri.Substring(SCIM_SMART_PREFIX.Length)
  else if uri.StartsWith(SCIM_OPENID_PREFIX) then
    result := 'openid:'+uri.Substring(SCIM_OPENID_PREFIX.Length)
  else if uri.StartsWith('http://www.healthintersections.com.au') then
    result := 'server:'+uri.Substring('http://www.healthintersections.com.au'.Length)
  else
    result := uri;
end;

end.
