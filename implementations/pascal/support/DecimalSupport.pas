Unit DecimalSupport;

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
  Windows,
  ActiveX,
  GuidSupport,
  StringSupport,
  MathSupport,
  SysUtils,
  AdvFactories,
  AdvObjectLists,
  AdvPersistents;

Const
  INTEGER_PRECISION = 24;

Type
  TSmartDecimal = class;
  {@class TSmartDecimalContext
    manages the object lifecycle for a set of calculations

  }
  TSmartDecimalContext = class (TAdvObjectList)
  private
  public
    Procedure BeforeDestruction; Override;

    function Link : TSmartDecimalContext; overload;

    Function Value(value : String) : TSmartDecimal; Overload;
    {!script hide}
    Function Value(value : Integer) : TSmartDecimal; Overload;
    Function Value(value : int64) : TSmartDecimal; Overload;
    Function Value(value : TSmartDecimal) : TSmartDecimal; Overload; // move into a different context
    {!script show}

    Function Equal(oOne, oTwo : TSmartDecimal) : Boolean; overload;
    Function Compares(oOne, oTwo : TSmartDecimal) : Integer; overload;
    Function Zero : TSmartDecimal;
    Function One : TSmartDecimal;
    Function FromActiveX(oX: tagDEC): TSmartDecimal;
  end;


  {@class TSmartDecimal
    Precision aware Decimal implementation. Any size number with any number of significant digits is supported.

    Note that operations are precision aware operations. Note that whole numbers are assumed to have
    unlimited precision. For example:
      2 x 2 = 4
      2.0 x 2.0 = 4.0
      2.00 x 2.0 = 4.0
    and
     10 / 3 = 3.33333333333333333333333333333333333333333333333
     10.0 / 3 = 3.33
     10.00 / 3 = 3.333
     10.00 / 3.0 = 3.3
     10 / 3.0 = 3.3

    Addition
      2 + 0.001 = 2.001
      2.0 + 0.001 = 2.0

    Note that the string representation is precision limited, but the internal representation
    is not.

    why QDecimal as a name in the DotNet API? It's for quantities, but just calling it
    Decimal clashes with System.Decimal which is always in scope
  }
  {!.Net HL7Connect.Ucum.QDecimal}
  TSmartDecimal = class (TAdvPersistent)
  private
    FContext : TSmartDecimalContext;
    FPrecision : integer;
    FScientific : Boolean;
    FNegative : Boolean;
    FDigits : String;
    FDecimal : integer;
    FOwned : integer;

    Procedure SetValue(sValue : String);
    Procedure SetValueDecimal(sValue : String);
    Procedure SetValueScientific(sValue : String);
    Function GetValue: String;
    Function GetValueDecimal : String;
    Function GetValueScientific : String;
//    Function GetValueByPrecision: String;
//    Function GetValueScientificByPrecision : String;
//    Function GetValueDecimalByPrecision : String;

    Function DoAdd(oOther : TSmartDecimal) : TSmartDecimal;
    Function DoSubtract(oOther : TSmartDecimal) : TSmartDecimal;

    Function StringAddition(s1, s2 : String):String;
    Function StringSubtraction(s1, s2 : String):String;

    Function dig(c : Char) : integer;
    Function cdig(i : integer) : Char;
  Public
    Constructor Create(context : TSmartDecimalContext); Overload;
    Constructor Create(sValue : String); Overload;
    Constructor Create(iValue : Integer); Overload;
    Constructor Create(iValue : int64); Overload;
    Destructor Destroy; Override;
    {!script hide}

    Function Link : TSmartDecimal; overload;
    Procedure Assign(oOther : TAdvObject); override;
    Function Clone : TSmartDecimal; Overload;
    Procedure Define(oFiler : TAdvFiler); Override;
    class Function Equal(oOne, oTwo : TSmartDecimal) : Boolean; overload;
    class Function Compares(oOne, oTwo : TSmartDecimal) : Integer; overload;
    class Function FromActiveX(oX: tagDEC): TSmartDecimal;
    function hasContext(other : TSmartDecimal) : boolean;

    Function Multiply(iOther : Integer) : TSmartDecimal; Overload;
    Function Divide(iOther : Integer) : TSmartDecimal; Overload;
    Function DivInt(iOther : Integer) : TSmartDecimal; Overload;
    Function Modulo(iOther : Integer) : TSmartDecimal; Overload;
    Function Add(iOther : Integer) : TSmartDecimal; Overload;
    Function Subtract(iOther : Integer) : TSmartDecimal; Overload;
    Function Equal(oOther : TSmartDecimal) : Boolean; overload;
    Function Compares(oOther : TSmartDecimal) : Integer; overload;
    Function AsCardinal : Cardinal;
    Function AsInteger : Integer;
    {!script show}

    {@member isZero
      true if this value is zero (also implies isWholeNumber = true)
    }
    Function IsZero : Boolean;

    {@member IsOne
      true if this value is zero (also implies isWholeNumber = true)
    }
    Function IsOne : Boolean;

    {@member IsWholeNumber
      true if this value is a whole number
    }
    Function IsWholeNumber : Boolean;

    {@member Trunc
      retrun this number with any decimal places removed
    }
    Function Trunc : TSmartDecimal;

    {@member Multiply
      return this value multiplied by other value.
    }
    Function Multiply(oOther : TSmartDecimal) : TSmartDecimal; Overload;

    {@member Divide
      return this value divided by other value.
    }
    Function Divide(oOther : TSmartDecimal) : TSmartDecimal; Overload;

    {@member DivInt
      return the number of times other will "fit into" this number. This is usually called
      Integer Division, but in this implementation, neither this nor other needs to be
      a whole number; however the result of this operation will always be a whole number
    }
    Function DivInt(oOther : TSmartDecimal) : TSmartDecimal; Overload;

    {@member Modulo
      modulus - the left over when fitting other into this.

      this modulo other = this - (other * (this divint other))
    }
    Function Modulo(oOther : TSmartDecimal) : TSmartDecimal; Overload;

    {@member Add
      return the result of adding this to other
    }
    Function Add(oOther : TSmartDecimal) : TSmartDecimal; Overload;

    {@member Subtract
      return the result of subtracting other from this
    }
    Function Subtract(oOther : TSmartDecimal) : TSmartDecimal; Overload;

    {@member AsCOMDecimal
      Represent the decimal in the native COM decimal format.
      Precision is lost in this representation.
    }
    Function AsCOMDecimal : TDecimal;

    {@member AsInt64
      Represent the decimal in the native COM decimal format.
      Precision is lost in this representation.
    }
    Function AsInt64 : Int64;

    {@member upperBound
      the upper bound given the imprecision. for example, if the value is 1,0, then the upper bound is 1.05.
    }
    Function upperBound : TSmartDecimal;

    {@member lowerBound
      the lower bound given the imprecision. for example, if the value is 1,0, then the upper bound is 1.05.
    }
    Function lowerBound : TSmartDecimal;

    {@member immediateUpperBound
      the upper bound given the face value. for example, if the value is 1,0, then the upper bound is 1.000000000000000000000000001.
    }
    Function immediateUpperBound : TSmartDecimal;

    {@member immediateLowerBound
      the immediate lower bound given the face value. for example, if the value is 1,0, then the upper bound is 0.99999999999999999999999999999999.
    }
    Function immediateLowerBound : TSmartDecimal;
  published
    {@member IsScientific
      whether to use a scientific representation (i.e. 1e10). This value
      is carried across operations
    }
    Property IsScientific : Boolean read FScientific write FScientific;

    {@member Precision
      the precision of the value. You can change the precision if you want.
    }
    Property Precision : Integer read FPrecision write FPrecision;

    {@member AsString
      String representation of the value - will use scientific or decimal notation as specified by the IsScientific property
    }
    Property AsString : String read GetValue;

    {@member AsScientific
      String representation using scientific notiation
    }
    Property AsScientific : String read GetValueScientific;

    {@member AsDecimal
      String representation using decimal notiation
    }
    Property AsDecimal : String read GetValuedecimal;

  end;

Function GUIDAsOIDWrong(Const aGUID : TGUID) : String;
Function GUIDAsOIDRight(Const aGUID : TGUID) : String;

Implementation

{$IFDEF VER130}
Uses
  BigNum;
{$ENDIF}

Constructor TSmartDecimal.Create(context : TSmartDecimalContext);
Begin
  inherited Create;
  if context = nil then
    FOwned := 0
  else
    FOwned := 1;
  FContext := context;
  FContext.Add(self);
End;

Constructor TSmartDecimal.Create(sValue : String);
begin
  Create;
  FOwned := 2;
  SetValue(sValue);
end;

Constructor TSmartDecimal.Create(iValue : Integer);
begin
  Create;
  FOwned := 2;
  SetValue(inttostr(iValue));
end;

Constructor TSmartDecimal.Create(iValue : int64);
begin
  Create;
  FOwned := 2;
  SetValue(inttostr(iValue));
end;

Function TSmartDecimal.Clone : TSmartDecimal;
Begin
  result := TSmartDecimal(Inherited Clone);
end;

Procedure TSmartDecimal.Assign(oOther : TAdvObject);
Begin
  inherited;
  FPrecision := TSmartDecimal(oOther).FPrecision;
  FScientific := TSmartDecimal(oOther).FScientific;
  FNegative := TSmartDecimal(oOther).FNegative;
  FDigits := TSmartDecimal(oOther).FDigits;
  FDecimal := TSmartDecimal(oOther).FDecimal;

end;

Procedure TSmartDecimal.SetValue(sValue : String);
Begin
  if (sValue= '') or (sValue = '-') then
    Error('SetValue', '"'+sValue+'" is not a valid decimal');
  sValue := lowercase(sValue);
  if pos('e', sValue) > 0 then
    SetValueScientific(sValue)
  Else
    SetValueDecimal(sValue);
end;

Function FirstNonZero(s : String):integer;
var
  i : integer;
begin
  result := length(s);
  for i := 1 to length(s) Do
    if not CharInSet(s[i], ['0', '.']) then
      result := IntegerMin(result, i);
end;

Function AllZerosButLast(s : String; iStart : integer):Boolean;
var
  i : integer;
begin
  result := iStart < length(s) - 1;
  for i := iStart to length(s) - 1 Do
    if s[i] <> '0' then
      result := false;
end;


Function AllZeros(s : String; iStart : integer):Boolean;
var
  i : integer;
begin
  result := true;
  for i := iStart to length(s) Do
    if s[i] <> '0' then
      result := false;
end;

Function CountSignificants(sValue : String):Integer;
var
  i : integer;
Begin
  i := pos('.', sValue);
  if i > 0 then
    delete(sValue, i, 1);
  while (sValue[1] = '0') do
    delete(sValue, 1, 1);
  result := length(sValue);
end;

Procedure TSmartDecimal.SetValueDecimal(sValue : String);
var
  iDecimal : integer;
  i : integer;
Begin
  FScientific := false;
  iDecimal := 0;
  FNegative := (sValue[1] = '-');
  if FNegative then
    delete(sValue, 1, 1);

  while (sValue[1] = '0') And (length(sValue) > 1) Do
    delete(sValue, 1, 1);

  for i := 1 to length(sValue) do
    if (sValue[i] = '.') And (iDecimal = 0) then
      iDecimal := i
    else if not CharInSet(sValue[i], ['0'..'9']) then
      raise Exception.Create('"'+sValue+'" is not a valid decimal');

  if iDecimal = 0 then
  Begin
    FPrecision := Length(sValue);
    FDecimal := Length(sValue)+1;
    FDigits := sValue;
  end
  else if iDecimal = length(sValue) then
    raise Exception.Create('"'+sValue+'" is not a valid decimal')
  else
  begin
    FDecimal := iDecimal;
    if AllZeros(sValue, 2) then
      FPrecision := length(sValue) - 1
    Else
      FPrecision := CountSignificants(sValue);
    FDigits := sValue;
    Delete(FDigits, FDecimal, 1);
    if AllZeros(FDigits, 1) then
      inc(FPrecision)
    else
      while (FDigits[1] = '0') Do
      begin
        delete(FDigits, 1, 1);
        dec(FDecimal);
      end;
  end;
end;

Function TSmartDecimal.Add(iOther : Integer) : TSmartDecimal;
var
  oTemp : TSmartDecimal;
Begin
  oTemp := FContext.Value(iOther);
  result := Add(oTemp);
end;

Function TSmartDecimal.Add(oOther : TSmartDecimal) : TSmartDecimal;
Begin
  if (self = nil) Or (oOther = nil) then
    result := nil
  Else if (FNegative = oOther.FNegative) then
  Begin
    result := DoAdd(oOther);
    result.FNegative := FNegative;
  end
  else if (FNegative) then
    result := oOther.DoSubtract(self)
  else
    result := DoSubtract(oOther);
end;

Function TSmartDecimal.StringAddition(s1, s2 : String):String;
var
  i, t, c : Integer;
Begin
  assert(length(s1) = length(s2));
  result := stringmultiply('0', length(s2));
  c := 0;
  for i := length(s1) downto 1 do
  begin
    t := c + dig(s1[i]) + dig(s2[i]);
    result[i] := cdig(t mod 10);
    c := t div 10;
  end;
  assert(c = 0);
end;

Function TSmartDecimal.DoAdd(oOther : TSmartDecimal) : TSmartDecimal;
var
  iMax : Integer;
  s1, s2, s3 : String;
Begin
  iMax := IntegerMax(FDecimal, oOther.FDecimal);
  s1 := StringMultiply('0', iMax - FDecimal+1) + FDigits;
  s2 := StringMultiply('0', iMax - oOther.FDecimal+1) + oOther.FDigits;
  if Length(s1) < length(s2) then
    s1 := s1 + StringMultiply('0', length(s2) - length(s1))
  else if Length(s2) < length(s1) then
    s2 := s2 + StringMultiply('0', length(s1) - length(s2));


  s3 := StringAddition(s1, s2);

  if s3[1] = '1' then
    inc(iMax)
  else
    delete(s3, 1, 1);
  if iMax <> length(s3)+1 then
  Begin
    if iMax <= 0 then
      raise exception.create('unhandled')
    else if imax <= length(s3) then
      insert('.', s3, iMax)
    else
      raise exception.create('unhandled')
  end;

  result := FContext.value(s3);
  result.FScientific := FScientific or oOther.FScientific;
  // todo: the problem with this is you have to figure out the absolute precision and take the lower of the two, not the relative one
  if FDecimal < oOther.FDecimal then
    result.FPrecision := FPrecision
  else if oOther.FDecimal < FDecimal then
    result.FPrecision := oOther.FPrecision
  else
    result.FPrecision := IntegerMin(FPrecision, oOther.FPrecision);
end;


Function TSmartDecimal.StringSubtraction(s1, s2 : String):String;
var
  i, t, c : integer;
Begin
  assert(length(s1) = length(s2));

  result := stringmultiply('0', length(s2));
  c := 0;
  for i := length(s1) downto 1 do
  begin
    t := c + (dig(s1[i]) - dig(s2[i]));
    if t < 0 then
    Begin
      inc(t, 10);
      if i = 1 then
        raise exception.create('internal logic error')
      else
        s1[i-1] := cdig(dig(s1[i-1])-1);
    end;
    result[i] := cdig(t);
  end;
  assert(c = 0);
end;

Function TSmartDecimal.DoSubtract(oOther : TSmartDecimal) : TSmartDecimal;
var
  iMax : Integer;
  s1, s2, s3 : String;
  bNeg : Boolean;
Begin
  iMax := IntegerMax(FDecimal, oOther.FDecimal);
  s1 := StringMultiply('0', iMax - FDecimal+1) + FDigits;
  s2 := StringMultiply('0', iMax - oOther.FDecimal+1) + oOther.FDigits;
  if Length(s1) < length(s2) then
    s1 := s1 + StringMultiply('0', length(s2) - length(s1))
  else if Length(s2) < length(s1) then
    s2 := s2 + StringMultiply('0', length(s1) - length(s2));

  bNeg := (s1 < s2);
  if bNeg then
  Begin
    s3 := s2;
    s2 := s1;
    s1 := s3;
  end;

  s3 := StringSubtraction(s1, s2);

  if s3[1] = '1' then
    inc(iMax)
  else
    delete(s3, 1, 1);
  if iMax <> length(s3)+1 then
  Begin
    if iMax <= 0 then
      raise exception.create('unhandled')
    else if imax <= length(s3) then
      insert('.', s3, iMax)
    else
      raise exception.create('unhandled');
  end;

  result := FContext.value(s3);
  result.FNegative := bNeg;
  result.FScientific := FScientific or oOther.FScientific;
  if FDecimal < oOther.FDecimal then
    FPrecision := FPrecision
  else if oOther.FDecimal < FDecimal then
    FPrecision := oOther.FPrecision
  else
    FPrecision := IntegerMin(FPrecision, oOther.FPrecision);
end;

Function TSmartDecimal.Multiply(iOther : Integer) : TSmartDecimal;
var
  oTemp : TSmartDecimal;
Begin
  oTemp := Fcontext.value(iOther);
  result := Multiply(oTemp);
end;

Function TSmartDecimal.Multiply(oOther : TSmartDecimal) : TSmartDecimal;
var
  iMax : Integer;
  s1, s2, s3 : String;
  s : Array of String;
  res : String;
  i, j, c, t : integer;
  iDec : Integer;
  iPrec : Integer;
Begin
  if (self = nil) or (oOther = nil) then
    result := nil
  Else if (self.isZero) or (oOther.IsZero) then
    result := FContext.Zero
  else if oOther.isOne then
    result := self.Link
  else
  Begin
    iMax := IntegerMax(FDecimal, oOther.FDecimal);
    s1 := StringMultiply('0', iMax - FDecimal+1) + FDigits;
    s2 := StringMultiply('0', iMax - oOther.FDecimal+1) + oOther.FDigits;
    if Length(s1) < length(s2) then
      s1 := s1 + StringMultiply('0', length(s2) - length(s1))
    else if Length(s2) < length(s1) then
      s2 := s2 + StringMultiply('0', length(s1) - length(s2));

    if s2 > s1 then
    Begin
      s3 := s1;
      s1 := s2;
      s2 := s3;
    end;
    SetLength(s, length(s2));

    t := 0;
    for i := length(s2) downto 1 do
    begin
      s[i-1] := StringMultiply('0', length(s2)-i);
      c := 0;
      for j := length(s1) downto 1 do
      begin
        t := c + (dig(s1[j]) * dig(s2[i]));
        insert(cdig(t mod 10), s[i-1], 1);
        c := t div 10;
      end;
      while c > 0 Do
      Begin
        insert(cdig(t mod 10), s[i-1], 1);
        c := t div 10;
      end;
    end;

    t := 0;
    for i := Low(s) to High(s) Do
      t := IntegerMax(t, Length(s[i]));
    for i := Low(s) to High(s) Do
      s[i] := StringMultiply('0', t-Length(s[i]))+s[i];

    res := '';
    c := 0;
    for i := t Downto 1 do
    Begin
      for j := Low(s) to High(s) Do
        c := c + dig(s[j][i]);
      insert(cdig(c mod 10), res, 1);
      c := c div 10;
    end;
      while c > 0 Do
      Begin
        assert(false, 'not implemented yet?');
//        s[i-1] := s[i-1] + cdig(t mod 10);
 //       c := t div 10;
      end;

    iDec := Length(res) + 1 - ((length(s1)-iMax)*2);

    while (res <> '') And (res <> '0') And (res[1] = '0') Do
    begin
      delete(res, 1, 1);
      dec(iDec);
    end;

    if IsWholeNumber and oOther.IsWholeNumber Then
      iPrec := INTEGER_PRECISION
    else if IsWholeNumber then
      iPrec := oOther.FPrecision
    else if oOther.IsWholeNumber then
      iPrec := FPrecision
    Else
      iPrec := IntegerMin(FPrecision, oOther.FPrecision);
    while (length(res) > iPrec) And (res[Length(res)] = '0') Do
      delete(res, Length(res), 1);

    result := FContext.value(res);
    result.FPrecision := iPrec;
    result.FDecimal := iDec;
    result.FNegative := FNegative <> oOther.FNegative;
    result.FScientific := FScientific or oOther.FScientific;
//    writeln('  '+asdecimal+' x ' +oOther.AsDecimal+' = ' +result.AsDecimal);
  end;
end;

Function TSmartDecimal.Divide(iOther : Integer) : TSmartDecimal;
var
  oTemp : TSmartDecimal;
Begin
  oTemp := FContext.Value(iOther);
  result := Divide(oTemp);
end;


Function TrimLeadingZeros(s : String):String;
begin
  result := s;
  while (result <> '') And (result[1] = '0') do
    delete(result, 1, 1);
  if result = '' then
    result := '0';
end;

Function RoundUp(const s : String; var d : Integer):String;
var
  i : integer;
  bUp : Boolean;
Begin
  result := s;
  i := length(s);
  bUp := true;
  while bUp and (i > 0) Do
  begin
    bUp := result[i] = '9';
    if bUp then
      result[i] := '0'
    else
      result[i] := char(ord(result[i])+1);
    dec(i);
  end;
  if bUp then
  begin
    result := '1'+result;
    inc(d);
  end;
end;

Function TSmartDecimal.Divide(oOther : TSmartDecimal) : TSmartDecimal;
var
  s : String;
  w, r, v : String;
  i, l, m, d, vi, iPrec : integer;
  tens : Array of String;
  bProc, bHandled, bUp : Boolean;
  Procedure Grabnext;
  Begin
    if (vi <= length(v)) then
    begin
      w := w + v[vi];
      inc(vi);
      bhandled := false;
    end
    else
    Begin
      w := w + '0';
      inc(d);
    end;
    while (length(w) < length(tens[0])) do
      w := '0'+w;
  end;
  Function finished : Boolean;
  begin
    result := bhandled and ((l > m) or ((vi > length(v)) And ((w = '') or AllZeros(w, 1))));
  end;
Begin
  if (self = nil) or (oOther = nil) then
    result := nil
  Else if IsZero Then
    result := FContext.Zero
  else if oOther.IsZero then
    raise Exception.create('Attempt to divide '+asString+' by zero')
  else if oOther.isOne then
    result := self.Link
  else
  Begin
    s := '0'+oOther.FDigits;
    m := IntegerMax(length(FDigits), Length(oOther.FDigits)) + 40; // max loops we'll do
    SetLength(tens, 10);
    tens[0] := StringAddition(StringMultiply('0', length(s)), s);
    for i := 1 to 9 do
      tens[i] := StringAddition(tens[i-1], s);
    v := FDigits;
    r := '';
    l := 0;
    d := (length(FDigits) - FDecimal + 1) - (length(oOther.FDigits) - oOther.FDecimal + 1);

    while length(v) < length(tens[0]) do
    begin
      v := v+'0';
      inc(d);
    end;
    if copy(v, 1, length(oOther.FDigits)) < oOther.FDigits then
    Begin
      if length(v) = length(tens[0]) then
      begin
        v := v + '0';
        inc(d);
      end;
      w := copy(v, 1, length(oOther.FDigits)+1);
      vi := length(w)+1;
    end
    Else
    Begin
      w := '0'+copy(v, 1, length(oOther.FDigits));
      vi := length(w);
    end;
    while not finished Do
    Begin
      inc(l);
      bHandled := true;
      bProc := false;
      for i := 8 downto 0 Do
      begin
        if tens[i] <= w then
        Begin
          bProc := true;
          r := r + cdig(i+1);
          w := TrimLeadingZeros(StringSubtraction(w, tens[i]));
          if not finished then
            grabnext;
          break;
        end;
      end;
      if not bProc then
      begin
        assert(w[1] = '0');
        delete(w, 1, 1);
        r := r + '0';
        if not finished then
          grabNext;
      end;
    end;
    if isWholeNumber And oOther.IsWholeNumber and (l < m) then
    begin
      for i := 1 to d do
        if (r[length(r)] = '0') then
        begin
          delete(r, length(r), 1);
          dec(d);
        end;
      iPrec := INTEGER_PRECISION;
    end
    Else
    Begin
      if IsWholeNumber and oOther.IsWholeNumber Then
        iPrec := INTEGER_PRECISION
      else if IsWholeNumber then
        iPrec := IntegerMax(oOther.FPrecision, length(r) - d)
      else if oOther.IsWholeNumber then
        iPrec := IntegerMax(FPrecision, length(r) - d)
      Else
        iPrec := IntegerMax(IntegerMin(FPrecision, oOther.FPrecision), length(r) - d);
      while (length(r) > iPrec) Do
      begin
        bUp := (r[Length(r)] > '5');
        delete(r, Length(r), 1);
        if bUp then
          r := roundUp(r, d);
        dec(d);
      end;
    end;
    result := FContext.value(r);
    result.FDecimal := length(r) - d + 1;
    result.FNegative := FNegative <> oOther.FNegative;
    result.FPrecision := iPrec;
    result.FScientific := FScientific or oOther.FScientific;
//    writeln('  '+asdecimal+' / ' +oOther.AsDecimal+' = ' +result.AsDecimal);
  end;
end;

Function TSmartDecimal.DivInt(iOther : Integer) : TSmartDecimal;
var
  oTemp : TSmartDecimal;
Begin
  oTemp := FContext.Value(iOther);
  result := DivInt(oTemp);
end;


Function TSmartDecimal.DivInt(oOther : TSmartDecimal) : TSmartDecimal;
var
  t : TSmartDecimal;
Begin
  if (self = nil) or (oOther = nil) then
    result := nil
  Else
  Begin
    t := Divide(oOther);
    result := t.Trunc;
  end;
end;


Function TSmartDecimal.Modulo(iOther : Integer) : TSmartDecimal;
var
  oTemp : TSmartDecimal;
Begin
  oTemp := FContext.value(iOther);
  result := Modulo(oTemp);
end;


Function TSmartDecimal.Modulo(oOther : TSmartDecimal) : TSmartDecimal;
var
  t, t2 : TSmartDecimal;
Begin
  if (self = nil) or (oOther = nil) then
    result := nil
  Else
  Begin
    t := DivInt(oOther);
    t2 := t.Multiply(oOther);
    result := subtract(t2);
  end;
end;



Function TSmartDecimal.Subtract(iOther : Integer) : TSmartDecimal;
var
  oTemp : TSmartDecimal;
Begin
  oTemp := FContext.value(iOther);
  result := Subtract(oTemp);
end;

Function TSmartDecimal.Subtract(oOther : TSmartDecimal) : TSmartDecimal;
Begin
  if (self = nil) Or (oOther = nil) then
    result := nil
  Else if (FNegative and not oOther.FNegative) then
  Begin
    result := DoAdd(oOther);
    result.FNegative := true;
  end
  Else if (not FNegative and oOther.FNegative) then
    result := DoAdd(oOther)
  Else if (FNegative and oOther.FNegative) then
  begin
    result := DoSubtract(oOther);
    result.FNegative := not result.FNegative;
  end
  Else
  begin
    result := oOther.DoSubtract(self);
    result.FNegative := not result.FNegative;
  end;
end;

Function TSmartDecimal.Equal(oOther : TSmartDecimal) : Boolean;
Begin
  result := FContext.Equal(self, oOther);
end;


function StringIsDecimal(s : String) : Boolean;
var
  bDec : Boolean;
  i : integer;
Begin
  bDec := false;
  result := true;
  for i := 1 to length(s) Do
  begin
    if not (
       ((i = 1) and (s[i] = '-')) or
       (not bDec and (s[i] = '.')) or
       CharInSet(s[i], ['0'..'9'])) Then
      result := false;
    bdec := s[i] = '.';
  End;
End;

Procedure TSmartDecimal.SetValueScientific(sValue: String);
var
  i : integer;
  s, e : String;
begin
  StringSplit(sValue, 'e', s, e);

  if (s= '') or (s = '-') or not StringIsDecimal(s) then
    Error('SetValue', '"'+sValue+'" is not a valid decimal (numeric)');
  if (e= '') or (e = '-') or not StringIsDecimal(e) then
    Error('SetValue', '"'+sValue+'" is not a valid decimal (exponent)');

  SetValueDecimal(s);
  FScientific := true;

  // now adjust for exponent

  if e[1] = '-' then
    i := 2
  Else
    i := 1;
  while i <= length(e) Do
  begin
    if not CharInSet(e[i], ['0'..'9']) then
      raise Exception.Create('"'+sValue+'" is not a valid decimal');
    inc(i);
  end;
  i := StrToInt(e);
  FDecimal := FDecimal + i;
end;

Function TSmartDecimal.GetValue: String;
begin
  if FScientific then
    result := GetValueScientific
  Else
    result := GetValueDecimal;
end;
{
Function TSmartDecimal.GetValueByPrecision: String;
begin
  if FScientific then
    result := GetValueScientificByPrecision
  Else
    result := GetValueDecimalByPrecision;
end;
}

Function TSmartDecimal.GetValueScientific: String;
var
  bZero : Boolean;
begin
  result := FDigits;
  bZero := AllZeros(result, 1);
  if bZero then
  begin
    if Precision < 2 then
      result := '0e0'
    Else
      result := '0.'+StringMultiply('0', FPrecision-1)+'e0';
  end
  Else
  begin
    if Length(FDigits) > 1 then
      insert('.', result, 2);
    result := result + 'e'+inttostr(FDecimal - 2);
  end;
  if FNegative and not bZero then
    result := '-' + result;
end;


function TSmartDecimal.hasContext(other: TSmartDecimal): boolean;
begin
  result := FContext = other.FContext;
end;

function TSmartDecimal.immediateLowerBound: TSmartDecimal;
var
  i : integer;
begin
  if IsZero then
  begin
    result := immediateUpperBound;
    result.FNegative := true;
  end
  else
  begin
    result := FContext.Value(AsDecimal);
    inc(result.FPrecision);
    if FNegative then
    result.FDigits := result.FDigits + StringMultiply('0', 25 - length(result.FDigits) - result.FDecimal)+'1'
    else
    begin
      i := length(result.FDigits);
      result.FDigits[i] := char(ord(result.FDigits[i]) - 1);
      while (i > 0) and (result.FDigits[i] < '0') do
      begin
        result.FDigits[i] := '9';
        dec(i);
        result.FDigits[i] := char(ord(result.FDigits[i]) - 1)
      end;
      assert(i > 0);
    result.FDigits := result.FDigits + StringMultiply('9', 24 - length(result.FDigits))+'9'
    end;
  end;
end;

function TSmartDecimal.immediateUpperBound: TSmartDecimal;
var
  i : integer;
begin
  result := FContext.Value(AsDecimal);
  inc(result.FPrecision);
  if not FNegative then
    result.FDigits := result.FDigits + StringMultiply('0', 25 - length(result.FDigits) - result.FDecimal)+'1'
  else
  begin
    i := length(result.FDigits);
    result.FDigits[i] := char(ord(result.FDigits[i]) - 1);
    while (i > 1) and (result.FDigits[i] < '0') do
    begin
      result.FDigits[i] := '0';
      dec(i);
      result.FDigits[i] := char(ord(result.FDigits[i]) - 1);
    end;
    assert(i > 0);
    result.FDigits := result.FDigits + StringMultiply('0', 24 - length(result.FDigits))+'9'
  end;
end;

Function TSmartDecimal.GetValueDecimal: String;
begin
  result := FDigits;
  if FDecimal <> length(FDigits) + 1 then
    if FDecimal < 1 then
      result := '0.'+StringMultiply('0', 1-FDecimal)+FDigits
    Else if FDecimal <= length(result) then
      if (FDecimal = 1) then
        result := '0.'+result
      Else
        insert('.', result, FDecimal)
    Else
      result := result + stringMultiply('0', FDecimal - length(result)-1);
  if (FPrecision = INTEGER_PRECISION) and result.Contains('.') and (AllZerosButLast(result, pos('.', result)+1)) then
    result := copy(result, 1, pos('.', result)-1);
  if FNegative and not AllZeros(result, 1) then
    result := '-' + result;
end;

Function TSmartDecimal.cdig(i: integer): Char;
begin
//  assert((i >= 0) and (I <= 9));
  result := char(i + ord('0'));
end;

Function TSmartDecimal.dig(c: Char): integer;
begin
//  assert(c in ['0'..'9']);
  result := ord(c) - ord('0');
end;

Function TSmartDecimal.IsZero: Boolean;
begin
  result := AllZeros(FDigits, 1);
end;

Function TSmartDecimal.IsOne: Boolean;
var
  oOne : TSmartDecimal;
begin
  oOne := FContext.One;
  result := Compares(oOne) = 0;
end;

Function TSmartDecimal.Compares(oOther: TSmartDecimal): Integer;
Begin
  result := FContext.Compares(self, oOther);
end;

Function TSmartDecimal.isWholeNumber: Boolean;
begin
  result := pos('.', GetValueDecimal) = 0;
end;


Procedure TSmartDecimal.Define(oFiler: TAdvFiler);
begin
  inherited;
  oFiler['Precision'].Defineinteger(FPrecision);
  oFiler['Scientific'].DefineBoolean(FScientific);
  oFiler['Negative'].DefineBoolean(FNegative);
  oFiler['Digits'].DefineString(FDigits);
  oFiler['Decimal'].Defineinteger(FDecimal);
end;


Destructor TSmartDecimal.Destroy;
begin
  if FOwned <> 4 then
    raise Exception.Create('Premature free for a decimal');
  inherited;
end;

Function CardinalToint64(i : Cardinal):Int64;
Begin
  result := i;
end;

Function TSmartDecimal.AsCOMDecimal : TDecimal;
var
  r2_32, t, t1, t2 : TSmartDecimal;
Begin
  ZeroMemory(@result, SizeOf(tagDEC));
  r2_32 := FContext.Value('4294967296');
  if FNegative then
    result.Sign := $80
  else
    result.Sign := 0;
  result.scale := length(FDigits) - FDecimal + 1;
  t := FContext.value(self);
  t.FDecimal := length(t.FDigits)+1;
  t.FNegative := false;

  t1 := t.Modulo(r2_32);
  result.Lo32 := Integer(t1.AsCardinal);

  t1 := t.DivInt(r2_32);
  t2 := t1.Modulo(r2_32);
  result.Mid32 := Integer(t2.AsCardinal);

  t1 := t.DivInt(r2_32);
  t2 := t1.DivInt(r2_32);
  t1 := t2.Modulo(r2_32);
  result.Hi32 := Integer(t1.AsCardinal);
end;



Function TSmartDecimal.Trunc: TSmartDecimal;
begin
  if FDecimal < 1 then
    result := FContext.Zero
  Else
  begin
    result := FContext.value(self);
    if Length(result.FDigits) >= FDecimal then
      SetLength(result.FDigits, FDecimal-1);
    if result.FDigits = '' then
    begin
      result.FDigits := '0';
      result.FDecimal := 2;
    end;
  end;
end;

function TSmartDecimal.upperBound: TSmartDecimal;
var
  i : integer;
begin
  result := FContext.Value(AsDecimal);
  inc(result.FPrecision);
  if not FNegative then
    result.FDigits := result.FDigits + '5'
  else
  begin
    i := length(result.FDigits);
    result.FDigits[i] := char(ord(result.FDigits[i]) - 1);
    while (i > 1) and (result.FDigits[i] < '0') do
    begin
      result.FDigits[i] := '0';
      dec(i);
      result.FDigits[i] := char(ord(result.FDigits[i]) - 1);
    end;
    assert(i > 0);
    result.FDigits := result.FDigits + '5'
  end;
end;

Function TSmartDecimal.AsCardinal: Cardinal;
var
  r : Int64;
  m : Int64;
begin
  r := AsInt64;
  if r < 0 then
    raise exception.create('Unable to represent '+AsString+' as an unsigned 4 byte number');
  m := High(Cardinal);
  if r > m then
    raise exception.create('Unable to represent '+AsString+' as an unsigned 4 byte number');
  result := r;
end;

function TSmartDecimal.AsInt64: Int64;
var
  t : TSmartDecimal;
begin
  if not isWholeNumber then
    raise exception.create('Unable to represent '+AsString+' as an integer');
  t := FContext.Value(Low(Int64));
  if FContext.Compares(self, t) < 0 then
    raise exception.create('Unable to represent '+AsString+' as a signed 8 byte integer');
  t := FContext.Value(High(Int64));
  if FContext.Compares(self, t) > 0 then
    raise exception.create('Unable to represent '+AsString+' as a signed 8 byte integer');
  result := StrToInt64(AsDecimal);
end;

function TSmartDecimal.AsInteger: Integer;
var
  r : Int64;
  m : Int64;
begin
  r := AsInt64;
  m := Low(Integer);
  if r < m then
    raise exception.create('Unable to represent '+AsString+' as a signed 4 byte number');
  m := high(Integer);
  if r > m then
    raise exception.create('Unable to represent '+AsString+' as a signed 4 byte number');
  result := r;
end;


{ TSmartDecimalContext }

Function TSmartDecimalContext.value(value : String) : TSmartDecimal;
begin
  result := TSmartDecimal.create(self);
  result.FOwned := 3;
  result.SetValue(value);
end;

Function TSmartDecimalContext.value(value : Integer) : TSmartDecimal;
begin
  result := TSmartDecimal.create(self);
  result.FOwned := 3;
  result.SetValue(inttostr(value));
end;

Function TSmartDecimalContext.value(value : int64) : TSmartDecimal;
begin
  result := TSmartDecimal.create(self);
  result.FOwned := 3;
  result.SetValue(inttostr(value));
end;

Function TSmartDecimalContext.Value(value : TSmartDecimal) : TSmartDecimal;
begin
  result := TSmartDecimal.create(self);
  result.FOwned := 3;
  result.FPrecision := value.FPrecision;
  result.FScientific := value.FScientific;
  result.FNegative := value.FNegative;
  result.FDigits := value.FDigits;
  result.FDecimal := value.FDecimal;
end;


procedure TSmartDecimalContext.BeforeDestruction;
var
  i : integer;
begin
  for i := 0 to Count - 1 do
    TSmartDecimal(ObjectByIndex[i]).FOwned := 4;
  inherited;
end;

Function TSmartDecimalContext.Equal(oOne, oTwo : TSmartDecimal) : Boolean;
Begin
  result := Compares(oOne, oTwo) = 0;
end;


Function TSmartDecimalContext.One: TSmartDecimal;
begin
  result := Value(1);
end;

Function TSmartDecimalContext.Zero: TSmartDecimal;
begin
  result := Value(0);
end;

Function TSmartDecimalContext.FromActiveX(oX: tagDEC): TSmartDecimal;
var
  r2_32, r1, r2, r3 : TSmartDecimal;
begin
  r2_32 := Value('4294967296');
  r1 := Value(cardinalToInt64(Cardinal(oX.hi32)));
  r2 := r1.Multiply(r2_32);
  r3 := r2.Multiply(r2_32);
  r1 := Value(cardinalToInt64(Cardinal(oX.Mid32)));
  r2 := r1.Multiply(r2_32);
  r1 := r3.Add(r2);
  r2 := Value(cardinalToInt64(Cardinal(oX.Lo32)));
  r3 := r1.add(r2);
  r3.FDecimal := length(r3.FDigits) + 1 - oX.scale;
  r3.FNegative := oX.sign <> 0;
  r3.FPrecision := length(r3.FDigits);
  r3.FScientific := not r3.isWholeNumber and (Abs(r3.FDecimal) > 7);
  result := r3;
end;

function TSmartDecimalContext.Link: TSmartDecimalContext;
begin
  result := TSmartDecimalContext(inherited Link);
end;

function round(prefix, s : String; i : integer) : String;
begin
  result := s;
  if s.Length > i then
  begin
    result := copy(result, 1, i);
    if (s[i+1]) >= '5' then
      result[i] := chr(ord(result[i])+1);
    while (i > 0) and  (result[i] > '9') do
    begin
      result[i] := '0';
      dec(i);
      result[i] := chr(ord(result[i])+1);
    end;
  end;
  if i = 0 then
  begin
    result := copy(prefix, 1, length(prefix)-1) +'1' + result;
  end
  else
    result := prefix + result;
end;

Function TSmartDecimalContext.Compares(oOne, oTwo: TSmartDecimal): Integer;
var
  iMax : Integer;
  s1, s2 : String;
Begin
  if (oOne = nil) Or (oTwo = nil) then
    result := 0
  Else
  Begin
    if oOne.FNegative and not oTwo.Fnegative then
      result := -1
    else if not oOne.FNegative and oTwo.Fnegative then
      result := 1
    else
    begin
      iMax := IntegerMax(oOne.FDecimal, oTwo.FDecimal);
      s1 := round('0'+StringMultiply('0', iMax - oOne.FDecimal+1), oOne.FDigits, oOne.FPrecision);
      s2 := round('0'+StringMultiply('0', iMax - oTwo.FDecimal+1), oTwo.FDigits, oTwo.FPrecision);
      if Length(s1) < length(s2) then
        s1 := s1 + StringMultiply('0', length(s2) - length(s1))
      else if Length(s2) < length(s1) then
        s2 := s2 + StringMultiply('0', length(s1) - length(s2));
      result := StringCompare(s1, s2);
      if oOne.FNegative then
        result := -result;
    End;
  end;
end;


function TSmartDecimal.Link: TSmartDecimal;
begin
  result := TSmartDecimal(Inherited Link);
end;

function TSmartDecimal.lowerBound: TSmartDecimal;
var
  i : integer;
begin
  if IsZero then
  begin
    result := upperBound;
    result.FNegative := true;
  end
  else
  begin
    result := FContext.Value(AsDecimal);
    inc(result.FPrecision);
    if FNegative then
      result.FDigits := result.FDigits + '5'
    else
    begin
      i := length(result.FDigits);
      result.FDigits[i] := char(ord(result.FDigits[i]) - 1);
      while (i > 0) and (result.FDigits[i] < '0') do
      begin
        result.FDigits[i] := '9';
        dec(i);
        result.FDigits[i] := char(ord(result.FDigits[i]) - 1)
      end;
      assert(i > 0);
      result.FDigits := result.FDigits + '5';
    end;
  end;
end;

class function TSmartDecimal.Compares(oOne, oTwo: TSmartDecimal): Integer;
var
  context : TSmartDecimalContext;
begin
  context := TSmartDecimalContext.create;
  try
    result := context.Compares(oOne, oTwo);
  finally
    context.free;
  end;
end;

class function TSmartDecimal.Equal(oOne, oTwo: TSmartDecimal): Boolean;
var
  context : TSmartDecimalContext;
begin
  context := TSmartDecimalContext.create;
  try
    result := context.Equal(oOne, oTwo);
  finally
    context.free;
  end;
end;

class function TSmartDecimal.FromActiveX(oX: tagDEC): TSmartDecimal;
var
  context : TSmartDecimalContext;
begin
  context := TSmartDecimalContext.create;
  try
    result := context.FromActiveX(oX);
    result.FContext := nil;
    result.Link;
  finally
    context.free;
  end;
end;

Function GUIDAsOIDWrong(Const aGUID : TGUID) : String;
var
  sGuid, s : String;
  r1, r2, r3, r4 : int64;
  c : integer;
  b1, b2, b3, b4, bs : TSmartDecimal;
  context : TSmartDecimalContext;
Begin
  sGuid := GUIDToString(aGuid);
  s := copy(sGuid, 30, 8);
  Val('$'+s, r1, c);
  s := copy(sGuid, 21, 4)+copy(sGuid, 26, 4);
  Val('$'+s, r2, c);
  s := copy(sGuid, 11, 4)+copy(sGuid, 26, 4);
  Val('$'+s, r3, c);
  s := copy(sGuid, 2, 8);
  Val('$'+s, r4, c);

  context := TSmartDecimalContext.create;
  Try
    b1 := context.value(r1);
    b2 := context.value(r2);
    b3 := context.value(r3);
    b4 := context.value(r4);
    bs := context.value('4294967296');
    b2 := b2.Multiply(bs);
    bs := context.value('18446744073709551616');
    b3 := b3.Multiply(bs);
    bs := context.value('79228162514264337593543950336');
    b4 := b4.Multiply(bs);
    b1 := b1.Add(b2);
    b1 := b1.Add(b3);
    b1 := b1.Add(b4);
    result := '2.25.'+b1.AsString;
  Finally
    context.free;
  End;
end;


Function GUIDAsOIDRight(Const aGUID : TGUID) : String;
var
  sGuid, s : String;
  r1, r2, r3, r4 : int64;
  c : integer;
  b1, b2, b3, b4, bs : TSmartDecimal;
  context : TSmartDecimalContext;
Begin
  sGuid := GUIDToString(aGuid);
  s := copy(sGuid, 30, 8);
  Val('$'+s, r1, c);
  s := copy(sGuid, 21, 4)+copy(sGuid, 26, 4);
  Val('$'+s, r2, c);
  s := copy(sGuid, 11, 4)+copy(sGuid, 16, 4);
  Val('$'+s, r3, c);
  s := copy(sGuid, 2, 8);
  Val('$'+s, r4, c);

  context := TSmartDecimalContext.create;
  Try
    b1 := context.Value(r1);
    b2 := context.Value(r2);
    b3 := context.Value(r3);
    b4 := context.Value(r4);
    bs := context.Value('4294967296');
    b2 := b2.Multiply(bs);
    bs := context.Value('18446744073709551616');
    b3 := b3.Multiply(bs);
    bs := context.Value('79228162514264337593543950336');
    b4 := b4.Multiply(bs);
    b1 := b1.Add(b2);
    b1 := b1.Add(b3);
    b1 := b1.Add(b4);
    result := '2.25.'+b1.AsString;
  Finally
    context.Free;
  End;
end;


Initialization
  Factory.RegisterClass(TSmartDecimal);
end.
