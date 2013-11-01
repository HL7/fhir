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

// Generated on Sat, Nov 2, 2013 09:06+1100 for FHIR v0.12

import java.util.*;

/**
 * Simple assertions and measurements made about a patient, device or other subject.
 */
public class Observation extends Resource {

    public enum ObservationStatus {
        registered, // The existence of the observation is registered, but there is no result yet available
        interim, // This is an initial or interim observation: data may be incomplete or unverified
        final_, // The observation is complete and verified by an authorised person
        amended, // The observation has been modified subsequent to being Final, and is complete and verified by an authorised person
        cancelled, // The observation is unavailable because the measurement was not started or not completed (also sometimes called "aborted")
        withdrawn, // The observation has been withdrawn following previous Final release
        Null; // added to help the parsers
        public static ObservationStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return registered;
        if ("interim".equals(codeString))
          return interim;
        if ("final".equals(codeString))
          return final_;
        if ("amended".equals(codeString))
          return amended;
        if ("cancelled".equals(codeString))
          return cancelled;
        if ("withdrawn".equals(codeString))
          return withdrawn;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case registered: return "registered";
            case interim: return "interim";
            case final_: return "final";
            case amended: return "amended";
            case cancelled: return "cancelled";
            case withdrawn: return "withdrawn";
            default: return "?";
          }
        }
    }

  public static class ObservationStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return ObservationStatus.registered;
        if ("interim".equals(codeString))
          return ObservationStatus.interim;
        if ("final".equals(codeString))
          return ObservationStatus.final_;
        if ("amended".equals(codeString))
          return ObservationStatus.amended;
        if ("cancelled".equals(codeString))
          return ObservationStatus.cancelled;
        if ("withdrawn".equals(codeString))
          return ObservationStatus.withdrawn;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationStatus.registered)
        return "registered";
      if (code == ObservationStatus.interim)
        return "interim";
      if (code == ObservationStatus.final_)
        return "final";
      if (code == ObservationStatus.amended)
        return "amended";
      if (code == ObservationStatus.cancelled)
        return "cancelled";
      if (code == ObservationStatus.withdrawn)
        return "withdrawn";
      return "?";
      }
    }

    public enum ObservationReliability {
        ok, // The result has no reliability concerns.
        ongoing, // An early estimate of value; measurement is still occurring.
        early, // An early estimate of value; processing is still occurring.
        questionable, // The observation value should be treated with care.
        calibrating, // The result has been generated while calibration is occurring.
        error, // The observation could not be completed because of an error.
        unknown, // No observation value was available.
        Null; // added to help the parsers
        public static ObservationReliability fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return ok;
        if ("ongoing".equals(codeString))
          return ongoing;
        if ("early".equals(codeString))
          return early;
        if ("questionable".equals(codeString))
          return questionable;
        if ("calibrating".equals(codeString))
          return calibrating;
        if ("error".equals(codeString))
          return error;
        if ("unknown".equals(codeString))
          return unknown;
        throw new Exception("Unknown ObservationReliability code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ok: return "ok";
            case ongoing: return "ongoing";
            case early: return "early";
            case questionable: return "questionable";
            case calibrating: return "calibrating";
            case error: return "error";
            case unknown: return "unknown";
            default: return "?";
          }
        }
    }

  public static class ObservationReliabilityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return ObservationReliability.ok;
        if ("ongoing".equals(codeString))
          return ObservationReliability.ongoing;
        if ("early".equals(codeString))
          return ObservationReliability.early;
        if ("questionable".equals(codeString))
          return ObservationReliability.questionable;
        if ("calibrating".equals(codeString))
          return ObservationReliability.calibrating;
        if ("error".equals(codeString))
          return ObservationReliability.error;
        if ("unknown".equals(codeString))
          return ObservationReliability.unknown;
        throw new Exception("Unknown ObservationReliability code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationReliability.ok)
        return "ok";
      if (code == ObservationReliability.ongoing)
        return "ongoing";
      if (code == ObservationReliability.early)
        return "early";
      if (code == ObservationReliability.questionable)
        return "questionable";
      if (code == ObservationReliability.calibrating)
        return "calibrating";
      if (code == ObservationReliability.error)
        return "error";
      if (code == ObservationReliability.unknown)
        return "unknown";
      return "?";
      }
    }

    public static class ObservationReferenceRangeComponent extends BackboneElement {
        /**
         * Code for the meaning of the reference range.
         */
        protected CodeableConcept meaning;

        /**
         * Actual value of the reference range.  May be a quantity (<20mg/L), a range (10-20 umol/L), or some text.
         */
        protected Type range;

      public ObservationReferenceRangeComponent() {
        super();
      }

      public ObservationReferenceRangeComponent(Type range) {
        super();
        this.range = range;
      }

        /**
         * @return {@link #meaning} (Code for the meaning of the reference range.)
         */
        public CodeableConcept getMeaning() { 
          return this.meaning;
        }

        /**
         * @param value {@link #meaning} (Code for the meaning of the reference range.)
         */
        public ObservationReferenceRangeComponent setMeaning(CodeableConcept value) { 
          this.meaning = value;
          return this;
        }

        /**
         * @return {@link #range} (Actual value of the reference range.  May be a quantity (<20mg/L), a range (10-20 umol/L), or some text.)
         */
        public Type getRange() { 
          return this.range;
        }

        /**
         * @param value {@link #range} (Actual value of the reference range.  May be a quantity (<20mg/L), a range (10-20 umol/L), or some text.)
         */
        public ObservationReferenceRangeComponent setRange(Type value) { 
          this.range = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("meaning", "CodeableConcept", "Code for the meaning of the reference range.", 0, java.lang.Integer.MAX_VALUE, meaning));
          childrenList.add(new Property("range[x]", "Quantity|Range|string", "Actual value of the reference range.  May be a quantity (<20mg/L), a range (10-20 umol/L), or some text.", 0, java.lang.Integer.MAX_VALUE, range));
        }

      public ObservationReferenceRangeComponent copy(Observation e) {
        ObservationReferenceRangeComponent dst = new ObservationReferenceRangeComponent();
        dst.meaning = meaning == null ? null : meaning.copy();
        dst.range = range == null ? null : range.copy();
        return dst;
      }

  }

    public static class ObservationComponentComponent extends BackboneElement {
        /**
         * Identifies what type of sub-observation was performed.
         */
        protected CodeableConcept name;

        /**
         * The information determined as a result of making the sub-observation.
         */
        protected Type value;

      public ObservationComponentComponent() {
        super();
      }

      public ObservationComponentComponent(CodeableConcept name, Type value) {
        super();
        this.name = name;
        this.value = value;
      }

        /**
         * @return {@link #name} (Identifies what type of sub-observation was performed.)
         */
        public CodeableConcept getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Identifies what type of sub-observation was performed.)
         */
        public ObservationComponentComponent setName(CodeableConcept value) { 
          this.name = value;
          return this;
        }

        /**
         * @return {@link #value} (The information determined as a result of making the sub-observation.)
         */
        public Type getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (The information determined as a result of making the sub-observation.)
         */
        public ObservationComponentComponent setValue(Type value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "CodeableConcept", "Identifies what type of sub-observation was performed.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("value[x]", "Quantity|CodeableConcept|Attachment|Ratio|Period|SampledData|string", "The information determined as a result of making the sub-observation.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      public ObservationComponentComponent copy(Observation e) {
        ObservationComponentComponent dst = new ObservationComponentComponent();
        dst.name = name == null ? null : name.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    /**
     * Identifies what type of observation was performed. Sometimes called code.
     */
    protected CodeableConcept name;

    /**
     * The information determined as a result of making the observation.
     */
    protected Type value;

    /**
     * The assessment made based on the result of the observation.
     */
    protected CodeableConcept interpretation;

    /**
     * May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.
     */
    protected String_ comments;

    /**
     * The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the "physiologically relevant time".
     */
    protected Type applies;

    /**
     * Date/Time this was made available.
     */
    protected Instant issued;

    /**
     * The status of the result value.
     */
    protected Enumeration<ObservationStatus> status;

    /**
     * An estimate of the degree to which quality issues have impacted on the value reported.
     */
    protected Enumeration<ObservationReliability> reliability;

    /**
     * Indicates where on the subject's body the observation was made.
     */
    protected CodeableConcept bodySite;

    /**
     * Indicates the mechanism used to perform the observation.
     */
    protected CodeableConcept method;

    /**
     * A unique identifier for the simple observation.
     */
    protected Identifier identifier;

    /**
     * The thing the observation is being made about.
     */
    protected ResourceReference subject;

    /**
     * Who was responsible for asserting the observed value as "true".
     */
    protected ResourceReference performer;

    /**
     * Guidance on how to interpret the value by comparison to a normal or recommended range.
     */
    protected List<ObservationReferenceRangeComponent> referenceRange = new ArrayList<ObservationReferenceRangeComponent>();

    /**
     * Component observation.
     */
    protected List<ObservationComponentComponent> component = new ArrayList<ObservationComponentComponent>();

    public Observation() {
      super();
    }

    public Observation(CodeableConcept name, Enumeration<ObservationStatus> status, Enumeration<ObservationReliability> reliability) {
      super();
      this.name = name;
      this.status = status;
      this.reliability = reliability;
    }

    /**
     * @return {@link #name} (Identifies what type of observation was performed. Sometimes called code.)
     */
    public CodeableConcept getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (Identifies what type of observation was performed. Sometimes called code.)
     */
    public Observation setName(CodeableConcept value) { 
      this.name = value;
      return this;
    }

    /**
     * @return {@link #value} (The information determined as a result of making the observation.)
     */
    public Type getValue() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (The information determined as a result of making the observation.)
     */
    public Observation setValue(Type value) { 
      this.value = value;
      return this;
    }

    /**
     * @return {@link #interpretation} (The assessment made based on the result of the observation.)
     */
    public CodeableConcept getInterpretation() { 
      return this.interpretation;
    }

    /**
     * @param value {@link #interpretation} (The assessment made based on the result of the observation.)
     */
    public Observation setInterpretation(CodeableConcept value) { 
      this.interpretation = value;
      return this;
    }

    /**
     * @return {@link #comments} (May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.)
     */
    public String_ getComments() { 
      return this.comments;
    }

    /**
     * @param value {@link #comments} (May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.)
     */
    public Observation setComments(String_ value) { 
      this.comments = value;
      return this;
    }

    /**
     * @return May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.
     */
    public String getCommentsSimple() { 
      return this.comments == null ? null : this.comments.getValue();
    }

    /**
     * @param value May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.
     */
    public Observation setCommentsSimple(String value) { 
      if (value == null)
        this.comments = null;
      else {
        if (this.comments == null)
          this.comments = new String_();
        this.comments.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #applies} (The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the "physiologically relevant time".)
     */
    public Type getApplies() { 
      return this.applies;
    }

    /**
     * @param value {@link #applies} (The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the "physiologically relevant time".)
     */
    public Observation setApplies(Type value) { 
      this.applies = value;
      return this;
    }

    /**
     * @return {@link #issued} (Date/Time this was made available.)
     */
    public Instant getIssued() { 
      return this.issued;
    }

    /**
     * @param value {@link #issued} (Date/Time this was made available.)
     */
    public Observation setIssued(Instant value) { 
      this.issued = value;
      return this;
    }

    /**
     * @return Date/Time this was made available.
     */
    public Calendar getIssuedSimple() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    /**
     * @param value Date/Time this was made available.
     */
    public Observation setIssuedSimple(Calendar value) { 
      if (value == null)
        this.issued = null;
      else {
        if (this.issued == null)
          this.issued = new Instant();
        this.issued.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The status of the result value.)
     */
    public Enumeration<ObservationStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the result value.)
     */
    public Observation setStatus(Enumeration<ObservationStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the result value.
     */
    public ObservationStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the result value.
     */
    public Observation setStatusSimple(ObservationStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ObservationStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #reliability} (An estimate of the degree to which quality issues have impacted on the value reported.)
     */
    public Enumeration<ObservationReliability> getReliability() { 
      return this.reliability;
    }

    /**
     * @param value {@link #reliability} (An estimate of the degree to which quality issues have impacted on the value reported.)
     */
    public Observation setReliability(Enumeration<ObservationReliability> value) { 
      this.reliability = value;
      return this;
    }

    /**
     * @return An estimate of the degree to which quality issues have impacted on the value reported.
     */
    public ObservationReliability getReliabilitySimple() { 
      return this.reliability == null ? null : this.reliability.getValue();
    }

    /**
     * @param value An estimate of the degree to which quality issues have impacted on the value reported.
     */
    public Observation setReliabilitySimple(ObservationReliability value) { 
        if (this.reliability == null)
          this.reliability = new Enumeration<ObservationReliability>();
        this.reliability.setValue(value);
      return this;
    }

    /**
     * @return {@link #bodySite} (Indicates where on the subject's body the observation was made.)
     */
    public CodeableConcept getBodySite() { 
      return this.bodySite;
    }

    /**
     * @param value {@link #bodySite} (Indicates where on the subject's body the observation was made.)
     */
    public Observation setBodySite(CodeableConcept value) { 
      this.bodySite = value;
      return this;
    }

    /**
     * @return {@link #method} (Indicates the mechanism used to perform the observation.)
     */
    public CodeableConcept getMethod() { 
      return this.method;
    }

    /**
     * @param value {@link #method} (Indicates the mechanism used to perform the observation.)
     */
    public Observation setMethod(CodeableConcept value) { 
      this.method = value;
      return this;
    }

    /**
     * @return {@link #identifier} (A unique identifier for the simple observation.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (A unique identifier for the simple observation.)
     */
    public Observation setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #subject} (The thing the observation is being made about.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The thing the observation is being made about.)
     */
    public Observation setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #performer} (Who was responsible for asserting the observed value as "true".)
     */
    public ResourceReference getPerformer() { 
      return this.performer;
    }

    /**
     * @param value {@link #performer} (Who was responsible for asserting the observed value as "true".)
     */
    public Observation setPerformer(ResourceReference value) { 
      this.performer = value;
      return this;
    }

    /**
     * @return {@link #referenceRange} (Guidance on how to interpret the value by comparison to a normal or recommended range.)
     */
    public List<ObservationReferenceRangeComponent> getReferenceRange() { 
      return this.referenceRange;
    }

    // syntactic sugar
    /**
     * @return {@link #referenceRange} (Guidance on how to interpret the value by comparison to a normal or recommended range.)
     */
    public ObservationReferenceRangeComponent addReferenceRange() { 
      ObservationReferenceRangeComponent t = new ObservationReferenceRangeComponent();
      this.referenceRange.add(t);
      return t;
    }

    /**
     * @return {@link #component} (Component observation.)
     */
    public List<ObservationComponentComponent> getComponent() { 
      return this.component;
    }

    // syntactic sugar
    /**
     * @return {@link #component} (Component observation.)
     */
    public ObservationComponentComponent addComponent() { 
      ObservationComponentComponent t = new ObservationComponentComponent();
      this.component.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("name", "CodeableConcept", "Identifies what type of observation was performed. Sometimes called code.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("value[x]", "Quantity|CodeableConcept|Attachment|Ratio|Period|SampledData|string", "The information determined as a result of making the observation.", 0, java.lang.Integer.MAX_VALUE, value));
        childrenList.add(new Property("interpretation", "CodeableConcept", "The assessment made based on the result of the observation.", 0, java.lang.Integer.MAX_VALUE, interpretation));
        childrenList.add(new Property("comments", "string", "May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.", 0, java.lang.Integer.MAX_VALUE, comments));
        childrenList.add(new Property("applies[x]", "Period|dateTime", "The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the 'physiologically relevant time'.", 0, java.lang.Integer.MAX_VALUE, applies));
        childrenList.add(new Property("issued", "instant", "Date/Time this was made available.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("status", "code", "The status of the result value.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("reliability", "code", "An estimate of the degree to which quality issues have impacted on the value reported.", 0, java.lang.Integer.MAX_VALUE, reliability));
        childrenList.add(new Property("bodySite", "CodeableConcept", "Indicates where on the subject's body the observation was made.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("method", "CodeableConcept", "Indicates the mechanism used to perform the observation.", 0, java.lang.Integer.MAX_VALUE, method));
        childrenList.add(new Property("identifier", "Identifier", "A unique identifier for the simple observation.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device)", "The thing the observation is being made about.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("performer", "Resource(Practitioner|Device|Organization)", "Who was responsible for asserting the observed value as 'true'.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("referenceRange", "", "Guidance on how to interpret the value by comparison to a normal or recommended range.", 0, java.lang.Integer.MAX_VALUE, referenceRange));
        childrenList.add(new Property("component", "", "Component observation.", 0, java.lang.Integer.MAX_VALUE, component));
      }

      public Observation copy() {
        Observation dst = new Observation();
        dst.name = name == null ? null : name.copy();
        dst.value = value == null ? null : value.copy();
        dst.interpretation = interpretation == null ? null : interpretation.copy();
        dst.comments = comments == null ? null : comments.copy();
        dst.applies = applies == null ? null : applies.copy();
        dst.issued = issued == null ? null : issued.copy();
        dst.status = status == null ? null : status.copy();
        dst.reliability = reliability == null ? null : reliability.copy();
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.method = method == null ? null : method.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.performer = performer == null ? null : performer.copy();
        dst.referenceRange = new ArrayList<ObservationReferenceRangeComponent>();
        for (ObservationReferenceRangeComponent i : referenceRange)
          dst.referenceRange.add(i.copy(dst));
        dst.component = new ArrayList<ObservationComponentComponent>();
        for (ObservationComponentComponent i : component)
          dst.component.add(i.copy(dst));
        return dst;
      }

      protected Observation typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Observation;
   }


}

