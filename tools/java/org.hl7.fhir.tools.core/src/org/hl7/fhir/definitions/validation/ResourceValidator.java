package org.hl7.fhir.definitions.validation;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.BindingSpecification.ElementType;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn.SearchType;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.Enumerations.BindingStrength;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.valuesets.IssueType;
import org.hl7.fhir.instance.utils.Translations;
import org.hl7.fhir.instance.validation.BaseValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.Utilities;


/** todo
 * check code lists used in Codings have displays
 * 
 * @author Grahame
 *
 */
public class ResourceValidator extends BaseValidator {

  public static class Usage {
    public Set<SearchParameterDefn.SearchType> usage = new HashSet<SearchParameterDefn.SearchType>();
  }

  public static class UsageT {
    public Set<String> usage = new HashSet<String>();
  }

  private Definitions definitions;
  private final Map<String, Usage> usages = new HashMap<String, Usage>();
  private final Map<String, Integer> names = new HashMap<String, Integer>();
  private final Map<SearchType, UsageT> usagest = new HashMap<SearchType, UsageT>();
  private Translations translations;
  private final Map<String, ValueSet> codeSystems;
//  private Map<String, Integer> typeCounter = new HashMap<String, Integer>();

	public ResourceValidator(Definitions definitions, Translations translations, Map<String, ValueSet> map) {
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
	// public void defineReference(String name) {
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
    rule(errors, IssueType.STRUCTURE, structure.getName(), name.length() > 1 && Character.isUpperCase(name.charAt(0)), "Resource Name must start with an uppercase alpha character");
    checkElement(errors, structure.getName(), structure, null, null, true, false, hasSummary(structure), new ArrayList<String>());
  }
  
  private boolean hasSummary(ElementDefn structure) {
    if (structure.isSummary())
      return true;
    for (ElementDefn e : structure.getElements()) {
      if (hasSummary(e))
        return true;
    }
    return false;
  }

  public List<ValidationMessage> checkStucture(String name, ElementDefn structure) {
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    checkStucture(errors, name, structure);
    return errors;
  }
  
  protected boolean rule(List<ValidationMessage> errors, IssueType type, String path, boolean b, String msg) {
    String rn = path.contains(".") ? path.substring(0, path.indexOf(".")) : path;
    return super.rule(errors, type, path, b, msg, "<a href=\""+(rn.toLowerCase())+".html\">"+rn+": "+Utilities.escapeXml(msg));
  }

  public void check(List<ValidationMessage> errors, String name, ResourceDefn parent) throws Exception {    
    rule(errors, IssueType.STRUCTURE, parent.getName(), !name.equals("Metadata"), "The name 'Metadata' is not a legal name for a resource");
    rule(errors, IssueType.STRUCTURE, parent.getName(), !name.equals("History"), "The name 'History' is not a legal name for a resource");
    rule(errors, IssueType.STRUCTURE, parent.getName(), !name.equals("Tag"), "The name 'Tag' is not a legal name for a resource");
    rule(errors, IssueType.STRUCTURE, parent.getName(), !name.equals("Tags"), "The name 'Tags' is not a legal name for a resource");
    rule(errors, IssueType.STRUCTURE, parent.getName(), !name.equals("MailBox"), "The name 'MailBox' is not a legal name for a resource");
    rule(errors, IssueType.STRUCTURE, parent.getName(), !name.equals("Validation"), "The name 'Validation' is not a legal name for a resource");
    rule(errors, IssueType.REQUIRED,  parent.getName(), translations.hasTranslation(name), "The name '"+name+"' is not found in the file translations.xml");
    rule(errors, IssueType.STRUCTURE, parent.getName(), name.length() > 1 && Character.isUpperCase(name.charAt(0)), "Resource Name must start with an uppercase alpha character");
    rule(errors, IssueType.STRUCTURE, parent.getName(), !Utilities.noString(parent.getFmmLevel()), "Resource must have a maturity level");

    rule(errors, IssueType.REQUIRED,  parent.getName(), parent.getRoot().getElements().size() > 0, "A resource must have at least one element in it before the build can proceed"); // too many downstream issues in the parsers, and it would only happen as a transient thing when designing the resources
    rule(errors, IssueType.REQUIRED,  parent.getName(), parent.getWg() != null, "A resource must have a designated owner"); // too many downstream issues in the parsers, and it would only happen as a transient thing when designing the resources
    
    if (Utilities.noString(parent.getEnteredInErrorStatus()))
      if (hasStatus(parent, "entered-in-error"))
        parent.setEnteredInErrorStatus(".status = entered-in-error");
      else if (hasStatus(parent, "retired"))
        parent.setEnteredInErrorStatus(".status = retired");
      else if (hasActivFalse(parent))
        parent.setEnteredInErrorStatus(".active = false");
      else 
        hint(errors, IssueType.REQUIRED,  parent.getName(), false, "A resource must have an 'entered in error' status"); // too many downstream issues in the parsers, and it would only happen as a transient thing when designing the resources
        
    String s = parent.getRoot().getMapping(Definitions.RIM_MAPPING);
    warning(errors, IssueType.REQUIRED, parent.getName(), !Utilities.noString(s), "RIM Mapping is required");

    List<String> vsWarns = new ArrayList<String>();
    int vsWarnings = checkElement(errors, parent.getName(), parent.getRoot(), parent, null, s == null || !s.equalsIgnoreCase("n/a"), false, hasSummary(parent.getRoot()), vsWarns);
    
    if (!resourceIsTechnical(name)) { // these are exempt because identification is tightly managed
      ElementDefn id = parent.getRoot().getElementByName("identifier");
      if (id == null)
        warning(errors, IssueType.STRUCTURE, parent.getName(), false, "All resources should have an identifier");
      else 
        rule(errors, IssueType.STRUCTURE, parent.getName(), id.typeCode().equals("Identifier"), "If a resource has an element named identifier, it must have a type 'Identifier'");
    }
    rule(errors, IssueType.STRUCTURE, parent.getName(), parent.getRoot().getElementByName("text") == null, "Element named \"text\" not allowed");
    rule(errors, IssueType.STRUCTURE, parent.getName(), parent.getRoot().getElementByName("contained") == null, "Element named \"contained\" not allowed");
    if (parent.getRoot().getElementByName("subject") != null && parent.getRoot().getElementByName("subject").typeCode().startsWith("Reference"))
      rule(errors, IssueType.STRUCTURE, parent.getName(), parent.getSearchParams().containsKey("subject"), "A resource that contains a subject reference must have a search parameter 'subject'");
    if (parent.getRoot().getElementByName("patient") != null && parent.getRoot().getElementByName("patient").typeCode().startsWith("Reference"))
      rule(errors, IssueType.STRUCTURE, parent.getName(), parent.getSearchParams().containsKey("patient"), "A resource that contains a patient reference must have a search parameter 'patient'");
    for (org.hl7.fhir.definitions.model.SearchParameterDefn p : parent.getSearchParams().values()) {
      if (!usages.containsKey(p.getCode()))
        usages.put(p.getCode(), new Usage());
      usages.get(p.getCode()).usage.add(p.getType());
      if (!usagest.containsKey(p.getType()))
        usagest.put(p.getType(), new UsageT());
      rule(errors, IssueType.STRUCTURE, parent.getName(), !p.getCode().equals("filter"), "Search Parameter Name cannot be 'filter')");
      rule(errors, IssueType.STRUCTURE, parent.getName(), !p.getCode().contains("."), "Search Parameter Names cannot contain a '.' (\""+p.getCode()+"\")");
      rule(errors, IssueType.STRUCTURE, parent.getName(), !p.getCode().equalsIgnoreCase("id"), "Search Parameter Names cannot be named 'id' (\""+p.getCode()+"\")");
      hint(errors, IssueType.STRUCTURE, parent.getName(), !stringMatches(p.getCode(), "id", "lastUpdated", "tag", "profile", "security", "text", "content", "list", "query"), "Search Parameter Names cannot be named one of the reserved names (\""+p.getCode()+"\")");
      rule(errors, IssueType.STRUCTURE, parent.getName(), p.getCode().equals(p.getCode().toLowerCase()), "Search Parameter Names should be all lowercase (\""+p.getCode()+"\")");
      if (rule(errors, IssueType.STRUCTURE, parent.getName(), !Utilities.noString(p.getDescription()), "Search Parameter description is empty (\""+p.getCode()+"\")"))
        rule(errors, IssueType.STRUCTURE, parent.getName(), Character.isUpperCase(p.getDescription().charAt(0)) || p.getDescription().startsWith("e.g. ") || p.getDescription().contains("|"), "Search Parameter descriptions should start with an uppercase character(\""+p.getDescription()+"\")");
      try {
        for (String path : p.getPaths()) {
          ElementDefn e;
          e = parent.getRoot().getElementForPath(path, definitions, "Resolving Search Parameter Path", true);
          List<TypeRef> tlist;
          if (path.endsWith("."+e.getName()))
            tlist = e.getTypes();
          else {
            tlist = new ArrayList<TypeRef>();
            for (TypeRef t : e.getTypes())
              if (path.endsWith(Utilities.capitalize(t.getName())))
                tlist.add(t);
          }
          for (TypeRef t : tlist) {
            if (definitions.getSearchRules().containsKey(t.getName()) && definitions.getSearchRules().get(t.getName()).contains(p.getType().name())) 
              usagest.get(p.getType()).usage.add((e.getTypes().size() > 1 ? path+":" : "") +t.getName());
            else 
              rule(errors, IssueType.STRUCTURE, parent.getName(), tlist.size() > 0, "Search Parameter "+p.getCode()+" : "+p.getType().name()+" type illegal for "+path+" : "+t.getName()+"");      
          }
        }
      } catch (Exception e1) {
      }
      try {
        if (p.getType() == SearchType.reference) {
          for (String path : p.getPaths()) {
            ElementDefn e;
            e = parent.getRoot().getElementForPath(path, definitions, "Resolving Search Parameter Path", true);
            for (TypeRef t : e.getTypes()) {
              if (t.getName().equals("Reference")) {
                for (String pn : t.getParams()) {
                  p.getTargets().add(pn);
                }
              }
            }
          }
        }
      } catch (Exception e1) {
        rule(errors, IssueType.STRUCTURE, parent.getName(), false, e1.getMessage());
      }
    }
    for (Operation op : parent.getOperations()) {
      rule(errors, IssueType.STRUCTURE, parent.getName()+"/$"+op.getName(), !parentHasOp(parent.getRoot().typeCode(), op.getName()), "Duplicate Operation Name $"+op.getName()+" on "+parent.getName()); 
    }
    
    for (Compartment c : definitions.getCompartments()) {
      if (rule(errors, IssueType.STRUCTURE, parent.getName(), c.getResources().containsKey(parent), "Resource not entered in resource map for compartment '"+c.getTitle()+"' (compartments.xml)")) {
        String param = c.getResources().get(parent);
        if (!Utilities.noString(param)) {
//          rule(errors, IssueType.STRUCTURE, parent.getName(), param.equals("{def}") || parent.getSearchParams().containsKey(c.getName()), "Resource "+parent.getName()+" in compartment " +c.getName()+" must have a search parameter named "+c.getName().toLowerCase()+")");
          for (String p : param.split("\\|")) {
            String pn = p.trim();
            if (pn.contains("."))
              pn = pn.substring(0, pn.indexOf("."));
            rule(errors, IssueType.STRUCTURE, parent.getName(), Utilities.noString(pn) || pn.equals("{def}") || parent.getSearchParams().containsKey(pn), "Resource "+parent.getName()+" in compartment " +c.getName()+": parameter "+param+" was not found ("+pn+")");
          }
        }
      }
    }
    // last check: if maturity level is 
    int warnings = 0;
    for (ValidationMessage em : errors) {
      if (em.getLevel() == IssueSeverity.WARNING)
        warnings++;
    }
    if (rule(errors, IssueType.STRUCTURE, parent.getName(), warnings == 0 || parent.getFmmLevel().equals("0"), "Resource "+parent.getName()+" (FMM="+parent.getFmmLevel()+") cannot have a FMM level >1 if it has warnings"))
      rule(errors, IssueType.STRUCTURE, parent.getName(), vsWarnings == 0 || parent.getFmmLevel().equals("0"), "Resource "+parent.getName()+" (FMM="+parent.getFmmLevel()+") cannot have a FMM level >1 if it has linked value set warnings ("+vsWarns.toString()+")");
	}

  private boolean stringMatches(String value, String... args) {
    for(String arg: args) {
      if (value.equalsIgnoreCase(arg))
        return true;      
    }
    return false;
  }

  private boolean hasActivFalse(ResourceDefn parent) {
    ElementDefn e = parent.getRoot().getElementByName("active");
    if (e != null) {
      if (e.typeCode().equals("boolean"))
        return true;
    }
    return false;
  }

  private boolean hasStatus(ResourceDefn parent, String code) {
    ElementDefn e = parent.getRoot().getElementByName("status");
    if (e != null) {
      if (e.hasBinding() && e.getBinding().getValueSet() != null && e.getBinding().getValueSet().hasDefine()) {
        for (ConceptDefinitionComponent cc : e.getBinding().getValueSet().getDefine().getConcept()) {
          if (cc.getCode().equals(code))
            return true;
        }
      } 
    }
    return false;
  }

  private boolean parentHasOp(String rname, String opname) throws Exception {
    if (Utilities.noString(rname))
        return false;
    ResourceDefn r = definitions.getResourceByName(rname);
    for (Operation op : r.getOperations()) {
      if (op.getName().equals(opname))
        return true;
    }    
    return parentHasOp(r.getRoot().typeCode(), opname);
  }

  private boolean resourceIsTechnical(String name) {
    return 
        name.equals("Binary") || 
        name.equals("Bundle") || 
        name.equals("ConceptMap") || 
        name.equals("Conformance") || 
        name.equals("MessageHeader") || 
        name.equals("Subscription") || 
        name.equals("DataElement") || 
        name.equals("Profile") || 
        name.equals("Query") || 
        name.equals("ValueSet") ||         
        name.equals("OperationDefinition") ||         
        name.equals("OperationOutcome");         
  }


  public List<ValidationMessage> check(String name, ResourceDefn parent) throws Exception {
    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    check(errors, name, parent);    
    return errors;
  }
  
	//todo: check that primitives *in datatypes* don't repeat
	
	private int checkElement(List<ValidationMessage> errors, String path, ElementDefn e, ResourceDefn parent, String parentName, boolean needsRimMapping, boolean optionalParent, boolean hasSummary, List<String> vsWarns) {
//	  for (TypeRef t : e.getTypes()) {
//  	  if (!typeCounter.containsKey(t.getName()))
//	      typeCounter.put(t.getName(), 1);
//  	  else
//  	    typeCounter.put(t.getName(), typeCounter.get(t.getName())+1);
//	  }
	  int vsWarnings = 0;
	  if (!names.containsKey(e.getName()))
	    names.put(e.getName(), 0);
    names.put(e.getName(), names.get(e.getName())+1);
	  
    rule(errors, IssueType.STRUCTURE, path, isValidToken(e.getName(), !path.contains(".")), "Name "+e.getName()+" is not a valid element name");
	  rule(errors, IssueType.STRUCTURE, path, e.unbounded() || e.getMaxCardinality() == 1,	"Max Cardinality must be 1 or unbounded");
		rule(errors, IssueType.STRUCTURE, path, e.getMinCardinality() == 0 || e.getMinCardinality() == 1, "Min Cardinality must be 0 or 1");
		if (hasSummary && e.getMinCardinality() == 0) {
      rule(errors, IssueType.STRUCTURE, path, optionalParent || e.isSummary(),  "An element with a minimum cardinality = 1 must be in the summary");
      optionalParent = false;
		}
		hasSummary = hasSummary && e.isSummary();
		hint(errors, IssueType.STRUCTURE, path, !nameOverlaps(e.getName(), parentName), "Name of child ("+e.getName()+") overlaps with name of parent ("+parentName+")");
    checkDefinitions(errors, path, e);
    warning(errors, IssueType.STRUCTURE, path, !Utilities.isPlural(e.getName()) || !e.unbounded(), "Element names should be singular");
    rule(errors, IssueType.STRUCTURE, path, !e.getName().equals("id"), "Element named \"id\" not allowed");
    hint(errors, IssueType.STRUCTURE, path, !e.getName().equals("comments"), "Element named \"comments\" not allowed - use 'comment'");
    hint(errors, IssueType.STRUCTURE, path, !e.getName().equals("notes"), "Element named \"notes\" not allowed - use 'note'");
    rule(errors, IssueType.STRUCTURE, path, !e.getName().endsWith("[x]") || !e.unbounded(), "Elements with a choice of types cannot have a cardinality > 1");
    rule(errors, IssueType.STRUCTURE, path, !e.getName().equals("extension"), "Element named \"extension\" not allowed");
    rule(errors, IssueType.STRUCTURE, path, !e.getName().equals("entries"), "Element named \"entries\" not allowed");
    rule(errors, IssueType.STRUCTURE, path, (parentName == null) || e.getName().charAt(0) == e.getName().toLowerCase().charAt(0), "Element Names must not start with an uppercase character");
    rule(errors, IssueType.STRUCTURE, path, e.getName().equals(path) || e.getElements().size() == 0 || (e.hasSvg() || e.isUmlBreak() || !Utilities.noString(e.getUmlDir())), "Element is missing a UML layout direction");
// this isn't a real hint, just a way to gather information   hint(errors, path, !e.isModifier(), "isModifier, minimum cardinality = "+e.getMinCardinality().toString());
    rule(errors, IssueType.STRUCTURE, path, !e.getDefinition().toLowerCase().startsWith("this is"), "Definition should not start with 'this is'");
    rule(errors, IssueType.STRUCTURE, path, e.getDefinition().endsWith(".") || e.getDefinition().endsWith("?") , "Definition should end with '.' or '?', but is '"+e.getDefinition()+"'");
    if (e.usesType("string") && e.usesType("CodeableConcept"))
      rule(errors, IssueType.STRUCTURE, path, e.getComments().contains("string") && e.getComments().contains("CodeableConcept"), "Element type cannot have both string and CodeableConcept unless the difference between their usage is explained in the comments");
    hint(errors, IssueType.BUSINESSRULE, path, Utilities.noString(e.getTodo()), "Element has a todo associated with it ("+e.getTodo()+")");
//    if (needsRimMapping)
//      warning(errors, IssueType.REQUIRED, path, !Utilities.noString(e.getMapping(ElementDefn.RIM_MAPPING)), "RIM Mapping is required");

    if (e.getName().equals("comment")) {
      hint(errors, IssueType.STRUCTURE, path, false, "MnM must have confirmed this should not be an Annotation");
      hint(errors, IssueType.STRUCTURE, path, e.typeCode().equals("string"), "The type of 'comment' must be 'string'");
      hint(errors, IssueType.STRUCTURE, path, e.getMinCardinality() == 0, "The min cardinality of 'comment' must be 0");
      hint(errors, IssueType.STRUCTURE, path, e.getMaxCardinality() == 1, "The max cardinality of 'comment' must be 1");
    }
    if (e.getName().equals("note")) {
      hint(errors, IssueType.STRUCTURE, path, e.typeCode().equals("Annotation"), "The type of 'note' must be 'Annotation'");
      hint(errors, IssueType.STRUCTURE, path, e.getMinCardinality() == 0, "The min cardinality of 'note' must be 0");
      hint(errors, IssueType.STRUCTURE, path, e.unbounded(), "The max cardinality of 'note' must be *");
    }
    String sd = e.getShortDefn();
    if( sd.length() > 0)
		{
			rule(errors, IssueType.STRUCTURE, path, sd.contains("|") || Character.isUpperCase(sd.charAt(0)) || sd.startsWith("e.g. ") || !Character.isLetter(sd.charAt(0)) || Utilities.isURL(sd), "Short Description must start with an uppercase character ('"+sd+"')");
		    rule(errors, IssueType.STRUCTURE, path, !sd.endsWith(".") || sd.endsWith("etc."), "Short Description must not end with a period ('"+sd+"')");
		    rule(errors, IssueType.STRUCTURE, path, e.getDefinition().contains("|") || Character.isUpperCase(e.getDefinition().charAt(0)) || !Character.isLetter(e.getDefinition().charAt(0)), "Long Description must start with an uppercase character ('"+e.getDefinition()+"')");
		}
		
    for (String inv : e.getInvariants().keySet()) {
      String xpath = e.getInvariants().get(inv).getXpath();
      rule(errors, IssueType.VALUE, path,  !(xpath.contains("&lt;") || xpath.contains("&gt;")), "error in xpath - do not escape xml characters in the xpath in the excel spreadsheet");
    }
    rule(errors, IssueType.STRUCTURE, path, !e.getName().startsWith("_"), "Element names cannot start with '_'");
		// if (e.getConformance() == ElementDefn.Conformance.Mandatory &&
		// !e.unbounded())
		// rule(errors, path, e.getMinCardinality() > 0,
		// "Min Cardinality cannot be 0 when element is mandatory");
		//TODO: Really? A composite element need not have a definition?
		checkType(errors, path, e, parent);
//		rule(errors, path, !"code".equals(e.typeCode()) || e.hasBinding(),
//				"Must have a binding if type is 'code'");

    rule(errors, IssueType.STRUCTURE, path, !"uuid".equals(e.typeCode()), "The type uuid is illegal");
		if (e.typeCode().equals("code") && parent != null && !e.isNoBindingAllowed()) {
		  rule(errors, IssueType.STRUCTURE, path, e.hasBinding(), "An element of type code must have a binding");
		}
    if ((e.usesType("Coding") && !parentName.equals("CodeableConcept")) || e.usesType("CodeableConcept")) {
      warning(errors, IssueType.STRUCTURE, path, e.hasBinding(), "An element of type CodeableConcept or Coding must have a binding");
    }
		
		if (e.hasBinding()) {
		  rule(errors, IssueType.STRUCTURE, path, e.typeCode().equals("code") || e.typeCode().contains("Coding") 
				  || e.typeCode().contains("CodeableConcept") || e.typeCode().equals("uri"), "Can only specify bindings for coded data types");
		  if (e.getBinding().getValueSet() != null && e.getBinding().getValueSet().getName() == null)
		    throw new Error("unnamed value set on "+e.getBinding().getName());
			BindingSpecification cd = e.getBinding();
			
			if (cd != null) {
			  check(errors, path, cd, sd, e);
			  if (cd.getValueSet() != null) {
			    Integer w = (Integer) cd.getValueSet().getUserData("warnings");
			    if (w != null && w > 0 && !vsWarns.contains(cd.getValueSet().getId())) {
			      vsWarnings++;
			      vsWarns.add(cd.getValueSet().getId());
			    }
			  }
			}
		}

    String s = e.getMapping(Definitions.RIM_MAPPING);
    warning(errors, IssueType.REQUIRED, path, !needsRimMapping || !Utilities.noString(s), "RIM Mapping is required");

    needsRimMapping = needsRimMapping && !"n/a".equalsIgnoreCase(s) && !Utilities.noString(s);
    
		for (ElementDefn c : e.getElements()) {
		  vsWarnings = vsWarnings + checkElement(errors, path + "." + c.getName(), c, parent, e.getName(), needsRimMapping, optionalParent, hasSummary, vsWarns);
		}
		return vsWarnings;
	}


  private boolean isValidToken(String name, boolean root) {
    if (Utilities.noString(name))
      return false;
    for (char c : name.toCharArray()) {
      if (!isValidChar(c))
        return false;
    }
    if (!Character.isLetter(name.charAt(0)))
      return false;
    if (root && !Character.isUpperCase(name.charAt(0)))
      return false;
    if (!root && !Character.isLowerCase(name.charAt(0)))
      return false;
    return true;
  }

  private boolean isValidChar(char c) {
    if (c >= 'a' && c <= 'z')
      return true;
    if (c >= 'A' && c <= 'Z')
      return true;
    if (c >= '0' && c <= '9')
      return true;
    if (c == '[' || c == ']')
      return true;
    return false;
  }

  private boolean isExemptFromCodeList(String path) {
    return path.equals("Timing.repeat.when");
  }

  private boolean hasGoodCode(List<DefinedCode> codes) {
    for (DefinedCode d : codes) 
      if (!Utilities.isInteger(d.getCode()) && d.getCode().length() > 1)
        return true;
    return false;
  }

  private void checkDefinitions(List<ValidationMessage> errors, String path, ElementDefn e) {
    rule(errors, IssueType.STRUCTURE, path, e.hasDefinition(), "A Definition is required");
    
    if (!e.hasShortDefn()) 
      return;
    
    warning(errors, IssueType.STRUCTURE, path, !e.getShortDefn().equals(e.getDefinition()), "Element needs a definition of its own");
    warning(errors, IssueType.STRUCTURE, path, !e.getShortDefn().equals(e.getName()), "Short description can't be the same as the name");
    Set<String> defn = new HashSet<String>();
    for (String w : splitByCamelCase(e.getName()).toLowerCase().split(" ")) 
      defn.add(Utilities.pluralizeMe(w));
    for (String w : path.split("\\.")) 
      for (String n : splitByCamelCase(w).split(" ")) 
        defn.add(Utilities.pluralizeMe(n.toLowerCase()));
    
    Set<String> provided = new HashSet<String>();
    for (String w : stripPunctuation(splitByCamelCase(e.getShortDefn()), false).split(" "))
      if (!Utilities.noString(w) && !grammarWord(w.toLowerCase()))
        provided.add(Utilities.pluralizeMe(w.toLowerCase()));
    boolean ok = false;
    for (String s : provided)
      if (!defn.contains(s))
        ok = true;
    warning(errors, IssueType.STRUCTURE, path, ok, "Short description doesn't add any new content: '"+e.getShortDefn()+"'");
  }

  private boolean nameOverlaps(String name, String parentName) {
	  if (Utilities.noString(parentName))
	    return false;
	  if (name.equals(parentName))
      return false; // special case
	  String[] names = Utilities.splitByCamelCase(name);
	  String[] parentNames = Utilities.splitByCamelCase(parentName);
	  for (int i = 1; i <= names.length; i++) {
	    if (arraysMatch(copyLeft(names, i), copyRight(parentNames, i)))
	      return true;
	  }
	  return false;
  }

  private boolean arraysMatch(String[] a1, String[] a2) {
    if (a1.length != a2.length)
      return false;
    for (int i = 0; i < a1.length; i++) 
      if (!a1[i].equals(a2[i]))
           return false;
    return true;
  }

  private String[] copyLeft(String[] names, int length) {
    String[] p = new String[Math.min(length, names.length)];
    for (int i = 0; i < p.length; i++)
      p[i] = names[i];
    return p;
  }
  
  private String[] copyRight(String[] names, int length) {
    String[] p = new String[Math.min(length, names.length)];
    for (int i = 0; i < p.length; i++)
      p[i] = names[i + Math.max(0, names.length - length)];
    return p;
  }

  private void checkType(List<ValidationMessage> errors, String path, ElementDefn e, ResourceDefn parent) {
    if (e.getTypes().size() == 0) {
      rule(errors, IssueType.STRUCTURE, path, path.contains("."), "Must have a type on a base element");
      rule(errors, IssueType.STRUCTURE, path, e.getName().equals("extension") || e.getElements().size() > 0, "Must have a type unless sub-elements exist");
    } else {
      rule(errors, IssueType.STRUCTURE, path, e.getTypes().size() == 1 || e.getName().endsWith("[x]"), "If an element has a choice of data types, it's name must end with [x]");
      if (definitions.dataTypeIsSharedInfo(e.typeCode())) {
        try {
          e.getElements().addAll(definitions.getElementDefn(e.typeCode()).getElements());
        } catch (Exception e1) {
          rule(errors, IssueType.STRUCTURE, path, false, e1.getMessage());
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
              rule(errors, IssueType.STRUCTURE, path, typeExists(s, parent), "Illegal Type '" + s + "'");
              if (t.isResourceReference()) {
                for (String p : t.getParams()) {
                  rule(errors, IssueType.STRUCTURE, path,
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
	}

	private boolean typeExists(String name, ResourceDefn parent) {
		return definitions.hasType(name) || definitions.getBaseResources().containsKey(name) ||
				(parent != null && parent.getRoot().hasNestedType(name));
	}

	
//	private List<ValidationMessage> check(String n, BindingSpecification cd) throws Exception {
//    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
//    check(errors, n, cd);
//    return errors;
//  }
  
	private void check(List<ValidationMessage> errors, String path, BindingSpecification cd, String sd, ElementDefn e)  {
    // basic integrity checks
    for (DefinedCode c : cd.getAllCodes()) {
      String d = c.getCode();
      if (Utilities.noString(d))
        d = c.getId();
      if (Utilities.noString(d))
        d = c.getDisplay();
      if (Utilities.noString(d))
        d = c.getDisplay();
      
      if (Utilities.noString(c.getSystem()))
        warning(errors, IssueType.STRUCTURE, "Binding @ "+path, !Utilities.noString(c.getDefinition()), "Code "+d+" must have a definition");
      warning(errors, IssueType.STRUCTURE, "Binding @ "+path, !(Utilities.noString(c.getId()) && Utilities.noString(c.getSystem())) , "Code "+d+" must have a id or a system");
    }
    
    // trigger processing into a Heirachical set if necessary
//    rule(errors, IssueType.STRUCTURE, "Binding @ "+path, !cd.isHeirachical() || (cd.getChildCodes().size() < cd.getCodes().size()), "Logic error processing Hirachical code set");

    // now, rules for the source
    warning(errors, IssueType.STRUCTURE, "Binding @ "+path, cd.getBinding() != BindingMethod.Unbound, "Need to provide a binding");
    rule(errors, IssueType.STRUCTURE, "Binding @ "+path, cd.getElementType() != ElementType.Simple || cd.getBinding() != BindingMethod.Unbound, "Need to provide a binding for code elements");
    rule(errors, IssueType.STRUCTURE, "Binding @ "+path, (cd.getElementType() != ElementType.Simple || cd.getStrength() == BindingStrength.REQUIRED), "Must be a required binding if bound to a code instead of a Coding/CodeableConcept");
    rule(errors, IssueType.STRUCTURE, "Binding @ "+path, Utilities.noString(cd.getDefinition())  || (cd.getDefinition().charAt(0) == cd.getDefinition().toUpperCase().charAt(0)), "Definition cannot start with a lowercase letter");
    if (cd.getBinding() == BindingMethod.CodeList) {
      if (path.toLowerCase().endsWith("status")) {
        if (rule(errors, IssueType.STRUCTURE, path, definitions.getStatusCodes().containsKey(path), "Status element not registered in status-codes.xml")) {
          for (DefinedCode c : cd.getAllCodes()) {
            boolean ok = false;
            for (String s : definitions.getStatusCodes().get(path)) {
              String[] parts = s.split("\\,");
              for (String p : parts)
                if (p.trim().equals(c.getCode()))
                  ok = true;
            }
            rule(errors, IssueType.STRUCTURE, path, ok, "Status element code \""+c.getCode()+"\" not found in status-codes.xml");
          }
        }
      }
      StringBuilder b = new StringBuilder();
      for (DefinedCode c : cd.getAllCodes()) {
        b.append(" | ").append(c.getCode());
      }
      if (sd.equals("*")) {
        e.setShortDefn(b.toString().substring(3));
        sd = b.toString().substring(3);
      }
        
      if (sd.contains("|")) {
        String esd = b.substring(3);
        rule(errors, IssueType.STRUCTURE, path, sd.startsWith(esd) || (sd.endsWith("+") && b.substring(3).startsWith(sd.substring(0, sd.length()-1)) ), "The short description \""+sd+"\" does not match the expected (\""+b.substring(3)+"\")");
      } else
        rule(errors, IssueType.STRUCTURE, path, cd.getStrength() != BindingStrength.REQUIRED || cd.getAllCodes().size() > 20 || cd.getAllCodes().size() == 1 || !hasGoodCode(cd.getAllCodes()) || isExemptFromCodeList(path), "The short description of an element with a code list should have the format code | code | etc");
    }
    boolean isComplex = !e.typeCode().equals("code");

    if (isComplex && cd.getValueSet() != null && cd.getValueSet().hasDefine() && cd.getStrength() != BindingStrength.EXAMPLE && 
          !cd.getValueSet().getUrl().contains("/v2/") && !cd.getValueSet().getUrl().contains("/v3/")) {
      hint(errors, IssueType.BUSINESSRULE, path, false, "The value "+cd.getValueSet().getUrl()+" defines codes, but is used by a Coding/CodeableConcept @ "+path+", so it should not use FHIR defined codes");
      cd.getValueSet().setUserData("vs-val-warned", true);
    }
    
    if (cd.getElementType() == ElementType.Unknown) {
      if (isComplex)
        cd.setElementType(ElementType.Complex);
      else
        cd.setElementType(ElementType.Simple);
    } else if (cd.getBinding() != BindingMethod.Reference)
      if (isComplex)
        rule(errors, IssueType.STRUCTURE, path, cd.getElementType() == ElementType.Complex, "Cannot use a binding from both code and Coding/CodeableConcept elements");
      else
        rule(errors, IssueType.STRUCTURE, path, cd.getElementType() == ElementType.Simple, "Cannot use a binding from both code and Coding/CodeableConcept elements");
  }
    
  

  public void dumpParams() {
//    for (String s : usages.keySet()) {
//      System.out.println(s+": "+usages.get(s).usage.toString());
//    }
//    for (SearchType s : usagest.keySet()) {
//      System.out.println(s.toString()+": "+usagest.get(s).usage.toString());
//    }
  }

  public void report() {
    // for dumping of ad-hoc summaries from the checking phase
//    for (String t : typeCounter.keySet()) {
//      System.out.println(t+": "+typeCounter.get(t).toString());
//    }
    // for tracking individual name usage
    
//    int total = 0;
//    for (String n : names.keySet()) {
//      System.out.println(n+" = "+names.get(n));
//      total += names.get(n);
//    }
//    System.out.println("total = "+Integer.toString(total));
  }

  public void summariseSearchTypes(Set<String> searchTypeUsage) {
    for (SearchType st : usagest.keySet()) {
      for (String u : usagest.get(st).usage) {
        searchTypeUsage.add(u+":"+st.name());
      }
    }
    
  }

}
