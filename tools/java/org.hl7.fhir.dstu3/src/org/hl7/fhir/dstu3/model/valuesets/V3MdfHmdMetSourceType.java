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


public enum V3MdfHmdMetSourceType {

        /**
         * This message element type is an HL7 common message element type.
         */
        C, 
        /**
         * This message element type is an HL7 data type.
         */
        D, 
        /**
         * This row represents a reference to a previously defined type.  Such a reference means that, in a given message, the message element instance for the current row is identical to the message element instance that instantiates the referred row.
         */
        I, 
        /**
         * New type. This row starts the definition of a new message element type. The subordinate rows beneath it compose the definition of the type. Each of these subordinate rows has the name of the message element type being defined in the In Message Element Type column. That name will be the same one that is in the Of Message Element Type of this row.
         */
        N, 
        /**
         * This row represents the recursive reuse of the message element type within which is appears. See 10.3.3.3.
         */
        R, 
        /**
         * This message element type was previously defined in this HMD and is being reused
         */
        U, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3MdfHmdMetSourceType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("C".equals(codeString))
          return C;
        if ("D".equals(codeString))
          return D;
        if ("I".equals(codeString))
          return I;
        if ("N".equals(codeString))
          return N;
        if ("R".equals(codeString))
          return R;
        if ("U".equals(codeString))
          return U;
        throw new Exception("Unknown V3MdfHmdMetSourceType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case C: return "C";
            case D: return "D";
            case I: return "I";
            case N: return "N";
            case R: return "R";
            case U: return "U";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/MdfHmdMetSourceType";
        }
        public String getDefinition() {
          switch (this) {
            case C: return "This message element type is an HL7 common message element type.";
            case D: return "This message element type is an HL7 data type.";
            case I: return "This row represents a reference to a previously defined type.  Such a reference means that, in a given message, the message element instance for the current row is identical to the message element instance that instantiates the referred row.";
            case N: return "New type. This row starts the definition of a new message element type. The subordinate rows beneath it compose the definition of the type. Each of these subordinate rows has the name of the message element type being defined in the In Message Element Type column. That name will be the same one that is in the Of Message Element Type of this row.";
            case R: return "This row represents the recursive reuse of the message element type within which is appears. See 10.3.3.3.";
            case U: return "This message element type was previously defined in this HMD and is being reused";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case C: return "Common MET";
            case D: return "Data type";
            case I: return "Reference";
            case N: return "New type";
            case R: return "Recursive";
            case U: return "Re-used MET";
            default: return "?";
          }
    }


}

