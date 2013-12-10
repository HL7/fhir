package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.client.ClientUtils;
import org.hl7.fhir.instance.client.EFhirClientException;
import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.client.ResourceAddress;
import org.hl7.fhir.instance.client.ResourceFormat;
import org.hl7.fhir.instance.model.AdverseReaction;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.DateTime;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceReference;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FHIRSimpleClientTest {
	
	private static String connectUrl1 = "http://hl7connect.healthintersections.com.au/svc/fhir/";
	private static String connectUrl2 = "http://fhir.furore.com/fhir";
	private static String connectUrl = connectUrl1;
	
	private FHIRClient testClient;
	private String testPatientId;
	private boolean logResource = true;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testClient = new FHIRSimpleClient();
		testClient.initialize(connectUrl);
	}

	@After
	public void tearDown() throws Exception {	
	}
	
	public void loadPatientResource() throws ParseException {
		Patient testPatient = buildPatient();
		AtomEntry<Patient> result = testClient.create(Patient.class, testPatient);
		testPatientId = getPatientId(result);
	}
	
	public void unloadPatientResource() {
		testClient.delete(Patient.class, testPatientId);
	}

	@Test
	@SuppressWarnings(value = "unused")
	public void testFHIRSimpleClient() {
		try {
			FHIRClient client = new FHIRSimpleClient();
			client.initialize(connectUrl);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetConformanceStatement() {
		try {
			testClient.setPreferredResourceFormat(ResourceFormat.RESOURCE_XML);
			Conformance stmt = testClient.getConformanceStatement();
			assertEquals("HL7Connect", stmt.getSoftware().getName().getValue());
			printResourceToSystemOut(stmt, false);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetConformanceStatementWithOptionsJson() {
		try {
			testClient.setPreferredResourceFormat(ResourceFormat.RESOURCE_JSON);
			Conformance stmt = testClient.getConformanceStatement(true);
			assertEquals("HL7Connect", stmt.getSoftware().getName().getValue());
			printResourceToSystemOut(stmt, true);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetConformanceStatementWithOptionsXml() {
		try {
			testClient.setPreferredResourceFormat(ResourceFormat.RESOURCE_XML);
			Conformance stmt = testClient.getConformanceStatement(true);
			assertEquals("HL7Connect", stmt.getSoftware().getName().getValue());
			printResourceToSystemOut(stmt, false);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetConformanceStatementWithGetXml() {
		try {
			testClient.setPreferredResourceFormat(ResourceFormat.RESOURCE_XML);
			Conformance stmt = testClient.getConformanceStatement(false);
			assertEquals("HL7Connect", stmt.getSoftware().getName().getValue());
			printResourceToSystemOut(stmt, false);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetConformanceStatementWithGetJson() {
		try {
			testClient.setPreferredResourceFormat(ResourceFormat.RESOURCE_JSON);
			Conformance stmt = testClient.getConformanceStatement(false);
			assertEquals("HL7Connect", stmt.getSoftware().getName().getValue());
			printResourceToSystemOut(stmt, false);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRead() throws ParseException {
		loadPatientResource();
		AtomEntry<Patient> fetchedPatient = testClient.read(Patient.class, testPatientId);
		assertEquals("2008-08-08", fetchedPatient.getResource().getBirthDate().getValue());
		unloadPatientResource();
	}

	@Test
	public void testVread() throws ParseException {
		loadPatientResource();
		AtomEntry<Patient> fetchedPatient = testClient.vread(Patient.class, testPatientId, "1");
		assertEquals("2008-08-08", fetchedPatient.getResource().getBirthDate().getValue());
		unloadPatientResource();
	}

	@Test
	public void testUpdate() throws ParseException {
		loadPatientResource();
		Patient modifiedPatient = testClient.read(Patient.class, testPatientId).getResource();
		DateTime modifiedBirthday = new DateTime();
		modifiedBirthday.setValue(new DateAndTime("2002-09-09"));
		modifiedPatient.setBirthDate(modifiedBirthday);
		AtomEntry<Patient> result = testClient.update(Patient.class, modifiedPatient, testPatientId);
		if(((Resource)result.getResource()) instanceof OperationOutcome) {
			fail();
		} else {
			Patient updatedResource = (Patient)result.getResource();
			assertTrue(modifiedBirthday.getValue().equals(updatedResource.getBirthDate().getValue()));
		}
		modifiedBirthday = new DateTime();
		modifiedBirthday.setValue(new DateAndTime("2008-08-08"));
		modifiedPatient.setBirthDate(modifiedBirthday);
		result = testClient.update(Patient.class, modifiedPatient, testPatientId);
		if(((Resource)result.getResource()) instanceof OperationOutcome) {
			fail();
		} else {
			Patient updatedResource = (Patient)result.getResource();
			assertTrue(modifiedBirthday.getValue().equals(updatedResource.getBirthDate().getValue()));
		}
		unloadPatientResource();
	}
	
	@Test
	public void testValidate() throws ParseException {
		loadPatientResource();
		Patient patient = testClient.read(Patient.class, testPatientId).getResource();
		DateTime modifiedBirthday = new DateTime();
		modifiedBirthday.setValue(new DateAndTime("2009-08-08"));
		patient.setBirthDate(modifiedBirthday);
		AtomEntry<OperationOutcome> validate = testClient.validate(Patient.class, patient, testPatientId);
		String issue = validate.getResource().getIssue().get(0).getDetailsSimple();
		assertTrue(issue.equals("Bad Syntax in /fhir/patient/validate/@" + testPatientId));//TODO not sure why bad syntax
		unloadPatientResource();
	}
	
	@Test
	public void testCreate() throws ParseException {
		Patient patientRequest = buildPatient();
		AtomEntry<Patient> result = testClient.create(Patient.class, patientRequest);
		Patient patient = (Patient)result.getResource();
		assertEquals(patientRequest.getGender().getCoding().get(0).getCodeSimple(), patient.getGender().getCoding().get(0).getCodeSimple());
		assertEquals(patientRequest.getBirthDate().getValue(), patient.getBirthDate().getValue());
		ResourceAddress.ResourceVersionedIdentifier identifier = ResourceAddress.parseCreateLocation(result.getLinks().get("self"));
		boolean success = testClient.delete(Patient.class, identifier.getId());
		assertTrue(success);
	}
	
	@Test
	public void testCreateWithErrors() throws ParseException {
		AdverseReaction adverseReaction = new AdverseReaction();
		adverseReaction.setReactionDateSimple(new DateAndTime("2013-01-10"));
		//adverseReaction.setDidNotOccurFlagSimple(false);
		AtomEntry<AdverseReaction> result = null;
		try {
			result = testClient.create(AdverseReaction.class, adverseReaction);
		} catch (EFhirClientException e) {
			assertEquals(1, e.getServerErrors().size());
		}
		if(result != null) {
			fail();
		}
	}
	
	@Test
	public void testDelete() throws ParseException {
		Patient patientRequest = buildPatient();
		AtomEntry<Patient> result = testClient.create(Patient.class, patientRequest);
		boolean success = testClient.delete(Patient.class, getPatientId(result));
		assertTrue(success);
	}
	

	@Test
	public void testGetHistoryForResourceWithId() throws ParseException {
		loadPatientResource();
		Patient patient = testClient.read(Patient.class, testPatientId).getResource();
		testClient.update(Patient.class, patient, testPatientId);
		testClient.update(Patient.class, patient, testPatientId);
		AtomFeed feed = testClient.history(Patient.class, testPatientId);
		assertNotNull(feed);
		assertEquals(3, feed.getEntryList().size());
	}
	
	
	@Test
	public void testGetHistoryForResourcesOfTypeSinceCalendarDate() throws ParseException {
		Calendar testDate = GregorianCalendar.getInstance();
		testDate.add(Calendar.HOUR_OF_DAY, -1);
		AtomEntry<Patient> createdEntry = testClient.create(Patient.class, buildPatient());
		testClient.update(Patient.class, (Patient)createdEntry.getResource(), getPatientId(createdEntry));
		AtomFeed feed = testClient.history(testDate, Patient.class);
		assertNotNull(feed);
		assertTrue(feed.getEntryList().size() >= 1);
		testClient.delete(Patient.class, getPatientId(createdEntry));
	}

	@Test
	public void testHistoryForAllResourceTypes() {
		Calendar testDate = GregorianCalendar.getInstance();
		testDate.add(Calendar.HOUR_OF_DAY, -1);
		AtomFeed feed = testClient.history(testDate);
		assertNotNull(feed);
		assertTrue(feed.getEntryList().size() > 1);
	}

	@Test
	public void testGetHistoryForResourceWithIdSinceCalendarDate() throws ParseException {
		Calendar testDate = GregorianCalendar.getInstance();
		testDate.add(Calendar.HOUR_OF_DAY, -1);
		AtomEntry<Patient> entry = testClient.create(Patient.class, buildPatient());
		testClient.update(Patient.class, (Patient)entry.getResource(), getPatientId(entry));
		testClient.update(Patient.class, (Patient)entry.getResource(), getPatientId(entry));
		AtomFeed feed = testClient.history(testDate, Patient.class, getPatientId(entry));
		assertNotNull(feed);
		assertEquals(3, feed.getEntryList().size());
	}

	@Test
	public void testSearch() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("_count", "50");
		parameters.put("gender", "F");
		parameters.put("birthdate", "2008-08-08");
		AtomFeed feed = testClient.search(Patient.class, parameters);
		assertTrue(feed != null);
		System.out.println(feed.getEntryList().size());
		assertTrue(feed.getEntryList().size() > 0);
	}

	@Test
	public void testTransactionSuccess() throws ParseException {
		Patient patient = buildPatient();
		AtomEntry<Patient> createdPatientEntry = testClient.create(Patient.class, patient);
		createdPatientEntry.getResource().setBirthDateSimple(new DateAndTime("1966-01-10"));
		ResourceReference patientReference = new ResourceReference();
		patientReference.setReferenceSimple(createdPatientEntry.getLinks().get("self"));
		AdverseReaction adverseReaction = new AdverseReaction();
		adverseReaction.setSubject(patientReference);
		adverseReaction.setReactionDateSimple(new DateAndTime("2013-01-10"));
		adverseReaction.setDidNotOccurFlagSimple(false);
		AtomEntry<AdverseReaction> adverseReactionEntry = testClient.create(AdverseReaction.class, adverseReaction);
		AtomFeed batchFeed = new AtomFeed();
		batchFeed.getEntryList().add(createdPatientEntry);
		//batchFeed.getEntryList().add(adverseReactionEntry);
		AtomFeed responseFeed = testClient.transaction(batchFeed);
		assertNotNull(responseFeed);
		assert(responseFeed.getEntryList().get(0).getResource() instanceof Patient);
	}
	
	@Test
	public void testTransactionError() throws ParseException {
		Patient patient = buildPatient();
		AtomEntry<Patient> createdPatientEntry = testClient.create(Patient.class, patient);
		createdPatientEntry.getResource().setBirthDateSimple(new DateAndTime("1966-01-10"));
		AtomFeed batchFeed = new AtomFeed();
		batchFeed.getEntryList().add(createdPatientEntry);
		batchFeed.getEntryList().add(createdPatientEntry);
		AtomFeed responseFeed = null;
		try {
			responseFeed = testClient.transaction(batchFeed);
		} catch(EFhirClientException e) {
			assertEquals(1, e.getServerErrors().size());
		}
		if(responseFeed != null) {
			fail();
		}
	}

	private Patient buildPatient() throws ParseException {
		Patient patient = new Patient();
		DateTime birthday = new DateTime();
		birthday.setValue(new DateAndTime("2008-08-08"));
		patient.setBirthDate(birthday);
		Code genderCode = new Code();
		genderCode.setValue("F");
		Coding genderCoding = new Coding();
		genderCoding.setCode(genderCode);
		genderCoding.setSystemSimple("http://hl7.org/fhir/v3/AdministrativeGender");
		CodeableConcept female = new CodeableConcept();
		female.getCoding().add(genderCoding);
		patient.setGender(female);
		return patient;
	}
	
	private String getPatientId(AtomEntry<Patient> patient) {
		ResourceAddress.ResourceVersionedIdentifier identifier = ResourceAddress.parseCreateLocation(patient.getLinks().get("self"));
		return identifier.getId();
	}
	
	private <T extends Resource> void printResourceToSystemOut(T resource, boolean isJson) {
		if(logResource) {
			System.out.println(new String(ClientUtils.getResourceAsByteArray(resource, true, isJson)));
		}
	}
}
