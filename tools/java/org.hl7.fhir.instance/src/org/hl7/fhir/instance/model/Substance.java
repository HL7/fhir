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

// Generated on Sun, Dec 1, 2013 22:52+1100 for FHIR v0.12

import java.util.*;

/**
 * A homogeneous material with a definite composition used in healthcare.
 */
public class Substance extends Resource {

    public static class SubstanceInstanceComponent extends BackboneElement {
        /**
         * Identifier associated with the package/container (usually a label affixed directly).
         */
        protected Identifier identifier;

        /**
         * When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
         */
        protected DateTime expiry;

        /**
         * The amount of the substance.
         */
        protected Quantity quantity;

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
         * @return {@link #expiry} (When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.)
         */
        public DateTime getExpiry() { 
          return this.expiry;
        }

        /**
         * @param value {@link #expiry} (When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.)
         */
        public SubstanceInstanceComponent setExpiry(DateTime value) { 
          this.expiry = value;
          return this;
        }

        /**
         * @return When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
         */
        public String getExpirySimple() { 
          return this.expiry == null ? null : this.expiry.getValue();
        }

        /**
         * @param value When the substance is no longer valid to use. For some substances, a single arbitrary date is used for expiry.
         */
        public SubstanceInstanceComponent setExpirySimple(String value) { 
          if (value == null)
            this.expiry = null;
          else {
            if (this.expiry == null)
              this.expiry = new DateTime();
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

      public SubstanceInstanceComponent copy(Substance e) {
        SubstanceInstanceComponent dst = new SubstanceInstanceComponent();
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
        protected ResourceReference substance;

      public SubstanceIngredientComponent() {
        super();
      }

      public SubstanceIngredientComponent(ResourceReference substance) {
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
        public ResourceReference getSubstance() { 
          return this.substance;
        }

        /**
         * @param value {@link #substance} (Another substance that is a component of this substance.)
         */
        public SubstanceIngredientComponent setSubstance(ResourceReference value) { 
          this.substance = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("quantity", "Ratio", "The amount of the ingredient in the substance - a concentration ratio.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("substance", "Resource(Substance)", "Another substance that is a component of this substance.", 0, java.lang.Integer.MAX_VALUE, substance));
        }

      public SubstanceIngredientComponent copy(Substance e) {
        SubstanceIngredientComponent dst = new SubstanceIngredientComponent();
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
     * A description of the substance - it's appearance, handling requirements, and other usage notes.
     */
    protected String_ description;

    /**
     * Don't know.
     */
    protected CodeableConcept status;

    /**
     * Substance may be used to desribe a kind of substance, or a specific package/container of the substance: an instance.
     */
    protected SubstanceInstanceComponent instance;

    /**
     * A substance can be composed of other substances.
     */
    protected List<SubstanceIngredientComponent> ingredient = new ArrayList<SubstanceIngredientComponent>();

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
     * @return {@link #description} (A description of the substance - it's appearance, handling requirements, and other usage notes.)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A description of the substance - it's appearance, handling requirements, and other usage notes.)
     */
    public Substance setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A description of the substance - it's appearance, handling requirements, and other usage notes.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A description of the substance - it's appearance, handling requirements, and other usage notes.
     */
    public Substance setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (Don't know.)
     */
    public CodeableConcept getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Don't know.)
     */
    public Substance setStatus(CodeableConcept value) { 
      this.status = value;
      return this;
    }

    /**
     * @return {@link #instance} (Substance may be used to desribe a kind of substance, or a specific package/container of the substance: an instance.)
     */
    public SubstanceInstanceComponent getInstance() { 
      return this.instance;
    }

    /**
     * @param value {@link #instance} (Substance may be used to desribe a kind of substance, or a specific package/container of the substance: an instance.)
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

    // syntactic sugar
    /**
     * @return {@link #ingredient} (A substance can be composed of other substances.)
     */
    public SubstanceIngredientComponent addIngredient() { 
      SubstanceIngredientComponent t = new SubstanceIngredientComponent();
      this.ingredient.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("type", "CodeableConcept", "A code (or set of codes) that identify this substance.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("description", "string", "A description of the substance - it's appearance, handling requirements, and other usage notes.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("status", "CodeableConcept", "Don't know.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("instance", "", "Substance may be used to desribe a kind of substance, or a specific package/container of the substance: an instance.", 0, java.lang.Integer.MAX_VALUE, instance));
        childrenList.add(new Property("ingredient", "", "A substance can be composed of other substances.", 0, java.lang.Integer.MAX_VALUE, ingredient));
      }

      public Substance copy() {
        Substance dst = new Substance();
        dst.type = type == null ? null : type.copy();
        dst.description = description == null ? null : description.copy();
        dst.status = status == null ? null : status.copy();
        dst.instance = instance == null ? null : instance.copy(dst);
        dst.ingredient = new ArrayList<SubstanceIngredientComponent>();
        for (SubstanceIngredientComponent i : ingredient)
          dst.ingredient.add(i.copy(dst));
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

