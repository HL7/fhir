package org.hl7.fhir.convertors.v2;

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
import org.hl7.fhir.convertors.v2.VocabularyPOCGenerator.ConceptDomain;
import org.hl7.fhir.convertors.v2.VocabularyPOCGenerator.VersionInfo;
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

public class VocabularyPOCGenerator {

  public static void main(String[] args) throws Exception {
    String dest = args[0]; // the vocabulary repository to populate
    String v2source = args[1]; // access database name
    String v3source = args[2]; // MIF file name
    String cdasource = args[3]; // MIF file name
    new VocabularyPOCGenerator(dest, v2source, v3source, cdasource).execute();
  }

  public class TableEntryComparator implements Comparator<TableEntry> {

    @Override
    public int compare(TableEntry arg0, TableEntry arg1) {
      return arg0.sortNo - arg1.sortNo;
    }
  }

  public class TableEntry {
    private String code;
    private String display;
    private Map<String, String> langs = new HashMap<String, String>();
    private String comments;
    private int sortNo;
    private String first;
    private String last;
    
    public boolean hasLang(String code) {
      return langs.containsKey(code);
    }
    public TableEntry copy() {
      TableEntry result = new TableEntry();
      result.code = code;
      result.display = display;
      result.langs.putAll(langs);
      result.comments = comments;
      result.sortNo = sortNo;
      return result;
    }
    public String getFirst() {
      return first;
    }
    public void setFirst(String first) {
      this.first = first;
    }
    public String getLast() {
      return last;
    }
    public void setLast(String last) {
      this.last = last;
    }
    
  }

  public class TableVersion {
    private String version;
    private String name;
    private String csoid;
    private String csversion;
    private String vsoid;
    private String steward;
    private List<TableEntry> entries = new ArrayList<TableEntry>();
    public TableVersion(String version, String name) {
      this.version = version;
      this.name = name;
    }
    public boolean hasLang(String code) {
      for (TableEntry v : entries)
        if (v.hasLang(code))
          return true;
      return false;
    }
    public void sort() {
      Collections.sort(entries, new TableEntryComparator());
    }
    public TableEntry find(String code) {
      for (TableEntry t : entries) {
        if (t.code.equals(code))
          return t;
      }
      return null;
    }
    public String getCsoid() {
      return csoid;
    }
    public void setCsoid(String csoid) {
      this.csoid = csoid;
    }
    public String getCsversion() {
      return csversion;
    }
    public void setCsversion(String csversion) {
      this.csversion = csversion;
    }
    public String getVsoid() {
      return vsoid;
    }
    public void setVsoid(String vsoid) {
      this.vsoid = vsoid;
    }
    
  }

  public class Table {
    private String id;
    private Map<String, String> langs = new HashMap<String, String>();
    private String name;
    private String oid;
    private String conceptDomainRef;
    private Map<String, TableVersion> versions = new HashMap<String, TableVersion>();
    private TableVersion master;
    
    public Table(String tableid) {
      id = tableid;
      if (id.length() !=4)
        throw new Error("TableId wrong length "+tableid);
    }
    
    public String getlang(String code) {
      return langs.get(code);
    }

    public void addLang(String code, String display) {
      if (!Utilities.noString(display))
        langs.put(code, display);
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getOid() {
      return oid;
    }

    public void setOid(String oid) {
      this.oid = oid;
    }

    
    public String getConceptDomainRef() {
      return conceptDomainRef;
    }

    public void setConceptDomainRef(String conceptDomainRef) {
      this.conceptDomainRef = conceptDomainRef;
    }

    public void item(String version, String code, String display, String german, String table_name, String comments, int sno) {
      if (!versions.containsKey(version))
        versions.put(version, new TableVersion(version, table_name));
      TableEntry entry = new TableEntry();
      entry.code = code;
      entry.display = display;
      if (!Utilities.noString(german))
        entry.langs.put("de", german);
      entry.comments = comments;
      entry.sortNo = sno;
      versions.get(version).entries.add(entry);
    }
    public boolean hasLangName(String code) {
      return langs.containsKey(code);
    }

    public boolean hasLangCode(String code) {
      for (TableVersion v : versions.values())
        if (v.hasLang(code))
          return true;
      return false;
    }
    
    public void processVersions() {
      master = new TableVersion(null, name);
      // first pass, languages
      for (String n : sorted(versions.keySet())) {
        if (n.contains(" ")) {
          String[] parts = n.split("\\ ");
          String lang = translateAffiliateCode(parts[1].toLowerCase()); 
          TableVersion tvl = versions.get(n);
          TableVersion tv = versions.get(parts[0]);
          if (name == null || !name.equals(tvl.name))
            langs.put(lang, tvl.name);
          if (tv != null) {
            for (TableEntry tel : tvl.entries) {
              TableEntry tem = tv.find(tel.code);
              if (tem == null)
                System.out.println("additional code for "+lang+" for table "+id+": "+tel.code);
              if (tem != null && !tel.display.equals(tem.display))
                tem.langs.put(lang, tel.display); 

            }
          } else
            System.out.println("no table for "+n+" for "+id);
        }
        
      }
      // second pass, versions
      for (String n : sorted(versions.keySet())) {
        if (!n.contains(" ")) {
          TableVersion tv = versions.get(n);         
          master.version = tv.version;
          master.vsoid = tv.vsoid;
          master.csoid = tv.csoid;
          master.csversion = tv.csversion;
          master.name = tv.name;
          master.steward = tv.steward;
          for (TableEntry te : tv.entries) {
            TableEntry tem = master.find(te.code);
            if (tem == null) {
              TableEntry ten = te.copy();
              ten.setFirst(n);
              master.entries.add(ten);
            } else {
              if (!Utilities.noString(te.display))
                tem.display = te.display;
              if (!Utilities.noString(te.comments))
                tem.comments = te.comments;
              for (String c : te.langs.keySet())
                tem.langs.put(c, te.langs.get(c));
              tem.sortNo = te.sortNo;
              tem.setLast(null);
            }
          }
          for (TableEntry tem : master.entries) {
            TableEntry te = tv.find(tem.code);
            if (te == null)
              tem.setLast(n);
          }
        } 
      }
      master.sort();
    }

    private String translateAffiliateCode(String code) {
      if (code.equals("de"))
        return "de";
      if (code.equals("uk"))
        return "en-UK";
      if (code.equals("fr"))
        return "fr";
      if (code.equals("ch"))
        return "rm"; 
      if (code.equals("at"))
        return "de-AT"; 
      
      throw new Error("No translation for "+code);
    }

    public Set<String> getOids() {
      Set<String> res = new HashSet<String>();
      for (TableVersion tv : versions.values())
        if (tv.csoid != null)
          res.add(tv.csoid);
      return res;
    }

    public TableVersion lastVersionForOid(String oid) {
      TableVersion res = null;
      for (String n : sorted(versions.keySet())) {
        if (oid.equals(versions.get(n).csoid))
          res = versions.get(n);
      }
      if (oid.equals(master.csoid))
        res = master;
      return res;
    }
  }

  public class ObjectInfo {
    private String oid;
    private String display;
    private String description;
    private int type;
    public ObjectInfo(String oid, String display, String description, int type) {
      super();
      this.oid = oid;
      this.display = display;
      this.description = description;
      this.type = type;
    }
    public String getOid() {
      return oid;
    }
    public String getDisplay() {
      return display;
    }
    public String getDescription() {
      return description;
    }
    public int getType() {
      return type;
    }  
  }

  public class VersionInfo {
    private String version;
    private Date publicationDate;
    public VersionInfo(String version, Date publicationDate) {
      super();
      this.version = version;
      this.publicationDate = publicationDate;
    }
    public String getVersion() {
      return version;
    }
    public Date getPublicationDate() {
      return publicationDate;
    }
  }

  public class ConceptDomain {
    private String name; 
    private XhtmlNode definition;
    private String text;
    private List<ConceptDomain> children = new ArrayList<ConceptDomain>();
    public String parent;
    public String conceptualClass;
  }

  private Connection source;
  private String dest;
  private Map<String, Table> tables = new HashMap<String, Table>();
  private Map<String, VersionInfo> versions = new HashMap<String, VersionInfo>();
  private Map<String, ObjectInfo> objects = new HashMap<String, ObjectInfo>();
  private Element mif;
  private List<ConceptDomain> v3ConceptDomains = new ArrayList<ConceptDomain>();
  private Date currentVersionDate;
  private Set<String> notations = new HashSet<String>();
  private String cdasource;
  private Map<String, CodeSystem> csmap;
  private IniFile cdaini;

  public VocabularyPOCGenerator(String dest, String v2source, String v3source, String cdasource) throws IOException, ClassNotFoundException, SQLException, FHIRException, SAXException, ParserConfigurationException {
    this.dest = dest;
    this.cdasource = cdasource;
    this.cdaini = new IniFile(Utilities.changeFileExt(cdasource, ".ini"));
    System.out.println("loading v2 Source");
    this.source = DriverManager.getConnection("jdbc:ucanaccess://"+v2source); // need ucanaccess from http://ucanaccess.sourceforge.net/site.html
    System.out.println("loading v3 Source");
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    this.mif = builder.parse(new FileInputStream(new File(v3source))).getDocumentElement();    
  }


  private void execute() throws Exception {
    loadTables();
    loadMif();
    for (String n : sorted(tables.keySet())) {
      Table t = tables.get(n);
      t.processVersions();
    }
    generateConceptDomains();
    generateV2CodeSystems();
    generateV3CodeSystems();
    generateV3ValueSets();
    loadCDAValueSets();
    System.out.println("finished");
  }


  private void loadCDAValueSets() throws IOException {
    Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cdasource), "UTF-8"));         
    String line = br.readLine(); // skip the headers
    while ((line = br.readLine()) != null) {
      String[] cols = line.split("\\|");
      while (cols.length < 11) {
        line = line + " "+br.readLine();
        cols = line.split("\\|");
      }
      String valueSetName = cols[0].trim();
      String valueSetOID = cols[1].trim();
      String definitionVersion = cols[2].trim();
      String expansionVersion = cols[3].trim();
      String purposeCF = cols[4].trim();
      String purposeDE = cols[5].trim();
      String purposeIC = cols[6].trim();
      String purposeEC = cols[7].trim();
      String code = cols[8].trim();
      String description = cols[9].trim();
      String codeSystemName = cols[10].trim();
      String codeSystemOID = cols[11].trim();
      String codeSystemVersion = cols[12].trim();
      String codeSystem = identifyOID(codeSystemOID);
      
      ValueSet vs = valueSets.get(valueSetOID);
      if (vs == null) {
        vs = new ValueSet();
        String name = Utilities.makeId(valueSetName);
        vs.setId("ccda-"+makeSafeId(name));
        vs.setUrl("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+vs.getId());
        vs.setName("CCDA"+name);
        vs.setTitle(valueSetName);
        vs.addIdentifier().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+valueSetOID);
        vs.setVersion(definitionVersion);
        vs.setDateElement(DateTimeType.parseV3(definitionVersion));
        vs.setDescription(purposeCF);
        vs.setPurpose(purposeDE);
        if (!Utilities.noString(purposeIC))
          vs.getCompose().addInclude().addExtension(vsext("rulesDescription"), new StringType(purposeIC));
        if (!Utilities.noString(purposeEC))
          vs.getCompose().addExclude().addExtension(vsext("rulesDescription"), new StringType(purposeEC));
        valueSets.put(valueSetOID, vs);
        String uuid = cdaini.getStringProperty("uuid", vs.getId());
        if (Utilities.noString(uuid)) {
          uuid = Utilities.makeUuidUrn();
          cdaini.setStringProperty("uuid", vs.getId(), uuid, null);
        }
        vs.getExpansion().setIdentifier(uuid);
        vs.getExpansion().setTimestampElement(new DateTimeType(expansionVersion.substring(11)));
        vs.getExpansion().addParameter().setName("CCDA Version").setValue(new StringType(expansionVersion.substring(0, 10)));
      }
      vs.getExpansion().addContains().setCode(code).setDisplay(description).setSystem(codeSystem).setVersion(codeSystemVersion);      
    }
    br.close(); 
    System.out.println("");
    for (ValueSet vs : valueSets.values()) {
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "cda", vs.getId()+".xml")), vs);      
    }
    cdaini.save();
    System.out.println(Integer.toString(valueSets.size())+" CDA value sets saved");
  }


  private String identifyOID(String oid) {
    if ("SNOMEDCT".equals(oid))
      return "http://snomed.info/sct";
    if ("2.16.840.1.113883.6.96".equals(oid))
      return "http://snomed.info/sct";
    if ("2.16.840.1.113883.6.1".equals(oid))
      return "http://loinc.org";
    if ("2.16.840.1.113883.12.292".equals(oid))
      return "http://hl7.org/fhir/sid/cvx";
    if ("2.16.840.1.113883.3.221.5".equals(oid))
      return "http://phdsc.org/standards/payer-typology.asp";
    if ("2.16.840.1.113883.6.88".equals(oid))
      return "http://www.nlm.nih.gov/research/umls/rxnorm";
     
    if (csmap.containsKey(oid))
      return csmap.get(oid).getUrl();

    return "urn:oid:"+oid;
  }


  private List<String> sorted(Set<String> keys) {
    List<String> res = new ArrayList<String>();
    res.addAll(keys);
    Collections.sort(res);
    return res;
  }


  private void loadTables() throws IOException, SQLException {
    System.out.println("reading v2 Database");
    Statement stmt = source.createStatement();
    String sql = "Select oid, symbolicName, object_description, Object_type from HL7Objects";
    ResultSet query = stmt.executeQuery(sql);
    while (query.next()) {
      ObjectInfo oi = new ObjectInfo(query.getString("oid"), query.getString("symbolicName"), query.getString("object_description"), query.getInt("Object_type"));
      objects.put(oi.getOid(), oi);
    }
    
    Map<String, VersionInfo> vers = new HashMap<String, VersionInfo>();
    query = stmt.executeQuery("SELECT version_id, hl7_version, date_release from HL7Versions");
    while (query.next()) {
      String vid = Integer.toString(query.getInt("version_id")); 
      String dn = query.getString("hl7_version");
      Date pd = query.getDate("date_release");
      if (pd != null && (currentVersionDate == null || currentVersionDate.before(pd)))
          currentVersionDate = pd;
      vers.put(vid, new VersionInfo(dn, pd));
    }

    Map<String, String> nameCache = new HashMap<String, String>();
    query = stmt.executeQuery("SELECT table_id, version_id, display_name, oid_table, cs_oid, cs_version, vs_oid, vs_expansion, vocab_domain, interpretation from HL7Tables order by version_id");
    while (query.next()) {
      String tid = Utilities.padLeft(Integer.toString(query.getInt("table_id")), '0', 4);
      String vid = vers.get(Integer.toString(query.getInt("version_id"))).getVersion();
      String dn = query.getString("display_name");
      nameCache.put(tid+"/"+vid, dn);
      if (!tables.containsKey(tid))
        tables.put(tid, new Table(tid));
      Table t = tables.get(tid);
      t.setName(query.getString("display_name"));
      if (!Utilities.noString(query.getString("interpretation")))
        t.addLang("de", query.getString("interpretation"));
      if (!Utilities.noString(query.getString("oid_table")))
        t.setOid(query.getString("oid_table"));
      if (!Utilities.noString(query.getString("vocab_domain")))
      t.setConceptDomainRef(query.getString("vocab_domain"));
      TableVersion tv = t.versions.get(vid);
      if (tv == null) {
        tv = new TableVersion(vid, query.getString("display_name"));
        t.versions.put(vid, tv);
      }
      tv.setCsoid(query.getString("cs_oid"));
      tv.setCsversion(query.getString("cs_version"));
      tv.setVsoid(query.getString("vs_oid"));
    }
    int i = 0;
    query = stmt.executeQuery("SELECT table_id, version_id, sort_no, table_value, display_name, interpretation, comment_as_pub  from HL7TableValues");
    while (query.next()) {
      String tid = Utilities.padLeft(Integer.toString(query.getInt("table_id")), '0', 4);
      VersionInfo vid = vers.get(Integer.toString(query.getInt("version_id")));
      if (!tables.containsKey(tid))
        tables.put(tid, new Table(tid));
      versions.put(vid.getVersion(), vid);
      Short sno = query.getShort("sort_no");
      String code = query.getString("table_value");
      String display = query.getString("display_name");
      String german = query.getString("interpretation");
      String comment = query.getString("comment_as_pub");
      tables.get(tid).item(vid.getVersion(), code, display, german, nameCache.get(tid+"/"+vid), comment, sno == null ? 0 : sno);
      i++;
    }
    System.out.println(Integer.toString(i)+" entries loaded");
    for (Table t : tables.values()) {
      for (TableVersion v : t.versions.values()) {
        v.sort();
      }
    }
  }

  private void loadMif() throws FHIRFormatError {
    List<Element> conceptDomains = new ArrayList<>();
    XMLUtil.getNamedChildren(mif, "conceptDomain", conceptDomains);
    for (Element e : conceptDomains) {
      ConceptDomain cd = new ConceptDomain();
      cd.name = e.getAttribute("name");
      Element xhtml = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "definition"), "text");
      cd.definition = new XhtmlParser().parseHtmlNode(xhtml);
      cd.text = XMLUtil.htmlToXmlEscapedPlainText(xhtml);
      Element spec = XMLUtil.getNamedChild(e, "specializesDomain");
      if (spec != null)
        cd.parent = spec.getAttribute("name"); 
      Element prop = XMLUtil.getNamedChild(e, "property");
      if (prop != null) {
        if (prop.getAttribute("name").equals("ConceptualSpaceForClassCode"))
          cd.conceptualClass = prop.getAttribute("value");
        else
          throw new Error("Unknown Property");
      }
      v3ConceptDomains.add(cd);
    }
    List<ConceptDomain> removed = new ArrayList<ConceptDomain>();
    for (ConceptDomain cd : v3ConceptDomains) {
      if (cd.parent != null) {
        ConceptDomain parent = getConceptDomain(cd.parent);
        if (parent == null)
          throw new Error("not found");
        parent.children.add(cd);
        removed.add(cd);
      }
    }
    v3ConceptDomains.removeAll(removed);
  }
  
  private ConceptDomain getConceptDomain(String code) {
    for (ConceptDomain cd : v3ConceptDomains) {
      if (cd.name.equals(code))
        return cd;
    }
    return null;
  }


  private void generateConceptDomains() throws FileNotFoundException, IOException {
    CodeSystem cs = new CodeSystem();
    cs.setId("conceptdomains");
    cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+cs.getId());
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
    int count = cs.getConcept().size() + addv3ConceptDomains(v3ConceptDomains, cs.getConcept(), codes);
    
    for (String n : sorted(tables.keySet())) {
      if (!n.equals("0000")) {
        Table t = tables.get(n);
        ObjectInfo oi = objects.get(t.getConceptDomainRef());
        if (oi != null) {
          ConceptDefinitionComponent c = cs.addConcept();
          c.setCode(oi.getDisplay());
          count++;
          String name = t.getName();
          c.setDisplay(name+" ("+t.id+")");
          c.setDefinition(oi.description);
          if (codes.containsKey(c.getCode())) {
            System.out.println("Name clash for Domain \""+c.getCode()+": used on "+codes.get(c.getCode())+" and on table "+t.id);
            if (codes.get(c.getCode()).equals("v3"))
              c.setCode(c.getCode()+"V2");
            else
              c.setCode(c.getCode()+"SNAFU");
            codes.put(c.getCode(), "table "+t.id);
          } else
            codes.put(c.getCode(), "table "+t.id);
          c.addProperty().setCode("source").setValue(new CodeType("v2"));
        }
      }
    }        
     
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "conceptdomains.xml")), cs);
    System.out.println("Save conceptdomains ("+Integer.toString(count)+" found)");
  }

  private int addv3ConceptDomains(List<ConceptDomain> domains, List<ConceptDefinitionComponent> concepts, Map<String, String> codes) {
    int res = 0;
    for (ConceptDomain cd : domains) {
      ConceptDefinitionComponent c = new ConceptDefinitionComponent();
      concepts.add(c);
      res++;
      c.setCode(cd.name);
      c.setDisplay(cd.name);
      c.setDefinition(cd.text);
      if (codes.containsKey(c.getCode()))
        System.out.println("Name clash for Domain \""+c.getCode()+": used on "+codes.get(c.getCode())+" and in v3");
      else
        codes.put(c.getCode(), "v3");
      c.addProperty().setCode("source").setValue(new CodeType("v3"));
      if (cd.conceptualClass != null)
        c.addProperty().setCode("ConceptualSpaceForClassCode").setValue(new CodeType(cd.conceptualClass));
      res = res + addv3ConceptDomains(cd.children, c.getConcept(), codes);
    } 
    return res;
  }

  private void generateV2CodeSystems() throws FileNotFoundException, IOException {
    int c = 0;
    int h = 0;
    for (String n : sorted(tables.keySet())) {
      if (!n.equals("0000")) {
        Table t = tables.get(n);
        Set<String> oids = t.getOids();
        for (String oid : oids) {
          TableVersion tv = t.lastVersionForOid(oid);
          generateCodeSystem(t, tv);
          c++;
        }
        for (String v : sorted(t.versions.keySet())) {
          if (!v.contains(" ")) {
            TableVersion tv = t.versions.get(v);
            if (!(new File(Utilities.path(dest, "v2", "v"+v)).exists()))
              Utilities.createDirectory(Utilities.path(dest, "v2", "v"+v));          
            generateVersionCodeSystem(t, tv);
            h++;
            //          new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "v"+n, "vs-"+cs.getId())+".xml"), produceValueSet(n, cs.getId()+"-"+n, cs, t, tv));
          }
        }
      }
    }
    System.out.println("Saved v2 code systems ("+Integer.toString(c)+" found, with "+Integer.toString(h)+" past versions)");
  }

  private void generateCodeSystem(Table t, TableVersion tv) throws FileNotFoundException, IOException {
    CodeSystem cs = new CodeSystem();
    if (tv == t.master) {
      cs.setId("v2-"+t.id);
    } else {
      cs.setId("v2-"+t.id+"-"+tv.version);
    }
    cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/"+cs.getId());
    cs.setValueSet("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+cs.getId());
      
    cs.setVersion(tv.csversion);
    cs.setName("V2Table"+t.id);
    cs.setTitle("V2 Table: "+t.name);
    cs.setStatus(PublicationStatus.ACTIVE);
    cs.setExperimental(false);
    cs.getIdentifier().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+tv.csoid);
    cs.setDateElement(new DateTimeType(currentVersionDate, TemporalPrecisionEnum.DAY));
    cs.setPublisher("HL7, Inc");
    cs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("https://github.com/grahamegrieve/vocab-poc");
    cs.setDescription("Underlying Code System for V2 table "+t.id+" ("+t.name+")");
    cs.setCopyright("Copyright HL7. Licensed under creative commons public domain");
    cs.setCaseSensitive(false); // not that it matters, since they are all numeric
    cs.setHierarchyMeaning(CodeSystemHierarchyMeaning.ISA); // todo - is this correct
    cs.setCompositional(false);
    cs.setVersionNeeded(false);
    cs.setContent(CodeSystemContentMode.COMPLETE);

    for (TableEntry te : tv.entries) {
      ConceptDefinitionComponent c = cs.addConcept();
      c.setCode(te.code);
      String name = te.display;
      c.setDisplay(name);
      c.setDefinition(name);
      if (!Utilities.noString(te.comments))
        ToolingExtensions.addCSComment(c, te.comments);
      if (te.getFirst() != null && !"2.1".equals(te.getFirst()))
        c.addExtension(csext("versionIntroduced"), Factory.newString_(te.getFirst()));
      if (!Utilities.noString(te.getLast()))
        c.addExtension(csext("versionDeprecated"), Factory.newString_(te.getLast()));   
    }    

    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "cs-"+cs.getId())+".xml"), cs);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "vs-"+cs.getId())+".xml"), produceValueSet("Master", cs, t, tv));
  }

  private void generateVersionCodeSystem(Table t, TableVersion tv) throws FileNotFoundException, IOException {
    CodeSystem cs = new CodeSystem();
    cs.setId("v2-"+t.id+"-"+tv.version);
    cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/"+cs.getId());
    cs.setValueSet("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+cs.getId());
      
    cs.setVersion(tv.csversion);
    cs.setName("V2Table"+t.id+"v"+tv.version);
    cs.setTitle("V2 Table: "+t.name);
    cs.setStatus(PublicationStatus.ACTIVE);
    cs.setExperimental(false);
    cs.getIdentifier().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+tv.csoid);
    cs.setDateElement(new DateTimeType(currentVersionDate, TemporalPrecisionEnum.DAY));
    cs.setPublisher("HL7, Inc");
    cs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("https://github.com/grahamegrieve/vocab-poc");
    cs.setDescription("Underlying Code System for V2 table "+t.id+" ("+t.name+")");
    cs.setCopyright("Copyright HL7. Licensed under creative commons public domain");
    cs.setCaseSensitive(false); // not that it matters, since they are all numeric
    cs.setHierarchyMeaning(CodeSystemHierarchyMeaning.ISA); // todo - is this correct
    cs.setCompositional(false);
    cs.setVersionNeeded(false);
    cs.setContent(CodeSystemContentMode.COMPLETE);

    for (TableEntry te : tv.entries) {
      ConceptDefinitionComponent c = cs.addConcept();
      c.setCode(te.code);
      String name = te.display;
      c.setDisplay(name);
      c.setDefinition(name);
      if (!Utilities.noString(te.comments))
        ToolingExtensions.addCSComment(c, te.comments);
      if (te.getFirst() != null && !"2.1".equals(te.getFirst()))
        c.addExtension("http://hl7.org/fhir/StructureDefinition/v2-version-defined", Factory.newString_(te.getFirst()));
      if (!Utilities.noString(te.getLast()))
        c.addExtension("http://hl7.org/fhir/StructureDefinition/v2-version-deprecated", Factory.newString_(te.getLast()));   
    }    

    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "v"+tv.version, "cs-"+cs.getId())+".xml"), cs);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "v"+tv.version, "vs-"+cs.getId())+".xml"), produceValueSet("Master", cs, t, tv));
  }
  
  private ValueSet produceValueSet(String vid, CodeSystem cs, Table t, TableVersion tv) {
    ValueSet vs = new ValueSet();
    vs.setId(cs.getId());
    vs.setUrl("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+vs.getId());
    vs.setVersion(cs.getVersion());
    vs.setName("V2Table"+t.id+"Version"+vid);
    vs.setTitle("V2 Table "+t.id+" Version "+vid);
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.setExperimental(false);
    if (tv.vsoid != null)
      vs.addIdentifier().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+tv.vsoid);
    vs.setDateElement(new DateTimeType(currentVersionDate, TemporalPrecisionEnum.DAY));
    vs.setPublisher("HL7, Inc");
    vs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("https://github.com/grahamegrieve/vocab-poc");
    vs.setDescription("V2 Table "+t.id+" Version "+vid+" ("+t.name+")");
    vs.setCopyright("Copyright HL7. Licensed under creative commons public domain");

    vs.getCompose().addInclude().setSystem(cs.getUrl()).setVersion(cs.getVersion());
    return vs;
  }

  private void generateV3CodeSystems() throws Exception {
    List<Element> list = new ArrayList<Element>();
    XMLUtil.getNamedChildren(mif, "codeSystem", list);
    csmap = new HashMap<String, CodeSystem>();
    for (Element l : list) {
      CodeSystem cs = generateV3CodeSystem(l);
      csmap.put(cs.getUserString("oid"), cs);
    }
    postProcess(csmap);
    for (CodeSystem cs : csmap.values())
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v3", "codeSystems", cs.getId())+".xml"), cs);
    System.out.println("Save v3 code systems ("+Integer.toString(csmap.size())+" found)");
  }


  private CodeSystem generateV3CodeSystem(Element item) throws Exception {
    CodeSystem cs = new CodeSystem();
    cs.setId("v3-"+makeSafeId(item.getAttribute("name")));
    cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/"+cs.getId());
    cs.setName(item.getAttribute("name"));
    cs.setTitle(item.getAttribute("title"));
    cs.getIdentifier().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+item.getAttribute("codeSystemId"));
    cs.setUserData("oid", item.getAttribute("codeSystemId"));
    cs.setStatus(PublicationStatus.ACTIVE);
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("header"))
        processHeader(child, cs);
      else if (child.getNodeName().equals("annotations"))
        processCSAnnotations(child, cs);
      else if (child.getNodeName().equals("releasedVersion"))
        processReleasedVersion(child, cs);
      else if (child.getNodeName().equals("historyItem"))
        processHistoryItem(child, cs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
    return cs;
  }

  private void postProcess(Map<String, CodeSystem> csmap) throws FHIRException {
    // first, resolve all the code system references
    for (CodeSystem cs : csmap.values()) {
      for (ConceptDefinitionComponent cd : cs.getConcept()) {
        for (ConceptPropertyComponent p : cd.getProperty()) {
          if (p.hasValueCoding() && !p.getValueCoding().hasSystem()) {
            CodeSystem ref = csmap.get(p.getValue().getUserString("oid"));
            if (ref == null)
              throw new FHIRException("reference to unknown code system "+p.getValue().getUserString("oid")+" in property of code "+cd.getCode()+" on "+cs.getId());
            p.getValueCoding().setSystem(ref.getUrl());
          }
        }
      }
    }
    // now, fix the heirarchies
    for (CodeSystem cs : csmap.values()) {
      List<ConceptDefinitionComponent> moved = new ArrayList<ConceptDefinitionComponent>();  
      for (ConceptDefinitionComponent cd : cs.getConcept()) {
        @SuppressWarnings("unchecked")
        List<String> parents = (List<String>) cd.getUserData("parents");
        if (parents != null && parents.size() > 0) {
          moved.add(cd);
          find(cs, parents.get(0), cd.getCode(), cs.getUrl()).getConcept().add(cd);
          for (int i = 1; i < parents.size(); i++) {
            find(cs, parents.get(i), cd.getCode(), cs.getUrl()).addExtension("http://hl7.org/fhir/StructureDefinition/codesystem-subsumes", new CodeType(cd.getCode()));
          }
        }
      }
      cs.getConcept().removeAll(moved);    
    }
  }


  private ConceptDefinitionComponent find(CodeSystem cs, String code, String src, String srcU) {
    for (ConceptDefinitionComponent cd : cs.getConcept()) {
      if (cd.getCode().equals(code))
        return cd;
      for (ConceptDefinitionDesignationComponent d : cd.getDesignation()) {
        if (d.getUse().getCode().equals("synonym") && d.getValue().equals(code))
          return cd;
      }
    }
    throw new Error("Unable to resolve reference to "+cs.getUrl()+"#"+code+" from "+srcU+"#"+src);
  }


  private void processHeader(Element item, CodeSystem cs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("legalese"))
        processLegalese(child, cs);
      else if (child.getNodeName().equals("responsibleGroup"))
        cs.setPublisher(child.getAttribute("organizationName"));
      else if (child.getNodeName().equals("contributor"))
        processContributor(child, cs);        
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processLegalese(Element item, CodeSystem cs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("notation"))
        notations.add(child.getTextContent());
      else if (child.getNodeName().equals("licenseTerms"))
        cs.setCopyright(child.getTextContent());
      else if (child.getNodeName().equals("versioningPolicy"))
        cs.addExtension(csext("versioning"), new StringType(child.getTextContent()));
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }        
  }

  private String csext(String name) {
    return "http://hl7.org/fhir/StructureDefinition/codeSystem-"+name;
  }

  private String vsext(String name) {
    return "http://hl7.org/fhir/StructureDefinition/valueSet-"+name;
  }


  private void processContributor(Element item, CodeSystem cs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    String role = null;
    String name = null;
    String notes = null;
    while (child != null) {
      if (child.getNodeName().equals("role"))
        role = child.getTextContent();
      else if (child.getNodeName().equals("name"))
        name = child.getAttribute("name");
      else if (child.getNodeName().equals("notes"))
        notes = child.getTextContent();
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
    if (!Utilities.existsInList(role, "Publisher", "Sponsor")) 
      throw new Exception("Unprocessed role "+role);
    if (name.equals("(see notes)"))
      name = notes;
    if (!cs.hasPublisher()) 
      cs.setPublisher(name);
    else if (!name.equals(cs.getPublisher()))
      cs.addContact().setName(name);
  }


  private void processReleasedVersion(Element item, CodeSystem cs) throws Exception {
    // ignore: hl7MaintainedIndicator, hl7ApprovedIndicator
    cs.setDateElement(new DateTimeType(item.getAttribute("releaseDate")));
    if ("false".equals(item.getAttribute("completeCodesIndicator"))) 
      cs.setContent(CodeSystemContentMode.FRAGMENT); // actually a fragment this time 
    else
      cs.setContent(CodeSystemContentMode.COMPLETE);
    
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("supportedLanguage"))
        processSupportedLanguage(child, cs);
      else if (child.getNodeName().equals("supportedConceptRelationship"))
        processSupportedConceptRelationship(child, cs);
      else if (child.getNodeName().equals("supportedConceptProperty"))
        processSupportedConceptProperty(child, cs);
      else if (child.getNodeName().equals("concept"))
        processConcept(child, cs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processHistoryItem(Element item, CodeSystem cs) throws Exception {
    Extension ext = new Extension().setUrl("http://hl7.org/fhir/StructureDefinition/codesystem-history");    
    cs.getExtension().add(ext);
    ext.addExtension("date", new DateTimeType(item.getAttribute("dateTime")));
    ext.addExtension("id", new StringType(item.getAttribute("id")));
    if (item.hasAttribute("responsiblePersonName"))
      ext.addExtension("author", new StringType(item.getAttribute("responsiblePersonName")));
    if (item.hasAttribute("isSubstantiveChange"))
      ext.addExtension("substantiative", new BooleanType(item.getAttribute("isSubstantiveChange")));
    if (item.hasAttribute("isBackwardCompatibleChange"))
      ext.addExtension("backwardCompatible", new BooleanType(item.getAttribute("isBackwardCompatibleChange")));

    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("description"))
        ext.addExtension("notes", new StringType(child.getTextContent()));
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }
  
  private void processSupportedLanguage(Element item, MetadataResource mr) throws Exception {
    mr.setLanguage(item.getTextContent());
    Element child = XMLUtil.getFirstChild(item);
    if (child != null) {
        throw new Exception("Unprocessed element "+child.getNodeName());
    }    
  }

  private void processCSAnnotations(Element item, CodeSystem cs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("documentation"))
        processCSDocumentation(child, cs);
      else if (child.getNodeName().equals("appInfo"))
        processCSAppInfo(child, cs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processCSDocumentation(Element item, CodeSystem cs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("description"))
        processCSDescription(child, cs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processCSDescription(Element item, CodeSystem cs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) {
        cs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(child));
        XhtmlNode html = new XhtmlParser().parseHtmlNode(child);
        html.setName("div");
        cs.getText().setDiv(html);
        cs.getText().setStatus(NarrativeStatus.GENERATED);
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processCSAppInfo(Element item, CodeSystem cs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("deprecationInfo"))
        processCSDeprecationInfo(child, cs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processCSDeprecationInfo(Element item, CodeSystem cs) throws Exception {
    String v = item.getAttribute("deprecationEffectiveVersion");
    if (Utilities.noString(v))
      throw new Exception("Element not understood: "+item.getNodeName());
    cs.addExtension(csext("versionDeprecated"), Factory.newString_(v));
    cs.setStatus(PublicationStatus.RETIRED);
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) { 
        if (!"This Code System was deprecated (retired) as of the Vocabulary Model version shown in \"deprecationEffectiveVersion\".".equals(child.getTextContent()))
            throw new Exception("Unprocessed element text: "+child.getTextContent());
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }


  private void processSupportedConceptRelationship(Element item, CodeSystem cs) throws Exception {
    if ("Specializes".equals(item.getAttribute("name")))
      return;
    if ("Generalizes".equals(item.getAttribute("name")))
      return;
    if (Utilities.existsInList(item.getAttribute("relationshipKind"), "NonDefinitionallyQualifiedBy", "Specializes", "ComponentOf", "Other", "LessThan")) {
      PropertyComponent pd = cs.addProperty();
      pd.setCode(item.getAttribute("name"));
      pd.setType(PropertyType.CODING);
      pd.addExtension(csext("relationshipKind"), new CodeType(item.getAttribute("relationshipKind")));
      Element child = XMLUtil.getFirstChild(item);
      while (child != null) {
        if (child.getNodeName().equals("description"))
          pd.setDescription(child.getTextContent());
        else
          throw new Exception("Unprocessed element "+child.getNodeName());
        child = XMLUtil.getNextSibling(child);
      }          
    } else
      throw new Exception("Unprocessed relationship "+item.getAttribute("name")+" : "+item.getAttribute("relationshipKind"));
  }
  
  private void processSupportedConceptProperty(Element item, CodeSystem cs) throws Exception {
    if (item.getAttribute("propertyName").equals("internalId"))
      return; // ignored and handled implicitly
    PropertyComponent pd = cs.addProperty();
    pd.setCode(item.getAttribute("propertyName"));
    String type = item.getAttribute("type");
    if ("Token".equals(type)) 
      pd.setType(PropertyType.CODE);
    else if ("String".equals(type)) 
      pd.setType(PropertyType.STRING);
    else if ("Boolean".equals(type)) 
      pd.setType(PropertyType.BOOLEAN);
    else
      throw new Exception("unknown type "+type);
    pd.addExtension(csext("mandatory"), new BooleanType(item.getAttribute("isMandatoryIndicator")));
    
    String defV = item.getAttribute("defaultValue");
    if (!Utilities.noString(defV)) {
      if (!("active".equals(defV) && pd.getCode().equals("status")) && !(Utilities.existsInList(defV, "&", "as&") && pd.getCode().startsWith("Name:")))
        throw new Exception("Unsupported default value "+defV);
    }
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("description"))
        pd.setDescription(child.getTextContent());
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }
  
  private void processConcept(Element item, CodeSystem cs) throws Exception {
    ConceptDefinitionComponent cd = cs.addConcept();
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("annotations"))
        processConceptAnnotations(child, cd);
      else if (child.getNodeName().equals("conceptProperty"))
        processConceptProperty(child, cd, cs);
      else if (child.getNodeName().equals("printName"))
        processPrintName(child, cd, cs);
      else if (child.getNodeName().equals("code"))
        processCode(child, cd, cs);
      else if (child.getNodeName().equals("conceptRelationship"))
        processConceptRelationship(child, cd, cs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }
  
  private void processConceptAnnotations(Element item, ConceptDefinitionComponent cd) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("documentation"))
        processConceptDocumentation(child, cd);
      else if (child.getNodeName().equals("appInfo"))
        processConceptAppInfo(child, cd);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processConceptDocumentation(Element item, ConceptDefinitionComponent cd) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("description"))
        processConceptDescription(child, cd);
      else if (child.getNodeName().equals("definition"))
        processConceptDefinition(child, cd);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processConceptDescription(Element item, ConceptDefinitionComponent cd) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) 
        cd.addExtension(csext("description"), new StringType(XMLUtil.htmlToXmlEscapedPlainText(child)));
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processConceptDefinition(Element item, ConceptDefinitionComponent cd) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) 
        cd.setDefinition(XMLUtil.htmlToXmlEscapedPlainText(child));
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processConceptAppInfo(Element item, ConceptDefinitionComponent cd) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("openIssue"))
        processConceptOpenIssue(child, cd);
      else if (child.getNodeName().equals("deprecationInfo"))
        processConceptDeprecationInfo(child, cd);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processConceptOpenIssue(Element item, ConceptDefinitionComponent cd) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) 
        cd.addExtension(csext("openIssue"), new StringType(XMLUtil.htmlToXmlEscapedPlainText(child)));
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processConceptDeprecationInfo(Element item, ConceptDefinitionComponent cd) throws Exception {
    String v = item.getAttribute("deprecationEffectiveVersion");
    if (Utilities.noString(v))
      throw new Exception("Element not understood: "+item.getNodeName());
    cd.addExtension(csext("versionDeprecated"), Factory.newString_(v));   
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) { 
        if (!"This element was deprecated as of the release indicated.".equals(child.getTextContent()))
            throw new Exception("Unprocessed element "+child.getNodeName());
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processConceptProperty(Element item, ConceptDefinitionComponent cd, CodeSystem cs) throws Exception {
    String id = item.getAttribute("name");
    if (id.equals("internalId")) {
      cd.setId(item.getAttribute("value")); 
    } else {
      PropertyComponent pd = cs.getProperty(id);
      if (pd == null)
        throw new Exception("Unknown Property "+id+" on "+item.getTagName()+" for code system "+cs.getId());
      ConceptPropertyComponent p = cd.addProperty();
      p.setCode(id);
      if (pd.getType() == PropertyType.CODE)
        p.setValue(new CodeType(item.getAttribute("value")));
      else if (pd.getType() == PropertyType.STRING)
        p.setValue(new StringType(item.getAttribute("value")));
      else if (pd.getType() == PropertyType.BOOLEAN)
        p.setValue(new BooleanType(item.getAttribute("value")));
      else
        throw new Exception("Unsupported Property type "+pd.getType().toCode());
      Element child = XMLUtil.getFirstChild(item);
      if (child != null) {
        throw new Exception("Unprocessed element "+child.getNodeName());
      }    
    }
  }

  private void processPrintName(Element item, ConceptDefinitionComponent cd, CodeSystem cs) throws Exception {
    if (!"false".equals(item.getAttribute("preferredForLanguage")))
      cd.addDesignation().setUse(new Coding().setSystem("http://something...?").setCode("deprecated alias")).setValue(item.getAttribute("text"));
    else if (Utilities.noString(item.getAttribute("language")) || item.getAttribute("language").equals(cs.getLanguage()))
      cd.setDisplay(item.getAttribute("text"));
    else 
      cd.addDesignation().setLanguage(item.getAttribute("language")).setValue(item.getAttribute("text"));
    Element child = XMLUtil.getFirstChild(item);
    if (child != null) {
      throw new Exception("Unprocessed element "+child.getNodeName());
    }    
  }
  
  private void processCode(Element item, ConceptDefinitionComponent cd, CodeSystem cs) throws Exception {
    if (!"active".equals(item.getAttribute("status"))&& !"retired".equals(item.getAttribute("status")))
      throw new Exception("Unexpected value for attribute status "+item.getAttribute("status"));
    if (cd.hasCode())
      cd.addDesignation().setUse(new Coding().setSystem("http://something...?").setCode("synonym")).setValue(item.getAttribute("code"));
    else
      cd.setCode(item.getAttribute("code"));
    Element child = XMLUtil.getFirstChild(item);
    if (child != null) {
      throw new Exception("Unprocessed element "+child.getNodeName());
    }    
  }
  
  private void processConceptRelationship(Element item, ConceptDefinitionComponent cd, CodeSystem cs) throws Exception {
    if ("Specializes".equals(item.getAttribute("relationshipName"))) {
      Element child = XMLUtil.getFirstChild(item);
      while (child != null) {
        if (child.getNodeName().equals("targetConcept")) {
          List<String> parents = (List<String>) cd.getUserData("parents");
          if (parents == null) {
            parents = new ArrayList<String>(); 
            cd.setUserData("parents", parents);
          }
          parents.add(child.getAttribute("code"));
        } else
          throw new Exception("Unprocessed element "+child.getNodeName());
        child = XMLUtil.getNextSibling(child);
      }    
    } else if (cs.getProperty(item.getAttribute("relationshipName")) != null) {
      PropertyComponent pd = cs.getProperty(item.getAttribute("relationshipName"));
      Element child = XMLUtil.getFirstChild(item);
      while (child != null) {
        if (child.getNodeName().equals("targetConcept")) {
          ConceptPropertyComponent t = cd.addProperty();
          t.setCode(pd.getCode());
          Coding c = new Coding();
          c.setCode(child.getAttribute("code"));
          if (Utilities.noString(child.getAttribute("codeSystem")))
            c.setSystem(cs.getUrl());
          else
            c.setUserData("oid", child.getAttribute("codeSystem"));
          t.setValue(c);
        } else
          throw new Exception("Unprocessed element "+child.getNodeName());
        child = XMLUtil.getNextSibling(child);
      }    
      

    } else
      throw new Exception("Unexpected value for attribute relationshipName "+item.getAttribute("relationshipName"));

  }
  
  private void generateV3ValueSets() throws Exception {
    List<Element> list = new ArrayList<Element>();
    XMLUtil.getNamedChildren(mif, "valueSet", list);
    HashMap<String, ValueSet> vsmap = new HashMap<String, ValueSet>();
    for (Element l : list){
      ValueSet vs = generateV3ValueSet(l);
      vsmap.put(vs.getUserString("oid"), vs);
    }
    postProcess(vsmap);   
    for (ValueSet vs : vsmap.values())
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v3", "valueSets", vs.getId())+".xml"), vs);
    System.out.println("Save v3 value sets ("+Integer.toString(vsmap.size())+" found)");
  }

  private void postProcess(HashMap<String, ValueSet> vsmap) throws Exception {
     // resolve vs references
    for (ValueSet vs : vsmap.values()) {
      for (ConceptSetComponent cs : vs.getCompose().getInclude()) 
        resolveVsReferences(vs, cs, vsmap);
      for (ConceptSetComponent cs : vs.getCompose().getExclude()) 
        resolveVsReferences(vs, cs, vsmap);
    }
  }


  private void resolveVsReferences(ValueSet vs, ConceptSetComponent cs, HashMap<String, ValueSet> vsmap) throws Exception {
    for (UriType vsref : cs.getValueSet()) {
      ValueSet vtgt = vsmap.get(vsref.getUserString("vsref"));
      if (vtgt == null)
        throw new Exception("Unable to resolve reference to value set "+vsref.getUserString("vsref")+" on value set "+vs.getId());
      vsref.setValue(vtgt.getUrl());
    }
  }


  private ValueSet generateV3ValueSet(Element item) throws Exception {
    ValueSet vs = new ValueSet();
    vs.setId("v3-"+makeSafeId(item.getAttribute("name")));
    vs.setUrl("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+vs.getId());
    vs.setName(item.getAttribute("name"));
    vs.setTitle(item.getAttribute("title"));
    vs.addIdentifier().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+item.getAttribute("id"));
    vs.setUserData("oid", item.getAttribute("id"));
    vs.setStatus(PublicationStatus.ACTIVE);
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("header"))
        processHeader(child, vs);
      else if (child.getNodeName().equals("annotations"))
        processVSAnnotations(child, vs);
      else if (child.getNodeName().equals("version"))
        processVersion(child, vs);
      else if (child.getNodeName().equals("historyItem"))
        processHistoryItem(child, vs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
    return vs;
  }

  private String makeSafeId(String s) {
    if (s.contains("("))
      s = s.substring(0, s.indexOf("("));
    return Utilities.makeId(s);
  }


  private void processHeader(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("legalese"))
        processLegalese(child, vs);
      else if (child.getNodeName().equals("responsibleGroup"))
        vs.setPublisher(child.getAttribute("organizationName"));
      else if (child.getNodeName().equals("contributor"))
        processContributor(child, vs);        
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processLegalese(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("notation"))
        notations.add(child.getTextContent());
      else if (child.getNodeName().equals("licenseTerms"))
        vs.setCopyright(child.getTextContent());
      else if (child.getNodeName().equals("versioningPolicy"))
        vs.addExtension(csext("versioning"), new StringType(child.getTextContent()));
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }        
  }

  private void processContributor(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    String role = null;
    String name = null;
    String notes = null;
    while (child != null) {
      if (child.getNodeName().equals("role"))
        role = child.getTextContent();
      else if (child.getNodeName().equals("name"))
        name = child.getAttribute("name");
      else if (child.getNodeName().equals("notes"))
        notes = child.getTextContent();
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
    if (!Utilities.existsInList(role, "Publisher", "Sponsor")) 
      throw new Exception("Unprocessed role "+role);
    if (name.equals("(see notes)"))
      name = notes;
    if (!vs.hasPublisher()) 
      vs.setPublisher(name);
    else if (!name.equals(vs.getPublisher()))
      vs.addContact().setName(name);
  }

  private void processVSAnnotations(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("documentation"))
        processVSDocumentation(child,vs);
      else if (child.getNodeName().equals("appInfo"))
        processVSAppInfo(child, vs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processVSDocumentation(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("description"))
        processVSDescription(child, vs);
      else if (child.getNodeName().equals("otherAnnotation"))
        processVSOtherAnnotation(child, vs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processVSDescription(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) {
        vs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(child));
        XhtmlNode html = new XhtmlParser().parseHtmlNode(child);
        html.setName("div");
        vs.getText().setDiv(html);
        vs.getText().setStatus(NarrativeStatus.GENERATED);
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processVSOtherAnnotation(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) {
        vs.setPurpose(XMLUtil.htmlToXmlEscapedPlainText(child));
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processVSAppInfo(Element item, ValueSet vs) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("deprecationInfo"))
        processVSDeprecationInfo(child, vs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processVSDeprecationInfo(Element item, ValueSet vs) throws Exception {
    String v = item.getAttribute("deprecationEffectiveVersion");
    if (Utilities.noString(v))
      throw new Exception("Element not understood: "+item.getNodeName());
    vs.addExtension(csext("versionDeprecated"), Factory.newString_(v));
    vs.setStatus(PublicationStatus.RETIRED);
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("text")) { 
        if (!"This element was deprecated as of the release indicated.".equals(child.getTextContent()))
            throw new Exception("Unprocessed element text: "+child.getTextContent());
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processHistoryItem(Element item, ValueSet vs) throws Exception {
    Extension ext = new Extension().setUrl("http://hl7.org/fhir/StructureDefinition/codesystem-history");    
    vs.getExtension().add(ext);
    ext.addExtension("date", new DateTimeType(item.getAttribute("dateTime")));
    ext.addExtension("id", new StringType(item.getAttribute("id")));
    if (item.hasAttribute("responsiblePersonName"))
      ext.addExtension("author", new StringType(item.getAttribute("responsiblePersonName")));
    if (item.hasAttribute("isSubstantiveChange"))
      ext.addExtension("substantiative", new BooleanType(item.getAttribute("isSubstantiveChange")));
    if (item.hasAttribute("isBackwardCompatibleChange"))
      ext.addExtension("backwardCompatible", new BooleanType(item.getAttribute("isBackwardCompatibleChange")));

    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("description"))
        ext.addExtension("notes", new StringType(child.getTextContent()));
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }
  
  private void processVersion(Element item, ValueSet vs) throws Exception {
    // ignore: hl7MaintainedIndicator, hl7ApprovedIndicator
    vs.setDateElement(new DateTimeType(item.getAttribute("versionDate")));
    
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("supportedLanguage"))
        processSupportedLanguage(child, vs);
      else if (child.getNodeName().equals("supportedCodeSystem"))
        ; // ignore this
      else if (child.getNodeName().equals("associatedConceptProperty"))
        ; // ignore this - for now? todo!
      else if (child.getNodeName().equals("content"))
        processContent(child, vs);
      else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processContent(Element item, ValueSet vs) throws Exception {
    String url = identifyOID(item.getAttribute("codeSystem"));
    processGeneralContent(item, vs, url, true, 0);
  }
  
  private void processGeneralContent(Element item, ValueSet vs, String url, boolean include, int level) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("codeBasedContent")) {
        ConceptSetComponent cset = include ? vs.getCompose().addInclude() : vs.getCompose().addExclude() ;
        cset.setSystem(url);
        processCodeBasedContent(child, vs, cset);
      } else if (child.getNodeName().equals("combinedContent")) {
        if (level > 0)
          throw new Exception("recursion not supported on "+vs.getUrl());
        processCombinedContent(child, vs, url);
      } else if (child.getNodeName().equals("valueSetRef")) {
        ConceptSetComponent vset = include ? vs.getCompose().addInclude() : vs.getCompose().addExclude() ;
        UriType vsref = new UriType();
        vset.getValueSet().add(vsref);
        vsref.setUserData("vsref", child.getAttribute("id"));
        vsref.setUserData("vsname", child.getAttribute("name"));
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }    
  }

  private void processCodeBasedContent(Element item, ValueSet vs, ConceptSetComponent cset) throws Exception {
    String code = item.getAttribute("code");
    boolean filtered = false;
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("includeRelatedCodes")) {
        // common case: include a child and all or some of it's descendants
        ConceptSetFilterComponent f = new ValueSet.ConceptSetFilterComponent();
        f.setOp("false".equals(child.getAttribute("includeHeadCode")) ? FilterOperator.DESCENDENTOF : FilterOperator.ISA);
        f.setProperty("concept");
        f.setValue(code);
        cset.getFilter().add(f);
        filtered = true;
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }        
    if (!filtered)
      cset.addConcept().setCode(code);
  }
  
  private void processCombinedContent(Element item, ValueSet vs, String url) throws Exception {
    Element child = XMLUtil.getFirstChild(item);
    while (child != null) {
      if (child.getNodeName().equals("unionWithContent")) {
        processGeneralContent(child, vs, url, true, 0);  
      } else if (child.getNodeName().equals("excludeContent")) {
        processGeneralContent(child, vs, url, false, 1);  
      } else
        throw new Exception("Unprocessed element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }        
  }
}
