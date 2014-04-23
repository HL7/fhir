package org.hl7.fhir.sentinel.taggers.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.sentinel.Tagger;

public class ProfileTagger implements Tagger {

	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	private FHIRClient client;
	
	@Override
	public void initialise(FHIRClient client, Conformance conf) {
		this.client = client;
		
		// no parameters, search all
//		AtomFeed all = client.searchAll(ValueSet.class, new HashMap<String, String>()); 
//    for (AtomEntry<? extends Resource> ae : all.getEntryList()) {
//    	profiles.put(ae.getId(), (Profile) ae.getResource());
//    }
	}

	@Override
	public void process(Resource resource, List<AtomCategory> tags, List<AtomCategory> added, List<AtomCategory> deleted) {
//
//		for (String n : profiles.keySet()) {
//			Profile p = profiles.get(n);
//			InstanceValidator v = new InstanceValidator();
//			v.validateInstanceByProfile(errors, root, profile)
//			
//		}
	}

}
