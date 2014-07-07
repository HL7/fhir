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
 * A record of an event made for purposes of maintaining a security log. Typical uses include detection of intrusion attempts and monitoring for inappropriate usage.
 */
public class SecurityEvent extends Resource {

    public enum SecurityEventAction {
        C, // Create a new database object, such as Placing an Order.
        R, // Display or print data, such as a Doctor Census.
        U, // Update data, such as Revise Patient Information.
        D, // Delete items, such as a doctor master file record.
        E, // Perform a system or application function such as log-on, program execution or use of an object's method, or perform a query/search operation.
        Null; // added to help the parsers
        public static SecurityEventAction fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("C".equals(codeString))
          return C;
        if ("R".equals(codeString))
          return R;
        if ("U".equals(codeString))
          return U;
        if ("D".equals(codeString))
          return D;
        if ("E".equals(codeString))
          return E;
        throw new Exception("Unknown SecurityEventAction code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case C: return "C";
            case R: return "R";
            case U: return "U";
            case D: return "D";
            case E: return "E";
            default: return "?";
          }
        }
    }

  public static class SecurityEventActionEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("C".equals(codeString))
          return SecurityEventAction.C;
        if ("R".equals(codeString))
          return SecurityEventAction.R;
        if ("U".equals(codeString))
          return SecurityEventAction.U;
        if ("D".equals(codeString))
          return SecurityEventAction.D;
        if ("E".equals(codeString))
          return SecurityEventAction.E;
        throw new Exception("Unknown SecurityEventAction code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SecurityEventAction.C)
        return "C";
      if (code == SecurityEventAction.R)
        return "R";
      if (code == SecurityEventAction.U)
        return "U";
      if (code == SecurityEventAction.D)
        return "D";
      if (code == SecurityEventAction.E)
        return "E";
      return "?";
      }
    }

    public enum SecurityEventOutcome {
        _0, // The operation completed successfully (whether with warnings or not).
        _4, // The action was not successful due to some kind of catered for error (often equivalent to an HTTP 400 response).
        _8, // The action was not successful due to some kind of unexpected error (often equivalent to an HTTP 500 response).
        _12, // An error of such magnitude occurred that the system is not longer available for use (i.e. the system died).
        Null; // added to help the parsers
        public static SecurityEventOutcome fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("0".equals(codeString))
          return _0;
        if ("4".equals(codeString))
          return _4;
        if ("8".equals(codeString))
          return _8;
        if ("12".equals(codeString))
          return _12;
        throw new Exception("Unknown SecurityEventOutcome code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _0: return "0";
            case _4: return "4";
            case _8: return "8";
            case _12: return "12";
            default: return "?";
          }
        }
    }

  public static class SecurityEventOutcomeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("0".equals(codeString))
          return SecurityEventOutcome._0;
        if ("4".equals(codeString))
          return SecurityEventOutcome._4;
        if ("8".equals(codeString))
          return SecurityEventOutcome._8;
        if ("12".equals(codeString))
          return SecurityEventOutcome._12;
        throw new Exception("Unknown SecurityEventOutcome code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SecurityEventOutcome._0)
        return "0";
      if (code == SecurityEventOutcome._4)
        return "4";
      if (code == SecurityEventOutcome._8)
        return "8";
      if (code == SecurityEventOutcome._12)
        return "12";
      return "?";
      }
    }

    public enum NetworkType {
        _1, // Machine Name, including DNS name.
        _2, // IP Address.
        _3, // Telephone Number.
        _4, // Email address.
        _5, // URI (User directory, HTTP-PUT, ftp, etc.).
        Null; // added to help the parsers
        public static NetworkType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return _1;
        if ("2".equals(codeString))
          return _2;
        if ("3".equals(codeString))
          return _3;
        if ("4".equals(codeString))
          return _4;
        if ("5".equals(codeString))
          return _5;
        throw new Exception("Unknown NetworkType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _1: return "1";
            case _2: return "2";
            case _3: return "3";
            case _4: return "4";
            case _5: return "5";
            default: return "?";
          }
        }
    }

  public static class NetworkTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return NetworkType._1;
        if ("2".equals(codeString))
          return NetworkType._2;
        if ("3".equals(codeString))
          return NetworkType._3;
        if ("4".equals(codeString))
          return NetworkType._4;
        if ("5".equals(codeString))
          return NetworkType._5;
        throw new Exception("Unknown NetworkType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NetworkType._1)
        return "1";
      if (code == NetworkType._2)
        return "2";
      if (code == NetworkType._3)
        return "3";
      if (code == NetworkType._4)
        return "4";
      if (code == NetworkType._5)
        return "5";
      return "?";
      }
    }

    public enum ObjectType {
        _1, // Person.
        _2, // System Object.
        _3, // Organization.
        _4, // Other.
        Null; // added to help the parsers
        public static ObjectType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return _1;
        if ("2".equals(codeString))
          return _2;
        if ("3".equals(codeString))
          return _3;
        if ("4".equals(codeString))
          return _4;
        throw new Exception("Unknown ObjectType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _1: return "1";
            case _2: return "2";
            case _3: return "3";
            case _4: return "4";
            default: return "?";
          }
        }
    }

  public static class ObjectTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return ObjectType._1;
        if ("2".equals(codeString))
          return ObjectType._2;
        if ("3".equals(codeString))
          return ObjectType._3;
        if ("4".equals(codeString))
          return ObjectType._4;
        throw new Exception("Unknown ObjectType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObjectType._1)
        return "1";
      if (code == ObjectType._2)
        return "2";
      if (code == ObjectType._3)
        return "3";
      if (code == ObjectType._4)
        return "4";
      return "?";
      }
    }

    public enum ObjectRole {
        _1, // This object is the patient that is the subject of care related to this event.  It is identifiable by patient ID or equivalent.  The patient may be either human or animal.
        _2, // This is a location identified as related to the event.  This is usually the location where the event took place.  Note that for shipping, the usual events are arrival at a location or departure from a location.
        _3, // This object is any kind of persistent document created as a result of the event.  This could be a paper report, film, electronic report, DICOM Study, etc.  Issues related to medical records life cycle management are conveyed elsewhere.
        _4, // A logical object related to the event.  (Deprecated).
        _5, // This is any configurable file used to control creation of documents.  Examples include the objects maintained by the HL7 Master File transactions, Value Sets, etc.
        _6, // A human participant not otherwise identified by some other category.
        _7, // (deprecated).
        _8, // Typically a licensed person who is providing or performing care related to the event, generally a physician.   The key distinction between doctor and practitioner is with regards to their role, not the licensing.  The doctor is the human who actually performed the work.  The practitioner is the human or organization that is responsible for the work.
        _9, // A person or system that is being notified as part of the event.  This is relevant in situations where automated systems provide notifications to other parties when an event took place.
        _10, // Insurance company, or any other organization who accepts responsibility for paying for the healthcare event.
        _11, // A person or active system object involved in the event with a security role.
        _12, // A person or system object involved in the event with the authority to modify security roles of other objects.
        _13, // A passive object, such as a role table, that is relevant to the event.
        _14, // (deprecated)  Relevant to certain RBAC security methodologies.
        _15, // Any person or organization responsible for providing care.  This encompasses all forms of care, licensed or otherwise, and all sorts of teams and care groups. Note, the distinction between practitioners and the doctor that actually provided the care to the patient.
        _16, // The source or destination for data transfer, when it does not match some other role.
        _17, // A source or destination for data transfer, that acts as an archive, database, or similar role.
        _18, // An object that holds schedule information.  This could be an appointment book, availability information, etc.
        _19, // An organization or person that is the recipient of services.  This could be an organization that is buying services for a patient, or a person that is buying services for an animal.
        _20, // An order, task, work item, procedure step, or other description of work to be performed.  E.g., a particular instance of an MPPS.
        _21, // A list of jobs or a system that provides lists of jobs.  E.g., an MWL SCP.
        _22, // (Deprecated).
        _23, // An object that specifies or controls the routing or delivery of items.  For example, a distribution list is the routing criteria for mail.  The items delivered may be documents, jobs, or other objects.
        _24, // The contents of a query.  This is used to capture the contents of any kind of query.  For security surveillance purposes knowing the queries being made is very important.
        Null; // added to help the parsers
        public static ObjectRole fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return _1;
        if ("2".equals(codeString))
          return _2;
        if ("3".equals(codeString))
          return _3;
        if ("4".equals(codeString))
          return _4;
        if ("5".equals(codeString))
          return _5;
        if ("6".equals(codeString))
          return _6;
        if ("7".equals(codeString))
          return _7;
        if ("8".equals(codeString))
          return _8;
        if ("9".equals(codeString))
          return _9;
        if ("10".equals(codeString))
          return _10;
        if ("11".equals(codeString))
          return _11;
        if ("12".equals(codeString))
          return _12;
        if ("13".equals(codeString))
          return _13;
        if ("14".equals(codeString))
          return _14;
        if ("15".equals(codeString))
          return _15;
        if ("16".equals(codeString))
          return _16;
        if ("17".equals(codeString))
          return _17;
        if ("18".equals(codeString))
          return _18;
        if ("19".equals(codeString))
          return _19;
        if ("20".equals(codeString))
          return _20;
        if ("21".equals(codeString))
          return _21;
        if ("22".equals(codeString))
          return _22;
        if ("23".equals(codeString))
          return _23;
        if ("24".equals(codeString))
          return _24;
        throw new Exception("Unknown ObjectRole code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _1: return "1";
            case _2: return "2";
            case _3: return "3";
            case _4: return "4";
            case _5: return "5";
            case _6: return "6";
            case _7: return "7";
            case _8: return "8";
            case _9: return "9";
            case _10: return "10";
            case _11: return "11";
            case _12: return "12";
            case _13: return "13";
            case _14: return "14";
            case _15: return "15";
            case _16: return "16";
            case _17: return "17";
            case _18: return "18";
            case _19: return "19";
            case _20: return "20";
            case _21: return "21";
            case _22: return "22";
            case _23: return "23";
            case _24: return "24";
            default: return "?";
          }
        }
    }

  public static class ObjectRoleEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return ObjectRole._1;
        if ("2".equals(codeString))
          return ObjectRole._2;
        if ("3".equals(codeString))
          return ObjectRole._3;
        if ("4".equals(codeString))
          return ObjectRole._4;
        if ("5".equals(codeString))
          return ObjectRole._5;
        if ("6".equals(codeString))
          return ObjectRole._6;
        if ("7".equals(codeString))
          return ObjectRole._7;
        if ("8".equals(codeString))
          return ObjectRole._8;
        if ("9".equals(codeString))
          return ObjectRole._9;
        if ("10".equals(codeString))
          return ObjectRole._10;
        if ("11".equals(codeString))
          return ObjectRole._11;
        if ("12".equals(codeString))
          return ObjectRole._12;
        if ("13".equals(codeString))
          return ObjectRole._13;
        if ("14".equals(codeString))
          return ObjectRole._14;
        if ("15".equals(codeString))
          return ObjectRole._15;
        if ("16".equals(codeString))
          return ObjectRole._16;
        if ("17".equals(codeString))
          return ObjectRole._17;
        if ("18".equals(codeString))
          return ObjectRole._18;
        if ("19".equals(codeString))
          return ObjectRole._19;
        if ("20".equals(codeString))
          return ObjectRole._20;
        if ("21".equals(codeString))
          return ObjectRole._21;
        if ("22".equals(codeString))
          return ObjectRole._22;
        if ("23".equals(codeString))
          return ObjectRole._23;
        if ("24".equals(codeString))
          return ObjectRole._24;
        throw new Exception("Unknown ObjectRole code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObjectRole._1)
        return "1";
      if (code == ObjectRole._2)
        return "2";
      if (code == ObjectRole._3)
        return "3";
      if (code == ObjectRole._4)
        return "4";
      if (code == ObjectRole._5)
        return "5";
      if (code == ObjectRole._6)
        return "6";
      if (code == ObjectRole._7)
        return "7";
      if (code == ObjectRole._8)
        return "8";
      if (code == ObjectRole._9)
        return "9";
      if (code == ObjectRole._10)
        return "10";
      if (code == ObjectRole._11)
        return "11";
      if (code == ObjectRole._12)
        return "12";
      if (code == ObjectRole._13)
        return "13";
      if (code == ObjectRole._14)
        return "14";
      if (code == ObjectRole._15)
        return "15";
      if (code == ObjectRole._16)
        return "16";
      if (code == ObjectRole._17)
        return "17";
      if (code == ObjectRole._18)
        return "18";
      if (code == ObjectRole._19)
        return "19";
      if (code == ObjectRole._20)
        return "20";
      if (code == ObjectRole._21)
        return "21";
      if (code == ObjectRole._22)
        return "22";
      if (code == ObjectRole._23)
        return "23";
      if (code == ObjectRole._24)
        return "24";
      return "?";
      }
    }

    public enum ObjectLifecycle {
        _1, // Origination / Creation.
        _2, // Import / Copy from original.
        _3, // Amendment.
        _4, // Verification.
        _5, // Translation.
        _6, // Access / Use.
        _7, // De-identification.
        _8, // Aggregation, summarization, derivation.
        _9, // Report.
        _10, // Export / Copy to target.
        _11, // Disclosure.
        _12, // Receipt of disclosure.
        _13, // Archiving.
        _14, // Logical deletion.
        _15, // Permanent erasure / Physical destruction.
        Null; // added to help the parsers
        public static ObjectLifecycle fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return _1;
        if ("2".equals(codeString))
          return _2;
        if ("3".equals(codeString))
          return _3;
        if ("4".equals(codeString))
          return _4;
        if ("5".equals(codeString))
          return _5;
        if ("6".equals(codeString))
          return _6;
        if ("7".equals(codeString))
          return _7;
        if ("8".equals(codeString))
          return _8;
        if ("9".equals(codeString))
          return _9;
        if ("10".equals(codeString))
          return _10;
        if ("11".equals(codeString))
          return _11;
        if ("12".equals(codeString))
          return _12;
        if ("13".equals(codeString))
          return _13;
        if ("14".equals(codeString))
          return _14;
        if ("15".equals(codeString))
          return _15;
        throw new Exception("Unknown ObjectLifecycle code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _1: return "1";
            case _2: return "2";
            case _3: return "3";
            case _4: return "4";
            case _5: return "5";
            case _6: return "6";
            case _7: return "7";
            case _8: return "8";
            case _9: return "9";
            case _10: return "10";
            case _11: return "11";
            case _12: return "12";
            case _13: return "13";
            case _14: return "14";
            case _15: return "15";
            default: return "?";
          }
        }
    }

  public static class ObjectLifecycleEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("1".equals(codeString))
          return ObjectLifecycle._1;
        if ("2".equals(codeString))
          return ObjectLifecycle._2;
        if ("3".equals(codeString))
          return ObjectLifecycle._3;
        if ("4".equals(codeString))
          return ObjectLifecycle._4;
        if ("5".equals(codeString))
          return ObjectLifecycle._5;
        if ("6".equals(codeString))
          return ObjectLifecycle._6;
        if ("7".equals(codeString))
          return ObjectLifecycle._7;
        if ("8".equals(codeString))
          return ObjectLifecycle._8;
        if ("9".equals(codeString))
          return ObjectLifecycle._9;
        if ("10".equals(codeString))
          return ObjectLifecycle._10;
        if ("11".equals(codeString))
          return ObjectLifecycle._11;
        if ("12".equals(codeString))
          return ObjectLifecycle._12;
        if ("13".equals(codeString))
          return ObjectLifecycle._13;
        if ("14".equals(codeString))
          return ObjectLifecycle._14;
        if ("15".equals(codeString))
          return ObjectLifecycle._15;
        throw new Exception("Unknown ObjectLifecycle code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObjectLifecycle._1)
        return "1";
      if (code == ObjectLifecycle._2)
        return "2";
      if (code == ObjectLifecycle._3)
        return "3";
      if (code == ObjectLifecycle._4)
        return "4";
      if (code == ObjectLifecycle._5)
        return "5";
      if (code == ObjectLifecycle._6)
        return "6";
      if (code == ObjectLifecycle._7)
        return "7";
      if (code == ObjectLifecycle._8)
        return "8";
      if (code == ObjectLifecycle._9)
        return "9";
      if (code == ObjectLifecycle._10)
        return "10";
      if (code == ObjectLifecycle._11)
        return "11";
      if (code == ObjectLifecycle._12)
        return "12";
      if (code == ObjectLifecycle._13)
        return "13";
      if (code == ObjectLifecycle._14)
        return "14";
      if (code == ObjectLifecycle._15)
        return "15";
      return "?";
      }
    }

    public static class SecurityEventEventComponent extends BackboneElement {
        /**
         * Identifier for a family of the event.
         */
        protected CodeableConcept type;

        /**
         * Identifier for the category of event.
         */
        protected List<CodeableConcept> subtype = new ArrayList<CodeableConcept>();

        /**
         * Indicator for type of action performed during the event that generated the audit.
         */
        protected Enumeration<SecurityEventAction> action;

        /**
         * The time when the event occurred on the source.
         */
        protected Instant dateTime;

        /**
         * Indicates whether the event succeeded or failed.
         */
        protected Enumeration<SecurityEventOutcome> outcome;

        /**
         * A free text description of the outcome of the event.
         */
        protected String_ outcomeDesc;

        private static final long serialVersionUID = -396992819L;

      public SecurityEventEventComponent() {
        super();
      }

      public SecurityEventEventComponent(CodeableConcept type, Instant dateTime) {
        super();
        this.type = type;
        this.dateTime = dateTime;
      }

        /**
         * @return {@link #type} (Identifier for a family of the event.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Identifier for a family of the event.)
         */
        public SecurityEventEventComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #subtype} (Identifier for the category of event.)
         */
        public List<CodeableConcept> getSubtype() { 
          return this.subtype;
        }

    // syntactic sugar
        /**
         * @return {@link #subtype} (Identifier for the category of event.)
         */
        public CodeableConcept addSubtype() { 
          CodeableConcept t = new CodeableConcept();
          this.subtype.add(t);
          return t;
        }

        /**
         * @return {@link #action} (Indicator for type of action performed during the event that generated the audit.)
         */
        public Enumeration<SecurityEventAction> getAction() { 
          return this.action;
        }

        /**
         * @param value {@link #action} (Indicator for type of action performed during the event that generated the audit.)
         */
        public SecurityEventEventComponent setAction(Enumeration<SecurityEventAction> value) { 
          this.action = value;
          return this;
        }

        /**
         * @return Indicator for type of action performed during the event that generated the audit.
         */
        public SecurityEventAction getActionSimple() { 
          return this.action == null ? null : this.action.getValue();
        }

        /**
         * @param value Indicator for type of action performed during the event that generated the audit.
         */
        public SecurityEventEventComponent setActionSimple(SecurityEventAction value) { 
          if (value == null)
            this.action = null;
          else {
            if (this.action == null)
              this.action = new Enumeration<SecurityEventAction>();
            this.action.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #dateTime} (The time when the event occurred on the source.)
         */
        public Instant getDateTime() { 
          return this.dateTime;
        }

        /**
         * @param value {@link #dateTime} (The time when the event occurred on the source.)
         */
        public SecurityEventEventComponent setDateTime(Instant value) { 
          this.dateTime = value;
          return this;
        }

        /**
         * @return The time when the event occurred on the source.
         */
        public DateAndTime getDateTimeSimple() { 
          return this.dateTime == null ? null : this.dateTime.getValue();
        }

        /**
         * @param value The time when the event occurred on the source.
         */
        public SecurityEventEventComponent setDateTimeSimple(DateAndTime value) { 
            if (this.dateTime == null)
              this.dateTime = new Instant();
            this.dateTime.setValue(value);
          return this;
        }

        /**
         * @return {@link #outcome} (Indicates whether the event succeeded or failed.)
         */
        public Enumeration<SecurityEventOutcome> getOutcome() { 
          return this.outcome;
        }

        /**
         * @param value {@link #outcome} (Indicates whether the event succeeded or failed.)
         */
        public SecurityEventEventComponent setOutcome(Enumeration<SecurityEventOutcome> value) { 
          this.outcome = value;
          return this;
        }

        /**
         * @return Indicates whether the event succeeded or failed.
         */
        public SecurityEventOutcome getOutcomeSimple() { 
          return this.outcome == null ? null : this.outcome.getValue();
        }

        /**
         * @param value Indicates whether the event succeeded or failed.
         */
        public SecurityEventEventComponent setOutcomeSimple(SecurityEventOutcome value) { 
          if (value == null)
            this.outcome = null;
          else {
            if (this.outcome == null)
              this.outcome = new Enumeration<SecurityEventOutcome>();
            this.outcome.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #outcomeDesc} (A free text description of the outcome of the event.)
         */
        public String_ getOutcomeDesc() { 
          return this.outcomeDesc;
        }

        /**
         * @param value {@link #outcomeDesc} (A free text description of the outcome of the event.)
         */
        public SecurityEventEventComponent setOutcomeDesc(String_ value) { 
          this.outcomeDesc = value;
          return this;
        }

        /**
         * @return A free text description of the outcome of the event.
         */
        public String getOutcomeDescSimple() { 
          return this.outcomeDesc == null ? null : this.outcomeDesc.getValue();
        }

        /**
         * @param value A free text description of the outcome of the event.
         */
        public SecurityEventEventComponent setOutcomeDescSimple(String value) { 
          if (value == null)
            this.outcomeDesc = null;
          else {
            if (this.outcomeDesc == null)
              this.outcomeDesc = new String_();
            this.outcomeDesc.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "Identifier for a family of the event.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("subtype", "CodeableConcept", "Identifier for the category of event.", 0, java.lang.Integer.MAX_VALUE, subtype));
          childrenList.add(new Property("action", "code", "Indicator for type of action performed during the event that generated the audit.", 0, java.lang.Integer.MAX_VALUE, action));
          childrenList.add(new Property("dateTime", "instant", "The time when the event occurred on the source.", 0, java.lang.Integer.MAX_VALUE, dateTime));
          childrenList.add(new Property("outcome", "code", "Indicates whether the event succeeded or failed.", 0, java.lang.Integer.MAX_VALUE, outcome));
          childrenList.add(new Property("outcomeDesc", "string", "A free text description of the outcome of the event.", 0, java.lang.Integer.MAX_VALUE, outcomeDesc));
        }

      public SecurityEventEventComponent copy() {
        SecurityEventEventComponent dst = new SecurityEventEventComponent();
        dst.type = type == null ? null : type.copy();
        dst.subtype = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : subtype)
          dst.subtype.add(i.copy());
        dst.action = action == null ? null : action.copy();
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.outcomeDesc = outcomeDesc == null ? null : outcomeDesc.copy();
        return dst;
      }

  }

    public static class SecurityEventParticipantComponent extends BackboneElement {
        /**
         * Specification of the role(s) the user plays when performing the event. Usually the codes used in this element are local codes defined by the role-based access control security system used in the local context.
         */
        protected List<CodeableConcept> role = new ArrayList<CodeableConcept>();

        /**
         * Direct reference to a resource that identifies the participant.
         */
        protected ResourceReference reference;

        /**
         * The actual object that is the target of the reference (Direct reference to a resource that identifies the participant.)
         */
        protected Resource referenceTarget;

        /**
         * Unique identifier for the user actively participating in the event.
         */
        protected String_ userId;

        /**
         * Alternative Participant Identifier. For a human, this should be a user identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available.
         */
        protected String_ altId;

        /**
         * Human-meaningful name for the user.
         */
        protected String_ name;

        /**
         * Indicator that the user is or is not the requestor, or initiator, for the event being audited.
         */
        protected Boolean requestor;

        /**
         * Type of media involved. Used when the event is about exporting/importing onto media.
         */
        protected Coding media;

        /**
         * Logical network location for application activity, if the activity has a network location.
         */
        protected SecurityEventParticipantNetworkComponent network;

        private static final long serialVersionUID = 274270822L;

      public SecurityEventParticipantComponent() {
        super();
      }

      public SecurityEventParticipantComponent(Boolean requestor) {
        super();
        this.requestor = requestor;
      }

        /**
         * @return {@link #role} (Specification of the role(s) the user plays when performing the event. Usually the codes used in this element are local codes defined by the role-based access control security system used in the local context.)
         */
        public List<CodeableConcept> getRole() { 
          return this.role;
        }

    // syntactic sugar
        /**
         * @return {@link #role} (Specification of the role(s) the user plays when performing the event. Usually the codes used in this element are local codes defined by the role-based access control security system used in the local context.)
         */
        public CodeableConcept addRole() { 
          CodeableConcept t = new CodeableConcept();
          this.role.add(t);
          return t;
        }

        /**
         * @return {@link #reference} (Direct reference to a resource that identifies the participant.)
         */
        public ResourceReference getReference() { 
          return this.reference;
        }

        /**
         * @param value {@link #reference} (Direct reference to a resource that identifies the participant.)
         */
        public SecurityEventParticipantComponent setReference(ResourceReference value) { 
          this.reference = value;
          return this;
        }

        /**
         * @return {@link #reference} (The actual object that is the target of the reference. Direct reference to a resource that identifies the participant.)
         */
        public Resource getReferenceTarget() { 
          return this.referenceTarget;
        }

        /**
         * @param value {@link #reference} (The actual object that is the target of the reference. Direct reference to a resource that identifies the participant.)
         */
        public SecurityEventParticipantComponent setReferenceTarget(Resource value) { 
          this.referenceTarget = value;
          return this;
        }

        /**
         * @return {@link #userId} (Unique identifier for the user actively participating in the event.)
         */
        public String_ getUserId() { 
          return this.userId;
        }

        /**
         * @param value {@link #userId} (Unique identifier for the user actively participating in the event.)
         */
        public SecurityEventParticipantComponent setUserId(String_ value) { 
          this.userId = value;
          return this;
        }

        /**
         * @return Unique identifier for the user actively participating in the event.
         */
        public String getUserIdSimple() { 
          return this.userId == null ? null : this.userId.getValue();
        }

        /**
         * @param value Unique identifier for the user actively participating in the event.
         */
        public SecurityEventParticipantComponent setUserIdSimple(String value) { 
          if (value == null)
            this.userId = null;
          else {
            if (this.userId == null)
              this.userId = new String_();
            this.userId.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #altId} (Alternative Participant Identifier. For a human, this should be a user identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available.)
         */
        public String_ getAltId() { 
          return this.altId;
        }

        /**
         * @param value {@link #altId} (Alternative Participant Identifier. For a human, this should be a user identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available.)
         */
        public SecurityEventParticipantComponent setAltId(String_ value) { 
          this.altId = value;
          return this;
        }

        /**
         * @return Alternative Participant Identifier. For a human, this should be a user identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available.
         */
        public String getAltIdSimple() { 
          return this.altId == null ? null : this.altId.getValue();
        }

        /**
         * @param value Alternative Participant Identifier. For a human, this should be a user identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available.
         */
        public SecurityEventParticipantComponent setAltIdSimple(String value) { 
          if (value == null)
            this.altId = null;
          else {
            if (this.altId == null)
              this.altId = new String_();
            this.altId.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #name} (Human-meaningful name for the user.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Human-meaningful name for the user.)
         */
        public SecurityEventParticipantComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Human-meaningful name for the user.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Human-meaningful name for the user.
         */
        public SecurityEventParticipantComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #requestor} (Indicator that the user is or is not the requestor, or initiator, for the event being audited.)
         */
        public Boolean getRequestor() { 
          return this.requestor;
        }

        /**
         * @param value {@link #requestor} (Indicator that the user is or is not the requestor, or initiator, for the event being audited.)
         */
        public SecurityEventParticipantComponent setRequestor(Boolean value) { 
          this.requestor = value;
          return this;
        }

        /**
         * @return Indicator that the user is or is not the requestor, or initiator, for the event being audited.
         */
        public boolean getRequestorSimple() { 
          return this.requestor == null ? false : this.requestor.getValue();
        }

        /**
         * @param value Indicator that the user is or is not the requestor, or initiator, for the event being audited.
         */
        public SecurityEventParticipantComponent setRequestorSimple(boolean value) { 
            if (this.requestor == null)
              this.requestor = new Boolean();
            this.requestor.setValue(value);
          return this;
        }

        /**
         * @return {@link #media} (Type of media involved. Used when the event is about exporting/importing onto media.)
         */
        public Coding getMedia() { 
          return this.media;
        }

        /**
         * @param value {@link #media} (Type of media involved. Used when the event is about exporting/importing onto media.)
         */
        public SecurityEventParticipantComponent setMedia(Coding value) { 
          this.media = value;
          return this;
        }

        /**
         * @return {@link #network} (Logical network location for application activity, if the activity has a network location.)
         */
        public SecurityEventParticipantNetworkComponent getNetwork() { 
          return this.network;
        }

        /**
         * @param value {@link #network} (Logical network location for application activity, if the activity has a network location.)
         */
        public SecurityEventParticipantComponent setNetwork(SecurityEventParticipantNetworkComponent value) { 
          this.network = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("role", "CodeableConcept", "Specification of the role(s) the user plays when performing the event. Usually the codes used in this element are local codes defined by the role-based access control security system used in the local context.", 0, java.lang.Integer.MAX_VALUE, role));
          childrenList.add(new Property("reference", "Resource(Practitioner|Patient|Device)", "Direct reference to a resource that identifies the participant.", 0, java.lang.Integer.MAX_VALUE, reference));
          childrenList.add(new Property("userId", "string", "Unique identifier for the user actively participating in the event.", 0, java.lang.Integer.MAX_VALUE, userId));
          childrenList.add(new Property("altId", "string", "Alternative Participant Identifier. For a human, this should be a user identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available.", 0, java.lang.Integer.MAX_VALUE, altId));
          childrenList.add(new Property("name", "string", "Human-meaningful name for the user.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("requestor", "boolean", "Indicator that the user is or is not the requestor, or initiator, for the event being audited.", 0, java.lang.Integer.MAX_VALUE, requestor));
          childrenList.add(new Property("media", "Coding", "Type of media involved. Used when the event is about exporting/importing onto media.", 0, java.lang.Integer.MAX_VALUE, media));
          childrenList.add(new Property("network", "", "Logical network location for application activity, if the activity has a network location.", 0, java.lang.Integer.MAX_VALUE, network));
        }

      public SecurityEventParticipantComponent copy() {
        SecurityEventParticipantComponent dst = new SecurityEventParticipantComponent();
        dst.role = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : role)
          dst.role.add(i.copy());
        dst.reference = reference == null ? null : reference.copy();
        dst.userId = userId == null ? null : userId.copy();
        dst.altId = altId == null ? null : altId.copy();
        dst.name = name == null ? null : name.copy();
        dst.requestor = requestor == null ? null : requestor.copy();
        dst.media = media == null ? null : media.copy();
        dst.network = network == null ? null : network.copy();
        return dst;
      }

  }

    public static class SecurityEventParticipantNetworkComponent extends BackboneElement {
        /**
         * An identifier for the network access point of the user device for the audit event.
         */
        protected String_ identifier;

        /**
         * An identifier for the type of network access point that originated the audit event.
         */
        protected Enumeration<NetworkType> type;

        private static final long serialVersionUID = -725471546L;

      public SecurityEventParticipantNetworkComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (An identifier for the network access point of the user device for the audit event.)
         */
        public String_ getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (An identifier for the network access point of the user device for the audit event.)
         */
        public SecurityEventParticipantNetworkComponent setIdentifier(String_ value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return An identifier for the network access point of the user device for the audit event.
         */
        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        /**
         * @param value An identifier for the network access point of the user device for the audit event.
         */
        public SecurityEventParticipantNetworkComponent setIdentifierSimple(String value) { 
          if (value == null)
            this.identifier = null;
          else {
            if (this.identifier == null)
              this.identifier = new String_();
            this.identifier.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (An identifier for the type of network access point that originated the audit event.)
         */
        public Enumeration<NetworkType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (An identifier for the type of network access point that originated the audit event.)
         */
        public SecurityEventParticipantNetworkComponent setType(Enumeration<NetworkType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return An identifier for the type of network access point that originated the audit event.
         */
        public NetworkType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value An identifier for the type of network access point that originated the audit event.
         */
        public SecurityEventParticipantNetworkComponent setTypeSimple(NetworkType value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<NetworkType>();
            this.type.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "string", "An identifier for the network access point of the user device for the audit event.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("type", "code", "An identifier for the type of network access point that originated the audit event.", 0, java.lang.Integer.MAX_VALUE, type));
        }

      public SecurityEventParticipantNetworkComponent copy() {
        SecurityEventParticipantNetworkComponent dst = new SecurityEventParticipantNetworkComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = type == null ? null : type.copy();
        return dst;
      }

  }

    public static class SecurityEventSourceComponent extends BackboneElement {
        /**
         * Logical source location within the healthcare enterprise network.
         */
        protected String_ site;

        /**
         * Identifier of the source where the event originated.
         */
        protected String_ identifier;

        /**
         * Code specifying the type of source where event originated.
         */
        protected List<Coding> type = new ArrayList<Coding>();

        private static final long serialVersionUID = 107583831L;

      public SecurityEventSourceComponent() {
        super();
      }

      public SecurityEventSourceComponent(String_ identifier) {
        super();
        this.identifier = identifier;
      }

        /**
         * @return {@link #site} (Logical source location within the healthcare enterprise network.)
         */
        public String_ getSite() { 
          return this.site;
        }

        /**
         * @param value {@link #site} (Logical source location within the healthcare enterprise network.)
         */
        public SecurityEventSourceComponent setSite(String_ value) { 
          this.site = value;
          return this;
        }

        /**
         * @return Logical source location within the healthcare enterprise network.
         */
        public String getSiteSimple() { 
          return this.site == null ? null : this.site.getValue();
        }

        /**
         * @param value Logical source location within the healthcare enterprise network.
         */
        public SecurityEventSourceComponent setSiteSimple(String value) { 
          if (value == null)
            this.site = null;
          else {
            if (this.site == null)
              this.site = new String_();
            this.site.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #identifier} (Identifier of the source where the event originated.)
         */
        public String_ getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (Identifier of the source where the event originated.)
         */
        public SecurityEventSourceComponent setIdentifier(String_ value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return Identifier of the source where the event originated.
         */
        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        /**
         * @param value Identifier of the source where the event originated.
         */
        public SecurityEventSourceComponent setIdentifierSimple(String value) { 
            if (this.identifier == null)
              this.identifier = new String_();
            this.identifier.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (Code specifying the type of source where event originated.)
         */
        public List<Coding> getType() { 
          return this.type;
        }

    // syntactic sugar
        /**
         * @return {@link #type} (Code specifying the type of source where event originated.)
         */
        public Coding addType() { 
          Coding t = new Coding();
          this.type.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("site", "string", "Logical source location within the healthcare enterprise network.", 0, java.lang.Integer.MAX_VALUE, site));
          childrenList.add(new Property("identifier", "string", "Identifier of the source where the event originated.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("type", "Coding", "Code specifying the type of source where event originated.", 0, java.lang.Integer.MAX_VALUE, type));
        }

      public SecurityEventSourceComponent copy() {
        SecurityEventSourceComponent dst = new SecurityEventSourceComponent();
        dst.site = site == null ? null : site.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = new ArrayList<Coding>();
        for (Coding i : type)
          dst.type.add(i.copy());
        return dst;
      }

  }

    public static class SecurityEventObjectComponent extends BackboneElement {
        /**
         * Identifies a specific instance of the participant object. The reference should always be version specific.
         */
        protected Identifier identifier;

        /**
         * Identifies a specific instance of the participant object. The reference should always be version specific.
         */
        protected ResourceReference reference;

        /**
         * The actual object that is the target of the reference (Identifies a specific instance of the participant object. The reference should always be version specific.)
         */
        protected Resource referenceTarget;

        /**
         * Object type being audited.
         */
        protected Enumeration<ObjectType> type;

        /**
         * Code representing the functional application role of Participant Object being audited.
         */
        protected Enumeration<ObjectRole> role;

        /**
         * Identifier for the data life-cycle stage for the participant object.
         */
        protected Enumeration<ObjectLifecycle> lifecycle;

        /**
         * Denotes policy-defined sensitivity for the Participant Object ID such as VIP, HIV status, mental health status or similar topics.
         */
        protected CodeableConcept sensitivity;

        /**
         * An instance-specific descriptor of the Participant Object ID audited, such as a person's name.
         */
        protected String_ name;

        /**
         * Text that describes the object in more detail.
         */
        protected String_ description;

        /**
         * The actual query for a query-type participant object.
         */
        protected Base64Binary query;

        /**
         * Additional Information about the Object.
         */
        protected List<SecurityEventObjectDetailComponent> detail = new ArrayList<SecurityEventObjectDetailComponent>();

        private static final long serialVersionUID = 545577037L;

      public SecurityEventObjectComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Identifies a specific instance of the participant object. The reference should always be version specific.)
         */
        public Identifier getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (Identifies a specific instance of the participant object. The reference should always be version specific.)
         */
        public SecurityEventObjectComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #reference} (Identifies a specific instance of the participant object. The reference should always be version specific.)
         */
        public ResourceReference getReference() { 
          return this.reference;
        }

        /**
         * @param value {@link #reference} (Identifies a specific instance of the participant object. The reference should always be version specific.)
         */
        public SecurityEventObjectComponent setReference(ResourceReference value) { 
          this.reference = value;
          return this;
        }

        /**
         * @return {@link #reference} (The actual object that is the target of the reference. Identifies a specific instance of the participant object. The reference should always be version specific.)
         */
        public Resource getReferenceTarget() { 
          return this.referenceTarget;
        }

        /**
         * @param value {@link #reference} (The actual object that is the target of the reference. Identifies a specific instance of the participant object. The reference should always be version specific.)
         */
        public SecurityEventObjectComponent setReferenceTarget(Resource value) { 
          this.referenceTarget = value;
          return this;
        }

        /**
         * @return {@link #type} (Object type being audited.)
         */
        public Enumeration<ObjectType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Object type being audited.)
         */
        public SecurityEventObjectComponent setType(Enumeration<ObjectType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Object type being audited.
         */
        public ObjectType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Object type being audited.
         */
        public SecurityEventObjectComponent setTypeSimple(ObjectType value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<ObjectType>();
            this.type.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #role} (Code representing the functional application role of Participant Object being audited.)
         */
        public Enumeration<ObjectRole> getRole() { 
          return this.role;
        }

        /**
         * @param value {@link #role} (Code representing the functional application role of Participant Object being audited.)
         */
        public SecurityEventObjectComponent setRole(Enumeration<ObjectRole> value) { 
          this.role = value;
          return this;
        }

        /**
         * @return Code representing the functional application role of Participant Object being audited.
         */
        public ObjectRole getRoleSimple() { 
          return this.role == null ? null : this.role.getValue();
        }

        /**
         * @param value Code representing the functional application role of Participant Object being audited.
         */
        public SecurityEventObjectComponent setRoleSimple(ObjectRole value) { 
          if (value == null)
            this.role = null;
          else {
            if (this.role == null)
              this.role = new Enumeration<ObjectRole>();
            this.role.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #lifecycle} (Identifier for the data life-cycle stage for the participant object.)
         */
        public Enumeration<ObjectLifecycle> getLifecycle() { 
          return this.lifecycle;
        }

        /**
         * @param value {@link #lifecycle} (Identifier for the data life-cycle stage for the participant object.)
         */
        public SecurityEventObjectComponent setLifecycle(Enumeration<ObjectLifecycle> value) { 
          this.lifecycle = value;
          return this;
        }

        /**
         * @return Identifier for the data life-cycle stage for the participant object.
         */
        public ObjectLifecycle getLifecycleSimple() { 
          return this.lifecycle == null ? null : this.lifecycle.getValue();
        }

        /**
         * @param value Identifier for the data life-cycle stage for the participant object.
         */
        public SecurityEventObjectComponent setLifecycleSimple(ObjectLifecycle value) { 
          if (value == null)
            this.lifecycle = null;
          else {
            if (this.lifecycle == null)
              this.lifecycle = new Enumeration<ObjectLifecycle>();
            this.lifecycle.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #sensitivity} (Denotes policy-defined sensitivity for the Participant Object ID such as VIP, HIV status, mental health status or similar topics.)
         */
        public CodeableConcept getSensitivity() { 
          return this.sensitivity;
        }

        /**
         * @param value {@link #sensitivity} (Denotes policy-defined sensitivity for the Participant Object ID such as VIP, HIV status, mental health status or similar topics.)
         */
        public SecurityEventObjectComponent setSensitivity(CodeableConcept value) { 
          this.sensitivity = value;
          return this;
        }

        /**
         * @return {@link #name} (An instance-specific descriptor of the Participant Object ID audited, such as a person's name.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (An instance-specific descriptor of the Participant Object ID audited, such as a person's name.)
         */
        public SecurityEventObjectComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return An instance-specific descriptor of the Participant Object ID audited, such as a person's name.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value An instance-specific descriptor of the Participant Object ID audited, such as a person's name.
         */
        public SecurityEventObjectComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #description} (Text that describes the object in more detail.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Text that describes the object in more detail.)
         */
        public SecurityEventObjectComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Text that describes the object in more detail.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Text that describes the object in more detail.
         */
        public SecurityEventObjectComponent setDescriptionSimple(String value) { 
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
         * @return {@link #query} (The actual query for a query-type participant object.)
         */
        public Base64Binary getQuery() { 
          return this.query;
        }

        /**
         * @param value {@link #query} (The actual query for a query-type participant object.)
         */
        public SecurityEventObjectComponent setQuery(Base64Binary value) { 
          this.query = value;
          return this;
        }

        /**
         * @return The actual query for a query-type participant object.
         */
        public byte[] getQuerySimple() { 
          return this.query == null ? null : this.query.getValue();
        }

        /**
         * @param value The actual query for a query-type participant object.
         */
        public SecurityEventObjectComponent setQuerySimple(byte[] value) { 
          if (value == null)
            this.query = null;
          else {
            if (this.query == null)
              this.query = new Base64Binary();
            this.query.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #detail} (Additional Information about the Object.)
         */
        public List<SecurityEventObjectDetailComponent> getDetail() { 
          return this.detail;
        }

    // syntactic sugar
        /**
         * @return {@link #detail} (Additional Information about the Object.)
         */
        public SecurityEventObjectDetailComponent addDetail() { 
          SecurityEventObjectDetailComponent t = new SecurityEventObjectDetailComponent();
          this.detail.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Identifies a specific instance of the participant object. The reference should always be version specific.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("reference", "Resource(Any)", "Identifies a specific instance of the participant object. The reference should always be version specific.", 0, java.lang.Integer.MAX_VALUE, reference));
          childrenList.add(new Property("type", "code", "Object type being audited.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("role", "code", "Code representing the functional application role of Participant Object being audited.", 0, java.lang.Integer.MAX_VALUE, role));
          childrenList.add(new Property("lifecycle", "code", "Identifier for the data life-cycle stage for the participant object.", 0, java.lang.Integer.MAX_VALUE, lifecycle));
          childrenList.add(new Property("sensitivity", "CodeableConcept", "Denotes policy-defined sensitivity for the Participant Object ID such as VIP, HIV status, mental health status or similar topics.", 0, java.lang.Integer.MAX_VALUE, sensitivity));
          childrenList.add(new Property("name", "string", "An instance-specific descriptor of the Participant Object ID audited, such as a person's name.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("description", "string", "Text that describes the object in more detail.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("query", "base64Binary", "The actual query for a query-type participant object.", 0, java.lang.Integer.MAX_VALUE, query));
          childrenList.add(new Property("detail", "", "Additional Information about the Object.", 0, java.lang.Integer.MAX_VALUE, detail));
        }

      public SecurityEventObjectComponent copy() {
        SecurityEventObjectComponent dst = new SecurityEventObjectComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.type = type == null ? null : type.copy();
        dst.role = role == null ? null : role.copy();
        dst.lifecycle = lifecycle == null ? null : lifecycle.copy();
        dst.sensitivity = sensitivity == null ? null : sensitivity.copy();
        dst.name = name == null ? null : name.copy();
        dst.description = description == null ? null : description.copy();
        dst.query = query == null ? null : query.copy();
        dst.detail = new ArrayList<SecurityEventObjectDetailComponent>();
        for (SecurityEventObjectDetailComponent i : detail)
          dst.detail.add(i.copy());
        return dst;
      }

  }

    public static class SecurityEventObjectDetailComponent extends BackboneElement {
        /**
         * Name of the property.
         */
        protected String_ type;

        /**
         * Property value.
         */
        protected Base64Binary value;

        private static final long serialVersionUID = -1973532085L;

      public SecurityEventObjectDetailComponent() {
        super();
      }

      public SecurityEventObjectDetailComponent(String_ type, Base64Binary value) {
        super();
        this.type = type;
        this.value = value;
      }

        /**
         * @return {@link #type} (Name of the property.)
         */
        public String_ getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Name of the property.)
         */
        public SecurityEventObjectDetailComponent setType(String_ value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Name of the property.
         */
        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Name of the property.
         */
        public SecurityEventObjectDetailComponent setTypeSimple(String value) { 
            if (this.type == null)
              this.type = new String_();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #value} (Property value.)
         */
        public Base64Binary getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (Property value.)
         */
        public SecurityEventObjectDetailComponent setValue(Base64Binary value) { 
          this.value = value;
          return this;
        }

        /**
         * @return Property value.
         */
        public byte[] getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value Property value.
         */
        public SecurityEventObjectDetailComponent setValueSimple(byte[] value) { 
            if (this.value == null)
              this.value = new Base64Binary();
            this.value.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "string", "Name of the property.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("value", "base64Binary", "Property value.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      public SecurityEventObjectDetailComponent copy() {
        SecurityEventObjectDetailComponent dst = new SecurityEventObjectDetailComponent();
        dst.type = type == null ? null : type.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    /**
     * Identifies the name, action type, time, and disposition of the audited event.
     */
    protected SecurityEventEventComponent event;

    /**
     * A person, a hardware device or software process.
     */
    protected List<SecurityEventParticipantComponent> participant = new ArrayList<SecurityEventParticipantComponent>();

    /**
     * Application systems and processes.
     */
    protected SecurityEventSourceComponent source;

    /**
     * Specific instances of data or objects that have been accessed.
     */
    protected List<SecurityEventObjectComponent> object = new ArrayList<SecurityEventObjectComponent>();

    private static final long serialVersionUID = -216029818L;

    public SecurityEvent() {
      super();
    }

    public SecurityEvent(SecurityEventEventComponent event, SecurityEventSourceComponent source) {
      super();
      this.event = event;
      this.source = source;
    }

    /**
     * @return {@link #event} (Identifies the name, action type, time, and disposition of the audited event.)
     */
    public SecurityEventEventComponent getEvent() { 
      return this.event;
    }

    /**
     * @param value {@link #event} (Identifies the name, action type, time, and disposition of the audited event.)
     */
    public SecurityEvent setEvent(SecurityEventEventComponent value) { 
      this.event = value;
      return this;
    }

    /**
     * @return {@link #participant} (A person, a hardware device or software process.)
     */
    public List<SecurityEventParticipantComponent> getParticipant() { 
      return this.participant;
    }

    // syntactic sugar
    /**
     * @return {@link #participant} (A person, a hardware device or software process.)
     */
    public SecurityEventParticipantComponent addParticipant() { 
      SecurityEventParticipantComponent t = new SecurityEventParticipantComponent();
      this.participant.add(t);
      return t;
    }

    /**
     * @return {@link #source} (Application systems and processes.)
     */
    public SecurityEventSourceComponent getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (Application systems and processes.)
     */
    public SecurityEvent setSource(SecurityEventSourceComponent value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #object} (Specific instances of data or objects that have been accessed.)
     */
    public List<SecurityEventObjectComponent> getObject() { 
      return this.object;
    }

    // syntactic sugar
    /**
     * @return {@link #object} (Specific instances of data or objects that have been accessed.)
     */
    public SecurityEventObjectComponent addObject() { 
      SecurityEventObjectComponent t = new SecurityEventObjectComponent();
      this.object.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("event", "", "Identifies the name, action type, time, and disposition of the audited event.", 0, java.lang.Integer.MAX_VALUE, event));
        childrenList.add(new Property("participant", "", "A person, a hardware device or software process.", 0, java.lang.Integer.MAX_VALUE, participant));
        childrenList.add(new Property("source", "", "Application systems and processes.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("object", "", "Specific instances of data or objects that have been accessed.", 0, java.lang.Integer.MAX_VALUE, object));
      }

      public SecurityEvent copy() {
        SecurityEvent dst = new SecurityEvent();
        dst.event = event == null ? null : event.copy();
        dst.participant = new ArrayList<SecurityEventParticipantComponent>();
        for (SecurityEventParticipantComponent i : participant)
          dst.participant.add(i.copy());
        dst.source = source == null ? null : source.copy();
        dst.object = new ArrayList<SecurityEventObjectComponent>();
        for (SecurityEventObjectComponent i : object)
          dst.object.add(i.copy());
        return dst;
      }

      protected SecurityEvent typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.SecurityEvent;
   }


}

