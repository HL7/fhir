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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * Provenance information that describes the activity that lead to the creation of a set of resources. This information can be used to help determine their reliability or trace where the information in them came from. The focus of the provenance resource is record keeping, audit and traceability, and not explicit statements of clinical significance.
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

      public ProvenanceAgentComponent() {
        super();
      }

      public ProvenanceAgentComponent(Coding role, Coding type, Uri reference) {
        super();
        this.role = role;
        this.type = type;
        this.reference = reference;
      }

        public Coding getRole() { 
          return this.role;
        }

        public ProvenanceAgentComponent setRole(Coding value) { 
          this.role = value;
          return this;
        }

        public Coding getType() { 
          return this.type;
        }

        public ProvenanceAgentComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        public Uri getReference() { 
          return this.reference;
        }

        public ProvenanceAgentComponent setReference(Uri value) { 
          this.reference = value;
          return this;
        }

        public String getReferenceSimple() { 
          return this.reference == null ? null : this.reference.getValue();
        }

        public ProvenanceAgentComponent setReferenceSimple(String value) { 
            if (this.reference == null)
              this.reference = new Uri();
            this.reference.setValue(value);
          return this;
        }

        public String_ getDisplay() { 
          return this.display;
        }

        public ProvenanceAgentComponent setDisplay(String_ value) { 
          this.display = value;
          return this;
        }

        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

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

      public ProvenanceAgentComponent copy(Provenance e) {
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
         * The entity is attributed to an agent to express the agent's responsibility for that entity, possibly along with other agents. This description can be understood as a shorthand for saying that the agent was responsible for the activity which generated the entity.
         */
        protected ProvenanceAgentComponent agent;

      public ProvenanceEntityComponent() {
        super();
      }

      public ProvenanceEntityComponent(Enumeration<ProvenanceEntityRole> role, Coding type, Uri reference) {
        super();
        this.role = role;
        this.type = type;
        this.reference = reference;
      }

        public Enumeration<ProvenanceEntityRole> getRole() { 
          return this.role;
        }

        public ProvenanceEntityComponent setRole(Enumeration<ProvenanceEntityRole> value) { 
          this.role = value;
          return this;
        }

        public ProvenanceEntityRole getRoleSimple() { 
          return this.role == null ? null : this.role.getValue();
        }

        public ProvenanceEntityComponent setRoleSimple(ProvenanceEntityRole value) { 
            if (this.role == null)
              this.role = new Enumeration<ProvenanceEntityRole>();
            this.role.setValue(value);
          return this;
        }

        public Coding getType() { 
          return this.type;
        }

        public ProvenanceEntityComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        public Uri getReference() { 
          return this.reference;
        }

        public ProvenanceEntityComponent setReference(Uri value) { 
          this.reference = value;
          return this;
        }

        public String getReferenceSimple() { 
          return this.reference == null ? null : this.reference.getValue();
        }

        public ProvenanceEntityComponent setReferenceSimple(String value) { 
            if (this.reference == null)
              this.reference = new Uri();
            this.reference.setValue(value);
          return this;
        }

        public String_ getDisplay() { 
          return this.display;
        }

        public ProvenanceEntityComponent setDisplay(String_ value) { 
          this.display = value;
          return this;
        }

        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

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

        public ProvenanceAgentComponent getAgent() { 
          return this.agent;
        }

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
          childrenList.add(new Property("agent", "@Provenance.agent", "The entity is attributed to an agent to express the agent's responsibility for that entity, possibly along with other agents. This description can be understood as a shorthand for saying that the agent was responsible for the activity which generated the entity.", 0, java.lang.Integer.MAX_VALUE, agent));
        }

      public ProvenanceEntityComponent copy(Provenance e) {
        ProvenanceEntityComponent dst = new ProvenanceEntityComponent();
        dst.role = role == null ? null : role.copy();
        dst.type = type == null ? null : type.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.display = display == null ? null : display.copy();
        dst.agent = agent == null ? null : agent.copy(e);
        return dst;
      }

  }

    /**
     * The resource(s) that were generated by  the activity described in this resource. A provenance can point to more than one target if multiple resources were created/updated by the same activity.
     */
    protected List<ResourceReference> target = new ArrayList<ResourceReference>();

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
     * A digital signature on the target resource. The signature should reference a participant by xml:id. The signature is only added to support checking cryptographic integrity of the provenance, and not to represent workflow and clinical aspects of the signing process.
     */
    protected String_ signature;

    public Provenance() {
      super();
    }

    public Provenance(Instant recorded) {
      super();
      this.recorded = recorded;
    }

    public List<ResourceReference> getTarget() { 
      return this.target;
    }

    // syntactic sugar
    public ResourceReference addTarget() { 
      ResourceReference t = new ResourceReference();
      this.target.add(t);
      return t;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public Provenance setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    public Instant getRecorded() { 
      return this.recorded;
    }

    public Provenance setRecorded(Instant value) { 
      this.recorded = value;
      return this;
    }

    public Calendar getRecordedSimple() { 
      return this.recorded == null ? null : this.recorded.getValue();
    }

    public Provenance setRecordedSimple(Calendar value) { 
        if (this.recorded == null)
          this.recorded = new Instant();
        this.recorded.setValue(value);
      return this;
    }

    public CodeableConcept getReason() { 
      return this.reason;
    }

    public Provenance setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    public ResourceReference getLocation() { 
      return this.location;
    }

    public Provenance setLocation(ResourceReference value) { 
      this.location = value;
      return this;
    }

    public List<Uri> getPolicy() { 
      return this.policy;
    }

    // syntactic sugar
    public Uri addPolicy() { 
      Uri t = new Uri();
      this.policy.add(t);
      return t;
    }

    public Uri addPolicySimple(String value) { 
      Uri t = new Uri();
      t.setValue(value);
      this.policy.add(t);
      return t;
    }

    public List<ProvenanceAgentComponent> getAgent() { 
      return this.agent;
    }

    // syntactic sugar
    public ProvenanceAgentComponent addAgent() { 
      ProvenanceAgentComponent t = new ProvenanceAgentComponent();
      this.agent.add(t);
      return t;
    }

    public List<ProvenanceEntityComponent> getEntity() { 
      return this.entity;
    }

    // syntactic sugar
    public ProvenanceEntityComponent addEntity() { 
      ProvenanceEntityComponent t = new ProvenanceEntityComponent();
      this.entity.add(t);
      return t;
    }

    public String_ getSignature() { 
      return this.signature;
    }

    public Provenance setSignature(String_ value) { 
      this.signature = value;
      return this;
    }

    public String getSignatureSimple() { 
      return this.signature == null ? null : this.signature.getValue();
    }

    public Provenance setSignatureSimple(String value) { 
      if (value == null)
        this.signature = null;
      else {
        if (this.signature == null)
          this.signature = new String_();
        this.signature.setValue(value);
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
        childrenList.add(new Property("signature", "string", "A digital signature on the target resource. The signature should reference a participant by xml:id. The signature is only added to support checking cryptographic integrity of the provenance, and not to represent workflow and clinical aspects of the signing process.", 0, java.lang.Integer.MAX_VALUE, signature));
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
          dst.agent.add(i.copy(dst));
        dst.entity = new ArrayList<ProvenanceEntityComponent>();
        for (ProvenanceEntityComponent i : entity)
          dst.entity.add(i.copy(dst));
        dst.signature = signature == null ? null : signature.copy();
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

