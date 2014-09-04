unit SCIMObjects;

interface

uses
  SysUtils,
  AdvObjects, AdvObjectLists,
  JSON, DateAndTime;

Const
  SCIM_ADMIN = 'http://www.healthintersections.com.au/scim/entitlement/admin';
  SCIM_ANONYMOUS = 'http://www.healthintersections.com.au/scim/entitlement/anonymous';

Type
  ESCIMException = class (Exception)
  private
    FStatus : integer;
    FStatusText : String;
    FScimType : String;
  public
    Constructor Create(status : Integer; statusText : String; scimType : String; message : String);
    property Status : integer read FStatus;
    property StatusText : String read FStatusText;
    property ScimType : String read FScimType;
  end;

  TSCIMObject = class (TAdvObject)
  private
    FCreated : TDateAndTime;
    FLastModified : TDateAndTime;
    FCreatedUTC : TDateAndTime;
    FLastModifiedUTC : TDateAndTime;
    FJson : TJsonObject;
    function GetCreated: TDateAndTime;
    function GetId: String;
    function GetLastModified: TDateAndTime;
    function GetLocation: String;
    function GetResourceType: String;
    function GetVersion: String;
    procedure SetCreated(const Value: TDateAndTime);
    procedure SetId(const Value: String);
    procedure SetLastModified(const Value: TDateAndTime);
    procedure SetLocation(const Value: String);
    procedure SetResourceType(const Value: String);
    procedure SetVersion(const Value: String);
    function GetExternalId: String;
    procedure SetExternalId(const Value: String);
    function GetCreatedUTC: TDateAndTime;
    function GetLastModifiedUTC: TDateAndTime;
  protected
    function hasSchema(s : String) : boolean;
    procedure checkSchema(s : String);
  public
    Constructor Create(FJson : TJsonObject); virtual;
    Destructor Destroy; override;

    property id : String read GetId write SetId;
    property ExternalId : String read GetExternalId write SetExternalId;
    property resourceType : String read GetResourceType write SetResourceType;
    property created : TDateAndTime read GetCreated write SetCreated;
    property lastModified : TDateAndTime read GetLastModified write SetLastModified;
    property createdUTC : TDateAndTime read GetCreatedUTC;
    property lastModifiedUTC : TDateAndTime read GetLastModifiedUTC;
    property location : String read GetLocation write SetLocation;
    property version : String read GetVersion write SetVersion;
    property json : TJsonObject read FJson;
  end;

  TSCIMContact = class (TAdvObject)
  private
    FJson : TJsonObject;
    function GetType: String;
    function GetValue: String;
    procedure SetType(const Value: String);
    procedure SetValue(const Value: String);
    function GetPrimary: Boolean;
    procedure SetPrimary(const Value: Boolean);
  public
    Constructor Create(FJson : TJsonObject);
    Destructor Destroy; override;

    Property Value : String read GetValue write SetValue;
    Property Type_ : String read GetType write SetType;
    Property Primary : Boolean read GetPrimary write SetPrimary;
  end;

  TSCIMContactList = class (TAdvObjectList)
  private
    function GetContact(index: integer): TSCIMContact;
  protected
    function ItemClass : TAdvObjectClass; override;
  public
    Property Contact[index: integer] : TSCIMContact read GetContact; default;
  end;

  TSCIMAddress = class (TAdvObject)
  private
    FJson : TJsonObject;
    function GetType: String;
    function GetPrimary: Boolean;
    procedure SetPrimary(const Value: Boolean);
    procedure SetType(const Value: String);
    function GetCountry: String;
    function GetFormatted: String;
    function GetLocality: String;
    function GetPostalCode: String;
    function GetRegion: String;
    function GetStreetAddress: String;
    procedure SetCountry(const Value: String);
    procedure SetFormatted(const Value: String);
    procedure SetLocality(const Value: String);
    procedure SetPostalCode(const Value: String);
    procedure SetRegion(const Value: String);
    procedure SetStreetAddress(const Value: String);
  public
    Constructor Create(FJson : TJsonObject);
    Destructor Destroy; override;

    Property Type_ : String read GetType write SetType;
    Property Primary : Boolean read GetPrimary write SetPrimary;
    Property StreetAddress : String read GetStreetAddress write SetStreetAddress;
    Property Locality : String read GetLocality write SetLocality;
    Property Region : String read GetRegion write SetRegion;
    Property PostalCode : String read GetPostalCode write SetPostalCode;
    Property Country : String read GetCountry write SetCountry;
    Property Formatted : String read GetFormatted write SetFormatted;
  end;

  TSCIMAddressList = class (TAdvObjectList)
  private
    function GetAddress(index: integer): TSCIMAddress;
  protected
    function ItemClass : TAdvObjectClass; override;
  public
    Property Address[index: integer] : TSCIMAddress read GetAddress; default;
  end;

  TSCIMUser = class (TSCIMObject)
  private
    FEmails : TSCIMContactList;
    FPhoneNums : TSCIMContactList;
    FIMs : TSCIMContactList;
    FAddresses : TSCIMAddressList;

    function GetPassword: String;
    procedure SetPassword(const Value: String);
    function GetUsername: String;
    procedure SetUsername(const Value: String);

    function GetDisplayName: String;
    function GetFamilyName: String;
    function GetGivenName: String;
    function GetLocale: String;
    function GetMiddleName: String;
    function GetName: String;
    function GetNickName: String;
    function GetPreferredLanguage: String;
    function GetPrefix: String;
    function GetProfileUrl: String;
    function GetSuffix: String;
    function GetTimezone: String;
    function GetTitle: String;
    function GetUserType: String;
    procedure SetDisplayName(const Value: String);
    procedure SetFamilyName(const Value: String);
    procedure SetGivenName(const Value: String);
    procedure SetLocale(const Value: String);
    procedure SetMiddleName(const Value: String);
    procedure SetName(const Value: String);
    procedure SetNickName(const Value: String);
    procedure SetPreferredLanguage(const Value: String);
    procedure SetPrefix(const Value: String);
    procedure SetProfileUrl(const Value: String);
    procedure SetSuffix(const Value: String);
    procedure SetTimezone(const Value: String);
    procedure SetTitle(const Value: String);
    procedure SetUserType(const Value: String);
    function GetEmails: TSCIMContactList;
    function GetIms: TSCIMContactList;
    function GetPhoneNums: TSCIMContactList;
    function GetAddresses: TSCIMAddressList;
    function GetEntitlement(i: integer): String;
    function GetEntitlementCount: integer;
  public
    Constructor Create(FJson : TJsonObject); override;
    Destructor Destroy; override;
    Function Link : TSCIMUser; overload;

    procedure check;
    procedure copyFrom(source : TSCIMUser);

    Property password : String read GetPassword write SetPassword;
    Property username : String read GetUsername write SetUsername;

    Property formattedName : String read GetName write SetName;
    Property FamilyName : String read GetFamilyName write SetFamilyName;
    Property GivenName : String read GetGivenName write SetGivenName;
    Property MiddleName : String read GetMiddleName write SetMiddleName;
    Property Prefix : String read GetPrefix write SetPrefix;
    Property Suffix : String read GetSuffix write SetSuffix;
    Property DisplayName : String read GetDisplayName write SetDisplayName;
    Property NickName : String read GetNickName write SetNickName;
    Property ProfileUrl : String read GetProfileUrl write SetProfileUrl;
    Property Title : String read GetTitle write SetTitle;
    Property UserType : String read GetUserType write SetUserType;
    Property PreferredLanguage : String read GetPreferredLanguage write SetPreferredLanguage;
    Property Locale : String read GetLocale write SetLocale;
    Property Timezone : String read GetTimezone write SetTimezone;

    Property emails : TSCIMContactList read GetEmails;
    Property phoneNums : TSCIMContactList read GetPhoneNums;
    Property ims : TSCIMContactList read GetIms;
    Property addresses : TSCIMAddressList read GetAddresses;

    Property entitlementCount : integer read GetEntitlementCount;
    Property entitlement[i : integer] : String read GetEntitlement;

    function AddEmail(value, type_ : String) : TSCIMContact;
    function hasEmail(value : String) : boolean;
    procedure addEntitlement(value : String);
    function hasEntitlement(value : String) : Boolean;

    function bestName : String;
  end;


implementation

{ TSCIMObject }

constructor TSCIMObject.Create(FJson: TJsonObject);
begin
  inherited create;
  self.FJson := FJson;
end;

destructor TSCIMObject.Destroy;
begin
  FCreatedUTC.free;
  FLastModifiedUTC.free;
  FCreated.Free;
  FLastModified.Free;
  FJson.Free;
  inherited;
end;

function TSCIMObject.GetCreated: TDateAndTime;
begin
  if FJson.has('meta') and FJson.obj['meta'].has('created') then
  begin
    if FCreated = nil then
      FCreated := TDateAndTime.create;
    FCreated.AsXML := FJson.obj['meta']['created'];
  end
  else if FCreated <> nil then
  begin
    FCreated.free;
    FCreated := nil;
  end;
  result := FCreated;
end;

function TSCIMObject.GetCreatedUTC: TDateAndTime;
begin
  FCreatedUTC.free;
  FCreatedUTC := FCreated.AsUTC;
end;

function TSCIMObject.GetExternalId: String;
begin
  result := FJson['externalId'];
end;

function TSCIMObject.GetId: String;
begin
  result := FJson['id'];
end;

function TSCIMObject.GetLastModified: TDateAndTime;
begin
  if FJson.has('meta') and FJson.obj['meta'].has('created') then
  begin
    if FLastModified = nil then
      FLastModified := TDateAndTime.create;
    FLastModified.AsXML := FJson.obj['meta']['created'];
  end
  else if FLastModified <> nil then
  begin
    FLastModified.free;
    FLastModified := nil;
  end;
  result := FLastModified;
end;

function TSCIMObject.GetLastModifiedUTC: TDateAndTime;
begin
  FLastModifiedUTC.free;
  FLastModifiedUTC := FLastModified.AsUTC;
end;

function TSCIMObject.GetLocation: String;
begin
  result := FJson.obj['meta']['location'];
end;

function TSCIMObject.GetResourceType: String;
begin
  result := FJson.obj['meta']['resourceType'];
end;

function TSCIMObject.GetVersion: String;
begin
  result := FJson.obj['meta']['version'];
end;

procedure TSCIMObject.SetCreated(const Value: TDateAndTime);
begin
  FCreated.Free;
  FCreated := Value;
  if not FJson.has('meta') then
    FJson.obj['meta'] := TJsonObject.Create;
  FJson.obj['meta']['created'] := FCreated.AsXML;
end;

procedure TSCIMObject.SetExternalId(const Value: String);
begin
  if Value = '' then
  begin
    if FJson.has('externalId') then
      FJson.clear('externalId');
  end
  else
  begin
    FJson['externalId'] := Value;
  end;
end;

procedure TSCIMObject.SetId(const Value: String);
begin
  if Value = '' then
  begin
    if FJson.has('id') then
      FJson.clear('id');
  end
  else
  begin
    FJson['id'] := Value;
  end;
end;

procedure TSCIMObject.SetLastModified(const Value: TDateAndTime);
begin
  FLastModified.Free;
  FLastModified := Value;
  if Value = nil then
  begin
    if FJson.has('meta') and FJson.has('lastModified') then
      FJson.obj['meta'].clear('lastModified');
    if FJson.obj['meta'].properties.IsEmpty then
      FJson.clear('meta');
  end
  else
  begin
    if not FJson.has('meta') then
      FJson.obj['meta'] := TJsonObject.Create;
    FJson.obj['meta']['created'] := FLastModified.AsXML;
  end;
end;

procedure TSCIMObject.SetLocation(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('meta') and FJson.has('location') then
      FJson.obj['meta'].clear('location');
    if FJson.obj['meta'].properties.IsEmpty then
      FJson.clear('meta');
  end
  else
  begin
    if not FJson.has('meta') then
      FJson.obj['meta'] := TJsonObject.Create;
    FJson.obj['meta']['location'] := Value;
  end;
end;

procedure TSCIMObject.SetResourceType(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('meta') and FJson.has('resourceType') then
      FJson.obj['meta'].clear('resourceType');
    if FJson.obj['meta'].properties.IsEmpty then
      FJson.clear('meta');
  end
  else
  begin
    if not FJson.has('meta') then
      FJson.obj['meta'] := TJsonObject.Create;
    FJson.obj['meta']['resourceType'] := Value;
  end;
end;

procedure TSCIMObject.SetVersion(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('meta') and FJson.has('version') then
      FJson.obj['meta'].clear('version');
    if FJson.obj['meta'].properties.IsEmpty then
      FJson.clear('meta');
  end
  else
  begin
    if not FJson.has('meta') then
      FJson.obj['meta'] := TJsonObject.Create;
    FJson.obj['meta']['version'] := Value;
  end;
end;

function TSCIMObject.hasSchema(s: String): boolean;
var
  i : integer;
  arr : TJsonArray;
begin
  result := false;
  arr := FJson.arr['schemas'];
  for i := 0 to arr.Count do
    result := result or (arr.Value[i] = s);
end;

procedure TSCIMObject.checkSchema(s: String);
begin
  if not hasSchema(s) then
    raise ESCIMException.Create(400, 'BAD REQUEST', 'invalidValue', 'Unable to find the expected schema '+s);
end;


{ TSCIMUser }

function TSCIMUser.AddEmail(value, type_: String): TSCIMContact;
begin
  result := TSCIMContact.Create(FJson.forcearr['emails'].addObject.Link);
  Emails.Add(result);
  result.Value := value;
  result.Type_ := type_;
end;

procedure TSCIMUser.addEntitlement(value: String);
begin
  FJson.forcearr['entitlements'].add(value);
end;

function TSCIMUser.bestName: String;
begin
  result := DisplayName;
  if result = '' then
    result := formattedName;
  if result = '' then
    result := username;
end;

procedure TSCIMUser.check;
begin
  checkSchema('urn:scim:schemas:core:2.0:User');
end;


procedure TSCIMUser.copyFrom(source: TSCIMUser);
var
  i : integer;
  n : string;
begin
  // copy from the source unless exists in the dest
  for i := 0 to source.FJson.properties.Count - 1 do
  begin
    n := source.FJson.properties.KeyByIndex[i];
    if not FJson.has(n) then
      FJson.properties.Add(n, source.FJson.properties[n].Link);
  end;

  // now delete anything in the dest that is null
  for i := FJson.properties.Count - 1 downto 0 do
    if FJson.properties.ValueByIndex[i] is TJsonNull then
      FJson.properties.DeleteByIndex(i);
end;

constructor TSCIMUser.Create(FJson: TJsonObject);
begin
  inherited Create(FJson);
  if not hasSchema('urn:scim:schemas:core:2.0:User') then
    FJson.forceArr['schemas'].add('urn:scim:schemas:core:2.0:User');
end;

destructor TSCIMUser.Destroy;
begin
  FEmails.free;
  inherited;
end;

function TSCIMUser.GetAddresses: TSCIMAddressList;
var
  arr : TJsonArray;
  i : integer;
begin
  if FAddresses = nil then
  begin
    FAddresses := TSCIMAddressList.Create;
    if (FJson.has('addresses')) then
    begin
      arr := FJson.arr['addresses'];
      for i := 0 to arr.Count - 1 do
        FAddresses.add(TSCIMAddress.Create(arr.Obj[i].Link));
    end;
  end;
  result := FAddresses;
end;

function TSCIMUser.GetDisplayName: String;
begin
  result := FJson['displayName'];
end;

function TSCIMUser.GetEmails: TSCIMContactList;
var
  arr : TJsonArray;
  i : integer;
begin
  if FEmails = nil then
  begin
    FEmails := TSCIMContactList.Create;
    if (FJson.has('emails')) then
    begin
      arr := FJson.arr['emails'];
      for i := 0 to arr.Count - 1 do
        FEmails.add(TSCIMContact.Create(arr.Obj[i].Link));
    end;
  end;
  result := FEmails;
end;

function TSCIMUser.GetEntitlement(i: integer): String;
begin
  result := json.arr['entitlements'].Value[i];
end;

function TSCIMUser.GetEntitlementCount: integer;
begin
  if json.has('entitlements') then
    result := json.arr['entitlements'].Count
  else
    result := 0;
end;

function TSCIMUser.GetFamilyName: String;
begin
  result := FJson.obj['name']['familyName'];
end;

function TSCIMUser.GetGivenName: String;
begin
  result := FJson.obj['name']['givenName'];
end;

function TSCIMUser.GetIms: TSCIMContactList;
var
  arr : TJsonArray;
  i : integer;
begin
  if FIms = nil then
  begin
    FIMs := TSCIMContactList.Create;
    if (FJson.has('ims')) then
    begin
      arr := FJson.arr['ims'];
      for i := 0 to arr.Count - 1 do
        FIMs.add(TSCIMContact.Create(arr.Obj[i].Link));
    end;
  end;
  result := FIms;
end;

function TSCIMUser.GetLocale: String;
begin
  result := FJson['locale'];
end;

function TSCIMUser.GetMiddleName: String;
begin
  result := FJson.obj['name']['middleName'];
end;

function TSCIMUser.GetName: String;
begin
  result := FJson.obj['name']['formatted'];
end;

function TSCIMUser.GetNickName: String;
begin
  result := FJson['nickName'];
end;

function TSCIMUser.GetPassword: String;
begin
  result := FJson['password'];
end;

function TSCIMUser.GetPhoneNums: TSCIMContactList;
var
  arr : TJsonArray;
  i : integer;
begin
  if FPhoneNums = nil then
  begin
    FPhoneNums := TSCIMContactList.Create;
    if (FJson.has('phoneNums')) then
    begin
      arr := FJson.arr['phoneNums'];
      for i := 0 to arr.Count - 1 do
        FPhoneNums.add(TSCIMContact.Create(arr.Obj[i].Link));
    end;
  end;
  result := FPhoneNums;
end;

function TSCIMUser.GetPreferredLanguage: String;
begin
  result := FJson['preferredLanguage'];
end;

function TSCIMUser.GetPrefix: String;
begin
  result := FJson.obj['name']['honorificPrefix'];
end;

function TSCIMUser.GetProfileUrl: String;
begin
  result := FJson['profileUrl'];
end;

function TSCIMUser.GetSuffix: String;
begin
  result := FJson.obj['name']['honorificSuffix'];
end;

function TSCIMUser.GetTimezone: String;
begin
  result := FJson['timezone'];
end;

function TSCIMUser.GetTitle: String;
begin
  result := FJson['title'];
end;

function TSCIMUser.GetUsername: String;
begin
  result := FJson['userName'];
end;

function TSCIMUser.GetUserType: String;
begin
  result := FJson['userType'];
end;

function TSCIMUser.hasEmail(value: String): boolean;
var
  i : integer;
begin
  result := false;
  for i := 0 to emails.Count - 1 do
    if emails[i].Value = value then
      result := true;
end;

function TSCIMUser.hasEntitlement(value: String): Boolean;
var
  i : integer;
begin
  result := false;
  if entitlementCount > 0 then
    for i := 0 to json.arr['entitlements'].Count - 1 do
      result := result or (json.arr['entitlements'].Value[i] = value);
end;

function TSCIMUser.Link: TSCIMUser;
begin
  result := TSCIMUser(Inherited Link);
end;

procedure TSCIMUser.SetDisplayName(const Value: String);
begin
  if (value <> '') then
    FJson['displayName'] := value
  else if FJson.has('displayName') then
    FJson.clear('displayName');
end;


procedure TSCIMUser.SetFamilyName(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('name') and FJson.has('familyName') then
      FJson.obj['name'].clear('familyName');
    if FJson.obj['name'].properties.IsEmpty then
      FJson.clear('name');
  end
  else
  begin
    if not FJson.has('name') then
      FJson.obj['name'] := TJsonObject.Create;
    FJson.obj['name']['familyName'] := Value;
  end;
end;

procedure TSCIMUser.SetGivenName(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('name') and FJson.has('givenName') then
      FJson.obj['name'].clear('givenName');
    if FJson.obj['name'].properties.IsEmpty then
      FJson.clear('name');
  end
  else
  begin
    if not FJson.has('name') then
      FJson.obj['name'] := TJsonObject.Create;
    FJson.obj['name']['givenName'] := Value;
  end;
end;

procedure TSCIMUser.SetLocale(const Value: String);
begin
  if (value <> '') then
    FJson['locale'] := value
  else if FJson.has('locale') then
    FJson.clear('locale');
end;

procedure TSCIMUser.SetMiddleName(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('name') and FJson.has('middleName') then
      FJson.obj['name'].clear('middleName');
    if FJson.obj['name'].properties.IsEmpty then
      FJson.clear('name');
  end
  else
  begin
    if not FJson.has('name') then
      FJson.obj['name'] := TJsonObject.Create;
    FJson.obj['name']['middleName'] := Value;
  end;
end;

procedure TSCIMUser.SetName(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('name') and FJson.has('formatted') then
      FJson.obj['name'].clear('formatted');
    if FJson.obj['name'].properties.IsEmpty then
      FJson.clear('name');
  end
  else
  begin
    if not FJson.has('name') then
      FJson.obj['name'] := TJsonObject.Create;
    FJson.obj['name']['formatted'] := Value;
  end;
end;

procedure TSCIMUser.SetNickName(const Value: String);
begin
  if (value <> '') then
    FJson['nickName'] := value
  else if FJson.has('nickName') then
    FJson.clear('nickName');
end;

procedure TSCIMUser.SetPassword(const Value: String);
begin
  if (value <> '') then
    FJson['password'] := value
  else if FJson.has('password') then
    FJson.clear('password');
end;

procedure TSCIMUser.SetPreferredLanguage(const Value: String);
begin
  if (value <> '') then
    FJson['preferredLanguage'] := value
  else if FJson.has('preferredLanguage') then
    FJson.clear('preferredLanguage');
end;

procedure TSCIMUser.SetPrefix(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('name') and FJson.has('honorificPrefix') then
      FJson.obj['name'].clear('honorificPrefix');
    if FJson.obj['name'].properties.IsEmpty then
      FJson.clear('name');
  end
  else
  begin
    if not FJson.has('name') then
      FJson.obj['name'] := TJsonObject.Create;
    FJson.obj['name']['familyName'] := Value;
  end;
end;

procedure TSCIMUser.SetProfileUrl(const Value: String);
begin
  if (value <> '') then
    FJson['profileUrl'] := value
  else if FJson.has('profileUrl') then
    FJson.clear('profileUrl');
end;

procedure TSCIMUser.SetSuffix(const Value: String);
begin
  if (value = '') then
  begin
    if FJson.has('name') and FJson.has('honorificSuffix') then
      FJson.obj['name'].clear('honorificSuffix');
    if FJson.obj['name'].properties.IsEmpty then
      FJson.clear('name');
  end
  else
  begin
    if not FJson.has('name') then
      FJson.obj['name'] := TJsonObject.Create;
    FJson.obj['name']['honorificSuffix'] := Value;
  end;
end;

procedure TSCIMUser.SetTimezone(const Value: String);
begin
  if (value <> '') then
    FJson['timezone'] := value
  else if FJson.has('timezone') then
    FJson.clear('timezone');
end;

procedure TSCIMUser.SetTitle(const Value: String);
begin
  if (value <> '') then
    FJson['title'] := value
  else if FJson.has('title') then
    FJson.clear('title');
end;

procedure TSCIMUser.SetUsername(const Value: String);
begin
  if (value <> '') then
    FJson['userName'] := value
  else if FJson.has('userName') then
    FJson.clear('userName');
end;

procedure TSCIMUser.SetUserType(const Value: String);
begin
  if (value <> '') then
    FJson['userType'] := value
  else if FJson.has('userType') then
    FJson.clear('userType');
end;

{ TSCIMContact }

constructor TSCIMContact.Create(FJson: TJsonObject);
begin
  inherited create;
  self.FJson := FJson;
end;

destructor TSCIMContact.Destroy;
begin
  FJson.free;
  inherited;
end;

function TSCIMContact.GetPrimary: Boolean;
begin
  result := StrToBoolDef(FJson['primary'], false);
end;

function TSCIMContact.GetType: String;
begin
  result := FJson['type'];
end;

function TSCIMContact.GetValue: String;
begin
  result := FJson['value'];
end;

procedure TSCIMContact.SetPrimary(const Value: Boolean);
begin
  if value then
    FJson['primary'] := 'true'
  else
    FJson['primary'] := 'false';
end;

procedure TSCIMContact.SetType(const Value: String);
begin
  if value <> '' then
    FJson['type'] := value
  else if FJson.has('type') then
    FJson.clear('type');
end;

procedure TSCIMContact.SetValue(const Value: String);
begin
  FJson['value'] := value;
end;

{ TSCIMContactList }

function TSCIMContactList.GetContact(index: integer): TSCIMContact;
begin
  result := TSCIMContact(ObjectByindex[index]);
end;

function TSCIMContactList.ItemClass: TAdvObjectClass;
begin
  result := TSCIMContact;
end;

{ TSCIMAddress }

constructor TSCIMAddress.Create(FJson: TJsonObject);
begin
  inherited create;
  self.FJson := FJson;
end;

destructor TSCIMAddress.Destroy;
begin
  FJson.free;
  inherited;
end;

function TSCIMAddress.GetCountry: String;
begin
  result := FJson['country'];
end;

function TSCIMAddress.GetFormatted: String;
begin
  result := FJson['formatted'];
end;

function TSCIMAddress.GetLocality: String;
begin
  result := FJson['locality'];
end;

function TSCIMAddress.GetPostalCode: String;
begin
  result := FJson['postalCode'];
end;

function TSCIMAddress.GetPrimary: Boolean;
begin
  result := StrToBoolDef(FJson['primary'], false);
end;

function TSCIMAddress.GetRegion: String;
begin
  result := FJson['region'];
end;

function TSCIMAddress.GetStreetAddress: String;
begin
  result := FJson['streetAddress'];
end;

function TSCIMAddress.GetType: String;
begin
  result := FJson['type'];
end;

procedure TSCIMAddress.SetCountry(const Value: String);
begin
  FJson['country'] := value;
end;

procedure TSCIMAddress.SetFormatted(const Value: String);
begin
  FJson['formatted'] := value;
end;

procedure TSCIMAddress.SetLocality(const Value: String);
begin
  FJson['locality'] := value;
end;

procedure TSCIMAddress.SetPostalCode(const Value: String);
begin
  FJson['postalCode'] := value;
end;

procedure TSCIMAddress.SetPrimary(const Value: Boolean);
begin
  if value then
    FJson['primary'] := 'true'
  else
    FJson['primary'] := 'false';
end;

procedure TSCIMAddress.SetRegion(const Value: String);
begin
  FJson['region'] := value;
end;

procedure TSCIMAddress.SetStreetAddress(const Value: String);
begin
  FJson['streetAddress'] := value;
end;

procedure TSCIMAddress.SetType(const Value: String);
begin
  FJson['type'] := value;
end;

{ TSCIMAddressList }

function TSCIMAddressList.GetAddress(index: integer): TSCIMAddress;
begin
  result := TSCIMAddress(ObjectByindex[index]);
end;

function TSCIMAddressList.ItemClass: TAdvObjectClass;
begin
  result := TSCIMAddress;
end;


{ ESCIMException }

constructor ESCIMException.Create(status: Integer; statusText, scimType, message: String);
begin
  inherited Create(message);
  FStatus := status;
  FStatusText := statusText;
  FScimType := scimType;
end;


end.
