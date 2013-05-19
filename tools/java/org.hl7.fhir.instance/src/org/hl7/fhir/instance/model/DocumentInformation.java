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

// Generated on Wed, Apr 3, 2013 07:59+1100 for FHIR v0.07

import java.util.*;

/**
 * Describes a document: the context and searchable metadata for a document
 */
public class DocumentInformation extends Type {

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

    public class Attester extends Element {
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
          if (value == null)
            this.mode = null;
          else {
            if (this.mode == null)
              this.mode = new Enumeration<DocumentAttestationMode>();
            this.mode.setValue(value);
          }
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

    public class Event extends Element {
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

    /**
     * Logical Identifier for the document, assigned when created. This identifier stays constant when subsequent versions of the document are created
     */
    private Identifier id;

    /**
     * Version specific identifier for the document, assigned when created. This identifier changes when subsequent versions of the document are created
     */
    private Identifier versionId;

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
    private List<Attester> attester = new ArrayList<Attester>();

    /**
     * Identifies the organization or group who is responsible for ongoing maintenance of and access to the document.
     */
    private ResourceReference custodian;

    /**
     * The main event/act/item, such as a colonoscopy or an appendectomy, being documented
     */
    private Event event;

    /**
     * Describes the clinical encounter or type of care this document is associated with.
     */
    private ResourceReference encounter;

    /**
     * This code represents the type of organizational setting of the clinical encounter during which the documented act occurred
     */
    private CodeableConcept facilityType;

    /**
     * The code specifying the clinical specialty where the act that resulted in the document was performed (e.g., Family Practice, Laboratory, Radiology).
     */
    private CodeableConcept practiceSetting;

    public Identifier getId() { 
      return this.id;
    }

    public void setId(Identifier value) { 
      this.id = value;
    }

    public Identifier getVersionId() { 
      return this.versionId;
    }

    public void setVersionId(Identifier value) { 
      this.versionId = value;
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
      if (value == null)
        this.created = null;
      else {
        if (this.created == null)
          this.created = new Instant();
        this.created.setValue(value);
      }
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

    public List<Attester> getAttester() { 
      return this.attester;
    }

    public ResourceReference getCustodian() { 
      return this.custodian;
    }

    public void setCustodian(ResourceReference value) { 
      this.custodian = value;
    }

    public Event getEvent() { 
      return this.event;
    }

    public void setEvent(Event value) { 
      this.event = value;
    }

    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    public void setEncounter(ResourceReference value) { 
      this.encounter = value;
    }

    public CodeableConcept getFacilityType() { 
      return this.facilityType;
    }

    public void setFacilityType(CodeableConcept value) { 
      this.facilityType = value;
    }

    public CodeableConcept getPracticeSetting() { 
      return this.practiceSetting;
    }

    public void setPracticeSetting(CodeableConcept value) { 
      this.practiceSetting = value;
    }


}

