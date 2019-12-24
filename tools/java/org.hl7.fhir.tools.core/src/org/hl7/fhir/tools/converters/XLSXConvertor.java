package org.hl7.fhir.tools.converters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.convertors.R2016MayToR5Loader;
import org.hl7.fhir.convertors.R2ToR5Loader;
import org.hl7.fhir.convertors.R3ToR5Loader;
import org.hl7.fhir.convertors.R4ToR5Loader;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.context.SimpleWorkerContext;
import org.hl7.fhir.r5.context.SimpleWorkerContext.IContextResourceLoader;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.VersionUtilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageCacheManager;
import org.hl7.fhir.utilities.cache.ToolsVersion;

public class XLSXConvertor {

  public static void main(String[] args) throws Exception {
    new XLSXConvertor().convert("1.0.2", "c:\\temp\\definitions.xlsx.r2.zip");
    new XLSXConvertor().convert("1.4.0", "c:\\temp\\definitions.xlsx.r14.zip");
    new XLSXConvertor().convert("3.0.1", "c:\\temp\\definitions.xlsx.r3.zip");
    new XLSXConvertor().convert("4.0.0", "c:\\temp\\definitions.xlsx.r4.zip");
    System.out.println("done");
  }

  private void convert(String version, String dest) throws Exception {
    System.out.println("generate "+dest);
    String td = Utilities.path("[tmp]", "xls-work");
    Utilities.createDirectory(td);
    Utilities.clearDirectory(td);
    PackageCacheManager pcm = new PackageCacheManager(true, ToolsVersion.TOOLS_VERSION);

    Map<String, byte[]> res = loadPackage(pcm.loadPackage("hl7.fhir.core", version));
    SimpleWorkerContext context = SimpleWorkerContext.fromDefinitions(res, loaderForVersion(version));
    ZipGenerator zip = new ZipGenerator(dest);
    for (StructureDefinition sd : context.allStructures()) {
      if (sd.getDerivation() == TypeDerivationRule.SPECIALIZATION) {
        String rn = sd.getId();
        System.out.println("  .. "+rn);
        new ProfileUtilities(context, null, null).generateXlsx(new FileOutputStream(Utilities.path(td, rn.toLowerCase()+".xlsx")), sd, false, false);
        zip.addFileName(rn.toLowerCase()+".xlsx", Utilities.path(td, rn.toLowerCase()+".xlsx"), false);
      }
    }
    zip.close();
  }

  private IContextResourceLoader loaderForVersion(String version) {
    if (VersionUtilities.isR2Ver(version))
      return new R2ToR5Loader();
    if (VersionUtilities.isR2BVer(version))
      return new R2016MayToR5Loader(); // special case
    if (VersionUtilities.isR3Ver(version))
      return new R3ToR5Loader();    
    if (VersionUtilities.isR4Ver(version))
      return new R4ToR5Loader();    
    return null;
  }

  private Map<String, byte[]> loadPackage(NpmPackage pi) throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    for (String s : pi.listResources("StructureDefinition")) {
      res.put(s, TextFile.streamToBytes(pi.load("package", s)));
    }
    return res;    
  }

}
