/*******************************************************************************
 * Crown Copyright (c) 2006, 2008, Copyright (c) 2006, 2008 Kestral Computing P/L.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Kestral Computing P/L - initial implementation
 *******************************************************************************/

package org.hl7.fhir.utilities.ucum;

import java.math.BigDecimal;

public class Value {

	private String unit;
	
	private String unitUC;
	
	private BigDecimal value;
	
	private String text;

	/**
	 * @param unit
	 * @param unitUC
	 * @param value
	 */
	public Value(String unit, String unitUC, BigDecimal value) {
		super();
		this.unit = unit;
		this.unitUC = unitUC;
		this.value = value;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the unitUC
	 */
	public String getUnitUC() {
		return unitUC;
	}

	/**
	 * @param unitUC the unitUC to set
	 */
	public void setUnitUC(String unitUC) {
		this.unitUC = unitUC;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		if (value == null)
			return unit;
		return value.toEngineeringString()+unit;
	}
	
	
}
