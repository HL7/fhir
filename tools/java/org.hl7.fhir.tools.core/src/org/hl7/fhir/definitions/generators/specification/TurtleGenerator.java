package org.hl7.fhir.definitions.generators.specification;

import java.io.IOException;
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

import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.utilities.Utilities;

public class TurtleGenerator {

  private class AnonTypeInfo {
    private String name;
    private ElementDefn defn;
    private boolean type;
    public AnonTypeInfo(String name, ElementDefn defn, boolean type) {
      super();
      this.name = name;
      this.defn = defn;
      this.type = type;
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

  private static final String isa = "http://something/is-a";
  
  private OutputStream destination;
  private OutputStreamWriter writer;
  private Definitions definitions;
  private Deque<AnonTypeInfo> anonTypes = new ArrayDeque<AnonTypeInfo>();
  private String lastSubject;
  private String lastComment;
  private boolean separator;

  public TurtleGenerator(OutputStream destination, Definitions definitions) {
    super();
    this.destination = destination;
    this.definitions = definitions;
  }
  
  public void execute() throws Exception {
    writer = new OutputStreamWriter(destination, "UTF-8");
    bases();
    
//    triple(fhir("FHIR"), isa, none("spec"));
//    ln();
    
    for (String n : sorted(definitions.getPrimitives().keySet())) {
      DefinedCode t = definitions.getPrimitives().get(n);
      if (t instanceof PrimitiveType)
        gen((PrimitiveType) t);
      if (t instanceof DefinedStringPattern)
        gen((DefinedStringPattern) t);
    }
    for (String n : sorted(definitions.getInfrastructure().keySet())) 
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

    checkClose(".");
    writer.flush();
    writer.close();
  }

  private void gen(PrimitiveType t) throws Exception {
    triple(0, "fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:Element", t.getDefinition());
    triple(1, "fhir:"+t.getCode()+".value", "a", "rdf:Property", t.getComment());
    if (t.getSchemaType().endsWith("+")) {
      triple(2, "fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType().substring(0, t.getSchemaType().length()-1), null);
      triple(2, "fhir:"+t.getCode()+".value", "fhir:regex", "\""+t.getRegEx()+"\"", null);
    } else if (t.getSchemaType().contains(",")) {
      triple(2, "fhir:"+t.getCode()+".value", "rdfs:range", "[ a owl:Class; owl:unionOf ("+t.getSchemaType().replace(",", "")+"). ]", "xs:union of "+t.getSchemaType());
    } else
      triple(2, "fhir:"+t.getCode()+".value", "rdfs:range", "xs:"+t.getSchemaType(), null);
    separator = true;
  }

  private void gen(DefinedStringPattern t) throws Exception {
    triple(0, "fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:"+t.getBase(), t.getDefinition());
    if (t.getSchema().endsWith("+")) {
      triple(2, "fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema().substring(0, t.getSchema().length()-1), null);
      triple(2, "fhir:"+t.getCode()+".value", "fhir:regex", "\""+t.getRegex()+"\"", null);
    } else
      triple(2, "fhir:"+t.getCode()+".value", "rdfs:range", t.getSchema(), null);
    separator = true;
  }

  private void gen(TypeDefn t) throws Exception {
    if (t.getTypes().isEmpty())
      triple(0, "fhir:"+t.getName(), "a", "rdfs:Class", t.getDefinition());
    else
      triple(0, "fhir:"+t.getName(), "rdfs:subClassOf", "fhir:Element", t.getDefinition());
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        triple(1, "fhir:"+t.getName()+"."+cn, "a", "fhir:ChoiceGroup", e.getDefinition());
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          triple(1, "fhir:"+t.getName()+"."+en, "a", "rdf:Property", "choice group "+cn+" as a "+tr.getName());
          triple(1, "fhir:"+t.getName()+"."+en, "fhir:inChoiceGroup", "fhir:"+t.getName()+"."+cn, null);
          genRange(t.getName(), en, e, tr, true);
        }
      } else {
        triple(1, "fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property", e.getDefinition());
        genRange(t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), true);
      }
    }
    processAnonTypes();
    separator = true;
  }

  private List<TypeRef> getAnyTypes() {
    List<TypeRef> refs = new ArrayList<TypeRef>();
    for (TypeRef t : definitions.getKnownTypes()) 
      if (!definitions.getInfrastructure().containsKey(t.getName()) && !definitions.getConstraints().containsKey(t.getName())) 
        refs.add(t);
    return refs;
  }
  
  private void genRange(String tn, String en, ElementDefn e, TypeRef tr, boolean datatype) throws Exception {
    if (tr == null) {
      triple(2, "fhir:"+tn+"."+en, "rdfs:range", "fhir:"+e.getDeclaredTypeName(), null);
      anonTypes.push(new AnonTypeInfo(e.getDeclaredTypeName(), e, datatype));
    } else if (tr.getName().startsWith("@")) {
      ElementDefn r = getElementForPath(tr.getName().substring(1));
      triple(2, "fhir:"+tn+"."+en, "rdfs:range", "fhir:"+r.getDeclaredTypeName(), null);        
    } else {
      triple(2, "fhir:"+tn+"."+en, "rdfs:range", "fhir:"+processType(tr.getName()), null);
      // if this is a code with an enumerated value set....
      
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
    ElementDefn t = rd.getRoot();
    if (t.getTypes().isEmpty())
      triple(0, "fhir:"+t.getName(), "a", "rdfs:Class", rd.getDefinition());
    else
      triple(0, "fhir:"+t.getName(), "rdfs:subClassOf", "fhir:"+processType(t.typeCode()), rd.getDefinition());
    for (ElementDefn e : t.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        triple(1, "fhir:"+t.getName()+"."+cn, "a", "fhir:ChoiceGroup", e.getDefinition());
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          triple(1, "fhir:"+t.getName()+"."+en, "a", "rdf:Property", "choice group "+cn+" as a "+tr.getName());
          triple(1, "fhir:"+t.getName()+"."+en, "fhir:inChoiceGroup", "fhir:"+t.getName()+"."+cn, null);
          genRange(t.getName(), en, e, tr, false);
        }
      } else {
        triple(1, "fhir:"+t.getName()+"."+e.getName(), "a", "rdf:Property", e.getDefinition());
        genRange(t.getName(), e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), false);
      }
    }
    processAnonTypes();
    separator = true;
  }

  private void processAnonTypes() throws Exception {
    while (!anonTypes.isEmpty())
      genAnon(anonTypes.pop());
  }

  private void genAnon(AnonTypeInfo at) throws Exception {
    if (at.type)
      triple(1, "fhir:"+at.name, "a", "fhir:Element", at.defn.getDefinition());
    else
      triple(1, "fhir:"+at.name, "a", "fhir:BackboneElement", at.defn.getDefinition());
    for (ElementDefn e : at.defn.getElements()) {
      if (e.getName().endsWith("[x]")) {
        String cn = e.getName().substring(0, e.getName().length()-3);
        triple(1, "fhir:"+at.name+"."+cn, "a", "fhir:ChoiceGroup", e.getDefinition());
        for (TypeRef tr : e.typeCode().equals("*") ? getAnyTypes() : e.getTypes()) {
          String en = cn+Utilities.capitalize(tr.getName());
          triple(1, "fhir:"+at.name+"."+en, "a", "rdf:Property", "choice group "+cn+" as a "+tr.getName());
          triple(1, "fhir:"+at.name+"."+en, "fhir:inChoiceGroup", "fhir:"+at.name+"."+cn, "choice group "+cn+" as a "+tr.getName());
          genRange(at.name, en, e, tr, at.type);
        }
      } else {
        triple(2, "fhir:"+at.name+"."+e.getName(), "a", "rdf:Property", e.getDefinition());
        genRange(at.name, e.getName(), e, e.getTypes().isEmpty() ? null : e.getTypes().get(0), at.type);
      }
    }
  }

  private List<String> sorted(Set<String> keys) {
    List<String> names = new ArrayList<String>();
    names.addAll(keys);
    Collections.sort(names);
    return names;
  }

  private void checkClose(String c) throws Exception {
    if (lastSubject != null) {
      if (Utilities.noString(lastComment))
        ln(c);
      else
        ln(c+" # "+lastComment.replace("\r\n", " ").replace("\r", " ").replace("\n", " "));
      if (c.equals(".")) {
        ln();
        lastSubject = null;
        if (separator) {
          separator = false;
          ln("# -------------------------------------------------------------------------------------");
          ln();
        }
      }
    }
  }
  private void triple(int indent, String subject, String predicate, String object, String comment) throws Exception {
    if (lastSubject != null && lastSubject.equals(subject)) {
      checkClose(";");
      String t = Utilities.padLeft("", ' ', 4);
      writer.write(t+predicate+" "+object);
      lastComment = comment;
    } else {
      checkClose(".");
      String t = Utilities.padLeft("", ' ', indent);
      writer.write(t+subject+" "+predicate+" "+object);
      lastSubject = subject;
      lastComment = comment;
    }
  }

  private void ln() throws Exception {
    writer.write("\r\n");
  }

  private void ln(String s) throws Exception {
    writer.write(s);
    writer.write("\r\n");
  }

  private void bases() throws Exception {
    writer.write("@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns##> .\r\n");
    writer.write("@prefix rdfs: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\r\n");
    writer.write("@prefix fhir: <http://hl7.org/fhir/> .\r\n");
    writer.write("@prefix xs: <http://www.w3.org/2001/XMLSchema#> .\r\n");
    //writer.write("@prefix unk: <http://unknown/> .\r\n");
    ln();
    writer.write("#done; the skeleton. Now, to flesh it out - semantics, expectations, codes etc? \r\n");
    ln();
    ln("# -------------------------------------------------------------------------------------");
    ln();
  }

}


   