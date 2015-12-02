package org.hl7.fhir.dstu21.model.valuesets;

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

// Generated on Sat, Jun 20, 2015 11:30+1000 for FHIR v0.5.0


public enum V3HL7ConformanceInclusion {

        /**
         * The message element is not mandatory, but its appearance may be constrained by one of the non-mandatory concepts.
         */
        _INCLUSIONNOTMANDATORY, 
        /**
         * This message element may not appear when the message is communicated.
         */
        NP, 
        /**
         * The message element may be populated or used by one system sponsor (or profile), but not by another.  Each system sponsor or profile is required to state the ability to accept or send the message element as part of a conformance claim.
         */
        NR, 
        /**
         * Pursuant to a profile or vendor conformance claim, the message element must appear every time the message is communicated, but the value may be communicated as null.
         */
        RE, 
        /**
         * Pursuant to a profile or vendor conformance claim, this message element may not appear when the message is communicated.
         */
        X, 
        /**
         * The message element must appear every time the message is communicated, but the value may be communicated as null.
         */
        RQ, 
        /**
         * The message element must appear every time the message is communicated and its value must not be null.  This condition is subject to the rules of multiplicity and conditionality. If a non-null default value is defined for the element, a null value may be communicated.
         */
        M, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3HL7ConformanceInclusion fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("_InclusionNotMandatory".equals(codeString))
          return _INCLUSIONNOTMANDATORY;
        if ("NP".equals(codeString))
          return NP;
        if ("NR".equals(codeString))
          return NR;
        if ("RE".equals(codeString))
          return RE;
        if ("X".equals(codeString))
          return X;
        if ("RQ".equals(codeString))
          return RQ;
        if ("M".equals(codeString))
          return M;
        throw new Exception("Unknown V3HL7ConformanceInclusion code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _INCLUSIONNOTMANDATORY: return "_InclusionNotMandatory";
            case NP: return "NP";
            case NR: return "NR";
            case RE: return "RE";
            case X: return "X";
            case RQ: return "RQ";
            case M: return "M";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/HL7ConformanceInclusion";
        }
        public String getDefinition() {
          switch (this) {
            case _INCLUSIONNOTMANDATORY: return "The message element is not mandatory, but its appearance may be constrained by one of the non-mandatory concepts.";
            case NP: return "This message element may not appear when the message is communicated.";
            case NR: return "The message element may be populated or used by one system sponsor (or profile), but not by another.  Each system sponsor or profile is required to state the ability to accept or send the message element as part of a conformance claim.";
            case RE: return "Pursuant to a profile or vendor conformance claim, the message element must appear every time the message is communicated, but the value may be communicated as null.";
            case X: return "Pursuant to a profile or vendor conformance claim, this message element may not appear when the message is communicated.";
            case RQ: return "The message element must appear every time the message is communicated, but the value may be communicated as null.";
            case M: return "The message element must appear every time the message is communicated and its value must not be null.  This condition is subject to the rules of multiplicity and conditionality. If a non-null default value is defined for the element, a null value may be communicated.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _INCLUSIONNOTMANDATORY: return "InclusionNotMandatory";
            case NP: return "Not permitted";
            case NR: return "Not required";
            case RE: return "Required may be empty";
            case X: return "Excluded";
            case RQ: return "Required";
            case M: return "Mandatory";
            default: return "?";
          }
    }


}

