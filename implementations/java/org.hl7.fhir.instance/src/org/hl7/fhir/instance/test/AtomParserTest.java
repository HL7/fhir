package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hl7.fhir.instance.formats.ResourceOrFeed;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.MedicationPrescription;
import org.hl7.fhir.instance.model.Patient;
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
	private String filepathFurore1NotPretty;
	private String filepathFurore1Pretty;
	
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
	filepathFurore1NotPretty = basePath  + "FuroreHistory15_noformat.xml";
	filepathFurore1Pretty = basePath + "FuroreHistory15_pretty.xml";
	parser = new XmlParser();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testItem() {
		ResourceOrFeed r = parseFile(itemPath);
		Resource p = r.getResource();
		assertTrue(p != null);
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
	
	@Test
	public void testFurorePretty() {
		try {
			ResourceOrFeed r = parseFile(filepathFurore1Pretty);
			AtomFeed feed = r.getFeed();
			assertTrue(feed.getEntryList().size() == 3);
			for(AtomEntry<? extends Resource> entry : feed.getEntryList()) {
				assertTrue(entry.getResource() instanceof Patient);
			}
			Patient patient1 = (Patient)feed.getEntryList().get(0).getResource();
			assertTrue(patient1.getName().get(0).getFamily().get(0).getValue().equalsIgnoreCase("Fox"));
			Patient patient2 = (Patient)feed.getEntryList().get(1).getResource();
			assertTrue(patient2.getName().get(0).getFamily().get(0).getValue().equalsIgnoreCase("Fox"));
			Patient patient3 = (Patient)feed.getEntryList().get(2).getResource();
			assertTrue(patient3.getName().get(0).getFamily().get(0).getValue().equalsIgnoreCase("Garrett"));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void testFuroreNotPretty() {
		try {
			ResourceOrFeed r = parseFile(filepathFurore1NotPretty);
			assertTrue(r.getFeed() != null || r.getResource() != null);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}

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
