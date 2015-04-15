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
import org.hl7.fhir.instance.model.Composition.SectionComponent;
import org.hl7.fhir.instance.model.Encounter.EncounterHospitalizationComponent;
import org.hl7.fhir.instance.model.Encounter.EncounterParticipantComponent;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Practitioner;
import org.hl7.fhir.instance.model.Reference;
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
	private Map<String, Reference> cache = new HashMap<String, Reference>();

	int errors = 0;
	int warnings = 0;
	private String destFolder;

	public ArgonautConverter(UcumService ucumSvc, String path, ITerminologyServices tx) throws Exception {
		super();
		this.ucumSvc = ucumSvc;
		validator = new ValidationEngine();
		validator.readDefinitions(path);
		if (tx != null)
			validator.getContext().setTerminologyServices(tx);
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
		
		this.destFolder = destFolder;

		CDAUtilities cda;
		try {
			System.out.println("Process "+Utilities.path(sourceFolder, filename));
			cda = new CDAUtilities(new FileInputStream(Utilities.path(sourceFolder, filename)));
			Element doc = cda.getElement();
			//    	cda.checkTemplateId(doc, "2.16.840.1.113883.10.20.22.1.1");
			Convert convert = new Convert(cda, ucumSvc);
			saveResource(makeSubject(cda, convert, doc, "patient-"+Utilities.changeFileExt(filename, "")), Utilities.path(destFolder, "patient-"+filename), "http://hl7.org/fhir/StructureDefinition/patient-daf-dafpatient");
			saveResource(makeAuthor(cda, convert, doc, "author-"+Utilities.changeFileExt(filename, "")), Utilities.path(destFolder, "author-"+filename), "http://hl7.org/fhir/StructureDefinition/pract-daf-dafpract");
			saveResource(makeEncounter(cda, convert, doc, "encounter-"+Utilities.changeFileExt(filename, "")), Utilities.path(destFolder, "encounter-"+filename), "http://hl7.org/fhir/StructureDefinition/encounter-daf-dafencounter");
			Element body =  cda.getDescendent(doc, "component/structuredBody");
			for (Element c : cda.getChildren(body, "component")) {
				processSection(cda, cda.getChild(c, "section"));
			}

//			scanSection("encounter", cda.getChild(doc, "documentationOf"));
//			scanSection("encounter", cda.getChild(doc, "componentOf"));
	//scanAuthor(cda)
		} catch (Exception e) {
			throw new Error("Unable to process "+Utilities.path(sourceFolder, filename)+": "+e.getMessage(), e);
		}
	}

	private void processSection(CDAUtilities cda, Element section) throws Exception {
		checkNoSubject(cda, section, "Section");
		
	  // this we do by templateId
		if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.11") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.5.1"))
		  scanSection("Problems", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.12") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.7.1"))
			scanSection("Procedures", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.3") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.22.1"))
		  scanSection("Encounters", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.6.1"))
		  scanSection("Alergies", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.2.1") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.6"))
		  scanSection("Immunizations", section);
		else if (cda.hasTemplateId(section, "1.3.6.1.4.1.19376.1.5.3.1.3.1"))
		  scanSection("reason for referral", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.3.1") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.14"))
		  scanSection("Results", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.4.1")	|| cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.16"))	
			scanSection("Vital Signs", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.1.1")	|| cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.8"))	
			scanSection("Medications", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.17")	|| cda.hasTemplateId(section, "2.16.840.1.113883.3.88.11.83.126"))	
			scanSection("Social History", section);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.9")	)	
			scanSection("Payers", section);
		
		
		
				
		else
			throw new Exception("Unprocessed section "+cda.getChild(section, "title").getTextContent());
					
//		if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.6") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.6.1"))
//			return processAdverseReactionsSection(section);
//		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.7") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.7.1"))
//			return processProceduresSection(section);
//		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.17"))  
//		  return processSocialHistorySection(section);
//		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.4") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.4.1"))
//		  return processVitalSignsSection(section);
//		else
//			// todo: error? 
	  
  }

	private void checkNoSubject(CDAUtilities cda, Element act, String path) throws Exception {
	  if (cda.getChild(act, "subject") != null) 
	  	throw new Exception("The conversion program cannot accept a nullFlavor at the location "+path);	  
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

	private Practitioner makePerformer(CDAUtilities cda, Convert convert, Element perf, String id) throws Exception {
		Element ae = cda.getChild(perf, "assignedEntity");
		Element ap = cda.getChild(ae, "assignedPerson");

		Practitioner pat = new Practitioner();
		pat.setId(id);
		for (Element e : cda.getChildren(ae, "id"))
			pat.getIdentifier().add(convert.makeIdentifierFromII(e));

		for (Element e : cda.getChildren(ae, "addr"))
			pat.getAddress().add(convert.makeAddressFromAD(e));
		for (Element e : cda.getChildren(ae, "telecom"))
			pat.getTelecom().add(convert.makeContactFromTEL(e));
		for (Element e : cda.getChildren(ap, "name"))
			pat.setName(convert.makeNameFromEN(e));

		return pat;
	}


///serviceEvent/performer/functionCode: 9036
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
		int i = 0;
		for (Element e : cda.getChildren(se, "performer")) {
			EncounterParticipantComponent part = enc.addParticipant();
			Practitioner perf = makePerformer(cda, convert, e, id+"-"+Integer.toString(i));
			Reference ref = null;
			for (Identifier identifier : perf.getIdentifier()) {
				String key = "Practitioner-"+keyFor(identifier);
				if (cache.containsKey(key))
					ref = cache.get(key);
			}
			if (ref == null) {
				saveResource(perf, Utilities.path(destFolder, perf.getId()), "http://hl7.org/fhir/StructureDefinition/practitioner-daf-dafpractitioner");
				ref = new Reference();
				ref.setReference("Practitioner/"+perf.getId());
				for (Identifier identifier : perf.getIdentifier()) {
					String key = "Practitioner-"+keyFor(identifier);
					cache.put(key, ref);
				}
			}
      part.setIndividual(ref);
      i++;
		}
	  return enc;
	}


	private String keyFor(Identifier identifier) {
	  return identifier.getSystem()+"||"+identifier.getValue();
  }


	// problems
//  /code: 782
//  /templateId: 2346
//  /text: 782
//  /title: 782
//  /entry: 5260
	
//  /entry/act: 5260
//  /entry/act/code: 5260
//  /entry/act/effectiveTime: 5260
//  /entry/act/effectiveTime/low: 5260
//  /entry/act/entryRelationship: 5260
//  /entry/act/entryRelationship/observation: 5260
//  /entry/act/entryRelationship/observation/code: 5260
//  /entry/act/entryRelationship/observation/effectiveTime: 5260
//  /entry/act/entryRelationship/observation/effectiveTime/low: 5260
//  /entry/act/entryRelationship/observation/entryRelationship: 5260
//  /entry/act/entryRelationship/observation/entryRelationship/observation: 5260
//  /entry/act/entryRelationship/observation/entryRelationship/observation/code: 5260
//  /entry/act/entryRelationship/observation/entryRelationship/observation/statusCode: 5260
//  /entry/act/entryRelationship/observation/entryRelationship/observation/templateId: 15780
//  /entry/act/entryRelationship/observation/entryRelationship/observation/text: 5260
//  /entry/act/entryRelationship/observation/entryRelationship/observation/text/reference: 5260
//  /entry/act/entryRelationship/observation/entryRelationship/observation/value: 5260
//  /entry/act/entryRelationship/observation/id: 5260
//  /entry/act/entryRelationship/observation/statusCode: 5260
//  /entry/act/entryRelationship/observation/templateId: 10520
//  /entry/act/entryRelationship/observation/text: 5260
//  /entry/act/entryRelationship/observation/text/reference: 5260
//  /entry/act/entryRelationship/observation/value: 5260
//  /entry/act/entryRelationship/observation/value/translation: 4966
//  /entry/act/id: 5260
//  /entry/act/performer: 4499
//  /entry/act/performer/assignedEntity: 4499
//  /entry/act/performer/assignedEntity/addr: 4499
//  /entry/act/performer/assignedEntity/assignedPerson: 4499
//  /entry/act/performer/assignedEntity/assignedPerson/name: 4499
//  /entry/act/performer/assignedEntity/assignedPerson/name/family: 4499
//  /entry/act/performer/assignedEntity/assignedPerson/name/given: 4499
//  /entry/act/performer/assignedEntity/assignedPerson/name/prefix: 3835
//  /entry/act/performer/assignedEntity/assignedPerson/name/suffix: 4165
//  /entry/act/performer/assignedEntity/id: 8998
//  /entry/act/performer/assignedEntity/telecom: 4499
//  /entry/act/statusCode: 5260
//  /entry/act/templateId: 21040

  
//	Procedures Analysis
//  /code: 782
//  /entry: 1789
//  /entry/procedure: 1789
//  /entry/procedure/code: 1789
//  /entry/procedure/code/originalText: 1789
//  /entry/procedure/code/originalText/reference: 1789
//  /entry/procedure/effectiveTime: 1789
//  /entry/procedure/id: 1789
//  /entry/procedure/performer: 1414
//  /entry/procedure/performer/assignedEntity: 1414
//  /entry/procedure/performer/assignedEntity/addr: 1414
//  /entry/procedure/performer/assignedEntity/assignedPerson: 1414
//  /entry/procedure/performer/assignedEntity/assignedPerson/name: 1414
//  /entry/procedure/performer/assignedEntity/assignedPerson/name/family: 1414
//  /entry/procedure/performer/assignedEntity/assignedPerson/name/given: 1416
//  /entry/procedure/performer/assignedEntity/assignedPerson/name/prefix: 1285
//  /entry/procedure/performer/assignedEntity/assignedPerson/name/suffix: 1383
//  /entry/procedure/performer/assignedEntity/id: 2492
//  /entry/procedure/performer/assignedEntity/telecom: 1414
//  /entry/procedure/statusCode: 1789
//  /entry/procedure/templateId: 5367
//  /entry/procedure/text: 1789
//  /entry/procedure/text/reference: 1789
//  /templateId: 782
//  /text: 782
//  /title: 782

//	Encounters Analysis
//  /code: 782
//  /entry: 782
//  /entry/encounter: 782
//  /entry/encounter/code: 782
//  /entry/encounter/code/originalText: 782
//  /entry/encounter/code/originalText/reference: 782
//  /entry/encounter/effectiveTime: 782
//  /entry/encounter/effectiveTime/high: 782
//  /entry/encounter/effectiveTime/low: 782
//  /entry/encounter/id: 782
//  /entry/encounter/participant: 782
//  /entry/encounter/participant/participantRole: 782
//  /entry/encounter/participant/participantRole/code: 782
//  /entry/encounter/participant/participantRole/playingEntity: 782
//  /entry/encounter/participant/participantRole/playingEntity/name: 782
//  /entry/encounter/participant/templateId: 782
//  /entry/encounter/performer: 782
//  /entry/encounter/performer/assignedEntity: 782
//  /entry/encounter/performer/assignedEntity/addr: 782
//  /entry/encounter/performer/assignedEntity/assignedPerson: 782
//  /entry/encounter/performer/assignedEntity/assignedPerson/name: 782
//  /entry/encounter/performer/assignedEntity/assignedPerson/name/family: 782
//  /entry/encounter/performer/assignedEntity/assignedPerson/name/given: 1362
//  /entry/encounter/performer/assignedEntity/assignedPerson/name/prefix: 782
//  /entry/encounter/performer/assignedEntity/assignedPerson/name/suffix: 49
//  /entry/encounter/performer/assignedEntity/id: 782
//  /entry/encounter/performer/assignedEntity/representedOrganization: 782
//  /entry/encounter/performer/assignedEntity/representedOrganization/addr: 782
//  /entry/encounter/performer/assignedEntity/representedOrganization/id: 782
//  /entry/encounter/performer/assignedEntity/representedOrganization/name: 782
//  /entry/encounter/performer/assignedEntity/representedOrganization/telecom: 782
//  /entry/encounter/performer/assignedEntity/telecom: 782
//  /entry/encounter/performer/time: 782
//  /entry/encounter/performer/time/high: 782
//  /entry/encounter/performer/time/low: 782
//  /entry/encounter/templateId: 2346
//  /entry/encounter/text: 782
//  /entry/encounter/text/reference: 782
//  /templateId: 2346
//  /text: 782
//  /title: 782

}
