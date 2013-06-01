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

// Generated on Sat, Jun 1, 2013 09:23+1000 for FHIR v0.09

import java.util.*;

/**
 * This is primarily for identification and definition of Medication, but also covers ingredients and packaging
 */
public class Medication extends Resource {

    public enum MedicationKind {
        product, // 
        package_, // 
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

  public class MedicationKindEnumFactory implements EnumFactory {
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

    public class MedicationProductComponent extends Element {
        /**
         * Describes the form of the item.  Powder; tables; carton
         */
        private CodeableConcept form;

        /**
         * The ingredients of the medication
         */
        private List<MedicationProductIngredientComponent> ingredient = new ArrayList<MedicationProductIngredientComponent>();

        public CodeableConcept getForm() { 
          return this.form;
        }

        public void setForm(CodeableConcept value) { 
          this.form = value;
        }

        public List<MedicationProductIngredientComponent> getIngredient() { 
          return this.ingredient;
        }

  }

    public class MedicationProductIngredientComponent extends Element {
        /**
         * The actual ingredient - either a substance (simple ingredient) or another medication
         */
        private ResourceReference item;

        /**
         * Specifies how many (or how much) of the items there are in this Medication.  E.g. 250 mg per tablet
         */
        private Ratio amount;

        public ResourceReference getItem() { 
          return this.item;
        }

        public void setItem(ResourceReference value) { 
          this.item = value;
        }

        public Ratio getAmount() { 
          return this.amount;
        }

        public void setAmount(Ratio value) { 
          this.amount = value;
        }

  }

    public class MedicationPackageComponent extends Element {
        /**
         * The kind of container that this package comes as
         */
        private CodeableConcept container;

        /**
         * A set of components that go to make up the described item.
         */
        private List<MedicationPackageContentComponent> content = new ArrayList<MedicationPackageContentComponent>();

        public CodeableConcept getContainer() { 
          return this.container;
        }

        public void setContainer(CodeableConcept value) { 
          this.container = value;
        }

        public List<MedicationPackageContentComponent> getContent() { 
          return this.content;
        }

  }

    public class MedicationPackageContentComponent extends Element {
        /**
         * The product that is in the package
         */
        private ResourceReference item;

        /**
         * The amount of the product that is in the package
         */
        private Quantity amount;

        public ResourceReference getItem() { 
          return this.item;
        }

        public void setItem(ResourceReference value) { 
          this.item = value;
        }

        public Quantity getAmount() { 
          return this.amount;
        }

        public void setAmount(Quantity value) { 
          this.amount = value;
        }

  }

    /**
     * The common name of the medication
     */
    private String_ name;

    /**
     * References to codes for this medication in standard medication terminologies, drug dictionaries, etc
     */
    private CodeableConcept code;

    /**
     * Set to true if the item is attributable to a specific manufacturer (even if we don't know who that is)
     */
    private Boolean isBrand;

    /**
     * Describes the details of the manufacturer
     */
    private ResourceReference manufacturer;

    /**
     * product | package
     */
    private Enumeration<MedicationKind> kind;

    /**
     * If is a product
     */
    private MedicationProductComponent product;

    /**
     * Specifies Ingredient / Product / Package
     */
    private MedicationPackageComponent package_;

    public String_ getName() { 
      return this.name;
    }

    public void setName(String_ value) { 
      this.name = value;
    }

    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    public void setNameSimple(String value) { 
      if (value == null)
        this.name = null;
      else {
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      }
    }

    public CodeableConcept getCode() { 
      return this.code;
    }

    public void setCode(CodeableConcept value) { 
      this.code = value;
    }

    public Boolean getIsBrand() { 
      return this.isBrand;
    }

    public void setIsBrand(Boolean value) { 
      this.isBrand = value;
    }

    public boolean getIsBrandSimple() { 
      return this.isBrand == null ? null : this.isBrand.getValue();
    }

    public void setIsBrandSimple(boolean value) { 
      if (value == false)
        this.isBrand = null;
      else {
        if (this.isBrand == null)
          this.isBrand = new Boolean();
        this.isBrand.setValue(value);
      }
    }

    public ResourceReference getManufacturer() { 
      return this.manufacturer;
    }

    public void setManufacturer(ResourceReference value) { 
      this.manufacturer = value;
    }

    public Enumeration<MedicationKind> getKind() { 
      return this.kind;
    }

    public void setKind(Enumeration<MedicationKind> value) { 
      this.kind = value;
    }

    public MedicationKind getKindSimple() { 
      return this.kind == null ? null : this.kind.getValue();
    }

    public void setKindSimple(MedicationKind value) { 
      if (value == null)
        this.kind = null;
      else {
        if (this.kind == null)
          this.kind = new Enumeration<MedicationKind>();
        this.kind.setValue(value);
      }
    }

    public MedicationProductComponent getProduct() { 
      return this.product;
    }

    public void setProduct(MedicationProductComponent value) { 
      this.product = value;
    }

    public MedicationPackageComponent getPackage() { 
      return this.package_;
    }

    public void setPackage(MedicationPackageComponent value) { 
      this.package_ = value;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Medication;
   }


}

