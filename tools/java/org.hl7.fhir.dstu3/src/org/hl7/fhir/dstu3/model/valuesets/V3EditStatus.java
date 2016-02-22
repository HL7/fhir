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


public enum V3EditStatus {

        /**
         * The item has been reviewed and approved and is now valid for use in HL7 standards and messages.
         */
        A, 
        /**
         * A previously active item has been retired from use.  The item may exist as previously stored data in a database, but no current or new HL7 standards or messges should use this item.  The code for an inactive item will not be reused by HL7.
         */
        I, 
        /**
         * A previously active item that is scheduled to be retired from use, but for practical reasons can not be immediately inactivated.  It is still legal to use an obsolete item in HL7 standards or messages, but those creating new messages should make every effort to use an active concept instead.
         */
        O, 
        /**
         * The item has been suggested for incorporation but has not been officially approved.  The item is not yet legal for use in HL7 standards and messages.
         */
        P, 
        /**
         * The item has been reviewed and rejected. The item is not legal for use in HL7 standards and messages.
         */
        R, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3EditStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("A".equals(codeString))
          return A;
        if ("I".equals(codeString))
          return I;
        if ("O".equals(codeString))
          return O;
        if ("P".equals(codeString))
          return P;
        if ("R".equals(codeString))
          return R;
        throw new Exception("Unknown V3EditStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case A: return "A";
            case I: return "I";
            case O: return "O";
            case P: return "P";
            case R: return "R";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/EditStatus";
        }
        public String getDefinition() {
          switch (this) {
            case A: return "The item has been reviewed and approved and is now valid for use in HL7 standards and messages.";
            case I: return "A previously active item has been retired from use.  The item may exist as previously stored data in a database, but no current or new HL7 standards or messges should use this item.  The code for an inactive item will not be reused by HL7.";
            case O: return "A previously active item that is scheduled to be retired from use, but for practical reasons can not be immediately inactivated.  It is still legal to use an obsolete item in HL7 standards or messages, but those creating new messages should make every effort to use an active concept instead.";
            case P: return "The item has been suggested for incorporation but has not been officially approved.  The item is not yet legal for use in HL7 standards and messages.";
            case R: return "The item has been reviewed and rejected. The item is not legal for use in HL7 standards and messages.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case A: return "Active";
            case I: return "Inactive";
            case O: return "Obsolete";
            case P: return "Proposed";
            case R: return "Rejected";
            default: return "?";
          }
    }


}

