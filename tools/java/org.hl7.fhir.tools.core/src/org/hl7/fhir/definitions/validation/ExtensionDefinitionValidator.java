package org.hl7.fhir.definitions.validation;

import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.exceptions.FHIRException;

public class ExtensionDefinitionValidator {

  
  private String context;

  public ExtensionDefinitionValidator(String context) {
    this.context = context;
  }

  public void validate(StructureDefinition sd) throws FHIRException {
    checkNoValueAndExtensions(sd);    
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
      
    }
  }

}
