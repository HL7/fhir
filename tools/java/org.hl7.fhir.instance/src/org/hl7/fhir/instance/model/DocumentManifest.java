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
 * A manifest that defines a set of documents.
 */
public class DocumentManifest extends Resource {

    public enum DocumentReferenceStatus {
        current, // This is the current reference for this document.
        superceded, // This reference has been superseded by another reference.
        enteredInError, // This reference was created in error.
        Null; // added to help the parsers
        public static DocumentReferenceStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("current".equals(codeString))
          return current;
        if ("superceded".equals(codeString))
          return superceded;
        if ("entered in error".equals(codeString))
          return enteredInError;
        throw new Exception("Unknown DocumentReferenceStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case current: return "current";
            case superceded: return "superceded";
            case enteredInError: return "entered in error";
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
        if ("entered in error".equals(codeString))
          return DocumentReferenceStatus.enteredInError;
        throw new Exception("Unknown DocumentReferenceStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DocumentReferenceStatus.current)
        return "current";
      if (code == DocumentReferenceStatus.superceded)
        return "superceded";
      if (code == DocumentReferenceStatus.enteredInError)
        return "entered in error";
      return "?";
      }
    }

    /**
     * A single identifier that uniquely identifies this manifest. Principally used to refer to the manifest in non-FHIR contexts.
     */
    protected Identifier masterIdentifier;

    /**
     * Other identifiers associated with the document, including version independent, source record and workflow related identifiers.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Who or what the set of documents is about. The documents can be about a person, (patient or healthcare practitioner), a device (i.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure). If the documents cross more than one subject, then more than one subject is allowed here (unusual use case).
     */
    protected List<ResourceReference> subject = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Who or what the set of documents is about. The documents can be about a person, (patient or healthcare practitioner), a device (i.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure). If the documents cross more than one subject, then more than one subject is allowed here (unusual use case).)
     */
    protected List<Resource> subjectTarget = new ArrayList<Resource>();


    /**
     * A patient, practitioner, or organization for which this set of documents is intended.
     */
    protected List<ResourceReference> recipient = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (A patient, practitioner, or organization for which this set of documents is intended.)
     */
    protected List<Resource> recipientTarget = new ArrayList<Resource>();


    /**
     * Specifies the kind of this set of documents (e.g. Patient Summary, Discharge Summary, Prescription, etc.). The type of a set of documents may be the same as one of the documents in it - especially if there is only one - but it may be wider.
     */
    protected CodeableConcept type;

    /**
     * Identifies who is responsible for adding the information to the document.
     */
    protected List<ResourceReference> author = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Identifies who is responsible for adding the information to the document.)
     */
    protected List<Resource> authorTarget = new ArrayList<Resource>();


    /**
     * When the document manifest was created for submission to the server (not necessarily the same thing as the actual resource last modified time, since it may be modified, replicated etc).
     */
    protected DateTime created;

    /**
     * Identifies the source system, application, or software that produced the document manifest.
     */
    protected Uri source;

    /**
     * The status of this document manifest.
     */
    protected Enumeration<DocumentReferenceStatus> status;

    /**
     * Whether this document manifest replaces another.
     */
    protected ResourceReference supercedes;

    /**
     * The actual object that is the target of the reference (Whether this document manifest replaces another.)
     */
    protected DocumentManifest supercedesTarget;

    /**
     * Human-readable description of the source document. This is sometimes known as the "title".
     */
    protected String_ description;

    /**
     * A code specifying the level of confidentiality of this set of Documents.
     */
    protected CodeableConcept confidentiality;

    /**
     * The list of resources that describe the parts of this document reference. Usually, these would be document references, but direct references to binary attachments and images are also allowed.
     */
    protected List<ResourceReference> content = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (The list of resources that describe the parts of this document reference. Usually, these would be document references, but direct references to binary attachments and images are also allowed.)
     */
    protected List<Resource> contentTarget = new ArrayList<Resource>();


    private static final long serialVersionUID = 1557669925L;

    public DocumentManifest() {
      super();
    }

    public DocumentManifest(Identifier masterIdentifier, Enumeration<DocumentReferenceStatus> status) {
      super();
      this.masterIdentifier = masterIdentifier;
      this.status = status;
    }

    /**
     * @return {@link #masterIdentifier} (A single identifier that uniquely identifies this manifest. Principally used to refer to the manifest in non-FHIR contexts.)
     */
    public Identifier getMasterIdentifier() { 
      return this.masterIdentifier;
    }

    /**
     * @param value {@link #masterIdentifier} (A single identifier that uniquely identifies this manifest. Principally used to refer to the manifest in non-FHIR contexts.)
     */
    public DocumentManifest setMasterIdentifier(Identifier value) { 
      this.masterIdentifier = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Other identifiers associated with the document, including version independent, source record and workflow related identifiers.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Other identifiers associated with the document, including version independent, source record and workflow related identifiers.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (Who or what the set of documents is about. The documents can be about a person, (patient or healthcare practitioner), a device (i.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure). If the documents cross more than one subject, then more than one subject is allowed here (unusual use case).)
     */
    public List<ResourceReference> getSubject() { 
      return this.subject;
    }

    // syntactic sugar
    /**
     * @return {@link #subject} (Who or what the set of documents is about. The documents can be about a person, (patient or healthcare practitioner), a device (i.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure). If the documents cross more than one subject, then more than one subject is allowed here (unusual use case).)
     */
    public ResourceReference addSubject() { 
      ResourceReference t = new ResourceReference();
      this.subject.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (The actual objects that are the target of the reference. Who or what the set of documents is about. The documents can be about a person, (patient or healthcare practitioner), a device (i.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure). If the documents cross more than one subject, then more than one subject is allowed here (unusual use case).)
     */
    public List<Resource> getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @return {@link #recipient} (A patient, practitioner, or organization for which this set of documents is intended.)
     */
    public List<ResourceReference> getRecipient() { 
      return this.recipient;
    }

    // syntactic sugar
    /**
     * @return {@link #recipient} (A patient, practitioner, or organization for which this set of documents is intended.)
     */
    public ResourceReference addRecipient() { 
      ResourceReference t = new ResourceReference();
      this.recipient.add(t);
      return t;
    }

    /**
     * @return {@link #recipient} (The actual objects that are the target of the reference. A patient, practitioner, or organization for which this set of documents is intended.)
     */
    public List<Resource> getRecipientTarget() { 
      return this.recipientTarget;
    }

    /**
     * @return {@link #type} (Specifies the kind of this set of documents (e.g. Patient Summary, Discharge Summary, Prescription, etc.). The type of a set of documents may be the same as one of the documents in it - especially if there is only one - but it may be wider.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Specifies the kind of this set of documents (e.g. Patient Summary, Discharge Summary, Prescription, etc.). The type of a set of documents may be the same as one of the documents in it - especially if there is only one - but it may be wider.)
     */
    public DocumentManifest setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #author} (Identifies who is responsible for adding the information to the document.)
     */
    public List<ResourceReference> getAuthor() { 
      return this.author;
    }

    // syntactic sugar
    /**
     * @return {@link #author} (Identifies who is responsible for adding the information to the document.)
     */
    public ResourceReference addAuthor() { 
      ResourceReference t = new ResourceReference();
      this.author.add(t);
      return t;
    }

    /**
     * @return {@link #author} (The actual objects that are the target of the reference. Identifies who is responsible for adding the information to the document.)
     */
    public List<Resource> getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @return {@link #created} (When the document manifest was created for submission to the server (not necessarily the same thing as the actual resource last modified time, since it may be modified, replicated etc).)
     */
    public DateTime getCreated() { 
      return this.created;
    }

    /**
     * @param value {@link #created} (When the document manifest was created for submission to the server (not necessarily the same thing as the actual resource last modified time, since it may be modified, replicated etc).)
     */
    public DocumentManifest setCreated(DateTime value) { 
      this.created = value;
      return this;
    }

    /**
     * @return When the document manifest was created for submission to the server (not necessarily the same thing as the actual resource last modified time, since it may be modified, replicated etc).
     */
    public DateAndTime getCreatedSimple() { 
      return this.created == null ? null : this.created.getValue();
    }

    /**
     * @param value When the document manifest was created for submission to the server (not necessarily the same thing as the actual resource last modified time, since it may be modified, replicated etc).
     */
    public DocumentManifest setCreatedSimple(DateAndTime value) { 
      if (value == null)
        this.created = null;
      else {
        if (this.created == null)
          this.created = new DateTime();
        this.created.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #source} (Identifies the source system, application, or software that produced the document manifest.)
     */
    public Uri getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (Identifies the source system, application, or software that produced the document manifest.)
     */
    public DocumentManifest setSource(Uri value) { 
      this.source = value;
      return this;
    }

    /**
     * @return Identifies the source system, application, or software that produced the document manifest.
     */
    public String getSourceSimple() { 
      return this.source == null ? null : this.source.getValue();
    }

    /**
     * @param value Identifies the source system, application, or software that produced the document manifest.
     */
    public DocumentManifest setSourceSimple(String value) { 
      if (value == null)
        this.source = null;
      else {
        if (this.source == null)
          this.source = new Uri();
        this.source.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The status of this document manifest.)
     */
    public Enumeration<DocumentReferenceStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of this document manifest.)
     */
    public DocumentManifest setStatus(Enumeration<DocumentReferenceStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of this document manifest.
     */
    public DocumentReferenceStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of this document manifest.
     */
    public DocumentManifest setStatusSimple(DocumentReferenceStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<DocumentReferenceStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #supercedes} (Whether this document manifest replaces another.)
     */
    public ResourceReference getSupercedes() { 
      return this.supercedes;
    }

    /**
     * @param value {@link #supercedes} (Whether this document manifest replaces another.)
     */
    public DocumentManifest setSupercedes(ResourceReference value) { 
      this.supercedes = value;
      return this;
    }

    /**
     * @return {@link #supercedes} (The actual object that is the target of the reference. Whether this document manifest replaces another.)
     */
    public DocumentManifest getSupercedesTarget() { 
      return this.supercedesTarget;
    }

    /**
     * @param value {@link #supercedes} (The actual object that is the target of the reference. Whether this document manifest replaces another.)
     */
    public DocumentManifest setSupercedesTarget(DocumentManifest value) { 
      this.supercedesTarget = value;
      return this;
    }

    /**
     * @return {@link #description} (Human-readable description of the source document. This is sometimes known as the "title".)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (Human-readable description of the source document. This is sometimes known as the "title".)
     */
    public DocumentManifest setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return Human-readable description of the source document. This is sometimes known as the "title".
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value Human-readable description of the source document. This is sometimes known as the "title".
     */
    public DocumentManifest setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #confidentiality} (A code specifying the level of confidentiality of this set of Documents.)
     */
    public CodeableConcept getConfidentiality() { 
      return this.confidentiality;
    }

    /**
     * @param value {@link #confidentiality} (A code specifying the level of confidentiality of this set of Documents.)
     */
    public DocumentManifest setConfidentiality(CodeableConcept value) { 
      this.confidentiality = value;
      return this;
    }

    /**
     * @return {@link #content} (The list of resources that describe the parts of this document reference. Usually, these would be document references, but direct references to binary attachments and images are also allowed.)
     */
    public List<ResourceReference> getContent() { 
      return this.content;
    }

    // syntactic sugar
    /**
     * @return {@link #content} (The list of resources that describe the parts of this document reference. Usually, these would be document references, but direct references to binary attachments and images are also allowed.)
     */
    public ResourceReference addContent() { 
      ResourceReference t = new ResourceReference();
      this.content.add(t);
      return t;
    }

    /**
     * @return {@link #content} (The actual objects that are the target of the reference. The list of resources that describe the parts of this document reference. Usually, these would be document references, but direct references to binary attachments and images are also allowed.)
     */
    public List<Resource> getContentTarget() { 
      return this.contentTarget;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("masterIdentifier", "Identifier", "A single identifier that uniquely identifies this manifest. Principally used to refer to the manifest in non-FHIR contexts.", 0, java.lang.Integer.MAX_VALUE, masterIdentifier));
        childrenList.add(new Property("identifier", "Identifier", "Other identifiers associated with the document, including version independent, source record and workflow related identifiers.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient|Practitioner|Group|Device)", "Who or what the set of documents is about. The documents can be about a person, (patient or healthcare practitioner), a device (i.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure). If the documents cross more than one subject, then more than one subject is allowed here (unusual use case).", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("recipient", "Resource(Patient|Practitioner|Organization)", "A patient, practitioner, or organization for which this set of documents is intended.", 0, java.lang.Integer.MAX_VALUE, recipient));
        childrenList.add(new Property("type", "CodeableConcept", "Specifies the kind of this set of documents (e.g. Patient Summary, Discharge Summary, Prescription, etc.). The type of a set of documents may be the same as one of the documents in it - especially if there is only one - but it may be wider.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("author", "Resource(Practitioner|Device|Patient|RelatedPerson)", "Identifies who is responsible for adding the information to the document.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("created", "dateTime", "When the document manifest was created for submission to the server (not necessarily the same thing as the actual resource last modified time, since it may be modified, replicated etc).", 0, java.lang.Integer.MAX_VALUE, created));
        childrenList.add(new Property("source", "uri", "Identifies the source system, application, or software that produced the document manifest.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("status", "code", "The status of this document manifest.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("supercedes", "Resource(DocumentManifest)", "Whether this document manifest replaces another.", 0, java.lang.Integer.MAX_VALUE, supercedes));
        childrenList.add(new Property("description", "string", "Human-readable description of the source document. This is sometimes known as the 'title'.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("confidentiality", "CodeableConcept", "A code specifying the level of confidentiality of this set of Documents.", 0, java.lang.Integer.MAX_VALUE, confidentiality));
        childrenList.add(new Property("content", "Resource(DocumentReference|Binary|Media)", "The list of resources that describe the parts of this document reference. Usually, these would be document references, but direct references to binary attachments and images are also allowed.", 0, java.lang.Integer.MAX_VALUE, content));
      }

      public DocumentManifest copy() {
        DocumentManifest dst = new DocumentManifest();
        dst.masterIdentifier = masterIdentifier == null ? null : masterIdentifier.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.subject = new ArrayList<ResourceReference>();
        for (ResourceReference i : subject)
          dst.subject.add(i.copy());
        dst.recipient = new ArrayList<ResourceReference>();
        for (ResourceReference i : recipient)
          dst.recipient.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.author = new ArrayList<ResourceReference>();
        for (ResourceReference i : author)
          dst.author.add(i.copy());
        dst.created = created == null ? null : created.copy();
        dst.source = source == null ? null : source.copy();
        dst.status = status == null ? null : status.copy();
        dst.supercedes = supercedes == null ? null : supercedes.copy();
        dst.description = description == null ? null : description.copy();
        dst.confidentiality = confidentiality == null ? null : confidentiality.copy();
        dst.content = new ArrayList<ResourceReference>();
        for (ResourceReference i : content)
          dst.content.add(i.copy());
        return dst;
      }

      protected DocumentManifest typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DocumentManifest;
   }


}

