package org.hl7.fhir.definitions.validation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Element;

public class InstanceValidator extends BaseValidator {

  private Map<String, Profile> types = new HashMap<String, Profile>();
  private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();
  private Map<String, ValueSet> codesystems = new HashMap<String, ValueSet>();
   
  public InstanceValidator(String validationZip) throws Exception {
    super();
    loadValidationResources(validationZip);
  }  
  
  private void loadValidationResources(String name) throws Exception {
    ZipInputStream zip = new ZipInputStream(new FileInputStream(name));
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      if (ze.getName().endsWith(".xml")) {
        XmlParser xml = new XmlParser();
        AtomFeed f = xml.parseGeneral(zip).getFeed();
        for (AtomEntry e : f.getEntryList()) {
          if (e.getId() == null) {
            System.out.println("unidentified resource "+e.getLinks().get("self")+" in "+ze.getName());
          }
          Resource r = e.getResource();
          if (r instanceof Profile) {
            Profile p = (Profile) r;
            if (p.getStructure().get(0).getName() != null)
              types.put(p.getStructure().get(0).getNameSimple().toLowerCase(), p);
            else 
              types.put(p.getStructure().get(0).getTypeSimple().toLowerCase(), p);
          }
          if (r instanceof ValueSet) {
            ValueSet vs = (ValueSet) r;
            valuesets.put(vs.getIdentifierSimple(), vs);
            if (vs.getDefine() != null) {
              codesystems.put(vs.getDefine().getSystemSimple().toString(), vs);
            }
          }
        }
      }
      zip.closeEntry();
    }
    zip.close();    
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
      ProfileStructureComponent s = getResourceForProfile(elem.getLocalName());
      if (rule(elem.getLocalName(), s != null, "Unknown Resource Type "+elem.getLocalName())) {
        validateElement(s, path+"/f:"+elem.getLocalName(), s.getElement().get(0), null, elem);
      }
    }
  }

  private ProfileStructureComponent getResourceForProfile(String localName) throws Exception {
    Profile r = (Profile) getResource(localName);
    if (r == null)
      return null;
    if (r.getStructure().size() != 1 || !(r.getStructure().get(0).getTypeSimple().equals(localName) || r.getStructure().get(0).getNameSimple().equals(localName)))
      throw new Exception("unexpected profile contents");
    ProfileStructureComponent s = r.getStructure().get(0);
    return s;
  }

  private Resource getResource(String id) {
    return types.get(id.toLowerCase());
  }

  private void validateBinary(Element elem) {
    // nothing yet
    
  }

  private void validateTag(String path, Element element, boolean onEntry) {
    // nothing yet
    
  }

  private void validateElement(ProfileStructureComponent structure, String path, ElementComponent definition, ElementComponent context, Element element) throws Exception {
    Map<String, ElementComponent> children = getChildren(structure, definition);
    ChildIterator ci = new ChildIterator(path, element);
    while (ci.next()) {
      ElementComponent child = children.get(ci.name());
      String type = null;
      if (ci.name().equals("extension")) {
        type = "Extension";
        child = definition; // it's going to be used as context below
      } else if (child == null) {
        child = getDefinitionByTailNameChoice(children, ci.name());
        if (child != null)
          type = ci.name().substring(tail(child.getPathSimple()).length() - 3);
        if ("Resource".equals(type))
          type = "ResourceReference";
      } else {
        if (child.getDefinition().getType().size() > 1)
          throw new Exception("multiple types?");
        if (child.getDefinition().getType().size() == 1)
          type = child.getDefinition().getType().get(0).getCodeSimple();
        if (type != null) {
          if (type.startsWith("Resource("))
            type = "ResourceReference";
          if (type.startsWith("@")) {
            child = findElement(structure, type.substring(1));
            type = null;
          }
        }       
      }
      if (type != null) {
        if (typeIsPrimitive(type)) 
          checkPrimitive(ci.path(), type, child, ci.element());
        else {
          if (type.equals("Identifier"))
            checkIdentifier(ci.path(), ci.element(), child);
          else if (type.equals("Coding"))
            checkCoding(ci.path(), ci.element(), child);
          else if (type.equals("CodeableConcept"))
            checkCodeableConcept(ci.path(), ci.element(), child);
          if (type.equals("Resource"))
            validateContains(ci.path(), child, definition, ci.element());
          else {
            ProfileStructureComponent r = getResourceForProfile(type);
            if (rule(ci.path(), r != null, "Unknown type "+type)) {
              validateElement(r, ci.path(), r.getElement().get(0), child, ci.element());
            }
          }
        }
      } else {
        if (rule(path, child != null, "Unrecognised Content "+ci.name()))
          validateElement(structure, ci.path(), child, null, ci.element());
      }
    }
  }

  private ElementComponent findElement(ProfileStructureComponent structure, String name) {
    for (ElementComponent c : structure.getElement()) {
      if (c.getPathSimple().equals(name)) {
        return c;
      }
    }
    return null;
  }

  private Map<String, ElementComponent> getChildren(ProfileStructureComponent structure, ElementComponent definition) {
    HashMap<String, ElementComponent> res = new HashMap<String, Profile.ElementComponent>(); 
    for (ElementComponent e : structure.getElement()) {
      if (e.getPathSimple().startsWith(definition.getPathSimple()+".") && !e.getPathSimple().equals(definition.getPathSimple())) {
        String tail = e.getPathSimple().substring(definition.getPathSimple().length()+1);
        if (!tail.contains(".")) {
          res.put(tail, e);
        }
      }
    }
    return res;
  }

  private ElementComponent getDefinitionByTailNameChoice(Map<String, ElementComponent> children, String name) {
    for (String n : children.keySet()) {
      if (n.endsWith("[x]") && name.startsWith(n.substring(0, n.length()-3))) {
        return children.get(n);
      }
    }
    return null;
  }

  private String tail(String path) {
    return path.substring(path.lastIndexOf(".")+1);
  }

  private void validateContains(String path, ElementComponent child, ElementComponent context, Element element) throws Exception {
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
    if ("xhtml".equalsIgnoreCase(t)) return true;
    return false;
  }

  private void checkPrimitive(String path, String type, ElementComponent context, Element e) {
    // for nothing to check    
  }

  private void checkExtension(String path, ElementComponent elementDefn, ElementComponent context, Element e) {
    // for now, nothing to check yet
    
  }

  private void checkResourceReference(String path, Element element, ElementComponent context, boolean b) {
    // nothing to do yet
    
  }

  private void checkIdentifier(String path, Element element, ElementComponent context) {
    
  }

  private void checkQuantity(String path, Element element, ElementComponent context, boolean b) {
    String code = XMLUtil.getNamedChildValue(element,  "code");
    String system = XMLUtil.getNamedChildValue(element,  "system");
    String units = XMLUtil.getNamedChildValue(element,  "units");
    
    if (system != null && code != null) {
      checkCode(path, code, system, units);
    }
    
  }


  private void checkCoding(String path, Element element, ElementComponent context) {
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
    return codesystems.get(system);
  }

  private void checkCodeableConcept(String path, Element element, ElementComponent context) {
  
      // todo: check primary
  }
  
}

