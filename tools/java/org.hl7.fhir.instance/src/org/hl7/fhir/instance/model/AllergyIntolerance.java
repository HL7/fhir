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

// Generated on Mon, Oct 13, 2014 08:51+1100 for FHIR v0.3.0

import java.util.*;

/**
 * Indicates the patient has a susceptibility to an adverse reaction upon exposure to a specified substance.
 */
public class AllergyIntolerance extends Resource {

    public enum Criticality {
        FATAL, // Likely to result in death if re-exposed.
        HIGH, // Likely to result in reactions that will need to be treated if re-exposed.
        MEDIUM, // Likely to result in reactions that will inconvenience the subject.
        LOW, // Not likely to result in any inconveniences for the subject.
        NULL; // added to help the parsers
        public static Criticality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("fatal".equals(codeString))
          return FATAL;
        if ("high".equals(codeString))
          return HIGH;
        if ("medium".equals(codeString))
          return MEDIUM;
        if ("low".equals(codeString))
          return LOW;
        throw new Exception("Unknown Criticality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case FATAL: return "fatal";
            case HIGH: return "high";
            case MEDIUM: return "medium";
            case LOW: return "low";
            default: return "?";
          }
        }
    }

  public static class CriticalityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("fatal".equals(codeString))
          return Criticality.FATAL;
        if ("high".equals(codeString))
          return Criticality.HIGH;
        if ("medium".equals(codeString))
          return Criticality.MEDIUM;
        if ("low".equals(codeString))
          return Criticality.LOW;
        throw new Exception("Unknown Criticality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Criticality.FATAL)
        return "fatal";
      if (code == Criticality.HIGH)
        return "high";
      if (code == Criticality.MEDIUM)
        return "medium";
      if (code == Criticality.LOW)
        return "low";
      return "?";
      }
    }

    public enum Sensitivitytype {
        ALLERGY, // Allergic Reaction.
        INTOLERANCE, // Non-Allergic Reaction.
        UNKNOWN, // Unknown type.
        NULL; // added to help the parsers
        public static Sensitivitytype fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("allergy".equals(codeString))
          return ALLERGY;
        if ("intolerance".equals(codeString))
          return INTOLERANCE;
        if ("unknown".equals(codeString))
          return UNKNOWN;
        throw new Exception("Unknown Sensitivitytype code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ALLERGY: return "allergy";
            case INTOLERANCE: return "intolerance";
            case UNKNOWN: return "unknown";
            default: return "?";
          }
        }
    }

  public static class SensitivitytypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("allergy".equals(codeString))
          return Sensitivitytype.ALLERGY;
        if ("intolerance".equals(codeString))
          return Sensitivitytype.INTOLERANCE;
        if ("unknown".equals(codeString))
          return Sensitivitytype.UNKNOWN;
        throw new Exception("Unknown Sensitivitytype code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Sensitivitytype.ALLERGY)
        return "allergy";
      if (code == Sensitivitytype.INTOLERANCE)
        return "intolerance";
      if (code == Sensitivitytype.UNKNOWN)
        return "unknown";
      return "?";
      }
    }

    public enum Sensitivitystatus {
        SUSPECTED, // A suspected sensitivity to a substance.
        CONFIRMED, // The sensitivity has been confirmed and is active.
        REFUTED, // The sensitivity has been shown to never have existed.
        RESOLVED, // The sensitivity used to exist but no longer does.
        NULL; // added to help the parsers
        public static Sensitivitystatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("suspected".equals(codeString))
          return SUSPECTED;
        if ("confirmed".equals(codeString))
          return CONFIRMED;
        if ("refuted".equals(codeString))
          return REFUTED;
        if ("resolved".equals(codeString))
          return RESOLVED;
        throw new Exception("Unknown Sensitivitystatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case SUSPECTED: return "suspected";
            case CONFIRMED: return "confirmed";
            case REFUTED: return "refuted";
            case RESOLVED: return "resolved";
            default: return "?";
          }
        }
    }

  public static class SensitivitystatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("suspected".equals(codeString))
          return Sensitivitystatus.SUSPECTED;
        if ("confirmed".equals(codeString))
          return Sensitivitystatus.CONFIRMED;
        if ("refuted".equals(codeString))
          return Sensitivitystatus.REFUTED;
        if ("resolved".equals(codeString))
          return Sensitivitystatus.RESOLVED;
        throw new Exception("Unknown Sensitivitystatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Sensitivitystatus.SUSPECTED)
        return "suspected";
      if (code == Sensitivitystatus.CONFIRMED)
        return "confirmed";
      if (code == Sensitivitystatus.REFUTED)
        return "refuted";
      if (code == Sensitivitystatus.RESOLVED)
        return "resolved";
      return "?";
      }
    }

    /**
     * This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Criticality of the sensitivity.
     */
    protected Enumeration<Criticality> criticality;

    /**
     * Type of the sensitivity.
     */
    protected Enumeration<Sensitivitytype> sensitivityType;

    /**
     * Date when the sensitivity was recorded.
     */
    protected DateTimeType recordedDate;

    /**
     * Status of the sensitivity.
     */
    protected Enumeration<Sensitivitystatus> status;

    /**
     * The patient who has the allergy or intolerance.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who has the allergy or intolerance.)
     */
    protected Patient subjectTarget;

    /**
     * Indicates who has responsibility for the record.
     */
    protected Reference recorder;

    /**
     * The actual object that is the target of the reference (Indicates who has responsibility for the record.)
     */
    protected Resource recorderTarget;

    /**
     * The substance that causes the sensitivity.
     */
    protected Reference substance;

    /**
     * The actual object that is the target of the reference (The substance that causes the sensitivity.)
     */
    protected Substance substanceTarget;

    /**
     * Reactions associated with the sensitivity.
     */
    protected List<Reference> reaction = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Reactions associated with the sensitivity.)
     */
    protected List<AdverseReaction> reactionTarget = new ArrayList<AdverseReaction>();


    /**
     * Observations that confirm or refute the sensitivity.
     */
    protected List<Reference> sensitivityTest = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Observations that confirm or refute the sensitivity.)
     */
    protected List<Observation> sensitivityTestTarget = new ArrayList<Observation>();


    private static final long serialVersionUID = -880479022L;

    public AllergyIntolerance() {
      super();
    }

    public AllergyIntolerance(Enumeration<Sensitivitytype> sensitivityType, Enumeration<Sensitivitystatus> status, Reference subject, Reference substance) {
      super();
      this.sensitivityType = sensitivityType;
      this.status = status;
      this.subject = subject;
      this.substance = substance;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #criticality} (Criticality of the sensitivity.). This is the underlying object with id, value and extensions. The accessor "getCriticality" gives direct access to the value
     */
    public Enumeration<Criticality> getCriticalityElement() { 
      return this.criticality;
    }

    /**
     * @param value {@link #criticality} (Criticality of the sensitivity.). This is the underlying object with id, value and extensions. The accessor "getCriticality" gives direct access to the value
     */
    public AllergyIntolerance setCriticalityElement(Enumeration<Criticality> value) { 
      this.criticality = value;
      return this;
    }

    /**
     * @return Criticality of the sensitivity.
     */
    public Criticality getCriticality() { 
      return this.criticality == null ? null : this.criticality.getValue();
    }

    /**
     * @param value Criticality of the sensitivity.
     */
    public AllergyIntolerance setCriticality(Criticality value) { 
      if (value == null)
        this.criticality = null;
      else {
        if (this.criticality == null)
          this.criticality = new Enumeration<Criticality>();
        this.criticality.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #sensitivityType} (Type of the sensitivity.). This is the underlying object with id, value and extensions. The accessor "getSensitivityType" gives direct access to the value
     */
    public Enumeration<Sensitivitytype> getSensitivityTypeElement() { 
      return this.sensitivityType;
    }

    /**
     * @param value {@link #sensitivityType} (Type of the sensitivity.). This is the underlying object with id, value and extensions. The accessor "getSensitivityType" gives direct access to the value
     */
    public AllergyIntolerance setSensitivityTypeElement(Enumeration<Sensitivitytype> value) { 
      this.sensitivityType = value;
      return this;
    }

    /**
     * @return Type of the sensitivity.
     */
    public Sensitivitytype getSensitivityType() { 
      return this.sensitivityType == null ? null : this.sensitivityType.getValue();
    }

    /**
     * @param value Type of the sensitivity.
     */
    public AllergyIntolerance setSensitivityType(Sensitivitytype value) { 
        if (this.sensitivityType == null)
          this.sensitivityType = new Enumeration<Sensitivitytype>();
        this.sensitivityType.setValue(value);
      return this;
    }

    /**
     * @return {@link #recordedDate} (Date when the sensitivity was recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedDate" gives direct access to the value
     */
    public DateTimeType getRecordedDateElement() { 
      return this.recordedDate;
    }

    /**
     * @param value {@link #recordedDate} (Date when the sensitivity was recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedDate" gives direct access to the value
     */
    public AllergyIntolerance setRecordedDateElement(DateTimeType value) { 
      this.recordedDate = value;
      return this;
    }

    /**
     * @return Date when the sensitivity was recorded.
     */
    public DateAndTime getRecordedDate() { 
      return this.recordedDate == null ? null : this.recordedDate.getValue();
    }

    /**
     * @param value Date when the sensitivity was recorded.
     */
    public AllergyIntolerance setRecordedDate(DateAndTime value) { 
      if (value == null)
        this.recordedDate = null;
      else {
        if (this.recordedDate == null)
          this.recordedDate = new DateTimeType();
        this.recordedDate.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (Status of the sensitivity.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<Sensitivitystatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Status of the sensitivity.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public AllergyIntolerance setStatusElement(Enumeration<Sensitivitystatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Status of the sensitivity.
     */
    public Sensitivitystatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Status of the sensitivity.
     */
    public AllergyIntolerance setStatus(Sensitivitystatus value) { 
        if (this.status == null)
          this.status = new Enumeration<Sensitivitystatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #subject} (The patient who has the allergy or intolerance.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who has the allergy or intolerance.)
     */
    public AllergyIntolerance setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who has the allergy or intolerance.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who has the allergy or intolerance.)
     */
    public AllergyIntolerance setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #recorder} (Indicates who has responsibility for the record.)
     */
    public Reference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Indicates who has responsibility for the record.)
     */
    public AllergyIntolerance setRecorder(Reference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #recorder} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Indicates who has responsibility for the record.)
     */
    public Resource getRecorderTarget() { 
      return this.recorderTarget;
    }

    /**
     * @param value {@link #recorder} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Indicates who has responsibility for the record.)
     */
    public AllergyIntolerance setRecorderTarget(Resource value) { 
      this.recorderTarget = value;
      return this;
    }

    /**
     * @return {@link #substance} (The substance that causes the sensitivity.)
     */
    public Reference getSubstance() { 
      return this.substance;
    }

    /**
     * @param value {@link #substance} (The substance that causes the sensitivity.)
     */
    public AllergyIntolerance setSubstance(Reference value) { 
      this.substance = value;
      return this;
    }

    /**
     * @return {@link #substance} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The substance that causes the sensitivity.)
     */
    public Substance getSubstanceTarget() { 
      return this.substanceTarget;
    }

    /**
     * @param value {@link #substance} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The substance that causes the sensitivity.)
     */
    public AllergyIntolerance setSubstanceTarget(Substance value) { 
      this.substanceTarget = value;
      return this;
    }

    /**
     * @return {@link #reaction} (Reactions associated with the sensitivity.)
     */
    public List<Reference> getReaction() { 
      return this.reaction;
    }

    // syntactic sugar
    /**
     * @return {@link #reaction} (Reactions associated with the sensitivity.)
     */
    public Reference addReaction() { 
      Reference t = new Reference();
      this.reaction.add(t);
      return t;
    }

    /**
     * @return {@link #reaction} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Reactions associated with the sensitivity.)
     */
    public List<AdverseReaction> getReactionTarget() { 
      return this.reactionTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #reaction} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. Reactions associated with the sensitivity.)
     */
    public AdverseReaction addReactionTarget() { 
      AdverseReaction r = new AdverseReaction();
      this.reactionTarget.add(r);
      return r;
    }

    /**
     * @return {@link #sensitivityTest} (Observations that confirm or refute the sensitivity.)
     */
    public List<Reference> getSensitivityTest() { 
      return this.sensitivityTest;
    }

    // syntactic sugar
    /**
     * @return {@link #sensitivityTest} (Observations that confirm or refute the sensitivity.)
     */
    public Reference addSensitivityTest() { 
      Reference t = new Reference();
      this.sensitivityTest.add(t);
      return t;
    }

    /**
     * @return {@link #sensitivityTest} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Observations that confirm or refute the sensitivity.)
     */
    public List<Observation> getSensitivityTestTarget() { 
      return this.sensitivityTestTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #sensitivityTest} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. Observations that confirm or refute the sensitivity.)
     */
    public Observation addSensitivityTestTarget() { 
      Observation r = new Observation();
      this.sensitivityTestTarget.add(r);
      return r;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("criticality", "code", "Criticality of the sensitivity.", 0, java.lang.Integer.MAX_VALUE, criticality));
        childrenList.add(new Property("sensitivityType", "code", "Type of the sensitivity.", 0, java.lang.Integer.MAX_VALUE, sensitivityType));
        childrenList.add(new Property("recordedDate", "dateTime", "Date when the sensitivity was recorded.", 0, java.lang.Integer.MAX_VALUE, recordedDate));
        childrenList.add(new Property("status", "code", "Status of the sensitivity.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who has the allergy or intolerance.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("recorder", "Reference(Practitioner|Patient)", "Indicates who has responsibility for the record.", 0, java.lang.Integer.MAX_VALUE, recorder));
        childrenList.add(new Property("substance", "Reference(Substance)", "The substance that causes the sensitivity.", 0, java.lang.Integer.MAX_VALUE, substance));
        childrenList.add(new Property("reaction", "Reference(AdverseReaction)", "Reactions associated with the sensitivity.", 0, java.lang.Integer.MAX_VALUE, reaction));
        childrenList.add(new Property("sensitivityTest", "Reference(Observation)", "Observations that confirm or refute the sensitivity.", 0, java.lang.Integer.MAX_VALUE, sensitivityTest));
      }

      public AllergyIntolerance copy() {
        AllergyIntolerance dst = new AllergyIntolerance();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.criticality = criticality == null ? null : criticality.copy();
        dst.sensitivityType = sensitivityType == null ? null : sensitivityType.copy();
        dst.recordedDate = recordedDate == null ? null : recordedDate.copy();
        dst.status = status == null ? null : status.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.recorder = recorder == null ? null : recorder.copy();
        dst.substance = substance == null ? null : substance.copy();
        dst.reaction = new ArrayList<Reference>();
        for (Reference i : reaction)
          dst.reaction.add(i.copy());
        dst.sensitivityTest = new ArrayList<Reference>();
        for (Reference i : sensitivityTest)
          dst.sensitivityTest.add(i.copy());
        return dst;
      }

      protected AllergyIntolerance typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.AllergyIntolerance;
   }


}

