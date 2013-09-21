package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;

import org.hl7.fhir.instance.formats.ParserBase.ResourceOrFeed;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.MedicationPrescription;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.hl7.fhir.instance.formats.AtomParser;

public class AtomParserTest {
	
	private File file = null;
	private String filepathResourceNotPretty = "C:\\cnanjo\\repository\\fhirTestInstances\\containedResource_notpretty.xml";
	private String filepathResourcePretty = "C:\\cnanjo\\repository\\fhirTestInstances\\containedResource_pretty.xml";
	private String filepathFeedNotPretty = "C:\\cnanjo\\repository\\fhirTestInstances\\containedFeed_notpretty.xml";
	private String filepathFeedPretty = "C:\\cnanjo\\repository\\fhirTestInstances\\containedFeed_pretty.xml";
	private XmlParser parser = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		parser = new XmlParser();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testResourceNotPretty() {
		ResourceOrFeed r = parseFile(filepathResourceNotPretty);
		MedicationPrescription p = (MedicationPrescription) r.getResource();
		System.out.println("|"+p.getStatusSimple()+"|");
		assertTrue(p.getStatusSimple().toString().equals("active"));
	}
	
	@Test
	public void testResourcePretty() {
		ResourceOrFeed r = parseFile(filepathResourcePretty);
		MedicationPrescription p = (MedicationPrescription) r.getResource();
		System.out.println("|"+p.getStatusSimple()+"|");
		assertTrue(p.getStatusSimple().toString().equals("active"));
		//|active|
	}
	
	@Test
	public void testFeedNotPretty() {
		ResourceOrFeed r = parseFile(filepathFeedNotPretty);
		MedicationPrescription p = (MedicationPrescription) r.getFeed().getEntryList().get(0).getResource();
		System.out.println("|"+p.getStatusSimple()+"|");
		assertTrue(p.getStatusSimple().toString().equals("active"));

	}
	
	@Test
	public void testFeedPretty() {
		ResourceOrFeed r = parseFile(filepathFeedPretty);
		MedicationPrescription p = (MedicationPrescription) r.getFeed().getEntryList().get(0).getResource();
		System.out.println("|"+p.getStatusSimple()+"|");
		assertTrue(p.getStatusSimple().toString().equals("active"));

	}

	/**
	 * 
	 */
	private ResourceOrFeed parseFile(String fileName) {
		try {
			file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			ResourceOrFeed resourceOrFeed = parser.parseGeneral(fis);
			assertTrue(true);
			return resourceOrFeed;
		} catch(Exception e) {
			e.printStackTrace();
			fail("Error Thrown");
		}
		return null;
	}

}
