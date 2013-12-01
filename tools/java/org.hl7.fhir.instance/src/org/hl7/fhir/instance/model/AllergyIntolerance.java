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

// Generated on Sun, Dec 1, 2013 22:52+1100 for FHIR v0.12

import java.util.*;

/**
 * Allergy/Intolerance.
 */
public class AllergyIntolerance extends Resource {

    public enum Criticality {
        fatal, // Likely to result in death if re-exposed.
        high, // Likely to result in reactions that will need to be treated if re-exposed.
        medium, // Likely to result in reactions that will inconvenience the subject.
        low, // Not likely to result in any inconveniences for the subject.
        Null; // added to help the parsers
        public static Criticality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("fatal".equals(codeString))
          return fatal;
        if ("high".equals(codeString))
          return high;
        if ("medium".equals(codeString))
          return medium;
        if ("low".equals(codeString))
          return low;
        throw new Exception("Unknown Criticality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case fatal: return "fatal";
            case high: return "high";
            case medium: return "medium";
            case low: return "low";
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
          return Criticality.fatal;
        if ("high".equals(codeString))
          return Criticality.high;
        if ("medium".equals(codeString))
          return Criticality.medium;
        if ("low".equals(codeString))
          return Criticality.low;
        throw new Exception("Unknown Criticality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Criticality.fatal)
        return "fatal";
      if (code == Criticality.high)
        return "high";
      if (code == Criticality.medium)
        return "medium";
      if (code == Criticality.low)
        return "low";
      return "?";
      }
    }

    public enum Sensitivitytype {
        allergy, // Allergic Reaction.
        intolerance, // Non-Allergic Reaction.
        unknown, // Unknown type.
        Null; // added to help the parsers
        public static Sensitivitytype fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("allergy".equals(codeString))
          return allergy;
        if ("intolerance".equals(codeString))
          return intolerance;
        if ("unknown".equals(codeString))
          return unknown;
        throw new Exception("Unknown Sensitivitytype code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case allergy: return "allergy";
            case intolerance: return "intolerance";
            case unknown: return "unknown";
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
          return Sensitivitytype.allergy;
        if ("intolerance".equals(codeString))
          return Sensitivitytype.intolerance;
        if ("unknown".equals(codeString))
          return Sensitivitytype.unknown;
        throw new Exception("Unknown Sensitivitytype code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Sensitivitytype.allergy)
        return "allergy";
      if (code == Sensitivitytype.intolerance)
        return "intolerance";
      if (code == Sensitivitytype.unknown)
        return "unknown";
      return "?";
      }
    }

    public enum Sensitivitystatus {
        suspected, // A suspected sensitivity to a substance.
        confirmed, // The sensitivity has been confirmed and is active.
        refuted, // The sensitivity has been shown to never have existed.
        resolved, // The sensitivity used to exist but no longer does.
        Null; // added to help the parsers
        public static Sensitivitystatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("suspected".equals(codeString))
          return suspected;
        if ("confirmed".equals(codeString))
          return confirmed;
        if ("refuted".equals(codeString))
          return refuted;
        if ("resolved".equals(codeString))
          return resolved;
        throw new Exception("Unknown Sensitivitystatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case suspected: return "suspected";
            case confirmed: return "confirmed";
            case refuted: return "refuted";
            case resolved: return "resolved";
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
          return Sensitivitystatus.suspected;
        if ("confirmed".equals(codeString))
          return Sensitivitystatus.confirmed;
        if ("refuted".equals(codeString))
          return Sensitivitystatus.refuted;
        if ("resolved".equals(codeString))
          return Sensitivitystatus.resolved;
        throw new Exception("Unknown Sensitivitystatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Sensitivitystatus.suspected)
        return "suspected";
      if (code == Sensitivitystatus.confirmed)
        return "confirmed";
      if (code == Sensitivitystatus.refuted)
        return "refuted";
      if (code == Sensitivitystatus.resolved)
        return "resolved";
      return "?";
      }
    }

    /**
     * This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
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
    protected DateTime recordedDate;

    /**
     * Status of the sensitivity.
     */
    protected Enumeration<Sensitivitystatus> status;

    /**
     * Who the sensitivity is for.
     */
    protected ResourceReference subject;

    /**
     * Who recorded the sensitivity.
     */
    protected ResourceReference recorder;

    /**
     * The substance that causes the sensitivity.
     */
    protected ResourceReference substance;

    /**
     * Reactions associated with the sensitivity.
     */
    protected List<ResourceReference> reaction = new ArrayList<ResourceReference>();

    /**
     * Observations that confirm or refute the sensitivity.
     */
    protected List<ResourceReference> sensitivityTest = new ArrayList<ResourceReference>();

    public AllergyIntolerance() {
      super();
    }

    public AllergyIntolerance(Enumeration<Sensitivitytype> sensitivityType, Enumeration<Sensitivitystatus> status, ResourceReference subject, ResourceReference substance) {
      super();
      this.sensitivityType = sensitivityType;
      this.status = status;
      this.subject = subject;
      this.substance = substance;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #criticality} (Criticality of the sensitivity.)
     */
    public Enumeration<Criticality> getCriticality() { 
      return this.criticality;
    }

    /**
     * @param value {@link #criticality} (Criticality of the sensitivity.)
     */
    public AllergyIntolerance setCriticality(Enumeration<Criticality> value) { 
      this.criticality = value;
      return this;
    }

    /**
     * @return Criticality of the sensitivity.
     */
    public Criticality getCriticalitySimple() { 
      return this.criticality == null ? null : this.criticality.getValue();
    }

    /**
     * @param value Criticality of the sensitivity.
     */
    public AllergyIntolerance setCriticalitySimple(Criticality value) { 
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
     * @return {@link #sensitivityType} (Type of the sensitivity.)
     */
    public Enumeration<Sensitivitytype> getSensitivityType() { 
      return this.sensitivityType;
    }

    /**
     * @param value {@link #sensitivityType} (Type of the sensitivity.)
     */
    public AllergyIntolerance setSensitivityType(Enumeration<Sensitivitytype> value) { 
      this.sensitivityType = value;
      return this;
    }

    /**
     * @return Type of the sensitivity.
     */
    public Sensitivitytype getSensitivityTypeSimple() { 
      return this.sensitivityType == null ? null : this.sensitivityType.getValue();
    }

    /**
     * @param value Type of the sensitivity.
     */
    public AllergyIntolerance setSensitivityTypeSimple(Sensitivitytype value) { 
        if (this.sensitivityType == null)
          this.sensitivityType = new Enumeration<Sensitivitytype>();
        this.sensitivityType.setValue(value);
      return this;
    }

    /**
     * @return {@link #recordedDate} (Date when the sensitivity was recorded.)
     */
    public DateTime getRecordedDate() { 
      return this.recordedDate;
    }

    /**
     * @param value {@link #recordedDate} (Date when the sensitivity was recorded.)
     */
    public AllergyIntolerance setRecordedDate(DateTime value) { 
      this.recordedDate = value;
      return this;
    }

    /**
     * @return Date when the sensitivity was recorded.
     */
    public String getRecordedDateSimple() { 
      return this.recordedDate == null ? null : this.recordedDate.getValue();
    }

    /**
     * @param value Date when the sensitivity was recorded.
     */
    public AllergyIntolerance setRecordedDateSimple(String value) { 
      if (value == null)
        this.recordedDate = null;
      else {
        if (this.recordedDate == null)
          this.recordedDate = new DateTime();
        this.recordedDate.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (Status of the sensitivity.)
     */
    public Enumeration<Sensitivitystatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Status of the sensitivity.)
     */
    public AllergyIntolerance setStatus(Enumeration<Sensitivitystatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Status of the sensitivity.
     */
    public Sensitivitystatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Status of the sensitivity.
     */
    public AllergyIntolerance setStatusSimple(Sensitivitystatus value) { 
        if (this.status == null)
          this.status = new Enumeration<Sensitivitystatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #subject} (Who the sensitivity is for.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who the sensitivity is for.)
     */
    public AllergyIntolerance setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #recorder} (Who recorded the sensitivity.)
     */
    public ResourceReference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Who recorded the sensitivity.)
     */
    public AllergyIntolerance setRecorder(ResourceReference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #substance} (The substance that causes the sensitivity.)
     */
    public ResourceReference getSubstance() { 
      return this.substance;
    }

    /**
     * @param value {@link #substance} (The substance that causes the sensitivity.)
     */
    public AllergyIntolerance setSubstance(ResourceReference value) { 
      this.substance = value;
      return this;
    }

    /**
     * @return {@link #reaction} (Reactions associated with the sensitivity.)
     */
    public List<ResourceReference> getReaction() { 
      return this.reaction;
    }

    // syntactic sugar
    /**
     * @return {@link #reaction} (Reactions associated with the sensitivity.)
     */
    public ResourceReference addReaction() { 
      ResourceReference t = new ResourceReference();
      this.reaction.add(t);
      return t;
    }

    /**
     * @return {@link #sensitivityTest} (Observations that confirm or refute the sensitivity.)
     */
    public List<ResourceReference> getSensitivityTest() { 
      return this.sensitivityTest;
    }

    // syntactic sugar
    /**
     * @return {@link #sensitivityTest} (Observations that confirm or refute the sensitivity.)
     */
    public ResourceReference addSensitivityTest() { 
      ResourceReference t = new ResourceReference();
      this.sensitivityTest.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("criticality", "code", "Criticality of the sensitivity.", 0, java.lang.Integer.MAX_VALUE, criticality));
        childrenList.add(new Property("sensitivityType", "code", "Type of the sensitivity.", 0, java.lang.Integer.MAX_VALUE, sensitivityType));
        childrenList.add(new Property("recordedDate", "dateTime", "Date when the sensitivity was recorded.", 0, java.lang.Integer.MAX_VALUE, recordedDate));
        childrenList.add(new Property("status", "code", "Status of the sensitivity.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("subject", "Resource(Patient)", "Who the sensitivity is for.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("recorder", "Resource(Practitioner|Patient)", "Who recorded the sensitivity.", 0, java.lang.Integer.MAX_VALUE, recorder));
        childrenList.add(new Property("substance", "Resource(Substance)", "The substance that causes the sensitivity.", 0, java.lang.Integer.MAX_VALUE, substance));
        childrenList.add(new Property("reaction", "Resource(AdverseReaction)", "Reactions associated with the sensitivity.", 0, java.lang.Integer.MAX_VALUE, reaction));
        childrenList.add(new Property("sensitivityTest", "Resource(Observation)", "Observations that confirm or refute the sensitivity.", 0, java.lang.Integer.MAX_VALUE, sensitivityTest));
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
        dst.reaction = new ArrayList<ResourceReference>();
        for (ResourceReference i : reaction)
          dst.reaction.add(i.copy());
        dst.sensitivityTest = new ArrayList<ResourceReference>();
        for (ResourceReference i : sensitivityTest)
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

