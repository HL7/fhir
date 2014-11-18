package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011+, HL7, Inc.
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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * A structured set of questions intended to guide the collection of answers. The questions are ordered and grouped into coherent subsets, corresponding to the structure of the grouping of the underlying questions.
 */
public class Questionnaire extends DomainResource {

    public enum QuestionnaireStatus {
        DRAFT, // This Questionnaire is not ready for official use.
        PUBLISHED, // This Questionnaire is ready for use.
        RETIRED, // This Questionnaire should no longer be used to gather data.
        NULL; // added to help the parsers
        public static QuestionnaireStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("published".equals(codeString))
          return PUBLISHED;
        if ("retired".equals(codeString))
          return RETIRED;
        throw new Exception("Unknown QuestionnaireStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DRAFT: return "draft";
            case PUBLISHED: return "published";
            case RETIRED: return "retired";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DRAFT: return "This Questionnaire is not ready for official use.";
            case PUBLISHED: return "This Questionnaire is ready for use.";
            case RETIRED: return "This Questionnaire should no longer be used to gather data.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "draft";
            case PUBLISHED: return "published";
            case RETIRED: return "retired";
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
          return QuestionnaireStatus.DRAFT;
        if ("published".equals(codeString))
          return QuestionnaireStatus.PUBLISHED;
        if ("retired".equals(codeString))
          return QuestionnaireStatus.RETIRED;
        throw new Exception("Unknown QuestionnaireStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == QuestionnaireStatus.DRAFT)
        return "draft";
      if (code == QuestionnaireStatus.PUBLISHED)
        return "published";
      if (code == QuestionnaireStatus.RETIRED)
        return "retired";
      return "?";
      }
    }

    public enum AnswerFormat {
        BOOLEAN, // Answer is a yes/no answer.
        DECIMAL, // Answer is a floating point number.
        INTEGER, // Answer is an integer.
        DATE, // Answer is a date.
        DATETIME, // Answer is a date and time.
        INSTANT, // Answer is a system timestamp.
        TIME, // Answer is a time independent of date.
        STRING, // Answer is a short (few words to short sentence) free-text entry.
        TEXT, // Answer is a long (potentially multi-paragram) free-text entry.
        CHOICE, // Answer is a choice from a list of options.
        OPENCHOICE, // Answer is a choice from a list of options or a free-text entry.
        ATTACHMENT, // Answer is binary content such as a image, PDF, etc.
        REFERENCE, // Answer is a reference to another resource (practitioner, organization, etc.).
        QUANTITY, // Answer is a combination of a numeric value and unit.
        NULL; // added to help the parsers
        public static AnswerFormat fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("boolean".equals(codeString))
          return BOOLEAN;
        if ("decimal".equals(codeString))
          return DECIMAL;
        if ("integer".equals(codeString))
          return INTEGER;
        if ("date".equals(codeString))
          return DATE;
        if ("dateTime".equals(codeString))
          return DATETIME;
        if ("instant".equals(codeString))
          return INSTANT;
        if ("time".equals(codeString))
          return TIME;
        if ("string".equals(codeString))
          return STRING;
        if ("text".equals(codeString))
          return TEXT;
        if ("choice".equals(codeString))
          return CHOICE;
        if ("open-choice".equals(codeString))
          return OPENCHOICE;
        if ("attachment".equals(codeString))
          return ATTACHMENT;
        if ("reference".equals(codeString))
          return REFERENCE;
        if ("quantity".equals(codeString))
          return QUANTITY;
        throw new Exception("Unknown AnswerFormat code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case BOOLEAN: return "boolean";
            case DECIMAL: return "decimal";
            case INTEGER: return "integer";
            case DATE: return "date";
            case DATETIME: return "dateTime";
            case INSTANT: return "instant";
            case TIME: return "time";
            case STRING: return "string";
            case TEXT: return "text";
            case CHOICE: return "choice";
            case OPENCHOICE: return "open-choice";
            case ATTACHMENT: return "attachment";
            case REFERENCE: return "reference";
            case QUANTITY: return "quantity";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case BOOLEAN: return "Answer is a yes/no answer.";
            case DECIMAL: return "Answer is a floating point number.";
            case INTEGER: return "Answer is an integer.";
            case DATE: return "Answer is a date.";
            case DATETIME: return "Answer is a date and time.";
            case INSTANT: return "Answer is a system timestamp.";
            case TIME: return "Answer is a time independent of date.";
            case STRING: return "Answer is a short (few words to short sentence) free-text entry.";
            case TEXT: return "Answer is a long (potentially multi-paragram) free-text entry.";
            case CHOICE: return "Answer is a choice from a list of options.";
            case OPENCHOICE: return "Answer is a choice from a list of options or a free-text entry.";
            case ATTACHMENT: return "Answer is binary content such as a image, PDF, etc.";
            case REFERENCE: return "Answer is a reference to another resource (practitioner, organization, etc.).";
            case QUANTITY: return "Answer is a combination of a numeric value and unit.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case BOOLEAN: return "boolean";
            case DECIMAL: return "decimal";
            case INTEGER: return "integer";
            case DATE: return "date";
            case DATETIME: return "dateTime";
            case INSTANT: return "instant";
            case TIME: return "time";
            case STRING: return "string";
            case TEXT: return "text";
            case CHOICE: return "choice";
            case OPENCHOICE: return "open-choice";
            case ATTACHMENT: return "attachment";
            case REFERENCE: return "reference";
            case QUANTITY: return "quantity";
            default: return "?";
          }
        }
    }

  public static class AnswerFormatEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("boolean".equals(codeString))
          return AnswerFormat.BOOLEAN;
        if ("decimal".equals(codeString))
          return AnswerFormat.DECIMAL;
        if ("integer".equals(codeString))
          return AnswerFormat.INTEGER;
        if ("date".equals(codeString))
          return AnswerFormat.DATE;
        if ("dateTime".equals(codeString))
          return AnswerFormat.DATETIME;
        if ("instant".equals(codeString))
          return AnswerFormat.INSTANT;
        if ("time".equals(codeString))
          return AnswerFormat.TIME;
        if ("string".equals(codeString))
          return AnswerFormat.STRING;
        if ("text".equals(codeString))
          return AnswerFormat.TEXT;
        if ("choice".equals(codeString))
          return AnswerFormat.CHOICE;
        if ("open-choice".equals(codeString))
          return AnswerFormat.OPENCHOICE;
        if ("attachment".equals(codeString))
          return AnswerFormat.ATTACHMENT;
        if ("reference".equals(codeString))
          return AnswerFormat.REFERENCE;
        if ("quantity".equals(codeString))
          return AnswerFormat.QUANTITY;
        throw new Exception("Unknown AnswerFormat code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AnswerFormat.BOOLEAN)
        return "boolean";
      if (code == AnswerFormat.DECIMAL)
        return "decimal";
      if (code == AnswerFormat.INTEGER)
        return "integer";
      if (code == AnswerFormat.DATE)
        return "date";
      if (code == AnswerFormat.DATETIME)
        return "dateTime";
      if (code == AnswerFormat.INSTANT)
        return "instant";
      if (code == AnswerFormat.TIME)
        return "time";
      if (code == AnswerFormat.STRING)
        return "string";
      if (code == AnswerFormat.TEXT)
        return "text";
      if (code == AnswerFormat.CHOICE)
        return "choice";
      if (code == AnswerFormat.OPENCHOICE)
        return "open-choice";
      if (code == AnswerFormat.ATTACHMENT)
        return "attachment";
      if (code == AnswerFormat.REFERENCE)
        return "reference";
      if (code == AnswerFormat.QUANTITY)
        return "quantity";
      return "?";
      }
    }

    public static class GroupComponent extends BackboneElement {
        /**
         * A identifier that is unique within the questionnaire allowing linkage to the equivalent group in a QuestionnaireAnswers resource.
         */
        protected StringType linkId;

        /**
         * The human-readable name for this section of the questionnaire.
         */
        protected StringType title;

        /**
         * Identifies a how this group of questions is known in a particular terminology such as LOINC.
         */
        protected List<Coding> concept = new ArrayList<Coding>();

        /**
         * Additional text for the group, used for display purposes.
         */
        protected StringType text;

        /**
         * If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.
         */
        protected BooleanType required;

        /**
         * Whether the group may occur multiple times in the instance, containing multiple sets of answers.
         */
        protected BooleanType repeats;

        /**
         * A sub-group within a group. The ordering of groups within this group is relevant.
         */
        protected List<GroupComponent> group = new ArrayList<GroupComponent>();

        /**
         * Set of questions within this group. The order of questions within the group is relevant.
         */
        protected List<QuestionComponent> question = new ArrayList<QuestionComponent>();

        private static final long serialVersionUID = 427521870L;

      public GroupComponent() {
        super();
      }

        /**
         * @return {@link #linkId} (A identifier that is unique within the questionnaire allowing linkage to the equivalent group in a QuestionnaireAnswers resource.). This is the underlying object with id, value and extensions. The accessor "getLinkId" gives direct access to the value
         */
        public StringType getLinkIdElement() { 
          return this.linkId;
        }

        /**
         * @param value {@link #linkId} (A identifier that is unique within the questionnaire allowing linkage to the equivalent group in a QuestionnaireAnswers resource.). This is the underlying object with id, value and extensions. The accessor "getLinkId" gives direct access to the value
         */
        public GroupComponent setLinkIdElement(StringType value) { 
          this.linkId = value;
          return this;
        }

        /**
         * @return A identifier that is unique within the questionnaire allowing linkage to the equivalent group in a QuestionnaireAnswers resource.
         */
        public String getLinkId() { 
          return this.linkId == null ? null : this.linkId.getValue();
        }

        /**
         * @param value A identifier that is unique within the questionnaire allowing linkage to the equivalent group in a QuestionnaireAnswers resource.
         */
        public GroupComponent setLinkId(String value) { 
          if (Utilities.noString(value))
            this.linkId = null;
          else {
            if (this.linkId == null)
              this.linkId = new StringType();
            this.linkId.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #title} (The human-readable name for this section of the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
         */
        public StringType getTitleElement() { 
          return this.title;
        }

        /**
         * @param value {@link #title} (The human-readable name for this section of the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
         */
        public GroupComponent setTitleElement(StringType value) { 
          this.title = value;
          return this;
        }

        /**
         * @return The human-readable name for this section of the questionnaire.
         */
        public String getTitle() { 
          return this.title == null ? null : this.title.getValue();
        }

        /**
         * @param value The human-readable name for this section of the questionnaire.
         */
        public GroupComponent setTitle(String value) { 
          if (Utilities.noString(value))
            this.title = null;
          else {
            if (this.title == null)
              this.title = new StringType();
            this.title.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #concept} (Identifies a how this group of questions is known in a particular terminology such as LOINC.)
         */
        public List<Coding> getConcept() { 
          return this.concept;
        }

        /**
         * @return {@link #concept} (Identifies a how this group of questions is known in a particular terminology such as LOINC.)
         */
    // syntactic sugar
        public Coding addConcept() { //3
          Coding t = new Coding();
          this.concept.add(t);
          return t;
        }

        /**
         * @return {@link #text} (Additional text for the group, used for display purposes.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public StringType getTextElement() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Additional text for the group, used for display purposes.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public GroupComponent setTextElement(StringType value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Additional text for the group, used for display purposes.
         */
        public String getText() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Additional text for the group, used for display purposes.
         */
        public GroupComponent setText(String value) { 
          if (Utilities.noString(value))
            this.text = null;
          else {
            if (this.text == null)
              this.text = new StringType();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #required} (If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getRequired" gives direct access to the value
         */
        public BooleanType getRequiredElement() { 
          return this.required;
        }

        /**
         * @param value {@link #required} (If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getRequired" gives direct access to the value
         */
        public GroupComponent setRequiredElement(BooleanType value) { 
          this.required = value;
          return this;
        }

        /**
         * @return If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.
         */
        public boolean getRequired() { 
          return this.required == null ? false : this.required.getValue();
        }

        /**
         * @param value If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.
         */
        public GroupComponent setRequired(boolean value) { 
          if (value == false)
            this.required = null;
          else {
            if (this.required == null)
              this.required = new BooleanType();
            this.required.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #repeats} (Whether the group may occur multiple times in the instance, containing multiple sets of answers.). This is the underlying object with id, value and extensions. The accessor "getRepeats" gives direct access to the value
         */
        public BooleanType getRepeatsElement() { 
          return this.repeats;
        }

        /**
         * @param value {@link #repeats} (Whether the group may occur multiple times in the instance, containing multiple sets of answers.). This is the underlying object with id, value and extensions. The accessor "getRepeats" gives direct access to the value
         */
        public GroupComponent setRepeatsElement(BooleanType value) { 
          this.repeats = value;
          return this;
        }

        /**
         * @return Whether the group may occur multiple times in the instance, containing multiple sets of answers.
         */
        public boolean getRepeats() { 
          return this.repeats == null ? false : this.repeats.getValue();
        }

        /**
         * @param value Whether the group may occur multiple times in the instance, containing multiple sets of answers.
         */
        public GroupComponent setRepeats(boolean value) { 
          if (value == false)
            this.repeats = null;
          else {
            if (this.repeats == null)
              this.repeats = new BooleanType();
            this.repeats.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #group} (A sub-group within a group. The ordering of groups within this group is relevant.)
         */
        public List<GroupComponent> getGroup() { 
          return this.group;
        }

        /**
         * @return {@link #group} (A sub-group within a group. The ordering of groups within this group is relevant.)
         */
    // syntactic sugar
        public GroupComponent addGroup() { //3
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

        /**
         * @return {@link #question} (Set of questions within this group. The order of questions within the group is relevant.)
         */
    // syntactic sugar
        public QuestionComponent addQuestion() { //3
          QuestionComponent t = new QuestionComponent();
          this.question.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("linkId", "string", "A identifier that is unique within the questionnaire allowing linkage to the equivalent group in a QuestionnaireAnswers resource.", 0, java.lang.Integer.MAX_VALUE, linkId));
          childrenList.add(new Property("title", "string", "The human-readable name for this section of the questionnaire.", 0, java.lang.Integer.MAX_VALUE, title));
          childrenList.add(new Property("concept", "Coding", "Identifies a how this group of questions is known in a particular terminology such as LOINC.", 0, java.lang.Integer.MAX_VALUE, concept));
          childrenList.add(new Property("text", "string", "Additional text for the group, used for display purposes.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("required", "boolean", "If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.", 0, java.lang.Integer.MAX_VALUE, required));
          childrenList.add(new Property("repeats", "boolean", "Whether the group may occur multiple times in the instance, containing multiple sets of answers.", 0, java.lang.Integer.MAX_VALUE, repeats));
          childrenList.add(new Property("group", "@Questionnaire.group", "A sub-group within a group. The ordering of groups within this group is relevant.", 0, java.lang.Integer.MAX_VALUE, group));
          childrenList.add(new Property("question", "", "Set of questions within this group. The order of questions within the group is relevant.", 0, java.lang.Integer.MAX_VALUE, question));
        }

      public GroupComponent copy() {
        GroupComponent dst = new GroupComponent();
        copyValues(dst);
        dst.linkId = linkId == null ? null : linkId.copy();
        dst.title = title == null ? null : title.copy();
        dst.concept = new ArrayList<Coding>();
        for (Coding i : concept)
          dst.concept.add(i.copy());
        dst.text = text == null ? null : text.copy();
        dst.required = required == null ? null : required.copy();
        dst.repeats = repeats == null ? null : repeats.copy();
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
         * An identifier that is unique within the questionnaire allowing linkage to the equivalent group in a [[[QuestionnaireAnswers]]] resource.
         */
        protected StringType linkId;

        /**
         * Identifies a how this group of questions is known in a particular terminology such as LOINC.
         */
        protected List<Coding> concept = new ArrayList<Coding>();

        /**
         * Text of the question as it is shown to the user.
         */
        protected StringType text;

        /**
         * The expected format of the answer, e.g. the type of input (string, integer) or whether a (multiple) choice is expected.
         */
        protected Enumeration<AnswerFormat> type;

        /**
         * If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.
         */
        protected BooleanType required;

        /**
         * Whether the group may occur multiple times in the instance, containing multiple sets of answers.
         */
        protected BooleanType repeats;

        /**
         * Reference to a valueset containing the possible options.
         */
        protected Reference options;

        /**
         * The actual object that is the target of the reference (Reference to a valueset containing the possible options.)
         */
        protected ValueSet optionsTarget;

        /**
         * Nested group, containing nested question for this question. The order of groups within the question is relevant.
         */
        protected List<GroupComponent> group = new ArrayList<GroupComponent>();

        private static final long serialVersionUID = 2101757219L;

      public QuestionComponent() {
        super();
      }

        /**
         * @return {@link #linkId} (An identifier that is unique within the questionnaire allowing linkage to the equivalent group in a [[[QuestionnaireAnswers]]] resource.). This is the underlying object with id, value and extensions. The accessor "getLinkId" gives direct access to the value
         */
        public StringType getLinkIdElement() { 
          return this.linkId;
        }

        /**
         * @param value {@link #linkId} (An identifier that is unique within the questionnaire allowing linkage to the equivalent group in a [[[QuestionnaireAnswers]]] resource.). This is the underlying object with id, value and extensions. The accessor "getLinkId" gives direct access to the value
         */
        public QuestionComponent setLinkIdElement(StringType value) { 
          this.linkId = value;
          return this;
        }

        /**
         * @return An identifier that is unique within the questionnaire allowing linkage to the equivalent group in a [[[QuestionnaireAnswers]]] resource.
         */
        public String getLinkId() { 
          return this.linkId == null ? null : this.linkId.getValue();
        }

        /**
         * @param value An identifier that is unique within the questionnaire allowing linkage to the equivalent group in a [[[QuestionnaireAnswers]]] resource.
         */
        public QuestionComponent setLinkId(String value) { 
          if (Utilities.noString(value))
            this.linkId = null;
          else {
            if (this.linkId == null)
              this.linkId = new StringType();
            this.linkId.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #concept} (Identifies a how this group of questions is known in a particular terminology such as LOINC.)
         */
        public List<Coding> getConcept() { 
          return this.concept;
        }

        /**
         * @return {@link #concept} (Identifies a how this group of questions is known in a particular terminology such as LOINC.)
         */
    // syntactic sugar
        public Coding addConcept() { //3
          Coding t = new Coding();
          this.concept.add(t);
          return t;
        }

        /**
         * @return {@link #text} (Text of the question as it is shown to the user.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public StringType getTextElement() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Text of the question as it is shown to the user.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public QuestionComponent setTextElement(StringType value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Text of the question as it is shown to the user.
         */
        public String getText() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Text of the question as it is shown to the user.
         */
        public QuestionComponent setText(String value) { 
          if (Utilities.noString(value))
            this.text = null;
          else {
            if (this.text == null)
              this.text = new StringType();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The expected format of the answer, e.g. the type of input (string, integer) or whether a (multiple) choice is expected.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<AnswerFormat> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The expected format of the answer, e.g. the type of input (string, integer) or whether a (multiple) choice is expected.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public QuestionComponent setTypeElement(Enumeration<AnswerFormat> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The expected format of the answer, e.g. the type of input (string, integer) or whether a (multiple) choice is expected.
         */
        public AnswerFormat getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The expected format of the answer, e.g. the type of input (string, integer) or whether a (multiple) choice is expected.
         */
        public QuestionComponent setType(AnswerFormat value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<AnswerFormat>();
            this.type.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #required} (If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getRequired" gives direct access to the value
         */
        public BooleanType getRequiredElement() { 
          return this.required;
        }

        /**
         * @param value {@link #required} (If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getRequired" gives direct access to the value
         */
        public QuestionComponent setRequiredElement(BooleanType value) { 
          this.required = value;
          return this;
        }

        /**
         * @return If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.
         */
        public boolean getRequired() { 
          return this.required == null ? false : this.required.getValue();
        }

        /**
         * @param value If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.
         */
        public QuestionComponent setRequired(boolean value) { 
          if (value == false)
            this.required = null;
          else {
            if (this.required == null)
              this.required = new BooleanType();
            this.required.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #repeats} (Whether the group may occur multiple times in the instance, containing multiple sets of answers.). This is the underlying object with id, value and extensions. The accessor "getRepeats" gives direct access to the value
         */
        public BooleanType getRepeatsElement() { 
          return this.repeats;
        }

        /**
         * @param value {@link #repeats} (Whether the group may occur multiple times in the instance, containing multiple sets of answers.). This is the underlying object with id, value and extensions. The accessor "getRepeats" gives direct access to the value
         */
        public QuestionComponent setRepeatsElement(BooleanType value) { 
          this.repeats = value;
          return this;
        }

        /**
         * @return Whether the group may occur multiple times in the instance, containing multiple sets of answers.
         */
        public boolean getRepeats() { 
          return this.repeats == null ? false : this.repeats.getValue();
        }

        /**
         * @param value Whether the group may occur multiple times in the instance, containing multiple sets of answers.
         */
        public QuestionComponent setRepeats(boolean value) { 
          if (value == false)
            this.repeats = null;
          else {
            if (this.repeats == null)
              this.repeats = new BooleanType();
            this.repeats.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #options} (Reference to a valueset containing the possible options.)
         */
        public Reference getOptions() { 
          return this.options;
        }

        /**
         * @param value {@link #options} (Reference to a valueset containing the possible options.)
         */
        public QuestionComponent setOptions(Reference value) { 
          this.options = value;
          return this;
        }

        /**
         * @return {@link #options} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Reference to a valueset containing the possible options.)
         */
        public ValueSet getOptionsTarget() { 
          return this.optionsTarget;
        }

        /**
         * @param value {@link #options} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Reference to a valueset containing the possible options.)
         */
        public QuestionComponent setOptionsTarget(ValueSet value) { 
          this.optionsTarget = value;
          return this;
        }

        /**
         * @return {@link #group} (Nested group, containing nested question for this question. The order of groups within the question is relevant.)
         */
        public List<GroupComponent> getGroup() { 
          return this.group;
        }

        /**
         * @return {@link #group} (Nested group, containing nested question for this question. The order of groups within the question is relevant.)
         */
    // syntactic sugar
        public GroupComponent addGroup() { //3
          GroupComponent t = new GroupComponent();
          this.group.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("linkId", "string", "An identifier that is unique within the questionnaire allowing linkage to the equivalent group in a [[[QuestionnaireAnswers]]] resource.", 0, java.lang.Integer.MAX_VALUE, linkId));
          childrenList.add(new Property("concept", "Coding", "Identifies a how this group of questions is known in a particular terminology such as LOINC.", 0, java.lang.Integer.MAX_VALUE, concept));
          childrenList.add(new Property("text", "string", "Text of the question as it is shown to the user.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("type", "code", "The expected format of the answer, e.g. the type of input (string, integer) or whether a (multiple) choice is expected.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("required", "boolean", "If true, indicates that the group must be present and have required questions within it answered.  If false, the group may be skipped when answering the questionnaire.", 0, java.lang.Integer.MAX_VALUE, required));
          childrenList.add(new Property("repeats", "boolean", "Whether the group may occur multiple times in the instance, containing multiple sets of answers.", 0, java.lang.Integer.MAX_VALUE, repeats));
          childrenList.add(new Property("options", "Reference(ValueSet)", "Reference to a valueset containing the possible options.", 0, java.lang.Integer.MAX_VALUE, options));
          childrenList.add(new Property("group", "@Questionnaire.group", "Nested group, containing nested question for this question. The order of groups within the question is relevant.", 0, java.lang.Integer.MAX_VALUE, group));
        }

      public QuestionComponent copy() {
        QuestionComponent dst = new QuestionComponent();
        copyValues(dst);
        dst.linkId = linkId == null ? null : linkId.copy();
        dst.concept = new ArrayList<Coding>();
        for (Coding i : concept)
          dst.concept.add(i.copy());
        dst.text = text == null ? null : text.copy();
        dst.type = type == null ? null : type.copy();
        dst.required = required == null ? null : required.copy();
        dst.repeats = repeats == null ? null : repeats.copy();
        dst.options = options == null ? null : options.copy();
        dst.group = new ArrayList<GroupComponent>();
        for (GroupComponent i : group)
          dst.group.add(i.copy());
        return dst;
      }

  }

    /**
     * This records identifiers associated with this question set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The version number assigned by the publisher for business reasons.  It may remain the same when the resource is updated.
     */
    protected StringType version;

    /**
     * The lifecycle status of the questionnaire as a whole.
     */
    protected Enumeration<QuestionnaireStatus> status;

    /**
     * The date that this version of the questionnaire was authored.
     */
    protected DateTimeType date;

    /**
     * Organization responsible for developing and maintaining the questionnaire.
     */
    protected StringType publisher;

    /**
     * A collection of related questions (or further groupings of questions).
     */
    protected GroupComponent group;

    private static final long serialVersionUID = -891710678L;

    public Questionnaire() {
      super();
    }

    public Questionnaire(Enumeration<QuestionnaireStatus> status, GroupComponent group) {
      super();
      this.status = status;
      this.group = group;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this question set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this question set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #version} (The version number assigned by the publisher for business reasons.  It may remain the same when the resource is updated.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public StringType getVersionElement() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The version number assigned by the publisher for business reasons.  It may remain the same when the resource is updated.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public Questionnaire setVersionElement(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The version number assigned by the publisher for business reasons.  It may remain the same when the resource is updated.
     */
    public String getVersion() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The version number assigned by the publisher for business reasons.  It may remain the same when the resource is updated.
     */
    public Questionnaire setVersion(String value) { 
      if (Utilities.noString(value))
        this.version = null;
      else {
        if (this.version == null)
          this.version = new StringType();
        this.version.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The lifecycle status of the questionnaire as a whole.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<QuestionnaireStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The lifecycle status of the questionnaire as a whole.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Questionnaire setStatusElement(Enumeration<QuestionnaireStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The lifecycle status of the questionnaire as a whole.
     */
    public QuestionnaireStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The lifecycle status of the questionnaire as a whole.
     */
    public Questionnaire setStatus(QuestionnaireStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<QuestionnaireStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #date} (The date that this version of the questionnaire was authored.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that this version of the questionnaire was authored.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public Questionnaire setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that this version of the questionnaire was authored.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that this version of the questionnaire was authored.
     */
    public Questionnaire setDate(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTimeType();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #publisher} (Organization responsible for developing and maintaining the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public StringType getPublisherElement() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Organization responsible for developing and maintaining the questionnaire.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public Questionnaire setPublisherElement(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return Organization responsible for developing and maintaining the questionnaire.
     */
    public String getPublisher() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value Organization responsible for developing and maintaining the questionnaire.
     */
    public Questionnaire setPublisher(String value) { 
      if (Utilities.noString(value))
        this.publisher = null;
      else {
        if (this.publisher == null)
          this.publisher = new StringType();
        this.publisher.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #group} (A collection of related questions (or further groupings of questions).)
     */
    public GroupComponent getGroup() { 
      return this.group;
    }

    /**
     * @param value {@link #group} (A collection of related questions (or further groupings of questions).)
     */
    public Questionnaire setGroup(GroupComponent value) { 
      this.group = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this question set that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The version number assigned by the publisher for business reasons.  It may remain the same when the resource is updated.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("status", "code", "The lifecycle status of the questionnaire as a whole.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("date", "dateTime", "The date that this version of the questionnaire was authored.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("publisher", "string", "Organization responsible for developing and maintaining the questionnaire.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("group", "", "A collection of related questions (or further groupings of questions).", 0, java.lang.Integer.MAX_VALUE, group));
      }

      public Questionnaire copy() {
        Questionnaire dst = new Questionnaire();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.version = version == null ? null : version.copy();
        dst.status = status == null ? null : status.copy();
        dst.date = date == null ? null : date.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.group = group == null ? null : group.copy();
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

