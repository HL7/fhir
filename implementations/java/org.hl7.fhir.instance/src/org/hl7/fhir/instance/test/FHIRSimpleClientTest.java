package org.hl7.fhir.instance.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
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
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.Condition.ConditionStatus;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.DateTime;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.Patient;
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
	
	private FHIRClient testClient;
	private String testPatientId;
	private String testPatientVersion;
	private boolean logResource = true;
	
	
	private static void configureForFurore() {
		connectUrl = "http://spark.furore.com/fhir/";
		userAgent = "Furore Spark";
	}
	
	private static void configureForHealthIntersection() {
		connectUrl = "http://hl7connect.healthintersections.com.au/svc/fhir/";
		userAgent = "HL7Connect";
	}
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		configureForFurore();
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
	
	public void loadPatientResource() {
		Patient testPatient = buildPatient();
		AtomEntry<Patient> result = testClient.create(Patient.class, testPatient);
		testPatientId = getEntryId(result);
		testPatientVersion = getEntryVersion(result);
	}
	
	public void unloadPatientResource() {
		testClient.delete(Patient.class, testPatientId);
	}

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
			printResourceToSystemOut(stmt, false);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRead() {
		loadPatientResource();
		AtomEntry<Patient> fetchedPatient = testClient.read(Patient.class, testPatientId);
		assertEquals("2008-08-08", fetchedPatient.getResource().getBirthDate().getValue());
		unloadPatientResource();
	}

	@Test
	public void testVread() {
		try {
			loadPatientResource();
			System.out.println(testPatientVersion);
			AtomEntry<Patient> fetchedPatient = testClient.vread(Patient.class, testPatientId, testPatientVersion);
			assertEquals("2008-08-08", fetchedPatient.getResource().getBirthDate().getValue());
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
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testValidate() {
		try {
			loadPatientResource();
			Patient patient = testClient.read(Patient.class, testPatientId).getResource();
			DateTime modifiedBirthday = new DateTime();
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
	public void testCreate() {
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
	public void testCreateWithErrors() {
		try {
			AdverseReaction adverseReaction = new AdverseReaction();
			adverseReaction.setDateSimple(new DateAndTime("2013-01-10"));
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
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testDelete() {
		Patient patientRequest = buildPatient();
		AtomEntry<Patient> result = testClient.create(Patient.class, patientRequest);
		boolean success = testClient.delete(Patient.class, getEntryId(result));
		assertTrue(success);
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
		Calendar testDate = GregorianCalendar.getInstance();
		testDate.add(Calendar.HOUR_OF_DAY, -1);
		AtomEntry<Patient> createdEntry = testClient.create(Patient.class, buildPatient());
		testClient.update(Patient.class, (Patient)createdEntry.getResource(), getEntryId(createdEntry));
		AtomFeed feed = testClient.history(testDate, Patient.class);
		assertNotNull(feed);
		assertTrue(feed.getEntryList().size() >= 1);
		testClient.delete(Patient.class, getEntryId(createdEntry));
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
	public void testGetHistoryForResourceWithIdSinceCalendarDate() {
		Calendar testDate = GregorianCalendar.getInstance();
		testDate.add(Calendar.HOUR_OF_DAY, -1);
		AtomEntry<Patient> entry = testClient.create(Patient.class, buildPatient());
		testClient.update(Patient.class, (Patient)entry.getResource(), getEntryId(entry));
		testClient.update(Patient.class, (Patient)entry.getResource(), getEntryId(entry));
		AtomFeed feed = testClient.history(testDate, Patient.class, getEntryId(entry));
		assertNotNull(feed);
		assertEquals(3, feed.getEntryList().size());
		testClient.delete(Patient.class, getEntryId(entry));
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
	public void testTransactionSuccess() {
		try {
			Patient patient = buildPatient();
			AtomEntry<Patient> createdPatientEntry = testClient.create(Patient.class, patient);
			createdPatientEntry.getResource().setBirthDateSimple(new DateAndTime("1966-01-10"));
			ResourceReference patientReference = new ResourceReference();
			patientReference.setReferenceSimple(createdPatientEntry.getLinks().get("self"));
			AdverseReaction adverseReaction = new AdverseReaction();
			adverseReaction.setSubject(patientReference);
			adverseReaction.setDateSimple(new DateAndTime("2013-01-10"));
			adverseReaction.setDidNotOccurFlagSimple(false);
			AtomEntry<AdverseReaction> adverseReactionEntry = testClient.create(AdverseReaction.class, adverseReaction);
			AtomFeed batchFeed = new AtomFeed();
			batchFeed.getEntryList().add(createdPatientEntry);
			batchFeed.getEntryList().add(adverseReactionEntry);
			System.out.println(new String(ClientUtils.getFeedAsByteArray(batchFeed, false, false)));
			AtomFeed responseFeed = testClient.transaction(batchFeed);
			assertNotNull(responseFeed);
			assert(responseFeed.getEntryList().get(0).getResource() instanceof Patient);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testTransactionError() {
		try {
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
			testClient.delete(Patient.class, getEntryId(createdPatientEntry));
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSearchPatientByGivenName() {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			String firstName = "Jsuis_" +  + System.currentTimeMillis();
			String lastName = "Malade";
			String fullName = firstName + " " + lastName;
			searchMap.put("given", firstName);
			AtomEntry<Patient> createdPatientEntry = testClient.create(Patient.class, buildPatient(fullName, firstName, lastName));
			Patient createdPatient = createdPatientEntry.getResource();
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
	public void testRetrievePatientConditionList() {
		try {
			AtomEntry<Patient> patient = testClient.create(Patient.class, buildPatient());
			AtomEntry<Condition> condition = testClient.create(Condition.class, buildCondition(patient));
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
			AtomEntry<Patient> createdPatientEntry = testClient.create(Patient.class, patient);
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
			AtomEntry<Patient> createdPatientEntry = testClient.create(Patient.class, patient);
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
			AtomEntry<Condition> createdConditionEntry1 = testClient.create(Condition.class, condition1);
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

	private CodeableConcept createCodeableConcept(String code, String system, String displayNameSimple) {
		CodeableConcept conditionCode = new CodeableConcept();
		Coding coding = conditionCode.addCoding();
		coding.setCodeSimple(code);
		coding.setSystemSimple(system);
		coding.setDisplaySimple(displayNameSimple);
		return conditionCode;
	}
	
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
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		return patient;
	}
	
	private <T extends Resource> String getEntryId(AtomEntry<T> entry) {
		ResourceAddress.ResourceVersionedIdentifier identifier = ResourceAddress.parseCreateLocation(entry.getLinks().get("self"));
		return identifier.getId();
	}
	
	private <T extends Resource> String getEntryVersion(AtomEntry<T> entry) {
		ResourceAddress.ResourceVersionedIdentifier identifier = ResourceAddress.parseCreateLocation(entry.getLinks().get("self"));
		return identifier.getVersion();
	}
	
	private <T extends Resource> String getEntryPath(AtomEntry<T> entry) {
		ResourceAddress.ResourceVersionedIdentifier identifier = ResourceAddress.parseCreateLocation(entry.getLinks().get("self"));
		return identifier.getResourcePath();
	}
	
	private <T extends Resource> String getResourceId(AtomEntry<T> entry) {
		ResourceAddress.ResourceVersionedIdentifier identifier = ResourceAddress.parseCreateLocation(entry.getLinks().get("self"));
		return identifier.getId();
	}
	
	private <T extends Resource> void printResourceToSystemOut(T resource, boolean isJson) {
		if(logResource) {
			System.out.println(new String(ClientUtils.getResourceAsByteArray(resource, true, isJson)));
		}
	}
}
