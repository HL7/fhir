package org.hl7.fhir.instance.client;

/**
 * Enumeration for preferred AtomFeed resource formats.
 * 
 * @author Claude Nanjo
 *
 */
public enum FeedFormat {
    FEED_XML("application/atom+xml"),
    FEED_JSON("application/fhir+json");

	
	private String header;
	
	private FeedFormat(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return this.header;
	}

}
