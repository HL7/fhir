package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Condition.ConditionStatus;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Patient.AdministrativeGender;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceReference;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FHIRSimpleClientTest {
	
	private static String connectUrl = null;
	private static String userAgent = null;
	private static DateAndTime testDateAndTime = null;
	
	private FHIRClient testClient;
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
		testDateAndTime = new DateAndTime("2008-08-08");
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
			assertEquals(userAgent, stmt.getSoftware().getName().getValue());
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
			assertEquals(userAgent, stmt.getSoftware().getName().getValue());
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
			assertEquals(userAgent, stmt.getSoftware().getName().getValue());
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
			assertEquals(userAgent, stmt.getSoftware().getName().getValue());
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
			assertEquals(userAgent, stmt.getSoftware().getName().getValue());
			printResourceToSystemOut(stmt, true);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRead() {
		loadPatientResource();
		AtomEntry<Patient> fetchedPatient = testClient.read(Patient.class, testPatientId);
		assertEqualDate(fetchedPatient.getResource().getBirthDate().getValue(),testDateAndTime);
		assertEquals(2, fetchedPatient.getTags().size());
		unloadPatientResource();
	}

	@Test
	public void testVread() {
		try {
			loadPatientResource();
			AtomEntry<Patient> fetchedPatient = testClient.vread(Patient.class, testPatientId, testPatientVersion);
			assertEqualDate(fetchedPatient.getResource().getBirthDate().getValue(),testDateAndTime);
			assertEquals(2, fetchedPatient.getTags().size());
			unloadPatientResource();
		} catch(EFhirClientException e) {
			List<OperationOutcome> outcomes = e.getServerErrors();
			for(OperationOutcome outcome : outcomes) {
				for(OperationOutcomeIssueComponent issue : outcome.getIssue()) {
					System.out.println(issue.getDetailsSimple());
				}
			}
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdate() {
		try {
			loadPatientResource();
			AtomEntry<Patient> originalPatientEntry = testClient.read(Patient.class, testPatientId);
			String originalEntryVersion = getEntryVersion(originalPatientEntry);
			DateTimeType modifiedBirthday = new DateTimeType();
			modifiedBirthday.setValue(new DateAndTime("2002-09-09"));
			originalPatientEntry.getResource().setBirthDate(modifiedBirthday);
			AtomEntry<Patient> updatedResult = testClient.update(Patient.class, originalPatientEntry.getResource(), testPatientId);
			if(updatedResult.getResource() == null) {
				updatedResult = testClient.read(Patient.class, testPatientId);
			}
			String resourceId = getEntryId(updatedResult);
			String resourceType = getResourceType(updatedResult);
			String entryVersion = getEntryVersion(updatedResult);
			assertEquals(resourceId, testPatientId);
			assertEquals("Patient", resourceType);
			assertEquals(Integer.parseInt(originalEntryVersion) + 1, Integer.parseInt(entryVersion));
			AtomEntry<Patient> fetchedUpdatedPatientEntry = testClient.read(Patient.class, testPatientId);
			assertEqualDate(new DateAndTime("2002-09-09"), fetchedUpdatedPatientEntry.getResource().getBirthDateSimple());
			unloadPatientResource();
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreate() {
		Patient patientRequest = buildPatient();
		AtomEntry<OperationOutcome> result = testClient.create(Patient.class, patientRequest);
		if(result.getResource() != null) {
			assertEquals(0, result.getResource().getIssue().size());
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
		AtomEntry<OperationOutcome> result = testClient.create(Patient.class, patientRequest);
		boolean success = testClient.delete(Patient.class, getEntryId(result));
		assertTrue(success);
	}
	
	@Test
	public void testValidate() {
		try {
			loadPatientResource();
			Patient patient = testClient.read(Patient.class, testPatientId).getResource();
			DateTimeType modifiedBirthday = new DateTimeType();
			modifiedBirthday.setValue(new DateAndTime("2009-08-08"));
			patient.setBirthDate(modifiedBirthday);
			AtomEntry<OperationOutcome> validate = testClient.validate(Patient.class, patient, testPatientId);
			assertTrue(validate.getResource().getIssue().size() == 0);//TODO not sure why bad syntax
			unloadPatientResource();
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	

	@Test
	public void testGetHistoryForResourceWithId() {
		loadPatientResource();
		Patient patient = testClient.read(Patient.class, testPatientId).getResource();
		testClient.update(Patient.class, patient, testPatientId);
		testClient.update(Patient.class, patient, testPatientId);
		AtomFeed feed = testClient.history(Patient.class, testPatientId);
		assertNotNull(feed);
		assertEquals(3, feed.getEntryList().size());
	}
	
	
	@Test
	public void testGetHistoryForResourcesOfTypeSinceCalendarDate() {
		try {
			Calendar testDate = Calendar.getInstance();
			testDate.add(Calendar.MINUTE, -10);
			Patient patient = buildPatient();
			AtomEntry<OperationOutcome> createdEntry = testClient.create(Patient.class, patient);
			testClient.update(Patient.class, patient, getEntryId(createdEntry));
			AtomFeed feed = testClient.history(testDate, Patient.class);
			assertNotNull(feed);
			assertTrue(feed.getEntryList().size() > 0);
			testClient.delete(Patient.class, getEntryId(createdEntry));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testHistoryForAllResourceTypes() throws Exception {
		DateAndTime testDate = DateAndTime.now();
		//testDate.add(Calendar.HOUR, -24);
		Calendar cal = testDate.toCalendar();
		cal.add(Calendar.HOUR, -24);
		testDate = new DateAndTime(cal);
		AtomFeed feed = testClient.history(testDate);
		assertNotNull(feed);
		assertTrue(feed.getEntryList().size() > 1);
	}
	
	@Test
	public void testHistoryForAllResourceTypesWithCount() throws Exception {
		DateAndTime testDate = DateAndTime.now();
		testClient.setMaximumRecordCount(5);
		Calendar cal = testDate.toCalendar();
		cal.add(Calendar.HOUR, -24);
		testDate = new DateAndTime(cal);
		AtomFeed feed = testClient.history(testDate);
		assertNotNull(feed);
		System.out.println(feed.getEntryList().size());
		assertTrue(feed.getEntryList().size() > 1);
	}

	@Test
	public void testGetHistoryForResourceWithIdSinceCalendarDate() {
		Calendar testDate = Calendar.getInstance();
		testDate.add(Calendar.MINUTE, -10);
		Patient patient = buildPatient();
		AtomEntry<OperationOutcome> entry = testClient.create(Patient.class, buildPatient());
		testClient.update(Patient.class, patient, getEntryId(entry));
		testClient.update(Patient.class, patient, getEntryId(entry));
		AtomFeed feed = testClient.history(testDate, Patient.class, getEntryId(entry));
		assertNotNull(feed);
		assertEquals(3, feed.getEntryList().size());
		testClient.delete(Patient.class, getEntryId(entry));
	}

	@Test
	public void testSearchForSingleResource() {
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
	public void testSearchPatientByGivenName() {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			String firstName = "Jsuis_" +  + System.currentTimeMillis();
			String lastName = "Malade";
			String fullName = firstName + " " + lastName;
			searchMap.put("given", firstName);
			Patient patient = buildPatient(fullName, firstName, lastName);
			AtomEntry<OperationOutcome> createdPatientEntry = testClient.create(Patient.class, patient);
			AtomFeed feed = testClient.search(Patient.class, searchMap);
			int resultSetSize = feed.getEntryList().size();
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
			AtomEntry<OperationOutcome> createdPatientEntry = testClient.create(Patient.class, patient);
			patient.setBirthDateSimple(new DateAndTime("1966-01-10"));
			ResourceReference patientReference = new ResourceReference();
			AtomEntry<Patient> patientEntry = new AtomEntry<Patient>();
			patientEntry.setResource(patient);
			patientEntry.setId(getEntryPath(createdPatientEntry));
			patientEntry.getLinks().put("self", createdPatientEntry.getLinks().get("self"));
			patientReference.setReferenceSimple(getEntryPath(createdPatientEntry));
			AdverseReaction adverseReaction = new AdverseReaction();
			adverseReaction.setSubject(patientReference);
			adverseReaction.setDateSimple(new DateAndTime("2013-01-10"));
			adverseReaction.setDidNotOccurFlagSimple(false);
			AtomEntry<OperationOutcome> createdAdverseReactionEntry = testClient.create(AdverseReaction.class, adverseReaction);
			AtomEntry<AdverseReaction> adverseReactionEntry = new AtomEntry<AdverseReaction>();
			adverseReactionEntry.setResource(adverseReaction);
			adverseReactionEntry.setId(getEntryPath(createdAdverseReactionEntry));
			adverseReactionEntry.getLinks().put("self", createdAdverseReactionEntry.getLinks().get("self"));
			AtomFeed batchFeed = new AtomFeed();
			batchFeed.getEntryList().add(patientEntry);
			batchFeed.getEntryList().add(adverseReactionEntry);
			System.out.println(new String(ClientUtils.getFeedAsByteArray(batchFeed, false, false)));
			AtomFeed responseFeed = testClient.transaction(batchFeed);
			assertNotNull(responseFeed);
			assert(responseFeed.getEntryList().get(0).getResource() instanceof Patient);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSimpleTransaction1() {
		try {
			Patient patient = buildPatient();
			AtomFeed batchFeed = new AtomFeed();
			AtomEntry<Patient> patientEntry = new AtomEntry<Patient>();
			patientEntry.setId("cid:Patient/temp1");
			patientEntry.setResource(patient);
			batchFeed.getEntryList().add(patientEntry);
			AtomFeed responseFeed = null;
			try {
				responseFeed = testClient.transaction(batchFeed);
			} catch(EFhirClientException e) {
				e.printStackTrace();
				fail();
			}
			assertNotNull(responseFeed);
			assertEquals(1, responseFeed.getEntryList().size());
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSimpleTransaction2() {
		try {
			Patient patient = buildPatient();
			AtomEntry<OperationOutcome> createdPatientEntry = testClient.create(Patient.class, patient);
			patient.setBirthDateSimple(new DateAndTime("1966-01-10"));
			AtomFeed batchFeed = new AtomFeed();
			AtomEntry<Patient> patientEntry = new AtomEntry<Patient>();
			patientEntry.getLinks().put("self", createdPatientEntry.getLinks().get("self"));
			patientEntry.setId(getEntryPath(createdPatientEntry));
			patientEntry.setResource(patient);
			batchFeed.getEntryList().add(patientEntry);
			AtomFeed responseFeed = null;
			try {
				responseFeed = testClient.transaction(batchFeed);
			} catch(EFhirClientException e) {
				e.printStackTrace();
				fail();
			}
			assertNotNull(responseFeed);
			assertEquals(1, responseFeed.getEntryList().size());
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
			AtomEntry<OperationOutcome> createdPatientEntry = testClient.create(Patient.class, patient);
			patient.setBirthDateSimple(new DateAndTime("1966-01-10"));
			AtomFeed batchFeed = new AtomFeed();
			AtomEntry<Patient> patientEntry = new AtomEntry<Patient>();
			patientEntry.getLinks().put("self", createdPatientEntry.getLinks().get("self"));
			patientEntry.setId(getEntryId(createdPatientEntry));
			patientEntry.setResource(patient);
			batchFeed.getEntryList().add(patientEntry);
			batchFeed.getEntryList().add(patientEntry);
			AtomFeed responseFeed = null;
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
	
	@Test
	public void testGetAllTags() {
		List<AtomCategory> tags = testClient.getAllTags();
		assertTrue(tags != null && tags.size() > 0);
	}
	
	@Test
	public void testGetAllTagsForResourceType() {
		List<AtomCategory> tags = testClient.getAllTagsForResourceType(Patient.class);
		assertTrue(tags != null && tags.size() > 0);
	}
	
	@Test
	public void testGetTagsForResource() {
		loadPatientResource();
		List<AtomCategory> tags = testClient.getTagsForResource(Patient.class, testPatientId);
		assertTrue(tags != null && tags.size() > 0);
		unloadPatientResource();
	}
	
	@Test
	public void testGetTagsForResourceVersion() {
		loadPatientResource();
		List<AtomCategory> tags = testClient.getTagsForResourceVersion(Patient.class, testPatientId, testPatientVersion);
		assertTrue(tags != null && tags.size() > 0);
		unloadPatientResource();
	}
	
//	@Test
//	public void testDeleteTagsForResource() {
//		loadPatientResource();
//		boolean success = testClient.deleteTagsForResource(Patient.class, testPatientId);
//		assertTrue(success);
//		unloadPatientResource();
//	}
//	
//	@Test
//	public void testDeleteTagsForResourceVersion() {
//		loadPatientResource();
//		List<AtomCategory> tags = generateCategoryHeader();
//		boolean success = testClient.deleteTagsForResourceVersion(Patient.class, testPatientId, tags, testPatientVersion);
//		assertTrue(success);
//		unloadPatientResource();
//	}
	
	@Test
	public void testCreateTagsForResource() {
		loadPatientResource();
		List<AtomCategory> tags = new ArrayList<AtomCategory>();
		tags.add(new AtomCategory("http://scheme.com", "http://term.com", "Some good ole term"));
		testClient.createTags(tags, Patient.class, testPatientId);
		unloadPatientResource();
	}
	
	@Test
	public void testCreateTagsForResourceVersion() {
		loadPatientResource();
		List<AtomCategory> tags = new ArrayList<AtomCategory>();
		tags.add(new AtomCategory("http://scheme.com", "http://term.com", "Some good ole term"));
		testClient.createTags(tags, Patient.class, testPatientId, testPatientVersion);
		unloadPatientResource();
	}

/*
	@Test
	public void testRetrievePatientConditionList() {
		try {
			Patient patient =  buildPatient();
			AtomEntry<OperationOutcome> patientResult = testClient.create(Patient.class, buildPatient());
			AtomEntry<OperationOutcome> condition = testClient.create(Condition.class, buildCondition(patient));
			Map<String, String> searchParameters = new HashMap<String,String>();
			System.out.println(getEntryPath(patient));
			searchParameters.put("subject", "patient/"+getEntryId(patient));
			System.out.println(patient.getLinks().get("self"));
			AtomFeed conditions = testClient.search(Condition.class, searchParameters);
			System.out.println(getEntryId(patient));
			System.out.println(conditions.getEntryList().size());
			assertTrue(conditions.getEntryList().size() > 0);
			testClient.delete(Condition.class, getEntryId(condition));
			testClient.delete(Patient.class, getEntryId(patient));
		} catch(EFhirClientException e) {
			List<OperationOutcome> outcomes = e.getServerErrors();
			for(OperationOutcome outcome : outcomes) {
				for(OperationOutcomeIssueComponent issue : outcome.getIssue()) {
					System.out.println(issue.getDetailsSimple());
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
			AtomEntry<OperationOutcome> createdPatientEntry = testClient.create(Patient.class, patient);
			//Search for Patient's conditions - none returned
			Map<String,String> searchParameters = new HashMap<String,String>();
			searchParameters.put("subject", "patient/@" + getEntryId(createdPatientEntry));
			AtomFeed conditions = testClient.search(Condition.class, searchParameters);
			//No pre-existing conditions
			assertTrue(conditions.getEntryList().size() == 0);
			//build new condition
			Condition condition = buildCondition(createdPatientEntry);
			//create condition
			AtomEntry<Condition> createdConditionEntry = testClient.create(Condition.class, condition);
			//fetch condition and ensure it has an ID
			AtomEntry<Condition> retrievedConditionEntry = testClient.read(Condition.class, getEntryId(createdConditionEntry));
			//Check that subject is patient
			condition = retrievedConditionEntry.getResource();
			String patientReference = condition.getSubject().getReferenceSimple();
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
			AtomEntry<OperationOutcome> createdPatientEntry = testClient.create(Patient.class, patient);
			//Search for Patient's conditions - none returned
			Map<String,String> searchParameters = new HashMap<String,String>();
			searchParameters.put("subject", "patient/@" + getEntryId(createdPatientEntry));
			AtomFeed conditions = testClient.search(Condition.class, searchParameters);
			//No pre-existing conditions
			assertTrue(conditions.getEntryList().size() == 0);
			//build new condition
			Condition condition1 = buildCondition(createdPatientEntry);
			//build a condition that is not preexisting
			CodeableConcept diabetesMellitus = createCodeableConcept("73211009", "http://snomed.info/id", "Diabetes mellitus (disorder)");
			Condition condition2 = buildCondition(createdPatientEntry, diabetesMellitus);
			//create condition
			AtomEntry<OperationOutcome> createdConditionEntry1 = testClient.create(Condition.class, condition1);
			//fetch condition and ensure it has an ID
			AtomEntry<Condition> retrievedConditionEntry1 = testClient.read(Condition.class, getEntryId(createdConditionEntry1));
			//Check that subject is patient
			condition1 = retrievedConditionEntry1.getResource();
			String patientReference = condition1.getSubject().getReferenceSimple();
			assertTrue(patientReference.equalsIgnoreCase("patient/@"+getEntryId(createdPatientEntry)));
			//Get all conditions for this patient
			AtomFeed preexistingConditions = testClient.search(Condition.class, searchParameters);
			assertTrue(preexistingConditions.getEntryList().size() == 1);
			AtomEntry<Condition> preexistingConditionEntry = (AtomEntry<Condition>)preexistingConditions.getEntryList().get(0);
			assertTrue(preexistingConditionEntry.getResource().getCode().getCoding().get(0).getCodeSimple().equalsIgnoreCase("29530003"));
			assertNotNull(preexistingConditionEntry.getResource().getCode().getCoding().get(0).getSystemSimple().equalsIgnoreCase("http://snomed.info/id"));
			assertTrue(preexistingConditionEntry.getResource().getCode().getCoding().get(0).getDisplaySimple().equalsIgnoreCase("Fungal granuloma (disorder)"));
			//Add new condition
			AtomEntry<Condition> createdConditionEntry2 = testClient.create(Condition.class, condition2);
			preexistingConditions = testClient.search(Condition.class, searchParameters);
			assertTrue(preexistingConditions.getEntryList().size() == 2);
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
		coding.setCodeSimple(code);
		coding.setSystemSimple(system);
		coding.setDisplaySimple(displayNameSimple);
		return conditionCode;
	}
	
	@SuppressWarnings("unused")
  private Condition buildCondition(AtomEntry<Patient> patientEntry) {
		CodeableConcept conditionCode = createCodeableConcept("29530003", "http://snomed.info/id", "Fungal granuloma (disorder)");
		return buildCondition(patientEntry, conditionCode);
	}
	
	private Condition buildCondition(AtomEntry<Patient> patientEntry, CodeableConcept conditionCode) {
		Condition condition = null;
		try {
			condition = new Condition();
			ResourceReference patientReference = new ResourceReference();
			patientReference.setReferenceSimple("patient/@"+getEntryId(patientEntry));
			condition.setSubject(patientReference);
			condition.setCode(conditionCode);
			condition.setStatusSimple(ConditionStatus.confirmed);
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
		try {
			HumanName name = patient.addName();
			name.setTextSimple(fullName);
			name.addGivenSimple(givenName);
			name.addFamilySimple(familyName);
			DateTimeType birthday = new DateTimeType();
			birthday.setValue(new DateAndTime("2008-08-08"));
			patient.setBirthDate(birthday);
			patient.setGenderSimple(AdministrativeGender.F); // This is now a Simple code value
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		return patient;
	}
	
	private void loadPatientResource() {
		Patient testPatient = buildPatient();
		List<AtomCategory> tags = generateCategoryHeader();
		AtomEntry<OperationOutcome> result = testClient.create(Patient.class, testPatient, tags);
		testPatientId = getEntryId(result);
		testPatientVersion = getEntryVersion(result);
	}
	
	private List<AtomCategory> generateCategoryHeader() {
		List<AtomCategory> tags = new ArrayList<AtomCategory>();
		tags.add(new AtomCategory("http://client/scheme", "http://client/scheme/tag/123","tag 123"));
		tags.add(new AtomCategory("http://client/scheme", "http://client/scheme/tag/456","tag 456"));
		return tags;
	}
	
	private void unloadPatientResource() {
		testClient.delete(Patient.class, testPatientId);
	}
	
	private <T extends Resource> ResourceAddress.ResourceVersionedIdentifier getAtomEntryLink(AtomEntry<T> entry, String linkName) {
		return ResourceAddress.parseCreateLocation(entry.getLinks().get(linkName));
	}
	
	private <T extends Resource> ResourceAddress.ResourceVersionedIdentifier getAtomEntrySelfLink(AtomEntry<T> entry) {
		return getAtomEntryLink(entry, "self");
	}
	
	private <T extends Resource> String getEntryId(AtomEntry<T> entry) {
		return getAtomEntrySelfLink(entry).getId();
	}
	
	private <T extends Resource> String getEntryVersion(AtomEntry<T> entry) {
		return getAtomEntrySelfLink(entry).getVersion();
	}
	
	private <T extends Resource> String getResourceType(AtomEntry<T> entry) {
		return getAtomEntrySelfLink(entry).getResourceType();
	}
	
	private <T extends Resource> String getEntryPath(AtomEntry<T> entry) {
		return getAtomEntrySelfLink(entry).getResourcePath();
	}
	
	@SuppressWarnings("unused")
  private <T extends Resource> String getResourceId(AtomEntry<T> entry) {
		return getAtomEntrySelfLink(entry).getId();
	}
	
	private <T extends Resource> void printResourceToSystemOut(T resource, boolean isJson) {
		if(logResource) {
			System.out.println(new String(ClientUtils.getResourceAsByteArray(resource, true, isJson)));
		}
	}
	
	private void assertEqualDate(DateAndTime OriginalDate, DateAndTime modifiedDate) {
		assertEquals(modifiedDate.getYear(),OriginalDate.getYear());
		assertEquals(modifiedDate.getMonth(),OriginalDate.getMonth());
		assertEquals(modifiedDate.getDay(),OriginalDate.getDay());
	}
}
