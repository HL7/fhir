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


public enum V3MessageCondition {

        /**
         * Success. Optional, as in version 2.x the AA conveys success.  Used for systems that must always return a status code.
         */
        _0, 
        /**
         * The message elements were not in the proper order, or required elements are missing.
         */
        _100, 
        /**
         * A required message element is missing.
         */
        _101, 
        /**
         * An element is represented by a data type that is not compatible with that which is allowable.
         */
        _102, 
        /**
         * The value received for a common HL7 or user defined table was not found to match allowable table values.
         */
        _103, 
        /**
         * The message type is not supported.
         */
        _200, 
        /**
         * The identified interaction is not supported.
         */
        _201, 
        /**
         * The ProcessingID is not supported.
         */
        _202, 
        /**
         * The VersionID is not supported.
         */
        _203, 
        /**
         * The identifier for a patient, order, etc. was not found.  Occurs for transactions other than additions.
         */
        _204, 
        /**
         * The identifier for a patient, order, etc. already exists.  Occurs in response to addition transactions (e.g. new Admit, new Order, etc.).
         */
        _205, 
        /**
         * The transaction could not be performed at the application storage level, e.g. database locked.
         */
        _206, 
        /**
         * A catchall for internal errors not explicitly covered by other error codes.
         */
        _207, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3MessageCondition fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("0".equals(codeString))
          return _0;
        if ("100".equals(codeString))
          return _100;
        if ("101".equals(codeString))
          return _101;
        if ("102".equals(codeString))
          return _102;
        if ("103".equals(codeString))
          return _103;
        if ("200".equals(codeString))
          return _200;
        if ("201".equals(codeString))
          return _201;
        if ("202".equals(codeString))
          return _202;
        if ("203".equals(codeString))
          return _203;
        if ("204".equals(codeString))
          return _204;
        if ("205".equals(codeString))
          return _205;
        if ("206".equals(codeString))
          return _206;
        if ("207".equals(codeString))
          return _207;
        throw new Exception("Unknown V3MessageCondition code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _0: return "0";
            case _100: return "100";
            case _101: return "101";
            case _102: return "102";
            case _103: return "103";
            case _200: return "200";
            case _201: return "201";
            case _202: return "202";
            case _203: return "203";
            case _204: return "204";
            case _205: return "205";
            case _206: return "206";
            case _207: return "207";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/MessageCondition";
        }
        public String getDefinition() {
          switch (this) {
            case _0: return "Success. Optional, as in version 2.x the AA conveys success.  Used for systems that must always return a status code.";
            case _100: return "The message elements were not in the proper order, or required elements are missing.";
            case _101: return "A required message element is missing.";
            case _102: return "An element is represented by a data type that is not compatible with that which is allowable.";
            case _103: return "The value received for a common HL7 or user defined table was not found to match allowable table values.";
            case _200: return "The message type is not supported.";
            case _201: return "The identified interaction is not supported.";
            case _202: return "The ProcessingID is not supported.";
            case _203: return "The VersionID is not supported.";
            case _204: return "The identifier for a patient, order, etc. was not found.  Occurs for transactions other than additions.";
            case _205: return "The identifier for a patient, order, etc. already exists.  Occurs in response to addition transactions (e.g. new Admit, new Order, etc.).";
            case _206: return "The transaction could not be performed at the application storage level, e.g. database locked.";
            case _207: return "A catchall for internal errors not explicitly covered by other error codes.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _0: return "Message accepted";
            case _100: return "Sequence error";
            case _101: return "Missing required element.";
            case _102: return "Data type error";
            case _103: return "Table value not found";
            case _200: return "Unsupported message type";
            case _201: return "Unsupported interaction";
            case _202: return "Unsupported ProcessingID";
            case _203: return "Unsupported VersionID";
            case _204: return "Unknown key identifier";
            case _205: return "Duplicate key identifier";
            case _206: return "Application record locked";
            case _207: return "Application internal error";
            default: return "?";
          }
    }


}

