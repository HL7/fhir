package org.hl7.fhir.igtools.spreadsheets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class TabDelimitedSpreadSheet {

  private StringBuilder cnt = new StringBuilder();
  private String filename;
  private List<String> columns = new ArrayList<String>();
  private int row;
  private int col;
  private boolean wantSave;
  
  public void setFileName(String srcName, String filename) {
    File src = new File(srcName);
    File tgt = new File(filename);
    this.wantSave = !tgt.exists() || src.lastModified() > tgt.lastModified(); 
    this.filename = filename;
  }

  public void sheet(String name) {
    cnt.append("\r\n=== Sheet "+name+" =====================================\r\n");
    columns.clear();
    col = 0;
    row = 0;
  }

  public void close() throws Exception {
    if (wantSave)
      TextFile.stringToFile(cnt.toString(), filename);
  }

  public void column(String v) throws Exception {
    if (col > 0)
      throw new Error("Logic Error");
    columns.add(v);  
  }
  
  public void row() {
    row++;
    cnt.append("\r\n -- Row "+Integer.toString(row)+" -----------------------------------\r\n");
    col = 0;
  }

  public void cell(String v) throws Exception {
    if (!Utilities.noString(v))
      cnt.append("  "+columns.get(col)+" = "+Utilities.escapeJson(v)+"\r\n");
    col++;
  }

  public boolean hasColumn(String colName) {
    return columns.contains(colName);
  }

}
