package org.hl7.fhir.convertors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Patient;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ArgonautConverter extends ConverterBase {

	private UcumService ucumSvc;
	private ValidationEngine validator;
	int errors = 0;
	int warnings = 0;
	
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
    	saveResource(makeSubject(cda, convert, doc, "patient-"+Utilities.changeFileExt(filename, "")), Utilities.path(destFolder, "patient-"+filename), "http://hl7.org/fhir/StructureDefinition/patient-daf-dafpatient");
    } catch (Exception e) {
    	throw new Error("Unable to process "+Utilities.path(sourceFolder, filename)+": "+e.getMessage(), e);
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


}
