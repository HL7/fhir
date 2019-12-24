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
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.utilities.Utilities;

public class JavaParserRdfGenerator extends JavaBaseGenerator {
  public enum JavaGenClass { Structure, Type, Resource, AbstractResource, BackboneElement, Constraint }

  private Map<ElementDefn, String> typeNames = new HashMap<ElementDefn, String>();
  private List<String> typeNameStrings = new ArrayList<String>();
  private List<ElementDefn> enums = new ArrayList<ElementDefn>();
  private List<String> enumNames = new ArrayList<String>();
  private List<ElementDefn> strucs  = new ArrayList<ElementDefn>();

  private String context;

  private StringBuilder reg = new StringBuilder();
  private StringBuilder regt = new StringBuilder();
  private StringBuilder regt2 = new StringBuilder();
  private StringBuilder regf = new StringBuilder();
  private StringBuilder regn = new StringBuilder();
  private StringBuilder regtn = new StringBuilder();
  private StringBuilder regtp = new StringBuilder();
  private String genparam;
  private String mainName;

  public JavaParserRdfGenerator(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }

  public void generate(Definitions definitions, String version, Date genDate) throws Exception {

    this.definitions = definitions;

    start(version, genDate);

    //    genElement();
    //    generateEnumParser();
    //    
    //    for (DefinedCode dc : definitions.getPrimitives().values()) 
    //      generatePrimitive(dc);
    //    
    //    for (ElementDefn n : definitions.getInfrastructure().values()) {
    //      if (!n.getName().equals("Element") && !n.getName().equals("BackboneElement")) {
    //        generate(n, JavaGenClass.Structure);
    //        String t = upFirst(n.getName());
    //        regt.append("    else if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return parse"+t+"(xpp);\r\n");
    //        regt2.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+t+"(xpp);\r\n");
    //        //    regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    //        regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      }
    //    }
    //    
    //    for (ElementDefn n : definitions.getTypes().values()) {
    //      generate(n, JavaGenClass.Type);
    //      regt.append("    else if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regt2.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    //    }
    //
    //    for (ProfiledType n : definitions.getConstraints().values()) {
    //      generateConstraint(n);
    //      regt.append("    else if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regt2.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    //    }
    //    for (ElementDefn n : definitions.getStructures().values()) {
    //      generate(n, JavaGenClass.Structure);
    //      regt.append("    else if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regt2.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+n.getName()+"(xpp);\r\n");
    //      regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    //    }
    //    
    //    for (String s : definitions.getBaseResources().keySet()) {
    //      ResourceDefn n = definitions.getBaseResources().get(s);
    //      generate(n.getRoot(), n.isAbstract() ? JavaGenClass.AbstractResource : JavaGenClass.Resource);
    //      if (!n.isAbstract()) {
    //        reg.append("    else if (xpp.getName().equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(xpp);\r\n");
    //        regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(xpp);\r\n");
    //        regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    //      }
    //    }
    //    
    //    for (String s : definitions.sortedResourceNames()) {
    //      ResourceDefn n = definitions.getResources().get(s);
    //      generate(n.getRoot(), JavaGenClass.Resource);
    //      reg.append("    else if (xpp.getName().equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(xpp);\r\n");
    //      regf.append("    else if (type.equals(\""+n.getName()+"\"))\r\n      return parse"+javaClassName(n.getName())+"(xpp);\r\n");
    //      regn.append("    if (xpp.getName().equals(prefix+\""+n.getName()+"\"))\r\n      return true;\r\n");
    //    }
    //    
    //    for (DefinedCode cd : definitions.getPrimitives().values()) {
    //      String n = upFirst(cd.getCode());
    //      String t = n;
    ////      if (n.equals("String"))
    ////        t = "StringType";
    ////      if (n.equals("Uri"))
    ////        t = "URI";
    //      regt.append("    else if (xpp.getName().equals(prefix+\""+n+"\"))\r\n      return parse"+t+"(xpp);\r\n");
    //      regf.append("    else if (type.equals(\""+cd.getCode()+"\"))\r\n      return parse"+t+"(xpp);\r\n");
    //      regn.append("    if (xpp.getName().equals(prefix+\""+n+"\"))\r\n      return true;\r\n");
    //    }
    //    
    //    finishParser();
  }

  //  private void genElement() throws Exception {
  //    write("  protected boolean parseElementContent(int eventType, XmlPullParser xpp, Element res) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\"extension\")) \r\n");
  //    write("      res.getExtension().add(parseExtension(xpp));\r\n");
  //    write("    else\r\n");
  //    write("      return false;\r\n");
  //    write("      \r\n");
  //    write("    return true;\r\n");    
  //    write("  }\r\n");
  //    write("\r\n");
  //    write("  protected boolean parseBackboneContent(int eventType, XmlPullParser xpp, BackboneElement res) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\"modifierExtension\")) \r\n");
  //    write("      res.getModifierExtension().add(parseExtension(xpp));\r\n");
  //    write("    else\r\n");
  //    write("      return parseElementContent(eventType, xpp, res);\r\n");
  //    write("      \r\n");
  //    write("    return true;\r\n");    
  //    write("  }\r\n");
  //    write("\r\n");
  //  }
  //
  ////  private void genReference() throws Exception {
  ////    write("  private boolean parseResourceContent(int eventType, XmlPullParser xpp, Resource res) {\r\n");
  ////    write("    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\"language\")) { \r\n");
  ////    write("      res.setLanguageElement(parseCode(xpp));\r\n");
  ////    write("    } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\"text\")) {\r\n"); 
  ////    write("      res.setText(parseNarrative(xpp));\r\n");
  ////    write("    } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\"contained\")) {\r\n"); 
  ////    write("      next(xpp);\r\n");
  ////    write("      nextNoWhitespace(xpp);\r\n");
  ////    write("      res.getContained().add(parse(xpp));\r\n");
  ////    write("      if (xpp.getName() == null) {;\r\n");
  ////    write("        next(xpp);\r\n");
  ////    write("      };\r\n");
  ////    write("      if(xpp.getName() != null) {;\r\n");
  ////    write("        next(xpp);\r\n");
  ////    write("      };\r\n");
  ////    write("      nextNoWhitespace(xpp);\r\n");
  ////    write("    } else\r\n");
  ////    write("      return parseBackboneContent(eventType, xpp, res);\r\n");
  ////    write("      \r\n");
  ////    write("    return true;\r\n");    
  ////    write("  }\r\n");
  ////    write("\r\n");
  ////  }
  //
  //  private void generateEnumParser() throws Exception {
  //    write("  @SuppressWarnings(\"unchecked\")\r\n");
  //    write("  protected <E extends Enum<E>> Enumeration<E> parseEnumeration(XmlPullParser xpp, E item, EnumFactory e) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    Enumeration<E> res = new Enumeration<E>(e);\r\n");
  //    write("    parseElementAttributes(xpp, res);\r\n");
  //    write("    res.setValue((E) e.fromCode(xpp.getAttributeValue(null, \"value\")));\r\n");
  //    write("    next(xpp);\r\n");
  //    write("    int eventType = nextNoWhitespace(xpp);\r\n");
  //    write("    while (eventType != XmlPullParser.END_TAG) {\r\n");
  //    write("      if (!parseElementContent(eventType, xpp, res))\r\n");
  //    write("        unknownContent(xpp);\r\n");
  //    write("      eventType = nextNoWhitespace(xpp);\r\n");
  //    write("    }\r\n");
  //    write("    next(xpp);\r\n");
  //    write("    parseElementClose(res);\r\n");
  //    write("    return res;\r\n");
  //    write("  }\r\n");
  //    write("\r\n");
  //  }
  //
  private String getPrimitiveTypeModelName(String code) {
    if (code.equals("string"))
      return "StringType";
    if (definitions.hasPrimitiveType(code))
      return upFirst(code)+"Type";
    return upFirst(code);
  }

  //  private void generatePrimitive(DefinedCode dc) throws Exception {
  //    String tn = getPrimitiveTypeModelName(dc.getCode());
  //    write("  protected "+tn+" parse"+upFirst(dc.getCode())+"(XmlPullParser xpp) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    "+tn+" res = new "+tn+"(xpp.getAttributeValue(null, \"value\"));\r\n");
  //    write("    parseElementAttributes(xpp, res);\r\n");
  //    write("    next(xpp);\r\n");
  //    write("    int eventType = nextNoWhitespace(xpp);\r\n");
  //    write("    while (eventType != XmlPullParser.END_TAG) {\r\n");
  //    write("      if (!parseElementContent(eventType, xpp, res))\r\n");
  //    write("        unknownContent(xpp);\r\n");
  //    write("      eventType = nextNoWhitespace(xpp);\r\n");
  //    write("    }\r\n");
  //    write("    next(xpp);\r\n");
  //    write("    parseElementClose(res);\r\n");
  //    write("    return res;\r\n");
  //    write("  }\r\n");
  //    write("\r\n");
  //    regt.append ("    else if (xpp.getName().equals(prefix+\""+dc.getCode()+"\"))\r\n      return parse"+upFirst(dc.getCode())+"(xpp);\r\n");
  //    regt2.append("    else if (type.equals(\""+dc.getCode()+"\"))\r\n      return parse"+upFirst(dc.getCode())+"(xpp);\r\n");
  //  }
  //
  private String upFirst(String n) {
    return Utilities.noString(n) ? "" : n.substring(0,1).toUpperCase() + n.substring(1);
  }

  private void start(String version, Date genDate) throws Exception {
    write("package org.hl7.fhir.r5.formats;\r\n");
    write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
    write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    write("import org.hl7.fhir.r5.model.*;\r\n");
    write("import org.xmlpull.v1.*;\r\n");
    write("import org.hl7.fhir.utilities.Utilities;\r\n");
    write("import org.hl7.fhir.exceptions.FHIRFormatError;\r\n");
    write("import org.hl7.fhir.exceptions.FHIRException;\r\n");
    write("import org.hl7.fhir.r5.utils.formats.Turtle.Complex;\r\n");
    write("import java.io.IOException;\r\n");
    //  write("import java.util.*;\r\n");
    write("\r\n");
    write("public class RdfParser extends RdfParserBase {\r\n");
    write("\r\n");
    write("  public RdfParser() {\r\n");
    write("    super();\r\n");
    write("  }\r\n\r\n");
    write("  public RdfParser(boolean allowUnknownContent) {\r\n");
    write("    super();\r\n");
    write("    setAllowUnknownContent(allowUnknownContent);\r\n");
    write("  }\r\n\r\n");
    write("\r\n");
  }


  //  private void generate(ElementDefn n, JavaGenClass clss) throws Exception {
  //    typeNames.clear();
  //    typeNameStrings.clear();
  //    enums.clear();
  //    strucs.clear();
  //    enumNames.clear();
  //    String nn = javaClassName(n.getName());
  //    for (ElementDefn e : n.getElements()) {
  //        scanNestedTypes(n, nn, e);
  //    }
  //    context = nn;
  //
  //    if (clss == JavaGenClass.AbstractResource)
  //      genInnerAbstract(n);
  //    else
  //      genInner(n, clss);
  //    
  //    for (ElementDefn e : strucs) {
  //      genInner(e, clss == JavaGenClass.Resource ? JavaGenClass.BackboneElement : JavaGenClass.Structure);
  //    }
  //
  //  }
  //
  private String javaClassName(String name) {
    if (name.equals("List"))
      return "ListResource";
    else 
      return name;
  }

  //  private void generateConstraint(ProfiledType cd) throws Exception {
  //    typeNames.clear();
  //    typeNameStrings.clear();
  //    enums.clear();
  //    strucs.clear();
  //    enumNames.clear();
  //    context = cd.getName();
  //    ElementDefn n = definitions.getTypes().get(cd.getBaseType());
  //    
  //    typeNames.put(n, cd.getName());
  //    for (ElementDefn e : n.getElements()) {
  //        scanNestedTypes(n, n.getName(), e);
  //    }
  //
  //    genInner(n, JavaGenClass.Constraint);
  //    
  //    for (ElementDefn e : strucs) {
  //      genInner(e, JavaGenClass.Structure);
  //    }
  //
  //  }
  //
  //  private void genInner(ElementDefn n, JavaGenClass clss) throws IOException, Exception {
  //    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());
  //    boolean bUseOwner = false;
  //    
  //    String pn = tn.contains("<") ? "\""+tn.substring(tn.indexOf('<')+1).replace(">", "") + "\"" : "";
  //    
  //    if (tn.contains(".")) {
  //      write("  protected "+tn+" parse"+upFirst(tn).replace(".", "")+"(XmlPullParser xpp, "+pathClass(tn)+" owner) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //      write("    "+tn+" res = new "+tn+"("+pn+");\r\n");
  //      bUseOwner = true;
  //    } else {
  //      write("  protected "+tn+" parse"+upFirst(tn).replace(".", "")+"(XmlPullParser xpp) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //      write("    "+tn+" res = new "+tn+"("+pn+");\r\n");
  //    }
  //    if (clss == JavaGenClass.Resource)
  //      write("    parse"+n.typeCode()+"Attributes(xpp, res);\r\n");
  //    else if (clss == JavaGenClass.BackboneElement)
  //      write("    parseBackboneAttributes(xpp, res);\r\n");
  //    else if (clss == JavaGenClass.Type && !tn.contains("."))
  //      write("    parseTypeAttributes(xpp, res);\r\n");
  //    else
  //      write("    parseElementAttributes(xpp, res);\r\n");
  //    for (ElementDefn e : n.getElements()) {
  //      if (e.typeCode().equals("xml:lang")) {
  //        write("    if (xpp.getAttributeValue(null, \"xml:lang\") != null)\r\n");
  //        write("        res.set"+upFirst(getElementName(e.getName(), true))+"(Factory.newCode(xpp.getAttributeValue(null, \"xml:Id\")));\r\n");
  //      } else if (e.isXmlAttribute()) {
  //        write("    if (xpp.getAttributeValue(null, \""+e.getName()+"\") != null)\r\n");
  //        write("        res.set"+upFirst(getElementName(e.getName(), true))+"(xpp.getAttributeValue(null, \""+e.getName()+"\"));\r\n");        
  //      }
  //    }    
  //    write("    next(xpp);\r\n");
  //    write("    int eventType = nextNoWhitespace(xpp);\r\n");
  //    write("    while (eventType != XmlPullParser.END_TAG) {\r\n");
  //    boolean first = true;
  //    for (ElementDefn e : n.getElements()) {
  //      if (!e.typeCode().equals("xml:lang") && !e.isXmlAttribute()) {
  //        genElement(n, e, first, clss, bUseOwner);
  //        first = false;
  //      }
  //    }
  //    if (clss == JavaGenClass.Resource)
  //      write("      } else if (!parse"+n.typeCode()+"Content(eventType, xpp, res))\r\n");
  //    else if (clss == JavaGenClass.BackboneElement)
  //        write("      } else if (!parseBackboneContent(eventType, xpp, res))\r\n");
  //      else
  //      write("      } else if (!parseElementContent(eventType, xpp, res))\r\n");
  //    write("        unknownContent(xpp);\r\n");
  //    write("      eventType = nextNoWhitespace(xpp);\r\n");
  //    write("    }\r\n");
  //    write("    next(xpp);\r\n");
  //    write("    parseElementClose(res);\r\n");
  //    write("    return res;\r\n");
  //    write("  }\r\n\r\n");    
  //  }
  //
  //  private void genInnerAbstract(ElementDefn n) throws IOException, Exception {
  //    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());
  //    boolean bUseOwner = false;
  //    
  //    String pn = tn.contains("<") ? "\""+tn.substring(tn.indexOf('<')+1).replace(">", "") + "\"" : "";
  //    
  //    write("  protected void parse"+upFirst(tn).replace(".", "")+"Attributes(XmlPullParser xpp, "+tn+" res) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    if (!Utilities.noString(n.typeCode()))
  //      write("    parse"+n.typeCode()+"Attributes(xpp, res);\r\n");
  //    else
  //      write("    if (!comments.isEmpty()) {\r\n      res.getFormatCommentsPre().addAll(comments);\r\n      comments.clear();\r\n     }\r\n");
  //    
  //    for (ElementDefn e : n.getElements()) {
  //      if (e.isXmlAttribute()) {
  //        write("    if (xpp.getAttributeValue(null, \""+e.getName()+"\") != null)\r\n");
  //        write("        res.set"+upFirst(getElementName(e.getName(), true))+"(xpp.getAttributeValue(null, \""+e.getName()+"\"));\r\n");        
  //      }
  //    }    
  //    write("  }\r\n\r\n");    
  //
  //    write("  protected boolean parse"+upFirst(tn).replace(".", "")+"Content(int eventType, XmlPullParser xpp, "+tn+" res) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    boolean first = true;
  //    for (ElementDefn e : n.getElements()) {
  //      if (!e.typeCode().equals("xml:lang") && !e.isXmlAttribute()) {
  //        genElement(n, e, first, JavaGenClass.BackboneElement, bUseOwner);
  //        first = false;
  //      }
  //    }
  //    write("    } else\r\n"); 
  //    if (Utilities.noString(n.typeCode()))
  //      write("        return false;\r\n"); 
  //    else
  //      write("    return parse"+n.typeCode()+"Content(eventType, xpp, res);\r\n");
  //    write("    return true;\r\n");
  //    write("  }\r\n\r\n");    
  //  }
  //
  //  private String pathClass(String tn) {
  //    return tn.substring(0, tn.indexOf('.'));
  //  }
  //
  //  private String pathNode(String tn) {
  //    return tn.substring(tn.indexOf('.')+1);
  //  }
  //
  //  private void genElement(ElementDefn root, ElementDefn e, boolean first, JavaGenClass clss, boolean bUseOwner) throws Exception {
  //    String name = e.getName();
  //    if (name.endsWith("[x]") || name.equals("[type]")) {
  //      String en = name.endsWith("[x]") && !name.equals("[x]") ? name.replace("[x]", "") : "value";
  //      String pfx = name.endsWith("[x]") && !name.equals("[x]") ? name.replace("[x]", "") : "";
  //      write("      "+(!first ? "} else " : "")+"if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, \""+pfx+"\")) {\r\n");
  //      write("        res.set"+upFirst(getElementName(en, false))+"(parseType(\""+en+"\", xpp));\r\n");
  //    } else {
  //      String prsr = null;
  //      BindingSpecification cd = e.getBinding();
  //      if (e.typeCode().equals("code") && cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
  //        String en = typeNames.get(e); // getCodeListType(cd.getBinding());
  //        if (e.getBinding().isShared())
  //          en = "Enumerations."+e.getBinding().getValueSet().getName();
  //        prsr = "parseEnumeration(xpp, "+en+".NULL, new "+en.substring(0, en.indexOf("."))+"."+en.substring(en.indexOf(".")+1)+"EnumFactory())"; // en+".fromCode(parseString(xpp))";
  //        // parseEnumeration(xpp, Narrative.NarrativeStatus.additional, new Narrative.NarrativeStatusEnumFactory())
  //      } else {   
  //        String tn = typeName(root, e).replace(".", "");
  //        if (name.equals("extension")) {
  //          name = "extension";
  //          tn = "Extension";
  //        }
  //        if (tn.equals("char[]"))
  //          tn = "xhtml";
  //        if (tn.contains("Reference("))
  //          prsr = "parseReference(xpp)";
  //        else if (tn.contains("("))
  //          prsr = "parse"+tn+"(xpp)";
  //        else if (tn.startsWith(context) && !tn.equals(context) && !definitions.hasType(tn)) {
  //          if (bUseOwner)
  //            prsr = "parse"+upFirst(tn)+"(xpp, owner)";
  //          else
  //            prsr = "parse"+upFirst(tn)+"(xpp, res)";
  //        } else
  //          if ("Uri".equalsIgnoreCase(tn))
  //            prsr = "parseUri(xpp)";
  //          else if ("Instant".equals(tn))
  //            prsr = "parseInstant(xpp)";
  //          else if (tn.equals("Resource") || tn.equals("DomainResource"))
  //            prsr = "parse"+upFirst(tn)+"Contained(xpp)";
  //          else
  //            prsr = "parse"+upFirst(tn)+"(xpp)";
  //      }
  //      
  ////      if (name.equals("extension")) {
  ////        write("      "+(!first ? "} else " : "")+"if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\"extension\")) {\r\n");
  ////        write("        eventType = nextNoWhitespace(xpp);\r\n");
  ////        write("        while (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\"extension\")) {\r\n");
  ////        if (clss == JavaGenClass.Type) 
  ////          write("          res.getExtensions().add(parseExtension(xpp));\r\n");
  ////        else
  ////          write("          res.getExtension().add(parseExtension(xpp));\r\n");
  ////        write("          next(xpp);\r\n");
  ////        write("          eventType = nextNoWhitespace(xpp);\r\n");
  ////        write("        }\r\n");
  ////        write("        if (eventType != XmlPullParser.END_TAG || !xpp.getName().equals(\""+name+"\"))\r\n");
  ////        write("          throw new FHIRFormatError(\"XML Error in "+root.getName()+": found '\"+xpp.getName()+\"'\");\r\n");          
  ////      } else 
  //      if (e.unbounded()) {
  //        write("      "+(!first ? "} else " : "")+"if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\""+name+"\")) {\r\n");
  //        write("        res.get"+upFirst(name)+"().add("+prsr+");\r\n");
  //      } else {
  //        write("      "+(!first ? "} else " : "")+"if (eventType == XmlPullParser.START_TAG && xpp.getName().equals(\""+name+"\")) {\r\n");
  //        write("        res.set"+upFirst(getElementName(name, false))+(isJavaPrimitive(e) ? "Element" : "")+"("+prsr+");\r\n");
  //      }
  //    }
  //  }
  //
  //  private String typeName(ElementDefn root, ElementDefn elem) throws Exception {
  //    String t = elem.typeCode();
  //    if (elem.usesCompositeType()) { 
  //      if (typeNames.containsKey(elem) && typeNames.get(elem) != null)
  //        return typeNames.get(elem);
  //      else  
  //        return root.getName();      
  //    } else if (elem.getTypes().size() == 0) {
  //        return typeNames.get(elem);
  //    } else if (typeNames.containsKey(elem))
  //      return typeNames.get(elem);
  //    else
  //      return upFirst(t);
  //  }
  //  
  //  private void finishParser() throws Exception {
  //    write("  @Override\r\n");
  //    write("  protected Resource parseResource(XmlPullParser xpp) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    "+reg.toString().substring(9));
  //    write("    else if (xpp.getName().equals(\"Binary\"))\r\n");
  //    write("      return parseBinary(xpp);\r\n");
  //    write("    throw new FHIRFormatError(\"Unknown resource type \"+xpp.getName()+\"\");\r\n");
  //    write("  }\r\n\r\n");
  //    write("  protected Type parseType(String prefix, XmlPullParser xpp) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    "+regt.toString().substring(9));
  //    write("    throw new FHIRFormatError(\"Unknown type \"+xpp.getName());\r\n");
  //    write("  }\r\n\r\n");
  //    write("  protected Type parseType(XmlPullParser xpp, String type) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    "+regt2.toString().substring(9));
  //    write("    throw new FHIRFormatError(\"Unknown type \"+type);\r\n");
  //    write("  }\r\n\r\n");
  //
  //    write("  public Base parseFragment(XmlPullParser xpp, String type) throws XmlPullParserException, IOException, FHIRFormatError {\r\n");
  //    write("    "+regf.toString().substring(9));
  //    write("    throw new FHIRFormatError(\"Unknown type \"+type);\r\n");
  //    write("  }\r\n\r\n");
  //
  //    write("  private boolean nameIsTypeName(XmlPullParser xpp, String prefix) {\r\n");
  //    write("    "+regn.toString());
  //    write("    return false;\r\n");
  //    write("  }\r\n");
  //    
  //  }
  //
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

  //  private void scanNestedTypes(ElementDefn root, String path, ElementDefn e) throws Exception {
  //    String tn = null;
  //    if (e.typeCode().equals("code") && e.hasBinding()) {
  //      BindingSpecification cd = e.getBinding();
  //      if (cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
  //        tn = getCodeListType(cd.getValueSet().getName());
  //        if (!enumNames.contains(tn)) {
  //          enumNames.add(tn);
  //          enums.add(e);
  //        }
  //        typeNames.put(e,  rootOf(path)+"."+upFirst(tn));
  //      }
  //    }
  //    if (tn == null) {
  //      if (e.getTypes().size() > 0 && !e.usesCompositeType()) {
  //        tn = e.typeCode();
  //        TypeRef tr = e.getTypes().get(0);
  //        if (tr.isUnboundGenericParam())
  //          tn = genparam;
  //        else if (tr.isXhtml()) 
  //          tn = "char[]";
  //        else if (tr.isWildcardType())
  //          tn ="Type";
  //        else if (tn.equals("string"))
  //          tn = "String";
  //        typeNames.put(e,  tn);
  //      } else {
  //        if (e.usesCompositeType()) {
  //          tn = typeNames.get(getElementForPath(root, e.typeCode().substring(1)));
  //          typeNames.put(e,  tn);
  //        } else {
  //        	if (e.getDeclaredTypeName() != null) 
  //				tn = e.getDeclaredTypeName();
  //			else
  //				tn = upFirst(e.getName());
  //          if (tn.equals("Element"))
  //            tn = "Element_";
  //          if (!e.getName().equals("extension"))
  //            strucs.add(e);
  //          if (typeNameStrings.contains(tn)) {
  //            char i = 'A';
  //            while (typeNameStrings.contains(tn+i))
  //              i++;
  //            tn = tn + i;
  //          }
  //          typeNameStrings.add(tn);
  //          tn = path+"."+tn;
  //          typeNames.put(e,  tn);
  //          for (ElementDefn c : e.getElements()) {
  //            scanNestedTypes(root, path, c);
  //          }
  //        }
  //      }
  //    } 
  //  }
  //
  private void scanNestedTypesComposer(ElementDefn root, String path, ElementDefn e) throws Exception {
    String tn = null;
    if (e.typeCode().equals("code") && e.hasBinding()) {
      BindingSpecification cd = e.getBinding();
      if (cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
        tn = getCodeListType(cd.getValueSet().getName());
        if (!enumNames.contains(tn)) {
          enumNames.add(tn);
          enums.add(e);
        }
        typeNames.put(e,  rootOf(path)+"."+tn);
      }
      if (cd != null && isEnum(cd)) {
        tn = getCodeListType(cd.getName());
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
        //        else if (tn.equals("string"))
        //          tn = "StringType";
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

  private ElementDefn getElementForPath(ElementDefn root, String pathname) throws Exception {
    String[] path = pathname.split("\\.");
    if (!path[0].equals(root.getName()))
      throw new Exception("Element Path '"+pathname+"' is not legal in this context");
    ElementDefn res = root;
    for (int i = 1; i < path.length; i++)
    {
      String en = path[i];
      if (en.length() == 0)
        throw new Exception("Improper path "+pathname);
      ElementDefn t = res.getElementByName(definitions, en, true, false);
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
    genparam = null;
    mainName = null;

    genElementComposer();
    generateEnumComposer();
    for (DefinedCode dc : definitions.getPrimitives().values()) {
      String nn = upFirst(dc.getCode());
//      if (nn.equals("Code"))
//        regtn.append("    else if (value instanceof "+nn+"Type)\r\n      compose"+nn+"(parent, parentType, name, ("+nn+"Type)value, false, index);\r\n");
//      else
        regtn.append("    else if (value instanceof "+nn+"Type)\r\n      compose"+nn+"(parent, parentType, name, ("+nn+"Type)value, index);\r\n");
      generatePrimitiveComposer(dc);
    }

    for (ElementDefn n : definitions.getInfrastructure().values()) {
      if (!n.getName().equals("Element") && !n.getName().equals("BackboneElement")) {
        String nn = javaClassName(n.getName());
        regtn.append("    else if (value instanceof "+nn+")\r\n      compose"+nn+"(parent, parentType, name, ("+nn+")value, index);\r\n");
        generateComposer(n, JavaGenClass.Structure);
      }
    }

    for (ElementDefn n : definitions.getTypes().values()) {
      generateComposer(n, JavaGenClass.Type);
      String nn = javaClassName(n.getName());
//      if (nn.equals("Coding"))
//        regtn.append("    else if (value instanceof "+nn+")\r\n      compose"+nn+"(parent, parentType, name, ("+nn+")value, false, index);\r\n");
//      else
        regtn.append("    else if (value instanceof "+nn+")\r\n      compose"+nn+"(parent, parentType, name, ("+nn+")value, index);\r\n");
    }

    for (String s : definitions.getBaseResources().keySet()) {
      ResourceDefn n = definitions.getBaseResources().get(s);
      generateComposer(n.getRoot(), n.isAbstract() ? JavaGenClass.AbstractResource : JavaGenClass.Resource);
      if (!n.isAbstract()) {
        String nn = javaClassName(n.getName());
        reg.append("    else if (resource instanceof "+nn+")\r\n      compose"+nn+"(parent, null, \""+nn+"\", ("+nn+")resource, -1);\r\n");
      }
    }

    for (String s : definitions.sortedResourceNames()) {
      ResourceDefn n = definitions.getResources().get(s);
      generateComposer(n.getRoot(), JavaGenClass.Resource);
      String nn = javaClassName(n.getName());
      reg.append("    else if (resource instanceof "+nn+")\r\n      compose"+nn+"(parent, null, \""+nn+"\", ("+nn+")resource, -1);\r\n");
    }
    finishComposer();
  }

  private void genElementComposer() throws Exception {
    write("  protected void composeElement(Complex t, String parentType, String name, Element element, int index) {\r\n");
    write("    if (element == null) \r\n");
    write("      return;\r\n");
    write("    if (index > -1)\r\n");
    write("      t.predicate(\"fhir:index\", Integer.toString(index));\r\n");
    write("    if (element.hasIdElement())\r\n");
    write("      composeString(t, \"Element\", \"id\", element.getIdElement(), -1);\r\n");
    write("    for (int i = 0; i < element.getExtension().size(); i++)\r\n");
    write("      composeExtension(t, \"Element\", \"extension\", element.getExtension().get(i), i);\r\n");
    write("  }\r\n");
    write("\r\n");


    write("  protected void composeBackboneElement(Complex t, String tType, String name, BackboneElement element, int index) {\r\n");
    write("    composeElement(t, tType, name, element, index);\r\n");    
    write("    for (int i = 0; i < element.getModifierExtension().size(); i++)\r\n");
    write("      composeExtension(t, \"Element\", \"modifierExtension\", element.getModifierExtension().get(i), i);\r\n");
    write("  }\r\n");
    write("\r\n");
  }

  private void generateEnumComposer() throws Exception {
    write("  private void composeEnum(Complex parent, String parentType, String name, Enumeration<? extends Enum> value, int index) {\r\n");
    write("    if (value == null)\r\n");
    write("      return;\r\n");
    write("    Complex t = parent.predicate(\"fhir:\"+parentType+\".\"+name);\r\n");
//    write("  t.predicate(\"a\", \"fhir:code\");\r\n");
//    write("  t.predicate(\"a\", \"fhir:ConceptBase\");\r\n");
//    write("  Complex c = t.predicate(\"fhir:ConceptBase.coding\");\r\n");
//    write("  c.predicate(\"a\", \"fhir:CodingBase\");\r\n");
//    write("  Complex cc = c.predicate(\"fhir:CodingBase.code\");\r\n");
//    write("  cc.predicate(\"a\", \"fhir:codeBase\");\r\n");
//    write("  cc.predicate(\"fhir:value\", ttlLiteral(value.asStringValue()));\r\n");
//    write("  Complex cs = c.predicate(\"fhir:CodingBase.system\");\r\n");
//    write("  cs.predicate(\"a\", \"fhir:string\");\r\n");
    write("    t.predicate(\"fhir:value\", ttlLiteral(value.asStringValue()));\r\n");
    write("    composeElement(t, parentType, name, value, index);\r\n");
    write("    decorateCode(t, value);\r\n");
    write("  }\r\n\r\n");
    write("\r\n");
  }


  
  private void generatePrimitiveComposer(DefinedCode dc) throws Exception {
//    if (dc.getCode().equals("code")) {
//      write("  private void composeCode(Complex parent, String parentType, String name, CodeType value, boolean inCodingBase, int index) {\r\n");
//      write("  if (value == null)\r\n");
//      write("    return;\r\n");
//      write("  if (!inCodingBase) {\r\n");
//      write("    Complex t = parent.predicate(\"fhir:\"+parentType+\".\"+name);\r\n");
//      write("    t.predicate(\"a\", \"fhir:code\");\r\n");
//      write("    t.predicate(\"a\", \"fhir:ConceptBase\");\r\n");
//      write("    Complex c = t.predicate(\"fhir:ConceptBase.coding\");\r\n");
//      write("    c.predicate(\"a\", \"fhir:CodingBase\");\r\n");
//      write("    Complex cc = c.predicate(\"fhir:CodingBase.code\");\r\n");
//      write("    cc.predicate(\"a\", \"fhir:codeBase\");\r\n");
//      write("    cc.predicate(\"fhir:value\", ttlLiteral(value.asStringValue()));\r\n");
//      write("    composeElement(t, parentType, name, value, index);\r\n");
//      write("  } else {\r\n\r\n");
//    } else {
      write("  protected void compose"+upFirst(dc.getCode())+"(Complex parent, String parentType, String name, "+upFirst(dc.getCode())+"Type value, int index) {\r\n");
      write("    if (value == null)\r\n");
      write("      return;\r\n");
//    }
    write("    Complex t = parent.predicate(\"fhir:\"+parentType+\".\"+name);\r\n");
//    write("    t.predicate(\"a\", \"fhir:"++"\");\r\n");
    write("    t.predicate(\"fhir:value\", ttlLiteral(value.asStringValue()));\r\n");
    write("    composeElement(t, parentType, name, value, index);\r\n");
    if (dc.getCode().equals("code")) {
      write("    decorateCode(t, value);\r\n");
//      write("    }    \r\n");
    }
    write("  }\r\n");
    write("\r\n");
  }

  private void generateComposer(ElementDefn n, JavaGenClass type) throws Exception {
    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    String nn = javaClassName(n.getName());
    mainName = nn;
    for (ElementDefn e : n.getElements()) {
      scanNestedTypesComposer(n, nn, e);
    }
    context = nn;

    if (type == JavaGenClass.AbstractResource)
      genInnerAbstractComposer(n);
    else
      genInnerComposer(n, n, type);

    for (ElementDefn e : strucs) {
      genInnerComposer(n, e, (type == JavaGenClass.Resource || type == JavaGenClass.AbstractResource) ?  JavaGenClass.BackboneElement : JavaGenClass.Structure);
    }

  }

  private void genInnerComposer(ElementDefn root, ElementDefn n, JavaGenClass type) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());

//    if (tn.equals("Coding"))
//      write("  protected void compose"+upFirst(tn).replace(".", "")+"(Complex parent, String parentType, String name, "+tn+" element, boolean inCodingBase, int index) {\r\n");
//    else
      write("  protected void compose"+upFirst(tn).replace(".", "")+"(Complex parent, String parentType, String name, "+tn+" element, int index) {\r\n");
    write("    if (element == null) \r\n");
    write("      return;\r\n");
    write("    Complex t;\r\n");

    write("    if (Utilities.noString(parentType))\r\n");
    write("      t = parent;\r\n");
    write("    else {\r\n");
    write("      t = parent.predicate(\"fhir:\"+parentType+'.'+name);\r\n");
//    if (tn.equals("Coding")) {
//      write("      if (inCodingBase)\r\n");
//      write("        t.predicate(\"a\", \"fhir:CodingBase\");\r\n");
//      write("      else\r\n");
//
//      write("        t.predicate(\"a\", \"fhir:"+n.getName()+"\");\r\n");
//    } else
//      write("      t.predicate(\"a\", \"fhir:"+n.getName()+"\");\r\n");
//    if (tn.equals("CodableConcept")) 
//      write("      t.predicate(\"a\", \"fhir:ConceptBase\");\r\n");
    write("    }\r\n");

//    if (tn.equals("Coding")) {
//      write("    if (!inCodingBase) {\r\n");
//      write("      t.predicate(\"a\", \"fhir:ConceptBase\");\r\n");
//      write("      t = t.predicate(\"fhir:ConceptBase.coding\");\r\n");
//      write("      t.predicate(\"a\", \"fhir:CodingBase\");\r\n");
//      write("    }\r\n");
//      tn = "CodingBase";
//    }

    if (type == JavaGenClass.Resource) 
      write("    compose"+n.typeCode()+"(t, \""+n.getName()+"\", name, element, index);\r\n");
    else if (type == JavaGenClass.BackboneElement) 
      write("    composeBackboneElement(t, \""+n.getName()+"\", name, element, index);\r\n");
    else
      write("    composeElement(t, \""+n.getName()+"\", name, element, index);\r\n");

    if (tn.equals("Coding")) 
      write("    decorateCoding(t, element);\r\n");
    else if (tn.equals("CodeableConcept")) 
      write("    decorateCodeableConcept(t, element);\r\n");

    for (ElementDefn e : n.getElements()) {
      genElement(root, n, e, type);
    }
    write("  }\r\n\r\n");    
  }

  private void genInnerAbstractComposer(ElementDefn n) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());

    write("  protected void compose"+upFirst(tn ).replace(".", "")+"(Complex t, String parentType, String name, "+tn+" element, int index) {\r\n");
    if (!Utilities.noString(n.typeCode()))
      write("    compose"+n.typeCode()+"(t, parentType, name, element, index);\r\n");

    for (ElementDefn e : n.getElements()) {
      genElement(n, n, e, JavaGenClass.AbstractResource);
    }
    write("  }\r\n\r\n");    
  }

  private void genElement(ElementDefn root, ElementDefn parent, ElementDefn e, JavaGenClass type) throws Exception {
    String name = e.getName();
    String gname = "get"+upFirst(checkJavaReservedWord(name))+"()";
    String tname = upFirst(e.typeCode());
    if (tname.startsWith("@")) {
      ElementDefn tgt = getElementForPath(root, e.typeCode().substring(1)); 
      tname = typeNames.get(tgt).replace(".", "");
    } else if (Utilities.noString(tname) && typeNames.containsKey(e)) {
      tname = typeNames.get(e).replace(".", "");
    } else if (tname.contains("("))
      tname = tname.substring(0, tname.indexOf("("));
    if (name.endsWith("[x]") || e.getTypes().size() > 1 || e.typeCode().equals("*")) { 
      tname = "Type";
      if (name.endsWith("[x]"))
        name = name.substring(0, name.length()-3);
      gname = "get"+upFirst(name)+"()";
    } else if (isJavaPrimitive(e) || e.typeCode().startsWith("canonical(")) {
      BindingSpecification cd = e.getBinding();
      if (e.typeCode().equals("code") && cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList)
        tname = "Enum"; 
      if (e.typeCode().equals("code") && cd != null && isEnum(cd))
        tname = "Enum"; 
      gname = "get"+upFirst(checkJavaReservedWord(name))+"Element()";
    }
    else if (definitions.getConstraints().containsKey(tname)) 
      tname = definitions.getConstraints().get(tname).getBaseType();

    if (root.getName().equals("Reference") && e.getName().equals("reference"))
      gname = "get"+upFirst(checkJavaReservedWord(name))+"Element_()"; // special case

    String cp = ""; // tname.equals("Code") || tname.equals("Coding") ? (root.getName().equals("CodeableConcept") || root.getName().equals("Coding") ? "false, " : "true, ") : "";
    if (e.unbounded()) {
      if (gname.endsWith("Element()") && !gname.equals("getElement()"))
        gname = gname.substring(0, gname.length()-9)+"()";
      write("    for (int i = 0; i < element."+gname+".size(); i++)\r\n");
      write("      compose"+tname+"(t, \""+root.getName()+"\", \""+name+"\", element."+gname+".get(i), "+cp+"i);\r\n");
    } else {
      write("    if (element.has"+gname.substring(3).replace("ReferenceElement_", "ReferenceElement")+")\r\n");
      write("      compose"+tname+"(t, \""+root.getName()+"\", \""+name+"\", element."+gname+", "+cp+"-1);\r\n");
    }
  }

  private String checkJavaReservedWord(String name) {
    if (Utilities.existsInList(name, "class"))
      return name+"_";
    else
      return name;
  }

  private boolean isPrimitive(ElementDefn e) {
    return definitions.hasPrimitiveType(e.typeCode());
  }


  private String prepEnumName(String en) {
    String[] parts = en.split("\\.");
    if (parts.length == 1)
      return upFirst(parts[0]);
    else
      return upFirst(parts[0])+'.'+upFirst(parts[1]);
  }


  private String leaf(String tn) {
    return tn.startsWith("java.lang.") ? tn.substring(10) : tn;
  }

  private String PrepGenericTypeName(String tn) {
    int i = tn.indexOf('(');
    return tn.substring(0, i)+"<"+upFirst(tn.substring(i+1).replace(")", ">"));
  }

  private String typeName(ElementDefn root, ElementDefn elem, JavaGenClass type, boolean formal) {
    String t = elem.typeCode();
    if ((type == JavaGenClass.Type || type == JavaGenClass.Constraint) && definitions.getPrimitives().containsKey(t)) {
      //      if (t.equals("boolean"))
      //        return "java.lang.Boolean";
      //      else if (t.equals("integer"))
      //        return "Integer";
      //      else if (t.equals("integer64"))
      //        return "Integer64?";
      //      else if (t.equals("decimal"))
      //        return "Decimal";
      //      else if (t.equals("base64Binary"))
      //        return "Base64Binary";
      //      else if (t.equals("instant"))
      //        return "Instant";
      //      else if (t.equals("uri"))
      //        return "Uri";
      //      else 
      //        return "String";
      //      else if (t.equals("string"))
      //        return "StringType";
      //      else
      return upFirst(t);
    } else if (elem.usesCompositeType()) { 
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
    write("  protected void composeResource(Complex parent, Resource resource) {\r\n");
    write("    "+reg.toString().substring(9));
    write("    else\r\n");
    write("      throw new Error(\"Unhandled resource type \"+resource.getClass().getName());\r\n");
    write("  }\r\n\r\n");

    write("  protected void composeType(Complex parent, String parentType, String name, Type value, int index) {\r\n");
    write("    if (value == null)\r\n");
    write("      return;\r\n");
    write(regtn.toString());
    write("    else\r\n");
    write("      throw new Error(\"Unhandled type\");\r\n");
    write("  }\r\n\r\n");
    //
    //    write("  private boolean nameIsTypeName(XmlPullParser xpp, String prefix) {\r\n");
    //    write("    "+regn.toString());
    //    write("    return false;\r\n");
    //    write("  }\r\n");
    //    
  }


  public void finish() throws IOException {
    write("}\r\n");
    write("\r\n");
    flush();
  }
}
