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

  public static class IdentifierUseEnumFactory implements EnumFactory {
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
     * The purpose of this identifier.
     */
    protected Enumeration<IdentifierUse> use;

    /**
     * A text string for the identifier that can be displayed to a human so they can recognize the identifier.
     */
    protected StringType label;

    /**
     * Establishes the namespace in which set of possible id values is unique.
     */
    protected UriType system;

    /**
     * The portion of the identifier typically displayed to the user and which is unique within the context of the system.
     */
    protected StringType value;

    /**
     * Time period during which identifier is/was valid for use.
     */
    protected Period period;

    /**
     * Organization that issued/manages the identifier.
     */
    protected ResourceReference assigner;

    /**
     * The actual object that is the target of the reference (Organization that issued/manages the identifier.)
     */
    protected Organization assignerTarget;

    private static final long serialVersionUID = -892620253L;

    public Identifier() {
      super();
    }

    /**
     * @return {@link #use} (The purpose of this identifier.)
     */
    public Enumeration<IdentifierUse> getUse() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (The purpose of this identifier.)
     */
    public Identifier setUse(Enumeration<IdentifierUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return The purpose of this identifier.
     */
    public IdentifierUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value The purpose of this identifier.
     */
    public Identifier setUseSimple(IdentifierUse value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<IdentifierUse>();
        this.use.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #label} (A text string for the identifier that can be displayed to a human so they can recognize the identifier.)
     */
    public StringType getLabel() { 
      return this.label;
    }

    /**
     * @param value {@link #label} (A text string for the identifier that can be displayed to a human so they can recognize the identifier.)
     */
    public Identifier setLabel(StringType value) { 
      this.label = value;
      return this;
    }

    /**
     * @return A text string for the identifier that can be displayed to a human so they can recognize the identifier.
     */
    public String getLabelSimple() { 
      return this.label == null ? null : this.label.getValue();
    }

    /**
     * @param value A text string for the identifier that can be displayed to a human so they can recognize the identifier.
     */
    public Identifier setLabelSimple(String value) { 
      if (value == null)
        this.label = null;
      else {
        if (this.label == null)
          this.label = new StringType();
        this.label.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #system} (Establishes the namespace in which set of possible id values is unique.)
     */
    public UriType getSystem() { 
      return this.system;
    }

    /**
     * @param value {@link #system} (Establishes the namespace in which set of possible id values is unique.)
     */
    public Identifier setSystem(UriType value) { 
      this.system = value;
      return this;
    }

    /**
     * @return Establishes the namespace in which set of possible id values is unique.
     */
    public String getSystemSimple() { 
      return this.system == null ? null : this.system.getValue();
    }

    /**
     * @param value Establishes the namespace in which set of possible id values is unique.
     */
    public Identifier setSystemSimple(String value) { 
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
     * @return {@link #value} (The portion of the identifier typically displayed to the user and which is unique within the context of the system.)
     */
    public StringType getValue() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (The portion of the identifier typically displayed to the user and which is unique within the context of the system.)
     */
    public Identifier setValue(StringType value) { 
      this.value = value;
      return this;
    }

    /**
     * @return The portion of the identifier typically displayed to the user and which is unique within the context of the system.
     */
    public String getValueSimple() { 
      return this.value == null ? null : this.value.getValue();
    }

    /**
     * @param value The portion of the identifier typically displayed to the user and which is unique within the context of the system.
     */
    public Identifier setValueSimple(String value) { 
      if (value == null)
        this.value = null;
      else {
        if (this.value == null)
          this.value = new StringType();
        this.value.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #period} (Time period during which identifier is/was valid for use.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (Time period during which identifier is/was valid for use.)
     */
    public Identifier setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #assigner} (Organization that issued/manages the identifier.)
     */
    public ResourceReference getAssigner() { 
      return this.assigner;
    }

    /**
     * @param value {@link #assigner} (Organization that issued/manages the identifier.)
     */
    public Identifier setAssigner(ResourceReference value) { 
      this.assigner = value;
      return this;
    }

    /**
     * @return {@link #assigner} (The actual object that is the target of the reference. Organization that issued/manages the identifier.)
     */
    public Organization getAssignerTarget() { 
      return this.assignerTarget;
    }

    /**
     * @param value {@link #assigner} (The actual object that is the target of the reference. Organization that issued/manages the identifier.)
     */
    public Identifier setAssignerTarget(Organization value) { 
      this.assignerTarget = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("use", "code", "The purpose of this identifier.", 0, java.lang.Integer.MAX_VALUE, use));
        childrenList.add(new Property("label", "string", "A text string for the identifier that can be displayed to a human so they can recognize the identifier.", 0, java.lang.Integer.MAX_VALUE, label));
        childrenList.add(new Property("system", "uri", "Establishes the namespace in which set of possible id values is unique.", 0, java.lang.Integer.MAX_VALUE, system));
        childrenList.add(new Property("value", "string", "The portion of the identifier typically displayed to the user and which is unique within the context of the system.", 0, java.lang.Integer.MAX_VALUE, value));
        childrenList.add(new Property("period", "Period", "Time period during which identifier is/was valid for use.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("assigner", "Resource(Organization)", "Organization that issued/manages the identifier.", 0, java.lang.Integer.MAX_VALUE, assigner));
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

