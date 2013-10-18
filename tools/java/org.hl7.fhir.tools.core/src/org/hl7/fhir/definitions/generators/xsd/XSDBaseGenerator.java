package org.hl7.fhir.definitions.generators.xsd;

/*
 Copyright (c) 2011-2013, HL7, Inc
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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.utilities.Utilities;

public class XSDBaseGenerator {

  private OutputStreamWriter writer;
  
  private Definitions definitions;

  private Map<String, ElementDefn> structures = new HashMap<String, ElementDefn>();
  private List<String> typenames = new ArrayList<String>();
  // private List<TypeDefn> datatypes = new ArrayList<TypeDefn>();
  // private Map<String, ConceptDomain> tx;
  private List<String> enums = new ArrayList<String>();
  private Map<String, String> enumDefs = new HashMap<String, String>();

  private List<String> genEnums = new ArrayList<String>();
  private Map<String, String> regexQueue = new HashMap<String, String>(); 

  // private Map<String, PrimitiveType> primitives;

  public XSDBaseGenerator(OutputStreamWriter out) throws UnsupportedEncodingException {
    writer = out;
  }

  private void write(String s) throws IOException {
    writer.write(s);
  }
  
  public void generate(String version, String genDate, boolean outer) throws Exception {
    if (outer) {
      write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
      write("<!-- \r\n");
      write(Config.FULL_LICENSE_CODE);
      write("\r\n");
      write("  Generated on " + genDate + " for FHIR v" + version + " \r\n");
      write("-->\r\n");
      write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://hl7.org/fhir\" xmlns:xhtml=\"http://www.w3.org/1999/xhtml\" "
          + "targetNamespace=\"http://hl7.org/fhir\" elementFormDefault=\"qualified\" version=\""+version+"\">\r\n");
    }
    write("  <xs:import namespace=\"http://www.w3.org/XML/1998/namespace\" schemaLocation=\"xml.xsd\"/>\r\n");
    write("  <xs:import namespace=\"http://www.w3.org/1999/xhtml\" schemaLocation=\"xhtml1-strict.xsd\"/>\r\n");
    if (outer)
      write("  <xs:include schemaLocation=\"fhir-all.xsd\"/>\r\n");

    genXmlIdRef();
    genElementRoot();
    write("\r\n");
    genPrimitives();
    write("\r\n");
    genResourceReference();
    genResource();

    for (ElementDefn e : definitions.getInfrastructure().values())
      genInfrastructure(e);
    for (ElementDefn e : definitions.getTypes().values())
      genType(e);
    for (DefinedCode cd : definitions.getConstraints().values())
      genConstraint(cd);
    for (ElementDefn e : definitions.getStructures().values())
      genStructure(e);
    for (BindingSpecification b : definitions.getBindings().values())
      if (b.getUseContexts().size() > 1 && b.getBinding() == Binding.CodeList)
        generateEnum(b.getName());
    if (outer) { 
      write("</xs:schema>\r\n");
      writer.flush();
    }
  }

  private void genElementRoot() throws Exception  {
    write("  <xs:complexType name=\"Element\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>The base element used for all FHIR elements and resources - allows for them to be extended with extensions</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:sequence>\r\n");
    write("      <xs:element name=\"extension\" type=\"Extension\" minOccurs=\"0\" maxOccurs=\"unbounded\">\r\n");
    write("        <xs:annotation>\r\n");
    write("          <xs:documentation>An extension - additional local content. The extension URL defines it's meaning</xs:documentation>\r\n");
    write("        </xs:annotation>\r\n");
    write("      </xs:element>\r\n");
    write("    </xs:sequence>\r\n");
    write("    <xs:attribute name=\"id\" type=\"id-primitive\"/>\r\n");
    write("  </xs:complexType>\r\n");
    write("\r\n");    
    write("  <xs:complexType name=\"BackboneElement\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>An element defined in a FHIR resources - can have modifierExtension elements</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:complexContent>\r\n");
    write("      <xs:extension base=\"Element\">\r\n");
    write("        <xs:sequence>\r\n");
    write("          <xs:element name=\"modifierExtension\" type=\"Extension\" minOccurs=\"0\" maxOccurs=\"unbounded\">\r\n");
    write("            <xs:annotation>\r\n");
    write("              <xs:documentation>An extension that modifies the meaning of the element that contains it - additional local content. The extension URL defines it's meaning</xs:documentation>\r\n");
    write("            </xs:annotation>\r\n");
    write("          </xs:element>\r\n");
    write("        </xs:sequence>\r\n");
    write("      </xs:extension>\r\n");
    write("    </xs:complexContent>\r\n");
    write("  </xs:complexType>\r\n");
    write("\r\n");    
  }

  private void genResource() throws Exception {
    write("  <xs:complexType name=\"Resource.Inline\">\r\n");
    write("    <xs:choice minOccurs=\"1\" maxOccurs=\"1\">\r\n");
    write("      <xs:element ref=\"Binary\"/>\r\n");
    for (String r : definitions.getResources().keySet()) 
      write("      <xs:element ref=\""+r+"\"/>\r\n");
    write("    </xs:choice>\r\n");
    write("  </xs:complexType>\r\n");
    write("  <xs:complexType name=\"Resource\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>The base resource declaration used for all FHIR resource types - adds Narrative and xml:lang</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:complexContent>\r\n");
    write("      <xs:extension base=\"BackboneElement\">\r\n");
    write("        <xs:sequence>\r\n");
    write("          <xs:element name=\"language\" type=\"code\" minOccurs=\"0\" maxOccurs=\"1\">\r\n");
    write("            <xs:annotation>\r\n");
    write("              <xs:documentation>The human language of the content. The value can be any valid value according to BCP-47</xs:documentation>\r\n");
    write("            </xs:annotation>\r\n");
    write("          </xs:element>\r\n");
    write("          <xs:element name=\"text\" type=\"Narrative\" minOccurs=\"0\" maxOccurs=\"1\">\r\n");
    write("            <xs:annotation>\r\n");
    write("              <xs:documentation>Text summary of resource content (for human interpretation)</xs:documentation>\r\n");
    write("            </xs:annotation>\r\n");
    write("          </xs:element>\r\n");
    write("          <xs:element name=\"contained\" type=\"Resource.Inline\" minOccurs=\"0\" maxOccurs=\"unbounded\">\r\n");
    write("            <xs:annotation>\r\n");
    write("              <xs:documentation>Contained, inline Resources. These resources do not have an independent existence apart from the resource that contains them - they cannot be identified independently, and nor can they have their own independent transaction scope</xs:documentation>\r\n");
    write("            </xs:annotation>\r\n");
    write("          </xs:element>\r\n");
    write("        </xs:sequence>\r\n");
    write("      </xs:extension>\r\n");
    write("    </xs:complexContent>\r\n");
    write("  </xs:complexType>\r\n");
    write("\r\n");
  }

  private void genXmlIdRef() throws Exception {
    write("  <!-- change this to xs:IDREF and all id attributes to type xs:ID to enforce internal references by schema,\r\n");
    write("       but note that this can't work in bundles (see comments in Resource Format section) -->\r\n");
    write("  <xs:simpleType name=\"xmlIdRef\">\r\n");
    write("    <xs:restriction base=\"id-primitive\">\r\n");
    write("      <xs:pattern value=\"[a-z0-9\\-\\.]{1,36}\"/>\r\n"); 
    write("    </xs:restriction>\r\n");
    write("  </xs:simpleType>\r\n");
  }

  //  private void genExtensionsElement() throws Exception {
  //    write("  <xs:complexType name=\"Extensions\">\r\n");
  //    write("    <xs:sequence>\r\n");
  //    write("      <xs:element name=\"extension\" type=\"Extension\" minOccurs=\"0\" maxOccurs=\"unbounded\">\r\n");
  //    write("        <xs:annotation>\r\n");
  //    write("          <xs:documentation>An extension value</xs:documentation>\r\n");
  //    write("        </xs:annotation>\r\n");
  //    write("      </xs:element>\r\n");
  //    write("    </xs:sequence>\r\n");
  //    write("  </xs:complexType>\r\n");
  //  }

  private void genResourceReference() throws Exception {
    write("  <xs:simpleType name=\"ResourceType\">\r\n");
    write("    <xs:restriction base=\"xs:string\">\r\n");
    for (DefinedCode c : definitions.getKnownResources().values()) {
      write("      <xs:enumeration value=\"" + c.getCode() + "\">\r\n");
      write("        <xs:annotation>\r\n");
      write("          <xs:documentation>"
          + Utilities.escapeXml(c.getDefinition())
          + "</xs:documentation>\r\n");
      write("        </xs:annotation>\r\n");
      write("      </xs:enumeration>\r\n");
    }
    write("    </xs:restriction>\r\n");
    write("  </xs:simpleType>\r\n");
  }

  private void genConstraint(DefinedCode cd) throws Exception {
    write("  <xs:complexType name=\"" + cd.getCode() + "\">\r\n");
    write("    <xs:complexContent>\r\n");
    write("      <xs:restriction base=\"" + cd.getComment() + "\">\r\n");
    write("        <xs:sequence>\r\n");

    ElementDefn elem = definitions.getTypes().get(cd.getComment());
    for (ElementDefn e : elem.getElements()) {
      if (e.getName().equals("[type]"))
        generateAny(elem, e, null);
      else 
        generateElement(elem, e, null);
    }
    write("        </xs:sequence>\r\n");

    write("        <xs:attribute name=\"id\" type=\"id-primitive\"/>\r\n");
    write("      </xs:restriction>\r\n");
    write("    </xs:complexContent>\r\n");
    write("  </xs:complexType>\r\n");

  }

  private void genPrimitives() throws Exception {
    for (DefinedCode cd : definitions.getPrimitives().values()) {
      if (cd instanceof PrimitiveType) {
        PrimitiveType pt = (PrimitiveType) cd;
        // two very special cases due to schema weirdness
        if (cd.getCode().equals("date")) {
          write("<xs:simpleType name=\"date-primitive\">\r\n");
          write("  <xs:restriction>\r\n");
          write("    <xs:simpleType>\r\n");
          write("      <xs:union memberTypes=\"xs:gYear xs:gYearMonth xs:date\"/>\r\n");
          write("    </xs:simpleType>\r\n");
          write("    <xs:pattern value=\"\\d{4}(\\-\\d{2}(\\-\\d{2})?)?(Z|(\\+|\\-)\\d{2}:\\d{2})?\"/>\r\n");
          write("  </xs:restriction>\r\n");
          write("</xs:simpleType>\r\n");
        } else if (cd.getCode().equals("dateTime")) {
          write("<xs:simpleType name=\"dateTime-primitive\">\r\n");
          write("  <xs:restriction>\r\n");
          write("    <xs:simpleType>\r\n");
          write("      <xs:union memberTypes=\"xs:gYear xs:gYearMonth xs:date xs:dateTime\"/>\r\n");
          write("    </xs:simpleType>\r\n");
          write("    <xs:pattern value=\"\\d{4}(\\-\\d{2}(\\-\\d{2}(T\\d{2}(:\\d{2}(:\\d{2}(\\.\\d+)?)?)?)?)?)?(Z|(\\+|\\-)\\d{2}:\\d{2})?\"/>\r\n");
          write("  </xs:restriction>\r\n");
          write("</xs:simpleType>\r\n");          
        } else {
          write("  <xs:simpleType name=\"" + pt.getCode() + "-primitive\">\r\n");
          if (pt.getSchemaType().contains(",")) {
            write("    <xs:union memberTypes=\""+pt.getSchemaType().replace(",", "")+"\"/>\r\n");
          } else if (pt.getSchemaType().equals("string")) {
            write("    <xs:restriction base=\"xs:"+pt.getSchemaType()+"\">\r\n");
            write("      <xs:minLength value=\"1\"/>\r\n");
            write("    </xs:restriction>\r\n");
          } else {
            write("    <xs:restriction base=\"xs:"+pt.getSchemaType()+"\"/>\r\n");
          }
          write("  </xs:simpleType>\r\n");
        }
        write("  <xs:complexType name=\"" + pt.getCode() + "\">\r\n");
        write("    <xs:annotation>\r\n");
        write("      <xs:documentation>"+Utilities.escapeXml(pt.getDefinition())+"</xs:documentation>\r\n");
        write("      <xs:documentation>If the element is present, it must have either a @value, an @id, or extensions</xs:documentation>\r\n");
        write("    </xs:annotation>\r\n");
        write("    <xs:complexContent>\r\n");
        write("      <xs:extension base=\"Element\">\r\n");
        write("        <xs:attribute name=\"value\" type=\"" + pt.getCode() + "-primitive\" use=\"optional\"/>\r\n");
        write("      </xs:extension>\r\n");
        write("    </xs:complexContent>\r\n");
        write("  </xs:complexType>\r\n");
      } else {
        DefinedStringPattern sp = (DefinedStringPattern) cd;
        write("  <xs:simpleType name=\"" + sp.getCode()+ "-primitive\">\r\n");
        if (sp.getSchema().endsWith("+")) {
          write("    <xs:restriction base=\""+sp.getSchema().substring(0, sp.getSchema().length()-1)+"\">\r\n");
          write("      <xs:pattern value=\"" + sp.getRegex() + "\"/>\r\n");
          write("      <xs:minLength value=\"1\"/>\r\n");
          write("    </xs:restriction>\r\n");
          write("  </xs:simpleType>\r\n");
        } else {
          write("    <xs:restriction base=\""+sp.getSchema()+"\">\r\n");
          write("      <xs:minLength value=\"1\"/>\r\n");
          write("    </xs:restriction>\r\n");
          write("  </xs:simpleType>\r\n");        
        }
        write("  <xs:complexType name=\"" + sp.getCode() + "\">\r\n");
        write("    <xs:annotation>\r\n");
        write("      <xs:documentation>"+Utilities.escapeXml(sp.getDefinition())+"</xs:documentation>\r\n");
        write("      <xs:documentation>If the element is present, it must have either a @value, an @id referenced from the Narrative, or extensions</xs:documentation>\r\n");
        write("    </xs:annotation>\r\n");
        write("    <xs:complexContent>\r\n");
        write("      <xs:extension base=\"Element\">\r\n");
        write("        <xs:attribute name=\"value\" type=\"" + sp.getCode()+ "-primitive\"/>\r\n");
        write("      </xs:extension>\r\n");
        write("    </xs:complexContent>\r\n");
        write("  </xs:complexType>\r\n");
      }
    }
  }

  private void genInfrastructure(ElementDefn elem) throws Exception {
    enums.clear();
    enumDefs.clear();
    String name = elem.getName();
    write("  <xs:complexType name=\"" + name + "\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>"+Utilities.escapeXml(elem.getDefinition())+"</xs:documentation>\r\n");
    write("      <xs:documentation>If the element is present, it must have a value for at least one of the defined elements, an @id referenced from the Narrative, or extensions</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:complexContent>\r\n");
    write("      <xs:extension base=\"Element\">\r\n");
    write("        <xs:sequence>\r\n");

    for (ElementDefn e : elem.getElements()) {
      if (e.getName().equals("[type]"))
        generateAny(elem, e, null);
      else 
        generateElement(elem, e, null);
    }
    write("        </xs:sequence>\r\n");
    write("      </xs:extension>\r\n");
    write("    </xs:complexContent>\r\n");
    write("  </xs:complexType>\r\n");
    while (!structures.isEmpty()) {
      String s = structures.keySet().iterator().next();
      generateType(elem, s, structures.get(s));
      structures.remove(s);
    }

    for (String en : enums) {
      generateEnum(en);
    }

  }

  private void genType(ElementDefn elem) throws Exception {
    enums.clear();
    enumDefs.clear();
    String name = elem.getName();
    write("  <xs:complexType name=\"" + name + "\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>"+Utilities.escapeXml(elem.getDefinition())+"</xs:documentation>\r\n");
    write("      <xs:documentation>If the element is present, it must have a value for at least one of the defined elements, an @id referenced from the Narrative, or extensions</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:complexContent>\r\n");
    write("      <xs:extension base=\"Element\">\r\n");

    write("        <xs:sequence>\r\n");

    for (ElementDefn e : elem.getElements()) {
      if (e.typeCode().equals("x ml:lang")) {
        // do nothing here
      } else if (e.getName().equals("[type]"))
        generateAny(elem, e, null);
      else 
        generateElement(elem, e, null);
    }
    write("        </xs:sequence>\r\n");
    for (ElementDefn e : elem.getElements()) {
      if (e.typeCode().equals("xml:lang")) {
        write("        <xs:attribute ref=\"xml:lang\"/>\r\n");      
      }
    }

    write("      </xs:extension>\r\n");
    write("    </xs:complexContent>\r\n");

    write("  </xs:complexType>\r\n");
    // write("  <xs:complexType name=\""+name+"\">\r\n");
    // write("    <xs:complexContent>\r\n");
    // write("      <xs:extension base=\"Core"+name+"\">\r\n");
    // write("        <xs:attribute name=\"id\" type=\"id-primitive\"/>\r\n");
    // write("      </xs:extension>\r\n");
    // write("    </xs:complexContent>\r\n");
    // write("  </xs:complexType>\r\n");

    genRegex();
    while (!structures.isEmpty()) {
      String s = structures.keySet().iterator().next();
      generateType(elem, s, structures.get(s));
      structures.remove(s);
    }

    for (String en : enums) {
      generateEnum(en);
    }
    genRegex();

  }

  private void genRegex() throws IOException {
    while (regexQueue.size() > 0) {
      String n = regexQueue.keySet().iterator().next();
      write("  <xs:simpleType name=\""+n+"\">\r\n");
      write("     <xs:restriction base=\"xs:string\">\r\n");
      write("      <xs:pattern value=\""+regexQueue.get(n)+"\"/>\r\n");
      write("    </xs:restriction>\r\n");
      write("  </xs:simpleType>\r\n");    
      regexQueue.remove(n);
    }

  }

  private void genStructure(ElementDefn elem) throws Exception {
    enums.clear();
    enumDefs.clear();
    String name = elem.getName();
    write("  <xs:complexType name=\"" + name + "\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>"+Utilities.escapeXml(elem.getDefinition())+"</xs:documentation>\r\n");
    write("      <xs:documentation>If the element is present, it must have a value for at least one of the defined elements, an @id referenced from the Narrative, or extensions</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:complexContent>\r\n");
    write("      <xs:extension base=\"Element\">\r\n");
    write("        <xs:sequence>\r\n");

    for (ElementDefn e : elem.getElements()) {
      if (e.getName().equals("[type]"))
        generateAny(elem, e, null);
      else 
        generateElement(elem, e, null);
    }
    write("        </xs:sequence>\r\n");
    write("      </xs:extension>\r\n");
    write("    </xs:complexContent>\r\n");
    write("  </xs:complexType>\r\n");
    genRegex();

    while (!structures.isEmpty()) {
      String s = structures.keySet().iterator().next();
      generateType(elem, s, structures.get(s));
      structures.remove(s);
    }

    for (String en : enums) {
      generateEnum(en);
    }

    genRegex();
  }

  private void generateEnum(String en) throws IOException {
    if (genEnums.contains(en))
      return;

    write("  <xs:simpleType name=\"" + en + "-list\">\r\n");
    write("    <xs:restriction base=\"xs:string\">\r\n");
    for (DefinedCode c : definitions.getBindingByName(en).getCodes()) {
      write("      <xs:enumeration value=\""
          + Utilities.escapeXml(c.getCode()) + "\">\r\n");
      write("        <xs:annotation>\r\n");
      write("          <xs:documentation>"
          + Utilities.escapeXml(c.getDefinition())
          + "</xs:documentation>\r\n");
      write("        </xs:annotation>\r\n");
      write("      </xs:enumeration>\r\n");
    }
    write("    </xs:restriction>\r\n");
    write("  </xs:simpleType>\r\n");

    write("  <xs:complexType name=\""+en+"\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>"+Utilities.escapeXml(enumDefs.get(en))+"</xs:documentation>\r\n");
    write("      <xs:documentation>If the element is present, it must have either a @value, an @id, or extensions</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:complexContent>\r\n");
    write("       <xs:extension base=\"Element\">\r\n");
    write("         <xs:attribute name=\"value\" type=\""+en + "-list\" use=\"optional\"/>\r\n");
    write("       </xs:extension>\r\n");
    write("     </xs:complexContent>\r\n");
    write("  </xs:complexType>\r\n");

    genEnums.add(en);
  }

  private void generateType(ElementDefn root, String name, ElementDefn struc)
      throws IOException, Exception {
    write("  <xs:complexType name=\"" + name + "\">\r\n");
    write("    <xs:annotation>\r\n");
    write("      <xs:documentation>"+Utilities.escapeXml(root.getDefinition())+"</xs:documentation>\r\n");
    write("      <xs:documentation>If the element is present, it must have a value for at least one of the defined elements, an @id referenced from the Narrative, or extensions</xs:documentation>\r\n");
    write("    </xs:annotation>\r\n");
    write("    <xs:complexContent>\r\n");
    write("      <xs:extension base=\"Element\">\r\n");
    write("        <xs:sequence>\r\n");

    for (ElementDefn e : struc.getElements()) {
      if (e.getName().equals("[type]"))
        generateAny(root, e, null);
      else 
        generateElement(root, e, null);
    }
    write("        </xs:sequence>\r\n");
    write("      </xs:extension>\r\n");
    write("    </xs:complexContent>\r\n");
    write("  </xs:complexType>\r\n");
  }

  private void generateAny(ElementDefn root, ElementDefn e, String prefix)
      throws Exception {
    write("        <xs:choice minOccurs=\""
        + e.getMinCardinality().toString() + "\" maxOccurs=\"1\">\r\n");
    if (e.hasDefinition()) {
      write("           <xs:annotation>\r\n");
      write("             <xs:documentation>"
          + Utilities.escapeXml(e.getDefinition())
          + "</xs:documentation>\r\n");
      write("           </xs:annotation>\r\n");
    }
    for (TypeRef t : definitions.getKnownTypes()) {
      if (!definitions.getInfrastructure().containsKey(t.getName())
          && !definitions.getConstraints().containsKey(t.getName())) {
        String en = prefix != null ? prefix + upFirst(t.getName()) : t
            .getName();
        if (t.hasParams()) {
          for (String p : t.getParams()) {
            write("           <xs:element name=\"" + en + "_"
                + upFirst(p) + "\" type=\""+ t.getName() + "_" + upFirst(p) + "\"/>\r\n");
          }
        } else if (!definitions.getShared().contains(t.getName()) && !t.getName().equals("oid") && !t.getName().equals("uuid") && !t.getName().equals("id") ) {
          if (t.getName().equals("ResourceReference"))
            write("           <xs:element name=\"" + prefix + "Resource\" type=\"" + t.getName()+ "\"/>\r\n");
          else
            write("           <xs:element name=\"" + en + "\" type=\"" + t.getName()+ "\"/>\r\n");
        }
      }
    }
    write("          </xs:choice>\r\n");
  }

  private void generateElement(ElementDefn root, ElementDefn e,
      String paramType) throws Exception {
    List<TypeRef> types = e.getTypes();
    if (types.size() > 1 || (types.size() == 1 && types.get(0).isWildcardType())) {
      if (!e.getName().contains("[x]"))
        throw new Exception("Element has multiple types as a choice doesn't have a [x] in the element name '"+ e.getName()+ "' in resource "+ root.getName());
      generateAny(root, e, e.getName().replace("[x]", ""));
      // write("<xs:choice>\r\n");
      // if (e.hasDefinition()) {
      // write("        <xs:annotation>\r\n");
      // write("          <xs:documentation>"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
      // write("        </xs:annotation>\r\n");
      // }
      // if (types.size() == 1)
      // types = definitions.getKnownTypes();
      // for (TypeDefn t : types) {
      // if (!definitions.getInfrastructure().containsKey(t.getName())) {
      // if (t.hasParams()) {
      // for (String p : t.getParams()) {
      // String tn = t.getName()+"_"+upFirst(p);
      // String n = e.getName().replace("[x]", upFirst(tn));
      // write("        <xs:element name=\""+n+"\" type=\""+tn+"\"/>\r\n");
      //
      // }
      // } else {
      // String tn = encodeType(e, t, false);
      // String n = e.getName().replace("[x]", upFirst(tn));
      // write("        <xs:element name=\""+n+"\" type=\""+encodeType(e,
      // t, true)+"\"/>\r\n");
      // }
      // }
      // }
      // write("      </xs:choice>\r\n");
    } else {
      write("          ");
      if ("extension".equals(e.getName()))
        write("<xs:element name=\"" + e.getName() + "\" type=\"Extension\" ");
      else if ("div".equals(e.getName()) && e.typeCode().equals("xhtml"))
        write("<xs:element ref=\"xhtml:div\" ");
      else if (e.usesCompositeType())
        write("<xs:element name=\"" + e.getName() + "\" type=\"" + e.typeCode().substring(1) + "\" ");
      else if (types.size() == 0 && e.getElements().size() > 0) {
        int i = 0;
        String tn = root.getName() + "." + upFirst(e.getName()) + (i == 0 ? "" : Integer.toString(i));
        // while (typenames.contains(tn)) {
        // i++;
        // tn = root.getName()+"."+upFirst(e.getName())+ (i == 0 ? "" :
        // Integer.toString(i));
        // }
        write("<xs:element name=\"" + e.getName() + "\" type=\"" + tn + "\" ");
        structures.put(tn, e);
        typenames.add(tn);
      } else if (types.size() == 1) {
        if (types.get(0).isUnboundGenericParam() && paramType != null)
          write("<xs:element name=\"" + e.getName() + "\" type=\"" + paramType + "\" ");
        else if (types.get(0).getName().equals("idref")) {
          write("<xs:element name=\"" + e.getName() + "\" type=\"xmlIdRef\" ");        
        } else if (!Utilities.noString(e.getRegex())) {
          String tn = root.getName()+Utilities.capitalize(e.getName())+"Type";
          regexQueue.put(tn, e.getRegex());
          write("<xs:element name=\"" + e.getName() + "\" type=\"" + tn + "\" ");
        } else           
          write("<xs:element name=\"" + e.getName() + "\" type=\"" + encodeType(e, types.get(0), true) + "\" ");
      } else
        throw new Exception("how do we get here? " + e.getName() + " in " + root.getName() + " " + Integer.toString(types.size()));

      write("minOccurs=\"" + e.getMinCardinality().toString() + "\"");
      if (e.unbounded())
        write(" maxOccurs=\"unbounded\"");
      else
        write(" maxOccurs=\"1\"");

      if (e.hasDefinition()) {
        write(">\r\n");
        write("            <xs:annotation>\r\n");
        write("              <xs:documentation>" + Utilities.escapeXml(e.getDefinition()) + "</xs:documentation>\r\n");
        write("            </xs:annotation>\r\n");
        write("          </xs:element>\r\n");
      } else {
        write("/>\r\n");
      }
    }
  }

  private String upFirst(String name) {
    return name.toUpperCase().charAt(0) + name.substring(1);
  }

  private String encodeType(ElementDefn e, TypeRef type, boolean params)
      throws Exception {
    if (type.isResourceReference())
      return "ResourceReference";
    else if (type.isIdRef())
      return "id-primitive";
    //    else if (params
    //        && definitions.getPrimitives().containsKey(type.getName())
    //        && definitions.getPrimitives().get(type.getName()) instanceof PrimitiveType)
    //      return "xs:"
    //          + ((PrimitiveType) definitions.getPrimitives().get(
    //              type.getName())).getSchemaType();
    else if (type.getName().equals("code")) {
      String en = null;
      if (e.hasBinding()) {
        BindingSpecification cd = definitions.getBindingByName(e.getBindingName());
        if (cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList) {
          en = cd.getName();
          enums.add(en);
          enumDefs.put(en, cd.getDefinition());
          return en;
        }
      }
      return "code";

    } else if (!type.hasParams() || !params) {
      //      if (params && definitions.getPrimitives().containsKey(type.getName())
      //          && !e.unbounded())
      //        return type.getName() + "-primitive";
      //      else
      return type.getName();
    } else if (type.getParams().size() > 1)
      throw new Exception(
          "multiple type parameters are only supported on resource ("
              + type.summary() + ")");
    else
      return type.getName() + "_" + upFirst(type.getParams().get(0));
  }

  public void setDefinitions(Definitions definitions) {
    this.definitions = definitions;
  }
  
  public OutputStreamWriter getWriter() {
    return writer;
  }

  
}
