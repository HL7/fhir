unit AdvStreamReaders;

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
  SysUtils, {$IFNDEF VER130} RTLConsts, {$ENDIF} MathSupport,
  AdvObjects, AdvStreams;

Type
  TAdvTextReader = class (TAdvObject)
  public
    procedure Close; virtual; abstract;
    function Peek: Integer; virtual; abstract;
    function Read: Integer; overload; virtual; abstract;
    function Read(const Buffer: TCharArray; Index, Count: Integer): Integer; overload; virtual; abstract;
    function ReadBlock(const Buffer: TCharArray; Index, Count: Integer): Integer; virtual; abstract;
    function ReadLine: string; virtual; abstract;
    function ReadToEnd: string; virtual; abstract;

    Function ReadString(Var s : String; iLength : Integer) : Integer;
  end;

  TAdvStreamReader = class(TAdvTextReader)
  private
    FBufferedData: TStringBuilder;
    FBufferSize: Integer;
    FDetectBOM: Boolean;
    FNoDataInStream: Boolean;
    FSkipPreamble: Boolean;
    FStream: TAdvStream;
    FCursor : Integer;
    {$IFNDEF VER130}
    FEncoding: TEncoding;
    function DetectBOM(var Encoding: TEncoding; Buffer: TBytes): Integer;
    function SkipPreamble(Encoding: TEncoding; Buffer: TBytes): Integer;
    procedure FillBuffer(var Encoding: TEncoding);
    {$ENDIF}
    function GetEndOfStream: Boolean;
  protected
    Property Stream : TAdvStream read FStream;
  public
    constructor Create(aStream: TAdvStream); overload;
    constructor Create(aStream: TAdvStream; DetectBOM: Boolean); overload;
    constructor Create(const Filename: string); overload;
    constructor Create(const Filename: string; DetectBOM: Boolean); overload;
    {$IFNDEF VER130}
    constructor Create(aStream: TAdvStream; Encoding: TEncoding; DetectBOM: Boolean = False; BufferSize: Integer = 1024); overload;
    constructor Create(const Filename: string; Encoding: TEncoding; DetectBOM: Boolean = False; BufferSize: Integer = 1024); overload;
    {$ENDIF}
    destructor Destroy; override;
    procedure Close; override;
    procedure DiscardBufferedData;
//    procedure OwnStream; inline;
    function Peek: Integer; override;
    function Read: Integer; overload; override;
    function Read(const Buffer: TCharArray; Index, Count: Integer): Integer; overload; override;
    function ReadBlock(const Buffer: TCharArray; Index, Count: Integer): Integer; override;
    function ReadLine: string; override;
    function ReadToEnd: string; override;
    property BaseStream: TAdvStream read FStream;
    {$IFNDEF VER130}
    property CurrentEncoding: TEncoding read FEncoding;
    {$ENDIF}
    property EndOfStream: Boolean read GetEndOfStream;
  end;

{

  TTextWriter = class
  public
    procedure Close; virtual; abstract;
    procedure Flush; virtual; abstract;
    procedure Write(Value: Boolean); overload; virtual; abstract;
    procedure Write(Value: Char); overload; virtual; abstract;
    procedure Write(const Value: TCharArray); overload; virtual; abstract;
    procedure Write(Value: Double); overload; virtual; abstract;
    procedure Write(Value: Integer); overload; virtual; abstract;
    procedure Write(Value: Int64); overload; virtual; abstract;
    procedure Write(Value: TObject); overload; virtual; abstract;
    procedure Write(Value: Single); overload; virtual; abstract;
    procedure Write(const Value: string); overload; virtual; abstract;
    procedure Write(Value: Cardinal); overload; virtual; abstract;
    procedure Write(Value: UInt64); overload; virtual; abstract;
    procedure Write(const Format: string; Args: array of const); overload; virtual; abstract;
    procedure Write(const Value: TCharArray; Index, Count: Integer); overload; virtual; abstract;
    procedure WriteLine; overload; virtual; abstract;
    procedure WriteLine(Value: Boolean); overload; virtual; abstract;
    procedure WriteLine(Value: Char); overload; virtual; abstract;
    procedure WriteLine(const Value: TCharArray); overload; virtual; abstract;
    procedure WriteLine(Value: Double); overload; virtual; abstract;
    procedure WriteLine(Value: Integer); overload; virtual; abstract;
    procedure WriteLine(Value: Int64); overload; virtual; abstract;
    procedure WriteLine(Value: TObject); overload; virtual; abstract;
    procedure WriteLine(Value: Single); overload; virtual; abstract;
    procedure WriteLine(const Value: string); overload; virtual; abstract;
    procedure WriteLine(Value: Cardinal); overload; virtual; abstract;
    procedure WriteLine(Value: UInt64); overload; virtual; abstract;
    procedure WriteLine(const Format: string; Args: array of const); overload; virtual; abstract;
    procedure WriteLine(const Value: TCharArray; Index, Count: Integer); overload; virtual; abstract;
  end;

  TBinaryReader = class
  strict private
    FStream: TStream;
    FEncoding: TEncoding;
    FTwoBytesPerChar: Boolean;
    FCharBytes: TBytes;
    FOneChar: TCharArray;
    FMaxCharsSize: Integer;
    function InternalReadChar: Integer;
    function InternalReadChars(const Chars: TCharArray; Index, Count: Integer): Integer;
  protected
    function GetBaseStream: TStream; virtual;
    function Read7BitEncodedInt: Integer; virtual;
  public
    constructor Create(Stream: TStream; AEncoding: TEncoding = nil); overload;
    constructor Create(Stream: TStream; AEncoding: TEncoding; AOwnsStream: Boolean = False); overload;
    constructor Create(const Filename: string; Encoding: TEncoding = nil); overload;
    destructor Destroy; override;
    procedure Close; virtual;
    function PeekChar: Integer; virtual;
    function Read: Integer; overload; virtual;
    function Read(const Buffer: TCharArray; Index, Count: Integer): Integer; overload; virtual;
    function Read(const Buffer: TBytes; Index, Count: Integer): Integer; overload; virtual;
    function ReadBoolean: Boolean; virtual;
    function ReadByte: Byte; virtual;
    function ReadBytes(Count: Integer): TBytes; virtual;
    function ReadChar: Char; virtual;
    function ReadChars(Count: Integer): TCharArray; virtual;
    function ReadDouble: Double; virtual;
    function ReadShortInt: ShortInt; virtual;
    function ReadInt16: ShortInt; inline;
    function ReadInteger: Integer; virtual;
    function ReadInt32: Integer; inline;
    function ReadInt64: Int64; virtual;
    function ReadSmallInt: SmallInt; virtual;
    function ReadSByte: SmallInt; inline;
    function ReadSingle: Single; virtual;
    function ReadString: string; virtual;
    function ReadWord: Word; virtual;
    function ReadUInt16: Word; inline;
    function ReadCardinal: Cardinal; virtual;
    function ReadUInt32: Cardinal; inline;
    function ReadUInt64: UInt64; virtual;
    property BaseStream: TStream read GetBaseStream;
  end;

  TBinaryWriter = class
  strict private
    FStream: TStream;
    FEncoding: TEncoding;
    class var FNull: TBinaryWriter;
    class destructor Destroy;
    class function GetNull: TBinaryWriter; static;
  protected
    function GetBaseStream: TStream; virtual;
    procedure Write7BitEncodedInt(Value: Integer); virtual;
    constructor Create; overload;
  public
    constructor Create(Stream: TStream); overload;
    constructor Create(Stream: TStream; Encoding: TEncoding); overload;
    constructor Create(Stream: TStream; Encoding: TEncoding; AOwnsStream: Boolean); overload;
    constructor Create(const Filename: string; Append: Boolean = False); overload;
    constructor Create(const Filename: string; Append: Boolean; Encoding: TEncoding); overload;
    destructor Destroy; override;
    procedure Close; virtual;
    function Seek(const Offset: Int64; Origin: TSeekOrigin): Int64; virtual;
    procedure Write(Value: Byte); overload; virtual;
    procedure Write(Value: Boolean); overload; virtual;
    procedure Write(Value: Char); overload; virtual;
    procedure Write(const Value: TCharArray); overload; virtual;
    procedure Write(const Value: TBytes); overload; virtual;
    procedure Write(Value: Double); overload; virtual;
    procedure Write(Value: Integer); overload; virtual;
    procedure Write(Value: SmallInt); overload; virtual;
    procedure Write(Value: ShortInt); overload; virtual;
    procedure Write(Value: Word); overload; virtual;
    procedure Write(Value: Cardinal); overload; virtual;
    procedure Write(Value: Int64); overload; virtual;
    procedure Write(Value: Single); overload; virtual;
    procedure Write(const Value: string); overload; virtual;
    procedure Write(Value: UInt64); overload; virtual;
    procedure Write(const Value: TCharArray; Index, Count: Integer); overload; virtual;
    procedure Write(const Value: TBytes; Index, Count: Integer); overload; virtual;
    property BaseStream: TStream read GetBaseStream;
    class property Null: TBinaryWriter read GetNull;
  end;

  TStringReader = class(TTextReader)
  private
    FData: string;   //String Data being read
    FIndex: Integer; //Next character index to be read
  public
    constructor Create(S: string);
    procedure Close; override;
    function Peek: Integer; override;
    function Read: Integer; overload; override;
    function Read(const Buffer: TCharArray; Index, Count: Integer): Integer; overload; override;
    function ReadBlock(const Buffer: TCharArray; Index, Count: Integer): Integer; override;
    function ReadLine: string; override;
    function ReadToEnd: string; override;
  end;

  TStringWriter = class(TTextWriter)
  private
    FBuilder: TStringBuilder;
    FOwnsBuilder: Boolean;
  public
    constructor Create; overload;
    constructor Create(Builder: TStringBuilder); overload;
    destructor Destroy; override;
    procedure Close; override;
    procedure Flush; override;
    procedure Write(Value: Boolean); override;
    procedure Write(Value: Char); override;
    procedure Write(const Value: TCharArray); override;
    procedure Write(Value: Double); override;
    procedure Write(Value: Integer); override;
    procedure Write(Value: Int64); override;
    procedure Write(Value: TObject); override;
    procedure Write(Value: Single); override;
    procedure Write(const Value: string); override;
    procedure Write(Value: Cardinal); override;
    procedure Write(Value: UInt64); override;
    procedure Write(const Format: string; Args: array of const); override;
    procedure Write(const Value: TCharArray; Index, Count: Integer); override;
    procedure WriteLine; override;
    procedure WriteLine(Value: Boolean); override;
    procedure WriteLine(Value: Char); override;
    procedure WriteLine(const Value: TCharArray); override;
    procedure WriteLine(Value: Double); override;
    procedure WriteLine(Value: Integer); override;
    procedure WriteLine(Value: Int64); override;
    procedure WriteLine(Value: TObject); override;
    procedure WriteLine(Value: Single); override;
    procedure WriteLine(const Value: string); override;
    procedure WriteLine(Value: Cardinal); override;
    procedure WriteLine(Value: UInt64); override;
    procedure WriteLine(const Format: string; Args: array of const); override;
    procedure WriteLine(const Value: TCharArray; Index, Count: Integer); override;
    function ToString: string; override;
  end;

  TStreamWriter = class(TTextWriter)
  private
    FStream: TStream;
    FEncoding: TEncoding;
    FNewLine: string;
    FAutoFlush: Boolean;
    FBufferIndex: Integer;
    FBuffer: TBytes;
    procedure WriteBytes(Bytes: TBytes);
  public
    constructor Create(Stream: TStream); overload;
    constructor Create(Stream: TStream; Encoding: TEncoding; BufferSize: Integer = 1024); overload;
    constructor Create(const Filename: string; Append: Boolean = False); overload;
    constructor Create(const Filename: string; Append: Boolean; Encoding: TEncoding; BufferSize: Integer = 1024); overload;
    destructor Destroy; override;
    procedure Close; override;
    procedure Flush; override;
    procedure OwnStream; inline;
    procedure Write(Value: Boolean); override;
    procedure Write(Value: Char); override;
    procedure Write(const Value: TCharArray); override;
    procedure Write(Value: Double); override;
    procedure Write(Value: Integer); override;
    procedure Write(Value: Int64); override;
    procedure Write(Value: TObject); override;
    procedure Write(Value: Single); override;
    procedure Write(const Value: string); override;
    procedure Write(Value: Cardinal); override;
    procedure Write(Value: UInt64); override;
    procedure Write(const Format: string; Args: array of const); override;
    procedure Write(const Value: TCharArray; Index, Count: Integer); override;
    procedure WriteLine; override;
    procedure WriteLine(Value: Boolean); override;
    procedure WriteLine(Value: Char); override;
    procedure WriteLine(const Value: TCharArray); override;
    procedure WriteLine(Value: Double); override;
    procedure WriteLine(Value: Integer); override;
    procedure WriteLine(Value: Int64); override;
    procedure WriteLine(Value: TObject); override;
    procedure WriteLine(Value: Single); override;
    procedure WriteLine(const Value: string); override;
    procedure WriteLine(Value: Cardinal); override;
    procedure WriteLine(Value: UInt64); override;
    procedure WriteLine(const Format: string; Args: array of const); override;
    procedure WriteLine(const Value: TCharArray; Index, Count: Integer); override;
    property AutoFlush: Boolean read FAutoFlush write FAutoFlush;
    property NewLine: string read FNewLine write FNewLine;
    property Encoding: TEncoding read FEncoding;
    property BaseStream: TStream read FStream;
  end;

}


implementation

Uses
  AdvFiles;

{ TAdvStreamReader }

constructor TAdvStreamReader.Create(aStream: TAdvStream);
begin
  Create(aStream, TEncoding.UTF8, True);
end;

constructor TAdvStreamReader.Create(aStream: TAdvStream; DetectBOM: Boolean);
begin
  Create(aStream, TEncoding.UTF8, DetectBOM);
end;

constructor TAdvStreamReader.Create(aStream: TAdvStream; Encoding: TEncoding; DetectBOM: Boolean = False; BufferSize: Integer = 1024);
begin
  Create;

  if not Assigned(aStream) then
    raise EArgumentException.CreateResFmt(@SParamIsNil, ['Stream']); // DO NOT LOCALIZE
  if not Assigned(Encoding) then
    raise EArgumentException.CreateResFmt(@SParamIsNil, ['Encoding']); // DO NOT LOCALIZE

  FBufferedData := TStringBuilder.Create;
  FEncoding := Encoding;
  FBufferSize := BufferSize;
  if FBufferSize < 12 then
    FBufferSize := 12;
  FNoDataInStream := False;
  FStream := aStream;
  FDetectBOM := DetectBOM;
  FSkipPreamble := not FDetectBOM;
  FCursor := 0;
end;

constructor TAdvStreamReader.Create(const Filename: string; Encoding: TEncoding; DetectBOM: Boolean = False; BufferSize: Integer = 1024);
var
  oFile : TAdvFile;
begin
  oFile := TAdvFile.Create;
  Try
    oFile.OpenRead;
    Create(oFile.Link, Encoding, DetectBOM, BufferSize);
  Finally
    oFile.Free;
  End;
end;

constructor TAdvStreamReader.Create(const Filename: string; DetectBOM: Boolean);
begin
  Create(Filename, TEncoding.UTF8, DetectBOM);
end;

constructor TAdvStreamReader.Create(const Filename: string);
begin
  Create(Filename, TEncoding.UTF8, true);
end;

destructor TAdvStreamReader.Destroy;
begin
  Close;
  inherited;
end;

procedure TAdvStreamReader.Close;
begin
  FStream.Free;
  FStream := nil;

  if FBufferedData <> nil then
  begin
    FBufferedData.Free;
    FBufferedData := nil;
  end;
end;

procedure TAdvStreamReader.DiscardBufferedData;
begin
  if FBufferedData <> nil then
  begin
    FBufferedData.Remove(0, FBufferedData.Length);
    FNoDataInStream := False;
  end;
end;

function TAdvStreamReader.DetectBOM(var Encoding: TEncoding; Buffer: TBytes): Integer;
var
  LEncoding: TEncoding;
begin
  // try to automatically detect the buffer encoding
  LEncoding := nil;
  Result := TEncoding.GetBufferEncoding(Buffer, LEncoding);

  // detected encoding points to Default and param Encoding requests some other
  // type of Encoding; set the Encoding param to UTF8 as it can also read ANSI (Default)
  if (LEncoding = TEncoding.Default) and (Encoding <> TEncoding.Default) then
    Encoding := TEncoding.UTF8
  else
    Encoding := LEncoding;

  FDetectBOM := False;
end;

procedure TAdvStreamReader.FillBuffer(var Encoding: TEncoding);
const
  BufferPadding = 4;
var
  LString: string;
  LBuffer: TBytes;
  BytesRead: Integer;
  StartIndex: Integer;
  ByteCount: Integer;
  ByteBufLen: Integer;
begin
  SetLength(LBuffer, FBufferSize + BufferPadding);

  // Read data from stream
  BytesRead := IntegerMin(FBufferSize, FStream.Readable);
  FStream.Read(LBuffer[0], BytesRead);
  inc(FCursor, BytesRead);
  FNoDataInStream := FStream.Readable = 0;

  // Check for byte order mark and calc start index for character data
  if FDetectBOM then
    StartIndex := DetectBOM(Encoding, LBuffer)
  else if FSkipPreamble then
    StartIndex := SkipPreamble(Encoding, LBuffer)
  else
    StartIndex := 0;

  // Convert to string and calc byte count for the string
  ByteBufLen := BytesRead - StartIndex;
  try
  LString := FEncoding.GetString(LBuffer, StartIndex, ByteBufLen);
  except
    on e : Exception do
      raise Exception.Create(e.message + ' between bytes '+inttostr(FCursor-ByteBufLen)+' and '+inttostr(FCursor));
  end;
  ByteCount := FEncoding.GetByteCount(LString);

  // If byte count <> number of bytes read from the stream
  // the buffer boundary is mid-character and additional bytes
  // need to be read from the stream to complete the character
  while ByteCount <> ByteBufLen do
  begin
    // Expand buffer if padding is used
    if (StartIndex + ByteBufLen) = Length(LBuffer) then
      SetLength(LBuffer, Length(LBuffer) + BufferPadding);

    // Read one more byte from the stream into the
    // buffer padding and convert to string again
    BytesRead := IntegerMin(FBufferSize, FStream.Readable);
    if BytesRead = 0 then
      // End of stream, append what's been read and discard remaining bytes
      Break
    Else
    begin
      FStream.Read(LBuffer[StartIndex + ByteBufLen], 1);
      inc(FCursor, 1);
    end;
    Inc(ByteBufLen);
    LString := FEncoding.GetString(LBuffer, StartIndex, ByteBufLen);
    ByteCount := FEncoding.GetByteCount(LString);
  end;

  // Add string to character data buffer
  FBufferedData.Append(LString);
end;

function TAdvStreamReader.GetEndOfStream: Boolean;
begin
  if not FNoDataInStream and (FBufferedData <> nil) and (FBufferedData.Length < 1) then
    FillBuffer(FEncoding);
  Result := FNoDataInStream and ((FBufferedData = nil) or (FBufferedData.Length = 0));
end;

function TAdvStreamReader.Peek: Integer;
begin
  Result := -1;
  if (FBufferedData <> nil) and (not EndOfStream) then
  begin
    if FBufferedData.Length < 1 then
      FillBuffer(FEncoding);
    Result := Integer(FBufferedData.Chars[0]);
  end;
end;

function TAdvStreamReader.Read(const Buffer: TCharArray; Index,
  Count: Integer): Integer;
begin
  Result := -1;
  if (FBufferedData <> nil) and (not EndOfStream) then
  begin
    while (FBufferedData.Length < Count) and (not EndOfStream) and (not FNoDataInStream) do
      FillBuffer(FEncoding);

    if FBufferedData.Length > Count then
      Result := Count
    else
      Result := FBufferedData.Length;

    FBufferedData.CopyTo(0, Buffer, Index, Result);
    FBufferedData.Remove(0, Result);
  end;
end;

function TAdvStreamReader.ReadBlock(const Buffer: TCharArray; Index,
  Count: Integer): Integer;
begin
  Result := Read(Buffer, Index, Count);
end;

function TAdvStreamReader.Read: Integer;
begin
  Result := -1;
  if (FBufferedData <> nil) and (not EndOfStream) then
  begin
    if FBufferedData.Length < 1 then
      FillBuffer(FEncoding);
    Result := Integer(FBufferedData.Chars[0]);
    FBufferedData.Remove(0, 1);
  end;
end;

function TAdvStreamReader.ReadLine: string;
var
  NewLineIndex: Integer;
  PostNewLineIndex: Integer;
begin
  Result := '';
  if FBufferedData = nil then
    Exit;
  NewLineIndex := 0;
  PostNewLineIndex := 0;

  while True do
  begin
    if (NewLineIndex + 2 > FBufferedData.Length) and (not FNoDataInStream) then
      FillBuffer(FEncoding);

    if NewLineIndex >= FBufferedData.Length then
    begin
      if FNoDataInStream then
      begin
        PostNewLineIndex := NewLineIndex;
        Break;
      end
      else
      begin
        FillBuffer(FEncoding);
        if FBufferedData.Length = 0 then
          Break;
      end;
    end;
    if FBufferedData[NewLineIndex] = #10 then
    begin
      PostNewLineIndex := NewLineIndex + 1;
      Break;
    end
    else
    if (FBufferedData[NewLineIndex] = #13) and (NewLineIndex + 1 < FBufferedData.Length) and (FBufferedData[NewLineIndex + 1] = #10) then
    begin
      PostNewLineIndex := NewLineIndex + 2;
      Break;
    end
    else
    if FBufferedData[NewLineIndex] = #13 then
    begin
      PostNewLineIndex := NewLineIndex + 1;
      Break;
    end;

    Inc(NewLineIndex);
  end;

  Result := FBufferedData.ToString;
  SetLength(Result, NewLineIndex);
  FBufferedData.Remove(0, PostNewLineIndex);
end;

function TAdvStreamReader.ReadToEnd: string;
begin
  Result := '';
  if (FBufferedData <> nil) and (not EndOfStream) then
  begin
    repeat
      FillBuffer(FEncoding);
    until FNoDataInStream;
    Result := FBufferedData.ToString;
    FBufferedData.Remove(0, FBufferedData.Length);
  end;
end;

function TAdvStreamReader.SkipPreamble(Encoding: TEncoding; Buffer: TBytes): Integer;
var
  I: Integer;
  LPreamble: TBytes;
  BOMPresent: Boolean;
begin
  Result := 0;
  LPreamble := Encoding.GetPreamble;
  if (Length(LPreamble) > 0) then
  begin
    if Length(Buffer) >= Length(LPreamble) then
    begin
      BOMPresent := True;
      for I := 0 to Length(LPreamble) - 1 do
        if LPreamble[I] <> Buffer[I] then
        begin
          BOMPresent := False;
          Break;
        end;
      if BOMPresent then
        Result := Length(LPreamble);
    end;
  end;
  FSkipPreamble := False;
end;


{ TAdvTextReader }

function TAdvTextReader.ReadString(var s: String; iLength: Integer): Integer;
var
  oBuffer : TCharArray;
begin
  SetLength(oBuffer, iLength);
  result := ReadBlock(oBuffer, 0, iLength);
  SetString(s, pchar(oBuffer), result);
end;

end.
