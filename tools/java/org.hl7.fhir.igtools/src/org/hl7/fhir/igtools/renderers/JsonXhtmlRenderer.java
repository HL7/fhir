package org.hl7.fhir.igtools.renderers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r5.formats.JsonCreator;
import org.hl7.fhir.r5.utils.TranslatingUtilities;
import org.hl7.fhir.utilities.Utilities;

public class JsonXhtmlRenderer extends TranslatingUtilities implements JsonCreator {

  StringBuilder b;
  
  private String indent = "  ";
  
  private class LevelInfo {
    private boolean started;
    private boolean isArr;
    public LevelInfo(boolean isArr) {
      super();
      this.started = false;
      this.isArr = isArr;
    }
  }
  private List<LevelInfo> levels = new ArrayList<LevelInfo>();
  private String href;
  
  @Override
  public void setIndent(String indent) {
    this.indent = indent;
  }
  
  @Override
  public void beginObject() throws IOException {
    checkInArray();
    if (b == null) {
      b = new StringBuilder();
      b.append("<pre class=\"json\">\r\n");
    }
    levels.add(0, new LevelInfo(false));
    b.append("{\r\n");
    for (int i = 0; i < levels.size(); i++) 
      b.append(indent);
  }


  @Override
  public void endObject() throws IOException {
    levels.remove(0);
    b.append("\r\n");
    for (int i = 0; i < levels.size(); i++) 
      b.append(indent);
    b.append("}");
  }

  @Override
  public void name(String name) throws IOException {
    if (levels.get(0).isArr)
      throw new IOException("Error producing JSON: attempt to use name in an array");
    
    if (levels.get(0).started) {
      b.append(",\r\n");
      for (int i = 0; i < levels.size(); i++) 
        b.append(indent);
    } else
      levels.get(0).started = true;
    b.append('"');
    if (href != null)
      b.append("<a href=\""+href+"\">");
    b.append(Utilities.escapeXml(name));
    if (href != null)
      b.append("</a>");
    b.append('"');
    b.append(" : ");
    href = null;
  }


  @Override
  public void nullValue() throws IOException {
    checkInArray();
    b.append("null");
  }

  private void checkInArray() {
    if (levels.size() > 0 && levels.get(0).isArr) {
      if (levels.get(0).started) {
        b.append(",\r\n");
        for (int i = 0; i < levels.size(); i++) 
          b.append(indent);
      } else
        levels.get(0).started = true;
    }
  }

  @Override
  public void value(String value) throws IOException {
    checkInArray();
    b.append('"');
    b.append(Utilities.escapeXml(Utilities.escapeJson(value)));
    b.append('"');
  }

  @Override
  public void value(Boolean value) throws IOException {
    checkInArray();
    b.append(value ? translate("json", "true") : translate("json", "false"));
  }

  @Override
  public void value(BigDecimal value) throws IOException {
    checkInArray();
    b.append(value.toString());
  }

  @Override
  public void valueNum(String value) throws IOException {
    checkInArray();
    b.append(value);
  }

  @Override
  public void value(Integer value) throws IOException {
    checkInArray();
    b.append(value.toString());
  }

  @Override
  public void beginArray() throws IOException {
    checkInArray();
    levels.add(0, new LevelInfo(true));
    b.append("[\r\n");
    for (int i = 0; i < levels.size(); i++) 
      b.append(indent);

  }

  @Override
  public void endArray() throws IOException {
    levels.remove(0);
    b.append("\r\n");
    for (int i = 0; i < levels.size(); i++) 
      b.append(indent);
    b.append("]");
  }

  @Override
  public void finish() throws IOException {
    b.append("</pre>\r\n");
  }

  @Override
  public String toString() {
    return b.toString();
  }

  @Override
  public void link(String href) {
    this.href = href;
  }
  
}
