package org.hl7.fhir.instance.formats;

import java.io.OutputStream;
import java.util.List;

import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Resource;


/**
 * General interface - either an XML or JSON composer. 
 * Defined to allow a factory to create a composer of the right type
 */
public interface Composer {

	/**
	 * Compose a resource to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	public void compose(OutputStream stream, Resource resource, boolean pretty) throws Exception;

	/**
	 * Compose a bundle to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	public void compose(OutputStream stream, AtomFeed feed, boolean pretty) throws Exception;
	
	/**
	 * Compose a tag list to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	public void compose(OutputStream stream, List<AtomCategory> tags, boolean pretty) throws Exception;
	
}
