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

package org.hl7.fhir.utilities.ucum.special;

import java.math.BigDecimal;

public class CelsiusHandler extends SpecialUnitHandler {

	@Override
	public String getCode() {
		return "Cel";
	}

	@Override
	public String getUnits() {
		return "K";
	}

	@Override
	public BigDecimal getValue() {
		return new BigDecimal(1);
	}

	
}
