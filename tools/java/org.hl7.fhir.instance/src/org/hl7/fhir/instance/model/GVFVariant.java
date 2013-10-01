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

// Generated on Tue, Oct 1, 2013 21:45+1000 for FHIR v0.11

import java.util.*;

import java.math.*;
/**
 * A segment of a GVF file.
 */
public class GVFVariant extends Resource {

    public enum FeatureType {
        sNV, // Single Nucleotide Variation.
        Null; // added to help the parsers
        public static FeatureType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("SNV".equals(codeString))
          return sNV;
        throw new Exception("Unknown FeatureType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case sNV: return "SNV";
            default: return "?";
          }
        }
    }

  public class FeatureTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("SNV".equals(codeString))
          return FeatureType.sNV;
        throw new Exception("Unknown FeatureType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == FeatureType.sNV)
        return "SNV";
      return "?";
      }
    }

    public enum Strand {
        Plus, // Plus strand.
        Minus, // Minus strand.
        Null; // added to help the parsers
        public static Strand fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("+".equals(codeString))
          return Plus;
        if ("-".equals(codeString))
          return Minus;
        throw new Exception("Unknown Strand code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case Plus: return "+";
            case Minus: return "-";
            default: return "?";
          }
        }
    }

  public class StrandEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("+".equals(codeString))
          return Strand.Plus;
        if ("-".equals(codeString))
          return Strand.Minus;
        throw new Exception("Unknown Strand code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Strand.Plus)
        return "+";
      if (code == Strand.Minus)
        return "-";
      return "?";
      }
    }

    public enum Database {
        dbSNP, // dbSNP.
        Null; // added to help the parsers
        public static Database fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("dbSNP".equals(codeString))
          return dbSNP;
        throw new Exception("Unknown Database code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case dbSNP: return "dbSNP";
            default: return "?";
          }
        }
    }

  public class DatabaseEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("dbSNP".equals(codeString))
          return Database.dbSNP;
        throw new Exception("Unknown Database code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Database.dbSNP)
        return "dbSNP";
      return "?";
      }
    }

    public enum SequenceVariant {
        nonSynonymousCodon, // Non synonymous codon change.
        Null; // added to help the parsers
        public static SequenceVariant fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("non_synonymous_codon".equals(codeString))
          return nonSynonymousCodon;
        throw new Exception("Unknown SequenceVariant code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case nonSynonymousCodon: return "non_synonymous_codon";
            default: return "?";
          }
        }
    }

  public class SequenceVariantEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("non_synonymous_codon".equals(codeString))
          return SequenceVariant.nonSynonymousCodon;
        throw new Exception("Unknown SequenceVariant code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SequenceVariant.nonSynonymousCodon)
        return "non_synonymous_codon";
      return "?";
      }
    }

    public enum Zygosity {
        homozygous, // 
        heterozygous, // 
        hemizygous, // 
        Null; // added to help the parsers
        public static Zygosity fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("homozygous".equals(codeString))
          return homozygous;
        if ("heterozygous".equals(codeString))
          return heterozygous;
        if ("hemizygous".equals(codeString))
          return hemizygous;
        throw new Exception("Unknown Zygosity code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case homozygous: return "homozygous";
            case heterozygous: return "heterozygous";
            case hemizygous: return "hemizygous";
            default: return "?";
          }
        }
    }

  public class ZygosityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("homozygous".equals(codeString))
          return Zygosity.homozygous;
        if ("heterozygous".equals(codeString))
          return Zygosity.heterozygous;
        if ("hemizygous".equals(codeString))
          return Zygosity.hemizygous;
        throw new Exception("Unknown Zygosity code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Zygosity.homozygous)
        return "homozygous";
      if (code == Zygosity.heterozygous)
        return "heterozygous";
      if (code == Zygosity.hemizygous)
        return "hemizygous";
      return "?";
      }
    }

    public class GVFVariantSubjectComponent extends Element {
        /**
         * Patient resource that stores information of the subejct.
         */
        protected ResourceReference patient;

        /**
         * Id of individual in GVF file that corresponds to the subject (only mandatory if the file is a multi-individual one.
         */
        protected String_ fileId;

        public ResourceReference getPatient() { 
          return this.patient;
        }

        public void setPatient(ResourceReference value) { 
          this.patient = value;
        }

        public String_ getFileId() { 
          return this.fileId;
        }

        public void setFileId(String_ value) { 
          this.fileId = value;
        }

        public String getFileIdSimple() { 
          return this.fileId == null ? null : this.fileId.getValue();
        }

        public void setFileIdSimple(String value) { 
          if (value == null)
            this.fileId = null;
          else {
            if (this.fileId == null)
              this.fileId = new String_();
            this.fileId.setValue(value);
          }
        }

      public GVFVariantSubjectComponent copy(GVFVariant e) {
        GVFVariantSubjectComponent dst = e.new GVFVariantSubjectComponent();
        dst.patient = patient == null ? null : patient.copy();
        dst.fileId = fileId == null ? null : fileId.copy();
        return dst;
      }

  }

    public class GVFVariantDbxrefComponent extends Element {
        /**
         * Name of the database.
         */
        protected Enumeration<Database> database;

        /**
         * Id of the feature within the database.
         */
        protected String_ identity;

        public Enumeration<Database> getDatabase() { 
          return this.database;
        }

        public void setDatabase(Enumeration<Database> value) { 
          this.database = value;
        }

        public Database getDatabaseSimple() { 
          return this.database == null ? null : this.database.getValue();
        }

        public void setDatabaseSimple(Database value) { 
            if (this.database == null)
              this.database = new Enumeration<Database>();
            this.database.setValue(value);
        }

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

      public GVFVariantDbxrefComponent copy(GVFVariant e) {
        GVFVariantDbxrefComponent dst = e.new GVFVariantDbxrefComponent();
        dst.database = database == null ? null : database.copy();
        dst.identity = identity == null ? null : identity.copy();
        return dst;
      }

  }

    public class GVFVariantVariantEffectComponent extends Element {
        /**
         * Effect of the variant.
         */
        protected Enumeration<SequenceVariant> sequenceVariant;

        /**
         * Zero-based index that tells the variant being discussed.
         */
        protected Integer index;

        /**
         * Type of the feature being described.
         */
        protected Enumeration<FeatureType> featureType;

        /**
         * Id of features being affected by the variant.
         */
        protected List<String_> featureId = new ArrayList<String_>();

        public Enumeration<SequenceVariant> getSequenceVariant() { 
          return this.sequenceVariant;
        }

        public void setSequenceVariant(Enumeration<SequenceVariant> value) { 
          this.sequenceVariant = value;
        }

        public SequenceVariant getSequenceVariantSimple() { 
          return this.sequenceVariant == null ? null : this.sequenceVariant.getValue();
        }

        public void setSequenceVariantSimple(SequenceVariant value) { 
            if (this.sequenceVariant == null)
              this.sequenceVariant = new Enumeration<SequenceVariant>();
            this.sequenceVariant.setValue(value);
        }

        public Integer getIndex() { 
          return this.index;
        }

        public void setIndex(Integer value) { 
          this.index = value;
        }

        public int getIndexSimple() { 
          return this.index == null ? null : this.index.getValue();
        }

        public void setIndexSimple(int value) { 
            if (this.index == null)
              this.index = new Integer();
            this.index.setValue(value);
        }

        public Enumeration<FeatureType> getFeatureType() { 
          return this.featureType;
        }

        public void setFeatureType(Enumeration<FeatureType> value) { 
          this.featureType = value;
        }

        public FeatureType getFeatureTypeSimple() { 
          return this.featureType == null ? null : this.featureType.getValue();
        }

        public void setFeatureTypeSimple(FeatureType value) { 
            if (this.featureType == null)
              this.featureType = new Enumeration<FeatureType>();
            this.featureType.setValue(value);
        }

        public List<String_> getFeatureId() { 
          return this.featureId;
        }

    // syntactic sugar
        public String_ addFeatureId() { 
          String_ t = new String_();
          this.featureId.add(t);
          return t;
        }

        public String_ addFeatureIdSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.featureId.add(t);
          return t;
        }

      public GVFVariantVariantEffectComponent copy(GVFVariant e) {
        GVFVariantVariantEffectComponent dst = e.new GVFVariantVariantEffectComponent();
        dst.sequenceVariant = sequenceVariant == null ? null : sequenceVariant.copy();
        dst.index = index == null ? null : index.copy();
        dst.featureType = featureType == null ? null : featureType.copy();
        dst.featureId = new ArrayList<String_>();
        for (String_ i : featureId)
          dst.featureId.add(i.copy());
        return dst;
      }

  }

    public class GVFVariantStartRangeComponent extends Element {
        /**
         * Start of the start range.
         */
        protected Integer start;

        /**
         * End of the start range.
         */
        protected Integer end;

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

      public GVFVariantStartRangeComponent copy(GVFVariant e) {
        GVFVariantStartRangeComponent dst = e.new GVFVariantStartRangeComponent();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        return dst;
      }

  }

    public class GVFVariantEndRangeComponent extends Element {
        /**
         * Start of the end range.
         */
        protected Integer start;

        /**
         * End of the end range.
         */
        protected Integer end;

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

      public GVFVariantEndRangeComponent copy(GVFVariant e) {
        GVFVariantEndRangeComponent dst = e.new GVFVariantEndRangeComponent();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        return dst;
      }

  }

    public class GVFVariantBreakpointDetailComponent extends Element {
        /**
         * Sequence Id of the variant.
         */
        protected String_ seqid;

        /**
         * Start position.
         */
        protected Integer start;

        /**
         * End position.
         */
        protected Integer end;

        /**
         * Direction of strand.
         */
        protected Enumeration<Strand> strand;

        public String_ getSeqid() { 
          return this.seqid;
        }

        public void setSeqid(String_ value) { 
          this.seqid = value;
        }

        public String getSeqidSimple() { 
          return this.seqid == null ? null : this.seqid.getValue();
        }

        public void setSeqidSimple(String value) { 
            if (this.seqid == null)
              this.seqid = new String_();
            this.seqid.setValue(value);
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

        public Enumeration<Strand> getStrand() { 
          return this.strand;
        }

        public void setStrand(Enumeration<Strand> value) { 
          this.strand = value;
        }

        public Strand getStrandSimple() { 
          return this.strand == null ? null : this.strand.getValue();
        }

        public void setStrandSimple(Strand value) { 
            if (this.strand == null)
              this.strand = new Enumeration<Strand>();
            this.strand.setValue(value);
        }

      public GVFVariantBreakpointDetailComponent copy(GVFVariant e) {
        GVFVariantBreakpointDetailComponent dst = e.new GVFVariantBreakpointDetailComponent();
        dst.seqid = seqid == null ? null : seqid.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        dst.strand = strand == null ? null : strand.copy();
        return dst;
      }

  }

    public class GVFVariantSequenceContextComponent extends Element {
        /**
         * Sequence adjacent to the feature at its 5 prime.
         */
        protected String_ fivePrime;

        /**
         * Sequence adjacent to the feature at its 3 prime.
         */
        protected String_ threePrime;

        public String_ getFivePrime() { 
          return this.fivePrime;
        }

        public void setFivePrime(String_ value) { 
          this.fivePrime = value;
        }

        public String getFivePrimeSimple() { 
          return this.fivePrime == null ? null : this.fivePrime.getValue();
        }

        public void setFivePrimeSimple(String value) { 
            if (this.fivePrime == null)
              this.fivePrime = new String_();
            this.fivePrime.setValue(value);
        }

        public String_ getThreePrime() { 
          return this.threePrime;
        }

        public void setThreePrime(String_ value) { 
          this.threePrime = value;
        }

        public String getThreePrimeSimple() { 
          return this.threePrime == null ? null : this.threePrime.getValue();
        }

        public void setThreePrimeSimple(String value) { 
            if (this.threePrime == null)
              this.threePrime = new String_();
            this.threePrime.setValue(value);
        }

      public GVFVariantSequenceContextComponent copy(GVFVariant e) {
        GVFVariantSequenceContextComponent dst = e.new GVFVariantSequenceContextComponent();
        dst.fivePrime = fivePrime == null ? null : fivePrime.copy();
        dst.threePrime = threePrime == null ? null : threePrime.copy();
        return dst;
      }

  }

    public class GVFVariantSampleComponent extends Element {
        /**
         * Attribute describing the phasing of a sequence.
         */
        protected List<String_> phased = new ArrayList<String_>();

        /**
         * Genotypes of the individual.
         */
        protected List<String_> genotype = new ArrayList<String_>();

        /**
         * Renumber of the sequence.
         */
        protected List<Integer> variantReads = new ArrayList<Integer>();

        /**
         * Total reads of all sequence present in the sample.
         */
        protected Integer totalReads;

        /**
         * Zygosity of the sequences.
         */
        protected Enumeration<Zygosity> zygosity;

        public List<String_> getPhased() { 
          return this.phased;
        }

    // syntactic sugar
        public String_ addPhased() { 
          String_ t = new String_();
          this.phased.add(t);
          return t;
        }

        public String_ addPhasedSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.phased.add(t);
          return t;
        }

        public List<String_> getGenotype() { 
          return this.genotype;
        }

    // syntactic sugar
        public String_ addGenotype() { 
          String_ t = new String_();
          this.genotype.add(t);
          return t;
        }

        public String_ addGenotypeSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.genotype.add(t);
          return t;
        }

        public List<Integer> getVariantReads() { 
          return this.variantReads;
        }

    // syntactic sugar
        public Integer addVariantReads() { 
          Integer t = new Integer();
          this.variantReads.add(t);
          return t;
        }

        public Integer addVariantReadsSimple(int value) { 
          Integer t = new Integer();
          t.setValue(value);
          this.variantReads.add(t);
          return t;
        }

        public Integer getTotalReads() { 
          return this.totalReads;
        }

        public void setTotalReads(Integer value) { 
          this.totalReads = value;
        }

        public int getTotalReadsSimple() { 
          return this.totalReads == null ? null : this.totalReads.getValue();
        }

        public void setTotalReadsSimple(int value) { 
          if (value == -1)
            this.totalReads = null;
          else {
            if (this.totalReads == null)
              this.totalReads = new Integer();
            this.totalReads.setValue(value);
          }
        }

        public Enumeration<Zygosity> getZygosity() { 
          return this.zygosity;
        }

        public void setZygosity(Enumeration<Zygosity> value) { 
          this.zygosity = value;
        }

        public Zygosity getZygositySimple() { 
          return this.zygosity == null ? null : this.zygosity.getValue();
        }

        public void setZygositySimple(Zygosity value) { 
          if (value == null)
            this.zygosity = null;
          else {
            if (this.zygosity == null)
              this.zygosity = new Enumeration<Zygosity>();
            this.zygosity.setValue(value);
          }
        }

      public GVFVariantSampleComponent copy(GVFVariant e) {
        GVFVariantSampleComponent dst = e.new GVFVariantSampleComponent();
        dst.phased = new ArrayList<String_>();
        for (String_ i : phased)
          dst.phased.add(i.copy());
        dst.genotype = new ArrayList<String_>();
        for (String_ i : genotype)
          dst.genotype.add(i.copy());
        dst.variantReads = new ArrayList<Integer>();
        for (Integer i : variantReads)
          dst.variantReads.add(i.copy());
        dst.totalReads = totalReads == null ? null : totalReads.copy();
        dst.zygosity = zygosity == null ? null : zygosity.copy();
        return dst;
      }

  }

    /**
     * Subject described by this segment of GVF file.
     */
    protected GVFVariantSubjectComponent subject;

    /**
     * Meta information of a GVF file.
     */
    protected ResourceReference meta;

    /**
     * Source GVF file.
     */
    protected Attachment sourceFile;

    /**
     * Id the sequence being described.
     */
    protected String_ seqid;

    /**
     * Algorithm or software used to generate the data.
     */
    protected String_ source;

    /**
     * Type of the feature being described.
     */
    protected Enumeration<FeatureType> type;

    /**
     * Start position.
     */
    protected Integer start;

    /**
     * End position.
     */
    protected Integer end;

    /**
     * Phred scaled score of the sequence.
     */
    protected Integer score;

    /**
     * Direction of the strand.
     */
    protected Enumeration<Strand> strand;

    /**
     * Id of the attribute, unique to other segments in the same source file.
     */
    protected String_ featureId;

    /**
     * Alias of the feature being described.
     */
    protected String_ alias;

    /**
     * Reference of the feature in a database.
     */
    protected GVFVariantDbxrefComponent dbxref;

    /**
     * Sequence presents in the variant.
     */
    protected List<String_> variantSeq = new ArrayList<String_>();

    /**
     * Reference sequence.
     */
    protected String_ referenceSeq;

    /**
     * Frequency of the variant.
     */
    protected List<Decimal> variantFreq = new ArrayList<Decimal>();

    /**
     * Effect of the variant.
     */
    protected List<GVFVariantVariantEffectComponent> variantEffect = new ArrayList<GVFVariantVariantEffectComponent>();

    /**
     * Attribute describing ambiguity of the start position of the feature.
     */
    protected GVFVariantStartRangeComponent startRange;

    /**
     * Attribute describing ambiguity of the end position of the feature.
     */
    protected GVFVariantEndRangeComponent endRange;

    /**
     * Codons that overlap with the feature being described.
     */
    protected List<String_> variantCodon = new ArrayList<String_>();

    /**
     * Codon that overlap with the reference sequence.
     */
    protected String_ referenceCodon;

    /**
     * Amino acids that overlap with the features being described.
     */
    protected List<String_> variantAA = new ArrayList<String_>();

    /**
     * Amino acids that overlaps with the reference sequence.
     */
    protected List<String_> referenceAA = new ArrayList<String_>();

    /**
     * Coordinate of a variant with zero length.
     */
    protected GVFVariantBreakpointDetailComponent breakpointDetail;

    /**
     * Sequences adjacent to the feature.
     */
    protected GVFVariantSequenceContextComponent sequenceContext;

    /**
     * Individuals for whom the feature is described.
     */
    protected List<String_> individual = new ArrayList<String_>();

    /**
     * Individual genotypic information.
     */
    protected List<GVFVariantSampleComponent> sample = new ArrayList<GVFVariantSampleComponent>();

    public GVFVariantSubjectComponent getSubject() { 
      return this.subject;
    }

    public void setSubject(GVFVariantSubjectComponent value) { 
      this.subject = value;
    }

    public ResourceReference getMeta() { 
      return this.meta;
    }

    public void setMeta(ResourceReference value) { 
      this.meta = value;
    }

    public Attachment getSourceFile() { 
      return this.sourceFile;
    }

    public void setSourceFile(Attachment value) { 
      this.sourceFile = value;
    }

    public String_ getSeqid() { 
      return this.seqid;
    }

    public void setSeqid(String_ value) { 
      this.seqid = value;
    }

    public String getSeqidSimple() { 
      return this.seqid == null ? null : this.seqid.getValue();
    }

    public void setSeqidSimple(String value) { 
        if (this.seqid == null)
          this.seqid = new String_();
        this.seqid.setValue(value);
    }

    public String_ getSource() { 
      return this.source;
    }

    public void setSource(String_ value) { 
      this.source = value;
    }

    public String getSourceSimple() { 
      return this.source == null ? null : this.source.getValue();
    }

    public void setSourceSimple(String value) { 
        if (this.source == null)
          this.source = new String_();
        this.source.setValue(value);
    }

    public Enumeration<FeatureType> getType() { 
      return this.type;
    }

    public void setType(Enumeration<FeatureType> value) { 
      this.type = value;
    }

    public FeatureType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    public void setTypeSimple(FeatureType value) { 
        if (this.type == null)
          this.type = new Enumeration<FeatureType>();
        this.type.setValue(value);
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

    public Integer getScore() { 
      return this.score;
    }

    public void setScore(Integer value) { 
      this.score = value;
    }

    public int getScoreSimple() { 
      return this.score == null ? null : this.score.getValue();
    }

    public void setScoreSimple(int value) { 
        if (this.score == null)
          this.score = new Integer();
        this.score.setValue(value);
    }

    public Enumeration<Strand> getStrand() { 
      return this.strand;
    }

    public void setStrand(Enumeration<Strand> value) { 
      this.strand = value;
    }

    public Strand getStrandSimple() { 
      return this.strand == null ? null : this.strand.getValue();
    }

    public void setStrandSimple(Strand value) { 
        if (this.strand == null)
          this.strand = new Enumeration<Strand>();
        this.strand.setValue(value);
    }

    public String_ getFeatureId() { 
      return this.featureId;
    }

    public void setFeatureId(String_ value) { 
      this.featureId = value;
    }

    public String getFeatureIdSimple() { 
      return this.featureId == null ? null : this.featureId.getValue();
    }

    public void setFeatureIdSimple(String value) { 
        if (this.featureId == null)
          this.featureId = new String_();
        this.featureId.setValue(value);
    }

    public String_ getAlias() { 
      return this.alias;
    }

    public void setAlias(String_ value) { 
      this.alias = value;
    }

    public String getAliasSimple() { 
      return this.alias == null ? null : this.alias.getValue();
    }

    public void setAliasSimple(String value) { 
      if (value == null)
        this.alias = null;
      else {
        if (this.alias == null)
          this.alias = new String_();
        this.alias.setValue(value);
      }
    }

    public GVFVariantDbxrefComponent getDbxref() { 
      return this.dbxref;
    }

    public void setDbxref(GVFVariantDbxrefComponent value) { 
      this.dbxref = value;
    }

    public List<String_> getVariantSeq() { 
      return this.variantSeq;
    }

    // syntactic sugar
    public String_ addVariantSeq() { 
      String_ t = new String_();
      this.variantSeq.add(t);
      return t;
    }

    public String_ addVariantSeqSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.variantSeq.add(t);
      return t;
    }

    public String_ getReferenceSeq() { 
      return this.referenceSeq;
    }

    public void setReferenceSeq(String_ value) { 
      this.referenceSeq = value;
    }

    public String getReferenceSeqSimple() { 
      return this.referenceSeq == null ? null : this.referenceSeq.getValue();
    }

    public void setReferenceSeqSimple(String value) { 
      if (value == null)
        this.referenceSeq = null;
      else {
        if (this.referenceSeq == null)
          this.referenceSeq = new String_();
        this.referenceSeq.setValue(value);
      }
    }

    public List<Decimal> getVariantFreq() { 
      return this.variantFreq;
    }

    // syntactic sugar
    public Decimal addVariantFreq() { 
      Decimal t = new Decimal();
      this.variantFreq.add(t);
      return t;
    }

    public Decimal addVariantFreqSimple(BigDecimal value) { 
      Decimal t = new Decimal();
      t.setValue(value);
      this.variantFreq.add(t);
      return t;
    }

    public List<GVFVariantVariantEffectComponent> getVariantEffect() { 
      return this.variantEffect;
    }

    // syntactic sugar
    public GVFVariantVariantEffectComponent addVariantEffect() { 
      GVFVariantVariantEffectComponent t = new GVFVariantVariantEffectComponent();
      this.variantEffect.add(t);
      return t;
    }

    public GVFVariantStartRangeComponent getStartRange() { 
      return this.startRange;
    }

    public void setStartRange(GVFVariantStartRangeComponent value) { 
      this.startRange = value;
    }

    public GVFVariantEndRangeComponent getEndRange() { 
      return this.endRange;
    }

    public void setEndRange(GVFVariantEndRangeComponent value) { 
      this.endRange = value;
    }

    public List<String_> getVariantCodon() { 
      return this.variantCodon;
    }

    // syntactic sugar
    public String_ addVariantCodon() { 
      String_ t = new String_();
      this.variantCodon.add(t);
      return t;
    }

    public String_ addVariantCodonSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.variantCodon.add(t);
      return t;
    }

    public String_ getReferenceCodon() { 
      return this.referenceCodon;
    }

    public void setReferenceCodon(String_ value) { 
      this.referenceCodon = value;
    }

    public String getReferenceCodonSimple() { 
      return this.referenceCodon == null ? null : this.referenceCodon.getValue();
    }

    public void setReferenceCodonSimple(String value) { 
      if (value == null)
        this.referenceCodon = null;
      else {
        if (this.referenceCodon == null)
          this.referenceCodon = new String_();
        this.referenceCodon.setValue(value);
      }
    }

    public List<String_> getVariantAA() { 
      return this.variantAA;
    }

    // syntactic sugar
    public String_ addVariantAA() { 
      String_ t = new String_();
      this.variantAA.add(t);
      return t;
    }

    public String_ addVariantAASimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.variantAA.add(t);
      return t;
    }

    public List<String_> getReferenceAA() { 
      return this.referenceAA;
    }

    // syntactic sugar
    public String_ addReferenceAA() { 
      String_ t = new String_();
      this.referenceAA.add(t);
      return t;
    }

    public String_ addReferenceAASimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.referenceAA.add(t);
      return t;
    }

    public GVFVariantBreakpointDetailComponent getBreakpointDetail() { 
      return this.breakpointDetail;
    }

    public void setBreakpointDetail(GVFVariantBreakpointDetailComponent value) { 
      this.breakpointDetail = value;
    }

    public GVFVariantSequenceContextComponent getSequenceContext() { 
      return this.sequenceContext;
    }

    public void setSequenceContext(GVFVariantSequenceContextComponent value) { 
      this.sequenceContext = value;
    }

    public List<String_> getIndividual() { 
      return this.individual;
    }

    // syntactic sugar
    public String_ addIndividual() { 
      String_ t = new String_();
      this.individual.add(t);
      return t;
    }

    public String_ addIndividualSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.individual.add(t);
      return t;
    }

    public List<GVFVariantSampleComponent> getSample() { 
      return this.sample;
    }

    // syntactic sugar
    public GVFVariantSampleComponent addSample() { 
      GVFVariantSampleComponent t = new GVFVariantSampleComponent();
      this.sample.add(t);
      return t;
    }

      public GVFVariant copy() {
        GVFVariant dst = new GVFVariant();
        dst.subject = subject == null ? null : subject.copy(dst);
        dst.meta = meta == null ? null : meta.copy();
        dst.sourceFile = sourceFile == null ? null : sourceFile.copy();
        dst.seqid = seqid == null ? null : seqid.copy();
        dst.source = source == null ? null : source.copy();
        dst.type = type == null ? null : type.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        dst.score = score == null ? null : score.copy();
        dst.strand = strand == null ? null : strand.copy();
        dst.featureId = featureId == null ? null : featureId.copy();
        dst.alias = alias == null ? null : alias.copy();
        dst.dbxref = dbxref == null ? null : dbxref.copy(dst);
        dst.variantSeq = new ArrayList<String_>();
        for (String_ i : variantSeq)
          dst.variantSeq.add(i.copy());
        dst.referenceSeq = referenceSeq == null ? null : referenceSeq.copy();
        dst.variantFreq = new ArrayList<Decimal>();
        for (Decimal i : variantFreq)
          dst.variantFreq.add(i.copy());
        dst.variantEffect = new ArrayList<GVFVariantVariantEffectComponent>();
        for (GVFVariantVariantEffectComponent i : variantEffect)
          dst.variantEffect.add(i.copy(dst));
        dst.startRange = startRange == null ? null : startRange.copy(dst);
        dst.endRange = endRange == null ? null : endRange.copy(dst);
        dst.variantCodon = new ArrayList<String_>();
        for (String_ i : variantCodon)
          dst.variantCodon.add(i.copy());
        dst.referenceCodon = referenceCodon == null ? null : referenceCodon.copy();
        dst.variantAA = new ArrayList<String_>();
        for (String_ i : variantAA)
          dst.variantAA.add(i.copy());
        dst.referenceAA = new ArrayList<String_>();
        for (String_ i : referenceAA)
          dst.referenceAA.add(i.copy());
        dst.breakpointDetail = breakpointDetail == null ? null : breakpointDetail.copy(dst);
        dst.sequenceContext = sequenceContext == null ? null : sequenceContext.copy(dst);
        dst.individual = new ArrayList<String_>();
        for (String_ i : individual)
          dst.individual.add(i.copy());
        dst.sample = new ArrayList<GVFVariantSampleComponent>();
        for (GVFVariantSampleComponent i : sample)
          dst.sample.add(i.copy(dst));
        return dst;
      }

      protected GVFVariant typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.GVFVariant;
   }


}

