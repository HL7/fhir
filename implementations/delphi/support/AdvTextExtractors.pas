Unit AdvTextExtractors;

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
  SysUtils, Classes,
  StringSupport, MathSupport, EncodeSupport,
  AdvExtractors, AdvCharacterSets, AdvStreams, AdvStreamReaders,
  AdvStringBuilders;


Type
  TAdvTextExtractor = Class(TAdvStreamReader)
    Private
      FLine : Integer;
      FCache : String;

      FBuilder : TAdvStringBuilder;

    Protected
      Function ErrorClass : EAdvExceptionClass; Override;

      Procedure CacheAdd(Const sValue : String); Virtual;
      Procedure CacheRemove(Const sValue : String); Virtual;

      Procedure Error(Const sMethod, sMessage : String); Override;

    Public
      Constructor Create; Overload; Override;
      Destructor Destroy; Override;

      Function More : Boolean; Virtual;

      Function ConsumeLine : String;

      Procedure ProduceString(Const sToken : String);
      Procedure ProduceCharacter(Const cToken : Char);

      Function ConsumeCharacter : Char; Overload; Virtual;
      Function ConsumeString(Const sToken : String) : String;
      Function ConsumeCharacter(Const cToken : Char) : Char; Overload;
      Function ConsumeCharacterCount(Const iCharacterCount : Integer) : String;

      Function ConsumeWhileCharacter(Const cToken : Char) : String;
      Function ConsumeWhileCharacterSet(Const aTokenSet : TCharSet) : String; Overload;
      Function ConsumeWhileCharacterSet(Const oCharacterSet : TAdvCharacterSet) : String; Overload;

      Function ConsumeUntilCharacter(Const cToken : Char) : String;
      Function ConsumeUntilCharacterSet(Const aTokenSet : TCharSet) : String;
      Function ConsumeUntilString(Const sToken : String) : String; Overload;
      Function ConsumeUntilString(Const aStringArray : Array Of String) : String; Overload;

      Function ConsumeRestStream : String;

      Function MatchString(Const sToken : String) : Boolean;
      Function MatchStringArray(Const aTokenSet : Array Of String) : Integer;

      Function NextCharacter : Char;

      Function CacheLength : Integer;
      Function StreamPosition : Int64;

      Property Line : Integer Read FLine Write FLine;
  End;

  EAdvTextExtractor = Class(EAdvExtractor);

  EAdvExceptionClass = AdvExtractors.EAdvExceptionClass;


Implementation


Constructor TAdvTextExtractor.Create;
Begin
  Inherited;

  FBuilder := TAdvStringBuilder.Create;
End;


Destructor TAdvTextExtractor.Destroy;
Begin
  FBuilder.Free;

  Inherited;
End;


Procedure TAdvTextExtractor.CacheAdd(Const sValue: String);
Begin
  Inherited;

  Dec(FLine, StringCount(sValue, cEnter));
End;


Procedure TAdvTextExtractor.CacheRemove(Const sValue: String);
Begin
  Inherited;

  Inc(FLine, StringCount(sValue, cEnter));
End;


Function TAdvTextExtractor.ConsumeLine : String;
Begin
  Result := ConsumeUntilCharacterSet(setVertical);

  ConsumeWhileCharacterSet(setVertical);
End;


Procedure TAdvTextExtractor.Error(Const sMethod, sMessage: String);
Begin
  Inherited Error(sMethod, StringFormat('Line %d: %s', [FLine, sMessage]));
End;


Function TAdvTextExtractor.ErrorClass: EAdvExceptionClass;
Begin
  Result := EAdvTextExtractor;
End;



Procedure TAdvTextExtractor.ProduceString(Const sToken : String);
Begin
  FCache := sToken + FCache;

  CacheAdd(sToken);
End;


Procedure TAdvTextExtractor.ProduceCharacter(Const cToken : Char);
Begin
  ProduceString(cToken);
End;


Function TAdvTextExtractor.MatchString(Const sToken: String): Boolean;
Var
  iCacheLength : Integer;
  iTokenLength : Integer;
  iReadSize : Integer;
  sNextString : String;
  Buffer: SysUtils.TCharArray;
Begin
  iTokenLength := Length(sToken);
  iCacheLength := Length(FCache);
  SetLength(Buffer, iTokenLength - iCacheLength);
  iReadSize := Read(Buffer, 0, iTokenLength - iCacheLength);

  If iReadSize > 0 Then
  Begin
    SetLength(Buffer, iReadSize);
    SetString(sNextString, pchar(Buffer), Length(Buffer));
    FCache := FCache + sNextString;
  End;

  Result := StringEquals(FCache, sToken, iTokenLength);
End;


Function TAdvTextExtractor.MatchStringArray(Const aTokenSet: Array Of String): Integer;
Begin
  Result := High(aTokenSet);
  While (Result >= Low(aTokenSet)) And Not MatchString(aTokenSet[Result]) Do
    Dec(Result);
End;


Function TAdvTextExtractor.NextCharacter : Char;
var
  Buffer: SysUtils.TCharArray;
Begin
  If Length(FCache) = 0 Then
  Begin
    SetLength(Buffer, 1);
    if Read(Buffer, 0, 1) = 1 Then
      result := Buffer[0]
    Else
      result := #0;
    FCache := Result;
  End
  Else
  Begin
    Result := FCache[1];
  End;
End;


Function TAdvTextExtractor.ConsumeCharacter : Char;
Begin
  Result := NextCharacter;

  Delete(FCache, 1, 1);

  CacheRemove(Result);
End;


Function TAdvTextExtractor.ConsumeCharacter(Const cToken : Char) : Char;

  Function ToCharacter(Const cChar : Char) : String;
  Begin
    If (cChar >= ' ') And (cChar <= #127) Then
      Result := cChar
    Else
      Result := '$' + inttohex(Word(cChar), 4);
  End;

Begin
  If Not StringEquals(cToken, NextCharacter) Then
    Error('Consume(Char)', StringFormat('Expected token ''%s'' but found token ''%s''', [ToCharacter(cToken), ToCharacter(NextCharacter)]));

  Result := ConsumeCharacter;
End;


Function TAdvTextExtractor.ConsumeString(Const sToken : String) : String;
Begin
  If Not MatchString(sToken) Then
    Error('Consume(String)', StringFormat('Expected token ''%s'' but found token ''%s''', [sToken, Copy(FCache, 1, Length(sToken))]));

  Delete(FCache, 1, Length(sToken));

  CacheRemove(sToken);

  Result := sToken;
End;


Function TAdvTextExtractor.ConsumeCharacterCount(Const iCharacterCount : Integer) : String;
Var
  iLoop : Integer;
Begin
  SetLength(Result, iCharacterCount);

  For iLoop := 1 To iCharacterCount Do
    Result[iLoop] := ConsumeCharacter;
End;


Function TAdvTextExtractor.ConsumeUntilCharacterSet(Const aTokenSet: TCharSet): String;
Begin
  FBuilder.Clear;
  While More And Not CharInSet(NextCharacter, aTokenSet) Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.ConsumeUntilString(Const sToken: String): String;
Begin
  FBuilder.Clear;
  While More And Not MatchString(sToken) Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.ConsumeUntilString(Const aStringArray: Array Of String): String;
Begin
  FBuilder.Clear;
  While More And Not (MatchStringArray(aStringArray) >= 0) Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.ConsumeUntilCharacter(Const cToken : Char) : String;
Begin
  FBuilder.Clear;
  While More And (NextCharacter <> cToken) Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.ConsumeRestStream : String;
Begin
  FBuilder.Clear;
  While More Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.ConsumeWhileCharacter(Const cToken : Char) : String;
Begin
  FBuilder.Clear;
  While More And (NextCharacter = cToken) Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.ConsumeWhileCharacterSet(Const aTokenSet : TCharSet) : String;
Begin
  FBuilder.Clear;
  While More And CharInSet(NextCharacter, aTokenSet) Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.ConsumeWhileCharacterSet(Const oCharacterSet: TAdvCharacterSet): String;
Begin
  FBuilder.Clear;
  While More And oCharacterSet.ContainsValue(NextCharacter) Do
    FBuilder.Append(ConsumeCharacter);
  Result := FBuilder.AsString;
End;


Function TAdvTextExtractor.CacheLength: Integer;
Begin
  Result := Length(FCache);
End;


Function TAdvTextExtractor.StreamPosition: Int64;
Begin
  Assert(Invariants('StreamPosition', BaseStream, TAdvAccessStream, 'Stream'));

  Result := TAdvAccessStream(BaseStream).Position - CacheLength;
End;


Function TAdvTextExtractor.More : Boolean;
Begin
  Result := Not Inherited EndOfStream Or (Length(FCache) > 0);
End;


End.
