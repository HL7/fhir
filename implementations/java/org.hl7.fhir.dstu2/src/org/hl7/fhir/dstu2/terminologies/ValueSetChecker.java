package org.hl7.fhir.dstu2.terminologies;

import org.hl7.fhir.dstu2.terminologies.ValueSetExpander.ETooCostly;
import org.hl7.fhir.dstu2.utils.EOperationOutcome;

public interface ValueSetChecker {

  boolean codeInValueSet(String system, String code) throws ETooCostly, EOperationOutcome, Exception;

}
