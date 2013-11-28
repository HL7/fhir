Unit AdvXMLFormatters;


{! 12 !}


Interface


Uses
  SysUtils, StringSupport, EncodeSupport,
  AdvStringBuilders, AdvXMLEntities,
  AdvTextFormatters, AdvStringMatches;


Type
  TAdvXMLFormatter = Class(TAdvTextFormatter)
    Private
      FAttributes : TAdvXMLAttributeMatch;
      FBuilder : TAdvStringBuilder;

    Protected
      Function UseAttributes : String;
      Function EncodeAttribute(Const sValue : String) : String;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvXMLFormatter;
      Function Clone : TAdvXMLFormatter; 

      Procedure ProduceHeader; 

      Procedure ProduceOpen(Const sName : String);
      Procedure ProduceClose(Const sName : String);
      Procedure ProduceTag(Const sName : String);
      Procedure ProduceText(Const sName, sValue : String); Overload;
      Procedure ProduceTextNoEscapeEoln(Const sName, sValue: String);
      Procedure ProduceText(Const sValue : String); Overload;
      Procedure ProduceComment(Const sComment : String);

      Procedure AddAttribute(Const sName, sValue : String);
      Procedure AddNamespace(Const sAbbreviation, sURI : String);

      Property Attributes : TAdvXMLAttributeMatch Read FAttributes;
  End;


Implementation


Constructor TAdvXMLFormatter.Create;
Begin 
  Inherited;

  FBuilder := TAdvStringBuilder.Create;
  FAttributes := TAdvXMLAttributeMatch.Create;
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


Function TAdvXMLFormatter.Link : TAdvXMLFormatter;
Begin 
  Result := TAdvXMLFormatter(Inherited Link);
End;  


Procedure TAdvXMLFormatter.ProduceHeader;
Begin 
  ProduceLine('<?xml version="1.0"' + UseAttributes + '?>');
End;  


Procedure TAdvXMLFormatter.ProduceOpen(Const sName : String);
Begin 
  Assert(Condition(sName <> '', 'ProduceOpen', 'Open tag name must be specified.'));

  ProduceLine('<' + sName + UseAttributes + '>');

  LevelDown;
End;  


Procedure TAdvXMLFormatter.ProduceClose(Const sName: String);
Begin 
  Assert(Condition(sName <> '', 'ProduceClose', 'Close tag name must be specified.'));

  LevelUp;

  ProduceLine('</' + sName + '>');
End;  


Procedure TAdvXMLFormatter.ProduceTextNoEscapeEoln(Const sName, sValue: String);
Begin
  Assert(Condition(sName <> '', 'ProduceText', 'Tag name for text must be specified.'));

  Produce(BeforeWhitespace);

  Produce('<' + sName + UseAttributes + '>' + EncodeXML(sValue, False) + '</' + sName + '>');

  Produce(AfterWhitespace);
End;


Procedure TAdvXMLFormatter.ProduceText(Const sName, sValue: String);
Begin
  Assert(Condition(sName <> '', 'ProduceText', 'Tag name for text must be specified.'));

  Produce(BeforeWhitespace);

  Produce('<' + sName + UseAttributes + '>' + EncodeXML(sValue) + '</' + sName + '>');

  Produce(AfterWhitespace);
End;


Procedure TAdvXMLFormatter.ProduceText(Const sValue: String);
Begin 
  ProduceLine(EncodeXML(sValue));
End;  


Procedure TAdvXMLFormatter.ProduceTag(Const sName: String);
Begin 
  Assert(Condition(sName <> '', 'ProduceTag', 'Tag name must be specified.'));

  ProduceLine('<' + sName + UseAttributes + ' />');
End;  


Procedure TAdvXMLFormatter.ProduceComment(Const sComment: String);
Begin
  ProduceLine('<!--' + sComment + '-->');
End;


Function TAdvXMLFormatter.EncodeAttribute(Const sValue : String) : String;
Var
  iLoop : Integer;
Begin
  FBuilder.Clear;

  For iLoop := 1 To Length(sValue) Do
  Begin
    Case sValue[iLoop] Of
      #0..#31, #127..#255 : FBuilder.Append('&#x' + inttohex(Ord(sValue[iLoop]), 2) + ';');
      '<' : FBuilder.Append('&lt;');
      '>' : FBuilder.Append('&gt;');
      '&' : FBuilder.Append('&amp;');
      '"' : FBuilder.Append('&quot;');
    Else if ord(sValue[iLoop]) > 255 Then
      FBuilder.Append('&#x' + inttohex(Ord(sValue[iLoop]), 4) + ';')
    Else
      FBuilder.Append(sValue[iLoop]);
    End;
  End;

  Result := FBuilder.AsString;
End;


Function TAdvXMLFormatter.UseAttributes : String;
Var
  iLoop : Integer;
Begin
  Result := '';
  For iLoop := 0 To FAttributes.Count - 1 Do
    Result := Result + SysUtils.Format(' %s="%s"', [FAttributes.KeyByIndex[iLoop], EncodeAttribute(FAttributes.ValueByIndex[iLoop])]);

  FAttributes.Clear;
End;


Procedure TAdvXMLFormatter.AddAttribute(Const sName, sValue : String);
Begin
  FAttributes.SetValueByKey(sName, sValue);
End;


Procedure TAdvXMLFormatter.AddNamespace(Const sAbbreviation, sURI : String);
Begin
  If sAbbreviation = '' Then
    AddAttribute('xmlns', sURI)
  Else
    AddAttribute('xmlns:' + sAbbreviation, sURI)
End;


End. // AdvXMLFormatters //
