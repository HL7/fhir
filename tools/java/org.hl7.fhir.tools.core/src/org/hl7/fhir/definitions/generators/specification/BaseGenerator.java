package org.hl7.fhir.definitions.generators.specification;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.tools.publisher.PageProcessor;

public class BaseGenerator {
  protected PageProcessor page;
  protected Definitions definitions;

  public static String getBindingLink(ElementDefn e) throws Exception {
    BindingSpecification bs = e.getBinding();
    return getBindingLink(bs);
  }
  
  public static String getBindingLink(BindingSpecification bs) throws Exception {
    if (bs.getValueSet() != null) 
      return bs.getValueSet().getUserString("path");
    else if (bs.getReference() != null)
      return bs.getReference();      
    else 
      return "(unbound)";
  }

}
