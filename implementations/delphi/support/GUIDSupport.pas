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
  bignum;


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
var
  sGuid, s : String;
  r1, r2, r3, r4 : int64;
  c : integer;
  b1, b2, b3, b4, bs : TBigNum;
Begin
  sGuid := GUIDToString(aGuid);
  s := copy(sGuid, 30, 8);
  Val('$'+s, r1, c);
  s := copy(sGuid, 21, 4)+copy(sGuid, 26, 4);
  Val('$'+s, r2, c);
  s := copy(sGuid, 11, 4)+copy(sGuid, 26, 4);
  Val('$'+s, r3, c);
  s := copy(sGuid, 2, 8);
  Val('$'+s, r4, c);

  b1 := TBigNum.Create;
  b2 := TBigNum.Create;
  b3 := TBigNum.Create;
  b4 := TBigNum.Create;
  bs := TBigNum.Create;
  Try
    b1.AsString := IntToStr(r1);
    b2.AsString := IntToStr(r2);
    b3.AsString := IntToStr(r3);
    b4.AsString := IntToStr(r4);
    bs.AsString := '4294967296';
    b2.Multiply(bs);
    bs.AsString := '18446744073709551616';
    b3.Multiply(bs);
    bs.AsString := '79228162514264337593543950336';
    b4.Multiply(bs);
    b1.Add(b2);
    b1.Add(b3);
    b1.Add(b4);
    result := '2.25.'+b1.AsString;
  Finally
    b1.Free;
    b2.Free;
    b3.Free;
    b4.Free;
    bs.Free;
  End;
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
      (s[01] in ['a'..'f', '0'..'9']) and
      (s[02] in ['a'..'f', '0'..'9']) and
      (s[03] in ['a'..'f', '0'..'9']) and
      (s[04] in ['a'..'f', '0'..'9']) and
      (s[05] in ['a'..'f', '0'..'9']) and
      (s[06] in ['a'..'f', '0'..'9']) and
      (s[07] in ['a'..'f', '0'..'9']) and
      (s[08] in ['a'..'f', '0'..'9']) and
      (s[09] = '-') and
      (s[10] in ['a'..'f', '0'..'9']) and
      (s[11] in ['a'..'f', '0'..'9']) and
      (s[12] in ['a'..'f', '0'..'9']) and
      (s[13] in ['a'..'f', '0'..'9']) and
      (s[14] = '-') and
      (s[15] in ['a'..'f', '0'..'9']) and
      (s[16] in ['a'..'f', '0'..'9']) and
      (s[17] in ['a'..'f', '0'..'9']) and
      (s[18] in ['a'..'f', '0'..'9']) and
      (s[19] = '-') and
      (s[20] in ['a'..'f', '0'..'9']) and
      (s[21] in ['a'..'f', '0'..'9']) and
      (s[22] in ['a'..'f', '0'..'9']) and
      (s[23] in ['a'..'f', '0'..'9']) and
      (s[24] = '-') and
      (s[25] in ['a'..'f', '0'..'9']) and
      (s[26] in ['a'..'f', '0'..'9']) and
      (s[27] in ['a'..'f', '0'..'9']) and
      (s[28] in ['a'..'f', '0'..'9']) and
      (s[29] in ['a'..'f', '0'..'9']) and
      (s[30] in ['a'..'f', '0'..'9']) and
      (s[31] in ['a'..'f', '0'..'9']) and
      (s[32] in ['a'..'f', '0'..'9']) and
      (s[33] in ['a'..'f', '0'..'9']) and
      (s[34] in ['a'..'f', '0'..'9']) and
      (s[35] in ['a'..'f', '0'..'9']) and
      (s[36] in ['a'..'f', '0'..'9']);
  end;
end;

function NewGuidURN : String;
begin
  result := 'urn:uuid:'+copy(GUIDToString(CreateGUID), 2, 36);
end;


End. // GUIDSupport //
