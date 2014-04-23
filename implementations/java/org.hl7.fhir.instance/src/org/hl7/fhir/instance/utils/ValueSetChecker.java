package org.hl7.fhir.instance.utils;

public interface ValueSetChecker {

  boolean codeInValueSet(String system, String code);

}
