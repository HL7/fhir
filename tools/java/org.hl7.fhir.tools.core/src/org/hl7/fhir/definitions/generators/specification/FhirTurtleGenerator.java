package org.hl7.fhir.definitions.generators.specification;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ElementDefinition.BindingStrength;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
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
  private Deque<AnonTypeInfo> anonTypes = new ArrayDeque<AnonTypeInfo>();
  private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();

  public FhirTurtleGenerator(OutputStream destination, Definitions definitions) {
    super(destination);
    this.definitions = definitions;
  }
  
  public void execute() throws Exception {
//    triple(fhir("FHIR"), isa, none("spec"));

    prefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    prefix("rdfs", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    prefix("fhir", "http://hl7.org/fhir/");
    prefix("xs", "http://www.w3.org/2001/XMLSchema#");
    prefix("owl", "http://www.w3.org/2002/07/owl#");
    prefix("dc", "http://purl.org/dc/elements/1.1/");
    prefix("dcterms", "http://purl.org/dc/terms/");
    prefix("rim", "http://hl7.org/owl/rim/");
    prefix("cs", "http://hl7.org/orim/codesystem/");
    prefix("vs", "http://hl7.org/orim/valueset/");
    prefix("dt", "http://hl7.org/orim/datatype/");
    
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
    
    valuesets.put("vs:NullFlavor", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/NullFlavor"));
    valuesets.put("vs:EntityClass", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/EntityClass"));
    valuesets.put("vs:EntityDeterminer", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/EntityDeterminer"));
    valuesets.put("vs:EntityStatus", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/EntityStatus"));
    valuesets.put("vs:IdentifierScope", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/IdentifierScope"));
    valuesets.put("vs:IdentifierReliability", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/IdentifierReliability"));
    valuesets.put("vs:EntityCode", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/EntityCode"));
    valuesets.put("vs:CodingRationale", definitions.getValuesets().get("http://hl7.org/fhir/v3/vs/CodingRationale"));
    
    
    for (String n : sorted(valuesets.keySet()))
      gen(n, valuesets.get(n));

    commit();
//    throw new Error("bang");
  }


  private void genPrimitiveType() {
    Section sct = section("Primitive");
    sct.triple("fhir:Primitive", "rdfs:subClassOf", "fhir:Element");
    sct.triple("fhir:Primitive.value", "a", "rdf:Property");
    sct.triple("fhir:Primitive.value", "rdfs:domain", "fhir:Primitive");
    sct.triple("fhir:Primitive.value", "fhir:minCardinality", literal("0"));
    sct.triple("fhir:Primitive.value", "fhir:maxCardinality", literal("1"));
  }

  private void gen(PrimitiveType t) {
    Section sct = section(t.getCode());
    sct.triple("fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:Primitive");
    sct.comment("fhir:"+t.getCode(), t.getDefinition());
    sct.triple("fhir:"+t.getCode()+".value", "rdfs:subPropertyOf", "fhir:Primitive.value");
    sct.triple("fhir:"+t.getCode()+".value", "rdfs:domain", "fhir:"+t.getCode());
    if (t.getSchemaType().endsWith("+")) {
      sct.triple("fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType().substring(0, t.getSchemaType().length()-1));
      sct.triple("fhir:"+t.getCode()+".value", "owl:withRestriction", complex().predicate("xs:pattern", literal(t.getRegEx())));
    } else if (t.getSchemaType().contains(",")) {
      sct.triple("fhir:"+t.getCode()+".value", "rdfs:range", complex().predicate("a", "owl:Class").predicate("owl:unionOf", "("+t.getSchemaType().replace(",", "")+")"), "xs:union of "+t.getSchemaType());
    } else
      sct.triple("fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType());
  }

  private void gen(DefinedStringPattern t) {
    Section sct = section(t.getCode());
    sct.triple("fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:"+t.getBase());
    sct.comment("fhir:"+t.getCode(), t.getDefinition());
    sct.triple("fhir:"+t.getCode()+".value", "rdfs:subPropertyOf", "fhir:"+t.getBase()+".value");
    if (t.getSchema().endsWith("+")) {
      sct.triple("fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema().substring(0, t.getSchema().length()-1));
      sct.triple("fhir:"+t.getCode()+".value", "owl:withRestriction", complex().predicate("xs:pattern", literal(t.getRegex())));
    } else
      sct.triple("fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema());
  }

  private void gen(TypeDefn t) throws Exception {
    Section sct = section(t.getName());
    if (t.getTypes().isEmpty())
      sct.triple("fhir:"+t.getName(), "a", "rdfs:Class");
    else
      sct.triple("fhir:"+t.getName(), "rdfs:subClassOf", "fhir:Element");
    sct.label("fhir:"+t.getName(), t.getShortDefn());
    sct.comment("fhir:"+t.getName(), t.getDefinition());
    sct.importTtl(getORimMapping(t));
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        sct.triple("fhir:"+t.getName()+"."+cn, "a", "rdf:Property");
        sct.label("fhir:"+t.getName()+"."+cn, e.getShortDefn());
        sct.comment("fhir:"+t.getName()+"."+cn, e.getDefinition());
        sct.importTtl(getORimMapping(e));
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          sct.triple("fhir:"+t.getName()+"."+en, "rdfs:subPropertyOf", "fhir:"+t.getName()+"."+cn);
          sct.triple("fhir:"+t.getName()+"."+en, "rdfs:domain", "fhir:"+t.getName());
          genRange(sct, t.getName(), en, e, tr, true);
        }
      } else {
        sct.triple("fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property");
        sct.comment("fhir:"+t.getName()+"."+e.getName(), e.getDefinition());
        sct.triple("fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+t.getName());
        sct.importTtl(getORimMapping(e));
        genRange(sct, t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), true);
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
  
  private void genRange(Section sct, String tn, String en, ElementDefn e, TypeRef tr, boolean datatype) throws Exception {
    // metadata
    if (e.isModifier())
      sct.triple("fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isModifier");
    if (e.isXmlAttribute())
      sct.triple("fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isXmlAtribute");
    if (e.hasMustSupport() && e.isMustSupport())
      sct.triple("fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isMustSupport");
    if (e.hasSummaryItem() && e.isSummaryItem())
      sct.triple("fhir:"+tn+"."+en, "fhir:hasFlag", "fhir:isSummaryItem");
    if (!Utilities.noString(e.getW5()))
      sct.triple("fhir:"+tn+"."+en, "fhir:w5", "fhir:w5-"+e.getW5());

    // cardinality
    sct.triple("fhir:"+tn+"."+en, "fhir:minCardinality", literal(e.getMinCardinality().toString()));
    sct.triple("fhir:"+tn+"."+en, "fhir:maxCardinality", literal(e.getMaxCardinality() == Integer.MAX_VALUE ? "*" : e.getMaxCardinality().toString()));
    // now in OWL:
    if (e.getMinCardinality() > 0)
      sct.triple("fhir:"+tn, "rdfs:subClassOf", complex().predicate("a", "owl:Restriction").predicate("owl:onProperty", "fhir:"+tn+"."+en).predicate("owl:minCardinality", literal(e.getMinCardinality().toString()+"^^xs:nonNegativeInteger")));
    if (e.getMaxCardinality() < Integer.MAX_VALUE)
      sct.triple("fhir:"+tn, "rdfs:subClassOf", complex().predicate("a", "owl:Restriction").predicate("owl:onProperty", "fhir:"+tn+"."+en).predicate("owl:maxCardinality", literal(e.getMaxCardinality().toString()+"^^xs:nonNegativeInteger")));
        
    // define
    if (tr == null) {
      sct.triple("fhir:"+tn+"."+en, "rdfs:range", "fhir:"+e.getDeclaredTypeName());
      anonTypes.push(new AnonTypeInfo(sct, e.getDeclaredTypeName(), e, datatype));
    } else if (tr.getName().startsWith("@")) {
      ElementDefn r = getElementForPath(tr.getName().substring(1));
      sct.triple("fhir:"+tn+"."+en, "rdfs:range", "fhir:"+r.getDeclaredTypeName());        
    } else {
      if (e.hasBinding()) {
        BindingSpecification bs = definitions.getBindingByName(e.getBindingName());
        if (bs.getReferredValueSet() != null) {
          String bn = getPNameForUri(bs.getReferredValueSet().getUrl());
          if (bs.getStrength() == BindingStrength.REQUIRED && bs.getBinding() == Binding.CodeList && tr.getName().equals("code"))
            sct.triple("fhir:"+tn+"."+en, "rdfs:range", bn+".class");
          else
            sct.triple("fhir:"+tn+"."+en, "rdfs:range", "fhir:"+processType(tr.getName()));
          sct.triple("fhir:"+tn+"."+en, "fhir:binding", bn);
          valuesets.put(bn, bs.getReferredValueSet());
        } else if (!Utilities.noString(bs.getReference())) {
          sct.triple("fhir:"+tn+"."+en, "rdfs:range", "fhir:"+processType(tr.getName()));
          sct.triple("fhir:"+tn+"."+en, "fhir:binding", "<"+bs.getReference()+">");
        }
        sct.triple("fhir:"+tn+"."+en, "fhir:bindingStrength", "fhir:BindingStrength\\#"+bs.getStrength().toCode());
      } else
        sct.triple("fhir:"+tn+"."+en, "rdfs:range", "fhir:"+processType(tr.getName()));
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
    Section sct = section(rd.getName());
    ElementDefn t = rd.getRoot();
    if (t.getTypes().isEmpty())
      sct.triple("fhir:"+t.getName(), "a", "rdfs:Class");
    else
      sct.triple("fhir:"+t.getName(), "rdfs:subClassOf", "fhir:"+processType(t.typeCode()));
    sct.comment("fhir:"+t.getName(), rd.getDefinition());
    sct.importTtl(getORimMapping(rd.getRoot()));
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        sct.triple("fhir:"+t.getName()+"."+cn, "a", "rdf:Property");
        sct.comment("fhir:"+t.getName()+"."+cn, e.getDefinition());
        sct.importTtl(getORimMapping(e));
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          sct.triple("fhir:"+t.getName()+"."+en, "rdf:subPropertyOf", "fhir:"+t.getName()+"."+cn);
          sct.triple("fhir:"+t.getName()+"."+en, "rdfs:domain", "fhir:"+rd.getName());
          genRange(sct, t.getName(), en, e, tr, false);
        }
      } else {
        sct.triple("fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property");
        sct.comment("fhir:"+t.getName()+"."+e.getName(), e.getDefinition());
        sct.triple("fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+rd.getName());
        sct.importTtl(getORimMapping(e));
        genRange(sct, t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), false);
      }
    }
    processAnonTypes();
  }

  private String getORimMapping(ElementDefn e) {
    for (String m : e.getMappings().keySet()) {
      if (m.equals("http://hl7.org/orim"))
        return e.getMappings().get(m);
    }
    return null;
  }

  private String getRimMapping(ElementDefn root) {
    if (root.getMappings().containsKey(Definitions.RIM_MAPPING)) {
      String res = root.getMappings().get(Definitions.RIM_MAPPING);
      if (isSimpleRIM(res))
        return res;
    } 
    return null;
  }

  private boolean isSimpleRIM(String res) {
    
    return false;
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
    for (ElementDefn e : at.getDefn().getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        at.getSection().triple("fhir:"+at.getName()+"."+cn, "a", "rdf:Property");
        at.getSection().comment("fhir:"+at.getName()+"."+cn, e.getDefinition());
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          at.getSection().triple("fhir:"+at.getName()+"."+en, "rdf:subPropertyOf", "fhir:"+at.getName()+"."+cn); 
          at.getSection().triple("fhir:"+at.getName()+"."+en, "rdfs:domain", "fhir:"+at.getName());
          genRange(at.getSection(), at.getName(), en, e, tr, at.isType());
        }
      } else {
        at.getSection().triple("fhir:"+at.getName()+"."+e.getName(), "a", "rdf:Property");
        at.getSection().comment("fhir:"+at.getName()+"."+e.getName(), e.getDefinition());
        at.getSection().triple("fhir:"+at.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+at.getName());
        genRange(at.getSection(), at.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), at.isType());
      }
    }
  }

  private void gen(String bn, ValueSet vs) {
    Section sct = section(bn);
    sct.triple(bn, "a", "fhir:ValueSet");
    if (vs.hasVersion())
      sct.triple(bn, "fhir:version", literal(vs.getVersion()));
    if (vs.hasName())
      sct.label(bn, vs.getName());
    if (vs.hasDescription()) 
      sct.comment(bn, vs.getDescription().replace("code system", "value set").replace("Code System", "Value Set").replace("Code system", "Value set"));
    if (vs.hasCopyright()) 
      sct.triple(bn, "dc:rights", literal(vs.getCopyright()));
    if (vs.hasDate()) 
      sct.triple(bn, "dc:date", literal(vs.getDateElement().asStringValue()));
    
    for (CodeableConcept cc : vs.getUseContext()) 
      codedTriple(sct, bn, "fhir:useContext", cc);
    sct.triple(bn, "fhir:status", "fhir:conformance-resource-status\\#"+vs.getStatus().toCode());
    sct.triple(bn, "fhir:canonical-status", getCanonicalStatus("ValueSet.status", vs.getStatus().toCode()));
    if (vs.hasDefine()) {
      sct.triple(bn, "fhir:include", gen(sct, vs.getDefine(), vs));
    }
  }

  private String gen(Section sct, ValueSetDefineComponent define, ValueSet vs) {
    String bn = getPNameForUri(define.getSystem()); 
    if (!bn.startsWith("<")) {
      sct.triple(bn, "a", "fhir:CodeSystem");
      if (define.hasVersion())
        sct.triple(bn, "fhir:version", literal(define.getVersion()));
      if (vs.hasName())
        sct.label(bn, vs.getName());
      if (vs.hasDescription()) 
        sct.comment(bn, vs.getDescription().replace("value set", "code system").replace("Value Set", "Code System").replace("Value set", "Code system"));
      if (vs.hasCopyright()) 
        sct.triple(bn, "dc:rights", literal(vs.getCopyright()));
      if (vs.hasDate()) 
        sct.triple(bn, "dc:date", literal(vs.getDate().toString()));
      
      sct.triple(bn+".class", "a", "fhir:Concept");
      sct.triple(bn+".class", "owl:oneOf", "("+listTokens(bn+"\\#", null, define.getConcept())+")");

      gen(sct, bn, bn+".class", define.getConcept());
    }
    return bn;
  }

  private String listTokens(String prefix, ConceptDefinitionComponent root, List<ConceptDefinitionComponent> children) {
    StringBuilder b = new StringBuilder();
    if (root != null) {
      b.append(" ");
      b.append(prefix);
      b.append(pctEncode(root.getCode()));
    }
    listChildTokens(prefix, children, b);
    return b.toString().substring(1);
  }

  private void listChildTokens(String prefix, List<ConceptDefinitionComponent> children, StringBuilder b) {
    for (ConceptDefinitionComponent child : children) {
      b.append(" ");
      b.append(prefix);
      b.append(pctEncode(child.getCode()));
      listChildTokens(prefix, child.getConcept(), b);
    }
  }

  private void gen(Section sct, String cs, String owner, List<ConceptDefinitionComponent> concepts) {
    for (ConceptDefinitionComponent c : concepts) {
      String pcc = pctEncode(c.getCode());
      sct.triple(cs+".class\\#"+pcc, "rdfs:subClassOf", owner);
      sct.triple(cs+".class\\#"+pcc, "owl:oneOf", "("+listTokens(cs+"\\#", c, c.getConcept())+")");
      if (c.hasDisplay())
        sct.label(cs+".class\\#"+pcc, c.getDisplay());
      if (c.hasDefinition())
        sct.comment(cs+".class\\#"+pcc, c.getDefinition());
      sct.triple(cs+"\\#"+pcc, "a", cs+".class\\#"+pcc);
      gen(sct, cs, cs+".class\\#"+pcc, c.getConcept());
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
    return "fhir:canonical-status-codes\\#"+definitions.getStatusCodes().get("@code").get(c);
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
//    if (s == null)
//      s = matches(url, "http://hl7.org/fhir/vs/", "fhir-vs");
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
 
  protected String getLinkedForm(Coding c) {
    if (c.hasSystem()) {
      if (c.getSystem().equals("http://loinc.org")) {
        prefixes.put("loinc", "http://loinc.org/");
        return "loinc:"+c.getCode();
      }
    }
    return null;
  }

}


   