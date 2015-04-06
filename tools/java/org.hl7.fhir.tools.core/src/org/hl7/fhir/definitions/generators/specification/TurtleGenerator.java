package org.hl7.fhir.definitions.generators.specification;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.utilities.Utilities;

public class TurtleGenerator {

  private class Triple implements Comparable<Triple> {
    private String section;
    private boolean primary;
    private String subject;
    private String predicate;
    private String object;
    private String comment;
    public Triple(String section, boolean primary, String subject, String predicate, String object, String comment) {
      super();
      this.section = section;
      this.primary = primary;
      this.subject = subject;
      this.predicate = predicate;
      this.object = object;
      this.comment = comment;
    }
    public String getSection() {
      return section;
    }
    public String getSubject() {
      return subject;
    }
    public String getPredicate() {
      return predicate;
    }
    public String getObject() {
      return object;
    }
    public String getComment() {
      return comment;
    }
    
    @Override
    public int compareTo(Triple o) {
      Triple other = (Triple) o;
      
      int i = section.compareTo(other.section);
      if (i == 0)
        i = Boolean.compare(!primary, !other.primary);
      if (i == 0)
        i = subject.compareTo(other.subject);
      if (i == 0)
        i = predicate.compareTo(other.predicate);
      if (i == 0)
        i = object.compareTo(other.object);
      return i;
    }
    
  }

  private List<Triple> triples = new ArrayList<Triple>();
  private List<String> sections = new ArrayList<String>();
  
  private class AnonTypeInfo {
    private String name;
    private ElementDefn defn;
    private boolean type;
    private String section;
    public AnonTypeInfo(String section, String name, ElementDefn defn, boolean type) {
      super();
      this.section = section;
      this.name = name;
      this.defn = defn;
      this.type = type;
    }
    public String getSection() {
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

  
  private OutputStream destination;
  private Definitions definitions;
  private Deque<AnonTypeInfo> anonTypes = new ArrayDeque<AnonTypeInfo>();
  private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();
  private Map<String, String> prefixes = new HashMap<String, String>();

  public TurtleGenerator(OutputStream destination, Definitions definitions) {
    super();
    this.destination = destination;
    this.definitions = definitions;
  }
  
  public void execute() throws Exception {
//    triple(fhir("FHIR"), isa, none("spec"));

    prefixes.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns##");
    prefixes.put("rdfs", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    prefixes.put("fhir", "http://hl7.org/fhir/");
    prefixes.put("xs", "http://www.w3.org/2001/XMLSchema#");
    prefixes.put("owl", "http://www.w3.org/2002/07/owl#");
    prefixes.put("dc", "http://purl.org/dc/elements/1.1/");
    prefixes.put("dcterms", "http://purl.org/dc/terms/");
    
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
    gen(definitions.getBaseResources().get("Resource"));
    for (String n : sorted(definitions.getBaseResources().keySet()))
      if (!n.equals("Resource"))
        gen(definitions.getBaseResources().get(n));
    for (String n : sorted(definitions.getResources().keySet())) 
      gen(definitions.getResources().get(n));

    for (String n : sorted(valuesets.keySet()))
      gen(n, valuesets.get(n));

    commit();
  }


  private void genPrimitiveType() {
    sections.add("Primitive");
    primaryTriple("Primitive", "fhir:Primitive", "rdfs:subClassOf", "fhir:Element");
    triple("Primitive", "fhir:Primitive.value", "a", "rdf:Property");
    triple("Primitive", "fhir:Primitive.value", "rdfs:domain", "fhir:Primitive");
    triple("Primitive", "fhir:Primitive.value", "fhir:minCardinality", literal("0"));
    triple("Primitive", "fhir:Primitive.value", "fhir:maxCardinality", literal("1"));
  }

  private void gen(PrimitiveType t) {
    sections.add(t.getCode());
    
    primaryTriple(t.getCode(), "fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:Primitive");
    comment(t.getCode(), "fhir:"+t.getCode(), t.getDefinition());
    triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:subPropertyOf", "fhir:Primitive.value");
    triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:domain", "fhir:"+t.getCode());
    if (t.getSchemaType().endsWith("+")) {
      triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType().substring(0, t.getSchemaType().length()-1));
      triple(t.getCode(), "fhir:"+t.getCode()+".value", "owl:withRestriction", "[ xsd:pattern \""+escape(t.getRegEx())+"\"]");
    } else if (t.getSchemaType().contains(",")) {
      triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:range", "[ a owl:Class; owl:unionOf ("+t.getSchemaType().replace(",", "")+") ]", "xs:union of "+t.getSchemaType());
    } else
      triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType());
  }

  private void gen(DefinedStringPattern t) {
    sections.add(t.getCode());
    primaryTriple(t.getCode(), "fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:"+t.getBase());
    comment(t.getCode(), "fhir:"+t.getCode(), t.getDefinition());
    triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:subPropertyOf", "fhir:"+t.getBase()+".value");
    if (t.getSchema().endsWith("+")) {
      triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema().substring(0, t.getSchema().length()-1));
      triple(t.getCode(), "fhir:"+t.getCode()+".value", "owl:withRestriction", "[ xsd:pattern \""+escape(t.getRegex())+"\"]");
    } else
      triple(t.getCode(), "fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema());
  }

  private void gen(TypeDefn t) throws Exception {
    sections.add(t.getName());
    if (t.getTypes().isEmpty())
      primaryTriple(t.getName(), "fhir:"+t.getName(), "a", "rdfs:Class");
    else
      primaryTriple(t.getName(), "fhir:"+t.getName(), "rdfs:subClassOf", "fhir:Element");
    label(t.getName(), "fhir:"+t.getName(), t.getShortDefn());
    comment(t.getName(), "fhir:"+t.getName(), t.getDefinition());
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        triple(t.getName(), "fhir:"+t.getName()+"."+cn, "a", "fhir:ChoiceGroup");
        label(t.getName(), "fhir:"+t.getName()+"."+cn, e.getShortDefn());
        comment(t.getName(), "fhir:"+t.getName()+"."+cn, e.getDefinition());
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          triple(t.getName(), "fhir:"+t.getName()+"."+en, "a", "rdf:Property", "choice group "+cn+" as a "+tr.getName());
          triple(t.getName(), "fhir:"+t.getName()+"."+en, "rdfs:domain", "fhir:"+t.getName());
          triple(t.getName(), "fhir:"+t.getName()+"."+en, "fhir:inChoiceGroup", "fhir:"+t.getName()+"."+cn);
          genRange(t.getName(), t.getName(), en, e, tr, true);
        }
      } else {
        triple(t.getName(), "fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property");
        comment(t.getName(), "fhir:"+t.getName()+"."+e.getName(), e.getDefinition());
        triple(t.getName(), "fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+t.getName());
        genRange(t.getName(), t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), true);
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
  
  private void genRange(String section, String tn, String en, ElementDefn e, TypeRef tr, boolean datatype) throws Exception {
    // metadata
    if (e.isModifier())
      triple(section, "fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isModifier");
    if (e.isXmlAttribute())
      triple(section, "fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isXmlAtribute");
    if (e.hasMustSupport() && e.isMustSupport())
      triple(section, "fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isMustSupport");
    if (e.hasSummaryItem() && e.isSummaryItem())
      triple(section, "fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isSummaryItem");
    if (!Utilities.noString(e.getW5()))
      triple(section, "fhir:"+tn+"."+en, "fhir:w5", "fhir:w5-"+e.getW5());

    // cardinality
    triple(section, "fhir:"+tn+"."+en, "fhir:minCardinality", literal(e.getMinCardinality().toString()));
    triple(section, "fhir:"+tn+"."+en, "fhir:maxCardinality", literal(e.getMaxCardinality() == Integer.MAX_VALUE ? "*" : e.getMaxCardinality().toString()));
    
    // define
    if (tr == null) {
      triple(section, "fhir:"+tn+"."+en, "rdfs:range", "fhir:"+e.getDeclaredTypeName());
      anonTypes.push(new AnonTypeInfo(section, e.getDeclaredTypeName(), e, datatype));
    } else if (tr.getName().startsWith("@")) {
      ElementDefn r = getElementForPath(tr.getName().substring(1));
      triple(section, "fhir:"+tn+"."+en, "rdfs:range", "fhir:"+r.getDeclaredTypeName());        
    } else {
      triple(section, "fhir:"+tn+"."+en, "rdfs:range", "fhir:"+processType(tr.getName()));
      if (e.hasBinding()) {
        BindingSpecification bs = definitions.getBindingByName(e.getBindingName());
        triple(section, "fhir:"+tn+"."+en, "fhir:bindingStrength", "fhir:BindingStrength#"+bs.getStrength().toCode());
        if (bs.getReferredValueSet() != null) {
          String bn = getPNameForUri(bs.getReferredValueSet().getUrl());
          triple(section, "fhir:"+tn+"."+en, "fhir:binding", bn);
          valuesets.put(bn, bs.getReferredValueSet());
        } else if (!Utilities.noString(bs.getReference()))
          triple(section, "fhir:"+tn+"."+en, "fhir:binding", "<"+bs.getReference()+">");
      }
    }
  }

  private String processType(String typeCode) {
    if (typeCode.equals("*"))
      return "Element";
    return typeCode;
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
    sections.add(rd.getName());
    ElementDefn t = rd.getRoot();
    if (t.getTypes().isEmpty())
      primaryTriple(rd.getName(), "fhir:"+t.getName(), "a", "rdfs:Class");
    else
      primaryTriple(rd.getName(), "fhir:"+t.getName(), "rdfs:subClassOf", "fhir:"+processType(t.typeCode()));
    comment(rd.getName(), "fhir:"+t.getName(), rd.getDefinition());
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        triple(rd.getName(), "fhir:"+t.getName()+"."+cn, "a", "fhir:ChoiceGroup");
        comment(rd.getName(), "fhir:"+t.getName()+"."+cn, e.getDefinition());
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          triple(rd.getName(), "fhir:"+t.getName()+"."+en, "a", "rdf:Property", "choice group "+cn+" as a "+tr.getName());
          triple(rd.getName(), "fhir:"+t.getName()+"."+en, "rdfs:domain", "fhir:"+rd.getName());
          triple(rd.getName(), "fhir:"+t.getName()+"."+en, "fhir:inChoiceGroup", "fhir:"+t.getName()+"."+cn);
          genRange(rd.getName(), t.getName(), en, e, tr, false);
        }
      } else {
        triple(rd.getName(), "fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property");
        comment(rd.getName(), "fhir:"+t.getName()+"."+e.getName(), e.getDefinition());
        triple(rd.getName(), "fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+rd.getName());
        genRange(rd.getName(), t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), false);
      }
    }
    processAnonTypes();
  }

  private void processAnonTypes() throws Exception {
    while (!anonTypes.isEmpty())
      genAnon(anonTypes.pop());
  }

  private void genAnon(AnonTypeInfo at) throws Exception {
    if (at.isType())
      triple(at.getSection(), "fhir:"+at.getName(), "a", "fhir:Element");
    else
      triple(at.getSection(), "fhir:"+at.getName(), "a", "fhir:BackboneElement");
    comment(at.getSection(), "fhir:"+at.getName(), at.getDefn().getDefinition());
    for (ElementDefn e : at.getDefn().getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        triple(at.getSection(), "fhir:"+at.getName()+"."+cn, "a", "fhir:ChoiceGroup");
        comment(at.getSection(), "fhir:"+at.getName()+"."+cn, e.getDefinition());
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          triple(at.getSection(), "fhir:"+at.getName()+"."+en, "a", "rdf:Property"); //  "choice group "+cn+" as a "+tr.getName());
          triple(at.getSection(), "fhir:"+at.getName()+"."+en, "rdfs:domain", "fhir:"+at.getName());
          triple(at.getSection(), "fhir:"+at.getName()+"."+en, "fhir:inChoiceGroup", "fhir:"+at.getName()+"."+cn);
          genRange(at.getSection(), at.getName(), en, e, tr, at.isType());
        }
      } else {
        triple(at.getSection(), "fhir:"+at.getName()+"."+e.getName(), "a", "rdf:Property");
        comment(at.getSection(), "fhir:"+at.getName()+"."+e.getName(), e.getDefinition());
        triple(at.getSection(), "fhir:"+at.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+at.getName());
        genRange(at.getSection(), at.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), at.isType());
      }
    }
  }

  private void gen(String bn, ValueSet vs) {
    sections.add(bn);
    primaryTriple(bn, bn, "a", "fhir:ValueSet");
    if (vs.hasVersion())
      triple(bn, bn, "fhir:version", literal(vs.getVersion()));
    if (vs.hasName())
      label(bn, bn, vs.getName());
    if (vs.hasDescription()) 
      comment(bn, bn, vs.getDescription());
    if (vs.hasCopyright()) 
      triple(bn, bn, "dc:rights", literal(vs.getCopyright()));
    if (vs.hasDate()) 
      triple(bn, bn, "dc:date", literal(vs.getDate().toString()));
    
    for (CodeableConcept cc : vs.getUseContext()) 
      codedTriple(bn, bn, "fhir:useContext", cc);
    triple(bn, bn, "fhir:status", "fhir:conformance-resource-status#"+vs.getStatus().toCode());
    triple(bn, bn, "fhir:canonical-status", getCanonicalStatus("ValueSet.status", vs.getStatus().toCode()));
    if (vs.hasDefine()) {
      triple(bn, bn, "fhir:include", gen(bn, vs.getDefine()));
    }
  }

  private String gen(String section, ValueSetDefineComponent define) {
    String bn = getPNameForUri(define.getSystem()); 
    triple(section, bn, "a", "fhir:CodeSystem");
    if (define.hasVersion())
      triple(section, bn, "fhir:version", literal(define.getVersion()));
    gen(section, bn, bn, define.getConcept());
    return bn;
  }

  private void gen(String section, String cs, String owner, List<ConceptDefinitionComponent> concepts) {
    for (ConceptDefinitionComponent c : concepts) {
      triple(section, cs+"#"+c.getCode(), "a", "fhir:concept");
      if (c.hasDisplay())
        label(section, cs+"#"+c.getCode(), c.getDisplay());
      if (c.hasDefinition())
        comment(section, cs+"#"+c.getCode(), c.getDisplay());
      triple(section, cs+"#"+c.getCode(), "fhir:memberOf", owner);
      gen(section, cs, cs+"#"+c.getCode(), c.getConcept());
    }
  }

  private List<String> sorted(Set<String> keys) {
    List<String> names = new ArrayList<String>();
    names.addAll(keys);
    Collections.sort(names);
    return names;
  }

  private void codedTriple(String section, String subject, String predicate, CodeableConcept cc) {
    for (Coding c : cc.getCoding()) {
      String s = getLinkedForm(c);
      if (s != null) 
        triple(section, subject, predicate, s, c.hasDisplay() ? c.getDisplay() : cc.getText());
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
    return "fhir:canonical-status-codes#"+definitions.getStatusCodes().get("@code").get(c);
  }

  private String getLinkedForm(Coding c) {
    if (c.hasSystem()) {
      if (c.getSystem().equals("http://loinc.org")) {
        prefixes.put("loinc", "http://loinc.org/");
        return "loinc:"+c.getCode();
      }
    }
    return null;
  }

  private String getPNameForUri(String url) {
//    String s = matches(url, "http://hl7.org/fhir/v2/vs/", "v2-vs");
//    if (s == null)
//      s = matches(url, "http://hl7.org/fhir/v2/", "v2");
//    if (s == null)
//      s = matches(url, "http://hl7.org/fhir/v3/vs/", "v3-vs");
//    if (s == null)
//      s = matches(url, "http://hl7.org/fhir/v3/", "v3");
//    if (s == null)
//      s = matches(url, "http://hl7.org/fhir/vs/", "fhir-vs");
    String  s = matches(url, "http://hl7.org/fhir/", "fhir");
    if (s == null)
      s = matches(url, "http://www.hl7.org/fhir/", "fhir");
    if (s == null)
      s = matches(url, "urn:oid:", "oid");
    if (s != null)
      return s;
    else
      return url;
  }


  private String matches(String url, String prefixUri, String prefix) {
    if (url.startsWith(prefixUri)) {
      prefixes.put(prefix, prefixUri);
      return prefix+":"+escape(url.substring(prefixUri.length()));
    }
    return null;
  }

  private void comment(String section, String subject, String comment) {
    triple(section, false, subject, "rdfs:comment", literal(comment), null);
    triple(section, false, subject, "dc:terms", literal(comment), null);
  }
  
  private void label(String section, String subject, String comment) {
    triple(section, false, subject, "rdfs:label", literal(comment), null);
    triple(section, false, subject, "dc:title", literal(comment), null);
  }
  
  private void triple(String section, String subject, String predicate, String object) {
    triple(section, false, subject, predicate, object, null);
  }
  
  private void triple(String section, String subject, String predicate, String object, String comment) {
    triple(section, false, subject, predicate, object, comment);
  }
  
  private void primaryTriple(String section, String subject, String predicate, String object) {
    triple(section, true, subject, predicate, object, null);
  }
  private void triple(String section, boolean primary, String subject, String predicate, String object, String comment) {
    if (!sections.contains(section))
      throw new Error("use undefined section "+section);
    checkPrefix(subject);
    checkPrefix(predicate);
    checkPrefix(object);
    triples.add(new Triple(section, primary, subject, predicate, object, comment == null ? "" : " # "+comment.replace("\r\n", " ").replace("\r", " ").replace("\n", " ")));
  }
  
  private void checkPrefix(String pname) {
    if (pname.startsWith("["))
      return;
    if (pname.startsWith("\""))
      return;
    if (pname.startsWith("<"))
      return;
    
    if (pname.contains(":")) {
      String prefix = pname.substring(0, pname.indexOf(":"));
      if (!prefixes.containsKey(prefix) && !prefix.equals("http")&& !prefix.equals("urn"))
        throw new Error("undefined prefix "+prefix); 
    }
  }

  private String literal(String s) {
    return "\""+escape(s)+"\"";
  }

  private String escape(String s) {
    if (s == null)
      return "";

    StringBuilder b = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == '\r')
        b.append("\\r");
      else if (c == '\n')
        b.append("\\n");
      else if (c == '"')
        b.append("\"");
      else if (c == '\\')
        b.append("\\\\");
      else if (c == '/')
        b.append("\\/");
      else 
        b.append(c);
    }   
    return b.toString();
  }

  private class LineOutputStreamWriter extends OutputStreamWriter {
    private LineOutputStreamWriter(OutputStream out) throws UnsupportedEncodingException {
      super(out, "UTF-8");
    }

    private void ln() throws Exception {
      write("\r\n");
    }

    private void ln(String s) throws Exception {
      write(s);
      write("\r\n");
    }

  }


  private void commit() throws Exception {
    LineOutputStreamWriter writer = new LineOutputStreamWriter(destination);
    commitPrefixes(writer);
    for (String s : sections) {
      commitSection(writer, s);
    }
    writer.ln("# -------------------------------------------------------------------------------------");
    writer.ln();
    writer.flush();
    writer.close();
  }
 
  private void commitPrefixes(LineOutputStreamWriter writer) throws Exception {
    writer.ln("# FHIR definitions");
    writer.write("# This is work in progress, and may change rapidly \r\n");
    writer.ln();
    for (String p : sorted(prefixes.keySet()))
      writer.ln("@prefix "+p+": <"+prefixes.get(p)+"> .");
    writer.ln();
 }

  private void commitSection(LineOutputStreamWriter writer, String section) throws Exception {
    List<Triple> sectlist = new ArrayList<Triple>();
    for (Triple t : triples)
      if (t.getSection().equals(section))
        sectlist.add(t);
    Collections.sort(sectlist);
    writer.ln("# - "+section+" "+Utilities.padLeft("", '-', 75-section.length()));
    writer.ln();
    String lastSubject = null;
    String lastComment = "";
    for (Triple t : sectlist) {
      boolean follow = false;
      if (lastSubject != null) {
        follow = lastSubject.equals(t.getSubject());
        String c = follow ? ";" : ".";
        writer.ln(c+lastComment);
        if (!follow) 
          writer.ln();
      }
      String left = follow ? Utilities.padLeft("", ' ', 2) : t.getSubject();
      writer.write(left+" "+t.getPredicate()+" "+t.getObject());
      lastComment = t.getComment();
      lastSubject = t.getSubject();
    }
    writer.ln("."+lastComment);
    writer.ln();
  }


}


   