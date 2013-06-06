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

// Generated on Wed, Jun 5, 2013 08:57+1000 for FHIR v0.09

import java.util.*;

import java.net.*;
/**
 * Provenance information associated with another resource that can be used to help determine its reliability or trace where the information in it came from. The focus of the provenance resource is record keeping, audit and traceability, not clinical meaning
 */
public class Provenance extends Resource {

    public class ProvenanceActivityComponent extends Element {
        /**
         * The period during which the activity occurred
         */
        private Period period;

        /**
         * The instant of time at which the activity was recorded
         */
        private Instant recorded;

        /**
         * The reason that the activity was taking place
         */
        private CodeableConcept reason;

        /**
         * Where the activity occurred, if relevant
         */
        private ResourceReference location;

        /**
         * Policy or plan the activity was defined by
         */
        private Uri policy;

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

        public URI getPolicySimple() { 
          return this.policy == null ? null : this.policy.getValue();
        }

        public void setPolicySimple(URI value) { 
          if (value == null)
            this.policy = null;
          else {
            if (this.policy == null)
              this.policy = new Uri();
            this.policy.setValue(value);
          }
        }

  }

    public class ProvenancePartyComponent extends Element {
        /**
         * The role that the participant played
         */
        private Coding role;

        /**
         * The type of the participant
         */
        private Coding type;

        /**
         * Identity of participant. May be a logical or physical uri and maybe absolute or relative
         */
        private Uri identifier;

        /**
         * Human readable description of the participant
         */
        private String_ description;

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

        public URI getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        public void setIdentifierSimple(URI value) { 
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

  }

    /**
     * The resource(s) that this provenance information pertains to. A provenance can point to more than one target if multiple resources were created/updated by the same action
     */
    private List<ResourceReference> target = new ArrayList<ResourceReference>();

    /**
     * The activity that was being undertaken that led to the creation of the resource being referenced
     */
    private ProvenanceActivityComponent activity;

    /**
     * An entity that is involved in the provenance of the target resource
     */
    private List<ProvenancePartyComponent> party = new ArrayList<ProvenancePartyComponent>();

    /**
     * A digital signature on the target resource. The signature should reference a participant by xml:id. The signature is only added to support checking cryptographic integrity of the provenance, and not to represent workflow and clinical aspects of the signing process
     */
    private String_ signature;

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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Provenance;
   }


}

