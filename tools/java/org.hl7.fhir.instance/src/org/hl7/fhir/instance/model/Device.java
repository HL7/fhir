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

// Generated on Fri, Oct 18, 2013 12:16+1100 for FHIR v0.12

import java.util.*;

/**
 * This resource identifies an instance of a manufactured thing that is used in the provision of healthcare without being substantially changed through that activity. The device may be a machine, an insert, a computer, an application, etc. This includes durable (reusable) medical equipment as well as disposable equipment used for diagnostic, treatment, and research for healthcare and public health.
 */
public class Device extends Resource {

    /**
     * Identifiers assigned to this device by various organizations. The most likely organizations to assign identifiers are the manufacturer and the owner, though regulatory agencies may also assign an identifier. The identifiers identify the particular device, not the kind of device.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Describes what kind of device that this.
     */
    protected CodeableConcept type;

    /**
     * The name of the manufacturer.
     */
    protected String_ manufacturer;

    /**
     * The "model" - an identifier assigned by the manufacturer to identify the product by its type. This number is shared by the all devices sold as the same type.
     */
    protected String_ model;

    /**
     * The version of the device, if the device has multiple releases under the same model, or if the device is software or carries firmware.
     */
    protected String_ version;

    /**
     * Date of expiry of this device (if applicable).
     */
    protected Date expiry;

    /**
     * FDA Mandated Unique Device Identifier - see http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm.
     */
    protected String_ udi;

    /**
     * Lot number of manufacture.
     */
    protected String_ lotNumber;

    /**
     * The organization that is responsible for the provision and ongoing maintenance of the device.
     */
    protected ResourceReference owner;

    /**
     * The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location.
     */
    protected ResourceReference location;

    /**
     * If the resource is affixed to a person.
     */
    protected ResourceReference patient;

    /**
     * Contact details for an organization or a particular human that is responsible for the device.
     */
    protected List<Contact> contact = new ArrayList<Contact>();

    /**
     * A network address on which the device may be contacted directly.
     */
    protected Uri url;

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
    }

    public String_ getManufacturer() { 
      return this.manufacturer;
    }

    public void setManufacturer(String_ value) { 
      this.manufacturer = value;
    }

    public String getManufacturerSimple() { 
      return this.manufacturer == null ? null : this.manufacturer.getValue();
    }

    public void setManufacturerSimple(String value) { 
      if (value == null)
        this.manufacturer = null;
      else {
        if (this.manufacturer == null)
          this.manufacturer = new String_();
        this.manufacturer.setValue(value);
      }
    }

    public String_ getModel() { 
      return this.model;
    }

    public void setModel(String_ value) { 
      this.model = value;
    }

    public String getModelSimple() { 
      return this.model == null ? null : this.model.getValue();
    }

    public void setModelSimple(String value) { 
      if (value == null)
        this.model = null;
      else {
        if (this.model == null)
          this.model = new String_();
        this.model.setValue(value);
      }
    }

    public String_ getVersion() { 
      return this.version;
    }

    public void setVersion(String_ value) { 
      this.version = value;
    }

    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    public void setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new String_();
        this.version.setValue(value);
      }
    }

    public Date getExpiry() { 
      return this.expiry;
    }

    public void setExpiry(Date value) { 
      this.expiry = value;
    }

    public String getExpirySimple() { 
      return this.expiry == null ? null : this.expiry.getValue();
    }

    public void setExpirySimple(String value) { 
      if (value == null)
        this.expiry = null;
      else {
        if (this.expiry == null)
          this.expiry = new Date();
        this.expiry.setValue(value);
      }
    }

    public String_ getUdi() { 
      return this.udi;
    }

    public void setUdi(String_ value) { 
      this.udi = value;
    }

    public String getUdiSimple() { 
      return this.udi == null ? null : this.udi.getValue();
    }

    public void setUdiSimple(String value) { 
      if (value == null)
        this.udi = null;
      else {
        if (this.udi == null)
          this.udi = new String_();
        this.udi.setValue(value);
      }
    }

    public String_ getLotNumber() { 
      return this.lotNumber;
    }

    public void setLotNumber(String_ value) { 
      this.lotNumber = value;
    }

    public String getLotNumberSimple() { 
      return this.lotNumber == null ? null : this.lotNumber.getValue();
    }

    public void setLotNumberSimple(String value) { 
      if (value == null)
        this.lotNumber = null;
      else {
        if (this.lotNumber == null)
          this.lotNumber = new String_();
        this.lotNumber.setValue(value);
      }
    }

    public ResourceReference getOwner() { 
      return this.owner;
    }

    public void setOwner(ResourceReference value) { 
      this.owner = value;
    }

    public ResourceReference getLocation() { 
      return this.location;
    }

    public void setLocation(ResourceReference value) { 
      this.location = value;
    }

    public ResourceReference getPatient() { 
      return this.patient;
    }

    public void setPatient(ResourceReference value) { 
      this.patient = value;
    }

    public List<Contact> getContact() { 
      return this.contact;
    }

    // syntactic sugar
    public Contact addContact() { 
      Contact t = new Contact();
      this.contact.add(t);
      return t;
    }

    public Uri getUrl() { 
      return this.url;
    }

    public void setUrl(Uri value) { 
      this.url = value;
    }

    public String getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    public void setUrlSimple(String value) { 
      if (value == null)
        this.url = null;
      else {
        if (this.url == null)
          this.url = new Uri();
        this.url.setValue(value);
      }
    }

      public Device copy() {
        Device dst = new Device();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.model = model == null ? null : model.copy();
        dst.version = version == null ? null : version.copy();
        dst.expiry = expiry == null ? null : expiry.copy();
        dst.udi = udi == null ? null : udi.copy();
        dst.lotNumber = lotNumber == null ? null : lotNumber.copy();
        dst.owner = owner == null ? null : owner.copy();
        dst.location = location == null ? null : location.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.contact = new ArrayList<Contact>();
        for (Contact i : contact)
          dst.contact.add(i.copy());
        dst.url = url == null ? null : url.copy();
        return dst;
      }

      protected Device typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Device;
   }


}

