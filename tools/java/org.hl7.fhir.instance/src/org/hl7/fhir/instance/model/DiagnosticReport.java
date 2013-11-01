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

// Generated on Sat, Nov 2, 2013 09:06+1100 for FHIR v0.12

import java.util.*;

/**
 * The findings and interpretation of diagnostic  tests performed on patients and/or specimens. The report includes clinical context such as requesting and provider information, and some mix of atomic results, images, textual and coded interpretation, and formatted representation of diagnostic reports.
 */
public class DiagnosticReport extends Resource {

    public enum ObservationStatus {
        registered, // The existence of the observation is registered, but there is no result yet available
        interim, // This is an initial or interim observation: data may be incomplete or unverified
        final_, // The observation is complete and verified by an authorised person
        amended, // The observation has been modified subsequent to being Final, and is complete and verified by an authorised person
        cancelled, // The observation is unavailable because the measurement was not started or not completed (also sometimes called "aborted")
        withdrawn, // The observation has been withdrawn following previous Final release
        Null; // added to help the parsers
        public static ObservationStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return registered;
        if ("interim".equals(codeString))
          return interim;
        if ("final".equals(codeString))
          return final_;
        if ("amended".equals(codeString))
          return amended;
        if ("cancelled".equals(codeString))
          return cancelled;
        if ("withdrawn".equals(codeString))
          return withdrawn;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case registered: return "registered";
            case interim: return "interim";
            case final_: return "final";
            case amended: return "amended";
            case cancelled: return "cancelled";
            case withdrawn: return "withdrawn";
            default: return "?";
          }
        }
    }

  public static class ObservationStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("registered".equals(codeString))
          return ObservationStatus.registered;
        if ("interim".equals(codeString))
          return ObservationStatus.interim;
        if ("final".equals(codeString))
          return ObservationStatus.final_;
        if ("amended".equals(codeString))
          return ObservationStatus.amended;
        if ("cancelled".equals(codeString))
          return ObservationStatus.cancelled;
        if ("withdrawn".equals(codeString))
          return ObservationStatus.withdrawn;
        throw new Exception("Unknown ObservationStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ObservationStatus.registered)
        return "registered";
      if (code == ObservationStatus.interim)
        return "interim";
      if (code == ObservationStatus.final_)
        return "final";
      if (code == ObservationStatus.amended)
        return "amended";
      if (code == ObservationStatus.cancelled)
        return "cancelled";
      if (code == ObservationStatus.withdrawn)
        return "withdrawn";
      return "?";
      }
    }

    public static class DiagnosticReportRequestDetailComponent extends BackboneElement {
        /**
         * The encounter that this diagnostic investigation is associated with.
         */
        protected ResourceReference encounter;

        /**
         * The local ID assigned to the order by the order requester.
         */
        protected Identifier requestOrderId;

        /**
         * The local ID assigned to the test order by the diagnostic service provider.
         */
        protected Identifier receiverOrderId;

        /**
         * Identification of pathology test requested,.
         */
        protected List<CodeableConcept> requestTest = new ArrayList<CodeableConcept>();

        /**
         * Anatomical location where the request test should be performed.
         */
        protected CodeableConcept bodySite;

        /**
         * Details of the clinician or organization requesting the diagnostic service.
         */
        protected ResourceReference requester;

        /**
         * Details of the clinical information provided to the diagnostic service along with the original request.
         */
        protected String_ clinicalInfo;

      public DiagnosticReportRequestDetailComponent() {
        super();
      }

        /**
         * @return {@link #encounter} (The encounter that this diagnostic investigation is associated with.)
         */
        public ResourceReference getEncounter() { 
          return this.encounter;
        }

        /**
         * @param value {@link #encounter} (The encounter that this diagnostic investigation is associated with.)
         */
        public DiagnosticReportRequestDetailComponent setEncounter(ResourceReference value) { 
          this.encounter = value;
          return this;
        }

        /**
         * @return {@link #requestOrderId} (The local ID assigned to the order by the order requester.)
         */
        public Identifier getRequestOrderId() { 
          return this.requestOrderId;
        }

        /**
         * @param value {@link #requestOrderId} (The local ID assigned to the order by the order requester.)
         */
        public DiagnosticReportRequestDetailComponent setRequestOrderId(Identifier value) { 
          this.requestOrderId = value;
          return this;
        }

        /**
         * @return {@link #receiverOrderId} (The local ID assigned to the test order by the diagnostic service provider.)
         */
        public Identifier getReceiverOrderId() { 
          return this.receiverOrderId;
        }

        /**
         * @param value {@link #receiverOrderId} (The local ID assigned to the test order by the diagnostic service provider.)
         */
        public DiagnosticReportRequestDetailComponent setReceiverOrderId(Identifier value) { 
          this.receiverOrderId = value;
          return this;
        }

        /**
         * @return {@link #requestTest} (Identification of pathology test requested,.)
         */
        public List<CodeableConcept> getRequestTest() { 
          return this.requestTest;
        }

    // syntactic sugar
        /**
         * @return {@link #requestTest} (Identification of pathology test requested,.)
         */
        public CodeableConcept addRequestTest() { 
          CodeableConcept t = new CodeableConcept();
          this.requestTest.add(t);
          return t;
        }

        /**
         * @return {@link #bodySite} (Anatomical location where the request test should be performed.)
         */
        public CodeableConcept getBodySite() { 
          return this.bodySite;
        }

        /**
         * @param value {@link #bodySite} (Anatomical location where the request test should be performed.)
         */
        public DiagnosticReportRequestDetailComponent setBodySite(CodeableConcept value) { 
          this.bodySite = value;
          return this;
        }

        /**
         * @return {@link #requester} (Details of the clinician or organization requesting the diagnostic service.)
         */
        public ResourceReference getRequester() { 
          return this.requester;
        }

        /**
         * @param value {@link #requester} (Details of the clinician or organization requesting the diagnostic service.)
         */
        public DiagnosticReportRequestDetailComponent setRequester(ResourceReference value) { 
          this.requester = value;
          return this;
        }

        /**
         * @return {@link #clinicalInfo} (Details of the clinical information provided to the diagnostic service along with the original request.)
         */
        public String_ getClinicalInfo() { 
          return this.clinicalInfo;
        }

        /**
         * @param value {@link #clinicalInfo} (Details of the clinical information provided to the diagnostic service along with the original request.)
         */
        public DiagnosticReportRequestDetailComponent setClinicalInfo(String_ value) { 
          this.clinicalInfo = value;
          return this;
        }

        /**
         * @return Details of the clinical information provided to the diagnostic service along with the original request.
         */
        public String getClinicalInfoSimple() { 
          return this.clinicalInfo == null ? null : this.clinicalInfo.getValue();
        }

        /**
         * @param value Details of the clinical information provided to the diagnostic service along with the original request.
         */
        public DiagnosticReportRequestDetailComponent setClinicalInfoSimple(String value) { 
          if (value == null)
            this.clinicalInfo = null;
          else {
            if (this.clinicalInfo == null)
              this.clinicalInfo = new String_();
            this.clinicalInfo.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("encounter", "Resource(Encounter)", "The encounter that this diagnostic investigation is associated with.", 0, java.lang.Integer.MAX_VALUE, encounter));
          childrenList.add(new Property("requestOrderId", "Identifier", "The local ID assigned to the order by the order requester.", 0, java.lang.Integer.MAX_VALUE, requestOrderId));
          childrenList.add(new Property("receiverOrderId", "Identifier", "The local ID assigned to the test order by the diagnostic service provider.", 0, java.lang.Integer.MAX_VALUE, receiverOrderId));
          childrenList.add(new Property("requestTest", "CodeableConcept", "Identification of pathology test requested,.", 0, java.lang.Integer.MAX_VALUE, requestTest));
          childrenList.add(new Property("bodySite", "CodeableConcept", "Anatomical location where the request test should be performed.", 0, java.lang.Integer.MAX_VALUE, bodySite));
          childrenList.add(new Property("requester", "Resource(Organization|Practitioner)", "Details of the clinician or organization requesting the diagnostic service.", 0, java.lang.Integer.MAX_VALUE, requester));
          childrenList.add(new Property("clinicalInfo", "string", "Details of the clinical information provided to the diagnostic service along with the original request.", 0, java.lang.Integer.MAX_VALUE, clinicalInfo));
        }

      public DiagnosticReportRequestDetailComponent copy(DiagnosticReport e) {
        DiagnosticReportRequestDetailComponent dst = new DiagnosticReportRequestDetailComponent();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.requestOrderId = requestOrderId == null ? null : requestOrderId.copy();
        dst.receiverOrderId = receiverOrderId == null ? null : receiverOrderId.copy();
        dst.requestTest = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : requestTest)
          dst.requestTest.add(i.copy());
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.requester = requester == null ? null : requester.copy();
        dst.clinicalInfo = clinicalInfo == null ? null : clinicalInfo.copy();
        return dst;
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
         * A sub-group in a report group. Sub groups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.
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
         * @return {@link #group} (A sub-group in a report group. Sub groups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.)
         */
        public List<ResultGroupComponent> getGroup() { 
          return this.group;
        }

    // syntactic sugar
        /**
         * @return {@link #group} (A sub-group in a report group. Sub groups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.)
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
          childrenList.add(new Property("group", "@DiagnosticReport.results", "A sub-group in a report group. Sub groups can be grouped in arbitrary ways. The group.name defines the purpose and interpretation of the grouping.", 0, java.lang.Integer.MAX_VALUE, group));
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

    /**
     * The status of the diagnostic report as a whole.
     */
    protected Enumeration<ObservationStatus> status;

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
    protected Identifier reportId;

    /**
     * Details concerning a single pathology test requested.
     */
    protected List<DiagnosticReportRequestDetailComponent> requestDetail = new ArrayList<DiagnosticReportRequestDetailComponent>();

    /**
     * The section of the diagnostic service that performs the examination e.g. biochemistry, haematology, MRI.
     */
    protected CodeableConcept serviceCategory;

    /**
     * The diagnostically relevant time for this report - that is, the point in time at which the observations that are reported in this diagnostic report relate to the patient.
     */
    protected Type diagnostic;

    /**
     * A group of results. Results may be grouped by specimen, or by some value in DiagnosticReport.resultGroup.name to describe what binds all the results together.
     */
    protected ResultGroupComponent results;

    /**
     * A list of key images associated with this report. The images are generally created during the diagnostic process, and maybe directly of the patient, or of treated specimens (i.e. slides of interest).
     */
    protected List<ResourceReference> image = new ArrayList<ResourceReference>();

    /**
     * Concise and clinically contextualised narrative interpretation of the diagnostic report.
     */
    protected String_ conclusion;

    /**
     * Codes for the conclusion.
     */
    protected List<CodeableConcept> codedDiagnosis = new ArrayList<CodeableConcept>();

    /**
     * Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.
     */
    protected List<Attachment> representation = new ArrayList<Attachment>();

    public DiagnosticReport() {
      super();
    }

    public DiagnosticReport(Enumeration<ObservationStatus> status, DateTime issued, ResourceReference subject, ResourceReference performer, Type diagnostic, ResultGroupComponent results) {
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
    public Enumeration<ObservationStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the diagnostic report as a whole.)
     */
    public DiagnosticReport setStatus(Enumeration<ObservationStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the diagnostic report as a whole.
     */
    public ObservationStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the diagnostic report as a whole.
     */
    public DiagnosticReport setStatusSimple(ObservationStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ObservationStatus>();
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
    public String getIssuedSimple() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    /**
     * @param value The date and/or time that this version of the report was released from the source diagnostic service.
     */
    public DiagnosticReport setIssuedSimple(String value) { 
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
     * @return {@link #reportId} (The local ID assigned to the report by the order filler, usually by the Information System of the diagnostic service provider.)
     */
    public Identifier getReportId() { 
      return this.reportId;
    }

    /**
     * @param value {@link #reportId} (The local ID assigned to the report by the order filler, usually by the Information System of the diagnostic service provider.)
     */
    public DiagnosticReport setReportId(Identifier value) { 
      this.reportId = value;
      return this;
    }

    /**
     * @return {@link #requestDetail} (Details concerning a single pathology test requested.)
     */
    public List<DiagnosticReportRequestDetailComponent> getRequestDetail() { 
      return this.requestDetail;
    }

    // syntactic sugar
    /**
     * @return {@link #requestDetail} (Details concerning a single pathology test requested.)
     */
    public DiagnosticReportRequestDetailComponent addRequestDetail() { 
      DiagnosticReportRequestDetailComponent t = new DiagnosticReportRequestDetailComponent();
      this.requestDetail.add(t);
      return t;
    }

    /**
     * @return {@link #serviceCategory} (The section of the diagnostic service that performs the examination e.g. biochemistry, haematology, MRI.)
     */
    public CodeableConcept getServiceCategory() { 
      return this.serviceCategory;
    }

    /**
     * @param value {@link #serviceCategory} (The section of the diagnostic service that performs the examination e.g. biochemistry, haematology, MRI.)
     */
    public DiagnosticReport setServiceCategory(CodeableConcept value) { 
      this.serviceCategory = value;
      return this;
    }

    /**
     * @return {@link #diagnostic} (The diagnostically relevant time for this report - that is, the point in time at which the observations that are reported in this diagnostic report relate to the patient.)
     */
    public Type getDiagnostic() { 
      return this.diagnostic;
    }

    /**
     * @param value {@link #diagnostic} (The diagnostically relevant time for this report - that is, the point in time at which the observations that are reported in this diagnostic report relate to the patient.)
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
     * @return {@link #image} (A list of key images associated with this report. The images are generally created during the diagnostic process, and maybe directly of the patient, or of treated specimens (i.e. slides of interest).)
     */
    public List<ResourceReference> getImage() { 
      return this.image;
    }

    // syntactic sugar
    /**
     * @return {@link #image} (A list of key images associated with this report. The images are generally created during the diagnostic process, and maybe directly of the patient, or of treated specimens (i.e. slides of interest).)
     */
    public ResourceReference addImage() { 
      ResourceReference t = new ResourceReference();
      this.image.add(t);
      return t;
    }

    /**
     * @return {@link #conclusion} (Concise and clinically contextualised narrative interpretation of the diagnostic report.)
     */
    public String_ getConclusion() { 
      return this.conclusion;
    }

    /**
     * @param value {@link #conclusion} (Concise and clinically contextualised narrative interpretation of the diagnostic report.)
     */
    public DiagnosticReport setConclusion(String_ value) { 
      this.conclusion = value;
      return this;
    }

    /**
     * @return Concise and clinically contextualised narrative interpretation of the diagnostic report.
     */
    public String getConclusionSimple() { 
      return this.conclusion == null ? null : this.conclusion.getValue();
    }

    /**
     * @param value Concise and clinically contextualised narrative interpretation of the diagnostic report.
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
     * @return {@link #representation} (Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.)
     */
    public List<Attachment> getRepresentation() { 
      return this.representation;
    }

    // syntactic sugar
    /**
     * @return {@link #representation} (Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.)
     */
    public Attachment addRepresentation() { 
      Attachment t = new Attachment();
      this.representation.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("status", "code", "The status of the diagnostic report as a whole.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("issued", "dateTime", "The date and/or time that this version of the report was released from the source diagnostic service.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device|Location)", "The subject of the report. Usually, but not always, this is a patient. However diagnostic services also perform analyses on specimens collected from a variety of other sources.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("performer", "Resource(Organization)", "The diagnostic service that is responsible for issuing the report.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("reportId", "Identifier", "The local ID assigned to the report by the order filler, usually by the Information System of the diagnostic service provider.", 0, java.lang.Integer.MAX_VALUE, reportId));
        childrenList.add(new Property("requestDetail", "", "Details concerning a single pathology test requested.", 0, java.lang.Integer.MAX_VALUE, requestDetail));
        childrenList.add(new Property("serviceCategory", "CodeableConcept", "The section of the diagnostic service that performs the examination e.g. biochemistry, haematology, MRI.", 0, java.lang.Integer.MAX_VALUE, serviceCategory));
        childrenList.add(new Property("diagnostic[x]", "dateTime|Period", "The diagnostically relevant time for this report - that is, the point in time at which the observations that are reported in this diagnostic report relate to the patient.", 0, java.lang.Integer.MAX_VALUE, diagnostic));
        childrenList.add(new Property("results", "", "A group of results. Results may be grouped by specimen, or by some value in DiagnosticReport.resultGroup.name to describe what binds all the results together.", 0, java.lang.Integer.MAX_VALUE, results));
        childrenList.add(new Property("image", "Resource(Media|ImagingStudy)", "A list of key images associated with this report. The images are generally created during the diagnostic process, and maybe directly of the patient, or of treated specimens (i.e. slides of interest).", 0, java.lang.Integer.MAX_VALUE, image));
        childrenList.add(new Property("conclusion", "string", "Concise and clinically contextualised narrative interpretation of the diagnostic report.", 0, java.lang.Integer.MAX_VALUE, conclusion));
        childrenList.add(new Property("codedDiagnosis", "CodeableConcept", "Codes for the conclusion.", 0, java.lang.Integer.MAX_VALUE, codedDiagnosis));
        childrenList.add(new Property("representation", "Attachment", "Rich text representation of the entire result as issued by the diagnostic service. Multiple formats are allowed but they SHALL be semantically equivalent.", 0, java.lang.Integer.MAX_VALUE, representation));
      }

      public DiagnosticReport copy() {
        DiagnosticReport dst = new DiagnosticReport();
        dst.status = status == null ? null : status.copy();
        dst.issued = issued == null ? null : issued.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.performer = performer == null ? null : performer.copy();
        dst.reportId = reportId == null ? null : reportId.copy();
        dst.requestDetail = new ArrayList<DiagnosticReportRequestDetailComponent>();
        for (DiagnosticReportRequestDetailComponent i : requestDetail)
          dst.requestDetail.add(i.copy(dst));
        dst.serviceCategory = serviceCategory == null ? null : serviceCategory.copy();
        dst.diagnostic = diagnostic == null ? null : diagnostic.copy();
        dst.results = results == null ? null : results.copy(dst);
        dst.image = new ArrayList<ResourceReference>();
        for (ResourceReference i : image)
          dst.image.add(i.copy());
        dst.conclusion = conclusion == null ? null : conclusion.copy();
        dst.codedDiagnosis = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : codedDiagnosis)
          dst.codedDiagnosis.add(i.copy());
        dst.representation = new ArrayList<Attachment>();
        for (Attachment i : representation)
          dst.representation.add(i.copy());
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

