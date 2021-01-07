package org.hl7.fhir.tools.publisher;

/*-
 * #%L
 * org.hl7.fhir.publisher.core
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JsonTrackingParser;
import org.hl7.fhir.utilities.npm.NpmPackage;
import org.hl7.fhir.utilities.npm.ToolsVersion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

/**
 * TODO: should we call versionless references ok? not sure....
 * (Grahame, 31-Oct 2019)
 * 
 * @author graha
 *
 */
public class SpecMapManager {

  private JsonObject spec;
  private JsonObject paths;
  private JsonObject pages;
  private JsonArray targets;
  private JsonArray images;
  private String base; // canonical, versionless
  private String base2; // versioned  
  private String name;
  private Set<String> targetSet = new HashSet<String>();
  private Set<String> imageSet = new HashSet<String>();
  private String version;
  private NpmPackage pi;
  private boolean fromSimplifier;

  public SpecMapManager(String npmName, String igVersion, String toolVersion, String buildId, Calendar genDate, String webUrl) {
    spec = new JsonObject();
    if (npmName != null)
      spec.addProperty("npm-name", npmName);
    spec.addProperty("ig-version", igVersion);
    spec.addProperty("tool-version", toolVersion);
    spec.addProperty("tool-build", buildId);
    spec.addProperty("webUrl", webUrl);
    if (genDate != null) {
      spec.addProperty("date", new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(genDate.getTime()));
      spec.addProperty("date-time", new SimpleDateFormat("yyyyMMddhhmmssZ", new Locale("en", "US")).format(genDate.getTime()));
    }
    paths = new JsonObject();
    spec.add("paths", paths);
    pages = new JsonObject();
    spec.add("pages", pages);
    targets = new JsonArray();
    spec.add("targets", targets);
    images = new JsonArray();
    spec.add("images", images);
  }

  public SpecMapManager(byte[] bytes, String version) throws JsonSyntaxException, IOException {
    this.version = version;
    spec = JsonTrackingParser.parseJson(bytes, true);
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

  public static SpecMapManager fromPackage(NpmPackage pi) throws JsonSyntaxException, IOException {
    return new SpecMapManager(TextFile.streamToBytes(pi.load("other", "spec.internals")), pi.fhirVersion());
  }

  public void path(String url, String path) {
    paths.addProperty(url, path);
  }

  public void save(String filename) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(spec);
    TextFile.stringToFile(json, filename);    
  }

  public String getVersion() throws FHIRException {
    if (spec.has("tool-version")) 
      return str(spec, "ig-version");
    else
      return version;
  }

  public String getNpmName() throws FHIRException {
    return strOpt(spec, "npm-name", "fhir");
  }
  
  public String getBuild() throws FHIRException {
    if (spec.has("tool-build")) 
      return str(spec, "tool-build");
    else
      return str(spec, "build");
  }

  /**
   * The official location of the IG, when built (= canonical URL in the build file)
   * 
   * @param url
   * @return
   * @throws Exception
   */
  public String getWebUrl(String url) throws Exception {
    return str(spec, "webUrl");
  }

  public String getPath(String url, String def) throws FHIRException {
    if (url == null) {
      return null;
    }
    if (paths.has(url)) {
      return strOpt(paths, url);      
    }
    String path = getSpecialPath(url);
    if (path != null) {
      return path;
    }
    if (def != null) {
      return def;
    }
    if (fromSimplifier) {
      return "https://simplifier.net/resolve?scope="+pi.name()+"@"+pi.version()+"&canonical="+url;
    }
    if (url.matches(Constants.URI_REGEX)) {
      int cc = 0;
      int cursor = url.length()-1;
      while (cursor > 0 && cc < 2) {
        if (url.charAt(cursor) == '/') {
          cc++;
        }
        cursor--;
      }
      String u = url.substring(cursor+2);
      return strOpt(paths, u);
      
    }
    
    return null;
  }

  // hack around things missing in spec.internals 
  private String getSpecialPath(String url) {
    if ("http://hl7.org/fhir/ValueSet/iso3166-1-3".equals(url)) {
      return Utilities.pathURL(base, "valueset-iso3166-1-3.html");
    }
    return null;
  }

  public List<String> getPathUrls() {
    List<String> res = new ArrayList<String>();
    for (Entry<String, JsonElement> e : paths.entrySet()) 
      res.add(e.getKey());
    return res;
  }

  public String getPage(String title) throws Exception {
    return strOpt(pages, title);
  }

  private String str(JsonObject obj, String name) throws FHIRException {
    if (!obj.has(name))
      throw new FHIRException("Property "+name+" not found");
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new FHIRException("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new FHIRException("Property "+name+" not a string");
    return p.getAsString();
  }

  private String strOpt(JsonObject obj, String name) throws FHIRException {
    if (!obj.has(name))
      return null;
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new FHIRException("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new FHIRException("Property "+name+" not a string");
    return p.getAsString();
  }

  private String strOpt(JsonObject obj, String name, String def) throws FHIRException {
    if (!obj.has(name))
      return def;
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new FHIRException("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new FHIRException("Property "+name+" not a string");
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

  public String getBase2() {
    return base2;
  }

  public void setBase2(String base2) {
    this.base2 = base2;
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
    if (hasTarget1(tgt))
      return true;
    else 
      return hasTarget2(tgt);
  }
  
  public boolean hasTarget1(String tgt) {
    if (tgt.startsWith(base+"/"))
      tgt = tgt.substring(base.length()+1);
    else if (tgt.startsWith(base))
      tgt = tgt.substring(base.length());
    else
      return false;
    if (tgt.contains("#"))
      tgt = tgt.substring(0, tgt.indexOf("#"));
    if (targetSet.contains(tgt))
      return true;
    if (Utilities.existsInList(tgt, "qa.html", "toc.html"))
      return true;
    if (targetSet.contains(tgt))
      return true;
    if (paths.has(base+"/"+tgt))
      return true;
    return false;  
  }

  public boolean hasTarget2(String tgt) {
    if (base2 == null)
      return false;
    
    if (tgt.startsWith(base2+"/"))
      tgt = tgt.substring(base2.length()+1);
    else if (tgt.startsWith(base2))
      tgt = tgt.substring(base2.length());
    else
      return false;
    if (tgt.contains("#"))
      tgt = tgt.substring(0, tgt.indexOf("#"));
    if (targetSet.contains(tgt))
      return true;
    if (Utilities.existsInList(tgt, "qa.html", "toc.html"))
      return true;
    if (targetSet.contains(tgt))
      return true;
    if (paths.has(base2+"/"+tgt))
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

  public Set<String> getTargets() {
    return targetSet;
  }

  public Set<String> getImages() {
    return imageSet;
  }

  public List<String> getPages() {
    List<String> res = new ArrayList<String>();
    for (Entry<String, JsonElement> e : pages.entrySet()) 
      res.add(e.getKey());
    return res;
  }

  public static SpecMapManager createForSimplifier(NpmPackage pi) {
    SpecMapManager res = new SpecMapManager(pi.name(), pi.fhirVersion(), ToolsVersion.TOOLS_VERSION_STR, null, null, pi.url());
    res.fromSimplifier = true;
    res.pi = pi;
    return res;
  }


}
