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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

/*
changes for James
- lazy construction of lists
- getX will construct if null
- add hasX
  
*/
public class JavaEnumerationsGenerator extends JavaBaseGenerator {
  private Map<String, String> enumInfo;
  private Map<String, String> adornments;
  
  
	public JavaEnumerationsGenerator(OutputStream out, Definitions definitions, Map<String, String> enumInfo, Map<String, String> adornments) throws UnsupportedEncodingException {
		super(out);
		this.definitions = definitions;
    this.enumInfo = enumInfo;
    this.adornments = adornments;
	}

	public void generate(Date genDate, String version) throws Exception {		
		write("package org.hl7.fhir.r5.model;\r\n");
		write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
		write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    write("\r\n");
    write("import org.hl7.fhir.instance.model.api.*;\r\n");
    write("import org.hl7.fhir.exceptions.FHIRException;\r\n");
    write("\r\n");

    write("public class Enumerations {\r\n");
    write("\r\n");
    write("// In here: \r\n");
		
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getCommonBindings().keySet());
    Collections.sort(names);
    for (String n : names) {
      BindingSpecification bs = definitions.getCommonBindings().get(n);
      if (bs.getBinding() == BindingMethod.CodeList || bs.getBinding() == BindingMethod.Special)
        write("//   "+bs.getValueSet().getName()+": "+bs.getDefinition()+"\r\n");
    }
    write("\r\n");
    write("\r\n");
    for (String n : names) {
      BindingSpecification bs = definitions.getCommonBindings().get(n);
      if (bs.getBinding() == BindingMethod.CodeList || bs.getBinding() == BindingMethod.Special)
			  generateEnum(bs);
		}
		write("\r\n");
		write("}\r\n");
		write("\r\n");
		flush();
	}

	private void generateEnum(BindingSpecification cd) throws Exception {
    String tns = cd.getValueSet().getName();
    cd.getValueSet().setUserData("java-generated", true);
    String url = cd.getValueSet().getUrl();
    CommaSeparatedStringBuilder el = new CommaSeparatedStringBuilder();

		write("    public enum "+tns+" {\r\n");
		int l = cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true).size();
		int i = 0;
		for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
				i++;
				String cc = Utilities.camelCase(c.getCode());
	      cc = makeConst(cc);
	      el.append(cc);

	      write("        /**\r\n");
	      write("         * "+c.getDefinition()+"\r\n");
	      write("         */\r\n");      
				write("        "+cc.toUpperCase()+", \r\n");
			}
    write("        /**\r\n");
    write("         * added to help the parsers\r\n");
    write("         */\r\n");      
    write("        NULL;\r\n");
    el.append("NULL");


		write("        public static "+tns+" fromCode(String codeString) throws FHIRException {\r\n");
		write("            if (codeString == null || \"\".equals(codeString))\r\n");
		write("                return null;\r\n");
		for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
				String cc = Utilities.camelCase(c.getCode());
				cc = makeConst(cc);
				write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
				write("          return "+cc+";\r\n");
			}
		write("        throw new FHIRException(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
		write("        }\r\n");	

		write("        public String toCode() {\r\n");
		write("          switch (this) {\r\n");
		for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
				String cc = Utilities.camelCase(c.getCode());
	      cc = makeConst(cc);
				write("            case "+cc+": return \""+c.getCode()+"\";\r\n");
			}
		write("            default: return \"?\";\r\n");
		write("          }\r\n"); 
		write("        }\r\n"); 

    write("        public String getSystem() {\r\n");
    write("          switch (this) {\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
	      String cc = Utilities.camelCase(c.getCode());
	      cc = makeConst(cc);
	      write("            case "+cc+": return \""+c.getSystem()+"\";\r\n");
			}
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

    write("        public String getDefinition() {\r\n");
    write("          switch (this) {\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
	      String cc = Utilities.camelCase(c.getCode());
	      cc = makeConst(cc);
	      write("            case "+cc+": return \""+Utilities.escapeJava(c.getDefinition())+"\";\r\n");
			}
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

    write("        public String getDisplay() {\r\n");
    write("          switch (this) {\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
	      String cc = Utilities.camelCase(c.getCode());
	      cc = makeConst(cc);
	      write("            case "+cc+": return \""+Utilities.escapeJava(Utilities.noString(c.getDisplay()) ? c.getCode() : c.getDisplay())+"\";\r\n");
			}
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

    if (adornments.containsKey(tns)) {
      write("// added from java-adornments.txt:\r\n");
      write(adornments.get(tns)+"\r\n");
      write("// end addition\r\n");
    }

		write("    }\r\n");
		write("\r\n");

		
		write("  public static class "+tns+"EnumFactory implements EnumFactory<"+tns+"> {\r\n");
		write("    public "+tns+" fromCode(String codeString) throws IllegalArgumentException {\r\n");
		
		write("      if (codeString == null || \"\".equals(codeString))\r\n");
    write("            if (codeString == null || \"\".equals(codeString))\r\n");
    write("                return null;\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
	      String cc = Utilities.camelCase(c.getCode());
	      cc = makeConst(cc);
	      write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
	      write("          return "+tns+"."+cc+";\r\n");
			}
    write("        throw new IllegalArgumentException(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
    write("        }\r\n"); 
    write("        public Enumeration<"+tns+"> fromType(Base code) throws FHIRException {\r\n");
    write("          if (code == null)\r\n");
    write("            return null;\r\n");
    write("          if (code.isEmpty())\r\n");
    write("            return new Enumeration<"+tns+">(this);\r\n");
    write("          String codeString = ((PrimitiveType) code).asStringValue();\r\n");
    write("          if (codeString == null || \"\".equals(codeString))\r\n");
    write("            return null;\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
      write("          return new Enumeration<"+tns+">(this, "+tns+"."+cc+");\r\n");
    }   
    write("        throw new FHIRException(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
    write("        }\r\n"); 
    write("    public String toCode("+tns+" code) {\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
	      String cc = Utilities.camelCase(c.getCode());
	      cc = makeConst(cc);
	      write("      if (code == "+tns+"."+cc+")\r\n        return \""+c.getCode()+"\";\r\n");
    }
    write("      return \"?\";\r\n"); 
    write("      }\r\n");
    
    write("    public String toSystem("+tns+" code) {\r\n");
    write("      return code.getSystem();\r\n");
    write("      }\r\n"); 

    write("    }\r\n"); 
    write("\r\n");
    enumInfo.put("org.hl7.fhir.r5.model.Enumerations."+tns, url+"|"+el.toString());
	}

}
