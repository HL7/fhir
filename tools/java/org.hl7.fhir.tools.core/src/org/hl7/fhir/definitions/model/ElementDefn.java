package org.hl7.fhir.definitions.model;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.Type;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;

public class ElementDefn {

  public static final int MAX_NEG = -1000000;
	
	private List<TypeRef> types = new ArrayList<TypeRef>();
	private List<ElementDefn> elements = new ArrayList<ElementDefn>();
 
	private Integer minCardinality;
	private Integer maxCardinality;
	private List<Invariant> statedInvariants = new ArrayList<Invariant>(); // a reference to an invariant defined on another element, but which constrains this one
  private Boolean modifier;
  private String modifierReason;
	private Boolean mustSupport;
	private Boolean summaryItem; // whether this is included in a summary
	private String regex;
	private boolean xmlAttribute;

	private Map<String, String> mappings = new HashMap<String, String>();
	// private String id;
	private BindingSpecification binding;
	private String umlDir;
  private boolean umlBreak;
  private int svgLeft;
  private int svgTop;
  private int svgWidth;
	private String name;
	private String shortDefn;
	private String definition;
	private String requirements;
	private String comments;
	private String todo;
	private List<String> aliases = new ArrayList<String>();
	private String committeeNotes;
	private String condition;
	private String maxLength;
	
	private String profileName; // only in a profile, for slicing
	private List<String> discriminator = new ArrayList<String>(); // when slicing
  private Type example;
  private Map<Integer, Type> otherExamples = new HashMap<Integer, Type>();
//  private Type defaultValue;
  private String meaningWhenMissing;
  private Type fixed; // only in a profile
  private Type pattern; // only in a profile
	private ElementDefinition derivation;
	private boolean inherited; // in a profile, was this element add from the
								// base definition (true) or was it specifically
								// constrained in the profile (false)
  private String statedType; // explicitly stated type (=xxxx)
	private boolean isCoveredByExample; // true if an example has hit this
	private String displayHint; // hits for generated narrative
	private String w5;
	private boolean noBindingAllowed; // note to validator 
	private boolean translatable;
	private String orderMeaning;
	private StandardsStatus standardsStatus; // defaults to container value
	private Boolean hierarchy;
	private boolean abstractType;
	
	public ElementDefn() {
		super();
	  svgLeft = MAX_NEG;
	  svgTop = MAX_NEG;
	  svgWidth = MAX_NEG;
	}

	public ElementDefn(ElementDefn pattern) {
		super();
		types.addAll(pattern.types);
		for (ElementDefn c : pattern.getElements())
			elements.add(new ElementDefn(c));

		minCardinality = pattern.minCardinality;
		maxCardinality = pattern.maxCardinality;
		statedInvariants.addAll(pattern.statedInvariants);
		modifier = pattern.modifier;
		mustSupport = pattern.mustSupport;

		
		binding = pattern.binding;
		name = pattern.name;
		shortDefn = pattern.shortDefn;
		definition = pattern.definition;
		requirements = pattern.requirements;
		mappings.putAll(pattern.mappings);
		comments = pattern.comments;
		todo = pattern.todo;
		committeeNotes = pattern.committeeNotes;
		condition = pattern.condition;
		example = pattern.example;
		profileName = pattern.profileName;
		fixed = pattern.fixed;
		inherited = pattern.inherited;

	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public boolean hasCondition() {
		return condition != null && !"".equals(condition);
	}

  public boolean hasModifier() {
    return modifier != null;    
  }
  
  public boolean isModifier() {
		return modifier != null ? modifier : false;
	}

	public void setIsModifier(Boolean value) {
		this.modifier = value;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public String getCommitteeNotes() {
		return committeeNotes;
	}

	public void setCommitteeNotes(String committeeNotes) {
		this.committeeNotes = committeeNotes;
	}

	public String getDefinition() {
		return (definition == null || "".equals(definition)) ? Utilities.appendPeriod(shortDefn)
				: definition;
	}

	public String getEnhancedDefinition() {
	  if (isModifier() && getMustSupport())
      return Utilities.removePeriod(getDefinition()) + " (this element modifies the meaning of other elements, and must be supported)";
    else if (isModifier())
      return Utilities.removePeriod(getDefinition()) + " (this element modifies the meaning of other elements)";
    else if (getMustSupport())
      return Utilities.removePeriod(getDefinition()) + " (this element must be supported)";
    else
      return Utilities.removePeriod(getDefinition());
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Map<String, String> getMappings() {
		return mappings;
	}


	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	public void setElements(List<ElementDefn> elements) {
		this.elements = elements;
	}

	public List<ElementDefn> getElements() {
		return elements;
	}

	public boolean hasNestedElements() {
		return elements != null && !elements.isEmpty();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasName() {
		return this.name != null && !this.name.equals("");
	}

	public String getShortDefn() {
		return shortDefn;
	}

	public void setShortDefn(String shortDefn) {
		this.shortDefn = shortDefn;
	}

	public boolean hasShortDefn() {
		return shortDefn != null && !"".equals(shortDefn);
	}

	
	public String getMaxLength() {
    return maxLength;
  }

  public void setMaxLength(String maxLength) {
    this.maxLength = maxLength;
  }

  public ElementDefn getElementByName(String name, boolean throughChoice, Definitions definitions, String purpose, boolean followType) throws Exception {
    return getElementByName(name, throughChoice, definitions, purpose, followType, new ArrayList<ElementDefn>());
  }
  
  public ElementDefn getElementByName(String name, boolean throughChoice, Definitions definitions, String purpose, boolean followType, List<ElementDefn> trace) throws Exception {
    String n = name.contains(".") ? name.substring(0, name.indexOf(".")) : name;
    String t = name.contains(".") ? name.substring(name.indexOf(".") + 1) : null;
    if (n.equals(this.name) && t != null) {
      if (trace != null)
        trace.add(this);
      return getElementByName(definitions, t, throughChoice, followType, trace);
    }
    
    ElementDefn focus = this;
    
    if (typeCode().startsWith("@")) {
      String s = typeCode().substring(1);
      focus = definitions.getElementDefn(s.substring(0, s.indexOf(".")));
      focus = focus.getElementForPath(s, definitions, purpose, throughChoice, followType);
    }
      
    for (int i = focus.elements.size() - 1; i >= 0; i--) {
      ElementDefn e = focus.elements.get(i);
      if (nameMatches(n, e, throughChoice, definitions))
        return t == null ? e : e.getElementByName(definitions, t, throughChoice, followType, trace);
    }
    if (followType && focus.types.size() == 1 && !focus.getElements().isEmpty()) {
      ElementDefn parent = definitions.getElementDefn("Type".equals(focus.typeCode()) || "Structure".equals(focus.typeCode())  ? "Element" : focus.typeCode());
      return parent.getElementByName(definitions, name, throughChoice, followType, trace);
    }
    return null;
  }

  private boolean nameMatches(String n, ElementDefn e, boolean throughChoice, Definitions definitions) {
    if (e.getName().equals(n))
      return true;
    else if (!throughChoice || !e.getName().endsWith("[x]"))
      return false;
    else {
      String b = e.getName().substring(0, e.getName().indexOf("["));
      if (!n.startsWith(b))
        return false;
      String tn = n.substring(b.length());
      if (e.typeCode().equals("*") && definitions != null) {
        for (TypeRef t : definitions.getKnownTypes()) {
          if (!definitions.getInfrastructure().containsKey(t.getName()) && !definitions.getConstraints().containsKey(t.getName())) {
            if (t.getName().equalsIgnoreCase(tn))
              return true;
          }
        }
      } else for (TypeRef t : e.getTypes()) 
        if (t.getName().equalsIgnoreCase(tn))
          return true;
      return false;
    }
  }

  public ElementDefn getElementByName(Definitions definitions, String name, boolean throughChoice, boolean followType) {
    return getElementByName(definitions, name, throughChoice, followType, null);
  }
  public ElementDefn getElementByName(Definitions definitions, String name, boolean throughChoice, boolean followType, List<ElementDefn> trace) {
		String n = name.contains(".") ? name.substring(0, name.indexOf("."))
				: name;
		String t = name.contains(".") ? name.substring(name.indexOf(".") + 1)
				: null;
		if (n.equals(this.name) && t != null)
			return getElementByName(definitions, t, throughChoice, followType, trace);

		for (int i = elements.size() - 1; i >= 0; i--) {
			ElementDefn e = elements.get(i);
			if (nameMatches(n, e, throughChoice, null)) {
			  if (trace != null)
	        trace.add(e);
				return t == null ? e : e.getElementByName(definitions, t, throughChoice, followType, trace);
			}
		}
		// ok, didn't find it. do we have a reference or a type to follow?
		if (followType && !Utilities.noString(typeCode())) {
		  if (typeCode().startsWith("@")) {
        try {
          ElementDefn ed = definitions.getElementByPath(typeCode().substring(1).split("\\."), "resolution", true);
          return ed.getElementByName(definitions, n, throughChoice, followType, trace);
        } catch (Exception e) {
          return null;
        }
		  } else {
		    try {
		      TypeDefn type = definitions.getElementDefn(typeCode());
		      return type.getElementByName(definitions, name, throughChoice, followType, trace);
		    } catch (Exception e) {
		      return null;
		    }
		  }
		}
		return null;
	}

	public ElementDefn getElementByProfileName(String name) {
		for (int i = elements.size() - 1; i >= 0; i--) {
			ElementDefn e = elements.get(i);
			if (e.getProfileName().equalsIgnoreCase(name))
				return e;
		}
		return null;
	}

	public BindingSpecification getBinding() {
		return binding;
	}

	public void setBinding(BindingSpecification binding) {
		this.binding = binding;
	}

	// public String getId() {
	// return id;
	// }
	//
	// public void setId(String id) {
	// this.id = id;
	// }


	public Integer getMinCardinality() {
		return minCardinality;
	}

	public void setMinCardinality(Integer minCardinality) {
		this.minCardinality = minCardinality;
	}

	public Integer getMaxCardinality() {
		return maxCardinality;
	}  

	public void setMaxCardinality(Integer maxCardinality) {
		this.maxCardinality = maxCardinality;
	}

	public String describeCardinality() {
	  String min = minCardinality == null ? "" : minCardinality.toString();
	  String max = maxCardinality == null ? "" : maxCardinality == Integer.MAX_VALUE ? "*" : maxCardinality.toString();
		return min + ".." + max;
	}

	// public String textForCardinality() {
	// if (maxCardinality != null) {
	// if (maxCardinality == 1)
	// if (minCardinality == 0)
	// return "?One";
	// else
	// return "One";
	// else
	// return "??";
	// } else if (minCardinality == 0)
	// return "Zero+";
	// else
	// return "One+";
	// }

	public boolean hasDefinition() {
		return (this.definition != null && !this.definition.equals(""))
				|| (shortDefn != null && !this.shortDefn.equals(""));
	}

	public boolean unbounded() {
		return maxCardinality != null && maxCardinality == Integer.MAX_VALUE;
	}

  public boolean hasBinding() {
    return binding != null;
  }

	// If an element with children explicitly declares a typename
	// ('=<typename>' in Excel "Type" column), a resource-local type is
	// defined and its name stored on the parent element.
	private String declaredTypeName = null;
	
	public String getDeclaredTypeName()
	{
		return declaredTypeName;
	}
	
	public void setDeclaredTypeName(String typeName)
	{
		this.declaredTypeName = typeName;
	}
	
	
	private boolean isAnonymousTypedGroup = false;
	
	public boolean isAnonymousTypedGroup()
	{
		return this.isAnonymousTypedGroup;
	}
	
	public void setAnonymousTypedGroup(boolean value)
	{
		this.isAnonymousTypedGroup = value;
	}

	public List<TypeRef> getTypes() {
		return types;
	}
	
	public boolean hasOnlyType(String name) {
		return types.size() == 1 && types.get(0).getName().equals(name);
	}

  public boolean hasType(String name) {
    for (TypeRef t : types) {
      if (t.getName().equals(name))
        return true;
    }
    return false;
  }

	
	public String typeCode() {
		StringBuilder tn = new StringBuilder();
		boolean first = true;
		for (TypeRef t : types) {
			if (!first)
				tn.append("|");
			first = false;
			tn.append(t.getName());
			if (t.hasParams()) {
				tn.append("(");
				boolean f = true;
				for (String s : t.getParams()) {
					if (!f)
						tn.append("|");
					f = false;
					tn.append(s);
				}
				tn.append(")");
			}
		}
		return tn.toString();
	}

  public String typeCodeNoParams() {
    StringBuilder tn = new StringBuilder();
    boolean first = true;
    for (TypeRef t : types) {
      if (!first)
        tn.append("|");
      first = false;
      tn.append(t.getName());
    }
    return tn.toString();
  }

  public String typeCodeBase() {
    List<String> ts = new ArrayList<>();

    for (TypeRef t : types) {
      ts.add(t.getName());
    }
    
    Collections.sort(ts);
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (String t : ts)
      b.append(t);
    return b.toString();
  }

  public String resolvedTypeCode(Definitions definitions) {
    StringBuilder tn = new StringBuilder();
    boolean first = true;
    for (TypeRef t : types) {
      if (!first)
        tn.append("|");
      first = false;
      tn.append(t.getName());
      if (t.hasParams()) {
        tn.append("(");
        boolean f = true;
        for (String s : t.getParams()) {
          if (definitions.hasLogicalModel(s)) {
            for (String sn : definitions.getLogicalModel(s).getImplementations()) {
              if (!f)
                tn.append("|");
              f = false;
              tn.append(sn);
            }
          } else {
            if (!f)
              tn.append("|");
            f = false;
            tn.append(s);
          }
        }
        tn.append(")");
      }
    }
    return tn.toString();
  }

	
	public boolean usesCompositeType() {
		return this.typeCode().startsWith("@");
	}
	
	public Type getExample() {
		return example;
	}

	public void setExample(Type example) {
		this.example = example;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Type getFixed() {
		return fixed;
	}

	public void setFixed(Type value) {
		this.fixed = value;
	}

	public boolean hasFixed() {
		return fixed != null && !fixed.equals("");
	}

	public void ban() {
		minCardinality = 0;
		maxCardinality = 0;
	}

	public void setDerivation(ElementDefinition derivation) {
		this.derivation = derivation;
	}

	public boolean isInherited() {
		return inherited;
	}

	public void setInherited(boolean inherited) {
		this.inherited = inherited;
	}

	public ElementDefinition getDerivation() {
		return derivation;
	}

	/**
	 * Warning: this method is only safe to call if the owner element has type
	 * "resource". The element names "id" and "text" are used in other contexts
	 * for valid element names
	 * 
	 * @return if this element is a standard Resource element like 'id',
	 *         'extension' and 'text'
	 */
	public boolean isBaseResourceElement() {
		return getName().equals("id") || getName().equals("extension")
				|| getName().equals("text");
	}

	public boolean isBoundCode() {
		return typeCode().equals("code") && hasBinding();
	}

	public List<Invariant> getStatedInvariants() {
		return statedInvariants;
	}

  public boolean hasMustSupport() {
    return mustSupport != null;
  }

	public Boolean isMustSupport() {
		return mustSupport;
	}

	public void setMustSupport(Boolean value) {
		this.mustSupport = value;
	}


	public boolean isXhtmlElement() {
		return !types.isEmpty() && types.get(0).isXhtml();
	}

	
	public ElementDefn getElementForPath(String pathname, Definitions definitions, String purpose, boolean throughChoice, boolean followType) throws Exception {
		String[] path = pathname.split("\\.");

		if (!path[0].equals(getName()) && !path[0].equals("{{name}}"))
			throw new Exception("Element Path '" + pathname
					+ "' is not legal in this context ("+purpose+") - expected "+getName()+" found "+path[0]);

		ElementDefn res = this;

		for (int i = 1; i < path.length; i++) {
			String en = path[i];
			if (en.startsWith("extension("))
			  return null; // don't resolve these here
			if (en.length() == 0)
				throw new Exception("Improper path " + pathname);
			ElementDefn t = null;

			if (res.typeCode().startsWith("@")) {
			  res = this.getElementForPath(res.typeCode().substring(1), definitions, purpose, throughChoice, followType);
			} else if (definitions.dataTypeIsSharedInfo(res.typeCode())) {
				res = definitions.getElementDefn(res.typeCode());
			} else if (definitions.hasType(res.typeCode()) && !"Base".equals(res.typeCode())) {
				res = definitions.getElementDefn(res.typeCode());
			}
			t = res.getElementByName(en, throughChoice, definitions, purpose, followType);
			if (t == null) {
			  return null;
			}
			res = t;

		}

		return res;
	}

	
	   /**** 
	    * Helper data for mapping the current model definitions to the
	    * eCore model. Are valid if this ElementDefn is the root of a type
	    * 	NestedTypes() - The nested groups of elements which are reused within
	    * 					the same resource/composite type and have been given an
	    * 					explicit name using the new '=<typename>' construct.
	    * 	NestedBindings() - The bindings as defined on the "Bindings" tab in
	    * 					the resource/composite type specification xls.
	    *   Invariants()  - The variants as referred to by the elements of
	    *   				a resource or composite type
	    *   AcceptableGenericTypes() - The types that may be uses as generic parameter
	    *   				to this type, as specified in fhir.ini (e.g. Interval(dateTime))			
	    ****/
	      
	   private Map<String, ElementDefn> nestedTypes = new HashMap<String, ElementDefn>();
	   
	   public Map<String, ElementDefn> getNestedTypes()
	   {
		   return nestedTypes;
	   }
	   
	   public boolean hasNestedType(String typename)
	   {
		   return nestedTypes.containsKey(typename);
	   }

	   
	   private Map<String, BindingSpecification> nestedBindings = new HashMap<String, BindingSpecification>();
	   
	   public Map<String, BindingSpecification> getNestedBindings()
	   {
		   return nestedBindings;
	   }

	   private Map<String, Invariant> invariants = new HashMap<String, Invariant>();

	   public Map<String, Invariant> getInvariants() 
	   {
			    return invariants;
	   }
	   
    private String sliceDescription;

    private String path;

    private boolean fromTemplate;

    private String normativeVersion;

    public boolean hasComments() {
      return comments != null && !"".equals(comments);
    }

    public boolean hasMapping(String name) {
      return mappings.containsKey(name);
    }

    public String getUmlDir() {
      return umlDir;
    }

    public void setUmlDir(String umlDir) {
      this.umlDir = umlDir;
    }

    public List<String> getAliases() {
      return aliases;
    }

	public void addMapping(String name, String value) {
		if (!Utilities.noString(value))
			mappings.put(name, value);
		
	}

	public String getMapping(String name) {
		return mappings.get(name);
	}

  public boolean isCoveredByExample() {
    return isCoveredByExample;
  }

  public void setCoveredByExample(boolean isCoveredByExample) {
    this.isCoveredByExample = isCoveredByExample;
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public boolean hasStatedType() {
    return statedType != null;
  }

  public String getStatedType() {
    return statedType;
  }

  public void setStatedType(String statedType) {
    this.statedType = statedType;
  }

  public boolean isSummary() {
    return summaryItem != null && summaryItem; 
  }

  public boolean hasSummaryItem() {
    return summaryItem != null; 
  }

  public Boolean isSummaryItem() {
    return summaryItem;
  }

  public void setSummaryItem(Boolean summaryItem) {
    this.summaryItem = summaryItem;
  }

  public boolean isUmlBreak() {
    return umlBreak;
  }

  public void setUmlBreak(boolean umlBreak) {
    this.umlBreak = umlBreak;
  }

  public List<String> getDiscriminator() {
    return discriminator;
  }

  public boolean hasSvg() {
    return svgLeft != -1;
  }

  public void setSvgLeft(int value) {
    svgLeft = value;   
  }

  public void setSvgTop(int value) {
    svgTop = value;       
  }

  public void setSvgWidth(int value) {
    svgWidth = value;    
  }
   
  public int getSvgLeft() {
    return svgLeft;
  }

  public int getSvgTop() {
    return svgTop;
  }

  public int getSvgWidth() {
    return svgWidth;
  }

  public boolean usesType(String name) {
    for (TypeRef t : getTypes()) {
      if (t.summary().equals(name) || (t.getName().equals(name) && name.equals("Reference")))
        return true;
    }
    return false;
  }

  public boolean isMandatory() {
    return (minCardinality != null && maxCardinality != null && minCardinality == 1 && maxCardinality == 1);    
  }

  public boolean isXmlAttribute() {
    return xmlAttribute;
  }

  public void setXmlAttribute(boolean xmlAttribute) {
    this.xmlAttribute = xmlAttribute;
  }

  public String getDisplayHint() {
    return displayHint;
  }

  public void setDisplayHint(String displayHint) {
    this.displayHint = displayHint;
  }

  public boolean hasStatedProfile() {
    if (types.isEmpty())
      return false;
    else for (TypeRef t : types)
      if (t.getProfile() != null)
        return true;
    return false;
  }

  public Type getPattern() {
    return pattern;
  }

  public void setPattern(Type pattern) {
    this.pattern = pattern;
  }

  public String getSliceDescription() {
    return sliceDescription;
  }

  public void setSliceDescription(String sliceDescription) {
    this.sliceDescription = sliceDescription;
  }

//  public Type getDefaultValue() {
//    return defaultValue;
//  }
//
//  public void setDefaultValue(Type defaultValue) {
//    this.defaultValue = defaultValue;
//  }

  public String getMeaningWhenMissing() {
    return meaningWhenMissing;
  }

  public boolean hasMeaningWhenMissing() {
    return !Utilities.noString(meaningWhenMissing);
  }

  public void setMeaningWhenMissing(String meaningWhenMissing) {
    this.meaningWhenMissing = meaningWhenMissing;
  }

  public String getW5() {
    return w5;
  }

  public void setW5(String w5) {
    this.w5 = w5;
  }

  public boolean eliminated() {
    return getMaxCardinality() != null && getMaxCardinality() == 0;
  }

  public boolean getMustSupport() {
    return mustSupport == null ? false: mustSupport;
  }

  public boolean isNoBindingAllowed() {
    return noBindingAllowed;
  }

  public void setNoBindingAllowed(boolean noBindingAllowed) {
    this.noBindingAllowed = noBindingAllowed;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Map<Integer, Type> getOtherExamples() {
    return otherExamples;
  }

  public String getPathTail() {
    return path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path;
  }

  public void copyFrom(ElementDefn other, String rootName, String title) {
    String titles = Utilities.pluralize(title, 2);
    types.clear();
    types.addAll(other.types);
    minCardinality = other.minCardinality;
    maxCardinality = other.maxCardinality;
    statedInvariants.clear();
    statedInvariants.addAll(other.statedInvariants);
    modifier = other.modifier;
    modifierReason = other.modifierReason;
    mustSupport = other.mustSupport;
    summaryItem = other.summaryItem; 
    regex = other.regex;
    xmlAttribute = other.xmlAttribute;
    mappings.clear();
    mappings.putAll(other.mappings);
    binding = other.binding;
    umlDir = other.umlDir;
    umlBreak = other.umlBreak;
    svgLeft = other.svgLeft;
    svgTop = other.svgTop;
    svgWidth = other.svgWidth;
    name = other.name == null ? null : other.name.replace("{{title}}", title).replace("{{titles}}", titles);
    shortDefn = other.shortDefn == null ? null : other.shortDefn.replace("{{title}}", title).replace("{{titles}}", titles);
    definition = other.definition == null ? null : other.definition.replace("{{title}}", title).replace("{{titles}}", titles);
    requirements = other.requirements == null ? null : other.requirements.replace("{{title}}", title).replace("{{titles}}", titles);
    comments = other.comments == null ? null : other.comments.replace("{{title}}", title).replace("{{titles}}", titles);
    todo = other.todo;
    aliases.clear();
    aliases.addAll(other.aliases);
    committeeNotes = other.committeeNotes == null ? null : other.committeeNotes.replace("{{title}}", title).replace("{{titles}}", titles);
    condition = other.condition;
    maxLength = other.maxLength;
    profileName = other.profileName;
    discriminator.clear();
    discriminator.addAll(other.discriminator);
    example = other.example;
    otherExamples.clear();
    otherExamples.putAll(other.otherExamples);
//    defaultValue = other.defaultValue;
    meaningWhenMissing = other.meaningWhenMissing == null ? null : other.meaningWhenMissing.replace("{{title}}", title).replace("{{titles}}", titles);
    fixed = other.fixed;
    pattern = other.pattern;
    derivation = other.derivation;
    inherited = other.inherited;
    statedType = other.statedType;
    isCoveredByExample = other.isCoveredByExample;
    displayHint = other.displayHint;
    w5 = other.w5;
    noBindingAllowed = other.noBindingAllowed;
    fromTemplate = true; 
  }

  public boolean isFromTemplate() {
    return fromTemplate;
  }

  public boolean isTranslatable() {
    return translatable;
  }

  public void setTranslatable(boolean translatable) {
    this.translatable = translatable;
  }

  public String getOrderMeaning() {
    return orderMeaning;
  }

  public void setOrderMeaning(String orderMeaning) {
    this.orderMeaning = orderMeaning;
  }

  public boolean hasDescriminator() {
    return !discriminator.isEmpty();
  }

  public StandardsStatus getStandardsStatus() {
    return standardsStatus;
  }

  public void setStandardsStatus(StandardsStatus standardsStatus) {
    this.standardsStatus = standardsStatus;
  }

  public String getModifierReason() {
    return modifierReason;
  }

  public void setModifierReason(String modifierReason) {
    this.modifierReason = modifierReason;
  }

  public Boolean getHierarchy() {
    return hierarchy;
  }

  public boolean hasHierarchy() {
    return hierarchy != null;
  }

  public void setHierarchy(Boolean hierarchy) {
    this.hierarchy = hierarchy;
  }	
  
  
  public String getNormativeVersion() {
    return normativeVersion;
  }

  public void setNormativeVersion(String normativeVersion) {
    this.normativeVersion = normativeVersion;
  }

  public String getNormativeVersion(ResourceDefn rd) {
    if (standardsStatus != null && standardsStatus != StandardsStatus.NORMATIVE)
      return null;
    else if (normativeVersion != null)
      return normativeVersion;
    else
      return rd.getNormativeVersion();
  }

  @Override
  public String toString() {
    return path == null ? name : path;
  }

  public boolean isAbstractType() {
    return abstractType;
  }

  public void setAbstractType(boolean abstractType) {
    this.abstractType = abstractType;
  }
  

  
}

