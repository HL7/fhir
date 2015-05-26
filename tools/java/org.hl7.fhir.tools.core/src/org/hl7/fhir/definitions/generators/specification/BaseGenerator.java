package org.hl7.fhir.definitions.generators.specification;

import java.io.File;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;

public class BaseGenerator {
  protected PageProcessor page;
  protected Definitions definitions;

  protected String getBindingLink(ElementDefn e) throws Exception {
    BindingSpecification bs = e.getBinding();
    if (bs.getValueSet() != null) 
      return bs.getValueSet().getUserString("path");
    else if (bs.getReference() != null)
      return bs.getReference();      
    else 
      return "(unbound)";
  }


}
