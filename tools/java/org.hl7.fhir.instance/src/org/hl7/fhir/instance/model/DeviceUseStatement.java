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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * A record of a device being used by a patient where the record is the result of a report from the patient or another clinician.
 */
public class DeviceUseStatement extends DomainResource {

    /**
     * Body site where the device was used.
     */
    protected List<CodeableConcept> bodySite = new ArrayList<CodeableConcept>();

    /**
     * The time period over which the device was used.
     */
    protected Period whenUsed;

    /**
     * The details of the device used.
     */
    protected Reference device;

    /**
     * The actual object that is the target of the reference (The details of the device used.)
     */
    protected Device deviceTarget;

    /**
     * An external identifier for this statement such as an IRI.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Reason or justification for the use of the device.
     */
    protected List<CodeableConcept> indication = new ArrayList<CodeableConcept>();

    /**
     * Details about the device statement that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.
     */
    protected List<StringType> notes = new ArrayList<StringType>();

    /**
     * The time at which the statement was made/recorded.
     */
    protected DateTimeType recordedOn;

    /**
     * The patient who used the device.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who used the device.)
     */
    protected Patient subjectTarget;

    /**
     * How often the device was used.
     */
    protected Type timing;

    private static final long serialVersionUID = -63820660L;

    public DeviceUseStatement() {
      super();
    }

    public DeviceUseStatement(Reference device, Reference subject) {
      super();
      this.device = device;
      this.subject = subject;
    }

    /**
     * @return {@link #bodySite} (Body site where the device was used.)
     */
    public List<CodeableConcept> getBodySite() { 
      return this.bodySite;
    }

    /**
     * @return {@link #bodySite} (Body site where the device was used.)
     */
    // syntactic sugar
    public CodeableConcept addBodySite() { //3
      CodeableConcept t = new CodeableConcept();
      this.bodySite.add(t);
      return t;
    }

    /**
     * @return {@link #whenUsed} (The time period over which the device was used.)
     */
    public Period getWhenUsed() { 
      return this.whenUsed;
    }

    /**
     * @param value {@link #whenUsed} (The time period over which the device was used.)
     */
    public DeviceUseStatement setWhenUsed(Period value) { 
      this.whenUsed = value;
      return this;
    }

    /**
     * @return {@link #device} (The details of the device used.)
     */
    public Reference getDevice() { 
      return this.device;
    }

    /**
     * @param value {@link #device} (The details of the device used.)
     */
    public DeviceUseStatement setDevice(Reference value) { 
      this.device = value;
      return this;
    }

    /**
     * @return {@link #device} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The details of the device used.)
     */
    public Device getDeviceTarget() { 
      return this.deviceTarget;
    }

    /**
     * @param value {@link #device} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The details of the device used.)
     */
    public DeviceUseStatement setDeviceTarget(Device value) { 
      this.deviceTarget = value;
      return this;
    }

    /**
     * @return {@link #identifier} (An external identifier for this statement such as an IRI.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (An external identifier for this statement such as an IRI.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #indication} (Reason or justification for the use of the device.)
     */
    public List<CodeableConcept> getIndication() { 
      return this.indication;
    }

    /**
     * @return {@link #indication} (Reason or justification for the use of the device.)
     */
    // syntactic sugar
    public CodeableConcept addIndication() { //3
      CodeableConcept t = new CodeableConcept();
      this.indication.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (Details about the device statement that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public List<StringType> getNotes() { 
      return this.notes;
    }

    /**
     * @return {@link #notes} (Details about the device statement that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    // syntactic sugar
    public StringType addNotesElement() {//2 
      StringType t = new StringType();
      this.notes.add(t);
      return t;
    }

    /**
     * @param value {@link #notes} (Details about the device statement that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public DeviceUseStatement addNotes(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.notes.add(t);
      return this;
    }

    /**
     * @param value {@link #notes} (Details about the device statement that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public boolean hasNotes(String value) { 
      for (StringType v : this.notes)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #recordedOn} (The time at which the statement was made/recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedOn" gives direct access to the value
     */
    public DateTimeType getRecordedOnElement() { 
      return this.recordedOn;
    }

    /**
     * @param value {@link #recordedOn} (The time at which the statement was made/recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedOn" gives direct access to the value
     */
    public DeviceUseStatement setRecordedOnElement(DateTimeType value) { 
      this.recordedOn = value;
      return this;
    }

    /**
     * @return The time at which the statement was made/recorded.
     */
    public DateAndTime getRecordedOn() { 
      return this.recordedOn == null ? null : this.recordedOn.getValue();
    }

    /**
     * @param value The time at which the statement was made/recorded.
     */
    public DeviceUseStatement setRecordedOn(DateAndTime value) { 
      if (value == null)
        this.recordedOn = null;
      else {
        if (this.recordedOn == null)
          this.recordedOn = new DateTimeType();
        this.recordedOn.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (The patient who used the device.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who used the device.)
     */
    public DeviceUseStatement setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who used the device.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who used the device.)
     */
    public DeviceUseStatement setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #timing} (How often the device was used.)
     */
    public Type getTiming() { 
      return this.timing;
    }

    /**
     * @param value {@link #timing} (How often the device was used.)
     */
    public DeviceUseStatement setTiming(Type value) { 
      this.timing = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("bodySite", "CodeableConcept", "Body site where the device was used.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("whenUsed", "Period", "The time period over which the device was used.", 0, java.lang.Integer.MAX_VALUE, whenUsed));
        childrenList.add(new Property("device", "Reference(Device)", "The details of the device used.", 0, java.lang.Integer.MAX_VALUE, device));
        childrenList.add(new Property("identifier", "Identifier", "An external identifier for this statement such as an IRI.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("indication", "CodeableConcept", "Reason or justification for the use of the device.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("notes", "string", "Details about the device statement that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.", 0, java.lang.Integer.MAX_VALUE, notes));
        childrenList.add(new Property("recordedOn", "dateTime", "The time at which the statement was made/recorded.", 0, java.lang.Integer.MAX_VALUE, recordedOn));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who used the device.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("timing[x]", "Timing|Period|dateTime", "How often the device was used.", 0, java.lang.Integer.MAX_VALUE, timing));
      }

      public DeviceUseStatement copy() {
        DeviceUseStatement dst = new DeviceUseStatement();
        copyValues(dst);
        dst.bodySite = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : bodySite)
          dst.bodySite.add(i.copy());
        dst.whenUsed = whenUsed == null ? null : whenUsed.copy();
        dst.device = device == null ? null : device.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.indication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : indication)
          dst.indication.add(i.copy());
        dst.notes = new ArrayList<StringType>();
        for (StringType i : notes)
          dst.notes.add(i.copy());
        dst.recordedOn = recordedOn == null ? null : recordedOn.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.timing = timing == null ? null : timing.copy();
        return dst;
      }

      protected DeviceUseStatement typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceUseStatement;
   }


}

