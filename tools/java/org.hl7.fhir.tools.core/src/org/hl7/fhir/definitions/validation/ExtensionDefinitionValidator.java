package org.hl7.fhir.definitions.validation;

import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.Enumerations.BindingStrength;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.Utilities;

public class ExtensionDefinitionValidator {

  
  private String context;

  public ExtensionDefinitionValidator(String context) {
    this.context = context;
  }

  public void validate(StructureDefinition sd) throws FHIRException {
    checkNoValueAndExtensions(sd);  
    checkCodesHaveRequiredBinding(sd);
  }

  private void checkCodesHaveRequiredBinding(StructureDefinition sd) throws FHIRException {
    if (Utilities.existsInList(sd.getId(), "codesystem-subsumes"))
      return;
    
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (ed.getPath().startsWith("Extension.value") && !"0".equals(ed.getMax())) {
        for (TypeRefComponent tr : ed.getType()) {
          if ("code".equals(tr.getCode())) {
            if (!ed.hasBinding() || ed.getBinding().getStrength() != BindingStrength.REQUIRED)
              throw new FHIRException("Extension "+sd.getUrl()+" has an element of type 'code' which must have required binding");
          }
        }
      }
    }
  }

  private void checkNoValueAndExtensions(StructureDefinition sd) throws FHIRException {
    boolean hasValue = false;
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (ed.getPath().startsWith("Extension.value")) {
        if (!ed.getMax().equals("0")) {
          hasValue = true;
        }
      }
    }
    if (hasValue) {
      for (ElementDefinition ed : sd.getSnapshot().getElement()) {
        if (ed.getPath().equals("Extension.extension")) {
          ed.setMax("0");
        }
        if (ed.getPath().startsWith("Extension.extension.")) {
          throw new FHIRException("The extension "+sd.getUrl()+" has both value and extensions");
        }
      }
      boolean found = false;
      int ip = 0;
      for (ElementDefinition ed : sd.getDifferential().getElement()) {
        if (ed.getPath().equals("Extension.extension")) {
          ed.setMax("0");
          found = true;
        }
        if (ed.getPath().equals("Extension") || ed.getPath().equals("Extension.id"))
          ip++;
      }
      if (!found) {
        ElementDefinition ed = new ElementDefinition();
        ed.setPath("Extension.extension");
        ed.setMax("0");
        sd.getDifferential().getElement().add(ip, ed);
      }
      
    }
  }

}
