package org.hl7.fhir.igtools.publisher;
 
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hl7.fhir.convertors.VersionConvertorConstants;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.conformance.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.context.IWorkerContext.ILoggingService.LogCategory;
import org.hl7.fhir.r5.elementmodel.ParserBase;
import org.hl7.fhir.r5.elementmodel.Property;
import org.hl7.fhir.r5.formats.FormatUtilities;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r5.model.MetadataResource;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class IGKnowledgeProvider implements ProfileKnowledgeProvider, ParserBase.ILinkResolver {

  private IWorkerContext context;
  private SpecMapManager specPaths;
  private Set<String> msgs = new HashSet<String>();
  private String pathToSpec;
  private String canonical;
  private List<ValidationMessage> errors;
  private JsonObject defaultConfig;
  private JsonObject resourceConfig;
  private String pathPattern;
  private boolean autoPath = false;
  private boolean noXhtml;
  
  public IGKnowledgeProvider(IWorkerContext context, String pathToSpec, JsonObject igs, List<ValidationMessage> errors, boolean noXhtml) throws Exception {
    super();
    this.context = context;
    this.pathToSpec = pathToSpec;
    if (this.pathToSpec.endsWith("/"))
      this.pathToSpec = this.pathToSpec.substring(0, this.pathToSpec.length()-1);
    this.errors = errors;
    this.noXhtml = noXhtml;
    loadPaths(igs);
  }

  private void loadPaths(JsonObject igs) throws Exception {
    JsonElement e = igs.get("canonicalBase");
    if (e == null)
      throw new Exception("You must define a canonicalBase in the json file");
    canonical = e.getAsString();
    e = igs.get("path-pattern");
    if (e != null)
      pathPattern = e.getAsString(); 
    defaultConfig = igs.getAsJsonObject("defaults");
    resourceConfig = igs.getAsJsonObject("resources");
    if (resourceConfig == null)
      throw new Exception("No \"resources\" entry found in json file (see http://wiki.hl7.org/index.php?title=IG_Publisher_Documentation#Control_file)");
    else
      for (Entry<String, JsonElement> pp : resourceConfig.entrySet()) {
      if (pp.getKey().equals("*")) {
        autoPath = true;
      } else if (!pp.getKey().startsWith("_")) {
        String s = pp.getKey();
        if (!s.contains("/"))
          throw new Exception("Bad Resource Identity - should have the format [Type]/[id]:" + s);
        String type = s.substring(0,  s.indexOf("/"));
        String id = s.substring(s.indexOf("/")+1); 
        if (!context.hasResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+type) && !(context.hasResource(StructureDefinition.class , "http://hl7.org/fhir/StructureDefinition/Conformance") && type.equals("CapabilityStatement")))
          throw new Exception("Bad Resource Identity - should have the format [Type]/[id] where Type is a valid resource type:" + s);
        if (!id.matches(FormatUtilities.ID_REGEX))
          throw new Exception("Bad Resource Identity - should have the format [Type]/[id] where id is a valid FHIR id type:" + s);

        if (!(pp.getValue() instanceof JsonObject))
          throw new Exception("Unexpected type in resource list - must be an object");
        JsonObject o = (JsonObject) pp.getValue();
        JsonElement p = o.get("base");
        //        if (p == null)
        //          throw new Exception("You must provide a base on each path in the json file");
        if (p != null && !(p instanceof JsonPrimitive) && !((JsonPrimitive) p).isString())
          throw new Exception("Unexpected type in paths - base must be a string");
        p = o.get("defns");
        if (p != null && !(p instanceof JsonPrimitive) && !((JsonPrimitive) p).isString())
          throw new Exception("Unexpected type in paths - defns must be a string");
        p = o.get("source");
        if (p != null && !(p instanceof JsonPrimitive) && !((JsonPrimitive) p).isString())
          throw new Exception("Unexpected type in paths - source must be a string");
      }
    }
  }
  
  private boolean hasBoolean(JsonObject obj, String code) {
    JsonElement e = obj.get(code);
    return e != null && e instanceof JsonPrimitive && ((JsonPrimitive) e).isBoolean();
  }

  private boolean getBoolean(JsonObject obj, String code) {
    JsonElement e = obj.get(code);
    return e != null && e instanceof JsonPrimitive && ((JsonPrimitive) e).getAsBoolean();
  }

  private boolean hasString(JsonObject obj, String code) {
    JsonElement e = obj.get(code);
    return e != null && (e instanceof JsonPrimitive && ((JsonPrimitive) e).isString()) || e instanceof JsonNull;
  }

  private String getString(JsonObject obj, String code) {
    JsonElement e = obj.get(code);
    if (e instanceof JsonNull)
      return null;
    else 
      return ((JsonPrimitive) e).getAsString();
  }

  public String doReplacements(String s, FetchedResource r, Map<String, String> vars, String format) throws FHIRException {
    if (Utilities.noString(s))
      return s;
    if (r.getId()== null) {
      throw new FHIRException("Error doing replacements - no id defined in resource: " + r.getTitle()== null ? "NO TITLE EITHER" : r.getTitle());
    }
    s = s.replace("{{[title]}}", r.getTitle() == null ? "?title?" : r.getTitle());
    s = s.replace("{{[name]}}", r.getId()+(format==null? "": "-"+format)+"-html");
    s = s.replace("{{[id]}}", r.getId());
    if (format!=null)
      s = s.replace("{{[fmt]}}", format);
    s = s.replace("{{[type]}}", r.getElement().fhirType());
    s = s.replace("{{[uid]}}", r.getElement().fhirType()+"="+r.getId());
    if (vars != null) {
      for (String n : vars.keySet())
        s = s == null ? "" : s.replace("{{["+n+"]}}", vars.get(n));
    }
    return s;
  }

  public String doReplacements(String s, Resource r, Map<String, String> vars, String format) {
    if (Utilities.noString(s))
      return s;
    s = s.replace("{{[title]}}", "?title?");
    s = s.replace("{{[name]}}", r.getId()+(format==null? "": "-"+format)+"-html");
    s = s.replace("{{[id]}}", r.getId());
    if (format!=null)
      s = s.replace("{{[fmt]}}", format);
//    s = s.replace("{{[type]}}", r.getElement().fhirType());
//    s = s.replace("{{[uid]}}", r.getElement().fhirType()+"="+r.getId());
    if (vars != null) {
      for (String n : vars.keySet())
        s = s.replace("{{["+n+"]}}", vars.get(n));
    }
    return s;
  }

  public boolean wantGen(FetchedResource r, String code) {
    if (r.getConfig() != null && hasBoolean(r.getConfig(), code))
      return getBoolean(r.getConfig(), code);
    JsonObject cfg = null;
    if (defaultConfig != null) {
      cfg = defaultConfig.getAsJsonObject(r.getElement().fhirType());
	  if (cfg != null && hasBoolean(cfg, code))
	    return getBoolean(cfg, code);
      cfg = defaultConfig.getAsJsonObject("Any");
      if (cfg != null && hasBoolean(cfg, code))
        return getBoolean(cfg, code);
    }
    return true;
  }

  public String getProperty(FetchedResource r, String propertyName) {
    if (r.getConfig() != null && hasString(r.getConfig(), propertyName))
      return getString(r.getConfig(), propertyName);
    if (defaultConfig != null) {
      JsonObject cfg = defaultConfig.getAsJsonObject(r.getElement().fhirType());
  	  if (cfg != null && hasString(cfg, propertyName))
  	    return getString(cfg, propertyName);
      cfg = defaultConfig.getAsJsonObject("Any");
      if (cfg != null && hasString(cfg, propertyName))
        return getString(cfg, propertyName);
    }
    return null;
  }

  public boolean hasProperty(FetchedResource r, String propertyName) {
    if (r.getConfig() != null && hasString(r.getConfig(), propertyName))
      return true;
    if (defaultConfig != null) {
      JsonObject cfg = defaultConfig.getAsJsonObject(r.getElement().fhirType());
      if (cfg != null && hasString(cfg, propertyName))
        return true;
      cfg = defaultConfig.getAsJsonObject("Any");
      if (cfg != null && hasString(cfg, propertyName))
        return true;
    }
    return false;
  }

  public String getDefinitionsName(FetchedResource r) {
	return getProperty(r, "defns");
  }

  // base specification only
  public void loadSpecPaths(SpecMapManager paths) throws Exception {
    this.specPaths = paths;
    for (MetadataResource bc : context.allConformanceResources()) {
      String s = paths.getPath(bc.getUrl());
      if (s == null && bc instanceof CodeSystem) { // work around for an R2 issue) 
        CodeSystem cs = (CodeSystem) bc;
        s = paths.getPath(cs.getValueSet());
      }
      if (s != null)
        bc.setUserData("path", specPath(s));
      
    }    
  }

  public String getSourceFor(String ref) {
    JsonObject o = resourceConfig.getAsJsonObject(ref);
    if (o == null)
      return null;
    JsonElement e = o.get("source");
    if (e == null)
      return null;
    return e.getAsString();
  }

  public void findConfiguration(FetchedFile f, FetchedResource r) {
    JsonObject e = resourceConfig.getAsJsonObject(r.getElement().fhirType()+"/"+r.getId());
    if (e != null)
      r.setConfig(e);
  }
  
  public void checkForPath(FetchedFile f, FetchedResource r, MetadataResource bc, boolean inner) throws FHIRException {
    if (!bc.hasUrl())
      error(f.getPath(), "Resource has no url: "+bc.getId());
    else if (bc.getUrl().startsWith(canonical) && !bc.getUrl().endsWith("/"+bc.getId()))
      error(f.getPath(), "Resource id/url mismatch: "+bc.getId()+"/"+bc.getUrl());
    if (!inner && !r.getId().equals(bc.getId()))
      error(f.getPath(), "Resource id/id mismatch: "+r.getId()+"/"+bc.getUrl());
    if (r.getConfig() == null)
      findConfiguration(f, r);
    JsonObject e = r.getConfig();
    bc.setUserData("config", e);
    String base = getProperty(r,  "base");
    if (base != null) 
      bc.setUserData("path", doReplacements(base, r, null, null));
    else if (pathPattern != null)
      bc.setUserData("path", pathPattern.replace("[type]", r.getElement().fhirType()).replace("[id]", r.getId()));
    else
      bc.setUserData("path", r.getElement().fhirType()+"/"+r.getId()+".html");
  }

  private void error(String location, String msg) {
    if (!msgs.contains(msg)) {
      msgs.add(msg);
      errors.add(new ValidationMessage(Source.Publisher, IssueType.INVARIANT, location, msg, IssueSeverity.ERROR));
    }
  }

  private void hint(String location, String msg) {
    if (!msgs.contains(msg)) {
      msgs.add(msg);
      errors.add(new ValidationMessage(Source.Publisher, IssueType.INVARIANT, location, msg, IssueSeverity.INFORMATION));
    }
  }

  private String makeCanonical(String ref) {
    return Utilities.pathURL(canonical, ref);
  }

  private void brokenLinkWarning(String location, String ref) {
    String s = "The reference "+ref+" could not be resolved";
    if (!msgs.contains(s)) {
      msgs.add(s);
      errors.add(new ValidationMessage(Source.Publisher, IssueType.INVARIANT, location, s, IssueSeverity.ERROR));
    }
  }

  private String specPath(String path) {
    return Utilities.pathURL(pathToSpec, path);
  }

  // ---- overrides ---------------------------------------------------------------------------
  
  @Override
  public boolean isDatatype(String name) {
    StructureDefinition sd = context.fetchTypeDefinition(name);
    return sd != null && (sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE || sd.getKind() == StructureDefinitionKind.COMPLEXTYPE) && sd.getDerivation() == TypeDerivationRule.SPECIALIZATION;
  }  

  @Override
  public boolean isResource(String name) {
    StructureDefinition sd = context.fetchTypeDefinition(name);
    return sd != null && (sd.getKind() == StructureDefinitionKind.RESOURCE) && sd.getDerivation() == TypeDerivationRule.SPECIALIZATION;
  }

  public boolean isLogical(String name) {
    StructureDefinition sd = context.fetchTypeDefinition(name);
    return sd != null && (sd.getKind() == StructureDefinitionKind.LOGICAL) && sd.getDerivation() == TypeDerivationRule.SPECIALIZATION;
  }

  @Override
  public boolean hasLinkFor(String name) {
    return isDatatype(name) || isResource(name) || isLogical(name);
  }

  @Override
  public String getLinkFor(String corepath, String name) {
    if (noXhtml && name.equals("xhtml"))
      return null;
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, ProfileUtilities.sdNs(name, null));
    if (sd != null && sd.hasUserData("path"))
        return sd.getUserString("path");
    brokenLinkWarning(corepath, name);
    return name+".html";
  }

  @Override
  public BindingResolution resolveBinding(StructureDefinition profile, ElementDefinitionBindingComponent binding, String path) {
    if (!binding.hasValueSet()) {
      BindingResolution br = new BindingResolution();
      br.url = specPath("terminologies.html#unbound");
      br.display = "(unbound)";
      return br;
    } else {
      return resolveBinding(profile, binding.getValueSet(), path);
    }
  }
  
  public BindingResolution resolveBinding(StructureDefinition profile, String ref, String path) {
    BindingResolution br = new BindingResolution();
    if (ref.startsWith("http://hl7.org/fhir/ValueSet/v3-")) {
      br.url = specPath("v3/"+ref.substring(32)+"/vs.html");
      br.display = ref.substring(32);
    } else if (ref.startsWith("ValueSet/")) {
      ValueSet vs = context.fetchResource(ValueSet.class, makeCanonical(ref));
      if (vs == null) {
        br.url = ref;  
        if (ref.equals("http://tools.ietf.org/html/bcp47"))
          br.display = "IETF BCP-47";
        else if (ref.equals("http://www.rfc-editor.org/bcp/bcp13.txt"))
          br.display = "IETF BCP-13";
        else if (ref.equals("http://www.ncbi.nlm.nih.gov/nuccore?db=nuccore"))
          br.display = "NucCore";
        else if (ref.equals("https://rtmms.nist.gov/rtmms/index.htm#!rosetta"))
          br.display = "Rosetta";
        else if (ref.equals("http://www.iso.org/iso/country_codes.htm"))
          br.display = "ISO Country Codes";
        else {
          br.url = ref.substring(9)+".html"; // broken link, 
          br.display = ref.substring(9);
          brokenLinkWarning(path, ref);
        }
      } else {
        br.url = vs.getUserString("path");
        br.display = vs.getName(); 
      }
    } else { 
      if (ref.startsWith("http://hl7.org/fhir/ValueSet/")) {
        ValueSet vs = context.fetchResource(ValueSet.class, ref);
        if (vs != null) { 
          br.url = vs.getUserString("path");
          br.display = vs.getName(); 
        } else {
          String vsr = VersionConvertorConstants.vsToRef(ref);
          if (vsr != null) {
            br.display = ref.substring(29);
            br.url = vsr;
          } else {
            br.display = ref.substring(29);
            br.url = ref.substring(29)+".html";
            brokenLinkWarning(path, ref);
          }
        }
      } else if (ref.startsWith("http://hl7.org/fhir/ValueSet/v3-")) {
        br.url = specPath("v3/"+ref.substring(26)+"/index.html"); 
        br.display = ref.substring(26);
      } else if (ref.startsWith("http://hl7.org/fhir/ValueSet/v2-")) {
        br.url = specPath("v2/"+ref.substring(26)+"/index.html"); 
        br.display = ref.substring(26);
      } else if (ref.startsWith("http://loinc.org/vs/")) {
        String code = tail(ref);
        if (code.startsWith("LL")) {
          br.url = "https://r.details.loinc.org/AnswerList/"+code+".html";
          br.display = "LOINC Answer List "+code;
        } else {
          br.url = "https://r.details.loinc.org/LOINC/"+code+".html";
          br.display = "LOINC "+code;
        }
      } else {
        ValueSet vs = context.fetchResource(ValueSet.class, ref);
        if (vs != null && vs.hasUserData("path")) {
          br.url = vs.getUserString("path");  
          br.display = vs.present();
        } else if (Utilities.isAbsoluteUrl(ref) && (!ref.startsWith("http://hl7.org") || !ref.startsWith("http://terminology.hl7.org"))) {
          br.url = ref;  
          br.display = ref;
        } else if (vs == null) {
          br.url = ref+".html"; // broken link, 
          br.display = ref;
          brokenLinkWarning(path, ref);
        } else if (ref.contains("|")) {
          br.url = vs.getUserString("versionpath");
          if (br.url==null) {
            System.out.println("Unable to find version-specific path for reference - defaulting to version-independent reference: " + ref);
            br.url = vs.getUserString("path");
          }
          br.display = vs.getName(); 
        } else {
          br.url = vs.getUserString("path");
          br.display = vs.getName(); 
        }
      }
    }
    return br;
  }

  private String tail(String ref) {
    if  (ref.contains("/"))
      return ref.substring(ref.lastIndexOf("/")+1);
    else
      return ref;
  }

  @Override
  public String getLinkForProfile(StructureDefinition profile, String url) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, url);
    if (noXhtml && sd != null && sd.getType().equals("xhtml"))
      return null;
    if (sd != null && sd.hasUserData("path"))
      return sd.getUserString("path")+"|"+sd.getName();
    brokenLinkWarning("??", url);
    return "unknown.html|??";
  }


  @Override
  public String resolveType(String type) {
    return getLinkFor("", type);
  }

  @Override
  public String resolveProperty(Property property) {
    String path = property.getDefinition().getPath();
    return property.getStructure().getUserString("path")+"#"+path;
  }

  @Override
  public String resolvePage(String name) {
    return specPath(name);
  }

  @Override
  public boolean prependLinks() {
    return false;
  }

  public String getCanonical() {
    return canonical;
  }

  public String getLinkFor(FetchedResource r) {
	String base = getProperty(r, "base");
	if (base!=null)
	  return base;
  return r.getElement().fhirType()+"-"+r.getId()+".html";
  }

  public IWorkerContext getContext() {
    return context;
  }

  public boolean isAutoPath() {
    return autoPath;
  }

  public BindingResolution resolveActualUrl(String uri) {
    BindingResolution br = new BindingResolution();
    if (uri.startsWith("http://loinc.org/vs/")) {
      String code = tail(uri);
      if (code.startsWith("LL")) {
        br.url = "https://r.details.loinc.org/AnswerList/"+code+".html";
        br.display = "LOINC Answer List "+code;
      } else {
        br.url = "https://r.details.loinc.org/LOINC/"+code+".html";
        br.display = "LOINC "+code;
      }
    } else if (uri.startsWith("urn:")) {
      br.url = null;
      br.display = uri;
    } else {
      br.url = uri;
      br.display = uri;
    }
    return br;
  }

  public boolean isHL7Checks() {
    return canonical.contains("hl7.org") || canonical.contains("fhir.org") ;
  }

}
