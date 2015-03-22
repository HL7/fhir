package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.client.ClientUtils;
import org.hl7.fhir.instance.client.EFhirClientException;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.client.ResourceAddress;
import org.hl7.fhir.instance.client.ResourceFormat;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Condition.ConditionStatus;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.Observation;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Patient.AdministrativeGender;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FHIRSimpleClientTest {
	
	private static String connectUrl = null;
	private static String userAgent = null;
	private static DateType testDateAndTime = null;
	
	private IFHIRClient testClient;
	private String testPatientId;
	private String testPatientVersion;
	private boolean logResource = true;
	private boolean useProxy = true;
	
	@SuppressWarnings("unused")
  private static void configureForFurore() {
		connectUrl = "http://spark.furore.com/fhir/";
		//connectUrl = "http://fhirlab.furore.com/fhir";
		userAgent = "Spark.Service";
	}
	
	private static void configureForHealthIntersection() {
		//connectUrl = "http://hl7connect.healthintersections.com.au/svc/fhir/";
		connectUrl = "http://fhir.healthintersections.com.au/open";
		//userAgent = "HL7Connect";
		userAgent = "Reference Server";
	}
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		configureForHealthIntersection();
		//configureForFurore();
		testDateAndTime = new DateType("2008-08-08");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testClient = new FHIRSimpleClient();
		testClient.initialize(connectUrl);
		if(useProxy) {
			((FHIRSimpleClient)testClient).configureProxy("127.0.0.1", 8888);
		}
	}

	@After
	public void tearDown() throws Exception {	
	}
	
	/**************************************************************
	 * START OF TEST SECTION
	 **************************************************************/

	@Test
	public void testFHIRSimpleClient() {
		try {
			IFHIRClient client = new FHIRSimpleClient();
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
			assertEquals(userAgent, stmt.getSoftware().getName());
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
			assertEquals(userAgent, stmt.getSoftware().getName());
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
			assertEquals(userAgent, stmt.getSoftware().getName());
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
			assertEquals(userAgent, stmt.getSoftware().getName());
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
			assertEquals(userAgent, stmt.getSoftware().getName());
			printResourceToSystemOut(stmt, true);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRead() {
		loadPatientReference();
		Patient fetchedPatient = testClient.read(Patient.class, testPatientId);
		assertEqualDate(fetchedPatient.getBirthDateElement(),testDateAndTime);
		assertEquals(2, fetchedPatient.getMeta().getTag().size());
		unloadPatientReference();
	}

	@Test
	public void testVread() {
		try {
			loadPatientReference();
			Patient fetchedPatient = testClient.vread(Patient.class, testPatientId, testPatientVersion);
			assertEqualDate(fetchedPatient.getBirthDateElement(),testDateAndTime);
			assertEquals(2, fetchedPatient.getMeta().getTag().size());
			unloadPatientReference();
		} catch(EFhirClientException e) {
			List<OperationOutcome> outcomes = e.getServerErrors();
			for(OperationOutcome outcome : outcomes) {
				for(OperationOutcomeIssueComponent issue : outcome.getIssue()) {
					System.out.println(issue.getDetails());
				}
			}
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdate() {
			loadPatientReference();
			Patient originalPatientEntry = testClient.read(Patient.class, testPatientId);
			String originalEntryVersion = getEntryVersion(originalPatientEntry);
		DateType modifiedBirthday = new DateType("2002-09-09");
			originalPatientEntry.setBirthDateElement(modifiedBirthday);
			Patient updatedResult = testClient.update(Patient.class, originalPatientEntry, testPatientId);
			if (updatedResult == null) {
				updatedResult = testClient.read(Patient.class, testPatientId);
			}
			String resourceId = getEntryId(updatedResult);
			String resourceType = getResourceType(updatedResult);
			String entryVersion = getEntryVersion(updatedResult);
			assertEquals(resourceId, testPatientId);
			assertEquals("Patient", resourceType);
			assertEquals(Integer.parseInt(originalEntryVersion) + 1, Integer.parseInt(entryVersion));
			Patient fetchedUpdatedPatientEntry = testClient.read(Patient.class, testPatientId);
		assertEqualDate(new DateType("2002-09-09"), fetchedUpdatedPatientEntry.getBirthDateElement());
			unloadPatientReference();
	}
	
	@Test
	public void testCreate() {
		Patient patientRequest = buildPatient();
		OperationOutcome result = testClient.create(Patient.class, patientRequest);
		if(result != null) {
			assertEquals(0, result.getIssue().size());
		}
		String resourceId = getEntryId(result);
		String resourceType = getResourceType(result);
		String entryVersion = getEntryVersion(result);
		assertEquals("Patient", resourceType);
		assertNotNull(resourceId);
		assertNotNull(entryVersion);
	}
	
	@Test
	public void testDelete() {
		Patient patientRequest = buildPatient();
		OperationOutcome result = testClient.create(Patient.class, patientRequest);
		boolean success = testClient.delete(Patient.class, getEntryId(result));
		assertTrue(success);
	}
	
	@Test
	public void testValidate() {
			loadPatientReference();
			Patient patient = testClient.read(Patient.class, testPatientId);
		DateType modifiedBirthday = new DateType("2009-08-08");
			patient.setBirthDateElement(modifiedBirthday);
			OperationOutcome validate = testClient.validate(Patient.class, patient, testPatientId);
			assertTrue(validate.getIssue().size() == 0);//TODO not sure why bad syntax
			unloadPatientReference();
	}
	

	@Test
	public void testGetHistoryForResourceWithId() {
		loadPatientReference();
		Patient patient = testClient.read(Patient.class, testPatientId);
		testClient.update(Patient.class, patient, testPatientId);
		testClient.update(Patient.class, patient, testPatientId);
		Bundle feed = testClient.history(Patient.class, testPatientId);
		assertNotNull(feed);
		assertEquals(3, feed.getEntry().size());
	}
	
	
	@Test
	public void testGetHistoryForResourcesOfTypeSinceCalendarDate() {
		try {
			Calendar testDate = Calendar.getInstance();
			testDate.add(Calendar.MINUTE, -10);
			Patient patient = buildPatient();
			OperationOutcome createdEntry = testClient.create(Patient.class, patient);
			testClient.update(Patient.class, patient, getEntryId(createdEntry));
			Bundle feed = testClient.history(testDate, Patient.class);
			assertNotNull(feed);
			assertTrue(feed.getEntry().size() > 0);
			testClient.delete(Patient.class, getEntryId(createdEntry));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testHistoryForAllResourceTypes() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -24);
		Date testDate = cal.getTime();
		Bundle feed = testClient.history(testDate);
		assertNotNull(feed);
		assertTrue(feed.getEntry().size() > 1);
	}
	
	@Test
	public void testHistoryForAllResourceTypesWithCount() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -24);
		Date testDate = cal.getTime();
		Bundle feed = testClient.history(testDate);
		assertNotNull(feed);
		System.out.println(feed.getEntry().size());
		assertTrue(feed.getEntry().size() > 1);
	}

	@Test
	public void testGetHistoryForResourceWithIdSinceCalendarDate() {
		Calendar testDate = Calendar.getInstance();
		testDate.add(Calendar.MINUTE, -10);
		Patient patient = buildPatient();
		OperationOutcome entry = testClient.create(Patient.class, buildPatient());
		testClient.update(Patient.class, patient, getEntryId(entry));
		testClient.update(Patient.class, patient, getEntryId(entry));
		Bundle feed = testClient.history(testDate, Patient.class, getEntryId(entry));
		assertNotNull(feed);
		assertEquals(3, feed.getEntry().size());
		testClient.delete(Patient.class, getEntryId(entry));
	}

	@Test
	public void testSearchForSingleReference() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("_count", "50");
		parameters.put("gender", "female");
		parameters.put("birthdate", "2008-08-08");
		Bundle feed = testClient.search(Patient.class, parameters);
		assertTrue(feed != null);
		System.out.println(feed.getEntry().size());
		assertTrue(feed.getEntry().size() > 0);
	}
	
	@Test
	public void testSearchPatientByGivenName() {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			String firstName = "Jsuis_" +  + System.currentTimeMillis();
			String lastName = "Malade";
			String fullName = firstName + " " + lastName;
			searchMap.put("given", firstName);
			Patient patient = buildPatient(fullName, firstName, lastName);
			OperationOutcome createdPatientEntry = testClient.create(Patient.class, patient);
			Bundle feed = testClient.search(Patient.class, searchMap);
			int resultSetSize = feed.getEntry().size();
			System.out.println(resultSetSize);
			assertTrue(resultSetSize == 1);
			testClient.delete(Patient.class, getEntryId(createdPatientEntry));
		} catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testTransactionSuccess() {
		try {
			Patient patient = buildPatient();
			OperationOutcome createdPatientEntry = testClient.create(Patient.class, patient);
			patient.setBirthDateElement(new DateType("1966-01-10"));
			Reference patientReference = new Reference();
			patient.setId(getEntryPath(createdPatientEntry));
			patientReference.setReference(getEntryPath(createdPatientEntry));
			Observation obs = new Observation();
			obs.setSubject(patientReference);
			obs.setApplies(Factory.newDateTime("2013-01-10"));
			OperationOutcome createdObservationEntry = testClient.create(Observation.class, obs);
			obs.setId(getEntryPath(createdObservationEntry));
			Bundle batchFeed = new Bundle();
			batchFeed.getEntry().add(new BundleEntryComponent().setResource(patient));
			batchFeed.getEntry().add(new BundleEntryComponent().setResource(obs));
			System.out.println(new String(ClientUtils.getFeedAsByteArray(batchFeed, false, false)));
			Bundle responseFeed = testClient.transaction(batchFeed);
			assertNotNull(responseFeed);
			assert(responseFeed.getEntry().get(0).getResource() instanceof Patient);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSimpleTransaction1() {
		try {
			Patient patient = buildPatient();
			Bundle batchFeed = new Bundle();
			patient.setId("cid:Patient/temp1");
			batchFeed.getEntry().add(new BundleEntryComponent().setResource(patient));
			Bundle responseFeed = null;
			try {
				responseFeed = testClient.transaction(batchFeed);
			} catch(EFhirClientException e) {
				e.printStackTrace();
				fail();
			}
			assertNotNull(responseFeed);
			assertEquals(1, responseFeed.getEntry().size());
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSimpleTransaction2() {
		try {
			Patient patient = buildPatient();
			OperationOutcome createdPatientEntry = testClient.create(Patient.class, patient);
			patient.setBirthDateElement(new DateType("1966-01-10"));
			Bundle batchFeed = new Bundle();
			patient.setId(getEntryPath(createdPatientEntry));
			batchFeed.getEntry().add(new BundleEntryComponent().setResource(patient));
			Bundle responseFeed = null;
			try {
				responseFeed = testClient.transaction(batchFeed);
			} catch(EFhirClientException e) {
				e.printStackTrace();
				fail();
			}
			assertNotNull(responseFeed);
			assertEquals(1, responseFeed.getEntry().size());
			testClient.delete(Patient.class, getEntryId(createdPatientEntry));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testTransactionError() {
		try {
			Patient patient = buildPatient();
			OperationOutcome createdPatientEntry = testClient.create(Patient.class, patient);
			patient.setBirthDateElement(new DateType("1966-01-10"));
			Bundle batchFeed = new Bundle();
			patient.setId(getEntryId(createdPatientEntry));
			batchFeed.getEntry().add(new BundleEntryComponent().setResource(patient));
			batchFeed.getEntry().add(new BundleEntryComponent().setResource(patient));
			Bundle responseFeed = null;
			try {
				responseFeed = testClient.transaction(batchFeed);
			} catch(EFhirClientException e) {
				assertEquals(1, e.getServerErrors().size());
			}
			if(responseFeed != null) {
				fail();
			}
			testClient.delete(Patient.class, getEntryId(createdPatientEntry));
		} catch(Exception e) {
			fail();
		}
	}
	
//	@Test
//	public void testGetAllTags() {
//		List<Coding> tags = testClient.getAllTags();
//		assertTrue(tags != null && tags.size() > 0);
//	}
//	
//	@Test
//	public void testGetAllTagsForResourceType() {
//		List<Coding> tags = testClient.getAllTagsForResourceType(Patient.class);
//		assertTrue(tags != null && tags.size() > 0);
//	}
//	
//	@Test
//	public void testGetTagsForReference() {
//		loadPatientReference();
//		List<Coding> tags = testClient.getTagsForReference(Patient.class, testPatientId);
//		assertTrue(tags != null && tags.size() > 0);
//		unloadPatientReference();
//	}
//	
//	@Test
//	public void testGetTagsForResourceVersion() {
//		loadPatientReference();
//		List<Coding> tags = testClient.getTagsForResourceVersion(Patient.class, testPatientId, testPatientVersion);
//		assertTrue(tags != null && tags.size() > 0);
//		unloadPatientReference();
//	}
//	
//	@Test
//	public void testDeleteTagsForReference() {
//		loadPatientReference();
//		boolean success = testClient.deleteTagsForReference(Patient.class, testPatientId);
//		assertTrue(success);
//		unloadPatientReference();
//	}
//	
//	@Test
//	public void testDeleteTagsForResourceVersion() {
//		loadPatientReference();
//		List<Coding> tags = generateCategoryHeader();
//		boolean success = testClient.deleteTagsForResourceVersion(Patient.class, testPatientId, tags, testPatientVersion);
//		assertTrue(success);
//		unloadPatientReference();
//	}
//	
//	@Test
//	public void testCreateTagsForReference() {
//		loadPatientReference();
//		List<Coding> tags = new ArrayList<Coding>();
//		tags.add(new Coding()); // todo-bundle "http://scheme.com", "http://term.com", "Some good ole term"));
//		testClient.createTags(tags, Patient.class, testPatientId);
//		unloadPatientReference();
//	}
//	
//	@Test
//	public void testCreateTagsForResourceVersion() {
//		loadPatientReference();
//		List<Coding> tags = new ArrayList<Coding>();
//		tags.add(new Coding()); // todo-bundle "http://scheme.com", "http://term.com", "Some good ole term"));
//		testClient.createTags(tags, Patient.class, testPatientId, testPatientVersion);
//		unloadPatientReference();
//	}

/*
	@Test
	public void testRetrievePatientConditionList() {
		try {
			Patient patient =  buildPatient();
			OperationOutcome patientResult = testClient.create(Patient.class, buildPatient());
			OperationOutcome condition = testClient.create(Condition.class, buildCondition(patient));
			Map<String, String> searchParameters = new HashMap<String,String>();
			System.out.println(getEntryPath(patient));
			searchParameters.put("subject", "patient/"+getEntryId(patient));
			System.out.println(patient.getLinks().get("self"));
			Bundle conditions = testClient.search(Condition.class, searchParameters);
			System.out.println(getEntryId(patient));
			System.out.println(conditions.getItem().size());
			assertTrue(conditions.getItem().size() > 0);
			testClient.delete(Condition.class, getEntryId(condition));
			testClient.delete(Patient.class, getEntryId(patient));
		} catch(EFhirClientException e) {
			List<OperationOutcome> outcomes = e.getServerErrors();
			for(OperationOutcome outcome : outcomes) {
				for(OperationOutcomeIssueComponent issue : outcome.getIssue()) {
					System.out.println(issue.getDetails());
				}
			}
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void createNonDuplicatePatientConditionNoPreexisting() {
		try {
				//Create a patient resource
			Patient patient = buildPatient();
			OperationOutcome createdPatientEntry = testClient.create(Patient.class, patient);
			//Search for Patient's conditions - none returned
			Map<String,String> searchParameters = new HashMap<String,String>();
			searchParameters.put("subject", "patient/@" + getEntryId(createdPatientEntry));
			Bundle conditions = testClient.search(Condition.class, searchParameters);
			//No pre-existing conditions
			assertTrue(conditions.getItem().size() == 0);
			//build new condition
			Condition condition = buildCondition(createdPatientEntry);
			//create condition
			AtomEntry<Condition> createdConditionEntry = testClient.create(Condition.class, condition);
			//fetch condition and ensure it has an ID
			AtomEntry<Condition> retrievedConditionEntry = testClient.read(Condition.class, getEntryId(createdConditionEntry));
			//Check that subject is patient
			condition = retrievedConditionEntry.getReference();
			String patientReference = condition.getSubject().getReference();
			System.out.println(patientReference);
			assertTrue(patientReference.equalsIgnoreCase("patient/@"+getEntryId(createdPatientEntry)));
			//Delete patient resource
		} catch(EFhirClientException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void createNonDuplicatePatientConditionPreexisting() {
		try {
			//Create a patient resource
			Patient patient = buildPatient();
			OperationOutcome createdPatientEntry = testClient.create(Patient.class, patient);
			//Search for Patient's conditions - none returned
			Map<String,String> searchParameters = new HashMap<String,String>();
			searchParameters.put("subject", "patient/@" + getEntryId(createdPatientEntry));
			Bundle conditions = testClient.search(Condition.class, searchParameters);
			//No pre-existing conditions
			assertTrue(conditions.getItem().size() == 0);
			//build new condition
			Condition condition1 = buildCondition(createdPatientEntry);
			//build a condition that is not preexisting
			CodeableConcept diabetesMellitus = createCodeableConcept("73211009", "http://snomed.info/id", "Diabetes mellitus (disorder)");
			Condition condition2 = buildCondition(createdPatientEntry, diabetesMellitus);
			//create condition
			OperationOutcome createdConditionEntry1 = testClient.create(Condition.class, condition1);
			//fetch condition and ensure it has an ID
			AtomEntry<Condition> retrievedConditionEntry1 = testClient.read(Condition.class, getEntryId(createdConditionEntry1));
			//Check that subject is patient
			condition1 = retrievedConditionEntry1.getReference();
			String patientReference = condition1.getSubject().getReference();
			assertTrue(patientReference.equalsIgnoreCase("patient/@"+getEntryId(createdPatientEntry)));
			//Get all conditions for this patient
			Bundle preexistingConditions = testClient.search(Condition.class, searchParameters);
			assertTrue(preexistingConditions.getItem().size() == 1);
			AtomEntry<Condition> preexistingConditionEntry = (AtomEntry<Condition>)preexistingConditions.getItem().get(0);
			assertTrue(preexistingConditionEntry.getReference().getCode().getCoding().get(0).getCode().equalsIgnoreCase("29530003"));
			assertNotNull(preexistingConditionEntry.getReference().getCode().getCoding().get(0).getSystem().equalsIgnoreCase("http://snomed.info/id"));
			assertTrue(preexistingConditionEntry.getReference().getCode().getCoding().get(0).getDisplay().equalsIgnoreCase("Fungal granuloma (disorder)"));
			//Add new condition
			AtomEntry<Condition> createdConditionEntry2 = testClient.create(Condition.class, condition2);
			preexistingConditions = testClient.search(Condition.class, searchParameters);
			assertTrue(preexistingConditions.getItem().size() == 2);
			//Delete patient resource
		} catch(EFhirClientException e) {
			e.printStackTrace();
			fail();
		}
	}
*/
	/**************************************************************
	 * END OF TEST SECTION
	 **************************************************************/
	
	/**************************************************************
	 * Helper Methods
	 **************************************************************/
	private CodeableConcept createCodeableConcept(String code, String system, String displayNameSimple) {
		CodeableConcept conditionCode = new CodeableConcept();
		Coding coding = conditionCode.addCoding();
		coding.setCode(code);
		coding.setSystem(system);
		coding.setDisplay(displayNameSimple);
		return conditionCode;
	}
	
	@SuppressWarnings("unused")
  private Condition buildCondition(Patient patientEntry) {
		CodeableConcept conditionCode = createCodeableConcept("29530003", "http://snomed.info/id", "Fungal granuloma (disorder)");
		return buildCondition(patientEntry, conditionCode);
	}
	
	private Condition buildCondition(Patient patientEntry, CodeableConcept conditionCode) {
		Condition condition = null;
		try {
			condition = new Condition();
			Reference patientReference = new Reference();
			patientReference.setReference("patient/@"+getEntryId(patientEntry));
			condition.setPatient(patientReference);
			condition.setCode(conditionCode);
			condition.setClinicalStatus(ConditionStatus.CONFIRMED);
		} catch (Exception e) {
			fail();
		}
		return condition;
	}

	private Patient buildPatient() {
		return buildPatient("Jsuis Malade", "Jsuis", "Malade");
	}
	
	private Patient buildPatient(String fullName, String givenName, String familyName) {
		Patient patient = new Patient();
			HumanName name = patient.addName();
			name.setText(fullName);
			name.addGiven(givenName);
			name.addFamily(familyName);
		DateType birthday = new DateType("2008-08-08");
			patient.setBirthDateElement(birthday);
			patient.setGender(AdministrativeGender.FEMALE); // This is now a Simple code value
		return patient;
	}
	
	private void loadPatientReference() {
		Patient testPatient = buildPatient();
		List<Coding> tags = generateCategoryHeader();
		OperationOutcome result = testClient.create(Patient.class, testPatient);
		testPatientId = getEntryId(result);
		testPatientVersion = getEntryVersion(result);
	}
	
	private List<Coding> generateCategoryHeader() {
		List<Coding> tags = new ArrayList<Coding>();
		tags.add(new Coding()); // todo-bundle "http://client/scheme", "http://client/scheme/tag/123","tag 123"));
		tags.add(new Coding()); // todo-bundle "http://client/scheme", "http://client/scheme/tag/456","tag 456"));
		return tags;
	}
	
	private void unloadPatientReference() {
		testClient.delete(Patient.class, testPatientId);
	}
	
	private <T extends Resource> ResourceAddress.ResourceVersionedIdentifier getAtomEntryLink(T entry, String linkName) {
		// todo-bundle return ResourceAddress.parseCreateLocation(entry.getLinks().get(linkName));
		return null;
	}
	
	private <T extends Resource> ResourceAddress.ResourceVersionedIdentifier getAtomEntrySelfLink(T entry) {
		return getAtomEntryLink(entry, "self");
	}
	
	private <T extends Resource> String getEntryId(T entry) {
		return getAtomEntrySelfLink(entry).getId();
	}
	
	private <T extends Resource> String getEntryVersion(T entry) {
		return getAtomEntrySelfLink(entry).getVersion();
	}
	
	private <T extends Resource> String getResourceType(T entry) {
		return getAtomEntrySelfLink(entry).getResourceType();
	}
	
	private <T extends Resource> String getEntryPath(T entry) {
		return getAtomEntrySelfLink(entry).getResourcePath();
	}
	
	@SuppressWarnings("unused")
  private <T extends Resource> String getResourceId(T entry) {
		return getAtomEntrySelfLink(entry).getId();
	}
	
	private <T extends Resource> void printResourceToSystemOut(T resource, boolean isJson) {
		if(logResource) {
			System.out.println(new String(ClientUtils.getResourceAsByteArray(resource, true, isJson)));
		}
	}
	
	private void assertEqualDate(DateType originalDate, DateType modifiedDate) {
		assert(originalDate.equals(modifiedDate));
	}
}
