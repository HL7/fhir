package org.hl7.fhir.igtools.publisher;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hl7.fhir.dstu3.elementmodel.ParserBase;
import org.hl7.fhir.dstu3.elementmodel.Property;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.dstu3.utils.ProfileUtilities.ProfileKnowledgeProvider.BindingResolution;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.igtools.renderers.ValidationPresenter.ValidationOutcomes;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class IGKnowledgeProvider implements ProfileKnowledgeProvider, ParserBase.ILinkResolver {

  private IWorkerContext context;
  private JsonObject specPaths;
  private Set<String> msgs = new HashSet<String>();
  private String pathToSpec;
  private String canonical;
  private ValidationOutcomes errors;
  private JsonObject resourceConfig;
  
  public IGKnowledgeProvider(IWorkerContext context, String pathToSpec, JsonObject igs, ValidationOutcomes errors) throws Exception {
    super();
    this.context = context;
    this.pathToSpec = pathToSpec;
    this.errors = errors;
    loadPaths(igs);
  }

  private void loadPaths(JsonObject igs) throws Exception {
    JsonElement e = igs.get("canonicalBase");
    if (e == null)
      throw new Exception("You must define a canonicalBase in the json file");
    canonical = e.getAsString();
    resourceConfig = igs.getAsJsonObject("resources");
    if (resourceConfig == null)
      throw new Exception("You must provide a list of resources in the json file");
    for (Entry<String, JsonElement> pp : resourceConfig.entrySet()) {
      if (!pp.getKey().startsWith("_")) {
        String s = pp.getKey();
        if (!s.contains("/"))
          throw new Exception("Bad Resource Identity - should have the format [Type]/[id]");
        String type = s.substring(0,  s.indexOf("/"));
        String id = s.substring(s.indexOf("/")+1); 
        try {
          ResourceType.fromCode(type);
        } catch (Exception ex) {
          throw new Exception("Bad Resource Identity - should have the format [Type]/[id] where Type is a valid resource type");
        }
        if (!id.matches(FormatUtilities.ID_REGEX))
          throw new Exception("Bad Resource Identity - should have the format [Type]/[id] where id is a valid FHIR id type");

        if (!(pp.getValue() instanceof JsonObject))
          throw new Exception("Unexpected type in resource list - must be an object");
        JsonObject o = (JsonObject) pp.getValue();
        JsonElement p = o.get("base");
        if (p == null)
          throw new Exception("You must provide a base on each path in the json file");
        if (!(p instanceof JsonPrimitive) && !((JsonPrimitive) p).isString())
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

  public void loadSpecPaths(JsonObject paths) {
    this.specPaths = paths;
    for (BaseConformance bc : context.allConformanceResources()) {
      JsonElement e = paths.get(bc.getUrl());
      if (e != null)
        bc.setUserData("path", specPath(e.getAsString()));
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

  public void findConfiguration(FetchedFile f) {
    JsonObject e = resourceConfig.getAsJsonObject(f.getType().toString()+"/"+f.getId());
    if (e == null)
      error("no configuration found for "+f.getType().toString()+"/"+f.getId());
    else 
      f.setConfig(e);
  }
  
  public void checkForPath(FetchedFile f, BaseConformance bc) {
    if (!bc.getUrl().endsWith("/"+bc.getId()))
      error("Resource id/url mismatch: "+bc.getId()+"/"+bc.getUrl());
    f.setId(bc.getId());
    if (f.getConfig() == null)
      findConfiguration(f);
    JsonObject e = f.getConfig();
    bc.setUserData("config", e);
    if (e != null) 
      bc.setUserData("path", e.get("base").getAsString());
  }

  private void error(String msg) {
    if (!msgs.contains(msg)) {
      msgs.add(msg);
      errors.getErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVARIANT, msg, IssueSeverity.ERROR));
    }
  }

  private String makeCanonical(String ref) {
    return canonical+"/"+ref;
  }

  private void brokenLinkWarning(String ref) {
    String s = "The reference "+ref+" could not be resolved";
    if (!msgs.contains(s)) {
      msgs.add(s);
      errors.getErrors().add(new ValidationMessage(Source.Publisher, IssueType.INVARIANT, s, IssueSeverity.ERROR));
    }
  }

  private String specPath(String path) {
    return pathToSpec+"/"+path;
  }

  public String getDefinitions(StructureDefinition sd) {
    JsonObject e = (JsonObject) sd.getUserData("config");
    if (e == null)
      error("No Paths for Resource: "+sd.getUrl());
    else {
      JsonElement p = e.get("defns");
      if (p == null)
        error("No Definition Path for Resource: "+sd.getUrl());
      return p.getAsString();
    }
    return "??";
  }

  // ---- overrides ---------------------------------------------------------------------------
  
  @Override
  public boolean isDatatype(String name) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    return sd != null && (sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE || sd.getKind() == StructureDefinitionKind.COMPLEXTYPE) && sd.getDerivation() == TypeDerivationRule.SPECIALIZATION;
  }  

  @Override
  public boolean isResource(String name) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    return sd != null && (sd.getKind() == StructureDefinitionKind.RESOURCE) && sd.getDerivation() == TypeDerivationRule.SPECIALIZATION;
  }

  @Override
  public boolean hasLinkFor(String name) {
    return isDatatype(name) || isResource(name);
  }

  @Override
  public String getLinkFor(String corepath, String name) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    if (sd != null && sd.hasUserData("path"))
        return sd.getUserString("path");
    brokenLinkWarning(name);
    return name+".html";
  }

  @Override
  public BindingResolution resolveBinding(ElementDefinitionBindingComponent binding) {
    BindingResolution br = new BindingResolution();
    if (!binding.hasValueSet()) {
      br.url = specPath("terminologies.html#unbound");
      br.display = "(unbound)";      
    } else if (binding.getValueSet() instanceof UriType) {
      String ref = ((UriType) binding.getValueSet()).getValue();
      if (ref.startsWith("http://hl7.org/fhir/ValueSet/v3-")) {
        br.url = specPath("v3/"+ref.substring(26)+"/index.html");
        br.display = ref.substring(26);
      } else {
        ValueSet vs = context.fetchResource(ValueSet.class, ref);
        if (vs != null) {
          br.url = vs.getUserString("path");
          br.display = vs.getName();
        } else {
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
          else
            br.display = "????";
        }
      }
    } else {
      String ref = ((Reference) binding.getValueSet()).getReference();
      if (ref.startsWith("ValueSet/")) {
        ValueSet vs = context.fetchResource(ValueSet.class, makeCanonical(ref));
        if (vs == null) {
          br.url = ref.substring(9)+".html"; // broken link, 
          br.display = ref.substring(9);
          brokenLinkWarning(ref);
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
            br.display = ref.substring(29);
            br.url = ref.substring(29)+".html";
            brokenLinkWarning(ref);
          }
        } else if (ref.startsWith("http://hl7.org/fhir/ValueSet/v3-")) {
          br.url = specPath("v3/"+ref.substring(26)+"/index.html"); 
          br.display = ref.substring(26);
        } else if (ref.startsWith("http://hl7.org/fhir/ValueSet/v2-")) {
          br.url = specPath("v2/"+ref.substring(26)+"/index.html"); 
          br.display = ref.substring(26);
        } else {
          brokenLinkWarning(ref);
          br.url = ref;
          br.display = "????";
        }
      }
    }
    return br;
  }

  @Override
  public String getLinkForProfile(StructureDefinition profile, String url) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, url);
    if (sd != null && sd.hasUserData("path"))
        return sd.getUserString("path")+"|"+sd.getName();
    brokenLinkWarning(url);
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


}
