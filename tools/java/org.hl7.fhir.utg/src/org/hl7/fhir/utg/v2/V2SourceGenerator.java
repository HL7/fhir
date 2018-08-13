package org.hl7.fhir.utg.v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.formats.IParser.OutputStyle;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Factory;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.TemporalPrecisionEnum;
import org.hl7.fhir.r4.model.UriType;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r4.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.r4.model.CodeSystem.CodeSystemHierarchyMeaning;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r4.model.CodeSystem.PropertyType;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.utg.BaseGenerator;
import org.hl7.fhir.utilities.Utilities;

public class V2SourceGenerator extends BaseGenerator {

  
  private static final String MASTER_VERSION = "2.9";

  public V2SourceGenerator(String dest, Map<String, CodeSystem> csmap, Set<String> knownCS) {
    super(dest, csmap, knownCS);
  }

  private Connection source;
  private Map<String, ObjectInfo> objects = new HashMap<String, ObjectInfo>();
  private Date currentVersionDate;
  private Map<String, Table> tables = new HashMap<String, Table>();
  private Map<String, VersionInfo> versions = new HashMap<String, VersionInfo>();


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
    public String status;
    public boolean backwardsCompatible;

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
   
    private String description;
    private int type;
    private boolean generate;
    private String section;
    private String anchor;
    private boolean caseInsensitive;
    private String steward;
    private String conceptDomainRef;
    
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
    public String getDescription() {
      return description;
    }
    public void setDescription(String string) throws SQLException {
      this.description = string;
    }
    public int getType() {
      return type;
    }
    public void setType(int type) {
      this.type = type;
    }
    public boolean isGenerate() {
      return generate;
    }
    public void setGenerate(boolean generate) {
      this.generate = generate;
    }
    public String getSection() {
      return section;
    }
    public void setSection(String section) {
      this.section = section;
    }
    public String getAnchor() {
      return anchor;
    }
    public void setAnchor(String anchor) {
      this.anchor = anchor;
    }
    public boolean isCaseInsensitive() {
      return caseInsensitive;
    }
    public void setCaseInsensitive(boolean caseInsensitive) {
      this.caseInsensitive = caseInsensitive;
    }
    public String getSteward() {
      return steward;
    }
    public void setSteward(String steward) {
      this.steward = steward;
    }
    public String getConceptDomainRef() {
      return conceptDomainRef;
    }

    public void setConceptDomainRef(String conceptDomainRef) {
      this.conceptDomainRef = conceptDomainRef;
    }

  }


  public class Table {
    private String id;
    private Map<String, String> langs = new HashMap<String, String>();
    private String name;
    private String oid;
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


    
    public void item(String version, String code, String display, String german, String table_name, String comments, int sno, String status, boolean backwardsCompatible) {
      if (!versions.containsKey(version))
        versions.put(version, new TableVersion(version, table_name));
      TableEntry entry = new TableEntry();
      entry.code = code;
      entry.display = display;
      if (!Utilities.noString(german))
        entry.langs.put("de", german);
      entry.comments = comments;
      entry.sortNo = sno;
      entry.status = status;
      entry.backwardsCompatible = backwardsCompatible;
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
          master.description = tv.description;
          master.name = tv.name;
          master.steward = tv.steward;
          master.section = tv.section;
          master.anchor = tv.anchor;
          master.generate = tv.generate;
          master.type = tv.type;
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
              tem.status = te.status;
              tem.backwardsCompatible = te.backwardsCompatible;
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


  public void load(String v2source) throws SQLException {
    System.out.println("loading v2 Source");
    this.source = DriverManager.getConnection("jdbc:ucanaccess://"+v2source); // need ucanaccess from http://ucanaccess.sourceforge.net/site.html   
  }

  public void loadTables() throws IOException, SQLException {
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
    query = stmt.executeQuery("SELECT table_id, version_id, display_name, oid_table, cs_oid, cs_version, vs_oid, vs_expansion, vocab_domain, interpretation, description_as_pub, table_type, generate, section, anchor, case_insensitive, steward  from HL7Tables where version_id < 100 order by version_id");
        
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
      if (!Utilities.noString(query.getString("oid_table"))) {
        t.setOid(query.getString("oid_table"));
      }
      TableVersion tv = t.versions.get(vid);
      if (tv == null) {
        tv = new TableVersion(vid, query.getString("display_name"));
        t.versions.put(vid, tv);
      }
      if (!Utilities.noString(query.getString("vocab_domain")))
        tv.setConceptDomainRef(query.getString("vocab_domain"));
      tv.setCsoid(query.getString("cs_oid"));
      tv.setCsversion(query.getString("cs_version"));
      tv.setVsoid(query.getString("vs_oid"));
      tv.setDescription(query.getString("description_as_pub"));
      tv.setType(query.getInt("table_type"));
      tv.setGenerate(query.getBoolean("generate"));
      tv.setSection(query.getString("section"));
      tv.setAnchor(query.getString("anchor"));
      tv.setCaseInsensitive(query.getBoolean("case_insensitive"));
      tv.setSteward(query.getString("steward"));
    }
    int i = 0;
    query = stmt.executeQuery("SELECT table_id, version_id, sort_no, table_value, display_name, interpretation, comment_as_pub, active, modification  from HL7TableValues where version_id < 100");
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
      String status = readStatusColumns(query.getString("active"), query.getString("modification"));
      boolean backwardsCompatible = "4".equals(query.getString("active"));
      
      tables.get(tid).item(vid.getVersion(), code, display, german, nameCache.get(tid+"/"+vid), comment, sno == null ? 0 : sno, status, backwardsCompatible);
      i++;
    }
    System.out.println(Integer.toString(i)+" entries loaded");
    for (Table t : tables.values()) {
      for (TableVersion v : t.versions.values()) {
        v.sort();
      }
    }
  }

  private String readStatusColumns(String active, String modification) {
    if (Utilities.noString(active))
      return null;
    if ("0".equals(active))
      return "Active";
    if ("1".equals(active))
      return "Deprecated";
    if ("2".equals(active))
      return "Retired";
    if ("3".equals(active))
      return "Active";
    if ("4".equals(active))
      return "Active";
    return null;
  }

  public void process() {
    for (String n : sorted(tables.keySet())) {
      Table t = tables.get(n);
      t.processVersions();
    }    
  }

  public int addConceptDomains(CodeSystem cs, Map<String, String> codes) {
    int count = 0;
    for (String n : sorted(tables.keySet())) {
      if (!n.equals("0000")) {
        Table t = tables.get(n);
        TableVersion tv = t.versions.get(MASTER_VERSION);
        if (tv != null) {
          ObjectInfo oi = objects.get(tv.getConceptDomainRef());
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
    }        
    return count;
  }

  public void generateCodeSystems() throws FileNotFoundException, IOException {
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
    knownCS.add(cs.getUrl());
    cs.setValueSet("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+cs.getId());
      
    cs.setVersion(tv.csversion);
    cs.setName("V2Table"+t.id);
    cs.setTitle("V2 Table: "+t.name);
    cs.setStatus(PublicationStatus.ACTIVE);
    cs.setExperimental(false);
    if (tv.csoid != null) {
      cs.getIdentifierFirstRep().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+tv.csoid);
      if (objects.containsKey(tv.csoid))
        cs.getIdentifierFirstRep().getValueElement().addExtension("http://healthintersections.com.au/fhir/StructureDefinition/identifier-display", new StringType(objects.get(tv.csoid).display));
    }
    cs.setDateElement(new DateTimeType(currentVersionDate, TemporalPrecisionEnum.DAY));
    cs.setPublisher("HL7, Inc");
    cs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("https://github.com/grahamegrieve/vocab-poc");
    if (tv.csoid != null && objects.containsKey(tv.csoid))
      cs.setDescription(objects.get(tv.csoid).description);
    else if (!Utilities.noString(tv.description))
      cs.setDescription(tv.description);
    else 
      cs.setDescription("Underlying Master Code System for V2 table "+t.id+" ("+t.name+")");
    cs.setPurpose("Underlying Master Code System for V2 table "+t.id+" ("+t.name+")");
    cs.setCopyright("Copyright HL7. Licensed under creative commons public domain");
    if (tv.isCaseInsensitive())
      cs.setCaseSensitive(false);
    else
      cs.setCaseSensitive(true); 
    cs.setHierarchyMeaning(CodeSystemHierarchyMeaning.ISA); // todo - is this correct
    cs.setCompositional(false);
    cs.setVersionNeeded(false);
    cs.setContent(CodeSystemContentMode.COMPLETE);
    if (!Utilities.noString(tv.getSteward()))
        cs.getExtension().add(new Extension().setUrl("http://hl7.org/fhir/StructureDefinition/structuredefinition-wg").setValue(new CodeType(tv.getSteward())));
    if (tv.getType() > 0)
      cs.getExtension().add(new Extension().setUrl("http://healthintersections.com.au/fhir/StructureDefinition/valueset-v2type").setValue(new CodeType(codeForType(tv.getType()))));
    if (tv.isGenerate())
      cs.getExtension().add(new Extension().setUrl("http://healthintersections.com.au/fhir/StructureDefinition/valueset-generate").setValue(new BooleanType(true)));

    cs.addProperty().setCode("status").setUri("http://healthintersections.com.au/csprop/status").setType(PropertyType.CODE).setDescription("Status of the concept");
    cs.addProperty().setCode("intro").setUri("http://healthintersections.com.au/csprop/intro").setType(PropertyType.CODE).setDescription("Version of HL7 in which the code was first defined");
    cs.addProperty().setCode("deprecated").setUri("http://healthintersections.com.au/csprop/deprecated").setType(PropertyType.CODE).setDescription("Version of HL7 in which the code was deprecated");
    cs.addProperty().setCode("backwardsCompatible").setUri("http://healthintersections.com.au/csprop/backwardsCompatible").setType(PropertyType.BOOLEAN).setDescription("Whether code is considered 'backwards compatible' (whatever that means)");

    for (TableEntry te : tv.entries) {
      ConceptDefinitionComponent c = cs.addConcept();
      c.setCode(te.code);
      String name = te.display;
      c.setDisplay(name);
      c.setDefinition(name);
      c.setId(Integer.toString(te.sortNo));
      if (!Utilities.noString(te.comments))
        ToolingExtensions.addCSComment(c, te.comments);
      if (te.getFirst() != null)
        c.addProperty().setCode("intro").setValue(new CodeType(te.getFirst()));
      if (!Utilities.noString(te.getLast()))
        c.addProperty().setCode("deprecated").setValue(new CodeType(te.getLast()));
      if (!Utilities.noString(te.status))
        c.addProperty().setCode("status").setValue(new CodeType(te.status));
      if (te.backwardsCompatible)
        c.addProperty().setCode("backwardsCompatible").setValue(new BooleanType(te.backwardsCompatible));
    }    

    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "cs-"+cs.getId())+".xml"), cs);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "vs-"+cs.getId())+".xml"), produceValueSet("Master", cs, t, tv));
  }

  private void generateVersionCodeSystem(Table t, TableVersion tv) throws FileNotFoundException, IOException {
    CodeSystem cs = new CodeSystem();
    cs.setId("v2-"+t.id+"-"+tv.version);
    cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/"+cs.getId());
    knownCS.add(cs.getUrl());
    cs.setValueSet("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+cs.getId());
      
    cs.setVersion(tv.csversion);
    cs.setName("V2Table"+t.id+"v"+tv.version);
    cs.setTitle("V2 Table: "+t.name);
    cs.setStatus(PublicationStatus.ACTIVE);
    cs.setExperimental(false);
    cs.getIdentifierFirstRep().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+tv.csoid);
    cs.setDateElement(new DateTimeType(currentVersionDate, TemporalPrecisionEnum.DAY));
    cs.setPublisher("HL7, Inc");
    cs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("https://github.com/grahamegrieve/vocab-poc");
    if (tv.csoid != null && objects.containsKey(tv.csoid))
      cs.setDescription(objects.get(tv.csoid).description);
    else if (!Utilities.noString(tv.description))
      cs.setDescription(tv.description);
    else 
      cs.setDescription("Underlying Code System for V2 table "+t.id+" ("+t.name+" "+tv.version+")");
    cs.setPurpose("Underlying Code System for V2 table "+t.id+" ("+t.name+", version "+tv.version+")");
    cs.setCopyright("Copyright HL7. Licensed under creative commons public domain");
    if (tv.isCaseInsensitive())
      cs.setCaseSensitive(false);
    else
      cs.setCaseSensitive(true); // not that it matters, since they are all numeric
    cs.setHierarchyMeaning(CodeSystemHierarchyMeaning.ISA); // todo - is this correct
    cs.setCompositional(false);
    cs.setVersionNeeded(false);
    cs.setContent(CodeSystemContentMode.COMPLETE);
    if (!Utilities.noString(tv.getSteward()))
      cs.getExtension().add(new Extension().setUrl("http://hl7.org/fhir/StructureDefinition/structuredefinition-wg").setValue(new CodeType(tv.getSteward())));
    if (!Utilities.noString(tv.getAnchor()))
      cs.getExtension().add(new Extension().setUrl("http://healthintersections.com.au/fhir/StructureDefinition/valueset-stdref").setValue(new UriType("http://hl7.org/v2/"+tv.getAnchor())));
    if (!Utilities.noString(tv.getSection()))
      cs.getExtension().add(new Extension().setUrl("http://healthintersections.com.au/fhir/StructureDefinition/valueset-stdsection").setValue(new StringType(tv.getSection())));
    if (tv.getType() > 0)
      cs.getExtension().add(new Extension().setUrl("http://healthintersections.com.au/fhir/StructureDefinition/valueset-v2type").setValue(new CodeType(codeForType(tv.getType()))));
    if (tv.isGenerate())
      cs.getExtension().add(new Extension().setUrl("http://healthintersections.com.au/fhir/StructureDefinition/valueset-generate").setValue(new BooleanType(true)));

    cs.addProperty().setCode("status").setUri("http://healthintersections.com.au/csprop/status").setType(PropertyType.CODE).setDescription("Status of the concept");
    cs.addProperty().setCode("intro").setUri("http://healthintersections.com.au/csprop/intro").setType(PropertyType.CODE).setDescription("Version of HL7 in which the code was first defined");
    cs.addProperty().setCode("deprecated").setUri("http://healthintersections.com.au/csprop/deprecated").setType(PropertyType.CODE).setDescription("Version of HL7 in which the code was deprecated");
    cs.addProperty().setCode("backwardsCompatible").setUri("http://healthintersections.com.au/csprop/backwardsCompatible").setType(PropertyType.BOOLEAN).setDescription("Whether code is considered 'backwards compatible' (whatever that means)");

    for (TableEntry te : tv.entries) {
      ConceptDefinitionComponent c = cs.addConcept();
      c.setCode(te.code);
      String name = te.display;
      c.setDisplay(name);
      c.setDefinition(name);
      c.setId(Integer.toString(te.sortNo));
      if (!Utilities.noString(te.comments))
        ToolingExtensions.addCSComment(c, te.comments);
      if (te.getFirst() != null)
        c.addProperty().setCode("intro").setValue(new CodeType(te.getFirst()));
      if (!Utilities.noString(te.getLast()))
        c.addProperty().setCode("deprecated").setValue(new CodeType(te.getLast()));
      if (!Utilities.noString(te.status))
        c.addProperty().setCode("status").setValue(new CodeType(te.status));
      if (te.backwardsCompatible)
        c.addProperty().setCode("backwardsCompatible").setValue(new BooleanType(te.backwardsCompatible));
    }    

    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "v"+tv.version, "cs-"+cs.getId())+".xml"), cs);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "v"+tv.version, "vs-"+cs.getId())+".xml"), produceValueSet("Master", cs, t, tv));
  }
  
  private String codeForType(int type) {
    if (type == 0)
      return  "undefined";
    if (type == 1)
      return  "User";
    if (type == 2)
      return  "HL7";
    if (type == 4)
      return  "no longer used";
    if (type == 5)
      return  "replaced";
    if (type == 6)
      return  "User Group /National Defined";
    if (type == 7)
      return  "Imported";
    if (type == 8)
      return  "Externally defined";
    
    throw new Error("not done yet: "+Integer.toString(type));
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

  public void generateTables() throws FileNotFoundException, IOException {
    CodeSystem cs = new CodeSystem();
    cs.setId("v2-tables");
    cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/"+cs.getId());
    cs.setName("V2Tables");
    cs.setTitle("V2 Table List");
    cs.setStatus(PublicationStatus.ACTIVE);
    cs.setExperimental(false);
    
    cs.setDateElement(new DateTimeType(currentVersionDate, TemporalPrecisionEnum.DAY));
    cs.setPublisher("HL7, Inc");
    cs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue("https://github.com/grahamegrieve/vocab-poc");
    cs.setDescription("Master List of V2 Tables");
    cs.setCopyright("Copyright HL7. Licensed under creative commons public domain");
    cs.setCaseSensitive(true); 
    cs.setHierarchyMeaning(CodeSystemHierarchyMeaning.ISA); 
    cs.setCompositional(false);
    cs.setVersionNeeded(false);
    cs.setContent(CodeSystemContentMode.COMPLETE);

    cs.addProperty().setCode("oid").setUri("http://healthintersections.com.au/csprop/oid").setType(PropertyType.STRING).setDescription("OID For Table");
    cs.addProperty().setCode("csoid").setUri("http://healthintersections.com.au/csprop/csoid").setType(PropertyType.STRING).setDescription("OID For Code System");
    cs.addProperty().setCode("vsoid").setUri("http://healthintersections.com.au/csprop/vsoid").setType(PropertyType.STRING).setDescription("OID For Value Set");
    cs.addProperty().setCode("v2type").setUri("http://healthintersections.com.au/csprop/v2type").setType(PropertyType.CODE).setDescription("Type of table");
    cs.addProperty().setCode("generate").setUri("http://healthintersections.com.au/csprop/generate").setType(PropertyType.BOOLEAN).setDescription("whether to generate table");
    cs.addProperty().setCode("version").setUri("http://healthintersections.com.au/csprop/version").setType(PropertyType.BOOLEAN).setDescription("Business version of table metadata");
    
    Map<String, String> codes = new HashMap<String, String>();
    int count = 0;
    for (String n : sorted(tables.keySet())) {
      if (!n.equals("0000")) {
        Table t = tables.get(n);
        TableVersion tv = t.master;
        if (tv != null) {
          ConceptDefinitionComponent c = cs.addConcept();
          c.setCode(t.id);
          count++;
          String name = t.getName();
          c.setDisplay(t.name);
          c.setDefinition(tv.description);
          c.addProperty().setCode("oid").setValue(new StringType(t.oid));
          if (!Utilities.noString(tv.csoid))
            c.addProperty().setCode("csoid").setValue(new StringType(tv.csoid));
          if (!Utilities.noString(tv.vsoid))
            c.addProperty().setCode("vsoid").setValue(new StringType(tv.vsoid));
          if (tv.getType() > 0)
            c.addProperty().setCode("v2type").setValue(new CodeType(codeForType(tv.getType())));
          if (tv.isGenerate())
            c.addProperty().setCode("generate").setValue(new BooleanType(true));
          c.addProperty().setCode("version").setValue(new IntegerType(1));
        }
      }
    }        

    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "v2", "v2-tables.xml")), cs);
    System.out.println("Save tables ("+Integer.toString(count)+" found)");

    
  }

}
