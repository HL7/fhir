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
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.utilities.Utilities;

public class JavaConverterGenerator extends JavaBaseGenerator {
  public enum JavaGenClass { Structure, Type, Resource, AbstractResource, BackboneElement, Constraint }

  private Map<ElementDefn, String> typeNames = new HashMap<ElementDefn, String>();
  private List<String> typeNameStrings = new ArrayList<String>();
  private List<ElementDefn> enums = new ArrayList<ElementDefn>();
  private List<String> elist = new ArrayList<String>();
  private List<String> enumNames = new ArrayList<String>();
  private List<ElementDefn> strucs  = new ArrayList<ElementDefn>();

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
    write("  public static org.hl7.fhir.dstu3.model.Type convertType(org.hl7.fhir.dstu2.model.Type src) {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    for (String s : sorted(definitions.getPrimitives().keySet())) {
      write("    if (src instanceof org.hl7.fhir.dstu2.model."+upFirst(s)+"Type)\r\n");
      write("      return convert"+upFirst(s)+"((org.hl7.fhir.dstu2.model."+upFirst(s)+"Type) src);\r\n");
    }
    for (String s : sorted(definitions.getInfrastructure().keySet())) {
      if (!exemptTypeName(s)) {
        write("    if (src instanceof org.hl7.fhir.dstu2.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.dstu2.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getTypes().keySet())) {
      if (!exemptTypeName(s)) {
      write("    if (src instanceof org.hl7.fhir.dstu2.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.dstu2.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getStructures().keySet())) {
      if (!exemptTypeName(s)) {
      write("    if (src instanceof org.hl7.fhir.dstu2.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.dstu2.model."+s+") src);\r\n");
      }
    }    
    for (String s : sorted(definitions.getConstraints().keySet())) {
      write("    if (src instanceof org.hl7.fhir.dstu2.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.dstu2.model."+s+") src);\r\n");
    }

    write("    throw new Error(\"Unknown type \"+src.fhirType());\r\n");
    write("  }\r\n\r\n");
    write("  public static org.hl7.fhir.dstu2.model.Type convertType(org.hl7.fhir.dstu3.model.Type src) {\r\n");
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
  }

  private void genResourceConvertor() throws IOException {
    write("  public static org.hl7.fhir.dstu3.model.Resource convertResource(org.hl7.fhir.dstu2.model.Resource src) {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    for (String s : sorted(definitions.getBaseResources().keySet())) {
      if (!definitions.getBaseResources().get(s).isAbstract()) {
        write("    if (src instanceof org.hl7.fhir.dstu2.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.dstu2.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getResources().keySet())) {
      write("    if (src instanceof org.hl7.fhir.dstu2.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.dstu2.model."+s+"Type) src);\r\n");
    }
    write("    throw new Error(\"Unknown resource \"+src.fhirType());\r\n");
    write("  }\r\n\r\n");
    
    write("  public static org.hl7.fhir.dstu2.model.Resource convertResource(org.hl7.fhir.dstu3.model.Resource src) {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    for (String s : sorted(definitions.getBaseResources().keySet())) {
      if (!definitions.getBaseResources().get(s).isAbstract()) {
        write("    if (src instanceof org.hl7.fhir.dstu3.model."+s+")\r\n");
        write("      return convert"+s+"((org.hl7.fhir.dstu3.model."+s+") src);\r\n");
      }
    }
    for (String s : sorted(definitions.getResources().keySet())) {
      write("    if (src instanceof org.hl7.fhir.dstu2.model."+s+")\r\n");
      write("      return convert"+s+"((org.hl7.fhir.dstu2.model."+s+"Type) src);\r\n");
    }
    write("    throw new Error(\"Unknown resource \"+src.fhirType());\r\n");
    write("  }\r\n\r\n");
  }

  private boolean exemptTypeName(String s) {
    return 
          Utilities.existsInList(s, "Element", "BackboneElement")  // structural
      ||
          Utilities.existsInList(s, "ModuleMetadata", "CodeSystem"); // no equivalence in DSTU2
}

  private List<String> sorted(Set<String> keys) {
    List<String> result = new ArrayList<String>();
    result.addAll(keys);
    Collections.sort(result);
    return result;
  }


  private void start(String version, Date genDate) throws IOException {
    write("package org.hl7.fhir.convertors;\r\n");
    write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
    write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    write("\r\n");
    write("public class VersionConvertor {\r\n");
    write("\r\n");

  }

  private void genVersionCommon() throws IOException {
    write("  private static void copyElement(org.hl7.fhir.dstu2.model.Element src, org.hl7.fhir.dstu3.model.Element tgt) {\r\n");
    write("    tgt.setId(src.getId());\r\n");
    write("    for (org.hl7.fhir.dstu2.model.Extension  e : src.getExtension()) {\r\n");
    write("      tgt.addExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

    write("  private static void copyElement(org.hl7.fhir.dstu3.model.Element src, org.hl7.fhir.dstu2.model.Element tgt) {\r\n");
    write("    tgt.setId(src.getId());\r\n");
    write("    for (org.hl7.fhir.dstu3.model.Extension  e : src.getExtension()) {\r\n");
    write("      tgt.addExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

    write("  private static void copyBackboneElement(org.hl7.fhir.dstu2.model.BackboneElement src, org.hl7.fhir.dstu3.model.BackboneElement tgt) {\r\n");
    write("    copyElement(src, tgt);\r\n");
    write("    for (org.hl7.fhir.dstu2.model.Extension  e : src.getModifierExtension()) {\r\n");
    write("      tgt.addModifierExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

    write("  private static void copyBackboneElement(org.hl7.fhir.dstu3.model.BackboneElement src, org.hl7.fhir.dstu2.model.BackboneElement tgt) {\r\n");
    write("    copyElement(src, tgt);\r\n");
    write("    for (org.hl7.fhir.dstu3.model.Extension  e : src.getModifierExtension()) {\r\n");
    write("      tgt.addModifierExtension(convertExtension(e));\r\n");
    write("    }\r\n");
    write("  }\r\n\r\n");

  }

  private void genVersionConvertor(DefinedCode pt) throws IOException {
    String tn = getPrimitiveTypeModelName(pt.getCode());
    write("  public static org.hl7.fhir.dstu3.model."+tn+" convert"+tn.substring(0,  tn.length()-4)+"(org.hl7.fhir.dstu2.model."+tn+" src) {\r\n");
    write("    org.hl7.fhir.dstu3.model."+tn+" tgt = new org.hl7.fhir.dstu3.model."+tn+"(src.getValue());\r\n");
    write("    copyElement(src, tgt);\r\n");
    write("    return tgt;\r\n");
    write("  }\r\n\r\n");
    write("  public static org.hl7.fhir.dstu2.model."+tn+" convert"+tn.substring(0,  tn.length()-4)+"(org.hl7.fhir.dstu3.model."+tn+" src) {\r\n");
    write("    org.hl7.fhir.dstu2.model."+tn+" tgt = new org.hl7.fhir.dstu2.model."+tn+"(src.getValue());\r\n");
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
    generate(n, n, clss, nameOverride);
  }
  
  private void generate(ElementDefn root, ElementDefn n, JavaGenClass clss, String nameOverride) throws Exception {
    if (exemptTypeName(n.getName()) || !r2TypeName(n.getName())) 
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

  private boolean r2TypeName(String name) {
    return Utilities.existsInList(name, "base64Binary", "boolean", "code", "date", "dateTime", "decimal", "id", "instant", "integer", "markdown", "oid", "positiveInt", "sid", "string", "time", "unsignedInt", "uri", "uuid", "Account", "Address", "Age", "AllergyIntolerance", "Annotation", "Appointment", "AppointmentResponse", "Attachment", "AuditEvent", 
        "BackboneElement", "Basic", "Binary", "BodySite", "Bundle", "CarePlan", "Claim", "ClaimResponse", "ClinicalImpression", "CodeableConcept", "Coding", "Communication", "CommunicationRequest", "Comparison", "Composition", "ConceptMap", "Condition", "Configuration", "Conformance", "Constants", "ContactPoint", "Contract", "Count", "Coverage", "DataElement", "DetectedIssue", "Device", 
        "DeviceComponent", "DeviceMetric", "DeviceUseRequest", "DeviceUseStatement", "DiagnosticOrder", "DiagnosticReport", "Distance", "DocumentManifest", "DocumentReference", "DomainResource", "Duration", "Element", "ElementDefinition", "EligibilityRequest", "EligibilityResponse", "Encounter", "EnrollmentRequest", "EnrollmentResponse", "EpisodeOfCare", "ExplanationOfBenefit", "ExpressionNode", "Extension", "FamilyMemberHistory", "Flag", "Goal", 
        "Group", "HealthcareService", "HumanName", "Identifier", "ImagingObjectSelection", "ImagingStudy", "Immunization", "ImmunizationRecommendation", "ImplementationGuide", "List_", "Location", "Media", "Medication", "MedicationAdministration", "MedicationDispense", "MedicationOrder", "MedicationStatement", "MessageHeader", "Meta", "Money", "NamingSystem", "Narrative", "NutritionOrder", "Observation", "OperationDefinition", "OperationOutcome", "Order", "OrderResponse", "Organization", 
        "Parameters", "Patient", "PaymentNotice", "PaymentReconciliation", "Period", "Person", "Practitioner", "Procedure", "ProcedureRequest", "ProcessRequest", "ProcessResponse", "Property", "Provenance", "Quantity", "Questionnaire", "QuestionnaireResponse", "Range", "Ratio", "Reference", "ReferralRequest", "RelatedPerson", "Resource", "Resource", "RiskAssessment", 
        "SampledData", "Schedule", "SearchParameter", "Signature", "SimpleQuantity", "Slot", "Specimen", "StructureDefinition", "Subscription", "Substance", "SupplyDelivery", "SupplyRequest", "TemporalPrecisionEnum", "TestScript", "Timing", "ValueSet", "VisionPrescription");
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
        tn = ed.getName();
      else
        tn = typeName+"."+(ed.getDeclaredTypeName() != null ? ed.getDeclaredTypeName() : upFirst(ed.getName()));
      stn = tn.contains(".") ? tn.substring(tn.lastIndexOf(".") + 1) : tn;
    }
    
    write("  public static org.hl7.fhir.dstu3.model."+tn+" convert"+stn+"(org.hl7.fhir.dstu2.model."+tn+" src) {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    write("    org.hl7.fhir.dstu3.model."+tn+" tgt = new org.hl7.fhir.dstu3.model."+tn+"();\r\n");
    String parentType = ed.typeCode();
    if (Utilities.noString(parentType) || parentType.equals("Type") || parentType.equals("Structure"))
      parentType = "Element";
    write("    copy"+parentType+"(src, tgt);\r\n");
    es = processElements(root, ed, es, "2");
    write("    return tgt;\r\n");
    write("  }\r\n\r\n");
    
    write("  public static org.hl7.fhir.dstu2.model."+tn+" convert"+stn+"(org.hl7.fhir.dstu3.model."+tn+" src) {\r\n");
    write("    if (src == null)\r\n");
    write("      return null;\r\n");
    write("    org.hl7.fhir.dstu2.model."+tn+" tgt = new org.hl7.fhir.dstu2.model."+tn+"();\r\n");
    write("    copy"+parentType+"(src, tgt);\r\n");
    es = processElements(root, ed, es, "3");
    write("    return tgt;\r\n");
    write("  }\r\n\r\n");
    
    write(es);
  }

  private String processElements(ElementDefn root, ElementDefn ed, String es, String ver) throws Exception, IOException {
    for (ElementDefn c : ed.getElements()) {
      boolean prim = isPrimitive(c) || "xhtml".equals(c.typeCode());
      String en = upFirst(c.getName()).replace("[x]", "");
      String cn = "";
      String tn = c.typeCode();
      if (tn.contains("("))
        tn = tn.substring(0, tn.indexOf("("));
      String tt = "";
      if (prim) {
        BindingSpecification cd = c.getBinding();
        String ctn = typeNames.get(c);
        tn = ctn;
        if (c.typeCode().equals("code") && cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
          cn = "convert"+upFirst(ctn.contains(".") ? ctn.substring(ctn.lastIndexOf(".")+1) : ctn)+"(";
          es = es + genEnumConvertor(cn.substring(0, cn.length()-1), ctn, cd);
          tn = "Enumeration<org.hl7.fhir.dstu"+ver+".model."+tn+">";
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
          tn = ed.getName()+"."+tn;
        } else if (c.getTypes().size() != 1 || "*".equals(c.typeCode()))
          cn = "convertType(";
        else
          cn = "convert"+upFirst(c.getTypes().get(0).getName())+"(";
      }
      String ct = Utilities.noString(cn) ? "" : ")";
      String et = "";// "Element";
      if (c.unbounded()) {
        write("    for (org.hl7.fhir.dstu"+ver+".model."+tn+" t : src.get"+en+et+"())\r\n");
        write("      tgt.add"+en+et+"("+cn+"t"+tt+ct+");\r\n");

      } else {
        write("    tgt.set"+en+et+"("+cn+"src.get"+en+et+"()"+ct+");\r\n");
      }
    }
    return es;
  }

  private String genEnumConvertor(String cn, String ctn, BindingSpecification cd) throws Exception {
    if (elist.contains(cn))
      return "";
    elist.add(cn);
    
    StringBuilder b = new StringBuilder();
    b.append("  private static org.hl7.fhir.dstu3.model."+ctn+" "+cn+"(org.hl7.fhir.dstu2.model."+ctn+" src) {\r\n");
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
    b.append("  private static org.hl7.fhir.dstu2.model."+ctn+" "+cn+"(org.hl7.fhir.dstu3.model."+ctn+" src) {\r\n");
    b.append("    if (src == null)\r\n");
    b.append("      return null;\r\n");
    b.append("    switch (src) {\r\n");
    for (DefinedCode c : cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true)) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      b.append("    case "+cc+": return org.hl7.fhir.dstu2.model."+ctn+"."+cc+";\r\n");
    } 
    b.append("    default: return org.hl7.fhir.dstu2.model."+ctn+".NULL;\r\n");
    b.append("  }\r\n");
    b.append("}\r\n\r\n");
    return b.toString();
  }

  private void genInnerAbstract(ElementDefn n) throws IOException, Exception {
    String tn = typeNames.containsKey(n) ? typeNames.get(n) : javaClassName(n.getName());
    String parent = n.typeCode();
    String es = "";
    write("  private static void copy"+tn+"(org.hl7.fhir.dstu2.model."+tn+" src, org.hl7.fhir.dstu3.model."+tn+" tgt) {\r\n");
    if (!Utilities.noString(parent))
      write("    copy"+parent+"(src, tgt);\r\n");
    es = processElements(n, n, es, "2");
    write("  }\r\n");
    write("  private static void copy"+tn+"(org.hl7.fhir.dstu3.model."+tn+" src, org.hl7.fhir.dstu2.model."+tn+" tgt) {\r\n");
    if (!Utilities.noString(parent))
      write("    copy"+parent+"(src, tgt);\r\n");
    es = processElements(n, n, es, "3");
    write("  }\r\n");
    write(es);
  }

  private String pathClass(String tn) {
    return tn.substring(0, tn.indexOf('.'));
  }

  private String pathNode(String tn) {
    return tn.substring(tn.indexOf('.')+1);
  }

  private void genElement(ElementDefn root, ElementDefn e, boolean first, JavaGenClass clss, boolean bUseOwner) throws Exception {
    String name = e.getName();
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
  
  private String getCodeListType(String binding) throws IOException {
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
      BindingSpecification cd = e.getBinding();
      if (cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
        tn = getCodeListType(cd.getValueSet().getName());
        if (!enumNames.contains(tn)) {
          enumNames.add(tn);
          enums.add(e);
        }
        if (Utilities.existsInList(tn, "BindingStrength"))
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
  

}
