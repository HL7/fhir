Unit AfsStreamManagers;


{! 1 !}
{0.00-003  24 Jun 04 08:59  []       User: Callan Hodgskin   Updates}

// Unit AfsStreamManagers
//
// [ TAfsStreamManager ]
//
// Uses inherited Name as AFS volume name, serves AfsStreams


Interface


Uses
  AdvPersistents, AdvStreams, AdvObjectMatches,
  AfsVolumes;


Type
  TAfsStreamManager = Class(TAdvPersistent)
  Private
    FVolume  : TAfsVolume;
    FMode    : TAfsMode;
    FStreams : TAdvObjectMatch;

    Procedure SetVolume(Const Value: TAfsVolume);

  Public
    Constructor Create; Override;
    Destructor Destroy; Override;

    Procedure Open; Overload; Virtual;
    Procedure Close; Overload; Virtual;

    Function Open(Const sName : String) : TAdvStream; Overload; Virtual;
    Procedure Close(oStream : TAdvStream); Overload; Virtual;
    Procedure Delete(Const sName : String); Overload; Virtual;
    Procedure Clear; Overload; Virtual;

    Property Volume : TAfsVolume Read FVolume Write SetVolume;
    Property Mode : TAfsMode Read FMode Write FMode;
  End; { TAfsStreamManager }


Implementation


Constructor TAfsStreamManager.Create;
Begin { Constructor TAfsStreamManager.Create }
  Inherited;

  FStreams := TAdvObjectMatch.Create;
End;  { Constructor TAfsStreamManager.Create }


Destructor TAfsStreamManager.Destroy;
Begin { Destructor TAfsStreamManager.Destroy }
  FStreams.Free;
  FVolume.Free;

  Inherited;
End;  { Destructor TAfsStreamManager.Destroy }


Procedure TAfsStreamManager.Open;
Begin { Procedure TAfsStreamManager.Open }
  FVolume.Open;
End;  { Procedure TAfsStreamManager.Open }


Procedure TAfsStreamManager.Close;
Begin { Procedure TAfsStreamManager.Close }
  FVolume.Close;
End;  { Procedure TAfsStreamManager.Close }


Function TAfsStreamManager.Open(Const sName : String) : TAdvStream;
Var
  oFile : TAfsFile;
Begin { Function TAfsStreamManager.Open }
  oFile := TAfsFile.Create(FVolume.Link);
  Try
    // TODO: Add sharing flag correctly
    oFile.Open(sName, FMode, asRead);

    Result := oFile.Stream;

    FStreams.Add(Result.Link, oFile.Link);
  Finally
    oFile.Free;
  End; { Try }
End;  { Function TAfsStreamManager.Open }


Procedure TAfsStreamManager.Close(oStream : TAdvStream);
Var
  iIndex : Integer;
  oFile : TAfsFile;
Begin { Procedure TAfsStreamManager.Close }
  iIndex := FStreams.IndexByKey(oStream);

  If FStreams.ExistsByIndex(iIndex) Then
  Begin { If }
    oFile := TAfsFile(FStreams.Values[iIndex]);
    Try
      oFile.Close;
    Finally
      FStreams.DeleteByIndex(iIndex);
    End;  { Finally }
  End;  { If }
End;  { Procedure TAfsStreamManager.Close }


Procedure TAfsStreamManager.Delete(Const sName : String);
Begin { Procedure TAfsStreamManager.Delete }
  FVolume.Delete(sName);
End;  { Procedure TAfsStreamManager.Delete }


Procedure TAfsStreamManager.Clear;
Begin { Procedure TAfsStreamManager.Clear }
  FVolume.Format;
End;  { Procedure TAfsStreamManager.Clear }


Procedure TAfsStreamManager.SetVolume(Const Value: TAfsVolume);
Begin { Procedure TAfsStreamManager.SetVolume }
  FVolume.Free;
  FVolume := Value;
End;  { Procedure TAfsStreamManager.SetVolume }


End. // AfsStreamManagers //
