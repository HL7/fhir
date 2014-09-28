package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.client.ResourceAddress;
import org.hl7.fhir.instance.model.Patient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import static org.junit.Assert.fail;

public class ResourceAddressTest {
	
	private String basePath = "http://fhir.healthintersections.com.au/open";
	private String fullNonVersionedPath = "http://fhir.healthintersections.com.au/open/Patient/318";
	private String fullVersionedPath = "http://fhir.healthintersections.com.au/open/Patient/318/_history/1";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseCreateLocation() {
		ResourceAddress.ResourceVersionedIdentifier versionedIdentifier = ResourceAddress.parseCreateLocation(fullVersionedPath);
		assertEquals(basePath, versionedIdentifier.getServiceRoot());
		assertEquals("Patient", versionedIdentifier.getResourceType());
		assertEquals("318",versionedIdentifier.getId());
		assertEquals("1", versionedIdentifier.getVersionId());
		assertEquals(fullNonVersionedPath, versionedIdentifier.getResourcePath());
	}
	
	@Test
	public void testBuildURIValidURLNoEndSlash() {
		try {
			assertEquals(new URI(basePath + "/"), ResourceAddress.buildAbsoluteURI(basePath));
		} catch(Exception e) {
			fail("Error validating URI");
		}
	}
	
	@Test
	public void testBuildURIInvalidScheme() {
		try {
			ResourceAddress.buildAbsoluteURI("urn://hl7connect.healthintersections.com.au/svc/fhir");
			fail("Should not be here as scheme is invalid");
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuildURIMissingHost() {
		try {
			ResourceAddress.buildAbsoluteURI("http:///svc/fhir");
			fail("Should not be here as host is missing");
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuildURIValidURLEndSlash() {
		try {
			assertEquals(new URI("http://hl7connect.healthintersections.com.au/svc/fhir/"), ResourceAddress.buildAbsoluteURI("http://hl7connect.healthintersections.com.au/svc/fhir/"));
		} catch(Exception e) {
			fail("Error validating URI");
		}
	}
	
	@Test
	public void testBuildURIInvalueURL() {
		try {
			ResourceAddress.buildAbsoluteURI("I am an invalid URI");
			fail("Assertion: Should not get to this point");
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void buildRelativePathFromReference() {
		Patient patient = new Patient();
		String path = ResourceAddress.buildRelativePathFromReference(patient);
		assertEquals("patient/",path);
	}
	
	@Test
	public void buildRelativePathFromResourceWithId() {
		Patient patient = new Patient();
		String path = ResourceAddress.buildRelativePathFromReference(patient,"23");
		assertEquals("patient/@23",path);
	}
	
	@Test
	public void buildRelativePathFromResourceType() {
		Patient patient = new Patient();
		String path = ResourceAddress.buildRelativePathFromResourceType(patient.getResourceType());
		assertEquals("patient/",path);
	}
	
	@Test
	public void buildRelativePathFromResourceTypeWithId() {
		Patient patient = new Patient();
		String path = ResourceAddress.buildRelativePathFromResourceType(patient.getResourceType(), "23");
		assertEquals("patient/@23",path);
	}

	@Test
	public void testAppendHttpParameter() {
		try {
			URI basePath = new URI("http://some.server.com:9090/path/to/file?param1=1&param2=2");
			URI updatedUri = ResourceAddress.appendHttpParameter(basePath, "param3", "3");
			System.out.println(updatedUri);
			assertTrue(updatedUri.toString().equals("http://some.server.com:9090/path/to/file?param1=1&param2=2&param3=3"));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAppendHttpParameters() {
		try {
			URI basePath = new URI("http://some.server.com:9090/path/to/file");
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("param1", "1");
			parameters.put("param2", "2");
			URI updatedUri = ResourceAddress.appendHttpParameters(basePath, parameters);
			System.out.println(updatedUri);
			assertTrue(updatedUri.toString().equals("http://some.server.com:9090/path/to/file?param1=1&param2=2"));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testGetCalendarDateInIsoFormat() {
		String isoDate = ResourceAddress.getCalendarDateInIsoTimeFormat(Calendar.getInstance());
		System.out.println(isoDate);
	}
}