package org.hl7.fhir.igtools.publisher;

import java.io.IOException;
import java.util.Calendar;

import org.hl7.fhir.utilities.TextFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class SpecMapManager {

  private JsonObject spec;
  private JsonObject paths;
  private JsonObject maps;

  public SpecMapManager(String version, String svnRevision, Calendar genDate) {
    spec = new JsonObject();
    spec.addProperty("version", version);
    spec.addProperty("build", svnRevision);
    spec.addProperty("date", genDate.toString());
    paths = new JsonObject();
    spec.add("paths", paths);
    maps = new JsonObject();
    spec.add("maps", maps);      
  }

  public void path(String url, String path) {
    paths.addProperty(url, path);
  }

  public void map(String url, String details) {
    maps.addProperty(url, details);    
  }

  public void save(String filename) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(spec);
    TextFile.stringToFile(json, filename);    
  }

}
