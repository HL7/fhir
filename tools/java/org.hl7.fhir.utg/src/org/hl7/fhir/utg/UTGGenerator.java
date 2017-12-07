package org.hl7.fhir.utg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.cert.CollectionCertStoreParameters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.r4.formats.IParser.OutputStyle;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.Factory;
import org.hl7.fhir.r4.model.MetadataResource;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.TemporalPrecisionEnum;
import org.hl7.fhir.r4.model.UriType;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.r4.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r4.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.r4.model.ValueSet.FilterOperator;
import org.hl7.fhir.r4.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.r4.model.CodeSystem.CodeSystemHierarchyMeaning;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.r4.model.CodeSystem.ConceptPropertyComponent;
import org.hl7.fhir.r4.model.CodeSystem.PropertyComponent;
import org.hl7.fhir.r4.model.CodeSystem.PropertyType;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ContactDetail;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.utg.v2.V2SourceGenerator;
import org.hl7.fhir.utg.v3.CDASourceGenerator;
import org.hl7.fhir.utg.v3.V3SourceGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CSVReader;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import ca.uhn.fhir.util.XmlUtil;

public class UTGGenerator extends BaseGenerator {

  public static void main(String[] args) throws Exception {
    String dest = args[0]; // the vocabulary repository to populate
    String v2source = args[1]; // access database name
    String v3source = args[2]; // MIF file name
    String cdasource = args[3]; // CDA source
    String nlmUsername = args[4]; // 
    String nlmPassword = args[5]; // 
    new UTGGenerator(dest, v2source, v3source, cdasource, nlmUsername, nlmPassword).execute();
  }


  private Date currentVersionDate;
  private V3SourceGenerator v3;
  private V2SourceGenerator v2;
  private CDASourceGenerator cda;

  public UTGGenerator(String dest, String v2source, String v3source, String cdaSource, String nlmUsername, String nlmPassword) throws IOException, ClassNotFoundException, SQLException, FHIRException, SAXException, ParserConfigurationException {
    super(dest, new HashMap<String, CodeSystem>(), new HashSet<String>());
    v2 = new V2SourceGenerator(dest, csmap, knownCS);
    v3 = new V3SourceGenerator(dest, csmap, knownCS);
    cda = new CDASourceGenerator(dest, csmap, knownCS, nlmUsername, nlmPassword);

    v2.load(v2source);
    v3.load(v3source);
    cda.load(cdaSource);
  
  }


  private void execute() throws Exception {
    v2.loadTables();
    v3.loadMif();
    v2.process();
    generateConceptDomains();
    v2.generateTables();
    v2.generateCodeSystems();
    v3.generateCodeSystems();
    v3.generateValueSets();
    cda.loadValueSetsSource();
    System.out.println("finished");
  }


  

  private void generateConceptDomains() throws FileNotFoundException, IOException {
    CodeSystem cs = new CodeSystem();
    cs.setId("conceptdomains");
    cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/"+cs.getId());
    cs.setName("ConceptDomains");
    cs.setTitle("Concept Domains");
    cs.setStatus(PublicationStatus.ACTIVE);
    cs.setExperimental(false);
    
    cs.setDateElement(new DateTimeType(currentVersionDate, TemporalPrecisionEnum.DAY));
    cs.setPublisher("HL7, Inc");
    cs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("https://github.com/grahamegrieve/vocab-poc");
    cs.setDescription("Concept Domains - includes both v2 abd v3 concept domains");
    cs.setCopyright("Copyright HL7. Licensed under creative commons public domain");
    cs.setCaseSensitive(true); 
    cs.setHierarchyMeaning(CodeSystemHierarchyMeaning.ISA); 
    cs.setCompositional(false);
    cs.setVersionNeeded(false);
    cs.setContent(CodeSystemContentMode.COMPLETE);

    cs.addProperty().setCode("source").setUri("http://something").setType(PropertyType.CODE);
    cs.addProperty().setCode("ConceptualSpaceForClassCode").setUri("http://somethingelse").setType(PropertyType.CODE);
    
    Map<String, String> codes = new HashMap<String, String>();
    
    int count = cs.getConcept().size() + v3.addConceptDomains(cs.getConcept(), codes);
    count = count + v2.addConceptDomains(cs, codes);
     
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "conceptdomains.xml")), cs);
    System.out.println("Save conceptdomains ("+Integer.toString(count)+" found)");
  }

 

  

}
