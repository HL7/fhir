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

// Generated on Thu, Nov 7, 2013 14:52+1100 for FHIR v0.12

import java.util.*;

/**
 * A documentation of healthcare-related information that is assembled together into a single statement of meaning that establishes its own context. A document is composed of a set of resources that include both human and computer readable portions. A human may attest to the accuracy of the human-readable portion and may authenticate and/or sign the entire whole. A document may be kept as a set of logically linked resources, or they may be bundled together in an atom feed.
 */
public class Document extends Resource {

    public enum DocumentStatus {
        preliminary, // This is an preliminary document (also known as initial or interim). The content may be incomplete or unverified.
        final_, // The document is complete and verified by an appropriate person, and no further work is planned.
        amended, // The document has been modified subsequent to being released as "final", and is complete and verified by an authorised person.
        appended, // The document has been modified subsequent to being released as "final", and is complete and verified by an authorised person. The modifications added new information to the document, but did not revise existing content.
        retracted, // The document has been withdrawn on the basis that it was original issued in error.
        Null; // added to help the parsers
        public static DocumentStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("preliminary".equals(codeString))
          return preliminary;
        if ("final".equals(codeString))
          return final_;
        if ("amended".equals(codeString))
          return amended;
        if ("appended".equals(codeString))
          return appended;
        if ("retracted".equals(codeString))
          return retracted;
        throw new Exception("Unknown DocumentStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case preliminary: return "preliminary";
            case final_: return "final";
            case amended: return "amended";
            case appended: return "appended";
            case retracted: return "retracted";
            default: return "?";
          }
        }
    }

  public static class DocumentStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("preliminary".equals(codeString))
          return DocumentStatus.preliminary;
        if ("final".equals(codeString))
          return DocumentStatus.final_;
        if ("amended".equals(codeString))
          return DocumentStatus.amended;
        if ("appended".equals(codeString))
          return DocumentStatus.appended;
        if ("retracted".equals(codeString))
          return DocumentStatus.retracted;
        throw new Exception("Unknown DocumentStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DocumentStatus.preliminary)
        return "preliminary";
      if (code == DocumentStatus.final_)
        return "final";
      if (code == DocumentStatus.amended)
        return "amended";
      if (code == DocumentStatus.appended)
        return "appended";
      if (code == DocumentStatus.retracted)
        return "retracted";
      return "?";
      }
    }

    public enum DocumentAttestationMode {
        personal, // The person authenticated the document in their personal capacity.
        professional, // The person authenticated the document in their professional capacity.
        legal, // The person authenticated the document and accepted legal responsibility for its content.
        official, // The organization authenticated the document as consistent with their policies and procedures.
        Null; // added to help the parsers
        public static DocumentAttestationMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("personal".equals(codeString))
          return personal;
        if ("professional".equals(codeString))
          return professional;
        if ("legal".equals(codeString))
          return legal;
        if ("official".equals(codeString))
          return official;
        throw new Exception("Unknown DocumentAttestationMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case personal: return "personal";
            case professional: return "professional";
            case legal: return "legal";
            case official: return "official";
            default: return "?";
          }
        }
    }

  public static class DocumentAttestationModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("personal".equals(codeString))
          return DocumentAttestationMode.personal;
        if ("professional".equals(codeString))
          return DocumentAttestationMode.professional;
        if ("legal".equals(codeString))
          return DocumentAttestationMode.legal;
        if ("official".equals(codeString))
          return DocumentAttestationMode.official;
        throw new Exception("Unknown DocumentAttestationMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DocumentAttestationMode.personal)
        return "personal";
      if (code == DocumentAttestationMode.professional)
        return "professional";
      if (code == DocumentAttestationMode.legal)
        return "legal";
      if (code == DocumentAttestationMode.official)
        return "official";
      return "?";
      }
    }

    public static class DocumentAttesterComponent extends BackboneElement {
        /**
         * The type of attestation the authenticator offers.
         */
        protected Enumeration<DocumentAttestationMode> mode;

        /**
         * When document was attested by the party.
         */
        protected DateTime time;

        /**
         * Who attested the document in the specified way.
         */
        protected ResourceReference party;

      public DocumentAttesterComponent() {
        super();
      }

      public DocumentAttesterComponent(Enumeration<DocumentAttestationMode> mode) {
        super();
        this.mode = mode;
      }

        /**
         * @return {@link #mode} (The type of attestation the authenticator offers.)
         */
        public Enumeration<DocumentAttestationMode> getMode() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (The type of attestation the authenticator offers.)
         */
        public DocumentAttesterComponent setMode(Enumeration<DocumentAttestationMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return The type of attestation the authenticator offers.
         */
        public DocumentAttestationMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value The type of attestation the authenticator offers.
         */
        public DocumentAttesterComponent setModeSimple(DocumentAttestationMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<DocumentAttestationMode>();
            this.mode.setValue(value);
          return this;
        }

        /**
         * @return {@link #time} (When document was attested by the party.)
         */
        public DateTime getTime() { 
          return this.time;
        }

        /**
         * @param value {@link #time} (When document was attested by the party.)
         */
        public DocumentAttesterComponent setTime(DateTime value) { 
          this.time = value;
          return this;
        }

        /**
         * @return When document was attested by the party.
         */
        public String getTimeSimple() { 
          return this.time == null ? null : this.time.getValue();
        }

        /**
         * @param value When document was attested by the party.
         */
        public DocumentAttesterComponent setTimeSimple(String value) { 
          if (value == null)
            this.time = null;
          else {
            if (this.time == null)
              this.time = new DateTime();
            this.time.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #party} (Who attested the document in the specified way.)
         */
        public ResourceReference getParty() { 
          return this.party;
        }

        /**
         * @param value {@link #party} (Who attested the document in the specified way.)
         */
        public DocumentAttesterComponent setParty(ResourceReference value) { 
          this.party = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("mode", "code", "The type of attestation the authenticator offers.", 0, java.lang.Integer.MAX_VALUE, mode));
          childrenList.add(new Property("time", "dateTime", "When document was attested by the party.", 0, java.lang.Integer.MAX_VALUE, time));
          childrenList.add(new Property("party", "Resource(Patient|Practitioner|Organization)", "Who attested the document in the specified way.", 0, java.lang.Integer.MAX_VALUE, party));
        }

      public DocumentAttesterComponent copy(Document e) {
        DocumentAttesterComponent dst = new DocumentAttesterComponent();
        dst.mode = mode == null ? null : mode.copy();
        dst.time = time == null ? null : time.copy();
        dst.party = party == null ? null : party.copy();
        return dst;
      }

  }

    public static class DocumentEventComponent extends BackboneElement {
        /**
         * This list of codes represents the main clinical acts, such as a colonoscopy or an appendectomy, being documented. In some cases, the event is inherent in the typeCode, such as a "History and Physical Report" in which the procedure being documented is necessarily a "History and Physical" act.
         */
        protected List<CodeableConcept> code = new ArrayList<CodeableConcept>();

        /**
         * The period of time covered by the document. There is no assertion that the document is a complete representation for this period, only that it documents events during this time.
         */
        protected Period period;

        /**
         * Full details for the event(s) the document concents.
         */
        protected List<ResourceReference> detail = new ArrayList<ResourceReference>();

      public DocumentEventComponent() {
        super();
      }

        /**
         * @return {@link #code} (This list of codes represents the main clinical acts, such as a colonoscopy or an appendectomy, being documented. In some cases, the event is inherent in the typeCode, such as a "History and Physical Report" in which the procedure being documented is necessarily a "History and Physical" act.)
         */
        public List<CodeableConcept> getCode() { 
          return this.code;
        }

    // syntactic sugar
        /**
         * @return {@link #code} (This list of codes represents the main clinical acts, such as a colonoscopy or an appendectomy, being documented. In some cases, the event is inherent in the typeCode, such as a "History and Physical Report" in which the procedure being documented is necessarily a "History and Physical" act.)
         */
        public CodeableConcept addCode() { 
          CodeableConcept t = new CodeableConcept();
          this.code.add(t);
          return t;
        }

        /**
         * @return {@link #period} (The period of time covered by the document. There is no assertion that the document is a complete representation for this period, only that it documents events during this time.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (The period of time covered by the document. There is no assertion that the document is a complete representation for this period, only that it documents events during this time.)
         */
        public DocumentEventComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        /**
         * @return {@link #detail} (Full details for the event(s) the document concents.)
         */
        public List<ResourceReference> getDetail() { 
          return this.detail;
        }

    // syntactic sugar
        /**
         * @return {@link #detail} (Full details for the event(s) the document concents.)
         */
        public ResourceReference addDetail() { 
          ResourceReference t = new ResourceReference();
          this.detail.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "This list of codes represents the main clinical acts, such as a colonoscopy or an appendectomy, being documented. In some cases, the event is inherent in the typeCode, such as a 'History and Physical Report' in which the procedure being documented is necessarily a 'History and Physical' act.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("period", "Period", "The period of time covered by the document. There is no assertion that the document is a complete representation for this period, only that it documents events during this time.", 0, java.lang.Integer.MAX_VALUE, period));
          childrenList.add(new Property("detail", "Resource(Any)", "Full details for the event(s) the document concents.", 0, java.lang.Integer.MAX_VALUE, detail));
        }

      public DocumentEventComponent copy(Document e) {
        DocumentEventComponent dst = new DocumentEventComponent();
        dst.code = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : code)
          dst.code.add(i.copy());
        dst.period = period == null ? null : period.copy();
        dst.detail = new ArrayList<ResourceReference>();
        for (ResourceReference i : detail)
          dst.detail.add(i.copy());
        return dst;
      }

  }

    public static class SectionComponent extends BackboneElement {
        /**
         * A code identifying the kind of content contained within the section.
         */
        protected CodeableConcept code;

        /**
         * Identifies the primary subject of the section.
         */
        protected ResourceReference subject;

        /**
         * Identifies the discrete data that provides the content for the section.
         */
        protected ResourceReference content;

        /**
         * Identifies a subtopic within the section as part of the document's table of contents.
         */
        protected List<SectionComponent> section = new ArrayList<SectionComponent>();

      public SectionComponent() {
        super();
      }

        /**
         * @return {@link #code} (A code identifying the kind of content contained within the section.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A code identifying the kind of content contained within the section.)
         */
        public SectionComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #subject} (Identifies the primary subject of the section.)
         */
        public ResourceReference getSubject() { 
          return this.subject;
        }

        /**
         * @param value {@link #subject} (Identifies the primary subject of the section.)
         */
        public SectionComponent setSubject(ResourceReference value) { 
          this.subject = value;
          return this;
        }

        /**
         * @return {@link #content} (Identifies the discrete data that provides the content for the section.)
         */
        public ResourceReference getContent() { 
          return this.content;
        }

        /**
         * @param value {@link #content} (Identifies the discrete data that provides the content for the section.)
         */
        public SectionComponent setContent(ResourceReference value) { 
          this.content = value;
          return this;
        }

        /**
         * @return {@link #section} (Identifies a subtopic within the section as part of the document's table of contents.)
         */
        public List<SectionComponent> getSection() { 
          return this.section;
        }

    // syntactic sugar
        /**
         * @return {@link #section} (Identifies a subtopic within the section as part of the document's table of contents.)
         */
        public SectionComponent addSection() { 
          SectionComponent t = new SectionComponent();
          this.section.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "A code identifying the kind of content contained within the section.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("subject", "Resource(Patient|Group|Device)", "Identifies the primary subject of the section.", 0, java.lang.Integer.MAX_VALUE, subject));
          childrenList.add(new Property("content", "Resource(Any)", "Identifies the discrete data that provides the content for the section.", 0, java.lang.Integer.MAX_VALUE, content));
          childrenList.add(new Property("section", "@Document.section", "Identifies a subtopic within the section as part of the document's table of contents.", 0, java.lang.Integer.MAX_VALUE, section));
        }

      public SectionComponent copy(Document e) {
        SectionComponent dst = new SectionComponent();
        dst.code = code == null ? null : code.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.content = content == null ? null : content.copy();
        dst.section = new ArrayList<SectionComponent>();
        for (SectionComponent i : section)
          dst.section.add(i.copy(e));
        return dst;
      }

  }

    /**
     * Logical Identifier for the document, assigned when created. This identifier stays constant when subsequent versions of the document are created.
     */
    protected Identifier identifier;

    /**
     * Version specific identifier for the document, assigned when created. This identifier changes when subsequent versions of the document are created.
     */
    protected Identifier versionIdentifier;

    /**
     * The document creation time, when the document first came into being. Where the document is a transform from an original document in some other format, the ClinicalDocument.effectiveTime is the time the original document is created.
     */
    protected Instant created;

    /**
     * Specifies the particular kind of document (e.g. History and Physical, Discharge Summary, Progress Note).
     */
    protected CodeableConcept type;

    /**
     * Additional detailed type for the document.
     */
    protected CodeableConcept subtype;

    /**
     * Official human-readable label for the document.
     */
    protected String_ title;

    /**
     * The workflow/clinical status of this document. The status is a rough guide to the clinical standing of the document.
     */
    protected Enumeration<DocumentStatus> status;

    /**
     * The code specifying the level of confidentiality of the XDS Document. These codes are specific to an XDS Affinity Domain.
     */
    protected Coding confidentiality;

    /**
     * Who or what the document is about. The document can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).
     */
    protected ResourceReference subject;

    /**
     * Identifies who is responsible for the information in the document.  (Not necessarily who typed it in.).
     */
    protected List<ResourceReference> author = new ArrayList<ResourceReference>();

    /**
     * A participant who has attested to the accuracy of the document.
     */
    protected List<DocumentAttesterComponent> attester = new ArrayList<DocumentAttesterComponent>();

    /**
     * Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.
     */
    protected ResourceReference custodian;

    /**
     * The main event/act/item, such as a colonoscopy or an appendectomy, being documented.
     */
    protected DocumentEventComponent event;

    /**
     * Describes the clinical encounter or type of care this document is associated with.
     */
    protected ResourceReference encounter;

    /**
     * Identifies the document this document supersedes, if any.
     */
    protected Id replaces;

    /**
     * Additional provenance about the document and the resources that are the sections.
     */
    protected List<ResourceReference> provenance = new ArrayList<ResourceReference>();

    /**
     * A fixed CSS stylesheet to use when rendering the documents.
     */
    protected Attachment stylesheet;

    /**
     * An alternative representation of the document that can be used in place of the html based rendering.
     */
    protected Attachment representation;

    /**
     * Identifies a main topic within the document's table of contents.
     */
    protected List<SectionComponent> section = new ArrayList<SectionComponent>();

    public Document() {
      super();
    }

    public Document(Instant created, CodeableConcept type, Enumeration<DocumentStatus> status, Coding confidentiality, ResourceReference subject) {
      super();
      this.created = created;
      this.type = type;
      this.status = status;
      this.confidentiality = confidentiality;
      this.subject = subject;
    }

    /**
     * @return {@link #identifier} (Logical Identifier for the document, assigned when created. This identifier stays constant when subsequent versions of the document are created.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Logical Identifier for the document, assigned when created. This identifier stays constant when subsequent versions of the document are created.)
     */
    public Document setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #versionIdentifier} (Version specific identifier for the document, assigned when created. This identifier changes when subsequent versions of the document are created.)
     */
    public Identifier getVersionIdentifier() { 
      return this.versionIdentifier;
    }

    /**
     * @param value {@link #versionIdentifier} (Version specific identifier for the document, assigned when created. This identifier changes when subsequent versions of the document are created.)
     */
    public Document setVersionIdentifier(Identifier value) { 
      this.versionIdentifier = value;
      return this;
    }

    /**
     * @return {@link #created} (The document creation time, when the document first came into being. Where the document is a transform from an original document in some other format, the ClinicalDocument.effectiveTime is the time the original document is created.)
     */
    public Instant getCreated() { 
      return this.created;
    }

    /**
     * @param value {@link #created} (The document creation time, when the document first came into being. Where the document is a transform from an original document in some other format, the ClinicalDocument.effectiveTime is the time the original document is created.)
     */
    public Document setCreated(Instant value) { 
      this.created = value;
      return this;
    }

    /**
     * @return The document creation time, when the document first came into being. Where the document is a transform from an original document in some other format, the ClinicalDocument.effectiveTime is the time the original document is created.
     */
    public Calendar getCreatedSimple() { 
      return this.created == null ? null : this.created.getValue();
    }

    /**
     * @param value The document creation time, when the document first came into being. Where the document is a transform from an original document in some other format, the ClinicalDocument.effectiveTime is the time the original document is created.
     */
    public Document setCreatedSimple(Calendar value) { 
        if (this.created == null)
          this.created = new Instant();
        this.created.setValue(value);
      return this;
    }

    /**
     * @return {@link #type} (Specifies the particular kind of document (e.g. History and Physical, Discharge Summary, Progress Note).)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Specifies the particular kind of document (e.g. History and Physical, Discharge Summary, Progress Note).)
     */
    public Document setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #subtype} (Additional detailed type for the document.)
     */
    public CodeableConcept getSubtype() { 
      return this.subtype;
    }

    /**
     * @param value {@link #subtype} (Additional detailed type for the document.)
     */
    public Document setSubtype(CodeableConcept value) { 
      this.subtype = value;
      return this;
    }

    /**
     * @return {@link #title} (Official human-readable label for the document.)
     */
    public String_ getTitle() { 
      return this.title;
    }

    /**
     * @param value {@link #title} (Official human-readable label for the document.)
     */
    public Document setTitle(String_ value) { 
      this.title = value;
      return this;
    }

    /**
     * @return Official human-readable label for the document.
     */
    public String getTitleSimple() { 
      return this.title == null ? null : this.title.getValue();
    }

    /**
     * @param value Official human-readable label for the document.
     */
    public Document setTitleSimple(String value) { 
      if (value == null)
        this.title = null;
      else {
        if (this.title == null)
          this.title = new String_();
        this.title.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The workflow/clinical status of this document. The status is a rough guide to the clinical standing of the document.)
     */
    public Enumeration<DocumentStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The workflow/clinical status of this document. The status is a rough guide to the clinical standing of the document.)
     */
    public Document setStatus(Enumeration<DocumentStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The workflow/clinical status of this document. The status is a rough guide to the clinical standing of the document.
     */
    public DocumentStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The workflow/clinical status of this document. The status is a rough guide to the clinical standing of the document.
     */
    public Document setStatusSimple(DocumentStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<DocumentStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #confidentiality} (The code specifying the level of confidentiality of the XDS Document. These codes are specific to an XDS Affinity Domain.)
     */
    public Coding getConfidentiality() { 
      return this.confidentiality;
    }

    /**
     * @param value {@link #confidentiality} (The code specifying the level of confidentiality of the XDS Document. These codes are specific to an XDS Affinity Domain.)
     */
    public Document setConfidentiality(Coding value) { 
      this.confidentiality = value;
      return this;
    }

    /**
     * @return {@link #subject} (Who or what the document is about. The document can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who or what the document is about. The document can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).)
     */
    public Document setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #author} (Identifies who is responsible for the information in the document.  (Not necessarily who typed it in.).)
     */
    public List<ResourceReference> getAuthor() { 
      return this.author;
    }

    // syntactic sugar
    /**
     * @return {@link #author} (Identifies who is responsible for the information in the document.  (Not necessarily who typed it in.).)
     */
    public ResourceReference addAuthor() { 
      ResourceReference t = new ResourceReference();
      this.author.add(t);
      return t;
    }

    /**
     * @return {@link #attester} (A participant who has attested to the accuracy of the document.)
     */
    public List<DocumentAttesterComponent> getAttester() { 
      return this.attester;
    }

    // syntactic sugar
    /**
     * @return {@link #attester} (A participant who has attested to the accuracy of the document.)
     */
    public DocumentAttesterComponent addAttester() { 
      DocumentAttesterComponent t = new DocumentAttesterComponent();
      this.attester.add(t);
      return t;
    }

    /**
     * @return {@link #custodian} (Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.)
     */
    public ResourceReference getCustodian() { 
      return this.custodian;
    }

    /**
     * @param value {@link #custodian} (Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.)
     */
    public Document setCustodian(ResourceReference value) { 
      this.custodian = value;
      return this;
    }

    /**
     * @return {@link #event} (The main event/act/item, such as a colonoscopy or an appendectomy, being documented.)
     */
    public DocumentEventComponent getEvent() { 
      return this.event;
    }

    /**
     * @param value {@link #event} (The main event/act/item, such as a colonoscopy or an appendectomy, being documented.)
     */
    public Document setEvent(DocumentEventComponent value) { 
      this.event = value;
      return this;
    }

    /**
     * @return {@link #encounter} (Describes the clinical encounter or type of care this document is associated with.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (Describes the clinical encounter or type of care this document is associated with.)
     */
    public Document setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #replaces} (Identifies the document this document supersedes, if any.)
     */
    public Id getReplaces() { 
      return this.replaces;
    }

    /**
     * @param value {@link #replaces} (Identifies the document this document supersedes, if any.)
     */
    public Document setReplaces(Id value) { 
      this.replaces = value;
      return this;
    }

    /**
     * @return Identifies the document this document supersedes, if any.
     */
    public String getReplacesSimple() { 
      return this.replaces == null ? null : this.replaces.getValue();
    }

    /**
     * @param value Identifies the document this document supersedes, if any.
     */
    public Document setReplacesSimple(String value) { 
      if (value == null)
        this.replaces = null;
      else {
        if (this.replaces == null)
          this.replaces = new Id();
        this.replaces.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #provenance} (Additional provenance about the document and the resources that are the sections.)
     */
    public List<ResourceReference> getProvenance() { 
      return this.provenance;
    }

    // syntactic sugar
    /**
     * @return {@link #provenance} (Additional provenance about the document and the resources that are the sections.)
     */
    public ResourceReference addProvenance() { 
      ResourceReference t = new ResourceReference();
      this.provenance.add(t);
      return t;
    }

    /**
     * @return {@link #stylesheet} (A fixed CSS stylesheet to use when rendering the documents.)
     */
    public Attachment getStylesheet() { 
      return this.stylesheet;
    }

    /**
     * @param value {@link #stylesheet} (A fixed CSS stylesheet to use when rendering the documents.)
     */
    public Document setStylesheet(Attachment value) { 
      this.stylesheet = value;
      return this;
    }

    /**
     * @return {@link #representation} (An alternative representation of the document that can be used in place of the html based rendering.)
     */
    public Attachment getRepresentation() { 
      return this.representation;
    }

    /**
     * @param value {@link #representation} (An alternative representation of the document that can be used in place of the html based rendering.)
     */
    public Document setRepresentation(Attachment value) { 
      this.representation = value;
      return this;
    }

    /**
     * @return {@link #section} (Identifies a main topic within the document's table of contents.)
     */
    public List<SectionComponent> getSection() { 
      return this.section;
    }

    // syntactic sugar
    /**
     * @return {@link #section} (Identifies a main topic within the document's table of contents.)
     */
    public SectionComponent addSection() { 
      SectionComponent t = new SectionComponent();
      this.section.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Logical Identifier for the document, assigned when created. This identifier stays constant when subsequent versions of the document are created.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("versionIdentifier", "Identifier", "Version specific identifier for the document, assigned when created. This identifier changes when subsequent versions of the document are created.", 0, java.lang.Integer.MAX_VALUE, versionIdentifier));
        childrenList.add(new Property("created", "instant", "The document creation time, when the document first came into being. Where the document is a transform from an original document in some other format, the ClinicalDocument.effectiveTime is the time the original document is created.", 0, java.lang.Integer.MAX_VALUE, created));
        childrenList.add(new Property("type", "CodeableConcept", "Specifies the particular kind of document (e.g. History and Physical, Discharge Summary, Progress Note).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("subtype", "CodeableConcept", "Additional detailed type for the document.", 0, java.lang.Integer.MAX_VALUE, subtype));
        childrenList.add(new Property("title", "string", "Official human-readable label for the document.", 0, java.lang.Integer.MAX_VALUE, title));
        childrenList.add(new Property("status", "code", "The workflow/clinical status of this document. The status is a rough guide to the clinical standing of the document.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("confidentiality", "Coding", "The code specifying the level of confidentiality of the XDS Document. These codes are specific to an XDS Affinity Domain.", 0, java.lang.Integer.MAX_VALUE, confidentiality));
        childrenList.add(new Property("subject", "Resource(Patient|Practitioner|Group|Device)", "Who or what the document is about. The document can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("author", "Resource(Practitioner|Device)", "Identifies who is responsible for the information in the document.  (Not necessarily who typed it in.).", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("attester", "", "A participant who has attested to the accuracy of the document.", 0, java.lang.Integer.MAX_VALUE, attester));
        childrenList.add(new Property("custodian", "Resource(Organization)", "Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.", 0, java.lang.Integer.MAX_VALUE, custodian));
        childrenList.add(new Property("event", "", "The main event/act/item, such as a colonoscopy or an appendectomy, being documented.", 0, java.lang.Integer.MAX_VALUE, event));
        childrenList.add(new Property("encounter", "Resource(Encounter|InterestOfCare)", "Describes the clinical encounter or type of care this document is associated with.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("replaces", "id", "Identifies the document this document supersedes, if any.", 0, java.lang.Integer.MAX_VALUE, replaces));
        childrenList.add(new Property("provenance", "Resource(Provenance)", "Additional provenance about the document and the resources that are the sections.", 0, java.lang.Integer.MAX_VALUE, provenance));
        childrenList.add(new Property("stylesheet", "Attachment", "A fixed CSS stylesheet to use when rendering the documents.", 0, java.lang.Integer.MAX_VALUE, stylesheet));
        childrenList.add(new Property("representation", "Attachment", "An alternative representation of the document that can be used in place of the html based rendering.", 0, java.lang.Integer.MAX_VALUE, representation));
        childrenList.add(new Property("section", "", "Identifies a main topic within the document's table of contents.", 0, java.lang.Integer.MAX_VALUE, section));
      }

      public Document copy() {
        Document dst = new Document();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.versionIdentifier = versionIdentifier == null ? null : versionIdentifier.copy();
        dst.created = created == null ? null : created.copy();
        dst.type = type == null ? null : type.copy();
        dst.subtype = subtype == null ? null : subtype.copy();
        dst.title = title == null ? null : title.copy();
        dst.status = status == null ? null : status.copy();
        dst.confidentiality = confidentiality == null ? null : confidentiality.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.author = new ArrayList<ResourceReference>();
        for (ResourceReference i : author)
          dst.author.add(i.copy());
        dst.attester = new ArrayList<DocumentAttesterComponent>();
        for (DocumentAttesterComponent i : attester)
          dst.attester.add(i.copy(dst));
        dst.custodian = custodian == null ? null : custodian.copy();
        dst.event = event == null ? null : event.copy(dst);
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.replaces = replaces == null ? null : replaces.copy();
        dst.provenance = new ArrayList<ResourceReference>();
        for (ResourceReference i : provenance)
          dst.provenance.add(i.copy());
        dst.stylesheet = stylesheet == null ? null : stylesheet.copy();
        dst.representation = representation == null ? null : representation.copy();
        dst.section = new ArrayList<SectionComponent>();
        for (SectionComponent i : section)
          dst.section.add(i.copy(dst));
        return dst;
      }

      protected Document typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Document;
   }


}

