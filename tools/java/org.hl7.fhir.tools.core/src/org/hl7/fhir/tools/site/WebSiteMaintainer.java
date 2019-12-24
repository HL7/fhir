package org.hl7.fhir.tools.site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JSONUtil;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WebSiteMaintainer {

  public static void main(String[] args) throws IOException {
    String root = Utilities.getDirectoryForFile(args[0]);
    List<JsonObject> pubs = new ArrayList<>();
    List<String> dirs = new ArrayList<>();
    JsonObject cv = null;
    JsonObject pl = JsonTrackingParser.parseJsonFile(args[0]);
    for (JsonObject v : JSONUtil.objects(pl, "list")) {
      String ver = JSONUtil.str(v, "version");
      if (!"current".equals(ver)) {
        String p = JSONUtil.str(v, "path").substring(20);
        v.addProperty("directory", Utilities.path(root, p));
        dirs.add(Utilities.path(root, p));
        pubs.add(v);
        if (v.has("current") && v.get("current").getAsBoolean())
          cv = v;
      }
    }
    for (JsonObject v : pubs) {
      new WebSiteReleaseUpdater(root, JSONUtil.str(v,  "directory"), v, cv).execute(null);
    }
    new WebSiteReleaseUpdater(root, root, cv, cv).execute(dirs);
  }

}
