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
 * Resource that records the patient's expression of a gene.
 */
public class GeneExpression extends Resource {

    public class GeneExpressionGeneComponent extends Element {
        /**
         * Identifier of the gene.
         */
        protected String_ identifier;

        /**
         * Coordinate of the gene.
         */
        protected GeneExpressionGeneCoordinateComponent coordinate;

        public String_ getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(String_ value) { 
          this.identifier = value;
        }

        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        public void setIdentifierSimple(String value) { 
          if (value == null)
            this.identifier = null;
          else {
            if (this.identifier == null)
              this.identifier = new String_();
            this.identifier.setValue(value);
          }
        }

        public GeneExpressionGeneCoordinateComponent getCoordinate() { 
          return this.coordinate;
        }

        public void setCoordinate(GeneExpressionGeneCoordinateComponent value) { 
          this.coordinate = value;
        }

      public GeneExpressionGeneComponent copy(GeneExpression e) {
        GeneExpressionGeneComponent dst = e.new GeneExpressionGeneComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.coordinate = coordinate == null ? null : coordinate.copy(e);
        return dst;
      }

  }

    public class GeneExpressionGeneCoordinateComponent extends Element {
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

      public GeneExpressionGeneCoordinateComponent copy(GeneExpression e) {
        GeneExpressionGeneCoordinateComponent dst = e.new GeneExpressionGeneCoordinateComponent();
        dst.chromosome = chromosome == null ? null : chromosome.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        return dst;
      }

  }

    public class GeneExpressionRnaSeqComponent extends Element {
        /**
         * Input lab for the RNA-Seq.
         */
        protected ResourceReference inputLab;

        /**
         * Input analysis for the RNA-Seq.
         */
        protected ResourceReference inputAnalysis;

        /**
         * Expression level of the gene in RPKM.
         */
        protected Decimal expression;

        /**
         * Isoform of the gene.
         */
        protected List<GeneExpressionRnaSeqIsoformComponent> isoform = new ArrayList<GeneExpressionRnaSeqIsoformComponent>();

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

        public Decimal getExpression() { 
          return this.expression;
        }

        public void setExpression(Decimal value) { 
          this.expression = value;
        }

        public BigDecimal getExpressionSimple() { 
          return this.expression == null ? null : this.expression.getValue();
        }

        public void setExpressionSimple(BigDecimal value) { 
            if (this.expression == null)
              this.expression = new Decimal();
            this.expression.setValue(value);
        }

        public List<GeneExpressionRnaSeqIsoformComponent> getIsoform() { 
          return this.isoform;
        }

    // syntactic sugar
        public GeneExpressionRnaSeqIsoformComponent addIsoform() { 
          GeneExpressionRnaSeqIsoformComponent t = new GeneExpressionRnaSeqIsoformComponent();
          this.isoform.add(t);
          return t;
        }

      public GeneExpressionRnaSeqComponent copy(GeneExpression e) {
        GeneExpressionRnaSeqComponent dst = e.new GeneExpressionRnaSeqComponent();
        dst.inputLab = inputLab == null ? null : inputLab.copy();
        dst.inputAnalysis = inputAnalysis == null ? null : inputAnalysis.copy();
        dst.expression = expression == null ? null : expression.copy();
        dst.isoform = new ArrayList<GeneExpressionRnaSeqIsoformComponent>();
        for (GeneExpressionRnaSeqIsoformComponent i : isoform)
          dst.isoform.add(i.copy(e));
        return dst;
      }

  }

    public class GeneExpressionRnaSeqIsoformComponent extends Element {
        /**
         * Identifier of the isoform.
         */
        protected String_ identity;

        /**
         * Expression level of the isoform in RPKM.
         */
        protected Decimal expression;

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

        public Decimal getExpression() { 
          return this.expression;
        }

        public void setExpression(Decimal value) { 
          this.expression = value;
        }

        public BigDecimal getExpressionSimple() { 
          return this.expression == null ? null : this.expression.getValue();
        }

        public void setExpressionSimple(BigDecimal value) { 
            if (this.expression == null)
              this.expression = new Decimal();
            this.expression.setValue(value);
        }

      public GeneExpressionRnaSeqIsoformComponent copy(GeneExpression e) {
        GeneExpressionRnaSeqIsoformComponent dst = e.new GeneExpressionRnaSeqIsoformComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.expression = expression == null ? null : expression.copy();
        return dst;
      }

  }

    /**
     * Subject described by the resource.
     */
    protected ResourceReference subject;

    /**
     * Gene of study.
     */
    protected GeneExpressionGeneComponent gene;

    /**
     * Microarray that studies the gene.
     */
    protected List<ResourceReference> microarray = new ArrayList<ResourceReference>();

    /**
     * RNA-Seq that studies the gene.
     */
    protected List<GeneExpressionRnaSeqComponent> rnaSeq = new ArrayList<GeneExpressionRnaSeqComponent>();

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public GeneExpressionGeneComponent getGene() { 
      return this.gene;
    }

    public void setGene(GeneExpressionGeneComponent value) { 
      this.gene = value;
    }

    public List<ResourceReference> getMicroarray() { 
      return this.microarray;
    }

    // syntactic sugar
    public ResourceReference addMicroarray() { 
      ResourceReference t = new ResourceReference();
      this.microarray.add(t);
      return t;
    }

    public List<GeneExpressionRnaSeqComponent> getRnaSeq() { 
      return this.rnaSeq;
    }

    // syntactic sugar
    public GeneExpressionRnaSeqComponent addRnaSeq() { 
      GeneExpressionRnaSeqComponent t = new GeneExpressionRnaSeqComponent();
      this.rnaSeq.add(t);
      return t;
    }

      public GeneExpression copy() {
        GeneExpression dst = new GeneExpression();
        dst.subject = subject == null ? null : subject.copy();
        dst.gene = gene == null ? null : gene.copy(dst);
        dst.microarray = new ArrayList<ResourceReference>();
        for (ResourceReference i : microarray)
          dst.microarray.add(i.copy());
        dst.rnaSeq = new ArrayList<GeneExpressionRnaSeqComponent>();
        for (GeneExpressionRnaSeqComponent i : rnaSeq)
          dst.rnaSeq.add(i.copy(dst));
        return dst;
      }

      protected GeneExpression typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.GeneExpression;
   }


}

