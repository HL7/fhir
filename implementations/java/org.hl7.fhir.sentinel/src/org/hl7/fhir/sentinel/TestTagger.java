package org.hl7.fhir.sentinel;

import java.util.List;
import java.util.Random;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Resource;

public class TestTagger implements Tagger {

	Random r = new Random();
	
	@Override
	public void initialise(FHIRClient client, Conformance conf) {
	}

	@Override
	public void process(Resource resource, List<AtomCategory> tags, List<AtomCategory> added, List<AtomCategory> deleted) {
		if (tags.isEmpty()) {
			AtomCategory cat = new AtomCategory("http://hl7.org/fhir/tag", "http://hl7.org/fhir/tools/tag/test", "Test Tag");
			added.add(cat);
		} else {
			deleted.addAll(tags);
		}
	}

}
