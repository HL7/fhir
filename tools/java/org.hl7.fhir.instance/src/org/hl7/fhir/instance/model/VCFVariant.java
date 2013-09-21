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

/**
 * Encapsulation of a snippet of a VCF/BCF2 file.
 */
public class VCFVariant extends Resource {

    public class VCFVariantSubjectComponent extends Element {
        /**
         * Patient resource that corresponds to the subject.
         */
        protected ResourceReference patient;

        /**
         * Id of the sample that corresponds to the subject.
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

      public VCFVariantSubjectComponent copy(VCFVariant e) {
        VCFVariantSubjectComponent dst = e.new VCFVariantSubjectComponent();
        dst.patient = patient == null ? null : patient.copy();
        dst.fileId = fileId == null ? null : fileId.copy();
        return dst;
      }

  }

    public class VCFVariantInfoComponent extends Element {
        /**
         * Id of the info.
         */
        protected String_ identity;

        /**
         * Value of the info.
         */
        protected String_ value;

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

        public String_ getValue() { 
          return this.value;
        }

        public void setValue(String_ value) { 
          this.value = value;
        }

        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        public void setValueSimple(String value) { 
          if (value == null)
            this.value = null;
          else {
            if (this.value == null)
              this.value = new String_();
            this.value.setValue(value);
          }
        }

      public VCFVariantInfoComponent copy(VCFVariant e) {
        VCFVariantInfoComponent dst = e.new VCFVariantInfoComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    public class VCFVariantSampleComponent extends Element {
        /**
         * Id of the sample.
         */
        protected String_ identity;

        /**
         * A field of the sample.
         */
        protected List<VCFVariantSampleFieldComponent> field = new ArrayList<VCFVariantSampleFieldComponent>();

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

        public List<VCFVariantSampleFieldComponent> getField() { 
          return this.field;
        }

    // syntactic sugar
        public VCFVariantSampleFieldComponent addField() { 
          VCFVariantSampleFieldComponent t = new VCFVariantSampleFieldComponent();
          this.field.add(t);
          return t;
        }

      public VCFVariantSampleComponent copy(VCFVariant e) {
        VCFVariantSampleComponent dst = e.new VCFVariantSampleComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.field = new ArrayList<VCFVariantSampleFieldComponent>();
        for (VCFVariantSampleFieldComponent i : field)
          dst.field.add(i.copy(e));
        return dst;
      }

  }

    public class VCFVariantSampleFieldComponent extends Element {
        /**
         * Id of the field.
         */
        protected String_ identity;

        /**
         * Value of the field.
         */
        protected List<String_> value = new ArrayList<String_>();

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

        public List<String_> getValue() { 
          return this.value;
        }

    // syntactic sugar
        public String_ addValue() { 
          String_ t = new String_();
          this.value.add(t);
          return t;
        }

        public String_ addValueSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.value.add(t);
          return t;
        }

      public VCFVariantSampleFieldComponent copy(VCFVariant e) {
        VCFVariantSampleFieldComponent dst = e.new VCFVariantSampleFieldComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.value = new ArrayList<String_>();
        for (String_ i : value)
          dst.value.add(i.copy());
        return dst;
      }

  }

    /**
     * Subject being described by the resource.
     */
    protected List<VCFVariantSubjectComponent> subject = new ArrayList<VCFVariantSubjectComponent>();

    /**
     * Source file of the resoruce.
     */
    protected Attachment sourceFile;

    /**
     * Meta information of the variant.
     */
    protected ResourceReference meta;

    /**
     * Id of the reference sequence.
     */
    protected String_ chrom;

    /**
     * Position of the variant.
     */
    protected Integer pos;

    /**
     * Identifier of the variant.
     */
    protected String_ identity;

    /**
     * Reference genotype of the variant.
     */
    protected String_ ref;

    /**
     * Alternative genotype of the variant.
     */
    protected List<String_> alt = new ArrayList<String_>();

    /**
     * Quality of the variant.
     */
    protected Integer qual;

    /**
     * Filter section of the file.
     */
    protected String_ filter;

    /**
     * Info section of the file.
     */
    protected VCFVariantInfoComponent info;

    /**
     * Sample being described in the file.
     */
    protected List<VCFVariantSampleComponent> sample = new ArrayList<VCFVariantSampleComponent>();

    public List<VCFVariantSubjectComponent> getSubject() { 
      return this.subject;
    }

    // syntactic sugar
    public VCFVariantSubjectComponent addSubject() { 
      VCFVariantSubjectComponent t = new VCFVariantSubjectComponent();
      this.subject.add(t);
      return t;
    }

    public Attachment getSourceFile() { 
      return this.sourceFile;
    }

    public void setSourceFile(Attachment value) { 
      this.sourceFile = value;
    }

    public ResourceReference getMeta() { 
      return this.meta;
    }

    public void setMeta(ResourceReference value) { 
      this.meta = value;
    }

    public String_ getChrom() { 
      return this.chrom;
    }

    public void setChrom(String_ value) { 
      this.chrom = value;
    }

    public String getChromSimple() { 
      return this.chrom == null ? null : this.chrom.getValue();
    }

    public void setChromSimple(String value) { 
        if (this.chrom == null)
          this.chrom = new String_();
        this.chrom.setValue(value);
    }

    public Integer getPos() { 
      return this.pos;
    }

    public void setPos(Integer value) { 
      this.pos = value;
    }

    public int getPosSimple() { 
      return this.pos == null ? null : this.pos.getValue();
    }

    public void setPosSimple(int value) { 
        if (this.pos == null)
          this.pos = new Integer();
        this.pos.setValue(value);
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

    public String_ getRef() { 
      return this.ref;
    }

    public void setRef(String_ value) { 
      this.ref = value;
    }

    public String getRefSimple() { 
      return this.ref == null ? null : this.ref.getValue();
    }

    public void setRefSimple(String value) { 
        if (this.ref == null)
          this.ref = new String_();
        this.ref.setValue(value);
    }

    public List<String_> getAlt() { 
      return this.alt;
    }

    // syntactic sugar
    public String_ addAlt() { 
      String_ t = new String_();
      this.alt.add(t);
      return t;
    }

    public String_ addAltSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.alt.add(t);
      return t;
    }

    public Integer getQual() { 
      return this.qual;
    }

    public void setQual(Integer value) { 
      this.qual = value;
    }

    public int getQualSimple() { 
      return this.qual == null ? null : this.qual.getValue();
    }

    public void setQualSimple(int value) { 
        if (this.qual == null)
          this.qual = new Integer();
        this.qual.setValue(value);
    }

    public String_ getFilter() { 
      return this.filter;
    }

    public void setFilter(String_ value) { 
      this.filter = value;
    }

    public String getFilterSimple() { 
      return this.filter == null ? null : this.filter.getValue();
    }

    public void setFilterSimple(String value) { 
      if (value == null)
        this.filter = null;
      else {
        if (this.filter == null)
          this.filter = new String_();
        this.filter.setValue(value);
      }
    }

    public VCFVariantInfoComponent getInfo() { 
      return this.info;
    }

    public void setInfo(VCFVariantInfoComponent value) { 
      this.info = value;
    }

    public List<VCFVariantSampleComponent> getSample() { 
      return this.sample;
    }

    // syntactic sugar
    public VCFVariantSampleComponent addSample() { 
      VCFVariantSampleComponent t = new VCFVariantSampleComponent();
      this.sample.add(t);
      return t;
    }

      public VCFVariant copy() {
        VCFVariant dst = new VCFVariant();
        dst.subject = new ArrayList<VCFVariantSubjectComponent>();
        for (VCFVariantSubjectComponent i : subject)
          dst.subject.add(i.copy(dst));
        dst.sourceFile = sourceFile == null ? null : sourceFile.copy();
        dst.meta = meta == null ? null : meta.copy();
        dst.chrom = chrom == null ? null : chrom.copy();
        dst.pos = pos == null ? null : pos.copy();
        dst.identity = identity == null ? null : identity.copy();
        dst.ref = ref == null ? null : ref.copy();
        dst.alt = new ArrayList<String_>();
        for (String_ i : alt)
          dst.alt.add(i.copy());
        dst.qual = qual == null ? null : qual.copy();
        dst.filter = filter == null ? null : filter.copy();
        dst.info = info == null ? null : info.copy(dst);
        dst.sample = new ArrayList<VCFVariantSampleComponent>();
        for (VCFVariantSampleComponent i : sample)
          dst.sample.add(i.copy(dst));
        return dst;
      }

      protected VCFVariant typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.VCFVariant;
   }


}

