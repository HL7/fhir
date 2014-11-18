package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011+, HL7, Inc.
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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * A technical identifier - identifies some entity uniquely and unambiguously.
 */
public class Identifier extends Type {

    public enum IdentifierUse {
        USUAL, // the identifier recommended for display and use in real-world interactions.
        OFFICIAL, // the identifier considered to be most trusted for the identification of this item.
        TEMP, // A temporary identifier.
        SECONDARY, // An identifier that was assigned in secondary use - it serves to identify the object in a relative context, but cannot be consistently assigned to the same object again in a different context.
        NULL; // added to help the parsers
        public static IdentifierUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("usual".equals(codeString))
          return USUAL;
        if ("official".equals(codeString))
          return OFFICIAL;
        if ("temp".equals(codeString))
          return TEMP;
        if ("secondary".equals(codeString))
          return SECONDARY;
        throw new Exception("Unknown IdentifierUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case USUAL: return "usual";
            case OFFICIAL: return "official";
            case TEMP: return "temp";
            case SECONDARY: return "secondary";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case USUAL: return "the identifier recommended for display and use in real-world interactions.";
            case OFFICIAL: return "the identifier considered to be most trusted for the identification of this item.";
            case TEMP: return "A temporary identifier.";
            case SECONDARY: return "An identifier that was assigned in secondary use - it serves to identify the object in a relative context, but cannot be consistently assigned to the same object again in a different context.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case USUAL: return "usual";
            case OFFICIAL: return "official";
            case TEMP: return "temp";
            case SECONDARY: return "secondary";
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
          return IdentifierUse.USUAL;
        if ("official".equals(codeString))
          return IdentifierUse.OFFICIAL;
        if ("temp".equals(codeString))
          return IdentifierUse.TEMP;
        if ("secondary".equals(codeString))
          return IdentifierUse.SECONDARY;
        throw new Exception("Unknown IdentifierUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == IdentifierUse.USUAL)
        return "usual";
      if (code == IdentifierUse.OFFICIAL)
        return "official";
      if (code == IdentifierUse.TEMP)
        return "temp";
      if (code == IdentifierUse.SECONDARY)
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
    protected Reference assigner;

    /**
     * The actual object that is the target of the reference (Organization that issued/manages the identifier.)
     */
    protected Organization assignerTarget;

    private static final long serialVersionUID = 334577297L;

    public Identifier() {
      super();
    }

    /**
     * @return {@link #use} (The purpose of this identifier.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public Enumeration<IdentifierUse> getUseElement() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (The purpose of this identifier.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public Identifier setUseElement(Enumeration<IdentifierUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return The purpose of this identifier.
     */
    public IdentifierUse getUse() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value The purpose of this identifier.
     */
    public Identifier setUse(IdentifierUse value) { 
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
     * @return {@link #label} (A text string for the identifier that can be displayed to a human so they can recognize the identifier.). This is the underlying object with id, value and extensions. The accessor "getLabel" gives direct access to the value
     */
    public StringType getLabelElement() { 
      return this.label;
    }

    /**
     * @param value {@link #label} (A text string for the identifier that can be displayed to a human so they can recognize the identifier.). This is the underlying object with id, value and extensions. The accessor "getLabel" gives direct access to the value
     */
    public Identifier setLabelElement(StringType value) { 
      this.label = value;
      return this;
    }

    /**
     * @return A text string for the identifier that can be displayed to a human so they can recognize the identifier.
     */
    public String getLabel() { 
      return this.label == null ? null : this.label.getValue();
    }

    /**
     * @param value A text string for the identifier that can be displayed to a human so they can recognize the identifier.
     */
    public Identifier setLabel(String value) { 
      if (Utilities.noString(value))
        this.label = null;
      else {
        if (this.label == null)
          this.label = new StringType();
        this.label.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #system} (Establishes the namespace in which set of possible id values is unique.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
     */
    public UriType getSystemElement() { 
      return this.system;
    }

    /**
     * @param value {@link #system} (Establishes the namespace in which set of possible id values is unique.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
     */
    public Identifier setSystemElement(UriType value) { 
      this.system = value;
      return this;
    }

    /**
     * @return Establishes the namespace in which set of possible id values is unique.
     */
    public String getSystem() { 
      return this.system == null ? null : this.system.getValue();
    }

    /**
     * @param value Establishes the namespace in which set of possible id values is unique.
     */
    public Identifier setSystem(String value) { 
      if (Utilities.noString(value))
        this.system = null;
      else {
        if (this.system == null)
          this.system = new UriType();
        this.system.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #value} (The portion of the identifier typically displayed to the user and which is unique within the context of the system.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
     */
    public StringType getValueElement() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (The portion of the identifier typically displayed to the user and which is unique within the context of the system.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
     */
    public Identifier setValueElement(StringType value) { 
      this.value = value;
      return this;
    }

    /**
     * @return The portion of the identifier typically displayed to the user and which is unique within the context of the system.
     */
    public String getValue() { 
      return this.value == null ? null : this.value.getValue();
    }

    /**
     * @param value The portion of the identifier typically displayed to the user and which is unique within the context of the system.
     */
    public Identifier setValue(String value) { 
      if (Utilities.noString(value))
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
    public Reference getAssigner() { 
      return this.assigner;
    }

    /**
     * @param value {@link #assigner} (Organization that issued/manages the identifier.)
     */
    public Identifier setAssigner(Reference value) { 
      this.assigner = value;
      return this;
    }

    /**
     * @return {@link #assigner} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Organization that issued/manages the identifier.)
     */
    public Organization getAssignerTarget() { 
      return this.assignerTarget;
    }

    /**
     * @param value {@link #assigner} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Organization that issued/manages the identifier.)
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
        childrenList.add(new Property("assigner", "Reference(Organization)", "Organization that issued/manages the identifier.", 0, java.lang.Integer.MAX_VALUE, assigner));
      }

      public Identifier copy() {
        Identifier dst = new Identifier();
        copyValues(dst);
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

