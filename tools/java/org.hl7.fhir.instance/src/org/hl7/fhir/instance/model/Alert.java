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

// Generated on Wed, Mar 25, 2015 13:49+1100 for FHIR v0.4.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.instance.model.annotations.ResourceDef;
import org.hl7.fhir.instance.model.annotations.SearchParamDefinition;
import org.hl7.fhir.instance.model.annotations.Block;
import org.hl7.fhir.instance.model.annotations.Child;
import org.hl7.fhir.instance.model.annotations.Description;
/**
 * Prospective warnings of potential issues when providing care to the patient.
 */
@ResourceDef(name="Alert", profile="http://hl7.org/fhir/Profile/Alert")
public class Alert extends DomainResource {

    public enum AlertStatus {
        /**
         * A current alert that should be displayed to a user. A system may use the category to determine which roles should view the alert.
         */
        ACTIVE, 
        /**
         * The alert does not need to be displayed any more.
         */
        INACTIVE, 
        /**
         * The alert was added in error, and should no longer be displayed.
         */
        ENTEREDINERROR, 
        /**
         * added to help the parsers
         */
        NULL;
        public static AlertStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("inactive".equals(codeString))
          return INACTIVE;
        if ("entered-in-error".equals(codeString))
          return ENTEREDINERROR;
        throw new Exception("Unknown AlertStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ACTIVE: return "active";
            case INACTIVE: return "inactive";
            case ENTEREDINERROR: return "entered-in-error";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case ACTIVE: return "";
            case INACTIVE: return "";
            case ENTEREDINERROR: return "";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ACTIVE: return "A current alert that should be displayed to a user. A system may use the category to determine which roles should view the alert.";
            case INACTIVE: return "The alert does not need to be displayed any more.";
            case ENTEREDINERROR: return "The alert was added in error, and should no longer be displayed.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ACTIVE: return "Active";
            case INACTIVE: return "Inactive";
            case ENTEREDINERROR: return "Entered In Error";
            default: return "?";
          }
        }
    }

  public static class AlertStatusEnumFactory implements EnumFactory<AlertStatus> {
    public AlertStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return AlertStatus.ACTIVE;
        if ("inactive".equals(codeString))
          return AlertStatus.INACTIVE;
        if ("entered-in-error".equals(codeString))
          return AlertStatus.ENTEREDINERROR;
        throw new IllegalArgumentException("Unknown AlertStatus code '"+codeString+"'");
        }
    public String toCode(AlertStatus code) {
      if (code == AlertStatus.ACTIVE)
        return "active";
      if (code == AlertStatus.INACTIVE)
        return "inactive";
      if (code == AlertStatus.ENTEREDINERROR)
        return "entered-in-error";
      return "?";
      }
    }

    /**
     * Identifier assigned to the alert for external use (outside the FHIR environment).
     */
    @Child(name ="identifier", type={Identifier.class}, order=0, min=0, max=Child.MAX_UNLIMITED)
    @Description(shortDefinition="Business identifier", formalDefinition="Identifier assigned to the alert for external use (outside the FHIR environment)." )
    protected List<Identifier> identifier;

    /**
     * Allows an alert to be divided into different categories like clinical, administrative etc.
     */
    @Child(name ="category", type={CodeableConcept.class}, order=1, min=0, max=1)
    @Description(shortDefinition="Clinical, administrative, etc.", formalDefinition="Allows an alert to be divided into different categories like clinical, administrative etc." )
    protected CodeableConcept category;

    /**
     * Supports basic workflow.
     */
    @Child(name ="status", type={CodeType.class}, order=2, min=1, max=1)
    @Description(shortDefinition="active | inactive | entered-in-error", formalDefinition="Supports basic workflow." )
    protected Enumeration<AlertStatus> status;

    /**
     * The person who this alert concerns.
     */
    @Child(name ="patient", type={Patient.class}, order=3, min=1, max=1)
    @Description(shortDefinition="Who is alert about?", formalDefinition="The person who this alert concerns." )
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (The person who this alert concerns.)
     */
    protected Patient patientTarget;

    /**
     * The person or device that created the alert.
     */
    @Child(name ="author", type={Practitioner.class, Patient.class, Device.class}, order=4, min=0, max=1)
    @Description(shortDefinition="Alert creator", formalDefinition="The person or device that created the alert." )
    protected Reference author;

    /**
     * The actual object that is the target of the reference (The person or device that created the alert.)
     */
    protected Resource authorTarget;

    /**
     * The coded value or textual component of the alert to display to the user.
     */
    @Child(name ="note", type={CodeableConcept.class}, order=5, min=1, max=1)
    @Description(shortDefinition="Partially deaf, Requires easy open caps, No permanent address, etc.", formalDefinition="The coded value or textual component of the alert to display to the user." )
    protected CodeableConcept note;

    private static final long serialVersionUID = -747954870L;

    public Alert() {
      super();
    }

    public Alert(Enumeration<AlertStatus> status, Reference patient, CodeableConcept note) {
      super();
      this.status = status;
      this.patient = patient;
      this.note = note;
    }

    /**
     * @return {@link #identifier} (Identifier assigned to the alert for external use (outside the FHIR environment).)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #identifier} (Identifier assigned to the alert for external use (outside the FHIR environment).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    // syntactic sugar
    public Alert addIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return this;
    }

    /**
     * @return {@link #category} (Allows an alert to be divided into different categories like clinical, administrative etc.)
     */
    public CodeableConcept getCategory() { 
      if (this.category == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Alert.category");
        else if (Configuration.doAutoCreate())
          this.category = new CodeableConcept(); // cc
      return this.category;
    }

    public boolean hasCategory() { 
      return this.category != null && !this.category.isEmpty();
    }

    /**
     * @param value {@link #category} (Allows an alert to be divided into different categories like clinical, administrative etc.)
     */
    public Alert setCategory(CodeableConcept value) { 
      this.category = value;
      return this;
    }

    /**
     * @return {@link #status} (Supports basic workflow.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<AlertStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Alert.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<AlertStatus>(new AlertStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (Supports basic workflow.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Alert setStatusElement(Enumeration<AlertStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Supports basic workflow.
     */
    public AlertStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Supports basic workflow.
     */
    public Alert setStatus(AlertStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<AlertStatus>(new AlertStatusEnumFactory());
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #patient} (The person who this alert concerns.)
     */
    public Reference getPatient() { 
      if (this.patient == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Alert.patient");
        else if (Configuration.doAutoCreate())
          this.patient = new Reference(); // cc
      return this.patient;
    }

    public boolean hasPatient() { 
      return this.patient != null && !this.patient.isEmpty();
    }

    /**
     * @param value {@link #patient} (The person who this alert concerns.)
     */
    public Alert setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person who this alert concerns.)
     */
    public Patient getPatientTarget() { 
      if (this.patientTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Alert.patient");
        else if (Configuration.doAutoCreate())
          this.patientTarget = new Patient(); // aa
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person who this alert concerns.)
     */
    public Alert setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #author} (The person or device that created the alert.)
     */
    public Reference getAuthor() { 
      if (this.author == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Alert.author");
        else if (Configuration.doAutoCreate())
          this.author = new Reference(); // cc
      return this.author;
    }

    public boolean hasAuthor() { 
      return this.author != null && !this.author.isEmpty();
    }

    /**
     * @param value {@link #author} (The person or device that created the alert.)
     */
    public Alert setAuthor(Reference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #author} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person or device that created the alert.)
     */
    public Resource getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @param value {@link #author} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person or device that created the alert.)
     */
    public Alert setAuthorTarget(Resource value) { 
      this.authorTarget = value;
      return this;
    }

    /**
     * @return {@link #note} (The coded value or textual component of the alert to display to the user.)
     */
    public CodeableConcept getNote() { 
      if (this.note == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Alert.note");
        else if (Configuration.doAutoCreate())
          this.note = new CodeableConcept(); // cc
      return this.note;
    }

    public boolean hasNote() { 
      return this.note != null && !this.note.isEmpty();
    }

    /**
     * @param value {@link #note} (The coded value or textual component of the alert to display to the user.)
     */
    public Alert setNote(CodeableConcept value) { 
      this.note = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier assigned to the alert for external use (outside the FHIR environment).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("category", "CodeableConcept", "Allows an alert to be divided into different categories like clinical, administrative etc.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("status", "code", "Supports basic workflow.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("patient", "Reference(Patient)", "The person who this alert concerns.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("author", "Reference(Practitioner|Patient|Device)", "The person or device that created the alert.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("note", "CodeableConcept", "The coded value or textual component of the alert to display to the user.", 0, java.lang.Integer.MAX_VALUE, note));
      }

      public Alert copy() {
        Alert dst = new Alert();
        copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        dst.category = category == null ? null : category.copy();
        dst.status = status == null ? null : status.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.author = author == null ? null : author.copy();
        dst.note = note == null ? null : note.copy();
        return dst;
      }

      protected Alert typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof Alert))
          return false;
        Alert o = (Alert) other;
        return compareDeep(identifier, o.identifier, true) && compareDeep(category, o.category, true) && compareDeep(status, o.status, true)
           && compareDeep(patient, o.patient, true) && compareDeep(author, o.author, true) && compareDeep(note, o.note, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof Alert))
          return false;
        Alert o = (Alert) other;
        return compareValues(status, o.status, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (identifier == null || identifier.isEmpty()) && (category == null || category.isEmpty())
           && (status == null || status.isEmpty()) && (patient == null || patient.isEmpty()) && (author == null || author.isEmpty())
           && (note == null || note.isEmpty());
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Alert;
   }

  @SearchParamDefinition(name="author", path="Alert.author", description="Alert creator", type="reference" )
  public static final String SP_AUTHOR = "author";
  @SearchParamDefinition(name="patient", path="Alert.patient", description="The identity of a subject to list alerts for", type="reference" )
  public static final String SP_PATIENT = "patient";
  @SearchParamDefinition(name="subject", path="Alert.patient", description="The identity of a subject to list alerts for", type="reference" )
  public static final String SP_SUBJECT = "subject";

}

