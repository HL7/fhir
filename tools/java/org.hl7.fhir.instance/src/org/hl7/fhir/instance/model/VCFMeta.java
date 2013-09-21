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
 * Encapsulation of the headers in a VCF/BCF2 file.
 */
public class VCFMeta extends Resource {

    public enum Version {
        v40, // Version 4.0.
        v41, // Version 4.1.
        Null; // added to help the parsers
        public static Version fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("v4_0".equals(codeString))
          return v40;
        if ("v4_1".equals(codeString))
          return v41;
        throw new Exception("Unknown Version code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case v40: return "v4_0";
            case v41: return "v4_1";
            default: return "?";
          }
        }
    }

  public class VersionEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("v4_0".equals(codeString))
          return Version.v40;
        if ("v4_1".equals(codeString))
          return Version.v41;
        throw new Exception("Unknown Version code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Version.v40)
        return "v4_0";
      if (code == Version.v41)
        return "v4_1";
      return "?";
      }
    }

    public enum Type {
        integer, // Integer.
        float_, // Float.
        flag, // Flag.
        character, // Character.
        string, // String.
        Null; // added to help the parsers
        public static Type fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("Integer".equals(codeString))
          return integer;
        if ("Float".equals(codeString))
          return float_;
        if ("Flag".equals(codeString))
          return flag;
        if ("Character".equals(codeString))
          return character;
        if ("String".equals(codeString))
          return string;
        throw new Exception("Unknown Type code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case integer: return "Integer";
            case float_: return "Float";
            case flag: return "Flag";
            case character: return "Character";
            case string: return "String";
            default: return "?";
          }
        }
    }

  public class TypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("Integer".equals(codeString))
          return Type.integer;
        if ("Float".equals(codeString))
          return Type.float_;
        if ("Flag".equals(codeString))
          return Type.flag;
        if ("Character".equals(codeString))
          return Type.character;
        if ("String".equals(codeString))
          return Type.string;
        throw new Exception("Unknown Type code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Type.integer)
        return "Integer";
      if (code == Type.float_)
        return "Float";
      if (code == Type.flag)
        return "Flag";
      if (code == Type.character)
        return "Character";
      if (code == Type.string)
        return "String";
      return "?";
      }
    }

    public class VCFMetaSubjectComponent extends Element {
        /**
         * Patient resource corresponds to the subject.
         */
        protected ResourceReference patient;

        /**
         * Id of the subject' sample in the file.
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

      public VCFMetaSubjectComponent copy(VCFMeta e) {
        VCFMetaSubjectComponent dst = e.new VCFMetaSubjectComponent();
        dst.patient = patient == null ? null : patient.copy();
        dst.fileId = fileId == null ? null : fileId.copy();
        return dst;
      }

  }

    public class VCFMetaContigComponent extends Element {
        /**
         * Id of the reference sequence mentioned in the samples.
         */
        protected String_ identity;

        /**
         * URL to contigs from which reference sequence in the samples are referred to.
         */
        protected Uri url;

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

        public Uri getUrl() { 
          return this.url;
        }

        public void setUrl(Uri value) { 
          this.url = value;
        }

        public String getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        public void setUrlSimple(String value) { 
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
        }

      public VCFMetaContigComponent copy(VCFMeta e) {
        VCFMetaContigComponent dst = e.new VCFMetaContigComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.url = url == null ? null : url.copy();
        return dst;
      }

  }

    public class VCFMetaInfoComponent extends Element {
        /**
         * Id of the info.
         */
        protected String_ identity;

        /**
         * Number of values in the info.
         */
        protected Integer number;

        /**
         * Type of the value of the info.
         */
        protected Enumeration<Type> type;

        /**
         * Description of the info.
         */
        protected String_ description;

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

        public Integer getNumber() { 
          return this.number;
        }

        public void setNumber(Integer value) { 
          this.number = value;
        }

        public int getNumberSimple() { 
          return this.number == null ? null : this.number.getValue();
        }

        public void setNumberSimple(int value) { 
            if (this.number == null)
              this.number = new Integer();
            this.number.setValue(value);
        }

        public Enumeration<Type> getType() { 
          return this.type;
        }

        public void setType(Enumeration<Type> value) { 
          this.type = value;
        }

        public Type getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(Type value) { 
            if (this.type == null)
              this.type = new Enumeration<Type>();
            this.type.setValue(value);
        }

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
        }

      public VCFMetaInfoComponent copy(VCFMeta e) {
        VCFMetaInfoComponent dst = e.new VCFMetaInfoComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.number = number == null ? null : number.copy();
        dst.type = type == null ? null : type.copy();
        dst.description = description == null ? null : description.copy();
        return dst;
      }

  }

    public class VCFMetaFilterComponent extends Element {
        /**
         * Id of the filter.
         */
        protected String_ identity;

        /**
         * Description of the filter.
         */
        protected String_ description;

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

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
        }

      public VCFMetaFilterComponent copy(VCFMeta e) {
        VCFMetaFilterComponent dst = e.new VCFMetaFilterComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.description = description == null ? null : description.copy();
        return dst;
      }

  }

    public class VCFMetaFormatComponent extends Element {
        /**
         * Id of the format.
         */
        protected String_ identity;

        /**
         * Number of values in the format.
         */
        protected Integer number;

        /**
         * Type of the value of the format.
         */
        protected Enumeration<Type> type;

        /**
         * Description of the format.
         */
        protected String_ description;

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

        public Integer getNumber() { 
          return this.number;
        }

        public void setNumber(Integer value) { 
          this.number = value;
        }

        public int getNumberSimple() { 
          return this.number == null ? null : this.number.getValue();
        }

        public void setNumberSimple(int value) { 
            if (this.number == null)
              this.number = new Integer();
            this.number.setValue(value);
        }

        public Enumeration<Type> getType() { 
          return this.type;
        }

        public void setType(Enumeration<Type> value) { 
          this.type = value;
        }

        public Type getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(Type value) { 
            if (this.type == null)
              this.type = new Enumeration<Type>();
            this.type.setValue(value);
        }

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
        }

      public VCFMetaFormatComponent copy(VCFMeta e) {
        VCFMetaFormatComponent dst = e.new VCFMetaFormatComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.number = number == null ? null : number.copy();
        dst.type = type == null ? null : type.copy();
        dst.description = description == null ? null : description.copy();
        return dst;
      }

  }

    /**
     * Subject being described by the file.
     */
    protected List<VCFMetaSubjectComponent> subject = new ArrayList<VCFMetaSubjectComponent>();

    /**
     * Source file of the resource.
     */
    protected Attachment sourceFile;

    /**
     * Version of the VCF/BCF2 file.
     */
    protected Enumeration<Version> fileFormat;

    /**
     * Date when the file is written.
     */
    protected Date fileDate;

    /**
     * Name of the reference genome used in the analysis of the file.
     */
    protected String_ reference;

    /**
     * URL to the assembly.
     */
    protected Uri assembly;

    /**
     * Contigs referred to in the file.
     */
    protected VCFMetaContigComponent contig;

    /**
     * Info section of the headers.
     */
    protected List<VCFMetaInfoComponent> info = new ArrayList<VCFMetaInfoComponent>();

    /**
     * Filer section of the headers.
     */
    protected List<VCFMetaFilterComponent> filter = new ArrayList<VCFMetaFilterComponent>();

    /**
     * Format section of the headers.
     */
    protected List<VCFMetaFormatComponent> format = new ArrayList<VCFMetaFormatComponent>();

    public List<VCFMetaSubjectComponent> getSubject() { 
      return this.subject;
    }

    // syntactic sugar
    public VCFMetaSubjectComponent addSubject() { 
      VCFMetaSubjectComponent t = new VCFMetaSubjectComponent();
      this.subject.add(t);
      return t;
    }

    public Attachment getSourceFile() { 
      return this.sourceFile;
    }

    public void setSourceFile(Attachment value) { 
      this.sourceFile = value;
    }

    public Enumeration<Version> getFileFormat() { 
      return this.fileFormat;
    }

    public void setFileFormat(Enumeration<Version> value) { 
      this.fileFormat = value;
    }

    public Version getFileFormatSimple() { 
      return this.fileFormat == null ? null : this.fileFormat.getValue();
    }

    public void setFileFormatSimple(Version value) { 
        if (this.fileFormat == null)
          this.fileFormat = new Enumeration<Version>();
        this.fileFormat.setValue(value);
    }

    public Date getFileDate() { 
      return this.fileDate;
    }

    public void setFileDate(Date value) { 
      this.fileDate = value;
    }

    public String getFileDateSimple() { 
      return this.fileDate == null ? null : this.fileDate.getValue();
    }

    public void setFileDateSimple(String value) { 
      if (value == null)
        this.fileDate = null;
      else {
        if (this.fileDate == null)
          this.fileDate = new Date();
        this.fileDate.setValue(value);
      }
    }

    public String_ getReference() { 
      return this.reference;
    }

    public void setReference(String_ value) { 
      this.reference = value;
    }

    public String getReferenceSimple() { 
      return this.reference == null ? null : this.reference.getValue();
    }

    public void setReferenceSimple(String value) { 
      if (value == null)
        this.reference = null;
      else {
        if (this.reference == null)
          this.reference = new String_();
        this.reference.setValue(value);
      }
    }

    public Uri getAssembly() { 
      return this.assembly;
    }

    public void setAssembly(Uri value) { 
      this.assembly = value;
    }

    public String getAssemblySimple() { 
      return this.assembly == null ? null : this.assembly.getValue();
    }

    public void setAssemblySimple(String value) { 
      if (value == null)
        this.assembly = null;
      else {
        if (this.assembly == null)
          this.assembly = new Uri();
        this.assembly.setValue(value);
      }
    }

    public VCFMetaContigComponent getContig() { 
      return this.contig;
    }

    public void setContig(VCFMetaContigComponent value) { 
      this.contig = value;
    }

    public List<VCFMetaInfoComponent> getInfo() { 
      return this.info;
    }

    // syntactic sugar
    public VCFMetaInfoComponent addInfo() { 
      VCFMetaInfoComponent t = new VCFMetaInfoComponent();
      this.info.add(t);
      return t;
    }

    public List<VCFMetaFilterComponent> getFilter() { 
      return this.filter;
    }

    // syntactic sugar
    public VCFMetaFilterComponent addFilter() { 
      VCFMetaFilterComponent t = new VCFMetaFilterComponent();
      this.filter.add(t);
      return t;
    }

    public List<VCFMetaFormatComponent> getFormat() { 
      return this.format;
    }

    // syntactic sugar
    public VCFMetaFormatComponent addFormat() { 
      VCFMetaFormatComponent t = new VCFMetaFormatComponent();
      this.format.add(t);
      return t;
    }

      public VCFMeta copy() {
        VCFMeta dst = new VCFMeta();
        dst.subject = new ArrayList<VCFMetaSubjectComponent>();
        for (VCFMetaSubjectComponent i : subject)
          dst.subject.add(i.copy(dst));
        dst.sourceFile = sourceFile == null ? null : sourceFile.copy();
        dst.fileFormat = fileFormat == null ? null : fileFormat.copy();
        dst.fileDate = fileDate == null ? null : fileDate.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.assembly = assembly == null ? null : assembly.copy();
        dst.contig = contig == null ? null : contig.copy(dst);
        dst.info = new ArrayList<VCFMetaInfoComponent>();
        for (VCFMetaInfoComponent i : info)
          dst.info.add(i.copy(dst));
        dst.filter = new ArrayList<VCFMetaFilterComponent>();
        for (VCFMetaFilterComponent i : filter)
          dst.filter.add(i.copy(dst));
        dst.format = new ArrayList<VCFMetaFormatComponent>();
        for (VCFMetaFormatComponent i : format)
          dst.format.add(i.copy(dst));
        return dst;
      }

      protected VCFMeta typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.VCFMeta;
   }


}

