package org.hl7.fhir.instance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import java.util.Map;

/**
 * The base element as defined in FHIR: an id attribute or property, and extensions. 
 * All FHIR classes that represent resource content inherit from this 
 */
public abstract class Element implements Serializable {

	/**
	 * 
	 */
  private static final long serialVersionUID = 1L;

	/**
	 * xml:id (or "id" in json) - the target id for internal references
	 */
	private String xmlId;
	
	/**
	 * Extensions on this element
	 */
  private List<Extension> extensions = new ArrayList<Extension>();
  
  /** extensions for convenience **/
  
  private List<String> xmlComments; // used to allow rough round-tripping of content
  private Map<String, String> tags; // allow users to add extra information to the class
 
  /**
   * @return xml:id (or "id" in json) - the target id for internal references
   */
	public String getXmlId() {
		return xmlId;
	}

	/**
	 * @param xmlId xml:id (or "id" in json) - the target id for internal references
	 */
	public void setXmlId(String xmlId) {
		this.xmlId = xmlId;
	}
	
	/**
	 * @return Extensions on this element
	 */
  public List<Extension> getExtensions() {
    return extensions;
  }
  
  /**
   * @return true if there are extensions on this element
   */
  public boolean hasExtensions() {
    return extensions.size() > 0;
  }
	
  /**
   * @param name the identity of the extension of interest
   * @return true if the named extension is on this element
   */
  public boolean hasExtension(String name) {
    if (name == null)
      return false;
    for (Extension e : extensions) {
      if (name.equals(e.getUrlSimple()))
        return true;
    }
    return false;
  }

  /**
   * @param name the identity of the extension of interest
   * @return The extension, if on this element, else null
   */
  public Extension getExtension(String name) {
    if (name == null)
      return null;
    for (Extension e : extensions) {
      if (name.equals(e.getUrlSimple()))
        return e;
    }
    return null;
  }
  
  /**
   * Supports iterating the children elements in some generic processor or browser
   * All defined children will be listed, even if they have no value on this instance
   * 
   * Note that the actual content of primitive or xhtml elements is not iterated explicitly.
   * To find these, the processing code must recognise the element as a primitive, typecast
   * the value to a {@link Type}, and examine the value
   *  
   * @return a list of all the children defined for this element
   */
  public List<Property> children() {
  	List<Property> result = new ArrayList<Property>();
  	listChildren(result);
  	return result;
  }

  /**
   * used internally when collecting the defined children for this element. overridden in descendent classes
   */
	protected void listChildren(List<Property> result) {
	// not an element  result.add(new Property("xml:id", "XML Identifier - target for an id ref", 0, 1, )))
		result.add(new Property("extension", "Extension", "XML Identifier - target for an id ref", 0, java.lang.Integer.MAX_VALUE, extensions));	  
  }

  public Property getChildByName(String name) {
    List<Property> children = new ArrayList<Property>();
    listChildren(children);
    for (Property c : children)
      if (c.getName().equals(name))
        return c;
    return null;
  }  
  
  public List<String> getXmlComments() {
    if (xmlComments == null)
      xmlComments = new ArrayList<String>();
    return xmlComments;
  }  

  public String getTag(String name) {
    if (tags == null)
      return null;
    return tags.get(name);
  }
  
  public void setTag(String name, String value) {
    if (tags == null)
      tags = new HashMap<String, String>();
    tags.put(name, value);
  }
}
