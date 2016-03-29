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

public enum Referralstatus {

        /**
         * A draft referral that has yet to be send.
         */
        DRAFT, 
        /**
         * The referral has been transmitted, but not yet acknowledged by the recipient.
         */
        REQUESTED, 
        /**
         * The referral has been acknowledged by the recipient, and is in the process of being actioned.
         */
        ACTIVE, 
        /**
         * The referral has been cancelled without being completed. For example it is no longer needed.
         */
        CANCELLED, 
        /**
         * The recipient has agreed to deliver the care requested by the referral.
         */
        ACCEPTED, 
        /**
         * The recipient has declined to accept the referral.
         */
        REJECTED, 
        /**
         * The referral has been completely actioned.
         */
        COMPLETED, 
        /**
         * added to help the parsers
         */
        NULL;
        public static Referralstatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("requested".equals(codeString))
          return REQUESTED;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        if ("accepted".equals(codeString))
          return ACCEPTED;
        if ("rejected".equals(codeString))
          return REJECTED;
        if ("completed".equals(codeString))
          return COMPLETED;
        throw new FHIRException("Unknown Referralstatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DRAFT: return "draft";
            case REQUESTED: return "requested";
            case ACTIVE: return "active";
            case CANCELLED: return "cancelled";
            case ACCEPTED: return "accepted";
            case REJECTED: return "rejected";
            case COMPLETED: return "completed";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/referralstatus";
        }
        public String getDefinition() {
          switch (this) {
            case DRAFT: return "A draft referral that has yet to be send.";
            case REQUESTED: return "The referral has been transmitted, but not yet acknowledged by the recipient.";
            case ACTIVE: return "The referral has been acknowledged by the recipient, and is in the process of being actioned.";
            case CANCELLED: return "The referral has been cancelled without being completed. For example it is no longer needed.";
            case ACCEPTED: return "The recipient has agreed to deliver the care requested by the referral.";
            case REJECTED: return "The recipient has declined to accept the referral.";
            case COMPLETED: return "The referral has been completely actioned.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "Draft";
            case REQUESTED: return "Requested";
            case ACTIVE: return "Active";
            case CANCELLED: return "Cancelled";
            case ACCEPTED: return "Accepted";
            case REJECTED: return "Rejected";
            case COMPLETED: return "Completed";
            default: return "?";
          }
    }


}

