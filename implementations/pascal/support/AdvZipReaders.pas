Unit AdvZipReaders;

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
  Windows,  // for LongWord
  SysUtils, // For Exception

  StringSupport,
  FileSupport,
  Classes,

  AdvBuffers,
  AdvExceptions,
  AdvMemories,
  AdvNameBuffers,
  AdvStreams,
  AdvVCLStreams,
  AdvZipDeclarations,
  AdvZipParts,
  AdvZipUtilities,
  AdvZipWorkers,

  ZLib;

Type
  TAdvZipReader = Class (TAdvZipWorker)
    Private
      Function EndCondition(iLongWord : LongWord) : Boolean;
      Function ReadLongWord : LongWord;
      Function ReadWord : Word;
      Function ReadByte : Byte;
      Procedure ReadPart;
      Procedure Skip(iCount : Integer);
      Function ReadString(iLength : Word):AnsiString;
      Procedure ReadData(iFlags, iComp : Word; iSizeComp, iSizeUncomp : LongWord; oBuffer : TAdvBuffer);
      Procedure ReadDeflate(iFlags : Word; iSizeComp, iSizeUncomp : LongWord; oBuffer: TAdvBuffer);
      Procedure ReadUncompressed(iSizeComp : LongWord; oBuffer: TAdvBuffer);
      Procedure ReadUnknownLengthDeflate(oBuffer : TAdvBuffer);
      Procedure ReadKnownDeflate(pIn : Pointer; iSizeComp, iSizeDecomp : LongWord; oBuffer : TAdvBuffer);
      Procedure ReadDirectory(iCount : Integer);
      Procedure ReadDigSig;
      Procedure ReadTermination;
    Public
      Procedure ReadZip; Overload; Virtual;
  End;

Implementation


Function TAdvZipReader.ReadLongWord: LongWord;
Begin
  Result := 0;
  Stream.Read(Result, SizeOf(Result));
End;

Function TAdvZipReader.ReadWord: Word;
Begin
  Result := 0;
  Stream.Read(Result, SizeOf(Result));
End;

Function TAdvZipReader.ReadByte: Byte;
Begin
  Result := 0;
  Stream.Read(Result, SizeOf(Result));
End;


Procedure TAdvZipReader.ReadPart;
Var
  iFlags : Word;
  iComp : Word;
  iSizeComp : LongWord;
  iSizeUncomp : LongWord;
  iNameLen : Word;
  iExtraLen : Word;
  iDate : Word;
  iTime : Word;
  oBuffer : TAdvZipPart;
Begin
  oBuffer := TAdvZipPart(Parts.New);
  Try
    ReadWord;                          // version needed to extract       2 bytes
    iFlags := ReadWord;               // general purpose bit flag        2 bytes
    iComp := ReadWord;                // compression method              2 bytes
    iTime := ReadWord;                // last mod file time              2 bytes
    iDate := ReadWord;                // last mod file date              2 bytes
    oBuffer.Timestamp := TimeAndDateToDateTime(iDate, iTime);
    ReadLongWord;                          // crc-32                          4 bytes
    iSizeComp := ReadLongWord;           // compressed size                 4 bytes
    iSizeUncomp := ReadLongWord;         // uncompressed size               4 bytes
    iNameLen := ReadWord;             // filename length                 2 bytes
    iExtraLen := ReadWord;            // extra field length              2 bytes
    oBuffer.Name := string(ReadString(iNameLen));    // filename (variable size)
    Skip(iExtraLen);                  // extra field (variable size)

    {Immediately following the local header for a file
      is the compressed or stored data for the file. }
    ReadData(iFlags, iComp, iSizeComp, iSizeUncomp, oBuffer);

    Parts.Add(oBuffer.Link);
  Finally
    oBuffer.Free;
  End;
End;

Procedure TAdvZipReader.ReadZip;
Var
  iSig : LongWord;
  iCount : Integer;
Begin
  Parts.Clear;
  Parts.Unsorted;

  iSig := ReadLongWord;
  While (iSig = SIG_LOCAL_FILE_HEADER) Do
  Begin
    ReadPart;
    iSig := ReadLongWord;
  End;
  iCount := 0;
  While (iSig = SIG_CENTRAL_DIRECTORY_HEADER) Do
  Begin
    ReadDirectory(iCount);
    Inc(iCount);
    iSig := ReadLongWord;
  End;
  If (iSig = SIG_DIGITAL_SIGNATURE) Then
  Begin
    ReadDigSig;
    iSig := ReadLongWord;
  End;
  If (iSig = SEG_TERMINATION) Then
  Begin
    ReadTermination;
  End;
  // we ignore the rest of the file!
End;

Procedure TAdvZipReader.ReadUncompressed(iSizeComp : LongWord; oBuffer: TAdvBuffer);
Begin
  oBuffer.Capacity := iSizeComp;
  If (iSizeComp > 0) Then
    Stream.Read(oBuffer.Data^, oBuffer.Capacity);
End;

Procedure TAdvZipReader.ReadDeflate(iFlags : Word; iSizeComp, iSizeUncomp : LongWord; oBuffer: TAdvBuffer);
Var
  pIn : PAnsiChar;
Begin
  If Bit(iFlags, flagUsesDataDescriptor) Then
    ReadUnknownLengthDeflate(oBuffer)
  Else
  Begin
    GetMem(pIn, iSizeComp+2);
    Try
      pIn[0] := AnsiChar(120);
      pIn[1] := AnsiChar(156);
      Stream.Read(pIn[2], iSizeComp);
      ReadKnownDeflate(pIn, iSizeComp+2, iSizeUncomp, oBuffer);
    Finally
      FreeMem(pIn);
    End;
  End;
End;

Function TAdvZipReader.EndCondition(iLongWord : LongWord) : Boolean;
Begin
  Result := (Stream.Readable = 0) Or // shouldn't run out - should run into the central directory
            (iLongWord = SIG_DATA_DESCRIPTOR);
End;


Procedure TAdvZipReader.ReadUnknownLengthDeflate(oBuffer : TAdvBuffer);
Var
  iCurrent : LongWord;
  iByte : Byte;
  oMem : TAdvMemoryStream;
  iSizeComp : LongWord;
  iSizeUncomp : LongWord;
Begin
  // well, we don't know how long it's going to be.
  // what we're going to do is read this a byte at a time until
  // we are at the next header or the source runs out. There's a minor chance
  // that this will terminate early (1 in 2^32)
  // then we lop off the last 12 bytes, and treat this is the decompressible
  // we can start with a 4 byte read because we know we have at least 12 bytes
  oMem := TAdvMemoryStream.Create;
  Try
    iByte := 120;
    oMem.Write(iByte, 1);
    iByte := 156;
    oMem.Write(iByte, 1);
    iCurrent := ReadLongWord;
    While (Not EndCondition(iCurrent)) Do
    Begin
      iByte := iCurrent And $FF;
      oMem.Write(iByte, 1);
      iCurrent := (iCurrent And $FFFFFF00) Shr 8 + ReadByte Shl 24;
    End;
    If iCurrent <> SIG_DATA_DESCRIPTOR Then
      Error('ReadUnknownLengthDeflate', 'Error in zip structure: Source is not terminated by a Data Descriptor');
    Skip(4);                          // crc-32                          4 bytes
    iSizeComp := ReadLongWord;           // compressed size                 4 bytes
    iSizeUncomp := ReadLongWord;         // uncompressed size               4 bytes
    {$WARNINGS OFF} // a widening notifications in this check and the assertion in ReadKnownDeflate
    If oMem.Buffer.Capacity <> iSizeComp + 2 Then
      Error( 'ReadUnknownLengthDeflate', 'Compressed length expected to be '+
        IntegerToString(iSizeComp)+' bytes but found '+IntegerToString(oMem.Buffer.Capacity)+' bytes');
    ReadKnownDeflate(oMem.Buffer.Data, iSizeComp + 2, iSizeUncomp, oBuffer);
  Finally
    oMem.Free;
  End;
End;

Type
  TPointerMemoryStream = Class (TCustomMemoryStream)
    Constructor Create(pData : Pointer; iSize : Integer);
    Function Write(Const Buffer; Count: LongInt): LongInt; Override;
  End;

Constructor TPointerMemoryStream.Create(pData : Pointer; iSize : Integer);
Begin
  Inherited Create;
  SetPointer(pData, iSize);
End;

Function TPointerMemoryStream.Write(Const Buffer; Count: Integer): LongInt;
Begin
  Raise EAdvException.Create('Should never be called');
End;


Procedure TAdvZipReader.ReadKnownDeflate(pIn : Pointer; iSizeComp, iSizeDecomp : LongWord; oBuffer : TAdvBuffer);
Var
  oSrc : TStream;
  oDecompressor : TZDecompressionStream;

{$IFOPT C+}
  iRead : Integer;
{$ENDIF}
Begin
  If iSizeDecomp > 0 Then
  Begin
    oSrc := TPointerMemoryStream.Create(pIn, iSizeComp);
    Try
      oDecompressor := TZDecompressionStream.Create(oSrc);
      Try
        oBuffer.Capacity := iSizeDecomp;

      {$IFOPT C+}
        iRead := oDecompressor.Read(oBuffer.Data^, iSizeDecomp);
        Assert(Condition(iRead = iSizeDecomp, 'ReadKnownDeflate', 'Expected to read '+IntegerToString(iSizeDecomp)+
            ' bytes, but actually found '+IntegerToString(iRead)+' bytes'));
      {$ELSE}
        oDecompressor.Read(oBuffer.Data^, iSizeDecomp);
      {$ENDIF}
      Finally
        oDecompressor.Free;
      End;
    Finally
      oSrc.Free;
    End;
  End;
End;

Procedure TAdvZipReader.ReadData(iFlags, iComp : Word; iSizeComp, iSizeUncomp: LongWord; oBuffer: TAdvBuffer);
Begin
  Case iComp Of
    METHOD_NONE: ReadUncompressed(iSizeComp, oBuffer);
    METHOD_DEFLATE: ReadDeflate(iFlags, iSizeComp, iSizeUncomp, oBuffer);
  Else
    Error('Decompress', 'Unknown Compression type '+IntegerToString(iComp));
  End;
End;

Function TAdvZipReader.ReadString(iLength: Word): AnsiString;
Begin
  SetLength(Result, iLength);
  If (iLength > 0) Then
    Stream.Read(Result[1], iLength);
End;

Procedure TAdvZipReader.Skip(iCount: Integer);
Begin
  If iCount > 0 Then
    ReadString(iCount);
End;

Procedure TAdvZipReader.ReadDigSig;
Var
  iLen : Word;
Begin
  iLen := ReadWord;
  Skip(iLen);
End;

Procedure TAdvZipReader.ReadDirectory(iCount: Integer);
Var
  oPart : TAdvZipPart;
  iNameLen : Word;
  iExtraLen : Word;
  iCommLen : Word;
Begin
  oPart := Parts.Part[iCount];
  ReadWord; //  version made by                 2 bytes    63 vs 20
  ReadWord; //  version needed to extract       2 bytes
  ReadWord; //  general purpose bit flag        2 bytes
  ReadWord; //  compression method              2 bytes
  ReadWord; //  last mod file time              2 bytes
  ReadWord; //  last mod file date              2 bytes
  ReadLongWord; //  crc-32                          4 bytes
  ReadLongWord; //  compressed size                 4 bytes
  ReadLongWord; //  uncompressed size               4 bytes
  iNameLen := ReadWord;  // filename length                 2 bytes
  iExtraLen := ReadWord; // extra field length              2 bytes   36 vs 0
  iCommLen := ReadWord; // file comment length             2 bytes
  ReadWord; // disk number start               2 bytes
  ReadWord; // internal file attributes        2 bytes
  ReadLongWord; // external file attributes        4 bytes  32 vs 0
  ReadLongWord; // relative offset of local header 4 bytes
  Skip(iNameLen);
  Skip(iExtraLen);
  oPart.Comment := string(ReadString(iCommLen));
End;

Procedure TAdvZipReader.ReadTermination;
Begin

End;

End.
