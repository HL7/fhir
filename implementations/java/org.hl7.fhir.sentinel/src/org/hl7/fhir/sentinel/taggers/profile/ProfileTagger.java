package org.hl7.fhir.sentinel.taggers.profile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.AtomBase;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
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
import org.xmlpull.v1.builder.XmlComment;

public class ProfileTagger implements Tagger {

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

	
	private void seeValueSet(AtomEntry<? extends Resource> e) {
		AtomEntry<? extends Resource> cached = cache.get(e.getId());
		if (cached == null || cached.getUpdated().before(e.getUpdated())) {
			cache.put(e.getId(), e);
			validator.seeValueSet(e.getId(), (ValueSet) e.getResource());
		}	  
  }

	private void seeProfile(AtomEntry<? extends Resource> e) {
		AtomEntry<? extends Resource> cached = cache.get(e.getId());
		if (cached == null || cached.getUpdated().before(e.getUpdated())) {
			cache.put(e.getId(), e);
			validator.seeProfile(e.getId(), (Profile) e.getResource());
		}
  }

	@Override
	public void process(AtomEntry<? extends Resource> entry, List<AtomCategory> tags, List<AtomCategory> added, List<AtomCategory> deleted) throws Exception {
			if (entry.getResource() instanceof Profile)
				seeProfile(entry);
			if (entry.getResource() instanceof ValueSet)
				seeValueSet(entry);

			XmlComposer xml = new XmlComposer();
			ByteArrayOutputStream stream = new ByteArrayOutputStream(); 
			xml.compose(stream, entry.getResource(), false);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(stream.toByteArray()));

			for (String n : validator.getTypes().keySet()) {
				Profile p = validator.getTypes().get(n);

				List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
				validator.validateInstanceByProfile(errors, doc.getDocumentElement(), p);

			}
	}

}
