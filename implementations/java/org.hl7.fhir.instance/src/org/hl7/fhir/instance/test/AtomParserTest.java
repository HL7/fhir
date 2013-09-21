package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hl7.fhir.instance.formats.ParserBase.ResourceOrFeed;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.MedicationPrescription;
import org.hl7.fhir.instance.model.Resource;
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
	private String itemPath = "C:/cnanjo/repository/fhir/trunk/build/publish/examples/diagnosticreport-examples-lab-text.xml";
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
	public void validateParserAgainstResourceSet() {
		File dir = new File("C:/cnanjo/repository/fhir/trunk/build/publish/examples");
		try {
			for(File file : dir.listFiles()) {
				parseFile(file);
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testItem() {
		ResourceOrFeed r = parseFile(itemPath);
		Resource p = r.getResource();
		//System.out.println("|"+p.getStatusSimple()+"|");
		//assertTrue(p.getStatusSimple().toString().equals("active"));
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
			return parseFile(file);
		} catch(Exception e) {
			e.printStackTrace();
			fail("Error Thrown");
		}
		return null;
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	private ResourceOrFeed parseFile(File file) throws FileNotFoundException, Exception {
		System.out.println(file.getName());
		FileInputStream fis = new FileInputStream(file);
		ResourceOrFeed resourceOrFeed = parser.parseGeneral(fis);
		assertTrue(true);
		return resourceOrFeed;
	}

}
