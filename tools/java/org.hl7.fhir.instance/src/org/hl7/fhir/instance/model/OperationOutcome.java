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

// Generated on Fri, Oct 18, 2013 12:16+1100 for FHIR v0.12

import java.util.*;

/**
 * A collection of error, warning or information messages that result from a system action.
 */
public class OperationOutcome extends Resource {

    public enum IssueSeverity {
        fatal, // The issue caused the action to fail, and no further checking could be performed.
        error, // The issue is sufficiently important to cause the action to fail.
        warning, // The issue is not important enough to cause the action to fail, but may cause it to be performed suboptimally or in a way that is not as desired.
        information, // The issue has no relation to the degree of success of the action.
        Null; // added to help the parsers
        public static IssueSeverity fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("fatal".equals(codeString))
          return fatal;
        if ("error".equals(codeString))
          return error;
        if ("warning".equals(codeString))
          return warning;
        if ("information".equals(codeString))
          return information;
        throw new Exception("Unknown IssueSeverity code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case fatal: return "fatal";
            case error: return "error";
            case warning: return "warning";
            case information: return "information";
            default: return "?";
          }
        }
    }

  public class IssueSeverityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("fatal".equals(codeString))
          return IssueSeverity.fatal;
        if ("error".equals(codeString))
          return IssueSeverity.error;
        if ("warning".equals(codeString))
          return IssueSeverity.warning;
        if ("information".equals(codeString))
          return IssueSeverity.information;
        throw new Exception("Unknown IssueSeverity code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == IssueSeverity.fatal)
        return "fatal";
      if (code == IssueSeverity.error)
        return "error";
      if (code == IssueSeverity.warning)
        return "warning";
      if (code == IssueSeverity.information)
        return "information";
      return "?";
      }
    }

    public class OperationOutcomeIssueComponent extends Element {
        /**
         * Indicates whether the issue indicates a variation from successful processing.
         */
        protected Enumeration<IssueSeverity> severity;

        /**
         * A code indicating the type of error, warning or information message.
         */
        protected Coding type;

        /**
         * Additional description of the issue.
         */
        protected String_ details;

        /**
         * A simple XPath limited to element names, repetition indicators and the default child access that identifies one of the elements in the resource that caused this issue to be raised.
         */
        protected List<String_> location = new ArrayList<String_>();

        public Enumeration<IssueSeverity> getSeverity() { 
          return this.severity;
        }

        public void setSeverity(Enumeration<IssueSeverity> value) { 
          this.severity = value;
        }

        public IssueSeverity getSeveritySimple() { 
          return this.severity == null ? null : this.severity.getValue();
        }

        public void setSeveritySimple(IssueSeverity value) { 
            if (this.severity == null)
              this.severity = new Enumeration<IssueSeverity>();
            this.severity.setValue(value);
        }

        public Coding getType() { 
          return this.type;
        }

        public void setType(Coding value) { 
          this.type = value;
        }

        public String_ getDetails() { 
          return this.details;
        }

        public void setDetails(String_ value) { 
          this.details = value;
        }

        public String getDetailsSimple() { 
          return this.details == null ? null : this.details.getValue();
        }

        public void setDetailsSimple(String value) { 
          if (value == null)
            this.details = null;
          else {
            if (this.details == null)
              this.details = new String_();
            this.details.setValue(value);
          }
        }

        public List<String_> getLocation() { 
          return this.location;
        }

    // syntactic sugar
        public String_ addLocation() { 
          String_ t = new String_();
          this.location.add(t);
          return t;
        }

        public String_ addLocationSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.location.add(t);
          return t;
        }

      public OperationOutcomeIssueComponent copy(OperationOutcome e) {
        OperationOutcomeIssueComponent dst = e.new OperationOutcomeIssueComponent();
        dst.severity = severity == null ? null : severity.copy();
        dst.type = type == null ? null : type.copy();
        dst.details = details == null ? null : details.copy();
        dst.location = new ArrayList<String_>();
        for (String_ i : location)
          dst.location.add(i.copy());
        return dst;
      }

  }

    /**
     * An error, warning or information message that results from a system action.
     */
    protected List<OperationOutcomeIssueComponent> issue = new ArrayList<OperationOutcomeIssueComponent>();

    public List<OperationOutcomeIssueComponent> getIssue() { 
      return this.issue;
    }

    // syntactic sugar
    public OperationOutcomeIssueComponent addIssue() { 
      OperationOutcomeIssueComponent t = new OperationOutcomeIssueComponent();
      this.issue.add(t);
      return t;
    }

      public OperationOutcome copy() {
        OperationOutcome dst = new OperationOutcome();
        dst.issue = new ArrayList<OperationOutcomeIssueComponent>();
        for (OperationOutcomeIssueComponent i : issue)
          dst.issue.add(i.copy(dst));
        return dst;
      }

      protected OperationOutcome typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.OperationOutcome;
   }


}

