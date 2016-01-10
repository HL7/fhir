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
  Windows, SysUtils, Generics.Collections,
  StringSupport,
  AdvBuffers, AdvMemories, AdvObjects;


Type
  HInternet = pointer;

  TProgressEvent = procedure (sender : TObject; msg : String) of object;

  TAdvWinInetClient = Class(TAdvObject)
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
      FUseWindowsProxySettings : Boolean;
      FProxySecure : Boolean;
      FProxyServer : String;
      FProxyPort : Integer;
      FPassword: String;
      FUsername: String;
      FProxyPassword: String;
      FProxyUsername: String;
      FHeaders : TDictionary<String, String>;

      FIgnoreContentTypeHeader : Boolean;
    FOnProgress: TProgressEvent;

      Procedure Check(ACondition : Boolean; Const ALocation, ADescription, ADetail : String; iError : Cardinal);
      Procedure SetResponse(Const Value: TAdvBuffer);
      Procedure SetRequest(Const Value: TAdvBuffer);
      Procedure Connect;
      Procedure Disconnect;
      Procedure SetUserAgent(Const Value: String);
      Procedure SetSecure(Const Value: Boolean);
      Procedure SetPort(Const Value: String);
      Procedure SetResource(Const Value: String);
      Procedure SetServer(Const Value: String);

      Procedure SetUseWindowsProxySettings(Const Value: Boolean);
      Procedure SetProxyServer(Const Value: String);
      Procedure SetProxySecure(Const Value: Boolean);
      Procedure SetProxyPort(Const Value: Integer);

      Function GetHeader(ARequest : HInternet; AHeader : DWord) : String;
      Function ErrorMessage(Const sDetail: String; iError: Cardinal) : String;

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Execute;

      Property Headers : TDictionary<String, String> read FHeaders;

      Procedure SetAddress(Const Value : String);

      Property UserAgent : String Read FUserAgent Write SetUserAgent;
      Property Server : String Read FServer Write SetServer;
      Property Secure : Boolean Read FSecure Write SetSecure;
      Property Port : String Read FPort Write SetPort;
      Property Resource : String Read FResource Write SetResource;
      Property UseWindowsProxySettings : Boolean Read FUseWindowsProxySettings Write SetUseWindowsProxySettings;
      Property ProxyServer : String Read FProxyServer Write SetProxyServer;
      Property ProxySecure : Boolean Read FProxySecure Write SetProxySecure;
      Property ProxyPort : Integer Read FProxyPort Write SetProxyPort;

      Property Username : String Read FUsername Write FUserName;
      Property Password : String Read FPassword Write FPassword;
      Property ProxyUsername : String Read FProxyUsername Write FProxyUserName;
      Property ProxyPassword : String Read FProxyPassword Write FProxyPassword;

      Property RequestMethod : String Read FRequestMethod Write FRequestMethod;
      Property RequestType : String Read FRequestType Write FRequestType;
      Property Request : TAdvBuffer Read FRequest Write SetRequest;
      Property SoapAction : String Read FSoapAction Write FSoapAction;

      Property ResponseCode : String Read FResponseCode Write FResponseCode;
      Property ResponseType : String Read FResponseType Write FResponseType;
      Property Response : TAdvBuffer Read FResponse Write SetResponse;

      Property IgnoreContentTypeHeader : Boolean Read FIgnoreContentTypeHeader Write FIgnoreContentTypeHeader;
      Property OnProgress : TProgressEvent read FOnProgress write FOnProgress;
      function getResponseHeader(name : String) : String;
  End;

  EAdvWinInetClient = Class(EAdvException);


Implementation


Const
  DLL_WININET = 'wininet.dll';

  INTERNET_OPEN_TYPE_PRECONFIG                    = 0;   // use registry configuration
  INTERNET_OPEN_TYPE_DIRECT                       = 1;   // direct to net
  INTERNET_OPEN_TYPE_PROXY = 3; // use specified proxy

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
  INTERNET_OPTION_USERNAME = 28;
  INTERNET_OPTION_PASSWORD = 29;
  INTERNET_OPTION_PROXY_USERNAME = 43;
  INTERNET_OPTION_PROXY_PASSWORD = 44;

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

  TInternetOpen = Function(lpszAgent: PChar; dwAccessType: DWORD; lpszProxy, lpszProxyBypass: PChar; dwFlags: DWORD): HInternet; Stdcall;
  TInternetCloseHandle = Function(hInternet: HINTERNET): BOOL; Stdcall;
  TInternetConnect = Function(hInternet: HINTERNET; lpszServerName: PChar; nServerPort: TInternetPort; lpszUserName, lpszPassword: PChar; dwService, dwFlags, dwContext: DWORD): HINTERNET; Stdcall;
  THttpQueryInfo = Function(hRequest: HINTERNET; dwInfoLevel: DWORD; lpBuffer: Pointer; Var lpdwBufferLength, lpdwIndex: DWORD): BOOL; Stdcall;
  THttpOpenRequest = Function(hConnect: HINTERNET; lpszVerb, lpszObjectName, lpszVersion,lpszReferrer: PChar; lplpszAcceptTypes: PChar; dwFlags, dwContext: DWORD): HINTERNET; Stdcall;
  THttpSendRequest = Function(hRequest: HINTERNET; lpszHeaders: PChar; dwHeadersLength: DWORD; lpOptional: Pointer; dwOptionalLength: DWORD): BOOL; Stdcall;
  TInternetQueryDataAvailable = Function (hFile: HINTERNET; Var lpdwNumberOfBytesAvailable: DWORD; dwFlags, dwContext: DWORD): BOOL; Stdcall;
  TInternetReadFile = Function (hFile: HINTERNET; lpBuffer: Pointer; dwNumberOfBytesToRead: DWORD; Var lpdwNumberOfBytesRead: DWORD): BOOL; Stdcall;
  TInternetQueryOption = Function (hInternet: HINTERNET; dwOption: DWORD; lpBuffer: Pointer; Var dwBufferLength: DWORD): BOOL; Stdcall;
  TInternetSetOption = Function (hInternet: HINTERNET; dwOption: DWORD; lpBuffer: Pointer; dwBufferLength: DWORD): BOOL; Stdcall;
  TInternetGetLastResponseInfo = Function (Var lpdwError: DWORD; lpszBuffer: PChar; Var lpdwBufferLength: DWORD): BOOL; Stdcall;

Var
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
  mInternetGetLastResponseInfo : TInternetGetLastResponseInfo;

// procedure LoadEntryPoint(Var VPointer : pointer; const AName : string);


Procedure LoadDLL;
Begin
  GHandle := LoadLibrary(DLL_WININET);
{$IFDEF VER130}
  If GHandle >= 32 Then
  @mInternetOpen := GetProcAddress(GHandle, 'InternetOpenA');
  @mInternetConnect := GetProcAddress(GHandle, 'InternetConnectA');
  @mHttpQueryInfo := GetProcAddress(GHandle, 'HttpQueryInfoA');
  @mHttpOpenRequest := GetProcAddress(GHandle, 'HttpOpenRequestA');
  @mHttpSendRequest := GetProcAddress(GHandle, 'HttpSendRequestA');
  @mInternetQueryOption := GetProcAddress(GHandle, 'InternetQueryOptionA');
  @mInternetSetOption := GetProcAddress(GHandle, 'InternetSetOptionA');
  @mInternetGetLastResponseInfo := GetProcAddress(GHandle, 'InternetGetLastResponseInfoA');
{$ELSE}
  If GHandle >= 32 Then
    @mInternetOpen := GetProcAddress(GHandle, 'InternetOpenW');
  @mInternetConnect := GetProcAddress(GHandle, 'InternetConnectW');
  @mHttpQueryInfo := GetProcAddress(GHandle, 'HttpQueryInfoW');
  @mHttpOpenRequest := GetProcAddress(GHandle, 'HttpOpenRequestW');
  @mHttpSendRequest := GetProcAddress(GHandle, 'HttpSendRequestW');
  @mInternetQueryOption := GetProcAddress(GHandle, 'InternetQueryOptionW');
  @mInternetSetOption := GetProcAddress(GHandle, 'InternetSetOptionW');
  @mInternetGetLastResponseInfo := GetProcAddress(GHandle, 'InternetGetLastResponseInfoW');
{$ENDIF}
  @mInternetCloseHandle := GetProcAddress(GHandle, 'InternetCloseHandle');
  @mInternetQueryDataAvailable := GetProcAddress(GHandle, 'InternetQueryDataAvailable');
  @mInternetReadFile := GetProcAddress(GHandle, 'InternetReadFile');
End;


Procedure UnLoadDLL;
Begin
  @mInternetOpen := Nil;
  @mInternetCloseHandle := Nil;
  @mInternetConnect := Nil;
  @mHttpQueryInfo := Nil;
  @mHttpOpenRequest := Nil;
  @mHttpSendRequest := Nil;
  @mInternetQueryDataAvailable := Nil;
  @mInternetReadFile := Nil;
  @mInternetQueryOption := Nil;
  @mInternetSetOption := Nil;
  @mInternetGetLastResponseInfo := Nil;
  If GHandle > 32 Then
    FreeLibrary(GHandle);
End;



Function InetErrorMessage(i : Cardinal):String;
Var
  a : PChar;
  l : DWord;
Begin
  Case i Of
    12001 : {ERROR_INTERNET_OUT_OF_HANDLES} Result := 'No more handles could be generated at this time.';
    12002 : {ERROR_INTERNET_TIMEOUT} Result := 'The request has timed out.';
    12003 : {ERROR_INTERNET_EXTENDED_ERROR}
      Begin
      l := 2048;
      GetMem(a, l);
      Try
        If mInternetGetLastResponseInfo(i, a, l) Then
        Begin
          setLength(Result, l);
          move(a^, Result[1], l);
        End
        Else
          Result := 'An extended error was returned from the server. This is typically a string or buffer containing a verbose error message. Called InternetGetLastResponseInfo to retrieve the error text, but it failed too.';
      Finally
        FreeMem(a);
      End;
      End;
    12004 : {ERROR_INTERNET_INTERNAL_ERROR} Result := 'An internal error has occurred.';
    12005 : {ERROR_INTERNET_INVALID_URL} Result := 'The URL is invalid.';
    12006 : {ERROR_INTERNET_UNRECOGNIZED_SCHEME} Result := 'The URL scheme could not be recognized or is not supported.';
    12007 : {ERROR_INTERNET_NAME_NOT_RESOLVED} Result := 'The server name could not be resolved.';
    12008 : {ERROR_INTERNET_PROTOCOL_NOT_FOUND} Result := 'The requested protocol could not be located.';
    12009 : {ERROR_INTERNET_INVALID_OPTION} Result := 'A request to InternetQueryOption or InternetSetOption specified an invalid option value.';
    12010 : {ERROR_INTERNET_BAD_OPTION_LENGTH} Result := 'The length of an option supplied to InternetQueryOption or InternetSetOption is incorrect for the type of option specified.';
    12011 : {ERROR_INTERNET_OPTION_NOT_SETTABLE} Result := 'The request option cannot be set, only queried.';
    12012 : {ERROR_INTERNET_SHUTDOWN} Result := 'The Win32 Internet function support is being shut down or unloaded.';
    12013 : {ERROR_INTERNET_INCORRECT_USER_NAME} Result := 'The request to connect and log on to an FTP server could not be completed because the supplied user name is incorrect.';
    12014 : {ERROR_INTERNET_INCORRECT_PASSWORD} Result := 'The request to connect and log on to an FTP server could not be completed because the supplied password is incorrect.';
    12015 : {ERROR_INTERNET_LOGIN_FAILURE} Result := 'The request to connect to and log on to an FTP server failed.';
    12016 : {ERROR_INTERNET_INVALID_OPERATION} Result := 'The requested operation is invalid.';
    12017 : {ERROR_INTERNET_OPERATION_CANCELLED} Result := 'The operation was canceled, usually because the handle on which the request was operating was closed before the operation completed.';
    12018 : {ERROR_INTERNET_INCORRECT_HANDLE_TYPE} Result := 'The type of handle supplied is incorrect for this operation.';
    12019 : {ERROR_INTERNET_INCORRECT_HANDLE_STATE} Result := 'The requested operation cannot be carried out because the handle supplied is not in the correct state.';
    12020 : {ERROR_INTERNET_NOT_PROXY_REQUEST} Result := 'The request cannot be made via a proxy.';
    12021 : {ERROR_INTERNET_REGISTRY_VALUE_NOT_FOUND} Result := 'A required registry value could not be located.';
    12022 : {ERROR_INTERNET_BAD_REGISTRY_PARAMETER} Result := 'A required registry value was located but is an incorrect type or has an invalid value.';
    12023 : {ERROR_INTERNET_NO_DIRECT_ACCESS} Result := 'Direct network access cannot be made at this time.';
    12024 : {ERROR_INTERNET_NO_CONTEXT} Result := 'An asynchronous request could not be made because a zero context value was supplied.';
    12025 : {ERROR_INTERNET_NO_CALLBACK} Result := 'An asynchronous request could not be made because a callback function has not been set.';
    12026 : {ERROR_INTERNET_REQUEST_PENDING} Result := 'The required operation could not be completed because one or more requests are pending.';
    12027 : {ERROR_INTERNET_INCORRECT_FORMAT} Result := 'The format of the request is invalid.';
    12028 : {ERROR_INTERNET_ITEM_NOT_FOUND} Result := 'The requested item could not be located.';
    12029 : {ERROR_INTERNET_CANNOT_CONNECT} Result := 'The attempt to connect to the server failed.';
    12030 : {ERROR_INTERNET_CONNECTION_ABORTED} Result := 'The connection with the server has been terminated.';
    12031 : {ERROR_INTERNET_CONNECTION_RESET} Result := 'The connection with the server has been reset.';
    12032 : {ERROR_INTERNET_FORCE_RETRY} Result := 'Calls for the Win32 Internet function to redo the request.';
    12033 : {ERROR_INTERNET_INVALID_PROXY_REQUEST} Result := 'The request to the proxy was invalid.';
    12036 : {ERROR_INTERNET_HANDLE_EXISTS} Result := 'The request failed because the handle already exists.';
    12037 : {ERROR_INTERNET_SEC_CERT_DATE_INVALID} Result := 'SSL certificate date that was received from the server is bad. The certificate is expired.';
    12038 : {ERROR_INTERNET_SEC_CERT_CN_INVALID} Result := 'SSL certificate common name (host name field) is incorrect. For example, if you entered www.server.com and the common name on the certificate says www.different.com.';
    12039 : {ERROR_INTERNET_HTTP_TO_HTTPS_ON_REDIR} Result := 'The application is moving from a non-SSL to an SSL connection because of a redirect.';
    12040 : {ERROR_INTERNET_HTTPS_TO_HTTP_ON_REDIR} Result := 'The application is moving from an SSL to an non-SSL connection because of a redirect.';
    12041 : {ERROR_INTERNET_MIXED_SECURITY} Result := 'Indicates that the content is not entirely secure. Some of the content being viewed may have come from unsecured servers.';
    12042 : {ERROR_INTERNET_CHG_POST_IS_NON_SECURE} Result := 'The application is posting and attempting to change multiple lines of text on a server that is not secure.';
    12043 : {ERROR_INTERNET_POST_IS_NON_SECURE} Result := 'The application is posting data to a server that is not secure.';
    12110 : {ERROR_FTP_TRANSFER_IN_PROGRESS} Result := 'The requested operation cannot be made on the FTP session handle because an operation is already in progress.';
    12111 : {ERROR_FTP_DROPPED} Result := 'The FTP operation was not completed because the session was aborted.';
    12130 : {ERROR_GOPHER_PROTOCOL_ERROR} Result := 'An error was detected while parsing data returned from the gopher server.';
    12131 : {ERROR_GOPHER_NOT_FILE} Result := 'The request must be made for a file locator.';
    12132 : {ERROR_GOPHER_DATA_ERROR} Result := 'An error was detected while receiving data from the gopher server.';
    12133 : {ERROR_GOPHER_END_OF_DATA} Result := 'The end of the data has been reached.';
    12134 : {ERROR_GOPHER_INVALID_LOCATOR} Result := 'The supplied locator is not valid.';
    12135 : {ERROR_GOPHER_INCORRECT_LOCATOR_TYPE} Result := 'The type of the locator is not correct for this operation.';
    12136 : {ERROR_GOPHER_NOT_GOPHER_PLUS} Result := 'The requested operation can only be made against a Gopher+ server or with a locator that specifies a Gopher+ operation.';
    12137 : {ERROR_GOPHER_ATTRIBUTE_NOT_FOUND} Result := 'The requested attribute could not be located.';
    12138 : {ERROR_GOPHER_UNKNOWN_LOCATOR} Result := 'The locator type is unknown.';
    12150 : {ERROR_HTTP_HEADER_NOT_FOUND} Result := 'The requested header could not be located.';
    12151 : {ERROR_HTTP_DOWNLEVEL_SERVER} Result := 'The server did not return any headers.';
    12152 : {ERROR_HTTP_INVALID_SERVER_RESPONSE} Result := 'The server response could not be parsed.';
    12153 : {ERROR_HTTP_INVALID_HEADER} Result := 'The supplied header is invalid.';
    12154 : {ERROR_HTTP_INVALID_QUERY_REQUEST} Result := 'The request made to HttpQueryInfo is invalid.';
    12155 : {ERROR_HTTP_HEADER_ALREADY_EXISTS} Result := 'The header could not be added because it already exists.';
    12156 : {ERROR_HTTP_REDIRECT_FAILED} Result := 'The redirection failed because either the scheme changed for example, HTTP to FTP) or all attempts made to redirect failed (default is five attempts).';
  Else
    Result := SysErrorMessage(i);
  End;
End;

Constructor TAdvWinInetClient.Create;
Begin
  Inherited;
  FHeaders := TDictionary<String, String>.create;;

{$IFDEF VER130}
  Check(@mInternetOpen <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetOpenA', 0);
  Check(@mInternetConnect <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetConnectA', 0);
  Check(@mHttpQueryInfo <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpQueryInfoA', 0);
  Check(@mHttpOpenRequest <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpOpenRequestA', 0);
  Check(@mHttpSendRequest <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpSendRequestA', 0);
{$ELSE}
  Check(@mInternetOpen <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetOpenW', 0);
  Check(@mInternetConnect <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetConnectW', 0);
  Check(@mHttpQueryInfo <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpQueryInfoW', 0);
  Check(@mHttpOpenRequest <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpOpenRequestW', 0);
  Check(@mHttpSendRequest <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'HttpSendRequestW', 0);

{$ENDIF}
  Check(GHandle >= 32, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_DLL, DLL_WININET, 0);
  Check(@mInternetCloseHandle <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetCloseHandle', 0);
  Check(@mInternetQueryDataAvailable <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetQueryDataAvailable', 0);
  Check(@mInternetReadFile <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetReadFile', 0);
  Check(@mInternetQueryOption <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetQueryOption', 0);
  Check(@mInternetSetOption <> Nil, 'TAdvWinInetClient.Create', RS_ERR_WININET_NO_ROUTINE, 'InternetSetOption', 0);
  FUserAgent := 'Kestral Software';
End;


Destructor TAdvWinInetClient.Destroy;
Begin
  FHeaders.Free;
  Disconnect;

  FResponse.Free;
  FRequest.Free;

  Inherited;
  End;


Function TAdvWinInetClient.ErrorClass: EAdvExceptionClass;
  Begin
  Result := EAdvWinInetClient;
  End;


Function TAdvWinInetClient.ErrorMessage(Const sDetail : String; iError : Cardinal) : String;
Begin
  If iError = 0 Then
    Result := '[' + sDetail + ']'
  Else If SysErrorMessage(iError) <> '' Then
    Result := '[' + sDetail + ' ' + SysErrorMessage(iError) + ']'
  Else If InetErrorMessage(iError) <> '' Then
    Result := '[' + sDetail + ' ' + InetErrorMessage(iError) + ']'
  Else
    Result := '[' + sDetail + ' #' + IntToStr(iError) + ']';
End;


Procedure TAdvWinInetClient.Check(ACondition : Boolean; Const ALocation, ADescription, aDetail : String; iError : Cardinal);
Begin
  If Not ACondition Then
    Error(ALocation, ADescription + ' ' + ErrorMessage(aDetail, iError));
End;


Procedure TAdvWinInetClient.Execute;
Var
  aReqHandle : HINTERNET;
  sHeaders : String;
  pData : Pointer;
  iSize : DWord;
  bOk : Boolean;
  oResponse : TAdvMemoryStream;
  dwFlags : DWORD;
  dwBuffLen : DWORD;
Begin
  Assert(Invariants('Execute', FResponse, TAdvBuffer, 'Response'));
  if assigned(FOnProgress) then
    FOnProgress(self, 'Connecting');
  Connect;

  sHeaders := '';

  If FSoapAction <> '' Then
    sHeaders := sHeaders + 'SOAPAction: "'+FSoapAction+'"'+cReturn;

  If FRequestType <> '' Then
    sHeaders := sHeaders + 'Content-Type: '+ FRequestType +cReturn;
  If FResponseType <> '' Then
    sHeaders := sHeaders + 'Accept: '+ FResponseType +cReturn;

  If FSecure Then
    aReqHandle := mHttpOpenRequest(FConnection, PChar(FRequestMethod), PChar(FResource), Nil, Nil, Nil,
        INTERNET_FLAG_SECURE Or INTERNET_FLAG_IGNORE_CERT_CN_INVALID Or INTERNET_FLAG_IGNORE_CERT_DATE_INVALID Or INTERNET_FLAG_IGNORE_REDIRECT_TO_HTTP Or
        INTERNET_FLAG_KEEP_CONNECTION Or INTERNET_FLAG_NO_AUTO_REDIRECT Or INTERNET_FLAG_NO_CACHE_WRITE Or INTERNET_FLAG_PRAGMA_NOCACHE, 0)
  Else
    aReqHandle := mHttpOpenRequest(FConnection, PChar(FRequestMethod), PChar(FResource), Nil, Nil, Nil,
        INTERNET_FLAG_KEEP_CONNECTION Or INTERNET_FLAG_NO_AUTO_REDIRECT Or INTERNET_FLAG_NO_CACHE_WRITE Or INTERNET_FLAG_PRAGMA_NOCACHE, 0);

  Check(aReqHandle <> Nil, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_REQ_OPEN, FResource, GetLastError);
  Try
    If FSecure Then
    Begin
      dwBuffLen := 4;
      bOk := mInternetQueryOption(aReqHandle, INTERNET_OPTION_SECURITY_FLAGS, pointer(@dwFlags), dwBuffLen);
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_QUERY_OPTION, FResource, GetLastError);
      dwFlags := dwFlags Or SECURITY_FLAG_IGNORE_UNKNOWN_CA;
      bOk := mInternetSetOption(aReqHandle, INTERNET_OPTION_SECURITY_FLAGS, @dwFlags, dwBuffLen);
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_SET_OPTION, FResource, GetLastError);
    End;

    // username and password
    If FUsername <> '' Then
    Begin
      bOk := mInternetSetOption(aReqHandle, INTERNET_OPTION_USERNAME, pchar(FUsername), Length(FUsername));
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_SET_OPTION, FResource, GetLastError);
    End;
    If FPassword <> '' Then
    Begin
      bOk := mInternetSetOption(aReqHandle, INTERNET_OPTION_PASSWORD, pchar(FPassword), Length(FPassword));
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_SET_OPTION, FResource, GetLastError);
    End;
    If FProxyUsername <> '' Then
    Begin
      bOk := mInternetSetOption(aReqHandle, INTERNET_OPTION_PROXY_USERNAME, pchar(FProxyUsername), Length(FProxyUsername));
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_SET_OPTION, FResource, GetLastError);
    End;
    If FProxyPassword <> '' Then
    Begin
      bOk := mInternetSetOption(aReqHandle, INTERNET_OPTION_PROXY_PASSWORD, pchar(FProxyPassword), Length(FProxyPassword));
      Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_SET_OPTION, FResource, GetLastError);
    End;

    If Assigned(FRequest) Then
      bOk := mHttpSendRequest(aReqHandle, pchar(sHeaders), Length(sHeaders), FRequest.Data, FRequest.Capacity)
    Else
      bOk := mHttpSendRequest(aReqHandle, pchar(sHeaders), Length(sHeaders), Nil, 0);

    Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_REQ_SEND, FResource, GetLastError);

    oResponse := TAdvMemoryStream.Create;
    GetMem(pData, 1024);
    Try
      FResponseCode := GetHeader(aReqHandle, HTTP_QUERY_STATUS_CODE);

      If Not IgnoreContentTypeHeader Then
      FResponseType := GetHeader(aReqHandle, HTTP_QUERY_CONTENT_TYPE);

      If Pos(';', FResponseType) > 0 Then
        FResponseType := Copy(FResponseType, 1, Pos(';', FResponseType) - 1);

      Repeat
        FillChar(pData^, 1024, #0);
        bOk := mInternetReadFile(aReqHandle, pData, 1024, iSize);

        // TODO: non-exception when reponse fails?
        Check(bOk, 'TAdvWinInetClient.DoExecute', RS_OP_WININET_READ, FResource, GetLastError);

        if assigned(FOnProgress) then
          FOnProgress(self, 'Receiving ('+ inttostr(oResponse.Size)+' bytes)');

        If iSize > 0 Then
          oResponse.Write(pData^, iSize);
      Until bOk And (iSize = 0);

      FResponse.Copy(oResponse.Buffer);
    Finally
      FreeMem(pData);
      oResponse.Free;
    End;
  Finally
    mInternetCloseHandle(aReqHandle);
  End;
End;


Function TAdvWinInetClient.GetHeader(ARequest: HInternet; AHeader: DWord): String;
Var
  pData : pointer;
  iSize : DWord;
  iIndex : DWord;
  bOk : Boolean;
Begin
  iSize := 1024;
  GetMem(pData, iSize);
  Try
    iIndex := 0;
    If Not mHttpQueryInfo(ARequest, AHeader, pData, iSize, iIndex) Then
    Begin
      If GetLastError = ERROR_INSUFFICIENT_BUFFER Then
      Begin
        FreeMem(pData);
        Getmem(pData, iSize);
        bOk := mHttpQueryInfo(ARequest, AHeader, pData, iSize, iIndex);
        Check(bOk, 'TAdvWinInetClient.GetHeader', RS_OP_WININET_QUERY, '2', GetLastError);
      End
      Else
      Begin
        Check(False, 'TAdvWinInetClient.GetHeader', RS_OP_WININET_QUERY, '1', GetLastError);
      End;
    End;

    If iSize <> 0 Then
    Begin
      SetLength(Result, iSize div sizeof(char));
      move(pData^, Result[1], iSize);
    End
    Else
    Begin
      Result := '';
    End;
  Finally
    FreeMem(pData);
  End;
End;


function TAdvWinInetClient.getResponseHeader(name: String): String;
begin

end;

Procedure TAdvWinInetClient.Connect;
Var
  sProxy : String;
Begin
  If FSession = Nil Then
  Begin
    If FUseWindowsProxySettings Then
      FSession := mInternetOpen(pchar(FUserAgent), INTERNET_OPEN_TYPE_PRECONFIG, Nil, Nil, 0)
    Else If FProxyServer <> '' Then
    Begin
      If FProxySecure Then
        sProxy := 'https://'+FProxyServer+':'+IntegerToString(FProxyPort)
      Else
        sProxy := 'http://'+FProxyServer+':'+IntegerToString(FProxyPort);
      FSession := mInternetOpen(pchar(FUserAgent), INTERNET_OPEN_TYPE_PROXY, pchar(sProxy), Nil, 0);
    End
    Else
      FSession := mInternetOpen(pchar(FUserAgent), INTERNET_OPEN_TYPE_DIRECT, Nil, Nil, 0);
  End;

  If FConnection = Nil Then
  Begin
    If FSecure Then
      FConnection := mInternetConnect(FSession, pchar(FServer), StrToIntDef(FPort, INTERNET_DEFAULT_HTTPS_PORT), Nil, Nil, INTERNET_SERVICE_HTTP, 0, 0)
    Else
      FConnection := mInternetConnect(FSession, pchar(FServer), StrToIntDef(FPort, INTERNET_DEFAULT_HTTP_PORT), Nil, Nil, INTERNET_SERVICE_HTTP, 0, 0);
  End;
End;


Procedure TAdvWinInetClient.Disconnect;
Begin
  If FConnection <> Nil Then
  Begin
    mInternetCloseHandle(FConnection);
    FConnection := Nil;
  End;

  If FSession <> Nil Then
  Begin
    mInternetCloseHandle(FSession);
    FSession := Nil;
  End;
End;


Procedure TAdvWinInetClient.SetResponse(Const Value: TAdvBuffer);
Begin
  FResponse.Free;
  FResponse := Value;
End;

Procedure TAdvWinInetClient.SetRequest(Const Value: TAdvBuffer);
Begin
  FRequest.Free;
  FRequest := Value;
End;

Procedure TAdvWinInetClient.SetUserAgent(Const Value: String);
Begin
  Disconnect;
  FUserAgent := Value;
End;

Procedure TAdvWinInetClient.SetPort(Const Value: String);
Begin
  Disconnect;
  FPort := Value;
End;

Procedure TAdvWinInetClient.SetResource(Const Value: String);
Begin
  Disconnect;
  FResource := Value;
End;

Procedure TAdvWinInetClient.SetServer(Const Value: String);
Begin
  Disconnect;
  FServer := Value;
End;

Procedure TAdvWinInetClient.SetSecure(Const Value: Boolean);
Begin
  Disconnect;
  FSecure := Value;
End;


Procedure TAdvWinInetClient.SetProxyPort(Const Value: Integer);
Begin
  Disconnect;
  FProxyPort := Value;
End;


Procedure TAdvWinInetClient.SetProxySecure(Const Value: Boolean);
Begin
  Disconnect;
  FProxySecure := Value;
End;


Procedure TAdvWinInetClient.SetProxyServer(Const Value: String);
Begin
  Disconnect;
  FProxyServer := Value;
End;


Procedure TAdvWinInetClient.SetUseWindowsProxySettings(Const Value: Boolean);
Begin
  Disconnect;
  FUseWindowsProxySettings := Value;
End;


procedure TAdvWinInetClient.SetAddress(Const Value: String);
Var
  sTemp : String;
  sLeft : String;
  iIndex : Integer;
Begin
  StringSplit(Value, '://', sLeft, sTemp);
  If (sLeft = 'http') or (sLeft = 'ftp') Then
    FSecure := False
  Else If sLeft = 'https' Then
    FSecure := True
  Else
    Error('GetServerAddress', 'The protocol '+sLeft+' is not supported');
  iIndex := StringFind(sTemp, ['?', '/']);
  If iIndex = 0 Then
  Begin
    FServer := sTemp;
    FResource := '';
  End
  Else
  Begin
    FServer := Copy(sTemp, 1, iIndex - 1);
    FResource := Copy(sTemp, iIndex, $FFF);
  End;
  If (StringFind(FServer, ':') > 0) Then
  Begin
    FPort := Copy(FServer, StringFind(FServer, ':')+1, $FF);
    FServer := Copy(FServer, 1, StringFind(FServer, ':')-1);
  End
  Else If FSecure Then
    FPort := '443'
  Else
    FPort := '80';
End;


Initialization
  LoadDLL;
Finalization
  UnloadDLL;
End.

