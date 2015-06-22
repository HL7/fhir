package org.hl7.fhir.definitions.generators.specification;

import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.definitions.model.W5Entry;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.Enumerations.BindingStrength;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.instance.model.valuesets.IssueType;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.rdf.TurtleGenerator;
import org.hl7.fhir.utilities.Utilities;

public class FhirTurtleGenerator extends TurtleGenerator {
  
  private class AnonTypeInfo {
    private String name;
    private ElementDefn defn;
    private boolean type;
    private Section section;
    public AnonTypeInfo(Section section, String name, ElementDefn defn, boolean type) {
      super();
      this.section = section;
      this.name = name;
      this.defn = defn;
      this.type = type;
    }
    public Section getSection() {
      return section;
    }
    public ElementDefn getDefn() {
      return defn;
    }
    public String getName() {
      return name;
    }
    public boolean isType() {
      return type;
    }
  }
  
  private Definitions definitions;
  private WorkerContext context;
  private Deque<AnonTypeInfo> anonTypes = new ArrayDeque<AnonTypeInfo>();
  private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();
  private Subject nilInstance;
  private List<ValidationMessage> issues;

  public FhirTurtleGenerator(OutputStream destination, Definitions definitions, WorkerContext context, List<ValidationMessage> issues) {
    super(destination);
    this.definitions = definitions;
    this.context = context;
    this.issues = issues;
  }
  
  /**
   * Only produce the v3 vocabulary for appending to rim.ttl
   * @throws Exception
   */
  public void executeV3(Bundle bundle) throws Exception {
    for (BundleEntryComponent e : bundle.getEntry()) {
      ValueSet vs = (ValueSet) e.getResource();
      valuesets.put("vs:"+tail(vs.getUrl()), vs);
    }

    for (String n : sorted(valuesets.keySet()))
      gen(n, valuesets.get(n));
    commit(false);
  }
  
  private String tail(String url) {
    return url.substring(url.lastIndexOf("/")+1);
  }

  /**
   * prouce main fhir.ttl file
   * @throws Exception
   */
  public void executeMain() throws Exception {
//    triple(fhir("FHIR"), isa, none("spec"));

    prefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    prefix("rdfs", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    prefix("fhir", "http://hl7.org/fhir/");
    prefix("fhir-vs", "http://hl7.org/fhir/vs/");
    prefix("ex", "http://hl7.org/fhir/StructureDefinition/");
    prefix("xs", "http://www.w3.org/2001/XMLSchema#");
    prefix("owl", "http://www.w3.org/2002/07/owl#");
    prefix("dc", "http://purl.org/dc/elements/1.1/");
    prefix("dcterms", "http://purl.org/dc/terms/");
    prefix("rim", "http://hl7.org/owl/rim/");
    prefix("cs", "http://hl7.org/orim/codesystem/");
    prefix("vs", "http://hl7.org/orim/valueset/");
    prefix("dt", "http://hl7.org/orim/datatype/");
    prefix("os", "http://open-services.net/ns/core#");
    prefix("loinc", "http://loinc.org/owl#");
    
    genBaseMetadata();
    
    gen(definitions.getInfrastructure().get("Element"));
    genPrimitiveType();
    
    for (String n : sorted(definitions.getPrimitives().keySet())) {
      DefinedCode t = definitions.getPrimitives().get(n);
      if (t instanceof PrimitiveType)
        gen((PrimitiveType) t);
      if (t instanceof DefinedStringPattern)
        gen((DefinedStringPattern) t);
    }
    for (String n : sorted(definitions.getInfrastructure().keySet()))
      if (!n.equals("Element"))
      gen(definitions.getInfrastructure().get(n));
    for (String n : sorted(definitions.getTypes().keySet())) 
      gen(definitions.getTypes().get(n));
    for (String n : sorted(definitions.getStructures().keySet())) 
      gen(definitions.getStructures().get(n));
    for (String n : sorted(definitions.getConstraints().keySet()))
      gen(definitions.getConstraints().get(n));
    gen(definitions.getBaseResources().get("Resource"));
    for (String n : sorted(definitions.getBaseResources().keySet()))
      if (!n.equals("Resource"))
        gen(definitions.getBaseResources().get(n));
    for (String n : sorted(definitions.getResources().keySet())) 
      gen(definitions.getResources().get(n));
    
    for (String n : sorted(context.getExtensionDefinitions().keySet()))
      genExtension(context.getExtensionDefinitions().get(n));
    
    for (String n : sorted(valuesets.keySet()))
      gen(n, valuesets.get(n));

    chckSubjects();
    commit(true);
  }


  private void genBaseMetadata() {
    Section section = section("Basic Framework");
    
    // terminology base:
    section.triple("fhir:Concept", "a", "rdf:Class");
    section.label("fhir:Concept", "A concept in a code system defined by FHIR");
    section.comment("fhir:Concept", "Concepts are defined as classes, and individual properties have a value which is an anonymous class with a type of the appropriate concept");

    section.triple("fhir:CodeSystem", "rdfs:subClassOf", "rdf:Class");
    section.label("fhir:CodeSystem", "Base class for code systems");
    section.comment("fhir:CodeSystem", "A code system identifies the definition framework. Code systems contain concepts");

    section.triple("fhir:ValueSet", "rdfs:subClassOf", "rdf:Class");
    section.label("fhir:ValueSet", "A value set - a set of codes for concepts from one or more code systems");
    section.comment("fhir:Concept", "ValueSets...");

    // wiring the terminology base together 
    section.triple("fhir:contains", "a", "rdf:Property");
    section.label("fhir:contains", "A concept in a code system");
    section.triple("fhir:contains", "rdfs:domain", "fhir:CodeSystem");
    section.triple("fhir:contains", "rdfs:range", "fhir:Concept"); // todo: is this valid? a meta-range? 
    
    section.triple("fhir:include", "a", "rdf:Property");
    section.label("fhir:include", "A concept included in a value set");
    section.comment("fhir:include", "Include a code system or value set in a value set");
    section.triple("fhir:include", "rdfs:domain", "fhir:ValueSet");
    section.triple("fhir:include", "rdfs:range", "fhir:Concept"); // todo: is this valid? a meta-range? 
    
    // standard properties
    section.triple("fhir:binding", "a", "rdf:Property");
    section.label("fhir:binding", "ValueSet bound to this property");
    section.comment("fhir:binding", "Refer to FHIR documentation for the use of this property");
    section.triple("fhir:binding", "rdfs:range", "xs:anyUri");
    
    section.triple("fhir:bindingStrength", "a", "rdf:Property");
    section.label("fhir:bindingStrength", "Strength of ValueSet binding");
    section.comment("fhir:bindingStrength", "Refer to FHIR documentation for the use of this property");
    section.triple("fhir:bindingStrength", "rdfs:range", "xs:anyUri");

    section.triple("fhir:canonicalStatus", "a", "rdf:Property");
    section.label("fhir:canonicalStatus", "Canonical Status Mapping");
    section.comment("fhir:canonicalStatus", "Each resource has it's own list of status codes. This contains a mapping through to the basic status");
    section.triple("fhir:canonicalStatus", "rdfs:range", "fhir:canonical-status");
    
    section.triple("fhir:hasFlag", "a", "rdf:Property");
    section.label("fhir:hasFlag", "Flags for FHIR properties");
    section.comment("fhir:hasFlag", "A set of flags that provide additional knowledge about the meaning of a property");
    section.triple("fhir:hasFlag", "rdfs:range", "fhir:flag-item");

    section.triple("fhir:w5", "a", "rdf:Property");
    section.label("fhir:w5", "W5 Categorization (preliminary)");
    section.comment("fhir:w5", "FHIR W5 categorization is a preliminary classification of the type of fhir property");
    section.triple("fhir:w5", "rdfs:range", "fhir:w5");


    section.triple("fhir:status", "a", "rdf:Property");
    section.label("fhir:status", "The status of the item");
    section.comment("fhir:status", "The status of the item");
    section.triple("fhir:status", "rdfs:range", "fhir:conformance-resource-status");

    section.triple("fhir:version", "a", "rdf:Property");
    section.label("fhir:version", "Assigned Version Number");
    section.comment("fhir:version", "The version number assigned to the item");
    section.triple("fhir:version", "rdfs:range", "xs:string");

    section.triple("fhir:loinc", "a", "rdf:Property");
    section.label("fhir:loinc", "LOINC equivalent concept");
    section.comment("fhir:loinc", "LOINC equivalent concept - may be a LOINC code, a LOINC part code, or a LOINC property");
    section.triple("fhir:loinc", "rdfs:range", "xs:anyURI");

    // nil instance
    nilInstance = section.subject("fhir:nil");
    nilInstance.comment("To indicate positively that a property has no value. Intended to be used where elements have a meaning when missing, to indicate that they realyl are missing in the instance");
    
    //---------------------------------------------------
    section = section("Property Flags");
    section.triple("fhir:flag-item.system", "a", "fhir:CodeSystem");
    section.comment("fhir:flag-item.system", "An internal code system defined for the FHIR definitions");
    section.triple("fhir:flag-item.system", "fhir:contains", "fhir:flag-item");
    
    section.triple("fhir:flag-item", "a", "fhir:Concept");

    section.triple("fhir:flag-item\\#isModifier", "rdfs:subClassOf", "fhir:flag-item");
    section.triple("fhir:flag-item\\#isModifier", "owl:oneOf", "(fhir:flag-item\\#isModifier)");
    section.comment("fhir:flag-item\\#isModifier", "An element is labeled isModifier if the value it contains may change the interpretation of the element that contains it (including if the element is the resource as a whole). Typical examples of elements that are labeled isModifier are elements such as 'status', 'active', 'refuted', or 'certainty'");

    section.triple("fhir:flag-item\\#isSummaryItem", "rdfs:subClassOf", "fhir:flag-item");
    section.triple("fhir:flag-item\\#isSummaryItem", "owl:oneOf", "(fhir:flag-item\\#isSummaryItem)");
    section.comment("fhir:flag-item\\#isSummaryItem", "Whether the element should be included if a client requests a search with the parameter _summary=true.");

    section.triple("fhir:flag-item\\#isXmlAtribute", "rdfs:subClassOf", "fhir:flag-item");
    section.triple("fhir:flag-item\\#isXmlAtribute", "owl:oneOf", "(fhir:flag-item\\#isXmlAtribute)");
    section.comment("fhir:flag-item\\#isXmlAtribute", "In the XML format, this property is represented as an attribute not an element");
    section.triple("fhir:isXmlAtribute", "a", "fhir:flag-item\\#isXmlAtribute");
    
    //---------------------------------------------------   
    section = section("Canonical Status Codes");
    section.triple("fhir:canonical-status.system", "a", "fhir:CodeSystem");
    section.comment("fhir:canonical-status.system", "An internal ontology defined for the FHIR definitions");
    section.triple("fhir:canonical-status.system", "fhir:contains", "fhir:canonical-status");
    
    section.triple("fhir:canonical-status", "a", "fhir:Concept");

    for (int i = 0; i < definitions.getStatusCodes().get("@code").size(); i++) {
      if (!Utilities.noString(definitions.getStatusCodes().get("@code").get(i))) {
        String pcc = pctEncode(definitions.getStatusCodes().get("@code").get(i));
        section.triple("fhir:canonical-status\\#"+pcc, "rdfs:subClassOf", "fhir:canonical-status");
        section.comment("fhir:canonical-status\\#"+pcc, definitions.getStatusCodes().get("@definition").get(i));
      }
    }
    
    //---------------------------------------------------   
    section = section("W5 Classifications");
    section.triple("fhir:w5.system", "a", "fhir:CodeSystem");
    section.comment("fhir:w5.system", "An internal ontology defined for the FHIR definitions");
    section.triple("fhir:w5.system", "fhir:contains", "fhir:w5");
    
    section.triple("fhir:w5", "a", "fhir:Concept");

    for (W5Entry e : definitions.getW5s().values()) {
      String pcc = pctEncode(e.getCode());
      section.triple("fhir:w5\\#"+pcc, "rdfs:subClassOf", "fhir:w5");
      section.comment("fhir:w5\\#"+pcc, e.getDescription());
    }
  }

//  private String listW5Codes() {
//    StringBuilder b = new StringBuilder();
//    for (W5Entry e : definitions.getW5s().values()) {
//      b.append(" ");
//      b.append("fhir:w5\\#");
//      b.append(pctEncode(e.getCode()));
//    }
//    return b.toString().substring(1);
//  }
//
//  private String listStatusCodes() {
//    StringBuilder b = new StringBuilder();
//    for (String s : definitions.getStatusCodes().get("@code")) {
//      b.append(" ");
//      b.append("fhir:canonical-status\\#");
//      b.append(pctEncode(s));
//    }
//    return b.toString().substring(1);
//  }

  private void genPrimitiveType() {
    Section section = section("Primitive");
    section.triple("fhir:Primitive", "rdfs:subClassOf", "fhir:Element");
    section.triple("fhir:Primitive.value", "a", "rdf:Property");
    section.triple("fhir:Primitive.value", "rdfs:domain", "fhir:Primitive");
    cardinality(section, "fhir:Primitive.value", "0", "1");
  }

  private void cardinality(Section section, String subj, String min, String max) {
    if ("0".equals(min) && "1".equals(max))
      section.triple(subj, "os:occurs", "os:Zero-or-one");
    else if ("0".equals(min) && "*".equals(max))
      section.triple(subj, "os:occurs", "os:Zero-or-many");
    else if ("1".equals(min) && "1".equals(max))
      section.triple(subj, "os:occurs", "os:Exactly-one");
    else if ("1".equals(min) && "*".equals(max))
      section.triple(subj, "os:occurs", "os:One-or-many");
    else
      throw new Error("Unhandled cardinality combination ("+min+"/"+max+")");
  }

  private void cardinality(Subject subject, String min, String max) {
    if ("0".equals(min) && "1".equals(max))
      subject.predicate("os:occurs", "os:Zero-or-one");
    else if ("0".equals(min) && "*".equals(max))
      subject.predicate("os:occurs", "os:Zero-or-many");
    else if ("1".equals(min) && "1".equals(max))
      subject.predicate("os:occurs", "os:Exactly-one");
    else if ("1".equals(min) && "*".equals(max))
      subject.predicate("os:occurs", "os:One-or-many");
    else
      throw new Error("Unhandled cardinality combination ("+min+"/"+max+")");
  }

  private void gen(PrimitiveType t) {
    Section section = section(t.getCode());
    section.triple("fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:Primitive");
    section.comment("fhir:"+t.getCode(), t.getDefinition());
    nilInstance.predicate("a", "fhir:"+t.getCode());
    section.triple("fhir:"+t.getCode()+".value", "rdfs:subPropertyOf", "fhir:Primitive.value");
    section.triple("fhir:"+t.getCode()+".value", "rdfs:domain", "fhir:"+t.getCode());
    if (t.getSchemaType().endsWith("+")) {
      section.triple("fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType().substring(0, t.getSchemaType().length()-1));
      section.triple("fhir:"+t.getCode()+".value", "owl:withRestriction", complex().predicate("xs:pattern", literal(t.getRegEx())));
    } else if (t.getSchemaType().contains(",")) {
      section.triple("fhir:"+t.getCode()+".value", "rdfs:range", complex().predicate("a", "owl:Class").predicate("owl:unionOf", "("+t.getSchemaType().replace(",", "")+")"), "xs:union of "+t.getSchemaType());
    } else
      section.triple("fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType());
  }

  private void gen(DefinedStringPattern t) {
    Section section = section(t.getCode());
    section.triple("fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:"+t.getBase());
    section.comment("fhir:"+t.getCode(), t.getDefinition());
    nilInstance.predicate("a", "fhir:"+t.getCode());
    section.triple("fhir:"+t.getCode()+".value", "rdfs:subPropertyOf", "fhir:"+t.getBase()+".value");
    if (t.getSchema().endsWith("+")) {
      section.triple("fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema().substring(0, t.getSchema().length()-1));
      section.triple("fhir:"+t.getCode()+".value", "owl:withRestriction", complex().predicate("xs:pattern", literal(t.getRegex())));
    } else
      section.triple("fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema());
  }

  private void gen(ProfiledType t) throws Exception {
    Section section = section(t.getName());
    section.triple("fhir:"+t.getName(), "rdfs:subClassOf", "fhir:"+t.getBaseType());
    nilInstance.predicate("a", "fhir:"+t.getName());

    section.label("fhir:"+t.getName(), t.getDescription());
    section.comment("fhir:"+t.getName(), t.getDefinition());
    if (!Utilities.noString(t.getInvariant().getTurtle()))
      section.importTtl(t.getInvariant().getTurtle());
  }

  
  private void gen(TypeDefn t) throws Exception {
    Section section = section(t.getName());
    if (t.getTypes().isEmpty())
      section.triple("fhir:"+t.getName(), "a", "rdfs:Class");
    else
      section.triple("fhir:"+t.getName(), "rdfs:subClassOf", "fhir:Element");
    section.label("fhir:"+t.getName(), t.getShortDefn());
    section.comment("fhir:"+t.getName(), t.getDefinition());
    if (t.getName().equals("Reference")) 
      section.triple("fhir:"+t.getName(), "a", "fhir:Resource"); // This is so that a reference can be replaced by a direct reference to it's target
    nilInstance.predicate("a", "fhir:"+t.getName());
    processMappings(section, "fhir:"+t.getName(), t);
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        section.triple("fhir:"+t.getName()+"."+cn, "a", "rdf:Property");
        section.label("fhir:"+t.getName()+"."+cn, e.getShortDefn());
        section.comment("fhir:"+t.getName()+"."+cn, e.getDefinition());
        processMappings(section, "fhir:"+t.getName()+"."+cn, e);
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          section.triple("fhir:"+t.getName()+"."+en, "rdfs:subPropertyOf", "fhir:"+t.getName()+"."+cn);
          section.triple("fhir:"+t.getName()+"."+en, "rdfs:domain", "fhir:"+t.getName());
          genRange(section, t.getName(), en, e, tr, true);
        }
      } else {
        section.triple("fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property");
        section.comment("fhir:"+t.getName()+"."+e.getName(), e.getDefinition());
        section.triple("fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+t.getName());
        processMappings(section, "fhir:"+t.getName()+"."+e.getName(), e);
        genRange(section, t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), true);
      }
    }
    processAnonTypes();
  }

  private List<TypeRef> getAnyTypes() {
    List<TypeRef> refs = new ArrayList<TypeRef>();
    refs.add(new TypeRef("integer"));
    refs.add(new TypeRef("decimal"));
    refs.add(new TypeRef("dateTime"));
    refs.add(new TypeRef("date"));
    refs.add(new TypeRef("instant"));
    refs.add(new TypeRef("time"));
    refs.add(new TypeRef("string"));
    refs.add(new TypeRef("uri"));
    refs.add(new TypeRef("boolean"));
    refs.add(new TypeRef("code"));
    refs.add(new TypeRef("base64Binary"));
    refs.add(new TypeRef("Coding"));
    refs.add(new TypeRef("CodeableConcept"));
    refs.add(new TypeRef("Attachment"));
    refs.add(new TypeRef("Identifier"));
    refs.add(new TypeRef("Quantity"));
    refs.add(new TypeRef("Range"));
    refs.add(new TypeRef("Period"));
    refs.add(new TypeRef("Ratio"));
    refs.add(new TypeRef("HumanName"));
    refs.add(new TypeRef("Address"));
    refs.add(new TypeRef("ContactPoint"));
    refs.add(new TypeRef("Timing"));
    refs.add(new TypeRef("Signature"));
    refs.add(new TypeRef("Reference"));
    return refs;
  }
  
  private void genRange(Section section, String tn, String en, ElementDefn e, TypeRef tr, boolean datatype) throws Exception {
    // metadata
    if (e.isModifier())
      section.triple("fhir:"+tn+"."+en, "fhir:hasFlag", complex().predicate("a", "fhir:isModifier"));
    if (e.isXmlAttribute())
      section.triple("fhir:"+tn+"."+en, "fhir:hasFlag", complex().predicate("a", "fhir:isXmlAtribute"));
    if (e.hasMustSupport() && e.isMustSupport())
      section.triple("fhir:"+tn+"."+en, "fhir:hasFlag", complex().predicate("a", "fhir:isMustSupport"));
    if (e.hasSummaryItem() && e.isSummaryItem())
      section.triple("fhir:"+tn+"."+en, "fhir:hasFlag", complex().predicate("a", "fhir:isSummaryItem"));
    if (!Utilities.noString(e.getW5()))
      section.triple("fhir:"+tn+"."+en, "fhir:w5", complex().predicate("a", "fhir:w5\\#"+e.getW5()));
    if (e.hasMeaningWhenMissing())
      section.triple("fhir:"+tn+"."+en, "fhir:missingMeaning", literal(e.getMeaningWhenMissing()));
    
    // cardinality
    cardinality(section, "fhir:"+tn+"."+en, e.getMinCardinality().toString(), e.getMaxCardinality() == Integer.MAX_VALUE ? "*" : e.getMaxCardinality().toString());
//    section.triple("fhir:"+tn+"."+en, "fhir:minCardinality", literal(e.getMinCardinality().toString()));
//    section.triple("fhir:"+tn+"."+en, "fhir:maxCardinality", literal(e.getMaxCardinality() == Integer.MAX_VALUE ? "*" : e.getMaxCardinality().toString()));
    
    // now in OWL:
    if (e.getMinCardinality() > 0)
      section.triple("fhir:"+tn, "rdfs:subClassOf", complex().predicate("a", "owl:Restriction").predicate("owl:onProperty", "fhir:"+tn+"."+en).predicate("owl:minCardinality", literal(e.getMinCardinality().toString()+"^^xs:nonNegativeInteger")));
    if (e.getMaxCardinality() < Integer.MAX_VALUE)
      section.triple("fhir:"+tn, "rdfs:subClassOf", complex().predicate("a", "owl:Restriction").predicate("owl:onProperty", "fhir:"+tn+"."+en).predicate("owl:maxCardinality", literal(e.getMaxCardinality().toString()+"^^xs:nonNegativeInteger")));
        
    // define
    if (tr == null) {
      section.triple("fhir:"+tn+"."+en, "rdfs:range", "fhir:"+e.getDeclaredTypeName());
      anonTypes.push(new AnonTypeInfo(section, e.getDeclaredTypeName(), e, datatype));
    } else if (tr.getName().startsWith("@")) {
      ElementDefn r = getElementForPath(tr.getName().substring(1));
      section.triple("fhir:"+tn+"."+en, "rdfs:range", "fhir:"+r.getDeclaredTypeName());        
    } else {
      if (e.hasBinding()) {
        BindingSpecification bs = e.getBinding();
        if (bs.getValueSet() != null) {
          String bn = getPNameForUri(bs.getValueSet().getUrl());
          if (bs.getStrength() == BindingStrength.REQUIRED && bs.getBinding() == BindingMethod.CodeList && tr.getName().equals("code") && bs.getValueSet().hasDefine())
            section.triple("fhir:"+tn+"."+en, "rdfs:range", getPNameForUri(bs.getValueSet().getDefine().getSystem()));
          else
            section.triple("fhir:"+tn+"."+en, "rdfs:range", processType(tr.getName()));
          section.triple("fhir:"+tn+"."+en, "fhir:binding", bn);
          if (!bn.startsWith("vs:")) // a v3 valueset
          valuesets.put(bn, bs.getValueSet());
        } else if (!Utilities.noString(bs.getReference())) {
          section.triple("fhir:"+tn+"."+en, "rdfs:range", processType(tr.getName()));
          section.triple("fhir:"+tn+"."+en, "fhir:binding", "<"+bs.getReference()+">");
        }
        section.triple("fhir:"+tn+"."+en, "fhir:bindingStrength", complex().predicate("a", "fhir:binding-strength\\#"+bs.getStrength().toCode()));
      } else
        section.triple("fhir:"+tn+"."+en, "rdfs:range", processType(tr.getName()));
    }
    if (e.getDefaultValue() != null) {
      if (e.getDefaultValue() instanceof DecimalType) 
        section.triple("fhir:"+tn+"."+en, "fhir:default", complex().predicate("a", "fhir:decimal").predicate("fhir:value", literal(((DecimalType) e.getDefaultValue()).asStringValue())));
      else if (e.getDefaultValue() instanceof BooleanType) 
        section.triple("fhir:"+tn+"."+en, "fhir:default", complex().predicate("a", "fhir:boolean").predicate("fhir:value", literal(((BooleanType) e.getDefaultValue()).asStringValue())));
      else if (e.getDefaultValue() instanceof IntegerType) 
        section.triple("fhir:"+tn+"."+en, "fhir:default", complex().predicate("a", "fhir:integer").predicate("fhir:value", literal(((IntegerType) e.getDefaultValue()).asStringValue())));
      else
        throw new Error("default of type "+e.getDefaultValue().getClass().getName()+" not handled yet");
    }
  }

  private String processType(String typeCode) {
    if (typeCode.equals("*"))
      return "fhir:Element";
    if (typeCode.equals("xhtml"))
      return "xs:String";
    return "fhir:"+typeCode;
  }
  
  private ElementDefn getElementForPath(String pathname) throws Exception {
    String[] path = pathname.split("\\.");
    ElementDefn res = definitions.getElementDefn(path[0]);
    for (int i = 1; i < path.length; i++)
    {
      String en = path[i];
      if (en.length() == 0)
        throw new Exception("Improper path "+pathname);
      ElementDefn t = res.getElementByName(en);
      if (t == null) {
        throw new Exception("unable to resolve "+pathname);
      }
      res = t; 
    }
    return res;

  }

  private void gen(ResourceDefn rd) throws Exception {
    Section section = section(rd.getName());
    ElementDefn t = rd.getRoot();
    if (t.getTypes().isEmpty())
      section.triple("fhir:"+t.getName(), "a", "rdfs:Class");
    else
      section.triple("fhir:"+t.getName(), "rdfs:subClassOf", processType(t.typeCode()));
    section.comment("fhir:"+t.getName(), rd.getDefinition());
    if (!Utilities.noString(t.getW5()))
      section.triple("fhir:"+t.getName(), "fhir:w5", complex().predicate("a", "fhir:w5\\#"+t.getW5()));
    processMappings(section, "fhir:"+rd.getName(), rd.getRoot());
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        section.triple("fhir:"+t.getName()+"."+cn, "a", "rdf:Property");
        section.comment("fhir:"+t.getName()+"."+cn, e.getDefinition());
        processMappings(section, "fhir:"+t.getName()+"."+cn, e);
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          section.triple("fhir:"+t.getName()+"."+en, "rdfs:subPropertyOf", "fhir:"+t.getName()+"."+cn);
          section.triple("fhir:"+t.getName()+"."+en, "rdfs:domain", "fhir:"+rd.getName());
          genRange(section, t.getName(), en, e, tr, false);
        }
      } else {
        section.triple("fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property");
        section.comment("fhir:"+t.getName()+"."+e.getName(), e.getDefinition());
        section.triple("fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+rd.getName());
        processMappings(section, "fhir:"+t.getName()+"."+e.getName(), e);
        genRange(section, t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), false);
      }
    }
    processAnonTypes();
  }

  private void processMappings(Section section, String subject, ElementDefn e) throws Exception {
    for (String m : e.getMappings().keySet()) {
      if (m.equals("http://hl7.org/orim"))
        section.importTtl(e.getMappings().get(m));
      if (m.equals("http://snomed.info")) {
        System.out.println("sct = "+e.getMappings().get(m));
        throw new Error("Snomed CT mappings are not done for RDF"); // reminder to actually do this // http://snomed.info/id/{sctid}
      }
      if (m.equals("http://loinc.org")) {
        String[] sl = e.getMappings().get(m).split("\\,");
        for (String sp : sl) {
          String s = sp.contains("(") ? sp.substring(0, sp.indexOf("(")).trim() : sp.trim();
          if (!s.contains(" ") && !s.contains("/"))
            section.triple(subject, "fhir:loinc", "loinc:"+s);
        }
      }
    }
  }

  private void processAnonTypes() throws Exception {
    while (!anonTypes.isEmpty())
      genAnon(anonTypes.pop());
  }

  private void genAnon(AnonTypeInfo at) throws Exception {
    if (at.isType())
      at.getSection().triple("fhir:"+at.getName(), "a", "fhir:Element");
    else
      at.getSection().triple("fhir:"+at.getName(), "a", "fhir:BackboneElement");
    at.getSection().comment("fhir:"+at.getName(), at.getDefn().getDefinition());
    processMappings(at.getSection(), "fhir:"+at.getName(), at.getDefn());
    for (ElementDefn e : at.getDefn().getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        at.getSection().triple("fhir:"+at.getName()+"."+cn, "a", "rdf:Property");
        at.getSection().comment("fhir:"+at.getName()+"."+cn, e.getDefinition());
        processMappings(at.getSection(), "fhir:"+at.getName()+"."+cn, e);
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          at.getSection().triple("fhir:"+at.getName()+"."+en, "rdfs:subPropertyOf", "fhir:"+at.getName()+"."+cn); 
          at.getSection().triple("fhir:"+at.getName()+"."+en, "rdfs:domain", "fhir:"+at.getName());
          genRange(at.getSection(), at.getName(), en, e, tr, at.isType());
        }
      } else {
        at.getSection().triple("fhir:"+at.getName()+"."+e.getName(), "a", "rdf:Property");
        at.getSection().comment("fhir:"+at.getName()+"."+e.getName(), e.getDefinition());
        processMappings(at.getSection(), "fhir:"+at.getName()+"."+e.getName(), e);
        at.getSection().triple("fhir:"+at.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+at.getName());
        genRange(at.getSection(), at.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), at.isType());
      }
    }
  }

  
  private void genExtension(StructureDefinition extension) {
    // for now, only simple extensions
    if (extension.getSnapshot().getElement().size() == 5 && !hasSection("Extension: "+tail(extension.getUrl()))) {
      ElementDefinition base = extension.getSnapshot().getElement().get(0);
      ElementDefinition valueX = extension.getSnapshot().getElement().get(4);
      Section section = section("Extension: "+tail(extension.getUrl()));
      Subject subject = section.subject("ex:birthplace");
      subject.predicate("a", "fhir:ExtensionDefinition");
      subject.label(extension.getDisplay());
      subject.comment(extension.getDescription());
      if (extension.hasVersion())
        subject.predicate("fhir:version", extension.getVersion());
      if (extension.hasCopyright()) 
        subject.predicate("dc:rights", literal(extension.getCopyright()));
      subject.predicate("fhir:status", complex().predicate("a", "fhir:conformance-resource-status\\#"+extension.getStatus().toCode()));
      subject.predicate("fhir:canonicalStatus", complex().predicate("a", getCanonicalStatus("ValueSet.status", extension.getStatus().toCode())));
      for (CodeableConcept cc : extension.getUseContext()) 
        codedTriple(subject, "fhir:useContext", cc);
      if (extension.hasDate()) 
        subject.predicate("dc:date", literal(extension.getDateElement().asStringValue()));
      
      subject.predicate("rdfs:range", processType(valueX.getType().get(0).getCode()));
      if (base.getIsModifier())
        subject.predicate("fhir:flag", "fhir:isModifier");
    }    
  }

  private void gen(String bn, ValueSet vs) {
    Section section = section(bn);
    section.triple(bn, "a", "fhir:ValueSet");
    if (vs.hasVersion())
      section.triple(bn, "fhir:version", literal(vs.getVersion()));
    if (vs.hasName())
      section.label(bn, vs.getName());
    if (vs.hasDescription()) 
      section.comment(bn, vs.getDescription().replace("code system", "value set").replace("Code System", "Value Set").replace("Code system", "Value set"));
    if (vs.hasCopyright()) 
      section.triple(bn, "dc:rights", literal(vs.getCopyright()));
    if (vs.hasDate()) 
      section.triple(bn, "dc:date", literal(vs.getDateElement().asStringValue()));
    
    for (CodeableConcept cc : vs.getUseContext()) 
      codedTriple(section, bn, "fhir:useContext", cc);
    section.triple(bn, "fhir:status", complex().predicate("a", "fhir:conformance-resource-status\\#"+vs.getStatus().toCode()));
    section.triple(bn, "fhir:canonicalStatus", complex().predicate("a", getCanonicalStatus("ValueSet.status", vs.getStatus().toCode())));
    if (vs.hasDefine()) {
      section.triple(bn, "fhir:include", gen(section, vs.getDefine(), vs));
    }
  }

  private String gen(Section section, ValueSetDefineComponent define, ValueSet vs) {
    String bn = getPNameForUri(define.getSystem()); 
    if (!bn.startsWith("<")) {
      section.triple(bn+".system", "a", "fhir:CodeSystem");
      if (define.hasVersion())
        section.triple(bn+".system", "fhir:version", literal(define.getVersion()));
      if (vs.hasName())
        section.label(bn+".system", vs.getName());
      if (vs.hasDescription()) 
        section.comment(bn+".system", vs.getDescription().replace("value set", "code system").replace("Value Set", "Code System").replace("Value set", "Code system"));
      if (vs.hasCopyright()) 
        section.triple(bn+".system", "dc:rights", literal(vs.getCopyright()));
      if (vs.hasDate()) 
        section.triple(bn+".system", "dc:date", literal(vs.getDate().toString()));
      
      section.triple(bn, "a", "fhir:Concept");

      gen(section, bn, bn, define.getConcept());
    }
    return bn;
  }

//  private String listTokens(String prefix, ConceptDefinitionComponent root, List<ConceptDefinitionComponent> children) {
//    StringBuilder b = new StringBuilder();
//    if (root != null) {
//      b.append(" ");
//      b.append(prefix);
//      b.append(pctEncode(root.getCode()));
//    }
//    listChildTokens(prefix, children, b);
//    return b.length() == 0 ? "" : b.toString().substring(1);
//  }

//  private void listChildTokens(String prefix, List<ConceptDefinitionComponent> children, StringBuilder b) {
//    for (ConceptDefinitionComponent child : children) {
//      if (!child.getCode().equals("...")) { // special work around for v2 - to be resolved elsewhere
//        b.append(" ");
//        b.append(prefix);
//        b.append(pctEncode(child.getCode()));
//        listChildTokens(prefix, child.getConcept(), b);
//      }
//    }
//  }

  private void gen(Section section, String cs, String owner, List<ConceptDefinitionComponent> concepts) {
    for (ConceptDefinitionComponent c : concepts) {
      if (!c.getCode().equals("...")) { // special work around for v2 - to be resolved elsewhere
        String pcc = pctEncode(c.getCode());
        section.triple(cs+"\\#"+pcc, "rdfs:subClassOf", owner);
        if (c.hasDisplay())
          section.label(cs+"\\#"+pcc, c.getDisplay());
        if (c.hasDefinition())
          section.comment(cs+"\\#"+pcc, c.getDefinition());
        gen(section, cs, cs+"\\#"+pcc, c.getConcept());
      }
    }
  }

  private String getCanonicalStatus(String path, String code) {
    List<String> codes = definitions.getStatusCodes().get(path);
    if (codes == null)
      throw new Error("unknown path "+path);
    int c = -1;
    for (int i = 0; i < codes.size(); i++)
      if (code.equals(codes.get(i)))
        c = i;
    if (c == -1)
      throw new Error("unknown code "+code+" @ path "+path);
    return "fhir:canonical-status\\#"+definitions.getStatusCodes().get("@code").get(c);
  }

  private String getPNameForUri(String url) {
    String  s = null;
//    String s = matches(url, "http://hl7.org/fhir/v2/vs/", "v2-vs");
//    if (s == null)
//      s = matches(url, "http://hl7.org/fhir/v2/", "v2");
    if (s == null)
      s = matches(url, "http://hl7.org/fhir/v3/vs/", "vs");
    if (s == null)
      s = matches(url, "http://hl7.org/fhir/v3/", "cs");
    if (s == null)
      s = matches(url, "http://hl7.org/fhir/vs/", "fhir-vs");
    if (s == null)
      s = matches(url, "http://hl7.org/fhir/", "fhir");
    if (s == null)
      s = matches(url, "http://www.hl7.org/fhir/", "fhir");
    if (s == null)
      s = matches(url, "urn:oid:", "oid");
    if (s != null)
      return s;
    else
      return "<"+url+">";
  }

  protected void codedTriple(Section section, String subject, String predicate, CodeableConcept cc) {
    for (Coding c : cc.getCoding()) {
      String s = getLinkedForm(c);
      if (s != null) 
        section.triple(subject, predicate, s, c.hasDisplay() ? c.getDisplay() : cc.getText());
    }
  }
 
  protected void codedTriple(Subject subject, String predicate, CodeableConcept cc) {
    for (Coding c : cc.getCoding()) {
      String s = getLinkedForm(c);
      if (s != null) 
        subject.predicate(predicate, new StringObject(s), c.hasDisplay() ? c.getDisplay() : cc.getText());
    }
  }
 
  protected String getLinkedForm(Coding c) {
    if (c.hasSystem()) {
      if (c.getSystem().equals("http://loinc.org")) {
        prefixes.put("loinc", "http://loinc.org/");
        return "loinc:"+c.getCode();
      }
    }
    return null;
  }

  protected void chckSubjects() {
    for (String s : sorted(predicateSet)) {
      if (s.startsWith("fhir:") && !subjectSet.contains(s))
        issues.add(new ValidationMessage(Source.Ontology, IssueType.INVALID, -1, -1, "turtle", "Undefined predicate "+s, IssueSeverity.WARNING));
    }
    for (String s : sorted(objectSet)) {
      if (s.startsWith("fhir:") && !subjectSet.contains(s))
        issues.add(new ValidationMessage(Source.Ontology, IssueType.INVALID, -1, -1, "turtle", "Undefined object "+s, IssueSeverity.WARNING));
    }
  }


}


   