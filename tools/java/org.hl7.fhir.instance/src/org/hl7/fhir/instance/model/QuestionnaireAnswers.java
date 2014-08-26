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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import java.util.*;

/**
 * A structured set of questions and their answers. The questions are ordered and grouped into coherent subsets, corresponding to the structure of the grouping of the underlying questions.
 */
public class QuestionnaireAnswers extends Resource {

    public enum QuestionnaireAnswersStatus {
        inProgress, // This QuestionnaireAnswers has been partially filled out with answers, but changes or additions are still expected to be made to it.
        completed, // This QuestionnaireAnswers has been filled out with answers, and the current content is regarded as definitive.
        amended, // This QuestionnaireAnswers has been filled out with answers, then marked as complete, yet changes or additions have been made to it afterwards.
        Null; // added to help the parsers
        public static QuestionnaireAnswersStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return inProgress;
        if ("completed".equals(codeString))
          return completed;
        if ("amended".equals(codeString))
          return amended;
        throw new Exception("Unknown QuestionnaireAnswersStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case inProgress: return "in progress";
            case completed: return "completed";
            case amended: return "amended";
            default: return "?";
          }
        }
    }

  public static class QuestionnaireAnswersStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return QuestionnaireAnswersStatus.inProgress;
        if ("completed".equals(codeString))
          return QuestionnaireAnswersStatus.completed;
        if ("amended".equals(codeString))
          return QuestionnaireAnswersStatus.amended;
        throw new Exception("Unknown QuestionnaireAnswersStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == QuestionnaireAnswersStatus.inProgress)
        return "in progress";
      if (code == QuestionnaireAnswersStatus.completed)
        return "completed";
      if (code == QuestionnaireAnswersStatus.amended)
        return "amended";
      return "?";
      }
    }

    public static class GroupComponent extends BackboneElement {
        /**
         * Identifies the group from the Questionnaire that corresponds to this group in the QuestionnaireAnswers resource.
         */
        protected StringType linkId;

        /**
         * Text that is displayed above the contents of the group.
         */
        protected StringType title;

        /**
         * Additional text for the group, used for display purposes.
         */
        protected StringType text;

        /**
         * More specific subject this section's answers are about, details the subject given in QuestionnaireAnswers.
         */
        protected ResourceReference subject;

        /**
         * The actual object that is the target of the reference (More specific subject this section's answers are about, details the subject given in QuestionnaireAnswers.)
         */
        protected Resource subjectTarget;

        /**
         * A sub-group within a group. The ordering of groups within this group is relevant.
         */
        protected List<GroupComponent> group = new ArrayList<GroupComponent>();

        /**
         * Set of questions within this group. The order of questions within the group is relevant.
         */
        protected List<QuestionComponent> question = new ArrayList<QuestionComponent>();

        private static final long serialVersionUID = -885347240L;

      public GroupComponent() {
        super();
      }

        /**
         * @return {@link #linkId} (Identifies the group from the Questionnaire that corresponds to this group in the QuestionnaireAnswers resource.)
         */
        public StringType getLinkId() { 
          return this.linkId;
        }

        /**
         * @param value {@link #linkId} (Identifies the group from the Questionnaire that corresponds to this group in the QuestionnaireAnswers resource.)
         */
        public GroupComponent setLinkId(StringType value) { 
          this.linkId = value;
          return this;
        }

        /**
         * @return Identifies the group from the Questionnaire that corresponds to this group in the QuestionnaireAnswers resource.
         */
        public String getLinkIdSimple() { 
          return this.linkId == null ? null : this.linkId.getValue();
        }

        /**
         * @param value Identifies the group from the Questionnaire that corresponds to this group in the QuestionnaireAnswers resource.
         */
        public GroupComponent setLinkIdSimple(String value) { 
          if (value == null)
            this.linkId = null;
          else {
            if (this.linkId == null)
              this.linkId = new StringType();
            this.linkId.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #title} (Text that is displayed above the contents of the group.)
         */
        public StringType getTitle() { 
          return this.title;
        }

        /**
         * @param value {@link #title} (Text that is displayed above the contents of the group.)
         */
        public GroupComponent setTitle(StringType value) { 
          this.title = value;
          return this;
        }

        /**
         * @return Text that is displayed above the contents of the group.
         */
        public String getTitleSimple() { 
          return this.title == null ? null : this.title.getValue();
        }

        /**
         * @param value Text that is displayed above the contents of the group.
         */
        public GroupComponent setTitleSimple(String value) { 
          if (value == null)
            this.title = null;
          else {
            if (this.title == null)
              this.title = new StringType();
            this.title.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #text} (Additional text for the group, used for display purposes.)
         */
        public StringType getText() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Additional text for the group, used for display purposes.)
         */
        public GroupComponent setText(StringType value) { 
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
              this.text = new StringType();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subject} (More specific subject this section's answers are about, details the subject given in QuestionnaireAnswers.)
         */
        public ResourceReference getSubject() { 
          return this.subject;
        }

        /**
         * @param value {@link #subject} (More specific subject this section's answers are about, details the subject given in QuestionnaireAnswers.)
         */
        public GroupComponent setSubject(ResourceReference value) { 
          this.subject = value;
          return this;
        }

        /**
         * @return {@link #subject} (The actual object that is the target of the reference. More specific subject this section's answers are about, details the subject given in QuestionnaireAnswers.)
         */
        public Resource getSubjectTarget() { 
          return this.subjectTarget;
        }

        /**
         * @param value {@link #subject} (The actual object that is the target of the reference. More specific subject this section's answers are about, details the subject given in QuestionnaireAnswers.)
         */
        public GroupComponent setSubjectTarget(Resource value) { 
          this.subjectTarget = value;
          return this;
        }

        /**
         * @return {@link #group} (A sub-group within a group. The ordering of groups within this group is relevant.)
         */
        public List<GroupComponent> getGroup() { 
          return this.group;
        }

    // syntactic sugar
        /**
         * @return {@link #group} (A sub-group within a group. The ordering of groups within this group is relevant.)
         */
        public GroupComponent addGroup() { 
          GroupComponent t = new GroupComponent();
          this.group.add(t);
          return t;
        }

        /**
         * @return {@link #question} (Set of questions within this group. The order of questions within the group is relevant.)
         */
        public List<QuestionComponent> getQuestion() { 
          return this.question;
        }

    // syntactic sugar
        /**
         * @return {@link #question} (Set of questions within this group. The order of questions within the group is relevant.)
         */
        public QuestionComponent addQuestion() { 
          QuestionComponent t = new QuestionComponent();
          this.question.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("linkId", "string", "Identifies the group from the Questionnaire that corresponds to this group in the QuestionnaireAnswers resource.", 0, java.lang.Integer.MAX_VALUE, linkId));
          childrenList.add(new Property("title", "string", "Text that is displayed above the contents of the group.", 0, java.lang.Integer.MAX_VALUE, title));
          childrenList.add(new Property("text", "string", "Additional text for the group, used for display purposes.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("subject", "Resource(Any)", "More specific subject this section's answers are about, details the subject given in QuestionnaireAnswers.", 0, java.lang.Integer.MAX_VALUE, subject));
          childrenList.add(new Property("group", "@QuestionnaireAnswers.group", "A sub-group within a group. The ordering of groups within this group is relevant.", 0, java.lang.Integer.MAX_VALUE, group));
          childrenList.add(new Property("question", "", "Set of questions within this group. The order of questions within the group is relevant.", 0, java.lang.Integer.MAX_VALUE, question));
        }

      public GroupComponent copy() {
        GroupComponent dst = new GroupComponent();
        dst.linkId = linkId == null ? null : linkId.copy();
        dst.title = title == null ? null : title.copy();
        dst.text = text == null ? null : text.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.group = new ArrayList<GroupComponent>();
        for (GroupComponent i : group)
          dst.group.add(i.copy());
        dst.question = new ArrayList<QuestionComponent>();
        for (QuestionComponent i : question)
          dst.question.add(i.copy());
        return dst;
      }

  }

    public static class QuestionComponent extends BackboneElement {
        /**
         * Identifies the question from the Questionnaire that corresponds to this question in the QuestionnaireAnswers resource.
         */
        protected StringType linkId;

        /**
         * Text of the question as it is shown to the user.
         */
        protected StringType text;

        /**
         * The respondent's answer(s) to the question.
         */
        protected List<QuestionAnswerComponent> answer = new ArrayList<QuestionAnswerComponent>();

        /**
         * Nested group, containing nested question for this question. The order of groups within the question is relevant.
         */
        protected List<GroupComponent> group = new ArrayList<GroupComponent>();

        private static final long serialVersionUID = -296697363L;

      public QuestionComponent() {
        super();
      }

        /**
         * @return {@link #linkId} (Identifies the question from the Questionnaire that corresponds to this question in the QuestionnaireAnswers resource.)
         */
        public StringType getLinkId() { 
          return this.linkId;
        }

        /**
         * @param value {@link #linkId} (Identifies the question from the Questionnaire that corresponds to this question in the QuestionnaireAnswers resource.)
         */
        public QuestionComponent setLinkId(StringType value) { 
          this.linkId = value;
          return this;
        }

        /**
         * @return Identifies the question from the Questionnaire that corresponds to this question in the QuestionnaireAnswers resource.
         */
        public String getLinkIdSimple() { 
          return this.linkId == null ? null : this.linkId.getValue();
        }

        /**
         * @param value Identifies the question from the Questionnaire that corresponds to this question in the QuestionnaireAnswers resource.
         */
        public QuestionComponent setLinkIdSimple(String value) { 
          if (value == null)
            this.linkId = null;
          else {
            if (this.linkId == null)
              this.linkId = new StringType();
            this.linkId.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #text} (Text of the question as it is shown to the user.)
         */
        public StringType getText() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Text of the question as it is shown to the user.)
         */
        public QuestionComponent setText(StringType value) { 
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
              this.text = new StringType();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #answer} (The respondent's answer(s) to the question.)
         */
        public List<QuestionAnswerComponent> getAnswer() { 
          return this.answer;
        }

    // syntactic sugar
        /**
         * @return {@link #answer} (The respondent's answer(s) to the question.)
         */
        public QuestionAnswerComponent addAnswer() { 
          QuestionAnswerComponent t = new QuestionAnswerComponent();
          this.answer.add(t);
          return t;
        }

        /**
         * @return {@link #group} (Nested group, containing nested question for this question. The order of groups within the question is relevant.)
         */
        public List<GroupComponent> getGroup() { 
          return this.group;
        }

    // syntactic sugar
        /**
         * @return {@link #group} (Nested group, containing nested question for this question. The order of groups within the question is relevant.)
         */
        public GroupComponent addGroup() { 
          GroupComponent t = new GroupComponent();
          this.group.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("linkId", "string", "Identifies the question from the Questionnaire that corresponds to this question in the QuestionnaireAnswers resource.", 0, java.lang.Integer.MAX_VALUE, linkId));
          childrenList.add(new Property("text", "string", "Text of the question as it is shown to the user.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("answer", "", "The respondent's answer(s) to the question.", 0, java.lang.Integer.MAX_VALUE, answer));
          childrenList.add(new Property("group", "@QuestionnaireAnswers.group", "Nested group, containing nested question for this question. The order of groups within the question is relevant.", 0, java.lang.Integer.MAX_VALUE, group));
        }

      public QuestionComponent copy() {
        QuestionComponent dst = new QuestionComponent();
        dst.linkId = linkId == null ? null : linkId.copy();
        dst.text = text == null ? null : text.copy();
        dst.answer = new ArrayList<QuestionAnswerComponent>();
        for (QuestionAnswerComponent i : answer)
          dst.answer.add(i.copy());
        dst.group = new ArrayList<GroupComponent>();
        for (GroupComponent i : group)
          dst.group.add(i.copy());
        return dst;
      }

  }

    public static class QuestionAnswerComponent extends BackboneElement {
        /**
         * Single-valued answer to the question.
         */
        protected Type value;

        private static final long serialVersionUID = -732981989L;

      public QuestionAnswerComponent() {
        super();
      }

        /**
         * @return {@link #value} (Single-valued answer to the question.)
         */
        public Type getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (Single-valued answer to the question.)
         */
        public QuestionAnswerComponent setValue(Type value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("value[x]", "boolean|decimal|integer|date|dateTime|instant|time|string|Attachment|Coding|Quantity", "Single-valued answer to the question.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      public QuestionAnswerComponent copy() {
        QuestionAnswerComponent dst = new QuestionAnswerComponent();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    /**
     * A business identifier assigned to a particular completed (or partially completed) questionnaire.
     */
    protected Identifier identifier;

    /**
     * Indicates the Questionnaire resource that defines the form for which answers are being provided.
     */
    protected ResourceReference questionnaire;

    /**
     * The actual object that is the target of the reference (Indicates the Questionnaire resource that defines the form for which answers are being provided.)
     */
    protected Questionnaire questionnaireTarget;

    /**
     * The lifecycle status of the questionnaire answers as a whole.
     */
    protected Enumeration<QuestionnaireAnswersStatus> status;

    /**
     * The subject of the questionnaire answers.  This could be a patient, organization, practitioner, device, etc.  This is who/what the answers apply to, but is not necessarily the source of information.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The subject of the questionnaire answers.  This could be a patient, organization, practitioner, device, etc.  This is who/what the answers apply to, but is not necessarily the source of information.)
     */
    protected Resource subjectTarget;

    /**
     * Person who received the answers to the questions in the QuestionnaireAnswers and recorded them in the system.
     */
    protected ResourceReference author;

    /**
     * The actual object that is the target of the reference (Person who received the answers to the questions in the QuestionnaireAnswers and recorded them in the system.)
     */
    protected Resource authorTarget;

    /**
     * The date and/or time that this version of the questionnaire answers was authored.
     */
    protected DateTimeType authored;

    /**
     * The person who answered the questions about the subject. Only used when this is not the subject him/herself.
     */
    protected ResourceReference source;

    /**
     * The actual object that is the target of the reference (The person who answered the questions about the subject. Only used when this is not the subject him/herself.)
     */
    protected Resource sourceTarget;

    /**
     * Encounter during which this set of questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.
     */
    protected ResourceReference encounter;

    /**
     * The actual object that is the target of the reference (Encounter during which this set of questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.)
     */
    protected Encounter encounterTarget;

    /**
     * A group of questions to a possibly similarly grouped set of questions in the questionnaire answers.
     */
    protected GroupComponent group;

    private static final long serialVersionUID = 1656433257L;

    public QuestionnaireAnswers() {
      super();
    }

    public QuestionnaireAnswers(Enumeration<QuestionnaireAnswersStatus> status, DateTimeType authored) {
      super();
      this.status = status;
      this.authored = authored;
    }

    /**
     * @return {@link #identifier} (A business identifier assigned to a particular completed (or partially completed) questionnaire.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (A business identifier assigned to a particular completed (or partially completed) questionnaire.)
     */
    public QuestionnaireAnswers setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #questionnaire} (Indicates the Questionnaire resource that defines the form for which answers are being provided.)
     */
    public ResourceReference getQuestionnaire() { 
      return this.questionnaire;
    }

    /**
     * @param value {@link #questionnaire} (Indicates the Questionnaire resource that defines the form for which answers are being provided.)
     */
    public QuestionnaireAnswers setQuestionnaire(ResourceReference value) { 
      this.questionnaire = value;
      return this;
    }

    /**
     * @return {@link #questionnaire} (The actual object that is the target of the reference. Indicates the Questionnaire resource that defines the form for which answers are being provided.)
     */
    public Questionnaire getQuestionnaireTarget() { 
      return this.questionnaireTarget;
    }

    /**
     * @param value {@link #questionnaire} (The actual object that is the target of the reference. Indicates the Questionnaire resource that defines the form for which answers are being provided.)
     */
    public QuestionnaireAnswers setQuestionnaireTarget(Questionnaire value) { 
      this.questionnaireTarget = value;
      return this;
    }

    /**
     * @return {@link #status} (The lifecycle status of the questionnaire answers as a whole.)
     */
    public Enumeration<QuestionnaireAnswersStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The lifecycle status of the questionnaire answers as a whole.)
     */
    public QuestionnaireAnswers setStatus(Enumeration<QuestionnaireAnswersStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The lifecycle status of the questionnaire answers as a whole.
     */
    public QuestionnaireAnswersStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The lifecycle status of the questionnaire answers as a whole.
     */
    public QuestionnaireAnswers setStatusSimple(QuestionnaireAnswersStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<QuestionnaireAnswersStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #subject} (The subject of the questionnaire answers.  This could be a patient, organization, practitioner, device, etc.  This is who/what the answers apply to, but is not necessarily the source of information.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The subject of the questionnaire answers.  This could be a patient, organization, practitioner, device, etc.  This is who/what the answers apply to, but is not necessarily the source of information.)
     */
    public QuestionnaireAnswers setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. The subject of the questionnaire answers.  This could be a patient, organization, practitioner, device, etc.  This is who/what the answers apply to, but is not necessarily the source of information.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The subject of the questionnaire answers.  This could be a patient, organization, practitioner, device, etc.  This is who/what the answers apply to, but is not necessarily the source of information.)
     */
    public QuestionnaireAnswers setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #author} (Person who received the answers to the questions in the QuestionnaireAnswers and recorded them in the system.)
     */
    public ResourceReference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (Person who received the answers to the questions in the QuestionnaireAnswers and recorded them in the system.)
     */
    public QuestionnaireAnswers setAuthor(ResourceReference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #author} (The actual object that is the target of the reference. Person who received the answers to the questions in the QuestionnaireAnswers and recorded them in the system.)
     */
    public Resource getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @param value {@link #author} (The actual object that is the target of the reference. Person who received the answers to the questions in the QuestionnaireAnswers and recorded them in the system.)
     */
    public QuestionnaireAnswers setAuthorTarget(Resource value) { 
      this.authorTarget = value;
      return this;
    }

    /**
     * @return {@link #authored} (The date and/or time that this version of the questionnaire answers was authored.)
     */
    public DateTimeType getAuthored() { 
      return this.authored;
    }

    /**
     * @param value {@link #authored} (The date and/or time that this version of the questionnaire answers was authored.)
     */
    public QuestionnaireAnswers setAuthored(DateTimeType value) { 
      this.authored = value;
      return this;
    }

    /**
     * @return The date and/or time that this version of the questionnaire answers was authored.
     */
    public DateAndTime getAuthoredSimple() { 
      return this.authored == null ? null : this.authored.getValue();
    }

    /**
     * @param value The date and/or time that this version of the questionnaire answers was authored.
     */
    public QuestionnaireAnswers setAuthoredSimple(DateAndTime value) { 
        if (this.authored == null)
          this.authored = new DateTimeType();
        this.authored.setValue(value);
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
    public QuestionnaireAnswers setSource(ResourceReference value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #source} (The actual object that is the target of the reference. The person who answered the questions about the subject. Only used when this is not the subject him/herself.)
     */
    public Resource getSourceTarget() { 
      return this.sourceTarget;
    }

    /**
     * @param value {@link #source} (The actual object that is the target of the reference. The person who answered the questions about the subject. Only used when this is not the subject him/herself.)
     */
    public QuestionnaireAnswers setSourceTarget(Resource value) { 
      this.sourceTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (Encounter during which this set of questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (Encounter during which this set of questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.)
     */
    public QuestionnaireAnswers setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The actual object that is the target of the reference. Encounter during which this set of questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} (The actual object that is the target of the reference. Encounter during which this set of questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.)
     */
    public QuestionnaireAnswers setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #group} (A group of questions to a possibly similarly grouped set of questions in the questionnaire answers.)
     */
    public GroupComponent getGroup() { 
      return this.group;
    }

    /**
     * @param value {@link #group} (A group of questions to a possibly similarly grouped set of questions in the questionnaire answers.)
     */
    public QuestionnaireAnswers setGroup(GroupComponent value) { 
      this.group = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "A business identifier assigned to a particular completed (or partially completed) questionnaire.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("questionnaire", "Resource(Questionnaire)", "Indicates the Questionnaire resource that defines the form for which answers are being provided.", 0, java.lang.Integer.MAX_VALUE, questionnaire));
        childrenList.add(new Property("status", "code", "The lifecycle status of the questionnaire answers as a whole.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("subject", "Resource(Any)", "The subject of the questionnaire answers.  This could be a patient, organization, practitioner, device, etc.  This is who/what the answers apply to, but is not necessarily the source of information.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("author", "Resource(Practitioner|Patient|RelatedPerson)", "Person who received the answers to the questions in the QuestionnaireAnswers and recorded them in the system.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("authored", "dateTime", "The date and/or time that this version of the questionnaire answers was authored.", 0, java.lang.Integer.MAX_VALUE, authored));
        childrenList.add(new Property("source", "Resource(Patient|Practitioner|RelatedPerson)", "The person who answered the questions about the subject. Only used when this is not the subject him/herself.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "Encounter during which this set of questionnaire answers were collected. When there were multiple encounters, this is the one considered most relevant to the context of the answers.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("group", "", "A group of questions to a possibly similarly grouped set of questions in the questionnaire answers.", 0, java.lang.Integer.MAX_VALUE, group));
      }

      public QuestionnaireAnswers copy() {
        QuestionnaireAnswers dst = new QuestionnaireAnswers();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.questionnaire = questionnaire == null ? null : questionnaire.copy();
        dst.status = status == null ? null : status.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.author = author == null ? null : author.copy();
        dst.authored = authored == null ? null : authored.copy();
        dst.source = source == null ? null : source.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.group = group == null ? null : group.copy();
        return dst;
      }

      protected QuestionnaireAnswers typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.QuestionnaireAnswers;
   }


}

