package org.hl7.fhir.definitions.validation;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingExtensibility;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength;
import org.hl7.fhir.definitions.model.BindingSpecification.ElementType;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.validation.BaseValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Element;


/** todo
 * check code lists used in Codings have displays
 * 
 * @author Grahame
 *
 */
public class ResourceValidator extends BaseValidator {

  public class Usage {
    public Set<SearchParameter.SearchType> usage= new HashSet<SearchParameter.SearchType>();
  }
  
 

  private Definitions definitions;
	private Map<String, Usage> usages = new HashMap<String, Usage>();
  private Element translations;
  private Map<String, AtomEntry<? extends Resource>> codeSystems = new HashMap<String, AtomEntry<? extends Resource>>();
  
  

	public ResourceValidator(Definitions definitions, Element translations, Map<String, AtomEntry<? extends Resource>> map) {
		super();
    source = Source.ResourceValidator;
		this.definitions = definitions;
		this.translations = translations;
		this.codeSystems = map;
	}

	// public void setConceptDomains(List<ConceptDomain> conceptDomains) {
	// this.conceptDomains = conceptDomains;
	// }
	//
	// public void defineResource(String name) {
	// this.resources.add(name);
	// }
	//
	// public void setDataTypes(String[] names) throws Exception {
	// TypeParser tp = new TypeParser();
	// for (String tn : names) {
	// datatypes.addAll(tp.parse(tn));
	// }
	// }

  public void checkStucture(List<ValidationMessage> errors, String name, ElementDefn structure) {
    rule(errors, "structure", structure.getName(), name.toLowerCase().substring(0, 1) != name.substring(0, 1), "Resource Name must start with an uppercase alpha character");
    checkElement(errors, structure.getName(), structure, null, null);
    
  }
  public List<ValidationMessage> checkStucture(String name, ElementDefn structure) {
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    checkStucture(errors, name, structure);
    return errors;
  
  }
  
  public void check(List<ValidationMessage> errors, String name, ResourceDefn parent) {
    rule(errors, "structure", parent.getName(), !name.equals("Metadata"), "The name 'Metadata' is not a legal name for a resource");
    rule(errors, "structure", parent.getName(), !name.equals("History"), "The name 'History' is not a legal name for a resource");
    rule(errors, "structure", parent.getName(), !name.equals("Tag"), "The name 'Tag  ' is not a legal name for a resource");
    rule(errors, "structure", parent.getName(), !name.equals("Tags"), "The name 'Tags' is not a legal name for a resource");
    rule(errors, "structure", parent.getName(), !name.equals("MailBox"), "The name 'MailBox' is not a legal name for a resource");
    rule(errors, "structure", parent.getName(), !name.equals("Validation"), "The name 'Validation' is not a legal name for a resource");
    rule(errors, "required",  parent.getName(), hasTranslationsEntry(name), "The name '"+name+"' is not found in the file translations.xml");
    rule(errors, "structure", parent.getName(), name.toLowerCase().substring(0, 1) != name.substring(0, 1), "Resource Name must start with an uppercase alpha character");

    rule(errors, "required",  parent.getName(), parent.getRoot().getElements().size() > 0, "A resource must have at least one element in it before the build can proceed"); // too many downstream issues in the parsers, and it would only happen as a transient thing when designing the resources
    
    checkElement(errors, parent.getName(), parent.getRoot(), parent, null);
    rule(errors, "structure", parent.getName(), parent.getRoot().getElementByName("text") == null, "Element named \"text\" not allowed");
    rule(errors, "structure", parent.getName(), parent.getRoot().getElementByName("contained") == null, "Element named \"contaned\" not allowed");
    if (parent.getRoot().getElementByName("subject") != null && parent.getRoot().getElementByName("subject").typeCode().startsWith("Resource"))
      rule(errors, "structure", parent.getName(), parent.getSearchParams().containsKey("subject"), "A resource that contains a subject reference must have a search parameter 'subject'");
    if (parent.getRoot().getElementByName("patient") != null && parent.getRoot().getElementByName("patient").typeCode().startsWith("Resource"))
      rule(errors, "structure", parent.getName(), parent.getSearchParams().containsKey("patient"), "A resource that contains a patient reference must have a search parameter 'patient'");
    for (org.hl7.fhir.definitions.model.SearchParameter p : parent.getSearchParams().values()) {
      if (!usages.containsKey(p.getCode()))
        usages.put(p.getCode(), new Usage());
      usages.get(p.getCode()).usage.add(p.getType());
      rule(errors, "structure", parent.getName(), !p.getCode().contains("."), "Search Parameter Names cannot contain a '.' (\""+p.getCode()+"\")");
      rule(errors, "structure", parent.getName(), !p.getCode().equalsIgnoreCase("id"), "Search Parameter Names cannot be named 'id' (\""+p.getCode()+"\")");
      rule(errors, "structure", parent.getName(), p.getCode().equals(p.getCode().toLowerCase()), "Search Parameter Names should be all lowercase (\""+p.getCode()+"\")");
    }
//    rule(errors, parent.getName(), !parent.getSearchParams().containsKey("id"), "A resource cannot have a search parameter 'id'");
    for (Compartment c : definitions.getCompartments()) 
      rule(errors, "structure", parent.getName(), c.getResources().containsKey(parent), "Resource not entered in resource map for compartment '"+c.getTitle()+"' (compartments.xml)");
	}

  private boolean hasTranslationsEntry(String name) {
    Element child = XMLUtil.getFirstChild(translations);
    while (child != null) {
      if (child.getNodeName().equals("item") && child.getAttribute("id").equals(name)) {
        return true;
      }
      child = XMLUtil.getNextSibling(child);
    }
    return false;
  }

  public List<ValidationMessage> check(String name, ResourceDefn parent) {
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    check(errors, name, parent);    
    return errors;
  }
  
	//todo: check that primitives *in datatypes* don't repeat
	
	private void checkElement(List<ValidationMessage> errors, String path, ElementDefn e, ResourceDefn parent, String parentName) {
		rule(errors, "structure", path, e.unbounded() || e.getMaxCardinality() == 1,	"Max Cardinality must be 1 or unbounded");
		rule(errors, "structure", path, e.getMinCardinality() == 0 || e.getMinCardinality() == 1, "Min Cardinality must be 0 or 1");
		hint(errors, "structure", path, !nameOverlaps(e.getName(), parentName), "Name of child ("+e.getName()+") overlaps with name of parent ("+parentName+")");
		rule(errors, "structure", path, e.hasShortDefn(), "Must have a short defn");
    warning(errors, "structure", path, !Utilities.isPlural(e.getName()) || !e.unbounded(), "Element names should be singular");
    rule(errors, "structure", path, !e.getName().equals("id"), "Element named \"id\" not allowed");
    rule(errors, "structure", path, !e.getName().equals("extension"), "Element named \"extension\" not allowed");
    rule(errors, "structure", path, !e.getName().equals("entries"), "Element named \"entries\" not allowed");
    rule(errors, "structure", path, (parentName == null) || e.getName().charAt(0) == e.getName().toLowerCase().charAt(0), "Element Names must not start with an uppercase character");
    rule(errors, "structure", path, e.getName().equals(path) || e.getElements().size() == 0 || (e.hasSvg() || e.isUmlBreak() || !Utilities.noString(e.getUmlDir())), "Element is missing a UML layout direction");
// this isn't a real hint, just a way to gather information   hint(errors, path, !e.isModifier(), "isModifier, minimum cardinality = "+e.getMinCardinality().toString());
    rule(errors, "structure", path, !e.getDefinition().toLowerCase().startsWith("this is"), "Definition should not start with 'this is'");
    rule(errors, "structure", path, e.getDefinition().endsWith("."), "Definition should end with '.', but is '"+e.getDefinition()+"'");
    
    if( e.getShortDefn().length() > 0)
		{
			rule(errors, "structure", path, e.getShortDefn().contains("|") || Character.isUpperCase(e.getShortDefn().charAt(0)) || !Character.isLetter(e.getShortDefn().charAt(0)), "Short Description must start with an uppercase character ('"+e.getShortDefn()+"')");
		    rule(errors, "structure", path, !e.getShortDefn().endsWith(".") || e.getShortDefn().endsWith("etc."), "Short Description must not end with a period ('"+e.getShortDefn()+"')");
		    rule(errors, "structure", path, e.getDefinition().contains("|") || Character.isUpperCase(e.getDefinition().charAt(0)) || !Character.isLetter(e.getDefinition().charAt(0)), "Long Description must start with an uppercase character ('"+e.getDefinition()+"')");
		}
		
    for (String inv : e.getInvariants().keySet()) {
      String xpath = e.getInvariants().get(inv).getXpath();
      rule(errors, "value", path,  !(xpath.contains("&lt;") || xpath.contains("&gt;")), "error in xpath - do not escape xml characters in the xpath in the excel spreadsheet");
    }
    rule(errors, "structure", path, !e.getName().startsWith("_"), "Element names cannot start with '_'");
		// if (e.getConformance() == ElementDefn.Conformance.Mandatory &&
		// !e.unbounded())
		// rule(errors, path, e.getMinCardinality() > 0,
		// "Min Cardinality cannot be 0 when element is mandatory");
		//TODO: Really? A composite element need not have a definition?
		checkType(errors, path, e, parent);
//		rule(errors, path, !"code".equals(e.typeCode()) || e.hasBinding(),
//				"Must have a binding if type is 'code'");

		if (e.typeCode().equals("code") && parent != null) {
		  rule(errors, "structure", path, e.hasBindingOrOk(), "An element of type code must have a binding");
		}
		
		if (e.hasBinding()) {
		  rule(errors, "structure", path, e.typeCode().equals("code") || e.typeCode().contains("Coding") 
				  || e.typeCode().contains("CodeableConcept"), "Can only specify bindings for coded data types");
			BindingSpecification cd = definitions.getBindingByName(e.getBindingName());
			rule(errors, "structure", path, cd != null, "Unable to resolve binding name " + e.getBindingName());
			
			if (cd != null) {
			  boolean isComplex = !e.typeCode().equals("code");
			  if (cd.getElementType() == ElementType.Unknown) {
			    if (isComplex)
			      cd.setElementType(ElementType.Complex);
			    else
            cd.setElementType(ElementType.Simple);
			  } else if (cd.getBinding() != Binding.Reference)
          if (isComplex)
            rule(errors, "structure", path, cd.getElementType() == ElementType.Complex, "Cannot use a binding from both code and Coding/CodeableConcept elements");
          else
            rule(errors, "structure", path, cd.getElementType() == ElementType.Simple, "Cannot use a binding from both code and Coding/CodeableConcept elements");
			}
		}
		for (ElementDefn c : e.getElements()) {
			checkElement(errors, path + "." + c.getName(), c, parent, e.getName());
		}

	}

  private boolean nameOverlaps(String name, String parentName) {
	  if (Utilities.noString(parentName))
	    return false;
	  if (name.equals(parentName))
      return false;
	  name = name.toLowerCase();
	  parentName = parentName.toLowerCase();
	  if (parentName.startsWith(name))
	    return true;
	  for (int i = 3; i < name.length(); i++)
	    if (parentName.endsWith(name.substring(0, i)))
	      return true;
	  return false;
  }

  private void checkType(List<ValidationMessage> errors, String path, ElementDefn e, ResourceDefn parent) {
		if (e.getTypes().size() == 0) {
			rule(errors, "structure", path, path.contains("."), "Must have a type on a base element");
			rule(errors, "structure", path, e.getName().equals("extension") || e.getElements().size() > 0, "Must have a type unless sub-elements exist");
		} else if (definitions.dataTypeIsSharedInfo(e.typeCode())) {
		  try {
        e.getElements().addAll(definitions.getElementDefn(e.typeCode()).getElements());
      } catch (Exception e1) {
        rule(errors, "structure", path, false, e1.getMessage());
      }
		} else {
			for (TypeRef t : e.getTypes()) 
			{
				String s = t.getName();
				if (s.charAt(0) == '@') {
					//TODO: validate path
				} 
				else 
				{
					if (s.charAt(0) == '#')
						s = s.substring(1);
					if (!t.isSpecialType()) {
						rule(errors, "structure", path, typeExists(s, parent), "Illegal Type '" + s + "'");
						if (t.isResourceReference()) {
							for (String p : t.getParams()) {
								rule(errors, "structure", path,
										p.equals("Any")
												|| definitions.hasResource(p),
										"Unknown resource type " + p);
							}
						}
					}
				}
			}
		}

	}

	private boolean typeExists(String name, ResourceDefn parent) {
		return definitions.hasType(name) ||
				(parent != null && parent.getRoot().hasNestedType(name));
	}

	
  public List<ValidationMessage> check(String n, BindingSpecification cd) throws Exception {
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    check(errors, n, cd);
    return errors;
  }
  
	public void check(List<ValidationMessage> errors, String n, BindingSpecification cd) throws Exception {
    // basic integrity checks
    for (DefinedCode c : cd.getCodes()) {
      String d = c.getCode();
      if (Utilities.noString(d))
        d = c.getId();
      if (Utilities.noString(d))
        d = c.getDisplay();
      if (Utilities.noString(d))
        d = c.getDisplay();
      
      if (Utilities.noString(c.getSystem()))
        warning(errors, "structure", "Binding "+n, !Utilities.noString(c.getDefinition()), "Code "+d+" must have a definition");
      warning(errors, "structure", "Binding "+n, !(Utilities.noString(c.getId()) && Utilities.noString(c.getSystem())) , "Code "+d+" must have a id or a system");
    }
    
    // trigger processing into a Heirachical set if necessary
    rule(errors, "structure", "Binding "+n, !cd.isHeirachical() || (cd.getChildCodes().size() < cd.getCodes().size()), "Logic error processing Hirachical code set");

    // now, rules for the source
    hint(errors, "structure", "Binding "+n, cd.getElementType() != ElementType.Unknown, "Binding is not used");
    warning(errors, "structure", "Binding "+n, cd.getBinding() != Binding.Unbound, "Need to provide a binding");
    rule(errors, "structure", "Binding "+n, cd.getElementType() != ElementType.Simple || cd.getBinding() != Binding.Unbound, "Need to provide a binding for code elements");
    rule(errors, "structure", "Binding "+n, (cd.getElementType() == ElementType.Complex || cd.getElementType() == ElementType.Unknown) || !cd.isExample(), "Can only be an example binding if bound to Coding/CodeableConcept");
    rule(errors, "structure", "Binding "+n, Utilities.noString(cd.getDefinition())  || (cd.getDefinition().charAt(0) == cd.getDefinition().toUpperCase().charAt(0)), "Definition cannot start with a lowercase letter");
    

    // set these for when the profiles are generated
    if (cd.getElementType() == ElementType.Simple) {
      cd.setBindingStrength(BindingStrength.Required);
      cd.setExtensibility(BindingExtensibility.Complete);
    }
    else if (cd.getElementType() == ElementType.Complex) {
      cd.setExtensibility(BindingExtensibility.Extensible);
      if (cd.isExample()) {
        cd.setBindingStrength(BindingStrength.Example);
      } else {
        cd.setBindingStrength(BindingStrength.Preferred);
      }
    }
    else {
      cd.setBindingStrength(BindingStrength.Unstated);
      cd.setExtensibility(BindingExtensibility.Extensible);      
    }
  }

  public void dumpParams() {
    for (String s : usages.keySet()) {
      System.out.println(s+": "+usages.get(s).usage.toString());
    }
  }
}
