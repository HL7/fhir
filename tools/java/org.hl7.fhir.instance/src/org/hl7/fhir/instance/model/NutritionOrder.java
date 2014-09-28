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

// Generated on Mon, Sep 29, 2014 07:39+1000 for FHIR v0.3.0

import java.util.*;

/**
 * A request to supply a diet, formula feeding (enteral) or oral nutritional supplement to a patient/resident.
 */
public class NutritionOrder extends Resource {

    public enum NutritionOrderStatus {
        requested, // TODO.
        active, // TODO.
        inactive, // TODO.
        held, // TODO.
        cancelled, // TODO.
        Null; // added to help the parsers
        public static NutritionOrderStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return requested;
        if ("active".equals(codeString))
          return active;
        if ("inactive".equals(codeString))
          return inactive;
        if ("held".equals(codeString))
          return held;
        if ("cancelled".equals(codeString))
          return cancelled;
        throw new Exception("Unknown NutritionOrderStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case requested: return "requested";
            case active: return "active";
            case inactive: return "inactive";
            case held: return "held";
            case cancelled: return "cancelled";
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
          return NutritionOrderStatus.requested;
        if ("active".equals(codeString))
          return NutritionOrderStatus.active;
        if ("inactive".equals(codeString))
          return NutritionOrderStatus.inactive;
        if ("held".equals(codeString))
          return NutritionOrderStatus.held;
        if ("cancelled".equals(codeString))
          return NutritionOrderStatus.cancelled;
        throw new Exception("Unknown NutritionOrderStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NutritionOrderStatus.requested)
        return "requested";
      if (code == NutritionOrderStatus.active)
        return "active";
      if (code == NutritionOrderStatus.inactive)
        return "inactive";
      if (code == NutritionOrderStatus.held)
        return "held";
      if (code == NutritionOrderStatus.cancelled)
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
         * @return {@link #isInEffect} (Indicates whether the nutrition item is  currently in effect for the patient.)
         */
        public BooleanType getIsInEffect() { 
          return this.isInEffect;
        }

        /**
         * @param value {@link #isInEffect} (Indicates whether the nutrition item is  currently in effect for the patient.)
         */
        public NutritionOrderItemComponent setIsInEffect(BooleanType value) { 
          this.isInEffect = value;
          return this;
        }

        /**
         * @return Indicates whether the nutrition item is  currently in effect for the patient.
         */
        public boolean getIsInEffectSimple() { 
          return this.isInEffect == null ? false : this.isInEffect.getValue();
        }

        /**
         * @param value Indicates whether the nutrition item is  currently in effect for the patient.
         */
        public NutritionOrderItemComponent setIsInEffectSimple(boolean value) { 
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
         * Identifies the type of nutrient that is being modified such as cabohydrate or sodium.
         */
        protected List<CodeableConcept> nutrientModifier = new ArrayList<CodeableConcept>();

        /**
         * The quantity or range of the specified nutrient to supply.
         */
        protected Type nutrientAmount;

        /**
         * Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.
         */
        protected List<CodeableConcept> textureModifier = new ArrayList<CodeableConcept>();

        /**
         * Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.
         */
        protected List<CodeableConcept> foodType = new ArrayList<CodeableConcept>();

        /**
         * Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.
         */
        protected List<CodeableConcept> fluidConsistencyType = new ArrayList<CodeableConcept>();

        /**
         * A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).
         */
        protected StringType description;

        private static final long serialVersionUID = 12996418L;

      public NutritionOrderItemOralDietComponent() {
        super();
      }

        /**
         * @return {@link #code} (A set of one or more codes representing diets that describe what can be consumed orally (i.e., take via the mouth).)
         */
        public List<CodeableConcept> getCode() { 
          return this.code;
        }

    // syntactic sugar
        /**
         * @return {@link #code} (A set of one or more codes representing diets that describe what can be consumed orally (i.e., take via the mouth).)
         */
        public CodeableConcept addCode() { 
          CodeableConcept t = new CodeableConcept();
          this.code.add(t);
          return t;
        }

        /**
         * @return {@link #nutrientModifier} (Identifies the type of nutrient that is being modified such as cabohydrate or sodium.)
         */
        public List<CodeableConcept> getNutrientModifier() { 
          return this.nutrientModifier;
        }

    // syntactic sugar
        /**
         * @return {@link #nutrientModifier} (Identifies the type of nutrient that is being modified such as cabohydrate or sodium.)
         */
        public CodeableConcept addNutrientModifier() { 
          CodeableConcept t = new CodeableConcept();
          this.nutrientModifier.add(t);
          return t;
        }

        /**
         * @return {@link #nutrientAmount} (The quantity or range of the specified nutrient to supply.)
         */
        public Type getNutrientAmount() { 
          return this.nutrientAmount;
        }

        /**
         * @param value {@link #nutrientAmount} (The quantity or range of the specified nutrient to supply.)
         */
        public NutritionOrderItemOralDietComponent setNutrientAmount(Type value) { 
          this.nutrientAmount = value;
          return this;
        }

        /**
         * @return {@link #textureModifier} (Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.)
         */
        public List<CodeableConcept> getTextureModifier() { 
          return this.textureModifier;
        }

    // syntactic sugar
        /**
         * @return {@link #textureModifier} (Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.)
         */
        public CodeableConcept addTextureModifier() { 
          CodeableConcept t = new CodeableConcept();
          this.textureModifier.add(t);
          return t;
        }

        /**
         * @return {@link #foodType} (Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.)
         */
        public List<CodeableConcept> getFoodType() { 
          return this.foodType;
        }

    // syntactic sugar
        /**
         * @return {@link #foodType} (Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.)
         */
        public CodeableConcept addFoodType() { 
          CodeableConcept t = new CodeableConcept();
          this.foodType.add(t);
          return t;
        }

        /**
         * @return {@link #fluidConsistencyType} (Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.)
         */
        public List<CodeableConcept> getFluidConsistencyType() { 
          return this.fluidConsistencyType;
        }

    // syntactic sugar
        /**
         * @return {@link #fluidConsistencyType} (Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.)
         */
        public CodeableConcept addFluidConsistencyType() { 
          CodeableConcept t = new CodeableConcept();
          this.fluidConsistencyType.add(t);
          return t;
        }

        /**
         * @return {@link #description} (A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).)
         */
        public StringType getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).)
         */
        public NutritionOrderItemOralDietComponent setDescription(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).
         */
        public NutritionOrderItemOralDietComponent setDescriptionSimple(String value) { 
          if (value == null)
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
          childrenList.add(new Property("nutrientModifier", "CodeableConcept", "Identifies the type of nutrient that is being modified such as cabohydrate or sodium.", 0, java.lang.Integer.MAX_VALUE, nutrientModifier));
          childrenList.add(new Property("nutrientAmount[x]", "Quantity|Range", "The quantity or range of the specified nutrient to supply.", 0, java.lang.Integer.MAX_VALUE, nutrientAmount));
          childrenList.add(new Property("textureModifier", "CodeableConcept", "Identifies any texture modifications (for solid foods) that should be made, e.g. easy to chew, chopped, ground, pureed.", 0, java.lang.Integer.MAX_VALUE, textureModifier));
          childrenList.add(new Property("foodType", "CodeableConcept", "Indicates what specific type of food (e.g., meats) the texture modification applies to or may apply to all foods in the diet.", 0, java.lang.Integer.MAX_VALUE, foodType));
          childrenList.add(new Property("fluidConsistencyType", "CodeableConcept", "Identifies the required consistency (e.g., honey-thick, nectar-thick, thin, thickened.) of liquids or fluids served to the patient.", 0, java.lang.Integer.MAX_VALUE, fluidConsistencyType));
          childrenList.add(new Property("description", "string", "A descriptive name of the required diets that describe what can be consumed orally (i.e., take via the mouth).", 0, java.lang.Integer.MAX_VALUE, description));
        }

      public NutritionOrderItemOralDietComponent copy() {
        NutritionOrderItemOralDietComponent dst = new NutritionOrderItemOralDietComponent();
        dst.code = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : code)
          dst.code.add(i.copy());
        dst.nutrientModifier = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : nutrientModifier)
          dst.nutrientModifier.add(i.copy());
        dst.nutrientAmount = nutrientAmount == null ? null : nutrientAmount.copy();
        dst.textureModifier = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : textureModifier)
          dst.textureModifier.add(i.copy());
        dst.foodType = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : foodType)
          dst.foodType.add(i.copy());
        dst.fluidConsistencyType = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : fluidConsistencyType)
          dst.fluidConsistencyType.add(i.copy());
        dst.description = description == null ? null : description.copy();
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

    // syntactic sugar
        /**
         * @return {@link #type} (Indicates the type of nutritional supplement product required such as high protein or pediatric clear liquid supplement.)
         */
        public CodeableConcept addType() { 
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
         * @return {@link #name} (The name of the nutritional supplement product to be provided to the patient.)
         */
        public StringType getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of the nutritional supplement product to be provided to the patient.)
         */
        public NutritionOrderItemSupplementComponent setName(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the nutritional supplement product to be provided to the patient.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the nutritional supplement product to be provided to the patient.
         */
        public NutritionOrderItemSupplementComponent setNameSimple(String value) { 
          if (value == null)
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

    // syntactic sugar
        /**
         * @return {@link #additiveType} (Indicates the type of modular component such as protein, carbohydrate or fiber to be provided in addition to or mixed with the base formula.)
         */
        public CodeableConcept addAdditiveType() { 
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

    // syntactic sugar
        /**
         * @return {@link #caloricDensity} (TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.)
         */
        public Quantity addCaloricDensity() { 
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

    // syntactic sugar
        /**
         * @return {@link #routeofAdministration} (***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***administration details including rate (ml per hour), route of adminstration, total volume.)
         */
        public CodeableConcept addRouteofAdministration() { 
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

    // syntactic sugar
        /**
         * @return {@link #rate} (TODO ***CARD AND TYPE ARE PLACEHOLDERS TO COMPLETE BUILD.  Need to discuss***.)
         */
        public Quantity addRate() { 
          Quantity t = new Quantity();
          this.rate.add(t);
          return t;
        }

        /**
         * @return {@link #baseFormulaName} (TODO.)
         */
        public StringType getBaseFormulaName() { 
          return this.baseFormulaName;
        }

        /**
         * @param value {@link #baseFormulaName} (TODO.)
         */
        public NutritionOrderItemEnteralFormulaComponent setBaseFormulaName(StringType value) { 
          this.baseFormulaName = value;
          return this;
        }

        /**
         * @return TODO.
         */
        public String getBaseFormulaNameSimple() { 
          return this.baseFormulaName == null ? null : this.baseFormulaName.getValue();
        }

        /**
         * @param value TODO.
         */
        public NutritionOrderItemEnteralFormulaComponent setBaseFormulaNameSimple(String value) { 
          if (value == null)
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
     * @return {@link #subject} (The actual object that is the target of the reference. The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The person (patient) who needs the nutrition order for an oral diet, nutritional supplement and/or enteral or formula feeding.)
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
     * @return {@link #orderer} (The actual object that is the target of the reference. The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.)
     */
    public Practitioner getOrdererTarget() { 
      return this.ordererTarget;
    }

    /**
     * @param value {@link #orderer} (The actual object that is the target of the reference. The practitioner that holds legal responsibility for ordering the diet, nutritional supplement, or formula feedings.)
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

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the order sender or by the order receiver.)
     */
    public Identifier addIdentifier() { 
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
     * @return {@link #encounter} (The actual object that is the target of the reference. An encounter that provides additional informaton about the healthcare context in which this request is made.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} (The actual object that is the target of the reference. An encounter that provides additional informaton about the healthcare context in which this request is made.)
     */
    public NutritionOrder setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #dateTime} (The date and time that this nutrition order was requested.)
     */
    public DateTimeType getDateTime() { 
      return this.dateTime;
    }

    /**
     * @param value {@link #dateTime} (The date and time that this nutrition order was requested.)
     */
    public NutritionOrder setDateTime(DateTimeType value) { 
      this.dateTime = value;
      return this;
    }

    /**
     * @return The date and time that this nutrition order was requested.
     */
    public DateAndTime getDateTimeSimple() { 
      return this.dateTime == null ? null : this.dateTime.getValue();
    }

    /**
     * @param value The date and time that this nutrition order was requested.
     */
    public NutritionOrder setDateTimeSimple(DateAndTime value) { 
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

    // syntactic sugar
    /**
     * @return {@link #allergyIntolerance} (The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
     */
    public Reference addAllergyIntolerance() { 
      Reference t = new Reference();
      this.allergyIntolerance.add(t);
      return t;
    }

    /**
     * @return {@link #allergyIntolerance} (The actual objects that are the target of the reference. The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
     */
    public List<AllergyIntolerance> getAllergyIntoleranceTarget() { 
      return this.allergyIntoleranceTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #allergyIntolerance} (Add an actual object that is the target of the reference. The ability to list substances that may cause allergies or intolerances which should be included in the nutrition order.)
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

    // syntactic sugar
    /**
     * @return {@link #foodPreferenceModifier} (This modifier is used to convey order-specific modifiers about the type of food that should be given. These can be derived from patient allergies, intolerances, or preferences such as Halal, Vegan or Kosher. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.)
     */
    public CodeableConcept addFoodPreferenceModifier() { 
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

    // syntactic sugar
    /**
     * @return {@link #excludeFoodModifier} (This modifier is used to convey order-specific modifiers about the type of food that should NOT be given. These can be derived from patient allergies, intolerances, or preferences such as No Red Meat, No Soy or No Wheat or  Gluten-Free. This modifier applies to the entire nutrition order inclusive of the oral diet, nutritional supplements and enteral formula feedings.)
     */
    public CodeableConcept addExcludeFoodModifier() { 
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

    // syntactic sugar
    /**
     * @return {@link #item} (Different items that combine to make a complete description of the nutrition to be provided via oral diet, nutritional supplement and/or formula order.)
     */
    public NutritionOrderItemComponent addItem() { 
      NutritionOrderItemComponent t = new NutritionOrderItemComponent();
      this.item.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.)
     */
    public Enumeration<NutritionOrderStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.)
     */
    public NutritionOrder setStatus(Enumeration<NutritionOrderStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.
     */
    public NutritionOrderStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The workflow status of the nutrition order request, e.g., Active, Inactive, Pending, Held, Canceled, Suspended.
     */
    public NutritionOrder setStatusSimple(NutritionOrderStatus value) { 
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

