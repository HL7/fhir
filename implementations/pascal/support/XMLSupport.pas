unit XMLSupport;

interface

Uses
  Xml.xmlintf;

function getChildNode(node : IXMLNode; name, ns : String) : IXMLNode; overload;
function getChildNode(node : IXMLNode; name : String) : IXMLNode; overload;

implementation

function getChildNode(node : IXMLNode; name, ns : String) : IXMLNode;
var
  i : integer;
  child : IXMLNode;
begin
  result := nil;
  for i := 0 to node.ChildNodes.Count - 1 do
  begin
    child  := node.ChildNodes[i];
    if (child.NamespaceURI = ns) and (child.NodeName = name) then
    begin
      result := child;
      exit;
    end;
  end;
end;

function getChildNode(node : IXMLNode; name : String) : IXMLNode;
var
  i : integer;
  child : IXMLNode;
begin
  result := nil;
  for i := 0 to node.ChildNodes.Count - 1 do
  begin
    child  := node.ChildNodes[i];
    if (child.NodeName = name) then
    begin
      result := child;
      exit;
    end;
  end;
end;

end.
