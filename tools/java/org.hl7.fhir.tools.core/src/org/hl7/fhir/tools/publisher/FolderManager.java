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
import java.io.File;

/**
 * This class gives easy access to the paths used by the publisher to get to
 * input files and store intermediate and output files
 * @author Ewout
 *
 */
public class FolderManager {

  
  /**
   * 
   */
  private char sl;
  
  public FolderManager(String root) {
    super();
    sl = File.separatorChar;
    rootDir = root+sl;
    srcDir = root+sl+"source"+sl;
    sndBoxDir = root+sl+"sandbox"+sl;
    termDir = srcDir+"terminologies"+sl;
    dtDir = srcDir+"datatypes"+sl;
    imgDir = root+sl+"images"+sl;
    xsdDir = root+sl+"schema"+sl;
    tmpResDir = xsdDir+"datatypes"+sl;
    tmpDir = root+sl+"temp"+sl;
    dstDir = root+sl+"publish"+sl;
    umlDir = root+sl+"uml"+sl;
    javaDir = root+sl+"tools"+sl+"java"+sl+"org.hl7.fhir.tools.core"+sl+"src"+ sl;
    archiveDir = root.substring(0, root.lastIndexOf(File.separator)+1)+"archive"+sl;
    
//    javaParserDir =  root+sl+"tools"+sl+"java"+sl+"org.hl7.fhir.tools.core"+sl+"src"+sl+"org"+sl+"hl7"+sl+"fhir"+sl+"instance"+sl+"formats"+sl;
//    cSharpDir      = root+sl+"tools"+sl+"csharp"+sl+"FHIR"+sl+"FHIR"+sl;
//    delphiDir      = root+sl+"tools"+sl+"delphi"+sl;
  }
  
  public String srcDir;
  public String sndBoxDir;
  public String imgDir;
  public String xsdDir;
  public String dstDir;
  public String umlDir;
  public String rootDir;
  public String termDir;
  public String dtDir;
  public String tmpResDir;
  public String tmpDir;
  public String javaDir;
  public String archiveDir;
  
  public String implDir(String name) {
    return rootDir+"implementations"+sl+name+sl;
  }
  
}