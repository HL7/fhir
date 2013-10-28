package org.hl7.fhir.instance.model;

import java.util.ArrayList;
import java.util.List;

public class Property {

	private String name;
	private String typeCode;
	private String definition;
	private int minCardinality;
	private int maxCardinality;
	public List<Element> values = new ArrayList<Element>();


	public Property(String name, String typeCode, String definition, int minCardinality, int maxCardinality, Element value) {
	  super();
	  this.name = name;
	  this.typeCode = typeCode;
	  this.definition = definition;
	  this.minCardinality = minCardinality;
	  this.maxCardinality = maxCardinality;
	  this.values.add(value);
  }

	public Property(String name, String typeCode, String definition, int minCardinality, int maxCardinality, List<? extends Element> values) {
	  super();
	  this.name = name;
	  this.typeCode = typeCode;
	  this.definition = definition;
	  this.minCardinality = minCardinality;
	  this.maxCardinality = maxCardinality;
	  this.values.addAll(values);
  }

	/**
	 * @return The name of this property in the FHIR Specification
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The stated type in the FHIR specification
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/** 
	 * @return The definition of this element in the FHIR spec
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * @return the minimum cardinality for this element 
	 */
	public int getMinCardinality() {
		return minCardinality;
	}

	/**
	 * @return the maximum cardinality for this element 
	 */
	public int getMaxCardinality() {
		return maxCardinality;
	}

	/**
	 * @return the actual values - will only be 1 unless maximum cardinality == MAX_INT
	 */
	public List<Element> getValues() {
		return values;
	}

	
}
