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


public enum V3DataType {

        /**
         * ANY
         */
        ANY, 
        /**
         * Annotated
         */
        ANT_ANY_, 
        /**
         * Annotated Concept Descriptor
         */
        ANT_CD_, 
        /**
         * Annotated Physical Quantity
         */
        ANT_PQ_, 
        /**
         * Bag
         */
        BAG_ANY_, 
        /**
         * Bag Of Concept Descriptors
         */
        BAG_CD_, 
        /**
         * Bag Of Physical Quantities
         */
        BAG_PQ_, 
        /**
         * Boolean
         */
        BL, 
        /**
         * Concept Descriptor
         */
        CD, 
        /**
         * Coded With Equivalents
         */
        CE, 
        /**
         * Coded Simple Value
         */
        CS, 
        /**
         * Coded Value
         */
        CV, 
        /**
         * Concept Role
         */
        CR, 
        /**
         * Historical
         */
        HXIT_ANY_, 
        /**
         * Historical Address
         */
        HXIT_AD_, 
        /**
         * Instance Identifier
         */
        II, 
        /**
         * Interval
         */
        IVL_QTY_, 
        /**
         * Interval Of Integer Numbers
         */
        IVL_INT_, 
        /**
         * Interval Of Physical Quantities
         */
        IVL_PQ_, 
        /**
         * Uncertain Probabilistic Interval Of Physical Quant
         */
        UVP_IVL_PQ__, 
        /**
         * Interval Of Real Numbers
         */
        IVL_REAL_, 
        /**
         * Interval Of Points In Time
         */
        IVL_TS_, 
        /**
         * Set Of Points In Time
         */
        SET_TS_, 
        /**
         * Event Related Interval
         */
        EIVL_TS_, 
        /**
         * General Timing Specification
         */
        GTS, 
        /**
         * Periodic Interval Of Time
         */
        PIVL_TS_, 
        /**
         * Sequence
         */
        LIST_ANY_, 
        /**
         * Sequence Of Booleans
         */
        LIST_BL_, 
        /**
         * Binary Data
         */
        BIN, 
        /**
         * Encoded Data
         */
        ED, 
        /**
         * Character String
         */
        ST, 
        /**
         * Address Part
         */
        ADXP, 
        /**
         * Organization Name
         */
        ON, 
        /**
         * Person Name Part
         */
        PNXP, 
        /**
         * Sequence Of Sequences Of Data Values
         */
        LIST_LIST_ANY__, 
        /**
         * Sequence Of Sequence Of Booleans
         */
        LIST_LIST_BL__, 
        /**
         * Sequence Of Binary Data
         */
        LIST_BIN_, 
        /**
         * Sequence Of Encoded Data
         */
        LIST_ED_, 
        /**
         * Sequence Of Character Strings
         */
        LIST_ST_, 
        /**
         * Sequence Of Postal Address Parts
         */
        LIST_ADXP_, 
        /**
         * Postal And Residential Address
         */
        AD, 
        /**
         * Sequence Of Person Name Parts
         */
        LIST_PNXP_, 
        /**
         * Person Name Type
         */
        PN, 
        /**
         * Object Identifier
         */
        OID, 
        /**
         * Quantity
         */
        QTY, 
        /**
         * Integer Number
         */
        INT, 
        /**
         * Monetary Amount
         */
        MO, 
        /**
         * Parametric Probability Distribution
         */
        PPD_QTY_, 
        /**
         * Physical Quantity
         */
        PQ, 
        /**
         * Parametric Probability Distribution Of Physical Quantity
         */
        PPD_PQ_, 
        /**
         * Real Number
         */
        REAL, 
        /**
         * Parametric Probability Distribution Of Real Number
         */
        PPD_REAL_, 
        /**
         * Ratio
         */
        RTO, 
        /**
         * Point In Time
         */
        TS, 
        /**
         * Set
         */
        SET_ANY_, 
        /**
         * Set Of Concept Descriptors
         */
        SET_CD_, 
        /**
         * Set Of Uncertain Probabilistic Concept Descriptor
         */
        SET_UVP_CD__, 
        /**
         * Non Parametric Probability Distribution Of Concept Descriptors
         */
        NPPD_CD_, 
        /**
         * Set Of Coded With Equivalents
         */
        SET_CE_, 
        /**
         * Set Of Coded Simple Value
         */
        SET_CS_, 
        /**
         * Set Of Coded Value
         */
        SET_CV_, 
        /**
         * Set Of Intervals Of Physical Quantity
         */
        SET_IVL_PQ__, 
        /**
         * Set Of Uncertain Probabilistic Interval Of Physical Quantities
         */
        SET_UVP_IVL_PQ___, 
        /**
         * Non Parametric Probability Distribution Of Intervals of Physical Quantities
         */
        NPPD_IVL_PQ__, 
        /**
         * Set Of Sequences Of Character Strings
         */
        SET_LIST_ST__, 
        /**
         * Set Of Addresses
         */
        SET_AD_, 
        /**
         * Set Of Historical Addresses
         */
        SET_HXIT_AD__, 
        /**
         * History Of Address
         */
        HIST_AD_, 
        /**
         * Set Of Physical Quantities
         */
        SET_PQ_, 
        /**
         * Set Of Character Strings
         */
        SET_ST_, 
        /**
         * Set Of Uncertain Value Probabilistic
         */
        SET_UVP_ANY__, 
        /**
         * Non Parametric Probability Distribution
         */
        NPPD_ANY_, 
        /**
         * Universal Resource Locator
         */
        URL, 
        /**
         * Telecommunication Address
         */
        TEL, 
        /**
         * Uncertain Value Narrative
         */
        UVN_ANY_, 
        /**
         * Uncertain Narrative Concept Descriptor
         */
        UVN_CD_, 
        /**
         * Uncertain Value Probabilistic
         */
        UVP_ANY_, 
        /**
         * Uncertain Probabilistic Concept Descriptor
         */
        UVP_CD_, 
        /**
         * Set Of Integer Numbers
         */
        SET_INT_, 
        /**
         * Set Of Real Numbers
         */
        SET_REAL_, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3DataType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ANY".equals(codeString))
          return ANY;
        if ("ANT<ANY>".equals(codeString))
          return ANT_ANY_;
        if ("ANT<CD>".equals(codeString))
          return ANT_CD_;
        if ("ANT<PQ>".equals(codeString))
          return ANT_PQ_;
        if ("BAG<ANY>".equals(codeString))
          return BAG_ANY_;
        if ("BAG<CD>".equals(codeString))
          return BAG_CD_;
        if ("BAG<PQ>".equals(codeString))
          return BAG_PQ_;
        if ("BL".equals(codeString))
          return BL;
        if ("CD".equals(codeString))
          return CD;
        if ("CE".equals(codeString))
          return CE;
        if ("CS".equals(codeString))
          return CS;
        if ("CV".equals(codeString))
          return CV;
        if ("CR".equals(codeString))
          return CR;
        if ("HXIT<ANY>".equals(codeString))
          return HXIT_ANY_;
        if ("HXIT<AD>".equals(codeString))
          return HXIT_AD_;
        if ("II".equals(codeString))
          return II;
        if ("IVL<QTY>".equals(codeString))
          return IVL_QTY_;
        if ("IVL<INT>".equals(codeString))
          return IVL_INT_;
        if ("IVL<PQ>".equals(codeString))
          return IVL_PQ_;
        if ("UVP<IVL<PQ>>".equals(codeString))
          return UVP_IVL_PQ__;
        if ("IVL<REAL>".equals(codeString))
          return IVL_REAL_;
        if ("IVL<TS>".equals(codeString))
          return IVL_TS_;
        if ("SET<TS>".equals(codeString))
          return SET_TS_;
        if ("EIVL<TS>".equals(codeString))
          return EIVL_TS_;
        if ("GTS".equals(codeString))
          return GTS;
        if ("PIVL<TS>".equals(codeString))
          return PIVL_TS_;
        if ("LIST<ANY>".equals(codeString))
          return LIST_ANY_;
        if ("LIST<BL>".equals(codeString))
          return LIST_BL_;
        if ("BIN".equals(codeString))
          return BIN;
        if ("ED".equals(codeString))
          return ED;
        if ("ST".equals(codeString))
          return ST;
        if ("ADXP".equals(codeString))
          return ADXP;
        if ("ON".equals(codeString))
          return ON;
        if ("PNXP".equals(codeString))
          return PNXP;
        if ("LIST<LIST<ANY>>".equals(codeString))
          return LIST_LIST_ANY__;
        if ("LIST<LIST<BL>>".equals(codeString))
          return LIST_LIST_BL__;
        if ("LIST<BIN>".equals(codeString))
          return LIST_BIN_;
        if ("LIST<ED>".equals(codeString))
          return LIST_ED_;
        if ("LIST<ST>".equals(codeString))
          return LIST_ST_;
        if ("LIST<ADXP>".equals(codeString))
          return LIST_ADXP_;
        if ("AD".equals(codeString))
          return AD;
        if ("LIST<PNXP>".equals(codeString))
          return LIST_PNXP_;
        if ("PN".equals(codeString))
          return PN;
        if ("OID".equals(codeString))
          return OID;
        if ("QTY".equals(codeString))
          return QTY;
        if ("INT".equals(codeString))
          return INT;
        if ("MO".equals(codeString))
          return MO;
        if ("PPD<QTY>".equals(codeString))
          return PPD_QTY_;
        if ("PQ".equals(codeString))
          return PQ;
        if ("PPD<PQ>".equals(codeString))
          return PPD_PQ_;
        if ("REAL".equals(codeString))
          return REAL;
        if ("PPD<REAL>".equals(codeString))
          return PPD_REAL_;
        if ("RTO".equals(codeString))
          return RTO;
        if ("TS".equals(codeString))
          return TS;
        if ("SET<ANY>".equals(codeString))
          return SET_ANY_;
        if ("SET<CD>".equals(codeString))
          return SET_CD_;
        if ("SET<UVP<CD>>".equals(codeString))
          return SET_UVP_CD__;
        if ("NPPD<CD>".equals(codeString))
          return NPPD_CD_;
        if ("SET<CE>".equals(codeString))
          return SET_CE_;
        if ("SET<CS>".equals(codeString))
          return SET_CS_;
        if ("SET<CV>".equals(codeString))
          return SET_CV_;
        if ("SET<IVL<PQ>>".equals(codeString))
          return SET_IVL_PQ__;
        if ("SET<UVP<IVL<PQ>>>".equals(codeString))
          return SET_UVP_IVL_PQ___;
        if ("NPPD<IVL<PQ>>".equals(codeString))
          return NPPD_IVL_PQ__;
        if ("SET<LIST<ST>>".equals(codeString))
          return SET_LIST_ST__;
        if ("SET<AD>".equals(codeString))
          return SET_AD_;
        if ("SET<HXIT<AD>>".equals(codeString))
          return SET_HXIT_AD__;
        if ("HIST<AD>".equals(codeString))
          return HIST_AD_;
        if ("SET<PQ>".equals(codeString))
          return SET_PQ_;
        if ("SET<ST>".equals(codeString))
          return SET_ST_;
        if ("SET<UVP<ANY>>".equals(codeString))
          return SET_UVP_ANY__;
        if ("NPPD<ANY>".equals(codeString))
          return NPPD_ANY_;
        if ("URL".equals(codeString))
          return URL;
        if ("TEL".equals(codeString))
          return TEL;
        if ("UVN<ANY>".equals(codeString))
          return UVN_ANY_;
        if ("UVN<CD>".equals(codeString))
          return UVN_CD_;
        if ("UVP<ANY>".equals(codeString))
          return UVP_ANY_;
        if ("UVP<CD>".equals(codeString))
          return UVP_CD_;
        if ("SET<INT>".equals(codeString))
          return SET_INT_;
        if ("SET<REAL>".equals(codeString))
          return SET_REAL_;
        throw new Exception("Unknown V3DataType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ANY: return "ANY";
            case ANT_ANY_: return "ANT<ANY>";
            case ANT_CD_: return "ANT<CD>";
            case ANT_PQ_: return "ANT<PQ>";
            case BAG_ANY_: return "BAG<ANY>";
            case BAG_CD_: return "BAG<CD>";
            case BAG_PQ_: return "BAG<PQ>";
            case BL: return "BL";
            case CD: return "CD";
            case CE: return "CE";
            case CS: return "CS";
            case CV: return "CV";
            case CR: return "CR";
            case HXIT_ANY_: return "HXIT<ANY>";
            case HXIT_AD_: return "HXIT<AD>";
            case II: return "II";
            case IVL_QTY_: return "IVL<QTY>";
            case IVL_INT_: return "IVL<INT>";
            case IVL_PQ_: return "IVL<PQ>";
            case UVP_IVL_PQ__: return "UVP<IVL<PQ>>";
            case IVL_REAL_: return "IVL<REAL>";
            case IVL_TS_: return "IVL<TS>";
            case SET_TS_: return "SET<TS>";
            case EIVL_TS_: return "EIVL<TS>";
            case GTS: return "GTS";
            case PIVL_TS_: return "PIVL<TS>";
            case LIST_ANY_: return "LIST<ANY>";
            case LIST_BL_: return "LIST<BL>";
            case BIN: return "BIN";
            case ED: return "ED";
            case ST: return "ST";
            case ADXP: return "ADXP";
            case ON: return "ON";
            case PNXP: return "PNXP";
            case LIST_LIST_ANY__: return "LIST<LIST<ANY>>";
            case LIST_LIST_BL__: return "LIST<LIST<BL>>";
            case LIST_BIN_: return "LIST<BIN>";
            case LIST_ED_: return "LIST<ED>";
            case LIST_ST_: return "LIST<ST>";
            case LIST_ADXP_: return "LIST<ADXP>";
            case AD: return "AD";
            case LIST_PNXP_: return "LIST<PNXP>";
            case PN: return "PN";
            case OID: return "OID";
            case QTY: return "QTY";
            case INT: return "INT";
            case MO: return "MO";
            case PPD_QTY_: return "PPD<QTY>";
            case PQ: return "PQ";
            case PPD_PQ_: return "PPD<PQ>";
            case REAL: return "REAL";
            case PPD_REAL_: return "PPD<REAL>";
            case RTO: return "RTO";
            case TS: return "TS";
            case SET_ANY_: return "SET<ANY>";
            case SET_CD_: return "SET<CD>";
            case SET_UVP_CD__: return "SET<UVP<CD>>";
            case NPPD_CD_: return "NPPD<CD>";
            case SET_CE_: return "SET<CE>";
            case SET_CS_: return "SET<CS>";
            case SET_CV_: return "SET<CV>";
            case SET_IVL_PQ__: return "SET<IVL<PQ>>";
            case SET_UVP_IVL_PQ___: return "SET<UVP<IVL<PQ>>>";
            case NPPD_IVL_PQ__: return "NPPD<IVL<PQ>>";
            case SET_LIST_ST__: return "SET<LIST<ST>>";
            case SET_AD_: return "SET<AD>";
            case SET_HXIT_AD__: return "SET<HXIT<AD>>";
            case HIST_AD_: return "HIST<AD>";
            case SET_PQ_: return "SET<PQ>";
            case SET_ST_: return "SET<ST>";
            case SET_UVP_ANY__: return "SET<UVP<ANY>>";
            case NPPD_ANY_: return "NPPD<ANY>";
            case URL: return "URL";
            case TEL: return "TEL";
            case UVN_ANY_: return "UVN<ANY>";
            case UVN_CD_: return "UVN<CD>";
            case UVP_ANY_: return "UVP<ANY>";
            case UVP_CD_: return "UVP<CD>";
            case SET_INT_: return "SET<INT>";
            case SET_REAL_: return "SET<REAL>";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/DataType";
        }
        public String getDefinition() {
          switch (this) {
            case ANY: return "ANY";
            case ANT_ANY_: return "Annotated";
            case ANT_CD_: return "Annotated Concept Descriptor";
            case ANT_PQ_: return "Annotated Physical Quantity";
            case BAG_ANY_: return "Bag";
            case BAG_CD_: return "Bag Of Concept Descriptors";
            case BAG_PQ_: return "Bag Of Physical Quantities";
            case BL: return "Boolean";
            case CD: return "Concept Descriptor";
            case CE: return "Coded With Equivalents";
            case CS: return "Coded Simple Value";
            case CV: return "Coded Value";
            case CR: return "Concept Role";
            case HXIT_ANY_: return "Historical";
            case HXIT_AD_: return "Historical Address";
            case II: return "Instance Identifier";
            case IVL_QTY_: return "Interval";
            case IVL_INT_: return "Interval Of Integer Numbers";
            case IVL_PQ_: return "Interval Of Physical Quantities";
            case UVP_IVL_PQ__: return "Uncertain Probabilistic Interval Of Physical Quant";
            case IVL_REAL_: return "Interval Of Real Numbers";
            case IVL_TS_: return "Interval Of Points In Time";
            case SET_TS_: return "Set Of Points In Time";
            case EIVL_TS_: return "Event Related Interval";
            case GTS: return "General Timing Specification";
            case PIVL_TS_: return "Periodic Interval Of Time";
            case LIST_ANY_: return "Sequence";
            case LIST_BL_: return "Sequence Of Booleans";
            case BIN: return "Binary Data";
            case ED: return "Encoded Data";
            case ST: return "Character String";
            case ADXP: return "Address Part";
            case ON: return "Organization Name";
            case PNXP: return "Person Name Part";
            case LIST_LIST_ANY__: return "Sequence Of Sequences Of Data Values";
            case LIST_LIST_BL__: return "Sequence Of Sequence Of Booleans";
            case LIST_BIN_: return "Sequence Of Binary Data";
            case LIST_ED_: return "Sequence Of Encoded Data";
            case LIST_ST_: return "Sequence Of Character Strings";
            case LIST_ADXP_: return "Sequence Of Postal Address Parts";
            case AD: return "Postal And Residential Address";
            case LIST_PNXP_: return "Sequence Of Person Name Parts";
            case PN: return "Person Name Type";
            case OID: return "Object Identifier";
            case QTY: return "Quantity";
            case INT: return "Integer Number";
            case MO: return "Monetary Amount";
            case PPD_QTY_: return "Parametric Probability Distribution";
            case PQ: return "Physical Quantity";
            case PPD_PQ_: return "Parametric Probability Distribution Of Physical Quantity";
            case REAL: return "Real Number";
            case PPD_REAL_: return "Parametric Probability Distribution Of Real Number";
            case RTO: return "Ratio";
            case TS: return "Point In Time";
            case SET_ANY_: return "Set";
            case SET_CD_: return "Set Of Concept Descriptors";
            case SET_UVP_CD__: return "Set Of Uncertain Probabilistic Concept Descriptor";
            case NPPD_CD_: return "Non Parametric Probability Distribution Of Concept Descriptors";
            case SET_CE_: return "Set Of Coded With Equivalents";
            case SET_CS_: return "Set Of Coded Simple Value";
            case SET_CV_: return "Set Of Coded Value";
            case SET_IVL_PQ__: return "Set Of Intervals Of Physical Quantity";
            case SET_UVP_IVL_PQ___: return "Set Of Uncertain Probabilistic Interval Of Physical Quantities";
            case NPPD_IVL_PQ__: return "Non Parametric Probability Distribution Of Intervals of Physical Quantities";
            case SET_LIST_ST__: return "Set Of Sequences Of Character Strings";
            case SET_AD_: return "Set Of Addresses";
            case SET_HXIT_AD__: return "Set Of Historical Addresses";
            case HIST_AD_: return "History Of Address";
            case SET_PQ_: return "Set Of Physical Quantities";
            case SET_ST_: return "Set Of Character Strings";
            case SET_UVP_ANY__: return "Set Of Uncertain Value Probabilistic";
            case NPPD_ANY_: return "Non Parametric Probability Distribution";
            case URL: return "Universal Resource Locator";
            case TEL: return "Telecommunication Address";
            case UVN_ANY_: return "Uncertain Value Narrative";
            case UVN_CD_: return "Uncertain Narrative Concept Descriptor";
            case UVP_ANY_: return "Uncertain Value Probabilistic";
            case UVP_CD_: return "Uncertain Probabilistic Concept Descriptor";
            case SET_INT_: return "Set Of Integer Numbers";
            case SET_REAL_: return "Set Of Real Numbers";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ANY: return "ANY";
            case ANT_ANY_: return "Annotated";
            case ANT_CD_: return "Annotated Concept Descriptor";
            case ANT_PQ_: return "Annotated Physical Quantity";
            case BAG_ANY_: return "Bag";
            case BAG_CD_: return "Bag Of Concept Descriptors";
            case BAG_PQ_: return "Bag Of Physical Quantities";
            case BL: return "Boolean";
            case CD: return "Concept Descriptor";
            case CE: return "Coded With Equivalents";
            case CS: return "Coded Simple Value";
            case CV: return "Coded Value";
            case CR: return "Concept Role";
            case HXIT_ANY_: return "Historical";
            case HXIT_AD_: return "Historical Address";
            case II: return "Instance Identifier";
            case IVL_QTY_: return "Interval";
            case IVL_INT_: return "Interval Of Integer Numbers";
            case IVL_PQ_: return "Interval Of Physical Quantities";
            case UVP_IVL_PQ__: return "Uncertain Probabilistic Interval Of Physical Quant";
            case IVL_REAL_: return "Interval Of Real Numbers";
            case IVL_TS_: return "Interval Of Points In Time";
            case SET_TS_: return "Set Of Points In Time";
            case EIVL_TS_: return "Event Related Interval";
            case GTS: return "General Timing Specification";
            case PIVL_TS_: return "Periodic Interval Of Time";
            case LIST_ANY_: return "Sequence";
            case LIST_BL_: return "Sequence Of Booleans";
            case BIN: return "Binary Data";
            case ED: return "Encoded Data";
            case ST: return "Character String";
            case ADXP: return "Address Part";
            case ON: return "Organization Name";
            case PNXP: return "Person Name Part";
            case LIST_LIST_ANY__: return "Sequence Of Sequences Of Data Values";
            case LIST_LIST_BL__: return "Sequence Of Sequence Of Booleans";
            case LIST_BIN_: return "Sequence Of Binary Data";
            case LIST_ED_: return "Sequence Of Encoded Data";
            case LIST_ST_: return "Sequence Of Character Strings";
            case LIST_ADXP_: return "Sequence Of Postal Address Parts";
            case AD: return "Postal And Residential Address";
            case LIST_PNXP_: return "Sequence Of Person Name Parts";
            case PN: return "Person Name Type";
            case OID: return "Object Identifier";
            case QTY: return "Quantity";
            case INT: return "Integer Number";
            case MO: return "Monetary Amount";
            case PPD_QTY_: return "Parametric Probability Distribution";
            case PQ: return "Physical Quantity";
            case PPD_PQ_: return "Parametric Probability Distribution Of Physical Quantity";
            case REAL: return "Real Number";
            case PPD_REAL_: return "Parametric Probability Distribution Of Real Number";
            case RTO: return "Ratio";
            case TS: return "Point In Time";
            case SET_ANY_: return "Set";
            case SET_CD_: return "Set Of Concept Descriptors";
            case SET_UVP_CD__: return "Set Of Uncertain Probabilistic Concept Descriptor";
            case NPPD_CD_: return "Non Parametric Probability Distribution Of Concept Descriptors";
            case SET_CE_: return "Set Of Coded With Equivalents";
            case SET_CS_: return "Set Of Coded Simple Value";
            case SET_CV_: return "Set Of Coded Value";
            case SET_IVL_PQ__: return "Set Of Intervals Of Physical Quantity";
            case SET_UVP_IVL_PQ___: return "Set Of Uncertain Probabilistic Interval Of Physical Quantities";
            case NPPD_IVL_PQ__: return "Non Parametric Probability Distribution Of Intervals of Physical Quantities";
            case SET_LIST_ST__: return "Set Of Sequences Of Character Strings";
            case SET_AD_: return "Set Of Addresses";
            case SET_HXIT_AD__: return "Set Of Historical Addresses";
            case HIST_AD_: return "History Of Address";
            case SET_PQ_: return "Set Of Physical Quantities";
            case SET_ST_: return "Set Of Character Strings";
            case SET_UVP_ANY__: return "Set Of Uncertain Value Probabilistic";
            case NPPD_ANY_: return "Non Parametric Probability Distribution";
            case URL: return "Universal Resource Locator";
            case TEL: return "Telecommunication Address";
            case UVN_ANY_: return "Uncertain Value Narrative";
            case UVN_CD_: return "Uncertain Narrative Concept Descriptor";
            case UVP_ANY_: return "Uncertain Value Probabilistic";
            case UVP_CD_: return "Uncertain Probabilistic Concept Descriptor";
            case SET_INT_: return "Set Of Integer Numbers";
            case SET_REAL_: return "Set Of Real Numbers";
            default: return "?";
          }
    }


}

