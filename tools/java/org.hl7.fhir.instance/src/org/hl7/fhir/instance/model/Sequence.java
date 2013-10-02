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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11


import java.math.*;
/**
 * Sequence resource.
 */
public class Sequence extends Resource {

    public enum SequenceType {
        aa, // Amino acid (protein) sequene.
        rna, // RNA sequence.
        dna, // DNA sequence.
        Null; // added to help the parsers
        public static SequenceType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("aa".equals(codeString))
          return aa;
        if ("rna".equals(codeString))
          return rna;
        if ("dna".equals(codeString))
          return dna;
        throw new Exception("Unknown SequenceType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case aa: return "aa";
            case rna: return "rna";
            case dna: return "dna";
            default: return "?";
          }
        }
    }

  public class SequenceTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("aa".equals(codeString))
          return SequenceType.aa;
        if ("rna".equals(codeString))
          return SequenceType.rna;
        if ("dna".equals(codeString))
          return SequenceType.dna;
        throw new Exception("Unknown SequenceType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SequenceType.aa)
        return "aa";
      if (code == SequenceType.rna)
        return "rna";
      if (code == SequenceType.dna)
        return "dna";
      return "?";
      }
    }

    public enum SampleClass {
        germline, // Germline.
        somatic, // Somatic.
        Null; // added to help the parsers
        public static SampleClass fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("germline".equals(codeString))
          return germline;
        if ("somatic".equals(codeString))
          return somatic;
        throw new Exception("Unknown SampleClass code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case germline: return "germline";
            case somatic: return "somatic";
            default: return "?";
          }
        }
    }

  public class SampleClassEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("germline".equals(codeString))
          return SampleClass.germline;
        if ("somatic".equals(codeString))
          return SampleClass.somatic;
        throw new Exception("Unknown SampleClass code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SampleClass.germline)
        return "germline";
      if (code == SampleClass.somatic)
        return "somatic";
      return "?";
      }
    }

    public enum Chromosome {
        chr1, // Chromosome 1.
        chr2, // Chromosome 2.
        chr3, // Chromosome 3.
        chr4, // Chromosome 4.
        chr5, // Chromosome 5.
        chr6, // Chromosome 6.
        chr7, // Chromosome 7.
        chr8, // Chromosome 8.
        chr9, // Chromosome 9.
        chr10, // Chromosome 10.
        chr11, // Chromosome 11.
        chr12, // Chromosome 12.
        chr13, // Chromosome 13.
        chr14, // Chromosome 14.
        chr15, // Chromosome 15.
        chr16, // Chromosome 16.
        chr17, // Chromosome 17.
        chr18, // Chromosome 18.
        chr19, // Chromosome 19.
        chr20, // Chromosome 20.
        chr21, // Chromosome 21.
        chrx, // Chromosome Y.
        chry, // Chromosome X.
        mt, // Mitochondria.
        Null; // added to help the parsers
        public static Chromosome fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("chr1".equals(codeString))
          return chr1;
        if ("chr2".equals(codeString))
          return chr2;
        if ("chr3".equals(codeString))
          return chr3;
        if ("chr4".equals(codeString))
          return chr4;
        if ("chr5".equals(codeString))
          return chr5;
        if ("chr6".equals(codeString))
          return chr6;
        if ("chr7".equals(codeString))
          return chr7;
        if ("chr8".equals(codeString))
          return chr8;
        if ("chr9".equals(codeString))
          return chr9;
        if ("chr10".equals(codeString))
          return chr10;
        if ("chr11".equals(codeString))
          return chr11;
        if ("chr12".equals(codeString))
          return chr12;
        if ("chr13".equals(codeString))
          return chr13;
        if ("chr14".equals(codeString))
          return chr14;
        if ("chr15".equals(codeString))
          return chr15;
        if ("chr16".equals(codeString))
          return chr16;
        if ("chr17".equals(codeString))
          return chr17;
        if ("chr18".equals(codeString))
          return chr18;
        if ("chr19".equals(codeString))
          return chr19;
        if ("chr20".equals(codeString))
          return chr20;
        if ("chr21".equals(codeString))
          return chr21;
        if ("chrx".equals(codeString))
          return chrx;
        if ("chry".equals(codeString))
          return chry;
        if ("mt".equals(codeString))
          return mt;
        throw new Exception("Unknown Chromosome code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case chr1: return "chr1";
            case chr2: return "chr2";
            case chr3: return "chr3";
            case chr4: return "chr4";
            case chr5: return "chr5";
            case chr6: return "chr6";
            case chr7: return "chr7";
            case chr8: return "chr8";
            case chr9: return "chr9";
            case chr10: return "chr10";
            case chr11: return "chr11";
            case chr12: return "chr12";
            case chr13: return "chr13";
            case chr14: return "chr14";
            case chr15: return "chr15";
            case chr16: return "chr16";
            case chr17: return "chr17";
            case chr18: return "chr18";
            case chr19: return "chr19";
            case chr20: return "chr20";
            case chr21: return "chr21";
            case chrx: return "chrx";
            case chry: return "chry";
            case mt: return "mt";
            default: return "?";
          }
        }
    }

  public class ChromosomeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("chr1".equals(codeString))
          return Chromosome.chr1;
        if ("chr2".equals(codeString))
          return Chromosome.chr2;
        if ("chr3".equals(codeString))
          return Chromosome.chr3;
        if ("chr4".equals(codeString))
          return Chromosome.chr4;
        if ("chr5".equals(codeString))
          return Chromosome.chr5;
        if ("chr6".equals(codeString))
          return Chromosome.chr6;
        if ("chr7".equals(codeString))
          return Chromosome.chr7;
        if ("chr8".equals(codeString))
          return Chromosome.chr8;
        if ("chr9".equals(codeString))
          return Chromosome.chr9;
        if ("chr10".equals(codeString))
          return Chromosome.chr10;
        if ("chr11".equals(codeString))
          return Chromosome.chr11;
        if ("chr12".equals(codeString))
          return Chromosome.chr12;
        if ("chr13".equals(codeString))
          return Chromosome.chr13;
        if ("chr14".equals(codeString))
          return Chromosome.chr14;
        if ("chr15".equals(codeString))
          return Chromosome.chr15;
        if ("chr16".equals(codeString))
          return Chromosome.chr16;
        if ("chr17".equals(codeString))
          return Chromosome.chr17;
        if ("chr18".equals(codeString))
          return Chromosome.chr18;
        if ("chr19".equals(codeString))
          return Chromosome.chr19;
        if ("chr20".equals(codeString))
          return Chromosome.chr20;
        if ("chr21".equals(codeString))
          return Chromosome.chr21;
        if ("chrx".equals(codeString))
          return Chromosome.chrx;
        if ("chry".equals(codeString))
          return Chromosome.chry;
        if ("mt".equals(codeString))
          return Chromosome.mt;
        throw new Exception("Unknown Chromosome code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Chromosome.chr1)
        return "chr1";
      if (code == Chromosome.chr2)
        return "chr2";
      if (code == Chromosome.chr3)
        return "chr3";
      if (code == Chromosome.chr4)
        return "chr4";
      if (code == Chromosome.chr5)
        return "chr5";
      if (code == Chromosome.chr6)
        return "chr6";
      if (code == Chromosome.chr7)
        return "chr7";
      if (code == Chromosome.chr8)
        return "chr8";
      if (code == Chromosome.chr9)
        return "chr9";
      if (code == Chromosome.chr10)
        return "chr10";
      if (code == Chromosome.chr11)
        return "chr11";
      if (code == Chromosome.chr12)
        return "chr12";
      if (code == Chromosome.chr13)
        return "chr13";
      if (code == Chromosome.chr14)
        return "chr14";
      if (code == Chromosome.chr15)
        return "chr15";
      if (code == Chromosome.chr16)
        return "chr16";
      if (code == Chromosome.chr17)
        return "chr17";
      if (code == Chromosome.chr18)
        return "chr18";
      if (code == Chromosome.chr19)
        return "chr19";
      if (code == Chromosome.chr20)
        return "chr20";
      if (code == Chromosome.chr21)
        return "chr21";
      if (code == Chromosome.chrx)
        return "chrx";
      if (code == Chromosome.chry)
        return "chry";
      if (code == Chromosome.mt)
        return "mt";
      return "?";
      }
    }

    public class SequenceSampleComponent extends Element {
        /**
         * Type of the sample.
         */
        protected Enumeration<SampleClass> class_;

        /**
         * Source of the sample.
         */
        protected CodeableConcept source;

        public Enumeration<SampleClass> getClass_() { 
          return this.class_;
        }

        public void setClass_(Enumeration<SampleClass> value) { 
          this.class_ = value;
        }

        public SampleClass getClass_Simple() { 
          return this.class_ == null ? null : this.class_.getValue();
        }

        public void setClass_Simple(SampleClass value) { 
            if (this.class_ == null)
              this.class_ = new Enumeration<SampleClass>();
            this.class_.setValue(value);
        }

        public CodeableConcept getSource() { 
          return this.source;
        }

        public void setSource(CodeableConcept value) { 
          this.source = value;
        }

      public SequenceSampleComponent copy(Sequence e) {
        SequenceSampleComponent dst = e.new SequenceSampleComponent();
        dst.class_ = class_ == null ? null : class_.copy();
        dst.source = source == null ? null : source.copy();
        return dst;
      }

  }

    public class SequenceCoordinateComponent extends Element {
        /**
         * Chromosome.
         */
        protected Enumeration<Chromosome> chromosome;

        /**
         * Start position.
         */
        protected Integer start;

        /**
         * End position.
         */
        protected Integer end;

        public Enumeration<Chromosome> getChromosome() { 
          return this.chromosome;
        }

        public void setChromosome(Enumeration<Chromosome> value) { 
          this.chromosome = value;
        }

        public Chromosome getChromosomeSimple() { 
          return this.chromosome == null ? null : this.chromosome.getValue();
        }

        public void setChromosomeSimple(Chromosome value) { 
            if (this.chromosome == null)
              this.chromosome = new Enumeration<Chromosome>();
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

      public SequenceCoordinateComponent copy(Sequence e) {
        SequenceCoordinateComponent dst = e.new SequenceCoordinateComponent();
        dst.chromosome = chromosome == null ? null : chromosome.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        return dst;
      }

  }

    public class SequenceInputVariantComponent extends Element {
        /**
         * ID of the sample in the files that corresponds to the owner of the sequence.
         */
        protected String_ sampleId;

        /**
         * Variant Resoruce that encapsulates a snippet of the file.
         */
        protected ResourceReference variant;

        public String_ getSampleId() { 
          return this.sampleId;
        }

        public void setSampleId(String_ value) { 
          this.sampleId = value;
        }

        public String getSampleIdSimple() { 
          return this.sampleId == null ? null : this.sampleId.getValue();
        }

        public void setSampleIdSimple(String value) { 
          if (value == null)
            this.sampleId = null;
          else {
            if (this.sampleId == null)
              this.sampleId = new String_();
            this.sampleId.setValue(value);
          }
        }

        public ResourceReference getVariant() { 
          return this.variant;
        }

        public void setVariant(ResourceReference value) { 
          this.variant = value;
        }

      public SequenceInputVariantComponent copy(Sequence e) {
        SequenceInputVariantComponent dst = e.new SequenceInputVariantComponent();
        dst.sampleId = sampleId == null ? null : sampleId.copy();
        dst.variant = variant == null ? null : variant.copy();
        return dst;
      }

  }

    /**
     * Owner of the sequence.
     */
    protected ResourceReference patient;

    /**
     * Type of the sequence.
     */
    protected Enumeration<SequenceType> type;

    /**
     * Sepcies of organisms from which sample of the sequence is extracted.
     */
    protected CodeableConcept species;

    /**
     * Source of the sequence.
     */
    protected SequenceSampleComponent sample;

    /**
     * Location of the genome that the sequence is mapped to.
     */
    protected SequenceCoordinateComponent coordinate;

    /**
     * Lab from which data about the sequence is garthered.
     */
    protected ResourceReference inputLab;

    /**
     * Analysis for the sequence.
     */
    protected ResourceReference inputAnalysis;

    /**
     * Snippet of a GVF or VCF files that stores raw information of this sequence.
     */
    protected SequenceInputVariantComponent inputVariant;

    /**
     * Quality of the read in phred scale.
     */
    protected Integer quality;

    /**
     * Log 2 of the quantity of the sequence presented in the sample.
     */
    protected Decimal quantity;

    /**
     * Read of the sequence; for DNA sequence, the two sequences of the same location are separated by a bar, "|".
     */
    protected String_ read;

    public ResourceReference getPatient() { 
      return this.patient;
    }

    public void setPatient(ResourceReference value) { 
      this.patient = value;
    }

    public Enumeration<SequenceType> getType() { 
      return this.type;
    }

    public void setType(Enumeration<SequenceType> value) { 
      this.type = value;
    }

    public SequenceType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    public void setTypeSimple(SequenceType value) { 
        if (this.type == null)
          this.type = new Enumeration<SequenceType>();
        this.type.setValue(value);
    }

    public CodeableConcept getSpecies() { 
      return this.species;
    }

    public void setSpecies(CodeableConcept value) { 
      this.species = value;
    }

    public SequenceSampleComponent getSample() { 
      return this.sample;
    }

    public void setSample(SequenceSampleComponent value) { 
      this.sample = value;
    }

    public SequenceCoordinateComponent getCoordinate() { 
      return this.coordinate;
    }

    public void setCoordinate(SequenceCoordinateComponent value) { 
      this.coordinate = value;
    }

    public ResourceReference getInputLab() { 
      return this.inputLab;
    }

    public void setInputLab(ResourceReference value) { 
      this.inputLab = value;
    }

    public ResourceReference getInputAnalysis() { 
      return this.inputAnalysis;
    }

    public void setInputAnalysis(ResourceReference value) { 
      this.inputAnalysis = value;
    }

    public SequenceInputVariantComponent getInputVariant() { 
      return this.inputVariant;
    }

    public void setInputVariant(SequenceInputVariantComponent value) { 
      this.inputVariant = value;
    }

    public Integer getQuality() { 
      return this.quality;
    }

    public void setQuality(Integer value) { 
      this.quality = value;
    }

    public int getQualitySimple() { 
      return this.quality == null ? null : this.quality.getValue();
    }

    public void setQualitySimple(int value) { 
      if (value == -1)
        this.quality = null;
      else {
        if (this.quality == null)
          this.quality = new Integer();
        this.quality.setValue(value);
      }
    }

    public Decimal getQuantity() { 
      return this.quantity;
    }

    public void setQuantity(Decimal value) { 
      this.quantity = value;
    }

    public BigDecimal getQuantitySimple() { 
      return this.quantity == null ? null : this.quantity.getValue();
    }

    public void setQuantitySimple(BigDecimal value) { 
      if (value == null)
        this.quantity = null;
      else {
        if (this.quantity == null)
          this.quantity = new Decimal();
        this.quantity.setValue(value);
      }
    }

    public String_ getRead() { 
      return this.read;
    }

    public void setRead(String_ value) { 
      this.read = value;
    }

    public String getReadSimple() { 
      return this.read == null ? null : this.read.getValue();
    }

    public void setReadSimple(String value) { 
        if (this.read == null)
          this.read = new String_();
        this.read.setValue(value);
    }

      public Sequence copy() {
        Sequence dst = new Sequence();
        dst.patient = patient == null ? null : patient.copy();
        dst.type = type == null ? null : type.copy();
        dst.species = species == null ? null : species.copy();
        dst.sample = sample == null ? null : sample.copy(dst);
        dst.coordinate = coordinate == null ? null : coordinate.copy(dst);
        dst.inputLab = inputLab == null ? null : inputLab.copy();
        dst.inputAnalysis = inputAnalysis == null ? null : inputAnalysis.copy();
        dst.inputVariant = inputVariant == null ? null : inputVariant.copy(dst);
        dst.quality = quality == null ? null : quality.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.read = read == null ? null : read.copy();
        return dst;
      }

      protected Sequence typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Sequence;
   }


}

