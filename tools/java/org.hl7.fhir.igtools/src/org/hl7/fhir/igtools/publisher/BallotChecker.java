package org.hl7.fhir.igtools.publisher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BallotChecker {

  private String folder;
  
  public BallotChecker(String folder) {
    this.folder = folder;
  }

  public String check(String canonical, String packageId, String version, String historyPage, String fhirVersion) throws IOException { 
    if (!canonical.contains("hl7.org") && !canonical.contains("fhir.org"))
      return "<ul><li>n/a - not an HL7.org or FHIR.org implementation guide</li></ul>\r\n";
    
    List<String> errors = new ArrayList<String>();
    if (canonical.contains("fhir.org")) {
      if (!Utilities.existsInList(historyPage, Utilities.pathURL(canonical, "history.html"), Utilities.pathURL(canonical, "history.shtml")))
        errors.add("History Page '"+historyPage+"' is wrong (ig.json#paths/history) - must be '"+Utilities.pathURL(canonical, "history.html")+"' or '"+Utilities.pathURL(canonical, "history.shtml")+"'");
    } else {
      if (!Utilities.existsInList(historyPage, Utilities.pathURL(canonical, "history.html"), Utilities.pathURL(canonical, "history.cfml")))
      errors.add("History Page '"+historyPage+"' is wrong (ig.json#paths/history) - must be '"+Utilities.pathURL(canonical, "history.html")+"' or '"+Utilities.pathURL(canonical, "history.cfml")+"'");
    }
    
    JsonObject json = null;
    String plfn = Utilities.path(folder, "package-list.json");
    File f = new File(plfn);
    if (!f.exists())
      errors.add("package-list.json: file not found in "+folder);
    else {
      try {
        json = JsonTrackingParser.parseJson(f);
      } catch (Exception e) {
        errors.add("package-list.json: " +e.getMessage());
      }
    }
    
    if (json != null) {
      if (!json.has("package-id"))
        errors.add("package-list.json: No Package Id");
      else if (!json.get("package-id").getAsString().equals(packageId))
        errors.add("package-list.json: package-id is wrong - is '"+json.get("package-id").getAsString()+"' should be '"+packageId+"'");
      
      if (!json.has("canonical"))
        errors.add("package-list.json: No Canonical URL");
      else if (!json.get("canonical").getAsString().equals(canonical))
        errors.add("package-list.json: canonical is wrong - is '"+json.get("canonical").getAsString()+"' should be '"+canonical+"'");

      JsonArray list = json.getAsJsonArray("list");
      boolean found = false;
      for (JsonElement n : list) {
        JsonObject o = (JsonObject) n;
        if (o.has("version") && o.get("version").getAsString().equals(version)) {
          found = true;
          if (!o.has("desc") && !o.has("descmd") && !o.has("changes"))
            errors.add("package-list.json entry: must have a 'desc' / 'descmd' or 'changes' (or both)");
          if (!o.has("date"))
            errors.add("package-list.json entry: must have a 'date' (though the value doesn't matter)");
          if (!o.has("status"))
            errors.add("package-list.json entry: must have a 'status' that describes the ballot status");
          if (!o.has("sequence"))
            errors.add("package-list.json entry: must have a 'sequence' that describes the ballot goal");
          if (!o.has("fhir-version"))
            errors.add("package-list.json entry: must have a 'fhir-version' that specifies the FHIR version ("+fhirVersion+")");
          else if (!o.get("fhir-version").getAsString().equals(fhirVersion))
            errors.add("package-list.json entry: must have a 'fhir-version' that entry with the right value - is '"+o.get("fhir-version").getAsString()+"', should start with '"+fhirVersion+"'");
          if (!o.has("path"))
            errors.add("package-list.json entry: must have a 'path' where it will be published");
          else if (!o.get("path").getAsString().startsWith(canonical))
            errors.add("package-list.json entry: must have a 'path' that starts with the canonical (is '"+o.get("path").getAsString()+"', should start with '"+canonical+"'");
          if (errors.size() == 0)
            errors.add("All ok (path - "+o.get("path").getAsString()+")");              
            
        }
      }
      if (!found)
        errors.add("package-list.json: No entry found for version "+version);
    }
    StringBuilder b = new StringBuilder();
    b.append("<ul>\r\n");
    for (String s : errors) {
      b.append("  <li>"+Utilities.escapeXml(s)+"</li>\r\n");
    }
    b.append("</ul>\r\n");
    return b.toString();              
  }

}
