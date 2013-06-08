package org.hl7.fhir.instance.model;
/*
Copyright (c) 2011-2013, HL7, Inc
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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class AtomEntry {
  private String id;
  private boolean deleted;
  private String title;
  private Map<String, String> links = new LinkedHashMap<String, String>();
  private Map<String, String> tags = new HashMap<String, String>();
  private String authorName;
  private String authorUri;
  private java.util.Calendar published;
  private java.util.Calendar updated;
  private Resource resource;
  private XhtmlNode summary;
  
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }


  public Map<String, String> getLinks() {
    return links;
  }
  
  public String getAuthorName() {
    return authorName;
  }
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  public String getAuthorUri() {
    return authorUri;
  }
  public void setAuthorUri(String authorUri) {
    this.authorUri = authorUri;
  }
  public java.util.Calendar getPublished() {
    return published;
  }
  public void setPublished(java.util.Calendar published) {
    this.published = published;
  }
  public java.util.Calendar getUpdated() {
    return updated;
  }
  public void setUpdated(java.util.Calendar updated) {
    this.updated = updated;
  }
  public Resource getResource() {
    return resource;
  }
  public void setResource(Resource resource) {
    this.resource = resource;
  }
  public XhtmlNode getSummary() {
    return summary;
  }
  public void setSummary(XhtmlNode summary) {
    this.summary = summary;
  }
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Map<String, String> getTags() {
		return tags;
	}

	
}
