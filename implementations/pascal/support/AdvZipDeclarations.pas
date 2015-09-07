Unit AdvZipDeclarations;

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

Const
  SIG_LOCAL_FILE_HEADER = $04034B50;
  SIG_DATA_DESCRIPTOR = $08074B50;
  SIG_CENTRAL_DIRECTORY_HEADER = $02014B50;
  SIG_DIGITAL_SIGNATURE = $05054B50;
  SEG_TERMINATION = $06054b50;

  METHOD_NONE = 0;
  METHOD_DEFLATE = 8;

Type
  TZipFlag = (
     flagEncrypted,           // Bit 0: If set, indicates that the file is encrypted.

     flagImploding1,
     flagImploding2,

     {     (For Method 6 - Imploding)
          Bit 1: If the compression method used was type 6,
                 Imploding, then this bit, if set, indicates
                 an 8K sliding dictionary was used.  If clear,
                 then a 4K sliding dictionary was used.
          Bit 2: If the compression method used was type 6,
                 Imploding, then this bit, if set, indicates
                 3 Shannon-Fano trees were used to encode the
                 sliding dictionary output.  If clear, then 2
                 Shannon-Fano trees were used.

          (For Methods 8 and 9 - Deflating)
          Bit 2  Bit 1
            0      0    Normal (-en) compression option was used.
            0      1    Maximum (-exx/-ex) compression option was used.
            1      0    Fast (-ef) compression option was used.
            1      1    Super Fast (-es) compression option was used.

          Note:  Bits 1 and 2 are undefined if the compression
                 method is any other.
         }
     flagUsesDataDescriptor,
{          Bit 3: If this bit is set, the fields crc-32, compressed
                 size and uncompressed size are set to zero in the
                 local header.  The correct values are put in the
                 data descriptor immediately following the compressed
                 data.  (Note: PKZIP version 2.04g for DOS only
                 recognizes this bit for method 8 compression, newer
                 versions of PKZIP recognize this bit for any
                 compression method.)                        }
     flagEnhancedDeflate,
                           {
          Bit 4: Reserved for use with method 8, for enhanced
                 deflating. }
     flagCompressPatched
     {
          Bit 5: If this bit is set, this indicates that the file is
                 compressed patched data.  (Note: Requires PKZIP
                 version 2.70 or greater)
      }
  );

Function Bit(iFlags : Word; aFlag : TZipFlag) : Boolean;


Implementation

Function Bit(iFlags : Word; aFlag : TZipFlag) : Boolean;
Var
  iVal : Word;
Begin
  iVal := 1 Shl Ord(aFlag);
  Result := iFlags And iVal > 0;
End;

End.
