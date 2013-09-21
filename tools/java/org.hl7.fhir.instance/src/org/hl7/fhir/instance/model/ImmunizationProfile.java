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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

import java.util.*;

/**
 * A patient's point-of-time immunization status and recommendation with optional supporting justification.
 */
public class ImmunizationProfile extends Resource {

    public enum ImmunizationForecastStatus {
        dUE, // This immunization is due to be given now.
        Null; // added to help the parsers
        public static ImmunizationForecastStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("DUE".equals(codeString))
          return dUE;
        throw new Exception("Unknown ImmunizationForecastStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case dUE: return "DUE";
            default: return "?";
          }
        }
    }

  public class ImmunizationForecastStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("DUE".equals(codeString))
          return ImmunizationForecastStatus.dUE;
        throw new Exception("Unknown ImmunizationForecastStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ImmunizationForecastStatus.dUE)
        return "DUE";
      return "?";
      }
    }

    public class ImmunizationProfileRecommendationComponent extends Element {
        /**
         * The date of the immunization recommendation.
         */
        protected DateTime recommendationDate;

        /**
         * Vaccine that pertains to the recommendation.
         */
        protected CodeableConcept vaccineType;

        /**
         * Recommended dose number.
         */
        protected Integer doseNumber;

        /**
         * Vaccine administration status.
         */
        protected Enumeration<ImmunizationForecastStatus> forecastStatus;

        /**
         * Vaccine date recommentations - e.g. earliest date to administer, latest date to administer, etc.
         */
        protected List<ImmunizationProfileRecommendationDateCriterionComponent> dateCriterion = new ArrayList<ImmunizationProfileRecommendationDateCriterionComponent>();

        /**
         * Contains information about the protocol under which the vaccine was administered.
         */
        protected ImmunizationProfileRecommendationProtocolComponent protocol;

        /**
         * Immunization event history that supports the status and recommendation.
         */
        protected List<ResourceReference> supportingImmunization = new ArrayList<ResourceReference>();

        /**
         * Adverse event report information that supports the status and recommendation.
         */
        protected List<ImmunizationProfileRecommendationSupportingAdverseEventReportComponent> supportingAdverseEventReport = new ArrayList<ImmunizationProfileRecommendationSupportingAdverseEventReportComponent>();

        /**
         * Patient observation that supports the status and recommendation.
         */
        protected List<ResourceReference> supportingPatientObservation = new ArrayList<ResourceReference>();

        public DateTime getRecommendationDate() { 
          return this.recommendationDate;
        }

        public void setRecommendationDate(DateTime value) { 
          this.recommendationDate = value;
        }

        public String getRecommendationDateSimple() { 
          return this.recommendationDate == null ? null : this.recommendationDate.getValue();
        }

        public void setRecommendationDateSimple(String value) { 
            if (this.recommendationDate == null)
              this.recommendationDate = new DateTime();
            this.recommendationDate.setValue(value);
        }

        public CodeableConcept getVaccineType() { 
          return this.vaccineType;
        }

        public void setVaccineType(CodeableConcept value) { 
          this.vaccineType = value;
        }

        public Integer getDoseNumber() { 
          return this.doseNumber;
        }

        public void setDoseNumber(Integer value) { 
          this.doseNumber = value;
        }

        public int getDoseNumberSimple() { 
          return this.doseNumber == null ? null : this.doseNumber.getValue();
        }

        public void setDoseNumberSimple(int value) { 
          if (value == -1)
            this.doseNumber = null;
          else {
            if (this.doseNumber == null)
              this.doseNumber = new Integer();
            this.doseNumber.setValue(value);
          }
        }

        public Enumeration<ImmunizationForecastStatus> getForecastStatus() { 
          return this.forecastStatus;
        }

        public void setForecastStatus(Enumeration<ImmunizationForecastStatus> value) { 
          this.forecastStatus = value;
        }

        public ImmunizationForecastStatus getForecastStatusSimple() { 
          return this.forecastStatus == null ? null : this.forecastStatus.getValue();
        }

        public void setForecastStatusSimple(ImmunizationForecastStatus value) { 
            if (this.forecastStatus == null)
              this.forecastStatus = new Enumeration<ImmunizationForecastStatus>();
            this.forecastStatus.setValue(value);
        }

        public List<ImmunizationProfileRecommendationDateCriterionComponent> getDateCriterion() { 
          return this.dateCriterion;
        }

    // syntactic sugar
        public ImmunizationProfileRecommendationDateCriterionComponent addDateCriterion() { 
          ImmunizationProfileRecommendationDateCriterionComponent t = new ImmunizationProfileRecommendationDateCriterionComponent();
          this.dateCriterion.add(t);
          return t;
        }

        public ImmunizationProfileRecommendationProtocolComponent getProtocol() { 
          return this.protocol;
        }

        public void setProtocol(ImmunizationProfileRecommendationProtocolComponent value) { 
          this.protocol = value;
        }

        public List<ResourceReference> getSupportingImmunization() { 
          return this.supportingImmunization;
        }

    // syntactic sugar
        public ResourceReference addSupportingImmunization() { 
          ResourceReference t = new ResourceReference();
          this.supportingImmunization.add(t);
          return t;
        }

        public List<ImmunizationProfileRecommendationSupportingAdverseEventReportComponent> getSupportingAdverseEventReport() { 
          return this.supportingAdverseEventReport;
        }

    // syntactic sugar
        public ImmunizationProfileRecommendationSupportingAdverseEventReportComponent addSupportingAdverseEventReport() { 
          ImmunizationProfileRecommendationSupportingAdverseEventReportComponent t = new ImmunizationProfileRecommendationSupportingAdverseEventReportComponent();
          this.supportingAdverseEventReport.add(t);
          return t;
        }

        public List<ResourceReference> getSupportingPatientObservation() { 
          return this.supportingPatientObservation;
        }

    // syntactic sugar
        public ResourceReference addSupportingPatientObservation() { 
          ResourceReference t = new ResourceReference();
          this.supportingPatientObservation.add(t);
          return t;
        }

      public ImmunizationProfileRecommendationComponent copy(ImmunizationProfile e) {
        ImmunizationProfileRecommendationComponent dst = e.new ImmunizationProfileRecommendationComponent();
        dst.recommendationDate = recommendationDate == null ? null : recommendationDate.copy();
        dst.vaccineType = vaccineType == null ? null : vaccineType.copy();
        dst.doseNumber = doseNumber == null ? null : doseNumber.copy();
        dst.forecastStatus = forecastStatus == null ? null : forecastStatus.copy();
        dst.dateCriterion = new ArrayList<ImmunizationProfileRecommendationDateCriterionComponent>();
        for (ImmunizationProfileRecommendationDateCriterionComponent i : dateCriterion)
          dst.dateCriterion.add(i.copy(e));
        dst.protocol = protocol == null ? null : protocol.copy(e);
        dst.supportingImmunization = new ArrayList<ResourceReference>();
        for (ResourceReference i : supportingImmunization)
          dst.supportingImmunization.add(i.copy());
        dst.supportingAdverseEventReport = new ArrayList<ImmunizationProfileRecommendationSupportingAdverseEventReportComponent>();
        for (ImmunizationProfileRecommendationSupportingAdverseEventReportComponent i : supportingAdverseEventReport)
          dst.supportingAdverseEventReport.add(i.copy(e));
        dst.supportingPatientObservation = new ArrayList<ResourceReference>();
        for (ResourceReference i : supportingPatientObservation)
          dst.supportingPatientObservation.add(i.copy());
        return dst;
      }

  }

    public class ImmunizationProfileRecommendationDateCriterionComponent extends Element {
        /**
         * Date classification of recommendation - e.g. earliest date to give, latest date to give, etc.
         */
        protected CodeableConcept code;

        /**
         * Date recommendation.
         */
        protected DateTime value;

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public DateTime getValue() { 
          return this.value;
        }

        public void setValue(DateTime value) { 
          this.value = value;
        }

        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        public void setValueSimple(String value) { 
            if (this.value == null)
              this.value = new DateTime();
            this.value.setValue(value);
        }

      public ImmunizationProfileRecommendationDateCriterionComponent copy(ImmunizationProfile e) {
        ImmunizationProfileRecommendationDateCriterionComponent dst = e.new ImmunizationProfileRecommendationDateCriterionComponent();
        dst.code = code == null ? null : code.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    public class ImmunizationProfileRecommendationProtocolComponent extends Element {
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
          if (value == -1)
            this.doseSequence = null;
          else {
            if (this.doseSequence == null)
              this.doseSequence = new Integer();
            this.doseSequence.setValue(value);
          }
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

      public ImmunizationProfileRecommendationProtocolComponent copy(ImmunizationProfile e) {
        ImmunizationProfileRecommendationProtocolComponent dst = e.new ImmunizationProfileRecommendationProtocolComponent();
        dst.doseSequence = doseSequence == null ? null : doseSequence.copy();
        dst.description = description == null ? null : description.copy();
        dst.authority = authority == null ? null : authority.copy();
        dst.series = series == null ? null : series.copy();
        return dst;
      }

  }

    public class ImmunizationProfileRecommendationSupportingAdverseEventReportComponent extends Element {
        /**
         * Unique identifier of the adverse event report.
         */
        protected List<Id> identifier = new ArrayList<Id>();

        /**
         * Advers event report classification.
         */
        protected CodeableConcept reportType;

        /**
         * The date of the adverse event report.
         */
        protected DateTime reportDate;

        /**
         * The content of the adverse event report.
         */
        protected String_ text;

        /**
         * The documented reaction described in the adverse event report.
         */
        protected List<ResourceReference> reaction = new ArrayList<ResourceReference>();

        public List<Id> getIdentifier() { 
          return this.identifier;
        }

    // syntactic sugar
        public Id addIdentifier() { 
          Id t = new Id();
          this.identifier.add(t);
          return t;
        }

        public Id addIdentifierSimple(String value) { 
          Id t = new Id();
          t.setValue(value);
          this.identifier.add(t);
          return t;
        }

        public CodeableConcept getReportType() { 
          return this.reportType;
        }

        public void setReportType(CodeableConcept value) { 
          this.reportType = value;
        }

        public DateTime getReportDate() { 
          return this.reportDate;
        }

        public void setReportDate(DateTime value) { 
          this.reportDate = value;
        }

        public String getReportDateSimple() { 
          return this.reportDate == null ? null : this.reportDate.getValue();
        }

        public void setReportDateSimple(String value) { 
          if (value == null)
            this.reportDate = null;
          else {
            if (this.reportDate == null)
              this.reportDate = new DateTime();
            this.reportDate.setValue(value);
          }
        }

        public String_ getText() { 
          return this.text;
        }

        public void setText(String_ value) { 
          this.text = value;
        }

        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        public void setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
        }

        public List<ResourceReference> getReaction() { 
          return this.reaction;
        }

    // syntactic sugar
        public ResourceReference addReaction() { 
          ResourceReference t = new ResourceReference();
          this.reaction.add(t);
          return t;
        }

      public ImmunizationProfileRecommendationSupportingAdverseEventReportComponent copy(ImmunizationProfile e) {
        ImmunizationProfileRecommendationSupportingAdverseEventReportComponent dst = e.new ImmunizationProfileRecommendationSupportingAdverseEventReportComponent();
        dst.identifier = new ArrayList<Id>();
        for (Id i : identifier)
          dst.identifier.add(i.copy());
        dst.reportType = reportType == null ? null : reportType.copy();
        dst.reportDate = reportDate == null ? null : reportDate.copy();
        dst.text = text == null ? null : text.copy();
        dst.reaction = new ArrayList<ResourceReference>();
        for (ResourceReference i : reaction)
          dst.reaction.add(i.copy());
        return dst;
      }

  }

    /**
     * The patient who is the subject of the profile.
     */
    protected ResourceReference subject;

    /**
     * Vaccine administration recommendations.
     */
    protected List<ImmunizationProfileRecommendationComponent> recommendation = new ArrayList<ImmunizationProfileRecommendationComponent>();

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public List<ImmunizationProfileRecommendationComponent> getRecommendation() { 
      return this.recommendation;
    }

    // syntactic sugar
    public ImmunizationProfileRecommendationComponent addRecommendation() { 
      ImmunizationProfileRecommendationComponent t = new ImmunizationProfileRecommendationComponent();
      this.recommendation.add(t);
      return t;
    }

      public ImmunizationProfile copy() {
        ImmunizationProfile dst = new ImmunizationProfile();
        dst.subject = subject == null ? null : subject.copy();
        dst.recommendation = new ArrayList<ImmunizationProfileRecommendationComponent>();
        for (ImmunizationProfileRecommendationComponent i : recommendation)
          dst.recommendation.add(i.copy(dst));
        return dst;
      }

      protected ImmunizationProfile typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ImmunizationProfile;
   }


}

