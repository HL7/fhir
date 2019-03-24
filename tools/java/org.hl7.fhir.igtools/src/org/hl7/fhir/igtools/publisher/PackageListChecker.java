package org.hl7.fhir.igtools.publisher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PackageListChecker {

  private String folder;
  
  public PackageListChecker(String folder) {
    this.folder = folder;
  }

  public String check(String canonical, String packageId, String version) throws IOException {
    String plfn = Utilities.path(folder, "package-list.json");
    File f = new File(plfn);
    if (!f.exists())
      return "No package-list.json file found in "+folder;
    else {
      JsonObject json = null;
      try {
        json = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(f));
      } catch (Exception e) {
        return "Error parsing Package List: " +e.getMessage();
      }
      List<String> errors = new ArrayList<String>();
      if (!json.has("package-id"))
        errors.add("No Package Id");
      else if (!json.get("package-id").getAsString().equals(packageId))
        errors.add("package-id is wrong - is '"+json.get("package-id").getAsString()+"' should +'"+packageId+"'");      
      if (!json.has("canonical"))
        errors.add("No Canonical URL");
      else if (json.get("canonical").getAsString().equals(canonical))
        errors.add("canonical is wrong - is '"+json.get("canonical").getAsString()+"' should +'"+canonical+"'");

      JsonArray list = json.getAsJsonArray("list");
      boolean found = false;
      for (JsonElement n : list) {
        JsonObject o = (JsonObject) n;
        if (o.has("version") && o.get("version").getAsString().equals(version)) {
          found = true;
          if (!o.has("desc") && !o.has("changes"))
            errors.add("package-list entry must have a 'desc' or 'changes' (or both)");
          if (!o.has("date"))
            errors.add("package-list entry must have a 'desc' (though the value doesn't matter)");
          if (!o.has("status"))
            errors.add("package-list entry must have a 'status' that describes the ballot status");
          if (!o.has("sequence"))
            errors.add("package-list entry must have a 'sequence' that describes the ballot goal");
          if (!o.has("path"))
            errors.add("package-list entry must have a 'path' where it will be published");
          else if (!o.get("path").getAsString().startsWith(canonical))
            errors.add("package-list entry must have a 'path' that starts with the canonical");
          if (errors.size() > 0)
            return "Errors: "+errors.toString();              
          else
            return "All ok (path - "+o.get("path").getAsString();              
            
        }
      }
      if (!found)
        errors.add("No entry found for version "+version);     
      return "Errors: "+errors.toString();              
    }
  }

}
