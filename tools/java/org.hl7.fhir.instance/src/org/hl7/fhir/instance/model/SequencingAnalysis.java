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

import java.util.*;

/**
 * Computational analysis on a patient's genetic raw file.
 */
public class SequencingAnalysis extends Resource {

    public enum RefGenome {
        gRCh, // Genome Reference Consortium for human.
        gRCm, // Genome Reference Consortium for mice.
        Null; // added to help the parsers
        public static RefGenome fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("GRCh".equals(codeString))
          return gRCh;
        if ("GRCm".equals(codeString))
          return gRCm;
        throw new Exception("Unknown RefGenome code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case gRCh: return "GRCh";
            case gRCm: return "GRCm";
            default: return "?";
          }
        }
    }

  public class RefGenomeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("GRCh".equals(codeString))
          return RefGenome.gRCh;
        if ("GRCm".equals(codeString))
          return RefGenome.gRCm;
        throw new Exception("Unknown RefGenome code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == RefGenome.gRCh)
        return "GRCh";
      if (code == RefGenome.gRCm)
        return "GRCm";
      return "?";
      }
    }

    public class SequencingAnalysisGenomeComponent extends Element {
        /**
         * Name of the reference genome.
         */
        protected Enumeration<RefGenome> name;

        /**
         * Build number of the refernece genome.
         */
        protected String_ build;

        public Enumeration<RefGenome> getName() { 
          return this.name;
        }

        public void setName(Enumeration<RefGenome> value) { 
          this.name = value;
        }

        public RefGenome getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(RefGenome value) { 
            if (this.name == null)
              this.name = new Enumeration<RefGenome>();
            this.name.setValue(value);
        }

        public String_ getBuild() { 
          return this.build;
        }

        public void setBuild(String_ value) { 
          this.build = value;
        }

        public String getBuildSimple() { 
          return this.build == null ? null : this.build.getValue();
        }

        public void setBuildSimple(String value) { 
            if (this.build == null)
              this.build = new String_();
            this.build.setValue(value);
        }

      public SequencingAnalysisGenomeComponent copy(SequencingAnalysis e) {
        SequencingAnalysisGenomeComponent dst = e.new SequencingAnalysisGenomeComponent();
        dst.name = name == null ? null : name.copy();
        dst.build = build == null ? null : build.copy();
        return dst;
      }

  }

    /**
     * Subject of the analysis.
     */
    protected ResourceReference subject;

    /**
     * Date when result of the analysis is updated.
     */
    protected Date date;

    /**
     * Name of the analysis.
     */
    protected String_ name;

    /**
     * Reference genome used in the analysis.
     */
    protected SequencingAnalysisGenomeComponent genome;

    /**
     * Files uploaded as result of the analysis.
     */
    protected List<Attachment> file = new ArrayList<Attachment>();

    /**
     * SequencingLab taken into account of the analysis.
     */
    protected List<ResourceReference> inputLab = new ArrayList<ResourceReference>();

    /**
     * SequencingAnalysis taken into account of the analysis.
     */
    protected List<ResourceReference> inputAnalysis = new ArrayList<ResourceReference>();

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
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
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
    }

    public SequencingAnalysisGenomeComponent getGenome() { 
      return this.genome;
    }

    public void setGenome(SequencingAnalysisGenomeComponent value) { 
      this.genome = value;
    }

    public List<Attachment> getFile() { 
      return this.file;
    }

    // syntactic sugar
    public Attachment addFile() { 
      Attachment t = new Attachment();
      this.file.add(t);
      return t;
    }

    public List<ResourceReference> getInputLab() { 
      return this.inputLab;
    }

    // syntactic sugar
    public ResourceReference addInputLab() { 
      ResourceReference t = new ResourceReference();
      this.inputLab.add(t);
      return t;
    }

    public List<ResourceReference> getInputAnalysis() { 
      return this.inputAnalysis;
    }

    // syntactic sugar
    public ResourceReference addInputAnalysis() { 
      ResourceReference t = new ResourceReference();
      this.inputAnalysis.add(t);
      return t;
    }

      public SequencingAnalysis copy() {
        SequencingAnalysis dst = new SequencingAnalysis();
        dst.subject = subject == null ? null : subject.copy();
        dst.date = date == null ? null : date.copy();
        dst.name = name == null ? null : name.copy();
        dst.genome = genome == null ? null : genome.copy(dst);
        dst.file = new ArrayList<Attachment>();
        for (Attachment i : file)
          dst.file.add(i.copy());
        dst.inputLab = new ArrayList<ResourceReference>();
        for (ResourceReference i : inputLab)
          dst.inputLab.add(i.copy());
        dst.inputAnalysis = new ArrayList<ResourceReference>();
        for (ResourceReference i : inputAnalysis)
          dst.inputAnalysis.add(i.copy());
        return dst;
      }

      protected SequencingAnalysis typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.SequencingAnalysis;
   }


}

