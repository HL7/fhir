package org.hl7.fhir.sentinel;

import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.MetaInformation;
import org.hl7.fhir.instance.model.Resource;


public interface Tagger {

	void initialise(IFHIRClient client, Conformance conf) throws Exception;
	
	void process(String base, Resource resource, MetaInformation meta, MetaInformation added, MetaInformation deleted) throws Exception;


}
