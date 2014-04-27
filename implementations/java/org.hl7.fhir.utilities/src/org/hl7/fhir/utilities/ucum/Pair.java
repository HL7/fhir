package org.hl7.fhir.utilities.ucum;

import java.math.BigDecimal;

public class Pair {

	private BigDecimal value;
	private String code;
	/**
	 * @param value
	 * @param code
	 */
	public Pair(BigDecimal value, String code) {
		super();
		this.value = value;
		this.code = code;
	}
	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
}
