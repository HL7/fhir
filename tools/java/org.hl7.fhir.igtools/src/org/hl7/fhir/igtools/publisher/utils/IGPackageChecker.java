package org.hl7.fhir.igtools.publisher.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.SPDXLicense;
import org.hl7.fhir.r5.utils.NPMPackageGenerator;
import org.hl7.fhir.r5.utils.NPMPackageGenerator.Category;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageGenerator.PackageType;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class IGPackageChecker {

  private String folder;
  private String canonical;
  private String vpath;
  private String packageId;

  public IGPackageChecker(String folder, String canonical, String vpath, String packageId) {
    this.folder = folder; 
    this.canonical = canonical;
    this.vpath = vpath;
    this.packageId = packageId;
   }

  public void check(String ver, String fhirversion, String name, String date) throws IOException, FHIRException {
    String pf = Utilities.path(folder, "package.tgz");
    File f = new File(pf);
    if (!f.exists()) {
      makePackage(pf, name, ver, fhirversion, date);
    } else {
      NpmPackage pck = NpmPackage.fromPackage(new FileInputStream(f));
      if (!pck.version().equals(ver)) {
        f.renameTo(new File(Utilities.changeFileExt(pf, ".tgz-old")));
        TarArchiveOutputStream tar;
        ByteArrayOutputStream OutputStream;
        BufferedOutputStream bufferedOutputStream;
        GzipCompressorOutputStream gzipOutputStream;

        OutputStream = new ByteArrayOutputStream();
        bufferedOutputStream = new BufferedOutputStream(OutputStream);
        gzipOutputStream = new GzipCompressorOutputStream(bufferedOutputStream);
        tar = new TarArchiveOutputStream(gzipOutputStream);

        for (String s : pck.getContent().keySet()) {
          byte[] b = pck.getContent().get(s);
          if (s.equals("package/package.json")) {
            JsonObject json = JsonTrackingParser.parseJson(b);
            json.remove("version");
            json.addProperty("version", ver);
            b = TextFile.stringToBytes(new GsonBuilder().setPrettyPrinting().create().toJson(json), false);
          }
          TarArchiveEntry entry = new TarArchiveEntry(s);
          entry.setSize(b.length);
          tar.putArchiveEntry(entry);
          tar.write(b);
          tar.closeArchiveEntry();
        }
        tar.finish();
        tar.close();
        gzipOutputStream.close();
        bufferedOutputStream.close();
        OutputStream.close();
        TextFile.bytesToFile(OutputStream.toByteArray(), pf);
      }
    }
    
  }

  private void makePackage(String file, String name, String ver, String fhirversion, String date) throws FHIRException, IOException {
    ImplementationGuide ig = new ImplementationGuide();
    ig.setUrl(Utilities.pathURL(canonical, "ImplementationGuide", "ig"));
    ig.setName(name);
    ig.setTitle(Utilities.titleize(name));
    ig.setVersion(ver);
    ig.getDateElement().setValueAsString(date);
    ig.setPackageId(packageId);
    ig.setLicense(SPDXLicense.CC01_0);
    ig.getManifest().setRendering(vpath);
    if (FHIRVersion.isValidCode(fhirversion))
      ig.addFhirVersion(FHIRVersion.fromCode(fhirversion));
    List<String> fhirversions = new ArrayList<>();
    fhirversions.add(fhirversion);
    NPMPackageGenerator npm = new NPMPackageGenerator(file, canonical, vpath, PackageType.IG, ig, date, fhirversions);
    for (File f : new File(folder).listFiles()) {
      if (f.getName().endsWith(".json")) {
        byte[] src = TextFile.fileToBytes(f.getAbsolutePath());
        String s = TextFile.bytesToString(src);
        if (s.contains("\"resourceType\"")) {
          JsonObject json = JsonTrackingParser.parseJson(s);
          if (json.has("resourceType") && json.has("id") && json.get("id").isJsonPrimitive()) {
            String rt = json.get("resourceType").getAsString();
            String id = json.get("id").getAsString();
            npm.addFile(Category.RESOURCE, rt+"-"+id+"json", src);
          }
        }
      }
    }
    npm.finish();    
  }


}
