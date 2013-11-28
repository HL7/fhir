Unit SystemSupport;


{! 23 !}


Interface


Uses
  SysUtils, Windows,
  DateSupport, StringSupport, ThreadSupport;

Function SystemTemp : String;

Implementation

Var
  gOSInfo : TOSVersionInfo;
  gSystemInfo : TSystemInfo;
  gNTDLLDebugBreakPointIssuePatched : Boolean = False;

Function SystemTemp : String;
Var
  iLength : Integer;
Begin
  SetLength(Result, MAX_PATH + 1);

  iLength := GetTempPath(MAX_PATH, PChar(Result));

  If Not IsPathDelimiter(Result, iLength) Then
  Begin
    Inc(iLength);
    Result[iLength] := '\';
  End;

  SetLength(Result, iLength);
End;

Function SystemIsWindowsNT : Boolean;
Begin
  Result := gOSInfo.dwPlatformId >= VER_PLATFORM_WIN32_NT;
End;

Function SystemIsWindows7 : Boolean;
Begin
  Result := SystemIsWindowsNT And (gOSInfo.dwMajorVersion >= 6) And (gOSInfo.dwMinorVersion >= 1);
End;

Initialization
  FillChar(gSystemInfo, SizeOf(gSystemInfo), 0);
  FillChar(gOSInfo, SizeOf(gOSInfo), 0);

  gOSInfo.dwOSVersionInfoSize := SizeOf(gOSInfo);

  GetVersionEx(gOSInfo);
  GetSystemInfo(gSystemInfo);

  If SystemIsWindows7 Then
  Begin
    // NOTE: Windows 7 changes the behaviour of GetThreadLocale.
    //       This is a workaround to force sysutils to use the correct locale.

    SetThreadLocale(GetUserDefaultLCID);
    SysUtils.GetFormatSettings;
  End;
End. // SystemSupport //
