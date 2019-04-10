package org.hl7.fhir.igtools.publisher;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.hl7.fhir.convertors.IGR2ConvertorAdvisor;
import org.hl7.fhir.convertors.VersionConvertorAdvisor40;
import org.hl7.fhir.convertors.VersionConvertorAdvisor50;
import org.hl7.fhir.convertors.VersionConvertor_10_40;
import org.hl7.fhir.convertors.VersionConvertor_10_50;
import org.hl7.fhir.convertors.VersionConvertor_14_40;
import org.hl7.fhir.convertors.VersionConvertor_14_50;
import org.hl7.fhir.convertors.VersionConvertor_30_40;
import org.hl7.fhir.convertors.VersionConvertor_30_50;
import org.hl7.fhir.convertors.VersionConvertor_40_50;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.context.IWorkerContext.ValidationResult;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.CodeableConcept;
import org.hl7.fhir.r5.model.Coding;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.GuideParameterCode;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDependsOnComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.SPDXLicense;
import org.hl7.fhir.r5.model.Parameters;
import org.hl7.fhir.r5.model.Reference;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.utils.IGHelper;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.PackageCacheManager;
import org.hl7.fhir.utilities.cache.ToolsVersion;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ConfigFileConverter {

  /**
   * given a string filename that refers to a json config file, find and load the ig, then 
   * merge everything into the ig and save it, along with a config.yml
   * @param pcm 
   * @throws Exception 
   * 
   */
  public void convert(String configFile, IWorkerContext context, PackageCacheManager pcm) throws Exception {
    JsonObject configuration = JsonTrackingParser.parseJsonFile(configFile);
    if (configuration.has("redirect")) { // redirect to support auto-build for complex projects with IG folder in subdirectory
      String redirectFile = Utilities.path(Utilities.getDirectoryForFile(configFile), configuration.get("redirect").getAsString());
      configFile = redirectFile;
      configuration = JsonTrackingParser.parseJsonFile(redirectFile);
    }
    String version = ostr(configuration, "version");
    if (Utilities.noString(version))
      version = Constants.VERSION;
    String rootDir = Utilities.getDirectoryForFile(configFile);
    if (Utilities.noString(rootDir))
      rootDir = getCurentDirectory();
    // We need the root to be expressed as a full path.  getDirectoryForFile will do that in general, but not in Eclipse
    rootDir = new File(rootDir).getCanonicalPath();

    JsonObject paths = configuration.getAsJsonObject("paths");
    List<String> resourceDirs = new ArrayList<String>();
    List<String> pagesDirs = new ArrayList<String>();
    if (paths != null && paths.get("resources") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("resources"))
        resourceDirs .add(Utilities.path(rootDir, ((JsonPrimitive) e).getAsString()));
    } else
      resourceDirs.add(Utilities.path(rootDir, str(paths, "resources", "resources")));
    if (paths != null && paths.get("pages") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("pages"))
        pagesDirs.add(Utilities.path(rootDir, ((JsonPrimitive) e).getAsString()));
    } else
      pagesDirs.add(Utilities.path(rootDir, str(paths, "pages", "pages")));
    String qaDir = Utilities.path(rootDir, str(paths, "qa"));
    String vsCache = ostr(paths, "txCache");

    String igName = Utilities.path(resourceDirs.get(0), str(configuration, "source", "ig.xml"));
    ImplementationGuide ig = (ImplementationGuide) parse(igName, version);

    // populating the IG from the config file
    ig.addFhirVersion(FHIRVersion.fromCode(version));
    if (configuration.has("fixed-business-version")) {
      ig.setVersion(configuration.getAsJsonPrimitive("fixed-business-version").getAsString());
      IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.APPLY, "version");
    }
    if (configuration.has("license")) 
      ig.setLicense(SPDXLicense.fromCode(configuration.getAsJsonPrimitive("license").getAsString()));
    if (configuration.has("html-template")) 
      IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.HTMLTEMPLATE, configuration.getAsJsonPrimitive("html-template").getAsString());
    for (String s : resourceDirs)
      ig.addExtension(ToolingExtensions.EXT_IGP_RESOURCES, new StringType(s));
    for (String s : pagesDirs)
      ig.addExtension(ToolingExtensions.EXT_IGP_PAGES, new StringType(s));
    ig.setPackageId(configuration.get("npm-name").getAsString());
    if (configuration.has("jurisdiction")) {
      ig.getJurisdiction().clear();
      for (String s : configuration.getAsJsonPrimitive("jurisdiction").getAsString().trim().split("\\,")) {
        CodeableConcept cc = ig.addJurisdiction();
        Coding c = cc.addCoding();
        String sc = s.trim();
        if (Utilities.isInteger(sc)) 
          c.setSystem("http://unstats.un.org/unsd/methods/m49/m49.htm").setCode(sc);
        else
          c.setSystem("urn:iso:std:iso:3166").setCode(sc);
        ValidationResult vr = context.validateCode(c, null);
        if (vr.getDisplay() != null)
          c.setDisplay(vr.getDisplay());
      }
      IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.APPLY, "jurisdiction");
    }
    IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.GENERATEJSON, true);
    IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.GENERATEXML, true);
    IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.GENERATETURTLE, true);

    Parameters p = new Parameters();
    String sct = str(configuration, "sct-edition", "http://snomed.info/sct/900000000000207008");
    p.addParameter("system-version", "http://snomed.info/sct|"+sct);
    p.addParameter("activeOnly", "true".equals(ostr(configuration, "no-inactive-codes")));
    new JsonParser().compose(new FileOutputStream(Utilities.path(Utilities.getDirectoryForFile(igName), "ig-expansion-parameters.json")), ig);
    p = new Parameters();
    p.addParameter("broken-links", "true".equals(ostr(configuration, "broken-links")));
    p.addParameter("check-aggregation", "true".equals(ostr(configuration, "check-aggregation")));
    p.addParameter("check-mustSupport", "true".equals(ostr(configuration, "check-mustSupport")));
    p.addParameter("anyExtensionsAllowed", "true".equals(ostr(configuration, "anyExtensionsAllowed")));
    p.addParameter("hintAboutNonMustSupport", "true".equals(ostr(configuration, "hintAboutNonMustSupport")));
    p.addParameter("suppressedWarningFile", ostr(configuration, "suppressedWarningFile"));
    if (paths.get("extension-domains") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("extension-domains"))
        p.addParameter("extension-domain", ((JsonPrimitive) e).getAsString());
    }
    if (paths.get("special-urls") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("special-urls"))
        p.addParameter("special-url", ((JsonPrimitive) e).getAsString());
    }
    new JsonParser().compose(new FileOutputStream(Utilities.path(Utilities.getDirectoryForFile(igName), "ig-validation-parameters.json")), ig);
    if (paths.get("spreadsheets") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("spreadsheets"))
        ig.addExtension(ToolingExtensions.EXT_IGP_SPREADSHEET, new StringType(((JsonPrimitive) e).getAsString()));
    }
    if (paths.get("bundles") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("bundles"))
        ig.addExtension(ToolingExtensions.EXT_IGP_BUNDLE, new StringType(((JsonPrimitive) e).getAsString()));
    }
    JsonObject defaults = configuration.getAsJsonObject("defaults");
    if (defaults != null) {
      JsonObject any = defaults.getAsJsonObject("any");
      if (any != null) {
        if (any.has("xml"))
          IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.GENERATEXML, Boolean.parseBoolean(any.get("xml").getAsString()));          
        if (any.has("json"))
          IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.GENERATEJSON, Boolean.parseBoolean(any.get("json").getAsString()));          
        if (any.has("tl"))
          IGHelper.setParameter(ig.getDefinition(), GuideParameterCode.GENERATETURTLE, Boolean.parseBoolean(any.get("ttl").getAsString()));          
      }
    }

    JsonArray deps = configuration.getAsJsonArray("dependencyList");
    ig.getDependsOn().clear();
    if (deps != null) {
      for (JsonElement d : deps) {
        JsonObject dep = (JsonObject) d;
        String name = str(dep, "name");
        String canonical = ostr(dep, "location");
        String igver = ostr(dep, "version");
        String packageId = ostr(dep, "package");

        if (Utilities.noString(packageId))
          packageId = pcm.getPackageId(canonical);
        if (Utilities.noString(canonical) && !Utilities.noString(packageId))
          canonical = pcm.getPackageUrl(packageId);
        if (Utilities.noString(canonical))
          throw new Exception("You must specify a canonical URL for the IG "+name);

        ImplementationGuideDependsOnComponent igd = ig.addDependsOn();
        igd.setId(name);
        igd.setUri(canonical);
        igd.setPackageId(packageId);
        igd.setVersion(igver);
      }
    }
    if (configuration.has("resources")) {
      for (Entry<String, JsonElement> pp : configuration.getAsJsonObject("resources").entrySet()) {
        if (!pp.getKey().startsWith("_")) {
          String s = pp.getKey();
          JsonObject o = (JsonObject) pp.getValue();
          JsonElement vb = o.get("base");
          JsonElement vd = o.get("defns");
          JsonElement vf = o.get("format");
          JsonElement vs = o.get("source");
          if (vb != null && vd != null && vf != null && vs != null) {
            ImplementationGuideDefinitionResourceComponent res = getResource(ig.getDefinition(), s);
            if (res == null) {
              res = ig.getDefinition().addResource();
              res.setReference(new Reference(s));
            }
            if (vb != null)
              res.addExtension(ToolingExtensions.EXT_IGP_BASE, new StringType(((JsonPrimitive) vb).getAsString()));
            if (vd != null)
              res.addExtension(ToolingExtensions.EXT_IGP_DEFNS, new StringType(((JsonPrimitive) vd).getAsString()));
            if (vf != null)
              res.addExtension(ToolingExtensions.EXT_IGP_FORMAT, new StringType(((JsonPrimitive) vf).getAsString()));
            if (vs != null)
              res.addExtension(ToolingExtensions.EXT_IGP_SOURCE, new StringType(((JsonPrimitive) vs).getAsString()));
          }
        }
      }
    }

    String newfile = Utilities.path(Utilities.getDirectoryForFile(igName), "ig-new.json");
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(newfile), ig);
    newfile = Utilities.path(Utilities.getDirectoryForFile(igName), "ig-new.xml");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(newfile), ig);
    IniFile ini = new IniFile(Utilities.path(rootDir, "ig.ini"));
    ini.setStringProperty("IG", "ig", newfile.substring(rootDir.length()+1), null);
    ini.save();
  }


  private ImplementationGuideDefinitionResourceComponent getResource(ImplementationGuideDefinitionComponent configuration, String ref) {
    for (ImplementationGuideDefinitionResourceComponent res : configuration.getResource()) {
      if (res.getReference().getReference().equals(ref))
        return res;
    }
    return null;
  }

  private Resource parse(String filename, String version) throws Exception {
    if (version.equals("3.0.1") || version.equals("3.0.0")) {
      org.hl7.fhir.dstu3.model.Resource res;
      if (filename.contains("json"))
        res = new org.hl7.fhir.dstu3.formats.JsonParser().parse(new FileInputStream(filename));
      else if (filename.contains("xml"))
        res = new org.hl7.fhir.dstu3.formats.XmlParser().parse(new FileInputStream(filename));
      else
        throw new Exception("Unable to determine file type for "+filename);
      return VersionConvertor_30_50.convertResource(res, false);
    } else if (version.equals("1.4.0")) {
      org.hl7.fhir.dstu2016may.model.Resource res;
      if (filename.contains("json"))
        res = new org.hl7.fhir.dstu2016may.formats.JsonParser().parse(new FileInputStream(filename));
      else if (filename.contains("xml"))
        res = new org.hl7.fhir.dstu2016may.formats.XmlParser().parse(new FileInputStream(filename));
      else
        throw new Exception("Unable to determine file type for "+filename);
      return VersionConvertor_14_50.convertResource(res);
    } else if (version.equals("1.0.2")) {
      org.hl7.fhir.dstu2.model.Resource res;
      if (filename.contains("json"))
        res = new org.hl7.fhir.dstu2.formats.JsonParser().parse(new FileInputStream(filename));
      else if (filename.contains("xml"))
        res = new org.hl7.fhir.dstu2.formats.XmlParser().parse(new FileInputStream(filename));
      else
        throw new Exception("Unable to determine file type for "+filename);

      VersionConvertorAdvisor50 advisor = new IGR2ConvertorAdvisor5();
      return new VersionConvertor_10_50(advisor).convertResource(res);
    } else if (version.equals("4.0.0")) {
      org.hl7.fhir.r4.model.Resource res;
      if (filename.contains("json"))
        res = new org.hl7.fhir.r4.formats.JsonParser().parse(new FileInputStream(filename));
      else if (filename.contains("xml"))
        res = new org.hl7.fhir.r4.formats.XmlParser().parse(new FileInputStream(filename));
      else
        throw new Exception("Unable to determine file type for "+filename);
      return VersionConvertor_40_50.convertResource(res);
    } else if (version.equals(Constants.VERSION)) {
      if (filename.contains("json"))
        return new JsonParser().parse(new FileInputStream(filename));
      else if (filename.contains("xml"))
        return new XmlParser().parse(new FileInputStream(filename));
      else
        throw new Exception("Unable to determine file type for "+filename);
    } else
      throw new Exception("Unsupported version "+version+" (current = "+Constants.VERSION+" ("+ToolsVersion.TOOLS_VERSION+")");

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

  private String ostr(JsonObject obj, String name) throws Exception {
    if (obj == null)
      return null;
    if (!obj.has(name))
      return null;
    if (!(obj.get(name) instanceof JsonPrimitive))
      return null;
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      return null;
    return p.getAsString();
  }

  private String str(JsonObject obj, String name, String defValue) throws Exception {
    if (obj == null || !obj.has(name))
      return defValue;
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new Exception("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new Exception("Property "+name+" not a string");
    return p.getAsString();
  }

  private static String getCurentDirectory() {
    String currentDirectory;
    File file = new File(".");
    currentDirectory = file.getAbsolutePath();
    return currentDirectory;
  }}

