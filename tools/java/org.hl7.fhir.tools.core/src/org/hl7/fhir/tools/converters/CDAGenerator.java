package org.hl7.fhir.tools.converters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.r4.formats.IParser.OutputStyle;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r4.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.r4.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r4.model.Enumeration;
import org.hl7.fhir.r4.model.Enumerations.BindingStrength;
import org.hl7.fhir.r4.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r4.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r4.model.Type;
import org.hl7.fhir.r4.model.UriType;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class CDAGenerator {

  public class AssociationSorter implements Comparator<Element> {

    @Override
    public int compare(Element arg0, Element arg1) {
      int sk0 = Integer.parseInt(arg0.getAttribute("sortKey"));
      int sk1 = Integer.parseInt(arg1.getAttribute("sortKey"));
      return sk0 - sk1;
    }
  }


  public enum PropStatus {
    IGNORE, 
    ATTRIBUTE, 
    ELEMENT
  }

  public static void main(String[] args) throws Exception {
    int lp = new CDAGenerator().execute("C:\\work\\org.hl7.fhir\\build\\guides\\ccda2\\mapping\\mif", "C:\\work\\org.hl7.fhir\\build\\guides\\ccda2\\mapping\\logical");
    System.out.println("Done. Longest path = "+Integer.toString(lp));
  }

  public int execute(String src, String dst) throws Exception {
    target = dst;
    start();
    processDataTypes(Utilities.path(src, "datatypes.mif"));
    processCDA(Utilities.path(src, "cda.mif"));
    finish();
    System.out.println(v3vs.toString());
    return lp;
  }

  public CDAGenerator() {
    super();
    primitiveTypes.put("BL", "boolean");
    primitiveTypes.put("INT", "integer");
    primitiveTypes.put("REAL", "decimal");
    primitiveTypes.put("TS", "dateTime");
    primitiveTypes.put("ST", "string");
    primitiveTypes.put("BIN", "base64Binary");
    primitiveTypes.put("CS", "code");
  }

  private String target;
  private List<StructureDefinition> structures;
  private Map<String, String> primitiveTypes = new HashMap<String, String>();
  private Map<String, Element> types = new HashMap<String, Element>();
  private Map<String, String> shadows = new HashMap<String, String>();
  private Set<String> v3vs = new HashSet<String>();

  int lp = 0;

  private void start() {
    structures = new ArrayList<StructureDefinition>();
    
    // loading forwardReferences:
//    shadows.put("guardian.guardianPerson", "ClinicalDocument.authenticator.assignedEntity.representedOrganization");
    
    
    
//    bundle.setId(UUID.randomUUID().toString().toLowerCase());
//    bundle.setType(BundleType.COLLECTION);
//    bundle.getFormatCommentsPre().add("\r\nExtensions defined for this set of structure definitions:\r\n"+
//      "  * add xmlText to ElementDefinition.representation: the content of the element does in the XML text for it's parent\r\n"+
//      "    (all of the text, not including text in child elements, with whitespace removed. This won't round-trip completely correctly\r\n"+
//      "\r\n"+
//      "  * add typeAttr to ElementDefinition.representation: instead type choices being handled by n[x] like usual, they are handled\r\n"+
//      "    by xsi:type in XML (and \"type\" : \"[value]\" in json)\r\n"+
//      "\r\n"+
//      "  * add cdaNarrative to ElementDefinition.representation: indicate that the content model of the element is \r\n"+
//      "    a CDA narrative, and must be converted to XHTML by the XML wire format reader\r\n"+
//      "\r\n"+
//      "  * define extension http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace\r\n"+
//      "    value is a URI that is the namespace used in the XML for the content defined in this structure definition.\r\n"+ 
//      "    The namespace will apply until another namespace is specified using the same extension\r\n"+
//      "\r\n");
//    bundle.getFormatCommentsPre().add("\r\nKnown types used with Observation.value, see http://www.healthintersections.com.au/?p=2428\r\n");
  }
  
  private void finish() throws FileNotFoundException, IOException {
    StringBuilder b = new StringBuilder();
    
    for (StructureDefinition sd : structures) {
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(target, sd.getId()+".xml")), sd);
      b.append("   <resource>\r\n"+
          "     <purpose value=\"logical\"/>\r\n"+
          "     <name value=\""+sd.getName()+"\"/>\r\n"+
          "     <sourceUri value=\"cda\\cda-logical-"+sd.getId()+".xml\"/>\r\n"+
          "   </resource>\r\n");
    }
    TextFile.stringToFile(b.toString(), Utilities.path(target, "ig-template.xml"));
//    dumpPaths();
  }

  private void dumpPaths() {
    for (StructureDefinition sd : structures) {
      if (sd.hasBaseDefinition())
        System.out.println("Class "+sd.getId() +" : "+sd.getBaseDefinition().substring(40));
      else
        System.out.println("Class "+sd.getId());
      for (ElementDefinition ed : sd.getDifferential().getElement()) {
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (TypeRefComponent t : ed.getType()) {
          b.append(t.getCode());
        }
        CommaSeparatedStringBuilder b2 = new CommaSeparatedStringBuilder();
        for (Enumeration<PropertyRepresentation> r : ed.getRepresentation())
          if (!r.asStringValue().equals("typeAttr"))
            b2.append(r.asStringValue());
        String s = Utilities.noString(b2.toString()) ? "" : " <<"+b2.toString()+">>";
        if (ed.hasContentReference())
          s = " <<see "+ed.getContentReference().substring(1)+">>";
        System.out.println("  "+ed.getPath()+" ["+ed.getMin()+".."+ed.getMax()+"] : "+b.toString()+s);
      }
    }    
  }

  private void processDataTypes(String filename) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
    System.out.println("Process Data Types");
    Document dtMif = XMLUtil.parseFileToDom(filename);
    List<Element> dts = new ArrayList<Element>();
    XMLUtil.getNamedChildren(dtMif.getDocumentElement(), "mif:datatype", dts);
    for (Element dt : dts) {
      String n = dt.getAttribute("name");
      types.put(n, dt);
      if (n.equals("IVL")) {
        processDataType(dt, n+"_TS", "TS");
        processDataType(dt, n+"_PQ", "PQ");
        processDataType(dt, n+"_INT", "INT");
      } else if (n.equals("PIVL")) {
        processDataType(dt, n+"_TS", null);
      } else if (n.equals("EIVL")) {
        processDataType(dt, n+"_TS", null);
      } else if (n.equals("RTO")) {
        processDataType(dt, n+"_PQ_PQ", "PQ");
      } else if (!"Binding".equals(dt.getAttribute("datatypeKind")))
        processDataType(dt, n, null);
    }
    buildSXPR();
    buildInfrastructureRoot();
    
    for (StructureDefinition sd : structures) {
      if (!sd.getAbstract())
        generateSnapShot(sd);
    }
    System.out.println(" ... done");
  }

  private void generateSnapShot(StructureDefinition sd) {
    sd.getSnapshot().getElement().add(sd.getDifferential().getElement().get(0));
    generateSnapShot(sd, sd, sd.getId());
  }

  private void generateSnapShot(StructureDefinition dst, StructureDefinition src, String typeName) {
    if (dst.hasSnapshot() && dst.getSnapshot().getElement().size() > 1)
      return;
    
    if (src.hasBaseDefinition()) {
      StructureDefinition dt = getDataType(src.getBaseDefinition());
      if (dt != null)
        generateSnapShot(dst, dt, typeName);
    }
    for (ElementDefinition ed : src.getDifferential().getElement()) {
      ElementDefinition ned = ed.copy();
      String path = ed.getPath();
      if (path.contains(".")) {
        path = typeName + path.substring(path.indexOf("."));
        ned.setPath(path);
        seePath(path);
        boolean found = false;
        for (ElementDefinition de : dst.getSnapshot().getElement()) {
          if (de.getPath().equals(path)) 
            found = true;
        }
        if (!found)
          dst.getSnapshot().getElement().add(ned);
      } 
    }
  }


  private void seePath(String path) {
    int l = path.length();
    lp = Math.max(lp, l);
  }

  private StructureDefinition getDataType(String name) {
    name = fix(name);
    for (StructureDefinition sd : structures) {
      if (sd.getUrl().equals(name))
        return sd;
    }
    throw new Error("Data Type "+name+" not found");
  }


  private void buildSXPR() {
    StructureDefinition sd = new StructureDefinition();
    sd.setId(fix("SXPR_TS"));
    sd.setUrl("http://hl7.org/fhir/cda/StructureDefinition/"+fix("SXPR_TS"));
    sd.setName("V3 Data type SXPR_TS (A set-component that is itself made up of set-components that are evaluated as one value)");
    sd.setTitle("SXPR_TS - Component part of GTS");
    sd.setStatus(PublicationStatus.ACTIVE);
    sd.setExperimental(false);
    sd.setPublisher("HL7");
    sd.setDescription("A set-component that is itself made up of set-components that are evaluated as one value");
    sd.setKind(StructureDefinitionKind.LOGICAL);
    sd.setAbstract(false);
    sd.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType("urn:hl7-org:v3"));
    sd.setBaseDefinition("http://hl7.org/fhir/cda/StructureDefinition/SXCM_TS");
    sd.setDerivation(TypeDerivationRule.SPECIALIZATION);

    ElementDefinition edb = new ElementDefinition();
    edb.setPath(sd.getId());
    edb.setMin(1);
    edb.setMax("*");
    edb.addType().setCode("Element");
    sd.getDifferential().getElement().add(edb);

    ElementDefinition ed = new ElementDefinition();
    ed.setPath("SXPR_TS.comp");
    seePath(ed);
    ed.setMin(1);
    ed.setMax("*");
    ed.addRepresentation(PropertyRepresentation.TYPEATTR);
    ed.addType().setCode("IVL_TS");
    ed.addType().setCode("EIVL_TS");
    ed.addType().setCode("PIVL_TS");
    ed.addType().setCode("SXPR_TS");
    sd.getDifferential().getElement().add(ed);
    
    structures.add(sd);
  }

  private void seePath(ElementDefinition ed) {
    seePath(ed.getPath());
  }

  private void buildInfrastructureRoot() {
    StructureDefinition sd = new StructureDefinition();
    sd.setId("InfrastructureRoot");
    sd.setUrl("http://hl7.org/fhir/cda/StructureDefinition/InfrastructureRoot");
    sd.setName("Base Type for all classes in the CDA structure");
    sd.setTitle("InfrastructureRoot");
    sd.setStatus(PublicationStatus.ACTIVE);
    sd.setExperimental(false);
    sd.setPublisher("HL7");
    sd.setDescription("Defines the base elements and attributes on all CDA elements (other than data types)");
    sd.setKind(StructureDefinitionKind.LOGICAL);
    sd.setAbstract(true);
    sd.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType("urn:hl7-org:v3"));
    sd.setBaseDefinition("http://hl7.org/fhir/cda/StructureDefinition/ANY");
    sd.setDerivation(TypeDerivationRule.SPECIALIZATION);

    ElementDefinition edb = new ElementDefinition();
    edb.setPath(sd.getId());
    seePath(edb);
    edb.setMin(1);
    edb.setMax("*");
    edb.addType().setCode("Element");
    sd.getDifferential().getElement().add(edb);

    ElementDefinition ed = new ElementDefinition();
    ed.setPath("InfrastructureRoot.realmCode");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("*");
    ed.setDefinition("When valued in an instance, this attribute signals the imposition of realm-specific constraints. The value of this attribute identifies the realm in question");
    ed.addType().setCode("CS");
    sd.getDifferential().getElement().add(ed);
    
    ed = new ElementDefinition();
    ed.setPath("InfrastructureRoot.typeId");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.setDefinition("When valued in an instance, this attribute signals the imposition of constraints defined in an HL7-specified message type. This might be a common type (also known as CMET in the messaging communication environment), or content included within a wrapper. The value of this attribute provides a unique identifier for the type in question.");
    ed.addType().setCode("II");
    sd.getDifferential().getElement().add(ed);
    
    ed = new ElementDefinition();
    ed.setPath("InfrastructureRoot.templateId");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("*");
    ed.setDefinition("When valued in an instance, this attribute signals the imposition of a set of template-defined constraints. The value of this attribute provides a unique identifier for the templates in question");
    ed.addType().setCode("II");
    sd.getDifferential().getElement().add(ed);
    
    structures.add(sd);
  }


  private void processDataType(Element dt, String n, String p) throws FileNotFoundException, IOException {

    if (!Utilities.existsInList(n, "TYPE", "BN", "BIN", "CO", "UID", "OID", "UUID", "RUID", "URL", "ADXP", "ENXP", "PN", 
        "TN", "ON", "RTO", "CAL", "CLCY", "SET", "LIST", "GLIST", "SLIST", "BAG", "HXIT", "HIST", "UVP", "NPPD", "PPD")) {
      if (n.equals("GTS"))
        n = "SXCM_TS";
      
      System.out.print(" "+n);

      StructureDefinition sd = new StructureDefinition();
      sd.setId(fix(n));
      sd.setUrl("http://hl7.org/fhir/cda/StructureDefinition/"+fix(n));
      sd.setName("V3 Data type "+n+" ("+dt.getAttribute("title")+")");
      sd.setTitle(sd.getName());
      sd.setStatus(PublicationStatus.ACTIVE);
      sd.setExperimental(false);
      sd.setPublisher("HL7");
      sd.setDescription(getDefinition(dt));
      sd.setKind(StructureDefinitionKind.LOGICAL);
      sd.setAbstract("true".equals(dt.getAttribute("isAbstract")));
      sd.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType("urn:hl7-org:v3"));
      Element derived = XMLUtil.getNamedChild(dt, "mif:derivedFrom");
      if (Utilities.existsInList(n, "ST", "ED", "TEL", "AD", "EN", "IVL_PQ", "IVL_INT", "TS") ) {
        sd.setBaseDefinition("http://hl7.org/fhir/cda/StructureDefinition/ANY");
      } else if (Utilities.existsInList(n, "SXCM_TS") ) {
        sd.setBaseDefinition("http://hl7.org/fhir/cda/StructureDefinition/TS");
      } else if (n.equals("PIVL_TS") || n.equals("EIVL_TS") || n.equals("IVL_TS")) {
        sd.setBaseDefinition("http://hl7.org/fhir/cda/StructureDefinition/SXCM_TS");
      } else if (derived != null) {
        sd.setBaseDefinition("http://hl7.org/fhir/cda/StructureDefinition/"+XMLUtil.getNamedChildAttribute(derived, "mif:targetDatatype", "name"));
      }
      sd.setDerivation(TypeDerivationRule.SPECIALIZATION);
      ElementDefinition edb = new ElementDefinition();
      edb.setPath(sd.getId());
      seePath(edb);
      edb.setMin(1);
      edb.setMax("*");
      edb.addType().setCode("Element");
      sd.getDifferential().getElement().add(edb);

      if (n.equals("ED"))
        addEDElements(sd.getDifferential().getElement());
      if (n.equals("SC"))
        copyAttributes(sd, getDefinition("CV"), "code", "codeSystem", "codeSystemVersion", "displayName");
      if (primitiveTypes.containsKey(n))
        addValueAttribute(sd.getDifferential().getElement(), n, primitiveTypes.get(n));
      if (n.equals("TEL"))
        addValueAttribute(sd.getDifferential().getElement(), n, "uri");
      if (n.equals("SXCM_TS")) {
        addOperatorAttribute(sd.getDifferential().getElement(), "SXCM_TS");
        sd.setAbstract(true);
      }
      if (n.equals("AD")) {
        addParts(sd.getDifferential().getElement(), n, "delimiter", "country", "state", "county", "city", "postalCode", "streetAddressLine", "houseNumber", 
            "houseNumberNumeric", "direction", "streetName", "streetNameBase", "streetNameType", "additionalLocator", "unitID", "unitType", "careOf", "censusTract", 
            "deliveryAddressLine", "deliveryInstallationType", "deliveryInstallationArea", "deliveryInstallationQualifier", "deliveryMode", "deliveryModeIdentifier", 
            "buildingNumberSuffix", "postBox", "precinct");
        addTextItem(sd.getDifferential().getElement(), n);
      }
      if (n.equals("EN")) {
        addParts(sd.getDifferential().getElement(), n, "delimiter", "family", "given", "prefix", "suffix");
        addTextItem(sd.getDifferential().getElement(), n);
      }
      List<Element> props = new ArrayList<Element>();
      XMLUtil.getNamedChildren(dt, "mif:property", props);
      for (Element prop : props) {
        processProperty(sd.getDifferential().getElement(), n, prop, p);
      }
        
      if (n.equals("TS") || n.equals("PQ") )
        addInclusiveAttribute(sd.getDifferential().getElement(), n);
      if (n.equals("CE") || n.equals("CV") || n.equals("CD") )
        addCDExtensions(sd.getDifferential().getElement(), n);
      structures.add(sd);
    }
  }

  private void copyAttributes(StructureDefinition target, StructureDefinition source, String... names) {
    for (ElementDefinition ed : source.getDifferential().getElement()) {
      boolean copy = false;
      for (String name : names) {
        if (ed.getPath().endsWith("."+name))
          copy = true;
      }
      if (copy) {
        ElementDefinition n = ed.copy();
        n.setPath(ed.getPath().replace(source.getId(), target.getId()));
        seePath(n);
        target.getDifferential().getElement().add(n);
      }
    }
  }


  private StructureDefinition getDefinition(String id) {
    for (StructureDefinition sd : structures) {
      if (sd.getId().equals(id))
        return sd;
    }
    return null;
  }


  private void addInclusiveAttribute(List<ElementDefinition> list, String n) {
    ElementDefinition ed = new ElementDefinition();
    ed.setPath(n+".inclusive");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode("boolean");
    ed.addRepresentation(PropertyRepresentation.XMLATTR);
    list.add(ed);
  }

  private void addCDExtensions(List<ElementDefinition> list, String n) {
    ElementDefinition ed = new ElementDefinition();
    ed.setPath(n+".valueSet");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode("string");
    ed.addRepresentation(PropertyRepresentation.XMLATTR);
    ed.setDefinition("The valueSet extension adds an attribute for elements with a CD dataType which indicates the particular value set constraining the coded concept");
    ed.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType("urn:hl7-org:sdtc"));
    ed = new ElementDefinition();
    ed.setPath(n+".valueSetVersion");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode("string");
    ed.addRepresentation(PropertyRepresentation.XMLATTR);
    ed.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType("urn:hl7-org:sdtc"));
    ed.setDefinition("The valueSetVersion extension adds an attribute for elements with a CD dataType which indicates the version of the particular value set constraining the coded concept.");
    list.add(ed);
  }

  private void addOperatorAttribute(List<ElementDefinition> list, String n) {
    ElementDefinition ed = new ElementDefinition();
    ed.setPath(n+".operator");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode("code");
    ed.addRepresentation(PropertyRepresentation.XMLATTR);
    list.add(ed);

  }

  private void addTextItem(List<ElementDefinition> list, String n) {
    ElementDefinition ed = new ElementDefinition();
    ed.setPath(n+".other");
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode("string");
    ed.addRepresentation(PropertyRepresentation.XMLTEXT);
    list.add(ed);
  }

  private void addParts(List<ElementDefinition> list, String n, String... parts) {
    for (String p : parts) {
      ElementDefinition ed = new ElementDefinition();
      ed.setPath(n+"."+p);
      seePath(ed);
      ed.setMin(0);
      ed.setMax("*");
      ed.addType().setCode("ST");
      list.add(ed);
    }
  }

  private void addEDElements(List<ElementDefinition> list) {
    ElementDefinition ed = new ElementDefinition();
    ed.setPath("ED.representation");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode("code");
    ed.addRepresentation(PropertyRepresentation.XMLATTR);
    list.add(ed);
    ed = new ElementDefinition();
    ed.setPath("ED.data");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode("base64Binary");
    ed.addRepresentation(PropertyRepresentation.XMLTEXT);
    list.add(ed);
  }

  private void addValueAttribute(List<ElementDefinition> list, String dtn, String t) {
    ElementDefinition ed = new ElementDefinition();
    ed.setPath(dtn+".value");
    seePath(ed);
    ed.setMin(0);
    ed.setMax("1");
    ed.addType().setCode(t);
    if (dtn.equals("ST"))
      ed.addRepresentation(PropertyRepresentation.XMLTEXT);
    else
      ed.addRepresentation(PropertyRepresentation.XMLATTR);
    if (dtn.equals("TS"))
      ed.addExtension().setUrl("http://www.healthintersections.com.au/fhir/StructureDefinition/elementdefinition-dateformat").setValue(new StringType("v3"));
    list.add(ed);
  }

  private void processProperty(List<ElementDefinition> list, String dtn, Element prop, String param) {
    ElementDefinition ed = new ElementDefinition();
    String n = prop.getAttribute("name");
    PropStatus p = getPropStatus(dtn, n);
    if (p == PropStatus.IGNORE)
      return;
    if (prop.hasAttribute("fixedValue") && prop.getAttribute("fixedValue").startsWith("NullFlavor.") )
      return;

    ed.setPath(dtn+"."+n);
    seePath(ed);
    ed.setDefinition(getDefinition(prop));
    ed.setComment(getDesignComments(prop));
    ed.setLabel(XMLUtil.getNamedChildAttribute(prop, "mif:businessName", "name"));
    ed.setMin(Integer.parseInt(prop.getAttribute("minimumMultiplicity")));
    ed.setMax(prop.getAttribute("maximumMultiplicity"));
    String t = getType(prop);
    if (primitiveTypes.containsKey(t) && p == PropStatus.ATTRIBUTE)
      ed.addType().setCode(primitiveTypes.get(t));
    else if (Utilities.existsInList(t, "UID"))
      ed.addType().setCode("string");
    else if (t.equals("T") && param != null)
      ed.addType().setCode(param);
    else if (t.equals("N") && param != null)
      ed.addType().setCode(param);
    else if (t.equals("D") && param != null)
      ed.addType().setCode(param);
    else if (t.equals("QTY") && param != null)
      ed.addType().setCode("PQ");
    else if (t.equals("IVL_T"))
      ed.addType().setCode("IVL_TS");
    else if ("GTS".equals(t)) {
      ed.setMax("*");
      ed.addRepresentation(PropertyRepresentation.TYPEATTR);
      ed.addType().setCode("IVL_TS");
      ed.addType().setCode("EIVL_TS");
      ed.addType().setCode("PIVL_TS");
      ed.addType().setCode("SXPR_TS");
      ed.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-defaulttype").setValue(new StringType("SXPR_TS"));
    } else
      ed.addType().setCode(t);
    if (p == PropStatus.ATTRIBUTE)
      ed.addRepresentation(PropertyRepresentation.XMLATTR);
    // special stuff
    String vs = getValueSet(dtn, n);
    if (vs != null) {
      ed.setBinding(new ElementDefinitionBindingComponent().setStrength(BindingStrength.REQUIRED).setValueSet(new Reference().setReference("http://hl7.org/fhir/ValueSet/v3-"+vs)));
      v3vs.add(vs);
    }
    list.add(ed);
  }

  private String getType(Element prop) {
    Element type = XMLUtil.getNamedChild(prop, "mif:type");
    if (type == null)
      type = XMLUtil.getNamedChild(prop, "type");
    String t = type.getAttribute("name");
    if (Utilities.existsInList(t, "SET", "LIST")) {
      String s = XMLUtil.getNamedChildAttribute(type, "mif:argumentDatatype", "name");
      if (s == null)
        s = XMLUtil.getNamedChildAttribute(type, "argumentDatatype", "name");
      return s;
    }
    List<Element> params = new ArrayList<Element>();
    XMLUtil.getNamedChildren(type, "mif:argumentDatatype", params);
    if (params.isEmpty())
      XMLUtil.getNamedChildren(type, "argumentDatatype", params);
    for (Element p : params)
      t = t + "_" + p.getAttribute("name");
    return t;
  }

  private String getValueSet(String dtn, String n) {
    if ("ANY".equals(dtn) && "nullFlavor".equals(n))
      return "NullFlavor";
    if ("ED".equals(dtn) && "compression".equals(n))
      return "CompressionAlgorithm";
    if ("ED".equals(dtn) && "integrityCheckAlgorithm".equals(n))
      return "IntegrityCheckAlgorithm";
    if ("TEL".equals(dtn) && "use".equals(n))
      return "AddressUse";
    return null;
  }

  private PropStatus getPropStatus(String dtn, String n) {
    if (Utilities.existsInList(n, "equal", "diffType", "literal", "demotion", "promotion"))
      return PropStatus.IGNORE;

    if (dtn.equals("ANY")) {
      if (n.equals("nullFlavor"))
        return PropStatus.ATTRIBUTE;
      else
        return PropStatus.IGNORE;
    } else if (Utilities.existsInList(dtn, "BL")) {
      return PropStatus.IGNORE;
    } else if (Utilities.existsInList(dtn, "ED")) {
      if (Utilities.existsInList(n, "language", "mediaType", "charset", "compression", "integrityCheck", "integrityCheckAlgorithm"))
        return PropStatus.ATTRIBUTE;
      else
        return PropStatus.ELEMENT;
    } else if (Utilities.existsInList(dtn, "CD", "CV", "CE")) {
      if (Utilities.existsInList(n, "code", "codeSystem", "codeSystemName", "codeSystemVersion", "displayName"))
        return PropStatus.ATTRIBUTE;
      else if (Utilities.existsInList(n, "implies"))
        return PropStatus.IGNORE;
      else
        return PropStatus.ELEMENT;
    } else if (Utilities.existsInList(dtn, "CR")) {
      if (Utilities.existsInList(n, "inverted"))
        return PropStatus.ATTRIBUTE;
      else
        return PropStatus.ELEMENT;
    } else if (Utilities.existsInList(dtn, "II")) {
      if (Utilities.existsInList(n, "scope", "reliability"))
        return PropStatus.IGNORE;
      else
        return PropStatus.ATTRIBUTE;
    } else if (Utilities.existsInList(dtn, "AD", "EN")) {
      if (Utilities.existsInList(n, "useablePeriod", "validTime"))
        return PropStatus.ELEMENT;
      else if (Utilities.existsInList(n, "isNotOrdered", "use"))
        return PropStatus.ATTRIBUTE;
      else
        return PropStatus.IGNORE;
    } else if (Utilities.existsInList(dtn, "TEL")) {
      if (Utilities.existsInList(n, "useablePeriod"))
        return PropStatus.ELEMENT;
      else
        return PropStatus.ATTRIBUTE;
      //    } else if (Utilities.existsInList(dtn, "GLIST")) {
      //      if (Utilities.existsInList(n, "head", "increment"))
      //        return PropStatus.ELEMENT;
      //      else
      //        return PropStatus.ATTRIBUTE;
    } else if (Utilities.existsInList(dtn, "PQ")) {
      if (Utilities.existsInList(n, "translation"))
        return PropStatus.ELEMENT;
      else if (Utilities.existsInList(n, "canonical", "compares", "isOne", "times", "inverted", "power", "plus"))
        return PropStatus.IGNORE;
      else
        return PropStatus.ATTRIBUTE;
    } else if (Utilities.existsInList(dtn, "MO")) {
      if (Utilities.existsInList(n, "value", "currency"))
        return PropStatus.ELEMENT;
      else 
        return PropStatus.IGNORE;
    } else if (Utilities.existsInList(dtn, "PQR")) {
      if (Utilities.existsInList(n, "value"))
        return PropStatus.ATTRIBUTE;
      else
        return PropStatus.IGNORE;
    } else if (dtn.startsWith("IVL_")) {
      if (Utilities.existsInList(n, "operator"))
        return PropStatus.ATTRIBUTE;
      else if (Utilities.existsInList(n, "lowClosed", "highClosed", "hull"))
        return PropStatus.IGNORE;
      else
        return PropStatus.ELEMENT;
    } else if (dtn.startsWith("PIVL")) {
      if (Utilities.existsInList(n, "operator", "alignment", "institutionSpecifiedTime"))
        return PropStatus.ATTRIBUTE;
      else if (Utilities.existsInList(n, "hull"))
        return PropStatus.IGNORE;
      else
        return PropStatus.ELEMENT;
    } else if (dtn.startsWith("RTO_")) {
      return PropStatus.ELEMENT;
    } else if (dtn.startsWith("EIVL")) {
      if (Utilities.existsInList(n, "operator"))
        return PropStatus.ATTRIBUTE;
      else if (Utilities.existsInList(n, "hull", "occurrenceAt"))
        return PropStatus.IGNORE;
      else
        return PropStatus.ELEMENT;
    } else
      return PropStatus.IGNORE;
  }

  private String getDefinition(Element element) {
    List<Element> annots = new ArrayList<Element>();
    XMLUtil.getNamedChildren(element, "mif:annotations", annots);
    for (Element annot : annots) {
      List<Element> docs = new ArrayList<Element>();
      XMLUtil.getNamedChildren(annot, "mif:documentation", docs);
      for (Element doc : docs) {
        List<Element> defs = new ArrayList<Element>();
        XMLUtil.getNamedChildren(doc, "mif:definition", defs);
        for (Element def : defs) {
          List<Element> texts = new ArrayList<Element>();
          XMLUtil.getNamedChildren(def, "mif:text", texts);
          for (Element text : texts) {
            return correctWhiteSpace(text.getTextContent());
          }
        }
      }  
    }
    return null;
  }

  private String getDesignComments(Element element) {
    List<Element> annots = new ArrayList<Element>();
    XMLUtil.getNamedChildren(element, "mif:annotations", annots);
    for (Element annot : annots) {
      List<Element> docs = new ArrayList<Element>();
      XMLUtil.getNamedChildren(annot, "mif:documentation", docs);
      for (Element doc : docs) {
        List<Element> defs = new ArrayList<Element>();
        XMLUtil.getNamedChildren(doc, "designComments", defs);
        for (Element def : defs) {
          List<Element> texts = new ArrayList<Element>();
          XMLUtil.getNamedChildren(def, "mif:text", texts);
          for (Element text : texts) {
            return correctWhiteSpace(text.getTextContent());
          }
        }
      }  
    }
    return null;
  }

  private String correctWhiteSpace(String s) {
    s = s.replace("\r", " ");
    s = s.replace("\n", " ");
    while (s.contains("  "))
      s = s.replace("  ", " ");
    return s.trim();
  }


  private Set<String> processed = new HashSet<String>();
  private Set<String> waiting = new HashSet<String>();
  
  private void processCDA(String filename) throws ParserConfigurationException, SAXException, IOException {
    System.out.println("Process Structure");

    Document pocd = XMLUtil.parseFileToDom(filename);
    List<Element> classes = new ArrayList<Element>();
    List<Element> associations = new ArrayList<Element>();
    XMLUtil.getNamedChildren(pocd.getDocumentElement(), "containedClass", classes);
    XMLUtil.getNamedChildren(pocd.getDocumentElement(), "association", associations);
    Collections.sort(associations, new AssociationSorter());

    waiting.add("ClinicalDocument");
    while (!waiting.isEmpty()) {
      for (String n : waiting) {
        processClass(classes, associations, n);
        break;
      }
    }
    
    // todo: checkTypes(sd);
    
  }


  private void checkTypes(StructureDefinition sd) {
    for (ElementDefinition ed : sd.getDifferential().getElement()) {
      for (TypeRefComponent t : ed.getType()) {
        checkType(t);
      }
    }
  }


  private void checkType(TypeRefComponent t) {
    String id = t.getCode();
    if (Utilities.existsInList(id, "string", "BackboneElement", "code", "boolean", "Resource"))
      return;
    for (StructureDefinition sd : structures) {
      if (sd.getId().equals(fix(id)))
        return;
    }
    if (id.equals("PN") || id.equals("ON"))
      t.setCode("EN");
    else if (id.equals("NARRATIVE"))
      t.setCode("xhtml");
    else
      System.out.println("Unknown data type "+id);
  }


  private String fix(String id) {
    return id; //.replace("_", ".");
  }

  private void processClass(List<Element> classes, List<Element> associations, String className) {
    Element cclass = null;
    for (Element cclss : classes) {
      String cname = XMLUtil.getNamedChildAttribute(cclss, "class", "name");
      if (cname.equals(className)) {
        cclass = cclss;
        break;
      }
    }
    if (cclass == null)
      throw new Error("Unable to find class "+className);
    processed.add(className);
    waiting.remove(className);
    
    StructureDefinition sd = new StructureDefinition();
    sd.setId(className);
    sd.setUrl("http://hl7.org/fhir/cda/StructureDefinition/"+className);
    sd.setName("CDAR2."+className);
    sd.setTitle("FHIR Definition for CDA R2 Class "+className);
    sd.setStatus(PublicationStatus.ACTIVE);
    sd.setExperimental(false);
    sd.setPublisher("HL7");
    sd.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType("urn:hl7-org:v3"));
    if ("ClinicalDocument".equals(className)) {
      sd.setDescription("This is a generated StructureDefinition that describes CDA - that is, CDA as it actually is for R2. "+
        "The intent of this StructureDefinition is to enable CDA to be a FHIR resource. That enables the FHIR infrastructure "+
        "- API, conformance, query - to be used directly against CDA");
    }
    sd.setKind(StructureDefinitionKind.LOGICAL);
    sd.setAbstract(false);
    sd.setBaseDefinition("http://hl7.org/fhir/cda/StructureDefinition/Element");

    ElementDefinition ed = new ElementDefinition();
    ed.setPath(className);
    seePath(ed);
    ed.setMin(1);
    ed.setMax("1");
    sd.getDifferential().getElement().add(ed);
    sd.getSnapshot().getElement().add(ed);
    processClassAttributes(classes, associations, sd.getDifferential().getElement(), sd.getSnapshot().getElement(), cclass, className, null);
    structures.add(sd);
  }


  private void processClassAttributes(List<Element> classes, List<Element> associations, List<ElementDefinition> diff, List<ElementDefinition> snapshot, Element cclss, String path, Element parentTarget) {
//    System.out.println("  ... "+path);
    addInfrastructureRootAttributes(snapshot, path);
    List<Element> attrs = new ArrayList<Element>();
    XMLUtil.getNamedChildren(XMLUtil.getFirstChild(cclss), "attribute", attrs);
    for (Element attr : attrs) {
      processAttribute(classes, diff, snapshot, cclss, path, attr);
    }
    // ok, we've done the attributes. Now, the assoications
    for (Element association : associations) {
      Element conn = XMLUtil.getNamedChild(association, "connections");
      Element tc = XMLUtil.getNamedChild(conn, "traversableConnection");
      Element nc = XMLUtil.getNamedChild(conn, "nonTraversableConnection");
      if (nc.getAttribute("participantClassName").equals(XMLUtil.getFirstChild(cclss).getAttribute("name"))) {
        processAssociation(classes, associations, diff, snapshot, cclss, path, tc);
      }
    }
    if (parentTarget != null)
      for (Element association : associations) {
        Element conn = XMLUtil.getNamedChild(association, "connections");
        Element tc = XMLUtil.getNamedChild(conn, "traversableConnection");
        Element nc = XMLUtil.getNamedChild(conn, "nonTraversableConnection");
        if (nc.getAttribute("participantClassName").equals(XMLUtil.getFirstChild(parentTarget).getAttribute("name"))) {
          processAssociation(classes, associations, diff, snapshot, parentTarget, path, tc);
        }
      }
  }


  private void addInfrastructureRootAttributes(List<ElementDefinition> list, String path) {
    StructureDefinition any = getDataType("http://hl7.org/fhir/cda/StructureDefinition/ANY");
    addAbstractClassAttributes(list, path, any);
    StructureDefinition ir = getDataType("http://hl7.org/fhir/cda/StructureDefinition/InfrastructureRoot");
    addAbstractClassAttributes(list, path, ir);
  }


  private void addAbstractClassAttributes(List<ElementDefinition> list, String path, StructureDefinition ir) {
    for (ElementDefinition ed : ir.getDifferential().getElement()) {
      if (ed.getPath().contains(".")) {
        ElementDefinition ned = ed.copy();
        ned.setPath(path+"."+ed.getPath().substring(ed.getPath().lastIndexOf(".")+1));
        seePath(ned);
        list.add(ned);
      }
    }
  }


  private void processAttribute(List<Element> classes, List<ElementDefinition> diff, List<ElementDefinition> snapshot, Element cclss, String path, Element attr) {
    String n = attr.getAttribute("name");
    ElementDefinition ed = new ElementDefinition();
    ed.setPath(path+"."+n);
    seePath(ed);
    ed.setMin(Integer.parseInt(attr.getAttribute("minimumMultiplicity")));
    ed.setMax(attr.getAttribute("maximumMultiplicity"));
    if (!Utilities.noString(attr.getAttribute("namespace")))
      ed.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType(attr.getAttribute("namespace")));
    String type = getType(attr);
    if ("true".equals(attr.getAttribute("isImmutable"))) {
      if (primitiveTypes.containsKey(type))
        type = primitiveTypes.get(type);
      else
        throw new Error("Immutable attribute that is not a primitive type"); 
      ed.addRepresentation(PropertyRepresentation.XMLATTR);
    }
    if ("GTS".equals(type)) {
      ed.setMax("*");
      ed.addRepresentation(PropertyRepresentation.TYPEATTR);
      ed.addType().setCode("IVL_TS");
      ed.addType().setCode("EIVL_TS");
      ed.addType().setCode("PIVL_TS");
      ed.addType().setCode("SXPR_TS");
      ed.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-defaulttype").setValue(new StringType("SXPR_TS"));
    } else if ("ANY".equals(type)) {
      ed.addRepresentation(PropertyRepresentation.TYPEATTR);
      ed.addType().setCode("BL");
      ed.addType().setCode("ED");
      ed.addType().setCode("ST");
      ed.addType().setCode("CD");
      ed.addType().setCode("CV");
      ed.addType().setCode("CE");
      ed.addType().setCode("SC");
      ed.addType().setCode("II");
      ed.addType().setCode("TEL");
      ed.addType().setCode("AD");
      ed.addType().setCode("EN");
      ed.addType().setCode("INT");
      ed.addType().setCode("REAL");
      ed.addType().setCode("PQ");
      ed.addType().setCode("MO");
      ed.addType().setCode("TS");
      ed.addType().setCode("IVL_PQ");
      ed.addType().setCode("IVL_TS");
      ed.addType().setCode("PIVL_TS");
      ed.addType().setCode("EIVL_TS");
      ed.addType().setCode("SXPR_TS");      
    } else 
      ed.addType().setCode(type);
    if ("R".equals(attr.getAttribute("conformance"))) 
      ed.setMustSupport(true);
    if (attr.hasAttribute("defaultValue")) 
      ed.setDefaultValue(buildValue(attr.getAttribute("defaultValue"), type, ed.getPath()));

    List<Element> enums = new ArrayList<Element>();
    XMLUtil.getNamedChildren(attr, "enumerationValue", enums);
    if (enums.size() == 1)
      ed.setFixed(buildValue(enums.get(0).getTextContent(), type, ed.getPath()));
    if (enums.size() > 1) {
//      throw new Error("todo: enums on "+ed.getPath());      
    } else if (XMLUtil.getNamedChild(attr, "vocabulary") != null) {
      // <vocabulary codingStrength="CWE"><conceptDomain name="ActClass"/></vocabulary>
      Element vocab = XMLUtil.getNamedChild(attr, "vocabulary");
      String cs = vocab.getAttribute("codingStrength");
      String cd = XMLUtil.getNamedChildAttribute(vocab, "conceptDomain", "name");
      ElementDefinitionBindingComponent bd = ed.getBinding();
      bd.setStrength(cs.equals("CNE") ? BindingStrength.REQUIRED : BindingStrength.EXTENSIBLE);
      bd.setValueSet(new Reference("http://hl7.org/fhir/ValueSet/v3-"+cd));
      v3vs.add(cd);
    }
    diff.add(ed);    
    snapshot.add(ed);    
  }

  private void processAssociation(List<Element> classes, List<Element> associations, List<ElementDefinition> diff, List<ElementDefinition> snapshot, Element cclss, String path, Element tc) {
    Element target = getClass(classes, tc.getAttribute("participantClassName"));
    if (isChoice(target)) {
      // we create an element for each participant choice, and use the child class instead
      List<Element> choices = new ArrayList<Element>();
      XMLUtil.getNamedChildren(tc, "choiceItem", choices);
      for (Element choice : choices) {
        String n = choice.getAttribute("traversalName");
        Element choiceClass = getClass(classes, choice.getAttribute("className"));
        Element c = XMLUtil.getNamedChild(choiceClass, "class");
        processAssociationClass(classes, associations, diff, snapshot, path, tc, choiceClass, c.getAttribute("name"), getDerivation(c), n, target);
      }
    } else {
      String n = tc.getAttribute("name");
      Element c = XMLUtil.getNamedChild(target, "class");
      processAssociationClass(classes, associations, diff, snapshot, path, tc, target, c.getAttribute("name"), getDerivation(c), n, null);
    }
  }

  private String getDerivation(Element element) {
    Element der = XMLUtil.getNamedChild(element, "derivedFrom");
    if (der == null)
      return null;
    else
      return der.getAttribute("className");
  }

  private void processAssociationClass(List<Element> classes, List<Element> associations, List<ElementDefinition> diff, List<ElementDefinition> snapshot, String path, Element tc, Element target, String t, String derivation, String n, Element parentTarget) {
    ElementDefinition ed = new ElementDefinition();
    String ipath = (path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path)+"."+n;
    
    ed.setPath(path+"."+n);
    seePath(ed);
    ed.setMin(Integer.parseInt(tc.getAttribute("minimumMultiplicity")));
    ed.setMax(tc.getAttribute("maximumMultiplicity"));
    ed.addType().setCode("BackboneElement");
    if ("R".equals(tc.getAttribute("conformance"))) 
      ed.setMustSupport(true);
    diff.add(ed);    
    snapshot.add(ed);    

    if (Utilities.existsInList(derivation, "ActRelationship", "Participation")) // these aren't entry points, so we walk into them
      processClassAttributes(classes, associations, diff, snapshot, target, ed.getPath(), parentTarget);
    else {
      ed.addType().setCode("http://hl7.org/fhir/cda/StructureDefinition/"+t);
      if (!processed.contains(t))
        waiting.add(t);
    }
  }

  private boolean isChoice(Element e) {
    return XMLUtil.hasNamedChild(XMLUtil.getFirstChild(e), "childClass");
  }


  private Element getClass(List<Element> classes, String name) {
    for (Element clss : classes) {
      if (XMLUtil.getNamedChildAttribute(clss, "class", "name").equals(name))
        return clss;
    }
    throw new Error("Unable to find class "+name);
  }


  private Type buildValue(String value, String type, String path) {
    if (type.equals("string"))
      return new StringType(value);
    else if (type.equals("code"))
      return new CodeType(value);
    else if (type.equals("boolean"))
      return new BooleanType(value);
    else if (type.equals("CS"))
      return new CodeType(value); // hack
    else
      throw new Error("Unhandled type: "+type+" @ "+path);
  }

}
