package org.hl7.fhir.r4.model;

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

// Generated on Tue, Dec 19, 2017 22:39+1100 for FHIR v3.1.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.r4.model.Enumerations.*;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.exceptions.FHIRException;
/**
 * Patient’s or family member's work information.
 */
@ResourceDef(name="OccupationalData", profile="http://hl7.org/fhir/Profile/OccupationalData")
public class OccupationalData extends DomainResource {

    public enum HistoryOfEmploymentStatus {
        /**
         * null
         */
        _741652, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static HistoryOfEmploymentStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("74165-2".equals(codeString))
          return _741652;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown HistoryOfEmploymentStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _741652: return "74165-2";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case _741652: return "http://loinc.org";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case _741652: return "";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _741652: return "History of employment status NIOSH";
            default: return "?";
          }
        }
    }

  public static class HistoryOfEmploymentStatusEnumFactory implements EnumFactory<HistoryOfEmploymentStatus> {
    public HistoryOfEmploymentStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("74165-2".equals(codeString))
          return HistoryOfEmploymentStatus._741652;
        throw new IllegalArgumentException("Unknown HistoryOfEmploymentStatus code '"+codeString+"'");
        }
        public Enumeration<HistoryOfEmploymentStatus> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<HistoryOfEmploymentStatus>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("74165-2".equals(codeString))
          return new Enumeration<HistoryOfEmploymentStatus>(this, HistoryOfEmploymentStatus._741652);
        throw new FHIRException("Unknown HistoryOfEmploymentStatus code '"+codeString+"'");
        }
    public String toCode(HistoryOfEmploymentStatus code) {
      if (code == HistoryOfEmploymentStatus._741652)
        return "74165-2";
      return "?";
      }
    public String toSystem(HistoryOfEmploymentStatus code) {
      return code.getSystem();
      }
    }

    public enum RetirementStatus {
        /**
         * Retirement Status
         */
        _875104, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static RetirementStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("87510-4".equals(codeString))
          return _875104;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown RetirementStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _875104: return "87510-4";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case _875104: return "http://hl7.org/fhir/retirement-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case _875104: return "Retirement Status";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _875104: return "Retirement Status";
            default: return "?";
          }
        }
    }

  public static class RetirementStatusEnumFactory implements EnumFactory<RetirementStatus> {
    public RetirementStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("87510-4".equals(codeString))
          return RetirementStatus._875104;
        throw new IllegalArgumentException("Unknown RetirementStatus code '"+codeString+"'");
        }
        public Enumeration<RetirementStatus> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<RetirementStatus>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("87510-4".equals(codeString))
          return new Enumeration<RetirementStatus>(this, RetirementStatus._875104);
        throw new FHIRException("Unknown RetirementStatus code '"+codeString+"'");
        }
    public String toCode(RetirementStatus code) {
      if (code == RetirementStatus._875104)
        return "87510-4";
      return "?";
      }
    public String toSystem(RetirementStatus code) {
      return code.getSystem();
      }
    }

    public enum HazardousDutyWork {
        /**
         * Hazardous Duty Work
         */
        _875112, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static HazardousDutyWork fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("87511-2".equals(codeString))
          return _875112;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown HazardousDutyWork code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _875112: return "87511-2";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case _875112: return "http://hl7.org/fhir/hazadardous-duty-work";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case _875112: return "Hazardous Duty Work";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _875112: return "Hazardous Duty Work";
            default: return "?";
          }
        }
    }

  public static class HazardousDutyWorkEnumFactory implements EnumFactory<HazardousDutyWork> {
    public HazardousDutyWork fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("87511-2".equals(codeString))
          return HazardousDutyWork._875112;
        throw new IllegalArgumentException("Unknown HazardousDutyWork code '"+codeString+"'");
        }
        public Enumeration<HazardousDutyWork> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<HazardousDutyWork>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("87511-2".equals(codeString))
          return new Enumeration<HazardousDutyWork>(this, HazardousDutyWork._875112);
        throw new FHIRException("Unknown HazardousDutyWork code '"+codeString+"'");
        }
    public String toCode(HazardousDutyWork code) {
      if (code == HazardousDutyWork._875112)
        return "87511-2";
      return "?";
      }
    public String toSystem(HazardousDutyWork code) {
      return code.getSystem();
      }
    }

    public enum UsualOccupation {
        /**
         * null
         */
        _218438, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static UsualOccupation fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("21843-8".equals(codeString))
          return _218438;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown UsualOccupation code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _218438: return "21843-8";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case _218438: return "http://loinc.org";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case _218438: return "";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _218438: return "History of Usual occupation";
            default: return "?";
          }
        }
    }

  public static class UsualOccupationEnumFactory implements EnumFactory<UsualOccupation> {
    public UsualOccupation fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("21843-8".equals(codeString))
          return UsualOccupation._218438;
        throw new IllegalArgumentException("Unknown UsualOccupation code '"+codeString+"'");
        }
        public Enumeration<UsualOccupation> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<UsualOccupation>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("21843-8".equals(codeString))
          return new Enumeration<UsualOccupation>(this, UsualOccupation._218438);
        throw new FHIRException("Unknown UsualOccupation code '"+codeString+"'");
        }
    public String toCode(UsualOccupation code) {
      if (code == UsualOccupation._218438)
        return "21843-8";
      return "?";
      }
    public String toSystem(UsualOccupation code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class OccupationalDataEmploymentStatusComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * 74165-2.
         */
        @Child(name = "code", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="74165-2", formalDefinition="74165-2." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/history-of-employment-status")
        protected Enumeration<HistoryOfEmploymentStatus> code;

        /**
         * Employment status effective time.
         */
        @Child(name = "effective", type = {DateTimeType.class, Period.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Employment status effective time", formalDefinition="Employment status effective time." )
        protected Type effective;

        /**
         * Employment status value.
         */
        @Child(name = "value", type = {CodeableConcept.class}, order=3, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Employment status value", formalDefinition="Employment status value." )
        protected CodeableConcept value;

        private static final long serialVersionUID = -889740778L;

    /**
     * Constructor
     */
      public OccupationalDataEmploymentStatusComponent() {
        super();
      }

    /**
     * Constructor
     */
      public OccupationalDataEmploymentStatusComponent(Enumeration<HistoryOfEmploymentStatus> code, Type effective, CodeableConcept value) {
        super();
        this.code = code;
        this.effective = effective;
        this.value = value;
      }

        /**
         * @return {@link #code} (74165-2.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public Enumeration<HistoryOfEmploymentStatus> getCodeElement() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataEmploymentStatusComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new Enumeration<HistoryOfEmploymentStatus>(new HistoryOfEmploymentStatusEnumFactory()); // bb
          return this.code;
        }

        public boolean hasCodeElement() { 
          return this.code != null && !this.code.isEmpty();
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (74165-2.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public OccupationalDataEmploymentStatusComponent setCodeElement(Enumeration<HistoryOfEmploymentStatus> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return 74165-2.
         */
        public HistoryOfEmploymentStatus getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value 74165-2.
         */
        public OccupationalDataEmploymentStatusComponent setCode(HistoryOfEmploymentStatus value) { 
            if (this.code == null)
              this.code = new Enumeration<HistoryOfEmploymentStatus>(new HistoryOfEmploymentStatusEnumFactory());
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #effective} (Employment status effective time.)
         */
        public Type getEffective() { 
          return this.effective;
        }

        /**
         * @return {@link #effective} (Employment status effective time.)
         */
        public DateTimeType getEffectiveDateTimeType() throws FHIRException { 
          if (!(this.effective instanceof DateTimeType))
            throw new FHIRException("Type mismatch: the type DateTimeType was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (DateTimeType) this.effective;
        }

        public boolean hasEffectiveDateTimeType() { 
          return this.effective instanceof DateTimeType;
        }

        /**
         * @return {@link #effective} (Employment status effective time.)
         */
        public Period getEffectivePeriod() throws FHIRException { 
          if (!(this.effective instanceof Period))
            throw new FHIRException("Type mismatch: the type Period was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (Period) this.effective;
        }

        public boolean hasEffectivePeriod() { 
          return this.effective instanceof Period;
        }

        public boolean hasEffective() { 
          return this.effective != null && !this.effective.isEmpty();
        }

        /**
         * @param value {@link #effective} (Employment status effective time.)
         */
        public OccupationalDataEmploymentStatusComponent setEffective(Type value) { 
          this.effective = value;
          return this;
        }

        /**
         * @return {@link #value} (Employment status value.)
         */
        public CodeableConcept getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataEmploymentStatusComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new CodeableConcept(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Employment status value.)
         */
        public OccupationalDataEmploymentStatusComponent setValue(CodeableConcept value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("code", "code", "74165-2.", 0, 1, code));
          children.add(new Property("effective[x]", "dateTime|Period", "Employment status effective time.", 0, 1, effective));
          children.add(new Property("value", "CodeableConcept", "Employment status value.", 0, 1, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3059181: /*code*/  return new Property("code", "code", "74165-2.", 0, 1, code);
          case 247104889: /*effective[x]*/  return new Property("effective[x]", "dateTime|Period", "Employment status effective time.", 0, 1, effective);
          case -1468651097: /*effective*/  return new Property("effective[x]", "dateTime|Period", "Employment status effective time.", 0, 1, effective);
          case -275306910: /*effectiveDateTime*/  return new Property("effective[x]", "dateTime|Period", "Employment status effective time.", 0, 1, effective);
          case -403934648: /*effectivePeriod*/  return new Property("effective[x]", "dateTime|Period", "Employment status effective time.", 0, 1, effective);
          case 111972721: /*value*/  return new Property("value", "CodeableConcept", "Employment status value.", 0, 1, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // Enumeration<HistoryOfEmploymentStatus>
        case -1468651097: /*effective*/ return this.effective == null ? new Base[0] : new Base[] {this.effective}; // Type
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // CodeableConcept
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          value = new HistoryOfEmploymentStatusEnumFactory().fromType(castToCode(value));
          this.code = (Enumeration) value; // Enumeration<HistoryOfEmploymentStatus>
          return value;
        case -1468651097: // effective
          this.effective = castToType(value); // Type
          return value;
        case 111972721: // value
          this.value = castToCodeableConcept(value); // CodeableConcept
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          value = new HistoryOfEmploymentStatusEnumFactory().fromType(castToCode(value));
          this.code = (Enumeration) value; // Enumeration<HistoryOfEmploymentStatus>
        } else if (name.equals("effective[x]")) {
          this.effective = castToType(value); // Type
        } else if (name.equals("value")) {
          this.value = castToCodeableConcept(value); // CodeableConcept
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCodeElement();
        case 247104889:  return getEffective(); 
        case -1468651097:  return getEffective(); 
        case 111972721:  return getValue(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"code"};
        case -1468651097: /*effective*/ return new String[] {"dateTime", "Period"};
        case 111972721: /*value*/ return new String[] {"CodeableConcept"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          throw new FHIRException("Cannot call addChild on a primitive type OccupationalData.code");
        }
        else if (name.equals("effectiveDateTime")) {
          this.effective = new DateTimeType();
          return this.effective;
        }
        else if (name.equals("effectivePeriod")) {
          this.effective = new Period();
          return this.effective;
        }
        else if (name.equals("value")) {
          this.value = new CodeableConcept();
          return this.value;
        }
        else
          return super.addChild(name);
      }

      public OccupationalDataEmploymentStatusComponent copy() {
        OccupationalDataEmploymentStatusComponent dst = new OccupationalDataEmploymentStatusComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.effective = effective == null ? null : effective.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalDataEmploymentStatusComponent))
          return false;
        OccupationalDataEmploymentStatusComponent o = (OccupationalDataEmploymentStatusComponent) other_;
        return compareDeep(code, o.code, true) && compareDeep(effective, o.effective, true) && compareDeep(value, o.value, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalDataEmploymentStatusComponent))
          return false;
        OccupationalDataEmploymentStatusComponent o = (OccupationalDataEmploymentStatusComponent) other_;
        return compareValues(code, o.code, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, effective, value);
      }

  public String fhirType() {
    return "OccupationalData.employmentStatus";

  }

  }

    @Block()
    public static class OccupationalDataRetirementStatusComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * 87510-4.
         */
        @Child(name = "code", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="87510-4", formalDefinition="87510-4." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/retirement-status")
        protected Enumeration<RetirementStatus> code;

        /**
         * Retirement status effective time.
         */
        @Child(name = "effective", type = {DateTimeType.class, Period.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Retirement status effective time", formalDefinition="Retirement status effective time." )
        protected Type effective;

        /**
         * Retirement status value.
         */
        @Child(name = "value", type = {CodeableConcept.class}, order=3, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Retirement status value", formalDefinition="Retirement status value." )
        protected CodeableConcept value;

        private static final long serialVersionUID = 695307622L;

    /**
     * Constructor
     */
      public OccupationalDataRetirementStatusComponent() {
        super();
      }

    /**
     * Constructor
     */
      public OccupationalDataRetirementStatusComponent(Enumeration<RetirementStatus> code, Type effective, CodeableConcept value) {
        super();
        this.code = code;
        this.effective = effective;
        this.value = value;
      }

        /**
         * @return {@link #code} (87510-4.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public Enumeration<RetirementStatus> getCodeElement() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataRetirementStatusComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new Enumeration<RetirementStatus>(new RetirementStatusEnumFactory()); // bb
          return this.code;
        }

        public boolean hasCodeElement() { 
          return this.code != null && !this.code.isEmpty();
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (87510-4.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public OccupationalDataRetirementStatusComponent setCodeElement(Enumeration<RetirementStatus> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return 87510-4.
         */
        public RetirementStatus getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value 87510-4.
         */
        public OccupationalDataRetirementStatusComponent setCode(RetirementStatus value) { 
            if (this.code == null)
              this.code = new Enumeration<RetirementStatus>(new RetirementStatusEnumFactory());
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #effective} (Retirement status effective time.)
         */
        public Type getEffective() { 
          return this.effective;
        }

        /**
         * @return {@link #effective} (Retirement status effective time.)
         */
        public DateTimeType getEffectiveDateTimeType() throws FHIRException { 
          if (!(this.effective instanceof DateTimeType))
            throw new FHIRException("Type mismatch: the type DateTimeType was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (DateTimeType) this.effective;
        }

        public boolean hasEffectiveDateTimeType() { 
          return this.effective instanceof DateTimeType;
        }

        /**
         * @return {@link #effective} (Retirement status effective time.)
         */
        public Period getEffectivePeriod() throws FHIRException { 
          if (!(this.effective instanceof Period))
            throw new FHIRException("Type mismatch: the type Period was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (Period) this.effective;
        }

        public boolean hasEffectivePeriod() { 
          return this.effective instanceof Period;
        }

        public boolean hasEffective() { 
          return this.effective != null && !this.effective.isEmpty();
        }

        /**
         * @param value {@link #effective} (Retirement status effective time.)
         */
        public OccupationalDataRetirementStatusComponent setEffective(Type value) { 
          this.effective = value;
          return this;
        }

        /**
         * @return {@link #value} (Retirement status value.)
         */
        public CodeableConcept getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataRetirementStatusComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new CodeableConcept(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Retirement status value.)
         */
        public OccupationalDataRetirementStatusComponent setValue(CodeableConcept value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("code", "code", "87510-4.", 0, 1, code));
          children.add(new Property("effective[x]", "dateTime|Period", "Retirement status effective time.", 0, 1, effective));
          children.add(new Property("value", "CodeableConcept", "Retirement status value.", 0, 1, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3059181: /*code*/  return new Property("code", "code", "87510-4.", 0, 1, code);
          case 247104889: /*effective[x]*/  return new Property("effective[x]", "dateTime|Period", "Retirement status effective time.", 0, 1, effective);
          case -1468651097: /*effective*/  return new Property("effective[x]", "dateTime|Period", "Retirement status effective time.", 0, 1, effective);
          case -275306910: /*effectiveDateTime*/  return new Property("effective[x]", "dateTime|Period", "Retirement status effective time.", 0, 1, effective);
          case -403934648: /*effectivePeriod*/  return new Property("effective[x]", "dateTime|Period", "Retirement status effective time.", 0, 1, effective);
          case 111972721: /*value*/  return new Property("value", "CodeableConcept", "Retirement status value.", 0, 1, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // Enumeration<RetirementStatus>
        case -1468651097: /*effective*/ return this.effective == null ? new Base[0] : new Base[] {this.effective}; // Type
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // CodeableConcept
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          value = new RetirementStatusEnumFactory().fromType(castToCode(value));
          this.code = (Enumeration) value; // Enumeration<RetirementStatus>
          return value;
        case -1468651097: // effective
          this.effective = castToType(value); // Type
          return value;
        case 111972721: // value
          this.value = castToCodeableConcept(value); // CodeableConcept
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          value = new RetirementStatusEnumFactory().fromType(castToCode(value));
          this.code = (Enumeration) value; // Enumeration<RetirementStatus>
        } else if (name.equals("effective[x]")) {
          this.effective = castToType(value); // Type
        } else if (name.equals("value")) {
          this.value = castToCodeableConcept(value); // CodeableConcept
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCodeElement();
        case 247104889:  return getEffective(); 
        case -1468651097:  return getEffective(); 
        case 111972721:  return getValue(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"code"};
        case -1468651097: /*effective*/ return new String[] {"dateTime", "Period"};
        case 111972721: /*value*/ return new String[] {"CodeableConcept"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          throw new FHIRException("Cannot call addChild on a primitive type OccupationalData.code");
        }
        else if (name.equals("effectiveDateTime")) {
          this.effective = new DateTimeType();
          return this.effective;
        }
        else if (name.equals("effectivePeriod")) {
          this.effective = new Period();
          return this.effective;
        }
        else if (name.equals("value")) {
          this.value = new CodeableConcept();
          return this.value;
        }
        else
          return super.addChild(name);
      }

      public OccupationalDataRetirementStatusComponent copy() {
        OccupationalDataRetirementStatusComponent dst = new OccupationalDataRetirementStatusComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.effective = effective == null ? null : effective.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalDataRetirementStatusComponent))
          return false;
        OccupationalDataRetirementStatusComponent o = (OccupationalDataRetirementStatusComponent) other_;
        return compareDeep(code, o.code, true) && compareDeep(effective, o.effective, true) && compareDeep(value, o.value, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalDataRetirementStatusComponent))
          return false;
        OccupationalDataRetirementStatusComponent o = (OccupationalDataRetirementStatusComponent) other_;
        return compareValues(code, o.code, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, effective, value);
      }

  public String fhirType() {
    return "OccupationalData.retirementStatus";

  }

  }

    @Block()
    public static class OccupationalDataCombatZoneHazardousDutyComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * 87511-2.
         */
        @Child(name = "code", type = {CodeType.class}, order=1, min=1, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="87511-2", formalDefinition="87511-2." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/hazadardous-duty-work")
        protected List<Enumeration<HazardousDutyWork>> code;

        /**
         * Combat Zone Hazardous Duty effective time.
         */
        @Child(name = "effective", type = {DateTimeType.class, Period.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Combat Zone Hazardous Duty effective time", formalDefinition="Combat Zone Hazardous Duty effective time." )
        protected Type effective;

        /**
         * Combat Zone Hazardous Duty value.
         */
        @Child(name = "value", type = {CodeableConcept.class}, order=3, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Combat Zone Hazardous Duty value", formalDefinition="Combat Zone Hazardous Duty value." )
        protected CodeableConcept value;

        private static final long serialVersionUID = -1552285935L;

    /**
     * Constructor
     */
      public OccupationalDataCombatZoneHazardousDutyComponent() {
        super();
      }

    /**
     * Constructor
     */
      public OccupationalDataCombatZoneHazardousDutyComponent(Type effective, CodeableConcept value) {
        super();
        this.effective = effective;
        this.value = value;
      }

        /**
         * @return {@link #code} (87511-2.)
         */
        public List<Enumeration<HazardousDutyWork>> getCode() { 
          if (this.code == null)
            this.code = new ArrayList<Enumeration<HazardousDutyWork>>();
          return this.code;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public OccupationalDataCombatZoneHazardousDutyComponent setCode(List<Enumeration<HazardousDutyWork>> theCode) { 
          this.code = theCode;
          return this;
        }

        public boolean hasCode() { 
          if (this.code == null)
            return false;
          for (Enumeration<HazardousDutyWork> item : this.code)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #code} (87511-2.)
         */
        public Enumeration<HazardousDutyWork> addCodeElement() {//2 
          Enumeration<HazardousDutyWork> t = new Enumeration<HazardousDutyWork>(new HazardousDutyWorkEnumFactory());
          if (this.code == null)
            this.code = new ArrayList<Enumeration<HazardousDutyWork>>();
          this.code.add(t);
          return t;
        }

        /**
         * @param value {@link #code} (87511-2.)
         */
        public OccupationalDataCombatZoneHazardousDutyComponent addCode(HazardousDutyWork value) { //1
          Enumeration<HazardousDutyWork> t = new Enumeration<HazardousDutyWork>(new HazardousDutyWorkEnumFactory());
          t.setValue(value);
          if (this.code == null)
            this.code = new ArrayList<Enumeration<HazardousDutyWork>>();
          this.code.add(t);
          return this;
        }

        /**
         * @param value {@link #code} (87511-2.)
         */
        public boolean hasCode(HazardousDutyWork value) { 
          if (this.code == null)
            return false;
          for (Enumeration<HazardousDutyWork> v : this.code)
            if (v.getValue().equals(value)) // code
              return true;
          return false;
        }

        /**
         * @return {@link #effective} (Combat Zone Hazardous Duty effective time.)
         */
        public Type getEffective() { 
          return this.effective;
        }

        /**
         * @return {@link #effective} (Combat Zone Hazardous Duty effective time.)
         */
        public DateTimeType getEffectiveDateTimeType() throws FHIRException { 
          if (!(this.effective instanceof DateTimeType))
            throw new FHIRException("Type mismatch: the type DateTimeType was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (DateTimeType) this.effective;
        }

        public boolean hasEffectiveDateTimeType() { 
          return this.effective instanceof DateTimeType;
        }

        /**
         * @return {@link #effective} (Combat Zone Hazardous Duty effective time.)
         */
        public Period getEffectivePeriod() throws FHIRException { 
          if (!(this.effective instanceof Period))
            throw new FHIRException("Type mismatch: the type Period was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (Period) this.effective;
        }

        public boolean hasEffectivePeriod() { 
          return this.effective instanceof Period;
        }

        public boolean hasEffective() { 
          return this.effective != null && !this.effective.isEmpty();
        }

        /**
         * @param value {@link #effective} (Combat Zone Hazardous Duty effective time.)
         */
        public OccupationalDataCombatZoneHazardousDutyComponent setEffective(Type value) { 
          this.effective = value;
          return this;
        }

        /**
         * @return {@link #value} (Combat Zone Hazardous Duty value.)
         */
        public CodeableConcept getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataCombatZoneHazardousDutyComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new CodeableConcept(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Combat Zone Hazardous Duty value.)
         */
        public OccupationalDataCombatZoneHazardousDutyComponent setValue(CodeableConcept value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("code", "code", "87511-2.", 0, java.lang.Integer.MAX_VALUE, code));
          children.add(new Property("effective[x]", "dateTime|Period", "Combat Zone Hazardous Duty effective time.", 0, 1, effective));
          children.add(new Property("value", "CodeableConcept", "Combat Zone Hazardous Duty value.", 0, 1, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3059181: /*code*/  return new Property("code", "code", "87511-2.", 0, java.lang.Integer.MAX_VALUE, code);
          case 247104889: /*effective[x]*/  return new Property("effective[x]", "dateTime|Period", "Combat Zone Hazardous Duty effective time.", 0, 1, effective);
          case -1468651097: /*effective*/  return new Property("effective[x]", "dateTime|Period", "Combat Zone Hazardous Duty effective time.", 0, 1, effective);
          case -275306910: /*effectiveDateTime*/  return new Property("effective[x]", "dateTime|Period", "Combat Zone Hazardous Duty effective time.", 0, 1, effective);
          case -403934648: /*effectivePeriod*/  return new Property("effective[x]", "dateTime|Period", "Combat Zone Hazardous Duty effective time.", 0, 1, effective);
          case 111972721: /*value*/  return new Property("value", "CodeableConcept", "Combat Zone Hazardous Duty value.", 0, 1, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : this.code.toArray(new Base[this.code.size()]); // Enumeration<HazardousDutyWork>
        case -1468651097: /*effective*/ return this.effective == null ? new Base[0] : new Base[] {this.effective}; // Type
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // CodeableConcept
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          value = new HazardousDutyWorkEnumFactory().fromType(castToCode(value));
          this.getCode().add((Enumeration) value); // Enumeration<HazardousDutyWork>
          return value;
        case -1468651097: // effective
          this.effective = castToType(value); // Type
          return value;
        case 111972721: // value
          this.value = castToCodeableConcept(value); // CodeableConcept
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          value = new HazardousDutyWorkEnumFactory().fromType(castToCode(value));
          this.getCode().add((Enumeration) value);
        } else if (name.equals("effective[x]")) {
          this.effective = castToType(value); // Type
        } else if (name.equals("value")) {
          this.value = castToCodeableConcept(value); // CodeableConcept
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return addCodeElement();
        case 247104889:  return getEffective(); 
        case -1468651097:  return getEffective(); 
        case 111972721:  return getValue(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"code"};
        case -1468651097: /*effective*/ return new String[] {"dateTime", "Period"};
        case 111972721: /*value*/ return new String[] {"CodeableConcept"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          throw new FHIRException("Cannot call addChild on a primitive type OccupationalData.code");
        }
        else if (name.equals("effectiveDateTime")) {
          this.effective = new DateTimeType();
          return this.effective;
        }
        else if (name.equals("effectivePeriod")) {
          this.effective = new Period();
          return this.effective;
        }
        else if (name.equals("value")) {
          this.value = new CodeableConcept();
          return this.value;
        }
        else
          return super.addChild(name);
      }

      public OccupationalDataCombatZoneHazardousDutyComponent copy() {
        OccupationalDataCombatZoneHazardousDutyComponent dst = new OccupationalDataCombatZoneHazardousDutyComponent();
        copyValues(dst);
        if (code != null) {
          dst.code = new ArrayList<Enumeration<HazardousDutyWork>>();
          for (Enumeration<HazardousDutyWork> i : code)
            dst.code.add(i.copy());
        };
        dst.effective = effective == null ? null : effective.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalDataCombatZoneHazardousDutyComponent))
          return false;
        OccupationalDataCombatZoneHazardousDutyComponent o = (OccupationalDataCombatZoneHazardousDutyComponent) other_;
        return compareDeep(code, o.code, true) && compareDeep(effective, o.effective, true) && compareDeep(value, o.value, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalDataCombatZoneHazardousDutyComponent))
          return false;
        OccupationalDataCombatZoneHazardousDutyComponent o = (OccupationalDataCombatZoneHazardousDutyComponent) other_;
        return compareValues(code, o.code, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, effective, value);
      }

  public String fhirType() {
    return "OccupationalData.combatZoneHazardousDuty";

  }

  }

    @Block()
    public static class OccupationalDataUsualOccupationComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * 21843-8.
         */
        @Child(name = "code", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="21843-8", formalDefinition="21843-8." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/usual-occupation")
        protected Enumeration<UsualOccupation> code;

        /**
         * Usual Occupation effective time.
         */
        @Child(name = "effective", type = {DateTimeType.class, Period.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation effective time", formalDefinition="Usual Occupation effective time." )
        protected Type effective;

        /**
         * Usual Occupation value.
         */
        @Child(name = "value", type = {CodeableConcept.class}, order=3, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation value", formalDefinition="Usual Occupation value." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/occupation-cdc-census-2010")
        protected CodeableConcept value;

        /**
         * Usual Occupation duration.
         */
        @Child(name = "duration", type = {}, order=4, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation duration", formalDefinition="Usual Occupation duration." )
        protected OccupationalDataUsualOccupationDurationComponent duration;

        /**
         * Usual Occupation industry.
         */
        @Child(name = "industry", type = {}, order=5, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation industry", formalDefinition="Usual Occupation industry." )
        protected OccupationalDataUsualOccupationIndustryComponent industry;

        private static final long serialVersionUID = -754978326L;

    /**
     * Constructor
     */
      public OccupationalDataUsualOccupationComponent() {
        super();
      }

    /**
     * Constructor
     */
      public OccupationalDataUsualOccupationComponent(Enumeration<UsualOccupation> code, Type effective, CodeableConcept value) {
        super();
        this.code = code;
        this.effective = effective;
        this.value = value;
      }

        /**
         * @return {@link #code} (21843-8.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public Enumeration<UsualOccupation> getCodeElement() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new Enumeration<UsualOccupation>(new UsualOccupationEnumFactory()); // bb
          return this.code;
        }

        public boolean hasCodeElement() { 
          return this.code != null && !this.code.isEmpty();
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (21843-8.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public OccupationalDataUsualOccupationComponent setCodeElement(Enumeration<UsualOccupation> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return 21843-8.
         */
        public UsualOccupation getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value 21843-8.
         */
        public OccupationalDataUsualOccupationComponent setCode(UsualOccupation value) { 
            if (this.code == null)
              this.code = new Enumeration<UsualOccupation>(new UsualOccupationEnumFactory());
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #effective} (Usual Occupation effective time.)
         */
        public Type getEffective() { 
          return this.effective;
        }

        /**
         * @return {@link #effective} (Usual Occupation effective time.)
         */
        public DateTimeType getEffectiveDateTimeType() throws FHIRException { 
          if (!(this.effective instanceof DateTimeType))
            throw new FHIRException("Type mismatch: the type DateTimeType was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (DateTimeType) this.effective;
        }

        public boolean hasEffectiveDateTimeType() { 
          return this.effective instanceof DateTimeType;
        }

        /**
         * @return {@link #effective} (Usual Occupation effective time.)
         */
        public Period getEffectivePeriod() throws FHIRException { 
          if (!(this.effective instanceof Period))
            throw new FHIRException("Type mismatch: the type Period was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (Period) this.effective;
        }

        public boolean hasEffectivePeriod() { 
          return this.effective instanceof Period;
        }

        public boolean hasEffective() { 
          return this.effective != null && !this.effective.isEmpty();
        }

        /**
         * @param value {@link #effective} (Usual Occupation effective time.)
         */
        public OccupationalDataUsualOccupationComponent setEffective(Type value) { 
          this.effective = value;
          return this;
        }

        /**
         * @return {@link #value} (Usual Occupation value.)
         */
        public CodeableConcept getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new CodeableConcept(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Usual Occupation value.)
         */
        public OccupationalDataUsualOccupationComponent setValue(CodeableConcept value) { 
          this.value = value;
          return this;
        }

        /**
         * @return {@link #duration} (Usual Occupation duration.)
         */
        public OccupationalDataUsualOccupationDurationComponent getDuration() { 
          if (this.duration == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationComponent.duration");
            else if (Configuration.doAutoCreate())
              this.duration = new OccupationalDataUsualOccupationDurationComponent(); // cc
          return this.duration;
        }

        public boolean hasDuration() { 
          return this.duration != null && !this.duration.isEmpty();
        }

        /**
         * @param value {@link #duration} (Usual Occupation duration.)
         */
        public OccupationalDataUsualOccupationComponent setDuration(OccupationalDataUsualOccupationDurationComponent value) { 
          this.duration = value;
          return this;
        }

        /**
         * @return {@link #industry} (Usual Occupation industry.)
         */
        public OccupationalDataUsualOccupationIndustryComponent getIndustry() { 
          if (this.industry == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationComponent.industry");
            else if (Configuration.doAutoCreate())
              this.industry = new OccupationalDataUsualOccupationIndustryComponent(); // cc
          return this.industry;
        }

        public boolean hasIndustry() { 
          return this.industry != null && !this.industry.isEmpty();
        }

        /**
         * @param value {@link #industry} (Usual Occupation industry.)
         */
        public OccupationalDataUsualOccupationComponent setIndustry(OccupationalDataUsualOccupationIndustryComponent value) { 
          this.industry = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("code", "code", "21843-8.", 0, 1, code));
          children.add(new Property("effective[x]", "dateTime|Period", "Usual Occupation effective time.", 0, 1, effective));
          children.add(new Property("value", "CodeableConcept", "Usual Occupation value.", 0, 1, value));
          children.add(new Property("duration", "", "Usual Occupation duration.", 0, 1, duration));
          children.add(new Property("industry", "", "Usual Occupation industry.", 0, 1, industry));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3059181: /*code*/  return new Property("code", "code", "21843-8.", 0, 1, code);
          case 247104889: /*effective[x]*/  return new Property("effective[x]", "dateTime|Period", "Usual Occupation effective time.", 0, 1, effective);
          case -1468651097: /*effective*/  return new Property("effective[x]", "dateTime|Period", "Usual Occupation effective time.", 0, 1, effective);
          case -275306910: /*effectiveDateTime*/  return new Property("effective[x]", "dateTime|Period", "Usual Occupation effective time.", 0, 1, effective);
          case -403934648: /*effectivePeriod*/  return new Property("effective[x]", "dateTime|Period", "Usual Occupation effective time.", 0, 1, effective);
          case 111972721: /*value*/  return new Property("value", "CodeableConcept", "Usual Occupation value.", 0, 1, value);
          case -1992012396: /*duration*/  return new Property("duration", "", "Usual Occupation duration.", 0, 1, duration);
          case 127156702: /*industry*/  return new Property("industry", "", "Usual Occupation industry.", 0, 1, industry);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // Enumeration<UsualOccupation>
        case -1468651097: /*effective*/ return this.effective == null ? new Base[0] : new Base[] {this.effective}; // Type
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // CodeableConcept
        case -1992012396: /*duration*/ return this.duration == null ? new Base[0] : new Base[] {this.duration}; // OccupationalDataUsualOccupationDurationComponent
        case 127156702: /*industry*/ return this.industry == null ? new Base[0] : new Base[] {this.industry}; // OccupationalDataUsualOccupationIndustryComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          value = new UsualOccupationEnumFactory().fromType(castToCode(value));
          this.code = (Enumeration) value; // Enumeration<UsualOccupation>
          return value;
        case -1468651097: // effective
          this.effective = castToType(value); // Type
          return value;
        case 111972721: // value
          this.value = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1992012396: // duration
          this.duration = (OccupationalDataUsualOccupationDurationComponent) value; // OccupationalDataUsualOccupationDurationComponent
          return value;
        case 127156702: // industry
          this.industry = (OccupationalDataUsualOccupationIndustryComponent) value; // OccupationalDataUsualOccupationIndustryComponent
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          value = new UsualOccupationEnumFactory().fromType(castToCode(value));
          this.code = (Enumeration) value; // Enumeration<UsualOccupation>
        } else if (name.equals("effective[x]")) {
          this.effective = castToType(value); // Type
        } else if (name.equals("value")) {
          this.value = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("duration")) {
          this.duration = (OccupationalDataUsualOccupationDurationComponent) value; // OccupationalDataUsualOccupationDurationComponent
        } else if (name.equals("industry")) {
          this.industry = (OccupationalDataUsualOccupationIndustryComponent) value; // OccupationalDataUsualOccupationIndustryComponent
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCodeElement();
        case 247104889:  return getEffective(); 
        case -1468651097:  return getEffective(); 
        case 111972721:  return getValue(); 
        case -1992012396:  return getDuration(); 
        case 127156702:  return getIndustry(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"code"};
        case -1468651097: /*effective*/ return new String[] {"dateTime", "Period"};
        case 111972721: /*value*/ return new String[] {"CodeableConcept"};
        case -1992012396: /*duration*/ return new String[] {};
        case 127156702: /*industry*/ return new String[] {};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          throw new FHIRException("Cannot call addChild on a primitive type OccupationalData.code");
        }
        else if (name.equals("effectiveDateTime")) {
          this.effective = new DateTimeType();
          return this.effective;
        }
        else if (name.equals("effectivePeriod")) {
          this.effective = new Period();
          return this.effective;
        }
        else if (name.equals("value")) {
          this.value = new CodeableConcept();
          return this.value;
        }
        else if (name.equals("duration")) {
          this.duration = new OccupationalDataUsualOccupationDurationComponent();
          return this.duration;
        }
        else if (name.equals("industry")) {
          this.industry = new OccupationalDataUsualOccupationIndustryComponent();
          return this.industry;
        }
        else
          return super.addChild(name);
      }

      public OccupationalDataUsualOccupationComponent copy() {
        OccupationalDataUsualOccupationComponent dst = new OccupationalDataUsualOccupationComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.effective = effective == null ? null : effective.copy();
        dst.value = value == null ? null : value.copy();
        dst.duration = duration == null ? null : duration.copy();
        dst.industry = industry == null ? null : industry.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalDataUsualOccupationComponent))
          return false;
        OccupationalDataUsualOccupationComponent o = (OccupationalDataUsualOccupationComponent) other_;
        return compareDeep(code, o.code, true) && compareDeep(effective, o.effective, true) && compareDeep(value, o.value, true)
           && compareDeep(duration, o.duration, true) && compareDeep(industry, o.industry, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalDataUsualOccupationComponent))
          return false;
        OccupationalDataUsualOccupationComponent o = (OccupationalDataUsualOccupationComponent) other_;
        return compareValues(code, o.code, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, effective, value, duration
          , industry);
      }

  public String fhirType() {
    return "OccupationalData.usualOccupation";

  }

  }

    @Block()
    public static class OccupationalDataUsualOccupationDurationComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Usual Occupation duration code.
         */
        @Child(name = "code", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation duration code", formalDefinition="Usual Occupation duration code." )
        protected CodeableConcept code;

        /**
         * Usual Occupation duration value.
         */
        @Child(name = "value", type = {Period.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation duration value", formalDefinition="Usual Occupation duration value." )
        protected Period value;

        private static final long serialVersionUID = -187323334L;

    /**
     * Constructor
     */
      public OccupationalDataUsualOccupationDurationComponent() {
        super();
      }

    /**
     * Constructor
     */
      public OccupationalDataUsualOccupationDurationComponent(CodeableConcept code, Period value) {
        super();
        this.code = code;
        this.value = value;
      }

        /**
         * @return {@link #code} (Usual Occupation duration code.)
         */
        public CodeableConcept getCode() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationDurationComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new CodeableConcept(); // cc
          return this.code;
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (Usual Occupation duration code.)
         */
        public OccupationalDataUsualOccupationDurationComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #value} (Usual Occupation duration value.)
         */
        public Period getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationDurationComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new Period(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Usual Occupation duration value.)
         */
        public OccupationalDataUsualOccupationDurationComponent setValue(Period value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("code", "CodeableConcept", "Usual Occupation duration code.", 0, 1, code));
          children.add(new Property("value", "Period", "Usual Occupation duration value.", 0, 1, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3059181: /*code*/  return new Property("code", "CodeableConcept", "Usual Occupation duration code.", 0, 1, code);
          case 111972721: /*value*/  return new Property("value", "Period", "Usual Occupation duration value.", 0, 1, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // CodeableConcept
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // Period
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          this.code = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 111972721: // value
          this.value = castToPeriod(value); // Period
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          this.code = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("value")) {
          this.value = castToPeriod(value); // Period
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCode(); 
        case 111972721:  return getValue(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"CodeableConcept"};
        case 111972721: /*value*/ return new String[] {"Period"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          this.code = new CodeableConcept();
          return this.code;
        }
        else if (name.equals("value")) {
          this.value = new Period();
          return this.value;
        }
        else
          return super.addChild(name);
      }

      public OccupationalDataUsualOccupationDurationComponent copy() {
        OccupationalDataUsualOccupationDurationComponent dst = new OccupationalDataUsualOccupationDurationComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalDataUsualOccupationDurationComponent))
          return false;
        OccupationalDataUsualOccupationDurationComponent o = (OccupationalDataUsualOccupationDurationComponent) other_;
        return compareDeep(code, o.code, true) && compareDeep(value, o.value, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalDataUsualOccupationDurationComponent))
          return false;
        OccupationalDataUsualOccupationDurationComponent o = (OccupationalDataUsualOccupationDurationComponent) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, value);
      }

  public String fhirType() {
    return "OccupationalData.usualOccupation.duration";

  }

  }

    @Block()
    public static class OccupationalDataUsualOccupationIndustryComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Usual Occupation industry code.
         */
        @Child(name = "code", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation industry code", formalDefinition="Usual Occupation industry code." )
        protected CodeableConcept code;

        /**
         * Usual Occupation industry value.
         */
        @Child(name = "value", type = {CodeableConcept.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Usual Occupation industry value", formalDefinition="Usual Occupation industry value." )
        protected CodeableConcept value;

        private static final long serialVersionUID = 1750253426L;

    /**
     * Constructor
     */
      public OccupationalDataUsualOccupationIndustryComponent() {
        super();
      }

    /**
     * Constructor
     */
      public OccupationalDataUsualOccupationIndustryComponent(CodeableConcept code, CodeableConcept value) {
        super();
        this.code = code;
        this.value = value;
      }

        /**
         * @return {@link #code} (Usual Occupation industry code.)
         */
        public CodeableConcept getCode() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationIndustryComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new CodeableConcept(); // cc
          return this.code;
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (Usual Occupation industry code.)
         */
        public OccupationalDataUsualOccupationIndustryComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #value} (Usual Occupation industry value.)
         */
        public CodeableConcept getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataUsualOccupationIndustryComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new CodeableConcept(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Usual Occupation industry value.)
         */
        public OccupationalDataUsualOccupationIndustryComponent setValue(CodeableConcept value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("code", "CodeableConcept", "Usual Occupation industry code.", 0, 1, code));
          children.add(new Property("value", "CodeableConcept", "Usual Occupation industry value.", 0, 1, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3059181: /*code*/  return new Property("code", "CodeableConcept", "Usual Occupation industry code.", 0, 1, code);
          case 111972721: /*value*/  return new Property("value", "CodeableConcept", "Usual Occupation industry value.", 0, 1, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // CodeableConcept
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // CodeableConcept
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          this.code = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 111972721: // value
          this.value = castToCodeableConcept(value); // CodeableConcept
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          this.code = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("value")) {
          this.value = castToCodeableConcept(value); // CodeableConcept
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCode(); 
        case 111972721:  return getValue(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"CodeableConcept"};
        case 111972721: /*value*/ return new String[] {"CodeableConcept"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          this.code = new CodeableConcept();
          return this.code;
        }
        else if (name.equals("value")) {
          this.value = new CodeableConcept();
          return this.value;
        }
        else
          return super.addChild(name);
      }

      public OccupationalDataUsualOccupationIndustryComponent copy() {
        OccupationalDataUsualOccupationIndustryComponent dst = new OccupationalDataUsualOccupationIndustryComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalDataUsualOccupationIndustryComponent))
          return false;
        OccupationalDataUsualOccupationIndustryComponent o = (OccupationalDataUsualOccupationIndustryComponent) other_;
        return compareDeep(code, o.code, true) && compareDeep(value, o.value, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalDataUsualOccupationIndustryComponent))
          return false;
        OccupationalDataUsualOccupationIndustryComponent o = (OccupationalDataUsualOccupationIndustryComponent) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, value);
      }

  public String fhirType() {
    return "OccupationalData.usualOccupation.industry";

  }

  }

    @Block()
    public static class OccupationalDataPastOrPresentOccupationComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Past Or Present Occupation code.
         */
        @Child(name = "code", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Past Or Present Occupation code", formalDefinition="Past Or Present Occupation code." )
        protected CodeableConcept code;

        /**
         * Past Or Present Occupation effective time.
         */
        @Child(name = "effective", type = {DateTimeType.class, Period.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Past Or Present Occupation effective time", formalDefinition="Past Or Present Occupation effective time." )
        protected Type effective;

        /**
         * Past Or Present Occupation value.
         */
        @Child(name = "value", type = {CodeableConcept.class}, order=3, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Past Or Present Occupation value", formalDefinition="Past Or Present Occupation value." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/occupation-cdc-census-2010")
        protected CodeableConcept value;

        private static final long serialVersionUID = -260590813L;

    /**
     * Constructor
     */
      public OccupationalDataPastOrPresentOccupationComponent() {
        super();
      }

    /**
     * Constructor
     */
      public OccupationalDataPastOrPresentOccupationComponent(CodeableConcept code, Type effective, CodeableConcept value) {
        super();
        this.code = code;
        this.effective = effective;
        this.value = value;
      }

        /**
         * @return {@link #code} (Past Or Present Occupation code.)
         */
        public CodeableConcept getCode() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataPastOrPresentOccupationComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new CodeableConcept(); // cc
          return this.code;
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (Past Or Present Occupation code.)
         */
        public OccupationalDataPastOrPresentOccupationComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #effective} (Past Or Present Occupation effective time.)
         */
        public Type getEffective() { 
          return this.effective;
        }

        /**
         * @return {@link #effective} (Past Or Present Occupation effective time.)
         */
        public DateTimeType getEffectiveDateTimeType() throws FHIRException { 
          if (!(this.effective instanceof DateTimeType))
            throw new FHIRException("Type mismatch: the type DateTimeType was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (DateTimeType) this.effective;
        }

        public boolean hasEffectiveDateTimeType() { 
          return this.effective instanceof DateTimeType;
        }

        /**
         * @return {@link #effective} (Past Or Present Occupation effective time.)
         */
        public Period getEffectivePeriod() throws FHIRException { 
          if (!(this.effective instanceof Period))
            throw new FHIRException("Type mismatch: the type Period was expected, but "+this.effective.getClass().getName()+" was encountered");
          return (Period) this.effective;
        }

        public boolean hasEffectivePeriod() { 
          return this.effective instanceof Period;
        }

        public boolean hasEffective() { 
          return this.effective != null && !this.effective.isEmpty();
        }

        /**
         * @param value {@link #effective} (Past Or Present Occupation effective time.)
         */
        public OccupationalDataPastOrPresentOccupationComponent setEffective(Type value) { 
          this.effective = value;
          return this;
        }

        /**
         * @return {@link #value} (Past Or Present Occupation value.)
         */
        public CodeableConcept getValue() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create OccupationalDataPastOrPresentOccupationComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new CodeableConcept(); // cc
          return this.value;
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Past Or Present Occupation value.)
         */
        public OccupationalDataPastOrPresentOccupationComponent setValue(CodeableConcept value) { 
          this.value = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("code", "CodeableConcept", "Past Or Present Occupation code.", 0, 1, code));
          children.add(new Property("effective[x]", "dateTime|Period", "Past Or Present Occupation effective time.", 0, 1, effective));
          children.add(new Property("value", "CodeableConcept", "Past Or Present Occupation value.", 0, 1, value));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 3059181: /*code*/  return new Property("code", "CodeableConcept", "Past Or Present Occupation code.", 0, 1, code);
          case 247104889: /*effective[x]*/  return new Property("effective[x]", "dateTime|Period", "Past Or Present Occupation effective time.", 0, 1, effective);
          case -1468651097: /*effective*/  return new Property("effective[x]", "dateTime|Period", "Past Or Present Occupation effective time.", 0, 1, effective);
          case -275306910: /*effectiveDateTime*/  return new Property("effective[x]", "dateTime|Period", "Past Or Present Occupation effective time.", 0, 1, effective);
          case -403934648: /*effectivePeriod*/  return new Property("effective[x]", "dateTime|Period", "Past Or Present Occupation effective time.", 0, 1, effective);
          case 111972721: /*value*/  return new Property("value", "CodeableConcept", "Past Or Present Occupation value.", 0, 1, value);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // CodeableConcept
        case -1468651097: /*effective*/ return this.effective == null ? new Base[0] : new Base[] {this.effective}; // Type
        case 111972721: /*value*/ return this.value == null ? new Base[0] : new Base[] {this.value}; // CodeableConcept
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          this.code = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1468651097: // effective
          this.effective = castToType(value); // Type
          return value;
        case 111972721: // value
          this.value = castToCodeableConcept(value); // CodeableConcept
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code")) {
          this.code = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("effective[x]")) {
          this.effective = castToType(value); // Type
        } else if (name.equals("value")) {
          this.value = castToCodeableConcept(value); // CodeableConcept
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCode(); 
        case 247104889:  return getEffective(); 
        case -1468651097:  return getEffective(); 
        case 111972721:  return getValue(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return new String[] {"CodeableConcept"};
        case -1468651097: /*effective*/ return new String[] {"dateTime", "Period"};
        case 111972721: /*value*/ return new String[] {"CodeableConcept"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          this.code = new CodeableConcept();
          return this.code;
        }
        else if (name.equals("effectiveDateTime")) {
          this.effective = new DateTimeType();
          return this.effective;
        }
        else if (name.equals("effectivePeriod")) {
          this.effective = new Period();
          return this.effective;
        }
        else if (name.equals("value")) {
          this.value = new CodeableConcept();
          return this.value;
        }
        else
          return super.addChild(name);
      }

      public OccupationalDataPastOrPresentOccupationComponent copy() {
        OccupationalDataPastOrPresentOccupationComponent dst = new OccupationalDataPastOrPresentOccupationComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.effective = effective == null ? null : effective.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalDataPastOrPresentOccupationComponent))
          return false;
        OccupationalDataPastOrPresentOccupationComponent o = (OccupationalDataPastOrPresentOccupationComponent) other_;
        return compareDeep(code, o.code, true) && compareDeep(effective, o.effective, true) && compareDeep(value, o.value, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalDataPastOrPresentOccupationComponent))
          return false;
        OccupationalDataPastOrPresentOccupationComponent o = (OccupationalDataPastOrPresentOccupationComponent) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, effective, value);
      }

  public String fhirType() {
    return "OccupationalData.pastOrPresentOccupation";

  }

  }

    /**
     * Business identifier assigned to the occupational data record.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Unique identifier for the occupational data record", formalDefinition="Business identifier assigned to the occupational data record." )
    protected Identifier identifier;

    /**
     * The status of this {{title}}. Enables tracking the life-cycle of the content.
     */
    @Child(name = "status", type = {CodeType.class}, order=1, min=1, max=1, modifier=true, summary=true)
    @Description(shortDefinition="draft | active | retired | unknown", formalDefinition="The status of this {{title}}. Enables tracking the life-cycle of the content." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/publication-status")
    protected Enumeration<PublicationStatus> status;

    /**
     * Who the occupational data is collected about.
     */
    @Child(name = "subject", type = {Patient.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Who the occupational data is collected about", formalDefinition="Who the occupational data is collected about." )
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (Who the occupational data is collected about.)
     */
    protected Patient subjectTarget;

    /**
     * Occupational Data author time.
     */
    @Child(name = "date", type = {DateTimeType.class}, order=3, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Occupational Data author time", formalDefinition="Occupational Data author time." )
    protected DateTimeType date;

    /**
     * Occupational Data author.
     */
    @Child(name = "author", type = {Practitioner.class, PractitionerRole.class, Patient.class, RelatedPerson.class}, order=4, min=1, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Occupational Data author", formalDefinition="Occupational Data author." )
    protected List<Reference> author;
    /**
     * The actual objects that are the target of the reference (Occupational Data author.)
     */
    protected List<Resource> authorTarget;


    /**
     * Employment status.
     */
    @Child(name = "employmentStatus", type = {}, order=5, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Employment status", formalDefinition="Employment status." )
    protected OccupationalDataEmploymentStatusComponent employmentStatus;

    /**
     * Retirement status.
     */
    @Child(name = "retirementStatus", type = {}, order=6, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Retirement status", formalDefinition="Retirement status." )
    protected OccupationalDataRetirementStatusComponent retirementStatus;

    /**
     * Combat Zone Hazardous Duty.
     */
    @Child(name = "combatZoneHazardousDuty", type = {}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Combat Zone Hazardous Duty", formalDefinition="Combat Zone Hazardous Duty." )
    protected List<OccupationalDataCombatZoneHazardousDutyComponent> combatZoneHazardousDuty;

    /**
     * Usual Occupation.
     */
    @Child(name = "usualOccupation", type = {}, order=8, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Usual Occupation", formalDefinition="Usual Occupation." )
    protected OccupationalDataUsualOccupationComponent usualOccupation;

    /**
     * Past Or Present Occupation.
     */
    @Child(name = "pastOrPresentOccupation", type = {}, order=9, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Past Or Present Occupation", formalDefinition="Past Or Present Occupation." )
    protected OccupationalDataPastOrPresentOccupationComponent pastOrPresentOccupation;

    private static final long serialVersionUID = -747334798L;

  /**
   * Constructor
   */
    public OccupationalData() {
      super();
    }

  /**
   * Constructor
   */
    public OccupationalData(Enumeration<PublicationStatus> status, DateTimeType date) {
      super();
      this.status = status;
      this.date = date;
    }

    /**
     * @return {@link #identifier} (Business identifier assigned to the occupational data record.)
     */
    public Identifier getIdentifier() { 
      if (this.identifier == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.identifier");
        else if (Configuration.doAutoCreate())
          this.identifier = new Identifier(); // cc
      return this.identifier;
    }

    public boolean hasIdentifier() { 
      return this.identifier != null && !this.identifier.isEmpty();
    }

    /**
     * @param value {@link #identifier} (Business identifier assigned to the occupational data record.)
     */
    public OccupationalData setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #status} (The status of this {{title}}. Enables tracking the life-cycle of the content.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<PublicationStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<PublicationStatus>(new PublicationStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (The status of this {{title}}. Enables tracking the life-cycle of the content.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public OccupationalData setStatusElement(Enumeration<PublicationStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of this {{title}}. Enables tracking the life-cycle of the content.
     */
    public PublicationStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of this {{title}}. Enables tracking the life-cycle of the content.
     */
    public OccupationalData setStatus(PublicationStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<PublicationStatus>(new PublicationStatusEnumFactory());
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #subject} (Who the occupational data is collected about.)
     */
    public Reference getSubject() { 
      if (this.subject == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.subject");
        else if (Configuration.doAutoCreate())
          this.subject = new Reference(); // cc
      return this.subject;
    }

    public boolean hasSubject() { 
      return this.subject != null && !this.subject.isEmpty();
    }

    /**
     * @param value {@link #subject} (Who the occupational data is collected about.)
     */
    public OccupationalData setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who the occupational data is collected about.)
     */
    public Patient getSubjectTarget() { 
      if (this.subjectTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.subject");
        else if (Configuration.doAutoCreate())
          this.subjectTarget = new Patient(); // aa
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who the occupational data is collected about.)
     */
    public OccupationalData setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #date} (Occupational Data author time.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      if (this.date == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.date");
        else if (Configuration.doAutoCreate())
          this.date = new DateTimeType(); // bb
      return this.date;
    }

    public boolean hasDateElement() { 
      return this.date != null && !this.date.isEmpty();
    }

    public boolean hasDate() { 
      return this.date != null && !this.date.isEmpty();
    }

    /**
     * @param value {@link #date} (Occupational Data author time.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public OccupationalData setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return Occupational Data author time.
     */
    public Date getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value Occupational Data author time.
     */
    public OccupationalData setDate(Date value) { 
        if (this.date == null)
          this.date = new DateTimeType();
        this.date.setValue(value);
      return this;
    }

    /**
     * @return {@link #author} (Occupational Data author.)
     */
    public List<Reference> getAuthor() { 
      if (this.author == null)
        this.author = new ArrayList<Reference>();
      return this.author;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public OccupationalData setAuthor(List<Reference> theAuthor) { 
      this.author = theAuthor;
      return this;
    }

    public boolean hasAuthor() { 
      if (this.author == null)
        return false;
      for (Reference item : this.author)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addAuthor() { //3
      Reference t = new Reference();
      if (this.author == null)
        this.author = new ArrayList<Reference>();
      this.author.add(t);
      return t;
    }

    public OccupationalData addAuthor(Reference t) { //3
      if (t == null)
        return this;
      if (this.author == null)
        this.author = new ArrayList<Reference>();
      this.author.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #author}, creating it if it does not already exist
     */
    public Reference getAuthorFirstRep() { 
      if (getAuthor().isEmpty()) {
        addAuthor();
      }
      return getAuthor().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Resource> getAuthorTarget() { 
      if (this.authorTarget == null)
        this.authorTarget = new ArrayList<Resource>();
      return this.authorTarget;
    }

    /**
     * @return {@link #employmentStatus} (Employment status.)
     */
    public OccupationalDataEmploymentStatusComponent getEmploymentStatus() { 
      if (this.employmentStatus == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.employmentStatus");
        else if (Configuration.doAutoCreate())
          this.employmentStatus = new OccupationalDataEmploymentStatusComponent(); // cc
      return this.employmentStatus;
    }

    public boolean hasEmploymentStatus() { 
      return this.employmentStatus != null && !this.employmentStatus.isEmpty();
    }

    /**
     * @param value {@link #employmentStatus} (Employment status.)
     */
    public OccupationalData setEmploymentStatus(OccupationalDataEmploymentStatusComponent value) { 
      this.employmentStatus = value;
      return this;
    }

    /**
     * @return {@link #retirementStatus} (Retirement status.)
     */
    public OccupationalDataRetirementStatusComponent getRetirementStatus() { 
      if (this.retirementStatus == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.retirementStatus");
        else if (Configuration.doAutoCreate())
          this.retirementStatus = new OccupationalDataRetirementStatusComponent(); // cc
      return this.retirementStatus;
    }

    public boolean hasRetirementStatus() { 
      return this.retirementStatus != null && !this.retirementStatus.isEmpty();
    }

    /**
     * @param value {@link #retirementStatus} (Retirement status.)
     */
    public OccupationalData setRetirementStatus(OccupationalDataRetirementStatusComponent value) { 
      this.retirementStatus = value;
      return this;
    }

    /**
     * @return {@link #combatZoneHazardousDuty} (Combat Zone Hazardous Duty.)
     */
    public List<OccupationalDataCombatZoneHazardousDutyComponent> getCombatZoneHazardousDuty() { 
      if (this.combatZoneHazardousDuty == null)
        this.combatZoneHazardousDuty = new ArrayList<OccupationalDataCombatZoneHazardousDutyComponent>();
      return this.combatZoneHazardousDuty;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public OccupationalData setCombatZoneHazardousDuty(List<OccupationalDataCombatZoneHazardousDutyComponent> theCombatZoneHazardousDuty) { 
      this.combatZoneHazardousDuty = theCombatZoneHazardousDuty;
      return this;
    }

    public boolean hasCombatZoneHazardousDuty() { 
      if (this.combatZoneHazardousDuty == null)
        return false;
      for (OccupationalDataCombatZoneHazardousDutyComponent item : this.combatZoneHazardousDuty)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public OccupationalDataCombatZoneHazardousDutyComponent addCombatZoneHazardousDuty() { //3
      OccupationalDataCombatZoneHazardousDutyComponent t = new OccupationalDataCombatZoneHazardousDutyComponent();
      if (this.combatZoneHazardousDuty == null)
        this.combatZoneHazardousDuty = new ArrayList<OccupationalDataCombatZoneHazardousDutyComponent>();
      this.combatZoneHazardousDuty.add(t);
      return t;
    }

    public OccupationalData addCombatZoneHazardousDuty(OccupationalDataCombatZoneHazardousDutyComponent t) { //3
      if (t == null)
        return this;
      if (this.combatZoneHazardousDuty == null)
        this.combatZoneHazardousDuty = new ArrayList<OccupationalDataCombatZoneHazardousDutyComponent>();
      this.combatZoneHazardousDuty.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #combatZoneHazardousDuty}, creating it if it does not already exist
     */
    public OccupationalDataCombatZoneHazardousDutyComponent getCombatZoneHazardousDutyFirstRep() { 
      if (getCombatZoneHazardousDuty().isEmpty()) {
        addCombatZoneHazardousDuty();
      }
      return getCombatZoneHazardousDuty().get(0);
    }

    /**
     * @return {@link #usualOccupation} (Usual Occupation.)
     */
    public OccupationalDataUsualOccupationComponent getUsualOccupation() { 
      if (this.usualOccupation == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.usualOccupation");
        else if (Configuration.doAutoCreate())
          this.usualOccupation = new OccupationalDataUsualOccupationComponent(); // cc
      return this.usualOccupation;
    }

    public boolean hasUsualOccupation() { 
      return this.usualOccupation != null && !this.usualOccupation.isEmpty();
    }

    /**
     * @param value {@link #usualOccupation} (Usual Occupation.)
     */
    public OccupationalData setUsualOccupation(OccupationalDataUsualOccupationComponent value) { 
      this.usualOccupation = value;
      return this;
    }

    /**
     * @return {@link #pastOrPresentOccupation} (Past Or Present Occupation.)
     */
    public OccupationalDataPastOrPresentOccupationComponent getPastOrPresentOccupation() { 
      if (this.pastOrPresentOccupation == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OccupationalData.pastOrPresentOccupation");
        else if (Configuration.doAutoCreate())
          this.pastOrPresentOccupation = new OccupationalDataPastOrPresentOccupationComponent(); // cc
      return this.pastOrPresentOccupation;
    }

    public boolean hasPastOrPresentOccupation() { 
      return this.pastOrPresentOccupation != null && !this.pastOrPresentOccupation.isEmpty();
    }

    /**
     * @param value {@link #pastOrPresentOccupation} (Past Or Present Occupation.)
     */
    public OccupationalData setPastOrPresentOccupation(OccupationalDataPastOrPresentOccupationComponent value) { 
      this.pastOrPresentOccupation = value;
      return this;
    }

      protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("identifier", "Identifier", "Business identifier assigned to the occupational data record.", 0, 1, identifier));
        children.add(new Property("status", "code", "The status of this {{title}}. Enables tracking the life-cycle of the content.", 0, 1, status));
        children.add(new Property("subject", "Reference(Patient)", "Who the occupational data is collected about.", 0, 1, subject));
        children.add(new Property("date", "dateTime", "Occupational Data author time.", 0, 1, date));
        children.add(new Property("author", "Reference(Practitioner|PractitionerRole|Patient|RelatedPerson)", "Occupational Data author.", 0, java.lang.Integer.MAX_VALUE, author));
        children.add(new Property("employmentStatus", "", "Employment status.", 0, 1, employmentStatus));
        children.add(new Property("retirementStatus", "", "Retirement status.", 0, 1, retirementStatus));
        children.add(new Property("combatZoneHazardousDuty", "", "Combat Zone Hazardous Duty.", 0, java.lang.Integer.MAX_VALUE, combatZoneHazardousDuty));
        children.add(new Property("usualOccupation", "", "Usual Occupation.", 0, 1, usualOccupation));
        children.add(new Property("pastOrPresentOccupation", "", "Past Or Present Occupation.", 0, 1, pastOrPresentOccupation));
      }

      @Override
      public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
        switch (_hash) {
        case -1618432855: /*identifier*/  return new Property("identifier", "Identifier", "Business identifier assigned to the occupational data record.", 0, 1, identifier);
        case -892481550: /*status*/  return new Property("status", "code", "The status of this {{title}}. Enables tracking the life-cycle of the content.", 0, 1, status);
        case -1867885268: /*subject*/  return new Property("subject", "Reference(Patient)", "Who the occupational data is collected about.", 0, 1, subject);
        case 3076014: /*date*/  return new Property("date", "dateTime", "Occupational Data author time.", 0, 1, date);
        case -1406328437: /*author*/  return new Property("author", "Reference(Practitioner|PractitionerRole|Patient|RelatedPerson)", "Occupational Data author.", 0, java.lang.Integer.MAX_VALUE, author);
        case 418561790: /*employmentStatus*/  return new Property("employmentStatus", "", "Employment status.", 0, 1, employmentStatus);
        case -274416309: /*retirementStatus*/  return new Property("retirementStatus", "", "Retirement status.", 0, 1, retirementStatus);
        case -855561583: /*combatZoneHazardousDuty*/  return new Property("combatZoneHazardousDuty", "", "Combat Zone Hazardous Duty.", 0, java.lang.Integer.MAX_VALUE, combatZoneHazardousDuty);
        case 464548589: /*usualOccupation*/  return new Property("usualOccupation", "", "Usual Occupation.", 0, 1, usualOccupation);
        case 1442028369: /*pastOrPresentOccupation*/  return new Property("pastOrPresentOccupation", "", "Past Or Present Occupation.", 0, 1, pastOrPresentOccupation);
        default: return super.getNamedProperty(_hash, _name, _checkValid);
        }

      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : new Base[] {this.identifier}; // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<PublicationStatus>
        case -1867885268: /*subject*/ return this.subject == null ? new Base[0] : new Base[] {this.subject}; // Reference
        case 3076014: /*date*/ return this.date == null ? new Base[0] : new Base[] {this.date}; // DateTimeType
        case -1406328437: /*author*/ return this.author == null ? new Base[0] : this.author.toArray(new Base[this.author.size()]); // Reference
        case 418561790: /*employmentStatus*/ return this.employmentStatus == null ? new Base[0] : new Base[] {this.employmentStatus}; // OccupationalDataEmploymentStatusComponent
        case -274416309: /*retirementStatus*/ return this.retirementStatus == null ? new Base[0] : new Base[] {this.retirementStatus}; // OccupationalDataRetirementStatusComponent
        case -855561583: /*combatZoneHazardousDuty*/ return this.combatZoneHazardousDuty == null ? new Base[0] : this.combatZoneHazardousDuty.toArray(new Base[this.combatZoneHazardousDuty.size()]); // OccupationalDataCombatZoneHazardousDutyComponent
        case 464548589: /*usualOccupation*/ return this.usualOccupation == null ? new Base[0] : new Base[] {this.usualOccupation}; // OccupationalDataUsualOccupationComponent
        case 1442028369: /*pastOrPresentOccupation*/ return this.pastOrPresentOccupation == null ? new Base[0] : new Base[] {this.pastOrPresentOccupation}; // OccupationalDataPastOrPresentOccupationComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.identifier = castToIdentifier(value); // Identifier
          return value;
        case -892481550: // status
          value = new PublicationStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<PublicationStatus>
          return value;
        case -1867885268: // subject
          this.subject = castToReference(value); // Reference
          return value;
        case 3076014: // date
          this.date = castToDateTime(value); // DateTimeType
          return value;
        case -1406328437: // author
          this.getAuthor().add(castToReference(value)); // Reference
          return value;
        case 418561790: // employmentStatus
          this.employmentStatus = (OccupationalDataEmploymentStatusComponent) value; // OccupationalDataEmploymentStatusComponent
          return value;
        case -274416309: // retirementStatus
          this.retirementStatus = (OccupationalDataRetirementStatusComponent) value; // OccupationalDataRetirementStatusComponent
          return value;
        case -855561583: // combatZoneHazardousDuty
          this.getCombatZoneHazardousDuty().add((OccupationalDataCombatZoneHazardousDutyComponent) value); // OccupationalDataCombatZoneHazardousDutyComponent
          return value;
        case 464548589: // usualOccupation
          this.usualOccupation = (OccupationalDataUsualOccupationComponent) value; // OccupationalDataUsualOccupationComponent
          return value;
        case 1442028369: // pastOrPresentOccupation
          this.pastOrPresentOccupation = (OccupationalDataPastOrPresentOccupationComponent) value; // OccupationalDataPastOrPresentOccupationComponent
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.identifier = castToIdentifier(value); // Identifier
        } else if (name.equals("status")) {
          value = new PublicationStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<PublicationStatus>
        } else if (name.equals("subject")) {
          this.subject = castToReference(value); // Reference
        } else if (name.equals("date")) {
          this.date = castToDateTime(value); // DateTimeType
        } else if (name.equals("author")) {
          this.getAuthor().add(castToReference(value));
        } else if (name.equals("employmentStatus")) {
          this.employmentStatus = (OccupationalDataEmploymentStatusComponent) value; // OccupationalDataEmploymentStatusComponent
        } else if (name.equals("retirementStatus")) {
          this.retirementStatus = (OccupationalDataRetirementStatusComponent) value; // OccupationalDataRetirementStatusComponent
        } else if (name.equals("combatZoneHazardousDuty")) {
          this.getCombatZoneHazardousDuty().add((OccupationalDataCombatZoneHazardousDutyComponent) value);
        } else if (name.equals("usualOccupation")) {
          this.usualOccupation = (OccupationalDataUsualOccupationComponent) value; // OccupationalDataUsualOccupationComponent
        } else if (name.equals("pastOrPresentOccupation")) {
          this.pastOrPresentOccupation = (OccupationalDataPastOrPresentOccupationComponent) value; // OccupationalDataPastOrPresentOccupationComponent
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return getIdentifier(); 
        case -892481550:  return getStatusElement();
        case -1867885268:  return getSubject(); 
        case 3076014:  return getDateElement();
        case -1406328437:  return addAuthor(); 
        case 418561790:  return getEmploymentStatus(); 
        case -274416309:  return getRetirementStatus(); 
        case -855561583:  return addCombatZoneHazardousDuty(); 
        case 464548589:  return getUsualOccupation(); 
        case 1442028369:  return getPastOrPresentOccupation(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case -892481550: /*status*/ return new String[] {"code"};
        case -1867885268: /*subject*/ return new String[] {"Reference"};
        case 3076014: /*date*/ return new String[] {"dateTime"};
        case -1406328437: /*author*/ return new String[] {"Reference"};
        case 418561790: /*employmentStatus*/ return new String[] {};
        case -274416309: /*retirementStatus*/ return new String[] {};
        case -855561583: /*combatZoneHazardousDuty*/ return new String[] {};
        case 464548589: /*usualOccupation*/ return new String[] {};
        case 1442028369: /*pastOrPresentOccupation*/ return new String[] {};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          this.identifier = new Identifier();
          return this.identifier;
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type OccupationalData.status");
        }
        else if (name.equals("subject")) {
          this.subject = new Reference();
          return this.subject;
        }
        else if (name.equals("date")) {
          throw new FHIRException("Cannot call addChild on a primitive type OccupationalData.date");
        }
        else if (name.equals("author")) {
          return addAuthor();
        }
        else if (name.equals("employmentStatus")) {
          this.employmentStatus = new OccupationalDataEmploymentStatusComponent();
          return this.employmentStatus;
        }
        else if (name.equals("retirementStatus")) {
          this.retirementStatus = new OccupationalDataRetirementStatusComponent();
          return this.retirementStatus;
        }
        else if (name.equals("combatZoneHazardousDuty")) {
          return addCombatZoneHazardousDuty();
        }
        else if (name.equals("usualOccupation")) {
          this.usualOccupation = new OccupationalDataUsualOccupationComponent();
          return this.usualOccupation;
        }
        else if (name.equals("pastOrPresentOccupation")) {
          this.pastOrPresentOccupation = new OccupationalDataPastOrPresentOccupationComponent();
          return this.pastOrPresentOccupation;
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "OccupationalData";

  }

      public OccupationalData copy() {
        OccupationalData dst = new OccupationalData();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.date = date == null ? null : date.copy();
        if (author != null) {
          dst.author = new ArrayList<Reference>();
          for (Reference i : author)
            dst.author.add(i.copy());
        };
        dst.employmentStatus = employmentStatus == null ? null : employmentStatus.copy();
        dst.retirementStatus = retirementStatus == null ? null : retirementStatus.copy();
        if (combatZoneHazardousDuty != null) {
          dst.combatZoneHazardousDuty = new ArrayList<OccupationalDataCombatZoneHazardousDutyComponent>();
          for (OccupationalDataCombatZoneHazardousDutyComponent i : combatZoneHazardousDuty)
            dst.combatZoneHazardousDuty.add(i.copy());
        };
        dst.usualOccupation = usualOccupation == null ? null : usualOccupation.copy();
        dst.pastOrPresentOccupation = pastOrPresentOccupation == null ? null : pastOrPresentOccupation.copy();
        return dst;
      }

      protected OccupationalData typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof OccupationalData))
          return false;
        OccupationalData o = (OccupationalData) other_;
        return compareDeep(identifier, o.identifier, true) && compareDeep(status, o.status, true) && compareDeep(subject, o.subject, true)
           && compareDeep(date, o.date, true) && compareDeep(author, o.author, true) && compareDeep(employmentStatus, o.employmentStatus, true)
           && compareDeep(retirementStatus, o.retirementStatus, true) && compareDeep(combatZoneHazardousDuty, o.combatZoneHazardousDuty, true)
           && compareDeep(usualOccupation, o.usualOccupation, true) && compareDeep(pastOrPresentOccupation, o.pastOrPresentOccupation, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof OccupationalData))
          return false;
        OccupationalData o = (OccupationalData) other_;
        return compareValues(status, o.status, true) && compareValues(date, o.date, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, status, subject
          , date, author, employmentStatus, retirementStatus, combatZoneHazardousDuty, usualOccupation
          , pastOrPresentOccupation);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.OccupationalData;
   }

 /**
   * Search parameter: <b>subject</b>
   * <p>
   * Description: <b>Who the occupational data is collected about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>OccupationalData.subject</b><br>
   * </p>
   */
  @SearchParamDefinition(name="subject", path="OccupationalData.subject", description="Who the occupational data is collected about", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Patient") }, target={Patient.class } )
  public static final String SP_SUBJECT = "subject";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>subject</b>
   * <p>
   * Description: <b>Who the occupational data is collected about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>OccupationalData.subject</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam SUBJECT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_SUBJECT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>OccupationalData:subject</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_SUBJECT = new ca.uhn.fhir.model.api.Include("OccupationalData:subject").toLocked();


}

