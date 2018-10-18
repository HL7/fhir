package org.hl7.fhir.igtools.templates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageCacheManager;

public class TemplateManager {

  private PackageCacheManager pcm;

  public TemplateManager(PackageCacheManager pcm) {
    this.pcm = pcm;
  }

  public NpmPackage fetch(String src) throws IOException {
    if (src.startsWith("https://github.com")) {
      URL url = new URL(Utilities.pathURL(src, "archive", "master.zip"));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      InputStream zip = connection.getInputStream();
      return NpmPackage.fromZip(zip, true); 
    } else {
      File f = new File(src);
      if (f.exists() && f.isDirectory())    
        return loadLiteralPath(src);
      else
        throw new IOException("Unable ti interpret template source '"+src+"'");
    }
  }

  private InputStream fetchUrl(String pathURL) {
    // TODO Auto-generated method stub
    return null;
  }

  private NpmPackage loadLiteralPath(String path) throws IOException {
    NpmPackage pi = new NpmPackage(path);
    return pi;
  }
  
}
