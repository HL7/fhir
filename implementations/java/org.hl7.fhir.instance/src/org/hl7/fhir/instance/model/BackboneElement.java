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

package org.hl7.fhir.instance.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An element that is allowed to have modifier extensions on it. 
 * 
 * Technically, this is an element that is defined in a resource, and has no assigned type, but defined element children instead 
 */
public class BackboneElement extends Element {

	/**
	 * Modifier extensions
	 */
  private List<Extension> modifierExtensions = new ArrayList<Extension>();

  /**
   * @return Modifier extensions
   */
  public List<Extension> getModifierExtensions() {
		return modifierExtensions;
	}

  /**
   * @return true if there are any modifier extensions or any extensions
   */
	public boolean hasExtensions() {
    return modifierExtensions.size() > 0 || super.hasExtensions();
  }

	/**
   * @param name the identity of the extension of interest
   * @return true if the named extension is on this element (extensions or modifier extensions)
	 */
  public boolean hasExtension(String name) {
    if (name == null)
      return false;
    for (Extension e : modifierExtensions) {
      if (name.equals(e.getUrlSimple()))
        return true;
    }
    return super.hasExtension(name);
  }

  /**
   * @param name the identity of the extension of interest
   * @return The extension, if on this element (as modifier or normal), else null
   */
  public Extension getExtension(String name) {
    if (name == null)
      return null;
    for (Extension e : modifierExtensions) {
      if (name.equals(e.getUrlSimple()))
        return e;
    }
    return super.getExtension(name);
  }
  
  /**
   * @return true if there are any modifier extensions
   */
  public boolean hasModifierExtensions() {
    return modifierExtensions.size() > 0;
  }
	
	/**
   * @param name the identity of the extension of interest
   * @return true if the named extension is on this element (modifier extensions only)
	 */
  public boolean hasModifierExtension(String name) {
    if (name == null)
      return false;
    for (Extension e : modifierExtensions) {
      if (name.equals(e.getUrlSimple()))
        return true;
    }
    return false;
  }

  /**
   * @param name the identity of the extension of interest
   * @return The extension, if on this element (modifier only), else null
   */
  public Extension getModifierExtension(String name) {
    if (name == null)
      return null;
    for (Extension e : modifierExtensions) {
      if (name.equals(e.getUrlSimple()))
        return e;
    }
    return null;
  }
  
  @Override
	protected void listChildren(List<Property> result) {
    super.listChildren(result);
		result.add(new Property("modifierExtension", "Extension", "XML Identifier - target for an id ref", 0, java.lang.Integer.MAX_VALUE, modifierExtensions));	  
  }  
  

}
