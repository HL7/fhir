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

// Generated on Tue, Jul 2, 2013 18:37+1000 for FHIR v0.09

import java.util.*;

/**
 * A record of an event made for purposes of maintaining a security log. Typical uses include detection of intrusion attempts and monitoring for inappropriate usage
 */
public class SecurityEvent extends Resource {

    public enum SecurityEventAction {
        C, // Create a new database object, such as Placing an Order.
        R, // Display or print data, such as a Doctor Census
        U, // Update data, such as Revise Patient Information
        D, // Delete items, such as a doctor master file record
        E, // Perform a system or application function such as log-on, program execution or use of an object's method, or perform a query/search operation
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

  public class SecurityEventActionEnumFactory implements EnumFactory {
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
        _0, // The operation completed successfully (whether with warnings or not)
        _4, // The action was not successful due to some kind of catered for error (often equivalent to an HTTP 400 response)
        _8, // The action was not successful due to some kind of unexpected error (often equivalent to an HTTP 500 response)
        _12, // An error of such magnitude occurred that the system is not longer availkable for use (i.e. the system died)
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

  public class SecurityEventOutcomeEnumFactory implements EnumFactory {
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
        name, // Machine Name, including DNS name
        ip, // IP Address
        phone, // Telephone Number
        email, // Email address
        uri, // URI (User directory, HTTP-PUT, ftp, etc.)
        Null; // added to help the parsers
        public static NetworkType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("name".equals(codeString))
          return name;
        if ("ip".equals(codeString))
          return ip;
        if ("phone".equals(codeString))
          return phone;
        if ("email".equals(codeString))
          return email;
        if ("uri".equals(codeString))
          return uri;
        throw new Exception("Unknown NetworkType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case name: return "name";
            case ip: return "ip";
            case phone: return "phone";
            case email: return "email";
            case uri: return "uri";
            default: return "?";
          }
        }
    }

  public class NetworkTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("name".equals(codeString))
          return NetworkType.name;
        if ("ip".equals(codeString))
          return NetworkType.ip;
        if ("phone".equals(codeString))
          return NetworkType.phone;
        if ("email".equals(codeString))
          return NetworkType.email;
        if ("uri".equals(codeString))
          return NetworkType.uri;
        throw new Exception("Unknown NetworkType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NetworkType.name)
        return "name";
      if (code == NetworkType.ip)
        return "ip";
      if (code == NetworkType.phone)
        return "phone";
      if (code == NetworkType.email)
        return "email";
      if (code == NetworkType.uri)
        return "uri";
      return "?";
      }
    }

    public enum ObjectType {
        _1, // Person
        _2, // System Object
        _3, // Organization
        _4, // Other
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

  public class ObjectTypeEnumFactory implements EnumFactory {
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
        _1, // A person or animal that is the subject of care for the event
        _2, // A physical or logical location related to the event
        _3, // A resource that is immutable and stored
        _4, // A resource that is either changeable or not even persisted
        _5, // An administrative record
        _6, // A logical agent involved in the event   (deprecated)
        _7, // (deprecated)
        _8, // A person providing healthcare related to the event (deprecated)
        _9, // A system requesting/receiving notification related to the event
        _10, // A person or organisation who accepts responsibility for paying for healthcare provision the event contributes to
        _11, // A logical agent involved in the event
        _12, // A user-role related to the event
        _13, // A policy (e.g. consent directive) related to the event
        _14, // Deprecated
        _15, // A human or organization providing care the event occurs in the context of
        _16, // A system that was the target of communications related to the event
        _17, // A system holding resources related to the event
        _18, // A schedule resource related to the event
        _19, // A person or animal that is the subject of services (not patient) related to the event
        _20, // A task in an IT system related to the event
        _21, // A sub-task in an IT system related to the event
        _22, // A database table related to the event (deprecated)
        _23, // A rule for how information related to the event is distributed
        _24, // A request for information related to the event
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

  public class ObjectRoleEnumFactory implements EnumFactory {
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
        _1, // Origination / Creation
        _2, // Import / Copy from original
        _3, // Amendment
        _4, // Verification
        _5, // Translation
        _6, // Access / Use
        _7, // De-identification
        _8, // Aggregation, summarization, derivation
        _9, // Report
        _10, // Export / Copy to target
        _11, // Disclosure
        _12, // Receipt of disclosure
        _13, // Archiving
        _14, // Logical deletion
        _15, // Permanent erasure / Physical destruction
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

  public class ObjectLifecycleEnumFactory implements EnumFactory {
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

    public class SecurityEventEventComponent extends Element {
        /**
         * Identifier for a family of the event
         */
        protected CodeableConcept type;

        /**
         * Identifier for the category of event
         */
        protected List<CodeableConcept> subtype = new ArrayList<CodeableConcept>();

        /**
         * Indicator for type of action performed during the event that generated the audit
         */
        protected Enumeration<SecurityEventAction> action;

        /**
         * The time when the event occurred on the source
         */
        protected Instant dateTime;

        /**
         * Indicates whether the event succeeded or failed
         */
        protected Enumeration<SecurityEventOutcome> outcome;

        /**
         * A free text description of the outcome of the event
         */
        protected String_ outcomeDesc;

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public List<CodeableConcept> getSubtype() { 
          return this.subtype;
        }

        public Enumeration<SecurityEventAction> getAction() { 
          return this.action;
        }

        public void setAction(Enumeration<SecurityEventAction> value) { 
          this.action = value;
        }

        public SecurityEventAction getActionSimple() { 
          return this.action == null ? null : this.action.getValue();
        }

        public void setActionSimple(SecurityEventAction value) { 
          if (value == null)
            this.action = null;
          else {
            if (this.action == null)
              this.action = new Enumeration<SecurityEventAction>();
            this.action.setValue(value);
          }
        }

        public Instant getDateTime() { 
          return this.dateTime;
        }

        public void setDateTime(Instant value) { 
          this.dateTime = value;
        }

        public Calendar getDateTimeSimple() { 
          return this.dateTime == null ? null : this.dateTime.getValue();
        }

        public void setDateTimeSimple(Calendar value) { 
            if (this.dateTime == null)
              this.dateTime = new Instant();
            this.dateTime.setValue(value);
        }

        public Enumeration<SecurityEventOutcome> getOutcome() { 
          return this.outcome;
        }

        public void setOutcome(Enumeration<SecurityEventOutcome> value) { 
          this.outcome = value;
        }

        public SecurityEventOutcome getOutcomeSimple() { 
          return this.outcome == null ? null : this.outcome.getValue();
        }

        public void setOutcomeSimple(SecurityEventOutcome value) { 
          if (value == null)
            this.outcome = null;
          else {
            if (this.outcome == null)
              this.outcome = new Enumeration<SecurityEventOutcome>();
            this.outcome.setValue(value);
          }
        }

        public String_ getOutcomeDesc() { 
          return this.outcomeDesc;
        }

        public void setOutcomeDesc(String_ value) { 
          this.outcomeDesc = value;
        }

        public String getOutcomeDescSimple() { 
          return this.outcomeDesc == null ? null : this.outcomeDesc.getValue();
        }

        public void setOutcomeDescSimple(String value) { 
          if (value == null)
            this.outcomeDesc = null;
          else {
            if (this.outcomeDesc == null)
              this.outcomeDesc = new String_();
            this.outcomeDesc.setValue(value);
          }
        }

      public SecurityEventEventComponent copy(SecurityEvent e) {
        SecurityEventEventComponent dst = e.new SecurityEventEventComponent();
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

    public class SecurityEventParticipantComponent extends Element {
        /**
         * Specification of the role(s) the user plays when performing the event. Usually the codes used in this element are local codes defined by the role-based access control security system used in the local context
         */
        protected List<CodeableConcept> role = new ArrayList<CodeableConcept>();

        /**
         * Direct reference to a resource that identifies the participant
         */
        protected ResourceReference reference;

        /**
         * Unique identifier for the user actively participating in the event
         */
        protected String_ userId;

        /**
         * User identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available
         */
        protected String_ authId;

        /**
         * Human-meaningful name for the user
         */
        protected String_ name;

        /**
         * Indicator that the user is or is not the requestor, or initiator, for the event being audited.
         */
        protected Boolean requestor;

        /**
         * Type of media involved. Used when the event is about exporting/importing onto media
         */
        protected Coding media;

        /**
         * Logical network location for application activity, if the activity has a network location
         */
        protected SecurityEventParticipantNetworkComponent network;

        public List<CodeableConcept> getRole() { 
          return this.role;
        }

        public ResourceReference getReference() { 
          return this.reference;
        }

        public void setReference(ResourceReference value) { 
          this.reference = value;
        }

        public String_ getUserId() { 
          return this.userId;
        }

        public void setUserId(String_ value) { 
          this.userId = value;
        }

        public String getUserIdSimple() { 
          return this.userId == null ? null : this.userId.getValue();
        }

        public void setUserIdSimple(String value) { 
          if (value == null)
            this.userId = null;
          else {
            if (this.userId == null)
              this.userId = new String_();
            this.userId.setValue(value);
          }
        }

        public String_ getAuthId() { 
          return this.authId;
        }

        public void setAuthId(String_ value) { 
          this.authId = value;
        }

        public String getAuthIdSimple() { 
          return this.authId == null ? null : this.authId.getValue();
        }

        public void setAuthIdSimple(String value) { 
          if (value == null)
            this.authId = null;
          else {
            if (this.authId == null)
              this.authId = new String_();
            this.authId.setValue(value);
          }
        }

        public String_ getName() { 
          return this.name;
        }

        public void setName(String_ value) { 
          this.name = value;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
        }

        public Boolean getRequestor() { 
          return this.requestor;
        }

        public void setRequestor(Boolean value) { 
          this.requestor = value;
        }

        public boolean getRequestorSimple() { 
          return this.requestor == null ? null : this.requestor.getValue();
        }

        public void setRequestorSimple(boolean value) { 
            if (this.requestor == null)
              this.requestor = new Boolean();
            this.requestor.setValue(value);
        }

        public Coding getMedia() { 
          return this.media;
        }

        public void setMedia(Coding value) { 
          this.media = value;
        }

        public SecurityEventParticipantNetworkComponent getNetwork() { 
          return this.network;
        }

        public void setNetwork(SecurityEventParticipantNetworkComponent value) { 
          this.network = value;
        }

      public SecurityEventParticipantComponent copy(SecurityEvent e) {
        SecurityEventParticipantComponent dst = e.new SecurityEventParticipantComponent();
        dst.role = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : role)
          dst.role.add(i.copy());
        dst.reference = reference == null ? null : reference.copy();
        dst.userId = userId == null ? null : userId.copy();
        dst.authId = authId == null ? null : authId.copy();
        dst.name = name == null ? null : name.copy();
        dst.requestor = requestor == null ? null : requestor.copy();
        dst.media = media == null ? null : media.copy();
        dst.network = network == null ? null : network.copy(e);
        return dst;
      }

  }

    public class SecurityEventParticipantNetworkComponent extends Element {
        /**
         * An identifier for the network access point of the user device for the audit event
         */
        protected String_ identifier;

        /**
         * An identifier for the type of network access point that originated the audit event
         */
        protected Enumeration<NetworkType> type;

        public String_ getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(String_ value) { 
          this.identifier = value;
        }

        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        public void setIdentifierSimple(String value) { 
          if (value == null)
            this.identifier = null;
          else {
            if (this.identifier == null)
              this.identifier = new String_();
            this.identifier.setValue(value);
          }
        }

        public Enumeration<NetworkType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<NetworkType> value) { 
          this.type = value;
        }

        public NetworkType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(NetworkType value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<NetworkType>();
            this.type.setValue(value);
          }
        }

      public SecurityEventParticipantNetworkComponent copy(SecurityEvent e) {
        SecurityEventParticipantNetworkComponent dst = e.new SecurityEventParticipantNetworkComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = type == null ? null : type.copy();
        return dst;
      }

  }

    public class SecurityEventSourceComponent extends Element {
        /**
         * Logical source location within the healthcare enterprise network
         */
        protected String_ site;

        /**
         * Identifier of the source where the event originated
         */
        protected String_ identifier;

        /**
         * Code specifying the type of source where event originated
         */
        protected List<Coding> type = new ArrayList<Coding>();

        public String_ getSite() { 
          return this.site;
        }

        public void setSite(String_ value) { 
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
              this.site = new String_();
            this.site.setValue(value);
          }
        }

        public String_ getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(String_ value) { 
          this.identifier = value;
        }

        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        public void setIdentifierSimple(String value) { 
            if (this.identifier == null)
              this.identifier = new String_();
            this.identifier.setValue(value);
        }

        public List<Coding> getType() { 
          return this.type;
        }

      public SecurityEventSourceComponent copy(SecurityEvent e) {
        SecurityEventSourceComponent dst = e.new SecurityEventSourceComponent();
        dst.site = site == null ? null : site.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = new ArrayList<Coding>();
        for (Coding i : type)
          dst.type.add(i.copy());
        return dst;
      }

  }

    public class SecurityEventObjectComponent extends Element {
        /**
         * Identifies a specific instance of the participant object. The reference should always be version specific
         */
        protected Identifier identifier;

        /**
         * Identifies a specific instance of the participant object. The reference should always be version specific
         */
        protected ResourceReference reference;

        /**
         * Object type being audited
         */
        protected Enumeration<ObjectType> type;

        /**
         * Code representing the functional application role of Participant Object being audited
         */
        protected Enumeration<ObjectRole> role;

        /**
         * Identifier for the data life-cycle stage for the participant object
         */
        protected Enumeration<ObjectLifecycle> lifecycle;

        /**
         * Denotes policy-defined sensitivity for the Participant Object ID such as VIP, HIV status, mental health status or similar topics
         */
        protected CodeableConcept sensitivity;

        /**
         * An instance-specific descriptor of the Participant Object ID audited, such as a person's name
         */
        protected String_ name;

        /**
         * The actual query for a query-type participant object
         */
        protected Base64Binary query;

        /**
         * Additional Information about the Object
         */
        protected List<SecurityEventObjectDetailsComponent> details = new ArrayList<SecurityEventObjectDetailsComponent>();

        public Identifier getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Identifier value) { 
          this.identifier = value;
        }

        public ResourceReference getReference() { 
          return this.reference;
        }

        public void setReference(ResourceReference value) { 
          this.reference = value;
        }

        public Enumeration<ObjectType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<ObjectType> value) { 
          this.type = value;
        }

        public ObjectType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(ObjectType value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<ObjectType>();
            this.type.setValue(value);
          }
        }

        public Enumeration<ObjectRole> getRole() { 
          return this.role;
        }

        public void setRole(Enumeration<ObjectRole> value) { 
          this.role = value;
        }

        public ObjectRole getRoleSimple() { 
          return this.role == null ? null : this.role.getValue();
        }

        public void setRoleSimple(ObjectRole value) { 
          if (value == null)
            this.role = null;
          else {
            if (this.role == null)
              this.role = new Enumeration<ObjectRole>();
            this.role.setValue(value);
          }
        }

        public Enumeration<ObjectLifecycle> getLifecycle() { 
          return this.lifecycle;
        }

        public void setLifecycle(Enumeration<ObjectLifecycle> value) { 
          this.lifecycle = value;
        }

        public ObjectLifecycle getLifecycleSimple() { 
          return this.lifecycle == null ? null : this.lifecycle.getValue();
        }

        public void setLifecycleSimple(ObjectLifecycle value) { 
          if (value == null)
            this.lifecycle = null;
          else {
            if (this.lifecycle == null)
              this.lifecycle = new Enumeration<ObjectLifecycle>();
            this.lifecycle.setValue(value);
          }
        }

        public CodeableConcept getSensitivity() { 
          return this.sensitivity;
        }

        public void setSensitivity(CodeableConcept value) { 
          this.sensitivity = value;
        }

        public String_ getName() { 
          return this.name;
        }

        public void setName(String_ value) { 
          this.name = value;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
        }

        public Base64Binary getQuery() { 
          return this.query;
        }

        public void setQuery(Base64Binary value) { 
          this.query = value;
        }

        public byte[] getQuerySimple() { 
          return this.query == null ? null : this.query.getValue();
        }

        public void setQuerySimple(byte[] value) { 
          if (value == null)
            this.query = null;
          else {
            if (this.query == null)
              this.query = new Base64Binary();
            this.query.setValue(value);
          }
        }

        public List<SecurityEventObjectDetailsComponent> getDetails() { 
          return this.details;
        }

      public SecurityEventObjectComponent copy(SecurityEvent e) {
        SecurityEventObjectComponent dst = e.new SecurityEventObjectComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.type = type == null ? null : type.copy();
        dst.role = role == null ? null : role.copy();
        dst.lifecycle = lifecycle == null ? null : lifecycle.copy();
        dst.sensitivity = sensitivity == null ? null : sensitivity.copy();
        dst.name = name == null ? null : name.copy();
        dst.query = query == null ? null : query.copy();
        dst.details = new ArrayList<SecurityEventObjectDetailsComponent>();
        for (SecurityEventObjectDetailsComponent i : details)
          dst.details.add(i.copy(e));
        return dst;
      }

  }

    public class SecurityEventObjectDetailsComponent extends Element {
        /**
         * Name of the property
         */
        protected String_ type;

        /**
         * Property value
         */
        protected Base64Binary value;

        public String_ getType() { 
          return this.type;
        }

        public void setType(String_ value) { 
          this.type = value;
        }

        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(String value) { 
            if (this.type == null)
              this.type = new String_();
            this.type.setValue(value);
        }

        public Base64Binary getValue() { 
          return this.value;
        }

        public void setValue(Base64Binary value) { 
          this.value = value;
        }

        public byte[] getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        public void setValueSimple(byte[] value) { 
            if (this.value == null)
              this.value = new Base64Binary();
            this.value.setValue(value);
        }

      public SecurityEventObjectDetailsComponent copy(SecurityEvent e) {
        SecurityEventObjectDetailsComponent dst = e.new SecurityEventObjectDetailsComponent();
        dst.type = type == null ? null : type.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    /**
     * Identifies the name, action type, time, and disposition of the audited event
     */
    protected SecurityEventEventComponent event;

    /**
     * A person, a hardware device or software process
     */
    protected List<SecurityEventParticipantComponent> participant = new ArrayList<SecurityEventParticipantComponent>();

    /**
     * Application systems and processes
     */
    protected SecurityEventSourceComponent source;

    /**
     * Specific instances of data or objects that have been accessed
     */
    protected List<SecurityEventObjectComponent> object = new ArrayList<SecurityEventObjectComponent>();

    public SecurityEventEventComponent getEvent() { 
      return this.event;
    }

    public void setEvent(SecurityEventEventComponent value) { 
      this.event = value;
    }

    public List<SecurityEventParticipantComponent> getParticipant() { 
      return this.participant;
    }

    public SecurityEventSourceComponent getSource() { 
      return this.source;
    }

    public void setSource(SecurityEventSourceComponent value) { 
      this.source = value;
    }

    public List<SecurityEventObjectComponent> getObject() { 
      return this.object;
    }

      public SecurityEvent copy() {
        SecurityEvent dst = new SecurityEvent();
        dst.event = event == null ? null : event.copy(dst);
        dst.participant = new ArrayList<SecurityEventParticipantComponent>();
        for (SecurityEventParticipantComponent i : participant)
          dst.participant.add(i.copy(dst));
        dst.source = source == null ? null : source.copy(dst);
        dst.object = new ArrayList<SecurityEventObjectComponent>();
        for (SecurityEventObjectComponent i : object)
          dst.object.add(i.copy(dst));
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

