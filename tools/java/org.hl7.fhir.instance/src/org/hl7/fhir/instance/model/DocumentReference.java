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
 * A reference to a document.
 */
public class DocumentReference extends Resource {

    public enum DocumentReferenceStatus {
        current, // This is the current reference for this document.
        superceded, // This reference has been superseded by another reference.
        error, // This reference was created in error.
        Null; // added to help the parsers
        public static DocumentReferenceStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("current".equals(codeString))
          return current;
        if ("superceded".equals(codeString))
          return superceded;
        if ("error".equals(codeString))
          return error;
        throw new Exception("Unknown DocumentReferenceStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case current: return "current";
            case superceded: return "superceded";
            case error: return "error";
            default: return "?";
          }
        }
    }

  public static class DocumentReferenceStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("current".equals(codeString))
          return DocumentReferenceStatus.current;
        if ("superceded".equals(codeString))
          return DocumentReferenceStatus.superceded;
        if ("error".equals(codeString))
          return DocumentReferenceStatus.error;
        throw new Exception("Unknown DocumentReferenceStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DocumentReferenceStatus.current)
        return "current";
      if (code == DocumentReferenceStatus.superceded)
        return "superceded";
      if (code == DocumentReferenceStatus.error)
        return "error";
      return "?";
      }
    }

    public static class DocumentReferenceServiceComponent extends BackboneElement {
        /**
         * The type of the service that can be used to access the documents.
         */
        protected CodeableConcept type;

        /**
         * Where the service end-point is located.
         */
        protected String_ address;

        /**
         * A list of named parameters that is used in the service call.
         */
        protected List<DocumentReferenceServiceParameterComponent> parameter = new ArrayList<DocumentReferenceServiceParameterComponent>();

      public DocumentReferenceServiceComponent() {
        super();
      }

      public DocumentReferenceServiceComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        public CodeableConcept getType() { 
          return this.type;
        }

        public DocumentReferenceServiceComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        public String_ getAddress() { 
          return this.address;
        }

        public DocumentReferenceServiceComponent setAddress(String_ value) { 
          this.address = value;
          return this;
        }

        public String getAddressSimple() { 
          return this.address == null ? null : this.address.getValue();
        }

        public DocumentReferenceServiceComponent setAddressSimple(String value) { 
          if (value == null)
            this.address = null;
          else {
            if (this.address == null)
              this.address = new String_();
            this.address.setValue(value);
          }
          return this;
        }

        public List<DocumentReferenceServiceParameterComponent> getParameter() { 
          return this.parameter;
        }

    // syntactic sugar
        public DocumentReferenceServiceParameterComponent addParameter() { 
          DocumentReferenceServiceParameterComponent t = new DocumentReferenceServiceParameterComponent();
          this.parameter.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "The type of the service that can be used to access the documents.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("address", "string", "Where the service end-point is located.", 0, java.lang.Integer.MAX_VALUE, address));
          childrenList.add(new Property("parameter", "", "A list of named parameters that is used in the service call.", 0, java.lang.Integer.MAX_VALUE, parameter));
        }

      public DocumentReferenceServiceComponent copy(DocumentReference e) {
        DocumentReferenceServiceComponent dst = new DocumentReferenceServiceComponent();
        dst.type = type == null ? null : type.copy();
        dst.address = address == null ? null : address.copy();
        dst.parameter = new ArrayList<DocumentReferenceServiceParameterComponent>();
        for (DocumentReferenceServiceParameterComponent i : parameter)
          dst.parameter.add(i.copy(e));
        return dst;
      }

  }

    public static class DocumentReferenceServiceParameterComponent extends BackboneElement {
        /**
         * The name of a parameter.
         */
        protected String_ name;

        /**
         * The value of the named parameter.
         */
        protected String_ value;

      public DocumentReferenceServiceParameterComponent() {
        super();
      }

      public DocumentReferenceServiceParameterComponent(String_ name) {
        super();
        this.name = name;
      }

        public String_ getName() { 
          return this.name;
        }

        public DocumentReferenceServiceParameterComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public DocumentReferenceServiceParameterComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
        }

        public String_ getValue() { 
          return this.value;
        }

        public DocumentReferenceServiceParameterComponent setValue(String_ value) { 
          this.value = value;
          return this;
        }

        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        public DocumentReferenceServiceParameterComponent setValueSimple(String value) { 
          if (value == null)
            this.value = null;
          else {
            if (this.value == null)
              this.value = new String_();
            this.value.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of a parameter.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("value", "string", "The value of the named parameter.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      public DocumentReferenceServiceParameterComponent copy(DocumentReference e) {
        DocumentReferenceServiceParameterComponent dst = new DocumentReferenceServiceParameterComponent();
        dst.name = name == null ? null : name.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    public static class DocumentReferenceContextComponent extends BackboneElement {
        /**
         * The type of clinical context, such as a kind of surgery, or a kind of speciality, or a clinical type.
         */
        protected List<CodeableConcept> code = new ArrayList<CodeableConcept>();

        /**
         * The time period over which the service that is described by the document was provided.
         */
        protected Period period;

        /**
         * The kind of facility where the patient was seen.
         */
        protected CodeableConcept facilityType;

      public DocumentReferenceContextComponent() {
        super();
      }

        public List<CodeableConcept> getCode() { 
          return this.code;
        }

    // syntactic sugar
        public CodeableConcept addCode() { 
          CodeableConcept t = new CodeableConcept();
          this.code.add(t);
          return t;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public DocumentReferenceContextComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        public CodeableConcept getFacilityType() { 
          return this.facilityType;
        }

        public DocumentReferenceContextComponent setFacilityType(CodeableConcept value) { 
          this.facilityType = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "The type of clinical context, such as a kind of surgery, or a kind of speciality, or a clinical type.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("period", "Period", "The time period over which the service that is described by the document was provided.", 0, java.lang.Integer.MAX_VALUE, period));
          childrenList.add(new Property("facilityType", "CodeableConcept", "The kind of facility where the patient was seen.", 0, java.lang.Integer.MAX_VALUE, facilityType));
        }

      public DocumentReferenceContextComponent copy(DocumentReference e) {
        DocumentReferenceContextComponent dst = new DocumentReferenceContextComponent();
        dst.code = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : code)
          dst.code.add(i.copy());
        dst.period = period == null ? null : period.copy();
        dst.facilityType = facilityType == null ? null : facilityType.copy();
        return dst;
      }

  }

    /**
     * Document identifier as assigned by the source of the document. This identifier is specific to this version of the document. This unique identifier may be used elsewhere to identify this version of the document.
     */
    protected Identifier masterIdentifier;

    /**
     * Other identifiers associated with the document, including version independent identifiers and source record identifiers.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Who or what the document is about. The document can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).
     */
    protected ResourceReference subject;

    /**
     * Specifies the particular kind of document (e.g. Patient Summary, Discharge Summary, Prescription, etc.).
     */
    protected CodeableConcept type;

    /**
     * More detailed information about the document type.
     */
    protected CodeableConcept subtype;

    /**
     * Identifies who is responsible for adding the information to the document.
     */
    protected List<ResourceReference> author = new ArrayList<ResourceReference>();

    /**
     * Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.
     */
    protected ResourceReference custodian;

    /**
     * A reference to a domain or server that manages policies under which the document is accessed and/or made available.
     */
    protected Uri policyManager;

    /**
     * Which person or organization authenticates that this document is valid.
     */
    protected ResourceReference authenticator;

    /**
     * When the document was created.
     */
    protected DateTime created;

    /**
     * When the document reference was created.
     */
    protected Instant indexed;

    /**
     * The status of this document reference.
     */
    protected Enumeration<DocumentReferenceStatus> status;

    /**
     * The status of the underlying document.
     */
    protected CodeableConcept docStatus;

    /**
     * If this document replaces another.
     */
    protected ResourceReference supercedes;

    /**
     * Human-readable description of the source document. This is sometimes known as the "title".
     */
    protected String_ description;

    /**
     * A code specifying the level of confidentiality of the XDS Document.
     */
    protected List<CodeableConcept> confidentiality = new ArrayList<CodeableConcept>();

    /**
     * The primary language in which the source document is written.
     */
    protected Code primaryLanguage;

    /**
     * The mime type of the source document.
     */
    protected Code mimeType;

    /**
     * The format of the document. This is used when the mimeType of the document does not provide enough differentiating information (typically, when the mime type of the document is text/xml).
     */
    protected CodeableConcept format;

    /**
     * The size of the source document this reference refers to in bytes.
     */
    protected Integer size;

    /**
     * A hash of the source document to ensure that changes have not occurred.
     */
    protected String_ hash;

    /**
     * A url at which the document can be accessed.
     */
    protected Uri location;

    /**
     * A description of a service call that can be used to retrieve the document.
     */
    protected DocumentReferenceServiceComponent service;

    /**
     * The clinical context in which the document was prepared.
     */
    protected DocumentReferenceContextComponent context;

    public DocumentReference() {
      super();
    }

    public DocumentReference(Identifier masterIdentifier, ResourceReference subject, CodeableConcept type, Instant indexed, Enumeration<DocumentReferenceStatus> status, Code mimeType) {
      super();
      this.masterIdentifier = masterIdentifier;
      this.subject = subject;
      this.type = type;
      this.indexed = indexed;
      this.status = status;
      this.mimeType = mimeType;
    }

    public Identifier getMasterIdentifier() { 
      return this.masterIdentifier;
    }

    public DocumentReference setMasterIdentifier(Identifier value) { 
      this.masterIdentifier = value;
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

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public DocumentReference setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    public CodeableConcept getType() { 
      return this.type;
    }

    public DocumentReference setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    public CodeableConcept getSubtype() { 
      return this.subtype;
    }

    public DocumentReference setSubtype(CodeableConcept value) { 
      this.subtype = value;
      return this;
    }

    public List<ResourceReference> getAuthor() { 
      return this.author;
    }

    // syntactic sugar
    public ResourceReference addAuthor() { 
      ResourceReference t = new ResourceReference();
      this.author.add(t);
      return t;
    }

    public ResourceReference getCustodian() { 
      return this.custodian;
    }

    public DocumentReference setCustodian(ResourceReference value) { 
      this.custodian = value;
      return this;
    }

    public Uri getPolicyManager() { 
      return this.policyManager;
    }

    public DocumentReference setPolicyManager(Uri value) { 
      this.policyManager = value;
      return this;
    }

    public String getPolicyManagerSimple() { 
      return this.policyManager == null ? null : this.policyManager.getValue();
    }

    public DocumentReference setPolicyManagerSimple(String value) { 
      if (value == null)
        this.policyManager = null;
      else {
        if (this.policyManager == null)
          this.policyManager = new Uri();
        this.policyManager.setValue(value);
      }
      return this;
    }

    public ResourceReference getAuthenticator() { 
      return this.authenticator;
    }

    public DocumentReference setAuthenticator(ResourceReference value) { 
      this.authenticator = value;
      return this;
    }

    public DateTime getCreated() { 
      return this.created;
    }

    public DocumentReference setCreated(DateTime value) { 
      this.created = value;
      return this;
    }

    public String getCreatedSimple() { 
      return this.created == null ? null : this.created.getValue();
    }

    public DocumentReference setCreatedSimple(String value) { 
      if (value == null)
        this.created = null;
      else {
        if (this.created == null)
          this.created = new DateTime();
        this.created.setValue(value);
      }
      return this;
    }

    public Instant getIndexed() { 
      return this.indexed;
    }

    public DocumentReference setIndexed(Instant value) { 
      this.indexed = value;
      return this;
    }

    public Calendar getIndexedSimple() { 
      return this.indexed == null ? null : this.indexed.getValue();
    }

    public DocumentReference setIndexedSimple(Calendar value) { 
        if (this.indexed == null)
          this.indexed = new Instant();
        this.indexed.setValue(value);
      return this;
    }

    public Enumeration<DocumentReferenceStatus> getStatus() { 
      return this.status;
    }

    public DocumentReference setStatus(Enumeration<DocumentReferenceStatus> value) { 
      this.status = value;
      return this;
    }

    public DocumentReferenceStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public DocumentReference setStatusSimple(DocumentReferenceStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<DocumentReferenceStatus>();
        this.status.setValue(value);
      return this;
    }

    public CodeableConcept getDocStatus() { 
      return this.docStatus;
    }

    public DocumentReference setDocStatus(CodeableConcept value) { 
      this.docStatus = value;
      return this;
    }

    public ResourceReference getSupercedes() { 
      return this.supercedes;
    }

    public DocumentReference setSupercedes(ResourceReference value) { 
      this.supercedes = value;
      return this;
    }

    public String_ getDescription() { 
      return this.description;
    }

    public DocumentReference setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    public DocumentReference setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
      return this;
    }

    public List<CodeableConcept> getConfidentiality() { 
      return this.confidentiality;
    }

    // syntactic sugar
    public CodeableConcept addConfidentiality() { 
      CodeableConcept t = new CodeableConcept();
      this.confidentiality.add(t);
      return t;
    }

    public Code getPrimaryLanguage() { 
      return this.primaryLanguage;
    }

    public DocumentReference setPrimaryLanguage(Code value) { 
      this.primaryLanguage = value;
      return this;
    }

    public String getPrimaryLanguageSimple() { 
      return this.primaryLanguage == null ? null : this.primaryLanguage.getValue();
    }

    public DocumentReference setPrimaryLanguageSimple(String value) { 
      if (value == null)
        this.primaryLanguage = null;
      else {
        if (this.primaryLanguage == null)
          this.primaryLanguage = new Code();
        this.primaryLanguage.setValue(value);
      }
      return this;
    }

    public Code getMimeType() { 
      return this.mimeType;
    }

    public DocumentReference setMimeType(Code value) { 
      this.mimeType = value;
      return this;
    }

    public String getMimeTypeSimple() { 
      return this.mimeType == null ? null : this.mimeType.getValue();
    }

    public DocumentReference setMimeTypeSimple(String value) { 
        if (this.mimeType == null)
          this.mimeType = new Code();
        this.mimeType.setValue(value);
      return this;
    }

    public CodeableConcept getFormat() { 
      return this.format;
    }

    public DocumentReference setFormat(CodeableConcept value) { 
      this.format = value;
      return this;
    }

    public Integer getSize() { 
      return this.size;
    }

    public DocumentReference setSize(Integer value) { 
      this.size = value;
      return this;
    }

    public int getSizeSimple() { 
      return this.size == null ? null : this.size.getValue();
    }

    public DocumentReference setSizeSimple(int value) { 
      if (value == -1)
        this.size = null;
      else {
        if (this.size == null)
          this.size = new Integer();
        this.size.setValue(value);
      }
      return this;
    }

    public String_ getHash() { 
      return this.hash;
    }

    public DocumentReference setHash(String_ value) { 
      this.hash = value;
      return this;
    }

    public String getHashSimple() { 
      return this.hash == null ? null : this.hash.getValue();
    }

    public DocumentReference setHashSimple(String value) { 
      if (value == null)
        this.hash = null;
      else {
        if (this.hash == null)
          this.hash = new String_();
        this.hash.setValue(value);
      }
      return this;
    }

    public Uri getLocation() { 
      return this.location;
    }

    public DocumentReference setLocation(Uri value) { 
      this.location = value;
      return this;
    }

    public String getLocationSimple() { 
      return this.location == null ? null : this.location.getValue();
    }

    public DocumentReference setLocationSimple(String value) { 
      if (value == null)
        this.location = null;
      else {
        if (this.location == null)
          this.location = new Uri();
        this.location.setValue(value);
      }
      return this;
    }

    public DocumentReferenceServiceComponent getService() { 
      return this.service;
    }

    public DocumentReference setService(DocumentReferenceServiceComponent value) { 
      this.service = value;
      return this;
    }

    public DocumentReferenceContextComponent getContext() { 
      return this.context;
    }

    public DocumentReference setContext(DocumentReferenceContextComponent value) { 
      this.context = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("masterIdentifier", "Identifier", "Document identifier as assigned by the source of the document. This identifier is specific to this version of the document. This unique identifier may be used elsewhere to identify this version of the document.", 0, java.lang.Integer.MAX_VALUE, masterIdentifier));
        childrenList.add(new Property("identifier", "Identifier", "Other identifiers associated with the document, including version independent identifiers and source record identifiers.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient|Practitioner|Group|Device)", "Who or what the document is about. The document can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("type", "CodeableConcept", "Specifies the particular kind of document (e.g. Patient Summary, Discharge Summary, Prescription, etc.).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("subtype", "CodeableConcept", "More detailed information about the document type.", 0, java.lang.Integer.MAX_VALUE, subtype));
        childrenList.add(new Property("author", "Resource(Practitioner|Device)", "Identifies who is responsible for adding the information to the document.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("custodian", "Resource(Organization)", "Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.", 0, java.lang.Integer.MAX_VALUE, custodian));
        childrenList.add(new Property("policyManager", "uri", "A reference to a domain or server that manages policies under which the document is accessed and/or made available.", 0, java.lang.Integer.MAX_VALUE, policyManager));
        childrenList.add(new Property("authenticator", "Resource(Practitioner|Organization)", "Which person or organization authenticates that this document is valid.", 0, java.lang.Integer.MAX_VALUE, authenticator));
        childrenList.add(new Property("created", "dateTime", "When the document was created.", 0, java.lang.Integer.MAX_VALUE, created));
        childrenList.add(new Property("indexed", "instant", "When the document reference was created.", 0, java.lang.Integer.MAX_VALUE, indexed));
        childrenList.add(new Property("status", "code", "The status of this document reference.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("docStatus", "CodeableConcept", "The status of the underlying document.", 0, java.lang.Integer.MAX_VALUE, docStatus));
        childrenList.add(new Property("supercedes", "Resource(DocumentReference)", "If this document replaces another.", 0, java.lang.Integer.MAX_VALUE, supercedes));
        childrenList.add(new Property("description", "string", "Human-readable description of the source document. This is sometimes known as the 'title'.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("confidentiality", "CodeableConcept", "A code specifying the level of confidentiality of the XDS Document.", 0, java.lang.Integer.MAX_VALUE, confidentiality));
        childrenList.add(new Property("primaryLanguage", "code", "The primary language in which the source document is written.", 0, java.lang.Integer.MAX_VALUE, primaryLanguage));
        childrenList.add(new Property("mimeType", "code", "The mime type of the source document.", 0, java.lang.Integer.MAX_VALUE, mimeType));
        childrenList.add(new Property("format", "CodeableConcept", "The format of the document. This is used when the mimeType of the document does not provide enough differentiating information (typically, when the mime type of the document is text/xml).", 0, java.lang.Integer.MAX_VALUE, format));
        childrenList.add(new Property("size", "integer", "The size of the source document this reference refers to in bytes.", 0, java.lang.Integer.MAX_VALUE, size));
        childrenList.add(new Property("hash", "string", "A hash of the source document to ensure that changes have not occurred.", 0, java.lang.Integer.MAX_VALUE, hash));
        childrenList.add(new Property("location", "uri", "A url at which the document can be accessed.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("service", "", "A description of a service call that can be used to retrieve the document.", 0, java.lang.Integer.MAX_VALUE, service));
        childrenList.add(new Property("context", "", "The clinical context in which the document was prepared.", 0, java.lang.Integer.MAX_VALUE, context));
      }

      public DocumentReference copy() {
        DocumentReference dst = new DocumentReference();
        dst.masterIdentifier = masterIdentifier == null ? null : masterIdentifier.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.type = type == null ? null : type.copy();
        dst.subtype = subtype == null ? null : subtype.copy();
        dst.author = new ArrayList<ResourceReference>();
        for (ResourceReference i : author)
          dst.author.add(i.copy());
        dst.custodian = custodian == null ? null : custodian.copy();
        dst.policyManager = policyManager == null ? null : policyManager.copy();
        dst.authenticator = authenticator == null ? null : authenticator.copy();
        dst.created = created == null ? null : created.copy();
        dst.indexed = indexed == null ? null : indexed.copy();
        dst.status = status == null ? null : status.copy();
        dst.docStatus = docStatus == null ? null : docStatus.copy();
        dst.supercedes = supercedes == null ? null : supercedes.copy();
        dst.description = description == null ? null : description.copy();
        dst.confidentiality = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : confidentiality)
          dst.confidentiality.add(i.copy());
        dst.primaryLanguage = primaryLanguage == null ? null : primaryLanguage.copy();
        dst.mimeType = mimeType == null ? null : mimeType.copy();
        dst.format = format == null ? null : format.copy();
        dst.size = size == null ? null : size.copy();
        dst.hash = hash == null ? null : hash.copy();
        dst.location = location == null ? null : location.copy();
        dst.service = service == null ? null : service.copy(dst);
        dst.context = context == null ? null : context.copy(dst);
        return dst;
      }

      protected DocumentReference typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DocumentReference;
   }


}

