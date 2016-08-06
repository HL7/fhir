<%@ language="javascript"%>

<%
  var s = String(Request.ServerVariables("HTTP_ACCEPT"));
  if (s.indexOf("json") > -1) 
    Response.Redirect("http://hl7.org/fhir/<%literal%>.json");
  else if (s.indexOf("xml") > -1) 
    Response.Redirect("http://hl7.org/fhir/<%literal%>.xml");
  else
    Response.Redirect("http://hl7.org/fhir/<%logical%>.html");

%>

<!DOCTYPE html>
<html>
<body>
You should not be seeing this page. If you do, ASP has failed badly.
</body>
</html>