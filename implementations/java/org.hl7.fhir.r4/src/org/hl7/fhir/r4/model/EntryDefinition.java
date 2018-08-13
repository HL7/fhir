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

// Generated on Sun, Aug 12, 2018 21:51+1000 for FHIR v3.4.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.r4.model.Enumerations.*;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.exceptions.FHIRException;
/**
 * Catalog entries are wrappers that contextualize items included in a catalog.
 */
@ResourceDef(name="EntryDefinition", profile="http://hl7.org/fhir/Profile/EntryDefinition")
public class EntryDefinition extends DomainResource {

    public enum EntryDefinitionRelationType {
        /**
         * the related entry represents an item to be provided as input to the current item.
         */
        HASINPUT, 
        /**
         * the related entry represents an item produced as out put of the current item.
         */
        HASOUTPUT, 
        /**
         * the related entry represents an item (product or material or substance) used by the current item.
         */
        USES, 
        /**
         * the related entry represents an activity that may be triggered by the current item.
         */
        TRIGGERS, 
        /**
         * the related entry represents an item that replaces the current retired item.
         */
        ISREPLACEDBY, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static EntryDefinitionRelationType fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("has-input".equals(codeString))
          return HASINPUT;
        if ("has-output".equals(codeString))
          return HASOUTPUT;
        if ("uses".equals(codeString))
          return USES;
        if ("triggers".equals(codeString))
          return TRIGGERS;
        if ("is-replaced-by".equals(codeString))
          return ISREPLACEDBY;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown EntryDefinitionRelationType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case HASINPUT: return "has-input";
            case HASOUTPUT: return "has-output";
            case USES: return "uses";
            case TRIGGERS: return "triggers";
            case ISREPLACEDBY: return "is-replaced-by";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case HASINPUT: return "http://hl7.org/fhir/relation-type";
            case HASOUTPUT: return "http://hl7.org/fhir/relation-type";
            case USES: return "http://hl7.org/fhir/relation-type";
            case TRIGGERS: return "http://hl7.org/fhir/relation-type";
            case ISREPLACEDBY: return "http://hl7.org/fhir/relation-type";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case HASINPUT: return "the related entry represents an item to be provided as input to the current item.";
            case HASOUTPUT: return "the related entry represents an item produced as out put of the current item.";
            case USES: return "the related entry represents an item (product or material or substance) used by the current item.";
            case TRIGGERS: return "the related entry represents an activity that may be triggered by the current item.";
            case ISREPLACEDBY: return "the related entry represents an item that replaces the current retired item.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case HASINPUT: return "Has Input";
            case HASOUTPUT: return "Has Output";
            case USES: return "Uses";
            case TRIGGERS: return "Triggers";
            case ISREPLACEDBY: return "Replaced By";
            default: return "?";
          }
        }
    }

  public static class EntryDefinitionRelationTypeEnumFactory implements EnumFactory<EntryDefinitionRelationType> {
    public EntryDefinitionRelationType fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("has-input".equals(codeString))
          return EntryDefinitionRelationType.HASINPUT;
        if ("has-output".equals(codeString))
          return EntryDefinitionRelationType.HASOUTPUT;
        if ("uses".equals(codeString))
          return EntryDefinitionRelationType.USES;
        if ("triggers".equals(codeString))
          return EntryDefinitionRelationType.TRIGGERS;
        if ("is-replaced-by".equals(codeString))
          return EntryDefinitionRelationType.ISREPLACEDBY;
        throw new IllegalArgumentException("Unknown EntryDefinitionRelationType code '"+codeString+"'");
        }
        public Enumeration<EntryDefinitionRelationType> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<EntryDefinitionRelationType>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("has-input".equals(codeString))
          return new Enumeration<EntryDefinitionRelationType>(this, EntryDefinitionRelationType.HASINPUT);
        if ("has-output".equals(codeString))
          return new Enumeration<EntryDefinitionRelationType>(this, EntryDefinitionRelationType.HASOUTPUT);
        if ("uses".equals(codeString))
          return new Enumeration<EntryDefinitionRelationType>(this, EntryDefinitionRelationType.USES);
        if ("triggers".equals(codeString))
          return new Enumeration<EntryDefinitionRelationType>(this, EntryDefinitionRelationType.TRIGGERS);
        if ("is-replaced-by".equals(codeString))
          return new Enumeration<EntryDefinitionRelationType>(this, EntryDefinitionRelationType.ISREPLACEDBY);
        throw new FHIRException("Unknown EntryDefinitionRelationType code '"+codeString+"'");
        }
    public String toCode(EntryDefinitionRelationType code) {
      if (code == EntryDefinitionRelationType.HASINPUT)
        return "has-input";
      if (code == EntryDefinitionRelationType.HASOUTPUT)
        return "has-output";
      if (code == EntryDefinitionRelationType.USES)
        return "uses";
      if (code == EntryDefinitionRelationType.TRIGGERS)
        return "triggers";
      if (code == EntryDefinitionRelationType.ISREPLACEDBY)
        return "is-replaced-by";
      return "?";
      }
    public String toSystem(EntryDefinitionRelationType code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class EntryDefinitionRelatedEntryComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.
         */
        @Child(name = "relationtype", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="has-input | has-output | uses | triggers | is-replaced-by", formalDefinition="The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/relation-type")
        protected Enumeration<EntryDefinitionRelationType> relationtype;

        /**
         * The reference to the related item.
         */
        @Child(name = "item", type = {EntryDefinition.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="The reference to the related item", formalDefinition="The reference to the related item." )
        protected Reference item;

        /**
         * The actual object that is the target of the reference (The reference to the related item.)
         */
        protected EntryDefinition itemTarget;

        private static final long serialVersionUID = -1520659563L;

    /**
     * Constructor
     */
      public EntryDefinitionRelatedEntryComponent() {
        super();
      }

    /**
     * Constructor
     */
      public EntryDefinitionRelatedEntryComponent(Enumeration<EntryDefinitionRelationType> relationtype, Reference item) {
        super();
        this.relationtype = relationtype;
        this.item = item;
      }

        /**
         * @return {@link #relationtype} (The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.). This is the underlying object with id, value and extensions. The accessor "getRelationtype" gives direct access to the value
         */
        public Enumeration<EntryDefinitionRelationType> getRelationtypeElement() { 
          if (this.relationtype == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create EntryDefinitionRelatedEntryComponent.relationtype");
            else if (Configuration.doAutoCreate())
              this.relationtype = new Enumeration<EntryDefinitionRelationType>(new EntryDefinitionRelationTypeEnumFactory()); // bb
          return this.relationtype;
        }

        public boolean hasRelationtypeElement() { 
          return this.relationtype != null && !this.relationtype.isEmpty();
        }

        public boolean hasRelationtype() { 
          return this.relationtype != null && !this.relationtype.isEmpty();
        }

        /**
         * @param value {@link #relationtype} (The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.). This is the underlying object with id, value and extensions. The accessor "getRelationtype" gives direct access to the value
         */
        public EntryDefinitionRelatedEntryComponent setRelationtypeElement(Enumeration<EntryDefinitionRelationType> value) { 
          this.relationtype = value;
          return this;
        }

        /**
         * @return The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.
         */
        public EntryDefinitionRelationType getRelationtype() { 
          return this.relationtype == null ? null : this.relationtype.getValue();
        }

        /**
         * @param value The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.
         */
        public EntryDefinitionRelatedEntryComponent setRelationtype(EntryDefinitionRelationType value) { 
            if (this.relationtype == null)
              this.relationtype = new Enumeration<EntryDefinitionRelationType>(new EntryDefinitionRelationTypeEnumFactory());
            this.relationtype.setValue(value);
          return this;
        }

        /**
         * @return {@link #item} (The reference to the related item.)
         */
        public Reference getItem() { 
          if (this.item == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create EntryDefinitionRelatedEntryComponent.item");
            else if (Configuration.doAutoCreate())
              this.item = new Reference(); // cc
          return this.item;
        }

        public boolean hasItem() { 
          return this.item != null && !this.item.isEmpty();
        }

        /**
         * @param value {@link #item} (The reference to the related item.)
         */
        public EntryDefinitionRelatedEntryComponent setItem(Reference value) { 
          this.item = value;
          return this;
        }

        /**
         * @return {@link #item} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The reference to the related item.)
         */
        public EntryDefinition getItemTarget() { 
          if (this.itemTarget == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create EntryDefinitionRelatedEntryComponent.item");
            else if (Configuration.doAutoCreate())
              this.itemTarget = new EntryDefinition(); // aa
          return this.itemTarget;
        }

        /**
         * @param value {@link #item} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The reference to the related item.)
         */
        public EntryDefinitionRelatedEntryComponent setItemTarget(EntryDefinition value) { 
          this.itemTarget = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("relationtype", "code", "The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.", 0, 1, relationtype));
          children.add(new Property("item", "Reference(EntryDefinition)", "The reference to the related item.", 0, 1, item));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case -261805258: /*relationtype*/  return new Property("relationtype", "code", "The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.", 0, 1, relationtype);
          case 3242771: /*item*/  return new Property("item", "Reference(EntryDefinition)", "The reference to the related item.", 0, 1, item);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -261805258: /*relationtype*/ return this.relationtype == null ? new Base[0] : new Base[] {this.relationtype}; // Enumeration<EntryDefinitionRelationType>
        case 3242771: /*item*/ return this.item == null ? new Base[0] : new Base[] {this.item}; // Reference
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -261805258: // relationtype
          value = new EntryDefinitionRelationTypeEnumFactory().fromType(castToCode(value));
          this.relationtype = (Enumeration) value; // Enumeration<EntryDefinitionRelationType>
          return value;
        case 3242771: // item
          this.item = castToReference(value); // Reference
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("relationtype")) {
          value = new EntryDefinitionRelationTypeEnumFactory().fromType(castToCode(value));
          this.relationtype = (Enumeration) value; // Enumeration<EntryDefinitionRelationType>
        } else if (name.equals("item")) {
          this.item = castToReference(value); // Reference
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -261805258:  return getRelationtypeElement();
        case 3242771:  return getItem(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -261805258: /*relationtype*/ return new String[] {"code"};
        case 3242771: /*item*/ return new String[] {"Reference"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("relationtype")) {
          throw new FHIRException("Cannot call addChild on a primitive type EntryDefinition.relationtype");
        }
        else if (name.equals("item")) {
          this.item = new Reference();
          return this.item;
        }
        else
          return super.addChild(name);
      }

      public EntryDefinitionRelatedEntryComponent copy() {
        EntryDefinitionRelatedEntryComponent dst = new EntryDefinitionRelatedEntryComponent();
        copyValues(dst);
        dst.relationtype = relationtype == null ? null : relationtype.copy();
        dst.item = item == null ? null : item.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof EntryDefinitionRelatedEntryComponent))
          return false;
        EntryDefinitionRelatedEntryComponent o = (EntryDefinitionRelatedEntryComponent) other_;
        return compareDeep(relationtype, o.relationtype, true) && compareDeep(item, o.item, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof EntryDefinitionRelatedEntryComponent))
          return false;
        EntryDefinitionRelatedEntryComponent o = (EntryDefinitionRelatedEntryComponent) other_;
        return compareValues(relationtype, o.relationtype, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(relationtype, item);
      }

  public String fhirType() {
    return "EntryDefinition.relatedEntry";

  }

  }

    /**
     * Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Unique identifier of the catalog item", formalDefinition="Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code." )
    protected List<Identifier> identifier;

    /**
     * The type of item - medication, device, service, protocol or other.
     */
    @Child(name = "type", type = {CodeableConcept.class}, order=1, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="The type of item - medication, device, service, protocol or other", formalDefinition="The type of item - medication, device, service, protocol or other." )
    protected CodeableConcept type;

    /**
     * Whether the entry represents an orderable item.
     */
    @Child(name = "orderable", type = {BooleanType.class}, order=2, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Whether the entry represents an orderable item", formalDefinition="Whether the entry represents an orderable item." )
    protected BooleanType orderable;

    /**
     * The item in a catalog or definition.
     */
    @Child(name = "referencedItem", type = {Medication.class, Device.class, Organization.class, Practitioner.class, HealthcareService.class, ActivityDefinition.class, PlanDefinition.class, SpecimenDefinition.class, ObservationDefinition.class, Binary.class}, order=3, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="The item that is being defined", formalDefinition="The item in a catalog or definition." )
    protected Reference referencedItem;

    /**
     * The actual object that is the target of the reference (The item in a catalog or definition.)
     */
    protected Resource referencedItemTarget;

    /**
     * Used in supporting related concepts, e.g. NDC to RxNorm.
     */
    @Child(name = "additionalIdentifier", type = {Identifier.class}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Any additional identifier(s) for the catalog item, in the same granularity or concept", formalDefinition="Used in supporting related concepts, e.g. NDC to RxNorm." )
    protected List<Identifier> additionalIdentifier;

    /**
     * Classes of devices, or ATC for medication.
     */
    @Child(name = "classification", type = {CodeableConcept.class}, order=5, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Classification (category or class) of the item entry", formalDefinition="Classes of devices, or ATC for medication." )
    protected List<CodeableConcept> classification;

    /**
     * Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.
     */
    @Child(name = "status", type = {CodeType.class}, order=6, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="draft | active | retired | unknown", formalDefinition="Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/publication-status")
    protected Enumeration<PublicationStatus> status;

    /**
     * The time period in which this catalog entry is expected to be active.
     */
    @Child(name = "validityPeriod", type = {Period.class}, order=7, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="The time period in which this catalog entry is expected to be active", formalDefinition="The time period in which this catalog entry is expected to be active." )
    protected Period validityPeriod;

    /**
     * Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated.
     */
    @Child(name = "lastUpdated", type = {DateTimeType.class}, order=8, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="When was this catalog last updated", formalDefinition="Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated." )
    protected DateTimeType lastUpdated;

    /**
     * Used for examplefor Out of Formulary, or any specifics.
     */
    @Child(name = "additionalCharacteristic", type = {CodeableConcept.class}, order=9, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Additional characteristics of the catalog entry", formalDefinition="Used for examplefor Out of Formulary, or any specifics." )
    protected List<CodeableConcept> additionalCharacteristic;

    /**
     * User for example for ATC classification, or.
     */
    @Child(name = "additionalClassification", type = {CodeableConcept.class}, order=10, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Additional classification of the catalog entry", formalDefinition="User for example for ATC classification, or." )
    protected List<CodeableConcept> additionalClassification;

    /**
     * Used for example, to point to a substance, or to a device used to administer a medication.
     */
    @Child(name = "relatedEntry", type = {}, order=11, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="An item that this catalog entry is related to", formalDefinition="Used for example, to point to a substance, or to a device used to administer a medication." )
    protected List<EntryDefinitionRelatedEntryComponent> relatedEntry;

    private static final long serialVersionUID = -460221169L;

  /**
   * Constructor
   */
    public EntryDefinition() {
      super();
    }

  /**
   * Constructor
   */
    public EntryDefinition(BooleanType orderable, Reference referencedItem) {
      super();
      this.orderable = orderable;
      this.referencedItem = referencedItem;
    }

    /**
     * @return {@link #identifier} (Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public EntryDefinition setIdentifier(List<Identifier> theIdentifier) { 
      this.identifier = theIdentifier;
      return this;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    public EntryDefinition addIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #identifier}, creating it if it does not already exist
     */
    public Identifier getIdentifierFirstRep() { 
      if (getIdentifier().isEmpty()) {
        addIdentifier();
      }
      return getIdentifier().get(0);
    }

    /**
     * @return {@link #type} (The type of item - medication, device, service, protocol or other.)
     */
    public CodeableConcept getType() { 
      if (this.type == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create EntryDefinition.type");
        else if (Configuration.doAutoCreate())
          this.type = new CodeableConcept(); // cc
      return this.type;
    }

    public boolean hasType() { 
      return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (The type of item - medication, device, service, protocol or other.)
     */
    public EntryDefinition setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #orderable} (Whether the entry represents an orderable item.). This is the underlying object with id, value and extensions. The accessor "getOrderable" gives direct access to the value
     */
    public BooleanType getOrderableElement() { 
      if (this.orderable == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create EntryDefinition.orderable");
        else if (Configuration.doAutoCreate())
          this.orderable = new BooleanType(); // bb
      return this.orderable;
    }

    public boolean hasOrderableElement() { 
      return this.orderable != null && !this.orderable.isEmpty();
    }

    public boolean hasOrderable() { 
      return this.orderable != null && !this.orderable.isEmpty();
    }

    /**
     * @param value {@link #orderable} (Whether the entry represents an orderable item.). This is the underlying object with id, value and extensions. The accessor "getOrderable" gives direct access to the value
     */
    public EntryDefinition setOrderableElement(BooleanType value) { 
      this.orderable = value;
      return this;
    }

    /**
     * @return Whether the entry represents an orderable item.
     */
    public boolean getOrderable() { 
      return this.orderable == null || this.orderable.isEmpty() ? false : this.orderable.getValue();
    }

    /**
     * @param value Whether the entry represents an orderable item.
     */
    public EntryDefinition setOrderable(boolean value) { 
        if (this.orderable == null)
          this.orderable = new BooleanType();
        this.orderable.setValue(value);
      return this;
    }

    /**
     * @return {@link #referencedItem} (The item in a catalog or definition.)
     */
    public Reference getReferencedItem() { 
      if (this.referencedItem == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create EntryDefinition.referencedItem");
        else if (Configuration.doAutoCreate())
          this.referencedItem = new Reference(); // cc
      return this.referencedItem;
    }

    public boolean hasReferencedItem() { 
      return this.referencedItem != null && !this.referencedItem.isEmpty();
    }

    /**
     * @param value {@link #referencedItem} (The item in a catalog or definition.)
     */
    public EntryDefinition setReferencedItem(Reference value) { 
      this.referencedItem = value;
      return this;
    }

    /**
     * @return {@link #referencedItem} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The item in a catalog or definition.)
     */
    public Resource getReferencedItemTarget() { 
      return this.referencedItemTarget;
    }

    /**
     * @param value {@link #referencedItem} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The item in a catalog or definition.)
     */
    public EntryDefinition setReferencedItemTarget(Resource value) { 
      this.referencedItemTarget = value;
      return this;
    }

    /**
     * @return {@link #additionalIdentifier} (Used in supporting related concepts, e.g. NDC to RxNorm.)
     */
    public List<Identifier> getAdditionalIdentifier() { 
      if (this.additionalIdentifier == null)
        this.additionalIdentifier = new ArrayList<Identifier>();
      return this.additionalIdentifier;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public EntryDefinition setAdditionalIdentifier(List<Identifier> theAdditionalIdentifier) { 
      this.additionalIdentifier = theAdditionalIdentifier;
      return this;
    }

    public boolean hasAdditionalIdentifier() { 
      if (this.additionalIdentifier == null)
        return false;
      for (Identifier item : this.additionalIdentifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Identifier addAdditionalIdentifier() { //3
      Identifier t = new Identifier();
      if (this.additionalIdentifier == null)
        this.additionalIdentifier = new ArrayList<Identifier>();
      this.additionalIdentifier.add(t);
      return t;
    }

    public EntryDefinition addAdditionalIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.additionalIdentifier == null)
        this.additionalIdentifier = new ArrayList<Identifier>();
      this.additionalIdentifier.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #additionalIdentifier}, creating it if it does not already exist
     */
    public Identifier getAdditionalIdentifierFirstRep() { 
      if (getAdditionalIdentifier().isEmpty()) {
        addAdditionalIdentifier();
      }
      return getAdditionalIdentifier().get(0);
    }

    /**
     * @return {@link #classification} (Classes of devices, or ATC for medication.)
     */
    public List<CodeableConcept> getClassification() { 
      if (this.classification == null)
        this.classification = new ArrayList<CodeableConcept>();
      return this.classification;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public EntryDefinition setClassification(List<CodeableConcept> theClassification) { 
      this.classification = theClassification;
      return this;
    }

    public boolean hasClassification() { 
      if (this.classification == null)
        return false;
      for (CodeableConcept item : this.classification)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addClassification() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.classification == null)
        this.classification = new ArrayList<CodeableConcept>();
      this.classification.add(t);
      return t;
    }

    public EntryDefinition addClassification(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.classification == null)
        this.classification = new ArrayList<CodeableConcept>();
      this.classification.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #classification}, creating it if it does not already exist
     */
    public CodeableConcept getClassificationFirstRep() { 
      if (getClassification().isEmpty()) {
        addClassification();
      }
      return getClassification().get(0);
    }

    /**
     * @return {@link #status} (Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<PublicationStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create EntryDefinition.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<PublicationStatus>(new PublicationStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public EntryDefinition setStatusElement(Enumeration<PublicationStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.
     */
    public PublicationStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.
     */
    public EntryDefinition setStatus(PublicationStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<PublicationStatus>(new PublicationStatusEnumFactory());
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #validityPeriod} (The time period in which this catalog entry is expected to be active.)
     */
    public Period getValidityPeriod() { 
      if (this.validityPeriod == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create EntryDefinition.validityPeriod");
        else if (Configuration.doAutoCreate())
          this.validityPeriod = new Period(); // cc
      return this.validityPeriod;
    }

    public boolean hasValidityPeriod() { 
      return this.validityPeriod != null && !this.validityPeriod.isEmpty();
    }

    /**
     * @param value {@link #validityPeriod} (The time period in which this catalog entry is expected to be active.)
     */
    public EntryDefinition setValidityPeriod(Period value) { 
      this.validityPeriod = value;
      return this;
    }

    /**
     * @return {@link #lastUpdated} (Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated.). This is the underlying object with id, value and extensions. The accessor "getLastUpdated" gives direct access to the value
     */
    public DateTimeType getLastUpdatedElement() { 
      if (this.lastUpdated == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create EntryDefinition.lastUpdated");
        else if (Configuration.doAutoCreate())
          this.lastUpdated = new DateTimeType(); // bb
      return this.lastUpdated;
    }

    public boolean hasLastUpdatedElement() { 
      return this.lastUpdated != null && !this.lastUpdated.isEmpty();
    }

    public boolean hasLastUpdated() { 
      return this.lastUpdated != null && !this.lastUpdated.isEmpty();
    }

    /**
     * @param value {@link #lastUpdated} (Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated.). This is the underlying object with id, value and extensions. The accessor "getLastUpdated" gives direct access to the value
     */
    public EntryDefinition setLastUpdatedElement(DateTimeType value) { 
      this.lastUpdated = value;
      return this;
    }

    /**
     * @return Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated.
     */
    public Date getLastUpdated() { 
      return this.lastUpdated == null ? null : this.lastUpdated.getValue();
    }

    /**
     * @param value Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated.
     */
    public EntryDefinition setLastUpdated(Date value) { 
      if (value == null)
        this.lastUpdated = null;
      else {
        if (this.lastUpdated == null)
          this.lastUpdated = new DateTimeType();
        this.lastUpdated.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #additionalCharacteristic} (Used for examplefor Out of Formulary, or any specifics.)
     */
    public List<CodeableConcept> getAdditionalCharacteristic() { 
      if (this.additionalCharacteristic == null)
        this.additionalCharacteristic = new ArrayList<CodeableConcept>();
      return this.additionalCharacteristic;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public EntryDefinition setAdditionalCharacteristic(List<CodeableConcept> theAdditionalCharacteristic) { 
      this.additionalCharacteristic = theAdditionalCharacteristic;
      return this;
    }

    public boolean hasAdditionalCharacteristic() { 
      if (this.additionalCharacteristic == null)
        return false;
      for (CodeableConcept item : this.additionalCharacteristic)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addAdditionalCharacteristic() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.additionalCharacteristic == null)
        this.additionalCharacteristic = new ArrayList<CodeableConcept>();
      this.additionalCharacteristic.add(t);
      return t;
    }

    public EntryDefinition addAdditionalCharacteristic(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.additionalCharacteristic == null)
        this.additionalCharacteristic = new ArrayList<CodeableConcept>();
      this.additionalCharacteristic.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #additionalCharacteristic}, creating it if it does not already exist
     */
    public CodeableConcept getAdditionalCharacteristicFirstRep() { 
      if (getAdditionalCharacteristic().isEmpty()) {
        addAdditionalCharacteristic();
      }
      return getAdditionalCharacteristic().get(0);
    }

    /**
     * @return {@link #additionalClassification} (User for example for ATC classification, or.)
     */
    public List<CodeableConcept> getAdditionalClassification() { 
      if (this.additionalClassification == null)
        this.additionalClassification = new ArrayList<CodeableConcept>();
      return this.additionalClassification;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public EntryDefinition setAdditionalClassification(List<CodeableConcept> theAdditionalClassification) { 
      this.additionalClassification = theAdditionalClassification;
      return this;
    }

    public boolean hasAdditionalClassification() { 
      if (this.additionalClassification == null)
        return false;
      for (CodeableConcept item : this.additionalClassification)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addAdditionalClassification() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.additionalClassification == null)
        this.additionalClassification = new ArrayList<CodeableConcept>();
      this.additionalClassification.add(t);
      return t;
    }

    public EntryDefinition addAdditionalClassification(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.additionalClassification == null)
        this.additionalClassification = new ArrayList<CodeableConcept>();
      this.additionalClassification.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #additionalClassification}, creating it if it does not already exist
     */
    public CodeableConcept getAdditionalClassificationFirstRep() { 
      if (getAdditionalClassification().isEmpty()) {
        addAdditionalClassification();
      }
      return getAdditionalClassification().get(0);
    }

    /**
     * @return {@link #relatedEntry} (Used for example, to point to a substance, or to a device used to administer a medication.)
     */
    public List<EntryDefinitionRelatedEntryComponent> getRelatedEntry() { 
      if (this.relatedEntry == null)
        this.relatedEntry = new ArrayList<EntryDefinitionRelatedEntryComponent>();
      return this.relatedEntry;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public EntryDefinition setRelatedEntry(List<EntryDefinitionRelatedEntryComponent> theRelatedEntry) { 
      this.relatedEntry = theRelatedEntry;
      return this;
    }

    public boolean hasRelatedEntry() { 
      if (this.relatedEntry == null)
        return false;
      for (EntryDefinitionRelatedEntryComponent item : this.relatedEntry)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public EntryDefinitionRelatedEntryComponent addRelatedEntry() { //3
      EntryDefinitionRelatedEntryComponent t = new EntryDefinitionRelatedEntryComponent();
      if (this.relatedEntry == null)
        this.relatedEntry = new ArrayList<EntryDefinitionRelatedEntryComponent>();
      this.relatedEntry.add(t);
      return t;
    }

    public EntryDefinition addRelatedEntry(EntryDefinitionRelatedEntryComponent t) { //3
      if (t == null)
        return this;
      if (this.relatedEntry == null)
        this.relatedEntry = new ArrayList<EntryDefinitionRelatedEntryComponent>();
      this.relatedEntry.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #relatedEntry}, creating it if it does not already exist
     */
    public EntryDefinitionRelatedEntryComponent getRelatedEntryFirstRep() { 
      if (getRelatedEntry().isEmpty()) {
        addRelatedEntry();
      }
      return getRelatedEntry().get(0);
    }

      protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("identifier", "Identifier", "Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.", 0, java.lang.Integer.MAX_VALUE, identifier));
        children.add(new Property("type", "CodeableConcept", "The type of item - medication, device, service, protocol or other.", 0, 1, type));
        children.add(new Property("orderable", "boolean", "Whether the entry represents an orderable item.", 0, 1, orderable));
        children.add(new Property("referencedItem", "Reference(Medication|Device|Organization|Practitioner|HealthcareService|ActivityDefinition|PlanDefinition|SpecimenDefinition|ObservationDefinition|Binary)", "The item in a catalog or definition.", 0, 1, referencedItem));
        children.add(new Property("additionalIdentifier", "Identifier", "Used in supporting related concepts, e.g. NDC to RxNorm.", 0, java.lang.Integer.MAX_VALUE, additionalIdentifier));
        children.add(new Property("classification", "CodeableConcept", "Classes of devices, or ATC for medication.", 0, java.lang.Integer.MAX_VALUE, classification));
        children.add(new Property("status", "code", "Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.", 0, 1, status));
        children.add(new Property("validityPeriod", "Period", "The time period in which this catalog entry is expected to be active.", 0, 1, validityPeriod));
        children.add(new Property("lastUpdated", "dateTime", "Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated.", 0, 1, lastUpdated));
        children.add(new Property("additionalCharacteristic", "CodeableConcept", "Used for examplefor Out of Formulary, or any specifics.", 0, java.lang.Integer.MAX_VALUE, additionalCharacteristic));
        children.add(new Property("additionalClassification", "CodeableConcept", "User for example for ATC classification, or.", 0, java.lang.Integer.MAX_VALUE, additionalClassification));
        children.add(new Property("relatedEntry", "", "Used for example, to point to a substance, or to a device used to administer a medication.", 0, java.lang.Integer.MAX_VALUE, relatedEntry));
      }

      @Override
      public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
        switch (_hash) {
        case -1618432855: /*identifier*/  return new Property("identifier", "Identifier", "Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.", 0, java.lang.Integer.MAX_VALUE, identifier);
        case 3575610: /*type*/  return new Property("type", "CodeableConcept", "The type of item - medication, device, service, protocol or other.", 0, 1, type);
        case -391199320: /*orderable*/  return new Property("orderable", "boolean", "Whether the entry represents an orderable item.", 0, 1, orderable);
        case -1896630996: /*referencedItem*/  return new Property("referencedItem", "Reference(Medication|Device|Organization|Practitioner|HealthcareService|ActivityDefinition|PlanDefinition|SpecimenDefinition|ObservationDefinition|Binary)", "The item in a catalog or definition.", 0, 1, referencedItem);
        case 1195162672: /*additionalIdentifier*/  return new Property("additionalIdentifier", "Identifier", "Used in supporting related concepts, e.g. NDC to RxNorm.", 0, java.lang.Integer.MAX_VALUE, additionalIdentifier);
        case 382350310: /*classification*/  return new Property("classification", "CodeableConcept", "Classes of devices, or ATC for medication.", 0, java.lang.Integer.MAX_VALUE, classification);
        case -892481550: /*status*/  return new Property("status", "code", "Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.", 0, 1, status);
        case -1434195053: /*validityPeriod*/  return new Property("validityPeriod", "Period", "The time period in which this catalog entry is expected to be active.", 0, 1, validityPeriod);
        case 1649733957: /*lastUpdated*/  return new Property("lastUpdated", "dateTime", "Typically date of issue is different from the beginning of the validity. This can be used to see when an item was last updated.", 0, 1, lastUpdated);
        case -1638369886: /*additionalCharacteristic*/  return new Property("additionalCharacteristic", "CodeableConcept", "Used for examplefor Out of Formulary, or any specifics.", 0, java.lang.Integer.MAX_VALUE, additionalCharacteristic);
        case -1622333459: /*additionalClassification*/  return new Property("additionalClassification", "CodeableConcept", "User for example for ATC classification, or.", 0, java.lang.Integer.MAX_VALUE, additionalClassification);
        case 130178823: /*relatedEntry*/  return new Property("relatedEntry", "", "Used for example, to point to a substance, or to a device used to administer a medication.", 0, java.lang.Integer.MAX_VALUE, relatedEntry);
        default: return super.getNamedProperty(_hash, _name, _checkValid);
        }

      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : this.identifier.toArray(new Base[this.identifier.size()]); // Identifier
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -391199320: /*orderable*/ return this.orderable == null ? new Base[0] : new Base[] {this.orderable}; // BooleanType
        case -1896630996: /*referencedItem*/ return this.referencedItem == null ? new Base[0] : new Base[] {this.referencedItem}; // Reference
        case 1195162672: /*additionalIdentifier*/ return this.additionalIdentifier == null ? new Base[0] : this.additionalIdentifier.toArray(new Base[this.additionalIdentifier.size()]); // Identifier
        case 382350310: /*classification*/ return this.classification == null ? new Base[0] : this.classification.toArray(new Base[this.classification.size()]); // CodeableConcept
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<PublicationStatus>
        case -1434195053: /*validityPeriod*/ return this.validityPeriod == null ? new Base[0] : new Base[] {this.validityPeriod}; // Period
        case 1649733957: /*lastUpdated*/ return this.lastUpdated == null ? new Base[0] : new Base[] {this.lastUpdated}; // DateTimeType
        case -1638369886: /*additionalCharacteristic*/ return this.additionalCharacteristic == null ? new Base[0] : this.additionalCharacteristic.toArray(new Base[this.additionalCharacteristic.size()]); // CodeableConcept
        case -1622333459: /*additionalClassification*/ return this.additionalClassification == null ? new Base[0] : this.additionalClassification.toArray(new Base[this.additionalClassification.size()]); // CodeableConcept
        case 130178823: /*relatedEntry*/ return this.relatedEntry == null ? new Base[0] : this.relatedEntry.toArray(new Base[this.relatedEntry.size()]); // EntryDefinitionRelatedEntryComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.getIdentifier().add(castToIdentifier(value)); // Identifier
          return value;
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -391199320: // orderable
          this.orderable = castToBoolean(value); // BooleanType
          return value;
        case -1896630996: // referencedItem
          this.referencedItem = castToReference(value); // Reference
          return value;
        case 1195162672: // additionalIdentifier
          this.getAdditionalIdentifier().add(castToIdentifier(value)); // Identifier
          return value;
        case 382350310: // classification
          this.getClassification().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -892481550: // status
          value = new PublicationStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<PublicationStatus>
          return value;
        case -1434195053: // validityPeriod
          this.validityPeriod = castToPeriod(value); // Period
          return value;
        case 1649733957: // lastUpdated
          this.lastUpdated = castToDateTime(value); // DateTimeType
          return value;
        case -1638369886: // additionalCharacteristic
          this.getAdditionalCharacteristic().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -1622333459: // additionalClassification
          this.getAdditionalClassification().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case 130178823: // relatedEntry
          this.getRelatedEntry().add((EntryDefinitionRelatedEntryComponent) value); // EntryDefinitionRelatedEntryComponent
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.getIdentifier().add(castToIdentifier(value));
        } else if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("orderable")) {
          this.orderable = castToBoolean(value); // BooleanType
        } else if (name.equals("referencedItem")) {
          this.referencedItem = castToReference(value); // Reference
        } else if (name.equals("additionalIdentifier")) {
          this.getAdditionalIdentifier().add(castToIdentifier(value));
        } else if (name.equals("classification")) {
          this.getClassification().add(castToCodeableConcept(value));
        } else if (name.equals("status")) {
          value = new PublicationStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<PublicationStatus>
        } else if (name.equals("validityPeriod")) {
          this.validityPeriod = castToPeriod(value); // Period
        } else if (name.equals("lastUpdated")) {
          this.lastUpdated = castToDateTime(value); // DateTimeType
        } else if (name.equals("additionalCharacteristic")) {
          this.getAdditionalCharacteristic().add(castToCodeableConcept(value));
        } else if (name.equals("additionalClassification")) {
          this.getAdditionalClassification().add(castToCodeableConcept(value));
        } else if (name.equals("relatedEntry")) {
          this.getRelatedEntry().add((EntryDefinitionRelatedEntryComponent) value);
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return addIdentifier(); 
        case 3575610:  return getType(); 
        case -391199320:  return getOrderableElement();
        case -1896630996:  return getReferencedItem(); 
        case 1195162672:  return addAdditionalIdentifier(); 
        case 382350310:  return addClassification(); 
        case -892481550:  return getStatusElement();
        case -1434195053:  return getValidityPeriod(); 
        case 1649733957:  return getLastUpdatedElement();
        case -1638369886:  return addAdditionalCharacteristic(); 
        case -1622333459:  return addAdditionalClassification(); 
        case 130178823:  return addRelatedEntry(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -391199320: /*orderable*/ return new String[] {"boolean"};
        case -1896630996: /*referencedItem*/ return new String[] {"Reference"};
        case 1195162672: /*additionalIdentifier*/ return new String[] {"Identifier"};
        case 382350310: /*classification*/ return new String[] {"CodeableConcept"};
        case -892481550: /*status*/ return new String[] {"code"};
        case -1434195053: /*validityPeriod*/ return new String[] {"Period"};
        case 1649733957: /*lastUpdated*/ return new String[] {"dateTime"};
        case -1638369886: /*additionalCharacteristic*/ return new String[] {"CodeableConcept"};
        case -1622333459: /*additionalClassification*/ return new String[] {"CodeableConcept"};
        case 130178823: /*relatedEntry*/ return new String[] {};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          return addIdentifier();
        }
        else if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("orderable")) {
          throw new FHIRException("Cannot call addChild on a primitive type EntryDefinition.orderable");
        }
        else if (name.equals("referencedItem")) {
          this.referencedItem = new Reference();
          return this.referencedItem;
        }
        else if (name.equals("additionalIdentifier")) {
          return addAdditionalIdentifier();
        }
        else if (name.equals("classification")) {
          return addClassification();
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type EntryDefinition.status");
        }
        else if (name.equals("validityPeriod")) {
          this.validityPeriod = new Period();
          return this.validityPeriod;
        }
        else if (name.equals("lastUpdated")) {
          throw new FHIRException("Cannot call addChild on a primitive type EntryDefinition.lastUpdated");
        }
        else if (name.equals("additionalCharacteristic")) {
          return addAdditionalCharacteristic();
        }
        else if (name.equals("additionalClassification")) {
          return addAdditionalClassification();
        }
        else if (name.equals("relatedEntry")) {
          return addRelatedEntry();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "EntryDefinition";

  }

      public EntryDefinition copy() {
        EntryDefinition dst = new EntryDefinition();
        copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        dst.type = type == null ? null : type.copy();
        dst.orderable = orderable == null ? null : orderable.copy();
        dst.referencedItem = referencedItem == null ? null : referencedItem.copy();
        if (additionalIdentifier != null) {
          dst.additionalIdentifier = new ArrayList<Identifier>();
          for (Identifier i : additionalIdentifier)
            dst.additionalIdentifier.add(i.copy());
        };
        if (classification != null) {
          dst.classification = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : classification)
            dst.classification.add(i.copy());
        };
        dst.status = status == null ? null : status.copy();
        dst.validityPeriod = validityPeriod == null ? null : validityPeriod.copy();
        dst.lastUpdated = lastUpdated == null ? null : lastUpdated.copy();
        if (additionalCharacteristic != null) {
          dst.additionalCharacteristic = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : additionalCharacteristic)
            dst.additionalCharacteristic.add(i.copy());
        };
        if (additionalClassification != null) {
          dst.additionalClassification = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : additionalClassification)
            dst.additionalClassification.add(i.copy());
        };
        if (relatedEntry != null) {
          dst.relatedEntry = new ArrayList<EntryDefinitionRelatedEntryComponent>();
          for (EntryDefinitionRelatedEntryComponent i : relatedEntry)
            dst.relatedEntry.add(i.copy());
        };
        return dst;
      }

      protected EntryDefinition typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof EntryDefinition))
          return false;
        EntryDefinition o = (EntryDefinition) other_;
        return compareDeep(identifier, o.identifier, true) && compareDeep(type, o.type, true) && compareDeep(orderable, o.orderable, true)
           && compareDeep(referencedItem, o.referencedItem, true) && compareDeep(additionalIdentifier, o.additionalIdentifier, true)
           && compareDeep(classification, o.classification, true) && compareDeep(status, o.status, true) && compareDeep(validityPeriod, o.validityPeriod, true)
           && compareDeep(lastUpdated, o.lastUpdated, true) && compareDeep(additionalCharacteristic, o.additionalCharacteristic, true)
           && compareDeep(additionalClassification, o.additionalClassification, true) && compareDeep(relatedEntry, o.relatedEntry, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof EntryDefinition))
          return false;
        EntryDefinition o = (EntryDefinition) other_;
        return compareValues(orderable, o.orderable, true) && compareValues(status, o.status, true) && compareValues(lastUpdated, o.lastUpdated, true)
          ;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, type, orderable
          , referencedItem, additionalIdentifier, classification, status, validityPeriod, lastUpdated
          , additionalCharacteristic, additionalClassification, relatedEntry);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.EntryDefinition;
   }


}

