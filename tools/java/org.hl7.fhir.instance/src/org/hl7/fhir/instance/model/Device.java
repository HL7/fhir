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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

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
     * A kind of this device.
     */
    protected CodeableConcept type;

    /**
     * A name of the manufacturer.
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
     * FDA Mandated Unique Device Identifier. Use the human readable information (the content that the user sees, which is sometimes different to the exact syntax represented in the barcode)  - see http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm.
     */
    protected String_ udi;

    /**
     * Lot number assigned by the manufacturer.
     */
    protected String_ lotNumber;

    /**
     * An organization that is responsible for the provision and ongoing maintenance of the device.
     */
    protected ResourceReference owner;

    /**
     * The actual object that is the target of the reference (An organization that is responsible for the provision and ongoing maintenance of the device.)
     */
    protected Organization ownerTarget;

    /**
     * The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location.
     */
    protected ResourceReference location;

    /**
     * The actual object that is the target of the reference (The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location.)
     */
    protected Location locationTarget;

    /**
     * Patient information, if the resource is affixed to a person.
     */
    protected ResourceReference patient;

    /**
     * The actual object that is the target of the reference (Patient information, if the resource is affixed to a person.)
     */
    protected Patient patientTarget;

    /**
     * Contact details for an organization or a particular human that is responsible for the device.
     */
    protected List<Contact> contact = new ArrayList<Contact>();

    /**
     * A network address on which the device may be contacted directly.
     */
    protected Uri url;

    private static final long serialVersionUID = -874134207L;

    public Device() {
      super();
    }

    public Device(CodeableConcept type) {
      super();
      this.type = type;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this device by various organizations. The most likely organizations to assign identifiers are the manufacturer and the owner, though regulatory agencies may also assign an identifier. The identifiers identify the particular device, not the kind of device.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifiers assigned to this device by various organizations. The most likely organizations to assign identifiers are the manufacturer and the owner, though regulatory agencies may also assign an identifier. The identifiers identify the particular device, not the kind of device.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #type} (A kind of this device.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (A kind of this device.)
     */
    public Device setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #manufacturer} (A name of the manufacturer.)
     */
    public String_ getManufacturer() { 
      return this.manufacturer;
    }

    /**
     * @param value {@link #manufacturer} (A name of the manufacturer.)
     */
    public Device setManufacturer(String_ value) { 
      this.manufacturer = value;
      return this;
    }

    /**
     * @return A name of the manufacturer.
     */
    public String getManufacturerSimple() { 
      return this.manufacturer == null ? null : this.manufacturer.getValue();
    }

    /**
     * @param value A name of the manufacturer.
     */
    public Device setManufacturerSimple(String value) { 
      if (value == null)
        this.manufacturer = null;
      else {
        if (this.manufacturer == null)
          this.manufacturer = new String_();
        this.manufacturer.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #model} (The "model" - an identifier assigned by the manufacturer to identify the product by its type. This number is shared by the all devices sold as the same type.)
     */
    public String_ getModel() { 
      return this.model;
    }

    /**
     * @param value {@link #model} (The "model" - an identifier assigned by the manufacturer to identify the product by its type. This number is shared by the all devices sold as the same type.)
     */
    public Device setModel(String_ value) { 
      this.model = value;
      return this;
    }

    /**
     * @return The "model" - an identifier assigned by the manufacturer to identify the product by its type. This number is shared by the all devices sold as the same type.
     */
    public String getModelSimple() { 
      return this.model == null ? null : this.model.getValue();
    }

    /**
     * @param value The "model" - an identifier assigned by the manufacturer to identify the product by its type. This number is shared by the all devices sold as the same type.
     */
    public Device setModelSimple(String value) { 
      if (value == null)
        this.model = null;
      else {
        if (this.model == null)
          this.model = new String_();
        this.model.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #version} (The version of the device, if the device has multiple releases under the same model, or if the device is software or carries firmware.)
     */
    public String_ getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The version of the device, if the device has multiple releases under the same model, or if the device is software or carries firmware.)
     */
    public Device setVersion(String_ value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The version of the device, if the device has multiple releases under the same model, or if the device is software or carries firmware.
     */
    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The version of the device, if the device has multiple releases under the same model, or if the device is software or carries firmware.
     */
    public Device setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new String_();
        this.version.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #expiry} (Date of expiry of this device (if applicable).)
     */
    public Date getExpiry() { 
      return this.expiry;
    }

    /**
     * @param value {@link #expiry} (Date of expiry of this device (if applicable).)
     */
    public Device setExpiry(Date value) { 
      this.expiry = value;
      return this;
    }

    /**
     * @return Date of expiry of this device (if applicable).
     */
    public DateAndTime getExpirySimple() { 
      return this.expiry == null ? null : this.expiry.getValue();
    }

    /**
     * @param value Date of expiry of this device (if applicable).
     */
    public Device setExpirySimple(DateAndTime value) { 
      if (value == null)
        this.expiry = null;
      else {
        if (this.expiry == null)
          this.expiry = new Date();
        this.expiry.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #udi} (FDA Mandated Unique Device Identifier. Use the human readable information (the content that the user sees, which is sometimes different to the exact syntax represented in the barcode)  - see http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm.)
     */
    public String_ getUdi() { 
      return this.udi;
    }

    /**
     * @param value {@link #udi} (FDA Mandated Unique Device Identifier. Use the human readable information (the content that the user sees, which is sometimes different to the exact syntax represented in the barcode)  - see http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm.)
     */
    public Device setUdi(String_ value) { 
      this.udi = value;
      return this;
    }

    /**
     * @return FDA Mandated Unique Device Identifier. Use the human readable information (the content that the user sees, which is sometimes different to the exact syntax represented in the barcode)  - see http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm.
     */
    public String getUdiSimple() { 
      return this.udi == null ? null : this.udi.getValue();
    }

    /**
     * @param value FDA Mandated Unique Device Identifier. Use the human readable information (the content that the user sees, which is sometimes different to the exact syntax represented in the barcode)  - see http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm.
     */
    public Device setUdiSimple(String value) { 
      if (value == null)
        this.udi = null;
      else {
        if (this.udi == null)
          this.udi = new String_();
        this.udi.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #lotNumber} (Lot number assigned by the manufacturer.)
     */
    public String_ getLotNumber() { 
      return this.lotNumber;
    }

    /**
     * @param value {@link #lotNumber} (Lot number assigned by the manufacturer.)
     */
    public Device setLotNumber(String_ value) { 
      this.lotNumber = value;
      return this;
    }

    /**
     * @return Lot number assigned by the manufacturer.
     */
    public String getLotNumberSimple() { 
      return this.lotNumber == null ? null : this.lotNumber.getValue();
    }

    /**
     * @param value Lot number assigned by the manufacturer.
     */
    public Device setLotNumberSimple(String value) { 
      if (value == null)
        this.lotNumber = null;
      else {
        if (this.lotNumber == null)
          this.lotNumber = new String_();
        this.lotNumber.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #owner} (An organization that is responsible for the provision and ongoing maintenance of the device.)
     */
    public ResourceReference getOwner() { 
      return this.owner;
    }

    /**
     * @param value {@link #owner} (An organization that is responsible for the provision and ongoing maintenance of the device.)
     */
    public Device setOwner(ResourceReference value) { 
      this.owner = value;
      return this;
    }

    /**
     * @return {@link #owner} (The actual object that is the target of the reference. An organization that is responsible for the provision and ongoing maintenance of the device.)
     */
    public Organization getOwnerTarget() { 
      return this.ownerTarget;
    }

    /**
     * @param value {@link #owner} (The actual object that is the target of the reference. An organization that is responsible for the provision and ongoing maintenance of the device.)
     */
    public Device setOwnerTarget(Organization value) { 
      this.ownerTarget = value;
      return this;
    }

    /**
     * @return {@link #location} (The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location.)
     */
    public ResourceReference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location.)
     */
    public Device setLocation(ResourceReference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #location} (The actual object that is the target of the reference. The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location.)
     */
    public Location getLocationTarget() { 
      return this.locationTarget;
    }

    /**
     * @param value {@link #location} (The actual object that is the target of the reference. The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location.)
     */
    public Device setLocationTarget(Location value) { 
      this.locationTarget = value;
      return this;
    }

    /**
     * @return {@link #patient} (Patient information, if the resource is affixed to a person.)
     */
    public ResourceReference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (Patient information, if the resource is affixed to a person.)
     */
    public Device setPatient(ResourceReference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} (The actual object that is the target of the reference. Patient information, if the resource is affixed to a person.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} (The actual object that is the target of the reference. Patient information, if the resource is affixed to a person.)
     */
    public Device setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #contact} (Contact details for an organization or a particular human that is responsible for the device.)
     */
    public List<Contact> getContact() { 
      return this.contact;
    }

    // syntactic sugar
    /**
     * @return {@link #contact} (Contact details for an organization or a particular human that is responsible for the device.)
     */
    public Contact addContact() { 
      Contact t = new Contact();
      this.contact.add(t);
      return t;
    }

    /**
     * @return {@link #url} (A network address on which the device may be contacted directly.)
     */
    public Uri getUrl() { 
      return this.url;
    }

    /**
     * @param value {@link #url} (A network address on which the device may be contacted directly.)
     */
    public Device setUrl(Uri value) { 
      this.url = value;
      return this;
    }

    /**
     * @return A network address on which the device may be contacted directly.
     */
    public String getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    /**
     * @param value A network address on which the device may be contacted directly.
     */
    public Device setUrlSimple(String value) { 
      if (value == null)
        this.url = null;
      else {
        if (this.url == null)
          this.url = new Uri();
        this.url.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this device by various organizations. The most likely organizations to assign identifiers are the manufacturer and the owner, though regulatory agencies may also assign an identifier. The identifiers identify the particular device, not the kind of device.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "A kind of this device.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("manufacturer", "string", "A name of the manufacturer.", 0, java.lang.Integer.MAX_VALUE, manufacturer));
        childrenList.add(new Property("model", "string", "The 'model' - an identifier assigned by the manufacturer to identify the product by its type. This number is shared by the all devices sold as the same type.", 0, java.lang.Integer.MAX_VALUE, model));
        childrenList.add(new Property("version", "string", "The version of the device, if the device has multiple releases under the same model, or if the device is software or carries firmware.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("expiry", "date", "Date of expiry of this device (if applicable).", 0, java.lang.Integer.MAX_VALUE, expiry));
        childrenList.add(new Property("udi", "string", "FDA Mandated Unique Device Identifier. Use the human readable information (the content that the user sees, which is sometimes different to the exact syntax represented in the barcode)  - see http://www.fda.gov/MedicalDevices/DeviceRegulationandGuidance/UniqueDeviceIdentification/default.htm.", 0, java.lang.Integer.MAX_VALUE, udi));
        childrenList.add(new Property("lotNumber", "string", "Lot number assigned by the manufacturer.", 0, java.lang.Integer.MAX_VALUE, lotNumber));
        childrenList.add(new Property("owner", "Resource(Organization)", "An organization that is responsible for the provision and ongoing maintenance of the device.", 0, java.lang.Integer.MAX_VALUE, owner));
        childrenList.add(new Property("location", "Resource(Location)", "The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. 'in/with the patient'), or a coded location.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("patient", "Resource(Patient)", "Patient information, if the resource is affixed to a person.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("contact", "Contact", "Contact details for an organization or a particular human that is responsible for the device.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("url", "uri", "A network address on which the device may be contacted directly.", 0, java.lang.Integer.MAX_VALUE, url));
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

