package org.hl7.fhir.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BulkDelete {


  private static final String DIR = "C:\\work\\org.hl7.fhir\\build\\vscache\\validation.cache";
  private static final String PATTERN = "v3";

  public static void main(String[] args) throws FileNotFoundException, IOException {
    for (File f : new File(DIR).listFiles()) {
      if (!f.isDirectory()) {
        String s = TextFile.fileToString(f);
        if (s.contains(PATTERN)) {
          System.out.println("delete "+f.getAbsolutePath());
          f.delete();
        }
      }

    }
  }

}
