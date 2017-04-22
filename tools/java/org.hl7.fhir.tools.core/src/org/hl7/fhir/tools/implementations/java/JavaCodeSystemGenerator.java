package org.hl7.fhir.tools.implementations.java;
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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.utilities.Utilities;

/*
This is used to generate an enumeration for every value set not already generated as part of the resources.

 */
public class JavaCodeSystemGenerator extends JavaBaseGenerator {

  public JavaCodeSystemGenerator(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }

  public void generate(Date genDate, String version, CodeSystem cs, String tns) throws Exception {		
    write("package org.hl7.fhir.r4.model.codesystems;\r\n");
    write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
    write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    write("\r\n");
    write("import org.hl7.fhir.exceptions.FHIRException;\r\n");
    write("\r\n");

    write("public enum "+tns+" {\r\n");
    write("\r\n");

    List<ConceptDefinitionComponent> codes = listAllCodes(cs);
    for (ConceptDefinitionComponent c : codes) {
      if (c.hasCode()) {
        String cc = Utilities.camelCase(c.getCode());
        cc = makeConst(cc);
        write("        /**\r\n");
        write("         * "+c.getDefinition()+"\r\n");
        write("         */\r\n");      
        write("        "+cc.toUpperCase()+", \r\n");
      }
    }
    write("        /**\r\n");
    write("         * added to help the parsers\r\n");
    write("         */\r\n");      
    write("        NULL;\r\n");

    write("        public static "+tns+" fromCode(String codeString) throws FHIRException {\r\n");
    write("            if (codeString == null || \"\".equals(codeString))\r\n");
    write("                return null;\r\n");
    for (ConceptDefinitionComponent c : codes) {
      if (c.hasCode()) {
        String cc = Utilities.camelCase(c.getCode());
        cc = makeConst(cc);
        write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
        write("          return "+cc+";\r\n");
      }
    }   
    write("        throw new FHIRException(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
    write("        }\r\n"); 

    write("        public String toCode() {\r\n");
    write("          switch (this) {\r\n");
    for (ConceptDefinitionComponent c : codes) {
      if (c.hasCode()) {
        String cc = Utilities.camelCase(c.getCode());
        cc = makeConst(cc);
        write("            case "+cc+": return \""+c.getCode()+"\";\r\n");
      }
    }   
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

    write("        public String getSystem() {\r\n");
    write("          return \""+cs.getUrl()+"\";\r\n");
    write("        }\r\n"); 

    write("        public String getDefinition() {\r\n");
    write("          switch (this) {\r\n");
    for (ConceptDefinitionComponent c : codes) {
      if (c.hasCode()) {
        String cc = Utilities.camelCase(c.getCode());
        cc = makeConst(cc);
        write("            case "+cc+": return \""+Utilities.escapeJava(c.getDefinition())+"\";\r\n");
      }
    }   
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

    write("        public String getDisplay() {\r\n");
    write("          switch (this) {\r\n");
    for (ConceptDefinitionComponent c : codes) {
      if (c.hasCode()) {
        String cc = Utilities.camelCase(c.getCode());
        cc = makeConst(cc);
        write("            case "+cc+": return \""+Utilities.escapeJava(Utilities.noString(c.getDisplay()) ? c.getCode() : c.getDisplay())+"\";\r\n");
      }
    }   
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 

    write("    }\r\n");
    write("\r\n");

    write("\r\n");
    write("}\r\n");
    write("\r\n");
    flush();
  }
}
