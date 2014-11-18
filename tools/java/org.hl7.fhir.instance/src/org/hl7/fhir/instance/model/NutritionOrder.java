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
 * A request to supply a diet, formula feeding (enteral) or oral nutritional supplement to a patient/resident.
 */
public class NutritionOrder extends DomainResource {

    public enum NutritionOrderStatus {
        REQUESTED, // TODO.
        ACTIVE, // TODO.
        INACTIVE, // TODO.
        HELD, // TODO.
        CANCELLED, // TODO.
        NULL; // added to help the parsers
        public static NutritionOrderStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return REQUESTED;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("inactive".equals(codeString))
          return INACTIVE;
        if ("held".equals(codeString))
          return HELD;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        throw new Exception("Unknown NutritionOrderStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REQUESTED: return "requested";
            case ACTIVE: return "active";
            case INACTIVE: return "inactive";
            case HELD: return "held";
            case CANCELLED: return "cancelled";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REQUESTED: return "TODO.";
            case ACTIVE: return "TODO.";
            case INACTIVE: return "TODO.";
            case HELD: return "TODO.";
            case CANCELLED: return "TODO.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REQUESTED: return "Requested";
            case ACTIVE: return "Active";
            case INACTIVE: return "Inactive";
            case HELD: return "Held";
            case CANCELLED: return "Cancelled";
            default: return "?";
          }
        }
    }

  public static class NutritionOrderStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return NutritionOrderStatus.REQUESTED;
        if ("active".equals(codeString))
          return NutritionOrderStatus.ACTIVE;
        if ("inactive".equals(codeString))
          return NutritionOrderStatus.INACTIVE;
        if ("held".equals(codeString))
          return NutritionOrderStatus.HELD;
        if ("cancelled".equals(codeString))
          return NutritionOrderStatus.CANCELLED;
        throw new Exception("Unknown NutritionOrderStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NutritionOrderStatus.REQUESTED)
        return "requested";
      if (code == NutritionOrderStatus.ACTIVE)
        return "active";
      if (code == NutritionOrderStatus.INACTIVE)
        return "inactive";
      if (code == NutritionOrderStatus.HELD)
        return "held";
      if (code == NutritionOrderStatus.CANCELLED)
        return "cancelled";
      return "?";
      }
    }

    public static class NutritionOrderItemComponent extends BackboneElement {
        /**
         * The frequency at which the diet, oral supplement or enteral formula should be given.
         */
        protected Type scheduled;

        /**
         * Indicates whether the nutrition item is  currently in effect for the patient.
         */
        protected BooleanType isInEffect;

        /**
         * Class that defines the components of an oral diet order for the patient.
         */
        protected NutritionOrderItemOralDietComponent oralDiet;

        /**
         * Class that defines the components of a supplement order for the patient.
         */
        protected NutritionOrderItemSupplementComponent supplement;

        /**
         * Class that defines the components of an enteral formula order for the patient.
         */
        protected NutritionOrderItemEnteralFormulaComponent enteralFormula;

        private static final long serialVersionUID = 2064921337L;

      public NutritionOrderItemComponent() {
        super();
      }

      public NutritionOrderItemComponent(BooleanType isInEffect) {
        super();
        this.isInEffect = isInEffect;
      }

        /**
         * @return {@link #scheduled} (The frequency at which the diet, oral supplement or enteral formula should be given.)
         */
        public Type getScheduled() { 
          return this.scheduled;
        }

        /**
         * @param value {@link #scheduled} (The frequency at which the diet, oral supplement or enteral formula should be given.)
         */
        public NutritionOrderItemComponent setScheduled(Type value) { 
          this.scheduled = value;
          return this;
        }

        /**
         * @return {@link #isInEffect} (Indicates whether the nutrition item is  currently in effect for the patient.). This is the underlying object with id, value and extensions. The accessor "getIsInEffect" gives direct access to the value
         */
        public BooleanType getIsInEffectElement() { 
          return this.isInEffect;
        }

        /**
         * @param value {@link #isInEffect} (Indicates whether the nutrition item is  currently in effect for the patient.). This is the underlying object with id, value and extensions. The accessor "getIsInEffect" gives direct access to the value
         */
        public NutritionOrderItemComponent setIsInEffectElement(BooleanType value) { 
          this.isInEffect = value;
          return this;
        }

        /**
         * @return Indicates whether the nutrition item is  currently in effect for the patient.
         */
        public boolean getIsInEffect() { 
          return this.isInEffect == null ? false : this.isInEffect.getValue();
        }

        /**
         * @param value Indicates whether the nutrition item is  currently in effect for the patient.
         */
        public NutritionOrderItemComponent setIsInEffect(boolean value) { 
            if (this.isInEffect == null)
              this.isInEffect = new BooleanType();
            this.isInEffect.setValue(value);
          return this;
        }

        /**
         * @return {@link #oralDiet} (Class that defines the components of an oral diet order for the patient.)
         */
        public NutritionOrderItemOralDietComponent getOralDiet() { 
          return this.oralDiet;
        }

        /**
         * @param value {@link #oralDiet} (Class that defines the components of an oral diet order for the patient.)
         */
        public NutritionOrderItemComponent setOralDiet(NutritionOrderItemOralDietComponent value) { 
          this.oralDiet = value;
          return this;
        }

        /**
         * @return {@link #supplement} (Class that defines the components of a supplement order for the patient.)
         */
        public NutritionOrderItemSupplementComponent getSupplement() { 
          return this.supplement;
        }

        /**
         * @param value {@link #supplement} (Class that defines the components of a supplement order for the patient.)
         */
        public NutritionOrderItemComponent setSupplement(NutritionOrderItemSupplementComponent value) { 
          this.supplement = value;
          return this;
        }

        /**
         * @return {@link #enteralFormula} (Class that defines the components of an enteral formula order for the patient.)
         */
        public NutritionOrderItemEnteralFormulaComponent getEnteralFormula() { 
          return this.enteralFormula;
        }

        /**
         * @param value {@link #enteralFormula} (Class that defines the components of an enteral formula order for the patient.)
         */
        public NutritionOrderItemComponent setEnteralFormula(NutritionOrderItemEnteralFormulaComponent value) { 
          this.enteralFormula = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("scheduled[x]", "Timing|Period", "The frequency at which the diet, oral supplement or enteral formula should be given.", 0, java.lang.Integer.MAX_VALUE, scheduled));
          childrenList.add(new Property("isInEffect", "boolean", "Indicates whether the nutrition item is  currently in effect for the patient.", 0, java.lang.Integer.MAX_VALUE, isInEffect));
          childrenList.add(new Property("oralDiet", "", "Class that defines the components of an oral diet order for the patient.", 0, java.lang.Integer.MAX_VALUE, oralDiet));
          childrenList.add(new Property("supplement", "", "Class that defines the components of a supplement order for the patient.", 0, java.lang.Integer.MAX_VALUE, supplement));
          childrenList.add(new Property("enteralFormula", "", "Class that defines the components of an enteral formula order for the patient.", 0, java.lang.Integer.MAX_VALUE, enteralFormula));
        }

      public NutritionOrderItemComponent copy() {
        NutritionOrderItemComponent dst = new NutritionOrderItemComponent();
        copyValues(dst);
        dst.scheduled = scheduled == null ? null : scheduled.copy();
        dst.isInEffect = isInEffect == null ? null : isInEffect.copy();
        dst.oralDiet = oralDiet == null ? null : oralDiet.copy();
        dst.supplement = supplement == null ? null : supplement.copy();
        dst.enteralFormula = enteralFormula == null ? null : enteralFormula.copy();
        return dst;
      }

  }

    public static class NutritionOrderItemOralDietComponent extends BackboneElement {
        /**
         * A set of one or more codes representing diets that describe what can be consumed orally (i.e., take via the mouth).
         */
        protected List<CodeableConcept> code = new ArrayList<CodeableConcept>();

        /**
         * Class that defines the details of any nutrient modifications required for the oral diet.
         */
        protected List<NutritionOrderItemOralDietNutrientsComponent> nutrients = new ArrayList<NutritionOrderItemOralDietNutrientsComponent>();

        /**
         * Class that describes any texture modifications required for the patient to safely consume various types of solid foods.
         */
        protected List<NutritionOrderItemOralDietTextureComponent> texture = new ArrayList<NutritionOrderItemOralDietTextureComponent>();

        /**
         * Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.
         */
        protected List<CodeableConcept> fluidConsistencyType = new ArrayList<CodeableConcept>();

        /**
         * A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).
         */
        protected StringType description;

        private static final long serialVersionUID = 1385056421L;

      public NutritionOrderItemOralDietComponent() {
        super();
      }

        /**
         * @return {@link #code} (A set of one or more codes representing diets that describe what can be consumed orally (i.e., take via the mouth).)
         */
        public List<CodeableConcept> getCode() { 
          return this.code;
        }

        /**
         * @return {@link #code} (A set of one or more codes representing diets that describe what can be consumed orally (i.e., take via the mouth).)
         */
    // syntactic sugar
        public CodeableConcept addCode() { //3
          CodeableConcept t = new CodeableConcept();
          this.code.add(t);
          return t;
        }

        /**
         * @return {@link #nutrients} (Class that defines the details of any nutrient modifications required for the oral diet.)
         */
        public List<NutritionOrderItemOralDietNutrientsComponent> getNutrients() { 
          return this.nutrients;
        }

        /**
         * @return {@link #nutrients} (Class that defines the details of any nutrient modifications required for the oral diet.)
         */
    // syntactic sugar
        public NutritionOrderItemOralDietNutrientsComponent addNutrients() { //3
          NutritionOrderItemOralDietNutrientsComponent t = new NutritionOrderItemOralDietNutrientsComponent();
          this.nutrients.add(t);
          return t;
        }

        /**
         * @return {@link #texture} (Class that describes any texture modifications required for the patient to safely consume various types of solid foods.)
         */
        public List<NutritionOrderItemOralDietTextureComponent> getTexture() { 
          return this.texture;
        }

        /**
         * @return {@link #texture} (Class that describes any texture modifications required for the patient to safely consume various types of solid foods.)
         */
    // syntactic sugar
        public NutritionOrderItemOralDietTextureComponent addTexture() { //3
          NutritionOrderItemOralDietTextureComponent t = new NutritionOrderItemOralDietTextureComponent();
          this.texture.add(t);
          return t;
        }

        /**
         * @return {@link #fluidConsistencyType} (Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.)
         */
        public List<CodeableConcept> getFluidConsistencyType() { 
          return this.fluidConsistencyType;
        }

        /**
         * @return {@link #fluidConsistencyType} (Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.)
         */
    // syntactic sugar
        public CodeableConcept addFluidConsistencyType() { //3
          CodeableConcept t = new CodeableConcept();
          this.fluidConsistencyType.add(t);
          return t;
        }

        /**
         * @return {@link #description} (A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public NutritionOrderItemOralDietComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).
         */
        public NutritionOrderItemOralDietComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "A set of one or more codes representing diets that describe what can be consumed orally (i.e., take via the mouth).", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("nutrients", "", "Class that defines the details of any nutrient modifications required for the oral diet.", 0, java.lang.Integer.MAX_VALUE, nutrients));
          childrenList.add(new Property("texture", "", "Class that describes any texture modifications required for the patient to safely consume various types of solid foods.", 0, java.lang.Integer.MAX_VALUE, texture));
          childrenList.add(new Property("fluidConsistencyType", "CodeableConcept", "Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.", 0, java.lang.Integer.MAX_VALUE, fluidConsistencyType));
          childrenList.add(new Property("description", "string", "A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).", 0, java.lang.Integer.MAX_VALUE, description));
        }

      public NutritionOrderItemOralDietComponent copy() {
        NutritionOrderItemOralDietComponent dst = new NutritionOrderItemOralDietComponent();
        copyValues(dst);
        dst.code = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : code)
          dst.code.add(i.copy());
        dst.nutrients = new ArrayList<NutritionOrderItemOralDietNutrientsComponent>();
        for (NutritionOrderItemOralDietNutrientsComponent i : nutrients)
          dst.nutrients.add(i.copy());
        dst.texture = new ArrayList<NutritionOrderItemOralDietTextureComponent>();
        for (NutritionOrderItemOralDietTextureComponent i : texture)
          dst.texture.add(i.copy());
        dst.fluidConsistencyType = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : fluidConsistencyType)
          dst.fluidConsistencyType.add(i.copy());
        dst.description = description == null ? null : description.copy();
        return dst;
      }

  }

    public static class NutritionOrderItemOralDietNutrientsComponent extends BackboneElement {
        /**
         * Identifies the type of nutrient that is being modified such as cabohydrate or sodium.
         */
        protected CodeableConcept modifier;

        /**
         * The quantity or range of the specified nutrient to supply.
         */
        protected Type amount;

        private static final long serialVersionUID = -1359777156L;

      public NutritionOrderItemOralDietNutrientsComponent() {
        super();
      }

        /**
         * @return {@link #modifier} (Identifies the type of nutrient that is being modified such as cabohydrate or sodium.)
         */
        public CodeableConcept getModifier() { 
          return this.modifier;
        }

        /**
         * @param value {@link #modifier} (Identifies the type of nutrient that is being modified such as cabohydrate or sodium.)
         */
        public NutritionOrderItemOralDietNutrientsComponent setModifier(CodeableConcept value) { 
          this.modifier = value;
          return this;
        }

        /**
         * @return {@link #amount} (The quantity or range of the specified nutrient to supply.)
         */
        public Type getAmount() { 
          return this.amount;
        }

        /**
         * @param value {@link #amount} (The quantity or range of the specified nutrient to supply.)
         */
        public NutritionOrderItemOralDietNutrientsComponent setAmount(Type value) { 
          this.amount = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("modifier", "CodeableConcept", "Identifies the type of nutrient that is being modified such as cabohydrate or sodium.", 0, java.lang.Integer.MAX_VALUE, modifier));
          childrenList.add(new Property("amount[x]", "Quantity|Range", "The quantity or range of the specified nutrient to supply.", 0, java.lang.Integer.MAX_VALUE, amount));
        }

      public NutritionOrderItemOralDietNutrientsComponent copy() {
        NutritionOrderItemOralDietNutrientsComponent dst = new NutritionOrderItemOralDietNutrientsComponent();
        copyValues(dst);
        dst.modifier = modifier == null ? null : modifier.copy();
        dst.amount = amount == null ? null : amount.copy();
        return dst;
      }

  }

    public static class NutritionOrderItemOralDietTextureComponent extends BackboneElement {
        /**
         * Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.
         */
        protected CodeableConcept modifier;

        /**
         * Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.
         */
        protected CodeableConcept foodType;

        private static final long serialVersionUID = -56402817L;

      public NutritionOrderItemOralDietTextureComponent() {
        super();
      }

        /**
         * @return {@link #modifier} (Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.)
         */
        public CodeableConcept getModifier() { 
          return this.modifier;
        }

        /**
         * @param value {@link #modifier} (Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.)
         */
        public NutritionOrderItemOralDietTextureComponent setModifier(CodeableConcept value) { 
          this.modifier = value;
          return this;
        }

        /**
         * @return {@link #foodType} (Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.)
         */
        public CodeableConcept getFoodType() { 
          return this.foodType;
        }

        /**
         * @param value {@link #foodType} (Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.)
         */
        public NutritionOrderItemOralDietTextureComponent setFoodType(CodeableConcept value) { 
          this.foodType = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("modifier", "CodeableConcept", "Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.", 0, java.lang.Integer.MAX_VALUE, modifier));
          childrenList.add(new Property("foodType", "CodeableConcept", "Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.", 0, java.lang.Integer.MAX_VALUE, foodType));
        }

      public NutritionOrderItemOralDietTextureComponent copy() {
        NutritionOrderItemOralDietTextureComponent dst = new NutritionOrderItemOralDietTextureComponent();
        copyValues(dst);
        dst.modifier = modifier == null ? null : modifier.copy();
        dst.foodType = foodType == null ? null : foodType.copy();
        return dst;
      }

  }

    public static class NutritionOrderItemSupplementComponent extends BackboneElement {
        /**
         * Indicates the type of nutritional supplement product required such as high protein or pediatric clear liquid supplement.
         */
        protected List<CodeableConcept> type = new ArrayList<CodeableConcept>();

        /**
         * The amount of the nutritional supplement product to provide to the patient.
         */
        protected Quantity quantity;

        /**
         * The name of the nutritional supplement product to be provided to the patient.
         */
        protected StringType name;

        private static final long serialVersionUID = -217350217L;

      public NutritionOrderItemSupplementComponent() {
        super();
      }

        /**
         * @return {@link #type} (Indicates the type of nutritional supplement product required such as high protein or pediatric clear liquid supplement.)
         */
        public List<CodeableConcept> getType() { 
          return this.type;
        }

        /**
         * @return {@link #type} (Indicates the type of nutritional supplement product required such as high protein or pediatric clear liquid supplement.)
         */
    // syntactic sugar
        public CodeableConcept addType() { //3
          CodeableConcept t = new CodeableConcept();
          this.type.add(t);
          return t;
        }

        /**
         * @return {@link #quantity} (The amount of the nutritional supplement product to provide to the patient.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount of the nutritional supplement product to provide to the patient.)
         */
        public NutritionOrderItemSupplementComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #name} (The name of the nutritional supplement product to be provided to the patient.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of the nutritional supplement product to be provided to the patient.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public NutritionOrderItemSupplementComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the nutritional supplement product to be provided to the patient.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the nutritional supplement product to be provided to the patient.
         */
        public NutritionOrderItemSupplementComponent setName(String value) { 
          if (Utilities.noString(value))
            this.name = null;
          else {
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "Indicates the type of nutritional supplement product required such as high protein or pediatric clear liquid supplement.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("quantity", "Quantity", "The amount of the nutritional supplement product to provide to the patient.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("name", "string", "The name of the nutritional supplement product to be provided to the patient.", 0, java.lang.Integer.MAX_VALUE, name));
        }

      public NutritionOrderItemSupplementComponent copy() {
        NutritionOrderItemSupplementComponent dst = new NutritionOrderItemSupplementComponent();
        copyValues(dst);
        dst.type = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : type)
          dst.type.add(i.copy());
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.name = name == null ? null : name.copy();
        return dst;
      }

  }

    public static class NutritionOrderItemEnteralFormulaComponent extends BackboneElement {
        /**
         * Indicates the type of enteral or infant formula requested such as pediatric elemental formula or.
         */
        protected CodeableConcept baseFormulaType;

        /**
         * Indicates the type of modular component such as protein, carbohydrate or fiber to be provided in addition to or mixed with the base formula.
         */
        protected List<CodeableConcept> additiveType = new ArrayList<CodeableConcept>();

        /**
         * TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.
         */
        protected List<Quantity> caloricDensity = new ArrayList<Quantity>();

        /**
         * ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***administration details including rate (ml per hour), route of adminstration, total volume.
         */
        protected List<CodeableConcept> routeofAdministration = new ArrayList<CodeableConcept>();

        /**
         * TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.
         */
        protected List<Quantity> rate = new ArrayList<Quantity>();

        /**
         * TODO.
         */
        protected StringType baseFormulaName;

        private static final long serialVersionUID = 701376846L;

      public NutritionOrderItemEnteralFormulaComponent() {
        super();
      }

        /**
         * @return {@link #baseFormulaType} (Indicates the type of enteral or infant formula requested such as pediatric elemental formula or.)
         */
        public CodeableConcept getBaseFormulaType() { 
          return this.baseFormulaType;
        }

        /**
         * @param value {@link #baseFormulaType} (Indicates the type of enteral or infant formula requested such as pediatric elemental formula or.)
         */
        public NutritionOrderItemEnteralFormulaComponent setBaseFormulaType(CodeableConcept value) { 
          this.baseFormulaType = value;
          return this;
        }

        /**
         * @return {@link #additiveType} (Indicates the type of modular component such as protein, carbohydrate or fiber to be provided in addition to or mixed with the base formula.)
         */
        public List<CodeableConcept> getAdditiveType() { 
          return this.additiveType;
        }

        /**
         * @return {@link #additiveType} (Indicates the type of modular component such as protein, carbohydrate or fiber to be provided in addition to or mixed with the base formula.)
         */
    // syntactic sugar
        public CodeableConcept addAdditiveType() { //3
          CodeableConcept t = new CodeableConcept();
          this.additiveType.add(t);
          return t;
        }

        /**
         * @return {@link #caloricDensity} (TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.)
         */
        public List<Quantity> getCaloricDensity() { 
          return this.caloricDensity;
        }

        /**
         * @return {@link #caloricDensity} (TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.)
         */
    // syntactic sugar
        public Quantity addCaloricDensity() { //3
          Quantity t = new Quantity();
          this.caloricDensity.add(t);
          return t;
        }

        /**
         * @return {@link #routeofAdministration} (***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***administration details including rate (ml per hour), route of adminstration, total volume.)
         */
        public List<CodeableConcept> getRouteofAdministration() { 
          return this.routeofAdministration;
        }

        /**
         * @return {@link #routeofAdministration} (***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***administration details including rate (ml per hour), route of adminstration, total volume.)
         */
    // syntactic sugar
        public CodeableConcept addRouteofAdministration() { //3
          CodeableConcept t = new CodeableConcept();
          this.routeofAdministration.add(t);
          return t;
        }

        /**
         * @return {@link #rate} (TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.)
         */
        public List<Quantity> getRate() { 
          return this.rate;
        }

        /**
         * @return {@link #rate} (TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.)
         */
    // syntactic sugar
        public Quantity addRate() { //3
          Quantity t = new Quantity();
          this.rate.add(t);
          return t;
        }

        /**
         * @return {@link #baseFormulaName} (TODO.). This is the underlying object with id, value and extensions. The accessor "getBaseFormulaName" gives direct access to the value
         */
        public StringType getBaseFormulaNameElement() { 
          return this.baseFormulaName;
        }

        /**
         * @param value {@link #baseFormulaName} (TODO.). This is the underlying object with id, value and extensions. The accessor "getBaseFormulaName" gives direct access to the value
         */
        public NutritionOrderItemEnteralFormulaComponent setBaseFormulaNameElement(StringType value) { 
          this.baseFormulaName = value;
          return this;
        }

        /**
         * @return TODO.
         */
        public String getBaseFormulaName() { 
          return this.baseFormulaName == null ? null : this.baseFormulaName.getValue();
        }

        /**
         * @param value TODO.
         */
        public NutritionOrderItemEnteralFormulaComponent setBaseFormulaName(String value) { 
          if (Utilities.noString(value))
            this.baseFormulaName = null;
          else {
            if (this.baseFormulaName == null)
              this.baseFormulaName = new StringType();
            this.baseFormulaName.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("baseFormulaType", "CodeableConcept", "Indicates the type of enteral or infant formula requested such as pediatric elemental formula or.", 0, java.lang.Integer.MAX_VALUE, baseFormulaType));
          childrenList.add(new Property("additiveType", "CodeableConcept", "Indicates the type of modular component such as protein, carbohydrate or fiber to be provided in addition to or mixed with the base formula.", 0, java.lang.Integer.MAX_VALUE, additiveType));
          childrenList.add(new Property("caloricDensity", "Quantity", "TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.", 0, java.lang.Integer.MAX_VALUE, caloricDensity));
          childrenList.add(new Property("routeofAdministration", "CodeableConcept", "***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***administration details including rate (ml per hour), route of adminstration, total volume.", 0, java.lang.Integer.MAX_VALUE, routeofAdministration));
          childrenList.add(new Property("rate", "Quantity", "TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.", 0, java.lang.Integer.MAX_VALUE, rate));
          childrenList.add(new Property("baseFormulaName", "string", "TODO.", 0, java.lang.Integer.MAX_VALUE, baseFormulaName));
        }

      public NutritionOrderItemEnteralFormulaComponent copy() {
        NutritionOrderItemEnteralFormulaComponent dst = new NutritionOrderItemEnteralFormulaComponent();
        copyValues(dst);
        dst.baseFormulaType = baseFormulaType == null ? null : baseFormulaType.copy();
        dst.additiveType = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : additiveType)
          dst.additiveType.add(i.copy());
        dst.caloricDensity = new ArrayList<Quantity>();
        for (Quantity i : caloricDensity)
          dst.caloricDensity.add(i.copy());
        dst.routeofAdministration = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : routeofAdministration)
          dst.routeofAdministration.add(i.copy());
        dst.rate = new ArrayList<Quantity>();
        for (Quantity i : rate)
          dst.rate.add(i.copy());
        dst.baseFormulaName = baseFormulaName == null ? null : baseFormulaName.copy();
        return dst;
      }

  }

    /**
     * The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.)
     */
    protected Patient subjectTarget;

    /**
     * The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.
     */
    protected Reference orderer;

    /**
     * The actual object that is the target of the reference (The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.)
     */
    protected Practitioner ordererTarget;

    /**
     * Identifiers assigned to this order by the order sender or by the order receiver.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * An encounter that provides additional informaton about the healthcare context in which this request is made.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (An encounter that provides additional informaton about the healthcare context in which this request is made.)
     */
    protected Encounter encounterTarget;

    /**
     * The date and time that this nutrition order was requested.
     */
    protected DateTimeType dateTime;

    /**
     * The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.
     */
    protected List<Reference> allergyIntolerance = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
     */
    protected List<AllergyIntolerance> allergyIntoleranceTarget = new ArrayList<AllergyIntolerance>();


    /**
     * This modifier is used to convey order-specific modifiers about the type of food that should be given. These can be derived from patient allergies, intolerances, or preferences such as Halal, Vegan or Kosher. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.
     */
    protected List<CodeableConcept> foodPreferenceModifier = new ArrayList<CodeableConcept>();

    /**
     * This modifier is used to convey order-specific modifiers about the type of food that should NOT be given. These can be derived from patient allergies, intolerances, or preferences such as No Red Meat, No Soy or No Wheat or  Gluten-Free. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.
     */
    protected List<CodeableConcept> excludeFoodModifier = new ArrayList<CodeableConcept>();

    /**
     * Different items that combine to make a complete description of the nutrition to be provided via oral diet, nutritional supplement and/or formula order.
     */
    protected List<NutritionOrderItemComponent> item = new ArrayList<NutritionOrderItemComponent>();

    /**
     * The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.
     */
    protected Enumeration<NutritionOrderStatus> status;

    private static final long serialVersionUID = 1103299087L;

    public NutritionOrder() {
      super();
    }

    public NutritionOrder(Reference subject, DateTimeType dateTime) {
      super();
      this.subject = subject;
      this.dateTime = dateTime;
    }

    /**
     * @return {@link #subject} (The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.)
     */
    public NutritionOrder setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.)
     */
    public NutritionOrder setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #orderer} (The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.)
     */
    public Reference getOrderer() { 
      return this.orderer;
    }

    /**
     * @param value {@link #orderer} (The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.)
     */
    public NutritionOrder setOrderer(Reference value) { 
      this.orderer = value;
      return this;
    }

    /**
     * @return {@link #orderer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.)
     */
    public Practitioner getOrdererTarget() { 
      return this.ordererTarget;
    }

    /**
     * @param value {@link #orderer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.)
     */
    public NutritionOrder setOrdererTarget(Practitioner value) { 
      this.ordererTarget = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the order sender or by the order receiver.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the order sender or by the order receiver.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #encounter} (An encounter that provides additional informaton about the healthcare context in which this request is made.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (An encounter that provides additional informaton about the healthcare context in which this request is made.)
     */
    public NutritionOrder setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (An encounter that provides additional informaton about the healthcare context in which this request is made.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (An encounter that provides additional informaton about the healthcare context in which this request is made.)
     */
    public NutritionOrder setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #dateTime} (The date and time that this nutrition order was requested.). This is the underlying object with id, value and extensions. The accessor "getDateTime" gives direct access to the value
     */
    public DateTimeType getDateTimeElement() { 
      return this.dateTime;
    }

    /**
     * @param value {@link #dateTime} (The date and time that this nutrition order was requested.). This is the underlying object with id, value and extensions. The accessor "getDateTime" gives direct access to the value
     */
    public NutritionOrder setDateTimeElement(DateTimeType value) { 
      this.dateTime = value;
      return this;
    }

    /**
     * @return The date and time that this nutrition order was requested.
     */
    public DateAndTime getDateTime() { 
      return this.dateTime == null ? null : this.dateTime.getValue();
    }

    /**
     * @param value The date and time that this nutrition order was requested.
     */
    public NutritionOrder setDateTime(DateAndTime value) { 
        if (this.dateTime == null)
          this.dateTime = new DateTimeType();
        this.dateTime.setValue(value);
      return this;
    }

    /**
     * @return {@link #allergyIntolerance} (The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
     */
    public List<Reference> getAllergyIntolerance() { 
      return this.allergyIntolerance;
    }

    /**
     * @return {@link #allergyIntolerance} (The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
     */
    // syntactic sugar
    public Reference addAllergyIntolerance() { //3
      Reference t = new Reference();
      this.allergyIntolerance.add(t);
      return t;
    }

    /**
     * @return {@link #allergyIntolerance} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
     */
    public List<AllergyIntolerance> getAllergyIntoleranceTarget() { 
      return this.allergyIntoleranceTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #allergyIntolerance} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
     */
    public AllergyIntolerance addAllergyIntoleranceTarget() { 
      AllergyIntolerance r = new AllergyIntolerance();
      this.allergyIntoleranceTarget.add(r);
      return r;
    }

    /**
     * @return {@link #foodPreferenceModifier} (This modifier is used to convey order-specific modifiers about the type of food that should be given. These can be derived from patient allergies, intolerances, or preferences such as Halal, Vegan or Kosher. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.)
     */
    public List<CodeableConcept> getFoodPreferenceModifier() { 
      return this.foodPreferenceModifier;
    }

    /**
     * @return {@link #foodPreferenceModifier} (This modifier is used to convey order-specific modifiers about the type of food that should be given. These can be derived from patient allergies, intolerances, or preferences such as Halal, Vegan or Kosher. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.)
     */
    // syntactic sugar
    public CodeableConcept addFoodPreferenceModifier() { //3
      CodeableConcept t = new CodeableConcept();
      this.foodPreferenceModifier.add(t);
      return t;
    }

    /**
     * @return {@link #excludeFoodModifier} (This modifier is used to convey order-specific modifiers about the type of food that should NOT be given. These can be derived from patient allergies, intolerances, or preferences such as No Red Meat, No Soy or No Wheat or  Gluten-Free. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.)
     */
    public List<CodeableConcept> getExcludeFoodModifier() { 
      return this.excludeFoodModifier;
    }

    /**
     * @return {@link #excludeFoodModifier} (This modifier is used to convey order-specific modifiers about the type of food that should NOT be given. These can be derived from patient allergies, intolerances, or preferences such as No Red Meat, No Soy or No Wheat or  Gluten-Free. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.)
     */
    // syntactic sugar
    public CodeableConcept addExcludeFoodModifier() { //3
      CodeableConcept t = new CodeableConcept();
      this.excludeFoodModifier.add(t);
      return t;
    }

    /**
     * @return {@link #item} (Different items that combine to make a complete description of the nutrition to be provided via oral diet, nutritional supplement and/or formula order.)
     */
    public List<NutritionOrderItemComponent> getItem() { 
      return this.item;
    }

    /**
     * @return {@link #item} (Different items that combine to make a complete description of the nutrition to be provided via oral diet, nutritional supplement and/or formula order.)
     */
    // syntactic sugar
    public NutritionOrderItemComponent addItem() { //3
      NutritionOrderItemComponent t = new NutritionOrderItemComponent();
      this.item.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<NutritionOrderStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public NutritionOrder setStatusElement(Enumeration<NutritionOrderStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.
     */
    public NutritionOrderStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.
     */
    public NutritionOrder setStatus(NutritionOrderStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<NutritionOrderStatus>();
        this.status.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("subject", "Reference(Patient)", "The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("orderer", "Reference(Practitioner)", "The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.", 0, java.lang.Integer.MAX_VALUE, orderer));
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order by the order sender or by the order receiver.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "An encounter that provides additional informaton about the healthcare context in which this request is made.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("dateTime", "dateTime", "The date and time that this nutrition order was requested.", 0, java.lang.Integer.MAX_VALUE, dateTime));
        childrenList.add(new Property("allergyIntolerance", "Reference(AllergyIntolerance)", "The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.", 0, java.lang.Integer.MAX_VALUE, allergyIntolerance));
        childrenList.add(new Property("foodPreferenceModifier", "CodeableConcept", "This modifier is used to convey order-specific modifiers about the type of food that should be given. These can be derived from patient allergies, intolerances, or preferences such as Halal, Vegan or Kosher. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.", 0, java.lang.Integer.MAX_VALUE, foodPreferenceModifier));
        childrenList.add(new Property("excludeFoodModifier", "CodeableConcept", "This modifier is used to convey order-specific modifiers about the type of food that should NOT be given. These can be derived from patient allergies, intolerances, or preferences such as No Red Meat, No Soy or No Wheat or  Gluten-Free. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.", 0, java.lang.Integer.MAX_VALUE, excludeFoodModifier));
        childrenList.add(new Property("item", "", "Different items that combine to make a complete description of the nutrition to be provided via oral diet, nutritional supplement and/or formula order.", 0, java.lang.Integer.MAX_VALUE, item));
        childrenList.add(new Property("status", "code", "The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.", 0, java.lang.Integer.MAX_VALUE, status));
      }

      public NutritionOrder copy() {
        NutritionOrder dst = new NutritionOrder();
        copyValues(dst);
        dst.subject = subject == null ? null : subject.copy();
        dst.orderer = orderer == null ? null : orderer.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.allergyIntolerance = new ArrayList<Reference>();
        for (Reference i : allergyIntolerance)
          dst.allergyIntolerance.add(i.copy());
        dst.foodPreferenceModifier = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : foodPreferenceModifier)
          dst.foodPreferenceModifier.add(i.copy());
        dst.excludeFoodModifier = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : excludeFoodModifier)
          dst.excludeFoodModifier.add(i.copy());
        dst.item = new ArrayList<NutritionOrderItemComponent>();
        for (NutritionOrderItemComponent i : item)
          dst.item.add(i.copy());
        dst.status = status == null ? null : status.copy();
        return dst;
      }

      protected NutritionOrder typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.NutritionOrder;
   }


}

