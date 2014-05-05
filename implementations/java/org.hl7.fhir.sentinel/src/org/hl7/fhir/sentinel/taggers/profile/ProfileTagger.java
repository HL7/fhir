package org.hl7.fhir.sentinel.taggers.profile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.AtomBase;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.instance.validation.ValidationErrorHandler;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.sentinel.Tagger;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmlpull.v1.builder.XmlComment;

public class ProfileTagger implements Tagger {

	private static final String HL7_PREFIX = "http://hl7.org/fhir/profile";
	private Map<String, AtomEntry<? extends Resource>> cache = new HashMap<String, AtomEntry<? extends Resource>>();
	private FHIRClient client;
	private InstanceValidator validator;
	
	@Override
	public void initialise(FHIRClient client, Conformance conf) throws Exception {
		this.client = client;

		validator = new InstanceValidator("C:\\work\\org.hl7.fhir\\build\\publish\\validation.zip", null, null);

	  String next = null;
	  int i = 1;
	  do {
	      System.out.println("Downloading ValueSets (Page "+Integer.toString(i)+")"+(next != null ? " ("+next+")" : "')"));
	      AtomFeed feed = null;
	      if (next != null)
	        feed = client.fetchFeed(next);
	      else
	        feed = client.history(ValueSet.class);
	      for (AtomEntry<? extends Resource> e : feed.getEntryList())
	      	seeValueSet(e);
        next = feed.getLinks().get("next");
	      i++;
	  } while (next != null);

	  next = null;
	  i = 1;
	  do {
	      System.out.println("Downloading Profiles (Page "+Integer.toString(i)+")"+(next != null ? " ("+next+")" : "')"));
	      AtomFeed feed = null;
	      if (next != null)
	        feed = client.fetchFeed(next);
	      else
	        feed = client.history(Profile.class);
	      for (AtomEntry<? extends Resource> e : feed.getEntryList())
	      	seeProfile(e);
        next = feed.getLinks().get("next");
	      i++;
	  } while (next != null);

	}

	
	private void seeValueSet(AtomEntry<? extends Resource> e) throws Exception {
		ValueSet vs = (ValueSet) e.getResource();
		if (isValidAgainstBase(vs, loadAsXml(vs), e.getId())) {
			AtomEntry<? extends Resource> cached = cache.get(e.getId());
			if (cached == null || cached.getUpdated().before(e.getUpdated())) {
				cache.put(e.getId(), e);
				validator.seeValueSet(e.getId(), vs);
			}	  
		}
  }

	private void seeProfile(AtomEntry<? extends Resource> e) throws Exception {
		Profile p = (Profile) e.getResource();
		if (isValidAgainstBase(p, loadAsXml(p), e.getId())) {
			AtomEntry<? extends Resource> cached = cache.get(e.getId());
			if (cached == null || cached.getUpdated().before(e.getUpdated())) {
				cache.put(e.getId(), e);
				validator.seeProfile(e.getId(), p);
			}
		}
  }

	@Override
	public void process(AtomEntry<? extends Resource> entry, List<AtomCategory> tags, List<AtomCategory> added, List<AtomCategory> deleted) throws Exception {
			if (entry.getResource() instanceof Profile)
				seeProfile(entry);
			if (entry.getResource() instanceof ValueSet)
				seeValueSet(entry);

			Document doc = loadAsXml(entry.getResource());

			if (!isValidAgainstBase(entry.getResource(), doc, entry.getId()))
				added.add(new AtomCategory("http://hl7.org/fhir/tag", "http://www.healthintersections.com.au/fhir/tags/invalid", "Non-conformant Resource"));
			
			for (String n : validator.getTypes().keySet()) {
				Profile p = validator.getTypes().get(n);

				if (p.getIdentifierSimple() != null && !p.getIdentifierSimple().equals("http://hl7.org/fhir/profile/"+doc.getDocumentElement().getLocalName().toLowerCase())) {
					boolean valid = check(doc, p, null);
					if (valid) 
						added.add(new AtomCategory("http://hl7.org/fhir/tag/profile", n, "Profile "+p.getNameSimple()));
				}
			}
	}


	private boolean check(Document doc, Profile p, String id) throws Exception {
	  List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
	  validator.validateInstanceByProfile(errors, doc.getDocumentElement(), p);
	  int i = 0;
	  for (ValidationMessage e : errors) {
	  	if (e.getLevel() == IssueSeverity.error || e.getLevel() == IssueSeverity.fatal) {
	  		i++;
	  	  if (id != null)
	  		  System.out.println(id+": "+e.getLevel().toCode()+" - "+e.getMessage()+" (@"+e.getLocation()+")");
	  	}
	  }
	  boolean valid = i == 0;
	  return valid;
  }

	private boolean isValidAgainstBase(Resource r, Document doc, String id) throws Exception {
	  String name = doc.getDocumentElement().getLocalName();
	  Profile p = validator.getTypes().get(name.toLowerCase());
	  new XmlComposer().compose(new FileOutputStream("c:\\temp\\resource.xml"), r, true);
	  if (p != null)
	  	new XmlComposer().compose(new FileOutputStream("c:\\temp\\profile.xml"), p, true);
	  
	  return p != null && check(doc, p, id);
  }


	private Document loadAsXml(Resource res) throws Exception {
	  XmlComposer xml = new XmlComposer();
	  ByteArrayOutputStream stream = new ByteArrayOutputStream(); 
	  xml.compose(stream, res, false);
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  factory.setNamespaceAware(true);
	  DocumentBuilder builder = factory.newDocumentBuilder();
	  Document doc = builder.parse(new ByteArrayInputStream(stream.toByteArray()));
	  return doc;
  }

}
