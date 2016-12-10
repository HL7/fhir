package org.hl7.fhir.tools.implementations;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.validation.ValidationMessage;

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
    return "XMLTools";
  }

  @Override
  public String getDescription(String version, String svnRevision) {
    return "Document Rendering Stylesheet, supplementary implementation schemas";
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String actualImpl, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
    ZipGenerator zip = new ZipGenerator(Utilities.path(destDir, getReference(version)));
    zip.addFolder(Utilities.path(implDir, ""), "", false);
    zip.close();
  }


  @Override
  public String getVersion() {
    return "0.01";
  }

  @Override
  public boolean doesCompile() {
    return false;
  }

  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger, List<ValidationMessage> issues) throws Exception {
    return false;
  }

}
