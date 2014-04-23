Unit ErrorSupport;

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
  Windows, SysUtils,
  MemorySupport, StringSupport;

Function ErrorAsString : String; Overload;
Function ErrorAsString(iError : Integer) : String; Overload;
Function ErrorAsMessage(iError : Integer) : String;
Function ErrorAsNumber : Integer; Overload;

Implementation

Function ErrorAsNumber : Integer;
Begin
  Result := GetLastError;
  SetLastError(0);
End;

Function ErrorAsString(iError : Integer) : String;
Begin
  Result := StringFormat('%d: %s', [iError, ErrorAsMessage(iError)]);
End;


Function ErrorAsMessage(iError : Integer) : String;
Var
{$IFDEF VER200}
  sTemp : PWideChar;
{$ELSE}
  sTemp : PChar;
{$ENDIF}
  iSize : Cardinal;
Begin
  iSize := 512;

  MemoryCreate(sTemp, iSize);
  Try
    // Get the last error number and convert it to text
    If FormatMessage(FORMAT_MESSAGE_FROM_SYSTEM Or FORMAT_MESSAGE_ARGUMENT_ARRAY, Nil, DWORD(iError), LANG_NEUTRAL, sTemp, iSize, Nil) <> 0 Then
      Result := StringTrimWhitespace(Copy(StrPas(sTemp), 1, iSize))
    Else
      Result := '';
  Finally
    MemoryDestroy(sTemp, iSize);
  End;
End;

Function ErrorAsString : String;
Var
  iError : Cardinal;
Begin
  iError := ErrorAsNumber;

  If iError = ERROR_SUCCESS Then
    Result := StringFormat('%d: Unknown Windows API error.', [iError])
  Else
    Result := ErrorAsString(iError);
End;

End.
