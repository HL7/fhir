package org.hl7.fhir.igtools.publisher;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

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

  private static final int BUFFER_SIZE = 1024;
  private String cacheFolder;
  
  public PackageCacheManager() throws IOException {
    cacheFolder = Utilities.path(System.getProperty("user.home"), ".fhir", "packages");
    if (!(new File(cacheFolder).exists()))
      Utilities.createDirectory(cacheFolder);
    if (!(new File(Utilities.path(cacheFolder, "resolution.ini")).exists()))
      TextFile.stringToFile("[urls]\r\n\r\n[local]\r\n\r\n", Utilities.path(cacheFolder, "resolution.ini"));  
  }

  public void recordMap(String url, String id) throws IOException {
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "resolution.ini"));
    ini.setStringProperty("urls", id, url, null);
    ini.save();
  }

  public String getPackageUrl(String id) throws IOException {
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "resolution.ini"));
    return ini.getStringProperty("urls", id); 
  }
  
  public String getPackageId(String url) throws IOException {
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "resolution.ini"));
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

  public String findPackageCache(String id) throws IOException {
    IniFile ini = new IniFile(Utilities.path(cacheFolder, "resolution.ini"));
    String s = ini.getStringProperty("local", id);
    if (!Utilities.noString(s))
      return s;
    
    String match = null;
    for (String f : sorted(new File(cacheFolder).list())) {
      if (f.startsWith(id+"-"))
        match = Utilities.path(cacheFolder, f, "package");
    }
    return match; 
  }
  
  
  public void addPackageToCache(String id, String version, InputStream tgz) throws IOException {
    String packRoot = Utilities.path(cacheFolder, id+"-"+version);
    Utilities.createDirectory(packRoot);
    Utilities.clearDirectory(packRoot);
    
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
        }
      }
    }  
  }
  
}
