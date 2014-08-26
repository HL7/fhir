package org.hl7.fhir.instance.validation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.Attachment;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.BindingConformance;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ExtensionContext;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Range;
import org.hl7.fhir.instance.model.Ratio;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.SampledData;
import org.hl7.fhir.instance.model.Schedule;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.instance.utils.ValueSetExpansionCache;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ExtensionLocatorService.Status;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Element;


/* 
 * todo:
 * check urn's don't start oid: or uuid: 
 */
public class InstanceValidator extends BaseValidator {
  // configuration items

  public abstract class WrapperElement {
    public abstract WrapperElement getNamedChild(String name);
    public abstract WrapperElement getFirstChild();
    public abstract WrapperElement getNextSibling();
    public abstract String getName();
    public abstract String getNamedChildValue(String name);
    public abstract void getNamedChildren(String name, List<WrapperElement> list);
    public abstract String getAttribute(String name);
    public abstract void getNamedChildrenWithWildcard(String name, List<WrapperElement> list);
    public abstract boolean hasAttribute(String name);
    public abstract Object getNamespace();
    public abstract String getText();
  }

  public class DOMWrapperElement extends WrapperElement {

    private Element element;

    public DOMWrapperElement(Element element) {
      super();
      this.element = element;
    }

    @Override
    public WrapperElement getNamedChild(String name) {
      Element res = XMLUtil.getNamedChild(element, name);
      return res == null ? null : new DOMWrapperElement(res);
    }

    @Override
    public WrapperElement getFirstChild() {
      Element res = XMLUtil.getFirstChild(element);
      return res == null ? null : new DOMWrapperElement(res);
    }

    @Override
    public WrapperElement getNextSibling() {
      Element res = XMLUtil.getNextSibling(element);
      return res == null ? null : new DOMWrapperElement(res);
    }

    @Override
    public String getName() {
      return element.getLocalName();
    }

    @Override
    public String getNamedChildValue(String name) {
      return XMLUtil.getNamedChildValue(element, name);
    }

    @Override
    public void getNamedChildren(String name, List<WrapperElement> list) {
      List<Element> el = new ArrayList<Element>();
      XMLUtil.getNamedChildren(element, name, el);
      for (Element e : el)
        list.add(new DOMWrapperElement(e));
    }

    @Override
    public String getAttribute(String name) {
      return element.getAttribute(name);
    }

    @Override
    public void getNamedChildrenWithWildcard(String name, List<WrapperElement> list) {
      List<Element> el = new ArrayList<Element>();
      XMLUtil.getNamedChildrenWithWildcard(element, name, el);
      for (Element e : el)
        list.add(new DOMWrapperElement(e));
    }

    @Override
    public boolean hasAttribute(String name) {
      return element.hasAttribute(name);
    }

    @Override
    public String getNamespace() {
      return element.getNamespaceURI();
    }

    @Override
    public String getText() {
      return element.getTextContent();
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
  private WorkerContext context;

  private static final String NS_FHIR = "http://hl7.org/fhir";


  private ValueSetExpansionCache cache;
  private boolean suppressLoincSnomedMessages;

  public InstanceValidator(WorkerContext context) throws Exception {
    super();
    source = Source.InstanceValidator;
    cache = new ValueSetExpansionCache(context, null);
    }

  public class ChildIterator {
    private WrapperElement parent;
    private String basePath;
    private int lastCount;
    private WrapperElement child;

    public ChildIterator(String path, WrapperElement elem) {
      parent = elem;
      basePath = path;  
    }

    public boolean next() {
      if (child == null) { 
        child = parent.getFirstChild();
        lastCount = 0;
      } else {
        String lastName = child.getName();
        child = child.getNextSibling();
        if (child != null && child.getName().equals(lastName)) 
          lastCount++;
        else
          lastCount = 0;
      }
      return child != null;
    }

    public String name() {
      return child.getName();
    }

    public WrapperElement element() {
      return child;
    }

    public String path() {
      String sfx = "";
      WrapperElement n = child.getNextSibling();
      if (n != null && n.getName().equals(child.getName())) { 
        sfx = "["+Integer.toString(lastCount)+"]";
      }
      return basePath+"/f:"+name()+sfx;
    }
  }

  public void validateInstance(List<ValidationMessage> errors, Element elem) throws Exception {
    validateInstance(errors, new DOMWrapperElement(elem));
  }
  public void validateInstance(List<ValidationMessage> errors, WrapperElement elem) throws Exception {
    validateInstance(errors, elem, null);  
  }
  
  public void validateInstance(List<ValidationMessage> errors, Element element, Profile profile) throws Exception {
    validateInstance(errors, new DOMWrapperElement(element), profile);
  }
  
  private void validateInstance(List<ValidationMessage> errors, WrapperElement elem, Profile profile) throws Exception {
    boolean feedHasAuthor = elem.getNamedChild("author") != null;
    if (elem.getName().equals("feed")) {
      // for now, if the user specified a profile, and it's a feed, we refuse
      if (rule(errors, "exception", "feed", profile == null, "Cannot validate a feed against a specified profile (TODO: re-assess this)")) {
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
    }
    else
      validate(errors, "", elem, profile);
  }

  public List<ValidationMessage> validateInstance(WrapperElement elem) throws Exception {
    return validateInstance(elem, null);
  }

  public List<ValidationMessage> validateInstance(Element elem, Profile profile) throws Exception {
    return validateInstance(new DOMWrapperElement(elem), profile);
  }
  public List<ValidationMessage> validateInstance(WrapperElement elem, Profile profile) throws Exception {
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    validateInstance(errors, elem);      
    return errors;
  }

  private void validateAtomEntry(List<ValidationMessage> errors, String path, WrapperElement element, boolean feedHasAuthor) throws Exception {
    rule(errors, "invalid", path, element.getNamedChild("title") != null, "Entry must have a title");
    rule(errors, "invalid", path, element.getNamedChild("updated") != null, "Entry must have a last updated time");
    rule(errors, "invalid", path, feedHasAuthor || element.getNamedChild("author") != null, "Entry must have an author because the feed doesn't");



    ChildIterator ci = new ChildIterator(path, element);
    while (ci.next()) {
      if (ci.name().equals("category"))
        validateTag(ci.path(), ci.element(), true);
      else if (ci.name().equals("id"))
        validateId(errors, ci.path(), ci.element(), true);
      else if (ci.name().equals("link"))
        validateLink(errors, ci.path(), ci.element(), true);
      else if (ci.name().equals("content")) {
        WrapperElement r = ci.element().getFirstChild();
        validate(errors, ci.path()+"/f:"+r.getName(), r, null);
      }
    }
  }

  private void validate(List<ValidationMessage> errors, String path, WrapperElement elem, Profile profile) throws Exception {
    if (elem.getName().equals("Binary"))
      validateBinary(elem);
    else {
      Profile p = profile != null ? profile : getProfileForType(elem.getName());
      ProfileStructureComponent s = getStructureForType(p, elem.getName());
      if (rule(errors, "invalid", elem.getName(), s != null, "Unknown Resource Type "+elem.getName())) {
        validateElement(errors, p, s, path+"/f:"+elem.getName(), s.getSnapshot().getElement().get(0), null, null, elem, elem.getName(), null);
        if (elem.getName().equals("Query"))
          validateQuery(errors, elem);
      }
    }
  }

  private void validateQuery(List<ValidationMessage> errors, WrapperElement elem) {
    // TODO - check that parameters match defined ones
    
  }

  private Profile getProfileForType(String localName) throws Exception {
    Profile r = (Profile) getResource(localName);
    if (r == null)
      return null;
    if (r.getStructure().size() != 1 || !(r.getStructure().get(0).getTypeSimple().equals(localName) || r.getStructure().get(0).getNameSimple().equals(localName)))
      throw new Exception("unexpected profile contents");
    return r;
  }

  private ProfileStructureComponent getStructureForType(Profile r, String localName) throws Exception {
    if (r.getStructure().size() != 1 || !(r.getStructure().get(0).getTypeSimple().equals(localName) || r.getStructure().get(0).getNameSimple().equals(localName)))
      throw new Exception("unexpected profile contents");
    ProfileStructureComponent s = r.getStructure().get(0);
    return s;
  }

  private Resource getResource(String id) {
    return context.getProfiles().get(id.toLowerCase()).getResource();
  }

  private void validateBinary(WrapperElement elem) {
    // nothing yet

  }

  private void validateTag(String path, WrapperElement element, boolean onEntry) {
    // nothing yet

  }

  private void validateLink(List<ValidationMessage> errors, String path, WrapperElement element, boolean onEntry) {
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

  private void validateId(List<ValidationMessage> errors, String path, WrapperElement element, boolean onEntry) {
    if (rule(errors, "invalid", path, !Utilities.noString(element.getText()), "id is empty"))
      rule(errors, "invalid", path, isAbsoluteUrl(element.getText()), "Id '"+element.getText()+"' is not an absolute URI (must start with http:, https:, urn:, cid:");
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

  private void validateElement(List<ValidationMessage> errors, Profile profile, ProfileStructureComponent structure, String path, ElementComponent definition, Profile cprofile, ElementComponent context, WrapperElement element, String actualType, ExtensionLocatorService.ExtensionLocationResponse extensionContext) throws Exception {
    // irrespective of what element it is, it cannot be empty
    if (NS_FHIR.equals(element.getNamespace())) {
      rule(errors, "invalid", path, !empty(element), "Elements must have some content (@value, @id, extensions, or children elements)");
    }
    Map<String, ElementComponent> children = ProfileUtilities.getChildMap(structure, definition.getPathSimple());
    ChildIterator ci = new ChildIterator(path, element);
    while (ci.next()) {
      ElementComponent child = children.get(ci.name());
      String type = null;
      if (ci.name().equals("extension")) 
      {
        type = "Extension";
        child = definition; // it's going to be used as context below
      } 
      else if (child == null) 
      {
        child = getDefinitionByTailNameChoice(children, ci.name());
        if (child != null)
          type = ci.name().substring(tail(child.getPathSimple()).length() - 3);
        if ("Resource".equals(type))
          type = "ResourceReference";
      } 
      else 
      {
        if (child.getDefinition().getType().size() == 1)
          type = child.getDefinition().getType().get(0).getCodeSimple();
          else if (child.getDefinition().getType().size() > 1 )
          {
        	  TypeRefComponent trc = child.getDefinition().getType().get(0);
        	  
        	  if(trc.getCodeSimple().equals("ResourceReference"))
        		  type = "ResourceReference";
        	  else
        		  throw new Exception("multiple types ("+describeTypes(child.getDefinition().getType())+") @ "+path+"/f:"+ci.name());
          }
          
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
          ExtensionLocatorService.ExtensionLocationResponse ec = null;
          if (type.equals("Identifier"))
            checkIdentifier(ci.path(), ci.element(), child);
          else if (type.equals("Coding"))
            checkCoding(errors, ci.path(), ci.element(), profile, child);
          else if (type.equals("CodeableConcept"))
            checkCodeableConcept(errors, ci.path(), ci.element(), profile, child);
          else if (type.equals("Extension"))
            ec = checkExtension(errors, ci.path(), ci.element(), profile, child, actualType, extensionContext);

          if (type.equals("Resource"))
            validateContains(errors, ci.path(), child, definition, ci.element());
          else {
            Profile p = getProfileForType(type); 
            ProfileStructureComponent r = getStructureForType(p, type);
            if (rule(errors, "structure", ci.path(), r != null, "Unknown type "+type)) {
              validateElement(errors, p, r, ci.path(), r.getSnapshot().getElement().get(0), profile, child, ci.element(), type, ec);
            }
          }
        }
      } else {
        if (rule(errors, "structure", path, child != null, "Unrecognised Content "+ci.name()))
          validateElement(errors, profile, structure, ci.path(), child, null, null, ci.element(), type, extensionContext);
      }
    }
  }

  private String describeTypes(List<TypeRefComponent> types) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (TypeRefComponent t : types) {
      b.append(t.getCodeSimple());
    }
    return b.toString();
  }

  private ExtensionLocatorService.ExtensionLocationResponse checkExtension(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, ElementComponent container, String parentType, ExtensionLocatorService.ExtensionLocationResponse extensionContext) throws Exception {
    String url = element.getAttribute("url");
    ExtensionLocatorService.ExtensionLocationResponse ext;
    if (url.startsWith("#") && extensionContext != null)
      ext = extensionContext.clone(url.substring(1));
    else
      ext = context.getExtensionLocator().locateExtension(url);
      
    if (ext.getStatus() == Status.NotAllowed) {
    	rule(errors, "structure", path+"[url='"+url+"']", false, "This extension cannot be used here ("+ext.getMessage()+")");
    } else if (ext.getStatus() == Status.Located) {
      ElementComponent elementComp = null;
    	// two questions 
      // 1. can this extension be used here?
      boolean ok = false;
      if (url.startsWith("#") && extensionContext != null) {
        elementComp = getElementByPath(ext.getDefinition(), url.substring(1));
        ok = rule(errors, "structure", path+"[url='"+url+"']", elementComp != null, "Unknown child path in extension ("+extensionContext.getUrl()+" / "+url+")");
      } else {
        elementComp = ext.getDefinition().getElement().get(0);
        ok = checkExtensionContext(errors, path+"[url='"+url+"']", ext.getDefinition(), container, parentType, ext.getUrl());
      }
      // 2. is the content of the extension valid?
      if (ok && elementComp.getDefinition().getType().size() > 0) { // if 0, then this just contains extensions
        if (elementComp.getDefinition().getType().size() > 1) 
          throw new Error("exceptions with multiple types are not yet handled");
        String cs = elementComp.getDefinition().getType().get(0).getCodeSimple();
        if (cs.contains("("))
          cs = cs.substring(0, cs.indexOf("("));
        WrapperElement child = element.getFirstChild();
        while (child != null && child.getName().equals("extension"))
          child = child.getNextSibling();
        boolean cok = false;
        if (cs.equals("*")) {
          cok = rule(errors, "structure", path+"[url='"+url+"']", child != null && child.getName().startsWith("value") && isKnownType(child.getName().substring(5)), "No Extension value found (looking for '*')");
          cs = child.getName().substring(5);
        } else {
          cok = rule(errors, "structure", path+"[url='"+url+"']", child != null && child.getName().startsWith("value") && child.getName().substring(5).equals(Utilities.capitalize(cs)), "No Extension value found (looking for '"+cs+"')");
        }
        if (cok) {
          Profile type = context.getProfiles().get(cs).getResource();
          ElementComponent ec = new ElementComponent(); // gimmy up a fake element component for the next call
          ec.setPathSimple(path+"[url='"+url+"']");
          ec.setNameSimple(child.getName());
          ec.setDefinition(elementComp.getDefinition());
          if (type != null) 
            validateElement(errors, profile, null, path+"[url='"+url+"']."+child.getName(), ec, null, null, child, "Extension", extensionContext);
          else {
            checkPrimitive(errors, path+"[url='"+url+"']."+child.getName(), cs, ec, child);
            // special: check vocabulary. Mostly, this isn't needed on a code, but it is with extension
            if (cs.equals("code"))  {
              ElementDefinitionBindingComponent binding = elementComp.getDefinition().getBinding();
              if (binding != null) {
                if (warning(errors, "code-unknown", path, binding.getReference() != null && binding.getReference() instanceof ResourceReference, "Binding for "+path+" missing or cannot be processed")) {
                  if (binding.getReference() != null && binding.getReference() instanceof ResourceReference) {
                    ValueSet vs = resolveBindingReference(binding.getReference());
                    if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
                      try {
                        vs = cache.getExpander().expand(vs).getValueset();
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
    return ext;
  }

  private boolean isKnownType(String code) {
    return context.getProfiles().get(code.toLowerCase()) != null; 
  }

  private ElementComponent getElementByPath(ProfileExtensionDefnComponent definition, String path) {
    for (ElementComponent e : definition.getElement()) {
      if (e.getPathSimple().equals(path))
        return e;
    }
    return null;
  }

  private boolean checkExtensionContext(List<ValidationMessage> errors, String path, ProfileExtensionDefnComponent definition, ElementComponent container, String parentType, String extensionParent) {
	  if (definition.getContextTypeSimple() == ExtensionContext.datatype) {
	  	boolean ok = false;
	  	for (StringType ct : definition.getContext()) 
	  		if (ct.getValue().equals("*") || ct.getValue().equals(parentType))
	  				ok = true;
	      return rule(errors, "structure", path, ok, "This extension is not allowed to be used with the type "+parentType);
	  } else if (definition.getContextTypeSimple() == ExtensionContext.extension) {
      boolean ok = false;
      for (StringType ct : definition.getContext()) 
        if (ct.getValue().equals("*") || ct.getValue().equals(extensionParent))
            ok = true;
      return rule(errors, "structure", path, ok, "This extension is not allowed to be used with the extension '"+extensionParent+"'");
	  } else if (definition.getContextTypeSimple() == ExtensionContext.mapping) {
  		throw new Error("Not handled yet");	  	
	  } else if (definition.getContextTypeSimple() == ExtensionContext.resource) {
      boolean ok = false;
      String simplePath = container.getPathSimple();
//      System.out.println(simplePath);
      if (simplePath.endsWith(".extension") || simplePath.endsWith(".modifierExtension")) 
        simplePath = simplePath.substring(0, simplePath.lastIndexOf('.'));
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (StringType ct : definition.getContext()) {
        b.append(ct.getValue());
        if (ct.getValue().equals("*") || ct.getValue().equals(parentType) || simplePath.equals(ct.getValue()) || simplePath.endsWith("."+ct.getValue()))
            ok = true;
      }
      return rule(errors, "structure", path, ok, "This extension is not allowed to be used with the resource "+(parentType == null ? simplePath : parentType)+" (allowed: "+b.toString()+")");
	  } else 
  		throw new Error("Unknown context type");	  	
  }
//
//  private String simplifyPath(String path) {
//    String s = path.replace("/f:", ".");
//    while (s.contains("[")) 
//      s = s.substring(0, s.indexOf("["))+s.substring(s.indexOf("]")+1);
//    String[] parts = s.split("\\.");
//    int i = 0;
//    while (i < parts.length && !context.getProfiles().containsKey(parts[i].toLowerCase()))
//      i++;
//    if (i >= parts.length)
//      throw new Error("Unable to process part "+path);
//    int j = parts.length - 1;
//    while (j > 0 && (parts[j].equals("extension") || parts[j].equals("modifierExtension")))
//        j--;
//    StringBuilder b = new StringBuilder();
//    boolean first = true;
//    for (int k = i; k <= j; k++) {
//      if (k == j || !parts[k].equals(parts[k+1])) {
//        if (first)
//          first = false;
//        else
//        b.append(".");
//      b.append(parts[k]);
//    }
//    }
//    return b.toString();
//  }
//

  private boolean empty(WrapperElement element) {
    if (element.hasAttribute("value"))
      return false;
    if (element.hasAttribute("id"))
      return false;
    if (element.hasAttribute("xml:id"))
      return false;
    WrapperElement child = element.getFirstChild();
    while (child != null) {
      if (NS_FHIR.equals(child.getNamespace()))
        return false;        
    }
    return true;
  }

  private ElementComponent findElement(ProfileStructureComponent structure, String name) {
    for (ElementComponent c : structure.getSnapshot().getElement()) {
      if (c.getPathSimple().equals(name)) {
        return c;
      }
    }
    return null;
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

  private void validateContains(List<ValidationMessage> errors, String path, ElementComponent child, ElementComponent context, WrapperElement element) throws Exception {
    WrapperElement e = element.getFirstChild();
    validate(errors, path, e, null);    
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

  private void checkPrimitive(List<ValidationMessage> errors, String path, String type, ElementComponent context, WrapperElement e) {
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

  private void checkIdentifier(String path, WrapperElement element, ElementComponent context) {

  }

  private void checkCoding(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, ElementComponent context) {
    String code = element.getNamedChildValue("code");
    String system = element.getNamedChildValue("system");
    String display = element.getNamedChildValue("display");

    if (system != null && code != null) {
      if (checkCode(errors, path, code, system, display)) 
        if (context != null && context.getDefinition().getBinding() != null) {
          ElementDefinitionBindingComponent binding = context.getDefinition().getBinding();
          if (warning(errors, "code-unknown", path, binding != null, "Binding for "+path+" missing")) {
            if (binding.getReference() != null && binding.getReference() instanceof ResourceReference) {
              ValueSet vs = resolveBindingReference(binding.getReference());
              if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
                try {
                  vs = cache.getExpander().expand(vs).getValueset();
                  if (warning(errors, "code-unknown", path, vs != null, "Unable to expand value set for "+describeReference(binding.getReference()))) {
                    warning(errors, "code-unknown", path, codeInExpansion(vs, system, code), "Code {"+system+"}"+code+" is not in value set "+describeReference(binding.getReference())+" ("+vs.getIdentifierSimple()+")");
                  }
                } catch (Exception e) {
                  if (e.getMessage() == null)
                    warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getIdentifierSimple()+" for "+describeReference(binding.getReference())+": --Null--");
//                  else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct"))
//                    hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Snomed value set - not validated");
//                  else if (!e.getMessage().contains("unable to find value set http://loinc.org"))
//                    hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Loinc value set - not validated");
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
    if (reference instanceof UriType)
      return context.getValueSets().get(((UriType) reference).getValue().toString()).getResource();
    else if (reference instanceof ResourceReference)
      return context.getValueSets().get(((ResourceReference) reference).getReferenceSimple()).getResource();
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

  private void checkCodeableConcept(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, ElementComponent context) {
    if (context != null && context.getDefinition().getBinding() != null) {
      ElementDefinitionBindingComponent binding = context.getDefinition().getBinding();
      if (warning(errors, "code-unknown", path, binding != null, "Binding for "+path+" missing (cc)")) {
        if (binding.getReference() != null && binding.getReference() instanceof ResourceReference) {
          ValueSet vs = resolveBindingReference(binding.getReference());
          if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
            try {
              ValueSetExpansionOutcome exp = cache.getExpander().expand(vs);
              vs = exp.getValueset();
              if (warning(errors, "code-unknown", path, vs != null, "Unable to expand value set for "+describeReference(binding.getReference()))) {
                boolean found = false;
                boolean any = false;
                WrapperElement c = element.getFirstChild();
                while (c != null) {
                  if (c.getName().equals("coding")) {
                    any = true;
                    String system = c.getNamedChildValue("system");
                    String code = c.getNamedChildValue("code");
                    if (system != null && code != null)
                      found = found || codeInExpansion(vs, system, code);
                  }
                  c = c.getNextSibling();
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
//              } else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct")) {
//                hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Snomed value set - not validated");
//              } else if (!e.getMessage().contains("unable to find value set http://loinc.org")) { 
//                hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Loinc value set - not validated");
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
    if (reference instanceof UriType)
      return ((UriType)reference).getValue();
    if (reference instanceof ResourceReference)
      return ((ResourceReference)reference).getReference().getValue();
    return "??";
  }


  private boolean checkCode(List<ValidationMessage> errors, String path, String code, String system, String display) {
    if (context.getTerminologyServices() != null && context.getTerminologyServices().verifiesSystem(system)) {
      org.hl7.fhir.instance.utils.TerminologyServices.ValidationResult s = context.getTerminologyServices().validateCode(system, code, display);
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
    return context.getCodeSystems().get(system).getResource();
  }

  public boolean isSuppressLoincSnomedMessages() {
    return suppressLoincSnomedMessages;
  }

  public void setSuppressLoincSnomedMessages(boolean suppressLoincSnomedMessages) {
    this.suppressLoincSnomedMessages = suppressLoincSnomedMessages;
  }

  public void validateInstanceByProfile(List<ValidationMessage> errors, Element root, Profile profile) throws Exception {
    validateInstanceByProfile(errors, new DOMWrapperElement(root), profile);
  }
  
  public void validateInstanceByProfile(List<ValidationMessage> errors, WrapperElement root, Profile profile) throws Exception {
    // we assume that the following things are true: 
    // the instance at root is valid against the schema and schematron
    // the instance validator had no issues against the base resource profile
    if (root.getName().equals("feed")) {
      // throw new Exception("not done yet");
      warning(errors, "invalid", "feed", false, "Validating feeds is not done yet");
    }
    else {
      // so the first question is what to validate against
      ProfileStructureComponent sc = null;
      for (ProfileStructureComponent s : profile.getStructure()) {
        if (root.getName().equals(s.getTypeSimple())) {
          if (sc == null)
            sc = s;
          else
            throw new Exception("the profile contains multiple matches for the resource "+root.getName()+" and the profile cannot be validated against");
        }
      }
      if (rule(errors, "invalid", root.getName(), sc != null, "Profile does not allow for this resource")) {
        // well, does it conform to the resource?
        // this is different to the case above because there may be more than one option at each point, and we could conform to any one of them
        checkByProfile(errors, root.getName(), root, profile, sc, sc.getSnapshot().getElement().get(0));
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
      int i = structure.getSnapshot().getElement().indexOf(elementDefn) + 1;
      String lead = elementDefn.getPathSimple();
      while (i < structure.getSnapshot().getElement().size()) {
        String name = structure.getSnapshot().getElement().get(i).getPathSimple();
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
          list.add(structure.getSnapshot().getElement().get(i));
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

  private void checkByProfile(List<ValidationMessage> errors, String path, WrapperElement focus, Profile profile, ProfileStructureComponent sc, ElementComponent elementDefn) throws Exception {
    // we have an element, and the structure that describes it. 
    // we know that's it's valid against the underlying spec - is it valid against this one?
    // in the instance validator above, we assume that schema or schmeatron has taken care of cardinalities, but here, we have no such reliance. 
    // so the walking algorithm is different: we're going to walk the definitions
    String type;
  	if (elementDefn.getPathSimple().endsWith("[x]")) {
  		String tail = elementDefn.getPathSimple().substring(elementDefn.getPathSimple().lastIndexOf(".")+1, elementDefn.getPathSimple().length()-3);
  		type = focus.getName().substring(tail.length());
  		rule(errors, "structure", path, typeAllowed(type, elementDefn.getDefinition().getType()), "The type '"+type+"' is not allowed at this point (must be one of '"+typeSummary(elementDefn)+")");
  	} else {
  		if (elementDefn.getDefinition().getType().size() == 1) {
  			type = elementDefn.getDefinition().getType().size() == 0 ? null : elementDefn.getDefinition().getType().get(0).getCodeSimple();
  		} else
  			type = null;
  	}
  	// constraints:
  	for (ElementDefinitionConstraintComponent c : elementDefn.getDefinition().getConstraint()) 
  		checkConstraint(errors, path, focus, c);
  	if (elementDefn.getDefinition().getBinding() != null && type != null)
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
  			List<WrapperElement> children = new ArrayList<WrapperElement>(); 
  			focus.getNamedChildrenWithWildcard(walker.name(), children);

  			if (children.size() == 0) {
  				// well, there's no children - should there be? 
  				for (ElementComponent defn : childset) {
  					if (!rule(errors,"required", path, defn.getDefinition() == null || defn.getDefinition().getMinSimple() == 0, "Required Element '"+walker.name()+"' missing"))
  						break; // no point complaining about missing ones after the first one
  				} 
  			} else if (childset.size() == 1) {
  				// simple case: one possible definition, and one or more children. 
  				rule(errors, "cardinality", path, childset.get(0).getDefinition().getMaxSimple().equals("*") || Integer.parseInt(childset.get(0).getDefinition().getMaxSimple()) >= children.size(),
  						"Too many elements for '"+walker.name()+"'"); // todo: sort out structure
  				for (WrapperElement child : children) {
  					checkByProfile(errors, childset.get(0).getPathSimple(), child, profile, sc, childset.get(0));
  				}
  			} else { 
  				// ok, this is the full case - we have a list of definitions, and a list of candidates for meeting those definitions. 
  				// we need to decide *if* that match a given definition
  			}
  		}
  	}
  }

	private void checkBinding(List<ValidationMessage> errors, String path, WrapperElement focus, Profile profile, ElementComponent elementDefn, String type) {
	  ElementDefinitionBindingComponent bc = elementDefn.getDefinition().getBinding();

	  if (bc != null && bc.getReference() != null && bc.getReference() instanceof ResourceReference) {
      String url = ((ResourceReference) bc.getReference()).getReferenceSimple();
	  	ValueSet vs = resolveValueSetReference(profile, (ResourceReference) bc.getReference());
	  	if (vs == null) {
	      rule(errors, "structure", path, false, "Cannot check binding on type '"+type+"' as the value set '"+url+"' could not be located");
      } else if (type.equals("code"))
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

	private void checkBindingCode(List<ValidationMessage> errors, String path, WrapperElement focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCode not done yet");	  
  }

	private void checkBindingCoding(List<ValidationMessage> errors, String path, WrapperElement focus, ValueSet vs) {
	  // rule(errors, "exception", path, false, "checkBindingCoding not done yet");	  
  }

	private void checkBindingCodeableConcept(List<ValidationMessage> errors, String path, WrapperElement focus, ValueSet vs) {
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
	  	if (t.equals("Resource") && Utilities.capitalize(type.getCodeSimple()).equals("ResourceReference"))
	  	  return true;
	  }
	  return false;
  }

	private void checkConstraint(List<ValidationMessage> errors, String path, WrapperElement focus, ElementDefinitionConstraintComponent c) throws Exception {
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

	private void checkPrimitiveByProfile(List<ValidationMessage> errors, String path, WrapperElement focus, ElementComponent elementDefn) {
		// two things to check - length, and fixed value
		String value = focus.getAttribute("value");
		if (elementDefn.getDefinition().getMaxLength() != null) {
			rule(errors, "too long", path, value.length() <= elementDefn.getDefinition().getMaxLengthSimple(), "The value '"+value+"' exceeds the allow length limit of "+Integer.toString(elementDefn.getDefinition().getMaxLengthSimple()));
		}
		if (elementDefn.getDefinition().getValue() != null) {
			checkFixedValue(errors, path, focus, elementDefn.getDefinition().getValue(), "");
		}
  }

	private void checkFixedValue(List<ValidationMessage> errors, String path, WrapperElement focus, org.hl7.fhir.instance.model.Element fixed, String propName) {
		if (fixed == null && focus == null)
			; // this is all good
		else if (fixed == null && focus != null)
	  	rule(errors, "value", path, false, "Unexpected element "+focus.getName());
		else if (fixed != null && focus == null)
	  	rule(errors, "value", path, false, "Mising element "+propName);
		else {
			String value = focus.getAttribute("value");
			if (fixed instanceof org.hl7.fhir.instance.model.BooleanType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.BooleanType) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.BooleanType) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.IntegerType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.IntegerType) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.IntegerType) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DecimalType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.DecimalType) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DecimalType) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Base64BinaryType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Base64BinaryType) fixed).getStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Base64BinaryType) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.InstantType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.InstantType) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.InstantType) fixed).getStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.StringType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.StringType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.StringType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.UriType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.UriType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.UriType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DateType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.DateType) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DateType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DateTimeType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DateTimeType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.OidType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.OidType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.OidType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.UuidType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.UuidType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.UuidType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.CodeType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.CodeType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.CodeType) fixed).getValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.IdType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.IdType) fixed).getValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.IdType) fixed).getValue()+"'");
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
			List<WrapperElement> extensions = new ArrayList<WrapperElement>();
			focus.getNamedChildren("extension", extensions);
			if (fixed.getExtensions().size() == 0) {
				rule(errors, "value", path, extensions.size() == 0, "No extensions allowed");
			} else if (rule(errors, "value", path, extensions.size() == fixed.getExtensions().size(), "Extensions count mismatch: expected "+Integer.toString(fixed.getExtensions().size())+" but found "+Integer.toString(extensions.size()))) {
				for (Extension e : fixed.getExtensions()) {
				  WrapperElement ex = getExtensionByUrl(extensions, e.getUrlSimple());
					if (rule(errors, "value", path, ex != null, "Extension count mismatch: unable to find extension: "+e.getUrlSimple())) {
						checkFixedValue(errors, path, ex.getFirstChild().getNextSibling(), e.getValue(), "extension.value");
					}
				}
			}
		}
  }

	private void checkAddress(List<ValidationMessage> errors, String path, WrapperElement focus, Address fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getText(), "text");
	  checkFixedValue(errors, path+".city", focus.getNamedChild("city"), fixed.getCity(), "city");
	  checkFixedValue(errors, path+".state", focus.getNamedChild("state"), fixed.getState(), "state");
	  checkFixedValue(errors, path+".country", focus.getNamedChild("country"), fixed.getCountry(), "country");
	  checkFixedValue(errors, path+".zip", focus.getNamedChild("zip"), fixed.getZip(), "zip");
	  
		List<WrapperElement> lines = new ArrayList<WrapperElement>();
		focus.getNamedChildren( "line", lines);
		if (rule(errors, "value", path, lines.size() == fixed.getLine().size(), "Expected "+Integer.toString(fixed.getLine().size())+" but found "+Integer.toString(lines.size())+" line elements")) {
			for (int i = 0; i < lines.size(); i++) 
				checkFixedValue(errors, path+".coding", lines.get(i), fixed.getLine().get(i), "coding");			
		}	  
  }

	private void checkContact(List<ValidationMessage> errors, String path, WrapperElement focus, Contact fixed) {
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValue(), "value");
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");
	  
  }

	private void checkAttachment(List<ValidationMessage> errors, String path, WrapperElement focus, Attachment fixed) {
	  checkFixedValue(errors, path+".contentType", focus.getNamedChild("contentType"), fixed.getContentType(), "contentType");
	  checkFixedValue(errors, path+".language", focus.getNamedChild("language"), fixed.getLanguage(), "language");
	  checkFixedValue(errors, path+".data", focus.getNamedChild("data"), fixed.getData(), "data");
	  checkFixedValue(errors, path+".url", focus.getNamedChild("url"), fixed.getUrl(), "url");
	  checkFixedValue(errors, path+".size", focus.getNamedChild("size"), fixed.getSize(), "size");
	  checkFixedValue(errors, path+".hash", focus.getNamedChild("hash"), fixed.getHash(), "hash");
	  checkFixedValue(errors, path+".title", focus.getNamedChild("title"), fixed.getTitle(), "title");	  
  }

	private void checkIdentifier(List<ValidationMessage> errors, String path, WrapperElement focus, Identifier fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".label", focus.getNamedChild("label"), fixed.getLabel(), "label");
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValue(), "value");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");
	  checkFixedValue(errors, path+".assigner", focus.getNamedChild("assigner"), fixed.getAssigner(), "assigner");
  }

	private void checkCoding(List<ValidationMessage> errors, String path, WrapperElement focus, Coding fixed) {
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".code", focus.getNamedChild("code"), fixed.getCode(), "code");
	  checkFixedValue(errors, path+".display", focus.getNamedChild("display"), fixed.getDisplay(), "display");	  
	  checkFixedValue(errors, path+".primary", focus.getNamedChild("primary"), fixed.getPrimary(), "primary");	  
  }

	private void checkHumanName(List<ValidationMessage> errors, String path, WrapperElement focus, HumanName fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUse(), "use");
	  checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getText(), "text");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");
	  
		List<WrapperElement> parts = new ArrayList<WrapperElement>();
		focus.getNamedChildren( "family", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" family elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".family", parts.get(i), fixed.getFamily().get(i), "family");			
		}	  
		focus.getNamedChildren( "given", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" given elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".given", parts.get(i), fixed.getFamily().get(i), "given");			
		}	  
		focus.getNamedChildren( "prefix", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" prefix elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".prefix", parts.get(i), fixed.getFamily().get(i), "prefix");			
		}	  
		focus.getNamedChildren( "suffix", parts);
		if (rule(errors, "value", path, parts.size() == fixed.getFamily().size(), "Expected "+Integer.toString(fixed.getFamily().size())+" but found "+Integer.toString(parts.size())+" suffix elements")) {
			for (int i = 0; i < parts.size(); i++) 
				checkFixedValue(errors, path+".suffix", parts.get(i), fixed.getFamily().get(i), "suffix");			
		}	  
  }

	private void checkCodeableConcept(List<ValidationMessage> errors, String path, WrapperElement focus, CodeableConcept fixed) {
		checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getText(), "text");
		List<WrapperElement> codings = new ArrayList<WrapperElement>();
		focus.getNamedChildren( "coding", codings);
		if (rule(errors, "value", path, codings.size() == fixed.getCoding().size(), "Expected "+Integer.toString(fixed.getCoding().size())+" but found "+Integer.toString(codings.size())+" coding elements")) {
			for (int i = 0; i < codings.size(); i++) 
				checkFixedValue(errors, path+".coding", codings.get(i), fixed.getCoding().get(i), "coding");			
		}	  
  }

	private void checkSchedule(List<ValidationMessage> errors, String path, WrapperElement focus, Schedule fixed) {
	  checkFixedValue(errors, path+".repeat", focus.getNamedChild("repeat"), fixed.getRepeat(), "value");
	  
		List<WrapperElement> events = new ArrayList<WrapperElement>();
		focus.getNamedChildren( "event", events);
		if (rule(errors, "value", path, events.size() == fixed.getEvent().size(), "Expected "+Integer.toString(fixed.getEvent().size())+" but found "+Integer.toString(events.size())+" event elements")) {
			for (int i = 0; i < events.size(); i++) 
				checkFixedValue(errors, path+".event", events.get(i), fixed.getEvent().get(i), "event");			
		}	  
  }

	private void checkPeriod(List<ValidationMessage> errors, String path, WrapperElement focus, Period fixed) {
	  checkFixedValue(errors, path+".start", focus.getNamedChild("start"), fixed.getStart(), "start");
	  checkFixedValue(errors, path+".end", focus.getNamedChild("end"), fixed.getEnd(), "end");	  
  }

	private void checkRange(List<ValidationMessage> errors, String path, WrapperElement focus, Range fixed) {
	  checkFixedValue(errors, path+".low", focus.getNamedChild("low"), fixed.getLow(), "low");
	  checkFixedValue(errors, path+".high", focus.getNamedChild("high"), fixed.getHigh(), "high");	  
	  
  }

	private void checkRatio(List<ValidationMessage> errors, String path,  WrapperElement focus, Ratio fixed) {
	  checkFixedValue(errors, path+".numerator", focus.getNamedChild("numerator"), fixed.getNumerator(), "numerator");
	  checkFixedValue(errors, path+".denominator", focus.getNamedChild("denominator"), fixed.getDenominator(), "denominator");	  
  }

	private void checkSampledData(List<ValidationMessage> errors, String path, WrapperElement focus, SampledData fixed) {
	  checkFixedValue(errors, path+".origin", focus.getNamedChild("origin"), fixed.getOrigin(), "origin");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");
	  checkFixedValue(errors, path+".factor", focus.getNamedChild("factor"), fixed.getFactor(), "factor");
	  checkFixedValue(errors, path+".lowerLimit", focus.getNamedChild("lowerLimit"), fixed.getLowerLimit(), "lowerLimit");
	  checkFixedValue(errors, path+".upperLimit", focus.getNamedChild("upperLimit"), fixed.getUpperLimit(), "upperLimit");
	  checkFixedValue(errors, path+".dimensions", focus.getNamedChild("dimensions"), fixed.getDimensions(), "dimensions");
	  checkFixedValue(errors, path+".data", focus.getNamedChild("data"), fixed.getData(), "data");
  }

	private void checkQuantity(List<ValidationMessage> errors, String path, WrapperElement focus, Quantity fixed) {
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValue(), "value");
	  checkFixedValue(errors, path+".comparator", focus.getNamedChild("comparator"), fixed.getComparator(), "comparator");
	  checkFixedValue(errors, path+".units", focus.getNamedChild("units"), fixed.getUnits(), "units");
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystem(), "system");
	  checkFixedValue(errors, path+".code", focus.getNamedChild("code"), fixed.getCode(), "code");
  }

	private boolean check(String v1, String v2) {
	  return v1 == null ? Utilities.noString(v1) : v1.equals(v2);
  }

	private WrapperElement getExtensionByUrl(List<WrapperElement> extensions, String urlSimple) {
	  for (WrapperElement e : extensions) {
	  	if (urlSimple.equals(e.getNamedChildValue("url")))
	  		return e;
	  }
		return null;
  }


	public CheckDisplayOption getCheckDisplay() {
		return checkDisplay;
	}

	public void setCheckDisplay(CheckDisplayOption checkDisplay) {
		this.checkDisplay = checkDisplay;
	}
	public WorkerContext getWorkerContext() {
	  return context;
  }
	
	
}

