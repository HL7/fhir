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


public enum V3PostalAddressUse {

        /**
         * AddressUse
         */
        _ADDRESSUSE, 
        /**
         * A flag indicating that the address is bad, in fact, useless.
         */
        BAD, 
        /**
         * A communication address at a home, attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call.  Typically used with urgent cases, or if no other contacts are available.
         */
        H, 
        /**
         * The primary home, to reach a person after business hours.
         */
        HP, 
        /**
         * A vacation home, to reach a person while on vacation.
         */
        HV, 
        /**
         * A temporary address, may be good for visit or mailing.  Note that an address history can provide more detailed information.
         */
        TMP, 
        /**
         * An office address.  First choice for business related contacts during business hours.
         */
        WP, 
        /**
         * Indicates a work place address or telecommunication address that reaches the individual or organization directly without intermediaries. For phones, often referred to as a 'private line'.
         */
        DIR, 
        /**
         * Indicates a work place address or telecommunication address that is a 'standard' address which may reach a reception service, mail-room, or other intermediary prior to the target entity.
         */
        PUB, 
        /**
         * Used primarily to visit an address.
         */
        PHYS, 
        /**
         * Used to send mail.
         */
        PST, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3PostalAddressUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("_AddressUse".equals(codeString))
          return _ADDRESSUSE;
        if ("BAD".equals(codeString))
          return BAD;
        if ("H".equals(codeString))
          return H;
        if ("HP".equals(codeString))
          return HP;
        if ("HV".equals(codeString))
          return HV;
        if ("TMP".equals(codeString))
          return TMP;
        if ("WP".equals(codeString))
          return WP;
        if ("DIR".equals(codeString))
          return DIR;
        if ("PUB".equals(codeString))
          return PUB;
        if ("PHYS".equals(codeString))
          return PHYS;
        if ("PST".equals(codeString))
          return PST;
        throw new Exception("Unknown V3PostalAddressUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _ADDRESSUSE: return "_AddressUse";
            case BAD: return "BAD";
            case H: return "H";
            case HP: return "HP";
            case HV: return "HV";
            case TMP: return "TMP";
            case WP: return "WP";
            case DIR: return "DIR";
            case PUB: return "PUB";
            case PHYS: return "PHYS";
            case PST: return "PST";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/PostalAddressUse";
        }
        public String getDefinition() {
          switch (this) {
            case _ADDRESSUSE: return "AddressUse";
            case BAD: return "A flag indicating that the address is bad, in fact, useless.";
            case H: return "A communication address at a home, attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call.  Typically used with urgent cases, or if no other contacts are available.";
            case HP: return "The primary home, to reach a person after business hours.";
            case HV: return "A vacation home, to reach a person while on vacation.";
            case TMP: return "A temporary address, may be good for visit or mailing.  Note that an address history can provide more detailed information.";
            case WP: return "An office address.  First choice for business related contacts during business hours.";
            case DIR: return "Indicates a work place address or telecommunication address that reaches the individual or organization directly without intermediaries. For phones, often referred to as a 'private line'.";
            case PUB: return "Indicates a work place address or telecommunication address that is a 'standard' address which may reach a reception service, mail-room, or other intermediary prior to the target entity.";
            case PHYS: return "Used primarily to visit an address.";
            case PST: return "Used to send mail.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _ADDRESSUSE: return "AddressUse";
            case BAD: return "bad address";
            case H: return "home address";
            case HP: return "primary home";
            case HV: return "vacation home";
            case TMP: return "temporary address";
            case WP: return "work place";
            case DIR: return "Direct";
            case PUB: return "Public";
            case PHYS: return "physical visit address";
            case PST: return "postal address";
            default: return "?";
          }
    }


}

