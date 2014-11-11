package org.hl7.fhir.instance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Base implements Serializable {

	
  private Map<String, Object> tags; // allow users to add extra information to the class
  
  public Object getTag(String name) {
    if (tags == null)
      return null;
    return tags.get(name);
  }
  
  public void setTag(String name, Object value) {
    if (tags == null)
      tags = new HashMap<String, Object>();
    tags.put(name, value);
  }

	protected abstract void listChildren(List<Property> result) ;

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

  public Property getChildByName(String name) {
    List<Property> children = new ArrayList<Property>();
    listChildren(children);
    for (Property c : children)
      if (c.getName().equals(name))
        return c;
    return null;
  }  
  
  public List<Base> listChildrenByName(String name) {
    List<Property> children = new ArrayList<Property>();
    listChildren(children);
    for (Property c : children)
      if (c.getName().equals(name) || (c.getName().endsWith("[x]") && name.startsWith(c.getName())))
        return c.values;
    return new ArrayList<Base>();
  }  
  
}
