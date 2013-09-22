package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private XmlParser parser = null;
	private String basePath = null;

	private String filepathResourceNotPretty;
	private String filepathResourcePretty;
	private String filepathFeedNotPretty;
	private String filepathFeedPretty;
	private String itemPath;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println(new java.io.File( "." ).getCanonicalPath());
	basePath = new java.io.File( "." ).getCanonicalPath().replaceAll(Pattern.quote("build"+File.separator)+".*",Matcher.quoteReplacement("build"+File.separator+"tests"+File.separator+"fixtures"+File.separator));

	filepathResourceNotPretty = basePath + "containedResource_notpretty.xml";
	filepathResourcePretty =  basePath + "containedResource_pretty.xml";
	filepathFeedNotPretty = basePath + "containedFeed_notpretty.xml";
	filepathFeedPretty = basePath  + "containedFeed_pretty.xml";
	itemPath = basePath + "diagnosticreport-feed.xml";
	parser = new XmlParser();
	}

	@After
	public void tearDown() throws Exception {
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
