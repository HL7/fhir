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

// Generated on Thu, Jan 23, 2014 01:09-0600 for FHIR v0.12

import java.util.*;

/**
 * A structured set of questions and their answers. The Questionnaire may contain questions, answers or both. The questions may be ordered and grouped into coherent subsets, corresponding to the structure of the grouping of the underlying questions.
 */
public class Questionnaire extends Resource {

    public enum QuestionnaireStatus {
        draft, // This Questionnaire is used as a template but the template is not ready for use or publication.
        published, // This Questionnaire is used as a template, is published and ready for use.
        retired, // This Questionnaire is used as a template but should no longer be used for new Questionnaires.
        inProgress, // This Questionnaire has been filled out with answers, but changes or additions are still expected to be made to it.
        completed, // This Questionnaire has been filled out with answers, and the current content is regarded as definitive.
        amended, // This Questionnaire has been filled out with answers, then marked as complete, yet changes or additions have been made to it afterwards.
        Null; // added to help the parsers
        public static QuestionnaireStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return draft;
        if ("published".equals(codeString))
          return published;
        if ("retired".equals(codeString))
          return retired;
        if ("in progress".equals(codeString))
          return inProgress;
        if ("completed".equals(codeString))
          return completed;
        if ("amended".equals(codeString))
          return amended;
        throw new Exception("Unknown QuestionnaireStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case draft: return "draft";
            case published: return "published";
            case retired: return "retired";
            case inProgress: return "in progress";
            case completed: return "completed";
            case amended: return "amended";
            default: return "?";
          }
        }
    }

  public static class QuestionnaireStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return QuestionnaireStatus.draft;
        if ("published".equals(codeString))
          return QuestionnaireStatus.published;
        if ("retired".equals(codeString))
          return QuestionnaireStatus.retired;
        if ("in progress".equals(codeString))
          return QuestionnaireStatus.inProgress;
        if ("completed".equals(codeString))
          return QuestionnaireStatus.completed;
        if ("amended".equals(codeString))
          return QuestionnaireStatus.amended;
        throw new Exception("Unknown QuestionnaireStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == QuestionnaireStatus.draft)
        return "draft";
      if (code == QuestionnaireStatus.published)
        return "published";
      if (code == QuestionnaireStatus.retired)
        return "retired";
      if (code == QuestionnaireStatus.inProgress)
        return "in progress";
      if (code == QuestionnaireStatus.completed)
        return "completed";
      if (code == QuestionnaireStatus.amended)
        return "amended";
      return "?";
      }
    }

    public static class GroupComponent extends BackboneElement {
        /**
         * Structured name for a section of a predefined list of questions this questionnaire is responding to.
         */
        protected CodeableConcept name;

        /**
         * Text that is displayed above the contents of the group.
         */
        protected String_ header;

        /**
         * Additional text for the group, used for display purposes.
         */
        protected String_ text;

        /**
         * Whether the contents of this group have a meaningful order.
         */
        protected Boolean ordered;

        /**
         * More specific subject this section's answers are about, details the subject given in Questionnaire.
         */
        protected ResourceReference subject;

        /**
         * A sub-group within a group.
         */
        protected List<GroupComponent> group = new ArrayList<GroupComponent>();

        /**
         * Set of questions within this group.
         */
        protected List<QuestionComponent> question = new ArrayList<QuestionComponent>();

      public GroupComponent() {
        super();
      }

        /**
         * @return {@link #name} (Structured name for a section of a predefined list of questions this questionnaire is responding to.)
         */
        public CodeableConcept getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Structured name for a section of a predefined list of questions this questionnaire is responding to.)
         */
        public GroupComponent setName(CodeableConcept value) { 
          this.name = value;
          return this;
        }

        /**
         * @return {@link #header} (Text that is displayed above the contents of the group.)
         */
        public String_ getHeader() { 
          return this.header;
        }

        /**
         * @param value {@link #header} (Text that is displayed above the contents of the group.)
         */
        public GroupComponent setHeader(String_ value) { 
          this.header = value;
          return this;
        }

        /**
         * @return Text that is displayed above the contents of the group.
         */
        public String getHeaderSimple() { 
          return this.header == null ? null : this.header.getValue();
        }

        /**
         * @param value Text that is displayed above the contents of the group.
         */
        public GroupComponent setHeaderSimple(String value) { 
          if (value == null)
            this.header = null;
          else {
            if (this.header == null)
              this.header = new String_();
            this.header.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #text} (Additional text for the group, used for display purposes.)
         */
        public String_ getText() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Additional text for the group, used for display purposes.)
         */
        public GroupComponent setText(String_ value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Additional text for the group, used for display purposes.
         */
        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Additional text for the group, used for display purposes.
         */
        public GroupComponent setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #ordered} (Whether the contents of this group have a meaningful order.)
         */
        public Boolean getOrdered() { 
          return this.ordered;
        }

        /**
         * @param value {@link #ordered} (Whether the contents of this group have a meaningful order.)
         */
        public GroupComponent setOrdered(Boolean value) { 
          this.ordered = value;
          return this;
        }

        /**
         * @return Whether the contents of this group have a meaningful order.
         */
        public boolean getOrderedSimple() { 
          return this.ordered == null ? false : this.ordered.getValue();
        }

        /**
         * @param value Whether the contents of this group have a meaningful order.
         */
        public GroupComponent setOrderedSimple(boolean value) { 
          if (value == false)
            this.ordered = null;
          else {
            if (this.ordered == null)
              this.ordered = new Boolean();
            this.ordered.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subject} (More specific subject this section's answers are about, details the subject given in Questionnaire.)
         */
        public ResourceReference getSubject() { 
          return this.subject;
        }

        /**
         * @param value {@link #subject} (More specific subject this section's answers are about, details the subject given in Questionnaire.)
         */
        public GroupComponent setSubject(ResourceReference value) { 
          this.subject = value;
          return this;
        }

        /**
         * @return {@link #group} (A sub-group within a group.)
         */
        public List<GroupComponent> getGroup() { 
          return this.group;
        }

    // syntactic sugar
        /**
         * @return {@link #group} (A sub-group within a group.)
         */
        public GroupComponent addGroup() { 
          GroupComponent t = new GroupComponent();
          this.group.add(t);
          return t;
        }

        /**
         * @return {@link #question} (Set of questions within this group.)
         */
        public List<QuestionComponent> getQuestion() { 
          return this.question;
        }

    // syntactic sugar
        /**
         * @return {@link #question} (Set of questions within this group.)
         */
        public QuestionComponent addQuestion() { 
          QuestionComponent t = new QuestionComponent();
          this.question.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "CodeableConcept", "Structured name for a section of a predefined list of questions this questionnaire is responding to.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("header", "string", "Text that is displayed above the contents of the group.", 0, java.lang.Integer.MAX_VALUE, header));
          childrenList.add(new Property("text", "string", "Additional text for the group, used for display purposes.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("ordered", "boolean", "Whether the contents of this group have a meaningful order.", 0, java.lang.Integer.MAX_VALUE, ordered));
          childrenList.add(new Property("subject", "Resource(Any)", "More specific subject this section's answers are about, details the subject given in Questionnaire.", 0, java.lang.Integer.MAX_VALUE, subject));
          childrenList.add(new Property("group", "@Questionnaire.group", "A sub-group within a group.", 0, java.lang.Integer.MAX_VALUE, group));
          childrenList.add(new Property("question", "", "Set of questions within this group.", 0, java.lang.Integer.MAX_VALUE, question));
        }

      public GroupComponent copy(Questionnaire e) {
        GroupComponent dst = new GroupComponent();
        dst.name = name == null ? null : name.copy();
        dst.header = header == null ? null : header.copy();
        dst.text = text == null ? null : text.copy();
        dst.ordered = ordered == null ? null : ordered.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.group = new ArrayList<GroupComponent>();
        for (GroupComponent i : group)
          dst.group.add(i.copy(e));
        dst.question = new ArrayList<QuestionComponent>();
        for (QuestionComponent i : question)
          dst.question.add(i.copy(e));
        return dst;
      }

  }

    public static class QuestionComponent extends BackboneElement {
        /**
         * Structured name for the question that identifies this question within the Questionnaire or Group.
         */
        protected CodeableConcept name;

        /**
         * Text of the question as it is shown to the user.
         */
        protected String_ text;

        /**
         * Single-valued answer to the question.
         */
        protected Type answer;

        /**
         * Selections made by the user from the list of options.
         */
        protected List<Coding> choice = new ArrayList<Coding>();

        /**
         * Reference to a valueset containing the possible options.
         */
        protected ResourceReference options;

        /**
         * Structured answer in the form of a FHIR Resource or datatype.
         */
        protected org.hl7.fhir.instance.model.Type data;

        /**
         * The remark contains information about the answer given. This is additional information about the answer the author wishes to convey, but should not be used to contain information that is part of the answer itself.
         */
        protected String_ remarks;

        /**
         * Nested group, containing nested question for this question.
         */
        protected List<GroupComponent> group = new ArrayList<GroupComponent>();

      public QuestionComponent() {
        super();
      }

        /**
         * @return {@link #name} (Structured name for the question that identifies this question within the Questionnaire or Group.)
         */
        public CodeableConcept getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Structured name for the question that identifies this question within the Questionnaire or Group.)
         */
        public QuestionComponent setName(CodeableConcept value) { 
          this.name = value;
          return this;
        }

        /**
         * @return {@link #text} (Text of the question as it is shown to the user.)
         */
        public String_ getText() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Text of the question as it is shown to the user.)
         */
        public QuestionComponent setText(String_ value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Text of the question as it is shown to the user.
         */
        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Text of the question as it is shown to the user.
         */
        public QuestionComponent setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #answer} (Single-valued answer to the question.)
         */
        public Type getAnswer() { 
          return this.answer;
        }

        /**
         * @param value {@link #answer} (Single-valued answer to the question.)
         */
        public QuestionComponent setAnswer(Type value) { 
          this.answer = value;
          return this;
        }

        /**
         * @return {@link #choice} (Selections made by the user from the list of options.)
         */
        public List<Coding> getChoice() { 
          return this.choice;
        }

    // syntactic sugar
        /**
         * @return {@link #choice} (Selections made by the user from the list of options.)
         */
        public Coding addChoice() { 
          Coding t = new Coding();
          this.choice.add(t);
          return t;
        }

        /**
         * @return {@link #options} (Reference to a valueset containing the possible options.)
         */
        public ResourceReference getOptions() { 
          return this.options;
        }

        /**
         * @param value {@link #options} (Reference to a valueset containing the possible options.)
         */
        public QuestionComponent setOptions(ResourceReference value) { 
          this.options = value;
          return this;
        }

        /**
         * @return {@link #data} (Structured answer in the form of a FHIR Resource or datatype.)
         */
        public org.hl7.fhir.instance.model.Type getData() { 
          return this.data;
        }

        /**
         * @param value {@link #data} (Structured answer in the form of a FHIR Resource or datatype.)
         */
        public QuestionComponent setData(org.hl7.fhir.instance.model.Type value) { 
          this.data = value;
          return this;
        }

        /**
         * @return {@link #remarks} (The remark contains information about the answer given. This is additional information about the answer the author wishes to convey, but should not be used to contain information that is part of the answer itself.)
         */
        public String_ getRemarks() { 
          return this.remarks;
        }

        /**
         * @param value {@link #remarks} (The remark contains information about the answer given. This is additional information about the answer the author wishes to convey, but should not be used to contain information that is part of the answer itself.)
         */
        public QuestionComponent setRemarks(String_ value) { 
          this.remarks = value;
          return this;
        }

        /**
         * @return The remark contains information about the answer given. This is additional information about the answer the author wishes to convey, but should not be used to contain information that is part of the answer itself.
         */
        public String getRemarksSimple() { 
          return this.remarks == null ? null : this.remarks.getValue();
        }

        /**
         * @param value The remark contains information about the answer given. This is additional information about the answer the author wishes to convey, but should not be used to contain information that is part of the answer itself.
         */
        public QuestionComponent setRemarksSimple(String value) { 
          if (value == null)
            this.remarks = null;
          else {
            if (this.remarks == null)
              this.remarks = new String_();
            this.remarks.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #group} (Nested group, containing nested question for this question.)
         */
        public List<GroupComponent> getGroup() { 
          return this.group;
        }

    // syntactic sugar
        /**
         * @return {@link #group} (Nested group, containing nested question for this question.)
         */
        public GroupComponent addGroup() { 
          GroupComponent t = new GroupComponent();
          this.group.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "CodeableConcept", "Structured name for the question that identifies this question within the Questionnaire or Group.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("text", "string", "Text of the question as it is shown to the user.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("answer[x]", "decimal|integer|boolean|date|string|dateTime|instant", "Single-valued answer to the question.", 0, java.lang.Integer.MAX_VALUE, answer));
          childrenList.add(new Property("choice", "Coding", "Selections made by the user from the list of options.", 0, java.lang.Integer.MAX_VALUE, choice));
          childrenList.add(new Property("options", "Resource(ValueSet)", "Reference to a valueset containing the possible options.", 0, java.lang.Integer.MAX_VALUE, options));
          childrenList.add(new Property("data[x]", "*", "Structured answer in the form of a FHIR Resource or datatype.", 0, java.lang.Integer.MAX_VALUE, data));
          childrenList.add(new Property("remarks", "string", "The remark contains information about the answer given. This is additional information about the answer the author wishes to convey, but should not be used to contain information that is part of the answer itself.", 0, java.lang.Integer.MAX_VALUE, remarks));
          childrenList.add(new Property("group", "@Questionnaire.group", "Nested group, containing nested question for this question.", 0, java.lang.Integer.MAX_VALUE, group));
        }

      public QuestionComponent copy(Questionnaire e) {
        QuestionComponent dst = new QuestionComponent();
        dst.name = name == null ? null : name.copy();
        dst.text = text == null ? null : text.copy();
        dst.answer = answer == null ? null : answer.copy();
        dst.choice = new ArrayList<Coding>();
        for (Coding i : choice)
          dst.choice.add(i.copy());
        dst.options = options == null ? null : options.copy();
        dst.data = data == null ? null : data.copy();
        dst.remarks = remarks == null ? null : remarks.copy();
        dst.group = new ArrayList<GroupComponent>();
        for (GroupComponent i : group)
          dst.group.add(i.copy(e));
        return dst;
      }

  }

    /**
     * The lifecycle status of the questionnaire as a whole.
     */
    protected Enumeration<QuestionnaireStatus> status;

    /**
     * The date and/or time that this version of the questionnaire was authored.
     */
    protected DateTime authored;

    /**
     * The subject of the questionnaires: this is the patient that the answers apply to, but this person is not necessarily the source of information.
     */
    protected ResourceReference subject;

    /**
     * Person who received the answers to the questions in the Questionnaire and recorded them in the system.
     */
    protected ResourceReference author;

    /**
     * The person who answered the questions about the subject. Only used when this is not the subject him/herself.
     */
    protected ResourceReference source;

    /**
     * Structured name for a predefined list of questions this questionnaire is responding to.
     */
    protected CodeableConcept name;

    /**
     * This records identifiers associated with this question/answer set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Encounter during which this questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.
     */
    protected ResourceReference encounter;

    /**
     * A group of questions to a possibly similarly grouped set of questions in the questionnaire.
     */
    protected GroupComponent group;

    public Questionnaire() {
      super();
    }

    public Questionnaire(Enumeration<QuestionnaireStatus> status, DateTime authored) {
      super();
      this.status = status;
      this.authored = authored;
    }

    /**
     * @return {@link #status} (The lifecycle status of the questionnaire as a whole.)
     */
    public Enumeration<QuestionnaireStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The lifecycle status of the questionnaire as a whole.)
     */
    public Questionnaire setStatus(Enumeration<QuestionnaireStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The lifecycle status of the questionnaire as a whole.
     */
    public QuestionnaireStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The lifecycle status of the questionnaire as a whole.
     */
    public Questionnaire setStatusSimple(QuestionnaireStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<QuestionnaireStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #authored} (The date and/or time that this version of the questionnaire was authored.)
     */
    public DateTime getAuthored() { 
      return this.authored;
    }

    /**
     * @param value {@link #authored} (The date and/or time that this version of the questionnaire was authored.)
     */
    public Questionnaire setAuthored(DateTime value) { 
      this.authored = value;
      return this;
    }

    /**
     * @return The date and/or time that this version of the questionnaire was authored.
     */
    public DateAndTime getAuthoredSimple() { 
      return this.authored == null ? null : this.authored.getValue();
    }

    /**
     * @param value The date and/or time that this version of the questionnaire was authored.
     */
    public Questionnaire setAuthoredSimple(DateAndTime value) { 
        if (this.authored == null)
          this.authored = new DateTime();
        this.authored.setValue(value);
      return this;
    }

    /**
     * @return {@link #subject} (The subject of the questionnaires: this is the patient that the answers apply to, but this person is not necessarily the source of information.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The subject of the questionnaires: this is the patient that the answers apply to, but this person is not necessarily the source of information.)
     */
    public Questionnaire setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #author} (Person who received the answers to the questions in the Questionnaire and recorded them in the system.)
     */
    public ResourceReference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (Person who received the answers to the questions in the Questionnaire and recorded them in the system.)
     */
    public Questionnaire setAuthor(ResourceReference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #source} (The person who answered the questions about the subject. Only used when this is not the subject him/herself.)
     */
    public ResourceReference getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (The person who answered the questions about the subject. Only used when this is not the subject him/herself.)
     */
    public Questionnaire setSource(ResourceReference value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #name} (Structured name for a predefined list of questions this questionnaire is responding to.)
     */
    public CodeableConcept getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (Structured name for a predefined list of questions this questionnaire is responding to.)
     */
    public Questionnaire setName(CodeableConcept value) { 
      this.name = value;
      return this;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this question/answer set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this question/answer set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #encounter} (Encounter during which this questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (Encounter during which this questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.)
     */
    public Questionnaire setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #group} (A group of questions to a possibly similarly grouped set of questions in the questionnaire.)
     */
    public GroupComponent getGroup() { 
      return this.group;
    }

    /**
     * @param value {@link #group} (A group of questions to a possibly similarly grouped set of questions in the questionnaire.)
     */
    public Questionnaire setGroup(GroupComponent value) { 
      this.group = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("status", "code", "The lifecycle status of the questionnaire as a whole.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("authored", "dateTime", "The date and/or time that this version of the questionnaire was authored.", 0, java.lang.Integer.MAX_VALUE, authored));
        childrenList.add(new Property("subject", "Resource(Patient|RelatedPerson)", "The subject of the questionnaires: this is the patient that the answers apply to, but this person is not necessarily the source of information.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("author", "Resource(Practitioner|Patient|RelatedPerson)", "Person who received the answers to the questions in the Questionnaire and recorded them in the system.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("source", "Resource(Patient|Practitioner|RelatedPerson)", "The person who answered the questions about the subject. Only used when this is not the subject him/herself.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("name", "CodeableConcept", "Structured name for a predefined list of questions this questionnaire is responding to.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this question/answer set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "Encounter during which this questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("group", "", "A group of questions to a possibly similarly grouped set of questions in the questionnaire.", 0, java.lang.Integer.MAX_VALUE, group));
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
        dst.group = group == null ? null : group.copy(dst);
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

