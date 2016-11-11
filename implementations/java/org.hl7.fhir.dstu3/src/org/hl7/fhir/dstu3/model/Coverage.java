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

// Generated on Fri, Nov 11, 2016 15:02+1100 for FHIR v1.7.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.exceptions.FHIRException;
/**
 * Financial instrument which may be used to pay for or reimburse health care products and services.
 */
@ResourceDef(name="Coverage", profile="http://hl7.org/fhir/Profile/Coverage")
public class Coverage extends DomainResource {

    public enum CoverageStatus {
        /**
         * The resource instance is currently in-force.
         */
        ACTIVE, 
        /**
         * The resource instance is withdrawn, rescinded or reversed.
         */
        CANCELLED, 
        /**
         * A new resource instance the contents of which is not complete.
         */
        DRAFT, 
        /**
         * The resource instance was entered in error.
         */
        ENTEREDINERROR, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static CoverageStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("entered-in-error".equals(codeString))
          return ENTEREDINERROR;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown CoverageStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ACTIVE: return "active";
            case CANCELLED: return "cancelled";
            case DRAFT: return "draft";
            case ENTEREDINERROR: return "entered-in-error";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case ACTIVE: return "http://hl7.org/fhir/coverage-status";
            case CANCELLED: return "http://hl7.org/fhir/coverage-status";
            case DRAFT: return "http://hl7.org/fhir/coverage-status";
            case ENTEREDINERROR: return "http://hl7.org/fhir/coverage-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ACTIVE: return "The resource instance is currently in-force.";
            case CANCELLED: return "The resource instance is withdrawn, rescinded or reversed.";
            case DRAFT: return "A new resource instance the contents of which is not complete.";
            case ENTEREDINERROR: return "The resource instance was entered in error.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ACTIVE: return "Active";
            case CANCELLED: return "Cancelled";
            case DRAFT: return "Draft";
            case ENTEREDINERROR: return "Entered In Error";
            default: return "?";
          }
        }
    }

  public static class CoverageStatusEnumFactory implements EnumFactory<CoverageStatus> {
    public CoverageStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return CoverageStatus.ACTIVE;
        if ("cancelled".equals(codeString))
          return CoverageStatus.CANCELLED;
        if ("draft".equals(codeString))
          return CoverageStatus.DRAFT;
        if ("entered-in-error".equals(codeString))
          return CoverageStatus.ENTEREDINERROR;
        throw new IllegalArgumentException("Unknown CoverageStatus code '"+codeString+"'");
        }
        public Enumeration<CoverageStatus> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("active".equals(codeString))
          return new Enumeration<CoverageStatus>(this, CoverageStatus.ACTIVE);
        if ("cancelled".equals(codeString))
          return new Enumeration<CoverageStatus>(this, CoverageStatus.CANCELLED);
        if ("draft".equals(codeString))
          return new Enumeration<CoverageStatus>(this, CoverageStatus.DRAFT);
        if ("entered-in-error".equals(codeString))
          return new Enumeration<CoverageStatus>(this, CoverageStatus.ENTEREDINERROR);
        throw new FHIRException("Unknown CoverageStatus code '"+codeString+"'");
        }
    public String toCode(CoverageStatus code) {
      if (code == CoverageStatus.ACTIVE)
        return "active";
      if (code == CoverageStatus.CANCELLED)
        return "cancelled";
      if (code == CoverageStatus.DRAFT)
        return "draft";
      if (code == CoverageStatus.ENTEREDINERROR)
        return "entered-in-error";
      return "?";
      }
    public String toSystem(CoverageStatus code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class GroupComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        @Child(name = "group", type = {StringType.class}, order=1, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="An identifier for the group", formalDefinition="Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID." )
        protected StringType group;

        /**
         * A short description for the group.
         */
        @Child(name = "groupDisplay", type = {StringType.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Display text for an identifier for the group", formalDefinition="A short description for the group." )
        protected StringType groupDisplay;

        /**
         * Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        @Child(name = "subGroup", type = {StringType.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="An identifier for the subsection of the group", formalDefinition="Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID." )
        protected StringType subGroup;

        /**
         * A short description for the subgroup.
         */
        @Child(name = "subGroupDisplay", type = {StringType.class}, order=4, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Display text for the subsection of the group", formalDefinition="A short description for the subgroup." )
        protected StringType subGroupDisplay;

        /**
         * Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        @Child(name = "plan", type = {StringType.class}, order=5, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="An identifier for the plan", formalDefinition="Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID." )
        protected StringType plan;

        /**
         * A short description for the plan.
         */
        @Child(name = "planDisplay", type = {StringType.class}, order=6, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Display text for the plan", formalDefinition="A short description for the plan." )
        protected StringType planDisplay;

        /**
         * Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a specific employer group within a class of employers. May be referred to as a Section or Division ID.
         */
        @Child(name = "subPlan", type = {StringType.class}, order=7, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="An identifier for the subsection of the plan", formalDefinition="Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a specific employer group within a class of employers. May be referred to as a Section or Division ID." )
        protected StringType subPlan;

        /**
         * A short description for the subplan.
         */
        @Child(name = "subPlanDisplay", type = {StringType.class}, order=8, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Display text for the subsection of the plan", formalDefinition="A short description for the subplan." )
        protected StringType subPlanDisplay;

        /**
         * Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group.
         */
        @Child(name = "class", type = {StringType.class}, order=9, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="An identifier for the class", formalDefinition="Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group." )
        protected StringType class_;

        /**
         * A short description for the class.
         */
        @Child(name = "classDisplay", type = {StringType.class}, order=10, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Display text for the class", formalDefinition="A short description for the class." )
        protected StringType classDisplay;

        /**
         * Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a subclass of coverage or employer group.
         */
        @Child(name = "subClass", type = {StringType.class}, order=11, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="An identifier for the subsection of the class", formalDefinition="Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a subclass of coverage or employer group." )
        protected StringType subClass;

        /**
         * A short description for the subclass.
         */
        @Child(name = "subClassDisplay", type = {StringType.class}, order=12, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Display text for the subsection of the subclass", formalDefinition="A short description for the subclass." )
        protected StringType subClassDisplay;

        private static final long serialVersionUID = -13147121L;

    /**
     * Constructor
     */
      public GroupComponent() {
        super();
      }

        /**
         * @return {@link #group} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.). This is the underlying object with id, value and extensions. The accessor "getGroup" gives direct access to the value
         */
        public StringType getGroupElement() { 
          if (this.group == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.group");
            else if (Configuration.doAutoCreate())
              this.group = new StringType(); // bb
          return this.group;
        }

        public boolean hasGroupElement() { 
          return this.group != null && !this.group.isEmpty();
        }

        public boolean hasGroup() { 
          return this.group != null && !this.group.isEmpty();
        }

        /**
         * @param value {@link #group} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.). This is the underlying object with id, value and extensions. The accessor "getGroup" gives direct access to the value
         */
        public GroupComponent setGroupElement(StringType value) { 
          this.group = value;
          return this;
        }

        /**
         * @return Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        public String getGroup() { 
          return this.group == null ? null : this.group.getValue();
        }

        /**
         * @param value Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        public GroupComponent setGroup(String value) { 
          if (Utilities.noString(value))
            this.group = null;
          else {
            if (this.group == null)
              this.group = new StringType();
            this.group.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #groupDisplay} (A short description for the group.). This is the underlying object with id, value and extensions. The accessor "getGroupDisplay" gives direct access to the value
         */
        public StringType getGroupDisplayElement() { 
          if (this.groupDisplay == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.groupDisplay");
            else if (Configuration.doAutoCreate())
              this.groupDisplay = new StringType(); // bb
          return this.groupDisplay;
        }

        public boolean hasGroupDisplayElement() { 
          return this.groupDisplay != null && !this.groupDisplay.isEmpty();
        }

        public boolean hasGroupDisplay() { 
          return this.groupDisplay != null && !this.groupDisplay.isEmpty();
        }

        /**
         * @param value {@link #groupDisplay} (A short description for the group.). This is the underlying object with id, value and extensions. The accessor "getGroupDisplay" gives direct access to the value
         */
        public GroupComponent setGroupDisplayElement(StringType value) { 
          this.groupDisplay = value;
          return this;
        }

        /**
         * @return A short description for the group.
         */
        public String getGroupDisplay() { 
          return this.groupDisplay == null ? null : this.groupDisplay.getValue();
        }

        /**
         * @param value A short description for the group.
         */
        public GroupComponent setGroupDisplay(String value) { 
          if (Utilities.noString(value))
            this.groupDisplay = null;
          else {
            if (this.groupDisplay == null)
              this.groupDisplay = new StringType();
            this.groupDisplay.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subGroup} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.). This is the underlying object with id, value and extensions. The accessor "getSubGroup" gives direct access to the value
         */
        public StringType getSubGroupElement() { 
          if (this.subGroup == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.subGroup");
            else if (Configuration.doAutoCreate())
              this.subGroup = new StringType(); // bb
          return this.subGroup;
        }

        public boolean hasSubGroupElement() { 
          return this.subGroup != null && !this.subGroup.isEmpty();
        }

        public boolean hasSubGroup() { 
          return this.subGroup != null && !this.subGroup.isEmpty();
        }

        /**
         * @param value {@link #subGroup} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.). This is the underlying object with id, value and extensions. The accessor "getSubGroup" gives direct access to the value
         */
        public GroupComponent setSubGroupElement(StringType value) { 
          this.subGroup = value;
          return this;
        }

        /**
         * @return Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        public String getSubGroup() { 
          return this.subGroup == null ? null : this.subGroup.getValue();
        }

        /**
         * @param value Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        public GroupComponent setSubGroup(String value) { 
          if (Utilities.noString(value))
            this.subGroup = null;
          else {
            if (this.subGroup == null)
              this.subGroup = new StringType();
            this.subGroup.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subGroupDisplay} (A short description for the subgroup.). This is the underlying object with id, value and extensions. The accessor "getSubGroupDisplay" gives direct access to the value
         */
        public StringType getSubGroupDisplayElement() { 
          if (this.subGroupDisplay == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.subGroupDisplay");
            else if (Configuration.doAutoCreate())
              this.subGroupDisplay = new StringType(); // bb
          return this.subGroupDisplay;
        }

        public boolean hasSubGroupDisplayElement() { 
          return this.subGroupDisplay != null && !this.subGroupDisplay.isEmpty();
        }

        public boolean hasSubGroupDisplay() { 
          return this.subGroupDisplay != null && !this.subGroupDisplay.isEmpty();
        }

        /**
         * @param value {@link #subGroupDisplay} (A short description for the subgroup.). This is the underlying object with id, value and extensions. The accessor "getSubGroupDisplay" gives direct access to the value
         */
        public GroupComponent setSubGroupDisplayElement(StringType value) { 
          this.subGroupDisplay = value;
          return this;
        }

        /**
         * @return A short description for the subgroup.
         */
        public String getSubGroupDisplay() { 
          return this.subGroupDisplay == null ? null : this.subGroupDisplay.getValue();
        }

        /**
         * @param value A short description for the subgroup.
         */
        public GroupComponent setSubGroupDisplay(String value) { 
          if (Utilities.noString(value))
            this.subGroupDisplay = null;
          else {
            if (this.subGroupDisplay == null)
              this.subGroupDisplay = new StringType();
            this.subGroupDisplay.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #plan} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.). This is the underlying object with id, value and extensions. The accessor "getPlan" gives direct access to the value
         */
        public StringType getPlanElement() { 
          if (this.plan == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.plan");
            else if (Configuration.doAutoCreate())
              this.plan = new StringType(); // bb
          return this.plan;
        }

        public boolean hasPlanElement() { 
          return this.plan != null && !this.plan.isEmpty();
        }

        public boolean hasPlan() { 
          return this.plan != null && !this.plan.isEmpty();
        }

        /**
         * @param value {@link #plan} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.). This is the underlying object with id, value and extensions. The accessor "getPlan" gives direct access to the value
         */
        public GroupComponent setPlanElement(StringType value) { 
          this.plan = value;
          return this;
        }

        /**
         * @return Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        public String getPlan() { 
          return this.plan == null ? null : this.plan.getValue();
        }

        /**
         * @param value Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.
         */
        public GroupComponent setPlan(String value) { 
          if (Utilities.noString(value))
            this.plan = null;
          else {
            if (this.plan == null)
              this.plan = new StringType();
            this.plan.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #planDisplay} (A short description for the plan.). This is the underlying object with id, value and extensions. The accessor "getPlanDisplay" gives direct access to the value
         */
        public StringType getPlanDisplayElement() { 
          if (this.planDisplay == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.planDisplay");
            else if (Configuration.doAutoCreate())
              this.planDisplay = new StringType(); // bb
          return this.planDisplay;
        }

        public boolean hasPlanDisplayElement() { 
          return this.planDisplay != null && !this.planDisplay.isEmpty();
        }

        public boolean hasPlanDisplay() { 
          return this.planDisplay != null && !this.planDisplay.isEmpty();
        }

        /**
         * @param value {@link #planDisplay} (A short description for the plan.). This is the underlying object with id, value and extensions. The accessor "getPlanDisplay" gives direct access to the value
         */
        public GroupComponent setPlanDisplayElement(StringType value) { 
          this.planDisplay = value;
          return this;
        }

        /**
         * @return A short description for the plan.
         */
        public String getPlanDisplay() { 
          return this.planDisplay == null ? null : this.planDisplay.getValue();
        }

        /**
         * @param value A short description for the plan.
         */
        public GroupComponent setPlanDisplay(String value) { 
          if (Utilities.noString(value))
            this.planDisplay = null;
          else {
            if (this.planDisplay == null)
              this.planDisplay = new StringType();
            this.planDisplay.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subPlan} (Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a specific employer group within a class of employers. May be referred to as a Section or Division ID.). This is the underlying object with id, value and extensions. The accessor "getSubPlan" gives direct access to the value
         */
        public StringType getSubPlanElement() { 
          if (this.subPlan == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.subPlan");
            else if (Configuration.doAutoCreate())
              this.subPlan = new StringType(); // bb
          return this.subPlan;
        }

        public boolean hasSubPlanElement() { 
          return this.subPlan != null && !this.subPlan.isEmpty();
        }

        public boolean hasSubPlan() { 
          return this.subPlan != null && !this.subPlan.isEmpty();
        }

        /**
         * @param value {@link #subPlan} (Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a specific employer group within a class of employers. May be referred to as a Section or Division ID.). This is the underlying object with id, value and extensions. The accessor "getSubPlan" gives direct access to the value
         */
        public GroupComponent setSubPlanElement(StringType value) { 
          this.subPlan = value;
          return this;
        }

        /**
         * @return Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a specific employer group within a class of employers. May be referred to as a Section or Division ID.
         */
        public String getSubPlan() { 
          return this.subPlan == null ? null : this.subPlan.getValue();
        }

        /**
         * @param value Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a specific employer group within a class of employers. May be referred to as a Section or Division ID.
         */
        public GroupComponent setSubPlan(String value) { 
          if (Utilities.noString(value))
            this.subPlan = null;
          else {
            if (this.subPlan == null)
              this.subPlan = new StringType();
            this.subPlan.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subPlanDisplay} (A short description for the subplan.). This is the underlying object with id, value and extensions. The accessor "getSubPlanDisplay" gives direct access to the value
         */
        public StringType getSubPlanDisplayElement() { 
          if (this.subPlanDisplay == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.subPlanDisplay");
            else if (Configuration.doAutoCreate())
              this.subPlanDisplay = new StringType(); // bb
          return this.subPlanDisplay;
        }

        public boolean hasSubPlanDisplayElement() { 
          return this.subPlanDisplay != null && !this.subPlanDisplay.isEmpty();
        }

        public boolean hasSubPlanDisplay() { 
          return this.subPlanDisplay != null && !this.subPlanDisplay.isEmpty();
        }

        /**
         * @param value {@link #subPlanDisplay} (A short description for the subplan.). This is the underlying object with id, value and extensions. The accessor "getSubPlanDisplay" gives direct access to the value
         */
        public GroupComponent setSubPlanDisplayElement(StringType value) { 
          this.subPlanDisplay = value;
          return this;
        }

        /**
         * @return A short description for the subplan.
         */
        public String getSubPlanDisplay() { 
          return this.subPlanDisplay == null ? null : this.subPlanDisplay.getValue();
        }

        /**
         * @param value A short description for the subplan.
         */
        public GroupComponent setSubPlanDisplay(String value) { 
          if (Utilities.noString(value))
            this.subPlanDisplay = null;
          else {
            if (this.subPlanDisplay == null)
              this.subPlanDisplay = new StringType();
            this.subPlanDisplay.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #class_} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group.). This is the underlying object with id, value and extensions. The accessor "getClass_" gives direct access to the value
         */
        public StringType getClass_Element() { 
          if (this.class_ == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.class_");
            else if (Configuration.doAutoCreate())
              this.class_ = new StringType(); // bb
          return this.class_;
        }

        public boolean hasClass_Element() { 
          return this.class_ != null && !this.class_.isEmpty();
        }

        public boolean hasClass_() { 
          return this.class_ != null && !this.class_.isEmpty();
        }

        /**
         * @param value {@link #class_} (Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group.). This is the underlying object with id, value and extensions. The accessor "getClass_" gives direct access to the value
         */
        public GroupComponent setClass_Element(StringType value) { 
          this.class_ = value;
          return this;
        }

        /**
         * @return Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group.
         */
        public String getClass_() { 
          return this.class_ == null ? null : this.class_.getValue();
        }

        /**
         * @param value Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group.
         */
        public GroupComponent setClass_(String value) { 
          if (Utilities.noString(value))
            this.class_ = null;
          else {
            if (this.class_ == null)
              this.class_ = new StringType();
            this.class_.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #classDisplay} (A short description for the class.). This is the underlying object with id, value and extensions. The accessor "getClassDisplay" gives direct access to the value
         */
        public StringType getClassDisplayElement() { 
          if (this.classDisplay == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.classDisplay");
            else if (Configuration.doAutoCreate())
              this.classDisplay = new StringType(); // bb
          return this.classDisplay;
        }

        public boolean hasClassDisplayElement() { 
          return this.classDisplay != null && !this.classDisplay.isEmpty();
        }

        public boolean hasClassDisplay() { 
          return this.classDisplay != null && !this.classDisplay.isEmpty();
        }

        /**
         * @param value {@link #classDisplay} (A short description for the class.). This is the underlying object with id, value and extensions. The accessor "getClassDisplay" gives direct access to the value
         */
        public GroupComponent setClassDisplayElement(StringType value) { 
          this.classDisplay = value;
          return this;
        }

        /**
         * @return A short description for the class.
         */
        public String getClassDisplay() { 
          return this.classDisplay == null ? null : this.classDisplay.getValue();
        }

        /**
         * @param value A short description for the class.
         */
        public GroupComponent setClassDisplay(String value) { 
          if (Utilities.noString(value))
            this.classDisplay = null;
          else {
            if (this.classDisplay == null)
              this.classDisplay = new StringType();
            this.classDisplay.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subClass} (Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a subclass of coverage or employer group.). This is the underlying object with id, value and extensions. The accessor "getSubClass" gives direct access to the value
         */
        public StringType getSubClassElement() { 
          if (this.subClass == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.subClass");
            else if (Configuration.doAutoCreate())
              this.subClass = new StringType(); // bb
          return this.subClass;
        }

        public boolean hasSubClassElement() { 
          return this.subClass != null && !this.subClass.isEmpty();
        }

        public boolean hasSubClass() { 
          return this.subClass != null && !this.subClass.isEmpty();
        }

        /**
         * @param value {@link #subClass} (Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a subclass of coverage or employer group.). This is the underlying object with id, value and extensions. The accessor "getSubClass" gives direct access to the value
         */
        public GroupComponent setSubClassElement(StringType value) { 
          this.subClass = value;
          return this;
        }

        /**
         * @return Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a subclass of coverage or employer group.
         */
        public String getSubClass() { 
          return this.subClass == null ? null : this.subClass.getValue();
        }

        /**
         * @param value Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a subclass of coverage or employer group.
         */
        public GroupComponent setSubClass(String value) { 
          if (Utilities.noString(value))
            this.subClass = null;
          else {
            if (this.subClass == null)
              this.subClass = new StringType();
            this.subClass.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #subClassDisplay} (A short description for the subclass.). This is the underlying object with id, value and extensions. The accessor "getSubClassDisplay" gives direct access to the value
         */
        public StringType getSubClassDisplayElement() { 
          if (this.subClassDisplay == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GroupComponent.subClassDisplay");
            else if (Configuration.doAutoCreate())
              this.subClassDisplay = new StringType(); // bb
          return this.subClassDisplay;
        }

        public boolean hasSubClassDisplayElement() { 
          return this.subClassDisplay != null && !this.subClassDisplay.isEmpty();
        }

        public boolean hasSubClassDisplay() { 
          return this.subClassDisplay != null && !this.subClassDisplay.isEmpty();
        }

        /**
         * @param value {@link #subClassDisplay} (A short description for the subclass.). This is the underlying object with id, value and extensions. The accessor "getSubClassDisplay" gives direct access to the value
         */
        public GroupComponent setSubClassDisplayElement(StringType value) { 
          this.subClassDisplay = value;
          return this;
        }

        /**
         * @return A short description for the subclass.
         */
        public String getSubClassDisplay() { 
          return this.subClassDisplay == null ? null : this.subClassDisplay.getValue();
        }

        /**
         * @param value A short description for the subclass.
         */
        public GroupComponent setSubClassDisplay(String value) { 
          if (Utilities.noString(value))
            this.subClassDisplay = null;
          else {
            if (this.subClassDisplay == null)
              this.subClassDisplay = new StringType();
            this.subClassDisplay.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("group", "string", "Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.", 0, java.lang.Integer.MAX_VALUE, group));
          childrenList.add(new Property("groupDisplay", "string", "A short description for the group.", 0, java.lang.Integer.MAX_VALUE, groupDisplay));
          childrenList.add(new Property("subGroup", "string", "Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.", 0, java.lang.Integer.MAX_VALUE, subGroup));
          childrenList.add(new Property("subGroupDisplay", "string", "A short description for the subgroup.", 0, java.lang.Integer.MAX_VALUE, subGroupDisplay));
          childrenList.add(new Property("plan", "string", "Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group. May also be referred to as a Policy or Group ID.", 0, java.lang.Integer.MAX_VALUE, plan));
          childrenList.add(new Property("planDisplay", "string", "A short description for the plan.", 0, java.lang.Integer.MAX_VALUE, planDisplay));
          childrenList.add(new Property("subPlan", "string", "Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a specific employer group within a class of employers. May be referred to as a Section or Division ID.", 0, java.lang.Integer.MAX_VALUE, subPlan));
          childrenList.add(new Property("subPlanDisplay", "string", "A short description for the subplan.", 0, java.lang.Integer.MAX_VALUE, subPlanDisplay));
          childrenList.add(new Property("class", "string", "Identifies a style or collective of coverage issues by the underwriter, for example may be used to identify a class of coverage or employer group.", 0, java.lang.Integer.MAX_VALUE, class_));
          childrenList.add(new Property("classDisplay", "string", "A short description for the class.", 0, java.lang.Integer.MAX_VALUE, classDisplay));
          childrenList.add(new Property("subClass", "string", "Identifies a sub-style or sub-collective of coverage issues by the underwriter, for example may be used to identify a subclass of coverage or employer group.", 0, java.lang.Integer.MAX_VALUE, subClass));
          childrenList.add(new Property("subClassDisplay", "string", "A short description for the subclass.", 0, java.lang.Integer.MAX_VALUE, subClassDisplay));
        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 98629247: /*group*/ return this.group == null ? new Base[0] : new Base[] {this.group}; // StringType
        case 1322335555: /*groupDisplay*/ return this.groupDisplay == null ? new Base[0] : new Base[] {this.groupDisplay}; // StringType
        case -2101792737: /*subGroup*/ return this.subGroup == null ? new Base[0] : new Base[] {this.subGroup}; // StringType
        case 1051914147: /*subGroupDisplay*/ return this.subGroupDisplay == null ? new Base[0] : new Base[] {this.subGroupDisplay}; // StringType
        case 3443497: /*plan*/ return this.plan == null ? new Base[0] : new Base[] {this.plan}; // StringType
        case -896076455: /*planDisplay*/ return this.planDisplay == null ? new Base[0] : new Base[] {this.planDisplay}; // StringType
        case -1868653175: /*subPlan*/ return this.subPlan == null ? new Base[0] : new Base[] {this.subPlan}; // StringType
        case -1736083719: /*subPlanDisplay*/ return this.subPlanDisplay == null ? new Base[0] : new Base[] {this.subPlanDisplay}; // StringType
        case 94742904: /*class*/ return this.class_ == null ? new Base[0] : new Base[] {this.class_}; // StringType
        case 1707405354: /*classDisplay*/ return this.classDisplay == null ? new Base[0] : new Base[] {this.classDisplay}; // StringType
        case -2105679080: /*subClass*/ return this.subClass == null ? new Base[0] : new Base[] {this.subClass}; // StringType
        case 1436983946: /*subClassDisplay*/ return this.subClassDisplay == null ? new Base[0] : new Base[] {this.subClassDisplay}; // StringType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 98629247: // group
          this.group = castToString(value); // StringType
          break;
        case 1322335555: // groupDisplay
          this.groupDisplay = castToString(value); // StringType
          break;
        case -2101792737: // subGroup
          this.subGroup = castToString(value); // StringType
          break;
        case 1051914147: // subGroupDisplay
          this.subGroupDisplay = castToString(value); // StringType
          break;
        case 3443497: // plan
          this.plan = castToString(value); // StringType
          break;
        case -896076455: // planDisplay
          this.planDisplay = castToString(value); // StringType
          break;
        case -1868653175: // subPlan
          this.subPlan = castToString(value); // StringType
          break;
        case -1736083719: // subPlanDisplay
          this.subPlanDisplay = castToString(value); // StringType
          break;
        case 94742904: // class
          this.class_ = castToString(value); // StringType
          break;
        case 1707405354: // classDisplay
          this.classDisplay = castToString(value); // StringType
          break;
        case -2105679080: // subClass
          this.subClass = castToString(value); // StringType
          break;
        case 1436983946: // subClassDisplay
          this.subClassDisplay = castToString(value); // StringType
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("group"))
          this.group = castToString(value); // StringType
        else if (name.equals("groupDisplay"))
          this.groupDisplay = castToString(value); // StringType
        else if (name.equals("subGroup"))
          this.subGroup = castToString(value); // StringType
        else if (name.equals("subGroupDisplay"))
          this.subGroupDisplay = castToString(value); // StringType
        else if (name.equals("plan"))
          this.plan = castToString(value); // StringType
        else if (name.equals("planDisplay"))
          this.planDisplay = castToString(value); // StringType
        else if (name.equals("subPlan"))
          this.subPlan = castToString(value); // StringType
        else if (name.equals("subPlanDisplay"))
          this.subPlanDisplay = castToString(value); // StringType
        else if (name.equals("class"))
          this.class_ = castToString(value); // StringType
        else if (name.equals("classDisplay"))
          this.classDisplay = castToString(value); // StringType
        else if (name.equals("subClass"))
          this.subClass = castToString(value); // StringType
        else if (name.equals("subClassDisplay"))
          this.subClassDisplay = castToString(value); // StringType
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 98629247: throw new FHIRException("Cannot make property group as it is not a complex type"); // StringType
        case 1322335555: throw new FHIRException("Cannot make property groupDisplay as it is not a complex type"); // StringType
        case -2101792737: throw new FHIRException("Cannot make property subGroup as it is not a complex type"); // StringType
        case 1051914147: throw new FHIRException("Cannot make property subGroupDisplay as it is not a complex type"); // StringType
        case 3443497: throw new FHIRException("Cannot make property plan as it is not a complex type"); // StringType
        case -896076455: throw new FHIRException("Cannot make property planDisplay as it is not a complex type"); // StringType
        case -1868653175: throw new FHIRException("Cannot make property subPlan as it is not a complex type"); // StringType
        case -1736083719: throw new FHIRException("Cannot make property subPlanDisplay as it is not a complex type"); // StringType
        case 94742904: throw new FHIRException("Cannot make property class as it is not a complex type"); // StringType
        case 1707405354: throw new FHIRException("Cannot make property classDisplay as it is not a complex type"); // StringType
        case -2105679080: throw new FHIRException("Cannot make property subClass as it is not a complex type"); // StringType
        case 1436983946: throw new FHIRException("Cannot make property subClassDisplay as it is not a complex type"); // StringType
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("group")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.group");
        }
        else if (name.equals("groupDisplay")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.groupDisplay");
        }
        else if (name.equals("subGroup")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.subGroup");
        }
        else if (name.equals("subGroupDisplay")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.subGroupDisplay");
        }
        else if (name.equals("plan")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.plan");
        }
        else if (name.equals("planDisplay")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.planDisplay");
        }
        else if (name.equals("subPlan")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.subPlan");
        }
        else if (name.equals("subPlanDisplay")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.subPlanDisplay");
        }
        else if (name.equals("class")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.class");
        }
        else if (name.equals("classDisplay")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.classDisplay");
        }
        else if (name.equals("subClass")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.subClass");
        }
        else if (name.equals("subClassDisplay")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.subClassDisplay");
        }
        else
          return super.addChild(name);
      }

      public GroupComponent copy() {
        GroupComponent dst = new GroupComponent();
        copyValues(dst);
        dst.group = group == null ? null : group.copy();
        dst.groupDisplay = groupDisplay == null ? null : groupDisplay.copy();
        dst.subGroup = subGroup == null ? null : subGroup.copy();
        dst.subGroupDisplay = subGroupDisplay == null ? null : subGroupDisplay.copy();
        dst.plan = plan == null ? null : plan.copy();
        dst.planDisplay = planDisplay == null ? null : planDisplay.copy();
        dst.subPlan = subPlan == null ? null : subPlan.copy();
        dst.subPlanDisplay = subPlanDisplay == null ? null : subPlanDisplay.copy();
        dst.class_ = class_ == null ? null : class_.copy();
        dst.classDisplay = classDisplay == null ? null : classDisplay.copy();
        dst.subClass = subClass == null ? null : subClass.copy();
        dst.subClassDisplay = subClassDisplay == null ? null : subClassDisplay.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof GroupComponent))
          return false;
        GroupComponent o = (GroupComponent) other;
        return compareDeep(group, o.group, true) && compareDeep(groupDisplay, o.groupDisplay, true) && compareDeep(subGroup, o.subGroup, true)
           && compareDeep(subGroupDisplay, o.subGroupDisplay, true) && compareDeep(plan, o.plan, true) && compareDeep(planDisplay, o.planDisplay, true)
           && compareDeep(subPlan, o.subPlan, true) && compareDeep(subPlanDisplay, o.subPlanDisplay, true)
           && compareDeep(class_, o.class_, true) && compareDeep(classDisplay, o.classDisplay, true) && compareDeep(subClass, o.subClass, true)
           && compareDeep(subClassDisplay, o.subClassDisplay, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof GroupComponent))
          return false;
        GroupComponent o = (GroupComponent) other;
        return compareValues(group, o.group, true) && compareValues(groupDisplay, o.groupDisplay, true) && compareValues(subGroup, o.subGroup, true)
           && compareValues(subGroupDisplay, o.subGroupDisplay, true) && compareValues(plan, o.plan, true) && compareValues(planDisplay, o.planDisplay, true)
           && compareValues(subPlan, o.subPlan, true) && compareValues(subPlanDisplay, o.subPlanDisplay, true)
           && compareValues(class_, o.class_, true) && compareValues(classDisplay, o.classDisplay, true) && compareValues(subClass, o.subClass, true)
           && compareValues(subClassDisplay, o.subClassDisplay, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(group, groupDisplay, subGroup
          , subGroupDisplay, plan, planDisplay, subPlan, subPlanDisplay, class_, classDisplay
          , subClass, subClassDisplay);
      }

  public String fhirType() {
    return "Coverage.group";

  }

  }

    /**
     * The main (and possibly only) identifier for the coverage - often referred to as a Member Id, Subscriber Id, Certificate number or Personal Health Number or Case ID.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="The primary coverage ID", formalDefinition="The main (and possibly only) identifier for the coverage - often referred to as a Member Id, Subscriber Id, Certificate number or Personal Health Number or Case ID." )
    protected List<Identifier> identifier;

    /**
     * The status of the resource instance.
     */
    @Child(name = "status", type = {CodeType.class}, order=1, min=0, max=1, modifier=true, summary=true)
    @Description(shortDefinition="active | cancelled | draft | entered-in-error", formalDefinition="The status of the resource instance." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/coverage-status")
    protected Enumeration<CoverageStatus> status;

    /**
     * The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements.
     */
    @Child(name = "issuer", type = {Organization.class, Patient.class, RelatedPerson.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Identifier for the plan or agreement issuer", formalDefinition="The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements." )
    protected Reference issuer;

    /**
     * The actual object that is the target of the reference (The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements.)
     */
    protected Resource issuerTarget;

    /**
     * A self, or other, payment agreement not an insurance policy.
     */
    @Child(name = "isAgreement", type = {BooleanType.class}, order=3, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Is a Payment Agreement", formalDefinition="A self, or other, payment agreement not an insurance policy." )
    protected BooleanType isAgreement;

    /**
     * Time period during which the coverage is in force. A missing start date indicates the start date isn't known, a missing end date means the coverage is continuing to be in force.
     */
    @Child(name = "period", type = {Period.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Coverage start and end dates", formalDefinition="Time period during which the coverage is in force. A missing start date indicates the start date isn't known, a missing end date means the coverage is continuing to be in force." )
    protected Period period;

    /**
     * The type of coverage: social program, medical plan, accident coverage (workers compensation, auto), group health.
     */
    @Child(name = "type", type = {Coding.class}, order=5, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Type of coverage", formalDefinition="The type of coverage: social program, medical plan, accident coverage (workers compensation, auto), group health." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/v3-ActCoverageTypeCode")
    protected Coding type;

    /**
     * The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due.
     */
    @Child(name = "planholder", type = {Patient.class, RelatedPerson.class, Organization.class}, order=6, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Plan holder", formalDefinition="The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due." )
    protected Reference planholder;

    /**
     * The actual object that is the target of the reference (The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due.)
     */
    protected Resource planholderTarget;

    /**
     * The party who benefits from the insurance coverage.
     */
    @Child(name = "beneficiary", type = {Patient.class}, order=7, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Plan Beneficiary", formalDefinition="The party who benefits from the insurance coverage." )
    protected Reference beneficiary;

    /**
     * The actual object that is the target of the reference (The party who benefits from the insurance coverage.)
     */
    protected Patient beneficiaryTarget;

    /**
     * The relationship of beneficiary (patient) (subscriber) to the the planholder.
     */
    @Child(name = "relationship", type = {Coding.class}, order=8, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Beneficiary relationship to Planholder", formalDefinition="The relationship of beneficiary (patient) (subscriber) to the the planholder." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/policyholder-relationship")
    protected Coding relationship;

    /**
     * A suite of underwrite specific classifiers, for example may be used to identify a class of coverage or employer group, Policy, Plan.
     */
    @Child(name = "group", type = {}, order=9, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Additional coverage classifications", formalDefinition="A suite of underwrite specific classifiers, for example may be used to identify a class of coverage or employer group, Policy, Plan." )
    protected GroupComponent group;

    /**
     * A unique identifier for a dependent under the coverage.
     */
    @Child(name = "dependent", type = {PositiveIntType.class}, order=10, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Dependent number", formalDefinition="A unique identifier for a dependent under the coverage." )
    protected PositiveIntType dependent;

    /**
     * An optional counter for a particular instance of the identified coverage which increments upon each renewal.
     */
    @Child(name = "sequence", type = {PositiveIntType.class}, order=11, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="The plan instance or sequence counter", formalDefinition="An optional counter for a particular instance of the identified coverage which increments upon each renewal." )
    protected PositiveIntType sequence;

    /**
     * The identifier for a community of providers.
     */
    @Child(name = "network", type = {StringType.class}, order=12, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Insurer network", formalDefinition="The identifier for a community of providers." )
    protected StringType network;

    /**
     * The policy(s) which constitute this insurance coverage.
     */
    @Child(name = "contract", type = {Contract.class}, order=13, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Contract details", formalDefinition="The policy(s) which constitute this insurance coverage." )
    protected List<Reference> contract;
    /**
     * The actual objects that are the target of the reference (The policy(s) which constitute this insurance coverage.)
     */
    protected List<Contract> contractTarget;


    private static final long serialVersionUID = 996703434L;

  /**
   * Constructor
   */
    public Coverage() {
      super();
    }

    /**
     * @return {@link #identifier} (The main (and possibly only) identifier for the coverage - often referred to as a Member Id, Subscriber Id, Certificate number or Personal Health Number or Case ID.)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Coverage setIdentifier(List<Identifier> theIdentifier) { 
      this.identifier = theIdentifier;
      return this;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    public Coverage addIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #identifier}, creating it if it does not already exist
     */
    public Identifier getIdentifierFirstRep() { 
      if (getIdentifier().isEmpty()) {
        addIdentifier();
      }
      return getIdentifier().get(0);
    }

    /**
     * @return {@link #status} (The status of the resource instance.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<CoverageStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<CoverageStatus>(new CoverageStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (The status of the resource instance.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Coverage setStatusElement(Enumeration<CoverageStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the resource instance.
     */
    public CoverageStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the resource instance.
     */
    public Coverage setStatus(CoverageStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<CoverageStatus>(new CoverageStatusEnumFactory());
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #issuer} (The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements.)
     */
    public Reference getIssuer() { 
      if (this.issuer == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.issuer");
        else if (Configuration.doAutoCreate())
          this.issuer = new Reference(); // cc
      return this.issuer;
    }

    public boolean hasIssuer() { 
      return this.issuer != null && !this.issuer.isEmpty();
    }

    /**
     * @param value {@link #issuer} (The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements.)
     */
    public Coverage setIssuer(Reference value) { 
      this.issuer = value;
      return this;
    }

    /**
     * @return {@link #issuer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements.)
     */
    public Resource getIssuerTarget() { 
      return this.issuerTarget;
    }

    /**
     * @param value {@link #issuer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements.)
     */
    public Coverage setIssuerTarget(Resource value) { 
      this.issuerTarget = value;
      return this;
    }

    /**
     * @return {@link #isAgreement} (A self, or other, payment agreement not an insurance policy.). This is the underlying object with id, value and extensions. The accessor "getIsAgreement" gives direct access to the value
     */
    public BooleanType getIsAgreementElement() { 
      if (this.isAgreement == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.isAgreement");
        else if (Configuration.doAutoCreate())
          this.isAgreement = new BooleanType(); // bb
      return this.isAgreement;
    }

    public boolean hasIsAgreementElement() { 
      return this.isAgreement != null && !this.isAgreement.isEmpty();
    }

    public boolean hasIsAgreement() { 
      return this.isAgreement != null && !this.isAgreement.isEmpty();
    }

    /**
     * @param value {@link #isAgreement} (A self, or other, payment agreement not an insurance policy.). This is the underlying object with id, value and extensions. The accessor "getIsAgreement" gives direct access to the value
     */
    public Coverage setIsAgreementElement(BooleanType value) { 
      this.isAgreement = value;
      return this;
    }

    /**
     * @return A self, or other, payment agreement not an insurance policy.
     */
    public boolean getIsAgreement() { 
      return this.isAgreement == null || this.isAgreement.isEmpty() ? false : this.isAgreement.getValue();
    }

    /**
     * @param value A self, or other, payment agreement not an insurance policy.
     */
    public Coverage setIsAgreement(boolean value) { 
        if (this.isAgreement == null)
          this.isAgreement = new BooleanType();
        this.isAgreement.setValue(value);
      return this;
    }

    /**
     * @return {@link #period} (Time period during which the coverage is in force. A missing start date indicates the start date isn't known, a missing end date means the coverage is continuing to be in force.)
     */
    public Period getPeriod() { 
      if (this.period == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.period");
        else if (Configuration.doAutoCreate())
          this.period = new Period(); // cc
      return this.period;
    }

    public boolean hasPeriod() { 
      return this.period != null && !this.period.isEmpty();
    }

    /**
     * @param value {@link #period} (Time period during which the coverage is in force. A missing start date indicates the start date isn't known, a missing end date means the coverage is continuing to be in force.)
     */
    public Coverage setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #type} (The type of coverage: social program, medical plan, accident coverage (workers compensation, auto), group health.)
     */
    public Coding getType() { 
      if (this.type == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.type");
        else if (Configuration.doAutoCreate())
          this.type = new Coding(); // cc
      return this.type;
    }

    public boolean hasType() { 
      return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (The type of coverage: social program, medical plan, accident coverage (workers compensation, auto), group health.)
     */
    public Coverage setType(Coding value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #planholder} (The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due.)
     */
    public Reference getPlanholder() { 
      if (this.planholder == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.planholder");
        else if (Configuration.doAutoCreate())
          this.planholder = new Reference(); // cc
      return this.planholder;
    }

    public boolean hasPlanholder() { 
      return this.planholder != null && !this.planholder.isEmpty();
    }

    /**
     * @param value {@link #planholder} (The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due.)
     */
    public Coverage setPlanholder(Reference value) { 
      this.planholder = value;
      return this;
    }

    /**
     * @return {@link #planholder} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due.)
     */
    public Resource getPlanholderTarget() { 
      return this.planholderTarget;
    }

    /**
     * @param value {@link #planholder} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due.)
     */
    public Coverage setPlanholderTarget(Resource value) { 
      this.planholderTarget = value;
      return this;
    }

    /**
     * @return {@link #beneficiary} (The party who benefits from the insurance coverage.)
     */
    public Reference getBeneficiary() { 
      if (this.beneficiary == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.beneficiary");
        else if (Configuration.doAutoCreate())
          this.beneficiary = new Reference(); // cc
      return this.beneficiary;
    }

    public boolean hasBeneficiary() { 
      return this.beneficiary != null && !this.beneficiary.isEmpty();
    }

    /**
     * @param value {@link #beneficiary} (The party who benefits from the insurance coverage.)
     */
    public Coverage setBeneficiary(Reference value) { 
      this.beneficiary = value;
      return this;
    }

    /**
     * @return {@link #beneficiary} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The party who benefits from the insurance coverage.)
     */
    public Patient getBeneficiaryTarget() { 
      if (this.beneficiaryTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.beneficiary");
        else if (Configuration.doAutoCreate())
          this.beneficiaryTarget = new Patient(); // aa
      return this.beneficiaryTarget;
    }

    /**
     * @param value {@link #beneficiary} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The party who benefits from the insurance coverage.)
     */
    public Coverage setBeneficiaryTarget(Patient value) { 
      this.beneficiaryTarget = value;
      return this;
    }

    /**
     * @return {@link #relationship} (The relationship of beneficiary (patient) (subscriber) to the the planholder.)
     */
    public Coding getRelationship() { 
      if (this.relationship == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.relationship");
        else if (Configuration.doAutoCreate())
          this.relationship = new Coding(); // cc
      return this.relationship;
    }

    public boolean hasRelationship() { 
      return this.relationship != null && !this.relationship.isEmpty();
    }

    /**
     * @param value {@link #relationship} (The relationship of beneficiary (patient) (subscriber) to the the planholder.)
     */
    public Coverage setRelationship(Coding value) { 
      this.relationship = value;
      return this;
    }

    /**
     * @return {@link #group} (A suite of underwrite specific classifiers, for example may be used to identify a class of coverage or employer group, Policy, Plan.)
     */
    public GroupComponent getGroup() { 
      if (this.group == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.group");
        else if (Configuration.doAutoCreate())
          this.group = new GroupComponent(); // cc
      return this.group;
    }

    public boolean hasGroup() { 
      return this.group != null && !this.group.isEmpty();
    }

    /**
     * @param value {@link #group} (A suite of underwrite specific classifiers, for example may be used to identify a class of coverage or employer group, Policy, Plan.)
     */
    public Coverage setGroup(GroupComponent value) { 
      this.group = value;
      return this;
    }

    /**
     * @return {@link #dependent} (A unique identifier for a dependent under the coverage.). This is the underlying object with id, value and extensions. The accessor "getDependent" gives direct access to the value
     */
    public PositiveIntType getDependentElement() { 
      if (this.dependent == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.dependent");
        else if (Configuration.doAutoCreate())
          this.dependent = new PositiveIntType(); // bb
      return this.dependent;
    }

    public boolean hasDependentElement() { 
      return this.dependent != null && !this.dependent.isEmpty();
    }

    public boolean hasDependent() { 
      return this.dependent != null && !this.dependent.isEmpty();
    }

    /**
     * @param value {@link #dependent} (A unique identifier for a dependent under the coverage.). This is the underlying object with id, value and extensions. The accessor "getDependent" gives direct access to the value
     */
    public Coverage setDependentElement(PositiveIntType value) { 
      this.dependent = value;
      return this;
    }

    /**
     * @return A unique identifier for a dependent under the coverage.
     */
    public int getDependent() { 
      return this.dependent == null || this.dependent.isEmpty() ? 0 : this.dependent.getValue();
    }

    /**
     * @param value A unique identifier for a dependent under the coverage.
     */
    public Coverage setDependent(int value) { 
        if (this.dependent == null)
          this.dependent = new PositiveIntType();
        this.dependent.setValue(value);
      return this;
    }

    /**
     * @return {@link #sequence} (An optional counter for a particular instance of the identified coverage which increments upon each renewal.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
     */
    public PositiveIntType getSequenceElement() { 
      if (this.sequence == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.sequence");
        else if (Configuration.doAutoCreate())
          this.sequence = new PositiveIntType(); // bb
      return this.sequence;
    }

    public boolean hasSequenceElement() { 
      return this.sequence != null && !this.sequence.isEmpty();
    }

    public boolean hasSequence() { 
      return this.sequence != null && !this.sequence.isEmpty();
    }

    /**
     * @param value {@link #sequence} (An optional counter for a particular instance of the identified coverage which increments upon each renewal.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
     */
    public Coverage setSequenceElement(PositiveIntType value) { 
      this.sequence = value;
      return this;
    }

    /**
     * @return An optional counter for a particular instance of the identified coverage which increments upon each renewal.
     */
    public int getSequence() { 
      return this.sequence == null || this.sequence.isEmpty() ? 0 : this.sequence.getValue();
    }

    /**
     * @param value An optional counter for a particular instance of the identified coverage which increments upon each renewal.
     */
    public Coverage setSequence(int value) { 
        if (this.sequence == null)
          this.sequence = new PositiveIntType();
        this.sequence.setValue(value);
      return this;
    }

    /**
     * @return {@link #network} (The identifier for a community of providers.). This is the underlying object with id, value and extensions. The accessor "getNetwork" gives direct access to the value
     */
    public StringType getNetworkElement() { 
      if (this.network == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Coverage.network");
        else if (Configuration.doAutoCreate())
          this.network = new StringType(); // bb
      return this.network;
    }

    public boolean hasNetworkElement() { 
      return this.network != null && !this.network.isEmpty();
    }

    public boolean hasNetwork() { 
      return this.network != null && !this.network.isEmpty();
    }

    /**
     * @param value {@link #network} (The identifier for a community of providers.). This is the underlying object with id, value and extensions. The accessor "getNetwork" gives direct access to the value
     */
    public Coverage setNetworkElement(StringType value) { 
      this.network = value;
      return this;
    }

    /**
     * @return The identifier for a community of providers.
     */
    public String getNetwork() { 
      return this.network == null ? null : this.network.getValue();
    }

    /**
     * @param value The identifier for a community of providers.
     */
    public Coverage setNetwork(String value) { 
      if (Utilities.noString(value))
        this.network = null;
      else {
        if (this.network == null)
          this.network = new StringType();
        this.network.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #contract} (The policy(s) which constitute this insurance coverage.)
     */
    public List<Reference> getContract() { 
      if (this.contract == null)
        this.contract = new ArrayList<Reference>();
      return this.contract;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Coverage setContract(List<Reference> theContract) { 
      this.contract = theContract;
      return this;
    }

    public boolean hasContract() { 
      if (this.contract == null)
        return false;
      for (Reference item : this.contract)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addContract() { //3
      Reference t = new Reference();
      if (this.contract == null)
        this.contract = new ArrayList<Reference>();
      this.contract.add(t);
      return t;
    }

    public Coverage addContract(Reference t) { //3
      if (t == null)
        return this;
      if (this.contract == null)
        this.contract = new ArrayList<Reference>();
      this.contract.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #contract}, creating it if it does not already exist
     */
    public Reference getContractFirstRep() { 
      if (getContract().isEmpty()) {
        addContract();
      }
      return getContract().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Contract> getContractTarget() { 
      if (this.contractTarget == null)
        this.contractTarget = new ArrayList<Contract>();
      return this.contractTarget;
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public Contract addContractTarget() { 
      Contract r = new Contract();
      if (this.contractTarget == null)
        this.contractTarget = new ArrayList<Contract>();
      this.contractTarget.add(r);
      return r;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "The main (and possibly only) identifier for the coverage - often referred to as a Member Id, Subscriber Id, Certificate number or Personal Health Number or Case ID.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "The status of the resource instance.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("issuer", "Reference(Organization|Patient|RelatedPerson)", "The program or plan underwriter or payor including both insurance and non-insurance agreements, such as patient-pay agreements.", 0, java.lang.Integer.MAX_VALUE, issuer));
        childrenList.add(new Property("isAgreement", "boolean", "A self, or other, payment agreement not an insurance policy.", 0, java.lang.Integer.MAX_VALUE, isAgreement));
        childrenList.add(new Property("period", "Period", "Time period during which the coverage is in force. A missing start date indicates the start date isn't known, a missing end date means the coverage is continuing to be in force.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("type", "Coding", "The type of coverage: social program, medical plan, accident coverage (workers compensation, auto), group health.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("planholder", "Reference(Patient|RelatedPerson|Organization)", "The party who 'owns' the insurance contractual relationship to the policy or to whom the benefit of the policy is due.", 0, java.lang.Integer.MAX_VALUE, planholder));
        childrenList.add(new Property("beneficiary", "Reference(Patient)", "The party who benefits from the insurance coverage.", 0, java.lang.Integer.MAX_VALUE, beneficiary));
        childrenList.add(new Property("relationship", "Coding", "The relationship of beneficiary (patient) (subscriber) to the the planholder.", 0, java.lang.Integer.MAX_VALUE, relationship));
        childrenList.add(new Property("group", "", "A suite of underwrite specific classifiers, for example may be used to identify a class of coverage or employer group, Policy, Plan.", 0, java.lang.Integer.MAX_VALUE, group));
        childrenList.add(new Property("dependent", "positiveInt", "A unique identifier for a dependent under the coverage.", 0, java.lang.Integer.MAX_VALUE, dependent));
        childrenList.add(new Property("sequence", "positiveInt", "An optional counter for a particular instance of the identified coverage which increments upon each renewal.", 0, java.lang.Integer.MAX_VALUE, sequence));
        childrenList.add(new Property("network", "string", "The identifier for a community of providers.", 0, java.lang.Integer.MAX_VALUE, network));
        childrenList.add(new Property("contract", "Reference(Contract)", "The policy(s) which constitute this insurance coverage.", 0, java.lang.Integer.MAX_VALUE, contract));
      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : this.identifier.toArray(new Base[this.identifier.size()]); // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<CoverageStatus>
        case -1179159879: /*issuer*/ return this.issuer == null ? new Base[0] : new Base[] {this.issuer}; // Reference
        case -2140761088: /*isAgreement*/ return this.isAgreement == null ? new Base[0] : new Base[] {this.isAgreement}; // BooleanType
        case -991726143: /*period*/ return this.period == null ? new Base[0] : new Base[] {this.period}; // Period
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // Coding
        case 1007064597: /*planholder*/ return this.planholder == null ? new Base[0] : new Base[] {this.planholder}; // Reference
        case -565102875: /*beneficiary*/ return this.beneficiary == null ? new Base[0] : new Base[] {this.beneficiary}; // Reference
        case -261851592: /*relationship*/ return this.relationship == null ? new Base[0] : new Base[] {this.relationship}; // Coding
        case 98629247: /*group*/ return this.group == null ? new Base[0] : new Base[] {this.group}; // GroupComponent
        case -1109226753: /*dependent*/ return this.dependent == null ? new Base[0] : new Base[] {this.dependent}; // PositiveIntType
        case 1349547969: /*sequence*/ return this.sequence == null ? new Base[0] : new Base[] {this.sequence}; // PositiveIntType
        case 1843485230: /*network*/ return this.network == null ? new Base[0] : new Base[] {this.network}; // StringType
        case -566947566: /*contract*/ return this.contract == null ? new Base[0] : this.contract.toArray(new Base[this.contract.size()]); // Reference
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.getIdentifier().add(castToIdentifier(value)); // Identifier
          break;
        case -892481550: // status
          this.status = new CoverageStatusEnumFactory().fromType(value); // Enumeration<CoverageStatus>
          break;
        case -1179159879: // issuer
          this.issuer = castToReference(value); // Reference
          break;
        case -2140761088: // isAgreement
          this.isAgreement = castToBoolean(value); // BooleanType
          break;
        case -991726143: // period
          this.period = castToPeriod(value); // Period
          break;
        case 3575610: // type
          this.type = castToCoding(value); // Coding
          break;
        case 1007064597: // planholder
          this.planholder = castToReference(value); // Reference
          break;
        case -565102875: // beneficiary
          this.beneficiary = castToReference(value); // Reference
          break;
        case -261851592: // relationship
          this.relationship = castToCoding(value); // Coding
          break;
        case 98629247: // group
          this.group = (GroupComponent) value; // GroupComponent
          break;
        case -1109226753: // dependent
          this.dependent = castToPositiveInt(value); // PositiveIntType
          break;
        case 1349547969: // sequence
          this.sequence = castToPositiveInt(value); // PositiveIntType
          break;
        case 1843485230: // network
          this.network = castToString(value); // StringType
          break;
        case -566947566: // contract
          this.getContract().add(castToReference(value)); // Reference
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier"))
          this.getIdentifier().add(castToIdentifier(value));
        else if (name.equals("status"))
          this.status = new CoverageStatusEnumFactory().fromType(value); // Enumeration<CoverageStatus>
        else if (name.equals("issuer"))
          this.issuer = castToReference(value); // Reference
        else if (name.equals("isAgreement"))
          this.isAgreement = castToBoolean(value); // BooleanType
        else if (name.equals("period"))
          this.period = castToPeriod(value); // Period
        else if (name.equals("type"))
          this.type = castToCoding(value); // Coding
        else if (name.equals("planholder"))
          this.planholder = castToReference(value); // Reference
        else if (name.equals("beneficiary"))
          this.beneficiary = castToReference(value); // Reference
        else if (name.equals("relationship"))
          this.relationship = castToCoding(value); // Coding
        else if (name.equals("group"))
          this.group = (GroupComponent) value; // GroupComponent
        else if (name.equals("dependent"))
          this.dependent = castToPositiveInt(value); // PositiveIntType
        else if (name.equals("sequence"))
          this.sequence = castToPositiveInt(value); // PositiveIntType
        else if (name.equals("network"))
          this.network = castToString(value); // StringType
        else if (name.equals("contract"))
          this.getContract().add(castToReference(value));
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return addIdentifier(); // Identifier
        case -892481550: throw new FHIRException("Cannot make property status as it is not a complex type"); // Enumeration<CoverageStatus>
        case -1179159879:  return getIssuer(); // Reference
        case -2140761088: throw new FHIRException("Cannot make property isAgreement as it is not a complex type"); // BooleanType
        case -991726143:  return getPeriod(); // Period
        case 3575610:  return getType(); // Coding
        case 1007064597:  return getPlanholder(); // Reference
        case -565102875:  return getBeneficiary(); // Reference
        case -261851592:  return getRelationship(); // Coding
        case 98629247:  return getGroup(); // GroupComponent
        case -1109226753: throw new FHIRException("Cannot make property dependent as it is not a complex type"); // PositiveIntType
        case 1349547969: throw new FHIRException("Cannot make property sequence as it is not a complex type"); // PositiveIntType
        case 1843485230: throw new FHIRException("Cannot make property network as it is not a complex type"); // StringType
        case -566947566:  return addContract(); // Reference
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          return addIdentifier();
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.status");
        }
        else if (name.equals("issuer")) {
          this.issuer = new Reference();
          return this.issuer;
        }
        else if (name.equals("isAgreement")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.isAgreement");
        }
        else if (name.equals("period")) {
          this.period = new Period();
          return this.period;
        }
        else if (name.equals("type")) {
          this.type = new Coding();
          return this.type;
        }
        else if (name.equals("planholder")) {
          this.planholder = new Reference();
          return this.planholder;
        }
        else if (name.equals("beneficiary")) {
          this.beneficiary = new Reference();
          return this.beneficiary;
        }
        else if (name.equals("relationship")) {
          this.relationship = new Coding();
          return this.relationship;
        }
        else if (name.equals("group")) {
          this.group = new GroupComponent();
          return this.group;
        }
        else if (name.equals("dependent")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.dependent");
        }
        else if (name.equals("sequence")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.sequence");
        }
        else if (name.equals("network")) {
          throw new FHIRException("Cannot call addChild on a primitive type Coverage.network");
        }
        else if (name.equals("contract")) {
          return addContract();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "Coverage";

  }

      public Coverage copy() {
        Coverage dst = new Coverage();
        copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        dst.status = status == null ? null : status.copy();
        dst.issuer = issuer == null ? null : issuer.copy();
        dst.isAgreement = isAgreement == null ? null : isAgreement.copy();
        dst.period = period == null ? null : period.copy();
        dst.type = type == null ? null : type.copy();
        dst.planholder = planholder == null ? null : planholder.copy();
        dst.beneficiary = beneficiary == null ? null : beneficiary.copy();
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.group = group == null ? null : group.copy();
        dst.dependent = dependent == null ? null : dependent.copy();
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.network = network == null ? null : network.copy();
        if (contract != null) {
          dst.contract = new ArrayList<Reference>();
          for (Reference i : contract)
            dst.contract.add(i.copy());
        };
        return dst;
      }

      protected Coverage typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof Coverage))
          return false;
        Coverage o = (Coverage) other;
        return compareDeep(identifier, o.identifier, true) && compareDeep(status, o.status, true) && compareDeep(issuer, o.issuer, true)
           && compareDeep(isAgreement, o.isAgreement, true) && compareDeep(period, o.period, true) && compareDeep(type, o.type, true)
           && compareDeep(planholder, o.planholder, true) && compareDeep(beneficiary, o.beneficiary, true)
           && compareDeep(relationship, o.relationship, true) && compareDeep(group, o.group, true) && compareDeep(dependent, o.dependent, true)
           && compareDeep(sequence, o.sequence, true) && compareDeep(network, o.network, true) && compareDeep(contract, o.contract, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof Coverage))
          return false;
        Coverage o = (Coverage) other;
        return compareValues(status, o.status, true) && compareValues(isAgreement, o.isAgreement, true) && compareValues(dependent, o.dependent, true)
           && compareValues(sequence, o.sequence, true) && compareValues(network, o.network, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, status, issuer
          , isAgreement, period, type, planholder, beneficiary, relationship, group, dependent
          , sequence, network, contract);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Coverage;
   }

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>The primary identifier of the insured and the coverage</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Coverage.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="Coverage.identifier", description="The primary identifier of the insured and the coverage", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>The primary identifier of the insured and the coverage</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Coverage.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);

 /**
   * Search parameter: <b>subgroup</b>
   * <p>
   * Description: <b>Sub-group identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.subGroup</b><br>
   * </p>
   */
  @SearchParamDefinition(name="subgroup", path="Coverage.group.subGroup", description="Sub-group identifier", type="string" )
  public static final String SP_SUBGROUP = "subgroup";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>subgroup</b>
   * <p>
   * Description: <b>Sub-group identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.subGroup</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam SUBGROUP = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_SUBGROUP);

 /**
   * Search parameter: <b>subplan</b>
   * <p>
   * Description: <b>Sub-plan identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.subPlan</b><br>
   * </p>
   */
  @SearchParamDefinition(name="subplan", path="Coverage.group.subPlan", description="Sub-plan identifier", type="string" )
  public static final String SP_SUBPLAN = "subplan";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>subplan</b>
   * <p>
   * Description: <b>Sub-plan identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.subPlan</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam SUBPLAN = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_SUBPLAN);

 /**
   * Search parameter: <b>type</b>
   * <p>
   * Description: <b>The kind of coverage (health plan, auto, Workers Compensation)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Coverage.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="type", path="Coverage.type", description="The kind of coverage (health plan, auto, Workers Compensation)", type="token" )
  public static final String SP_TYPE = "type";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>type</b>
   * <p>
   * Description: <b>The kind of coverage (health plan, auto, Workers Compensation)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Coverage.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam TYPE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_TYPE);

 /**
   * Search parameter: <b>issuer</b>
   * <p>
   * Description: <b>The identity of the insurer</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Coverage.issuer</b><br>
   * </p>
   */
  @SearchParamDefinition(name="issuer", path="Coverage.issuer", description="The identity of the insurer", type="reference", target={Organization.class, Patient.class, RelatedPerson.class } )
  public static final String SP_ISSUER = "issuer";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>issuer</b>
   * <p>
   * Description: <b>The identity of the insurer</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Coverage.issuer</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam ISSUER = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_ISSUER);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Coverage:issuer</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_ISSUER = new ca.uhn.fhir.model.api.Include("Coverage:issuer").toLocked();

 /**
   * Search parameter: <b>sequence</b>
   * <p>
   * Description: <b>Sequence number</b><br>
   * Type: <b>number</b><br>
   * Path: <b>Coverage.sequence</b><br>
   * </p>
   */
  @SearchParamDefinition(name="sequence", path="Coverage.sequence", description="Sequence number", type="number" )
  public static final String SP_SEQUENCE = "sequence";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>sequence</b>
   * <p>
   * Description: <b>Sequence number</b><br>
   * Type: <b>number</b><br>
   * Path: <b>Coverage.sequence</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.NumberClientParam SEQUENCE = new ca.uhn.fhir.rest.gclient.NumberClientParam(SP_SEQUENCE);

 /**
   * Search parameter: <b>planholder</b>
   * <p>
   * Description: <b>Reference to the planholder</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Coverage.planholder</b><br>
   * </p>
   */
  @SearchParamDefinition(name="planholder", path="Coverage.planholder", description="Reference to the planholder", type="reference", target={Organization.class, Patient.class, RelatedPerson.class } )
  public static final String SP_PLANHOLDER = "planholder";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>planholder</b>
   * <p>
   * Description: <b>Reference to the planholder</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Coverage.planholder</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PLANHOLDER = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PLANHOLDER);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Coverage:planholder</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PLANHOLDER = new ca.uhn.fhir.model.api.Include("Coverage:planholder").toLocked();

 /**
   * Search parameter: <b>beneficiary</b>
   * <p>
   * Description: <b>Covered party</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Coverage.beneficiary</b><br>
   * </p>
   */
  @SearchParamDefinition(name="beneficiary", path="Coverage.beneficiary", description="Covered party", type="reference", target={Patient.class } )
  public static final String SP_BENEFICIARY = "beneficiary";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>beneficiary</b>
   * <p>
   * Description: <b>Covered party</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Coverage.beneficiary</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam BENEFICIARY = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_BENEFICIARY);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Coverage:beneficiary</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_BENEFICIARY = new ca.uhn.fhir.model.api.Include("Coverage:beneficiary").toLocked();

 /**
   * Search parameter: <b>subclass</b>
   * <p>
   * Description: <b>Sub-class identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.subClass</b><br>
   * </p>
   */
  @SearchParamDefinition(name="subclass", path="Coverage.group.subClass", description="Sub-class identifier", type="string" )
  public static final String SP_SUBCLASS = "subclass";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>subclass</b>
   * <p>
   * Description: <b>Sub-class identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.subClass</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam SUBCLASS = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_SUBCLASS);

 /**
   * Search parameter: <b>plan</b>
   * <p>
   * Description: <b>A plan or policy identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.plan</b><br>
   * </p>
   */
  @SearchParamDefinition(name="plan", path="Coverage.group.plan", description="A plan or policy identifier", type="string" )
  public static final String SP_PLAN = "plan";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>plan</b>
   * <p>
   * Description: <b>A plan or policy identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.plan</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam PLAN = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_PLAN);

 /**
   * Search parameter: <b>class</b>
   * <p>
   * Description: <b>Class identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.class</b><br>
   * </p>
   */
  @SearchParamDefinition(name="class", path="Coverage.group.class", description="Class identifier", type="string" )
  public static final String SP_CLASS = "class";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>class</b>
   * <p>
   * Description: <b>Class identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.class</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam CLASS = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_CLASS);

 /**
   * Search parameter: <b>dependent</b>
   * <p>
   * Description: <b>Dependent number</b><br>
   * Type: <b>number</b><br>
   * Path: <b>Coverage.dependent</b><br>
   * </p>
   */
  @SearchParamDefinition(name="dependent", path="Coverage.dependent", description="Dependent number", type="number" )
  public static final String SP_DEPENDENT = "dependent";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>dependent</b>
   * <p>
   * Description: <b>Dependent number</b><br>
   * Type: <b>number</b><br>
   * Path: <b>Coverage.dependent</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.NumberClientParam DEPENDENT = new ca.uhn.fhir.rest.gclient.NumberClientParam(SP_DEPENDENT);

 /**
   * Search parameter: <b>group</b>
   * <p>
   * Description: <b>Group identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.group</b><br>
   * </p>
   */
  @SearchParamDefinition(name="group", path="Coverage.group.group", description="Group identifier", type="string" )
  public static final String SP_GROUP = "group";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>group</b>
   * <p>
   * Description: <b>Group identifier</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Coverage.group.group</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam GROUP = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_GROUP);


}

