package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.Encounter.EncounterStatusHistoryComponent;
import org.hl7.fhir.instance.utils.FHIRPathEvaluator;
import org.hl7.fhir.instance.utils.BuildToolPathEvaluator.ElementDefinitionMatch;
import org.hl7.fhir.utilities.Utilities;

public class BuildToolPathEvaluator extends FHIRPathEvaluator {

  public class ElementDefinitionMatch {
    private ElementDefinition definition;
    private String fixedType;
    public ElementDefinitionMatch(ElementDefinition definition, String fixedType) {
      super();
      this.definition = definition;
      this.fixedType = fixedType;
    }
    public ElementDefinition getDefinition() {
      return definition;
    }
    public String getFixedType() {
      return fixedType;
    }
    
  }

  private IWorkerContext context;
  
  public BuildToolPathEvaluator(IWorkerContext context) {
    super();
    this.context = context;
  }

  @Override
  protected void getChildrenByName(Base item, String name, List<Base> result) {
    throw new Error("yet to do");
  }

  @Override
  protected void getChildTypesByName(String type, String name, Set<String> result) throws Exception {
    if (context == null)
      throw new Exception("No context provided in BuildToolPathEvaluator.getChildTypesByName");
    if (Utilities.noString(type))
      throw new Exception("No type provided in BuildToolPathEvaluator.getChildTypesByName");
    if (type.equals("xhtml"))
      return;
    String url = null;
    if (type.contains(".")) {
      url = "http://hl7.org/fhir/StructureDefinition/"+type.substring(0, type.indexOf("."));
    } else {
      url = "http://hl7.org/fhir/StructureDefinition/"+type;
    }
    String tail = "";
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, url);
    if (sd == null)
      throw new Exception("Unknown type "+type); // this really is an error, because we can only get to here if the internal infrastrucgture is wrong
    List<StructureDefinition> sdl = new ArrayList<StructureDefinition>();
    ElementDefinitionMatch m = null;
    if (type.contains("."))
      m = getElementDefinition(sd, type);
    if (m != null && hasDataType(m.definition)) {
      for (TypeRefComponent t : m.definition.getType()) {
        StructureDefinition dt = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+t.getCode());
        if (dt == null)
          throw new Exception("unknown data type "+t.getCode());
        sdl.add(dt);
      }
    } else {
      sdl.add(sd);
      if (type.contains("."))
        tail = type.substring(type.indexOf("."));
    }

    for (StructureDefinition sdi : sdl) {
      String path = sdi.getSnapshot().getElement().get(0).getPath()+tail+".";
      if (name.equals("**")) {
        for (ElementDefinition ed : sdi.getSnapshot().getElement()) {
          if (ed.getPath().startsWith(path))
            for (TypeRefComponent t : ed.getType()) {
              if (t.hasCode() && t.getCodeElement().hasValue()) {
                String tn = null;
                if (t.getCode().equals("Element") || t.getCode().equals("BackboneElement"))
                  tn = ed.getPath();
                else
                  tn = t.getCode();
                if (!result.contains(tn)) {
                  result.add(tn);
                  getChildTypesByName(tn, "**", result);
                }
              }
            }
        }      
      } else if (name.equals("*")) {
        for (ElementDefinition ed : sdi.getSnapshot().getElement()) {
          if (ed.getPath().startsWith(path) && !ed.getPath().substring(path.length()).contains("."))
            for (TypeRefComponent t : ed.getType()) {
              if (t.getCode().equals("Element") || t.getCode().equals("BackboneElement"))
                result.add(ed.getPath());
              else if (t.getCode().equals("Resource"))
                result.addAll(context.getResourceNames());
              else
                result.add(t.getCode());
            }
        }
      } else {
        if (name.endsWith("*")) 
          path = sdi.getSnapshot().getElement().get(0).getPath()+tail+"."+name.substring(0, name.length()-1);
        else
          path = sdi.getSnapshot().getElement().get(0).getPath()+tail+"."+name;

        ElementDefinitionMatch ed = getElementDefinition(sdi, path);
        if (ed != null) {
          if (ed.getFixedType() != null)
            result.add(ed.getFixedType());
          else
            for (TypeRefComponent t : ed.getDefinition().getType()) {
              if (Utilities.noString(t.getCode()))
                throw new Exception("Illegal reference to primative value attribute @ "+path);

              if (t.getCode().equals("Element") || t.getCode().equals("BackboneElement"))
                result.add(path);
              else if (t.getCode().equals("Resource"))
                result.addAll(context.getResourceNames());
              else
                result.add(t.getCode());
            }
        }
      }
    }
  }

  private ElementDefinitionMatch getElementDefinition(StructureDefinition sd, String path) {
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (ed.getPath().equals(path)) {
        if (ed.hasNameReference()) {
          return getElementDefinitionByName(sd, ed.getNameReference());
        } else
          return new ElementDefinitionMatch(ed, null);
      }
      if (ed.getPath().endsWith("[x]") && path.startsWith(ed.getPath().substring(0, ed.getPath().length()-3)))
        return new ElementDefinitionMatch(ed, ed.getPath().substring(ed.getPath().length()-3));
      if (ed.hasNameReference() && path.startsWith(ed.getPath()+".")) {
        ElementDefinitionMatch m = getElementDefinitionByName(sd, ed.getNameReference());
        return getElementDefinition(sd, m.definition.getPath()+path.substring(ed.getPath().length()));
      }
    }
    return null;
  }

  private boolean hasDataType(ElementDefinition ed) {
    return ed.hasType() && !(ed.getType().get(0).getCode().equals("Element") || ed.getType().get(0).getCode().equals("BackboneElement"));
  }

  private ElementDefinitionMatch getElementDefinitionByName(StructureDefinition sd, String name) {
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (name.equals(ed.getName())) 
        return new ElementDefinitionMatch(ed, null);
    }
    return null;
  }

}
