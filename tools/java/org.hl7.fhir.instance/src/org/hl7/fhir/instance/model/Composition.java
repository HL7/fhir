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

// Generated on Sun, Dec 1, 2013 22:52+1100 for FHIR v0.12

import java.util.*;

/**
 * A set of healthcare-related information that is assembled together into a single logical document that provides a single coherent statement of meaning, establishes its own context and that has clinical attestation with regard to who is making the statement.
 */
public class Composition extends Resource {

    public enum CompositionStatus {
        preliminary, // This is an preliminary composition or document (also known as initial or interim). The content may be incomplete or unverified.
        final_, // The composition or document is complete and verified by an appropriate person, and no further work is planned.
        appended, // The composition or document has been modified subsequent to being released as "final", and is complete and verified by an authorised person. The modifications added new information to the composition or document, but did not revise existing content.
        amended, // The composition or document has been modified subsequent to being released as "final", and is complete and verified by an authorised person.
        enteredInError, // The composition or document was originally created/issued in error, and this is an amendment that marks that the entire series should not be considered as valid.
        Null; // added to help the parsers
        public static CompositionStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("preliminary".equals(codeString))
          return preliminary;
        if ("final".equals(codeString))
          return final_;
        if ("appended".equals(codeString))
          return appended;
        if ("amended".equals(codeString))
          return amended;
        if ("entered in error".equals(codeString))
          return enteredInError;
        throw new Exception("Unknown CompositionStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case preliminary: return "preliminary";
            case final_: return "final";
            case appended: return "appended";
            case amended: return "amended";
            case enteredInError: return "entered in error";
            default: return "?";
          }
        }
    }

  public static class CompositionStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("preliminary".equals(codeString))
          return CompositionStatus.preliminary;
        if ("final".equals(codeString))
          return CompositionStatus.final_;
        if ("appended".equals(codeString))
          return CompositionStatus.appended;
        if ("amended".equals(codeString))
          return CompositionStatus.amended;
        if ("entered in error".equals(codeString))
          return CompositionStatus.enteredInError;
        throw new Exception("Unknown CompositionStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CompositionStatus.preliminary)
        return "preliminary";
      if (code == CompositionStatus.final_)
        return "final";
      if (code == CompositionStatus.appended)
        return "appended";
      if (code == CompositionStatus.amended)
        return "amended";
      if (code == CompositionStatus.enteredInError)
        return "entered in error";
      return "?";
      }
    }

    public enum CompositionAttestationMode {
        personal, // The person authenticated the content in their personal capacity.
        professional, // The person authenticated the content in their professional capacity.
        legal, // The person authenticated the content and accepted legal responsibility for its content.
        official, // The organization authenticated the content as consistent with their policies and procedures.
        Null; // added to help the parsers
        public static CompositionAttestationMode fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown CompositionAttestationMode code '"+codeString+"'");
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

  public static class CompositionAttestationModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("personal".equals(codeString))
          return CompositionAttestationMode.personal;
        if ("professional".equals(codeString))
          return CompositionAttestationMode.professional;
        if ("legal".equals(codeString))
          return CompositionAttestationMode.legal;
        if ("official".equals(codeString))
          return CompositionAttestationMode.official;
        throw new Exception("Unknown CompositionAttestationMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CompositionAttestationMode.personal)
        return "personal";
      if (code == CompositionAttestationMode.professional)
        return "professional";
      if (code == CompositionAttestationMode.legal)
        return "legal";
      if (code == CompositionAttestationMode.official)
        return "official";
      return "?";
      }
    }

    public static class CompositionAttesterComponent extends BackboneElement {
        /**
         * The type of attestation the authenticator offers.
         */
        protected Enumeration<CompositionAttestationMode> mode;

        /**
         * When composition was attested by the party.
         */
        protected DateTime time;

        /**
         * Who attested the composition in the specified way.
         */
        protected ResourceReference party;

      public CompositionAttesterComponent() {
        super();
      }

      public CompositionAttesterComponent(Enumeration<CompositionAttestationMode> mode) {
        super();
        this.mode = mode;
      }

        /**
         * @return {@link #mode} (The type of attestation the authenticator offers.)
         */
        public Enumeration<CompositionAttestationMode> getMode() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (The type of attestation the authenticator offers.)
         */
        public CompositionAttesterComponent setMode(Enumeration<CompositionAttestationMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return The type of attestation the authenticator offers.
         */
        public CompositionAttestationMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value The type of attestation the authenticator offers.
         */
        public CompositionAttesterComponent setModeSimple(CompositionAttestationMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<CompositionAttestationMode>();
            this.mode.setValue(value);
          return this;
        }

        /**
         * @return {@link #time} (When composition was attested by the party.)
         */
        public DateTime getTime() { 
          return this.time;
        }

        /**
         * @param value {@link #time} (When composition was attested by the party.)
         */
        public CompositionAttesterComponent setTime(DateTime value) { 
          this.time = value;
          return this;
        }

        /**
         * @return When composition was attested by the party.
         */
        public String getTimeSimple() { 
          return this.time == null ? null : this.time.getValue();
        }

        /**
         * @param value When composition was attested by the party.
         */
        public CompositionAttesterComponent setTimeSimple(String value) { 
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
         * @return {@link #party} (Who attested the composition in the specified way.)
         */
        public ResourceReference getParty() { 
          return this.party;
        }

        /**
         * @param value {@link #party} (Who attested the composition in the specified way.)
         */
        public CompositionAttesterComponent setParty(ResourceReference value) { 
          this.party = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("mode", "code", "The type of attestation the authenticator offers.", 0, java.lang.Integer.MAX_VALUE, mode));
          childrenList.add(new Property("time", "dateTime", "When composition was attested by the party.", 0, java.lang.Integer.MAX_VALUE, time));
          childrenList.add(new Property("party", "Resource(Patient|Practitioner|Organization)", "Who attested the composition in the specified way.", 0, java.lang.Integer.MAX_VALUE, party));
        }

      public CompositionAttesterComponent copy(Composition e) {
        CompositionAttesterComponent dst = new CompositionAttesterComponent();
        dst.mode = mode == null ? null : mode.copy();
        dst.time = time == null ? null : time.copy();
        dst.party = party == null ? null : party.copy();
        return dst;
      }

  }

    public static class CompositionEventComponent extends BackboneElement {
        /**
         * This list of codes represents the main clinical acts, such as a colonoscopy or an appendectomy, being documented. In some cases, the event is inherent in the typeCode, such as a "History and Physical Report" in which the procedure being documented is necessarily a "History and Physical" act.
         */
        protected List<CodeableConcept> code = new ArrayList<CodeableConcept>();

        /**
         * The period of time covered by the documentation. There is no assertion that the documentation is a complete representation for this period, only that it documents events during this time.
         */
        protected Period period;

        /**
         * Full details for the event(s) the composition/documentation concents.
         */
        protected List<ResourceReference> detail = new ArrayList<ResourceReference>();

      public CompositionEventComponent() {
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
         * @return {@link #period} (The period of time covered by the documentation. There is no assertion that the documentation is a complete representation for this period, only that it documents events during this time.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (The period of time covered by the documentation. There is no assertion that the documentation is a complete representation for this period, only that it documents events during this time.)
         */
        public CompositionEventComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        /**
         * @return {@link #detail} (Full details for the event(s) the composition/documentation concents.)
         */
        public List<ResourceReference> getDetail() { 
          return this.detail;
        }

    // syntactic sugar
        /**
         * @return {@link #detail} (Full details for the event(s) the composition/documentation concents.)
         */
        public ResourceReference addDetail() { 
          ResourceReference t = new ResourceReference();
          this.detail.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "This list of codes represents the main clinical acts, such as a colonoscopy or an appendectomy, being documented. In some cases, the event is inherent in the typeCode, such as a 'History and Physical Report' in which the procedure being documented is necessarily a 'History and Physical' act.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("period", "Period", "The period of time covered by the documentation. There is no assertion that the documentation is a complete representation for this period, only that it documents events during this time.", 0, java.lang.Integer.MAX_VALUE, period));
          childrenList.add(new Property("detail", "Resource(Any)", "Full details for the event(s) the composition/documentation concents.", 0, java.lang.Integer.MAX_VALUE, detail));
        }

      public CompositionEventComponent copy(Composition e) {
        CompositionEventComponent dst = new CompositionEventComponent();
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
         * A nested sub-section within this section.
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
         * @return {@link #section} (A nested sub-section within this section.)
         */
        public List<SectionComponent> getSection() { 
          return this.section;
        }

    // syntactic sugar
        /**
         * @return {@link #section} (A nested sub-section within this section.)
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
          childrenList.add(new Property("section", "@Composition.section", "A nested sub-section within this section.", 0, java.lang.Integer.MAX_VALUE, section));
        }

      public SectionComponent copy(Composition e) {
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
     * Logical Identifier for the composition, assigned when created. This identifier stays constant as the composition is changed over time.
     */
    protected Identifier identifier;

    /**
     * The composition editing time, when the composition was last logically changed by the author.
     */
    protected Instant instant;

    /**
     * Specifies the particular kind of composition (e.g. History and Physical, Discharge Summary, Progress Note). This usually equates to the purpose of making the composition.
     */
    protected CodeableConcept type;

    /**
     * A categorisation for the type of the composition. This may be implied by or derived from the code specified in the Composition Type.
     */
    protected CodeableConcept class_;

    /**
     * Official human-readable label for the composition.
     */
    protected String_ title;

    /**
     * The workflow/clinical status of this composition. The status is a marker for the clinical standing of the document.
     */
    protected Enumeration<CompositionStatus> status;

    /**
     * The code specifying the level of confidentiality of the Composition.
     */
    protected Coding confidentiality;

    /**
     * Who or what the composition is about. The composition can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).
     */
    protected ResourceReference subject;

    /**
     * Identifies who is responsible for the information in the composition.  (Not necessarily who typed it in.).
     */
    protected List<ResourceReference> author = new ArrayList<ResourceReference>();

    /**
     * A participant who has attested to the accuracy of the composition/document.
     */
    protected List<CompositionAttesterComponent> attester = new ArrayList<CompositionAttesterComponent>();

    /**
     * Identifies the organization or group who is responsible for ongoing maintenance of and access to the composition/document information.
     */
    protected ResourceReference custodian;

    /**
     * The main event/act/item, such as a colonoscopy or an appendectomy, being documented.
     */
    protected CompositionEventComponent event;

    /**
     * Describes the clinical encounter or type of care this documentation is associated with.
     */
    protected ResourceReference encounter;

    /**
     * The root of the sections that make up the composition.
     */
    protected List<SectionComponent> section = new ArrayList<SectionComponent>();

    public Composition() {
      super();
    }

    public Composition(Instant instant, CodeableConcept type, Enumeration<CompositionStatus> status, Coding confidentiality, ResourceReference subject) {
      super();
      this.instant = instant;
      this.type = type;
      this.status = status;
      this.confidentiality = confidentiality;
      this.subject = subject;
    }

    /**
     * @return {@link #identifier} (Logical Identifier for the composition, assigned when created. This identifier stays constant as the composition is changed over time.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Logical Identifier for the composition, assigned when created. This identifier stays constant as the composition is changed over time.)
     */
    public Composition setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #instant} (The composition editing time, when the composition was last logically changed by the author.)
     */
    public Instant getInstant() { 
      return this.instant;
    }

    /**
     * @param value {@link #instant} (The composition editing time, when the composition was last logically changed by the author.)
     */
    public Composition setInstant(Instant value) { 
      this.instant = value;
      return this;
    }

    /**
     * @return The composition editing time, when the composition was last logically changed by the author.
     */
    public Calendar getInstantSimple() { 
      return this.instant == null ? null : this.instant.getValue();
    }

    /**
     * @param value The composition editing time, when the composition was last logically changed by the author.
     */
    public Composition setInstantSimple(Calendar value) { 
        if (this.instant == null)
          this.instant = new Instant();
        this.instant.setValue(value);
      return this;
    }

    /**
     * @return {@link #type} (Specifies the particular kind of composition (e.g. History and Physical, Discharge Summary, Progress Note). This usually equates to the purpose of making the composition.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Specifies the particular kind of composition (e.g. History and Physical, Discharge Summary, Progress Note). This usually equates to the purpose of making the composition.)
     */
    public Composition setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #class_} (A categorisation for the type of the composition. This may be implied by or derived from the code specified in the Composition Type.)
     */
    public CodeableConcept getClass_() { 
      return this.class_;
    }

    /**
     * @param value {@link #class_} (A categorisation for the type of the composition. This may be implied by or derived from the code specified in the Composition Type.)
     */
    public Composition setClass_(CodeableConcept value) { 
      this.class_ = value;
      return this;
    }

    /**
     * @return {@link #title} (Official human-readable label for the composition.)
     */
    public String_ getTitle() { 
      return this.title;
    }

    /**
     * @param value {@link #title} (Official human-readable label for the composition.)
     */
    public Composition setTitle(String_ value) { 
      this.title = value;
      return this;
    }

    /**
     * @return Official human-readable label for the composition.
     */
    public String getTitleSimple() { 
      return this.title == null ? null : this.title.getValue();
    }

    /**
     * @param value Official human-readable label for the composition.
     */
    public Composition setTitleSimple(String value) { 
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
     * @return {@link #status} (The workflow/clinical status of this composition. The status is a marker for the clinical standing of the document.)
     */
    public Enumeration<CompositionStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The workflow/clinical status of this composition. The status is a marker for the clinical standing of the document.)
     */
    public Composition setStatus(Enumeration<CompositionStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The workflow/clinical status of this composition. The status is a marker for the clinical standing of the document.
     */
    public CompositionStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The workflow/clinical status of this composition. The status is a marker for the clinical standing of the document.
     */
    public Composition setStatusSimple(CompositionStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<CompositionStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #confidentiality} (The code specifying the level of confidentiality of the Composition.)
     */
    public Coding getConfidentiality() { 
      return this.confidentiality;
    }

    /**
     * @param value {@link #confidentiality} (The code specifying the level of confidentiality of the Composition.)
     */
    public Composition setConfidentiality(Coding value) { 
      this.confidentiality = value;
      return this;
    }

    /**
     * @return {@link #subject} (Who or what the composition is about. The composition can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who or what the composition is about. The composition can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).)
     */
    public Composition setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #author} (Identifies who is responsible for the information in the composition.  (Not necessarily who typed it in.).)
     */
    public List<ResourceReference> getAuthor() { 
      return this.author;
    }

    // syntactic sugar
    /**
     * @return {@link #author} (Identifies who is responsible for the information in the composition.  (Not necessarily who typed it in.).)
     */
    public ResourceReference addAuthor() { 
      ResourceReference t = new ResourceReference();
      this.author.add(t);
      return t;
    }

    /**
     * @return {@link #attester} (A participant who has attested to the accuracy of the composition/document.)
     */
    public List<CompositionAttesterComponent> getAttester() { 
      return this.attester;
    }

    // syntactic sugar
    /**
     * @return {@link #attester} (A participant who has attested to the accuracy of the composition/document.)
     */
    public CompositionAttesterComponent addAttester() { 
      CompositionAttesterComponent t = new CompositionAttesterComponent();
      this.attester.add(t);
      return t;
    }

    /**
     * @return {@link #custodian} (Identifies the organization or group who is responsible for ongoing maintenance of and access to the composition/document information.)
     */
    public ResourceReference getCustodian() { 
      return this.custodian;
    }

    /**
     * @param value {@link #custodian} (Identifies the organization or group who is responsible for ongoing maintenance of and access to the composition/document information.)
     */
    public Composition setCustodian(ResourceReference value) { 
      this.custodian = value;
      return this;
    }

    /**
     * @return {@link #event} (The main event/act/item, such as a colonoscopy or an appendectomy, being documented.)
     */
    public CompositionEventComponent getEvent() { 
      return this.event;
    }

    /**
     * @param value {@link #event} (The main event/act/item, such as a colonoscopy or an appendectomy, being documented.)
     */
    public Composition setEvent(CompositionEventComponent value) { 
      this.event = value;
      return this;
    }

    /**
     * @return {@link #encounter} (Describes the clinical encounter or type of care this documentation is associated with.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (Describes the clinical encounter or type of care this documentation is associated with.)
     */
    public Composition setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #section} (The root of the sections that make up the composition.)
     */
    public List<SectionComponent> getSection() { 
      return this.section;
    }

    // syntactic sugar
    /**
     * @return {@link #section} (The root of the sections that make up the composition.)
     */
    public SectionComponent addSection() { 
      SectionComponent t = new SectionComponent();
      this.section.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Logical Identifier for the composition, assigned when created. This identifier stays constant as the composition is changed over time.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("instant", "instant", "The composition editing time, when the composition was last logically changed by the author.", 0, java.lang.Integer.MAX_VALUE, instant));
        childrenList.add(new Property("type", "CodeableConcept", "Specifies the particular kind of composition (e.g. History and Physical, Discharge Summary, Progress Note). This usually equates to the purpose of making the composition.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("class", "CodeableConcept", "A categorisation for the type of the composition. This may be implied by or derived from the code specified in the Composition Type.", 0, java.lang.Integer.MAX_VALUE, class_));
        childrenList.add(new Property("title", "string", "Official human-readable label for the composition.", 0, java.lang.Integer.MAX_VALUE, title));
        childrenList.add(new Property("status", "code", "The workflow/clinical status of this composition. The status is a marker for the clinical standing of the document.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("confidentiality", "Coding", "The code specifying the level of confidentiality of the Composition.", 0, java.lang.Integer.MAX_VALUE, confidentiality));
        childrenList.add(new Property("subject", "Resource(Patient|Practitioner|Group|Device|Location)", "Who or what the composition is about. The composition can be about a person, (patient or healthcare practitioner), a device (I.e. machine) or even a group of subjects (such as a document about a herd of farm animals, or a set of patients that share a common exposure).", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("author", "Resource(Practitioner|Device)", "Identifies who is responsible for the information in the composition.  (Not necessarily who typed it in.).", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("attester", "", "A participant who has attested to the accuracy of the composition/document.", 0, java.lang.Integer.MAX_VALUE, attester));
        childrenList.add(new Property("custodian", "Resource(Organization)", "Identifies the organization or group who is responsible for ongoing maintenance of and access to the composition/document information.", 0, java.lang.Integer.MAX_VALUE, custodian));
        childrenList.add(new Property("event", "", "The main event/act/item, such as a colonoscopy or an appendectomy, being documented.", 0, java.lang.Integer.MAX_VALUE, event));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "Describes the clinical encounter or type of care this documentation is associated with.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("section", "", "The root of the sections that make up the composition.", 0, java.lang.Integer.MAX_VALUE, section));
      }

      public Composition copy() {
        Composition dst = new Composition();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.instant = instant == null ? null : instant.copy();
        dst.type = type == null ? null : type.copy();
        dst.class_ = class_ == null ? null : class_.copy();
        dst.title = title == null ? null : title.copy();
        dst.status = status == null ? null : status.copy();
        dst.confidentiality = confidentiality == null ? null : confidentiality.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.author = new ArrayList<ResourceReference>();
        for (ResourceReference i : author)
          dst.author.add(i.copy());
        dst.attester = new ArrayList<CompositionAttesterComponent>();
        for (CompositionAttesterComponent i : attester)
          dst.attester.add(i.copy(dst));
        dst.custodian = custodian == null ? null : custodian.copy();
        dst.event = event == null ? null : event.copy(dst);
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.section = new ArrayList<SectionComponent>();
        for (SectionComponent i : section)
          dst.section.add(i.copy(dst));
        return dst;
      }

      protected Composition typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Composition;
   }


}

