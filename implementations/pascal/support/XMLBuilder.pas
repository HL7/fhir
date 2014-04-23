unit XMLBuilder;

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
  AdvObjects, AdvStreams,
  IdSoapMsXml;

type
  TSourceLocation = record
    line, col : integer;
  end;

  TXmlBuilder = {abstract} class (TAdvObject)
  private
    FNoHeader: Boolean;
  Protected
    FIsPretty: Boolean;
    FCharEncoding: String;
    FNamespace: String;
    procedure SetNamespace(const Value: String); virtual;
  Public
    procedure Start; overload; virtual; abstract;
    procedure StartFragment; overload; virtual; abstract;
    procedure Finish; overload; virtual; abstract;
    procedure Build(oStream: TStream);   overload; virtual; abstract;
    procedure Build(oStream: TAdvStream);   overload; virtual; abstract;
    function Build : String;   overload; virtual; abstract;

    function SourceLocation : TSourceLocation;  overload; virtual; abstract;
    procedure Comment(Const sContent : WideString); overload; virtual; abstract;
    procedure AddAttribute(Const sName, sValue : WideString); overload; virtual; abstract;
    procedure AddAttributeNS(Const sNamespace, sName, sValue : WideString); overload; virtual; abstract;
    function Tag(Const sName : WideString) : TSourceLocation; overload; virtual; abstract;
    function Open(Const sName : WideString) : TSourceLocation; overload; virtual; abstract;
    procedure Close(Const sName : WideString); overload; virtual; abstract;
    function Text(Const sValue : WideString) : TSourceLocation; overload; virtual; abstract;
    function Entity(Const sValue : WideString) : TSourceLocation; overload; virtual; abstract;
    function TagText(Const sName, sValue : WideString) : TSourceLocation; overload; virtual; abstract;
    Procedure WriteXml(iElement : IXMLDomElement); overload; virtual; abstract;

    property IsPretty : boolean read FIsPretty Write FIsPretty;
    property CharEncoding : String read FCharEncoding write FCharEncoding;
    property Namespace : String Read FNamespace write SetNamespace;
    property NoHeader : Boolean read FNoHeader write FNoHeader;
  End;

implementation

{ TXmlBuilder }

procedure TXmlBuilder.SetNamespace(const Value: String);
begin
  FNamespace := Value;
end;

end.
