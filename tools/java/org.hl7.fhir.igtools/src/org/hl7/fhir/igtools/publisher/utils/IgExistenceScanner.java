package org.hl7.fhir.igtools.publisher.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IgExistenceScanner {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    System.out.println("looking for IGs in "+args[0]);
    List<String> igs = scanForIgs(args[0]);
    IGRegistryMaintainer reg = new IGRegistryMaintainer(args.length > 2 ? args[2] : null);
    System.out.println("found: ");
    for (String s : igs) {
      System.out.println(s);
      new IGReleaseUpdater(s, args[1], args[0], reg).check();
    }
    System.out.println("==================== ");
    reg.finish();
  }
  
  public static List<String> scanForIgs(String folder) {
    return scanForIgs(new File(folder), true);
  }
  
  public static List<String> scanForIgs(File folder, boolean isRoot) {
    List<String> igs = new ArrayList<>();
    boolean isIg = false;
    for (File f : folder.listFiles()) {
      if (f.getName().equals("package-list.json") && !isRoot)
        isIg = true;
    }
    if (isIg)
        igs.add(folder.getAbsolutePath());
    else { 
      for (File f : folder.listFiles()) {
        if (f.isDirectory())
          igs.addAll(scanForIgs(f, false));
      }
    }
    return igs;
  }
}
