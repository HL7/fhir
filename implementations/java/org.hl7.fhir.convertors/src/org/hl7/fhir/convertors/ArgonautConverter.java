package org.hl7.fhir.convertors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Encounter;
import org.hl7.fhir.instance.model.Encounter.EncounterHospitalizationComponent;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Practitioner;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceFactory;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.utils.FHIRTerminologyServices;
import org.hl7.fhir.instance.utils.ITerminologyServices;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ResourceUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.instance.validation.ValidationEngine;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.ucum.UcumEssenceService;
import org.hl7.fhir.utilities.ucum.UcumService;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ArgonautConverter extends ConverterBase {

	private UcumService ucumSvc;
	private ValidationEngine validator;
	private Map<String, Map<String, Integer>> sections = new HashMap<String, Map<String,Integer>>();

	int errors = 0;
	int warnings = 0;

	public ArgonautConverter(UcumService ucumSvc, String path, ITerminologyServices tx) throws Exception {
		super();
		this.ucumSvc = ucumSvc;
//		validator = new ValidationEngine();
//		validator.readDefinitions(path);
//		if (tx != null)
//			validator.getContext().setTerminologyServices(tx);

	}


	public ArgonautConverter(UcumEssenceService ucumEssenceService) {
		// TODO Auto-generated constructor stub
		ucumSvc = ucumEssenceService;
	}


	public int getErrors() {
		return errors;
	}


	public int getWarnings() {
		return warnings;
	}


	public void convert(String sourceFolder, String destFolder) throws Exception {
		File source = new File(sourceFolder);
		for (String f : source.list()) {
			convert(sourceFolder, destFolder, f);
		}
		ZipGenerator zip = new ZipGenerator("C:\\work\\com.healthintersections.fhir\\argonaut\\output\\output.zip");
		zip.addFiles("C:\\work\\com.healthintersections.fhir\\argonaut\\output\\", "", "xml", null);
		zip.close();
		printSectionSummaries();
	}


	private void printSectionSummaries() {
	  for (String n : sections.keySet()) {
	  	System.out.println(n+" Analysis");
	    Map<String, Integer> s = sections.get(n);
	    for (String p : sorted(s.keySet())) {
		  	System.out.println("  "+p+": "+s.get(p));
	    }
	  }
  }

  private List<String> sorted(Set<String> keys) {
    List<String> names = new ArrayList<String>();
    names.addAll(keys);
    Collections.sort(names);
    return names;
  }

	private void convert(String sourceFolder, String destFolder, String filename) {
		if (new File(Utilities.path(sourceFolder, filename)).length() == 0)
			return;

		CDAUtilities cda;
		try {
			System.out.println("Process "+Utilities.path(sourceFolder, filename));
			cda = new CDAUtilities(new FileInputStream(Utilities.path(sourceFolder, filename)));
			Element doc = cda.getElement();
			//    	cda.checkTemplateId(doc, "2.16.840.1.113883.10.20.22.1.1");
			Convert convert = new Convert(cda, ucumSvc);
		//	saveResource(makeSubject(cda, convert, doc, "patient-"+Utilities.changeFileExt(filename, "")), Utilities.path(destFolder, "patient-"+filename), "http://hl7.org/fhir/StructureDefinition/patient-daf-dafpatient");
//  	saveResource(makeAuthor(cda, convert, doc, "author-"+Utilities.changeFileExt(filename, "")), Utilities.path(destFolder, "author-"+filename), "http://hl7.org/fhir/StructureDefinition/practitioner-daf-dafpractitioner");
  	saveResource(makeEncounter(cda, convert, doc, "encounter-"+Utilities.changeFileExt(filename, "")), Utilities.path(destFolder, "encounter-"+filename), "http://hl7.org/fhir/StructureDefinition/encounter-daf-dafencounter");
//			scanSection("encounter", cda.getChild(doc, "documentationOf"));
//			scanSection("encounter", cda.getChild(doc, "componentOf"));
	//scanAuthor(cda)
		} catch (Exception e) {
			throw new Error("Unable to process "+Utilities.path(sourceFolder, filename)+": "+e.getMessage(), e);
		}
	}

	private void scanSection(String name, Element child) {
	  Map<String, Integer> section;
	  if (sections.containsKey(name))
	  	section = sections.get(name);
	  else {
	  	section = new HashMap<String, Integer>();
	    sections.put(name, section); 
	  }
	  iterateChildren(section, "/", child);
  }


	private void iterateChildren(Map<String, Integer> section, String path, Element element) {
	  Element child = XMLUtil.getFirstChild(element);
	  while (child != null) {
	  	String pathC = path+child.getNodeName();
	  	if (section.containsKey(pathC))
	  		section.put(pathC, section.get(pathC)+1);
	  	else
	  		section.put(pathC, 1);
	  	iterateChildren(section, pathC+"/", child);
	  	child = XMLUtil.getNextSibling(child);
	  }
  }


	private void saveResource(DomainResource resource, String path, String profile) throws Exception {
		NarrativeGenerator generator = new NarrativeGenerator("", validator.getContext());
		generator.generate(resource);
		XmlParser parser = new XmlParser();
		parser.setOutputStyle(OutputStyle.PRETTY);
		FileOutputStream fo = new FileOutputStream(path);
		parser.compose(fo, resource);
		fo.close();
		validate(path, profile);
	}

	private void validate(String filename, String url) throws Exception {
		StructureDefinition def = validator.getContext().getProfiles().get(url);
		if (def == null)
			throw new Exception("Unable to find Structure Definition "+url);

		validator.reset();
		validator.setProfile(def);
		validator.setSource(validator.loadFromFile(filename));
		validator.process();
		List<ValidationMessage> msgs = validator.getOutputs();
		boolean ok = false;
		for (ValidationMessage m : msgs) {
			if (m.getLevel() != IssueSeverity.INFORMATION)
				System.out.println("  "+m.getLevel().toCode()+": "+m.getMessage()+" @ "+m.getLocation());
			if (m.getLevel() == IssueSeverity.WARNING)
				warnings++;
			if (m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL)
				errors++;

			ok = ok && !(m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL);
		}
	}



//  /patientRole/providerOrganization: 2979
//  /patientRole/providerOrganization/addr: 2979
//  /patientRole/providerOrganization/id: 2979
//  /patientRole/providerOrganization/name: 2979
//  /patientRole/providerOrganization/telecom: 2979
	private Patient makeSubject(CDAUtilities cda, Convert convert, Element doc, String id) throws Exception {
		Element rt = cda.getChild(doc, "recordTarget");
		Element pr = cda.getChild(rt, "patientRole");
		Element p = cda.getChild(pr, "patient");

		Patient pat = new Patient();
		pat.setId(id);
		for (Element e : cda.getChildren(pr, "id"))
			pat.getIdentifier().add(convert.makeIdentifierFromII(e));

		for (Element e : cda.getChildren(pr, "addr"))
			pat.getAddress().add(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(pr, "telecom"))
			pat.getTelecom().add(convert.makeContactFromTEL(e));
		for (Element e : cda.getChildren(p, "name"))
			pat.getName().add(convert.makeNameFromEN(e));
		pat.setGender(convert.makeGenderFromCD(cda.getChild(p, "administrativeGenderCode")));
		pat.setBirthDateElement(convert.makeDateFromTS(cda.getChild(p, "birthTime")));
		pat.setMaritalStatus(convert.makeCodeableConceptFromCD(cda.getChild(p, "maritalStatusCode")));
		pat.addExtension(Factory.newExtension(CcdaExtensions.DAF_NAME_RACE, convert.makeCodeableConceptFromCD(cda.getChild(p, "raceCode")), false));
		pat.addExtension(Factory.newExtension(CcdaExtensions.DAF_NAME_ETHNICITY, convert.makeCodeableConceptFromCD(cda.getChild(p, "ethnicGroupCode")), false));

		pat.addExtension(Factory.newExtension(CcdaExtensions.NAME_RELIGION, convert.makeCodeableConceptFromCD(cda.getChild(p, "religiousAffiliationCode")), false));
		pat.addExtension(Factory.newExtension(CcdaExtensions.NAME_BIRTHPLACE, convert.makeAddressFromAD(cda.getChild(p, new String[] {"birthplace", "place", "addr"})), false));

		Element g = cda.getChild(p, "guardian");
		if (g != null) {
			Patient.ContactComponent guardian = new Patient.ContactComponent();
			pat.getContact().add(guardian);
			guardian.getRelationship().add(Factory.newCodeableConcept("GUARD", "urn:oid:2.16.840.1.113883.5.110", "guardian"));
			for (Element e : cda.getChildren(g, "addr"))
				if (guardian.getAddress() == null)
					guardian.setAddress(convert.makeAddressFromAD(e));
			for (Element e : cda.getChildren(g, "telecom"))
				guardian.getTelecom().add(convert.makeContactFromTEL(e));
			g = cda.getChild(g, "guardianPerson");
			for (Element e : cda.getChildren(g, "name"))
				if (guardian.getName() == null)
					guardian.setName(convert.makeNameFromEN(e));
		}

		Element l = cda.getChild(p, "languageCommunication");
		CodeableConcept cc = new CodeableConcept();
		Coding c = new Coding();
		c.setSystem(ResourceUtilities.FHIR_LANGUAGE);
		c.setCode(patchLanguage(cda.getChild(l, "languageCode").getAttribute("code")));
		cc.getCoding().add(c);
		pat.addCommunication().setLanguage(cc); 

		// todo: this got broken.... lang.setMode(convert.makeCodeableConceptFromCD(cda.getChild(l, "modeCode")));
		//		cc.getExtension().add(Factory.newExtension(CcdaExtensions.NAME_LANG_PROF, convert.makeCodeableConceptFromCD(cda.getChild(l, "modeCode")), false));
		//		pat.getExtension().add(Factory.newExtension(CcdaExtensions.NAME_RELIGION, convert.makeCodeableConceptFromCD(cda.getChild(p, "religiousAffiliationCode")), false));
		Element prv = cda.getChild(pr, "providerOrganization");
		if (prv != null) 
			pat.setManagingOrganization(Factory.makeReference("Organization/"+cda.getChild(prv, "id").getAttribute("root"), cda.getChild(prv, "name").getTextContent()));

		return pat;
	}

	private String patchLanguage(String lang) {
		if (lang.equals("spa"))
			return "es";
		if (lang.equals("eng"))
			return "en";
		return lang;
	}

//  /assignedAuthor/representedOrganization: 2979
//  /assignedAuthor/representedOrganization/addr: 2979
//  /assignedAuthor/representedOrganization/id: 2979
//  /assignedAuthor/representedOrganization/name: 2979
//  /assignedAuthor/representedOrganization/telecom: 2979

	private Practitioner makeAuthor(CDAUtilities cda, Convert convert, Element doc, String id) throws Exception {
		Element a = cda.getChild(doc, "author");
		Element aa = cda.getChild(a, "assignedAuthor");
		Element ap = cda.getChild(aa, "assignedPerson");

		Practitioner pat = new Practitioner();
		pat.setId(id);
		for (Element e : cda.getChildren(a, "id"))
			pat.getIdentifier().add(convert.makeIdentifierFromII(e));

		for (Element e : cda.getChildren(aa, "addr"))
			pat.getAddress().add(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(aa, "telecom"))
			pat.getTelecom().add(convert.makeContactFromTEL(e));
		for (Element e : cda.getChildren(ap, "name"))
			pat.setName(convert.makeNameFromEN(e));

		Element prv = cda.getChild(aa, "representedOrganization");
		if (prv != null) 
			pat.addPractitionerRole().setManagingOrganization(Factory.makeReference("Organization/"+cda.getChild(prv, "id").getAttribute("root"), cda.getChild(prv, "name").getTextContent()));
		return pat;
	}

//  /serviceEvent/performer: 9036
//  /serviceEvent/performer/assignedEntity: 9036
//  /serviceEvent/performer/assignedEntity/addr: 9036
//  /serviceEvent/performer/assignedEntity/assignedPerson: 9036
//  /serviceEvent/performer/assignedEntity/assignedPerson/name: 9036
//  /serviceEvent/performer/assignedEntity/assignedPerson/name/family: 9036
//  /serviceEvent/performer/assignedEntity/assignedPerson/name/given: 9036
//  /serviceEvent/performer/assignedEntity/assignedPerson/name/prefix: 6582
//  /serviceEvent/performer/assignedEntity/assignedPerson/name/suffix: 7716
//  /serviceEvent/performer/assignedEntity/id: 18072
//  /serviceEvent/performer/assignedEntity/telecom: 9036
//  /serviceEvent/performer/functionCode: 9036

	private Encounter makeEncounter(CDAUtilities cda, Convert convert, Element doc, String id) throws Exception {
		Element co = cda.getChild(doc, "componentOf");
		Element ee = cda.getChild(co, "encompassingEncounter");
		Element of = cda.getChild(doc, "documentationOf");
		Element se = cda.getChild(of, "serviceEvent");
		Element p = cda.getChild(se, "performer");

		Encounter enc = new Encounter();
		enc.setId(id);
		for (Element e : cda.getChildren(ee, "id"))
			enc.getIdentifier().add(convert.makeIdentifierFromII(e));

		Period p1 = convert.makePeriodFromIVL(cda.getChild(ee, "effectiveTime"));
		Period p2 = convert.makePeriodFromIVL(cda.getChild(se, "effectiveTime"));
		if (Base.compareDeep(p1, p2, false))
			throw new Error("episode time mismatch");
		enc.setPeriod(p1);

		Element dd = cda.getChild(ee, "dischargeDispositionCode");
		if (dd != null) {
			enc.setHospitalization(new EncounterHospitalizationComponent());
			enc.getHospitalization().setDischargeDisposition(convert.makeCodeableConceptFromCD(dd));
		}
		for (Element e : cda.getChildren(ee, "id")) {
      
//			enc.
		}
	  return enc;
	}


}
