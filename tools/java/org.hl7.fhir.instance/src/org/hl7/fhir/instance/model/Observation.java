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
 * Measurements and simple assertions made about a patient, device or other subject.
 */
public class Observation extends Resource {

    public enum ObservationStatus {
        registered, // The existence of the observation is registered, but there is no result yet available.
        preliminary, // This is an initial or interim observation: data may be incomplete or unverified.
        final_, // The observation is complete and verified by an authorized person.
        amended, // The observation has been modified subsequent to being Final, and is complete and verified by an authorized person.
        cancelled, // The observation is unavailable because the measurement was not started or not completed (also sometimes called "aborted").
        enteredInError, // The observation has been withdrawn following previous Final release.
        Null; // added to help the parsers
        public static ObservationStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return registered;
        if ("preliminary".equals(codeString))
          return preliminary;
        if ("final".equals(codeString))
          return final_;
        if ("amended".equals(codeString))
          return amended;
        if ("cancelled".equals(codeString))
          return cancelled;
        if ("entered in error".equals(codeString))
          return enteredInError;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case registered: return "registered";
            case preliminary: return "preliminary";
            case final_: return "final";
            case amended: return "amended";
            case cancelled: return "cancelled";
            case enteredInError: return "entered in error";
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
        if ("preliminary".equals(codeString))
          return ObservationStatus.preliminary;
        if ("final".equals(codeString))
          return ObservationStatus.final_;
        if ("amended".equals(codeString))
          return ObservationStatus.amended;
        if ("cancelled".equals(codeString))
          return ObservationStatus.cancelled;
        if ("entered in error".equals(codeString))
          return ObservationStatus.enteredInError;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationStatus.registered)
        return "registered";
      if (code == ObservationStatus.preliminary)
        return "preliminary";
      if (code == ObservationStatus.final_)
        return "final";
      if (code == ObservationStatus.amended)
        return "amended";
      if (code == ObservationStatus.cancelled)
        return "cancelled";
      if (code == ObservationStatus.enteredInError)
        return "entered in error";
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

    public enum ObservationRelationshiptypes {
        hascomponent, // The target observation is a component of this observation (e.g. Systolic and Diastolic Blood Pressure).
        hasmember, // This observation is a group observation (e.g. a battery, a panel of tests, a set of vital sign measurements) that includes the target as a member of the group.
        derivedfrom, // The target observation is part of the information from which this observation value is derived (e.g. calculated anion gap, Apgar score).
        sequelto, // This observation follows the target observation (e.g. timed tests such as Glucose Tolerance Test).
        replaces, // This observation replaces a previous observation (i.e. a revised value). The target observation is now obsolete.
        qualifiedby, // The value of the target observation qualifies (refines) the semantics of the source observation (e.g. a lipaemia measure target from a plasma measure).
        interferedby, // The value of the target observation interferes (degardes quality, or prevents valid observation) with the semantics of the source observation (e.g. a hemolysis measure target from a plasma potassium measure which has no value).
        Null; // added to help the parsers
        public static ObservationRelationshiptypes fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("has-component".equals(codeString))
          return hascomponent;
        if ("has-member".equals(codeString))
          return hasmember;
        if ("derived-from".equals(codeString))
          return derivedfrom;
        if ("sequel-to".equals(codeString))
          return sequelto;
        if ("replaces".equals(codeString))
          return replaces;
        if ("qualified-by".equals(codeString))
          return qualifiedby;
        if ("interfered-by".equals(codeString))
          return interferedby;
        throw new Exception("Unknown ObservationRelationshiptypes code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case hascomponent: return "has-component";
            case hasmember: return "has-member";
            case derivedfrom: return "derived-from";
            case sequelto: return "sequel-to";
            case replaces: return "replaces";
            case qualifiedby: return "qualified-by";
            case interferedby: return "interfered-by";
            default: return "?";
          }
        }
    }

  public static class ObservationRelationshiptypesEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("has-component".equals(codeString))
          return ObservationRelationshiptypes.hascomponent;
        if ("has-member".equals(codeString))
          return ObservationRelationshiptypes.hasmember;
        if ("derived-from".equals(codeString))
          return ObservationRelationshiptypes.derivedfrom;
        if ("sequel-to".equals(codeString))
          return ObservationRelationshiptypes.sequelto;
        if ("replaces".equals(codeString))
          return ObservationRelationshiptypes.replaces;
        if ("qualified-by".equals(codeString))
          return ObservationRelationshiptypes.qualifiedby;
        if ("interfered-by".equals(codeString))
          return ObservationRelationshiptypes.interferedby;
        throw new Exception("Unknown ObservationRelationshiptypes code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationRelationshiptypes.hascomponent)
        return "has-component";
      if (code == ObservationRelationshiptypes.hasmember)
        return "has-member";
      if (code == ObservationRelationshiptypes.derivedfrom)
        return "derived-from";
      if (code == ObservationRelationshiptypes.sequelto)
        return "sequel-to";
      if (code == ObservationRelationshiptypes.replaces)
        return "replaces";
      if (code == ObservationRelationshiptypes.qualifiedby)
        return "qualified-by";
      if (code == ObservationRelationshiptypes.interferedby)
        return "interfered-by";
      return "?";
      }
    }

    public static class ObservationReferenceRangeComponent extends BackboneElement {
        /**
         * The value of the low bound of the reference range. If this is omitted, the low bound of the reference range is assumed to be meaningless. E.g. <2.3.
         */
        protected Quantity low;

        /**
         * The value of the high bound of the reference range. If this is omitted, the high bound of the reference range is assumed to be meaningless. E.g. >5.
         */
        protected Quantity high;

        /**
         * Code for the meaning of the reference range.
         */
        protected CodeableConcept meaning;

        /**
         * The age at which this reference range is applicable. This is a neonatal age (e.g. number of weeks at term) if the meaning says so.
         */
        protected Range age;

        /**
         * Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.
         */
        protected String_ text;

        private static final long serialVersionUID = 801418099L;

      public ObservationReferenceRangeComponent() {
        super();
      }

        /**
         * @return {@link #low} (The value of the low bound of the reference range. If this is omitted, the low bound of the reference range is assumed to be meaningless. E.g. <2.3.)
         */
        public Quantity getLow() { 
          return this.low;
        }

        /**
         * @param value {@link #low} (The value of the low bound of the reference range. If this is omitted, the low bound of the reference range is assumed to be meaningless. E.g. <2.3.)
         */
        public ObservationReferenceRangeComponent setLow(Quantity value) { 
          this.low = value;
          return this;
        }

        /**
         * @return {@link #high} (The value of the high bound of the reference range. If this is omitted, the high bound of the reference range is assumed to be meaningless. E.g. >5.)
         */
        public Quantity getHigh() { 
          return this.high;
        }

        /**
         * @param value {@link #high} (The value of the high bound of the reference range. If this is omitted, the high bound of the reference range is assumed to be meaningless. E.g. >5.)
         */
        public ObservationReferenceRangeComponent setHigh(Quantity value) { 
          this.high = value;
          return this;
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
         * @return {@link #age} (The age at which this reference range is applicable. This is a neonatal age (e.g. number of weeks at term) if the meaning says so.)
         */
        public Range getAge() { 
          return this.age;
        }

        /**
         * @param value {@link #age} (The age at which this reference range is applicable. This is a neonatal age (e.g. number of weeks at term) if the meaning says so.)
         */
        public ObservationReferenceRangeComponent setAge(Range value) { 
          this.age = value;
          return this;
        }

        /**
         * @return {@link #text} (Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.)
         */
        public String_ getText() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.)
         */
        public ObservationReferenceRangeComponent setText(String_ value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.
         */
        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.
         */
        public ObservationReferenceRangeComponent setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("low", "Quantity", "The value of the low bound of the reference range. If this is omitted, the low bound of the reference range is assumed to be meaningless. E.g. <2.3.", 0, java.lang.Integer.MAX_VALUE, low));
          childrenList.add(new Property("high", "Quantity", "The value of the high bound of the reference range. If this is omitted, the high bound of the reference range is assumed to be meaningless. E.g. >5.", 0, java.lang.Integer.MAX_VALUE, high));
          childrenList.add(new Property("meaning", "CodeableConcept", "Code for the meaning of the reference range.", 0, java.lang.Integer.MAX_VALUE, meaning));
          childrenList.add(new Property("age", "Range", "The age at which this reference range is applicable. This is a neonatal age (e.g. number of weeks at term) if the meaning says so.", 0, java.lang.Integer.MAX_VALUE, age));
          childrenList.add(new Property("text", "string", "Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of 'Negative' or a list or table of 'normals'.", 0, java.lang.Integer.MAX_VALUE, text));
        }

      public ObservationReferenceRangeComponent copy() {
        ObservationReferenceRangeComponent dst = new ObservationReferenceRangeComponent();
        dst.low = low == null ? null : low.copy();
        dst.high = high == null ? null : high.copy();
        dst.meaning = meaning == null ? null : meaning.copy();
        dst.age = age == null ? null : age.copy();
        dst.text = text == null ? null : text.copy();
        return dst;
      }

  }

    public static class ObservationRelatedComponent extends BackboneElement {
        /**
         * A code specifying the kind of relationship that exists with the target observation.
         */
        protected Enumeration<ObservationRelationshiptypes> type;

        /**
         * A reference to the observation that is related to this observation.
         */
        protected ResourceReference target;

        /**
         * The actual object that is the target of the reference (A reference to the observation that is related to this observation.)
         */
        protected Observation targetTarget;

        private static final long serialVersionUID = -984646850L;

      public ObservationRelatedComponent() {
        super();
      }

      public ObservationRelatedComponent(ResourceReference target) {
        super();
        this.target = target;
      }

        /**
         * @return {@link #type} (A code specifying the kind of relationship that exists with the target observation.)
         */
        public Enumeration<ObservationRelationshiptypes> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (A code specifying the kind of relationship that exists with the target observation.)
         */
        public ObservationRelatedComponent setType(Enumeration<ObservationRelationshiptypes> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return A code specifying the kind of relationship that exists with the target observation.
         */
        public ObservationRelationshiptypes getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value A code specifying the kind of relationship that exists with the target observation.
         */
        public ObservationRelatedComponent setTypeSimple(ObservationRelationshiptypes value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<ObservationRelationshiptypes>();
            this.type.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #target} (A reference to the observation that is related to this observation.)
         */
        public ResourceReference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (A reference to the observation that is related to this observation.)
         */
        public ObservationRelatedComponent setTarget(ResourceReference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} (The actual object that is the target of the reference. A reference to the observation that is related to this observation.)
         */
        public Observation getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} (The actual object that is the target of the reference. A reference to the observation that is related to this observation.)
         */
        public ObservationRelatedComponent setTargetTarget(Observation value) { 
          this.targetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "A code specifying the kind of relationship that exists with the target observation.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("target", "Resource(Observation)", "A reference to the observation that is related to this observation.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ObservationRelatedComponent copy() {
        ObservationRelatedComponent dst = new ObservationRelatedComponent();
        dst.type = type == null ? null : type.copy();
        dst.target = target == null ? null : target.copy();
        return dst;
      }

  }

    /**
     * Describes what was observed. Sometimes this is called the observation "code".
     */
    protected CodeableConcept name;

    /**
     * The information determined as a result of making the observation, if the information has a simple value.
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
     * The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the "physiologically relevant time". This is usually either the time of the procedure or of specimen collection, but very often the source of the date/time is not known, only the date/time itself.
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
     * The actual object that is the target of the reference (The thing the observation is being made about.)
     */
    protected Resource subjectTarget;

    /**
     * The specimen that was used when this observation was made.
     */
    protected ResourceReference specimen;

    /**
     * The actual object that is the target of the reference (The specimen that was used when this observation was made.)
     */
    protected Specimen specimenTarget;

    /**
     * Who was responsible for asserting the observed value as "true".
     */
    protected List<ResourceReference> performer = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Who was responsible for asserting the observed value as "true".)
     */
    protected List<Resource> performerTarget = new ArrayList<Resource>();


    /**
     * Guidance on how to interpret the value by comparison to a normal or recommended range.
     */
    protected List<ObservationReferenceRangeComponent> referenceRange = new ArrayList<ObservationReferenceRangeComponent>();

    /**
     * Related observations - either components, or previous observations, or statements of derivation.
     */
    protected List<ObservationRelatedComponent> related = new ArrayList<ObservationRelatedComponent>();

    private static final long serialVersionUID = -757546248L;

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
     * @return {@link #name} (Describes what was observed. Sometimes this is called the observation "code".)
     */
    public CodeableConcept getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (Describes what was observed. Sometimes this is called the observation "code".)
     */
    public Observation setName(CodeableConcept value) { 
      this.name = value;
      return this;
    }

    /**
     * @return {@link #value} (The information determined as a result of making the observation, if the information has a simple value.)
     */
    public Type getValue() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (The information determined as a result of making the observation, if the information has a simple value.)
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
     * @return {@link #applies} (The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the "physiologically relevant time". This is usually either the time of the procedure or of specimen collection, but very often the source of the date/time is not known, only the date/time itself.)
     */
    public Type getApplies() { 
      return this.applies;
    }

    /**
     * @param value {@link #applies} (The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the "physiologically relevant time". This is usually either the time of the procedure or of specimen collection, but very often the source of the date/time is not known, only the date/time itself.)
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
    public DateAndTime getIssuedSimple() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    /**
     * @param value Date/Time this was made available.
     */
    public Observation setIssuedSimple(DateAndTime value) { 
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
     * @return {@link #subject} (The actual object that is the target of the reference. The thing the observation is being made about.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The thing the observation is being made about.)
     */
    public Observation setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #specimen} (The specimen that was used when this observation was made.)
     */
    public ResourceReference getSpecimen() { 
      return this.specimen;
    }

    /**
     * @param value {@link #specimen} (The specimen that was used when this observation was made.)
     */
    public Observation setSpecimen(ResourceReference value) { 
      this.specimen = value;
      return this;
    }

    /**
     * @return {@link #specimen} (The actual object that is the target of the reference. The specimen that was used when this observation was made.)
     */
    public Specimen getSpecimenTarget() { 
      return this.specimenTarget;
    }

    /**
     * @param value {@link #specimen} (The actual object that is the target of the reference. The specimen that was used when this observation was made.)
     */
    public Observation setSpecimenTarget(Specimen value) { 
      this.specimenTarget = value;
      return this;
    }

    /**
     * @return {@link #performer} (Who was responsible for asserting the observed value as "true".)
     */
    public List<ResourceReference> getPerformer() { 
      return this.performer;
    }

    // syntactic sugar
    /**
     * @return {@link #performer} (Who was responsible for asserting the observed value as "true".)
     */
    public ResourceReference addPerformer() { 
      ResourceReference t = new ResourceReference();
      this.performer.add(t);
      return t;
    }

    /**
     * @return {@link #performer} (The actual objects that are the target of the reference. Who was responsible for asserting the observed value as "true".)
     */
    public List<Resource> getPerformerTarget() { 
      return this.performerTarget;
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
     * @return {@link #related} (Related observations - either components, or previous observations, or statements of derivation.)
     */
    public List<ObservationRelatedComponent> getRelated() { 
      return this.related;
    }

    // syntactic sugar
    /**
     * @return {@link #related} (Related observations - either components, or previous observations, or statements of derivation.)
     */
    public ObservationRelatedComponent addRelated() { 
      ObservationRelatedComponent t = new ObservationRelatedComponent();
      this.related.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("name", "CodeableConcept", "Describes what was observed. Sometimes this is called the observation 'code'.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("value[x]", "Quantity|CodeableConcept|Attachment|Ratio|dateTime|Period|SampledData|string", "The information determined as a result of making the observation, if the information has a simple value.", 0, java.lang.Integer.MAX_VALUE, value));
        childrenList.add(new Property("interpretation", "CodeableConcept", "The assessment made based on the result of the observation.", 0, java.lang.Integer.MAX_VALUE, interpretation));
        childrenList.add(new Property("comments", "string", "May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.", 0, java.lang.Integer.MAX_VALUE, comments));
        childrenList.add(new Property("applies[x]", "dateTime|Period", "The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the 'physiologically relevant time'. This is usually either the time of the procedure or of specimen collection, but very often the source of the date/time is not known, only the date/time itself.", 0, java.lang.Integer.MAX_VALUE, applies));
        childrenList.add(new Property("issued", "instant", "Date/Time this was made available.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("status", "code", "The status of the result value.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("reliability", "code", "An estimate of the degree to which quality issues have impacted on the value reported.", 0, java.lang.Integer.MAX_VALUE, reliability));
        childrenList.add(new Property("bodySite", "CodeableConcept", "Indicates where on the subject's body the observation was made.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("method", "CodeableConcept", "Indicates the mechanism used to perform the observation.", 0, java.lang.Integer.MAX_VALUE, method));
        childrenList.add(new Property("identifier", "Identifier", "A unique identifier for the simple observation.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device|Location)", "The thing the observation is being made about.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("specimen", "Resource(Specimen)", "The specimen that was used when this observation was made.", 0, java.lang.Integer.MAX_VALUE, specimen));
        childrenList.add(new Property("performer", "Resource(Practitioner|Device|Organization|Patient)", "Who was responsible for asserting the observed value as 'true'.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("referenceRange", "", "Guidance on how to interpret the value by comparison to a normal or recommended range.", 0, java.lang.Integer.MAX_VALUE, referenceRange));
        childrenList.add(new Property("related", "", "Related observations - either components, or previous observations, or statements of derivation.", 0, java.lang.Integer.MAX_VALUE, related));
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
        dst.specimen = specimen == null ? null : specimen.copy();
        dst.performer = new ArrayList<ResourceReference>();
        for (ResourceReference i : performer)
          dst.performer.add(i.copy());
        dst.referenceRange = new ArrayList<ObservationReferenceRangeComponent>();
        for (ObservationReferenceRangeComponent i : referenceRange)
          dst.referenceRange.add(i.copy());
        dst.related = new ArrayList<ObservationRelatedComponent>();
        for (ObservationRelatedComponent i : related)
          dst.related.add(i.copy());
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

