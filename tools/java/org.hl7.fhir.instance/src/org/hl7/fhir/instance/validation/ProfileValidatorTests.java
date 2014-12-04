package org.hl7.fhir.instance.validation;

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
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ProfileValidatorTests {

	private File registry;
	private File pack;

	public ProfileValidatorTests(File pack, File registry) {
	  this.pack = pack;
	  this.registry = registry;
  }

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		File pack = new File(args[0]);
		File registry = new File(args[1]);
		if (!pack.exists())
		  throw new Exception("unable to find validation pack (1st argument)");
		if (!registry.exists())
		  throw new Exception("unable to find test case registry (2nd argument)");
		ProfileValidatorTests self = new ProfileValidatorTests(pack, registry);
		self.execute();
	}

	public void execute() throws Exception {
	  InstanceValidator v = new InstanceValidator(WorkerContext.fromPack(pack.getAbsolutePath()));
	  
    Document reg = parseXml(registry.getAbsolutePath());
    List<Element> tests = new ArrayList<Element>();
    XMLUtil.getNamedChildren(reg.getDocumentElement(), "test", tests);
    String dir = Utilities.getDirectoryForFile(registry.getAbsolutePath()) + File.separator;
    for (Element test : tests) {
    	executeCase(v, dir, test);
    }
  }

	private Document parseXml(String filename) throws Exception  {
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document reg = builder.parse(new CSFileInputStream(filename));
	  return reg;
  }

	private void executeCase(InstanceValidator v, String dir, Element test) throws Exception {
	  Element r = parseXml(dir+XMLUtil.getNamedChildValue(test, "instance")+".xml").getDocumentElement();
	  Profile p = (Profile) parseReference(dir+XMLUtil.getNamedChildValue(test, "profile")+".xml");
	  List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
	  v.validate(errors, r, p);
	  String outcome = XMLUtil.getNamedChildValue(test, "outcome");
	  boolean ok;
	  if ("ok".equals(outcome)) {
	  	ok = (errors.size() == 0); 
	  } else {
	  	ok = (errors.size() == Integer.parseInt(outcome));
	  }
	  System.out.println(test.getAttribute("name") + " : "+(ok ? " ok " : "FAIL"));
	  if (!ok)
      for (ValidationMessage vm : errors) 
	  	  System.out.println("  ..: "+vm.summary());
	}

	private Resource parseReference(String filename) throws Exception {
		XmlParser xml = new XmlParser();
		return xml.parse(new FileInputStream(filename));
  }

	

}
