Unit AdvTimeControllers;


{! 13 !}


Interface


Uses
  DateSupport, MemorySupport,
  AdvObjects, AdvExclusiveCriticalSections;


Type
  TAdvTimeController = Class(TAdvObject)
    Private
      FTimeZoneInformationArray : Array[TTimeZone] Of TTimeZoneInformation;
      FCurrentTimeZone : TTimeZone;
      FCriticalSection : TAdvExclusiveCriticalSection;

      Function TimeZoneInformation(Const aTimeZone : TTimeZone) : TTimeZoneInformation;

    Public
      Constructor Create; Override;
      Destructor Destroy; Override;

      Function Link : TAdvTimeController;

      Function MinimumDateTimeValue : TDateTime;
      Function MaximumDateTimeValue : TDateTime;

      Function UniversalDate : TDateTime; 
      Function UniversalTime : TDateTime; 
      Function UniversalDateTime : TDateTime; 

      Function LocalDate : TDateTime; 
      Function LocalTime : TDateTime; 
      Function LocalDateTime : TDateTime; 
      Function LocalTimezoneBias : TDateTime;
       
      Function TimezoneBiasForUniversalDateTime(Const aDateTime : TDateTime; Const aTimeZone : TTimeZone) : TDateTime;

      Function LocalToUniversal(Const aDateTime : TDateTime) : TDateTime; Overload;
      Function LocalToUniversal(Const aDateTime : TDateTime; Const aTimeZone : TTimeZone) : TDateTime; Overload;
      Function UniversalToLocal(Const aDateTime : TDateTime) : TDateTime; Overload;
      Function UniversalToLocal(Const aDateTime : TDateTime; Const aTimeZone : TTimeZone) : TDateTime; Overload;

      Property TimeZone : TTimeZone Read FCurrentTimeZone;
  End;


Implementation


Constructor TAdvTimeController.Create;
Begin
  Inherited;

  FCriticalSection := TAdvExclusiveCriticalSection.Create;
  FCurrentTimeZone := DateSupport.TimeZone;
End;


Destructor TAdvTimeController.Destroy;
Var
  aTimeZone : TTimeZone;
Begin
  For aTimeZone := Low(aTimeZone) To High(aTimeZone) Do
    DateSupport.DestroyTimeZoneInformation(FTimeZoneInformationArray[aTimeZone]);

  FCriticalSection.Free;

  Inherited;
End;


Function TAdvTimeController.Link: TAdvTimeController;
Begin
  Result := TAdvTimeController(Inherited Link);
End;


Function TAdvTimeController.TimeZoneInformation(Const aTimeZone: TTimeZone): TTimeZoneInformation;
Begin
  Result := FTimeZoneInformationArray[aTimeZone];

  If Not Assigned(Result) Then
  Begin
    FCriticalSection.Lock;
    Try
      Result := FTimeZoneInformationArray[aTimeZone];

      If Not Assigned(Result) Then
      Begin
        Result := DateSupport.CreateTimeZoneInformation(aTimeZone);

        FTimeZoneInformationArray[aTimeZone] := Result;
      End;
    Finally
      FCriticalSection.Unlock;
    End;
  End;
End;


Function TAdvTimeController.LocalTimeZoneBias: TDateTime;
Begin
  Result := DateSupport.TimeZoneBias(TimeZoneInformation(FCurrentTimeZone));
End;


Function TAdvTimeController.TimezoneBiasForUniversalDateTime(Const aDateTime : TDateTime; Const aTimeZone : TTimeZone) : TDateTime;
Begin
  Result := DateSupport.TimeZoneBias(TimeZoneInformation(aTimeZone), aDateTime, True);
End;


Function TAdvTimeController.UniversalDateTime: TDateTime;
Begin
  Result := DateSupport.UniversalDateTime;
End;


Function TAdvTimeController.UniversalDate: TDateTime;
Begin
  Result := DateSupport.UniversalDate;
End;


Function TAdvTimeController.UniversalTime: TDateTime;
Begin
  Result := DateSupport.UniversalTime;
End;


Function TAdvTimeController.LocalDateTime: TDateTime;
Begin
  Result := DateSupport.LocalDateTime;
End;


Function TAdvTimeController.LocalDate: TDateTime;
Begin
  Result := DateSupport.LocalDate;
End;


Function TAdvTimeController.LocalTime: TDateTime;
Begin
  Result := DateSupport.LocalTime;
End;


Function TAdvTimeController.LocalToUniversal(Const aDateTime: TDateTime): TDateTime;
Begin
  Result := DateSupport.LocalDateTimeToUniversalDateTime(aDateTime, TimeZoneInformation(FCurrentTimeZone));
End;


Function TAdvTimeController.UniversalToLocal(Const aDateTime: TDateTime): TDateTime;
Begin
  Result := DateSupport.UniversalDateTimeToLocalDateTime(aDateTime, TimeZoneInformation(FCurrentTimeZone));
End;


Function TAdvTimeController.LocalToUniversal(Const aDateTime: TDateTime; Const aTimeZone : TTimeZone): TDateTime;
Begin
  Result := DateSupport.LocalDateTimeToUniversalDateTime(aDateTime, TimeZoneInformation(aTimeZone));
End;


Function TAdvTimeController.UniversalToLocal(Const aDateTime: TDateTime; Const aTimeZone : TTimeZone): TDateTime;
Begin
  Result := DateSupport.UniversalDateTimeToLocalDateTime(aDateTime, TimeZoneInformation(aTimeZone));
End;


Function TAdvTimeController.MaximumDateTimeValue : TDateTime;
Begin
  Result := DateSupport.DATETIME_MAX;
End;


Function TAdvTimeController.MinimumDateTimeValue : TDateTime;
Begin
  Result := DateSupport.DATETIME_MIN;
End;


End.
