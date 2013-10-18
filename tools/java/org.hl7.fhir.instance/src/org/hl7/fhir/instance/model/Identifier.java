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

// Generated on Fri, Oct 18, 2013 12:16+1100 for FHIR v0.12

/**
 * A technical identifier - identifies some entity uniquely and unambiguously.
 */
public class Identifier extends Type {

    public enum IdentifierUse {
        usual, // the identifier recommended for display and use in real-world interactions.
        official, // the identifier considered to be most trusted for the identification of this item.
        temp, // A temporary identifier.
        secondary, // An identifier that was assigned in secondary use - it serves to identify the object in a relative context, but cannot be consistently assigned to the same object again in a different context.
        Null; // added to help the parsers
        public static IdentifierUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("usual".equals(codeString))
          return usual;
        if ("official".equals(codeString))
          return official;
        if ("temp".equals(codeString))
          return temp;
        if ("secondary".equals(codeString))
          return secondary;
        throw new Exception("Unknown IdentifierUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case usual: return "usual";
            case official: return "official";
            case temp: return "temp";
            case secondary: return "secondary";
            default: return "?";
          }
        }
    }

  public class IdentifierUseEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("usual".equals(codeString))
          return IdentifierUse.usual;
        if ("official".equals(codeString))
          return IdentifierUse.official;
        if ("temp".equals(codeString))
          return IdentifierUse.temp;
        if ("secondary".equals(codeString))
          return IdentifierUse.secondary;
        throw new Exception("Unknown IdentifierUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == IdentifierUse.usual)
        return "usual";
      if (code == IdentifierUse.official)
        return "official";
      if (code == IdentifierUse.temp)
        return "temp";
      if (code == IdentifierUse.secondary)
        return "secondary";
      return "?";
      }
    }

    /**
     * usual | official | temp | secondary (If known).
     */
    protected Enumeration<IdentifierUse> use;

    /**
     * A label for the identifier that can be displayed to a human so they can recognize the identifier.
     */
    protected String_ label;

    /**
     * Establishes the namespace in which set of possible id values is unique.
     */
    protected Uri system;

    /**
     * The portion of the identifier typically displayed to the user and which is unique within the context of the system.
     */
    protected String_ value;

    /**
     * Time period during which identifier was valid for use.
     */
    protected Period period;

    /**
     * Organization that issued/manages the identifier.
     */
    protected ResourceReference assigner;

    public Enumeration<IdentifierUse> getUse() { 
      return this.use;
    }

    public void setUse(Enumeration<IdentifierUse> value) { 
      this.use = value;
    }

    public IdentifierUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    public void setUseSimple(IdentifierUse value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<IdentifierUse>();
        this.use.setValue(value);
      }
    }

    public String_ getLabel() { 
      return this.label;
    }

    public void setLabel(String_ value) { 
      this.label = value;
    }

    public String getLabelSimple() { 
      return this.label == null ? null : this.label.getValue();
    }

    public void setLabelSimple(String value) { 
      if (value == null)
        this.label = null;
      else {
        if (this.label == null)
          this.label = new String_();
        this.label.setValue(value);
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

    public String_ getValue() { 
      return this.value;
    }

    public void setValue(String_ value) { 
      this.value = value;
    }

    public String getValueSimple() { 
      return this.value == null ? null : this.value.getValue();
    }

    public void setValueSimple(String value) { 
      if (value == null)
        this.value = null;
      else {
        if (this.value == null)
          this.value = new String_();
        this.value.setValue(value);
      }
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

    public ResourceReference getAssigner() { 
      return this.assigner;
    }

    public void setAssigner(ResourceReference value) { 
      this.assigner = value;
    }

      public Identifier copy() {
        Identifier dst = new Identifier();
        dst.use = use == null ? null : use.copy();
        dst.label = label == null ? null : label.copy();
        dst.system = system == null ? null : system.copy();
        dst.value = value == null ? null : value.copy();
        dst.period = period == null ? null : period.copy();
        dst.assigner = assigner == null ? null : assigner.copy();
        return dst;
      }

      protected Identifier typedCopy() {
        return copy();
      }


}

