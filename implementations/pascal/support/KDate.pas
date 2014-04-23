unit KDate;

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
  Classes,
  DateSupport,
  StringSupport,
  SysConst,
  SysUtils;

type
  EKDateFormatError = class(Exception);

  { transfer types for DATE, TIME, TIMESTAMP }
  TDate = record
    year: Smallint;
    month: Word;
    day: Word;
  end;
  TTime = record
    hour: Word;
    minute: Word;
    second: Word;
  end;
  TTimeStamp = record
    year: Smallint;
    month: Word;
    day: Word;
    hour: Word;
    minute: Word;
    second: Word;
    fraction: Cardinal;
  end;
(*  TTime2 = record
    hour: Word;
    minute: Word;
    second: Word;
    fraction: Cardinal;
  end;
  TTimeStampOffset = record
    year: Smallint;
    month: Word;
    day: Word;
    hour: Word;
    minute: Word;
    second: Word;
    fraction: Cardinal;
    timezone_hour: Smallint;
    timezone_minute: Smallint;
  end;


function Date(Year: SmallInt;
  Month: Word;
  Day: Word): TDate;
function Time(Hour: Word;
  Minute: Word;
  Second: Word): TTime;
function Time2(Hour: Word;
  Minute: Word;
  Second: Word;
  Fraction: Cardinal): TTime2;
function TimeStamp(Year: SmallInt;
  Month: Word;
  Day: Word;
  Hour: Word;
  Minute: Word;
  Second: Word;
  Fraction: Cardinal): TTimeStamp;
function TimeStampOffset(Year: SmallInt;
  Month: Word;
  Day: Word;
  Hour: Word;
  Minute: Word;
  Second: Word;
  Fraction: Cardinal;
  timezone_hour: SmallInt;
  timezone_minute: SmallInt): TTimeStampOffset;

procedure ZeroTimestamp(var VRec: TTimeStamp);
function TSEqual(TS1, TS2: TTimeStamp): Boolean;
function TSCompare(TS1, TS2: TTimeStamp): Integer;
*)
function TSToDateTime(TS: TTimeStamp): TDateTime;
function NullTS: TTimeStamp;
function DateTimeToTS(Value : TDateTime): TTimeStamp;
function DateAsTS(D: TDate): TTimeStamp;
function TimeAsTS(T: TTime): TTimeStamp;
(*
function TSOffsetToDateTimeOffset(TS: TTimeStampOffset): TDateTimeOffset;
function NullTSOffset: TTimeStampOffset;
function TSNull(TS: TTimeStamp): Boolean;
function TSAsDate(TS: TTimeStamp): TDate;
function TSAsTime(TS: TTimeStamp): TTime;
function TSAsTime2(TS: TTimeStamp): TTime2;
function Time2AsTS(T: TTime2): TTimeStamp;

*)
function ReadDate(const format, date: String; AllowBlankTimes: Boolean = False; allowNoDay: Boolean = False; allownodate: Boolean = False; NoFixYear : Boolean = false): TTimeStamp;
function DTReadDateTZ(format, date: String; AllowBlankTimes: Boolean = False): TDateTime;
(*
function DTReadDate(const format, date: String; AllowBlankTimes: Boolean = False): TDateTime;
function OpenReadDate(adate: String; AllowNoDay: Boolean = False): TTimeStamp;
function OpenReadTime(atime: String): TDateTime;
*)
procedure IncrementMonth(var d: TTimeStamp);
procedure IncrementYear(var d: TTimeStamp);
//procedure IncrementWeekDay(var d: TTimeStamp);
procedure IncrementQuarter(var d: TTimeStamp);
procedure IncrementWeek(var d: TTimeStamp);
(*
function DTIncrementMonth(var d: TDateTime): TDateTime;
*)
procedure IncrementDay(var d: TTimeStamp);
(*
function DTIncrementDay(var d: TDateTime): TDateTime;
procedure FindBlock(ch: Char; const s: String; var start, blength: Integer);
function WeekOfMonth(var d : TTimeStamp):Integer;

*)
function FormatTimeStamp(const Aformat: String; const ATTimeStamp: TTimeStamp): String;
(*
*)
var
  iPreCentWindow: Integer; //used in initialisation
(*
{------------------------------------------------------------------------------
   Date/Time Routines and constants                                                }
*)
const
  MINUTE_LENGTH = 1 / (24 * 60);
  SECOND_LENGTH = MINUTE_LENGTH / 60;
  MILLISECOND_LENGTH = MINUTE_LENGTH / (60 * 1000);
(*
  YEAR_LENGTH = 365;
  AVERAGE_YEAR_LENGTH = 365.25;
  AVERAGE_MONTH_LENGTH = 365.25 / 12;


{$IFNDEF LINUX}
function GetTimeZoneBias: TDateTime;
{$ENDIF}

*)
function ThisYear: Integer;
(*
*)
function GetMonthShortName(i: Integer): String;
function GetMonthLongName(i: Integer; Awid: Byte = 255): String;
(*
function GetDayName(i: Integer): String;
function ReadMonthName(s: String): Integer;
*)
function DescribePeriod(Period: TDateTime): String;
(*
function NowAsString: String;  // returns the current date and time as a string in form ccyymmddhhmmssmmm
function CheckDateIsValid(Ayear, Amonth, Aday: Word; var Verr: String): Boolean;
procedure EnforceDateIsValid(Ayear, Amonth, Aday: Word);
function CheckTTimeStampIsValid(const ATimeStamp: TTimeStamp; var Verr: String): Boolean;
procedure EnforceTTimeStampIsValid(const ATimeStamp: TTimeStamp);
function RecogniseDay(s: String): Integer;
function IsADayOfWeek(s: String): Boolean;
function ReadPeriod(AStr : String):TDateTime;

*)
implementation

uses
  Windows;

{--- various string utilities. Of course, these should be from other units, but there is distribution issues ---}
(*
function Substring(AStr: String; ABeginPos, AEndPos: Integer): String;
begin
  Result := Copy(AStr, ABeginPos, AEndPos - ABeginPos);
end;

function StripCharLeft(AStr: String; AChar: Char): String;
begin
  while (Length(AStr) > 0) and (AStr[1] = AChar) do
    Delete(AStr, 1, 1);
  Result := AStr;
end;

function StripChar(AStr: String; AChar: Char): String;
var
  i: Integer;
begin
  for i := Length(AStr) downto 1 do
    if AStr[i] = AChar then
      Delete(AStr, i, 1);
  Result := AStr;
end;

function isNumeric(ch: Char): Boolean;
begin
  Result := (ch >= '0') and (ch <= '9');
end;

function isLetter(ch: Char): Boolean;
begin
  Result := ((ch >= 'A') and (ch <= 'Z')) or ((ch >= 'a') and (ch <= 'z'));
end;

function isAlphaNumericChar(ch: Char): Boolean;
begin
  Result := (isNumeric(ch) or isLetter(ch));
end;

function isANumber(const AStr: String): Boolean;
var
  LVal: Integer;
  LErr: Integer;
begin
  if AStr = '' then
    begin
    Result := False
    end
  else
    begin
    Val(AStr, LVal, LErr);
    if LVal = -1 then
      begin
      // do nothing but remove hint
      end;
    Result := LErr = 0;
    end;
end;

function PadString(const AStr: String; AWidth: Integer; APadChar: Char; APadLeft: Boolean): String;
begin
  if Length(AStr) >= AWidth then
    Result := AStr
  else
    begin
    {$IFDEF CLR}
    Result := AStr;
    while length(AStr) < AWidth do
      begin
      if APadLeft then
        Result := APadChar + Result
      else
        Result := Result + APadChar;
      end;
    {$ELSE}
    SetLength(Result, AWidth);
    FillChar(Result[1], AWidth, APadChar);
    if AStr <> '' then
      if APadLeft then
        Move(AStr[1], Result[(AWidth - Length(AStr)) + 1], Length(AStr))
      else
        Move(AStr[1], Result[1], Length(AStr))
        {$ENDIF}
    end;
end;
*)
function ReplaceSubString(var AStr: String; const ASearchStr, AReplaceStr: String): Boolean;
var
  sStr : String;
begin
  sStr := StringReplace(aStr, ASearchStr, AReplaceStr, [rfReplaceAll, rfIgnoreCase]);
  result := aStr <> sStr;
  aStr := sStr;
end;

procedure ZeroTimestamp(var VRec: TTimeStamp);
begin
  VRec.year := 0;
  VRec.month := 0;
  VRec.day := 0;
  VRec.hour := 0;
  VRec.minute := 0;
  VRec.second := 0;
  VRec.fraction := 0;
end;


function CheckDateIsValid(Ayear, Amonth, Aday: Word; var Verr: String): Boolean;
  function DayOkForMonth: Boolean;
    begin
    Result := False;
    case Amonth of
      1, 3, 5, 7, 8, 10, 12:
        begin
        if Aday > 31 then
          begin
          Verr := 'Day ' + IntToStr(Aday) + ' too large for ' + GetMonthLongName(Amonth);
          exit;
          end;
        end;
      4, 6, 9, 11:
        begin
        if Aday > 30 then
          begin
          Verr := 'Day ' + IntToStr(Aday) + ' too large for ' + GetMonthLongName(Amonth);
          exit;
          end;
        end;
      2:
        begin
        if IsLeapYear(Ayear) then
          begin
          if Aday > 29 then
            begin
            Verr := 'Day ' + IntToStr(Aday) + ' too large for February';
            exit;
            end;
          end
        else
          begin
          if Aday = 29 then
            begin
            Verr := 'Day 29 too large for February in non-leap years';
            exit;
            end
          else if Aday > 28 then
            begin
            Verr := 'Day ' + IntToStr(Aday) + ' too large for February';
            exit;
            end;
          end;
        end;
      else
        begin
        Verr := 'Day ' + IntToStr(Aday) + ', Month ' + IntToStr(AMonth) + ', - Month is invalid';
        exit;
        end;
      end; //case
    Result := True;
    end;
begin
  Result := False;
  if (Ayear < 1000) or (Ayear > 3000) then
    begin
    Verr := 'Year ' + IntToStr(Ayear) + ' is invalid';
    exit;
    end;
  if (Amonth < 1) or (Amonth > 12) then
    begin
    Verr := 'Month ' + IntToStr(Amonth) + ' is invalid';
    exit;
    end;
  if Aday < 1 then
    begin
    Verr := 'Day ' + IntToStr(Aday) + ' is invalid';
    exit;
    end
  else if not DayOkForMonth then
    exit;
  Result := True;
end;

procedure EnforceDateIsValid(Ayear, Amonth, Aday: Word);
var
  LErr: String;
begin
  if not CheckDateIsValid(Ayear, Amonth, Aday, Lerr) then
    raise EKDateFormatError.Create(Lerr);
end;

function CheckTTimeStampIsValid(const ATimeStamp: TTimeStamp; var Verr: String): Boolean;
  function DumpTTimeStamp: String;
    begin
    Result := 'TTimeStamp <yr ' + IntToStr(ATimeStamp.year) + ', mo ' + IntToStr(ATimeStamp.month) + ', da ' + IntToStr(ATimeStamp.day) + ', hr ' +
      IntToStr(ATimeStamp.hour) + ', mi ' + IntToStr(ATimeStamp.minute) + ', se ' + IntToStr(ATimeStamp.second) + ', fr ' + IntToStr(ATimeStamp.fraction) + '>';
    end;
var
  LErr: String;
begin
  Verr := '';
  Result := False;
  if not CheckDateIsValid(ATimeStamp.year, ATimeStamp.month, ATimeStamp.day, LErr) then
    begin
    Verr := DumpTTimeStamp + ' is invalid: ' + LErr;
    exit;
    end;
  if ATimeStamp.hour > 23 then
    begin
    Verr := 'Hour in ' + DumpTTimeStamp + ' is invalid';
    exit;
    end;
  if ATimeStamp.minute > 59 then
    begin
    Verr := 'Minute in ' + DumpTTimeStamp + ' is invalid';
    exit;
    end;
  if ATimeStamp.second > 59 then
    begin
    Verr := 'Second in ' + DumpTTimeStamp + ' is invalid';
    exit;
    end;
  Result := True;
end;

procedure EnforceTTimeStampIsValid(const ATimeStamp: TTimeStamp);
var
  LErr: String;
begin
  if not CheckTTimeStampIsValid(ATimeStamp, Lerr) then
    raise EKDateFormatError.Create(Lerr);
end;


function FormatTimeStamp(const Aformat: String; const ATTimeStamp: TTimeStamp): String;
begin
  EnforceTTimeStampIsValid(ATTimeStamp);
  Result := Aformat;
  if not ReplaceSubString(Result, 'yyyy', StringPadRight(IntToStr(ATTimeStamp.year), '0', 4)) then
    replaceSubstring(Result, 'yy', copy(IntToStr(ATTimeStamp.year), 3, 2));
  if not ReplaceSubString(Result, 'mmmm', GetMonthLongName(ATTimeStamp.month, 4)) then
    if not ReplaceSubString(Result, 'mmm', GetMonthShortName(ATTimeStamp.month)) then
      if not ReplaceSubString(Result, 'mm', StringPadLeft(IntToStr(ATTimeStamp.month), '0', 2)) then
        ReplaceSubString(Result, 'm', IntToStr(ATTimeStamp.month));
  if not ReplaceSubString(Result, 'dd', StringPadLeft(IntToStr(ATTimeStamp.day), '0', 2)) then
    ReplaceSubString(Result, 'd', IntToStr(ATTimeStamp.day));
  ReplaceSubString(Result, 'hh', StringPadLeft(IntToStr(ATTimeStamp.hour), '0', 2));
  ReplaceSubString(Result, 'nn', StringPadLeft(IntToStr(ATTimeStamp.minute), '0', 2));
  ReplaceSubString(Result, 'ss', StringPadLeft(IntToStr(ATTimeStamp.second), '0', 2));
end;

procedure FindBlock(ch: Char; const s: String; var start, blength: Integer);
begin
  start := pos(ch, s);
  if start = 0 then
    blength := 0
  else
    begin
    blength := 1;
    while (start + blength <= length(s)) and (s[start + blength] = ch) do
      inc(blength);
    end;
end;


function UserStrToInt(st, Info: String): Integer; { raise an EHL7UserException }
var
  E: Integer;
begin
  Val(St, Result, E);
  if E <> 0 then
    raise EKDateFormatError.CreateFmt(SInvalidInteger + ' reading ' + Info, [St]);
end;

function ReadDate(const format, date: String; AllowBlankTimes: Boolean = False; allowNoDay: Boolean = False; allowNoDate: Boolean = False; NoFixYear : Boolean = false): TTimeStamp;
var
  start, length: Integer;
  s: String;
  tmp: String;
begin
  Result.year := 0;
  Result.month := 0;
  Result.day := 0;
  Result.hour := 0;
  Result.minute := 0;
  Result.second := 0;
  Result.fraction := 0;
  FindBlock('y', Format, start, length);
  tmp := copy(date, start, length);
  if lowercase(tmp) = 'nown' then
    exit;
  if (tmp = '') and AllowNoDate then
    // we don't bother with the year
  else
    begin
    Result.year := UserStrToInt(tmp, 'Year from "' + date + '"');
    if not NoFixYear then
    begin
      if Result.year < 100 then
        if abs(Result.year) > {$IFNDEF VER130}FormatSettings.{$ENDIF}TwoDigitYearCenturyWindow then      //abs as result.year is a smallint, twodigityearcenturywindow is a word (range checking)
          inc(Result.year, 1900)
        else
          inc(Result.year, 2000);
    end;
    end;
  FindBlock('m', Format, start, length);
  s := lowercase(copy(date, start, length));
  if AllowNoDate and (tmp = '') then
    // we don't worry about the month
  else
    begin
    if length > 2 then
      begin
      if (s = 'jan') or (s = 'january') then
        Result.month := 1
      else if (s = 'feb') or (s = 'february') then
        Result.month := 2
      else if (s = 'mar') or (s = 'march') then
        Result.month := 3
      else if (s = 'apr') or (s = 'april') then
        Result.month := 4
      else if (s = 'may') then
        Result.month := 5
      else if (s = 'jun') or (s = 'june') then
        Result.month := 6
      else if (s = 'jul') or (s = 'july') then
        Result.month := 7
      else if (s = 'aug') or (s = 'august') then
        Result.month := 8
      else if (s = 'sep') or (s = 'september') then
        Result.month := 9
      else if (s = 'oct') or (s = 'october') then
        Result.month := 10
      else if (s = 'nov') or (s = 'november') then
        Result.month := 11
      else if (s = 'dec') or (s = 'december') then
        Result.month := 12
      else
        raise EKDateFormatError.Create('The Month "' + s + '" is unknown');
      end
    else if s = '' then
      Result.Month := 1
    else
      Result.month := UserStrToInt(s, 'Month from "' + date + '"');
    if (Result.month > 12) or (Result.month < 1) then
      raise EKDateFormatError.Create('invalid month ' + IntToStr(Result.month));
    end;
  FindBlock('d', Format, start, length);
  tmp := copy(date, start, length);
  if (AllowNoday or AllowNoDate) and (tmp = '') then
    // we don't check the day
  else
    begin
    Result.day := UserStrToInt(tmp, 'Day from "' + date + '"');
    EnforceDateIsValid(Result.year, Result.month, Result.day);
    end;
  FindBlock('h', Format, start, length);
  if length <> 0 then
    if AllowBlankTimes then
      Result.hour := StrToIntDef(copy(date, start, length), 0)
    else
      Result.hour := UserStrToInt(copy(date, start, length), 'Hour from "' + date + '"');
  FindBlock('s', Format, start, length);
  if length <> 0 then
    if AllowBlankTimes then
      Result.second := StrToIntDef(copy(date, start, length), 0)
    else
      Result.second := UserStrToInt(copy(date, start, length), 'Second from "' + date + '"');
  FindBlock('n', Format, start, length);
  if length <> 0 then
    if AllowBlankTimes then
      Result.minute := StrToIntDef(copy(date, start, length), 0)
    else
      Result.minute := UserStrToInt(copy(date, start, length), 'Minute from "' + date + '"');
  FindBlock('z', Format, start, length);
  if length <> 0 then
    if AllowBlankTimes then
      Result.fraction := StrToIntDef(copy(date, start, length), 0);
  FindBlock('x', Format, start, length);
  if length <> 0 then
    if uppercase(copy(date, start, length)) = 'AM' then
      begin
      if Result.hour = 12 then
        Result.hour := 0
      end
    else
      begin
      inc(Result.hour, 12);
      end;

  if Result.hour = 24 then
    begin
    Result.hour := 0;
    incrementDay(Result);
    end;
end;
(*

function DTReadDate(const format, date: String; AllowBlankTimes: Boolean = False): TDateTime;
begin
  Result := TSToDateTime(ReadDate(format, date, allowblankTimes));
end;

*)
function DTReadDateTZ(format, date: String; AllowBlankTimes: Boolean = False): TDateTime;
var
  s : String;
  adjust : TDateTime;
begin
  if date = '' then
    result := 0
  else
  begin
    adjust := 0;
    if (date <> '') then
    begin
      if date[length(date)] = 'Z' then
        date := copy(date, 1, length(date)-1)
      else if pos('+', date) > 0 then
      begin
        s := copy(date, pos('+', date)+1, $FF);
        adjust := - EncodeTime(strtoint(copy(s, 1, 2)), strtoint(copy(s, 4, 2)), 0, 0);
        date := copy(date, 1, LastDelimiter('+', date)-1);
      end
      else if LastDelimiter('-', date) > 10 then
      begin
        s := copy(date, LastDelimiter('-', date)+1, $FF);
        adjust := + EncodeTime(strtoint(copy(s, 1, 2)), strtoint(copy(s, 4, 2)), 0, 0);
        date := copy(date, 1, LastDelimiter('-', date)-1);
      end
    end;
    Result := TSToDateTime(ReadDate(format, date, allowblankTimes)) + adjust;
  end;
end;

(*
function RecogniseMonth(s: String): Integer;
begin
  if isANumber(s) then
    begin
    Result := StrToInt(s);
    if (Result < 1) or (Result > 12) then
      raise EKDateFormatError.Create(s + ' is not a valid month');
    end
  else
    begin
    s := lowercase(s);
    if s = 'jan' then
      Result := 1 
    else if s = 'january' then 
      Result := 1 
    else if s = 'feb' then 
      Result := 2 
    else if s = 'february' then 
      Result := 2 
    else if s = 'mar' then 
      Result := 3
    else if s = 'march' then 
      Result := 3 
    else if s = 'apr' then 
      Result := 4
    else if s = 'april' then
      Result := 4 
    else if s = 'may' then 
      Result := 5 
    else if s = 'jun' then 
      Result := 6 
    else if s = 'june' then 
      Result := 6 
    else if s = 'jul' then 
      Result := 7 
    else if s = 'july' then 
      Result := 7 
    else if s = 'aug' then 
      Result := 8 
    else if s = 'august' then 
      Result := 8 
    else if s = 'sep' then 
      Result := 9 
    else if s = 'sept' then 
      Result := 9 
    else if s = 'september' then 
      Result := 9
    else if s = 'oct' then 
      Result := 10 
    else if s = 'october' then 
      Result := 10 
    else if s = 'nov' then
      Result := 11 
    else if s = 'november' then
      Result := 11 
    else if s = 'dec' then 
      Result := 12
    else if s = 'december' then 
      Result := 12 
    else
      raise EKDateFormatError.Create(s + ' is not a valid month');
    end;
end;

function ReadDay(s: String): Integer;
begin
  s := lowercase(s);
  if s = 'sun' then
    Result := 1
  else if s = 'sunday' then
    Result := 1
  else if s = 'mon' then
    Result := 2
  else if s = 'monday' then
    Result := 2
  else if s = 'tues' then
    Result := 3
  else if s = 'tuesday' then
    Result := 3
  else if s = 'wed' then
    Result := 4
  else if s = 'wednesday' then
    Result := 4
  else if s = 'thurs' then
    Result := 5
  else if s = 'thursday' then
    Result := 5
  else if s = 'fri' then
    Result := 6
  else if s = 'friday' then
    Result := 6
  else if s = 'sat' then
    Result := 7
  else if s = 'saturday' then
    Result := 7
  else
    result := -1;
end;
function RecogniseDay(s: String): Integer;
begin
  result := ReadDay(s);
  if result = -1 then
    raise EKDateFormatError.Create(s + ' is not a valid day of the week');
end;

function IsADayOfWeek(s: String): Boolean;
begin
  result := ReadDay(s) <> -1;
end;

*)
function ThisYear: Integer;
var
  y, m, d: Word;
begin
  DecodeDate(now, y, m, d);
  Result := y;
end;
(*

function ThisCentury: Integer;
begin
  Result := (ThisYear div 100) * 100;
end;

function ReadTokens(const d: String; tokens: TStringList): String;
var
  i, c: Integer;
  s: String;
begin
  i := 1;
  c := 0;
  Result := '';
  tokens.add('');
  while (i <= length(d)) and (d[i] = ' ') do 
    inc(i);
  while i <= length(d) do
    begin
    if isAlphaNumericChar(d[i]) then
      begin
      tokens[c] := tokens[c] + d[i];
      inc(i);
      end
    else
      begin
      inc(c);
      inc(i);
      tokens.add('');
      while (i <= length(d)) and (d[i] = ' ') do 
        inc(i);
      end;
    end;
  for i := tokens.Count - 1 downto 0 do
    if (tokens[i] = '') or (lowercase(tokens[i]) = 'rd') or (lowercase(tokens[i]) = 'th') or (lowercase(tokens[i]) = 'st') or (lowercase(tokens[i]) = 'nd') then
      tokens.Delete(i)
  else if (IsADayOfWeek(tokens[i])) then
    begin
    Result := tokens[i];
    tokens.Delete(i);
    end
  else
    begin
    s := lowercase(copy(tokens[i], length(tokens[i]) - 1, 2));
    if (s = 'rd') or (s = 'th') or (s = 'st') or (s = 'nd') then
      tokens[i] := copy(tokens[i], 1, length(tokens[i]) - 2);
    end;
end;

function ReadYear(tokens: TStringList; date: String): Integer;
var
  year: String;
  t: Integer;
begin
  if tokens.Count = 2 then
    Result := ThisYear
  else
    begin
    year := tokens[2];
    if not isANumber(year) then
      raise EKDateFormatError.Create('The year ' + tokens[2] + ' is not a valid number');
    t := UserStrToInt(year, 'Year from "' + date + '"');
    if length(year) = 2 then
      begin
      if t > {$IFNDEF VER130}FormatSettings.{$ENDIF}TwoDigitYearCenturyWindow then
        Result := t + ThisCentury - 100
      else
        Result := t + ThisCentury;
      end
    else
      Result := t;
    end;
end;

procedure ReadMonthDay(tokens: TStringList; date: String; var ts: TTimeStamp);
var
  t1, t2: Integer;
  month, day: String;
begin
  if isANumber(tokens[0]) and isANumber(tokens[1]) then
    begin
    //ok have a problem - which is the month?
    t1 := UserStrToInt(tokens[0], 'Month/day from "' + date + '"');
    t2 := UserStrToInt(tokens[1], 'Month/day from "' + date + '"');
    if t1 < 1 then
      raise EKDateFormatError.Create('invalid number ' + tokens[0]);
    if t2 < 1 then
      raise EKDateFormatError.Create('invalid number ' + tokens[1]);
    if t1 > 12 then
      begin
      ts.day := t1;
      ts.month := t2;
      end
    else if t2 > 12 then
      begin
      ts.day := t2;
      ts.month := t1;
      end
    else
      begin
      { // causing all sorts of problems in NT services
      if pos('d', LowerCase(ShortDateFormat)) > pos('m', LowerCase(ShortDateFormat)) then
        begin
        ts.day := t2;
        ts.month := t1;
        end
      else
        begin
        }
      ts.day := t1;
      ts.month := t2;

        {
        end
        }
      end;
    end
  else if not isANumber(tokens[0]) and not isANumber(tokens[1]) then
    raise EKDateFormatError.Create('Couldn''t find a valid number for the day of month')
  else
    begin
    if not isANumber(tokens[0]) then
      begin
      month := tokens[0];
      day := tokens[1];
      end
    else
      begin
      month := tokens[1];
      day := tokens[0];
      end;
    ts.day := UserStrToInt(day, 'Day from "' + date + '"');
    ts.month := RecogniseMonth(month);
    end;
end;

function CanRecogniseMonth(s: String): Boolean;
begin
  try
    RecogniseMonth(s);
    Result := True;
  except
    Result := False;
    end;
end;

function StrToIntWithError(s, n: String): Integer;
begin
  try
    Result := StrToInt(s);
  except
    raise EKDateFormatError.Create(n + ' [' + s + '] is not a valid integer');
    end;
end;

function OpenReadDate(adate: String; AllowNoDay: Boolean = False): TTimeStamp;
var
  tokens: TStringList;
  KnownDayOfWeek: String;
  AddedDay: Boolean;
begin
  AddedDay := False;
  try
    if SameText(adate, 't') then
      begin
      Result := DateTimeToTS(trunc(now));
      exit;
      end;
    if (SameText(adate, 'p')) or (SameText(adate, 'y')) then
      begin
      Result := DateTimeToTS(trunc(now) - 1);
      exit;
      end;
    if ((adate[1] = '+') or (adate[1] = '-')) and IsANumber(copy(adate, 2, length(aDate) - 1)) then
      begin
      if (adate[1] = '+') then
        Result := DateTimeToTS(trunc(now) + StrToIntWithError(copy(adate, 2, length(adate) - 1), 'number'))
      else
        Result := DateTimeToTS(trunc(now) - StrToIntWithError(copy(adate, 2, length(adate) - 1), 'number'))
      end;
    ReplaceSubString(adate, '+', ' ');
    ZeroTimestamp(Result);
    tokens := TStringList.Create;
    try
      KnownDayOfWeek := ReadTokens(adate, tokens);
      if (tokens.Count > 3) or (tokens.Count < 2) then
        raise EKDateFormatError.Create('Basic error trying to understand date - ' + IntToStr(tokens.Count) + ' sections found');
      if AllowNoDay and CanRecogniseMonth(tokens[0]) and (tokens.Count = 2) then
        begin
        tokens.insert(0, '1');
        AddedDay := True;
        end;
      Result.Year := ReadYear(tokens, adate);
      ReadMonthDay(tokens, adate, Result);
      EnforceDateIsValid(Result.year, Result.month, Result.Day);
      if KnownDayOfWeek <> '' then
        begin
        if DayOfWeek(TSToDateTime(Result)) <> RecogniseDay(KnownDayOfWeek) then
          raise EKDateFormatError.Create('The Day of week given did not match the date given (' + GetDayName(DayOfWeek(TSToDateTime(Result))) + ')');
        end;
      if AddedDay then
        Result.Day := 0;
    finally
      tokens.Free;
      end;
  except
    on e:
    EKDateFormatError do
      begin
      raise EKDateFormatError.Create('Date "' + adate + '" does not appear to be a valid date: ' + e.message);
      end;
    on e:
    Exception do
      raise EKDateFormatError.Create('Date "' + adate + '" does not appear to be a valid date: ' + e.message);
    end;
end;

function OpenReadTime(atime: String): TDateTime;
  function bit(s, f: Integer; desc: String): Integer;
    begin
    try
      Result := UserStrToInt(substring(atime, s, f + 1), desc + ' from "' + atime + '"');
    except
      on e:
      Exception do
        begin
        raise EKDateFormatError.Create('Time ' + atime + ' does not appear to be a valid time');
        raise;
        end;
      end;
    end;
var
  h, n, s: Integer;
  IsPM: Boolean;
begin
  n := 0;
  s := 0;
  atime := Uppercase(stripchar(atime, ' '));
  IsPM := False;
  if (pos('AM', atime) = length(atime) - 1) or (pos('PM', atime) = length(atime) - 1) then
    begin
    IsPM := pos('PM', atime) = length(atime) - 1;
    atime := copy(atime, 1, length(atime) - 2);
    end;
  case length(atime) of
    1:
      h := bit(1, 1, 'hour');
    2:
      h := bit(1, 2, 'hour');
    3:
      begin
      h := bit(1, 1, 'hour');
      n := bit(2, 3, 'minute');
      end;
    4:
      begin
      if atime[2] = ':' then
        begin
        h := bit(1, 1, 'hour');
        n := bit(3, 4, 'minute');
        end
      else
        begin
        h := bit(1, 2, 'hour');
        n := bit(3, 4, 'minute');
        end;
      end;
    5:
      if atime[3] = ':' then
        begin
        h := bit(1, 2, 'hour');
        n := bit(4, 5, 'minute');
        end
      else
        begin
        h := bit(1, 1, 'hour');
        n := bit(2, 3, 'minute');
        s := bit(4, 5, 'second');
        end;
    6:
      begin
      h := bit(1, 2, 'hour');
      n := bit(3, 4, 'minute');
      s := bit(5, 6, 'second');
      end;
    7:
      begin
      h := bit(1, 1, 'hour');
      n := bit(3, 4, 'minute');
      s := bit(6, 7, 'second');
      end;
    8:
      begin
      h := bit(1, 2, 'hour');
      n := bit(4, 5, 'minute');
      s := bit(7, 8, 'second');
      end;
    else 
      raise EKDateFormatError.Create('time ' + atime + ' does not appear to be a valid time');
    end;
  if isPM then
    begin
    if h = 12 then
      h := 0
    else if h > 12 then
      raise Exception.Create('invalid date format with "PM" - hour is greater than 12')
    else
      h := h + 12;
    end;
  while s >= 60 do
    begin
    inc(n);
    dec(s, 60);
    end;
  while n >= 60 do
    begin
    inc(h);
    dec(n, 60);
    end;
  if h > 23 then
    h := 23;
  Result := EncodeTime(h, n, s, 0);
end;

*)
procedure IncrementMonth(var d: TTimeStamp);
begin
  if d.month = 12 then
    begin
    inc(d.year);
    d.month := 1
    end
  else
    inc(d.month);
  if d.day > MONTHS_DAYS[IsLeapYear(d.Year), TMonthOfYear(d.month - 1)] then
    d.Day := MONTHS_DAYS[IsLeapYear(d.Year), TMonthOfYear(d.month - 1)];
end;

procedure IncrementWeek(var d: TTimeStamp);
var
  i: Integer;
begin
  for i := 1 to 7 do
    IncrementDay(d);
end;

(*
procedure IncrementWeekDay(var d: TTimeStamp);
begin
  IncrementDay(d);
  while (dayofweek(TStoDateTime(d)) = 1) or (dayofweek(TStoDateTime(d)) = 7) do
    IncrementDay(d);
end;
*)

procedure IncrementQuarter(var d: TTimeStamp);
var
  i: Integer;
begin
  for i := 1 to 3 do
    Incrementmonth(d);
end;

procedure IncrementYear(var d: TTimeStamp);
begin
  inc(d.year);
end;
(*

function DTIncrementMonth(var d: TDateTime): TDateTime;
var 
  dtimestamp: TTimeStamp;
begin
  dtimestamp := DateTimetoTS(d);
  IncrementMonth(dtimestamp);
  Result := TSToDateTime(dtimestamp);
end;

*)
procedure IncrementDay(var d: TTimeStamp);
begin
  inc(d.day);
  case d.month of
    1:
      if d.day = 32 then
        begin
        d.month := 2;
        d.day := 1;
        end;
    2:
      if (IsLeapYear(d.year) and (d.day = 30)) or (not IsLeapYear(d.year) and (d.day = 29)) then
        begin
        d.month := 3;
        d.day := 1;
        end;
    3:
      if d.day = 32 then
        begin
        d.month := 4;
        d.day := 1;
        end;
    4:
      if d.day = 31 then
        begin
        d.month := 5;
        d.day := 1;
        end;
    5:
      if d.day = 32 then
        begin
        d.month := 6;
        d.day := 1;
        end;
    6:
      if d.day = 31 then
        begin
        d.month := 7;
        d.day := 1;
        end;
    7:
      if d.day = 32 then
        begin
        d.month := 8;
        d.day := 1;
        end;
    8:
      if d.day = 32 then
        begin
        d.month := 9;
        d.day := 1;
        end;
    9:
      if d.day = 31 then
        begin
        d.month := 10;
        d.day := 1;
        end;
    10:
      if d.day = 32 then
        begin
        d.month := 11;
        d.day := 1;
        end;
    11:
      if d.day = 31 then
        begin
        d.month := 12;
        d.day := 1;
        end;
    12:
      if d.day = 32 then
        begin
        inc(d.year);
        d.month := 1;
        d.day := 1;
        end;
    else
      raise Exception.Create('unknown month in NextDay');
    end;
end;
(*
function DTIncrementDay(var d: TDateTime): TDateTime;
var
  dtimestamp: TTimeStamp;
begin
  dtimestamp := DateTimetoTS(d);
  IncrementDay(dtimestamp);
  Result := TSToDateTime(dtimestamp);
end;

{$IFNDEF LINUX}

function GetTimeZoneBias: TDateTime;
var
  lpTimeZoneInformation: TTimeZoneInformation;
  DaylightBias: Boolean;
  bias: Integer;
begin
  DaylightBias := GetTimeZoneInformation(lpTimeZoneInformation) = TIME_ZONE_ID_DAYLIGHT;
  bias := lpTimeZoneInformation.bias;
  if DaylightBias then
    bias := bias + lpTimeZoneInformation.daylightbias
  else
    bias := bias + lpTimeZoneInformation.standardbias;
  Result := (bias / 1440);
end;
{$ENDIF}

*)
function GetMonthShortName(i: Integer): String;
begin
  case i of
    1:
      Result := 'Jan';
    2:
      Result := 'Feb';
    3:
      Result := 'Mar';
    4:
      Result := 'Apr';
    5:
      Result := 'May';
    6:
      Result := 'Jun';
    7:
      Result := 'Jul';
    8:
      Result := 'Aug';
    9:
      Result := 'Sep';
    10:
      Result := 'Oct';
    11:
      Result := 'Nov';
    12:
      Result := 'Dec';
    else 
      Result := StringPadLeft(IntToStr(i), '0', 3);
    end;
end;

function GetMonthLongName(i: Integer; Awid: Byte = 255): String;
begin
  case i of
    1:
      Result := 'January';
    2:
      Result := 'February';
    3:
      Result := 'March';
    4:
      Result := 'April';
    5:
      Result := 'May';
    6:
      Result := 'June';
    7:
      Result := 'July';
    8:
      Result := 'August';
    9:
      Result := 'September';
    10:
      Result := 'October';
    11:
      Result := 'November';
    12:
      Result := 'December';
    else 
      begin
      Result := IntToStr(i);
      if Awid <> 255 then
        if length(Result) < Awid then
          Result := StringPadRight(Result, ' ', AWid);
      end;
    end;
end;

        (*
function GetDayName(i: Integer): String;
begin
  case i of
    1:
      Result := 'Sunday';
    2:
      Result := 'Monday';
    3:
      Result := 'Tuesday';
    4:
      Result := 'Wednesday';
    5:
      Result := 'Thursday';
    6:
      Result := 'Friday';
    7:
      Result := 'Saturday';
    else 
      raise Exception.Create(IntToStr(i) + ' is not a valid day of the week');
    end;
end;

function ReadMonthName(s: String): Integer;
begin
  s := lowercase(s);
  if s = 'jan' then
    Result := 1 
  else if s = 'feb' then
    Result := 2 
  else if s = 'mar' then
    Result := 3 
  else if s = 'apr' then 
    Result := 4 
  else if s = 'may' then 
    Result := 5 
  else if s = 'jun' then 
    Result := 6 
  else if s = 'jul' then 
    Result := 7 
  else if s = 'aug' then 
    Result := 8 
  else if s = 'sep' then 
    Result := 9 
  else if s = 'oct' then
    Result := 10 
  else if s = 'nov' then 
    Result := 11 
  else if s = 'dec' then 
    Result := 12 
  else if s = 'january' then   
    Result := 1 
  else if s = 'february' then  
    Result := 2 
  else if s = 'march' then     
    Result := 3 
  else if s = 'april' then     
    Result := 4 
  else if s = 'june' then      
    Result := 6 
  else if s = 'july' then      
    Result := 7 
  else if s = 'august' then    
    Result := 8 
  else if s = 'september' then 
    Result := 9
  else if s = 'october' then
    Result := 10 
  else if s = 'november' then  
    Result := 11 
  else if s = 'december' then
    Result := 12 
  else
    Result := -1;
end;
*)
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
(*
{$IFNDEF LINUX}

function NowAsString: String;
var
  SystemTime: TSystemTime;
begin
  GetLocalTime(SystemTime);
  with SystemTime do
    Result := StringPadRight(IntToStr(wYear), '0', 4) +
      StringPadLeft(IntToStr(wMonth), '0', 2) +
      StringPadLeft(IntToStr(wDay), '0', 2) +
      StringPadLeft(IntToStr(wHour), '0', 2) +
      StringPadLeft(IntToStr(wMinute), '0', 2) +
      StringPadLeft(IntToStr(wSecond), '0', 2) +
      StringPadLeft(IntToStr(wMilliseconds), '0', 3);
end;
{$ELSE}

function NowAsString: String;
begin
  Result := FormatDateTime('yyyymmddhhnnsszzz', now);
end;
{$ENDIF}


function Date(Year: SmallInt;
  Month: Word;
  Day: Word): TDate;
begin
  Result.Year := Year;
  Result.Month := Month;
  Result.Day := Day;
end;

function Time(Hour: Word;
  Minute: Word;
  Second: Word): TTime;
begin
  Result.Hour := Hour;
  Result.Minute := Minute;
  Result.Second := Second;
end;

function Time2(Hour: Word;
  Minute: Word;
  Second: Word;
  Fraction: Cardinal): TTime2;
begin
  Result.Hour := Hour;
  Result.Minute := Minute;
  Result.Second := Second;
  Result.Fraction := Fraction;
end;

function TimeStamp(Year: SmallInt;
  Month: Word;
  Day: Word;
  Hour: Word;
  Minute: Word;
  Second: Word;
  Fraction: Cardinal): TTimeStamp;
begin
  Result.Year := Year;
  Result.Month := Month;
  Result.Day := Day;
  Result.Hour := Hour;
  Result.Minute := Minute;
  Result.Second := Second;
  Result.Fraction := Fraction;
end;

function TimeStampOffset(Year: SmallInt;
  Month: Word;
  Day: Word;
  Hour: Word;
  Minute: Word;
  Second: Word;
  Fraction: Cardinal;
  timezone_hour: SmallInt;
  timezone_minute: SmallInt): TTimeStampOffset;
begin
  Result.Year := Year;
  Result.Month := Month;
  Result.Day := Day;
  Result.Hour := Hour;
  Result.Minute := Minute;
  Result.Second := Second;
  Result.Fraction := Fraction;
  Result.timezone_hour := timezone_hour;
  Result.timezone_minute := timezone_minute;
end;

function TSEqual(TS1, TS2: TTimeStamp): Boolean;
begin  
  Result := (TS1.Year = TS2.Year) and (TS1.Month = TS2.Month) and (TS1.Day = TS2.Day) and (TS1.Hour = TS2.Hour) and (TS1.Minute = TS2.Minute) and (TS1.Second = TS2.Second) and (TS1.Fraction = TS2.Fraction);
end;

function TSSmaller(TS1, TS2: TTimeStamp): Boolean;
begin  
  if TS1.Year <> TS2.Year then    
    Result := TS1.Year < TS2.Year  
  else if TS1.Month <> TS2.Month then    
    Result := TS1.Month < TS2.Month  
  else if TS1.Day <> TS2.Day then    
    Result := TS1.Day < TS2.Day
  else if TS1.Hour <> TS2.Hour then    
    Result := TS1.Hour < TS2.Hour  
  else if TS1.Minute <> TS2.Minute then    
    Result := TS1.Minute < TS2.Minute
  else if TS1.Second <> TS2.Second then    
    Result := TS1.Second < TS2.Second  
  else if TS1.Fraction <> TS2.Fraction then    
    Result := TS1.Fraction < TS2.Fraction  
  else    
    Result := False;
end;

function TSCompare(TS1, TS2: TTimeStamp): Integer;
begin  
  if TSEqual(TS1, TS2) then    
    Result := 0  
  else if TSSmaller(TS1, TS2) then    
    Result := -1
  else    
    Result := 1;
end;
*)

function TSToDateTime(TS: TTimeStamp): TDateTime;
begin
  with TS do
    Result := EncodeDate(Year, Month, Day) + EncodeTime(Hour, Minute, Second, Fraction div 1000000);
end;
(*
function TSOffsetToDateTimeOffset(TS: TTimeStampOffset): TDateTimeOffset;
Begin
  with TS do
  begin
    Result.Value := EncodeDate(Year, Month, Day) + EncodeTime(Hour, Minute, Second, Fraction div 1000000);
    Result.Offset := TS.timezone_hour * 60 + TS.timezone_minute;
  end;
End;
*)

function DateTimeToTS(Value : TDateTime): TTimeStamp;
var
  DTYear, DTMonth, DTDay, DTHour, DTMinute, DTSecond, DTFraction: Word;
begin
  DecodeDate(Value, DTYear, DTMonth, DTDay);
  DecodeTime(Value, DTHour, DTMinute, DTSecond, DTFraction);
  with Result do
    begin
    Year := DTYear;
    Month := DTMonth;
    Day := DTDay;
    Hour := DTHour;
    Minute := DTMinute;
    Second := DTSecond;
    Fraction := DTFraction * 1000000;
    end;
end;

function NullTS: TTimeStamp;
begin
  Result := DateTimeToTS(0);
end;
(*
function NullTSOffset: TTimeStampOffset;
Begin
  FillChar(Result, SizeOf(TTimestampOffset), 0);
  Result.year := 1899;
  Result.month := 12;
  Result.day := 30;
End;


function TSNull(TS: TTimeStamp): Boolean;
begin  
  Result := TSEqual(TS, NullTS);
end;

function TSAsDate(TS: TTimeStamp): TDate;
begin
  with Result do 
    begin    
    Year := TS.Year;
    Month := TS.Month;
    Day := TS.Day;
    end;
end;

*)
function DateAsTS(D: TDate): TTimeStamp;
begin
  Result := NullTS;
  with Result do
    begin
    Year := D.Year;
    Month := D.Month;
    Day := D.Day;
    end;
end;

function TSAsTime(TS: TTimeStamp): TTime;
begin
  with Result do
    begin
    Hour := TS.Hour;
    Minute := TS.Minute;
    Second := TS.Second;
    end;
end;

function TimeAsTS(T: TTime): TTimeStamp;
begin
  Result := NullTS;
  with Result do
    begin
    Hour := T.Hour;
    Minute := T.Minute;
    Second := T.Second;
    end;
end;
(*

function TSAsTime2(TS: TTimeStamp): TTime2;
begin
  with Result do
    begin
    Hour := TS.Hour;
    Minute := TS.Minute;
    Second := TS.Second;
    fraction := TS.fraction;
    end;
end;

function Time2AsTS(T: TTime2): TTimeStamp;
begin
  Result := NullTS;
  with Result do
    begin
    Hour := T.Hour;
    Minute := T.Minute;
    Second := T.Second;
    fraction := T.fraction;
    end;
end;

Function IsFloat(Const AValue : String) : Boolean;
Var
  LDummy : Extended;
Begin
  Result := TextToFloat(PChar(AValue), LDummy, fvExtended);
End;

Function toFloat(Const AValue : String) : Double;
Var
  LDummy : Extended;
Begin
  TextToFloat(PChar(AValue), LDummy, fvExtended);
  result := LDummy;
End;

function ReadPeriod(AStr : String):TDateTime;
var
  i : integer;
  s : String;
begin
  if AStr = '' then
    result := 0
  else
    begin
    i := 1;
{$IFDEF VER130}
    while (i <= Length(AStr)) and (AStr[i] in ['0'..'9', '.']) do inc(i);
{$ELSE}
    while (i <= Length(AStr)) and CharInSet(AStr[i], ['0'..'9', '.']) do inc(i);
{$ENDIF}
    if i = 1 then
      raise EKDateFormatError.create('No Numeric portion found in Time Period "'+AStr+'"')
    else if (i = 2) and (AStr[1] = '0') then
      result := 0 // no error '0' alone is fine - no units required
    else if not IsFloat(SubString(AStr, 1, i)) then
      raise EKDateFormatError.create('"'+SubString(AStr, 1, i)+'" is not valid number in Time Period "'+AStr+'"')
    else
      begin
      s := StripCharLeft(copy(AStr, i, $FF), ' ');
      if SameText(s, 's') or SameText(s, 'sec') then
        result := SECOND_LENGTH
      else if SameText(s, 'n') or SameText(s, 'min') then
        result := MINUTE_LENGTH
      else if SameText(s, 'h') or SameText(s, 'hr') or SameText(s, 'hour') or SameText(s, '/24') then
        result := 60 * MINUTE_LENGTH
      else if SameText(s, 'd') or SameText(s, 'day') or SameText(s, '/7') or SameText(s, '/5') then
        result := 1
      else if SameText(s, 'month') or SameText(s, '/12') then
        result := YEAR_LENGTH/12
      else if SameText(s, 'y') or SameText(s, 'year') or SameText(s, 'yr') then
        result := YEAR_LENGTH
      else
        raise EKDateFormatError.create('"'+StripCharLeft(copy(AStr, i, $FF), ' ')+'" is not a valid time unit in Time Period "'+AStr+'"');
      result := result * toFloat(SubString(AStr, 1, i));
      end;
    end;
end;

function WeekOfMonth(var d : TTimeStamp):Integer;
var
i : Integer;
begin
  i := 1;
  while (d.day > 7) do
    begin
    d.day := d.day - 7;  // don't do any change of month checking
    i := i + 1;
    end;
  while (not (dayofweek(TStoDateTime(d))=7)) do
    begin
    incrementday(d)
    end;
  if (d.day > 7) then
    begin
    i := i + 1;
    end;
  Result := i;
end;
*)

initialization
  iPreCentWindow := ((ThisYear mod 100)) - 70;
  if iPreCentWindow < 0 then
    iPreCentWindow := iPreCentWindow + 100;
  {$IFNDEF VER130}FormatSettings.{$ENDIF}TwoDigitYearCenturyWindow := iPreCentWindow;
end.
