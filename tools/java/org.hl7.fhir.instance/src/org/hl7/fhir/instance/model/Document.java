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

// Generated on Wed, May 22, 2013 17:49+1000 for FHIR v0.09

import java.util.*;

/**
 * A documentation of healthcare-related information that is assembled together into a single statement of meaning that establishes its own context. A document is composed of a set of resources that include both human and computer readable portions. A human may attest to the accuracy of the human readable portion and may authenticate and/or sign the entire whole. A document may be kept as a set of logically linked resources, or they may be bundled together in an atom feed
 */
public class Document extends Resource {

    public enum DocumentAttestationMode {
        personal, // The person authenticated the document in their personal capacity
        professional, // The person authenticated the document in their professional capacity
        legal, // The person authenticated the document and accepted legal responsibility for its content
        official, // The organization authenticated the document as consistent with their policies and procedures
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

  public class DocumentAttestationModeEnumFactory implements EnumFactory {
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

    public class DocumentAttesterComponent extends Element {
        /**
         * The type of attestation the authenticator offers
         */
        private Enumeration<DocumentAttestationMode> mode;

        /**
         * When document was attested by the party
         */
        private DateTime time;

        /**
         * Who attested the document in the specified way
         */
        private ResourceReference party;

        public Enumeration<DocumentAttestationMode> getMode() { 
          return this.mode;
        }

        public void setMode(Enumeration<DocumentAttestationMode> value) { 
          this.mode = value;
        }

        public DocumentAttestationMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        public void setModeSimple(DocumentAttestationMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<DocumentAttestationMode>();
            this.mode.setValue(value);
        }

        public DateTime getTime() { 
          return this.time;
        }

        public void setTime(DateTime value) { 
          this.time = value;
        }

        public String getTimeSimple() { 
          return this.time == null ? null : this.time.getValue();
        }

        public void setTimeSimple(String value) { 
          if (value == null)
            this.time = null;
          else {
            if (this.time == null)
              this.time = new DateTime();
            this.time.setValue(value);
          }
        }

        public ResourceReference getParty() { 
          return this.party;
        }

        public void setParty(ResourceReference value) { 
          this.party = value;
        }

  }

    public class DocumentEventComponent extends Element {
        /**
         * This list of codes represents the main clinical acts, such as a colonoscopy or an appendectomy, being documented. In some cases, the event is inherent in the typeCode, such as a "History and Physical Report" in which the procedure being documented is necessarily a "History and Physical" act.
         */
        private List<CodeableConcept> code = new ArrayList<CodeableConcept>();

        /**
         * The period of time covered by the document. There is no assertion that the document is a complete representation for this period, only that it documents events during this time
         */
        private Period period;

        /**
         * Full details for the event(s) the document concents
         */
        private List<ResourceReference> detail = new ArrayList<ResourceReference>();

        public List<CodeableConcept> getCode() { 
          return this.code;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public void setPeriod(Period value) { 
          this.period = value;
        }

        public List<ResourceReference> getDetail() { 
          return this.detail;
        }

  }

    public class SectionComponent extends Element {
        /**
         * A code identifying the kind of content contained within the section
         */
        private CodeableConcept code;

        /**
         * Identifies the primary subject of the section.
         */
        private ResourceReference subject;

        /**
         * Identifies the discrete data that provides the content for the section.
         */
        private ResourceReference content;

        /**
         * Identifies a subtopic within the section as part of the document's table of contents
         */
        private List<SectionComponent> section = new ArrayList<SectionComponent>();

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public ResourceReference getSubject() { 
          return this.subject;
        }

        public void setSubject(ResourceReference value) { 
          this.subject = value;
        }

        public ResourceReference getContent() { 
          return this.content;
        }

        public void setContent(ResourceReference value) { 
          this.content = value;
        }

        public List<SectionComponent> getSection() { 
          return this.section;
        }

  }

    /**
     * Logical Identifier for the document, assigned when created. This identifier stays constant when subsequent versions of the document are created
     */
    private Identifier identifier;

    /**
     * Version specific identifier for the document, assigned when created. This identifier changes when subsequent versions of the document are created
     */
    private Identifier versionIdentifier;

    /**
     * The document creation time, when the document first came into being. Where the document is a transform from an original document in some other format, the ClinicalDocument.effectiveTime is the time the original document is created.
     */
    private Instant created;

    /**
     * The code specifying the particular kind of document (e.g., Prescription, Discharge Summary, Report).
     */
    private Coding class_;

    /**
     * Specifies the particular kind of document (e.g. History and Physical, Discharge Summary, Progress Note)
     */
    private CodeableConcept type;

    /**
     * Official human-readable label for the document
     */
    private String_ title;

    /**
     * The code specifying the level of confidentiality of the XDS Document. These codes are specific to an XDS Affinity Domain.
     */
    private Coding confidentiality;

    /**
     * Identifies the primary subject of the document.
     */
    private ResourceReference subject;

    /**
     * Identifies who is responsible for the information in the document.  (Not necessarily who typed it in.)
     */
    private List<ResourceReference> author = new ArrayList<ResourceReference>();

    /**
     * A participant who has attested to the accuracy of the document
     */
    private List<DocumentAttesterComponent> attester = new ArrayList<DocumentAttesterComponent>();

    /**
     * Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.
     */
    private ResourceReference custodian;

    /**
     * The main event/act/item, such as a colonoscopy or an appendectomy, being documented
     */
    private DocumentEventComponent event;

    /**
     * Describes the clinical visit or type of care this document is associated with.
     */
    private ResourceReference visit;

    /**
     * Identifies the document this document supersedes, if any.
     */
    private Id replaces;

    /**
     * Additional provenance about the document and the resources that are the sections
     */
    private List<ResourceReference> provenance = new ArrayList<ResourceReference>();

    /**
     * A fixed CSS stylesheet to use when rendering the documents
     */
    private Attachment stylesheet;

    /**
     * An alternative representation of the document that can be used in place of the html based rendering
     */
    private Attachment representation;

    /**
     * Identifies a main topic within the document's table of contents
     */
    private List<SectionComponent> section = new ArrayList<SectionComponent>();

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public Identifier getVersionIdentifier() { 
      return this.versionIdentifier;
    }

    public void setVersionIdentifier(Identifier value) { 
      this.versionIdentifier = value;
    }

    public Instant getCreated() { 
      return this.created;
    }

    public void setCreated(Instant value) { 
      this.created = value;
    }

    public Calendar getCreatedSimple() { 
      return this.created == null ? null : this.created.getValue();
    }

    public void setCreatedSimple(Calendar value) { 
        if (this.created == null)
          this.created = new Instant();
        this.created.setValue(value);
    }

    public Coding getClass_() { 
      return this.class_;
    }

    public void setClass_(Coding value) { 
      this.class_ = value;
    }

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
    }

    public String_ getTitle() { 
      return this.title;
    }

    public void setTitle(String_ value) { 
      this.title = value;
    }

    public String getTitleSimple() { 
      return this.title == null ? null : this.title.getValue();
    }

    public void setTitleSimple(String value) { 
      if (value == null)
        this.title = null;
      else {
        if (this.title == null)
          this.title = new String_();
        this.title.setValue(value);
      }
    }

    public Coding getConfidentiality() { 
      return this.confidentiality;
    }

    public void setConfidentiality(Coding value) { 
      this.confidentiality = value;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public List<ResourceReference> getAuthor() { 
      return this.author;
    }

    public List<DocumentAttesterComponent> getAttester() { 
      return this.attester;
    }

    public ResourceReference getCustodian() { 
      return this.custodian;
    }

    public void setCustodian(ResourceReference value) { 
      this.custodian = value;
    }

    public DocumentEventComponent getEvent() { 
      return this.event;
    }

    public void setEvent(DocumentEventComponent value) { 
      this.event = value;
    }

    public ResourceReference getVisit() { 
      return this.visit;
    }

    public void setVisit(ResourceReference value) { 
      this.visit = value;
    }

    public Id getReplaces() { 
      return this.replaces;
    }

    public void setReplaces(Id value) { 
      this.replaces = value;
    }

    public String getReplacesSimple() { 
      return this.replaces == null ? null : this.replaces.getValue();
    }

    public void setReplacesSimple(String value) { 
      if (value == null)
        this.replaces = null;
      else {
        if (this.replaces == null)
          this.replaces = new Id();
        this.replaces.setValue(value);
      }
    }

    public List<ResourceReference> getProvenance() { 
      return this.provenance;
    }

    public Attachment getStylesheet() { 
      return this.stylesheet;
    }

    public void setStylesheet(Attachment value) { 
      this.stylesheet = value;
    }

    public Attachment getRepresentation() { 
      return this.representation;
    }

    public void setRepresentation(Attachment value) { 
      this.representation = value;
    }

    public List<SectionComponent> getSection() { 
      return this.section;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Document;
   }


}

