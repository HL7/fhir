package org.hl7.fhir.convertors.v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.CodeType;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.Enumerations.BindingStrength;
import org.hl7.fhir.dstu3.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.dstu3.model.IntegerType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.utilities.Utilities;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public class V2ProfileGenerator {

  // open the v2 database
  // for each version
  // for each data type
  // for each segment

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

  public class VersionInfo {
    public VersionInfo(String id, String version) {
      this.id = id;
      this.version = version;
    }
    private String id;
    private String version;
    private Map<String, Segment> segments = new HashMap<String, Segment>();
    private Map<Integer, DataElement> dataelements = new HashMap<Integer, DataElement>();
  }

  private Database db;
  private Map<String, VersionInfo> versions = new HashMap<String, VersionInfo>();


  private void processVersion(VersionInfo vi, String dest) throws IOException {
    System.out.println("Version "+vi.version);
    Utilities.createDirectory(dest);
    for (String s : sorted(vi.segments.keySet())) {
      if (s.length() == 3)
        generateSegmentProfile(vi, vi.segments.get(s), dest);
    }
  }

  private void generateSegmentProfile(VersionInfo vi, Segment segment, String dest) throws FileNotFoundException, IOException {
    StructureDefinition sd = new StructureDefinition();
    sd.setId(vi.version+"-"+segment.code);
    sd.setUrl("http://hl7.org/fhir/v2/StructureDefinition/"+vi.version+"-"+segment.code);
    sd.setName("Segment "+segment.code+" for Version "+vi.version);
    sd.setStatus(ConformanceResourceStatus.DRAFT);
    sd.setPublisher("HL7, International");
    sd.setDateElement(DateTimeType.today());
    sd.setCopyright("This content is published under HL7's normal content licence, the license that applies to HL7 v2. Use is only open to HL7 members");
    sd.setKind(StructureDefinitionKind.LOGICAL);
    sd.setType("Element");
    sd.setBaseDefinition("http://hl7.org/fhir/v2/StructureDefinition/logical#Message.segment");
    sd.setDerivation(TypeDerivationRule.CONSTRAINT);
    
    int i = 0;
    sd.getDifferential().addElement().setPath("Message.segment:"+segment.code+".element").getSlicing().setOrdered(true).addDiscriminator("index");
    for (SegmentDataElement sde : segment.elements) {
      i++;
      ElementDefinition ed = sd.getDifferential().addElement();
      ed.setPath("Message.segment.element");
      ed.setId("Message.segment:"+segment.code+".element:"+Integer.toString(i));
      ed.setName(segment.code+"-"+Integer.toString(i));
      if ("R".equals(sde.conf))
        ed.setMin(1);
      else
        ed.setMin(0);
      ed.setMax(sde.max == Integer.MAX_VALUE ? "*" : Integer.toString(sde.max));
      ed.setDefinition(sde.element.desc);
      ed.addExtension().setUrl("http://hl7.org/fhir/v2/StructureDefinition/req-opt").setValue(new CodeType(sde.conf));
      
      ed = sd.getDifferential().addElement();
      ed.setPath("Message.segment.element.index");
      ed.setId("Message.segment:"+segment.code+".element:"+Integer.toString(i)+".index");
      ed.setFixed(new IntegerType(i));
      
      ed = sd.getDifferential().addElement();
      ed.setPath("Message.segment.element.content");
      ed.setId("Message.segment:"+segment.code+".element:"+Integer.toString(i)+".content");
      ed.addType().setProfile("http://hl7.org/fhir/v2/StructureDefinition/"+sde.element.type);
      if (sde.element.tbl > 0) {
        ed.getBinding().setStrength("CNE".equals(sde.element.type) || "ID".equals(sde.element.type)  ? BindingStrength.REQUIRED : BindingStrength.EXTENSIBLE); 
        ed.getBinding().setValueSet(new UriType("http://hl7.org/fhir/ValueSet/v2-"+Utilities.padLeft(Integer.toString(sde.element.tbl), '0', 4)));
      }
      // todo: length / minLength / maxLength / confLength;
    }
    FileOutputStream stream = new FileOutputStream(Utilities.path(dest, vi.version+"-"+segment.code+".xml"));
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(stream, sd);
    stream.close();
  }

  private void connect(String source) throws IOException {    
    db = DatabaseBuilder.open(new File(source));
  }

  private void execute(String source, String output) throws IOException {
    connect(source);
    loadVersions();
    loadSegments();
    loadDataElements();
    loadSegmentDataElements();
    for (String s : sorted(versions.keySet())) {
      VersionInfo vi = versions.get(s);
      processVersion(vi, Utilities.path(output, "v"+vi.version));
    }
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
