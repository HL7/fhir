package org.hl7.fhir.utilities.ucum;

import org.hl7.fhir.utilities.Utilities;

/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Kestral Computing P/L - initial implementation
 *******************************************************************************/

/**
 * This class is defined to work around the limitations of Java Big Decimal
 * 
 * @author Grahame
 *
 */
public class Decimal {

	private int precision;
	private boolean scientific;
	private boolean negative;
	private String digits;
	private int decimal;

	private Decimal() {
		super();
	}
	
	public Decimal(String value) throws Exception {
		super();
		value = value.toLowerCase();
		if (value.contains("e"))
			setValueScientific(value);
		else
			setValueDecimal(value);
	}

	public Decimal(int i) {
		super();
		try {
	    setValueDecimal(Integer.toString(i));
    } catch (Exception e) {
    }
  }

	private void setValueDecimal(String value) throws Exception {
		//	var
		//	  dec : integer;
		//	  i : integer;
		scientific = false;
		int dec = -1;
		negative = value.startsWith("-");
		if (negative)
			value = value.substring(1);

		while (value.startsWith("0") && value.length() > 1)
			value = value.substring(1);

		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) == '.' && dec == -1)
				dec = i;
			else if (!Character.isDigit(value.charAt(i)))
				throw new Exception("'"+value+"' is not a valid decimal");
		}

		if (dec == -1) {
			precision = value.length();
			decimal = value.length();
			digits = value;
		} else if (dec == value.length() -1)
			throw new Exception("'"+value+"' is not a valid decimal");
		else {
			decimal = dec;
			if (allZeros(value, 1))
				precision = value.length() - 1;
			else
				precision = countSignificants(value);
			digits = delete(value, decimal, 1);
			if (allZeros(digits, 0))
				precision++;
			else
				while (digits.charAt(0) == '0') {
					digits = digits.substring(1);
					decimal--;
				}
		}
	}

	private boolean allZeros(String s, int start) {
		boolean result = true;
		for (int i = start; i < s.length(); i++) {
			if (s.charAt(i) != '0')
				result = false;
		}
		return result;
	}

	private int countSignificants(String value) {
		int i = value.indexOf(".");
		if (i > -1)
			value = delete(value, i, 1);
		while (value.charAt(0) == '0')
			value = value.substring(1);
		return value.length();
	}

	private String delete(String value, int offset, int length) {
		if (offset == 0)
			return value.substring(length);
		else
			return value.substring(0,  offset)+value.substring(offset+length);
	}
	
	private void setValueScientific(String value) throws Exception {
		int i = value.indexOf("e");
		String s = value.substring(0, i);
		String e = value.substring(i+1);
				
	  if (Utilities.noString(s) || s.equals("-") || !Utilities.IsDecimal(s))
	    throw new Exception("'"+value+"' is not a valid decimal (numeric)");
	  if (Utilities.noString(e) || e.equals("-") || !Utilities.IsInteger(e))
	    throw new Exception("'"+value+"' is not a valid decimal (exponent)");

	  setValueDecimal(s);
	  scientific = true;

	  // now adjust for exponent

	  if (e.charAt(0) == '-')
	    i = 1;
	  else
	    i = 0;
	  while (i < e.length()) {
	    if (!Character.isDigit(e.charAt(i)))
	      throw new Exception(""+value+"' is not a valid decimal");
	    i++;
	  }
	  i = Integer.parseInt(e);
	  decimal = decimal + i;
	}

	private String stringMultiply(char c, int i) {
	  return Utilities.padLeft("", c, i);
  }

	private String insert(String ins, String value, int offset) {
		if (offset == 0)
			return ins+value;
		else
			return value.substring(0, offset)+ins+value.substring(offset);
  }

	@Override
  public String toString() {
	  return asDecimal();
  }

	public Decimal copy() {
		Decimal result = new Decimal();
		result.precision = precision;
		result.scientific = scientific;
		result.negative = negative;
		result.digits = digits;
		result.decimal = decimal;
		return result;
	}

	public static Decimal Zero()  {
		try {
			return new Decimal("0");
		} catch (Exception e) {
			return null; // won't happen
		}
	}

	public boolean isZero() {
	  return allZeros(digits, 0);
	}
	
	public static Decimal One() {
		try {
			return new Decimal("1");
		} catch (Exception e) {
			return null; // won't happen
		}
	}

	public boolean isOne() {
	  Decimal one = One();
	  return compares(one) == 0;
	}

	public int compares(Decimal other) {
		//	  s1, s2 : AnsiString;
		if (other == null)
			return 0;

		if (this.negative && !other.negative)
			return -1;
		else if (!this.negative && other.negative)
			return 1;
		else {
			int iMax = Math.max(this.decimal, other.decimal);
			String s1 = stringMultiply('0', iMax - this.decimal+1) + this.digits;
			String s2 = stringMultiply('0', iMax - other.decimal+1) + other.digits;
			if (s1.length() < s2.length()) 
				s1 = s1 + stringMultiply('0', s2.length() - s1.length());
				else if (s2.length() < s1.length()) 
					s2 = s2 + stringMultiply('0', s1.length() - s2.length());
			int result = s1.compareTo(s2);
			if (this.negative)
				result = -result;
			return result;
		}
	}

	public boolean isWholeNumber() {
	  return !asDecimal().contains(".");
	}

	public String asDecimal() {
		String result = digits;
		if (decimal != digits.length())
			if (decimal < 0)
				result = "0."+stringMultiply('0', 0-decimal)+digits;
			else if (decimal <= result.length())
				if (decimal == 0) 
					result = "0."+result;
				else
					result = insert(".", result, decimal);
			else
				result = result + stringMultiply('0', decimal - result.length()-1);
		if (negative && !allZeros(result, 0))
			result = "-" + result;
		return result;
	}

	public int asInteger() throws Exception {
	  if (!isWholeNumber())
	    throw new Exception("Unable to represent "+toString()+" as an integer");
	  if (compares(new Decimal(Integer.MIN_VALUE)) < 0)
	  	throw new Exception("Unable to represent "+toString()+" as a signed 8 byte integer");
	  if (compares(new Decimal(Integer.MAX_VALUE)) > 0)
	  	throw new Exception("Unable to represent "+toString()+" as a signed 8 byte integer");
	  return Integer.parseInt(asDecimal());
  }

	public String asScientific() {
	  String result = digits;
	  boolean zero = allZeros(result, 0);
	  if (zero) {
	    if (precision < 2)
	      result = "0e0";
	    else
	      result = "0."+stringMultiply('0', precision-1)+"e0";
	  } else {
	    if (digits.length() > 1)
	      result = insert(".", result, 1);
	    result = result + 'e'+Integer.toString(decimal - 1);
	  }
	  if (negative && !zero)
	    result = '-' + result;
	  return result;
  }

	public Decimal trunc() {
	  if (decimal < 0)
    return Zero();

    Decimal result = copy();
    if (result.digits.length() >= result.decimal)
    	result.digits = result.digits.substring(0, result.decimal);
    if (Utilities.noString(result.digits)) {
      result.digits = "0";
      result.decimal = 1;
      result.negative = false;
    }
	  return result;
  }


	
}
