package org.hl7.fhir.dstu3.model.codesystems;

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

// Generated on Mon, Mar 28, 2016 15:19-0600 for FHIR v1.3.0


import org.hl7.fhir.dstu3.exceptions.FHIRException;

public enum ActionBehaviorType {

        /**
         * Specifies grouping behavior using the action-group-behavior valueset
         */
        GROUPING, 
        /**
         * Specifies selection behavior using the action-selection-behavior valueset
         */
        SELECTION, 
        /**
         * Specifies required behavior using the action-required-behavior valueset
         */
        REQUIRED, 
        /**
         * Specifies precheck behavior using the action-precheck-behavior valueset
         */
        PRECHECK, 
        /**
         * Specifies cardinality behavior using the action-cardinality behavior valueset
         */
        CARDINALITY, 
        /**
         * added to help the parsers
         */
        NULL;
        public static ActionBehaviorType fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("grouping".equals(codeString))
          return GROUPING;
        if ("selection".equals(codeString))
          return SELECTION;
        if ("required".equals(codeString))
          return REQUIRED;
        if ("precheck".equals(codeString))
          return PRECHECK;
        if ("cardinality".equals(codeString))
          return CARDINALITY;
        throw new FHIRException("Unknown ActionBehaviorType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case GROUPING: return "grouping";
            case SELECTION: return "selection";
            case REQUIRED: return "required";
            case PRECHECK: return "precheck";
            case CARDINALITY: return "cardinality";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/action-behavior-type";
        }
        public String getDefinition() {
          switch (this) {
            case GROUPING: return "Specifies grouping behavior using the action-group-behavior valueset";
            case SELECTION: return "Specifies selection behavior using the action-selection-behavior valueset";
            case REQUIRED: return "Specifies required behavior using the action-required-behavior valueset";
            case PRECHECK: return "Specifies precheck behavior using the action-precheck-behavior valueset";
            case CARDINALITY: return "Specifies cardinality behavior using the action-cardinality behavior valueset";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case GROUPING: return "Grouping Behavior";
            case SELECTION: return "Selection Behavior";
            case REQUIRED: return "Required Behavior";
            case PRECHECK: return "Precheck Behavior";
            case CARDINALITY: return "Cardinality Behavior";
            default: return "?";
          }
    }


}

