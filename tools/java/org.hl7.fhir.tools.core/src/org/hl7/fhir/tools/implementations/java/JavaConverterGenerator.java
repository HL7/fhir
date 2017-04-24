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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r4.model.Enumerations.BindingStrength;
import org.hl7.fhir.utilities.Utilities;

public class JavaConverterGenerator extends JavaBaseGenerator {
  public enum JavaGenClass { Structure, Type, Resource, AbstractResource, BackboneElement, Constraint }

  private Map<ElementDefn, String> typeNames = new HashMap<ElementDefn, String>();
  private List<String> typeNameStrings = new ArrayList<String>();
  private List<ElementDefn> enums = new ArrayList<ElementDefn>();
  private List<String> elist = new ArrayList<String>();
  private List<String> enumNames = new ArrayList<String>();
  private List<ElementDefn> strucs  = new ArrayList<ElementDefn>();
  private boolean doneQ;

  private String genparam;

  public JavaConverterGenerator(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }

  public void generate(Definitions definitions, String version, Date genDate) throws Exception {

    this.definitions = definitions;
    start(version, genDate);
    genVersionCommon();
    for (String s : sorted(definitions.getPrimitives().keySet())) {
      genVersionConvertor(definitions.getPrimitives().get(s));
    }
    for (String s : sorted(definitions.getInfrastructure().keySet())) {
      generate(definitions.getInfrastructure().get(s), JavaGenClass.Type, null);
    }
    for (String s : sorted(definitions.getTypes().keySet())) {
      generate(definitions.getTypes().get(s), JavaGenClass.Type, null);
    }
    for (String s : sorted(definitions.getStructures().keySet())) {
      generate(definitions.getStructures().get(s), JavaGenClass.Type, null);
    }
    for (String s : sorted(definitions.getConstraints().keySet())) {
      generate(definitions.getConstraints().get(s));
    }
    genTypeConvertor();

    for (String s : sorted(definitions.getBaseResources().keySet())) {
      ResourceDefn n = definitions.getBaseResources().get(s);
      generate(n.getRoot(), n.isAbstract() ? JavaGenClass.AbstractResource : JavaGenClass.Resource, null);
    }
    for (String s : sorted(definitions.getResources().keySet())) {
      ResourceDefn n = definitions.getResources().get(s);
      generate(n.getRoot(), JavaGenClass.Resource, null);
    }
    genResourceConvertor();


    write("\r\n");
    write("}\r\n");

  }

  private void generate(ProfiledType pt) throws Exception {
    generate(definitions.getElementDefn(pt.getBaseType()), JavaGenClass.Type, pt.getName());
  }

  private void genTypeConvertor() throws IOException {
    write("  public static org.hl7.fhir.r4.model.Type convertType(org.hl7.fhir.dstu3.model.Type src) throws FHIRException {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    for (String s : sorted(definitions.getPrimitives().keySet())) {
      write("    if (src instanceof org.hl7.fhir.dstu3.model."+upFirst(s)+"Type)\r\n");
      write("      return convert"+upFirst(s)+"((org.hl7.fhir.dstu3.model."+upFirst(s)+"Type) src);\r\n");
    }
    for (String s : sorted(definitions.getInfrastructure().keySet())) {
      if (!exemptTypeName(s)) {
        write("    if (src instanceof org.hl7.fhir.dstu3.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.dstu3.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getTypes().keySet())) {
      if (!exemptTypeName(s)) {
        write("    if (src instanceof org.hl7.fhir.dstu3.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.dstu3.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getStructures().keySet())) {
      if (!exemptTypeName(s)) {
        write("    if (src instanceof org.hl7.fhir.dstu3.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.dstu3.model."+s+") src);\r\n");
      }
    }    
    for (String s : sorted(definitions.getConstraints().keySet())) {
      write("    if (src instanceof org.hl7.fhir.dstu3.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.dstu3.model."+s+") src);\r\n");
    }

    write("    throw new Error(\"Unknown type \"+src.fhirType());\r\n");
    write("  }\r\n\r\n");
    write("  public static org.hl7.fhir.dstu3.model.Type convertType(org.hl7.fhir.r4.model.Type src) throws FHIRException {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    for (String s : sorted(definitions.getPrimitives().keySet())) {
      write("    if (src instanceof org.hl7.fhir.r4.model."+upFirst(s)+"Type)\r\n");
      write("      return convert"+upFirst(s)+"((org.hl7.fhir.r4.model."+upFirst(s)+"Type) src);\r\n");
    }
    for (String s : sorted(definitions.getInfrastructure().keySet())) {
      if (!exemptTypeName(s)) {
        write("    if (src instanceof org.hl7.fhir.r4.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.r4.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getTypes().keySet())) {
      if (!exemptTypeName(s)) {
        write("    if (src instanceof org.hl7.fhir.r4.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.r4.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getStructures().keySet())) {
      if (!exemptTypeName(s)) {
        write("    if (src instanceof org.hl7.fhir.r4.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.r4.model."+s+") src);\r\n");
      }
    }    
    for (String s : sorted(definitions.getConstraints().keySet())) {
      write("    if (src instanceof org.hl7.fhir.r4.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.r4.model."+s+") src);\r\n");
    }
    write("    throw new Error(\"Unknown type \"+src.fhirType());\r\n");
    write("  }\r\n\r\n");
  }

  private void genResourceConvertor() throws IOException {
    write("  public static org.hl7.fhir.r4.model.Resource convertResource(org.hl7.fhir.dstu3.model.Resource src) throws FHIRException {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    for (String s : sorted(definitions.getBaseResources().keySet())) {
      if (!definitions.getBaseResources().get(s).isAbstract()) {
        write("    if (src instanceof org.hl7.fhir.dstu3.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.dstu3.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getResources().keySet())) {
      write("    if (src instanceof org.hl7.fhir.dstu3.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.dstu3.model."+s+") src);\r\n");
    }
    write("    throw new Error(\"Unknown resource \"+src.fhirType());\r\n");
    write("  }\r\n\r\n");

    write("  public static org.hl7.fhir.dstu3.model.Resource convertResource(org.hl7.fhir.r4.model.Resource src) throws FHIRException {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    for (String s : sorted(definitions.getBaseResources().keySet())) {
      if (!definitions.getBaseResources().get(s).isAbstract()) {
        write("    if (src instanceof org.hl7.fhir.r4.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.r4.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getResources().keySet())) {
      write("    if (src instanceof org.hl7.fhir.r4.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.r4.model."+s+") src);\r\n");
    }
    write("    throw new Error(\"Unknown resource \"+src.fhirType());\r\n");
    write("  }\r\n\r\n");
  }

  private boolean exemptTypeName(String s) {
    return 
        Utilities.existsInList(s, "Element", "BackboneElement")  // structural
        ||
        Utilities.existsInList(s, "ModuleMetadata"); // no equivalence in DSTU2
  }

  private List<String> sorted(Set<String> keys) {
    List<String> result = new ArrayList<String>();
    result.addAll(keys);
    Collections.sort(result);
    return result;
  }


  private void start(String version, Date genDate) throws IOException {
    write("package org.hl7.fhir.convertors;\r\n\r\n");
    write("import org.hl7.fhir.exceptions.FHIRException;\r\n\r\n");
    write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
    write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    write("\r\n");
    write("public class VersionConvertor_30_40 {\r\n");
    write("\r\n");


  }

  private void genVersionCommon() throws IOException {
    write("  private static void copyElement(org.hl7.fhir.dstu3.model.Element src, org.hl7.fhir.r4.model.Element tgt) throws FHIRException {\r\n");
    write("    if (src.hasId())\r\n      tgt.setId(src.getId());\r\n");
    write("    for (org.hl7.fhir.dstu3.model.Extension  e : src.getExtension()) {\r\n");
    write("      tgt.addExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

    write("  private static void copyElement(org.hl7.fhir.r4.model.Element src, org.hl7.fhir.dstu3.model.Element tgt) throws FHIRException {\r\n");
    write("    if (src.hasId())\r\n      tgt.setId(src.getId());\r\n");
    write("    for (org.hl7.fhir.r4.model.Extension  e : src.getExtension()) {\r\n");
    write("      tgt.addExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

    write("  private static void copyBackboneElement(org.hl7.fhir.dstu3.model.BackboneElement src, org.hl7.fhir.r4.model.BackboneElement tgt) throws FHIRException {\r\n");
    write("    copyElement(src, tgt);\r\n");
    write("    for (org.hl7.fhir.dstu3.model.Extension  e : src.getModifierExtension()) {\r\n");
    write("      tgt.addModifierExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

    write("  private static void copyBackboneElement(org.hl7.fhir.r4.model.BackboneElement src, org.hl7.fhir.dstu3.model.BackboneElement tgt) throws FHIRException {\r\n");
    write("    copyElement(src, tgt);\r\n");
    write("    for (org.hl7.fhir.r4.model.Extension  e : src.getModifierExtension()) {\r\n");
    write("      tgt.addModifierExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

  }

  private void genVersionConvertor(DefinedCode pt) throws IOException {
    String tn = getPrimitiveTypeModelName(pt.getCode());
    write("  public static org.hl7.fhir.r4.model."+tn+" convert"+tn.substring(0,  tn.length()-4)+"(org.hl7.fhir.dstu3.model."+tn+" src) throws FHIRException {\r\n");
    write("    org.hl7.fhir.r4.model."+tn+" tgt = new org.hl7.fhir.r4.model."+tn+"(src.getValue());\r\n");
    write("    copyElement(src, tgt);\r\n");
    write("    return tgt;\r\n");
    write("  }\r\n\r\n");
    write("  public static org.hl7.fhir.dstu3.model."+tn+" convert"+tn.substring(0,  tn.length()-4)+"(org.hl7.fhir.r4.model."+tn+" src) throws FHIRException {\r\n");
    write("    org.hl7.fhir.dstu3.model."+tn+" tgt = new org.hl7.fhir.dstu3.model."+tn+"(src.getValue());\r\n");
    write("    copyElement(src, tgt);\r\n");
    write("    return tgt;\r\n");
    write("  }\r\n\r\n");
  }


  private String getPrimitiveTypeModelName(String code) {
    if (code.equals("string"))
      return "StringType";
    if (definitions.hasPrimitiveType(code))
      return upFirst(code)+"Type";
    return upFirst(code);
  }

  private String upFirst(String n) {
    if (Utilities.noString(n))
      return "";
    return n.substring(0,1).toUpperCase() + n.substring(1);
  }


  private void generate(ElementDefn n, JavaGenClass clss, String nameOverride) throws Exception {
    if (n.getName().equals("Quantity") && !doneQ) {
      doneQ = true;
      write("   public static void copyQuantity(org.hl7.fhir.dstu3.model.Quantity src, org.hl7.fhir.r4.model.Quantity tgt) throws FHIRException {\r\n");
      write("    if (src == null || tgt == null)\r\n");
      write("      return;\r\n");
      write("    copyElement(src, tgt);\r\n");
      write("    if (src.hasValue())\r\n");
      write("      tgt.setValue(src.getValue());\r\n");
      write("    if (src.hasComparator())\r\n");
      write("      tgt.setComparator(convertQuantityComparator(src.getComparator()));\r\n");
      write("    if (src.hasUnit())\r\n");
      write("      tgt.setUnit(src.getUnit());\r\n");
      write("    if (src.hasSystem())\r\n");
      write("      tgt.setSystem(src.getSystem());\r\n");
      write("    if (src.hasCode())\r\n");
      write("      tgt.setCode(src.getCode());\r\n");
      write("  }\r\n");
      write("  \r\n");
      write("  public static void copyQuantity(org.hl7.fhir.r4.model.Quantity src, org.hl7.fhir.dstu3.model.Quantity tgt) throws FHIRException {\r\n");
      write("    if (src == null || tgt == null)\r\n");
      write("      return;\r\n");
      write("    copyElement(src, tgt);\r\n");
      write("    if (src.hasValue())\r\n");
      write("      tgt.setValue(src.getValue());\r\n");
      write("    if (src.hasComparator())\r\n");
      write("      tgt.setComparator(convertQuantityComparator(src.getComparator()));\r\n");
      write("    if (src.hasUnit())\r\n");
      write("      tgt.setUnit(src.getUnit());\r\n");
      write("    if (src.hasSystem())\r\n");
      write("      tgt.setSystem(src.getSystem());\r\n");
      write("    if (src.hasCode())\r\n");
      write("     tgt.setCode(src.getCode());\r\n");
      write("}\r\n");
      write("\r\n");
    }
    generate(n, n, clss, nameOverride);
  }

  private void generate(ElementDefn root, ElementDefn n, JavaGenClass clss, String nameOverride) throws Exception {
    if (exemptTypeName(n.getName())) 
      return;

    typeNames.clear();
    typeNameStrings.clear();
    enums.clear();
    strucs.clear();
    enumNames.clear();
    String nn = javaClassName(n.getName());
    for (ElementDefn e : n.getElements()) {
      scanNestedTypes(n, nn, e);
    }

    if (clss == JavaGenClass.AbstractResource)
      genInnerAbstract(n);
    else
      genInner(null, root, n, clss, nameOverride);

    for (ElementDefn e : strucs) {
      genInner(n.getName(), root, e, clss == JavaGenClass.Resource ? JavaGenClass.BackboneElement : JavaGenClass.Structure, null);
    }

  }


  private String javaClassName(String name) {
    if (name.equals("List"))
      return "ListResource";
    else 
      return name;
  }


  private void genInner(String typeName, ElementDefn root, ElementDefn ed, JavaGenClass clss, String nameOverride) throws IOException, Exception {
    String es = "";
    String tn = nameOverride;
    String stn = nameOverride;
    if (tn == null) {
      if (typeName == null)
        tn = javaClassName(ed.getName());
      else
        tn = typeName+"."+(ed.getDeclaredTypeName() != null ? ed.getDeclaredTypeName() : upFirst(ed.getName()));
      stn = tn.contains(".") ? tn.substring(tn.lastIndexOf(".") + 1) : tn;
    }

    write("  public static org.hl7.fhir.r4.model."+tn+" convert"+stn+"(org.hl7.fhir.dstu3.model."+tn+" src) throws FHIRException {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    write("    org.hl7.fhir.r4.model."+tn+" tgt = new org.hl7.fhir.r4.model."+tn+"();\r\n");
    String parentType = ed.typeCode();
    if (Utilities.noString(parentType) || parentType.equals("Type") || parentType.equals("Structure"))
      parentType = "Element";
    write("    copy"+parentType+"(src, tgt);\r\n");
    es = processElements(root, ed, es, "dstu3");
    write("    return tgt;\r\n");
    write("  }\r\n\r\n");

    write("  public static org.hl7.fhir.dstu3.model."+tn+" convert"+stn+"(org.hl7.fhir.r4.model."+tn+" src) throws FHIRException {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    write("    org.hl7.fhir.dstu3.model."+tn+" tgt = new org.hl7.fhir.dstu3.model."+tn+"();\r\n");
    write("    copy"+parentType+"(src, tgt);\r\n");
    es = processElements(root, ed, es, "r4");
    write("    return tgt;\r\n");
    write("  }\r\n\r\n");

    write(es);
  }

  private String processElements(ElementDefn root, ElementDefn ed, String es, String ver) throws Exception, IOException {
    for (ElementDefn c : ed.getElements()) {
      boolean prim = isPrimitive(c) || "xhtml".equals(c.typeCode());
      String en = upFirst(c.getName()).replace("[x]", "");
      if (en.equals("Class"))
        en = "Class_";
      String cn = "";
      String tn = c.typeCode();
      if (tn.contains("("))
        tn = tn.substring(0, tn.indexOf("("));
      String tt = "";
      if (prim) {
        BindingSpecification cd = c.getBinding();
        String ctn = typeNames.get(c);
        tn = ctn;
        if (c.typeCode().equals("code") && cd != null && isEnum(cd)) {
          cn = "convert"+upFirst(ctn.contains(".") ? ctn.substring(ctn.lastIndexOf(".")+1) : ctn)+"(";
          es = es + genEnumConvertor(cn.substring(0, cn.length()-1), ctn, cd);
          tn = "Enumeration<org.hl7.fhir."+ver+".model."+tn+">";
        } else {
          tn = upFirst(tn) + "Type";
        }
        tt = ".getValue()"; 
      } else {
        if (c.typeCode().contains("@")) {
          ElementDefn cs = getElementForPath(root, c.typeCode().substring(1));
          tn = cs.getDeclaredTypeName() == null ? upFirst(cs.getName()) : cs.getDeclaredTypeName();
          cn = "convert"+tn+"(";
          tn = root.getName()+"."+tn;
        } else if (c.getTypes().size() == 0) {
          tn = c.getDeclaredTypeName() == null ? upFirst(c.getName()) : c.getDeclaredTypeName();
          cn = "convert"+tn+"(";
          if (ed.getPath().contains("."))
            tn = root(typeNames.get(ed)) +"."+tn;
          else
            tn = ed.getName()+"."+tn;
        } else if (c.getTypes().size() != 1 || "*".equals(c.typeCode()))
          cn = "convertType(";
        else
          cn = "convert"+upFirst(c.getTypes().get(0).getName())+"(";
      }
      String ct = Utilities.noString(cn) ? "" : ")";
      if (c.unbounded()) {
        write("    for (org.hl7.fhir."+ver+".model."+tn+" t : src.get"+en+"())\r\n");
        write("      tgt.add"+en+"("+cn+"t"+tt+ct+");\r\n");

      } else {
        write("    if (src.has"+en+"())\r\n");
        write("      tgt.set"+en+"("+cn+"src.get"+en+"()"+ct+");\r\n");
      }
    }
    return es;
  }

  private boolean isEnum(BindingSpecification cd) {
    return cd.getBinding() == (BindingSpecification.BindingMethod.CodeList) || (cd.getStrength() == BindingStrength.REQUIRED && cd.getBinding() == BindingMethod.ValueSet);
  }

  private String root(String s) {
    return s.contains(".") ? s.substring(0, s.indexOf(".")) : s;
  }

  private String genEnumConvertor(String cn, String ctn, BindingSpecification cd) throws Exception {
    if (elist.contains(cn))
      return "";
    elist.add(cn);

    StringBuilder b = new StringBuilder();
    b.append("  private static org.hl7.fhir.r4.model."+ctn+" "+cn+"(org.hl7.fhir.dstu3.model."+ctn+" src) throws FHIRException {\r\n");
    b.append("    if (src == null)\r\n");
    b.append("      return null;\r\n");
    b.append("    switch (src) {\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      b.append("    case "+cc+": return org.hl7.fhir.r4.model."+ctn+"."+cc+";\r\n");
    } 
    b.append("    default: return org.hl7.fhir.r4.model."+ctn+".NULL;\r\n");
    b.append("  }\r\n");
    b.append("}\r\n\r\n");
    b.append("  private static org.hl7.fhir.dstu3.model."+ctn+" "+cn+"(org.hl7.fhir.r4.model."+ctn+" src) throws FHIRException {\r\n");
    b.append("    if (src == null)\r\n");
    b.append("      return null;\r\n");
    b.append("    switch (src) {\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      b.append("    case "+cc+": return org.hl7.fhir.dstu3.model."+ctn+"."+cc+";\r\n");
    } 
    b.append("    default: return org.hl7.fhir.dstu3.model."+ctn+".NULL;\r\n");
    b.append("  }\r\n");
    b.append("}\r\n\r\n");
    return b.toString();
  }

  private void genInnerAbstract(ElementDefn n) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());
    String parent = n.typeCode();
    String es = "";
    write("  private static void copy"+tn+"(org.hl7.fhir.dstu3.model."+tn+" src, org.hl7.fhir.r4.model."+tn+" tgt) throws FHIRException {\r\n");
    if (!Utilities.noString(parent))
      write("    copy"+parent+"(src, tgt);\r\n");
    es = processElements(n, n, es, "dstu3");
    write("  }\r\n");
    write("  private static void copy"+tn+"(org.hl7.fhir.r4.model."+tn+" src, org.hl7.fhir.dstu3.model."+tn+" tgt) throws FHIRException {\r\n");
    if (!Utilities.noString(parent))
      write("    copy"+parent+"(src, tgt);\r\n");
    es = processElements(n, n, es, "r4");
    write("  }\r\n");
    write(es);
  }

  private String getCodeListType(String binding) throws IOException {
    StringBuilder b = new StringBuilder();
    boolean up = true;
    for (char ch: binding.toCharArray()) {
      if (ch == '-' || ch == ' ')
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
      BindingSpecification cd = e.getBinding();
      if (cd != null && isEnum(cd)) {
        tn = getCodeListType(cd.getName());
        if (!enumNames.contains(tn)) {
          enumNames.add(tn);
          enums.add(e);
        }
        if (Utilities.existsInList(tn, "AbstractType", "AdministrativeGender", "AgeUnits", "BindingStrength", "ConceptMapEquivalence", "DataAbsentReason", "DataType", "DocumentReferenceStatus", "FHIRAllTypes", "FHIRDefinedType", "MessageEvent", "NoteType", "PublicationStatus", "RemittanceOutcome", "ResourceType", "SearchParamType", "SpecialValues"))
          typeNames.put(e,  "Enumerations."+upFirst(tn));
        else
          typeNames.put(e,  rootOf(path)+"."+upFirst(tn));
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



  private boolean isPrimitive(ElementDefn e) {
    return definitions.hasPrimitiveType(e.typeCode());
  }


}
