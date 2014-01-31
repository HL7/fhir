package org.hl7.fhir.convertors;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.hl7.fhir.instance.model.AllergyIntolerance.Criticality;
import org.hl7.fhir.instance.model.AllergyIntolerance.Sensitivitystatus;
import org.hl7.fhir.instance.model.AllergyIntolerance.Sensitivitytype;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Composition;
import org.hl7.fhir.instance.model.Composition.CompositionAttestationMode;
import org.hl7.fhir.instance.model.Composition.CompositionAttesterComponent;
import org.hl7.fhir.instance.model.Composition.SectionComponent;
import org.hl7.fhir.instance.model.AllergyIntolerance;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.List_;
import org.hl7.fhir.instance.model.List_.ListEntryComponent;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Organization;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Practitioner;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceFactory;
import org.hl7.fhir.instance.model.Substance;
import org.hl7.fhir.instance.model.Encounter;
import org.w3c.dom.Element;

public class CCDAConverter {

	private CDAUtilities cda;
	private Element doc; 
	private Convert convert;
	private AtomFeed feed;
	private Composition composition;
	
	public AtomFeed convert(InputStream stream) throws Exception {

		cda = new CDAUtilities(stream);
		doc = cda.getElement();
		cda.checkTemplateId(doc, "2.16.840.1.113883.10.20.22.1.1");
		convert = new Convert(cda);

		// check it's a CDA/CCD
		feed = new AtomFeed();
		feed.setUpdated(DateAndTime.now());
		feed.setId(UUID.randomUUID().toString());
		
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

	
	private String addResource(Resource r, String title, String id) {
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
    addResource(composition, "Composition", UUID.randomUUID().toString());

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
			composition.getEvent().getDetail().add(Factory.makeResourceReference(addResource(visit, "Encounter", UUID.randomUUID().toString())));			
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
		return addResource(pat, "Subject", UUID.randomUUID().toString());
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

	  return addResource(o, name, UUID.randomUUID().toString());
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

	  return addResource(pr, "Author", UUID.randomUUID().toString());
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
	  att.setParty(Factory.makeResourceReference(addResource(pr, title, UUID.randomUUID().toString())));
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
		if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.6"))
			return processAdverseReactionsSection(section);
	  return null;
  }


	private SectionComponent processAdverseReactionsSection(Element section) throws Exception {
		List_ list = new List_();
		Integer i = 0;
		for (Element entry : cda.getChildren(section, "entry")) {
			i++;
			Element concern = cda.getChild(entry, "act");
			cda.checkTemplateId(concern, "2.16.840.1.113883.10.20.22.4.30");
			AllergyIntolerance ai = new AllergyIntolerance();
			list.getContained().add(ai); 
			ai.setXmlId("a"+i.toString());
			ListEntryComponent item = new List_.ListEntryComponent();
			list.getEntry().add(item);
			item.setItem(Factory.makeResourceReference("#a"+i.toString()));
			for (Element e : cda.getChildren(concern, "id"))
				ai.getIdentifier().add(convert.makeIdentifierFromII(e));
			String s = cda.getStatus(concern);
			if ("active".equals(s))
  			ai.setStatusSimple(Sensitivitystatus.confirmed);
			if ("suspended".equals(s)) {
  			ai.setStatusSimple(Sensitivitystatus.confirmed);
  			Boolean b = new Boolean();
  			b.setValue(true);
  			ai.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/allergy-suppressed", b, false));
			}
			if ("aborted".equals(s))
  			ai.setStatusSimple(Sensitivitystatus.refuted);
			if ("completed".equals(s))
  			ai.setStatusSimple(Sensitivitystatus.resolved);
			ai.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/allergy-period", 
					convert.makePeriodFromIVL(cda.getChild(concern, "effectiveTime")), false));
			Element obs = cda.getChild(cda.getChild(concern, "entryRelationship"), "observation");
			cda.checkTemplateId(obs, "2.16.840.1.113883.10.20.22.4.7");
			CodeableConcept type = convert.makeCodeableConceptFromCD(cda.getChild(obs, "value"));
			String ss = type.getCoding().get(0).getCode().getValue();
			if (ss.equals("416098002") || ss.equals("414285001"))
				ai.setSensitivityTypeSimple(Sensitivitytype.allergy);
			else if (ss.equals("59037007") || ss.equals("235719002"))
				ai.setSensitivityTypeSimple(Sensitivitytype.intolerance);
			else
				ai.setSensitivityTypeSimple(Sensitivitytype.unknown);
			ai.getExtensions().add(Factory.newExtension("http://www.healthintersections.com.au/fhir/extensions/allergy-category", type, false));
			ai.setCriticalitySimple(readCriticality(cda.getSeverity(obs)));
			Substance subst = new Substance();
			subst.setType(convert.makeCodeableConceptFromCD(cda.getDescendent(obs, "participant/participantRole/playingEntity/code"))); 
			subst.setXmlId("s1");
		  ai.getContained().add(subst);
			ai.setSubstance(Factory.makeResourceReference("#s1"));
			
		}
		
		
		// todo: text
		SectionComponent s = new Composition.SectionComponent();
		s.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section,  "code")));
		// todo: check subject
		s.setContent(Factory.makeResourceReference(addResource(list, "Allergies, Adverse Reactions, Alerts", UUID.randomUUID().toString())));
		return s;
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


}
