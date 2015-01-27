unit DateAndTime;

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

interface

uses
  Windows, BytesSupport, DecimalSupport, StringSupport,
  DateSupport, SysUtils, AdvObjects, KDate, MathSupport, HL7v2DateSupport;

type
  TDateAndTimePrecision = (dtpYear, dtpMonth, dtpDay, dtpHour, dtpMin, dtpSec, dtpNanoSeconds);
  TDateAndTimeTimezone = (dttzUnknown, dttzUTC, dttzLocal, dttzSpecified);

Const
  codes_TDateAndTimePrecision : Array [TDateAndTimePrecision] of String = ('Year', 'Month', 'Day', 'Hour', 'Min', 'Sec', 'NanoSeconds');
  codes_TDateAndTimeTimezone : Array [TDateAndTimeTimezone] of String = ('Unknown', 'UTC', 'Local', 'Specified');

Type
{$M+}

  {@Class TDateAndTime
    This class is used to hold date and time information, along with precision and a timezone.

    Note that the timezone conversions are based on the information in the local windows registry.
    This is not always correct in all circumstances, but patches usually exist where it is not.
  }
  TDateAndTime = class (TAdvObject)
  Private
    FSource : String;
    FTimeStamp: TTimeStamp;
    FPrecision : TDateAndTimePrecision;
    FFractionPrecision : integer;
    FTimezoneType : TDateAndTimeTimezone;
    FTimeZoneHours : Integer;
    FTimezoneMins : Integer;

    function AllValid(var err : String): Boolean;
    function GetAsHL7: String;
    procedure SetAsHL7(Value: String);
    function GetAsXml: String;
    procedure SetAsXml(Value: String);
  Public
    constructor Create; Override;
    destructor Destroy; Override;

    class function CreateHL7(value : String) : TDateAndTime;
    class function CreateXML(value : String) : TDateAndTime;
    class function CreateUTC(value : TDateTime) : TDateAndTime;

    Function Link : TDateAndTime; overload;
    Function Clone : TDateAndTime; overload;
    procedure Assign(source : TAdvObject); Override;
    function GetAsString: String;
    function ToString: String; override;
    function GetDateTime: TDateTime;
    function GetYear: Integer;
    procedure SetYear(Year: Integer);
    function GetMonth: Integer;
    procedure SetMonth(Month: Integer);
    function GetDay: Integer;
    procedure SetDay(Day: Integer);
    function GetHour: Integer;
    procedure SetHour(Hour: Integer);
    function GetMinute: Integer;
    procedure SetMinute(Minute: Integer);
    function GetSecond: Integer;
    procedure SetSecond(Second: Integer);
    function GetNanoSecond: Integer;
    procedure SetNanoSecond(NanoSecond: Integer);
    procedure SetDateTime(dt: TDateTime);
    function AsUTCDateTime : TDateTime;
    function AsUTCDateTimeMin : TDateTime;
    function AsUTCDateTimeMax : TDateTime;
    function AsUTCDateTimeMinHL7 : String;
    function AsUTCDateTimeMaxHL7 : String;
    function source : String;


      {@Member ReadDate
         Read a date (date) given the specified format. The specified
         format can be any combination of YYYY, YYY, YY, MM, MMM, DD, HH, NN, SS.

         Use spaces for parts of the date that are just separators.
         This method can't cope with variable length date representations such as full month names.
         If the year is < 100, it will be adjusted to a current year (irrespective of the year length YYYY or YY). See ReadDateStrict
      }
    procedure ReadDate(format, date: String; AllowBlankTimes: Boolean = False; allowNoDay: Boolean = False; allownodate: Boolean = False);

      {@Member ReadDate
        Like ReadDate, but years < 100 will not be corrected as if they are 2 digit year representations
      }
    procedure ReadDateStrict(format, date: String; AllowBlankTimes: Boolean = False; allowNoDay: Boolean = False; allownodate: Boolean = False);

      {@Member IncrementMonth
      Adds one to the current month, and if needed, will increment the year if needed
      }
    procedure IncrementMonth;
      {@Member IncrementYear
      Adds one to the year
      }
    procedure IncrementYear;
      {@Member IncrementQuarter
      Adds 3 to the month number.
      }
    procedure IncrementQuarter;
      {@Member IncrementWeek                                              yy
      Adds 7 to the day number and corrects month and year as needed.
      }
    procedure IncrementWeek;
      {@Member IncrementDay
      Adds one to the day number, and may update month and year as needed.
      }
    procedure IncrementDay;

      {@Member FormatTimeStamp
      Returns the date and time in the format specified.
      <PRE>

      Valid formatting strings are

      yyyy    4 digit year
      yy      2 digit year
      mmmm    long month name
      mmm     short month name
      mm      2 digit month number (will include leading 0 if needed)
      m       month number (no leading 0)
      dd      2 digit day number (will include leading 0 if needed)
      d       day number (no leading 0)
      hh      2 digit hour number
      nn      2 digit minutes
      ss      2 digit seconds
      </PRE>
      }
    function FormatTimeStamp(format: String): String;

    {@member AsLocal
      This date and time in the local timezone of the computer
    }
    function AsLocal : TDateAndTime;

    {@member AsUTC
      this date and time in UTC time
    }
    function AsUTC : TDateAndTime;

    {@member AsTZ
      this date and time in the specified timezone.
    }
    function AsTz(hr, min : Integer):TDateAndTime;

    {@member Equal
      returns true if the timezone, precision, and actual instant are the same
    }
    function Equal(other : TDateAndTime) : Boolean;

    {@member SameTime
      returns true if the specified instant is the same allowing for specified precision - corrects for timezone
    }
    function SameTime(other : TDateAndTime) : Boolean;

    {!SCRIPT HIDE}
    property TimeStamp: TTimeStamp Read FTimeStamp Write FTimeStamp;
    property DateTime: TDateTime Read GetdateTime Write SetDateTime;
    {!SCRIPT SHOW}
  Published
      {@Member AsString
      Returns the date and time as a string using the current locale value. This is primarily for debugging
      as the format is fixed to the host computer's format
      }
    property AsString: String Read GetAsString;

      {@Member AsHL7
      The date and time as a string using the standard HL7 format (yyyymmddhhnnss.zzzT)
      }
    property AsHL7: String Read GetAsHL7 write SetAsHL7;

      {@Member AsHL7
      The date and time as a string using the standard XML format (yyyy-mm-dd"T"hh:nn:ssT)
      }
    property AsXML : String read GetAsXml write SetAsXml;
      {@Member Year
      This is the year represented as an integer. It will include the century too.
      }
    property Year: Integer Read GetYear Write SetYear;
      {@Member Month
      This is the month represented as an integer.
      }
    property Month: Integer Read GetMonth Write SetMonth;
      {@Member Day
      This is the day represented as an integer.
      }
    property Day: Integer Read GetDay Write SetDay;
      {@Member Hour
      This is the hour represented as an integer.
      }
    property Hour: Integer Read GetHour Write SetHour;
      {@Member Minute
      This is the Minute represented as an integer.
      }
    property Minute: Integer Read GetMinute Write SetMinute;
      {@Member Second
      This is the seconds represented as an integer.
      }
    property Second: Integer Read GetSecond Write SetSecond;
      {@Member NanoSecond
      This is the number of nane seconds represented as an integer.
      }
    property NanoSecond: Integer Read GetNanoSecond Write SetNanoSecond;

    {@member Precision
      The precision to which the date and time is specified
    }
    Property Precision : TDateAndTimePrecision read FPrecision write FPrecision;

    Property FractionPrecision : Integer read FFractionPrecision write FFractionPrecision;
    {@member TzType
      The type of timezone
    }
    Property TimezoneType : TDateAndTimeTimezone read FTimezoneType write FTimezoneType;

    {@member TzHour
      Timezone hours
    }
    Property TzHour : Integer read FTimeZoneHours write FTimeZoneHours;

    {@member TzMinute
      timezone minutes
    }
    Property TzMinute : Integer read FTimezoneMins write FTimezoneMins;

  end;

{@Routine NowUTC
Returns a TDateAndTime object set to todays date and the current time, in UTC
}
function NowUTC : TDateAndTime;

{@Routine NowLocal
Returns a TDateAndTime object set to todays date and the current time, in the local timezone
}
function NowLocal : TDateAndTime;

{@Routine Today
Returns a TDateAndTime object set to todays date and no time (and no timezone either)
}
function Today: TDateAndTime;

implementation

uses
  IdSMTP,
  GuidSupport,
  EncodeSupport;

constructor TDateAndTime.Create;
begin
  inherited;
end;

destructor TDateAndTime.Destroy;
begin
  inherited;
end;

function TDateAndTime.AllValid(var err : String): Boolean;
begin
  Result := False;
  err := '';
  with FTimeStamp do
    begin
    if Year > 3000 then
      err := 'Year is not valid'
    else if (precision >= dtpMonth) and ((Month > 12) or (Month < 1)) then
      err := 'Month is not valid'
    else if (precision >= dtpDay) and ((Day < 1) or (Day >= 32) or (MONTHS_DAYS[IsLeapYear(Year)][TMonthOfYear(Month-1)] < Day)) then
      err := 'Day is not valid for '+inttostr(Year)+'/'+inttostr(Month)
    else if (precision >= dtpHour) and (Hour > 23) then
      err := 'Hour is not valid'
    else if (precision >= dtpMin) and (Minute > 59) then
      err := 'Minute is not valid'
    else if (precision >= dtpSec) and (Second > 59) then
      err := 'Second is not valid'
    else if (precision >= dtpNanoSeconds) and (FractionPrecision > 999999999) then
      err := 'Fraction is not valid'
    else if (TimezoneType = dttzSpecified) and ((TzHour < -13) or (TzHour > 14)) then
      err := 'Timezone hours is not valid'
    else if (TimezoneType = dttzSpecified) and not ((TzMinute = 0) or (TzMinute = 15) or (TzMinute = 30) or (TzMinute = 45)) then
      err := 'Timezone minutes is not valid'
    else
      Result := True;
    end;
end;

function TDateAndTime.GetAsString: String;
begin
  Result := DateTimeToStr(DateTime);
end;

function TDateAndTime.GetDateTime: TDateTime;
var
  msg : String;
begin
  if not AllValid(msg) then
    raise Exception.Create('Invalid date encountered ('+msg+')');
  case Precision of
    dtpYear : Result := EncodeDate(FTimeStamp.Year, 1, 1);
    dtpMonth : Result := EncodeDate(FTimeStamp.Year, FTimeStamp.Month, 1);
    dtpDay: Result := EncodeDate(FTimeStamp.Year, FTimeStamp.Month, FTimeStamp.Day);
    dtpHour : Result := EncodeDate(FTimeStamp.Year, FTimeStamp.Month, FTimeStamp.Day) + EncodeTime(FTimeStamp.Hour, 0, 0, 0);
    dtpMin : Result := EncodeDate(FTimeStamp.Year, FTimeStamp.Month, FTimeStamp.Day) + EncodeTime(FTimeStamp.Hour, FTimeStamp.Minute, 0, 0);
    dtpSec : Result := EncodeDate(FTimeStamp.Year, FTimeStamp.Month, FTimeStamp.Day) + EncodeTime(FTimeStamp.Hour, FTimeStamp.Minute, FTimeStamp.Second, 0);
    dtpNanoSeconds : Result := EncodeDate(FTimeStamp.Year, FTimeStamp.Month, FTimeStamp.Day) + EncodeTime(FTimeStamp.Hour, FTimeStamp.Minute, FTimeStamp.Second, FTimeStamp.Fraction div 1000000);
  else
    Result := 0;
  end;
end;

procedure TDateAndTime.SetDateTime(dt: TDateTime);
var
  ms: Word;
  yr: Word;
begin
  DecodeTime(dt, FTimeStamp.Hour, FTimeStamp.Minute, FTimeStamp.Second, ms);
  FTimeStamp.Fraction := ms * 1000000;
  DecodeDate(dt, yr, FTimeStamp.Month, FTimeStamp.Day);
  FTimeStamp.Year := yr;
  FPrecision := dtpSec;
  FFractionPrecision := 0;
  FTimezoneType := dttzLocal;
end;

function TDateAndTime.GetYear: Integer;
begin
  Result := FTimeStamp.Year;
end;

procedure TDateAndTime.SetYear(Year: Integer);
begin
  FTimeStamp.Year := Year;
end;

function TDateAndTime.GetMonth: Integer;
begin
  Result := FTimeStamp.Month;
end;

procedure TDateAndTime.SetMonth(Month: Integer);
begin
  FTimeStamp.Month := Month;
end;

function TDateAndTime.GetDay: Integer;
begin
  Result := FTimeStamp.Day;
end;

procedure TDateAndTime.SetDay(Day: Integer);
begin
  FTimeStamp.Day := Day;
end;

function TDateAndTime.GetHour: Integer;
begin
  Result := FTimeStamp.Hour;
end;

procedure TDateAndTime.SetHour(Hour: Integer);
begin
  FTimeStamp.Hour := Hour;
end;

function TDateAndTime.GetMinute: Integer;
begin
  Result := FTimeStamp.Minute;
end;

procedure TDateAndTime.SetMinute(Minute: Integer);
begin
  FTimeStamp.Minute := Minute;
end;

function TDateAndTime.GetSecond: Integer;
begin
  Result := FTimeStamp.Second;
end;

procedure TDateAndTime.SetSecond(Second: Integer);
begin
  FTimeStamp.Second := Second;
end;

function TDateAndTime.GetNanoSecond: Integer;
begin
  Result := FTimeStamp.Fraction;
end;

procedure TDateAndTime.SetNanoSecond(NanoSecond: Integer);
begin
  FTimeStamp.Fraction := NanoSecond;
end;

procedure TDateAndTime.ReadDate(format, date: String; AllowBlankTimes: Boolean = False; allowNoDay: Boolean = False; allownodate: Boolean = False);
begin
  FTimeStamp := KDate.ReadDate(format, date, AllowBlankTimes, allowNoDay, allownodate);
  if (Hour = 0) and (Minute = 0) and (Second = 0) then
    FPrecision := dtpDay
  else
    FPrecision := dtpSec;
  FFractionPrecision := 0;
  FTimezoneType := dttzLocal;
end;

procedure TDateAndTime.ReadDateStrict(format, date: String; AllowBlankTimes: Boolean = False; allowNoDay: Boolean = False; allownodate: Boolean = False);
begin
  FTimeStamp := KDate.ReadDate(format, date, AllowBlankTimes, allowNoDay, allownodate, true);
  if (Hour = 0) and (Minute = 0) and (Second = 0) then
    FPrecision := dtpDay
  else
    FPrecision := dtpSec;
  FFractionPrecision := 0;
  FTimezoneType := dttzLocal;
end;

procedure TDateAndTime.IncrementMonth;
begin
  KDate.IncrementMonth(FTimeStamp);
end;

procedure TDateAndTime.IncrementYear;
begin
  KDate.IncrementYear(FTimeStamp);
end;

procedure TDateAndTime.IncrementQuarter;
begin
  KDate.IncrementQuarter(FTimeStamp);
end;

procedure TDateAndTime.IncrementWeek;
begin
  KDate.IncrementWeek(FTimeStamp);
end;

procedure TDateAndTime.IncrementDay;
begin
  KDate.IncrementDay(FTimeStamp);
end;

function TDateAndTime.FormatTimeStamp(format: String): String;
begin
  Result := KDate.FormatTimeStamp(format, FTimeStamp);
end;

function Today: TDateAndTime;
begin
  Result := TDateAndTime.Create;
  Result.DateTime := trunc(sysutils.now);
end;

class function TDateAndTime.CreateHL7(value: String) : TDateAndTime;
begin
  result := TDateAndTime.Create;
  try
    result.AsHL7 := value;
    result.Link;
  finally
    result.Free;
  end;
end;

class function TDateAndTime.CreateXML(value: String) : TDateAndTime;
begin
  result := TDateAndTime.Create;
  try
    result.AsXml := value;
    result.Link;
  finally
    result.free;
  end;
end;

Function sv(i, w : integer):String;
begin
  result := StringPadLeft(inttostr(abs(i)), '0', w);
  if i < 0 then
    result := '-'+result;
end;

function TDateAndTime.GetAsHL7: String;
begin
  case FPrecision of
    dtpYear:  result := sv(Year, 4);
    dtpMonth: result := sv(Year, 4) + sv(Month, 2);
    dtpDay:   result := sv(Year, 4) + sv(Month, 2) + sv(Day, 2);
    dtpHour:  result := sv(Year, 4) + sv(Month, 2) + sv(Day, 2) + sv(hour, 2);
    dtpMin:   result := sv(Year, 4) + sv(Month, 2) + sv(Day, 2) + sv(hour, 2)+ sv(Minute, 2);
    dtpSec:   result := sv(Year, 4) + sv(Month, 2) + sv(Day, 2) + sv(hour, 2)+ sv(Minute, 2)+ sv(Second, 2);
    dtpNanoSeconds: result := sv(Year, 4) + sv(Month, 2) + sv(Day, 2) + sv(hour, 2)+ sv(Minute, 2)+ sv(Second, 2)+'.'+copy(sv(NanoSecond, 9), 1, FFractionPrecision);
  end;
  case FTimezoneType of
    dttzUTC : result := result + 'Z';
    dttzSpecified :
      if TzHour < 0 then
        result := result + sv(TzHour, 2) + sv(TzMinute, 2)
      else
        result := result + '+'+sv(TzHour, 2) + sv(TzMinute, 2);
    dttzLocal :
      if TimeZoneBias > 0 then
        result := result + '+'+FormatDateTime('hhnn', TimeZoneBias, FormatSettings)
      else
        result := result + '-'+FormatDateTime('hhnn', -TimeZoneBias, FormatSettings);
  {else
    dttzUnknown - do nothing }
  end;
end;

function TDateAndTime.GetAsXml: String;
begin
  if self = nil then
  begin
    result := '(null?)';
    exit;
  end;

  case FPrecision of
    dtpYear:  result := sv(Year, 4);
    dtpMonth: result := sv(Year, 4) + '-' + sv(Month, 2);
    dtpDay:   result := sv(Year, 4) + '-' + sv(Month, 2) + '-' + sv(Day, 2);
    dtpHour:  result := sv(Year, 4) + '-' + sv(Month, 2) + '-' + sv(Day, 2) + 'T' + sv(hour, 2) + ':' + sv(Minute, 2); // note minutes anyway in this case
    dtpMin:   result := sv(Year, 4) + '-' + sv(Month, 2) + '-' + sv(Day, 2) + 'T' + sv(hour, 2) + ':' + sv(Minute, 2);
    dtpSec:   result := sv(Year, 4) + '-' + sv(Month, 2) + '-' + sv(Day, 2) + 'T' + sv(hour, 2) + ':' + sv(Minute, 2)+ ':' + sv(Second, 2);
    dtpNanoSeconds: result := sv(Year, 4) + '-' + sv(Month, 2) + '-' + sv(Day, 2) + 'T' + sv(hour, 2) + ':' + sv(Minute, 2)+ ':' + sv(Second, 2)+'.'+copy(sv(NanoSecond, 9), 1, FFractionPrecision);
  end;
  case FTimezoneType of
    dttzUTC : result := result + 'Z';
    dttzSpecified :
      if TzHour < 0 then
        result := result + sv(TzHour, 2) + ':'+sv(TzMinute, 2)
      else
        result := result + '+'+sv(TzHour, 2) + ':'+sv(TzMinute, 2);
    dttzLocal :
      if TimeZoneBias > 0 then
        result := result + '+'+FormatDateTime('hh:nn', TimeZoneBias, FormatSettings)
      else
        result := result + '-'+FormatDateTime('hh:nn', -TimeZoneBias, FormatSettings);
  {else
    dttzUnknown - do nothing }
  end;
end;

function vs(value : String; start, len, min, max : Integer; name : String):Integer;
var
  v : String;
begin
  v := copy(value, start, len);
  if not StringIsInteger16(v) then
    raise exception.create('Unable to parse date/time "'+value+'" at '+name);
  result := StrToInt(v);
  if (result < min) or (result > max) then
    raise exception.create('Value for '+name+' in date/time "'+value+'" is not allowed');

end;

procedure TDateAndTime.SetAsHL7(Value: String);
var
  s : String;
  neg : boolean;
  msg : String;
begin
  FSource := Value;

  if pos('Z', value) = length(value) then
  begin
    FTimezoneType := dttzUTC;
    Delete(value, length(value), 1);
  end
  else if StringContainsAny(Value, ['-', '+']) then
  begin
    neg := Pos('-', Value) > 0;
    StringSplit(value, ['-', '+'], value, s);
    if length(s) <> 4 then
      raise Exception.create('Unable to parse date/time "'+value+'": timezone is illegal length - must be 4');
    TzHour := vs(s, 1, 2, 0, 13, 'timezone hours');
    TzMinute := vs(s, 3, 2, 0, 59, 'timezone minutes');
    TimezoneType := dttzSpecified;
    if neg then
      TzHour := -TzHour;
  end;

  FFractionPrecision := 0;

  if Length(value) >=4 then
    Year := vs(Value, 1, 4, 1800, 2100, 'years');
  if Length(value) < 6 then
    Precision := dtpYear
  else
  begin
    Month := vs(Value, 5, 2, 1, 12, 'months');
    if length(Value) < 8 then
      Precision := dtpMonth
    else
    begin
      Day := vs(Value, 7, 2, 1, 31, 'days');
      if length(Value) < 10 then
        Precision := dtpDay
      else
      begin
        Hour := vs(Value, 9, 2, 0, 23, 'hours');
        if length(Value) < 12 then
          Precision := dtpHour
        else
        begin
          Minute := vs(Value, 11, 2, 0, 59, 'minutes');
          if length(Value) < 14 then
            Precision := dtpMin
          else
          begin
            Second := vs(Value, 13, 2, 0, 59, 'seconds');
            if length(Value) <= 15 then
              Precision := dtpSec
            else
            begin
              s := copy(Value, 16, 4);
              FFractionPrecision := length(s);
              NanoSecond := trunc(vs(Value, 16, 4, 0, 9999, 'fractions') * power(10, 9 - FFractionPrecision));
              Precision := dtpNanoSeconds;
            end;
          end;
        end;
      end;
    end;
  end;
  if not AllValid(msg) then
    raise Exception.Create('Invalid date encountered ('+msg+')');
end;

procedure TDateAndTime.SetAsXml(Value: String);
var
  s : String;
  neg : boolean;
  msg : String;
begin
  FSource := Value;
  if pos('Z', value) = length(value) then
  begin
    FTimezoneType := dttzUTC;
    Delete(value, length(value), 1);
  end
  else if (pos('T', value) > 0) and StringContainsAny(copy(Value, pos('T', value)+1, $FF), ['-', '+']) then
  begin
    neg := Pos('-', copy(Value, pos('T', value)+1, $FF)) > 0;
    StringSplitRight(value, ['-', '+'], value, s);
    if length(s) <> 5 then
      raise Exception.create('Unable to parse date/time "'+value+'": timezone is illegal length - must be 5');
    TzHour := vs(s, 1, 2, 0, 14, 'timezone hours');
    TzMinute := vs(s, 4, 2, 0, 59, 'timezone minutes');
    TimezoneType := dttzSpecified;
    if neg then
      TzHour := -TzHour;
  end;

  FFractionPrecision := 0;

  if Length(value) >=4 then
    Year := vs(Value, 1, 4, 1800, 2100, 'years');
  if Length(value) < 7 then
    Precision := dtpYear
  else
  begin
    Month := vs(Value, 6, 2, 1, 12, 'months');
    if length(Value) < 10 then
      Precision := dtpMonth
    else
    begin
      Day := vs(Value, 9, 2, 1, 31, 'days');
      if length(Value) < 13 then
        Precision := dtpDay
      else
      begin
        Hour := vs(Value, 12, 2, 0, 23, 'hours');
        if length(Value) < 15 then
          Precision := dtpHour
        else
        begin
          Minute := vs(Value, 15, 2, 0, 59, 'minutes');
          if length(Value) < 18 then
            Precision := dtpMin
          else
          begin
            Second := vs(Value, 18, 2, 0, 59, 'seconds');
            if length(Value) <= 20 then
              Precision := dtpSec
            else
            begin
              s := copy(Value, 21, 6);
              FFractionPrecision := length(s);
              NanoSecond := trunc(vs(Value, 21, 4, 0, 999999, 'fractions') * power(10, 9 - FFractionPrecision));
              Precision := dtpNanoSeconds;
            end;
          end;
        end;
      end;
    end;
  end;
  if not AllValid(msg) then
    raise Exception.Create('Invalid date encountered ('+msg+')');
end;


function TDateAndTime.AsLocal: TDateAndTime;
var
  aTime : TTimeZoneInformation;
  bias : TDateTime;
begin
  result := nil;
  if Precision < dtpHour then
  begin
    result := self.clone;
    result.TimezoneType := dttzLocal;
  end
  else case TimezoneType of
    dttzUnknown : result := Self.Clone;
    dttzLocal : result := self.Clone;
    dttzUTC :
      begin
      result := TDateAndTime.create;
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result.SetDatetime(UniversalDateTimeToLocalDateTime(GetDateTime, aTime));
        result.precision := precision;
        result.FractionPrecision := FractionPrecision;
        result.TimezoneType := dttzLocal;
        result.Link;
      finally
        DestroyTimeZoneInformation(aTime);
        result.free;
      end;
      end;
    dttzSpecified :
      begin
      if TzHour < 0 then
        bias := - (-TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE)
      else
        bias := (TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE);
      result := TDateAndTime.create;
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result.SetDatetime(UniversalDateTimeToLocalDateTime(GetDateTime-bias, aTime));
        result.precision := precision;
        result.FractionPrecision := FractionPrecision;
        result.TimezoneType := dttzLocal;
        result.Link;
      finally
        DestroyTimeZoneInformation(aTime);
        result.free;
      end;
      end;
  end;
end;

function TDateAndTime.AsTz(hr, min: Integer): TDateAndTime;
var
  aTime : TTimeZoneInformation;
  bias : TDateTime;
  nbias : TDateTime;
begin
  result := nil;
  if Hour < 0 then
    nbias := - (-Hr * DATETIME_HOUR_ONE) + (min * DATETIME_MINUTE_ONE)
  else
    nbias := (Hr * DATETIME_HOUR_ONE) + (min * DATETIME_MINUTE_ONE);

  if Precision < dtpHour then
  begin
    result := self.clone;
    result.TimezoneType := dttzSpecified;
    result.TzHour := hr;
    result.TzMinute := min;
  end
  else case TimezoneType of
    dttzUnknown : result := Self.Clone;
    dttzLocal :
      begin
      result := TDateAndTime.create;
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result.SetDatetime(LocalDateTimeToUniversalDateTime(GetDateTime, aTime)+nbias);
        result.precision := precision;
        result.FractionPrecision := FractionPrecision;
        result.TimezoneType := dttzLocal;
        result.Link;
      finally
        DestroyTimeZoneInformation(aTime);
        result.free;
      end;
      end;
    dttzUTC :
      begin
      result := TDateAndTime.create;
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result.SetDatetime(GetDateTime+nbias);
        result.precision := precision;
        result.FractionPrecision := FractionPrecision;
        result.TimezoneType := dttzLocal;
        result.Link;
      finally
        DestroyTimeZoneInformation(aTime);
        result.free;
      end;
      end;
    dttzSpecified :
      begin
      if TzHour < 0 then
        bias := - (-TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE)
      else
        bias := (TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE);
      result := TDateAndTime.create;
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result.SetDatetime(GetDateTime-bias+nbias);
        result.precision := precision;
        result.FractionPrecision := FractionPrecision;
        result.TimezoneType := dttzLocal;
        result.Link;
      finally
        DestroyTimeZoneInformation(aTime);
        result.free;
      end;
      end;
  end;
end;

function TDateAndTime.AsUTC: TDateAndTime;
var
  aTime : TTimeZoneInformation;
  bias : TDateTime;
begin
  result := nil;
  if Precision < dtpHour then
  begin
    result := self.clone;
    result.TimezoneType := dttzUTC;
  end
  else case TimezoneType of
    dttzUnknown : result := Self.Clone;
    dttzUTC : result := self.Clone;
    dttzLocal :
      begin
      result := TDateAndTime.create;
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result.SetDatetime(LocalDateTimeToUniversalDateTime(GetDateTime, aTime));
        result.precision := precision;
        result.FractionPrecision := FractionPrecision;
        result.TimezoneType := dttzUTC;
        result.Link;
      finally
        DestroyTimeZoneInformation(aTime);
        result.free;
      end;
      end;
    dttzSpecified :
      begin
      if TzHour < 0 then
        bias := - (-TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE)
      else
        bias := (TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE);
      result := TDateAndTime.create;
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result.SetDatetime(GetDateTime-bias);
        result.precision := precision;
        result.FractionPrecision := FractionPrecision;
        result.TimezoneType := dttzUTC;
        result.Link;
      finally
        DestroyTimeZoneInformation(aTime);
        result.free;
      end;
      end;
  end;
end;

function NowUTC : TDateAndTime;
begin
  result := TDateAndTime.create;
  try
    result.SetDateTime(UniversalDateTime);
    result.TimezoneType := dttzUTC;
    result.link;
  finally
    result.free;
  end;
end;

function NowLocal : TDateAndTime;
begin
  result := TDateAndTime.create;
  try
    result.SetDateTime(LocalDateTime);
    result.TimezoneType := dttzLocal;
    result.link;
  finally
    result.free;
  end;
end;


procedure TDateAndTime.Assign(source: TAdvObject);
var
  src : TDateAndTime;
begin
  inherited;
  src := TDateAndTime(Source);
  FTimeStamp := src.FTimeStamp;
  FPrecision := src.FPrecision;
  FFractionPrecision := src.FFractionPrecision;
  FTimezoneType := src.FTimezoneType;
  FTimeZoneHours  := src.FTimeZoneHours;
  FTimezoneMins := src.FTimezoneMins;
end;

function TDateAndTime.Clone: TDateAndTime;
begin
  result := TDateAndTime(Inherited Clone);
end;

function TDateAndTime.Link: TDateAndTime;
begin
  result := TDateAndTime(Inherited Link);
end;

function TDateAndTime.Equal(other: TDateAndTime): Boolean;
var
  src : TDateAndTime;
begin
  src := TDateAndTime(other);
  result := (FTimestamp.year = src.FTimestamp.year) and
    ((FPrecision < dtpMonth) or (FTimestamp.month = src.FTimestamp.month)) and
    ((FPrecision < dtpDay) or (FTimestamp.day = src.FTimestamp.day)) and
    ((FPrecision < dtpHour) or (FTimestamp.hour = src.FTimestamp.hour)) and
    ((FPrecision < dtpMin) or (FTimestamp.minute = src.FTimestamp.minute)) and
    ((FPrecision < dtpSec) or (FTimestamp.second = src.FTimestamp.second)) and
    ((FPrecision < dtpNanoSeconds) or (FTimestamp.fraction = src.FTimestamp.fraction)) and (FPrecision = src.FPrecision) and (FFractionPrecision = src.FFractionPrecision) and
    (FTimezoneType = src.FTimezoneType) and (FTimeZoneHours = src.FTimeZoneHours) and (FTimezoneMins = src.FTimezoneMins);
end;
                

function TDateAndTime.SameTime(other: TDateAndTime): Boolean;
begin
  raise Exception.create('not done yet');
end;

function TDateAndTime.AsUTCDateTime: TDateTime;
var
  msg : String;
  bias : TDateTime;
  aTime : TTimeZoneInformation;
begin
  if not AllValid(msg) then
    raise Exception.Create('Invalid date encountered ('+msg+')');
  Result := EncodeDate(FTimeStamp.Year, IntegerMax(FTimeStamp.Month, 1), IntegerMax(FTimeStamp.Day, 1));
  Result := Result + EncodeTime(FTimeStamp.Hour, FTimeStamp.Minute, FTimeStamp.Second, FTimeStamp.Fraction div 1000000);
  case FTimezoneType of
    dttzLocal:
      begin
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result := LocalDateTimeToUniversalDateTime(result, aTime);
      finally
        DestroyTimeZoneInformation(aTime);
      end;
      end;
    dttzSpecified:
       begin
        if TzHour < 0 then
          bias := - (-TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE)
        else
          bias := (TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE);
       result := result - bias;
       end;
  { else
    dttzUnknown:
    dttzUTC: }
  end;
end;

class function TDateAndTime.CreateUTC(value: TDateTime) : TDateAndTime;
begin
  result := Create;
  try
    result.SetDateTime(value);
    result.TimezoneType := dttzUTC;
    result.link;
  finally
    result.Free;
  end;
end;

function TDateAndTime.ToString: String;
begin
  result := GetAsString;
end;

function IsAfterLastMonthDay(y, m, d : integer) : boolean;
begin
  case m of
    1: result := d > 31;
    2: if IsLeapYear(y) then result := d > 29 else result := d > 28;
    3: result := d > 31;
    4: result := d > 30;
    5: result := d > 31;
    6: result := d > 30;
    7: result := d > 31;
    8: result := d > 31;
    9: result := d > 30;
    10: result := d > 31;
    11: result := d > 30;
    12: result := d > 31;
  else
    result := false;
  end;
end;

function TDateAndTime.AsUTCDateTimeMax: TDateTime;
var
  msg : String;
  bias : TDateTime;
  aTime : TTimeZoneInformation;
  local : TTimeStamp;
begin
  if not AllValid(msg) then
    raise Exception.Create('Invalid date encountered ('+msg+')');

  local := FTimeStamp;
  local.fraction := 0;
  case FPrecision of
    dtpYear : inc(local.year);
    dtpMonth : inc(local.month);
    dtpDay : inc(local.day);
    dtpHour : inc(local.hour);
    dtpMin : inc(local.minute);
    dtpSec, dtpNanoSeconds : inc(local.second);
  end;
  if local.second >= 60 then
  begin
    inc(local.minute);
    local.second := 0;
  end;
  if local.minute >= 60 then
  begin
    inc(local.hour);
    local.minute := 0;
  end;
  if local.hour >= 60 then
  begin
    inc(local.day);
    local.hour := 0;
  end;
  if IsAfterLastMonthDay(local.Year, local.month, local.day) then
  begin
    inc(local.month);
    local.day := 1;
  end;
  if local.month >= 12 then
  begin
    inc(local.year);
    local.month := 1;
  end;
  result := EncodeDate(local.Year, IntegerMax(local.month, 1), IntegerMax(local.day, 1)) + EncodeTime(local.Hour, local.minute, local.second, 0);

  case FTimezoneType of
    dttzLocal:
      begin
      aTime := CreateTimeZoneInformation(TimeZone);
      try
        result := LocalDateTimeToUniversalDateTime(result, aTime);
      finally
        DestroyTimeZoneInformation(aTime);
      end;
      end;
    dttzSpecified:
       begin
        if TzHour < 0 then
          bias := - (-TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE)
        else
          bias := (TzHour * DATETIME_HOUR_ONE) + (TzMinute * DATETIME_MINUTE_ONE);
       result := result - bias;
       end;
  { else
    dttzUnknown:
    dttzUTC: }
  end;
end;

function TDateAndTime.AsUTCDateTimeMin: TDateTime;
begin
  result := AsUTCDateTime;
end;

function TDateAndTime.AsUTCDateTimeMaxHL7: String;
begin
  result := HL7DateToString(AsUTCDateTimeMax, 'yyyymmddhhnnss', false)
end;

function TDateAndTime.AsUTCDateTimeMinHL7: String;
begin
  result := HL7DateToString(AsUTCDateTimeMin, 'yyyymmddhhnnss', false)
end;


function DateAndTimeFromXml(source : String) : TDateAndTime;
begin
  result := TDateAndTime.CreateXML(source);
end;

function DateAndTimeFromHL7(source : String) : TDateAndTime;
begin
  result := TDateAndTime.CreateHL7(source);
end;

function TDateAndTime.source: String;
begin
  result := FSource;
end;

end.

