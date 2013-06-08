package org.hl7.fhir.definitions.validation;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.validation.ValidationMessage.Level;

public class BaseValidator {

  protected List<ValidationMessage> errors = new ArrayList<ValidationMessage>();

  protected boolean rule(String path, boolean b, String msg) {
  	if (!b)
  		errors.add(new ValidationMessage(path + ": " + msg, Level.Error));
  	return b;
  
  }

  protected boolean hint(String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(path + ": " + msg, Level.Hint));
    return b;
    
  }

  protected boolean warning(String path, boolean b, String msg) {
    if (!b)
      errors.add(new ValidationMessage(path + ": " + msg, Level.Warning));
    return b;
    
  }

}
