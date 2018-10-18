package org.hl7.fhir.tools.converters;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class OldVersionRedirector {

  public static void main(String[] args) throws Exception {
    new OldVersionRedirector().processVersions("F:\\fhir\\web", "F:\\fhir\\webgen");
  }

  private void processVersions(String src, String dst) throws Exception {
    for (File f : new File(src).listFiles()) {
      if (f.isDirectory()) {
        if (new File(Utilities.path(f.getAbsolutePath(), "version.info")).exists()) {
          processVersion(f, dst, Utilities.pathURL("http://hl7.org/fhir", f.getName()));
        }
      }
    }
  }

  private void processVersion(File f, String dst, String url) throws Exception {
    String s = TextFile.fileToString(Utilities.path(f.getAbsolutePath(), "version.info"));
    IniFile ini = new IniFile(new ByteArrayInputStream(s.getBytes()));
    String ver = ini.getStringProperty("FHIR", "version");
    ver = ver.substring(0, 3);
    System.out.println("Process "+f.getAbsolutePath()+" as version "+ver);
    processVersionFiles(f, Utilities.path(dst, ver), ver, url);
  }

  private void processVersionFiles(File folder, String dst, String ver, String url) throws Exception {
    for (File f : folder.listFiles()) {
      if (f.getName().endsWith(".profile.json")) {
        try {
          processProfile(f.getAbsolutePath(), dst, ver, url);
        } catch (Exception e) {
          throw new Exception("error processing "+f.getAbsolutePath()+": "+e.getMessage(), e);
        }
      }
    }    
  }

  private void processProfile(String profile, String dst, String ver, String url) throws JsonSyntaxException, FileNotFoundException, IOException {
    JsonObject json = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(profile));
    if (json.has("kind")) {
      if (!"resource".equals(json.get("kind").getAsString()))
        return;
    } else if (!json.has("type") || !"resource".equals(json.get("type").getAsString()))
      return;
    if (json.has("derivation") && "constraint".equals(json.get("derivation").getAsString()))
      return;
    
    String name = json.get("name").getAsString();
    if (name.contains(" "))
      return;
    
    System.out.println("  "+name);
    String htmlPath = Utilities.pathURL(url, name.toLowerCase()+".html"); 
    String techPath = Utilities.pathURL(url, name.toLowerCase()+".profile"); 
    String asp = produceAsp(htmlPath, techPath);
    String path = Utilities.path(dst, "StructureDefinition", name, "index.asp");
    Utilities.createDirectory(Utilities.getDirectoryForFile(path));
    TextFile.stringToFile(asp, path);
  }

  private String produceAsp(String htmlPath, String techPath) {
    return "<%@ language=\"javascript\"%>\r\n"+
        "\r\n"+
        "<%\r\n"+
        "  var s = String(Request.ServerVariables(\"HTTP_ACCEPT\"));\r\n"+
        "  if (s.indexOf(\"json\") > -1) \r\n"+
        "    Response.Redirect(\""+techPath+".json\");\r\n"+
        "  else if (s.indexOf(\"xml\") > -1) \r\n"+
        "    Response.Redirect(\""+techPath+".xml\");\r\n"+
        "  else\r\n"+
        "    Response.Redirect(\""+htmlPath+".html\");\r\n"+
        "\r\n"+
        "%>\r\n"+
        "\r\n"+
        "<!DOCTYPE html>\r\n"+
        "<html>\r\n"+
        "<body>\r\n"+
        "You should not be seeing this page. If you do, ASP has failed badly.\r\n"+
        "</body>\r\n"+
        "</html>\r\n";


  }

  private void copyFile(String src, String dst) throws IOException {
    Utilities.createDirectory(Utilities.getDirectoryForFile(dst));
    FileUtils.copyFile(new File(src), new File(dst), true);
  }
}
