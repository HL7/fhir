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


public enum V3MdfHmdRowType {

        /**
         * Identifies an association leading from the "class" (or association) that is most directly above this row
         */
        ASSOC, 
        /**
         * Identifies an attribute of the "class" (or association) that is most directly above this row.
         */
        ATTR, 
        /**
         * Identifies "class" in the HMD. There is only one class entry in a Hierarchical Message Definition. This is the root class for the message.
         */
        CLASS, 
        /**
         * Always the first row of the table, identifies the particular Hierarchical Message Definition in the nomenclature of the HL7 Repository.
         */
        HMD, 
        /**
         * Identifies a message element that represents one of whatever is repeated in a collection
         */
        ITEM, 
        /**
         * This row corresponds to a subcomponent of the row above; it would not normally be included in an HMD, but it is included in order to be able to state a constraint on the subtype. This is explained in 10.2.2.4.
         */
        STC, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3MdfHmdRowType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("assoc".equals(codeString))
          return ASSOC;
        if ("attr".equals(codeString))
          return ATTR;
        if ("class".equals(codeString))
          return CLASS;
        if ("hmd".equals(codeString))
          return HMD;
        if ("item".equals(codeString))
          return ITEM;
        if ("stc".equals(codeString))
          return STC;
        throw new Exception("Unknown V3MdfHmdRowType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ASSOC: return "assoc";
            case ATTR: return "attr";
            case CLASS: return "class";
            case HMD: return "hmd";
            case ITEM: return "item";
            case STC: return "stc";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/MdfHmdRowType";
        }
        public String getDefinition() {
          switch (this) {
            case ASSOC: return "Identifies an association leading from the 'class' (or association) that is most directly above this row";
            case ATTR: return "Identifies an attribute of the 'class' (or association) that is most directly above this row.";
            case CLASS: return "Identifies 'class' in the HMD. There is only one class entry in a Hierarchical Message Definition. This is the root class for the message.";
            case HMD: return "Always the first row of the table, identifies the particular Hierarchical Message Definition in the nomenclature of the HL7 Repository.";
            case ITEM: return "Identifies a message element that represents one of whatever is repeated in a collection";
            case STC: return "This row corresponds to a subcomponent of the row above; it would not normally be included in an HMD, but it is included in order to be able to state a constraint on the subtype. This is explained in 10.2.2.4.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ASSOC: return "Association Row";
            case ATTR: return "Attribute Row";
            case CLASS: return "HMD root class";
            case HMD: return "HMD identifier";
            case ITEM: return "Collection Item Row";
            case STC: return "Subtype constraint";
            default: return "?";
          }
    }


}

