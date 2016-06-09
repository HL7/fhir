package org.hl7.fhir.dstu3.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.Manager;
import org.hl7.fhir.dstu3.elementmodel.TurtleParser;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Test;

import junit.framework.Assert;

public class ParserTests {

	private String root = "C:\\work\\org.hl7.fhir\\build\\publish";

//	@Test
	public void testBundleTurtle() throws Exception {
    if (TestingUtilities.context == null) {
      TestingUtilities.context = SimpleWorkerContext.fromPack(Utilities.path(root, "validation-min.xml.zip"));
      ((SimpleWorkerContext) TestingUtilities.context).connectToTSServer("http://local.healthintersections.com.au:960/open");
    }
    org.hl7.fhir.dstu3.elementmodel.XmlParser xp = new org.hl7.fhir.dstu3.elementmodel.XmlParser(TestingUtilities.context);
    Element e = xp.parse(new FileInputStream(Utilities.path(root, "parameters-example.xml")));
    TurtleParser tp = new TurtleParser(TestingUtilities.context);
    tp.compose(e, new FileOutputStream(Utilities.path(root, "parameters-example.ttl")), OutputStyle.PRETTY, "http://hl7.org/fhir");
    tp = new TurtleParser(TestingUtilities.context);
    e = tp.parse(new FileInputStream(Utilities.path(root, "parameters-example.ttl")));
    xp = new org.hl7.fhir.dstu3.elementmodel.XmlParser(TestingUtilities.context);
    xp.compose(e, new FileOutputStream(Utilities.path(root, "parameters-example-1.xml")), OutputStyle.PRETTY, null); //"http://hl7.org/fhir");
	}
//	@Test
//	public void testSpecific() throws Exception {
//		String examples = Utilities.path(root, "examples");
//		String fn = "organization-example-f002-burgers-card(f002).xml";
//		testRoundTrip(Utilities.path(examples, fn), fn);	  
//	}
//
//	@Test
//	public void testAll() throws Exception {
//		String examples = Utilities.path(root, "examples");
//		for (String fn : new File(examples).list()) {
//			if (fn.endsWith(".xml")) {
//				testRoundTrip(Utilities.path(examples, fn), fn);
//			}
//		}
//	}

	 @Test
	  public void testParameters() throws Exception {
	   testRoundTrip(Utilities.path(root, "gao", "example-gao-request-parameters-CT-head.xml"), "testcase");
	 }

	@SuppressWarnings("deprecation")
	private void testRoundTrip(String filename, String name) throws Exception {
		System.out.println(name);
		Resource r = new org.hl7.fhir.dstu3.formats.XmlParser().parse(new FileInputStream(filename));
		String fn = makeTempFilename();
		new org.hl7.fhir.dstu3.formats.XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(fn), r);
    String msg = TestingUtilities.checkXMLIsSame(filename, fn);
    Assert.assertTrue(name+": "+msg, msg == null);
    String j1 = makeTempFilename();
		new org.hl7.fhir.dstu3.formats.JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(j1), r);

		if (TestingUtilities.context == null) {
      TestingUtilities.context = SimpleWorkerContext.fromPack(Utilities.path(root, "validation-min.xml.zip"));
    }
		
		Element re = Manager.parse(TestingUtilities.context, new FileInputStream(filename), FhirFormat.XML);
    fn = makeTempFilename();
    Manager.compose(TestingUtilities.context, re, new FileOutputStream(fn), FhirFormat.XML, OutputStyle.PRETTY, null);
    msg = TestingUtilities.checkXMLIsSame(filename, fn);
    Assert.assertTrue(name+": "+msg, msg == null);
    String j2 = makeTempFilename();
    Manager.compose(TestingUtilities.context, re, new FileOutputStream(j2), FhirFormat.JSON, OutputStyle.PRETTY, null);

    msg = TestingUtilities.checkJsonIsSame(j1, j2);
    Assert.assertTrue(name+": "+msg, msg == null);

	  // ok, we've produced equivalent JSON by both methods.
	  // now, we're going to reverse the process
		r = new org.hl7.fhir.dstu3.formats.JsonParser().parse(new FileInputStream(j2)); // crossover too
    fn = makeTempFilename();
		new org.hl7.fhir.dstu3.formats.JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(fn), r);
    msg = TestingUtilities.checkJsonIsSame(j2, fn);
    Assert.assertTrue(name+": "+msg, msg == null);
    String x1 = makeTempFilename();
		new org.hl7.fhir.dstu3.formats.XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(x1), r);

		re = Manager.parse(TestingUtilities.context, new FileInputStream(j1), FhirFormat.JSON);
    fn = makeTempFilename();
    Manager.compose(TestingUtilities.context, re, new FileOutputStream(fn), FhirFormat.JSON, OutputStyle.PRETTY, null);
    msg = TestingUtilities.checkJsonIsSame(j1, fn);
    Assert.assertTrue(name+": "+msg, msg == null);
    String x2 = makeTempFilename();
    Manager.compose(TestingUtilities.context, re, new FileOutputStream(x2), FhirFormat.XML, OutputStyle.PRETTY, null);

    msg = TestingUtilities.checkXMLIsSame(x1, x2);
    Assert.assertTrue(name+": "+msg, msg == null);
    msg = TestingUtilities.checkXMLIsSame(filename, x1);
    Assert.assertTrue(name+": "+msg, msg == null);

	}

	int i = 0;
	private String makeTempFilename() {
		i++;
  	return "c:\\temp\\fhirtests\\"+Integer.toString(i)+".tmp";
	}

}
