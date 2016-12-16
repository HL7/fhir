package org.hl7.fhir.dstu3.validation;

import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.context.SimpleWorkerContext.IValidatorFactory;
import org.hl7.fhir.dstu3.utils.IResourceValidator;
import org.hl7.fhir.exceptions.FHIRException;

public class InstanceValidatorFactory implements IValidatorFactory {

  @Override
  public IResourceValidator makeValidator(IWorkerContext ctxts) throws FHIRException {
    return new InstanceValidator(ctxts);
  }

}
