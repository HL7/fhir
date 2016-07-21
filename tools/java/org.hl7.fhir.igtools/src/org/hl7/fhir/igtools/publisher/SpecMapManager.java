package org.hl7.fhir.igtools.publisher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.utilities.TextFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

public class SpecMapManager {

  private JsonObject spec;
  private JsonObject paths;
  private JsonObject pages;
  private String base;

  public SpecMapManager(String version, String svnRevision, Calendar genDate) {
    spec = new JsonObject();
    spec.addProperty("version", version);
    spec.addProperty("build", svnRevision);
    spec.addProperty("date", new SimpleDateFormat("yyyy-MM-dd").format(genDate.getTime()));
    paths = new JsonObject();
    spec.add("paths", paths);
    pages = new JsonObject();
    spec.add("pages", pages);
  }

  public SpecMapManager(byte[] bytes) throws JsonSyntaxException, IOException {
    spec = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.bytesToString(bytes));
    paths = spec.getAsJsonObject("paths");
    pages = spec.getAsJsonObject("pages");
  }

  public void path(String url, String path) {
    paths.addProperty(url, path);
  }

  public void save(String filename) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(spec);
    TextFile.stringToFile(json, filename);    
  }

  public String getVersion() throws Exception {
    return str(spec, "version");
  }

  public String getPath(String url) throws Exception {
    return strOpt(paths, url);
  }

  public String getPage(String title) throws Exception {
    return strOpt(pages, title);
  }

  private String str(JsonObject obj, String name) throws Exception {
    if (!obj.has(name))
      throw new Exception("Property "+name+" not found");
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new Exception("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new Exception("Property "+name+" not a string");
    return p.getAsString();
  }

  private String strOpt(JsonObject obj, String name) throws Exception {
    if (!obj.has(name))
      return null;
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new Exception("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new Exception("Property "+name+" not a string");
    return p.getAsString();
  }

  public void page(String title, String url) {
    pages.addProperty(title, url);    
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  
}
