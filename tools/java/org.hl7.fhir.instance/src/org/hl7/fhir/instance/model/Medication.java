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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

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
         * Identifies a particular constituent of interest in the product.
         */
        protected List<MedicationProductIngredientComponent> ingredient = new ArrayList<MedicationProductIngredientComponent>();

        private static final long serialVersionUID = 698672741L;

      public MedicationProductComponent() {
        super();
      }

        /**
         * @return {@link #form} (Describes the form of the item.  Powder; tables; carton.)
         */
        public CodeableConcept getForm() { 
          return this.form;
        }

        /**
         * @param value {@link #form} (Describes the form of the item.  Powder; tables; carton.)
         */
        public MedicationProductComponent setForm(CodeableConcept value) { 
          this.form = value;
          return this;
        }

        /**
         * @return {@link #ingredient} (Identifies a particular constituent of interest in the product.)
         */
        public List<MedicationProductIngredientComponent> getIngredient() { 
          return this.ingredient;
        }

    // syntactic sugar
        /**
         * @return {@link #ingredient} (Identifies a particular constituent of interest in the product.)
         */
        public MedicationProductIngredientComponent addIngredient() { 
          MedicationProductIngredientComponent t = new MedicationProductIngredientComponent();
          this.ingredient.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("form", "CodeableConcept", "Describes the form of the item.  Powder; tables; carton.", 0, java.lang.Integer.MAX_VALUE, form));
          childrenList.add(new Property("ingredient", "", "Identifies a particular constituent of interest in the product.", 0, java.lang.Integer.MAX_VALUE, ingredient));
        }

      public MedicationProductComponent copy() {
        MedicationProductComponent dst = new MedicationProductComponent();
        dst.form = form == null ? null : form.copy();
        dst.ingredient = new ArrayList<MedicationProductIngredientComponent>();
        for (MedicationProductIngredientComponent i : ingredient)
          dst.ingredient.add(i.copy());
        return dst;
      }

  }

    public static class MedicationProductIngredientComponent extends BackboneElement {
        /**
         * The actual ingredient - either a substance (simple ingredient) or another medication.
         */
        protected ResourceReference item;

        /**
         * The actual object that is the target of the reference (The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        protected Resource itemTarget;

        /**
         * Specifies how many (or how much) of the items there are in this Medication.  E.g. 250 mg per tablet.
         */
        protected Ratio amount;

        private static final long serialVersionUID = 928082101L;

      public MedicationProductIngredientComponent() {
        super();
      }

      public MedicationProductIngredientComponent(ResourceReference item) {
        super();
        this.item = item;
      }

        /**
         * @return {@link #item} (The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public ResourceReference getItem() { 
          return this.item;
        }

        /**
         * @param value {@link #item} (The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public MedicationProductIngredientComponent setItem(ResourceReference value) { 
          this.item = value;
          return this;
        }

        /**
         * @return {@link #item} (The actual object that is the target of the reference. The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public Resource getItemTarget() { 
          return this.itemTarget;
        }

        /**
         * @param value {@link #item} (The actual object that is the target of the reference. The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public MedicationProductIngredientComponent setItemTarget(Resource value) { 
          this.itemTarget = value;
          return this;
        }

        /**
         * @return {@link #amount} (Specifies how many (or how much) of the items there are in this Medication.  E.g. 250 mg per tablet.)
         */
        public Ratio getAmount() { 
          return this.amount;
        }

        /**
         * @param value {@link #amount} (Specifies how many (or how much) of the items there are in this Medication.  E.g. 250 mg per tablet.)
         */
        public MedicationProductIngredientComponent setAmount(Ratio value) { 
          this.amount = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("item", "Resource(Substance|Medication)", "The actual ingredient - either a substance (simple ingredient) or another medication.", 0, java.lang.Integer.MAX_VALUE, item));
          childrenList.add(new Property("amount", "Ratio", "Specifies how many (or how much) of the items there are in this Medication.  E.g. 250 mg per tablet.", 0, java.lang.Integer.MAX_VALUE, amount));
        }

      public MedicationProductIngredientComponent copy() {
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

        private static final long serialVersionUID = -62466804L;

      public MedicationPackageComponent() {
        super();
      }

        /**
         * @return {@link #container} (The kind of container that this package comes as.)
         */
        public CodeableConcept getContainer() { 
          return this.container;
        }

        /**
         * @param value {@link #container} (The kind of container that this package comes as.)
         */
        public MedicationPackageComponent setContainer(CodeableConcept value) { 
          this.container = value;
          return this;
        }

        /**
         * @return {@link #content} (A set of components that go to make up the described item.)
         */
        public List<MedicationPackageContentComponent> getContent() { 
          return this.content;
        }

    // syntactic sugar
        /**
         * @return {@link #content} (A set of components that go to make up the described item.)
         */
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

      public MedicationPackageComponent copy() {
        MedicationPackageComponent dst = new MedicationPackageComponent();
        dst.container = container == null ? null : container.copy();
        dst.content = new ArrayList<MedicationPackageContentComponent>();
        for (MedicationPackageContentComponent i : content)
          dst.content.add(i.copy());
        return dst;
      }

  }

    public static class MedicationPackageContentComponent extends BackboneElement {
        /**
         * Identifies one of the items in the package.
         */
        protected ResourceReference item;

        /**
         * The actual object that is the target of the reference (Identifies one of the items in the package.)
         */
        protected Medication itemTarget;

        /**
         * The amount of the product that is in the package.
         */
        protected Quantity amount;

        private static final long serialVersionUID = 1971935074L;

      public MedicationPackageContentComponent() {
        super();
      }

      public MedicationPackageContentComponent(ResourceReference item) {
        super();
        this.item = item;
      }

        /**
         * @return {@link #item} (Identifies one of the items in the package.)
         */
        public ResourceReference getItem() { 
          return this.item;
        }

        /**
         * @param value {@link #item} (Identifies one of the items in the package.)
         */
        public MedicationPackageContentComponent setItem(ResourceReference value) { 
          this.item = value;
          return this;
        }

        /**
         * @return {@link #item} (The actual object that is the target of the reference. Identifies one of the items in the package.)
         */
        public Medication getItemTarget() { 
          return this.itemTarget;
        }

        /**
         * @param value {@link #item} (The actual object that is the target of the reference. Identifies one of the items in the package.)
         */
        public MedicationPackageContentComponent setItemTarget(Medication value) { 
          this.itemTarget = value;
          return this;
        }

        /**
         * @return {@link #amount} (The amount of the product that is in the package.)
         */
        public Quantity getAmount() { 
          return this.amount;
        }

        /**
         * @param value {@link #amount} (The amount of the product that is in the package.)
         */
        public MedicationPackageContentComponent setAmount(Quantity value) { 
          this.amount = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("item", "Resource(Medication)", "Identifies one of the items in the package.", 0, java.lang.Integer.MAX_VALUE, item));
          childrenList.add(new Property("amount", "Quantity", "The amount of the product that is in the package.", 0, java.lang.Integer.MAX_VALUE, amount));
        }

      public MedicationPackageContentComponent copy() {
        MedicationPackageContentComponent dst = new MedicationPackageContentComponent();
        dst.item = item == null ? null : item.copy();
        dst.amount = amount == null ? null : amount.copy();
        return dst;
      }

  }

    /**
     * The common/commercial name of the medication absent information such as strength, form, etc.  E.g. Acetaminophen, Tylenol 3, etc.  The fully coordinated name is communicated as the display of Medication.code.
     */
    protected String_ name;

    /**
     * A code (or set of codes) that identify this medication.   Usage note: This could be a standard drug code such as a drug regulator code, RxNorm code, SNOMED CT code, etc. It could also be a local formulary code, optionally with translations to the standard drug codes.
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
     * The actual object that is the target of the reference (Describes the details of the manufacturer.)
     */
    protected Organization manufacturerTarget;

    /**
     * Medications are either a single administrable product or a package that contains one or more products.
     */
    protected Enumeration<MedicationKind> kind;

    /**
     * Information that only applies to products (not packages).
     */
    protected MedicationProductComponent product;

    /**
     * Information that only applies to packages (not products).
     */
    protected MedicationPackageComponent package_;

    private static final long serialVersionUID = 1411342864L;

    public Medication() {
      super();
    }

    /**
     * @return {@link #name} (The common/commercial name of the medication absent information such as strength, form, etc.  E.g. Acetaminophen, Tylenol 3, etc.  The fully coordinated name is communicated as the display of Medication.code.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (The common/commercial name of the medication absent information such as strength, form, etc.  E.g. Acetaminophen, Tylenol 3, etc.  The fully coordinated name is communicated as the display of Medication.code.)
     */
    public Medication setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return The common/commercial name of the medication absent information such as strength, form, etc.  E.g. Acetaminophen, Tylenol 3, etc.  The fully coordinated name is communicated as the display of Medication.code.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value The common/commercial name of the medication absent information such as strength, form, etc.  E.g. Acetaminophen, Tylenol 3, etc.  The fully coordinated name is communicated as the display of Medication.code.
     */
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

    /**
     * @return {@link #code} (A code (or set of codes) that identify this medication.   Usage note: This could be a standard drug code such as a drug regulator code, RxNorm code, SNOMED CT code, etc. It could also be a local formulary code, optionally with translations to the standard drug codes.)
     */
    public CodeableConcept getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (A code (or set of codes) that identify this medication.   Usage note: This could be a standard drug code such as a drug regulator code, RxNorm code, SNOMED CT code, etc. It could also be a local formulary code, optionally with translations to the standard drug codes.)
     */
    public Medication setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    /**
     * @return {@link #isBrand} (Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is).)
     */
    public Boolean getIsBrand() { 
      return this.isBrand;
    }

    /**
     * @param value {@link #isBrand} (Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is).)
     */
    public Medication setIsBrand(Boolean value) { 
      this.isBrand = value;
      return this;
    }

    /**
     * @return Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is).
     */
    public boolean getIsBrandSimple() { 
      return this.isBrand == null ? false : this.isBrand.getValue();
    }

    /**
     * @param value Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is).
     */
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

    /**
     * @return {@link #manufacturer} (Describes the details of the manufacturer.)
     */
    public ResourceReference getManufacturer() { 
      return this.manufacturer;
    }

    /**
     * @param value {@link #manufacturer} (Describes the details of the manufacturer.)
     */
    public Medication setManufacturer(ResourceReference value) { 
      this.manufacturer = value;
      return this;
    }

    /**
     * @return {@link #manufacturer} (The actual object that is the target of the reference. Describes the details of the manufacturer.)
     */
    public Organization getManufacturerTarget() { 
      return this.manufacturerTarget;
    }

    /**
     * @param value {@link #manufacturer} (The actual object that is the target of the reference. Describes the details of the manufacturer.)
     */
    public Medication setManufacturerTarget(Organization value) { 
      this.manufacturerTarget = value;
      return this;
    }

    /**
     * @return {@link #kind} (Medications are either a single administrable product or a package that contains one or more products.)
     */
    public Enumeration<MedicationKind> getKind() { 
      return this.kind;
    }

    /**
     * @param value {@link #kind} (Medications are either a single administrable product or a package that contains one or more products.)
     */
    public Medication setKind(Enumeration<MedicationKind> value) { 
      this.kind = value;
      return this;
    }

    /**
     * @return Medications are either a single administrable product or a package that contains one or more products.
     */
    public MedicationKind getKindSimple() { 
      return this.kind == null ? null : this.kind.getValue();
    }

    /**
     * @param value Medications are either a single administrable product or a package that contains one or more products.
     */
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

    /**
     * @return {@link #product} (Information that only applies to products (not packages).)
     */
    public MedicationProductComponent getProduct() { 
      return this.product;
    }

    /**
     * @param value {@link #product} (Information that only applies to products (not packages).)
     */
    public Medication setProduct(MedicationProductComponent value) { 
      this.product = value;
      return this;
    }

    /**
     * @return {@link #package_} (Information that only applies to packages (not products).)
     */
    public MedicationPackageComponent getPackage() { 
      return this.package_;
    }

    /**
     * @param value {@link #package_} (Information that only applies to packages (not products).)
     */
    public Medication setPackage(MedicationPackageComponent value) { 
      this.package_ = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("name", "string", "The common/commercial name of the medication absent information such as strength, form, etc.  E.g. Acetaminophen, Tylenol 3, etc.  The fully coordinated name is communicated as the display of Medication.code.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("code", "CodeableConcept", "A code (or set of codes) that identify this medication.   Usage note: This could be a standard drug code such as a drug regulator code, RxNorm code, SNOMED CT code, etc. It could also be a local formulary code, optionally with translations to the standard drug codes.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("isBrand", "boolean", "Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is).", 0, java.lang.Integer.MAX_VALUE, isBrand));
        childrenList.add(new Property("manufacturer", "Resource(Organization)", "Describes the details of the manufacturer.", 0, java.lang.Integer.MAX_VALUE, manufacturer));
        childrenList.add(new Property("kind", "code", "Medications are either a single administrable product or a package that contains one or more products.", 0, java.lang.Integer.MAX_VALUE, kind));
        childrenList.add(new Property("product", "", "Information that only applies to products (not packages).", 0, java.lang.Integer.MAX_VALUE, product));
        childrenList.add(new Property("package", "", "Information that only applies to packages (not products).", 0, java.lang.Integer.MAX_VALUE, package_));
      }

      public Medication copy() {
        Medication dst = new Medication();
        dst.name = name == null ? null : name.copy();
        dst.code = code == null ? null : code.copy();
        dst.isBrand = isBrand == null ? null : isBrand.copy();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.kind = kind == null ? null : kind.copy();
        dst.product = product == null ? null : product.copy();
        dst.package_ = package_ == null ? null : package_.copy();
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

