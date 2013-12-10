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

import org.hl7.fhir.utilities.xhtml.XhtmlNode;

/**
 * A resource in a bundle
 */
public class AtomEntry<T extends Resource> extends AtomBase {
	
  /**
   * If this is marked as a deleted resource (in history feeds)
   */
	private boolean deleted;
	
	/**
	 * Date this resource was published (no particular use in the FHIR specification)
	 */
  private DateAndTime published;
  
  /**
   * The resource itself
   */
  private T resource;
  
  /**
   * A summary of the resource - usually extracted from the narrative in the resource
   */
  private XhtmlNode summary;
  
  /**
   * @return Date this resource was published (no particular use in the FHIR specification)
   */
  public DateAndTime getPublished() {
    return published;
  }
  
  /**
   * @param published Date this resource was published (no particular use in the FHIR specification)
   */
  public void setPublished(DateAndTime published) {
    this.published = published;
  }
  
  /**
   * @return  The resource itself
   */
  public T getResource() {
    return resource;
  }
  /**
   * @param resource The resource itself
   */
  public void setResource(T resource) {
    this.resource = resource;
  }
  
  /**
   * @return A summary of the resource - usually extracted from the narrative in the resource
   */
  public XhtmlNode getSummary() {
    return summary;
  }
  /**
   * @param summary A summary of the resource - usually extracted from the narrative in the resource
   */
  public void setSummary(XhtmlNode summary) {
    this.summary = summary;
  }
  
  /**
   * @return If this is marked as a deleted resource (in history feeds)
   */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * @param deleted  If this is marked as a deleted resource (in history feeds)
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
