Unit GUIDSupport;

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


// TGUID is declared in system


Function CreateGUID : TGUID;
Function GUIDToString(Const aGUID : TGUID) : String;
Function StringToGUID(Const sGUID: String) : TGUID;
Function NilGUID : TGUID;
Function GUIDAsOID(Const aGUID : TGUID) : String;
Function IsGuid(s : String): Boolean;
function NewGuidURN : String;


Implementation


Uses
  ActiveX,
  ComObj,
  SysUtils,
  DecimalSupport;


Function CreateGUID : TGUID;
Begin
  CoCreateGuid(Result);
End;


Function GUIDToString(Const aGUID : TGUID) : String;
Begin
  Result := ComObj.GUIDToString(aGUID);
End;


Function StringToGUID(Const sGUID: String) : TGUID;
Begin
  Result := ComObj.StringToGUID(sGUID);
End;


Function NilGUID : TGUID;
Begin
  Result := StringToGUID('{00000000-0000-0000-0000-000000000000}');
End;

Function GUIDAsOID(Const aGUID : TGUID) : String;
begin
  result := DecimalSupport.GUIDAsOIDRight(aGUID);
end;

Function IsGuid(s : String): Boolean;
begin
  if length(s) < 36  then
    result := false
  else
  begin
    if (s[1] = '{') then
    begin
      delete(s, 1, 1);
      delete(s, length(s), 1);
    end;
    s := lowercase(s);
    result := (Length(s) = 36) and
      CharInSet(s[01], ['a'..'f', '0'..'9']) and
      CharInSet(s[02], ['a'..'f', '0'..'9']) and
      CharInSet(s[03], ['a'..'f', '0'..'9']) and
      CharInSet(s[04], ['a'..'f', '0'..'9']) and
      CharInSet(s[05], ['a'..'f', '0'..'9']) and
      CharInSet(s[06], ['a'..'f', '0'..'9']) and
      CharInSet(s[07], ['a'..'f', '0'..'9']) and
      CharInSet(s[08], ['a'..'f', '0'..'9']) and
      (s[09] = '-') and
      CharInSet(s[10], ['a'..'f', '0'..'9']) and
      CharInSet(s[11], ['a'..'f', '0'..'9']) and
      CharInSet(s[12], ['a'..'f', '0'..'9']) and
      CharInSet(s[13], ['a'..'f', '0'..'9']) and
      (s[14] = '-') and
      CharInSet(s[15], ['a'..'f', '0'..'9']) and
      CharInSet(s[16], ['a'..'f', '0'..'9']) and
      CharInSet(s[17], ['a'..'f', '0'..'9']) and
      CharInSet(s[18], ['a'..'f', '0'..'9']) and
      (s[19] = '-') and
      CharInSet(s[20], ['a'..'f', '0'..'9']) and
      CharInSet(s[21], ['a'..'f', '0'..'9']) and
      CharInSet(s[22], ['a'..'f', '0'..'9']) and
      CharInSet(s[23], ['a'..'f', '0'..'9']) and
      (s[24] = '-') and
      CharInSet(s[25], ['a'..'f', '0'..'9']) and
      CharInSet(s[26], ['a'..'f', '0'..'9']) and
      CharInSet(s[27], ['a'..'f', '0'..'9']) and
      CharInSet(s[28], ['a'..'f', '0'..'9']) and
      CharInSet(s[29], ['a'..'f', '0'..'9']) and
      CharInSet(s[30], ['a'..'f', '0'..'9']) and
      CharInSet(s[31], ['a'..'f', '0'..'9']) and
      CharInSet(s[32], ['a'..'f', '0'..'9']) and
      CharInSet(s[33], ['a'..'f', '0'..'9']) and
      CharInSet(s[34], ['a'..'f', '0'..'9']) and
      CharInSet(s[35], ['a'..'f', '0'..'9']) and
      CharInSet(s[36], ['a'..'f', '0'..'9']);
  end;
end;

function NewGuidURN : String;
begin
  result := 'urn:uuid:'+copy(GUIDToString(CreateGUID), 2, 36);
end;


End. // GUIDSupport //
