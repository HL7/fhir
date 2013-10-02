package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2013, HL7, Inc.
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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11


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

  public class QuantityComparatorEnumFactory implements EnumFactory {
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
    protected Decimal value;

    /**
     * How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is "<" , then the real value is < stated value.
     */
    protected Enumeration<QuantityComparator> comparator;

    /**
     * A human-readable form of the units.
     */
    protected String_ units;

    /**
     * The identification of the system that provides the coded form of the unit.
     */
    protected Uri system;

    /**
     * A computer processable form of the units in some unit representation system.
     */
    protected Code code;

    public Decimal getValue() { 
      return this.value;
    }

    public void setValue(Decimal value) { 
      this.value = value;
    }

    public BigDecimal getValueSimple() { 
      return this.value == null ? null : this.value.getValue();
    }

    public void setValueSimple(BigDecimal value) { 
      if (value == null)
        this.value = null;
      else {
        if (this.value == null)
          this.value = new Decimal();
        this.value.setValue(value);
      }
    }

    public Enumeration<QuantityComparator> getComparator() { 
      return this.comparator;
    }

    public void setComparator(Enumeration<QuantityComparator> value) { 
      this.comparator = value;
    }

    public QuantityComparator getComparatorSimple() { 
      return this.comparator == null ? null : this.comparator.getValue();
    }

    public void setComparatorSimple(QuantityComparator value) { 
      if (value == null)
        this.comparator = null;
      else {
        if (this.comparator == null)
          this.comparator = new Enumeration<QuantityComparator>();
        this.comparator.setValue(value);
      }
    }

    public String_ getUnits() { 
      return this.units;
    }

    public void setUnits(String_ value) { 
      this.units = value;
    }

    public String getUnitsSimple() { 
      return this.units == null ? null : this.units.getValue();
    }

    public void setUnitsSimple(String value) { 
      if (value == null)
        this.units = null;
      else {
        if (this.units == null)
          this.units = new String_();
        this.units.setValue(value);
      }
    }

    public Uri getSystem() { 
      return this.system;
    }

    public void setSystem(Uri value) { 
      this.system = value;
    }

    public String getSystemSimple() { 
      return this.system == null ? null : this.system.getValue();
    }

    public void setSystemSimple(String value) { 
      if (value == null)
        this.system = null;
      else {
        if (this.system == null)
          this.system = new Uri();
        this.system.setValue(value);
      }
    }

    public Code getCode() { 
      return this.code;
    }

    public void setCode(Code value) { 
      this.code = value;
    }

    public String getCodeSimple() { 
      return this.code == null ? null : this.code.getValue();
    }

    public void setCodeSimple(String value) { 
      if (value == null)
        this.code = null;
      else {
        if (this.code == null)
          this.code = new Code();
        this.code.setValue(value);
      }
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

