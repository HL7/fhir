package org.hl7.fhir.sentinel;

import java.util.List;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Resource;

public interface Tagger {

	void initialise(FHIRClient client, Conformance conf) throws Exception;
	
	void process(AtomEntry<? extends Resource> entry, List<AtomCategory> tags, List<AtomCategory> added, List<AtomCategory> deleted) throws Exception;


}
