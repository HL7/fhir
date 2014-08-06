package org.hl7.fhir.tools.implementations.java;
/*
Copyright (c) 2011-2014, HL7, Inc
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

public class JavaParserJsonGenerator extends JavaBaseGenerator {
  public enum JavaGenClass { Structure, Type, Resource, Constraint, Backbone }

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
  private StringBuilder regf = new StringBuilder();
  private StringBuilder regn = new StringBuilder();
  private String genparam;
  
  public JavaParserJsonGenerator(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }

  public void generate(Definitions definitions, String version, Date genDate) throws Exception {

    this.definitions = definitions;
    
    start(version, genDate);

    genElement();
    generateEnumParser();
    
    for (DefinedCode dc : definitions.getPrimitives().values()) 
      generatePrimitive(dc);
    
    for (ElementDefn n : definitions.getInfrastructure().values()) {
      generate(n, JavaGenClass.Structure);
      String t = upFirst(n.getName());
//      regt.append("    else if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return parse"+t+"(xpp);\r\n");
//    regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json);\r\n");
    }
    
    for (ElementDefn n : definitions.getTypes().values()) {
      generate(n, JavaGenClass.Type);
      String an = n.getName().equals("ResourceReference") ? "Resource" : n.getName();
      regt.append("    else if (json.has(prefix+\""+an+"\"))\r\n      return parse"+n.getName()+"(json.getAsJsonObject(prefix+\""+an+"\"));\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+an+"\"))\r\n      return true;\r\n");
    }

    for (ProfiledType n : definitions.getConstraints().values()) {
      generateConstraint(n);
      regt.append("    else if (json.has(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json.getAsJsonObject(prefix+\""+n.getName()+"\"));\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    }
    for (ElementDefn n : definitions.getStructures().values()) {
      generate(n, JavaGenClass.Structure);
      regt.append("    else if (json.has(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(json.getAsJsonObject(prefix+\""+n.getName()+"\"));\r\n");
      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
      regn.append("    if (json.has(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    }
    
    genResource();
    
    for (String s : definitions.sortedResourceNames()) {
      ResourceDefn n = definitions.getResources().get(s);
      generate(n.getRoot(), JavaGenClass.Resource);
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
    
    finish();
  }

  private void genElement() throws Exception {
    write("  protected void parseElementProperties(JsonObject json, Element element) throws Exception {\r\n");
    write("    super.parseElementProperties(json, element);\r\n");
    write("    if (json != null && json.has(\"extension\")) {\r\n");
    write("      JsonArray array = json.getAsJsonArray(\"extension\");\r\n");
    write("      for (int i = 0; i < array.size(); i++) {\r\n");
    write("        element.getExtensions().add(parseExtension(array.get(i).getAsJsonObject()));\r\n");
    write("      }\r\n");
    write("    };\r\n");    
    write("  }\r\n");
    write("\r\n");
    write("  protected void parseBackboneProperties(JsonObject json, BackboneElement element) throws Exception {\r\n");
    write("    parseElementProperties(json, element);\r\n");
    write("    if (json != null && json.has(\"modifierExtension\")) {\r\n");
    write("      JsonArray array = json.getAsJsonArray(\"modifierExtension\");\r\n");
    write("      for (int i = 0; i < array.size(); i++) {\r\n");
    write("        element.getModifierExtensions().add(parseExtension(array.get(i).getAsJsonObject()));\r\n");
    write("      }\r\n");
    write("    };\r\n");    
    write("  }\r\n");
    write("\r\n");
    write("  protected void parseTypeProperties(JsonObject json, Element element) throws Exception {\r\n");
    write("    parseElementProperties(json, element);\r\n");
    write("  }\r\n");
    write("\r\n");
  }

  private void genResource() throws Exception {
    write("  protected void parseResourceProperties(JsonObject json, Resource res) throws Exception {\r\n");
    write("    parseBackboneProperties(json, res); \r\n");
    write("    if (json.has(\"language\"))\r\n");
    write("      res.setLanguage(parseCode(json.get(\"language\").getAsString()));\r\n");
    write("    if (json.has(\"_language\"))\r\n");
    write("      parseElementProperties(json.getAsJsonObject(\"_language\"), res.getLanguage());\r\n");
    write("    if (json.has(\"text\"))\r\n");
    write("      res.setText(parseNarrative(json.getAsJsonObject(\"text\")));\r\n");
    write("    if (json.has(\"contained\")) {\r\n");
    write("      JsonArray array = json.getAsJsonArray(\"contained\");\r\n");
    write("      for (int i = 0; i < array.size(); i++) {\r\n");
    write("        res.getContained().add(parseResource(array.get(i).getAsJsonObject()));\r\n");
    write("      }\r\n");
    write("    };\r\n");    
    write("  }\r\n");
    write("\r\n");
  }

  private void generateEnumParser() throws Exception {
    write("  @SuppressWarnings(\"unchecked\")\r\n");
    write("  private <E extends Enum<E>> Enumeration<E> parseEnumeration(String s, E item, EnumFactory e) throws Exception {\r\n");
    write("    Enumeration<E> res = new Enumeration<E>();\r\n");
//    write("    parseElementProperties(json, res);\r\n");
    write("    if (s != null)\r\n");
    write("      res.setValue((E) e.fromCode(s));\r\n");
    write("    return res;\r\n");
    write("  }\r\n");
    write("\r\n");
  }

  private String getPrimitiveTypeModelName(String code) {
    if (code.equals("string"))
      return "StringType";
    if (definitions.hasPrimitiveType(code))
      return upFirst(code)+"Type";
    return upFirst(code);
  }

  private void generatePrimitive(DefinedCode dc) throws Exception {
    String tn = getPrimitiveTypeModelName(dc.getCode());
    String jpn = getAsJsonPrimitive(dc.getCode(), false);
    write("  private "+tn+" parse"+upFirst(dc.getCode())+"("+jpn+" v) throws Exception {\r\n");
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

  private String upFirst(String n) {
    return n.substring(0,1).toUpperCase() + n.substring(1);
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


  private void generate(ElementDefn n, JavaGenClass clss) throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    String nn = javaClassName(n.getName());
    for (ElementDefn e : n.getElements()) {
        scanNestedTypes(n, nn, e);
    }
    context = nn;

    genInner(n, clss);
    
    for (ElementDefn e : strucs) {
      genInner(e, clss == JavaGenClass.Resource ? JavaGenClass.Backbone : JavaGenClass.Structure);
    }

  }

  private String javaClassName(String name) {
    if (name.equals("List"))
      return "List_";
    else 
      return name;
  }

  private void generateConstraint(ProfiledType cd) throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    context = cd.getName();
    ElementDefn n = definitions.getTypes().get(cd.getBaseType());
    
    typeNames.put(n, cd.getName());
    for (ElementDefn e : n.getElements()) {
        scanNestedTypes(n, n.getName(), e);
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
      write("  private "+tn+" parse"+upFirst(tn).replace(".", "").replace("<", "_").replace(">", "")+"(JsonObject json, "+pathClass(tn)+" owner) throws Exception {\r\n");
      write("    "+tn+" res = new "+tn+"("+pn+");\r\n");
      bUseOwner = true;
    } else {
      write("  private "+tn+" parse"+upFirst(tn).replace(".", "").replace("<", "_").replace(">", "")+"(JsonObject json) throws Exception {\r\n");
      write("    "+tn+" res = new "+tn+"("+pn+");\r\n");
    }
    if (clss == JavaGenClass.Resource)
      write("    parseResourceProperties(json, res);\r\n");
    else if (clss == JavaGenClass.Backbone)
      write("    parseBackboneProperties(json, res);\r\n");
    else if (clss == JavaGenClass.Type && !tn.contains("."))
      write("    parseTypeProperties(json, res);\r\n");
    else
      write("    parseElementProperties(json, res);\r\n");
    boolean first = true;
    for (ElementDefn e : n.getElements()) {
      genElement(n, e, first, clss, bUseOwner);
      first = false;
    }
    write("    return res;\r\n");
    write("  }\r\n\r\n");    
  }

  private String pathClass(String tn) {
    return tn.substring(0, tn.indexOf('.'));
  }

  private String pathNode(String tn) {
    return tn.substring(tn.indexOf('.')+1);
  }

  private void genElement(ElementDefn root, ElementDefn e, boolean first, JavaGenClass clss, boolean bUseOwner) throws Exception {
    String name = e.getName();
    if (name.endsWith("[x]") || name.equals("[type]")) {
      String en = name.endsWith("[x]") && !name.equals("[x]") ? name.replace("[x]", "") : "value";
      String pfx = name.endsWith("[x]") ? name.replace("[x]", "") : "";
      write("    Type "+getElementName(en, false)+" = parseType(\""+en+"\", json);\r\n");
      write("    if ("+getElementName(en, false)+" != null)\r\n");
      write("      res.set"+upFirst(getElementName(en, false))+"("+getElementName(en, false)+");\r\n");
    } else {
        String prsr = null;
        String aprsr = null;
        String anprsr = null;
        BindingSpecification cd = definitions.getBindingByName(e.getBindingName());
        if (e.typeCode().equals("code") && cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList) {
          String en = typeNames.get(e); // getCodeListType(cd.getBinding());
          prsr = "parseEnumeration(json.get(\""+name+"\").getAsString(), "+en+".Null, new "+en.substring(0, en.indexOf("."))+"."+en.substring(en.indexOf(".")+1)+"EnumFactory())"; // en+".fromCode(parseString(xpp))";
          aprsr = "parseEnumeration(array.get(i).getAsString(), "+en+".Null, new "+en.substring(0, en.indexOf("."))+"."+en.substring(en.indexOf(".")+1)+"EnumFactory())"; // en+".fromCode(parseString(xpp))";
          anprsr = "parseEnumeration(null, "+en+".Null, new "+en.substring(0, en.indexOf("."))+"."+en.substring(en.indexOf(".")+1)+"EnumFactory())"; // en+".fromCode(parseString(xpp))";
          // parseEnumeration(xpp, Narrative.NarrativeStatus.additional, new Narrative.NarrativeStatusEnumFactory())
        } else {   
          String tn = typeName(root, e).replace(".", "");
          if (name.equals("extension")) {
            name = "extension";
            tn = "Extension";
          }
          if (tn.equals("char[]")) {
            prsr = "parseXhtml(json.get(\""+name+"\").getAsString())";
          } else if (tn.contains("Resource(")) {
            prsr = "parseResourceReference(json.getAsJsonObject(\""+name+"\"))";
            aprsr = "parseResourceReference(array.get(i).getAsJsonObject())";
            anprsr = "parseResourceReference(null)";
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
          write("      res.set"+upFirst(getElementName(name, false))+"("+prsr+");\r\n");
          if (isPrimitive(e)) {
            write("    if (json.has(\"_"+name+"\"))\r\n");
            write("      parseElementProperties(json.getAsJsonObject(\"_"+name+"\"), res.get"+upFirst(getElementName(name, false))+"());\r\n");
          }
        }
    }
  }

  private boolean isPrimitive(ElementDefn e) {
    return definitions.hasPrimitiveType(e.typeCode()) || "idref".equals(e.typeCode());
  }

  private String PrepGenericName(String tn) {
    int i = tn.indexOf('(');
    return tn.substring(0, i)+"_"+upFirst(tn.substring(i+1).replace(")", ""));
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
  
  private void finish() throws Exception {
    write("  @Override\r\n");
    write("  protected Resource parseResource(JsonObject json) throws Exception {\r\n");
    write("    String t = json.get(\"resourceType\").getAsString();\r\n");
    write("    if (Utilities.noString(t))\r\n");
    write("      throw new Exception(\"Unable to find resource type - maybe not a FHIR resource?\");\r\n");
    write("    "+reg.toString().substring(9));
    write("    else if (t.equals(\"Binary\"))\r\n");
    write("      return parseBinary(json);\r\n");
    write("    throw new Exception(\"Unknown.Unrecognised resource type\");\r\n");
    write("  }\r\n\r\n");
    write("  protected Type parseType(String prefix, JsonObject json) throws Exception {\r\n");
    write("    "+regt.toString().substring(9));
    write("    return null;\r\n");
    write("  }\r\n\r\n");

//    write("  public Element parseFragment(XmlPullParser xpp, String type) throws Exception {\r\n");
//    write("    "+regf.toString().substring(9));
//    write("    throw new Exception(\"Unknown type \"+type);\r\n");
//    write("  }\r\n\r\n");
//
//    write("  private boolean hasTypeName(JsonObject json, String prefix) {\r\n");
//    write("    "+regn.toString());
//    write("    return false;\r\n");
//    write("  }\r\n");
    
    
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

  private void scanNestedTypes(ElementDefn root, String path, ElementDefn e) throws Exception {
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
        else if (tr.isIdRef())
          tn ="String";
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
            scanNestedTypes(root, path, c);
          }
        }
      }
    } 
  }

  private String rootOf(String path) {
    int i = path.indexOf('.');
    return i == -1 ? path : path.substring(0, i);
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

  
}
