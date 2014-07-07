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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

import java.util.*;

/**
 * A patient's point-of-time immunization status and recommendation with optional supporting justification.
 */
public class ImmunizationRecommendation extends Resource {

    public static class ImmunizationRecommendationRecommendationComponent extends BackboneElement {
        /**
         * The date the immunization recommendation was created.
         */
        protected DateTime date;

        /**
         * Vaccine that pertains to the recommendation.
         */
        protected CodeableConcept vaccineType;

        /**
         * This indicates the next recommended dose number (e.g. dose 2 is the next recommended dose).
         */
        protected Integer doseNumber;

        /**
         * Vaccine administration status.
         */
        protected CodeableConcept forecastStatus;

        /**
         * Vaccine date recommendations - e.g. earliest date to administer, latest date to administer, etc.
         */
        protected List<ImmunizationRecommendationRecommendationDateCriterionComponent> dateCriterion = new ArrayList<ImmunizationRecommendationRecommendationDateCriterionComponent>();

        /**
         * Contains information about the protocol under which the vaccine was administered.
         */
        protected ImmunizationRecommendationRecommendationProtocolComponent protocol;

        /**
         * Immunization event history that supports the status and recommendation.
         */
        protected List<ResourceReference> supportingImmunization = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Immunization event history that supports the status and recommendation.)
         */
        protected List<Immunization> supportingImmunizationTarget = new ArrayList<Immunization>();


        /**
         * Patient Information that supports the status and recommendation.  This includes patient observations, adverse reactions and allergy/intolerance information.
         */
        protected List<ResourceReference> supportingPatientInformation = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Patient Information that supports the status and recommendation.  This includes patient observations, adverse reactions and allergy/intolerance information.)
         */
        protected List<Resource> supportingPatientInformationTarget = new ArrayList<Resource>();


        private static final long serialVersionUID = -143636358L;

      public ImmunizationRecommendationRecommendationComponent() {
        super();
      }

      public ImmunizationRecommendationRecommendationComponent(DateTime date, CodeableConcept vaccineType, CodeableConcept forecastStatus) {
        super();
        this.date = date;
        this.vaccineType = vaccineType;
        this.forecastStatus = forecastStatus;
      }

        /**
         * @return {@link #date} (The date the immunization recommendation was created.)
         */
        public DateTime getDate() { 
          return this.date;
        }

        /**
         * @param value {@link #date} (The date the immunization recommendation was created.)
         */
        public ImmunizationRecommendationRecommendationComponent setDate(DateTime value) { 
          this.date = value;
          return this;
        }

        /**
         * @return The date the immunization recommendation was created.
         */
        public DateAndTime getDateSimple() { 
          return this.date == null ? null : this.date.getValue();
        }

        /**
         * @param value The date the immunization recommendation was created.
         */
        public ImmunizationRecommendationRecommendationComponent setDateSimple(DateAndTime value) { 
            if (this.date == null)
              this.date = new DateTime();
            this.date.setValue(value);
          return this;
        }

        /**
         * @return {@link #vaccineType} (Vaccine that pertains to the recommendation.)
         */
        public CodeableConcept getVaccineType() { 
          return this.vaccineType;
        }

        /**
         * @param value {@link #vaccineType} (Vaccine that pertains to the recommendation.)
         */
        public ImmunizationRecommendationRecommendationComponent setVaccineType(CodeableConcept value) { 
          this.vaccineType = value;
          return this;
        }

        /**
         * @return {@link #doseNumber} (This indicates the next recommended dose number (e.g. dose 2 is the next recommended dose).)
         */
        public Integer getDoseNumber() { 
          return this.doseNumber;
        }

        /**
         * @param value {@link #doseNumber} (This indicates the next recommended dose number (e.g. dose 2 is the next recommended dose).)
         */
        public ImmunizationRecommendationRecommendationComponent setDoseNumber(Integer value) { 
          this.doseNumber = value;
          return this;
        }

        /**
         * @return This indicates the next recommended dose number (e.g. dose 2 is the next recommended dose).
         */
        public int getDoseNumberSimple() { 
          return this.doseNumber == null ? null : this.doseNumber.getValue();
        }

        /**
         * @param value This indicates the next recommended dose number (e.g. dose 2 is the next recommended dose).
         */
        public ImmunizationRecommendationRecommendationComponent setDoseNumberSimple(int value) { 
          if (value == -1)
            this.doseNumber = null;
          else {
            if (this.doseNumber == null)
              this.doseNumber = new Integer();
            this.doseNumber.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #forecastStatus} (Vaccine administration status.)
         */
        public CodeableConcept getForecastStatus() { 
          return this.forecastStatus;
        }

        /**
         * @param value {@link #forecastStatus} (Vaccine administration status.)
         */
        public ImmunizationRecommendationRecommendationComponent setForecastStatus(CodeableConcept value) { 
          this.forecastStatus = value;
          return this;
        }

        /**
         * @return {@link #dateCriterion} (Vaccine date recommendations - e.g. earliest date to administer, latest date to administer, etc.)
         */
        public List<ImmunizationRecommendationRecommendationDateCriterionComponent> getDateCriterion() { 
          return this.dateCriterion;
        }

    // syntactic sugar
        /**
         * @return {@link #dateCriterion} (Vaccine date recommendations - e.g. earliest date to administer, latest date to administer, etc.)
         */
        public ImmunizationRecommendationRecommendationDateCriterionComponent addDateCriterion() { 
          ImmunizationRecommendationRecommendationDateCriterionComponent t = new ImmunizationRecommendationRecommendationDateCriterionComponent();
          this.dateCriterion.add(t);
          return t;
        }

        /**
         * @return {@link #protocol} (Contains information about the protocol under which the vaccine was administered.)
         */
        public ImmunizationRecommendationRecommendationProtocolComponent getProtocol() { 
          return this.protocol;
        }

        /**
         * @param value {@link #protocol} (Contains information about the protocol under which the vaccine was administered.)
         */
        public ImmunizationRecommendationRecommendationComponent setProtocol(ImmunizationRecommendationRecommendationProtocolComponent value) { 
          this.protocol = value;
          return this;
        }

        /**
         * @return {@link #supportingImmunization} (Immunization event history that supports the status and recommendation.)
         */
        public List<ResourceReference> getSupportingImmunization() { 
          return this.supportingImmunization;
        }

    // syntactic sugar
        /**
         * @return {@link #supportingImmunization} (Immunization event history that supports the status and recommendation.)
         */
        public ResourceReference addSupportingImmunization() { 
          ResourceReference t = new ResourceReference();
          this.supportingImmunization.add(t);
          return t;
        }

        /**
         * @return {@link #supportingImmunization} (The actual objects that are the target of the reference. Immunization event history that supports the status and recommendation.)
         */
        public List<Immunization> getSupportingImmunizationTarget() { 
          return this.supportingImmunizationTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #supportingImmunization} (Add an actual object that is the target of the reference. Immunization event history that supports the status and recommendation.)
         */
        public Immunization addSupportingImmunizationTarget() { 
          Immunization r = new Immunization();
          this.supportingImmunizationTarget.add(r);
          return r;
        }

        /**
         * @return {@link #supportingPatientInformation} (Patient Information that supports the status and recommendation.  This includes patient observations, adverse reactions and allergy/intolerance information.)
         */
        public List<ResourceReference> getSupportingPatientInformation() { 
          return this.supportingPatientInformation;
        }

    // syntactic sugar
        /**
         * @return {@link #supportingPatientInformation} (Patient Information that supports the status and recommendation.  This includes patient observations, adverse reactions and allergy/intolerance information.)
         */
        public ResourceReference addSupportingPatientInformation() { 
          ResourceReference t = new ResourceReference();
          this.supportingPatientInformation.add(t);
          return t;
        }

        /**
         * @return {@link #supportingPatientInformation} (The actual objects that are the target of the reference. Patient Information that supports the status and recommendation.  This includes patient observations, adverse reactions and allergy/intolerance information.)
         */
        public List<Resource> getSupportingPatientInformationTarget() { 
          return this.supportingPatientInformationTarget;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("date", "dateTime", "The date the immunization recommendation was created.", 0, java.lang.Integer.MAX_VALUE, date));
          childrenList.add(new Property("vaccineType", "CodeableConcept", "Vaccine that pertains to the recommendation.", 0, java.lang.Integer.MAX_VALUE, vaccineType));
          childrenList.add(new Property("doseNumber", "integer", "This indicates the next recommended dose number (e.g. dose 2 is the next recommended dose).", 0, java.lang.Integer.MAX_VALUE, doseNumber));
          childrenList.add(new Property("forecastStatus", "CodeableConcept", "Vaccine administration status.", 0, java.lang.Integer.MAX_VALUE, forecastStatus));
          childrenList.add(new Property("dateCriterion", "", "Vaccine date recommendations - e.g. earliest date to administer, latest date to administer, etc.", 0, java.lang.Integer.MAX_VALUE, dateCriterion));
          childrenList.add(new Property("protocol", "", "Contains information about the protocol under which the vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, protocol));
          childrenList.add(new Property("supportingImmunization", "Resource(Immunization)", "Immunization event history that supports the status and recommendation.", 0, java.lang.Integer.MAX_VALUE, supportingImmunization));
          childrenList.add(new Property("supportingPatientInformation", "Resource(Observation|AdverseReaction|AllergyIntolerance)", "Patient Information that supports the status and recommendation.  This includes patient observations, adverse reactions and allergy/intolerance information.", 0, java.lang.Integer.MAX_VALUE, supportingPatientInformation));
        }

      public ImmunizationRecommendationRecommendationComponent copy() {
        ImmunizationRecommendationRecommendationComponent dst = new ImmunizationRecommendationRecommendationComponent();
        dst.date = date == null ? null : date.copy();
        dst.vaccineType = vaccineType == null ? null : vaccineType.copy();
        dst.doseNumber = doseNumber == null ? null : doseNumber.copy();
        dst.forecastStatus = forecastStatus == null ? null : forecastStatus.copy();
        dst.dateCriterion = new ArrayList<ImmunizationRecommendationRecommendationDateCriterionComponent>();
        for (ImmunizationRecommendationRecommendationDateCriterionComponent i : dateCriterion)
          dst.dateCriterion.add(i.copy());
        dst.protocol = protocol == null ? null : protocol.copy();
        dst.supportingImmunization = new ArrayList<ResourceReference>();
        for (ResourceReference i : supportingImmunization)
          dst.supportingImmunization.add(i.copy());
        dst.supportingPatientInformation = new ArrayList<ResourceReference>();
        for (ResourceReference i : supportingPatientInformation)
          dst.supportingPatientInformation.add(i.copy());
        return dst;
      }

  }

    public static class ImmunizationRecommendationRecommendationDateCriterionComponent extends BackboneElement {
        /**
         * Date classification of recommendation - e.g. earliest date to give, latest date to give, etc.
         */
        protected CodeableConcept code;

        /**
         * Date recommendation.
         */
        protected DateTime value;

        private static final long serialVersionUID = -647492960L;

      public ImmunizationRecommendationRecommendationDateCriterionComponent() {
        super();
      }

      public ImmunizationRecommendationRecommendationDateCriterionComponent(CodeableConcept code, DateTime value) {
        super();
        this.code = code;
        this.value = value;
      }

        /**
         * @return {@link #code} (Date classification of recommendation - e.g. earliest date to give, latest date to give, etc.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Date classification of recommendation - e.g. earliest date to give, latest date to give, etc.)
         */
        public ImmunizationRecommendationRecommendationDateCriterionComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #value} (Date recommendation.)
         */
        public DateTime getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (Date recommendation.)
         */
        public ImmunizationRecommendationRecommendationDateCriterionComponent setValue(DateTime value) { 
          this.value = value;
          return this;
        }

        /**
         * @return Date recommendation.
         */
        public DateAndTime getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value Date recommendation.
         */
        public ImmunizationRecommendationRecommendationDateCriterionComponent setValueSimple(DateAndTime value) { 
            if (this.value == null)
              this.value = new DateTime();
            this.value.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Date classification of recommendation - e.g. earliest date to give, latest date to give, etc.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("value", "dateTime", "Date recommendation.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      public ImmunizationRecommendationRecommendationDateCriterionComponent copy() {
        ImmunizationRecommendationRecommendationDateCriterionComponent dst = new ImmunizationRecommendationRecommendationDateCriterionComponent();
        dst.code = code == null ? null : code.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    public static class ImmunizationRecommendationRecommendationProtocolComponent extends BackboneElement {
        /**
         * Indicates the nominal position in a series of the next dose.  This is the recommended dose number as per a specified protocol.
         */
        protected Integer doseSequence;

        /**
         * Contains the description about the protocol under which the vaccine was administered.
         */
        protected String_ description;

        /**
         * Indicates the authority who published the protocol?  E.g. ACIP.
         */
        protected ResourceReference authority;

        /**
         * The actual object that is the target of the reference (Indicates the authority who published the protocol?  E.g. ACIP.)
         */
        protected Organization authorityTarget;

        /**
         * One possible path to achieve presumed immunity against a disease - within the context of an authority.
         */
        protected String_ series;

        private static final long serialVersionUID = -732744530L;

      public ImmunizationRecommendationRecommendationProtocolComponent() {
        super();
      }

        /**
         * @return {@link #doseSequence} (Indicates the nominal position in a series of the next dose.  This is the recommended dose number as per a specified protocol.)
         */
        public Integer getDoseSequence() { 
          return this.doseSequence;
        }

        /**
         * @param value {@link #doseSequence} (Indicates the nominal position in a series of the next dose.  This is the recommended dose number as per a specified protocol.)
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setDoseSequence(Integer value) { 
          this.doseSequence = value;
          return this;
        }

        /**
         * @return Indicates the nominal position in a series of the next dose.  This is the recommended dose number as per a specified protocol.
         */
        public int getDoseSequenceSimple() { 
          return this.doseSequence == null ? null : this.doseSequence.getValue();
        }

        /**
         * @param value Indicates the nominal position in a series of the next dose.  This is the recommended dose number as per a specified protocol.
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setDoseSequenceSimple(int value) { 
          if (value == -1)
            this.doseSequence = null;
          else {
            if (this.doseSequence == null)
              this.doseSequence = new Integer();
            this.doseSequence.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #description} (Contains the description about the protocol under which the vaccine was administered.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Contains the description about the protocol under which the vaccine was administered.)
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Contains the description about the protocol under which the vaccine was administered.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Contains the description about the protocol under which the vaccine was administered.
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #authority} (Indicates the authority who published the protocol?  E.g. ACIP.)
         */
        public ResourceReference getAuthority() { 
          return this.authority;
        }

        /**
         * @param value {@link #authority} (Indicates the authority who published the protocol?  E.g. ACIP.)
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setAuthority(ResourceReference value) { 
          this.authority = value;
          return this;
        }

        /**
         * @return {@link #authority} (The actual object that is the target of the reference. Indicates the authority who published the protocol?  E.g. ACIP.)
         */
        public Organization getAuthorityTarget() { 
          return this.authorityTarget;
        }

        /**
         * @param value {@link #authority} (The actual object that is the target of the reference. Indicates the authority who published the protocol?  E.g. ACIP.)
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setAuthorityTarget(Organization value) { 
          this.authorityTarget = value;
          return this;
        }

        /**
         * @return {@link #series} (One possible path to achieve presumed immunity against a disease - within the context of an authority.)
         */
        public String_ getSeries() { 
          return this.series;
        }

        /**
         * @param value {@link #series} (One possible path to achieve presumed immunity against a disease - within the context of an authority.)
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setSeries(String_ value) { 
          this.series = value;
          return this;
        }

        /**
         * @return One possible path to achieve presumed immunity against a disease - within the context of an authority.
         */
        public String getSeriesSimple() { 
          return this.series == null ? null : this.series.getValue();
        }

        /**
         * @param value One possible path to achieve presumed immunity against a disease - within the context of an authority.
         */
        public ImmunizationRecommendationRecommendationProtocolComponent setSeriesSimple(String value) { 
          if (value == null)
            this.series = null;
          else {
            if (this.series == null)
              this.series = new String_();
            this.series.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("doseSequence", "integer", "Indicates the nominal position in a series of the next dose.  This is the recommended dose number as per a specified protocol.", 0, java.lang.Integer.MAX_VALUE, doseSequence));
          childrenList.add(new Property("description", "string", "Contains the description about the protocol under which the vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("authority", "Resource(Organization)", "Indicates the authority who published the protocol?  E.g. ACIP.", 0, java.lang.Integer.MAX_VALUE, authority));
          childrenList.add(new Property("series", "string", "One possible path to achieve presumed immunity against a disease - within the context of an authority.", 0, java.lang.Integer.MAX_VALUE, series));
        }

      public ImmunizationRecommendationRecommendationProtocolComponent copy() {
        ImmunizationRecommendationRecommendationProtocolComponent dst = new ImmunizationRecommendationRecommendationProtocolComponent();
        dst.doseSequence = doseSequence == null ? null : doseSequence.copy();
        dst.description = description == null ? null : description.copy();
        dst.authority = authority == null ? null : authority.copy();
        dst.series = series == null ? null : series.copy();
        return dst;
      }

  }

    /**
     * A unique identifier assigned to this particular recommendation record.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The patient who is the subject of the profile.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The patient who is the subject of the profile.)
     */
    protected Patient subjectTarget;

    /**
     * Vaccine administration recommendations.
     */
    protected List<ImmunizationRecommendationRecommendationComponent> recommendation = new ArrayList<ImmunizationRecommendationRecommendationComponent>();

    private static final long serialVersionUID = -1986363579L;

    public ImmunizationRecommendation() {
      super();
    }

    public ImmunizationRecommendation(ResourceReference subject) {
      super();
      this.subject = subject;
    }

    /**
     * @return {@link #identifier} (A unique identifier assigned to this particular recommendation record.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (A unique identifier assigned to this particular recommendation record.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (The patient who is the subject of the profile.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who is the subject of the profile.)
     */
    public ImmunizationRecommendation setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. The patient who is the subject of the profile.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The patient who is the subject of the profile.)
     */
    public ImmunizationRecommendation setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #recommendation} (Vaccine administration recommendations.)
     */
    public List<ImmunizationRecommendationRecommendationComponent> getRecommendation() { 
      return this.recommendation;
    }

    // syntactic sugar
    /**
     * @return {@link #recommendation} (Vaccine administration recommendations.)
     */
    public ImmunizationRecommendationRecommendationComponent addRecommendation() { 
      ImmunizationRecommendationRecommendationComponent t = new ImmunizationRecommendationRecommendationComponent();
      this.recommendation.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "A unique identifier assigned to this particular recommendation record.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient)", "The patient who is the subject of the profile.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("recommendation", "", "Vaccine administration recommendations.", 0, java.lang.Integer.MAX_VALUE, recommendation));
      }

      public ImmunizationRecommendation copy() {
        ImmunizationRecommendation dst = new ImmunizationRecommendation();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.recommendation = new ArrayList<ImmunizationRecommendationRecommendationComponent>();
        for (ImmunizationRecommendationRecommendationComponent i : recommendation)
          dst.recommendation.add(i.copy());
        return dst;
      }

      protected ImmunizationRecommendation typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ImmunizationRecommendation;
   }


}

