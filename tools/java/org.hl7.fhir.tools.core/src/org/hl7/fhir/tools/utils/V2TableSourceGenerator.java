package org.hl7.fhir.tools.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.tools.publisher.BuildWorkerContext.Concept;
import org.hl7.fhir.tools.utils.V2TableSourceGenerator.Table;
import org.hl7.fhir.tools.utils.V2TableSourceGenerator.TableVersion;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLWriter;

/**
 * This unit take the v2 access database, and generates source for the FHIR build tool
 * 
 * @author Grahame Grieve
 *
 */
public class V2TableSourceGenerator {

  public class TableEntry {
    private String code;
    private String display;
    private String german;
    public boolean hasGerman() {
      return !Utilities.noString(german);
    }
  }
  
  public class TableVersion {
    private String name;
    private List<TableEntry> entries = new ArrayList<TableEntry>();
    public TableVersion(String name) {
      this.name = name;
    }
    public boolean hasGerman() {
      for (TableEntry v : entries)
        if (v.hasGerman())
          return true;
      return false;
    }
  }

  public class Table {
    private String id;
    private Map<String, TableVersion> versions = new HashMap<String, TableVersion>();
    public Table(String tableid) {
      id = tableid;
    }
    public void item(String version, String code, String display, String german, String table_name) {
      if (!versions.containsKey(version))
        versions.put(version, new TableVersion(table_name));
      TableEntry entry = new TableEntry();
      entry.code = code;
      entry.display = display;
      entry.german = german;
      versions.get(version).entries.add(entry);
    }
    public boolean hasGerman() {
      for (TableVersion v : versions.values())
        if (v.hasGerman())
          return true;
      return false;
    }    
  }

  /**
   * two arguments:
   * - ms access database
   * - destination of source file
   * @param args
   * @throws SQLException 
   * @throws ClassNotFoundException 
   * @throws IOException 
   */
  public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
    
    V2TableSourceGenerator self = new V2TableSourceGenerator(args[0], args[1]);
    self.execute();
  }

  
  private String source;
  private String dest;
  private Connection db;
  private Map<String, Table> tables = new HashMap<String, Table>();
  private Set<String> versions = new HashSet<String>();
      
  public V2TableSourceGenerator(String source, String dest) {
    this.source = source;
    this.dest = dest;
  }

  private void execute() throws ClassNotFoundException, SQLException, IOException {
    connect();
    loadTables();
    saveTables();
  }

  private void connect() throws ClassNotFoundException, SQLException {    
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+source;
    db = DriverManager.getConnection(url,"","");
  }

  private void loadTables() throws SQLException {
    String sql = "SELECT HL7TableValues.table_id, hl7_version, table_value, HL7TableValues.display_name, HL7TableValues.interpretation, Hl7Tables.display_name as table_name FROM HL7TableValues, HL7Versions, HL7Tables where HL7Versions.version_id = HL7TableValues.version_id and HL7Tables.table_id = HL7TableValues.table_id and HL7Versions.version_id = HL7Tables.version_id order by HL7TableValues.table_Id, HL7_version, HL7TableValues.sort_no";
    Statement stmt = db.createStatement();
    ResultSet query = stmt.executeQuery(sql);
    int i = 0;
    while (query.next()) {
      String tableid = query.getString("table_id");
      String version = query.getString("hl7_version");
      if (!tables.containsKey(tableid))
        tables.put(tableid, new Table(tableid));
      versions.add(version);
      tables.get(tableid).item(version, query.getString("table_value"), query.getString("display_name"), query.getString("interpretation"), query.getString("table_name"));
      i++;
    }
    System.out.println(Integer.toString(i)+" entries loaded");
  }

  private void saveTables() throws IOException {
    save(dest, false);
    save(Utilities.changeFileExt(dest, "_de.xml"), true);
  }

  private void save(String filename, boolean german) throws UnsupportedEncodingException, FileNotFoundException, IOException {
    String ns = "http://hl7.org/fhir/dev";
    XMLWriter xml = new XMLWriter(new FileOutputStream(filename), "UTF-8");
    xml.setPretty(true);
    xml.start();
    xml.attribute("xmlns", ns);
    xml.enter("tables");
    List<Integer> tl = new ArrayList<Integer>();
    for (String c : tables.keySet())
      tl.add(Integer.parseInt(c));
    Collections.sort(tl);
    for (Integer c : tl) {
      String id = c.toString();
      xml.attribute("id", id);
      Table table = tables.get(id);
      xml.enter("table");
      List<String> vl = new ArrayList<String>();
      vl.addAll(table.versions.keySet());
      Collections.sort(vl);
      for (String v : vl) {
        TableVersion tv = table.versions.get(v);
        xml.attribute("version", v);
        xml.attribute("desc", tv.name);
        xml.enter("version");
        for (TableEntry te : tv.entries) {
          xml.attribute("code", te.code);
          xml.attribute("desc", te.display);
          if (german && te.hasGerman()) {
            xml.enter("item");
            xml.attribute("lang", "de");
            xml.attribute("value", te.german);
            xml.element("desc");
            xml.exit("item");
          } else
            xml.element("item");
        }
        xml.exit("version");
      }
      xml.exit("table");
    }
    xml.exit("tables");
    xml.end();
    xml.close();
  }

}
