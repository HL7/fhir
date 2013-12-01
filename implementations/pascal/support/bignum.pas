unit BigNum;

{ BigNum v2.0, 32-bit for Delphi  by Jes R. Klinke

	Implements calculations on integers of arbitrary size.
	All operations necessary for cryptographic applications are provided,
	for functions generating large prime numbers look in BigPrime.pas.

	You may use this unit for whatever you want. But if you make a commercial
	product at least send me a copy of it, please.

	New in version 2:
	* Dynamic size
			Each instance of TBigNum has just enough memory allocated to it to
			keep its current value. You don't have to specify a fixed BigNumSize.
	* Negative numbers
			Most of the calculations now support negative values.
	* More efficient calculations
			As each instance of TBigNum keeps track of how many words are actually
			used in it's value, only the necessary calculations are performed.
			This particularly speeds up the multiplication, as lots of
			MUL's with zero words are avoided.

	New in Delphi 32-bit release:
	* Special Object Pascal features
			Properties provide easier syntax.
			Exceptions add understandable error messages.

	To do's:
	* Pentium optimization
			The assembler parts are tranlated instruction by instruction from the
			16-bit version. That means that there are lot of complicated string
			and loop instruction, which are poorly optimized on new Intel
			processors.

	Any comment or bug reports are welcome.
	You can reach me at jesk@diku.dk or jesk@dk-online.dk.
	My snail-mail address is Jes Rahbek Klinke
													 Haandvaerkerhaven 3, 2. mf
													 2400 Copenhagen NV
}

interface

uses
	SysUtils;

type
	PIntArr = ^TIntArr;
	TIntArr = array [0..10000] of LongInt; //jwe was 1000

	TBigNum = class
	private
		Value			: PIntArr;
		Alloc, Used	: Integer;
		Sign			: Boolean;
	{ obsolete functions, use the properties AsLong and AsString }
		function 	ToLong: LongInt;
		procedure 	FromLong(AValue: LongInt);
		function 	ToStr: string;
		procedure 	FromStr(S: string);
	{ internal procedures for memory management }
		procedure 	Realloc(Ints: Integer; Preserve: Boolean);
		function 	Critical: Boolean;
		procedure 	CountUsed;
	public
		constructor Create;overload;
		constructor Create(anint : Integer);overload; //jwe makes sense to have this one
		constructor Create(AValue : TBigNum);overload;
		destructor 	Destroy; override;
		procedure 	Assign(AValue: TBigNum);
		procedure 	Add(AValue: TBigNum);
		procedure 	Subtract(AValue: TBigNum);
		procedure 	Multiply(AValue: Integer);overload;
		procedure 	Multiply(AValue: TBigNum);overload;
		function 	Divide(ADivisor: TBigNum): Boolean;
		function 	Modulo(ADivisor: TBigNum): Boolean;
		procedure 	PowerModulo(AExponent, AModulo: TBigNum);
		procedure 	BitwiseOr(AMask: TBigNum);
		function 	Compare(AValue: TBigNum): Integer;overload;
		function 	Compare(AValue: Integer): Integer;overload;
		procedure 	Mult10;
		function 	Div10: Integer;
		procedure 	Mult2;
		procedure 	Div2;
		function 	Negative: Boolean;
		procedure 	Swap(AValue: TBigNum);
		property 	AsLong: LongInt read ToLong write FromLong; //jwe to go with above
		property 	AsString: string read ToStr write FromStr;
	{ procedures working on absolute values only, primarily for internal use. }
		procedure 	AbsIncrement(By: LongInt);
		procedure 	AbsDecrement(By: LongInt);
		function 	AbsCompare(AValue: TBigNum): Integer;
		procedure 	AbsAdd(AValue: TBigNum);
		procedure 	AbsSubtract(AValue: TBigNum);
		function 	AbsDivide(ADivisor: TBigNum): Boolean;
		function 	AbsModulo(ADivisor: TBigNum): Boolean;
		function 	AbsModuloLong(ADivisor: Integer): Integer;
		function 	IsEven : Boolean;
	end;

	EBigNum = class(Exception);
	EBigNumInvalidArg = class(EBigNum);
	EBigNumDivByZero = class(EBigNum);
	EBigNumTooBig = class(EBigNum);

implementation

constructor TBigNum.Create;
begin
	inherited Create;
	Alloc := 0;
	Used := 0;
end;

constructor TBigNum.Create(anint : Integer);
begin
	inherited Create;
	Alloc := 0;
	Used := 0;
	AsLong := anint;
end;
constructor TBigNum.Create(AValue : TBigNum);
begin
	inherited Create;
	Alloc := 0;
	Used := 0;
	Assign(AValue);
end;

destructor TBigNum.Destroy;
begin
	FreeMem(Value, Alloc * SizeOf(Integer));
	inherited Destroy;
end;

procedure TBigNum.Assign(AValue: TBigNum);
begin
	if Alloc < AValue.Used then
		Realloc(AValue.Used, False);
	Used := AValue.Used;
	Move(AValue.Value^, Value^, Used shl 2);
	FillChar(Value^[Used], (Alloc - Used) shl 2, 0);
	Sign := AValue.Sign;
end;

procedure TBigNum.Add(AValue: TBigNum);
var
	MValue: TBigNum;
begin
	if Sign xor AValue.Sign then
		if AbsCompare(AValue) >= 0 then
			AbsSubtract(AValue)
		else
		begin
			MValue := TBigNum.Create;
			MValue.Assign(AValue);
			Self.Swap(MValue);
			AbsSubtract(MValue);
			MValue.Destroy;
		end
	else
		AbsAdd(AValue);
end;

procedure TBigNum.Subtract(AValue: TBigNum);
var
	MValue: TBigNum;
begin
	if Sign xor AValue.Sign then
		AbsAdd(AValue)
	else
		if AbsCompare(AValue) >= 0 then
			AbsSubtract(AValue)
		else
		begin
			MValue := TBigNum.Create;
			MValue.Assign(AValue);
			Self.Swap(MValue);
			AbsSubtract(MValue);
			MValue.Destroy;
			Sign := not Sign;
		end;
end;


procedure TBigNum.Multiply(AValue: TBigNum);
var
	Needed: Integer;
	Result: PIntArr;
	SmallVal, BigVal: PIntArr;
	SmallSize, BigSize: Integer;
begin
	if Used = 0 then
		Exit;
	if AValue.Used = 0 then
	begin
		Used := 0;
		Exit;
	end;
	Sign := Sign xor AValue.Sign;
	Needed := Used + AValue.Used + 1;
	GetMem(Result, Needed * SizeOf(Integer));
	FillChar(Result^, Needed * SizeOf(Integer), 0);
	if Used > AValue.Used then
	begin
		SmallVal := AValue.Value;
		SmallSize := AValue.Used;
		BigVal := Value;
		BigSize := Used;
	end
	else
	begin
		BigVal := AValue.Value;
		BigSize := AValue.Used;
		SmallVal := Value;
		SmallSize := Used;
	end;
	asm
		PUSH	EDI
		PUSH	ESI
		PUSH	EBX
		XOR		EDX,EDX
@@0:PUSH	EDX
		MOV		EDI,SmallVal
		LEA		EDI,[EDI+4*EDX]
		MOV		EAX,[EDI]
		MOV		EDI,Result
		LEA		EDI,[EDI+4*EDX]
		MOV		ESI,BigVal
		MOV		ECX,BigSize
		PUSH	EBP
		MOV		EBP,EAX
		XOR		EDX,EDX
@@1:MOV		EAX,[ESI]
		MOV		EBX,EDX
		MUL		EBP
		ADD		EBX,EAX
		MOV		EAX,[EDI]
		ADC		EDX,0
		ADD		EAX,EBX
		MOV		[EDI],EAX
		ADC		EDX,0
		ADD		ESI,4
		ADD		EDI,4
		DEC		ECX
		JNE		@@1
		MOV		EAX,[EDI]
		ADD		EAX,EDX
		MOV		[EDI],EAX
		POP		EBP
		POP		EDX
		INC		EDX
		CMP		EDX,SmallSize
		JNE		@@0
		POP		EBX
		POP		ESI
		POP		EDI
	end;
	Realloc(Needed, False);
	Move(Result^, Value^, Needed * SizeOf(Integer));
	if Alloc - Needed > 0 then
		FillChar(Value^[Needed], (Alloc - Needed) * SizeOf(Integer), 0);
	FreeMem(Result, Needed * SizeOf(Integer));
	CountUsed;
end;

procedure 	TBigNum.Multiply(AValue: Integer);
var
	t	:		TBigNum;
begin
	t := TBigNum.Create(Avalue);
	Multiply(t);
	t.Free;
end;

{ Note: At first sight, you might think, that Divide and Modulo gives wrong
	results for negative values. This depends on the definition of the quoient
	and remainder.
	The definition used by these routines is:
	Given the divident N and divisor D, the quotient Q and remainder R is then
	defined by the equation
		N = D * Q + R,
	where the absolute value of R is less then the absolute value of D and R
	have the same sign as D.
	This will prove to be very convinient.}

function TBigNum.Divide(ADivisor: TBigNum): Boolean;
begin
	if Sign xor ADivisor.Sign then
	begin
		Subtract(ADivisor);
		AbsDecrement(1);
		Result := AbsDivide(ADivisor);
		Sign := not Sign;
	end
	else
	begin
		Result := AbsDivide(ADivisor);
	end;
end;

function TBigNum.Modulo(ADivisor: TBigNum): Boolean;
begin
	if Sign xor ADivisor.Sign then
	begin
		Subtract(ADivisor);
		AbsDecrement(1);
		Result := AbsModulo(ADivisor);
		Add(ADivisor);
		AbsDecrement(1);
	end
	else
	begin
		Result := AbsModulo(ADivisor);
	end;
end;

procedure TBigNum.PowerModulo(AExponent, AModulo: TBigNum);
var
	Result, A: TBigNum;
	I: Integer;
begin
	if AExponent.Sign then
		raise EBigNumInvalidArg.Create('Negative exponent in PowerModulo');
	Result := TBigNum.Create;
	A := TBigNum.Create;
	Result.AsLong := 1;
	A.Assign(Self);
	for I := 0 to AExponent.Used * SizeOf(Integer) * 8 - 1 do
	begin
		if AExponent.Value^[I shr 5] and (1 shl (I and 31)) <> 0 then
		begin
			Result.Multiply(A);
			Result.Modulo(AModulo);
		end;
		A.Multiply(A);
		A.Modulo(AModulo);
	end;
	Assign(Result);
	A.Destroy;
	Result.Destroy;
end;

procedure TBigNum.BitwiseOr(AMask: TBigNum);
begin
	if AMask.Used > Used then
		Realloc(AMask.Used, True);
	asm
		PUSH  EDI
		PUSH  ESI
		MOV		EDI,Self
		MOV		EDI,[EDI.TBigNum.Value]
		MOV		ESI,AMask
		MOV   ECX,[ESI.TBigNum.Used]
		OR		ECX,ECX
		JE		@@1
		MOV		ESI,[ESI.TBigNum.Value]
@@0:MOV		EAX,[ESI]
		OR    EAX,[EDI]
		MOV		[EDI],EAX
		ADD		ESI,4
		ADD		EDI,4
		DEC		ECX
		JNE		@@0
@@1:POP   ESI
		POP		EDI
	end;
	CountUsed;
end;

function TBigNum.Compare(AValue: TBigNum): Integer;
begin
	if Sign xor AValue.Sign then
		if Sign then
			Compare := -1
		else
			Compare := 1
	else
		if Sign then
			Compare := -AbsCompare(AValue)
		else
			Compare := AbsCompare(AValue);
end;
function TBigNum.Compare(AValue: Integer): Integer;
var
	t	:	TBigNum;
begin
	t := TBigNum.Create(Avalue);
	result := Compare(t);
  t.Free;
end;

procedure TBigNum.Mult10;
begin
	Realloc(Used + 1, True);
	asm
		PUSH	EDI
		PUSH	ESI
		PUSH	EBX
		MOV		EDI,Self
		MOV   ECX,[EDI.TBigNum.Used]
		OR		ECX,ECX
		JE		@@1
		MOV		EDI,[EDI.TBigNum.Value]
		MOV		ESI,10
		XOR   EBX,EBX
@@0:MOV   EAX,[EDI]
		MUL   ESI
		ADD   EAX,EBX
		MOV		[EDI],EAX
		ADC   EDX,0
		ADD		EDI,4
		MOV   EBX,EDX
		DEC		ECX
		JNE		@@0
		MOV   [EDI],EBX
@@1:POP		EBX
		POP		ESI
		POP		EDI
	end;
	CountUsed;
end;

function TBigNum.Div10: Integer;
begin
	asm
		PUSH	EDI
		PUSH	EBX
		MOV		EDI,Self
		MOV   ECX,[EDI.TBigNum.Used]
		XOR		EDX,EDX
		OR		ECX,ECX
		JE		@@1
		MOV		EDI,[EDI.TBigNum.Value]
		MOV   EDX,ECX
		LEA		EDI,[EDI+4*EDX]
		XOR   EDX,EDX
@@0:SUB		EDI,4
		MOV   EAX,[EDI]
		MOV   EBX,10
		DIV   EBX
		MOV		[EDI],EAX
		DEC		ECX
		JNE		@@0
@@1:MOV		@Result,EDX
		POP		EBX
		POP		EDI
	end;
	CountUsed;
end;

procedure TBigNum.Mult2;
begin
	if Critical then
	begin
		Realloc(Used + 1, True);
		Used := Used + 1;
	end;
	asm
		PUSH	EDI
		MOV   EDI,Self
		MOV   ECX,[EDI.TBigNum.Used]
		OR		ECX,ECX
		JE		@@1
		MOV		EDI,[EDI.TBigNum.Value]
		CLC
@@0:MOV   EAX,[EDI]
		RCL   EAX,1
		MOV		[EDI],EAX
		ADC		EAX,EAX
		ADD		EDI,4
		SHR		EAX,1
		DEC		ECX
		JNE		@@0
@@1:POP		EDI
	end;
	CountUsed;
end;

procedure TBigNum.Div2;
begin
	asm
		PUSH	EDI
		MOV   EDI,Self
		MOV   ECX,[EDI.TBigNum.Used]
		OR		ECX,ECX
		JE		@@1
		MOV   EDI,[EDI.TBigNum.Value]
		MOV   EDX,ECX
		DEC   EDX
		SHL   EDX,2
		ADD   EDI,EDX
		XOR   EDX,EDX
		CLC
@@0:MOV   EAX,[EDI]
		RCR   EAX,1
		MOV		[EDI],EAX
		ADC		EAX,EAX
		SUB		EDI,4
		SHR		EAX,1
		DEC		ECX
		JNE		@@0
@@1:POP		EDI
	end;
	CountUsed;
end;

function TBigNum.Negative: Boolean;
begin
	Result := Sign;
end;

procedure TBigNum.Swap(AValue: TBigNum);
var
	MI: Integer;
	MP: PIntArr;
	MB: Boolean;
begin
	MI := Alloc;
	Alloc := AValue.Alloc;
	AValue.Alloc := MI;
	MI := Used;
	Used := AValue.Used;
	AValue.Used := MI;
	MP := Value;
	Value := AValue.Value;
	AValue.Value := MP;
	MB := Sign;
	Sign := AValue.Sign;
	AValue.Sign := MB;
end;

function TBigNum.AbsCompare(AValue: TBigNum): Integer;
begin
	if Used > AValue.Used then
		AbsCompare := 1
	else if Used < AValue.Used then
		AbsCompare := -1
	else if Used = 0 then
		AbsCompare := 0
	else
		asm
			PUSH	EDI
			PUSH	ESI
			MOV		EDI,Self
			MOV		EDI,[EDI.TBigNum.Value]
			MOV		ESI,AValue
			MOV   ECX,[ESI.TBigNum.Used]
			MOV		ESI,[ESI.TBigNum.Value]
			MOV   EDX,ECX
			DEC   EDX
			SHL   EDX,2
			ADD   EDI,EDX
			ADD   ESI,EDX
			STD
			REPZ  CMPSD
{			MOV		EAX,[ESI]
			MOV		EDX,[EDI]
			SUB		ESI,4
			SUB		EDI,4}

			MOV   @Result,0FFFFFFFFh
			JA    @@1
			MOV   @Result,000000000h
			JE    @@1
			MOV   @Result,000000001h
@@1:  CLD
			POP   ESI
			POP		EDI
		end;
end;

procedure TBigNum.AbsIncrement(By: LongInt);
begin
	if (Used = 0) or Critical then
	begin
		Inc(Used);
		Realloc(Used, True);
	end;
	asm
		PUSH	EDI
		MOV		EDI,Self
		MOV   ECX,[EDI.TBigNum.Used]
		DEC   ECX
		MOV   EDI,[EDI.TBigNum.Value]
		ADD		EDI,4
		MOV   EAX,[EDI-4]
		ADD   EAX,By
		MOV		[EDI-4],EAX
		JNC		@@1
//jwe		OR		ECX,ECX
//jwe		JZ		@@1
@@0:MOV   EAX,[EDI]
		ADC   EAX,0
		MOV		[EDI],EAX
		ADC		EAX,EAX
		ADD		EDI,4
		SHR		EAX,1
		JC		@@0
@@1:POP		EDI
	end;
	CountUsed;
end;

procedure TBigNum.AbsDecrement(By: LongInt);
begin
	asm
		PUSH	EDI
		MOV		EDI,Self
		MOV   ECX,[EDI.TBigNum.Used]
		DEC   ECX
		MOV   EDI,[EDI.TBigNum.Value]
		CLD
		ADD		EDI,4
		MOV   EAX,[EDI-4]
		SUB   EAX,By
		MOV		[EDI-4],EAX
		OR		ECX,ECX
		JZ		@@1
@@0:MOV   EAX,[EDI]
		SBB   EAX,0
		MOV		[EDI],EAX
		ADC		EAX,EAX
		ADD		EDI,4
		SHR		EAX,1
		DEC		ECX
		JNE		@@0
@@1:POP		EDI
	end;
	CountUsed;
end;

procedure TBigNum.AbsAdd(AValue: TBigNum);
var
	RealAdds, ExtraAdds: Integer;
begin
	if AValue.Used >= Alloc then
		if AValue.Critical or (AValue.Used = Alloc) and (Alloc = Used) and Critical then
			Realloc(AValue.Used + 1, True)
		else
			if AValue.Used > Alloc then
				Realloc(AValue.Used, True)
	else if AValue.Used < Alloc then
		if (Used = Alloc) and Critical then
			Realloc(Used + 1, True);
	RealAdds := AValue.Used;
	ExtraAdds := Alloc - RealAdds;
	asm
		PUSH	EDI
		PUSH	ESI
		MOV		EDI,Self
		MOV		EDI,[EDI.TBigNum.Value]
		MOV		ESI,AValue
		MOV		ESI,[ESI.TBigNum.Value]
		MOV   ECX,RealAdds
		JCXZ  @@2
		CLD
		CLC
@@0:LODSD
		ADC   [EDI],EAX
		INC   EDI
		INC   EDI
		INC   EDI
		INC   EDI
		LOOP  @@0
		MOV   ECX,ExtraAdds
		JCXZ  @@2
@@1:ADC   DWORD PTR [EDI],0
		INC   EDI
		INC   EDI
		LOOP  @@1
@@2:POP   ESI
		POP		EDI
	end;
	CountUsed;
end;

procedure TBigNum.AbsSubtract(AValue: TBigNum);
begin
	asm
		PUSH  EDI
		PUSH	ESI
		MOV		EDI,Self
		MOV   EDX,[EDI.TBigNum.Used]
		MOV   EDI,[EDI.TBigNum.Value]
		MOV   ESI,AValue
		MOV   ECX,[ESI.TBigNum.Used]
		MOV   ESI,[ESI.TBigNum.Value]
		SUB   EDX,ECX
		JCXZ  @@2
		CLD
		CLC
@@0:LODSD
		SBB   [EDI],EAX
		INC   EDI
		INC   EDI
		INC   EDI
		INC   EDI
		LOOP  @@0
		MOV   ECX,EDX
		JCXZ  @@2
@@1:SBB   DWORD PTR [EDI],0
		INC   EDI
		INC   EDI
//jwe		LOOP  @@0
		LOOP  @@1
@@2:POP   ESI
		POP		EDI
	end;
	CountUsed;
end;

function TBigNum.AbsDivide(ADivisor: TBigNum): Boolean;
var
	Bit, Res, Divisor: TBigNum;
	NoRemainder: Boolean;
begin
	if ADivisor.Used = 0 then
		raise EBigNumDivByZero.Create('AbsDivide by zero');
	Bit := TBigNum.Create;
	Res := TBigNum.Create;
	Divisor := TBigNum.Create;
	Divisor.Assign(ADivisor);
	NoRemainder := False;
	Bit.AsLong := 1;
	Res.AsLong := 0;
	while AbsCompare(Divisor) >= 0 do
	begin
		Bit.Mult2;
		Divisor.Mult2;
	end;
	while (Bit.Value^[0] and 1 = 0) and not NoRemainder do
	begin
		Bit.Div2;
		Divisor.Div2;
		case AbsCompare(Divisor) of
			1:
			begin
				Res.BitwiseOr(Bit);
				AbsSubtract(Divisor);
			end;
			0:
			begin
				NoRemainder := True;
				Res.BitwiseOr(Bit);
				AbsSubtract(Divisor);
			end;
		end;
	end;
	AbsDivide := NoRemainder;
	Assign(Res);
	Divisor.Destroy;
	Res.Destroy;
	Bit.Destroy;
end;

function TBigNum.AbsModulo(ADivisor: TBigNum): Boolean;
var
	Divisor: TBigNum;
	NoRemainder: Boolean;
	Count: Integer;
begin
	if ADivisor.Used = 0 then
		raise EBigNumDivByZero.Create('AbsModulo by zero');
	Divisor := TBigNum.Create;
	Divisor.Assign(ADivisor);
	NoRemainder := False;
	Count := 0;
	while AbsCompare(Divisor) >= 0 do
	begin
		Inc(Count);
		Divisor.Mult2;
	end;
	while (Count <> 0) and not NoRemainder do
	begin
		Divisor.Div2;
		case AbsCompare(Divisor) of
			1:
			begin
				AbsSubtract(Divisor);
			end;
			0:
			begin
				NoRemainder := True;
				AbsSubtract(Divisor);
			end;
		end;
		Dec(Count);
	end;
	AbsModulo := NoRemainder;
	Divisor.Destroy;
end;

function TBigNum.AbsModuloLong(ADivisor: Integer): Integer;
asm
		PUSH	ESI
		PUSH	EBX
		MOV		EBX,ADivisor
		MOV		ESI,Self
		MOV		ECX,[ESI.TBigNum.Used]
		MOV		ESI,[ESI.TBigNum.Value]
		MOV		EDX,ECX
		DEC		EDX
		SHL		EDX,2
		ADD		ESI,EDX
		XOR		EDX,EDX
		STD
@@0:LODSD
		DIV		EBX
		LOOP  @@0
		MOV		EAX,EDX
		CLD
		POP		EBX
		POP		ESI
end;

procedure TBigNum.FromLong(AValue: LongInt);
begin
	if AValue < 0 then
	begin
		Sign := True;
		AValue := -AValue;
	end
	else
		Sign := False;
	if Alloc < 1 then
		Realloc(1, False);
	Move(AValue, Value^[0], SizeOf(LongInt));
	Used := 1;
	if Alloc > Used then
		FillChar(Value^[Used], (Alloc - Used) shl 2, 0);
	CountUsed;
end;

function TBigNum.ToLong: LongInt;
var
	Res: LongInt;
begin
	if (Used > 1) or (Used = 1) and Critical then
		raise EBigNumTooBig.Create('Value don''t fit in a LongInt');
	if Used = 1 then
		Res := Value^[0]
	else
		Res := 0;
	if Sign then
		AsLong := -Res
	else
		AsLong := Res;
  Result := res;
end;

procedure TBigNum.FromStr(S: string);
var
	I: Integer;
begin
	if Length(S) = 0 then
		raise EBigNumInvalidArg.Create('Not a valid number in FromStr');
	Used := 0;
	if S[1] = '-' then
	begin
		Sign := True;
		I := 2;
	end
	else
	begin
		Sign := False;
		I := 1;
	end;
	while I <= Length(S) do
	begin
		Mult10;
		if (S[I] > '9') or (S[I] < '0') then
			raise EBigNumInvalidArg.Create('Not a valid number in FromStr');
		AbsIncrement(Byte(S[I]) - Byte('0'));
		Inc(I);
	end;
end;

function TBigNum.ToStr: string;
var
	M: TBigNum;
	Res: string;
begin
	if Used = 0 then
	begin
		Result := '0';
		Exit;
	end;
	M := TBigNum.Create;
	M.Assign(Self);
	while M.Used > 0 do
		Res := Char(Byte('0') + M.Div10) + Res;
	if Sign then
		Result := '-' + Res
	else
		Result := Res;
	M.Destroy;
end;

procedure TBigNum.Realloc(Ints: Integer; Preserve: Boolean);
var
	NewValue: PIntArr;
begin
	if Ints <= Alloc then
	begin
		if Preserve then
		begin
			FillChar(Value^[Used], (Alloc - Used) shl 2, 0);
		end;
		Exit;
	end;
	if Preserve then
	begin
		GetMem(NewValue, Ints * SizeOf(Integer));
		Move(Value^, NewValue^, Used shl 2);
		FillChar(NewValue^[Used], (Ints - Used) shl 2, 0);
		FreeMem(Value, Alloc * SizeOf(Integer));
		Value := NewValue;
		Alloc := Ints;
	end
	else
	begin
		FreeMem(Value, Alloc * SizeOf(Integer));
		Alloc := Ints;
		GetMem(Value, Alloc * SizeOf(Integer));
	end;
end;

function TBigNum.Critical: Boolean;
begin
	Critical := (Used > 0) and (Value^[Used - 1] and (1 shl (SizeOf(Integer) * 8 - 1)) <> 0);
end;

function TBigNum.IsEven: Boolean;
begin
	result := (Value[0] AND 1) = 0;
end;
procedure TBigNum.CountUsed;
begin
	Used := Alloc;
	while (Used > 0) and (Value^[Used - 1] = 0) do
		Dec(Used);
end;
(*
var
	BigA, BigB: TBigNum;
	I: Integer;

begin
	BigA.Init; { Caution: Because of the new dynamic memory allocation }
	BigB.Init; {          you have to use Init and Done. }
	WriteLn('Fibonacci numbers:');
	BigA.Val('0');
	BigB.Val('1');
	for I := 1 to 370 do
	begin
		WriteLn(BigB.Str: 79);
		BigA.Add(BigB);
		BigA.Swap(BigB);
	end;
	WriteLn(BigB.Str: 79);
	WriteLn('Factorials:');
	BigA.Val('1');
	BigB.Val('1');
	for I := 1 to 49 do
	begin
		WriteLn(BigA.Str: 70, ' = ', BigB.Str, '!');
		BigB.AbsIncrement(1);
		BigA.Multiply(BigB);
	end;
	for I := 1 to 49 do
	begin
		WriteLn(BigA.Str: 70, ' = ', BigB.Str, '!');
		BigA.Divide(BigB);
		BigB.AbsDecrement(1);
	end;
	WriteLn(BigA.Str: 70, ' = ', BigB.Str, '!');
	WriteLn('Powers of 2:');
	BigA.Val('1');
	BigB.Val('2');
	for I := 1 to 250 do
	begin
		WriteLn(BigA.Str: 79);
		BigA.Multiply(BigB);
	end;
	for I := 1 to 250 do
	begin
		WriteLn(BigA.Str: 79);
		BigA.Divide(BigB);
	end;
	WriteLn(BigA.Str: 79);
	BigB.Done;
	BigA.Done;
	Write('Press enter to exit.');
	ReadLn;*)
end.


