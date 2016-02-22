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


public enum V3MDFSubjectAreaPrefix {

        /**
         * Holds classes that are of interest to the committee identified by the rest of the Subject Area Name.
         */
        COI, 
        /**
         * Holds a sub-set of the RIM that is the domain information model for the committee identified by the rest of the Subject Area name.
         */
        DIM, 
        /**
         * Is one of a set of subject areas that hold the classes defined in the Reference Information Model (RIM).
         */
        RIM, 
        /**
         * Holds classes whose steward is the committee identified by the remaining portions of the Subject Area Name.
         */
        STW, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3MDFSubjectAreaPrefix fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("COI".equals(codeString))
          return COI;
        if ("DIM".equals(codeString))
          return DIM;
        if ("RIM".equals(codeString))
          return RIM;
        if ("STW".equals(codeString))
          return STW;
        throw new Exception("Unknown V3MDFSubjectAreaPrefix code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case COI: return "COI";
            case DIM: return "DIM";
            case RIM: return "RIM";
            case STW: return "STW";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/MDFSubjectAreaPrefix";
        }
        public String getDefinition() {
          switch (this) {
            case COI: return "Holds classes that are of interest to the committee identified by the rest of the Subject Area Name.";
            case DIM: return "Holds a sub-set of the RIM that is the domain information model for the committee identified by the rest of the Subject Area name.";
            case RIM: return "Is one of a set of subject areas that hold the classes defined in the Reference Information Model (RIM).";
            case STW: return "Holds classes whose steward is the committee identified by the remaining portions of the Subject Area Name.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case COI: return "Class of Interest";
            case DIM: return "Domain model";
            case RIM: return "RIM content";
            case STW: return "Stewardship";
            default: return "?";
          }
    }


}

