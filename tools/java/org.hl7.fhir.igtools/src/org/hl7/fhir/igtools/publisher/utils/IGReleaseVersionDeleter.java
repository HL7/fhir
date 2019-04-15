package org.hl7.fhir.igtools.publisher.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JSONUtil;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.JsonObject;

/**
 * This deletes all the current files for a directory that contains the root of an IG without deleting the hitory infrastructure or past versions
 * 
 * @author graha
 *
 */
public class IGReleaseVersionDeleter {

  public void clear(String folder, String historyRepo) throws IOException, FHIRException {
    List<String> igs = listIgs(folder);
    List<String> hist = listHistoryFolder(historyRepo);
    hist.add("package-list.json");
    int i = 0;
    for (File f : new File(folder).listFiles()) {
      String rn = f.getAbsolutePath().substring(folder.length()+1);
      if (!igs.contains(rn) && !hist.contains(rn)) {
          System.out.println("Delete "+rn);
          if (f.isDirectory())
            Utilities.clearDirectory(f.getAbsolutePath());
          f.delete();
          i++;
      }
    }
    System.out.println("Cleaned current release of "+folder+": deleted "+i+" files");
  }

  private List<String> listHistoryFolder(String historyRepo) {
    List<String> files = new ArrayList<>();
    listFiles(files, historyRepo, new File(historyRepo));
    return files;
  }

  private void listFiles(List<String> files, String base, File folder) {
    for (File f : folder.listFiles()) {
      if (!f.getName().equals(".git")) {
        files.add(f.getAbsolutePath().substring(base.length()+1));
        if (f.isDirectory())
          listFiles(files, base, f);
      }
    }
  }

  private List<String> listIgs(String folder) throws IOException, FHIRException {
    String pl = Utilities.path(folder, "package-list.json");
    if (!new File(pl).exists())
      throw new FHIRException("Folder '"+folder+"' is not a valid IG directory");
    JsonObject json = JsonTrackingParser.parseJsonFile(pl);
    List<String> igs = new ArrayList<>();
    String canonical = JSONUtil.str(json, "canonical");
    for (JsonObject obj : JSONUtil.objects(json, "list")) {
      String path = JSONUtil.str(obj, "path");
      if (path.startsWith(canonical)) {
        igs.add(path.substring(canonical.length()+1));
      }
    }
    return igs;
  }
  

  public static void main(String[] args) throws FileNotFoundException, IOException, FHIRException {
    new IGReleaseVersionDeleter().clear(args[0], args[1]);
  }
  
}
