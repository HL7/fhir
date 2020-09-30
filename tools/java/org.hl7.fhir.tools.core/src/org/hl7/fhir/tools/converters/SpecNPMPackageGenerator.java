package org.hl7.fhir.tools.converters;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.convertors.VersionConvertor_10_50;
import org.hl7.fhir.convertors.VersionConvertor_14_50;
import org.hl7.fhir.convertors.VersionConvertor_30_50;
import org.hl7.fhir.convertors.VersionConvertor_40_50;
import org.hl7.fhir.dstu2.model.StructureDefinition;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.igtools.publisher.SpecMapManager;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.BooleanType;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.ManifestPageComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ManifestResourceComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.SPDXLicense;
import org.hl7.fhir.r5.model.Reference;
import org.hl7.fhir.r5.utils.NPMPackageGenerator;
import org.hl7.fhir.r5.utils.NPMPackageGenerator.Category;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.VersionUtilities;
import org.hl7.fhir.utilities.cache.PackageGenerator.PackageType;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class SpecNPMPackageGenerator {

  public class ResourceEntry {
    String type;
    String id;
    String canonical;
    boolean conf;
    byte[] json;
    byte[] xml;
  }
//
//  public static void main(String[] args) throws Exception {
////    generateForVersion("F:\\fhir\\web\\DSTU2", "http://hl7.org/fhir/DSTU2");
//    generateForVersion("E:\\fhir\\web\\2016May", "http://hl7.org/fhir/2016May", new Date());
////    generateForVersion("F:\\fhir\\web\\STU3", "http://hl7.org/fhir/2016STU3");
//    System.out.println("Done");
//  }

//  private static void generateForVersion(String folder, String url, Date genDate) throws Exception {
//    SpecNPMPackageGenerator self = new SpecNPMPackageGenerator();
//    self.generate(folder, url, false, genDate);
//  }
//  
  
  public void generate(String folder, String url, boolean forWeb, Date genDate) throws Exception {
    System.out.println("Generate Package for "+folder);
    
    Map<String, byte[]> files = loadZip(new FileInputStream(Utilities.path(folder, "igpack.zip")));
    FHIRVersion version = determineVersion(files);    
    
    System.out.println(" .. Loading v"+version);
    SpecMapManager spm = new SpecMapManager(files.get("spec.internals"), version.toCode());    
    System.out.println(" .. Conformance Resources");
    List<ResourceEntry> reslist = makeResourceList(files, version.toCode());
    System.out.println(" .. Other Resources");
    addToResList(folder, reslist, version.toCode());
    
    System.out.println(" .. building IG");
    ImplementationGuide ig = new ImplementationGuide();
    ig.setId("fhir");
    ig.setUrl("http://hl7.org/fhir/ImplementationGuide/fhir");
    ig.setVersion(version.toCode());
    ig.addFhirVersion(version);
    ig.setLicense(SPDXLicense.CC01_0);
    ig.setTitle("FHIR Core package");
    ig.setDescription("FHIR Core package - the NPM package that contains all the definitions for the base FHIR specification");
    ig.setPublisher("HL7 Inc");
    ig.getContactFirstRep().getTelecomFirstRep().setSystem(ContactPointSystem.URL).setValue("http://hl7.org/fhir");
    ig.setPackageId("hl7.fhir.r5.core");
    ig.getManifest().setRendering(url);
    for (ResourceEntry e : reslist) {
      ManifestResourceComponent r = ig.getManifest().addResource();
      r.setReference(new Reference(e.type+"/"+e.id));
      if (e.conf)
        r.setExample(new BooleanType(true));
      r.setRelativePath(spm.getPath(e.canonical, null));  
    }
    for (String k : files.keySet()) {
      if (k.endsWith(".png") || k.endsWith(".gif"))
        ig.getManifest().addImage(k);
      else if (k.endsWith(".css"))
        ig.getManifest().addOther(k);
    }
    Map<String, ManifestPageComponent> map = new HashMap<String, ManifestPageComponent>();
    for (String k : spm.getPages()) {
      ManifestPageComponent pp = ig.getManifest().addPage();
      pp.setName(k).setTitle(spm.getPage(k));
      map.put(pp.getName(), pp);      
    }
    for (String k : spm.getTargets()) {
      String n = null;
      String f = null;
      if (k.contains("#")) {
        n = k.substring(0, k.indexOf("#"));
        f = k.substring(k.indexOf("#")+1);
      } else
        n = k;
      ManifestPageComponent p = map.get(n);
      if (p == null) {
        p = ig.getManifest().addPage();
        p.setName(n);
        map.put(p.getName(), p);      
      }
      if (f != null)
        p.addAnchor(f);
    }
    // ok ig is full loaded...
    
    System.out.println(" .. Building NPM Package");

    NPMPackageGenerator npm = new NPMPackageGenerator(Utilities.path(folder, "hl7.fhir.r5.core.tgz"), "http://hl7.org/fhir", url, PackageType.CORE, ig, genDate, true);
    
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    new org.hl7.fhir.r5.formats.JsonParser().setOutputStyle(OutputStyle.NORMAL).compose(bs, ig);
//    npm.addFile(Category.RESOURCE, "ig-r4.json", bs.toByteArray());
    addConvertedIg(npm, ig, version.toCode());
    for (ResourceEntry e : reslist) {
      npm.addFile(Category.RESOURCE, e.type+"-"+e.id+".json", e.json);
    }
    for (String k : files.keySet()) {
      if (k.endsWith(".png") || k.endsWith(".css") || k.endsWith(".template") || k.endsWith(".zip") || k.endsWith(".gif") 
          || k.equals("spec.internals") || k.equals("mappingSpaces.details"))
        npm.addFile(Category.OTHER, k, files.get(k));
    }
    
    for (String fn : new File(folder).list()) {
      if (fn.endsWith(".schema.json") || fn.endsWith(".openapi.json") ) {
        byte[] b = TextFile.fileToBytes(Utilities.path(folder, fn));
        npm.addFile(Category.OPENAPI, fn, b);
      }
      if (fn.endsWith(".xsd") || fn.endsWith(".sch") ) {
        byte[] b = TextFile.fileToBytes(Utilities.path(folder, fn));
        npm.addFile(Category.SCHEMATRON, fn, b);
      }
    }
    npm.finish();
    System.out.println(" .. Built");

    System.out.println(" .. Building NPM Package (xml)");
    ig.setId("fhir-xml");
    ig.setUrl("http://hl7.org/fhir/ImplementationGuide/fhir-xml");
    ig.setTitle("FHIR Core package (XML Conformance files)");
    ig.setDescription("FHIR Core package - the NPM package that contains all the definitions for the base FHIR specification (XML)");
    ig.setPackageId("hl7.fhir.r5.corexml");

    npm = new NPMPackageGenerator(Utilities.path(folder, "hl7.fhir.r5.corexml.tgz"), "http://hl7.org/fhir", url, PackageType.CORE, ig, genDate, true);
    bs = new ByteArrayOutputStream();
    new org.hl7.fhir.r5.formats.XmlParser().setOutputStyle(OutputStyle.NORMAL).compose(bs, ig);
    npm.addFile(Category.RESOURCE, "ig-r4.json", bs.toByteArray());
    addConvertedIgX(npm, ig, version.toCode());
    for (ResourceEntry e : reslist) {
      if (e.xml != null)
        npm.addFile(Category.RESOURCE, e.type+"-"+e.id+".xml", e.xml);
    }
    npm.finish();
    System.out.println(" .. Built");

  }

  private void addConvertedIg(NPMPackageGenerator npm, ImplementationGuide ig, String version) throws IOException, FHIRException {
    if (VersionUtilities.isR5Ver(version))
      addConvertedIg5(npm, ig);
    else if (VersionUtilities.isR4Ver(version))
      addConvertedIg4(npm, ig);
    else if (VersionUtilities.isR3Ver(version))
      addConvertedIg3(npm, ig);
    else if (VersionUtilities.isR2BVer(version))
      addConvertedIg14(npm, ig);
    else if (VersionUtilities.isR2Ver(version))
      addConvertedIg10(npm, ig);
  }

  private void addConvertedIg5(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException {
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".json", new JsonParser().composeBytes(ig));
  }

  private void addConvertedIg4(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.r4.model.Resource res = VersionConvertor_40_50.convertResource(ig);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".json", new org.hl7.fhir.r4.formats.JsonParser().composeBytes(res));
  }

  private void addConvertedIg3(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.dstu3.model.Resource res = VersionConvertor_30_50.convertResource(ig, false);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".json", new org.hl7.fhir.dstu3.formats.JsonParser().composeBytes(res));
  }

  private void addConvertedIg14(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.dstu2016may.model.Resource res = VersionConvertor_14_50.convertResource(ig);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".json", new org.hl7.fhir.dstu2016may.formats.JsonParser().composeBytes(res));
  }

  private void addConvertedIg10(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.dstu2.model.Resource res = VersionConvertor_10_50.convertResource(ig);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".json", new org.hl7.fhir.dstu2.formats.JsonParser().composeBytes(res));
  }

  private void addConvertedIgX(NPMPackageGenerator npm, ImplementationGuide ig, String version) throws IOException, FHIRException {
    if (version.equals(Constants.VERSION))
      addConvertedIg5X(npm, ig);
    else if (version.equals("3.0.1"))
      addConvertedIg3X(npm, ig);
    else if (version.equals("4.0.0"))
      addConvertedIg4X(npm, ig);
    else if (version.equals("1.4.0"))
      addConvertedIg14X(npm, ig);
    else if (version.equals("1.0.2"))
      addConvertedIg10X(npm, ig);
  }

  private void addConvertedIg5X(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException {
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".xml", new XmlParser().composeBytes(ig));
  }

  private void addConvertedIg4X(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.r4.model.Resource res = VersionConvertor_40_50.convertResource(ig);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".xml", new org.hl7.fhir.r4.formats.XmlParser().composeBytes(res));
  }

  private void addConvertedIg3X(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.dstu3.model.Resource res = VersionConvertor_30_50.convertResource(ig, false);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".xml", new org.hl7.fhir.dstu3.formats.XmlParser().composeBytes(res));
  }

  private void addConvertedIg14X(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.dstu2016may.model.Resource res = VersionConvertor_14_50.convertResource(ig);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".xml", new org.hl7.fhir.dstu2016may.formats.XmlParser().composeBytes(res));
  }

  private void addConvertedIg10X(NPMPackageGenerator npm, ImplementationGuide ig) throws IOException, FHIRException {
    org.hl7.fhir.dstu2.model.Resource res = VersionConvertor_10_50.convertResource(ig);
    npm.addFile(Category.RESOURCE, "ImplementationGuide-"+ig.getId()+".xml", new org.hl7.fhir.dstu2.formats.XmlParser().composeBytes(res));
  }

  private void addToResList(String folder, List<ResourceEntry> reslist, String version) {
    for (File f : new File(folder).listFiles()) {
      if (f.getName().endsWith(".json") && !f.getName().endsWith(".diff.json") && !f.getName().endsWith(".schema.json") && !f.getName().equals("package.json") 
          && !f.getName().equals("backbone-elements.json")&& !f.getName().equals("choice-elements.json")) {
        try {
          byte[] b = TextFile.fileToBytes(f.getAbsolutePath());
          loadFile(reslist, b, f.getAbsolutePath());
        } catch (Exception e) {
          // nothing - we'll just ignore the file
        }
      }
    }    
  }

  private void loadFile(List<ResourceEntry> reslist, byte[] b, String sourceName) throws FHIRFormatError, IOException {
    try {
      JsonObject json = parseJson(b);
      if (json.has("id") && json.has("resourceType")) {
        String id = json.get("id").getAsString();
        String type = json.get("resourceType").getAsString(); 
        if (!Utilities.noString(id) && !hasEntry(reslist, type, id)) {
          ResourceEntry e = new ResourceEntry();
          e.type = type;
          e.id = id;
          e.json = b;
          e.conf = false;
          reslist.add(e);
        }
      }
    } catch (Throwable e) {
      throw new Error("Exception parsing "+sourceName+": "+e.getMessage(), e);
    }
  }

  private JsonObject parseJson(byte[] b) throws JsonSyntaxException, IOException {
    return (JsonObject) new com.google.gson.JsonParser().parse(TextFile.bytesToString(b, true));
  }

  private boolean hasEntry(List<ResourceEntry> reslist, String fhirType, String id) {
    for (ResourceEntry e : reslist) {
      if (e.type.equals(fhirType) && e.id.equals(id)) {
        return true;
      }
    }
    return false;
  }

  private List<ResourceEntry> makeResourceList(Map<String, byte[]> files, String version) throws FHIRFormatError, IOException {
    List<ResourceEntry> res = new ArrayList<SpecNPMPackageGenerator.ResourceEntry>();
    if (VersionUtilities.isR5Ver(version))
      makeResourceList5(files, version, res);
    else if (VersionUtilities.isR4Ver(version))
      makeResourceList4(files, version, res);
    else if (VersionUtilities.isR3Ver(version))
      makeResourceList3(files, version, res);
    else if (VersionUtilities.isR2BVer(version))
      makeResourceList14(files, version, res);
    else if (VersionUtilities.isR2Ver(version))
      makeResourceList10(files, version, res);
    return res;
  }

  private List<ResourceEntry> makeResourceList4(Map<String, byte[]> files, String version, List<ResourceEntry> res) throws FHIRFormatError, IOException {
    for (String k : files.keySet()) {
      if (k.endsWith(".xml")) {
        Bundle b = (Bundle) new org.hl7.fhir.r5.formats.XmlParser().parse(files.get(k));
        for (org.hl7.fhir.r5.model.Bundle.BundleEntryComponent be : b.getEntry()) {
          if (be.hasResource()) {
            ResourceEntry e = new ResourceEntry();
            e.type = be.getResource().fhirType();
            e.id = be.getResource().getId();
            e.json = new org.hl7.fhir.r5.formats.JsonParser().composeBytes(be.getResource());
            e.xml = new org.hl7.fhir.r5.formats.XmlParser().composeBytes(be.getResource());
            e.conf = true;
            if (be.getResource() instanceof org.hl7.fhir.r5.model.CanonicalResource)
              e.canonical = ((org.hl7.fhir.r5.model.CanonicalResource) be.getResource()).getUrl();
            res.add(e);
          }
        }
      }
    }
    return null;
  }

  private List<ResourceEntry> makeResourceList5(Map<String, byte[]> files, String version, List<ResourceEntry> res) throws FHIRFormatError, IOException {
    for (String k : files.keySet()) {
      if (k.endsWith(".xml")) {
        Bundle b = (Bundle) new org.hl7.fhir.r5.formats.XmlParser().parse(files.get(k));
        for (org.hl7.fhir.r5.model.Bundle.BundleEntryComponent be : b.getEntry()) {
          if (be.hasResource()) {
            ResourceEntry e = new ResourceEntry();
            e.type = be.getResource().fhirType();
            e.id = be.getResource().getId();
            e.json = new org.hl7.fhir.r5.formats.JsonParser().composeBytes(be.getResource());
            e.xml = new org.hl7.fhir.r5.formats.XmlParser().composeBytes(be.getResource());
            e.conf = true;
            if (be.getResource() instanceof org.hl7.fhir.r5.model.CanonicalResource)
              e.canonical = ((org.hl7.fhir.r5.model.CanonicalResource) be.getResource()).getUrl();
            res.add(e);
          }
        }
      }
    }
    return null;
  }

  private List<ResourceEntry> makeResourceList3(Map<String, byte[]> files, String version, List<ResourceEntry> res) throws FHIRFormatError, IOException {
    for (String k : files.keySet()) {
      if (k.endsWith(".xml")) {
        org.hl7.fhir.dstu3.model.Bundle b = (org.hl7.fhir.dstu3.model.Bundle) new org.hl7.fhir.dstu3.formats.XmlParser().parse(files.get(k));
        for (org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent be : b.getEntry()) {
          if (be.hasResource()) {
            ResourceEntry e = new ResourceEntry();
            e.type = be.getResource().fhirType();
            e.id = be.getResource().getId();
            e.json = new org.hl7.fhir.dstu3.formats.JsonParser().composeBytes(be.getResource());
            e.xml = new org.hl7.fhir.dstu3.formats.XmlParser().composeBytes(be.getResource());
            e.conf = true;
            if (be.getResource() instanceof org.hl7.fhir.dstu3.model.MetadataResource)
              e.canonical = ((org.hl7.fhir.dstu3.model.MetadataResource) be.getResource()).getUrl();
            res.add(e);
          }
        }
      }
    }
    return null;
  }

  private List<ResourceEntry> makeResourceList14(Map<String, byte[]> files, String version, List<ResourceEntry> res) throws FHIRFormatError, IOException {
    for (String k : files.keySet()) {
      if (k.endsWith(".xml")) {
        org.hl7.fhir.dstu2016may.model.Bundle b = (org.hl7.fhir.dstu2016may.model.Bundle) new org.hl7.fhir.dstu2016may.formats.XmlParser().parse(files.get(k));
        for (org.hl7.fhir.dstu2016may.model.Bundle.BundleEntryComponent be : b.getEntry()) {
          if (be.hasResource()) {
            ResourceEntry e = new ResourceEntry();
            e.type = be.getResource().fhirType();
            e.id = be.getResource().getId();
            e.json = new org.hl7.fhir.dstu2016may.formats.JsonParser().composeBytes(be.getResource());
            e.xml = new org.hl7.fhir.dstu2016may.formats.XmlParser().composeBytes(be.getResource());
            e.conf = true;
            if (be.getResource() instanceof org.hl7.fhir.dstu2016may.model.ValueSet)
              e.canonical = ((org.hl7.fhir.dstu2016may.model.ValueSet) be.getResource()).getUrl();
            if (be.getResource() instanceof org.hl7.fhir.dstu2016may.model.StructureDefinition)
              e.canonical = ((org.hl7.fhir.dstu2016may.model.StructureDefinition) be.getResource()).getUrl();
            if (be.getResource() instanceof org.hl7.fhir.dstu2016may.model.ConceptMap)
              e.canonical = ((org.hl7.fhir.dstu2016may.model.ConceptMap) be.getResource()).getUrl();
            if (be.getResource() instanceof org.hl7.fhir.dstu2016may.model.CodeSystem)
              e.canonical = ((org.hl7.fhir.dstu2016may.model.CodeSystem) be.getResource()).getUrl();
            res.add(e);
          }
        }
      }
    }
    return null;
  }

  private List<ResourceEntry> makeResourceList10(Map<String, byte[]> files, String version, List<ResourceEntry> res) throws FHIRFormatError, IOException {
    for (String k : files.keySet()) {
      if (k.endsWith(".xml")) {
        org.hl7.fhir.dstu2.model.Resource r = new org.hl7.fhir.dstu2.formats.XmlParser().parse(files.get(k));
        if (r instanceof StructureDefinition) {
          ResourceEntry e = new ResourceEntry();
          e.type = r.fhirType();
          e.id = r.getId();
          e.json = new org.hl7.fhir.dstu2.formats.JsonParser().composeBytes(r);
          e.xml = new org.hl7.fhir.dstu2.formats.XmlParser().composeBytes(r);
          e.conf = true;
          if (r instanceof org.hl7.fhir.dstu2.model.StructureDefinition)
            e.canonical = ((org.hl7.fhir.dstu2.model.StructureDefinition) r).getUrl();
          res.add(e);
        } else {
          org.hl7.fhir.dstu2.model.Bundle b = (org.hl7.fhir.dstu2.model.Bundle) r;
          for (org.hl7.fhir.dstu2.model.Bundle.BundleEntryComponent be : b.getEntry()) {
            if (be.hasResource()) {
              ResourceEntry e = new ResourceEntry();
              e.type = be.getResource().fhirType();
              e.id = be.getResource().getId();
              e.json = new org.hl7.fhir.dstu2.formats.JsonParser().composeBytes(be.getResource());
              e.conf = true;
              if (be.getResource() instanceof org.hl7.fhir.dstu2.model.ValueSet)
                e.canonical = ((org.hl7.fhir.dstu2.model.ValueSet) be.getResource()).getUrl();
              if (be.getResource() instanceof org.hl7.fhir.dstu2.model.StructureDefinition)
                e.canonical = ((org.hl7.fhir.dstu2.model.StructureDefinition) be.getResource()).getUrl();
              if (be.getResource() instanceof org.hl7.fhir.dstu2.model.ConceptMap)
                e.canonical = ((org.hl7.fhir.dstu2.model.ConceptMap) be.getResource()).getUrl();
              res.add(e);
            }
          }
        }
      }
    }
    return null;
  }

  private boolean isResource(byte[] b) {
    String s = new String(b).replace(" ", "");
    return s.contains("\"resourceType\":\""); 
  }

  private boolean isIncludedResource(String resourceType) {
    return resourceType != null && Utilities.existsInList(resourceType, "CapabilityStatement", "Conformance", "StructureDefinition", "ImplementationGuide",  "SearchParameter", "MessageDefinition", 
        "OperationDefinition", "CompartmentDefinition", "StructureMap", "GraphDefinition", "ExampleScenario", 
        "CodeSystem", "ValueSet", "ConceptMap", "ExpansionProfile", "NamingSystem", "TerminologyCapabilities");
  }

  private String readVersion(String folder) throws IOException {
    IniFile ini = new IniFile(Utilities.path(folder, "version.info"));
    return ini.getStringProperty("FHIR", "version");
  }

  private Map<String, byte[]> loadZip(InputStream stream) throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    ZipInputStream zip = new ZipInputStream(stream);
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      int size;
      byte[] buffer = new byte[2048];

      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(bytes, buffer.length);

      while ((size = zip.read(buffer, 0, buffer.length)) != -1) {
        bos.write(buffer, 0, size);
      }
      bos.flush();
      bos.close();
      res.put(ze.getName(), bytes.toByteArray());

      zip.closeEntry();
    }
    zip.close();
    return res;
  }

  private FHIRVersion determineVersion(Map<String, byte[]> files) throws FHIRException {
    byte[] b = files.get("version.info");
    if (b == null)
      return FHIRVersion.NULL;
    String s = new String(b);
    s = Utilities.stripBOM(s).trim();
    while (s.charAt(0) != '[')
      s = s.substring(1);
    byte[] bytes = {};
    try {
      bytes = s.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {

    }
    ByteArrayInputStream bs = new ByteArrayInputStream(bytes);
    IniFile ini = new IniFile(bs);
    String v = ini.getStringProperty("FHIR", "version");
    if (v == null)
      throw new Error("unable to determine version from "+new String(bytes));
    if ("3.0.0".equals(v))
      v = "3.0.1";
    return FHIRVersion.fromCode(v);
  }


}
