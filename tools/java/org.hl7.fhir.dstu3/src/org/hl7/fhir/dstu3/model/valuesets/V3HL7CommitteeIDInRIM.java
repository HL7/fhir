package org.hl7.fhir.dstu3.model.valuesets;

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


public enum V3HL7CommitteeIDInRIM {

        /**
         * Modeling and Methodology
         */
        C00, 
        /**
         * Technical Steering Committee
         */
        C01, 
        /**
         * Control/Query/MasterFiles
         */
        C02, 
        /**
         * Patient Administration
         */
        C03, 
        /**
         * Orders/Observation
         */
        C04, 
        /**
         * Financial Management
         */
        C06, 
        /**
         * Information Management (Medical Records)
         */
        C09, 
        /**
         * Scheduling & Referral
         */
        C10, 
        /**
         * Patient Care
         */
        C12, 
        /**
         * PRA Technical Committee
         */
        C20, 
        /**
         * PRA Technical Committee
         */
        C21, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3HL7CommitteeIDInRIM fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("C00".equals(codeString))
          return C00;
        if ("C01".equals(codeString))
          return C01;
        if ("C02".equals(codeString))
          return C02;
        if ("C03".equals(codeString))
          return C03;
        if ("C04".equals(codeString))
          return C04;
        if ("C06".equals(codeString))
          return C06;
        if ("C09".equals(codeString))
          return C09;
        if ("C10".equals(codeString))
          return C10;
        if ("C12".equals(codeString))
          return C12;
        if ("C20".equals(codeString))
          return C20;
        if ("C21".equals(codeString))
          return C21;
        throw new Exception("Unknown V3HL7CommitteeIDInRIM code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case C00: return "C00";
            case C01: return "C01";
            case C02: return "C02";
            case C03: return "C03";
            case C04: return "C04";
            case C06: return "C06";
            case C09: return "C09";
            case C10: return "C10";
            case C12: return "C12";
            case C20: return "C20";
            case C21: return "C21";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/HL7CommitteeIDInRIM";
        }
        public String getDefinition() {
          switch (this) {
            case C00: return "Modeling and Methodology";
            case C01: return "Technical Steering Committee";
            case C02: return "Control/Query/MasterFiles";
            case C03: return "Patient Administration";
            case C04: return "Orders/Observation";
            case C06: return "Financial Management";
            case C09: return "Information Management (Medical Records)";
            case C10: return "Scheduling & Referral";
            case C12: return "Patient Care";
            case C20: return "PRA Technical Committee";
            case C21: return "PRA Technical Committee";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case C00: return "Modeling and Methodology";
            case C01: return "Technical Steering Committee";
            case C02: return "Control/Query/MasterFiles";
            case C03: return "Patient Administration";
            case C04: return "Orders/Observation";
            case C06: return "Financial Management";
            case C09: return "Medical records";
            case C10: return "Scheduling";
            case C12: return "Patient Care";
            case C20: return "Structured Document Committee";
            case C21: return "Vocabulary Committee";
            default: return "?";
          }
    }


}

