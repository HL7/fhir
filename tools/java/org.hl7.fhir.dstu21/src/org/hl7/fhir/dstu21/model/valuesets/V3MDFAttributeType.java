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


public enum V3MDFAttributeType {

        /**
         * For attributes representing the location at which an organization, person, or item may be found or reached.
         */
        ADDR, 
        /**
         * For attributes representing some concept. Note that names of individual things are not considered concepts.
         */
        CD, 
        /**
         * For attributes representing communication addresses, such as telephones, fax, pagers, e-mail, Web-sites and other devices and their respective protocols. See also PHON.
         */
        COM, 
        /**
         * For attributes representing a statement used to describe something.
         */
        DESC, 
        /**
         * For attributes representing a point in time at which an event happened or will happen. Levels of precision and variation are part of this concept and should usually not be specified in separate attributes.
         */
        DTTM, 
        /**
         * For attributes representing formalized text that is to be evaluated primarily by computes. An attribute named "constraint_text" is most likely such a formal expression and should be renamed to "constraint_expr".
         */
        EXPR, 
        /**
         * For attributes that represent a fraction or proportion. The former attribute type PCT for "percentage" is superceded by FRC and is no longer permitted. See also QTY.
         */
        FRC, 
        /**
         * For attributes that serve to identify some instance of an information model class. Note that real world Identifiers (e.g., SSN) exist not as attributes but as an association to a special information model class. The attribute type "id" without a prefix is reserved to be the main instance identifier of the class.
         */
        ID, 
        /**
         * For attributes representing a specific condition as true or false.
         */
        IND, 
        /**
         * For attributes representing dimensionless numbers. Note that there is a big conceptual difference between integer numbers and floating point numbers. See also QTY.
         */
        NBR, 
        /**
         * For attributes that represent a name by which an instance of the class is known.
         */
        NM, 
        /**
         * For attributes representing telephone number of a telecommunication device.  See also COM.
         */
        PHON, 
        /**
         * For attributes representing a quantity. The nature of the quantity must be further specified through the choice of data type and through additional constraints. For physical quantities (including elapsed time) the PQ data type must be used. For monetary amounts the MO data type must be used. Parallel unit attributes are not permitted in these cases. Counted objects are not physical quantities and the count nouns are not units of measure.
         */
        QTY, 
        /**
         * A range of time between a start and an end time having a duration. The range may be infinite or undefined on either side.
         */
        TIME, 
        /**
         * A range of time between a start and an end time having a duration. The range may be infinite or undefined on either side.
         */
        TMR, 
        /**
         * For attributes representing non-descriptive, non-naming text not targeted to human interpretation. Formal expressions evaluated by computers should use the EXPR attribute type instead.
         */
        TXT, 
        /**
         * For an attribute (e.g., Observation.value) that represents a value whose data type is determined dynamically and is not predefined by the static class diagram.
         */
        VALUE, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3MDFAttributeType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ADDR".equals(codeString))
          return ADDR;
        if ("CD".equals(codeString))
          return CD;
        if ("COM".equals(codeString))
          return COM;
        if ("DESC".equals(codeString))
          return DESC;
        if ("DTTM".equals(codeString))
          return DTTM;
        if ("EXPR".equals(codeString))
          return EXPR;
        if ("FRC".equals(codeString))
          return FRC;
        if ("ID".equals(codeString))
          return ID;
        if ("IND".equals(codeString))
          return IND;
        if ("NBR".equals(codeString))
          return NBR;
        if ("NM".equals(codeString))
          return NM;
        if ("PHON".equals(codeString))
          return PHON;
        if ("QTY".equals(codeString))
          return QTY;
        if ("TIME".equals(codeString))
          return TIME;
        if ("TMR".equals(codeString))
          return TMR;
        if ("TXT".equals(codeString))
          return TXT;
        if ("VALUE".equals(codeString))
          return VALUE;
        throw new Exception("Unknown V3MDFAttributeType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ADDR: return "ADDR";
            case CD: return "CD";
            case COM: return "COM";
            case DESC: return "DESC";
            case DTTM: return "DTTM";
            case EXPR: return "EXPR";
            case FRC: return "FRC";
            case ID: return "ID";
            case IND: return "IND";
            case NBR: return "NBR";
            case NM: return "NM";
            case PHON: return "PHON";
            case QTY: return "QTY";
            case TIME: return "TIME";
            case TMR: return "TMR";
            case TXT: return "TXT";
            case VALUE: return "VALUE";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/MDFAttributeType";
        }
        public String getDefinition() {
          switch (this) {
            case ADDR: return "For attributes representing the location at which an organization, person, or item may be found or reached.";
            case CD: return "For attributes representing some concept. Note that names of individual things are not considered concepts.";
            case COM: return "For attributes representing communication addresses, such as telephones, fax, pagers, e-mail, Web-sites and other devices and their respective protocols. See also PHON.";
            case DESC: return "For attributes representing a statement used to describe something.";
            case DTTM: return "For attributes representing a point in time at which an event happened or will happen. Levels of precision and variation are part of this concept and should usually not be specified in separate attributes.";
            case EXPR: return "For attributes representing formalized text that is to be evaluated primarily by computes. An attribute named 'constraint_text' is most likely such a formal expression and should be renamed to 'constraint_expr'.";
            case FRC: return "For attributes that represent a fraction or proportion. The former attribute type PCT for 'percentage' is superceded by FRC and is no longer permitted. See also QTY.";
            case ID: return "For attributes that serve to identify some instance of an information model class. Note that real world Identifiers (e.g., SSN) exist not as attributes but as an association to a special information model class. The attribute type 'id' without a prefix is reserved to be the main instance identifier of the class.";
            case IND: return "For attributes representing a specific condition as true or false.";
            case NBR: return "For attributes representing dimensionless numbers. Note that there is a big conceptual difference between integer numbers and floating point numbers. See also QTY.";
            case NM: return "For attributes that represent a name by which an instance of the class is known.";
            case PHON: return "For attributes representing telephone number of a telecommunication device.  See also COM.";
            case QTY: return "For attributes representing a quantity. The nature of the quantity must be further specified through the choice of data type and through additional constraints. For physical quantities (including elapsed time) the PQ data type must be used. For monetary amounts the MO data type must be used. Parallel unit attributes are not permitted in these cases. Counted objects are not physical quantities and the count nouns are not units of measure.";
            case TIME: return "A range of time between a start and an end time having a duration. The range may be infinite or undefined on either side.";
            case TMR: return "A range of time between a start and an end time having a duration. The range may be infinite or undefined on either side.";
            case TXT: return "For attributes representing non-descriptive, non-naming text not targeted to human interpretation. Formal expressions evaluated by computers should use the EXPR attribute type instead.";
            case VALUE: return "For an attribute (e.g., Observation.value) that represents a value whose data type is determined dynamically and is not predefined by the static class diagram.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ADDR: return "Address";
            case CD: return "Code";
            case COM: return "Communication Address";
            case DESC: return "Description";
            case DTTM: return "Date and Time";
            case EXPR: return "Formal Expression";
            case FRC: return "Fraction";
            case ID: return "Identifier";
            case IND: return "Indicator";
            case NBR: return "Number";
            case NM: return "Name";
            case PHON: return "Phone";
            case QTY: return "Quantity";
            case TIME: return "General Timing";
            case TMR: return "Time Range";
            case TXT: return "Text";
            case VALUE: return "Value";
            default: return "?";
          }
    }


}

