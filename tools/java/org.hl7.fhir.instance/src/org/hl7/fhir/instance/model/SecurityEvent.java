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

// Generated on Thu, May 30, 2013 06:48+1000 for FHIR v0.09

import java.util.*;

/**
 * A record of an event
 */
public class SecurityEvent extends Resource {

    public enum SecurityEventEventAction {
        C, // Create
        R, // Read/View/Print/Query
        U, // Update
        D, // Delete
        E, // Execute
        Null; // added to help the parsers
        public static SecurityEventEventAction fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown SecurityEventEventAction code '"+codeString+"'");
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

  public class SecurityEventEventActionEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("C".equals(codeString))
          return SecurityEventEventAction.C;
        if ("R".equals(codeString))
          return SecurityEventEventAction.R;
        if ("U".equals(codeString))
          return SecurityEventEventAction.U;
        if ("D".equals(codeString))
          return SecurityEventEventAction.D;
        if ("E".equals(codeString))
          return SecurityEventEventAction.E;
        throw new Exception("Unknown SecurityEventEventAction code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SecurityEventEventAction.C)
        return "C";
      if (code == SecurityEventEventAction.R)
        return "R";
      if (code == SecurityEventEventAction.U)
        return "U";
      if (code == SecurityEventEventAction.D)
        return "D";
      if (code == SecurityEventEventAction.E)
        return "E";
      return "?";
      }
    }

    public enum SecurityEventEventOutcome {
        _0, // Success
        _4, // Minor failure
        _8, // Serious failure
        _12, // Major failure
        Null; // added to help the parsers
        public static SecurityEventEventOutcome fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown SecurityEventEventOutcome code '"+codeString+"'");
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

  public class SecurityEventEventOutcomeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("0".equals(codeString))
          return SecurityEventEventOutcome._0;
        if ("4".equals(codeString))
          return SecurityEventEventOutcome._4;
        if ("8".equals(codeString))
          return SecurityEventEventOutcome._8;
        if ("12".equals(codeString))
          return SecurityEventEventOutcome._12;
        throw new Exception("Unknown SecurityEventEventOutcome code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SecurityEventEventOutcome._0)
        return "0";
      if (code == SecurityEventEventOutcome._4)
        return "4";
      if (code == SecurityEventEventOutcome._8)
        return "8";
      if (code == SecurityEventEventOutcome._12)
        return "12";
      return "?";
      }
    }

    public enum NetworkType {
        name, // Machine Name, including DNS name
        ip, // IP Address
        phone, // Telephone Number
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
        throw new Exception("Unknown NetworkType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case name: return "name";
            case ip: return "ip";
            case phone: return "phone";
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
        throw new Exception("Unknown NetworkType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NetworkType.name)
        return "name";
      if (code == NetworkType.ip)
        return "ip";
      if (code == NetworkType.phone)
        return "phone";
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
        _1, // Patient
        _2, // Location
        _3, // Report
        _4, // Resource
        _5, // Master file
        _6, // User
        _7, // List
        _8, // Doctor
        _9, // Subscriber
        _10, // Guarantor
        _11, // Security User Entity
        _12, // Security User Group
        _13, // Security Resource
        _14, // Security Granularity Definition
        _15, // Practitioner
        _16, // Data Destination
        _17, // Data Repository
        _18, // Schedule
        _19, // Customer
        _20, // Job
        _21, // Job Stream
        _22, // Table
        _23, // Routing Criteria
        _24, // Query
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
         * Identifier for a specific audited event
         */
        private Coding identifier;

        /**
         * Indicator for type of action performed during the event that generated the audit
         */
        private Enumeration<SecurityEventEventAction> action;

        /**
         * The time when the event occurred on the source
         */
        private Instant dateTime;

        /**
         * Indicates whether the event succeeded or failed
         */
        private Enumeration<SecurityEventEventOutcome> outcome;

        /**
         * Identifier for the category of event
         */
        private List<Coding> code = new ArrayList<Coding>();

        public Coding getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Coding value) { 
          this.identifier = value;
        }

        public Enumeration<SecurityEventEventAction> getAction() { 
          return this.action;
        }

        public void setAction(Enumeration<SecurityEventEventAction> value) { 
          this.action = value;
        }

        public SecurityEventEventAction getActionSimple() { 
          return this.action == null ? null : this.action.getValue();
        }

        public void setActionSimple(SecurityEventEventAction value) { 
          if (value == null)
            this.action = null;
          else {
            if (this.action == null)
              this.action = new Enumeration<SecurityEventEventAction>();
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

        public Enumeration<SecurityEventEventOutcome> getOutcome() { 
          return this.outcome;
        }

        public void setOutcome(Enumeration<SecurityEventEventOutcome> value) { 
          this.outcome = value;
        }

        public SecurityEventEventOutcome getOutcomeSimple() { 
          return this.outcome == null ? null : this.outcome.getValue();
        }

        public void setOutcomeSimple(SecurityEventEventOutcome value) { 
            if (this.outcome == null)
              this.outcome = new Enumeration<SecurityEventEventOutcome>();
            this.outcome.setValue(value);
        }

        public List<Coding> getCode() { 
          return this.code;
        }

  }

    public class SecurityEventParticipantComponent extends Element {
        /**
         * Unique identifier for the user actively participating in the event
         */
        private String_ userId;

        /**
         * User identifier text string from authentication system. This identifier would be one known to a common authentication system (e.g., single sign-on), if available
         */
        private String_ otherUserId;

        /**
         * Human-meaningful name for the user
         */
        private String_ name;

        /**
         * Indicator that the user is or is not the requestor, or initiator, for the event being audited.
         */
        private Boolean requestor;

        /**
         * Specification of the role(s) the user plays when performing the event, as assigned in role-based access control security
         */
        private List<Coding> role = new ArrayList<Coding>();

        /**
         * Used when the event is about exporting/importing onto media
         */
        private CodeableConcept mediaId;

        /**
         * Logical network location for application activity, if the activity has a network location
         */
        private SecurityEventParticipantNetworkComponent network;

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
            if (this.userId == null)
              this.userId = new String_();
            this.userId.setValue(value);
        }

        public String_ getOtherUserId() { 
          return this.otherUserId;
        }

        public void setOtherUserId(String_ value) { 
          this.otherUserId = value;
        }

        public String getOtherUserIdSimple() { 
          return this.otherUserId == null ? null : this.otherUserId.getValue();
        }

        public void setOtherUserIdSimple(String value) { 
          if (value == null)
            this.otherUserId = null;
          else {
            if (this.otherUserId == null)
              this.otherUserId = new String_();
            this.otherUserId.setValue(value);
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

        public List<Coding> getRole() { 
          return this.role;
        }

        public CodeableConcept getMediaId() { 
          return this.mediaId;
        }

        public void setMediaId(CodeableConcept value) { 
          this.mediaId = value;
        }

        public SecurityEventParticipantNetworkComponent getNetwork() { 
          return this.network;
        }

        public void setNetwork(SecurityEventParticipantNetworkComponent value) { 
          this.network = value;
        }

  }

    public class SecurityEventParticipantNetworkComponent extends Element {
        /**
         * An identifier for the type of network access point that originated the audit event
         */
        private Enumeration<NetworkType> type;

        /**
         * An identifier for the network access point of the user device for the audit event
         */
        private String_ identifier;

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

  }

    public class SecurityEventSourceComponent extends Element {
        /**
         * Logical source location within the healthcare enterprise network
         */
        private String_ site;

        /**
         * Identifier of the source where the event originated
         */
        private String_ identifier;

        /**
         * Code specifying the type of source where event originated
         */
        private List<Coding> type = new ArrayList<Coding>();

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

  }

    public class SecurityEventObjectComponent extends Element {
        /**
         * Object type being audited
         */
        private Enumeration<ObjectType> type;

        /**
         * Code representing the functional application role of Participant Object being audited
         */
        private Enumeration<ObjectRole> role;

        /**
         * Identifier for the data life-cycle stage for the participant object
         */
        private Enumeration<ObjectLifecycle> lifecycle;

        /**
         * Describes the identifier that is contained in Participant Object ID
         */
        private Coding idType;

        /**
         * Identifies a specific instance of the participant object
         */
        private String_ identifier;

        /**
         * Denotes policy-defined sensitivity for the Participant Object ID such as VIP, HIV status, mental health status or similar topics
         */
        private String_ sensitivity;

        /**
         * An instance-specific descriptor of the Participant Object ID audited, such as a person's name
         */
        private String_ name;

        /**
         * The actual query for a query-type participant object
         */
        private Base64Binary query;

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

        public Coding getIdType() { 
          return this.idType;
        }

        public void setIdType(Coding value) { 
          this.idType = value;
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

        public String_ getSensitivity() { 
          return this.sensitivity;
        }

        public void setSensitivity(String_ value) { 
          this.sensitivity = value;
        }

        public String getSensitivitySimple() { 
          return this.sensitivity == null ? null : this.sensitivity.getValue();
        }

        public void setSensitivitySimple(String value) { 
          if (value == null)
            this.sensitivity = null;
          else {
            if (this.sensitivity == null)
              this.sensitivity = new String_();
            this.sensitivity.setValue(value);
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

  }

    /**
     * Identifies the name, action type, time, and disposition of the audited event
     */
    private SecurityEventEventComponent event;

    /**
     * A person, a hardware device or software process
     */
    private List<SecurityEventParticipantComponent> participant = new ArrayList<SecurityEventParticipantComponent>();

    /**
     * Application systems and processes
     */
    private SecurityEventSourceComponent source;

    /**
     * Specific instances of data or objects that have been accessed
     */
    private List<SecurityEventObjectComponent> object = new ArrayList<SecurityEventObjectComponent>();

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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.SecurityEvent;
   }


}

