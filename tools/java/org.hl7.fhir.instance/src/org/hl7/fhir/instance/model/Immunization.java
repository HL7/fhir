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

// Generated on Wed, May 15, 2013 09:11+1000 for FHIR v0.09

import java.util.*;

/**
 * An administered immunization
 */
public class Immunization extends Resource {

    public class ImmunizationRefusalComponent extends Element {
        /**
         * Refusal or exemption reason
         */
        private Code reason;

        public Code getReason() { 
          return this.reason;
        }

        public void setReason(Code value) { 
          this.reason = value;
        }

        public String getReasonSimple() { 
          return this.reason == null ? null : this.reason.getValue();
        }

        public void setReasonSimple(String value) { 
            if (this.reason == null)
              this.reason = new Code();
            this.reason.setValue(value);
        }

  }

    public class ImmunizationReactionComponent extends Element {
        /**
         * Date of reaction to the immunization
         */
        private DateTime date;

        /**
         * Details of the reaction
         */
        private ResourceReference detail;

        /**
         * Self-reported indicator
         */
        private Boolean reported;

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

  }

    public class ImmunizationVaccinationProtocolComponent extends Element {
        /**
         * Nominal position in a series
         */
        private Integer doseSequence;

        /**
         * Vaccine Administration Protocol Description
         */
        private String_ description;

        /**
         * Who published the protocol?  E.g. ACIP
         */
        private ResourceReference authority;

        /**
         * One possible path to achieve presumed immunity against a disease - within the context of an authority
         */
        private String_ series;

        /**
         * The recommended number of doses to achieve immunity.
         */
        private Integer seriesDoses;

        /**
         * The targeted disease
         */
        private Code doseTarget;

        /**
         * Should this count??
         */
        private Code doseStatus;

        /**
         * Dose Status Reason
         */
        private Code doseStatusReason;

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

        public Code getDoseTarget() { 
          return this.doseTarget;
        }

        public void setDoseTarget(Code value) { 
          this.doseTarget = value;
        }

        public String getDoseTargetSimple() { 
          return this.doseTarget == null ? null : this.doseTarget.getValue();
        }

        public void setDoseTargetSimple(String value) { 
          if (value == null)
            this.doseTarget = null;
          else {
            if (this.doseTarget == null)
              this.doseTarget = new Code();
            this.doseTarget.setValue(value);
          }
        }

        public Code getDoseStatus() { 
          return this.doseStatus;
        }

        public void setDoseStatus(Code value) { 
          this.doseStatus = value;
        }

        public String getDoseStatusSimple() { 
          return this.doseStatus == null ? null : this.doseStatus.getValue();
        }

        public void setDoseStatusSimple(String value) { 
            if (this.doseStatus == null)
              this.doseStatus = new Code();
            this.doseStatus.setValue(value);
        }

        public Code getDoseStatusReason() { 
          return this.doseStatusReason;
        }

        public void setDoseStatusReason(Code value) { 
          this.doseStatusReason = value;
        }

        public String getDoseStatusReasonSimple() { 
          return this.doseStatusReason == null ? null : this.doseStatusReason.getValue();
        }

        public void setDoseStatusReasonSimple(String value) { 
          if (value == null)
            this.doseStatusReason = null;
          else {
            if (this.doseStatusReason == null)
              this.doseStatusReason = new Code();
            this.doseStatusReason.setValue(value);
          }
        }

  }

    /**
     * Who this immunization was adminstered to
     */
    private ResourceReference subject;

    /**
     * Clinician who ordered the vaccine
     */
    private ResourceReference requester;

    /**
     * Clinician that administered the vaccine
     */
    private ResourceReference performer;

    /**
     * Name of manufacturer
     */
    private ResourceReference manufacturer;

    /**
     * The service delivery location where the administration occurred.
     */
    private ResourceReference location;

    /**
     * Date vaccine administered
     */
    private DateTime date;

    /**
     * True if this administration was reported rather than observed
     */
    private Boolean reported;

    /**
     * Vaccine administered
     */
    private Code vaccineType;

    /**
     * Lot number for vaccine
     */
    private String_ lotNumber;

    /**
     * Date vaccine batch expires
     */
    private Date expirationDate;

    /**
     * Body site where vaccine was administered
     */
    private Code site;

    /**
     * Route of administration
     */
    private Code route;

    /**
     * Vaccine dosage
     */
    private Quantity doseQuantity;

    /**
     * Exemption(s)/ Parent Refusal(s) of Vaccine Product Type Administered
     */
    private ImmunizationRefusalComponent refusal;

    /**
     * Categorical data indicating that an adverse event is associated in time to an immunization
     */
    private List<ImmunizationReactionComponent> reaction = new ArrayList<ImmunizationReactionComponent>();

    /**
     * Vaccine Administration Protocol
     */
    private ImmunizationVaccinationProtocolComponent vaccinationProtocol;

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

    public Code getVaccineType() { 
      return this.vaccineType;
    }

    public void setVaccineType(Code value) { 
      this.vaccineType = value;
    }

    public String getVaccineTypeSimple() { 
      return this.vaccineType == null ? null : this.vaccineType.getValue();
    }

    public void setVaccineTypeSimple(String value) { 
      if (value == null)
        this.vaccineType = null;
      else {
        if (this.vaccineType == null)
          this.vaccineType = new Code();
        this.vaccineType.setValue(value);
      }
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

    public Code getSite() { 
      return this.site;
    }

    public void setSite(Code value) { 
      this.site = value;
    }

    public String getSiteSimple() { 
      return this.site == null ? null : this.site.getValue();
    }

    public void setSiteSimple(String value) { 
      if (value == null)
        this.site = null;
      else {
        if (this.site == null)
          this.site = new Code();
        this.site.setValue(value);
      }
    }

    public Code getRoute() { 
      return this.route;
    }

    public void setRoute(Code value) { 
      this.route = value;
    }

    public String getRouteSimple() { 
      return this.route == null ? null : this.route.getValue();
    }

    public void setRouteSimple(String value) { 
      if (value == null)
        this.route = null;
      else {
        if (this.route == null)
          this.route = new Code();
        this.route.setValue(value);
      }
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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Immunization;
   }


}

