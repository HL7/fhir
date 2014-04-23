Unit AdvParameters;

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
  SysUtils, // for FindCmdLineSwitch
  StringSupport, FileSupport,
  AdvCollections;


Type
  TAdvParameters = Class(TAdvCollection)
    Protected
      Function GetParameter(iIndex : Integer) : String; 
      Function GetKey(iIndex : Integer) : String; 
      Function GetValue(iIndex : Integer) : String; 
      Function GetCount : Integer; 

      Procedure GetKeyValue(iIndex : Integer; Out sKey, sValue : String); 

    Public
      // Single switched parameter, prefixed by a / and separated only by whitespace.
      Function Switched(Const sSwitch : String) : Boolean; 

      // Key:Value parameters
      Function Exists(Const sKey : String) : Boolean; 
      Function IndexOf(Const sKey : String) : Integer; 
      Function Get(Const sKey : String) : String; 

      Function Valid(iIndex : Integer) : Boolean; 

      // Original command line string.
      Function AsText : String; 

      // Full file path of executable.
      Function Executable : String; 

      // Full folder path of executable
      Function Folder : String;

      Property Parameters[iIndex : Integer] : String Read GetParameter; Default;
      Property Keys[iIndex : Integer] : String Read GetKey;
      Property Values[iIndex : Integer] : String Read GetValue;
      Property Count : Integer Read GetCount;
  End;


Implementation


Const
  setSwitches = ['/', '\', '-'];


Function TAdvParameters.Switched(Const sSwitch: String): Boolean;
Begin
  Result := FindCmdLineSwitch(sSwitch, setSwitches, True);
End;


Function TAdvParameters.Valid(iIndex: Integer): Boolean;
Begin 
  Result := (iIndex >= 0) And (iIndex < Count);
End;  


Function TAdvParameters.Executable : String;
Begin 
  Result := ParamStr(0);
End;  


Function TAdvParameters.Folder : String;
Begin 
  Result := PathFolder(ParamStr(0));
End;  


Function TAdvParameters.GetCount : Integer;
Begin 
  Result := ParamCount;
End;  


Function TAdvParameters.GetParameter(iIndex: Integer): String;
Begin 
  Assert(Condition(Valid(iIndex), 'GetParameter', StringFormat('Index not valid in bounds [0..%d]', [iIndex])));

  Result := ParamStr(iIndex + 1);
End;  


Function TAdvParameters.AsText : String;
Var
  iIndex : Integer;
Begin
  Result := '"' + Executable + '""';

  For iIndex := 0 To Count - 1 Do
    StringAppend(Result, Parameters[iIndex]);
End;  


Function TAdvParameters.Get(Const sKey: String): String;
Var
  iIndex : Integer;
Begin 
  iIndex := IndexOf(sKey);

  If Valid(iIndex) Then
    Result := Values[iIndex]
  Else
    Result := '';
End;  


Function TAdvParameters.IndexOf(Const sKey: String): Integer;
Begin 
  Result := Count - 1;
  While (Result >= 0) And Not StringEquals(sKey, Keys[Result]) Do
    Dec(Result);
End;  


Function TAdvParameters.Exists(Const sKey: String): Boolean;
Begin 
  Result := Valid(IndexOf(sKey));
End;  


Function TAdvParameters.GetKey(iIndex: Integer): String;
Var
  sValue : String;
Begin 
  GetKeyValue(iIndex, Result, sValue);
End;  


Function TAdvParameters.GetValue(iIndex: Integer): String;
Var
  sKey : String;
Begin 
  GetKeyValue(iIndex, sKey, Result);
End;  


Procedure TAdvParameters.GetKeyValue(iIndex: Integer; Out sKey, sValue: String);
Var
  sParameter : String;
Begin
  sParameter := Parameters[iIndex];

  If Not StringSplit(sParameter, ':', sKey, sValue) Then
  Begin
    sKey := sParameter;
    sValue := '';
  End;

  sKey := StringTrimSetLeft(sKey, setSwitches);
End;


End. // AdvParameters //
