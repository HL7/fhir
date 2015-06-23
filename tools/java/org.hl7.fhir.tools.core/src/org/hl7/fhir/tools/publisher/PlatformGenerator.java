package org.hl7.fhir.tools.publisher;
/*
Copyright (c) 2011+, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.instance.validation.ValidationMessage;
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
   * @return returns true if this generator uses eCore for the FHIR definitions, false if it uses ElementDefn
   */
  public boolean isECoreGenerator();
  
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
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision)  throws Exception;
  
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
   * 
   * @return true if this platform can be tested as part of the run-time tests i.e. supports loadAndSave
   */
  public boolean doesTest();

  /**
   * Load the source File to the object model, then save it to the dest file 
   * Both dest and source should be XML. The build tool will check if the 
   * canonical XML of the source and dest are the same. If so, it passes
   * 
   * @param sourceFile
   * @param destFile
   * @throws Exception 
   */
  public void loadAndSave(FolderManager folders, String sourceFile, String destFile) throws Exception;
  
  /**
   * Load a set of source Files to the object model, then save them to [tmp]\*.xx.xml
   * where xx is getName()
   * 
   * Both dest and source should be XML. The build tool will check if the 
   * canonical XML of the source and dest are the same. If so, it passes
   * 
   */
  public void test(FolderManager folders, Collection<String> names) throws Exception;
  
  /**
   * Used during the build to check the syntactical validity of fragments. We use the 
   * java generated code rather than the schemas because the fragments are quite often
   * incomplete, and we mainly want to know whether they include things that are not known
   *  
   */
  public String checkFragments(FolderManager folders, String fragmentsXml) throws Exception;

  /**
   * Whether to list this in the downloads page
   * @return
   */
  public boolean wantListAsDownload();
  
  /** 
   * find out whether the reference implementation supports the sign and verify
   *  
   * @return
   */
  public boolean canSign();

  /**
   * Sign a provenance resource or an atom feed
   * 
   * @param filename - the file name to sign
   * @param atom - whether this is an atome feed or a provenance resource
   * @param type - which type of certificate to use (rsa, dsa, or ecdsa)
   * 
   * @throws Exception
   */
  public void sign(String filename, boolean atom, String type) throws Exception;
  
  /**
   * Verify that the provenance resource or an atom feed has a valid signature
   * 
   * @param filename - name of the file or bundle (xml or json)
   * @throws Exception 
   */
  public void verify(String filename) throws Exception;
  
}
