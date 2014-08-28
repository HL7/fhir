package org.hl7.fhir.instance.formats;

import java.util.List;

import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Resource;

public class ResourceOrFeed {
	private Resource resource;
	private AtomFeed feed;
	private List<AtomCategory> taglist;

	public ResourceOrFeed() {
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public AtomFeed getFeed() {
		return feed;
	}

	public void setFeed(AtomFeed feed) {
		this.feed = feed;
	}

	public List<AtomCategory> getTaglist() {
		return taglist;
	}

	public void setTaglist(List<AtomCategory> taglist) {
		this.taglist = taglist;
	}
	
	
}