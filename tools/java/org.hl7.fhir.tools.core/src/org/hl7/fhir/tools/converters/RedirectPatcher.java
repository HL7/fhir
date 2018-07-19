package org.hl7.fhir.tools.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class RedirectPatcher {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    new RedirectPatcher().execute();
    System.out.println("done");
  }

  public static String path(String... args) {
    StringBuilder s = new StringBuilder();
    boolean d = false;
    for(String arg: args) {
      if (!d)
        d = !Utilities.noString(arg);
      else if (!s.toString().endsWith(File.separator))
        s.append(File.separator);
      String a = arg;
      a = a.replace("\\", File.separator);
      a = a.replace("/", File.separator);
      if (s.length() > 0 && a.startsWith(File.separator))
        a = a.substring(File.separator.length());
      
      while (a.startsWith(".."+File.separator)) {
        String p = s.toString().substring(0, s.length()-1);
        if (!p.contains(File.separator)) {
          s = new StringBuilder();
        } else {
          s = new StringBuilder(p.substring(0,  p.lastIndexOf(File.separator))+File.separator);
        }
        a = a.substring(3);
      }
      if ("..".equals(a)) {
        int i = s.substring(0, s.length()-1).lastIndexOf(File.separator);
        s = new StringBuilder(s.substring(0, i+1));
      } else
        s.append(a);
    }
    return s.toString();
  }


  private void execute() throws FileNotFoundException, IOException {
    // for main redirectors
//    for (String dir : directories("F:\\fhir\\web")) {
//      if (!isSubVersion(dir)) {
//        processDirectory(dir);
//      }
//    }
    for (String f : filesByType("F:\\fhir\\web\\json-schema", ".schema.json")) {
      processJsonSchema(f);
    }
  }

  private void processJsonSchema(String f) throws IOException {
    String sname = f.split("\\\\")[4];
    sname = sname.substring(0, sname.indexOf("."));
    String rd = "<%@ language=\"javascript\"%>\r\n\r\n<%\r\n  Response.Redirect(\"http://hl7.org/fhir/json-schema/$s.schema.json\");\r\n\r\n%>\r\n\r\n<!DOCTYPE html>\r\n<html>\r\n<body>\r\nYou should not be seeing this page. If you do, ASP has failed badly.\r\n</body>\r\n</html>";
    rd = rd.replace("$s", sname);
    Utilities.createDirectory(path("F:\\fhir\\web\\json-schema", sname));
    TextFile.stringToFile(rd, path("F:\\fhir\\web\\json-schema", sname, "index.asp"));
  }

  private void processDirectory(String dir) throws FileNotFoundException, IOException {
    int i = 0;
    for (String f : filesByType(dir, ".asp")) {
      processAspFile(f);
      i++;
    }
//    if (i > 0)
//      System.out.println(dir+" : "+Integer.toString(i)+" files");
    for (String sdir : directories(dir)) {
      processDirectory(sdir);
    }    
  }

  private void processAspFile(String f) throws FileNotFoundException, IOException {
    String target = readExistingAsp(f);
    String targetHtml = target;
    if (!targetFileExists(targetHtml))
      System.out.println(f+": broken reference to "+targetHtml);
    else {
      if (target.contains("#"))
        target = target.substring(0, target.indexOf("#"));
      String targetXml = Utilities.changeFileExt(target, ".xml");      
      if (target.startsWith("v2/")) {
        String t = targetXml.replace("/index.xml", "");
        targetXml = targetXml.replace("index.xml", t.replaceAll("/", "-")+".vs.xml");
      }
      if (target.startsWith("v3/")) {
        if (target.contains("vs")) {
          String t = targetXml.replace("/vs.xml", "");
          targetXml = targetXml.replace("vs.xml", t.replaceAll("/", "-")+".xml");
        } else {
          String t = targetXml.replace("/cs.xml", "");
          targetXml = targetXml.replace("cs.xml", t.replaceAll("/", "-")+".cs.xml");
        }
      }
      String[] parts = f.split("\\\\");

      if (!targetFileExists(targetXml)) {
        if (!target.contains("-extensions"))
          targetXml = Utilities.changeFileExt(target, ".profile.xml");
        else if (f.contains("\\SearchParameter\\"))
          targetXml = "search-parameters.xml";
        else
          targetXml = Utilities.changeFileExt(target, ".profile.xml");
        
        if (!targetFileExists(targetXml) && parts.length > 1) 
          targetXml = parts[parts.length-2]+".xml";
        if (!targetFileExists(targetXml) && parts.length > 1) 
          targetXml = parts[parts.length-2].toLowerCase()+".profile.xml";
        if (!targetFileExists(targetXml)) {
          if (!Utilities.existsInList(target, "json.html", "xml.html", "rdf.html", "cqif/cqif.html", "ehrsrle/ehrsrle.html", "terminologies-systems.html", "terminology-service.html") && !target.contains("-definitions"))
            System.out.println(f+": broken reference to "+target+" (xml)");
          targetXml = null;
        }
      }
      String targetJson = targetXml == null ? null : Utilities.changeFileExt(targetXml, ".json");
      if (!targetFileExists(targetJson)) 
        targetJson = null;
      String targetTtl = targetXml == null ? null : Utilities.changeFileExt(targetXml, ".ttl");
      if (!targetFileExists(targetTtl)) 
        targetTtl = null;
      produceAsp(f, targetHtml, targetXml, targetJson, targetTtl);
    }
  }


/*
<%@ language="javascript"%>

<%
  var s = String(Request.ServerVariables("HTTP_ACCEPT"));
  if (s.indexOf("application/json+fhir") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.json2");
  else if (s.indexOf("application/fhir+json") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.json1");
  else if (s.indexOf("application/json") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.json");
  else if (s.indexOf("application/xml+fhir") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.xml2");
  else if (s.indexOf("application/fhir+xml") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.xml1");
  else if (s.indexOf("application/fhir+turtle") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.ttl");
  else if (s.indexOf("text/html") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.html");
  else if (s.indexOf("application/xml") > -1) 
    Response.Redirect("http://hl7.org/fhir/valueset-example.xml");
  else
    Response.Redirect("http://hl7.org/fhir/valueset-example.html");

%>

<!DOCTYPE html>
<html>
<body>
You should not be seeing this page. If you do, ASP has failed badly.
</body>
</html>  
 */
  private static final String HEADER = "<%@ language=\"javascript\"%>\r\n\r\n<%\r\n  var s = String(Request.ServerVariables(\"HTTP_ACCEPT\"));\r\n  if (s.indexOf(\"text/html\") > -1) \r\n";
  private static final String FOOTER = "  else\r\n    Response.Redirect(\"http://hl7.org/fhir/$$$$\");\r\n\r\n%>\r\n\r\n<!DOCTYPE html>\r\n<html>\r\n<body>\r\nYou should not be seeing this page. If you do, ASP has failed badly.\r\n</body>\r\n</html>";

  private void produceAsp(String filename, String targetHtml, String targetXml, String targetJson, String targetTtl) throws IOException {
    StringBuilder b = new StringBuilder();
    b.append(HEADER);
    b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetHtml+"\");\r\n");
    if (targetTtl != null) {
      b.append("  else if (s.indexOf(\"application/fhir+turtle\") > -1)\r\n");
      b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetTtl+"\");\r\n");
    }
    if (targetJson != null) {
      b.append("  else if (s.indexOf(\"application/json+fhir\") > -1)\r\n");
      b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetJson+"2\");\r\n");
      b.append("  else if (s.indexOf(\"application/fhir+json\") > -1)\r\n");
      b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetJson+"1\");\r\n");
      b.append("  else if (s.indexOf(\"application/json\") > -1)\r\n");
      b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetJson+"\");\r\n");
    }
    if (targetXml != null) {
      b.append("  else if (s.indexOf(\"application/xml+fhir\") > -1)\r\n");
      b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetXml+"2\");\r\n");
      b.append("  else if (s.indexOf(\"application/fhir+xml\") > -1)\r\n");
      b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetXml+"1\");\r\n");
      b.append("  else if (s.indexOf(\"application/xml\") > -1)\r\n");
      b.append("    Response.Redirect(\"http://hl7.org/fhir/"+targetXml+"\");\r\n");
    }
    b.append(FOOTER.replace("$$$$", targetHtml));
    TextFile.stringToFile(b.toString(), filename);
    
  }

  private boolean targetFileExists(String target) {
    if (target == null)
      return false;
    if (target.contains("#"))
      target = target.substring(0, target.indexOf("#"));
    return new File("F:\\fhir\\web\\"+target.replace("\\", "/")).exists();
  }

  private String readExistingAsp(String f) throws FileNotFoundException, IOException {
    String asp = TextFile.fileToString(f);
    String[] fragments = asp.split("\\\"");
    return fragments[11].substring(20);
  }

  private List<String> filesByType(String dir, String ext) {
    File d = new File(dir);
    List<String> res = new ArrayList<String>();
    for (String f : d.list()) {
      if (f.endsWith(ext)) {
        res.add(path(dir, f));
      }
    }
    return res;
  }

  private List<String> directories(String dir) {
    File d = new File(dir);
    List<String> res = new ArrayList<String>();
    for (String f : d.list()) {
      if (new File(path(dir, f)).isDirectory()) {
        res.add(path(dir, f));
      }
    }
    return res;
  }

  private boolean isSubVersion(String dir) {
    return new File(path(dir, "version.info")).exists();
  }

}
