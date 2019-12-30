package org.hl7.fhir.definitions.uml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.uml.UMLClass.UMLClassType;
import org.hl7.fhir.utilities.Utilities;

public class UMLPackage extends UMLEntity {

  public class UMLClassSorter implements Comparator<UMLClass> {

    @Override
    public int compare(UMLClass c0, UMLClass c1) {
      String n0 = c0.getName();
      String n1 = c1.getName();
      if (Utilities.existsInList(n0, "DataType", "PrimitiveType")) {
        n0 = "AAAAA";
      }
      if (Utilities.existsInList(n1, "DataType", "PrimitiveType")) {
        n1 = "AAAAA";
      }
      return n0.compareTo(n1);
    }
  }

  public class UMLEnumerationSorter implements Comparator<UMLEnumeration> {

    @Override
    public int compare(UMLEnumeration c0, UMLEnumeration c1) {
      String n0 = c0.getName();
      String n1 = c1.getName();
      return n0.compareTo(n1);
    }
  }

  public class UMLPrimitiveSorter implements Comparator<UMLPrimitive> {

    @Override
    public int compare(UMLPrimitive c0, UMLPrimitive c1) {
      String n0 = c0.getName();
      String n1 = c1.getName();
      return n0.compareTo(n1);
    }
  }

  private Map<String, UMLType> types = new HashMap<>();
  private Map<String, UMLDiagram> diagrams = new HashMap<>();
  
  public UMLPackage(String name) {
    super(name);
  }

  public Map<String, UMLType> getTypes() {
    return types;
  }
  public Map<String, UMLDiagram> getDiagrams() {
    return diagrams;
  }
  
  /** 
   * returns a list of of class for a parent (or null), sorted by name (except that DataType & PrimitiveType come first)
   * 
   * @param parent
   * @return
   */
  public List<UMLClass> getClassesByParent(UMLType parent) {
    List<UMLClass> res = new ArrayList<>();
    for (UMLType t : types.values()) {
      if (t instanceof UMLClass) {
        UMLClass c = (UMLClass) t;
        if (c.getSpecialises() == parent) {
          res.add(c);
        }
      }      
    }
    Collections.sort(res, new UMLClassSorter());
    return res;
  }
  
  public List<UMLEnumeration> getEnums() {
    List<UMLEnumeration> res = new ArrayList<>();
    for (UMLType t : types.values()) {
      if (t instanceof UMLEnumeration) {
        UMLEnumeration c = (UMLEnumeration) t;
        res.add(c);
      }
    }
    Collections.sort(res, new UMLEnumerationSorter());
    return res;
  }
      
  public List<UMLPrimitive> getPrimitives() {
    List<UMLPrimitive> res = new ArrayList<>();
    for (UMLType t : types.values()) {
      if (t instanceof UMLPrimitive) {
        UMLPrimitive c = (UMLPrimitive) t;
        res.add(c);
      }
    }
    Collections.sort(res, new UMLPrimitiveSorter());
    return res;
  }

  public UMLClass getClassByName(String name) {
    UMLType t = types.get(name);
    if (t != null && t instanceof UMLClass) {
      return (UMLClass) t;
    }
    throw new Error("UML Class "+name+" not found");
  }

  public boolean hasPrimitive(String name) {
    UMLType t = types.get(name);
    return (t != null && t instanceof UMLPrimitive);
  }

  public boolean hasClass(String name) {
    UMLType t = types.get(name);
    return (t != null && t instanceof UMLClass);
  }

  public UMLClass getClassByNameCreate(String name) {
    UMLType t = types.get(name);
    if (t != null && t instanceof UMLClass) {
      return (UMLClass) t;
    }
    if (t == null) {
      UMLClass c = new UMLClass(name, UMLClassType.Class);
      types.put(name, c);
      return c;
    }
    throw new Error("UML Class "+name+" not found");
  }

}
