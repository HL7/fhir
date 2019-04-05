package org.hl7.fhir.igtools.publisher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class ImageUpdater {
  private String source;
  private String filename;

  public static void main(String[] args) throws FileNotFoundException, IOException {
    ImageUpdater self = new ImageUpdater();
    self.source = args[0];
    self.filename = args[2];
    self.process(new File(args[1]));
    System.out.println("Done");
  }

  private void process(File folder) throws FileNotFoundException, IOException {
    System.out.println("Folder: "+folder.getAbsolutePath());
    for (File f : folder.listFiles()) {
      if (f.isDirectory()) {
        process(f);
      }
      if (f.getName().equals(filename)) {
        System.out.println("  Overwrite "+f.getAbsolutePath()+" with "+source);
        IOUtils.copy(new FileInputStream(source), new FileOutputStream(f));
      }
    }
  }

}
