Unit AdvCSVExtractors;


{! 5 !}


Interface


Uses
  SysUtils,
  StringSupport,
  AdvTextExtractors, AdvStringLists;


Type
  TAdvCSVExtractor = Class(TAdvTextExtractor)
    Private
      FSeparator : Char;
      FQuote : Char;
      FHasQuote : Boolean;

    Public
      Constructor Create; Override;

      Procedure ConsumeEntries(oEntries : TAdvStringList); Overload; 
      Procedure ConsumeEntries; Overload;
      Function ConsumeEntry : String;
      Function MoreEntries : Boolean;

      Property Separator : Char Read FSeparator Write FSeparator;
      Property Quote : Char Read FQuote Write FQuote;
      Property HasQuote : Boolean Read FHasQuote Write FHasQuote;
  End;


Implementation


Constructor TAdvCSVExtractor.Create;
Begin 
  Inherited;

  FSeparator := ',';
  FQuote := '"';
  FHasQuote := True;
End;  


Procedure TAdvCSVExtractor.ConsumeEntries(oEntries : TAdvStringList);
Var
  sEntry : String;
Begin 
  If Assigned(oEntries) Then
    oEntries.Clear;

  // Consume all preceeding whitespace.
  ConsumeWhileCharacterSet(setControls + setVertical + setHorizontal);

  While MoreEntries Do
  Begin 
    sEntry := ConsumeEntry;

    If Assigned(oEntries) Then
      oEntries.Add(sEntry);
  End;  
End;  


Function TAdvCSVExtractor.ConsumeEntry : String;
Var
  bMore : Boolean;
Begin
  // strip all leading whitespace.
  ConsumeWhileCharacterSet(setControls + setHorizontal);

  If More Then
  Begin 
    If Not FHasQuote Or (NextCharacter <> FQuote) Then
    Begin
      // If it doesn't start with a quote then the entry is ended by a new line or the separator character.

      Result := ConsumeUntilCharacterSet([FSeparator] + setVertical);
    End
    Else
    Begin 
      // Otherwise, if it is quoted, the entry is ended only by a closing quote.
      // Double quotes within the entry are resolved to a single quote.

      ConsumeCharacter(FQuote);
                    
      Result := '';
      bMore := True;
      While bMore And More Do
      Begin 
        If NextCharacter = FQuote Then
        Begin 
          ConsumeCharacter(FQuote);

          bMore := More And (NextCharacter = FQuote);

          If bMore Then
            Result := Result + ConsumeCharacter
          Else
            ProduceString(FQuote);
        End
        Else
        Begin
          Result := Result + ConsumeCharacter;
        End;
      End;  

      If More Then
        ConsumeCharacter(FQuote);
    End;  

    If More Then
    Begin 
      // strip trailing whitespace.
      ConsumeWhileCharacterSet(setControls + setHorizontal - setVertical);

      If More And (NextCharacter = FSeparator) Then
      Begin
        // strip separator character.
        ConsumeCharacter(FSeparator);

        // strip trailing non-newline whitespace after separator.
        ConsumeWhileCharacterSet(setControls + setHorizontal - setVertical);
      End;  
    End;  
  End
  Else
  Begin
    Result := '';
  End;
End;


Procedure TAdvCSVExtractor.ConsumeEntries;
Begin 
  ConsumeEntries(Nil);
End;  


Function TAdvCSVExtractor.MoreEntries : Boolean;
Begin 
  Result := More And Not CharInSet(NextCharacter, setVertical);
End;


End. // AdvCSVExtractors //
