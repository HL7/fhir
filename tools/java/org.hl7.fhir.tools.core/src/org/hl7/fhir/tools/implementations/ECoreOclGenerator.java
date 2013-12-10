package org.hl7.fhir.tools.implementations;
/*
Copyright (c) 2011-2013, HL7, Inc
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
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.ZipGenerator;

/*
 * 
 * 
 * 
, however because of the wide variation in how different architectures and 
tools map from UML to XML, there should be no expectation that any particular
tool will produce compatible XML from these UML diagrams
 * 
 * Systems are welcome
to use these object models as a basis for serialization internally or even
between trading partner systems, with any form of exchange technology (including 
JSON). Systems that use this form of exchange cannot claim to be conformant 
with this specification, but can describe themselves as using "FHIR consistent
object models".

 */
public class ECoreOclGenerator extends BaseGenerator implements PlatformGenerator {

  @Override
public String getName() {
    return "ecore";
  }

  @Override
public String getTitle() {
    return "ECore";
  }

  @Override
public String getDescription() {
    return "Formal Object Definitions in OCLinECore text format - under development";
  }

  @Override
public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
	  ECoreOclFormatGenerator eofg = new ECoreOclFormatGenerator(new FileOutputStream(implDir+"eCore.txt"));
	  eofg.generate(definitions, version, genDate);
	  eofg.close();

    ZipGenerator zip = new ZipGenerator(destDir+"ecore.zip");
    zip.addFiles(implDir, "", ".txt", null);
    zip.close();    
    
  }

  @Override
public boolean isECoreGenerator() {
    return false;
  }

  @Override
public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir,
      String implDir, Logger logger, String svnRevision) throws Exception {
    throw new UnsupportedOperationException("Ocl generator uses ElementDefn-style definitions.");
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
public void loadAndSave(String rootDir, String sourceFile, String destFile) {
   
  }

  @Override
public String checkFragments(String rootDir, String fragments, boolean inProcess) throws Exception {
    return "Not supported by eCore implementation";
  }
}
