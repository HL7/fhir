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

// Generated on Thu, Jan 23, 2014 11:33-0600 for FHIR v0.12

import java.util.*;

/**
 * The findings and interpretation of diagnostic  tests performed on patients, groups of patients, devices, and locations, and/or specimens derived from these. The report includes clinical context such as requesting and provider information, and some mix of atomic results, images, textual and coded interpretation, and formatted representation of diagnostic reports.
 */
public class DiagnosticReport extends Resource {

    public enum DiagnosticReportStatus {
        registered, // The existence of the report is registered, but there is nothing yet available.
        partial, // This is a partial (e.g. initial, interim or preliminary) report: data in the report may be incomplete or unverified.
        final_, // The report is complete and verified by an authorized person.
        corrected, // The report has been modified subsequent to being Final, and is complete and verified by an authorized person.
        amended, // The report has been modified subsequent to being Final, and is complete and verified by an authorized person, and data has been changed.
        appended, // The report has been modified subsequent to being Final, and is complete and verified by an authorized person. New content has been added, but existing content hasn't changed.
        cancelled, // The report is unavailable because the measurement was not started or not completed (also sometimes called "aborted").
        enteredInError, // The report has been withdrawn following previous Final release.
        Null; // added to help the parsers
        public static DiagnosticReportStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return registered;
        if ("partial".equals(codeString))
          return partial;
        if ("final".equals(codeString))
          return final_;
        if ("corrected".equals(codeString))
          return corrected;
        if ("amended".equals(codeString))
          return amended;
        if ("appended".equals(codeString))
          return appended;
        if ("cancelled".equals(codeString))
          return cancelled;
        if ("entered in error".equals(codeString))
          return enteredInError;
        throw new Exception("Unknown DiagnosticReportStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case registered: return "registered";
            case partial: return "partial";
            case final_: return "final";
            case corrected: return "corrected";
            case amended: return "amended";
            case appended: return "appended";
            case cancelled: return "cancelled";
            case enteredInError: return "entered in error";
            default: return "?";
          }
        }
    }

  public static class DiagnosticReportStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return DiagnosticReportStatus.registered;
        if ("partial".equals(codeString))
          return DiagnosticReportStatus.partial;
        if ("final".equals(codeString))
          return DiagnosticReportStatus.final_;
        if ("corrected".equals(codeString))
          return DiagnosticReportStatus.corrected;
        if ("amended".equals(codeString))
          return DiagnosticReportStatus.amended;
        if ("appended".equals(codeString))
          return DiagnosticReportStatus.appended;
        if ("cancelled".equals(codeString))
          return DiagnosticReportStatus.cancelled;
        if ("entered in error".equals(codeString))
          return DiagnosticReportStatus.enteredInError;
        throw new Exception("Unknown DiagnosticReportStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DiagnosticReportStatus.registered)
        return "registered";
      if (code == DiagnosticReportStatus.partial)
        return "partial";
      if (code == DiagnosticReportStatus.final_)
        return "final";
      if (code == DiagnosticReportStatus.corrected)
        return "corrected";
      if (code == DiagnosticReportStatus.amended)
        return "amended";
      if (code == DiagnosticReportStatus.appended)
        return "appended";
      if (code == DiagnosticReportStatus.cancelled)
        return "cancelled";
      if (code == DiagnosticReportStatus.enteredInError)
        return "entered in error";
      return "?";
      }
    }

    public static class ResultGroupComponent extends BackboneElement {
        /**
         * A code or name that describes this group of results. For the base group, this is the report name.
         */
        protected CodeableConcept name;

        /**
         * Details about the individual specimen to which these 'Result group' test results refer.
         */
        protected ResourceReference specimen;

        /**
         * A subgroup in a report group. Subgroups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.
         */
        protected List<ResultGroupComponent> group = new ArrayList<ResultGroupComponent>();

        /**
         * Specific detailed result, including both the value of the result item and additional information that may be useful for clinical interpretation. Results include whatever specific data items pathology labs report as part of the clinical service; it is not confined to measurements.
         */
        protected List<ResourceReference> result = new ArrayList<ResourceReference>();

      public ResultGroupComponent() {
        super();
      }

      public ResultGroupComponent(CodeableConcept name) {
        super();
        this.name = name;
      }

        /**
         * @return {@link #name} (A code or name that describes this group of results. For the base group, this is the report name.)
         */
        public CodeableConcept getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (A code or name that describes this group of results. For the base group, this is the report name.)
         */
        public ResultGroupComponent setName(CodeableConcept value) { 
          this.name = value;
          return this;
        }

        /**
         * @return {@link #specimen} (Details about the individual specimen to which these 'Result group' test results refer.)
         */
        public ResourceReference getSpecimen() { 
          return this.specimen;
        }

        /**
         * @param value {@link #specimen} (Details about the individual specimen to which these 'Result group' test results refer.)
         */
        public ResultGroupComponent setSpecimen(ResourceReference value) { 
          this.specimen = value;
          return this;
        }

        /**
         * @return {@link #group} (A subgroup in a report group. Subgroups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.)
         */
        public List<ResultGroupComponent> getGroup() { 
          return this.group;
        }

    // syntactic sugar
        /**
         * @return {@link #group} (A subgroup in a report group. Subgroups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.)
         */
        public ResultGroupComponent addGroup() { 
          ResultGroupComponent t = new ResultGroupComponent();
          this.group.add(t);
          return t;
        }

        /**
         * @return {@link #result} (Specific detailed result, including both the value of the result item and additional information that may be useful for clinical interpretation. Results include whatever specific data items pathology labs report as part of the clinical service; it is not confined to measurements.)
         */
        public List<ResourceReference> getResult() { 
          return this.result;
        }

    // syntactic sugar
        /**
         * @return {@link #result} (Specific detailed result, including both the value of the result item and additional information that may be useful for clinical interpretation. Results include whatever specific data items pathology labs report as part of the clinical service; it is not confined to measurements.)
         */
        public ResourceReference addResult() { 
          ResourceReference t = new ResourceReference();
          this.result.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "CodeableConcept", "A code or name that describes this group of results. For the base group, this is the report name.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("specimen", "Resource(Specimen)", "Details about the individual specimen to which these 'Result group' test results refer.", 0, java.lang.Integer.MAX_VALUE, specimen));
          childrenList.add(new Property("group", "@DiagnosticReport.results", "A subgroup in a report group. Subgroups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.", 0, java.lang.Integer.MAX_VALUE, group));
          childrenList.add(new Property("result", "Resource(Observation)", "Specific detailed result, including both the value of the result item and additional information that may be useful for clinical interpretation. Results include whatever specific data items pathology labs report as part of the clinical service; it is not confined to measurements.", 0, java.lang.Integer.MAX_VALUE, result));
        }

      public ResultGroupComponent copy(DiagnosticReport e) {
        ResultGroupComponent dst = new ResultGroupComponent();
        dst.name = name == null ? null : name.copy();
        dst.specimen = specimen == null ? null : specimen.copy();
        dst.group = new ArrayList<ResultGroupComponent>();
        for (ResultGroupComponent i : group)
          dst.group.add(i.copy(e));
        dst.result = new ArrayList<ResourceReference>();
        for (ResourceReference i : result)
          dst.result.add(i.copy());
        return dst;
      }

  }

    public static class DiagnosticReportImageComponent extends BackboneElement {
        /**
         * A comment about the image. Typically, this is used to provide an explanation for why the image is included, or to draw the viewer's attention to important features.
         */
        protected String_ comment;

        /**
         * Reference to the image source.
         */
        protected ResourceReference link;

      public DiagnosticReportImageComponent() {
        super();
      }

      public DiagnosticReportImageComponent(ResourceReference link) {
        super();
        this.link = link;
      }

        /**
         * @return {@link #comment} (A comment about the image. Typically, this is used to provide an explanation for why the image is included, or to draw the viewer's attention to important features.)
         */
        public String_ getComment() { 
          return this.comment;
        }

        /**
         * @param value {@link #comment} (A comment about the image. Typically, this is used to provide an explanation for why the image is included, or to draw the viewer's attention to important features.)
         */
        public DiagnosticReportImageComponent setComment(String_ value) { 
          this.comment = value;
          return this;
        }

        /**
         * @return A comment about the image. Typically, this is used to provide an explanation for why the image is included, or to draw the viewer's attention to important features.
         */
        public String getCommentSimple() { 
          return this.comment == null ? null : this.comment.getValue();
        }

        /**
         * @param value A comment about the image. Typically, this is used to provide an explanation for why the image is included, or to draw the viewer's attention to important features.
         */
        public DiagnosticReportImageComponent setCommentSimple(String value) { 
          if (value == null)
            this.comment = null;
          else {
            if (this.comment == null)
              this.comment = new String_();
            this.comment.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #link} (Reference to the image source.)
         */
        public ResourceReference getLink() { 
          return this.link;
        }

        /**
         * @param value {@link #link} (Reference to the image source.)
         */
        public DiagnosticReportImageComponent setLink(ResourceReference value) { 
          this.link = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("comment", "string", "A comment about the image. Typically, this is used to provide an explanation for why the image is included, or to draw the viewer's attention to important features.", 0, java.lang.Integer.MAX_VALUE, comment));
          childrenList.add(new Property("link", "Resource(Media)", "Reference to the image source.", 0, java.lang.Integer.MAX_VALUE, link));
        }

      public DiagnosticReportImageComponent copy(DiagnosticReport e) {
        DiagnosticReportImageComponent dst = new DiagnosticReportImageComponent();
        dst.comment = comment == null ? null : comment.copy();
        dst.link = link == null ? null : link.copy();
        return dst;
      }

  }

    /**
     * The status of the diagnostic report as a whole.
     */
    protected Enumeration<DiagnosticReportStatus> status;

    /**
     * The date and/or time that this version of the report was released from the source diagnostic service.
     */
    protected DateTime issued;

    /**
     * The subject of the report. Usually, but not always, this is a patient. However diagnostic services also perform analyses on specimens collected from a variety of other sources.
     */
    protected ResourceReference subject;

    /**
     * The diagnostic service that is responsible for issuing the report.
     */
    protected ResourceReference performer;

    /**
     * The local ID assigned to the report by the order filler, usually by the Information System of the diagnostic service provider.
     */
    protected Identifier identifier;

    /**
     * Details concerning a test requested.
     */
    protected List<ResourceReference> requestDetail = new ArrayList<ResourceReference>();

    /**
     * The section of the diagnostic service that performs the examination e.g. biochemistry, hematology, MRI.
     */
    protected CodeableConcept serviceCategory;

    /**
     * The time or time-period the observed values are related to. This is usually either the time of the procedure or of specimen collection(s), but very often the source of the date/time is not known, only the date/time itself.
     */
    protected Type diagnostic;

    /**
     * A group of results. Results may be grouped by specimen, or by some value in DiagnosticReport.resultGroup.name to describe what binds all the results together.
     */
    protected ResultGroupComponent results;

    /**
     * One or more links to full details of any imaging performed during the diagnostic investigation. Typically, this is imaging performed by DICOM enabled modalities, but this is not required. A fully enabled PACS viewer can use this information to provide views of the source images.
     */
    protected List<ResourceReference> imagingStudy = new ArrayList<ResourceReference>();

    /**
     * A list of key images associated with this report. The images are generally created during the diagnostic process, and may be directly of the patient, or of treated specimens (i.e. slides of interest).
     */
    protected List<DiagnosticReportImageComponent> image = new ArrayList<DiagnosticReportImageComponent>();

    /**
     * Concise and clinically contextualized narrative interpretation of the diagnostic report.
     */
    protected String_ conclusion;

    /**
     * Codes for the conclusion.
     */
    protected List<CodeableConcept> codedDiagnosis = new ArrayList<CodeableConcept>();

    /**
     * Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.
     */
    protected List<Attachment> presentedForm = new ArrayList<Attachment>();

    public DiagnosticReport() {
      super();
    }

    public DiagnosticReport(Enumeration<DiagnosticReportStatus> status, DateTime issued, ResourceReference subject, ResourceReference performer, Type diagnostic, ResultGroupComponent results) {
      super();
      this.status = status;
      this.issued = issued;
      this.subject = subject;
      this.performer = performer;
      this.diagnostic = diagnostic;
      this.results = results;
    }

    /**
     * @return {@link #status} (The status of the diagnostic report as a whole.)
     */
    public Enumeration<DiagnosticReportStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the diagnostic report as a whole.)
     */
    public DiagnosticReport setStatus(Enumeration<DiagnosticReportStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the diagnostic report as a whole.
     */
    public DiagnosticReportStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the diagnostic report as a whole.
     */
    public DiagnosticReport setStatusSimple(DiagnosticReportStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<DiagnosticReportStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #issued} (The date and/or time that this version of the report was released from the source diagnostic service.)
     */
    public DateTime getIssued() { 
      return this.issued;
    }

    /**
     * @param value {@link #issued} (The date and/or time that this version of the report was released from the source diagnostic service.)
     */
    public DiagnosticReport setIssued(DateTime value) { 
      this.issued = value;
      return this;
    }

    /**
     * @return The date and/or time that this version of the report was released from the source diagnostic service.
     */
    public DateAndTime getIssuedSimple() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    /**
     * @param value The date and/or time that this version of the report was released from the source diagnostic service.
     */
    public DiagnosticReport setIssuedSimple(DateAndTime value) { 
        if (this.issued == null)
          this.issued = new DateTime();
        this.issued.setValue(value);
      return this;
    }

    /**
     * @return {@link #subject} (The subject of the report. Usually, but not always, this is a patient. However diagnostic services also perform analyses on specimens collected from a variety of other sources.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The subject of the report. Usually, but not always, this is a patient. However diagnostic services also perform analyses on specimens collected from a variety of other sources.)
     */
    public DiagnosticReport setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #performer} (The diagnostic service that is responsible for issuing the report.)
     */
    public ResourceReference getPerformer() { 
      return this.performer;
    }

    /**
     * @param value {@link #performer} (The diagnostic service that is responsible for issuing the report.)
     */
    public DiagnosticReport setPerformer(ResourceReference value) { 
      this.performer = value;
      return this;
    }

    /**
     * @return {@link #identifier} (The local ID assigned to the report by the order filler, usually by the Information System of the diagnostic service provider.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The local ID assigned to the report by the order filler, usually by the Information System of the diagnostic service provider.)
     */
    public DiagnosticReport setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #requestDetail} (Details concerning a test requested.)
     */
    public List<ResourceReference> getRequestDetail() { 
      return this.requestDetail;
    }

    // syntactic sugar
    /**
     * @return {@link #requestDetail} (Details concerning a test requested.)
     */
    public ResourceReference addRequestDetail() { 
      ResourceReference t = new ResourceReference();
      this.requestDetail.add(t);
      return t;
    }

    /**
     * @return {@link #serviceCategory} (The section of the diagnostic service that performs the examination e.g. biochemistry, hematology, MRI.)
     */
    public CodeableConcept getServiceCategory() { 
      return this.serviceCategory;
    }

    /**
     * @param value {@link #serviceCategory} (The section of the diagnostic service that performs the examination e.g. biochemistry, hematology, MRI.)
     */
    public DiagnosticReport setServiceCategory(CodeableConcept value) { 
      this.serviceCategory = value;
      return this;
    }

    /**
     * @return {@link #diagnostic} (The time or time-period the observed values are related to. This is usually either the time of the procedure or of specimen collection(s), but very often the source of the date/time is not known, only the date/time itself.)
     */
    public Type getDiagnostic() { 
      return this.diagnostic;
    }

    /**
     * @param value {@link #diagnostic} (The time or time-period the observed values are related to. This is usually either the time of the procedure or of specimen collection(s), but very often the source of the date/time is not known, only the date/time itself.)
     */
    public DiagnosticReport setDiagnostic(Type value) { 
      this.diagnostic = value;
      return this;
    }

    /**
     * @return {@link #results} (A group of results. Results may be grouped by specimen, or by some value in DiagnosticReport.resultGroup.name to describe what binds all the results together.)
     */
    public ResultGroupComponent getResults() { 
      return this.results;
    }

    /**
     * @param value {@link #results} (A group of results. Results may be grouped by specimen, or by some value in DiagnosticReport.resultGroup.name to describe what binds all the results together.)
     */
    public DiagnosticReport setResults(ResultGroupComponent value) { 
      this.results = value;
      return this;
    }

    /**
     * @return {@link #imagingStudy} (One or more links to full details of any imaging performed during the diagnostic investigation. Typically, this is imaging performed by DICOM enabled modalities, but this is not required. A fully enabled PACS viewer can use this information to provide views of the source images.)
     */
    public List<ResourceReference> getImagingStudy() { 
      return this.imagingStudy;
    }

    // syntactic sugar
    /**
     * @return {@link #imagingStudy} (One or more links to full details of any imaging performed during the diagnostic investigation. Typically, this is imaging performed by DICOM enabled modalities, but this is not required. A fully enabled PACS viewer can use this information to provide views of the source images.)
     */
    public ResourceReference addImagingStudy() { 
      ResourceReference t = new ResourceReference();
      this.imagingStudy.add(t);
      return t;
    }

    /**
     * @return {@link #image} (A list of key images associated with this report. The images are generally created during the diagnostic process, and may be directly of the patient, or of treated specimens (i.e. slides of interest).)
     */
    public List<DiagnosticReportImageComponent> getImage() { 
      return this.image;
    }

    // syntactic sugar
    /**
     * @return {@link #image} (A list of key images associated with this report. The images are generally created during the diagnostic process, and may be directly of the patient, or of treated specimens (i.e. slides of interest).)
     */
    public DiagnosticReportImageComponent addImage() { 
      DiagnosticReportImageComponent t = new DiagnosticReportImageComponent();
      this.image.add(t);
      return t;
    }

    /**
     * @return {@link #conclusion} (Concise and clinically contextualized narrative interpretation of the diagnostic report.)
     */
    public String_ getConclusion() { 
      return this.conclusion;
    }

    /**
     * @param value {@link #conclusion} (Concise and clinically contextualized narrative interpretation of the diagnostic report.)
     */
    public DiagnosticReport setConclusion(String_ value) { 
      this.conclusion = value;
      return this;
    }

    /**
     * @return Concise and clinically contextualized narrative interpretation of the diagnostic report.
     */
    public String getConclusionSimple() { 
      return this.conclusion == null ? null : this.conclusion.getValue();
    }

    /**
     * @param value Concise and clinically contextualized narrative interpretation of the diagnostic report.
     */
    public DiagnosticReport setConclusionSimple(String value) { 
      if (value == null)
        this.conclusion = null;
      else {
        if (this.conclusion == null)
          this.conclusion = new String_();
        this.conclusion.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #codedDiagnosis} (Codes for the conclusion.)
     */
    public List<CodeableConcept> getCodedDiagnosis() { 
      return this.codedDiagnosis;
    }

    // syntactic sugar
    /**
     * @return {@link #codedDiagnosis} (Codes for the conclusion.)
     */
    public CodeableConcept addCodedDiagnosis() { 
      CodeableConcept t = new CodeableConcept();
      this.codedDiagnosis.add(t);
      return t;
    }

    /**
     * @return {@link #presentedForm} (Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.)
     */
    public List<Attachment> getPresentedForm() { 
      return this.presentedForm;
    }

    // syntactic sugar
    /**
     * @return {@link #presentedForm} (Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.)
     */
    public Attachment addPresentedForm() { 
      Attachment t = new Attachment();
      this.presentedForm.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("status", "code", "The status of the diagnostic report as a whole.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("issued", "dateTime", "The date and/or time that this version of the report was released from the source diagnostic service.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device|Location)", "The subject of the report. Usually, but not always, this is a patient. However diagnostic services also perform analyses on specimens collected from a variety of other sources.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("performer", "Resource(Practitioner|Organization)", "The diagnostic service that is responsible for issuing the report.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("identifier", "Identifier", "The local ID assigned to the report by the order filler, usually by the Information System of the diagnostic service provider.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("requestDetail", "Resource(DiagnosticOrder)", "Details concerning a test requested.", 0, java.lang.Integer.MAX_VALUE, requestDetail));
        childrenList.add(new Property("serviceCategory", "CodeableConcept", "The section of the diagnostic service that performs the examination e.g. biochemistry, hematology, MRI.", 0, java.lang.Integer.MAX_VALUE, serviceCategory));
        childrenList.add(new Property("diagnostic[x]", "dateTime|Period", "The time or time-period the observed values are related to. This is usually either the time of the procedure or of specimen collection(s), but very often the source of the date/time is not known, only the date/time itself.", 0, java.lang.Integer.MAX_VALUE, diagnostic));
        childrenList.add(new Property("results", "", "A group of results. Results may be grouped by specimen, or by some value in DiagnosticReport.resultGroup.name to describe what binds all the results together.", 0, java.lang.Integer.MAX_VALUE, results));
        childrenList.add(new Property("imagingStudy", "Resource(ImagingStudy)", "One or more links to full details of any imaging performed during the diagnostic investigation. Typically, this is imaging performed by DICOM enabled modalities, but this is not required. A fully enabled PACS viewer can use this information to provide views of the source images.", 0, java.lang.Integer.MAX_VALUE, imagingStudy));
        childrenList.add(new Property("image", "", "A list of key images associated with this report. The images are generally created during the diagnostic process, and may be directly of the patient, or of treated specimens (i.e. slides of interest).", 0, java.lang.Integer.MAX_VALUE, image));
        childrenList.add(new Property("conclusion", "string", "Concise and clinically contextualized narrative interpretation of the diagnostic report.", 0, java.lang.Integer.MAX_VALUE, conclusion));
        childrenList.add(new Property("codedDiagnosis", "CodeableConcept", "Codes for the conclusion.", 0, java.lang.Integer.MAX_VALUE, codedDiagnosis));
        childrenList.add(new Property("presentedForm", "Attachment", "Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.", 0, java.lang.Integer.MAX_VALUE, presentedForm));
      }

      public DiagnosticReport copy() {
        DiagnosticReport dst = new DiagnosticReport();
        dst.status = status == null ? null : status.copy();
        dst.issued = issued == null ? null : issued.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.performer = performer == null ? null : performer.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.requestDetail = new ArrayList<ResourceReference>();
        for (ResourceReference i : requestDetail)
          dst.requestDetail.add(i.copy());
        dst.serviceCategory = serviceCategory == null ? null : serviceCategory.copy();
        dst.diagnostic = diagnostic == null ? null : diagnostic.copy();
        dst.results = results == null ? null : results.copy(dst);
        dst.imagingStudy = new ArrayList<ResourceReference>();
        for (ResourceReference i : imagingStudy)
          dst.imagingStudy.add(i.copy());
        dst.image = new ArrayList<DiagnosticReportImageComponent>();
        for (DiagnosticReportImageComponent i : image)
          dst.image.add(i.copy(dst));
        dst.conclusion = conclusion == null ? null : conclusion.copy();
        dst.codedDiagnosis = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : codedDiagnosis)
          dst.codedDiagnosis.add(i.copy());
        dst.presentedForm = new ArrayList<Attachment>();
        for (Attachment i : presentedForm)
          dst.presentedForm.add(i.copy());
        return dst;
      }

      protected DiagnosticReport typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DiagnosticReport;
   }


}

