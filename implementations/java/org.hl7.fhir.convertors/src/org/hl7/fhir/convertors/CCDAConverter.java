package org.hl7.fhir.convertors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.AdverseReaction;
import org.hl7.fhir.instance.model.AdverseReaction.AdverseReactionSymptomComponent;
import org.hl7.fhir.instance.model.AdverseReaction.ReactionSeverity;
import org.hl7.fhir.instance.model.AllergyIntolerance.Criticality;
import org.hl7.fhir.instance.model.AllergyIntolerance.Sensitivitystatus;
import org.hl7.fhir.instance.model.AllergyIntolerance.Sensitivitytype;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Comparison;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Observation;
import org.hl7.fhir.instance.model.Observation.ObservationRelatedComponent;
import org.hl7.fhir.instance.model.Observation.ObservationRelationshiptypes;
import org.hl7.fhir.instance.model.Observation.ObservationReliability;
import org.hl7.fhir.instance.model.Observation.ObservationStatus;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Composition.CompositionAttestationMode;
import org.hl7.fhir.instance.model.Composition.CompositionAttesterComponent;
import org.hl7.fhir.instance.model.Composition.SectionComponent;
import org.hl7.fhir.instance.model.AllergyIntolerance;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.Device;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.List_;
import org.hl7.fhir.instance.model.List_.ListEntryComponent;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Location;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Organization;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Practitioner;
import org.hl7.fhir.instance.model.Procedure;
import org.hl7.fhir.instance.model.Procedure.ProcedurePerformerComponent;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceFactory;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.ResourceType;
import org.hl7.fhir.instance.model.Substance;
import org.hl7.fhir.instance.model.Encounter;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.w3c.dom.Element;

/**
Advance Directives Section 42348-3 : 
Allergies, Adverse Reactions, Alerts Section 48765-2 :     List(AlleryIntolerance)        processAdverseReactionsSection     
Anesthesia Section 59774-0 : 
Assessment Section 51848-0 : 
Assessment and Plan Section 51487-2 : 
Chief Complaint Section 10154-3 : 
Chief Complaint and Reason for Visit Section 46239-0 : 
Complications 55109-3: 
DICOM Object Catalog Section - DCM 121181 : 
Discharge Diet Section 42344-2 : 
Encounters Section 46240-8: 
Family History Section 10157-6 : 
Findings Section 18782-3 : 
Functional Status Section 47420-5 : 
General Status Section 10210-3 : 
History of Past Illness Section 11348-0 : 
History of Present Illness Section 10164-2 : 
Hospital Admission Diagnosis Section 46241-6 : 
Hospital Consultations Section 18841-7 : 
Hospital Course Section 8648-8 : 
Hospital Discharge Diagnosis Section 11535-2 : 
Hospital Discharge Instructions Section : 
Hospital Discharge Medications Section (entries optional) 10183-2 : 
Hospital Discharge Physical Section 10184-0 : 
Hospital Discharge Studies Summary Section 11493-4 : 
Immunizations Section 11369-6 : 
Interventions Section 62387-6 : 
Medical Equipment Section 46264-8 : 
Medical (General) History Section 11329-0 : 
Medications Section 10160-0 : 
Medications Administered Section 29549-3 : 
Objective Section 61149-1 : 
Operative Note Fluid Section 10216-0 : 
Operative Note Surgical Procedure Section 10223-6 : 
Payers Section 48768-6 : 
Physical Exam Section 29545-1 : 
Plan of Care Section 18776-5 : 
Planned Procedure Section 59772-: 
Postoperative Diagnosis Section 10218-6 : 
Postprocedure Diagnosis Section 59769-0 : 
Preoperative Diagnosis Section 10219-4 : 
Problem Section 11450-4 : 
Procedure Description Section 29554-3: 
Procedure Disposition Section 59775-7 : 
Procedure Estimated Blood Loss Section 59770-8 : 
Procedure Findings Section 59776-5 : 
Procedure Implants Section 59771-6 : 
Procedure Indications Section 59768-2 : 
Procedure Specimens Taken Section 59773-2 : 
Procedures Section 47519-4 :                          List (Procedure)                       processProceduresSection
Reason for Referral Section 42349-1 : 
Reason for Visit Section 29299-5 : 
Results Section 30954-2 : 
Review of Systems Section 10187-3 : 
Social History Section 29762-2 :                      List (Observation)                     processSocialHistorySection
Subjective Section 61150-9: 
Surgical Drains Section 11537-8 : 
Vital Signs Section 8716-3 : 


MU Sections:
Allergies/Adverse Reactions
Problems
Encounters
Medications
Results
Vital Signs
Procedures
Immunizations
Reason for Referral
Hospital Discharge Instructions
Functional Status
Plan of Care
Hospital Discharge Medication
All of General Header

 * @author Grahame
 *
 */
public class CCDAConverter {

	public enum SocialHistoryType {
	  SocialHistory, Pregnancy, SmokingStatus, TobaccoUse

  }


	public enum ProcedureType {
	  Observation, Procedure, Act

  }


	private CDAUtilities cda;
	private Element doc; 
	private Convert convert;
	private AtomFeed feed;
	private Composition composition;
	private Map<String, Practitioner> practitionerCache = new HashMap<String, Practitioner>();
	private Integer refCounter = 0;
  private Map<String, Profile> profiles = new HashMap<String, Profile>(); // for the generator
	
	public AtomFeed convert(InputStream stream) throws Exception {

		cda = new CDAUtilities(stream);
		doc = cda.getElement();
		cda.checkTemplateId(doc, "2.16.840.1.113883.10.20.22.1.1");
		convert = new Convert(cda);

		// check it's a CDA/CCD
		feed = new AtomFeed();
		feed.setUpdated(DateAndTime.now());
		feed.setId(makeUUIDReference());
		
		// process the header
		makeDocument();
		composition.setSubject(Factory.makeResourceReference(makeSubject()));
		for (Element e : cda.getChildren(doc, "author"))
			composition.getAuthor().add(Factory.makeResourceReference(makeAuthor(e)));
		// todo: data enterer & informant goes in provenance
		composition.setCustodian(Factory.makeResourceReference(makeOrganization(
				 cda.getDescendent(doc, "custodian/assignedCustodian/representedCustodianOrganization"), "Custodian")));
		// todo: informationRecipient		
		for (Element e : cda.getChildren(doc, "legalAuthenticator"))
			composition.getAttester().add(makeAttester(e, CompositionAttestationMode.legal, "Legal Authenticator"));
		for (Element e : cda.getChildren(doc, "authenticator"))
			composition.getAttester().add(makeAttester(e, CompositionAttestationMode.professional, "Authenticator"));
		
		// process the contents
		// we do this by section - keep the original section order
		Element body =  cda.getDescendent(doc, "component/structuredBody");
		processComponentSections(composition.getSection(), body);
		return feed;
	}

	
	private String addResource(Resource r, String title, String id) throws Exception {
		if (r.getText() == null)
			r.setText(new Narrative());
		if (r.getText().getDiv() == null) {
			r.getText().setStatusSimple(NarrativeStatus.generated);
			new NarrativeGenerator("", null, null, null, null, profiles, null).generate(r);
		}
		AtomEntry e = new AtomEntry();
		e.setUpdated(DateAndTime.now());
		e.setResource(r);
		e.setTitle(title);
		e.setId(id);
		feed.getEntryList().add(e);
		return id;
	}

	private void makeDocument() throws Exception {
		composition = (Composition) ResourceFactory.createResource("Composition");
    addResource(composition, "Composition", makeUUIDReference());

		Element title = cda.getChild(doc, "title");
		if (title == null) {
			feed.setTitle("Clinical Composition (generated from CCDA Composition)");
		} else {
			feed.setTitle(title.getTextContent());
			composition.setTitleSimple(title.getTextContent());			
		}
		if (cda.getChild(doc, "setId") != null) {
 			feed.setId(convert.makeURIfromII(cda.getChild(doc, "id")));
			composition.setIdentifier(convert.makeIdentifierFromII(cda.getChild(doc, "setId")));
		} else
			composition.setIdentifier(convert.makeIdentifierFromII(cda.getChild(doc, "id"))); // well, we fall back to id
			
		composition.setDate(convert.makeDateTimeFromTS(cda.getChild(doc, "effectiveTime")));
		composition.setType(convert.makeCodeableConceptFromCD(cda.getChild(doc, "code")));
		composition.setConfidentiality(convert.makeCodingFromCV(cda.getChild(doc, "confidentialityCode")));
		if (cda.getChild(doc, "confidentialityCode") != null)
			composition.setLanguageSimple(cda.getChild(doc, "confidentialityCode").getAttribute("value")); // todo - fix streaming for this
		
		Element ee = cda.getChild(doc, "componentOf");
		if (ee != null)
			ee = cda.getChild(ee, "encompassingEncounter");
		if (ee != null) {
			Encounter visit = new Encounter();
			for (Element e : cda.getChildren(ee, "id"))
				visit.getIdentifier().add(convert.makeIdentifierFromII(e));
			visit.setHospitalization(new Encounter.EncounterHospitalizationComponent());
			visit.getHospitalization().setPeriod(convert.makePeriodFromIVL(cda.getChild(ee, "effectiveTime")));
			composition.setEvent(new Composition.CompositionEventComponent());
			composition.getEvent().getCode().add(convert.makeCodeableConceptFromCD(cda.getChild(ee, "code")));
			composition.getEvent().setPeriod(visit.getHospitalization().getPeriod());
			composition.getEvent().getDetail().add(Factory.makeResourceReference(addResource(visit, "Encounter", makeUUIDReference())));			
		}
		
		// main todo: fill out the narrative, but before we can do that, we have to convert everything else
	}

	private String makeSubject() throws Exception {
		Element rt = cda.getChild(doc, "recordTarget");
		Element pr = cda.getChild(rt, "patientRole");
		Element p = cda.getChild(pr, "patient");
		
		Patient pat = (Patient) ResourceFactory.createResource("Patient");
		for (Element e : cda.getChildren(pr, "id"))
			pat.getIdentifier().add(convert.makeIdentifierFromII(e));

		for (Element e : cda.getChildren(pr, "addr"))
			pat.getAddress().add(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(pr, "telecom"))
			pat.getTelecom().add(convert.makeContactFromTEL(e));
		for (Element e : cda.getChildren(p, "name"))
			pat.getName().add(convert.makeNameFromEN(e));
		pat.setGender(convert.makeCodeableConceptFromCD(cda.getChild(p, "administrativeGenderCode")));
		pat.setBirthDate(convert.makeDateTimeFromTS(cda.getChild(p, "birthTime")));
		pat.setMaritalStatus(convert.makeCodeableConceptFromCD(cda.getChild(p, "maritalStatusCode")));
		pat.getExtensions().add(Factory.newExtension(CcdaExtensions.NAME_RELIGION, convert.makeCodeableConceptFromCD(cda.getChild(p, "religiousAffiliationCode")), false));
		pat.getExtensions().add(Factory.newExtension(CcdaExtensions.NAME_RACE, convert.makeCodeableConceptFromCD(cda.getChild(p, "raceCode")), false));
		pat.getExtensions().add(Factory.newExtension(CcdaExtensions.NAME_ETHNICITY, convert.makeCodeableConceptFromCD(cda.getChild(p, "ethnicGroupCode")), false));
		pat.getExtensions().add(Factory.newExtension(CcdaExtensions.NAME_BIRTHPLACE, convert.makeAddressFromAD(cda.getChild(p, new String[] {"birthplace", "place", "addr"})), false));
		
		Patient.ContactComponent guardian = new Patient.ContactComponent();
		pat.getContact().add(guardian);
		guardian.getRelationship().add(Factory.newCodeableConcept("GUARD", "urn:oid:2.16.840.1.113883.5.110", "guardian"));
		Element g = cda.getChild(p, "guardian");
		for (Element e : cda.getChildren(g, "addr"))
			if (guardian.getAddress() == null)
				guardian.setAddress(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(g, "telecom"))
			guardian.getTelecom().add(convert.makeContactFromTEL(e));
		g = cda.getChild(g, "guardianPerson");
		for (Element e : cda.getChildren(g, "name"))
			if (guardian.getName() == null)
				guardian.setName(convert.makeNameFromEN(e));

	  Element l = cda.getChild(p, "languageCommunication");
	  CodeableConcept cc = new CodeableConcept();
	  Coding c = new Coding();
	  c.setCodeSimple(cda.getChild(l, "languageCode").getAttribute("code"));
	  cc.getCoding().add(c);
		pat.getCommunication().add(cc); 

		// todo: this got broken.... lang.setMode(convert.makeCodeableConceptFromCD(cda.getChild(l, "modeCode")));
		cc.getExtensions().add(Factory.newExtension(CcdaExtensions.NAME_LANG_PROF, convert.makeCodeableConceptFromCD(cda.getChild(l, "modeCode")), false));
		pat.getExtensions().add(Factory.newExtension(CcdaExtensions.NAME_RELIGION, convert.makeCodeableConceptFromCD(cda.getChild(p, "religiousAffiliationCode")), false));
		pat.setManagingOrganization(Factory.makeResourceReference(makeOrganization(cda.getChild(pr, "providerOrganization"), "Provider")));
		return addResource(pat, "Subject", makeUUIDReference());
	}


	private String makeOrganization(Element org, String name) throws Exception {
	  Organization o = new Organization();
		for (Element e : cda.getChildren(org, "id"))
			o.getIdentifier().add(convert.makeIdentifierFromII(e));
		for (Element e : cda.getChildren(org, "name"))
			o.setNameSimple(e.getTextContent());
		for (Element e : cda.getChildren(org, "addr"))
			o.getAddress().add(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(org, "telecom"))
			o.getTelecom().add(convert.makeContactFromTEL(e));

	  return addResource(o, name, makeUUIDReference());
  }

	private String makeAuthor(Element auth) throws Exception {
		Element aa = cda.getChild(auth, "assignedAuthor");
		Element ap = cda.getChild(aa, "assignedPerson");
		
		Practitioner  pr = (Practitioner) ResourceFactory.createResource("Practitioner");
		for (Element e : cda.getChildren(aa, "id"))
			pr.getIdentifier().add(convert.makeIdentifierFromII(e));
		for (Element e : cda.getChildren(aa, "addr"))
			if (pr.getAddress() == null)
				pr.setAddress(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(aa, "telecom"))
			pr.getTelecom().add(convert.makeContactFromTEL(e));
		for (Element e : cda.getChildren(ap, "name"))
			if (pr.getName() != null)
				pr.setName(convert.makeNameFromEN(e));

	  return addResource(pr, "Author", makeUUIDReference());
	}


	private String makeUUIDReference() {
	  return "urn:uuid:"+UUID.randomUUID().toString().toLowerCase();
  }


	private CompositionAttesterComponent makeAttester(Element a1, CompositionAttestationMode mode, String title) throws Exception {
		Practitioner  pr = (Practitioner) ResourceFactory.createResource("Practitioner");
		Element ass = cda.getChild(a1, "assignedEntity");
		for (Element e : cda.getChildren(ass, "id"))
			pr.getIdentifier().add(convert.makeIdentifierFromII(e));
		for (Element e : cda.getChildren(ass, "addr"))
			if (pr.getAddress() == null) // just take the first
				pr.setAddress(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(ass, "telecom"))
			pr.getTelecom().add(convert.makeContactFromTEL(e));
		Element ap = cda.getChild(ass, "assignedPerson");
		for (Element e : cda.getChildren(ap, "name"))
			if (pr.getName() == null) // just take the first
  			pr.setName(convert.makeNameFromEN(e));
		

		CompositionAttesterComponent att = new CompositionAttesterComponent();
		att.addModeSimple(mode);
		att.setTime(convert.makeDateTimeFromTS(cda.getChild(a1,"time")));
	  att.setParty(Factory.makeResourceReference(addResource(pr, title, makeUUIDReference())));
	  return att;
  }


	private void processComponentSections(List<SectionComponent> sections, Element container) throws Exception {
		for (Element c : cda.getChildren(container, "component")) {
			SectionComponent s = processSection(cda.getChild(c, "section"));
			if (s != null) 
				sections.add(s);
		}
	  
  }


	private SectionComponent processSection(Element section) throws Exception {
	  // this we do by templateId
		if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.6") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.6.1"))
			return processAdverseReactionsSection(section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.7") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.7.1"))
			return processProceduresSection(section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.17"))  
		  return processSocialHistorySection(section);
		else
			// todo: error? 
	  return null;
  }

	
	private SectionComponent processProceduresSection(Element section) throws Exception {
		List_ list = new List_();
		for (Element entry : cda.getChildren(section, "entry")) {
			Element procedure = cda.getlastChild(entry);
			
			if (cda.hasTemplateId(procedure, "2.16.840.1.113883.10.20.22.4.14")) {
		    processProcedure(list, procedure, ProcedureType.Procedure);
			} else if (cda.hasTemplateId(procedure, "2.16.840.1.113883.10.20.22.4.13")) {
				processProcedure(list, procedure, ProcedureType.Observation);
			} else if (cda.hasTemplateId(procedure, "2.16.840.1.113883.10.20.22.4.12")) {
				processProcedure(list, procedure, ProcedureType.Act);
			} else
				throw new Exception("Unhandled Section template ids: "+cda.showTemplateIds(procedure));
		}
		
		// todo: text
		SectionComponent s = new Composition.SectionComponent();
		s.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section,  "code")));
		// todo: check subject
		s.setContent(Factory.makeResourceReference(addResource(list, "Procedures", makeUUIDReference())));
		return s;
		
	}

	private void processProcedure(List_ list, Element procedure, ProcedureType type) throws Exception {


		switch (type) {
		case Procedure : 
			cda.checkTemplateId(procedure, "2.16.840.1.113883.10.20.22.4.14");
			break;		
		case Observation: 
			cda.checkTemplateId(procedure, "2.16.840.1.113883.10.20.22.4.13");
  		break;		
		case Act: 
		  cda.checkTemplateId(procedure, "2.16.840.1.113883.10.20.22.4.12");
		}
		checkNoNegationOrNullFlavor(procedure, "Procedure ("+type.toString()+")");
		  
		Procedure p = new Procedure();
		addItemToList(list, p);
		
		// moodCode is either INT or EVN. INT is not handled yet. INT is deprecated anyway
   if (procedure.getAttribute("moodCode").equals("INT"))
  		p.getModifierExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/procedure-planned", Factory.newBoolean(true), false));
		
		// SHALL contain at least one [1..*] id (CONF:7655).
		for (Element e : cda.getChildren(procedure, "id")) 
			p.getIdentifier().add(convert.makeIdentifierFromII(e));
		
		// SHALL contain exactly one [1..1] code (CONF:7656).
		// This code @code in a procedure activity SHOULD be selected from LOINC or SNOMED CT and MAY be selected from CPT-4, ICD9 Procedures, ICD10 Procedures
		p.setType(convert.makeCodeableConceptFromCD(cda.getChild(procedure, "code")));
		
		// SHALL contain exactly one [1..1] statusCode/@code, which SHALL be selected from ValueSet 2.16.840.1.113883.11.20.9.22 ProcedureAct
		// completed | active | aborted | cancelled - not in FHIR
		p.getModifierExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/procedure-status", Factory.newCode(cda.getStatus(procedure)), false));
		
		// SHOULD contain zero or one [0..1] effectiveTime (CONF:7662).
		p.setDate(convert.makePeriodFromIVL(cda.getChild(procedure, "effectiveTime")));
		
		// MAY contain zero or one [0..1] priorityCode/@code, which SHALL be selected from ValueSet 2.16.840.1.113883.1.11.16866 ActPriority DYNAMIC (CONF:7668)
		p.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/procedure-priority", convert.makeCodeableConceptFromCD(cda.getChild(procedure, "priorityCode")), false));
		
		// MAY contain zero or one [0..1] methodCode (CONF:7670).
		p.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/procedure-method", convert.makeCodeableConceptFromCD(cda.getChild(procedure, "methodCode")), false));

		if (type == ProcedureType.Observation) {
			// for Procedure-Observation:
			// 9.	SHALL contain exactly one [1..1] value (CONF:16846).
			// don't know what this is. It's not the actual result of the procedure (that goes in results "This section records ... procedure observations"), and there seems to be no value. The example as <value xsi:type="CD"/> which is not valid
			// so we ignore this for now
		}
		
		//  SHOULD contain zero or more [0..*] targetSiteCode/@code, which SHALL be selected from ValueSet 2.16.840.1.113883.3.88.12.3221.8.9 Body site DYNAMIC (CONF:7683).
		for (Element e : cda.getChildren(procedure, "targetSiteCode")) 
			p.getBodySite().add(convert.makeCodeableConceptFromCD(e));
		
		//  MAY contain zero or more [0..*] specimen (CONF:7697). 
		// todo: add these as extensions when specimens are done. 
		
		//  SHOULD contain zero or more [0..*] performer (CONF:7718) such that it
		for (Element e : cda.getChildren(procedure, "performer")) {
			ProcedurePerformerComponent pp = new ProcedurePerformerComponent();
			p.getPerformer().add(pp);
			pp.setPerson(makeReferenceToPractitionerForAssignedEntity(e, p));
		}		

		for (Element participant : cda.getChildren(procedure, "participant")) {
			Element participantRole = cda.getlastChild(participant);
			if (type == ProcedureType.Procedure && cda.hasTemplateId(participantRole, "2.16.840.1.113883.10.20.22.4.37")) {
		  	//   MAY contain zero or more [0..*] participant (CONF:7751) such that it  SHALL contain exactly one [1..1] @typeCode="DEV" Device
			  // implanted devices
				p.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/implanted-devices", Factory.makeResourceReference(processDevice(participantRole, p)), false));
			} else if (cda.hasTemplateId(participantRole, "2.16.840.1.113883.10.20.22.4.32")) {
			// MAY contain zero or more [0..*] participant (CONF:7765) such that it SHALL contain exactly one [1..1] Service Delivery Location (templateId:2.16.840.1.113883.10.20.22.4.32) (CONF:7767)
				p.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/location", Factory.makeResourceReference(processSDLocation(participantRole, p)), false));
			}
		}
		
		for (Element e : cda.getChildren(procedure, "entryRelationship")) {
			Element a /* act*/ = cda.getlastChild(e);
      if (a.getLocalName().equals("encounter")) {
    		// MAY contain zero or more [0..*] entryRelationship (CONF:7768) such that it SHALL contain exactly one encounter which SHALL contain exactly one [1..1] id (CONF:7773).
    		// todo - and process as a full encounter while we're at it
      } else if (cda.hasTemplateId(a, "2.16.840.1.113883.10.20.22.4.20")) {
    		//  MAY contain zero or one [0..1] entryRelationship (CONF:7775) such that it SHALL contain exactly one [1..1] Instructions (templateId:2.16.840.1.113883.10.20.22.4.20) (CONF:7778).
    		// had code for type, plus text for instructions
      	Extension n = Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/procedure-instructions", null, true);
      	n.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/procedure-instructions-type", convert.makeCodeableConceptFromCD(cda.getChild(a, "code")), false));
      	n.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/procedure-instructions-text", convert.makeStringFromED(cda.getChild(a, "text")), false));
				p.getExtensions().add(n);
      } else if (cda.hasTemplateId(a, "2.16.840.1.113883.10.20.22.4.19")) {
    		// MAY contain zero or more [0..*] entryRelationship (CONF:7779) such that it SHALL contain exactly one [1..1] Indication (templateId:2.16.840.1.113883.10.20.22.4.19) (CONF:7781).
      	processIndication(p.getIndication(), a);
      } else if (cda.hasTemplateId(cda.getlastChild(e), "2.16.840.1.113883.10.20.22.4.16")) {
    		//  MAY contain zero or one [0..1] entryRelationship (CONF:7886) such that it SHALL contain exactly one [1..1] Medication Activity (templateId:2.16.840.1.113883.10.20.22.4.16) (CONF:7888).
    		// todo
      }
		}
	}


	private String processSDLocation(Element participantRole, Resource r) throws Exception {
	  Location l = new Location();
	  l.setType(convert.makeCodeableConceptFromCD(cda.getChild(participantRole, "code")));
	  for (Element id : cda.getChildren(participantRole, "id")) {
	  	if (l.getIdentifier() == null) 
	  	  l.setIdentifier(convert.makeIdentifierFromII(id));
	  }
	  for (Element addr : cda.getChildren(participantRole, "addr")) {
	  	if (l.getAddress() == null) 
	  	  l.setAddress(convert.makeAddressFromAD(addr));
	  }
	  
	  for (Element telecom : cda.getChildren(participantRole, "telecom")) {
   	  l.getTelecom().add(convert.makeContactFromTEL(telecom));
	  }
	  
	  
		Element place = cda.getChild(participantRole, "playingDevice");
		if (cda.getChild(place, "name") != null)
    	l.setNameSimple(cda.getChild(place, "name").getTextContent());

  	String id = nextRef();
  	l.setXmlId(id);
  	r.getContained().add(l);
	  return "#"+id;
  }


	private String processDevice(Element participantRole, Resource r) throws Exception {
	  Device d = new Device();
	  for (Element id : cda.getChildren(participantRole, "id")) {
	  	// todo: check for UDIs, how? 
	  	d.getIdentifier().add(convert.makeIdentifierFromII(id));
	  }
		Element device = cda.getChild(participantRole, "playingDevice");
		// todo: if (cda.getChild(device, "code") != null)
  	d.setType(convert.makeCodeableConceptFromCD(cda.getChild(device, "code")));

  	// CCDA has an id - this is manufacturer? We just call it the name, but what to do about this?
		Element org = cda.getChild(participantRole, "scopingEntity");
  	d.setManufacturerSimple(convert.makeURIfromII(cda.getChild(org, "id")));
  	
  	String id = nextRef();
  	d.setXmlId(id);
  	r.getContained().add(d);
	  return "#"+id;
  }


	private void processIndication(List<CodeableConcept> l, Element obs) throws Exception {
	  Element v = cda.getChild(obs, "value");
	  if (v == null) {
	  	// have to find it by ID
	  	Element o = cda.getById(cda.getChild(obs, "id"), "value");
	  	if (o != null)      		
	  	  v = cda.getChild(obs, "value");
	  }
	  if (v != null)
	  	l.add(convert.makeCodeableConceptFromCD(v));
  }
	
	private ResourceReference makeReferenceToPractitionerForAssignedEntity(Element assignedEntity, Resource r) throws Exception {
		ResourceReference ref = null;
		// do we have this by id? 
		String uri = getIdForEntity(assignedEntity);
		Practitioner p = null;
		if (uri != null) {
			ref = Factory.makeResourceReference(uri);
			p = practitionerCache.get(uri);
		}
		if (p == null) {
			p = new Practitioner();
			if (uri == null) {
				// make a contained practitioner
				String n = nextRef();
				p.setXmlId(n);
				r.getContained().add(p);
				ref = Factory.makeResourceReference("#"+n);
			} else {
				// add this to feed
				ref = Factory.makeResourceReference(addResource(p, "Practitioner", uri));
			}
		}
		// ref and p are both sorted. now we fill out p as much as we can (remembering it might already be populated)
    addToCodeableList(p.getRole(), convert.makeCodeableConceptFromCD(cda.getChild(assignedEntity, "code")));
		for (Element e : cda.getChildren(assignedEntity, "id")) 
		  addToIdList(p.getIdentifier(), convert.makeIdentifierFromII(e));
		for (Element e : cda.getChildren(assignedEntity, "addr")) 
		  if (p.getAddress() == null) 
		    p.setAddress(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(assignedEntity, "telecom")) 
		  addToContactList(p.getTelecom(), convert.makeContactFromTEL(e));
		for (Element e : cda.getChildren(cda.getChild(assignedEntity, "assignedPerson"), "name")) 
		  if (p.getName() == null) 
		  	p.setName(convert.makeNameFromEN(e));
		// todo: 
		//	representedOrganization
		return ref;
  }


	private void addToContactList(List<Contact> list, Contact c) throws Exception {
		for (Contact item : list) {
			if (Comparison.matches(item, c, null)) 
				Comparison.merge(item, c);
		}
		list.add(c);
  }


	private void addToIdList(List<Identifier> list, Identifier id) throws Exception {
		for (Identifier item : list) {
			if (Comparison.matches(item, id, null)) 
				Comparison.merge(item, id);
		}
		list.add(id);
  }


	private void addToCodeableList(List<CodeableConcept> list, CodeableConcept code) throws Exception {
		for (CodeableConcept item : list) {
			if (Comparison.matches(item, code, null)) 
				Comparison.merge(item, code);
		}
		list.add(code);
  }


	private String getIdForEntity(Element assignedEntity) throws Exception {
	  Element id = cda.getChild(assignedEntity, "id"); // for now, just grab the first
	  if (id == null)
	  	return null;
	  if (id.getAttribute("extension") == null) {
	  	if (convert.isGuid(id.getAttribute("root")))
	  		return "urn:uuid:"+id.getAttribute("root");
	  	else 
	  		return "urn:oid:"+id.getAttribute("root");
	  } else 
	  	return "ii:"+id.getAttribute("root")+"::"+id.getAttribute("extension");
  }


	private SectionComponent processAdverseReactionsSection(Element section) throws Exception {
		List_ list = new List_();
		for (Element entry : cda.getChildren(section, "entry")) {
			Element concern = cda.getChild(entry, "act");
			if (cda.hasTemplateId(concern, "2.16.840.1.113883.10.20.22.4.30")) {
		    processAllergyProblemAct(list, concern);
			} else
				throw new Exception("Unhandled Section template ids: "+cda.showTemplateIds(concern));
		}
		
		
		// todo: text
		SectionComponent s = new Composition.SectionComponent();
		s.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section,  "code")));
		// todo: check subject
		s.setContent(Factory.makeResourceReference(addResource(list, "Allergies, Adverse Reactions, Alerts", makeUUIDReference())));
		return s;
  }


	private void processAllergyProblemAct(List_ list, Element concern) throws Exception {
		cda.checkTemplateId(concern, "2.16.840.1.113883.10.20.22.4.30");  
	  // Allergy Problem Act - this is a concern - we treat the concern as information about it's place in the list
		checkNoNegationOrNullFlavor(concern, "Allergy Problem Act");

		// SHALL contain at least one [1..*] entryRelationship (CONF:7509) such that it
		// SHALL contain exactly one [1..1] Allergy - intolerance Observation
		for (Element entry : cda.getChildren(concern, "entryRelationship")) {
			Element obs = cda.getChild(entry, "observation");
  		cda.checkTemplateId(obs, "2.16.840.1.113883.10.20.22.4.7");
  		checkNoNegationOrNullFlavor(obs, "Allergy - intolerance Observation");
  		
			AllergyIntolerance ai = new AllergyIntolerance();			
			ListEntryComponent item = addItemToList(list, ai);
			
			// this first section comes from the concern, and is processed once for each observation in the concern group
  		// SHALL contain at least one [1..*] id (CONF:7472).
			for (Element e : cda.getChildren(concern, "id")) 
				item.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/list-id", convert.makeIdentifierFromII(e), false));

      // SHALL contain exactly one [1..1] statusCode, which SHALL be selected from ValueSet 2.16.840.1.113883.3.88.12.80.68 HITSPProblemStatus DYNAMIC (CONF:7485)
		  // the status code is about the concern (e.g. the entry in the list)
			// possible values: active, suspended, aborted, completed, with an effective time 
			String s = cda.getStatus(concern);
			item.getFlag().add(Factory.newCodeableConcept(s, "http://hl7.org/fhir/v3/ActStatus", s));
			if (s.equals("aborted")) // only on this condition?
				item.setDeletedSimple(true);
			
			// SHALL contain exactly one [1..1] effectiveTime (CONF:7498)
			Period p = convert.makePeriodFromIVL(cda.getChild(concern, "effectiveTime"));
			item.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/list-period", p,  false));
			if (p.getEnd() != null)
				item.setDate(p.getEnd());
			else
				item.setDate(p.getStart());
						
			//ok, now process the actual observation
			// SHALL contain at least one [1..*] id (CONF:7382)
			for (Element e : cda.getChildren(obs, "id"))
				ai.getIdentifier().add(convert.makeIdentifierFromII(e));
			
			
			// SHALL contain exactly one [1..1] effectiveTime (CONF:7387)
			ai.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/allergyintolerance-period", convert.makePeriodFromIVL(cda.getChild(obs, "effectiveTime")),  false));
			
			// SHALL contain exactly one [1..1] value with @xsi:type="CD" (CONF:7390)
			CodeableConcept type = convert.makeCodeableConceptFromCD(cda.getChild(obs, "value"));
			// This value SHALL contain @code, which SHALL be selected from ValueSet 2.16.840.1.113883.3.88.12.3221.6.2 Allergy/Adverse Event Type
			String ss = type.getCoding().get(0).getCode().getValue();
			if (ss.equals("416098002") || ss.equals("414285001"))
				ai.setSensitivityTypeSimple(Sensitivitytype.allergy);
			else if (ss.equals("59037007") || ss.equals("235719002"))
				ai.setSensitivityTypeSimple(Sensitivitytype.intolerance);
			else
				ai.setSensitivityTypeSimple(Sensitivitytype.unknown);
			ai.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/allergy-category", type, false));
			
			// SHOULD contain zero or one [0..1] participant (CONF:7402) such that it
			Substance subst = new Substance();
			String n = nextRef();
			subst.setXmlId(n);
		  ai.getContained().add(subst);
			ai.setSubstance(Factory.makeResourceReference("#"+n));
			// ......This playingEntity SHALL contain exactly one [1..1] code			
			subst.setType(convert.makeCodeableConceptFromCD(cda.getDescendent(obs, "participant/participantRole/playingEntity/code"))); 

			//  MAY contain zero or one [0..1] entryRelationship (CONF:7440) such that it SHALL contain exactly one [1..1]  Alert Status Observation
			//  SHOULD contain zero or more [0..*] entryRelationship (CONF:7447) such that it SHALL contain exactly one [1..1] Reaction Observation (templateId:2.16.840.1.113883.10.20.22.4.9) (CONF:7450).
		  for (Element e : cda.getChildren(obs,  "entryRelationship")) {
		  	Element child = cda.getChild(e, "observation");
		  	if (cda.hasTemplateId(child, "2.16.840.1.113883.10.20.22.4.28") && ai.getStatus() == null) {
		  		// SHALL contain exactly one [1..1] value with @xsi:type="CE", where the @code SHALL be selected from ValueSet Problem Status Value Set 2.16.840.1.113883.3.88.12.80.68 DYNAMIC (CONF:7322).
		  		// 55561003  SNOMED CT  Active
		  		// 73425007  SNOMED CT  Inactive
		  		// 413322009  SNOMED CT  Resolved
		  		String sc = cda.getChild(child, "value").getAttribute("code");
		  		if (sc.equals("55561003"))
		  			ai.setStatusSimple(Sensitivitystatus.confirmed);
		  		else
		  			ai.setStatusSimple(Sensitivitystatus.resolved);
		  	} else if (cda.hasTemplateId(child, "2.16.840.1.113883.10.20.22.4.9")) {
		  		AdverseReaction reaction = processAdverseReactionObservation(child);
		  		n = nextRef();
					reaction.setXmlId(n);
				  ai.getContained().add(subst);
					ai.setSubstance(Factory.makeResourceReference("#"+n));
		  	}
		  }
			
			//  SHOULD contain zero or one [0..1] entryRelationship (CONF:9961) such that it SHALL contain exactly one [1..1] Severity Observation (templateId:2.16.840.1.113883.10.20.22.4.8) (CONF:9963).
			ai.setCriticalitySimple(readCriticality(cda.getSeverity(obs)));
		}
  }


	// this is going to be a contained resource, so we aren't going to generate any narrative
	private AdverseReaction processAdverseReactionObservation(Element reaction) throws Exception {
		checkNoNegationOrNullFlavor(reaction, "Adverse Reaction Observation");
		
		// This clinical statement represents an undesired symptom, finding, etc., due to an administered or exposed substance. A reaction can be defined with respect to its	severity, and can have been treated by one or more interventions.
		AdverseReaction ar = new AdverseReaction();
		
		// SHALL contain exactly one [1..1] id (CONF:7329).
		for (Element e : cda.getChildren(reaction, "id")) 
			ar.getIdentifier().add(convert.makeIdentifierFromII(e));

		// SHALL contain exactly one [1..1] code (CONF:7327). The value set for this code element has not been specified.
		// todo: what the heck is this? 
		
		// SHOULD contain zero or one [0..1] text (CONF:7330).
		// todo: so what is this? how can we know whether to ignore it? 
		
		// 8.  SHOULD contain zero or one [0..1] effectiveTime (CONF:7332). 
		//  	a.  This effectiveTime SHOULD contain zero or one [0..1] low (CONF:7333). 
		//  	b.  This effectiveTime SHOULD contain zero or one [0..1] high (CONF:7334).
		// !this is a problem because FHIR just has a date, not a period.
		ar.setDate(convert.makeDateTimeFromIVL(cda.getChild(reaction, "effectiveTime")));

		// SHALL contain exactly one [1..1] value with @xsi:type="CD", where the @code SHALL be selected from ValueSet 2.16.840.1.113883.3.88.12.3221.7.4 Problem	DYNAMIC (CONF:7335).
		AdverseReactionSymptomComponent symptom = new AdverseReactionSymptomComponent();
		ar.getSymptom().add(symptom);
		symptom.setCode(convert.makeCodeableConceptFromCD(cda.getChild(reaction, "value")));
		
	  // SHOULD contain zero or one [0..1] entryRelationship (CONF:7580) such that it SHALL contain exactly one [1..1] Severity Observation  (templateId:2.16.840.1.113883.10.20.22.4.8) (CONF:7582).
		symptom.setSeveritySimple(readSeverity(cda.getSeverity(reaction)));

		// MAY contain zero or more [0..*] entryRelationship (CONF:7337) such that it SHALL contain exactly one [1..1] Procedure Activity Procedure (templateId:2.16.840.1.113883.10.20.22.4.14) (CONF:7339). 
		// i.  This procedure activity is intended to contain information about procedures that were performed in response to an allergy reaction
		// todo: process these into an procedure and add as an extension
		
		// MAY contain zero or more [0..*] entryRelationship (CONF:7340) such that it SHALL contain exactly one [1..1] Medication Activity (templateId:2.16.840.1.113883.10.20.22.4.16) (CONF:7342). 
		// i.  This medication activity is intended to contain information about medications that were administered in response to an allergy reaction. (CONF:7584).
		// todo: process these into an medication statement and add as an extension
		
	  return ar;
  }


	private SectionComponent processSocialHistorySection(Element section) throws Exception {
		List_ list = new List_();
		for (Element entry : cda.getChildren(section, "entry")) {
			Element observation = cda.getlastChild(entry);
			
			if (cda.hasTemplateId(observation, "2.16.840.1.113883.10.20.22.4.38")) {
		    processSocialObservation(list, observation, SocialHistoryType.SocialHistory);
			} else if (cda.hasTemplateId(observation, "2.16.840.1.113883.10.20.15.3.8")) {
				processSocialObservation(list, observation, SocialHistoryType.Pregnancy);
			} else if (cda.hasTemplateId(observation, "2.16.840.1.113883.10.20.22.4.78")) {
				processSocialObservation(list, observation, SocialHistoryType.SmokingStatus);
			} else if (cda.hasTemplateId(observation, "2.16.840.1.113883.10.20.22.4.85")) {
				processSocialObservation(list, observation, SocialHistoryType.TobaccoUse);
			} else
				throw new Exception("Unhandled Section template ids: "+cda.showTemplateIds(observation));
		}
		
		// todo: text
		SectionComponent s = new Composition.SectionComponent();
		s.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section,  "code")));
		// todo: check subject
		s.setContent(Factory.makeResourceReference(addResource(list, "Procedures", makeUUIDReference())));
		return s;
		
	}

	

	private void processSocialObservation(List_ list, Element so,   SocialHistoryType type) throws Exception {
		Observation obs = new Observation();
		addItemToList(list, obs);
		
		switch (type) {
		case SocialHistory : 
			cda.checkTemplateId(so, "2.16.840.1.113883.10.20.22.4.38");
			// SHALL contain exactly one [1..1] code (CONF:8558/).
			obs.setName(convert.makeCodeableConceptFromCD(cda.getChild(so, "code")));
			break;		
		case Pregnancy: 
			cda.checkTemplateId(so, "2.16.840.1.113883.10.20.15.3.8");
			// SHALL contain exactly one [1..1] code (CONF:8558/), which SHALL be an assertion
			obs.setName(Factory.newCodeableConcept("11449-6", "http://loinc.org", "Pregnancy Status"));
  		break;		
		case SmokingStatus: 
		  cda.checkTemplateId(so, "2.16.840.1.113883.10.20.22.4.78");
			// SHALL contain exactly one [1..1] code (CONF:8558/), which SHALL be an assertion
			obs.setName(Factory.newCodeableConcept("72166-2", "http://loinc.org", "Tobacco Smoking Status"));
  		break;		
		case TobaccoUse: 
		  cda.checkTemplateId(so, "2.16.840.1.113883.10.20.22.4.12");
			// SHALL contain exactly one [1..1] code (CONF:8558/), which SHALL be an assertion
			obs.setName(Factory.newCodeableConcept("11367-0", "http://loinc.org", "History of Tobacco Use"));
		}
		  
		// SHALL contain at least one [1..*] id (8551).
		for (Element e : cda.getChildren(so, "id"))
			if (obs.getIdentifier() == null) // only one in FHIR
			  obs.setIdentifier(convert.makeIdentifierFromII(e));
			else 
			  obs.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/additional-id", convert.makeIdentifierFromII(e), false));

		
		// SHALL contain exactly one [1..1] statusCode (CONF:8553/455/14809).
		// a.	This statusCode SHALL contain exactly one [1..1] @code="completed" Completed (CodeSystem: ActStatus 2.16.840.1.113883.5.14 STATIC) (CONF:19117).
		obs.setStatusSimple(ObservationStatus.final_);

		// SHOULD contain zero or one [0..1] effectiveTime (CONF:2018/14814).
		// for smoking status/tobacco: low only. in R2, this is just value. So we treat low only as just a value
		Element et = cda.getChild(so, "effectiveTime");
		if (et != null) {
			if (cda.getChild(et, "low") != null)
				obs.setApplies(convert.makeDateTimeFromTS(cda.getChild(et, "low")));
			else
				obs.setApplies(convert.makeDateTimeFromIVL(et));
		}
		
		//	SHOULD contain zero or one [0..1] value (CONF:8559).
		// a.	Observation/value can be any data type. 
		for (Element e : cda.getChildren(so, "value"))
			if (obs.getValue() == null) { // only one in FHIR
				// special case for pregnancy:
				if (type == SocialHistoryType.Pregnancy && "true".equals(e.getAttribute("negationInd"))) {
					obs.setValue(Factory.newCodeableConcept("60001007", "http://snomed.info/sct", "Not pregnant"));
				} else {
					// negationInd is not described. but it might well be used. For now, we blow up 
					checkNoNegation(so, "Social Observation ("+type.toString()+")");

					if (so.hasAttribute("nullFlavor"))
						obs.setValue(convert.makeCodeableConceptFromNullFlavor(so.getAttribute("nullFlavor")));
					else if (e.hasAttribute("nullFlavor") && !"OTH".equals(e.getAttribute("nullFlavor")))
						obs.setValue(convert.makeCodeableConceptFromNullFlavor(e.getAttribute("nullFlavor")));
					else
						obs.setValue(convert.makeTypeFromANY(e));
				}
			} else
				throw new Exception("too many values on Social Observation");

		if (type == SocialHistoryType.Pregnancy) {
			for (Element e : cda.getChildren(so, "entyRelationship")) {
				Element dd = cda.getChild(e, "observation");
				checkNoNegationOrNullFlavor(dd, "Estimated Date of Delivery");
				//  MAY contain zero or one [0..1] entryRelationship (CONF:458) such that it
				//    SHALL contain exactly one [1..1] @typeCode="REFR" Refers to (CodeSystem: HL7ActRelationshipType 2.16.840.1.113883.5.1002 STATIC) (CONF:459).
				//   	SHALL contain exactly one [1..1] Estimated Date of Delivery (templateId:2.16.840.1.113883.10.20.15.3.1) (CONF:15584).
				Observation co = new Observation();
				String id = nextRef();
				co.setXmlId(id);
				obs.getContained().add(co);
				ObservationRelatedComponent or = new ObservationRelatedComponent();
				obs.getRelated().add(or);
				or.setTypeSimple(ObservationRelationshiptypes.hascomponent);
				or.setTarget(Factory.makeResourceReference("#"+id));
				co.setName(Factory.newCodeableConcept("11778-8", "http://loinc.org", "Delivery date Estimated"));
				co.setValue(convert.makeDateTimeFromTS(cda.getChild(dd, "value"))); // not legal, see gForge http://gforge.hl7.org/gf/project/fhir/tracker/?action=TrackerItemEdit&tracker_item_id=3125&start=0 
			}
		}
		
		// lastly, we assume that these are to be marked as reliable observations:
		obs.setReliabilitySimple(ObservationReliability.ok);
  }


	private void checkNoNegation(Element act, String path) throws Exception {
	  if ("true".equals(act.getAttribute("negationInd")))
	  	throw new Exception("The conversion program cannot accept a negationInd at the location "+path);
  }

	private void checkNoNegationOrNullFlavor(Element act, String path) throws Exception {
	  if (act.hasAttribute("nullFlavor"))
	  	throw new Exception("The conversion program cannot accept a nullFlavor at the location "+path);
	  if ("true".equals(act.getAttribute("negationInd")))
	  	throw new Exception("The conversion program cannot accept a negationInd at the location "+path);
  }


	private ListEntryComponent addItemToList(List_ list, Resource ai)
      throws Exception {
	  list.getContained().add(ai);
	  String n = nextRef();
	  ai.setXmlId(n);
	  ListEntryComponent item = new List_.ListEntryComponent();
	  list.getEntry().add(item);
	  item.setItem(Factory.makeResourceReference("#"+n));
	  return item;
  }


	private String nextRef() {
	  refCounter++;
	  String n = refCounter.toString();
	  return n;
  }

	private Criticality readCriticality(String severity) {
		if ("255604002".equals(severity)) // Mild 
			return Criticality.low; 
		if ("371923003".equals(severity)) //  Mild to moderate 
			return Criticality.low; 
		if ("6736007".equals(severity)) // Moderate
			return Criticality.medium; 
		if ("371924009".equals(severity)) // Moderate to severe
			return Criticality.medium; 
		if ("24484000".equals(severity)) // Severe
			return Criticality.high; 
		if ("399166001".equals(severity)) // Fatal
			return Criticality.fatal; 
	  return null;
  }


	private ReactionSeverity readSeverity(String severity) {
		if ("255604002".equals(severity)) // Mild 
			return ReactionSeverity.minor; 
		if ("371923003".equals(severity)) //  Mild to moderate 
			return ReactionSeverity.moderate; 
		if ("6736007".equals(severity)) // Moderate
			return ReactionSeverity.moderate; 
		if ("371924009".equals(severity)) // Moderate to severe
			return ReactionSeverity.serious; 
		if ("24484000".equals(severity)) // Severe
			return ReactionSeverity.severe; 
		if ("399166001".equals(severity)) // Fatal
			return ReactionSeverity.severe; 
	  return null;
  }


	public void initialize(String srcPath) throws FileNotFoundException, Exception {
		AtomFeed feed = new XmlParser().parseGeneral(new FileInputStream(srcPath+"profiles-resources.xml")).getFeed();
		for (AtomEntry<? extends Resource> ae : feed.getEntryList()) {
			if (ae.getResource().getResourceType() == ResourceType.Profile) {
				Profile p = (Profile) ae.getResource();
				profiles.put(p.getStructure().get(0).getTypeSimple(), p);
			}
	  }
	  
  }

}
