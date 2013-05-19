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

// Generated on Wed, May 15, 2013 09:11+1000 for FHIR v0.09

import java.util.*;

/**
 * Demographics and qualification information for an individual who is directly or indirectly involved in the provisioning of healthcare
 */
public class Practitioner extends Resource {

    public class PractitionerQualificationComponent extends Element {
        /**
         * Coded representation of the qualification
         */
        private CodeableConcept code;

        /**
         * Period during which the qualification is valid
         */
        private Period period;

        /**
         * Organization that regulates and issues the qualification
         */
        private ResourceReference issuer;

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public void setPeriod(Period value) { 
          this.period = value;
        }

        public ResourceReference getIssuer() { 
          return this.issuer;
        }

        public void setIssuer(ResourceReference value) { 
          this.issuer = value;
        }

  }

    /**
     * An identifier that applies to this person in this role
     */
    private List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Practitioner Demographic details
     */
    private Demographics details;

    /**
     * The organisation that the practitioner represents
     */
    private ResourceReference organization;

    /**
     * The way in which the person represents the organisation - what role do they have?
     */
    private List<CodeableConcept> role = new ArrayList<CodeableConcept>();

    /**
     * Specific specialty of the practitioner
     */
    private List<CodeableConcept> specialty = new ArrayList<CodeableConcept>();

    /**
     * The period during which the person is authorized to perform the service
     */
    private Period period;

    /**
     * Qualifications relevant to the provided service
     */
    private List<PractitionerQualificationComponent> qualification = new ArrayList<PractitionerQualificationComponent>();

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    public Demographics getDetails() { 
      return this.details;
    }

    public void setDetails(Demographics value) { 
      this.details = value;
    }

    public ResourceReference getOrganization() { 
      return this.organization;
    }

    public void setOrganization(ResourceReference value) { 
      this.organization = value;
    }

    public List<CodeableConcept> getRole() { 
      return this.role;
    }

    public List<CodeableConcept> getSpecialty() { 
      return this.specialty;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

    public List<PractitionerQualificationComponent> getQualification() { 
      return this.qualification;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Practitioner;
   }


}

