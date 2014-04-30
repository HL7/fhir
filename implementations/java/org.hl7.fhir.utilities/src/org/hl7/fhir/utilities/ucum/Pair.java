package org.hl7.fhir.utilities.ucum;

public class Pair {

	private Decimal value;
	private String code;
	
	
	/**
	 * @param value
	 * @param code
	 */
	public Pair(Decimal value, String code) {
		super();
		this.value = value;
		this.code = code;
	}
	/**
	 * @return the value
	 */
	public Decimal getValue() {
		return value;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
}
