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

// Generated on Wed, Jun 1, 2016 23:03+1000 for FHIR v1.4.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
/**
 * A record of a healthcare consumer’s privacy policy, which is in accordance with governing jurisdictional and organization privacy policies that grant or withhold consent:.
 */
@ResourceDef(name="Consent", profile="http://hl7.org/fhir/Profile/Consent")
public class Consent extends DomainResource {

    public enum ConsentStatus {
        /**
         * The consent is in development or awaiting use but is not yet intended to be acted upon.
         */
        DRAFT, 
        /**
         * The consent has be proposed but not yet agreed to by all parties. The negotiation stage.
         */
        PROPOSED, 
        /**
         * The consent is to be followed and enforced.
         */
        ACTIVE, 
        /**
         * The consent has been rejected by one or more of the parties.
         */
        REJECTED, 
        /**
         * The consent is terminated or replaced.
         */
        INACTIVE, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static ConsentStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("proposed".equals(codeString))
          return PROPOSED;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("rejected".equals(codeString))
          return REJECTED;
        if ("inactive".equals(codeString))
          return INACTIVE;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown ConsentStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DRAFT: return "draft";
            case PROPOSED: return "proposed";
            case ACTIVE: return "active";
            case REJECTED: return "rejected";
            case INACTIVE: return "inactive";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case DRAFT: return "http://hl7.org/fhir/consent-status";
            case PROPOSED: return "http://hl7.org/fhir/consent-status";
            case ACTIVE: return "http://hl7.org/fhir/consent-status";
            case REJECTED: return "http://hl7.org/fhir/consent-status";
            case INACTIVE: return "http://hl7.org/fhir/consent-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DRAFT: return "The consent is in development or awaiting use but is not yet intended to be acted upon.";
            case PROPOSED: return "The consent has be proposed but not yet agreed to by all parties. The negotiation stage.";
            case ACTIVE: return "The consent is to be followed and enforced.";
            case REJECTED: return "The consent has been rejected by one or more of the parties.";
            case INACTIVE: return "The consent is terminated or replaced.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "Pending";
            case PROPOSED: return "Proposed";
            case ACTIVE: return "Active";
            case REJECTED: return "Rejected";
            case INACTIVE: return "Inactive";
            default: return "?";
          }
        }
    }

  public static class ConsentStatusEnumFactory implements EnumFactory<ConsentStatus> {
    public ConsentStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ConsentStatus.DRAFT;
        if ("proposed".equals(codeString))
          return ConsentStatus.PROPOSED;
        if ("active".equals(codeString))
          return ConsentStatus.ACTIVE;
        if ("rejected".equals(codeString))
          return ConsentStatus.REJECTED;
        if ("inactive".equals(codeString))
          return ConsentStatus.INACTIVE;
        throw new IllegalArgumentException("Unknown ConsentStatus code '"+codeString+"'");
        }
        public Enumeration<ConsentStatus> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("draft".equals(codeString))
          return new Enumeration<ConsentStatus>(this, ConsentStatus.DRAFT);
        if ("proposed".equals(codeString))
          return new Enumeration<ConsentStatus>(this, ConsentStatus.PROPOSED);
        if ("active".equals(codeString))
          return new Enumeration<ConsentStatus>(this, ConsentStatus.ACTIVE);
        if ("rejected".equals(codeString))
          return new Enumeration<ConsentStatus>(this, ConsentStatus.REJECTED);
        if ("inactive".equals(codeString))
          return new Enumeration<ConsentStatus>(this, ConsentStatus.INACTIVE);
        throw new FHIRException("Unknown ConsentStatus code '"+codeString+"'");
        }
    public String toCode(ConsentStatus code) {
      if (code == ConsentStatus.DRAFT)
        return "draft";
      if (code == ConsentStatus.PROPOSED)
        return "proposed";
      if (code == ConsentStatus.ACTIVE)
        return "active";
      if (code == ConsentStatus.REJECTED)
        return "rejected";
      if (code == ConsentStatus.INACTIVE)
        return "inactive";
      return "?";
      }
    public String toSystem(ConsentStatus code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class ExceptComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Type exception to the base Consent policy such as specific requirements, purposes for actions, obligations, prohibitions, e.g. life time maximum benefit.
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Consent Exception Type", formalDefinition="Type exception to the base Consent policy such as specific requirements, purposes for actions, obligations, prohibitions, e.g. life time maximum benefit." )
        protected CodeableConcept type;

        /**
         * Relevant time or time-period when this Consent Exception is applicable.
         */
        @Child(name = "period", type = {Period.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Consent Exception Effective Time", formalDefinition="Relevant time or time-period when this Consent Exception is applicable." )
        protected Period period;

        /**
         * An Actor subject to this exception.
         */
        @Child(name = "actor", type = {Device.class, Group.class, Organization.class, Patient.class, Practitioner.class, RelatedPerson.class}, order=3, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Consent Exception actor", formalDefinition="An Actor subject to this exception." )
        protected List<Reference> actor;
        /**
         * The actual objects that are the target of the reference (An Actor subject to this exception.)
         */
        protected List<Resource> actorTarget;


        /**
         * The Reference of concern in the context of this exception of the agrement.
         */
        @Child(name = "data", type = {}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Context of the Consent Exception", formalDefinition="The Reference of concern in the context of this exception of the agrement." )
        protected List<Reference> data;
        /**
         * The actual objects that are the target of the reference (The Reference of concern in the context of this exception of the agrement.)
         */
        protected List<Resource> dataTarget;


        /**
         * Action stipulated by this Consent Exception.
         */
        @Child(name = "action", type = {CodeableConcept.class}, order=5, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Consent Exception Action", formalDefinition="Action stipulated by this Consent Exception." )
        protected List<CodeableConcept> action;

        private static final long serialVersionUID = -1351563312L;

    /**
     * Constructor
     */
      public ExceptComponent() {
        super();
      }

    /**
     * Constructor
     */
      public ExceptComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (Type exception to the base Consent policy such as specific requirements, purposes for actions, obligations, prohibitions, e.g. life time maximum benefit.)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create ExceptComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (Type exception to the base Consent policy such as specific requirements, purposes for actions, obligations, prohibitions, e.g. life time maximum benefit.)
         */
        public ExceptComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #period} (Relevant time or time-period when this Consent Exception is applicable.)
         */
        public Period getPeriod() { 
          if (this.period == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create ExceptComponent.period");
            else if (Configuration.doAutoCreate())
              this.period = new Period(); // cc
          return this.period;
        }

        public boolean hasPeriod() { 
          return this.period != null && !this.period.isEmpty();
        }

        /**
         * @param value {@link #period} (Relevant time or time-period when this Consent Exception is applicable.)
         */
        public ExceptComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        /**
         * @return {@link #actor} (An Actor subject to this exception.)
         */
        public List<Reference> getActor() { 
          if (this.actor == null)
            this.actor = new ArrayList<Reference>();
          return this.actor;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public ExceptComponent setActor(List<Reference> theActor) { 
          this.actor = theActor;
          return this;
        }

        public boolean hasActor() { 
          if (this.actor == null)
            return false;
          for (Reference item : this.actor)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Reference addActor() { //3
          Reference t = new Reference();
          if (this.actor == null)
            this.actor = new ArrayList<Reference>();
          this.actor.add(t);
          return t;
        }

        public ExceptComponent addActor(Reference t) { //3
          if (t == null)
            return this;
          if (this.actor == null)
            this.actor = new ArrayList<Reference>();
          this.actor.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #actor}, creating it if it does not already exist
         */
        public Reference getActorFirstRep() { 
          if (getActor().isEmpty()) {
            addActor();
          }
          return getActor().get(0);
        }

        /**
         * @deprecated Use Reference#setResource(IBaseResource) instead
         */
        @Deprecated
        public List<Resource> getActorTarget() { 
          if (this.actorTarget == null)
            this.actorTarget = new ArrayList<Resource>();
          return this.actorTarget;
        }

        /**
         * @return {@link #data} (The Reference of concern in the context of this exception of the agrement.)
         */
        public List<Reference> getData() { 
          if (this.data == null)
            this.data = new ArrayList<Reference>();
          return this.data;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public ExceptComponent setData(List<Reference> theData) { 
          this.data = theData;
          return this;
        }

        public boolean hasData() { 
          if (this.data == null)
            return false;
          for (Reference item : this.data)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Reference addData() { //3
          Reference t = new Reference();
          if (this.data == null)
            this.data = new ArrayList<Reference>();
          this.data.add(t);
          return t;
        }

        public ExceptComponent addData(Reference t) { //3
          if (t == null)
            return this;
          if (this.data == null)
            this.data = new ArrayList<Reference>();
          this.data.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #data}, creating it if it does not already exist
         */
        public Reference getDataFirstRep() { 
          if (getData().isEmpty()) {
            addData();
          }
          return getData().get(0);
        }

        /**
         * @deprecated Use Reference#setResource(IBaseResource) instead
         */
        @Deprecated
        public List<Resource> getDataTarget() { 
          if (this.dataTarget == null)
            this.dataTarget = new ArrayList<Resource>();
          return this.dataTarget;
        }

        /**
         * @return {@link #action} (Action stipulated by this Consent Exception.)
         */
        public List<CodeableConcept> getAction() { 
          if (this.action == null)
            this.action = new ArrayList<CodeableConcept>();
          return this.action;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public ExceptComponent setAction(List<CodeableConcept> theAction) { 
          this.action = theAction;
          return this;
        }

        public boolean hasAction() { 
          if (this.action == null)
            return false;
          for (CodeableConcept item : this.action)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addAction() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.action == null)
            this.action = new ArrayList<CodeableConcept>();
          this.action.add(t);
          return t;
        }

        public ExceptComponent addAction(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.action == null)
            this.action = new ArrayList<CodeableConcept>();
          this.action.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #action}, creating it if it does not already exist
         */
        public CodeableConcept getActionFirstRep() { 
          if (getAction().isEmpty()) {
            addAction();
          }
          return getAction().get(0);
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "Type exception to the base Consent policy such as specific requirements, purposes for actions, obligations, prohibitions, e.g. life time maximum benefit.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("period", "Period", "Relevant time or time-period when this Consent Exception is applicable.", 0, java.lang.Integer.MAX_VALUE, period));
          childrenList.add(new Property("actor", "Reference(Device|Group|Organization|Patient|Practitioner|RelatedPerson)", "An Actor subject to this exception.", 0, java.lang.Integer.MAX_VALUE, actor));
          childrenList.add(new Property("data", "Reference(Any)", "The Reference of concern in the context of this exception of the agrement.", 0, java.lang.Integer.MAX_VALUE, data));
          childrenList.add(new Property("action", "CodeableConcept", "Action stipulated by this Consent Exception.", 0, java.lang.Integer.MAX_VALUE, action));
        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -991726143: /*period*/ return this.period == null ? new Base[0] : new Base[] {this.period}; // Period
        case 92645877: /*actor*/ return this.actor == null ? new Base[0] : this.actor.toArray(new Base[this.actor.size()]); // Reference
        case 3076010: /*data*/ return this.data == null ? new Base[0] : this.data.toArray(new Base[this.data.size()]); // Reference
        case -1422950858: /*action*/ return this.action == null ? new Base[0] : this.action.toArray(new Base[this.action.size()]); // CodeableConcept
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          break;
        case -991726143: // period
          this.period = castToPeriod(value); // Period
          break;
        case 92645877: // actor
          this.getActor().add(castToReference(value)); // Reference
          break;
        case 3076010: // data
          this.getData().add(castToReference(value)); // Reference
          break;
        case -1422950858: // action
          this.getAction().add(castToCodeableConcept(value)); // CodeableConcept
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type"))
          this.type = castToCodeableConcept(value); // CodeableConcept
        else if (name.equals("period"))
          this.period = castToPeriod(value); // Period
        else if (name.equals("actor"))
          this.getActor().add(castToReference(value));
        else if (name.equals("data"))
          this.getData().add(castToReference(value));
        else if (name.equals("action"))
          this.getAction().add(castToCodeableConcept(value));
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610:  return getType(); // CodeableConcept
        case -991726143:  return getPeriod(); // Period
        case 92645877:  return addActor(); // Reference
        case 3076010:  return addData(); // Reference
        case -1422950858:  return addAction(); // CodeableConcept
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("period")) {
          this.period = new Period();
          return this.period;
        }
        else if (name.equals("actor")) {
          return addActor();
        }
        else if (name.equals("data")) {
          return addData();
        }
        else if (name.equals("action")) {
          return addAction();
        }
        else
          return super.addChild(name);
      }

      public ExceptComponent copy() {
        ExceptComponent dst = new ExceptComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.period = period == null ? null : period.copy();
        if (actor != null) {
          dst.actor = new ArrayList<Reference>();
          for (Reference i : actor)
            dst.actor.add(i.copy());
        };
        if (data != null) {
          dst.data = new ArrayList<Reference>();
          for (Reference i : data)
            dst.data.add(i.copy());
        };
        if (action != null) {
          dst.action = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : action)
            dst.action.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof ExceptComponent))
          return false;
        ExceptComponent o = (ExceptComponent) other;
        return compareDeep(type, o.type, true) && compareDeep(period, o.period, true) && compareDeep(actor, o.actor, true)
           && compareDeep(data, o.data, true) && compareDeep(action, o.action, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof ExceptComponent))
          return false;
        ExceptComponent o = (ExceptComponent) other;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(type, period, actor, data
          , action);
      }

  public String fhirType() {
    return "Consent.except";

  }

  }

    /**
     * Unique identifier for this Consent.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Consent identifier", formalDefinition="Unique identifier for this Consent." )
    protected Identifier identifier;

    /**
     * Indicates the current state of this consent.
     */
    @Child(name = "status", type = {CodeType.class}, order=1, min=0, max=1, modifier=true, summary=true)
    @Description(shortDefinition="draft | proposed | active | rejected | inactive", formalDefinition="Indicates the current state of this consent." )
    protected Enumeration<ConsentStatus> status;

    /**
     * Type of Consent such as an insurance policy, real estate consent, a will, power of attorny, Privacy or Security policy , trust framework agreement, etc. opt-in, opt-out, etc.
     */
    @Child(name = "type", type = {CodeableConcept.class}, order=2, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Consent Type", formalDefinition="Type of Consent such as an insurance policy, real estate consent, a will, power of attorny, Privacy or Security policy , trust framework agreement, etc. opt-in, opt-out, etc." )
    protected CodeableConcept type;

    /**
     * When this  Consent was issued.
     */
    @Child(name = "issued", type = {DateTimeType.class}, order=3, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="When this Consent was issued", formalDefinition="When this  Consent was issued." )
    protected DateTimeType issued;

    /**
     * Relevant time or time-period when this Consent is applicable.
     */
    @Child(name = "applies", type = {Period.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Effective time", formalDefinition="Relevant time or time-period when this Consent is applicable." )
    protected Period applies;

    /**
     * The patent under which this consent applies.
     */
    @Child(name = "patient", type = {Patient.class}, order=5, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Consent Patient", formalDefinition="The patent under which this consent applies." )
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (The patent under which this consent applies.)
     */
    protected Patient patientTarget;

    /**
     * A recognized organizations, or jurisdictions formed for the purpose of achieving some form of collective action such as the promulgation, administration and enforcement of consents and policies.  When empty, there Consent is under the authority of he Patient alone.
     */
    @Child(name = "authority", type = {Organization.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Authority under which this Consent has standing", formalDefinition="A recognized organizations, or jurisdictions formed for the purpose of achieving some form of collective action such as the promulgation, administration and enforcement of consents and policies.  When empty, there Consent is under the authority of he Patient alone." )
    protected List<Reference> authority;
    /**
     * The actual objects that are the target of the reference (A recognized organizations, or jurisdictions formed for the purpose of achieving some form of collective action such as the promulgation, administration and enforcement of consents and policies.  When empty, there Consent is under the authority of he Patient alone.)
     */
    protected List<Organization> authorityTarget;


    /**
     * Recognized governance framework or system operating with a circumscribed scope in accordance with specified principles, policies, processes or procedures for managing rights, actions, or behaviors of parties or principals relative to resources.
     */
    @Child(name = "domain", type = {Location.class}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Domain in which this Consent applies", formalDefinition="Recognized governance framework or system operating with a circumscribed scope in accordance with specified principles, policies, processes or procedures for managing rights, actions, or behaviors of parties or principals relative to resources." )
    protected List<Reference> domain;
    /**
     * The actual objects that are the target of the reference (Recognized governance framework or system operating with a circumscribed scope in accordance with specified principles, policies, processes or procedures for managing rights, actions, or behaviors of parties or principals relative to resources.)
     */
    protected List<Location> domainTarget;


    /**
     * Who or what is granted authority under this Consent.
     */
    @Child(name = "recipient", type = {Device.class, Group.class, Organization.class, Patient.class, Practitioner.class, RelatedPerson.class}, order=8, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Grantee recipient", formalDefinition="Who or what is granted authority under this Consent." )
    protected List<Reference> recipient;
    /**
     * The actual objects that are the target of the reference (Who or what is granted authority under this Consent.)
     */
    protected List<Resource> recipientTarget;


    /**
     * Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves.
     */
    @Child(name = "grantor", type = {Organization.class, Patient.class, Practitioner.class, RelatedPerson.class}, order=9, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Grantor", formalDefinition="Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves." )
    protected Reference grantor;

    /**
     * The actual object that is the target of the reference (Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves.)
     */
    protected Resource grantorTarget;

    /**
     * The data covered by this consent.
     */
    @Child(name = "data", type = {}, order=10, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Data impacted by the Consent", formalDefinition="The data covered by this consent." )
    protected List<Reference> data;
    /**
     * The actual objects that are the target of the reference (The data covered by this consent.)
     */
    protected List<Resource> dataTarget;


    /**
     * Actions controlled by this Consent.
     */
    @Child(name = "action", type = {CodeableConcept.class}, order=11, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Actions affected by", formalDefinition="Actions controlled by this Consent." )
    protected List<CodeableConcept> action;

    /**
     * One or more exceptions to the base policy of this Consent.
     */
    @Child(name = "except", type = {}, order=12, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Consent Exception List", formalDefinition="One or more exceptions to the base policy of this Consent." )
    protected List<ExceptComponent> except;

    /**
     * Consent legal text in human renderable form.
     */
    @Child(name = "content", type = {Attachment.class, DocumentReference.class}, order=13, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Consent Legal Text", formalDefinition="Consent legal text in human renderable form." )
    protected Type content;

    private static final long serialVersionUID = 1767985847L;

  /**
   * Constructor
   */
    public Consent() {
      super();
    }

  /**
   * Constructor
   */
    public Consent(CodeableConcept type, DateTimeType issued, Reference patient) {
      super();
      this.type = type;
      this.issued = issued;
      this.patient = patient;
    }

    /**
     * @return {@link #identifier} (Unique identifier for this Consent.)
     */
    public Identifier getIdentifier() { 
      if (this.identifier == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.identifier");
        else if (Configuration.doAutoCreate())
          this.identifier = new Identifier(); // cc
      return this.identifier;
    }

    public boolean hasIdentifier() { 
      return this.identifier != null && !this.identifier.isEmpty();
    }

    /**
     * @param value {@link #identifier} (Unique identifier for this Consent.)
     */
    public Consent setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #status} (Indicates the current state of this consent.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ConsentStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<ConsentStatus>(new ConsentStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (Indicates the current state of this consent.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Consent setStatusElement(Enumeration<ConsentStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Indicates the current state of this consent.
     */
    public ConsentStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Indicates the current state of this consent.
     */
    public Consent setStatus(ConsentStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<ConsentStatus>(new ConsentStatusEnumFactory());
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #type} (Type of Consent such as an insurance policy, real estate consent, a will, power of attorny, Privacy or Security policy , trust framework agreement, etc. opt-in, opt-out, etc.)
     */
    public CodeableConcept getType() { 
      if (this.type == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.type");
        else if (Configuration.doAutoCreate())
          this.type = new CodeableConcept(); // cc
      return this.type;
    }

    public boolean hasType() { 
      return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (Type of Consent such as an insurance policy, real estate consent, a will, power of attorny, Privacy or Security policy , trust framework agreement, etc. opt-in, opt-out, etc.)
     */
    public Consent setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #issued} (When this  Consent was issued.). This is the underlying object with id, value and extensions. The accessor "getIssued" gives direct access to the value
     */
    public DateTimeType getIssuedElement() { 
      if (this.issued == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.issued");
        else if (Configuration.doAutoCreate())
          this.issued = new DateTimeType(); // bb
      return this.issued;
    }

    public boolean hasIssuedElement() { 
      return this.issued != null && !this.issued.isEmpty();
    }

    public boolean hasIssued() { 
      return this.issued != null && !this.issued.isEmpty();
    }

    /**
     * @param value {@link #issued} (When this  Consent was issued.). This is the underlying object with id, value and extensions. The accessor "getIssued" gives direct access to the value
     */
    public Consent setIssuedElement(DateTimeType value) { 
      this.issued = value;
      return this;
    }

    /**
     * @return When this  Consent was issued.
     */
    public Date getIssued() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    /**
     * @param value When this  Consent was issued.
     */
    public Consent setIssued(Date value) { 
        if (this.issued == null)
          this.issued = new DateTimeType();
        this.issued.setValue(value);
      return this;
    }

    /**
     * @return {@link #applies} (Relevant time or time-period when this Consent is applicable.)
     */
    public Period getApplies() { 
      if (this.applies == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.applies");
        else if (Configuration.doAutoCreate())
          this.applies = new Period(); // cc
      return this.applies;
    }

    public boolean hasApplies() { 
      return this.applies != null && !this.applies.isEmpty();
    }

    /**
     * @param value {@link #applies} (Relevant time or time-period when this Consent is applicable.)
     */
    public Consent setApplies(Period value) { 
      this.applies = value;
      return this;
    }

    /**
     * @return {@link #patient} (The patent under which this consent applies.)
     */
    public Reference getPatient() { 
      if (this.patient == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.patient");
        else if (Configuration.doAutoCreate())
          this.patient = new Reference(); // cc
      return this.patient;
    }

    public boolean hasPatient() { 
      return this.patient != null && !this.patient.isEmpty();
    }

    /**
     * @param value {@link #patient} (The patent under which this consent applies.)
     */
    public Consent setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patent under which this consent applies.)
     */
    public Patient getPatientTarget() { 
      if (this.patientTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.patient");
        else if (Configuration.doAutoCreate())
          this.patientTarget = new Patient(); // aa
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patent under which this consent applies.)
     */
    public Consent setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #authority} (A recognized organizations, or jurisdictions formed for the purpose of achieving some form of collective action such as the promulgation, administration and enforcement of consents and policies.  When empty, there Consent is under the authority of he Patient alone.)
     */
    public List<Reference> getAuthority() { 
      if (this.authority == null)
        this.authority = new ArrayList<Reference>();
      return this.authority;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Consent setAuthority(List<Reference> theAuthority) { 
      this.authority = theAuthority;
      return this;
    }

    public boolean hasAuthority() { 
      if (this.authority == null)
        return false;
      for (Reference item : this.authority)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addAuthority() { //3
      Reference t = new Reference();
      if (this.authority == null)
        this.authority = new ArrayList<Reference>();
      this.authority.add(t);
      return t;
    }

    public Consent addAuthority(Reference t) { //3
      if (t == null)
        return this;
      if (this.authority == null)
        this.authority = new ArrayList<Reference>();
      this.authority.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #authority}, creating it if it does not already exist
     */
    public Reference getAuthorityFirstRep() { 
      if (getAuthority().isEmpty()) {
        addAuthority();
      }
      return getAuthority().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Organization> getAuthorityTarget() { 
      if (this.authorityTarget == null)
        this.authorityTarget = new ArrayList<Organization>();
      return this.authorityTarget;
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public Organization addAuthorityTarget() { 
      Organization r = new Organization();
      if (this.authorityTarget == null)
        this.authorityTarget = new ArrayList<Organization>();
      this.authorityTarget.add(r);
      return r;
    }

    /**
     * @return {@link #domain} (Recognized governance framework or system operating with a circumscribed scope in accordance with specified principles, policies, processes or procedures for managing rights, actions, or behaviors of parties or principals relative to resources.)
     */
    public List<Reference> getDomain() { 
      if (this.domain == null)
        this.domain = new ArrayList<Reference>();
      return this.domain;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Consent setDomain(List<Reference> theDomain) { 
      this.domain = theDomain;
      return this;
    }

    public boolean hasDomain() { 
      if (this.domain == null)
        return false;
      for (Reference item : this.domain)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addDomain() { //3
      Reference t = new Reference();
      if (this.domain == null)
        this.domain = new ArrayList<Reference>();
      this.domain.add(t);
      return t;
    }

    public Consent addDomain(Reference t) { //3
      if (t == null)
        return this;
      if (this.domain == null)
        this.domain = new ArrayList<Reference>();
      this.domain.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #domain}, creating it if it does not already exist
     */
    public Reference getDomainFirstRep() { 
      if (getDomain().isEmpty()) {
        addDomain();
      }
      return getDomain().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Location> getDomainTarget() { 
      if (this.domainTarget == null)
        this.domainTarget = new ArrayList<Location>();
      return this.domainTarget;
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public Location addDomainTarget() { 
      Location r = new Location();
      if (this.domainTarget == null)
        this.domainTarget = new ArrayList<Location>();
      this.domainTarget.add(r);
      return r;
    }

    /**
     * @return {@link #recipient} (Who or what is granted authority under this Consent.)
     */
    public List<Reference> getRecipient() { 
      if (this.recipient == null)
        this.recipient = new ArrayList<Reference>();
      return this.recipient;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Consent setRecipient(List<Reference> theRecipient) { 
      this.recipient = theRecipient;
      return this;
    }

    public boolean hasRecipient() { 
      if (this.recipient == null)
        return false;
      for (Reference item : this.recipient)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addRecipient() { //3
      Reference t = new Reference();
      if (this.recipient == null)
        this.recipient = new ArrayList<Reference>();
      this.recipient.add(t);
      return t;
    }

    public Consent addRecipient(Reference t) { //3
      if (t == null)
        return this;
      if (this.recipient == null)
        this.recipient = new ArrayList<Reference>();
      this.recipient.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #recipient}, creating it if it does not already exist
     */
    public Reference getRecipientFirstRep() { 
      if (getRecipient().isEmpty()) {
        addRecipient();
      }
      return getRecipient().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Resource> getRecipientTarget() { 
      if (this.recipientTarget == null)
        this.recipientTarget = new ArrayList<Resource>();
      return this.recipientTarget;
    }

    /**
     * @return {@link #grantor} (Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves.)
     */
    public Reference getGrantor() { 
      if (this.grantor == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Consent.grantor");
        else if (Configuration.doAutoCreate())
          this.grantor = new Reference(); // cc
      return this.grantor;
    }

    public boolean hasGrantor() { 
      return this.grantor != null && !this.grantor.isEmpty();
    }

    /**
     * @param value {@link #grantor} (Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves.)
     */
    public Consent setGrantor(Reference value) { 
      this.grantor = value;
      return this;
    }

    /**
     * @return {@link #grantor} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves.)
     */
    public Resource getGrantorTarget() { 
      return this.grantorTarget;
    }

    /**
     * @param value {@link #grantor} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves.)
     */
    public Consent setGrantorTarget(Resource value) { 
      this.grantorTarget = value;
      return this;
    }

    /**
     * @return {@link #data} (The data covered by this consent.)
     */
    public List<Reference> getData() { 
      if (this.data == null)
        this.data = new ArrayList<Reference>();
      return this.data;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Consent setData(List<Reference> theData) { 
      this.data = theData;
      return this;
    }

    public boolean hasData() { 
      if (this.data == null)
        return false;
      for (Reference item : this.data)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addData() { //3
      Reference t = new Reference();
      if (this.data == null)
        this.data = new ArrayList<Reference>();
      this.data.add(t);
      return t;
    }

    public Consent addData(Reference t) { //3
      if (t == null)
        return this;
      if (this.data == null)
        this.data = new ArrayList<Reference>();
      this.data.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #data}, creating it if it does not already exist
     */
    public Reference getDataFirstRep() { 
      if (getData().isEmpty()) {
        addData();
      }
      return getData().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Resource> getDataTarget() { 
      if (this.dataTarget == null)
        this.dataTarget = new ArrayList<Resource>();
      return this.dataTarget;
    }

    /**
     * @return {@link #action} (Actions controlled by this Consent.)
     */
    public List<CodeableConcept> getAction() { 
      if (this.action == null)
        this.action = new ArrayList<CodeableConcept>();
      return this.action;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Consent setAction(List<CodeableConcept> theAction) { 
      this.action = theAction;
      return this;
    }

    public boolean hasAction() { 
      if (this.action == null)
        return false;
      for (CodeableConcept item : this.action)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addAction() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.action == null)
        this.action = new ArrayList<CodeableConcept>();
      this.action.add(t);
      return t;
    }

    public Consent addAction(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.action == null)
        this.action = new ArrayList<CodeableConcept>();
      this.action.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #action}, creating it if it does not already exist
     */
    public CodeableConcept getActionFirstRep() { 
      if (getAction().isEmpty()) {
        addAction();
      }
      return getAction().get(0);
    }

    /**
     * @return {@link #except} (One or more exceptions to the base policy of this Consent.)
     */
    public List<ExceptComponent> getExcept() { 
      if (this.except == null)
        this.except = new ArrayList<ExceptComponent>();
      return this.except;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Consent setExcept(List<ExceptComponent> theExcept) { 
      this.except = theExcept;
      return this;
    }

    public boolean hasExcept() { 
      if (this.except == null)
        return false;
      for (ExceptComponent item : this.except)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public ExceptComponent addExcept() { //3
      ExceptComponent t = new ExceptComponent();
      if (this.except == null)
        this.except = new ArrayList<ExceptComponent>();
      this.except.add(t);
      return t;
    }

    public Consent addExcept(ExceptComponent t) { //3
      if (t == null)
        return this;
      if (this.except == null)
        this.except = new ArrayList<ExceptComponent>();
      this.except.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #except}, creating it if it does not already exist
     */
    public ExceptComponent getExceptFirstRep() { 
      if (getExcept().isEmpty()) {
        addExcept();
      }
      return getExcept().get(0);
    }

    /**
     * @return {@link #content} (Consent legal text in human renderable form.)
     */
    public Type getContent() { 
      return this.content;
    }

    /**
     * @return {@link #content} (Consent legal text in human renderable form.)
     */
    public Attachment getContentAttachment() throws FHIRException { 
      if (!(this.content instanceof Attachment))
        throw new FHIRException("Type mismatch: the type Attachment was expected, but "+this.content.getClass().getName()+" was encountered");
      return (Attachment) this.content;
    }

    public boolean hasContentAttachment() { 
      return this.content instanceof Attachment;
    }

    /**
     * @return {@link #content} (Consent legal text in human renderable form.)
     */
    public Reference getContentReference() throws FHIRException { 
      if (!(this.content instanceof Reference))
        throw new FHIRException("Type mismatch: the type Reference was expected, but "+this.content.getClass().getName()+" was encountered");
      return (Reference) this.content;
    }

    public boolean hasContentReference() { 
      return this.content instanceof Reference;
    }

    public boolean hasContent() { 
      return this.content != null && !this.content.isEmpty();
    }

    /**
     * @param value {@link #content} (Consent legal text in human renderable form.)
     */
    public Consent setContent(Type value) { 
      this.content = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Unique identifier for this Consent.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "Indicates the current state of this consent.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("type", "CodeableConcept", "Type of Consent such as an insurance policy, real estate consent, a will, power of attorny, Privacy or Security policy , trust framework agreement, etc. opt-in, opt-out, etc.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("issued", "dateTime", "When this  Consent was issued.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("applies", "Period", "Relevant time or time-period when this Consent is applicable.", 0, java.lang.Integer.MAX_VALUE, applies));
        childrenList.add(new Property("patient", "Reference(Patient)", "The patent under which this consent applies.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("authority", "Reference(Organization)", "A recognized organizations, or jurisdictions formed for the purpose of achieving some form of collective action such as the promulgation, administration and enforcement of consents and policies.  When empty, there Consent is under the authority of he Patient alone.", 0, java.lang.Integer.MAX_VALUE, authority));
        childrenList.add(new Property("domain", "Reference(Location)", "Recognized governance framework or system operating with a circumscribed scope in accordance with specified principles, policies, processes or procedures for managing rights, actions, or behaviors of parties or principals relative to resources.", 0, java.lang.Integer.MAX_VALUE, domain));
        childrenList.add(new Property("recipient", "Reference(Device|Group|Organization|Patient|Practitioner|RelatedPerson)", "Who or what is granted authority under this Consent.", 0, java.lang.Integer.MAX_VALUE, recipient));
        childrenList.add(new Property("grantor", "Reference(Organization|Patient|Practitioner|RelatedPerson)", "Who grants the authority under this Consent. If empty, the grantor is assumed to be the Patient themselves.", 0, java.lang.Integer.MAX_VALUE, grantor));
        childrenList.add(new Property("data", "Reference(Any)", "The data covered by this consent.", 0, java.lang.Integer.MAX_VALUE, data));
        childrenList.add(new Property("action", "CodeableConcept", "Actions controlled by this Consent.", 0, java.lang.Integer.MAX_VALUE, action));
        childrenList.add(new Property("except", "", "One or more exceptions to the base policy of this Consent.", 0, java.lang.Integer.MAX_VALUE, except));
        childrenList.add(new Property("content[x]", "Attachment|Reference(DocumentReference)", "Consent legal text in human renderable form.", 0, java.lang.Integer.MAX_VALUE, content));
      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : new Base[] {this.identifier}; // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<ConsentStatus>
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -1179159893: /*issued*/ return this.issued == null ? new Base[0] : new Base[] {this.issued}; // DateTimeType
        case -793235316: /*applies*/ return this.applies == null ? new Base[0] : new Base[] {this.applies}; // Period
        case -791418107: /*patient*/ return this.patient == null ? new Base[0] : new Base[] {this.patient}; // Reference
        case 1475610435: /*authority*/ return this.authority == null ? new Base[0] : this.authority.toArray(new Base[this.authority.size()]); // Reference
        case -1326197564: /*domain*/ return this.domain == null ? new Base[0] : this.domain.toArray(new Base[this.domain.size()]); // Reference
        case 820081177: /*recipient*/ return this.recipient == null ? new Base[0] : this.recipient.toArray(new Base[this.recipient.size()]); // Reference
        case 280295423: /*grantor*/ return this.grantor == null ? new Base[0] : new Base[] {this.grantor}; // Reference
        case 3076010: /*data*/ return this.data == null ? new Base[0] : this.data.toArray(new Base[this.data.size()]); // Reference
        case -1422950858: /*action*/ return this.action == null ? new Base[0] : this.action.toArray(new Base[this.action.size()]); // CodeableConcept
        case -1289550567: /*except*/ return this.except == null ? new Base[0] : this.except.toArray(new Base[this.except.size()]); // ExceptComponent
        case 951530617: /*content*/ return this.content == null ? new Base[0] : new Base[] {this.content}; // Type
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.identifier = castToIdentifier(value); // Identifier
          break;
        case -892481550: // status
          this.status = new ConsentStatusEnumFactory().fromType(value); // Enumeration<ConsentStatus>
          break;
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          break;
        case -1179159893: // issued
          this.issued = castToDateTime(value); // DateTimeType
          break;
        case -793235316: // applies
          this.applies = castToPeriod(value); // Period
          break;
        case -791418107: // patient
          this.patient = castToReference(value); // Reference
          break;
        case 1475610435: // authority
          this.getAuthority().add(castToReference(value)); // Reference
          break;
        case -1326197564: // domain
          this.getDomain().add(castToReference(value)); // Reference
          break;
        case 820081177: // recipient
          this.getRecipient().add(castToReference(value)); // Reference
          break;
        case 280295423: // grantor
          this.grantor = castToReference(value); // Reference
          break;
        case 3076010: // data
          this.getData().add(castToReference(value)); // Reference
          break;
        case -1422950858: // action
          this.getAction().add(castToCodeableConcept(value)); // CodeableConcept
          break;
        case -1289550567: // except
          this.getExcept().add((ExceptComponent) value); // ExceptComponent
          break;
        case 951530617: // content
          this.content = (Type) value; // Type
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier"))
          this.identifier = castToIdentifier(value); // Identifier
        else if (name.equals("status"))
          this.status = new ConsentStatusEnumFactory().fromType(value); // Enumeration<ConsentStatus>
        else if (name.equals("type"))
          this.type = castToCodeableConcept(value); // CodeableConcept
        else if (name.equals("issued"))
          this.issued = castToDateTime(value); // DateTimeType
        else if (name.equals("applies"))
          this.applies = castToPeriod(value); // Period
        else if (name.equals("patient"))
          this.patient = castToReference(value); // Reference
        else if (name.equals("authority"))
          this.getAuthority().add(castToReference(value));
        else if (name.equals("domain"))
          this.getDomain().add(castToReference(value));
        else if (name.equals("recipient"))
          this.getRecipient().add(castToReference(value));
        else if (name.equals("grantor"))
          this.grantor = castToReference(value); // Reference
        else if (name.equals("data"))
          this.getData().add(castToReference(value));
        else if (name.equals("action"))
          this.getAction().add(castToCodeableConcept(value));
        else if (name.equals("except"))
          this.getExcept().add((ExceptComponent) value);
        else if (name.equals("content[x]"))
          this.content = (Type) value; // Type
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return getIdentifier(); // Identifier
        case -892481550: throw new FHIRException("Cannot make property status as it is not a complex type"); // Enumeration<ConsentStatus>
        case 3575610:  return getType(); // CodeableConcept
        case -1179159893: throw new FHIRException("Cannot make property issued as it is not a complex type"); // DateTimeType
        case -793235316:  return getApplies(); // Period
        case -791418107:  return getPatient(); // Reference
        case 1475610435:  return addAuthority(); // Reference
        case -1326197564:  return addDomain(); // Reference
        case 820081177:  return addRecipient(); // Reference
        case 280295423:  return getGrantor(); // Reference
        case 3076010:  return addData(); // Reference
        case -1422950858:  return addAction(); // CodeableConcept
        case -1289550567:  return addExcept(); // ExceptComponent
        case 264548711:  return getContent(); // Type
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          this.identifier = new Identifier();
          return this.identifier;
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type Consent.status");
        }
        else if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("issued")) {
          throw new FHIRException("Cannot call addChild on a primitive type Consent.issued");
        }
        else if (name.equals("applies")) {
          this.applies = new Period();
          return this.applies;
        }
        else if (name.equals("patient")) {
          this.patient = new Reference();
          return this.patient;
        }
        else if (name.equals("authority")) {
          return addAuthority();
        }
        else if (name.equals("domain")) {
          return addDomain();
        }
        else if (name.equals("recipient")) {
          return addRecipient();
        }
        else if (name.equals("grantor")) {
          this.grantor = new Reference();
          return this.grantor;
        }
        else if (name.equals("data")) {
          return addData();
        }
        else if (name.equals("action")) {
          return addAction();
        }
        else if (name.equals("except")) {
          return addExcept();
        }
        else if (name.equals("contentAttachment")) {
          this.content = new Attachment();
          return this.content;
        }
        else if (name.equals("contentReference")) {
          this.content = new Reference();
          return this.content;
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "Consent";

  }

      public Consent copy() {
        Consent dst = new Consent();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.type = type == null ? null : type.copy();
        dst.issued = issued == null ? null : issued.copy();
        dst.applies = applies == null ? null : applies.copy();
        dst.patient = patient == null ? null : patient.copy();
        if (authority != null) {
          dst.authority = new ArrayList<Reference>();
          for (Reference i : authority)
            dst.authority.add(i.copy());
        };
        if (domain != null) {
          dst.domain = new ArrayList<Reference>();
          for (Reference i : domain)
            dst.domain.add(i.copy());
        };
        if (recipient != null) {
          dst.recipient = new ArrayList<Reference>();
          for (Reference i : recipient)
            dst.recipient.add(i.copy());
        };
        dst.grantor = grantor == null ? null : grantor.copy();
        if (data != null) {
          dst.data = new ArrayList<Reference>();
          for (Reference i : data)
            dst.data.add(i.copy());
        };
        if (action != null) {
          dst.action = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : action)
            dst.action.add(i.copy());
        };
        if (except != null) {
          dst.except = new ArrayList<ExceptComponent>();
          for (ExceptComponent i : except)
            dst.except.add(i.copy());
        };
        dst.content = content == null ? null : content.copy();
        return dst;
      }

      protected Consent typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof Consent))
          return false;
        Consent o = (Consent) other;
        return compareDeep(identifier, o.identifier, true) && compareDeep(status, o.status, true) && compareDeep(type, o.type, true)
           && compareDeep(issued, o.issued, true) && compareDeep(applies, o.applies, true) && compareDeep(patient, o.patient, true)
           && compareDeep(authority, o.authority, true) && compareDeep(domain, o.domain, true) && compareDeep(recipient, o.recipient, true)
           && compareDeep(grantor, o.grantor, true) && compareDeep(data, o.data, true) && compareDeep(action, o.action, true)
           && compareDeep(except, o.except, true) && compareDeep(content, o.content, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof Consent))
          return false;
        Consent o = (Consent) other;
        return compareValues(status, o.status, true) && compareValues(issued, o.issued, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, status, type
          , issued, applies, patient, authority, domain, recipient, grantor, data, action
          , except, content);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Consent;
   }

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>The identity of the consent</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Consent.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="Consent.identifier", description="The identity of the consent", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>The identity of the consent</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Consent.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);

 /**
   * Search parameter: <b>data</b>
   * <p>
   * Description: <b>The identity of the topic of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.data</b><br>
   * </p>
   */
  @SearchParamDefinition(name="data", path="Consent.data", description="The identity of the topic of the consent", type="reference" )
  public static final String SP_DATA = "data";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>data</b>
   * <p>
   * Description: <b>The identity of the topic of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.data</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam DATA = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_DATA);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Consent:data</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_DATA = new ca.uhn.fhir.model.api.Include("Consent:data").toLocked();

 /**
   * Search parameter: <b>applies</b>
   * <p>
   * Description: <b>The effective date/time range of the consent</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Consent.applies</b><br>
   * </p>
   */
  @SearchParamDefinition(name="applies", path="Consent.applies", description="The effective date/time range of the consent", type="date" )
  public static final String SP_APPLIES = "applies";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>applies</b>
   * <p>
   * Description: <b>The effective date/time range of the consent</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Consent.applies</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam APPLIES = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_APPLIES);

 /**
   * Search parameter: <b>patient</b>
   * <p>
   * Description: <b>The identity of the patient of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.patient</b><br>
   * </p>
   */
  @SearchParamDefinition(name="patient", path="Consent.patient", description="The identity of the patient of the consent", type="reference" )
  public static final String SP_PATIENT = "patient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>patient</b>
   * <p>
   * Description: <b>The identity of the patient of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.patient</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PATIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PATIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Consent:patient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PATIENT = new ca.uhn.fhir.model.api.Include("Consent:patient").toLocked();

 /**
   * Search parameter: <b>authority</b>
   * <p>
   * Description: <b>The authority of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.authority</b><br>
   * </p>
   */
  @SearchParamDefinition(name="authority", path="Consent.authority", description="The authority of the consent", type="reference" )
  public static final String SP_AUTHORITY = "authority";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>authority</b>
   * <p>
   * Description: <b>The authority of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.authority</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam AUTHORITY = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_AUTHORITY);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Consent:authority</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_AUTHORITY = new ca.uhn.fhir.model.api.Include("Consent:authority").toLocked();

 /**
   * Search parameter: <b>domain</b>
   * <p>
   * Description: <b>The domain of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.domain</b><br>
   * </p>
   */
  @SearchParamDefinition(name="domain", path="Consent.domain", description="The domain of the consent", type="reference" )
  public static final String SP_DOMAIN = "domain";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>domain</b>
   * <p>
   * Description: <b>The domain of the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.domain</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam DOMAIN = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_DOMAIN);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Consent:domain</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_DOMAIN = new ca.uhn.fhir.model.api.Include("Consent:domain").toLocked();

 /**
   * Search parameter: <b>recipient</b>
   * <p>
   * Description: <b>Recipient Agent authorized by the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.recipient</b><br>
   * </p>
   */
  @SearchParamDefinition(name="recipient", path="Consent.recipient", description="Recipient Agent authorized by the consent", type="reference" )
  public static final String SP_RECIPIENT = "recipient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>recipient</b>
   * <p>
   * Description: <b>Recipient Agent authorized by the consent</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Consent.recipient</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam RECIPIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_RECIPIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Consent:recipient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_RECIPIENT = new ca.uhn.fhir.model.api.Include("Consent:recipient").toLocked();

 /**
   * Search parameter: <b>issued</b>
   * <p>
   * Description: <b>The date/time the consent was issued</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Consent.issued</b><br>
   * </p>
   */
  @SearchParamDefinition(name="issued", path="Consent.issued", description="The date/time the consent was issued", type="date" )
  public static final String SP_ISSUED = "issued";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>issued</b>
   * <p>
   * Description: <b>The date/time the consent was issued</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Consent.issued</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam ISSUED = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_ISSUED);

 /**
   * Search parameter: <b>type</b>
   * <p>
   * Description: <b>The type of the consent</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Consent.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="type", path="Consent.type", description="The type of the consent", type="token" )
  public static final String SP_TYPE = "type";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>type</b>
   * <p>
   * Description: <b>The type of the consent</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Consent.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam TYPE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_TYPE);

 /**
   * Search parameter: <b>status</b>
   * <p>
   * Description: <b>The status of the consent</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Consent.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="status", path="Consent.status", description="The status of the consent", type="token" )
  public static final String SP_STATUS = "status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>status</b>
   * <p>
   * Description: <b>The status of the consent</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Consent.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_STATUS);


}

