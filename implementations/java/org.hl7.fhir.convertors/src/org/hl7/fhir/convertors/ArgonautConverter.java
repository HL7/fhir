package org.hl7.fhir.convertors;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.AllergyIntolerance;
import org.hl7.fhir.instance.model.AllergyIntolerance.AllergyIntoleranceEventComponent;
import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.Binary;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Condition;
import org.hl7.fhir.instance.model.ContactPoint;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DocumentReference;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.Organization;
import org.hl7.fhir.instance.model.DocumentReference.DocumentReferenceContextComponent;
import org.hl7.fhir.instance.model.DocumentReference.DocumentReferenceStatus;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Immunization;
import org.hl7.fhir.instance.model.Immunization.ImmunizationExplanationComponent;
import org.hl7.fhir.instance.model.Location;
import org.hl7.fhir.instance.model.Medication;
import org.hl7.fhir.instance.model.Condition.ConditionStatus;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Encounter;
import org.hl7.fhir.instance.model.Encounter.EncounterClass;
import org.hl7.fhir.instance.model.Encounter.EncounterHospitalizationComponent;
import org.hl7.fhir.instance.model.Encounter.EncounterParticipantComponent;
import org.hl7.fhir.instance.model.Encounter.EncounterState;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.List_;
import org.hl7.fhir.instance.model.List_.ListMode;
import org.hl7.fhir.instance.model.List_.ListStatus;
import org.hl7.fhir.instance.model.MedicationStatement;
import org.hl7.fhir.instance.model.MedicationStatement.MedicationStatementDosageComponent;
import org.hl7.fhir.instance.model.MedicationStatement.MedicationStatementStatus;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.Observation;
import org.hl7.fhir.instance.model.Observation.ObservationRelationshiptypes;
import org.hl7.fhir.instance.model.Observation.ObservationReliability;
import org.hl7.fhir.instance.model.Observation.ObservationStatus;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Practitioner;
import org.hl7.fhir.instance.model.Procedure;
import org.hl7.fhir.instance.model.Procedure.ProcedurePerformerComponent;
import org.hl7.fhir.instance.model.Procedure.ProcedureStatus;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceType;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.Timing;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.utils.FHIRTerminologyServices;
import org.hl7.fhir.instance.utils.ITerminologyServices;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ResourceUtilities;
import org.hl7.fhir.instance.validation.ValidationEngine;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.ucum.UcumEssenceService;
import org.hl7.fhir.utilities.ucum.UcumService;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

public class ArgonautConverter extends ConverterBase {
	//  public final static String DEF_TS_SERVER = "http://fhir-dev.healthintersections.com.au/open";
  public final static String DEV_TS_SERVER = "http://local.healthintersections.com.au:960/open";
	public static final String UCUM_PATH = "c:\\work\\org.hl7.fhir\\build\\implementations\\java\\org.hl7.fhir.convertors\\samples\\ucum-essence.xml";
	public static final String SRC_PATH = "c:\\work\\org.hl7.fhir\\build\\publish\\";
	private static final String DEFAULT_ID_SPACE = "urn:uuid:e8e06b15-0f74-4b8e-b5e2-609dae7119dc";
	
	private static final boolean WANT_SAVE = true;
	private static final boolean WANT_VALIDATE = false;
	private String destFolder;
	
	public static void main(String[] args) {
		try {
		  ArgonautConverter c = new ArgonautConverter(new UcumEssenceService(UCUM_PATH), Utilities.path(SRC_PATH, "validation.zip"), new FHIRTerminologyServices(DEV_TS_SERVER));
		  c.destFolder = "C:\\work\\com.healthintersections.fhir\\argonaut\\output";
		  c.convert("C:\\work\\com.healthintersections.fhir\\argonaut\\file_emergency", EncounterClass.EMERGENCY);
		  c.convert("C:\\work\\com.healthintersections.fhir\\argonaut\\file_ed", EncounterClass.INPATIENT);
		  c.convert("C:\\work\\com.healthintersections.fhir\\argonaut\\fileX", EncounterClass.AMBULATORY);
			c.printSectionSummaries();
			c.closeZips();
		  System.out.println("All done. "+Integer.toString(c.getErrors())+" errors, "+Integer.toString(c.getWarnings())+" warnings");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public class Context {

		public String baseId;
		public Reference authorRef;
		public Encounter encounter;
		public EncounterClass encClass;
		public int obsId;
		public DateTimeType now = DateTimeType.now();
		public int orgId;
		public Reference subjectRef;
  }

	public class Stats {
		public int instances;
		public int errors;
		public int warnings;
  }

	private UcumService ucumSvc;
	private ValidationEngine validator;
	private Map<String, Map<String, Integer>> sections = new HashMap<String, Map<String,Integer>>();
	private Map<String, Practitioner> practitionerCache = new HashMap<String, Practitioner>();
	public int perfCount;
	private Set<String> oids = new HashSet<String>();
	private Map<String, ZipGenerator> zips = new HashMap<String, ZipGenerator>();
	private Map<String, Stats> stats = new HashMap<String, Stats>();
	private ZipGenerator zip;

	int errors = 0;
	int warnings = 0;

	public ArgonautConverter(UcumService ucumSvc, String path, ITerminologyServices tx) throws Exception {
		super();
		this.ucumSvc = ucumSvc;
		validator = new ValidationEngine();
		validator.readDefinitions(path);
		validator.setNoSchematron(true);
		validator.setAnyExtensionsAllowed(true);
		if (tx != null)
			validator.getContext().setTerminologyServices(tx);
	}

	public int getErrors() {
		return errors;
	}


	public int getWarnings() {
		return warnings;
	}


	public void convert(String sourceFolder, EncounterClass clss) throws Exception {
		File source = new File(sourceFolder);
		for (String f : source.list()) {
			convert(sourceFolder, f, clss);
		}
	}

	private void closeZips() throws Exception {
		for (ZipGenerator z : zips.values())
			z.close();	  
  }

	public void printSectionSummaries() {
  	System.out.println("Statistics:");
	  for (String n : sorted(stats.keySet())) { 
	  	Stats s = stats.get(n);
	  	System.out.println("  "+n+": generated "+Integer.toString(s.instances)+", errors "+Integer.toString(s.errors)+", warnings "+Integer.toString(s.warnings));
	  }
		
  	System.out.println("OIDs:");
	  for (String n : sorted(oids)) 
	  	System.out.println("  "+n);
	  
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

	private void convert(String sourceFolder, String filename, EncounterClass clss) {
		if (new File(Utilities.path(sourceFolder, filename)).length() == 0)
			return;
		
		CDAUtilities cda;
		try {
			System.out.println("Process "+Utilities.path(sourceFolder, filename));
			cda = new CDAUtilities(new FileInputStream(Utilities.path(sourceFolder, filename)));
			zip = new ZipGenerator(Utilities.path(destFolder, "doc", Utilities.changeFileExt(filename, ".zip")));
			Element doc = cda.getElement();
			Convert convert = new Convert(cda, ucumSvc, "-0400");
			convert.setGenerateMissingExtensions(true);
			Context context = new Context();
			context.baseId = Utilities.changeFileExt(filename, "");
			context.encClass = clss;
			makeSubject(cda, convert, doc, context, context.baseId+"-patient");
			makeAuthor(cda, convert, doc, context, context.baseId+"-author");
			makeEncounter(cda, convert, doc, context, context.baseId+"-encounter");
			Element body =  cda.getDescendent(doc, "component/structuredBody");
			for (Element c : cda.getChildren(body, "component")) {
				processSection(cda, convert, context, cda.getChild(c, "section"));
			}
			oids.addAll(convert.getOids());
			saveResource(context.encounter);
			makeBinary(sourceFolder, filename, context);
			makeDocumentReference(cda, convert, doc, context);
			zip.close();
		} catch (Exception e) {
			throw new Error("Unable to process "+Utilities.path(sourceFolder, filename)+": "+e.getMessage(), e);
		}
	}

	private void processSection(CDAUtilities cda, Convert convert, Context context, Element section) throws Exception {
		checkNoSubject(cda, section, "Section");
		
	  // this we do by templateId
		if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.11") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.5.1"))
			processProblemsSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.12") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.7.1"))
			processProcedureSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.3") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.22.1"))
			processEncountersSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.6.1"))
			processAllergiesSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.2.1") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.6"))
			processImmunizationsSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "1.3.6.1.4.1.19376.1.5.3.1.3.1"))
			processReasonForEncounter(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.3.1") || cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.14"))
			processResultsSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.4.1")	|| cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.16"))	
			processVitalSignsSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.1.1")	|| cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.8"))	
			processMedicationsSection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.22.2.17")	|| cda.hasTemplateId(section, "2.16.840.1.113883.3.88.11.83.126"))	
			processSocialHistorySection(cda, convert, section, context);
		else if (cda.hasTemplateId(section, "2.16.840.1.113883.10.20.1.9")	)	
			scanSection("Payers", section);
		else
			throw new Exception("Unprocessed section "+cda.getChild(section, "title").getTextContent());
  }

	private void checkNoSubject(CDAUtilities cda, Element act, String path) throws Exception {
	  if (cda.getChild(act, "subject") != null) 
	  	throw new Exception("The conversion program cannot accept a subject at the location "+path);	  
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
	  	String pathC = path+child.getNodeName()+attributes(child);
	  	if (section.containsKey(pathC))
	  		section.put(pathC, section.get(pathC)+1);
	  	else
	  		section.put(pathC, 1);
	  	iterateChildren(section, pathC+"/", child);
	  	child = XMLUtil.getNextSibling(child);
	  }
  }


	private String attributes(Element child) {
		String s = ",";
		if (child.hasAttribute("inversionInd"))
			s += "inversionInd:"+child.getAttribute("inversionInd")+",";
		if (child.hasAttribute("negationInd"))
			s += "negationInd:"+child.getAttribute("negationInd")+",";
		if (child.hasAttribute("nullFlavor"))
			s += "nullFlavor:"+child.getAttribute("nullFlavor")+",";
		if (child.hasAttribute("xsi:type"))
			s += "type:"+child.getAttribute("xsi:type")+",";
		s = s.substring(0, s.length()-1);
			
		if (child.getNodeName().equals("statusCode"))
			return "[code:"+child.getAttribute("code")+"]";
		if (child.getNodeName().equals("temnplateId"))
			return "[id:"+child.getAttribute("root")+"]";
		else if (child.hasAttribute("moodCode"))
	  	return "["+child.getAttribute("classCode")+","+child.getAttribute("moodCode")+s+"]";
	  else if (child.hasAttribute("classCode"))
		 	return "["+child.getAttribute("classCode")+s+"]";
	  else if (child.hasAttribute("typeCode"))
		 	return "["+child.getAttribute("typeCode")+s+"]";
	  else if (Utilities.noString(s))
	  	return "";
	  else
	  	return "["+s.substring(1)+"]";
  }


	private void saveResource(Resource resource) throws Exception {
		if (!WANT_SAVE)
			return;
		
		DomainResource dr = null;
		if (resource instanceof DomainResource) {
			dr = (DomainResource) resource;
  		if (!dr.hasText()) {
	  	  NarrativeGenerator generator = new NarrativeGenerator("", validator.getContext());
		    generator.generate(dr);
	  	}
		}
		XmlParser parser = new XmlParser();
		parser.setOutputStyle(OutputStyle.PRETTY);
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		parser.compose(ba, resource);
		ba.close();
		byte[] src = ba.toByteArray();
		String rn = resource.getResourceType().toString();
		zip.addBytes(resource.getId()+".xml", src, false);
		if (!zips.containsKey(rn)) {
			zips.put(rn, new ZipGenerator(Utilities.path(destFolder, "type", resource.getResourceType().toString().toLowerCase()+".zip")));
			stats.put(rn, new Stats());
		}
		
		zips.get(rn).addBytes(resource.getId()+"xml", src, false);
		Stats ss = stats.get(rn);
		ss.instances++;

		String profile = resource.getUserString("profile");
		validate(src, profile, resource, ss);
	}

	private void validate(byte[] src, String url, Resource resource, Stats stats) throws Exception {
		if (!WANT_VALIDATE)
			return;
		if (url == null)
			url = "http://hl7.org/fhir/StructureDefinition/"+resource.getResourceType().toString();
		StructureDefinition def = validator.getContext().getProfiles().get(url);
		if (def == null)
			throw new Exception("Unable to find Structure Definition "+url);

		validator.reset();
		validator.setProfile(def);
		validator.setSource(src);
		validator.process();
		List<ValidationMessage> msgs = validator.getOutputs();
		boolean ok = false;
		boolean first = true;
		for (ValidationMessage m : msgs) {
			if (m.getLevel() == IssueSeverity.ERROR && !msgOk(m.getMessage())) {
				if (first) {
					System.out.println("  validate "+resource.getId()+".xml against "+url);
					first = false;
				}
				System.out.println("    "+m.getLevel().toCode()+": "+m.getMessage()+" @ "+m.getLocation());
				if (m.getLevel() == IssueSeverity.WARNING) {
					stats.warnings++;
					warnings++;
				}
				if (m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL) {
					stats.errors++;
					errors++;
				}
			}

			ok = ok && !(m.getLevel() == IssueSeverity.ERROR || m.getLevel() == IssueSeverity.FATAL);
		}
	}

	private boolean msgOk(String message) {
	  if (message.equals("Invalid Resource target type. Found Observation, but expected one of (DiagnosticReport)"))
	  	return true;
	  return false;
  }

	private void checkGenerateIdentifier(List<Identifier> ids, DomainResource resource) {
	  if (ids.isEmpty())
	  	ids.add(new Identifier().setSystem(DEFAULT_ID_SPACE).setValue(resource.getClass().getName().toLowerCase()+"-"+resource.getId()));	  
  }



	private void makeSubject(CDAUtilities cda, Convert convert, Element doc, Context context, String id) throws Exception {
		Element rt = cda.getChild(doc, "recordTarget");
	  scanSection("Patient", rt);	  
		Element pr = cda.getChild(rt, "patientRole");
		Element p = cda.getChild(pr, "patient");

		Patient pat = new Patient();
		pat.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/patient-daf-dafpatient");
		StringBuilder b = new StringBuilder();
		
		pat.setId(id);
		for (Element e : cda.getChildren(p, "name")) {
			HumanName name = convert.makeNameFromEN(e);
			pat.getName().add(name);
			b.append(NarrativeGenerator.displayHumanName(name));
			b.append(" ");
		}
		b.append("(");
		for (Element e : cda.getChildren(pr, "id")) {			
			Identifier identifier = convert.makeIdentifierFromII(e);
			pat.getIdentifier().add(identifier);
			b.append(identifier.getValue());
			b.append(", ");
		}

		for (Element e : cda.getChildren(pr, "addr"))
			pat.getAddress().add(makeDefaultAddress(convert.makeAddressFromAD(e)));
		for (Element e : cda.getChildren(pr, "telecom"))
			pat.getTelecom().add(convert.makeContactFromTEL(e));
		pat.setGender(convert.makeGenderFromCD(cda.getChild(p, "administrativeGenderCode")));
		b.append(pat.getGender().getDisplay());
		b.append(", ");
		pat.setBirthDateElement(convert.makeDateFromTS(cda.getChild(p, "birthTime")));
		b.append("DOB: ");
		b.append(pat.getBirthDateElement().toHumanDisplay());
		b.append(")");
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
					guardian.setAddress(makeDefaultAddress(convert.makeAddressFromAD(e)));
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

		Element prv = cda.getChild(pr, "providerOrganization");
		if (prv != null) 
			pat.setManagingOrganization(new Reference().setReference("Organization/"+processOrganization(prv, cda, convert, context).getId()));

		context.subjectRef = new Reference().setDisplay(b.toString()).setReference("Patient/"+pat.getId());
		saveResource(pat);
	}

	private Organization processOrganization(Element oo, CDAUtilities cda, Convert convert, Context context) throws Exception {
		Organization org = new Organization();
		org.setId(context.baseId+"-organization-"+Integer.toString(context.orgId));
		org.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/org-daf-daforganization");
		context.orgId++;
		for (Element e : cda.getChildren(oo, "id"))
			org.getIdentifier().add(convert.makeIdentifierFromII(e));
		for (Element e : cda.getChildren(oo, "addr"))
			org.getAddress().add(makeDefaultAddress(convert.makeAddressFromAD(e)));
		for (Element e : cda.getChildren(oo, "telecom")) {
			ContactPoint cp = convert.makeContactFromTEL(e);
			if (Utilities.noString(cp.getValue()))
  			cp.setValue("1 (555) 555 5555");
			org.getTelecom().add(cp);
		}
		for (Element e : cda.getChildren(oo, "name"))
			org.setName(e.getTextContent());
		saveResource(org);
	  return org;
  }

	private Address makeDefaultAddress(Address ad) {
	  if (ad == null || ad.isEmpty()) {
	  	ad = new Address();
	  	ad.addLine("21 Doar road");
	  	ad.setCity("Erewhon");
	  	ad.setState("CA");
	  	ad.setPostalCode("31233");
	  }
	  return ad;
  }

	private String patchLanguage(String lang) {
		if (lang.equals("spa"))
			return "es";
		if (lang.equals("eng"))
			return "en";
		return lang;
	}

	private Practitioner makeAuthor(CDAUtilities cda, Convert convert, Element doc, Context context, String id) throws Exception {
		Element a = cda.getChild(doc, "author");
	  scanSection("Author", a);	
	  Practitioner author = processPerformer(cda, convert, context, a, "assignedAuthor", "assignedPerson");
	  context.authorRef = new Reference().setDisplay(author.getUserString("display")).setReference("Practitioner/"+author.getId());
		return author;
	}

///legalAuthenticator/assignedEntity: 2979
///legalAuthenticator/assignedEntity/addr: 2979
///legalAuthenticator/assignedEntity/assignedPerson: 2979
///legalAuthenticator/assignedEntity/id: 2979
///legalAuthenticator/assignedEntity/representedOrganization: 2979
///legalAuthenticator/assignedEntity/representedOrganization/addr: 2979
///legalAuthenticator/assignedEntity/representedOrganization/id: 2979
///legalAuthenticator/assignedEntity/representedOrganization/name: 2979
///legalAuthenticator/assignedEntity/representedOrganization/telecom: 2979
///legalAuthenticator/assignedEntity/telecom: 2979

	private Practitioner makePerformer(CDAUtilities cda, Convert convert, Context context, Element eperf, String roleName, String entityName) throws Exception {
		Element ae = cda.getChild(eperf, roleName); 
		Element ap = cda.getChild(ae, entityName);

		StringBuilder b = new StringBuilder();
		
		Practitioner perf = new Practitioner();
		perf.setId("performer-"+Integer.toString(perfCount));
		perf.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/pract-daf-dafpract");
		perfCount++;
		for (Element e : cda.getChildren(ae, "id")) {
			Identifier id = convert.makeIdentifierFromII(e);
			perf.getIdentifier().add(id);
		}

		for (Element e : cda.getChildren(ap, "name")) {
			HumanName name = convert.makeNameFromEN(e);
			perf.setName(name);
			b.append(NarrativeGenerator.displayHumanName(name));
			b.append(" ");
		}
		for (Element e : cda.getChildren(ae, "addr"))
			perf.getAddress().add(makeDefaultAddress(convert.makeAddressFromAD(e)));
		boolean first = true;
		for (Element e : cda.getChildren(ae, "telecom")) {
			if (first) {
			  b.append("(");
			  first = false;
			} else
			  b.append(" ");
			ContactPoint contact = convert.makeContactFromTEL(e);
			perf.getTelecom().add(contact);
			b.append(NarrativeGenerator.displayContactPoint(contact));
		}
		if (!first)
			b.append(")");

		Element e = cda.getChild(ae, "representedOrganization");
		if (e != null)
		  perf.addPractitionerRole().setManagingOrganization(new Reference().setReference("Organization/"+processOrganization(e, cda, convert, context).getId()));
		perf.setUserData("display", b.toString());
		return perf;
	}


///serviceEvent/performer/functionCode: 9036
	private Encounter makeEncounter(CDAUtilities cda, Convert convert, Element doc, Context context, String id) throws Exception {
		Element co = cda.getChild(doc, "componentOf");
		Element ee = cda.getChild(co, "encompassingEncounter");
	  scanSection("Encounter", co);	  
		Element of = cda.getChild(doc, "documentationOf");
		Element se = cda.getChild(of, "serviceEvent");
	  scanSection("Encounter", of);	  

		Encounter enc = new Encounter();
		enc.setId(id);
		enc.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/encounter-daf-dafencounter");
		context.encounter = enc;
		enc.setPatient(context.subjectRef);
		
		for (Element e : cda.getChildren(ee, "id"))
			enc.getIdentifier().add(convert.makeIdentifierFromII(e));
		checkGenerateIdentifier(enc.getIdentifier(), enc);

		Period p1 = convert.makePeriodFromIVL(cda.getChild(ee, "effectiveTime"));
//		Period p2 = convert.makePeriodFromIVL(cda.getChild(se, "effectiveTime")); // well, what is this?
//		if (!Base.compareDeep(p1, p2, false))
//			throw new Error("episode time mismatch: "+NarrativeGenerator.displayPeriod(p1)+" & "+NarrativeGenerator.displayPeriod(p2));
		enc.setPeriod(p1);
		if (p1.hasEnd())
			enc.setStatus(EncounterState.FINISHED);
		else
			enc.setStatus(EncounterState.INPROGRESS);
		enc.setClass_(context.encClass);
		
		Element dd = cda.getChild(ee, "dischargeDispositionCode");
		if (dd != null) {
			enc.setHospitalization(new EncounterHospitalizationComponent());
			enc.getHospitalization().setDischargeDisposition(convert.makeCodeableConceptFromCD(dd));
		}
		for (Element e : cda.getChildren(se, "performer")) {
			Practitioner p = processPerformer(cda, convert, context, e, "assignedEntity", "assignedPerson");
			Reference ref = new Reference().setReference("Practitioner/"+p.getId()).setDisplay(p.getUserString("display"));
			if (ref != null) 
				enc.addParticipant().setIndividual(ref);
		}
	  return enc;
	}


	private Practitioner processPerformer(CDAUtilities cda, Convert convert, Context context, Element e, String roleName, String entityName) throws Exception {
	  Practitioner perf = makePerformer(cda, convert, context, e, roleName, entityName);
	  if (perf == null)
	  	return null;
	  
	  Reference ref = null;
	  for (Identifier identifier : perf.getIdentifier()) {
	  	String key = keyFor(identifier);
	  	if (practitionerCache.containsKey(key))
	  		return practitionerCache.get(key);
	  }

   	saveResource(perf);
  	for (Identifier identifier : perf.getIdentifier()) {
  		String key = "Practitioner-"+keyFor(identifier);
  		practitionerCache.put(key, perf);
  	}
   	return perf;
  }


	private String keyFor(Identifier identifier) {
	  return identifier.getSystem()+"||"+identifier.getValue();
  }

	private void buildNarrative(DomainResource resource, Element child) {
		if (!Utilities.noString(child.getTextContent()))
			resource.setText(new Narrative().setStatus(NarrativeStatus.ADDITIONAL).setDiv(new XhtmlNode(NodeType.Text).setContent(child.getTextContent())));
  }

	private void processProcedureSection(CDAUtilities cda, Convert convert, Element sect, Context context) throws DOMException, Exception {
	  scanSection("Procedures", sect);
		List_ list = new List_();
		list.setId(context.baseId+"-list-procedures");
		// list.setUserData("profile", "") none?
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(sect, "code")));
    list.setTitle(cda.getChild(sect, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setMode(ListMode.SNAPSHOT);
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
		buildNarrative(list, cda.getChild(sect, "text"));

		int i = 0;
		for (Element c : cda.getChildren(sect, "entry")) {
			Element p = cda.getChild(c, "procedure");
			Procedure proc = new Procedure();
			proc.setId(context.baseId+"-procedure-"+Integer.toString(i));
			proc.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/procedure-daf-dafprocedure");
			i++;
			proc.setPatient(context.subjectRef);
			proc.setEncounter(new Reference().setReference("Encounter/"+context.encounter.getId()));
			list.addEntry().setItem(new Reference().setReference("Procedure/"+proc.getId()));
			proc.setType(convert.makeCodeableConceptFromCD(cda.getChild(p, "code")));
			for (Element e : cda.getChildren(p, "id"))
				proc.getIdentifier().add(convert.makeIdentifierFromII(e));
			
			proc.setStatus(determineProcedureStatus(cda.getChild(p, "statusCode")));
			buildNarrative(proc, cda.getChild(p, "text"));
			proc.setPerformed(convert.makeDateTimeFromTS(cda.getChild(p, "effectiveTime")));

			for (Element e : cda.getChildren(p, "performer")) {
				ProcedurePerformerComponent part = proc.addPerformer();
				Practitioner pp = processPerformer(cda, convert, context, e, "assignedEntity", "assignedPerson");
				Reference ref = new Reference().setReference("Practitioner/"+pp.getId()).setDisplay(pp.getUserString("display"));
				part.setPerson(ref);
			}
			saveResource(proc);
		}
		saveResource(list);
	}	

	private ProcedureStatus determineProcedureStatus(Element child) {
		if ("completed".equals(child.getAttribute("code")))
			return ProcedureStatus.COMPLETED;
	  throw new Error("not done yet: "+child.getAttribute("code"));
  }


	private void processReasonForEncounter(CDAUtilities cda, Convert convert, Element sect, Context context) throws DOMException, Exception {
	  scanSection("Reason", sect);
		context.encounter.addReason().setText(cda.getChild(sect, "text").getTextContent());
	}
	
  private void processProblemsSection(CDAUtilities cda, Convert convert, Element sect, Context context) throws DOMException, Exception {
	  scanSection("Problems", sect);
		List_ list = new List_();
		list.setId(context.baseId+"-list-problems");
		list.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/list-daf-dafproblemlist");
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(sect, "code")));
    list.setTitle(cda.getChild(sect, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setMode(ListMode.SNAPSHOT);
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
		buildNarrative(list, cda.getChild(sect, "text"));

		int i = 0;
		for (Element c : cda.getChildren(sect, "entry")) {
			Element pca = cda.getChild(c, "act"); // problem concern act
			Condition cond = new Condition();
			cond.setId(context.baseId+"-problem-"+Integer.toString(i));
			cond.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/condition-daf-dafcondition");
			i++;
			cond.setPatient(context.subjectRef);
			cond.setEncounter(new Reference().setReference("Encounter/"+context.encounter.getId()));
			
			cond.setDateAssertedElement(convert.makeDateFromTS(cda.getChild(cda.getChild(pca, "effectiveTime"), "low")));
			
			boolean found = false;
			for (Element e : cda.getChildren(pca, "id")) {
				Identifier id = convert.makeIdentifierFromII(e);
				cond.getIdentifier().add(id);
			}
			if (!found) {
				list.addEntry().setItem(new Reference().setReference("Condition/"+cond.getId()));
				for (Element e : cda.getChildren(pca, "performer")) {
					if (cond.hasAsserter())
						throw new Error("additional asserter discovered");
					Practitioner p = processPerformer(cda, convert, context, e, "assignedEntity", "assignedPerson");
					Reference ref = new Reference().setReference("Practitioner/"+p.getId()).setDisplay(p.getUserString("display"));
					cond.setAsserter(ref);
				}
				Element po = cda.getChild(cda.getChild(pca, "entryRelationship"), "observation"); // problem observation
				cond.setClinicalStatus(ConditionStatus.UNKNOWN);
				cond.setCode(convert.makeCodeableConceptFromCD(cda.getChild(po, "value")));
				cond.setOnset(convert.makeDateTimeFromTS(cda.getChild(cda.getChild(po, "effectiveTime"), "low")));
				Element pso = cda.getChild(cda.getChild(po, "entryRelationship"), "observation"); // problem status observation
				String status = cda.getChild(pso, "value").getAttribute("code");
				if (status.equals("55561003"))
					cond.setAbatement(new BooleanType("false"));
				else 
					throw new Error("unknown status code "+status);
				saveResource(cond);
			}
		}
		saveResource(list);
	}

	private void processAllergiesSection(CDAUtilities cda, Convert convert, Element section, Context context) throws Exception {
		scanSection("Allergies", section);
		List_ list = new List_();
		list.setId(context.baseId+"-list-allergies");
		list.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/list-daf-dafallergylist");
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section, "code")));
    list.setTitle(cda.getChild(section, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
    list.setMode(ListMode.SNAPSHOT);
		buildNarrative(list, cda.getChild(section, "text"));

		int i = 0;
		for (Element c : cda.getChildren(section, "entry")) {
			Element apa = cda.getChild(c, "act"); // allergy problem act
			AllergyIntolerance ai = new AllergyIntolerance();
			ai.setId(context.baseId+"-allergy-"+Integer.toString(i));
			ai.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/allergyintolerance-daf-dafallergyintolerance");
			i++;
			ai.setPatient(context.subjectRef);
			
			ai.setRecordedDateElement(convert.makeDateTimeFromTS(cda.getChild(cda.getChild(apa, "effectiveTime"), "low")));
			boolean found = false;
			for (Element e : cda.getChildren(apa, "id")) {
				Identifier id = convert.makeIdentifierFromII(e);
				ai.getIdentifier().add(id);
			}
			if (!found) {
				list.addEntry().setItem(new Reference().setReference("AllergyIntolerance/"+ai.getId()));

				Element ao = cda.getChild(cda.getChild(apa, "entryRelationship"), "observation"); // allergy observation
				if (!cda.getChild(ao, "value").getAttribute("code").equals("419511003"))
					throw new Error("unexpected code");
				// nothing....

				// no allergy status observation
				List<Element> reactions = cda.getChildren(ao, "entryRelationship");
				Element pe = cda.getChild(cda.getChild(cda.getChild(ao, "participant"), "participantRole"), "playingEntity");
				Element pec = cda.getChild(pe, "code");
				if (pec == null || !Utilities.noString(pec.getAttribute("nullFlavor"))) {
					String n = cda.getChild(pe, "name").getTextContent();
					//				if (n.contains("No Known Drug Allergies") && reactions.isEmpty())
					//					ai.setSubstance(new CodeableConcept().setText(n)); // todo: what do with this? 
					//				else
					ai.setSubstance(new CodeableConcept().setText(n));
				} else
					ai.setSubstance(convert.makeCodeableConceptFromCD(pec));
				if (!reactions.isEmpty()) {
					AllergyIntoleranceEventComponent aie = ai.addEvent();
					for (Element er : reactions) {
						Element ro = cda.getChild(er, "observation");
						aie.addManifestation(convert.makeCodeableConceptFromCD(cda.getChild(ro, "value")));
					}
				}

				saveResource(ai);
			}
		}
		saveResource(list);
  }

	private void processVitalSignsSection(CDAUtilities cda, Convert convert, Element section, Context context) throws Exception {
		scanSection("Vital Signs", section);
		List_ list = new List_();
		list.setId(context.baseId+"-list-vitalsigns");
		//. list.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/list-daf-dafproblemlist"); no list 
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section, "code")));
    list.setTitle(cda.getChild(section, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setMode(ListMode.SNAPSHOT);
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
		buildNarrative(list, cda.getChild(section, "text"));

		int i = 0;
		for (Element c : cda.getChildren(section, "entry")) {
			Element org = cda.getChild(c, "organizer"); // problem concern act
			for (Element oc : cda.getChildren(org, "component")) {
				Element o = cda.getChild(oc, "observation"); // problem concern act
				Observation obs = new Observation();
				obs.setId(context.baseId+"-vitals-"+Integer.toString(i));
				obs.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/observation-daf-vitalsigns-dafvitalsigns");
				i++;
				obs.setSubject(context.subjectRef);
				obs.setEncounter(new Reference().setReference("Encounter/"+context.encounter.getId()));
				obs.setCode(convert.makeCodeableConceptFromCD(cda.getChild(o, "code")));
				
				boolean found = false;
				for (Element e : cda.getChildren(o, "id")) {
					Identifier id = convert.makeIdentifierFromII(e);
					obs.getIdentifier().add(id);
				}

				if (!found) {
					list.addEntry().setItem(new Reference().setReference("Observation/"+obs.getId()));
					obs.setStatus(ObservationStatus.FINAL);
					obs.setReliability(ObservationReliability.OK);
					obs.setApplies(convert.makeDateTimeFromTS(cda.getChild(o, "effectiveTime")));
					String v = cda.getChild(o, "value").getAttribute("value");
					if (!Utilities.IsDecimal(v)) {
						obs.setDataAbsentReason(new CodeableConcept().setText(v));
					} else
						obs.setValue(convert.makeQuantityFromPQ(cda.getChild(o, "value")));
					saveResource(obs);
				}
			}
		}
		saveResource(list);	  
  }

	private void processResultsSection(CDAUtilities cda, Convert convert, Element section, Context context) throws Exception {
	  scanSection("Results", section);
	  
		List_ list = new List_();
		list.setId(context.baseId+"-list-results");
    list.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/list-daf-dafresultlist"); 
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section, "code")));
    list.setTitle(cda.getChild(section, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setMode(ListMode.SNAPSHOT);
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
		buildNarrative(list, cda.getChild(section, "text"));

		context.obsId = 0;
		for (Element c : cda.getChildren(section, "entry")) {
			Element org = cda.getChild(c, "organizer"); 
			if (org != null) {
				Observation panel = new Observation();
				panel.setId(context.baseId+"-results-"+Integer.toString(context.obsId));
				panel.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/observation-daf-results-dafresultobspanel");
				context.obsId++;
				panel.setSubject(context.subjectRef);
				panel.setEncounter(new Reference().setReference("Encounter/"+context.encounter.getId()));
				panel.setStatus(ObservationStatus.FINAL);
				panel.setReliability(ObservationReliability.OK);
				boolean found = false;
				for (Element e : cda.getChildren(org, "id")) {
					Identifier id = convert.makeIdentifierFromII(e);
					panel.getIdentifier().add(id);
				}
				if (!found) {
					list.addEntry().setItem(new Reference().setReference("Observation/"+panel.getId()));

					panel.setCode(convert.makeCodeableConceptFromCD(cda.getChild(org, "code")));
					for (Element comp : cda.getChildren(org, "component")) {
						Observation obs = processObservation(cda, convert, context, cda.getChild(comp, "observation"));
						panel.addRelated().setType(ObservationRelationshiptypes.HASCOMPONENT).setTarget(new Reference().setReference("Observation/"+obs.getId()));
						if (!panel.hasApplies())
							panel.setApplies(obs.getApplies());
						else {
							if (!Base.compareDeep(panel.getApplies(), obs.getApplies(), false)) {
								Period p = panel.getApplies() instanceof Period ? panel.getAppliesPeriod() : new Period().setStartElement(panel.getAppliesDateTimeType()).setEndElement(panel.getAppliesDateTimeType());
								if (p.getStartElement().after(obs.getAppliesDateTimeType()))
									p.setStartElement(obs.getAppliesDateTimeType());
								if (p.getEndElement().before(obs.getAppliesDateTimeType()))
									p.setEndElement(obs.getAppliesDateTimeType());
								panel.setApplies(p);
							}
						}

					}
					saveResource(panel);
				}
			}
			Element o = cda.getChild(c, "observation"); 
			if (o != null) {
				Observation obs = processObservation(cda, convert, context, o);
				list.addEntry().setItem(new Reference().setReference("Observation/"+obs.getId()));
			}
		}
		saveResource(list);	  
  }

	private Observation processObservation(CDAUtilities cda, Convert convert, Context context, Element o) throws Exception {
		Observation obs = new Observation();
		obs.setId(context.baseId+"-results-"+Integer.toString(context.obsId));
		context.obsId++;
		obs.setSubject(context.subjectRef);
		obs.setEncounter(new Reference().setReference("Encounter/"+context.encounter.getId()));
		obs.setStatus(ObservationStatus.FINAL);
		obs.setReliability(ObservationReliability.OK);
		obs.setApplies(convert.makeDateTimeFromTS(cda.getChild(o, "effectiveTime")));
		obs.setCode(convert.makeCodeableConceptFromCD(cda.getChild(o, "code")));
		obs.setInterpretation(convert.makeCodeableConceptFromCD(cda.getChild(o, "interpretationCode")));
		Element rr = cda.getChild(o, "referenceRange");
		if (rr != null)  
			obs.addReferenceRange().setText(cda.getChild(cda.getChild(rr, "observationRange"), "text").getTextContent());
		
		Element v = cda.getChild(o, "value");
		String type = v.getAttribute("xsi:type");
		if ("ST".equals(type)) {
			obs.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/observation-daf-results-dafresultobsother");
			obs.setValue(new StringType(v.getTextContent()));
		} else if ("CD".equals(type)) {
			obs.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/observation-daf-results-dafresultobscode");
			obs.setValue(convert.makeCodeableConceptFromCD(v));
		} else if ("PQ".equals(type)) {
			obs.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/observation-daf-results-dafresultobsquantity");
			String va = cda.getChild(o, "value").getAttribute("value");
			if (!Utilities.IsDecimal(va)) {
				obs.setDataAbsentReason(new CodeableConcept().setText(va));
			} else
			  obs.setValue(convert.makeQuantityFromPQ(cda.getChild(o, "value")));
		} else
			throw new Exception("Unknown type '"+type+"'");
		
		for (Element e : cda.getChildren(o, "id")) {
			Identifier id = convert.makeIdentifierFromII(e);
			obs.getIdentifier().add(id);
		}
		saveResource(obs);
		return obs;
  }

	private void processSocialHistorySection(CDAUtilities cda, Convert convert, Element section, Context context) throws Exception {
		scanSection("Social History", section);
		int i = 0;
		for (Element c : cda.getChildren(section, "entry")) {
			Element o = cda.getChild(c, "observation");
			Observation obs = new Observation();
			obs.setId(context.baseId+"-smoking-"+(i == 0 ? "" : Integer.toString(i)));
			obs.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/observation-daf-smokingstatus-dafsmokingstatus");
			i++;
			obs.setSubject(context.subjectRef);
			obs.setEncounter(new Reference().setReference("Encounter/"+context.encounter.getId()));
			obs.setCode(convert.makeCodeableConceptFromCD(cda.getChild(o, "code")));
			
			boolean found = false;
			for (Element e : cda.getChildren(o, "id")) {
				Identifier id = convert.makeIdentifierFromII(e);
				obs.getIdentifier().add(convert.makeIdentifierFromII(e));
			}
			if (!found) {
				obs.setStatus(ObservationStatus.FINAL);
				obs.setReliability(ObservationReliability.OK);
				obs.setApplies(convert.makeDateTimeFromTS(cda.getChild(o, "effectiveTime")));
				obs.setValue(convert.makeCodeableConceptFromCD(cda.getChild(o, "value")));
				saveResource(obs);
			}
		}
  }

	private void processMedicationsSection(CDAUtilities cda, Convert convert, Element section, Context context) throws Exception {
		scanSection("Medications", section);
		List_ list = new List_();
		list.setId(context.baseId+"-list-medications");
		list.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/list-daf-dafmedicationlist");
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section, "code")));
    list.setTitle(cda.getChild(section, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setMode(ListMode.SNAPSHOT);
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
		buildNarrative(list, cda.getChild(section, "text"));

		int i = 0;
		for (Element c : cda.getChildren(section, "entry")) {
			Element sa = cda.getChild(c, "substanceAdministration"); // allergy problem act
			MedicationStatement ms = new MedicationStatement();
			ms.setId(context.baseId+"-medication-"+Integer.toString(i));
			ms.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/medicationstatement-daf-dafmedicationstatement");
			i++;
			ms.setPatient(context.subjectRef);
			
			boolean found = false;
			for (Element e : cda.getChildren(sa, "id")) {
				Identifier id = convert.makeIdentifierFromII(e);
				ms.getIdentifier().add(id);
			}
			if (!found) {
				ms.setStatus(MedicationStatementStatus.COMPLETED);
				list.addEntry().setItem(new Reference().setReference("MedicationStatement/"+ms.getId()));

				Element mm = cda.getChild(cda.getChild(cda.getChild(sa, "consumable"), "manufacturedProduct"), "manufacturedMaterial"); // allergy observation
				ms.setMedication(new Reference().setReference("#med"));
				Medication med = new Medication();
				med.setId("med");
				med.setCode(convert.makeCodeableConceptFromCD(cda.getChild(mm, "code")));
				ms.getContained().add(med);
				MedicationStatementDosageComponent dosage = ms.addDosage();
				Element qty = cda.getChild(sa, "doseQuantity"); // allergy observation
				try {
					if (cda.getChild(qty, "low") != null) {
						// todo: this is not correct?
						dosage.getExtension().add(new Extension().setUrl("http://healthintersections.com.au/fhir/extensions/medication-statement-range").setValue(convert.makeRangeFromIVLPQ(qty)));
					} else {
						dosage.setQuantity(convert.makeQuantityFromPQ(qty));
					}
				} catch (Exception e) {
					System.out.println("  invalid dose quantity '"+qty.getAttribute("value")+" "+qty.getAttribute("unit")+"' ("+e.getClass().getName()+") in "+context.baseId);
				}
				dosage.setRoute(convert.makeCodeableConceptFromCD(cda.getChild(sa, "routeCode")));
				Type t = convert.makeSomethingFromGTS(cda.getChildren(sa, "effectiveTime"));
				if (t instanceof Timing) {
					dosage.setSchedule((Timing) t);
					if (dosage.getSchedule().hasRepeat() && dosage.getSchedule().getRepeat().hasBounds())
						ms.setEffective(dosage.getSchedule().getRepeat().getBounds());
				} else if (t instanceof Period)
					ms.setEffective(t);
				else
					throw new Exception("Undecided how to handle "+t.getClass().getName());

				for (Element e : cda.getChildren(sa, "author")) {
					if (ms.hasInformationSource())
						throw new Error("additional author discovered");
					Practitioner p = processPerformer(cda, convert, context, e, "assignedAuthor", "assignedPerson");
					Reference ref = new Reference().setReference("Practitioner/"+p.getId()).setDisplay(p.getUserString("display"));
					ms.setInformationSource(ref);
					ms.setDateAssertedElement(convert.makeDateTimeFromTS(cda.getChild(e, "time")));
				}
				saveResource(ms);
			}
		}
		saveResource(list);
  }
	
	private void processEncountersSection(CDAUtilities cda, Convert convert, Element section, Context context) throws Exception {
	  scanSection("Encounters", section);
		List_ list = new List_();
		list.setId(context.baseId+"-list-encounters");
		list.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/list-daf-dafencounterlist");
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section, "code")));
    list.setTitle(cda.getChild(section, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setMode(ListMode.SNAPSHOT);
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
		buildNarrative(list, cda.getChild(section, "text"));

		int i = 0;
		for (Element c : cda.getChildren(section, "entry")) {
			Element ee = cda.getChild(c, "encounter"); // allergy problem act
			Encounter enc = new Encounter();
			enc.setId(context.baseId+"-encounter-"+Integer.toString(i));
			enc.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/encounter-daf-dafencounter");
			i++;
			enc.setPatient(context.subjectRef);
			list.addEntry().setItem(new Reference().setReference("Encounter/"+enc.getId()));
			
			for (Element e : cda.getChildren(ee, "id"))
				enc.getIdentifier().add(convert.makeIdentifierFromII(e));
			checkGenerateIdentifier(enc.getIdentifier(), enc);


			enc.setPeriod(convert.makePeriodFromIVL(cda.getChild(ee, "effectiveTime")));
			if (enc.getPeriod().hasEnd())
				enc.setStatus(EncounterState.FINISHED);
			else
				enc.setStatus(EncounterState.INPROGRESS);

			if (cda.getChild(ee, "text") != null)
				enc.setClass_(convertTextToClass(cda.getChild(ee, "text").getTextContent().trim()));
			else
				enc.setClass_(EncounterClass.OTHER); // todo: fix this
			
			CodeableConcept type = convert.makeCodeableConceptFromCD(cda.getChild(ee, "code"));
			enc.addType(type);
			
			for (Element e : cda.getChildren(ee, "performer")) {
				Practitioner p = processPerformer(cda, convert, context, e, "assignedEntity", "assignedPerson");
				Reference ref = new Reference().setReference("Practitioner/"+p.getId()).setDisplay(p.getUserString("display"));
				enc.addParticipant().setIndividual(ref).setPeriod(convert.makePeriodFromIVL(cda.getChild(e, "time")));
			}
			enc.addLocation().setLocation(new Reference().setReference("#loc"));
			Location loc = new Location();
			loc.setId("loc");
			Element pr = cda.getChild(cda.getChild(ee, "participant"), "participantRole");
			loc.setName(cda.getChild(cda.getChild(pr, "playingEntity"), "name").getTextContent());
			loc.setType(convert.makeCodeableConceptFromCD(cda.getChild(pr, "code")));
			enc.getContained().add(loc);
			saveResource(enc);
		}
		saveResource(list);
  }


	private EncounterClass convertTextToClass(String v) {
		v = v.toLowerCase();
	  if (v.equals("inpatient"))
	  	return EncounterClass.INPATIENT;
	  if (v.equals("emergency department") ||v.equals("emergency department admit decision"))
	  	return EncounterClass.EMERGENCY;
	  if (v.equals("x-ray exam"))
	  	return EncounterClass.AMBULATORY;
	  if (v.equals("outpatient"))
	  	return EncounterClass.OUTPATIENT;	  
	  throw new Error("unknown encounter type "+v);
  }

	private void processImmunizationsSection(CDAUtilities cda, Convert convert, Element section, Context context) throws Exception {
	  scanSection("Immunizations", section);	  
		List_ list = new List_();
		list.setId(context.baseId+"-list-immunizations");
		list.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/list-daf-dafimmunizationlist");
		list.setSubject(context.subjectRef);
    list.setCode(convert.makeCodeableConceptFromCD(cda.getChild(section, "code")));
    list.setTitle(cda.getChild(section, "title").getTextContent());
    list.setStatus(ListStatus.CURRENT); 
    list.setMode(ListMode.SNAPSHOT);
    list.setDateElement(context.now);
    list.setSource(context.authorRef);
		buildNarrative(list, cda.getChild(section, "text"));

		int i = 0;
		for (Element c : cda.getChildren(section, "entry")) {
			Element sa = cda.getChild(c, "substanceAdministration"); // allergy problem act
			Immunization imm = new Immunization();
			imm.setId(context.baseId+"-immunization-"+Integer.toString(i));
			imm.setUserData("profile", "http://hl7.org/fhir/StructureDefinition/immunization-daf-dafimmunization");
			i++;
			imm.setPatient(context.subjectRef);
			imm.setEncounter(new Reference().setReference("Encounter/"+context.encounter.getId()));
			imm.setWasNotGiven("true".equals(sa.getAttribute("negationInd")));
			
      boolean found = false;
			for (Element e : cda.getChildren(sa, "id")) {
				Identifier id = convert.makeIdentifierFromII(e);
				imm.getIdentifier().add(id);
			}
			if (!found) {
				list.addEntry().setItem(new Reference().setReference("Immunization/"+imm.getId()));

				imm.setDateElement(convert.makeDateTimeFromTS(cda.getChild(cda.getChild(sa, "effectiveTime"), "low")));
				if (imm.getWasNotGiven()) {
					Element reason = cda.getChild(cda.getChildByAttribute(sa, "entryRelationship", "typeCode", "RSON"), "observation");
					imm.setExplanation( new ImmunizationExplanationComponent());
					imm.getExplanation().addReasonNotGiven(convert.makeCodeableConceptFromCD(cda.getChild(reason, "code")));
				}
				Element mm = cda.getChild(cda.getChild(cda.getChild(sa, "consumable"), "manufacturedProduct"), "manufacturedMaterial");
				imm.setVaccineType(convert.makeCodeableConceptFromCD(cda.getChild(mm, "code")));
				imm.setRoute(convert.makeCodeableConceptFromCD(cda.getChild(sa, "routeCode")));
				if (cda.getChild(mm, "lotNumberText") != null)
					imm.setLotNumber(cda.getChild(mm, "lotNumberText").getTextContent());
				Element mr = cda.getChild(cda.getChild(cda.getChild(sa, "consumable"), "manufacturedProduct"), "manufacturerOrganization");
				if (mr != null)
					imm.setManufacturer(new Reference().setDisplay(cda.getChild(mr, "name").getTextContent()));

				// the problem with this is that you can't have just a dose sequence number
				//			Element subject = cda.getChild(cda.getChildByAttribute(sa, "entryRelationship", "typeCode", "SUBJ"), "observation");
				//			if (subject != null)
				//				imm.addVaccinationProtocol().setDoseSequence(Integer.parseInt(cda.getChild(subject, "value").getAttribute("value")));

				for (Element e : cda.getChildren(sa, "performer")) {
					if (imm.hasPerformer())
						throw new Error("additional performer discovered");
					Practitioner p = processPerformer(cda, convert, context, e, "assignedEntity", "assignedPerson");
					Reference ref = new Reference().setReference("Practitioner/"+p.getId()).setDisplay(p.getUserString("display"));
					imm.setPerformer(ref);
				}
				imm.setReported(!imm.hasPerformer());
				saveResource(imm);
			}
		}
		saveResource(list);
  }

	private void makeBinary(String sourceFolder, String filename, Context context) throws Exception {
	  Binary binary = new Binary();
	  binary.setId(context.baseId+"-binary");
	  binary.setContentType("application/hl7-v3+xml");
	  binary.setContent(IOUtils.toByteArray(new FileInputStream(Utilities.path(sourceFolder, filename))));
	  saveResource(binary);
  }

//  /informationRecipient: 2979
//  /informationRecipient/intendedRecipient: 2979
//  /informationRecipient/intendedRecipient/addr: 2979
//  /informationRecipient/intendedRecipient/informationRecipient: 2979
//  /informationRecipient/intendedRecipient/informationRecipient/name: 2979
//  /informationRecipient/intendedRecipient/receivedOrganization: 2979
//  /informationRecipient/intendedRecipient/receivedOrganization/addr: 2979
//  /informationRecipient/intendedRecipient/receivedOrganization/id: 2979
//  /informationRecipient/intendedRecipient/receivedOrganization/name: 2979
//  /informationRecipient/intendedRecipient/receivedOrganization/telecom: 2979

	private void makeDocumentReference(CDAUtilities cda, Convert convert, Element doc, Context context) throws Exception {
    scanSection("document", doc);
		DocumentReference ref = new DocumentReference();
		ref.setId(context.baseId+"-document");
		ref.setMasterIdentifier(convert.makeIdentifierFromII(cda.getChild(doc, "id")));
		ref.setSubject(context.subjectRef);
		ref.setType(convert.makeCodeableConceptFromCD(cda.getChild(doc, "code")));
		for (Element ti : cda.getChildren(doc, "templateId"))
			ref.addFormat("urn:oid:"+ti.getAttribute("root"));
		ref.addAuthor(context.authorRef);
		ref.setCreatedElement(convert.makeDateTimeFromTS(cda.getChild(doc, "effectiveTime")));
		ref.setIndexedElement(InstantType.now());
		ref.setStatus(DocumentReferenceStatus.CURRENT);
		ref.addConfidentiality(convert.makeCodeableConceptFromCD(cda.getChild(doc, "confidentialityCode")));
		ref.addContent().setContentType("application/hl7-v3+xml").setUrl("Binary/"+context.baseId).setLanguage(convertLanguage(cda.getChild(doc, "language")));
		ref.setContext(new DocumentReferenceContextComponent());
		ref.getContext().setPeriod(convert.makePeriodFromIVL(cda.getChild(cda.getChild(doc, "serviceEvent"), "effectiveTime")));
		for (CodeableConcept cc : context.encounter.getType())
			ref.getContext().addEvent(cc);
		ref.setDescription(cda.getChild(doc, "title").getTextContent());
		ref.setCustodian(new Reference().setReference("Organization/"+processOrganization(cda.getDescendent(doc, "custodian/assignedCustodian/representedCustodianOrganization"), cda, convert, context)));
		Practitioner p = processPerformer(cda, convert, context, cda.getChild(doc, "legalAuthenticator"), "assignedEntity", "assignedPerson");
		ref.setAuthenticator(new Reference().setReference("Practitioner/"+p.getId()).setDisplay(p.getUserString("display")));
	  saveResource(ref);
  }

	private String convertLanguage(Element child) {
		if (child == null)
			return null;
	  return child.getAttribute("code");
  }


}
