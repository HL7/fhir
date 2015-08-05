package org.hl7.fhir.definitions.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.DiagnosticReport;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Observation;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;

/**
 * To run the resource order updater, do things in this order:
 * 
 * - Get everything running so the build completes with no errors 
 * - make sure no one else is working on the same resource
 * - create a proposed-order on the first tab in the spreadsheet (under fmm etc), and put in it a comma seperated list of top level elements in your proposed order  
 * - save the file, and run a full build. If the order is not acceptable to the tool, validation will fail 
 * - repeat above until you have an order that you are happy with, and is acceptable to the tool (you will see the words 'build order ok' in the build log) 
 * - reorder the elements in the spreadsheet
 * - do a build (a full build - most important) and wait until the java compile is complete. wait for it to get to validation. that bit should fail (old resources are not not valid)
 * - run the file "build\tools\java\copy-current-generated-code.bat" (needs windows). Ignore any difference analysis that appears (unless you know why not to)
 * - recompile (automatic if you run publish batch file, or refresh in eclipse etc)
 * - run this class with the build directory as the parameter
 * - now go and check all the files that it cannot convert; there are various reasons why they might fail...
 * 
 * @author Grahame Grieve
 *
 */
public class ResourceOrderUpdater {

  public static void main(String[] args) throws FileNotFoundException, Exception {
    if (args.length < 2) {
      System.out.println("Re-order Resources. 2 parameters, the path to the 'build' folder, and the type of resource to update");      
    } else {
      String folder = args[0];
      String resource = args[1];

      System.out.println("Re-order Resources of type "+resource+" in "+folder);
      
      processFolder(Utilities.path(folder, "guides"), resource);      
      processFolder(Utilities.path(folder, "source"), resource);

      System.out.println("");
      System.out.println("Finished");
    }
  }

  private static void processFolder(String path, String resource) {
    System.out.println("process "+path);
    File folder = new File(path);
    for (String filename : folder.list()) {
      File file = new File(Utilities.path(path, filename));
      if (file.isDirectory())
        processFolder(file.getAbsolutePath(), resource);
      else {
        if (filename.endsWith(".xml")) {
          try {
            if (TextFile.fileToString(file.getAbsolutePath()).contains("<"+resource)) {
              process(file.getAbsolutePath());
            }
          } catch (Exception e) {
            System.err.println("Error Processing "+file.getAbsolutePath()+": "+e.getMessage());
          }
        }
      }
    }
  }

  private static void process(String filename) throws Exception {
    System.out.println("  process "+filename);
    XmlParser xml = new XmlParser();
    xml.setOutputStyle(OutputStyle.PRETTY);
    InputStream si = new FileInputStream(filename);
    Resource r = xml.parse(si);
    si.close();
    
    OutputStream so = new FileOutputStream(filename);
    xml.compose(so, r);
    so.close();
  }

}
