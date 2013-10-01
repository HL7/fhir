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

/**
 * Analysis of a patient's genetic test.
 */
public class GeneticAnalysis extends Resource {

    public class GeneticAnalysisGeneticAnalysisSummaryComponent extends Element {
        /**
         * Genetic disease being assesed.
         */
        protected Coding geneticDiseaseAssessed;

        /**
         * Medication being assesed.
         */
        protected Coding medicationAssesed;

        /**
         * Class of the source of sample.
         */
        protected Coding genomicSourceClass;

        /**
         * Overall interpretation of the patient's genotype on the genetic disease being assesed.
         */
        protected Coding geneticDiseaseAnalysisOverallInterpretation;

        /**
         * Carrier status of the patietn.
         */
        protected Coding geneticDiseaseAnalysisOverallCarrierInterpertation;

        /**
         * Analysis on the efficacy of the drug being assessed.
         */
        protected Coding drugEfficacyAnalysisOverallInterpretation;

        /**
         * Summary of the analysis.
         */
        protected String_ geneticAnalysisSummaryReport;

        /**
         * Additional notes.
         */
        protected String_ reasonForStudyAdditionalNote;

        public Coding getGeneticDiseaseAssessed() { 
          return this.geneticDiseaseAssessed;
        }

        public void setGeneticDiseaseAssessed(Coding value) { 
          this.geneticDiseaseAssessed = value;
        }

        public Coding getMedicationAssesed() { 
          return this.medicationAssesed;
        }

        public void setMedicationAssesed(Coding value) { 
          this.medicationAssesed = value;
        }

        public Coding getGenomicSourceClass() { 
          return this.genomicSourceClass;
        }

        public void setGenomicSourceClass(Coding value) { 
          this.genomicSourceClass = value;
        }

        public Coding getGeneticDiseaseAnalysisOverallInterpretation() { 
          return this.geneticDiseaseAnalysisOverallInterpretation;
        }

        public void setGeneticDiseaseAnalysisOverallInterpretation(Coding value) { 
          this.geneticDiseaseAnalysisOverallInterpretation = value;
        }

        public Coding getGeneticDiseaseAnalysisOverallCarrierInterpertation() { 
          return this.geneticDiseaseAnalysisOverallCarrierInterpertation;
        }

        public void setGeneticDiseaseAnalysisOverallCarrierInterpertation(Coding value) { 
          this.geneticDiseaseAnalysisOverallCarrierInterpertation = value;
        }

        public Coding getDrugEfficacyAnalysisOverallInterpretation() { 
          return this.drugEfficacyAnalysisOverallInterpretation;
        }

        public void setDrugEfficacyAnalysisOverallInterpretation(Coding value) { 
          this.drugEfficacyAnalysisOverallInterpretation = value;
        }

        public String_ getGeneticAnalysisSummaryReport() { 
          return this.geneticAnalysisSummaryReport;
        }

        public void setGeneticAnalysisSummaryReport(String_ value) { 
          this.geneticAnalysisSummaryReport = value;
        }

        public String getGeneticAnalysisSummaryReportSimple() { 
          return this.geneticAnalysisSummaryReport == null ? null : this.geneticAnalysisSummaryReport.getValue();
        }

        public void setGeneticAnalysisSummaryReportSimple(String value) { 
          if (value == null)
            this.geneticAnalysisSummaryReport = null;
          else {
            if (this.geneticAnalysisSummaryReport == null)
              this.geneticAnalysisSummaryReport = new String_();
            this.geneticAnalysisSummaryReport.setValue(value);
          }
        }

        public String_ getReasonForStudyAdditionalNote() { 
          return this.reasonForStudyAdditionalNote;
        }

        public void setReasonForStudyAdditionalNote(String_ value) { 
          this.reasonForStudyAdditionalNote = value;
        }

        public String getReasonForStudyAdditionalNoteSimple() { 
          return this.reasonForStudyAdditionalNote == null ? null : this.reasonForStudyAdditionalNote.getValue();
        }

        public void setReasonForStudyAdditionalNoteSimple(String value) { 
          if (value == null)
            this.reasonForStudyAdditionalNote = null;
          else {
            if (this.reasonForStudyAdditionalNote == null)
              this.reasonForStudyAdditionalNote = new String_();
            this.reasonForStudyAdditionalNote.setValue(value);
          }
        }

      public GeneticAnalysisGeneticAnalysisSummaryComponent copy(GeneticAnalysis e) {
        GeneticAnalysisGeneticAnalysisSummaryComponent dst = e.new GeneticAnalysisGeneticAnalysisSummaryComponent();
        dst.geneticDiseaseAssessed = geneticDiseaseAssessed == null ? null : geneticDiseaseAssessed.copy();
        dst.medicationAssesed = medicationAssesed == null ? null : medicationAssesed.copy();
        dst.genomicSourceClass = genomicSourceClass == null ? null : genomicSourceClass.copy();
        dst.geneticDiseaseAnalysisOverallInterpretation = geneticDiseaseAnalysisOverallInterpretation == null ? null : geneticDiseaseAnalysisOverallInterpretation.copy();
        dst.geneticDiseaseAnalysisOverallCarrierInterpertation = geneticDiseaseAnalysisOverallCarrierInterpertation == null ? null : geneticDiseaseAnalysisOverallCarrierInterpertation.copy();
        dst.drugEfficacyAnalysisOverallInterpretation = drugEfficacyAnalysisOverallInterpretation == null ? null : drugEfficacyAnalysisOverallInterpretation.copy();
        dst.geneticAnalysisSummaryReport = geneticAnalysisSummaryReport == null ? null : geneticAnalysisSummaryReport.copy();
        dst.reasonForStudyAdditionalNote = reasonForStudyAdditionalNote == null ? null : reasonForStudyAdditionalNote.copy();
        return dst;
      }

  }

    public class GeneticAnalysisDnaRegionAnalysisTestCoverageComponent extends Element {
        /**
         * DNA studied.
         */
        protected List<GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent> dnaRegionOfInterest = new ArrayList<GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent>();

        public List<GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent> getDnaRegionOfInterest() { 
          return this.dnaRegionOfInterest;
        }

    // syntactic sugar
        public GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent addDnaRegionOfInterest() { 
          GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent t = new GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent();
          this.dnaRegionOfInterest.add(t);
          return t;
        }

      public GeneticAnalysisDnaRegionAnalysisTestCoverageComponent copy(GeneticAnalysis e) {
        GeneticAnalysisDnaRegionAnalysisTestCoverageComponent dst = e.new GeneticAnalysisDnaRegionAnalysisTestCoverageComponent();
        dst.dnaRegionOfInterest = new ArrayList<GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent>();
        for (GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent i : dnaRegionOfInterest)
          dst.dnaRegionOfInterest.add(i.copy(e));
        return dst;
      }

  }

    public class GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent extends Element {
        /**
         * Identifier of the sequence represented in NCBI genomic nucleotide RefSeq IDs with their version number.
         */
        protected String_ genomicReferenceSequenceIdentifier;

        /**
         * Start position of the DNA region of interest.
         */
        protected Integer regionOfInterestStart;

        /**
         * End position of the DNA region of interest.
         */
        protected Integer regionOfInterestStop;

        /**
         * Genotype of the region in reference genome.
         */
        protected String_ referenceNucleotide;

        /**
         * The patient's genotype in the region.
         */
        protected String_ variableNucleotide;

        /**
         * ID of the genechip.
         */
        protected String_ genechipId;

        /**
         * ID of manufacturer of the genechip.
         */
        protected String_ genechipManufacturerId;

        /**
         * Version of the genechip.
         */
        protected String_ genechipVersion;

        public String_ getGenomicReferenceSequenceIdentifier() { 
          return this.genomicReferenceSequenceIdentifier;
        }

        public void setGenomicReferenceSequenceIdentifier(String_ value) { 
          this.genomicReferenceSequenceIdentifier = value;
        }

        public String getGenomicReferenceSequenceIdentifierSimple() { 
          return this.genomicReferenceSequenceIdentifier == null ? null : this.genomicReferenceSequenceIdentifier.getValue();
        }

        public void setGenomicReferenceSequenceIdentifierSimple(String value) { 
          if (value == null)
            this.genomicReferenceSequenceIdentifier = null;
          else {
            if (this.genomicReferenceSequenceIdentifier == null)
              this.genomicReferenceSequenceIdentifier = new String_();
            this.genomicReferenceSequenceIdentifier.setValue(value);
          }
        }

        public Integer getRegionOfInterestStart() { 
          return this.regionOfInterestStart;
        }

        public void setRegionOfInterestStart(Integer value) { 
          this.regionOfInterestStart = value;
        }

        public int getRegionOfInterestStartSimple() { 
          return this.regionOfInterestStart == null ? null : this.regionOfInterestStart.getValue();
        }

        public void setRegionOfInterestStartSimple(int value) { 
          if (value == -1)
            this.regionOfInterestStart = null;
          else {
            if (this.regionOfInterestStart == null)
              this.regionOfInterestStart = new Integer();
            this.regionOfInterestStart.setValue(value);
          }
        }

        public Integer getRegionOfInterestStop() { 
          return this.regionOfInterestStop;
        }

        public void setRegionOfInterestStop(Integer value) { 
          this.regionOfInterestStop = value;
        }

        public int getRegionOfInterestStopSimple() { 
          return this.regionOfInterestStop == null ? null : this.regionOfInterestStop.getValue();
        }

        public void setRegionOfInterestStopSimple(int value) { 
          if (value == -1)
            this.regionOfInterestStop = null;
          else {
            if (this.regionOfInterestStop == null)
              this.regionOfInterestStop = new Integer();
            this.regionOfInterestStop.setValue(value);
          }
        }

        public String_ getReferenceNucleotide() { 
          return this.referenceNucleotide;
        }

        public void setReferenceNucleotide(String_ value) { 
          this.referenceNucleotide = value;
        }

        public String getReferenceNucleotideSimple() { 
          return this.referenceNucleotide == null ? null : this.referenceNucleotide.getValue();
        }

        public void setReferenceNucleotideSimple(String value) { 
          if (value == null)
            this.referenceNucleotide = null;
          else {
            if (this.referenceNucleotide == null)
              this.referenceNucleotide = new String_();
            this.referenceNucleotide.setValue(value);
          }
        }

        public String_ getVariableNucleotide() { 
          return this.variableNucleotide;
        }

        public void setVariableNucleotide(String_ value) { 
          this.variableNucleotide = value;
        }

        public String getVariableNucleotideSimple() { 
          return this.variableNucleotide == null ? null : this.variableNucleotide.getValue();
        }

        public void setVariableNucleotideSimple(String value) { 
          if (value == null)
            this.variableNucleotide = null;
          else {
            if (this.variableNucleotide == null)
              this.variableNucleotide = new String_();
            this.variableNucleotide.setValue(value);
          }
        }

        public String_ getGenechipId() { 
          return this.genechipId;
        }

        public void setGenechipId(String_ value) { 
          this.genechipId = value;
        }

        public String getGenechipIdSimple() { 
          return this.genechipId == null ? null : this.genechipId.getValue();
        }

        public void setGenechipIdSimple(String value) { 
          if (value == null)
            this.genechipId = null;
          else {
            if (this.genechipId == null)
              this.genechipId = new String_();
            this.genechipId.setValue(value);
          }
        }

        public String_ getGenechipManufacturerId() { 
          return this.genechipManufacturerId;
        }

        public void setGenechipManufacturerId(String_ value) { 
          this.genechipManufacturerId = value;
        }

        public String getGenechipManufacturerIdSimple() { 
          return this.genechipManufacturerId == null ? null : this.genechipManufacturerId.getValue();
        }

        public void setGenechipManufacturerIdSimple(String value) { 
          if (value == null)
            this.genechipManufacturerId = null;
          else {
            if (this.genechipManufacturerId == null)
              this.genechipManufacturerId = new String_();
            this.genechipManufacturerId.setValue(value);
          }
        }

        public String_ getGenechipVersion() { 
          return this.genechipVersion;
        }

        public void setGenechipVersion(String_ value) { 
          this.genechipVersion = value;
        }

        public String getGenechipVersionSimple() { 
          return this.genechipVersion == null ? null : this.genechipVersion.getValue();
        }

        public void setGenechipVersionSimple(String value) { 
          if (value == null)
            this.genechipVersion = null;
          else {
            if (this.genechipVersion == null)
              this.genechipVersion = new String_();
            this.genechipVersion.setValue(value);
          }
        }

      public GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent copy(GeneticAnalysis e) {
        GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent dst = e.new GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent();
        dst.genomicReferenceSequenceIdentifier = genomicReferenceSequenceIdentifier == null ? null : genomicReferenceSequenceIdentifier.copy();
        dst.regionOfInterestStart = regionOfInterestStart == null ? null : regionOfInterestStart.copy();
        dst.regionOfInterestStop = regionOfInterestStop == null ? null : regionOfInterestStop.copy();
        dst.referenceNucleotide = referenceNucleotide == null ? null : referenceNucleotide.copy();
        dst.variableNucleotide = variableNucleotide == null ? null : variableNucleotide.copy();
        dst.genechipId = genechipId == null ? null : genechipId.copy();
        dst.genechipManufacturerId = genechipManufacturerId == null ? null : genechipManufacturerId.copy();
        dst.genechipVersion = genechipVersion == null ? null : genechipVersion.copy();
        return dst;
      }

  }

    public class GeneticAnalysisGeneticAnalysisDiscreteResultComponent extends Element {
        /**
         * DNA analysis discrete sequence variation.
         */
        protected List<GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent> dnaAnalysisDiscreteSequenceVariation = new ArrayList<GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent>();

        public List<GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent> getDnaAnalysisDiscreteSequenceVariation() { 
          return this.dnaAnalysisDiscreteSequenceVariation;
        }

    // syntactic sugar
        public GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent addDnaAnalysisDiscreteSequenceVariation() { 
          GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent t = new GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent();
          this.dnaAnalysisDiscreteSequenceVariation.add(t);
          return t;
        }

      public GeneticAnalysisGeneticAnalysisDiscreteResultComponent copy(GeneticAnalysis e) {
        GeneticAnalysisGeneticAnalysisDiscreteResultComponent dst = e.new GeneticAnalysisGeneticAnalysisDiscreteResultComponent();
        dst.dnaAnalysisDiscreteSequenceVariation = new ArrayList<GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent>();
        for (GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent i : dnaAnalysisDiscreteSequenceVariation)
          dst.dnaAnalysisDiscreteSequenceVariation.add(i.copy(e));
        return dst;
      }

  }

    public class GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent extends Element {
        /**
         * Identifier of the gene represented in NCBI genomic nucleotide RefSeq IDs with their version number.
         */
        protected String_ geneIdentifier;

        /**
         * Identifier of the reference sequence represented in NCBI genomic nucleotide RefSeq IDs with their version number.
         */
        protected String_ genomicReferenceSequenceIdentifier;

        /**
         * Reference transcript represented in NCBI genomic nucleotide RefSeq IDs with their version number.
         */
        protected String_ transcriptReferenceIdentifier;

        /**
         * Name of the allele.
         */
        protected String_ alleleName;

        /**
         * Identifier of the DNA variation.
         */
        protected String_ dnaSequenceVariationIdentifier;

        /**
         * DNA variation represented in HGVS nomenclature.
         */
        protected String_ dnaSequenceVariation;

        /**
         * Type of the variation.
         */
        protected Coding dnaSequenceVariationType;

        /**
         * Amino acid change represented in HGVS nomenclature.
         */
        protected String_ aminoAcidChange;

        /**
         * Type of the amino acid change.
         */
        protected Coding aminoAcidChangeType;

        /**
         * Human-readable name of the DNA region.
         */
        protected String_ dnaRegionName;

        /**
         * Allelic state.
         */
        protected Coding allellicState;

        /**
         * Class of the source of sample.
         */
        protected Coding genomicSourceClass;

        /**
         * Conventional display of the DNA region and its interpretation.
         */
        protected String_ dnaSequenceVariationDisplayName;

        /**
         * Interpretation of the genotype's effect on genetic disease.
         */
        protected Coding geneticDiseaseSequenceVariationInterpretation;

        /**
         * Interpretation of the genotype's effect on the drug's metabolic effect.
         */
        protected Coding drugMetabolismSequenceVariationInterpretatioin;

        /**
         * Interpretation of the genotype's effect on the drug's efficacy.
         */
        protected Coding drugEfficacySequenceVariationInterpretation;

        /**
         * Genotyping result on a known set of mutation.
         */
        protected Coding geneticVariantAssessment;

        public String_ getGeneIdentifier() { 
          return this.geneIdentifier;
        }

        public void setGeneIdentifier(String_ value) { 
          this.geneIdentifier = value;
        }

        public String getGeneIdentifierSimple() { 
          return this.geneIdentifier == null ? null : this.geneIdentifier.getValue();
        }

        public void setGeneIdentifierSimple(String value) { 
          if (value == null)
            this.geneIdentifier = null;
          else {
            if (this.geneIdentifier == null)
              this.geneIdentifier = new String_();
            this.geneIdentifier.setValue(value);
          }
        }

        public String_ getGenomicReferenceSequenceIdentifier() { 
          return this.genomicReferenceSequenceIdentifier;
        }

        public void setGenomicReferenceSequenceIdentifier(String_ value) { 
          this.genomicReferenceSequenceIdentifier = value;
        }

        public String getGenomicReferenceSequenceIdentifierSimple() { 
          return this.genomicReferenceSequenceIdentifier == null ? null : this.genomicReferenceSequenceIdentifier.getValue();
        }

        public void setGenomicReferenceSequenceIdentifierSimple(String value) { 
          if (value == null)
            this.genomicReferenceSequenceIdentifier = null;
          else {
            if (this.genomicReferenceSequenceIdentifier == null)
              this.genomicReferenceSequenceIdentifier = new String_();
            this.genomicReferenceSequenceIdentifier.setValue(value);
          }
        }

        public String_ getTranscriptReferenceIdentifier() { 
          return this.transcriptReferenceIdentifier;
        }

        public void setTranscriptReferenceIdentifier(String_ value) { 
          this.transcriptReferenceIdentifier = value;
        }

        public String getTranscriptReferenceIdentifierSimple() { 
          return this.transcriptReferenceIdentifier == null ? null : this.transcriptReferenceIdentifier.getValue();
        }

        public void setTranscriptReferenceIdentifierSimple(String value) { 
          if (value == null)
            this.transcriptReferenceIdentifier = null;
          else {
            if (this.transcriptReferenceIdentifier == null)
              this.transcriptReferenceIdentifier = new String_();
            this.transcriptReferenceIdentifier.setValue(value);
          }
        }

        public String_ getAlleleName() { 
          return this.alleleName;
        }

        public void setAlleleName(String_ value) { 
          this.alleleName = value;
        }

        public String getAlleleNameSimple() { 
          return this.alleleName == null ? null : this.alleleName.getValue();
        }

        public void setAlleleNameSimple(String value) { 
          if (value == null)
            this.alleleName = null;
          else {
            if (this.alleleName == null)
              this.alleleName = new String_();
            this.alleleName.setValue(value);
          }
        }

        public String_ getDnaSequenceVariationIdentifier() { 
          return this.dnaSequenceVariationIdentifier;
        }

        public void setDnaSequenceVariationIdentifier(String_ value) { 
          this.dnaSequenceVariationIdentifier = value;
        }

        public String getDnaSequenceVariationIdentifierSimple() { 
          return this.dnaSequenceVariationIdentifier == null ? null : this.dnaSequenceVariationIdentifier.getValue();
        }

        public void setDnaSequenceVariationIdentifierSimple(String value) { 
          if (value == null)
            this.dnaSequenceVariationIdentifier = null;
          else {
            if (this.dnaSequenceVariationIdentifier == null)
              this.dnaSequenceVariationIdentifier = new String_();
            this.dnaSequenceVariationIdentifier.setValue(value);
          }
        }

        public String_ getDnaSequenceVariation() { 
          return this.dnaSequenceVariation;
        }

        public void setDnaSequenceVariation(String_ value) { 
          this.dnaSequenceVariation = value;
        }

        public String getDnaSequenceVariationSimple() { 
          return this.dnaSequenceVariation == null ? null : this.dnaSequenceVariation.getValue();
        }

        public void setDnaSequenceVariationSimple(String value) { 
          if (value == null)
            this.dnaSequenceVariation = null;
          else {
            if (this.dnaSequenceVariation == null)
              this.dnaSequenceVariation = new String_();
            this.dnaSequenceVariation.setValue(value);
          }
        }

        public Coding getDnaSequenceVariationType() { 
          return this.dnaSequenceVariationType;
        }

        public void setDnaSequenceVariationType(Coding value) { 
          this.dnaSequenceVariationType = value;
        }

        public String_ getAminoAcidChange() { 
          return this.aminoAcidChange;
        }

        public void setAminoAcidChange(String_ value) { 
          this.aminoAcidChange = value;
        }

        public String getAminoAcidChangeSimple() { 
          return this.aminoAcidChange == null ? null : this.aminoAcidChange.getValue();
        }

        public void setAminoAcidChangeSimple(String value) { 
          if (value == null)
            this.aminoAcidChange = null;
          else {
            if (this.aminoAcidChange == null)
              this.aminoAcidChange = new String_();
            this.aminoAcidChange.setValue(value);
          }
        }

        public Coding getAminoAcidChangeType() { 
          return this.aminoAcidChangeType;
        }

        public void setAminoAcidChangeType(Coding value) { 
          this.aminoAcidChangeType = value;
        }

        public String_ getDnaRegionName() { 
          return this.dnaRegionName;
        }

        public void setDnaRegionName(String_ value) { 
          this.dnaRegionName = value;
        }

        public String getDnaRegionNameSimple() { 
          return this.dnaRegionName == null ? null : this.dnaRegionName.getValue();
        }

        public void setDnaRegionNameSimple(String value) { 
          if (value == null)
            this.dnaRegionName = null;
          else {
            if (this.dnaRegionName == null)
              this.dnaRegionName = new String_();
            this.dnaRegionName.setValue(value);
          }
        }

        public Coding getAllellicState() { 
          return this.allellicState;
        }

        public void setAllellicState(Coding value) { 
          this.allellicState = value;
        }

        public Coding getGenomicSourceClass() { 
          return this.genomicSourceClass;
        }

        public void setGenomicSourceClass(Coding value) { 
          this.genomicSourceClass = value;
        }

        public String_ getDnaSequenceVariationDisplayName() { 
          return this.dnaSequenceVariationDisplayName;
        }

        public void setDnaSequenceVariationDisplayName(String_ value) { 
          this.dnaSequenceVariationDisplayName = value;
        }

        public String getDnaSequenceVariationDisplayNameSimple() { 
          return this.dnaSequenceVariationDisplayName == null ? null : this.dnaSequenceVariationDisplayName.getValue();
        }

        public void setDnaSequenceVariationDisplayNameSimple(String value) { 
          if (value == null)
            this.dnaSequenceVariationDisplayName = null;
          else {
            if (this.dnaSequenceVariationDisplayName == null)
              this.dnaSequenceVariationDisplayName = new String_();
            this.dnaSequenceVariationDisplayName.setValue(value);
          }
        }

        public Coding getGeneticDiseaseSequenceVariationInterpretation() { 
          return this.geneticDiseaseSequenceVariationInterpretation;
        }

        public void setGeneticDiseaseSequenceVariationInterpretation(Coding value) { 
          this.geneticDiseaseSequenceVariationInterpretation = value;
        }

        public Coding getDrugMetabolismSequenceVariationInterpretatioin() { 
          return this.drugMetabolismSequenceVariationInterpretatioin;
        }

        public void setDrugMetabolismSequenceVariationInterpretatioin(Coding value) { 
          this.drugMetabolismSequenceVariationInterpretatioin = value;
        }

        public Coding getDrugEfficacySequenceVariationInterpretation() { 
          return this.drugEfficacySequenceVariationInterpretation;
        }

        public void setDrugEfficacySequenceVariationInterpretation(Coding value) { 
          this.drugEfficacySequenceVariationInterpretation = value;
        }

        public Coding getGeneticVariantAssessment() { 
          return this.geneticVariantAssessment;
        }

        public void setGeneticVariantAssessment(Coding value) { 
          this.geneticVariantAssessment = value;
        }

      public GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent copy(GeneticAnalysis e) {
        GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent dst = e.new GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent();
        dst.geneIdentifier = geneIdentifier == null ? null : geneIdentifier.copy();
        dst.genomicReferenceSequenceIdentifier = genomicReferenceSequenceIdentifier == null ? null : genomicReferenceSequenceIdentifier.copy();
        dst.transcriptReferenceIdentifier = transcriptReferenceIdentifier == null ? null : transcriptReferenceIdentifier.copy();
        dst.alleleName = alleleName == null ? null : alleleName.copy();
        dst.dnaSequenceVariationIdentifier = dnaSequenceVariationIdentifier == null ? null : dnaSequenceVariationIdentifier.copy();
        dst.dnaSequenceVariation = dnaSequenceVariation == null ? null : dnaSequenceVariation.copy();
        dst.dnaSequenceVariationType = dnaSequenceVariationType == null ? null : dnaSequenceVariationType.copy();
        dst.aminoAcidChange = aminoAcidChange == null ? null : aminoAcidChange.copy();
        dst.aminoAcidChangeType = aminoAcidChangeType == null ? null : aminoAcidChangeType.copy();
        dst.dnaRegionName = dnaRegionName == null ? null : dnaRegionName.copy();
        dst.allellicState = allellicState == null ? null : allellicState.copy();
        dst.genomicSourceClass = genomicSourceClass == null ? null : genomicSourceClass.copy();
        dst.dnaSequenceVariationDisplayName = dnaSequenceVariationDisplayName == null ? null : dnaSequenceVariationDisplayName.copy();
        dst.geneticDiseaseSequenceVariationInterpretation = geneticDiseaseSequenceVariationInterpretation == null ? null : geneticDiseaseSequenceVariationInterpretation.copy();
        dst.drugMetabolismSequenceVariationInterpretatioin = drugMetabolismSequenceVariationInterpretatioin == null ? null : drugMetabolismSequenceVariationInterpretatioin.copy();
        dst.drugEfficacySequenceVariationInterpretation = drugEfficacySequenceVariationInterpretation == null ? null : drugEfficacySequenceVariationInterpretation.copy();
        dst.geneticVariantAssessment = geneticVariantAssessment == null ? null : geneticVariantAssessment.copy();
        return dst;
      }

  }

    /**
     * Subject of the analysis.
     */
    protected ResourceReference subject;

    /**
     * Author of the analysis.
     */
    protected ResourceReference author;

    /**
     * Date when result of the analysis is updated.
     */
    protected Date date;

    /**
     * Summary of the analysis.
     */
    protected GeneticAnalysisGeneticAnalysisSummaryComponent geneticAnalysisSummary;

    /**
     * Coverage of the genetic test.
     */
    protected GeneticAnalysisDnaRegionAnalysisTestCoverageComponent dnaRegionAnalysisTestCoverage;

    /**
     * Genetic analysis discrete result.
     */
    protected GeneticAnalysisGeneticAnalysisDiscreteResultComponent geneticAnalysisDiscreteResult;

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getAuthor() { 
      return this.author;
    }

    public void setAuthor(ResourceReference value) { 
      this.author = value;
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
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new Date();
        this.date.setValue(value);
      }
    }

    public GeneticAnalysisGeneticAnalysisSummaryComponent getGeneticAnalysisSummary() { 
      return this.geneticAnalysisSummary;
    }

    public void setGeneticAnalysisSummary(GeneticAnalysisGeneticAnalysisSummaryComponent value) { 
      this.geneticAnalysisSummary = value;
    }

    public GeneticAnalysisDnaRegionAnalysisTestCoverageComponent getDnaRegionAnalysisTestCoverage() { 
      return this.dnaRegionAnalysisTestCoverage;
    }

    public void setDnaRegionAnalysisTestCoverage(GeneticAnalysisDnaRegionAnalysisTestCoverageComponent value) { 
      this.dnaRegionAnalysisTestCoverage = value;
    }

    public GeneticAnalysisGeneticAnalysisDiscreteResultComponent getGeneticAnalysisDiscreteResult() { 
      return this.geneticAnalysisDiscreteResult;
    }

    public void setGeneticAnalysisDiscreteResult(GeneticAnalysisGeneticAnalysisDiscreteResultComponent value) { 
      this.geneticAnalysisDiscreteResult = value;
    }

      public GeneticAnalysis copy() {
        GeneticAnalysis dst = new GeneticAnalysis();
        dst.subject = subject == null ? null : subject.copy();
        dst.author = author == null ? null : author.copy();
        dst.date = date == null ? null : date.copy();
        dst.geneticAnalysisSummary = geneticAnalysisSummary == null ? null : geneticAnalysisSummary.copy(dst);
        dst.dnaRegionAnalysisTestCoverage = dnaRegionAnalysisTestCoverage == null ? null : dnaRegionAnalysisTestCoverage.copy(dst);
        dst.geneticAnalysisDiscreteResult = geneticAnalysisDiscreteResult == null ? null : geneticAnalysisDiscreteResult.copy(dst);
        return dst;
      }

      protected GeneticAnalysis typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.GeneticAnalysis;
   }


}

