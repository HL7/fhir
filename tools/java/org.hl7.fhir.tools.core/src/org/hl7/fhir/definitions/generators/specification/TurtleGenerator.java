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
    
    writer.flush();
    writer.close();
  }

  private void gen(PrimitiveType t) throws Exception {
    triple(0, "fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:Element", t.getDefinition());
    triple(1, "fhir:"+t.getCode()+".value", "a", "fhir:Property", t.getComment());
    triple(2, "fhir:"+t.getCode()+".value", "rdfs:domain", "xs:"+t.getSchemaType(), null);
    ln();
  }

  private void gen(DefinedStringPattern t) throws Exception {
    triple(0, "fhir:"+t.getCode(), "rdfs:subClassOf", "fhir:"+t.getBase(), t.getDefinition());
    triple(2, "fhir:"+t.getCode()+".value", "rdfs:domain", t.getSchema(), null);
    ln();
  }

  private void gen(TypeDefn t) throws Exception {
    if (t.getTypes().isEmpty())
      triple(0, "fhir:"+t.getName(), "a", "rdfs:Class", t.getDefinition());
    else
      triple(0, "fhir:"+t.getName(), "rdfs:subClassOf", "fhir:Element", t.getDefinition());
    for (ElementDefn e : t.getElements()) {
      triple(1, "fhir:"+t.getName()+"."+e.getName(), "a", "fhir:Property", e.getDefinition());
      if (!e.getTypes().isEmpty())
        triple(2, "fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+processType(e.typeCode()), null);
      else {
        triple(2, "fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+e.getDeclaredTypeName(), null);
        anonTypes.push(new AnonTypeInfo(e.getDeclaredTypeName(), e, true));
      }
    }
    ln();   
    processAnonTypes();
  }

  private String processType(String typeCode) {
    if (typeCode.equals("*"))
      return "Element";
    return typeCode;
  }

  private void gen(ResourceDefn rd) throws Exception {
    ElementDefn t = rd.getRoot();
    if (t.getTypes().isEmpty())
      triple(0, "fhir:"+t.getName(), "a", "rdfs:Class", rd.getDefinition());
    else
      triple(0, "fhir:"+t.getName(), "rdfs:subClassOf", "fhir:"+processType(t.typeCode()), rd.getDefinition());
    for (ElementDefn e : t.getElements()) {
      triple(1, "fhir:"+t.getName()+"."+e.getName(), "a", "fhir:Property", e.getDefinition());
      if (!e.getTypes().isEmpty())
        triple(2, "fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+e.typeCode(), null);
      else {
        triple(2, "fhir:"+t.getName()+"."+e.getName(), "rdfs:domain", "fhir:"+e.getDeclaredTypeName(), null);
        anonTypes.push(new AnonTypeInfo(e.getDeclaredTypeName(), e, false));
      }
    }
    ln();   
    processAnonTypes();
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
      triple(2, "fhir:"+at.name+"."+e.getName(), "a", "fhir:Property", e.getDefinition());
      if (!e.getTypes().isEmpty())
        triple(3, "fhir:"+at.name+"."+e.getName(), "rdfs:domain", "fhir:"+processType(e.typeCode()), null);
      else {
        triple(3, "fhir:"+at.name+"."+e.getName(), "rdfs:domain", "fhir:"+e.getDeclaredTypeName(), null);
        anonTypes.push(new AnonTypeInfo(e.getDeclaredTypeName(), e, at.type));
      }
    }
    ln();   
  }

  private List<String> sorted(Set<String> keys) {
    List<String> names = new ArrayList<String>();
    names.addAll(keys);
    Collections.sort(names);
    return names;
  }

  private void triple(int indent, String subject, String predicate, String object, String comment) throws Exception {
    String t = Utilities.padLeft("", ' ', indent);
    if (comment == null)
      writer.write(t+subject+" "+predicate+" "+object+".\r\n");
    else
      writer.write(t+subject+" "+predicate+" "+object+". # "+comment+"\r\n");
  }

  private void ln() throws Exception {
    writer.write("\r\n");
  }

  private void bases() throws Exception {
    writer.write("@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns##> .\r\n");
    writer.write("@prefix rdfs: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\r\n");
    writer.write("@prefix fhir: <http://hl7.org/fhir/> .\r\n");
    writer.write("@prefix xs: <http://www.w3.org/2001/XMLSchema#> .\r\n");
    //writer.write("@prefix unk: <http://unknown/> .\r\n");
    ln();
    writer.write("#todo: what about choice types? \r\n");
    writer.write("#todo: what about schema union types? \r\n");
    ln();
  }

}


   