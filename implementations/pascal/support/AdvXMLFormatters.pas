Unit AdvXMLFormatters;

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
  SysUtils, StringSupport, EncodeSupport,
  AdvStringBuilders, AdvXMLEntities,
  AdvTextFormatters, AdvStringMatches;


Type
  TAdvXMLFormatter = Class(TAdvTextFormatter)
    Private
      FAttributes : TAdvXMLAttributeList;
      FBuilder : TAdvStringBuilder;
      FLine : integer;
      FCol : integer;
      FLastText : boolean;
      FPending : string;
      FNoDense : Boolean;

      procedure updateForText(s : String);
      procedure commitPending;
    Protected
      Function UseAttributes : String;

      Procedure ProducePretty(sValue : String);
    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvXMLFormatter;
      Function Clone : TAdvXMLFormatter; 

      Procedure ProduceHeader; 

      Procedure ProduceOpen(Const sName : String);
      Procedure ProduceClose(Const sName : String);
      Procedure ProduceTag(Const sName : String);
      Procedure ProducePI(Const sName, sText : String);
      Procedure ProduceText(Const sName, sValue : String); Overload;
      Procedure ProduceTextNoEscapeEoln(Const sName, sValue: String);
      Procedure ProduceText(Const sValue : String; processing : TEolnOption = eolnEscape); Overload;
      Procedure ProduceComment(Const sComment : String);
      Procedure ProduceCData(Const sText : String);

      Procedure AddAttribute(Const sName, sValue : String; sNs : String = ''); // ns is only used for sorting in canonical mode
      Procedure AddNamespace(Const sAbbreviation, sURI : String);

      Property Attributes : TAdvXMLAttributeList Read FAttributes;
      property Line : integer read FLine;
      property Col : integer read FCol;
      property NoDense : Boolean read FNoDense write FNoDense;
  End;


Implementation


Constructor TAdvXMLFormatter.Create;
Begin
  Inherited;

  FBuilder := TAdvStringBuilder.Create;
  FAttributes := TAdvXMLAttributeList.Create;
  FLine := 1;
  FCol := 0;
  FLastText := true;
End;


Destructor TAdvXMLFormatter.Destroy;
Begin
  FAttributes.Free;
  FBuilder.Free;

  Inherited;
End;  


Function TAdvXMLFormatter.Clone : TAdvXMLFormatter;
Begin 
  Result := TAdvXMLFormatter(Inherited Clone);
End;  


procedure TAdvXMLFormatter.commitPending;
begin
  if (FPending <> '') then
  begin
    LevelUp;
    ProducePretty('<'+FPending+'>');
    LevelDown;
    FPending := '';
  end;
end;

Function TAdvXMLFormatter.Link : TAdvXMLFormatter;
Begin 
  Result := TAdvXMLFormatter(Inherited Link);
End;  


Procedure TAdvXMLFormatter.ProduceHeader;
Begin 
  ProducePretty('<?xml version="1.0"' + UseAttributes + '?>');
  ProducePretty('');
End;  


Procedure TAdvXMLFormatter.ProduceOpen(Const sName : String);
Begin 
  Assert(Condition(sName <> '', 'ProduceOpen', 'Open tag name must be specified.'));

  commitPending;

  FLastText := false;

  FPending := sName + UseAttributes;

  LevelDown;

  if FNoDense then
    CommitPending;
End;  


procedure TAdvXMLFormatter.ProduceCData(const sText: String);
begin
  commitPending;

  FLastText := false;
  ProducePretty('<![CDATA[' + sText + ']]>');
end;

Procedure TAdvXMLFormatter.ProduceClose(Const sName: String);
Begin 
  Assert(Condition(sName <> '', 'ProduceClose', 'Close tag name must be specified.'));

  LevelUp;

  if FPending <> '' then
  begin
    ProducePretty('<' + FPending + '/>');
    FPending := '';
  end
  else
    ProducePretty('</' + sName + '>');
  FLastText := false;
End;  


Procedure TAdvXMLFormatter.ProduceTextNoEscapeEoln(Const sName, sValue: String);
Begin
  Assert(Condition(sName <> '', 'ProduceText', 'Tag name for text must be specified.'));
  commitPending;

  FLastText := false;
  ProducePretty('<' + sName + UseAttributes + '>' + EncodeXML(sValue, xmlText) + '</' + sName + '>');
End;


procedure TAdvXMLFormatter.updateForText(s: String);
var
  i : integer;
begin
  i := 1;
  while i <= length(s) do
  begin
    if CharInSet(s[i], [#10, #13]) then
    begin
      inc(Fline);
      Fcol := 0;
      if (i < length(s)) and (s[i+1] <> s[i]) and CharInSet(s[i+1], [#10, #13]) then
        inc(i);
    end
    else
      inc(Fcol);
    inc(i);
  end;
End;


Procedure TAdvXMLFormatter.ProduceText(Const sName, sValue: String);
Begin
  Assert(Condition(sName <> '', 'ProduceText', 'Tag name for text must be specified.'));

  commitPending;
  FLastText := false;
  ProducePretty('<' + sName + UseAttributes + '>' + EncodeXML(sValue, xmlText) + '</' + sName + '>');
End;


Procedure TAdvXMLFormatter.ProduceText(Const sValue: String; processing : TEolnOption = eolnEscape);
var
  s : String;
Begin
  if sValue = '' then
    exit;

  commitPending;

  s := EncodeXML(sValue, xmlText, processing);
  Produce(s); // no pretty - might be a sequence of text
  updateForText(s);
  FLastText := true;
End;  


Procedure TAdvXMLFormatter.ProduceTag(Const sName: String);
Begin 
  Assert(Condition(sName <> '', 'ProduceTag', 'Tag name must be specified.'));
  commitPending;

  FLastText := false;

  ProducePretty('<' + sName + UseAttributes + ' />');
End;  


Procedure TAdvXMLFormatter.ProduceComment(Const sComment: String);
Begin
  commitPending;

  FLastText := false;
  ProducePretty('<!--' + sComment + '-->');
End;


Function TAdvXMLFormatter.UseAttributes : String;
Var
  iLoop : Integer;
Begin
  Result := '';
  For iLoop := 0 To FAttributes.Count - 1 Do
    Result := Result + SysUtils.Format(' %s="%s"', [FAttributes[iLoop].Name, EncodeXml(FAttributes[iLoop].Value, xmlAttribute)]);

  FAttributes.Clear;
End;


Procedure TAdvXMLFormatter.AddAttribute(Const sName, sValue : String; sNs : String = '');
var
  attr : TAdvXMLAttribute;
Begin
  attr := TAdvXMLAttribute.Create;
  try
    attr.Name := sName;
    attr.Value := sValue;
    if sNs <> '' then
      attr.SortKey := sNs+#1+sName;
    FAttributes.Add(Attr.Link);
  finally
    attr.Free;
  end;
End;


Procedure TAdvXMLFormatter.AddNamespace(Const sAbbreviation, sURI : String);
Begin
  If sAbbreviation = '' Then
    AddAttribute('xmlns', sURI, #0)
  Else
    AddAttribute('xmlns:' + sAbbreviation, sURI, #0);
End;

procedure TAdvXMLFormatter.ProducePI(const sName, sText: String);
begin
  Assert(Condition(sName <> '', 'ProducePI', 'PI name must be specified.'));
  commitPending;

  FLastText := false;

  if sText <> '' then
    ProducePretty('<?' + sName + ' ' + sText+ '?>')
  else
    ProducePretty('<?' + sName + UseAttributes + sText+ '?>');
end;

procedure TAdvXMLFormatter.ProducePretty(sValue: String);
var
  s : string;
begin
  if HasWhitespace and not FLastText then
    s := #13#10 + BeforeWhitespace+sValue
  else
    s := sValue;
  if (s <> '') then
  begin
    Produce(s);
    UpdateForText(s);
  end;
end;

End. // AdvXMLFormatters //
