package org.hl7.fhir.tools.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.r4.formats.JsonParser;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.publisher.NPMPackageGenerator;
import org.hl7.fhir.igtools.publisher.NPMPackageGenerator.Category;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ImplementationGuide;
import org.hl7.fhir.r4.model.ImplementationGuide.SPDXLicense;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class SpecNPMPackageGenerator {

  public static void main(String[] args) throws FileNotFoundException, IOException, FHIRException {
//    generateForVersion("C:\\work\\org.hl7.fhir\\build\\publish", );
//    generateForVersion("F:\\fhir\\web\\DSTU2", new org.hl7.fhir.dstu2.formats.JsonParser());
//    generateForVersion("F:\\fhir\\web\\2016May", new org.hl7.fhir.dstu2016may.formats.JsonParser());
  generateForVersion("F:\\fhir\\web\\STU3");

  }

  private static void generateForVersion(String folder) throws FileNotFoundException, IOException, FHIRException {
    SpecNPMPackageGenerator self = new SpecNPMPackageGenerator();
    self.generate(folder);
  }

  public void generate(String folder) throws FileNotFoundException, IOException, FHIRException {
    System.out.println("Generate Package for "+folder);
    ImplementationGuide ig = new ImplementationGuide();
    ig.setUrl("http://hl7.org/fhir");
    ig.setVersion(readVersion(folder));
    ig.setFhirVersion(ig.getVersion());
    ig.setLicense(SPDXLicense.CC01_0);
    ig.setTitle("FHIR Core package");
    ig.setDescription("FHIR Core package - the NPM package that contains all the definitions for the base FHIR specification");
    ig.setPublisher("HL7 Inc");
    ig.getContactFirstRep().getTelecomFirstRep().setSystem(ContactPointSystem.URL).setValue("http://hl7.org/fhir");
    ig.setPackageId("hl7.fhir.core");
    
    JsonParser p = new org.hl7.fhir.r4.formats.JsonParser();
    Set<String> resources = new HashSet<>();
    NPMPackageGenerator npm = new NPMPackageGenerator(Utilities.path(folder, "package.tgz"), ig);
    for (String fn : new File(folder).list()) {
      if (fn.endsWith(".json") && !fn.endsWith("canonical.json")) {
        byte[] b = TextFile.fileToBytes(Utilities.path(folder, fn));
        if (isResource(b)) {
          try {
            Resource r = p.parse(b);
            if (r instanceof Bundle && ((Bundle) r).getType() == BundleType.COLLECTION) {
              for (BundleEntryComponent e : ((Bundle) r).getEntry()) {
                if (e.hasResource()) {
                  String id = e.getResource().fhirType()+"-"+e.getResource().getId();
                  if (!resources.contains(id)) {
                    resources.add(id);
                    System.out.println("Add resource "+e.getResource().getId()+" from "+fn);
                    npm.addFile(Category.RESOURCE, id+".json", p.composeBytes(e.getResource()));
                  }              
                }
              }
            } else {
              String id = r.fhirType()+"-"+r.getId();
              if (!resources.contains(id) && isIncludedResource(r.fhirType())) {
                resources.add(id);
                System.out.println("Add resource "+fn);
                npm.addFile(Category.RESOURCE, id+".json", b);
              }
            }
          } catch (Throwable e) {
            System.out.println("Error parsing "+fn+": "+e.getMessage());
          }
        }
      }
      if (fn.endsWith(".schema.json") || fn.endsWith(".openapi.json") ) {
        byte[] b = TextFile.fileToBytes(Utilities.path(folder, fn));
        System.out.println("Add resource "+fn);
        npm.addFile(Category.OPENAPI, fn, b);
      }
      if (fn.endsWith(".xsd") || fn.endsWith(".sch") ) {
        byte[] b = TextFile.fileToBytes(Utilities.path(folder, fn));
        System.out.println("Add resource "+fn);
        npm.addFile(Category.SCHEMATRON, fn, b);
      }
    }
    npm.finish();
        
    System.out.println("Done");
  }

  private boolean isResource(byte[] b) {
    String s = new String(b).replace(" ", "");
    return s.contains("\"resourceType\":\""); 
  }

  private boolean isIncludedResource(String resourceType) {
    return resourceType != null && Utilities.existsInList(resourceType, "CapabilityStatement", "Conformance", "StructureDefinition", "ImplementationGuide",  "SearchParameter", "MessageDefinition", 
        "OperationDefinition", "CompartmentDefinition", "StructureMap", "GraphDefinition", "ExampleScenario", 
        "CodeSystem", "ValueSet", "ConceptMap", "ExpansionProfile", "NamingSystem", "TerminologyCapabilities");
  }

  private String readVersion(String folder) throws IOException {
    IniFile ini = new IniFile(Utilities.path(folder, "version.info"));
    return ini.getStringProperty("FHIR", "version");
  }

}
