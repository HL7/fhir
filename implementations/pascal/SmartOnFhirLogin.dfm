object SmartOnFhirLoginForm: TSmartOnFhirLoginForm
  Left = 0
  Top = 0
  BorderIcons = []
  Caption = 'Server Authorization'
  ClientHeight = 673
  ClientWidth = 837
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  OnHide = FormHide
  OnShow = FormShow
  PixelsPerInch = 96
  TextHeight = 13
  object Panel1: TPanel
    Left = 0
    Top = 628
    Width = 837
    Height = 45
    Align = alBottom
    BevelOuter = bvNone
    TabOrder = 0
    DesignSize = (
      837
      45)
    object Button1: TButton
      Left = 754
      Top = 14
      Width = 75
      Height = 25
      Anchors = [akRight, akBottom]
      Cancel = True
      Caption = 'Cancel'
      ModalResult = 2
      TabOrder = 0
      OnClick = Button1Click
    end
    object Memo1: TMemo
      Left = 10
      Top = 14
      Width = 129
      Height = 29
      TabOrder = 1
      Visible = False
    end
    object Button2: TButton
      Left = 10
      Top = 16
      Width = 117
      Height = 25
      Caption = 'Copy browser log'
      TabOrder = 2
      OnClick = Button2Click
    end
  end
  object browser: TWebBrowser
    Left = 0
    Top = 0
    Width = 837
    Height = 628
    Align = alClient
    TabOrder = 1
    OnStatusTextChange = browserStatusTextChange
    OnCommandStateChange = browserCommandStateChange
    OnDownloadBegin = browserDownloadBegin
    OnDownloadComplete = browserDownloadComplete
    OnTitleChange = browserTitleChange
    OnBeforeNavigate2 = browserBeforeNavigate2
    OnNewWindow2 = browserNewWindow2
    OnNavigateComplete2 = browserNavigateComplete2
    OnDocumentComplete = browserDocumentComplete
    OnSetSecureLockIcon = browserSetSecureLockIcon
    OnFileDownload = browserFileDownload
    OnNavigateError = browserNavigateError
    OnUpdatePageStatus = browserUpdatePageStatus
    OnNewWindow3 = browserNewWindow3
    OnSetPhishingFilterStatus = browserSetPhishingFilterStatus
    OnWindowStateChanged = browserWindowStateChanged
    OnNewProcess = browserNewProcess
    OnThirdPartyUrlBlocked = browserThirdPartyUrlBlocked
    OnRedirectXDomainBlocked = browserRedirectXDomainBlocked
    OnBeforeScriptExecute = browserBeforeScriptExecute
    OnWebWorkerStarted = browserWebWorkerStarted
    OnWebWorkerFinsihed = browserWebWorkerFinsihed
    OnShowScriptError = browserShowScriptError
    ExplicitLeft = 390
    ExplicitTop = 300
    ExplicitWidth = 300
    ExplicitHeight = 150
    ControlData = {
      4C00000082560000E84000000000000000000000000000000000000000000000
      000000004C000000000000000000000001000000E0D057007335CF11AE690800
      2B2E126208000000000000004C0000000114020000000000C000000000000046
      8000000000000000000000000000000000000000000000000000000000000000
      00000000000000000100000000000000000000000000000000000000}
  end
end
