package org.hl7.fhir.instance.utils;

import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ValueSetExpander.ValueSetExpansionOutcome;

public class ValueSetExpansionCache implements ValueSetExpanderFactory {

  public class CacheAwareExpander implements ValueSetExpander {

	  @Override
	  public ValueSetExpansionOutcome expand(ValueSet source) {
	  	if (expansions.containsKey(source.getIdentifierSimple()))
	  		return expansions.get(source.getIdentifierSimple());
	  	ValueSetExpander vse = new ValueSetExpanderSimple(valuesets, codesystems, ValueSetExpansionCache.this, locator);
	  	ValueSetExpansionOutcome vso = vse.expand(source);
	  	expansions.put(source.getIdentifierSimple(), vso);
	  	return vso;
	  }
  }

	private Map<String, ValueSet> valuesets;
	private Map<String, ValueSet> codesystems;
	private Map<String, ValueSetExpansionOutcome> expansions = new HashMap<String, ValueSetExpansionOutcome>();
	private TerminologyServices locator;
	
	public ValueSetExpansionCache(Map<String, ValueSet> valuesets, Map<String, ValueSet> codesystems, TerminologyServices locator) {
    super();
    this.valuesets = valuesets;
    this.codesystems = codesystems;
    this.locator = locator;
  }
  
	@Override
	public ValueSetExpander getExpander() {
		return new CacheAwareExpander();
		// return new ValueSetExpanderSimple(valuesets, codesystems);
	}

}
