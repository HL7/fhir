package org.hl7.fhir.igtools.publisher;

import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities.ProfileKnowledgeProvider;

public class IGKnowledgeProvider implements ProfileKnowledgeProvider {

  private IWorkerContext context;
  
  
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
  public String getLinkFor(String typeSimple) {
    return "unknown.html";
  }

  @Override
  public BindingResolution resolveBinding(ElementDefinitionBindingComponent binding) {
    return null;
  }

  @Override
  public String getLinkForProfile(StructureDefinition profile, String url) {
    return "unknown.html";
  }

}
