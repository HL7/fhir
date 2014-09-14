package org.hl7.fhir.tools.implementations.delphi;
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
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.Config;

public class DelphiCodeGenerator extends OutputStreamWriter {

	// fragments
  public String name;
  public List<String> uses = new ArrayList<String>();
  public List<String> comments = new ArrayList<String>();
  public List<String> precomments = new ArrayList<String>();
  public List<String> enumDefs = new ArrayList<String>();
	public List<String> enumConsts = new ArrayList<String>();
	public List<String> enumProcs = new ArrayList<String>();
  public List<String> classFwds = new ArrayList<String>();
  public List<String> classDefs = new ArrayList<String>();
	public List<String> classImpls = new ArrayList<String>();
  public List<String> inits = new ArrayList<String>();
  public List<String> procs = new ArrayList<String>();
  public List<String> procsPub = new ArrayList<String>();
	
	public DelphiCodeGenerator(OutputStream out) throws UnsupportedEncodingException {
		super(out, "ASCII");
	}

	public void start() throws Exception {
	}

	public String escape(String v) {
	  StringBuilder s = new StringBuilder();
	  for (char c : v.toCharArray())
	    if (c == '\'')
	      s.append("''");
	    else
	      s.append(c);
	  return s.toString();
	}
	
	public void finish() throws Exception {
	  if (precomments.size() > 0) {
	    for (int i = 0; i < precomments.size(); i++) {
	      if (precomments.get(i).charAt(0) == '!')
	        write("{"+precomments.get(i)+"}\r\n");
	      else
	        write("// "+precomments.get(i)+"\r\n");
	    }
	    write("\r\n");
	  }
    write("unit "+name+";\r\n");
    write("\r\n");
    write("{\r\n"+Config.FULL_LICENSE_CODE+"}\r\n\r\n");
    write("\r\n");
    write("{$IFDEF FHIR-DSTU}\r\n");
    write("This is the dev branch of the FHIR code\r\n");
    write("{$ENDIF}\r\n");
    write("\r\n");
    write("interface\r\n");
    write("\r\n");

    for (int i = 0; i < comments.size(); i++) {
      if (comments.get(i).charAt(0) == '!')
        write("{"+comments.get(i)+"}\r\n");
      else
        write("// "+comments.get(i)+"\r\n");
    }
    write("\r\n");
    
    write("uses\r\n");
    write("  ");
    for (int i = 0; i < uses.size(); i++) {
      if (i > 0)
        write(", ");
      write(uses.get(i));
    }
    write(";\r\n");
    write("\r\n");
    
    if (enumDefs.size() > 0) {
      write("Type\r\n");

      for (String s : enumDefs) {
        write(s+"\r\n");
      }
    }

    if (classDefs.size() > 0) {
      write("Type\r\n");
      for (String s : classFwds) {
        write(s);
      }
      write("\r\n");
      for (String s : classDefs) {
        write(s+"\r\n");
      }
    }
    if (enumConsts.size() > 0 || enumProcs.size() > 0) {
      
      write("Const\r\n");
      for (String s : enumConsts) {
        write(s+"\r\n");
      }
      write("\r\n");

        for (String s : enumProcs) {
          write(s+"\r\n");
        }
        write("\r\n");
      }
    if (procsPub.size() > 0) {
      for (String s : procsPub) {
        write(s);
      }
      write("\r\n");
    }
    write("implementation\r\n");
    write("\r\n");
    for (String s : classImpls) {
      write(s+"\r\n");
    }

    for (String s : procs) {
      write(s+"\r\n");
    }

    if (inits.size() > 0) {
      write("initialization;\r\n");
      for (String s : inits) {
        write("  "+s+"\r\n");
      }
    }
    write("end.\r\n");
    write("\r\n");
		flush();
		close();
	}
	


}
