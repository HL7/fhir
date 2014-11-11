program FHIRTest;

{$APPTYPE CONSOLE}

uses
  FastMM4 in 'support\FastMM4.pas',
  FastMM4Messages in 'support\FastMM4Messages.pas',
  AdvGenerics in 'support\AdvGenerics.pas',
  AdvGenericsTests in 'support\AdvGenericsTests.pas',
  SysUtils,
  Classes,
  ActiveX,
  StringSupport in 'support\StringSupport.pas',
  MathSupport in 'support\MathSupport.pas',
  DecimalSupport in 'support\DecimalSupport.pas',
  GUIDSupport in 'support\GUIDSupport.pas',
  AdvFactories in 'support\AdvFactories.pas',
  FileSupport in 'support\FileSupport.pas',
  MemorySupport in 'support\MemorySupport.pas',
  DateSupport in 'support\DateSupport.pas',
  ErrorSupport in 'support\ErrorSupport.pas',
  SystemSupport in 'support\SystemSupport.pas',
  ThreadSupport in 'support\ThreadSupport.pas',
  EncodeSupport in 'support\EncodeSupport.pas',
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
  AdvStringMatches in 'support\AdvStringMatches.pas',
  ParseMap in 'support\ParseMap.pas',
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
  AfsResourceVolumes in 'support\AfsResourceVolumes.pas',
  AfsVolumes in 'support\AfsVolumes.pas',
  AfsStreamManagers in 'support\AfsStreamManagers.pas',
  AdvObjectMatches in 'support\AdvObjectMatches.pas',
  RegExpr in 'support\RegExpr.pas',
  AdvStringObjectMatches in 'support\AdvStringObjectMatches.pas',
  JWT in 'support\JWT.pas',
  HMAC in 'support\HMAC.pas',
  libeay32 in 'support\libeay32.pas',
  DigitalSignatures in 'support\DigitalSignatures.pas',
  XMLSupport in 'support\XMLSupport.pas',
  InternetFetcher in 'support\InternetFetcher.pas',
  SCIMObjects in 'SCIMObjects.pas',

  IdSSLOpenSSLHeaders,

  FHIRLang in 'FHIRLang.pas',
  FHIRBase in 'FHIRBase.pas',
  FHIRParserBase in 'FHIRParserBase.pas',
  FHIRConstants in 'FHIRConstants.pas',
  FHIRTypes in 'FHIRTypes.pas',
  FHIRComponents in 'FHIRComponents.pas',
  FHIRResources in 'FHIRResources.pas',
  FHIRParser in 'FHIRParser.pas',
  FHIRSupport in 'FHIRSupport.pas',
  FHIRUtilities in 'FHIRUtilities.pas',
  FHIRDigitalSignatures in 'FHIRDigitalSignatures.pas';

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


procedure Roundtrip(Source, Dest : String);
var
  f : TFileStream;
  m : TMemoryStream;
  p : TFHIRParser;
  c : TFHIRComposer;
  r : TFhirResource;
begin
  try
    p := TFHIRXmlParser.Create('en');
    try
      p.ParserPolicy := xppDrop;
      f := TFileStream.Create(source, fmopenRead,+ fmShareDenyWrite);
      try
        p.source := f;
        p.Parse;
        r := p.resource.Link;
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
        TFHIRJsonComposer(c).Comments := true;
        c.Compose(m, '', '', '', r, true, nil);
      finally
        c.free;
      end;
      m.Position := 0;
      m.SaveToFile(ChangeFileExt(dest, '.json'));
      m.Position := 0;
      r.Free;
      r := nil;
      p := TFHIRJsonParser.Create('en');
      try
        p.source := m;
        p.Parse;
        r := p.resource.Link;
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
        c.Compose(f, '', '', '', r, true, nil);
      finally
        c.free;
      end;
    finally
      f.free;
    end;
  finally
    r.Free;
  end;

//  IdSoapXmlCheckDifferent(source, dest);
end;

procedure roundTripDirectory(pathSource, pathDest : String);
var
  SR: TSearchRec;
  s : String;
begin
  if FindFirst(IncludeTrailingPathDelimiter(PathSource) + '*.xml', faAnyFile, SR) = 0 then
  begin
    repeat
      s := copy(SR.Name, 1, pos('.', SR.Name)-1);
      if (SR.Attr <> faDirectory) and (copy(s, length(s)-1, 2) <> '-d') then
      begin
        Writeln(SR.name);
        Roundtrip(IncludeTrailingPathDelimiter(pathSource)+SR.Name, IncludeTrailingPathDelimiter(pathDest)+s+'-d'+ExtractFileExt((SR.Name)));
      end;
    until FindNext(SR) <> 0;
    FindClose(SR);
  end;
end;

function ConfigureDigSig(dsig : TDigitalSigner; certpath, certtype : String) : TSignatureMethod;
begin
  if certtype = 'rsa' then
  begin
    dsig.KeyFile := IncludeTrailingPathDelimiter(certpath)+'rsa_2048.pem';
    dsig.KeyPassword := 'fhir';
    result := sdXmlRSASha256;
  end
  else if certtype = 'dsa' then
  begin
    dsig.KeyFile := IncludeTrailingPathDelimiter(certpath)+'dsa_1024.pem';
    dsig.KeyPassword := 'fhir';
    result := sdXmlDSASha256;
  end
  else if certtype = 'ecdsa' then
  begin
    dsig.KeyFile := IncludeTrailingPathDelimiter(certpath)+'ecdsa_priv.pem';
//    dsig.CertFile := IncludeTrailingPathDelimiter(certpath)+'ecdsa_pub.pem';
    result := sdXmlRSASha256;
  end;
end;

procedure signProvenance(filename, certpath, certtype : String);
begin
  raise Exception.Create('Not Done Yet');
{  // ok, first we look at the filename, and see what it is.
  p := TFHIRXmlParser.Create('en');
  try
    p.ParserPolicy := xppDrop;
    f := TFileStream.Create(filename, fmopenRead,+ fmShareDenyWrite);
    try
      p.source := f;
      p.Parse;
      if p.feed <> nil then
        sigtype := 0
      else if p.resource is TFhirProvenance then
        sigtype := 1
      else
        raise Exception.Create('Do not know how to sign a '+CODES_TFhirResourceType[p.resource.ResourceType]);
    finally
      f.Free;
    end;
  finally
    p.free;
  end;
}
end;

procedure signAtom(filename, certpath, certtype : String);
var
  dsig : TDigitalSigner;
  method : TSignatureMethod;
begin
  dsig := TDigitalSigner.Create;
  try
    BytesToFile(dsig.signEnveloped(FileToBytes(filename), ConfigureDigSig(dsig, certPath, certType), true), filename);
  finally
    dsig.Free;
  end;
end;

procedure verify(filename, certificate, password : String);
begin
  raise Exception.Create('Not Done Yet');
end;

procedure DoBuildEntry;
var
  certpath, certtype, password : String;
begin
  try
    CoInitialize(nil);
    IdSSLOpenSSLHeaders.load;
    LoadEAYExtensions;
    ERR_load_crypto_strings;
    OpenSSL_add_all_algorithms;
    try
      if (paramstr(1) = '-signatom') then
      begin
        if not FindCmdLineSwitch('certpath', certpath) then
          raise Exception.Create('No certificate provided');
        if not FindCmdLineSwitch('certtype', certtype) then
          raise Exception.Create('No certificate provided');
//        writeln('-signatom '+paramstr(2)+' -certpath '+certpath+' -certtype '+certtype);
        signAtom(paramstr(2), certpath, certtype);
      end
      else if (paramstr(1) = '-signprovenance') then
      begin
        raise Exception.Create('Not Done Yet');
      end
      else if (paramstr(1) = '-verify') then
      begin
  //      FindCmdLineSwitch('cert', cert);
  //      FindCmdLineSwitch('password', password);
  //      verify(paramstr(2), cert, password);
      end
      else if DirectoryExists(ParamStr(2)) and DirectoryExists(Paramstr(1)) then
        roundTripDirectory(Paramstr(1), ParamStr(2))
      else
      begin
        if (ParamStr(1) = '') or (ParamStr(2) = '') or not FileExists(paramstr(1)) then
          raise Exception.Create('Provide input and output file names');
        roundTrip(paramStr(1), paramStr(2));
      end;
    except
      on e:exception do
        SaveStringToFile(AnsiString(e.Message), ParamStr(2)+'.err');
    end;
  finally
    UnloadEAYExtensions;
    CoUninitialize;
  end;
end;

begin
  if paramstr(1) = '-test' then
    TAdvGenericsTests.execute
  else
    DoBuildEntry;
end.
