Unit AdvExtractors;


{! 3 !}


Interface


Uses
  AdvStreams;


Type
  TAdvExtractor = Class(TAdvStreamAdapter)
    Protected

      Function ErrorClass : EAdvExceptionClass; Overload; Override;

      Procedure SetStream(oStream : TAdvStream); Override;

    Public
      Procedure Clear; Virtual;

      Function More : Boolean; Virtual;
  End;

  EAdvExtractor = Class(EAdvStream);
  EAdvExceptionClass = AdvStreams.EAdvExceptionClass;


Implementation


Function TAdvExtractor.ErrorClass : EAdvExceptionClass;
Begin
  Result := EAdvExtractor;
End;


Procedure TAdvExtractor.SetStream(oStream: TAdvStream);
Begin
  Inherited;

  Clear;
End;


Procedure TAdvExtractor.Clear;
Begin
End;


Function TAdvExtractor.More: Boolean;
Begin
  Result := (Stream.Readable > 0);
End;


End. // AdvExtractors //
