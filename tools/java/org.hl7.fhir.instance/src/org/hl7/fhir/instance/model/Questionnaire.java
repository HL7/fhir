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

// Generated on Wed, Oct 23, 2013 23:11+1100 for FHIR v0.12

import java.util.*;

/**
 * A set of answers to predefined lists of questions. The questions may be ordered and grouped into coherent subsets, corresponding to the structure of the grouping of the underlying questions.
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

    public class QuestionComponent extends BackboneElement {
        /**
         * Structured name for the question.
         */
        protected CodeableConcept name;

        /**
         * Text of the question as it may appear on screen or on a form.
         */
        protected String_ text;

        /**
         * Single-valued answer to the question.
         */
        protected Type answer;

        /**
         * One of more selections from the list of options.
         */
        protected List<Coding> choice = new ArrayList<Coding>();

        /**
         * Reference to a valueset containing the possible options.
         */
        protected Type options;

        /**
         * Structured answer in the form of a FHIR Resource or datatype.
         */
        protected org.hl7.fhir.instance.model.Type data;

        /**
         * The remark contains information about the answer given. This is additional information about the answer the author wishes to convey, but should not be used to contain information that is part of the answer itself.
         */
        protected String_ remarks;

        public CodeableConcept getName() { 
          return this.name;
        }

        public void setName(CodeableConcept value) { 
          this.name = value;
        }

        public String_ getText() { 
          return this.text;
        }

        public void setText(String_ value) { 
          this.text = value;
        }

        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        public void setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
        }

        public Type getAnswer() { 
          return this.answer;
        }

        public void setAnswer(Type value) { 
          this.answer = value;
        }

        public List<Coding> getChoice() { 
          return this.choice;
        }

    // syntactic sugar
        public Coding addChoice() { 
          Coding t = new Coding();
          this.choice.add(t);
          return t;
        }

        public Type getOptions() { 
          return this.options;
        }

        public void setOptions(Type value) { 
          this.options = value;
        }

        public org.hl7.fhir.instance.model.Type getData() { 
          return this.data;
        }

        public void setData(org.hl7.fhir.instance.model.Type value) { 
          this.data = value;
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

      public QuestionComponent copy(Questionnaire e) {
        QuestionComponent dst = e.new QuestionComponent();
        dst.name = name == null ? null : name.copy();
        dst.text = text == null ? null : text.copy();
        dst.answer = answer == null ? null : answer.copy();
        dst.choice = new ArrayList<Coding>();
        for (Coding i : choice)
          dst.choice.add(i.copy());
        dst.options = options == null ? null : options.copy();
        dst.data = data == null ? null : data.copy();
        dst.remarks = remarks == null ? null : remarks.copy();
        return dst;
      }

  }

    public class GroupComponent extends BackboneElement {
        /**
         * Structured name for a section of a predefined list of questions this questionnaire is responding to.
         */
        protected CodeableConcept name;

        /**
         * Header for the group, used for display purposes.
         */
        protected String_ header;

        /**
         * Additional text for the group, used for display purposes.
         */
        protected String_ text;

        /**
         * More specific subject this section's answers are about, details the subject given in Questionnaire.
         */
        protected ResourceReference subject;

        /**
         * Set of questions within this group.
         */
        protected List<QuestionComponent> question = new ArrayList<QuestionComponent>();

        /**
         * A sub-group within a group.
         */
        protected List<GroupComponent> group = new ArrayList<GroupComponent>();

        public CodeableConcept getName() { 
          return this.name;
        }

        public void setName(CodeableConcept value) { 
          this.name = value;
        }

        public String_ getHeader() { 
          return this.header;
        }

        public void setHeader(String_ value) { 
          this.header = value;
        }

        public String getHeaderSimple() { 
          return this.header == null ? null : this.header.getValue();
        }

        public void setHeaderSimple(String value) { 
          if (value == null)
            this.header = null;
          else {
            if (this.header == null)
              this.header = new String_();
            this.header.setValue(value);
          }
        }

        public String_ getText() { 
          return this.text;
        }

        public void setText(String_ value) { 
          this.text = value;
        }

        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        public void setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
        }

        public ResourceReference getSubject() { 
          return this.subject;
        }

        public void setSubject(ResourceReference value) { 
          this.subject = value;
        }

        public List<QuestionComponent> getQuestion() { 
          return this.question;
        }

    // syntactic sugar
        public QuestionComponent addQuestion() { 
          QuestionComponent t = new QuestionComponent();
          this.question.add(t);
          return t;
        }

        public List<GroupComponent> getGroup() { 
          return this.group;
        }

    // syntactic sugar
        public GroupComponent addGroup() { 
          GroupComponent t = new GroupComponent();
          this.group.add(t);
          return t;
        }

      public GroupComponent copy(Questionnaire e) {
        GroupComponent dst = e.new GroupComponent();
        dst.name = name == null ? null : name.copy();
        dst.header = header == null ? null : header.copy();
        dst.text = text == null ? null : text.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.question = new ArrayList<QuestionComponent>();
        for (QuestionComponent i : question)
          dst.question.add(i.copy(e));
        dst.group = new ArrayList<GroupComponent>();
        for (GroupComponent i : group)
          dst.group.add(i.copy(e));
        return dst;
      }

  }

    /**
     * The status of the questionnaire as a whole.
     */
    protected Enumeration<ObservationStatus> status;

    /**
     * The date and/or time that this version of the questionnaire was authored.
     */
    protected DateTime authored;

    /**
     * The subject of the questionnaires: this is the patient that the answers apply to, but this person is not necessarily the source of information.
     */
    protected ResourceReference subject;

    /**
     * Person that collected the answers to the questions in the Questionnaire.
     */
    protected ResourceReference author;

    /**
     * The person that answered the questions about the subject. Only used when this is not the subject him/herself.
     */
    protected ResourceReference source;

    /**
     * Structured name for a predefined list of questions this questionnaire is responding to.
     */
    protected CodeableConcept name;

    /**
     * This records identifiers associated with this question/answer set that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Encounter during which this questionnaireanswers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.
     */
    protected ResourceReference encounter;

    /**
     * Answers to questions on a questionnaire.
     */
    protected List<QuestionComponent> question = new ArrayList<QuestionComponent>();

    /**
     * A group of questions to a possibly similarly grouped set of question in the questionnaire.
     */
    protected List<GroupComponent> group = new ArrayList<GroupComponent>();

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

    public DateTime getAuthored() { 
      return this.authored;
    }

    public void setAuthored(DateTime value) { 
      this.authored = value;
    }

    public String getAuthoredSimple() { 
      return this.authored == null ? null : this.authored.getValue();
    }

    public void setAuthoredSimple(String value) { 
        if (this.authored == null)
          this.authored = new DateTime();
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

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    public void setEncounter(ResourceReference value) { 
      this.encounter = value;
    }

    public List<QuestionComponent> getQuestion() { 
      return this.question;
    }

    // syntactic sugar
    public QuestionComponent addQuestion() { 
      QuestionComponent t = new QuestionComponent();
      this.question.add(t);
      return t;
    }

    public List<GroupComponent> getGroup() { 
      return this.group;
    }

    // syntactic sugar
    public GroupComponent addGroup() { 
      GroupComponent t = new GroupComponent();
      this.group.add(t);
      return t;
    }

      public Questionnaire copy() {
        Questionnaire dst = new Questionnaire();
        dst.status = status == null ? null : status.copy();
        dst.authored = authored == null ? null : authored.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.author = author == null ? null : author.copy();
        dst.source = source == null ? null : source.copy();
        dst.name = name == null ? null : name.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.question = new ArrayList<QuestionComponent>();
        for (QuestionComponent i : question)
          dst.question.add(i.copy(dst));
        dst.group = new ArrayList<GroupComponent>();
        for (GroupComponent i : group)
          dst.group.add(i.copy(dst));
        return dst;
      }

      protected Questionnaire typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Questionnaire;
   }


}

