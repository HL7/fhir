package org.hl7.fhir.igtools.publisher.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.JSONUtil;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class IGRegistryMaintainer {

  public class PublicationEntry {
    private String name;
    private String version;
    private String fhirVersion;
    private String path;
    
    public PublicationEntry(String name, String version, String fhirVersion, String path) {
      super();
      this.name = name;
      this.version = version;
      this.fhirVersion = fhirVersion;
      this.path = path;
    }
    
    public String getName() {
      return name;
    }
    
    public String getVersion() {
      return version;
    }
    
    public String getFhirVersion() {
      return fhirVersion;
    }
    
    public String getPath() {
      return path;
    }
  }
  
  public class ImplementationGuideEntry {
    private String packageId;
    private String canonical;
    private String title;
    private String cibuild;
    private List<PublicationEntry> releases = new ArrayList<>();
    private List<PublicationEntry> candidates = new ArrayList<>();
    
    public ImplementationGuideEntry(String packageId, String canonical, String title) {
      super();
      this.packageId = packageId;
      this.canonical = canonical;
      this.title = title;
    }

    public String getPackageId() {
      return packageId;
    }

    public String getCanonical() {
      return canonical;
    }

    public String getTitle() {
      return title;
    }

    public List<PublicationEntry> getReleases() {
      return releases;
    }

    public List<PublicationEntry> getCandidates() {
      return candidates;
    }

  }

  private String path;
  private List<ImplementationGuideEntry> igs = new ArrayList<>();

  public IGRegistryMaintainer(String path) {
    this.path = path;
  }

  public ImplementationGuideEntry seeIg(String packageId, String canonical, String title) {
    ImplementationGuideEntry ig = new ImplementationGuideEntry(packageId, canonical, title);
    igs.add(ig);
    return ig;
  }

  public void seeCiBuild(ImplementationGuideEntry ig, String path) {
    ig.cibuild = path;
  }

  public void seeRelease(ImplementationGuideEntry ig, String name, String version, String fhirVersion, String path) {
    PublicationEntry p = new PublicationEntry(name, version, fhirVersion, path);
    ig.releases.add(p);
  }

  public void seeCandidate(ImplementationGuideEntry ig, String name, String version, String fhirVersion, String path) {
    PublicationEntry p = new PublicationEntry(name, version, fhirVersion, path);
    ig.candidates.add(p);
  }

  public void finish() throws JsonSyntaxException, FileNotFoundException, IOException {
    if (path != null) {
      // load the file
      JsonObject json = (JsonObject) new JsonParser().parse(TextFile.fileToString(path)); // use gson parser to preseve property order
      for (ImplementationGuideEntry ig : igs) {
        JsonObject e = JSONUtil.findByStringProp(json.getAsJsonArray("guides"), "npm-name", ig.packageId);
        if (e == null) {
          e = new JsonObject();
          json.getAsJsonArray("guides").add(e);
          e.addProperty("name", ig.title);
          e.addProperty("category", "??");
          e.addProperty("npm-name", ig.packageId);
          e.addProperty("description", "??");
          e.addProperty("authority", getAuthority(ig.canonical));
          e.addProperty("country", getCountry(ig.canonical));
          e.addProperty("history", getHistoryPage(ig.canonical));
          e.addProperty("canonical", ig.canonical);
          e.addProperty("ci-build", ig.cibuild);
        } else {
          if (!e.has("ci-build") || e.get("ci-build").equals(ig.cibuild)) {
            e.remove("ci-build");
            e.addProperty("ci-build", ig.cibuild);
          }
          if (!e.has("canonical") || e.get("canonical").equals(ig.canonical)) {
            e.remove("canonical");
            e.addProperty("canonical", ig.canonical);
          }
        }
        if (e.has("editions"))
          e.remove("editions");
        JsonArray a = new JsonArray();
        e.add("editions", a);
        if (!ig.getCandidates().isEmpty()) {
          PublicationEntry p = ig.getCandidates().get(0);
          a.add(makeEdition(p));
        }
        for (PublicationEntry p : ig.getReleases()) {
          a.add(makeEdition(p));
        }
      }
      TextFile.stringToFile(new GsonBuilder().setPrettyPrinting().create().toJson(json), path, false);
    }
    for (ImplementationGuideEntry ig : igs) {
      System.out.println(ig.packageId+" ("+ig.canonical+"): "+ig.title+" @ "+ig.cibuild);
      for (PublicationEntry p : ig.getReleases()) {
        System.out.println("  release: "+p.name+" "+p.version+"/"+p.fhirVersion+" @ "+p.path);
      }
      if (!ig.getCandidates().isEmpty()) {
        PublicationEntry p = ig.getCandidates().get(0);
        System.out.println("  candidate: "+p.name+" "+p.version+"/"+p.fhirVersion+" @ "+p.path);
      }
    }    
  }
  
  private JsonObject makeEdition(PublicationEntry p) {
    JsonObject e = new JsonObject();
    e.addProperty("name", p.getName());
    e.addProperty("ig-version", p.getVersion());
    e.addProperty("fhir-version", p.getFhirVersion());
    e.addProperty("url", p.getPath());
    return e;
  }

  private String getHistoryPage(String canonical) {
    if (canonical.contains("hl7.org"))
      return Utilities.pathURL(canonical, "history.cfml");
    if (canonical.contains("fhir.org"))
      return Utilities.pathURL(canonical, "history.shtml");
    return Utilities.pathURL(canonical, "history.html");
  }

  private String getCountry(String canonical) {
    if (canonical.contains("hl7.org")) {
      if (canonical.contains("/uv/"))
        return "uv";
      if (canonical.contains("/us/"))
        return "us";
    }
    return "??";
  }

  private String getAuthority(String canonical) {
    if (canonical.contains("hl7.org"))
      return "HL7";
    return "??";
  }

}
