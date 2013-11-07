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

// Generated on Thu, Nov 7, 2013 14:52+1100 for FHIR v0.12

import java.util.*;

/**
 * A patient's point-of-time immunization status and recommendation with optional supporting justification.
 */
public class ImmunizationRecommendation extends Resource {

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

  public static class ImmunizationForecastStatusEnumFactory implements EnumFactory {
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

    public static class ImmunizationRecommendationRecommendationComponent extends BackboneElement {
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
         * Adverse event report information that supports the status and recommendation.
         */
        protected List<ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent> supportingAdverseEventReport = new ArrayList<ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent>();

        /**
         * Patient observation that supports the status and recommendation.
         */
        protected List<ResourceReference> supportingPatientObservation = new ArrayList<ResourceReference>();

      public ImmunizationRecommendationRecommendationComponent() {
        super();
      }

      public ImmunizationRecommendationRecommendationComponent(DateTime recommendationDate, CodeableConcept vaccineType, Enumeration<ImmunizationForecastStatus> forecastStatus) {
        super();
        this.recommendationDate = recommendationDate;
        this.vaccineType = vaccineType;
        this.forecastStatus = forecastStatus;
      }

        /**
         * @return {@link #recommendationDate} (The date of the immunization recommendation.)
         */
        public DateTime getRecommendationDate() { 
          return this.recommendationDate;
        }

        /**
         * @param value {@link #recommendationDate} (The date of the immunization recommendation.)
         */
        public ImmunizationRecommendationRecommendationComponent setRecommendationDate(DateTime value) { 
          this.recommendationDate = value;
          return this;
        }

        /**
         * @return The date of the immunization recommendation.
         */
        public String getRecommendationDateSimple() { 
          return this.recommendationDate == null ? null : this.recommendationDate.getValue();
        }

        /**
         * @param value The date of the immunization recommendation.
         */
        public ImmunizationRecommendationRecommendationComponent setRecommendationDateSimple(String value) { 
            if (this.recommendationDate == null)
              this.recommendationDate = new DateTime();
            this.recommendationDate.setValue(value);
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
         * @return {@link #doseNumber} (Recommended dose number.)
         */
        public Integer getDoseNumber() { 
          return this.doseNumber;
        }

        /**
         * @param value {@link #doseNumber} (Recommended dose number.)
         */
        public ImmunizationRecommendationRecommendationComponent setDoseNumber(Integer value) { 
          this.doseNumber = value;
          return this;
        }

        /**
         * @return Recommended dose number.
         */
        public int getDoseNumberSimple() { 
          return this.doseNumber == null ? null : this.doseNumber.getValue();
        }

        /**
         * @param value Recommended dose number.
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
        public Enumeration<ImmunizationForecastStatus> getForecastStatus() { 
          return this.forecastStatus;
        }

        /**
         * @param value {@link #forecastStatus} (Vaccine administration status.)
         */
        public ImmunizationRecommendationRecommendationComponent setForecastStatus(Enumeration<ImmunizationForecastStatus> value) { 
          this.forecastStatus = value;
          return this;
        }

        /**
         * @return Vaccine administration status.
         */
        public ImmunizationForecastStatus getForecastStatusSimple() { 
          return this.forecastStatus == null ? null : this.forecastStatus.getValue();
        }

        /**
         * @param value Vaccine administration status.
         */
        public ImmunizationRecommendationRecommendationComponent setForecastStatusSimple(ImmunizationForecastStatus value) { 
            if (this.forecastStatus == null)
              this.forecastStatus = new Enumeration<ImmunizationForecastStatus>();
            this.forecastStatus.setValue(value);
          return this;
        }

        /**
         * @return {@link #dateCriterion} (Vaccine date recommentations - e.g. earliest date to administer, latest date to administer, etc.)
         */
        public List<ImmunizationRecommendationRecommendationDateCriterionComponent> getDateCriterion() { 
          return this.dateCriterion;
        }

    // syntactic sugar
        /**
         * @return {@link #dateCriterion} (Vaccine date recommentations - e.g. earliest date to administer, latest date to administer, etc.)
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
         * @return {@link #supportingAdverseEventReport} (Adverse event report information that supports the status and recommendation.)
         */
        public List<ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent> getSupportingAdverseEventReport() { 
          return this.supportingAdverseEventReport;
        }

    // syntactic sugar
        /**
         * @return {@link #supportingAdverseEventReport} (Adverse event report information that supports the status and recommendation.)
         */
        public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent addSupportingAdverseEventReport() { 
          ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent t = new ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent();
          this.supportingAdverseEventReport.add(t);
          return t;
        }

        /**
         * @return {@link #supportingPatientObservation} (Patient observation that supports the status and recommendation.)
         */
        public List<ResourceReference> getSupportingPatientObservation() { 
          return this.supportingPatientObservation;
        }

    // syntactic sugar
        /**
         * @return {@link #supportingPatientObservation} (Patient observation that supports the status and recommendation.)
         */
        public ResourceReference addSupportingPatientObservation() { 
          ResourceReference t = new ResourceReference();
          this.supportingPatientObservation.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("recommendationDate", "dateTime", "The date of the immunization recommendation.", 0, java.lang.Integer.MAX_VALUE, recommendationDate));
          childrenList.add(new Property("vaccineType", "CodeableConcept", "Vaccine that pertains to the recommendation.", 0, java.lang.Integer.MAX_VALUE, vaccineType));
          childrenList.add(new Property("doseNumber", "integer", "Recommended dose number.", 0, java.lang.Integer.MAX_VALUE, doseNumber));
          childrenList.add(new Property("forecastStatus", "code", "Vaccine administration status.", 0, java.lang.Integer.MAX_VALUE, forecastStatus));
          childrenList.add(new Property("dateCriterion", "", "Vaccine date recommentations - e.g. earliest date to administer, latest date to administer, etc.", 0, java.lang.Integer.MAX_VALUE, dateCriterion));
          childrenList.add(new Property("protocol", "", "Contains information about the protocol under which the vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, protocol));
          childrenList.add(new Property("supportingImmunization", "Resource(Immunization)", "Immunization event history that supports the status and recommendation.", 0, java.lang.Integer.MAX_VALUE, supportingImmunization));
          childrenList.add(new Property("supportingAdverseEventReport", "", "Adverse event report information that supports the status and recommendation.", 0, java.lang.Integer.MAX_VALUE, supportingAdverseEventReport));
          childrenList.add(new Property("supportingPatientObservation", "Resource(Observation)", "Patient observation that supports the status and recommendation.", 0, java.lang.Integer.MAX_VALUE, supportingPatientObservation));
        }

      public ImmunizationRecommendationRecommendationComponent copy(ImmunizationRecommendation e) {
        ImmunizationRecommendationRecommendationComponent dst = new ImmunizationRecommendationRecommendationComponent();
        dst.recommendationDate = recommendationDate == null ? null : recommendationDate.copy();
        dst.vaccineType = vaccineType == null ? null : vaccineType.copy();
        dst.doseNumber = doseNumber == null ? null : doseNumber.copy();
        dst.forecastStatus = forecastStatus == null ? null : forecastStatus.copy();
        dst.dateCriterion = new ArrayList<ImmunizationRecommendationRecommendationDateCriterionComponent>();
        for (ImmunizationRecommendationRecommendationDateCriterionComponent i : dateCriterion)
          dst.dateCriterion.add(i.copy(e));
        dst.protocol = protocol == null ? null : protocol.copy(e);
        dst.supportingImmunization = new ArrayList<ResourceReference>();
        for (ResourceReference i : supportingImmunization)
          dst.supportingImmunization.add(i.copy());
        dst.supportingAdverseEventReport = new ArrayList<ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent>();
        for (ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent i : supportingAdverseEventReport)
          dst.supportingAdverseEventReport.add(i.copy(e));
        dst.supportingPatientObservation = new ArrayList<ResourceReference>();
        for (ResourceReference i : supportingPatientObservation)
          dst.supportingPatientObservation.add(i.copy());
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
        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value Date recommendation.
         */
        public ImmunizationRecommendationRecommendationDateCriterionComponent setValueSimple(String value) { 
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

      public ImmunizationRecommendationRecommendationDateCriterionComponent copy(ImmunizationRecommendation e) {
        ImmunizationRecommendationRecommendationDateCriterionComponent dst = new ImmunizationRecommendationRecommendationDateCriterionComponent();
        dst.code = code == null ? null : code.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    public static class ImmunizationRecommendationRecommendationProtocolComponent extends BackboneElement {
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

      public ImmunizationRecommendationRecommendationProtocolComponent() {
        super();
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
        public ImmunizationRecommendationRecommendationProtocolComponent setDoseSequence(Integer value) { 
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
          childrenList.add(new Property("doseSequence", "integer", "Nominal position in a series.", 0, java.lang.Integer.MAX_VALUE, doseSequence));
          childrenList.add(new Property("description", "string", "Contains the description about the protocol under which the vaccine was administered.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("authority", "Resource(Organization)", "Indicates the authority who published the protocol?  E.g. ACIP.", 0, java.lang.Integer.MAX_VALUE, authority));
          childrenList.add(new Property("series", "string", "One possible path to achieve presumed immunity against a disease - within the context of an authority.", 0, java.lang.Integer.MAX_VALUE, series));
        }

      public ImmunizationRecommendationRecommendationProtocolComponent copy(ImmunizationRecommendation e) {
        ImmunizationRecommendationRecommendationProtocolComponent dst = new ImmunizationRecommendationRecommendationProtocolComponent();
        dst.doseSequence = doseSequence == null ? null : doseSequence.copy();
        dst.description = description == null ? null : description.copy();
        dst.authority = authority == null ? null : authority.copy();
        dst.series = series == null ? null : series.copy();
        return dst;
      }

  }

    public static class ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent extends BackboneElement {
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

      public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Unique identifier of the adverse event report.)
         */
        public List<Id> getIdentifier() { 
          return this.identifier;
        }

    // syntactic sugar
        /**
         * @return {@link #identifier} (Unique identifier of the adverse event report.)
         */
        public Id addIdentifier() { 
          Id t = new Id();
          this.identifier.add(t);
          return t;
        }

        /**
         * @param value {@link #identifier} (Unique identifier of the adverse event report.)
         */
        public Id addIdentifierSimple(String value) { 
          Id t = new Id();
          t.setValue(value);
          this.identifier.add(t);
          return t;
        }

        /**
         * @return {@link #reportType} (Advers event report classification.)
         */
        public CodeableConcept getReportType() { 
          return this.reportType;
        }

        /**
         * @param value {@link #reportType} (Advers event report classification.)
         */
        public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent setReportType(CodeableConcept value) { 
          this.reportType = value;
          return this;
        }

        /**
         * @return {@link #reportDate} (The date of the adverse event report.)
         */
        public DateTime getReportDate() { 
          return this.reportDate;
        }

        /**
         * @param value {@link #reportDate} (The date of the adverse event report.)
         */
        public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent setReportDate(DateTime value) { 
          this.reportDate = value;
          return this;
        }

        /**
         * @return The date of the adverse event report.
         */
        public String getReportDateSimple() { 
          return this.reportDate == null ? null : this.reportDate.getValue();
        }

        /**
         * @param value The date of the adverse event report.
         */
        public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent setReportDateSimple(String value) { 
          if (value == null)
            this.reportDate = null;
          else {
            if (this.reportDate == null)
              this.reportDate = new DateTime();
            this.reportDate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #text} (The content of the adverse event report.)
         */
        public String_ getText() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (The content of the adverse event report.)
         */
        public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent setText(String_ value) { 
          this.text = value;
          return this;
        }

        /**
         * @return The content of the adverse event report.
         */
        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value The content of the adverse event report.
         */
        public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #reaction} (The documented reaction described in the adverse event report.)
         */
        public List<ResourceReference> getReaction() { 
          return this.reaction;
        }

    // syntactic sugar
        /**
         * @return {@link #reaction} (The documented reaction described in the adverse event report.)
         */
        public ResourceReference addReaction() { 
          ResourceReference t = new ResourceReference();
          this.reaction.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "id", "Unique identifier of the adverse event report.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("reportType", "CodeableConcept", "Advers event report classification.", 0, java.lang.Integer.MAX_VALUE, reportType));
          childrenList.add(new Property("reportDate", "dateTime", "The date of the adverse event report.", 0, java.lang.Integer.MAX_VALUE, reportDate));
          childrenList.add(new Property("text", "string", "The content of the adverse event report.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("reaction", "Resource(AdverseReaction)", "The documented reaction described in the adverse event report.", 0, java.lang.Integer.MAX_VALUE, reaction));
        }

      public ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent copy(ImmunizationRecommendation e) {
        ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent dst = new ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent();
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
    protected List<ImmunizationRecommendationRecommendationComponent> recommendation = new ArrayList<ImmunizationRecommendationRecommendationComponent>();

    public ImmunizationRecommendation() {
      super();
    }

    public ImmunizationRecommendation(ResourceReference subject) {
      super();
      this.subject = subject;
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
        childrenList.add(new Property("subject", "Resource(Patient)", "The patient who is the subject of the profile.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("recommendation", "", "Vaccine administration recommendations.", 0, java.lang.Integer.MAX_VALUE, recommendation));
      }

      public ImmunizationRecommendation copy() {
        ImmunizationRecommendation dst = new ImmunizationRecommendation();
        dst.subject = subject == null ? null : subject.copy();
        dst.recommendation = new ArrayList<ImmunizationRecommendationRecommendationComponent>();
        for (ImmunizationRecommendationRecommendationComponent i : recommendation)
          dst.recommendation.add(i.copy(dst));
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

