unit HMAC;

interface

uses
  System.SysUtils,
  EncdDecd,
  IdGlobal, IdHMAC, IdSSLOpenSSL, IdHash,
  BytesSupport;

type
  TIdHMACClass = class of TIdHMAC;
  THMACUtils = class
  public
    class function HMAC(alg : TIdHMACClass; aKey, aMessage: TBytes): TBytes;
    class function HMAC_HexStr(alg : TIdHMACClass; aKey, aMessage: TBytes): TBytes;
    class function HMAC_Base64(alg : TIdHMACClass; aKey, aMessage: TBytes): AnsiString;
  end;

function idb(b : TBytes) : TIdBytes;  overload;
function idb(b : TIdBytes) : TBytes;  overload;

implementation

function idb(b : TBytes) : TIdBytes;
begin
  SetLength(result, length(b));
  if (length(b) > 0) then
    move(b[0], result[0], length(b));
end;

function idb(b : TIdBytes) : TBytes;
begin
  SetLength(result, length(b));
  if (length(b) > 0) then
    move(b[0], result[0], length(b));
end;

class function THMACUtils.HMAC(alg : TIdHMACClass; aKey, aMessage: TBytes): TBytes;
var
  _alg : TIdHMAC;
begin
  if not IdSSLOpenSSL.LoadOpenSSLLibrary then Exit;
  _alg := alg.Create;
  try
    _alg.Key := idb(aKey);
    Result:= idb(_alg.HashValue(idb(aMessage)));
  finally
    _alg.Free;
  end;
end;

class function THMACUtils.HMAC_HexStr(alg : TIdHMACClass; aKey, aMessage: TBytes): TBytes;
var
  I: Byte;
begin
  Result:= AnsiStringAsBytes('0x');
  for I in HMAC(alg, aKey, aMessage) do
    Result:= BytesAdd(Result, AnsiStringAsBytes(IntToHex(I, 2)));
end;

class function THMACUtils.HMAC_Base64(alg : TIdHMACClass; aKey, aMessage: TBytes): AnsiString;
var
  _HMAC: TBytes;
begin
  _HMAC:= HMAC(alg, aKey, aMessage);
  Result:= EncodeBase64(_HMAC, Length(_HMAC));
end;

end.
