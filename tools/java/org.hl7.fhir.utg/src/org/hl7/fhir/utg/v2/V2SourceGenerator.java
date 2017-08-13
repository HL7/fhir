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
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Factory;
import org.hl7.fhir.r4.model.TemporalPrecisionEnum;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.r4.model.CodeSystem.CodeSystemHierarchyMeaning;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.utg.BaseGenerator;
import org.hl7.fhir.utilities.Utilities;

public class V2SourceGenerator extends BaseGenerator {

  public V2SourceGenerator(String dest, Map<String, CodeSystem> csmap) {
    super(dest, csmap);
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

}
