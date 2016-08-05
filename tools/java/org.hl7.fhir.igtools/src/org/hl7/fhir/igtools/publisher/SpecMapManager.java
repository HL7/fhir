package org.hl7.fhir.igtools.publisher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

public class SpecMapManager {

  private JsonObject spec;
  private JsonObject paths;
  private JsonObject pages;
  private JsonArray targets;
  private JsonArray images;
  private String base;
  private String name;
  private Set<String> targetSet = new HashSet<String>();
  private Set<String> imageSet = new HashSet<String>();

  public SpecMapManager(String version, String svnRevision, Calendar genDate) {
    spec = new JsonObject();
    spec.addProperty("version", version);
    spec.addProperty("build", svnRevision);
    spec.addProperty("date", new SimpleDateFormat("yyyy-MM-dd").format(genDate.getTime()));
    paths = new JsonObject();
    spec.add("paths", paths);
    pages = new JsonObject();
    spec.add("pages", pages);
    targets = new JsonArray();
    spec.add("targets", targets);
    images = new JsonArray();
    spec.add("images", images);
  }

  public SpecMapManager(byte[] bytes) throws JsonSyntaxException, IOException {
    spec = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.bytesToString(bytes));
    paths = spec.getAsJsonObject("paths");
    pages = spec.getAsJsonObject("pages");
    targets = spec.getAsJsonArray("targets");
    images = spec.getAsJsonArray("images");
    if (targets != null)
      for (JsonElement e : targets) {
        targetSet.add(((JsonPrimitive) e).getAsString());
    }
    if (images != null)
      for (JsonElement e : images) {
        imageSet.add(((JsonPrimitive) e).getAsString());
    }
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

  public void target(String tgt) {
    if (!targetSet.contains(tgt)) {
      targetSet.add(tgt);
      targets.add(new JsonPrimitive(tgt));
    }
  }
  
  public void image(String tgt) {
    if (!imageSet.contains(tgt)) {
      imageSet.add(tgt);
      images.add(new JsonPrimitive(tgt));
    }
  }
  
  public boolean hasTarget(String tgt) {
    if (tgt.startsWith(base+"/"))
      tgt = tgt.substring(base.length()+1);
    else if (tgt.startsWith(base))
      tgt = tgt.substring(base.length());
    else
      return false;
    if (targetSet.contains(tgt))
      return true;
    if (Utilities.existsInList(tgt, "qa.html", "toc.html"))
      return true;
    if (tgt.contains("#"))
      tgt = tgt.substring(0, tgt.indexOf("#"));
    if (targetSet.contains(tgt))
      return true;
    return false;  
  }

  public boolean hasImage(String tgt) {
    if (tgt.startsWith(base+"/"))
      tgt = tgt.substring(base.length()+1);
    else if (tgt.startsWith(base))
      tgt = tgt.substring(base.length());
    else
      return false;
    if (imageSet.contains(tgt))
      return true;
    return false;  
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
