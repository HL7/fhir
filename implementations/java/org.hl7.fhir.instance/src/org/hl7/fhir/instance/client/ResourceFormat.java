package org.hl7.fhir.instance.client;

public enum ResourceFormat {
	
    RESOURCE_XML("application/fhir+xml"),
    RESOURCE_JSON("application/fhir+json");

	
	private String header;
	
	private ResourceFormat(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return this.header;
	}

}
