package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.instance.model.ValueSet.FilterOperator;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;

public class ValueSetExpanderSimple implements ValueSetExpander {

  private WorkerContext context;
  private List<ValueSetExpansionContainsComponent> codes = new ArrayList<ValueSet.ValueSetExpansionContainsComponent>();
  private Map<String, ValueSetExpansionContainsComponent> map = new HashMap<String, ValueSet.ValueSetExpansionContainsComponent>();
  private ValueSet focus;

	private ValueSetExpanderFactory factory;
  
  public ValueSetExpanderSimple(WorkerContext context, ValueSetExpanderFactory factory) {
    super();
    this.context = context;
    this.factory = factory;
  }
  
  @Override
  public ValueSetExpansionOutcome expand(ValueSet source) {

    try {
      focus = source.copy();
      focus.setExpansion(new ValueSet.ValueSetExpansionComponent());
      focus.getExpansion().setTimestampSimple(DateAndTime.now());


      handleDefine(source);
      if (source.getCompose() != null) 
        handleCompose(source.getCompose());

      for (ValueSetExpansionContainsComponent c : codes) {
        if (map.containsKey(key(c))) {
          focus.getExpansion().getContains().add(c);
        }
      }
      return new ValueSetExpansionOutcome(focus, null);
    } catch (Exception e) {
      // well, we couldn't expand, so we'll return an interface to a checker that can check membership of the set
      // that might fail too, but it might not, later.
      return new ValueSetExpansionOutcome(new ValueSetCheckerSimple(source, factory, context), e.getMessage());
    }
  }

	private void handleCompose(ValueSetComposeComponent compose) throws Exception {
  	for (UriType imp : compose.getImport()) 
  		importValueSet(imp.getValue());
  	for (ConceptSetComponent inc : compose.getInclude()) 
  		includeCodes(inc);
  	for (ConceptSetComponent inc : compose.getExclude()) 
  		excludeCodes(inc);

  }

	private void importValueSet(String value) throws Exception {
	  if (value == null)
	  	throw new Exception("unable to find value set with no identity");
	  ValueSet vs = context.getValueSets().get(value).getResource();
	  if (vs == null)
			throw new Exception("Unable to find imported value set "+value);
	  ValueSetExpansionOutcome vso = factory.getExpander().expand(vs);
	  if (vso.getService() != null)
      throw new Exception("Unable to expand imported value set "+value);
	  for (ValueSetExpansionContainsComponent c : vso.getValueset().getExpansion().getContains()) {
	  	addCode(c.getSystemSimple(), c.getCodeSimple(), c.getDisplaySimple());
	  }	  
  }

	private void includeCodes(ConceptSetComponent inc) throws Exception {
	  if (context.getTerminologyServices() != null && context.getTerminologyServices().supportsSystem(inc.getSystemSimple())) {
        addCodes(context.getTerminologyServices().expandVS(inc));
      return;
	  }
	    
	  AtomEntry<ValueSet> ae = context.getCodeSystems().get(inc.getSystemSimple());
	  if (ae == null)
      throw new Exception("unable to find code system "+inc.getSystemSimple().toString());
    ValueSet cs = ae.getResource();
	  if (inc.getCode().size() == 0 && inc.getFilter().size() == 0) {
	    // special case - add all the code system
	    for (ValueSetDefineConceptComponent def : cs.getDefine().getConcept()) {
        addCodeAndDescendents(inc.getSystemSimple(), def);
	    }
	  }
	    
	  for (CodeType c : inc.getCode()) {
	  	addCode(inc.getSystemSimple(), c.getValue(), getCodeDisplay(cs, c.getValue()));
	  }
	  if (inc.getFilter().size() > 1)
	    throw new Exception("Multiple filters not handled yet"); // need to and them, and this isn't done yet. But this shouldn't arise in non loinc and snomed value sets
    if (inc.getFilter().size() == 1) {
	    ConceptSetFilterComponent fc = inc.getFilter().get(0);
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

	private void addCodes(List<ValueSetExpansionContainsComponent> expand) throws Exception {
	  if (expand.size() > 500) 
	    throw new Exception("Too many codes to display (>"+Integer.toString(expand.size())+")");
    for (ValueSetExpansionContainsComponent c : expand) {
      addCode(c.getSystemSimple(), c.getCodeSimple(), c.getDisplaySimple());
    }   
  }

	private void addCodeAndDescendents(String system, ValueSetDefineConceptComponent def) {
		if (!ToolingExtensions.hasDeprecated(def)) {  
			if (def.getAbstract() == null || !def.getAbstractSimple())
				addCode(system, def.getCodeSimple(), def.getDisplaySimple());
			for (ValueSetDefineConceptComponent c : def.getConcept()) 
				addCodeAndDescendents(system, c);
		}
  }

	private void excludeCodes(ConceptSetComponent inc) throws Exception {
	  ValueSet cs = context.getCodeSystems().get(inc.getSystemSimple().toString()).getResource();
	  if (cs == null)
	  	throw new Exception("unable to find value set "+inc.getSystemSimple().toString());
    if (inc.getCode().size() == 0 && inc.getFilter().size() == 0) {
      // special case - add all the code system
//      for (ValueSetDefineConceptComponent def : cs.getDefine().getConcept()) {
//!!!!        addCodeAndDescendents(inc.getSystemSimple(), def);
//      }
    }
      

	  for (CodeType c : inc.getCode()) {
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
		if (!ToolingExtensions.hasDeprecated(c)) { 

			if (c.getAbstract() == null || !c.getAbstractSimple()) {
				addCode(system, c.getCodeSimple(), c.getDisplaySimple());
			}
			for (ValueSetDefineConceptComponent g : c.getConcept()) 
				addDefinedCode(vs, vs.getDefine().getSystemSimple(), g);
		}
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
