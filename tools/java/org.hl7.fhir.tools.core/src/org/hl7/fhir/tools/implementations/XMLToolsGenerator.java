package org.hl7.fhir.tools.implementations;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;

/**
 * Objective C generator. Based off the CSharpGenerator (November-2013)
 * 
 * @author Andrew Willison
 * 
 */
public class XMLToolsGenerator extends BaseGenerator implements PlatformGenerator {

  @Override
  public String getName() {
    return "xmltools";
  }

  @Override
  public String getTitle() {
    return "XML Tools";
  }

  @Override
  public String getDescription() {
    return "Document Rendering Stylesheet, supplementary implementation schemas";
  }

  @Override
  public boolean isECoreGenerator() {
    return false;
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
    ZipGenerator zip = new ZipGenerator(Utilities.path(destDir, getReference(version)));
    zip.addFolder(Utilities.path(implDir, ""), "", false);
    zip.close();
  }

  @Override
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, Logger logger, String svnRevision) throws Exception {
    throw new UnsupportedOperationException("not supported");

  }

  @Override
  public boolean doesCompile() {
    return false;
  }

  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) {
    return false;
  }

  @Override
  public boolean doesTest() {
    return false;
  }

  @Override
  public void loadAndSave(String rootDir, String sourceFile, String destFile) throws Exception {
  }

  @Override
  public String checkFragments(String rootDir, String fragments, boolean inProcess) throws Exception {
    throw new UnsupportedOperationException("not supported");   
  }

  @Override
  public String getVersion() {
    return "0.01";
  }

}
