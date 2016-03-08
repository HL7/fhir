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

// Generated on Tue, Mar 8, 2016 22:13+1100 for FHIR v1.3.0

import java.util.*;

import java.math.*;
import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
/**
 * The MeasureReport resource contains the results of evaluating a measure.
 */
@ResourceDef(name="MeasureReport", profile="http://hl7.org/fhir/Profile/MeasureReport")
public class MeasureReport extends DomainResource {

    public enum MeasureReportStatus {
        /**
         * The report is complete and ready for use
         */
        COMPLETE, 
        /**
         * The report is currently being generated
         */
        PENDING, 
        /**
         * An error occurred attempting to generate the report
         */
        ERROR, 
        /**
         * added to help the parsers
         */
        NULL;
        public static MeasureReportStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("complete".equals(codeString))
          return COMPLETE;
        if ("pending".equals(codeString))
          return PENDING;
        if ("error".equals(codeString))
          return ERROR;
        throw new FHIRException("Unknown MeasureReportStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case COMPLETE: return "complete";
            case PENDING: return "pending";
            case ERROR: return "error";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case COMPLETE: return "http://hl7.org/fhir/measure-report-status";
            case PENDING: return "http://hl7.org/fhir/measure-report-status";
            case ERROR: return "http://hl7.org/fhir/measure-report-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case COMPLETE: return "The report is complete and ready for use";
            case PENDING: return "The report is currently being generated";
            case ERROR: return "An error occurred attempting to generate the report";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case COMPLETE: return "Complete";
            case PENDING: return "Pending";
            case ERROR: return "Error";
            default: return "?";
          }
        }
    }

  public static class MeasureReportStatusEnumFactory implements EnumFactory<MeasureReportStatus> {
    public MeasureReportStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("complete".equals(codeString))
          return MeasureReportStatus.COMPLETE;
        if ("pending".equals(codeString))
          return MeasureReportStatus.PENDING;
        if ("error".equals(codeString))
          return MeasureReportStatus.ERROR;
        throw new IllegalArgumentException("Unknown MeasureReportStatus code '"+codeString+"'");
        }
        public Enumeration<MeasureReportStatus> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("complete".equals(codeString))
          return new Enumeration<MeasureReportStatus>(this, MeasureReportStatus.COMPLETE);
        if ("pending".equals(codeString))
          return new Enumeration<MeasureReportStatus>(this, MeasureReportStatus.PENDING);
        if ("error".equals(codeString))
          return new Enumeration<MeasureReportStatus>(this, MeasureReportStatus.ERROR);
        throw new FHIRException("Unknown MeasureReportStatus code '"+codeString+"'");
        }
    public String toCode(MeasureReportStatus code) {
      if (code == MeasureReportStatus.COMPLETE)
        return "complete";
      if (code == MeasureReportStatus.PENDING)
        return "pending";
      if (code == MeasureReportStatus.ERROR)
        return "error";
      return "?";
      }
    public String toSystem(MeasureReportStatus code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class MeasureReportPopulationReportComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The name of the population group as identified in the measure definition.
         */
        @Child(name = "name", type = {StringType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Name of the population group", formalDefinition="The name of the population group as identified in the measure definition." )
        protected StringType name;

        /**
         * The populations that make up the population group, one for each type of population appropriate for the measure.
         */
        @Child(name = "population", type = {}, order=2, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="The populations in the group", formalDefinition="The populations that make up the population group, one for each type of population appropriate for the measure." )
        protected List<MeasureReportPopulationReportPopulationComponent> population;

        /**
         * The measure score.
         */
        @Child(name = "measureScore", type = {DecimalType.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The measure score", formalDefinition="The measure score." )
        protected DecimalType measureScore;

        /**
         * Stratification results.
         */
        @Child(name = "stratifier", type = {}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Stratification results", formalDefinition="Stratification results." )
        protected List<MeasureReportPopulationReportStratifierComponent> stratifier;

        /**
         * Supplemental data elements for the measure provide additional information requested by the measure for each patient involved in the populations.
         */
        @Child(name = "supplementalData", type = {}, order=5, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Supplemental data elements for the measure", formalDefinition="Supplemental data elements for the measure provide additional information requested by the measure for each patient involved in the populations." )
        protected List<MeasureReportPopulationReportSupplementalDataComponent> supplementalData;

        private static final long serialVersionUID = 1746619155L;

    /**
     * Constructor
     */
      public MeasureReportPopulationReportComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MeasureReportPopulationReportComponent(StringType name) {
        super();
        this.name = name;
      }

        /**
         * @return {@link #name} (The name of the population group as identified in the measure definition.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          if (this.name == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportComponent.name");
            else if (Configuration.doAutoCreate())
              this.name = new StringType(); // bb
          return this.name;
        }

        public boolean hasNameElement() { 
          return this.name != null && !this.name.isEmpty();
        }

        public boolean hasName() { 
          return this.name != null && !this.name.isEmpty();
        }

        /**
         * @param value {@link #name} (The name of the population group as identified in the measure definition.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public MeasureReportPopulationReportComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the population group as identified in the measure definition.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the population group as identified in the measure definition.
         */
        public MeasureReportPopulationReportComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #population} (The populations that make up the population group, one for each type of population appropriate for the measure.)
         */
        public List<MeasureReportPopulationReportPopulationComponent> getPopulation() { 
          if (this.population == null)
            this.population = new ArrayList<MeasureReportPopulationReportPopulationComponent>();
          return this.population;
        }

        public boolean hasPopulation() { 
          if (this.population == null)
            return false;
          for (MeasureReportPopulationReportPopulationComponent item : this.population)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #population} (The populations that make up the population group, one for each type of population appropriate for the measure.)
         */
    // syntactic sugar
        public MeasureReportPopulationReportPopulationComponent addPopulation() { //3
          MeasureReportPopulationReportPopulationComponent t = new MeasureReportPopulationReportPopulationComponent();
          if (this.population == null)
            this.population = new ArrayList<MeasureReportPopulationReportPopulationComponent>();
          this.population.add(t);
          return t;
        }

    // syntactic sugar
        public MeasureReportPopulationReportComponent addPopulation(MeasureReportPopulationReportPopulationComponent t) { //3
          if (t == null)
            return this;
          if (this.population == null)
            this.population = new ArrayList<MeasureReportPopulationReportPopulationComponent>();
          this.population.add(t);
          return this;
        }

        /**
         * @return {@link #measureScore} (The measure score.). This is the underlying object with id, value and extensions. The accessor "getMeasureScore" gives direct access to the value
         */
        public DecimalType getMeasureScoreElement() { 
          if (this.measureScore == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportComponent.measureScore");
            else if (Configuration.doAutoCreate())
              this.measureScore = new DecimalType(); // bb
          return this.measureScore;
        }

        public boolean hasMeasureScoreElement() { 
          return this.measureScore != null && !this.measureScore.isEmpty();
        }

        public boolean hasMeasureScore() { 
          return this.measureScore != null && !this.measureScore.isEmpty();
        }

        /**
         * @param value {@link #measureScore} (The measure score.). This is the underlying object with id, value and extensions. The accessor "getMeasureScore" gives direct access to the value
         */
        public MeasureReportPopulationReportComponent setMeasureScoreElement(DecimalType value) { 
          this.measureScore = value;
          return this;
        }

        /**
         * @return The measure score.
         */
        public BigDecimal getMeasureScore() { 
          return this.measureScore == null ? null : this.measureScore.getValue();
        }

        /**
         * @param value The measure score.
         */
        public MeasureReportPopulationReportComponent setMeasureScore(BigDecimal value) { 
          if (value == null)
            this.measureScore = null;
          else {
            if (this.measureScore == null)
              this.measureScore = new DecimalType();
            this.measureScore.setValue(value);
          }
          return this;
        }

        /**
         * @param value The measure score.
         */
        public MeasureReportPopulationReportComponent setMeasureScore(long value) { 
              this.measureScore = new DecimalType();
            this.measureScore.setValue(value);
          return this;
        }

        /**
         * @param value The measure score.
         */
        public MeasureReportPopulationReportComponent setMeasureScore(double value) { 
              this.measureScore = new DecimalType();
            this.measureScore.setValue(value);
          return this;
        }

        /**
         * @return {@link #stratifier} (Stratification results.)
         */
        public List<MeasureReportPopulationReportStratifierComponent> getStratifier() { 
          if (this.stratifier == null)
            this.stratifier = new ArrayList<MeasureReportPopulationReportStratifierComponent>();
          return this.stratifier;
        }

        public boolean hasStratifier() { 
          if (this.stratifier == null)
            return false;
          for (MeasureReportPopulationReportStratifierComponent item : this.stratifier)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #stratifier} (Stratification results.)
         */
    // syntactic sugar
        public MeasureReportPopulationReportStratifierComponent addStratifier() { //3
          MeasureReportPopulationReportStratifierComponent t = new MeasureReportPopulationReportStratifierComponent();
          if (this.stratifier == null)
            this.stratifier = new ArrayList<MeasureReportPopulationReportStratifierComponent>();
          this.stratifier.add(t);
          return t;
        }

    // syntactic sugar
        public MeasureReportPopulationReportComponent addStratifier(MeasureReportPopulationReportStratifierComponent t) { //3
          if (t == null)
            return this;
          if (this.stratifier == null)
            this.stratifier = new ArrayList<MeasureReportPopulationReportStratifierComponent>();
          this.stratifier.add(t);
          return this;
        }

        /**
         * @return {@link #supplementalData} (Supplemental data elements for the measure provide additional information requested by the measure for each patient involved in the populations.)
         */
        public List<MeasureReportPopulationReportSupplementalDataComponent> getSupplementalData() { 
          if (this.supplementalData == null)
            this.supplementalData = new ArrayList<MeasureReportPopulationReportSupplementalDataComponent>();
          return this.supplementalData;
        }

        public boolean hasSupplementalData() { 
          if (this.supplementalData == null)
            return false;
          for (MeasureReportPopulationReportSupplementalDataComponent item : this.supplementalData)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #supplementalData} (Supplemental data elements for the measure provide additional information requested by the measure for each patient involved in the populations.)
         */
    // syntactic sugar
        public MeasureReportPopulationReportSupplementalDataComponent addSupplementalData() { //3
          MeasureReportPopulationReportSupplementalDataComponent t = new MeasureReportPopulationReportSupplementalDataComponent();
          if (this.supplementalData == null)
            this.supplementalData = new ArrayList<MeasureReportPopulationReportSupplementalDataComponent>();
          this.supplementalData.add(t);
          return t;
        }

    // syntactic sugar
        public MeasureReportPopulationReportComponent addSupplementalData(MeasureReportPopulationReportSupplementalDataComponent t) { //3
          if (t == null)
            return this;
          if (this.supplementalData == null)
            this.supplementalData = new ArrayList<MeasureReportPopulationReportSupplementalDataComponent>();
          this.supplementalData.add(t);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of the population group as identified in the measure definition.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("population", "", "The populations that make up the population group, one for each type of population appropriate for the measure.", 0, java.lang.Integer.MAX_VALUE, population));
          childrenList.add(new Property("measureScore", "decimal", "The measure score.", 0, java.lang.Integer.MAX_VALUE, measureScore));
          childrenList.add(new Property("stratifier", "", "Stratification results.", 0, java.lang.Integer.MAX_VALUE, stratifier));
          childrenList.add(new Property("supplementalData", "", "Supplemental data elements for the measure provide additional information requested by the measure for each patient involved in the populations.", 0, java.lang.Integer.MAX_VALUE, supplementalData));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("name"))
          this.name = castToString(value); // StringType
        else if (name.equals("population"))
          this.getPopulation().add((MeasureReportPopulationReportPopulationComponent) value);
        else if (name.equals("measureScore"))
          this.measureScore = castToDecimal(value); // DecimalType
        else if (name.equals("stratifier"))
          this.getStratifier().add((MeasureReportPopulationReportStratifierComponent) value);
        else if (name.equals("supplementalData"))
          this.getSupplementalData().add((MeasureReportPopulationReportSupplementalDataComponent) value);
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("name")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.name");
        }
        else if (name.equals("population")) {
          return addPopulation();
        }
        else if (name.equals("measureScore")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.measureScore");
        }
        else if (name.equals("stratifier")) {
          return addStratifier();
        }
        else if (name.equals("supplementalData")) {
          return addSupplementalData();
        }
        else
          return super.addChild(name);
      }

      public MeasureReportPopulationReportComponent copy() {
        MeasureReportPopulationReportComponent dst = new MeasureReportPopulationReportComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        if (population != null) {
          dst.population = new ArrayList<MeasureReportPopulationReportPopulationComponent>();
          for (MeasureReportPopulationReportPopulationComponent i : population)
            dst.population.add(i.copy());
        };
        dst.measureScore = measureScore == null ? null : measureScore.copy();
        if (stratifier != null) {
          dst.stratifier = new ArrayList<MeasureReportPopulationReportStratifierComponent>();
          for (MeasureReportPopulationReportStratifierComponent i : stratifier)
            dst.stratifier.add(i.copy());
        };
        if (supplementalData != null) {
          dst.supplementalData = new ArrayList<MeasureReportPopulationReportSupplementalDataComponent>();
          for (MeasureReportPopulationReportSupplementalDataComponent i : supplementalData)
            dst.supplementalData.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportComponent))
          return false;
        MeasureReportPopulationReportComponent o = (MeasureReportPopulationReportComponent) other;
        return compareDeep(name, o.name, true) && compareDeep(population, o.population, true) && compareDeep(measureScore, o.measureScore, true)
           && compareDeep(stratifier, o.stratifier, true) && compareDeep(supplementalData, o.supplementalData, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportComponent))
          return false;
        MeasureReportPopulationReportComponent o = (MeasureReportPopulationReportComponent) other;
        return compareValues(name, o.name, true) && compareValues(measureScore, o.measureScore, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (name == null || name.isEmpty()) && (population == null || population.isEmpty())
           && (measureScore == null || measureScore.isEmpty()) && (stratifier == null || stratifier.isEmpty())
           && (supplementalData == null || supplementalData.isEmpty());
      }

  public String fhirType() {
    return "MeasureReport.populationReport";

  }

  }

    @Block()
    public static class MeasureReportPopulationReportPopulationComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The type of the population.
         */
        @Child(name = "type", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="initial-population | numerator | numerator-exclusion | denominator | denominator-exclusion | denominator-exception | measure-population | measure-population-exclusion | measure-score", formalDefinition="The type of the population." )
        protected CodeType type;

        /**
         * The number of members of the population.
         */
        @Child(name = "count", type = {IntegerType.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Size of the population", formalDefinition="The number of members of the population." )
        protected IntegerType count;

        /**
         * Bundle of MeasureResponse resources, one per patient.
         */
        @Child(name = "patients", type = {Bundle.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Bundle of MeasureResponse resources, one per patient", formalDefinition="Bundle of MeasureResponse resources, one per patient." )
        protected Reference patients;

        /**
         * The actual object that is the target of the reference (Bundle of MeasureResponse resources, one per patient.)
         */
        protected Bundle patientsTarget;

        private static final long serialVersionUID = -958834122L;

    /**
     * Constructor
     */
      public MeasureReportPopulationReportPopulationComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MeasureReportPopulationReportPopulationComponent(CodeType type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (The type of the population.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public CodeType getTypeElement() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportPopulationComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeType(); // bb
          return this.type;
        }

        public boolean hasTypeElement() { 
          return this.type != null && !this.type.isEmpty();
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (The type of the population.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public MeasureReportPopulationReportPopulationComponent setTypeElement(CodeType value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of the population.
         */
        public String getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of the population.
         */
        public MeasureReportPopulationReportPopulationComponent setType(String value) { 
            if (this.type == null)
              this.type = new CodeType();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #count} (The number of members of the population.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public IntegerType getCountElement() { 
          if (this.count == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportPopulationComponent.count");
            else if (Configuration.doAutoCreate())
              this.count = new IntegerType(); // bb
          return this.count;
        }

        public boolean hasCountElement() { 
          return this.count != null && !this.count.isEmpty();
        }

        public boolean hasCount() { 
          return this.count != null && !this.count.isEmpty();
        }

        /**
         * @param value {@link #count} (The number of members of the population.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public MeasureReportPopulationReportPopulationComponent setCountElement(IntegerType value) { 
          this.count = value;
          return this;
        }

        /**
         * @return The number of members of the population.
         */
        public int getCount() { 
          return this.count == null || this.count.isEmpty() ? 0 : this.count.getValue();
        }

        /**
         * @param value The number of members of the population.
         */
        public MeasureReportPopulationReportPopulationComponent setCount(int value) { 
            if (this.count == null)
              this.count = new IntegerType();
            this.count.setValue(value);
          return this;
        }

        /**
         * @return {@link #patients} (Bundle of MeasureResponse resources, one per patient.)
         */
        public Reference getPatients() { 
          if (this.patients == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportPopulationComponent.patients");
            else if (Configuration.doAutoCreate())
              this.patients = new Reference(); // cc
          return this.patients;
        }

        public boolean hasPatients() { 
          return this.patients != null && !this.patients.isEmpty();
        }

        /**
         * @param value {@link #patients} (Bundle of MeasureResponse resources, one per patient.)
         */
        public MeasureReportPopulationReportPopulationComponent setPatients(Reference value) { 
          this.patients = value;
          return this;
        }

        /**
         * @return {@link #patients} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Bundle of MeasureResponse resources, one per patient.)
         */
        public Bundle getPatientsTarget() { 
          if (this.patientsTarget == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportPopulationComponent.patients");
            else if (Configuration.doAutoCreate())
              this.patientsTarget = new Bundle(); // aa
          return this.patientsTarget;
        }

        /**
         * @param value {@link #patients} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Bundle of MeasureResponse resources, one per patient.)
         */
        public MeasureReportPopulationReportPopulationComponent setPatientsTarget(Bundle value) { 
          this.patientsTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The type of the population.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("count", "integer", "The number of members of the population.", 0, java.lang.Integer.MAX_VALUE, count));
          childrenList.add(new Property("patients", "Reference(Bundle)", "Bundle of MeasureResponse resources, one per patient.", 0, java.lang.Integer.MAX_VALUE, patients));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type"))
          this.type = castToCode(value); // CodeType
        else if (name.equals("count"))
          this.count = castToInteger(value); // IntegerType
        else if (name.equals("patients"))
          this.patients = castToReference(value); // Reference
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.type");
        }
        else if (name.equals("count")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.count");
        }
        else if (name.equals("patients")) {
          this.patients = new Reference();
          return this.patients;
        }
        else
          return super.addChild(name);
      }

      public MeasureReportPopulationReportPopulationComponent copy() {
        MeasureReportPopulationReportPopulationComponent dst = new MeasureReportPopulationReportPopulationComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.count = count == null ? null : count.copy();
        dst.patients = patients == null ? null : patients.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportPopulationComponent))
          return false;
        MeasureReportPopulationReportPopulationComponent o = (MeasureReportPopulationReportPopulationComponent) other;
        return compareDeep(type, o.type, true) && compareDeep(count, o.count, true) && compareDeep(patients, o.patients, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportPopulationComponent))
          return false;
        MeasureReportPopulationReportPopulationComponent o = (MeasureReportPopulationReportPopulationComponent) other;
        return compareValues(type, o.type, true) && compareValues(count, o.count, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (type == null || type.isEmpty()) && (count == null || count.isEmpty())
           && (patients == null || patients.isEmpty());
      }

  public String fhirType() {
    return "MeasureReport.populationReport.population";

  }

  }

    @Block()
    public static class MeasureReportPopulationReportStratifierComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * When a measure includes multiple stratifiers, there will be a stratifier group for each stratifier defined by the measure.
         */
        @Child(name = "name", type = {StringType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="A group of stratifier data", formalDefinition="When a measure includes multiple stratifiers, there will be a stratifier group for each stratifier defined by the measure." )
        protected StringType name;

        /**
         * The populations that make up the stratifier, one for each type of population appropriate to the measure.
         */
        @Child(name = "population", type = {}, order=2, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="The populations in the stratifier", formalDefinition="The populations that make up the stratifier, one for each type of population appropriate to the measure." )
        protected List<MeasureReportPopulationReportStratifierPopulationComponent> population;

        /**
         * The measure score.
         */
        @Child(name = "measureScore", type = {DecimalType.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The measure score", formalDefinition="The measure score." )
        protected DecimalType measureScore;

        private static final long serialVersionUID = 1604139902L;

    /**
     * Constructor
     */
      public MeasureReportPopulationReportStratifierComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MeasureReportPopulationReportStratifierComponent(StringType name) {
        super();
        this.name = name;
      }

        /**
         * @return {@link #name} (When a measure includes multiple stratifiers, there will be a stratifier group for each stratifier defined by the measure.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          if (this.name == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportStratifierComponent.name");
            else if (Configuration.doAutoCreate())
              this.name = new StringType(); // bb
          return this.name;
        }

        public boolean hasNameElement() { 
          return this.name != null && !this.name.isEmpty();
        }

        public boolean hasName() { 
          return this.name != null && !this.name.isEmpty();
        }

        /**
         * @param value {@link #name} (When a measure includes multiple stratifiers, there will be a stratifier group for each stratifier defined by the measure.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public MeasureReportPopulationReportStratifierComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return When a measure includes multiple stratifiers, there will be a stratifier group for each stratifier defined by the measure.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value When a measure includes multiple stratifiers, there will be a stratifier group for each stratifier defined by the measure.
         */
        public MeasureReportPopulationReportStratifierComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #population} (The populations that make up the stratifier, one for each type of population appropriate to the measure.)
         */
        public List<MeasureReportPopulationReportStratifierPopulationComponent> getPopulation() { 
          if (this.population == null)
            this.population = new ArrayList<MeasureReportPopulationReportStratifierPopulationComponent>();
          return this.population;
        }

        public boolean hasPopulation() { 
          if (this.population == null)
            return false;
          for (MeasureReportPopulationReportStratifierPopulationComponent item : this.population)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #population} (The populations that make up the stratifier, one for each type of population appropriate to the measure.)
         */
    // syntactic sugar
        public MeasureReportPopulationReportStratifierPopulationComponent addPopulation() { //3
          MeasureReportPopulationReportStratifierPopulationComponent t = new MeasureReportPopulationReportStratifierPopulationComponent();
          if (this.population == null)
            this.population = new ArrayList<MeasureReportPopulationReportStratifierPopulationComponent>();
          this.population.add(t);
          return t;
        }

    // syntactic sugar
        public MeasureReportPopulationReportStratifierComponent addPopulation(MeasureReportPopulationReportStratifierPopulationComponent t) { //3
          if (t == null)
            return this;
          if (this.population == null)
            this.population = new ArrayList<MeasureReportPopulationReportStratifierPopulationComponent>();
          this.population.add(t);
          return this;
        }

        /**
         * @return {@link #measureScore} (The measure score.). This is the underlying object with id, value and extensions. The accessor "getMeasureScore" gives direct access to the value
         */
        public DecimalType getMeasureScoreElement() { 
          if (this.measureScore == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportStratifierComponent.measureScore");
            else if (Configuration.doAutoCreate())
              this.measureScore = new DecimalType(); // bb
          return this.measureScore;
        }

        public boolean hasMeasureScoreElement() { 
          return this.measureScore != null && !this.measureScore.isEmpty();
        }

        public boolean hasMeasureScore() { 
          return this.measureScore != null && !this.measureScore.isEmpty();
        }

        /**
         * @param value {@link #measureScore} (The measure score.). This is the underlying object with id, value and extensions. The accessor "getMeasureScore" gives direct access to the value
         */
        public MeasureReportPopulationReportStratifierComponent setMeasureScoreElement(DecimalType value) { 
          this.measureScore = value;
          return this;
        }

        /**
         * @return The measure score.
         */
        public BigDecimal getMeasureScore() { 
          return this.measureScore == null ? null : this.measureScore.getValue();
        }

        /**
         * @param value The measure score.
         */
        public MeasureReportPopulationReportStratifierComponent setMeasureScore(BigDecimal value) { 
          if (value == null)
            this.measureScore = null;
          else {
            if (this.measureScore == null)
              this.measureScore = new DecimalType();
            this.measureScore.setValue(value);
          }
          return this;
        }

        /**
         * @param value The measure score.
         */
        public MeasureReportPopulationReportStratifierComponent setMeasureScore(long value) { 
              this.measureScore = new DecimalType();
            this.measureScore.setValue(value);
          return this;
        }

        /**
         * @param value The measure score.
         */
        public MeasureReportPopulationReportStratifierComponent setMeasureScore(double value) { 
              this.measureScore = new DecimalType();
            this.measureScore.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "When a measure includes multiple stratifiers, there will be a stratifier group for each stratifier defined by the measure.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("population", "", "The populations that make up the stratifier, one for each type of population appropriate to the measure.", 0, java.lang.Integer.MAX_VALUE, population));
          childrenList.add(new Property("measureScore", "decimal", "The measure score.", 0, java.lang.Integer.MAX_VALUE, measureScore));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("name"))
          this.name = castToString(value); // StringType
        else if (name.equals("population"))
          this.getPopulation().add((MeasureReportPopulationReportStratifierPopulationComponent) value);
        else if (name.equals("measureScore"))
          this.measureScore = castToDecimal(value); // DecimalType
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("name")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.name");
        }
        else if (name.equals("population")) {
          return addPopulation();
        }
        else if (name.equals("measureScore")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.measureScore");
        }
        else
          return super.addChild(name);
      }

      public MeasureReportPopulationReportStratifierComponent copy() {
        MeasureReportPopulationReportStratifierComponent dst = new MeasureReportPopulationReportStratifierComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        if (population != null) {
          dst.population = new ArrayList<MeasureReportPopulationReportStratifierPopulationComponent>();
          for (MeasureReportPopulationReportStratifierPopulationComponent i : population)
            dst.population.add(i.copy());
        };
        dst.measureScore = measureScore == null ? null : measureScore.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportStratifierComponent))
          return false;
        MeasureReportPopulationReportStratifierComponent o = (MeasureReportPopulationReportStratifierComponent) other;
        return compareDeep(name, o.name, true) && compareDeep(population, o.population, true) && compareDeep(measureScore, o.measureScore, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportStratifierComponent))
          return false;
        MeasureReportPopulationReportStratifierComponent o = (MeasureReportPopulationReportStratifierComponent) other;
        return compareValues(name, o.name, true) && compareValues(measureScore, o.measureScore, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (name == null || name.isEmpty()) && (population == null || population.isEmpty())
           && (measureScore == null || measureScore.isEmpty());
      }

  public String fhirType() {
    return "MeasureReport.populationReport.stratifier";

  }

  }

    @Block()
    public static class MeasureReportPopulationReportStratifierPopulationComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The type of the population.
         */
        @Child(name = "type", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="initial-population | numerator | numerator-exclusion | denominator | denominator-exclusion | denominator-exception | measure-population | measure-population-exclusion | measure-score", formalDefinition="The type of the population." )
        protected CodeType type;

        /**
         * The number of members of the population.
         */
        @Child(name = "count", type = {IntegerType.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Size of the population", formalDefinition="The number of members of the population." )
        protected IntegerType count;

        /**
         * Bundle of MeasureResponse resources, one per patient.
         */
        @Child(name = "patients", type = {Bundle.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Bundle of MeasureResponse resources, one per patient", formalDefinition="Bundle of MeasureResponse resources, one per patient." )
        protected Reference patients;

        /**
         * The actual object that is the target of the reference (Bundle of MeasureResponse resources, one per patient.)
         */
        protected Bundle patientsTarget;

        private static final long serialVersionUID = -958834122L;

    /**
     * Constructor
     */
      public MeasureReportPopulationReportStratifierPopulationComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MeasureReportPopulationReportStratifierPopulationComponent(CodeType type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (The type of the population.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public CodeType getTypeElement() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportStratifierPopulationComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeType(); // bb
          return this.type;
        }

        public boolean hasTypeElement() { 
          return this.type != null && !this.type.isEmpty();
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (The type of the population.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public MeasureReportPopulationReportStratifierPopulationComponent setTypeElement(CodeType value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of the population.
         */
        public String getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of the population.
         */
        public MeasureReportPopulationReportStratifierPopulationComponent setType(String value) { 
            if (this.type == null)
              this.type = new CodeType();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #count} (The number of members of the population.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public IntegerType getCountElement() { 
          if (this.count == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportStratifierPopulationComponent.count");
            else if (Configuration.doAutoCreate())
              this.count = new IntegerType(); // bb
          return this.count;
        }

        public boolean hasCountElement() { 
          return this.count != null && !this.count.isEmpty();
        }

        public boolean hasCount() { 
          return this.count != null && !this.count.isEmpty();
        }

        /**
         * @param value {@link #count} (The number of members of the population.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public MeasureReportPopulationReportStratifierPopulationComponent setCountElement(IntegerType value) { 
          this.count = value;
          return this;
        }

        /**
         * @return The number of members of the population.
         */
        public int getCount() { 
          return this.count == null || this.count.isEmpty() ? 0 : this.count.getValue();
        }

        /**
         * @param value The number of members of the population.
         */
        public MeasureReportPopulationReportStratifierPopulationComponent setCount(int value) { 
            if (this.count == null)
              this.count = new IntegerType();
            this.count.setValue(value);
          return this;
        }

        /**
         * @return {@link #patients} (Bundle of MeasureResponse resources, one per patient.)
         */
        public Reference getPatients() { 
          if (this.patients == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportStratifierPopulationComponent.patients");
            else if (Configuration.doAutoCreate())
              this.patients = new Reference(); // cc
          return this.patients;
        }

        public boolean hasPatients() { 
          return this.patients != null && !this.patients.isEmpty();
        }

        /**
         * @param value {@link #patients} (Bundle of MeasureResponse resources, one per patient.)
         */
        public MeasureReportPopulationReportStratifierPopulationComponent setPatients(Reference value) { 
          this.patients = value;
          return this;
        }

        /**
         * @return {@link #patients} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Bundle of MeasureResponse resources, one per patient.)
         */
        public Bundle getPatientsTarget() { 
          if (this.patientsTarget == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportStratifierPopulationComponent.patients");
            else if (Configuration.doAutoCreate())
              this.patientsTarget = new Bundle(); // aa
          return this.patientsTarget;
        }

        /**
         * @param value {@link #patients} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Bundle of MeasureResponse resources, one per patient.)
         */
        public MeasureReportPopulationReportStratifierPopulationComponent setPatientsTarget(Bundle value) { 
          this.patientsTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The type of the population.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("count", "integer", "The number of members of the population.", 0, java.lang.Integer.MAX_VALUE, count));
          childrenList.add(new Property("patients", "Reference(Bundle)", "Bundle of MeasureResponse resources, one per patient.", 0, java.lang.Integer.MAX_VALUE, patients));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type"))
          this.type = castToCode(value); // CodeType
        else if (name.equals("count"))
          this.count = castToInteger(value); // IntegerType
        else if (name.equals("patients"))
          this.patients = castToReference(value); // Reference
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.type");
        }
        else if (name.equals("count")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.count");
        }
        else if (name.equals("patients")) {
          this.patients = new Reference();
          return this.patients;
        }
        else
          return super.addChild(name);
      }

      public MeasureReportPopulationReportStratifierPopulationComponent copy() {
        MeasureReportPopulationReportStratifierPopulationComponent dst = new MeasureReportPopulationReportStratifierPopulationComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.count = count == null ? null : count.copy();
        dst.patients = patients == null ? null : patients.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportStratifierPopulationComponent))
          return false;
        MeasureReportPopulationReportStratifierPopulationComponent o = (MeasureReportPopulationReportStratifierPopulationComponent) other;
        return compareDeep(type, o.type, true) && compareDeep(count, o.count, true) && compareDeep(patients, o.patients, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportStratifierPopulationComponent))
          return false;
        MeasureReportPopulationReportStratifierPopulationComponent o = (MeasureReportPopulationReportStratifierPopulationComponent) other;
        return compareValues(type, o.type, true) && compareValues(count, o.count, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (type == null || type.isEmpty()) && (count == null || count.isEmpty())
           && (patients == null || patients.isEmpty());
      }

  public String fhirType() {
    return "MeasureReport.populationReport.stratifier.population";

  }

  }

    @Block()
    public static class MeasureReportPopulationReportSupplementalDataComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The name of the supplemental data element.
         */
        @Child(name = "name", type = {StringType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Name of the supplemental element", formalDefinition="The name of the supplemental data element." )
        protected StringType name;

        /**
         * A group of supplemental data corresponding to a supplemental data element specification in the measure.
         */
        @Child(name = "group", type = {}, order=2, min=1, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="A group of supplemental data", formalDefinition="A group of supplemental data corresponding to a supplemental data element specification in the measure." )
        protected List<MeasureReportPopulationReportSupplementalDataGroupComponent> group;

        private static final long serialVersionUID = -273404200L;

    /**
     * Constructor
     */
      public MeasureReportPopulationReportSupplementalDataComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MeasureReportPopulationReportSupplementalDataComponent(StringType name) {
        super();
        this.name = name;
      }

        /**
         * @return {@link #name} (The name of the supplemental data element.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          if (this.name == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportSupplementalDataComponent.name");
            else if (Configuration.doAutoCreate())
              this.name = new StringType(); // bb
          return this.name;
        }

        public boolean hasNameElement() { 
          return this.name != null && !this.name.isEmpty();
        }

        public boolean hasName() { 
          return this.name != null && !this.name.isEmpty();
        }

        /**
         * @param value {@link #name} (The name of the supplemental data element.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public MeasureReportPopulationReportSupplementalDataComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the supplemental data element.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the supplemental data element.
         */
        public MeasureReportPopulationReportSupplementalDataComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #group} (A group of supplemental data corresponding to a supplemental data element specification in the measure.)
         */
        public List<MeasureReportPopulationReportSupplementalDataGroupComponent> getGroup() { 
          if (this.group == null)
            this.group = new ArrayList<MeasureReportPopulationReportSupplementalDataGroupComponent>();
          return this.group;
        }

        public boolean hasGroup() { 
          if (this.group == null)
            return false;
          for (MeasureReportPopulationReportSupplementalDataGroupComponent item : this.group)
            if (!item.isEmpty())
              return true;
          return false;
        }

        /**
         * @return {@link #group} (A group of supplemental data corresponding to a supplemental data element specification in the measure.)
         */
    // syntactic sugar
        public MeasureReportPopulationReportSupplementalDataGroupComponent addGroup() { //3
          MeasureReportPopulationReportSupplementalDataGroupComponent t = new MeasureReportPopulationReportSupplementalDataGroupComponent();
          if (this.group == null)
            this.group = new ArrayList<MeasureReportPopulationReportSupplementalDataGroupComponent>();
          this.group.add(t);
          return t;
        }

    // syntactic sugar
        public MeasureReportPopulationReportSupplementalDataComponent addGroup(MeasureReportPopulationReportSupplementalDataGroupComponent t) { //3
          if (t == null)
            return this;
          if (this.group == null)
            this.group = new ArrayList<MeasureReportPopulationReportSupplementalDataGroupComponent>();
          this.group.add(t);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of the supplemental data element.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("group", "", "A group of supplemental data corresponding to a supplemental data element specification in the measure.", 0, java.lang.Integer.MAX_VALUE, group));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("name"))
          this.name = castToString(value); // StringType
        else if (name.equals("group"))
          this.getGroup().add((MeasureReportPopulationReportSupplementalDataGroupComponent) value);
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("name")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.name");
        }
        else if (name.equals("group")) {
          return addGroup();
        }
        else
          return super.addChild(name);
      }

      public MeasureReportPopulationReportSupplementalDataComponent copy() {
        MeasureReportPopulationReportSupplementalDataComponent dst = new MeasureReportPopulationReportSupplementalDataComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        if (group != null) {
          dst.group = new ArrayList<MeasureReportPopulationReportSupplementalDataGroupComponent>();
          for (MeasureReportPopulationReportSupplementalDataGroupComponent i : group)
            dst.group.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportSupplementalDataComponent))
          return false;
        MeasureReportPopulationReportSupplementalDataComponent o = (MeasureReportPopulationReportSupplementalDataComponent) other;
        return compareDeep(name, o.name, true) && compareDeep(group, o.group, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportSupplementalDataComponent))
          return false;
        MeasureReportPopulationReportSupplementalDataComponent o = (MeasureReportPopulationReportSupplementalDataComponent) other;
        return compareValues(name, o.name, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (name == null || name.isEmpty()) && (group == null || group.isEmpty())
          ;
      }

  public String fhirType() {
    return "MeasureReport.populationReport.supplementalData";

  }

  }

    @Block()
    public static class MeasureReportPopulationReportSupplementalDataGroupComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The number of patients in the supplemental data group.
         */
        @Child(name = "count", type = {IntegerType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Number of patients in the group", formalDefinition="The number of patients in the supplemental data group." )
        protected IntegerType count;

        /**
         * A bundle containing the patients in this supplemental data group.
         */
        @Child(name = "patients", type = {Bundle.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Bundle of patients", formalDefinition="A bundle containing the patients in this supplemental data group." )
        protected Reference patients;

        /**
         * The actual object that is the target of the reference (A bundle containing the patients in this supplemental data group.)
         */
        protected Bundle patientsTarget;

        private static final long serialVersionUID = 1153236909L;

    /**
     * Constructor
     */
      public MeasureReportPopulationReportSupplementalDataGroupComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MeasureReportPopulationReportSupplementalDataGroupComponent(IntegerType count) {
        super();
        this.count = count;
      }

        /**
         * @return {@link #count} (The number of patients in the supplemental data group.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public IntegerType getCountElement() { 
          if (this.count == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportSupplementalDataGroupComponent.count");
            else if (Configuration.doAutoCreate())
              this.count = new IntegerType(); // bb
          return this.count;
        }

        public boolean hasCountElement() { 
          return this.count != null && !this.count.isEmpty();
        }

        public boolean hasCount() { 
          return this.count != null && !this.count.isEmpty();
        }

        /**
         * @param value {@link #count} (The number of patients in the supplemental data group.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public MeasureReportPopulationReportSupplementalDataGroupComponent setCountElement(IntegerType value) { 
          this.count = value;
          return this;
        }

        /**
         * @return The number of patients in the supplemental data group.
         */
        public int getCount() { 
          return this.count == null || this.count.isEmpty() ? 0 : this.count.getValue();
        }

        /**
         * @param value The number of patients in the supplemental data group.
         */
        public MeasureReportPopulationReportSupplementalDataGroupComponent setCount(int value) { 
            if (this.count == null)
              this.count = new IntegerType();
            this.count.setValue(value);
          return this;
        }

        /**
         * @return {@link #patients} (A bundle containing the patients in this supplemental data group.)
         */
        public Reference getPatients() { 
          if (this.patients == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportSupplementalDataGroupComponent.patients");
            else if (Configuration.doAutoCreate())
              this.patients = new Reference(); // cc
          return this.patients;
        }

        public boolean hasPatients() { 
          return this.patients != null && !this.patients.isEmpty();
        }

        /**
         * @param value {@link #patients} (A bundle containing the patients in this supplemental data group.)
         */
        public MeasureReportPopulationReportSupplementalDataGroupComponent setPatients(Reference value) { 
          this.patients = value;
          return this;
        }

        /**
         * @return {@link #patients} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A bundle containing the patients in this supplemental data group.)
         */
        public Bundle getPatientsTarget() { 
          if (this.patientsTarget == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportPopulationReportSupplementalDataGroupComponent.patients");
            else if (Configuration.doAutoCreate())
              this.patientsTarget = new Bundle(); // aa
          return this.patientsTarget;
        }

        /**
         * @param value {@link #patients} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A bundle containing the patients in this supplemental data group.)
         */
        public MeasureReportPopulationReportSupplementalDataGroupComponent setPatientsTarget(Bundle value) { 
          this.patientsTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("count", "integer", "The number of patients in the supplemental data group.", 0, java.lang.Integer.MAX_VALUE, count));
          childrenList.add(new Property("patients", "Reference(Bundle)", "A bundle containing the patients in this supplemental data group.", 0, java.lang.Integer.MAX_VALUE, patients));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("count"))
          this.count = castToInteger(value); // IntegerType
        else if (name.equals("patients"))
          this.patients = castToReference(value); // Reference
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("count")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.count");
        }
        else if (name.equals("patients")) {
          this.patients = new Reference();
          return this.patients;
        }
        else
          return super.addChild(name);
      }

      public MeasureReportPopulationReportSupplementalDataGroupComponent copy() {
        MeasureReportPopulationReportSupplementalDataGroupComponent dst = new MeasureReportPopulationReportSupplementalDataGroupComponent();
        copyValues(dst);
        dst.count = count == null ? null : count.copy();
        dst.patients = patients == null ? null : patients.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportSupplementalDataGroupComponent))
          return false;
        MeasureReportPopulationReportSupplementalDataGroupComponent o = (MeasureReportPopulationReportSupplementalDataGroupComponent) other;
        return compareDeep(count, o.count, true) && compareDeep(patients, o.patients, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReportPopulationReportSupplementalDataGroupComponent))
          return false;
        MeasureReportPopulationReportSupplementalDataGroupComponent o = (MeasureReportPopulationReportSupplementalDataGroupComponent) other;
        return compareValues(count, o.count, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (count == null || count.isEmpty()) && (patients == null || patients.isEmpty())
          ;
      }

  public String fhirType() {
    return "MeasureReport.populationReport.supplementalData.group";

  }

  }

    @Block()
    public static class MeasureReportEvaluatedResourcesComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Evaluated Resources URL.
         */
        @Child(name = "url", type = {UriType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Evaluated Resources URL", formalDefinition="Evaluated Resources URL." )
        protected UriType url;

        /**
         * Evaluated Resources value.
         */
        @Child(name = "value", type = {StringType.class}, order=2, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Evaluated Resources value", formalDefinition="Evaluated Resources value." )
        protected StringType value;

        private static final long serialVersionUID = 1210309237L;

    /**
     * Constructor
     */
      public MeasureReportEvaluatedResourcesComponent() {
        super();
      }

    /**
     * Constructor
     */
      public MeasureReportEvaluatedResourcesComponent(UriType url, StringType value) {
        super();
        this.url = url;
        this.value = value;
      }

        /**
         * @return {@link #url} (Evaluated Resources URL.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public UriType getUrlElement() { 
          if (this.url == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportEvaluatedResourcesComponent.url");
            else if (Configuration.doAutoCreate())
              this.url = new UriType(); // bb
          return this.url;
        }

        public boolean hasUrlElement() { 
          return this.url != null && !this.url.isEmpty();
        }

        public boolean hasUrl() { 
          return this.url != null && !this.url.isEmpty();
        }

        /**
         * @param value {@link #url} (Evaluated Resources URL.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public MeasureReportEvaluatedResourcesComponent setUrlElement(UriType value) { 
          this.url = value;
          return this;
        }

        /**
         * @return Evaluated Resources URL.
         */
        public String getUrl() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value Evaluated Resources URL.
         */
        public MeasureReportEvaluatedResourcesComponent setUrl(String value) { 
            if (this.url == null)
              this.url = new UriType();
            this.url.setValue(value);
          return this;
        }

        /**
         * @return {@link #value} (Evaluated Resources value.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public StringType getValueElement() { 
          if (this.value == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create MeasureReportEvaluatedResourcesComponent.value");
            else if (Configuration.doAutoCreate())
              this.value = new StringType(); // bb
          return this.value;
        }

        public boolean hasValueElement() { 
          return this.value != null && !this.value.isEmpty();
        }

        public boolean hasValue() { 
          return this.value != null && !this.value.isEmpty();
        }

        /**
         * @param value {@link #value} (Evaluated Resources value.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public MeasureReportEvaluatedResourcesComponent setValueElement(StringType value) { 
          this.value = value;
          return this;
        }

        /**
         * @return Evaluated Resources value.
         */
        public String getValue() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value Evaluated Resources value.
         */
        public MeasureReportEvaluatedResourcesComponent setValue(String value) { 
            if (this.value == null)
              this.value = new StringType();
            this.value.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("url", "uri", "Evaluated Resources URL.", 0, java.lang.Integer.MAX_VALUE, url));
          childrenList.add(new Property("value", "string", "Evaluated Resources value.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("url"))
          this.url = castToUri(value); // UriType
        else if (name.equals("value"))
          this.value = castToString(value); // StringType
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("url")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.url");
        }
        else if (name.equals("value")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.value");
        }
        else
          return super.addChild(name);
      }

      public MeasureReportEvaluatedResourcesComponent copy() {
        MeasureReportEvaluatedResourcesComponent dst = new MeasureReportEvaluatedResourcesComponent();
        copyValues(dst);
        dst.url = url == null ? null : url.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReportEvaluatedResourcesComponent))
          return false;
        MeasureReportEvaluatedResourcesComponent o = (MeasureReportEvaluatedResourcesComponent) other;
        return compareDeep(url, o.url, true) && compareDeep(value, o.value, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReportEvaluatedResourcesComponent))
          return false;
        MeasureReportEvaluatedResourcesComponent o = (MeasureReportEvaluatedResourcesComponent) other;
        return compareValues(url, o.url, true) && compareValues(value, o.value, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && (url == null || url.isEmpty()) && (value == null || value.isEmpty())
          ;
      }

  public String fhirType() {
    return "MeasureReport.evaluatedResources";

  }

  }

    /**
     * A reference to the Measure that was evaluated to produce this report.
     */
    @Child(name = "measure", type = {Measure.class}, order=0, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Measure that was evaluated", formalDefinition="A reference to the Measure that was evaluated to produce this report." )
    protected Reference measure;

    /**
     * The actual object that is the target of the reference (A reference to the Measure that was evaluated to produce this report.)
     */
    protected Measure measureTarget;

    /**
     * Optional Patient if the report was requested for a single patient.
     */
    @Child(name = "patient", type = {Patient.class}, order=1, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Optional Patient", formalDefinition="Optional Patient if the report was requested for a single patient." )
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (Optional Patient if the report was requested for a single patient.)
     */
    protected Patient patientTarget;

    /**
     * Reporting period.
     */
    @Child(name = "period", type = {Period.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Reporting period", formalDefinition="Reporting period." )
    protected Period period;

    /**
     * Response status.
     */
    @Child(name = "status", type = {CodeType.class}, order=3, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="complete | pending | error", formalDefinition="Response status." )
    protected Enumeration<MeasureReportStatus> status;

    /**
     * Population Report.
     */
    @Child(name = "populationReport", type = {}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Population Report", formalDefinition="Population Report." )
    protected List<MeasureReportPopulationReportComponent> populationReport;

    /**
     * Reporting Organization.
     */
    @Child(name = "reportingOrganization", type = {StringType.class}, order=5, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Reporting Organization", formalDefinition="Reporting Organization." )
    protected StringType reportingOrganization;

    /**
     * Resources used in the evaluation of this response.
     */
    @Child(name = "evaluatedResources", type = {}, order=6, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Evaluated Resources", formalDefinition="Resources used in the evaluation of this response." )
    protected MeasureReportEvaluatedResourcesComponent evaluatedResources;

    private static final long serialVersionUID = -328170588L;

  /**
   * Constructor
   */
    public MeasureReport() {
      super();
    }

    /**
     * @return {@link #measure} (A reference to the Measure that was evaluated to produce this report.)
     */
    public Reference getMeasure() { 
      if (this.measure == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.measure");
        else if (Configuration.doAutoCreate())
          this.measure = new Reference(); // cc
      return this.measure;
    }

    public boolean hasMeasure() { 
      return this.measure != null && !this.measure.isEmpty();
    }

    /**
     * @param value {@link #measure} (A reference to the Measure that was evaluated to produce this report.)
     */
    public MeasureReport setMeasure(Reference value) { 
      this.measure = value;
      return this;
    }

    /**
     * @return {@link #measure} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A reference to the Measure that was evaluated to produce this report.)
     */
    public Measure getMeasureTarget() { 
      if (this.measureTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.measure");
        else if (Configuration.doAutoCreate())
          this.measureTarget = new Measure(); // aa
      return this.measureTarget;
    }

    /**
     * @param value {@link #measure} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A reference to the Measure that was evaluated to produce this report.)
     */
    public MeasureReport setMeasureTarget(Measure value) { 
      this.measureTarget = value;
      return this;
    }

    /**
     * @return {@link #patient} (Optional Patient if the report was requested for a single patient.)
     */
    public Reference getPatient() { 
      if (this.patient == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.patient");
        else if (Configuration.doAutoCreate())
          this.patient = new Reference(); // cc
      return this.patient;
    }

    public boolean hasPatient() { 
      return this.patient != null && !this.patient.isEmpty();
    }

    /**
     * @param value {@link #patient} (Optional Patient if the report was requested for a single patient.)
     */
    public MeasureReport setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Optional Patient if the report was requested for a single patient.)
     */
    public Patient getPatientTarget() { 
      if (this.patientTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.patient");
        else if (Configuration.doAutoCreate())
          this.patientTarget = new Patient(); // aa
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Optional Patient if the report was requested for a single patient.)
     */
    public MeasureReport setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #period} (Reporting period.)
     */
    public Period getPeriod() { 
      if (this.period == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.period");
        else if (Configuration.doAutoCreate())
          this.period = new Period(); // cc
      return this.period;
    }

    public boolean hasPeriod() { 
      return this.period != null && !this.period.isEmpty();
    }

    /**
     * @param value {@link #period} (Reporting period.)
     */
    public MeasureReport setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #status} (Response status.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<MeasureReportStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<MeasureReportStatus>(new MeasureReportStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (Response status.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public MeasureReport setStatusElement(Enumeration<MeasureReportStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Response status.
     */
    public MeasureReportStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Response status.
     */
    public MeasureReport setStatus(MeasureReportStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<MeasureReportStatus>(new MeasureReportStatusEnumFactory());
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #populationReport} (Population Report.)
     */
    public List<MeasureReportPopulationReportComponent> getPopulationReport() { 
      if (this.populationReport == null)
        this.populationReport = new ArrayList<MeasureReportPopulationReportComponent>();
      return this.populationReport;
    }

    public boolean hasPopulationReport() { 
      if (this.populationReport == null)
        return false;
      for (MeasureReportPopulationReportComponent item : this.populationReport)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #populationReport} (Population Report.)
     */
    // syntactic sugar
    public MeasureReportPopulationReportComponent addPopulationReport() { //3
      MeasureReportPopulationReportComponent t = new MeasureReportPopulationReportComponent();
      if (this.populationReport == null)
        this.populationReport = new ArrayList<MeasureReportPopulationReportComponent>();
      this.populationReport.add(t);
      return t;
    }

    // syntactic sugar
    public MeasureReport addPopulationReport(MeasureReportPopulationReportComponent t) { //3
      if (t == null)
        return this;
      if (this.populationReport == null)
        this.populationReport = new ArrayList<MeasureReportPopulationReportComponent>();
      this.populationReport.add(t);
      return this;
    }

    /**
     * @return {@link #reportingOrganization} (Reporting Organization.). This is the underlying object with id, value and extensions. The accessor "getReportingOrganization" gives direct access to the value
     */
    public StringType getReportingOrganizationElement() { 
      if (this.reportingOrganization == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.reportingOrganization");
        else if (Configuration.doAutoCreate())
          this.reportingOrganization = new StringType(); // bb
      return this.reportingOrganization;
    }

    public boolean hasReportingOrganizationElement() { 
      return this.reportingOrganization != null && !this.reportingOrganization.isEmpty();
    }

    public boolean hasReportingOrganization() { 
      return this.reportingOrganization != null && !this.reportingOrganization.isEmpty();
    }

    /**
     * @param value {@link #reportingOrganization} (Reporting Organization.). This is the underlying object with id, value and extensions. The accessor "getReportingOrganization" gives direct access to the value
     */
    public MeasureReport setReportingOrganizationElement(StringType value) { 
      this.reportingOrganization = value;
      return this;
    }

    /**
     * @return Reporting Organization.
     */
    public String getReportingOrganization() { 
      return this.reportingOrganization == null ? null : this.reportingOrganization.getValue();
    }

    /**
     * @param value Reporting Organization.
     */
    public MeasureReport setReportingOrganization(String value) { 
      if (Utilities.noString(value))
        this.reportingOrganization = null;
      else {
        if (this.reportingOrganization == null)
          this.reportingOrganization = new StringType();
        this.reportingOrganization.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #evaluatedResources} (Resources used in the evaluation of this response.)
     */
    public MeasureReportEvaluatedResourcesComponent getEvaluatedResources() { 
      if (this.evaluatedResources == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create MeasureReport.evaluatedResources");
        else if (Configuration.doAutoCreate())
          this.evaluatedResources = new MeasureReportEvaluatedResourcesComponent(); // cc
      return this.evaluatedResources;
    }

    public boolean hasEvaluatedResources() { 
      return this.evaluatedResources != null && !this.evaluatedResources.isEmpty();
    }

    /**
     * @param value {@link #evaluatedResources} (Resources used in the evaluation of this response.)
     */
    public MeasureReport setEvaluatedResources(MeasureReportEvaluatedResourcesComponent value) { 
      this.evaluatedResources = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("measure", "Reference(Measure)", "A reference to the Measure that was evaluated to produce this report.", 0, java.lang.Integer.MAX_VALUE, measure));
        childrenList.add(new Property("patient", "Reference(Patient)", "Optional Patient if the report was requested for a single patient.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("period", "Period", "Reporting period.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("status", "code", "Response status.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("populationReport", "", "Population Report.", 0, java.lang.Integer.MAX_VALUE, populationReport));
        childrenList.add(new Property("reportingOrganization", "string", "Reporting Organization.", 0, java.lang.Integer.MAX_VALUE, reportingOrganization));
        childrenList.add(new Property("evaluatedResources", "", "Resources used in the evaluation of this response.", 0, java.lang.Integer.MAX_VALUE, evaluatedResources));
      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("measure"))
          this.measure = castToReference(value); // Reference
        else if (name.equals("patient"))
          this.patient = castToReference(value); // Reference
        else if (name.equals("period"))
          this.period = castToPeriod(value); // Period
        else if (name.equals("status"))
          this.status = new MeasureReportStatusEnumFactory().fromType(value); // Enumeration<MeasureReportStatus>
        else if (name.equals("populationReport"))
          this.getPopulationReport().add((MeasureReportPopulationReportComponent) value);
        else if (name.equals("reportingOrganization"))
          this.reportingOrganization = castToString(value); // StringType
        else if (name.equals("evaluatedResources"))
          this.evaluatedResources = (MeasureReportEvaluatedResourcesComponent) value; // MeasureReportEvaluatedResourcesComponent
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("measure")) {
          this.measure = new Reference();
          return this.measure;
        }
        else if (name.equals("patient")) {
          this.patient = new Reference();
          return this.patient;
        }
        else if (name.equals("period")) {
          this.period = new Period();
          return this.period;
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.status");
        }
        else if (name.equals("populationReport")) {
          return addPopulationReport();
        }
        else if (name.equals("reportingOrganization")) {
          throw new FHIRException("Cannot call addChild on a primitive type MeasureReport.reportingOrganization");
        }
        else if (name.equals("evaluatedResources")) {
          this.evaluatedResources = new MeasureReportEvaluatedResourcesComponent();
          return this.evaluatedResources;
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "MeasureReport";

  }

      public MeasureReport copy() {
        MeasureReport dst = new MeasureReport();
        copyValues(dst);
        dst.measure = measure == null ? null : measure.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.period = period == null ? null : period.copy();
        dst.status = status == null ? null : status.copy();
        if (populationReport != null) {
          dst.populationReport = new ArrayList<MeasureReportPopulationReportComponent>();
          for (MeasureReportPopulationReportComponent i : populationReport)
            dst.populationReport.add(i.copy());
        };
        dst.reportingOrganization = reportingOrganization == null ? null : reportingOrganization.copy();
        dst.evaluatedResources = evaluatedResources == null ? null : evaluatedResources.copy();
        return dst;
      }

      protected MeasureReport typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof MeasureReport))
          return false;
        MeasureReport o = (MeasureReport) other;
        return compareDeep(measure, o.measure, true) && compareDeep(patient, o.patient, true) && compareDeep(period, o.period, true)
           && compareDeep(status, o.status, true) && compareDeep(populationReport, o.populationReport, true)
           && compareDeep(reportingOrganization, o.reportingOrganization, true) && compareDeep(evaluatedResources, o.evaluatedResources, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof MeasureReport))
          return false;
        MeasureReport o = (MeasureReport) other;
        return compareValues(status, o.status, true) && compareValues(reportingOrganization, o.reportingOrganization, true)
          ;
      }

      public boolean isEmpty() {
        return super.isEmpty() && (measure == null || measure.isEmpty()) && (patient == null || patient.isEmpty())
           && (period == null || period.isEmpty()) && (status == null || status.isEmpty()) && (populationReport == null || populationReport.isEmpty())
           && (reportingOrganization == null || reportingOrganization.isEmpty()) && (evaluatedResources == null || evaluatedResources.isEmpty())
          ;
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MeasureReport;
   }

 /**
   * Search parameter: <b>patient</b>
   * <p>
   * Description: <b>The identity of a patient to search for individual measure report results for</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MeasureReport.patient</b><br>
   * </p>
   */
  @SearchParamDefinition(name="patient", path="MeasureReport.patient", description="The identity of a patient to search for individual measure report results for", type="reference" )
  public static final String SP_PATIENT = "patient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>patient</b>
   * <p>
   * Description: <b>The identity of a patient to search for individual measure report results for</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>MeasureReport.patient</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PATIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PATIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>MeasureReport:patient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PATIENT = new ca.uhn.fhir.model.api.Include("MeasureReport:patient").toLocked();


}

