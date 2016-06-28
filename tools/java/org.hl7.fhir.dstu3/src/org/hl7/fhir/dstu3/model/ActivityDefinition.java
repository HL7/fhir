package org.hl7.fhir.dstu3.model;

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

// Generated on Tue, Jun 28, 2016 21:54+1000 for FHIR v1.4.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
/**
 * This resource allows for the definition of an order set as a sharable, consumable, and executable artifact in support of clinical decision support.
 */
@ResourceDef(name="ActivityDefinition", profile="http://hl7.org/fhir/Profile/ActivityDefinition")
public class ActivityDefinition extends DomainResource {

    public enum ActivityDefinitionCategory {
        /**
         * To communicate with a participant in some way
         */
        COMMUNICATION, 
        /**
         * To consume food of a specified nature
         */
        DIET, 
        /**
         * To consume/receive a drug, vaccine or other product
         */
        DRUG, 
        /**
         * To meet with the patient (in-patient, out-patient, etc.)
         */
        ENCOUNTER, 
        /**
         * To capture information about a patient (vitals, labs, diagnostic images, etc.)
         */
        OBSERVATION, 
        /**
         * To modify the patient in some way (surgery, physiotherapy, education, counseling, etc.)
         */
        PROCEDURE, 
        /**
         * To refer the patient to receive some service
         */
        REFERRAL, 
        /**
         * To provide something to the patient (medication, medical supply, etc.)
         */
        SUPPLY, 
        /**
         * Some other form of action
         */
        OTHER, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static ActivityDefinitionCategory fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("communication".equals(codeString))
          return COMMUNICATION;
        if ("diet".equals(codeString))
          return DIET;
        if ("drug".equals(codeString))
          return DRUG;
        if ("encounter".equals(codeString))
          return ENCOUNTER;
        if ("observation".equals(codeString))
          return OBSERVATION;
        if ("procedure".equals(codeString))
          return PROCEDURE;
        if ("referral".equals(codeString))
          return REFERRAL;
        if ("supply".equals(codeString))
          return SUPPLY;
        if ("other".equals(codeString))
          return OTHER;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown ActivityDefinitionCategory code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case COMMUNICATION: return "communication";
            case DIET: return "diet";
            case DRUG: return "drug";
            case ENCOUNTER: return "encounter";
            case OBSERVATION: return "observation";
            case PROCEDURE: return "procedure";
            case REFERRAL: return "referral";
            case SUPPLY: return "supply";
            case OTHER: return "other";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case COMMUNICATION: return "http://hl7.org/fhir/activity-definition-category";
            case DIET: return "http://hl7.org/fhir/activity-definition-category";
            case DRUG: return "http://hl7.org/fhir/activity-definition-category";
            case ENCOUNTER: return "http://hl7.org/fhir/activity-definition-category";
            case OBSERVATION: return "http://hl7.org/fhir/activity-definition-category";
            case PROCEDURE: return "http://hl7.org/fhir/activity-definition-category";
            case REFERRAL: return "http://hl7.org/fhir/activity-definition-category";
            case SUPPLY: return "http://hl7.org/fhir/activity-definition-category";
            case OTHER: return "http://hl7.org/fhir/activity-definition-category";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case COMMUNICATION: return "To communicate with a participant in some way";
            case DIET: return "To consume food of a specified nature";
            case DRUG: return "To consume/receive a drug, vaccine or other product";
            case ENCOUNTER: return "To meet with the patient (in-patient, out-patient, etc.)";
            case OBSERVATION: return "To capture information about a patient (vitals, labs, diagnostic images, etc.)";
            case PROCEDURE: return "To modify the patient in some way (surgery, physiotherapy, education, counseling, etc.)";
            case REFERRAL: return "To refer the patient to receive some service";
            case SUPPLY: return "To provide something to the patient (medication, medical supply, etc.)";
            case OTHER: return "Some other form of action";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case COMMUNICATION: return "Communication";
            case DIET: return "Diet";
            case DRUG: return "Drug";
            case ENCOUNTER: return "Encounter";
            case OBSERVATION: return "Observation";
            case PROCEDURE: return "Procedure";
            case REFERRAL: return "Referral";
            case SUPPLY: return "Supply";
            case OTHER: return "Other";
            default: return "?";
          }
        }
    }

  public static class ActivityDefinitionCategoryEnumFactory implements EnumFactory<ActivityDefinitionCategory> {
    public ActivityDefinitionCategory fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("communication".equals(codeString))
          return ActivityDefinitionCategory.COMMUNICATION;
        if ("diet".equals(codeString))
          return ActivityDefinitionCategory.DIET;
        if ("drug".equals(codeString))
          return ActivityDefinitionCategory.DRUG;
        if ("encounter".equals(codeString))
          return ActivityDefinitionCategory.ENCOUNTER;
        if ("observation".equals(codeString))
          return ActivityDefinitionCategory.OBSERVATION;
        if ("procedure".equals(codeString))
          return ActivityDefinitionCategory.PROCEDURE;
        if ("referral".equals(codeString))
          return ActivityDefinitionCategory.REFERRAL;
        if ("supply".equals(codeString))
          return ActivityDefinitionCategory.SUPPLY;
        if ("other".equals(codeString))
          return ActivityDefinitionCategory.OTHER;
        throw new IllegalArgumentException("Unknown ActivityDefinitionCategory code '"+codeString+"'");
        }
        public Enumeration<ActivityDefinitionCategory> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("communication".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.COMMUNICATION);
        if ("diet".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.DIET);
        if ("drug".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.DRUG);
        if ("encounter".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.ENCOUNTER);
        if ("observation".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.OBSERVATION);
        if ("procedure".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.PROCEDURE);
        if ("referral".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.REFERRAL);
        if ("supply".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.SUPPLY);
        if ("other".equals(codeString))
          return new Enumeration<ActivityDefinitionCategory>(this, ActivityDefinitionCategory.OTHER);
        throw new FHIRException("Unknown ActivityDefinitionCategory code '"+codeString+"'");
        }
    public String toCode(ActivityDefinitionCategory code) {
      if (code == ActivityDefinitionCategory.COMMUNICATION)
        return "communication";
      if (code == ActivityDefinitionCategory.DIET)
        return "diet";
      if (code == ActivityDefinitionCategory.DRUG)
        return "drug";
      if (code == ActivityDefinitionCategory.ENCOUNTER)
        return "encounter";
      if (code == ActivityDefinitionCategory.OBSERVATION)
        return "observation";
      if (code == ActivityDefinitionCategory.PROCEDURE)
        return "procedure";
      if (code == ActivityDefinitionCategory.REFERRAL)
        return "referral";
      if (code == ActivityDefinitionCategory.SUPPLY)
        return "supply";
      if (code == ActivityDefinitionCategory.OTHER)
        return "other";
      return "?";
      }
    public String toSystem(ActivityDefinitionCategory code) {
      return code.getSystem();
      }
    }

    public enum ActivityParticipantType {
        /**
         * The participant is the patient under evaluation
         */
        PATIENT, 
        /**
         * The participant is a practitioner involved in the patient's care
         */
        PRACTITIONER, 
        /**
         * The participant is a person related to the patient
         */
        RELATEDPERSON, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static ActivityParticipantType fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("patient".equals(codeString))
          return PATIENT;
        if ("practitioner".equals(codeString))
          return PRACTITIONER;
        if ("related-person".equals(codeString))
          return RELATEDPERSON;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown ActivityParticipantType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PATIENT: return "patient";
            case PRACTITIONER: return "practitioner";
            case RELATEDPERSON: return "related-person";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case PATIENT: return "http://hl7.org/fhir/activity-participant-type";
            case PRACTITIONER: return "http://hl7.org/fhir/activity-participant-type";
            case RELATEDPERSON: return "http://hl7.org/fhir/activity-participant-type";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PATIENT: return "The participant is the patient under evaluation";
            case PRACTITIONER: return "The participant is a practitioner involved in the patient's care";
            case RELATEDPERSON: return "The participant is a person related to the patient";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PATIENT: return "Patient";
            case PRACTITIONER: return "Practitioner";
            case RELATEDPERSON: return "Related Person";
            default: return "?";
          }
        }
    }

  public static class ActivityParticipantTypeEnumFactory implements EnumFactory<ActivityParticipantType> {
    public ActivityParticipantType fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("patient".equals(codeString))
          return ActivityParticipantType.PATIENT;
        if ("practitioner".equals(codeString))
          return ActivityParticipantType.PRACTITIONER;
        if ("related-person".equals(codeString))
          return ActivityParticipantType.RELATEDPERSON;
        throw new IllegalArgumentException("Unknown ActivityParticipantType code '"+codeString+"'");
        }
        public Enumeration<ActivityParticipantType> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("patient".equals(codeString))
          return new Enumeration<ActivityParticipantType>(this, ActivityParticipantType.PATIENT);
        if ("practitioner".equals(codeString))
          return new Enumeration<ActivityParticipantType>(this, ActivityParticipantType.PRACTITIONER);
        if ("related-person".equals(codeString))
          return new Enumeration<ActivityParticipantType>(this, ActivityParticipantType.RELATEDPERSON);
        throw new FHIRException("Unknown ActivityParticipantType code '"+codeString+"'");
        }
    public String toCode(ActivityParticipantType code) {
      if (code == ActivityParticipantType.PATIENT)
        return "patient";
      if (code == ActivityParticipantType.PRACTITIONER)
        return "practitioner";
      if (code == ActivityParticipantType.RELATEDPERSON)
        return "related-person";
      return "?";
      }
    public String toSystem(ActivityParticipantType code) {
      return code.getSystem();
      }
    }

    /**
     * A reference to a ModuleMetadata resource containing metadata for the orderset.
     */
    @Child(name = "moduleMetadata", type = {ModuleMetadata.class}, order=0, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="The metadata for the plan definition", formalDefinition="A reference to a ModuleMetadata resource containing metadata for the orderset." )
    protected ModuleMetadata moduleMetadata;

    /**
     * A reference to a Library resource containing any formal logic used by the orderset.
     */
    @Child(name = "library", type = {Library.class}, order=1, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Logic used by the plan definition", formalDefinition="A reference to a Library resource containing any formal logic used by the orderset." )
    protected List<Reference> library;
    /**
     * The actual objects that are the target of the reference (A reference to a Library resource containing any formal logic used by the orderset.)
     */
    protected List<Library> libraryTarget;


    /**
     * High-level categorization of the type of activity.
     */
    @Child(name = "category", type = {CodeType.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="communication | diet | drug | encounter | observation | procedure | referral | supply | other", formalDefinition="High-level categorization of the type of activity." )
    protected Enumeration<ActivityDefinitionCategory> category;

    /**
     * Detailed description of the type of activity; e.g. What lab test, what procedure, what kind of encounter.
     */
    @Child(name = "code", type = {CodeableConcept.class}, order=3, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Detail type of activity", formalDefinition="Detailed description of the type of activity; e.g. What lab test, what procedure, what kind of encounter." )
    protected CodeableConcept code;

    /**
     * The period, timing or frequency upon which the described activity is to occur.
     */
    @Child(name = "timing", type = {CodeableConcept.class, Timing.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="When activity is to occur", formalDefinition="The period, timing or frequency upon which the described activity is to occur." )
    protected Type timing;

    /**
     * Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc.
     */
    @Child(name = "location", type = {Location.class}, order=5, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Where it should happen", formalDefinition="Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc." )
    protected Reference location;

    /**
     * The actual object that is the target of the reference (Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc.)
     */
    protected Location locationTarget;

    /**
     * The type of participant in the action.
     */
    @Child(name = "participantType", type = {CodeType.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="patient | practitioner | related-person", formalDefinition="The type of participant in the action." )
    protected List<Enumeration<ActivityParticipantType>> participantType;

    /**
     * Identifies the food, drug or other product being consumed or supplied in the activity.
     */
    @Child(name = "product", type = {Medication.class, Substance.class, CodeableConcept.class}, order=7, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="What's administered/supplied", formalDefinition="Identifies the food, drug or other product being consumed or supplied in the activity." )
    protected Type product;

    /**
     * Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).
     */
    @Child(name = "quantity", type = {SimpleQuantity.class}, order=8, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="How much is administered/consumed/supplied", formalDefinition="Identifies the quantity expected to be consumed at once (per dose, per meal, etc.)." )
    protected SimpleQuantity quantity;

    /**
     * This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
     */
    @Child(name = "description", type = {StringType.class}, order=9, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Extra info on activity occurrence", formalDefinition="This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc." )
    protected StringType description;

    private static final long serialVersionUID = -1562010009L;

  /**
   * Constructor
   */
    public ActivityDefinition() {
      super();
    }

    /**
     * @return {@link #moduleMetadata} (A reference to a ModuleMetadata resource containing metadata for the orderset.)
     */
    public ModuleMetadata getModuleMetadata() { 
      if (this.moduleMetadata == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActivityDefinition.moduleMetadata");
        else if (Configuration.doAutoCreate())
          this.moduleMetadata = new ModuleMetadata(); // cc
      return this.moduleMetadata;
    }

    public boolean hasModuleMetadata() { 
      return this.moduleMetadata != null && !this.moduleMetadata.isEmpty();
    }

    /**
     * @param value {@link #moduleMetadata} (A reference to a ModuleMetadata resource containing metadata for the orderset.)
     */
    public ActivityDefinition setModuleMetadata(ModuleMetadata value) { 
      this.moduleMetadata = value;
      return this;
    }

    /**
     * @return {@link #library} (A reference to a Library resource containing any formal logic used by the orderset.)
     */
    public List<Reference> getLibrary() { 
      if (this.library == null)
        this.library = new ArrayList<Reference>();
      return this.library;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ActivityDefinition setLibrary(List<Reference> theLibrary) { 
      this.library = theLibrary;
      return this;
    }

    public boolean hasLibrary() { 
      if (this.library == null)
        return false;
      for (Reference item : this.library)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addLibrary() { //3
      Reference t = new Reference();
      if (this.library == null)
        this.library = new ArrayList<Reference>();
      this.library.add(t);
      return t;
    }

    public ActivityDefinition addLibrary(Reference t) { //3
      if (t == null)
        return this;
      if (this.library == null)
        this.library = new ArrayList<Reference>();
      this.library.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #library}, creating it if it does not already exist
     */
    public Reference getLibraryFirstRep() { 
      if (getLibrary().isEmpty()) {
        addLibrary();
      }
      return getLibrary().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Library> getLibraryTarget() { 
      if (this.libraryTarget == null)
        this.libraryTarget = new ArrayList<Library>();
      return this.libraryTarget;
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public Library addLibraryTarget() { 
      Library r = new Library();
      if (this.libraryTarget == null)
        this.libraryTarget = new ArrayList<Library>();
      this.libraryTarget.add(r);
      return r;
    }

    /**
     * @return {@link #category} (High-level categorization of the type of activity.). This is the underlying object with id, value and extensions. The accessor "getCategory" gives direct access to the value
     */
    public Enumeration<ActivityDefinitionCategory> getCategoryElement() { 
      if (this.category == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActivityDefinition.category");
        else if (Configuration.doAutoCreate())
          this.category = new Enumeration<ActivityDefinitionCategory>(new ActivityDefinitionCategoryEnumFactory()); // bb
      return this.category;
    }

    public boolean hasCategoryElement() { 
      return this.category != null && !this.category.isEmpty();
    }

    public boolean hasCategory() { 
      return this.category != null && !this.category.isEmpty();
    }

    /**
     * @param value {@link #category} (High-level categorization of the type of activity.). This is the underlying object with id, value and extensions. The accessor "getCategory" gives direct access to the value
     */
    public ActivityDefinition setCategoryElement(Enumeration<ActivityDefinitionCategory> value) { 
      this.category = value;
      return this;
    }

    /**
     * @return High-level categorization of the type of activity.
     */
    public ActivityDefinitionCategory getCategory() { 
      return this.category == null ? null : this.category.getValue();
    }

    /**
     * @param value High-level categorization of the type of activity.
     */
    public ActivityDefinition setCategory(ActivityDefinitionCategory value) { 
      if (value == null)
        this.category = null;
      else {
        if (this.category == null)
          this.category = new Enumeration<ActivityDefinitionCategory>(new ActivityDefinitionCategoryEnumFactory());
        this.category.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #code} (Detailed description of the type of activity; e.g. What lab test, what procedure, what kind of encounter.)
     */
    public CodeableConcept getCode() { 
      if (this.code == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActivityDefinition.code");
        else if (Configuration.doAutoCreate())
          this.code = new CodeableConcept(); // cc
      return this.code;
    }

    public boolean hasCode() { 
      return this.code != null && !this.code.isEmpty();
    }

    /**
     * @param value {@link #code} (Detailed description of the type of activity; e.g. What lab test, what procedure, what kind of encounter.)
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
     * @return {@link #timing} (The period, timing or frequency upon which the described activity is to occur.)
     */
    public CodeableConcept getTimingCodeableConcept() throws FHIRException { 
      if (!(this.timing instanceof CodeableConcept))
        throw new FHIRException("Type mismatch: the type CodeableConcept was expected, but "+this.timing.getClass().getName()+" was encountered");
      return (CodeableConcept) this.timing;
    }

    public boolean hasTimingCodeableConcept() { 
      return this.timing instanceof CodeableConcept;
    }

    /**
     * @return {@link #timing} (The period, timing or frequency upon which the described activity is to occur.)
     */
    public Timing getTimingTiming() throws FHIRException { 
      if (!(this.timing instanceof Timing))
        throw new FHIRException("Type mismatch: the type Timing was expected, but "+this.timing.getClass().getName()+" was encountered");
      return (Timing) this.timing;
    }

    public boolean hasTimingTiming() { 
      return this.timing instanceof Timing;
    }

    public boolean hasTiming() { 
      return this.timing != null && !this.timing.isEmpty();
    }

    /**
     * @param value {@link #timing} (The period, timing or frequency upon which the described activity is to occur.)
     */
    public ActivityDefinition setTiming(Type value) { 
      this.timing = value;
      return this;
    }

    /**
     * @return {@link #location} (Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc.)
     */
    public Reference getLocation() { 
      if (this.location == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActivityDefinition.location");
        else if (Configuration.doAutoCreate())
          this.location = new Reference(); // cc
      return this.location;
    }

    public boolean hasLocation() { 
      return this.location != null && !this.location.isEmpty();
    }

    /**
     * @param value {@link #location} (Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc.)
     */
    public ActivityDefinition setLocation(Reference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #location} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc.)
     */
    public Location getLocationTarget() { 
      if (this.locationTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActivityDefinition.location");
        else if (Configuration.doAutoCreate())
          this.locationTarget = new Location(); // aa
      return this.locationTarget;
    }

    /**
     * @param value {@link #location} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc.)
     */
    public ActivityDefinition setLocationTarget(Location value) { 
      this.locationTarget = value;
      return this;
    }

    /**
     * @return {@link #participantType} (The type of participant in the action.)
     */
    public List<Enumeration<ActivityParticipantType>> getParticipantType() { 
      if (this.participantType == null)
        this.participantType = new ArrayList<Enumeration<ActivityParticipantType>>();
      return this.participantType;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ActivityDefinition setParticipantType(List<Enumeration<ActivityParticipantType>> theParticipantType) { 
      this.participantType = theParticipantType;
      return this;
    }

    public boolean hasParticipantType() { 
      if (this.participantType == null)
        return false;
      for (Enumeration<ActivityParticipantType> item : this.participantType)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #participantType} (The type of participant in the action.)
     */
    public Enumeration<ActivityParticipantType> addParticipantTypeElement() {//2 
      Enumeration<ActivityParticipantType> t = new Enumeration<ActivityParticipantType>(new ActivityParticipantTypeEnumFactory());
      if (this.participantType == null)
        this.participantType = new ArrayList<Enumeration<ActivityParticipantType>>();
      this.participantType.add(t);
      return t;
    }

    /**
     * @param value {@link #participantType} (The type of participant in the action.)
     */
    public ActivityDefinition addParticipantType(ActivityParticipantType value) { //1
      Enumeration<ActivityParticipantType> t = new Enumeration<ActivityParticipantType>(new ActivityParticipantTypeEnumFactory());
      t.setValue(value);
      if (this.participantType == null)
        this.participantType = new ArrayList<Enumeration<ActivityParticipantType>>();
      this.participantType.add(t);
      return this;
    }

    /**
     * @param value {@link #participantType} (The type of participant in the action.)
     */
    public boolean hasParticipantType(ActivityParticipantType value) { 
      if (this.participantType == null)
        return false;
      for (Enumeration<ActivityParticipantType> v : this.participantType)
        if (v.getValue().equals(value)) // code
          return true;
      return false;
    }

    /**
     * @return {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
     */
    public Type getProduct() { 
      return this.product;
    }

    /**
     * @return {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
     */
    public Reference getProductReference() throws FHIRException { 
      if (!(this.product instanceof Reference))
        throw new FHIRException("Type mismatch: the type Reference was expected, but "+this.product.getClass().getName()+" was encountered");
      return (Reference) this.product;
    }

    public boolean hasProductReference() { 
      return this.product instanceof Reference;
    }

    /**
     * @return {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
     */
    public CodeableConcept getProductCodeableConcept() throws FHIRException { 
      if (!(this.product instanceof CodeableConcept))
        throw new FHIRException("Type mismatch: the type CodeableConcept was expected, but "+this.product.getClass().getName()+" was encountered");
      return (CodeableConcept) this.product;
    }

    public boolean hasProductCodeableConcept() { 
      return this.product instanceof CodeableConcept;
    }

    public boolean hasProduct() { 
      return this.product != null && !this.product.isEmpty();
    }

    /**
     * @param value {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
     */
    public ActivityDefinition setProduct(Type value) { 
      this.product = value;
      return this;
    }

    /**
     * @return {@link #quantity} (Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).)
     */
    public SimpleQuantity getQuantity() { 
      if (this.quantity == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActivityDefinition.quantity");
        else if (Configuration.doAutoCreate())
          this.quantity = new SimpleQuantity(); // cc
      return this.quantity;
    }

    public boolean hasQuantity() { 
      return this.quantity != null && !this.quantity.isEmpty();
    }

    /**
     * @param value {@link #quantity} (Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).)
     */
    public ActivityDefinition setQuantity(SimpleQuantity value) { 
      this.quantity = value;
      return this;
    }

    /**
     * @return {@link #description} (This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      if (this.description == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActivityDefinition.description");
        else if (Configuration.doAutoCreate())
          this.description = new StringType(); // bb
      return this.description;
    }

    public boolean hasDescriptionElement() { 
      return this.description != null && !this.description.isEmpty();
    }

    public boolean hasDescription() { 
      return this.description != null && !this.description.isEmpty();
    }

    /**
     * @param value {@link #description} (This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public ActivityDefinition setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
     */
    public ActivityDefinition setDescription(String value) { 
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
        childrenList.add(new Property("moduleMetadata", "ModuleMetadata", "A reference to a ModuleMetadata resource containing metadata for the orderset.", 0, java.lang.Integer.MAX_VALUE, moduleMetadata));
        childrenList.add(new Property("library", "Reference(Library)", "A reference to a Library resource containing any formal logic used by the orderset.", 0, java.lang.Integer.MAX_VALUE, library));
        childrenList.add(new Property("category", "code", "High-level categorization of the type of activity.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("code", "CodeableConcept", "Detailed description of the type of activity; e.g. What lab test, what procedure, what kind of encounter.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("timing[x]", "CodeableConcept|Timing", "The period, timing or frequency upon which the described activity is to occur.", 0, java.lang.Integer.MAX_VALUE, timing));
        childrenList.add(new Property("location", "Reference(Location)", "Identifies the facility where the activity will occur; e.g. home, hospital, specific clinic, etc.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("participantType", "code", "The type of participant in the action.", 0, java.lang.Integer.MAX_VALUE, participantType));
        childrenList.add(new Property("product[x]", "Reference(Medication|Substance)|CodeableConcept", "Identifies the food, drug or other product being consumed or supplied in the activity.", 0, java.lang.Integer.MAX_VALUE, product));
        childrenList.add(new Property("quantity", "SimpleQuantity", "Identifies the quantity expected to be consumed at once (per dose, per meal, etc.).", 0, java.lang.Integer.MAX_VALUE, quantity));
        childrenList.add(new Property("description", "string", "This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.", 0, java.lang.Integer.MAX_VALUE, description));
      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 455891387: /*moduleMetadata*/ return this.moduleMetadata == null ? new Base[0] : new Base[] {this.moduleMetadata}; // ModuleMetadata
        case 166208699: /*library*/ return this.library == null ? new Base[0] : this.library.toArray(new Base[this.library.size()]); // Reference
        case 50511102: /*category*/ return this.category == null ? new Base[0] : new Base[] {this.category}; // Enumeration<ActivityDefinitionCategory>
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // CodeableConcept
        case -873664438: /*timing*/ return this.timing == null ? new Base[0] : new Base[] {this.timing}; // Type
        case 1901043637: /*location*/ return this.location == null ? new Base[0] : new Base[] {this.location}; // Reference
        case 841294093: /*participantType*/ return this.participantType == null ? new Base[0] : this.participantType.toArray(new Base[this.participantType.size()]); // Enumeration<ActivityParticipantType>
        case -309474065: /*product*/ return this.product == null ? new Base[0] : new Base[] {this.product}; // Type
        case -1285004149: /*quantity*/ return this.quantity == null ? new Base[0] : new Base[] {this.quantity}; // SimpleQuantity
        case -1724546052: /*description*/ return this.description == null ? new Base[0] : new Base[] {this.description}; // StringType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 455891387: // moduleMetadata
          this.moduleMetadata = castToModuleMetadata(value); // ModuleMetadata
          break;
        case 166208699: // library
          this.getLibrary().add(castToReference(value)); // Reference
          break;
        case 50511102: // category
          this.category = new ActivityDefinitionCategoryEnumFactory().fromType(value); // Enumeration<ActivityDefinitionCategory>
          break;
        case 3059181: // code
          this.code = castToCodeableConcept(value); // CodeableConcept
          break;
        case -873664438: // timing
          this.timing = (Type) value; // Type
          break;
        case 1901043637: // location
          this.location = castToReference(value); // Reference
          break;
        case 841294093: // participantType
          this.getParticipantType().add(new ActivityParticipantTypeEnumFactory().fromType(value)); // Enumeration<ActivityParticipantType>
          break;
        case -309474065: // product
          this.product = (Type) value; // Type
          break;
        case -1285004149: // quantity
          this.quantity = castToSimpleQuantity(value); // SimpleQuantity
          break;
        case -1724546052: // description
          this.description = castToString(value); // StringType
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("moduleMetadata"))
          this.moduleMetadata = castToModuleMetadata(value); // ModuleMetadata
        else if (name.equals("library"))
          this.getLibrary().add(castToReference(value));
        else if (name.equals("category"))
          this.category = new ActivityDefinitionCategoryEnumFactory().fromType(value); // Enumeration<ActivityDefinitionCategory>
        else if (name.equals("code"))
          this.code = castToCodeableConcept(value); // CodeableConcept
        else if (name.equals("timing[x]"))
          this.timing = (Type) value; // Type
        else if (name.equals("location"))
          this.location = castToReference(value); // Reference
        else if (name.equals("participantType"))
          this.getParticipantType().add(new ActivityParticipantTypeEnumFactory().fromType(value));
        else if (name.equals("product[x]"))
          this.product = (Type) value; // Type
        else if (name.equals("quantity"))
          this.quantity = castToSimpleQuantity(value); // SimpleQuantity
        else if (name.equals("description"))
          this.description = castToString(value); // StringType
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 455891387:  return getModuleMetadata(); // ModuleMetadata
        case 166208699:  return addLibrary(); // Reference
        case 50511102: throw new FHIRException("Cannot make property category as it is not a complex type"); // Enumeration<ActivityDefinitionCategory>
        case 3059181:  return getCode(); // CodeableConcept
        case 164632566:  return getTiming(); // Type
        case 1901043637:  return getLocation(); // Reference
        case 841294093: throw new FHIRException("Cannot make property participantType as it is not a complex type"); // Enumeration<ActivityParticipantType>
        case 1753005361:  return getProduct(); // Type
        case -1285004149:  return getQuantity(); // SimpleQuantity
        case -1724546052: throw new FHIRException("Cannot make property description as it is not a complex type"); // StringType
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("moduleMetadata")) {
          this.moduleMetadata = new ModuleMetadata();
          return this.moduleMetadata;
        }
        else if (name.equals("library")) {
          return addLibrary();
        }
        else if (name.equals("category")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActivityDefinition.category");
        }
        else if (name.equals("code")) {
          this.code = new CodeableConcept();
          return this.code;
        }
        else if (name.equals("timingCodeableConcept")) {
          this.timing = new CodeableConcept();
          return this.timing;
        }
        else if (name.equals("timingTiming")) {
          this.timing = new Timing();
          return this.timing;
        }
        else if (name.equals("location")) {
          this.location = new Reference();
          return this.location;
        }
        else if (name.equals("participantType")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActivityDefinition.participantType");
        }
        else if (name.equals("productReference")) {
          this.product = new Reference();
          return this.product;
        }
        else if (name.equals("productCodeableConcept")) {
          this.product = new CodeableConcept();
          return this.product;
        }
        else if (name.equals("quantity")) {
          this.quantity = new SimpleQuantity();
          return this.quantity;
        }
        else if (name.equals("description")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActivityDefinition.description");
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "ActivityDefinition";

  }

      public ActivityDefinition copy() {
        ActivityDefinition dst = new ActivityDefinition();
        copyValues(dst);
        dst.moduleMetadata = moduleMetadata == null ? null : moduleMetadata.copy();
        if (library != null) {
          dst.library = new ArrayList<Reference>();
          for (Reference i : library)
            dst.library.add(i.copy());
        };
        dst.category = category == null ? null : category.copy();
        dst.code = code == null ? null : code.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.location = location == null ? null : location.copy();
        if (participantType != null) {
          dst.participantType = new ArrayList<Enumeration<ActivityParticipantType>>();
          for (Enumeration<ActivityParticipantType> i : participantType)
            dst.participantType.add(i.copy());
        };
        dst.product = product == null ? null : product.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.description = description == null ? null : description.copy();
        return dst;
      }

      protected ActivityDefinition typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof ActivityDefinition))
          return false;
        ActivityDefinition o = (ActivityDefinition) other;
        return compareDeep(moduleMetadata, o.moduleMetadata, true) && compareDeep(library, o.library, true)
           && compareDeep(category, o.category, true) && compareDeep(code, o.code, true) && compareDeep(timing, o.timing, true)
           && compareDeep(location, o.location, true) && compareDeep(participantType, o.participantType, true)
           && compareDeep(product, o.product, true) && compareDeep(quantity, o.quantity, true) && compareDeep(description, o.description, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof ActivityDefinition))
          return false;
        ActivityDefinition o = (ActivityDefinition) other;
        return compareValues(category, o.category, true) && compareValues(participantType, o.participantType, true)
           && compareValues(description, o.description, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(moduleMetadata, library, category
          , code, timing, location, participantType, product, quantity, description);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ActivityDefinition;
   }

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>Logical identifier for the module (e.g. CMS-143)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="ActivityDefinition.moduleMetadata.identifier", description="Logical identifier for the module (e.g. CMS-143)", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>Logical identifier for the module (e.g. CMS-143)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);

 /**
   * Search parameter: <b>topic</b>
   * <p>
   * Description: <b>Topics associated with the module</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.topic</b><br>
   * </p>
   */
  @SearchParamDefinition(name="topic", path="ActivityDefinition.moduleMetadata.topic", description="Topics associated with the module", type="token" )
  public static final String SP_TOPIC = "topic";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>topic</b>
   * <p>
   * Description: <b>Topics associated with the module</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.topic</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam TOPIC = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_TOPIC);

 /**
   * Search parameter: <b>description</b>
   * <p>
   * Description: <b>Text search against the description</b><br>
   * Type: <b>string</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.description</b><br>
   * </p>
   */
  @SearchParamDefinition(name="description", path="ActivityDefinition.moduleMetadata.description", description="Text search against the description", type="string" )
  public static final String SP_DESCRIPTION = "description";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>description</b>
   * <p>
   * Description: <b>Text search against the description</b><br>
   * Type: <b>string</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.description</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam DESCRIPTION = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_DESCRIPTION);

 /**
   * Search parameter: <b>title</b>
   * <p>
   * Description: <b>Text search against the title</b><br>
   * Type: <b>string</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.title</b><br>
   * </p>
   */
  @SearchParamDefinition(name="title", path="ActivityDefinition.moduleMetadata.title", description="Text search against the title", type="string" )
  public static final String SP_TITLE = "title";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>title</b>
   * <p>
   * Description: <b>Text search against the title</b><br>
   * Type: <b>string</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.title</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam TITLE = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_TITLE);

 /**
   * Search parameter: <b>version</b>
   * <p>
   * Description: <b>Version of the module (e.g. 1.0.0)</b><br>
   * Type: <b>string</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.version</b><br>
   * </p>
   */
  @SearchParamDefinition(name="version", path="ActivityDefinition.moduleMetadata.version", description="Version of the module (e.g. 1.0.0)", type="string" )
  public static final String SP_VERSION = "version";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>version</b>
   * <p>
   * Description: <b>Version of the module (e.g. 1.0.0)</b><br>
   * Type: <b>string</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.version</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam VERSION = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_VERSION);

 /**
   * Search parameter: <b>status</b>
   * <p>
   * Description: <b>Status of the module</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="status", path="ActivityDefinition.moduleMetadata.status", description="Status of the module", type="token" )
  public static final String SP_STATUS = "status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>status</b>
   * <p>
   * Description: <b>Status of the module</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ActivityDefinition.moduleMetadata.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_STATUS);


}

