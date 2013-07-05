package org.hl7.fhir.definitions.parsers;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingExtensibility;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.utilities.XLSXmlParser;
import org.hl7.fhir.utilities.XLSXmlParser.Sheet;

public class BindingsParser {

  private InputStream file;
  private String filename;
  private String root;
  private XLSXmlParser xls;
  private BindingNameRegistry registry;

  public BindingsParser(InputStream file, String filename, String root, BindingNameRegistry registry) {
    this.file = file;
		this.filename = filename;
		this.root = root;
		this.registry = registry;
	}

	public List<BindingSpecification> parse() throws Exception {
		List<BindingSpecification> results = new ArrayList<BindingSpecification>();
		BindingSpecification n = new BindingSpecification();
		n.setName("*unbound*");
		n.setBinding(BindingSpecification.Binding.Unbound);
		results.add(n);
		
		xls = new XLSXmlParser(file, filename);
		Sheet sheet = xls.getSheets().get("Bindings");

    for (int row = 0; row < sheet.rows.size(); row++) {
		  processLine(results, sheet, row);
		}		
		return results;
	}
	
	private void processLine(List<BindingSpecification> results, Sheet sheet, int row) throws Exception {
		BindingSpecification cd = new BindingSpecification();
    cd.setName(sheet.getColumn(row, "Binding Name"));
		cd.setDefinition(sheet.getColumn(row, "Definition"));
    cd.setBinding(readBinding(sheet.getColumn(row, "Binding")));
    cd.setReference(sheet.getColumn(row, "Reference"));
    cd.setDescription(sheet.getColumn(row, "Description"));
    cd.setId(registry.idForName(cd.getName()));
    cd.setSource(filename);
    cd.setUri(sheet.getColumn(row, "Uri"));
    cd.setOid(sheet.getColumn(row, "Oid"));
    cd.setWebSite(sheet.getColumn(row, "Website"));
    cd.setEmail(sheet.getColumn(row, "Email"));

    results.add(cd);
	}

	public static BindingExtensibility readExtensibility(String s) throws Exception {
    s = s.toLowerCase();
    if (s == null || "".equals(s) || "complete".equals(s))
      return BindingSpecification.BindingExtensibility.Complete;
    if (s.equals("extensible"))
      return BindingSpecification.BindingExtensibility.Extensible;
    throw new Exception("Unknown Binding Extensibility: "+s);
  }

  public static BindingSpecification.Binding readBinding(String s) throws Exception {
		s = s.toLowerCase();
		if (s == null || "".equals(s) || "unbound".equals(s))
			return BindingSpecification.Binding.Unbound;
		if (s.equals("code list"))
			return BindingSpecification.Binding.CodeList;
		if (s.equals("special"))
			return BindingSpecification.Binding.Special;
		if (s.equals("reference"))
			return BindingSpecification.Binding.Reference;
		if (s.equals("value set"))
			return BindingSpecification.Binding.ValueSet;
		throw new Exception("Unknown Binding: "+s);
	}
		
	public static BindingSpecification.BindingStrength readBindingStrength(String s) throws Exception {
    s = s.toLowerCase();
    if (s == null || "".equals(s))
      return BindingSpecification.BindingStrength.Unstated;
    if (s.equals("required"))
      return BindingSpecification.BindingStrength.Required;
    if (s.equals("preferred"))
      return BindingSpecification.BindingStrength.Preferred;
    if (s.equals("example"))
      return BindingSpecification.BindingStrength.Example;
    throw new Exception("Unknown Binding Strength: "+s);
  }

  public boolean loadCodes(BindingSpecification cd) throws Exception {
    // TODO Auto-generated method stub
    Sheet sheet = xls.getSheets().get(cd.getReference().substring(1));
    if (sheet == null)
      return false;
    
    for (int row = 0; row < sheet.rows.size(); row++) {
      DefinedCode c = new DefinedCode();
      c.setId(sheet.getColumn(row, "Id"));
      c.setCode(sheet.getColumn(row, "Code"));
      c.setDisplay(sheet.getColumn(row, "Display"));
      c.setSystem(sheet.getColumn(row, "System"));
      c.setDefinition(sheet.getColumn(row, "Definition"));
      c.setComment(sheet.getColumn(row, "Comment"));
      cd.getCodes().add(c);
    }
    return true;
    
  }
}
