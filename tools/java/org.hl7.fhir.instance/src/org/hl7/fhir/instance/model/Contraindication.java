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
 * Indicates an actual or potential clinical issue with or between one or more active or proposed clinical actions for a patient.  E.g. Drug-drug interaction, Ineffective treatment frequency, Procedure-condition conflict, etc.
 */
public class Contraindication extends DomainResource {

    public static class ContraindicationMitigationComponent extends BackboneElement {
        /**
         * Describes the action that was taken or the observation that was made that reduces/eliminates the risk associated with the identified contraindication.
         */
        protected CodeableConcept action;

        /**
         * Indicates when the mitigating action was documented.
         */
        protected DateTimeType date;

        /**
         * Identifies the practitioner who determined the mitigation and takes responsibility for the mitigation step occurring.
         */
        protected Reference author;

        /**
         * The actual object that is the target of the reference (Identifies the practitioner who determined the mitigation and takes responsibility for the mitigation step occurring.)
         */
        protected Practitioner authorTarget;

        private static final long serialVersionUID = -1994768436L;

      public ContraindicationMitigationComponent() {
        super();
      }

      public ContraindicationMitigationComponent(CodeableConcept action) {
        super();
        this.action = action;
      }

        /**
         * @return {@link #action} (Describes the action that was taken or the observation that was made that reduces/eliminates the risk associated with the identified contraindication.)
         */
        public CodeableConcept getAction() { 
          return this.action;
        }

        /**
         * @param value {@link #action} (Describes the action that was taken or the observation that was made that reduces/eliminates the risk associated with the identified contraindication.)
         */
        public ContraindicationMitigationComponent setAction(CodeableConcept value) { 
          this.action = value;
          return this;
        }

        /**
         * @return {@link #date} (Indicates when the mitigating action was documented.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
         */
        public DateTimeType getDateElement() { 
          return this.date;
        }

        /**
         * @param value {@link #date} (Indicates when the mitigating action was documented.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
         */
        public ContraindicationMitigationComponent setDateElement(DateTimeType value) { 
          this.date = value;
          return this;
        }

        /**
         * @return Indicates when the mitigating action was documented.
         */
        public DateAndTime getDate() { 
          return this.date == null ? null : this.date.getValue();
        }

        /**
         * @param value Indicates when the mitigating action was documented.
         */
        public ContraindicationMitigationComponent setDate(DateAndTime value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateTimeType();
            this.date.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #author} (Identifies the practitioner who determined the mitigation and takes responsibility for the mitigation step occurring.)
         */
        public Reference getAuthor() { 
          return this.author;
        }

        /**
         * @param value {@link #author} (Identifies the practitioner who determined the mitigation and takes responsibility for the mitigation step occurring.)
         */
        public ContraindicationMitigationComponent setAuthor(Reference value) { 
          this.author = value;
          return this;
        }

        /**
         * @return {@link #author} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identifies the practitioner who determined the mitigation and takes responsibility for the mitigation step occurring.)
         */
        public Practitioner getAuthorTarget() { 
          return this.authorTarget;
        }

        /**
         * @param value {@link #author} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identifies the practitioner who determined the mitigation and takes responsibility for the mitigation step occurring.)
         */
        public ContraindicationMitigationComponent setAuthorTarget(Practitioner value) { 
          this.authorTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("action", "CodeableConcept", "Describes the action that was taken or the observation that was made that reduces/eliminates the risk associated with the identified contraindication.", 0, java.lang.Integer.MAX_VALUE, action));
          childrenList.add(new Property("date", "dateTime", "Indicates when the mitigating action was documented.", 0, java.lang.Integer.MAX_VALUE, date));
          childrenList.add(new Property("author", "Reference(Practitioner)", "Identifies the practitioner who determined the mitigation and takes responsibility for the mitigation step occurring.", 0, java.lang.Integer.MAX_VALUE, author));
        }

      public ContraindicationMitigationComponent copy() {
        ContraindicationMitigationComponent dst = new ContraindicationMitigationComponent();
        copyValues(dst);
        dst.action = action == null ? null : action.copy();
        dst.date = date == null ? null : date.copy();
        dst.author = author == null ? null : author.copy();
        return dst;
      }

  }

    /**
     * Indicates the patient whose record the contraindication is associated with.
     */
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (Indicates the patient whose record the contraindication is associated with.)
     */
    protected Patient patientTarget;

    /**
     * Identifies the general type of issue identified.
     */
    protected CodeableConcept category;

    /**
     * Indicates the degree of importance associated with the identified issue based on the potential impact on the patient.
     */
    protected CodeType severity;

    /**
     * Indicates the resource representing the current activity or proposed activity that.
     */
    protected List<Reference> implicated = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Indicates the resource representing the current activity or proposed activity that.)
     */
    protected List<Resource> implicatedTarget = new ArrayList<Resource>();


    /**
     * A textual explanation of the contraindication.
     */
    protected StringType detail;

    /**
     * The date or date-time when the contraindication was initially identified.
     */
    protected DateTimeType date;

    /**
     * Identifies the provider or software that identified the.
     */
    protected Reference author;

    /**
     * The actual object that is the target of the reference (Identifies the provider or software that identified the.)
     */
    protected Resource authorTarget;

    /**
     * Business identifier associated with the contraindication record.
     */
    protected Identifier identifier;

    /**
     * The literature, knowledge-base or similar reference that describes the propensity for the contraindication identified.
     */
    protected UriType reference;

    /**
     * Indicates an action that has been taken or is committed to to reduce or eliminate the likelihood of the risk identified by the contraindicaiton from manifesting.  Can also reflect an observation of known mitigating factors that may reduce/eliminate the need for any action.
     */
    protected List<ContraindicationMitigationComponent> mitigation = new ArrayList<ContraindicationMitigationComponent>();

    private static final long serialVersionUID = -1374855035L;

    public Contraindication() {
      super();
    }

    /**
     * @return {@link #patient} (Indicates the patient whose record the contraindication is associated with.)
     */
    public Reference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (Indicates the patient whose record the contraindication is associated with.)
     */
    public Contraindication setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Indicates the patient whose record the contraindication is associated with.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Indicates the patient whose record the contraindication is associated with.)
     */
    public Contraindication setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #category} (Identifies the general type of issue identified.)
     */
    public CodeableConcept getCategory() { 
      return this.category;
    }

    /**
     * @param value {@link #category} (Identifies the general type of issue identified.)
     */
    public Contraindication setCategory(CodeableConcept value) { 
      this.category = value;
      return this;
    }

    /**
     * @return {@link #severity} (Indicates the degree of importance associated with the identified issue based on the potential impact on the patient.). This is the underlying object with id, value and extensions. The accessor "getSeverity" gives direct access to the value
     */
    public CodeType getSeverityElement() { 
      return this.severity;
    }

    /**
     * @param value {@link #severity} (Indicates the degree of importance associated with the identified issue based on the potential impact on the patient.). This is the underlying object with id, value and extensions. The accessor "getSeverity" gives direct access to the value
     */
    public Contraindication setSeverityElement(CodeType value) { 
      this.severity = value;
      return this;
    }

    /**
     * @return Indicates the degree of importance associated with the identified issue based on the potential impact on the patient.
     */
    public String getSeverity() { 
      return this.severity == null ? null : this.severity.getValue();
    }

    /**
     * @param value Indicates the degree of importance associated with the identified issue based on the potential impact on the patient.
     */
    public Contraindication setSeverity(String value) { 
      if (Utilities.noString(value))
        this.severity = null;
      else {
        if (this.severity == null)
          this.severity = new CodeType();
        this.severity.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #implicated} (Indicates the resource representing the current activity or proposed activity that.)
     */
    public List<Reference> getImplicated() { 
      return this.implicated;
    }

    /**
     * @return {@link #implicated} (Indicates the resource representing the current activity or proposed activity that.)
     */
    // syntactic sugar
    public Reference addImplicated() { //3
      Reference t = new Reference();
      this.implicated.add(t);
      return t;
    }

    /**
     * @return {@link #implicated} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Indicates the resource representing the current activity or proposed activity that.)
     */
    public List<Resource> getImplicatedTarget() { 
      return this.implicatedTarget;
    }

    /**
     * @return {@link #detail} (A textual explanation of the contraindication.). This is the underlying object with id, value and extensions. The accessor "getDetail" gives direct access to the value
     */
    public StringType getDetailElement() { 
      return this.detail;
    }

    /**
     * @param value {@link #detail} (A textual explanation of the contraindication.). This is the underlying object with id, value and extensions. The accessor "getDetail" gives direct access to the value
     */
    public Contraindication setDetailElement(StringType value) { 
      this.detail = value;
      return this;
    }

    /**
     * @return A textual explanation of the contraindication.
     */
    public String getDetail() { 
      return this.detail == null ? null : this.detail.getValue();
    }

    /**
     * @param value A textual explanation of the contraindication.
     */
    public Contraindication setDetail(String value) { 
      if (Utilities.noString(value))
        this.detail = null;
      else {
        if (this.detail == null)
          this.detail = new StringType();
        this.detail.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #date} (The date or date-time when the contraindication was initially identified.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date or date-time when the contraindication was initially identified.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public Contraindication setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date or date-time when the contraindication was initially identified.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date or date-time when the contraindication was initially identified.
     */
    public Contraindication setDate(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTimeType();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #author} (Identifies the provider or software that identified the.)
     */
    public Reference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (Identifies the provider or software that identified the.)
     */
    public Contraindication setAuthor(Reference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #author} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identifies the provider or software that identified the.)
     */
    public Resource getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @param value {@link #author} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identifies the provider or software that identified the.)
     */
    public Contraindication setAuthorTarget(Resource value) { 
      this.authorTarget = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Business identifier associated with the contraindication record.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Business identifier associated with the contraindication record.)
     */
    public Contraindication setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #reference} (The literature, knowledge-base or similar reference that describes the propensity for the contraindication identified.). This is the underlying object with id, value and extensions. The accessor "getReference" gives direct access to the value
     */
    public UriType getReferenceElement() { 
      return this.reference;
    }

    /**
     * @param value {@link #reference} (The literature, knowledge-base or similar reference that describes the propensity for the contraindication identified.). This is the underlying object with id, value and extensions. The accessor "getReference" gives direct access to the value
     */
    public Contraindication setReferenceElement(UriType value) { 
      this.reference = value;
      return this;
    }

    /**
     * @return The literature, knowledge-base or similar reference that describes the propensity for the contraindication identified.
     */
    public String getReference() { 
      return this.reference == null ? null : this.reference.getValue();
    }

    /**
     * @param value The literature, knowledge-base or similar reference that describes the propensity for the contraindication identified.
     */
    public Contraindication setReference(String value) { 
      if (Utilities.noString(value))
        this.reference = null;
      else {
        if (this.reference == null)
          this.reference = new UriType();
        this.reference.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #mitigation} (Indicates an action that has been taken or is committed to to reduce or eliminate the likelihood of the risk identified by the contraindicaiton from manifesting.  Can also reflect an observation of known mitigating factors that may reduce/eliminate the need for any action.)
     */
    public List<ContraindicationMitigationComponent> getMitigation() { 
      return this.mitigation;
    }

    /**
     * @return {@link #mitigation} (Indicates an action that has been taken or is committed to to reduce or eliminate the likelihood of the risk identified by the contraindicaiton from manifesting.  Can also reflect an observation of known mitigating factors that may reduce/eliminate the need for any action.)
     */
    // syntactic sugar
    public ContraindicationMitigationComponent addMitigation() { //3
      ContraindicationMitigationComponent t = new ContraindicationMitigationComponent();
      this.mitigation.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("patient", "Reference(Patient)", "Indicates the patient whose record the contraindication is associated with.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("category", "CodeableConcept", "Identifies the general type of issue identified.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("severity", "code", "Indicates the degree of importance associated with the identified issue based on the potential impact on the patient.", 0, java.lang.Integer.MAX_VALUE, severity));
        childrenList.add(new Property("implicated", "Reference(Any)", "Indicates the resource representing the current activity or proposed activity that.", 0, java.lang.Integer.MAX_VALUE, implicated));
        childrenList.add(new Property("detail", "string", "A textual explanation of the contraindication.", 0, java.lang.Integer.MAX_VALUE, detail));
        childrenList.add(new Property("date", "dateTime", "The date or date-time when the contraindication was initially identified.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("author", "Reference(Practitioner|Device)", "Identifies the provider or software that identified the.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("identifier", "Identifier", "Business identifier associated with the contraindication record.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("reference", "uri", "The literature, knowledge-base or similar reference that describes the propensity for the contraindication identified.", 0, java.lang.Integer.MAX_VALUE, reference));
        childrenList.add(new Property("mitigation", "", "Indicates an action that has been taken or is committed to to reduce or eliminate the likelihood of the risk identified by the contraindicaiton from manifesting.  Can also reflect an observation of known mitigating factors that may reduce/eliminate the need for any action.", 0, java.lang.Integer.MAX_VALUE, mitigation));
      }

      public Contraindication copy() {
        Contraindication dst = new Contraindication();
        copyValues(dst);
        dst.patient = patient == null ? null : patient.copy();
        dst.category = category == null ? null : category.copy();
        dst.severity = severity == null ? null : severity.copy();
        dst.implicated = new ArrayList<Reference>();
        for (Reference i : implicated)
          dst.implicated.add(i.copy());
        dst.detail = detail == null ? null : detail.copy();
        dst.date = date == null ? null : date.copy();
        dst.author = author == null ? null : author.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.mitigation = new ArrayList<ContraindicationMitigationComponent>();
        for (ContraindicationMitigationComponent i : mitigation)
          dst.mitigation.add(i.copy());
        return dst;
      }

      protected Contraindication typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Contraindication;
   }


}

