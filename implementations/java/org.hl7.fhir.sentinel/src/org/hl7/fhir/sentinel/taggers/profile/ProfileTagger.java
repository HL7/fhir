package org.hl7.fhir.sentinel.taggers.profile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Resource.ResourceMetaComponent;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ResourceUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.sentinel.Tagger;
import org.w3c.dom.Document;

public class ProfileTagger implements Tagger {

	private Map<String, Resource> cache = new HashMap<String, Resource>();
	private InstanceValidator validator;
	
	@Override
	public void initialise(IFHIRClient client, Conformance conf) throws Exception {

		validator = new InstanceValidator(WorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation.zip"));

	  String next = null;
	  int i = 1;
	  do {
	      System.out.println("Downloading ValueSets (Page "+Integer.toString(i)+")"+(next != null ? " ("+next+")" : ""));
	      Bundle feed = null;
	      if (next != null)
	        feed = client.fetchFeed(next);
	      else
	        feed = client.history(ValueSet.class);
	      for (BundleEntryComponent e : feed.getEntry())
	      	seeValueSet(e.hasBase() ? e.getBase() : feed.getBase(), (ValueSet) e.getResource());
        next = ResourceUtilities.getLink(feed, "next");
	      i++;
	  } while (next != null);

	  next = null;
	  i = 1;
	  do {
	      System.out.println("Downloading Profiles (Page "+Integer.toString(i)+")"+(next != null ? " ("+next+")" : ""));
	      Bundle feed = null;
	      if (next != null)
	        feed = client.fetchFeed(next);
	      else
	        feed = client.history(Profile.class);
	      for (BundleEntryComponent e : feed.getEntry())
	      	seeProfile(e.hasBase() ? e.getBase() : feed.getBase(), (Profile) e.getResource());
        next = ResourceUtilities.getLink(feed, "next");
	      i++;
	  } while (next != null);

	}

	
	@SuppressWarnings("unchecked")
  private void seeValueSet(String base, ValueSet vs) throws Exception {
		if (isValidAgainstBase(vs, loadAsXml(vs), vs.getId())) {
			Resource cached = cache.get(vs.getId());
			if (cached == null || cached.getMeta().getLastUpdated().before(vs.getMeta().getLastUpdated())) {
				cache.put(vs.getId(), vs);
				validator.getContext().seeValueSet(base, (ValueSet) vs);
			}	  
		}
  }

	@SuppressWarnings("unchecked")
  private void seeProfile(String base, Profile p) throws Exception {
		if (isValidAgainstBase(p, loadAsXml(p), p.getId())) {
			Resource cached = cache.get(p.getResourceType().toString()+"#"+ p.getId());
			if (cached == null || cached.getMeta().getLastUpdated().before(p.getMeta().getLastUpdated())) {
				cache.put(p.getResourceType().toString()+"#"+ p.getId(), p);
				validator.getContext().seeProfile(base, (Profile) p);
			}
		}
  }

	@Override
	public void process(String base, Resource entry, ResourceMetaComponent tags, ResourceMetaComponent added, ResourceMetaComponent deleted) throws Exception  {
		if (entry instanceof Profile)
			seeProfile(base, (Profile) entry);
		if (entry instanceof ValueSet)
			seeValueSet(base, (ValueSet) entry);

		Document doc = loadAsXml(entry);

		try {
			if (!isValidAgainstBase(entry, doc, entry.getId()))
				added.getTag().add(new Coding()); // todo-bundle "http://hl7.org/fhir/tag", "http://www.healthintersections.com.au/fhir/tags/invalid", "Non-conformant Resource"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String n : validator.getContext().getProfiles().keySet()) {
			Profile p = validator.getContext().getProfiles().get(n);

			try {
				if (p.getUrl() != null && !p.getUrl().equals("http://hl7.org/fhir/profile/"+doc.getDocumentElement().getLocalName().toLowerCase())) {
					boolean valid = check(doc, p, null);
					if (valid) 
						added.getTag().add(new Coding()); // todo-bundle "http://hl7.org/fhir/tag/profile", n, "Profile "+p.getName()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private boolean check(Document doc, Profile p, String id) throws Exception {
	  List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
	  validator.validate(errors, doc.getDocumentElement(), p);
	  int i = 0;
	  for (ValidationMessage e : errors) {
	  	if (e.getLevel() == IssueSeverity.ERROR || e.getLevel() == IssueSeverity.FATAL) {
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
	  Profile p = validator.getContext().getProfiles().get(name);
	  if (p == null)
	  	throw new Exception("unable to find Profile for "+name);
	  new XmlParser().compose(new FileOutputStream("c:\\temp\\resource.xml"), r, true);
	  if (p != null)
	  	new XmlParser().compose(new FileOutputStream("c:\\temp\\profile.xml"), p, true);
	  
	  return p != null && check(doc, p, id);
  }


	private Document loadAsXml(Resource res) throws Exception {
		XmlParser xml = new XmlParser();
	  ByteArrayOutputStream stream = new ByteArrayOutputStream(); 
	  xml.compose(stream, res, false);
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  factory.setNamespaceAware(true);
	  DocumentBuilder builder = factory.newDocumentBuilder();
	  Document doc = builder.parse(new ByteArrayInputStream(stream.toByteArray()));
	  return doc;
  }

}
