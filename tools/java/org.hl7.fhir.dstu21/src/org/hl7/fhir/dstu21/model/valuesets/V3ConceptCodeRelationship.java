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


public enum V3ConceptCodeRelationship {

        /**
         * The target concept is a part or portion of the source concept.
         */
        HASPART, 
        /**
         * Target concept is a subtype / subclass or the source (or the target concept implies the source
         */
        HASSUBTYPE, 
        /**
         * The source concept code is "less than" the target concept in a strictly ordinal sense.
         */
        SMALLERTHAN, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3ConceptCodeRelationship fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("hasPart".equals(codeString))
          return HASPART;
        if ("hasSubtype".equals(codeString))
          return HASSUBTYPE;
        if ("smallerThan".equals(codeString))
          return SMALLERTHAN;
        throw new Exception("Unknown V3ConceptCodeRelationship code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case HASPART: return "hasPart";
            case HASSUBTYPE: return "hasSubtype";
            case SMALLERTHAN: return "smallerThan";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/ConceptCodeRelationship";
        }
        public String getDefinition() {
          switch (this) {
            case HASPART: return "The target concept is a part or portion of the source concept.";
            case HASSUBTYPE: return "Target concept is a subtype / subclass or the source (or the target concept implies the source";
            case SMALLERTHAN: return "The source concept code is 'less than' the target concept in a strictly ordinal sense.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case HASPART: return "has part";
            case HASSUBTYPE: return "has subtype";
            case SMALLERTHAN: return "is smaller than";
            default: return "?";
          }
    }


}

