package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.instance.model.ValueSet.FilterOperator;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;

public class ValueSetExpanderSimple implements ValueSetExpander {

	private static final int UPPER_LIMIT = 10000;// this value is arbitrary
	
  private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();
  private Map<String, ValueSet> codesystems = new HashMap<String, ValueSet>();
  private List<ValueSetExpansionContainsComponent> codes = new ArrayList<ValueSet.ValueSetExpansionContainsComponent>();
  private Map<String, ValueSetExpansionContainsComponent> map = new HashMap<String, ValueSet.ValueSetExpansionContainsComponent>();
  private ValueSet focus;

	private ValueSetExpanderFactory factory;
  
  public ValueSetExpanderSimple(Map<String, ValueSet> valuesets, Map<String, ValueSet> codesystems, ValueSetExpanderFactory factory) {
    super();
    this.valuesets = valuesets;
    this.codesystems = codesystems;
    this.factory = factory;
  }
  
  public ValueSet expand(ValueSet source) throws Exception {
    focus = source.copy();
    focus.setExpansion(new ValueSet.ValueSetExpansionComponent());
    focus.getExpansion().setTimestampSimple(Calendar.getInstance());
    
  	
    handleDefine(source);
    if (source.getCompose() != null) 
    	handleCompose(source.getCompose());

    // sanity check on value set size;
    if (map.size() > UPPER_LIMIT)
    	throw new Exception("Value set size of "+Integer.toString(map.size())+" exceeds upper limit of "+Integer.toString(UPPER_LIMIT));
    
    for (ValueSetExpansionContainsComponent c : codes) {
    	if (map.containsKey(key(c))) {
    		focus.getExpansion().getContains().add(c);
    	}
    }
    return focus;
  }

	private void handleCompose(ValueSetComposeComponent compose) throws Exception {
  	for (Uri imp : compose.getImport()) 
  		importValueSet(imp.getValue());
  	for (ConceptSetComponent inc : compose.getInclude()) 
  		includeCodes(inc);
  	for (ConceptSetComponent inc : compose.getExclude()) 
  		excludeCodes(inc);

  }

	private void importValueSet(String value) throws Exception {
	  if (value == null)
	  	throw new Exception("unable to find value set with no identity");
	  ValueSet vs = valuesets.get(value);
	  if (vs == null)
		  	throw new Exception("Unable to find imported value set "+value);
	  vs = factory.getExpander().expand(vs);
	  for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
	  	addCode(c.getSystemSimple(), c.getCodeSimple(), c.getDisplaySimple());
	  }	  
  }

	private void includeCodes(ConceptSetComponent inc) throws Exception {
    if (inc.getSystemSimple().equals("http://snomed.info/id"))
      throw new Exception("Snomed Expansion is not yet supported (which release?)");
    if (inc.getSystemSimple().equals("http://loinc.org"))
      throw new Exception("LOINC Expansion is not yet supported (todo)");
	    
	  ValueSet cs = codesystems.get(inc.getSystemSimple());
	  if (cs == null)
	  	throw new Exception("unable to find value set "+inc.getSystemSimple().toString());
	  if (inc.getCode().size() == 0 && inc.getFilter().size() == 0) {
	    // special case - add all the code system
	    for (ValueSetDefineConceptComponent def : cs.getDefine().getConcept()) {
        addCodeAndDescendents(inc.getSystemSimple(), def);
	    }
	  }
	    
	  for (Code c : inc.getCode()) {
	  	addCode(inc.getSystemSimple(), c.getValue(), getCodeDisplay(cs, c.getValue()));
	  }
	  for (ConceptSetFilterComponent fc : inc.getFilter()) {
	  	if ("concept".equals(fc.getPropertySimple()) && fc.getOpSimple() == FilterOperator.isa) {
	  		// special: all non-abstract codes in the target code system under the value
	  		ValueSetDefineConceptComponent def = getConceptForCode(cs.getDefine().getConcept(), fc.getValueSimple());
	  		if (def == null)
	  			throw new Exception("Code '"+fc.getValueSimple()+"' not found in system '"+inc.getSystemSimple()+"'");
	  		addCodeAndDescendents(inc.getSystemSimple(), def);
	  	} else
	  		throw new Exception("not done yet");
	  }
  }

	private void addCodeAndDescendents(String system, ValueSetDefineConceptComponent def) {
	  if (def.getAbstract() == null || !def.getAbstractSimple())
	  	addCode(system, def.getCodeSimple(), def.getDisplaySimple());
	  for (ValueSetDefineConceptComponent c : def.getConcept()) 
	  	addCodeAndDescendents(system, c);	  
  }

	private void excludeCodes(ConceptSetComponent inc) throws Exception {
	  ValueSet cs = codesystems.get(inc.getSystemSimple().toString());
	  if (cs == null)
	  	throw new Exception("unable to find value set "+inc.getSystemSimple().toString());
    if (inc.getCode().size() == 0 && inc.getFilter().size() == 0) {
      // special case - add all the code system
//      for (ValueSetDefineConceptComponent def : cs.getDefine().getConcept()) {
//!!!!        addCodeAndDescendents(inc.getSystemSimple(), def);
//      }
    }
      

	  for (Code c : inc.getCode()) {
	  	// we don't need to check whether the codes are valid here- they can't have gotten into this list if they aren't valid
	  	map.remove(key(inc.getSystemSimple(), c.getValue()));
	  }
	  if (inc.getFilter().size() > 0)
	  	throw new Exception("not done yet");
  }

	
	private String getCodeDisplay(ValueSet cs, String code) throws Exception {
		ValueSetDefineConceptComponent def = getConceptForCode(cs.getDefine().getConcept(), code);
		if (def == null)
			throw new Exception("Unable to find code '"+code+"' in code system "+cs.getDefine().getSystemSimple());
		return def.getDisplaySimple();
  }

	private ValueSetDefineConceptComponent getConceptForCode(List<ValueSetDefineConceptComponent> clist, String code) {
		for (ValueSetDefineConceptComponent c : clist) {
			if (code.equals(c.getCodeSimple()))
			  return c;
			ValueSetDefineConceptComponent v = getConceptForCode(c.getConcept(), code);   
			if (v != null)
			  return v;
		}
		return null;
  }
	
	private void handleDefine(ValueSet vs) {
	  if (vs.getDefine() != null) {
      // simple case: just generate the return
    	for (ValueSetDefineConceptComponent c : vs.getDefine().getConcept()) 
    		addDefinedCode(vs, vs.getDefine().getSystemSimple(), c);
   	}
  }

	private String key(ValueSetExpansionContainsComponent c) {
		return key(c.getSystemSimple(), c.getCodeSimple());
	}

	private String key(String uri, String code) {
		return "{"+uri+"}"+code;
	}

	private void addDefinedCode(ValueSet vs, String system, ValueSetDefineConceptComponent c) {
		if (c.getAbstract() == null || !c.getAbstractSimple()) {
			addCode(system, c.getCodeSimple(), c.getDisplaySimple());
		}
  	for (ValueSetDefineConceptComponent g : c.getConcept()) 
  		addDefinedCode(vs, vs.getDefine().getSystemSimple(), g);
  }

	private void addCode(String system, String code, String display) {
		ValueSetExpansionContainsComponent n = new ValueSet.ValueSetExpansionContainsComponent();
		n.setSystemSimple(system);
	  n.setCodeSimple(code);
	  n.setDisplaySimple(display);
	  String s = key(n);
	  if (!map.containsKey(s)) { 
	  	codes.add(n);
	  	map.put(s, n);
	  }
  }

  
}
