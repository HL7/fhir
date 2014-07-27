package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NarrativeGeneratorTests {

	private NarrativeGenerator gen;
	
	@Before
	public void setUp() throws Exception {
		if (gen == null)
  		gen = new NarrativeGenerator("", WorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation.zip"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		process("C:\\work\\org.hl7.fhir\\build\\source\\questionnaireanswers\\questionnaireanswers-example-f201-lifelines.xml");
	}

	private void process(String path) throws Exception {
	  XmlParser p = new XmlParser();
	  Resource r = p.parse(new FileInputStream(path));
	  gen.generate(r);
	  new XmlComposer().compose(new FileOutputStream("c:\\temp\\gen.xml"), r, true);
	  
  }

}
