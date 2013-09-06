package org.hl7.fhir.instance.formats;

import java.io.OutputStream;

import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Resource;

public interface Composer {

	public void compose(OutputStream stream, Resource resource, boolean pretty) throws Exception;

	public void compose(OutputStream stream, AtomFeed feed, boolean pretty) throws Exception;
	
}
