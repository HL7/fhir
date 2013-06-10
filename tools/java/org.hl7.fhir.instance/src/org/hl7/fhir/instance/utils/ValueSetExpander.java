package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.model.ValueSet;

public interface ValueSetExpander {
	 public ValueSet expand(ValueSet source) throws Exception;
}
