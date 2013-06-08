package org.hl7.fhir.definitions.validation;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Element;

public class InstanceValidator extends BaseValidator {

  private Definitions definitions;

  public InstanceValidator(Definitions definitions) {
    super();
    this.definitions = definitions;
  }
  
  public class ChildIterator {
    private Element parent;
    private String basePath;
    private int lastCount;
    private Element child;
    
    public ChildIterator(String path, Element elem) {
      parent = elem;
      basePath = path;  
    }

    public boolean next() {
      if (child == null) { 
        child = XMLUtil.getFirstChild(parent);
        lastCount = 0;
      } else {
        String lastName = child.getLocalName();
        child = XMLUtil.getNextSibling(child);
        if (child != null && child.getLocalName().equals(lastName)) 
          lastCount++;
        else
          lastCount = 0;
      }
      return child != null;
    }

    public String name() {
      return child.getLocalName();
    }

    public Element element() {
      return child;
    }

    public String path() {
      String sfx = "";
      Element n = XMLUtil.getNextSibling(child);
      if (n != null && n.getLocalName().equals(child.getLocalName())) { 
        sfx = "["+Integer.toString(lastCount)+"]";
      }
      return basePath+"/f:"+name()+sfx;
    }
  }

  public List<ValidationMessage> validateInstance(Element elem) throws Exception {
    errors.clear();

    if (elem.getLocalName().equals("feed")) {
      ChildIterator ci = new ChildIterator("", elem);
      while (ci.next()) {
        if (ci.name().equals("category"))
          validateTag(ci.path(), ci.element(), false);
        else if (ci.name().equals("entry"))
        validateAtomEntry(ci.path(), ci.element());
      }
    }
    else
      validate("", elem);
    
    return errors;
  }

  private void validateAtomEntry(String path, Element element) throws Exception {
    ChildIterator ci = new ChildIterator(path, element);
    while (ci.next()) {
      if (ci.name().equals("category"))
        validateTag(ci.path(), ci.element(), false);
      else if (ci.name().equals("content")) {
        Element r = XMLUtil.getFirstChild(ci.element());
        validate(ci.path()+"/f:"+r.getLocalName(), r);
      }
    }
  }

  private void validate(String path, Element elem) throws Exception {
    if (elem.getLocalName().equals("Binary"))
      validateBinary(elem);
    else {
      ResourceDefn r = definitions.getResourceByName(elem.getLocalName());
      if (rule(elem.getLocalName(), r != null, "Unknown Resource Type "+elem.getLocalName())) {
        validateElement(path+"/f:"+elem.getLocalName(), r.getRoot(), null, elem);
      }
    }
  }

  private void validateBinary(Element elem) {
    // nothing yet
    
  }

  private void validateTag(String path, Element element, boolean onEntry) {
    // nothing yet
    
  }

  private void validateElement(String path, ElementDefn defn, ElementDefn context, Element element) throws Exception {
    if (defn.getName().equals("Identifier")) 
      checkIdentifier(path, element, context);
    else if (defn.getName().equals("Coding")) 
      checkCoding(path, element, context, false);
    else if (defn.getName().equals("Quantity")) 
      checkQuantity(path, element, context, false);
    else if (defn.getName().equals("ResourceReference")) 
      checkResourceReference(path, element, context, false);
    else if (defn.getName().equals("CodeableConcept"))
      checkCodeableConcept(path, element, context);
    else if (defn.getElements().size() > 0) { // we're not going to trouble ourselves with this, we're just going to walk the definitions
      ChildIterator ci = new ChildIterator(path, element);
      while (ci.next()) {
        ElementDefn child = defn.getElementByName(ci.name());
        if (child != null) {
          if (definitions.hasElementDefn(child.typeCode())) {
            ElementDefn t = definitions.getElementDefn(child.typeCode());
            validateElement(ci.path(), t, child, ci.element());
          } else if (typeIsPrimitive(child.typeCode()))
            checkPrimitive(path, child.typeCode(), defn, ci.element());
          else
            validateElement(ci.path(), child, null, ci.element());
        } else if (ci.name().equals("extension")) {
          checkExtension(path, definitions.getElementDefn("Extension"), defn, ci.element());
        } else {
          child = defn.getElementByName(ci.name(), true);
          if (child != null) {
            String type = ci.name().substring(child.getName().length() - 3);
            if (typeIsPrimitive(type))
              checkPrimitive(path, type, defn, ci.element());
            else {
              ElementDefn t;
              if (definitions.getConstraints().containsKey(type))
                t = definitions.getElementDefn(definitions.getConstraints().get(type).getComment());
              else if (type.equals("Resource")) 
                t = definitions.getElementDefn("ResourceReference");
              else
                t = definitions.getElementDefn(type);
              
              if (rule(path, t != null, "Unknown Choice Type: "+type))
                validateElement(ci.path(), t, child, ci.element());
            }
          } else {
            if (defn.typeCode().equals("Resource"))
              child = definitions.getBaseResource().getRoot().getElementByName(ci.name());
            if (child == null) 
              warning(path, false, "Unknown Element "+ci.name());
            else if (child.typeCode().equals("Resource"))
              validateContains(ci.path(), child, defn, ci.element());
            else
              validateElement(ci.path(), child, defn, ci.element()); 
          }
        }
      }
    }
  }

  private void validateContains(String path, ElementDefn child, ElementDefn context, Element element) throws Exception {
    Element e = XMLUtil.getFirstChild(element);
    validate(path, e);    
  }

  private boolean typeIsPrimitive(String t) {
    if ("boolean".equalsIgnoreCase(t)) return true;
    if ("integer".equalsIgnoreCase(t)) return true;
    if ("decimal".equalsIgnoreCase(t)) return true;
    if ("base64Binary".equalsIgnoreCase(t)) return true;
    if ("instant".equalsIgnoreCase(t)) return true;
    if ("string".equalsIgnoreCase(t)) return true;
    if ("uri".equalsIgnoreCase(t)) return true;
    if ("date".equalsIgnoreCase(t)) return true;
    if ("date".equalsIgnoreCase(t)) return true;
    if ("dateTime".equalsIgnoreCase(t)) return true;
    if ("date".equalsIgnoreCase(t)) return true;
    if ("oid".equalsIgnoreCase(t)) return true;
    if ("uuid".equalsIgnoreCase(t)) return true;
    if ("code".equalsIgnoreCase(t)) return true;
    if ("id".equalsIgnoreCase(t)) return true;
    return false;
  }

  private void checkPrimitive(String path, String type, ElementDefn context, Element e) {
    // for nothing to check    
  }

  private void checkExtension(String path, ElementDefn elementDefn, ElementDefn context, Element e) {
    // for now, nothing to check yet
    
  }

  private void checkResourceReference(String path, Element element, ElementDefn context, boolean b) {
    // nothing to do yet
    
  }

  private void checkIdentifier(String path, Element element, ElementDefn context) {
    
  }

  private void checkQuantity(String path, Element element, ElementDefn context, boolean b) {
    String code = XMLUtil.getNamedChildValue(element,  "code");
    String system = XMLUtil.getNamedChildValue(element,  "system");
    String units = XMLUtil.getNamedChildValue(element,  "units");
    
    if (system != null && code != null) {
      checkCode(path, code, system, units);
    }
    
  }


  private void checkCoding(String path, Element element, ElementDefn context, boolean inCodeableConcept) {
    String code = XMLUtil.getNamedChildValue(element,  "code");
    String system = XMLUtil.getNamedChildValue(element,  "system");
    String display = XMLUtil.getNamedChildValue(element,  "display");
    
    if (system != null && code != null) {
      checkCode(path, code, system, display);
    }
  }

  private void checkCode(String path, String code, String system, String display) {
    if (system.startsWith("http://hl7.org/fhir")) {
      if (system.equals("http://hl7.org/fhir/sid/icd-10"))
          ; // else don't check ICD-10 (for now)
        else {
          ValueSet vs = getValueSet(system);
          if (warning(path, vs != null, "Unknown Code System "+system)) {
            ValueSetDefineConceptComponent def = getCodeDefinition(vs, code); 
            if (warning(path, def != null, "Unknown Code ("+system+"#"+code+")"))
              warning(path, display == null || display.equals(def.getDisplaySimple()), "Display should be '"+def.getDisplaySimple()+"'");
              
          }
        }
    } else if (system.startsWith("http://loinc.org")) {
      // todo: hint(path, false, "Checking codes is not done yet ("+system+"#"+code+")");
    } else if (system.startsWith("http://unitsofmeasure.org")) {
      // todo: hint(path, false, "Checking codes is not done yet ("+system+"#"+code+")");
    }
    // else ...
  }

  private ValueSetDefineConceptComponent getCodeDefinition(ValueSetDefineConceptComponent c, String code) {
    if (code.equals(c.getCodeSimple()))
      return c;
    for (ValueSetDefineConceptComponent g : c.getConcept()) {
      ValueSetDefineConceptComponent r = getCodeDefinition(g, code);
      if (r != null)
        return r;
    }
    return null;
  }
  
  private ValueSetDefineConceptComponent getCodeDefinition(ValueSet vs, String code) {
    for (ValueSetDefineConceptComponent c : vs.getDefine().getConcept()) {
      ValueSetDefineConceptComponent r = getCodeDefinition(c, code);
      if (r != null)
        return r;
    }
    return null;
  }

  private ValueSet getValueSet(String system) {
    for (ValueSet vs : definitions.getValuesets().values()) {
      if (vs.getDefine() != null && system.equals(vs.getDefine().getSystemSimple().toString()))
        return vs;
    }
    return null;
  }

  private void checkCodeableConcept(String path, Element element, ElementDefn context) {
    ChildIterator ci = new ChildIterator(path, element);
    while (ci.next()) {
      if (ci.name().equals("coding")) {
        checkCoding(ci.path(), ci.element(), context, true);
      }
      // todo: check primary
    }
    
  }
  
}

