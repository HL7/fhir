package org.hl7.fhir.utg.v2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Type;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.utg.BaseExporter;
import org.hl7.fhir.utilities.CSVWriter;
import org.hl7.fhir.utilities.Utilities;

public class V2CsvExporter extends BaseExporter {

  private static final String CURRENT_V2_VERSION = "2.9";
  private String folder;
  private CSVWriter tables;
  private CSVWriter tableCodes;
  
  public V2CsvExporter(String dest) throws IOException {
    folder = dest;
    Utilities.createDirectory(folder);
    tables = new CSVWriter(new FileOutputStream(Utilities.path(folder, "tables.csv")));
    tables.line("table_id", "version_id", "display_name", "description_as_pub", "table_table", "generate", "oid_table", "section", "anchor", "case_insensitive", "steward", "cs_oid", "cs_version", "vs_oid");
    tableCodes = new CSVWriter(new FileOutputStream(Utilities.path(folder, "tableCodes.csv")));
    tableCodes.line("table", "sort-no", "level", "code", "print name", "status");
  }  

  public void generate(Map<String, CodeSystem> codeSystems, Map<String, ValueSet> valueSets) throws IOException {
    System.out.print("Write v2.. ");
    
    CodeSystem cs = codeSystems.get("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/v2-tables");
    for (ConceptDefinitionComponent c : cs.getConcept()) {
      processV2Table(c, codeSystems.get("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/v2-"+c.getCode()));
      
    }
    
    System.out.println("Done");
  }

  private void processV2Table(ConceptDefinitionComponent tbl, CodeSystem cs) throws IOException {
    
    // table fields:
    //
    String table_id = tbl.getCode(); 
    String version_id = CURRENT_V2_VERSION;
    String display_name = tbl.getDisplay();
    String description_as_pub = tbl.getDefinition();
    String table_table = readTableType(tbl);
    String generate = readGenerate(tbl);
    String oid_table = readString(CodeSystemUtilities.readProperty(tbl, "oid"));
    String cs_oid = readString(CodeSystemUtilities.readProperty(tbl, "csoid")); 
    String vs_oid = readString(CodeSystemUtilities.readProperty(tbl, "vsoid"));
    String section = readProperty(CodeSystemUtilities.readProperty(tbl, "stdsection"), 0);
    String anchor = readProperty(CodeSystemUtilities.readProperty(tbl, "stdref"), 18); 
    String case_insensitive = cs == null ? "" : cs.getCaseSensitive() ? "1" : "0";
    String steward = cs == null ? null : readExtension(cs, "http://hl7.org/fhir/StructureDefinition/structuredefinition-wg", 0); 
    String cs_version = cs == null ? null : cs.getVersion();
    
    tables.line(table_id, version_id, display_name, description_as_pub, table_table, generate, oid_table, section, anchor, case_insensitive, steward, cs_oid, cs_version, vs_oid);
    if (cs != null)
      for (ConceptDefinitionComponent cd : cs.getConcept()) {
        writeConcept(table_id, 0, cd);
    }
  }

  private String readString(Type v) {
    return v == null ? null : v.primitiveValue();
  }

  private void writeConcept(String table_id, int i, ConceptDefinitionComponent cd) throws IOException {
    String status = readString(CodeSystemUtilities.readProperty(cd, "status"));
    tableCodes.line(table_id, cd.getId(), Integer.toString(i), cd.getCode(), cd.getDisplay(), status);
    for (ConceptDefinitionComponent child : cd.getConcept()) {
      writeConcept(table_id, i+1, child);
    }
  }

  private String readTableType(ConceptDefinitionComponent cs) {
    Type t = CodeSystemUtilities.readProperty(cs, "v2type");
    if (t  == null)
      return null;
    if (!t.isPrimitive()) 
      return null;
    String s = t.primitiveValue();
    if (Utilities.noString(s) || !Utilities.isInteger(s))
      return null;
    if (s.equals("undefined"))
      return "0";
    if (s.equals("User"))
      return "1";
    if (s.equals("HL7"))
      return "2";
    if (s.equals("no longer used"))
      return "4";
    if (s.equals("replaced"))
      return "5";
    if (s.equals("User Group /National Defined"))
      return "6";
    if (s.equals("Imported"))
      return "7";
    if (s.equals("Externally defined"))
      return "8"; 
    return s;
  }


  private String readGenerate(ConceptDefinitionComponent cs) {
    Type t = CodeSystemUtilities.readProperty(cs, "generate");
    if (t  == null)
      return null;
    if (!t.isPrimitive()) 
      return null;
    String s = t.primitiveValue();
    if (Utilities.noString(s) || !Utilities.isInteger(s))
      return null;
    else
      return s;
  }

  private String readOid(CodeSystem cs) {
    if (cs.hasIdentifier() && cs.getIdentifier().getSystem().equals("urn:ietf:rfc:3986")) 
      return cs.getIdentifier().getValue();
    else
      return null;
  }

  private String readExtension(CodeSystem cs, String url, int offset) {
    Extension ext = ToolingExtensions.getExtension(cs, url);
    if (ext == null)
      return null;
    if (!ext.hasValue())
      return null;
    if (!ext.getValue().isPrimitive()) 
      return null;
    String s = ext.getValue().primitiveValue();
    if (Utilities.noString(s) || offset == 0 || s.length() < offset)
      return s;
    else
      return s.substring(offset);
  }

  private String readProperty(Type t, int offset) {
    if (t == null)
      return null;
    if (!t.isPrimitive()) 
      return null;
    String s = t.primitiveValue();
    if (Utilities.noString(s) || offset == 0 || s.length() < offset)
      return s;
    else
      return s.substring(offset);
  }
}
