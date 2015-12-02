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


public enum V3MdfRmimRowType {

        /**
         * Identifies an association leading from the class that is most directly above this row
         */
        ASSOC, 
        /**
         * Identifies an attribute of the "class" that is most directly above this row
         */
        ATTR, 
        /**
         * Identifies a "class" in the Refined Message Information Model
         */
        CLASS, 
        /**
         * Always the first row of the table, identifies the particular Refined Message Information Model in the nomenclature of the HL7 Repository
         */
        RMIM, 
        /**
         * This row corresponds to a subcomponent of the row above; it would not normally be included in an Refined Message Information Model, but it is included in order to be able to state a constraint on the subtype. This is explained in 10.2.2.4.
         */
        STC, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3MdfRmimRowType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("assoc".equals(codeString))
          return ASSOC;
        if ("attr".equals(codeString))
          return ATTR;
        if ("class".equals(codeString))
          return CLASS;
        if ("rmim".equals(codeString))
          return RMIM;
        if ("stc".equals(codeString))
          return STC;
        throw new Exception("Unknown V3MdfRmimRowType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ASSOC: return "assoc";
            case ATTR: return "attr";
            case CLASS: return "class";
            case RMIM: return "rmim";
            case STC: return "stc";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/MdfRmimRowType";
        }
        public String getDefinition() {
          switch (this) {
            case ASSOC: return "Identifies an association leading from the class that is most directly above this row";
            case ATTR: return "Identifies an attribute of the 'class' that is most directly above this row";
            case CLASS: return "Identifies a 'class' in the Refined Message Information Model";
            case RMIM: return "Always the first row of the table, identifies the particular Refined Message Information Model in the nomenclature of the HL7 Repository";
            case STC: return "This row corresponds to a subcomponent of the row above; it would not normally be included in an Refined Message Information Model, but it is included in order to be able to state a constraint on the subtype. This is explained in 10.2.2.4.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ASSOC: return "Association row";
            case ATTR: return "Attribute row";
            case CLASS: return "Class row";
            case RMIM: return "RMIM identifier";
            case STC: return "Subtype constraint";
            default: return "?";
          }
    }


}

