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

public enum QicoreAppropriatenessScore {

        /**
         * The procedure is extremely inappropriate
         */
        EXTREMELYINAPPROPRIATE, 
        /**
         * The procedure is inappropriate
         */
        INAPPROPRIATE, 
        /**
         * The procedure is probably inappropriate
         */
        PROBABLYINAPPROPRIATE, 
        /**
         * The appropriateness of the procedure is uncertain, leaning towards inappropriate
         */
        UNCERTAININAPPROPRIATE, 
        /**
         * The appropriateness of the procedure is uncertain
         */
        UNCERTAIN, 
        /**
         * The appropriateness of the procedure is uncertain, leaning towards appropriate
         */
        UNCERTAINAPPROPRIATE, 
        /**
         * The procedure is probably appropriate
         */
        PROBABLYAPPROPRIATE, 
        /**
         * The procedure is appropriate
         */
        APPROPRIATE, 
        /**
         * The procedure is extremely appropriate
         */
        EXTREMELYAPPROPRIATE, 
        /**
         * added to help the parsers
         */
        NULL;
        public static QicoreAppropriatenessScore fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("extremely-inappropriate".equals(codeString))
          return EXTREMELYINAPPROPRIATE;
        if ("inappropriate".equals(codeString))
          return INAPPROPRIATE;
        if ("probably-inappropriate".equals(codeString))
          return PROBABLYINAPPROPRIATE;
        if ("uncertain-inappropriate".equals(codeString))
          return UNCERTAININAPPROPRIATE;
        if ("uncertain".equals(codeString))
          return UNCERTAIN;
        if ("uncertain-appropriate".equals(codeString))
          return UNCERTAINAPPROPRIATE;
        if ("probably-appropriate".equals(codeString))
          return PROBABLYAPPROPRIATE;
        if ("appropriate".equals(codeString))
          return APPROPRIATE;
        if ("extremely-appropriate".equals(codeString))
          return EXTREMELYAPPROPRIATE;
        throw new FHIRException("Unknown QicoreAppropriatenessScore code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case EXTREMELYINAPPROPRIATE: return "extremely-inappropriate";
            case INAPPROPRIATE: return "inappropriate";
            case PROBABLYINAPPROPRIATE: return "probably-inappropriate";
            case UNCERTAININAPPROPRIATE: return "uncertain-inappropriate";
            case UNCERTAIN: return "uncertain";
            case UNCERTAINAPPROPRIATE: return "uncertain-appropriate";
            case PROBABLYAPPROPRIATE: return "probably-appropriate";
            case APPROPRIATE: return "appropriate";
            case EXTREMELYAPPROPRIATE: return "extremely-appropriate";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/appropriateness-score";
        }
        public String getDefinition() {
          switch (this) {
            case EXTREMELYINAPPROPRIATE: return "The procedure is extremely inappropriate";
            case INAPPROPRIATE: return "The procedure is inappropriate";
            case PROBABLYINAPPROPRIATE: return "The procedure is probably inappropriate";
            case UNCERTAININAPPROPRIATE: return "The appropriateness of the procedure is uncertain, leaning towards inappropriate";
            case UNCERTAIN: return "The appropriateness of the procedure is uncertain";
            case UNCERTAINAPPROPRIATE: return "The appropriateness of the procedure is uncertain, leaning towards appropriate";
            case PROBABLYAPPROPRIATE: return "The procedure is probably appropriate";
            case APPROPRIATE: return "The procedure is appropriate";
            case EXTREMELYAPPROPRIATE: return "The procedure is extremely appropriate";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case EXTREMELYINAPPROPRIATE: return "Extremely Inappropriate";
            case INAPPROPRIATE: return "Inappropriate";
            case PROBABLYINAPPROPRIATE: return "Probably Inappropriate";
            case UNCERTAININAPPROPRIATE: return "Uncertain Inappropriate";
            case UNCERTAIN: return "Uncertain";
            case UNCERTAINAPPROPRIATE: return "Uncertain Appropriate";
            case PROBABLYAPPROPRIATE: return "Probably Appropriate";
            case APPROPRIATE: return "Appropriate";
            case EXTREMELYAPPROPRIATE: return "Extremely Appropriate";
            default: return "?";
          }
    }


}

