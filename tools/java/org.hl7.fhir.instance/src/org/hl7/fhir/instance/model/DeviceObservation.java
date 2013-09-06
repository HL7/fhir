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

// Generated on Fri, Sep 6, 2013 22:32+1000 for FHIR v0.11

import java.util.*;

/**
 * A set of observations produced by a device.
 */
public class DeviceObservation extends Resource {

    /**
     * A code that identifies what type of device observation this is.
     */
    protected CodeableConcept code;

    /**
     * Identifiers assigned to this observation.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Date the measurements were made.
     */
    protected Instant issued;

    /**
     * The subject of the measurements. Usually, but not always, this is a patient. However devices are also used to make measurements on other things as well.
     */
    protected ResourceReference subject;

    /**
     * Device that produced the results.
     */
    protected ResourceReference device;

    /**
     * The actual measurements that the device produced.
     */
    protected List<ResourceReference> measurement = new ArrayList<ResourceReference>();

    public CodeableConcept getCode() { 
      return this.code;
    }

    public void setCode(CodeableConcept value) { 
      this.code = value;
    }

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    public Instant getIssued() { 
      return this.issued;
    }

    public void setIssued(Instant value) { 
      this.issued = value;
    }

    public Calendar getIssuedSimple() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    public void setIssuedSimple(Calendar value) { 
        if (this.issued == null)
          this.issued = new Instant();
        this.issued.setValue(value);
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getDevice() { 
      return this.device;
    }

    public void setDevice(ResourceReference value) { 
      this.device = value;
    }

    public List<ResourceReference> getMeasurement() { 
      return this.measurement;
    }

      public DeviceObservation copy() {
        DeviceObservation dst = new DeviceObservation();
        dst.code = code == null ? null : code.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.issued = issued == null ? null : issued.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.device = device == null ? null : device.copy();
        dst.measurement = new ArrayList<ResourceReference>();
        for (ResourceReference i : measurement)
          dst.measurement.add(i.copy());
        return dst;
      }

      protected DeviceObservation typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceObservation;
   }


}

