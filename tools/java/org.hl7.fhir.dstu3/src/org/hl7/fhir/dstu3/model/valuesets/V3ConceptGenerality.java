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


public enum V3ConceptGenerality {

        /**
         * Include only the descendents of this concept in the domain or value set, not the concept itself.
         */
        A, 
        /**
         * Include only the concept itself in the domain or value set.  Do not include descendents of the concept.
         */
        L, 
        /**
         * Include both the concept and its descendents as possible values in the domain or value set.
         */
        S, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3ConceptGenerality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("A".equals(codeString))
          return A;
        if ("L".equals(codeString))
          return L;
        if ("S".equals(codeString))
          return S;
        throw new Exception("Unknown V3ConceptGenerality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case A: return "A";
            case L: return "L";
            case S: return "S";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/ConceptGenerality";
        }
        public String getDefinition() {
          switch (this) {
            case A: return "Include only the descendents of this concept in the domain or value set, not the concept itself.";
            case L: return "Include only the concept itself in the domain or value set.  Do not include descendents of the concept.";
            case S: return "Include both the concept and its descendents as possible values in the domain or value set.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case A: return "Abstract";
            case L: return "Leaf";
            case S: return "Specializable";
            default: return "?";
          }
    }


}

