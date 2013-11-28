program FHIRTest;

{$APPTYPE CONSOLE}

uses
  FHIRConstants,
  FHIRParser,
  SysUtils,
  Classes,
  ActiveX,
  StringSupport in 'support\StringSupport.pas',
  MathSupport in 'support\MathSupport.pas',
  DecimalSupport in 'support\DecimalSupport.pas',
  GUIDSupport in 'support\GUIDSupport.pas',
  bignum in 'support\bignum.pas',
  AdvFactories in 'support\AdvFactories.pas',
  FileSupport in 'support\FileSupport.pas',
  MemorySupport in 'support\MemorySupport.pas',
  DateSupport in 'support\DateSupport.pas',
  ErrorSupport in 'support\ErrorSupport.pas',
  SystemSupport in 'support\SystemSupport.pas',
  ThreadSupport in 'support\ThreadSupport.pas',
  EncodeSupport in 'support\EncodeSupport.pas',
  NetworkSupport in 'support\NetworkSupport.pas',
  AdvControllers in 'support\AdvControllers.pas',
  AdvPersistents in 'support\AdvPersistents.pas',
  AdvObjects in 'support\AdvObjects.pas',
  AdvExceptions in 'support\AdvExceptions.pas',
  AdvFilers in 'support\AdvFilers.pas',
  ColourSupport in 'support\ColourSupport.pas',
  CurrencySupport in 'support\CurrencySupport.pas',
  AdvPersistentLists in 'support\AdvPersistentLists.pas',
  AdvObjectLists in 'support\AdvObjectLists.pas',
  AdvItems in 'support\AdvItems.pas',
  AdvCollections in 'support\AdvCollections.pas',
  AdvIterators in 'support\AdvIterators.pas',
  AdvClassHashes in 'support\AdvClassHashes.pas',
  AdvHashes in 'support\AdvHashes.pas',
  HashSupport in 'support\HashSupport.pas',
  AdvStringHashes in 'support\AdvStringHashes.pas',
  AdvProfilers in 'support\AdvProfilers.pas',
  AdvStringIntegerMatches in 'support\AdvStringIntegerMatches.pas',
  AdvStreams in 'support\AdvStreams.pas',
  AdvParameters in 'support\AdvParameters.pas',
  AdvExclusiveCriticalSections in 'support\AdvExclusiveCriticalSections.pas',
  AdvThreads in 'support\AdvThreads.pas',
  AdvSignals in 'support\AdvSignals.pas',
  AdvSynchronizationRegistries in 'support\AdvSynchronizationRegistries.pas',
  AdvTimeControllers in 'support\AdvTimeControllers.pas',
  AdvIntegerMatches in 'support\AdvIntegerMatches.pas',
  AdvBuffers in 'support\AdvBuffers.pas',
  BytesSupport in 'support\BytesSupport.pas',
  AdvStringBuilders in 'support\AdvStringBuilders.pas',
  AdvFiles in 'support\AdvFiles.pas',
  AdvLargeIntegerMatches in 'support\AdvLargeIntegerMatches.pas',
  AdvStringLargeIntegerMatches in 'support\AdvStringLargeIntegerMatches.pas',
  AdvStringLists in 'support\AdvStringLists.pas',
  AdvCSVFormatters in 'support\AdvCSVFormatters.pas',
  AdvTextFormatters in 'support\AdvTextFormatters.pas',
  AdvFormatters in 'support\AdvFormatters.pas',
  AdvCSVExtractors in 'support\AdvCSVExtractors.pas',
  AdvTextExtractors in 'support\AdvTextExtractors.pas',
  AdvExtractors in 'support\AdvExtractors.pas',
  AdvCharacterSets in 'support\AdvCharacterSets.pas',
  AdvOrdinalSets in 'support\AdvOrdinalSets.pas',
  AdvStreamReaders in 'support\AdvStreamReaders.pas',
  AdvStringStreams in 'support\AdvStringStreams.pas',
  DateAndTime in 'support\DateAndTime.pas',
  KDate in 'support\KDate.pas',
  HL7V2DateSupport in 'support\HL7V2DateSupport.pas',
  FHIRBase in 'FHIRBase.pas',
  AdvStringMatches in 'support\AdvStringMatches.pas',
  FHIRResources in 'FHIRResources.pas',
  FHIRParserBase in 'FHIRParserBase.pas',
  FHIRSupport in 'FHIRSupport.pas',
  ParseMap in 'support\ParseMap.pas',
  FHIRAtomFeed in 'FHIRAtomFeed.pas',
  MsXmlParser in 'support\MsXmlParser.pas',
  AdvMemories in 'support\AdvMemories.pas',
  XMLBuilder in 'support\XMLBuilder.pas',
  AdvWinInetClients in 'support\AdvWinInetClients.pas',
  MsXmlBuilder in 'support\MsXmlBuilder.pas',
  TextUtilities in 'support\TextUtilities.pas',
  AdvVCLStreams in 'support\AdvVCLStreams.pas',
  AdvXmlBuilders in 'support\AdvXmlBuilders.pas',
  AdvXMLFormatters in 'support\AdvXMLFormatters.pas',
  AdvXMLEntities in 'support\AdvXMLEntities.pas',
  JSON in 'support\JSON.pas',
  FHIRLang in 'FHIRLang.pas',
  AfsResourceVolumes in 'support\AfsResourceVolumes.pas',
  AfsVolumes in 'support\AfsVolumes.pas',
  AfsStreamManagers in 'support\AfsStreamManagers.pas',
  AdvObjectMatches in 'support\AdvObjectMatches.pas',
  RegExpr in 'support\RegExpr.pas',
  FHIRUtilities in 'FHIRUtilities.pas';

procedure SaveStringToFile(s : AnsiString; fn : String);
var
  f : TFileStream;
begin  
  f := TFileStream.Create(fn, fmCreate);
  try
    f.Write(s[1], length(s));
  finally
    f.free;
  end;
end;

var
  f : TFileStream;
  m : TMemoryStream;
  p : TFHIRParser;
  c : TFHIRComposer;
  r : TFhirResource;
  a : TFHIRAtomFeed;
  o : TAdvObject;
procedure Roundtrip(Source, Dest : String);
begin
  try
    p := TFHIRXmlParser.Create('en');
    try
      f := TFileStream.Create(source, fmopenRead,+ fmShareDenyWrite);
      try
        p.source := f;
        p.Parse;
        r := p.resource.Link;
        a := p.feed.Link;
      finally
        f.Free;
      end;
    finally
      p.free;
    end;
    m := TMemoryStream.Create;
    try
      c := TFHIRJsonComposer.Create('en');
      try
        if r <> nil then
          c.Compose(m, '', '', r, true)
        else
          c.Compose(m, a, true);
      finally
        c.free;
      end;
      m.Position := 0;
      r.Free;
      a.Free;
      r := nil;
      a := nil;
      p := TFHIRJsonParser.Create('en');
      try
        p.source := m;
        p.Parse;
        r := p.resource.Link;
        a := p.feed.Link;
      finally
        p.Free;
      end;
    finally
      m.Free;
    end;
    f := TFileStream.Create(dest, fmCreate);
    try
      c := TFHIRXMLComposer.Create('en');
      try
        if r <> nil then
          c.Compose(f, '', '', r, true)
        else
          c.Compose(f, a, true);
      finally
        c.free;
      end;
    finally
      f.free;
    end;
  finally
    r.Free;
    a.Free;
  end;
end;

begin
  try
    CoInitialize(nil);
    if (ParamStr(1) = '') or (ParamStr(2) = '') or not FileExists(paramstr(1)) then
      raise Exception.Create('Provide input and output file names');
    roundTrip(paramStr(1), paramStr(2));
  except
    on e:exception do
      SaveStringToFile(AnsiString(e.Message), ParamStr(2)+'.err');
  end;
end.
