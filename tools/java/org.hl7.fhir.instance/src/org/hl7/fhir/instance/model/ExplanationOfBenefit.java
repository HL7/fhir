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
 * This resource provides the adjudication details from the processing of a Claim resource.
 */
public class ExplanationOfBenefit extends DomainResource {

    public enum RSLink {
        COMPLETE, // The processing completed without errors.
        ERROR, // The processing identified with errors.
        NULL; // added to help the parsers
        public static RSLink fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("complete".equals(codeString))
          return COMPLETE;
        if ("error".equals(codeString))
          return ERROR;
        throw new Exception("Unknown RSLink code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case COMPLETE: return "complete";
            case ERROR: return "error";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case COMPLETE: return "The processing completed without errors.";
            case ERROR: return "The processing identified with errors.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case COMPLETE: return "complete";
            case ERROR: return "error";
            default: return "?";
          }
        }
    }

  public static class RSLinkEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("complete".equals(codeString))
          return RSLink.COMPLETE;
        if ("error".equals(codeString))
          return RSLink.ERROR;
        throw new Exception("Unknown RSLink code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == RSLink.COMPLETE)
        return "complete";
      if (code == RSLink.ERROR)
        return "error";
      return "?";
      }
    }

    /**
     * The Response Business Identifier.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Original request resource referrence.
     */
    protected Reference request;

    /**
     * The actual object that is the target of the reference (Original request resource referrence.)
     */
    protected OralHealthClaim requestTarget;

    /**
     * Original request Business Identifer.
     */
    protected List<Identifier> requestIdentifier = new ArrayList<Identifier>();

    /**
     * Transaction status: error, complete.
     */
    protected Enumeration<RSLink> outcome;

    /**
     * A description of the status of the adjudication.
     */
    protected StringType disposition;

    /**
     * The version of the style of resource contents. This should be mapped to the allowable profiles for this and supporting resources.
     */
    protected Coding ruleset;

    /**
     * The style (standard) and version of the original material which was converted into this resource.
     */
    protected Coding originalRuleset;

    /**
     * The date when the enclosed suite of services were performed or completed.
     */
    protected DateType date;

    /**
     * The Insurer who produced this adjudicated response.
     */
    protected Reference organization;

    /**
     * The actual object that is the target of the reference (The Insurer who produced this adjudicated response.)
     */
    protected Organization organizationTarget;

    /**
     * The practitioner who is responsible for the services rendered to the patient.
     */
    protected Reference requestProvider;

    /**
     * The actual object that is the target of the reference (The practitioner who is responsible for the services rendered to the patient.)
     */
    protected Practitioner requestProviderTarget;

    /**
     * The organization which is responsible for the services rendered to the patient.
     */
    protected Reference requestOrganization;

    /**
     * The actual object that is the target of the reference (The organization which is responsible for the services rendered to the patient.)
     */
    protected Organization requestOrganizationTarget;

    private static final long serialVersionUID = -991483386L;

    public ExplanationOfBenefit() {
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
     * @return {@link #request} (Original request resource referrence.)
     */
    public Reference getRequest() { 
      return this.request;
    }

    /**
     * @param value {@link #request} (Original request resource referrence.)
     */
    public ExplanationOfBenefit setRequest(Reference value) { 
      this.request = value;
      return this;
    }

    /**
     * @return {@link #request} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Original request resource referrence.)
     */
    public OralHealthClaim getRequestTarget() { 
      return this.requestTarget;
    }

    /**
     * @param value {@link #request} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Original request resource referrence.)
     */
    public ExplanationOfBenefit setRequestTarget(OralHealthClaim value) { 
      this.requestTarget = value;
      return this;
    }

    /**
     * @return {@link #requestIdentifier} (Original request Business Identifer.)
     */
    public List<Identifier> getRequestIdentifier() { 
      return this.requestIdentifier;
    }

    /**
     * @return {@link #requestIdentifier} (Original request Business Identifer.)
     */
    // syntactic sugar
    public Identifier addRequestIdentifier() { //3
      Identifier t = new Identifier();
      this.requestIdentifier.add(t);
      return t;
    }

    /**
     * @return {@link #outcome} (Transaction status: error, complete.). This is the underlying object with id, value and extensions. The accessor "getOutcome" gives direct access to the value
     */
    public Enumeration<RSLink> getOutcomeElement() { 
      return this.outcome;
    }

    /**
     * @param value {@link #outcome} (Transaction status: error, complete.). This is the underlying object with id, value and extensions. The accessor "getOutcome" gives direct access to the value
     */
    public ExplanationOfBenefit setOutcomeElement(Enumeration<RSLink> value) { 
      this.outcome = value;
      return this;
    }

    /**
     * @return Transaction status: error, complete.
     */
    public RSLink getOutcome() { 
      return this.outcome == null ? null : this.outcome.getValue();
    }

    /**
     * @param value Transaction status: error, complete.
     */
    public ExplanationOfBenefit setOutcome(RSLink value) { 
      if (value == null)
        this.outcome = null;
      else {
        if (this.outcome == null)
          this.outcome = new Enumeration<RSLink>();
        this.outcome.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #disposition} (A description of the status of the adjudication.). This is the underlying object with id, value and extensions. The accessor "getDisposition" gives direct access to the value
     */
    public StringType getDispositionElement() { 
      return this.disposition;
    }

    /**
     * @param value {@link #disposition} (A description of the status of the adjudication.). This is the underlying object with id, value and extensions. The accessor "getDisposition" gives direct access to the value
     */
    public ExplanationOfBenefit setDispositionElement(StringType value) { 
      this.disposition = value;
      return this;
    }

    /**
     * @return A description of the status of the adjudication.
     */
    public String getDisposition() { 
      return this.disposition == null ? null : this.disposition.getValue();
    }

    /**
     * @param value A description of the status of the adjudication.
     */
    public ExplanationOfBenefit setDisposition(String value) { 
      if (Utilities.noString(value))
        this.disposition = null;
      else {
        if (this.disposition == null)
          this.disposition = new StringType();
        this.disposition.setValue(value);
      }
      return this;
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
    public ExplanationOfBenefit setRuleset(Coding value) { 
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
    public ExplanationOfBenefit setOriginalRuleset(Coding value) { 
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
    public ExplanationOfBenefit setDateElement(DateType value) { 
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
    public ExplanationOfBenefit setDate(DateAndTime value) { 
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
     * @return {@link #organization} (The Insurer who produced this adjudicated response.)
     */
    public Reference getOrganization() { 
      return this.organization;
    }

    /**
     * @param value {@link #organization} (The Insurer who produced this adjudicated response.)
     */
    public ExplanationOfBenefit setOrganization(Reference value) { 
      this.organization = value;
      return this;
    }

    /**
     * @return {@link #organization} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The Insurer who produced this adjudicated response.)
     */
    public Organization getOrganizationTarget() { 
      return this.organizationTarget;
    }

    /**
     * @param value {@link #organization} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The Insurer who produced this adjudicated response.)
     */
    public ExplanationOfBenefit setOrganizationTarget(Organization value) { 
      this.organizationTarget = value;
      return this;
    }

    /**
     * @return {@link #requestProvider} (The practitioner who is responsible for the services rendered to the patient.)
     */
    public Reference getRequestProvider() { 
      return this.requestProvider;
    }

    /**
     * @param value {@link #requestProvider} (The practitioner who is responsible for the services rendered to the patient.)
     */
    public ExplanationOfBenefit setRequestProvider(Reference value) { 
      this.requestProvider = value;
      return this;
    }

    /**
     * @return {@link #requestProvider} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The practitioner who is responsible for the services rendered to the patient.)
     */
    public Practitioner getRequestProviderTarget() { 
      return this.requestProviderTarget;
    }

    /**
     * @param value {@link #requestProvider} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The practitioner who is responsible for the services rendered to the patient.)
     */
    public ExplanationOfBenefit setRequestProviderTarget(Practitioner value) { 
      this.requestProviderTarget = value;
      return this;
    }

    /**
     * @return {@link #requestOrganization} (The organization which is responsible for the services rendered to the patient.)
     */
    public Reference getRequestOrganization() { 
      return this.requestOrganization;
    }

    /**
     * @param value {@link #requestOrganization} (The organization which is responsible for the services rendered to the patient.)
     */
    public ExplanationOfBenefit setRequestOrganization(Reference value) { 
      this.requestOrganization = value;
      return this;
    }

    /**
     * @return {@link #requestOrganization} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The organization which is responsible for the services rendered to the patient.)
     */
    public Organization getRequestOrganizationTarget() { 
      return this.requestOrganizationTarget;
    }

    /**
     * @param value {@link #requestOrganization} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The organization which is responsible for the services rendered to the patient.)
     */
    public ExplanationOfBenefit setRequestOrganizationTarget(Organization value) { 
      this.requestOrganizationTarget = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "The Response Business Identifier.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("request", "Reference(OralHealthClaim)", "Original request resource referrence.", 0, java.lang.Integer.MAX_VALUE, request));
        childrenList.add(new Property("requestIdentifier", "Identifier", "Original request Business Identifer.", 0, java.lang.Integer.MAX_VALUE, requestIdentifier));
        childrenList.add(new Property("outcome", "code", "Transaction status: error, complete.", 0, java.lang.Integer.MAX_VALUE, outcome));
        childrenList.add(new Property("disposition", "string", "A description of the status of the adjudication.", 0, java.lang.Integer.MAX_VALUE, disposition));
        childrenList.add(new Property("ruleset", "Coding", "The version of the style of resource contents. This should be mapped to the allowable profiles for this and supporting resources.", 0, java.lang.Integer.MAX_VALUE, ruleset));
        childrenList.add(new Property("originalRuleset", "Coding", "The style (standard) and version of the original material which was converted into this resource.", 0, java.lang.Integer.MAX_VALUE, originalRuleset));
        childrenList.add(new Property("date", "date", "The date when the enclosed suite of services were performed or completed.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("organization", "Reference(Organization)", "The Insurer who produced this adjudicated response.", 0, java.lang.Integer.MAX_VALUE, organization));
        childrenList.add(new Property("requestProvider", "Reference(Practitioner)", "The practitioner who is responsible for the services rendered to the patient.", 0, java.lang.Integer.MAX_VALUE, requestProvider));
        childrenList.add(new Property("requestOrganization", "Reference(Organization)", "The organization which is responsible for the services rendered to the patient.", 0, java.lang.Integer.MAX_VALUE, requestOrganization));
      }

      public ExplanationOfBenefit copy() {
        ExplanationOfBenefit dst = new ExplanationOfBenefit();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.request = request == null ? null : request.copy();
        dst.requestIdentifier = new ArrayList<Identifier>();
        for (Identifier i : requestIdentifier)
          dst.requestIdentifier.add(i.copy());
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.disposition = disposition == null ? null : disposition.copy();
        dst.ruleset = ruleset == null ? null : ruleset.copy();
        dst.originalRuleset = originalRuleset == null ? null : originalRuleset.copy();
        dst.date = date == null ? null : date.copy();
        dst.organization = organization == null ? null : organization.copy();
        dst.requestProvider = requestProvider == null ? null : requestProvider.copy();
        dst.requestOrganization = requestOrganization == null ? null : requestOrganization.copy();
        return dst;
      }

      protected ExplanationOfBenefit typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ExplanationOfBenefit;
   }


}

