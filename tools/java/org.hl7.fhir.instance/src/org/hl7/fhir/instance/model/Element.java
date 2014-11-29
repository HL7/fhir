package org.hl7.fhir.instance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
Copyright (c) 2011+, HL7, Inc
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

import org.hl7.fhir.utilities.Utilities;

/**
 * The base element as defined in FHIR: an id attribute or property, and extensions. 
 * All FHIR classes that represent resource content inherit from this 
 */
public abstract class Element extends Base {

	/**
	 * 
	 */
  private static final long serialVersionUID = 1L;

	/**
	 * xml:id (or "id" in json) - the target id for internal references
	 */
	private String elementId;
	
	/**
	 * Extensions on this element
	 */
  private List<Extension> extension;
  
  /**
   * Round tracking xml comments for testing convenience
   */
  private List<String> formatComments; // used to allow rough round-tripping of content
 
  /**
   * @return xml:id (or "id" in json) - the target id for internal references
   */
	public String getElementId() {
		return elementId;
	}

	/**
	 * @param xmlId xml:id (or "id" in json) - the target id for internal references
	 */
	public void setElementId(String xmlId) {
		this.elementId = xmlId;
	}
	
	/**
	 * @return Extensions on this element
	 */
  public List<Extension> getExtension() {
  	if (extension == null)
  		extension = new ArrayList<Extension>();
    return extension;
  }
  
  /**
   * @return true if there are extensions on this element
   */
  public boolean hasExtension() {
    return extension != null && extension.size() > 0;
  }
	
  /**
   * @return true if there are extensions on this element
   */
  public boolean hasExtensions() {
    return extension != null && extension.size() > 0;
  }
	
  /**
   * @param name the identity of the extension of interest
   * @return true if the named extension is on this element
   */
  public boolean hasExtension(String name) {
    if (name == null || extension == null)
      return false;
    for (Extension e : extension) {
      if (name.equals(e.getUrl()))
        return true;
    }
    return false;
  }

  /**
   * @param name the identity of the extension of interest
   * @return The extension, if on this element, else null
   */
  public Extension getExtension(String name) {
    if (name == null || extension == null)
      return null;
    for (Extension e : extension) {
      if (name.equals(e.getUrl()))
        return e;
    }
    return null;
  }
  
  public void setStringExtension(String uri, String value) {
  	if (extension == null)
  		extension = new ArrayList<Extension>();
    Extension ext = getExtension(uri);
    if (ext != null)
      ext.setValue(new StringType(value));
    else
      extension.add(new Extension(new UriType(uri)).setValue(new StringType(value)));
  }

  /**
   * used internally when collecting the defined children for this element. overridden in descendent classes
   */
  @Override
	protected void listChildren(List<Property> result) {
	// not an element  result.add(new Property("xml:id", "XML Identifier - target for an id ref", 0, 1, )))
		result.add(new Property("extension", "Extension", "XML Identifier - target for an id ref", 0, java.lang.Integer.MAX_VALUE, extension));	  
  }  
  
  
  public boolean hasFormatComment() {
  	return (formatComments != null && !formatComments.isEmpty());
  }
  
  public List<String> getFormatComments() {
    if (formatComments == null)
    	formatComments = new ArrayList<String>();
    return formatComments;
  }  


  public void copyValues(Element dst) {
  	dst.elementId = elementId;
  	
  	if (extension != null) {
    dst.extension = new ArrayList<Extension>();
    for (Extension i : extension)
      dst.extension.add(i.copy());
  	}
    if (formatComments == null || formatComments.isEmpty())
    	dst.formatComments = null;
    else { 
      dst.formatComments = new ArrayList<String>();
      dst.formatComments.addAll(formatComments);    	
    }
  }
  
	public boolean isEmpty() {
	  return elementId == null && (extension == null || extension.isEmpty());
  }

	public boolean hasElementId() {
	  return !Utilities.noString(elementId);
  }

}
