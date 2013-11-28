unit XMLBuilder;

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
  End;

implementation

{ TXmlBuilder }

procedure TXmlBuilder.SetNamespace(const Value: String);
begin
  FNamespace := Value;
end;

end.
