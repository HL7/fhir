Unit AdvWinInetClients;

{
Copyright (c) 2001-2013, Kestral Computing Pty Ltd (http://www.kestral.com.au)
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

Interface

Uses
  Windows,
  SysUtils,
  StringSupport,
  AdvBuffers,
  AdvMemories,
  AdvObjects;

Type
  HInternet = pointer;

  TAdvWinInetClient = class (TAdvObject)
    Private
      FUserAgent : String;
      FRequestType: String;
      FResponseCode: String;
      FResponseType: String;
      FSoapAction: String;
      FRequestMethod: String;
      FResponse: TAdvBuffer;
      FRequest: TAdvBuffer;
      FSecure : Boolean;
      FServer : String;
      FPort : String;
      FResource : String;
      FSession : HInternet;
      FConnection : HInternet;

      Procedure Check(ACondition : boolean; ALocation, ADescription, ADetail : string; iError : Cardinal);
      procedure SetResponse(const Value: TAdvBuffer);
      procedure SetRequest(const Value: TAdvBuffer);
      procedure Connect;
      procedure DoExecute;
      Procedure Disconnect;
      procedure SetUserAgent(const Value: String);
      procedure SetSecure(const Value: Boolean);
      procedure SetPort(const Value: String);
      procedure SetResource(const Value: String);
      procedure SetServer(const Value: String);
      function GetHeader(ARequest : HInternet; AHeader : DWord):string;
    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Property UserAgent : String read FUserAgent write SetUserAgent;
      Property Server : String read FServer write SetServer;
      Property Secure : Boolean read FSecure write SetSecure;
      Property Port : String read FPort write SetPort;
      Property Resource : String read FResource write SetResource;

      Property RequestMethod : String read FRequestMethod write FRequestMethod;
      Property RequestType : String read FRequestType write FRequestType;
      Property Request : TAdvBuffer read FRequest write SetRequest;
      Property SoapAction : String read FSoapAction write FSoapAction;

      Property ResponseCode : String read FResponseCode write FResponseCode;
      Property ResponseType : String read FResponseType write FResponseType;
      Property Response : TAdvBuffer read FResponse write SetResponse;

      Procedure Execute;
  End;

Implementation

const
  DLL_WININET = 'wininet.dll';

  INTERNET_OPEN_TYPE_PRECONFIG                    = 0;   // use registry configuration
  INTERNET_OPEN_TYPE_DIRECT                       = 1;   // direct to net
  INTERNET_FLAG_SECURE            = $00800000;  // use PCT/SSL if applicable (HTTP)
  INTERNET_FLAG_KEEP_CONNECTION   = $00400000;  // use keep-alive semantics
  INTERNET_FLAG_NO_AUTO_REDIRECT  = $00200000;  // don't handle redirections automatically
  INTERNET_FLAG_NO_CACHE_WRITE    = $04000000;  // don't write this item to the cache
  INTERNET_FLAG_PRAGMA_NOCACHE    = $00000100;  // asking wininet to add "pragma: no-cache"
  INTERNET_FLAG_IGNORE_CERT_CN_INVALID = $00001000;
  INTERNET_FLAG_IGNORE_CERT_DATE_INVALID = $00002000;
  INTERNET_FLAG_IGNORE_REDIRECT_TO_HTTP = $00008000;

  SECURITY_FLAG_IGNORE_UNKNOWN_CA = $0100;

  HTTP_QUERY_CONTENT_TYPE         = 1;
  HTTP_QUERY_STATUS_CODE          = 19;
  INTERNET_DEFAULT_HTTP_PORT      = 80;
  INTERNET_DEFAULT_HTTPS_PORT     = 443;
  INTERNET_SERVICE_HTTP           = 3;
  INTERNET_OPTION_SECURITY_FLAGS  = 31;

  RS_ERR_WININET_NO_DLL = 'Unable to Load WinInet DLL';
  RS_ERR_WININET_NO_ROUTINE = 'Unable to find WinInet Entry Point';
  RS_OP_WININET_QUERY = 'WinInet Request Status Query';
  RS_OP_WININET_REQ_OPEN = 'Open Request';
  RS_OP_WININET_REQ_SEND = 'Send Request';
  RS_OP_WININET_QUERY_OPTION = 'Query Option';
  RS_OP_WININET_SET_OPTION = 'Set Option';
  RS_OP_WININET_READ = 'Read Request Result';
  RS_OP_WININET_CONNECT = 'Connect';

Type
  INTERNET_PORT = Word;
  TInternetPort = INTERNET_PORT;

  TInternetOpen = function(lpszAgent: PChar; dwAccessType: DWORD;
                            lpszProxy, lpszProxyBypass: PChar; dwFlags: DWORD): HInternet; stdcall;
  TInternetCloseHandle = function(hInternet: HINTERNET): BOOL; stdcall;
  TInternetConnect = function(hInternet: HINTERNET; lpszServerName: PChar;
                            nServerPort: TInternetPort; lpszUserName, lpszPassword: PChar;
                            dwService, dwFlags, dwContext: DWORD): HINTERNET; stdcall;
  THttpQueryInfo = function(hRequest: HINTERNET; dwInfoLevel: DWORD;
                            lpBuffer: Pointer; var lpdwBufferLength, lpdwIndex: DWORD): BOOL; stdcall;
  THttpOpenRequest = function(hConnect: HINTERNET; lpszVerb, lpszObjectName, lpszVersion,lpszReferrer: PChar;
                            lplpszAcceptTypes: PChar; dwFlags, dwContext: DWORD): HINTERNET; stdcall;
  THttpSendRequest = function(hRequest: HINTERNET; lpszHeaders: PChar; dwHeadersLength: DWORD;
                            lpOptional: Pointer; dwOptionalLength: DWORD): BOOL; stdcall;
  TInternetQueryDataAvailable = function (hFile: HINTERNET; var lpdwNumberOfBytesAvailable: DWORD;
                            dwFlags, dwContext: DWORD): BOOL; stdcall;
  TInternetReadFile = function (hFile: HINTERNET; lpBuffer: Pointer; dwNumberOfBytesToRead: DWORD;
                            var lpdwNumberOfBytesRead: DWORD): BOOL; stdcall;
  TInternetQueryOption = function (hInternet: HINTERNET; dwOption: DWORD;
                             lpBuffer: Pointer; var dwBufferLength: DWORD): BOOL; stdcall;
  TInternetSetOption = function (hInternet: HINTERNET; dwOption: DWORD;
                            lpBuffer: Pointer; dwBufferLength: DWORD): BOOL; stdcall;

var
  GHandle : THandle;
  mInternetOpen : TInternetOpen;
  mInternetCloseHandle : TInternetCloseHandle;
  mInternetConnect : TInternetConnect;
  mHttpQueryInfo : THttpQueryInfo;
  mHttpOpenRequest : THttpOpenRequest;
  mHttpSendRequest : THttpSendRequest;
  mInternetQueryDataAvailable : TInternetQueryDataAvailable;
  mInternetReadFile : TInternetReadFile;
  mInternetQueryOption : TInternetQueryOption;
  mInternetSetOption : TInternetSetOption;
// procedure LoadEntryPoint(Var VPointer : pointer; const AName : string);


Procedure LoadDLL;
begin
  GHandle := LoadLibrary(DLL_WININET);
  if GHandle >= 32 Then
  @mInternetOpen := GetProcAddress(GHandle, 'InternetOpenA');
  @mInternetCloseHandle := GetProcAddress(GHandle, 'InternetCloseHandle');
  @mInternetConnect := GetProcAddress(GHandle, 'InternetConnectA');
  @mHttpQueryInfo := GetProcAddress(GHandle, 'HttpQueryInfoA');
  @mHttpOpenRequest := GetProcAddress(GHandle, 'HttpOpenRequestA');
  @mHttpSendRequest := GetProcAddress(GHandle, 'HttpSendRequestA');
  @mInternetQueryDataAvailable := GetProcAddress(GHandle, 'InternetQueryDataAvailable');
  @mInternetReadFile := GetProcAddress(GHandle, 'InternetReadFile');
  @mInternetQueryOption := GetProcAddress(GHandle, 'InternetQueryOptionA');
  @mInternetSetOption := GetProcAddress(GHandle, 'InternetSetOptionA');
end;

Procedure UnLoadDLL;
begin
  @mInternetOpen := nil;
  @mInternetCloseHandle := nil;
  @mInternetConnect := nil;
  @mHttpQueryInfo := nil;
  @mHttpOpenRequest := nil;
  @mHttpSendRequest := nil;
  @mInternetQueryDataAvailable := nil;
  @mInternetReadFile := nil;
  @mInternetQueryOption := nil;
  @mInternetSetOption := nil;
  if GHandle > 32 Then
    FreeLibrary(GHandle);
end;


{ TAdvWinInetClient }

constructor TAdvWinInetClient.Create;
begin
  inherited;
  Check(GHandle >= 32, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_DLL, DLL_WININET, 0);
  Check(@mInternetOpen <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetOpenA', 0);
  Check(@mInternetCloseHandle <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetCloseHandle', 0);
  Check(@mInternetConnect <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetConnectA', 0);
  Check(@mHttpQueryInfo <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpQueryInfoA', 0);
  Check(@mHttpOpenRequest <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpOpenRequestA', 0);
  Check(@mHttpSendRequest <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpSendRequestA', 0);
  Check(@mInternetQueryDataAvailable <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetQueryDataAvailable', 0);
  Check(@mInternetReadFile <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetReadFile', 0);
  Check(@mInternetQueryOption <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetQueryOption', 0);
  Check(@mInternetSetOption <> nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetSetOption', 0);
  FUserAgent := 'Kestral Software';
end;


destructor TAdvWinInetClient.Destroy;
begin
  Disconnect;
  FResponse.Free;
  FRequest.Free;
  inherited;
end;


procedure TAdvWinInetClient.Check(ACondition : boolean; ALocation, ADescription, aDetail : string; iError : Cardinal);
begin
  if not ACondition then
    if iError = 0 Then
      Error(ALocation, ADescription+' ['+ADetail+']')
    else if SysErrorMessage(iError) <> '' Then
      Error(ALocation, ADescription+' ['+ADetail+' '+SysErrorMessage(iError)+']')
    else
      Error(ALocation, ADescription+' ['+ADetail+' #'+IntToStr(iError)+']');
end;


procedure TAdvWinInetClient.Execute;
begin
  Connect;
  DoExecute;
end;

procedure TAdvWinInetClient.SetResponse(const Value: TAdvBuffer);
begin
  FResponse.Free;
  FResponse := Value;
end;

procedure TAdvWinInetClient.SetRequest(const Value: TAdvBuffer);
begin
  FRequest.Free;
  FRequest := Value;
end;

procedure TAdvWinInetClient.Connect;
begin
  if FSession = nil then
    FSession := mInternetOpen(pchar(FUserAgent), INTERNET_OPEN_TYPE_PRECONFIG, nil, nil, 0);
  if FConnection = nil then
    If FSecure then
      FConnection := mInternetConnect(FSession, pchar(FServer), StrToIntDef(FPort, INTERNET_DEFAULT_HTTPS_PORT), nil, nil, INTERNET_SERVICE_HTTP, 0, 0)
    else
      FConnection := mInternetConnect(FSession, pchar(FServer), StrToIntDef(FPort, INTERNET_DEFAULT_HTTP_PORT), nil, nil, INTERNET_SERVICE_HTTP, 0, 0);
end;

procedure TAdvWinInetClient.Disconnect;
begin
  if FConnection <> nil Then
  Begin
    mInternetCloseHandle(FConnection);
    FConnection := nil;
  End;
  If FSession <> nil Then
  Begin
    mInternetCloseHandle(FSession);
    FSession := nil;
  End;
end;

procedure TAdvWinInetClient.SetUserAgent(const Value: String);
begin
  Disconnect;
  FUserAgent := Value;
end;

procedure TAdvWinInetClient.SetPort(const Value: String);
begin
  Disconnect;
  FPort := Value;
end;

procedure TAdvWinInetClient.SetResource(const Value: String);
begin
  Disconnect;
  FResource := Value;
end;

procedure TAdvWinInetClient.SetServer(const Value: String);
begin
  Disconnect;
  FServer := Value;
end;

procedure TAdvWinInetClient.SetSecure(const Value: Boolean);
begin
  Disconnect;
  FSecure := Value;
end;

procedure TAdvWinInetClient.DoExecute;
var
  aReqHandle : HINTERNET;
  sHeaders : string;
  pData : Pointer;
  iSize : DWord;
  bOk : boolean;
  oResponse : TAdvMemoryStream;
  dwFlags : DWORD;
  dwBuffLen : DWORD;
begin
  sHeaders :=
     'SOAPAction: "'+FSoapAction+'"'+cReturn+
     'Content-Type: '+ FRequestType +cReturn;
  if FSecure then
    aReqHandle := mHttpOpenRequest(FConnection, PChar(FRequestMethod), PChar(FResource), nil, nil, nil,
        INTERNET_FLAG_SECURE or INTERNET_FLAG_IGNORE_CERT_CN_INVALID or INTERNET_FLAG_IGNORE_CERT_DATE_INVALID or INTERNET_FLAG_IGNORE_REDIRECT_TO_HTTP or
        INTERNET_FLAG_KEEP_CONNECTION or INTERNET_FLAG_NO_AUTO_REDIRECT or INTERNET_FLAG_NO_CACHE_WRITE or INTERNET_FLAG_PRAGMA_NOCACHE, 0)
  else
    aReqHandle := mHttpOpenRequest(FConnection, PChar(FRequestMethod), PChar(FResource), nil, nil, nil,
        INTERNET_FLAG_KEEP_CONNECTION or INTERNET_FLAG_NO_AUTO_REDIRECT or INTERNET_FLAG_NO_CACHE_WRITE or INTERNET_FLAG_PRAGMA_NOCACHE, 0);

  Check(aReqHandle <> nil, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_REQ_OPEN, FResource, GetLastError);
  try
    if FSecure Then
    Begin
      dwBuffLen := 4;
      bOk := mInternetQueryOption(aReqHandle, INTERNET_OPTION_SECURITY_FLAGS, pointer(@dwFlags), dwBuffLen);
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_QUERY_OPTION, FResource, GetLastError);
      dwFlags := dwFlags or SECURITY_FLAG_IGNORE_UNKNOWN_CA;
      bOk := mInternetSetOption(aReqHandle, INTERNET_OPTION_SECURITY_FLAGS, @dwFlags, dwBuffLen);
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_SET_OPTION, FResource, GetLastError);
    End;

    bOk := mHttpSendRequest(aReqHandle, pchar(sHeaders), length(sHeaders), FRequest.Data, FRequest.Capacity);
    Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_REQ_SEND, FResource, GetLastError);

    oResponse := TAdvMemoryStream.Create;
    GetMem(pData, 1024);
    Try
      FResponseCode := GetHeader(aReqHandle, HTTP_QUERY_STATUS_CODE);
      FResponseType := GetHeader(aReqHandle, HTTP_QUERY_CONTENT_TYPE);
      if Pos(';', FResponseType) > 0 then
        FResponseType := copy(FResponseType, 1, Pos(';', FResponseType) - 1);
      repeat
        FillChar(pData^, 1024, #0);
        bOk := mInternetReadFile(aReqHandle, pData, 1024, iSize);
        Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_READ, FResource, GetLastError);
        if iSize > 0 then
          oResponse.Write(pData^, iSize);
      until bOk and (iSize = 0);
      FResponse.Copy(oResponse.Buffer);
    Finally
      FreeMem(pData);
      oResponse.Free;
    End;
  finally
    mInternetCloseHandle(aReqHandle);
  end;
end;

function TAdvWinInetClient.GetHeader(ARequest: HInternet; AHeader: DWord): string;
var
  pData : pointer;
  iSize : DWord;
  iIndex : DWord;
  bOk : boolean;
begin

  iSize := 1024;
  GetMem(pData, iSize);
  try
    iIndex := 0;
    if not mHttpQueryInfo(ARequest, AHeader, pData, iSize, iIndex) then
      begin
      if GetLastError = ERROR_INSUFFICIENT_BUFFER then
        begin
        FreeMem(pData);
        Getmem(pData, iSize);
        bOk := mHttpQueryInfo(ARequest, AHeader, pData, iSize, iIndex);
        Check(bOk, 'TAdvWinInetClient.GetHeader', RS_OP_WININET_QUERY, '2', GetLastError);
        end
      else
        Check(False, 'TAdvWinInetClient.GetHeader', RS_OP_WININET_QUERY, '1', GetLastError);
      end;

    if iSize <> 0 then
      begin
      SetLength(result, iSize);
      move(pData^, result[1], iSize);
      end
    else
      result := '';
  finally
    FreeMem(pData);
  end;
end;


Initialization
  LoadDLL;
Finalization
  UnloadDLL;
End.

