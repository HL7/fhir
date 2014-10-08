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
  AdvStreams, AdvStringMatches, AdvObjectLists, AdvObjects,
  IdSoapMsXml, Xml.xmlintf;

type
  TSourceLocation = record
    line, col : integer;
  end;

  TXmlCanonicalisationMethod = (xcmCanonicalise, xcmComments, xcmTrimWhitespace, {xcmPrefixRewrite, } xcmQNameAware);
  TXmlCanonicalisationMethodSet = set of TXmlCanonicalisationMethod;

  TXmlBuilderNamespaceList = class (TAdvStringMatch)
  private
    FDefaultNS : String;
    FNew: TStringList;
    FDefaultSet: boolean;
    procedure SetDefaultNS(const Value: String);
  public
    Constructor Create; override;
    Destructor Destroy; override;

    function link : TXmlBuilderNamespaceList; overload;
    function clone : TXmlBuilderNamespaceList; overload;

    Procedure Assign(oObject : TAdvObject); Override;

    Property DefaultNS : String read FDefaultNS write SetDefaultNS;
    Property DefaultSet : boolean read FDefaultSet write FDefaultSet;
    Property new : TStringList read FNew;
  end;

  TXmlBuilder = {abstract} class (TAdvObject)
  private
    function GetCurrentNamespaces: TXmlBuilderNamespaceList;
  Protected
    FNoHeader: Boolean;
    FCanonicalise: TXmlCanonicalisationMethodSet;
    FIsPretty: Boolean;
    FCharEncoding: String;
    FNamespaces : TAdvObjectList;
  Public
    constructor Create; override;
    destructor Destroy; override;

    procedure Start; overload; virtual; abstract;
    procedure StartFragment; overload; virtual; abstract;
    procedure Finish; overload; virtual; abstract;
    procedure Build(oStream: TStream);   overload; virtual; abstract;
    procedure Build(oStream: TAdvStream);   overload; virtual; abstract;
    function Build : String;   overload; virtual; abstract;

    function SourceLocation : TSourceLocation;  overload; virtual; abstract;
    procedure Comment(Const sContent : String); overload; virtual; abstract;
    procedure AddAttribute(Const sName, sValue : String); overload; virtual; abstract;
    procedure AddAttributeNS(Const sNamespace, sName, sValue : String); overload; virtual; abstract;
    function Tag(Const sName : String) : TSourceLocation; overload; virtual; abstract;
    function Open(Const sName : String) : TSourceLocation; overload; virtual; abstract;
    procedure Close(Const sName : String); overload; virtual; abstract;
    function Text(Const sValue : String) : TSourceLocation; overload; virtual; abstract;
    function Entity(Const sValue : String) : TSourceLocation; overload; virtual; abstract;
    function TagText(Const sName, sValue : String) : TSourceLocation; overload; virtual; abstract;
    procedure ProcessingInstruction(sName, sText : String); overload; virtual; abstract;
    procedure DocType(sText : String); overload; virtual; abstract;

    Procedure WriteXml(iElement : IXMLDomElement); overload; virtual; abstract;
    Procedure WriteXmlNode(iDoc : IXMLDOMNode); overload; virtual; abstract;

    Procedure WriteXml(iElement : IXMLNode; first : boolean); overload; virtual; abstract;
    Procedure WriteXmlNode(iNode : IXMLNode; first : boolean); overload; virtual; abstract;
    Procedure WriteXmlDocument(iDoc : IXMLDocument); overload; virtual; abstract;

    property IsPretty : boolean read FIsPretty Write FIsPretty;
    property CharEncoding : String read FCharEncoding write FCharEncoding;
    property CurrentNamespaces : TXmlBuilderNamespaceList Read GetCurrentNamespaces;
    property NoHeader : Boolean read FNoHeader write FNoHeader;
    property Canonicalise : TXmlCanonicalisationMethodSet read FCanonicalise write FCanonicalise;

    // consumer has to call this manually if it wants to change namespaes, before it starts playing with attributes or namesapces.
    // it must call pop if it calls push
    procedure NSPush;
    Procedure NSPop;
  End;

implementation


{ TXmlBuilderNamespaceList }

procedure TXmlBuilderNamespaceList.Assign(oObject: TAdvObject);
begin
  inherited;

  DefaultNS := TXmlBuilderNamespaceList(oObject).DefaultNS;
  FDefaultSet := false;
end;

function TXmlBuilderNamespaceList.clone: TXmlBuilderNamespaceList;
begin
  result := TXmlBuilderNamespaceList(Inherited Clone);
end;

constructor TXmlBuilderNamespaceList.Create;
begin
  inherited;

end;

destructor TXmlBuilderNamespaceList.Destroy;
begin

  inherited;
end;

function TXmlBuilderNamespaceList.link: TXmlBuilderNamespaceList;
begin
  result := TXmlBuilderNamespaceList(Inherited Link);
end;

procedure TXmlBuilderNamespaceList.SetDefaultNS(const Value: String);
begin
  if FDefaultNS <> Value then
    FDefaultSet := true;
  FDefaultNS := Value;
end;

{ TXmlBuilder }

constructor TXmlBuilder.Create;
begin
  inherited;
  FNamespaces := TAdvObjectList.create;
  FNamespaces.Add(TXmlBuilderNamespaceList.Create());
end;

destructor TXmlBuilder.Destroy;
begin
  FNamespaces.Free;
  inherited;
end;

function TXmlBuilder.GetCurrentNamespaces: TXmlBuilderNamespaceList;
begin
  result := FNamespaces[FNamespaces.Count - 1] as TXmlBuilderNamespaceList
end;

procedure TXmlBuilder.NSPop;
begin
  FNamespaces.DeleteByIndex(FNamespaces.Count - 1);
end;

procedure TXmlBuilder.NSPush;
begin
  FNamespaces.Add(CurrentNamespaces.clone);
end;

end.
