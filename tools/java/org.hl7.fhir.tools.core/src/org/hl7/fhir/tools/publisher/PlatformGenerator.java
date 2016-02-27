package org.hl7.fhir.tools.publisher;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.utilities.Logger;

public interface PlatformGenerator {

  /**
   * @return The name of the generator - used to show progress in log reports - must be a valid java token, and a filename, all lowercase    
   */
  public String getName();

  /**
   * Formal name under which the implementation is shown in FHIR 
   * @return
   */
  public String getTitle();
  
  /**
   * @return a string description of what the reference implementation produces for an implementer, along with an estimate of status, and dependencies
   */
  public String getDescription(String version, String svnRevision);

  /**
   * @return the URL for the download. In principle, the URL should be a link to a generated file with the format fhir-[version]-[title]-[reference impl version]
   */
  public String getReference(String version);
  
  /**
   * @return the version of the reference implementation
   */
  public String getVersion();
    
  /**
   * Actually generate the reference implementation. The reference generator must generate a zip file [name].zip in the dst dir where
   * [name] is the name returned by getName(), and the zip file contains the contents of the reference implementation. The routine should 
   * throw an exception if the generation fails.
   * 
   * @param definitions - the logical definitions that are FHIR
   * @param destDir - the destination directory, where the .zip file is to go (has a path separator appended)
   * @param implDir - the folder in the /implementations directory in the FHIR subversion tree, if the generator wants to store stuff in subversion (has a path separator appended)
   * @param version - the version of FHIR that is being published
   * @param genDate - the official date of publication (the start of the build process)
   * @param logger - so that the generator can log issues/errors/progress to the same place as the overall build process
   * @param svnRevision TODO
   */
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision)  throws Exception;
  

  /**
   * todo: should this be part of the generate or not?
   * 
   * @return true if the platform generator is able to take a compile and build the generated code.
   */
  public boolean doesCompile();
  
 
  /**
   * Compile the generated code.
   * 
   * @param errors - a list of errors from the compile
   * @return true if the compile succeeded
   * @throws Exception 
   */
  public boolean compile(String rootDir, List<String> errors, Logger logger, List<ValidationMessage> issues) throws Exception;
  

  /**
   * Whether to list this in the downloads page
   * @return
   */
  public boolean wantListAsDownload();
  
}
