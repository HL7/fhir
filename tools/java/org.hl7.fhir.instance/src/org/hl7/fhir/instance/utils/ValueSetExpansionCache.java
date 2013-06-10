package org.hl7.fhir.instance.utils;

import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.model.ValueSet;

public class ValueSetExpansionCache implements ValueSetExpanderFactory {

  public class CacheAwareExpander implements ValueSetExpander {

	  @Override
	  public ValueSet expand(ValueSet source) throws Exception {
	  	if (expansions.containsKey(source.getIdentifierSimple()))
	  		return expansions.get(source.getIdentifierSimple());
	  	ValueSetExpander vse = new ValueSetExpanderSimple(valuesets, codesystems, ValueSetExpansionCache.this);
	  	ValueSet vs = vse.expand(source);
	  	expansions.put(source.getIdentifierSimple(), vs);
	  	return vs;
	  }
  }

	private Map<String, ValueSet> valuesets;
	private Map<String, ValueSet> codesystems;
	private Map<String, ValueSet> expansions = new HashMap<String, ValueSet>();
	
	public ValueSetExpansionCache(Map<String, ValueSet> valuesets, Map<String, ValueSet> codesystems) {
    super();
    this.valuesets = valuesets;
    this.codesystems = codesystems;
  }
  
	@Override
	public ValueSetExpander getExpander() {
		return new CacheAwareExpander();
		// return new ValueSetExpanderSimple(valuesets, codesystems);
	}

}
