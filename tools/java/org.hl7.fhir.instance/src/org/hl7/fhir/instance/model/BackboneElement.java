package org.hl7.fhir.instance.model;

import java.util.ArrayList;
import java.util.List;

public class BackboneElement extends Element {

  private List<Extension> modifierExtensions = new ArrayList<Extension>();
   
  
  
  public List<Extension> getModifierExtensions() {
		return modifierExtensions;
	}

	public boolean hasExtensions() {
    return modifierExtensions.size() > 0 || super.hasExtensions();
  }
	
  public boolean hasExtension(String name) {
    if (name == null)
      return false;
    for (Extension e : modifierExtensions) {
      if (name.equals(e.getUrlSimple()))
        return true;
    }
    return super.hasExtension(name);
  }

  public Extension getExtension(String name) {
    if (name == null)
      return null;
    for (Extension e : modifierExtensions) {
      if (name.equals(e.getUrlSimple()))
        return e;
    }
    return super.getExtension(name);
  }
  
  public boolean hasModifierExtensions() {
    return modifierExtensions.size() > 0;
  }
	
  public boolean hasModifierExtension(String name) {
    if (name == null)
      return false;
    for (Extension e : modifierExtensions) {
      if (name.equals(e.getUrlSimple()))
        return true;
    }
    return false;
  }

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
