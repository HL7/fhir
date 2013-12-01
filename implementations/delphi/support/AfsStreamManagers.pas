Unit AfsStreamManagers;

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
