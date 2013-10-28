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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * Primarily used for identification and definition of Medication, but also covers ingredients and packaging.
 */
public class Medication extends Resource {

    public enum MedicationKind {
        product, // The medication is a product.
        package_, // The medication is a package - a contained group of one of more products.
        Null; // added to help the parsers
        public static MedicationKind fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("product".equals(codeString))
          return product;
        if ("package".equals(codeString))
          return package_;
        throw new Exception("Unknown MedicationKind code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case product: return "product";
            case package_: return "package";
            default: return "?";
          }
        }
    }

  public static class MedicationKindEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("product".equals(codeString))
          return MedicationKind.product;
        if ("package".equals(codeString))
          return MedicationKind.package_;
        throw new Exception("Unknown MedicationKind code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationKind.product)
        return "product";
      if (code == MedicationKind.package_)
        return "package";
      return "?";
      }
    }

    public static class MedicationProductComponent extends BackboneElement {
        /**
         * Describes the form of the item.  Powder; tables; carton.
         */
        protected CodeableConcept form;

        /**
         * The ingredients of the medication.
         */
        protected List<MedicationProductIngredientComponent> ingredient = new ArrayList<MedicationProductIngredientComponent>();

      public MedicationProductComponent() {
        super();
      }

        public CodeableConcept getForm() { 
          return this.form;
        }

        public MedicationProductComponent setForm(CodeableConcept value) { 
          this.form = value;
          return this;
        }

        public List<MedicationProductIngredientComponent> getIngredient() { 
          return this.ingredient;
        }

    // syntactic sugar
        public MedicationProductIngredientComponent addIngredient() { 
          MedicationProductIngredientComponent t = new MedicationProductIngredientComponent();
          this.ingredient.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("form", "CodeableConcept", "Describes the form of the item.  Powder; tables; carton.", 0, java.lang.Integer.MAX_VALUE, form));
          childrenList.add(new Property("ingredient", "", "The ingredients of the medication.", 0, java.lang.Integer.MAX_VALUE, ingredient));
        }

      public MedicationProductComponent copy(Medication e) {
        MedicationProductComponent dst = new MedicationProductComponent();
        dst.form = form == null ? null : form.copy();
        dst.ingredient = new ArrayList<MedicationProductIngredientComponent>();
        for (MedicationProductIngredientComponent i : ingredient)
          dst.ingredient.add(i.copy(e));
        return dst;
      }

  }

    public static class MedicationProductIngredientComponent extends BackboneElement {
        /**
         * The actual ingredient - either a substance (simple ingredient) or another medication.
         */
        protected ResourceReference item;

        /**
         * Specifies how many (or how much) of the items there are in this Medication.  E.g. 250 mg per tablet.
         */
        protected Ratio amount;

      public MedicationProductIngredientComponent() {
        super();
      }

      public MedicationProductIngredientComponent(ResourceReference item) {
        super();
        this.item = item;
      }

        public ResourceReference getItem() { 
          return this.item;
        }

        public MedicationProductIngredientComponent setItem(ResourceReference value) { 
          this.item = value;
          return this;
        }

        public Ratio getAmount() { 
          return this.amount;
        }

        public MedicationProductIngredientComponent setAmount(Ratio value) { 
          this.amount = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("item", "Resource(Substance|Medication)", "The actual ingredient - either a substance (simple ingredient) or another medication.", 0, java.lang.Integer.MAX_VALUE, item));
          childrenList.add(new Property("amount", "Ratio", "Specifies how many (or how much) of the items there are in this Medication.  E.g. 250 mg per tablet.", 0, java.lang.Integer.MAX_VALUE, amount));
        }

      public MedicationProductIngredientComponent copy(Medication e) {
        MedicationProductIngredientComponent dst = new MedicationProductIngredientComponent();
        dst.item = item == null ? null : item.copy();
        dst.amount = amount == null ? null : amount.copy();
        return dst;
      }

  }

    public static class MedicationPackageComponent extends BackboneElement {
        /**
         * The kind of container that this package comes as.
         */
        protected CodeableConcept container;

        /**
         * A set of components that go to make up the described item.
         */
        protected List<MedicationPackageContentComponent> content = new ArrayList<MedicationPackageContentComponent>();

      public MedicationPackageComponent() {
        super();
      }

        public CodeableConcept getContainer() { 
          return this.container;
        }

        public MedicationPackageComponent setContainer(CodeableConcept value) { 
          this.container = value;
          return this;
        }

        public List<MedicationPackageContentComponent> getContent() { 
          return this.content;
        }

    // syntactic sugar
        public MedicationPackageContentComponent addContent() { 
          MedicationPackageContentComponent t = new MedicationPackageContentComponent();
          this.content.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("container", "CodeableConcept", "The kind of container that this package comes as.", 0, java.lang.Integer.MAX_VALUE, container));
          childrenList.add(new Property("content", "", "A set of components that go to make up the described item.", 0, java.lang.Integer.MAX_VALUE, content));
        }

      public MedicationPackageComponent copy(Medication e) {
        MedicationPackageComponent dst = new MedicationPackageComponent();
        dst.container = container == null ? null : container.copy();
        dst.content = new ArrayList<MedicationPackageContentComponent>();
        for (MedicationPackageContentComponent i : content)
          dst.content.add(i.copy(e));
        return dst;
      }

  }

    public static class MedicationPackageContentComponent extends BackboneElement {
        /**
         * The product that is in the package.
         */
        protected ResourceReference item;

        /**
         * The amount of the product that is in the package.
         */
        protected Quantity amount;

      public MedicationPackageContentComponent() {
        super();
      }

      public MedicationPackageContentComponent(ResourceReference item) {
        super();
        this.item = item;
      }

        public ResourceReference getItem() { 
          return this.item;
        }

        public MedicationPackageContentComponent setItem(ResourceReference value) { 
          this.item = value;
          return this;
        }

        public Quantity getAmount() { 
          return this.amount;
        }

        public MedicationPackageContentComponent setAmount(Quantity value) { 
          this.amount = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("item", "Resource(Medication)", "The product that is in the package.", 0, java.lang.Integer.MAX_VALUE, item));
          childrenList.add(new Property("amount", "Quantity", "The amount of the product that is in the package.", 0, java.lang.Integer.MAX_VALUE, amount));
        }

      public MedicationPackageContentComponent copy(Medication e) {
        MedicationPackageContentComponent dst = new MedicationPackageContentComponent();
        dst.item = item == null ? null : item.copy();
        dst.amount = amount == null ? null : amount.copy();
        return dst;
      }

  }

    /**
     * The common name of the medication.
     */
    protected String_ name;

    /**
     * References to codes for this medication in standard medication terminologies, drug dictionaries, etc.
     */
    protected CodeableConcept code;

    /**
     * Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is).
     */
    protected Boolean isBrand;

    /**
     * Describes the details of the manufacturer.
     */
    protected ResourceReference manufacturer;

    /**
     * product | package.
     */
    protected Enumeration<MedicationKind> kind;

    /**
     * If is a product.
     */
    protected MedicationProductComponent product;

    /**
     * Specifies Ingredient / Product / Package.
     */
    protected MedicationPackageComponent package_;

    public Medication() {
      super();
    }

    public String_ getName() { 
      return this.name;
    }

    public Medication setName(String_ value) { 
      this.name = value;
      return this;
    }

    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    public Medication setNameSimple(String value) { 
      if (value == null)
        this.name = null;
      else {
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      }
      return this;
    }

    public CodeableConcept getCode() { 
      return this.code;
    }

    public Medication setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    public Boolean getIsBrand() { 
      return this.isBrand;
    }

    public Medication setIsBrand(Boolean value) { 
      this.isBrand = value;
      return this;
    }

    public boolean getIsBrandSimple() { 
      return this.isBrand == null ? null : this.isBrand.getValue();
    }

    public Medication setIsBrandSimple(boolean value) { 
      if (value == false)
        this.isBrand = null;
      else {
        if (this.isBrand == null)
          this.isBrand = new Boolean();
        this.isBrand.setValue(value);
      }
      return this;
    }

    public ResourceReference getManufacturer() { 
      return this.manufacturer;
    }

    public Medication setManufacturer(ResourceReference value) { 
      this.manufacturer = value;
      return this;
    }

    public Enumeration<MedicationKind> getKind() { 
      return this.kind;
    }

    public Medication setKind(Enumeration<MedicationKind> value) { 
      this.kind = value;
      return this;
    }

    public MedicationKind getKindSimple() { 
      return this.kind == null ? null : this.kind.getValue();
    }

    public Medication setKindSimple(MedicationKind value) { 
      if (value == null)
        this.kind = null;
      else {
        if (this.kind == null)
          this.kind = new Enumeration<MedicationKind>();
        this.kind.setValue(value);
      }
      return this;
    }

    public MedicationProductComponent getProduct() { 
      return this.product;
    }

    public Medication setProduct(MedicationProductComponent value) { 
      this.product = value;
      return this;
    }

    public MedicationPackageComponent getPackage() { 
      return this.package_;
    }

    public Medication setPackage(MedicationPackageComponent value) { 
      this.package_ = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("name", "string", "The common name of the medication.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("code", "CodeableConcept", "References to codes for this medication in standard medication terminologies, drug dictionaries, etc.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("isBrand", "boolean", "Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is).", 0, java.lang.Integer.MAX_VALUE, isBrand));
        childrenList.add(new Property("manufacturer", "Resource(Organization)", "Describes the details of the manufacturer.", 0, java.lang.Integer.MAX_VALUE, manufacturer));
        childrenList.add(new Property("kind", "code", "product | package.", 0, java.lang.Integer.MAX_VALUE, kind));
        childrenList.add(new Property("product", "", "If is a product.", 0, java.lang.Integer.MAX_VALUE, product));
        childrenList.add(new Property("package", "", "Specifies Ingredient / Product / Package.", 0, java.lang.Integer.MAX_VALUE, package_));
      }

      public Medication copy() {
        Medication dst = new Medication();
        dst.name = name == null ? null : name.copy();
        dst.code = code == null ? null : code.copy();
        dst.isBrand = isBrand == null ? null : isBrand.copy();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.kind = kind == null ? null : kind.copy();
        dst.product = product == null ? null : product.copy(dst);
        dst.package_ = package_ == null ? null : package_.copy(dst);
        return dst;
      }

      protected Medication typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Medication;
   }


}

