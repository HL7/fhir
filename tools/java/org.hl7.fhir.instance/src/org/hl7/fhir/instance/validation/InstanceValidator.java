package org.hl7.fhir.instance.validation;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Attachment;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.BindingConformance;
import org.hl7.fhir.instance.model.Profile.ConstraintSeverity;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementSlicingComponent;
import org.hl7.fhir.instance.model.Profile.ExtensionContext;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.PropertyRepresentation;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Range;
import org.hl7.fhir.instance.model.Ratio;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.SampledData;
import org.hl7.fhir.instance.model.Schedule;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.utils.ConceptLocator;
import org.hl7.fhir.instance.utils.ConceptLocator.ValidationResult;
import org.hl7.fhir.instance.utils.ValueSetExpansionCache;
import org.hl7.fhir.instance.validation.ExtensionLocatorService.Status;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.NamespaceContextMap;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Element;


/* 
 * todo:
 * check urn's don't start oid: or uuid: 
 */
public class InstanceValidator extends BaseValidator {
  // configuration items

  public class NullExtensionResolver implements ExtensionLocatorService {

    @Override
    public ExtensionLocationResponse locateExtension(String uri) {
      return new ExtensionLocationResponse(ExtensionLocatorService.Status.Unknown, null, null);
    }
  }
  
  public enum CheckDisplayOption {
    Ignore,
    Check,
    CheckCaseAndSpace,
    CheckCase,
    CheckSpace
  }

  private CheckDisplayOption checkDisplay;


  private static final String NS_FHIR = "http://hl7.org/fhir";


  private Map<String, Profile> types = new HashMap<String, Profile>();
  private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();
  private Map<String, ValueSet> codesystems = new HashMap<String, ValueSet>();
  private ValueSetExpansionCache cache = new ValueSetExpansionCache(valuesets, codesystems);
  private boolean suppressLoincSnomedMessages;
  private ExtensionLocatorService extensions;


  private ConceptLocator conceptLocator;

  public InstanceValidator(String validationZip, ExtensionLocatorService extensions, ConceptLocator conceptLocator) throws Exception {
    super();
    source = Source.InstanceValidator;
    loadValidationResources(validationZip);
    this.extensions = (extensions == null ) ? new NullExtensionResolver() : extensions;
    this.conceptLocator = conceptLocator;
  }  

  private void loadValidationResources(String name) throws Exception {
    ZipInputStream zip = new ZipInputStream(new FileInputStream(name));
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      if (ze.getName().endsWith(".xml")) {
        readFile(zip, ze.getName());
      }
      zip.closeEntry();
    }
    zip.close();    
  }

  public InstanceValidator(Map<String, byte[]> source, ExtensionLocatorService extensions) throws Exception {
    super();
    super.source = Source.InstanceValidator;
    this.extensions = (extensions == null ) ? new NullExtensionResolver() : extensions;
    loadValidationResources(source);
  }  

  private void loadValidationResources(Map<String, byte[]> source) throws Exception {
    for (String name : source.keySet()) {
      if (name.endsWith(".xml")) {
        readFile(new ByteArrayInputStream(source.get(name)), name);        
      }
    }
  }

  private void readFile(InputStream zip, String name) throws Exception {
    XmlParser xml = new XmlParser();
    AtomFeed f = xml.parseGeneral(zip).getFeed();
    for (AtomEntry e : f.getEntryList()) {
      if (e.getId() == null) {
        System.out.println("unidentified resource "+e.getLinks().get("self")+" in "+name);
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

  public void validateInstance(List<ValidationMessage> errors, Element elem) throws Exception {
    boolean feedHasAuthor = XMLUtil.getNamedChild(elem, "author") != null;
    if (elem.getLocalName().equals("feed")) {
      ChildIterator ci = new ChildIterator("", elem);
      while (ci.next()) {
        if (ci.name().equals("category"))
          validateTag(ci.path(), ci.element(), false);
        else if (ci.name().equals("id"))
          validateId(errors, ci.path(), ci.element(), true);
        else if (ci.name().equals("link"))
          validateLink(errors, ci.path(), ci.element(), false);
        else if (ci.name().equals("entry")) 
          validateAtomEntry(errors, ci.path(), ci.element(), feedHasAuthor);
      }
    }
    else
      validate(errors, "", elem);
  }

  public List<ValidationMessage> validateInstance(Element elem) throws Exception {
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    validateInstance(errors, elem);      
    return errors;
  }

  private void validateAtomEntry(List<ValidationMessage> errors, String path, Element element, boolean feedHasAuthor) throws Exception {
    rule(errors, "invalid", path, XMLUtil.getNamedChild(element, "title") != null, "Entry must have a title");
    rule(errors, "invalid", path, XMLUtil.getNamedChild(element, "updated") != null, "Entry must have a last updated time");
    rule(errors, "invalid", path, feedHasAuthor || XMLUtil.getNamedChild(element, "author") != null, "Entry must have an author because the feed doesn't");



    ChildIterator ci = new ChildIterator(path, element);
    while (ci.next()) {
      if (ci.name().equals("category"))
        validateTag(ci.path(), ci.element(), true);
      else if (ci.name().equals("id"))
        validateId(errors, ci.path(), ci.element(), true);
      else if (ci.name().equals("link"))
        validateLink(errors, ci.path(), ci.element(), true);
      else if (ci.name().equals("content")) {
        Element r = XMLUtil.getFirstChild(ci.element());
        validate(errors, ci.path()+"/f:"+r.getLocalName(), r);
      }
    }
  }

  private void validate(List<ValidationMessage> errors, String path, Element elem) throws Exception {
    if (elem.getLocalName().equals("Binary"))
      validateBinary(elem);
    else {
      Profile p = getProfileForType(elem.getLocalName());
      ProfileStructureComponent s = getStructureForType(p, elem.getLocalName());
      if (rule(errors, "invalid", elem.getLocalName(), s != null, "Unknown Resource Type "+elem.getLocalName())) {
        validateElement(errors, p, s, path+"/f:"+elem.getLocalName(), s.getElement().get(0), null, null, elem, elem.getLocalName());
        if (elem.getLocalName().equals("Query"))
          validateQuery(errors, elem);
      }
    }
  }

  private void validateQuery(List<ValidationMessage> errors, Element elem) {
    // TODO - check that parameters match defined ones
    
  }

  private Profile getProfileForType(String localName) throws Exception {
    Profile r = (Profile) getResource(localName);
    if (r == null)
      return null;
    if (r.getStructure().size() != 1 || !(r.getStructure().get(0).getTypeSimple().equals(localName) || r.getStructure().get(0).getNameSimple().equals(localName)))
      throw new Exception("unexpected profile contents");
    ProfileStructureComponent s = r.getStructure().get(0);
    return r;
  }

  private ProfileStructureComponent getStructureForType(Profile r, String localName) throws Exception {
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

  private void validateLink(List<ValidationMessage> errors, String path, Element element, boolean onEntry) {
    if (rule(errors, "invalid", path, element.hasAttribute("rel"), "Link element has no '@rel'")) {
      String rel = element.getAttribute("rel");
      if (rule(errors, "invalid", path, !Utilities.noString(rel), "Link/@rel is empty")) {
        if (rel.equals("self")) {
          if (rule(errors, "invalid", path, element.hasAttribute("href"), "Link/@rel='self' has no href"))
            rule(errors, "invalid", path, isAbsoluteUrl(element.getAttribute("href")), "Link/@rel='self' '"+element.getAttribute("href")+"' is not an absolute URI (must start with http:, https:, urn:, cid:");
        }
      }
    }
  }

  private void validateId(List<ValidationMessage> errors, String path, Element element, boolean onEntry) {
    if (rule(errors, "invalid", path, !Utilities.noString(element.getTextContent()), "id is empty"))
      rule(errors, "invalid", path, isAbsoluteUrl(element.getTextContent()), "Id '"+element.getTextContent()+"' is not an absolute URI (must start with http:, https:, urn:, cid:");
  }

  private boolean isAbsoluteUrl(String url) {
    if (url == null)
      return false;
    if (url.startsWith("http:"))
      return true;
    if (url.startsWith("https:"))
      return true;
    if (url.startsWith("urn:"))
      return true;
    if (url.startsWith("cid:"))
      return true;
    return false;
  }

  private void validateElement(List<ValidationMessage> errors, Profile profile, ProfileStructureComponent structure, String path, ElementComponent definition, Profile cprofile, ElementComponent context, Element element, String actualType) throws Exception {
    // irrespective of what element it is, it cannot be empty
    if (NS_FHIR.equals(element.getNamespaceURI())) {
      rule(errors, "invalid", path, !empty(element), "Elements must have some content (@value, @id, extensions, or children elements)");
    }
    Map<String, ElementComponent> children = getChildren(structure, definition.getPathSimple());
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
          checkPrimitive(errors, ci.path(), type, child, ci.element());
        else {
          if (type.equals("Identifier"))
            checkIdentifier(ci.path(), ci.element(), child);
          else if (type.equals("Coding"))
            checkCoding(errors, ci.path(), ci.element(), profile, child);
          else if (type.equals("CodeableConcept"))
            checkCodeableConcept(errors, ci.path(), ci.element(), profile, child);
          else if (type.equals("Extension"))
            checkExtension(errors, ci.path(), ci.element(), profile, child, actualType);

          if (type.equals("Resource"))
            validateContains(errors, ci.path(), child, definition, ci.element());
          else {
            Profile p = getProfileForType(type); 
            ProfileStructureComponent r = getStructureForType(p, type);
            if (rule(errors, "structure", ci.path(), r != null, "Unknown type "+type)) {
              validateElement(errors, p, r, ci.path(), r.getElement().get(0), profile, child, ci.element(), type);
            }
          }
        }
      } else {
        if (rule(errors, "structure", path, child != null, "Unrecognised Content "+ci.name()))
          validateElement(errors, profile, structure, ci.path(), child, null, null, ci.element(), type);
      }
    }
  }

  private void checkExtension(List<ValidationMessage> errors, String path, Element element, Profile profile, ElementComponent container, String parentType) throws Exception {
    String url = element.getAttribute("url");
    ExtensionLocatorService.ExtensionLocationResponse ext = extensions.locateExtension(url);
    if (ext.getStatus() == Status.NotAllowed) {
    	rule(errors, "structure", path+"[url='"+url+"']", false, "This extension cannot be used here ("+ext.getMessage()+")");
    } else if (ext.getStatus() == Status.Located) {
    	// two questions 
    	// can this extension be used here?, and is the content of the extension valid?
      checkExtensionContext(errors, path+"[url='"+url+"']", ext.getDefinition(), container, parentType, ((Element) element.getParentNode()).getAttribute("url"));
      if (ext.getDefinition().getDefinition().getType().size() > 0) { // if 0, then this just contains extensions
        if (ext.getDefinition().getDefinition().getType().size() > 1) 
          throw new Error("exceptions with multiple types are not yet handled");
        String cs = ext.getDefinition().getDefinition().getType().get(0).getCodeSimple();
        if (cs.contains("("))
          cs = cs.substring(0, cs.indexOf("("));
        String childName = "value"+Utilities.capitalize(cs);
        Element child = XMLUtil.getNamedChild(element, childName);
        if (rule(errors, "structure", path+"[url='"+url+"']", child != null, "No Extension value found (looking for '"+childName+"')")) {
          Profile type = types.get(cs);
          ElementComponent ec = new ElementComponent(); // gimmy up a fake element component for the next call
          ec.setPathSimple(path+"[url='"+url+"']");
          ec.setNameSimple(childName);
          ec.setDefinition(ext.getDefinition().getDefinition());
          if (type != null) 
            validateElement(errors, profile, null, path+"[url='"+url+"']."+childName, ec, null, null, child, "Extension");
          else {
            checkPrimitive(errors, path+"[url='"+url+"']."+childName, cs, ec, child);
            // special: check vocabulary. Mostly, this isn't needed on a code, but it is with extension
            if (cs.equals("code"))  {
              ElementDefinitionBindingComponent binding = ext.getDefinition().getDefinition().getBinding();
              if (binding != null) {
                if (warning(errors, "code-unknown", path, binding.getReference() != null && binding.getReference() instanceof ResourceReference, "Binding for "+path+" missing or cannot be processed")) {
                  if (binding.getReference() != null && binding.getReference() instanceof ResourceReference) {
                    ValueSet vs = resolveBindingReference(binding.getReference());
                    if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
                      try {
                        vs = cache.getExpander().expand(vs);
                        if (warning(errors, "code-unknown", path, vs != null, "Unable to expand value set for "+describeReference(binding.getReference()))) {
                          warning(errors, "code-unknown", path, codeInExpansion(vs, null, child.getAttribute("value")), "Code "+child.getAttribute("value")+" is not in value set "+describeReference(binding.getReference())+" ("+vs.getIdentifierSimple()+")");
                        }
                      } catch (Exception e) {
                        warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getIdentifierSimple()+" for "+describeReference(binding.getReference())+": "+e.getMessage());
                      }
                    }
                  } 
                }
              }
            }
          }
        }
      }
    }
  }

  private void checkExtensionContext(List<ValidationMessage> errors, String path, ProfileExtensionDefnComponent definition, ElementComponent container, String parentType, String extensionParent) {
	  if (definition.getContextTypeSimple() == ExtensionContext.datatype) {
	  	boolean ok = false;
	  	for (String_ ct : definition.getContext()) 
	  		if (ct.getValue().equals("*") || ct.getValue().equals(parentType))
	  				ok = true;
	  	rule(errors, "structure", path, ok, "This extension is not allowed to be used with the type "+parentType);
	  } else if (definition.getContextTypeSimple() == ExtensionContext.extension) {
      boolean ok = false;
      for (String_ ct : definition.getContext()) 
        if (ct.getValue().equals("*") || ct.getValue().equals(extensionParent))
            ok = true;
      rule(errors, "structure", path, ok, "This extension is not allowed to be used with the extension '"+extensionParent+"'");
	  } else if (definition.getContextTypeSimple() == ExtensionContext.mapping) {
  		throw new Error("Not handled yet");	  	
	  } else if (definition.getContextTypeSimple() == ExtensionContext.resource) {
      boolean ok = false;
      String simplePath = simplifyPath(path);
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (String_ ct : definition.getContext()) {
        b.append(ct.getValue());
        if (ct.getValue().equals("*") || ct.getValue().equals(parentType) || simplePath.equals(ct.getValue()) || simplePath.endsWith("."+ct.getValue()))
            ok = true;
      }
      rule(errors, "structure", path, ok, "This extension is not allowed to be used with the resource "+(parentType == null ? simplePath : parentType)+" (allowed: "+b.toString()+")");
	  } else 
  		throw new Error("Unknown context type");	  	
  }

  private String simplifyPath(String path) {
    String s = path.replace("/f:", ".");
    while (s.contains("[")) 
      s = s.substring(0, s.indexOf("["))+s.substring(s.indexOf("]")+1);
    String[] parts = s.split("\\.");
    int i = 0;
    while (i < parts.length && !types.containsKey(parts[i].toLowerCase()))
      i++;
    if (i >= parts.length)
      throw new Error("Unable to process part "+path);
    int j = parts.length - 1;
    while (j > 0 && (parts[j].equals("extension") || parts[j].equals("modifierExtension")))
        j--;
    while (j > 1 && (parts[j].equals(parts[j-1])))
      j--;
    StringBuilder b = new StringBuilder();
    for (int k = i; k <= j; k++) {
      if (k > i)
        b.append(".");
      b.append(parts[k]);
    }
    return b.toString();
  }

	private boolean empty(Element element) {
    if (element.hasAttribute("value"))
      return false;
    if (element.hasAttribute("id"))
      return false;
    if (element.hasAttribute("xml:id"))
      return false;
    Element child = XMLUtil.getFirstChild(element);
    while (child != null) {
      if (NS_FHIR.equals(child.getNamespaceURI()))
        return false;        
    }
    return true;
  }

  private ElementComponent findElement(ProfileStructureComponent structure, String name) {
    for (ElementComponent c : structure.getElement()) {
      if (c.getPathSimple().equals(name)) {
        return c;
      }
    }
    return null;
  }

  private Map<String, ElementComponent> getChildren(ProfileStructureComponent structure, String path) {
    HashMap<String, ElementComponent> res = new HashMap<String, Profile.ElementComponent>(); 
    for (ElementComponent e : structure.getElement()) {
      String p = e.getPathSimple();
      if (!Utilities.noString(e.getDefinition().getNameReferenceSimple()) && path.startsWith(p)) {
        if (path.length() > p.length())
          return getChildren(structure, e.getDefinition().getNameReferenceSimple()+"."+path.substring(p.length()+1));
        else
          return getChildren(structure, e.getDefinition().getNameReferenceSimple());
      } else if (p.startsWith(path+".") && !p.equals(path)) {
          String tail = p.substring(path.length()+1);
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

  private void validateContains(List<ValidationMessage> errors, String path, ElementComponent child, ElementComponent context, Element element) throws Exception {
    Element e = XMLUtil.getFirstChild(element);
    validate(errors, path, e);    
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
    if ("dateTime".equalsIgnoreCase(t)) return true;
    if ("date".equalsIgnoreCase(t)) return true;
    if ("oid".equalsIgnoreCase(t)) return true;
    if ("uuid".equalsIgnoreCase(t)) return true;
    if ("code".equalsIgnoreCase(t)) return true;
    if ("id".equalsIgnoreCase(t)) return true;
    if ("xhtml".equalsIgnoreCase(t)) return true;
    return false;
  }

  private void checkPrimitive(List<ValidationMessage> errors, String path, String type, ElementComponent context, Element e) {
    if (type.equals("uri")) {
      rule(errors, "invalid", path, !e.getAttribute("value").startsWith("oid:"), "URI values cannot start with oid:");
      rule(errors, "invalid", path, !e.getAttribute("value").startsWith("uuid:"), "URI values cannot start with uuid:");
    }
    if (!type.equalsIgnoreCase("string") && e.hasAttribute("value")) {
      if (rule(errors, "invalid", path, e.getAttribute("value").length() > 0, "@value cannot be empty")) {
        warning(errors, "invalid", path, e.getAttribute("value").trim().equals(e.getAttribute("value")), "value should not start or finish with whitespace");
      }
    }

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

  private void checkQuantity(List<ValidationMessage> errors, String path, Element element, ElementComponent context, boolean b) {
    String code = XMLUtil.getNamedChildValue(element,  "code");
    String system = XMLUtil.getNamedChildValue(element,  "system");
    String units = XMLUtil.getNamedChildValue(element,  "units");

    if (system != null && code != null) {
      checkCode(errors, path, code, system, units);
    }

  }


  private void checkCoding(List<ValidationMessage> errors, String path, Element element, Profile profile, ElementComponent context) {
    String code = XMLUtil.getNamedChildValue(element,  "code");
    String system = XMLUtil.getNamedChildValue(element,  "system");
    String display = XMLUtil.getNamedChildValue(element,  "display");

    if (system != null && code != null) {
      if (checkCode(errors, path, code, system, display)) 
        if (context != null && context.getDefinition().getBinding() != null) {
          ElementDefinitionBindingComponent binding = context.getDefinition().getBinding();
          if (warning(errors, "code-unknown", path, binding != null, "Binding for "+path+" missing")) {
            if (binding.getReference() != null && binding.getReference() instanceof ResourceReference) {
              ValueSet vs = resolveBindingReference(binding.getReference());
              if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
                try {
                  vs = cache.getExpander().expand(vs);
                  if (warning(errors, "code-unknown", path, vs != null, "Unable to expand value set for "+describeReference(binding.getReference()))) {
                    warning(errors, "code-unknown", path, codeInExpansion(vs, system, code), "Code {"+system+"}"+code+" is not in value set "+describeReference(binding.getReference())+" ("+vs.getIdentifierSimple()+")");
                  }
                } catch (Exception e) {
                  if (e.getMessage() == null)
                    warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getIdentifierSimple()+" for "+describeReference(binding.getReference())+": --Null--");
                  else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct"))
                    hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Snomed value set - not validated");
                  else if (!e.getMessage().contains("unable to find value set http://loinc.org"))
                    hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Loinc value set - not validated");
                  else
                    warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getIdentifierSimple()+" for "+describeReference(binding.getReference())+": "+e.getMessage());
                }
              }
            } else if (binding.getReference() != null)
              hint(errors, "code-unknown", path, false, "Binding by URI reference cannot be checked");
            else 
              hint(errors, "code-unknown", path, false, "Binding has no source, so can't be checked");
          }
        }
    }
  }


  private ValueSet resolveBindingReference(Type reference) {
    if (reference instanceof Uri)
      return valuesets.get(((Uri) reference).getValue().toString());
    else if (reference instanceof ResourceReference)
      return valuesets.get(((ResourceReference) reference).getReferenceSimple());
    else
      return null;
  }

  private boolean codeInExpansion(ValueSet vs, String system, String code) {
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
      if (code.equals(c.getCodeSimple()) && (system == null || system.equals(c.getSystemSimple())))
        return true;
      if (codeinExpansion(c, system, code)) 
        return true;
    }
    return false;
  }

  private boolean codeinExpansion(ValueSetExpansionContainsComponent cnt, String system, String code) {
    for (ValueSetExpansionContainsComponent c : cnt.getContains()) {
      if (code.equals(c.getCodeSimple()) && system.equals(c.getSystemSimple().toString()))
        return true;
      if (codeinExpansion(c, system, code)) 
        return true;
    }
    return false;
  }

  private void checkCodeableConcept(List<ValidationMessage> errors, String path, Element element, Profile profile, ElementComponent context) {
    if (context != null && context.getDefinition().getBinding() != null) {
      ElementDefinitionBindingComponent binding = context.getDefinition().getBinding();
      if (warning(errors, "code-unknown", path, binding != null, "Binding for "+path+" missing (cc)")) {
        if (binding.getReference() != null && binding.getReference() instanceof ResourceReference) {
          ValueSet vs = resolveBindingReference(binding.getReference());
          if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
            try {
              vs = cache.getExpander().expand(vs);
              if (warning(errors, "code-unknown", path, binding != null, "Unable to expand value set for "+describeReference(binding.getReference()))) {
                boolean found = false;
                boolean any = false;
                Element c = XMLUtil.getFirstChild(element);
                while (c != null) {
                  if (c.getNodeName().equals("coding")) {
                    any = true;
                    String system = XMLUtil.getNamedChildValue(c, "system");
                    String code = XMLUtil.getNamedChildValue(c, "code");
                    if (system != null && code != null)
                      found = found || codeInExpansion(vs, system, code);
                  }
                  c = XMLUtil.getNextSibling(c);
                }
                if (!any && binding.getConformanceSimple() == BindingConformance.required)
                  warning(errors, "code-unknown", path, found, "No code provided, and value set "+describeReference(binding.getReference())+" ("+vs.getIdentifierSimple()+") is required");
                if (any)
                  if (binding.getConformanceSimple() == BindingConformance.example)
                    hint(errors, "code-unknown", path, found, "None of the codes are in the example value set "+describeReference(binding.getReference())+" ("+vs.getIdentifierSimple()+")");
                  else 
                    warning(errors, "code-unknown", path, found, "None of the codes are in the expected value set "+describeReference(binding.getReference())+" ("+vs.getIdentifierSimple()+")");
              }
            } catch (Exception e) {
              if (e.getMessage() == null) {
                warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getIdentifierSimple()+" for "+describeReference(binding.getReference())+": --Null--");
              } else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct")) {
                hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Snomed value set - not validated");
              } else if (!e.getMessage().contains("unable to find value set http://loinc.org")) { 
                hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Loinc value set - not validated");
              } else
                warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getIdentifierSimple()+" for "+describeReference(binding.getReference())+": "+e.getMessage());
            }
          }
        } else if (binding.getReference() != null)
          hint(errors, "code-unknown", path, false, "Binding by URI reference cannot be checked");
        else 
          hint(errors, "code-unknown", path, false, "Binding has no source, so can't be checked");
      }
    }
  }

  private String describeReference(Type reference) {
    if (reference == null)
      return "null";
    if (reference instanceof Uri)
      return ((Uri)reference).getValue();
    if (reference instanceof ResourceReference)
      return ((ResourceReference)reference).getReference().getValue();
    return "??";
  }


  private boolean checkCode(List<ValidationMessage> errors, String path, String code, String system, String display) {
    if (conceptLocator != null && conceptLocator.verifiesSystem(system)) {
      ValidationResult s = conceptLocator.validate(system, code, display);
      if (s == null)
        return true;
      if (s.getSeverity() == IssueSeverity.information)
        hint(errors, "code-unknown", path, s == null, s.getMessage());
      else if (s.getSeverity() == IssueSeverity.warning)
        warning(errors, "code-unknown", path, s == null, s.getMessage());
      else
        return rule(errors, "code-unknown", path, s == null, s.getMessage());
      return true;
    } else if (system.startsWith("http://hl7.org/fhir")) {
      if (system.equals("http://hl7.org/fhir/sid/icd-10"))
        return true; // else don't check ICD-10 (for now)
      else {
        ValueSet vs = getValueSet(system);
        if (warning(errors, "code-unknown", path, vs != null, "Unknown Code System "+system)) {
          ValueSetDefineConceptComponent def = getCodeDefinition(vs, code); 
          if (warning(errors, "code-unknown", path, def != null, "Unknown Code ("+system+"#"+code+")"))
            return warning(errors, "code-unknown", path, display == null || display.equals(def.getDisplaySimple()), "Display should be '"+def.getDisplaySimple()+"'");
        }
        return false;
      }
    } else if (system.startsWith("http://loinc.org")) {
      return true;
    } else if (system.startsWith("http://unitsofmeasure.org")) {
      return true;
    }
    else 
      return true;
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

  public boolean isSuppressLoincSnomedMessages() {
    return suppressLoincSnomedMessages;
  }

  public void setSuppressLoincSnomedMessages(boolean suppressLoincSnomedMessages) {
    this.suppressLoincSnomedMessages = suppressLoincSnomedMessages;
  }

  public void validateInstanceByProfile(List<ValidationMessage> errors, Element root, Profile profile) throws Exception {
    // we assume that the following things are true: 
    // the instance at root is valid against the schema and schematron
    // the instance validator had no issues against the base resource profile
    if (root.getLocalName().equals("feed")) {
      // throw new Exception("not done yet");
      warning(errors, "invalid", "feed", false, "Validating feeds is not done yet");
    }
    else {
      // so the first question is what to validate against
      ProfileStructureComponent sc = null;
      for (ProfileStructureComponent s : profile.getStructure()) {
        if (root.getLocalName().equals(s.getTypeSimple())) {
          if (sc == null)
            sc = s;
          else
            throw new Exception("the profile contains multiple matches for the resource "+root.getLocalName()+" and the profile cannot be validated against");
        }
      }
      if (rule(errors, "invalid", root.getLocalName(), sc != null, "Profile does not allow for this resource")) {
        // well, does it conform to the resource?
        // this is different to the case above because there may be more than one option at each point, and we could conform to any one of them
        checkByProfile(errors, root.getLocalName(), root, profile, sc, sc.getElement().get(0));
      }
    }
  }

  public class ProfileStructureIterator {

    private ProfileStructureComponent structure;
    private ElementComponent elementDefn;
    private List<String> names = new ArrayList<String>();
    private Map<String, List<ElementComponent>> children = new HashMap<String, List<ElementComponent>>();
    private int cursor;

    public ProfileStructureIterator(Profile profile, ProfileStructureComponent sc, ElementComponent elementDefn) {
      this.structure = sc;        
      this.elementDefn = elementDefn;
      loadMap();
      cursor = -1;
    }

    private void loadMap() {
      int i = structure.getElement().indexOf(elementDefn) + 1;
      String lead = elementDefn.getPathSimple();
      while (i < structure.getElement().size()) {
        String name = structure.getElement().get(i).getPathSimple();
        if (name.length() <= lead.length()) 
          return; // cause we've got to the end of the possible matches
        String tail = name.substring(lead.length()+1);
        if (Utilities.isToken(tail) && name.substring(0, lead.length()).equals(lead)) {
          List<ElementComponent> list = children.get(tail);
          if (list == null) {
            list = new ArrayList<Profile.ElementComponent>();
            names.add(tail);
            children.put(tail, list);
          }
          list.add(structure.getElement().get(i));
        }
        i++;
      }
    }

    public boolean more() {
      cursor++;
      return cursor < names.size();
    }

    public List<ElementComponent> current() {
      return children.get(name());
    }

    public String name() {
      return names.get(cursor);
    }

  }

  private void checkByProfile(List<ValidationMessage> errors, String path, Element focus, Profile profile, ProfileStructureComponent sc, ElementComponent elementDefn) throws Exception {
    // we have an element, and the structure that describes it. 
    // we know that's it's valid against the underlying spec - is it valid against this one?
    // in the instance validator above, we assume that schema or schmeatron has taken care of cardinalities, but here, we have no such reliance. 
    // so the walking algorithm is different: we're going to walk the definitions
    String type;
  	if (elementDefn.getPathSimple().endsWith("[x]")) {
  		String tail = elementDefn.getPathSimple().substring(elementDefn.getPathSimple().lastIndexOf(".")+1, elementDefn.getPathSimple().length()-3);
  		type = focus.getLocalName().substring(tail.length());
  		rule(errors, "structure", path, typeAllowed(type, elementDefn.getDefinition().getType()), "The type '"+type+"' is not allowed at this point (must be one of '"+typeSummary(elementDefn)+")");
  	} else {
  		if (rule(errors, "struture", path, elementDefn.getDefinition().getType().size() == 1, "Error in profile: type count != 0, but only no type in instance"))
  			type = elementDefn.getDefinition().getType().get(0).getCodeSimple();
  		else
  			type = null;
  	}
  	// constraints:
  	for (ElementDefinitionConstraintComponent c : elementDefn.getDefinition().getConstraint()) 
  		checkConstraint(errors, path, focus, c);
  	if (elementDefn.getDefinition().getBinding() != null)
  		checkBinding(errors, path, focus, profile, elementDefn, type);
  	
  	// type specific checking:
  	if (type != null && typeIsPrimitive(type)) {
  		checkPrimitiveByProfile(errors, path, focus, elementDefn);
  	} else {
  		if (elementDefn.getDefinition().getValue() != null)
  			checkFixedValue(errors, path, focus, elementDefn.getDefinition().getValue(), "");
  			 
  		ProfileStructureIterator walker = new ProfileStructureIterator(profile, sc, elementDefn);
  		while (walker.more()) {
  			// collect all the slices for the path
  			List<ElementComponent> childset = walker.current();
  			// collect all the elements that match it by name
  			List<Element> children = new ArrayList<Element>(); 
  			XMLUtil.getNamedChildrenWithWildcard(focus, walker.name(), children);

  			if (children.size() == 0) {
  				// well, there's no children - should there be? 
  				for (ElementComponent defn : childset) {
  					if (!rule(errors,"required", path, defn.getDefinition().getMinSimple() == 0, "Required Element '"+walker.name()+"' missing"))
  						break; // no point complaining about missing ones after the first one
  				} 
  			} else if (childset.size() == 1) {
  				// simple case: one possible definition, and one or more children. 
  				rule(errors, "cardinality", path, childset.get(0).getDefinition().getMaxSimple() == "*" || Integer.parseInt(childset.get(0).getDefinition().getMaxSimple()) >= children.size(), "Too many elements for '"+walker.name()+"'"); // todo: sort out structure
  				for (Element child : children) {
  					checkByProfile(errors, path+"."+childset.get(0).getNameSimple(), child, profile, sc, childset.get(0));
  				}
  			} else { 
  				// ok, this is the full case - we have a list of definitions, and a list of candidates for meeting those definitions. 
  				// we need to decide *if* that match a given definition
  			}
  		}
  	}
  }

	private void checkBinding(List<ValidationMessage> errors, String path, Element focus, Profile profile, ElementComponent elementDefn, String type) {
	  ElementDefinitionBindingComponent bc = elementDefn.getDefinition().getBinding();

	  if (bc != null && bc.getReference() != null && bc.getReference() instanceof ResourceReference) {
	  	ValueSet vs = resolveValueSetReference(profile, (ResourceReference) bc.getReference());
	  	if (vs == null)
	  		rule(errors, "structure", path, false, "Cannot check binding on type '"+type+"' as the value set '"+((ResourceReference) bc.getReference()).getReferenceSimple()+"' could not be located");
	  	else if (type.equals("code"))
	  		checkBindingCode(errors, path, focus, vs);
	  	else if (type.equals("Coding"))
	  		checkBindingCoding(errors, path, focus, vs);
	  	else if (type.equals("CodeableConcept"))
	  		checkBindingCodeableConcept(errors, path, focus, vs);
	  	else 
	  		rule(errors, "structure", path, false, "Cannot check binding on type '"+type+"'");
	  }
  }

	private ValueSet resolveValueSetReference(Profile profile, ResourceReference reference) {
	  if (reference.getReferenceSimple().startsWith("#")) {
	  	for (Resource r : profile.getContained()) {
	  		if (r instanceof ValueSet && r.getXmlId().equals(reference.getReferenceSimple().substring(1)))
	  			return (ValueSet) r;
	  	}
	  	return null;
	  } else
	  	return resolveBindingReference(reference);
	   
  }

	private void checkBindingCode(List<ValidationMessage> errors, String path, Element focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCode not done yet");	  
  }

	private void checkBindingCoding(List<ValidationMessage> errors, String path, Element focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCoding not done yet");	  
  }

	private void checkBindingCodeableConcept(List<ValidationMessage> errors, String path, Element focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCodeableConcept not done yet");	  
  }

	private String typeSummary(ElementComponent elementDefn) {
	  StringBuilder b = new StringBuilder();
	  for (TypeRefComponent t : elementDefn.getDefinition().getType()) {
	  	b.append("|"+t.getCodeSimple());
	  }
	  return b.toString().substring(1);
  }

	private boolean typeAllowed(String t, List<TypeRefComponent> types) {
	  for (TypeRefComponent type : types) {
	  	if (t.equals(Utilities.capitalize(type.getCodeSimple())))
	  		return true;
	  }
	  return false;
  }

	private void checkConstraint(List<ValidationMessage> errors, String path, Element focus, ElementDefinitionConstraintComponent c) throws Exception {
//		try
//   	{
//			XPathFactory xpf = new net.sf.saxon.xpath.XPathFactoryImpl();
//      NamespaceContext context = new NamespaceContextMap("f", "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml");
//			
//			XPath xpath = xpf.newXPath();
//      xpath.setNamespaceContext(context);
//   		Boolean ok = (Boolean) xpath.evaluate(c.getXpathSimple(), focus, XPathConstants.BOOLEAN);
//   		if (ok == null || !ok) {
//   			if (c.getSeveritySimple() == ConstraintSeverity.warning)
//   				warning(errors, "invariant", path, false, c.getHumanSimple());
//   			else
//   				rule(errors, "invariant", path, false, c.getHumanSimple());
//   		}
//		}
//		catch (XPathExpressionException e) {
//		  rule(errors, "invariant", path, false, "error executing invariant: "+e.getMessage());
//		}
  }

	private void checkPrimitiveByProfile(List<ValidationMessage> errors, String path, Element focus, ElementComponent elementDefn) {
		// two things to check - length, and fixed value
		String value = focus.getAttribute("value");
		if (elementDefn.getDefinition().getMaxLength() != null) {
			rule(errors, "too long", path, value.length() <= elementDefn.getDefinition().getMaxLengthSimple(), "The value '"+value+"' exceeds the allow length limit of "+Integer.toString(elementDefn.getDefinition().getMaxLengthSimple()));
		}
		if (elementDefn.getDefinition().getValue() != null) {
			checkFixedValue(errors, path, focus, elementDefn.getDefinition().getValue(), "");
		}
  }

	private void checkFixedValue(List<ValidationMessage> errors, String path, Element focus, org.hl7.fhir.instance.model.Element fixed, String propName) {
		if (fixed == null && focus == null)
			; // this is all good
		else if (fixed == null && focus != null)
	  	rule(errors, "value", path, false, "Unexpected element "+focus.getNodeName());
		else if (fixed != null && focus == null)
	  	rule(errors, "value", path, false, "Mising element "+propName);
		else {
			String value = focus.getAttribute("value");
			if (fixed instanceof org.hl7.fhir.instance.model.Boolean)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Boolean) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Boolean) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Integer)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Integer) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Integer) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Decimal)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Decimal) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Decimal) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Base64Binary)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Base64Binary) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Base64Binary) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Instant)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Instant) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Instant) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.String_)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.String_) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.String_) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Uri)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Uri) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Uri) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Date)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Date) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Date) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DateTime)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.DateTime) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DateTime) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Oid)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Oid) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Oid) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Uuid)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Uuid) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Uuid) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Code)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Code) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Code) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Id)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Id) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Id) fixed).getValue()+"'");
			else if (fixed instanceof Quantity)
				checkQuantity(errors, path, focus, (Quantity) fixed);
			else if (fixed instanceof Address)
				checkAddress(errors, path, focus, (Address) fixed);
			else if (fixed instanceof Contact)
				checkContact(errors, path, focus, (Contact) fixed);
			else if (fixed instanceof Attachment)
				checkAttachment(errors, path, focus, (Attachment) fixed);
			else if (fixed instanceof Identifier)
				checkIdentifier(errors, path, focus, (Identifier) fixed);
			else if (fixed instanceof Coding)
				checkCoding(errors, path, focus, (Coding) fixed);
			else if (fixed instanceof HumanName)
				checkHumanName(errors, path, focus, (HumanName) fixed);
			else if (fixed instanceof CodeableConcept)
				checkCodeableConcept(errors, path, focus, (CodeableConcept) fixed);
			else if (fixed instanceof Schedule)
				checkSchedule(errors, path, focus, (Schedule) fixed);
			else if (fixed instanceof Period)
				checkPeriod(errors, path, focus, (Period) fixed);
			else if (fixed instanceof Range)
				checkRange(errors, path, focus, (Range) fixed);
			else if (fixed instanceof Ratio)
				checkRatio(errors, path, focus, (Ratio) fixed);
			else if (fixed instanceof SampledData)
				checkSampledData(errors, path, focus, (SampledData) fixed);
	
			else
				 rule(errors, "exception", path, false, "Unhandled fixed value type "+fixed.getClass().getName());
			List<Element> extensions = new ArrayList<Element>();
			XMLUtil.getNamedChildren(focus, "extension", extensions);
			if (fixed.getExtensions().size() == 0) {
				rule(errors, "value", path, extensions.size() == 0, "No extensions allowed");
			} else if (rule(errors, "value", path, extensions.size() == fixed.getExtensions().size(), "Extensions count mismatch: expected "+Integer.toString(fixed.getExtensions().size())+" but found "+Integer.toString(extensions.size()))) {
				for (Extension e : fixed.getExtensions()) {
					Element ex = getExtensionByUrl(extensions, e.getUrlSimple());
					if (rule(errors, "value", path, ex != null, "Extension count mismatch: unable to find extension: "+e.getUrlSimple())) {
						checkFixedValue(errors, path, XMLUtil.getNextSibling(XMLUtil.getFirstChild(ex)), e.getValue(), "extension.value");
					}
				}
			}
		}
  }

	private void checkAddress(List<ValidationMessage> errors, String path, Element focus, Address fixed) {
	  checkFixedValue(errors, path+".use", XMLUtil.getNamedChild(focus, "use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".text", XMLUtil.getNamedChild(focus, "text"), fixed.getText(), "text");
	  checkFixedValue(errors, path+".city", XMLUtil.getNamedChild(focus, "city"), fixed.getCity(), "city");
	  checkFixedValue(errors, path+".state", XMLUtil.getNamedChild(focus, "state"), fixed.getState(), "state");
	  checkFixedValue(errors, path+".country", XMLUtil.getNamedChild(focus, "country"), fixed.getCountry(), "country");
	  checkFixedValue(errors, path+".zip", XMLUtil.getNamedChild(focus, "zip"), fixed.getZip(), "zip");
	  
		List<Element> lines = new ArrayList<Element>();
		XMLUtil.getNamedChildren(focus,  "line", lines);
		if (rule(errors, "value", path, lines.size() == fixed.getLine().size(), "Expected "+Integer.toString(fixed.getLine().size())+" but found "+Integer.toString(lines.size())+" line elements")) {
			for (int i = 0; i < lines.size(); i++) 
				checkFixedValue(errors, path+".coding", lines.get(i), fixed.getLine().get(i), "coding");			
		}	  
  }

	private void checkContact(List<ValidationMessage> errors, String path, Element focus, Contact fixed) {
	  checkFixedValue(errors, path+".system", XMLUtil.getNamedChild(focus, "system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".value", XMLUtil.getNamedChild(focus, "value"), fixed.getValue(), "value");
	  checkFixedValue(errors, path+".use", XMLUtil.getNamedChild(focus, "use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".period", XMLUtil.getNamedChild(focus, "period"), fixed.getPeriod(), "period");
	  
  }

	private void checkAttachment(List<ValidationMessage> errors, String path, Element focus, Attachment fixed) {
	  checkFixedValue(errors, path+".contentType", XMLUtil.getNamedChild(focus, "contentType"), fixed.getContentType(), "contentType");
	  checkFixedValue(errors, path+".language", XMLUtil.getNamedChild(focus, "language"), fixed.getLanguage(), "language");
	  checkFixedValue(errors, path+".data", XMLUtil.getNamedChild(focus, "data"), fixed.getData(), "data");
	  checkFixedValue(errors, path+".url", XMLUtil.getNamedChild(focus, "url"), fixed.getUrl(), "url");
	  checkFixedValue(errors, path+".size", XMLUtil.getNamedChild(focus, "size"), fixed.getSize(), "size");
	  checkFixedValue(errors, path+".hash", XMLUtil.getNamedChild(focus, "hash"), fixed.getHash(), "hash");
	  checkFixedValue(errors, path+".title", XMLUtil.getNamedChild(focus, "title"), fixed.getTitle(), "title");	  
  }

	private void checkIdentifier(List<ValidationMessage> errors, String path, Element focus, Identifier fixed) {
	  checkFixedValue(errors, path+".use", XMLUtil.getNamedChild(focus, "use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".label", XMLUtil.getNamedChild(focus, "label"), fixed.getLabel(), "label");
	  checkFixedValue(errors, path+".system", XMLUtil.getNamedChild(focus, "system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".value", XMLUtil.getNamedChild(focus, "value"), fixed.getValue(), "value");
	  checkFixedValue(errors, path+".period", XMLUtil.getNamedChild(focus, "period"), fixed.getPeriod(), "period");
	  checkFixedValue(errors, path+".assigner", XMLUtil.getNamedChild(focus, "assigner"), fixed.getAssigner(), "assigner");
  }

	private void checkCoding(List<ValidationMessage> errors, String path, Element focus, Coding fixed) {
	  checkFixedValue(errors, path+".system", XMLUtil.getNamedChild(focus, "system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".code", XMLUtil.getNamedChild(focus, "code"), fixed.getCode(), "code");
	  checkFixedValue(errors, path+".display", XMLUtil.getNamedChild(focus, "display"), fixed.getDisplay(), "display");	  
	  checkFixedValue(errors, path+".primary", XMLUtil.getNamedChild(focus, "primary"), fixed.getPrimary(), "primary");	  
  }

	private void checkHumanName(List<ValidationMessage> errors, String path, Element focus, HumanName fixed) {
	  checkFixedValue(errors, path+".use", XMLUtil.getNamedChild(focus, "use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".text", XMLUtil.getNamedChild(focus, "text"), fixed.getText(), "text");
	  checkFixedValue(errors, path+".period", XMLUtil.getNamedChild(focus, "period"), fixed.getPeriod(), "period");
	  
		List<Element> parts = new ArrayList<Element>();
		XMLUtil.getNamedChildren(focus,  "family", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" family elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".family", parts.get(i), fixed.getFamily().get(i), "family");			
		}	  
		XMLUtil.getNamedChildren(focus,  "given", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" given elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".given", parts.get(i), fixed.getFamily().get(i), "given");			
		}	  
		XMLUtil.getNamedChildren(focus,  "prefix", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" prefix elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".prefix", parts.get(i), fixed.getFamily().get(i), "prefix");			
		}	  
		XMLUtil.getNamedChildren(focus,  "suffix", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" suffix elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".suffix", parts.get(i), fixed.getFamily().get(i), "suffix");			
		}	  
  }

	private void checkCodeableConcept(List<ValidationMessage> errors, String path, Element focus, CodeableConcept fixed) {
		checkFixedValue(errors, path+".text", XMLUtil.getNamedChild(focus, "text"), fixed.getText(), "text");
		List<Element> codings = new ArrayList<Element>();
		XMLUtil.getNamedChildren(focus,  "coding", codings);
		if (rule(errors, "value", path, codings.size() == fixed.getCoding().size(), "Expected "+Integer.toString(fixed.getCoding().size())+" but found "+Integer.toString(codings.size())+" coding elements")) {
			for (int i = 0; i < codings.size(); i++) 
				checkFixedValue(errors, path+".coding", codings.get(i), fixed.getCoding().get(i), "coding");			
		}	  
  }

	private void checkSchedule(List<ValidationMessage> errors, String path, Element focus, Schedule fixed) {
	  checkFixedValue(errors, path+".repeat", XMLUtil.getNamedChild(focus, "repeat"), fixed.getRepeat(), "value");
	  
		List<Element> events = new ArrayList<Element>();
		XMLUtil.getNamedChildren(focus,  "event", events);
		if (rule(errors, "value", path, events.size() == fixed.getEvent().size(), "Expected "+Integer.toString(fixed.getEvent().size())+" but found "+Integer.toString(events.size())+" event elements")) {
			for (int i = 0; i < events.size(); i++) 
				checkFixedValue(errors, path+".event", events.get(i), fixed.getEvent().get(i), "event");			
		}	  
  }

	private void checkPeriod(List<ValidationMessage> errors, String path, Element focus, Period fixed) {
	  checkFixedValue(errors, path+".start", XMLUtil.getNamedChild(focus, "start"), fixed.getStart(), "start");
	  checkFixedValue(errors, path+".end", XMLUtil.getNamedChild(focus, "end"), fixed.getEnd(), "end");	  
  }

	private void checkRange(List<ValidationMessage> errors, String path, Element focus, Range fixed) {
	  checkFixedValue(errors, path+".low", XMLUtil.getNamedChild(focus, "low"), fixed.getLow(), "low");
	  checkFixedValue(errors, path+".high", XMLUtil.getNamedChild(focus, "high"), fixed.getHigh(), "high");	  
	  
  }

	private void checkRatio(List<ValidationMessage> errors, String path,  Element focus, Ratio fixed) {
	  checkFixedValue(errors, path+".numerator", XMLUtil.getNamedChild(focus, "numerator"), fixed.getNumerator(), "numerator");
	  checkFixedValue(errors, path+".denominator", XMLUtil.getNamedChild(focus, "denominator"), fixed.getDenominator(), "denominator");	  
  }

	private void checkSampledData(List<ValidationMessage> errors, String path, Element focus, SampledData fixed) {
	  checkFixedValue(errors, path+".origin", XMLUtil.getNamedChild(focus, "origin"), fixed.getOrigin(), "origin");
	  checkFixedValue(errors, path+".period", XMLUtil.getNamedChild(focus, "period"), fixed.getPeriod(), "period");
	  checkFixedValue(errors, path+".factor", XMLUtil.getNamedChild(focus, "factor"), fixed.getFactor(), "factor");
	  checkFixedValue(errors, path+".lowerLimit", XMLUtil.getNamedChild(focus, "lowerLimit"), fixed.getLowerLimit(), "lowerLimit");
	  checkFixedValue(errors, path+".upperLimit", XMLUtil.getNamedChild(focus, "upperLimit"), fixed.getUpperLimit(), "upperLimit");
	  checkFixedValue(errors, path+".dimensions", XMLUtil.getNamedChild(focus, "dimensions"), fixed.getDimensions(), "dimensions");
	  checkFixedValue(errors, path+".data", XMLUtil.getNamedChild(focus, "data"), fixed.getData(), "data");
  }

	private void checkQuantity(List<ValidationMessage> errors, String path, Element focus, Quantity fixed) {
	  checkFixedValue(errors, path+".value", XMLUtil.getNamedChild(focus, "value"), fixed.getValue(), "value");
	  checkFixedValue(errors, path+".comparator", XMLUtil.getNamedChild(focus, "comparator"), fixed.getComparator(), "comparator");
	  checkFixedValue(errors, path+".units", XMLUtil.getNamedChild(focus, "units"), fixed.getUnits(), "units");
	  checkFixedValue(errors, path+".system", XMLUtil.getNamedChild(focus, "system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".code", XMLUtil.getNamedChild(focus, "code"), fixed.getCode(), "code");
  }

	private boolean check(String v1, String v2) {
	  return v1 == null ? Utilities.noString(v1) : v1.equals(v2);
  }

	private Element getExtensionByUrl(List<Element> extensions, String urlSimple) {
	  for (Element e : extensions) {
	  	if (urlSimple.equals(XMLUtil.getNamedChildValue(e, "url")))
	  		return e;
	  }
		return null;
  }

	private String typeCode(List<TypeRefComponent> types) {
	  StringBuilder b = new StringBuilder();
	  for (TypeRefComponent t : types) {
	  	b.append("|"+t.getCodeSimple());	  	
	  }
	  if (b.length() > 0)
	  	return b.substring(1); 
	  else
	  	return null;
  }
}

