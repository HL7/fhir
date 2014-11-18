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
 * A homogeneous material with a definite composition.
 */
public class Substance extends DomainResource {

    public static class SubstanceInstanceComponent extends BackboneElement {
        /**
         * Identifier associated with the package/container (usually a label affixed directly).
         */
        protected Identifier identifier;

        /**
         * When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
         */
        protected DateTimeType expiry;

        /**
         * The amount of the substance.
         */
        protected Quantity quantity;

        private static final long serialVersionUID = -1474380480L;

      public SubstanceInstanceComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Identifier associated with the package/container (usually a label affixed directly).)
         */
        public Identifier getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (Identifier associated with the package/container (usually a label affixed directly).)
         */
        public SubstanceInstanceComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #expiry} (When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.). This is the underlying object with id, value and extensions. The accessor "getExpiry" gives direct access to the value
         */
        public DateTimeType getExpiryElement() { 
          return this.expiry;
        }

        /**
         * @param value {@link #expiry} (When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.). This is the underlying object with id, value and extensions. The accessor "getExpiry" gives direct access to the value
         */
        public SubstanceInstanceComponent setExpiryElement(DateTimeType value) { 
          this.expiry = value;
          return this;
        }

        /**
         * @return When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
         */
        public DateAndTime getExpiry() { 
          return this.expiry == null ? null : this.expiry.getValue();
        }

        /**
         * @param value When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
         */
        public SubstanceInstanceComponent setExpiry(DateAndTime value) { 
          if (value == null)
            this.expiry = null;
          else {
            if (this.expiry == null)
              this.expiry = new DateTimeType();
            this.expiry.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #quantity} (The amount of the substance.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount of the substance.)
         */
        public SubstanceInstanceComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Identifier associated with the package/container (usually a label affixed directly).", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("expiry", "dateTime", "When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.", 0, java.lang.Integer.MAX_VALUE, expiry));
          childrenList.add(new Property("quantity", "Quantity", "The amount of the substance.", 0, java.lang.Integer.MAX_VALUE, quantity));
        }

      public SubstanceInstanceComponent copy() {
        SubstanceInstanceComponent dst = new SubstanceInstanceComponent();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.expiry = expiry == null ? null : expiry.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        return dst;
      }

  }

    public static class SubstanceIngredientComponent extends BackboneElement {
        /**
         * The amount of the ingredient in the substance - a concentration ratio.
         */
        protected Ratio quantity;

        /**
         * Another substance that is a component of this substance.
         */
        protected Reference substance;

        /**
         * The actual object that is the target of the reference (Another substance that is a component of this substance.)
         */
        protected Substance substanceTarget;

        private static final long serialVersionUID = -1783242034L;

      public SubstanceIngredientComponent() {
        super();
      }

      public SubstanceIngredientComponent(Reference substance) {
        super();
        this.substance = substance;
      }

        /**
         * @return {@link #quantity} (The amount of the ingredient in the substance - a concentration ratio.)
         */
        public Ratio getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount of the ingredient in the substance - a concentration ratio.)
         */
        public SubstanceIngredientComponent setQuantity(Ratio value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #substance} (Another substance that is a component of this substance.)
         */
        public Reference getSubstance() { 
          return this.substance;
        }

        /**
         * @param value {@link #substance} (Another substance that is a component of this substance.)
         */
        public SubstanceIngredientComponent setSubstance(Reference value) { 
          this.substance = value;
          return this;
        }

        /**
         * @return {@link #substance} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Another substance that is a component of this substance.)
         */
        public Substance getSubstanceTarget() { 
          return this.substanceTarget;
        }

        /**
         * @param value {@link #substance} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Another substance that is a component of this substance.)
         */
        public SubstanceIngredientComponent setSubstanceTarget(Substance value) { 
          this.substanceTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("quantity", "Ratio", "The amount of the ingredient in the substance - a concentration ratio.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("substance", "Reference(Substance)", "Another substance that is a component of this substance.", 0, java.lang.Integer.MAX_VALUE, substance));
        }

      public SubstanceIngredientComponent copy() {
        SubstanceIngredientComponent dst = new SubstanceIngredientComponent();
        copyValues(dst);
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.substance = substance == null ? null : substance.copy();
        return dst;
      }

  }

    /**
     * A code (or set of codes) that identify this substance.
     */
    protected CodeableConcept type;

    /**
     * A description of the substance - its appearance, handling requirements, and other usage notes.
     */
    protected StringType description;

    /**
     * Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance.
     */
    protected SubstanceInstanceComponent instance;

    /**
     * A substance can be composed of other substances.
     */
    protected List<SubstanceIngredientComponent> ingredient = new ArrayList<SubstanceIngredientComponent>();

    private static final long serialVersionUID = -1024152127L;

    public Substance() {
      super();
    }

    public Substance(CodeableConcept type) {
      super();
      this.type = type;
    }

    /**
     * @return {@link #type} (A code (or set of codes) that identify this substance.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (A code (or set of codes) that identify this substance.)
     */
    public Substance setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #description} (A description of the substance - its appearance, handling requirements, and other usage notes.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A description of the substance - its appearance, handling requirements, and other usage notes.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public Substance setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A description of the substance - its appearance, handling requirements, and other usage notes.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A description of the substance - its appearance, handling requirements, and other usage notes.
     */
    public Substance setDescription(String value) { 
      if (Utilities.noString(value))
        this.description = null;
      else {
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #instance} (Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance.)
     */
    public SubstanceInstanceComponent getInstance() { 
      return this.instance;
    }

    /**
     * @param value {@link #instance} (Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance.)
     */
    public Substance setInstance(SubstanceInstanceComponent value) { 
      this.instance = value;
      return this;
    }

    /**
     * @return {@link #ingredient} (A substance can be composed of other substances.)
     */
    public List<SubstanceIngredientComponent> getIngredient() { 
      return this.ingredient;
    }

    /**
     * @return {@link #ingredient} (A substance can be composed of other substances.)
     */
    // syntactic sugar
    public SubstanceIngredientComponent addIngredient() { //3
      SubstanceIngredientComponent t = new SubstanceIngredientComponent();
      this.ingredient.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("type", "CodeableConcept", "A code (or set of codes) that identify this substance.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("description", "string", "A description of the substance - its appearance, handling requirements, and other usage notes.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("instance", "", "Substance may be used to describe a kind of substance, or a specific package/container of the substance: an instance.", 0, java.lang.Integer.MAX_VALUE, instance));
        childrenList.add(new Property("ingredient", "", "A substance can be composed of other substances.", 0, java.lang.Integer.MAX_VALUE, ingredient));
      }

      public Substance copy() {
        Substance dst = new Substance();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.description = description == null ? null : description.copy();
        dst.instance = instance == null ? null : instance.copy();
        dst.ingredient = new ArrayList<SubstanceIngredientComponent>();
        for (SubstanceIngredientComponent i : ingredient)
          dst.ingredient.add(i.copy());
        return dst;
      }

      protected Substance typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Substance;
   }


}

