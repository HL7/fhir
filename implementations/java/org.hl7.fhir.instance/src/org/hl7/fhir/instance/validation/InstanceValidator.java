package org.hl7.fhir.instance.validation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.Attachment;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ContactPoint;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.BindingConformance;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.ExtensionDefinition.ExtensionContext;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Quantity;
import org.hl7.fhir.instance.model.Range;
import org.hl7.fhir.instance.model.Ratio;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.SampledData;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Timing;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.instance.utils.ValueSetExpansionCache;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.utils.WorkerContext.ExtensionDefinitionResult;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/* 
 * todo:
 * check urn's don't start oid: or uuid: 
 */
public class InstanceValidator extends BaseValidator implements IResourceValidator {
  
  // configuration items
  private CheckDisplayOption checkDisplay;
  @Override
  public CheckDisplayOption getCheckDisplay() {
    return checkDisplay;
  }
  @Override
  public void setCheckDisplay(CheckDisplayOption checkDisplay) {
    this.checkDisplay = checkDisplay;
  }
  

  // used during the build process to keep the overall volume of messages down
  private boolean suppressLoincSnomedMessages;
  public boolean isSuppressLoincSnomedMessages() {
    return suppressLoincSnomedMessages;
  }
  public void setSuppressLoincSnomedMessages(boolean suppressLoincSnomedMessages) {
    this.suppressLoincSnomedMessages = suppressLoincSnomedMessages;
  }

  public boolean isRequireResourceId() {
    return requiresResourceId;
  }
  public void setRequireResourceId(boolean requiresResourceId) {
    this.requiresResourceId = requiresResourceId;
  }
  
  // public API

  @Override
  public List<ValidationMessage> validate(Element element) throws Exception {
    List<ValidationMessage> results = new ArrayList<ValidationMessage>();
    validate(results, element);
    return results;
  }
  @Override
  public List<ValidationMessage> validate(Element element, String profile) throws Exception {
    List<ValidationMessage> results = new ArrayList<ValidationMessage>();
    validate(results, element, profile);
    return results;
  }
  @Override
  public List<ValidationMessage> validate(Element element, Profile profile) throws Exception {
    List<ValidationMessage> results = new ArrayList<ValidationMessage>();
    validate(results, element, profile);
    return results;
  }
  
  @Override
  public List<ValidationMessage> validate(Document document) throws Exception {
    List<ValidationMessage> results = new ArrayList<ValidationMessage>();
    validate(results, document);
    return results;
  }
  @Override
  public List<ValidationMessage> validate(Document document, String profile) throws Exception {
    List<ValidationMessage> results = new ArrayList<ValidationMessage>();
    validate(results, document, profile);
    return results;
  }
  @Override
  public List<ValidationMessage> validate(Document document, Profile profile) throws Exception {
    List<ValidationMessage> results = new ArrayList<ValidationMessage>();
    validate(results, document, profile);
    return results;
  }
  
  @Override
  public void validate(List<ValidationMessage> errors, Element element) throws Exception {
    validateResource(errors, "", new DOMWrapperElement(element), null, requiresResourceId, null);
  }
  @Override
  public void validate(List<ValidationMessage> errors, Element element, String profile) throws Exception {
    Profile p = context.getProfiles().get(profile);
    if (p == null)
      throw new Exception("Profile '"+profile+"' not found");
    validateResource(errors, "", new DOMWrapperElement(element), p, requiresResourceId, null);
  }
  @Override
  public void validate(List<ValidationMessage> errors, Element element, Profile profile) throws Exception {
    validateResource(errors, "", new DOMWrapperElement(element), profile, requiresResourceId, null);
  }
  
  @Override
  public void validate(List<ValidationMessage> errors, Document document) throws Exception {
  	checkForProcessingInstruction(errors, document);
    validateResource(errors, "", new DOMWrapperElement(document.getDocumentElement()), null, requiresResourceId, null);
  }
  @Override
  public void validate(List<ValidationMessage> errors, Document document, String profile) throws Exception {
  	checkForProcessingInstruction(errors, document);
    Profile p = context.getProfiles().get(profile);
    if (p == null)
      throw new Exception("Profile '"+profile+"' not found");
    validateResource(errors, "", new DOMWrapperElement(document.getDocumentElement()), p, requiresResourceId, null);
  }

  @Override
  public void validate(List<ValidationMessage> errors, Document document, Profile profile) throws Exception {
  	checkForProcessingInstruction(errors, document);
    validateResource(errors, "", new DOMWrapperElement(document.getDocumentElement()), profile, requiresResourceId, null);
  }
  
  
  // implementation
  
  private void checkForProcessingInstruction(List<ValidationMessage> errors, Document document) {
	  Node node = document.getFirstChild();
	  while (node != null) {
	  	rule(errors, "invalid", "(document)", node.getNodeType() != Node.PROCESSING_INSTRUCTION_NODE, "No processing instructions allowed in resources");
	  	node = node.getNextSibling();
	  }
  }

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
    public abstract String getNamespace();
    public abstract boolean doesNamespace();
    
    public abstract String getText();
		public abstract boolean hasNamespace(String string);
		public abstract boolean hasProcessingInstruction();
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
    public boolean doesNamespace() {
      return true;
  }

    @Override
    public String getText() {
      return element.getTextContent();
  }

		@Override
    public boolean hasNamespace(String ns) {
	    for (int i = 0; i < element.getAttributes().getLength(); i++) {
	    	Node a = element.getAttributes().item(i);
	    	if ((a.getNodeName().equals("xmlns") || a.getNodeName().startsWith("xmlns:")) && a.getNodeValue().equals(ns))
	    		return true;
	    }
	    return false;
    }

		@Override
    public boolean hasProcessingInstruction() {
		  Node node = element.getFirstChild();
		  while (node != null) {
		  	if (node.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE)
		  		return true;
		  	node = node.getNextSibling();
		  }
	    return false;
    }

  }

  public class ChildIterator {
    private WrapperElement parent;
    private String basePath;
    private int lastCount;
    private WrapperElement child;

    public ChildIterator(String path, WrapperElement element) {
      parent = element;
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
        sfx = "["+Integer.toString(lastCount+1)+"]";
      }
      return basePath+"/f:"+name()+sfx;
    }
  }

  private WorkerContext context;
  private ProfileUtilities utilities;
  private ValueSetExpansionCache cache;
  private ExtensionDefinitionResult fakeExtension = new ExtensionDefinitionResult(null, null, null);
  private boolean requiresResourceId;

  public InstanceValidator(WorkerContext context) throws Exception {
    super();
    this.context = context;
    source = Source.InstanceValidator;
    cache = new ValueSetExpansionCache(context, null);
    utilities = new ProfileUtilities(context);
  }

  
  public WorkerContext getContext() {
		return context;
	}
	/*
   * The actual base entry point
	 */
  private void validateResource(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, boolean needsId, List<WrapperElement> containers) throws Exception {
    if (containers == null) 
      containers = new ArrayList<InstanceValidator.WrapperElement>();
    containers.add(element);

    // getting going - either we got a profile, or not.
    boolean ok;
    if (element.doesNamespace()) {
      ok = rule(errors, "invalid", "/", element.getNamespace().equals(FormatUtilities.FHIR_NS), "Namespace mismatch - expected '"+FormatUtilities.FHIR_NS+"', found '"+element.getNamespace()+"'"); 
      if (ok) {
        String resourceName = element.getName();
        if (profile == null) {
          profile = context.getProfiles().get("http://hl7.org/fhir/Profile/"+resourceName);
          ok = rule(errors, "invalid", path + "/f:"+resourceName, profile != null, "No profile found for resource type '"+resourceName+"'");
        } else
          ok = rule(errors, "invalid", path + "/f:"+resourceName, profile.getType().equals(resourceName), "Specified profile type was '"+profile.getType()+"', but resource type was '"+resourceName+"'");
	  	}
    } else {
      throw new Error("not done yet");
	  }
    if (ok) {
      rule(errors, "invalid", path + "/f:"+element.getName(), !needsId ||(element.getNamedChild("id") != null), "Resource has no id");
      start(errors, path, element, profile, containers);
  }
  }

  // we assume that the following things are true: 
  // the instance at root is valid against the schema and schematron
  // the instance validator had no issues against the base resource profile
  private void start(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, List<WrapperElement> containers) throws Exception {
    // profile is valid, and matches the resource name 
    if (rule(errors, "structure", element.getName(), profile.hasSnapshot(), "Profile has no snapshort - validation is against the snapshot, so it must be provided")) {
      validateElement(errors, profile, null, path+"/f:"+element.getName(), profile.getSnapshot().getElement().get(0), null, null, element, element.getName(), containers);
      
      checkDeclaredProfiles(errors, path, element, containers);
      
      // specific known special validations 
      if (element.getName().equals("Bundle"))
        validateBundle(errors, element);
    }
  }

//	private String findProfileTag(WrapperElement element) {
//  	String uri = null;
//	  List<WrapperElement> list = new ArrayList<WrapperElement>();
//	  element.getNamedChildren("category", list);
//	  for (WrapperElement c : list) {
//	  	if ("http://hl7.org/fhir/tag/profile".equals(c.getAttribute("scheme"))) {
//	  		uri = c.getAttribute("term");
//	  	}
//	  }
//	  return uri;
//  }
    

  private void checkDeclaredProfiles(List<ValidationMessage> errors, String path, WrapperElement element, List<WrapperElement> containers) throws Exception {
    WrapperElement meta = element.getNamedChild("meta");
    if (meta != null) {
      List<WrapperElement> profiles = new ArrayList<InstanceValidator.WrapperElement>();
      meta.getNamedChildren("profile", profiles);
      int i = 0;
      for (WrapperElement profile : profiles) {
        String ref = profile.getAttribute("value");
        String p = path+"/f:meta/f:profile["+Integer.toString(i)+"]";
        if (rule(errors, "invalid", p, !Utilities.noString(ref), "Profile reference invalid")) {
          Profile pr = context.getProfiles().get(ref);
          if (warning(errors, "invalid", p, pr != null, "Profile reference could not be resolved")) {
            if (rule(errors, "structure", p, pr.hasSnapshot(), "Profile has no snapshort - validation is against the snapshot, so it must be provided")) {
              validateElement(errors, pr, null, path, pr.getSnapshot().getElement().get(0), null, null, element, element.getName(), containers);
            }
          }
          i++;
        }
      }   
    }
  }
  
  private void validateBundle(List<ValidationMessage> errors, WrapperElement bundle) {
    String base = bundle.getNamedChildValue("base");
    rule(errors, "invalid", "Bundle", !"urn:guid:".equals(base), "The base 'urn:guid:' is not valid (use urn:uuid:)");
    
    List<WrapperElement> entries = new ArrayList<WrapperElement>();
    bundle.getNamedChildren("entry", entries);
    WrapperElement firstEntry = null;
    String firstBase = null;
    int i = 0;
    for (WrapperElement entry : entries) {
      String ebase = entry.getNamedChildValue("base");
      rule(errors, "invalid", "f:Bundle/f:entry["+Integer.toString(i)+"]", !"urn:guid:".equals(ebase), "The base 'urn:guid:' is not valid");
      rule(errors, "invalid", "f:Bundle/f:entry["+Integer.toString(i)+"]", !Utilities.noString(base) || !Utilities.noString(ebase), "entry does not have a base");
      if (i == 0) { 
        firstEntry = entry;
        firstBase = ebase == null ? base : ebase;
      }
      i++;
    }
    if (bundle.getNamedChildValue("type").equals("document"))
      if (rule(errors, "invalid", "f:Bundle/f:entry[0]", firstEntry.getNamedChild("resource") != null, "No resource on first entry"))
        validateDocument(errors, bundle, firstEntry.getNamedChild("resource").getFirstChild(), firstBase);
    if (bundle.getNamedChildValue("type").equals("message"))
      validateMessage(errors, bundle);
  }
  
  private void validateMessage(List<ValidationMessage> errors, WrapperElement bundle) {
    // TODO Auto-generated method stub
                                                                                                                                           
  }
  
  
  private void validateDocument(List<ValidationMessage> errors, WrapperElement bundle, WrapperElement composition, String base) {
    // first entry must be a composition
    if (rule(errors, "invalid", "f:Bundle/f:entry[0]/f:resource", composition.getName().equals("Composition"), "The first entry in a document must be a composition")) {
      // the compsotion subject and section references must resolve in the bundle
      validateBundleReference(errors, "f:Bundle/f:entry[0]/f:resource/f:Composition/f:subject", bundle, base, composition.getNamedChild("subject"), "Composition Subject");
      validateSections(errors, "f:Bundle/f:entry[0]/f:resource/f:Composition", base, bundle, composition);      
    }
  }  
  
  private void validateSections(List<ValidationMessage> errors, String path, String base, WrapperElement bundle, WrapperElement focus) {
    List<WrapperElement> sections = new ArrayList<WrapperElement>();
    focus.getNamedChildren("entry", sections);
    int i = 0;
    for (WrapperElement section : sections) {
      validateBundleReference(errors, path+"/f:section["+Integer.toString(i)+"]", bundle, base, section.getNamedChild("content"), "Section Content");    
      validateSections(errors, path+"/f:section["+Integer.toString(i)+"]", base, bundle, section);
      i++;
    }
  }
    
  private void validateBundleReference(List<ValidationMessage> errors, String path, WrapperElement bundle, String base, WrapperElement ref, String name) {
    if (ref != null && !Utilities.noString(ref.getNamedChildValue("reference"))) {
      WrapperElement target = resolveInBundle(bundle, base, ref.getNamedChildValue("reference"));
      rule(errors, "invalid", path+"/f:reference", target != null, "Unable to resolve the target of the reference in the bundle ("+name+")");
    }
  }
  
  private WrapperElement resolveInBundle(WrapperElement bundle, String base, String ref) {
    String target;
    if (ref.startsWith("http:") || ref.startsWith("https:") || ref.startsWith("urn:"))
      target = ref;
    else if ("urn:uuid:".equals(base) || "urn:oid:".equals(base))
      target = base+ref;
    else
      target = Utilities.appendSlash(base)+ref;
        
    List<WrapperElement> entries = new ArrayList<WrapperElement>();
    bundle.getNamedChildren("entry", entries);
    for (WrapperElement entry : entries) {
      String tbase = entry.getNamedChildValue("base");
      if (Utilities.noString(base))
        tbase = bundle.getNamedChildValue("base");
      if (tbase == null)
        tbase = "";
      WrapperElement res = entry.getNamedChild("resource");
      if (res != null)
        res = res.getFirstChild();
      if (res != null) {
        String turl;
        if ("urn:uuid:".equals(base) || "urn:oid:".equals(base))
          turl = tbase+ref;
        else
          turl = appendForwardSlash(tbase)+res.getName()+"/"+res.getNamedChildValue("id");
        if (turl.equals(target)) 
          return res;
      }
    }
    return null;
  }
  
  private String appendForwardSlash(String tbase) {
    if (tbase == null)
      return "/";
    else if (tbase.endsWith("/"))
      return tbase;
    else
      return tbase+"/";
  }
  private Profile getProfileForType(String type) throws Exception {
    return context.getProfiles().get("http://hl7.org/fhir/Profile/"+type);
  }

  private void validateElement(List<ValidationMessage> errors, Profile profile, ExtensionDefinitionResult ex, String path, ElementDefinition definition, Profile cprofile, ElementDefinition context, WrapperElement element, String actualType, List<WrapperElement> containers) throws Exception {
    // irrespective of what element it is, it cannot be empty
  	if (element.doesNamespace()) {
      rule(errors, "invalid", path, FormatUtilities.FHIR_NS.equals(element.getNamespace()), "Namespace mismatch - expected '"+FormatUtilities.FHIR_NS+"', found '"+element.getNamespace()+"'");
      rule(errors, "invalid", path, !element.hasNamespace("http://www.w3.org/2001/XMLSchema-instance"), "Schema Instance Namespace is not allowed in instances");
      rule(errors, "invalid", path, !element.hasProcessingInstruction(), "No Processing Instructions in resources");
  	}
    rule(errors, "invalid", path, !empty(element), "Elements must have some content (@value, extensions, or children elements)");
    
    Map<String, ElementDefinition> children = ProfileUtilities.getChildMap(profile, definition.getPath(), definition.getNameReference());
    for (ElementDefinition child : children.values()) {
    	if (child.getRepresentation().isEmpty()) {
    		List<WrapperElement> list = new ArrayList<WrapperElement>();  
    		element.getNamedChildrenWithWildcard(tail(child.getPath()), list);
    		if (child.getMin() > 0) {
    			rule(errors, "structure", mergePath(path, child.getPath()), list.size() > 0, "Element "+child.getPath()+" is required");
    		}
    		if (child.hasMax() && !child.getMax().equals("*")) {
    			rule(errors, "structure", mergePath(path, child.getPath()), list.size() <= Integer.parseInt(child.getMax()), "Element "+child.getPath()+" can only occur "+child.getMax()+" time"+(child.getMax().equals("1") ? "" : "s"));
    		}
    	}
    }
    ChildIterator ci = new ChildIterator(path, element);
    while (ci.next()) {
      ElementDefinition child = children.get(ci.name());
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
          type = ci.name().substring(tail(child.getPath()).length() - 3);
      } 
      else 
      {
        if (child.getType().size() == 1)
          type = child.getType().get(0).getCode();
          else if (child.getType().size() > 1 )
          {
        	  TypeRefComponent trc = child.getType().get(0);
        	  
        	  if(trc.getCode().equals("Reference"))
        		  type = "Reference";
        	  else
        		  throw new Exception("multiple types ("+describeTypes(child.getType())+") @ "+path+"/f:"+ci.name());
          }
          
        if (type != null) {
          if (type.startsWith("@")) {
            child = findElement(profile, type.substring(1));
            type = null;
          }
        }       
      }
      if (type != null) {
        ExtensionDefinitionResult ec = null;
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
            ec = checkExtension(errors, ci.path(), ci.element(), profile, child, actualType, ex, containers);
          else if (type.equals("Reference"))
            checkReference(errors, ci.path(), ci.element(), profile, child, actualType, ex, containers);

          if (type.equals("Resource"))
            validateContains(errors, ci.path(), child, definition, ci.element(), containers, !isBundleEntry(ci.path())); //    if (str.matches(".*([.,/])work\\1$"))
          else {
            Profile p = getProfileForType(type); 
            if (rule(errors, "structure", ci.path(), p != null, "Unknown type "+type)) {
              validateElement(errors, p, ec, ci.path(), p.getSnapshot().getElement().get(0), profile, child, ci.element(), type, containers);
            }
          }
        }
      } else {
        if (rule(errors, "structure", path, child != null, "Unrecognised Content "+ci.name()))
          validateElement(errors, profile, ex, ci.path(), child, null, null, ci.element(), type, containers);
      }
    }
  }

  private String mergePath(String path1, String path2) {
    // path1 is xpath path
    // path2 is dotted path 
    String[] parts = path2.split("\\.");
    StringBuilder b = new StringBuilder(path1);
    for (int i = 1; i < parts.length -1; i++)
      b.append("/f:"+parts[i]);
    return b.toString();
  }
  private boolean isBundleEntry(String path) {
    String[] parts = path.split("\\/");
    return parts.length > 2 && parts[parts.length-1].startsWith("f:resource") && parts[parts.length-2].startsWith("f:entry["); 
  }
  
  private String describeTypes(List<TypeRefComponent> types) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (TypeRefComponent t : types) {
      b.append(t.getCode());
    }
    return b.toString();
  }

  private void checkReference(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, ElementDefinition container, String parentType, ExtensionDefinitionResult extensionContext, List<WrapperElement> containers) throws Exception {
    String ref = element.getNamedChildValue("reference");
    if (Utilities.noString(ref)) {
      // todo - what should we do in this case?
      hint(errors, "structure", path, !Utilities.noString(element.getNamedChildValue("display")), "A Reference without an actual reference should have a display");
      return; 
    }
    
    WrapperElement we = resolve(ref, containers);
    String ft;
    if (we != null)
      ft = we.getName();
    else
      ft = tryParse(ref);
    if (hint(errors, "structure", path, ft != null, "Unable to determine type of target resource")) {
      boolean ok = false;
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (TypeRefComponent type : container.getType()) {
        if (!ok && type.getCode().equals("Reference")) {
          // we validate as much as we can. First, can we infer a type from the profile? 
          if (!type.hasProfile() || type.getProfile().equals("http://hl7.org/fhir/Profile/Any")) 
            ok = true;
          else {
            String pr = type.getProfile();

            String bt = getBaseType(profile, pr);
            if (rule(errors, "structure", path, bt != null, "Unable to resolve the profile reference '"+pr+"'")) {
              b.append(bt);
              ok = bt.equals(ft);
            } else 
              ok = true; // suppress following check
          }
        }
        if (!ok && type.getCode().equals("*")) {
          ok = true; // can refer to anything
        }
      }
      rule(errors, "structure", path, ok, "Invalid Resource target type. Found "+ft+", but expected one of ("+b.toString()+")");
    }
  }
  
  private WrapperElement resolve(String ref, List<WrapperElement> containers) {
    if (ref.startsWith("#")) {
      // work back through the contained list.
      // really, there should only be one level for this (contained resources cannot contain 
      // contained resources), but we'll leave that to some other code to worry about
      for (int i = containers.size() - 1; i >= 0; i--) {
        WrapperElement res = getContainedById(containers.get(i), ref.substring(1));
        if (res != null)
          return res;
      }
      return null;
    } else {
      // work back through the contained list - if any of them are bundles, try to resolve 
      // the resource in the bundle
      for (int i = containers.size() - 1; i >= 0; i--) {
        if (containers.get(i).getName().equals("Bundle")) {
        WrapperElement res = getFromBundle(containers.get(i), ref.substring(1));
        if (res != null)
          return res;
        }
      }
      
      // todo: consult the external host for resolution 
      return null;
      
    }
  }
  
  private WrapperElement getFromBundle(WrapperElement bundle, String ref) {
    List<WrapperElement> entries = new ArrayList<WrapperElement>();
    bundle.getNamedChildren("entry", entries);
    for (WrapperElement we : entries) {
      WrapperElement res = we.getNamedChild("resource").getFirstChild();
      if (res != null) {
        String url = genFullUrl(bundle.getNamedChildValue("base"), we.getNamedChildValue("base"), res.getName(), res.getNamedChildValue("id"));
        if (url.endsWith(ref))
          return res;
      }
    }
    return null;
  }

  private String genFullUrl(String bundleBase, String entryBase, String type, String id) {
    String base = Utilities.noString(entryBase) ? bundleBase : entryBase;
    if (Utilities.noString(base)) {
      return type+"/"+id;
    } else if ("urn:uuid".equals(base) || "urn:oid".equals(base))
      return base+id;
    else 
      return Utilities.appendSlash(base)+type+"/"+id;
  }
  
  private WrapperElement getContainedById(WrapperElement container, String id) {
    List<WrapperElement> contained = new ArrayList<WrapperElement>();
    container.getNamedChildren("contained", contained);
    for (WrapperElement we : contained) {
      if (id.equals(we.getNamedChildValue("id")))
        return we;
    }
    return null;
  }

  private String tryParse(String ref) {
    String[] parts = ref.split("\\/");
    switch (parts.length) {
    case 1:
      return null;
    case 2:
      return checkResourceType(parts[0]);
    default:
      if (parts[parts.length-2].equals("_history"))
        return checkResourceType(parts[parts.length-4]);
      else
        return checkResourceType(parts[parts.length-2]);
    }
  }
  
  private String checkResourceType(String type) {
    if (context.getProfiles().containsKey("http://hl7.org/fhir/Profile/"+type))
      return type;
    else
      return null;
  }
  private String getBaseType(Profile profile, String pr) {
    if (pr.startsWith("http://hl7.org/fhir/Profile/")) {
      // this just has to be a base type
      return pr.substring(28);
    } else {
      Profile p = resolveProfile(profile, pr);
      if (p == null)
        return null;
      else
        return p.getType();
    }
  }
  
  private Profile resolveProfile(Profile profile, String pr) {
    if (pr.startsWith("#")) {
      for (Resource r : profile.getContained()) {
        if (r.getId().equals(pr.substring(1)) && r instanceof Profile)
          return (Profile) r;
      }
      return null;
    }
    else
      return context.getProfiles().get(pr);
  }
  
  private ExtensionDefinitionResult checkExtension(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, ElementDefinition container, String parentType, ExtensionDefinitionResult extensionContext, List<WrapperElement> containers) throws Exception {
    String url = element.getAttribute("url");
    if (extensionContext == fakeExtension)
      return extensionContext;
    
    ExtensionDefinitionResult ex = context.getExtensionDefinition(extensionContext, url);
    if (ex == null) {
      if (rule(errors, "structure", path+"[url='"+url+"']", allowUnknownExtension(url), "The extension "+url+" is unknown, and not allowed here"))
        ex = fakeExtension;
      else
        warning(errors, "structure", path+"[url='"+url+"']", allowUnknownExtension(url), "Unknown extension "+url);
    } else {
    	// two questions 
      // 1. can this extension be used here?
      if (!ex.isLocal())
        checkExtensionContext(errors, path+"[url='"+url+"']", ex.getExtensionDefinition(), container, parentType, ex.getExtensionDefinition().getUrl());
      
      // 2. is the content of the extension valid?
      if (ex.getElementDefinition().getType().size() > 0) { // if 0, then this just contains extensions
        WrapperElement child = element.getFirstChild();
        while (child != null && child.getName().equals("extension"))
          child = child.getNextSibling();
        boolean cok = false;
        String type = null;
        String tprofile = null;
        if (rule(errors, "structure", path+"[url='"+url+"']", child != null, "No Extension value found")) {
          if (rule(errors, "structure", path+"[url='"+url+"']", child.getName().startsWith("value"), "Unexpected Element '"+child.getName()+"' found")) {
            String tn = child.getName().substring(5);
            for (TypeRefComponent tr : ex.getElementDefinition().getType()) {
              if (Utilities.capitalize(tr.getCode()).equals(tn)) {
                cok = true;
                type = tr.getCode();
                tprofile = tr.getProfile();
              }
            }
            rule(errors, "structure", path+"[url='"+url+"']", cok, "Unexpected type '"+tn+"' found (expected "+ProfileUtilities.typeCode(ex.getElementDefinition().getType())+")");
          }
        }
        if (cok) {
          Profile ptype = (tprofile == null || type.equals("Reference")) ? context.getProfiles().get("http://hl7.org/fhir/Profile/"+type) : context.getProfiles().get(tprofile);
          
          ElementDefinition ec = ptype.getSnapshot().getElement().get(0);
          if (type != null) 
            validateElement(errors, ptype, extensionContext, path+"[url='"+url+"']."+child.getName(), ec, null, null, child, "Extension", containers);
          else {
            checkPrimitive(errors, path+"[url='"+url+"']."+child.getName(), type, ec, child);
            // special: check vocabulary. Mostly, this isn't needed on a code, but it is with extension
            if (type.equals("code"))  {
              ElementDefinitionBindingComponent binding = ex.getElementDefinition().getBinding();
              if (binding != null) {
                if (warning(errors, "code-unknown", path, binding.hasReference() && binding.getReference() instanceof Reference, "Binding for "+path+" missing or cannot be processed")) {
                  if (binding.hasReference() && binding.getReference() instanceof Reference) {
                    ValueSet vs = resolveBindingReference(binding.getReference());
                    if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
                      try {
                        vs = cache.getExpander().expand(vs).getValueset();
                        if (warning(errors, "code-unknown", path, vs != null, "Unable to expand value set for "+describeReference(binding.getReference()))) {
                          warning(errors, "code-unknown", path, codeInExpansion(vs, null, child.getAttribute("value")), "Code "+child.getAttribute("value")+" is not in value set "+describeReference(binding.getReference())+" ("+vs.getUrl()+")");
                        }
                      } catch (Exception e) {
                        warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getReference())+": "+e.getMessage());
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
    return ex;
  }

  private boolean allowUnknownExtension(String url) {
    return url.contains("example.org") || url.contains("acme.com") || url.contains("nema.org");
  }
  
  private boolean isKnownType(String code) {
    return context.getProfiles().get(code.toLowerCase()) != null; 
  }

  private ElementDefinition getElementByPath(ExtensionDefinition definition, String path) {
    for (ElementDefinition e : definition.getElement()) {
      if (e.getPath().equals(path))
        return e;
    }
    return null;
  }

  private boolean checkExtensionContext(List<ValidationMessage> errors, String path, ExtensionDefinition definition, ElementDefinition container, String parentType, String extensionParent) {
	  if (definition.getContextType() == ExtensionContext.DATATYPE) {
	    if (parentType == null)
        return rule(errors, "structure", path, false, "This extension is not allowed to be used on an element that is not a type");
	    else {
	  	boolean ok = false;
	  	for (StringType ct : definition.getContext()) 
	  		if (ct.getValue().equals("*") || ct.getValue().equals(parentType))
	  				ok = true;
	      return rule(errors, "structure", path, ok, "This extension is not allowed to be used with the type "+parentType);
	    }
	  } else if (definition.getContextType() == ExtensionContext.EXTENSION) {
      boolean ok = false;
      for (StringType ct : definition.getContext()) 
        if (ct.getValue().equals("*") || ct.getValue().equals(extensionParent))
            ok = true;
      return rule(errors, "structure", path, ok, "This extension is not allowed to be used with the extension '"+extensionParent+"'");
	  } else if (definition.getContextType() == ExtensionContext.MAPPING) {
  		throw new Error("Not handled yet");	  	
	  } else if (definition.getContextType() == ExtensionContext.RESOURCE) {
      boolean ok = false;
      String simplePath = container.getPath();
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
    if (element.hasAttribute("xml:id"))
      return false;
    WrapperElement child = element.getFirstChild();
    while (child != null) {
      if (FormatUtilities.FHIR_NS.equals(child.getNamespace()))
        return false;        
    }
    return true;
  }

  private ElementDefinition findElement(Profile profile, String name) {
    for (ElementDefinition c : profile.getSnapshot().getElement()) {
      if (c.getPath().equals(name)) {
        return c;
      }
    }
    return null;
  }

  private ElementDefinition getDefinitionByTailNameChoice(Map<String, ElementDefinition> children, String name) {
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

  private void validateContains(List<ValidationMessage> errors, String path, ElementDefinition child, ElementDefinition context, WrapperElement element, List<WrapperElement> containers, boolean needsId) throws Exception {
    WrapperElement e = element.getFirstChild();
    List<WrapperElement> clone = new ArrayList<InstanceValidator.WrapperElement>();
    clone.addAll(containers);
    String resourceName = e.getName();
    Profile profile = this.context.getProfiles().get("http://hl7.org/fhir/Profile/"+resourceName);
    if (rule(errors, "invalid", path + "/f:"+resourceName, profile != null, "No profile found for contained resource of type '"+resourceName+"'"))
      validateResource(errors, path, e, profile, needsId, clone);    
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

  private void checkPrimitive(List<ValidationMessage> errors, String path, String type, ElementDefinition context, WrapperElement e) {
    if (type.equals("uri")) {
      rule(errors, "invalid", path, !e.getAttribute("value").startsWith("oid:"), "URI values cannot start with oid:");
      rule(errors, "invalid", path, !e.getAttribute("value").startsWith("uuid:"), "URI values cannot start with uuid:");
    }
    if (!type.equalsIgnoreCase("string") && e.hasAttribute("value")) {
      if (rule(errors, "invalid", path, e.getAttribute("value").length() > 0, "@value cannot be empty")) {
        warning(errors, "invalid", path, e.getAttribute("value").trim().equals(e.getAttribute("value")), "value should not start or finish with whitespace");
      }
    }
    if (type.equals("dateTime")) {
      rule(errors, "invalid", path, yearIsValid(e.getAttribute("value")), "The value '"+e.getAttribute("value")+"' is not a valid year");
      rule(errors, "invalid", path, e.getAttribute("value").matches("-?[0-9]{4}(-(0[1-9]|1[0-2])(-(0[0-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?)?)?)?"), "Not a valid date time");
      rule(errors, "invalid", path, !hasTime(e.getAttribute("value")) || hasTimeZone(e.getAttribute("value")), "if a date has a time, it must have a timezone");
      
    }
    if (type.equals("instant")) {
      rule(errors, "invalid", path, e.getAttribute("value").matches("-?[0-9]{4}-(0[1-9]|1[0-2])-(0[0-9]|[1-2][0-9]|3[0-1])T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))"), "The instant '"+e.getAttribute("value")+"' is not valid (by regex)");
      rule(errors, "invalid", path, yearIsValid(e.getAttribute("value")), "The value '"+e.getAttribute("value")+"' is not a valid year");      
    }

    // for nothing to check    
  }

  private boolean yearIsValid(String v) {
    if (Utilities.noString(v) || v.length() < 4)
      return false;
    v = v.substring(0, 4);
    if (!Utilities.IsInteger(v))
      return false;
    int i = Integer.parseInt(v);
    return i >= 1800 && i <= 2100;
  }
  private boolean hasTimeZone(String fmt) {
    return fmt.length() > 10 && (fmt.substring(10).contains("-") || fmt.substring(10).contains("-") || fmt.substring(10).contains("+") || fmt.substring(10).contains("Z"));
  }
  
  private boolean hasTime(String fmt) {
    return fmt.contains("T");
  }
  
  private void checkIdentifier(String path, WrapperElement element, ElementDefinition context) {

  }

  private void checkIdentifier(String path, Element element, ElementDefinition context) {

  }

  private void checkQuantity(List<ValidationMessage> errors, String path, Element element, ElementDefinition context, boolean b) {
    String code = XMLUtil.getNamedChildValue(element,  "code");
    String system = XMLUtil.getNamedChildValue(element,  "system");
    String units = XMLUtil.getNamedChildValue(element,  "units");

    if (system != null && code != null) {
      checkCode(errors, path, code, system, units);
    }

  }


  private void checkCoding(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, ElementDefinition context) {
    String code = element.getNamedChildValue("code");
    String system = element.getNamedChildValue("system");
    String display = element.getNamedChildValue("display");

    if (system != null && code != null) {
      if (checkCode(errors, path, code, system, display)) 
        if (context != null && context.getBinding() != null) {
          ElementDefinitionBindingComponent binding = context.getBinding();
          if (warning(errors, "code-unknown", path, binding != null, "Binding for "+path+" missing")) {
            if (binding.hasReference() && binding.getReference() instanceof Reference) {
              ValueSet vs = resolveBindingReference(binding.getReference());
              if (warning(errors, "code-unknown", path, vs != null, "ValueSet "+describeReference(binding.getReference())+" not found")) {
                try {
                  vs = cache.getExpander().expand(vs).getValueset();
                  if (warning(errors, "code-unknown", path, vs != null, "Unable to expand value set for "+describeReference(binding.getReference()))) {
                    warning(errors, "code-unknown", path, codeInExpansion(vs, system, code), "Code {"+system+"}"+code+" is not in value set "+describeReference(binding.getReference())+" ("+vs.getUrl()+")");
                  }
                } catch (Exception e) {
                  if (e.getMessage() == null)
                    warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getReference())+": --Null--");
//                  else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct"))
//                    hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Snomed value set - not validated");
//                  else if (!e.getMessage().contains("unable to find value set http://loinc.org"))
//                    hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Loinc value set - not validated");
                  else
                    warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getReference())+": "+e.getMessage());
                }
              }
            } else if (binding.hasReference())
              hint(errors, "code-unknown", path, false, "Binding by URI reference cannot be checked");
            else 
              hint(errors, "code-unknown", path, false, "Binding has no source, so can't be checked");
          }
        }
    }
  }


  private ValueSet resolveBindingReference(Type reference) {
    if (reference instanceof UriType)
      return context.getValueSets().get(((UriType) reference).getValue().toString());
    else if (reference instanceof Reference)
      return context.getValueSets().get(((Reference) reference).getReference());
    else
      return null;
  }

  private boolean codeInExpansion(ValueSet vs, String system, String code) {
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
      if (code.equals(c.getCode()) && (system == null || system.equals(c.getSystem())))
        return true;
      if (codeinExpansion(c, system, code)) 
        return true;
    }
    return false;
  }

  private boolean codeinExpansion(ValueSetExpansionContainsComponent cnt, String system, String code) {
    for (ValueSetExpansionContainsComponent c : cnt.getContains()) {
      if (code.equals(c.getCode()) && system.equals(c.getSystem().toString()))
        return true;
      if (codeinExpansion(c, system, code)) 
        return true;
    }
    return false;
  }

  private void checkCodeableConcept(List<ValidationMessage> errors, String path, WrapperElement element, Profile profile, ElementDefinition context) {
    if (context != null && context.hasBinding()) {
      ElementDefinitionBindingComponent binding = context.getBinding();
      if (warning(errors, "code-unknown", path, binding != null, "Binding for "+path+" missing (cc)")) {
        if (binding.hasReference() && binding.getReference() instanceof Reference) {
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
                if (!any && binding.getConformance() == BindingConformance.REQUIRED)
                  warning(errors, "code-unknown", path, found, "No code provided, and value set "+describeReference(binding.getReference())+" ("+vs.getUrl()+") is required");
                if (any)
                  if (binding.getConformance() == BindingConformance.EXAMPLE)
                    hint(errors, "code-unknown", path, found, "None of the codes are in the example value set "+describeReference(binding.getReference())+" ("+vs.getUrl()+")");
                  else 
                    warning(errors, "code-unknown", path, found, "None of the codes are in the expected value set "+describeReference(binding.getReference())+" ("+vs.getUrl()+")");
              }
            } catch (Exception e) {
              if (e.getMessage() == null) {
                warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getReference())+": --Null--");
//              } else if (!e.getMessage().contains("unable to find value set http://snomed.info/sct")) {
//                hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Snomed value set - not validated");
//              } else if (!e.getMessage().contains("unable to find value set http://loinc.org")) { 
//                hint(errors, "code-unknown", path, suppressLoincSnomedMessages, "Loinc value set - not validated");
              } else
                warning(errors, "code-unknown", path, false, "Exception opening value set "+vs.getUrl()+" for "+describeReference(binding.getReference())+": "+e.getMessage());
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
    if (reference instanceof Reference)
      return ((Reference)reference).getReference();
    return "??";
  }


  private boolean checkCode(List<ValidationMessage> errors, String path, String code, String system, String display) {
    if (context.getTerminologyServices() != null && context.getTerminologyServices().verifiesSystem(system)) {
      org.hl7.fhir.instance.utils.ITerminologyServices.ValidationResult s = context.getTerminologyServices().validateCode(system, code, display);
      if (s == null)
        return true;
      if (s.getSeverity() == IssueSeverity.INFORMATION)
        hint(errors, "code-unknown", path, s == null, s.getMessage());
      else if (s.getSeverity() == IssueSeverity.WARNING)
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
          ConceptDefinitionComponent def = getCodeDefinition(vs, code); 
          if (warning(errors, "code-unknown", path, def != null, "Unknown Code ("+system+"#"+code+")"))
            return warning(errors, "code-unknown", path, display == null || display.equals(def.getDisplay()), "Display should be '"+def.getDisplay()+"'");
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

  private ConceptDefinitionComponent getCodeDefinition(ConceptDefinitionComponent c, String code) {
    if (code.equals(c.getCode()))
      return c;
    for (ConceptDefinitionComponent g : c.getConcept()) {
      ConceptDefinitionComponent r = getCodeDefinition(g, code);
      if (r != null)
        return r;
    }
    return null;
  }

  private ConceptDefinitionComponent getCodeDefinition(ValueSet vs, String code) {
    for (ConceptDefinitionComponent c : vs.getDefine().getConcept()) {
      ConceptDefinitionComponent r = getCodeDefinition(c, code);
      if (r != null)
        return r;
    }
    return null;
  }

  private ValueSet getValueSet(String system) {
    return context.getCodeSystems().get(system);
  }


  public class ProfileStructureIterator {

    private Profile profile;
    private ElementDefinition elementDefn;
    private List<String> names = new ArrayList<String>();
    private Map<String, List<ElementDefinition>> children = new HashMap<String, List<ElementDefinition>>();
    private int cursor;

    public ProfileStructureIterator(Profile profile, ElementDefinition elementDefn) {
      this.profile = profile;        
      this.elementDefn = elementDefn;
      loadMap();
      cursor = -1;
    }

    private void loadMap() {
      int i = profile.getSnapshot().getElement().indexOf(elementDefn) + 1;
      String lead = elementDefn.getPath();
      while (i < profile.getSnapshot().getElement().size()) {
        String name = profile.getSnapshot().getElement().get(i).getPath();
        if (name.length() <= lead.length()) 
          return; // cause we've got to the end of the possible matches
        String tail = name.substring(lead.length()+1);
        if (Utilities.isToken(tail) && name.substring(0, lead.length()).equals(lead)) {
          List<ElementDefinition> list = children.get(tail);
          if (list == null) {
            list = new ArrayList<ElementDefinition>();
            names.add(tail);
            children.put(tail, list);
          }
          list.add(profile.getSnapshot().getElement().get(i));
        }
        i++;
      }
    }

    public boolean more() {
      cursor++;
      return cursor < names.size();
    }

    public List<ElementDefinition> current() {
      return children.get(name());
    }

    public String name() {
      return names.get(cursor);
    }

  }

  private void checkByProfile(List<ValidationMessage> errors, String path, WrapperElement focus, Profile profile, ElementDefinition elementDefn) throws Exception {
    // we have an element, and the structure that describes it. 
    // we know that's it's valid against the underlying spec - is it valid against this one?
    // in the instance validator above, we assume that schema or schmeatron has taken care of cardinalities, but here, we have no such reliance. 
    // so the walking algorithm is different: we're going to walk the definitions
    String type;
  	if (elementDefn.getPath().endsWith("[x]")) {
  		String tail = elementDefn.getPath().substring(elementDefn.getPath().lastIndexOf(".")+1, elementDefn.getPath().length()-3);
  		type = focus.getName().substring(tail.length());
  		rule(errors, "structure", path, typeAllowed(type, elementDefn.getType()), "The type '"+type+"' is not allowed at this point (must be one of '"+typeSummary(elementDefn)+")");
  	} else {
  		if (elementDefn.getType().size() == 1) {
  			type = elementDefn.getType().size() == 0 ? null : elementDefn.getType().get(0).getCode();
  		} else
  			type = null;
  	}
  	// constraints:
  	for (ElementDefinitionConstraintComponent c : elementDefn.getConstraint()) 
  		checkConstraint(errors, path, focus, c);
  	if (elementDefn.hasBinding() && type != null)
  		checkBinding(errors, path, focus, profile, elementDefn, type);
  	
  	// type specific checking:
  	if (type != null && typeIsPrimitive(type)) {
  		checkPrimitiveByProfile(errors, path, focus, elementDefn);
  	} else {
  		if (elementDefn.hasFixed())
  			checkFixedValue(errors, path, focus, elementDefn.getFixed(), "");
  			 
  		ProfileStructureIterator walker = new ProfileStructureIterator(profile, elementDefn);
  		while (walker.more()) {
  			// collect all the slices for the path
  			List<ElementDefinition> childset = walker.current();
  			// collect all the elements that match it by name
  			List<WrapperElement> children = new ArrayList<WrapperElement>(); 
  			focus.getNamedChildrenWithWildcard(walker.name(), children);

  			if (children.size() == 0) {
  				// well, there's no children - should there be? 
  				for (ElementDefinition defn : childset) {
  					if (!rule(errors,"required", path, defn.getMin() == 0, "Required Element '"+walker.name()+"' missing"))
  						break; // no point complaining about missing ones after the first one
  				} 
  			} else if (childset.size() == 1) {
  				// simple case: one possible definition, and one or more children. 
  				rule(errors, "cardinality", path, childset.get(0).getMax().equals("*") || Integer.parseInt(childset.get(0).getMax()) >= children.size(),
  						"Too many elements for '"+walker.name()+"'"); // todo: sort out structure
  				for (WrapperElement child : children) {
  					checkByProfile(errors, childset.get(0).getPath(), child, profile, childset.get(0));
  				}
  			} else { 
  				// ok, this is the full case - we have a list of definitions, and a list of candidates for meeting those definitions. 
  				// we need to decide *if* that match a given definition
  			}
  		}
  	}
  }

	private void checkBinding(List<ValidationMessage> errors, String path, WrapperElement focus, Profile profile, ElementDefinition elementDefn, String type) {
	  ElementDefinitionBindingComponent bc = elementDefn.getBinding();

	  if (bc != null && bc.hasReference() && bc.getReference() instanceof Reference) {
      String url = ((Reference) bc.getReference()).getReference();
	  	ValueSet vs = resolveValueSetReference(profile, (Reference) bc.getReference());
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

	private ValueSet resolveValueSetReference(Profile profile, Reference reference) {
	  if (reference.getReference().startsWith("#")) {
	  	for (Resource r : profile.getContained()) {
	  		if (r instanceof ValueSet && r.getId().equals(reference.getReference().substring(1)))
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

	private String typeSummary(ElementDefinition elementDefn) {
	  StringBuilder b = new StringBuilder();
	  for (TypeRefComponent t : elementDefn.getType()) {
	  	b.append("|"+t.getCode());
	  }
	  return b.toString().substring(1);
  }

	private boolean typeAllowed(String t, List<TypeRefComponent> types) {
	  for (TypeRefComponent type : types) {
	  	if (t.equals(Utilities.capitalize(type.getCode())))
	  		return true;
	  	if (t.equals("Resource") && Utilities.capitalize(type.getCode()).equals("Reference"))
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
//   		Boolean ok = (Boolean) xpath.evaluate(c.getXpath(), focus, XPathConstants.BOOLEAN);
//   		if (ok == null || !ok) {
//   			if (c.getSeverity() == ConstraintSeverity.warning)
//   				warning(errors, "invariant", path, false, c.getHuman());
//   			else
//   				rule(errors, "invariant", path, false, c.getHuman());
//   		}
//		}
//		catch (XPathExpressionException e) {
//		  rule(errors, "invariant", path, false, "error executing invariant: "+e.getMessage());
//		}
  }

	private void checkPrimitiveByProfile(List<ValidationMessage> errors, String path, WrapperElement focus, ElementDefinition elementDefn) {
		// two things to check - length, and fixed value
		String value = focus.getAttribute("value");
		if (elementDefn.hasMaxLengthElement()) {
			rule(errors, "too long", path, value.length() <= elementDefn.getMaxLength(), "The value '"+value+"' exceeds the allow length limit of "+Integer.toString(elementDefn.getMaxLength()));
		}
		if (elementDefn.hasFixed()) {
			checkFixedValue(errors, path, focus, elementDefn.getFixed(), "");
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
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.BooleanType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.IntegerType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.IntegerType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.DecimalType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.DecimalType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.Base64BinaryType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.Base64BinaryType) fixed).asStringValue()+"'");
			else if (fixed instanceof org.hl7.fhir.instance.model.InstantType)
				rule(errors, "value", path, check(((org.hl7.fhir.instance.model.InstantType) fixed).getValue().toString(), value), "Value is '"+value+"' but must be '"+((org.hl7.fhir.instance.model.InstantType) fixed).asStringValue()+"'");
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
			else if (fixed instanceof ContactPoint)
				checkContactPoint(errors, path, focus, (ContactPoint) fixed);
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
			else if (fixed instanceof Timing)
				checkTiming(errors, path, focus, (Timing) fixed);
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
			if (fixed.getExtension().size() == 0) {
				rule(errors, "value", path, extensions.size() == 0, "No extensions allowed");
			} else if (rule(errors, "value", path, extensions.size() == fixed.getExtension().size(), "Extensions count mismatch: expected "+Integer.toString(fixed.getExtension().size())+" but found "+Integer.toString(extensions.size()))) {
				for (Extension e : fixed.getExtension()) {
				  WrapperElement ex = getExtensionByUrl(extensions, e.getUrl());
					if (rule(errors, "value", path, ex != null, "Extension count mismatch: unable to find extension: "+e.getUrl())) {
						checkFixedValue(errors, path, ex.getFirstChild().getNextSibling(), e.getValue(), "extension.value");
					}
				}
			}
		}
  }

	private void checkAddress(List<ValidationMessage> errors, String path, WrapperElement focus, Address fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getTextElement(), "text");
	  checkFixedValue(errors, path+".city", focus.getNamedChild("city"), fixed.getCityElement(), "city");
	  checkFixedValue(errors, path+".state", focus.getNamedChild("state"), fixed.getStateElement(), "state");
	  checkFixedValue(errors, path+".country", focus.getNamedChild("country"), fixed.getCountryElement(), "country");
	  checkFixedValue(errors, path+".zip", focus.getNamedChild("zip"), fixed.getPostalCodeElement(), "postalCode");
	  
		List<WrapperElement> lines = new ArrayList<WrapperElement>();
		focus.getNamedChildren( "line", lines);
		if (rule(errors, "value", path, lines.size() == fixed.getLine().size(), "Expected "+Integer.toString(fixed.getLine().size())+" but found "+Integer.toString(lines.size())+" line elements")) {
			for (int i = 0; i < lines.size(); i++) 
				checkFixedValue(errors, path+".coding", lines.get(i), fixed.getLine().get(i), "coding");			
		}	  
  }

	private void checkContactPoint(List<ValidationMessage> errors, String path, WrapperElement focus, ContactPoint fixed) {
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValueElement(), "value");
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");
	  
  }

	private void checkAttachment(List<ValidationMessage> errors, String path, WrapperElement focus, Attachment fixed) {
	  checkFixedValue(errors, path+".contentType", focus.getNamedChild("contentType"), fixed.getContentTypeElement(), "contentType");
	  checkFixedValue(errors, path+".language", focus.getNamedChild("language"), fixed.getLanguageElement(), "language");
	  checkFixedValue(errors, path+".data", focus.getNamedChild("data"), fixed.getDataElement(), "data");
	  checkFixedValue(errors, path+".url", focus.getNamedChild("url"), fixed.getUrlElement(), "url");
	  checkFixedValue(errors, path+".size", focus.getNamedChild("size"), fixed.getSizeElement(), "size");
	  checkFixedValue(errors, path+".hash", focus.getNamedChild("hash"), fixed.getHashElement(), "hash");
	  checkFixedValue(errors, path+".title", focus.getNamedChild("title"), fixed.getTitleElement(), "title");	  
  }

	private void checkIdentifier(List<ValidationMessage> errors, String path, WrapperElement focus, Identifier fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".label", focus.getNamedChild("label"), fixed.getLabelElement(), "label");
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValueElement(), "value");
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriod(), "period");
	  checkFixedValue(errors, path+".assigner", focus.getNamedChild("assigner"), fixed.getAssigner(), "assigner");
  }

	private void checkCoding(List<ValidationMessage> errors, String path, WrapperElement focus, Coding fixed) {
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".code", focus.getNamedChild("code"), fixed.getCodeElement(), "code");
	  checkFixedValue(errors, path+".display", focus.getNamedChild("display"), fixed.getDisplayElement(), "display");	  
	  checkFixedValue(errors, path+".primary", focus.getNamedChild("primary"), fixed.getPrimaryElement(), "primary");	  
  }

	private void checkHumanName(List<ValidationMessage> errors, String path, WrapperElement focus, HumanName fixed) {
	  checkFixedValue(errors, path+".use", focus.getNamedChild("use"), fixed.getUseElement(), "use");
	  checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getTextElement(), "text");
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
		checkFixedValue(errors, path+".text", focus.getNamedChild("text"), fixed.getTextElement(), "text");
		List<WrapperElement> codings = new ArrayList<WrapperElement>();
		focus.getNamedChildren( "coding", codings);
		if (rule(errors, "value", path, codings.size() == fixed.getCoding().size(), "Expected "+Integer.toString(fixed.getCoding().size())+" but found "+Integer.toString(codings.size())+" coding elements")) {
			for (int i = 0; i < codings.size(); i++) 
				checkFixedValue(errors, path+".coding", codings.get(i), fixed.getCoding().get(i), "coding");			
		}	  
  }

	private void checkTiming(List<ValidationMessage> errors, String path, WrapperElement focus, Timing fixed) {
	  checkFixedValue(errors, path+".repeat", focus.getNamedChild("repeat"), fixed.getRepeat(), "value");
	  
		List<WrapperElement> events = new ArrayList<WrapperElement>();
		focus.getNamedChildren( "event", events);
		if (rule(errors, "value", path, events.size() == fixed.getEvent().size(), "Expected "+Integer.toString(fixed.getEvent().size())+" but found "+Integer.toString(events.size())+" event elements")) {
			for (int i = 0; i < events.size(); i++) 
				checkFixedValue(errors, path+".event", events.get(i), fixed.getEvent().get(i), "event");			
		}	  
  }

	private void checkPeriod(List<ValidationMessage> errors, String path, WrapperElement focus, Period fixed) {
	  checkFixedValue(errors, path+".start", focus.getNamedChild("start"), fixed.getStartElement(), "start");
	  checkFixedValue(errors, path+".end", focus.getNamedChild("end"), fixed.getEndElement(), "end");	  
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
	  checkFixedValue(errors, path+".period", focus.getNamedChild("period"), fixed.getPeriodElement(), "period");
	  checkFixedValue(errors, path+".factor", focus.getNamedChild("factor"), fixed.getFactorElement(), "factor");
	  checkFixedValue(errors, path+".lowerLimit", focus.getNamedChild("lowerLimit"), fixed.getLowerLimitElement(), "lowerLimit");
	  checkFixedValue(errors, path+".upperLimit", focus.getNamedChild("upperLimit"), fixed.getUpperLimitElement(), "upperLimit");
	  checkFixedValue(errors, path+".dimensions", focus.getNamedChild("dimensions"), fixed.getDimensionsElement(), "dimensions");
	  checkFixedValue(errors, path+".data", focus.getNamedChild("data"), fixed.getDataElement(), "data");
  }

	private void checkQuantity(List<ValidationMessage> errors, String path, WrapperElement focus, Quantity fixed) {
	  checkFixedValue(errors, path+".value", focus.getNamedChild("value"), fixed.getValueElement(), "value");
	  checkFixedValue(errors, path+".comparator", focus.getNamedChild("comparator"), fixed.getComparatorElement(), "comparator");
	  checkFixedValue(errors, path+".units", focus.getNamedChild("units"), fixed.getUnitsElement(), "units");
	  checkFixedValue(errors, path+".system", focus.getNamedChild("system"), fixed.getSystemElement(), "system");
	  checkFixedValue(errors, path+".code", focus.getNamedChild("code"), fixed.getCodeElement(), "code");
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



	
}

