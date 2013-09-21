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

// Generated on Sun, Sep 22, 2013 06:57+1000 for FHIR v0.11

import java.util.*;

/**
 * Immunization event information.
 */
public class Immunization extends Resource {

    public class ImmunizationExplanationComponent extends Element {
        /**
         * Reasons why a vaccine was administered.
         */
        protected List<CodeableConcept> reason = new ArrayList<CodeableConcept>();

        /**
         * Refusal or exemption reasons.
         */
        protected List<CodeableConcept> refusalReason = new ArrayList<CodeableConcept>();

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

      public ImmunizationExplanationComponent copy(Immunization e) {
        ImmunizationExplanationComponent dst = e.new ImmunizationExplanationComponent();
        dst.reason = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : reason)
          dst.reason.add(i.copy());
        dst.refusalReason = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : refusalReason)
          dst.refusalReason.add(i.copy());
        return dst;
      }

  }

    public class ImmunizationReactionComponent extends Element {
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

        public DateTime getDate() { 
          return this.date;
        }

        public void setDate(DateTime value) { 
          this.date = value;
        }

        public String getDateSimple() { 
          return this.date == null ? null : this.date.getValue();
        }

        public void setDateSimple(String value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateTime();
            this.date.setValue(value);
          }
        }

        public ResourceReference getDetail() { 
          return this.detail;
        }

        public void setDetail(ResourceReference value) { 
          this.detail = value;
        }

        public Boolean getReported() { 
          return this.reported;
        }

        public void setReported(Boolean value) { 
          this.reported = value;
        }

        public boolean getReportedSimple() { 
          return this.reported == null ? null : this.reported.getValue();
        }

        public void setReportedSimple(boolean value) { 
          if (value == false)
            this.reported = null;
          else {
            if (this.reported == null)
              this.reported = new Boolean();
            this.reported.setValue(value);
          }
        }

      public ImmunizationReactionComponent copy(Immunization e) {
        ImmunizationReactionComponent dst = e.new ImmunizationReactionComponent();
        dst.date = date == null ? null : date.copy();
        dst.detail = detail == null ? null : detail.copy();
        dst.reported = reported == null ? null : reported.copy();
        return dst;
      }

  }

    public class ImmunizationVaccinationProtocolComponent extends Element {
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

        public Integer getDoseSequence() { 
          return this.doseSequence;
        }

        public void setDoseSequence(Integer value) { 
          this.doseSequence = value;
        }

        public int getDoseSequenceSimple() { 
          return this.doseSequence == null ? null : this.doseSequence.getValue();
        }

        public void setDoseSequenceSimple(int value) { 
            if (this.doseSequence == null)
              this.doseSequence = new Integer();
            this.doseSequence.setValue(value);
        }

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
        }

        public ResourceReference getAuthority() { 
          return this.authority;
        }

        public void setAuthority(ResourceReference value) { 
          this.authority = value;
        }

        public String_ getSeries() { 
          return this.series;
        }

        public void setSeries(String_ value) { 
          this.series = value;
        }

        public String getSeriesSimple() { 
          return this.series == null ? null : this.series.getValue();
        }

        public void setSeriesSimple(String value) { 
          if (value == null)
            this.series = null;
          else {
            if (this.series == null)
              this.series = new String_();
            this.series.setValue(value);
          }
        }

        public Integer getSeriesDoses() { 
          return this.seriesDoses;
        }

        public void setSeriesDoses(Integer value) { 
          this.seriesDoses = value;
        }

        public int getSeriesDosesSimple() { 
          return this.seriesDoses == null ? null : this.seriesDoses.getValue();
        }

        public void setSeriesDosesSimple(int value) { 
          if (value == -1)
            this.seriesDoses = null;
          else {
            if (this.seriesDoses == null)
              this.seriesDoses = new Integer();
            this.seriesDoses.setValue(value);
          }
        }

        public CodeableConcept getDoseTarget() { 
          return this.doseTarget;
        }

        public void setDoseTarget(CodeableConcept value) { 
          this.doseTarget = value;
        }

        public CodeableConcept getDoseStatus() { 
          return this.doseStatus;
        }

        public void setDoseStatus(CodeableConcept value) { 
          this.doseStatus = value;
        }

        public CodeableConcept getDoseStatusReason() { 
          return this.doseStatusReason;
        }

        public void setDoseStatusReason(CodeableConcept value) { 
          this.doseStatusReason = value;
        }

      public ImmunizationVaccinationProtocolComponent copy(Immunization e) {
        ImmunizationVaccinationProtocolComponent dst = e.new ImmunizationVaccinationProtocolComponent();
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
     * Clinician that administered the vaccine.
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

    public DateTime getDate() { 
      return this.date;
    }

    public void setDate(DateTime value) { 
      this.date = value;
    }

    public String getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    public void setDateSimple(String value) { 
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
    }

    public CodeableConcept getVaccineType() { 
      return this.vaccineType;
    }

    public void setVaccineType(CodeableConcept value) { 
      this.vaccineType = value;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public Boolean getRefusedIndicator() { 
      return this.refusedIndicator;
    }

    public void setRefusedIndicator(Boolean value) { 
      this.refusedIndicator = value;
    }

    public boolean getRefusedIndicatorSimple() { 
      return this.refusedIndicator == null ? null : this.refusedIndicator.getValue();
    }

    public void setRefusedIndicatorSimple(boolean value) { 
        if (this.refusedIndicator == null)
          this.refusedIndicator = new Boolean();
        this.refusedIndicator.setValue(value);
    }

    public Boolean getReported() { 
      return this.reported;
    }

    public void setReported(Boolean value) { 
      this.reported = value;
    }

    public boolean getReportedSimple() { 
      return this.reported == null ? null : this.reported.getValue();
    }

    public void setReportedSimple(boolean value) { 
        if (this.reported == null)
          this.reported = new Boolean();
        this.reported.setValue(value);
    }

    public ResourceReference getPerformer() { 
      return this.performer;
    }

    public void setPerformer(ResourceReference value) { 
      this.performer = value;
    }

    public ResourceReference getRequester() { 
      return this.requester;
    }

    public void setRequester(ResourceReference value) { 
      this.requester = value;
    }

    public ResourceReference getManufacturer() { 
      return this.manufacturer;
    }

    public void setManufacturer(ResourceReference value) { 
      this.manufacturer = value;
    }

    public ResourceReference getLocation() { 
      return this.location;
    }

    public void setLocation(ResourceReference value) { 
      this.location = value;
    }

    public String_ getLotNumber() { 
      return this.lotNumber;
    }

    public void setLotNumber(String_ value) { 
      this.lotNumber = value;
    }

    public String getLotNumberSimple() { 
      return this.lotNumber == null ? null : this.lotNumber.getValue();
    }

    public void setLotNumberSimple(String value) { 
      if (value == null)
        this.lotNumber = null;
      else {
        if (this.lotNumber == null)
          this.lotNumber = new String_();
        this.lotNumber.setValue(value);
      }
    }

    public Date getExpirationDate() { 
      return this.expirationDate;
    }

    public void setExpirationDate(Date value) { 
      this.expirationDate = value;
    }

    public String getExpirationDateSimple() { 
      return this.expirationDate == null ? null : this.expirationDate.getValue();
    }

    public void setExpirationDateSimple(String value) { 
      if (value == null)
        this.expirationDate = null;
      else {
        if (this.expirationDate == null)
          this.expirationDate = new Date();
        this.expirationDate.setValue(value);
      }
    }

    public CodeableConcept getSite() { 
      return this.site;
    }

    public void setSite(CodeableConcept value) { 
      this.site = value;
    }

    public CodeableConcept getRoute() { 
      return this.route;
    }

    public void setRoute(CodeableConcept value) { 
      this.route = value;
    }

    public Quantity getDoseQuantity() { 
      return this.doseQuantity;
    }

    public void setDoseQuantity(Quantity value) { 
      this.doseQuantity = value;
    }

    public ImmunizationExplanationComponent getExplanation() { 
      return this.explanation;
    }

    public void setExplanation(ImmunizationExplanationComponent value) { 
      this.explanation = value;
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

    public void setVaccinationProtocol(ImmunizationVaccinationProtocolComponent value) { 
      this.vaccinationProtocol = value;
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

