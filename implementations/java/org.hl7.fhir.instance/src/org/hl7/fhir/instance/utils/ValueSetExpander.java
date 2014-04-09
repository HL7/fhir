package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.model.ValueSet;

public interface ValueSetExpander {
  /**
   * Some value sets are just too big to expand. Instead of an expanded value set, 
   * you get back an interface that can test membership - usually on a server somewhere
   * 
   * @author Grahame
   */
	 public class ValueSetExpansionOutcome {
	   private ValueSet valueset;
	   private ValueSetChecker service;
	   public ValueSetExpansionOutcome(ValueSet valueset) {
	     super();
	     this.valueset = valueset;
	     this.service = null;
	   }
	   public ValueSetExpansionOutcome(ValueSetChecker service) {
	     super();
	     this.valueset = null;
	     this.service = service;
	   }
	   public ValueSet getValueset() {
	     return valueset;
	   }
	   public ValueSetChecker getService() {
	     return service;
	   }

  }

  public ValueSetExpansionOutcome expand(ValueSet source);
}
