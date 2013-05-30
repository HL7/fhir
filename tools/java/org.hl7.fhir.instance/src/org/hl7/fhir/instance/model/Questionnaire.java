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

// Generated on Thu, May 30, 2013 06:48+1000 for FHIR v0.09

import java.util.*;

/**
 * A set of answers to predefined lists of questions. The answers may be grouped into coherent subsets, corresponding to the structure of the grouping of the underlying questions.
 */
public class Questionnaire extends Resource {

    public enum ObservationStatus {
        registered, // The existence of the observation is registered, but there is no result yet available
        interim, // This is an initial or interim observation: data may be incomplete or unverified
        final_, // The observation is complete and verified by an authorised person
        amended, // The observation has been modified subsequent to being Final, and is complete and verified by an authorised person
        cancelled, // The observation is unavailable because the measurement was not started or not completed (also sometimes called "aborted")
        withdrawn, // The observation has been withdrawn following previous Final release
        Null; // added to help the parsers
        public static ObservationStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return registered;
        if ("interim".equals(codeString))
          return interim;
        if ("final".equals(codeString))
          return final_;
        if ("amended".equals(codeString))
          return amended;
        if ("cancelled".equals(codeString))
          return cancelled;
        if ("withdrawn".equals(codeString))
          return withdrawn;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case registered: return "registered";
            case interim: return "interim";
            case final_: return "final";
            case amended: return "amended";
            case cancelled: return "cancelled";
            case withdrawn: return "withdrawn";
            default: return "?";
          }
        }
    }

  public class ObservationStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return ObservationStatus.registered;
        if ("interim".equals(codeString))
          return ObservationStatus.interim;
        if ("final".equals(codeString))
          return ObservationStatus.final_;
        if ("amended".equals(codeString))
          return ObservationStatus.amended;
        if ("cancelled".equals(codeString))
          return ObservationStatus.cancelled;
        if ("withdrawn".equals(codeString))
          return ObservationStatus.withdrawn;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationStatus.registered)
        return "registered";
      if (code == ObservationStatus.interim)
        return "interim";
      if (code == ObservationStatus.final_)
        return "final";
      if (code == ObservationStatus.amended)
        return "amended";
      if (code == ObservationStatus.cancelled)
        return "cancelled";
      if (code == ObservationStatus.withdrawn)
        return "withdrawn";
      return "?";
      }
    }

    public class AnswerComponent extends Element {
        /**
         * Code of the answer, used to relate an answer to a question in the questionnaire and/or the actual question text
         */
        private CodeableConcept name;

        /**
         * The actual answer data
         */
        private Type value;

        /**
         * Data captured from the care process which supports the given answer.
         */
        private ResourceReference evidence;

        /**
         * The remark contains information about the answer given. This is additional information about the anwer the author wishes to convey, but should not be used to contain information that is part of the answer itself.
         */
        private String_ remarks;

        public CodeableConcept getName() { 
          return this.name;
        }

        public void setName(CodeableConcept value) { 
          this.name = value;
        }

        public Type getValue() { 
          return this.value;
        }

        public void setValue(Type value) { 
          this.value = value;
        }

        public ResourceReference getEvidence() { 
          return this.evidence;
        }

        public void setEvidence(ResourceReference value) { 
          this.evidence = value;
        }

        public String_ getRemarks() { 
          return this.remarks;
        }

        public void setRemarks(String_ value) { 
          this.remarks = value;
        }

        public String getRemarksSimple() { 
          return this.remarks == null ? null : this.remarks.getValue();
        }

        public void setRemarksSimple(String value) { 
          if (value == null)
            this.remarks = null;
          else {
            if (this.remarks == null)
              this.remarks = new String_();
            this.remarks.setValue(value);
          }
        }

  }

    public class SectionComponent extends Element {
        /**
         * Structured name for a section of a predefined list of questions this questionnaire is responding to.
         */
        private CodeableConcept name;

        /**
         * Answers to questions on a section of a questionnaire
         */
        private List<AnswerComponent> answer = new ArrayList<AnswerComponent>();

        /**
         * A sub-section within a section in a questionnaire
         */
        private List<SectionComponent> section = new ArrayList<SectionComponent>();

        public CodeableConcept getName() { 
          return this.name;
        }

        public void setName(CodeableConcept value) { 
          this.name = value;
        }

        public List<AnswerComponent> getAnswer() { 
          return this.answer;
        }

        public List<SectionComponent> getSection() { 
          return this.section;
        }

  }

    /**
     * The status of the questionnaire as a whole
     */
    private Enumeration<ObservationStatus> status;

    /**
     * The date and/or time that this version of the questionnaire was authored
     */
    private Instant authored;

    /**
     * The subject of the questionnaires: this is the patient that the answers apply to, but this person is not necessarily the source of information
     */
    private ResourceReference subject;

    /**
     * Person that collected the answers to the questions in the Questionnaire
     */
    private ResourceReference author;

    /**
     * The person that answered the questions about the subject. Only used when this is not the subject him/herself
     */
    private ResourceReference source;

    /**
     * Structured name for a predefined list of questions this questionnaire is responding to
     */
    private CodeableConcept name;

    /**
     * An identifier that identifier this specific set of answers
     */
    private Identifier identifier;

    /**
     * Visit during which this questionnaireanswers were collected. When there were multiple visits, this is the one considered most relevant to the context of the answers.
     */
    private ResourceReference visit;

    /**
     * Answers to questions on a questionnaire
     */
    private List<AnswerComponent> answer = new ArrayList<AnswerComponent>();

    /**
     * A group of anwers to a possibly similarly grouped set of question in the questionnaire
     */
    private List<SectionComponent> section = new ArrayList<SectionComponent>();

    public Enumeration<ObservationStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<ObservationStatus> value) { 
      this.status = value;
    }

    public ObservationStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(ObservationStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ObservationStatus>();
        this.status.setValue(value);
    }

    public Instant getAuthored() { 
      return this.authored;
    }

    public void setAuthored(Instant value) { 
      this.authored = value;
    }

    public Calendar getAuthoredSimple() { 
      return this.authored == null ? null : this.authored.getValue();
    }

    public void setAuthoredSimple(Calendar value) { 
        if (this.authored == null)
          this.authored = new Instant();
        this.authored.setValue(value);
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getAuthor() { 
      return this.author;
    }

    public void setAuthor(ResourceReference value) { 
      this.author = value;
    }

    public ResourceReference getSource() { 
      return this.source;
    }

    public void setSource(ResourceReference value) { 
      this.source = value;
    }

    public CodeableConcept getName() { 
      return this.name;
    }

    public void setName(CodeableConcept value) { 
      this.name = value;
    }

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public ResourceReference getVisit() { 
      return this.visit;
    }

    public void setVisit(ResourceReference value) { 
      this.visit = value;
    }

    public List<AnswerComponent> getAnswer() { 
      return this.answer;
    }

    public List<SectionComponent> getSection() { 
      return this.section;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Questionnaire;
   }


}

