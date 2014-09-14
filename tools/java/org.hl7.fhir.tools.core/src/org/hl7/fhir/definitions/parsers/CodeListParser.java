package org.hl7.fhir.definitions.parsers;
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
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.utilities.CSVReader;

public class CodeListParser  extends CSVReader {

	public CodeListParser(InputStream in) throws UnsupportedEncodingException {
		super(in);
	}

	public void parse(List<DefinedCode> codes) throws Exception {
		String[] titles = parseLine();
		while (ready())
		{
		  String[] values = parseLine();
		  processLine(codes, titles, values);
		}				
	}

	private void processLine(List<DefinedCode> codes, String[] titles, String[] values) throws Exception {
		DefinedCode c = new DefinedCode();
    c.setId(getColumn(titles, values, "Id"));
    c.setCode(getColumn(titles, values, "Code"));
		c.setDefinition(getColumn(titles, values, "Definition"));
    c.setComment(getColumn(titles, values, "Comment"));
    c.setParent(getColumn(titles, values, "Parent"));
		codes.add(c);
	}
	
}
