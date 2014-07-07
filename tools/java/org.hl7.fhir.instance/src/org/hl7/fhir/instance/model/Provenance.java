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
 * Provenance information that describes the activity that led to the creation of a set of resources. This information can be used to help determine their reliability or trace where the information in them came from. The focus of the provenance resource is record keeping, audit and traceability, and not explicit statements of clinical significance.
 */
public class Provenance extends Resource {

    public enum ProvenanceEntityRole {
        derivation, // A transformation of an entity into another, an update of an entity resulting in a new one, or the construction of a new entity based on a preexisting entity.
        revision, // A derivation for which the resulting entity is a revised version of some original.
        quotation, // The repeat of (some or all of) an entity, such as text or image, by someone who may or may not be its original author.
        source, // A primary source for a topic refers to something produced by some agent with direct experience and knowledge about the topic, at the time of the topic's study, without benefit from hindsight.
        Null; // added to help the parsers
        public static ProvenanceEntityRole fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("derivation".equals(codeString))
          return derivation;
        if ("revision".equals(codeString))
          return revision;
        if ("quotation".equals(codeString))
          return quotation;
        if ("source".equals(codeString))
          return source;
        throw new Exception("Unknown ProvenanceEntityRole code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case derivation: return "derivation";
            case revision: return "revision";
            case quotation: return "quotation";
            case source: return "source";
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
          return ProvenanceEntityRole.derivation;
        if ("revision".equals(codeString))
          return ProvenanceEntityRole.revision;
        if ("quotation".equals(codeString))
          return ProvenanceEntityRole.quotation;
        if ("source".equals(codeString))
          return ProvenanceEntityRole.source;
        throw new Exception("Unknown ProvenanceEntityRole code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProvenanceEntityRole.derivation)
        return "derivation";
      if (code == ProvenanceEntityRole.revision)
        return "revision";
      if (code == ProvenanceEntityRole.quotation)
        return "quotation";
      if (code == ProvenanceEntityRole.source)
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
        protected Uri reference;

        /**
         * Human-readable description of the participant.
         */
        protected String_ display;

        private static final long serialVersionUID = 182214533L;

      public ProvenanceAgentComponent() {
        super();
      }

      public ProvenanceAgentComponent(Coding role, Coding type, Uri reference) {
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
         * @return {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.)
         */
        public Uri getReference() { 
          return this.reference;
        }

        /**
         * @param value {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.)
         */
        public ProvenanceAgentComponent setReference(Uri value) { 
          this.reference = value;
          return this;
        }

        /**
         * @return Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public String getReferenceSimple() { 
          return this.reference == null ? null : this.reference.getValue();
        }

        /**
         * @param value Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public ProvenanceAgentComponent setReferenceSimple(String value) { 
            if (this.reference == null)
              this.reference = new Uri();
            this.reference.setValue(value);
          return this;
        }

        /**
         * @return {@link #display} (Human-readable description of the participant.)
         */
        public String_ getDisplay() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (Human-readable description of the participant.)
         */
        public ProvenanceAgentComponent setDisplay(String_ value) { 
          this.display = value;
          return this;
        }

        /**
         * @return Human-readable description of the participant.
         */
        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value Human-readable description of the participant.
         */
        public ProvenanceAgentComponent setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new String_();
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
        protected Uri reference;

        /**
         * Human-readable description of the entity.
         */
        protected String_ display;

        /**
         * The entity is attributed to an agent to express the agent's responsibility for that entity, possibly along with other agents. This description can be understood as shorthand for saying that the agent was responsible for the activity which generated the entity.
         */
        protected ProvenanceAgentComponent agent;

        private static final long serialVersionUID = -1381135550L;

      public ProvenanceEntityComponent() {
        super();
      }

      public ProvenanceEntityComponent(Enumeration<ProvenanceEntityRole> role, Coding type, Uri reference) {
        super();
        this.role = role;
        this.type = type;
        this.reference = reference;
      }

        /**
         * @return {@link #role} (How the entity was used during the activity.)
         */
        public Enumeration<ProvenanceEntityRole> getRole() { 
          return this.role;
        }

        /**
         * @param value {@link #role} (How the entity was used during the activity.)
         */
        public ProvenanceEntityComponent setRole(Enumeration<ProvenanceEntityRole> value) { 
          this.role = value;
          return this;
        }

        /**
         * @return How the entity was used during the activity.
         */
        public ProvenanceEntityRole getRoleSimple() { 
          return this.role == null ? null : this.role.getValue();
        }

        /**
         * @param value How the entity was used during the activity.
         */
        public ProvenanceEntityComponent setRoleSimple(ProvenanceEntityRole value) { 
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
         * @return {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.)
         */
        public Uri getReference() { 
          return this.reference;
        }

        /**
         * @param value {@link #reference} (Identity of participant. May be a logical or physical uri and maybe absolute or relative.)
         */
        public ProvenanceEntityComponent setReference(Uri value) { 
          this.reference = value;
          return this;
        }

        /**
         * @return Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public String getReferenceSimple() { 
          return this.reference == null ? null : this.reference.getValue();
        }

        /**
         * @param value Identity of participant. May be a logical or physical uri and maybe absolute or relative.
         */
        public ProvenanceEntityComponent setReferenceSimple(String value) { 
            if (this.reference == null)
              this.reference = new Uri();
            this.reference.setValue(value);
          return this;
        }

        /**
         * @return {@link #display} (Human-readable description of the entity.)
         */
        public String_ getDisplay() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (Human-readable description of the entity.)
         */
        public ProvenanceEntityComponent setDisplay(String_ value) { 
          this.display = value;
          return this;
        }

        /**
         * @return Human-readable description of the entity.
         */
        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value Human-readable description of the entity.
         */
        public ProvenanceEntityComponent setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new String_();
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
        dst.role = role == null ? null : role.copy();
        dst.type = type == null ? null : type.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.display = display == null ? null : display.copy();
        dst.agent = agent == null ? null : agent.copy();
        return dst;
      }

  }

    /**
     * The resource(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.
     */
    protected List<ResourceReference> target = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (The resource(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
     */
    protected List<Resource> targetTarget = new ArrayList<Resource>();


    /**
     * The period during which the activity occurred.
     */
    protected Period period;

    /**
     * The instant of time at which the activity was recorded.
     */
    protected Instant recorded;

    /**
     * The reason that the activity was taking place.
     */
    protected CodeableConcept reason;

    /**
     * Where the activity occurred, if relevant.
     */
    protected ResourceReference location;

    /**
     * The actual object that is the target of the reference (Where the activity occurred, if relevant.)
     */
    protected Location locationTarget;

    /**
     * Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.
     */
    protected List<Uri> policy = new ArrayList<Uri>();

    /**
     * An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.
     */
    protected List<ProvenanceAgentComponent> agent = new ArrayList<ProvenanceAgentComponent>();

    /**
     * An entity used in this activity.
     */
    protected List<ProvenanceEntityComponent> entity = new ArrayList<ProvenanceEntityComponent>();

    /**
     * A digital signature on the target resource(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.
     */
    protected String_ integritySignature;

    private static final long serialVersionUID = -1568551152L;

    public Provenance() {
      super();
    }

    public Provenance(Instant recorded) {
      super();
      this.recorded = recorded;
    }

    /**
     * @return {@link #target} (The resource(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
     */
    public List<ResourceReference> getTarget() { 
      return this.target;
    }

    // syntactic sugar
    /**
     * @return {@link #target} (The resource(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
     */
    public ResourceReference addTarget() { 
      ResourceReference t = new ResourceReference();
      this.target.add(t);
      return t;
    }

    /**
     * @return {@link #target} (The actual objects that are the target of the reference. The resource(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.)
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
     * @return {@link #recorded} (The instant of time at which the activity was recorded.)
     */
    public Instant getRecorded() { 
      return this.recorded;
    }

    /**
     * @param value {@link #recorded} (The instant of time at which the activity was recorded.)
     */
    public Provenance setRecorded(Instant value) { 
      this.recorded = value;
      return this;
    }

    /**
     * @return The instant of time at which the activity was recorded.
     */
    public DateAndTime getRecordedSimple() { 
      return this.recorded == null ? null : this.recorded.getValue();
    }

    /**
     * @param value The instant of time at which the activity was recorded.
     */
    public Provenance setRecordedSimple(DateAndTime value) { 
        if (this.recorded == null)
          this.recorded = new Instant();
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
    public ResourceReference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (Where the activity occurred, if relevant.)
     */
    public Provenance setLocation(ResourceReference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #location} (The actual object that is the target of the reference. Where the activity occurred, if relevant.)
     */
    public Location getLocationTarget() { 
      return this.locationTarget;
    }

    /**
     * @param value {@link #location} (The actual object that is the target of the reference. Where the activity occurred, if relevant.)
     */
    public Provenance setLocationTarget(Location value) { 
      this.locationTarget = value;
      return this;
    }

    /**
     * @return {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    public List<Uri> getPolicy() { 
      return this.policy;
    }

    // syntactic sugar
    /**
     * @return {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    public Uri addPolicy() { 
      Uri t = new Uri();
      this.policy.add(t);
      return t;
    }

    /**
     * @param value {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    public Uri addPolicySimple(String value) { 
      Uri t = new Uri();
      t.setValue(value);
      this.policy.add(t);
      return t;
    }

    /**
     * @param value {@link #policy} (Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.)
     */
    public boolean hasPolicySimple(String value) { 
      for (Uri v : this.policy)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #agent} (An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.)
     */
    public List<ProvenanceAgentComponent> getAgent() { 
      return this.agent;
    }

    // syntactic sugar
    /**
     * @return {@link #agent} (An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.)
     */
    public ProvenanceAgentComponent addAgent() { 
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

    // syntactic sugar
    /**
     * @return {@link #entity} (An entity used in this activity.)
     */
    public ProvenanceEntityComponent addEntity() { 
      ProvenanceEntityComponent t = new ProvenanceEntityComponent();
      this.entity.add(t);
      return t;
    }

    /**
     * @return {@link #integritySignature} (A digital signature on the target resource(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.)
     */
    public String_ getIntegritySignature() { 
      return this.integritySignature;
    }

    /**
     * @param value {@link #integritySignature} (A digital signature on the target resource(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.)
     */
    public Provenance setIntegritySignature(String_ value) { 
      this.integritySignature = value;
      return this;
    }

    /**
     * @return A digital signature on the target resource(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.
     */
    public String getIntegritySignatureSimple() { 
      return this.integritySignature == null ? null : this.integritySignature.getValue();
    }

    /**
     * @param value A digital signature on the target resource(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.
     */
    public Provenance setIntegritySignatureSimple(String value) { 
      if (value == null)
        this.integritySignature = null;
      else {
        if (this.integritySignature == null)
          this.integritySignature = new String_();
        this.integritySignature.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("target", "Resource(Any)", "The resource(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("period", "Period", "The period during which the activity occurred.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("recorded", "instant", "The instant of time at which the activity was recorded.", 0, java.lang.Integer.MAX_VALUE, recorded));
        childrenList.add(new Property("reason", "CodeableConcept", "The reason that the activity was taking place.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("location", "Resource(Location)", "Where the activity occurred, if relevant.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("policy", "uri", "Policy or plan the activity was defined by. Typically, a single activity may have multiple applicable policy documents, such as patient consent, guarantor funding, etc.", 0, java.lang.Integer.MAX_VALUE, policy));
        childrenList.add(new Property("agent", "", "An agent takes a role in an activity such that the agent can be assigned some degree of responsibility for the activity taking place. An agent can be a person, a piece of software, an inanimate object, an organization, or other entities that may be ascribed responsibility.", 0, java.lang.Integer.MAX_VALUE, agent));
        childrenList.add(new Property("entity", "", "An entity used in this activity.", 0, java.lang.Integer.MAX_VALUE, entity));
        childrenList.add(new Property("integritySignature", "string", "A digital signature on the target resource(s). The signature should match a Provenance.agent.reference in the provenance resource. The signature is only added to support checking cryptographic integrity of the resource, and not to represent workflow and clinical aspects of the signing process, or to support non-repudiation.", 0, java.lang.Integer.MAX_VALUE, integritySignature));
      }

      public Provenance copy() {
        Provenance dst = new Provenance();
        dst.target = new ArrayList<ResourceReference>();
        for (ResourceReference i : target)
          dst.target.add(i.copy());
        dst.period = period == null ? null : period.copy();
        dst.recorded = recorded == null ? null : recorded.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.location = location == null ? null : location.copy();
        dst.policy = new ArrayList<Uri>();
        for (Uri i : policy)
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

