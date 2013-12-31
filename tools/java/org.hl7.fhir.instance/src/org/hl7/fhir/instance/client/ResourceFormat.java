package org.hl7.fhir.instance.client;

/**
 * Enumeration for preferred FHIR resource formats.
 * 
 * @author Claude Nanjo
 *
 */
public enum ResourceFormat {
	
    RESOURCE_XML("application/xml+fhir"),
    RESOURCE_JSON("application/json+fhir");

	
	private String header;
	
	private ResourceFormat(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return this.header;
	}

}
