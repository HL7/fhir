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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import java.math.*;
import org.hl7.fhir.utilities.Utilities;
/**
 * A provider issued list of services and products provided, or to be provided, to a patient which is provided to an insurer for payment recovery.
 */
public class OralHealthClaim extends DomainResource {

    public enum UseLink {
        COMPLETE, // The treatment is complete and this represents a Claim for the services.
        PROPOSED, // The treatment is proposed and this represents a Pre-authorization for the services.
        EXPLORATORY, // The treatment is proposed and this represents a Pre-determination for the services.
        OTHER, // A locally defined or otherwise resolved status.
        NULL; // added to help the parsers
        public static UseLink fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("complete".equals(codeString))
          return COMPLETE;
        if ("proposed".equals(codeString))
          return PROPOSED;
        if ("exploratory".equals(codeString))
          return EXPLORATORY;
        if ("other".equals(codeString))
          return OTHER;
        throw new Exception("Unknown UseLink code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case COMPLETE: return "complete";
            case PROPOSED: return "proposed";
            case EXPLORATORY: return "exploratory";
            case OTHER: return "other";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case COMPLETE: return "The treatment is complete and this represents a Claim for the services.";
            case PROPOSED: return "The treatment is proposed and this represents a Pre-authorization for the services.";
            case EXPLORATORY: return "The treatment is proposed and this represents a Pre-determination for the services.";
            case OTHER: return "A locally defined or otherwise resolved status.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case COMPLETE: return "complete";
            case PROPOSED: return "proposed";
            case EXPLORATORY: return "exploratory";
            case OTHER: return "other";
            default: return "?";
          }
        }
    }

  public static class UseLinkEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("complete".equals(codeString))
          return UseLink.COMPLETE;
        if ("proposed".equals(codeString))
          return UseLink.PROPOSED;
        if ("exploratory".equals(codeString))
          return UseLink.EXPLORATORY;
        if ("other".equals(codeString))
          return UseLink.OTHER;
        throw new Exception("Unknown UseLink code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == UseLink.COMPLETE)
        return "complete";
      if (code == UseLink.PROPOSED)
        return "proposed";
      if (code == UseLink.EXPLORATORY)
        return "exploratory";
      if (code == UseLink.OTHER)
        return "other";
      return "?";
      }
    }

    public static class PayeeComponent extends BackboneElement {
        /**
         * Party to be reimbursed: Subscriber, provider, other.
         */
        protected Coding type;

        /**
         * The provider who is to be reimbursed for the claim (the party to whom any benefit is assigned).
         */
        protected Reference provider;

        /**
         * The actual object that is the target of the reference (The provider who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        protected Practitioner providerTarget;

        /**
         * The organization who is to be reimbursed for the claim (the party to whom any benefit is assigned).
         */
        protected Reference organization;

        /**
         * The actual object that is the target of the reference (The organization who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        protected Organization organizationTarget;

        /**
         * The person other than the subscriber who is to be reimbursed for the claim (the party to whom any benefit is assigned).
         */
        protected Reference person;

        /**
         * The actual object that is the target of the reference (The person other than the subscriber who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        protected Patient personTarget;

        private static final long serialVersionUID = -503108488L;

      public PayeeComponent() {
        super();
      }

        /**
         * @return {@link #type} (Party to be reimbursed: Subscriber, provider, other.)
         */
        public Coding getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Party to be reimbursed: Subscriber, provider, other.)
         */
        public PayeeComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #provider} (The provider who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public Reference getProvider() { 
          return this.provider;
        }

        /**
         * @param value {@link #provider} (The provider who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public PayeeComponent setProvider(Reference value) { 
          this.provider = value;
          return this;
        }

        /**
         * @return {@link #provider} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The provider who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public Practitioner getProviderTarget() { 
          return this.providerTarget;
        }

        /**
         * @param value {@link #provider} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The provider who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public PayeeComponent setProviderTarget(Practitioner value) { 
          this.providerTarget = value;
          return this;
        }

        /**
         * @return {@link #organization} (The organization who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public Reference getOrganization() { 
          return this.organization;
        }

        /**
         * @param value {@link #organization} (The organization who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public PayeeComponent setOrganization(Reference value) { 
          this.organization = value;
          return this;
        }

        /**
         * @return {@link #organization} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The organization who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public Organization getOrganizationTarget() { 
          return this.organizationTarget;
        }

        /**
         * @param value {@link #organization} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The organization who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public PayeeComponent setOrganizationTarget(Organization value) { 
          this.organizationTarget = value;
          return this;
        }

        /**
         * @return {@link #person} (The person other than the subscriber who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public Reference getPerson() { 
          return this.person;
        }

        /**
         * @param value {@link #person} (The person other than the subscriber who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public PayeeComponent setPerson(Reference value) { 
          this.person = value;
          return this;
        }

        /**
         * @return {@link #person} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person other than the subscriber who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public Patient getPersonTarget() { 
          return this.personTarget;
        }

        /**
         * @param value {@link #person} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person other than the subscriber who is to be reimbursed for the claim (the party to whom any benefit is assigned).)
         */
        public PayeeComponent setPersonTarget(Patient value) { 
          this.personTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "Coding", "Party to be reimbursed: Subscriber, provider, other.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("provider", "Reference(Practitioner)", "The provider who is to be reimbursed for the claim (the party to whom any benefit is assigned).", 0, java.lang.Integer.MAX_VALUE, provider));
          childrenList.add(new Property("organization", "Reference(Organization)", "The organization who is to be reimbursed for the claim (the party to whom any benefit is assigned).", 0, java.lang.Integer.MAX_VALUE, organization));
          childrenList.add(new Property("person", "Reference(Patient)", "The person other than the subscriber who is to be reimbursed for the claim (the party to whom any benefit is assigned).", 0, java.lang.Integer.MAX_VALUE, person));
        }

      public PayeeComponent copy() {
        PayeeComponent dst = new PayeeComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.provider = provider == null ? null : provider.copy();
        dst.organization = organization == null ? null : organization.copy();
        dst.person = person == null ? null : person.copy();
        return dst;
      }

  }

    public static class DiagnosisComponent extends BackboneElement {
        /**
         * Sequence of diagnosis.
         */
        protected IntegerType sequence;

        /**
         * The diagnosis.
         */
        protected Coding diagnosis;

        private static final long serialVersionUID = -935927954L;

      public DiagnosisComponent() {
        super();
      }

      public DiagnosisComponent(IntegerType sequence, Coding diagnosis) {
        super();
        this.sequence = sequence;
        this.diagnosis = diagnosis;
      }

        /**
         * @return {@link #sequence} (Sequence of diagnosis.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public IntegerType getSequenceElement() { 
          return this.sequence;
        }

        /**
         * @param value {@link #sequence} (Sequence of diagnosis.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public DiagnosisComponent setSequenceElement(IntegerType value) { 
          this.sequence = value;
          return this;
        }

        /**
         * @return Sequence of diagnosis.
         */
        public int getSequence() { 
          return this.sequence == null ? null : this.sequence.getValue();
        }

        /**
         * @param value Sequence of diagnosis.
         */
        public DiagnosisComponent setSequence(int value) { 
            if (this.sequence == null)
              this.sequence = new IntegerType();
            this.sequence.setValue(value);
          return this;
        }

        /**
         * @return {@link #diagnosis} (The diagnosis.)
         */
        public Coding getDiagnosis() { 
          return this.diagnosis;
        }

        /**
         * @param value {@link #diagnosis} (The diagnosis.)
         */
        public DiagnosisComponent setDiagnosis(Coding value) { 
          this.diagnosis = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("sequence", "integer", "Sequence of diagnosis.", 0, java.lang.Integer.MAX_VALUE, sequence));
          childrenList.add(new Property("diagnosis", "Coding", "The diagnosis.", 0, java.lang.Integer.MAX_VALUE, diagnosis));
        }

      public DiagnosisComponent copy() {
        DiagnosisComponent dst = new DiagnosisComponent();
        copyValues(dst);
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.diagnosis = diagnosis == null ? null : diagnosis.copy();
        return dst;
      }

  }

    public static class CoverageComponent extends BackboneElement {
        /**
         * A service line item.
         */
        protected IntegerType sequence;

        /**
         * The instance number of the Coverage which is the focus for adjudication, that is the Coverage to which the claim is to be adjudicated against.
         */
        protected BooleanType focal;

        /**
         * Reference to the program or plan identification, underwriter or payor.
         */
        protected Reference coverage;

        /**
         * The actual object that is the target of the reference (Reference to the program or plan identification, underwriter or payor.)
         */
        protected Coverage coverageTarget;

        /**
         * The contract number of a business agrement which describes the terms and conditions.
         */
        protected StringType businessArrangement;

        /**
         * The relationship of the patient to the subscriber.
         */
        protected Coding relationship;

        /**
         * A list of references from the Insurer to which these services pertain.
         */
        protected List<StringType> preauthref = new ArrayList<StringType>();

        /**
         * The Coverages adjudication details.
         */
        protected Reference claimResponse;

        /**
         * The actual object that is the target of the reference (The Coverages adjudication details.)
         */
        protected ClaimResponse claimResponseTarget;

        /**
         * The style (standard) and version of the original material which was converted into this resource.
         */
        protected Coding originalRuleset;

        private static final long serialVersionUID = 1207065968L;

      public CoverageComponent() {
        super();
      }

      public CoverageComponent(IntegerType sequence, BooleanType focal, Reference coverage, Coding relationship) {
        super();
        this.sequence = sequence;
        this.focal = focal;
        this.coverage = coverage;
        this.relationship = relationship;
      }

        /**
         * @return {@link #sequence} (A service line item.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public IntegerType getSequenceElement() { 
          return this.sequence;
        }

        /**
         * @param value {@link #sequence} (A service line item.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public CoverageComponent setSequenceElement(IntegerType value) { 
          this.sequence = value;
          return this;
        }

        /**
         * @return A service line item.
         */
        public int getSequence() { 
          return this.sequence == null ? null : this.sequence.getValue();
        }

        /**
         * @param value A service line item.
         */
        public CoverageComponent setSequence(int value) { 
            if (this.sequence == null)
              this.sequence = new IntegerType();
            this.sequence.setValue(value);
          return this;
        }

        /**
         * @return {@link #focal} (The instance number of the Coverage which is the focus for adjudication, that is the Coverage to which the claim is to be adjudicated against.). This is the underlying object with id, value and extensions. The accessor "getFocal" gives direct access to the value
         */
        public BooleanType getFocalElement() { 
          return this.focal;
        }

        /**
         * @param value {@link #focal} (The instance number of the Coverage which is the focus for adjudication, that is the Coverage to which the claim is to be adjudicated against.). This is the underlying object with id, value and extensions. The accessor "getFocal" gives direct access to the value
         */
        public CoverageComponent setFocalElement(BooleanType value) { 
          this.focal = value;
          return this;
        }

        /**
         * @return The instance number of the Coverage which is the focus for adjudication, that is the Coverage to which the claim is to be adjudicated against.
         */
        public boolean getFocal() { 
          return this.focal == null ? false : this.focal.getValue();
        }

        /**
         * @param value The instance number of the Coverage which is the focus for adjudication, that is the Coverage to which the claim is to be adjudicated against.
         */
        public CoverageComponent setFocal(boolean value) { 
            if (this.focal == null)
              this.focal = new BooleanType();
            this.focal.setValue(value);
          return this;
        }

        /**
         * @return {@link #coverage} (Reference to the program or plan identification, underwriter or payor.)
         */
        public Reference getCoverage() { 
          return this.coverage;
        }

        /**
         * @param value {@link #coverage} (Reference to the program or plan identification, underwriter or payor.)
         */
        public CoverageComponent setCoverage(Reference value) { 
          this.coverage = value;
          return this;
        }

        /**
         * @return {@link #coverage} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Reference to the program or plan identification, underwriter or payor.)
         */
        public Coverage getCoverageTarget() { 
          return this.coverageTarget;
        }

        /**
         * @param value {@link #coverage} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Reference to the program or plan identification, underwriter or payor.)
         */
        public CoverageComponent setCoverageTarget(Coverage value) { 
          this.coverageTarget = value;
          return this;
        }

        /**
         * @return {@link #businessArrangement} (The contract number of a business agrement which describes the terms and conditions.). This is the underlying object with id, value and extensions. The accessor "getBusinessArrangement" gives direct access to the value
         */
        public StringType getBusinessArrangementElement() { 
          return this.businessArrangement;
        }

        /**
         * @param value {@link #businessArrangement} (The contract number of a business agrement which describes the terms and conditions.). This is the underlying object with id, value and extensions. The accessor "getBusinessArrangement" gives direct access to the value
         */
        public CoverageComponent setBusinessArrangementElement(StringType value) { 
          this.businessArrangement = value;
          return this;
        }

        /**
         * @return The contract number of a business agrement which describes the terms and conditions.
         */
        public String getBusinessArrangement() { 
          return this.businessArrangement == null ? null : this.businessArrangement.getValue();
        }

        /**
         * @param value The contract number of a business agrement which describes the terms and conditions.
         */
        public CoverageComponent setBusinessArrangement(String value) { 
          if (Utilities.noString(value))
            this.businessArrangement = null;
          else {
            if (this.businessArrangement == null)
              this.businessArrangement = new StringType();
            this.businessArrangement.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #relationship} (The relationship of the patient to the subscriber.)
         */
        public Coding getRelationship() { 
          return this.relationship;
        }

        /**
         * @param value {@link #relationship} (The relationship of the patient to the subscriber.)
         */
        public CoverageComponent setRelationship(Coding value) { 
          this.relationship = value;
          return this;
        }

        /**
         * @return {@link #preauthref} (A list of references from the Insurer to which these services pertain.)
         */
        public List<StringType> getPreauthref() { 
          return this.preauthref;
        }

        /**
         * @return {@link #preauthref} (A list of references from the Insurer to which these services pertain.)
         */
    // syntactic sugar
        public StringType addPreauthrefElement() {//2 
          StringType t = new StringType();
          this.preauthref.add(t);
          return t;
        }

        /**
         * @param value {@link #preauthref} (A list of references from the Insurer to which these services pertain.)
         */
        public CoverageComponent addPreauthref(String value) { //1
          StringType t = new StringType();
          t.setValue(value);
          this.preauthref.add(t);
          return this;
        }

        /**
         * @param value {@link #preauthref} (A list of references from the Insurer to which these services pertain.)
         */
        public boolean hasPreauthref(String value) { 
          for (StringType v : this.preauthref)
            if (v.equals(value)) // string
              return true;
          return false;
        }

        /**
         * @return {@link #claimResponse} (The Coverages adjudication details.)
         */
        public Reference getClaimResponse() { 
          return this.claimResponse;
        }

        /**
         * @param value {@link #claimResponse} (The Coverages adjudication details.)
         */
        public CoverageComponent setClaimResponse(Reference value) { 
          this.claimResponse = value;
          return this;
        }

        /**
         * @return {@link #claimResponse} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The Coverages adjudication details.)
         */
        public ClaimResponse getClaimResponseTarget() { 
          return this.claimResponseTarget;
        }

        /**
         * @param value {@link #claimResponse} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The Coverages adjudication details.)
         */
        public CoverageComponent setClaimResponseTarget(ClaimResponse value) { 
          this.claimResponseTarget = value;
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
        public CoverageComponent setOriginalRuleset(Coding value) { 
          this.originalRuleset = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("sequence", "integer", "A service line item.", 0, java.lang.Integer.MAX_VALUE, sequence));
          childrenList.add(new Property("focal", "boolean", "The instance number of the Coverage which is the focus for adjudication, that is the Coverage to which the claim is to be adjudicated against.", 0, java.lang.Integer.MAX_VALUE, focal));
          childrenList.add(new Property("coverage", "Reference(Coverage)", "Reference to the program or plan identification, underwriter or payor.", 0, java.lang.Integer.MAX_VALUE, coverage));
          childrenList.add(new Property("businessArrangement", "string", "The contract number of a business agrement which describes the terms and conditions.", 0, java.lang.Integer.MAX_VALUE, businessArrangement));
          childrenList.add(new Property("relationship", "Coding", "The relationship of the patient to the subscriber.", 0, java.lang.Integer.MAX_VALUE, relationship));
          childrenList.add(new Property("preauthref", "string", "A list of references from the Insurer to which these services pertain.", 0, java.lang.Integer.MAX_VALUE, preauthref));
          childrenList.add(new Property("claimResponse", "Reference(ClaimResponse)", "The Coverages adjudication details.", 0, java.lang.Integer.MAX_VALUE, claimResponse));
          childrenList.add(new Property("originalRuleset", "Coding", "The style (standard) and version of the original material which was converted into this resource.", 0, java.lang.Integer.MAX_VALUE, originalRuleset));
        }

      public CoverageComponent copy() {
        CoverageComponent dst = new CoverageComponent();
        copyValues(dst);
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.focal = focal == null ? null : focal.copy();
        dst.coverage = coverage == null ? null : coverage.copy();
        dst.businessArrangement = businessArrangement == null ? null : businessArrangement.copy();
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.preauthref = new ArrayList<StringType>();
        for (StringType i : preauthref)
          dst.preauthref.add(i.copy());
        dst.claimResponse = claimResponse == null ? null : claimResponse.copy();
        dst.originalRuleset = originalRuleset == null ? null : originalRuleset.copy();
        return dst;
      }

  }

    public static class MissingTeethComponent extends BackboneElement {
        /**
         * The code identifying which tooth is missing.
         */
        protected Coding tooth;

        /**
         * Missing reason may be: E-extraction, O-other.
         */
        protected Coding reason;

        /**
         * The date of the extraction either known from records or patient reported estimate.
         */
        protected DateType extractiondate;

        private static final long serialVersionUID = -1311739967L;

      public MissingTeethComponent() {
        super();
      }

      public MissingTeethComponent(Coding tooth) {
        super();
        this.tooth = tooth;
      }

        /**
         * @return {@link #tooth} (The code identifying which tooth is missing.)
         */
        public Coding getTooth() { 
          return this.tooth;
        }

        /**
         * @param value {@link #tooth} (The code identifying which tooth is missing.)
         */
        public MissingTeethComponent setTooth(Coding value) { 
          this.tooth = value;
          return this;
        }

        /**
         * @return {@link #reason} (Missing reason may be: E-extraction, O-other.)
         */
        public Coding getReason() { 
          return this.reason;
        }

        /**
         * @param value {@link #reason} (Missing reason may be: E-extraction, O-other.)
         */
        public MissingTeethComponent setReason(Coding value) { 
          this.reason = value;
          return this;
        }

        /**
         * @return {@link #extractiondate} (The date of the extraction either known from records or patient reported estimate.). This is the underlying object with id, value and extensions. The accessor "getExtractiondate" gives direct access to the value
         */
        public DateType getExtractiondateElement() { 
          return this.extractiondate;
        }

        /**
         * @param value {@link #extractiondate} (The date of the extraction either known from records or patient reported estimate.). This is the underlying object with id, value and extensions. The accessor "getExtractiondate" gives direct access to the value
         */
        public MissingTeethComponent setExtractiondateElement(DateType value) { 
          this.extractiondate = value;
          return this;
        }

        /**
         * @return The date of the extraction either known from records or patient reported estimate.
         */
        public DateAndTime getExtractiondate() { 
          return this.extractiondate == null ? null : this.extractiondate.getValue();
        }

        /**
         * @param value The date of the extraction either known from records or patient reported estimate.
         */
        public MissingTeethComponent setExtractiondate(DateAndTime value) { 
          if (value == null)
            this.extractiondate = null;
          else {
            if (this.extractiondate == null)
              this.extractiondate = new DateType();
            this.extractiondate.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("tooth", "Coding", "The code identifying which tooth is missing.", 0, java.lang.Integer.MAX_VALUE, tooth));
          childrenList.add(new Property("reason", "Coding", "Missing reason may be: E-extraction, O-other.", 0, java.lang.Integer.MAX_VALUE, reason));
          childrenList.add(new Property("extractiondate", "date", "The date of the extraction either known from records or patient reported estimate.", 0, java.lang.Integer.MAX_VALUE, extractiondate));
        }

      public MissingTeethComponent copy() {
        MissingTeethComponent dst = new MissingTeethComponent();
        copyValues(dst);
        dst.tooth = tooth == null ? null : tooth.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.extractiondate = extractiondate == null ? null : extractiondate.copy();
        return dst;
      }

  }

    public static class OrthodonticPlanComponent extends BackboneElement {
        /**
         * The intended start date for service.
         */
        protected DateType start;

        /**
         * The estimated first examination fee.
         */
        protected Money examFee;

        /**
         * The estimated diagnostic fee.
         */
        protected Money diagnosticFee;

        /**
         * The estimated initial payment.
         */
        protected Money initialPayment;

        /**
         * The estimated treatment duration in months.
         */
        protected IntegerType durationMonths;

        /**
         * The anticipated number of payments.
         */
        protected IntegerType paymentCount;

        /**
         * The anticipated payment amount.
         */
        protected Money periodicPayment;

        private static final long serialVersionUID = 1892827159L;

      public OrthodonticPlanComponent() {
        super();
      }

        /**
         * @return {@link #start} (The intended start date for service.). This is the underlying object with id, value and extensions. The accessor "getStart" gives direct access to the value
         */
        public DateType getStartElement() { 
          return this.start;
        }

        /**
         * @param value {@link #start} (The intended start date for service.). This is the underlying object with id, value and extensions. The accessor "getStart" gives direct access to the value
         */
        public OrthodonticPlanComponent setStartElement(DateType value) { 
          this.start = value;
          return this;
        }

        /**
         * @return The intended start date for service.
         */
        public DateAndTime getStart() { 
          return this.start == null ? null : this.start.getValue();
        }

        /**
         * @param value The intended start date for service.
         */
        public OrthodonticPlanComponent setStart(DateAndTime value) { 
          if (value == null)
            this.start = null;
          else {
            if (this.start == null)
              this.start = new DateType();
            this.start.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #examFee} (The estimated first examination fee.)
         */
        public Money getExamFee() { 
          return this.examFee;
        }

        /**
         * @param value {@link #examFee} (The estimated first examination fee.)
         */
        public OrthodonticPlanComponent setExamFee(Money value) { 
          this.examFee = value;
          return this;
        }

        /**
         * @return {@link #diagnosticFee} (The estimated diagnostic fee.)
         */
        public Money getDiagnosticFee() { 
          return this.diagnosticFee;
        }

        /**
         * @param value {@link #diagnosticFee} (The estimated diagnostic fee.)
         */
        public OrthodonticPlanComponent setDiagnosticFee(Money value) { 
          this.diagnosticFee = value;
          return this;
        }

        /**
         * @return {@link #initialPayment} (The estimated initial payment.)
         */
        public Money getInitialPayment() { 
          return this.initialPayment;
        }

        /**
         * @param value {@link #initialPayment} (The estimated initial payment.)
         */
        public OrthodonticPlanComponent setInitialPayment(Money value) { 
          this.initialPayment = value;
          return this;
        }

        /**
         * @return {@link #durationMonths} (The estimated treatment duration in months.). This is the underlying object with id, value and extensions. The accessor "getDurationMonths" gives direct access to the value
         */
        public IntegerType getDurationMonthsElement() { 
          return this.durationMonths;
        }

        /**
         * @param value {@link #durationMonths} (The estimated treatment duration in months.). This is the underlying object with id, value and extensions. The accessor "getDurationMonths" gives direct access to the value
         */
        public OrthodonticPlanComponent setDurationMonthsElement(IntegerType value) { 
          this.durationMonths = value;
          return this;
        }

        /**
         * @return The estimated treatment duration in months.
         */
        public int getDurationMonths() { 
          return this.durationMonths == null ? null : this.durationMonths.getValue();
        }

        /**
         * @param value The estimated treatment duration in months.
         */
        public OrthodonticPlanComponent setDurationMonths(int value) { 
          if (value == -1)
            this.durationMonths = null;
          else {
            if (this.durationMonths == null)
              this.durationMonths = new IntegerType();
            this.durationMonths.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #paymentCount} (The anticipated number of payments.). This is the underlying object with id, value and extensions. The accessor "getPaymentCount" gives direct access to the value
         */
        public IntegerType getPaymentCountElement() { 
          return this.paymentCount;
        }

        /**
         * @param value {@link #paymentCount} (The anticipated number of payments.). This is the underlying object with id, value and extensions. The accessor "getPaymentCount" gives direct access to the value
         */
        public OrthodonticPlanComponent setPaymentCountElement(IntegerType value) { 
          this.paymentCount = value;
          return this;
        }

        /**
         * @return The anticipated number of payments.
         */
        public int getPaymentCount() { 
          return this.paymentCount == null ? null : this.paymentCount.getValue();
        }

        /**
         * @param value The anticipated number of payments.
         */
        public OrthodonticPlanComponent setPaymentCount(int value) { 
          if (value == -1)
            this.paymentCount = null;
          else {
            if (this.paymentCount == null)
              this.paymentCount = new IntegerType();
            this.paymentCount.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #periodicPayment} (The anticipated payment amount.)
         */
        public Money getPeriodicPayment() { 
          return this.periodicPayment;
        }

        /**
         * @param value {@link #periodicPayment} (The anticipated payment amount.)
         */
        public OrthodonticPlanComponent setPeriodicPayment(Money value) { 
          this.periodicPayment = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("start", "date", "The intended start date for service.", 0, java.lang.Integer.MAX_VALUE, start));
          childrenList.add(new Property("examFee", "Money", "The estimated first examination fee.", 0, java.lang.Integer.MAX_VALUE, examFee));
          childrenList.add(new Property("diagnosticFee", "Money", "The estimated diagnostic fee.", 0, java.lang.Integer.MAX_VALUE, diagnosticFee));
          childrenList.add(new Property("initialPayment", "Money", "The estimated initial payment.", 0, java.lang.Integer.MAX_VALUE, initialPayment));
          childrenList.add(new Property("durationMonths", "integer", "The estimated treatment duration in months.", 0, java.lang.Integer.MAX_VALUE, durationMonths));
          childrenList.add(new Property("paymentCount", "integer", "The anticipated number of payments.", 0, java.lang.Integer.MAX_VALUE, paymentCount));
          childrenList.add(new Property("periodicPayment", "Money", "The anticipated payment amount.", 0, java.lang.Integer.MAX_VALUE, periodicPayment));
        }

      public OrthodonticPlanComponent copy() {
        OrthodonticPlanComponent dst = new OrthodonticPlanComponent();
        copyValues(dst);
        dst.start = start == null ? null : start.copy();
        dst.examFee = examFee == null ? null : examFee.copy();
        dst.diagnosticFee = diagnosticFee == null ? null : diagnosticFee.copy();
        dst.initialPayment = initialPayment == null ? null : initialPayment.copy();
        dst.durationMonths = durationMonths == null ? null : durationMonths.copy();
        dst.paymentCount = paymentCount == null ? null : paymentCount.copy();
        dst.periodicPayment = periodicPayment == null ? null : periodicPayment.copy();
        return dst;
      }

  }

    public static class ItemsComponent extends BackboneElement {
        /**
         * A service line number.
         */
        protected IntegerType sequence;

        /**
         * The type of product or service.
         */
        protected Coding type;

        /**
         * The practitioner who is responsible for the services rendered to the patient.
         */
        protected Reference provider;

        /**
         * The actual object that is the target of the reference (The practitioner who is responsible for the services rendered to the patient.)
         */
        protected Practitioner providerTarget;

        /**
         * If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.
         */
        protected Coding service;

        /**
         * The date when the enclosed suite of services were performed or completed.
         */
        protected DateType serviceDate;

        /**
         * The number of repetitions of a service or product.
         */
        protected Quantity quantity;

        /**
         * If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.
         */
        protected Money unitPrice;

        /**
         * A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        protected DecimalType factor;

        /**
         * An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        protected DecimalType points;

        /**
         * The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.
         */
        protected Money net;

        /**
         * List of Unique Device Identifiers associated with this line item.
         */
        protected Coding udi;

        /**
         * Physical service site on the patient (limb, tooth, etc).
         */
        protected Coding bodySite;

        /**
         * A region or surface of the site, eg. limb region or tooth surface(s).
         */
        protected List<Coding> subsite = new ArrayList<Coding>();

        /**
         * Item typification or modifiers codes, eg for Oral whether the treatment is cosmetic or associated with TMJ, or an appliance was lost or stolen.
         */
        protected List<Coding> modifier = new ArrayList<Coding>();

        /**
         * Second tier of goods and services.
         */
        protected List<DetailComponent> detail = new ArrayList<DetailComponent>();

        /**
         * The materials and placement date of prior fixed prosthesis.
         */
        protected ProsthesisComponent prosthesis;

        private static final long serialVersionUID = 1683408733L;

      public ItemsComponent() {
        super();
      }

      public ItemsComponent(IntegerType sequence, Coding type, Coding service) {
        super();
        this.sequence = sequence;
        this.type = type;
        this.service = service;
      }

        /**
         * @return {@link #sequence} (A service line number.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public IntegerType getSequenceElement() { 
          return this.sequence;
        }

        /**
         * @param value {@link #sequence} (A service line number.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public ItemsComponent setSequenceElement(IntegerType value) { 
          this.sequence = value;
          return this;
        }

        /**
         * @return A service line number.
         */
        public int getSequence() { 
          return this.sequence == null ? null : this.sequence.getValue();
        }

        /**
         * @param value A service line number.
         */
        public ItemsComponent setSequence(int value) { 
            if (this.sequence == null)
              this.sequence = new IntegerType();
            this.sequence.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (The type of product or service.)
         */
        public Coding getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of product or service.)
         */
        public ItemsComponent setType(Coding value) { 
          this.type = value;
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
        public ItemsComponent setProvider(Reference value) { 
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
        public ItemsComponent setProviderTarget(Practitioner value) { 
          this.providerTarget = value;
          return this;
        }

        /**
         * @return {@link #service} (If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.)
         */
        public Coding getService() { 
          return this.service;
        }

        /**
         * @param value {@link #service} (If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.)
         */
        public ItemsComponent setService(Coding value) { 
          this.service = value;
          return this;
        }

        /**
         * @return {@link #serviceDate} (The date when the enclosed suite of services were performed or completed.). This is the underlying object with id, value and extensions. The accessor "getServiceDate" gives direct access to the value
         */
        public DateType getServiceDateElement() { 
          return this.serviceDate;
        }

        /**
         * @param value {@link #serviceDate} (The date when the enclosed suite of services were performed or completed.). This is the underlying object with id, value and extensions. The accessor "getServiceDate" gives direct access to the value
         */
        public ItemsComponent setServiceDateElement(DateType value) { 
          this.serviceDate = value;
          return this;
        }

        /**
         * @return The date when the enclosed suite of services were performed or completed.
         */
        public DateAndTime getServiceDate() { 
          return this.serviceDate == null ? null : this.serviceDate.getValue();
        }

        /**
         * @param value The date when the enclosed suite of services were performed or completed.
         */
        public ItemsComponent setServiceDate(DateAndTime value) { 
          if (value == null)
            this.serviceDate = null;
          else {
            if (this.serviceDate == null)
              this.serviceDate = new DateType();
            this.serviceDate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #quantity} (The number of repetitions of a service or product.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The number of repetitions of a service or product.)
         */
        public ItemsComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #unitPrice} (If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.)
         */
        public Money getUnitPrice() { 
          return this.unitPrice;
        }

        /**
         * @param value {@link #unitPrice} (If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.)
         */
        public ItemsComponent setUnitPrice(Money value) { 
          this.unitPrice = value;
          return this;
        }

        /**
         * @return {@link #factor} (A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.). This is the underlying object with id, value and extensions. The accessor "getFactor" gives direct access to the value
         */
        public DecimalType getFactorElement() { 
          return this.factor;
        }

        /**
         * @param value {@link #factor} (A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.). This is the underlying object with id, value and extensions. The accessor "getFactor" gives direct access to the value
         */
        public ItemsComponent setFactorElement(DecimalType value) { 
          this.factor = value;
          return this;
        }

        /**
         * @return A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        public BigDecimal getFactor() { 
          return this.factor == null ? null : this.factor.getValue();
        }

        /**
         * @param value A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        public ItemsComponent setFactor(BigDecimal value) { 
          if (value == null)
            this.factor = null;
          else {
            if (this.factor == null)
              this.factor = new DecimalType();
            this.factor.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #points} (An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.). This is the underlying object with id, value and extensions. The accessor "getPoints" gives direct access to the value
         */
        public DecimalType getPointsElement() { 
          return this.points;
        }

        /**
         * @param value {@link #points} (An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.). This is the underlying object with id, value and extensions. The accessor "getPoints" gives direct access to the value
         */
        public ItemsComponent setPointsElement(DecimalType value) { 
          this.points = value;
          return this;
        }

        /**
         * @return An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        public BigDecimal getPoints() { 
          return this.points == null ? null : this.points.getValue();
        }

        /**
         * @param value An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        public ItemsComponent setPoints(BigDecimal value) { 
          if (value == null)
            this.points = null;
          else {
            if (this.points == null)
              this.points = new DecimalType();
            this.points.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #net} (The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.)
         */
        public Money getNet() { 
          return this.net;
        }

        /**
         * @param value {@link #net} (The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.)
         */
        public ItemsComponent setNet(Money value) { 
          this.net = value;
          return this;
        }

        /**
         * @return {@link #udi} (List of Unique Device Identifiers associated with this line item.)
         */
        public Coding getUdi() { 
          return this.udi;
        }

        /**
         * @param value {@link #udi} (List of Unique Device Identifiers associated with this line item.)
         */
        public ItemsComponent setUdi(Coding value) { 
          this.udi = value;
          return this;
        }

        /**
         * @return {@link #bodySite} (Physical service site on the patient (limb, tooth, etc).)
         */
        public Coding getBodySite() { 
          return this.bodySite;
        }

        /**
         * @param value {@link #bodySite} (Physical service site on the patient (limb, tooth, etc).)
         */
        public ItemsComponent setBodySite(Coding value) { 
          this.bodySite = value;
          return this;
        }

        /**
         * @return {@link #subsite} (A region or surface of the site, eg. limb region or tooth surface(s).)
         */
        public List<Coding> getSubsite() { 
          return this.subsite;
        }

        /**
         * @return {@link #subsite} (A region or surface of the site, eg. limb region or tooth surface(s).)
         */
    // syntactic sugar
        public Coding addSubsite() { //3
          Coding t = new Coding();
          this.subsite.add(t);
          return t;
        }

        /**
         * @return {@link #modifier} (Item typification or modifiers codes, eg for Oral whether the treatment is cosmetic or associated with TMJ, or an appliance was lost or stolen.)
         */
        public List<Coding> getModifier() { 
          return this.modifier;
        }

        /**
         * @return {@link #modifier} (Item typification or modifiers codes, eg for Oral whether the treatment is cosmetic or associated with TMJ, or an appliance was lost or stolen.)
         */
    // syntactic sugar
        public Coding addModifier() { //3
          Coding t = new Coding();
          this.modifier.add(t);
          return t;
        }

        /**
         * @return {@link #detail} (Second tier of goods and services.)
         */
        public List<DetailComponent> getDetail() { 
          return this.detail;
        }

        /**
         * @return {@link #detail} (Second tier of goods and services.)
         */
    // syntactic sugar
        public DetailComponent addDetail() { //3
          DetailComponent t = new DetailComponent();
          this.detail.add(t);
          return t;
        }

        /**
         * @return {@link #prosthesis} (The materials and placement date of prior fixed prosthesis.)
         */
        public ProsthesisComponent getProsthesis() { 
          return this.prosthesis;
        }

        /**
         * @param value {@link #prosthesis} (The materials and placement date of prior fixed prosthesis.)
         */
        public ItemsComponent setProsthesis(ProsthesisComponent value) { 
          this.prosthesis = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("sequence", "integer", "A service line number.", 0, java.lang.Integer.MAX_VALUE, sequence));
          childrenList.add(new Property("type", "Coding", "The type of product or service.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("provider", "Reference(Practitioner)", "The practitioner who is responsible for the services rendered to the patient.", 0, java.lang.Integer.MAX_VALUE, provider));
          childrenList.add(new Property("service", "Coding", "If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.", 0, java.lang.Integer.MAX_VALUE, service));
          childrenList.add(new Property("serviceDate", "date", "The date when the enclosed suite of services were performed or completed.", 0, java.lang.Integer.MAX_VALUE, serviceDate));
          childrenList.add(new Property("quantity", "Quantity", "The number of repetitions of a service or product.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("unitPrice", "Money", "If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.", 0, java.lang.Integer.MAX_VALUE, unitPrice));
          childrenList.add(new Property("factor", "decimal", "A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.", 0, java.lang.Integer.MAX_VALUE, factor));
          childrenList.add(new Property("points", "decimal", "An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.", 0, java.lang.Integer.MAX_VALUE, points));
          childrenList.add(new Property("net", "Money", "The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.", 0, java.lang.Integer.MAX_VALUE, net));
          childrenList.add(new Property("udi", "Coding", "List of Unique Device Identifiers associated with this line item.", 0, java.lang.Integer.MAX_VALUE, udi));
          childrenList.add(new Property("bodySite", "Coding", "Physical service site on the patient (limb, tooth, etc).", 0, java.lang.Integer.MAX_VALUE, bodySite));
          childrenList.add(new Property("subsite", "Coding", "A region or surface of the site, eg. limb region or tooth surface(s).", 0, java.lang.Integer.MAX_VALUE, subsite));
          childrenList.add(new Property("modifier", "Coding", "Item typification or modifiers codes, eg for Oral whether the treatment is cosmetic or associated with TMJ, or an appliance was lost or stolen.", 0, java.lang.Integer.MAX_VALUE, modifier));
          childrenList.add(new Property("detail", "", "Second tier of goods and services.", 0, java.lang.Integer.MAX_VALUE, detail));
          childrenList.add(new Property("prosthesis", "", "The materials and placement date of prior fixed prosthesis.", 0, java.lang.Integer.MAX_VALUE, prosthesis));
        }

      public ItemsComponent copy() {
        ItemsComponent dst = new ItemsComponent();
        copyValues(dst);
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.type = type == null ? null : type.copy();
        dst.provider = provider == null ? null : provider.copy();
        dst.service = service == null ? null : service.copy();
        dst.serviceDate = serviceDate == null ? null : serviceDate.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.unitPrice = unitPrice == null ? null : unitPrice.copy();
        dst.factor = factor == null ? null : factor.copy();
        dst.points = points == null ? null : points.copy();
        dst.net = net == null ? null : net.copy();
        dst.udi = udi == null ? null : udi.copy();
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.subsite = new ArrayList<Coding>();
        for (Coding i : subsite)
          dst.subsite.add(i.copy());
        dst.modifier = new ArrayList<Coding>();
        for (Coding i : modifier)
          dst.modifier.add(i.copy());
        dst.detail = new ArrayList<DetailComponent>();
        for (DetailComponent i : detail)
          dst.detail.add(i.copy());
        dst.prosthesis = prosthesis == null ? null : prosthesis.copy();
        return dst;
      }

  }

    public static class DetailComponent extends BackboneElement {
        /**
         * A service line number.
         */
        protected IntegerType sequence;

        /**
         * The type of product or service.
         */
        protected Coding type;

        /**
         * If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.
         */
        protected Coding service;

        /**
         * The number of repetitions of a service or product.
         */
        protected Quantity quantity;

        /**
         * If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.
         */
        protected Money unitPrice;

        /**
         * A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        protected DecimalType factor;

        /**
         * An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        protected DecimalType points;

        /**
         * The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.
         */
        protected Money net;

        /**
         * List of Unique Device Identifiers associated with this line item.
         */
        protected Coding udi;

        /**
         * Third tier of goods and services.
         */
        protected List<SubDetailComponent> subDetail = new ArrayList<SubDetailComponent>();

        private static final long serialVersionUID = 1268522508L;

      public DetailComponent() {
        super();
      }

      public DetailComponent(IntegerType sequence, Coding type, Coding service) {
        super();
        this.sequence = sequence;
        this.type = type;
        this.service = service;
      }

        /**
         * @return {@link #sequence} (A service line number.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public IntegerType getSequenceElement() { 
          return this.sequence;
        }

        /**
         * @param value {@link #sequence} (A service line number.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public DetailComponent setSequenceElement(IntegerType value) { 
          this.sequence = value;
          return this;
        }

        /**
         * @return A service line number.
         */
        public int getSequence() { 
          return this.sequence == null ? null : this.sequence.getValue();
        }

        /**
         * @param value A service line number.
         */
        public DetailComponent setSequence(int value) { 
            if (this.sequence == null)
              this.sequence = new IntegerType();
            this.sequence.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (The type of product or service.)
         */
        public Coding getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of product or service.)
         */
        public DetailComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #service} (If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.)
         */
        public Coding getService() { 
          return this.service;
        }

        /**
         * @param value {@link #service} (If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.)
         */
        public DetailComponent setService(Coding value) { 
          this.service = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The number of repetitions of a service or product.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The number of repetitions of a service or product.)
         */
        public DetailComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #unitPrice} (If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.)
         */
        public Money getUnitPrice() { 
          return this.unitPrice;
        }

        /**
         * @param value {@link #unitPrice} (If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.)
         */
        public DetailComponent setUnitPrice(Money value) { 
          this.unitPrice = value;
          return this;
        }

        /**
         * @return {@link #factor} (A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.). This is the underlying object with id, value and extensions. The accessor "getFactor" gives direct access to the value
         */
        public DecimalType getFactorElement() { 
          return this.factor;
        }

        /**
         * @param value {@link #factor} (A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.). This is the underlying object with id, value and extensions. The accessor "getFactor" gives direct access to the value
         */
        public DetailComponent setFactorElement(DecimalType value) { 
          this.factor = value;
          return this;
        }

        /**
         * @return A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        public BigDecimal getFactor() { 
          return this.factor == null ? null : this.factor.getValue();
        }

        /**
         * @param value A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        public DetailComponent setFactor(BigDecimal value) { 
          if (value == null)
            this.factor = null;
          else {
            if (this.factor == null)
              this.factor = new DecimalType();
            this.factor.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #points} (An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.). This is the underlying object with id, value and extensions. The accessor "getPoints" gives direct access to the value
         */
        public DecimalType getPointsElement() { 
          return this.points;
        }

        /**
         * @param value {@link #points} (An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.). This is the underlying object with id, value and extensions. The accessor "getPoints" gives direct access to the value
         */
        public DetailComponent setPointsElement(DecimalType value) { 
          this.points = value;
          return this;
        }

        /**
         * @return An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        public BigDecimal getPoints() { 
          return this.points == null ? null : this.points.getValue();
        }

        /**
         * @param value An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        public DetailComponent setPoints(BigDecimal value) { 
          if (value == null)
            this.points = null;
          else {
            if (this.points == null)
              this.points = new DecimalType();
            this.points.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #net} (The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.)
         */
        public Money getNet() { 
          return this.net;
        }

        /**
         * @param value {@link #net} (The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.)
         */
        public DetailComponent setNet(Money value) { 
          this.net = value;
          return this;
        }

        /**
         * @return {@link #udi} (List of Unique Device Identifiers associated with this line item.)
         */
        public Coding getUdi() { 
          return this.udi;
        }

        /**
         * @param value {@link #udi} (List of Unique Device Identifiers associated with this line item.)
         */
        public DetailComponent setUdi(Coding value) { 
          this.udi = value;
          return this;
        }

        /**
         * @return {@link #subDetail} (Third tier of goods and services.)
         */
        public List<SubDetailComponent> getSubDetail() { 
          return this.subDetail;
        }

        /**
         * @return {@link #subDetail} (Third tier of goods and services.)
         */
    // syntactic sugar
        public SubDetailComponent addSubDetail() { //3
          SubDetailComponent t = new SubDetailComponent();
          this.subDetail.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("sequence", "integer", "A service line number.", 0, java.lang.Integer.MAX_VALUE, sequence));
          childrenList.add(new Property("type", "Coding", "The type of product or service.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("service", "Coding", "If a grouping item then 'GROUP' otherwise it is a node therefore a code to indicate the Professional Service or Product supplied.", 0, java.lang.Integer.MAX_VALUE, service));
          childrenList.add(new Property("quantity", "Quantity", "The number of repetitions of a service or product.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("unitPrice", "Money", "If the item is a node then this is the fee for the product or service, otherwise this is the total of the fees for the children of the group.", 0, java.lang.Integer.MAX_VALUE, unitPrice));
          childrenList.add(new Property("factor", "decimal", "A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.", 0, java.lang.Integer.MAX_VALUE, factor));
          childrenList.add(new Property("points", "decimal", "An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.", 0, java.lang.Integer.MAX_VALUE, points));
          childrenList.add(new Property("net", "Money", "The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.", 0, java.lang.Integer.MAX_VALUE, net));
          childrenList.add(new Property("udi", "Coding", "List of Unique Device Identifiers associated with this line item.", 0, java.lang.Integer.MAX_VALUE, udi));
          childrenList.add(new Property("subDetail", "", "Third tier of goods and services.", 0, java.lang.Integer.MAX_VALUE, subDetail));
        }

      public DetailComponent copy() {
        DetailComponent dst = new DetailComponent();
        copyValues(dst);
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.type = type == null ? null : type.copy();
        dst.service = service == null ? null : service.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.unitPrice = unitPrice == null ? null : unitPrice.copy();
        dst.factor = factor == null ? null : factor.copy();
        dst.points = points == null ? null : points.copy();
        dst.net = net == null ? null : net.copy();
        dst.udi = udi == null ? null : udi.copy();
        dst.subDetail = new ArrayList<SubDetailComponent>();
        for (SubDetailComponent i : subDetail)
          dst.subDetail.add(i.copy());
        return dst;
      }

  }

    public static class SubDetailComponent extends BackboneElement {
        /**
         * A service line number.
         */
        protected IntegerType sequence;

        /**
         * The type of product or service.
         */
        protected Coding type;

        /**
         * The fee for an addtional service or product or charge.
         */
        protected Coding service;

        /**
         * The number of repetitions of a service or product.
         */
        protected Quantity quantity;

        /**
         * The fee for an addtional service or product or charge.
         */
        protected Money unitPrice;

        /**
         * A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        protected DecimalType factor;

        /**
         * An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        protected DecimalType points;

        /**
         * The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.
         */
        protected Money net;

        /**
         * List of Unique Device Identifiers associated with this line item.
         */
        protected Coding udi;

        private static final long serialVersionUID = 122809194L;

      public SubDetailComponent() {
        super();
      }

      public SubDetailComponent(IntegerType sequence, Coding type, Coding service) {
        super();
        this.sequence = sequence;
        this.type = type;
        this.service = service;
      }

        /**
         * @return {@link #sequence} (A service line number.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public IntegerType getSequenceElement() { 
          return this.sequence;
        }

        /**
         * @param value {@link #sequence} (A service line number.). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public SubDetailComponent setSequenceElement(IntegerType value) { 
          this.sequence = value;
          return this;
        }

        /**
         * @return A service line number.
         */
        public int getSequence() { 
          return this.sequence == null ? null : this.sequence.getValue();
        }

        /**
         * @param value A service line number.
         */
        public SubDetailComponent setSequence(int value) { 
            if (this.sequence == null)
              this.sequence = new IntegerType();
            this.sequence.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (The type of product or service.)
         */
        public Coding getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of product or service.)
         */
        public SubDetailComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #service} (The fee for an addtional service or product or charge.)
         */
        public Coding getService() { 
          return this.service;
        }

        /**
         * @param value {@link #service} (The fee for an addtional service or product or charge.)
         */
        public SubDetailComponent setService(Coding value) { 
          this.service = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The number of repetitions of a service or product.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The number of repetitions of a service or product.)
         */
        public SubDetailComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #unitPrice} (The fee for an addtional service or product or charge.)
         */
        public Money getUnitPrice() { 
          return this.unitPrice;
        }

        /**
         * @param value {@link #unitPrice} (The fee for an addtional service or product or charge.)
         */
        public SubDetailComponent setUnitPrice(Money value) { 
          this.unitPrice = value;
          return this;
        }

        /**
         * @return {@link #factor} (A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.). This is the underlying object with id, value and extensions. The accessor "getFactor" gives direct access to the value
         */
        public DecimalType getFactorElement() { 
          return this.factor;
        }

        /**
         * @param value {@link #factor} (A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.). This is the underlying object with id, value and extensions. The accessor "getFactor" gives direct access to the value
         */
        public SubDetailComponent setFactorElement(DecimalType value) { 
          this.factor = value;
          return this;
        }

        /**
         * @return A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        public BigDecimal getFactor() { 
          return this.factor == null ? null : this.factor.getValue();
        }

        /**
         * @param value A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.
         */
        public SubDetailComponent setFactor(BigDecimal value) { 
          if (value == null)
            this.factor = null;
          else {
            if (this.factor == null)
              this.factor = new DecimalType();
            this.factor.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #points} (An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.). This is the underlying object with id, value and extensions. The accessor "getPoints" gives direct access to the value
         */
        public DecimalType getPointsElement() { 
          return this.points;
        }

        /**
         * @param value {@link #points} (An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.). This is the underlying object with id, value and extensions. The accessor "getPoints" gives direct access to the value
         */
        public SubDetailComponent setPointsElement(DecimalType value) { 
          this.points = value;
          return this;
        }

        /**
         * @return An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        public BigDecimal getPoints() { 
          return this.points == null ? null : this.points.getValue();
        }

        /**
         * @param value An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.
         */
        public SubDetailComponent setPoints(BigDecimal value) { 
          if (value == null)
            this.points = null;
          else {
            if (this.points == null)
              this.points = new DecimalType();
            this.points.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #net} (The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.)
         */
        public Money getNet() { 
          return this.net;
        }

        /**
         * @param value {@link #net} (The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.)
         */
        public SubDetailComponent setNet(Money value) { 
          this.net = value;
          return this;
        }

        /**
         * @return {@link #udi} (List of Unique Device Identifiers associated with this line item.)
         */
        public Coding getUdi() { 
          return this.udi;
        }

        /**
         * @param value {@link #udi} (List of Unique Device Identifiers associated with this line item.)
         */
        public SubDetailComponent setUdi(Coding value) { 
          this.udi = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("sequence", "integer", "A service line number.", 0, java.lang.Integer.MAX_VALUE, sequence));
          childrenList.add(new Property("type", "Coding", "The type of product or service.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("service", "Coding", "The fee for an addtional service or product or charge.", 0, java.lang.Integer.MAX_VALUE, service));
          childrenList.add(new Property("quantity", "Quantity", "The number of repetitions of a service or product.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("unitPrice", "Money", "The fee for an addtional service or product or charge.", 0, java.lang.Integer.MAX_VALUE, unitPrice));
          childrenList.add(new Property("factor", "decimal", "A real number that represents a multiplier used in determining the overall value of services delivered and/or goods received. The concept of a Factor allows for a discount or surcharge multiplier to be applied to a monetary amount.", 0, java.lang.Integer.MAX_VALUE, factor));
          childrenList.add(new Property("points", "decimal", "An amount that expresses the weighting (based on difficulty, cost and/or resource intensiveness) associated with the good or service delivered. The concept of Points allows for assignment of point values for services and/or goods, such that a monetary amount can be assigned to each point.", 0, java.lang.Integer.MAX_VALUE, points));
          childrenList.add(new Property("net", "Money", "The quantity times the unit price for an addtional service or product or charge. For example, the formula: unit Quantity * unit Price (Cost per Point) * factor Number  * points = net Amount. Quantity, factor and points are assumed to be 1 if not supplied.", 0, java.lang.Integer.MAX_VALUE, net));
          childrenList.add(new Property("udi", "Coding", "List of Unique Device Identifiers associated with this line item.", 0, java.lang.Integer.MAX_VALUE, udi));
        }

      public SubDetailComponent copy() {
        SubDetailComponent dst = new SubDetailComponent();
        copyValues(dst);
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.type = type == null ? null : type.copy();
        dst.service = service == null ? null : service.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.unitPrice = unitPrice == null ? null : unitPrice.copy();
        dst.factor = factor == null ? null : factor.copy();
        dst.points = points == null ? null : points.copy();
        dst.net = net == null ? null : net.copy();
        dst.udi = udi == null ? null : udi.copy();
        return dst;
      }

  }

    public static class ProsthesisComponent extends BackboneElement {
        /**
         * Is this the initial placement of a fixed prosthesis?.
         */
        protected BooleanType initial;

        /**
         * Date of the initial placement.
         */
        protected DateType priorDate;

        /**
         * Material of the prior denture or bridge prosthesis. (Oral).
         */
        protected Coding priorMaterial;

        private static final long serialVersionUID = 1739349641L;

      public ProsthesisComponent() {
        super();
      }

        /**
         * @return {@link #initial} (Is this the initial placement of a fixed prosthesis?.). This is the underlying object with id, value and extensions. The accessor "getInitial" gives direct access to the value
         */
        public BooleanType getInitialElement() { 
          return this.initial;
        }

        /**
         * @param value {@link #initial} (Is this the initial placement of a fixed prosthesis?.). This is the underlying object with id, value and extensions. The accessor "getInitial" gives direct access to the value
         */
        public ProsthesisComponent setInitialElement(BooleanType value) { 
          this.initial = value;
          return this;
        }

        /**
         * @return Is this the initial placement of a fixed prosthesis?.
         */
        public boolean getInitial() { 
          return this.initial == null ? false : this.initial.getValue();
        }

        /**
         * @param value Is this the initial placement of a fixed prosthesis?.
         */
        public ProsthesisComponent setInitial(boolean value) { 
          if (value == false)
            this.initial = null;
          else {
            if (this.initial == null)
              this.initial = new BooleanType();
            this.initial.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #priorDate} (Date of the initial placement.). This is the underlying object with id, value and extensions. The accessor "getPriorDate" gives direct access to the value
         */
        public DateType getPriorDateElement() { 
          return this.priorDate;
        }

        /**
         * @param value {@link #priorDate} (Date of the initial placement.). This is the underlying object with id, value and extensions. The accessor "getPriorDate" gives direct access to the value
         */
        public ProsthesisComponent setPriorDateElement(DateType value) { 
          this.priorDate = value;
          return this;
        }

        /**
         * @return Date of the initial placement.
         */
        public DateAndTime getPriorDate() { 
          return this.priorDate == null ? null : this.priorDate.getValue();
        }

        /**
         * @param value Date of the initial placement.
         */
        public ProsthesisComponent setPriorDate(DateAndTime value) { 
          if (value == null)
            this.priorDate = null;
          else {
            if (this.priorDate == null)
              this.priorDate = new DateType();
            this.priorDate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #priorMaterial} (Material of the prior denture or bridge prosthesis. (Oral).)
         */
        public Coding getPriorMaterial() { 
          return this.priorMaterial;
        }

        /**
         * @param value {@link #priorMaterial} (Material of the prior denture or bridge prosthesis. (Oral).)
         */
        public ProsthesisComponent setPriorMaterial(Coding value) { 
          this.priorMaterial = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("initial", "boolean", "Is this the initial placement of a fixed prosthesis?.", 0, java.lang.Integer.MAX_VALUE, initial));
          childrenList.add(new Property("priorDate", "date", "Date of the initial placement.", 0, java.lang.Integer.MAX_VALUE, priorDate));
          childrenList.add(new Property("priorMaterial", "Coding", "Material of the prior denture or bridge prosthesis. (Oral).", 0, java.lang.Integer.MAX_VALUE, priorMaterial));
        }

      public ProsthesisComponent copy() {
        ProsthesisComponent dst = new ProsthesisComponent();
        copyValues(dst);
        dst.initial = initial == null ? null : initial.copy();
        dst.priorDate = priorDate == null ? null : priorDate.copy();
        dst.priorMaterial = priorMaterial == null ? null : priorMaterial.copy();
        return dst;
      }

  }

    /**
     * The business identifier for the instance: invoice number, claim number, pre-determination or pre-authorization number.
     */
    protected Identifier identifier;

    /**
     * The version of the specification on which this instance relies.
     */
    protected Coding ruleset;

    /**
     * The version of the specification from which the original instance was created.
     */
    protected Coding originalRuleset;

    /**
     * The date when the enclosed suite of services were performed or completed.
     */
    protected DateType date;

    /**
     * Insurer Identifier, typical BIN number (6 digit).
     */
    protected Reference target;

    /**
     * The actual object that is the target of the reference (Insurer Identifier, typical BIN number (6 digit).)
     */
    protected Organization targetTarget;

    /**
     * The provider which is responsible for the bill, claim pre-determination, pre-authorization.
     */
    protected Reference provider;

    /**
     * The actual object that is the target of the reference (The provider which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    protected Practitioner providerTarget;

    /**
     * The organization which is responsible for the bill, claim pre-determination, pre-authorization.
     */
    protected Reference organization;

    /**
     * The actual object that is the target of the reference (The organization which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    protected Organization organizationTarget;

    /**
     * Complete (Bill or Claim), Proposed (Pre-Authorization), Exploratory (Pre-determination).
     */
    protected Enumeration<UseLink> use;

    /**
     * Immediate (STAT), best effort (NORMAL), deferred (DEFER).
     */
    protected Coding priority;

    /**
     * In the case of a Pre-Determination/Pre-Authorization the provider may request that funds in the amount of the expected Benefit be reserved ('Patient' or 'Provider') to pay for the Benefits determined on the subsequent claim(s). 'None' explicitly indicates no funds reserving is requested.
     */
    protected Coding fundsReserve;

    /**
     * Person who created the invoice/claim/pre-determination or pre-authorization.
     */
    protected Reference enterer;

    /**
     * The actual object that is the target of the reference (Person who created the invoice/claim/pre-determination or pre-authorization.)
     */
    protected Practitioner entererTarget;

    /**
     * Facility where the services were provided.
     */
    protected Reference facility;

    /**
     * The actual object that is the target of the reference (Facility where the services were provided.)
     */
    protected Location facilityTarget;

    /**
     * Theparty to be reimbused for the services.
     */
    protected PayeeComponent payee;

    /**
     * The referral resource which lists the date, practitioner, reason and other supporting information.
     */
    protected Reference referral;

    /**
     * The actual object that is the target of the reference (The referral resource which lists the date, practitioner, reason and other supporting information.)
     */
    protected ReferralRequest referralTarget;

    /**
     * Ordered list of patient diagnosis for which care is sought.
     */
    protected List<DiagnosisComponent> diagnosis = new ArrayList<DiagnosisComponent>();

    /**
     * List of patient conditions for which care is sought.
     */
    protected List<Coding> condition = new ArrayList<Coding>();

    /**
     * Patient Resource.
     */
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (Patient Resource.)
     */
    protected Patient patientTarget;

    /**
     * Financial instrument by which payment information for health care.
     */
    protected List<CoverageComponent> coverage = new ArrayList<CoverageComponent>();

    /**
     * Factors which may influence the applicability of coverage.
     */
    protected List<Coding> exception = new ArrayList<Coding>();

    /**
     * Name of school for over-aged dependants.
     */
    protected StringType school;

    /**
     * Date of an accident which these services are addessing.
     */
    protected DateType accident;

    /**
     * Type of accident: work, auto, etc.
     */
    protected Coding accidentType;

    /**
     * A list of intervention and exception codes which may influence the adjudication of the claim.
     */
    protected List<Coding> interventionException = new ArrayList<Coding>();

    /**
     * A list of teeth which would be expected but are not found due to having been previously  extracted or for other reasons.
     */
    protected List<MissingTeethComponent> missingteeth = new ArrayList<MissingTeethComponent>();

    /**
     * The highlevel detail sof an Orthodonic Treatment Plan.
     */
    protected OrthodonticPlanComponent orthoPlan;

    /**
     * First tier of goods and services.
     */
    protected List<ItemsComponent> item = new ArrayList<ItemsComponent>();

    /**
     * Code to indicate that Xrays, images, emails, documents, models or attachments are being sent in support of this submission.
     */
    protected List<Coding> additionalMaterials = new ArrayList<Coding>();

    private static final long serialVersionUID = -1376670187L;

    public OralHealthClaim() {
      super();
    }

    public OralHealthClaim(Reference patient) {
      super();
      this.patient = patient;
    }

    /**
     * @return {@link #identifier} (The business identifier for the instance: invoice number, claim number, pre-determination or pre-authorization number.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The business identifier for the instance: invoice number, claim number, pre-determination or pre-authorization number.)
     */
    public OralHealthClaim setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #ruleset} (The version of the specification on which this instance relies.)
     */
    public Coding getRuleset() { 
      return this.ruleset;
    }

    /**
     * @param value {@link #ruleset} (The version of the specification on which this instance relies.)
     */
    public OralHealthClaim setRuleset(Coding value) { 
      this.ruleset = value;
      return this;
    }

    /**
     * @return {@link #originalRuleset} (The version of the specification from which the original instance was created.)
     */
    public Coding getOriginalRuleset() { 
      return this.originalRuleset;
    }

    /**
     * @param value {@link #originalRuleset} (The version of the specification from which the original instance was created.)
     */
    public OralHealthClaim setOriginalRuleset(Coding value) { 
      this.originalRuleset = value;
      return this;
    }

    /**
     * @return {@link #date} (The date when the enclosed suite of services were performed or completed.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date when the enclosed suite of services were performed or completed.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public OralHealthClaim setDateElement(DateType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date when the enclosed suite of services were performed or completed.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date when the enclosed suite of services were performed or completed.
     */
    public OralHealthClaim setDate(DateAndTime value) { 
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
     * @return {@link #target} (Insurer Identifier, typical BIN number (6 digit).)
     */
    public Reference getTarget() { 
      return this.target;
    }

    /**
     * @param value {@link #target} (Insurer Identifier, typical BIN number (6 digit).)
     */
    public OralHealthClaim setTarget(Reference value) { 
      this.target = value;
      return this;
    }

    /**
     * @return {@link #target} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Insurer Identifier, typical BIN number (6 digit).)
     */
    public Organization getTargetTarget() { 
      return this.targetTarget;
    }

    /**
     * @param value {@link #target} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Insurer Identifier, typical BIN number (6 digit).)
     */
    public OralHealthClaim setTargetTarget(Organization value) { 
      this.targetTarget = value;
      return this;
    }

    /**
     * @return {@link #provider} (The provider which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public Reference getProvider() { 
      return this.provider;
    }

    /**
     * @param value {@link #provider} (The provider which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public OralHealthClaim setProvider(Reference value) { 
      this.provider = value;
      return this;
    }

    /**
     * @return {@link #provider} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The provider which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public Practitioner getProviderTarget() { 
      return this.providerTarget;
    }

    /**
     * @param value {@link #provider} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The provider which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public OralHealthClaim setProviderTarget(Practitioner value) { 
      this.providerTarget = value;
      return this;
    }

    /**
     * @return {@link #organization} (The organization which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public Reference getOrganization() { 
      return this.organization;
    }

    /**
     * @param value {@link #organization} (The organization which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public OralHealthClaim setOrganization(Reference value) { 
      this.organization = value;
      return this;
    }

    /**
     * @return {@link #organization} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The organization which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public Organization getOrganizationTarget() { 
      return this.organizationTarget;
    }

    /**
     * @param value {@link #organization} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The organization which is responsible for the bill, claim pre-determination, pre-authorization.)
     */
    public OralHealthClaim setOrganizationTarget(Organization value) { 
      this.organizationTarget = value;
      return this;
    }

    /**
     * @return {@link #use} (Complete (Bill or Claim), Proposed (Pre-Authorization), Exploratory (Pre-determination).). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public Enumeration<UseLink> getUseElement() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (Complete (Bill or Claim), Proposed (Pre-Authorization), Exploratory (Pre-determination).). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public OralHealthClaim setUseElement(Enumeration<UseLink> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return Complete (Bill or Claim), Proposed (Pre-Authorization), Exploratory (Pre-determination).
     */
    public UseLink getUse() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value Complete (Bill or Claim), Proposed (Pre-Authorization), Exploratory (Pre-determination).
     */
    public OralHealthClaim setUse(UseLink value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<UseLink>();
        this.use.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #priority} (Immediate (STAT), best effort (NORMAL), deferred (DEFER).)
     */
    public Coding getPriority() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (Immediate (STAT), best effort (NORMAL), deferred (DEFER).)
     */
    public OralHealthClaim setPriority(Coding value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return {@link #fundsReserve} (In the case of a Pre-Determination/Pre-Authorization the provider may request that funds in the amount of the expected Benefit be reserved ('Patient' or 'Provider') to pay for the Benefits determined on the subsequent claim(s). 'None' explicitly indicates no funds reserving is requested.)
     */
    public Coding getFundsReserve() { 
      return this.fundsReserve;
    }

    /**
     * @param value {@link #fundsReserve} (In the case of a Pre-Determination/Pre-Authorization the provider may request that funds in the amount of the expected Benefit be reserved ('Patient' or 'Provider') to pay for the Benefits determined on the subsequent claim(s). 'None' explicitly indicates no funds reserving is requested.)
     */
    public OralHealthClaim setFundsReserve(Coding value) { 
      this.fundsReserve = value;
      return this;
    }

    /**
     * @return {@link #enterer} (Person who created the invoice/claim/pre-determination or pre-authorization.)
     */
    public Reference getEnterer() { 
      return this.enterer;
    }

    /**
     * @param value {@link #enterer} (Person who created the invoice/claim/pre-determination or pre-authorization.)
     */
    public OralHealthClaim setEnterer(Reference value) { 
      this.enterer = value;
      return this;
    }

    /**
     * @return {@link #enterer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Person who created the invoice/claim/pre-determination or pre-authorization.)
     */
    public Practitioner getEntererTarget() { 
      return this.entererTarget;
    }

    /**
     * @param value {@link #enterer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Person who created the invoice/claim/pre-determination or pre-authorization.)
     */
    public OralHealthClaim setEntererTarget(Practitioner value) { 
      this.entererTarget = value;
      return this;
    }

    /**
     * @return {@link #facility} (Facility where the services were provided.)
     */
    public Reference getFacility() { 
      return this.facility;
    }

    /**
     * @param value {@link #facility} (Facility where the services were provided.)
     */
    public OralHealthClaim setFacility(Reference value) { 
      this.facility = value;
      return this;
    }

    /**
     * @return {@link #facility} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Facility where the services were provided.)
     */
    public Location getFacilityTarget() { 
      return this.facilityTarget;
    }

    /**
     * @param value {@link #facility} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Facility where the services were provided.)
     */
    public OralHealthClaim setFacilityTarget(Location value) { 
      this.facilityTarget = value;
      return this;
    }

    /**
     * @return {@link #payee} (Theparty to be reimbused for the services.)
     */
    public PayeeComponent getPayee() { 
      return this.payee;
    }

    /**
     * @param value {@link #payee} (Theparty to be reimbused for the services.)
     */
    public OralHealthClaim setPayee(PayeeComponent value) { 
      this.payee = value;
      return this;
    }

    /**
     * @return {@link #referral} (The referral resource which lists the date, practitioner, reason and other supporting information.)
     */
    public Reference getReferral() { 
      return this.referral;
    }

    /**
     * @param value {@link #referral} (The referral resource which lists the date, practitioner, reason and other supporting information.)
     */
    public OralHealthClaim setReferral(Reference value) { 
      this.referral = value;
      return this;
    }

    /**
     * @return {@link #referral} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The referral resource which lists the date, practitioner, reason and other supporting information.)
     */
    public ReferralRequest getReferralTarget() { 
      return this.referralTarget;
    }

    /**
     * @param value {@link #referral} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The referral resource which lists the date, practitioner, reason and other supporting information.)
     */
    public OralHealthClaim setReferralTarget(ReferralRequest value) { 
      this.referralTarget = value;
      return this;
    }

    /**
     * @return {@link #diagnosis} (Ordered list of patient diagnosis for which care is sought.)
     */
    public List<DiagnosisComponent> getDiagnosis() { 
      return this.diagnosis;
    }

    /**
     * @return {@link #diagnosis} (Ordered list of patient diagnosis for which care is sought.)
     */
    // syntactic sugar
    public DiagnosisComponent addDiagnosis() { //3
      DiagnosisComponent t = new DiagnosisComponent();
      this.diagnosis.add(t);
      return t;
    }

    /**
     * @return {@link #condition} (List of patient conditions for which care is sought.)
     */
    public List<Coding> getCondition() { 
      return this.condition;
    }

    /**
     * @return {@link #condition} (List of patient conditions for which care is sought.)
     */
    // syntactic sugar
    public Coding addCondition() { //3
      Coding t = new Coding();
      this.condition.add(t);
      return t;
    }

    /**
     * @return {@link #patient} (Patient Resource.)
     */
    public Reference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (Patient Resource.)
     */
    public OralHealthClaim setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Patient Resource.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Patient Resource.)
     */
    public OralHealthClaim setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #coverage} (Financial instrument by which payment information for health care.)
     */
    public List<CoverageComponent> getCoverage() { 
      return this.coverage;
    }

    /**
     * @return {@link #coverage} (Financial instrument by which payment information for health care.)
     */
    // syntactic sugar
    public CoverageComponent addCoverage() { //3
      CoverageComponent t = new CoverageComponent();
      this.coverage.add(t);
      return t;
    }

    /**
     * @return {@link #exception} (Factors which may influence the applicability of coverage.)
     */
    public List<Coding> getException() { 
      return this.exception;
    }

    /**
     * @return {@link #exception} (Factors which may influence the applicability of coverage.)
     */
    // syntactic sugar
    public Coding addException() { //3
      Coding t = new Coding();
      this.exception.add(t);
      return t;
    }

    /**
     * @return {@link #school} (Name of school for over-aged dependants.). This is the underlying object with id, value and extensions. The accessor "getSchool" gives direct access to the value
     */
    public StringType getSchoolElement() { 
      return this.school;
    }

    /**
     * @param value {@link #school} (Name of school for over-aged dependants.). This is the underlying object with id, value and extensions. The accessor "getSchool" gives direct access to the value
     */
    public OralHealthClaim setSchoolElement(StringType value) { 
      this.school = value;
      return this;
    }

    /**
     * @return Name of school for over-aged dependants.
     */
    public String getSchool() { 
      return this.school == null ? null : this.school.getValue();
    }

    /**
     * @param value Name of school for over-aged dependants.
     */
    public OralHealthClaim setSchool(String value) { 
      if (Utilities.noString(value))
        this.school = null;
      else {
        if (this.school == null)
          this.school = new StringType();
        this.school.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #accident} (Date of an accident which these services are addessing.). This is the underlying object with id, value and extensions. The accessor "getAccident" gives direct access to the value
     */
    public DateType getAccidentElement() { 
      return this.accident;
    }

    /**
     * @param value {@link #accident} (Date of an accident which these services are addessing.). This is the underlying object with id, value and extensions. The accessor "getAccident" gives direct access to the value
     */
    public OralHealthClaim setAccidentElement(DateType value) { 
      this.accident = value;
      return this;
    }

    /**
     * @return Date of an accident which these services are addessing.
     */
    public DateAndTime getAccident() { 
      return this.accident == null ? null : this.accident.getValue();
    }

    /**
     * @param value Date of an accident which these services are addessing.
     */
    public OralHealthClaim setAccident(DateAndTime value) { 
      if (value == null)
        this.accident = null;
      else {
        if (this.accident == null)
          this.accident = new DateType();
        this.accident.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #accidentType} (Type of accident: work, auto, etc.)
     */
    public Coding getAccidentType() { 
      return this.accidentType;
    }

    /**
     * @param value {@link #accidentType} (Type of accident: work, auto, etc.)
     */
    public OralHealthClaim setAccidentType(Coding value) { 
      this.accidentType = value;
      return this;
    }

    /**
     * @return {@link #interventionException} (A list of intervention and exception codes which may influence the adjudication of the claim.)
     */
    public List<Coding> getInterventionException() { 
      return this.interventionException;
    }

    /**
     * @return {@link #interventionException} (A list of intervention and exception codes which may influence the adjudication of the claim.)
     */
    // syntactic sugar
    public Coding addInterventionException() { //3
      Coding t = new Coding();
      this.interventionException.add(t);
      return t;
    }

    /**
     * @return {@link #missingteeth} (A list of teeth which would be expected but are not found due to having been previously  extracted or for other reasons.)
     */
    public List<MissingTeethComponent> getMissingteeth() { 
      return this.missingteeth;
    }

    /**
     * @return {@link #missingteeth} (A list of teeth which would be expected but are not found due to having been previously  extracted or for other reasons.)
     */
    // syntactic sugar
    public MissingTeethComponent addMissingteeth() { //3
      MissingTeethComponent t = new MissingTeethComponent();
      this.missingteeth.add(t);
      return t;
    }

    /**
     * @return {@link #orthoPlan} (The highlevel detail sof an Orthodonic Treatment Plan.)
     */
    public OrthodonticPlanComponent getOrthoPlan() { 
      return this.orthoPlan;
    }

    /**
     * @param value {@link #orthoPlan} (The highlevel detail sof an Orthodonic Treatment Plan.)
     */
    public OralHealthClaim setOrthoPlan(OrthodonticPlanComponent value) { 
      this.orthoPlan = value;
      return this;
    }

    /**
     * @return {@link #item} (First tier of goods and services.)
     */
    public List<ItemsComponent> getItem() { 
      return this.item;
    }

    /**
     * @return {@link #item} (First tier of goods and services.)
     */
    // syntactic sugar
    public ItemsComponent addItem() { //3
      ItemsComponent t = new ItemsComponent();
      this.item.add(t);
      return t;
    }

    /**
     * @return {@link #additionalMaterials} (Code to indicate that Xrays, images, emails, documents, models or attachments are being sent in support of this submission.)
     */
    public List<Coding> getAdditionalMaterials() { 
      return this.additionalMaterials;
    }

    /**
     * @return {@link #additionalMaterials} (Code to indicate that Xrays, images, emails, documents, models or attachments are being sent in support of this submission.)
     */
    // syntactic sugar
    public Coding addAdditionalMaterials() { //3
      Coding t = new Coding();
      this.additionalMaterials.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "The business identifier for the instance: invoice number, claim number, pre-determination or pre-authorization number.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("ruleset", "Coding", "The version of the specification on which this instance relies.", 0, java.lang.Integer.MAX_VALUE, ruleset));
        childrenList.add(new Property("originalRuleset", "Coding", "The version of the specification from which the original instance was created.", 0, java.lang.Integer.MAX_VALUE, originalRuleset));
        childrenList.add(new Property("date", "date", "The date when the enclosed suite of services were performed or completed.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("target", "Reference(Organization)", "Insurer Identifier, typical BIN number (6 digit).", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("provider", "Reference(Practitioner)", "The provider which is responsible for the bill, claim pre-determination, pre-authorization.", 0, java.lang.Integer.MAX_VALUE, provider));
        childrenList.add(new Property("organization", "Reference(Organization)", "The organization which is responsible for the bill, claim pre-determination, pre-authorization.", 0, java.lang.Integer.MAX_VALUE, organization));
        childrenList.add(new Property("use", "code", "Complete (Bill or Claim), Proposed (Pre-Authorization), Exploratory (Pre-determination).", 0, java.lang.Integer.MAX_VALUE, use));
        childrenList.add(new Property("priority", "Coding", "Immediate (STAT), best effort (NORMAL), deferred (DEFER).", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("fundsReserve", "Coding", "In the case of a Pre-Determination/Pre-Authorization the provider may request that funds in the amount of the expected Benefit be reserved ('Patient' or 'Provider') to pay for the Benefits determined on the subsequent claim(s). 'None' explicitly indicates no funds reserving is requested.", 0, java.lang.Integer.MAX_VALUE, fundsReserve));
        childrenList.add(new Property("enterer", "Reference(Practitioner)", "Person who created the invoice/claim/pre-determination or pre-authorization.", 0, java.lang.Integer.MAX_VALUE, enterer));
        childrenList.add(new Property("facility", "Reference(Location)", "Facility where the services were provided.", 0, java.lang.Integer.MAX_VALUE, facility));
        childrenList.add(new Property("payee", "", "Theparty to be reimbused for the services.", 0, java.lang.Integer.MAX_VALUE, payee));
        childrenList.add(new Property("referral", "Reference(ReferralRequest)", "The referral resource which lists the date, practitioner, reason and other supporting information.", 0, java.lang.Integer.MAX_VALUE, referral));
        childrenList.add(new Property("diagnosis", "", "Ordered list of patient diagnosis for which care is sought.", 0, java.lang.Integer.MAX_VALUE, diagnosis));
        childrenList.add(new Property("condition", "Coding", "List of patient conditions for which care is sought.", 0, java.lang.Integer.MAX_VALUE, condition));
        childrenList.add(new Property("patient", "Reference(Patient)", "Patient Resource.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("coverage", "", "Financial instrument by which payment information for health care.", 0, java.lang.Integer.MAX_VALUE, coverage));
        childrenList.add(new Property("exception", "Coding", "Factors which may influence the applicability of coverage.", 0, java.lang.Integer.MAX_VALUE, exception));
        childrenList.add(new Property("school", "string", "Name of school for over-aged dependants.", 0, java.lang.Integer.MAX_VALUE, school));
        childrenList.add(new Property("accident", "date", "Date of an accident which these services are addessing.", 0, java.lang.Integer.MAX_VALUE, accident));
        childrenList.add(new Property("accidentType", "Coding", "Type of accident: work, auto, etc.", 0, java.lang.Integer.MAX_VALUE, accidentType));
        childrenList.add(new Property("interventionException", "Coding", "A list of intervention and exception codes which may influence the adjudication of the claim.", 0, java.lang.Integer.MAX_VALUE, interventionException));
        childrenList.add(new Property("missingteeth", "", "A list of teeth which would be expected but are not found due to having been previously  extracted or for other reasons.", 0, java.lang.Integer.MAX_VALUE, missingteeth));
        childrenList.add(new Property("orthoPlan", "", "The highlevel detail sof an Orthodonic Treatment Plan.", 0, java.lang.Integer.MAX_VALUE, orthoPlan));
        childrenList.add(new Property("item", "", "First tier of goods and services.", 0, java.lang.Integer.MAX_VALUE, item));
        childrenList.add(new Property("additionalMaterials", "Coding", "Code to indicate that Xrays, images, emails, documents, models or attachments are being sent in support of this submission.", 0, java.lang.Integer.MAX_VALUE, additionalMaterials));
      }

      public OralHealthClaim copy() {
        OralHealthClaim dst = new OralHealthClaim();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.ruleset = ruleset == null ? null : ruleset.copy();
        dst.originalRuleset = originalRuleset == null ? null : originalRuleset.copy();
        dst.date = date == null ? null : date.copy();
        dst.target = target == null ? null : target.copy();
        dst.provider = provider == null ? null : provider.copy();
        dst.organization = organization == null ? null : organization.copy();
        dst.use = use == null ? null : use.copy();
        dst.priority = priority == null ? null : priority.copy();
        dst.fundsReserve = fundsReserve == null ? null : fundsReserve.copy();
        dst.enterer = enterer == null ? null : enterer.copy();
        dst.facility = facility == null ? null : facility.copy();
        dst.payee = payee == null ? null : payee.copy();
        dst.referral = referral == null ? null : referral.copy();
        dst.diagnosis = new ArrayList<DiagnosisComponent>();
        for (DiagnosisComponent i : diagnosis)
          dst.diagnosis.add(i.copy());
        dst.condition = new ArrayList<Coding>();
        for (Coding i : condition)
          dst.condition.add(i.copy());
        dst.patient = patient == null ? null : patient.copy();
        dst.coverage = new ArrayList<CoverageComponent>();
        for (CoverageComponent i : coverage)
          dst.coverage.add(i.copy());
        dst.exception = new ArrayList<Coding>();
        for (Coding i : exception)
          dst.exception.add(i.copy());
        dst.school = school == null ? null : school.copy();
        dst.accident = accident == null ? null : accident.copy();
        dst.accidentType = accidentType == null ? null : accidentType.copy();
        dst.interventionException = new ArrayList<Coding>();
        for (Coding i : interventionException)
          dst.interventionException.add(i.copy());
        dst.missingteeth = new ArrayList<MissingTeethComponent>();
        for (MissingTeethComponent i : missingteeth)
          dst.missingteeth.add(i.copy());
        dst.orthoPlan = orthoPlan == null ? null : orthoPlan.copy();
        dst.item = new ArrayList<ItemsComponent>();
        for (ItemsComponent i : item)
          dst.item.add(i.copy());
        dst.additionalMaterials = new ArrayList<Coding>();
        for (Coding i : additionalMaterials)
          dst.additionalMaterials.add(i.copy());
        return dst;
      }

      protected OralHealthClaim typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.OralHealthClaim;
   }


}

