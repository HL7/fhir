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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * Measurements and simple assertions made about a patient, device or other subject.
 */
public class Observation extends DomainResource {

    public enum DataAbsentReason {
        UNKNOWN, // The value is not known
        ASKED, // The source human does not know the value
        TEMP, // There is reason to expect (from the workflow) that the value may become known
        NOTASKED, // The workflow didn't lead to this value being known
        MASKED, // The information is not available due to security, privacy or related reasons
        UNSUPPORTED, // The source system wasn't capable of supporting this element
        ASTEXT, // The content of the data is represented in the resource narrative
        ERROR, // Some system or workflow process error means that the information is not available
        NULL; // added to help the parsers
        public static DataAbsentReason fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("unknown".equals(codeString))
          return UNKNOWN;
        if ("asked".equals(codeString))
          return ASKED;
        if ("temp".equals(codeString))
          return TEMP;
        if ("notasked".equals(codeString))
          return NOTASKED;
        if ("masked".equals(codeString))
          return MASKED;
        if ("unsupported".equals(codeString))
          return UNSUPPORTED;
        if ("astext".equals(codeString))
          return ASTEXT;
        if ("error".equals(codeString))
          return ERROR;
        throw new Exception("Unknown DataAbsentReason code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case UNKNOWN: return "unknown";
            case ASKED: return "asked";
            case TEMP: return "temp";
            case NOTASKED: return "notasked";
            case MASKED: return "masked";
            case UNSUPPORTED: return "unsupported";
            case ASTEXT: return "astext";
            case ERROR: return "error";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case UNKNOWN: return "The value is not known";
            case ASKED: return "The source human does not know the value";
            case TEMP: return "There is reason to expect (from the workflow) that the value may become known";
            case NOTASKED: return "The workflow didn't lead to this value being known";
            case MASKED: return "The information is not available due to security, privacy or related reasons";
            case UNSUPPORTED: return "The source system wasn't capable of supporting this element";
            case ASTEXT: return "The content of the data is represented in the resource narrative";
            case ERROR: return "Some system or workflow process error means that the information is not available";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case UNKNOWN: return "unknown";
            case ASKED: return "asked";
            case TEMP: return "temp";
            case NOTASKED: return "notasked";
            case MASKED: return "masked";
            case UNSUPPORTED: return "unsupported";
            case ASTEXT: return "astext";
            case ERROR: return "error";
            default: return "?";
          }
        }
    }

  public static class DataAbsentReasonEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("unknown".equals(codeString))
          return DataAbsentReason.UNKNOWN;
        if ("asked".equals(codeString))
          return DataAbsentReason.ASKED;
        if ("temp".equals(codeString))
          return DataAbsentReason.TEMP;
        if ("notasked".equals(codeString))
          return DataAbsentReason.NOTASKED;
        if ("masked".equals(codeString))
          return DataAbsentReason.MASKED;
        if ("unsupported".equals(codeString))
          return DataAbsentReason.UNSUPPORTED;
        if ("astext".equals(codeString))
          return DataAbsentReason.ASTEXT;
        if ("error".equals(codeString))
          return DataAbsentReason.ERROR;
        throw new Exception("Unknown DataAbsentReason code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DataAbsentReason.UNKNOWN)
        return "unknown";
      if (code == DataAbsentReason.ASKED)
        return "asked";
      if (code == DataAbsentReason.TEMP)
        return "temp";
      if (code == DataAbsentReason.NOTASKED)
        return "notasked";
      if (code == DataAbsentReason.MASKED)
        return "masked";
      if (code == DataAbsentReason.UNSUPPORTED)
        return "unsupported";
      if (code == DataAbsentReason.ASTEXT)
        return "astext";
      if (code == DataAbsentReason.ERROR)
        return "error";
      return "?";
      }
    }

    public enum ObservationStatus {
        REGISTERED, // The existence of the observation is registered, but there is no result yet available.
        PRELIMINARY, // This is an initial or interim observation: data may be incomplete or unverified.
        FINAL, // The observation is complete and verified by an authorized person.
        AMENDED, // The observation has been modified subsequent to being Final, and is complete and verified by an authorized person.
        CANCELLED, // The observation is unavailable because the measurement was not started or not completed (also sometimes called "aborted").
        ENTEREDINERROR, // The observation has been withdrawn following previous Final release.
        NULL; // added to help the parsers
        public static ObservationStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return REGISTERED;
        if ("preliminary".equals(codeString))
          return PRELIMINARY;
        if ("final".equals(codeString))
          return FINAL;
        if ("amended".equals(codeString))
          return AMENDED;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        if ("entered in error".equals(codeString))
          return ENTEREDINERROR;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REGISTERED: return "registered";
            case PRELIMINARY: return "preliminary";
            case FINAL: return "final";
            case AMENDED: return "amended";
            case CANCELLED: return "cancelled";
            case ENTEREDINERROR: return "entered in error";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REGISTERED: return "The existence of the observation is registered, but there is no result yet available.";
            case PRELIMINARY: return "This is an initial or interim observation: data may be incomplete or unverified.";
            case FINAL: return "The observation is complete and verified by an authorized person.";
            case AMENDED: return "The observation has been modified subsequent to being Final, and is complete and verified by an authorized person.";
            case CANCELLED: return "The observation is unavailable because the measurement was not started or not completed (also sometimes called 'aborted').";
            case ENTEREDINERROR: return "The observation has been withdrawn following previous Final release.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REGISTERED: return "Registered";
            case PRELIMINARY: return "preliminary";
            case FINAL: return "final";
            case AMENDED: return "amended";
            case CANCELLED: return "cancelled";
            case ENTEREDINERROR: return "entered in error";
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
          return ObservationStatus.REGISTERED;
        if ("preliminary".equals(codeString))
          return ObservationStatus.PRELIMINARY;
        if ("final".equals(codeString))
          return ObservationStatus.FINAL;
        if ("amended".equals(codeString))
          return ObservationStatus.AMENDED;
        if ("cancelled".equals(codeString))
          return ObservationStatus.CANCELLED;
        if ("entered in error".equals(codeString))
          return ObservationStatus.ENTEREDINERROR;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationStatus.REGISTERED)
        return "registered";
      if (code == ObservationStatus.PRELIMINARY)
        return "preliminary";
      if (code == ObservationStatus.FINAL)
        return "final";
      if (code == ObservationStatus.AMENDED)
        return "amended";
      if (code == ObservationStatus.CANCELLED)
        return "cancelled";
      if (code == ObservationStatus.ENTEREDINERROR)
        return "entered in error";
      return "?";
      }
    }

    public enum ObservationReliability {
        OK, // The result has no reliability concerns.
        ONGOING, // An early estimate of value; measurement is still occurring.
        EARLY, // An early estimate of value; processing is still occurring.
        QUESTIONABLE, // The observation value should be treated with care.
        CALIBRATING, // The result has been generated while calibration is occurring.
        ERROR, // The observation could not be completed because of an error.
        UNKNOWN, // No observation  reliability value was available.
        NULL; // added to help the parsers
        public static ObservationReliability fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return OK;
        if ("ongoing".equals(codeString))
          return ONGOING;
        if ("early".equals(codeString))
          return EARLY;
        if ("questionable".equals(codeString))
          return QUESTIONABLE;
        if ("calibrating".equals(codeString))
          return CALIBRATING;
        if ("error".equals(codeString))
          return ERROR;
        if ("unknown".equals(codeString))
          return UNKNOWN;
        throw new Exception("Unknown ObservationReliability code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case OK: return "ok";
            case ONGOING: return "ongoing";
            case EARLY: return "early";
            case QUESTIONABLE: return "questionable";
            case CALIBRATING: return "calibrating";
            case ERROR: return "error";
            case UNKNOWN: return "unknown";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case OK: return "The result has no reliability concerns.";
            case ONGOING: return "An early estimate of value; measurement is still occurring.";
            case EARLY: return "An early estimate of value; processing is still occurring.";
            case QUESTIONABLE: return "The observation value should be treated with care.";
            case CALIBRATING: return "The result has been generated while calibration is occurring.";
            case ERROR: return "The observation could not be completed because of an error.";
            case UNKNOWN: return "No observation  reliability value was available.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case OK: return "ok";
            case ONGOING: return "ongoing";
            case EARLY: return "early";
            case QUESTIONABLE: return "questionable";
            case CALIBRATING: return "calibrating";
            case ERROR: return "error";
            case UNKNOWN: return "unknown";
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
          return ObservationReliability.OK;
        if ("ongoing".equals(codeString))
          return ObservationReliability.ONGOING;
        if ("early".equals(codeString))
          return ObservationReliability.EARLY;
        if ("questionable".equals(codeString))
          return ObservationReliability.QUESTIONABLE;
        if ("calibrating".equals(codeString))
          return ObservationReliability.CALIBRATING;
        if ("error".equals(codeString))
          return ObservationReliability.ERROR;
        if ("unknown".equals(codeString))
          return ObservationReliability.UNKNOWN;
        throw new Exception("Unknown ObservationReliability code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationReliability.OK)
        return "ok";
      if (code == ObservationReliability.ONGOING)
        return "ongoing";
      if (code == ObservationReliability.EARLY)
        return "early";
      if (code == ObservationReliability.QUESTIONABLE)
        return "questionable";
      if (code == ObservationReliability.CALIBRATING)
        return "calibrating";
      if (code == ObservationReliability.ERROR)
        return "error";
      if (code == ObservationReliability.UNKNOWN)
        return "unknown";
      return "?";
      }
    }

    public enum ObservationRelationshiptypes {
        HASCOMPONENT, // The target observation is a component of this observation (e.g. Systolic and Diastolic Blood Pressure).
        HASMEMBER, // This observation is a group observation (e.g. a battery, a panel of tests, a set of vital sign measurements) that includes the target as a member of the group.
        DERIVEDFROM, // The target observation is part of the information from which this observation value is derived (e.g. calculated anion gap, Apgar score).
        SEQUELTO, // This observation follows the target observation (e.g. timed tests such as Glucose Tolerance Test).
        REPLACES, // This observation replaces a previous observation (i.e. a revised value). The target observation is now obsolete.
        QUALIFIEDBY, // The value of the target observation qualifies (refines) the semantics of the source observation (e.g. a lipaemia measure target from a plasma measure).
        INTERFEREDBY, // The value of the target observation interferes (degardes quality, or prevents valid observation) with the semantics of the source observation (e.g. a hemolysis measure target from a plasma potassium measure which has no value).
        NULL; // added to help the parsers
        public static ObservationRelationshiptypes fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("has-component".equals(codeString))
          return HASCOMPONENT;
        if ("has-member".equals(codeString))
          return HASMEMBER;
        if ("derived-from".equals(codeString))
          return DERIVEDFROM;
        if ("sequel-to".equals(codeString))
          return SEQUELTO;
        if ("replaces".equals(codeString))
          return REPLACES;
        if ("qualified-by".equals(codeString))
          return QUALIFIEDBY;
        if ("interfered-by".equals(codeString))
          return INTERFEREDBY;
        throw new Exception("Unknown ObservationRelationshiptypes code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case HASCOMPONENT: return "has-component";
            case HASMEMBER: return "has-member";
            case DERIVEDFROM: return "derived-from";
            case SEQUELTO: return "sequel-to";
            case REPLACES: return "replaces";
            case QUALIFIEDBY: return "qualified-by";
            case INTERFEREDBY: return "interfered-by";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case HASCOMPONENT: return "The target observation is a component of this observation (e.g. Systolic and Diastolic Blood Pressure).";
            case HASMEMBER: return "This observation is a group observation (e.g. a battery, a panel of tests, a set of vital sign measurements) that includes the target as a member of the group.";
            case DERIVEDFROM: return "The target observation is part of the information from which this observation value is derived (e.g. calculated anion gap, Apgar score).";
            case SEQUELTO: return "This observation follows the target observation (e.g. timed tests such as Glucose Tolerance Test).";
            case REPLACES: return "This observation replaces a previous observation (i.e. a revised value). The target observation is now obsolete.";
            case QUALIFIEDBY: return "The value of the target observation qualifies (refines) the semantics of the source observation (e.g. a lipaemia measure target from a plasma measure).";
            case INTERFEREDBY: return "The value of the target observation interferes (degardes quality, or prevents valid observation) with the semantics of the source observation (e.g. a hemolysis measure target from a plasma potassium measure which has no value).";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case HASCOMPONENT: return "has-component";
            case HASMEMBER: return "has-member";
            case DERIVEDFROM: return "derived-from";
            case SEQUELTO: return "sequel-to";
            case REPLACES: return "replaces";
            case QUALIFIEDBY: return "qualified-by";
            case INTERFEREDBY: return "interfered-by";
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
          return ObservationRelationshiptypes.HASCOMPONENT;
        if ("has-member".equals(codeString))
          return ObservationRelationshiptypes.HASMEMBER;
        if ("derived-from".equals(codeString))
          return ObservationRelationshiptypes.DERIVEDFROM;
        if ("sequel-to".equals(codeString))
          return ObservationRelationshiptypes.SEQUELTO;
        if ("replaces".equals(codeString))
          return ObservationRelationshiptypes.REPLACES;
        if ("qualified-by".equals(codeString))
          return ObservationRelationshiptypes.QUALIFIEDBY;
        if ("interfered-by".equals(codeString))
          return ObservationRelationshiptypes.INTERFEREDBY;
        throw new Exception("Unknown ObservationRelationshiptypes code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationRelationshiptypes.HASCOMPONENT)
        return "has-component";
      if (code == ObservationRelationshiptypes.HASMEMBER)
        return "has-member";
      if (code == ObservationRelationshiptypes.DERIVEDFROM)
        return "derived-from";
      if (code == ObservationRelationshiptypes.SEQUELTO)
        return "sequel-to";
      if (code == ObservationRelationshiptypes.REPLACES)
        return "replaces";
      if (code == ObservationRelationshiptypes.QUALIFIEDBY)
        return "qualified-by";
      if (code == ObservationRelationshiptypes.INTERFEREDBY)
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
        protected StringType text;

        private static final long serialVersionUID = 230621180L;

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
         * @return {@link #text} (Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public StringType getTextElement() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public ObservationReferenceRangeComponent setTextElement(StringType value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.
         */
        public String getText() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Text based reference range in an observation which may be used when a quantitative range is not appropriate for an observation.  An example would be a reference value of "Negative" or a list or table of 'normals'.
         */
        public ObservationReferenceRangeComponent setText(String value) { 
          if (Utilities.noString(value))
            this.text = null;
          else {
            if (this.text == null)
              this.text = new StringType();
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
        copyValues(dst);
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
        protected Reference target;

        /**
         * The actual object that is the target of the reference (A reference to the observation that is related to this observation.)
         */
        protected Observation targetTarget;

        private static final long serialVersionUID = 1078793488L;

      public ObservationRelatedComponent() {
        super();
      }

      public ObservationRelatedComponent(Reference target) {
        super();
        this.target = target;
      }

        /**
         * @return {@link #type} (A code specifying the kind of relationship that exists with the target observation.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<ObservationRelationshiptypes> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (A code specifying the kind of relationship that exists with the target observation.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public ObservationRelatedComponent setTypeElement(Enumeration<ObservationRelationshiptypes> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return A code specifying the kind of relationship that exists with the target observation.
         */
        public ObservationRelationshiptypes getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value A code specifying the kind of relationship that exists with the target observation.
         */
        public ObservationRelatedComponent setType(ObservationRelationshiptypes value) { 
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
        public Reference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (A reference to the observation that is related to this observation.)
         */
        public ObservationRelatedComponent setTarget(Reference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A reference to the observation that is related to this observation.)
         */
        public Observation getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A reference to the observation that is related to this observation.)
         */
        public ObservationRelatedComponent setTargetTarget(Observation value) { 
          this.targetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "A code specifying the kind of relationship that exists with the target observation.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("target", "Reference(Observation)", "A reference to the observation that is related to this observation.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ObservationRelatedComponent copy() {
        ObservationRelatedComponent dst = new ObservationRelatedComponent();
        copyValues(dst);
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
     * Provides a reason why the expected value in the element Observation.value[x] is missing.
     */
    protected Enumeration<DataAbsentReason> dataAbsentReason;

    /**
     * The assessment made based on the result of the observation.
     */
    protected CodeableConcept interpretation;

    /**
     * May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.
     */
    protected StringType comments;

    /**
     * The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the "physiologically relevant time". This is usually either the time of the procedure or of specimen collection, but very often the source of the date/time is not known, only the date/time itself.
     */
    protected Type applies;

    /**
     * The date and time this observation was made available.
     */
    protected InstantType issued;

    /**
     * The status of the result value.
     */
    protected Enumeration<ObservationStatus> status;

    /**
     * An estimate of the degree to which quality issues have impacted on the value reported.
     */
    protected Enumeration<ObservationReliability> reliability;

    /**
     * Indicates the site on the subject's body where the observation was made ( i.e. the target site).
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
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The thing the observation is being made about.)
     */
    protected Resource subjectTarget;

    /**
     * The specimen that was used when this observation was made.
     */
    protected Reference specimen;

    /**
     * The actual object that is the target of the reference (The specimen that was used when this observation was made.)
     */
    protected Specimen specimenTarget;

    /**
     * Who was responsible for asserting the observed value as "true".
     */
    protected List<Reference> performer = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Who was responsible for asserting the observed value as "true".)
     */
    protected List<Resource> performerTarget = new ArrayList<Resource>();


    /**
     * The healthcare event  ( e.g. a patient and healthcare provider interaction ) that relates to this observation.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (The healthcare event  ( e.g. a patient and healthcare provider interaction ) that relates to this observation.)
     */
    protected Encounter encounterTarget;

    /**
     * Guidance on how to interpret the value by comparison to a normal or recommended range.
     */
    protected List<ObservationReferenceRangeComponent> referenceRange = new ArrayList<ObservationReferenceRangeComponent>();

    /**
     * Related observations - either components, or previous observations, or statements of derivation.
     */
    protected List<ObservationRelatedComponent> related = new ArrayList<ObservationRelatedComponent>();

    private static final long serialVersionUID = -1244154751L;

    public Observation() {
      super();
    }

    public Observation(CodeableConcept name, Enumeration<ObservationStatus> status) {
      super();
      this.name = name;
      this.status = status;
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
     * @return {@link #dataAbsentReason} (Provides a reason why the expected value in the element Observation.value[x] is missing.). This is the underlying object with id, value and extensions. The accessor "getDataAbsentReason" gives direct access to the value
     */
    public Enumeration<DataAbsentReason> getDataAbsentReasonElement() { 
      return this.dataAbsentReason;
    }

    /**
     * @param value {@link #dataAbsentReason} (Provides a reason why the expected value in the element Observation.value[x] is missing.). This is the underlying object with id, value and extensions. The accessor "getDataAbsentReason" gives direct access to the value
     */
    public Observation setDataAbsentReasonElement(Enumeration<DataAbsentReason> value) { 
      this.dataAbsentReason = value;
      return this;
    }

    /**
     * @return Provides a reason why the expected value in the element Observation.value[x] is missing.
     */
    public DataAbsentReason getDataAbsentReason() { 
      return this.dataAbsentReason == null ? null : this.dataAbsentReason.getValue();
    }

    /**
     * @param value Provides a reason why the expected value in the element Observation.value[x] is missing.
     */
    public Observation setDataAbsentReason(DataAbsentReason value) { 
      if (value == null)
        this.dataAbsentReason = null;
      else {
        if (this.dataAbsentReason == null)
          this.dataAbsentReason = new Enumeration<DataAbsentReason>();
        this.dataAbsentReason.setValue(value);
      }
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
     * @return {@link #comments} (May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
     */
    public StringType getCommentsElement() { 
      return this.comments;
    }

    /**
     * @param value {@link #comments} (May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
     */
    public Observation setCommentsElement(StringType value) { 
      this.comments = value;
      return this;
    }

    /**
     * @return May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.
     */
    public String getComments() { 
      return this.comments == null ? null : this.comments.getValue();
    }

    /**
     * @param value May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.
     */
    public Observation setComments(String value) { 
      if (Utilities.noString(value))
        this.comments = null;
      else {
        if (this.comments == null)
          this.comments = new StringType();
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
     * @return {@link #issued} (The date and time this observation was made available.). This is the underlying object with id, value and extensions. The accessor "getIssued" gives direct access to the value
     */
    public InstantType getIssuedElement() { 
      return this.issued;
    }

    /**
     * @param value {@link #issued} (The date and time this observation was made available.). This is the underlying object with id, value and extensions. The accessor "getIssued" gives direct access to the value
     */
    public Observation setIssuedElement(InstantType value) { 
      this.issued = value;
      return this;
    }

    /**
     * @return The date and time this observation was made available.
     */
    public DateAndTime getIssued() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    /**
     * @param value The date and time this observation was made available.
     */
    public Observation setIssued(DateAndTime value) { 
      if (value == null)
        this.issued = null;
      else {
        if (this.issued == null)
          this.issued = new InstantType();
        this.issued.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The status of the result value.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ObservationStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the result value.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Observation setStatusElement(Enumeration<ObservationStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the result value.
     */
    public ObservationStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the result value.
     */
    public Observation setStatus(ObservationStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ObservationStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #reliability} (An estimate of the degree to which quality issues have impacted on the value reported.). This is the underlying object with id, value and extensions. The accessor "getReliability" gives direct access to the value
     */
    public Enumeration<ObservationReliability> getReliabilityElement() { 
      return this.reliability;
    }

    /**
     * @param value {@link #reliability} (An estimate of the degree to which quality issues have impacted on the value reported.). This is the underlying object with id, value and extensions. The accessor "getReliability" gives direct access to the value
     */
    public Observation setReliabilityElement(Enumeration<ObservationReliability> value) { 
      this.reliability = value;
      return this;
    }

    /**
     * @return An estimate of the degree to which quality issues have impacted on the value reported.
     */
    public ObservationReliability getReliability() { 
      return this.reliability == null ? null : this.reliability.getValue();
    }

    /**
     * @param value An estimate of the degree to which quality issues have impacted on the value reported.
     */
    public Observation setReliability(ObservationReliability value) { 
      if (value == null)
        this.reliability = null;
      else {
        if (this.reliability == null)
          this.reliability = new Enumeration<ObservationReliability>();
        this.reliability.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #bodySite} (Indicates the site on the subject's body where the observation was made ( i.e. the target site).)
     */
    public CodeableConcept getBodySite() { 
      return this.bodySite;
    }

    /**
     * @param value {@link #bodySite} (Indicates the site on the subject's body where the observation was made ( i.e. the target site).)
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
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The thing the observation is being made about.)
     */
    public Observation setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The thing the observation is being made about.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The thing the observation is being made about.)
     */
    public Observation setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #specimen} (The specimen that was used when this observation was made.)
     */
    public Reference getSpecimen() { 
      return this.specimen;
    }

    /**
     * @param value {@link #specimen} (The specimen that was used when this observation was made.)
     */
    public Observation setSpecimen(Reference value) { 
      this.specimen = value;
      return this;
    }

    /**
     * @return {@link #specimen} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The specimen that was used when this observation was made.)
     */
    public Specimen getSpecimenTarget() { 
      return this.specimenTarget;
    }

    /**
     * @param value {@link #specimen} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The specimen that was used when this observation was made.)
     */
    public Observation setSpecimenTarget(Specimen value) { 
      this.specimenTarget = value;
      return this;
    }

    /**
     * @return {@link #performer} (Who was responsible for asserting the observed value as "true".)
     */
    public List<Reference> getPerformer() { 
      return this.performer;
    }

    /**
     * @return {@link #performer} (Who was responsible for asserting the observed value as "true".)
     */
    // syntactic sugar
    public Reference addPerformer() { //3
      Reference t = new Reference();
      this.performer.add(t);
      return t;
    }

    /**
     * @return {@link #performer} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Who was responsible for asserting the observed value as "true".)
     */
    public List<Resource> getPerformerTarget() { 
      return this.performerTarget;
    }

    /**
     * @return {@link #encounter} (The healthcare event  ( e.g. a patient and healthcare provider interaction ) that relates to this observation.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The healthcare event  ( e.g. a patient and healthcare provider interaction ) that relates to this observation.)
     */
    public Observation setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The healthcare event  ( e.g. a patient and healthcare provider interaction ) that relates to this observation.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The healthcare event  ( e.g. a patient and healthcare provider interaction ) that relates to this observation.)
     */
    public Observation setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #referenceRange} (Guidance on how to interpret the value by comparison to a normal or recommended range.)
     */
    public List<ObservationReferenceRangeComponent> getReferenceRange() { 
      return this.referenceRange;
    }

    /**
     * @return {@link #referenceRange} (Guidance on how to interpret the value by comparison to a normal or recommended range.)
     */
    // syntactic sugar
    public ObservationReferenceRangeComponent addReferenceRange() { //3
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

    /**
     * @return {@link #related} (Related observations - either components, or previous observations, or statements of derivation.)
     */
    // syntactic sugar
    public ObservationRelatedComponent addRelated() { //3
      ObservationRelatedComponent t = new ObservationRelatedComponent();
      this.related.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("name", "CodeableConcept", "Describes what was observed. Sometimes this is called the observation 'code'.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("value[x]", "Quantity|CodeableConcept|Attachment|Ratio|dateTime|Period|SampledData|string|time", "The information determined as a result of making the observation, if the information has a simple value.", 0, java.lang.Integer.MAX_VALUE, value));
        childrenList.add(new Property("dataAbsentReason", "code", "Provides a reason why the expected value in the element Observation.value[x] is missing.", 0, java.lang.Integer.MAX_VALUE, dataAbsentReason));
        childrenList.add(new Property("interpretation", "CodeableConcept", "The assessment made based on the result of the observation.", 0, java.lang.Integer.MAX_VALUE, interpretation));
        childrenList.add(new Property("comments", "string", "May include statements about significant, unexpected or unreliable values, or information about the source of the value where this may be relevant to the interpretation of the result.", 0, java.lang.Integer.MAX_VALUE, comments));
        childrenList.add(new Property("applies[x]", "dateTime|Period", "The time or time-period the observed value is asserted as being true. For biological subjects - e.g. human patients - this is usually called the 'physiologically relevant time'. This is usually either the time of the procedure or of specimen collection, but very often the source of the date/time is not known, only the date/time itself.", 0, java.lang.Integer.MAX_VALUE, applies));
        childrenList.add(new Property("issued", "instant", "The date and time this observation was made available.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("status", "code", "The status of the result value.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("reliability", "code", "An estimate of the degree to which quality issues have impacted on the value reported.", 0, java.lang.Integer.MAX_VALUE, reliability));
        childrenList.add(new Property("bodySite", "CodeableConcept", "Indicates the site on the subject's body where the observation was made ( i.e. the target site).", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("method", "CodeableConcept", "Indicates the mechanism used to perform the observation.", 0, java.lang.Integer.MAX_VALUE, method));
        childrenList.add(new Property("identifier", "Identifier", "A unique identifier for the simple observation.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Reference(Patient|Group|Device|Location)", "The thing the observation is being made about.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("specimen", "Reference(Specimen)", "The specimen that was used when this observation was made.", 0, java.lang.Integer.MAX_VALUE, specimen));
        childrenList.add(new Property("performer", "Reference(Practitioner|Device|Organization|Patient|RelatedPerson)", "Who was responsible for asserting the observed value as 'true'.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "The healthcare event  ( e.g. a patient and healthcare provider interaction ) that relates to this observation.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("referenceRange", "", "Guidance on how to interpret the value by comparison to a normal or recommended range.", 0, java.lang.Integer.MAX_VALUE, referenceRange));
        childrenList.add(new Property("related", "", "Related observations - either components, or previous observations, or statements of derivation.", 0, java.lang.Integer.MAX_VALUE, related));
      }

      public Observation copy() {
        Observation dst = new Observation();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.value = value == null ? null : value.copy();
        dst.dataAbsentReason = dataAbsentReason == null ? null : dataAbsentReason.copy();
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
        dst.performer = new ArrayList<Reference>();
        for (Reference i : performer)
          dst.performer.add(i.copy());
        dst.encounter = encounter == null ? null : encounter.copy();
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

