package org.hl7.fhir.utilities.cache;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Package cache manager
 * 
 * API:
 * 
 * constructor
 *  getPackageUrl
 *  getPackageId
 *  findPackageCache 
 *  addPackageToCache  
 * 
 * @author Grahame Grieve
 *
 */
public class PackageCacheManager {

  public static final String PACKAGE_REGEX = "^[a-z][a-z0-9\\_]*(\\.[a-z0-9\\_]+)+$";
  public static final String PACKAGE_VERSION_REGEX = "^[a-z][a-z0-9\\_]*(\\.[a-z0-9\\_]+)+\\-[a-z0-9\\-]+(\\.[a-z0-9\\-]+)*$";

  private static final int BUFFER_SIZE = 1024;
  private static final String CACHE_VERSION = "1"; // first version
  private static final int ANALYSIS_VERSION = 2;

  private String cacheFolder;
  
  public PackageCacheManager(boolean userMode) throws IOException {
    if (userMode)
      cacheFolder = Utilities.path(System.getProperty("user.home"), ".fhir", "packages");
    else
      cacheFolder = Utilities.path("var", "lib", ".fhir", "packages");
    if (!(new File(cacheFolder).exists()))
      Utilities.createDirectory(cacheFolder);
    if (!(new File(Utilities.path(cacheFolder, "packages.ini")).exists()))
      TextFile.stringToFile("[urls]\r\n\r\n[local]\r\n\r\n", Utilities.path(cacheFolder, "packages.ini"));  
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "packages.ini"));
    boolean save = false;
    if (!CACHE_VERSION.equals(ini.getStringProperty("cache", "version"))) {
      clearCache();
      ini.setStringProperty("cache", "version", CACHE_VERSION, null);
      save = true;
    }
    save = checkIniHasMapping("fhir.argonaut.ehr", "http://fhir.org/guides/argonaut", ini) || save;
    save = checkIniHasMapping("fhir.argonaut.pd", "http://fhir.org/guides/argonaut-pd", ini) || save;
    save = checkIniHasMapping("fhir.argonaut.scheduling", "http://fhir.org/guides/argonaut-scheduling", ini) || save;
    save = checkIniHasMapping("fhir.hspc.acog", "http://hl7.org/fhir/fpar", ini) || save;
    save = checkIniHasMapping("hl7.fhir.au.argonaut", "http://hl7.org.au/fhir/argonaut", ini) || save;
    save = checkIniHasMapping("hl7.fhir.au.base", "http://hl7.org.au/fhir", ini) || save;
    save = checkIniHasMapping("hl7.fhir.au.pd", "http://hl7.org.au/fhir", ini) || save;
    save = checkIniHasMapping("hl7.fhir.smart", "http://hl7.org/fhir/smart-app-launch", ini) || save;
    save = checkIniHasMapping("hl7.fhir.snomed", "http://hl7.org/fhir/ig/snomed", ini) || save;
    save = checkIniHasMapping("hl7.fhir.test.v10", "http://hl7.org/fhir/test-ig-10", ini) || save;
    save = checkIniHasMapping("hl7.fhir.test.v30", "http://hl7.org/fhir/test", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.breastcancer", "http://hl7.org/fhir/us/breastcancer", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.ccda", "http://hl7.org/fhir/us/ccda", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.cds.opioids", "http://hl7.org/fhir/ig/opioid-cds", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.core", "http://hl7.org/fhir/us/core", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.dafresearch", "http://hl7.org/fhir/us/daf-research", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.ecr", "http://fhir.hl7.org/us/ecr", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.hai", "http://hl7.org/fhir/us/hai", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.hedis", "http://hl7.org/fhir/us/hedis", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.meds", "http://hl7.org/fhir/us/meds", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.qicore", "http://hl7.org/fhir/us/qicore", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.sdc", "http://hl7.org/fhir/us/sdc", ini) || save;
    save = checkIniHasMapping("hl7.fhir.us.sdcde", "http://hl7.org/fhir/us/sdcde", ini) || save;
    save = checkIniHasMapping("hl7.fhir.uv.genomicsreporting", "http://hl7.org/fhir/uv/genomics-reporting", ini) || save;
    save = checkIniHasMapping("hl7.fhir.uv.immds", "http://hl7.org/fhir/uv/cdsi", ini) || save;
    save = checkIniHasMapping("hl7.fhir.uv.ips", "http://hl7.org/fhir/uv/ips", ini) || save;
    save = checkIniHasMapping("hl7.fhir.uv.phd", "http://hl7.org/fhir/devices", ini) || save;
    save = checkIniHasMapping("hl7.fhir.uv.vhdir", "http://hl7.org/fhir/ig/vhdir", ini) || save;
    save = checkIniHasMapping("hl7.fhir.vn.base", "http://hl7.org/fhir/ig/vietnam", ini) || save;
    save = checkIniHasMapping("hl7.fhir.vocabpoc", "http://hl7.org/fhir/ig/vocab-poc", ini) || save;
    ini.save();    
  }
  

  private boolean checkIniHasMapping(String pid, String curl, IniFile ini) {
    if (curl.equals(ini.getStringProperty("urls", pid)))
      return false;
    ini.setStringProperty("urls", pid, curl, null);
    return true;
  }


  private void clearCache() throws IOException {
    for (File f : new File(cacheFolder).listFiles()) {
      if (f.isDirectory())
        FileUtils.deleteDirectory(f);
      else if (!f.getName().equals("packages.ini"))
        FileUtils.forceDelete(f);
    }    
  }


  public void recordMap(String url, String id) throws IOException {
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "packages.ini"));
    ini.setStringProperty("urls", id, url, null);
    ini.save();
  }

  public String getPackageUrl(String id) throws IOException {
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "packages.ini"));
    return ini.getStringProperty("urls", id); 
  }
  
  public String getPackageId(String url) throws IOException {
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "packages.ini"));
    String[] ids = ini.getPropertyNames("urls");
    if (ids != null) {
      for (String id : ids) {
        if (url.equals(ini.getStringProperty("urls", id)))
          return id;
      }
    }
    return null;
  }
  
  private List<String> sorted(String[] keys) {
    List<String> names = new ArrayList<String>();
    for (String s : keys)
      names.add(s);
    Collections.sort(names);
    return names;
  }

  public NpmPackage loadPackageCacheLatest(String id) throws IOException {
    String match = null;
    List<String> l = sorted(new File(cacheFolder).list());
    for (int i = l.size()-1; i >= 0; i--) {
      String f = l.get(i);
      if (f.startsWith(id+"-")) {
        return loadPackageInfo(Utilities.path(cacheFolder, f)); 
      }
    }
    return null;    
  }
  
  public NpmPackage loadPackageCache(String id, String version) throws IOException {    
    String match = null;
    for (String f : sorted(new File(cacheFolder).list())) {
      if (f.equals(id+"-"+version)) {
        return loadPackageInfo(Utilities.path(cacheFolder, f)); 
      }
    }
    return null;
  }
  
  private NpmPackage loadPackageInfo(String path) throws IOException {
    NpmPackage pi = new NpmPackage(path);
    return pi;
  }

  public NpmPackage addPackageToCache(String id, String version, InputStream tgz) throws IOException {
    String packRoot = Utilities.path(cacheFolder, id+"-"+version);
    Utilities.createDirectory(packRoot);
    Utilities.clearDirectory(packRoot);
    
    long size = 0;
    GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(tgz);
    try (TarArchiveInputStream tarIn = new TarArchiveInputStream(gzipIn)) {
      TarArchiveEntry entry;

      while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {
        if (entry.isDirectory()) {
          File f = new File(Utilities.path(packRoot, entry.getName()));
          if (!f.mkdir()) 
            throw new IOException("Unable to create directory '%s', during extraction of archive contents: "+ f.getAbsolutePath());
        } else {
          int count;
          byte data[] = new byte[BUFFER_SIZE];
          String fn = Utilities.path(packRoot, entry.getName());
          String dir = Utilities.getDirectoryForFile(fn);
          if (!(new File(dir).exists()))
            Utilities.createDirectory(dir);
          
          FileOutputStream fos = new FileOutputStream(fn, false);
          try (BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER_SIZE)) {
            while ((count = tarIn.read(data, 0, BUFFER_SIZE)) != -1) {
              dest.write(data, 0, count);
            }
          }
          fos.close();
          size = size + new File(fn).length();
        }
      }
    }  
    JsonObject npm = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(Utilities.path(packRoot, "package", "package.json")));
    Map<String, String> profiles = new HashMap<String, String>(); 
    Map<String, String> canonicals = new HashMap<String, String>(); 
    analysePackage(packRoot, npm.getAsJsonObject("dependencies").get("hl7.fhir.core").getAsString(), profiles, canonicals);
    IniFile ini = new IniFile(Utilities.path(packRoot, "cache.ini"));
    ini.setTimeStampFormat("dd/MM/yyyy h:mm:ss a");
    ini.setLongProperty("Package", "size", size, null);
    ini.setTimestampProperty("Package", "install", Timestamp.from(Instant.now()), null);
    for (String p : profiles.keySet()) 
      ini.setStringProperty("Profiles", p, profiles.get(p), null);
    for (String p : canonicals.keySet()) 
      ini.setStringProperty("Canonicals", p, canonicals.get(p), null);
    ini.setIntegerProperty("Packages", "analysis", ANALYSIS_VERSION, null);
    return loadPackageInfo(packRoot);
  }

  private void analysePackage(String dir, String v, Map<String, String> profiles, Map<String, String> canonicals) throws IOException {
    for (File f : new File(Utilities.path(dir, "package")).listFiles()) {
      try {
        JsonObject j = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(f));
        if (!Utilities.noString(j.get("url").getAsString()) && !Utilities.noString(j.get("resourceType").getAsString())) 
          canonicals.put(j.get("url").getAsString(), f.getName());
        if ("StructureDefinition".equals(j.get("resourceType").getAsString()) && "resource".equals(j.get("kind").getAsString())) {
          String bd = null;
          if ("1.0.2".equals(v)) 
            bd = j.get("constrainedType").getAsString();
          else
            bd = j.get("type").getAsString();
        if (Utilities.noString(bd))
            bd = j.get("name").getAsString();
        if (!"Extension".equals(bd))
          profiles.put(j.get("url").getAsString(), bd);
        }
      } catch (Exception e) {
        // nothing
      }
    }
  }


  public NpmPackage addPackageToCache(InputStream tgz) throws IOException {
    NpmPackage pi = addPackageToCache("temp", "temp", tgz);
    String actual = Utilities.path(cacheFolder, pi.getNpm().get("name").getAsString()+"-"+pi.getNpm().get("version").getAsString());
    File dst = new File(actual);
    int i = 0;
    while (!(new File(pi.getPath()).renameTo(dst))) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      i++;
      if (i == 10)
        throw new IOException("Unable to rename from "+pi.getPath()+" to "+actual);        
    }
    pi.setPath(actual);
    return pi;
  }


  public NpmPackage extractLocally(String filename) {
    throw new Error("Not done yet");
  }

  public NpmPackage extractLocally(InputStream src) {
    throw new Error("Not done yet");
  }

  public String getFolder() {
    return cacheFolder;
  }
  
}
