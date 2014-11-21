package org.hl7.fhir.instance.model;

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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * (informative) The details of a Healthcare Service available at a location.
 */
public class HealthcareService extends DomainResource {

    public static class ServiceTypeComponent extends BackboneElement {
        /**
         * The specific type of service being delivered or performed.
         */
        protected CodeableConcept type;

        /**
         * Collection of Specialties handled by the Service Site. This is more of a Medical Term.
         */
        protected List<CodeableConcept> specialty = new ArrayList<CodeableConcept>();

        private static final long serialVersionUID = 2092085388L;

      public ServiceTypeComponent() {
        super();
      }

      public ServiceTypeComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (The specific type of service being delivered or performed.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The specific type of service being delivered or performed.)
         */
        public ServiceTypeComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #specialty} (Collection of Specialties handled by the Service Site. This is more of a Medical Term.)
         */
        public List<CodeableConcept> getSpecialty() { 
          return this.specialty;
        }

        /**
         * @return {@link #specialty} (Collection of Specialties handled by the Service Site. This is more of a Medical Term.)
         */
    // syntactic sugar
        public CodeableConcept addSpecialty() { //3
          CodeableConcept t = new CodeableConcept();
          this.specialty.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "The specific type of service being delivered or performed.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("specialty", "CodeableConcept", "Collection of Specialties handled by the Service Site. This is more of a Medical Term.", 0, java.lang.Integer.MAX_VALUE, specialty));
        }

      public ServiceTypeComponent copy() {
        ServiceTypeComponent dst = new ServiceTypeComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.specialty = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : specialty)
          dst.specialty.add(i.copy());
        return dst;
      }

  }

    public static class HealthcareServiceAvailableTimeComponent extends BackboneElement {
        /**
         * Indicates which Days of the week are available between the Start and End Times.
         */
        protected List<CodeableConcept> daysOfWeek = new ArrayList<CodeableConcept>();

        /**
         * Is this always available? (hence times are irrelevant) e.g. 24 hour service.
         */
        protected BooleanType allDay;

        /**
         * The opening time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.
         */
        protected DateTimeType availableStartTime;

        /**
         * The closing time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.
         */
        protected DateTimeType availableEndTime;

        private static final long serialVersionUID = -1020910821L;

      public HealthcareServiceAvailableTimeComponent() {
        super();
      }

        /**
         * @return {@link #daysOfWeek} (Indicates which Days of the week are available between the Start and End Times.)
         */
        public List<CodeableConcept> getDaysOfWeek() { 
          return this.daysOfWeek;
        }

        /**
         * @return {@link #daysOfWeek} (Indicates which Days of the week are available between the Start and End Times.)
         */
    // syntactic sugar
        public CodeableConcept addDaysOfWeek() { //3
          CodeableConcept t = new CodeableConcept();
          this.daysOfWeek.add(t);
          return t;
        }

        /**
         * @return {@link #allDay} (Is this always available? (hence times are irrelevant) e.g. 24 hour service.). This is the underlying object with id, value and extensions. The accessor "getAllDay" gives direct access to the value
         */
        public BooleanType getAllDayElement() { 
          return this.allDay;
        }

        /**
         * @param value {@link #allDay} (Is this always available? (hence times are irrelevant) e.g. 24 hour service.). This is the underlying object with id, value and extensions. The accessor "getAllDay" gives direct access to the value
         */
        public HealthcareServiceAvailableTimeComponent setAllDayElement(BooleanType value) { 
          this.allDay = value;
          return this;
        }

        /**
         * @return Is this always available? (hence times are irrelevant) e.g. 24 hour service.
         */
        public boolean getAllDay() { 
          return this.allDay == null ? false : this.allDay.getValue();
        }

        /**
         * @param value Is this always available? (hence times are irrelevant) e.g. 24 hour service.
         */
        public HealthcareServiceAvailableTimeComponent setAllDay(boolean value) { 
          if (value == false)
            this.allDay = null;
          else {
            if (this.allDay == null)
              this.allDay = new BooleanType();
            this.allDay.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #availableStartTime} (The opening time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.). This is the underlying object with id, value and extensions. The accessor "getAvailableStartTime" gives direct access to the value
         */
        public DateTimeType getAvailableStartTimeElement() { 
          return this.availableStartTime;
        }

        /**
         * @param value {@link #availableStartTime} (The opening time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.). This is the underlying object with id, value and extensions. The accessor "getAvailableStartTime" gives direct access to the value
         */
        public HealthcareServiceAvailableTimeComponent setAvailableStartTimeElement(DateTimeType value) { 
          this.availableStartTime = value;
          return this;
        }

        /**
         * @return The opening time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.
         */
        public DateAndTime getAvailableStartTime() { 
          return this.availableStartTime == null ? null : this.availableStartTime.getValue();
        }

        /**
         * @param value The opening time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.
         */
        public HealthcareServiceAvailableTimeComponent setAvailableStartTime(DateAndTime value) { 
          if (value == null)
            this.availableStartTime = null;
          else {
            if (this.availableStartTime == null)
              this.availableStartTime = new DateTimeType();
            this.availableStartTime.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #availableEndTime} (The closing time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.). This is the underlying object with id, value and extensions. The accessor "getAvailableEndTime" gives direct access to the value
         */
        public DateTimeType getAvailableEndTimeElement() { 
          return this.availableEndTime;
        }

        /**
         * @param value {@link #availableEndTime} (The closing time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.). This is the underlying object with id, value and extensions. The accessor "getAvailableEndTime" gives direct access to the value
         */
        public HealthcareServiceAvailableTimeComponent setAvailableEndTimeElement(DateTimeType value) { 
          this.availableEndTime = value;
          return this;
        }

        /**
         * @return The closing time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.
         */
        public DateAndTime getAvailableEndTime() { 
          return this.availableEndTime == null ? null : this.availableEndTime.getValue();
        }

        /**
         * @param value The closing time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.
         */
        public HealthcareServiceAvailableTimeComponent setAvailableEndTime(DateAndTime value) { 
          if (value == null)
            this.availableEndTime = null;
          else {
            if (this.availableEndTime == null)
              this.availableEndTime = new DateTimeType();
            this.availableEndTime.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("daysOfWeek", "CodeableConcept", "Indicates which Days of the week are available between the Start and End Times.", 0, java.lang.Integer.MAX_VALUE, daysOfWeek));
          childrenList.add(new Property("allDay", "boolean", "Is this always available? (hence times are irrelevant) e.g. 24 hour service.", 0, java.lang.Integer.MAX_VALUE, allDay));
          childrenList.add(new Property("availableStartTime", "dateTime", "The opening time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.", 0, java.lang.Integer.MAX_VALUE, availableStartTime));
          childrenList.add(new Property("availableEndTime", "dateTime", "The closing time of day (the date is not included). Note: If the AllDay flag is set, then this time is ignored.", 0, java.lang.Integer.MAX_VALUE, availableEndTime));
        }

      public HealthcareServiceAvailableTimeComponent copy() {
        HealthcareServiceAvailableTimeComponent dst = new HealthcareServiceAvailableTimeComponent();
        copyValues(dst);
        dst.daysOfWeek = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : daysOfWeek)
          dst.daysOfWeek.add(i.copy());
        dst.allDay = allDay == null ? null : allDay.copy();
        dst.availableStartTime = availableStartTime == null ? null : availableStartTime.copy();
        dst.availableEndTime = availableEndTime == null ? null : availableEndTime.copy();
        return dst;
      }

  }

    public static class HealthcareServiceNotAvailableTimeComponent extends BackboneElement {
        /**
         * The reason that can be presented to the user as to why this time is not available.
         */
        protected StringType description;

        /**
         * Service is not available (seasonally or for a public holiday) from this date.
         */
        protected DateTimeType startDate;

        /**
         * Service is not available (seasonally or for a public holiday) until this date.
         */
        protected DateTimeType endDate;

        private static final long serialVersionUID = -1448794L;

      public HealthcareServiceNotAvailableTimeComponent() {
        super();
      }

      public HealthcareServiceNotAvailableTimeComponent(StringType description) {
        super();
        this.description = description;
      }

        /**
         * @return {@link #description} (The reason that can be presented to the user as to why this time is not available.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (The reason that can be presented to the user as to why this time is not available.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public HealthcareServiceNotAvailableTimeComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return The reason that can be presented to the user as to why this time is not available.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value The reason that can be presented to the user as to why this time is not available.
         */
        public HealthcareServiceNotAvailableTimeComponent setDescription(String value) { 
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          return this;
        }

        /**
         * @return {@link #startDate} (Service is not available (seasonally or for a public holiday) from this date.). This is the underlying object with id, value and extensions. The accessor "getStartDate" gives direct access to the value
         */
        public DateTimeType getStartDateElement() { 
          return this.startDate;
        }

        /**
         * @param value {@link #startDate} (Service is not available (seasonally or for a public holiday) from this date.). This is the underlying object with id, value and extensions. The accessor "getStartDate" gives direct access to the value
         */
        public HealthcareServiceNotAvailableTimeComponent setStartDateElement(DateTimeType value) { 
          this.startDate = value;
          return this;
        }

        /**
         * @return Service is not available (seasonally or for a public holiday) from this date.
         */
        public DateAndTime getStartDate() { 
          return this.startDate == null ? null : this.startDate.getValue();
        }

        /**
         * @param value Service is not available (seasonally or for a public holiday) from this date.
         */
        public HealthcareServiceNotAvailableTimeComponent setStartDate(DateAndTime value) { 
          if (value == null)
            this.startDate = null;
          else {
            if (this.startDate == null)
              this.startDate = new DateTimeType();
            this.startDate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #endDate} (Service is not available (seasonally or for a public holiday) until this date.). This is the underlying object with id, value and extensions. The accessor "getEndDate" gives direct access to the value
         */
        public DateTimeType getEndDateElement() { 
          return this.endDate;
        }

        /**
         * @param value {@link #endDate} (Service is not available (seasonally or for a public holiday) until this date.). This is the underlying object with id, value and extensions. The accessor "getEndDate" gives direct access to the value
         */
        public HealthcareServiceNotAvailableTimeComponent setEndDateElement(DateTimeType value) { 
          this.endDate = value;
          return this;
        }

        /**
         * @return Service is not available (seasonally or for a public holiday) until this date.
         */
        public DateAndTime getEndDate() { 
          return this.endDate == null ? null : this.endDate.getValue();
        }

        /**
         * @param value Service is not available (seasonally or for a public holiday) until this date.
         */
        public HealthcareServiceNotAvailableTimeComponent setEndDate(DateAndTime value) { 
          if (value == null)
            this.endDate = null;
          else {
            if (this.endDate == null)
              this.endDate = new DateTimeType();
            this.endDate.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("description", "string", "The reason that can be presented to the user as to why this time is not available.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("startDate", "dateTime", "Service is not available (seasonally or for a public holiday) from this date.", 0, java.lang.Integer.MAX_VALUE, startDate));
          childrenList.add(new Property("endDate", "dateTime", "Service is not available (seasonally or for a public holiday) until this date.", 0, java.lang.Integer.MAX_VALUE, endDate));
        }

      public HealthcareServiceNotAvailableTimeComponent copy() {
        HealthcareServiceNotAvailableTimeComponent dst = new HealthcareServiceNotAvailableTimeComponent();
        copyValues(dst);
        dst.description = description == null ? null : description.copy();
        dst.startDate = startDate == null ? null : startDate.copy();
        dst.endDate = endDate == null ? null : endDate.copy();
        return dst;
      }

  }

    /**
     * External Ids for this item.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The location where this healthcare service may be provided.
     */
    protected Reference location;

    /**
     * The actual object that is the target of the reference (The location where this healthcare service may be provided.)
     */
    protected Location locationTarget;

    /**
     * Identifies the broad category of service being performed or delivered. Selecting a Service Category then determines the list of relevant service types that can be selected in the Primary Service Type.
     */
    protected CodeableConcept serviceCategory;

    /**
     * A specific type of service that may be delivered or performed.
     */
    protected List<ServiceTypeComponent> serviceType = new ArrayList<ServiceTypeComponent>();

    /**
     * Further description of the service as it would be presented to a consumer while searching.
     */
    protected StringType serviceName;

    /**
     * Additional description of the  or any specific issues not covered by the other attributes, which can be displayed as further detail under the serviceName.
     */
    protected StringType comment;

    /**
     * Extra details about the service that can't be placed in the other fields.
     */
    protected StringType extraDetails;

    /**
     * The free provision code provides a link to the Free Provision reference entity to enable the selection of one free provision type.
     */
    protected CodeableConcept freeProvisionCode;

    /**
     * Does this service have specific eligibility requirements that need to be met in order to use the service.
     */
    protected CodeableConcept eligibility;

    /**
     * The description of service eligibility should, in general, not exceed one or two paragraphs. It should be sufficient for a prospective consumer to determine if they are likely to be eligible or not. Where eligibility requirements and conditions are complex, it may simply be noted that an eligibility assessment is required. Where eligibility is determined by an outside source, such as an Act of Parliament, this should be noted, preferably with a reference to a commonly available copy of the source document such as a web page.
     */
    protected StringType eligibilityNote;

    /**
     * Indicates whether or not a prospective consumer will require an appointment for a particular service at a Site to be provided by the Organization. Indicates if an appointment is required for access to this service. If this flag is 'NotDefined', then this flag is overridden by the Site's availability flag. (ConditionalIndicator Enum).
     */
    protected CodeableConcept appointmentRequired;

    /**
     * If there is an image associated with this Service Site, its URI can be included here.
     */
    protected UriType imageURI;

    /**
     * A Collection of times that the Service Site is available.
     */
    protected List<HealthcareServiceAvailableTimeComponent> availableTime = new ArrayList<HealthcareServiceAvailableTimeComponent>();

    /**
     * Not avail times - need better description.
     */
    protected List<HealthcareServiceNotAvailableTimeComponent> notAvailableTime = new ArrayList<HealthcareServiceNotAvailableTimeComponent>();

    /**
     * A description of Site availability exceptions, e.g., public holiday availability. Succinctly describing all possible exceptions to normal Site availability as details in the Available Times and Not Available Times.
     */
    protected StringType availabilityExceptions;

    /**
     * The public part of the 'keys' allocated to an Organization by an accredited body to support secure exchange of data over the internet. To be provided by the Organization, where available.
     */
    protected StringType publicKey;

    /**
     * Program Names that can be used to categorize the service.
     */
    protected List<StringType> programName = new ArrayList<StringType>();

    /**
     * List of contacts related to this specific healthcare service. If this is empty, then refer to the location's contacts.
     */
    protected List<ContactPoint> contactPoint = new ArrayList<ContactPoint>();

    /**
     * Collection of Characteristics (attributes).
     */
    protected List<CodeableConcept> characteristic = new ArrayList<CodeableConcept>();

    /**
     * Ways that the service accepts referrals.
     */
    protected List<CodeableConcept> referralMethod = new ArrayList<CodeableConcept>();

    /**
     * The setting where this service can be provided, such is in home, or at location in organisation.
     */
    protected List<CodeableConcept> setting = new ArrayList<CodeableConcept>();

    /**
     * Collection of Target Groups for the Service Site (The target audience that this service is for).
     */
    protected List<CodeableConcept> targetGroup = new ArrayList<CodeableConcept>();

    /**
     * Need better description.
     */
    protected List<CodeableConcept> coverageArea = new ArrayList<CodeableConcept>();

    /**
     * Need better description.
     */
    protected List<CodeableConcept> catchmentArea = new ArrayList<CodeableConcept>();

    /**
     * List of the specific.
     */
    protected List<CodeableConcept> serviceCode = new ArrayList<CodeableConcept>();

    private static final long serialVersionUID = 149126444L;

    public HealthcareService() {
      super();
    }

    public HealthcareService(Reference location) {
      super();
      this.location = location;
    }

    /**
     * @return {@link #identifier} (External Ids for this item.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (External Ids for this item.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #location} (The location where this healthcare service may be provided.)
     */
    public Reference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (The location where this healthcare service may be provided.)
     */
    public HealthcareService setLocation(Reference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #location} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The location where this healthcare service may be provided.)
     */
    public Location getLocationTarget() { 
      return this.locationTarget;
    }

    /**
     * @param value {@link #location} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The location where this healthcare service may be provided.)
     */
    public HealthcareService setLocationTarget(Location value) { 
      this.locationTarget = value;
      return this;
    }

    /**
     * @return {@link #serviceCategory} (Identifies the broad category of service being performed or delivered. Selecting a Service Category then determines the list of relevant service types that can be selected in the Primary Service Type.)
     */
    public CodeableConcept getServiceCategory() { 
      return this.serviceCategory;
    }

    /**
     * @param value {@link #serviceCategory} (Identifies the broad category of service being performed or delivered. Selecting a Service Category then determines the list of relevant service types that can be selected in the Primary Service Type.)
     */
    public HealthcareService setServiceCategory(CodeableConcept value) { 
      this.serviceCategory = value;
      return this;
    }

    /**
     * @return {@link #serviceType} (A specific type of service that may be delivered or performed.)
     */
    public List<ServiceTypeComponent> getServiceType() { 
      return this.serviceType;
    }

    /**
     * @return {@link #serviceType} (A specific type of service that may be delivered or performed.)
     */
    // syntactic sugar
    public ServiceTypeComponent addServiceType() { //3
      ServiceTypeComponent t = new ServiceTypeComponent();
      this.serviceType.add(t);
      return t;
    }

    /**
     * @return {@link #serviceName} (Further description of the service as it would be presented to a consumer while searching.). This is the underlying object with id, value and extensions. The accessor "getServiceName" gives direct access to the value
     */
    public StringType getServiceNameElement() { 
      return this.serviceName;
    }

    /**
     * @param value {@link #serviceName} (Further description of the service as it would be presented to a consumer while searching.). This is the underlying object with id, value and extensions. The accessor "getServiceName" gives direct access to the value
     */
    public HealthcareService setServiceNameElement(StringType value) { 
      this.serviceName = value;
      return this;
    }

    /**
     * @return Further description of the service as it would be presented to a consumer while searching.
     */
    public String getServiceName() { 
      return this.serviceName == null ? null : this.serviceName.getValue();
    }

    /**
     * @param value Further description of the service as it would be presented to a consumer while searching.
     */
    public HealthcareService setServiceName(String value) { 
      if (Utilities.noString(value))
        this.serviceName = null;
      else {
        if (this.serviceName == null)
          this.serviceName = new StringType();
        this.serviceName.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #comment} (Additional description of the  or any specific issues not covered by the other attributes, which can be displayed as further detail under the serviceName.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public StringType getCommentElement() { 
      return this.comment;
    }

    /**
     * @param value {@link #comment} (Additional description of the  or any specific issues not covered by the other attributes, which can be displayed as further detail under the serviceName.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public HealthcareService setCommentElement(StringType value) { 
      this.comment = value;
      return this;
    }

    /**
     * @return Additional description of the  or any specific issues not covered by the other attributes, which can be displayed as further detail under the serviceName.
     */
    public String getComment() { 
      return this.comment == null ? null : this.comment.getValue();
    }

    /**
     * @param value Additional description of the  or any specific issues not covered by the other attributes, which can be displayed as further detail under the serviceName.
     */
    public HealthcareService setComment(String value) { 
      if (Utilities.noString(value))
        this.comment = null;
      else {
        if (this.comment == null)
          this.comment = new StringType();
        this.comment.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #extraDetails} (Extra details about the service that can't be placed in the other fields.). This is the underlying object with id, value and extensions. The accessor "getExtraDetails" gives direct access to the value
     */
    public StringType getExtraDetailsElement() { 
      return this.extraDetails;
    }

    /**
     * @param value {@link #extraDetails} (Extra details about the service that can't be placed in the other fields.). This is the underlying object with id, value and extensions. The accessor "getExtraDetails" gives direct access to the value
     */
    public HealthcareService setExtraDetailsElement(StringType value) { 
      this.extraDetails = value;
      return this;
    }

    /**
     * @return Extra details about the service that can't be placed in the other fields.
     */
    public String getExtraDetails() { 
      return this.extraDetails == null ? null : this.extraDetails.getValue();
    }

    /**
     * @param value Extra details about the service that can't be placed in the other fields.
     */
    public HealthcareService setExtraDetails(String value) { 
      if (Utilities.noString(value))
        this.extraDetails = null;
      else {
        if (this.extraDetails == null)
          this.extraDetails = new StringType();
        this.extraDetails.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #freeProvisionCode} (The free provision code provides a link to the Free Provision reference entity to enable the selection of one free provision type.)
     */
    public CodeableConcept getFreeProvisionCode() { 
      return this.freeProvisionCode;
    }

    /**
     * @param value {@link #freeProvisionCode} (The free provision code provides a link to the Free Provision reference entity to enable the selection of one free provision type.)
     */
    public HealthcareService setFreeProvisionCode(CodeableConcept value) { 
      this.freeProvisionCode = value;
      return this;
    }

    /**
     * @return {@link #eligibility} (Does this service have specific eligibility requirements that need to be met in order to use the service.)
     */
    public CodeableConcept getEligibility() { 
      return this.eligibility;
    }

    /**
     * @param value {@link #eligibility} (Does this service have specific eligibility requirements that need to be met in order to use the service.)
     */
    public HealthcareService setEligibility(CodeableConcept value) { 
      this.eligibility = value;
      return this;
    }

    /**
     * @return {@link #eligibilityNote} (The description of service eligibility should, in general, not exceed one or two paragraphs. It should be sufficient for a prospective consumer to determine if they are likely to be eligible or not. Where eligibility requirements and conditions are complex, it may simply be noted that an eligibility assessment is required. Where eligibility is determined by an outside source, such as an Act of Parliament, this should be noted, preferably with a reference to a commonly available copy of the source document such as a web page.). This is the underlying object with id, value and extensions. The accessor "getEligibilityNote" gives direct access to the value
     */
    public StringType getEligibilityNoteElement() { 
      return this.eligibilityNote;
    }

    /**
     * @param value {@link #eligibilityNote} (The description of service eligibility should, in general, not exceed one or two paragraphs. It should be sufficient for a prospective consumer to determine if they are likely to be eligible or not. Where eligibility requirements and conditions are complex, it may simply be noted that an eligibility assessment is required. Where eligibility is determined by an outside source, such as an Act of Parliament, this should be noted, preferably with a reference to a commonly available copy of the source document such as a web page.). This is the underlying object with id, value and extensions. The accessor "getEligibilityNote" gives direct access to the value
     */
    public HealthcareService setEligibilityNoteElement(StringType value) { 
      this.eligibilityNote = value;
      return this;
    }

    /**
     * @return The description of service eligibility should, in general, not exceed one or two paragraphs. It should be sufficient for a prospective consumer to determine if they are likely to be eligible or not. Where eligibility requirements and conditions are complex, it may simply be noted that an eligibility assessment is required. Where eligibility is determined by an outside source, such as an Act of Parliament, this should be noted, preferably with a reference to a commonly available copy of the source document such as a web page.
     */
    public String getEligibilityNote() { 
      return this.eligibilityNote == null ? null : this.eligibilityNote.getValue();
    }

    /**
     * @param value The description of service eligibility should, in general, not exceed one or two paragraphs. It should be sufficient for a prospective consumer to determine if they are likely to be eligible or not. Where eligibility requirements and conditions are complex, it may simply be noted that an eligibility assessment is required. Where eligibility is determined by an outside source, such as an Act of Parliament, this should be noted, preferably with a reference to a commonly available copy of the source document such as a web page.
     */
    public HealthcareService setEligibilityNote(String value) { 
      if (Utilities.noString(value))
        this.eligibilityNote = null;
      else {
        if (this.eligibilityNote == null)
          this.eligibilityNote = new StringType();
        this.eligibilityNote.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #appointmentRequired} (Indicates whether or not a prospective consumer will require an appointment for a particular service at a Site to be provided by the Organization. Indicates if an appointment is required for access to this service. If this flag is 'NotDefined', then this flag is overridden by the Site's availability flag. (ConditionalIndicator Enum).)
     */
    public CodeableConcept getAppointmentRequired() { 
      return this.appointmentRequired;
    }

    /**
     * @param value {@link #appointmentRequired} (Indicates whether or not a prospective consumer will require an appointment for a particular service at a Site to be provided by the Organization. Indicates if an appointment is required for access to this service. If this flag is 'NotDefined', then this flag is overridden by the Site's availability flag. (ConditionalIndicator Enum).)
     */
    public HealthcareService setAppointmentRequired(CodeableConcept value) { 
      this.appointmentRequired = value;
      return this;
    }

    /**
     * @return {@link #imageURI} (If there is an image associated with this Service Site, its URI can be included here.). This is the underlying object with id, value and extensions. The accessor "getImageURI" gives direct access to the value
     */
    public UriType getImageURIElement() { 
      return this.imageURI;
    }

    /**
     * @param value {@link #imageURI} (If there is an image associated with this Service Site, its URI can be included here.). This is the underlying object with id, value and extensions. The accessor "getImageURI" gives direct access to the value
     */
    public HealthcareService setImageURIElement(UriType value) { 
      this.imageURI = value;
      return this;
    }

    /**
     * @return If there is an image associated with this Service Site, its URI can be included here.
     */
    public String getImageURI() { 
      return this.imageURI == null ? null : this.imageURI.getValue();
    }

    /**
     * @param value If there is an image associated with this Service Site, its URI can be included here.
     */
    public HealthcareService setImageURI(String value) { 
      if (Utilities.noString(value))
        this.imageURI = null;
      else {
        if (this.imageURI == null)
          this.imageURI = new UriType();
        this.imageURI.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #availableTime} (A Collection of times that the Service Site is available.)
     */
    public List<HealthcareServiceAvailableTimeComponent> getAvailableTime() { 
      return this.availableTime;
    }

    /**
     * @return {@link #availableTime} (A Collection of times that the Service Site is available.)
     */
    // syntactic sugar
    public HealthcareServiceAvailableTimeComponent addAvailableTime() { //3
      HealthcareServiceAvailableTimeComponent t = new HealthcareServiceAvailableTimeComponent();
      this.availableTime.add(t);
      return t;
    }

    /**
     * @return {@link #notAvailableTime} (Not avail times - need better description.)
     */
    public List<HealthcareServiceNotAvailableTimeComponent> getNotAvailableTime() { 
      return this.notAvailableTime;
    }

    /**
     * @return {@link #notAvailableTime} (Not avail times - need better description.)
     */
    // syntactic sugar
    public HealthcareServiceNotAvailableTimeComponent addNotAvailableTime() { //3
      HealthcareServiceNotAvailableTimeComponent t = new HealthcareServiceNotAvailableTimeComponent();
      this.notAvailableTime.add(t);
      return t;
    }

    /**
     * @return {@link #availabilityExceptions} (A description of Site availability exceptions, e.g., public holiday availability. Succinctly describing all possible exceptions to normal Site availability as details in the Available Times and Not Available Times.). This is the underlying object with id, value and extensions. The accessor "getAvailabilityExceptions" gives direct access to the value
     */
    public StringType getAvailabilityExceptionsElement() { 
      return this.availabilityExceptions;
    }

    /**
     * @param value {@link #availabilityExceptions} (A description of Site availability exceptions, e.g., public holiday availability. Succinctly describing all possible exceptions to normal Site availability as details in the Available Times and Not Available Times.). This is the underlying object with id, value and extensions. The accessor "getAvailabilityExceptions" gives direct access to the value
     */
    public HealthcareService setAvailabilityExceptionsElement(StringType value) { 
      this.availabilityExceptions = value;
      return this;
    }

    /**
     * @return A description of Site availability exceptions, e.g., public holiday availability. Succinctly describing all possible exceptions to normal Site availability as details in the Available Times and Not Available Times.
     */
    public String getAvailabilityExceptions() { 
      return this.availabilityExceptions == null ? null : this.availabilityExceptions.getValue();
    }

    /**
     * @param value A description of Site availability exceptions, e.g., public holiday availability. Succinctly describing all possible exceptions to normal Site availability as details in the Available Times and Not Available Times.
     */
    public HealthcareService setAvailabilityExceptions(String value) { 
      if (Utilities.noString(value))
        this.availabilityExceptions = null;
      else {
        if (this.availabilityExceptions == null)
          this.availabilityExceptions = new StringType();
        this.availabilityExceptions.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #publicKey} (The public part of the 'keys' allocated to an Organization by an accredited body to support secure exchange of data over the internet. To be provided by the Organization, where available.). This is the underlying object with id, value and extensions. The accessor "getPublicKey" gives direct access to the value
     */
    public StringType getPublicKeyElement() { 
      return this.publicKey;
    }

    /**
     * @param value {@link #publicKey} (The public part of the 'keys' allocated to an Organization by an accredited body to support secure exchange of data over the internet. To be provided by the Organization, where available.). This is the underlying object with id, value and extensions. The accessor "getPublicKey" gives direct access to the value
     */
    public HealthcareService setPublicKeyElement(StringType value) { 
      this.publicKey = value;
      return this;
    }

    /**
     * @return The public part of the 'keys' allocated to an Organization by an accredited body to support secure exchange of data over the internet. To be provided by the Organization, where available.
     */
    public String getPublicKey() { 
      return this.publicKey == null ? null : this.publicKey.getValue();
    }

    /**
     * @param value The public part of the 'keys' allocated to an Organization by an accredited body to support secure exchange of data over the internet. To be provided by the Organization, where available.
     */
    public HealthcareService setPublicKey(String value) { 
      if (Utilities.noString(value))
        this.publicKey = null;
      else {
        if (this.publicKey == null)
          this.publicKey = new StringType();
        this.publicKey.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #programName} (Program Names that can be used to categorize the service.)
     */
    public List<StringType> getProgramName() { 
      return this.programName;
    }

    /**
     * @return {@link #programName} (Program Names that can be used to categorize the service.)
     */
    // syntactic sugar
    public StringType addProgramNameElement() {//2 
      StringType t = new StringType();
      this.programName.add(t);
      return t;
    }

    /**
     * @param value {@link #programName} (Program Names that can be used to categorize the service.)
     */
    public HealthcareService addProgramName(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.programName.add(t);
      return this;
    }

    /**
     * @param value {@link #programName} (Program Names that can be used to categorize the service.)
     */
    public boolean hasProgramName(String value) { 
      for (StringType v : this.programName)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #contactPoint} (List of contacts related to this specific healthcare service. If this is empty, then refer to the location's contacts.)
     */
    public List<ContactPoint> getContactPoint() { 
      return this.contactPoint;
    }

    /**
     * @return {@link #contactPoint} (List of contacts related to this specific healthcare service. If this is empty, then refer to the location's contacts.)
     */
    // syntactic sugar
    public ContactPoint addContactPoint() { //3
      ContactPoint t = new ContactPoint();
      this.contactPoint.add(t);
      return t;
    }

    /**
     * @return {@link #characteristic} (Collection of Characteristics (attributes).)
     */
    public List<CodeableConcept> getCharacteristic() { 
      return this.characteristic;
    }

    /**
     * @return {@link #characteristic} (Collection of Characteristics (attributes).)
     */
    // syntactic sugar
    public CodeableConcept addCharacteristic() { //3
      CodeableConcept t = new CodeableConcept();
      this.characteristic.add(t);
      return t;
    }

    /**
     * @return {@link #referralMethod} (Ways that the service accepts referrals.)
     */
    public List<CodeableConcept> getReferralMethod() { 
      return this.referralMethod;
    }

    /**
     * @return {@link #referralMethod} (Ways that the service accepts referrals.)
     */
    // syntactic sugar
    public CodeableConcept addReferralMethod() { //3
      CodeableConcept t = new CodeableConcept();
      this.referralMethod.add(t);
      return t;
    }

    /**
     * @return {@link #setting} (The setting where this service can be provided, such is in home, or at location in organisation.)
     */
    public List<CodeableConcept> getSetting() { 
      return this.setting;
    }

    /**
     * @return {@link #setting} (The setting where this service can be provided, such is in home, or at location in organisation.)
     */
    // syntactic sugar
    public CodeableConcept addSetting() { //3
      CodeableConcept t = new CodeableConcept();
      this.setting.add(t);
      return t;
    }

    /**
     * @return {@link #targetGroup} (Collection of Target Groups for the Service Site (The target audience that this service is for).)
     */
    public List<CodeableConcept> getTargetGroup() { 
      return this.targetGroup;
    }

    /**
     * @return {@link #targetGroup} (Collection of Target Groups for the Service Site (The target audience that this service is for).)
     */
    // syntactic sugar
    public CodeableConcept addTargetGroup() { //3
      CodeableConcept t = new CodeableConcept();
      this.targetGroup.add(t);
      return t;
    }

    /**
     * @return {@link #coverageArea} (Need better description.)
     */
    public List<CodeableConcept> getCoverageArea() { 
      return this.coverageArea;
    }

    /**
     * @return {@link #coverageArea} (Need better description.)
     */
    // syntactic sugar
    public CodeableConcept addCoverageArea() { //3
      CodeableConcept t = new CodeableConcept();
      this.coverageArea.add(t);
      return t;
    }

    /**
     * @return {@link #catchmentArea} (Need better description.)
     */
    public List<CodeableConcept> getCatchmentArea() { 
      return this.catchmentArea;
    }

    /**
     * @return {@link #catchmentArea} (Need better description.)
     */
    // syntactic sugar
    public CodeableConcept addCatchmentArea() { //3
      CodeableConcept t = new CodeableConcept();
      this.catchmentArea.add(t);
      return t;
    }

    /**
     * @return {@link #serviceCode} (List of the specific.)
     */
    public List<CodeableConcept> getServiceCode() { 
      return this.serviceCode;
    }

    /**
     * @return {@link #serviceCode} (List of the specific.)
     */
    // syntactic sugar
    public CodeableConcept addServiceCode() { //3
      CodeableConcept t = new CodeableConcept();
      this.serviceCode.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "External Ids for this item.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("location", "Reference(Location)", "The location where this healthcare service may be provided.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("serviceCategory", "CodeableConcept", "Identifies the broad category of service being performed or delivered. Selecting a Service Category then determines the list of relevant service types that can be selected in the Primary Service Type.", 0, java.lang.Integer.MAX_VALUE, serviceCategory));
        childrenList.add(new Property("serviceType", "", "A specific type of service that may be delivered or performed.", 0, java.lang.Integer.MAX_VALUE, serviceType));
        childrenList.add(new Property("serviceName", "string", "Further description of the service as it would be presented to a consumer while searching.", 0, java.lang.Integer.MAX_VALUE, serviceName));
        childrenList.add(new Property("comment", "string", "Additional description of the  or any specific issues not covered by the other attributes, which can be displayed as further detail under the serviceName.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("extraDetails", "string", "Extra details about the service that can't be placed in the other fields.", 0, java.lang.Integer.MAX_VALUE, extraDetails));
        childrenList.add(new Property("freeProvisionCode", "CodeableConcept", "The free provision code provides a link to the Free Provision reference entity to enable the selection of one free provision type.", 0, java.lang.Integer.MAX_VALUE, freeProvisionCode));
        childrenList.add(new Property("eligibility", "CodeableConcept", "Does this service have specific eligibility requirements that need to be met in order to use the service.", 0, java.lang.Integer.MAX_VALUE, eligibility));
        childrenList.add(new Property("eligibilityNote", "string", "The description of service eligibility should, in general, not exceed one or two paragraphs. It should be sufficient for a prospective consumer to determine if they are likely to be eligible or not. Where eligibility requirements and conditions are complex, it may simply be noted that an eligibility assessment is required. Where eligibility is determined by an outside source, such as an Act of Parliament, this should be noted, preferably with a reference to a commonly available copy of the source document such as a web page.", 0, java.lang.Integer.MAX_VALUE, eligibilityNote));
        childrenList.add(new Property("appointmentRequired", "CodeableConcept", "Indicates whether or not a prospective consumer will require an appointment for a particular service at a Site to be provided by the Organization. Indicates if an appointment is required for access to this service. If this flag is 'NotDefined', then this flag is overridden by the Site's availability flag. (ConditionalIndicator Enum).", 0, java.lang.Integer.MAX_VALUE, appointmentRequired));
        childrenList.add(new Property("imageURI", "uri", "If there is an image associated with this Service Site, its URI can be included here.", 0, java.lang.Integer.MAX_VALUE, imageURI));
        childrenList.add(new Property("availableTime", "", "A Collection of times that the Service Site is available.", 0, java.lang.Integer.MAX_VALUE, availableTime));
        childrenList.add(new Property("notAvailableTime", "", "Not avail times - need better description.", 0, java.lang.Integer.MAX_VALUE, notAvailableTime));
        childrenList.add(new Property("availabilityExceptions", "string", "A description of Site availability exceptions, e.g., public holiday availability. Succinctly describing all possible exceptions to normal Site availability as details in the Available Times and Not Available Times.", 0, java.lang.Integer.MAX_VALUE, availabilityExceptions));
        childrenList.add(new Property("publicKey", "string", "The public part of the 'keys' allocated to an Organization by an accredited body to support secure exchange of data over the internet. To be provided by the Organization, where available.", 0, java.lang.Integer.MAX_VALUE, publicKey));
        childrenList.add(new Property("programName", "string", "Program Names that can be used to categorize the service.", 0, java.lang.Integer.MAX_VALUE, programName));
        childrenList.add(new Property("contactPoint", "ContactPoint", "List of contacts related to this specific healthcare service. If this is empty, then refer to the location's contacts.", 0, java.lang.Integer.MAX_VALUE, contactPoint));
        childrenList.add(new Property("characteristic", "CodeableConcept", "Collection of Characteristics (attributes).", 0, java.lang.Integer.MAX_VALUE, characteristic));
        childrenList.add(new Property("referralMethod", "CodeableConcept", "Ways that the service accepts referrals.", 0, java.lang.Integer.MAX_VALUE, referralMethod));
        childrenList.add(new Property("setting", "CodeableConcept", "The setting where this service can be provided, such is in home, or at location in organisation.", 0, java.lang.Integer.MAX_VALUE, setting));
        childrenList.add(new Property("targetGroup", "CodeableConcept", "Collection of Target Groups for the Service Site (The target audience that this service is for).", 0, java.lang.Integer.MAX_VALUE, targetGroup));
        childrenList.add(new Property("coverageArea", "CodeableConcept", "Need better description.", 0, java.lang.Integer.MAX_VALUE, coverageArea));
        childrenList.add(new Property("catchmentArea", "CodeableConcept", "Need better description.", 0, java.lang.Integer.MAX_VALUE, catchmentArea));
        childrenList.add(new Property("serviceCode", "CodeableConcept", "List of the specific.", 0, java.lang.Integer.MAX_VALUE, serviceCode));
      }

      public HealthcareService copy() {
        HealthcareService dst = new HealthcareService();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.location = location == null ? null : location.copy();
        dst.serviceCategory = serviceCategory == null ? null : serviceCategory.copy();
        dst.serviceType = new ArrayList<ServiceTypeComponent>();
        for (ServiceTypeComponent i : serviceType)
          dst.serviceType.add(i.copy());
        dst.serviceName = serviceName == null ? null : serviceName.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.extraDetails = extraDetails == null ? null : extraDetails.copy();
        dst.freeProvisionCode = freeProvisionCode == null ? null : freeProvisionCode.copy();
        dst.eligibility = eligibility == null ? null : eligibility.copy();
        dst.eligibilityNote = eligibilityNote == null ? null : eligibilityNote.copy();
        dst.appointmentRequired = appointmentRequired == null ? null : appointmentRequired.copy();
        dst.imageURI = imageURI == null ? null : imageURI.copy();
        dst.availableTime = new ArrayList<HealthcareServiceAvailableTimeComponent>();
        for (HealthcareServiceAvailableTimeComponent i : availableTime)
          dst.availableTime.add(i.copy());
        dst.notAvailableTime = new ArrayList<HealthcareServiceNotAvailableTimeComponent>();
        for (HealthcareServiceNotAvailableTimeComponent i : notAvailableTime)
          dst.notAvailableTime.add(i.copy());
        dst.availabilityExceptions = availabilityExceptions == null ? null : availabilityExceptions.copy();
        dst.publicKey = publicKey == null ? null : publicKey.copy();
        dst.programName = new ArrayList<StringType>();
        for (StringType i : programName)
          dst.programName.add(i.copy());
        dst.contactPoint = new ArrayList<ContactPoint>();
        for (ContactPoint i : contactPoint)
          dst.contactPoint.add(i.copy());
        dst.characteristic = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : characteristic)
          dst.characteristic.add(i.copy());
        dst.referralMethod = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : referralMethod)
          dst.referralMethod.add(i.copy());
        dst.setting = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : setting)
          dst.setting.add(i.copy());
        dst.targetGroup = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : targetGroup)
          dst.targetGroup.add(i.copy());
        dst.coverageArea = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : coverageArea)
          dst.coverageArea.add(i.copy());
        dst.catchmentArea = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : catchmentArea)
          dst.catchmentArea.add(i.copy());
        dst.serviceCode = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : serviceCode)
          dst.serviceCode.add(i.copy());
        return dst;
      }

      protected HealthcareService typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.HealthcareService;
   }


}

