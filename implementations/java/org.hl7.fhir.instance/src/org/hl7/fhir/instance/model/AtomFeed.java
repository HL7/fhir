package org.hl7.fhir.instance.model;
/*
Copyright (c) 2011-2014, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

 */

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.formats.FormatUtilities;

/**
 * A FHIR bundle, as represented in XML (e.g. Atom) or JSON
 */
public class AtomFeed extends AtomBase {
	
	/**
	 * When searching, the total number of resources in the search (>= the number of resources found in the search, but this may be < the number of resources in this bundle, if included resources are also present)
	 */
	private java.lang.Integer totalResults;

	/**
	 * The resources in this bundle
	 */
	private List<AtomEntry<? extends Resource>> entryList = new ArrayList<AtomEntry<? extends Resource>>();

	/**
	 * @return The resources in this bundle
	 */
	public List<AtomEntry<? extends Resource>> getEntryList() {
		return entryList;
	}

	/**
	 * @return When searching, the total number of resources in the search
	 */
	public java.lang.Integer getTotalResults() {
		return this.totalResults;
	}
	
	/** 
	 * @param totalResults When searching, the total number of resources in the search
	 */
	public void setTotalResults(java.lang.Integer totalResults) {
		this.totalResults = totalResults;
	}

  public AtomEntry<? extends Resource> getById(String id) {
    for (AtomEntry<? extends Resource> e : entryList) {
      if (e.getId().equals(id))
        return e;
    }
    return null;
  }

  public boolean isDocument() {
    return hasTag("http://hl7.org/fhir/tag", "http://hl7.org/fhir/tag/document");
  }

  private boolean hasTag(String scheme, String term) {
    for (AtomCategory tag : getTags()) {
      if (scheme.equals(tag.getScheme()) && term.equals(tag.getTerm()))
        return true;
    }
    return false;
  }

	public String getLogicalId(AtomEntry<? extends Resource> ae) throws Exception {
		if (ae.getId() == null)
	    throw new Exception("Unable to determine logical id for unknown resource");
		
	  if (getLinks().containsKey("fhir-base") && ae.getId().startsWith(getLinks().get("fhir-base"))) {
	  	String tail = ae.getId().substring((getLinks().get("fhir-base")+"/"+ae.getResource().getResourceType().toString()).length());
	  	if (tail.startsWith("/"))
	  		tail = tail.substring(1);
	  	if (FormatUtilities.isValidId(tail))
	  		return tail;
	  	else
		    throw new Exception("Unable to determine logical id for resource "+ae.getId());	  		
	  } else if (ae.getId().contains("/")) {
	  	String tail = ae.getId().substring(ae.getId().lastIndexOf("/")+1);
	  	if (FormatUtilities.isValidId(tail))
	  		return tail;
	  	else
		    throw new Exception("Unable to determine logical id for resource "+ae.getId());	  			  	
	  } else
	    throw new Exception("Unable to determine logical id for resource "+ae.getId());
  }

	public String getVersionId(AtomEntry<? extends Resource> ae) throws Exception {
	  String self = ae.getLinks().get("self");
	  if (self == null)
	    throw new Exception("Unable to determine version id for resource "+ae.getId());
	  String id = getLogicalId(ae);
	  if (!self.contains("/"+id+"/_history/"))
	    throw new Exception("Unable to determine version id for resource "+ae.getId()+" (self = "+self+")");
	  String vid = self.substring(self.lastIndexOf("/"+id+"/_history/")+("/"+id+"/_history/").length());
  	if (FormatUtilities.isValidId(vid))
  		return vid;
  	else
	    throw new Exception("Unable to determine version id for resource "+ae.getId()+" (self = "+self+")");
  }

}
