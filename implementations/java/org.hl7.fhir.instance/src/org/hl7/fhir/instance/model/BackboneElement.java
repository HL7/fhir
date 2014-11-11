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

package org.hl7.fhir.instance.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An element that is allowed to have modifier extensions on it. 
 * 
 * Technically, this is an element that is defined in a resource, and has no assigned type, but defined element children instead 
 */
public abstract class BackboneElement extends Element {

  private static final long serialVersionUID = 7949281299174185258L;
	/**
	 * Modifier extensions
	 */
  private List<Extension> modifierExtension = new ArrayList<Extension>();

  /**
   * @return Modifier extensions
   */
  public List<Extension> getModifierExtension() {
		return modifierExtension;
	}

  /**
   * @return true if there are any modifier extensions or any extensions
   */
	@Override
  public boolean hasExtensions() {
    return modifierExtension.size() > 0 || super.hasExtensions();
  }

	/**
   * @param name the identity of the extension of interest
   * @return true if the named extension is on this element (extensions or modifier extensions)
	 */
  @Override
  public boolean hasExtension(String name) {
    if (name == null)
      return false;
    for (Extension e : modifierExtension) {
      if (name.equals(e.getUrl()))
        return true;
    }
    return super.hasExtension(name);
  }

  /**
   * @param name the identity of the extension of interest
   * @return The extension, if on this element (as modifier or normal), else null
   */
  @Override
  public Extension getExtension(String name) {
    if (name == null)
      return null;
    for (Extension e : modifierExtension) {
      if (name.equals(e.getUrl()))
        return e;
    }
    return super.getExtension(name);
  }
  
  /**
   * @return true if there are any modifier extensions
   */
  public boolean hasModifierExtensions() {
    return modifierExtension.size() > 0;
  }
	
	/**
   * @param name the identity of the extension of interest
   * @return true if the named extension is on this element (modifier extensions only)
	 */
  public boolean hasModifierExtension(String name) {
    if (name == null)
      return false;
    for (Extension e : modifierExtension) {
      if (name.equals(e.getUrl()))
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
    for (Extension e : modifierExtension) {
      if (name.equals(e.getUrl()))
        return e;
    }
    return null;
  }
  
  @Override
	protected void listChildren(List<Property> result) {
    super.listChildren(result);
		result.add(new Property("modifierExtension", "Extension", "XML Identifier - target for an id ref", 0, java.lang.Integer.MAX_VALUE, modifierExtension));	  
  }  
  
  public void copyValues(BackboneElement dst) {
  	super.copyValues(dst);
  	
    dst.modifierExtension = new ArrayList<Extension>();
    for (Extension i : modifierExtension)
      dst.modifierExtension.add(i.copy());
  }


}
