package org.hl7.fhir.tools.implementations.delphi;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.FolderManager;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.validation.ValidationMessage;

/**
 * This has been moved out and only s 
 * 
 * todo: the delphi reference implementation depends on too much HL7Connect infrastructure.
 * 
 * @author Grahame
 *
 */
public class DelphiGenerator extends BaseGenerator implements PlatformGenerator {


  public DelphiGenerator(FolderManager folders) {
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String actualImpl, String version, Date genDate, Logger logger, String svnRevision)  throws Exception {
  }

  @Override
  public String getName() {
    return "pascal";
  }

  @Override
  public String getDescription(String version, String svnRevision) {
    return "Resource Definitions and XML & JSON parsers, Client, Reference Server, Utilities etc. Delphi (unicode verions): [http://github.com/grahamegrieve/fhirserver](http://github.com/grahamegrieve/fhirserver)";
  }

  @Override
  public String getTitle() {
    return "Pascal";
  }

  @Override
  public String getVersion() {
    return "0.80";
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
