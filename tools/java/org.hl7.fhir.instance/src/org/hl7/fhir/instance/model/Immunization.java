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

// Generated on Mon, Feb 3, 2014 15:10+1100 for FHIR v0.80

import java.util.*;

/**
 * Immunization event information.
 */
public class Immunization extends Resource {

    public static class ImmunizationExplanationComponent extends BackboneElement {
        /**
         * Reasons why a vaccine was administered.
         */
        protected List<CodeableConcept> reason = new ArrayList<CodeableConcept>();

        /**
         * Refusal or exemption reasons.
         */
        protected List<CodeableConcept> refusalReason = new ArrayList<CodeableConcept>();

      public ImmunizationExplanationComponent() {
        super();
      }

        /**
         * @return {@link #reason} (Reasons why a vaccine was administered.)
         */
        public List<CodeableConcept> getReason() { 
          return this.reason;
        }

    // syntactic sugar
        /**
         * @return {@link #reason} (Reasons why a vaccine was administered.)
         */
        public CodeableConcept addReason() { 
          CodeableConcept t = new CodeableConcept();
          this.reason.add(t);
          return t;
        }

        /**
         * @return {@link #refusalReason} (Refusal or exemption reasons.)
         */
        public List<CodeableConcept> getRefusalReason() { 
          return this.refusalReason;
        }

    // syntactic sugar
        /**
         * @return {@link #refusalReason} (Refusal or exemption reasons.)
         */
        public CodeableConcept addRefusalReason() { 
          CodeableConcept t = new CodeableConcept();
          this.refusalReason.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("reason", "CodeableConcept", "Reasons why a vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, reason));
          childrenList.add(new Property("refusalReason", "CodeableConcept", "Refusal or exemption reasons.", 0, java.lang.Integer.MAX_VALUE, refusalReason));
        }

      public ImmunizationExplanationComponent copy(Immunization e) {
        ImmunizationExplanationComponent dst = new ImmunizationExplanationComponent();
        dst.reason = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : reason)
          dst.reason.add(i.copy());
        dst.refusalReason = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : refusalReason)
          dst.refusalReason.add(i.copy());
        return dst;
      }

  }

    public static class ImmunizationReactionComponent extends BackboneElement {
        /**
         * Date of reaction to the immunization.
         */
        protected DateTime date;

        /**
         * Details of the reaction.
         */
        protected ResourceReference detail;

        /**
         * Self-reported indicator.
         */
        protected Boolean reported;

      public ImmunizationReactionComponent() {
        super();
      }

        /**
         * @return {@link #date} (Date of reaction to the immunization.)
         */
        public DateTime getDate() { 
          return this.date;
        }

        /**
         * @param value {@link #date} (Date of reaction to the immunization.)
         */
        public ImmunizationReactionComponent setDate(DateTime value) { 
          this.date = value;
          return this;
        }

        /**
         * @return Date of reaction to the immunization.
         */
        public DateAndTime getDateSimple() { 
          return this.date == null ? null : this.date.getValue();
        }

        /**
         * @param value Date of reaction to the immunization.
         */
        public ImmunizationReactionComponent setDateSimple(DateAndTime value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateTime();
            this.date.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #detail} (Details of the reaction.)
         */
        public ResourceReference getDetail() { 
          return this.detail;
        }

        /**
         * @param value {@link #detail} (Details of the reaction.)
         */
        public ImmunizationReactionComponent setDetail(ResourceReference value) { 
          this.detail = value;
          return this;
        }

        /**
         * @return {@link #reported} (Self-reported indicator.)
         */
        public Boolean getReported() { 
          return this.reported;
        }

        /**
         * @param value {@link #reported} (Self-reported indicator.)
         */
        public ImmunizationReactionComponent setReported(Boolean value) { 
          this.reported = value;
          return this;
        }

        /**
         * @return Self-reported indicator.
         */
        public boolean getReportedSimple() { 
          return this.reported == null ? false : this.reported.getValue();
        }

        /**
         * @param value Self-reported indicator.
         */
        public ImmunizationReactionComponent setReportedSimple(boolean value) { 
          if (value == false)
            this.reported = null;
          else {
            if (this.reported == null)
              this.reported = new Boolean();
            this.reported.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("date", "dateTime", "Date of reaction to the immunization.", 0, java.lang.Integer.MAX_VALUE, date));
          childrenList.add(new Property("detail", "Resource(AdverseReaction|Observation)", "Details of the reaction.", 0, java.lang.Integer.MAX_VALUE, detail));
          childrenList.add(new Property("reported", "boolean", "Self-reported indicator.", 0, java.lang.Integer.MAX_VALUE, reported));
        }

      public ImmunizationReactionComponent copy(Immunization e) {
        ImmunizationReactionComponent dst = new ImmunizationReactionComponent();
        dst.date = date == null ? null : date.copy();
        dst.detail = detail == null ? null : detail.copy();
        dst.reported = reported == null ? null : reported.copy();
        return dst;
      }

  }

    public static class ImmunizationVaccinationProtocolComponent extends BackboneElement {
        /**
         * Nominal position in a series.
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
         * One possible path to achieve presumed immunity against a disease - within the context of an authority.
         */
        protected String_ series;

        /**
         * The recommended number of doses to achieve immunity.
         */
        protected Integer seriesDoses;

        /**
         * The targeted disease.
         */
        protected CodeableConcept doseTarget;

        /**
         * Indicates if the immunization event should "count" against  the protocol.
         */
        protected CodeableConcept doseStatus;

        /**
         * Provides an explanation as to why a immunization event should or should not count against the protocol.
         */
        protected CodeableConcept doseStatusReason;

      public ImmunizationVaccinationProtocolComponent() {
        super();
      }

      public ImmunizationVaccinationProtocolComponent(Integer doseSequence, CodeableConcept doseTarget, CodeableConcept doseStatus) {
        super();
        this.doseSequence = doseSequence;
        this.doseTarget = doseTarget;
        this.doseStatus = doseStatus;
      }

        /**
         * @return {@link #doseSequence} (Nominal position in a series.)
         */
        public Integer getDoseSequence() { 
          return this.doseSequence;
        }

        /**
         * @param value {@link #doseSequence} (Nominal position in a series.)
         */
        public ImmunizationVaccinationProtocolComponent setDoseSequence(Integer value) { 
          this.doseSequence = value;
          return this;
        }

        /**
         * @return Nominal position in a series.
         */
        public int getDoseSequenceSimple() { 
          return this.doseSequence == null ? null : this.doseSequence.getValue();
        }

        /**
         * @param value Nominal position in a series.
         */
        public ImmunizationVaccinationProtocolComponent setDoseSequenceSimple(int value) { 
            if (this.doseSequence == null)
              this.doseSequence = new Integer();
            this.doseSequence.setValue(value);
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
        public ImmunizationVaccinationProtocolComponent setDescription(String_ value) { 
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
        public ImmunizationVaccinationProtocolComponent setDescriptionSimple(String value) { 
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
        public ImmunizationVaccinationProtocolComponent setAuthority(ResourceReference value) { 
          this.authority = value;
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
        public ImmunizationVaccinationProtocolComponent setSeries(String_ value) { 
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
        public ImmunizationVaccinationProtocolComponent setSeriesSimple(String value) { 
          if (value == null)
            this.series = null;
          else {
            if (this.series == null)
              this.series = new String_();
            this.series.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #seriesDoses} (The recommended number of doses to achieve immunity.)
         */
        public Integer getSeriesDoses() { 
          return this.seriesDoses;
        }

        /**
         * @param value {@link #seriesDoses} (The recommended number of doses to achieve immunity.)
         */
        public ImmunizationVaccinationProtocolComponent setSeriesDoses(Integer value) { 
          this.seriesDoses = value;
          return this;
        }

        /**
         * @return The recommended number of doses to achieve immunity.
         */
        public int getSeriesDosesSimple() { 
          return this.seriesDoses == null ? null : this.seriesDoses.getValue();
        }

        /**
         * @param value The recommended number of doses to achieve immunity.
         */
        public ImmunizationVaccinationProtocolComponent setSeriesDosesSimple(int value) { 
          if (value == -1)
            this.seriesDoses = null;
          else {
            if (this.seriesDoses == null)
              this.seriesDoses = new Integer();
            this.seriesDoses.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #doseTarget} (The targeted disease.)
         */
        public CodeableConcept getDoseTarget() { 
          return this.doseTarget;
        }

        /**
         * @param value {@link #doseTarget} (The targeted disease.)
         */
        public ImmunizationVaccinationProtocolComponent setDoseTarget(CodeableConcept value) { 
          this.doseTarget = value;
          return this;
        }

        /**
         * @return {@link #doseStatus} (Indicates if the immunization event should "count" against  the protocol.)
         */
        public CodeableConcept getDoseStatus() { 
          return this.doseStatus;
        }

        /**
         * @param value {@link #doseStatus} (Indicates if the immunization event should "count" against  the protocol.)
         */
        public ImmunizationVaccinationProtocolComponent setDoseStatus(CodeableConcept value) { 
          this.doseStatus = value;
          return this;
        }

        /**
         * @return {@link #doseStatusReason} (Provides an explanation as to why a immunization event should or should not count against the protocol.)
         */
        public CodeableConcept getDoseStatusReason() { 
          return this.doseStatusReason;
        }

        /**
         * @param value {@link #doseStatusReason} (Provides an explanation as to why a immunization event should or should not count against the protocol.)
         */
        public ImmunizationVaccinationProtocolComponent setDoseStatusReason(CodeableConcept value) { 
          this.doseStatusReason = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("doseSequence", "integer", "Nominal position in a series.", 0, java.lang.Integer.MAX_VALUE, doseSequence));
          childrenList.add(new Property("description", "string", "Contains the description about the protocol under which the vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("authority", "Resource(Organization)", "Indicates the authority who published the protocol?  E.g. ACIP.", 0, java.lang.Integer.MAX_VALUE, authority));
          childrenList.add(new Property("series", "string", "One possible path to achieve presumed immunity against a disease - within the context of an authority.", 0, java.lang.Integer.MAX_VALUE, series));
          childrenList.add(new Property("seriesDoses", "integer", "The recommended number of doses to achieve immunity.", 0, java.lang.Integer.MAX_VALUE, seriesDoses));
          childrenList.add(new Property("doseTarget", "CodeableConcept", "The targeted disease.", 0, java.lang.Integer.MAX_VALUE, doseTarget));
          childrenList.add(new Property("doseStatus", "CodeableConcept", "Indicates if the immunization event should 'count' against  the protocol.", 0, java.lang.Integer.MAX_VALUE, doseStatus));
          childrenList.add(new Property("doseStatusReason", "CodeableConcept", "Provides an explanation as to why a immunization event should or should not count against the protocol.", 0, java.lang.Integer.MAX_VALUE, doseStatusReason));
        }

      public ImmunizationVaccinationProtocolComponent copy(Immunization e) {
        ImmunizationVaccinationProtocolComponent dst = new ImmunizationVaccinationProtocolComponent();
        dst.doseSequence = doseSequence == null ? null : doseSequence.copy();
        dst.description = description == null ? null : description.copy();
        dst.authority = authority == null ? null : authority.copy();
        dst.series = series == null ? null : series.copy();
        dst.seriesDoses = seriesDoses == null ? null : seriesDoses.copy();
        dst.doseTarget = doseTarget == null ? null : doseTarget.copy();
        dst.doseStatus = doseStatus == null ? null : doseStatus.copy();
        dst.doseStatusReason = doseStatusReason == null ? null : doseStatusReason.copy();
        return dst;
      }

  }

    /**
     * A unique identifier assigned to this adverse reaction record.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Date vaccine administered or was to be administered.
     */
    protected DateTime date;

    /**
     * Vaccine that was administered or was to be administered.
     */
    protected CodeableConcept vaccineType;

    /**
     * The patient to whom the vaccine was to be administered.
     */
    protected ResourceReference subject;

    /**
     * Indicates if the vaccination was refused.
     */
    protected Boolean refusedIndicator;

    /**
     * True if this administration was reported rather than directly administered.
     */
    protected Boolean reported;

    /**
     * Clinician who administered the vaccine.
     */
    protected ResourceReference performer;

    /**
     * Clinician who ordered the vaccination.
     */
    protected ResourceReference requester;

    /**
     * Name of vaccine manufacturer.
     */
    protected ResourceReference manufacturer;

    /**
     * The service delivery location where the vaccine administration occurred.
     */
    protected ResourceReference location;

    /**
     * Lot number of the  vaccine product.
     */
    protected String_ lotNumber;

    /**
     * Date vaccine batch expires.
     */
    protected Date expirationDate;

    /**
     * Body site where vaccine was administered.
     */
    protected CodeableConcept site;

    /**
     * The path by which the vaccine product is taken into the body.
     */
    protected CodeableConcept route;

    /**
     * The quantity of vaccine product that was administered.
     */
    protected Quantity doseQuantity;

    /**
     * Reasons why a vaccine was administered or refused.
     */
    protected ImmunizationExplanationComponent explanation;

    /**
     * Categorical data indicating that an adverse event is associated in time to an immunization.
     */
    protected List<ImmunizationReactionComponent> reaction = new ArrayList<ImmunizationReactionComponent>();

    /**
     * Contains information about the protocol(s) under which the vaccine was administered.
     */
    protected List<ImmunizationVaccinationProtocolComponent> vaccinationProtocol = new ArrayList<ImmunizationVaccinationProtocolComponent>();

    public Immunization() {
      super();
    }

    public Immunization(DateTime date, CodeableConcept vaccineType, ResourceReference subject, Boolean refusedIndicator, Boolean reported) {
      super();
      this.date = date;
      this.vaccineType = vaccineType;
      this.subject = subject;
      this.refusedIndicator = refusedIndicator;
      this.reported = reported;
    }

    /**
     * @return {@link #identifier} (A unique identifier assigned to this adverse reaction record.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (A unique identifier assigned to this adverse reaction record.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #date} (Date vaccine administered or was to be administered.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (Date vaccine administered or was to be administered.)
     */
    public Immunization setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return Date vaccine administered or was to be administered.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value Date vaccine administered or was to be administered.
     */
    public Immunization setDateSimple(DateAndTime value) { 
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      return this;
    }

    /**
     * @return {@link #vaccineType} (Vaccine that was administered or was to be administered.)
     */
    public CodeableConcept getVaccineType() { 
      return this.vaccineType;
    }

    /**
     * @param value {@link #vaccineType} (Vaccine that was administered or was to be administered.)
     */
    public Immunization setVaccineType(CodeableConcept value) { 
      this.vaccineType = value;
      return this;
    }

    /**
     * @return {@link #subject} (The patient to whom the vaccine was to be administered.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient to whom the vaccine was to be administered.)
     */
    public Immunization setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #refusedIndicator} (Indicates if the vaccination was refused.)
     */
    public Boolean getRefusedIndicator() { 
      return this.refusedIndicator;
    }

    /**
     * @param value {@link #refusedIndicator} (Indicates if the vaccination was refused.)
     */
    public Immunization setRefusedIndicator(Boolean value) { 
      this.refusedIndicator = value;
      return this;
    }

    /**
     * @return Indicates if the vaccination was refused.
     */
    public boolean getRefusedIndicatorSimple() { 
      return this.refusedIndicator == null ? false : this.refusedIndicator.getValue();
    }

    /**
     * @param value Indicates if the vaccination was refused.
     */
    public Immunization setRefusedIndicatorSimple(boolean value) { 
        if (this.refusedIndicator == null)
          this.refusedIndicator = new Boolean();
        this.refusedIndicator.setValue(value);
      return this;
    }

    /**
     * @return {@link #reported} (True if this administration was reported rather than directly administered.)
     */
    public Boolean getReported() { 
      return this.reported;
    }

    /**
     * @param value {@link #reported} (True if this administration was reported rather than directly administered.)
     */
    public Immunization setReported(Boolean value) { 
      this.reported = value;
      return this;
    }

    /**
     * @return True if this administration was reported rather than directly administered.
     */
    public boolean getReportedSimple() { 
      return this.reported == null ? false : this.reported.getValue();
    }

    /**
     * @param value True if this administration was reported rather than directly administered.
     */
    public Immunization setReportedSimple(boolean value) { 
        if (this.reported == null)
          this.reported = new Boolean();
        this.reported.setValue(value);
      return this;
    }

    /**
     * @return {@link #performer} (Clinician who administered the vaccine.)
     */
    public ResourceReference getPerformer() { 
      return this.performer;
    }

    /**
     * @param value {@link #performer} (Clinician who administered the vaccine.)
     */
    public Immunization setPerformer(ResourceReference value) { 
      this.performer = value;
      return this;
    }

    /**
     * @return {@link #requester} (Clinician who ordered the vaccination.)
     */
    public ResourceReference getRequester() { 
      return this.requester;
    }

    /**
     * @param value {@link #requester} (Clinician who ordered the vaccination.)
     */
    public Immunization setRequester(ResourceReference value) { 
      this.requester = value;
      return this;
    }

    /**
     * @return {@link #manufacturer} (Name of vaccine manufacturer.)
     */
    public ResourceReference getManufacturer() { 
      return this.manufacturer;
    }

    /**
     * @param value {@link #manufacturer} (Name of vaccine manufacturer.)
     */
    public Immunization setManufacturer(ResourceReference value) { 
      this.manufacturer = value;
      return this;
    }

    /**
     * @return {@link #location} (The service delivery location where the vaccine administration occurred.)
     */
    public ResourceReference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (The service delivery location where the vaccine administration occurred.)
     */
    public Immunization setLocation(ResourceReference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #lotNumber} (Lot number of the  vaccine product.)
     */
    public String_ getLotNumber() { 
      return this.lotNumber;
    }

    /**
     * @param value {@link #lotNumber} (Lot number of the  vaccine product.)
     */
    public Immunization setLotNumber(String_ value) { 
      this.lotNumber = value;
      return this;
    }

    /**
     * @return Lot number of the  vaccine product.
     */
    public String getLotNumberSimple() { 
      return this.lotNumber == null ? null : this.lotNumber.getValue();
    }

    /**
     * @param value Lot number of the  vaccine product.
     */
    public Immunization setLotNumberSimple(String value) { 
      if (value == null)
        this.lotNumber = null;
      else {
        if (this.lotNumber == null)
          this.lotNumber = new String_();
        this.lotNumber.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #expirationDate} (Date vaccine batch expires.)
     */
    public Date getExpirationDate() { 
      return this.expirationDate;
    }

    /**
     * @param value {@link #expirationDate} (Date vaccine batch expires.)
     */
    public Immunization setExpirationDate(Date value) { 
      this.expirationDate = value;
      return this;
    }

    /**
     * @return Date vaccine batch expires.
     */
    public DateAndTime getExpirationDateSimple() { 
      return this.expirationDate == null ? null : this.expirationDate.getValue();
    }

    /**
     * @param value Date vaccine batch expires.
     */
    public Immunization setExpirationDateSimple(DateAndTime value) { 
      if (value == null)
        this.expirationDate = null;
      else {
        if (this.expirationDate == null)
          this.expirationDate = new Date();
        this.expirationDate.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #site} (Body site where vaccine was administered.)
     */
    public CodeableConcept getSite() { 
      return this.site;
    }

    /**
     * @param value {@link #site} (Body site where vaccine was administered.)
     */
    public Immunization setSite(CodeableConcept value) { 
      this.site = value;
      return this;
    }

    /**
     * @return {@link #route} (The path by which the vaccine product is taken into the body.)
     */
    public CodeableConcept getRoute() { 
      return this.route;
    }

    /**
     * @param value {@link #route} (The path by which the vaccine product is taken into the body.)
     */
    public Immunization setRoute(CodeableConcept value) { 
      this.route = value;
      return this;
    }

    /**
     * @return {@link #doseQuantity} (The quantity of vaccine product that was administered.)
     */
    public Quantity getDoseQuantity() { 
      return this.doseQuantity;
    }

    /**
     * @param value {@link #doseQuantity} (The quantity of vaccine product that was administered.)
     */
    public Immunization setDoseQuantity(Quantity value) { 
      this.doseQuantity = value;
      return this;
    }

    /**
     * @return {@link #explanation} (Reasons why a vaccine was administered or refused.)
     */
    public ImmunizationExplanationComponent getExplanation() { 
      return this.explanation;
    }

    /**
     * @param value {@link #explanation} (Reasons why a vaccine was administered or refused.)
     */
    public Immunization setExplanation(ImmunizationExplanationComponent value) { 
      this.explanation = value;
      return this;
    }

    /**
     * @return {@link #reaction} (Categorical data indicating that an adverse event is associated in time to an immunization.)
     */
    public List<ImmunizationReactionComponent> getReaction() { 
      return this.reaction;
    }

    // syntactic sugar
    /**
     * @return {@link #reaction} (Categorical data indicating that an adverse event is associated in time to an immunization.)
     */
    public ImmunizationReactionComponent addReaction() { 
      ImmunizationReactionComponent t = new ImmunizationReactionComponent();
      this.reaction.add(t);
      return t;
    }

    /**
     * @return {@link #vaccinationProtocol} (Contains information about the protocol(s) under which the vaccine was administered.)
     */
    public List<ImmunizationVaccinationProtocolComponent> getVaccinationProtocol() { 
      return this.vaccinationProtocol;
    }

    // syntactic sugar
    /**
     * @return {@link #vaccinationProtocol} (Contains information about the protocol(s) under which the vaccine was administered.)
     */
    public ImmunizationVaccinationProtocolComponent addVaccinationProtocol() { 
      ImmunizationVaccinationProtocolComponent t = new ImmunizationVaccinationProtocolComponent();
      this.vaccinationProtocol.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "A unique identifier assigned to this adverse reaction record.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("date", "dateTime", "Date vaccine administered or was to be administered.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("vaccineType", "CodeableConcept", "Vaccine that was administered or was to be administered.", 0, java.lang.Integer.MAX_VALUE, vaccineType));
        childrenList.add(new Property("subject", "Resource(Patient)", "The patient to whom the vaccine was to be administered.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("refusedIndicator", "boolean", "Indicates if the vaccination was refused.", 0, java.lang.Integer.MAX_VALUE, refusedIndicator));
        childrenList.add(new Property("reported", "boolean", "True if this administration was reported rather than directly administered.", 0, java.lang.Integer.MAX_VALUE, reported));
        childrenList.add(new Property("performer", "Resource(Practitioner)", "Clinician who administered the vaccine.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("requester", "Resource(Practitioner)", "Clinician who ordered the vaccination.", 0, java.lang.Integer.MAX_VALUE, requester));
        childrenList.add(new Property("manufacturer", "Resource(Organization)", "Name of vaccine manufacturer.", 0, java.lang.Integer.MAX_VALUE, manufacturer));
        childrenList.add(new Property("location", "Resource(Location)", "The service delivery location where the vaccine administration occurred.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("lotNumber", "string", "Lot number of the  vaccine product.", 0, java.lang.Integer.MAX_VALUE, lotNumber));
        childrenList.add(new Property("expirationDate", "date", "Date vaccine batch expires.", 0, java.lang.Integer.MAX_VALUE, expirationDate));
        childrenList.add(new Property("site", "CodeableConcept", "Body site where vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, site));
        childrenList.add(new Property("route", "CodeableConcept", "The path by which the vaccine product is taken into the body.", 0, java.lang.Integer.MAX_VALUE, route));
        childrenList.add(new Property("doseQuantity", "Quantity", "The quantity of vaccine product that was administered.", 0, java.lang.Integer.MAX_VALUE, doseQuantity));
        childrenList.add(new Property("explanation", "", "Reasons why a vaccine was administered or refused.", 0, java.lang.Integer.MAX_VALUE, explanation));
        childrenList.add(new Property("reaction", "", "Categorical data indicating that an adverse event is associated in time to an immunization.", 0, java.lang.Integer.MAX_VALUE, reaction));
        childrenList.add(new Property("vaccinationProtocol", "", "Contains information about the protocol(s) under which the vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, vaccinationProtocol));
      }

      public Immunization copy() {
        Immunization dst = new Immunization();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.date = date == null ? null : date.copy();
        dst.vaccineType = vaccineType == null ? null : vaccineType.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.refusedIndicator = refusedIndicator == null ? null : refusedIndicator.copy();
        dst.reported = reported == null ? null : reported.copy();
        dst.performer = performer == null ? null : performer.copy();
        dst.requester = requester == null ? null : requester.copy();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.location = location == null ? null : location.copy();
        dst.lotNumber = lotNumber == null ? null : lotNumber.copy();
        dst.expirationDate = expirationDate == null ? null : expirationDate.copy();
        dst.site = site == null ? null : site.copy();
        dst.route = route == null ? null : route.copy();
        dst.doseQuantity = doseQuantity == null ? null : doseQuantity.copy();
        dst.explanation = explanation == null ? null : explanation.copy(dst);
        dst.reaction = new ArrayList<ImmunizationReactionComponent>();
        for (ImmunizationReactionComponent i : reaction)
          dst.reaction.add(i.copy(dst));
        dst.vaccinationProtocol = new ArrayList<ImmunizationVaccinationProtocolComponent>();
        for (ImmunizationVaccinationProtocolComponent i : vaccinationProtocol)
          dst.vaccinationProtocol.add(i.copy(dst));
        return dst;
      }

      protected Immunization typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Immunization;
   }


}

