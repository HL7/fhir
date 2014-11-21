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
 * Provenance information that describes the activity that led to the creation of a set of resources. This information can be used to help determine their reliability or trace where the information in them came from. The focus of the provenance resource is record keeping, audit and traceability, and not explicit statements of clinical significance.
 */
public class Provenance extends DomainResource {

    public enum ProvenanceEntityRole {
        DERIVATION, // A transformation of an entity into another, an update of an entity resulting in a new one, or the construction of a new entity based on a preexisting entity.
        REVISION, // A derivation for which the resulting entity is a revised version of some original.
        QUOTATION, // The repeat of (some or all of) an entity, such as text or image, by someone who may or may not be its original author.
        SOURCE, // A primary source for a topic refers to something produced by some agent with direct experience and knowledge about the topic, at the time of the topic's study, without benefit from hindsight.
        NULL; // added to help the parsers
        public static ProvenanceEntityRole fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("derivation".equals(codeString))
          return DERIVATION;
        if ("revision".equals(codeString))
          return REVISION;
        if ("quotation".equals(codeString))
          return QUOTATION;
        if ("source".equals(codeString))
          return SOURCE;
        throw new Exception("Unknown ProvenanceEntityRole code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DERIVATION: return "derivation";
            case REVISION: return "revision";
            case QUOTATION: return "quotation";
            case SOURCE: return "source";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DERIVATION: return "A transformation of an entity into another, an update of an entity resulting in a new one, or the construction of a new entity based on a preexisting entity.";
            case REVISION: return "A derivation for which the resulting entity is a revised version of some original.";
            case QUOTATION: return "The repeat of (some or all of) an entity, such as text or image, by someone who may or may not be its original author.";
            case SOURCE: return "A primary source for a topic refers to something produced by some agent with direct experience and knowledge about the topic, at the time of the topic's study, without benefit from hindsight.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DERIVATION: return "derivation";
            case REVISION: return "revision";
            case QUOTATION: return "quotation";
            case SOURCE: return "source";
            default: return "?";
          }
        }
    }

  public static class ProvenanceEntityRoleEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("derivation".equals(codeString))
          return ProvenanceEntityRole.DERIVATION;
        if ("revision".equals(codeString))
          return ProvenanceEntityRole.REVISION;
        if ("quotation".equals(codeString))
          return ProvenanceEntityRole.QUOTATION;
        if ("source".equals(codeString))
          return ProvenanceEntityRole.SOURCE;
        throw new Exception("Unknown ProvenanceEntityRole code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProvenanceEntityRole.DERIVATION)
        return "derivation";
      if (code == ProvenanceEntityRole.REVISION)
        return "revision";
      if (code == ProvenanceEntityRole.QUOTATION)
        return "quotation";
      if (code == ProvenanceEntityRole.SOURCE)
        return "source";
      return "?";
      }
    }

    public static class ProvenanceAgentComponent extends BackboneElement {
        /**
         * The role that the participant played.
         */
        protected Coding role;

        /**
         * The type of the participant.
         */
        protected Coding type;

        /**
         * Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        protected UriType reference;

        /**
         * Human-readable description of the participant.
         */
        protected StringType display;

        private static final long serialVersionUID = 14713896L;

      public ProvenanceAgentComponent() {
        super();
      }

      public ProvenanceAgentComponent(Coding role, Coding type, UriType reference) {
        super();
        this.role = role;
        this.type = type;
        this.reference = reference;
      }

        /**
         * @return {@link #role} (The role that the participant played.)
         */
        public Coding getRole() { 
          return this.role;
        }

        /**
         * @param value {@link #role} (The role that the participant played.)
         */
        public ProvenanceAgentComponent setRole(Coding value) { 
          this.role = value;
          return this;
        }

        /**
         * @return {@link #type} (The type of the participant.)
         */
        public Coding getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of the participant.)
         */
        public ProvenanceAgentComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.). This is the underlying object with id, value and extensions. The accessor "getReference" gives direct access to the value
         */
        public UriType getReferenceElement() { 
          return this.reference;
        }

        /**
         * @param value {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.). This is the underlying object with id, value and extensions. The accessor "getReference" gives direct access to the value
         */
        public ProvenanceAgentComponent setReferenceElement(UriType value) { 
          this.reference = value;
          return this;
        }

        /**
         * @return Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public String getReference() { 
          return this.reference == null ? null : this.reference.getValue();
        }

        /**
         * @param value Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public ProvenanceAgentComponent setReference(String value) { 
            if (this.reference == null)
              this.reference = new UriType();
            this.reference.setValue(value);
          return this;
        }

        /**
         * @return {@link #display} (Human-readable description of the participant.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public StringType getDisplayElement() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (Human-readable description of the participant.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public ProvenanceAgentComponent setDisplayElement(StringType value) { 
          this.display = value;
          return this;
        }

        /**
         * @return Human-readable description of the participant.
         */
        public String getDisplay() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value Human-readable description of the participant.
         */
        public ProvenanceAgentComponent setDisplay(String value) { 
          if (Utilities.noString(value))
            this.display = null;
          else {
            if (this.display == null)
              this.display = new StringType();
            this.display.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("role", "Coding", "The role that the participant played.", 0, java.lang.Integer.MAX_VALUE, role));
          childrenList.add(new Property("type", "Coding", "The type of the participant.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("reference", "uri", "Identity of participant. May be a logical or physical uri and maybe absolute or relative.", 0, java.lang.Integer.MAX_VALUE, reference));
          childrenList.add(new Property("display", "string", "Human-readable description of the participant.", 0, java.lang.Integer.MAX_VALUE, display));
        }

      public ProvenanceAgentComponent copy() {
        ProvenanceAgentComponent dst = new ProvenanceAgentComponent();
        copyValues(dst);
        dst.role = role == null ? null : role.copy();
        dst.type = type == null ? null : type.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.display = display == null ? null : display.copy();
        return dst;
      }

  }

    public static class ProvenanceEntityComponent extends BackboneElement {
        /**
         * How the entity was used during the activity.
         */
        protected Enumeration<ProvenanceEntityRole> role;

        /**
         * The type of the entity. If the entity is a resource, then this is a resource type.
         */
        protected Coding type;

        /**
         * Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        protected UriType reference;

        /**
         * Human-readable description of the entity.
         */
        protected StringType display;

        /**
         * The entity is attributed to an agent to express the agent's responsibility for that entity, possibly along with other agents. This description can be understood as shorthand for saying that the agent was responsible for the activity which generated the entity.
         */
        protected ProvenanceAgentComponent agent;

        private static final long serialVersionUID = 1533729633L;

      public ProvenanceEntityComponent() {
        super();
      }

      public ProvenanceEntityComponent(Enumeration<ProvenanceEntityRole> role, Coding type, UriType reference) {
        super();
        this.role = role;
        this.type = type;
        this.reference = reference;
      }

        /**
         * @return {@link #role} (How the entity was used during the activity.). This is the underlying object with id, value and extensions. The accessor "getRole" gives direct access to the value
         */
        public Enumeration<ProvenanceEntityRole> getRoleElement() { 
          return this.role;
        }

        /**
         * @param value {@link #role} (How the entity was used during the activity.). This is the underlying object with id, value and extensions. The accessor "getRole" gives direct access to the value
         */
        public ProvenanceEntityComponent setRoleElement(Enumeration<ProvenanceEntityRole> value) { 
          this.role = value;
          return this;
        }

        /**
         * @return How the entity was used during the activity.
         */
        public ProvenanceEntityRole getRole() { 
          return this.role == null ? null : this.role.getValue();
        }

        /**
         * @param value How the entity was used during the activity.
         */
        public ProvenanceEntityComponent setRole(ProvenanceEntityRole value) { 
            if (this.role == null)
              this.role = new Enumeration<ProvenanceEntityRole>();
            this.role.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (The type of the entity. If the entity is a resource, then this is a resource type.)
         */
        public Coding getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of the entity. If the entity is a resource, then this is a resource type.)
         */
        public ProvenanceEntityComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.). This is the underlying object with id, value and extensions. The accessor "getReference" gives direct access to the value
         */
        public UriType getReferenceElement() { 
          return this.reference;
        }

        /**
         * @param value {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.). This is the underlying object with id, value and extensions. The accessor "getReference" gives direct access to the value
         */
        public ProvenanceEntityComponent setReferenceElement(UriType value) { 
          this.reference = value;
          return this;
        }

        /**
         * @return Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public String getReference() { 
          return this.reference == null ? null : this.reference.getValue();
        }

        /**
         * @param value Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public ProvenanceEntityComponent setReference(String value) { 
            if (this.reference == null)
              this.reference = new UriType();
            this.reference.setValue(value);
          return this;
        }

        /**
         * @return {@link #display} (Human-readable description of the entity.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public StringType getDisplayElement() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (Human-readable description of the entity.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public ProvenanceEntityComponent setDisplayElement(StringType value) { 
          this.display = value;
          return this;
        }

        /**
         * @return Human-readable description of the entity.
         */
        public String getDisplay() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value Human-readable description of the entity.
         */
        public ProvenanceEntityComponent setDisplay(String value) { 
          if (Utilities.noString(value))
            this.display = null;
          else {
            if (this.display == null)
              this.display = new StringType();
            this.display.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #agent} (The entity is attributed to an agent to express the agent's responsibility for that entity, possibly along with other agents. This description can be understood as shorthand for saying that the agent was responsible for the activity which generated the entity.)
         */
        public ProvenanceAgentComponent getAgent() { 
          return this.agent;
        }

        /**
         * @param value {@link #agent} (The entity is attributed to an agent to express the agent's responsibility for that entity, possibly along with other agents. This description can be understood as shorthand for saying that the agent was responsible for the activity which generated the entity.)
         */
        public ProvenanceEntityComponent setAgent(ProvenanceAgentComponent value) { 
          this.agent = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("role", "code", "How the entity was used during the activity.", 0, java.lang.Integer.MAX_VALUE, role));
          childrenList.add(new Property("type", "Coding", "The type of the entity. If the entity is a resource, then this is a resource type.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("reference", "uri", "Identity of participant. May be a logical or physical uri and maybe absolute or relative.", 0, java.lang.Integer.MAX_VALUE, reference));
          childrenList.add(new Property("display", "string", "Human-readable description of the entity.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("agent", "@Provenance.agent", "The entity is attributed to an agent to express the agent's responsibility for that entity, possibly along with other agents. This description can be understood as shorthand for saying that the agent was responsible for the activity which generated the entity.", 0, java.lang.Integer.MAX_VALUE, agent));
        }

      public ProvenanceEntityComponent copy() {
        ProvenanceEntityComponent dst = new ProvenanceEntityComponent();
        copyValues(dst);
        dst.role = role == null ? null : role.copy();
        dst.type = type == null ? null : type.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.display = display == null ? null : display.copy();
        dst.agent = agent == null ? null : agent.copy();
        return dst;
      }

  }

    /**
     * The Reference(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.
     */
    protected List<Reference> target = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (The Reference(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
     */
    protected List<Resource> targetTarget = new ArrayList<Resource>();


    /**
     * The period during which the activity occurred.
     */
    protected Period period;

    /**
     * The instant of time at which the activity was recorded.
     */
    protected InstantType recorded;

    /**
     * The reason that the activity was taking place.
     */
    protected CodeableConcept reason;

    /**
     * Where the activity occurred, if relevant.
     */
    protected Reference location;

    /**
     * The actual object that is the target of the reference (Where the activity occurred, if relevant.)
     */
    protected Location locationTarget;

    /**
     * Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.
     */
    protected List<UriType> policy = new ArrayList<UriType>();

    /**
     * An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.
     */
    protected List<ProvenanceAgentComponent> agent = new ArrayList<ProvenanceAgentComponent>();

    /**
     * An entity used in this activity.
     */
    protected List<ProvenanceEntityComponent> entity = new ArrayList<ProvenanceEntityComponent>();

    /**
     * A digital signature on the target Reference(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.
     */
    protected StringType integritySignature;

    private static final long serialVersionUID = 289748387L;

    public Provenance() {
      super();
    }

    public Provenance(InstantType recorded) {
      super();
      this.recorded = recorded;
    }

    /**
     * @return {@link #target} (The Reference(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
     */
    public List<Reference> getTarget() { 
      return this.target;
    }

    /**
     * @return {@link #target} (The Reference(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
     */
    // syntactic sugar
    public Reference addTarget() { //3
      Reference t = new Reference();
      this.target.add(t);
      return t;
    }

    /**
     * @return {@link #target} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The Reference(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
     */
    public List<Resource> getTargetTarget() { 
      return this.targetTarget;
    }

    /**
     * @return {@link #period} (The period during which the activity occurred.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (The period during which the activity occurred.)
     */
    public Provenance setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #recorded} (The instant of time at which the activity was recorded.). This is the underlying object with id, value and extensions. The accessor "getRecorded" gives direct access to the value
     */
    public InstantType getRecordedElement() { 
      return this.recorded;
    }

    /**
     * @param value {@link #recorded} (The instant of time at which the activity was recorded.). This is the underlying object with id, value and extensions. The accessor "getRecorded" gives direct access to the value
     */
    public Provenance setRecordedElement(InstantType value) { 
      this.recorded = value;
      return this;
    }

    /**
     * @return The instant of time at which the activity was recorded.
     */
    public DateAndTime getRecorded() { 
      return this.recorded == null ? null : this.recorded.getValue();
    }

    /**
     * @param value The instant of time at which the activity was recorded.
     */
    public Provenance setRecorded(DateAndTime value) { 
        if (this.recorded == null)
          this.recorded = new InstantType();
        this.recorded.setValue(value);
      return this;
    }

    /**
     * @return {@link #reason} (The reason that the activity was taking place.)
     */
    public CodeableConcept getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (The reason that the activity was taking place.)
     */
    public Provenance setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #location} (Where the activity occurred, if relevant.)
     */
    public Reference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (Where the activity occurred, if relevant.)
     */
    public Provenance setLocation(Reference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #location} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Where the activity occurred, if relevant.)
     */
    public Location getLocationTarget() { 
      return this.locationTarget;
    }

    /**
     * @param value {@link #location} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Where the activity occurred, if relevant.)
     */
    public Provenance setLocationTarget(Location value) { 
      this.locationTarget = value;
      return this;
    }

    /**
     * @return {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    public List<UriType> getPolicy() { 
      return this.policy;
    }

    /**
     * @return {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    // syntactic sugar
    public UriType addPolicyElement() {//2 
      UriType t = new UriType();
      this.policy.add(t);
      return t;
    }

    /**
     * @param value {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    public Provenance addPolicy(String value) { //1
      UriType t = new UriType();
      t.setValue(value);
      this.policy.add(t);
      return this;
    }

    /**
     * @param value {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    public boolean hasPolicy(String value) { 
      for (UriType v : this.policy)
        if (v.equals(value)) // uri
          return true;
      return false;
    }

    /**
     * @return {@link #agent} (An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.)
     */
    public List<ProvenanceAgentComponent> getAgent() { 
      return this.agent;
    }

    /**
     * @return {@link #agent} (An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.)
     */
    // syntactic sugar
    public ProvenanceAgentComponent addAgent() { //3
      ProvenanceAgentComponent t = new ProvenanceAgentComponent();
      this.agent.add(t);
      return t;
    }

    /**
     * @return {@link #entity} (An entity used in this activity.)
     */
    public List<ProvenanceEntityComponent> getEntity() { 
      return this.entity;
    }

    /**
     * @return {@link #entity} (An entity used in this activity.)
     */
    // syntactic sugar
    public ProvenanceEntityComponent addEntity() { //3
      ProvenanceEntityComponent t = new ProvenanceEntityComponent();
      this.entity.add(t);
      return t;
    }

    /**
     * @return {@link #integritySignature} (A digital signature on the target Reference(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.). This is the underlying object with id, value and extensions. The accessor "getIntegritySignature" gives direct access to the value
     */
    public StringType getIntegritySignatureElement() { 
      return this.integritySignature;
    }

    /**
     * @param value {@link #integritySignature} (A digital signature on the target Reference(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.). This is the underlying object with id, value and extensions. The accessor "getIntegritySignature" gives direct access to the value
     */
    public Provenance setIntegritySignatureElement(StringType value) { 
      this.integritySignature = value;
      return this;
    }

    /**
     * @return A digital signature on the target Reference(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.
     */
    public String getIntegritySignature() { 
      return this.integritySignature == null ? null : this.integritySignature.getValue();
    }

    /**
     * @param value A digital signature on the target Reference(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.
     */
    public Provenance setIntegritySignature(String value) { 
      if (Utilities.noString(value))
        this.integritySignature = null;
      else {
        if (this.integritySignature == null)
          this.integritySignature = new StringType();
        this.integritySignature.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("target", "Reference(Any)", "The Reference(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("period", "Period", "The period during which the activity occurred.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("recorded", "instant", "The instant of time at which the activity was recorded.", 0, java.lang.Integer.MAX_VALUE, recorded));
        childrenList.add(new Property("reason", "CodeableConcept", "The reason that the activity was taking place.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("location", "Reference(Location)", "Where the activity occurred, if relevant.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("policy", "uri", "Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.", 0, java.lang.Integer.MAX_VALUE, policy));
        childrenList.add(new Property("agent", "", "An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.", 0, java.lang.Integer.MAX_VALUE, agent));
        childrenList.add(new Property("entity", "", "An entity used in this activity.", 0, java.lang.Integer.MAX_VALUE, entity));
        childrenList.add(new Property("integritySignature", "string", "A digital signature on the target Reference(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.", 0, java.lang.Integer.MAX_VALUE, integritySignature));
      }

      public Provenance copy() {
        Provenance dst = new Provenance();
        copyValues(dst);
        dst.target = new ArrayList<Reference>();
        for (Reference i : target)
          dst.target.add(i.copy());
        dst.period = period == null ? null : period.copy();
        dst.recorded = recorded == null ? null : recorded.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.location = location == null ? null : location.copy();
        dst.policy = new ArrayList<UriType>();
        for (UriType i : policy)
          dst.policy.add(i.copy());
        dst.agent = new ArrayList<ProvenanceAgentComponent>();
        for (ProvenanceAgentComponent i : agent)
          dst.agent.add(i.copy());
        dst.entity = new ArrayList<ProvenanceEntityComponent>();
        for (ProvenanceEntityComponent i : entity)
          dst.entity.add(i.copy());
        dst.integritySignature = integritySignature == null ? null : integritySignature.copy();
        return dst;
      }

      protected Provenance typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Provenance;
   }


}

