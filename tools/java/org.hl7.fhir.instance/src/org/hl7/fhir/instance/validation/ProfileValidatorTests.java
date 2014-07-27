package org.hl7.fhir.instance.validation;

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
	  Profile p = (Profile) parseResource(dir+XMLUtil.getNamedChildValue(test, "profile")+".xml");
	  List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
	  v.validateInstanceByProfile(errors , r, p);
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

	private Resource parseResource(String filename) throws Exception {
		XmlParser xml = new XmlParser();
		return xml.parse(new FileInputStream(filename));
  }

	

}
