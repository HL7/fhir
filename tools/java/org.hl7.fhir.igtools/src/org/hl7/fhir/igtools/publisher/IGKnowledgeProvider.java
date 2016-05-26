package org.hl7.fhir.igtools.publisher;

import java.io.IOException;

import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.utilities.TextFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class IGKnowledgeProvider implements ProfileKnowledgeProvider {

  private IWorkerContext context;
  private JsonObject map;
  
  public IGKnowledgeProvider(IWorkerContext context) {
    super();
    this.context = context;
  }

  @Override
  public boolean isDatatype(String name) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    return sd != null && (sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE || sd.getKind() == StructureDefinitionKind.COMPLEXTYPE);
  }  

  @Override
  public boolean isResource(String name) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    return sd != null && (sd.getKind() == StructureDefinitionKind.RESOURCE);
  }

  @Override
  public boolean hasLinkFor(String name) {
    return false;
  }

  @Override
  public String getLinkFor(String name) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    if (sd != null && sd.hasUserData("path"))
        return sd.getUserString("path")+"|"+sd.getName();
    return name+".html|??";
  }

  @Override
  public BindingResolution resolveBinding(ElementDefinitionBindingComponent binding) {
    return new BindingResolution();
  }

  @Override
  public String getLinkForProfile(StructureDefinition profile, String url) {
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, url);
    if (sd != null && sd.hasUserData("path"))
        return sd.getUserString("path")+"|"+sd.getName();
    return "unknown.html|??";
  }

  public void loadMap(byte[] bs) throws IOException {
    String s = TextFile.bytesToString(bs);
    Gson g = new Gson();
    map = g.fromJson(s, JsonObject.class);
    for (BaseConformance bc : context.allConformanceResources()) {
      JsonElement e = map.get(bc.getUrl());
      if (e != null)
        bc.setUserData("path", e.getAsString());
    }
  }

  public void checkForPath(BaseConformance bc) {
    JsonElement e = map.get(bc.getUrl());
    if (e != null)
      bc.setUserData("path", e.getAsString());
  }
}
