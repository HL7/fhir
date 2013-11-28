Unit AdvCSVFormatters;


{! 10 !}


Interface


Uses
  EncodeSupport,
  AdvTextFormatters, AdvStringLists;


Type
  TAdvCSVFormatter = Class(TAdvTextFormatter)
    Private
      FSeparator : Char;
      FQuote : Char;
      FHasQuote : Boolean;
      FEmptyLine : Boolean;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Procedure Clear; Override;

      Procedure ProduceEntryStringArray(Const aEntryStringArray : Array Of String);
      Procedure ProduceEntryStringList(oEntryStringList : TAdvStringList);
      Procedure ProduceEntry(Const sEntry : String);
      Procedure ProduceSeparator;

      Procedure ProduceNewLine; Override;

      Property Separator : Char Read FSeparator Write FSeparator;
      Property Quote : Char Read FQuote Write FQuote;
      Property HasQuote : Boolean Read FHasQuote Write FHasQuote;
  End;


Implementation


Constructor TAdvCSVFormatter.Create;
Begin
  Inherited;

  FSeparator := ',';
  FQuote := '"';
  FHasQuote := True;
  FEmptyLine := True;
End;


Destructor TAdvCSVFormatter.Destroy;
Begin
  Inherited;
End;


Procedure TAdvCSVFormatter.Clear;
Begin
  Inherited;

  FEmptyLine := True;
End;


Procedure TAdvCSVFormatter.ProduceEntryStringList(oEntryStringList: TAdvStringList);
Var
  iEntryIndex : Integer;
Begin
  For iEntryIndex := 0 To oEntryStringList.Count - 1 Do
    ProduceEntry(oEntryStringList[iEntryIndex]);
End;


Procedure TAdvCSVFormatter.ProduceEntryStringArray(Const aEntryStringArray: Array Of String);
Var
  iEntryIndex : Integer;
Begin
  For iEntryIndex := Low(aEntryStringArray) To High(aEntryStringArray) Do
    ProduceEntry(aEntryStringArray[iEntryIndex]);
End;


Procedure TAdvCSVFormatter.ProduceEntry(Const sEntry : String);
Begin
  If FEmptyLine Then
    FEmptyLine := False
  Else
    ProduceSeparator;

  If FHasQuote Then
    Produce(EncodeQuotedString(sEntry, FQuote))
  Else
    Produce(sEntry)
End;


Procedure TAdvCSVFormatter.ProduceNewLine;
Begin
  Inherited;

  FEmptyLine := True;
End;


Procedure TAdvCSVFormatter.ProduceSeparator;
Begin 
  Produce(FSeparator);
End;


End. // AdvCSVFormatters //
