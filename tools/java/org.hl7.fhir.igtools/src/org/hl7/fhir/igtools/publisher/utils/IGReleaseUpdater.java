package org.hl7.fhir.igtools.publisher.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class IGReleaseUpdater {

  private String folder;
  private String url;
  private String rootFolder;

  public IGReleaseUpdater(String folder, String url, String rootFolder) {
    this.folder = folder;
    this.url = url;
    this.rootFolder = rootFolder;
  }

  public void check()  {
    List<String> errs = new ArrayList<>(); 
    try {
      String f = Utilities.path(folder, "package-list.json");
      if (!new File(f).exists())
        errs.add("unable to find package-list.json");
      else {
        JsonObject json = JsonTrackingParser.parseJsonFile(f);
        String canonical = json.get("canonical").getAsString();
        JsonArray list = json.getAsJsonArray("list");
        JsonObject root = null;
        for (JsonElement n : list) {
          JsonObject o = (JsonObject) n;
          if (!o.has("version"))
           throw new Error(folder+" has Version without version");
          if (!o.get("version").getAsString().equals("current")) {
            if (o.has("current"))
              root = o;
          }
        }
        
        List<String> folders = new ArrayList<>();
        for (JsonElement n : list) {
          JsonObject o = (JsonObject) n;
          if (!o.get("version").getAsString().equals("current")) {
            String v = o.get("version").getAsString();
            if (!o.has("path"))
              errs.add("version "+v+" has no path'"); 
            else {
              String path = o.get("path").getAsString();;
              String vf = Utilities.path(path.replace(url, rootFolder));
              if (!path.endsWith(".html")) {
                if (!(new File(vf).exists()))
                  errs.add("version "+v+" path "+vf+" not found (canonical = "+canonical+", path = "+path+")");
                else {
                  folders.add(vf);
                  updateStatement(vf, null, json, o, errs, root, canonical);
                }
              }
              if (o.has("current"))
                root = o;
            }
          }
        }
        if (root != null)
          updateStatement(folder, folders, json, root, errs, root, canonical);
      }
    } catch (Exception e) {
      errs.add(e.getMessage());
    }
    if (errs.size() == 0)
      System.out.println(": ok");
    else {
      System.out.println("");
      for (String s : errs) {
        System.out.println("    "+s);
      }      
    }
  }

  private void updateStatement(String vf, List<String> ignoreList, JsonObject ig, JsonObject version, List<String> errs, JsonObject root, String canonical) throws FileNotFoundException, IOException {
    String fragment = genFragment(ig, version, root, canonical);
    System.out.println("  "+vf+": "+fragment);
    IGReleaseVersionUpdater igvu = new IGReleaseVersionUpdater(vf, ignoreList, version);
    igvu.updateStatement(fragment);
    System.out.println("    .. "+igvu.getCount()+" files updated");
    IGReleaseRedirectionBuilder rb = new IGReleaseRedirectionBuilder(vf, canonical, version.get("path").getAsString());
    rb.buildAspRedirections();
    System.out.println("    .. "+rb.getCountTotal()+" redirections ("+rb.getCountUpdated()+" created/updated)");
  }

  /**
   * The fragment of HTML this generates has 3 parts 
   * 
   * 1. statement of what this is 
   * 2. reference to current version
   * 3. referenceto list of published versions
   * 
   * @param version
   * @param root
   * @param canonical
   * @return
   */
  private String genFragment(JsonObject ig, JsonObject version, JsonObject root, String canonical) {
    String p1 = ig.get("title").getAsString()+" (v"+version.get("version").getAsString()+": "+state(ig, version)+").";
    String p2 = root == null ? "" : version == root ? " This is the current published version. " : " The current version is <a href=\""+(root.get("path").getAsString().startsWith(canonical) ? canonical : root.get("path").getAsString())+"\">"+root.get("version").getAsString()+"</a>.";
    String p3 = " See the <a href=\""+Utilities.pathURL(canonical, "history.cfml")+"\">Directory of published versions</a>";
    return p1+p2+p3;
  }

  private String state(JsonObject ig, JsonObject version) {
    String status = version.get("status").getAsString();
    String sequence = version.get("sequence").getAsString();
    String desc = version.get("sequence").getAsString();
    if ("trial-use".equals(status))
      return decorate(sequence);
    else if ("ballot".equals(status))
      return decorate(sequence)+" Ballot "+ballotCount(ig, sequence, version);
    else if ("draft".equals(status))
      return decorate(sequence)+" Draft";
    else 
      throw new Error("unknown status "+status);
  }

  private String decorate(String sequence) {
    return sequence.replace("STU", "<a href=\"https://confluence.hl7.org/display/HL7/HL7+Balloting\" title=\"Standard for Trial-Use\">STU</a>");
  }

  private String ballotCount(JsonObject ig, String sequence, JsonObject version) {
    int c = 1;
    JsonArray list = ig.getAsJsonArray("list");
    for (int i = list.size() - 1; i >= 0; i--) {
      JsonObject o = (JsonObject) list.get(i);
      if (o == version)
        return Integer.toString(c);
      if (sequence.equals(o.get("sequence").getAsString()) && "ballot".equals(version.get("status").getAsString()))
        c++;
    }
    return "1";
  }

}
