package org.hl7.fhir.tools.implementations.csharp;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.dstu3.utils.Version;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;

public class CSharpGenerator extends BaseGenerator implements PlatformGenerator {

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String actualImpl, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
  }

  @Override
  public String getName() {
    return "csharp";
  }


  @Override
  public String getDescription(String version, String svnRevision) {
    return "Object models, Parsers/Serialisers, Validators, and a Client. The source code for that compiled .NET library can be found on GitHub at " +
        "[http://github.com/ewoutkramer/fhir-net-api](http://github.com/ewoutkramer/fhir-net-api)";
  }

  @Override
  public String getTitle() {
    return "csharp";
  }


  @Override
  public String getVersion() {
    return Version.VERSION;
  }

  @Override
  public String getReference(String version) {
    return "http://www.nuget.org/packages/Hl7.Fhir";
  }

  public String getZipFilename(String version) {
    return super.getReference(version);
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
