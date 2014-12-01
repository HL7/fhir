package org.hl7.fhir.tools.implementations.java;
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
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.tools.implementations.java.JavaParserXmlGenerator.JavaGenClass;
import org.hl7.fhir.utilities.Utilities;

public class JavaParserJsonGenerator extends JavaBaseGenerator {
  public enum JavaGenClass { Structure, Type, Resource, AbstractResource, Constraint, Backbone }

  private Definitions definitions;
  private Map<ElementDefn, String> typeNames = new HashMap<ElementDefn, String>();
  private List<String> typeNameStrings = new ArrayList<String>();
  private List<ElementDefn> enums = new ArrayList<ElementDefn>();
  private List<String> enumNames = new ArrayList<String>();
  private List<ElementDefn> strucs  = new ArrayList<ElementDefn>();
  //  private List<String> lists = new ArrayList<String>();

  private String context;

  private StringBuilder reg = new StringBuilder();
  private StringBuilder regt = new StringBuilder();
  private StringBuilder regt2 = new StringBuilder();
  private StringBuilder regf = new StringBuilder();
  private StringBuilder regn = new StringBuilder();
  private StringBuilder regtn = new StringBuilder();
  private StringBuilder regtp = new StringBuilder();
  private StringBuilder regti = new StringBuilder();
  private String genparam;

  public JavaParserJsonGenerator(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }

  public void generateParser(Definitions definitions, String version, Date genDate) throws Exception {

    this.definitions = definitions;

    start(version, genDate);

    genElementParser();
    generateEnumParser();

    for (DefinedCode dc : definitions.getPrimitives().values()) 
      generatePrimitiveParser(dc);

    for (ElementDefn n : definitions.getInfrastructure().values()) {
      if (!n.getName().equals("Element") && !n.getName().equals("BackboneElement")) {
        generateParser(n, JavaGenClass.Structure);
        String t = upFirst(n.getName());
        //      regt.append("    else if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return parse"+t+"(xpp);\r\n");
        //    regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
        regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json);\r\n");
      }
    }

    for (ElementDefn n : definitions.getTypes().values()) {
      generateParser(n, JavaGenClass.Type);
      regt.append("    else if (json.has(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json.getAsJsonObject(prefix+\""+n.getName()+"\"));\r\n");
      regt2.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json);\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    }

    for (ProfiledType n : definitions.getConstraints().values()) {
      generateConstraintParser(n);
      regt.append("    else if (json.has(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json.getAsJsonObject(prefix+\""+n.getName()+"\"));\r\n");
      regt2.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json);\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    }
    for (ElementDefn n : definitions.getStructures().values()) {
      generateParser(n, JavaGenClass.Structure);
      regt.append("    else if (json.has(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json.getAsJsonObject(prefix+\""+n.getName()+"\"));\r\n");
      regt2.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json);\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    }

    for (String s : definitions.getBaseResources().keySet()) {
      ResourceDefn n = definitions.getBaseResources().get(s);
      generateParser(n.getRoot(), n.isAbstract() ? JavaGenClass.AbstractResource : JavaGenClass.Resource);
      if (!n.isAbstract()) {
        reg.append("    else if (t.equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(json);\r\n");
        regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(xpp);\r\n");
        regn.append("    if (json.has(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
      }
    }

    for (String s : definitions.sortedResourceNames()) {
      ResourceDefn n = definitions.getResources().get(s);
      generateParser(n.getRoot(), JavaGenClass.Resource);
      reg.append("    else if (t.equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(json);\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    }

    for (DefinedCode cd : definitions.getPrimitives().values()) {
      String n = upFirst(cd.getCode());
      String t = n;
      regt.append("    else if (json.has(prefix+\""+n+"\") || json.has(\"_\"+prefix+\""+n+"\")) {\r\n");
      regt.append("      Type t = json.has(prefix+\""+n+"\") ? parse"+t+"(json.get(prefix+\""+n+"\").getAs"+getAsJsonPrimitive(cd.getCode(), true)+"()) : new "+getPrimitiveTypeModelName(cd.getCode())+"();\r\n");
      regt.append("      if (json.has(\"_\"+prefix+\""+n+"\"))\r\n");
      regt.append("        parseElementProperties(json.getAsJsonObject(\"_\"+prefix+\""+n+"\"), t);\r\n");
      regt.append("      return t;\r\n");
      regt.append("    }\r\n");

      regf.append("    else if (type.equals(\""+cd.getCode()+"\"))\r\n");
      regf.append("      return parse"+t+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+n+"\") || json.has(\"_\"+prefix+\""+n+"\"))\r\n      return true;\r\n");
    }

    finishParser();
  }

  private void genElementParser() throws Exception {
    write("  protected void parseElementProperties(JsonObject json, Element element) throws Exception {\r\n");
    write("    super.parseElementProperties(json, element);\r\n");
    write("    parseExtensions(json, element.getExtension(), false);\r\n");
    write("  }\r\n");
    write("\r\n");
    write("  protected void parseBackboneProperties(JsonObject json, BackboneElement element) throws Exception {\r\n");
    write("    parseElementProperties(json, element);\r\n");
    write("    if (json != null && json.has(\"modifier\")) {\r\n");
    write("      JsonObject obj = json.getAsJsonObject(\"modifier\");\r\n");
    write("      parseExtensions(obj, element.getModifierExtension(), false);\r\n");
    write("    };\r\n");    
    write("  }\r\n");
    write("\r\n");
    write("  protected void parseTypeProperties(JsonObject json, Element element) throws Exception {\r\n");
    write("    parseElementProperties(json, element);\r\n");
    write("  }\r\n");
    write("\r\n");
  }

  private void generateEnumParser() throws Exception {
    write("  @SuppressWarnings(\"unchecked\")\r\n");
    write("  protected <E extends Enum<E>> Enumeration<E> parseEnumeration(String s, E item, EnumFactory e) throws Exception {\r\n");
    write("    Enumeration<E> res = new Enumeration<E>();\r\n");
    //    write("    parseElementProperties(json, res);\r\n");
    write("    if (s != null)\r\n");
    write("      res.setValue((E) e.fromCode(s));\r\n");
    write("    return res;\r\n");
    write("  }\r\n");
    write("\r\n");
  }

  private void generatePrimitiveParser(DefinedCode dc) throws Exception {
    String tn = getPrimitiveTypeModelName(dc.getCode());
    String jpn = getAsJsonPrimitive(dc.getCode(), false);
    write("  protected "+tn+" parse"+upFirst(dc.getCode())+"("+jpn+" v) throws Exception {\r\n");
    write("    "+tn+" res = new "+tn+"();\r\n");
    write("    if (v != null)\r\n");
    write("      res.setValue(parse"+upFirst(dc.getCode())+"Primitive(v));\r\n");
    write("    return res;\r\n");
    write("  }\r\n");
    write("\r\n");
  }

  private String getAsJsonPrimitive(String code, boolean shrt) {
    if ("boolean".equals(code))
      return shrt ? "Boolean" : "java.lang.Boolean";
    if ("decimal".equals(code))
      return shrt ? "BigDecimal" : "java.math.BigDecimal";
    if ("integer".equals(code))
      return shrt ? "Long" : "java.lang.Long";
    else         
      return "String";
  }

  private void start(String version, Date genDate) throws Exception {
    write("package org.hl7.fhir.instance.formats;\r\n");
    write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
    write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    for (DefinedCode dc : definitions.getPrimitives().values()) 
      write("import org.hl7.fhir.instance.model."+getPrimitiveTypeModelName(dc.getCode())+";\r\n");
    write("import org.hl7.fhir.instance.model.*;\r\n");
    write("import org.hl7.fhir.utilities.Utilities;\r\n");

    write("import com.google.gson.JsonObject;\r\n");
    write("import com.google.gson.JsonArray;\r\n");
    //  write("import java.util.*;\r\n");
    write("\r\n");
    write("public class JsonParser extends JsonParserBase {\r\n");
    write("\r\n");
  }


  private void generateParser(ElementDefn n, JavaGenClass clss) throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    String nn = javaClassName(n.getName());
    for (ElementDefn e : n.getElements()) {
      scanNestedTypesParser(n, nn, e);
    }
    context = nn;

    if (clss == JavaGenClass.AbstractResource)
      genInnerAbstract(n);
    else
      genInner(n, clss);

    for (ElementDefn e : strucs) {
      genInner(e, (clss == JavaGenClass.Resource || clss == JavaGenClass.AbstractResource) ? JavaGenClass.Backbone : JavaGenClass.Structure);
    }

  }

  private String javaClassName(String name) {
    if (name.equals("List"))
      return "List_";
    else 
      return name;
  }

  private void generateConstraintParser(ProfiledType cd) throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    context = cd.getName();
    ElementDefn n = definitions.getTypes().get(cd.getBaseType());

    typeNames.put(n, cd.getName());
    for (ElementDefn e : n.getElements()) {
      scanNestedTypesParser(n, n.getName(), e);
    }

    genInner(n, JavaGenClass.Constraint);

    for (ElementDefn e : strucs) {
      genInner(e, JavaGenClass.Structure);
    }

  }

  private void genInner(ElementDefn n, JavaGenClass clss) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());
    boolean bUseOwner = false;

    String pn = tn.contains("<") ? "\""+tn.substring(tn.indexOf('<')+1).replace(">", "") + "\"" : "";

    if (tn.contains(".")) {
      write("  protected "+tn+" parse"+upFirst(tn).replace(".", "")+"(JsonObject json, "+pathClass(tn)+" owner) throws Exception {\r\n");
      write("    "+tn+" res = new "+tn+"("+pn+");\r\n");
      bUseOwner = true;
    } else {
      write("  protected "+tn+" parse"+upFirst(tn).replace(".", "")+"(JsonObject json) throws Exception {\r\n");
      write("    "+tn+" res = new "+tn+"("+pn+");\r\n");
    }
    if (clss == JavaGenClass.Resource)
      write("    parse"+n.typeCode()+"Properties(json, res);\r\n");
    else if (clss == JavaGenClass.Backbone)
      write("    parseBackboneProperties(json, res);\r\n");
    else if (clss == JavaGenClass.Type && !tn.contains("."))
      write("    parseTypeProperties(json, res);\r\n");
    else
      write("    parseElementProperties(json, res);\r\n");
    boolean first = true;
    for (ElementDefn e : n.getElements()) {
      genElementParser(n, e, first, clss, bUseOwner);
      first = false;
    }
    write("    return res;\r\n");
    write("  }\r\n\r\n");    
  }

  private void genInnerAbstract(ElementDefn n) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());
    boolean bUseOwner = false;

    String pn = tn.contains("<") ? "\""+tn.substring(tn.indexOf('<')+1).replace(">", "") + "\"" : "";

    write("  protected void parse"+upFirst(tn).replace(".", "")+"Properties(JsonObject json, "+tn+" res) throws Exception {\r\n");

    if (!Utilities.noString(n.typeCode()))
      write("    parse"+n.typeCode()+"Properties(json, res);\r\n");

    boolean first = true;
    for (ElementDefn e : n.getElements()) {
      genElementParser(n, e, first, JavaGenClass.Resource, bUseOwner);
      first = false;
    }
    write("  }\r\n\r\n");    
  }

  private void genElementParser(ElementDefn root, ElementDefn e, boolean first, JavaGenClass clss, boolean bUseOwner) throws Exception {
    String name = e.getName();
    if (name.endsWith("[x]") || name.equals("[type]")) {
      String en = name.endsWith("[x]") && !name.equals("[x]") ? name.replace("[x]", "") : "value";
      String pfx = name.endsWith("[x]") ? name.replace("[x]", "") : "";
      write("    Type "+getElementName(en, false)+" = parseType(\""+en+"\", json);\r\n");
      write("    if ("+getElementName(en, false)+" != null)\r\n");
      write("      res.set"+upFirst(getElementName(en, false))+"("+getElementName(en, false)+");\r\n");
    } else if (name.equals("extension")) {
      // special case handling for extensions in json
      write("    parseExtensions(json, res.getExtension(), false);\r\n");
    } else if (name.equals("modifierExtension")) {
      write("    if (json != null && json.has(\"modifier\")) {\r\n");
      write("      JsonObject obj = json.getAsJsonObject(\"modifier\");\r\n");
      write("      parseExtensions(obj, res.getModifierExtension(), false);\r\n");
      write("    };\r\n");
    } else {
      String prsr = null;
      String aprsr = null;
      String anprsr = null;
      BindingSpecification cd = definitions.getBindingByName(e.getBindingName());
      if (e.typeCode().equals("code") && cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList) {
        String en = typeNames.get(e); // getCodeListType(cd.getBinding());
        prsr = "parseEnumeration(json.get(\""+name+"\").getAsString(), "+en+".NULL, new "+en.substring(0, en.indexOf("."))+"."+en.substring(en.indexOf(".")+1)+"EnumFactory())"; // en+".fromCode(parseString(xpp))";
        aprsr = "parseEnumeration(array.get(i).getAsString(), "+en+".NULL, new "+en.substring(0, en.indexOf("."))+"."+en.substring(en.indexOf(".")+1)+"EnumFactory())"; // en+".fromCode(parseString(xpp))";
        anprsr = "parseEnumeration(null, "+en+".NULL, new "+en.substring(0, en.indexOf("."))+"."+en.substring(en.indexOf(".")+1)+"EnumFactory())"; // en+".fromCode(parseString(xpp))";
        // parseEnumeration(xpp, Narrative.NarrativeStatus.additional, new Narrative.NarrativeStatusEnumFactory())
      } else {   
        String tn = typeName(root, e).replace(".", "");
        if (name.equals("extension")) {
          name = "extension";
          tn = "Extension";
        }
        if (tn.equals("char[]")) {
          prsr = "parseXhtml(json.get(\""+name+"\").getAsString())";
        } else if (tn.contains("Reference(")) {
          prsr = "parseReference(json.getAsJsonObject(\""+name+"\"))";
          aprsr = "parseReference(array.get(i).getAsJsonObject())";
          anprsr = "parseReference(null)";
        } else if (tn.startsWith(context) && !tn.equals(context) && !definitions.hasType(tn)) {
          if (bUseOwner) {
            prsr = "parse"+upFirst(tn)+"(json.getAsJsonObject(\""+name+"\"), owner)";
            aprsr = "parse"+upFirst(tn)+"(array.get(i).getAsJsonObject(), owner)";
            anprsr = "parse"+upFirst(tn)+"(null)";
          } else {
            prsr = "parse"+upFirst(tn)+"(json.getAsJsonObject(\""+name+"\"), res)";
            aprsr = "parse"+upFirst(tn)+"(array.get(i).getAsJsonObject(), res)";
            anprsr = "parse"+upFirst(tn)+"(null)";
          }
        } else if ("Uri".equalsIgnoreCase(tn)) {
          prsr = "parseUri(json.get(\""+name+"\").getAsString())";
          aprsr = "parseUri(array.get(i).getAsString())";
          anprsr = "parse"+upFirst(tn)+"(null)";
        } else if ("Instant".equals(tn)) {
          prsr = "parseInstant(json.get(\""+name+"\").getAsString())";
          aprsr = "parseInstant(array.get(i).getAsString())";
          anprsr = "parse"+upFirst(tn)+"(null)";
        } else if (isPrimitive(e)){
          prsr = "parse"+upFirst(tn)+"(json.get(\""+name+"\").getAs"+getAsJsonPrimitive(e.typeCode(), true)+"())";
          aprsr = "parse"+upFirst(tn)+"(array.get(i).getAs"+getAsJsonPrimitive(e.typeCode(), true)+"())";
          anprsr = "parse"+upFirst(tn)+"(null)";
        } else {
          prsr = "parse"+upFirst(tn)+"(json.getAsJsonObject(\""+name+"\"))";
          aprsr = "parse"+upFirst(tn)+"(array.get(i).getAsJsonObject())";
          anprsr = "parse"+upFirst(tn)+"(null)";
        }
      }

      if (e.unbounded()) {
        if (isPrimitive(e)) {
          write("    if (json.has(\""+name+"\")) {\r\n");
          write("      JsonArray array = json.getAsJsonArray(\""+name+"\");\r\n");
          write("      for (int i = 0; i < array.size(); i++) {\r\n");
          write("        res.get"+upFirst(name)+"().add("+aprsr+");\r\n");
          write("      }\r\n");
          write("    };\r\n");          
          write("    if (json.has(\"_"+name+"\")) {\r\n");
          write("      JsonArray array = json.getAsJsonArray(\"_"+name+"\");\r\n");
          write("      for (int i = 0; i < array.size(); i++) {\r\n");
          write("        if (i == res.get"+upFirst(name)+"().size())\r\n");
          write("          res.get"+upFirst(name)+"().add("+anprsr+");\r\n");
          write("        if (array.get(i) instanceof JsonObject) \r\n");
          write("          parseElementProperties(array.get(i).getAsJsonObject(), res.get"+upFirst(name)+"().get(i));\r\n");
          write("      }\r\n");
          write("    };\r\n");          
        } else {
          write("    if (json.has(\""+name+"\")) {\r\n");
          write("      JsonArray array = json.getAsJsonArray(\""+name+"\");\r\n");
          write("      for (int i = 0; i < array.size(); i++) {\r\n");
          write("        res.get"+upFirst(name)+"().add("+aprsr+");\r\n");
          write("      }\r\n");
          write("    };\r\n");
        }
      } else {
        write("    if (json.has(\""+name+"\"))\r\n");
        if (isPrimitive(e)) {
          write("      res.set"+upFirst(getElementName(name, false))+"Element("+prsr+");\r\n");
          write("    if (json.has(\"_"+name+"\"))\r\n");
          write("      parseElementProperties(json.getAsJsonObject(\"_"+name+"\"), res.get"+upFirst(getElementName(name, false))+"Element());\r\n");
        } else
          write("      res.set"+upFirst(getElementName(name, false))+"("+prsr+");\r\n");
      }
    }
  }

  private String typeName(ElementDefn root, ElementDefn elem) throws Exception {
    String t = elem.typeCode();
    if (elem.usesCompositeType()) { 
      if (typeNames.containsKey(elem) && typeNames.get(elem) != null)
        return typeNames.get(elem);
      else  
        return root.getName();      
    } else if (elem.getTypes().size() == 0) {
      return typeNames.get(elem);
    } else if (typeNames.containsKey(elem))
      return typeNames.get(elem);
    else
      return upFirst(t);
  }

  private void finishParser() throws Exception {
    write("  @Override\r\n");
    write("  protected Resource parseResource(JsonObject json) throws Exception {\r\n");
    write("    String t = json.get(\"resourceType\").getAsString();\r\n");
    write("    if (Utilities.noString(t))\r\n");
    write("      throw new Exception(\"Unable to find resource type - maybe not a FHIR resource?\");\r\n");
    write("    "+reg.toString().substring(9));
    write("    else if (t.equals(\"Binary\"))\r\n");
    write("      return parseBinary(json);\r\n");
    write("    throw new Exception(\"Unknown.Unrecognised resource type '\"+t+\"' (in property 'resourceType')\");\r\n");
    write("  }\r\n\r\n");
    write("  protected Type parseType(String prefix, JsonObject json) throws Exception {\r\n");
    write("    "+regt.toString().substring(9));
    write("    return null;\r\n");
    write("  }\r\n\r\n");
    write("  protected Type parseType(JsonObject json, String type) throws Exception {\r\n");
    write("    "+regt2.toString().substring(9));
    write("    throw new Exception(\"Unknown Type \"+type);\r\n");
    write("  }\r\n\r\n");

    //    write("  public Element parseFragment(XmlPullParser xpp, String type) throws Exception {\r\n");
    //    write("    "+regf.toString().substring(9));
    //    write("    throw new Exception(\"Unknown type \"+type);\r\n");
    //    write("  }\r\n\r\n");
    //
    write("  protected boolean hasTypeName(JsonObject json, String prefix) {\r\n");
    write("    "+regn.toString());
    write("    return false;\r\n");
    write("  }\r\n");
  }

  public void finish() throws Exception {

    write("}\r\n");
    write("\r\n");
    flush();
  }

  private String getCodeListType(String binding) {
    StringBuilder b = new StringBuilder();
    boolean up = true;
    for (char ch: binding.toCharArray()) {
      if (ch == '-')
        up = true;
      else if (up) {
        b.append(Character.toUpperCase(ch));
        up = false;
      }
      else        
        b.append(ch);
    }
    return b.toString();
  }

  private void scanNestedTypesParser(ElementDefn root, String path, ElementDefn e) throws Exception {
    String tn = null;
    if (e.typeCode().equals("code") && e.hasBinding()) {
      BindingSpecification cd = definitions.getBindingByName(e.getBindingName());
      if (cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList) {
        tn = getCodeListType(cd.getReference());
        if (!enumNames.contains(tn)) {
          enumNames.add(tn);
          enums.add(e);
        }
        typeNames.put(e,  rootOf(path)+"."+upFirst(tn.substring(1)));
      }
    }
    if (tn == null) {
      if (e.getTypes().size() > 0 && !e.usesCompositeType()) {
        tn = e.typeCode();
        TypeRef tr = e.getTypes().get(0);
        if (tr.isUnboundGenericParam())
          tn = genparam;
        else if (tr.isXhtml()) 
          tn = "char[]";
        else if (tr.isWildcardType())
          tn ="Type";
        else if (tn.equals("string"))
          tn = "String";
        typeNames.put(e,  tn);
      } else {
        if (e.usesCompositeType()) {
          tn = typeNames.get(getElementForPath(root, e.typeCode().substring(1)));
          typeNames.put(e,  tn);
        } else {
          if (e.getDeclaredTypeName() != null) 
            tn = e.getDeclaredTypeName();
          else
            tn = upFirst(e.getName());
          if (tn.equals("Element"))
            tn = "Element_";
          if (!e.getName().equals("extension"))
            strucs.add(e);
          if (typeNameStrings.contains(tn)) {
            char i = 'A';
            while (typeNameStrings.contains(tn+i))
              i++;
            tn = tn + i;
          }
          typeNameStrings.add(tn);
          tn = path+"."+tn;
          typeNames.put(e,  tn);
          for (ElementDefn c : e.getElements()) {
            scanNestedTypesParser(root, path, c);
          }
        }
      }
    } 
  }

  private Object getElementForPath(ElementDefn root, String pathname) throws Exception {
    String[] path = pathname.split("\\.");
    if (!path[0].equals(root.getName()))
      throw new Exception("Element Path '"+pathname+"' is not legal in this context");
    ElementDefn res = root;
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

  public void generateComposer() throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    enumNames.clear();
    strucs.clear();
    context = null;

    reg = new StringBuilder();
    regt = new StringBuilder();
    regt2 = new StringBuilder();
    regf = new StringBuilder();
    regn = new StringBuilder();
    regtn = new StringBuilder();
    regtp = new StringBuilder();
    regti = new StringBuilder();
    genparam = null;

    genElementComposer();
    generateEnumComposer();
    for (DefinedCode dc : definitions.getPrimitives().values()) 
      generatePrimitiveComposer(dc);

    for (ElementDefn n : definitions.getInfrastructure().values()) {
      if (!n.getName().equals("Element") && !n.getName().equals("BackboneElement")) {
        generateComposer(n, JavaGenClass.Structure);
        //      String t = upFirst(n.getName());
        //      regt.append("    else if (type instanceof "+t+")\r\n       compose"+n.getName()+"(prefix+\""+n.getName()+"\", ("+t+") type);\r\n");
      }
    }

    for (ElementDefn n : definitions.getTypes().values()) {
      generateComposer(n, JavaGenClass.Type);
      regtn.append("    else if (type instanceof "+n.getName()+")\r\n       compose"+n.getName()+"(prefix+\""+n.getName()+"\", ("+n.getName()+") type);\r\n");
      regti.append("    else if (type instanceof "+n.getName()+")\r\n       compose"+n.getName()+"Inner(("+n.getName()+") type);\r\n");
    }

    for (ProfiledType n : definitions.getConstraints().values()) {
      generateConstraintComposer(n);
      regtp.append("    else if (type instanceof "+n.getName()+")\r\n       compose"+n.getName()+"(prefix+\""+n.getName()+"\", ("+n.getName()+") type);\r\n");
      regti.append("    else if (type instanceof "+n.getName()+")\r\n       compose"+n.getName()+"Inner(("+n.getName()+") type);\r\n");
    }
    for (ElementDefn n : definitions.getStructures().values()) {
        generateComposer(n, JavaGenClass.Structure);
        regtn.append("    else if (type instanceof "+n.getName()+")\r\n       compose"+n.getName()+"(prefix+\""+n.getName()+"\", ("+n.getName()+") type);\r\n");
        regti.append("    else if (type instanceof "+n.getName()+")\r\n       compose"+n.getName()+"Inner(("+n.getName()+") type);\r\n");
    }

    for (String s : definitions.getBaseResources().keySet()) {
      ResourceDefn n = definitions.getBaseResources().get(s);
      generateComposer(n.getRoot(), n.isAbstract() ? JavaGenClass.AbstractResource : JavaGenClass.Resource);
      if (!n.isAbstract()) {
        String nn = javaClassName(n.getName());
        reg.append("    else if (resource instanceof "+nn+")\r\n      compose"+nn+"(\""+n.getName()+"\", ("+nn+")resource);\r\n");
        regn.append("    else if (resource instanceof "+nn+")\r\n      compose"+nn+"(name, ("+nn+")resource);\r\n");
      }
    }

    for (String s : definitions.sortedResourceNames()) {
      ResourceDefn n = definitions.getResources().get(s);
      generateComposer(n.getRoot(), JavaGenClass.Resource);
      String nn = javaClassName(n.getName());
      reg.append("    else if (resource instanceof "+nn+")\r\n      compose"+nn+"(\""+n.getName()+"\", ("+nn+")resource);\r\n");
      regn.append("    else if (resource instanceof "+nn+")\r\n      compose"+nn+"(name, ("+nn+")resource);\r\n");
    }

    for (DefinedCode cd : definitions.getPrimitives().values()) {
      String n = upFirst(cd.getCode());
      String t = upFirst(cd.getCode())+"Type";
      regtn.append("    else if (type instanceof "+t+") {\r\n");
      regtn.append("      compose"+upFirst(n)+"Core(prefix+\""+n+"\", ("+t+") type, false);\r\n");
      regtn.append("      compose"+upFirst(n)+"Extras(prefix+\""+n+"\", ("+t+") type, false);\r\n");
      regtn.append("    }\r\n");
    }

    finishComposer();
  }

  private void genElementComposer() throws Exception {

    write("  protected void composeElement(Element element) throws Exception {\r\n");
    write("    if (element.hasId())\r\n");
    write("      prop(\"id\", element.getId());\r\n");
    write("      if (makeComments(element)) {\r\n");
    write("        openArray(\"fhir_comments\");\r\n");
    write("        for (String s : element.getFormatComments())\r\n");
    write("          prop(null,  s);\r\n");
    write("         closeArray();\r\n");
    write("      }\r\n");
    write("    if (element.hasExtension()) {\r\n");
    write("      composeExtensions(element.getExtension());\r\n");
    write("    }\r\n");
    write("  }\r\n");
    write("\r\n");
    write("  protected void composeBackbone(BackboneElement element) throws Exception {\r\n");
    write("    composeElement(element);\r\n");
    write("    if (element.hasModifierExtension()) {\r\n");
    write("      openObject(\"modifier\");\r\n");
    write("      composeExtensions(element.getModifierExtension());\r\n");
    write("      close();\r\n");
    write("    }\r\n");
    write("  }\r\n");
    write("\r\n");
  }

  private String upFirst(String n) {
    return n.substring(0,1).toUpperCase() + n.substring(1);
  }


  private void generateEnumComposer() throws Exception {
    write("  protected <E extends Enum<E>> void composeEnumerationCore(String name, Enumeration<E> value, EnumFactory e, boolean inArray) throws Exception {\r\n");
    write("    if (value != null && value.getValue() != null) {\r\n");
    write("      prop(name, e.toCode(value.getValue()));\r\n");
    write("    } else if (inArray)   \r\n");
    write("      writeNull(name);\r\n");
    write("  }    \r\n");
    write("\r\n");
    write("  protected <E extends Enum<E>> void composeEnumerationExtras(String name, Enumeration<E> value, EnumFactory e, boolean inArray) throws Exception {\r\n");
    write("    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || makeComments(value))) {\r\n");
    write("      open(inArray ? null : \"_\"+name);\r\n");
    write("      composeElement(value);\r\n");
    write("      close();\r\n");
    write("    } else if (inArray)   \r\n");
    write("      writeNull(name);\r\n");
    write("  }    \r\n");
    write("\r\n");
  }

  private String getPrimitiveTypeModelName(String code) {
    if (code.equals("string"))
      return "StringType";
    if (definitions.hasPrimitiveType(code))
      return upFirst(code)+"Type";
    return upFirst(code);
  }

  private void generatePrimitiveComposer(DefinedCode dc) throws Exception {
    String tn = getPrimitiveTypeModelName(dc.getCode());

    write("  protected void compose"+upFirst(dc.getCode())+"Core(String name, "+tn+" value, boolean inArray) throws Exception {\r\n");
    write("    if (value != null && value.hasValue()) {\r\n");
    if (dc.getCode().equals("integer"))
      write("        prop(name, Integer.valueOf(value.getValue()));\r\n");
    else  if (dc.getCode().equals("boolean")) 
      write("        prop(name, value.getValue());\r\n");
    else  if (dc.getCode().equals("decimal")) 
      write("        prop(name, value.getValue());\r\n");
    else {
      write("        prop(name, toString(value.getValue()));\r\n");
    }
    write("    }    \r\n");
    write("    else if (inArray) \r\n");
    write("      writeNull(name); \r\n");
    write("  }    \r\n");
    write("\r\n");

    write("  protected void compose"+upFirst(dc.getCode())+"Extras(String name, "+tn+" value, boolean inArray) throws Exception {\r\n");
    write("    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || makeComments(value))) {\r\n");
    write("      open(inArray ? null : \"_\"+name);\r\n");
    write("      composeElement(value);\r\n");
    write("      close();\r\n");
    write("    }\r\n");
    write("    else if (inArray) \r\n");
    write("      writeNull(name); \r\n");
    write("  }\r\n");
    write("\r\n");
  }


  private void generateComposer(ElementDefn n, JavaGenClass clss) throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    String nn = javaClassName(n.getName());
    for (ElementDefn e : n.getElements()) {
      scanNestedTypesComposer(n, nn, e);
    }
    context = nn;

    if (clss == JavaGenClass.AbstractResource)
      genInnerAbstractComposer(n);
    else
      genInnerComposer(n, clss);

    for (ElementDefn e : strucs) {
      genInnerComposer(e, (clss == JavaGenClass.Resource || clss == JavaGenClass.AbstractResource) ? JavaGenClass.Backbone : JavaGenClass.Structure);
    }

  }

  private void generateConstraintComposer(ProfiledType cd) throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    context = cd.getName();
    ElementDefn n = definitions.getTypes().get(cd.getBaseType());

    typeNames.put(n, cd.getName());
    for (ElementDefn e : n.getElements()) {
      scanNestedTypesComposer(n, n.getName(), e);
    }

    genInnerComposer(n, JavaGenClass.Constraint);

    for (ElementDefn e : strucs) {
      genInnerComposer(e, JavaGenClass.Structure);
    }

  }

  private void genInnerComposer(ElementDefn n, JavaGenClass clss) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());

    write("  protected void compose"+upFirst(tn).replace(".", "")+"(String name, "+tn+" element) throws Exception {\r\n");
    write("    if (element != null) {\r\n");
    if (clss == JavaGenClass.Resource) 
      write("      prop(\"resourceType\", name);\r\n");
    else
      write("      open(name);\r\n");
    write("      compose"+upFirst(tn).replace(".", "")+"Inner(element);\r\n");
    if (clss != JavaGenClass.Resource)  
      write("      close();\r\n");
    write("    }\r\n");    
    write("  }\r\n\r\n");    
    write("  protected void compose"+upFirst(tn).replace(".", "")+"Inner("+tn+" element) throws Exception {\r\n");
    if (clss == JavaGenClass.Resource) 
      write("      compose"+n.typeCode()+"Elements(element);\r\n");
    else if (clss == JavaGenClass.Backbone) 
      write("      composeBackbone(element);\r\n");
    else
      write("      composeElement(element);\r\n");
    for (ElementDefn e : n.getElements()) 
      genElementComposer(n, e, clss);
    write("  }\r\n\r\n");    
  }

  private void genInnerAbstractComposer(ElementDefn n) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());

    write("  protected void compose"+upFirst(tn).replace(".", "")+"Elements("+tn+" element) throws Exception {\r\n");
    if (!Utilities.noString(n.typeCode())) 
      write("      compose"+n.typeCode()+"Elements(element);\r\n");
    for (ElementDefn e : n.getElements()) 
      genElementComposer(n, e, JavaGenClass.Backbone);
    write("  }\r\n\r\n");    
  }

  private String pathClass(String tn) {
    return tn.substring(0, tn.indexOf('.'));
  }

  private String pathNode(String tn) {
    return tn.substring(tn.indexOf('.')+1);
  }

  private void genElementComposer(ElementDefn root, ElementDefn e, JavaGenClass clss) throws Exception {
    String name = e.getName();
    if (name.endsWith("[x]") || name.equals("[type]")) {
      String en = name.endsWith("[x]") & !name.equals("[x]") ? name.replace("[x]", "") : "value";
      String pfx = name.endsWith("[x]") ? name.replace("[x]", "") : "";
      write("      if (element.has"+upFirst(en)+"()) {\r\n");
      write("        composeType(\""+pfx+"\", element.get"+upFirst(en)+"());\r\n");
      write("      }\r\n");
    } else if (name.equals("extension")) {
      // special case handling for extensions in json
      write("      if (element.hasExtension()) {\r\n");
      write("        composeExtensions(element.getExtension());\r\n");
      write("      };\r\n");
    } else if (name.equals("modifierExtension")) {
      write("      if (element.hasModifierExtension()) {\r\n");
      write("        openObject(\"modifier\");\r\n");
      write("        composeExtensions(element.getModifierExtension());\r\n");
      write("        close();\r\n");
      write("      };\r\n");      
    } else {
      String comp = null;
      String en = null;
      BindingSpecification cd = definitions.getBindingByName(e.getBindingName());
      String tn = typeName(root, e, false);
      if (e.typeCode().equals("code") && cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList) {
        en = typeNames.get(e); // getCodeListType(cd.getBinding());
        comp = null;
      } else {   
        if (name.equals("extension")) {
          name = "extension";
          tn = "Extension";
        }
        if (tn.equals("char[]"))
          tn = "xhtml";
        else if (tn.equals("code")) {
          tn = "Code";
          comp = "composeCode";
        } else if (tn.equals("instant"))
          tn = "Instant";
        if (tn.contains("Reference(")) {
          comp = "composeReference";
          tn = "Reference";
        } else if (tn.contains("("))
          comp = "compose"+tn;
        else if (tn.startsWith(context) && !tn.equals(context)) {
          comp = "compose"+upFirst(leaf(tn)).replace(".", "");
        } else
          comp = "compose"+upFirst(leaf(tn)).replace(".", "");
      }
      //      if ((!contentsHaveId && typeIsSimple(e)) || e.typeCode().equals("xml:lang")) 
      //        comp = comp+"Simple";

      if (name.equals("extension")) {
        String s = "Extension"; 
        write("      if (element.has"+s+"()) {\r\n");
        write("        openArray(\"extension\");\r\n");
        write("        for (Extension e : element.get"+s+"()) \r\n");
        write("          composeExtension(null, e);\r\n");
        write("        closeArray();\r\n");
        write("      };\r\n");
      } else if (e.unbounded()) {
        tn = typeName(root, e, true);
        if (tn.contains("Reference(")) {
          comp = "composeReference";
          tn = "Reference";
        }
        write("      if (element.has"+upFirst(name)+"()) {\r\n");
        if (en == null) {
          if (tn.equals("String"))
            tn = "StringType";
          if (definitions.hasPrimitiveType(tn))
            tn = upFirst(tn)+"Type";

          if (isPrimitive(e)) {
            write("        openArray(\""+name+"\");\r\n");
            write("        for ("+(tn.contains("(") ? tn : upFirst(tn))+" e : element.get"+upFirst(getElementName(name, false))+"()) \r\n");
            write("          "+comp+"Core(null, e, true);\r\n");
            write("        closeArray();\r\n");
            write("        if (anyHasExtras(element.get"+upFirst(getElementName(name, false))+"())) {\r\n");
            write("          openArray(\"_"+name+"\");\r\n");
            write("          for ("+(tn.contains("(") ? tn : upFirst(tn))+" e : element.get"+upFirst(getElementName(name, false))+"()) \r\n");
            write("            "+comp+"Extras(null, e, true);\r\n");
            write("          closeArray();\r\n");
            write("        }\r\n");
          } else if (e.typeCode().equals("Resource")){
            write("        openArray(\""+name+"\");\r\n");
            write("        for ("+(tn.contains("(") ? tn : upFirst(tn))+" e : element.get"+upFirst(getElementName(name, false))+"()) {\r\n");
            write("          open(null);\r\n");
            write("          "+comp+"(e);\r\n");
            write("          close();\r\n");
            write("        }\r\n");
            write("        closeArray();\r\n");

          } else {
            write("        openArray(\""+name+"\");\r\n");
            write("        for ("+(tn.contains("(") ? tn : upFirst(tn))+" e : element.get"+upFirst(getElementName(name, false))+"()) \r\n");
            write("          "+comp+"(null, e);\r\n");
            write("        closeArray();\r\n");
          }
        } else {
          write("        openArray(\""+name+"\");\r\n");
          write("        for (Enumeration<"+prepEnumName(en)+"> e : element.get"+upFirst(getElementName(name, false))+"()) \r\n");
          write("          composeEnumerationCore(null, e, new "+context+"."+upFirst(en.substring(en.indexOf(".")+2))+"EnumFactory(), true);\r\n");
          write("        closeArray();\r\n");
          write("        if (anyHasExtras(element.get"+upFirst(getElementName(name, false))+"())) {\r\n");
          write("          openArray(\"_"+name+"\");\r\n");
          write("          for (Enumeration<"+prepEnumName(en)+"> e : element.get"+upFirst(getElementName(name, false))+"()) \r\n");
          write("            composeEnumerationExtras(null, e, new "+context+"."+upFirst(en.substring(en.indexOf(".")+2))+"EnumFactory(), true);\r\n");
          write("          closeArray();\r\n");
          write("        }\r\n");
        }
        write("      };\r\n");
      } else if (en != null) {
        write("      if (element.has"+upFirst(getElementName(name, false))+"Element()) {\r\n");
        write("        composeEnumerationCore(\""+name+"\", element.get"+upFirst(getElementName(name, false))+"Element(), new "+context+"."+upFirst(en.substring(en.indexOf(".")+2))+"EnumFactory(), false);\r\n");
        write("        composeEnumerationExtras(\""+name+"\", element.get"+upFirst(getElementName(name, false))+"Element(), new "+context+"."+upFirst(en.substring(en.indexOf(".")+2))+"EnumFactory(), false);\r\n");
        write("      }\r\n");
        //write("        composeString(\""+name+"\", element.get"+upFirst(getElementName(name, false))+"().toCode());\r\n");        
      } else if (e.typeCode().equals("Resource")){
        write("        if (element.has"+upFirst(getElementName(name, false))+"()) {\r\n");
        write("          open(\""+name+"\");\r\n");
        write("          "+comp+"(element.get"+upFirst(getElementName(name, false))+"());\r\n");
        write("          close();\r\n");
        write("        }\r\n");
      } else if (isPrimitive(e)) {
        write("      if (element.has"+upFirst(getElementName(name, false))+"Element()) {\r\n");
        write("        "+comp+"Core(\""+name+"\", element.get"+upFirst(getElementName(name, false))+"Element(), false);\r\n");
        write("        "+comp+"Extras(\""+name+"\", element.get"+upFirst(getElementName(name, false))+"Element(), false);\r\n");
        write("      }\r\n");
      } else {  
        write("      if (element.has"+upFirst(getElementName(name, false))+"()) {\r\n");
        write("        "+comp+"(\""+name+"\", element.get"+upFirst(getElementName(name, false))+"());\r\n");
        write("      }\r\n");
      }
    }
  }

  private boolean isPrimitive(ElementDefn e) {
    return definitions.hasPrimitiveType(e.typeCode());
  }


  private String prepEnumName(String en) {
    String[] parts = en.split("\\.");
    if (parts.length == 1)
      return upFirst(parts[0]);
    else
      return upFirst(parts[0])+'.'+upFirst(parts[1].substring(1));
  }

  private String leaf(String tn) {
    return tn.startsWith("java.lang.") ? tn.substring(10) : tn;
  }

  private boolean typeIsSimple(ElementDefn e) {
    String t = e.typeCode();
    return definitions.getPrimitives().containsKey(t);
  }

  private String typeName(ElementDefn root, ElementDefn elem, boolean formal) throws Exception {
    String t = elem.typeCode();
    //    if (usePrimitive && definitions.getPrimitives().containsKey(t)) {
    //      if (t.equals("boolean"))
    //        return formal ? "boolean" : "java.lang.Boolean";
    //      else if (t.equals("integer"))
    //        return "int";
    //      else if (t.equals("decimal"))
    //        return formal ? "BigDecimal" : "BigDecimal";
    //      else if (t.equals("base64Binary"))
    //        return formal ? "byte[]" : "bytes";
    //      else if (t.equals("instant"))
    //        return formal ? "java.util.Calendar" : "Date";
    //      else if (t.equals("uri"))
    //        return formal ? "java.net.URI" : "Uri";
    //      else if (t.equals("dateTime"))
    //        return "DateTime";
    //      else if (t.equals("date"))
    //        return "Date";
    //      else 
    //        return "String";
    ////        return upFirst(t);
    //    }  else if (t.equals("xml:lang"))
    //        return formal ? "string" : "Code";
    //    else 
    if (elem.usesCompositeType()) { 
      if (typeNames.containsKey(elem) && typeNames.get(elem) != null)
        return typeNames.get(elem);
      else  
        return root.getName();      
    } else if (elem.getTypes().size() == 0) {
      return typeNames.get(elem);
    } else if (typeNames.containsKey(elem))
      return typeNames.get(elem);
    else
      return upFirst(t);
  }

  private void finishComposer() throws Exception {
    write("  @Override\r\n");
    write("  protected void composeResource(Resource resource) throws Exception {\r\n");
    write("    "+reg.toString().substring(9));
    write("    else if (resource instanceof Binary)\r\n");
    write("      composeBinary(\"Binary\", (Binary)resource);\r\n");
    write("    else\r\n");
    write("      throw new Exception(\"Unhanded resource type \"+resource.getClass().getName());\r\n");
    write("  }\r\n\r\n");
    write("  protected void composeNamedReference(String name, Resource resource) throws Exception {\r\n");
    write("    "+regn.toString().substring(9));
    write("    else if (resource instanceof Binary)\r\n");
    write("      composeBinary(name, (Binary)resource);\r\n");
    write("    else\r\n");
    write("      throw new Exception(\"Unhanded resource type \"+resource.getClass().getName());\r\n");
    write("  }\r\n\r\n");
    write("  protected void composeType(String prefix, Type type) throws Exception {\r\n");
    write("    if (type == null)\r\n");
    write("      ;\r\n");
    write(regtp.toString());
    write(regtn.toString());
    write("    else\r\n");
    write("      throw new Exception(\"Unhanded type\");\r\n");
    write("  }\r\n\r\n");
    write("  protected void composeTypeInner(Type type) throws Exception {\r\n");
    write("    if (type == null)\r\n");
    write("      ;\r\n");
    write(regti.toString());
    write("    else\r\n");
    write("      throw new Exception(\"Unhanded type\");\r\n");
    write("  }\r\n\r\n");
    //
    //    write("  private boolean nameIsTypeName(XmlPullParser xpp, String prefix) {\r\n");
    //    write("    "+regn.toString());
    //    write("    return false;\r\n");
    //    write("  }\r\n");
    //    
  }

  private void scanNestedTypesComposer(ElementDefn root, String path, ElementDefn e) throws Exception {
    String tn = null;
    if (e.typeCode().equals("code") && e.hasBinding()) {
      BindingSpecification cd = definitions.getBindingByName(e.getBindingName());
      if (cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList) {
        tn = getCodeListType(cd.getReference());
        if (!enumNames.contains(tn)) {
          enumNames.add(tn);
          enums.add(e);
        }
        typeNames.put(e,  rootOf(path)+"."+tn);
      }
    }
    if (tn == null) {
      if (e.usesCompositeType()) {
        tn = typeNames.get(getElementForPath(root, e.typeCode().substring(1)));
        typeNames.put(e,  tn);
      } else if (e.getTypes().size() > 0) {
        tn = e.typeCode();
        TypeRef tr = e.getTypes().get(0);

        if (tr.isUnboundGenericParam())
          tn = genparam;
        else if (tr.isXhtml()) 
          tn = "char[]";
        else if (tr.isWildcardType())
          tn ="Type";
        else if (tn.equals("xml:lang"))
          tn = "Code";
        else if (tn.equals("string"))
          tn = "String";
        if (tn.contains("<"))
          tn = tn.substring(0, tn.indexOf('<')+1)+tn.substring(tn.indexOf('<')+1, tn.indexOf('<')+2).toUpperCase()+tn.substring(tn.indexOf('<')+2);
        typeNames.put(e,  tn);
      } else {
        if (e.getDeclaredTypeName() != null) 
          tn = e.getDeclaredTypeName();
        else
          tn = upFirst(e.getName());
        if (tn.equals("Element"))
          tn = "Element_";
        if (!e.getName().equals("extension"))
          strucs.add(e);
        if (typeNameStrings.contains(tn)) {
          char i = 'A';
          while (typeNameStrings.contains(tn+i))
            i++;
          tn = tn + i;
        }
        typeNameStrings.add(tn);
        tn = path+"."+tn;
        typeNames.put(e,  tn);
        for (ElementDefn c : e.getElements()) {
          scanNestedTypesComposer(root, path, c);
        }
      }
    } 
  }

  private String rootOf(String path) {
    int i = path.indexOf('.');
    return i == -1 ? path : path.substring(0, i);
  }

  protected String getElementName(String name, boolean alone) {
    if (name.equals("[type]"))
      return "value";
    else if ((alone && GeneratorUtils.isJavaReservedWord(name)) || (!alone && name.equals("class")))
      return name+"_";
    else
      return name.replace("[x]", "");
  }

}
