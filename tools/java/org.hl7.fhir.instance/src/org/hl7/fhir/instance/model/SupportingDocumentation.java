package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011+, HL7, Inc.
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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

/**
 * This resource provides the supporting information for a process, for example clinical or financial  information related to a claim or pre-authorization.
 */
public class SupportingDocumentation extends DomainResource {

    public static class SupportingDocumentationDetailComponent extends BackboneElement {
        /**
         * A link Id for the response to reference.
         */
        protected IntegerType linkId;

        /**
         * The attached content.
         */
        protected Type content;

        /**
         * The date when the material was created.
         */
        protected DateType date;

        private static final long serialVersionUID = -779251474L;

      public SupportingDocumentationDetailComponent() {
        super();
      }

      public SupportingDocumentationDetailComponent(IntegerType linkId, Type content) {
        super();
        this.linkId = linkId;
        this.content = content;
      }

        /**
         * @return {@link #linkId} (A link Id for the response to reference.). This is the underlying object with id, value and extensions. The accessor "getLinkId" gives direct access to the value
         */
        public IntegerType getLinkIdElement() { 
          return this.linkId;
        }

        /**
         * @param value {@link #linkId} (A link Id for the response to reference.). This is the underlying object with id, value and extensions. The accessor "getLinkId" gives direct access to the value
         */
        public SupportingDocumentationDetailComponent setLinkIdElement(IntegerType value) { 
          this.linkId = value;
          return this;
        }

        /**
         * @return A link Id for the response to reference.
         */
        public int getLinkId() { 
          return this.linkId == null ? null : this.linkId.getValue();
        }

        /**
         * @param value A link Id for the response to reference.
         */
        public SupportingDocumentationDetailComponent setLinkId(int value) { 
            if (this.linkId == null)
              this.linkId = new IntegerType();
            this.linkId.setValue(value);
          return this;
        }

        /**
         * @return {@link #content} (The attached content.)
         */
        public Type getContent() { 
          return this.content;
        }

        /**
         * @param value {@link #content} (The attached content.)
         */
        public SupportingDocumentationDetailComponent setContent(Type value) { 
          this.content = value;
          return this;
        }

        /**
         * @return {@link #date} (The date when the material was created.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
         */
        public DateType getDateElement() { 
          return this.date;
        }

        /**
         * @param value {@link #date} (The date when the material was created.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
         */
        public SupportingDocumentationDetailComponent setDateElement(DateType value) { 
          this.date = value;
          return this;
        }

        /**
         * @return The date when the material was created.
         */
        public DateAndTime getDate() { 
          return this.date == null ? null : this.date.getValue();
        }

        /**
         * @param value The date when the material was created.
         */
        public SupportingDocumentationDetailComponent setDate(DateAndTime value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateType();
            this.date.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("linkId", "integer", "A link Id for the response to reference.", 0, java.lang.Integer.MAX_VALUE, linkId));
          childrenList.add(new Property("content[x]", "Reference(Any)|Attachment", "The attached content.", 0, java.lang.Integer.MAX_VALUE, content));
          childrenList.add(new Property("date", "date", "The date when the material was created.", 0, java.lang.Integer.MAX_VALUE, date));
        }

      public SupportingDocumentationDetailComponent copy() {
        SupportingDocumentationDetailComponent dst = new SupportingDocumentationDetailComponent();
        copyValues(dst);
        dst.linkId = linkId == null ? null : linkId.copy();
        dst.content = content == null ? null : content.copy();
        dst.date = date == null ? null : date.copy();
        return dst;
      }

  }

    /**
     * The Response Business Identifier.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The version of the style of resource contents. This should be mapped to the allowable profiles for this and supporting resources.
     */
    protected Coding ruleset;

    /**
     * The style (standard) and version of the original material which was converted into this resource.
     */
    protected Coding originalRuleset;

    /**
     * The date when this resource was created.
     */
    protected DateType date;

    /**
     * The Insurer, organization or Provider who is target  of the submission.
     */
    protected Reference target;

    /**
     * The actual object that is the target of the reference (The Insurer, organization or Provider who is target  of the submission.)
     */
    protected Resource targetTarget;

    /**
     * The practitioner who is responsible for the services rendered to the patient.
     */
    protected Reference provider;

    /**
     * The actual object that is the target of the reference (The practitioner who is responsible for the services rendered to the patient.)
     */
    protected Practitioner providerTarget;

    /**
     * The organization which is responsible for the services rendered to the patient.
     */
    protected Reference organization;

    /**
     * The actual object that is the target of the reference (The organization which is responsible for the services rendered to the patient.)
     */
    protected Organization organizationTarget;

    /**
     * Original request identifer.
     */
    protected Identifier requestIdentifier;

    /**
     * Original request identifer.
     */
    protected Reference request;

    /**
     * The actual object that is the target of the reference (Original request identifer.)
     */
    protected Resource requestTarget;

    /**
     * Original response identifer.
     */
    protected Identifier responseIdentifier;

    /**
     * Original response identifer.
     */
    protected Reference response;

    /**
     * The actual object that is the target of the reference (Original response identifer.)
     */
    protected Resource responseTarget;

    /**
     * Person who created the submission.
     */
    protected Reference author;

    /**
     * The actual object that is the target of the reference (Person who created the submission.)
     */
    protected Practitioner authorTarget;

    /**
     * The patient who is directly or indirectly the subject of the supporting information.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who is directly or indirectly the subject of the supporting information.)
     */
    protected Patient subjectTarget;

    /**
     * Supporting Files.
     */
    protected List<SupportingDocumentationDetailComponent> detail = new ArrayList<SupportingDocumentationDetailComponent>();

    private static final long serialVersionUID = 903391309L;

    public SupportingDocumentation() {
      super();
    }

    /**
     * @return {@link #identifier} (The Response Business Identifier.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (The Response Business Identifier.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #ruleset} (The version of the style of resource contents. This should be mapped to the allowable profiles for this and supporting resources.)
     */
    public Coding getRuleset() { 
      return this.ruleset;
    }

    /**
     * @param value {@link #ruleset} (The version of the style of resource contents. This should be mapped to the allowable profiles for this and supporting resources.)
     */
    public SupportingDocumentation setRuleset(Coding value) { 
      this.ruleset = value;
      return this;
    }

    /**
     * @return {@link #originalRuleset} (The style (standard) and version of the original material which was converted into this resource.)
     */
    public Coding getOriginalRuleset() { 
      return this.originalRuleset;
    }

    /**
     * @param value {@link #originalRuleset} (The style (standard) and version of the original material which was converted into this resource.)
     */
    public SupportingDocumentation setOriginalRuleset(Coding value) { 
      this.originalRuleset = value;
      return this;
    }

    /**
     * @return {@link #date} (The date when this resource was created.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date when this resource was created.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public SupportingDocumentation setDateElement(DateType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date when this resource was created.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date when this resource was created.
     */
    public SupportingDocumentation setDate(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateType();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #target} (The Insurer, organization or Provider who is target  of the submission.)
     */
    public Reference getTarget() { 
      return this.target;
    }

    /**
     * @param value {@link #target} (The Insurer, organization or Provider who is target  of the submission.)
     */
    public SupportingDocumentation setTarget(Reference value) { 
      this.target = value;
      return this;
    }

    /**
     * @return {@link #target} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The Insurer, organization or Provider who is target  of the submission.)
     */
    public Resource getTargetTarget() { 
      return this.targetTarget;
    }

    /**
     * @param value {@link #target} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The Insurer, organization or Provider who is target  of the submission.)
     */
    public SupportingDocumentation setTargetTarget(Resource value) { 
      this.targetTarget = value;
      return this;
    }

    /**
     * @return {@link #provider} (The practitioner who is responsible for the services rendered to the patient.)
     */
    public Reference getProvider() { 
      return this.provider;
    }

    /**
     * @param value {@link #provider} (The practitioner who is responsible for the services rendered to the patient.)
     */
    public SupportingDocumentation setProvider(Reference value) { 
      this.provider = value;
      return this;
    }

    /**
     * @return {@link #provider} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The practitioner who is responsible for the services rendered to the patient.)
     */
    public Practitioner getProviderTarget() { 
      return this.providerTarget;
    }

    /**
     * @param value {@link #provider} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The practitioner who is responsible for the services rendered to the patient.)
     */
    public SupportingDocumentation setProviderTarget(Practitioner value) { 
      this.providerTarget = value;
      return this;
    }

    /**
     * @return {@link #organization} (The organization which is responsible for the services rendered to the patient.)
     */
    public Reference getOrganization() { 
      return this.organization;
    }

    /**
     * @param value {@link #organization} (The organization which is responsible for the services rendered to the patient.)
     */
    public SupportingDocumentation setOrganization(Reference value) { 
      this.organization = value;
      return this;
    }

    /**
     * @return {@link #organization} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The organization which is responsible for the services rendered to the patient.)
     */
    public Organization getOrganizationTarget() { 
      return this.organizationTarget;
    }

    /**
     * @param value {@link #organization} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The organization which is responsible for the services rendered to the patient.)
     */
    public SupportingDocumentation setOrganizationTarget(Organization value) { 
      this.organizationTarget = value;
      return this;
    }

    /**
     * @return {@link #requestIdentifier} (Original request identifer.)
     */
    public Identifier getRequestIdentifier() { 
      return this.requestIdentifier;
    }

    /**
     * @param value {@link #requestIdentifier} (Original request identifer.)
     */
    public SupportingDocumentation setRequestIdentifier(Identifier value) { 
      this.requestIdentifier = value;
      return this;
    }

    /**
     * @return {@link #request} (Original request identifer.)
     */
    public Reference getRequest() { 
      return this.request;
    }

    /**
     * @param value {@link #request} (Original request identifer.)
     */
    public SupportingDocumentation setRequest(Reference value) { 
      this.request = value;
      return this;
    }

    /**
     * @return {@link #request} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Original request identifer.)
     */
    public Resource getRequestTarget() { 
      return this.requestTarget;
    }

    /**
     * @param value {@link #request} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Original request identifer.)
     */
    public SupportingDocumentation setRequestTarget(Resource value) { 
      this.requestTarget = value;
      return this;
    }

    /**
     * @return {@link #responseIdentifier} (Original response identifer.)
     */
    public Identifier getResponseIdentifier() { 
      return this.responseIdentifier;
    }

    /**
     * @param value {@link #responseIdentifier} (Original response identifer.)
     */
    public SupportingDocumentation setResponseIdentifier(Identifier value) { 
      this.responseIdentifier = value;
      return this;
    }

    /**
     * @return {@link #response} (Original response identifer.)
     */
    public Reference getResponse() { 
      return this.response;
    }

    /**
     * @param value {@link #response} (Original response identifer.)
     */
    public SupportingDocumentation setResponse(Reference value) { 
      this.response = value;
      return this;
    }

    /**
     * @return {@link #response} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Original response identifer.)
     */
    public Resource getResponseTarget() { 
      return this.responseTarget;
    }

    /**
     * @param value {@link #response} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Original response identifer.)
     */
    public SupportingDocumentation setResponseTarget(Resource value) { 
      this.responseTarget = value;
      return this;
    }

    /**
     * @return {@link #author} (Person who created the submission.)
     */
    public Reference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (Person who created the submission.)
     */
    public SupportingDocumentation setAuthor(Reference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #author} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Person who created the submission.)
     */
    public Practitioner getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @param value {@link #author} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Person who created the submission.)
     */
    public SupportingDocumentation setAuthorTarget(Practitioner value) { 
      this.authorTarget = value;
      return this;
    }

    /**
     * @return {@link #subject} (The patient who is directly or indirectly the subject of the supporting information.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who is directly or indirectly the subject of the supporting information.)
     */
    public SupportingDocumentation setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who is directly or indirectly the subject of the supporting information.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who is directly or indirectly the subject of the supporting information.)
     */
    public SupportingDocumentation setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #detail} (Supporting Files.)
     */
    public List<SupportingDocumentationDetailComponent> getDetail() { 
      return this.detail;
    }

    /**
     * @return {@link #detail} (Supporting Files.)
     */
    // syntactic sugar
    public SupportingDocumentationDetailComponent addDetail() { //3
      SupportingDocumentationDetailComponent t = new SupportingDocumentationDetailComponent();
      this.detail.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "The Response Business Identifier.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("ruleset", "Coding", "The version of the style of resource contents. This should be mapped to the allowable profiles for this and supporting resources.", 0, java.lang.Integer.MAX_VALUE, ruleset));
        childrenList.add(new Property("originalRuleset", "Coding", "The style (standard) and version of the original material which was converted into this resource.", 0, java.lang.Integer.MAX_VALUE, originalRuleset));
        childrenList.add(new Property("date", "date", "The date when this resource was created.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("target", "Reference(Organization|Practitioner)", "The Insurer, organization or Provider who is target  of the submission.", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("provider", "Reference(Practitioner)", "The practitioner who is responsible for the services rendered to the patient.", 0, java.lang.Integer.MAX_VALUE, provider));
        childrenList.add(new Property("organization", "Reference(Organization)", "The organization which is responsible for the services rendered to the patient.", 0, java.lang.Integer.MAX_VALUE, organization));
        childrenList.add(new Property("requestIdentifier", "Identifier", "Original request identifer.", 0, java.lang.Integer.MAX_VALUE, requestIdentifier));
        childrenList.add(new Property("request", "Reference(Any)", "Original request identifer.", 0, java.lang.Integer.MAX_VALUE, request));
        childrenList.add(new Property("responseIdentifier", "Identifier", "Original response identifer.", 0, java.lang.Integer.MAX_VALUE, responseIdentifier));
        childrenList.add(new Property("response", "Reference(Any)", "Original response identifer.", 0, java.lang.Integer.MAX_VALUE, response));
        childrenList.add(new Property("author", "Reference(Practitioner)", "Person who created the submission.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who is directly or indirectly the subject of the supporting information.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("detail", "", "Supporting Files.", 0, java.lang.Integer.MAX_VALUE, detail));
      }

      public SupportingDocumentation copy() {
        SupportingDocumentation dst = new SupportingDocumentation();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.ruleset = ruleset == null ? null : ruleset.copy();
        dst.originalRuleset = originalRuleset == null ? null : originalRuleset.copy();
        dst.date = date == null ? null : date.copy();
        dst.target = target == null ? null : target.copy();
        dst.provider = provider == null ? null : provider.copy();
        dst.organization = organization == null ? null : organization.copy();
        dst.requestIdentifier = requestIdentifier == null ? null : requestIdentifier.copy();
        dst.request = request == null ? null : request.copy();
        dst.responseIdentifier = responseIdentifier == null ? null : responseIdentifier.copy();
        dst.response = response == null ? null : response.copy();
        dst.author = author == null ? null : author.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.detail = new ArrayList<SupportingDocumentationDetailComponent>();
        for (SupportingDocumentationDetailComponent i : detail)
          dst.detail.add(i.copy());
        return dst;
      }

      protected SupportingDocumentation typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.SupportingDocumentation;
   }


}

