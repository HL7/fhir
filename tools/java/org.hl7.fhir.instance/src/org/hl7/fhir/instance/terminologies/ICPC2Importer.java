package org.hl7.fhir.instance.terminologies;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * This is defined as a prototype ClaML importer
 * 
 * @author Grahame
 *
 */

public class ICPC2Importer {

  public static void main(String[] args) {
    try {
      ICPC2Importer r = new ICPC2Importer();
      r.setSourceFileName("c:\\temp\\ICPC-2e-v5.0.xml");
      r.setTargetFileName("C:\\temp\\icpc2.xml");
      r.go();
      System.out.println("Completed OK");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String sourceFileName; // the ICPC2 ClaML file
  private String targetFileName; // the value set to produce
  
  public ICPC2Importer() {
    super();
  }
  public ICPC2Importer(String sourceFileName, String targetFileName) {
    super();
    this.sourceFileName = sourceFileName;
    this.targetFileName = targetFileName;
  }
  public String getSourceFileName() {
    return sourceFileName;
  }
  public void setSourceFileName(String sourceFileName) {
    this.sourceFileName = sourceFileName;
  }
  public String getTargetFileName() {
    return targetFileName;
  }
  public void setTargetFileName(String targetFileName) {
    this.targetFileName = targetFileName;
  }

  public void go() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(false);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new FileInputStream(sourceFileName));

    ValueSet vs = new ValueSet();
    vs.setUrl("http://hl7.org/fhir/sid/icpc2");
    Element title = XMLUtil.getNamedChild(doc.getDocumentElement(), "Title");
    vs.setVersion(title.getAttribute("version"));
    vs.setName(title.getAttribute("name"));
    vs.setImmutable(true);
    Element identifier = XMLUtil.getNamedChild(doc.getDocumentElement(), "Identifier");
    vs.setPublisher(identifier.getAttribute("authority"));
    vs.setIdentifier(new Identifier().setValue(identifier.getAttribute("uid")));
    List<Element> authors = new ArrayList<Element>(); 
    XMLUtil.getNamedChildren(XMLUtil.getNamedChild(doc.getDocumentElement(), "Authors"), "Author", authors);
    for (Element a : authors)
      if (!a.getAttribute("name").contains("+"))
        vs.addContact().setName(a.getTextContent());
    vs.setCopyright("The copyright of ICPC, both in hard copy and in electronic form, is owned by Wonca. See http://www.kith.no/templates/kith_WebPage____1110.aspx");
    vs.setStatus(ConformanceResourceStatus.ACTIVE);
    vs.setDateElement(new DateTimeType(title.getAttribute("date")));
    vs.setDefine(new ValueSetDefineComponent());
    vs.getDefine().setSystem("http://hl7.org/fhir/sid/icpc2/cs");
    
    Map<String, ConceptDefinitionComponent> concepts = new HashMap<String, ConceptDefinitionComponent>();
    List<Element> classes = new ArrayList<Element>(); 
    XMLUtil.getNamedChildren(doc.getDocumentElement(), "Class", classes);
    for (Element cls : classes) {
      processClass(cls, concepts, vs.getDefine());
    }
    
    XmlParser xml = new XmlParser();
    xml.setOutputStyle(OutputStyle.PRETTY);
    xml.compose(new FileOutputStream(targetFileName), vs);
  }
  private void processClass(Element cls, Map<String, ConceptDefinitionComponent> concepts, ValueSetDefineComponent define) {
    ConceptDefinitionComponent concept = new ConceptDefinitionComponent();
    concept.setCode(cls.getAttribute("code"));
    concept.setDefinition(getRubric(cls, "preferred"));
    String s = getRubric(cls, "shortTitle");
    if (s != null && !s.equals(concept.getDefinition()))
      concept.addDesignation().setUse(new Coding().setSystem("http://hl7.org/fhir/sid/icpc2/rubrics").setCode("shortTitle")).setValue(s);
    s = getRubric(cls, "inclusion");
    if (s != null)
      concept.addDesignation().setUse(new Coding().setSystem("http://hl7.org/fhir/sid/icpc2/rubrics").setCode("inclusion")).setValue(s);
    s = getRubric(cls, "exclusion");
    if (s != null)
      concept.addDesignation().setUse(new Coding().setSystem("http://hl7.org/fhir/sid/icpc2/rubrics").setCode("exclusion")).setValue(s);
    s = getRubric(cls, "criteria");
    if (s != null)
      concept.addDesignation().setUse(new Coding().setSystem("http://hl7.org/fhir/sid/icpc2/rubrics").setCode("criteria")).setValue(s);
    s = getRubric(cls, "consider");
    if (s != null)
      concept.addDesignation().setUse(new Coding().setSystem("http://hl7.org/fhir/sid/icpc2/rubrics").setCode("consider")).setValue(s);
    s = getRubric(cls, "note");
    if (s != null)
      concept.addDesignation().setUse(new Coding().setSystem("http://hl7.org/fhir/sid/icpc2/rubrics").setCode("note")).setValue(s);
    
    concepts.put(concept.getCode(), concept);
    List<Element> children = new ArrayList<Element>(); 
    XMLUtil.getNamedChildren(cls, "SubClass", children);
    if (children.size() > 0)
      concept.setAbstract(true);
    
    Element parent = XMLUtil.getNamedChild(cls, "SuperClass");
    if (parent == null) {
      define.addConcept(concept);
    } else {
      ConceptDefinitionComponent p = concepts.get(parent.getAttribute("code"));
      p.getConcept().add(concept);
    }
  }
  
  private String getRubric(Element cls, String kind) {
    List<Element> rubrics = new ArrayList<Element>(); 
    XMLUtil.getNamedChildren(cls, "Rubric", rubrics);
    for (Element r : rubrics) {
      if (r.getAttribute("kind").equals(kind))
        return XMLUtil.getNamedChild(r,  "Label").getTextContent();
    }
    return null;
  }
  
}
