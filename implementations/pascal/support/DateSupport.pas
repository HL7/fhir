Unit DateSupport;

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
  SysUtils, Windows, Registry,
  StringSupport, MathSupport, MemorySupport, ErrorSupport;

Type
  TDateTime = System.TDateTime;  // Number of days and a fraction of a day since 1899-12-30.
  TDuration = Int64;             // Number of milliseconds.
  TYear = Word;
  TMonthOfYear =
    (MonthOfYearJanuary, MonthOfYearFebruary, MonthOfYearMarch, MonthOfYearApril, MonthOfYearMay, MonthOfYearJune,
     MonthOfYearJuly, MonthOfYearAugust, MonthOfYearSeptember, MonthOfYearOctober, MonthOfYearNovember, MonthOfYearDecember);
  TMonthDays = Array [TMonthOfYear] Of Word;
  PMonthDays = ^TMonthDays;
  TTimeZone = (TimeZoneUnknown,
      // proven supported, fixed where windows is wrong.
      TimeZoneNZ, TimeZoneAustraliaVicNSW, TimeZoneAustraliaQLD, TimeZoneAustraliaTas, TimeZoneAustraliaSA, TimeZoneAustraliaNT, TimeZoneAustraliaWA,

      // accepted windows at face value
      TimeZoneAfghanistan,
      TimeZoneUSAlaska,
      TimeZoneArab_Riyadh,
      TimeZoneArab_AbuDhabi,
      TimeZoneArab_Baghdad,
      TimeZoneArgentina,
      TimeZoneArmenia,
      TimeZoneCanadaAtlantic,
      TimeZoneAzerbaijan,
      TimeZoneAzores,
      TimeZoneCanadaCentral,
      TimeZoneCapeVerde,
      TimeZoneCaucasus,
      TimeZoneCentralAmerica,
      TimeZoneCentralAsia,
      TimeZoneBrazilCentral,
      TimeZoneEuropeCentral_Budapest,
      TimeZoneEuropeCentral_Warsaw,
      TimeZonePacificCentral,
      TimeZoneUSACentral,
      TimeZoneMexicoCentral,
      TimeZoneChina,
      TimeZoneAfricaEastern,
      TimeZoneEuropeEastern, //_Minsk
      TimeZoneSouthAmericaEastern_Brasilia,
      TimeZoneUSEastern,
      TimeZoneEgypt,
      TimeZoneEkaterinburg,
      TimeZoneFiji,
      TimeZoneFLE,
      TimeZoneGeorgia,
      TimeZoneGMT,
      TimeZoneGreenland,
      TimeZoneGreenwich,
      TimeZoneGTB,
      TimeZoneUSHawaii,
      TimeZoneIndia,
      TimeZoneIran,
      TimeZoneIsrael,
      TimeZoneJordan,
      TimeZoneKamchatka,
      TimeZoneKorea,
      TimeZoneMauritius,
      TimeZoneMexico1,
      TimeZoneMexico2,
      TimeZoneMidAtlantic,
      TimeZoneMiddleEast,
      TimeZoneMontevideo,
      TimeZoneMorocco,
      TimeZoneUSArizona,
      TimeZoneMexicoMountain,
      TimeZoneMyanmar,
      TimeZoneAsiaNorthCentral,
      TimeZoneNamibia,
      TimeZoneNepal,
      TimeZoneNewfoundland,
      TimeZoneAsiaNorthEast,
      TimeZoneAsiaNorth,
      TimeZonePacificChile,
      TimeZonePacific,
      TimeZoneMexicoPacific,
      TimeZonePakistan,
      TimeZoneParaguay,
      TimeZoneRomance,
      TimeZoneRussian,
      TimeZoneSouthAmericaEastern_Cayenne,
      TimeZoneSouthAmericaPacific,
      TimeZoneSouthAmericaWestern,
      TimeZoneSamoa,
      TimeZoneAsiaSouthEast,
      TimeZoneSingapore,
      TimeZoneSouthAfrica,
      TimeZoneSriLanka,
      TimeZoneTaipei,
      TimeZoneTokyo,
      TimeZoneTonga,
      TimeZoneUSIndiana,
      TimeZoneUSMountain,
      TimeZoneUTC,
      TimeZoneVenezuela,
      TimeZoneVladivostok,
      TimeZoneAfricaWestCentral,
      TimeZoneEuropeWestern,
      TimeZoneAsiaWest,
      TimeZonePacificWest,
      TimeZoneYakutsk
      );
  TTimeZoneYearInfo = Class
    Year : TYear;
    HasDaylightSaving : Boolean;
    StandardStart : TSystemTime;
    StandardBias : Double; // number of minutes
    DaylightStart : TSystemTime;
    DaylightBias : Double; // number of minutes
  End;
  TTimeZoneInformation = Class
    Identity : TTimeZone;
    Name : String;
    WindowsName : String;
    StandardName : String;
    DaylightName : String;
    Display : String;
    BaseRules : TTimeZoneYearInfo;
    YearRules : Array Of TTimeZoneYearInfo;
  End;

  EDateTime = Class(Exception);
  TDateToken = (dttDay, dttMonth, dttYear);
  TDateNumber = Word;
  TDateNumbers = Array[0..Integer(High(TDateToken))] Of TDateNumber;
  TDateNumberLengths = Array[0..Integer(High(TDateToken))] Of Integer;
  TDateIndex = Integer;
  TDateIndices = Array [TDateToken] Of TDateIndex;

Const
  DATETIME_MIN = -693593; // '1/01/0001'
  DATETIME_MAX = 2958465; // '31/12/9999'
  MONTHOFYEAR_LONG : Array[TMonthOfYear] Of String =
    ('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');


  MONTHOFYEAR_SHORT : Array [TMonthOfYear] Of String =
    ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');

  MONTHS_DAYS : Array [Boolean{IsLeapYear}] Of TMonthDays =
    ((31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31),
     (31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
  DATETIME_DAY_HOURS = 24;
  DATETIME_DAY_MINUTES = DATETIME_DAY_HOURS * 60;
  DATETIME_DAY_SECONDS = DATETIME_DAY_MINUTES * 60;
  DATETIME_DAY_MILLISECONDS = DATETIME_DAY_SECONDS * 1000;

  DATETIME_SECOND_MILLISECONDS = 1000;
  DATETIME_MINUTE_SECONDS = 60;
  DATETIME_MINUTE_MILLISECONDS = DATETIME_SECOND_MILLISECONDS * DATETIME_MINUTE_SECONDS;
  DATETIME_HOUR_SECONDS = DATETIME_DAY_SECONDS Div 24;
  DATETIME_HALFHOUR_SECONDS = DATETIME_HOUR_SECONDS Div 2;
  DATETIME_HOUR_MILLISECONDS = DATETIME_HOUR_SECONDS * DATETIME_SECOND_MILLISECONDS;

  DATETIME_DAY_ONE = 1.0;
  DATETIME_HOUR_ONE = DATETIME_DAY_ONE / DATETIME_DAY_HOURS;
  DATETIME_MINUTE_ONE = DATETIME_DAY_ONE / DATETIME_DAY_MINUTES;
  DATETIME_SECOND_ONE = DATETIME_DAY_ONE / DATETIME_DAY_SECONDS;
  DATETIME_MILLISECOND_ONE = DATETIME_DAY_ONE / DATETIME_DAY_MILLISECONDS;

  NAMES_TIMEZONES : Array[TTimeZone] Of String =
    ('Unknown', 'NewZealand', 'Australia-VIC/NSW/ACT', 'Australia-QLD', 'Australia-TAS', 'Australia-SA', 'Australia-NT', 'Australia-WA',
     'Afghan',
     'US-Alaska',
     'Arab(Riyadh)',
     'Arab(AbuDhabi)',
     'Arab(Baghdad)',
     'Argentina',
     'Armenia',
     'CA-Atlantic',
     'Azerbaijan',
     'Azores',
     'CA-Central',
     'CapeVerde',
     'Caucasus',
     'America-Central',
     'Asia-Central',
     'Brazil-Central',
     'Europe(Budapest)',
     'Europe(Warsaw)',
     'Pacific-Central',
     'US-Central',
     'Mex-Central',
     'China',
     'Africa-East',
     'Europe-East',
     'SA-East(Brasilia)',
     'US-East',
     'Egypt',
     'Ekaterinburg',
     'Fiji',
     'FLE',
     'Georgia',
     'Greenich Mean Time (GMT)',
     'Greenland',
     'Greenwich',
     'GTB',
     'US-Hawaii',
     'India',
     'Iran',
     'Israel',
     'Jordan',
     'Kamchatka',
     'Korea',
     'Mauritius',
     'Mexico1',
     'Mexico2',
     'MidAtlantic',
     'MiddleEast',
     'Montevideo',
     'Morocco',
     'US-Arizona',
     'Mex-Mountain',
     'Myanmar',
     'Asia-NC',
     'Namibia',
     'Nepal',
     'Newfoundland',
     'Asia-NE',
     'Asia-N',
     'Chile',
     'Pacific',
     'Mex-Pacific',
     'Pakistan',
     'Paraguay',
     'Romance',
     'Russian',
     'SA-East(Cayenne)',
     'SA-Pacific',
     'SA-Western',
     'Samoa',
     'Asia-SE',
     'Singapore',
     'SouthAfrica',
     'SriLanka',
     'Taipei',
     'Tokyo',
     'Tonga',
     'US-Indiana',
     'US-Mountain',
     'UTC',
     'Venezuela',
     'Vladivostok',
     'Africa-WC',
     'Europe-W',
     'Asia-W',
     'Pacific-W',
     'Yakutsk'
    );

// System state.
Function UniversalDate : TDateTime; Overload;
Function UniversalTime : TDateTime; Overload;
Function UniversalDateTime : TDateTime; Overload;

Function LocalDate : TDateTime; Overload;
Function LocalTime : TDateTime; Overload;
Function LocalDateTime : TDateTime; Overload;

Function DateTimeMax(Const aA, aB : TDateTime) : TDateTime; Overload;
Function DateTimeMin(Const aA, aB : TDateTime) : TDateTime; Overload;

Function DateTimeCompare(Const aA, aB : TDateTime) : Integer; Overload;
Function DateTimeCompare(Const aA, aB, aThreshold : TDateTime) : Integer; Overload;

Function ToDateIndices(Const sFormat : String; Out aIndices : TDateIndices) : Boolean; Overload;
Function ToDateIndices(Const sFormat : String) : TDateIndices; Overload;

Function DateTimeFormat(Const aDateTime : TDateTime; Const sFormat : String) : String;
Function ToDateTime(Const sValue, sFormat : String) : TDateTime; Overload;

Function CreateTimeZoneInformation(Const aTimeZone : TTimeZone) : TTimeZoneInformation;
Procedure DestroyTimeZoneInformation(Var aTimeZoneInformation : TTimeZoneInformation);
Function TimeZone : TTimeZone; Overload;
Function TimeZoneBias : TDateTime; Overload;
Function TimeZoneBias(Const aTimeZoneInformation : TTimeZoneInformation) : TDateTime; Overload;
Function TimeZoneBias(Const aTimeZoneInformation : TTimeZoneInformation; Const aInstant : TDateTime; Const bInstantIsUTC : Boolean) : TDateTime; Overload;

Function LocalDateTimeToUniversalDateTime(Const aDateTime : TDateTime; Const aTimeZoneInformation : TTimeZoneInformation) : TDateTime; Overload;
Function UniversalDateTimeToLocalDateTime(Const aDateTime : TDateTime; Const aTimeZoneInformation : TTimeZoneInformation) : TDateTime; Overload;

Function XMLDateTimeStringToDateTime(Const sValue : String) : TDateTime;
Function DateTimeToXMLDateTimeTimeZoneString(Const aTimestamp, aTimeZone : TDateTime) : String;

Function IsLeapYearByYear(Const iYear : TYear) : Boolean; Overload;
function DescribePeriod(Period: TDateTime): String;

Implementation


Uses
  SystemSupport; // See Initialization section.

Type
  TDate ={Type}TDateTime;        // Date only.
  TTime ={Type}TDateTime;        // Time only.

Const
  DELTA_DATE = 693594; // Days between 1/1/0001 and 12/31/1899
  DATETIME_FORMAT_XML = 'yyyy-mm-dd"T"hh:nn:ss';

  WINDOWS_NAMES_TIMEZONES : Array[TTimeZone] Of String =
    ('',
     'New Zealand Standard Time',
     'AUS Eastern Standard Time',
     'E. Australia Standard Time',
     'Tasmania Standard Time',
     'Cen. Australia Standard Time',
     'AUS Central Standard Time',
     'W. Australia Standard Time',
     'Afghanistan Standard Time',
     'Alaskan Standard Time',
     'Arab Standard Time',
     'Arabian Standard Time',
     'Arabic Standard Time',
     'Argentina Standard Time',
     'Armenian Standard Time',
     'Atlantic Standard Time',
     'Azerbaijan Standard Time',
     'Azores Standard Time',
     'Canada Central Standard Time',
     'Cape Verde Standard Time',
     'Caucasus Standard Time',
     'Central America Standard Time',
     'Central Asia Standard Time',
     'Central Brazilian Standard Time',
     'Central Europe Standard Time',
     'Central European Standard Time',
     'Central Pacific Standard Time',
     'Central Standard Time',
     'Central Standard Time (Mexico)',
     'China Standard Time',
     'E. Africa Standard Time',
     'E. Europe Standard Time',
     'E. South America Standard Time',
     'Eastern Standard Time',
     'Egypt Standard Time',
     'Ekaterinburg Standard Time',
     'Fiji Standard Time',
     'FLE Standard Time',
     'Georgian Standard Time',
     'GMT Standard Time',
     'Greenland Standard Time',
     'Greenwich Standard Time',
     'GTB Standard Time',
     'Hawaiian Standard Time',
     'India Standard Time',
     'Iran Standard Time',
     'Israel Standard Time',
     'Jordan Standard Time',
     'Kamchatka Standard Time',
     'Korea Standard Time',
     'Mauritius Standard Time',
     'Mexico Standard Time',
     'Mexico Standard Time 2',
     'Mid-Atlantic Standard Time',
     'Middle East Standard Time',
     'Montevideo Standard Time',
     'Morocco Standard Time',
     'Mountain Standard Time',
     'Mountain Standard Time (Mexico)',
     'Myanmar Standard Time',
     'N. Central Asia Standard Time',
     'Namibia Standard Time',
     'Nepal Standard Time',
     'Newfoundland Standard Time',
     'North Asia East Standard Time',
     'North Asia Standard Time',
     'Pacific SA Standard Time',
     'Pacific Standard Time',
     'Pacific Standard Time (Mexico)',
     'Pakistan Standard Time',
     'Paraguay Standard Time',
     'Romance Standard Time',
     'Russian Standard Time',
     'SA Eastern Standard Time',
     'SA Pacific Standard Time',
     'SA Western Standard Time',
     'Samoa Standard Time',
     'SE Asia Standard Time',
     'Singapore Standard Time',
     'South Africa Standard Time',
     'Sri Lanka Standard Time',
     'Taipei Standard Time',
     'Tokyo Standard Time',
     'Tonga Standard Time',
     'US Eastern Standard Time',
     'US Mountain Standard Time',
     'UTC',
     'Venezuela Standard Time',
     'Vladivostok Standard Time',
     'W. Central Africa Standard Time',
     'W. Europe Standard Time',
     'West Asia Standard Time',
     'West Pacific Standard Time',
     'Yakutsk Standard Time'
     );

Procedure Error(Const sMethod, sMessage : String);
Begin
  Raise EDateTime.Create('(DateSupport.' + sMethod + '): ' + sMessage);
End;

Function DateTimeMax(Const aA, aB : TDateTime) : TDateTime;
Begin
  If DateTimeCompare(aA, aB) > 0 Then
    Result := aA
  Else
    Result := aB;
End;


Function DateTimeMin(Const aA, aB : TDateTime) : TDateTime;
Begin
  If DateTimeCompare(aA, aB) < 0 Then
    Result := aA
  Else
    Result := aB;
End;

Function DateTimeCompare(Const aA, aB : TDateTime) : Integer;
Begin
  Result := MathSupport.RealCompare(aA, aB);
End;


Function DateTimeCompare(Const aA, aB, aThreshold : TDateTime) : Integer;
Begin
  Result := MathSupport.RealCompare(aA, aB, aThreshold);
End;

Function TimeSeparator : Char;
Begin
  Result := {$IFNDEF VER130}FormatSettings.{$ENDIF}TimeSeparator;
End;


Function DateSeparator : Char;
Begin
  Result := {$IFNDEF VER130}FormatSettings.{$ENDIF}DateSeparator;
End;


Function DateSeparators : TCharSet;
Begin
  Result := [' ', ',', '-', '.', '/', '\', DateSeparator, TimeSeparator];
End;

Function TimeSeparators : TCharSet;
Begin
  Result := ['.', ':', TimeSeparator];
End;


Function ToDateIndices(Const sFormat : String; Out aIndices : TDateIndices) : Boolean;
Const
  SET_FORMAT = ['d', 'D', 'm', 'M', 'y', 'Y'];
Var
  iLoop  : Integer;
  aSeparators : TCharSet;
  iFormat : Integer;
  iLength : Integer;
  cFound : Char;
Begin 
  FillChar(aIndices, SizeOf(aIndices), -1);

  aSeparators := DateSeparators;
  iLoop := 0;
  iFormat := 1;
  iLength := Length(sFormat);

  While (iFormat <= iLength) Do
  Begin 
    While (iFormat <= iLength) And Not CharInSet(sFormat[iFormat], SET_FORMAT) Do
      Inc(iFormat);

    If (iFormat <= iLength) Then
    Begin 
      cFound := sFormat[iFormat];
      Inc(iFormat);

      Case cFound Of
        'd', 'D' : aIndices[dttDay] := iLoop;
        'm', 'M' : aIndices[dttMonth] := iLoop;
        'y', 'Y' : aIndices[dttYear] := iLoop;
      End;  

      // Skip format character pairs eg. 'dd/mm/yyyy'
      While (iFormat <= iLength) And (sFormat[iFormat] = cFound) Do
        Inc(iFormat);
    End;  

    Inc(iLoop);
  End;

  Result := False;
  iLoop := Integer(Low(aIndices));
  While (iLoop <= Integer(High(aIndices))) And Not Result Do
  Begin 
    Result := aIndices[TDateToken(iLoop)] >= 0;
    Inc(iLoop);
  End;  
End;  

Function ToDateTimeElement(Var pData : PChar) : TDateNumber;
Const
  ORD_0 = Ord('0');
Var
  pStart : PChar;
  iLength : Integer;
Begin
  If CharInSet(pData^, setNumbers) Then
  Begin
    Result := 0;
    While CharInSet(pData^, setNumbers) And (Result <= 1000) Do // Less than year 10,000 in the next call.
    Begin
      Result := (Result * 10) + (Ord(pData^) - ORD_0);
      Inc(pData);
    End;
  End
  Else
  Begin
    pStart := pData;
    While StringIsAlphabetic(pData^) Do
      Inc(pData);

    iLength := Integer(pData) - Integer(pStart);

    If iLength = 3 Then
      Result := StringArrayIndexOf({$IFNDEF VER130}FormatSettings.{$ENDIF}ShortMonthNames, Copy(pStart, 1, iLength)) + 1
    Else
      Result := StringArrayIndexOf({$IFNDEF VER130}FormatSettings.{$ENDIF}LongMonthNames, Copy(pStart, 1, iLength)) + 1;
  End;
End;


Function ToDateIndices(Const sFormat : String) : TDateIndices;
Begin
  If Not ToDateIndices(sFormat, Result) Then
    Error('ToDateIndices', StringFormat('Unable to determine the meaning of the date format ''%s''', [sFormat]));
End;

Function PCharToTime(Var pValue : PChar; Out aTime : TTime) : Boolean;
Const
  ORD0 = Ord('0');
Var
  aSeps  : TCharSet;
  iHour, iMinute, iSecond, iMillisecond : Word;
Begin
  aSeps := TimeSeparators;
  iHour := 0;
  iMinute := 0;
  iSecond := 0;
  iMillisecond := 0;

  Result := Assigned(pValue);

  If Result Then
  Begin
    // Ignore Whitespace
    While CharInSet(pValue^, setWhitespace) Do
      Inc(pValue);

    // Hour
    While CharInSet(pValue^, ['0'..'9']) Do
    Begin
      iHour := (iHour * 10) + (Ord(pValue^) - ORD0);
      Inc(pValue);
    End;

    Result := (iHour < 24) And CharInSet(pValue^, aSeps);

    If Result Then
    Begin
      // Minute
      Inc(pValue);
      While CharInSet(pValue^, ['0'..'9']) Do
      Begin
        iMinute := (iMinute * 10) + (Ord(pValue^) - ORD0);
        Inc(pValue);
      End;

      Result := (iMinute < 60);

      If Result And CharInSet(pValue^, aSeps) Then
      Begin
        // Second
        Inc(pValue);

        If CharInSet(pValue^, ['0'..'9']) Then
        Begin
          // First digit
          iSecond := (Ord(pValue^) - ORD0);
          Inc(pValue);

          If CharInSet(pValue^, ['0'..'9']) Then
          Begin
            // second digit
            iSecond := (iSecond * 10) + (Ord(pValue^) - ORD0);
            Inc(pValue);

            // Ignore trailing numbers after a two digit seconds.
            While CharInSet(pValue^, ['0'..'9']) Do
              Inc(pValue);
          End;

          Result := (iSecond < 60);

          // Optional milliseconds
          If CharInSet(pValue^, aSeps) Then
          Begin
            // Millisecond
            Inc(pValue);
            While CharInSet(pValue^, ['0'..'9']) Do
            Begin
              iMillisecond := (iMillisecond * 10) + (Ord(pValue^) - ORD0);
              Inc(pValue);
            End;

            Result := (iMillisecond < 1000);
          End;
        End;
      End;

      // Optional 12 hour display of AM or PM.
      If pValue^ <> #0 Then
      Begin
        // Ignore whitespace
        While (pValue^ = ' ') Do
          Inc(pValue);

        If CharInSet(pValue^, ['A', 'a']) Then
        Begin
          Result := (iHour <= 12);

          If iHour = 12 Then
            iHour := 0;
        End
        Else If CharInSet(pValue^, ['P', 'p']) Then
        Begin
          Result := (iHour <= 12);

          If iHour < 12 Then
            Inc(iHour, 12);
        End;
      End;
    End;
  End;

  aTime := ((iHour * 3600000) + (iMinute * 60000) + (iSecond * 1000) + iMillisecond) / DATETIME_DAY_MILLISECONDS;
End;


{$IFDEF VER130}
Function TryEncodeDate(iYear, iMonth, iDay : Word; Out aDate : TDateTime) : Boolean;
Var
  iTemp  : Integer;
  pTable : PMonthDays;
Begin
  aDate := 0;

  pTable := @MONTHS_DAYS[IsLeapYearByYear(iYear)];

  Result := (iYear >= 1) And (iYear <= 9999) And (iMonth >= 1) And (iMonth <= 12) And (iDay >= 1) And (iDay <= pTable^[TMonthOfYear(iMonth - 1)]);

  If Result Then
  Begin
    For iTemp := 0 To iMonth - 2 Do
      Inc(iDay, pTable^[TMonthOfYear(iTemp)]);

    iTemp := iYear - 1;

    aDate := (iTemp * 365) + (iTemp Div 4) - (iTemp Div 100) + (iTemp Div 400) + iDay - DELTA_DATE;
  End;
End;

{$ENDIF}

Function ToDateTime(Const sValue, sFormat : String; Out aDateTime : TDateTime) : Boolean; Overload;
Var
  pStart : PChar;
  pValue : PChar;
  iLoop  : Integer;
  aLoop : TDateToken;
  aSeparators : TCharSet;
  aTime  : TTime;
  iYear  : TDateNumber;
  aNumbers : TDateNumbers;
  aLengths : TDateNumberLengths;
  aIndices : TDateIndices;
  iIndices : Integer;
  bLeadingTime : Boolean;
Begin 
  // TODO: this routine doesn't suppose continuous date formats - eg. 'yyyymmdd'.
  //       date formats must have separation characters.

  aIndices := ToDateIndices(sFormat);
  iIndices := IntegerArrayMax(aIndices);
  pValue := PChar(sValue);

  bLeadingTime := CharInSet(Upcase(StringGet(sFormat, 1)), ['H', 'N', 'S', 'Z']);

  If bLeadingTime Then
    Result := PCharToTime(pValue, aTime)
  Else
    Result := iIndices > 0;

  If Result Then
  Begin
    If iIndices < High(aNumbers) Then
    Begin
      For aLoop := Low(aIndices) To High(aIndices) Do
      Begin
        If aIndices[aLoop] < 0 Then
          aIndices[aLoop] := iIndices + 1;
      End;
    End;

    // Whitespace
    While CharInSet(pValue^, setWhitespace) Do
      Inc(pValue);

    // Date
    aSeparators := DateSeparators;
    iLoop := Low(aNumbers);
    While (pValue^ <> #0) And (iLoop <= High(aNumbers)) Do
    Begin 
      pStart := pValue;

      aNumbers[iLoop] := ToDateTimeElement(pValue);
      aLengths[iLoop] := Integer(pValue) - Integer(pStart);

      While CharInSet(pValue^, aSeparators) Do
        Inc(pValue);

      Inc(iLoop);
    End;  

    Result := iLoop >= iIndices;

    If Result Then
    Begin
      While (iLoop <= High(aNumbers)) Do
      Begin 
        aNumbers[iLoop] := 1;
        Inc(iLoop);
      End;  

      iYear := aNumbers[aIndices[dttYear]];

      If (iYear < 100) And (aLengths[aIndices[dttYear]] <= 2) Then
      Begin 
        If iYear > {$IFNDEF VER130}FormatSettings.{$ENDIF}TwoDigitYearCenturyWindow Then
          Inc(iYear, 1900)
        Else
          Inc(iYear, 2000);
      End;

      // Return
      Result := TryEncodeDate   (iYear, aNumbers[aIndices[dttMonth]], aNumbers[aIndices[dttDay]], aDateTime);
    End;
  End;

  If Result And Not bLeadingTime Then
  Begin
    While Not CharInSet(pValue^, [#0, '0'..'9']) Do
      Inc(pValue);

    If (pValue^ <> #0) Then
      Result := PCharToTime(pValue, aTime)
    Else
      aTime := 0;
  End;

  If Result Then
    aDateTime := Int(aDateTime) + Frac(aTime);
End;

Function ToDateTime(Const sValue, sFormat: String): TDateTime;
Begin
  If Not ToDateTime(sValue, sFormat, Result) Then
    Error('ToDateTime', StringFormat('Unable to convert ''%s'' as ''%s''', [sValue, sFormat]));
End;

Function DateTimeFormat(Const aDateTime : TDateTime; Const sFormat : String) : String;
Begin
  Result := SysUtils.FormatDateTime(sFormat, aDateTime);
End;

Function TimeZoneBiasToString(Const aTimeZoneBias : TDateTime) : String;
Begin
  // -10:00 etc

  If aTimeZoneBias < 0 Then
    Result := DateTimeFormat(-aTimeZoneBias, '"-"hh:nn')
  Else
    Result := DateTimeFormat(aTimeZoneBias, '"+"hh:nn');
End;

Function DateTimeToXMLTimeZoneString(Const aValue : TDateTime) : String;
Begin
  If aValue = 0 Then
    Result := TimeZoneBiasToString(TimeZoneBias)
  Else
    Result := TimeZoneBiasToString(aValue);
End;

Function DateTimeToXMLDateTimeString(Const aValue : TDateTime) : String;
Begin
  Result := DateTimeFormat(aValue, DATETIME_FORMAT_XML);
End;


Function DateTimeToXMLDateTimeTimeZoneString(Const aTimestamp, aTimeZone : TDateTime) : String;
Begin
  Result := DateTimeToXMLDateTimeString(aTimestamp) + 'T' + DateTimeToXMLTimeZoneString(aTimeZone);
End;

Function XMLDateTimeStringToDateTime(Const sValue : String) : TDateTime;
Begin
  Result := ToDateTime(sValue, DATETIME_FORMAT_XML);
End;

Function IsLeapYearByYear(Const iYear : TYear) : Boolean;
Begin
  Result := SysUtils.IsLeapYear(iYear);
End;


Function TimeZone : TTimeZone;
Var
  iRet : DWord;
  aInfo : TIME_ZONE_INFORMATION;
  aLoop : TTimeZone;
Begin
  // this is the current TimeZone.

  Result := TimeZoneUnknown;

  iRet := GetTimeZoneInformation(aInfo);

  If iRet = $FFFFFFFF Then
    Error('TimeZone', StringFormat('Unable to get time zone information [%s]', [ErrorAsString]));

  For aLoop := Low(TTimeZone) To High(TTimeZone) Do
  Begin
    If aInfo.StandardName = WINDOWS_NAMES_TIMEZONES[aLoop] Then
      Result := aLoop;
  End;

  If Result = TimeZoneUnknown Then
  Begin
    If (StringStartsWith(aInfo.StandardName, 'Tasmania')) Then
    Begin
      Result := TimeZoneAustraliaTas;
    End
    Else If (StringStartsWith(aInfo.StandardName, 'New Zealand')) Then
    Begin
      Result := TimeZoneNZ;
    End
    Else If aInfo.Bias = -600 Then
    Begin
      If (iRet = 0) Then
        Result := TimeZoneAustraliaQLD
      Else
        Result := TimeZoneAustraliaVicNSW;
    End
    Else If aInfo.Bias = -570 Then
    Begin
      If (iRet = 0) Then
        Result := TimeZoneAustraliaNT
      Else
        Result := TimeZoneAustraliaSA;
    End
    Else If aInfo.Bias = -480 Then
      Result := TimeZoneAustraliaWA
    Else
      Result := TimeZoneUnknown;
  End;
End;

Function CloneTimeZoneYearInfo(Const aTimeZoneYearInfo : TTimeZoneYearInfo) : TTimeZoneYearInfo;
Begin
  Result := TTimeZoneYearInfo.Create;
  Result.Year := aTimeZoneYearInfo.Year;
  Result.HasDaylightSaving := aTimeZoneYearInfo.HasDaylightSaving;
  Result.StandardStart := aTimeZoneYearInfo.StandardStart;
  Result.StandardBias := aTimeZoneYearInfo.StandardBias;
  Result.DaylightStart := aTimeZoneYearInfo.DaylightStart;
  Result.DaylightBias := aTimeZoneYearInfo.DaylightBias;
End;

Const
  KEY_ROOT = 'SOFTWARE\Microsoft\Windows NT\CurrentVersion\Time Zones\';

Function CreateTimeZoneInformation(Const aTimeZone : TTimeZone) : TTimeZoneInformation;
Type
  REG_TZI_FORMAT = Packed Record
    Bias: LongInt; { Current Bias of the Time Zone. }
    StandardBias: LongInt; { Standard Time Bias, normally 0, for the Time Zone. }
    DaylightBias: LongInt; { Daylight Savings Bias of the Time Zone. }
    StandardDate: TSystemTime; { Date Standard Time takes over if Daylight Savings Time is used in the Time Zone. }
    DaylightDate: TSystemTime; { Date Daylight Savings Time takes over if Daylight Savings Time used in the Time Zone. }
  End;
Var
  oReg : TRegistry;
  aInfo : TIME_ZONE_INFORMATION;
  aRegInfo : REG_TZI_FORMAT;
  iStart : Integer;
  iLast : Integer;
  iLoop : Integer;
  oTimeZoneYearInfo : TTimeZoneYearInfo;
Begin
  // Get the information used interanally which is extracted from the registry - cache it up for quicker usage.

  Result := TTimeZoneInformation.Create;
  Try
    Result.Identity := aTimeZone;
    Result.Name := NAMES_TIMEZONES[aTimeZone];
    Result.BaseRules := TTimeZoneYearInfo.Create;

    oReg := TRegistry.Create;
    Try
      If aTimeZone = TimeZoneUnknown Then
      Begin
        If GetTimeZoneInformation(aInfo) = $FFFFFFFF Then
          Error('CreateTimeZoneInformation', StringFormat('Unable to get time zone information [%s]', [ErrorAsString]));
        Result.WindowsName := aInfo.StandardName;
        Result.Display := '(unknown timezone)';
        Result.BaseRules.Year := 0;
        Result.BaseRules.StandardStart := aInfo.StandardDate;
        Result.BaseRules.StandardBias := aInfo.Bias + aInfo.StandardBias;
        Result.BaseRules.DaylightStart := aInfo.DaylightDate;
        Result.BaseRules.DaylightBias := aInfo.Bias + aInfo.DaylightBias;
      End
      Else
      Begin
        Result.WindowsName := WINDOWS_NAMES_TIMEZONES[aTimeZone];
        oReg.RootKey := HKEY_LOCAL_MACHINE;
        If Not oReg.OpenKeyReadOnly(KEY_ROOT + Result.WindowsName) Then
          Error('CreateTimeZoneInformation', StringFormat('Unable to load time zone information [%s]', [ErrorAsString]));
        Result.Display := oReg.ReadString('Display');
        If oReg.ReadBinaryData('TZI', aRegInfo, SizeOf(aRegInfo)) <> SizeOf(aRegInfo) Then
          Error('CreateTimeZoneInformation', StringFormat('Unable to load time zone binary information [%s]', [ErrorAsString]));
        Result.BaseRules.Year := 0;
        Result.BaseRules.StandardStart := aRegInfo.StandardDate;
        Result.BaseRules.StandardBias := aRegInfo.Bias + aRegInfo.StandardBias;
        Result.BaseRules.DaylightStart := aRegInfo.DaylightDate;
        Result.BaseRules.DaylightBias := aRegInfo.Bias + aRegInfo.DaylightBias;
        Result.StandardName := oReg.ReadString('Std');
        Result.DaylightName := oReg.ReadString('Dlt')
      End;
      Result.BaseRules.HasDaylightSaving := Result.BaseRules.DayLightStart.wMonth <> 0;
      oReg.CloseKey;
      If oReg.OpenKeyReadOnly(KEY_ROOT + Result.WindowsName+'\Dynamic DST') Then
      Begin
        iStart := oReg.ReadInteger('FirstEntry');
        iLast := oReg.ReadInteger('LastEntry');
        SetLength(Result.YearRules, iLast - iStart + 1);
        For iLoop := iStart To iLast Do
        Begin
          oTimeZoneYearInfo := TTimeZoneYearInfo.Create;
          Result.YearRules[iLoop - iStart] := oTimeZoneYearInfo;

          oTimeZoneYearInfo.Year := iLoop;
          If oReg.ReadBinaryData(IntegerToString(iLoop), aRegInfo, SizeOf(aRegInfo)) <> SizeOf(aRegInfo) Then
            Error('CreateTimeZoneInformation', StringFormat('Unable to load time zone binary information [%s] for [%s]', [ErrorAsString, IntegerToString(iLoop)]));
          oTimeZoneYearInfo.StandardStart := aRegInfo.StandardDate;
          oTimeZoneYearInfo.StandardBias := aRegInfo.Bias + aRegInfo.StandardBias;
          oTimeZoneYearInfo.DaylightStart := aRegInfo.DaylightDate;
          oTimeZoneYearInfo.DaylightBias := aRegInfo.Bias + aRegInfo.DaylightBias;
          oTimeZoneYearInfo.HasDaylightSaving := oTimeZoneYearInfo.DayLightStart.wMonth <> 0;
        End;
      End;
    Finally
      oReg.Free;
    End;
    // This is a temporary workaround for erroneous information in
    // some windows registries. Fix is http://support.microsoft.com/hotfix/KBHotfix.aspx?kbnum=974176&kbln=en-us
    // but it is not widely applied
    If (aTimeZone = TimeZoneAustraliaWA) And (Result.BaseRules.DaylightStart.wMonth <> 0) Then
    Begin
      SetLength(Result.YearRules, Length(Result.YearRules) + 2);
      // first year, 2005, just repeats no daylight
      // second year, 2006, claims daylight savings has already started
      Result.YearRules[1].StandardStart.wMonth := 0;
      Result.YearRules[High(Result.YearRules) - 1] := CloneTimeZoneYearInfo(Result.BaseRules);
      Result.YearRules[High(Result.YearRules) - 1].Year := 2008;
      Result.BaseRules.DaylightStart.wMonth := 0; // Daylight saving ended in March 2009
      Result.YearRules[High(Result.YearRules)] := CloneTimeZoneYearInfo(Result.BaseRules);      
      Result.YearRules[High(Result.YearRules)].Year := 2009;
      Result.BaseRules.StandardStart.wMonth := 0; // no more daylight saving
      Result.BaseRules.HasDaylightSaving := False;
    End;
  Except
    DestroyTimeZoneInformation(Result);

    Raise;
  End;
End;

Procedure DestroyTimeZoneInformation(Var aTimeZoneInformation : TTimeZoneInformation);
Var
  iYearRuleIndex : Integer;
Begin
  If Assigned(aTimeZoneInformation) Then
  Begin
    FreeAndNil(aTimeZoneInformation.BaseRules);

    For iYearRuleIndex := Low(aTimeZoneInformation.YearRules) To High(aTimeZoneInformation.YearRules) Do
      FreeAndNil(aTimeZoneInformation.YearRules[iYearRuleIndex]);

    aTimeZoneInformation.Free;
    aTimeZoneInformation := Nil;
  End;
End;

Function GetTargetDay(Const iYear, iMonth, iWeek, iDayOfWeek : Integer) : Integer;
Var
  iLoop : Integer;
Begin
  If (iWeek < 1) Or (iWeek > 4) Then
  Begin
    iLoop := MONTHS_DAYS[IsLeapYearByYear(iYear)][TMonthOfYear(iMonth-1)];
    While DayOfWeek(EncodeDate(iYear, iMonth, iLoop)) - 1 <> iDayOfWeek Do
      Dec(iLoop);
  End
  Else
  Begin
    iLoop := 7 * (iWeek - 1) + 1;
    While DayOfWeek(EncodeDate(iYear, iMonth, iLoop)) - 1 <> iDayOfWeek Do
      Inc(iLoop);
  End;
  Result := iLoop;
End;


Function IsAfterDaylightTime(Const aInstant : TDateTime; Const aTime : TSystemTime) : Boolean;
Var
  iYear, iMonth, iDay, iMin, iHour, iSec, iMSec : Word;
  iTarget : Integer;
Begin
  // we have in aTime
  //   month - the month it starts
  //   day of week - day that it clicks in (0 = Sunday)
  //   day - which week in the month it clicks in. 1 = 1st, 2 = 2nd, 3 = 3rd, 4 = 4th, else "last"
  //   min/sec
  DecodeDate(aInstant, iYear, iMonth, iDay);
  DecodeTime(aInstant, iHour, iMin, iSec, iMSec);
  Result := False;
  If (iMonth > aTime.wMonth) Then
  Begin
    Result := True;
  End
  Else If (iMonth = aTime.wMonth) Then
  Begin
    iTarget := getTargetDay(iYear, iMonth, aTime.wDay, aTime.wDayOfWeek);
    If (iDay > iTarget) Then
    Begin
      Result := True;
    End
    Else If (iDay = iTarget) Then
    Begin
      If (iHour > aTime.wHour) Then
        Result := True
      Else If (iHour = aTime.wHour) Then
        Result := (iMin >= aTime.wMinute);
    End;
  End;
End;


Function isDayLightSaving(Const aRules : TTimeZoneYearInfo; Const aInstant: TDateTime; Const bInstantIsUTC : Boolean) : Boolean;
Begin
  If Not aRules.HasDaylightSaving Then
  Begin
    Result := False;
  End
  Else If (aRules.StandardStart.wMonth < aRules.DaylightStart.wMonth) Or (aRules.DaylightStart.wMonth = 0) Then
  Begin
    // we start the year in daylight saving
    If bInstantIsUTC Then
    Begin
      If (aRules.DaylightStart.wMonth <> 0) And IsAfterDaylightTime(aInstant - (aRules.StandardBias / 1440), aRules.DaylightStart) Then
        Result := True
      Else
        Result := (aRules.StandardStart.wMonth <> 0) And Not IsAfterDaylightTime(aInstant - (aRules.DaylightBias / 1440), aRules.StandardStart);
    End
    Else
    Begin
      // we ignore the vexed question of whether aInstant is standard or daylight saving
      If (aRules.DaylightStart.wMonth <> 0) And IsAfterDaylightTime(aInstant, aRules.DaylightStart) Then
        Result := True
      Else
        Result := (aRules.StandardStart.wMonth <> 0) And Not IsAfterDaylightTime(aInstant, aRules.StandardStart);
    End;
  End
  Else
  Begin
    // we start the year in standard time

    If bInstantIsUTC Then
    Begin
      If IsAfterDaylightTime(aInstant - (aRules.DaylightBias / 1440), aRules.StandardStart) Then
        Result := False
      Else
        Result := IsAfterDaylightTime(aInstant - (aRules.StandardBias / 1440), aRules.DaylightStart);
    End
    Else
    Begin
      If IsAfterDaylightTime(aInstant, aRules.StandardStart) Then
        Result := False
      Else
        Result := IsAfterDaylightTime(aInstant, aRules.DaylightStart);
    End;
  End;
End;

Function isDayLightSavings(Const aTimeZone : TTimeZoneInformation; Const aInstant : TDateTime; Const bInstantIsUTC : Boolean) : Boolean; Overload;
Var
  bFound : Boolean;
  iLoop : Integer;
  iYear, iMonth, iDay : Word;
Begin
  // aInstant is in UTC. Get the year for the instant in local time
  // strictly, we should recalculate the year for each year
  // if there's ever a timezone where the timezone changes at the click of the new year, and it does so
  // for a particular year, this will be wrong
  If Not bInstantIsUTC Then
    DecodeDate(aInstant, iYear, iMonth, iDay)
  Else If (aTimeZone.BaseRules.StandardStart.wMonth < aTimeZone.BaseRules.DaylightStart.wMonth) And (aTimeZone.BaseRules.DaylightStart.wMonth <> 0) Then
    // because on the turn of the year, we're in daylight saving time
    DecodeDate(aInstant - (aTimeZone.BaseRules.DaylightBias / 1440), iYear, iMonth, iDay)
  Else
    DecodeDate(aInstant - (aTimeZone.BaseRules.StandardBias / 1440), iYear, iMonth, iDay);

  // First of all, which information applies?
  bFound := False;
  Result := False;

  For iLoop := Low(aTimeZone.YearRules) To High(aTimeZone.YearRules) Do
  Begin
    If iYear = aTimeZone.YearRules[iLoop].Year Then
    Begin
      Result := isDayLightSaving(aTimeZone.YearRules[iLoop], aInstant, bInstantIsUTC);
      bFound := True;

      Break;
    End;
  End;

  If Not bFound Then
    Result := isDayLightSaving(aTimeZone.BaseRules, aInstant, bInstantIsUTC)
End;


Function TimeZoneNameShort(Const aTimeZoneInformation : TTimeZoneInformation; Const aInstant : TDateTime; Const bInstantIsUTC : Boolean) : String;
Begin
  // this is different to TimeZone_CODES[TimeZone] because it includes "S" or "D", based on instant

  Case aTimeZoneInformation.Identity Of
    TimeZoneNZ :
      If isDayLightSavings(aTimeZoneInformation, aInstant, bInstantIsUTC) Then
        Result := 'NZDT'
      Else
        Result := 'NZST';
    TimeZoneAustraliaVicNSW, TimeZoneAustraliaTas :
      If isDayLightSavings(aTimeZoneInformation, aInstant, bInstantIsUTC) Then
        Result := 'AEDT'
      Else
        Result := 'AEST';
    TimeZoneAustraliaQLD :
        Result := 'AEST';
    TimeZoneAustraliaSA :
      If isDayLightSavings(aTimeZoneInformation, aInstant, bInstantIsUTC) Then
        Result := 'ACDT'
      Else
        Result := 'ACST';
    TimeZoneAustraliaNT :
        Result := 'ACST';
    TimeZoneAustraliaWA :
      If isDayLightSavings(aTimeZoneInformation, aInstant, bInstantIsUTC) Then
        Result := 'AWDT'
      Else
        Result := 'AWST';
    TimeZoneAfghanistan..TimeZoneYakutsk :
      If isDayLightSavings(aTimeZoneInformation, aInstant, bInstantIsUTC) Then
        Result := NAMES_TIMEZONES[aTimeZoneInformation.Identity]+'(D)'
      Else
        Result := NAMES_TIMEZONES[aTimeZoneInformation.Identity]+'(S)';
  Else // TimeZoneUnknown
    Result := 'Unknown';
  End;
End;


Function TimeZoneBias : TDateTime;
Var
  aTimeZone : TIME_ZONE_INFORMATION;
Begin
  // this is the current TimeZone bias in the current TimeZone.

  Case GetTimeZoneInformation(aTimeZone) Of
    TIME_ZONE_ID_DAYLIGHT : Result := aTimeZone.Bias + aTimeZone.DaylightBias;
    TIME_ZONE_ID_STANDARD : Result := aTimeZone.Bias + aTimeZone.StandardBias;
    TIME_ZONE_ID_UNKNOWN : Result := aTimeZone.Bias;
  Else
    Error('TimeZoneBias', StringFormat('Unable to get time zone information [%s]', [ErrorAsString]));

    Result := 0;
  End;

  Result := Result / -1440;
End;


Function TimeZoneBias(Const aTimeZoneInformation : TTimeZoneInformation) : TDateTime;
Begin
  // this is the current TimeZone bias in the specified TimeZone

  Result := TimeZoneBias(aTimeZoneInformation, UniversalDateTime, True);
End;


Function TimeZoneBias(Const aTimeZoneInformation : TTimeZoneInformation; Const aInstant : TDateTime; Const bInstantIsUTC : Boolean) : TDateTime;
Begin
  // this is the TimeZone bias in the current TimeZone at the time specified
  If isDayLightSavings(aTimeZoneInformation, aInstant, bInstantIsUTC) Then
    Result := aTimeZoneInformation.BaseRules.DaylightBias
  Else
    Result := aTimeZoneInformation.BaseRules.StandardBias;
  Result := Result / -1440;
End;



Function AsDate(Const aValue : TDateTime) : TDate;
Begin
  Result := Int(aValue);
End;


Function AsTime(Const aValue : TDateTime) : TTime;
Begin
  Result := Frac(aValue);
End;
Function UniversalDate : TDateTime;
Begin
  Result := AsDate(UniversalDateTime);
End;


Function UniversalTime : TDateTime;
Begin
 Result := AsTime(UniversalDateTime);
End;


Function UniversalDateTime : TDateTime;
Var
  LrSystemTime : TSystemTime;
Begin
  GetSystemTime(LrSystemTime);

  Result := SystemTimeToDateTime(LrSystemTime);
End;


Function LocalDate : TDateTime;
Begin
  Result := AsDate(LocalDateTime);
End;


Function LocalTime : TDateTime;
Begin
  Result := AsTime(LocalDateTime);
End;


Function LocalDateTime : TDateTime;
Begin
  Result := SysUtils.Now;
End;



Function LocalDateTimeToUniversalDateTime(Const aDateTime : TDateTime; Const aTimeZoneInformation : TTimeZoneInformation) : TDateTime;
Begin
  Result := aDateTime - TimeZoneBias(aTimeZoneInformation, aDateTime, False);
End;


Function UniversalDateTimeToLocalDateTime(Const aDateTime : TDateTime; Const aTimeZoneInformation : TTimeZoneInformation) : TDateTime;
Begin
  // converts to local time, but you can't know what local time exactly

  Result := aDateTime + TimeZoneBias(aTimeZoneInformation, aDateTime, True);
End;


const
  MINUTE_LENGTH = 1 / (24 * 60);
  SECOND_LENGTH = MINUTE_LENGTH / 60;

function DescribePeriod(Period: TDateTime): String;
begin
  if period < 0 then
    period := -period;
  if Period < SECOND_LENGTH then
    Result := IntToStr(trunc(Period * 1000 / SECOND_LENGTH)) + 'ms'
  else if Period < 180 * SECOND_LENGTH then
    Result := IntToStr(trunc(Period / SECOND_LENGTH)) + 'sec'
  else if Period < 180 * MINUTE_LENGTH then
    Result := IntToStr(trunc(Period / MINUTE_LENGTH)) + 'min'
  else if Period < 72 * 60 * MINUTE_LENGTH then
    Result := IntToStr(trunc(Period / (MINUTE_LENGTH * 60))) + 'hr'
  else
    Result := IntToStr(trunc(Period)) + ' days';
end;



End. // DateSupport //


