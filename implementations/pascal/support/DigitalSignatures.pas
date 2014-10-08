unit DigitalSignatures;

interface

{
Digital Signature support for FHIR Server

introduction:

This is written to support digital signatures in the FHIR server, but
it can be used elsewhere too. Dependencies:
 - Indy 10
 - the support library for FHIR Server
It should compile and work under any unicode version of delphi, but
may be very fragile against changes to openXML. It's currently
developed and tested using XE5

About XML:

The hardest part of digital signatures is actually XML canonicalization.
You can't use MSXML for this - it's too loose with the XML data. Instead,
this source uses the openXML provider. You can't change this here; You
can use msxml elsewhere, but not in here. This is why the interface
is entirely binary, and not based on a DOM.

OpenSSL

Tested against openSSL 1.0.1g. These interfaces are stable, but you shouldn't
use anything older than this anyway. note: you must have deployment infrastructure
for keeping up with openSSL bug changes. You have to initialise OpenSSL correctly
before using:
  LoadEAYExtensions;
  ERR_load_crypto_strings;
  OpenSSL_add_all_algorithms;

Since these only need to be done once, this object doesn't do them

Certificates

you have to generate certificates using openSSL or equivalent, and refer
to them here. You have to nominate a method (DSA/RSA) which matches the
certificate you nominate
}

uses
  SysUtils, Classes,
  IdHashSHA, IdGlobal,
  Xml.xmlintf, Xml.XMLDoc, Xml.adomxmldom,
  BytesSupport, StringSupport, EncodeSupport, EncdDecd,
  AdvObjects, AdvObjectLists,
  XmlBuilder, AdvXmlBuilders,
  IdSSLOpenSSLHeaders, libeay32, HMAC, XMLSupport, InternetFetcher;

Const
  NS_DS = 'http://www.w3.org/2000/09/xmldsig#';

Type
  TSignatureMethod = (sdXmlDSASha1, sdXmlRSASha1, sdXmlDSASha256, sdXmlRSASha256);

  TDigitalSignatureReference = class (TAdvObject)
  private
    FUrl: String;
    FTransforms: TStringList;
    FContent: TBytes;
  public
    constructor Create; override;
    destructor Destroy; override;

    property url: String read FUrl write FUrl;
    property transforms : TStringList read FTransforms;
    property content : TBytes read FContent write FContent;
  end;

  TDigitalSignatureReferenceList = class (TAdvObjectList)
  private
    function getReference(i: integer): TDigitalSignatureReference;

  protected
    function ItemClass : TAdvObjectClass; override;
  public
    property reference[i : integer] : TDigitalSignatureReference read getReference; default;
  end;

  TKeyInfo = class (TAdvObject)
  private
    dsa : PDSA;
    rsa : PRSA;
    function checkSignatureRSA(digest, signature : TBytes; method : TSignatureMethod) : boolean;
    function checkSignatureDSA(digest, signature : TBytes; method : TSignatureMethod) : boolean;
    function checkSignature(digest, signature : TBytes; method : TSignatureMethod) : boolean;
  public
    destructor Destroy; override;
  end;

  TDigitalSigner = class (TAdvObject)
  private
    FkeyFile: AnsiString;
    FKeyPassword: AnsiString;
    FCertFile: AnsiString;

    // attribute / enum methods
    function canoncalizationSet(uri : String) : TXmlCanonicalisationMethodSet;
    function signatureMethod(uri : String) : TSignatureMethod;
    function digestAlgorithmForMethod(method: TSignatureMethod): String;
    function signAlgorithmForMethod(method: TSignatureMethod): String;

    // xml routines
    function loadXml(source: TBytes) : IXMLDocument;
    function canonicaliseXml(method : TXmlCanonicalisationMethodSet; source : TBytes; var dom : IXMLDocument) : TBytes; overload;
    function canonicaliseXml(method : TXmlCanonicalisationMethodSet; dom : IXMLNode) : TBytes; overload;
    function canonicaliseXml(method : TXmlCanonicalisationMethodSet; dom : IXMLDocument) : TBytes; overload;

    // digest and signing routine
    function digest(source : TBytes; method : TSignatureMethod) : TBytes;
    function digestSHA1(source : TBytes) : TBytes;
    function digestSHA256(source : TBytes) : TBytes;
    procedure checkDigest(ref : IXMLNode; doc : IXMLDocument);
    function sign(src: TBytes; method: TSignatureMethod): TBytes;
    function signDSA(src: TBytes; method: TSignatureMethod): TBytes;
    function signRSA(src: TBytes; method: TSignatureMethod): TBytes;

    // key/ certificate management routines
    function LoadKeyInfo(sig : IXmlNode) : TKeyInfo;
    function loadRSAKey: PRSA;
    function loadDSAKey: PDSA;
    procedure AddKeyInfo(sig: IXmlNode; method : TSignatureMethod);

    // source content management
    function resolveReference(url : string) : TBytes;

  public
    // certificate files, for signing
    Property KeyFile : AnsiString read FkeyFile write FkeyFile;
    Property KeyPassword : AnsiString read FKeyPassword write FKeyPassword;

    function signEnveloped(xml : TBytes; method : TSignatureMethod; keyinfo : boolean) : TBytes;
    function signExternal(references : TDigitalSignatureReferenceList; method : TSignatureMethod; keyinfo : boolean) : TBytes;
    function verifySignature(xml : TBytes) : boolean;
  end;

  TDigitalSignatureTests = class (TAdvObject)
  private
    class procedure testFile(filename : String);
    class procedure testGen;
    class procedure testValidate;
  public
    class procedure test;
  end;

implementation

function base64(bytes : TBytes): String;
begin
  result := String(EncodeBase64(@bytes[0], length(bytes))).replace(#13#10, '');
end;

function unbase64(value : String): TBytes;
begin
  result := decodeBase64(AnsiString(value));
end;

procedure check(test: boolean; failmsg: string);
begin
  if not test then
    raise Exception.Create(failmsg);
end;

function StripLeadingZeros(bytes : AnsiString) : AnsiString;
var
  i : integer;
begin
  i := 1;
  while (i < length(bytes)) and (bytes[i] = #0) do
    inc(i);
  result := copy(bytes, i, length(bytes));
end;

function BytesPairToAsn1(bytes : TBytes) : TBytes;
var
  r, s : AnsiString;
begin
  r := StripLeadingZeros(copy(BytesAsAnsiString(bytes), 1, length(bytes) div 2));
  s := StripLeadingZeros(copy(BytesAsAnsiString(bytes), length(bytes) div 2+1, length(bytes) div 2));
  if (r[1] >= #$80) then
    Insert(#0, r, 1);
  if (s[1] >= #$80) then
    Insert(#0, s, 1);
  result := AnsiStringAsBytes(ansichar($30)+ansichar(4+length(r)+length(s))+ansichar(02)+ansichar(length(r))+r+ansichar(02)+ansichar(length(s))+s);
end;

function asn1SigToBytePair(asn1 : TBytes) : TBytes;
var
  sv, r, s : AnsiString;
  l : integer;
begin
  sv := BytesAsAnsiString(asn1);
  if sv[1] <> #$30 then
    raise Exception.Create('Error 1 reading asn1 DER signature');
  if ord(sv[2]) <> length(sv)-2 then
    raise Exception.Create('Error 2 reading asn1 DER signature');
  delete(sv, 1, 2);
  if sv[1] <> #$02 then
    raise Exception.Create('Error 3 reading asn1 DER signature');
  r := copy(sv, 3, ord(sv[2]));
  delete(sv, 1, length(r)+2);
  if sv[1] <> #$02 then
    raise Exception.Create('Error 4 reading asn1 DER signature');
  s := copy(sv, 3, ord(sv[2]));
  delete(sv, 1, length(s)+2);
  if length(sv) <> 0 then
    raise Exception.Create('Error 5 reading asn1 DER signature');

  if (r[2] >= #$80) and (r[1] = #0) then
    delete(r, 1, 1);
  if (s[2] >= #$80) and (s[1] = #0) then
    delete(s, 1, 1);

  if (length(r) <= 20) and (length(s) <= 20) then
    l := 20
  else
    l := 32;
  while length(r) < l do
    insert(#0, r, 1);
  while length(s) < l do
    insert(#0, s, 1);
  result := AnsiStringAsBytes(r+s);
end;


{ TDigitalSigner }

function TDigitalSigner.canoncalizationSet(uri : String) : TXmlCanonicalisationMethodSet;
begin
  if uri = 'http://www.w3.org/TR/2001/REC-xml-c14n-20010315' then
    result := [xcmCanonicalise]
  else if uri = 'http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments' then
    result := [xcmCanonicalise, xcmComments]
  else
    raise Exception.Create('Canonicalization Method '+uri+' is not supported');
end;

function TDigitalSigner.canonicaliseXml(method : TXmlCanonicalisationMethodSet; source: TBytes; var dom : IXMLDocument): TBytes;
var
  xb : TAdvXmlBuilder;
begin
  dom := loadXml(source);

  xb := TAdvXmlBuilder.Create;
  try
    xb.Canonicalise := method;
    xb.Start;
    xb.WriteXmlDocument(dom);
    xb.Finish;
    result := TEncoding.UTF8.GetBytes(xb.Build);
  finally
    xb.Free;
  end;
end;

function TDigitalSigner.canonicaliseXml(method : TXmlCanonicalisationMethodSet; dom: IXMLNode): TBytes;
var
  xb : TAdvXmlBuilder;
begin
  xb := TAdvXmlBuilder.Create;
  try
    xb.Canonicalise := method;
    xb.Start;
    xb.WriteXml(dom, true);
    xb.Finish;
    result := TEncoding.UTF8.GetBytes(xb.Build);
  finally
    xb.Free;
  end;
end;

function TDigitalSigner.canonicaliseXml(method : TXmlCanonicalisationMethodSet; dom: IXMLDocument): TBytes;
var
  xb : TAdvXmlBuilder;
begin
  xb := TAdvXmlBuilder.Create;
  try
    xb.Canonicalise := method;
    xb.Start;
    xb.WriteXmlDocument(dom);
    xb.Finish;
    result := TEncoding.UTF8.GetBytes(xb.Build);
  finally
    xb.Free;
  end;
end;

procedure TDigitalSigner.checkDigest(ref: IXMLNode; doc: IXMLDocument);
var
  bytes, digest : TBytes;
  transforms, transform : IXMLNode;
  bEnv : boolean;
  i : integer;
begin
  //Obtain the data object to be digested. (For example, the signature application may dereference the URI and execute Transforms provided by the signer in the Reference element, or it may obtain the content through other means such as a local cache.)
  if ref.getAttribute('URI') = '' then
    bytes := canonicaliseXml([xcmCanonicalise], doc)
  else
    bytes := resolveReference(ref.getAttribute('URI'));

  // check the transforms
  bEnv := false;
  transforms := getChildNode(ref, 'Transforms', NS_DS);
  if transforms <> nil then
    for i := 0 to transforms.ChildNodes.Count - 1 do
    begin
      transform := transforms.ChildNodes[i];
      if (transform.NodeType = ntElement) and (transform.NodeName = 'Transform') then
        if transform.GetAttribute('Algorithm') = 'http://www.w3.org/2000/09/xmldsig#enveloped-signature' then
          bEnv := true
        else
          raise Exception.Create('Transform '+transform.GetAttribute('Algorithm')+' is not supported');
    end;
  if (doc <> nil) and not bEnv then
    raise Exception.Create('Reference Transform is not http://www.w3.org/2000/09/xmldsig#enveloped-signature');

  //Digest the resulting data object using the DigestMethod specified in its Reference specification.
  if getChildNode(ref, 'DigestMethod', NS_DS).GetAttribute('Algorithm') = 'http://www.w3.org/2000/09/xmldsig#sha1' then
    bytes := digestSHA1(bytes)
  else if getChildNode(ref, 'DigestMethod', NS_DS).GetAttribute('Algorithm') = 'http://www.w3.org/2000/09/xmldsig#sha256' then
    bytes := digestSHA256(bytes)
  else
    raise Exception.Create('Unknown Digest method '+getChildNode(ref, 'DigestMethod', NS_DS).GetAttribute('Algorithm'));
  digest := unbase64(getChildNode(ref, 'DigestValue', NS_DS).Text);

  //Compare the generated digest value against DigestValue in the SignedInfo Reference; if there is any mismatch, validation fails.
  if not SameBytes(bytes, digest) then
    raise Exception.Create('Digest mismatch on reference '+ref.getAttribute('URI'));
end;


function TDigitalSigner.verifySignature(xml: TBytes): boolean;
var
  doc : IXMLDocument;
  sig, si : IXMLNode;
  can, v : TBytes;
  key : TKeyInfo;
  i : integer;
begin
  doc := loadXml(xml);
  if (doc.DocumentElement.NodeName = 'Signature') and (doc.DocumentElement.NamespaceURI = NS_DS) then
    sig := doc.DocumentElement
  else
    sig := getChildNode(doc.DocumentElement, 'Signature', NS_DS);
  if (sig = nil) then
    raise Exception.Create('Signature not found');
  si := getChildNode(sig, 'SignedInfo', NS_DS);
  if (si = nil) then
    raise Exception.Create('SignedInfo not found');
  if (sig <> doc.DocumentElement) then
    doc.DocumentElement.ChildNodes.Remove(sig)
  else
    doc := nil;

  //k. now we follow the method:
  // 1. Canonicalize the SignedInfo element based on the CanonicalizationMethod in SignedInfo.
  can := canonicaliseXml(canoncalizationSet(getChildNode(si, 'CanonicalizationMethod', NS_DS).getAttribute('Algorithm')), si);

  // 2. For each Reference in SignedInfo:
  for i := 0 to si.ChildNodes.Count - 1 do
    if (si.ChildNodes[i].NodeType = ntElement) and (si.ChildNodes[i].NodeName = 'Reference') then
      checkDigest(si.ChildNodes[i], doc);

  // 3. Obtain the keying information from KeyInfo or from an external source.
  key := LoadKeyInfo(sig);
  try
    // 4. Obtain the canonical form of the SignatureMethod using the CanonicalizationMethod and use the result (and previously obtained KeyInfo) to confirm the SignatureValue over the SignedInfo element.
    v := unbase64(getChildNode(sig, 'SignatureValue').text);
    result := key.checkSignature(can, v, signatureMethod(getChildNode(si, 'SignatureMethod', NS_DS).GetAttribute('Algorithm')));
  finally
    key.Free;
  end;
end;

function TKeyInfo.checkSignature(digest, signature: TBytes; method: TSignatureMethod) : boolean;
begin
  if method in [sdXmlDSASha1, sdXmlDSASha256] then
    result := checkSignatureDSA(digest, signature, method)
  else
    result := checkSignatureRSA(digest, signature, method);
end;

function TKeyInfo.checkSignatureRSA(digest, signature: TBytes; method: TSignatureMethod) : boolean;
var
  ctx : EVP_MD_CTX;
  e: integer;
  pkey: PEVP_PKEY;
begin
  pkey := EVP_PKEY_new;
  try
    check(EVP_PKEY_set1_RSA(pkey, rsa) = 1, 'openSSL EVP_PKEY_set1_RSA failed');

    // 2. do the signing
    EVP_MD_CTX_init(@ctx);
    try
      if method = sdXmlRSASha1 then
        EVP_VerifyInit(@ctx, EVP_sha1)
      else
        EVP_VerifyInit(@ctx, EVP_sha256);
      check(EVP_VerifyUpdate(@ctx, @digest[0], Length(digest)) = 1, 'openSSL EVP_VerifyUpdate failed');
      e := EVP_VerifyFinal(@ctx, @signature[0], length(signature), pKey);
      result := e = 1;
    finally
      EVP_MD_CTX_cleanup(@ctx);
    end;
  finally
    EVP_PKEY_free(pKey);
  end;
end;

function TKeyInfo.checkSignatureDSA(digest, signature: TBytes; method: TSignatureMethod) : Boolean;
var
  ctx : EVP_MD_CTX;
  e: integer;
  pkey: PEVP_PKEY;
  err : Array [0..250] of ansichar;
  m : String;
  asn1 : TBytes;
begin
  pkey := EVP_PKEY_new;
  try
    check(EVP_PKEY_set1_DSA(pkey, dsa) = 1, 'openSSL EVP_PKEY_set1_RSA failed');

    // 2. do the signing
    EVP_MD_CTX_init(@ctx);
    try
      if method = sdXmlDSASha1 then
        EVP_VerifyInit(@ctx, EVP_sha1)
      else
        EVP_VerifyInit(@ctx, EVP_sha256);
      check(EVP_VerifyUpdate(@ctx, @digest[0], Length(digest)) = 1, 'openSSL EVP_VerifyUpdate failed');
      asn1 := BytesPairToAsn1(signature);
      e := EVP_VerifyFinal(@ctx, @asn1[0], length(asn1), pKey);
      if (e = -1) then
      begin
        m := '';
        e := ERR_get_error;
        repeat
          ERR_error_string(e, @err);
          m := m + inttohex(e, 8)+' ('+String(err)+')'+#13#10;
          e := ERR_get_error;
        until e = 0;
        raise Exception.Create('OpenSSL Error verifying signature: '+#13#10+m);
      end
      else
        result := e = 1;
    finally
      EVP_MD_CTX_cleanup(@ctx);
    end;
  finally
    EVP_PKEY_free(pKey);
  end;
end;


destructor TKeyInfo.Destroy;
begin
  if dsa <> nil then
    DSA_Free(dsa);
  if rsa <> nil then
    RSA_free(rsa);
  inherited;
end;

function TDigitalSigner.digest(source: TBytes; method: TSignatureMethod): TBytes;
begin
  if method in [sdXmlDSASha256, sdXmlRSASha256] then
    result := digestSHA256(source)
  else
    result := digestSHA1(source);
end;

function TDigitalSigner.digestSHA1(source: TBytes): TBytes;
var
  hash : TIdHashSHA1;
begin
  hash := TIdHashSHA1.Create;
  try
    result := idb(hash.HashBytes(idb(source)));
  finally
    hash.Free;
  end;
end;

function TDigitalSigner.digestSHA256(source: TBytes): TBytes;
var
  hash : TIdHashSHA256;
  b : TIdBytes;
begin
  hash := TIdHashSHA256.Create;
  try
    b := idb(source);
    b := hash.HashBytes(b);
    result := idb(b);
  finally
    hash.Free;
  end;
end;


function TDigitalSigner.loadRSAKey: PRSA;
var
  bp: pBIO;
  fn, pp: PAnsiChar;
  pk: PRSA;
begin
  fn := PAnsiChar(FkeyFile);
  pp := PAnsiChar(FKeyPassword);
  bp := BIO_new(BIO_s_file());
  BIO_read_filename(bp, fn);
  pk := nil;
  result := PEM_read_bio_RSAPrivateKey(bp, @pk, nil, pp);
  if result = nil then
    raise Exception.Create('Private key failure.' + GetSSLErrorMessage);
end;


function TDigitalSigner.loadDSAKey: PDSA;
var
  bp: pBIO;
  fn, pp: PAnsiChar;
  pk: PDSA;
begin
  fn := PAnsiChar(FkeyFile);
  pp := PAnsiChar(FKeyPassword);
  bp := BIO_new(BIO_s_file());
  BIO_read_filename(bp, fn);
  pk := nil;
  result := PEM_read_bio_DSAPrivateKey(bp, @pk, nil, pp);
  if result = nil then
    raise Exception.Create('Private key failure.' + GetSSLErrorMessage);
end;


function TDigitalSigner.sign(src : TBytes; method: TSignatureMethod) : TBytes;
begin
  if method in [sdXmlDSASha1, sdXmlDSASha256] then
    result := signDSA(src, method)
  else
    result := signRSA(src, method);
end;


function TDigitalSigner.signDSA(src : TBytes; method: TSignatureMethod) : TBytes;
var
  pkey: PEVP_PKEY;
  dkey: PDSA;
  ctx : EVP_MD_CTX;
  len : integer;
  asn1 : TBytes;
begin
  // 1. Load the RSA private Key from FKey
  dkey := loadDSAKey;
  try
    pkey := EVP_PKEY_new;
    try
      check(EVP_PKEY_set1_DSA(pkey, dkey) = 1, 'openSSL EVP_PKEY_set1_DSA failed');

      // 2. do the signing
      SetLength(asn1, EVP_PKEY_size(pkey));
      EVP_MD_CTX_init(@ctx);
      try
        if method = sdXmlDSASha256 then
          EVP_SignInit(@ctx, EVP_sha256)
        else
          EVP_SignInit(@ctx, EVP_sha1);
        check(EVP_SignUpdate(@ctx, @src[0], Length(src)) = 1, 'openSSL EVP_SignUpdate failed');
        check(EVP_SignFinal(@ctx, @asn1[0], len, pKey) = 1, 'openSSL EVP_SignFinal failed');
        SetLength(asn1, len);
        result := asn1SigToBytePair(asn1);
      finally
        EVP_MD_CTX_cleanup(@ctx);
      end;
    finally
      EVP_PKEY_free(pKey);
    end;
  finally
    DSA_free(dkey);
  end;
end;


function TDigitalSigner.signRSA(src : TBytes; method: TSignatureMethod) : TBytes;
var
  pkey: PEVP_PKEY;
  rkey: PRSA;
  ctx : EVP_MD_CTX;
  keysize : integer;
  len : integer;
begin
  // 1. Load the RSA private Key from FKey
  rkey := loadRSAKey;
  try
    pkey := EVP_PKEY_new;
    try
      check(EVP_PKEY_set1_RSA(pkey, rkey) = 1, 'openSSL EVP_PKEY_set1_RSA failed');

      // 2. do the signing
      keysize := EVP_PKEY_size(pkey);
      SetLength(result, keysize);
      EVP_MD_CTX_init(@ctx);
      try
        if method = sdXmlRSASha256 then
          EVP_SignInit(@ctx, EVP_sha256)
        else
          EVP_SignInit(@ctx, EVP_sha1);
        check(EVP_SignUpdate(@ctx, @src[0], Length(src)) = 1, 'openSSL EVP_SignUpdate failed');
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
end;

function TDigitalSigner.signAlgorithmForMethod(method : TSignatureMethod) : String;
begin
  case method of
    sdXmlDSASha1 : result := 'http://www.w3.org/2000/09/xmldsig#dsa-sha1';
    sdXmlRSASha1 : result := 'http://www.w3.org/2000/09/xmldsig#rsa-sha1';
    sdXmlDSASha256 : result := 'http://www.w3.org/2000/09/xmldsig#dsa-sha256';
    sdXmlRSASha256 : result := 'http://www.w3.org/2000/09/xmldsig#rsa-sha256';
  else
    raise Exception.Create('unknown method');
  end;
end;

function TDigitalSigner.digestAlgorithmForMethod(method : TSignatureMethod) : String;
begin
  case method of
    sdXmlDSASha256, sdXmlRSASha256 : result := 'http://www.w3.org/2000/09/xmldsig#sha256';
  else
    result := 'http://www.w3.org/2000/09/xmldsig#sha1';
  end;
end;


function TDigitalSigner.signEnveloped(xml: TBytes; method : TSignatureMethod; keyinfo : boolean): TBytes;
var
  can, dig :  TBytes;
  dom : IXMLDocument;
  sig, si, ref, trns: IXMLNode;
  s : String;
begin
  can := canonicaliseXml([xcmCanonicalise],xml, dom);
  sig := dom.DocumentElement.AddChild('Signature', NS_DS);
  sig.DeclareNamespace('', NS_DS);
  si := sig.addChild('SignedInfo');
  si.addChild('CanonicalizationMethod').setAttribute('Algorithm', 'http://www.w3.org/TR/2001/REC-xml-c14n-20010315');
  si.addChild('SignatureMethod').setAttribute('Algorithm', signAlgorithmForMethod(method));
  ref := si.AddChild('Reference');
  ref.setAttribute('URI', '');
  trns := ref.addChild('Transforms');
  trns.addChild('Transform').SetAttribute('Algorithm', 'http://www.w3.org/2000/09/xmldsig#enveloped-signature');
  ref.addChild('DigestMethod').setAttribute('Algorithm', digestAlgorithmForMethod(method));
  dig := digest(can, method); // the method doesn't actually apply to this, but we figure that if the method specifies sha256, then it should be used here
  ref.addChild('DigestValue').Text := String(EncodeBase64(@dig[0], length(dig)));
  can := canonicaliseXml([xcmCanonicalise],si);
  dig := sign(can, method);
  s := base64(dig);
  sig.AddChild('SignatureValue').Text := s;

  if keyinfo then
    AddKeyInfo(sig, method);
  dom.SaveToXML(s);
  result := TEncoding.UTF8.GetBytes(s);
end;

function bn2Base64(p : PBigNum) : String;
var
  b : TBytes;
begin
  setlength(b,  BN_num_bytes(p));
  BN_bn2bin(p, @b[0]);
  result := String(base64(b));
end;

procedure TDigitalSigner.AddKeyInfo(sig : IXmlNode; method : TSignatureMethod);
var
  kv: IXMLNode;
  dkey : PDSA;
  rkey : PRSA;
begin
  if method in [sdXmlDSASha1, sdXmlDSASha256] then
  begin
    kv := sig.AddChild('KeyInfo').AddChild('KeyValue').AddChild('DSAKeyValue');
    dkey := LoadDSAKey;
    try
      kv.AddChild('P').text := bn2Base64(dkey.p);
      kv.AddChild('Q').text := bn2Base64(dkey.q);
      kv.AddChild('G').text := bn2Base64(dkey.g);
      kv.AddChild('Y').text := bn2Base64(dkey.pub_key);
    finally
      DSA_free(dKey);
    end;
  end
  else
  begin
    kv := sig.AddChild('KeyInfo').AddChild('KeyValue').AddChild('RSAKeyValue');
    rkey := loadRSAKey;
    try
      kv.AddChild('Modulus').text := bn2Base64(rkey.n);
      kv.AddChild('Exponent').text := bn2Base64(rkey.e);
    finally
      RSA_free(rkey);
    end;
  end;
end;

function TDigitalSigner.signExternal(references: TDigitalSignatureReferenceList; method : TSignatureMethod; keyinfo : boolean): TBytes;
var
  doc : TXMLDocument;
  sig, si, ref, trns : IXMLNode;
  i : integer;
  reference : TDigitalSignatureReference;
  s, t : String;
  can, dig : TBytes;
begin
  doc := TXMLDocument.Create(nil);
  doc.DOMVendor := OpenXML4Factory;
  doc.Options := [doNamespaceDecl];
  sig := doc.CreateElement('Signature', NS_DS);
  sig.SetAttribute('xmlns', NS_DS);
  si := sig.AddChild('SignedInfo');
  si.AddChild('CanonicalizationMethod').SetAttribute('Algorithm', 'http://www.w3.org/TR/2001/REC-xml-c14n-20010315');
  si.addChild('SignatureMethod').setAttribute('Algorithm', signAlgorithmForMethod(method));
  for i := 0 to references.Count - 1 do
  begin
    reference := references[i];
    ref := si.AddChild('Reference');
    ref.setAttribute('URI', reference.URL);
    if reference.transforms.count > 0 then
    begin
      trns := ref.addChild('Transforms');
      for t in reference.transforms do
        trns.addChild('Transform').SetAttribute('Algorithm', t);
    end;
    ref.addChild('DigestMethod').setAttribute('Algorithm', digestAlgorithmForMethod(method));
    dig := digest(reference.content, method);
    ref.addChild('DigestValue').Text := String(EncodeBase64(@dig[0], length(dig)));
  end;
  can := canonicaliseXml([xcmCanonicalise],si);
  dig := sign(can, method);
  s := base64(dig);
  sig.AddChild('SignatureValue').Text := s;
  if keyinfo then
    AddKeyInfo(sig, method);
  result := canonicaliseXml([xcmCanonicalise], sig);  // don't need to canonicalise the whole lot, but why not?
end;

function TDigitalSigner.LoadKeyInfo(sig: IXmlNode): TKeyInfo;
var
  ki, kv, kd : IXMLNode;
  v : TBytes;
//  p : pansichar;
begin
  result := TKeyInfo.Create;
  try
    ki := getChildNode(sig, 'KeyInfo', NS_DS);
    if ki = nil then
      raise Exception.Create('No KeyInfo found in digital signature');
    kv := getChildNode(ki, 'KeyValue', NS_DS);
    if kv = nil then
      raise Exception.Create('No KeyValue found in digital signature');
    kd := getChildNode(kv, 'RSAKeyValue', NS_DS);
    if kd <> nil then
    begin
      result.rsa := RSA_new;
      v := unbase64(getChildNode(kd, 'Modulus', NS_DS).Text);
      result.rsa.n := BN_bin2bn(@v[0], length(v), nil);
      v := unbase64(getChildNode(kd, 'Exponent', NS_DS).Text);
      result.rsa.e := BN_bin2bn(@v[0], length(v), nil);
    end
    else
    begin
      kd := getChildNode(kv, 'DSAKeyValue', NS_DS);
      if kd <> nil then
      begin
        result.dsa := DSA_new;
        v := unbase64(getChildNode(kd, 'P', NS_DS).Text);
        result.dsa.p := BN_bin2bn(@v[0], length(v), nil);
        v := unbase64(getChildNode(kd, 'Q', NS_DS).Text);
        result.dsa.q := BN_bin2bn(@v[0], length(v), nil);
        v := unbase64(getChildNode(kd, 'G', NS_DS).Text);
        result.dsa.g := BN_bin2bn(@v[0], length(v), nil);
        v := unbase64(getChildNode(kd, 'Y', NS_DS).Text);
        result.dsa.pub_key := BN_bin2bn(@v[0], length(v), nil);

//        if getChildNode(kd, 'X', NS_DS) <> nil then
//        begin
//          v := unbase64(getChildNode(kd, 'X', NS_DS).Text);
//          result.dsa.priv_key := BN_bin2bn(@v[0], length(v), nil);
//        end;
      end
      else
        raise Exception.Create('No Key Info found');
    end;

    result.Link;
  finally
    result.Free;
  end;
end;

function TDigitalSigner.loadXml(source: TBytes): IXMLDocument;
var
  doc : TXMLDocument;
  bs : TBytesStream;
begin
  doc := TXMLDocument.Create(nil);
  result := doc;
  doc.DOMVendor := OpenXML4Factory;
  doc.ParseOptions := [poPreserveWhiteSpace];
  doc.Options := [doNamespaceDecl];

  bs := TBytesStream.Create(source);
  try
    result.LoadFromStream(bs);
  finally
    bs.Free;
  end;
end;

function TDigitalSigner.resolveReference(url: string): TBytes;
var
  fetch : TInternetFetcher;
begin
  fetch := TInternetFetcher.Create;
  try
    fetch.URL := URL;
    fetch.Fetch;
    result := fetch.Buffer.AsBytes;
  finally
    fetch.Free;
  end;
end;

function TDigitalSigner.signatureMethod(uri: String): TSignatureMethod;
begin
  if uri = 'http://www.w3.org/2000/09/xmldsig#dsa-sha1' then
    result := sdXmlDSASha1
  else if uri = 'http://www.w3.org/2000/09/xmldsig#rsa-sha1' then
    result := sdXmlRSASha1
  else if uri = 'http://www.w3.org/2000/09/xmldsig#dsa-sha256' then
    result := sdXmlDSASha256
  else if uri = 'http://www.w3.org/2000/09/xmldsig#rsa-sha256' then
    result := sdXmlRSASha256
  else
    raise Exception.Create('Unsupported signature method '+uri);
end;

{ TDigitalSignatureReferenceList }

function TDigitalSignatureReferenceList.getReference(i: integer): TDigitalSignatureReference;
begin
  result := TDigitalSignatureReference(ObjectByIndex[i]);
end;

function TDigitalSignatureReferenceList.itemClass: TAdvObjectClass;
begin
  result := TDigitalSignatureReference;
end;

{ TDigitalSignatureReference }

constructor TDigitalSignatureReference.Create;
begin
  inherited;
  FTransforms := TStringList.Create;
end;

destructor TDigitalSignatureReference.Destroy;
begin
  FTransforms.Free;
  inherited;
end;

{ TDigitalSignatureTests }

var
  inputRSA : TBytes;
  inputDSA : TBytes;

class procedure TDigitalSignatureTests.test;
begin
  LoadEAYExtensions;
  ERR_load_crypto_strings;
  OpenSSL_add_all_algorithms;
  testGen;
  testValidate;
  UnloadEAYExtensions;
end;

class procedure TDigitalSignatureTests.testFile(filename: String);
var
  bytes : TBytes;
  f : TFileStream;
  sig : TDigitalSigner;
begin
  f := TFileStream.Create(filename, fmOpenRead);
  try
    setLength(bytes, f.Size);
    f.Read(bytes[0], length(bytes));
  finally
    f.free;
  end;
  sig := TDigitalSigner.Create;
  try
    if not sig.verifySignature(bytes) then
      raise Exception.Create('Signature Invalid, '+filename);
  finally
    sig.Free;
  end;
end;

class procedure TDigitalSignatureTests.testGen;
var
  sig : TDigitalSigner;
  output : string;
begin
  // rsa using test key
  sig := TDigitalSigner.Create;
  try
    sig.KeyFile := 'C:\work\fhirserver\Exec\jwt-test.key.key';
    sig.KeyPassword := 'fhirserver';
    inputRSA := sig.signEnveloped(TEncoding.UTF8.GetBytes('<Envelope xmlns="urn:envelope">'+#13#10+'</Envelope>'+#13#10), sdXmlRSASha256, true);
    output := TENcoding.UTF8.GetString(inputRSA);
    writeln(output);
 finally
    sig.Free;
  end;

  //  dsa using test key
  sig := TDigitalSigner.Create;
  try
    sig.KeyFile := 'C:\work\fhirserver\tests\signatures\test_dsa_key.pem';
    sig.KeyPassword := 'fhir';
    inputDSA := sig.signEnveloped(TEncoding.UTF8.GetBytes('<Envelope xmlns="urn:envelope">'+#13#10+'</Envelope>'+#13#10), sdXmlDSASha256, true);
    output := TENcoding.UTF8.GetString(inputDSA);
    writeln(output);
 finally
    sig.Free;
  end;
end;

class procedure TDigitalSignatureTests.testValidate;
var
  sig : TDigitalSigner;
begin
  // 1. test yourself
  sig := TDigitalSigner.Create;
  try
    if not sig.verifySignature(inputRSA) then raise Exception.Create('signature invalid');
  finally
    sig.Free;
  end;

  sig := TDigitalSigner.Create;
  try
    if not sig.verifySignature(inputDSA) then raise Exception.Create('signature invalid');
  finally
    sig.Free;
  end;

  // 2. other examples
  // rsa, work
  testFile('C:\work\fhirserver\tests\signatures\java_example_rsa.xml');

  // dsa, don't work
  testFile('C:\work\fhirserver\tests\signatures\java_example_dsa.xml');
  testFile('C:\work\fhirserver\tests\signatures\james.xml');
end;


end.
