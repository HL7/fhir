package org.hl7.fhir.convertors.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.dstu3.conformance.ProfileUtilities;
import org.hl7.fhir.dstu3.context.SimpleWorkerContext;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.CodeType;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.Enumerations.BindingStrength;
import org.hl7.fhir.dstu3.model.Enumerations.PublicationStatus;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.ElementDefinition.DiscriminatorType;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.utils.EOperationOutcome;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.Utilities;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class V2ProfileGenerator {

  // open the v2 database
  // for each version
  //   for each table
  //   for each data type
  //   for each segment

  public class DataElement {
    private int id;
    private String desc;
    private String type;
    private String length;
    private Integer minLength;
    private Integer maxLength;
    private String confLength;
    private int tbl;
    
    public DataElement(int id, String desc, String type, String length, Integer minLength, Integer maxLength, String confLength, int tbl) {
      this.id = id;
      this.desc = desc;
      this.type = type;
      this.length = length;
      this.minLength = minLength;
      this.maxLength = maxLength;
      this.confLength = confLength;
      this.tbl = tbl;
    }

  }

  public class SegmentDataElement {
    public SegmentDataElement(DataElement element, String conf, int max) {
      this.element = element;
      this.conf = conf;
      this.max = max;
    }
    private DataElement element;
    private String conf;
    private int max;
  }
  
  public class Segment {
    public Segment(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }
    private String code;
    private String desc;
    private List<SegmentDataElement> elements = new ArrayList<SegmentDataElement>();
  }

  public class TableItem {
    public TableItem(String code, String desc, int sn) {
      this.code = code;
      this.desc = desc;
      this.sn = sn;
    }
    private String code;
    private String desc;
    private int sn;
    
  }
  
  public class TableDef implements Comparator<TableItem> {
    public TableDef(String id, String desc, String type) {
      this.id = id;
      this.desc = desc;
      this.type = type;
    }
    private String id;
    private String desc;
    private String type;
    private List<TableItem> items = new ArrayList<TableItem>();
    public void sort() {
      Collections.sort(items, this);
    }
    @Override
    public int compare(TableItem t1, TableItem t2) {
      return Integer.compare(t1.sn, t2.sn);
    }
  }

  public class Component {
    private int sn;
    private String desc;
    private String type;
    private String profile;
    private String length;
    private Integer minLength;
    private Integer maxLength;
    private String confLength;
    private Integer tbl;
    private String conf;
  }
  
  public class DataType  implements Comparator<Component>  {
    public DataType(String code, String desc) {
      this.code = code;
      this.desc = desc;
    }
    private String code;
    private String desc;
    private String base;
    private boolean repeating;
    private boolean primitive;
    private boolean mixed;
    private List<Component> components = new ArrayList<Component>();
    public void sort() {
      Collections.sort(components, this);
    }
    @Override
    public int compare(Component c1, Component c2) {
      return Integer.compare(c1.sn, c2.sn);
    }
  }
  public class VersionInfo {
    public VersionInfo(String id, String version) {
      this.id = id;
      this.version = version;
    }
    private String id;
    private String version;
    private Map<String, Segment> segments = new HashMap<String, Segment>();
    private Map<String, TableDef> tables = new HashMap<String, TableDef>();
    private Map<Integer, DataElement> dataelements = new HashMap<Integer, DataElement>();
    private Map<String, DataType> datatypes = new HashMap<String, DataType>();
  }

  private Database db;
  private Map<String, VersionInfo> versions = new HashMap<String, VersionInfo>();
  private ProfileUtilities utils;
  private SimpleWorkerContext context;
  private NarrativeGenerator gen;


  private void processVersion(VersionInfo vi, String dest) throws IOException, DefinitionException, FHIRException, EOperationOutcome {
    System.out.println("Version "+vi.version);
    Utilities.createDirectory(dest);
    Utilities.createDirectory(Utilities.path(dest, "segments"));
    Utilities.createDirectory(Utilities.path(dest, "tables"));
    Utilities.createDirectory(Utilities.path(dest, "datatypes"));
    for (String s : sorted(vi.tables.keySet()))
      generateTable(vi, vi.tables.get(s), dest);
    for (String s : sorted(vi.segments.keySet())) {
      if (s.length() == 3)
        generateSegmentProfile(vi, vi.segments.get(s), dest);
    }
    for (String s : sorted(vi.datatypes.keySet()))
      generateDataType(vi, vi.datatypes.get(s), dest);
  }

  private void generateDataType(VersionInfo vi, DataType dt, String dest) throws FileNotFoundException, IOException, DefinitionException, FHIRException, EOperationOutcome {
    if (dt.primitive) {
      generatePrimitiveDatatype(vi, dt, dest);
    } else {
      generateComplexDataType(vi, dt, dest);
    }
  }

  private void generatePrimitiveDatatype(VersionInfo vi, DataType dt, String dest) throws FileNotFoundException, IOException, DefinitionException, FHIRException, EOperationOutcome {
    StructureDefinition sd = new StructureDefinition();
    sd.setId(vi.version+"-"+dt.code);
    sd.setUrl("http://hl7.org/fhir/v2/StructureDefinition/"+vi.version+"-"+dt.code);
    sd.setName(vi.version+"."+dt.code);
    sd.setTitle("Data Type "+dt.code+" for Version "+vi.version);
    sd.setStatus(PublicationStatus.DRAFT);
    sd.setPublisher("HL7, International");
    sd.setDateElement(DateTimeType.today());
    sd.setCopyright("This content is published under HL7's normal content licence, the license that applies to HL7 v2. Use is only open to HL7 members");
    sd.setKind(StructureDefinitionKind.LOGICAL);
    sd.setType("Element");
    sd.setBaseDefinition("http://hl7.org/fhir/v2/StructureDefinition/Component");
    sd.setDerivation(TypeDerivationRule.CONSTRAINT);

    ElementDefinition ed = sd.getDifferential().addElement();
    ed.setPath("Component");
    ed.setDefinition(dt.desc);

    ed = sd.getDifferential().addElement();
    ed.setPath("Component.component");
    ed.setMax("0");
    utils.generateSnapshot(context.fetchResource(StructureDefinition.class, sd.getBaseDefinition()), sd, sd.getUrl(), sd.getName());
    gen.generate(sd);
    FileOutputStream stream = new FileOutputStream(Utilities.path(dest, "datatypes", vi.version+"-"+dt.code+".xml"));
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(stream, sd);
    stream.close();
  }

  private void generateComplexDataType(VersionInfo vi, DataType dt, String dest) throws FileNotFoundException, IOException, DefinitionException, FHIRException, EOperationOutcome {
    StructureDefinition sd = new StructureDefinition();
    sd.setId(vi.version+"-"+dt.code);
    sd.setUrl("http://hl7.org/fhir/v2/StructureDefinition/"+vi.version+"-"+dt.code);
    sd.setName("Data Type "+dt.code+" for Version "+vi.version);
    sd.setStatus(PublicationStatus.DRAFT);
    sd.setPublisher("HL7, International");
    sd.setDateElement(DateTimeType.today());
    sd.setCopyright("This content is published under HL7's normal content licence, the license that applies to HL7 v2. Use is only open to HL7 members");
    sd.setKind(StructureDefinitionKind.LOGICAL);
    sd.setType("Element");
    sd.setBaseDefinition("http://hl7.org/fhir/v2/StructureDefinition/Component");
    sd.setDerivation(TypeDerivationRule.CONSTRAINT);

    ElementDefinition ed = sd.getDifferential().addElement();
    ed.setPath("Component");
    ed.setDefinition(dt.desc);

    ed = sd.getDifferential().addElement();
    ed.setPath("Component.text");
    ed.setMax("0");

    sd.getDifferential().addElement().setPath("Component:"+dt.code+".component").getSlicing().setOrdered(true).addDiscriminator().setType(DiscriminatorType.VALUE).setPath("index");

    int i = 0;
    for (Component comp : dt.components) {
      i++;
      ed = sd.getDifferential().addElement();
      ed.setPath("Component.component");
      ed.setId("Component:"+dt.code+".component:"+Integer.toString(i));
      ed.setSliceName(dt.code+"-"+Integer.toString(i));
      if ("R".equals(comp.conf))
        ed.setMin(1);
      else
        ed.setMin(0);
      ed.setDefinition(comp.desc);
      ed.addExtension().setUrl("http://hl7.org/fhir/v2/StructureDefinition/req-opt").setValue(new CodeType(comp.conf));
      ed.addType().setProfile("http://hl7.org/fhir/v2/StructureDefinition/"+(comp.profile == null ? comp.type : comp.profile));
      if (comp.tbl > 0) {
        ed.getBinding().setStrength("CNE".equals(comp.type) || "ID".equals(comp.type)  ? BindingStrength.REQUIRED : BindingStrength.EXTENSIBLE); 
        ed.getBinding().setValueSet(new UriType("http://hl7.org/fhir/ValueSet/v2-"+Utilities.padLeft(Integer.toString(comp.tbl), '0', 4)));
      }
      if (comp.maxLength != null && comp.maxLength > 0)
        ed.setMaxLength(comp.maxLength);
      if (!Utilities.noString(comp.confLength))
        ed.addExtension("http://hl7.org/fhir/v2/StructureDefinition/conformance-length", new StringType(comp.confLength));
    }
    utils.generateSnapshot(context.fetchResource(StructureDefinition.class, sd.getBaseDefinition()), sd, sd.getUrl(), sd.getName());
    gen.generate(sd);
    FileOutputStream stream = new FileOutputStream(Utilities.path(dest, "datatypes", vi.version+"-"+dt.code+".xml"));
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(stream, sd);
    stream.close();
  }

  private void generateTable(VersionInfo vi, TableDef tbl, String dest) throws IOException {
    ValueSet vs = new ValueSet();
    vs.setId(vi.version+"-"+tbl.id);
    vs.setUrl("http://hl7.org/fhir/v2/ValueSet/"+vi.version+"-"+tbl.id);
    vs.setName("Table "+tbl.id+" for Version "+vi.version);
    vs.setStatus(PublicationStatus.DRAFT);
    vs.setPublisher("HL7, International");
    vs.setDateElement(DateTimeType.today());
    vs.setCopyright("This content is published under HL7's normal content licence, the license that applies to HL7 v2. Use is only open to HL7 members");

    ConceptSetComponent inc = vs.getCompose().addInclude();
    inc.setSystem("http://hl7.org/fhir/v2/"+tbl.id);
    for (TableItem ti : tbl.items) {
      ConceptReferenceComponent cc = inc.addConcept();
      cc.setCode(ti.code);
      cc.setDisplay(ti.desc);
    }
    
    FileOutputStream stream = new FileOutputStream(Utilities.path(dest, "tables", vi.version+"-"+tbl.id+".xml"));
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(stream, vs);
    stream.close();
  }

  private void generateSegmentProfile(VersionInfo vi, Segment segment, String dest) throws FileNotFoundException, IOException, DefinitionException, FHIRException, EOperationOutcome {
    StructureDefinition sd = new StructureDefinition();
    sd.setId(vi.version+"-"+segment.code);
    sd.setUrl("http://hl7.org/fhir/v2/StructureDefinition/"+vi.version+"-"+segment.code);
    sd.setName("Segment "+segment.code+" for Version "+vi.version);
    sd.setStatus(PublicationStatus.DRAFT);
    sd.setPublisher("HL7, International");
    sd.setDateElement(DateTimeType.today());
    sd.setCopyright("This content is published under HL7's normal content licence, the license that applies to HL7 v2. Use is only open to HL7 members");
    sd.setKind(StructureDefinitionKind.LOGICAL);
    sd.setType("Element");
    sd.setBaseDefinition("http://hl7.org/fhir/v2/StructureDefinition/Segment");
    sd.setDerivation(TypeDerivationRule.CONSTRAINT);
    
    int i = 0;
    sd.getDifferential().addElement().setPath("Segment:"+segment.code+".element").getSlicing().setOrdered(true).addDiscriminator().setType(DiscriminatorType.VALUE).setPath("index");
    for (SegmentDataElement sde : segment.elements) {
      i++;
      ElementDefinition ed = sd.getDifferential().addElement();
      ed.setPath("Segment.element");
      ed.setId("Segment:"+segment.code+".element:"+Integer.toString(i));
      ed.setSliceName(segment.code+"-"+Integer.toString(i));
      if ("R".equals(sde.conf))
        ed.setMin(1);
      else
        ed.setMin(0);
      ed.setMax(sde.max == Integer.MAX_VALUE ? "*" : Integer.toString(sde.max));
      ed.setDefinition(sde.element.desc);
      ed.addExtension().setUrl("http://hl7.org/fhir/v2/StructureDefinition/req-opt").setValue(new CodeType(sde.conf));
      
      ed = sd.getDifferential().addElement();
      ed.setPath("Segment.element.field");
      ed.setId("Segment:"+segment.code+".element.field");
      ed.addType().setProfile("http://hl7.org/fhir/v2/StructureDefinition/"+sde.element.type);
      if (sde.element.tbl > 0) {
        ed.getBinding().setStrength("CNE".equals(sde.element.type) || "ID".equals(sde.element.type)  ? BindingStrength.REQUIRED : BindingStrength.EXTENSIBLE); 
        ed.getBinding().setValueSet(new UriType("http://hl7.org/fhir/ValueSet/v2-"+Utilities.padLeft(Integer.toString(sde.element.tbl), '0', 4)));
      }
      if (sde.element != null) { 
        if (sde.element.maxLength != null && sde.element.maxLength > 0)
          ed.setMaxLength(sde.element.maxLength);
        if (!Utilities.noString(sde.element.confLength))
          ed.addExtension("http://hl7.org/fhir/v2/StructureDefinition/conformance-length", new StringType(sde.element.confLength));
      }
    }
    utils.generateSnapshot(context.fetchResource(StructureDefinition.class, sd.getBaseDefinition()), sd, sd.getUrl(), sd.getName());
    gen.generate(sd);
    FileOutputStream stream = new FileOutputStream(Utilities.path(dest, "segments", vi.version+"-"+segment.code+".xml"));
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(stream, sd);
    stream.close();
  }

  private void connect(String source) throws IOException {    
    db = DatabaseBuilder.open(new File(source));
  }

  private void execute(String source, String output) throws IOException, FHIRException, EOperationOutcome {
    System.out.println("Loading Context");
    context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\igpack.zip");
    utils = new ProfileUtilities(context, null, null);
    gen = new NarrativeGenerator("", "", context);
    System.out.println("Loading v2 reference model");
    addLogicals();
    connect(source);
    loadVersions();
    loadTables();
    loadDataTypes();
    loadSegments();
    loadDataElements();
    loadSegmentDataElements();
    for (String s : sorted(versions.keySet())) {
      VersionInfo vi = versions.get(s);
      processVersion(vi, Utilities.path(output, "v"+vi.version));
    }
  }

  private void addLogicals() throws FileNotFoundException, IOException, FHIRException {
    loadFile("C:\\work\\org.hl7.fhir\\build\\guides\\v2\\reference\\Component.xml");
    loadFile("C:\\work\\org.hl7.fhir\\build\\guides\\v2\\reference\\Field.xml");
    loadFile("C:\\work\\org.hl7.fhir\\build\\guides\\v2\\reference\\Segment.xml");
    loadFile("C:\\work\\org.hl7.fhir\\build\\guides\\v2\\reference\\Message.xml");
  }

  private void loadFile(String path) throws FileNotFoundException, IOException, FHIRException {
    StructureDefinition sd = (StructureDefinition) new XmlParser().parse(new FileInputStream(path));
    context.seeResource(sd.getUrl(), sd);
    utils.generateSnapshot(context.fetchResource(StructureDefinition.class, sd.getBaseDefinition()), sd, sd.getUrl(), sd.getName());
  }

  private List<String> sorted(Set<String> keys) {
    List<String> res = new ArrayList<String>();
    res.addAll(keys);
    Collections.sort(res);
    return res;
  }

  private void loadSegmentDataElements() throws IOException {
    Table table = db.getTable("HL7SegmentDataElements");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      int id = row.getInt("version_id");
      String code = row.getString("seg_code");
      short seq = row.getShort("seq_no");
      int did = row.getInt("data_item");
      String conf = row.getString("req_opt");
      String rep = row.getString("repetitional");
      Short repC = row.getShort("repetitions");
      
      if (versions.containsKey(vid)) {
        VersionInfo vi = versions.get(vid);
        List<SegmentDataElement> segs = vi.segments.get(code).elements;
        segs.add(new SegmentDataElement(vi.dataelements.get(did), conf, "Y".equals(rep) ? (repC == null || repC == 0 ? Integer.MAX_VALUE : repC) : 1));
      }
    }
  }


  private void loadDataElements() throws IOException {
    Table table = db.getTable("HL7DataElements");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      int id = row.getInt("data_item");
      String desc = row.getString("description");
      String type = row.getString("data_structure");
      String length = row.getString("length_old");
      Integer minLength = row.getInt("min_length");
      Integer maxLength = row.getInt("max_length");
      String confLength = row.getString("conf_length");
      int tbl = row.getInt("table_id");
      
      if (versions.containsKey(vid))
        versions.get(vid).dataelements.put(id, new DataElement(id, desc, type, length, minLength, maxLength, confLength, tbl));
    }
  }

  private class ComponentEntry {
    
    private String description;
    private Integer  table_id;
    private String data_type_code;
    private String data_structure;
  }
  
  private void loadDataTypes() throws IOException {
    Table table = db.getTable("Hl7DataTypes");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      String code = row.getString("data_type_code");
      String desc = row.getString("description");
      if (versions.containsKey(vid))
        versions.get(vid).datatypes.put(code, new DataType(code, desc));
    }
    table = db.getTable("Hl7DataStructures");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      String code = row.getString("data_structure");
      String desc = row.getString("description");
      String dtc = row.getString("data_type_code");
      if (versions.containsKey(vid)) {
        DataType dt = versions.get(vid).datatypes.get(code);
        if (dt == null) {
          dt = new DataType(code, desc);
          versions.get(vid).datatypes.put(code, dt);
          dt.base = dtc;
        } else if (desc != null) 
          dt.desc = desc;
        dt.repeating = row.getBoolean("repeating");
        dt.primitive = row.getBoolean("elementary");
        dt.mixed = row.getBoolean("elementary");
      }
    }
    Map<String, ComponentEntry> comps = new HashMap<String, ComponentEntry>();
    table = db.getTable("Hl7Components");
    for (Row row : table) {
      ComponentEntry ce = new ComponentEntry();
      String id = Integer.toString(row.getInt("version_id"))+"::"+Integer.toString(row.getInt("comp_no"));
      ce.description = row.getString("description");
      ce.table_id = row.getInt("comp_no");
      ce.data_type_code = row.getString("data_type_code");
      ce.data_structure = row.getString("data_structure");
      comps.put(id, ce);
    }
    table = db.getTable("Hl7DataStructureComponents");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      String code = row.getString("data_structure");
      int cn = row.getInt("comp_no");
      String id = Integer.toString(row.getInt("version_id"))+"::"+Integer.toString(row.getInt("comp_no"));
      ComponentEntry ce = comps.get(id);
      Component comp = new Component();
      DataType dt = versions.get(vid).datatypes.get(code);
      dt.components.add(comp);
      comp.sn = row.getShort("seq_no");
      comp.tbl = row.getInt("table_id");      
      comp.length = row.getString("length_old");
      comp.minLength = row.getInt("min_length");
      comp.maxLength = row.getInt("max_length");
      comp.confLength = row.getString("conf_length");
      comp.conf = row.getString("req_opt");
      comp.type = ce.data_type_code;
      comp.profile = ce.data_structure;
      if (comp.tbl == 0)
        comp.tbl = ce.table_id;
      comp.desc = ce.description;
    }
    for (VersionInfo vi : versions.values())
      for (TableDef tbl : vi.tables.values())
        tbl.sort();
    
  }

  private void loadSegments() throws IOException {
    Table table = db.getTable("Hl7Segments");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      String code = row.getString("seg_code");
      String desc = row.getString("description");
      if (versions.containsKey(vid))
        versions.get(vid).segments.put(code, new Segment(code, desc));
    }
  }

  private void loadTables() throws IOException {
    Table table = db.getTable("Hl7Tables");
    for (Row row : table) {
      if (row.getInt("table_id") > 0) {
        String vid = Integer.toString(row.getInt("version_id")); 
        String id = Utilities.padLeft(Integer.toString(row.getInt("table_id")), '0', 4);
        String desc = row.getString("display_name");
        String tt = row.getString("table_type");
        if (versions.containsKey(vid))
          versions.get(vid).tables.put(id, new TableDef(id, desc, tt));
      }
    }
    table = db.getTable("Hl7TableValues");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      String id = Utilities.padLeft(Integer.toString(row.getInt("table_id")), '0', 4);
      String value = row.getString("table_value");
      String desc = row.getString("display_name");
      Short sn = row.getShort("sort_no");
      if (versions.containsKey(vid))
        versions.get(vid).tables.get(id).items.add(new TableItem(value, desc, sn == null ? 0 : sn));
    }
    for (VersionInfo vi : versions.values())
      for (TableDef tbl : vi.tables.values())
        tbl.sort();
  }

  private void loadVersions() throws IOException {
    Table table = db.getTable("HL7Versions");
    for (Row row : table) {
      String vid = Integer.toString(row.getInt("version_id")); 
      String dn = row.getString("hl7_version");
      if (!dn.contains(" ") && !dn.equals("0"))
        versions.put(vid, new VersionInfo(vid, dn));
    }
  }

  public static void main(String[] args) throws Exception {
    if (!(new File(args[0]).exists()))
      throw new Exception("v2 database not found");
    if (!(new File(args[1]).exists()))
      throw new Exception("output directory not found");
    new V2ProfileGenerator().execute(args[0], args[1]);
  }


}
