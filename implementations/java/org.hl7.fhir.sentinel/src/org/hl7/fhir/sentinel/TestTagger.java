package org.hl7.fhir.sentinel;

import java.util.Random;

import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Meta;
import org.hl7.fhir.instance.model.Resource;

public class TestTagger implements Tagger {

	Random r = new Random();
	
	@Override
	public void initialise(IFHIRClient client, Conformance conf) {
	}

	@Override
	public void process(String base, Resource entry, Meta current, Meta added, Meta deleted) throws Exception {
		if (current.getTag().isEmpty()) {
			Coding cat = new Coding(); // todo-bundle "http://hl7.org/fhir/tag", "http://hl7.org/fhir/tools/tag/test", "Test Tag");
			added.getTag().add(cat);
		} else {
			// todo-bundle deleted.copyValues(current);
		}
	}

}
