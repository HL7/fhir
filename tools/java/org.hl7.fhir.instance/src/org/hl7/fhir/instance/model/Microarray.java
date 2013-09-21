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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

import java.util.*;

import java.math.*;
/**
 * A resource that displays result of a  microarray.
 */
public class Microarray extends Resource {

    public class MicroarraySubjectComponent extends Element {
        /**
         * Resource that corresponds to the subject.
         */
        protected ResourceReference patient;

        /**
         * Id of the sample that belongs to the subject.
         */
        protected List<String_> sampleId = new ArrayList<String_>();

        public ResourceReference getPatient() { 
          return this.patient;
        }

        public void setPatient(ResourceReference value) { 
          this.patient = value;
        }

        public List<String_> getSampleId() { 
          return this.sampleId;
        }

    // syntactic sugar
        public String_ addSampleId() { 
          String_ t = new String_();
          this.sampleId.add(t);
          return t;
        }

        public String_ addSampleIdSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.sampleId.add(t);
          return t;
        }

      public MicroarraySubjectComponent copy(Microarray e) {
        MicroarraySubjectComponent dst = e.new MicroarraySubjectComponent();
        dst.patient = patient == null ? null : patient.copy();
        dst.sampleId = new ArrayList<String_>();
        for (String_ i : sampleId)
          dst.sampleId.add(i.copy());
        return dst;
      }

  }

    public class MicroarrayScannerComponent extends Element {
        /**
         * Manufactuerer of the scanner.
         */
        protected ResourceReference manufacturer;

        /**
         * Name of scanner model.
         */
        protected String_ name;

        /**
         * Version of the model.
         */
        protected String_ version;

        public ResourceReference getManufacturer() { 
          return this.manufacturer;
        }

        public void setManufacturer(ResourceReference value) { 
          this.manufacturer = value;
        }

        public String_ getName() { 
          return this.name;
        }

        public void setName(String_ value) { 
          this.name = value;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
        }

        public String_ getVersion() { 
          return this.version;
        }

        public void setVersion(String_ value) { 
          this.version = value;
        }

        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        public void setVersionSimple(String value) { 
          if (value == null)
            this.version = null;
          else {
            if (this.version == null)
              this.version = new String_();
            this.version.setValue(value);
          }
        }

      public MicroarrayScannerComponent copy(Microarray e) {
        MicroarrayScannerComponent dst = e.new MicroarrayScannerComponent();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.name = name == null ? null : name.copy();
        dst.version = version == null ? null : version.copy();
        return dst;
      }

  }

    public class MicroarraySampleComponent extends Element {
        /**
         * Id of the sample.
         */
        protected String_ identity;

        /**
         * Organism that the sample belong s to.
         */
        protected CodeableConcept organism;

        /**
         * Specimen used on the grid.
         */
        protected MicroarraySampleSpecimenComponent specimen;

        /**
         * Gene of study.
         */
        protected MicroarraySampleGeneComponent gene;

        /**
         * Intensity(expression) of the gene.
         */
        protected Decimal intensity;

        /**
         * Whether the grid is a control in the experiment.
         */
        protected Boolean isControl;

        public String_ getIdentity() { 
          return this.identity;
        }

        public void setIdentity(String_ value) { 
          this.identity = value;
        }

        public String getIdentitySimple() { 
          return this.identity == null ? null : this.identity.getValue();
        }

        public void setIdentitySimple(String value) { 
            if (this.identity == null)
              this.identity = new String_();
            this.identity.setValue(value);
        }

        public CodeableConcept getOrganism() { 
          return this.organism;
        }

        public void setOrganism(CodeableConcept value) { 
          this.organism = value;
        }

        public MicroarraySampleSpecimenComponent getSpecimen() { 
          return this.specimen;
        }

        public void setSpecimen(MicroarraySampleSpecimenComponent value) { 
          this.specimen = value;
        }

        public MicroarraySampleGeneComponent getGene() { 
          return this.gene;
        }

        public void setGene(MicroarraySampleGeneComponent value) { 
          this.gene = value;
        }

        public Decimal getIntensity() { 
          return this.intensity;
        }

        public void setIntensity(Decimal value) { 
          this.intensity = value;
        }

        public BigDecimal getIntensitySimple() { 
          return this.intensity == null ? null : this.intensity.getValue();
        }

        public void setIntensitySimple(BigDecimal value) { 
            if (this.intensity == null)
              this.intensity = new Decimal();
            this.intensity.setValue(value);
        }

        public Boolean getIsControl() { 
          return this.isControl;
        }

        public void setIsControl(Boolean value) { 
          this.isControl = value;
        }

        public boolean getIsControlSimple() { 
          return this.isControl == null ? null : this.isControl.getValue();
        }

        public void setIsControlSimple(boolean value) { 
            if (this.isControl == null)
              this.isControl = new Boolean();
            this.isControl.setValue(value);
        }

      public MicroarraySampleComponent copy(Microarray e) {
        MicroarraySampleComponent dst = e.new MicroarraySampleComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.organism = organism == null ? null : organism.copy();
        dst.specimen = specimen == null ? null : specimen.copy(e);
        dst.gene = gene == null ? null : gene.copy(e);
        dst.intensity = intensity == null ? null : intensity.copy();
        dst.isControl = isControl == null ? null : isControl.copy();
        return dst;
      }

  }

    public class MicroarraySampleSpecimenComponent extends Element {
        /**
         * Type of the specimen.
         */
        protected String_ type;

        /**
         * Source of the specimen.
         */
        protected CodeableConcept source;

        public String_ getType() { 
          return this.type;
        }

        public void setType(String_ value) { 
          this.type = value;
        }

        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(String value) { 
            if (this.type == null)
              this.type = new String_();
            this.type.setValue(value);
        }

        public CodeableConcept getSource() { 
          return this.source;
        }

        public void setSource(CodeableConcept value) { 
          this.source = value;
        }

      public MicroarraySampleSpecimenComponent copy(Microarray e) {
        MicroarraySampleSpecimenComponent dst = e.new MicroarraySampleSpecimenComponent();
        dst.type = type == null ? null : type.copy();
        dst.source = source == null ? null : source.copy();
        return dst;
      }

  }

    public class MicroarraySampleGeneComponent extends Element {
        /**
         * Identifier of the gene.
         */
        protected String_ identity;

        /**
         * Coordinate of the gene.
         */
        protected MicroarraySampleGeneCoordinateComponent coordinate;

        public String_ getIdentity() { 
          return this.identity;
        }

        public void setIdentity(String_ value) { 
          this.identity = value;
        }

        public String getIdentitySimple() { 
          return this.identity == null ? null : this.identity.getValue();
        }

        public void setIdentitySimple(String value) { 
          if (value == null)
            this.identity = null;
          else {
            if (this.identity == null)
              this.identity = new String_();
            this.identity.setValue(value);
          }
        }

        public MicroarraySampleGeneCoordinateComponent getCoordinate() { 
          return this.coordinate;
        }

        public void setCoordinate(MicroarraySampleGeneCoordinateComponent value) { 
          this.coordinate = value;
        }

      public MicroarraySampleGeneComponent copy(Microarray e) {
        MicroarraySampleGeneComponent dst = e.new MicroarraySampleGeneComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.coordinate = coordinate == null ? null : coordinate.copy(e);
        return dst;
      }

  }

    public class MicroarraySampleGeneCoordinateComponent extends Element {
        /**
         * Chromosome.
         */
        protected String_ chromosome;

        /**
         * Start position.
         */
        protected Integer start;

        /**
         * End position.
         */
        protected Integer end;

        public String_ getChromosome() { 
          return this.chromosome;
        }

        public void setChromosome(String_ value) { 
          this.chromosome = value;
        }

        public String getChromosomeSimple() { 
          return this.chromosome == null ? null : this.chromosome.getValue();
        }

        public void setChromosomeSimple(String value) { 
            if (this.chromosome == null)
              this.chromosome = new String_();
            this.chromosome.setValue(value);
        }

        public Integer getStart() { 
          return this.start;
        }

        public void setStart(Integer value) { 
          this.start = value;
        }

        public int getStartSimple() { 
          return this.start == null ? null : this.start.getValue();
        }

        public void setStartSimple(int value) { 
            if (this.start == null)
              this.start = new Integer();
            this.start.setValue(value);
        }

        public Integer getEnd() { 
          return this.end;
        }

        public void setEnd(Integer value) { 
          this.end = value;
        }

        public int getEndSimple() { 
          return this.end == null ? null : this.end.getValue();
        }

        public void setEndSimple(int value) { 
            if (this.end == null)
              this.end = new Integer();
            this.end.setValue(value);
        }

      public MicroarraySampleGeneCoordinateComponent copy(Microarray e) {
        MicroarraySampleGeneCoordinateComponent dst = e.new MicroarraySampleGeneCoordinateComponent();
        dst.chromosome = chromosome == null ? null : chromosome.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        return dst;
      }

  }

    /**
     * Subject of the microarray.
     */
    protected List<MicroarraySubjectComponent> subject = new ArrayList<MicroarraySubjectComponent>();

    /**
     * Organization that does the microarray.
     */
    protected ResourceReference organization;

    /**
     * Date when result of the microarray is updated.
     */
    protected Date date;

    /**
     * Scanner used in the microarray.
     */
    protected MicroarrayScannerComponent scanner;

    /**
     * Sample of a grid on the chip.
     */
    protected List<MicroarraySampleComponent> sample = new ArrayList<MicroarraySampleComponent>();

    public List<MicroarraySubjectComponent> getSubject() { 
      return this.subject;
    }

    // syntactic sugar
    public MicroarraySubjectComponent addSubject() { 
      MicroarraySubjectComponent t = new MicroarraySubjectComponent();
      this.subject.add(t);
      return t;
    }

    public ResourceReference getOrganization() { 
      return this.organization;
    }

    public void setOrganization(ResourceReference value) { 
      this.organization = value;
    }

    public Date getDate() { 
      return this.date;
    }

    public void setDate(Date value) { 
      this.date = value;
    }

    public String getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    public void setDateSimple(String value) { 
        if (this.date == null)
          this.date = new Date();
        this.date.setValue(value);
    }

    public MicroarrayScannerComponent getScanner() { 
      return this.scanner;
    }

    public void setScanner(MicroarrayScannerComponent value) { 
      this.scanner = value;
    }

    public List<MicroarraySampleComponent> getSample() { 
      return this.sample;
    }

    // syntactic sugar
    public MicroarraySampleComponent addSample() { 
      MicroarraySampleComponent t = new MicroarraySampleComponent();
      this.sample.add(t);
      return t;
    }

      public Microarray copy() {
        Microarray dst = new Microarray();
        dst.subject = new ArrayList<MicroarraySubjectComponent>();
        for (MicroarraySubjectComponent i : subject)
          dst.subject.add(i.copy(dst));
        dst.organization = organization == null ? null : organization.copy();
        dst.date = date == null ? null : date.copy();
        dst.scanner = scanner == null ? null : scanner.copy(dst);
        dst.sample = new ArrayList<MicroarraySampleComponent>();
        for (MicroarraySampleComponent i : sample)
          dst.sample.add(i.copy(dst));
        return dst;
      }

      protected Microarray typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Microarray;
   }


}

