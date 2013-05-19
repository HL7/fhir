package org.hl7.fhir.tools.implementations.java;
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GeneratorUtils;

public class JavaBaseGenerator extends OutputStreamWriter {

	public JavaBaseGenerator(OutputStream out) throws UnsupportedEncodingException {
		super(out, "UTF-8");
	}

	
	protected String getElementName(String name, boolean alone) {
	  if (name.equals("[type]"))
	    return "value";
	  else if ((alone && GeneratorUtils.isJavaReservedWord(name)) || (!alone && name.equals("class")))
	    return name+"_";
	  else
	    return name.replace("[x]", "");
	}

	protected String getTypeName(ElementDefn e) throws Exception {
		if (e.getTypes().size() > 1) {
			return "Type";
		} else if (e.getTypes().size() == 0) {
			throw new Exception("not supported");
		} else {
			return getTypename(e.getTypes().get(0));
		}
	}

	protected String getTypename(TypeRef type) throws Exception {
		if (type.getParams().size() == 1) {			
			if (type.isResourceReference())
				return "ResourceReference";
			else if (type.getName().equals("Interval"))
				return "Interval<"+getTypeName(type.getParams().get(0))+">";
			else
				throw new Exception("not supported");
		} else if (type.getParams().size() > 1) {
			if (type.isResourceReference())
				return "ResourceReference";
			else
				throw new Exception("not supported");
		} else {
			return getTypeName(type.getName());
		}
	}

	protected String getTypeName(String tn) {
		if (tn.equals("string")) {
			return "String_";
		} else if (tn.equals("Any")) {
			return "Resource";
		} else {
			return getTitle(tn);
		}
	}

	protected String getTitle(String name) {
		return name.substring(0, 1).toUpperCase()+ name.substring(1);
	}
}
