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

// Generated on Wed, Jun 19, 2013 01:55+1000 for FHIR v0.09

import java.util.*;

/**
 * Used as part of care plans, protocols and other resources that describe
 */
public class ActivityDefinition extends Type {

    public enum ActivityDefinitionCategory {
        diet, // To consume food of a specified nature
        drug, // To consume/receive a drug, vaccine or other product
        visit, // To meet or communicate with the patient (in-patient, out-patient, phone call, etc.)
        observation, // To capture information about a patient (vitals, labs, diagnostic images, etc.)
        procedure, // To modify the patient in some way (surgery, physio-therapy, education, counselling, etc.)
        supply, // To provide something to the patient (medication, medical supply, etc.)
        other, // Some other form of action
        Null; // added to help the parsers
        public static ActivityDefinitionCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return diet;
        if ("drug".equals(codeString))
          return drug;
        if ("visit".equals(codeString))
          return visit;
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
            case visit: return "visit";
            case observation: return "observation";
            case procedure: return "procedure";
            case supply: return "supply";
            case other: return "other";
            default: return "?";
          }
        }
    }

  public class ActivityDefinitionCategoryEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return ActivityDefinitionCategory.diet;
        if ("drug".equals(codeString))
          return ActivityDefinitionCategory.drug;
        if ("visit".equals(codeString))
          return ActivityDefinitionCategory.visit;
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
      if (code == ActivityDefinitionCategory.visit)
        return "visit";
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
     * Identifies the quantity expected to be consumed at once (per dose, per meal, etc.)
     */
    protected Quantity quantity;

    /**
     * This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
     */
    protected String_ details;

    public Enumeration<ActivityDefinitionCategory> getCategory() { 
      return this.category;
    }

    public void setCategory(Enumeration<ActivityDefinitionCategory> value) { 
      this.category = value;
    }

    public ActivityDefinitionCategory getCategorySimple() { 
      return this.category == null ? null : this.category.getValue();
    }

    public void setCategorySimple(ActivityDefinitionCategory value) { 
      if (value == null)
        this.category = null;
      else {
        if (this.category == null)
          this.category = new Enumeration<ActivityDefinitionCategory>();
        this.category.setValue(value);
      }
    }

    public CodeableConcept getCode() { 
      return this.code;
    }

    public void setCode(CodeableConcept value) { 
      this.code = value;
    }

    public Type getTiming() { 
      return this.timing;
    }

    public void setTiming(Type value) { 
      this.timing = value;
    }

    public ResourceReference getLocation() { 
      return this.location;
    }

    public void setLocation(ResourceReference value) { 
      this.location = value;
    }

    public List<ResourceReference> getPerformer() { 
      return this.performer;
    }

    public ResourceReference getProduct() { 
      return this.product;
    }

    public void setProduct(ResourceReference value) { 
      this.product = value;
    }

    public Quantity getQuantity() { 
      return this.quantity;
    }

    public void setQuantity(Quantity value) { 
      this.quantity = value;
    }

    public String_ getDetails() { 
      return this.details;
    }

    public void setDetails(String_ value) { 
      this.details = value;
    }

    public String getDetailsSimple() { 
      return this.details == null ? null : this.details.getValue();
    }

    public void setDetailsSimple(String value) { 
      if (value == null)
        this.details = null;
      else {
        if (this.details == null)
          this.details = new String_();
        this.details.setValue(value);
      }
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

