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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

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

        public List<CodeableConcept> getReason() { 
          return this.reason;
        }

    // syntactic sugar
        public CodeableConcept addReason() { 
          CodeableConcept t = new CodeableConcept();
          this.reason.add(t);
          return t;
        }

        public List<CodeableConcept> getRefusalReason() { 
          return this.refusalReason;
        }

    // syntactic sugar
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

        public DateTime getDate() { 
          return this.date;
        }

        public ImmunizationReactionComponent setDate(DateTime value) { 
          this.date = value;
          return this;
        }

        public String getDateSimple() { 
          return this.date == null ? null : this.date.getValue();
        }

        public ImmunizationReactionComponent setDateSimple(String value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateTime();
            this.date.setValue(value);
          }
          return this;
        }

        public ResourceReference getDetail() { 
          return this.detail;
        }

        public ImmunizationReactionComponent setDetail(ResourceReference value) { 
          this.detail = value;
          return this;
        }

        public Boolean getReported() { 
          return this.reported;
        }

        public ImmunizationReactionComponent setReported(Boolean value) { 
          this.reported = value;
          return this;
        }

        public boolean getReportedSimple() { 
          return this.reported == null ? null : this.reported.getValue();
        }

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

      public ImmunizationVaccinationProtocolComponent(Integer doseSequence, CodeableConcept doseStatus) {
        super();
        this.doseSequence = doseSequence;
        this.doseStatus = doseStatus;
      }

        public Integer getDoseSequence() { 
          return this.doseSequence;
        }

        public ImmunizationVaccinationProtocolComponent setDoseSequence(Integer value) { 
          this.doseSequence = value;
          return this;
        }

        public int getDoseSequenceSimple() { 
          return this.doseSequence == null ? null : this.doseSequence.getValue();
        }

        public ImmunizationVaccinationProtocolComponent setDoseSequenceSimple(int value) { 
            if (this.doseSequence == null)
              this.doseSequence = new Integer();
            this.doseSequence.setValue(value);
          return this;
        }

        public String_ getDescription() { 
          return this.description;
        }

        public ImmunizationVaccinationProtocolComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

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

        public ResourceReference getAuthority() { 
          return this.authority;
        }

        public ImmunizationVaccinationProtocolComponent setAuthority(ResourceReference value) { 
          this.authority = value;
          return this;
        }

        public String_ getSeries() { 
          return this.series;
        }

        public ImmunizationVaccinationProtocolComponent setSeries(String_ value) { 
          this.series = value;
          return this;
        }

        public String getSeriesSimple() { 
          return this.series == null ? null : this.series.getValue();
        }

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

        public Integer getSeriesDoses() { 
          return this.seriesDoses;
        }

        public ImmunizationVaccinationProtocolComponent setSeriesDoses(Integer value) { 
          this.seriesDoses = value;
          return this;
        }

        public int getSeriesDosesSimple() { 
          return this.seriesDoses == null ? null : this.seriesDoses.getValue();
        }

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

        public CodeableConcept getDoseTarget() { 
          return this.doseTarget;
        }

        public ImmunizationVaccinationProtocolComponent setDoseTarget(CodeableConcept value) { 
          this.doseTarget = value;
          return this;
        }

        public CodeableConcept getDoseStatus() { 
          return this.doseStatus;
        }

        public ImmunizationVaccinationProtocolComponent setDoseStatus(CodeableConcept value) { 
          this.doseStatus = value;
          return this;
        }

        public CodeableConcept getDoseStatusReason() { 
          return this.doseStatusReason;
        }

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
     * Contains information about the protocol under which the vaccine was administered.
     */
    protected ImmunizationVaccinationProtocolComponent vaccinationProtocol;

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

    public DateTime getDate() { 
      return this.date;
    }

    public Immunization setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    public String getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    public Immunization setDateSimple(String value) { 
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      return this;
    }

    public CodeableConcept getVaccineType() { 
      return this.vaccineType;
    }

    public Immunization setVaccineType(CodeableConcept value) { 
      this.vaccineType = value;
      return this;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public Immunization setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    public Boolean getRefusedIndicator() { 
      return this.refusedIndicator;
    }

    public Immunization setRefusedIndicator(Boolean value) { 
      this.refusedIndicator = value;
      return this;
    }

    public boolean getRefusedIndicatorSimple() { 
      return this.refusedIndicator == null ? null : this.refusedIndicator.getValue();
    }

    public Immunization setRefusedIndicatorSimple(boolean value) { 
        if (this.refusedIndicator == null)
          this.refusedIndicator = new Boolean();
        this.refusedIndicator.setValue(value);
      return this;
    }

    public Boolean getReported() { 
      return this.reported;
    }

    public Immunization setReported(Boolean value) { 
      this.reported = value;
      return this;
    }

    public boolean getReportedSimple() { 
      return this.reported == null ? null : this.reported.getValue();
    }

    public Immunization setReportedSimple(boolean value) { 
        if (this.reported == null)
          this.reported = new Boolean();
        this.reported.setValue(value);
      return this;
    }

    public ResourceReference getPerformer() { 
      return this.performer;
    }

    public Immunization setPerformer(ResourceReference value) { 
      this.performer = value;
      return this;
    }

    public ResourceReference getRequester() { 
      return this.requester;
    }

    public Immunization setRequester(ResourceReference value) { 
      this.requester = value;
      return this;
    }

    public ResourceReference getManufacturer() { 
      return this.manufacturer;
    }

    public Immunization setManufacturer(ResourceReference value) { 
      this.manufacturer = value;
      return this;
    }

    public ResourceReference getLocation() { 
      return this.location;
    }

    public Immunization setLocation(ResourceReference value) { 
      this.location = value;
      return this;
    }

    public String_ getLotNumber() { 
      return this.lotNumber;
    }

    public Immunization setLotNumber(String_ value) { 
      this.lotNumber = value;
      return this;
    }

    public String getLotNumberSimple() { 
      return this.lotNumber == null ? null : this.lotNumber.getValue();
    }

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

    public Date getExpirationDate() { 
      return this.expirationDate;
    }

    public Immunization setExpirationDate(Date value) { 
      this.expirationDate = value;
      return this;
    }

    public String getExpirationDateSimple() { 
      return this.expirationDate == null ? null : this.expirationDate.getValue();
    }

    public Immunization setExpirationDateSimple(String value) { 
      if (value == null)
        this.expirationDate = null;
      else {
        if (this.expirationDate == null)
          this.expirationDate = new Date();
        this.expirationDate.setValue(value);
      }
      return this;
    }

    public CodeableConcept getSite() { 
      return this.site;
    }

    public Immunization setSite(CodeableConcept value) { 
      this.site = value;
      return this;
    }

    public CodeableConcept getRoute() { 
      return this.route;
    }

    public Immunization setRoute(CodeableConcept value) { 
      this.route = value;
      return this;
    }

    public Quantity getDoseQuantity() { 
      return this.doseQuantity;
    }

    public Immunization setDoseQuantity(Quantity value) { 
      this.doseQuantity = value;
      return this;
    }

    public ImmunizationExplanationComponent getExplanation() { 
      return this.explanation;
    }

    public Immunization setExplanation(ImmunizationExplanationComponent value) { 
      this.explanation = value;
      return this;
    }

    public List<ImmunizationReactionComponent> getReaction() { 
      return this.reaction;
    }

    // syntactic sugar
    public ImmunizationReactionComponent addReaction() { 
      ImmunizationReactionComponent t = new ImmunizationReactionComponent();
      this.reaction.add(t);
      return t;
    }

    public ImmunizationVaccinationProtocolComponent getVaccinationProtocol() { 
      return this.vaccinationProtocol;
    }

    public Immunization setVaccinationProtocol(ImmunizationVaccinationProtocolComponent value) { 
      this.vaccinationProtocol = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
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
        childrenList.add(new Property("vaccinationProtocol", "", "Contains information about the protocol under which the vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, vaccinationProtocol));
      }

      public Immunization copy() {
        Immunization dst = new Immunization();
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
        dst.vaccinationProtocol = vaccinationProtocol == null ? null : vaccinationProtocol.copy(dst);
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

