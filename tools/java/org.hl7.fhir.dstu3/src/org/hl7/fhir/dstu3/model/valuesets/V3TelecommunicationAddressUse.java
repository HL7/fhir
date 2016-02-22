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


public enum V3TelecommunicationAddressUse {

        /**
         * An automated answering machine used for less urgent cases and if the main purpose of contact is to leave a message or access an automated announcement.
         */
        AS, 
        /**
         * A contact specifically designated to be used for emergencies.  This is the first choice in emergencies, independent of any other use codes.
         */
        EC, 
        /**
         * A telecommunication device that moves and stays with its owner.  May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business.
         */
        MC, 
        /**
         * A paging device suitable to solicit a callback or to leave a very short message.
         */
        PG, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3TelecommunicationAddressUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AS".equals(codeString))
          return AS;
        if ("EC".equals(codeString))
          return EC;
        if ("MC".equals(codeString))
          return MC;
        if ("PG".equals(codeString))
          return PG;
        throw new Exception("Unknown V3TelecommunicationAddressUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case AS: return "AS";
            case EC: return "EC";
            case MC: return "MC";
            case PG: return "PG";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/TelecommunicationAddressUse";
        }
        public String getDefinition() {
          switch (this) {
            case AS: return "An automated answering machine used for less urgent cases and if the main purpose of contact is to leave a message or access an automated announcement.";
            case EC: return "A contact specifically designated to be used for emergencies.  This is the first choice in emergencies, independent of any other use codes.";
            case MC: return "A telecommunication device that moves and stays with its owner.  May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business.";
            case PG: return "A paging device suitable to solicit a callback or to leave a very short message.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case AS: return "answering service";
            case EC: return "emergency contact";
            case MC: return "mobile contact";
            case PG: return "pager";
            default: return "?";
          }
    }


}

