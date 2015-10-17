<%@ language="javascript"%>

<%
  var s = String(Request.ServerVariables("HTTP_ACCEPT"));
  if (s.indexOf("json") > -1) 
    Response.Redirect("http://hl7.org/fhir/<%filename%>.json");
  else if (s.indexOf("html") > -1) 
    Response.Redirect("http://hl7.org/fhir/<%filename%>.html");
  else
    Response.Redirect("http://hl7.org/fhir/<%filename%>.xml");

%>

<!DOCTYPE html>
<html>
<body>
You should not be seeing this page. If you do, ASP has failed badly.
</body>
</html>