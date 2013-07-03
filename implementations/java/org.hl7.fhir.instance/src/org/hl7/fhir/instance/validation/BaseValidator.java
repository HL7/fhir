package org.hl7.fhir.instance.validation;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;

public class BaseValidator {

  protected Source source;
  
  protected boolean fail(List<ValidationMessage> errors, String type, String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.fatal));
    return b;
  }

  
  protected boolean rule(List<ValidationMessage> errors, String type, String path, boolean b, String msg) {
  	if (!b)
  		errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.error));
  	return b;
  }

  protected boolean hint(List<ValidationMessage> errors, String type, String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.information));
    return b;
  }

  protected boolean warning(List<ValidationMessage> errors, String type, String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(source, type, path, msg, IssueSeverity.warning));
    return b;
    
  }

}
