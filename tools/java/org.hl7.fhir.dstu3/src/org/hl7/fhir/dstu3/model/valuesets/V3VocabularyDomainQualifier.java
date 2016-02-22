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


public enum V3VocabularyDomainQualifier {

        /**
         * The extensibility of coding determines whether or not extensions are allowed in the domain of a coded attribute.
         */
        _EXTENSIBILITY, 
        /**
         * The coded attribute allows only concepts from the defined value set. No extensions are allowed.
         */
        CNE, 
        /**
         * The coded attribute allows local codes or user entered text to be sent when the concept that the user would like to express is not a member of the defined value set.  If the concept that the user wants to express is represented by a code in the standard value set, the standard code must be sent.  The local code can be sent in addition to the standard code if desired.  Only if the concept that the user wants to express is NOT represented by a standard code, can a solitary local code be sent.
         */
        CWE, 
        /**
         * The jurisdiction or realm within which the domain will be used. A realm might be a country, a group of countries, a region of the world, or an organization.
         */
        _REALMOFUSE, 
        /**
         * Canada
         */
        CANADA, 
        /**
         * North America
         */
        NORTHAMERICA, 
        /**
         * United States
         */
        USA, 
        /**
         * The root or universal HL7 jurisdiction or realm from which all subsequent specializations are derived.
         */
        UV, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3VocabularyDomainQualifier fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("_Extensibility".equals(codeString))
          return _EXTENSIBILITY;
        if ("CNE".equals(codeString))
          return CNE;
        if ("CWE".equals(codeString))
          return CWE;
        if ("_RealmOfUse".equals(codeString))
          return _REALMOFUSE;
        if ("Canada".equals(codeString))
          return CANADA;
        if ("NorthAmerica".equals(codeString))
          return NORTHAMERICA;
        if ("USA".equals(codeString))
          return USA;
        if ("UV".equals(codeString))
          return UV;
        throw new Exception("Unknown V3VocabularyDomainQualifier code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case _EXTENSIBILITY: return "_Extensibility";
            case CNE: return "CNE";
            case CWE: return "CWE";
            case _REALMOFUSE: return "_RealmOfUse";
            case CANADA: return "Canada";
            case NORTHAMERICA: return "NorthAmerica";
            case USA: return "USA";
            case UV: return "UV";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/VocabularyDomainQualifier";
        }
        public String getDefinition() {
          switch (this) {
            case _EXTENSIBILITY: return "The extensibility of coding determines whether or not extensions are allowed in the domain of a coded attribute.";
            case CNE: return "The coded attribute allows only concepts from the defined value set. No extensions are allowed.";
            case CWE: return "The coded attribute allows local codes or user entered text to be sent when the concept that the user would like to express is not a member of the defined value set.  If the concept that the user wants to express is represented by a code in the standard value set, the standard code must be sent.  The local code can be sent in addition to the standard code if desired.  Only if the concept that the user wants to express is NOT represented by a standard code, can a solitary local code be sent.";
            case _REALMOFUSE: return "The jurisdiction or realm within which the domain will be used. A realm might be a country, a group of countries, a region of the world, or an organization.";
            case CANADA: return "Canada";
            case NORTHAMERICA: return "North America";
            case USA: return "United States";
            case UV: return "The root or universal HL7 jurisdiction or realm from which all subsequent specializations are derived.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case _EXTENSIBILITY: return "Extensibility";
            case CNE: return "Coded No Extensions";
            case CWE: return "Coded With Extensions";
            case _REALMOFUSE: return "RealmOfUse";
            case CANADA: return "Canada";
            case NORTHAMERICA: return "North America";
            case USA: return "United States";
            case UV: return "Universal";
            default: return "?";
          }
    }


}

