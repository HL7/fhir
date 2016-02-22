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


public enum V3ConceptProperty {

        /**
         * Property identifiers for a value sets
         */
        _VALUESETPROPERTYID, 
        /**
         * Entity to which a given value set or concept code applies
         */
        APPLIESTO, 
        /**
         * Description of how set or code applies
         */
        HOWAPPLIES, 
        /**
         * Outstanding issue that still needs resolution
         */
        OPENISSUE, 
        /**
         * Description: Qualifier value for concept status.
         */
        CONCEPTSTATUSQUALIFIER, 
        /**
         * Name that should be used when the relationship is used in the reverse (target to source) direction.  Example: The inverse relationship of hasPart is isPartOf
         */
        INVERSERELATIONSHIP, 
        /**
         * Object identifier assigned to a concept
         */
        OID, 
        /**
         * Description:specialized by domain
         */
        SPECIALIZEDBYDOMAIN, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3ConceptProperty fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("_ValueSetPropertyId".equals(codeString))
          return _VALUESETPROPERTYID;
        if ("appliesTo".equals(codeString))
          return APPLIESTO;
        if ("howApplies".equals(codeString))
          return HOWAPPLIES;
        if ("openIssue".equals(codeString))
          return OPENISSUE;
        if ("conceptStatusQualifier".equals(codeString))
          return CONCEPTSTATUSQUALIFIER;
        if ("inverseRelationship".equals(codeString))
          return INVERSERELATIONSHIP;
        if ("OID".equals(codeString))
          return OID;
        if ("specializedByDomain".equals(codeString))
          return SPECIALIZEDBYDOMAIN;
        throw new Exception("Unknown V3ConceptProperty code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _VALUESETPROPERTYID: return "_ValueSetPropertyId";
            case APPLIESTO: return "appliesTo";
            case HOWAPPLIES: return "howApplies";
            case OPENISSUE: return "openIssue";
            case CONCEPTSTATUSQUALIFIER: return "conceptStatusQualifier";
            case INVERSERELATIONSHIP: return "inverseRelationship";
            case OID: return "OID";
            case SPECIALIZEDBYDOMAIN: return "specializedByDomain";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/ConceptProperty";
        }
        public String getDefinition() {
          switch (this) {
            case _VALUESETPROPERTYID: return "Property identifiers for a value sets";
            case APPLIESTO: return "Entity to which a given value set or concept code applies";
            case HOWAPPLIES: return "Description of how set or code applies";
            case OPENISSUE: return "Outstanding issue that still needs resolution";
            case CONCEPTSTATUSQUALIFIER: return "Description: Qualifier value for concept status.";
            case INVERSERELATIONSHIP: return "Name that should be used when the relationship is used in the reverse (target to source) direction.  Example: The inverse relationship of hasPart is isPartOf";
            case OID: return "Object identifier assigned to a concept";
            case SPECIALIZEDBYDOMAIN: return "Description:specialized by domain";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _VALUESETPROPERTYID: return "ValueSetPropertyId";
            case APPLIESTO: return "applies to";
            case HOWAPPLIES: return "how applies";
            case OPENISSUE: return "open issue";
            case CONCEPTSTATUSQUALIFIER: return "concept status qualifier";
            case INVERSERELATIONSHIP: return "inverted relationship name";
            case OID: return "ISO Object Identifier";
            case SPECIALIZEDBYDOMAIN: return "specialized by domain";
            default: return "?";
          }
    }


}

