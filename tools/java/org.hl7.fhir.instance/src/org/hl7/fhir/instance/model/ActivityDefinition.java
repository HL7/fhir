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

// Generated on Mon, Dec 9, 2013 22:20+1100 for FHIR v0.12

import java.util.*;

/**
 * Used as part of care plans, protocols and other resources that describe.
 */
public class ActivityDefinition extends Type {

    public enum ActivityDefinitionCategory {
        diet, // To consume food of a specified nature.
        drug, // To consume/receive a drug, vaccine or other product.
        encounter, // To meet or communicate with the patient (in-patient, out-patient, phone call, etc.).
        observation, // To capture information about a patient (vitals, labs, diagnostic images, etc.).
        procedure, // To modify the patient in some way (surgery, physiotherapy, education, counseling, etc.).
        supply, // To provide something to the patient (medication, medical supply, etc.).
        other, // Some other form of action.
        Null; // added to help the parsers
        public static ActivityDefinitionCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return diet;
        if ("drug".equals(codeString))
          return drug;
        if ("encounter".equals(codeString))
          return encounter;
        if ("observation".equals(codeString))
          return observation;
        if ("procedure".equals(codeString))
          return procedure;
        if ("supply".equals(codeString))
          return supply;
        if ("other".equals(codeString))
          return other;
        throw new Exception("Unknown ActivityDefinitionCategory code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case diet: return "diet";
            case drug: return "drug";
            case encounter: return "encounter";
            case observation: return "observation";
            case procedure: return "procedure";
            case supply: return "supply";
            case other: return "other";
            default: return "?";
          }
        }
    }

  public static class ActivityDefinitionCategoryEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return ActivityDefinitionCategory.diet;
        if ("drug".equals(codeString))
          return ActivityDefinitionCategory.drug;
        if ("encounter".equals(codeString))
          return ActivityDefinitionCategory.encounter;
        if ("observation".equals(codeString))
          return ActivityDefinitionCategory.observation;
        if ("procedure".equals(codeString))
          return ActivityDefinitionCategory.procedure;
        if ("supply".equals(codeString))
          return ActivityDefinitionCategory.supply;
        if ("other".equals(codeString))
          return ActivityDefinitionCategory.other;
        throw new Exception("Unknown ActivityDefinitionCategory code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ActivityDefinitionCategory.diet)
        return "diet";
      if (code == ActivityDefinitionCategory.drug)
        return "drug";
      if (code == ActivityDefinitionCategory.encounter)
        return "encounter";
      if (code == ActivityDefinitionCategory.observation)
        return "observation";
      if (code == ActivityDefinitionCategory.procedure)
        return "procedure";
      if (code == ActivityDefinitionCategory.supply)
        return "supply";
      if (code == ActivityDefinitionCategory.other)
        return "other";
      return "?";
      }
    }

    /**
     * High-level categorization of the type of activity.
     */
    protected Enumeration<ActivityDefinitionCategory> category;

    /**
     * Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.
     */
    protected CodeableConcept code;

    /**
     * The period, timing or frequency upon which the described activity is to occur.
     */
    protected Type timing;

    /**
     * Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.
     */
    protected ResourceReference location;

    /**
     * Identifies who's expected to be involved in the activity.
     */
    protected List<ResourceReference> performer = new ArrayList<ResourceReference>();

    /**
     * Identifies the food, drug or other product being consumed or supplied in the activity.
     */
    protected ResourceReference product;

    /**
     * Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).
     */
    protected Quantity quantity;

    /**
     * This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
     */
    protected String_ details;

    public ActivityDefinition() {
      super();
    }

    /**
     * @return {@link #category} (High-level categorization of the type of activity.)
     */
    public Enumeration<ActivityDefinitionCategory> getCategory() { 
      return this.category;
    }

    /**
     * @param value {@link #category} (High-level categorization of the type of activity.)
     */
    public ActivityDefinition setCategory(Enumeration<ActivityDefinitionCategory> value) { 
      this.category = value;
      return this;
    }

    /**
     * @return High-level categorization of the type of activity.
     */
    public ActivityDefinitionCategory getCategorySimple() { 
      return this.category == null ? null : this.category.getValue();
    }

    /**
     * @param value High-level categorization of the type of activity.
     */
    public ActivityDefinition setCategorySimple(ActivityDefinitionCategory value) { 
      if (value == null)
        this.category = null;
      else {
        if (this.category == null)
          this.category = new Enumeration<ActivityDefinitionCategory>();
        this.category.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #code} (Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.)
     */
    public CodeableConcept getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.)
     */
    public ActivityDefinition setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    /**
     * @return {@link #timing} (The period, timing or frequency upon which the described activity is to occur.)
     */
    public Type getTiming() { 
      return this.timing;
    }

    /**
     * @param value {@link #timing} (The period, timing or frequency upon which the described activity is to occur.)
     */
    public ActivityDefinition setTiming(Type value) { 
      this.timing = value;
      return this;
    }

    /**
     * @return {@link #location} (Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.)
     */
    public ResourceReference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.)
     */
    public ActivityDefinition setLocation(ResourceReference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #performer} (Identifies who's expected to be involved in the activity.)
     */
    public List<ResourceReference> getPerformer() { 
      return this.performer;
    }

    // syntactic sugar
    /**
     * @return {@link #performer} (Identifies who's expected to be involved in the activity.)
     */
    public ResourceReference addPerformer() { 
      ResourceReference t = new ResourceReference();
      this.performer.add(t);
      return t;
    }

    /**
     * @return {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
     */
    public ResourceReference getProduct() { 
      return this.product;
    }

    /**
     * @param value {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
     */
    public ActivityDefinition setProduct(ResourceReference value) { 
      this.product = value;
      return this;
    }

    /**
     * @return {@link #quantity} (Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).)
     */
    public Quantity getQuantity() { 
      return this.quantity;
    }

    /**
     * @param value {@link #quantity} (Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).)
     */
    public ActivityDefinition setQuantity(Quantity value) { 
      this.quantity = value;
      return this;
    }

    /**
     * @return {@link #details} (This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.)
     */
    public String_ getDetails() { 
      return this.details;
    }

    /**
     * @param value {@link #details} (This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.)
     */
    public ActivityDefinition setDetails(String_ value) { 
      this.details = value;
      return this;
    }

    /**
     * @return This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
     */
    public String getDetailsSimple() { 
      return this.details == null ? null : this.details.getValue();
    }

    /**
     * @param value This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
     */
    public ActivityDefinition setDetailsSimple(String value) { 
      if (value == null)
        this.details = null;
      else {
        if (this.details == null)
          this.details = new String_();
        this.details.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("category", "code", "High-level categorization of the type of activity.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("code", "CodeableConcept", "Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("timing[x]", "CodeableConcept|Schedule", "The period, timing or frequency upon which the described activity is to occur.", 0, java.lang.Integer.MAX_VALUE, timing));
        childrenList.add(new Property("location", "Resource(Location)", "Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("performer", "Resource(Practitioner|Organization|RelatedPerson|Patient)", "Identifies who's expected to be involved in the activity.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("product", "Resource(Medication|Substance)", "Identifies the food, drug or other product being consumed or supplied in the activity.", 0, java.lang.Integer.MAX_VALUE, product));
        childrenList.add(new Property("quantity", "Quantity", "Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).", 0, java.lang.Integer.MAX_VALUE, quantity));
        childrenList.add(new Property("details", "string", "This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.", 0, java.lang.Integer.MAX_VALUE, details));
      }

      public ActivityDefinition copy() {
        ActivityDefinition dst = new ActivityDefinition();
        dst.category = category == null ? null : category.copy();
        dst.code = code == null ? null : code.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.location = location == null ? null : location.copy();
        dst.performer = new ArrayList<ResourceReference>();
        for (ResourceReference i : performer)
          dst.performer.add(i.copy());
        dst.product = product == null ? null : product.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.details = details == null ? null : details.copy();
        return dst;
      }

      protected ActivityDefinition typedCopy() {
        return copy();
      }


}

