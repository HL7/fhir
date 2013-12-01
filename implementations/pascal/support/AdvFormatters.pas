Unit AdvFormatters;

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
  SysUtils, StringSupport,
  AdvExceptions, AdvStreams;


Type
  TAdvFormatter = Class(TAdvStreamAdapter)
    Protected
      Function ErrorClass : EAdvExceptionClass; Overload; Override;

      Procedure SetStream(oStream : TAdvStream); Override;

    Public
      Procedure Clear; Overload; Virtual;

      Procedure ProduceBytes(Const aBytes : TBytes); Overload; Virtual;
      Procedure Produce(Const sText: String); Overload; Virtual;
  End;

  EAdvFormatter = Class(EAdvStream);


Implementation


Function TAdvFormatter.ErrorClass : EAdvExceptionClass;
Begin 
  Result := EAdvFormatter;
End;


Procedure TAdvFormatter.Clear;
Begin
End;


Procedure TAdvFormatter.ProduceBytes(Const aBytes : TBytes);
Begin
  Write(aBytes[0], Length(aBytes));
End;

Procedure TAdvFormatter.Produce(Const sText: String);
{$IFDEF VER130}
Begin
  Write(Pointer(sText)^, Length(sText));
End;
{$ELSE}
Var
  Bytes : TBytes;
Begin
  Assert(Condition(sText <> '', 'Produce', 'Text must not be empty.'));

  Bytes := SysUtils.TEncoding.UTF8.GetBytes(sText);

  Write(Bytes[0], Length(Bytes));
End;
{$ENDIF}


Procedure TAdvFormatter.SetStream(oStream: TAdvStream);
Begin
  Inherited;

  Clear;
End;


End. // AdvFormatters //
