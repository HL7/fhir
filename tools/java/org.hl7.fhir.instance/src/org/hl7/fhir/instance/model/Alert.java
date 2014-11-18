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
 * Prospective warnings of potential issues when providing care to the patient.
 */
public class Alert extends DomainResource {

    public enum AlertStatus {
        ACTIVE, // A current alert that should be displayed to a user. A system may use the category to determine which roles should view the alert.
        INACTIVE, // The alert does not need to be displayed any more.
        ENTEREDINERROR, // The alert was added in error, and should no longer be displayed.
        NULL; // added to help the parsers
        public static AlertStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("inactive".equals(codeString))
          return INACTIVE;
        if ("entered in error".equals(codeString))
          return ENTEREDINERROR;
        throw new Exception("Unknown AlertStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ACTIVE: return "active";
            case INACTIVE: return "inactive";
            case ENTEREDINERROR: return "entered in error";
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
            case ACTIVE: return "active";
            case INACTIVE: return "inactive";
            case ENTEREDINERROR: return "entered in error";
            default: return "?";
          }
        }
    }

  public static class AlertStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return AlertStatus.ACTIVE;
        if ("inactive".equals(codeString))
          return AlertStatus.INACTIVE;
        if ("entered in error".equals(codeString))
          return AlertStatus.ENTEREDINERROR;
        throw new Exception("Unknown AlertStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AlertStatus.ACTIVE)
        return "active";
      if (code == AlertStatus.INACTIVE)
        return "inactive";
      if (code == AlertStatus.ENTEREDINERROR)
        return "entered in error";
      return "?";
      }
    }

    /**
     * Identifier assigned to the alert for external use (outside the FHIR environment).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Allows an alert to be divided into different categories like clinical, administrative etc.
     */
    protected CodeableConcept category;

    /**
     * Supports basic workflow.
     */
    protected Enumeration<AlertStatus> status;

    /**
     * The person who this alert concerns.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The person who this alert concerns.)
     */
    protected Patient subjectTarget;

    /**
     * The person or device that created the alert.
     */
    protected Reference author;

    /**
     * The actual object that is the target of the reference (The person or device that created the alert.)
     */
    protected Resource authorTarget;

    /**
     * The textual component of the alert to display to the user.
     */
    protected StringType note;

    private static final long serialVersionUID = -1397907794L;

    public Alert() {
      super();
    }

    public Alert(Enumeration<AlertStatus> status, Reference subject, StringType note) {
      super();
      this.status = status;
      this.subject = subject;
      this.note = note;
    }

    /**
     * @return {@link #identifier} (Identifier assigned to the alert for external use (outside the FHIR environment).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Identifier assigned to the alert for external use (outside the FHIR environment).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #category} (Allows an alert to be divided into different categories like clinical, administrative etc.)
     */
    public CodeableConcept getCategory() { 
      return this.category;
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
      return this.status;
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
          this.status = new Enumeration<AlertStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #subject} (The person who this alert concerns.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The person who this alert concerns.)
     */
    public Alert setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person who this alert concerns.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person who this alert concerns.)
     */
    public Alert setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #author} (The person or device that created the alert.)
     */
    public Reference getAuthor() { 
      return this.author;
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
     * @return {@link #note} (The textual component of the alert to display to the user.). This is the underlying object with id, value and extensions. The accessor "getNote" gives direct access to the value
     */
    public StringType getNoteElement() { 
      return this.note;
    }

    /**
     * @param value {@link #note} (The textual component of the alert to display to the user.). This is the underlying object with id, value and extensions. The accessor "getNote" gives direct access to the value
     */
    public Alert setNoteElement(StringType value) { 
      this.note = value;
      return this;
    }

    /**
     * @return The textual component of the alert to display to the user.
     */
    public String getNote() { 
      return this.note == null ? null : this.note.getValue();
    }

    /**
     * @param value The textual component of the alert to display to the user.
     */
    public Alert setNote(String value) { 
        if (this.note == null)
          this.note = new StringType();
        this.note.setValue(value);
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier assigned to the alert for external use (outside the FHIR environment).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("category", "CodeableConcept", "Allows an alert to be divided into different categories like clinical, administrative etc.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("status", "code", "Supports basic workflow.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("subject", "Reference(Patient)", "The person who this alert concerns.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("author", "Reference(Practitioner|Patient|Device)", "The person or device that created the alert.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("note", "string", "The textual component of the alert to display to the user.", 0, java.lang.Integer.MAX_VALUE, note));
      }

      public Alert copy() {
        Alert dst = new Alert();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.category = category == null ? null : category.copy();
        dst.status = status == null ? null : status.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.author = author == null ? null : author.copy();
        dst.note = note == null ? null : note.copy();
        return dst;
      }

      protected Alert typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Alert;
   }


}

