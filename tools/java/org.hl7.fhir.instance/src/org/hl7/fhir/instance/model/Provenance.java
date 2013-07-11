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

// Generated on Thu, Jul 11, 2013 17:46+1000 for FHIR v0.09

import java.util.*;

/**
 * Provenance information associated with another resource that can be used to help determine its reliability or trace where the information in it came from. The focus of the provenance resource is record keeping, audit and traceability, not clinical meaning
 */
public class Provenance extends Resource {

    public class ProvenanceActivityComponent extends Element {
        /**
         * The period during which the activity occurred
         */
        protected Period period;

        /**
         * The instant of time at which the activity was recorded
         */
        protected Instant recorded;

        /**
         * The reason that the activity was taking place
         */
        protected CodeableConcept reason;

        /**
         * Where the activity occurred, if relevant
         */
        protected ResourceReference location;

        /**
         * Policy or plan the activity was defined by
         */
        protected Uri policy;

        public Period getPeriod() { 
          return this.period;
        }

        public void setPeriod(Period value) { 
          this.period = value;
        }

        public Instant getRecorded() { 
          return this.recorded;
        }

        public void setRecorded(Instant value) { 
          this.recorded = value;
        }

        public Calendar getRecordedSimple() { 
          return this.recorded == null ? null : this.recorded.getValue();
        }

        public void setRecordedSimple(Calendar value) { 
            if (this.recorded == null)
              this.recorded = new Instant();
            this.recorded.setValue(value);
        }

        public CodeableConcept getReason() { 
          return this.reason;
        }

        public void setReason(CodeableConcept value) { 
          this.reason = value;
        }

        public ResourceReference getLocation() { 
          return this.location;
        }

        public void setLocation(ResourceReference value) { 
          this.location = value;
        }

        public Uri getPolicy() { 
          return this.policy;
        }

        public void setPolicy(Uri value) { 
          this.policy = value;
        }

        public String getPolicySimple() { 
          return this.policy == null ? null : this.policy.getValue();
        }

        public void setPolicySimple(String value) { 
          if (value == null)
            this.policy = null;
          else {
            if (this.policy == null)
              this.policy = new Uri();
            this.policy.setValue(value);
          }
        }

      public ProvenanceActivityComponent copy(Provenance e) {
        ProvenanceActivityComponent dst = e.new ProvenanceActivityComponent();
        dst.period = period == null ? null : period.copy();
        dst.recorded = recorded == null ? null : recorded.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.location = location == null ? null : location.copy();
        dst.policy = policy == null ? null : policy.copy();
        return dst;
      }

  }

    public class ProvenancePartyComponent extends Element {
        /**
         * The role that the participant played
         */
        protected Coding role;

        /**
         * The type of the participant
         */
        protected Coding type;

        /**
         * Identity of participant. May be a logical or physical uri and maybe absolute or relative
         */
        protected Uri identifier;

        /**
         * Human readable description of the participant
         */
        protected String_ description;

        public Coding getRole() { 
          return this.role;
        }

        public void setRole(Coding value) { 
          this.role = value;
        }

        public Coding getType() { 
          return this.type;
        }

        public void setType(Coding value) { 
          this.type = value;
        }

        public Uri getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Uri value) { 
          this.identifier = value;
        }

        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        public void setIdentifierSimple(String value) { 
            if (this.identifier == null)
              this.identifier = new Uri();
            this.identifier.setValue(value);
        }

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
        }

      public ProvenancePartyComponent copy(Provenance e) {
        ProvenancePartyComponent dst = e.new ProvenancePartyComponent();
        dst.role = role == null ? null : role.copy();
        dst.type = type == null ? null : type.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.description = description == null ? null : description.copy();
        return dst;
      }

  }

    /**
     * The resource(s) that this provenance information pertains to. A provenance can point to more than one target if multiple resources were created/updated by the same action
     */
    protected List<ResourceReference> target = new ArrayList<ResourceReference>();

    /**
     * The activity that was being undertaken that led to the creation of the resource being referenced
     */
    protected ProvenanceActivityComponent activity;

    /**
     * An entity that is involved in the provenance of the target resource
     */
    protected List<ProvenancePartyComponent> party = new ArrayList<ProvenancePartyComponent>();

    /**
     * A digital signature on the target resource. The signature should reference a participant by xml:id. The signature is only added to support checking cryptographic integrity of the provenance, and not to represent workflow and clinical aspects of the signing process
     */
    protected String_ signature;

    public List<ResourceReference> getTarget() { 
      return this.target;
    }

    public ProvenanceActivityComponent getActivity() { 
      return this.activity;
    }

    public void setActivity(ProvenanceActivityComponent value) { 
      this.activity = value;
    }

    public List<ProvenancePartyComponent> getParty() { 
      return this.party;
    }

    public String_ getSignature() { 
      return this.signature;
    }

    public void setSignature(String_ value) { 
      this.signature = value;
    }

    public String getSignatureSimple() { 
      return this.signature == null ? null : this.signature.getValue();
    }

    public void setSignatureSimple(String value) { 
      if (value == null)
        this.signature = null;
      else {
        if (this.signature == null)
          this.signature = new String_();
        this.signature.setValue(value);
      }
    }

      public Provenance copy() {
        Provenance dst = new Provenance();
        dst.target = new ArrayList<ResourceReference>();
        for (ResourceReference i : target)
          dst.target.add(i.copy());
        dst.activity = activity == null ? null : activity.copy(dst);
        dst.party = new ArrayList<ProvenancePartyComponent>();
        for (ProvenancePartyComponent i : party)
          dst.party.add(i.copy(dst));
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

