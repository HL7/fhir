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

    public DeviceObservation() {
      super();
    }

    public DeviceObservation(CodeableConcept code, Instant issued, ResourceReference subject, ResourceReference device) {
      super();
      this.code = code;
      this.issued = issued;
      this.subject = subject;
      this.device = device;
    }

    public CodeableConcept getCode() { 
      return this.code;
    }

    public DeviceObservation setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public Instant getIssued() { 
      return this.issued;
    }

    public DeviceObservation setIssued(Instant value) { 
      this.issued = value;
      return this;
    }

    public Calendar getIssuedSimple() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    public DeviceObservation setIssuedSimple(Calendar value) { 
        if (this.issued == null)
          this.issued = new Instant();
        this.issued.setValue(value);
      return this;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public DeviceObservation setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    public ResourceReference getDevice() { 
      return this.device;
    }

    public DeviceObservation setDevice(ResourceReference value) { 
      this.device = value;
      return this;
    }

    public List<ResourceReference> getMeasurement() { 
      return this.measurement;
    }

    // syntactic sugar
    public ResourceReference addMeasurement() { 
      ResourceReference t = new ResourceReference();
      this.measurement.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("code", "CodeableConcept", "A code that identifies what type of device observation this is.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this observation.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("issued", "instant", "Date the measurements were made.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device)", "The subject of the measurements. Usually, but not always, this is a patient. However devices are also used to make measurements on other things as well.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("device", "Resource(Device)", "Device that produced the results.", 0, java.lang.Integer.MAX_VALUE, device));
        childrenList.add(new Property("measurement", "Resource(Observation)", "The actual measurements that the device produced.", 0, java.lang.Integer.MAX_VALUE, measurement));
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

