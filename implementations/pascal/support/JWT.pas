unit JWT;

interface

uses
  SysUtils, EncdDecd, Classes,
  IdSSLOpenSSLHeaders, IdHMACSHA1,

  EncodeSupport, BytesSupport, StringSupport,
  AdvObjects, AdvObjectLists,
  JSON, HMAC, libeay32;

Type
  // 1st, JWK

  TJWK = class (TAdvObject)
  private
    FObj : TJsonObject;
    function GetExponent: TBytes;
    function GetId: String;
    function GetKey: TBytes;
    function GetKeyType: String;
    function GetPrivateKey: TBytes;
    function GetPublicKey: TBytes;
    procedure SetExponent(const Value: TBytes);
    procedure SetId(const Value: String);
    procedure SetKey(const Value: TBytes);
    procedure SetKeyType(const Value: String);
    procedure setObj(const Value: TJsonObject);
    procedure SetPrivateKey(const Value: TBytes);
    procedure SetPublicKey(const Value: TBytes);
    function GetHasExponent: boolean;
    function GetHasKey: boolean;
    function GetHasPrivateKey: boolean;
    function GetHasPublicKey: boolean;
  public
    constructor create(obj : TJsonObject); overload;
    constructor create(pkey : PRSA); overload;
    destructor Destroy; override;
    Property obj : TJsonObject read FObj write setObj;

    property keyType : String read GetKeyType write SetKeyType;
    property id : String read GetId write SetId;

    property key : TBytes read GetKey write SetKey;
    property publicKey : TBytes read GetPublicKey write SetPublicKey;
    property exponent : TBytes read GetExponent write SetExponent;
    property privateKey : TBytes read GetPrivateKey write SetPrivateKey;

    property hasKey : boolean read GetHasKey;
    property hasPublicKey : boolean read GetHasPublicKey;
    property hasExponent : boolean read GetHasExponent;
    property hasPrivateKey : boolean read GetHasPrivateKey;

    procedure clearKey;
    procedure clearPublicKey;
    procedure clearExponent;
    procedure clearPrivateKey;
    function Load(privkey : boolean) : PRSA;
  end;

  TJWKList = class (TAdvObjectList)
  private
    FObj: TJsonObject;
    function GetKey(index: integer): TJWK;
    procedure Setkey(index: integer; const Value: TJWK);
    procedure setObj(const Value: TJsonObject);
  protected
    function ItemClass : TAdvObjectClass; override;
  public
    constructor create(_obj : TJsonObject); overload;
    destructor Destroy; override;
    Property obj : TJsonObject read FObj write setObj;

    property Key[index : integer] : TJWK read GetKey write Setkey; default;
  end;

  TJWTAlgorithm = (jwt_none, jwt_hmac_sha256, jwt_hmac_rsa256);


{

 +---------------+------------------------------+--------------------+
   | alg Parameter | Digital Signature or MAC     | Implementation     |
   | Value         | Algorithm                    | Requirements       |
   +---------------+------------------------------+--------------------+
   | HS256         | HMAC using SHA-256           | Required           |
   | HS384         | HMAC using SHA-384           | Optional           |
   | HS512         | HMAC using SHA-512           | Optional           |
   | RS256         | RSASSA-PKCS-v1_5 using       | Recommended        |
   |               | SHA-256                      |                    |
   | RS384         | RSASSA-PKCS-v1_5 using       | Optional           |
   |               | SHA-384                      |                    |
   | RS512         | RSASSA-PKCS-v1_5 using       | Optional           |
   |               | SHA-512                      |                    |
   | ES256         | ECDSA using P-256 and        | Recommended+       |
   |               | SHA-256                      |                    |
   | ES384         | ECDSA using P-384 and        | Optional           |
   |               | SHA-384                      |                    |
   | ES512         | ECDSA using P-521 and        | Optional           |
   |               | SHA-512                      |                    |
   | PS256         | RSASSA-PSS using SHA-256 and | Optional           |
   |               | MGF1 with SHA-256            |                    |
   | PS384         | RSASSA-PSS using SHA-384 and | Optional           |
   |               | MGF1 with SHA-384            |                    |
   | PS512         | RSASSA-PSS using SHA-512 and | Optional           |
   |               | MGF1 with SHA-512            |                    |
   | none          | No digital signature or MAC  | Optional           |
   |               | performed                    |                    |
   +---------------+------------------------------+--------------------+
}

  TJWT = class (TAdvObject)
  private
    FHeader : TJsonObject;
    FPayLoad : TJsonObject;

    procedure setHeader(const Value: TJsonObject);
    procedure setPayload(const Value: TJsonObject);

    function GetaddressCountry: string;
    function GetaddressFormatted: string;
    function GetaddressLocality: string;
    function GetaddressPostCode: string;
    function GetaddressRegion: string;
    function GetaddressStreet: string;
    function Getaudience: string;
    function Getbirthdate: string;
    function Getemail: string;
    function GetemailVerified: boolean;
    function Getexpires: TDateTime;
    function GetfamilyName: string;
    function Getgender: string;
    function GetgivenName: string;
    function Getid: string;
    function GetissuedAt: TDateTime;
    function Getissuer: string;
    function Getlocale: string;
    function GetmiddleName: string;
    function Getname: string;
    function GetnickName: string;
    function GetnotBefore: TDateTime;
    function Getphone: string;
    function Getphone_verified: boolean;
    function Getpicture: string;
    function GetpreferredName: string;
    function Getprofile: string;
    function Getsubject: string;
    function GettimeZone: string;
    function GetupdatedAt: TDateTime;
    function Getwebsite: string;
    procedure SetaddressCountry(Value: string);
    procedure SetaddressFormatted(Value: string);
    procedure SetaddressLocality(Value: string);
    procedure SetaddressPostCode(Value: string);
    procedure SetaddressRegion(Value: string);
    procedure SetaddressStreet(Value: string);
    procedure Setaudience(Value: string);
    procedure Setbirthdate(Value: string);
    procedure Setemail(Value: string);
    procedure SetemailVerified(Value: boolean);
    procedure Setexpires(Value: TDateTime);
    procedure SetfamilyName(Value: string);
    procedure Setgender(Value: string);
    procedure SetgivenName(Value: string);
    procedure Setid(Value: string);
    procedure SetissuedAt(Value: TDateTime);
    procedure Setissuer(Value: string);
    procedure Setlocale(Value: string);
    procedure SetmiddleName(Value: string);
    procedure Setname(Value: string);
    procedure SetnickName(Value: string);
    procedure SetnotBefore(Value: TDateTime);
    procedure Setphone(Value: string);
    procedure Setphone_verified(Value: boolean);
    procedure Setpicture(Value: string);
    procedure SetpreferredName(Value: string);
    procedure Setprofile(Value: string);
    procedure Setsubject(Value: string);
    procedure SettimeZone(Value: string);
    procedure SetupdatedAt(Value: TDateTime);
    procedure Setwebsite(Value: string);
  public
    constructor create; override;
    constructor create(header, payload : TJsonObject); overload;
    destructor Destroy; override;

    // the header is provided to get/set extra properties beyond those used in packing/unpacking.
    // you don't need to do anything with it if you don't use extra properties
    Property header : TJsonObject read FHeader write setHeader;
    Property payload : TJsonObject read FPayload write setPayload;

    // information in the JWT
    // from JWT itself
    property issuer : string read Getissuer write Setissuer; // 'iss'
    property subject : string read Getsubject write Setsubject;  // 'sub' Identifier for the End-User at the Issuer.
    property audience : string read Getaudience write Setaudience; // 'aud'
    property expires : TDateTime read Getexpires write Setexpires; // 'exp'
    property notBefore : TDateTime read GetnotBefore write SetnotBefore; // 'nbf'
    property issuedAt : TDateTime read GetissuedAt write SetissuedAt; // 'ist'
    property id : string read Getid write Setid; // 'jti'

    // from openid:
    property name : string  read Getname write Setname; // 'name' End-User's full name in displayable form including all name parts, possibly including titles and suffixes, ordered according to the End-User's locale and preferences.
    property givenName : string read GetgivenName write SetgivenName; // 'given_name' Given name(s) or first name(s) of the End-User. Note that in some cultures, people can have multiple given names; all can be present, with the names being separated by space characters.
    property familyName : string read GetfamilyName  write SetfamilyName; // 'family_name' Surname(s) or last name(s) of the End-User. Note that in some cultures, people can have multiple family names or no family name; all can be present, with the names being separated by space characters.
    property middleName : string read GetmiddleName  write SetmiddleName; // 'middle_name' Middle name(s) of the End-User. Note that in some cultures, people can have multiple middle names; all can be present, with the names being separated by space characters. Also note that in some cultures, middle names are not used.
    property nickName : string read GetnickName  write SetnickName;	// 'nickname' Casual name of the End-User that may or may not be the same as the given_name. For instance, a nickname value of Mike might be returned alongside a given_name value of Michael.
    property preferredName : string read GetpreferredName write SetpreferredName;	// 'preferred_username' Shorthand name by which the End-User wishes to be referred to at the RP, such as janedoe or j.doe. This value MAY be any valid JSON string including special characters such as @, /, or whitespace. The RP MUST NOT rely upon this value being unique, as discussed in Section 5.7.
    property profile : string read Getprofile write Setprofile; // 'profile' URL of the End-User's profile page. The contents of this Web page SHOULD be about the End-User.
    property picture : string read Getpicture write Setpicture; // 'picture' URL of the End-User's profile picture. This URL MUST refer to an image file (for example, a PNG, JPEG, or GIF image file), rather than to a Web page containing an image. Note that this URL SHOULD specifically reference a profile photo of the End-User suitable for displaying when describing the End-User, rather than an arbitrary photo taken by the End-User.
    property website : string read Getwebsite write Setwebsite; // 'website' URL of the End-User's Web page or blog. This Web page SHOULD contain information published by the End-User or an organization that the End-User is affiliated with.
    property email : string read Getemail write Setemail; // 'email' End-User's preferred e-mail address. Its value MUST conform to the RFC 5322 [RFC5322] addr-spec syntax. The RP MUST NOT rely upon this value being unique, as discussed in Section 5.7.
    property emailVerified : boolean  read GetemailVerified write SetemailVerified; // 'email_verified' True if the End-User's e-mail address has been verified; otherwise false. When this Claim Value is true, this means that the OP took affirmative steps to ensure that this e-mail address was controlled by the End-User at the time the verification was performed. The means by which an e-mail address is verified is context-specific, and dependent upon the trust framework or contractual agreements within which the parties are operating.
    property gender : string read Getgender  write Setgender; // 'gender' End-User's gender. Values defined by this specification are female and male. Other values MAY be used when neither of the defined values are applicable.
    property birthdate : string read Getbirthdate write Setbirthdate; // 'birthdate' End-User's birthday, represented as an ISO 8601:2004 [ISO8601‑2004] YYYY-MM-DD format. The year MAY be 0000, indicating that it is omitted. To represent only the year, YYYY format is allowed. Note that depending on the underlying platform's date related function, providing just year can result in varying month and day, so the implementers need to take this factor into account to correctly process the dates.
    property timeZone : string read GettimeZone  write SettimeZone;	// 'zoneinfo' String from zoneinfo [zoneinfo] time zone database representing the End-User's time zone. For example, Europe/Paris or America/Los_Angeles.
    property locale : string read Getlocale  write Setlocale;	// 'locale' End-User's locale, represented as a BCP47 [RFC5646] language tag. This is typically an ISO 639-1 Alpha-2 [ISO639‑1] language code in lowercase and an ISO 3166-1 Alpha-2 [ISO3166‑1] country code in uppercase, separated by a dash. For example, en-US or fr-CA. As a compatibility note, some implementations have used an underscore as the separator rather than a dash, for example, en_US; Relying Parties MAY choose to accept this locale syntax as well.
    property phone : string read Getphone write Setphone; // 'phone_number' End-User's preferred telephone number. E.164 [E.164] is RECOMMENDED as the format of this Claim, for example, +1 (425) 555-1212 or +56 (2) 687 2400. If the phone number contains an extension, it is RECOMMENDED that the extension be represented using the RFC 3966 [RFC3966] extension syntax, for example, +1 (604) 555-1234;ext=5678.
    property phone_verified : boolean  read Getphone_verified  write Setphone_verified; // 'phone_number_verified' True if the End-User's phone number has been verified; otherwise false. When this Claim Value is true, this means that the OP took affirmative steps to ensure that this phone number was controlled by the End-User at the time the verification was performed. The means by which a phone number is verified is context-specific, and dependent upon the trust framework or contractual agreements within which the parties are operating. When true, the phone_number Claim MUST be in E.164 format and any extensions MUST be represented in RFC 3966 format.
    property updatedAt : TDateTime read GetupdatedAt  write SetupdatedAt; // 'updated_at' Time the End-User's information was last updated. Its value is a JSON number representing the number of seconds from 1970-01-01T0:0:0Z as measured in UTC until the date/time.' +                                  '
    // 'address' object	 End-User's preferred postal address. The value of the address member is a JSON [RFC4627] structure containing some or all of the members defined in Section 5.1.1.
    property addressFormatted : string read GetaddressFormatted write SetaddressFormatted; // 'address.formatted'  Full mailing address, formatted for display or use on a mailing label. This field MAY contain multiple lines, separated by newlines. Newlines can be represented either as a carriage return/line feed pair ("\r\n") or as a single line feed character ("\n").
    property addressStreet : string read GetaddressStreet write SetaddressStreet; // 'address.street_address'  Full street address component, which MAY include house number, street name, Post Office Box, and multi-line extended street address information. This field MAY contain multiple lines, separated by newlines. Newlines can be represented either as a carriage return/line feed pair ("\r\n") or as a single line feed character ("\n").
    property addressLocality : string read GetaddressLocality write SetaddressLocality; // 'address.locality'  City or locality component.
    property addressRegion : string read GetaddressRegion write SetaddressRegion; // 'address.region'  State, province, prefecture, or region component.
    property addressPostCode : string read GetaddressPostCode write SetaddressPostCode; // 'address.postal_code'  Zip code or postal code component.
    property addressCountry : string read GetaddressCountry write SetaddressCountry; // 'address.country'  Country name component.
  end;

  TJWTUtils = class (TAdvObject)
  private
    class function loadPrivateKey(pemfile, pempassword : AnsiString) : PRSA;
    class function loadPublicKey(pemfile : AnsiString) : PRSA;

    class function Sign_Hmac_SHA256(input : TBytes; key: TJWK) : TBytes;
    class procedure Verify_Hmac_SHA256(input : TBytes; sig : TBytes; key: TJWK);
    class function Sign_Hmac_RSA256(input : TBytes; key: TJWK) : TBytes; overload;
    class function Sign_Hmac_RSA256(input : TBytes; pemfile, pempassword : String) : TBytes; overload;
    class procedure Verify_Hmac_RSA256(input : TBytes; sig : TBytes; header : TJsonObject; keys: TJWKList);
  public

    // general use: pack a JWT using the key speciifed. No key needed if method = none
    class function pack(jwt : TJWT; method : TJWTAlgorithm; key : TJWK) : String; overload;

    // special use - use an existing PEM to sign the JWT
    class function rsa_pack(jwt : TJWT; method : TJWTAlgorithm; pem_file, pem_password : AnsiString) : String; overload;


    // for testing only - need to control whitespace in order to reproduce signatures
    class function pack(header, payload : String; method : TJWTAlgorithm; key : TJWK) : String; overload;

    // read a JWT. if verify is true, at least one key must be provided.
    // the code will pick between multiple keys based on the key id.
    // if no key id is provided in the JWT, there can only be one key
    //
    // todo: what if you don't know?
    class function unpack(token : string; verify : boolean; keys : TJWKList) : TJWT;

    // load the publi key details from the provided filename
    class function loadKeyFromCert(filename : String) : TJWK;
  end;

implementation

procedure check(test: boolean; failmsg: string);
begin
  if not test then
    raise Exception.Create(failmsg);
end;

function JWTBase64URL(b : TBytes) : TBytes; overload;
var
  b64 : AnsiString;
begin
  b64 := EncodeBase64(@b[0], length(b));
  b64 := StringReplace(b64, #13#10, '').TrimRight(['=']);
  b64 := StringReplace(b64, '+', '-', [rfReplaceAll]);
  b64 := StringReplace(b64, '/', '_', [rfReplaceAll]);
  result := AnsiStringAsBytes(b64);
end;

function JWTBase64URLStr(b : TBytes) : String; overload;
var
  b64 : AnsiString;
begin
  b64 := EncodeBase64(@b[0], length(b));
  b64 := StringReplace(b64, #13#10, '').TrimRight(['=']);
  b64 := StringReplace(b64, '+', '-', [rfReplaceAll]);
  b64 := StringReplace(b64, '/', '_', [rfReplaceAll]);
  result := b64;
end;

function JWTBase64URL(s : String) : TBytes; overload;
begin
  result := JWTBase64URL(TEncoding.UTF8.GetBytes(s));
end;

function JWTBase64URLStr(s : String) : String; overload;
begin
  result := JWTBase64URLStr(TEncoding.UTF8.GetBytes(s));
end;

function JWTDeBase64URL(s : String) : TBytes;
begin
  s := s + StringOfChar ('=', (4 - Length (s) mod 4) mod 4);
  s := StringReplace (s, '-', '+', [rfReplaceAll]);
  s := StringReplace (s, '_', '/', [rfReplaceAll]);
  result := DecodeBase64(AnsiString(s));
end;

function JWTDeBase64URLUTF8(s : String) : string;
begin
  result := TEncoding.UTF8.GetString(JWTDeBase64URL(s));
end;


const
  // Sets UnixStartDate to TDateTime of 01/01/1970
  UnixStartDate: TDateTime = 25569.0;

function DateTimeToUnix(ConvDate: TDateTime): Longint;
begin
  Result := Round((ConvDate - UnixStartDate) * 86400);
end;

function UnixToDateTime(USec: Longint): TDateTime;
begin
  Result := (Usec / 86400) + UnixStartDate;
end;

{ TJWK }

constructor TJWK.create(obj: TJsonObject);
begin
  create;
  FObj := obj;
end;

procedure TJWK.clearExponent;
begin
  FObj.clear('e');
end;

procedure TJWK.clearKey;
begin
  FObj.clear('k');
end;

procedure TJWK.clearPrivateKey;
begin
  FObj.clear('d');
end;

procedure TJWK.clearPublicKey;
begin
  FObj.clear('n');
end;

constructor TJWK.create(pkey: PRSA);
var
  b : TBytes;
begin
  create;
  obj := TJsonObject.Create;
  keyType := 'RSA';
  if (pkey.e <> nil) then
  begin
    setlength(b,  BN_num_bytes(pKey.e));
    BN_bn2bin(pkey.e, @b[0]);
    exponent := b;
  end;
  if (pkey.n <> nil) then
  begin
    setlength(b,  BN_num_bytes(pKey.n));
    BN_bn2bin(pkey.n, @b[0]);
    publicKey := b;
  end;
  if (pkey.d <> nil) then
  begin
    setlength(b,  BN_num_bytes(pKey.d));
    BN_bn2bin(pkey.d, @b[0]);
    privateKey := b;
  end;
end;

destructor TJWK.Destroy;
begin
  FObj.Free;
  inherited;
end;

function TJWK.GetExponent: TBytes;
begin
  result := JWTDeBase64URL(FObj.vStr['e']);
end;

function TJWK.GetHasExponent: boolean;
begin
  result := FObj.has('e');
end;

function TJWK.GetHasKey: boolean;
begin
  result := FObj.has('k');
end;

function TJWK.GetHasPrivateKey: boolean;
begin
  result := FObj.has('d');
end;

function TJWK.GetHasPublicKey: boolean;
begin
  result := FObj.has('n');
end;

function TJWK.GetId: String;
begin
  result := FObj.vStr['kid'];
end;

function TJWK.GetKey: TBytes;
begin
  result := JWTDeBase64URL(FObj.vStr['k']);
end;

function TJWK.GetKeyType: String;
begin
  result := FObj.vStr['kty'];
end;

function TJWK.GetPrivateKey: TBytes;
begin
  result := JWTDeBase64URL(FObj.vStr['d']);
end;

function TJWK.GetPublicKey: TBytes;
begin
  result := JWTDeBase64URL(FObj.vStr['n']);
end;

procedure TJWK.SetExponent(const Value: TBytes);
begin
  FObj.vStr['e'] := BytesAsAnsiString(JWTBase64URL(Value));
end;

procedure TJWK.SetId(const Value: String);
begin
  FObj.vStr['kid'] := Value;
end;

procedure TJWK.SetKey(const Value: TBytes);
begin
  FObj.vStr['k'] := BytesAsAnsiString(JWTBase64URL(Value));
end;

procedure TJWK.SetKeyType(const Value: String);
begin
  FObj.vStr['kty'] := Value;
end;

procedure TJWK.setObj(const Value: TJsonObject);
begin
  FObj.Free;
  FObj := Value;
end;

procedure TJWK.SetPrivateKey(const Value: TBytes);
begin
  FObj.vStr['d'] := BytesAsAnsiString(JWTBase64URL(Value));
end;

procedure TJWK.SetPublicKey(const Value: TBytes);
begin
  FObj.vStr['n'] := BytesAsAnsiString(JWTBase64URL(Value));
end;

function TJWK.Load(privKey : boolean): PRSA;
var
  b : TBytes;
begin
  check(keyType = 'RSA', 'RSA Key expected in JWK, but found '+KeyType);
  check(hasExponent, 'RSA Key needs an exponent');
  if (privkey) then
    check(hasPrivateKey, 'RSA Key needs an private key')
  else
    check(hasPublicKey, 'RSA Key needs an public key');

  result := RSA_new;
  b := exponent;
  result.e := BN_bin2bn(@b[0], length(b), nil);
  if hasPublicKey then
  begin
    b := publicKey;
    result.n := BN_bin2bn(@b[0], length(b), nil);
  end;
  if hasPrivateKey then
  begin
    b := privateKey;
    result.d := BN_bin2bn(@b[0], length(b), nil);
  end;
end;

{ TJWKList }

constructor TJWKList.create(_obj: TJsonObject);
begin
  Create;
  Obj := _obj;  // note assign to property
end;

destructor TJWKList.Destroy;
begin
  FObj.Free;
  inherited;
end;

function TJWKList.GetKey(index: integer): TJWK;
begin
  result := TJWK(ObjectByIndex[index]);
end;

function TJWKList.ItemClass: TAdvObjectClass;
begin
  result := TJWK;
end;

procedure TJWKList.Setkey(index: integer; const Value: TJWK);
begin
  ObjectByIndex[index] := value;
end;

procedure TJWKList.setObj(const Value: TJsonObject);
var
  arr : TJsonArray;
  i : integer;
begin
  FObj.Free;
  FObj := nil;
  clear;

  if Value.has('kty') then
  begin
    Add(TJWK.create(value))
  end
  else if Value.has('keys') then
  begin
    FObj := Value;
    arr := FObj.vArr['keys'];
    for i := 0 to arr.Count  - 1 do
      Add(TJWK.create(arr.Obj[i].Link));
  end
  else
    raise Exception.Create('Unable to read Keys');
end;

{ TJWT }

constructor TJWT.create(header, payload: TJsonObject);
begin
  Create;
  FHeader := header;
  FPayload := payload;
end;

constructor TJWT.create;
begin
  inherited create;
  FHeader := TJsonObject.Create('header');
  FPayload := TJsonObject.Create('payload');
end;

destructor TJWT.Destroy;
begin
  FHeader.free;
  FPayload.Free;
  inherited;
end;

procedure TJWT.setHeader(const Value: TJsonObject);
begin
  assert(value <> nil);
  FHeader.Free;
  FHeader := value;
end;

procedure TJWT.setPayload(const Value: TJsonObject);
begin
  assert(value <> nil);
  FPayload.Free;
  FPayload := value;
end;

function TJWT.Getissuer : String;
begin
  result := payload['iss'];
end;

procedure TJWT.Setissuer(value : String);
begin
  payload['iss'] := value;
end;

function TJWT.Getsubject : String;
begin
  result := payload['sub'];
end;

procedure TJWT.Setsubject(value : String);
begin
  payload['sub'] := value;
end;

function TJWT.Getaudience : String;
begin
  result := payload['aud'];
end;

procedure TJWT.Setaudience(value : String);
begin
  payload['aud'] := value;
end;

function TJWT.Getexpires : TDateTime;
begin
  result := UnixToDateTime(StrToIntDef(payload['exp'], 0));
end;

procedure TJWT.Setexpires(value : TDateTime);
begin
  payload['exp'] := IntToStr(DateTimeToUnix(value));
end;

function TJWT.GetnotBefore : TDateTime;
begin
  result := UnixToDateTime(StrToIntDef(payload['nbf'], 0));
end;

procedure TJWT.SetnotBefore(value : TDateTime);
begin
  payload['nbf'] := IntToStr(DateTimeToUnix(value));
end;

function TJWT.GetissuedAt : TDateTime;
begin
  result := UnixToDateTime(StrToIntDef(payload['ist'], 0));
end;

procedure TJWT.SetissuedAt(value : TDateTime);
begin
  payload['ist'] := IntToStr(DateTimeToUnix(value));
end;

function TJWT.Getid : String;
begin
  result := payload['jti'];
end;

procedure TJWT.Setid(value : String);
begin
  if payload = nil then
    payload := TJsonObject.Create('payload');
  payload['jti'] := value;
end;


function TJWT.Getname : string;
begin
  result := payload['name'];
end;

procedure TJWT.Setname(value : string);
begin
  payload['name'] := value;
end;

function TJWT.GetgivenName : string;
begin
  result := payload['given_name'];
end;

procedure TJWT.SetgivenName(value : string);
begin
  payload['given_name'] := value;
end;

function TJWT.GetfamilyName : string;
begin
  result := payload['family_name'];
end;

procedure TJWT.SetfamilyName(value : string);
begin
  payload['family_name'] := value;
end;

function TJWT.GetmiddleName : string;
begin
  result := payload['middle_name'];
end;

procedure TJWT.SetmiddleName(value : string);
begin
  payload['middle_name'] := value;
end;

function TJWT.GetnickName : string;
begin
  result := payload['nickname'];
end;

procedure TJWT.SetnickName(value : string);
begin
  payload['nickname'] := value;
end;

function TJWT.GetpreferredName : string;
begin
  result := payload['preferred_username'];
end;

procedure TJWT.SetpreferredName(value : string);
begin
	  payload['preferred_username'] := value;
  end;

function TJWT.Getprofile : string;
begin
  result := payload['profile'];
end;

procedure TJWT.Setprofile(value : string);
begin
  payload['profile'] := value;
end;

function TJWT.Getpicture : string;
begin
  result := payload['picture'];
end;

procedure TJWT.Setpicture(value : string);
begin
  payload['picture'] := value;
end;

function TJWT.Getwebsite : string;
begin
  result := payload['website'];
end;

procedure TJWT.Setwebsite(value : string);
begin
  payload['website'] := value;
end;

function TJWT.Getemail : string;
begin
  result := payload['email'];
end;

procedure TJWT.Setemail(value : string);
begin
  payload['email'] := value;
end;

function TJWT.GetemailVerified : boolean ;
begin
  result := payload.bool['email_verified'];
end;

procedure TJWT.SetemailVerified(value : boolean );
begin
  payload.bool['email_verified'] := value;
end;

function TJWT.Getgender : string;
begin
  result := payload['gender'];
end;

procedure TJWT.Setgender(value : string);
begin
  payload['gender'] := value;
end;

function TJWT.Getbirthdate : string;
begin
  result := payload['birthdate'];
end;

procedure TJWT.Setbirthdate(value : string);
begin
  payload['birthdate'] := value;
end;

function TJWT.GettimeZone : string;
begin
  result := payload['zoneinfo'];
end;

procedure TJWT.SettimeZone(value : string);
begin
  payload['zoneinfo'] := value;
end;

function TJWT.Getlocale : string;
begin
  result := payload['locale'];
end;

procedure TJWT.Setlocale(value : string);
begin
  payload['locale'] := value;
end;

function TJWT.Getphone : string;
begin
  result := payload['phone_number'];
end;

procedure TJWT.Setphone(value : string);
begin
  payload['phone_number'] := value;
end;

function TJWT.Getphone_verified : boolean ;
begin
  result := payload.bool['phone_number_verified'];
end;

procedure TJWT.Setphone_verified(value : boolean );
begin
  payload.bool['phone_number_verified'] := value;
end;

function TJWT.GetupdatedAt : TDateTime;
begin
  result := UnixToDateTime(StrToIntDef(payload['updated_at'], 0));
end;

procedure TJWT.SetupdatedAt(value : TDateTime);
begin
  payload['updated_at'] := IntToStr(DateTimeToUnix(value));
end;

function TJWT.GetaddressFormatted : string;
begin
  result := payload.forceObj['address']['formatted'];
end;

procedure TJWT.SetaddressFormatted(value : string);
begin
  payload.forceObj['address']['formatted'] := value;
end;

function TJWT.GetaddressStreet : string;
begin
  result := payload.forceObj['address']['street_address'];
end;

procedure TJWT.SetaddressStreet(value : string);
begin
  payload.forceObj['address']['street_address'] := value;
end;

function TJWT.GetaddressLocality : string;
begin
  result := payload.forceObj['address']['locality'];
end;

procedure TJWT.SetaddressLocality(value : string);
begin
  payload.forceObj['address']['locality'] := value;
end;

function TJWT.GetaddressRegion : string;
begin
  result := payload.forceObj['address']['region'];
end;

procedure TJWT.SetaddressRegion(value : string);
begin
  payload.forceObj['address']['region'] := value;
end;

function TJWT.GetaddressPostCode : string;
begin
  result := payload.forceObj['address']['postal_code'];
end;

procedure TJWT.SetaddressPostCode(value : string);
begin
  payload.forceObj['address']['postal_code'] := value;
end;

function TJWT.GetaddressCountry : string;
begin
  result := payload.forceObj['address']['country'];
end;

procedure TJWT.SetaddressCountry(value : string);
begin
  payload.forceObj['address']['country'] := value;
end;

{ TJWTUtils }

class function TJWTUtils.pack(jwt: TJWT; method: TJWTAlgorithm; key: TJWK): String;
var
  input, sig : TBytes;
begin
  jwt.header.vStr['typ'] := 'JWT';
  case method of
    jwt_none : jwt.header.vStr['alg'] := 'none';
    jwt_hmac_sha256 : jwt.header.vStr['alg'] := 'HS256';
    jwt_hmac_rsa256 : jwt.header.vStr['alg'] := 'RS256';
  else
    raise Exception.Create('Unsupported Message Encryption Format');
  end;
  if (key <> nil) and (method <> jwt_none) and (key.id <> '') then
    jwt.header.vStr['kid'] := key.id;

  input := JWTBase64URL(TJSONWriter.write(jwt.header));
  input := BytesAdd(input, Byte('.'));
  input := BytesAdd(input, JWTBase64URL(TJSONWriter.write(jwt.payload)));
  case method of
    jwt_none: SetLength(sig, 0);
    jwt_hmac_sha256: sig := Sign_Hmac_SHA256(input, key);
    jwt_hmac_rsa256: sig := Sign_Hmac_RSA256(input, key);
  end;
  result := BytesAsString(input)+'.'+BytesAsString(JWTBase64URL(sig));
end;


class function TJWTUtils.loadKeyFromCert(filename: String): TJWK;
var
  key : PRSA;
begin
  key := PRSA(LoadPublicKey(filename));
  try
    result := TJWK.create(key);
  finally
    RSA_free(key);
  end;
end;

class function TJWTUtils.loadPrivateKey(pemfile, pempassword: AnsiString): PRSA;
var
  bp: pBIO;
  fn, pp: PAnsiChar;
  pk: PRSA;
begin
  fn := PAnsiChar(pemfile);
  pp := PAnsiChar(pempassword);
  bp := BIO_new(BIO_s_file());
  BIO_read_filename(bp, fn);
  pk := nil;
  result := PEM_read_bio_RSAPrivateKey(bp, @pk, nil, pp);
  if result = nil then
    raise Exception.Create('Private key failure.' + GetSSLErrorMessage);
end;

class function TJWTUtils.loadPublicKey(pemfile: AnsiString) : PRSA;
var
  bp: pBIO;
  fn: PAnsiChar;
  xk : PX509;
  pk : PEVP_PKEY;
begin
  fn := PAnsiChar(pemfile);
  bp := BIO_new(BIO_s_file());
  BIO_read_filename(bp, fn);
  xk := nil;
  xk := PEM_read_bio_X509(bp, @xk, nil, nil);
  if xk = nil then
    raise Exception.Create('Public key failure.' + GetSSLErrorMessage);
  try
    pk := X509_get_pubkey(xk);
    try
      result := EVP_PKEY_get1_RSA(pk);
    finally
      EVP_PKEY_free(pk);
    end;
  finally
    X509_free(xk);
  end;
end;

class function TJWTUtils.pack(header, payload: String; method: TJWTAlgorithm; key : TJWK): String;
var
  input, sig : TBytes;
begin
  case method of
    jwt_hmac_sha256 :
      begin
      check(key <> nil, 'A Key must be provided for HMAC/SHA-256');
      check(key.keyType = 'oct', 'A Symmetric Key must be provided for HMAC/SHA-256');
      end;
    jwt_hmac_rsa256 :
      begin
      check(key <> nil, 'A Key must be provided for HMAC/SHA-256');
      check(key.keyType = 'RSA', 'An RSA Key must be provided for HMAC/SHA-256');
      end;
  else
    raise Exception.Create('Unsupported Message Encryption Format');
  end;

  input := BytesAdd(JWTBase64URL(header),  Byte(Ord('.')));
  input := BytesAdd(input, JWTBase64URL(payload));
  case method of
    jwt_hmac_sha256: sig := Sign_Hmac_SHA256(input, key);
    jwt_hmac_rsa256: sig := Sign_Hmac_RSA256(input, key);
  end;
  result := BytesAsString(input)+'.'+BytesAsString(JWTBase64URL(sig));
end;

class function TJWTUtils.rsa_pack(jwt: TJWT; method: TJWTAlgorithm; pem_file, pem_password: AnsiString): String;
var
  input, sig : TBytes;
begin
  jwt.header.vStr['typ'] := 'JWT';
  case method of
    jwt_hmac_rsa256 : jwt.header.vStr['alg'] := 'RS256';
  else
    raise Exception.Create('Unsupported Message Encryption Format for PEM based signature');
  end;

  input := JWTBase64URL(TJSONWriter.write(jwt.header));
  input := BytesAdd(input, Byte('.'));
  input := BytesAdd(input, JWTBase64URL(TJSONWriter.write(jwt.payload)));
  sig := Sign_Hmac_RSA256(input, pem_file, pem_password);
  result := BytesAsString(input)+'.'+BytesAsString(JWTBase64URL(sig));
end;

class function TJWTUtils.unpack(token: string; verify: boolean; keys: TJWKList): TJWT;
var
  header, payload, sig : String;
  hb, pb : TBytes;
  h, p : TJsonObject;
begin
  StringSplit(token, '.', header, payload);
  StringSplit(payload, '.', payload, sig);
  check(header <> '', 'Header not found reading JWT');
  check(payload <> '', 'payload not found reading JWT');

  hb := JWTDeBase64URL(header);
  pb := JWTDeBase64URL(payload);

  h := TJSONParser.Parse(hb);
  try
    p := TJSONParser.Parse(pb);
    try
      if verify then
      begin
        if (h.vStr['alg'] = 'HS256') then
        begin
          check(keys.count = 1, 'There can only be a single key for HMA/SHA-256');
          verify_hmac_SHA256(AnsiStringAsBytes(AnsiString(header+'.'+payload)), JWTDeBase64URL(sig), keys[0])
        end
        else if (h.vStr['alg'] = 'RS256') then
          verify_hmac_RSA256(AnsiStringAsBytes(AnsiString(header+'.'+payload)), JWTDeBase64URL(sig), h, keys)
        else if (h.vStr['alg'] = 'none') then
          check(sig = '', 'There cannot be a sig when there is no algorithm')
        else
          raise Exception.Create('Unknown Algorithm '+h.vStr['alg']);
      end;
      result := TJWT.create(h.Link, p.Link);
    finally
      p.free;
    end;
  finally
    h.Free;
  end;
end;

class function TJWTUtils.Sign_Hmac_RSA256(input: TBytes; pemfile, pempassword: String): TBytes;
var
  ctx : EVP_MD_CTX;
  keysize : integer;
  len : integer;
  e: integer;
  pkey: PEVP_PKEY;
  rkey: PRSA;
  keys : TJWKList;
begin
  OpenSSL_add_all_algorithms;

  keys := TJWKList.create;
  try
    // 1. Load the RSA private Key from FKey
    rkey := loadPrivateKey(pemfile, pempassword);
    try
      pkey := EVP_PKEY_new;
      try
        check(EVP_PKEY_set1_RSA(pkey, rkey) = 1, 'openSSL EVP_PKEY_set1_RSA failed');

        // 2. do the signing
        keysize := EVP_PKEY_size(pkey);
        SetLength(result, keysize);
        EVP_MD_CTX_init(@ctx);
        try
          EVP_SignInit(@ctx, EVP_sha256);
          check(EVP_SignUpdate(@ctx, @input[0], Length(input)) = 1, 'openSSL EVP_SignUpdate failed');
          check(EVP_SignFinal(@ctx, @result[0], len, pKey) = 1, 'openSSL EVP_SignFinal failed');
          SetLength(result, len);
        finally
          EVP_MD_CTX_cleanup(@ctx);
        end;
      finally
        EVP_PKEY_free(pKey);
      end;
      keys.Add(TJWK.create(rkey));
    finally
      RSA_free(rkey);
    end;

    Verify_Hmac_RSA256(input, result, nil, keys);
  finally
    keys.Free;
  end;
end;

class function TJWTUtils.Sign_Hmac_SHA256(input: TBytes; key: TJWK): TBytes;
begin
  check(key <> nil, 'A Key must be provided for HMAC/SHA-256');
  check(key.keyType = 'oct', 'A Symmetric Key must be provided for HMAC/SHA-256');
  check(key.hasKey, 'A Symmetric Key Value must be provided for HMAC/SHA-256');
  check(Length(key.Key) > 0, 'A valid Symmetric Key Value must be provided for HMAC/SHA-256');
  result := THMACUtils.HMAC(TIdHMACSHA256, Key.key, input);

  Verify_Hmac_SHA256(input, result, key);
end;

class procedure TJWTUtils.Verify_Hmac_SHA256(input, sig: TBytes; key: TJWK);
var
  expected : TBytes;
begin
  check(key <> nil, 'A Key must be provided for HMAC/SHA-256');
  check(key.keyType = 'oct', 'A Symmetric Key must be provided for HMAC/SHA-256');
  check(key.hasKey, 'A Symmetric Key Value must be provided for HMAC/SHA-256');
  check(Length(key.Key) > 0, 'A valid Symmetric Key Value must be provided for HMAC/SHA-256');

  expected := THMACUtils.HMAC(TIdHMACSHA256, key.key, input);
  check(SameBytes(expected, sig),'Signature is not valid (HMAC/SHA-256)');
end;


class function TJWTUtils.Sign_Hmac_RSA256(input: TBytes; key: TJWK): TBytes;
var
  ctx : EVP_MD_CTX;
  keysize : integer;
  len : integer;
  e: integer;
  pkey: PEVP_PKEY;
  rkey: PRSA;
  keys : TJWKList;
begin
  check(key <> nil, 'A key must be provided for RSA/SHA-256');
  OpenSSL_add_all_algorithms;

  // 1. Load the RSA private Key from FKey
  rkey := key.Load(true);
  try
    pkey := EVP_PKEY_new;
    try
      check(EVP_PKEY_set1_RSA(pkey, rkey) = 1, 'openSSL EVP_PKEY_set1_RSA failed');

      // 2. do the signing
      keysize := EVP_PKEY_size(pkey);
      SetLength(result, keysize);
      EVP_MD_CTX_init(@ctx);
      try
        EVP_SignInit(@ctx, EVP_sha256);
        check(EVP_SignUpdate(@ctx, @input[0], Length(input)) = 1, 'openSSL EVP_SignUpdate failed');
        check(EVP_SignFinal(@ctx, @result[0], len, pKey) = 1, 'openSSL EVP_SignFinal failed');
        SetLength(result, len);
      finally
        EVP_MD_CTX_cleanup(@ctx);
      end;
    finally
      EVP_PKEY_free(pKey);
    end;
  finally
    RSA_free(rkey);
  end;

  keys := TJWKList.create;
  try
    keys.Add(key.Link);
    Verify_Hmac_RSA256(input, result, nil, keys);
  finally
    keys.Free;
  end;
end;

class procedure TJWTUtils.Verify_Hmac_RSA256(input, sig: TBytes; header : TJsonObject; keys: TJWKList);
var
  ctx : EVP_MD_CTX;
  e: integer;
  pkey: PEVP_PKEY;
  rkey: PRSA;
  key : TJWK;
  i : integer;
begin
  check((keys <> nil) and (keys.Count > 0), 'No keys provided for RSA/SHA-256 verification');
  OpenSSL_add_all_algorithms;

  key := nil;
  if (header <> nil) and (header['kid'] <> '') then
  begin
    for i := 0 to keys.count - 1 do
      if keys[i].id = header['kid'] then
        key := keys[i];
    check(key <> nil, 'No matching key found for key '+header['kid']);
  end
  else
  begin
    check(keys.count = 1, 'No Key Id specified in JWT, and multiple possible keys specified');
    key := keys[0];
  end;

  // 1. Load the RSA private Key from FKey
  rkey := key.Load(false);
  try
    pkey := EVP_PKEY_new;
    try
      check(EVP_PKEY_set1_RSA(pkey, rkey) = 1, 'openSSL EVP_PKEY_set1_RSA failed');

      // 2. do the signing
      EVP_MD_CTX_init(@ctx);
      try
        EVP_VerifyInit(@ctx, EVP_sha256);
        check(EVP_VerifyUpdate(@ctx, @input[0], Length(input)) = 1, 'openSSL EVP_VerifyUpdate failed');
        e := EVP_VerifyFinal(@ctx, @sig[0], length(sig), pKey);
        check(e = 1, 'Signature is not valid (RSA) (e = '+inttostr(e)+')');
      finally
        EVP_MD_CTX_cleanup(@ctx);
      end;

    finally
      EVP_PKEY_free(pKey);
    end;
  finally
    RSA_free(rkey);
  end;
end;

end.



