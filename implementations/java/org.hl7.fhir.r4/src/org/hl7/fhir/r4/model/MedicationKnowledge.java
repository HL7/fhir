package org.hl7.fhir.r4.model;

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

// Generated on Tue, Apr 3, 2018 06:39+1000 for FHIR v3.4.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.exceptions.FHIRException;
/**
 * Information about a medication that is used to support knowledge.
 */
@ResourceDef(name="MedicationKnowledge", profile="http://hl7.org/fhir/Profile/MedicationKnowledge")
public class MedicationKnowledge extends DomainResource {

    public enum MedicationKnowledgeStatus {
        /**
         * The medication is available for use
         */
        ACTIVE, 
        /**
         * The medication is not available for use
         */
        INACTIVE, 
        /**
         * The medication was entered in error
         */
        ENTEREDINERROR, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static MedicationKnowledgeStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("inactive".equals(codeString))
          return INACTIVE;
        if ("entered-in-error".equals(codeString))
          return ENTEREDINERROR;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown MedicationKnowledgeStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ACTIVE: return "active";
            case INACTIVE: return "inactive";
            case ENTEREDINERROR: return "entered-in-error";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case ACTIVE: return "http://hl7.org/fhir/medicationKnowledge-status";
            case INACTIVE: return "http://hl7.org/fhir/medicationKnowledge-status";
            case ENTEREDINERROR: return "http://hl7.org/fhir/medicationKnowledge-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ACTIVE: return "The medication is available for use";
            case INACTIVE: return "The medication is not available for use";
            case ENTEREDINERROR: return "The medication was entered in error";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ACTIVE: return "Active";
            case INACTIVE: return "Inactive";
            case ENTEREDINERROR: return "Entered in Error";
            default: return "?";
          }
        }
    }

  public static class MedicationKnowledgeStatusEnumFactory implements EnumFactory<MedicationKnowledgeStatus> {
    public MedicationKnowledgeStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return MedicationKnowledgeStatus.ACTIVE;
        if ("inactive".equals(codeString))
          return MedicationKnowledgeStatus.INACTIVE;
        if ("entered-in-error".equals(codeString))
          return MedicationKnowledgeStatus.ENTEREDINERROR;
        throw new IllegalArgumentException("Unknown MedicationKnowledgeStatus code '"+codeString+"'");
        }
        public Enumeration<MedicationKnowledgeStatus> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<MedicationKnowledgeStatus>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("active".equals(codeString))
          return new Enumeration<MedicationKnowledgeStatus>(this, MedicationKnowledgeStatus.ACTIVE);
        if ("inactive".equals(codeString))
          return new Enumeration<MedicationKnowledgeStatus>(this, MedicationKnowledgeStatus.INACTIVE);
        if ("entered-in-error".equals(codeString))
          return new Enumeration<MedicationKnowledgeStatus>(this, MedicationKnowledgeStatus.ENTEREDINERROR);
        throw new FHIRException("Unknown MedicationKnowledgeStatus code '"+codeString+"'");
        }
    public String toCode(MedicationKnowledgeStatus code) {
      if (code == MedicationKnowledgeStatus.ACTIVE)
        return "active";
      if (code == MedicationKnowledgeStatus.INACTIVE)
        return "inactive";
      if (code == MedicationKnowledgeStatus.ENTEREDINERROR)
        return "entered-in-error";
      return "?";
      }
    public String toSystem(MedicationKnowledgeStatus code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class MedicationKnowledgeIngredientComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The actual ingredient - either a substance (simple ingredient) or another medication.
         */
        @Child(name = "item", type = {CodeableConcept.class, Substance.class, Medication.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Medication(s) or substance(s) contained in the medication", formalDefinition="The actual ingredient - either a substance (simple ingredient) or another medication." )
        protected Type item;

        /**
         * Indication of whether this ingredient affects the therapeutic action of the drug.
         */
        @Child(name = "isActive", type = {BooleanType.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Active ingredient indicator", formalDefinition="Indication of whether this ingredient affects the therapeutic action of the drug." )
        protected BooleanType isActive;

        /**
         * Specifies how many (or how much) of the items there are in this Medication.  For example, 250 mg per tablet.  This is expressed as a ratio where the numerator is 250mg and the denominator is 1 tablet.
         */
        @Child(name = "amount", type = {Ratio.class}, order=3, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Quantity of ingredient present", formalDefinition="Specifies how many (or how much) of the items there are in this Medication.  For example, 250 mg per tablet.  This is expressed as a ratio where the numerator is 250mg and the denominator is 1 tablet." )
        protected Ratio amount;

        private static final long serialVersionUID = -1796655982L;

    /**
     * Constructor
     */
      public MedicationKnowledgeIngredientComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MedicationKnowledgeIngredientComponent(Type item) {
        super();
        this.item = item;
      }

        /**
         * @return {@link #item} (The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public Type getItem() { 
          return this.item;
        }

        /**
         * @return {@link #item} (The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public CodeableConcept getItemCodeableConcept() throws FHIRException { 
          if (this.item == null)
            return null;
          if (!(this.item instanceof CodeableConcept))
            throw new FHIRException("Type mismatch: the type CodeableConcept was expected, but "+this.item.getClass().getName()+" was encountered");
          return (CodeableConcept) this.item;
        }

        public boolean hasItemCodeableConcept() { 
          return this != null && this.item instanceof CodeableConcept;
        }

        /**
         * @return {@link #item} (The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public Reference getItemReference() throws FHIRException { 
          if (this.item == null)
            return null;
          if (!(this.item instanceof Reference))
            throw new FHIRException("Type mismatch: the type Reference was expected, but "+this.item.getClass().getName()+" was encountered");
          return (Reference) this.item;
        }

        public boolean hasItemReference() { 
          return this != null && this.item instanceof Reference;
        }

        public boolean hasItem() { 
          return this.item != null && !this.item.isEmpty();
        }

        /**
         * @param value {@link #item} (The actual ingredient - either a substance (simple ingredient) or another medication.)
         */
        public MedicationKnowledgeIngredientComponent setItem(Type value) { 
          if (value != null && !(value instanceof CodeableConcept || value instanceof Reference))
            throw new Error("Not the right type for MedicationKnowledge.ingredient.item[x]: "+value.fhirType());
          this.item = value;
          return this;
        }

        /**
         * @return {@link #isActive} (Indication of whether this ingredient affects the therapeutic action of the drug.). This is the underlying object with id, value and extensions. The accessor "getIsActive" gives direct access to the value
         */
        public BooleanType getIsActiveElement() { 
          if (this.isActive == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeIngredientComponent.isActive");
            else if (Configuration.doAutoCreate())
              this.isActive = new BooleanType(); // bb
          return this.isActive;
        }

        public boolean hasIsActiveElement() { 
          return this.isActive != null && !this.isActive.isEmpty();
        }

        public boolean hasIsActive() { 
          return this.isActive != null && !this.isActive.isEmpty();
        }

        /**
         * @param value {@link #isActive} (Indication of whether this ingredient affects the therapeutic action of the drug.). This is the underlying object with id, value and extensions. The accessor "getIsActive" gives direct access to the value
         */
        public MedicationKnowledgeIngredientComponent setIsActiveElement(BooleanType value) { 
          this.isActive = value;
          return this;
        }

        /**
         * @return Indication of whether this ingredient affects the therapeutic action of the drug.
         */
        public boolean getIsActive() { 
          return this.isActive == null || this.isActive.isEmpty() ? false : this.isActive.getValue();
        }

        /**
         * @param value Indication of whether this ingredient affects the therapeutic action of the drug.
         */
        public MedicationKnowledgeIngredientComponent setIsActive(boolean value) { 
            if (this.isActive == null)
              this.isActive = new BooleanType();
            this.isActive.setValue(value);
          return this;
        }

        /**
         * @return {@link #amount} (Specifies how many (or how much) of the items there are in this Medication.  For example, 250 mg per tablet.  This is expressed as a ratio where the numerator is 250mg and the denominator is 1 tablet.)
         */
        public Ratio getAmount() { 
          if (this.amount == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeIngredientComponent.amount");
            else if (Configuration.doAutoCreate())
              this.amount = new Ratio(); // cc
          return this.amount;
        }

        public boolean hasAmount() { 
          return this.amount != null && !this.amount.isEmpty();
        }

        /**
         * @param value {@link #amount} (Specifies how many (or how much) of the items there are in this Medication.  For example, 250 mg per tablet.  This is expressed as a ratio where the numerator is 250mg and the denominator is 1 tablet.)
         */
        public MedicationKnowledgeIngredientComponent setAmount(Ratio value) { 
          this.amount = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("item[x]", "CodeableConcept|Reference(Substance|Medication)", "The actual ingredient - either a substance (simple ingredient) or another medication.", 0, 1, item));
          children.add(new Property("isActive", "boolean", "Indication of whether this ingredient affects the therapeutic action of the drug.", 0, 1, isActive));
          children.add(new Property("amount", "Ratio", "Specifies how many (or how much) of the items there are in this Medication.  For example, 250 mg per tablet.  This is expressed as a ratio where the numerator is 250mg and the denominator is 1 tablet.", 0, 1, amount));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 2116201613: /*item[x]*/  return new Property("item[x]", "CodeableConcept|Reference(Substance|Medication)", "The actual ingredient - either a substance (simple ingredient) or another medication.", 0, 1, item);
          case 3242771: /*item*/  return new Property("item[x]", "CodeableConcept|Reference(Substance|Medication)", "The actual ingredient - either a substance (simple ingredient) or another medication.", 0, 1, item);
          case 106644494: /*itemCodeableConcept*/  return new Property("item[x]", "CodeableConcept|Reference(Substance|Medication)", "The actual ingredient - either a substance (simple ingredient) or another medication.", 0, 1, item);
          case 1376364920: /*itemReference*/  return new Property("item[x]", "CodeableConcept|Reference(Substance|Medication)", "The actual ingredient - either a substance (simple ingredient) or another medication.", 0, 1, item);
          case -748916528: /*isActive*/  return new Property("isActive", "boolean", "Indication of whether this ingredient affects the therapeutic action of the drug.", 0, 1, isActive);
          case -1413853096: /*amount*/  return new Property("amount", "Ratio", "Specifies how many (or how much) of the items there are in this Medication.  For example, 250 mg per tablet.  This is expressed as a ratio where the numerator is 250mg and the denominator is 1 tablet.", 0, 1, amount);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3242771: /*item*/ return this.item == null ? new Base[0] : new Base[] {this.item}; // Type
        case -748916528: /*isActive*/ return this.isActive == null ? new Base[0] : new Base[] {this.isActive}; // BooleanType
        case -1413853096: /*amount*/ return this.amount == null ? new Base[0] : new Base[] {this.amount}; // Ratio
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3242771: // item
          this.item = castToType(value); // Type
          return value;
        case -748916528: // isActive
          this.isActive = castToBoolean(value); // BooleanType
          return value;
        case -1413853096: // amount
          this.amount = castToRatio(value); // Ratio
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("item[x]")) {
          this.item = castToType(value); // Type
        } else if (name.equals("isActive")) {
          this.isActive = castToBoolean(value); // BooleanType
        } else if (name.equals("amount")) {
          this.amount = castToRatio(value); // Ratio
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 2116201613:  return getItem(); 
        case 3242771:  return getItem(); 
        case -748916528:  return getIsActiveElement();
        case -1413853096:  return getAmount(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3242771: /*item*/ return new String[] {"CodeableConcept", "Reference"};
        case -748916528: /*isActive*/ return new String[] {"boolean"};
        case -1413853096: /*amount*/ return new String[] {"Ratio"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("itemCodeableConcept")) {
          this.item = new CodeableConcept();
          return this.item;
        }
        else if (name.equals("itemReference")) {
          this.item = new Reference();
          return this.item;
        }
        else if (name.equals("isActive")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.isActive");
        }
        else if (name.equals("amount")) {
          this.amount = new Ratio();
          return this.amount;
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeIngredientComponent copy() {
        MedicationKnowledgeIngredientComponent dst = new MedicationKnowledgeIngredientComponent();
        copyValues(dst);
        dst.item = item == null ? null : item.copy();
        dst.isActive = isActive == null ? null : isActive.copy();
        dst.amount = amount == null ? null : amount.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeIngredientComponent))
          return false;
        MedicationKnowledgeIngredientComponent o = (MedicationKnowledgeIngredientComponent) other_;
        return compareDeep(item, o.item, true) && compareDeep(isActive, o.isActive, true) && compareDeep(amount, o.amount, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeIngredientComponent))
          return false;
        MedicationKnowledgeIngredientComponent o = (MedicationKnowledgeIngredientComponent) other_;
        return compareValues(isActive, o.isActive, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(item, isActive, amount);
      }

  public String fhirType() {
    return "MedicationKnowledge.ingredient";

  }

  }

    @Block()
    public static class MedicationKnowledgeCostComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The formulary or payer that assigns the price to the medication.
         */
        @Child(name = "formulary", type = {StringType.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="The formulary which applies to the price", formalDefinition="The formulary or payer that assigns the price to the medication." )
        protected StringType formulary;

        /**
         * The price of the medication.
         */
        @Child(name = "cost", type = {Money.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="The price of the medication", formalDefinition="The price of the medication." )
        protected Money cost;

        private static final long serialVersionUID = -1637098581L;

    /**
     * Constructor
     */
      public MedicationKnowledgeCostComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MedicationKnowledgeCostComponent(StringType formulary, Money cost) {
        super();
        this.formulary = formulary;
        this.cost = cost;
      }

        /**
         * @return {@link #formulary} (The formulary or payer that assigns the price to the medication.). This is the underlying object with id, value and extensions. The accessor "getFormulary" gives direct access to the value
         */
        public StringType getFormularyElement() { 
          if (this.formulary == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeCostComponent.formulary");
            else if (Configuration.doAutoCreate())
              this.formulary = new StringType(); // bb
          return this.formulary;
        }

        public boolean hasFormularyElement() { 
          return this.formulary != null && !this.formulary.isEmpty();
        }

        public boolean hasFormulary() { 
          return this.formulary != null && !this.formulary.isEmpty();
        }

        /**
         * @param value {@link #formulary} (The formulary or payer that assigns the price to the medication.). This is the underlying object with id, value and extensions. The accessor "getFormulary" gives direct access to the value
         */
        public MedicationKnowledgeCostComponent setFormularyElement(StringType value) { 
          this.formulary = value;
          return this;
        }

        /**
         * @return The formulary or payer that assigns the price to the medication.
         */
        public String getFormulary() { 
          return this.formulary == null ? null : this.formulary.getValue();
        }

        /**
         * @param value The formulary or payer that assigns the price to the medication.
         */
        public MedicationKnowledgeCostComponent setFormulary(String value) { 
            if (this.formulary == null)
              this.formulary = new StringType();
            this.formulary.setValue(value);
          return this;
        }

        /**
         * @return {@link #cost} (The price of the medication.)
         */
        public Money getCost() { 
          if (this.cost == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeCostComponent.cost");
            else if (Configuration.doAutoCreate())
              this.cost = new Money(); // cc
          return this.cost;
        }

        public boolean hasCost() { 
          return this.cost != null && !this.cost.isEmpty();
        }

        /**
         * @param value {@link #cost} (The price of the medication.)
         */
        public MedicationKnowledgeCostComponent setCost(Money value) { 
          this.cost = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("formulary", "string", "The formulary or payer that assigns the price to the medication.", 0, 1, formulary));
          children.add(new Property("cost", "Money", "The price of the medication.", 0, 1, cost));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 1829805613: /*formulary*/  return new Property("formulary", "string", "The formulary or payer that assigns the price to the medication.", 0, 1, formulary);
          case 3059661: /*cost*/  return new Property("cost", "Money", "The price of the medication.", 0, 1, cost);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 1829805613: /*formulary*/ return this.formulary == null ? new Base[0] : new Base[] {this.formulary}; // StringType
        case 3059661: /*cost*/ return this.cost == null ? new Base[0] : new Base[] {this.cost}; // Money
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 1829805613: // formulary
          this.formulary = castToString(value); // StringType
          return value;
        case 3059661: // cost
          this.cost = castToMoney(value); // Money
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("formulary")) {
          this.formulary = castToString(value); // StringType
        } else if (name.equals("cost")) {
          this.cost = castToMoney(value); // Money
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1829805613:  return getFormularyElement();
        case 3059661:  return getCost(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1829805613: /*formulary*/ return new String[] {"string"};
        case 3059661: /*cost*/ return new String[] {"Money"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("formulary")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.formulary");
        }
        else if (name.equals("cost")) {
          this.cost = new Money();
          return this.cost;
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeCostComponent copy() {
        MedicationKnowledgeCostComponent dst = new MedicationKnowledgeCostComponent();
        copyValues(dst);
        dst.formulary = formulary == null ? null : formulary.copy();
        dst.cost = cost == null ? null : cost.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeCostComponent))
          return false;
        MedicationKnowledgeCostComponent o = (MedicationKnowledgeCostComponent) other_;
        return compareDeep(formulary, o.formulary, true) && compareDeep(cost, o.cost, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeCostComponent))
          return false;
        MedicationKnowledgeCostComponent o = (MedicationKnowledgeCostComponent) other_;
        return compareValues(formulary, o.formulary, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(formulary, cost);
      }

  public String fhirType() {
    return "MedicationKnowledge.cost";

  }

  }

    @Block()
    public static class MedicationKnowledgeMonitoringProgramComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Type of program under which the medication is monitored.
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Type of program under which the medication is monitored", formalDefinition="Type of program under which the medication is monitored." )
        protected CodeableConcept type;

        /**
         * Name of the reviewing program.
         */
        @Child(name = "name", type = {StringType.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Name of the reviewing program", formalDefinition="Name of the reviewing program." )
        protected StringType name;

        private static final long serialVersionUID = -280346281L;

    /**
     * Constructor
     */
      public MedicationKnowledgeMonitoringProgramComponent() {
        super();
      }

        /**
         * @return {@link #type} (Type of program under which the medication is monitored.)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeMonitoringProgramComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (Type of program under which the medication is monitored.)
         */
        public MedicationKnowledgeMonitoringProgramComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #name} (Name of the reviewing program.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          if (this.name == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeMonitoringProgramComponent.name");
            else if (Configuration.doAutoCreate())
              this.name = new StringType(); // bb
          return this.name;
        }

        public boolean hasNameElement() { 
          return this.name != null && !this.name.isEmpty();
        }

        public boolean hasName() { 
          return this.name != null && !this.name.isEmpty();
        }

        /**
         * @param value {@link #name} (Name of the reviewing program.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public MedicationKnowledgeMonitoringProgramComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Name of the reviewing program.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Name of the reviewing program.
         */
        public MedicationKnowledgeMonitoringProgramComponent setName(String value) { 
          if (Utilities.noString(value))
            this.name = null;
          else {
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("type", "CodeableConcept", "Type of program under which the medication is monitored.", 0, 1, type));
          children.add(new Property("name", "string", "Name of the reviewing program.", 0, 1, name));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3575610: /*type*/  return new Property("type", "CodeableConcept", "Type of program under which the medication is monitored.", 0, 1, type);
          case 3373707: /*name*/  return new Property("name", "string", "Name of the reviewing program.", 0, 1, name);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case 3373707: /*name*/ return this.name == null ? new Base[0] : new Base[] {this.name}; // StringType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 3373707: // name
          this.name = castToString(value); // StringType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("name")) {
          this.name = castToString(value); // StringType
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610:  return getType(); 
        case 3373707:  return getNameElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case 3373707: /*name*/ return new String[] {"string"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("name")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.name");
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeMonitoringProgramComponent copy() {
        MedicationKnowledgeMonitoringProgramComponent dst = new MedicationKnowledgeMonitoringProgramComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.name = name == null ? null : name.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeMonitoringProgramComponent))
          return false;
        MedicationKnowledgeMonitoringProgramComponent o = (MedicationKnowledgeMonitoringProgramComponent) other_;
        return compareDeep(type, o.type, true) && compareDeep(name, o.name, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeMonitoringProgramComponent))
          return false;
        MedicationKnowledgeMonitoringProgramComponent o = (MedicationKnowledgeMonitoringProgramComponent) other_;
        return compareValues(name, o.name, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(type, name);
      }

  public String fhirType() {
    return "MedicationKnowledge.monitoringProgram";

  }

  }

    @Block()
    public static class MedicationKnowledgeAdministrationGuidelinesComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Dosage for the medication for the specific guidelines.
         */
        @Child(name = "dosage", type = {Dosage.class}, order=1, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Dosage for the medication for the specific guidelines", formalDefinition="Dosage for the medication for the specific guidelines." )
        protected List<Dosage> dosage;

        /**
         * Indication for use that apply to the specific administration guidelines.
         */
        @Child(name = "indication", type = {CodeableConcept.class}, order=2, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Indication for use that apply to the specific administration guidelines", formalDefinition="Indication for use that apply to the specific administration guidelines." )
        protected List<CodeableConcept> indication;

        /**
         * Characteristics of the patient that are relevant to the administration guidelines (for example, height, weight,gender,  etc).
         */
        @Child(name = "patientCharacteristics", type = {}, order=3, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Characteristics of the patient that are relevant to the administration guidelines", formalDefinition="Characteristics of the patient that are relevant to the administration guidelines (for example, height, weight,gender,  etc)." )
        protected List<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent> patientCharacteristics;

        private static final long serialVersionUID = -1735249018L;

    /**
     * Constructor
     */
      public MedicationKnowledgeAdministrationGuidelinesComponent() {
        super();
      }

        /**
         * @return {@link #dosage} (Dosage for the medication for the specific guidelines.)
         */
        public List<Dosage> getDosage() { 
          if (this.dosage == null)
            this.dosage = new ArrayList<Dosage>();
          return this.dosage;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public MedicationKnowledgeAdministrationGuidelinesComponent setDosage(List<Dosage> theDosage) { 
          this.dosage = theDosage;
          return this;
        }

        public boolean hasDosage() { 
          if (this.dosage == null)
            return false;
          for (Dosage item : this.dosage)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Dosage addDosage() { //3
          Dosage t = new Dosage();
          if (this.dosage == null)
            this.dosage = new ArrayList<Dosage>();
          this.dosage.add(t);
          return t;
        }

        public MedicationKnowledgeAdministrationGuidelinesComponent addDosage(Dosage t) { //3
          if (t == null)
            return this;
          if (this.dosage == null)
            this.dosage = new ArrayList<Dosage>();
          this.dosage.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #dosage}, creating it if it does not already exist
         */
        public Dosage getDosageFirstRep() { 
          if (getDosage().isEmpty()) {
            addDosage();
          }
          return getDosage().get(0);
        }

        /**
         * @return {@link #indication} (Indication for use that apply to the specific administration guidelines.)
         */
        public List<CodeableConcept> getIndication() { 
          if (this.indication == null)
            this.indication = new ArrayList<CodeableConcept>();
          return this.indication;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public MedicationKnowledgeAdministrationGuidelinesComponent setIndication(List<CodeableConcept> theIndication) { 
          this.indication = theIndication;
          return this;
        }

        public boolean hasIndication() { 
          if (this.indication == null)
            return false;
          for (CodeableConcept item : this.indication)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addIndication() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.indication == null)
            this.indication = new ArrayList<CodeableConcept>();
          this.indication.add(t);
          return t;
        }

        public MedicationKnowledgeAdministrationGuidelinesComponent addIndication(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.indication == null)
            this.indication = new ArrayList<CodeableConcept>();
          this.indication.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #indication}, creating it if it does not already exist
         */
        public CodeableConcept getIndicationFirstRep() { 
          if (getIndication().isEmpty()) {
            addIndication();
          }
          return getIndication().get(0);
        }

        /**
         * @return {@link #patientCharacteristics} (Characteristics of the patient that are relevant to the administration guidelines (for example, height, weight,gender,  etc).)
         */
        public List<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent> getPatientCharacteristics() { 
          if (this.patientCharacteristics == null)
            this.patientCharacteristics = new ArrayList<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent>();
          return this.patientCharacteristics;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public MedicationKnowledgeAdministrationGuidelinesComponent setPatientCharacteristics(List<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent> thePatientCharacteristics) { 
          this.patientCharacteristics = thePatientCharacteristics;
          return this;
        }

        public boolean hasPatientCharacteristics() { 
          if (this.patientCharacteristics == null)
            return false;
          for (MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent item : this.patientCharacteristics)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent addPatientCharacteristics() { //3
          MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent t = new MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent();
          if (this.patientCharacteristics == null)
            this.patientCharacteristics = new ArrayList<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent>();
          this.patientCharacteristics.add(t);
          return t;
        }

        public MedicationKnowledgeAdministrationGuidelinesComponent addPatientCharacteristics(MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent t) { //3
          if (t == null)
            return this;
          if (this.patientCharacteristics == null)
            this.patientCharacteristics = new ArrayList<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent>();
          this.patientCharacteristics.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #patientCharacteristics}, creating it if it does not already exist
         */
        public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent getPatientCharacteristicsFirstRep() { 
          if (getPatientCharacteristics().isEmpty()) {
            addPatientCharacteristics();
          }
          return getPatientCharacteristics().get(0);
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("dosage", "Dosage", "Dosage for the medication for the specific guidelines.", 0, java.lang.Integer.MAX_VALUE, dosage));
          children.add(new Property("indication", "CodeableConcept", "Indication for use that apply to the specific administration guidelines.", 0, java.lang.Integer.MAX_VALUE, indication));
          children.add(new Property("patientCharacteristics", "", "Characteristics of the patient that are relevant to the administration guidelines (for example, height, weight,gender,  etc).", 0, java.lang.Integer.MAX_VALUE, patientCharacteristics));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case -1326018889: /*dosage*/  return new Property("dosage", "Dosage", "Dosage for the medication for the specific guidelines.", 0, java.lang.Integer.MAX_VALUE, dosage);
          case -597168804: /*indication*/  return new Property("indication", "CodeableConcept", "Indication for use that apply to the specific administration guidelines.", 0, java.lang.Integer.MAX_VALUE, indication);
          case -960531341: /*patientCharacteristics*/  return new Property("patientCharacteristics", "", "Characteristics of the patient that are relevant to the administration guidelines (for example, height, weight,gender,  etc).", 0, java.lang.Integer.MAX_VALUE, patientCharacteristics);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1326018889: /*dosage*/ return this.dosage == null ? new Base[0] : this.dosage.toArray(new Base[this.dosage.size()]); // Dosage
        case -597168804: /*indication*/ return this.indication == null ? new Base[0] : this.indication.toArray(new Base[this.indication.size()]); // CodeableConcept
        case -960531341: /*patientCharacteristics*/ return this.patientCharacteristics == null ? new Base[0] : this.patientCharacteristics.toArray(new Base[this.patientCharacteristics.size()]); // MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1326018889: // dosage
          this.getDosage().add(castToDosage(value)); // Dosage
          return value;
        case -597168804: // indication
          this.getIndication().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -960531341: // patientCharacteristics
          this.getPatientCharacteristics().add((MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent) value); // MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("dosage")) {
          this.getDosage().add(castToDosage(value));
        } else if (name.equals("indication")) {
          this.getIndication().add(castToCodeableConcept(value));
        } else if (name.equals("patientCharacteristics")) {
          this.getPatientCharacteristics().add((MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent) value);
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1326018889:  return addDosage(); 
        case -597168804:  return addIndication(); 
        case -960531341:  return addPatientCharacteristics(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1326018889: /*dosage*/ return new String[] {"Dosage"};
        case -597168804: /*indication*/ return new String[] {"CodeableConcept"};
        case -960531341: /*patientCharacteristics*/ return new String[] {};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("dosage")) {
          return addDosage();
        }
        else if (name.equals("indication")) {
          return addIndication();
        }
        else if (name.equals("patientCharacteristics")) {
          return addPatientCharacteristics();
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeAdministrationGuidelinesComponent copy() {
        MedicationKnowledgeAdministrationGuidelinesComponent dst = new MedicationKnowledgeAdministrationGuidelinesComponent();
        copyValues(dst);
        if (dosage != null) {
          dst.dosage = new ArrayList<Dosage>();
          for (Dosage i : dosage)
            dst.dosage.add(i.copy());
        };
        if (indication != null) {
          dst.indication = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : indication)
            dst.indication.add(i.copy());
        };
        if (patientCharacteristics != null) {
          dst.patientCharacteristics = new ArrayList<MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent>();
          for (MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent i : patientCharacteristics)
            dst.patientCharacteristics.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeAdministrationGuidelinesComponent))
          return false;
        MedicationKnowledgeAdministrationGuidelinesComponent o = (MedicationKnowledgeAdministrationGuidelinesComponent) other_;
        return compareDeep(dosage, o.dosage, true) && compareDeep(indication, o.indication, true) && compareDeep(patientCharacteristics, o.patientCharacteristics, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeAdministrationGuidelinesComponent))
          return false;
        MedicationKnowledgeAdministrationGuidelinesComponent o = (MedicationKnowledgeAdministrationGuidelinesComponent) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(dosage, indication, patientCharacteristics
          );
      }

  public String fhirType() {
    return "MedicationKnowledge.administrationGuidelines";

  }

  }

    @Block()
    public static class MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Specific characteristic that is relevant to the administration guideline (e.g. height, weight, gender).
         */
        @Child(name = "characteristic", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Specific characteristic that is relevant to the administration guideline", formalDefinition="Specific characteristic that is relevant to the administration guideline (e.g. height, weight, gender)." )
        protected CodeableConcept characteristic;

        /**
         * The specific characteristic (e.g. height, weight, gender, etc).
         */
        @Child(name = "value", type = {StringType.class}, order=2, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="The specific characteristic", formalDefinition="The specific characteristic (e.g. height, weight, gender, etc)." )
        protected List<StringType> value;

        private static final long serialVersionUID = 1482439924L;

    /**
     * Constructor
     */
      public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent(CodeableConcept characteristic) {
        super();
        this.characteristic = characteristic;
      }

        /**
         * @return {@link #characteristic} (Specific characteristic that is relevant to the administration guideline (e.g. height, weight, gender).)
         */
        public CodeableConcept getCharacteristic() { 
          if (this.characteristic == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent.characteristic");
            else if (Configuration.doAutoCreate())
              this.characteristic = new CodeableConcept(); // cc
          return this.characteristic;
        }

        public boolean hasCharacteristic() { 
          return this.characteristic != null && !this.characteristic.isEmpty();
        }

        /**
         * @param value {@link #characteristic} (Specific characteristic that is relevant to the administration guideline (e.g. height, weight, gender).)
         */
        public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent setCharacteristic(CodeableConcept value) { 
          this.characteristic = value;
          return this;
        }

        /**
         * @return {@link #value} (The specific characteristic (e.g. height, weight, gender, etc).)
         */
        public List<StringType> getValue() { 
          if (this.value == null)
            this.value = new ArrayList<StringType>();
          return this.value;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent setValue(List<StringType> theValue) { 
          this.value = theValue;
          return this;
        }

        public boolean hasValue() { 
          if (this.value == null)
            return false;
          for (StringType item : this.value)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #value} (The specific characteristic (e.g. height, weight, gender, etc).)
         */
        public StringType addValueElement() {//2 
          StringType t = new StringType();
          if (this.value == null)
            this.value = new ArrayList<StringType>();
          this.value.add(t);
          return t;
        }

        /**
         * @param value {@link #value} (The specific characteristic (e.g. height, weight, gender, etc).)
         */
        public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent addValue(String value) { //1
          StringType t = new StringType();
          t.setValue(value);
          if (this.value == null)
            this.value = new ArrayList<StringType>();
          this.value.add(t);
          return this;
        }

        /**
         * @param value {@link #value} (The specific characteristic (e.g. height, weight, gender, etc).)
         */
        public boolean hasValue(String value) { 
          if (this.value == null)
            return false;
          for (StringType v : this.value)
            if (v.getValue().equals(value)) // string
              return true;
          return false;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("characteristic", "CodeableConcept", "Specific characteristic that is relevant to the administration guideline (e.g. height, weight, gender).", 0, 1, characteristic));
          children.add(new Property("value", "string", "The specific characteristic (e.g. height, weight, gender, etc).", 0, java.lang.Integer.MAX_VALUE, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 366313883: /*characteristic*/  return new Property("characteristic", "CodeableConcept", "Specific characteristic that is relevant to the administration guideline (e.g. height, weight, gender).", 0, 1, characteristic);
          case 111972721: /*value*/  return new Property("value", "string", "The specific characteristic (e.g. height, weight, gender, etc).", 0, java.lang.Integer.MAX_VALUE, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 366313883: /*characteristic*/ return this.characteristic == null ? new Base[0] : new Base[] {this.characteristic}; // CodeableConcept
        case 111972721: /*value*/ return this.value == null ? new Base[0] : this.value.toArray(new Base[this.value.size()]); // StringType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 366313883: // characteristic
          this.characteristic = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 111972721: // value
          this.getValue().add(castToString(value)); // StringType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("characteristic")) {
          this.characteristic = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("value")) {
          this.getValue().add(castToString(value));
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 366313883:  return getCharacteristic(); 
        case 111972721:  return addValueElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 366313883: /*characteristic*/ return new String[] {"CodeableConcept"};
        case 111972721: /*value*/ return new String[] {"string"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("characteristic")) {
          this.characteristic = new CodeableConcept();
          return this.characteristic;
        }
        else if (name.equals("value")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.value");
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent copy() {
        MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent dst = new MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent();
        copyValues(dst);
        dst.characteristic = characteristic == null ? null : characteristic.copy();
        if (value != null) {
          dst.value = new ArrayList<StringType>();
          for (StringType i : value)
            dst.value.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent))
          return false;
        MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent o = (MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent) other_;
        return compareDeep(characteristic, o.characteristic, true) && compareDeep(value, o.value, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent))
          return false;
        MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent o = (MedicationKnowledgeAdministrationGuidelinesPatientCharacteristicsComponent) other_;
        return compareValues(value, o.value, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(characteristic, value);
      }

  public String fhirType() {
    return "MedicationKnowledge.administrationGuidelines.patientCharacteristics";

  }

  }

    @Block()
    public static class MedicationKnowledgeMedicineClassificationComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification).
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification)", formalDefinition="The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification)." )
        protected CodeableConcept type;

        /**
         * Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc).
         */
        @Child(name = "classification", type = {StringType.class}, order=2, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Specific category assigned to the medication", formalDefinition="Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc)." )
        protected List<StringType> classification;

        private static final long serialVersionUID = -778326682L;

    /**
     * Constructor
     */
      public MedicationKnowledgeMedicineClassificationComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MedicationKnowledgeMedicineClassificationComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification).)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeMedicineClassificationComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification).)
         */
        public MedicationKnowledgeMedicineClassificationComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #classification} (Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc).)
         */
        public List<StringType> getClassification() { 
          if (this.classification == null)
            this.classification = new ArrayList<StringType>();
          return this.classification;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public MedicationKnowledgeMedicineClassificationComponent setClassification(List<StringType> theClassification) { 
          this.classification = theClassification;
          return this;
        }

        public boolean hasClassification() { 
          if (this.classification == null)
            return false;
          for (StringType item : this.classification)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #classification} (Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc).)
         */
        public StringType addClassificationElement() {//2 
          StringType t = new StringType();
          if (this.classification == null)
            this.classification = new ArrayList<StringType>();
          this.classification.add(t);
          return t;
        }

        /**
         * @param value {@link #classification} (Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc).)
         */
        public MedicationKnowledgeMedicineClassificationComponent addClassification(String value) { //1
          StringType t = new StringType();
          t.setValue(value);
          if (this.classification == null)
            this.classification = new ArrayList<StringType>();
          this.classification.add(t);
          return this;
        }

        /**
         * @param value {@link #classification} (Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc).)
         */
        public boolean hasClassification(String value) { 
          if (this.classification == null)
            return false;
          for (StringType v : this.classification)
            if (v.getValue().equals(value)) // string
              return true;
          return false;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("type", "CodeableConcept", "The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification).", 0, 1, type));
          children.add(new Property("classification", "string", "Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc).", 0, java.lang.Integer.MAX_VALUE, classification));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3575610: /*type*/  return new Property("type", "CodeableConcept", "The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification).", 0, 1, type);
          case 382350310: /*classification*/  return new Property("classification", "string", "Specific category assigned to the medication (e.g. anti-infective, anti-hypertensive, antibiotic, etc).", 0, java.lang.Integer.MAX_VALUE, classification);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case 382350310: /*classification*/ return this.classification == null ? new Base[0] : this.classification.toArray(new Base[this.classification.size()]); // StringType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 382350310: // classification
          this.getClassification().add(castToString(value)); // StringType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("classification")) {
          this.getClassification().add(castToString(value));
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610:  return getType(); 
        case 382350310:  return addClassificationElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case 382350310: /*classification*/ return new String[] {"string"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("classification")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.classification");
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeMedicineClassificationComponent copy() {
        MedicationKnowledgeMedicineClassificationComponent dst = new MedicationKnowledgeMedicineClassificationComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        if (classification != null) {
          dst.classification = new ArrayList<StringType>();
          for (StringType i : classification)
            dst.classification.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeMedicineClassificationComponent))
          return false;
        MedicationKnowledgeMedicineClassificationComponent o = (MedicationKnowledgeMedicineClassificationComponent) other_;
        return compareDeep(type, o.type, true) && compareDeep(classification, o.classification, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeMedicineClassificationComponent))
          return false;
        MedicationKnowledgeMedicineClassificationComponent o = (MedicationKnowledgeMedicineClassificationComponent) other_;
        return compareValues(classification, o.classification, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(type, classification);
      }

  public String fhirType() {
    return "MedicationKnowledge.medicineClassification";

  }

  }

    @Block()
    public static class MedicationKnowledgeBatchComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The assigned lot number of a batch of the specified product.
         */
        @Child(name = "lotNumber", type = {StringType.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Identifier assigned to batch", formalDefinition="The assigned lot number of a batch of the specified product." )
        protected StringType lotNumber;

        /**
         * When this specific batch of product will expire.
         */
        @Child(name = "expirationDate", type = {DateTimeType.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="When batch will expire", formalDefinition="When this specific batch of product will expire." )
        protected DateTimeType expirationDate;

        /**
         * An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs.
         */
        @Child(name = "serialNumber", type = {StringType.class}, order=3, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Identifier assigned to a drug at the time of manufacture", formalDefinition="An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs." )
        protected StringType serialNumber;

        private static final long serialVersionUID = 321549563L;

    /**
     * Constructor
     */
      public MedicationKnowledgeBatchComponent() {
        super();
      }

        /**
         * @return {@link #lotNumber} (The assigned lot number of a batch of the specified product.). This is the underlying object with id, value and extensions. The accessor "getLotNumber" gives direct access to the value
         */
        public StringType getLotNumberElement() { 
          if (this.lotNumber == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeBatchComponent.lotNumber");
            else if (Configuration.doAutoCreate())
              this.lotNumber = new StringType(); // bb
          return this.lotNumber;
        }

        public boolean hasLotNumberElement() { 
          return this.lotNumber != null && !this.lotNumber.isEmpty();
        }

        public boolean hasLotNumber() { 
          return this.lotNumber != null && !this.lotNumber.isEmpty();
        }

        /**
         * @param value {@link #lotNumber} (The assigned lot number of a batch of the specified product.). This is the underlying object with id, value and extensions. The accessor "getLotNumber" gives direct access to the value
         */
        public MedicationKnowledgeBatchComponent setLotNumberElement(StringType value) { 
          this.lotNumber = value;
          return this;
        }

        /**
         * @return The assigned lot number of a batch of the specified product.
         */
        public String getLotNumber() { 
          return this.lotNumber == null ? null : this.lotNumber.getValue();
        }

        /**
         * @param value The assigned lot number of a batch of the specified product.
         */
        public MedicationKnowledgeBatchComponent setLotNumber(String value) { 
          if (Utilities.noString(value))
            this.lotNumber = null;
          else {
            if (this.lotNumber == null)
              this.lotNumber = new StringType();
            this.lotNumber.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #expirationDate} (When this specific batch of product will expire.). This is the underlying object with id, value and extensions. The accessor "getExpirationDate" gives direct access to the value
         */
        public DateTimeType getExpirationDateElement() { 
          if (this.expirationDate == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeBatchComponent.expirationDate");
            else if (Configuration.doAutoCreate())
              this.expirationDate = new DateTimeType(); // bb
          return this.expirationDate;
        }

        public boolean hasExpirationDateElement() { 
          return this.expirationDate != null && !this.expirationDate.isEmpty();
        }

        public boolean hasExpirationDate() { 
          return this.expirationDate != null && !this.expirationDate.isEmpty();
        }

        /**
         * @param value {@link #expirationDate} (When this specific batch of product will expire.). This is the underlying object with id, value and extensions. The accessor "getExpirationDate" gives direct access to the value
         */
        public MedicationKnowledgeBatchComponent setExpirationDateElement(DateTimeType value) { 
          this.expirationDate = value;
          return this;
        }

        /**
         * @return When this specific batch of product will expire.
         */
        public Date getExpirationDate() { 
          return this.expirationDate == null ? null : this.expirationDate.getValue();
        }

        /**
         * @param value When this specific batch of product will expire.
         */
        public MedicationKnowledgeBatchComponent setExpirationDate(Date value) { 
          if (value == null)
            this.expirationDate = null;
          else {
            if (this.expirationDate == null)
              this.expirationDate = new DateTimeType();
            this.expirationDate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #serialNumber} (An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs.). This is the underlying object with id, value and extensions. The accessor "getSerialNumber" gives direct access to the value
         */
        public StringType getSerialNumberElement() { 
          if (this.serialNumber == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeBatchComponent.serialNumber");
            else if (Configuration.doAutoCreate())
              this.serialNumber = new StringType(); // bb
          return this.serialNumber;
        }

        public boolean hasSerialNumberElement() { 
          return this.serialNumber != null && !this.serialNumber.isEmpty();
        }

        public boolean hasSerialNumber() { 
          return this.serialNumber != null && !this.serialNumber.isEmpty();
        }

        /**
         * @param value {@link #serialNumber} (An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs.). This is the underlying object with id, value and extensions. The accessor "getSerialNumber" gives direct access to the value
         */
        public MedicationKnowledgeBatchComponent setSerialNumberElement(StringType value) { 
          this.serialNumber = value;
          return this;
        }

        /**
         * @return An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs.
         */
        public String getSerialNumber() { 
          return this.serialNumber == null ? null : this.serialNumber.getValue();
        }

        /**
         * @param value An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs.
         */
        public MedicationKnowledgeBatchComponent setSerialNumber(String value) { 
          if (Utilities.noString(value))
            this.serialNumber = null;
          else {
            if (this.serialNumber == null)
              this.serialNumber = new StringType();
            this.serialNumber.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("lotNumber", "string", "The assigned lot number of a batch of the specified product.", 0, 1, lotNumber));
          children.add(new Property("expirationDate", "dateTime", "When this specific batch of product will expire.", 0, 1, expirationDate));
          children.add(new Property("serialNumber", "string", "An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs.", 0, 1, serialNumber));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 462547450: /*lotNumber*/  return new Property("lotNumber", "string", "The assigned lot number of a batch of the specified product.", 0, 1, lotNumber);
          case -668811523: /*expirationDate*/  return new Property("expirationDate", "dateTime", "When this specific batch of product will expire.", 0, 1, expirationDate);
          case 83787357: /*serialNumber*/  return new Property("serialNumber", "string", "An identifier assigned to a drug at the point of manufacturing and repackaging (at the package or pallet level), sufficient to facilitate the identification, validation, authentication, and tracking and tracking of drugs.", 0, 1, serialNumber);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 462547450: /*lotNumber*/ return this.lotNumber == null ? new Base[0] : new Base[] {this.lotNumber}; // StringType
        case -668811523: /*expirationDate*/ return this.expirationDate == null ? new Base[0] : new Base[] {this.expirationDate}; // DateTimeType
        case 83787357: /*serialNumber*/ return this.serialNumber == null ? new Base[0] : new Base[] {this.serialNumber}; // StringType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 462547450: // lotNumber
          this.lotNumber = castToString(value); // StringType
          return value;
        case -668811523: // expirationDate
          this.expirationDate = castToDateTime(value); // DateTimeType
          return value;
        case 83787357: // serialNumber
          this.serialNumber = castToString(value); // StringType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("lotNumber")) {
          this.lotNumber = castToString(value); // StringType
        } else if (name.equals("expirationDate")) {
          this.expirationDate = castToDateTime(value); // DateTimeType
        } else if (name.equals("serialNumber")) {
          this.serialNumber = castToString(value); // StringType
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 462547450:  return getLotNumberElement();
        case -668811523:  return getExpirationDateElement();
        case 83787357:  return getSerialNumberElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 462547450: /*lotNumber*/ return new String[] {"string"};
        case -668811523: /*expirationDate*/ return new String[] {"dateTime"};
        case 83787357: /*serialNumber*/ return new String[] {"string"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("lotNumber")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.lotNumber");
        }
        else if (name.equals("expirationDate")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.expirationDate");
        }
        else if (name.equals("serialNumber")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.serialNumber");
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeBatchComponent copy() {
        MedicationKnowledgeBatchComponent dst = new MedicationKnowledgeBatchComponent();
        copyValues(dst);
        dst.lotNumber = lotNumber == null ? null : lotNumber.copy();
        dst.expirationDate = expirationDate == null ? null : expirationDate.copy();
        dst.serialNumber = serialNumber == null ? null : serialNumber.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeBatchComponent))
          return false;
        MedicationKnowledgeBatchComponent o = (MedicationKnowledgeBatchComponent) other_;
        return compareDeep(lotNumber, o.lotNumber, true) && compareDeep(expirationDate, o.expirationDate, true)
           && compareDeep(serialNumber, o.serialNumber, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeBatchComponent))
          return false;
        MedicationKnowledgeBatchComponent o = (MedicationKnowledgeBatchComponent) other_;
        return compareValues(lotNumber, o.lotNumber, true) && compareValues(expirationDate, o.expirationDate, true)
           && compareValues(serialNumber, o.serialNumber, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(lotNumber, expirationDate
          , serialNumber);
      }

  public String fhirType() {
    return "MedicationKnowledge.batch";

  }

  }

    @Block()
    public static class MedicationKnowledgePackagingComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * A code that defines the specific type of packaging that the medication can be found in (e.g. blister sleeve, tube, bottle).
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="A code that defines the specific type of packaging that the medication can be found in", formalDefinition="A code that defines the specific type of packaging that the medication can be found in (e.g. blister sleeve, tube, bottle)." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/medicationKnowledge-package")
        protected CodeableConcept type;

        /**
         * The number of product units the package would contain if fully loaded.
         */
        @Child(name = "quantity", type = {SimpleQuantity.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="The number of product units the package would contain if fully loaded", formalDefinition="The number of product units the package would contain if fully loaded." )
        protected SimpleQuantity quantity;

        private static final long serialVersionUID = -216496823L;

    /**
     * Constructor
     */
      public MedicationKnowledgePackagingComponent() {
        super();
      }

        /**
         * @return {@link #type} (A code that defines the specific type of packaging that the medication can be found in (e.g. blister sleeve, tube, bottle).)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgePackagingComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (A code that defines the specific type of packaging that the medication can be found in (e.g. blister sleeve, tube, bottle).)
         */
        public MedicationKnowledgePackagingComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The number of product units the package would contain if fully loaded.)
         */
        public SimpleQuantity getQuantity() { 
          if (this.quantity == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgePackagingComponent.quantity");
            else if (Configuration.doAutoCreate())
              this.quantity = new SimpleQuantity(); // cc
          return this.quantity;
        }

        public boolean hasQuantity() { 
          return this.quantity != null && !this.quantity.isEmpty();
        }

        /**
         * @param value {@link #quantity} (The number of product units the package would contain if fully loaded.)
         */
        public MedicationKnowledgePackagingComponent setQuantity(SimpleQuantity value) { 
          this.quantity = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("type", "CodeableConcept", "A code that defines the specific type of packaging that the medication can be found in (e.g. blister sleeve, tube, bottle).", 0, 1, type));
          children.add(new Property("quantity", "SimpleQuantity", "The number of product units the package would contain if fully loaded.", 0, 1, quantity));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3575610: /*type*/  return new Property("type", "CodeableConcept", "A code that defines the specific type of packaging that the medication can be found in (e.g. blister sleeve, tube, bottle).", 0, 1, type);
          case -1285004149: /*quantity*/  return new Property("quantity", "SimpleQuantity", "The number of product units the package would contain if fully loaded.", 0, 1, quantity);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -1285004149: /*quantity*/ return this.quantity == null ? new Base[0] : new Base[] {this.quantity}; // SimpleQuantity
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1285004149: // quantity
          this.quantity = castToSimpleQuantity(value); // SimpleQuantity
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("quantity")) {
          this.quantity = castToSimpleQuantity(value); // SimpleQuantity
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610:  return getType(); 
        case -1285004149:  return getQuantity(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -1285004149: /*quantity*/ return new String[] {"SimpleQuantity"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("quantity")) {
          this.quantity = new SimpleQuantity();
          return this.quantity;
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgePackagingComponent copy() {
        MedicationKnowledgePackagingComponent dst = new MedicationKnowledgePackagingComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgePackagingComponent))
          return false;
        MedicationKnowledgePackagingComponent o = (MedicationKnowledgePackagingComponent) other_;
        return compareDeep(type, o.type, true) && compareDeep(quantity, o.quantity, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgePackagingComponent))
          return false;
        MedicationKnowledgePackagingComponent o = (MedicationKnowledgePackagingComponent) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(type, quantity);
      }

  public String fhirType() {
    return "MedicationKnowledge.packaging";

  }

  }

    @Block()
    public static class MedicationKnowledgeDrugCharacteristicComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * A code specifying which characteristic of the medicine is being described (for example, colour, shape, imprint).
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Code specifying the type of characteristic of medication", formalDefinition="A code specifying which characteristic of the medicine is being described (for example, colour, shape, imprint)." )
        protected CodeableConcept type;

        /**
         * Description of the characteristic.
         */
        @Child(name = "value", type = {StringType.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Description of the characteristic", formalDefinition="Description of the characteristic." )
        protected StringType value;

        private static final long serialVersionUID = 944223389L;

    /**
     * Constructor
     */
      public MedicationKnowledgeDrugCharacteristicComponent() {
        super();
      }

        /**
         * @return {@link #type} (A code specifying which characteristic of the medicine is being described (for example, colour, shape, imprint).)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeDrugCharacteristicComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (A code specifying which characteristic of the medicine is being described (for example, colour, shape, imprint).)
         */
        public MedicationKnowledgeDrugCharacteristicComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #value} (Description of the characteristic.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public StringType getValueElement() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MedicationKnowledgeDrugCharacteristicComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new StringType(); // bb
          return this.value;
        }

        public boolean hasValueElement() { 
          return this.value != null && !this.value.isEmpty();
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Description of the characteristic.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public MedicationKnowledgeDrugCharacteristicComponent setValueElement(StringType value) { 
          this.value = value;
          return this;
        }

        /**
         * @return Description of the characteristic.
         */
        public String getValue() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value Description of the characteristic.
         */
        public MedicationKnowledgeDrugCharacteristicComponent setValue(String value) { 
          if (Utilities.noString(value))
            this.value = null;
          else {
            if (this.value == null)
              this.value = new StringType();
            this.value.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("type", "CodeableConcept", "A code specifying which characteristic of the medicine is being described (for example, colour, shape, imprint).", 0, 1, type));
          children.add(new Property("value", "string", "Description of the characteristic.", 0, 1, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3575610: /*type*/  return new Property("type", "CodeableConcept", "A code specifying which characteristic of the medicine is being described (for example, colour, shape, imprint).", 0, 1, type);
          case 111972721: /*value*/  return new Property("value", "string", "Description of the characteristic.", 0, 1, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // StringType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 111972721: // value
          this.value = castToString(value); // StringType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("value")) {
          this.value = castToString(value); // StringType
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610:  return getType(); 
        case 111972721:  return getValueElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case 111972721: /*value*/ return new String[] {"string"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("value")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.value");
        }
        else
          return super.addChild(name);
      }

      public MedicationKnowledgeDrugCharacteristicComponent copy() {
        MedicationKnowledgeDrugCharacteristicComponent dst = new MedicationKnowledgeDrugCharacteristicComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeDrugCharacteristicComponent))
          return false;
        MedicationKnowledgeDrugCharacteristicComponent o = (MedicationKnowledgeDrugCharacteristicComponent) other_;
        return compareDeep(type, o.type, true) && compareDeep(value, o.value, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledgeDrugCharacteristicComponent))
          return false;
        MedicationKnowledgeDrugCharacteristicComponent o = (MedicationKnowledgeDrugCharacteristicComponent) other_;
        return compareValues(value, o.value, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(type, value);
      }

  public String fhirType() {
    return "MedicationKnowledge.drugCharacteristic";

  }

  }

    /**
     * A code (or set of codes) that specify this medication, or a textual description if no code is available. Usage note: This could be a standard medication code such as a code from RxNorm, SNOMED CT, IDMP etc. It could also be a national or local formulary code, optionally with translations to other code systems.
     */
    @Child(name = "code", type = {CodeableConcept.class}, order=0, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Codes that identify this medication", formalDefinition="A code (or set of codes) that specify this medication, or a textual description if no code is available. Usage note: This could be a standard medication code such as a code from RxNorm, SNOMED CT, IDMP etc. It could also be a national or local formulary code, optionally with translations to other code systems." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/medication-codes")
    protected CodeableConcept code;

    /**
     * A code to indicate if the medication is in active use.
     */
    @Child(name = "status", type = {CodeType.class}, order=1, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="active | inactive | entered-in-error", formalDefinition="A code to indicate if the medication is in active use." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/medicationKnowledge-status")
    protected Enumeration<MedicationKnowledgeStatus> status;

    /**
     * Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.
     */
    @Child(name = "manufacturer", type = {Organization.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Manufacturer of the item", formalDefinition="Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product." )
    protected Reference manufacturer;

    /**
     * The actual object that is the target of the reference (Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.)
     */
    protected Organization manufacturerTarget;

    /**
     * Describes the form of the item.  Powder; tablets; capsule.
     */
    @Child(name = "form", type = {CodeableConcept.class}, order=3, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="powder | tablets | capsule +", formalDefinition="Describes the form of the item.  Powder; tablets; capsule." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/medication-form-codes")
    protected CodeableConcept form;

    /**
     * Specific amount of the drug in the packaged product.  For example, when specifying a product that has the same strength (For example, Insulin glargine 100 unit per mL solution for injection), this attribute provides additional clarification of the package amount (For example, 3 mL, 10mL, etc).
     */
    @Child(name = "amount", type = {SimpleQuantity.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Amount of drug in package", formalDefinition="Specific amount of the drug in the packaged product.  For example, when specifying a product that has the same strength (For example, Insulin glargine 100 unit per mL solution for injection), this attribute provides additional clarification of the package amount (For example, 3 mL, 10mL, etc)." )
    protected SimpleQuantity amount;

    /**
     * Additional names for a medication, for example, the name(s) given to a medication in different countries.
     */
    @Child(name = "name", type = {StringType.class}, order=5, min=1, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Additional names for a medication", formalDefinition="Additional names for a medication, for example, the name(s) given to a medication in different countries." )
    protected List<StringType> name;

    /**
     * Todo - need to consider what this might include -.
     */
    @Child(name = "description", type = {StringType.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Description of the medication", formalDefinition="Todo - need to consider what this might include -." )
    protected List<StringType> description;

    /**
     * Associated or related medicaqtions.  For example, if the medication is a branded product (e.g. Crestor), this is the Therapeutic Moeity (e.g. Rosuvastatin) or if this is a generic medication (e.g. Rosuvastatin), this would link to a branded product (e.g. Crestor).
     */
    @Child(name = "generalizedMedicine", type = {Medication.class}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Related medications, either branded products or generic medications", formalDefinition="Associated or related medicaqtions.  For example, if the medication is a branded product (e.g. Crestor), this is the Therapeutic Moeity (e.g. Rosuvastatin) or if this is a generic medication (e.g. Rosuvastatin), this would link to a branded product (e.g. Crestor)." )
    protected List<Reference> generalizedMedicine;
    /**
     * The actual objects that are the target of the reference (Associated or related medicaqtions.  For example, if the medication is a branded product (e.g. Crestor), this is the Therapeutic Moeity (e.g. Rosuvastatin) or if this is a generic medication (e.g. Rosuvastatin), this would link to a branded product (e.g. Crestor).)
     */
    protected List<Medication> generalizedMedicineTarget;


    /**
     * Category of the medication (e.g. branded product, therapeutic moeity, etc).
     */
    @Child(name = "type", type = {CodeableConcept.class}, order=8, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Category of the medication", formalDefinition="Category of the medication (e.g. branded product, therapeutic moeity, etc)." )
    protected List<CodeableConcept> type;

    /**
     * Associated documentation about the medication (e.g. professional monograph, patient educaton monograph).
     */
    @Child(name = "monograph", type = {DocumentReference.class}, order=9, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Associated documentation about the medication", formalDefinition="Associated documentation about the medication (e.g. professional monograph, patient educaton monograph)." )
    protected List<Reference> monograph;
    /**
     * The actual objects that are the target of the reference (Associated documentation about the medication (e.g. professional monograph, patient educaton monograph).)
     */
    protected List<DocumentReference> monographTarget;


    /**
     * The time required for any specified property (e.g., the concentration of a substance in the body) to decrease by half.
     */
    @Child(name = "halfLifePeriod", type = {Duration.class}, order=10, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Time required for specified property to decrease by half", formalDefinition="The time required for any specified property (e.g., the concentration of a substance in the body) to decrease by half." )
    protected Duration halfLifePeriod;

    /**
     * Identifies a particular constituent of interest in the product.
     */
    @Child(name = "ingredient", type = {}, order=11, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Active or inactive ingredient", formalDefinition="Identifies a particular constituent of interest in the product." )
    protected List<MedicationKnowledgeIngredientComponent> ingredient;

    /**
     * The price of the medication.
     */
    @Child(name = "cost", type = {}, order=12, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="The pricing of the medication", formalDefinition="The price of the medication." )
    protected List<MedicationKnowledgeCostComponent> cost;

    /**
     * The program under which the medication is reviewed.
     */
    @Child(name = "monitoringProgram", type = {}, order=13, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Program under which a medication is reviewed", formalDefinition="The program under which the medication is reviewed." )
    protected List<MedicationKnowledgeMonitoringProgramComponent> monitoringProgram;

    /**
     * Guidelines for the administration of the medication.
     */
    @Child(name = "administrationGuidelines", type = {}, order=14, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Gudelines for administration of the medication", formalDefinition="Guidelines for the administration of the medication." )
    protected List<MedicationKnowledgeAdministrationGuidelinesComponent> administrationGuidelines;

    /**
     * Categorization of the medication within a formulary or classification system.
     */
    @Child(name = "medicineClassification", type = {}, order=15, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Categorization of the medication within a formulary or classification system", formalDefinition="Categorization of the medication within a formulary or classification system." )
    protected List<MedicationKnowledgeMedicineClassificationComponent> medicineClassification;

    /**
     * Information that only applies to packages (not products).
     */
    @Child(name = "batch", type = {}, order=16, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Details about packaged medications", formalDefinition="Information that only applies to packages (not products)." )
    protected MedicationKnowledgeBatchComponent batch;

    /**
     * Information that only applies to packages (not products).
     */
    @Child(name = "packaging", type = {}, order=17, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Details about packaged medications", formalDefinition="Information that only applies to packages (not products)." )
    protected MedicationKnowledgePackagingComponent packaging;

    /**
     * Specifies descriptive properties of the medicine, such as color, shape, imprints, etc.
     */
    @Child(name = "drugCharacteristic", type = {}, order=18, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Specifies descriptive properties of the medicine", formalDefinition="Specifies descriptive properties of the medicine, such as color, shape, imprints, etc." )
    protected List<MedicationKnowledgeDrugCharacteristicComponent> drugCharacteristic;

    private static final long serialVersionUID = -1401057878L;

  /**
   * Constructor
   */
    public MedicationKnowledge() {
      super();
    }

    /**
     * @return {@link #code} (A code (or set of codes) that specify this medication, or a textual description if no code is available. Usage note: This could be a standard medication code such as a code from RxNorm, SNOMED CT, IDMP etc. It could also be a national or local formulary code, optionally with translations to other code systems.)
     */
    public CodeableConcept getCode() { 
      if (this.code == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.code");
        else if (Configuration.doAutoCreate())
          this.code = new CodeableConcept(); // cc
      return this.code;
    }

    public boolean hasCode() { 
      return this.code != null && !this.code.isEmpty();
    }

    /**
     * @param value {@link #code} (A code (or set of codes) that specify this medication, or a textual description if no code is available. Usage note: This could be a standard medication code such as a code from RxNorm, SNOMED CT, IDMP etc. It could also be a national or local formulary code, optionally with translations to other code systems.)
     */
    public MedicationKnowledge setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    /**
     * @return {@link #status} (A code to indicate if the medication is in active use.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<MedicationKnowledgeStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<MedicationKnowledgeStatus>(new MedicationKnowledgeStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (A code to indicate if the medication is in active use.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public MedicationKnowledge setStatusElement(Enumeration<MedicationKnowledgeStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return A code to indicate if the medication is in active use.
     */
    public MedicationKnowledgeStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value A code to indicate if the medication is in active use.
     */
    public MedicationKnowledge setStatus(MedicationKnowledgeStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<MedicationKnowledgeStatus>(new MedicationKnowledgeStatusEnumFactory());
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #manufacturer} (Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.)
     */
    public Reference getManufacturer() { 
      if (this.manufacturer == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.manufacturer");
        else if (Configuration.doAutoCreate())
          this.manufacturer = new Reference(); // cc
      return this.manufacturer;
    }

    public boolean hasManufacturer() { 
      return this.manufacturer != null && !this.manufacturer.isEmpty();
    }

    /**
     * @param value {@link #manufacturer} (Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.)
     */
    public MedicationKnowledge setManufacturer(Reference value) { 
      this.manufacturer = value;
      return this;
    }

    /**
     * @return {@link #manufacturer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.)
     */
    public Organization getManufacturerTarget() { 
      if (this.manufacturerTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.manufacturer");
        else if (Configuration.doAutoCreate())
          this.manufacturerTarget = new Organization(); // aa
      return this.manufacturerTarget;
    }

    /**
     * @param value {@link #manufacturer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.)
     */
    public MedicationKnowledge setManufacturerTarget(Organization value) { 
      this.manufacturerTarget = value;
      return this;
    }

    /**
     * @return {@link #form} (Describes the form of the item.  Powder; tablets; capsule.)
     */
    public CodeableConcept getForm() { 
      if (this.form == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.form");
        else if (Configuration.doAutoCreate())
          this.form = new CodeableConcept(); // cc
      return this.form;
    }

    public boolean hasForm() { 
      return this.form != null && !this.form.isEmpty();
    }

    /**
     * @param value {@link #form} (Describes the form of the item.  Powder; tablets; capsule.)
     */
    public MedicationKnowledge setForm(CodeableConcept value) { 
      this.form = value;
      return this;
    }

    /**
     * @return {@link #amount} (Specific amount of the drug in the packaged product.  For example, when specifying a product that has the same strength (For example, Insulin glargine 100 unit per mL solution for injection), this attribute provides additional clarification of the package amount (For example, 3 mL, 10mL, etc).)
     */
    public SimpleQuantity getAmount() { 
      if (this.amount == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.amount");
        else if (Configuration.doAutoCreate())
          this.amount = new SimpleQuantity(); // cc
      return this.amount;
    }

    public boolean hasAmount() { 
      return this.amount != null && !this.amount.isEmpty();
    }

    /**
     * @param value {@link #amount} (Specific amount of the drug in the packaged product.  For example, when specifying a product that has the same strength (For example, Insulin glargine 100 unit per mL solution for injection), this attribute provides additional clarification of the package amount (For example, 3 mL, 10mL, etc).)
     */
    public MedicationKnowledge setAmount(SimpleQuantity value) { 
      this.amount = value;
      return this;
    }

    /**
     * @return {@link #name} (Additional names for a medication, for example, the name(s) given to a medication in different countries.)
     */
    public List<StringType> getName() { 
      if (this.name == null)
        this.name = new ArrayList<StringType>();
      return this.name;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setName(List<StringType> theName) { 
      this.name = theName;
      return this;
    }

    public boolean hasName() { 
      if (this.name == null)
        return false;
      for (StringType item : this.name)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #name} (Additional names for a medication, for example, the name(s) given to a medication in different countries.)
     */
    public StringType addNameElement() {//2 
      StringType t = new StringType();
      if (this.name == null)
        this.name = new ArrayList<StringType>();
      this.name.add(t);
      return t;
    }

    /**
     * @param value {@link #name} (Additional names for a medication, for example, the name(s) given to a medication in different countries.)
     */
    public MedicationKnowledge addName(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      if (this.name == null)
        this.name = new ArrayList<StringType>();
      this.name.add(t);
      return this;
    }

    /**
     * @param value {@link #name} (Additional names for a medication, for example, the name(s) given to a medication in different countries.)
     */
    public boolean hasName(String value) { 
      if (this.name == null)
        return false;
      for (StringType v : this.name)
        if (v.getValue().equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #description} (Todo - need to consider what this might include -.)
     */
    public List<StringType> getDescription() { 
      if (this.description == null)
        this.description = new ArrayList<StringType>();
      return this.description;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setDescription(List<StringType> theDescription) { 
      this.description = theDescription;
      return this;
    }

    public boolean hasDescription() { 
      if (this.description == null)
        return false;
      for (StringType item : this.description)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #description} (Todo - need to consider what this might include -.)
     */
    public StringType addDescriptionElement() {//2 
      StringType t = new StringType();
      if (this.description == null)
        this.description = new ArrayList<StringType>();
      this.description.add(t);
      return t;
    }

    /**
     * @param value {@link #description} (Todo - need to consider what this might include -.)
     */
    public MedicationKnowledge addDescription(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      if (this.description == null)
        this.description = new ArrayList<StringType>();
      this.description.add(t);
      return this;
    }

    /**
     * @param value {@link #description} (Todo - need to consider what this might include -.)
     */
    public boolean hasDescription(String value) { 
      if (this.description == null)
        return false;
      for (StringType v : this.description)
        if (v.getValue().equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #generalizedMedicine} (Associated or related medicaqtions.  For example, if the medication is a branded product (e.g. Crestor), this is the Therapeutic Moeity (e.g. Rosuvastatin) or if this is a generic medication (e.g. Rosuvastatin), this would link to a branded product (e.g. Crestor).)
     */
    public List<Reference> getGeneralizedMedicine() { 
      if (this.generalizedMedicine == null)
        this.generalizedMedicine = new ArrayList<Reference>();
      return this.generalizedMedicine;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setGeneralizedMedicine(List<Reference> theGeneralizedMedicine) { 
      this.generalizedMedicine = theGeneralizedMedicine;
      return this;
    }

    public boolean hasGeneralizedMedicine() { 
      if (this.generalizedMedicine == null)
        return false;
      for (Reference item : this.generalizedMedicine)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addGeneralizedMedicine() { //3
      Reference t = new Reference();
      if (this.generalizedMedicine == null)
        this.generalizedMedicine = new ArrayList<Reference>();
      this.generalizedMedicine.add(t);
      return t;
    }

    public MedicationKnowledge addGeneralizedMedicine(Reference t) { //3
      if (t == null)
        return this;
      if (this.generalizedMedicine == null)
        this.generalizedMedicine = new ArrayList<Reference>();
      this.generalizedMedicine.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #generalizedMedicine}, creating it if it does not already exist
     */
    public Reference getGeneralizedMedicineFirstRep() { 
      if (getGeneralizedMedicine().isEmpty()) {
        addGeneralizedMedicine();
      }
      return getGeneralizedMedicine().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Medication> getGeneralizedMedicineTarget() { 
      if (this.generalizedMedicineTarget == null)
        this.generalizedMedicineTarget = new ArrayList<Medication>();
      return this.generalizedMedicineTarget;
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public Medication addGeneralizedMedicineTarget() { 
      Medication r = new Medication();
      if (this.generalizedMedicineTarget == null)
        this.generalizedMedicineTarget = new ArrayList<Medication>();
      this.generalizedMedicineTarget.add(r);
      return r;
    }

    /**
     * @return {@link #type} (Category of the medication (e.g. branded product, therapeutic moeity, etc).)
     */
    public List<CodeableConcept> getType() { 
      if (this.type == null)
        this.type = new ArrayList<CodeableConcept>();
      return this.type;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setType(List<CodeableConcept> theType) { 
      this.type = theType;
      return this;
    }

    public boolean hasType() { 
      if (this.type == null)
        return false;
      for (CodeableConcept item : this.type)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addType() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.type == null)
        this.type = new ArrayList<CodeableConcept>();
      this.type.add(t);
      return t;
    }

    public MedicationKnowledge addType(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.type == null)
        this.type = new ArrayList<CodeableConcept>();
      this.type.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #type}, creating it if it does not already exist
     */
    public CodeableConcept getTypeFirstRep() { 
      if (getType().isEmpty()) {
        addType();
      }
      return getType().get(0);
    }

    /**
     * @return {@link #monograph} (Associated documentation about the medication (e.g. professional monograph, patient educaton monograph).)
     */
    public List<Reference> getMonograph() { 
      if (this.monograph == null)
        this.monograph = new ArrayList<Reference>();
      return this.monograph;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setMonograph(List<Reference> theMonograph) { 
      this.monograph = theMonograph;
      return this;
    }

    public boolean hasMonograph() { 
      if (this.monograph == null)
        return false;
      for (Reference item : this.monograph)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addMonograph() { //3
      Reference t = new Reference();
      if (this.monograph == null)
        this.monograph = new ArrayList<Reference>();
      this.monograph.add(t);
      return t;
    }

    public MedicationKnowledge addMonograph(Reference t) { //3
      if (t == null)
        return this;
      if (this.monograph == null)
        this.monograph = new ArrayList<Reference>();
      this.monograph.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #monograph}, creating it if it does not already exist
     */
    public Reference getMonographFirstRep() { 
      if (getMonograph().isEmpty()) {
        addMonograph();
      }
      return getMonograph().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<DocumentReference> getMonographTarget() { 
      if (this.monographTarget == null)
        this.monographTarget = new ArrayList<DocumentReference>();
      return this.monographTarget;
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public DocumentReference addMonographTarget() { 
      DocumentReference r = new DocumentReference();
      if (this.monographTarget == null)
        this.monographTarget = new ArrayList<DocumentReference>();
      this.monographTarget.add(r);
      return r;
    }

    /**
     * @return {@link #halfLifePeriod} (The time required for any specified property (e.g., the concentration of a substance in the body) to decrease by half.)
     */
    public Duration getHalfLifePeriod() { 
      if (this.halfLifePeriod == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.halfLifePeriod");
        else if (Configuration.doAutoCreate())
          this.halfLifePeriod = new Duration(); // cc
      return this.halfLifePeriod;
    }

    public boolean hasHalfLifePeriod() { 
      return this.halfLifePeriod != null && !this.halfLifePeriod.isEmpty();
    }

    /**
     * @param value {@link #halfLifePeriod} (The time required for any specified property (e.g., the concentration of a substance in the body) to decrease by half.)
     */
    public MedicationKnowledge setHalfLifePeriod(Duration value) { 
      this.halfLifePeriod = value;
      return this;
    }

    /**
     * @return {@link #ingredient} (Identifies a particular constituent of interest in the product.)
     */
    public List<MedicationKnowledgeIngredientComponent> getIngredient() { 
      if (this.ingredient == null)
        this.ingredient = new ArrayList<MedicationKnowledgeIngredientComponent>();
      return this.ingredient;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setIngredient(List<MedicationKnowledgeIngredientComponent> theIngredient) { 
      this.ingredient = theIngredient;
      return this;
    }

    public boolean hasIngredient() { 
      if (this.ingredient == null)
        return false;
      for (MedicationKnowledgeIngredientComponent item : this.ingredient)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public MedicationKnowledgeIngredientComponent addIngredient() { //3
      MedicationKnowledgeIngredientComponent t = new MedicationKnowledgeIngredientComponent();
      if (this.ingredient == null)
        this.ingredient = new ArrayList<MedicationKnowledgeIngredientComponent>();
      this.ingredient.add(t);
      return t;
    }

    public MedicationKnowledge addIngredient(MedicationKnowledgeIngredientComponent t) { //3
      if (t == null)
        return this;
      if (this.ingredient == null)
        this.ingredient = new ArrayList<MedicationKnowledgeIngredientComponent>();
      this.ingredient.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #ingredient}, creating it if it does not already exist
     */
    public MedicationKnowledgeIngredientComponent getIngredientFirstRep() { 
      if (getIngredient().isEmpty()) {
        addIngredient();
      }
      return getIngredient().get(0);
    }

    /**
     * @return {@link #cost} (The price of the medication.)
     */
    public List<MedicationKnowledgeCostComponent> getCost() { 
      if (this.cost == null)
        this.cost = new ArrayList<MedicationKnowledgeCostComponent>();
      return this.cost;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setCost(List<MedicationKnowledgeCostComponent> theCost) { 
      this.cost = theCost;
      return this;
    }

    public boolean hasCost() { 
      if (this.cost == null)
        return false;
      for (MedicationKnowledgeCostComponent item : this.cost)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public MedicationKnowledgeCostComponent addCost() { //3
      MedicationKnowledgeCostComponent t = new MedicationKnowledgeCostComponent();
      if (this.cost == null)
        this.cost = new ArrayList<MedicationKnowledgeCostComponent>();
      this.cost.add(t);
      return t;
    }

    public MedicationKnowledge addCost(MedicationKnowledgeCostComponent t) { //3
      if (t == null)
        return this;
      if (this.cost == null)
        this.cost = new ArrayList<MedicationKnowledgeCostComponent>();
      this.cost.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #cost}, creating it if it does not already exist
     */
    public MedicationKnowledgeCostComponent getCostFirstRep() { 
      if (getCost().isEmpty()) {
        addCost();
      }
      return getCost().get(0);
    }

    /**
     * @return {@link #monitoringProgram} (The program under which the medication is reviewed.)
     */
    public List<MedicationKnowledgeMonitoringProgramComponent> getMonitoringProgram() { 
      if (this.monitoringProgram == null)
        this.monitoringProgram = new ArrayList<MedicationKnowledgeMonitoringProgramComponent>();
      return this.monitoringProgram;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setMonitoringProgram(List<MedicationKnowledgeMonitoringProgramComponent> theMonitoringProgram) { 
      this.monitoringProgram = theMonitoringProgram;
      return this;
    }

    public boolean hasMonitoringProgram() { 
      if (this.monitoringProgram == null)
        return false;
      for (MedicationKnowledgeMonitoringProgramComponent item : this.monitoringProgram)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public MedicationKnowledgeMonitoringProgramComponent addMonitoringProgram() { //3
      MedicationKnowledgeMonitoringProgramComponent t = new MedicationKnowledgeMonitoringProgramComponent();
      if (this.monitoringProgram == null)
        this.monitoringProgram = new ArrayList<MedicationKnowledgeMonitoringProgramComponent>();
      this.monitoringProgram.add(t);
      return t;
    }

    public MedicationKnowledge addMonitoringProgram(MedicationKnowledgeMonitoringProgramComponent t) { //3
      if (t == null)
        return this;
      if (this.monitoringProgram == null)
        this.monitoringProgram = new ArrayList<MedicationKnowledgeMonitoringProgramComponent>();
      this.monitoringProgram.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #monitoringProgram}, creating it if it does not already exist
     */
    public MedicationKnowledgeMonitoringProgramComponent getMonitoringProgramFirstRep() { 
      if (getMonitoringProgram().isEmpty()) {
        addMonitoringProgram();
      }
      return getMonitoringProgram().get(0);
    }

    /**
     * @return {@link #administrationGuidelines} (Guidelines for the administration of the medication.)
     */
    public List<MedicationKnowledgeAdministrationGuidelinesComponent> getAdministrationGuidelines() { 
      if (this.administrationGuidelines == null)
        this.administrationGuidelines = new ArrayList<MedicationKnowledgeAdministrationGuidelinesComponent>();
      return this.administrationGuidelines;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setAdministrationGuidelines(List<MedicationKnowledgeAdministrationGuidelinesComponent> theAdministrationGuidelines) { 
      this.administrationGuidelines = theAdministrationGuidelines;
      return this;
    }

    public boolean hasAdministrationGuidelines() { 
      if (this.administrationGuidelines == null)
        return false;
      for (MedicationKnowledgeAdministrationGuidelinesComponent item : this.administrationGuidelines)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public MedicationKnowledgeAdministrationGuidelinesComponent addAdministrationGuidelines() { //3
      MedicationKnowledgeAdministrationGuidelinesComponent t = new MedicationKnowledgeAdministrationGuidelinesComponent();
      if (this.administrationGuidelines == null)
        this.administrationGuidelines = new ArrayList<MedicationKnowledgeAdministrationGuidelinesComponent>();
      this.administrationGuidelines.add(t);
      return t;
    }

    public MedicationKnowledge addAdministrationGuidelines(MedicationKnowledgeAdministrationGuidelinesComponent t) { //3
      if (t == null)
        return this;
      if (this.administrationGuidelines == null)
        this.administrationGuidelines = new ArrayList<MedicationKnowledgeAdministrationGuidelinesComponent>();
      this.administrationGuidelines.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #administrationGuidelines}, creating it if it does not already exist
     */
    public MedicationKnowledgeAdministrationGuidelinesComponent getAdministrationGuidelinesFirstRep() { 
      if (getAdministrationGuidelines().isEmpty()) {
        addAdministrationGuidelines();
      }
      return getAdministrationGuidelines().get(0);
    }

    /**
     * @return {@link #medicineClassification} (Categorization of the medication within a formulary or classification system.)
     */
    public List<MedicationKnowledgeMedicineClassificationComponent> getMedicineClassification() { 
      if (this.medicineClassification == null)
        this.medicineClassification = new ArrayList<MedicationKnowledgeMedicineClassificationComponent>();
      return this.medicineClassification;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setMedicineClassification(List<MedicationKnowledgeMedicineClassificationComponent> theMedicineClassification) { 
      this.medicineClassification = theMedicineClassification;
      return this;
    }

    public boolean hasMedicineClassification() { 
      if (this.medicineClassification == null)
        return false;
      for (MedicationKnowledgeMedicineClassificationComponent item : this.medicineClassification)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public MedicationKnowledgeMedicineClassificationComponent addMedicineClassification() { //3
      MedicationKnowledgeMedicineClassificationComponent t = new MedicationKnowledgeMedicineClassificationComponent();
      if (this.medicineClassification == null)
        this.medicineClassification = new ArrayList<MedicationKnowledgeMedicineClassificationComponent>();
      this.medicineClassification.add(t);
      return t;
    }

    public MedicationKnowledge addMedicineClassification(MedicationKnowledgeMedicineClassificationComponent t) { //3
      if (t == null)
        return this;
      if (this.medicineClassification == null)
        this.medicineClassification = new ArrayList<MedicationKnowledgeMedicineClassificationComponent>();
      this.medicineClassification.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #medicineClassification}, creating it if it does not already exist
     */
    public MedicationKnowledgeMedicineClassificationComponent getMedicineClassificationFirstRep() { 
      if (getMedicineClassification().isEmpty()) {
        addMedicineClassification();
      }
      return getMedicineClassification().get(0);
    }

    /**
     * @return {@link #batch} (Information that only applies to packages (not products).)
     */
    public MedicationKnowledgeBatchComponent getBatch() { 
      if (this.batch == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.batch");
        else if (Configuration.doAutoCreate())
          this.batch = new MedicationKnowledgeBatchComponent(); // cc
      return this.batch;
    }

    public boolean hasBatch() { 
      return this.batch != null && !this.batch.isEmpty();
    }

    /**
     * @param value {@link #batch} (Information that only applies to packages (not products).)
     */
    public MedicationKnowledge setBatch(MedicationKnowledgeBatchComponent value) { 
      this.batch = value;
      return this;
    }

    /**
     * @return {@link #packaging} (Information that only applies to packages (not products).)
     */
    public MedicationKnowledgePackagingComponent getPackaging() { 
      if (this.packaging == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MedicationKnowledge.packaging");
        else if (Configuration.doAutoCreate())
          this.packaging = new MedicationKnowledgePackagingComponent(); // cc
      return this.packaging;
    }

    public boolean hasPackaging() { 
      return this.packaging != null && !this.packaging.isEmpty();
    }

    /**
     * @param value {@link #packaging} (Information that only applies to packages (not products).)
     */
    public MedicationKnowledge setPackaging(MedicationKnowledgePackagingComponent value) { 
      this.packaging = value;
      return this;
    }

    /**
     * @return {@link #drugCharacteristic} (Specifies descriptive properties of the medicine, such as color, shape, imprints, etc.)
     */
    public List<MedicationKnowledgeDrugCharacteristicComponent> getDrugCharacteristic() { 
      if (this.drugCharacteristic == null)
        this.drugCharacteristic = new ArrayList<MedicationKnowledgeDrugCharacteristicComponent>();
      return this.drugCharacteristic;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public MedicationKnowledge setDrugCharacteristic(List<MedicationKnowledgeDrugCharacteristicComponent> theDrugCharacteristic) { 
      this.drugCharacteristic = theDrugCharacteristic;
      return this;
    }

    public boolean hasDrugCharacteristic() { 
      if (this.drugCharacteristic == null)
        return false;
      for (MedicationKnowledgeDrugCharacteristicComponent item : this.drugCharacteristic)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public MedicationKnowledgeDrugCharacteristicComponent addDrugCharacteristic() { //3
      MedicationKnowledgeDrugCharacteristicComponent t = new MedicationKnowledgeDrugCharacteristicComponent();
      if (this.drugCharacteristic == null)
        this.drugCharacteristic = new ArrayList<MedicationKnowledgeDrugCharacteristicComponent>();
      this.drugCharacteristic.add(t);
      return t;
    }

    public MedicationKnowledge addDrugCharacteristic(MedicationKnowledgeDrugCharacteristicComponent t) { //3
      if (t == null)
        return this;
      if (this.drugCharacteristic == null)
        this.drugCharacteristic = new ArrayList<MedicationKnowledgeDrugCharacteristicComponent>();
      this.drugCharacteristic.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #drugCharacteristic}, creating it if it does not already exist
     */
    public MedicationKnowledgeDrugCharacteristicComponent getDrugCharacteristicFirstRep() { 
      if (getDrugCharacteristic().isEmpty()) {
        addDrugCharacteristic();
      }
      return getDrugCharacteristic().get(0);
    }

      protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("code", "CodeableConcept", "A code (or set of codes) that specify this medication, or a textual description if no code is available. Usage note: This could be a standard medication code such as a code from RxNorm, SNOMED CT, IDMP etc. It could also be a national or local formulary code, optionally with translations to other code systems.", 0, 1, code));
        children.add(new Property("status", "code", "A code to indicate if the medication is in active use.", 0, 1, status));
        children.add(new Property("manufacturer", "Reference(Organization)", "Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.", 0, 1, manufacturer));
        children.add(new Property("form", "CodeableConcept", "Describes the form of the item.  Powder; tablets; capsule.", 0, 1, form));
        children.add(new Property("amount", "SimpleQuantity", "Specific amount of the drug in the packaged product.  For example, when specifying a product that has the same strength (For example, Insulin glargine 100 unit per mL solution for injection), this attribute provides additional clarification of the package amount (For example, 3 mL, 10mL, etc).", 0, 1, amount));
        children.add(new Property("name", "string", "Additional names for a medication, for example, the name(s) given to a medication in different countries.", 0, java.lang.Integer.MAX_VALUE, name));
        children.add(new Property("description", "string", "Todo - need to consider what this might include -.", 0, java.lang.Integer.MAX_VALUE, description));
        children.add(new Property("generalizedMedicine", "Reference(Medication)", "Associated or related medicaqtions.  For example, if the medication is a branded product (e.g. Crestor), this is the Therapeutic Moeity (e.g. Rosuvastatin) or if this is a generic medication (e.g. Rosuvastatin), this would link to a branded product (e.g. Crestor).", 0, java.lang.Integer.MAX_VALUE, generalizedMedicine));
        children.add(new Property("type", "CodeableConcept", "Category of the medication (e.g. branded product, therapeutic moeity, etc).", 0, java.lang.Integer.MAX_VALUE, type));
        children.add(new Property("monograph", "Reference(DocumentReference)", "Associated documentation about the medication (e.g. professional monograph, patient educaton monograph).", 0, java.lang.Integer.MAX_VALUE, monograph));
        children.add(new Property("halfLifePeriod", "Duration", "The time required for any specified property (e.g., the concentration of a substance in the body) to decrease by half.", 0, 1, halfLifePeriod));
        children.add(new Property("ingredient", "", "Identifies a particular constituent of interest in the product.", 0, java.lang.Integer.MAX_VALUE, ingredient));
        children.add(new Property("cost", "", "The price of the medication.", 0, java.lang.Integer.MAX_VALUE, cost));
        children.add(new Property("monitoringProgram", "", "The program under which the medication is reviewed.", 0, java.lang.Integer.MAX_VALUE, monitoringProgram));
        children.add(new Property("administrationGuidelines", "", "Guidelines for the administration of the medication.", 0, java.lang.Integer.MAX_VALUE, administrationGuidelines));
        children.add(new Property("medicineClassification", "", "Categorization of the medication within a formulary or classification system.", 0, java.lang.Integer.MAX_VALUE, medicineClassification));
        children.add(new Property("batch", "", "Information that only applies to packages (not products).", 0, 1, batch));
        children.add(new Property("packaging", "", "Information that only applies to packages (not products).", 0, 1, packaging));
        children.add(new Property("drugCharacteristic", "", "Specifies descriptive properties of the medicine, such as color, shape, imprints, etc.", 0, java.lang.Integer.MAX_VALUE, drugCharacteristic));
      }

      @Override
      public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
        switch (_hash) {
        case 3059181: /*code*/  return new Property("code", "CodeableConcept", "A code (or set of codes) that specify this medication, or a textual description if no code is available. Usage note: This could be a standard medication code such as a code from RxNorm, SNOMED CT, IDMP etc. It could also be a national or local formulary code, optionally with translations to other code systems.", 0, 1, code);
        case -892481550: /*status*/  return new Property("status", "code", "A code to indicate if the medication is in active use.", 0, 1, status);
        case -1969347631: /*manufacturer*/  return new Property("manufacturer", "Reference(Organization)", "Describes the details of the manufacturer of the medication product.  This is not intended to represent the distributor of a medication product.", 0, 1, manufacturer);
        case 3148996: /*form*/  return new Property("form", "CodeableConcept", "Describes the form of the item.  Powder; tablets; capsule.", 0, 1, form);
        case -1413853096: /*amount*/  return new Property("amount", "SimpleQuantity", "Specific amount of the drug in the packaged product.  For example, when specifying a product that has the same strength (For example, Insulin glargine 100 unit per mL solution for injection), this attribute provides additional clarification of the package amount (For example, 3 mL, 10mL, etc).", 0, 1, amount);
        case 3373707: /*name*/  return new Property("name", "string", "Additional names for a medication, for example, the name(s) given to a medication in different countries.", 0, java.lang.Integer.MAX_VALUE, name);
        case -1724546052: /*description*/  return new Property("description", "string", "Todo - need to consider what this might include -.", 0, java.lang.Integer.MAX_VALUE, description);
        case 1667541202: /*generalizedMedicine*/  return new Property("generalizedMedicine", "Reference(Medication)", "Associated or related medicaqtions.  For example, if the medication is a branded product (e.g. Crestor), this is the Therapeutic Moeity (e.g. Rosuvastatin) or if this is a generic medication (e.g. Rosuvastatin), this would link to a branded product (e.g. Crestor).", 0, java.lang.Integer.MAX_VALUE, generalizedMedicine);
        case 3575610: /*type*/  return new Property("type", "CodeableConcept", "Category of the medication (e.g. branded product, therapeutic moeity, etc).", 0, java.lang.Integer.MAX_VALUE, type);
        case -1442980789: /*monograph*/  return new Property("monograph", "Reference(DocumentReference)", "Associated documentation about the medication (e.g. professional monograph, patient educaton monograph).", 0, java.lang.Integer.MAX_VALUE, monograph);
        case -628810640: /*halfLifePeriod*/  return new Property("halfLifePeriod", "Duration", "The time required for any specified property (e.g., the concentration of a substance in the body) to decrease by half.", 0, 1, halfLifePeriod);
        case -206409263: /*ingredient*/  return new Property("ingredient", "", "Identifies a particular constituent of interest in the product.", 0, java.lang.Integer.MAX_VALUE, ingredient);
        case 3059661: /*cost*/  return new Property("cost", "", "The price of the medication.", 0, java.lang.Integer.MAX_VALUE, cost);
        case 569848092: /*monitoringProgram*/  return new Property("monitoringProgram", "", "The program under which the medication is reviewed.", 0, java.lang.Integer.MAX_VALUE, monitoringProgram);
        case 496930945: /*administrationGuidelines*/  return new Property("administrationGuidelines", "", "Guidelines for the administration of the medication.", 0, java.lang.Integer.MAX_VALUE, administrationGuidelines);
        case 1791551680: /*medicineClassification*/  return new Property("medicineClassification", "", "Categorization of the medication within a formulary or classification system.", 0, java.lang.Integer.MAX_VALUE, medicineClassification);
        case 93509434: /*batch*/  return new Property("batch", "", "Information that only applies to packages (not products).", 0, 1, batch);
        case 1802065795: /*packaging*/  return new Property("packaging", "", "Information that only applies to packages (not products).", 0, 1, packaging);
        case -844126885: /*drugCharacteristic*/  return new Property("drugCharacteristic", "", "Specifies descriptive properties of the medicine, such as color, shape, imprints, etc.", 0, java.lang.Integer.MAX_VALUE, drugCharacteristic);
        default: return super.getNamedProperty(_hash, _name, _checkValid);
        }

      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // CodeableConcept
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<MedicationKnowledgeStatus>
        case -1969347631: /*manufacturer*/ return this.manufacturer == null ? new Base[0] : new Base[] {this.manufacturer}; // Reference
        case 3148996: /*form*/ return this.form == null ? new Base[0] : new Base[] {this.form}; // CodeableConcept
        case -1413853096: /*amount*/ return this.amount == null ? new Base[0] : new Base[] {this.amount}; // SimpleQuantity
        case 3373707: /*name*/ return this.name == null ? new Base[0] : this.name.toArray(new Base[this.name.size()]); // StringType
        case -1724546052: /*description*/ return this.description == null ? new Base[0] : this.description.toArray(new Base[this.description.size()]); // StringType
        case 1667541202: /*generalizedMedicine*/ return this.generalizedMedicine == null ? new Base[0] : this.generalizedMedicine.toArray(new Base[this.generalizedMedicine.size()]); // Reference
        case 3575610: /*type*/ return this.type == null ? new Base[0] : this.type.toArray(new Base[this.type.size()]); // CodeableConcept
        case -1442980789: /*monograph*/ return this.monograph == null ? new Base[0] : this.monograph.toArray(new Base[this.monograph.size()]); // Reference
        case -628810640: /*halfLifePeriod*/ return this.halfLifePeriod == null ? new Base[0] : new Base[] {this.halfLifePeriod}; // Duration
        case -206409263: /*ingredient*/ return this.ingredient == null ? new Base[0] : this.ingredient.toArray(new Base[this.ingredient.size()]); // MedicationKnowledgeIngredientComponent
        case 3059661: /*cost*/ return this.cost == null ? new Base[0] : this.cost.toArray(new Base[this.cost.size()]); // MedicationKnowledgeCostComponent
        case 569848092: /*monitoringProgram*/ return this.monitoringProgram == null ? new Base[0] : this.monitoringProgram.toArray(new Base[this.monitoringProgram.size()]); // MedicationKnowledgeMonitoringProgramComponent
        case 496930945: /*administrationGuidelines*/ return this.administrationGuidelines == null ? new Base[0] : this.administrationGuidelines.toArray(new Base[this.administrationGuidelines.size()]); // MedicationKnowledgeAdministrationGuidelinesComponent
        case 1791551680: /*medicineClassification*/ return this.medicineClassification == null ? new Base[0] : this.medicineClassification.toArray(new Base[this.medicineClassification.size()]); // MedicationKnowledgeMedicineClassificationComponent
        case 93509434: /*batch*/ return this.batch == null ? new Base[0] : new Base[] {this.batch}; // MedicationKnowledgeBatchComponent
        case 1802065795: /*packaging*/ return this.packaging == null ? new Base[0] : new Base[] {this.packaging}; // MedicationKnowledgePackagingComponent
        case -844126885: /*drugCharacteristic*/ return this.drugCharacteristic == null ? new Base[0] : this.drugCharacteristic.toArray(new Base[this.drugCharacteristic.size()]); // MedicationKnowledgeDrugCharacteristicComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          this.code = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -892481550: // status
          value = new MedicationKnowledgeStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<MedicationKnowledgeStatus>
          return value;
        case -1969347631: // manufacturer
          this.manufacturer = castToReference(value); // Reference
          return value;
        case 3148996: // form
          this.form = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1413853096: // amount
          this.amount = castToSimpleQuantity(value); // SimpleQuantity
          return value;
        case 3373707: // name
          this.getName().add(castToString(value)); // StringType
          return value;
        case -1724546052: // description
          this.getDescription().add(castToString(value)); // StringType
          return value;
        case 1667541202: // generalizedMedicine
          this.getGeneralizedMedicine().add(castToReference(value)); // Reference
          return value;
        case 3575610: // type
          this.getType().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -1442980789: // monograph
          this.getMonograph().add(castToReference(value)); // Reference
          return value;
        case -628810640: // halfLifePeriod
          this.halfLifePeriod = castToDuration(value); // Duration
          return value;
        case -206409263: // ingredient
          this.getIngredient().add((MedicationKnowledgeIngredientComponent) value); // MedicationKnowledgeIngredientComponent
          return value;
        case 3059661: // cost
          this.getCost().add((MedicationKnowledgeCostComponent) value); // MedicationKnowledgeCostComponent
          return value;
        case 569848092: // monitoringProgram
          this.getMonitoringProgram().add((MedicationKnowledgeMonitoringProgramComponent) value); // MedicationKnowledgeMonitoringProgramComponent
          return value;
        case 496930945: // administrationGuidelines
          this.getAdministrationGuidelines().add((MedicationKnowledgeAdministrationGuidelinesComponent) value); // MedicationKnowledgeAdministrationGuidelinesComponent
          return value;
        case 1791551680: // medicineClassification
          this.getMedicineClassification().add((MedicationKnowledgeMedicineClassificationComponent) value); // MedicationKnowledgeMedicineClassificationComponent
          return value;
        case 93509434: // batch
          this.batch = (MedicationKnowledgeBatchComponent) value; // MedicationKnowledgeBatchComponent
          return value;
        case 1802065795: // packaging
          this.packaging = (MedicationKnowledgePackagingComponent) value; // MedicationKnowledgePackagingComponent
          return value;
        case -844126885: // drugCharacteristic
          this.getDrugCharacteristic().add((MedicationKnowledgeDrugCharacteristicComponent) value); // MedicationKnowledgeDrugCharacteristicComponent
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          this.code = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("status")) {
          value = new MedicationKnowledgeStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<MedicationKnowledgeStatus>
        } else if (name.equals("manufacturer")) {
          this.manufacturer = castToReference(value); // Reference
        } else if (name.equals("form")) {
          this.form = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("amount")) {
          this.amount = castToSimpleQuantity(value); // SimpleQuantity
        } else if (name.equals("name")) {
          this.getName().add(castToString(value));
        } else if (name.equals("description")) {
          this.getDescription().add(castToString(value));
        } else if (name.equals("generalizedMedicine")) {
          this.getGeneralizedMedicine().add(castToReference(value));
        } else if (name.equals("type")) {
          this.getType().add(castToCodeableConcept(value));
        } else if (name.equals("monograph")) {
          this.getMonograph().add(castToReference(value));
        } else if (name.equals("halfLifePeriod")) {
          this.halfLifePeriod = castToDuration(value); // Duration
        } else if (name.equals("ingredient")) {
          this.getIngredient().add((MedicationKnowledgeIngredientComponent) value);
        } else if (name.equals("cost")) {
          this.getCost().add((MedicationKnowledgeCostComponent) value);
        } else if (name.equals("monitoringProgram")) {
          this.getMonitoringProgram().add((MedicationKnowledgeMonitoringProgramComponent) value);
        } else if (name.equals("administrationGuidelines")) {
          this.getAdministrationGuidelines().add((MedicationKnowledgeAdministrationGuidelinesComponent) value);
        } else if (name.equals("medicineClassification")) {
          this.getMedicineClassification().add((MedicationKnowledgeMedicineClassificationComponent) value);
        } else if (name.equals("batch")) {
          this.batch = (MedicationKnowledgeBatchComponent) value; // MedicationKnowledgeBatchComponent
        } else if (name.equals("packaging")) {
          this.packaging = (MedicationKnowledgePackagingComponent) value; // MedicationKnowledgePackagingComponent
        } else if (name.equals("drugCharacteristic")) {
          this.getDrugCharacteristic().add((MedicationKnowledgeDrugCharacteristicComponent) value);
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCode(); 
        case -892481550:  return getStatusElement();
        case -1969347631:  return getManufacturer(); 
        case 3148996:  return getForm(); 
        case -1413853096:  return getAmount(); 
        case 3373707:  return addNameElement();
        case -1724546052:  return addDescriptionElement();
        case 1667541202:  return addGeneralizedMedicine(); 
        case 3575610:  return addType(); 
        case -1442980789:  return addMonograph(); 
        case -628810640:  return getHalfLifePeriod(); 
        case -206409263:  return addIngredient(); 
        case 3059661:  return addCost(); 
        case 569848092:  return addMonitoringProgram(); 
        case 496930945:  return addAdministrationGuidelines(); 
        case 1791551680:  return addMedicineClassification(); 
        case 93509434:  return getBatch(); 
        case 1802065795:  return getPackaging(); 
        case -844126885:  return addDrugCharacteristic(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"CodeableConcept"};
        case -892481550: /*status*/ return new String[] {"code"};
        case -1969347631: /*manufacturer*/ return new String[] {"Reference"};
        case 3148996: /*form*/ return new String[] {"CodeableConcept"};
        case -1413853096: /*amount*/ return new String[] {"SimpleQuantity"};
        case 3373707: /*name*/ return new String[] {"string"};
        case -1724546052: /*description*/ return new String[] {"string"};
        case 1667541202: /*generalizedMedicine*/ return new String[] {"Reference"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -1442980789: /*monograph*/ return new String[] {"Reference"};
        case -628810640: /*halfLifePeriod*/ return new String[] {"Duration"};
        case -206409263: /*ingredient*/ return new String[] {};
        case 3059661: /*cost*/ return new String[] {};
        case 569848092: /*monitoringProgram*/ return new String[] {};
        case 496930945: /*administrationGuidelines*/ return new String[] {};
        case 1791551680: /*medicineClassification*/ return new String[] {};
        case 93509434: /*batch*/ return new String[] {};
        case 1802065795: /*packaging*/ return new String[] {};
        case -844126885: /*drugCharacteristic*/ return new String[] {};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          this.code = new CodeableConcept();
          return this.code;
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.status");
        }
        else if (name.equals("manufacturer")) {
          this.manufacturer = new Reference();
          return this.manufacturer;
        }
        else if (name.equals("form")) {
          this.form = new CodeableConcept();
          return this.form;
        }
        else if (name.equals("amount")) {
          this.amount = new SimpleQuantity();
          return this.amount;
        }
        else if (name.equals("name")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.name");
        }
        else if (name.equals("description")) {
          throw new FHIRException("Cannot call addChild on a primitive type MedicationKnowledge.description");
        }
        else if (name.equals("generalizedMedicine")) {
          return addGeneralizedMedicine();
        }
        else if (name.equals("type")) {
          return addType();
        }
        else if (name.equals("monograph")) {
          return addMonograph();
        }
        else if (name.equals("halfLifePeriod")) {
          this.halfLifePeriod = new Duration();
          return this.halfLifePeriod;
        }
        else if (name.equals("ingredient")) {
          return addIngredient();
        }
        else if (name.equals("cost")) {
          return addCost();
        }
        else if (name.equals("monitoringProgram")) {
          return addMonitoringProgram();
        }
        else if (name.equals("administrationGuidelines")) {
          return addAdministrationGuidelines();
        }
        else if (name.equals("medicineClassification")) {
          return addMedicineClassification();
        }
        else if (name.equals("batch")) {
          this.batch = new MedicationKnowledgeBatchComponent();
          return this.batch;
        }
        else if (name.equals("packaging")) {
          this.packaging = new MedicationKnowledgePackagingComponent();
          return this.packaging;
        }
        else if (name.equals("drugCharacteristic")) {
          return addDrugCharacteristic();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "MedicationKnowledge";

  }

      public MedicationKnowledge copy() {
        MedicationKnowledge dst = new MedicationKnowledge();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.status = status == null ? null : status.copy();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.form = form == null ? null : form.copy();
        dst.amount = amount == null ? null : amount.copy();
        if (name != null) {
          dst.name = new ArrayList<StringType>();
          for (StringType i : name)
            dst.name.add(i.copy());
        };
        if (description != null) {
          dst.description = new ArrayList<StringType>();
          for (StringType i : description)
            dst.description.add(i.copy());
        };
        if (generalizedMedicine != null) {
          dst.generalizedMedicine = new ArrayList<Reference>();
          for (Reference i : generalizedMedicine)
            dst.generalizedMedicine.add(i.copy());
        };
        if (type != null) {
          dst.type = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : type)
            dst.type.add(i.copy());
        };
        if (monograph != null) {
          dst.monograph = new ArrayList<Reference>();
          for (Reference i : monograph)
            dst.monograph.add(i.copy());
        };
        dst.halfLifePeriod = halfLifePeriod == null ? null : halfLifePeriod.copy();
        if (ingredient != null) {
          dst.ingredient = new ArrayList<MedicationKnowledgeIngredientComponent>();
          for (MedicationKnowledgeIngredientComponent i : ingredient)
            dst.ingredient.add(i.copy());
        };
        if (cost != null) {
          dst.cost = new ArrayList<MedicationKnowledgeCostComponent>();
          for (MedicationKnowledgeCostComponent i : cost)
            dst.cost.add(i.copy());
        };
        if (monitoringProgram != null) {
          dst.monitoringProgram = new ArrayList<MedicationKnowledgeMonitoringProgramComponent>();
          for (MedicationKnowledgeMonitoringProgramComponent i : monitoringProgram)
            dst.monitoringProgram.add(i.copy());
        };
        if (administrationGuidelines != null) {
          dst.administrationGuidelines = new ArrayList<MedicationKnowledgeAdministrationGuidelinesComponent>();
          for (MedicationKnowledgeAdministrationGuidelinesComponent i : administrationGuidelines)
            dst.administrationGuidelines.add(i.copy());
        };
        if (medicineClassification != null) {
          dst.medicineClassification = new ArrayList<MedicationKnowledgeMedicineClassificationComponent>();
          for (MedicationKnowledgeMedicineClassificationComponent i : medicineClassification)
            dst.medicineClassification.add(i.copy());
        };
        dst.batch = batch == null ? null : batch.copy();
        dst.packaging = packaging == null ? null : packaging.copy();
        if (drugCharacteristic != null) {
          dst.drugCharacteristic = new ArrayList<MedicationKnowledgeDrugCharacteristicComponent>();
          for (MedicationKnowledgeDrugCharacteristicComponent i : drugCharacteristic)
            dst.drugCharacteristic.add(i.copy());
        };
        return dst;
      }

      protected MedicationKnowledge typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledge))
          return false;
        MedicationKnowledge o = (MedicationKnowledge) other_;
        return compareDeep(code, o.code, true) && compareDeep(status, o.status, true) && compareDeep(manufacturer, o.manufacturer, true)
           && compareDeep(form, o.form, true) && compareDeep(amount, o.amount, true) && compareDeep(name, o.name, true)
           && compareDeep(description, o.description, true) && compareDeep(generalizedMedicine, o.generalizedMedicine, true)
           && compareDeep(type, o.type, true) && compareDeep(monograph, o.monograph, true) && compareDeep(halfLifePeriod, o.halfLifePeriod, true)
           && compareDeep(ingredient, o.ingredient, true) && compareDeep(cost, o.cost, true) && compareDeep(monitoringProgram, o.monitoringProgram, true)
           && compareDeep(administrationGuidelines, o.administrationGuidelines, true) && compareDeep(medicineClassification, o.medicineClassification, true)
           && compareDeep(batch, o.batch, true) && compareDeep(packaging, o.packaging, true) && compareDeep(drugCharacteristic, o.drugCharacteristic, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof MedicationKnowledge))
          return false;
        MedicationKnowledge o = (MedicationKnowledge) other_;
        return compareValues(status, o.status, true) && compareValues(name, o.name, true) && compareValues(description, o.description, true)
          ;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, status, manufacturer
          , form, amount, name, description, generalizedMedicine, type, monograph, halfLifePeriod
          , ingredient, cost, monitoringProgram, administrationGuidelines, medicineClassification
          , batch, packaging, drugCharacteristic);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MedicationKnowledge;
   }

 /**
   * Search parameter: <b>code</b>
   * <p>
   * Description: <b>Codes that identify this medication</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.code</b><br>
   * </p>
   */
  @SearchParamDefinition(name="code", path="MedicationKnowledge.code", description="Codes that identify this medication", type="token" )
  public static final String SP_CODE = "code";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>code</b>
   * <p>
   * Description: <b>Codes that identify this medication</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.code</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam CODE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_CODE);

 /**
   * Search parameter: <b>ingredient</b>
   * <p>
   * Description: <b>Medication(s) or substance(s) contained in the medication</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MedicationKnowledge.ingredient.itemReference</b><br>
   * </p>
   */
  @SearchParamDefinition(name="ingredient", path="MedicationKnowledge.ingredient.item.as(Reference)", description="Medication(s) or substance(s) contained in the medication", type="reference", target={Medication.class, Substance.class } )
  public static final String SP_INGREDIENT = "ingredient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>ingredient</b>
   * <p>
   * Description: <b>Medication(s) or substance(s) contained in the medication</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MedicationKnowledge.ingredient.itemReference</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam INGREDIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_INGREDIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>MedicationKnowledge:ingredient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_INGREDIENT = new ca.uhn.fhir.model.api.Include("MedicationKnowledge:ingredient").toLocked();

 /**
   * Search parameter: <b>lot-number</b>
   * <p>
   * Description: <b>Identifier assigned to batch</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.batch.lotNumber</b><br>
   * </p>
   */
  @SearchParamDefinition(name="lot-number", path="MedicationKnowledge.batch.lotNumber", description="Identifier assigned to batch", type="token" )
  public static final String SP_LOT_NUMBER = "lot-number";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>lot-number</b>
   * <p>
   * Description: <b>Identifier assigned to batch</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.batch.lotNumber</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam LOT_NUMBER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_LOT_NUMBER);

 /**
   * Search parameter: <b>serial-number</b>
   * <p>
   * Description: <b>Identifier assigned to a drug at the time of manufacture</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.batch.serialNumber</b><br>
   * </p>
   */
  @SearchParamDefinition(name="serial-number", path="MedicationKnowledge.batch.serialNumber", description="Identifier assigned to a drug at the time of manufacture", type="token" )
  public static final String SP_SERIAL_NUMBER = "serial-number";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>serial-number</b>
   * <p>
   * Description: <b>Identifier assigned to a drug at the time of manufacture</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.batch.serialNumber</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam SERIAL_NUMBER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_SERIAL_NUMBER);

 /**
   * Search parameter: <b>classification-type</b>
   * <p>
   * Description: <b>The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.medicineClassification.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="classification-type", path="MedicationKnowledge.medicineClassification.type", description="The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification)", type="token" )
  public static final String SP_CLASSIFICATION_TYPE = "classification-type";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>classification-type</b>
   * <p>
   * Description: <b>The type of category for the medication (for example, therapeutic classification, therapeutic sub-classification)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.medicineClassification.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam CLASSIFICATION_TYPE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_CLASSIFICATION_TYPE);

 /**
   * Search parameter: <b>classification</b>
   * <p>
   * Description: <b>Specific category assigned to the medication</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.medicineClassification.classification</b><br>
   * </p>
   */
  @SearchParamDefinition(name="classification", path="MedicationKnowledge.medicineClassification.classification", description="Specific category assigned to the medication", type="token" )
  public static final String SP_CLASSIFICATION = "classification";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>classification</b>
   * <p>
   * Description: <b>Specific category assigned to the medication</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.medicineClassification.classification</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam CLASSIFICATION = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_CLASSIFICATION);

 /**
   * Search parameter: <b>manufacturer</b>
   * <p>
   * Description: <b>Manufacturer of the item</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MedicationKnowledge.manufacturer</b><br>
   * </p>
   */
  @SearchParamDefinition(name="manufacturer", path="MedicationKnowledge.manufacturer", description="Manufacturer of the item", type="reference", target={Organization.class } )
  public static final String SP_MANUFACTURER = "manufacturer";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>manufacturer</b>
   * <p>
   * Description: <b>Manufacturer of the item</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MedicationKnowledge.manufacturer</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam MANUFACTURER = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_MANUFACTURER);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>MedicationKnowledge:manufacturer</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_MANUFACTURER = new ca.uhn.fhir.model.api.Include("MedicationKnowledge:manufacturer").toLocked();

 /**
   * Search parameter: <b>ingredient-code</b>
   * <p>
   * Description: <b>Medication(s) or substance(s) contained in the medication</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.ingredient.itemCodeableConcept</b><br>
   * </p>
   */
  @SearchParamDefinition(name="ingredient-code", path="MedicationKnowledge.ingredient.item.as(CodeableConcept)", description="Medication(s) or substance(s) contained in the medication", type="token" )
  public static final String SP_INGREDIENT_CODE = "ingredient-code";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>ingredient-code</b>
   * <p>
   * Description: <b>Medication(s) or substance(s) contained in the medication</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.ingredient.itemCodeableConcept</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam INGREDIENT_CODE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_INGREDIENT_CODE);

 /**
   * Search parameter: <b>form</b>
   * <p>
   * Description: <b>powder | tablets | capsule +</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.form</b><br>
   * </p>
   */
  @SearchParamDefinition(name="form", path="MedicationKnowledge.form", description="powder | tablets | capsule +", type="token" )
  public static final String SP_FORM = "form";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>form</b>
   * <p>
   * Description: <b>powder | tablets | capsule +</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.form</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam FORM = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_FORM);

 /**
   * Search parameter: <b>monograph</b>
   * <p>
   * Description: <b>Associated documentation about the medication</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MedicationKnowledge.monograph</b><br>
   * </p>
   */
  @SearchParamDefinition(name="monograph", path="MedicationKnowledge.monograph", description="Associated documentation about the medication", type="reference", target={DocumentReference.class } )
  public static final String SP_MONOGRAPH = "monograph";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>monograph</b>
   * <p>
   * Description: <b>Associated documentation about the medication</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MedicationKnowledge.monograph</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam MONOGRAPH = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_MONOGRAPH);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>MedicationKnowledge:monograph</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_MONOGRAPH = new ca.uhn.fhir.model.api.Include("MedicationKnowledge:monograph").toLocked();

 /**
   * Search parameter: <b>monitoring-program-name</b>
   * <p>
   * Description: <b>Name of the reviewing program</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.monitoringProgram.name</b><br>
   * </p>
   */
  @SearchParamDefinition(name="monitoring-program-name", path="MedicationKnowledge.monitoringProgram.name", description="Name of the reviewing program", type="token" )
  public static final String SP_MONITORING_PROGRAM_NAME = "monitoring-program-name";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>monitoring-program-name</b>
   * <p>
   * Description: <b>Name of the reviewing program</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.monitoringProgram.name</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam MONITORING_PROGRAM_NAME = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_MONITORING_PROGRAM_NAME);

 /**
   * Search parameter: <b>monitoring-program-type</b>
   * <p>
   * Description: <b>Type of program under which the medication is monitored</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.monitoringProgram.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="monitoring-program-type", path="MedicationKnowledge.monitoringProgram.type", description="Type of program under which the medication is monitored", type="token" )
  public static final String SP_MONITORING_PROGRAM_TYPE = "monitoring-program-type";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>monitoring-program-type</b>
   * <p>
   * Description: <b>Type of program under which the medication is monitored</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.monitoringProgram.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam MONITORING_PROGRAM_TYPE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_MONITORING_PROGRAM_TYPE);

 /**
   * Search parameter: <b>formulary</b>
   * <p>
   * Description: <b>The formulary which applies to the price</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.cost.formulary</b><br>
   * </p>
   */
  @SearchParamDefinition(name="formulary", path="MedicationKnowledge.cost.formulary", description="The formulary which applies to the price", type="token" )
  public static final String SP_FORMULARY = "formulary";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>formulary</b>
   * <p>
   * Description: <b>The formulary which applies to the price</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.cost.formulary</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam FORMULARY = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_FORMULARY);

 /**
   * Search parameter: <b>expiration-date</b>
   * <p>
   * Description: <b>When batch will expire</b><br>
   * Type: <b>date</b><br>
   * Path: <b>MedicationKnowledge.batch.expirationDate</b><br>
   * </p>
   */
  @SearchParamDefinition(name="expiration-date", path="MedicationKnowledge.batch.expirationDate", description="When batch will expire", type="date" )
  public static final String SP_EXPIRATION_DATE = "expiration-date";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>expiration-date</b>
   * <p>
   * Description: <b>When batch will expire</b><br>
   * Type: <b>date</b><br>
   * Path: <b>MedicationKnowledge.batch.expirationDate</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam EXPIRATION_DATE = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_EXPIRATION_DATE);

 /**
   * Search parameter: <b>status</b>
   * <p>
   * Description: <b>active | inactive | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="status", path="MedicationKnowledge.status", description="active | inactive | entered-in-error", type="token" )
  public static final String SP_STATUS = "status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>status</b>
   * <p>
   * Description: <b>active | inactive | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>MedicationKnowledge.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_STATUS);


}

