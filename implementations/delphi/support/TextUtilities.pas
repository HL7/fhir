unit TextUtilities;

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

interface

Uses
  SysUtils, Classes;

function FormatTextToHTML(AStr: String): String; // translate ready for use in HTML
function FormatTextToXML(AStr: String): String;
function FormatXMLToHTML(AStr : String):String;

function StringToUTFStream(value : String):TStream;

implementation

uses
  math;



function FormatTextToHTML(AStr: String): String;
var
  LIsNewLine: Boolean;
  I: Integer;
  b : TStringBuilder;
begin
  Result := '';
  if Length(AStr) <= 0 then
    exit;
  b := TStringBuilder.Create;
  try
    LIsNewLine := True;
    i := 1;
    while i <= Length(AStr) do
      begin
      if (AStr[i] <> #32) and (LIsNewLine) then
        LIsNewLine := False;
      case AStr[i] of
        '''': b.Append('&#' + IntToStr(Ord(AStr[i])) + ';');
        '"': b.Append('&quot;');
        '&': b.Append('&amp;');
        '<': b.Append('&lt;');
        '>': b.Append('&gt;');
        #32:
          begin
          if LIsNewLine then
            b.Append('&nbsp;')
          else
            b.Append(' ');
          end;
        #13:
          begin
          if i < Length(AStr) then
            if AStr[i + 1] = #10 then
              Inc(i);
          b.Append('<br/>');
          LIsNewLine := True;
          end;
        else
          begin
          if AStr[i] in [' '..'~'] then
            b.Append(AStr[i])
          else
            b.Append('&#' + IntToStr(Ord(AStr[i])) + ';');
          end;
        end;
      Inc(i);
      end;
    result := b.ToString;
  finally
    b.free;
  end;
end;


function FormatTextToXML(AStr: String): String;
var
  i: Integer;
begin
  Result := '';
  if Length(AStr) <= 0 then
    exit;
  i := 1;
  while i <= Length(AStr) do
    begin
    case AStr[i] of
      '''':
        Result := Result + '&#' + IntToStr(Ord(AStr[i])) + ';';
      '"':
        Result := Result + '&quot;';
      '&':
        Result := Result + '&amp;';
      '<':
        Result := Result + '&lt;';
      '>':
        Result := Result + '&gt;';
      #32:
        Result := Result + ' ';
      #13:
        begin
        if i < length(AStr) then
          if AStr[i + 1] = #10 then
            Inc(i);
        Result := Result + ' ';
        end;
      else
        begin
        if AStr[i] in [' '..'~'] then
          Result := Result + AStr[i]
        else
          Result := Result + '&#' + IntToStr(Ord(AStr[i])) + ';';
        end;
      end;
    Inc(i);
    end;
end;

function FormatXMLToHTML(AStr : String):String;
var
  LIsNewLine: Boolean;
  I: Integer;
  LLen: Integer;
  LInAmp : Boolean;
  LInTag : boolean;
  LInAttr : Boolean;
  b : TStringBuilder;
begin
  LInAmp := false;
  LInTag := false;
  LInAttr := false;
  Result := '';
  if Length(AStr) <= 0 then
    exit;
  b := TStringBuilder.Create;
  try
    LIsNewLine := True;
    i := 1;
    while i <= Length(AStr) do
      begin
      if (AStr[i] <> #32) and (LIsNewLine) then
        LIsNewLine := False;
      case AStr[i] of
        '''':
          b.Append('&#' + IntToStr(Ord(AStr[i])) + ';');
        '"':
          begin
          if LInAttr then
            b.Append('</font>');
          b.Append('&quot;');
          if LInTag then
            begin
            if LInAttr then
              LInAttr := false
            else
             begin
             LInAttr := true;
             b.Append('<font color="Green">');
             end;
            end;
          end;
        '&':
          begin
          b.Append('<b>&amp;');
          LInAmp := true;
          end;
        ';':
          begin
          if LInAmp then
            b.Append(';</b>');
          LInAmp := false;
          end;
        '<':
          begin
          b.Append('<font color="navy">&lt;</font><font color="maroon">');
          LInTag := AStr[i+1] <> '!';
          end;
        '>':
          begin
          LInTag := false;
          b.Append('</font><font color="navy">&gt;</font>');
          end;
        #32:
          begin
          if LIsNewLine then
            b.Append('&nbsp;')
          else
            b.Append(' ');
          end;
        #13:
          begin
          if i < Length(AStr) then
            if AStr[i + 1] = #10 then
              Inc(i);
          b.Append('<br>');
          LIsNewLine := True;
          end;
        else
          begin
          if AStr[i] in [' '..'~'] then
            b.Append(AStr[i])
          else
            b.Append('&#' + IntToStr(Ord(AStr[i])) + ';');
          end;
        end;
      Inc(i);
      end;
    result := b.ToString;
  finally
    b.Free;
  end;
end;


function StringToUTFStream(value : String):TStream;
begin
  result := TBytesStream.Create(TEncoding.UTF8.GetBytes(value));
end;


end.
