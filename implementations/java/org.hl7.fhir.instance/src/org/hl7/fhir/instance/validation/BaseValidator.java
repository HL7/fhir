package org.hl7.fhir.instance.validation;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;

public class BaseValidator {

  protected List<ValidationMessage> errors = new ArrayList<ValidationMessage>();

  protected Source source;
  
  protected boolean fail(String type, String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.fatal));
    return b;
  }

  
  protected boolean rule(String type, String path, boolean b, String msg) {
  	if (!b)
  		errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.error));
  	return b;
  }

  protected boolean hint(String type, String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.information));
    return b;
  }

  protected boolean warning(String type, String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.warning));
    return b;
    
  }

}
