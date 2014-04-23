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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for Atom Entries and Feeds - common properties between both 
 */
public class AtomBase {

	/**
	 * identity of this item. Feed id May have use in FHIR for documents and messages 
	 */
  private String id;
  
  /**
   * Links associated with this item. FHIR defines some uses for known links
   */
  private Map<String, String> links = new LinkedHashMap<String, String>();
  
  /**
   * Tags (e.g. scheme defined by FHIR) and other categories associated with the item  
   */
  private List<AtomCategory> tags = new ArrayList<AtomCategory>();
  
  /**
   * When this was last updated
   */
  private DateAndTime updated;
  
  /**
   * The author name. There must be an author for entries; they may inherit it from the feed. FHIR doesn't define how this must be populated
   */
  private String authorName;
  /**
   * optional URI for the author
   */
  private String authorUri;
  /**
   * optional title for the feed or entry. FHIR does not define a value
   */
  private String title;

  /**
   * @return identity of this item. Feed id May have use in FHIR for documents and messages
   */
  public String getId() {
    return id;
  }
  /**
   * @param id identity of this item. Feed id May have use in FHIR for documents and messages
   */
  public void setId(String id) {
    this.id = id;
  }
  
  /**
   * @return optional title for the feed or entry. FHIR does not define a value
   */
  public String getTitle() {
    return title;
  }
  /**
   * @param title optional title for the feed or entry. FHIR does not define a value
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return Links associated with this item. FHIR defines some uses for known links
   */
  public Map<String, String> getLinks() {
    return links;
  }
  
  /**
   * @return The author name. There must be an author for entries; they may inherit it from the feed. FHIR doesn't define how this must be populated
   */
  public String getAuthorName() {
    return authorName;
  }
  /**
   * @param authorName The author name. There must be an author for entries; they may inherit it from the feed. FHIR doesn't define how this must be populated
   */
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  
  /**
   * @return optional URI for the author
   */
  public String getAuthorUri() {
    return authorUri;
  }
  /**
   * @param authorUri optional URI for the author
   */
  public void setAuthorUri(String authorUri) {
    this.authorUri = authorUri;
  }
  
  /**
   * @return When this was last updated
   */
  public DateAndTime getUpdated() {
    return updated;
  }
  /**
   * @param updated When this was last updated
   */
  public void setUpdated(DateAndTime updated) {
    this.updated = updated;
  }
  
  /**
   * @return Tags (e.g. scheme defined by FHIR) and other categories associated with the item
   */
	public List<AtomCategory> getTags() {
		return tags;
	}

  
}
