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

// Generated on Mon, Jun 10, 2013 20:06+1000 for FHIR v0.09

import java.util.*;

/**
 * An administered immunization
 */
public class Immunization extends Resource {

    public class ImmunizationRefusalComponent extends Element {
        /**
         * Refusal or exemption reason
         */
        protected CodeableConcept reason;

        public CodeableConcept getReason() { 
          return this.reason;
        }

        public void setReason(CodeableConcept value) { 
          this.reason = value;
        }

      public ImmunizationRefusalComponent copy(Immunization e) {
        ImmunizationRefusalComponent dst = e.new ImmunizationRefusalComponent();
        dst.reason = reason == null ? null : reason.copy();
        return dst;
      }

  }

    public class ImmunizationReactionComponent extends Element {
        /**
         * Date of reaction to the immunization
         */
        protected DateTime date;

        /**
         * Details of the reaction
         */
        protected ResourceReference detail;

        /**
         * Self-reported indicator
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
         * Nominal position in a series
         */
        protected Integer doseSequence;

        /**
         * Vaccine Administration Protocol Description
         */
        protected String_ description;

        /**
         * Who published the protocol?  E.g. ACIP
         */
        protected ResourceReference authority;

        /**
         * One possible path to achieve presumed immunity against a disease - within the context of an authority
         */
        protected String_ series;

        /**
         * The recommended number of doses to achieve immunity.
         */
        protected Integer seriesDoses;

        /**
         * The targeted disease
         */
        protected CodeableConcept doseTarget;

        /**
         * Should this count??
         */
        protected CodeableConcept doseStatus;

        /**
         * Dose Status Reason
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
     * Who this immunization was adminstered to
     */
    protected ResourceReference subject;

    /**
     * Clinician who ordered the vaccine
     */
    protected ResourceReference requester;

    /**
     * Clinician that administered the vaccine
     */
    protected ResourceReference performer;

    /**
     * Name of manufacturer
     */
    protected ResourceReference manufacturer;

    /**
     * The service delivery location where the administration occurred.
     */
    protected ResourceReference location;

    /**
     * Date vaccine administered
     */
    protected DateTime date;

    /**
     * True if this administration was reported rather than observed
     */
    protected Boolean reported;

    /**
     * Vaccine administered
     */
    protected CodeableConcept vaccineType;

    /**
     * Lot number for vaccine
     */
    protected String_ lotNumber;

    /**
     * Date vaccine batch expires
     */
    protected Date expirationDate;

    /**
     * Body site where vaccine was administered
     */
    protected CodeableConcept site;

    /**
     * Route of administration
     */
    protected CodeableConcept route;

    /**
     * Vaccine dosage
     */
    protected Quantity doseQuantity;

    /**
     * Exemption(s)/ Parent Refusal(s) of Vaccine Product Type Administered
     */
    protected ImmunizationRefusalComponent refusal;

    /**
     * Categorical data indicating that an adverse event is associated in time to an immunization
     */
    protected List<ImmunizationReactionComponent> reaction = new ArrayList<ImmunizationReactionComponent>();

    /**
     * Vaccine Administration Protocol
     */
    protected ImmunizationVaccinationProtocolComponent vaccinationProtocol;

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getRequester() { 
      return this.requester;
    }

    public void setRequester(ResourceReference value) { 
      this.requester = value;
    }

    public ResourceReference getPerformer() { 
      return this.performer;
    }

    public void setPerformer(ResourceReference value) { 
      this.performer = value;
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

    public CodeableConcept getVaccineType() { 
      return this.vaccineType;
    }

    public void setVaccineType(CodeableConcept value) { 
      this.vaccineType = value;
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

    public ImmunizationRefusalComponent getRefusal() { 
      return this.refusal;
    }

    public void setRefusal(ImmunizationRefusalComponent value) { 
      this.refusal = value;
    }

    public List<ImmunizationReactionComponent> getReaction() { 
      return this.reaction;
    }

    public ImmunizationVaccinationProtocolComponent getVaccinationProtocol() { 
      return this.vaccinationProtocol;
    }

    public void setVaccinationProtocol(ImmunizationVaccinationProtocolComponent value) { 
      this.vaccinationProtocol = value;
    }

      public Immunization copy() {
        Immunization dst = new Immunization();
        dst.subject = subject == null ? null : subject.copy();
        dst.requester = requester == null ? null : requester.copy();
        dst.performer = performer == null ? null : performer.copy();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.location = location == null ? null : location.copy();
        dst.date = date == null ? null : date.copy();
        dst.reported = reported == null ? null : reported.copy();
        dst.vaccineType = vaccineType == null ? null : vaccineType.copy();
        dst.lotNumber = lotNumber == null ? null : lotNumber.copy();
        dst.expirationDate = expirationDate == null ? null : expirationDate.copy();
        dst.site = site == null ? null : site.copy();
        dst.route = route == null ? null : route.copy();
        dst.doseQuantity = doseQuantity == null ? null : doseQuantity.copy();
        dst.refusal = refusal == null ? null : refusal.copy(dst);
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

