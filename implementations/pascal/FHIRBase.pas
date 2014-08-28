unit FHIRBase;

{
Copyright (c) 2011-2014, HL7, Inc
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

{$IFDEF FHIR-DSTU}
This is the dev branch of the FHIR code
{$ENDIF}

{!ignore TFHIRObject}
{!ignore TFHIRObjectList}
{!ignore TFHIRAttribute}
{!Wrapper uses Classes,MSSEWrap}

Interface

Uses
  Classes,
  DateAndTime,
  SysUtils,
  AdvExceptions,
  AdvObjects,
  AdvObjectLists,
  AdvBuffers,
  AdvStringMatches,
  AdvStringLists,
  DateSupport,
  EncodeSupport,
  DecimalSupport;

Type
  {@Enum TFHIRCommandType
    Possible command types supported by HL7Connect FHIR interfaces
  }
  TFHIRCommandType = (
    fcmdUnknown, {@enum.value fcmdUnknown Unknown command}
    fcmdMailbox, {@enum.value fcmdMailbox Mailbox submission}
    fcmdRead, {@enum.value fcmdRead Read the resource}
    fcmdVersionRead, {@enum.value fcmdVersionRead Read a particular version of the resource}
    fcmdUpdate, {@enum.value fcmdUpdate Update the resource}
    fcmdDelete, {@enum.value fcmdDelete Delete the resource}
    fcmdHistoryInstance, {@enum.value fcmdHistory get a history for the resource}

    fcmdCreate, {@enum.value fcmdCreate create a resource}
    fcmdSearch, {@enum.value fcmdSearch search a resource type}
    fcmdHistoryType,{@enum.value fcmdUpdate get updates for the resource type}

    fcmdValidate, {@enum.value fcmdValidate validate the resource}
    fcmdConformanceStmt, {@enum.value fcmdConformanceStmt get the conformance statement for the system}
    fcmdTransaction, {@enum.value fcmdTransaction Update or create a set of resources}
    fcmdHistorySystem, {@enum.value fcmdUpdate get updates for the resource type}
    fcmdUpload, {@enum.value fcmdUpload Manual upload (Server extension)}
    fcmdGetTags, {@enum.value fcmdGetTags get a list of tags fixed to a resource version, resource, used with a resource type, or used on the system}
    fcmdUpdateTags, {@enum.value fcmdAddTags add to the list of tags attached to a resource or version}
    fcmdDeleteTags, {@enum.value fcmdDeleteTags delete from the list of tags attached to a resource or version}

    fcmdOperation, {@enum.value fcmdOperation operation, as defined in DSTU2}

    fcmdWebUI, {@enum.value fcmdWebUI Special web interface operations - not a valid FHIR operation}
    fcmdNull); {@enum.value fcmdNull Internal use only - not a valid FHIR operation}



  {@Enum TFHIRFormat
    Format support.
  }
  TFHIRFormat = (
    ffAsIs, {@enum.value ffAsIs leave the format as received/expected, or default to XML}
    ffXml, {@enum.value ffXml XML}
    ffJson,{@enum.value ffJson JSON}
    ffXhtml); {@enum.value ffXhtml XHTML - only for retrieval from the server}


  {@Enum TFHIRHtmlNodeType
    Enumeration of html node types
  }
  TFHIRHtmlNodeType = (
    fhntElement, {@enum.value fhntElement The node is an element}
    fhntText, {@enum.value fhntText The node is a text fragment}
    fhntComment, {@enum.value fhntComment The node is a comment}
    fhntDocument);{@enum.value fhntDocument The node represents a document (not used in FHIR context)}

  TFHIRAuthProvider = (apNone, apInternal, apFacebook, apGoogle, apHL7);


  TFHIRXhtmlParserPolicy = (xppAllow, xppDrop, xppReject);

Const
  FHIR_NS = 'http://hl7.org/fhir';
  FHIR_TAG_SCHEME = 'http://hl7.org/fhir/tag';
  CODES_TFHIRCommandType : array [TFHIRCommandType] of String = (
    'Unknown', 'MailBox', 'Read', 'VersionRead', 'Update', 'Delete', 'HistoryInstance', 'Create', 'Search', 'HistoryType', 'Validate', 'ConformanceStmt', 'Transaction', 'HistorySystem', 'Upload', 'GetTags', 'UpdateTags', 'DeleteTags', 'Operation', 'WebUI', 'Null');
  CODES_TFHIRHtmlNodeType : array [TFHIRHtmlNodeType] of String = ('Element', 'Text', 'Comment', 'Document');
  CODES_TFHIRFormat : Array [TFHIRFormat] of String = ('AsIs', 'XML', 'JSON', 'XHTML');
  MIMETYPES_TFHIRFormat : Array [TFHIRFormat] of String = ('', 'text/xml+fhir', 'application/json+fhir', 'text/xhtml');
  Names_TFHIRAuthProvider : Array [TFHIRAuthProvider] of String = ('', 'Custom', 'Facebook', 'Google', 'HL7');

type

  TFHIRObject = class;
  TFHIRObjectList = class;

  TFHIRProperty = class (TAdvObject)
  Private
    FName : String;
    FType : String;
    FObj : TFHIRObject;
    FList : TFHIRObjectList;
    FValue : String;
  Public
    Constructor Create(oOwner : TFHIRObject; Const sName, sType : String; oObject : TFHIRObject); Overload;
    Constructor Create(oOwner : TFHIRObject; Const sName, sType : String; oList : TFHIRObjectList); Overload;
    Constructor Create(oOwner : TFHIRObject; Const sName, sType : String; sValue : String); Overload;
    Destructor Destroy; Override;

    Property Name : String read FName;
    Property Type_ : String read FType;
    Property Obj : TFHIRObject read FObj;
    Property List : TFHIRObjectList read FList;
    Property Value : String read FValue;
  End;


  TFHIRPropertyList = class (TAdvObjectList)
  private
    Function GetProperty(iIndex : Integer) : TFHIRProperty;
  public
    Property Properties[iIndex : Integer] : TFHIRProperty read GetProperty; default;
  End;


  TFHIRPropertyIterator = class (TAdvObject)
  private
    FFocus : TFHIRObject;
    FProperties : TFHIRPropertyList;
    FCursor : Integer;
    Function GetCurrent : TFHIRProperty;
  public
    Constructor Create(oFocus : TFHIRObject; bInheritedProperties : Boolean); overload;
    Destructor Destroy; Override;
    Procedure Next;
    Procedure Reset;
    Function More : Boolean;
    Property Current : TFHIRProperty read GetCurrent;
  End;

  {$M+}
  TFHIRObject = class (TAdvObject)
  private
    FTag : TAdvObject;
    FTagValue : String;
    procedure SetTag(const Value: TAdvObject);
  protected
    Procedure GetChildrenByName(name : string; list : TFHIRObjectList); virtual;
    Procedure ListProperties(oList : TFHIRPropertyList; bInheritedProperties : Boolean); Virtual;
  public
    Destructor Destroy; override;
    function createIterator(bInheritedProperties : Boolean) : TFHIRPropertyIterator;
    procedure ListChildrenByName(name : string; list : TFHIRObjectList);
    Function PerformQuery(path : String):TFHIRObjectList;
    property Tag : TAdvObject read FTag write SetTag;
    property TagValue : String read FTagValue write FTagValue;
  end;

  TFHIRObjectList = class (TAdvObjectList)
  private
    Function GetItemN(index : Integer) : TFHIRObject;
  protected
    function ItemClass : TAdvObjectClass; override;
  public
    function Link : TFHIRObjectList; Overload;
    function Clone : TFHIRObjectList; Overload;
    Property ObjByIndex[index : Integer] : TFHIRObject read GetItemN; default;
  end;

  TFHIRObjectText = class (TFHIRObject)
  private
    FValue : String;
  protected
    Procedure ListProperties(oList : TFHIRPropertyList; bInheritedProperties : Boolean); Override;
  public
    constructor create(value : String); Overload;
    constructor create(value : TDateAndTime); Overload;
    constructor create(value : boolean); Overload;
    property value : string read FValue write FValue;
  end;

  TFHIRSearchParameters = class (TAdvStringMatch);

  TFHIRAttribute = class (TFHIRObject)
  private
    FName : String;
    FValue : String;
  protected
    Procedure ListProperties(oList : TFHIRPropertyList; bInheritedProperties : Boolean); Override;
  public
    Constructor Create(Name : String; Value : String); Overload;

    function Link : TFHIRAttribute; Overload;
    function Clone : TFHIRAttribute; Overload;
    procedure Assign(oSource : TAdvObject); override;
    property Name : String read FName write FName;
    property Value : String read FValue write FValue;
  end;

  TFHIRAttributeList = class (TFHIRObjectList)
  private
    Function GetItemN(index : Integer) : TFHIRAttribute;
  public
    Function IndexOf(value : TFHIRAttribute) : Integer;
    Function Item(index : Integer) : TFHIRAttribute;
    Function Count : Integer; Overload;
    Property Segments[index : Integer] : TFHIRAttribute read GetItemN; default;
    Function Get(name : String):String;
    Procedure SetValue(name : String; value :String);
  End;

  TFhirXHtmlNodeList = class;

  {@Class TFhirXHtmlNode
    An xhtml node. Has a type - is either an element, with a name and children,
    or a different type of node with text (usually text or comment)
  }
  {!.Net HL7Connect.Fhir.XhtmlNode}
  TFhirXHtmlNode = class (TFHIRObject)
  private
    FNodeType : TFHIRHtmlNodeType;
    FName : String;
    FAttributes : TFHIRAttributeList;
    FChildNodes : TFhirXHtmlNodeList;
    FContent : String;
    procedure SetNodeType(const Value: TFHIRHtmlNodeType);
    function GetChildNodes: TFhirXHtmlNodeList;
  protected
    Procedure GetChildrenByName(name : string; list : TFHIRObjectList); override;
    Procedure ListProperties(oList : TFHIRPropertyList; bInheritedProperties : Boolean); Override;
  public
    Constructor Create; Override;
    Destructor Destroy; Override;
    {!script hide}
    function Link : TFhirXHtmlNode; Overload;
    function Clone : TFhirXHtmlNode; Overload;
    procedure Assign(oSource : TAdvObject); override;
    property Attributes : TFHIRAttributeList read FAttributes;
    {!script show}

    {@member AsPlainText
      plain text content of html
    }
    function AsPlainText : String;

  published
    {@member NodeType
      The type of the node - fhntElement, fhntText, fhntComment, fhntDocument

      Note that documents are not encountered in FHIR resources
    }
    property NodeType : TFHIRHtmlNodeType read FNodeType write SetNodeType;

    {@member Name
      The name of the element, if the node is an element

      Note that namespaces are not supported in FHIR xhtml
    }
    property Name : String read FName write FName;

    {@member Content
      The content of the element if it is a text or comment node
    }
    property Content : String read FContent write FContent;

    {@member ChildNodes
      The children of the node, if it is an element
    }
    property ChildNodes : TFhirXHtmlNodeList read GetChildNodes;

    {@member AddText
      Add a text node to the end of the list of nodes.

      If you want more control over the node children use @ChildNodes
    }
    function AddText(content : String) : TFhirXHtmlNode;

    {@member AddComment
      Add a comment node to the end of the list of nodes.

      If you want more control over the node children use @ChildNodes
    }
    function AddComment(content : String) : TFhirXHtmlNode;

    {@member AddChild
      Add a child element to the end of the list of nodes.

      If you want more control over the node children use @ChildNodes
    }
    function AddChild(name : String) : TFhirXHtmlNode;

    {@member AddTag
      Add a child element to the end of the list of nodes.

      If you want more control over the node children use @ChildNodes
    }
    function AddTag(name : String) : TFhirXHtmlNode;

    {@member GetAttribute
      Get an attribute by it's name

      Note that namespaces are not supported in FHIR xhtml
    }
    Function GetAttribute(name : String) : String;

    {@member SetAttribute
      Set the value of an attribute. Create it if it doesn't exist

      Note that namespaces are not supported in FHIR xhtml
    }
    procedure SetAttribute(name, value : String);
  end;

  {@Class TFHIRXHtmlNodeList
    A list of Xhtml Nodes
  }
  {!.Net HL7Connect.Fhir.XHtmlNodeList}
  TFHIRXHtmlNodeList = class (TFHIRObjectList)
  private
    Function GetItemN(index : Integer) : TFHIRXHtmlNode;
    Procedure SetItemN(index : Integer; value : TFHIRXHtmlNode);
  public
    {!script hide}
    Function Link : TFHIRXHtmlNodeList; Overload;
    Function Clone : TFHIRXHtmlNodeList; Overload;
    {!script show}
    {@member Append
      Add an Xhtml Node to the end of the list.
    }
    Function Append : TFHIRXHtmlNode;
    {@member AddItem
      Add an already existing Xhtml Node to the end of the list.
    }
    Procedure AddItem(value : TFHIRXHtmlNode);
    {@member IndexOf
      See if an item is already in the list. returns -1 if not in the list
    }
    Function IndexOf(value : TFHIRXHtmlNode) : Integer;
    {@member Insert
       Insert an Xhtml node before the designated index (0 = first item)
    }
    Function Insert(index : Integer) : TFHIRXHtmlNode;
    {@member InsertItem
       Insert an existing Xhtml Node before the designated index (0 = first item)
    }
    Procedure InsertItem(index : Integer; value : TFHIRXHtmlNode);
    {@member Item
       Get the indexth Xhtml Node. (0 = first item)
    }
    Function Item(index : Integer) : TFHIRXHtmlNode;
    {@member SetItemByIndex
       Set the indexth Xhtml Node. (0 = first item)
    }
    Procedure SetItemByIndex(index : Integer; value : TFHIRXHtmlNode);
    {@member Count
      The number of items in the collection
    }
    Function Count : Integer; Overload;
    {@member remove
      Remove the indexth item. The first item is index 0.
    }
    Procedure Remove(index : Integer);
    {@member ClearItems
      Remove All Items from the list
    }
    Procedure ClearItems;
    Property Nodes[index : Integer] : TFHIRXHtmlNode read GetItemN write SetItemN; default;
  End;

  {@Class TFHIRBase
    A base FHIR element - can have an id on it
  }
  {!.Net HL7Connect.Fhir.Base}
  TFHIRBase = class (TFHIRObject)
  private
    FCommentsStart: TAdvStringList;
    FCommentsEnd: TAdvStringList;
    function GetCommentsStart: TAdvStringList;
    function GetCommentsEnd: TAdvStringList;
  protected
//    Procedure GetChildrenByName(name : string; list : TFHIRObjectList); override;
//   Procedure ListProperties(oList : TFHIRPropertyList; bInheritedProperties : Boolean); Override;
  public
    Destructor Destroy; override;
    {!script hide}
    Function Link : TFHIRBase; Overload;
    Function Clone : TFHIRBase; Overload;
    procedure Assign(oSource : TAdvObject); override;
    {!script show}
    function HasXmlCommentsStart : Boolean;
    function HasXmlCommentsEnd : Boolean;
    function HasComments : Boolean;
  published
    {@member comments
      comments from the XML stream. No support for comments in JSON
    }
    Property xml_commentsStart : TAdvStringList read GetCommentsStart;
    Property xml_commentsEnd : TAdvStringList read GetCommentsEnd;
  end;

  TFHIRBaseFactory = class (TAdvObject)
  private
  public
  end;

Implementation

Uses
  StringSupport,
  FHIRUtilities,
  FHIRTypes,
  FHIRResources;

type
  TFHIRQueryProcessor = class (TAdvObject)
  private
    FPath: String;
    FResults: TFHIRObjectList;
    FSource: TFHIRObjectList;
  public
    constructor Create; Override;
    destructor Destroy; Override;

    property source : TFHIRObjectList read FSource;
    property path : String read FPath write FPath;
    procedure execute;
    property results : TFHIRObjectList read FResults;
  end;

                   (*
{ TFHIRType }

function TFHIRType.Clone: TFHIRType;
begin
  result := TFHIRType(Inherited Clone);
end;

procedure TFHIRType.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  inherited;
end;

function TFHIRType.Link: TFHIRType;
begin
  result := TFHIRType(Inherited Link);
end;
*)
(*
{ TFHIRResourceReference }

procedure TFHIRResourceReference.Assign(oSource: TAdvObject);
begin
  inherited;
  resourceType := TFHIRResourceReference(oSource).resourceType;
  id := TFHIRResourceReference(oSource).id;
  version := TFHIRResourceReference(oSource).version;
  text := TFHIRResourceReference(oSource).text;
end;

function TFHIRResourceReference.Clone: TFHIRResourceReference;
begin
  result := TFHIRResourceReference(Inherited Clone);
end;

function TFHIRResourceReference.Link: TFHIRResourceReference;
begin
  result := TFHIRResourceReference(Inherited Link);
end;
  *)

{ TFHIRBase }

procedure TFHIRBase.Assign(oSource: TAdvObject);
begin
  inherited;
  if TFHIRBase(oSource).HasXmlCommentsStart then
    xml_commentsStart.assign(TFHIRBase(oSource).xml_commentsStart)
  else if FCommentsStart <> nil then
  begin
    FCommentsStart.free;
    FCommentsStart := nil;
  end;
  if TFHIRBase(oSource).HasXmlCommentsEnd then
    xml_commentsEnd.assign(TFHIRBase(oSource).xml_commentsEnd)
  else if FCommentsEnd <> nil then
  begin
    FCommentsEnd.free;
    FCommentsEnd := nil;
  end;
end;

function TFHIRBase.Clone: TFHIRBase;
begin
  result := TFHIRBase(Inherited Clone);
end;

destructor TFHIRBase.Destroy;
begin
  FCommentsStart.Free;
  FCommentsEnd.Free;
  inherited;
end;

function TFHIRBase.GetCommentsStart: TAdvStringList;
begin
  if FCommentsStart = nil then
    FCommentsStart := TAdvStringList.Create;
  result := FCommentsStart;
end;

function TFHIRBase.HasXmlCommentsStart: Boolean;
begin
  result := (FCommentsStart <> nil) and (FCommentsStart.count > 0);
end;

function TFHIRBase.GetCommentsEnd: TAdvStringList;
begin
  if FCommentsEnd = nil then
    FCommentsEnd := TAdvStringList.Create;
  result := FCommentsEnd;
end;

function TFHIRBase.HasComments: Boolean;
begin
  result := HasXmlCommentsStart or HasXmlCommentsEnd;
end;

function TFHIRBase.HasXmlCommentsEnd: Boolean;
begin
  result := (FCommentsEnd <> nil) and (FCommentsEnd.count > 0);
end;

function TFHIRBase.Link: TFHIRBase;
begin
  result := TFHIRBase(Inherited Link);
end;
(*
{ TFHIRString }

procedure TFHIRString.Assign(oSource: TAdvObject);
begin
  inherited;
  FValue := TFHIRString(oSource).FValue;
end;

function TFHIRString.Clone: TFHIRString;
begin
  result := TFHIRString(inherited Clone);
end;

constructor TFHIRString.Create(value: String);
begin
  Create;
  FValue := value;
end;

procedure TFHIRString.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  inherited;
  if (name = 'text()') then
    list.add(TFHIRObjectText.create(value));
end;

function TFHIRString.Link: TFHIRString;
begin
  result := TFHIRString(inherited Link);
end;

procedure TFHIRString.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'content', 'string', FValue));
end;

{ TFHIRCode }

function TFHIRCode.Clone: TFHIRCode;
begin
  result := TFHIRCode(inherited Clone);
end;

function TFHIRCode.Link: TFHIRCode;
begin
  result := TFHIRCode(inherited Link);
end;

{ TFHIRBoolean }

procedure TFHIRBoolean.Assign(oSource: TAdvObject);
begin
  inherited;
  FValue := TFHIRBoolean(oSource).FValue;
end;

function TFHIRBoolean.Clone: TFHIRBoolean;
begin
  result := TFHIRBoolean(inherited Clone);
end;

constructor TFHIRBoolean.Create(value: boolean);
begin
  Create;
  FValue := value;
end;

procedure TFHIRBoolean.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  inherited;
  if (name = 'text()') then
    list.add(TFHIRObjectText.create(lowercase(BooleantoString(value))));
end;

function TFHIRBoolean.Link: TFHIRBoolean;
begin
  result := TFHIRBoolean(inherited Link);
end;

procedure TFHIRBoolean.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'content', 'string', BooleanToString(FValue)));
end;

{ TFHIRInteger }

procedure TFHIRInteger.Assign(oSource: TAdvObject);
begin
  inherited;
  FValue := TFHIRInteger(oSource).FValue;
end;

function TFHIRInteger.Clone: TFHIRInteger;
begin
  result := TFHIRInteger(inherited Clone);
end;

procedure TFHIRInteger.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  inherited;
  if (name = 'text()') then
    list.add(TFHIRObjectText.create(inttostr(value)));
end;

function TFHIRInteger.Link: TFHIRInteger;
begin
  result := TFHIRInteger(inherited Link);
end;

procedure TFHIRInteger.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'content', 'string', IntToStr(FValue)));
end;

{ TFHIRInstant }

procedure TFHIRInstant.Assign(oSource: TAdvObject);
begin
  inherited;
  FValue := TFHIRInstant(oSource).FValue;
end;

function TFHIRInstant.Clone: TFHIRInstant;
begin
  result := TFHIRInstant(inherited Clone);
end;

constructor TFHIRInstant.Create(aValue: TDateAndTime);
begin
  Create;
  FValue := avalue;
end;

constructor TFHIRInstant.CreateUTC(aValue: TDateTime);
begin
  Create;
  FValue := TDateAndTime.create;
  FValue.SetDateTime(avalue);
  FValue.TimezoneType := dttzUTC;
end;

destructor TFHIRInstant.Destroy;
begin
  FValue.Free;
  inherited;
end;

procedure TFHIRInstant.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  inherited;
  if (name = 'text()') and (value <> nil) then
    list.add(TFHIRObjectText.create(value.AsXML));
end;

function TFHIRInstant.Link: TFHIRInstant;
begin
  result := TFHIRInstant(inherited Link);
end;

procedure TFHIRInstant.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'content', 'string', FValue.asxml));
end;

procedure TFHIRInstant.SetValue(const Value: TDateAndTime);
begin
  FValue.Free;
  FValue := Value;
end;

{ TFHIRDecimal }

procedure TFHIRDecimal.Assign(oSource: TAdvObject);
begin
  inherited;
  FValue := TFHIRDecimal(oSource).FValue.Link;
end;

function TFHIRDecimal.Clone: TFHIRDecimal;
begin
  result := TFHIRDecimal(inherited Clone);
end;

procedure TFHIRDecimal.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  inherited;
  if (name = 'text()') and (value <> nil) then
    list.add(TFHIRObjectText.create(value.AsString));
end;

function TFHIRDecimal.Link: TFHIRDecimal;
begin
  result := TFHIRDecimal(inherited Link);
end;

procedure TFHIRDecimal.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'content', 'string', FValue.AsString));
end;

{ TFHIRBytes }

procedure TFHIRBytes.Assign(oSource: TAdvObject);
begin
  inherited;
  FValue := TFHIRBytes(oSource).FValue.Link;
end;

function TFHIRBytes.Clone: TFHIRBytes;
begin
  result := TFHIRBytes(inherited Clone);
end;

procedure TFHIRBytes.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  inherited;
  if (name = 'text()') then
    list.add(TFHIRObjectText.create(EncodeBase64(value.asbytes)));
end;

function TFHIRBytes.Link: TFHIRBytes;
begin
  result := TFHIRBytes(inherited Link);
end;


procedure TFHIRBytes.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'content', 'string', FValue.AsBase64));
end;
  *)

{ TFHIRXHtmlNodeList }
procedure TFHIRXHtmlNodeList.AddItem(value: TFHIRXHtmlNode);
begin
  add(value.Link);
end;

function TFHIRXHtmlNodeList.Append: TFHIRXHtmlNode;
begin
  result := TFhirXHtmlNode.create;
  try
    add(result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRXHtmlNodeList.ClearItems;
begin
  Clear;
end;

function TFHIRXHtmlNodeList.Clone: TFHIRXHtmlNodeList;
begin
  result := TFHIRXHtmlNodeList(inherited Clone);
end;

function TFHIRXHtmlNodeList.Count: Integer;
begin
  result := Inherited Count;
end;

function TFHIRXHtmlNodeList.GetItemN(index: Integer): TFHIRXHtmlNode;
begin
  result := TFHIRXHtmlNode(ObjectByIndex[index]);
end;

function TFHIRXHtmlNodeList.IndexOf(value: TFHIRXHtmlNode): Integer;
begin
  result := IndexByReference(value);
end;

function TFHIRXHtmlNodeList.Insert(index: Integer): TFHIRXHtmlNode;
begin
  result := TFhirXHtmlNode.create;
  try
    inherited insert(index, result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRXHtmlNodeList.InsertItem(index: Integer; value: TFHIRXHtmlNode);
begin
  Inherited Insert(index, value.Link);
end;

function TFHIRXHtmlNodeList.Item(index: Integer): TFHIRXHtmlNode;
begin
  result := TFHIRXHtmlNode(ObjectByIndex[index]);
end;

function TFHIRXHtmlNodeList.Link: TFHIRXHtmlNodeList;
begin
  result := TFHIRXHtmlNodeList(inherited Link);
end;

procedure TFHIRXHtmlNodeList.Remove(index: Integer);
begin
  DeleteByIndex(index);
end;

procedure TFHIRXHtmlNodeList.SetItemByIndex(index: Integer; value: TFHIRXHtmlNode);
begin
  Nodes[index] := value.Link;
end;

procedure TFHIRXHtmlNodeList.SetItemN(index: Integer; value: TFHIRXHtmlNode);
begin
  ObjectByIndex[index] := value;
end;

{ TFHIRAttributeList }
function TFHIRAttributeList.Count: Integer;
begin
  result := Inherited Count;
end;

function TFHIRAttributeList.Get(name: String): String;
var
  i : integer;
begin
  result := '';
  for i := 0 to Count - 1 do
    if GetItemN(i).Name = name then
      result := GetItemN(i).Value;
end;

function TFHIRAttributeList.GetItemN(index: Integer): TFHIRAttribute;
begin
  result := TFHIRAttribute(ObjectByIndex[index]);
end;

function TFHIRAttributeList.IndexOf(value: TFHIRAttribute): Integer;
begin
  result := IndexByReference(value);
end;

function TFHIRAttributeList.Item(index: Integer): TFHIRAttribute;
begin
  result := TFHIRAttribute(ObjectByIndex[index]);
end;

procedure TFHIRAttributeList.SetValue(name, value: String);
var
  i : integer;
  b : boolean;
  attr : TFHIRAttribute;
begin
  b := false;
  for i := 0 to Count - 1 do
    if GetItemN(i).Name = name then
    begin
      b := true;
      GetItemN(i).Value := value;
    end;
  if not b then
  begin
    attr := TFHIRAttribute.create;
    try
      attr.name := name;
      attr.value := value;
      add(attr.link);
    finally
      attr.free;
    end;
  end;
end;

{ TFHIRAttribute }

procedure TFHIRAttribute.Assign(oSource: TAdvObject);
begin
  inherited;
  FName := TFHIRAttribute(oSource).FName;
  FValue := TFHIRAttribute(oSource).FValue;
end;

function TFHIRAttribute.Clone: TFHIRAttribute;
begin
  result := TFHIRAttribute(inherited Clone);
end;

constructor TFHIRAttribute.Create(Name, Value: String);
begin
  Create;
  FName := Name;
  FValue := Value;
end;

function TFHIRAttribute.Link: TFHIRAttribute;
begin
  result := TFHIRAttribute(inherited Link);
end;

procedure TFHIRAttribute.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'name', 'string', FName));
  oList.add(TFHIRProperty.create(self, 'value', 'string', FValue));
end;

{ TFhirXHtmlNode }

function TFhirXHtmlNode.AddChild(name: String): TFhirXHtmlNode;
var
  node : TFhirXHtmlNode;
begin
  node := TFhirXHtmlNode.create;
  try
    node.NodeType := fhntElement;
    node.FName := name;
    ChildNodes.add(node.Link);
    result := node;
  finally
    node.free;
  end;
end;

function TFhirXHtmlNode.AddComment(content: String): TFhirXHtmlNode;
var
  node : TFhirXHtmlNode;
begin
  node := TFhirXHtmlNode.create;
  try
    node.NodeType := fhntComment;
    node.FContent := content;
    ChildNodes.add(node.Link);
    result := node;
  finally
    node.free;
  end;
end;

function TFhirXHtmlNode.AddTag(name: String): TFhirXHtmlNode;
begin
  result := AddChild(name);
end;

function TFhirXHtmlNode.AddText(content : String): TFhirXHtmlNode;
var
  node : TFhirXHtmlNode;
begin
  if content = '' then
    result := nil
  else
  begin
    node := TFhirXHtmlNode.create;
    try
      node.NodeType := fhntText;
      node.FContent := content;
      ChildNodes.add(node.Link);
      result := node;
    finally
      node.free;
    end;
  end;
end;

function TFhirXHtmlNode.AsPlainText: String;
var
  s : String;
  i : integer;
begin
  case NodeType of
    fhntText : result := Content;
    fhntComment : result := '';
  else // fhntElement, fhntDocument
    s := '';
    for i := 0 to ChildNodes.count - 1 do
      s := s + ChildNodes[i].AsPlainText;
    if (Name = 'p') or (Name = 'h2') or (Name = 'h3') or (Name = 'h4') or (Name = 'h5') or (Name = 'h6') or (name='div') then
      result := s + #13#10
    else if Name = 'li' then
      result := '* '+ s +#13#10
    else
      result := s;
  end;
end;

procedure TFhirXHtmlNode.Assign(oSource: TAdvObject);
begin
  inherited;
  NodeType := TFhirXHtmlNode(oSource).FNodeType;
  FName := TFhirXHtmlNode(oSource).FName;
  FContent := TFhirXHtmlNode(oSource).FContent;
  if TFhirXHtmlNode(oSource).Attributes <> nil Then
    FAttributes.assign(TFhirXHtmlNode(oSource).Attributes);
  if TFhirXHtmlNode(oSource).FChildNodes <> nil then
    ChildNodes.assign(TFhirXHtmlNode(oSource).FChildNodes);
end;

function TFhirXHtmlNode.Clone: TFhirXHtmlNode;
begin
  result := TFhirXHtmlNode(inherited Clone);
end;

constructor TFhirXHtmlNode.Create;
begin
  inherited;
end;

destructor TFhirXHtmlNode.Destroy;
begin
   FChildNodes.Free;
  FAttributes.Free;
  inherited;
end;

function TFhirXHtmlNode.GetAttribute(name: String): String;
var
  i : integer;
begin
  result := '';
  for i := 0 to FAttributes.Count - 1 Do
    if FAttributes[i].Name = name then
    begin
      result := FAttributes[i].Value;
      exit;
    end;
end;

function TFhirXHtmlNode.GetChildNodes: TFhirXHtmlNodeList;
begin
  if FChildNodes = nil then
    FChildNodes := TFhirXHtmlNodeList.create;
  result := FChildNodes;
end;

procedure TFhirXHtmlNode.GetChildrenByName(name: string; list: TFHIRObjectList);
var
  i : integer;
begin
  inherited;
  for i := 0 to FAttributes.Count - 1 do
    if name = '@'+FAttributes[i].FName then
      list.add(FAttributes[i].Link);
  for i := 0 to FChildNodes.Count - 1 do
    if name = FChildNodes[i].FName then
      list.add(FChildNodes[i].Link);
  if name = 'text()' then
    list.add(TFHIRObjectText.create(FContent));
end;

function TFhirXHtmlNode.Link: TFhirXHtmlNode;
begin
  result := TFhirXHtmlNode(inherited Link);
end;

procedure TFhirXHtmlNode.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'type', 'string', CODES_TFHIRHtmlNodeType[FNodeType]));
  oList.add(TFHIRProperty.create(self, 'name', 'string', FName));
  oList.add(TFHIRProperty.create(self, 'attribute', 'Attribute', FAttributes.Link));
  oList.add(TFHIRProperty.create(self, 'childNode', 'Node', FChildNodes.Link));
  oList.add(TFHIRProperty.create(self, 'content', 'string', FContent));
end;

procedure TFhirXHtmlNode.SetAttribute(name, value: String);
var
  i : integer;
begin
  for i := 0 to FAttributes.Count - 1 Do
    if FAttributes[i].Name = name then
    begin
      FAttributes[i].Value := value;
      exit;
    end;
  FAttributes.add(TFHIRAttribute.create(name, value));
end;

procedure TFhirXHtmlNode.SetNodeType(const Value: TFHIRHtmlNodeType);
begin
  FNodeType := Value;
  if FNodeType = fhntElement then
  begin
    FChildNodes := TFhirXHtmlNodeList.create;
    FAttributes := TFHIRAttributeList.create;
  end;
end;

(*
{ TFHIRResourceReferenceList }
procedure TFHIRResourceReferenceList.AddItem(value: TFHIRResourceReference);
begin
  add(value.Link);
end;

function TFHIRResourceReferenceList.Append: TFHIRResourceReference;
begin
  result := TFhirResourceReference.create;
  try
    add(result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRResourceReferenceList.ClearItems;
begin
  Clear;
end;

function TFHIRResourceReferenceList.Clone: TFHIRResourceReferenceList;
begin
  result := TFHIRResourceReferenceList(inherited Clone);
end;

function TFHIRResourceReferenceList.Count: Integer;
begin
  result := Inherited Count;
end;

function TFHIRResourceReferenceList.GetItemN(index: Integer): TFHIRResourceReference;
begin
  result := TFHIRResourceReference(ObjectByIndex[index]);
end;

function TFHIRResourceReferenceList.IndexOf(value: TFHIRResourceReference): Integer;
begin
  result := IndexByReference(value);
end;

function TFHIRResourceReferenceList.Insert(index: Integer): TFHIRResourceReference;
begin
  result := TFhirResourceReference.create;
  try
    inherited insert(index, result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRResourceReferenceList.InsertItem(index: Integer; value: TFHIRResourceReference);
begin
  Inherited Insert(index, value.Link);
end;

function TFHIRResourceReferenceList.Item(index: Integer): TFHIRResourceReference;
begin
  result := TFHIRResourceReference(ObjectByIndex[index]);
end;

function TFHIRResourceReferenceList.Link: TFHIRResourceReferenceList;
begin
  result := TFHIRResourceReferenceList(inherited Link);
end;

procedure TFHIRResourceReferenceList.Remove(index: Integer);
begin
  DeleteByIndex(index);
end;

procedure TFHIRResourceReferenceList.SetItemByIndex(index: Integer; value: TFHIRResourceReference);
begin
  References[index] := value.Link;
end;


procedure TFHIRResourceReferenceList.SetItemN(index: Integer; const value: TFHIRResourceReference);
begin
  ObjectByIndex[index] := value;
end;
*)

{ TFHIRBaseFactory }
(*
function TFHIRBaseFactory.makeBoolean(value: Boolean): TFHIRBoolean;
begin
  result := TFHIRBoolean.create;
  result.value := value;
end;

function TFHIRBaseFactory.makeBytesFromChars(value: String): TFHIRBytes;
begin
  result := TFHIRBytes.create;
  result.value := TAdvBuffer.create;
  result.value.AsText := value;
end;

function TFHIRBaseFactory.makeBytesFromFile(value: String): TFHIRBytes;
begin
  result := TFHIRBytes.create;
  result.value := TAdvBuffer.create;
  result.value.LoadFromFileName(value);
end;

function TFHIRBaseFactory.makeBytesFromStream(value: TStream): TFHIRBytes;
begin
  result := TFHIRBytes.create;
  result.value := TAdvBuffer.create;
  result.value.LoadFromStream(value);
end;

function TFHIRBaseFactory.makeDateTimeHL7(value: String): TDateAndTime;
begin
  result := TDateAndTime.CreateHL7(value);
end;

function TFHIRBaseFactory.makeDecimal(value: String): TFHIRDecimal;
begin
  result := TFHIRDecimal.create;
  result.value := TSmartDecimal.create(value);
end;

function TFHIRBaseFactory.makeInstant(value: TDateAndTime): TFHIRInstant;
begin
  result := TFHIRInstant.create;
  result.value := value.link;
end;

function TFHIRBaseFactory.makeInstantHL7(value: String): TFHIRInstant;
begin
  result := TFHIRInstant.create;
  result.value := TDateAndTime.CreateHL7(value);
end;

function TFHIRBaseFactory.makeInstantXML(value: String): TFHIRInstant;
begin
  result := TFHIRInstant.create;
  result.value := TDateAndTime.CreateXML(value);
end;

function TFHIRBaseFactory.makeInteger(value: Integer): TFHIRInteger;
begin
  result := TFHIRInteger.create;
  result.value := value;
end;
                                               (*
function TFHIRBaseFactory.makeResourceReference(resourceType, id: String): TFHIRResourceReference;
begin
  result := TFHIRResourceReference.create;
  result.resourceType := resourceType;
  result.id := id;
end;


function TFHIRBaseFactory.makeString(value: String): TFHIRString;
begin
  result := TFHIRString.create;
  result.value := value;
end;
*)

{ TFHIRObject }

function TFHIRObject.createIterator(bInheritedProperties: Boolean): TFHIRPropertyIterator;
begin
  Result := TFHIRPropertyIterator.create(self, bInheritedProperties);
end;

destructor TFHIRObject.destroy;
begin
  FTag.Free;
  inherited;
end;

procedure TFHIRObject.GetChildrenByName(name: string; list: TFHIRObjectList);
begin
  // nothing to add here
end;

procedure TFHIRObject.ListChildrenByName(name: string; list: TFHIRObjectList);
begin
  if self <> nil then
    GetChildrenByName(name, list);
end;

procedure TFHIRObject.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  // nothing to add here
end;

function TFHIRObject.PerformQuery(path: String): TFHIRObjectList;
var
  qry : TFHIRQueryProcessor;
begin
  qry := TFHIRQueryProcessor.create;
  try
    qry.source.Add(self.Link);
    qry.path := path;
    qry.execute;
    result := qry.results.Link;
  finally
    qry.free;
  end;
end;

procedure TFHIRObject.SetTag(const Value: TAdvObject);
begin
  FTag.Free;
  FTag := Value;
end;

{ TFHIRObjectText }

constructor TFHIRObjectText.create(value: String);
begin
  Create;
  self.value := value;
end;

constructor TFHIRObjectText.create(value: boolean);
begin
  Create;
  self.value := lowercase(BooleanToString(value));
end;

constructor TFHIRObjectText.create(value: TDateAndTime);
begin
  Create;
  self.value := value.AsXML;
end;

procedure TFHIRObjectText.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'value', 'string', FValue));
end;

{ TFHIRQueryProcessor }

constructor TFHIRQueryProcessor.Create;
begin
  inherited;
  FResults := TFHIRObjectList.Create;
  FSource := TFHIRObjectList.Create;
end;

destructor TFHIRQueryProcessor.Destroy;
begin
  FSource.Free;
  FResults.Free;
  inherited;
end;

procedure TFHIRQueryProcessor.execute;
var
  src, seg : String;
  i : integer;
  first : boolean;
  list : TFhirResourceReferenceList;
begin
  src := FPath;
  if (src = '*') and (FSource[0] is TFHIRResource) then
  begin
    list := TFhirResourceReferenceList.Create;
    try
      listReferences(FSource[0] as TFHIRResource, list);
      FResults.AddAll(list);
    finally
      list.Free;
    end;
  end
  else
begin
  first := true;
  while (src <> '') do
  begin
      StringSplit(src, '.', seg, src);
    if (not IsValidIdent(seg)) Then
        raise exception.create('unable to parse path "'+FPath+'"');
    FResults.clear;
    if first then
      for i := 0 to FSource.count - 1 Do
      begin
        if FSource[i].ClassName = 'TFhir'+seg then
          FResults.add(FSource[i].Link);
      end
    else
      for i := 0 to FSource.count - 1 Do
        FSource[i].GetChildrenByName(seg, FResults);
    first := false;
    if src <> '' then
    begin
      FSource.Free;
      FSource := FResults;
      FResults := TFHIRObjectList.Create;
      end;
    end;
  end;
end;


{ TFHIRObjectList }

function TFHIRObjectList.Clone: TFHIRObjectList;
begin
  result := TFHIRObjectList(Inherited Clone);
end;

function TFHIRObjectList.GetItemN(index: Integer): TFHIRObject;
begin
  result := TFHIRObject(ObjectByIndex[index]);
end;

function TFHIRObjectList.ItemClass: TAdvObjectClass;
begin
  result := TFHIRObject;
end;

function TFHIRObjectList.Link: TFHIRObjectList;
begin
  result := TFHIRObjectList(Inherited Link);
end;
(*

{ TFHIRSid }

function TFHIRSid.Clone: TFHIRSid;
begin
  result := TFHIRSid(Inherited Clone);
end;

function TFHIRSid.Link: TFHIRSid;
begin
  result := TFHIRSid(Inherited Link);
end;

{ TFHIRDateTime }

function TFHIRDateTime.Clone: TFHIRDateTime;
begin
  result := TFHIRDateTime(Inherited Clone);
end;

function TFHIRDateTime.Link: TFHIRDateTime;
begin
  result := TFHIRDateTime(Inherited Link);
end;

{ TFHIRDate }

function TFHIRDate.Clone: TFHIRDate;
begin
  result := TFHIRDate(Inherited Clone);
end;

function TFHIRDate.Link: TFHIRDate;
begin
  result := TFHIRDate(Inherited Link);
end;

{ TFHIRUri }

function TFHIRUri.Clone: TFHIRUri;
begin
  result := TFHIRUri(Inherited Clone);
end;

function TFHIRUri.Link: TFHIRUri;
begin
  result := TFHIRUri(Inherited Link);
end;

{ TFHIRId }

function TFHIRId.Clone: TFHIRId;
begin
  result := TFHIRId(Inherited Clone);
end;

function TFHIRId.Link: TFHIRId;
begin
  result := TFHIRId(Inherited Link);
end;

{ TFHIROid }

function TFHIROid.Clone: TFHIROid;
begin
  result := TFHIROid(Inherited Clone);
end;

function TFHIROid.Link: TFHIROid;
begin
  result := TFHIROid(Inherited Link);
end;

{ TFHIRUuid }

function TFHIRUuid.Clone: TFHIRUuid;
begin
  result := TFHIRUuid(Inherited Clone);
end;

function TFHIRUuid.Link: TFHIRUuid;
begin
  result := TFHIRUuid(Inherited Link);
end;

{ TFHIRBuffer }

procedure TFHIRBuffer.Assign(oSource: TAdvObject);
begin
  inherited;
  FBuffer.Assign(TFHIRBuffer(oSource).FBuffer);
end;

function TFHIRBuffer.Clone: TFHIRBuffer;
begin
  result := TFHIRBuffer(Inherited Clone);
end;

constructor TFHIRBuffer.Create;
begin
  inherited;
  FBuffer := TAdvBuffer.Create;
end;

constructor TFHIRBuffer.Create(buffer: TAdvBuffer);
begin
  Create;
  FBuffer.Assign(buffer);
end;

destructor TFHIRBuffer.Destroy;
begin
  FBuffer.Free;
  inherited;
end;

function TFHIRBuffer.GetText: String;
begin
  result := FBuffer.AsText;
end;

function TFHIRBuffer.Link: TFHIRBuffer;
begin
  result := TFHIRBuffer(Inherited Link);
end;

procedure TFHIRBuffer.ListProperties(oList: TFHIRPropertyList; bInheritedProperties: Boolean);
begin
  if (bInheritedProperties) Then
    inherited;
  oList.add(TFHIRProperty.create(self, 'content', 'string', FBuffer.asBase64));
end;

procedure TFHIRBuffer.LoadFromFile(filename: String);
begin
  FBuffer.LoadFromFileName(filename);
end;

procedure TFHIRBuffer.SaveToFile(filename: String);
begin
  FBuffer.SaveToFileName(filename);
end;

procedure TFHIRBuffer.SetBuffer(const Value: TAdvBuffer);
begin
  FBuffer.Free;
  FBuffer := Value;
end;

procedure TFHIRBuffer.SetText(const Value: String);
begin
  FBuffer.AsText := value;
end;
*)


{ TFHIRProperty }

constructor TFHIRProperty.Create(oOwner: TFHIRObject; const sName, sType: String; oObject: TFHIRObject);
begin
  Create;
  FName := sName;
  FType := sType;
  FObj := oObject;
end;

constructor TFHIRProperty.Create(oOwner: TFHIRObject; const sName, sType: String; oList: TFHIRObjectList);
begin
  Create;
  FName := sName;
  FType := sType;
  FList := oList;
end;

constructor TFHIRProperty.Create(oOwner: TFHIRObject; const sName, sType: String; sValue: String);
begin
  Create;
  FName := sName;
  FType := sType;
  FValue := sValue;
end;

destructor TFHIRProperty.Destroy;
begin
  FObj.free;
  FList.free;
  inherited;
end;

{ TFHIRPropertyList }

function TFHIRPropertyList.GetProperty(iIndex: Integer): TFHIRProperty;
begin
  result := TFHIRProperty(ObjectByIndex[iIndex]);
end;

{ TFHIRPropertyIterator }

constructor TFHIRPropertyIterator.Create(oFocus: TFHIRObject; bInheritedProperties: Boolean);
begin
  Create;
  FFocus := oFocus;
  FProperties := TFHIRPropertyList.Create;
  if FFocus <> nil Then
    FFocus.ListProperties(FProperties, bInheritedProperties);
end;

destructor TFHIRPropertyIterator.Destroy;
begin
  FProperties.Free;
  inherited;
end;

function TFHIRPropertyIterator.GetCurrent: TFHIRProperty;
begin
  Result := FProperties[FCursor];
end;

function TFHIRPropertyIterator.More: Boolean;
begin
  result := FCursor < FProperties.Count;
end;

procedure TFHIRPropertyIterator.Next;
begin
  inc(FCursor);
end;

procedure TFHIRPropertyIterator.Reset;
begin
  FCursor := 0;
end;

End.
