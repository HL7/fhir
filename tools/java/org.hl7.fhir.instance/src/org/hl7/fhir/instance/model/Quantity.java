package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2014, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import java.util.*;

import java.math.*;
/**
 * A measured amount (or an amount that can potentially be measured). Note that measured amounts include amounts that are not precisely quantified, including amounts involving arbitrary units and floating currencies.
 */
public class Quantity extends Type {

    public enum QuantityComparator {
        lessThan, // The actual value is less than the given value.
        lessOrEqual, // The actual value is less than or equal to the given value.
        greaterOrEqual, // The actual value is greater than or equal to the given value.
        greaterThan, // The actual value is greater than the given value.
        Null; // added to help the parsers
        public static QuantityComparator fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("<".equals(codeString))
          return lessThan;
        if ("<=".equals(codeString))
          return lessOrEqual;
        if (">=".equals(codeString))
          return greaterOrEqual;
        if (">".equals(codeString))
          return greaterThan;
        throw new Exception("Unknown QuantityComparator code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case lessThan: return "<";
            case lessOrEqual: return "<=";
            case greaterOrEqual: return ">=";
            case greaterThan: return ">";
            default: return "?";
          }
        }
    }

  public static class QuantityComparatorEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("<".equals(codeString))
          return QuantityComparator.lessThan;
        if ("<=".equals(codeString))
          return QuantityComparator.lessOrEqual;
        if (">=".equals(codeString))
          return QuantityComparator.greaterOrEqual;
        if (">".equals(codeString))
          return QuantityComparator.greaterThan;
        throw new Exception("Unknown QuantityComparator code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == QuantityComparator.lessThan)
        return "<";
      if (code == QuantityComparator.lessOrEqual)
        return "<=";
      if (code == QuantityComparator.greaterOrEqual)
        return ">=";
      if (code == QuantityComparator.greaterThan)
        return ">";
      return "?";
      }
    }

    /**
     * The value of the measured amount. The value includes an implicit precision in the presentation of the value.
     */
    protected DecimalType value;

    /**
     * How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is "<" , then the real value is < stated value.
     */
    protected Enumeration<QuantityComparator> comparator;

    /**
     * A human-readable form of the units.
     */
    protected StringType units;

    /**
     * The identification of the system that provides the coded form of the unit.
     */
    protected UriType system;

    /**
     * A computer processable form of the units in some unit representation system.
     */
    protected CodeType code;

    private static final long serialVersionUID = -483422721L;

    public Quantity() {
      super();
    }

    /**
     * @return {@link #value} (The value of the measured amount. The value includes an implicit precision in the presentation of the value.)
     */
    public DecimalType getValue() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (The value of the measured amount. The value includes an implicit precision in the presentation of the value.)
     */
    public Quantity setValue(DecimalType value) { 
      this.value = value;
      return this;
    }

    /**
     * @return The value of the measured amount. The value includes an implicit precision in the presentation of the value.
     */
    public BigDecimal getValueSimple() { 
      return this.value == null ? null : this.value.getValue();
    }

    /**
     * @param value The value of the measured amount. The value includes an implicit precision in the presentation of the value.
     */
    public Quantity setValueSimple(BigDecimal value) { 
      if (value == null)
        this.value = null;
      else {
        if (this.value == null)
          this.value = new DecimalType();
        this.value.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #comparator} (How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is "<" , then the real value is < stated value.)
     */
    public Enumeration<QuantityComparator> getComparator() { 
      return this.comparator;
    }

    /**
     * @param value {@link #comparator} (How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is "<" , then the real value is < stated value.)
     */
    public Quantity setComparator(Enumeration<QuantityComparator> value) { 
      this.comparator = value;
      return this;
    }

    /**
     * @return How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is "<" , then the real value is < stated value.
     */
    public QuantityComparator getComparatorSimple() { 
      return this.comparator == null ? null : this.comparator.getValue();
    }

    /**
     * @param value How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is "<" , then the real value is < stated value.
     */
    public Quantity setComparatorSimple(QuantityComparator value) { 
      if (value == null)
        this.comparator = null;
      else {
        if (this.comparator == null)
          this.comparator = new Enumeration<QuantityComparator>();
        this.comparator.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #units} (A human-readable form of the units.)
     */
    public StringType getUnits() { 
      return this.units;
    }

    /**
     * @param value {@link #units} (A human-readable form of the units.)
     */
    public Quantity setUnits(StringType value) { 
      this.units = value;
      return this;
    }

    /**
     * @return A human-readable form of the units.
     */
    public String getUnitsSimple() { 
      return this.units == null ? null : this.units.getValue();
    }

    /**
     * @param value A human-readable form of the units.
     */
    public Quantity setUnitsSimple(String value) { 
      if (value == null)
        this.units = null;
      else {
        if (this.units == null)
          this.units = new StringType();
        this.units.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #system} (The identification of the system that provides the coded form of the unit.)
     */
    public UriType getSystem() { 
      return this.system;
    }

    /**
     * @param value {@link #system} (The identification of the system that provides the coded form of the unit.)
     */
    public Quantity setSystem(UriType value) { 
      this.system = value;
      return this;
    }

    /**
     * @return The identification of the system that provides the coded form of the unit.
     */
    public String getSystemSimple() { 
      return this.system == null ? null : this.system.getValue();
    }

    /**
     * @param value The identification of the system that provides the coded form of the unit.
     */
    public Quantity setSystemSimple(String value) { 
      if (value == null)
        this.system = null;
      else {
        if (this.system == null)
          this.system = new UriType();
        this.system.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #code} (A computer processable form of the units in some unit representation system.)
     */
    public CodeType getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (A computer processable form of the units in some unit representation system.)
     */
    public Quantity setCode(CodeType value) { 
      this.code = value;
      return this;
    }

    /**
     * @return A computer processable form of the units in some unit representation system.
     */
    public String getCodeSimple() { 
      return this.code == null ? null : this.code.getValue();
    }

    /**
     * @param value A computer processable form of the units in some unit representation system.
     */
    public Quantity setCodeSimple(String value) { 
      if (value == null)
        this.code = null;
      else {
        if (this.code == null)
          this.code = new CodeType();
        this.code.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("value", "decimal", "The value of the measured amount. The value includes an implicit precision in the presentation of the value.", 0, java.lang.Integer.MAX_VALUE, value));
        childrenList.add(new Property("comparator", "code", "How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is '<' , then the real value is < stated value.", 0, java.lang.Integer.MAX_VALUE, comparator));
        childrenList.add(new Property("units", "string", "A human-readable form of the units.", 0, java.lang.Integer.MAX_VALUE, units));
        childrenList.add(new Property("system", "uri", "The identification of the system that provides the coded form of the unit.", 0, java.lang.Integer.MAX_VALUE, system));
        childrenList.add(new Property("code", "code", "A computer processable form of the units in some unit representation system.", 0, java.lang.Integer.MAX_VALUE, code));
      }

      public Quantity copy() {
        Quantity dst = new Quantity();
        dst.value = value == null ? null : value.copy();
        dst.comparator = comparator == null ? null : comparator.copy();
        dst.units = units == null ? null : units.copy();
        dst.system = system == null ? null : system.copy();
        dst.code = code == null ? null : code.copy();
        return dst;
      }

      protected Quantity typedCopy() {
        return copy();
      }


}

