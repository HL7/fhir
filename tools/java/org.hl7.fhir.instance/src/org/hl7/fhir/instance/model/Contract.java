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

import org.hl7.fhir.utilities.Utilities;
/**
 * A formal agreement between parties regarding the conduct of business, exchange of information or other matters.
 */
public class Contract extends DomainResource {

    public static class ContractTermComponent extends BackboneElement {
        /**
         * Unique Id for this particular term.
         */
        protected Identifier identifier;

        /**
         * The type of the term.
         */
        protected CodeableConcept type;

        /**
         * The subttype of the term which is appropriate to the term type.
         */
        protected CodeableConcept subtype;

        /**
         * Who or what the contract term is about.
         */
        protected Reference subject;

        /**
         * The actual object that is the target of the reference (Who or what the contract term is about.)
         */
        protected Resource subjectTarget;

        /**
         * Human readable form of the term of the contract.
         */
        protected StringType text;

        private static final long serialVersionUID = -697165954L;

      public ContractTermComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Unique Id for this particular term.)
         */
        public Identifier getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (Unique Id for this particular term.)
         */
        public ContractTermComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #type} (The type of the term.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of the term.)
         */
        public ContractTermComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #subtype} (The subttype of the term which is appropriate to the term type.)
         */
        public CodeableConcept getSubtype() { 
          return this.subtype;
        }

        /**
         * @param value {@link #subtype} (The subttype of the term which is appropriate to the term type.)
         */
        public ContractTermComponent setSubtype(CodeableConcept value) { 
          this.subtype = value;
          return this;
        }

        /**
         * @return {@link #subject} (Who or what the contract term is about.)
         */
        public Reference getSubject() { 
          return this.subject;
        }

        /**
         * @param value {@link #subject} (Who or what the contract term is about.)
         */
        public ContractTermComponent setSubject(Reference value) { 
          this.subject = value;
          return this;
        }

        /**
         * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who or what the contract term is about.)
         */
        public Resource getSubjectTarget() { 
          return this.subjectTarget;
        }

        /**
         * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who or what the contract term is about.)
         */
        public ContractTermComponent setSubjectTarget(Resource value) { 
          this.subjectTarget = value;
          return this;
        }

        /**
         * @return {@link #text} (Human readable form of the term of the contract.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public StringType getTextElement() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Human readable form of the term of the contract.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
         */
        public ContractTermComponent setTextElement(StringType value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Human readable form of the term of the contract.
         */
        public String getText() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Human readable form of the term of the contract.
         */
        public ContractTermComponent setText(String value) { 
          if (Utilities.noString(value))
            this.text = null;
          else {
            if (this.text == null)
              this.text = new StringType();
            this.text.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Unique Id for this particular term.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("type", "CodeableConcept", "The type of the term.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("subtype", "CodeableConcept", "The subttype of the term which is appropriate to the term type.", 0, java.lang.Integer.MAX_VALUE, subtype));
          childrenList.add(new Property("subject", "Reference(Any)", "Who or what the contract term is about.", 0, java.lang.Integer.MAX_VALUE, subject));
          childrenList.add(new Property("text", "string", "Human readable form of the term of the contract.", 0, java.lang.Integer.MAX_VALUE, text));
        }

      public ContractTermComponent copy() {
        ContractTermComponent dst = new ContractTermComponent();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = type == null ? null : type.copy();
        dst.subtype = subtype == null ? null : subtype.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.text = text == null ? null : text.copy();
        return dst;
      }

  }

    /**
     * Who and/or what this is about.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (Who and/or what this is about.)
     */
    protected Resource subjectTarget;

    /**
     * Type of contract (Privacy-Security, Agreement, Insurance).
     */
    protected CodeableConcept type;

    /**
     * More specific type of contract (Privacy, Disclosure-Authorization, Advanced-Directive, DNR, Authorization-to-Treat).
     */
    protected CodeableConcept subtype;

    /**
     * When this was issued.
     */
    protected DateTimeType issued;

    /**
     * Relevant time/time-period when applicable.
     */
    protected Period applies;

    /**
     * Contract author or responsible party.
     */
    protected List<Reference> author = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Contract author or responsible party.)
     */
    protected List<Resource> authorTarget = new ArrayList<Resource>();


    /**
     * First Party to the contract, may be the party who confers or delegates the rights defined in the contract.
     */
    protected List<Reference> grantor = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (First Party to the contract, may be the party who confers or delegates the rights defined in the contract.)
     */
    protected List<Resource> grantorTarget = new ArrayList<Resource>();


    /**
     * The Second party to the contract, may be the party who accepts obligations or be that to which rights are delegated.
     */
    protected List<Reference> grantee = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (The Second party to the contract, may be the party who accepts obligations or be that to which rights are delegated.)
     */
    protected List<Resource> granteeTarget = new ArrayList<Resource>();


    /**
     * Who witnesses the contract.
     */
    protected List<Reference> witness = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Who witnesses the contract.)
     */
    protected List<Resource> witnessTarget = new ArrayList<Resource>();


    /**
     * Unique Id for this contract.
     */
    protected Identifier identifier;

    /**
     * A contract provision.
     */
    protected List<ContractTermComponent> term = new ArrayList<ContractTermComponent>();

    /**
     * Friendly Human readable form (might be a reference to the UI used to capture the contract).
     */
    protected Attachment friendly;

    /**
     * Legal text in Human readable form.
     */
    protected Attachment legal;

    /**
     * Computable Policy rules (e.g. XACML, DKAL, SecPal).
     */
    protected Attachment rule;

    private static final long serialVersionUID = 893784601L;

    public Contract() {
      super();
    }

    /**
     * @return {@link #subject} (Who and/or what this is about.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who and/or what this is about.)
     */
    public Contract setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who and/or what this is about.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who and/or what this is about.)
     */
    public Contract setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #type} (Type of contract (Privacy-Security, Agreement, Insurance).)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Type of contract (Privacy-Security, Agreement, Insurance).)
     */
    public Contract setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #subtype} (More specific type of contract (Privacy, Disclosure-Authorization, Advanced-Directive, DNR, Authorization-to-Treat).)
     */
    public CodeableConcept getSubtype() { 
      return this.subtype;
    }

    /**
     * @param value {@link #subtype} (More specific type of contract (Privacy, Disclosure-Authorization, Advanced-Directive, DNR, Authorization-to-Treat).)
     */
    public Contract setSubtype(CodeableConcept value) { 
      this.subtype = value;
      return this;
    }

    /**
     * @return {@link #issued} (When this was issued.). This is the underlying object with id, value and extensions. The accessor "getIssued" gives direct access to the value
     */
    public DateTimeType getIssuedElement() { 
      return this.issued;
    }

    /**
     * @param value {@link #issued} (When this was issued.). This is the underlying object with id, value and extensions. The accessor "getIssued" gives direct access to the value
     */
    public Contract setIssuedElement(DateTimeType value) { 
      this.issued = value;
      return this;
    }

    /**
     * @return When this was issued.
     */
    public DateAndTime getIssued() { 
      return this.issued == null ? null : this.issued.getValue();
    }

    /**
     * @param value When this was issued.
     */
    public Contract setIssued(DateAndTime value) { 
      if (value == null)
        this.issued = null;
      else {
        if (this.issued == null)
          this.issued = new DateTimeType();
        this.issued.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #applies} (Relevant time/time-period when applicable.)
     */
    public Period getApplies() { 
      return this.applies;
    }

    /**
     * @param value {@link #applies} (Relevant time/time-period when applicable.)
     */
    public Contract setApplies(Period value) { 
      this.applies = value;
      return this;
    }

    /**
     * @return {@link #author} (Contract author or responsible party.)
     */
    public List<Reference> getAuthor() { 
      return this.author;
    }

    /**
     * @return {@link #author} (Contract author or responsible party.)
     */
    // syntactic sugar
    public Reference addAuthor() { //3
      Reference t = new Reference();
      this.author.add(t);
      return t;
    }

    /**
     * @return {@link #author} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Contract author or responsible party.)
     */
    public List<Resource> getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @return {@link #grantor} (First Party to the contract, may be the party who confers or delegates the rights defined in the contract.)
     */
    public List<Reference> getGrantor() { 
      return this.grantor;
    }

    /**
     * @return {@link #grantor} (First Party to the contract, may be the party who confers or delegates the rights defined in the contract.)
     */
    // syntactic sugar
    public Reference addGrantor() { //3
      Reference t = new Reference();
      this.grantor.add(t);
      return t;
    }

    /**
     * @return {@link #grantor} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. First Party to the contract, may be the party who confers or delegates the rights defined in the contract.)
     */
    public List<Resource> getGrantorTarget() { 
      return this.grantorTarget;
    }

    /**
     * @return {@link #grantee} (The Second party to the contract, may be the party who accepts obligations or be that to which rights are delegated.)
     */
    public List<Reference> getGrantee() { 
      return this.grantee;
    }

    /**
     * @return {@link #grantee} (The Second party to the contract, may be the party who accepts obligations or be that to which rights are delegated.)
     */
    // syntactic sugar
    public Reference addGrantee() { //3
      Reference t = new Reference();
      this.grantee.add(t);
      return t;
    }

    /**
     * @return {@link #grantee} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The Second party to the contract, may be the party who accepts obligations or be that to which rights are delegated.)
     */
    public List<Resource> getGranteeTarget() { 
      return this.granteeTarget;
    }

    /**
     * @return {@link #witness} (Who witnesses the contract.)
     */
    public List<Reference> getWitness() { 
      return this.witness;
    }

    /**
     * @return {@link #witness} (Who witnesses the contract.)
     */
    // syntactic sugar
    public Reference addWitness() { //3
      Reference t = new Reference();
      this.witness.add(t);
      return t;
    }

    /**
     * @return {@link #witness} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Who witnesses the contract.)
     */
    public List<Resource> getWitnessTarget() { 
      return this.witnessTarget;
    }

    /**
     * @return {@link #identifier} (Unique Id for this contract.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Unique Id for this contract.)
     */
    public Contract setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #term} (A contract provision.)
     */
    public List<ContractTermComponent> getTerm() { 
      return this.term;
    }

    /**
     * @return {@link #term} (A contract provision.)
     */
    // syntactic sugar
    public ContractTermComponent addTerm() { //3
      ContractTermComponent t = new ContractTermComponent();
      this.term.add(t);
      return t;
    }

    /**
     * @return {@link #friendly} (Friendly Human readable form (might be a reference to the UI used to capture the contract).)
     */
    public Attachment getFriendly() { 
      return this.friendly;
    }

    /**
     * @param value {@link #friendly} (Friendly Human readable form (might be a reference to the UI used to capture the contract).)
     */
    public Contract setFriendly(Attachment value) { 
      this.friendly = value;
      return this;
    }

    /**
     * @return {@link #legal} (Legal text in Human readable form.)
     */
    public Attachment getLegal() { 
      return this.legal;
    }

    /**
     * @param value {@link #legal} (Legal text in Human readable form.)
     */
    public Contract setLegal(Attachment value) { 
      this.legal = value;
      return this;
    }

    /**
     * @return {@link #rule} (Computable Policy rules (e.g. XACML, DKAL, SecPal).)
     */
    public Attachment getRule() { 
      return this.rule;
    }

    /**
     * @param value {@link #rule} (Computable Policy rules (e.g. XACML, DKAL, SecPal).)
     */
    public Contract setRule(Attachment value) { 
      this.rule = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("subject", "Reference(Patient|Organization)", "Who and/or what this is about.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("type", "CodeableConcept", "Type of contract (Privacy-Security, Agreement, Insurance).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("subtype", "CodeableConcept", "More specific type of contract (Privacy, Disclosure-Authorization, Advanced-Directive, DNR, Authorization-to-Treat).", 0, java.lang.Integer.MAX_VALUE, subtype));
        childrenList.add(new Property("issued", "dateTime", "When this was issued.", 0, java.lang.Integer.MAX_VALUE, issued));
        childrenList.add(new Property("applies", "Period", "Relevant time/time-period when applicable.", 0, java.lang.Integer.MAX_VALUE, applies));
        childrenList.add(new Property("author", "Reference(Practitioner|RelatedPerson|Organization)", "Contract author or responsible party.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("grantor", "Reference(Practitioner|RelatedPerson|Organization)", "First Party to the contract, may be the party who confers or delegates the rights defined in the contract.", 0, java.lang.Integer.MAX_VALUE, grantor));
        childrenList.add(new Property("grantee", "Reference(Practitioner|RelatedPerson|Organization)", "The Second party to the contract, may be the party who accepts obligations or be that to which rights are delegated.", 0, java.lang.Integer.MAX_VALUE, grantee));
        childrenList.add(new Property("witness", "Reference(Practitioner|RelatedPerson|Organization)", "Who witnesses the contract.", 0, java.lang.Integer.MAX_VALUE, witness));
        childrenList.add(new Property("identifier", "Identifier", "Unique Id for this contract.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("term", "", "A contract provision.", 0, java.lang.Integer.MAX_VALUE, term));
        childrenList.add(new Property("friendly", "Attachment", "Friendly Human readable form (might be a reference to the UI used to capture the contract).", 0, java.lang.Integer.MAX_VALUE, friendly));
        childrenList.add(new Property("legal", "Attachment", "Legal text in Human readable form.", 0, java.lang.Integer.MAX_VALUE, legal));
        childrenList.add(new Property("rule", "Attachment", "Computable Policy rules (e.g. XACML, DKAL, SecPal).", 0, java.lang.Integer.MAX_VALUE, rule));
      }

      public Contract copy() {
        Contract dst = new Contract();
        copyValues(dst);
        dst.subject = subject == null ? null : subject.copy();
        dst.type = type == null ? null : type.copy();
        dst.subtype = subtype == null ? null : subtype.copy();
        dst.issued = issued == null ? null : issued.copy();
        dst.applies = applies == null ? null : applies.copy();
        dst.author = new ArrayList<Reference>();
        for (Reference i : author)
          dst.author.add(i.copy());
        dst.grantor = new ArrayList<Reference>();
        for (Reference i : grantor)
          dst.grantor.add(i.copy());
        dst.grantee = new ArrayList<Reference>();
        for (Reference i : grantee)
          dst.grantee.add(i.copy());
        dst.witness = new ArrayList<Reference>();
        for (Reference i : witness)
          dst.witness.add(i.copy());
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.term = new ArrayList<ContractTermComponent>();
        for (ContractTermComponent i : term)
          dst.term.add(i.copy());
        dst.friendly = friendly == null ? null : friendly.copy();
        dst.legal = legal == null ? null : legal.copy();
        dst.rule = rule == null ? null : rule.copy();
        return dst;
      }

      protected Contract typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Contract;
   }


}

