package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.utils.ValueSetExpander.ETooCostly;

public interface ValueSetChecker {

  boolean codeInValueSet(String system, String code) throws ETooCostly;

}
