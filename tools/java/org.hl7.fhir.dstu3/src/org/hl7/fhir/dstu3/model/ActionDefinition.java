package org.hl7.fhir.dstu3.model;

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

// Generated on Fri, Mar 18, 2016 09:23+1100 for FHIR v1.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.DatatypeDef;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
/**
 * The definition of the actions that should be returned by evaluation of the artifact.
 */
@DatatypeDef(name="ActionDefinition")
public class ActionDefinition extends Type implements ICompositeType {

    public enum ParticipantType {
        /**
         * The participant is the patient under evaluation
         */
        PATIENT, 
        /**
         * The participant is a practitioner involved in the patient's care
         */
        PRACTITIONER, 
        /**
         * The participant is a person related to the patient
         */
        RELATEDPERSON, 
        /**
         * added to help the parsers
         */
        NULL;
        public static ParticipantType fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("patient".equals(codeString))
          return PATIENT;
        if ("practitioner".equals(codeString))
          return PRACTITIONER;
        if ("related-person".equals(codeString))
          return RELATEDPERSON;
        throw new FHIRException("Unknown ParticipantType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PATIENT: return "patient";
            case PRACTITIONER: return "practitioner";
            case RELATEDPERSON: return "related-person";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case PATIENT: return "http://hl7.org/fhir/action-participant-type";
            case PRACTITIONER: return "http://hl7.org/fhir/action-participant-type";
            case RELATEDPERSON: return "http://hl7.org/fhir/action-participant-type";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PATIENT: return "The participant is the patient under evaluation";
            case PRACTITIONER: return "The participant is a practitioner involved in the patient's care";
            case RELATEDPERSON: return "The participant is a person related to the patient";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PATIENT: return "Patient";
            case PRACTITIONER: return "Practitioner";
            case RELATEDPERSON: return "Related Person";
            default: return "?";
          }
        }
    }

  public static class ParticipantTypeEnumFactory implements EnumFactory<ParticipantType> {
    public ParticipantType fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("patient".equals(codeString))
          return ParticipantType.PATIENT;
        if ("practitioner".equals(codeString))
          return ParticipantType.PRACTITIONER;
        if ("related-person".equals(codeString))
          return ParticipantType.RELATEDPERSON;
        throw new IllegalArgumentException("Unknown ParticipantType code '"+codeString+"'");
        }
        public Enumeration<ParticipantType> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("patient".equals(codeString))
          return new Enumeration<ParticipantType>(this, ParticipantType.PATIENT);
        if ("practitioner".equals(codeString))
          return new Enumeration<ParticipantType>(this, ParticipantType.PRACTITIONER);
        if ("related-person".equals(codeString))
          return new Enumeration<ParticipantType>(this, ParticipantType.RELATEDPERSON);
        throw new FHIRException("Unknown ParticipantType code '"+codeString+"'");
        }
    public String toCode(ParticipantType code) {
      if (code == ParticipantType.PATIENT)
        return "patient";
      if (code == ParticipantType.PRACTITIONER)
        return "practitioner";
      if (code == ParticipantType.RELATEDPERSON)
        return "related-person";
      return "?";
      }
    public String toSystem(ParticipantType code) {
      return code.getSystem();
      }
    }

    public enum ActionType {
        /**
         * The action is to create a new resource
         */
        CREATE, 
        /**
         * The action is to update an existing resource
         */
        UPDATE, 
        /**
         * The action is to remove an existing resource
         */
        REMOVE, 
        /**
         * The action is to fire a specific event
         */
        FIREEVENT, 
        /**
         * added to help the parsers
         */
        NULL;
        public static ActionType fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("create".equals(codeString))
          return CREATE;
        if ("update".equals(codeString))
          return UPDATE;
        if ("remove".equals(codeString))
          return REMOVE;
        if ("fire-event".equals(codeString))
          return FIREEVENT;
        throw new FHIRException("Unknown ActionType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case CREATE: return "create";
            case UPDATE: return "update";
            case REMOVE: return "remove";
            case FIREEVENT: return "fire-event";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case CREATE: return "http://hl7.org/fhir/action-type";
            case UPDATE: return "http://hl7.org/fhir/action-type";
            case REMOVE: return "http://hl7.org/fhir/action-type";
            case FIREEVENT: return "http://hl7.org/fhir/action-type";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case CREATE: return "The action is to create a new resource";
            case UPDATE: return "The action is to update an existing resource";
            case REMOVE: return "The action is to remove an existing resource";
            case FIREEVENT: return "The action is to fire a specific event";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case CREATE: return "Create";
            case UPDATE: return "Update";
            case REMOVE: return "Remove";
            case FIREEVENT: return "Fire Event";
            default: return "?";
          }
        }
    }

  public static class ActionTypeEnumFactory implements EnumFactory<ActionType> {
    public ActionType fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("create".equals(codeString))
          return ActionType.CREATE;
        if ("update".equals(codeString))
          return ActionType.UPDATE;
        if ("remove".equals(codeString))
          return ActionType.REMOVE;
        if ("fire-event".equals(codeString))
          return ActionType.FIREEVENT;
        throw new IllegalArgumentException("Unknown ActionType code '"+codeString+"'");
        }
        public Enumeration<ActionType> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("create".equals(codeString))
          return new Enumeration<ActionType>(this, ActionType.CREATE);
        if ("update".equals(codeString))
          return new Enumeration<ActionType>(this, ActionType.UPDATE);
        if ("remove".equals(codeString))
          return new Enumeration<ActionType>(this, ActionType.REMOVE);
        if ("fire-event".equals(codeString))
          return new Enumeration<ActionType>(this, ActionType.FIREEVENT);
        throw new FHIRException("Unknown ActionType code '"+codeString+"'");
        }
    public String toCode(ActionType code) {
      if (code == ActionType.CREATE)
        return "create";
      if (code == ActionType.UPDATE)
        return "update";
      if (code == ActionType.REMOVE)
        return "remove";
      if (code == ActionType.FIREEVENT)
        return "fire-event";
      return "?";
      }
    public String toSystem(ActionType code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class ActionDefinitionBehaviorComponent extends Element implements IBaseDatatypeElement {
        /**
         * The type of the behavior to be described, such as grouping, visual, or selection behaviors.
         */
        @Child(name = "type", type = {Coding.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="", formalDefinition="The type of the behavior to be described, such as grouping, visual, or selection behaviors." )
        protected Coding type;

        /**
         * The specific behavior. The code used here is determined by the type of behavior being described. For example, the grouping behavior uses the grouping-behavior-type valueset.
         */
        @Child(name = "value", type = {Coding.class}, order=2, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="", formalDefinition="The specific behavior. The code used here is determined by the type of behavior being described. For example, the grouping behavior uses the grouping-behavior-type valueset." )
        protected Coding value;

        private static final long serialVersionUID = -1054119695L;

    /**
     * Constructor
     */
      public ActionDefinitionBehaviorComponent() {
        super();
      }

    /**
     * Constructor
     */
      public ActionDefinitionBehaviorComponent(Coding type, Coding value) {
        super();
        this.type = type;
        this.value = value;
      }

        /**
         * @return {@link #type} (The type of the behavior to be described, such as grouping, visual, or selection behaviors.)
         */
        public Coding getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create ActionDefinitionBehaviorComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new Coding(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (The type of the behavior to be described, such as grouping, visual, or selection behaviors.)
         */
        public ActionDefinitionBehaviorComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #value} (The specific behavior. The code used here is determined by the type of behavior being described. For example, the grouping behavior uses the grouping-behavior-type valueset.)
         */
        public Coding getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create ActionDefinitionBehaviorComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new Coding(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (The specific behavior. The code used here is determined by the type of behavior being described. For example, the grouping behavior uses the grouping-behavior-type valueset.)
         */
        public ActionDefinitionBehaviorComponent setValue(Coding value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "Coding", "The type of the behavior to be described, such as grouping, visual, or selection behaviors.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("value", "Coding", "The specific behavior. The code used here is determined by the type of behavior being described. For example, the grouping behavior uses the grouping-behavior-type valueset.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type"))
          this.type = castToCoding(value); // Coding
        else if (name.equals("value"))
          this.value = castToCoding(value); // Coding
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          this.type = new Coding();
          return this.type;
        }
        else if (name.equals("value")) {
          this.value = new Coding();
          return this.value;
        }
        else
          return super.addChild(name);
      }

      public ActionDefinitionBehaviorComponent copy() {
        ActionDefinitionBehaviorComponent dst = new ActionDefinitionBehaviorComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof ActionDefinitionBehaviorComponent))
          return false;
        ActionDefinitionBehaviorComponent o = (ActionDefinitionBehaviorComponent) other;
        return compareDeep(type, o.type, true) && compareDeep(value, o.value, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof ActionDefinitionBehaviorComponent))
          return false;
        ActionDefinitionBehaviorComponent o = (ActionDefinitionBehaviorComponent) other;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && (type == null || type.isEmpty()) && (value == null || value.isEmpty())
          ;
      }

  public String fhirType() {
    return "ActionDefinition.behavior";

  }

  }

    @Block()
    public static class ActionDefinitionCustomizationComponent extends Element implements IBaseDatatypeElement {
        /**
         * The path to the element to be customized.
         */
        @Child(name = "path", type = {StringType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="", formalDefinition="The path to the element to be customized." )
        protected StringType path;

        /**
         * An expression specifying the value of the customized element.
         */
        @Child(name = "expression", type = {StringType.class}, order=2, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="", formalDefinition="An expression specifying the value of the customized element." )
        protected StringType expression;

        private static final long serialVersionUID = -252690483L;

    /**
     * Constructor
     */
      public ActionDefinitionCustomizationComponent() {
        super();
      }

    /**
     * Constructor
     */
      public ActionDefinitionCustomizationComponent(StringType path, StringType expression) {
        super();
        this.path = path;
        this.expression = expression;
      }

        /**
         * @return {@link #path} (The path to the element to be customized.). This is the underlying object with id, value and extensions. The accessor "getPath" gives direct access to the value
         */
        public StringType getPathElement() { 
          if (this.path == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create ActionDefinitionCustomizationComponent.path");
            else if (Configuration.doAutoCreate())
              this.path = new StringType(); // bb
          return this.path;
        }

        public boolean hasPathElement() { 
          return this.path != null && !this.path.isEmpty();
        }

        public boolean hasPath() { 
          return this.path != null && !this.path.isEmpty();
        }

        /**
         * @param value {@link #path} (The path to the element to be customized.). This is the underlying object with id, value and extensions. The accessor "getPath" gives direct access to the value
         */
        public ActionDefinitionCustomizationComponent setPathElement(StringType value) { 
          this.path = value;
          return this;
        }

        /**
         * @return The path to the element to be customized.
         */
        public String getPath() { 
          return this.path == null ? null : this.path.getValue();
        }

        /**
         * @param value The path to the element to be customized.
         */
        public ActionDefinitionCustomizationComponent setPath(String value) { 
            if (this.path == null)
              this.path = new StringType();
            this.path.setValue(value);
          return this;
        }

        /**
         * @return {@link #expression} (An expression specifying the value of the customized element.). This is the underlying object with id, value and extensions. The accessor "getExpression" gives direct access to the value
         */
        public StringType getExpressionElement() { 
          if (this.expression == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create ActionDefinitionCustomizationComponent.expression");
            else if (Configuration.doAutoCreate())
              this.expression = new StringType(); // bb
          return this.expression;
        }

        public boolean hasExpressionElement() { 
          return this.expression != null && !this.expression.isEmpty();
        }

        public boolean hasExpression() { 
          return this.expression != null && !this.expression.isEmpty();
        }

        /**
         * @param value {@link #expression} (An expression specifying the value of the customized element.). This is the underlying object with id, value and extensions. The accessor "getExpression" gives direct access to the value
         */
        public ActionDefinitionCustomizationComponent setExpressionElement(StringType value) { 
          this.expression = value;
          return this;
        }

        /**
         * @return An expression specifying the value of the customized element.
         */
        public String getExpression() { 
          return this.expression == null ? null : this.expression.getValue();
        }

        /**
         * @param value An expression specifying the value of the customized element.
         */
        public ActionDefinitionCustomizationComponent setExpression(String value) { 
            if (this.expression == null)
              this.expression = new StringType();
            this.expression.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("path", "string", "The path to the element to be customized.", 0, java.lang.Integer.MAX_VALUE, path));
          childrenList.add(new Property("expression", "string", "An expression specifying the value of the customized element.", 0, java.lang.Integer.MAX_VALUE, expression));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("path"))
          this.path = castToString(value); // StringType
        else if (name.equals("expression"))
          this.expression = castToString(value); // StringType
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("path")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.path");
        }
        else if (name.equals("expression")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.expression");
        }
        else
          return super.addChild(name);
      }

      public ActionDefinitionCustomizationComponent copy() {
        ActionDefinitionCustomizationComponent dst = new ActionDefinitionCustomizationComponent();
        copyValues(dst);
        dst.path = path == null ? null : path.copy();
        dst.expression = expression == null ? null : expression.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof ActionDefinitionCustomizationComponent))
          return false;
        ActionDefinitionCustomizationComponent o = (ActionDefinitionCustomizationComponent) other;
        return compareDeep(path, o.path, true) && compareDeep(expression, o.expression, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof ActionDefinitionCustomizationComponent))
          return false;
        ActionDefinitionCustomizationComponent o = (ActionDefinitionCustomizationComponent) other;
        return compareValues(path, o.path, true) && compareValues(expression, o.expression, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (path == null || path.isEmpty()) && (expression == null || expression.isEmpty())
          ;
      }

  public String fhirType() {
    return "ActionDefinition.customization";

  }

  }

    /**
     * A unique identifier for the action.
     */
    @Child(name = "actionIdentifier", type = {Identifier.class}, order=0, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="A unique identifier for the action." )
    protected Identifier actionIdentifier;

    /**
     * A user-visible number for the action.
     */
    @Child(name = "number", type = {StringType.class}, order=1, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="A user-visible number for the action." )
    protected StringType number;

    /**
     * The title of the action.
     */
    @Child(name = "title", type = {StringType.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="The title of the action." )
    protected StringType title;

    /**
     * A short description of the action.
     */
    @Child(name = "description", type = {StringType.class}, order=3, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="A short description of the action." )
    protected StringType description;

    /**
     * A text equivalent of the action to be performed.
     */
    @Child(name = "textEquivalent", type = {StringType.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="A text equivalent of the action to be performed." )
    protected StringType textEquivalent;

    /**
     * Concepts associated with the action.
     */
    @Child(name = "concept", type = {CodeableConcept.class}, order=5, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="Concepts associated with the action." )
    protected List<CodeableConcept> concept;

    /**
     * Supporting evidence for the action.
     */
    @Child(name = "supportingEvidence", type = {Attachment.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="Supporting evidence for the action." )
    protected List<Attachment> supportingEvidence;

    /**
     * Supporting documentation for the action.
     */
    @Child(name = "documentation", type = {Attachment.class}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="Supporting documentation for the action." )
    protected List<Attachment> documentation;

    /**
     * The type of participant in the action.
     */
    @Child(name = "participantType", type = {CodeType.class}, order=8, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="patient | practitioner | related-person", formalDefinition="The type of participant in the action." )
    protected List<Enumeration<ParticipantType>> participantType;

    /**
     * The type of action to perform (create, update, remove).
     */
    @Child(name = "type", type = {CodeType.class}, order=9, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="create | update | remove | fire-event", formalDefinition="The type of action to perform (create, update, remove)." )
    protected Enumeration<ActionType> type;

    /**
     * A behavior associated with the action. Behaviors define how the action is to be presented and/or executed within the receiving environment.
     */
    @Child(name = "behavior", type = {}, order=10, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="A behavior associated with the action. Behaviors define how the action is to be presented and/or executed within the receiving environment." )
    protected List<ActionDefinitionBehaviorComponent> behavior;

    /**
     * The resource that is the target of the action (e.g. CommunicationRequest).
     */
    @Child(name = "resource", type = {}, order=11, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="The resource that is the target of the action (e.g. CommunicationRequest)." )
    protected Reference resource;

    /**
     * The actual object that is the target of the reference (The resource that is the target of the action (e.g. CommunicationRequest).)
     */
    protected Resource resourceTarget;

    /**
     * Customizations that should be applied to the statically defined resource.
     */
    @Child(name = "customization", type = {}, order=12, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="Customizations that should be applied to the statically defined resource." )
    protected List<ActionDefinitionCustomizationComponent> customization;

    /**
     * Sub actions.
     */
    @Child(name = "actions", type = {ActionDefinition.class}, order=13, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="Sub actions." )
    protected List<ActionDefinition> actions;

    private static final long serialVersionUID = -452767919L;

  /**
   * Constructor
   */
    public ActionDefinition() {
      super();
    }

    /**
     * @return {@link #actionIdentifier} (A unique identifier for the action.)
     */
    public Identifier getActionIdentifier() { 
      if (this.actionIdentifier == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActionDefinition.actionIdentifier");
        else if (Configuration.doAutoCreate())
          this.actionIdentifier = new Identifier(); // cc
      return this.actionIdentifier;
    }

    public boolean hasActionIdentifier() { 
      return this.actionIdentifier != null && !this.actionIdentifier.isEmpty();
    }

    /**
     * @param value {@link #actionIdentifier} (A unique identifier for the action.)
     */
    public ActionDefinition setActionIdentifier(Identifier value) { 
      this.actionIdentifier = value;
      return this;
    }

    /**
     * @return {@link #number} (A user-visible number for the action.). This is the underlying object with id, value and extensions. The accessor "getNumber" gives direct access to the value
     */
    public StringType getNumberElement() { 
      if (this.number == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActionDefinition.number");
        else if (Configuration.doAutoCreate())
          this.number = new StringType(); // bb
      return this.number;
    }

    public boolean hasNumberElement() { 
      return this.number != null && !this.number.isEmpty();
    }

    public boolean hasNumber() { 
      return this.number != null && !this.number.isEmpty();
    }

    /**
     * @param value {@link #number} (A user-visible number for the action.). This is the underlying object with id, value and extensions. The accessor "getNumber" gives direct access to the value
     */
    public ActionDefinition setNumberElement(StringType value) { 
      this.number = value;
      return this;
    }

    /**
     * @return A user-visible number for the action.
     */
    public String getNumber() { 
      return this.number == null ? null : this.number.getValue();
    }

    /**
     * @param value A user-visible number for the action.
     */
    public ActionDefinition setNumber(String value) { 
      if (Utilities.noString(value))
        this.number = null;
      else {
        if (this.number == null)
          this.number = new StringType();
        this.number.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #title} (The title of the action.). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
     */
    public StringType getTitleElement() { 
      if (this.title == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActionDefinition.title");
        else if (Configuration.doAutoCreate())
          this.title = new StringType(); // bb
      return this.title;
    }

    public boolean hasTitleElement() { 
      return this.title != null && !this.title.isEmpty();
    }

    public boolean hasTitle() { 
      return this.title != null && !this.title.isEmpty();
    }

    /**
     * @param value {@link #title} (The title of the action.). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
     */
    public ActionDefinition setTitleElement(StringType value) { 
      this.title = value;
      return this;
    }

    /**
     * @return The title of the action.
     */
    public String getTitle() { 
      return this.title == null ? null : this.title.getValue();
    }

    /**
     * @param value The title of the action.
     */
    public ActionDefinition setTitle(String value) { 
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
     * @return {@link #description} (A short description of the action.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      if (this.description == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActionDefinition.description");
        else if (Configuration.doAutoCreate())
          this.description = new StringType(); // bb
      return this.description;
    }

    public boolean hasDescriptionElement() { 
      return this.description != null && !this.description.isEmpty();
    }

    public boolean hasDescription() { 
      return this.description != null && !this.description.isEmpty();
    }

    /**
     * @param value {@link #description} (A short description of the action.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public ActionDefinition setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A short description of the action.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A short description of the action.
     */
    public ActionDefinition setDescription(String value) { 
      if (Utilities.noString(value))
        this.description = null;
      else {
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #textEquivalent} (A text equivalent of the action to be performed.). This is the underlying object with id, value and extensions. The accessor "getTextEquivalent" gives direct access to the value
     */
    public StringType getTextEquivalentElement() { 
      if (this.textEquivalent == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActionDefinition.textEquivalent");
        else if (Configuration.doAutoCreate())
          this.textEquivalent = new StringType(); // bb
      return this.textEquivalent;
    }

    public boolean hasTextEquivalentElement() { 
      return this.textEquivalent != null && !this.textEquivalent.isEmpty();
    }

    public boolean hasTextEquivalent() { 
      return this.textEquivalent != null && !this.textEquivalent.isEmpty();
    }

    /**
     * @param value {@link #textEquivalent} (A text equivalent of the action to be performed.). This is the underlying object with id, value and extensions. The accessor "getTextEquivalent" gives direct access to the value
     */
    public ActionDefinition setTextEquivalentElement(StringType value) { 
      this.textEquivalent = value;
      return this;
    }

    /**
     * @return A text equivalent of the action to be performed.
     */
    public String getTextEquivalent() { 
      return this.textEquivalent == null ? null : this.textEquivalent.getValue();
    }

    /**
     * @param value A text equivalent of the action to be performed.
     */
    public ActionDefinition setTextEquivalent(String value) { 
      if (Utilities.noString(value))
        this.textEquivalent = null;
      else {
        if (this.textEquivalent == null)
          this.textEquivalent = new StringType();
        this.textEquivalent.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #concept} (Concepts associated with the action.)
     */
    public List<CodeableConcept> getConcept() { 
      if (this.concept == null)
        this.concept = new ArrayList<CodeableConcept>();
      return this.concept;
    }

    public boolean hasConcept() { 
      if (this.concept == null)
        return false;
      for (CodeableConcept item : this.concept)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #concept} (Concepts associated with the action.)
     */
    // syntactic sugar
    public CodeableConcept addConcept() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.concept == null)
        this.concept = new ArrayList<CodeableConcept>();
      this.concept.add(t);
      return t;
    }

    // syntactic sugar
    public ActionDefinition addConcept(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.concept == null)
        this.concept = new ArrayList<CodeableConcept>();
      this.concept.add(t);
      return this;
    }

    /**
     * @return {@link #supportingEvidence} (Supporting evidence for the action.)
     */
    public List<Attachment> getSupportingEvidence() { 
      if (this.supportingEvidence == null)
        this.supportingEvidence = new ArrayList<Attachment>();
      return this.supportingEvidence;
    }

    public boolean hasSupportingEvidence() { 
      if (this.supportingEvidence == null)
        return false;
      for (Attachment item : this.supportingEvidence)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #supportingEvidence} (Supporting evidence for the action.)
     */
    // syntactic sugar
    public Attachment addSupportingEvidence() { //3
      Attachment t = new Attachment();
      if (this.supportingEvidence == null)
        this.supportingEvidence = new ArrayList<Attachment>();
      this.supportingEvidence.add(t);
      return t;
    }

    // syntactic sugar
    public ActionDefinition addSupportingEvidence(Attachment t) { //3
      if (t == null)
        return this;
      if (this.supportingEvidence == null)
        this.supportingEvidence = new ArrayList<Attachment>();
      this.supportingEvidence.add(t);
      return this;
    }

    /**
     * @return {@link #documentation} (Supporting documentation for the action.)
     */
    public List<Attachment> getDocumentation() { 
      if (this.documentation == null)
        this.documentation = new ArrayList<Attachment>();
      return this.documentation;
    }

    public boolean hasDocumentation() { 
      if (this.documentation == null)
        return false;
      for (Attachment item : this.documentation)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #documentation} (Supporting documentation for the action.)
     */
    // syntactic sugar
    public Attachment addDocumentation() { //3
      Attachment t = new Attachment();
      if (this.documentation == null)
        this.documentation = new ArrayList<Attachment>();
      this.documentation.add(t);
      return t;
    }

    // syntactic sugar
    public ActionDefinition addDocumentation(Attachment t) { //3
      if (t == null)
        return this;
      if (this.documentation == null)
        this.documentation = new ArrayList<Attachment>();
      this.documentation.add(t);
      return this;
    }

    /**
     * @return {@link #participantType} (The type of participant in the action.)
     */
    public List<Enumeration<ParticipantType>> getParticipantType() { 
      if (this.participantType == null)
        this.participantType = new ArrayList<Enumeration<ParticipantType>>();
      return this.participantType;
    }

    public boolean hasParticipantType() { 
      if (this.participantType == null)
        return false;
      for (Enumeration<ParticipantType> item : this.participantType)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #participantType} (The type of participant in the action.)
     */
    // syntactic sugar
    public Enumeration<ParticipantType> addParticipantTypeElement() {//2 
      Enumeration<ParticipantType> t = new Enumeration<ParticipantType>(new ParticipantTypeEnumFactory());
      if (this.participantType == null)
        this.participantType = new ArrayList<Enumeration<ParticipantType>>();
      this.participantType.add(t);
      return t;
    }

    /**
     * @param value {@link #participantType} (The type of participant in the action.)
     */
    public ActionDefinition addParticipantType(ParticipantType value) { //1
      Enumeration<ParticipantType> t = new Enumeration<ParticipantType>(new ParticipantTypeEnumFactory());
      t.setValue(value);
      if (this.participantType == null)
        this.participantType = new ArrayList<Enumeration<ParticipantType>>();
      this.participantType.add(t);
      return this;
    }

    /**
     * @param value {@link #participantType} (The type of participant in the action.)
     */
    public boolean hasParticipantType(ParticipantType value) { 
      if (this.participantType == null)
        return false;
      for (Enumeration<ParticipantType> v : this.participantType)
        if (v.equals(value)) // code
          return true;
      return false;
    }

    /**
     * @return {@link #type} (The type of action to perform (create, update, remove).). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Enumeration<ActionType> getTypeElement() { 
      if (this.type == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActionDefinition.type");
        else if (Configuration.doAutoCreate())
          this.type = new Enumeration<ActionType>(new ActionTypeEnumFactory()); // bb
      return this.type;
    }

    public boolean hasTypeElement() { 
      return this.type != null && !this.type.isEmpty();
    }

    public boolean hasType() { 
      return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (The type of action to perform (create, update, remove).). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public ActionDefinition setTypeElement(Enumeration<ActionType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return The type of action to perform (create, update, remove).
     */
    public ActionType getType() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value The type of action to perform (create, update, remove).
     */
    public ActionDefinition setType(ActionType value) { 
      if (value == null)
        this.type = null;
      else {
        if (this.type == null)
          this.type = new Enumeration<ActionType>(new ActionTypeEnumFactory());
        this.type.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #behavior} (A behavior associated with the action. Behaviors define how the action is to be presented and/or executed within the receiving environment.)
     */
    public List<ActionDefinitionBehaviorComponent> getBehavior() { 
      if (this.behavior == null)
        this.behavior = new ArrayList<ActionDefinitionBehaviorComponent>();
      return this.behavior;
    }

    public boolean hasBehavior() { 
      if (this.behavior == null)
        return false;
      for (ActionDefinitionBehaviorComponent item : this.behavior)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #behavior} (A behavior associated with the action. Behaviors define how the action is to be presented and/or executed within the receiving environment.)
     */
    // syntactic sugar
    public ActionDefinitionBehaviorComponent addBehavior() { //3
      ActionDefinitionBehaviorComponent t = new ActionDefinitionBehaviorComponent();
      if (this.behavior == null)
        this.behavior = new ArrayList<ActionDefinitionBehaviorComponent>();
      this.behavior.add(t);
      return t;
    }

    // syntactic sugar
    public ActionDefinition addBehavior(ActionDefinitionBehaviorComponent t) { //3
      if (t == null)
        return this;
      if (this.behavior == null)
        this.behavior = new ArrayList<ActionDefinitionBehaviorComponent>();
      this.behavior.add(t);
      return this;
    }

    /**
     * @return {@link #resource} (The resource that is the target of the action (e.g. CommunicationRequest).)
     */
    public Reference getResource() { 
      if (this.resource == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ActionDefinition.resource");
        else if (Configuration.doAutoCreate())
          this.resource = new Reference(); // cc
      return this.resource;
    }

    public boolean hasResource() { 
      return this.resource != null && !this.resource.isEmpty();
    }

    /**
     * @param value {@link #resource} (The resource that is the target of the action (e.g. CommunicationRequest).)
     */
    public ActionDefinition setResource(Reference value) { 
      this.resource = value;
      return this;
    }

    /**
     * @return {@link #resource} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The resource that is the target of the action (e.g. CommunicationRequest).)
     */
    public Resource getResourceTarget() { 
      return this.resourceTarget;
    }

    /**
     * @param value {@link #resource} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The resource that is the target of the action (e.g. CommunicationRequest).)
     */
    public ActionDefinition setResourceTarget(Resource value) { 
      this.resourceTarget = value;
      return this;
    }

    /**
     * @return {@link #customization} (Customizations that should be applied to the statically defined resource.)
     */
    public List<ActionDefinitionCustomizationComponent> getCustomization() { 
      if (this.customization == null)
        this.customization = new ArrayList<ActionDefinitionCustomizationComponent>();
      return this.customization;
    }

    public boolean hasCustomization() { 
      if (this.customization == null)
        return false;
      for (ActionDefinitionCustomizationComponent item : this.customization)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #customization} (Customizations that should be applied to the statically defined resource.)
     */
    // syntactic sugar
    public ActionDefinitionCustomizationComponent addCustomization() { //3
      ActionDefinitionCustomizationComponent t = new ActionDefinitionCustomizationComponent();
      if (this.customization == null)
        this.customization = new ArrayList<ActionDefinitionCustomizationComponent>();
      this.customization.add(t);
      return t;
    }

    // syntactic sugar
    public ActionDefinition addCustomization(ActionDefinitionCustomizationComponent t) { //3
      if (t == null)
        return this;
      if (this.customization == null)
        this.customization = new ArrayList<ActionDefinitionCustomizationComponent>();
      this.customization.add(t);
      return this;
    }

    /**
     * @return {@link #actions} (Sub actions.)
     */
    public List<ActionDefinition> getActions() { 
      if (this.actions == null)
        this.actions = new ArrayList<ActionDefinition>();
      return this.actions;
    }

    public boolean hasActions() { 
      if (this.actions == null)
        return false;
      for (ActionDefinition item : this.actions)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #actions} (Sub actions.)
     */
    // syntactic sugar
    public ActionDefinition addActions() { //3
      ActionDefinition t = new ActionDefinition();
      if (this.actions == null)
        this.actions = new ArrayList<ActionDefinition>();
      this.actions.add(t);
      return t;
    }

    // syntactic sugar
    public ActionDefinition addActions(ActionDefinition t) { //3
      if (t == null)
        return this;
      if (this.actions == null)
        this.actions = new ArrayList<ActionDefinition>();
      this.actions.add(t);
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("actionIdentifier", "Identifier", "A unique identifier for the action.", 0, java.lang.Integer.MAX_VALUE, actionIdentifier));
        childrenList.add(new Property("number", "string", "A user-visible number for the action.", 0, java.lang.Integer.MAX_VALUE, number));
        childrenList.add(new Property("title", "string", "The title of the action.", 0, java.lang.Integer.MAX_VALUE, title));
        childrenList.add(new Property("description", "string", "A short description of the action.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("textEquivalent", "string", "A text equivalent of the action to be performed.", 0, java.lang.Integer.MAX_VALUE, textEquivalent));
        childrenList.add(new Property("concept", "CodeableConcept", "Concepts associated with the action.", 0, java.lang.Integer.MAX_VALUE, concept));
        childrenList.add(new Property("supportingEvidence", "Attachment", "Supporting evidence for the action.", 0, java.lang.Integer.MAX_VALUE, supportingEvidence));
        childrenList.add(new Property("documentation", "Attachment", "Supporting documentation for the action.", 0, java.lang.Integer.MAX_VALUE, documentation));
        childrenList.add(new Property("participantType", "code", "The type of participant in the action.", 0, java.lang.Integer.MAX_VALUE, participantType));
        childrenList.add(new Property("type", "code", "The type of action to perform (create, update, remove).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("behavior", "", "A behavior associated with the action. Behaviors define how the action is to be presented and/or executed within the receiving environment.", 0, java.lang.Integer.MAX_VALUE, behavior));
        childrenList.add(new Property("resource", "Reference(Any)", "The resource that is the target of the action (e.g. CommunicationRequest).", 0, java.lang.Integer.MAX_VALUE, resource));
        childrenList.add(new Property("customization", "", "Customizations that should be applied to the statically defined resource.", 0, java.lang.Integer.MAX_VALUE, customization));
        childrenList.add(new Property("actions", "ActionDefinition", "Sub actions.", 0, java.lang.Integer.MAX_VALUE, actions));
      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("actionIdentifier"))
          this.actionIdentifier = castToIdentifier(value); // Identifier
        else if (name.equals("number"))
          this.number = castToString(value); // StringType
        else if (name.equals("title"))
          this.title = castToString(value); // StringType
        else if (name.equals("description"))
          this.description = castToString(value); // StringType
        else if (name.equals("textEquivalent"))
          this.textEquivalent = castToString(value); // StringType
        else if (name.equals("concept"))
          this.getConcept().add(castToCodeableConcept(value));
        else if (name.equals("supportingEvidence"))
          this.getSupportingEvidence().add(castToAttachment(value));
        else if (name.equals("documentation"))
          this.getDocumentation().add(castToAttachment(value));
        else if (name.equals("participantType"))
          this.getParticipantType().add(new ParticipantTypeEnumFactory().fromType(value));
        else if (name.equals("type"))
          this.type = new ActionTypeEnumFactory().fromType(value); // Enumeration<ActionType>
        else if (name.equals("behavior"))
          this.getBehavior().add((ActionDefinitionBehaviorComponent) value);
        else if (name.equals("resource"))
          this.resource = castToReference(value); // Reference
        else if (name.equals("customization"))
          this.getCustomization().add((ActionDefinitionCustomizationComponent) value);
        else if (name.equals("actions"))
          this.getActions().add(castToActionDefinition(value));
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("actionIdentifier")) {
          this.actionIdentifier = new Identifier();
          return this.actionIdentifier;
        }
        else if (name.equals("number")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.number");
        }
        else if (name.equals("title")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.title");
        }
        else if (name.equals("description")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.description");
        }
        else if (name.equals("textEquivalent")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.textEquivalent");
        }
        else if (name.equals("concept")) {
          return addConcept();
        }
        else if (name.equals("supportingEvidence")) {
          return addSupportingEvidence();
        }
        else if (name.equals("documentation")) {
          return addDocumentation();
        }
        else if (name.equals("participantType")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.participantType");
        }
        else if (name.equals("type")) {
          throw new FHIRException("Cannot call addChild on a primitive type ActionDefinition.type");
        }
        else if (name.equals("behavior")) {
          return addBehavior();
        }
        else if (name.equals("resource")) {
          this.resource = new Reference();
          return this.resource;
        }
        else if (name.equals("customization")) {
          return addCustomization();
        }
        else if (name.equals("actions")) {
          return addActions();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "ActionDefinition";

  }

      public ActionDefinition copy() {
        ActionDefinition dst = new ActionDefinition();
        copyValues(dst);
        dst.actionIdentifier = actionIdentifier == null ? null : actionIdentifier.copy();
        dst.number = number == null ? null : number.copy();
        dst.title = title == null ? null : title.copy();
        dst.description = description == null ? null : description.copy();
        dst.textEquivalent = textEquivalent == null ? null : textEquivalent.copy();
        if (concept != null) {
          dst.concept = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : concept)
            dst.concept.add(i.copy());
        };
        if (supportingEvidence != null) {
          dst.supportingEvidence = new ArrayList<Attachment>();
          for (Attachment i : supportingEvidence)
            dst.supportingEvidence.add(i.copy());
        };
        if (documentation != null) {
          dst.documentation = new ArrayList<Attachment>();
          for (Attachment i : documentation)
            dst.documentation.add(i.copy());
        };
        if (participantType != null) {
          dst.participantType = new ArrayList<Enumeration<ParticipantType>>();
          for (Enumeration<ParticipantType> i : participantType)
            dst.participantType.add(i.copy());
        };
        dst.type = type == null ? null : type.copy();
        if (behavior != null) {
          dst.behavior = new ArrayList<ActionDefinitionBehaviorComponent>();
          for (ActionDefinitionBehaviorComponent i : behavior)
            dst.behavior.add(i.copy());
        };
        dst.resource = resource == null ? null : resource.copy();
        if (customization != null) {
          dst.customization = new ArrayList<ActionDefinitionCustomizationComponent>();
          for (ActionDefinitionCustomizationComponent i : customization)
            dst.customization.add(i.copy());
        };
        if (actions != null) {
          dst.actions = new ArrayList<ActionDefinition>();
          for (ActionDefinition i : actions)
            dst.actions.add(i.copy());
        };
        return dst;
      }

      protected ActionDefinition typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof ActionDefinition))
          return false;
        ActionDefinition o = (ActionDefinition) other;
        return compareDeep(actionIdentifier, o.actionIdentifier, true) && compareDeep(number, o.number, true)
           && compareDeep(title, o.title, true) && compareDeep(description, o.description, true) && compareDeep(textEquivalent, o.textEquivalent, true)
           && compareDeep(concept, o.concept, true) && compareDeep(supportingEvidence, o.supportingEvidence, true)
           && compareDeep(documentation, o.documentation, true) && compareDeep(participantType, o.participantType, true)
           && compareDeep(type, o.type, true) && compareDeep(behavior, o.behavior, true) && compareDeep(resource, o.resource, true)
           && compareDeep(customization, o.customization, true) && compareDeep(actions, o.actions, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof ActionDefinition))
          return false;
        ActionDefinition o = (ActionDefinition) other;
        return compareValues(number, o.number, true) && compareValues(title, o.title, true) && compareValues(description, o.description, true)
           && compareValues(textEquivalent, o.textEquivalent, true) && compareValues(participantType, o.participantType, true)
           && compareValues(type, o.type, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (actionIdentifier == null || actionIdentifier.isEmpty()) && (number == null || number.isEmpty())
           && (title == null || title.isEmpty()) && (description == null || description.isEmpty()) && (textEquivalent == null || textEquivalent.isEmpty())
           && (concept == null || concept.isEmpty()) && (supportingEvidence == null || supportingEvidence.isEmpty())
           && (documentation == null || documentation.isEmpty()) && (participantType == null || participantType.isEmpty())
           && (type == null || type.isEmpty()) && (behavior == null || behavior.isEmpty()) && (resource == null || resource.isEmpty())
           && (customization == null || customization.isEmpty()) && (actions == null || actions.isEmpty())
          ;
      }


}

