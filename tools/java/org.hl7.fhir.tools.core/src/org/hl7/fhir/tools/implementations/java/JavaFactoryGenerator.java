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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.definitions.Config;


public class JavaFactoryGenerator extends OutputStreamWriter {

  private Map<String, String> resources = new HashMap<String, String>(); 
  private Map<String, String> types = new HashMap<String, String>(); 
	
	public JavaFactoryGenerator(OutputStream out) throws UnsupportedEncodingException {
		super(out, "UTF-8");
	}

  public void registerReference(String name, String classname) {
    resources.put(name,  classname);
  }
  
  public void registerType(String name, String classname) {
    types.put(name,  classname);
  }
  
	public void generate(String version, Date genDate) throws Exception {
		write("package org.hl7.fhir.instance.model;\r\n");
    write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
    write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
		write("public class ResourceFactory extends Factory {\r\n");
		write("\r\n");
		write("    public static Resource createReference(String name) throws Exception {\r\n");
		for (String name : resources.keySet()) {
			write("        if (\""+name+"\".equals(name))\r\n");
			write("            return new "+javaClassName(resources.get(name))+"();\r\n");
		}
		
		write("        else\r\n");
		write("            throw new Exception(\"Unknown Resource Name '\"+name+\"'\");\r\n");
		write("    }\r\n");
		write("\r\n");
    write("    public static Element createType(String name) throws Exception {\r\n");
    for (String name : types.keySet()) {
      write("        if (\""+name+"\".equals(name))\r\n");
      String t = types.get(name);
      if (t.contains("<"))
        write("            return new "+t+"(\""+t.substring(t.indexOf('<')+1).replace(">", "")+"\");\r\n");
      else
        write("            return new "+t+"();\r\n");
    }    
    write("        else\r\n");
    write("            throw new Exception(\"Unknown Type Name '\"+name+\"'\");\r\n");
    write("    }\r\n");
    write("\r\n");
		write("}\r\n");
		write("\r\n");
		flush();
		close();
	}
	
	private String javaClassName(String name) {
    if (name.equals("List"))
      return "List_";
    else 
      return name;
  }

}
