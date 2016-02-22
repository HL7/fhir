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


public enum V3ParameterizedDataType {

        /**
         * Bag
         */
        BAG_T_, 
        /**
         * Sequence
         */
        LIST_T_, 
        /**
         * Set
         */
        SET_T_, 
        /**
         * Event Related Interval
         */
        EIVL_T_, 
        /**
         * Interval
         */
        IVL_T_, 
        /**
         * Periodic Interval
         */
        PIVL_T_, 
        /**
         * Type
         */
        T, 
        /**
         * Annotated
         */
        ANT_T_, 
        /**
         * Historical
         */
        HXIT_T_, 
        /**
         * Non Parametric Probability Distribution
         */
        NPPD_T_, 
        /**
         * Parametric Probability Distribution
         */
        PPD_T_, 
        /**
         * Uncertain Value Narrative
         */
        UVN_T_, 
        /**
         * Uncertain Value Probabilistic
         */
        UVP_T_, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3ParameterizedDataType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("BAG<T>".equals(codeString))
          return BAG_T_;
        if ("LIST<T>".equals(codeString))
          return LIST_T_;
        if ("SET<T>".equals(codeString))
          return SET_T_;
        if ("EIVL<T>".equals(codeString))
          return EIVL_T_;
        if ("IVL<T>".equals(codeString))
          return IVL_T_;
        if ("PIVL<T>".equals(codeString))
          return PIVL_T_;
        if ("T".equals(codeString))
          return T;
        if ("ANT<T>".equals(codeString))
          return ANT_T_;
        if ("HXIT<T>".equals(codeString))
          return HXIT_T_;
        if ("NPPD<T>".equals(codeString))
          return NPPD_T_;
        if ("PPD<T>".equals(codeString))
          return PPD_T_;
        if ("UVN<T>".equals(codeString))
          return UVN_T_;
        if ("UVP<T>".equals(codeString))
          return UVP_T_;
        throw new Exception("Unknown V3ParameterizedDataType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case BAG_T_: return "BAG<T>";
            case LIST_T_: return "LIST<T>";
            case SET_T_: return "SET<T>";
            case EIVL_T_: return "EIVL<T>";
            case IVL_T_: return "IVL<T>";
            case PIVL_T_: return "PIVL<T>";
            case T: return "T";
            case ANT_T_: return "ANT<T>";
            case HXIT_T_: return "HXIT<T>";
            case NPPD_T_: return "NPPD<T>";
            case PPD_T_: return "PPD<T>";
            case UVN_T_: return "UVN<T>";
            case UVP_T_: return "UVP<T>";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/ParameterizedDataType";
        }
        public String getDefinition() {
          switch (this) {
            case BAG_T_: return "Bag";
            case LIST_T_: return "Sequence";
            case SET_T_: return "Set";
            case EIVL_T_: return "Event Related Interval";
            case IVL_T_: return "Interval";
            case PIVL_T_: return "Periodic Interval";
            case T: return "Type";
            case ANT_T_: return "Annotated";
            case HXIT_T_: return "Historical";
            case NPPD_T_: return "Non Parametric Probability Distribution";
            case PPD_T_: return "Parametric Probability Distribution";
            case UVN_T_: return "Uncertain Value Narrative";
            case UVP_T_: return "Uncertain Value Probabilistic";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case BAG_T_: return "Bag";
            case LIST_T_: return "Sequence";
            case SET_T_: return "Set";
            case EIVL_T_: return "Event Related Interval";
            case IVL_T_: return "Interval";
            case PIVL_T_: return "Periodic Interval";
            case T: return "Type";
            case ANT_T_: return "Annotated";
            case HXIT_T_: return "Historical";
            case NPPD_T_: return "Non Parametric Probability Distribution";
            case PPD_T_: return "Parametric Probability Distribution";
            case UVN_T_: return "Uncertain Value Narrative";
            case UVP_T_: return "Uncertain Value Probabilistic";
            default: return "?";
          }
    }


}

