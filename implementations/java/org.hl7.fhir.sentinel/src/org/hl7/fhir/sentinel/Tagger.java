package org.hl7.fhir.sentinel;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Resource.ResourceMetaComponent;

public interface Tagger {

	void initialise(FHIRClient client, Conformance conf) throws Exception;
	
	void process(Resource resource, ResourceMetaComponent meta, ResourceMetaComponent added, ResourceMetaComponent deleted) throws Exception;


}
