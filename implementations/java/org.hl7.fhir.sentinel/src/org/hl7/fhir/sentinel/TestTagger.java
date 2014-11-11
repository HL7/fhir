package org.hl7.fhir.sentinel;

import java.util.List;
import java.util.Random;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Resource.ResourceMetaComponent;

public class TestTagger implements Tagger {

	Random r = new Random();
	
	@Override
	public void initialise(FHIRClient client, Conformance conf) {
	}

	@Override
	public void process(Resource entry, ResourceMetaComponent current, ResourceMetaComponent added, ResourceMetaComponent deleted) throws Exception {
		if (current.getTag().isEmpty()) {
			Coding cat = new Coding(); // todo-bundle "http://hl7.org/fhir/tag", "http://hl7.org/fhir/tools/tag/test", "Test Tag");
			added.getTag().add(cat);
		} else {
			// todo-bundle deleted.copyValues(current);
		}
	}

}
