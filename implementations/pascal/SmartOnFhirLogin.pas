unit SmartOnFhirLogin;


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

// see SmartOnFhirUtilities for doco

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, {$IFDEF NPPUNICODE} NppForms,{$ENDIF} Vcl.OleCtrls, Vcl.StdCtrls, Vcl.ExtCtrls,
  ActiveX, ole2, SHDocVw, IdContext, IdHTTPServer, IdCustomHTTPServer, IdSocketHandle, ParseMap, SmartOnFhirUtilities,
  GuidSupport;

const
  UMSG = WM_USER + 1;

type
  TSmartOnFhirLoginForm = class({$IFDEF NPPUNICODE}TNppForm{$ELSE} TForm {$ENDIF})
    Panel1: TPanel;
    Button1: TButton;
    browser: TWebBrowser;
    Memo1: TMemo;
    Button2: TButton;
    procedure browserBeforeNavigate2(ASender: TObject; const pDisp: IDispatch; const [Ref] URL, Flags, TargetFrameName, PostData, Headers: OleVariant; var Cancel: WordBool);
    procedure browserBeforeScriptExecute(ASender: TObject; const pDispWindow: IDispatch);
    procedure browserCommandStateChange(ASender: TObject; Command: Integer; Enable: WordBool);
    procedure browserDocumentComplete(ASender: TObject; const pDisp: IDispatch; const [Ref] URL: OleVariant);
    procedure browserDownloadBegin(Sender: TObject);
    procedure browserDownloadComplete(Sender: TObject);
    procedure browserFileDownload(ASender: TObject; ActiveDocument: WordBool; var Cancel: WordBool);
    procedure browserNavigateComplete2(ASender: TObject; const pDisp: IDispatch; const [Ref] URL: OleVariant);
    procedure browserNavigateError(ASender: TObject; const pDisp: IDispatch; const [Ref] URL, Frame, StatusCode: OleVariant; var Cancel: WordBool);
    procedure browserNewProcess(ASender: TObject; lCauseFlag: Integer; const pWB2: IDispatch; var Cancel: WordBool);
    procedure browserNewWindow2(ASender: TObject; var ppDisp: IDispatch; var Cancel: WordBool);
    procedure browserNewWindow3(ASender: TObject; var ppDisp: IDispatch; var Cancel: WordBool; dwFlags: Cardinal; const bstrUrlContext, bstrUrl: WideString);
    procedure browserRedirectXDomainBlocked(ASender: TObject; const pDisp: IDispatch; const [Ref] StartURL, RedirectURL, Frame, StatusCode: OleVariant);
    procedure browserSetPhishingFilterStatus(ASender: TObject; PhishingFilterStatus: Integer);
    procedure browserSetSecureLockIcon(ASender: TObject; SecureLockIcon: Integer);
    procedure browserShowScriptError(ASender: TObject; const [Ref] AErrorLine, AErrorCharacter, AErrorMessage, AErrorCode, AErrorUrl: OleVariant; var AOut: OleVariant; var AHandled: Boolean);
    procedure browserStatusTextChange(ASender: TObject; const Text: WideString);
    procedure browserThirdPartyUrlBlocked(ASender: TObject; const [Ref] URL: OleVariant; dwCount: Cardinal);
    procedure browserTitleChange(ASender: TObject; const Text: WideString);
    procedure browserUpdatePageStatus(ASender: TObject; const pDisp: IDispatch; const [Ref] nPage, fDone: OleVariant);
    procedure browserWebWorkerFinsihed(ASender: TObject; dwUniqueID: Cardinal);
    procedure browserWebWorkerStarted(ASender: TObject; dwUniqueID: Cardinal; const bstrWorkerLabel: WideString);
    procedure browserWindowStateChanged(ASender: TObject; dwWindowStateFlags, dwValidFlagsMask: Cardinal);
    procedure Button2Click(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure FormHide(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure FormDestroy(Sender: TObject);
  private
    { Private declarations }
    webserver : TIdHTTPServer;
    FServer : TRegisteredFHIRServer;
    FLogoPath: String;
    FScopes: String;
    FErrorMessage: String;
    FToken: TSmartOnFhirAccessToken;

    FInitialState : string;
    FFinalState : string;
    FAuthCode : String;
    FHandleError: boolean;
    procedure DoDone(var Msg: TMessage); message UMSG;
    procedure DoCommandGet(AContext: TIdContext; ARequestInfo: TIdHTTPRequestInfo; AResponseInfo: TIdHTTPResponseInfo);
    procedure SetToken(const Value: TSmartOnFhirAccessToken);
    procedure SetServer(const Value: TRegisteredFHIRServer);
  public
    { Public declarations }
    // in
    property logoPath : String read FLogoPath write FLogoPath;
    property server : TRegisteredFHIRServer read FServer write SetServer;
    property scopes : String read FScopes write FScopes;
    property handleError : boolean read FHandleError write FHandleError;

    // out
    // if modalResult = mrok, you'll get a token. otherwise, you'll get an error message
    property ErrorMessage : String read FErrorMessage write FErrorMessage;
    Property Token : TSmartOnFhirAccessToken read FToken write SetToken;
  end;

var
  SmartOnFhirLoginForm: TSmartOnFhirLoginForm;


implementation

{$R *.dfm}

{ TSmartOnFhirLoginForm }

procedure TSmartOnFhirLoginForm.DoCommandGet(AContext: TIdContext; ARequestInfo: TIdHTTPRequestInfo; AResponseInfo: TIdHTTPResponseInfo);
var
  s : TArray<String>;
  pm : TParseMap;
begin
  if ARequestInfo.Document = '/done' then
  begin
    s := ARequestInfo.RawHTTPCommand.Split([' ']);
    pm := TParseMap.create(s[1].Substring(6));
    try
      FAuthCode := pm.GetVar('code');
      FFinalState := pm.GetVar('state');
    finally
      pm.free;
    end;

    AResponseInfo.ResponseNo := 200;
    AResponseInfo.ResponseText := 'OK';
    AResponseInfo.ContentText := 'Checking Authorization, please wait...';
    PostMessage(handle, UMSG, 0, 0);
  end
  else
  begin
    AResponseInfo.ResponseNo := 200;
    AResponseInfo.ResponseText := 'OK';
    AResponseInfo.ContentStream := TFileStream.Create(FLogoPath, fmOpenRead + fmShareDenyWrite);
    AResponseInfo.FreeContentStream := true;
  end;
end;

procedure TSmartOnFhirLoginForm.FormDestroy(Sender: TObject);
begin
  inherited;
  FToken.Free;
  FServer.Free;
end;

procedure TSmartOnFhirLoginForm.FormHide(Sender: TObject);
begin
  webserver.Active := false;
  webserver.Free;
end;

procedure TSmartOnFhirLoginForm.FormShow(Sender: TObject);
var
  url : String;
  SHandle: TIdSocketHandle;
begin
  inherited;
  webserver := TIdHTTPServer.Create(nil);
  SHandle := webserver.Bindings.Add;
  SHandle.IP := '127.0.0.1';
  SHandle.Port := server.redirectPort;
  webserver.OnCommandGet := DoCommandGet;
  webserver.Active := true;

  FInitialState := NewGuidId;
  url := buildAuthUrl(server, scopes, FInitialState);
  browser.Navigate(url);
end;

procedure TSmartOnFhirLoginForm.SetServer(const Value: TRegisteredFHIRServer);
begin
  FServer.Free;
  FServer := Value;
end;

procedure TSmartOnFhirLoginForm.SetToken(const Value: TSmartOnFhirAccessToken);
begin
  FToken.Free;
  FToken := Value;
end;

procedure TSmartOnFhirLoginForm.DoDone(var Msg: TMessage);
begin
  Application.ProcessMessages;
  try
    if (FInitialState <> FFinalState) then
      raise Exception.create('State parameter mismatch ('+FInitialState+'/'+FFinalState+')');
    token := getSmartOnFhirAuthToken(server, FAuthcode);
    ModalResult := mrOK;
  except
    on e : Exception do
    begin
      if FHandleError then
        MessageDlg('Error converting Authorization Token to Access Token: ' +e.Message, mtError, [mbok], 0)
      else
        FErrorMessage := 'Error converting Authorization Token to Access Token: ' +e.Message;
      ModalResult := mrAbort;
    end;
  end;
end;

// courtesy of Internet Explorer's damned 'unfriendly error message' policy, we're almost
// certainly not going to have any idea what goes wrong if the OAuth process fails.
// but just in case, we're going to log everything we can, so the user can send it to
// us

procedure TSmartOnFhirLoginForm.Button2Click(Sender: TObject);
begin
  memo1.CopyToClipboard;
end;

procedure TSmartOnFhirLoginForm.browserBeforeNavigate2(ASender: TObject; const pDisp: IDispatch; const [Ref] URL, Flags, TargetFrameName, PostData, Headers: OleVariant; var Cancel: WordBool);
begin
  memo1.Text := memo1.Text + 'BeforeNavigate2: '+url+' '+headers+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserBeforeScriptExecute(ASender: TObject; const pDispWindow: IDispatch);
begin
  memo1.text := memo1.text + 'BeforeScriptExecute'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserCommandStateChange(ASender: TObject; Command: Integer; Enable: WordBool);
begin
  memo1.text := memo1.text + 'CommandStateChange: '+inttostr(command)+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserDocumentComplete(ASender: TObject; const pDisp: IDispatch; const [Ref] URL: OleVariant);
begin
  memo1.text := memo1.text + 'DocumentComplete'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserDownloadBegin(Sender: TObject);
begin
  memo1.text := memo1.text + 'DownloadBegin'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserDownloadComplete(Sender: TObject);
begin
  memo1.text := memo1.text + 'DownloadComplete'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserFileDownload(ASender: TObject; ActiveDocument: WordBool; var Cancel: WordBool);
begin
  memo1.text := memo1.text + 'FileDownload'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserNavigateComplete2(ASender: TObject; const pDisp: IDispatch; const [Ref] URL: OleVariant);
begin
  memo1.text := memo1.text + 'NavigateComplete2: '+URL+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserNavigateError(ASender: TObject; const pDisp: IDispatch; const [Ref] URL, Frame, StatusCode: OleVariant; var Cancel: WordBool);
var
  s : String;
begin
  s := VarToStr(statusCode);
  memo1.text := memo1.text + 'NavigateError: '+url+' '+s+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserNewProcess(ASender: TObject; lCauseFlag: Integer; const pWB2: IDispatch; var Cancel: WordBool);
begin
  memo1.text := memo1.text + 'NewProcess'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserNewWindow2(ASender: TObject; var ppDisp: IDispatch; var Cancel: WordBool);
begin
  memo1.text := memo1.text + 'NewWindow2'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserNewWindow3(ASender: TObject; var ppDisp: IDispatch; var Cancel: WordBool; dwFlags: Cardinal; const bstrUrlContext, bstrUrl: WideString);
begin
  memo1.text := memo1.text + 'NewWindow3'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserRedirectXDomainBlocked(ASender: TObject; const pDisp: IDispatch; const [Ref] StartURL, RedirectURL, Frame, StatusCode: OleVariant);
begin
  memo1.text := memo1.text + 'RedirectXDomainBlocked: '+StartURL+' ' +RedirectURL+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserSetPhishingFilterStatus(ASender: TObject; PhishingFilterStatus: Integer);
begin
  memo1.text := memo1.text + 'SetPhishingFilterStatus'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserSetSecureLockIcon(ASender: TObject; SecureLockIcon: Integer);
begin
  memo1.text := memo1.text + 'SetSecureLockIcon'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserShowScriptError(ASender: TObject; const [Ref] AErrorLine, AErrorCharacter, AErrorMessage, AErrorCode, AErrorUrl: OleVariant; var AOut: OleVariant; var AHandled: Boolean);
begin
  memo1.text := memo1.text + 'ShowScriptError: '+AErrorLine+' '+AErrorCharacter+' '+AErrorMessage+' '+AErrorCode+' '+AErrorUrl+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserStatusTextChange(ASender: TObject; const Text: WideString);
begin
  memo1.text := memo1.text + 'StatusTextChange: '+text+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserThirdPartyUrlBlocked(ASender: TObject; const [Ref] URL: OleVariant; dwCount: Cardinal);
begin
  memo1.text := memo1.text + 'ThirdPartyUrlBlocked: '+url+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserTitleChange(ASender: TObject; const Text: WideString);
begin
  memo1.text := memo1.text + 'TitleChange: '+text+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserUpdatePageStatus(ASender: TObject; const pDisp: IDispatch; const [Ref] nPage, fDone: OleVariant);
begin
  memo1.text := memo1.text + 'UpdatePageStatus'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserWebWorkerFinsihed(ASender: TObject; dwUniqueID: Cardinal);
begin
  memo1.text := memo1.text + 'WebWorkerFinsihed'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserWebWorkerStarted(ASender: TObject; dwUniqueID: Cardinal; const bstrWorkerLabel: WideString);
begin
  memo1.text := memo1.text + 'WebWorkerStarted'+#13#10;
end;

procedure TSmartOnFhirLoginForm.browserWindowStateChanged(ASender: TObject; dwWindowStateFlags, dwValidFlagsMask: Cardinal);
begin
  memo1.text := memo1.text + 'WindowStateChanged'+#13#10;
end;

procedure TSmartOnFhirLoginForm.Button1Click(Sender: TObject);
begin
  ErrorMessage := 'User Cancelled';
end;

end.


