unit FHIRAtomFeed;

{!Wrapper uses FHIRResources,FHIRTypes,MSSEWrap}

{
Copyright (c) 2011-2013, HL7, Inc
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
  SysUtils, Classes,
  StringSupport, BytesSupport,
  Json,
  FHIRBase,
  FHIRTypes,
  FHIRResources,
  DateAndTime,
  AdvMemories,
  AdvObjects,
  AdvObjectLists;

type
  {@Class TFHIRAtomLink
    A Atom Link - type and url
  }
  {!.Net HL7Connect.Fhir.AtomLink}
  TFHIRAtomLink = class (TFHIRBase)
  private
    FURL : String;
    FRel : String;
  public
    {!script hide}
    procedure Assign(oSource : TAdvObject); override;
    function Link : TFHIRAtomLink; overload;
    function Clone : TFHIRAtomLink; overload;
    {!script show}

  published
    {@member URL
      The link
    }
    property URL : String read FURL write FURL;
    {@member Rel
      The type of link
    }
    property Rel : String read FRel write FRel;
  end;

  {@Class TFHIRAtomLinkList
    A list of Atom Links
  }
  {!.Net HL7Connect.Fhir.AtomLinkList}
  TFHIRAtomLinkList = class (TFHIRObjectList)
  private
    // procedure SetItemN(index : Integer; value : TFHIRAtomLink);
    procedure SetRel(name: string; const Value: string);
  protected
    function ItemClass: TAdvObjectClass; Override;
    Procedure InsertByIndex(index : Integer; value : TFHIRAtomLink);

  public
    {!script hide}
    Function Link : TFHIRAtomLinkList; Overload;
    Function Clone : TFHIRAtomLinkList; Overload;
    Function GetItemN(index : Integer) : TFHIRAtomLink;
    {!script show}
    {@member GetRel
      Get the url for a given rel type (or blank)
    }
    Function GetRel(relType : String) : String;

    {@member Append
      Add an AtomLink to the end of the list.
    }
    Function Append : TFHIRAtomLink;
    {@member AddItem
      Add an already existing AtomLink to the end of the list.
    }
    Procedure AddItem(value : TFHIRAtomLink);
    {@member AddValue
      Add a URL and Rel to the end of the list.
    }
    Procedure AddValue(url, rel : String);
    {@member IndexOf
      See if an item is already in the list. returns -1 if not in the list
    }
    Function IndexOf(value : TFHIRAtomLink) : Integer;
    {@member Insert
       Insert AtomLink before the designated index (0 = first item)
    }
    Function Insert(index : Integer) : TFHIRAtomLink;
    {@member InsertItem
       Insert an existing AtomLink before the designated index (0 = first item)
    }
    Procedure InsertItem(index : Integer; value : TFHIRAtomLink);
    {@member Item
       Get the indexth AtomLink. (0 = first item)
    }
    Function Item(index : Integer) : TFHIRAtomLink;
    {@member SetItemByIndex
       Set the indexth AtomLink. (0 = first item)
    }
    Procedure SetItemByIndex(index : Integer; value : TFHIRAtomLink);
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
    Property Rel[name : string] : String read GetRel write SetRel; default;
  End;

  {@Class TFHIRAtomCategory
    An Atom Category - scheme, term, and label
  }
  {!.Net HL7Connect.Fhir.AtomCategory}
  TFHIRAtomCategory  = class (TFHIRBase)
  private
    FScheme : String;
    FTerm : String;
    FLabel : String;
    FTagKey: integer;
  public
    {!script hide}
    procedure Assign(oSource : TAdvObject); override;
    function Link : TFHIRAtomCategory; overload;
    function Clone : TFHIRAtomCategory; overload;
    Property TagKey : integer read FTagKey write FTagKey;
    {!script show}

  published
    {@member scheme
      What kind of category this is. http://hl7.org/fhir/tag = tag.
    }
    property scheme : String read FScheme write FScheme;
    {@member term
      Category URI - what tag this is (if it's tag)
    }
    property term : String read FTerm write FTerm;
    {@member label
      Label for the category
    }
    property label_ : String read FLabel write FLabel;
  end;

  {@Class TFHIRAtomCategoryList
    A list of Atom Links
  }
  {!.Net HL7Connect.Fhir.AtomCategoryList}
  TFHIRAtomCategoryList = class (TFHIRObjectList)
  private
    procedure SetItemN(index : Integer; value : TFHIRAtomCategory);
    function GetJson : TBytes;
    procedure SetJson(value : TBytes); overload;
    procedure SetJson(obj : TJsonObject); overload;
  protected
    function ItemClass: TAdvObjectClass; Override;
    Procedure InsertByIndex(index : Integer; value : TFHIRAtomCategory);

  public
    {!script hide}
    Function Link : TFHIRAtomCategoryList; Overload;
    Function Clone : TFHIRAtomCategoryList; Overload;
    Function GetItemN(index : Integer) : TFHIRAtomCategory;
    Property ItemN[index : Integer] : TFHIRAtomCategory read GetItemN write SetItemN; default;
    Property Json : TBytes read GetJson write SetJson;
    procedure DecodeJson(stream : TStream);
    Procedure CopyTags(other : TFHIRAtomCategoryList);
    function HasTag(schemeUri, tagUri : string):Boolean; overload;
    function HasTag(schemeUri, tagUri : string; var n : integer):Boolean; overload;
    function GetTag(schemeUri, tagUri : string):TFHIRAtomCategory;
    Function AsHeader : String;

    {!script show}
    {@member Append
      Add an AtomCategory to the end of the list.
    }
    Function Append : TFHIRAtomCategory;
    {@member AddItem
      Add an already existing AtomCategory to the end of the list.
    }
    Procedure AddItem(value : TFHIRAtomCategory);
    {@member AddValue
      Add a URL and Rel to the end of the list.
    }
    function AddValue(scheme, term, label_ : String):TFhirAtomCategory;
    {@member AddTag
      Add a FHIR Tag to the end of the list.
    }
    function AddTag(tag : String):TFhirAtomCategory;
    {@member AddTagDescription
      Add a FHIR Tag with a description to the end of the list.
    }
    function AddTagDescription(tag, desc : String):TFhirAtomCategory;
    {@member IndexOf
      See if an item is already in the list. returns -1 if not in the list
    }
    Function IndexOf(value : TFHIRAtomCategory) : Integer;
    {@member Insert
       Insert AtomCategory before the designated index (0 = first item)
    }
    Function Insert(index : Integer) : TFHIRAtomCategory;
    {@member InsertItem
       Insert an existing AtomCategory before the designated index (0 = first item)
    }
    Procedure InsertItem(index : Integer; value : TFHIRAtomCategory);
    {@member Item
       Get the indexth AtomCategory. (0 = first item)
    }
    Function Item(index : Integer) : TFHIRAtomCategory;
    {@member SetItemByIndex
       Set the indexth AtomCategory. (0 = first item)
    }
    Procedure SetItemByIndex(index : Integer; value : TFHIRAtomCategory);
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
  End;

  {@Class TFHIRAtomBase
    An entry in an atom feed
  }
  {!.Net HL7Connect.Fhir.AtomBase}
  TFHIRAtomBase = class (TFHIRBase)
  private
    Fid: string;
    Ftitle: string;
    FauthorUri: String;
    FauthorName: String;
    FLinks : TFHIRAtomLinkList;
    FCategories : TFHIRAtomCategoryList;
    Fpublished_: TDateAndTime;
    Fupdated: TDateAndTime;
    procedure Setpublished_(const Value: TDateAndTime);
    procedure Setupdated(const Value: TDateAndTime);
  public
    constructor Create; Override;
    destructor Destroy; override;
    {!script hide}
    procedure Assign(oSource : TAdvObject); override;
    function Link : TFHIRAtomBase; overload;
    function Clone : TFHIRAtomBase; overload;
    Function HasTag(schemeUri, tag : string) : boolean;
    {!script show}
  published
    {@member title
      text summary of resource
    }
    property title : string read Ftitle write Ftitle;
    {@member links
      Master Location for Resource
    }
    property links : TFHIRAtomLinkList read Flinks;

    {@member categories
      Tags on the resource
    }
    property categories : TFHIRAtomCategoryList read Fcategories;

    {@member id
      Master Id for this resource
    }
    property id : string read Fid write Fid;

    {@member updated
      Last Updated for resource
    }
    property updated : TDateAndTime read Fupdated write Setupdated;

    {@member published_
      Time resource sourced for aggregation (optional)
    }
    property published_ : TDateAndTime read Fpublished_ write Setpublished_;

    {@member authorName
      Name of Human or Device that authored the resource
    }
    property authorName : String read FauthorName write FauthorName;

    {@member authorUri
      Link to the resource for the author
    }
    property authorUri : String read FauthorUri write FauthorUri;

  end;

  {@Class TFHIRAtomEntry
    An entry in an atom feed
  }
  {!.Net HL7Connect.Fhir.AtomEntry}
  TFHIRAtomEntry = class (TFHIRAtomBase)
  private
    ForiginalId : String;
    Fresource: TFhirResource;
    Fsummary: TFhirXHtmlNode;
    FDeleted: boolean;
    procedure SetResource(const Value: TFhirResource);
    procedure Setsummary(const Value: TFHIRXhtmlNode);
  public
    constructor Create; Override;
    destructor Destroy; override;
    {!script hide}
    procedure Assign(oSource : TAdvObject); override;
    function Link : TFHIRAtomEntry; overload;
    function Clone : TFHIRAtomEntry; overload;
    {!script show}

  published

    {@member originalId
      - the original id if this was first received from a batch update that identified it differently
    }
    property originalId : String read ForiginalId write ForiginalId;

    {@member resource
      actual resource for the entry
    }
    property resource : TFhirResource read Fresource write SetResource;

    {@member summary
      xhtml extracted from the resource
    }
    property summary : TFHIRXhtmlNode read Fsummary write Setsummary;

    {@member deleted
      true if this resource is deleted.

      Deleted resources only have an id, and a updated (date of deletion) and optionally a link_ and author.
    }
    property deleted : boolean read FDeleted write FDeleted;
  end;

  {@Class TFHIRAtomEntryList
    A list of Atom Entries
  }
  {!.Net HL7Connect.Fhir.AtomEntryList}
  TFHIRAtomEntryList = class (TFHIRObjectList)
  private
    Function GetItemN(index : Integer) : TFHIRAtomEntry;
    procedure SetItemN(index : Integer; value : TFHIRAtomEntry);
  protected
    Procedure InsertByIndex(index : Integer; value : TFHIRAtomEntry);

  public
    {!script hide}
    Function Link : TFHIRAtomEntryList; Overload;
    Function Clone : TFHIRAtomEntryList; Overload;
    {!script show}
    {@member Append
      Add an AtomEntry to the end of the list.
    }
    Function Append : TFHIRAtomEntry;
    {@member AddItem
      Add an already existing AtomEntry to the end of the list.
    }
    Procedure AddItem(value : TFHIRAtomEntry);
    {@member IndexOf
      See if an item is already in the list. returns -1 if not in the list
    }
    Function IndexOf(value : TFHIRAtomEntry) : Integer;
    {@member Insert
       Insert AtomEntry before the designated index (0 = first item)
    }
    Function Insert(index : Integer) : TFHIRAtomEntry;
    {@member InsertItem
       Insert an existing AtomEntry before the designated index (0 = first item)
    }
    Procedure InsertItem(index : Integer; value : TFHIRAtomEntry);
    {@member Item
       Get the indexth AtomEntry. (0 = first item)
    }
    Function Item(index : Integer) : TFHIRAtomEntry;
    {@member SetItemByIndex
       Set the indexth AtomEntry. (0 = first item)
    }
    Procedure SetItemByIndex(index : Integer; value : TFHIRAtomEntry);
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
    Property AtomEntrys[index : Integer] : TFHIRAtomEntry read GetItemN write SetItemN; default;
  End;


  {@Class TFHIRAtomFeed
    An atom feed (as profiled in the FHIR specification)
  }
  {!.Net HL7Connect.Fhir.AtomFeed}
  TFHIRAtomFeed = class (TFHIRAtomBase)
  private
    Fentries: TFHIRAtomEntryList;
    FisSearch: Boolean;
    FSearchCount: Integer;
    FSearchTotal: Integer;
    FSearchOffset: Integer;
    FSQL: String;
    function GetFHIRBaseUrl: String;
    procedure SetFhirBaseUrl(const Value: String);
  public
    constructor Create; Override;
    destructor Destroy; override;
    {!script hide}
    procedure Assign(oSource : TAdvObject); override;
    function Link : TFHIRAtomFeed; overload;
    function Clone : TFHIRAtomFeed; overload;
    Property isSearch : Boolean read FisSearch write FisSearch;
    Property SearchCount : Integer read FSearchCount write FSearchCount;
    Property SearchTotal : Integer read FSearchTotal write FSearchTotal;
    Property SearchOffset : Integer read FSearchOffset write FSearchOffset;
    Property sql : String read FSQL write FSQL;
    Property fhirBaseUrl : String read GetFHIRBaseUrl write SetFhirBaseUrl;

    {!script show}

    {@member addEntry
      add a resource to a feed with the given title / author / id. link is optional
      both id and link (if present) must be full URIs.

      updated is required.

      a resource is required.
    }
    {!script nolink}
    Function addEntry(title: String; updated : TDateAndTime; id, link : String; resource : TFhirResource) : TFHIRAtomEntry;
  published
    {@member entries
      actual content of the atom feed
    }
    property entries : TFHIRAtomEntryList read Fentries;

  end;

implementation

{ TFHIRAtomBase }

function TFHIRAtomBase.Clone: TFHIRAtomBase;
begin
  result := TFHIRAtomBase(Inherited Clone);
end;

constructor TFHIRAtomBase.Create;
begin
  inherited;
  FLinks := TFHIRAtomLinkList.create;
  FCategories := TFHIRAtomCategoryList.create;
end;

destructor TFHIRAtomBase.Destroy;
begin
  FCategories.Free;
  FLinks.free;
  Fpublished_.free;
  Fupdated.Free;
  inherited;
end;

procedure TFHIRAtomBase.Assign(oSource: TAdvObject);
begin
  inherited;
  Fid := TFHIRAtomBase(oSource).Fid;
  Ftitle := TFHIRAtomBase(oSource).Ftitle;
  FauthorUri := TFHIRAtomBase(oSource).FauthorUri;
  FauthorName := TFHIRAtomBase(oSource).FauthorName;
  Flinks.assign(TFHIRAtomBase(oSource).Flinks);
  Fcategories.assign(TFHIRAtomBase(oSource).Fcategories);
  Fpublished_ := TFHIRAtomBase(oSource).Fpublished_.Clone;
  Fupdated := TFHIRAtomBase(oSource).Fupdated.Clone;
end;

procedure TFHIRAtomBase.Setpublished_(const Value: TDateAndTime);
begin
  Fpublished_.free;
  Fpublished_ := Value;
end;

procedure TFHIRAtomBase.Setupdated(const Value: TDateAndTime);
begin
  Fupdated.free;
  Fupdated := Value;
end;

function TFHIRAtomBase.HasTag(schemeUri, tag: string): boolean;
begin
  result := categories.HasTag(schemeUri, tag);
end;

function TFHIRAtomBase.Link: TFHIRAtomBase;
begin
  result := TFHIRAtomBase(Inherited Link);
end;

{ TFHIRAtomEntry }

procedure TFHIRAtomEntry.Assign(oSource: TAdvObject);
begin
  inherited;
  ForiginalId := TFHIRAtomEntry(oSource).ForiginalId;
  summary  := TFHIRAtomEntry(oSource).summary.clone;
  resource  := TFHIRAtomEntry(oSource).resource.clone;
  Deleted := TFHIRAtomEntry(oSource).deleted;
end;

function TFHIRAtomEntry.Clone: TFHIRAtomEntry;
begin
  result := TFHIRAtomEntry(inherited Clone);
end;

constructor TFHIRAtomEntry.Create;
begin
  inherited;
end;

destructor TFHIRAtomEntry.Destroy;
begin
  Fsummary.Free;
  Fresource.Free;
  inherited;
end;

function TFHIRAtomEntry.Link: TFHIRAtomEntry;
begin
  result := TFHIRAtomEntry(inherited Link);
end;

procedure TFHIRAtomEntry.SetResource(const Value: TFhirResource);
begin
  Fresource.Free;
  Fresource := Value;
end;

procedure TFHIRAtomEntry.Setsummary(const Value: TFHIRXhtmlNode);
begin
  Fsummary.Free;
  Fsummary := Value;
end;

{ TFHIRAtomFeed }

Function TFHIRAtomFeed.addEntry(title : String; updated : TDateAndTime; id, link : String; resource : TFhirResource) : TFHIRAtomEntry;
begin
  result := TFHIRAtomEntry.create;
  try
    result.title := title;
    result.id := id;
    result.Flinks.addValue(link, 'self');
    result.resource := resource.Link;
    result.updated := updated.Link;
    if resource.text <> nil then
      result.summary := resource.text.div_.link;
    Fentries.add(result.link);
  finally
    result.free;
  end;
end;

procedure TFHIRAtomFeed.Assign(oSource: TAdvObject);
begin
  inherited;
  Fentries.Assign(TFHIRAtomFeed(oSource).Fentries);
end;

function TFHIRAtomFeed.Clone: TFHIRAtomFeed;
begin
  result := TFHIRAtomFeed(Inherited Clone);
end;

constructor TFHIRAtomFeed.Create;
begin
  inherited;
  Fentries := TFHIRAtomEntryList.Create;
end;

destructor TFHIRAtomFeed.Destroy;
begin
  FEntries.free;
  inherited;
end;

function TFHIRAtomFeed.GetFHIRBaseUrl: String;
begin
  result := Flinks.GetRel('fhir-base');
end;

function TFHIRAtomFeed.Link: TFHIRAtomFeed;
begin
  result := TFHIRAtomFeed(Inherited Link);
end;


procedure TFHIRAtomFeed.SetFhirBaseUrl(const Value: String);
begin
  FLinks.SetRel('fhir-base', value);
end;

{ TFHIRAtomEntryList }

procedure TFHIRAtomEntryList.AddItem(value: TFHIRAtomEntry);
begin
  add(value.Link);
end;

function TFHIRAtomEntryList.Append: TFHIRAtomEntry;
begin
  result := TFhirAtomEntry.create;
  try
    add(result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRAtomEntryList.ClearItems;
begin
  Clear;
end;

function TFHIRAtomEntryList.Clone: TFHIRAtomEntryList;
begin
  result := TFHIRAtomEntryList(inherited Clone);
end;

function TFHIRAtomEntryList.Count: Integer;
begin
  result := Inherited Count;
end;

function TFHIRAtomEntryList.GetItemN(index: Integer): TFHIRAtomEntry;
begin
  result := TFHIRAtomEntry(ObjectByIndex[index]);
end;

function TFHIRAtomEntryList.IndexOf(value: TFHIRAtomEntry): Integer;
begin
  result := IndexByReference(value);
end;

function TFHIRAtomEntryList.Insert(index: Integer): TFHIRAtomEntry;
begin
  result := TFhirAtomEntry.create;
  try
    inherited insert(index, result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRAtomEntryList.InsertByIndex(index: Integer; value: TFHIRAtomEntry);
begin
  inherited Insert(index, value);
end;

procedure TFHIRAtomEntryList.InsertItem(index: Integer; value: TFHIRAtomEntry);
begin
  Inherited Insert(index, value.Link);
end;

function TFHIRAtomEntryList.Item(index: Integer): TFHIRAtomEntry;
begin
  result := TFHIRAtomEntry(ObjectByIndex[index]);
end;

function TFHIRAtomEntryList.Link: TFHIRAtomEntryList;
begin
  result := TFHIRAtomEntryList(inherited Link);
end;

procedure TFHIRAtomEntryList.Remove(index: Integer);
begin
  DeleteByIndex(index);
end;

procedure TFHIRAtomEntryList.SetItemByIndex(index: Integer; value: TFHIRAtomEntry);
begin
  ObjectByIndex[index] := value.Link;
end;

procedure TFHIRAtomEntryList.SetItemN(index: Integer; value: TFHIRAtomEntry);
begin
  ObjectByIndex[index] := value;
end;

{ TFHIRAtomLink }

procedure TFHIRAtomLink.Assign(oSource: TAdvObject);
begin
  inherited;
  url := TFHIRAtomLink(oSource).URL;
  rel := TFHIRAtomLink(oSource).rel;
end;

function TFHIRAtomLink.Clone: TFHIRAtomLink;
begin
  result := TFHIRAtomLink(inherited Clone);
end;

function TFHIRAtomLink.Link: TFHIRAtomLink;
begin
  result := TFHIRAtomLink(inherited Link);
end;

{ TFHIRAtomLinkList }
procedure TFHIRAtomLinkList.AddItem(value: TFHIRAtomLink);
begin
  add(value.Link);
end;

procedure TFHIRAtomLinkList.AddValue(url, rel: String);
var
  link : TFHIRAtomLink;
begin
  link := TFhirAtomLink.create;
  try
    link.url := url;
    link.rel := rel;
    add(link.Link);
  finally
    link.free;
  end;

end;

function TFHIRAtomLinkList.Append: TFHIRAtomLink;
begin
  result := TFhirAtomLink.create;
  try
    add(result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRAtomLinkList.ClearItems;
begin
  Clear;
end;

function TFHIRAtomLinkList.Clone: TFHIRAtomLinkList;
begin
  result := TFHIRAtomLinkList(inherited Clone);
end;

function TFHIRAtomLinkList.Count: Integer;
begin
  result := Inherited Count;
end;

function TFHIRAtomLinkList.GetItemN(index: Integer): TFHIRAtomLink;
begin
  result := TFHIRAtomLink(ObjectByIndex[index]);
end;

function TFHIRAtomLinkList.IndexOf(value: TFHIRAtomLink): Integer;
begin
  result := IndexByReference(value);
end;

function TFHIRAtomLinkList.Insert(index: Integer): TFHIRAtomLink;
begin
  result := TFhirAtomLink.create;
  try
    inherited insert(index, result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRAtomLinkList.InsertByIndex(index: Integer; value: TFHIRAtomLink);
begin
  inherited Insert(index, value);
end;

procedure TFHIRAtomLinkList.InsertItem(index: Integer; value: TFHIRAtomLink);
begin
  Inherited Insert(index, value.Link);
end;

function TFHIRAtomLinkList.Item(index: Integer): TFHIRAtomLink;
begin
  result := TFHIRAtomLink(ObjectByIndex[index]);
end;

function TFHIRAtomLinkList.Link: TFHIRAtomLinkList;
begin
  result := TFHIRAtomLinkList(inherited Link);
end;

function TFHIRAtomLinkList.GetRel(relType : String): String;
var
  i : integer;
begin
  result := '';
  for i := 0 to Count - 1 do
    if GetItemN(i).Rel = RelType then
      result := GetItemN(i).URL;
end;

procedure TFHIRAtomLinkList.Remove(index: Integer);
begin
  DeleteByIndex(index);
end;

procedure TFHIRAtomLinkList.SetItemByIndex(index: Integer; value: TFHIRAtomLink);
begin
  ObjectByIndex[index] := value.Link;
end;

{
procedure TFHIRAtomLinkList.SetItemN(index: Integer; value: TFHIRAtomLink);
begin
  ObjectByIndex[index] := value;
end;
}

procedure TFHIRAtomLinkList.SetRel(name: string; const Value: string);
var
  i : integer;
  b : boolean;
begin
  b := false;
  for i := 0 to Count - 1 do
    if GetItemN(i).Rel = name then
    begin
      GetItemN(i).URL := value;
      b := true;
    end;
  if not b then
    AddValue(value, name);
end;

function TFHIRAtomLinkList.ItemClass: TAdvObjectClass;
begin
  result := TFHIRAtomLink;
end;

{ TFHIRAtomCategoryList }
procedure TFHIRAtomCategoryList.AddItem(value: TFHIRAtomCategory);
begin
  add(value.Link);
end;

function TFHIRAtomCategoryList.AddValue(scheme, term, label_: String) : TFhirAtomCategory;
var
  link : TFHIRAtomCategory;
begin
  link := TFhirAtomCategory.create;
  try
    link.scheme := scheme;
    link.term := term;
    link.label_ := label_;
    add(link.Link);
    result := link;
  finally
    link.free;
  end;

end;

function TFHIRAtomCategoryList.Append: TFHIRAtomCategory;
begin
  result := TFhirAtomCategory.create;
  try
    add(result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRAtomCategoryList.ClearItems;
begin
  Clear;
end;

function TFHIRAtomCategoryList.Clone: TFHIRAtomCategoryList;
begin
  result := TFHIRAtomCategoryList(inherited Clone);
end;

function TFHIRAtomCategoryList.Count: Integer;
begin
  result := Inherited Count;
end;

procedure TFHIRAtomCategoryList.DecodeJson(stream: TStream);
begin
  stream.Position := 0;
  SetJson(TJSONParser.Parse(stream));
end;

function TFHIRAtomCategoryList.GetItemN(index: Integer): TFHIRAtomCategory;
begin
  result := TFHIRAtomCategory(ObjectByIndex[index]);
end;

function TFHIRAtomCategoryList.IndexOf(value: TFHIRAtomCategory): Integer;
begin
  result := IndexByReference(value);
end;

function TFHIRAtomCategoryList.Insert(index: Integer): TFHIRAtomCategory;
begin
  result := TFhirAtomCategory.create;
  try
    inherited insert(index, result.Link);
  finally
    result.free;
  end;
end;

procedure TFHIRAtomCategoryList.InsertByIndex(index: Integer; value: TFHIRAtomCategory);
begin
  inherited Insert(index, value);
end;

procedure TFHIRAtomCategoryList.InsertItem(index: Integer; value: TFHIRAtomCategory);
begin
  Inherited Insert(index, value.Link);
end;

function TFHIRAtomCategoryList.Item(index: Integer): TFHIRAtomCategory;
begin
  result := TFHIRAtomCategory(ObjectByIndex[index]);
end;

function TFHIRAtomCategoryList.Link: TFHIRAtomCategoryList;
begin
  result := TFHIRAtomCategoryList(inherited Link);
end;

procedure TFHIRAtomCategoryList.Remove(index: Integer);
begin
  DeleteByIndex(index);
end;

procedure TFHIRAtomCategoryList.SetItemByIndex(index: Integer; value: TFHIRAtomCategory);
begin
  ObjectByIndex[index] := value.Link;
end;

procedure TFHIRAtomCategoryList.SetItemN(index: Integer; value: TFHIRAtomCategory);
begin
  ObjectByIndex[index] := value;
end;


function TFHIRAtomCategoryList.ItemClass: TAdvObjectClass;
begin
  result := TFHIRAtomCategory;
end;

function TFHIRAtomCategoryList.GetJson: TBytes;
var
  i : integer;
  json : TJSONWriter;
  strm : TAdvMemoryStream;
begin
  strm := TAdvMemoryStream.Create;
  try
    json := TJSONWriter.Create;
    try
      json.Stream := strm.Link;
      json.Start;
      json.ValueArray('tags');
      for i := 0 to Count - 1 do
      begin
        json.ValueObject('');
        json.Value('scheme', ItemN[i].scheme);
        json.Value('term', ItemN[i].term);
        json.Value('label', ItemN[i].label_);
        json.FinishObject;
      end;
      json.FinishArray;
      json.Finish;
    finally
      json.Free;
    end;
    strm.Position := 0;
    setLength(result, strm.Size);
    strm.Read(result[0], strm.Size);
  finally
    strm.Free;
  end;
end;

procedure TFHIRAtomCategoryList.CopyTags(other: TFHIRAtomCategoryList);
var
  i : integer;
begin
  if other <> nil then
    for i := 0 to other.Count - 1 do
      if not HasTag(other[i].Scheme, other[i].term) then
        add(other[i].link);
end;

procedure TFHIRAtomCategoryList.SetJson(obj : TJsonObject);
var
  jsn : TJsonObject;
  ja : TJsonArray;
  cat : TFHIRAtomCategory;
  i : integer;
begin
  ja := obj.vArr['tags'];
  for i := 0 to ja.Count - 1 do
  begin
    jsn := ja[i];
    cat := TFHIRAtomCategory.create;
    try
      cat.scheme := FHIR_TAG_SCHEME;
      cat.term := jsn['term'];
      cat.label_ := jsn['label'];
      cat.scheme := jsn['scheme'];
      add(cat.Link);
    finally
      cat.free;
    end;
  end;
end;
procedure TFHIRAtomCategoryList.SetJson(value : TBytes);
begin
  SetJson(TJSONParser.Parse(value));
end;

function TFHIRAtomCategoryList.AsHeader: String;
var
  i : integer;
begin
  result := '';
  for i := 0 to Count - 1 do
  begin
    if i > 0 then
      result := result + ',';
    result := result + ItemN[i].term+'; scheme="'+ItemN[i].scheme+'"';
    if ItemN[i].label_ <> '' then
      result := result + '; label="'+ItemN[i].label_+'"';
  end;
end;

function TFHIRAtomCategoryList.HasTag(schemeUri, tagUri: string): Boolean;
var
  i : integer;
begin
  result := false;
  for i := 0 to Count - 1 do
    if (ItemN[i].FScheme = schemeUri) and  (ItemN[i].FTerm = tagUri) then
    begin
      result := true;
      break;
    end;
end;

function TFHIRAtomCategoryList.GetTag(schemeUri, tagUri: string): TFHIRAtomCategory;
var
  i : integer;
begin
  result := nil;
  for i := 0 to Count - 1 do
    if (ItemN[i].FScheme = schemeUri) and (ItemN[i].FTerm = tagUri) then
    begin
      result := ItemN[i];
      break;
    end;
end;

function TFHIRAtomCategoryList.HasTag(schemeUri, tagUri: string; var n: integer): Boolean;
var
  i : integer;
begin
  result := false;
  for i := 0 to Count - 1 do
    if (ItemN[i].FScheme = schemeUri) and (ItemN[i].FTerm = tagUri) then
    begin
      result := true;
      n := i;
      break;
    end;
end;

function TFHIRAtomCategoryList.AddTag(tag: String): TFhirAtomCategory;
begin
  Result := AddTagDescription(tag, '');
end;

function TFHIRAtomCategoryList.AddTagDescription(tag, desc: String): TFhirAtomCategory;
begin
  Result := AddValue(FHIR_TAG_SCHEME, tag, desc);

end;

{ TFHIRAtomCategory }

procedure TFHIRAtomCategory.Assign(oSource: TAdvObject);
begin
  inherited;
  FScheme := TFHIRAtomCategory(oSource).FScheme;
  FTerm := TFHIRAtomCategory(oSource).FTerm;
  FLabel := TFHIRAtomCategory(oSource).FLabel;
end;

function TFHIRAtomCategory.Clone: TFHIRAtomCategory;
begin
  result := TFHIRAtomCategory(Inherited Clone);
end;

function TFHIRAtomCategory.Link: TFHIRAtomCategory;
begin
  result := TFHIRAtomCategory(Inherited Link);
end;

end.
