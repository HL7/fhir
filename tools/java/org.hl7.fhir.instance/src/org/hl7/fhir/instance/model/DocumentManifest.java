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

// Generated on Thu, Oct 10, 2013 11:38+1100 for FHIR v0.12

import java.util.*;

/**
 * A manifest that defines a set of documents.
 */
public class DocumentManifest extends Resource {

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

  public class DocumentReferenceStatusEnumFactory implements EnumFactory {
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

    /**
     * A single identifier that identifies this manifest. Principally used to refer to the manifest in non-FHIR contexts.
     */
    protected Identifier identifier;

    /**
     * Who or what the set of documents is about. The documents can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure). If the documents cross more than one subject, then more than one subjecti allowed here (unusual use case).
     */
    protected List<ResourceReference> subject = new ArrayList<ResourceReference>();

    /**
     * A patient, practitioner, or organization that this set of documents is intended for.
     */
    protected List<ResourceReference> recipient = new ArrayList<ResourceReference>();

    /**
     * Specifies the kind of this set of documents (e.g. Patient Summary, Discharge Summary, Prescription, etc.). The type of a set of documents may be the same as one of the documents in it - especially if there's only one - but it may be wider.
     */
    protected CodeableConcept type;

    /**
     * Identifies who is responsible for adding the information to the document.
     */
    protected List<ResourceReference> author = new ArrayList<ResourceReference>();

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
     * If this document manifest replaces another.
     */
    protected ResourceReference supercedes;

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

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public List<ResourceReference> getSubject() { 
      return this.subject;
    }

    // syntactic sugar
    public ResourceReference addSubject() { 
      ResourceReference t = new ResourceReference();
      this.subject.add(t);
      return t;
    }

    public List<ResourceReference> getRecipient() { 
      return this.recipient;
    }

    // syntactic sugar
    public ResourceReference addRecipient() { 
      ResourceReference t = new ResourceReference();
      this.recipient.add(t);
      return t;
    }

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
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

    public DateTime getCreated() { 
      return this.created;
    }

    public void setCreated(DateTime value) { 
      this.created = value;
    }

    public String getCreatedSimple() { 
      return this.created == null ? null : this.created.getValue();
    }

    public void setCreatedSimple(String value) { 
      if (value == null)
        this.created = null;
      else {
        if (this.created == null)
          this.created = new DateTime();
        this.created.setValue(value);
      }
    }

    public Uri getSource() { 
      return this.source;
    }

    public void setSource(Uri value) { 
      this.source = value;
    }

    public String getSourceSimple() { 
      return this.source == null ? null : this.source.getValue();
    }

    public void setSourceSimple(String value) { 
      if (value == null)
        this.source = null;
      else {
        if (this.source == null)
          this.source = new Uri();
        this.source.setValue(value);
      }
    }

    public Enumeration<DocumentReferenceStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<DocumentReferenceStatus> value) { 
      this.status = value;
    }

    public DocumentReferenceStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(DocumentReferenceStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<DocumentReferenceStatus>();
        this.status.setValue(value);
    }

    public ResourceReference getSupercedes() { 
      return this.supercedes;
    }

    public void setSupercedes(ResourceReference value) { 
      this.supercedes = value;
    }

    public String_ getDescription() { 
      return this.description;
    }

    public void setDescription(String_ value) { 
      this.description = value;
    }

    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    public void setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
    }

    public CodeableConcept getConfidentiality() { 
      return this.confidentiality;
    }

    public void setConfidentiality(CodeableConcept value) { 
      this.confidentiality = value;
    }

    public List<ResourceReference> getContent() { 
      return this.content;
    }

    // syntactic sugar
    public ResourceReference addContent() { 
      ResourceReference t = new ResourceReference();
      this.content.add(t);
      return t;
    }

      public DocumentManifest copy() {
        DocumentManifest dst = new DocumentManifest();
        dst.identifier = identifier == null ? null : identifier.copy();
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

