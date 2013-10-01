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
 * A lab for sequencing.
 */
public class SequencingLab extends Resource {

    public enum SequencingType {
        wholeGenome, // Whole genome sequencing.
        wholeExome, // Whole exome sequencing.
        targetedCapture, // Targeted capture.
        Null; // added to help the parsers
        public static SequencingType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("whole_genome".equals(codeString))
          return wholeGenome;
        if ("whole_exome".equals(codeString))
          return wholeExome;
        if ("targeted_capture".equals(codeString))
          return targetedCapture;
        throw new Exception("Unknown SequencingType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case wholeGenome: return "whole_genome";
            case wholeExome: return "whole_exome";
            case targetedCapture: return "targeted_capture";
            default: return "?";
          }
        }
    }

  public class SequencingTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("whole_genome".equals(codeString))
          return SequencingType.wholeGenome;
        if ("whole_exome".equals(codeString))
          return SequencingType.wholeExome;
        if ("targeted_capture".equals(codeString))
          return SequencingType.targetedCapture;
        throw new Exception("Unknown SequencingType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SequencingType.wholeGenome)
        return "whole_genome";
      if (code == SequencingType.wholeExome)
        return "whole_exome";
      if (code == SequencingType.targetedCapture)
        return "targeted_capture";
      return "?";
      }
    }

    public enum SequencingSystemClass {
        srs, // Short read sequencing.
        sms, // Single molecule sequencing.
        capillary, // Capillary sequencing.
        dnaChip, // DNA microarray SNP detection.
        Null; // added to help the parsers
        public static SequencingSystemClass fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("srs".equals(codeString))
          return srs;
        if ("sms".equals(codeString))
          return sms;
        if ("capillary".equals(codeString))
          return capillary;
        if ("dna_chip".equals(codeString))
          return dnaChip;
        throw new Exception("Unknown SequencingSystemClass code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case srs: return "srs";
            case sms: return "sms";
            case capillary: return "capillary";
            case dnaChip: return "dna_chip";
            default: return "?";
          }
        }
    }

  public class SequencingSystemClassEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("srs".equals(codeString))
          return SequencingSystemClass.srs;
        if ("sms".equals(codeString))
          return SequencingSystemClass.sms;
        if ("capillary".equals(codeString))
          return SequencingSystemClass.capillary;
        if ("dna_chip".equals(codeString))
          return SequencingSystemClass.dnaChip;
        throw new Exception("Unknown SequencingSystemClass code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SequencingSystemClass.srs)
        return "srs";
      if (code == SequencingSystemClass.sms)
        return "sms";
      if (code == SequencingSystemClass.capillary)
        return "capillary";
      if (code == SequencingSystemClass.dnaChip)
        return "dna_chip";
      return "?";
      }
    }

    public enum SequencingSystemName {
        illumina, // Illumina.
        solid, // ABI SOLiD.
        Null; // added to help the parsers
        public static SequencingSystemName fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("illumina".equals(codeString))
          return illumina;
        if ("solid".equals(codeString))
          return solid;
        throw new Exception("Unknown SequencingSystemName code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case illumina: return "illumina";
            case solid: return "solid";
            default: return "?";
          }
        }
    }

  public class SequencingSystemNameEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("illumina".equals(codeString))
          return SequencingSystemName.illumina;
        if ("solid".equals(codeString))
          return SequencingSystemName.solid;
        throw new Exception("Unknown SequencingSystemName code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SequencingSystemName.illumina)
        return "illumina";
      if (code == SequencingSystemName.solid)
        return "solid";
      return "?";
      }
    }

    public enum SequencingSpecimenType {
        germline, // Germline.
        somatic, // Somatic.
        likelyGermline, // Likely germline.
        likelySomatic, // Likely somatic.
        Null; // added to help the parsers
        public static SequencingSpecimenType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("germline".equals(codeString))
          return germline;
        if ("somatic".equals(codeString))
          return somatic;
        if ("likely_germline".equals(codeString))
          return likelyGermline;
        if ("likely_somatic".equals(codeString))
          return likelySomatic;
        throw new Exception("Unknown SequencingSpecimenType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case germline: return "germline";
            case somatic: return "somatic";
            case likelyGermline: return "likely_germline";
            case likelySomatic: return "likely_somatic";
            default: return "?";
          }
        }
    }

  public class SequencingSpecimenTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("germline".equals(codeString))
          return SequencingSpecimenType.germline;
        if ("somatic".equals(codeString))
          return SequencingSpecimenType.somatic;
        if ("likely_germline".equals(codeString))
          return SequencingSpecimenType.likelyGermline;
        if ("likely_somatic".equals(codeString))
          return SequencingSpecimenType.likelySomatic;
        throw new Exception("Unknown SequencingSpecimenType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SequencingSpecimenType.germline)
        return "germline";
      if (code == SequencingSpecimenType.somatic)
        return "somatic";
      if (code == SequencingSpecimenType.likelyGermline)
        return "likely_germline";
      if (code == SequencingSpecimenType.likelySomatic)
        return "likely_somatic";
      return "?";
      }
    }

    public class SequencingLabSystemComponent extends Element {
        /**
         * Class of sequencing system.
         */
        protected Enumeration<SequencingSystemClass> class_;

        /**
         * Version of sequencing system.
         */
        protected String_ version;

        /**
         * Name of sequencing system.
         */
        protected Enumeration<SequencingSystemName> name;

        /**
         * Id of sequencing system.
         */
        protected String_ identity;

        public Enumeration<SequencingSystemClass> getClass_() { 
          return this.class_;
        }

        public void setClass_(Enumeration<SequencingSystemClass> value) { 
          this.class_ = value;
        }

        public SequencingSystemClass getClass_Simple() { 
          return this.class_ == null ? null : this.class_.getValue();
        }

        public void setClass_Simple(SequencingSystemClass value) { 
          if (value == null)
            this.class_ = null;
          else {
            if (this.class_ == null)
              this.class_ = new Enumeration<SequencingSystemClass>();
            this.class_.setValue(value);
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

        public Enumeration<SequencingSystemName> getName() { 
          return this.name;
        }

        public void setName(Enumeration<SequencingSystemName> value) { 
          this.name = value;
        }

        public SequencingSystemName getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(SequencingSystemName value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new Enumeration<SequencingSystemName>();
            this.name.setValue(value);
          }
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
          if (value == null)
            this.identity = null;
          else {
            if (this.identity == null)
              this.identity = new String_();
            this.identity.setValue(value);
          }
        }

      public SequencingLabSystemComponent copy(SequencingLab e) {
        SequencingLabSystemComponent dst = e.new SequencingLabSystemComponent();
        dst.class_ = class_ == null ? null : class_.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.identity = identity == null ? null : identity.copy();
        return dst;
      }

  }

    public class SequencingLabSpecimenComponent extends Element {
        /**
         * Whether the specimen is from germline or somatic cells of the patient.
         */
        protected Enumeration<SequencingSpecimenType> type;

        /**
         * Source of the specimen.
         */
        protected CodeableConcept source;

        public Enumeration<SequencingSpecimenType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<SequencingSpecimenType> value) { 
          this.type = value;
        }

        public SequencingSpecimenType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(SequencingSpecimenType value) { 
            if (this.type == null)
              this.type = new Enumeration<SequencingSpecimenType>();
            this.type.setValue(value);
        }

        public CodeableConcept getSource() { 
          return this.source;
        }

        public void setSource(CodeableConcept value) { 
          this.source = value;
        }

      public SequencingLabSpecimenComponent copy(SequencingLab e) {
        SequencingLabSpecimenComponent dst = e.new SequencingLabSpecimenComponent();
        dst.type = type == null ? null : type.copy();
        dst.source = source == null ? null : source.copy();
        return dst;
      }

  }

    /**
     * Subject of the sequencing lab.
     */
    protected ResourceReference subject;

    /**
     * Organization that does the sequencing.
     */
    protected String_ organization;

    /**
     * Name of the lab.
     */
    protected String_ name;

    /**
     * Date when the result of the lab is uploaded.
     */
    protected Date date;

    /**
     * Type of the sequencing lab.
     */
    protected Enumeration<SequencingType> type;

    /**
     * System of machine used for sequencing.
     */
    protected SequencingLabSystemComponent system;

    /**
     * Specimen of the lab.
     */
    protected SequencingLabSpecimenComponent specimen;

    /**
     * Files uploaded as results of the lab.
     */
    protected List<Attachment> file = new ArrayList<Attachment>();

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public String_ getOrganization() { 
      return this.organization;
    }

    public void setOrganization(String_ value) { 
      this.organization = value;
    }

    public String getOrganizationSimple() { 
      return this.organization == null ? null : this.organization.getValue();
    }

    public void setOrganizationSimple(String value) { 
      if (value == null)
        this.organization = null;
      else {
        if (this.organization == null)
          this.organization = new String_();
        this.organization.setValue(value);
      }
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

    public Enumeration<SequencingType> getType() { 
      return this.type;
    }

    public void setType(Enumeration<SequencingType> value) { 
      this.type = value;
    }

    public SequencingType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    public void setTypeSimple(SequencingType value) { 
        if (this.type == null)
          this.type = new Enumeration<SequencingType>();
        this.type.setValue(value);
    }

    public SequencingLabSystemComponent getSystem() { 
      return this.system;
    }

    public void setSystem(SequencingLabSystemComponent value) { 
      this.system = value;
    }

    public SequencingLabSpecimenComponent getSpecimen() { 
      return this.specimen;
    }

    public void setSpecimen(SequencingLabSpecimenComponent value) { 
      this.specimen = value;
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

      public SequencingLab copy() {
        SequencingLab dst = new SequencingLab();
        dst.subject = subject == null ? null : subject.copy();
        dst.organization = organization == null ? null : organization.copy();
        dst.name = name == null ? null : name.copy();
        dst.date = date == null ? null : date.copy();
        dst.type = type == null ? null : type.copy();
        dst.system = system == null ? null : system.copy(dst);
        dst.specimen = specimen == null ? null : specimen.copy(dst);
        dst.file = new ArrayList<Attachment>();
        for (Attachment i : file)
          dst.file.add(i.copy());
        return dst;
      }

      protected SequencingLab typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.SequencingLab;
   }


}

