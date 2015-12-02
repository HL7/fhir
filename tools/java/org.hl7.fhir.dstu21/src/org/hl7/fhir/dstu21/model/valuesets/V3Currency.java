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


public enum V3Currency {

        /**
         * Argentine Peso, monetary currency of Argentina
         */
        ARS, 
        /**
         * Australian Dollar, monetary currency of Australia
         */
        AUD, 
        /**
         * Brazilian Real, monetary currency of Brazil
         */
        BRL, 
        /**
         * Canadian Dollar, monetary currency of Canada
         */
        CAD, 
        /**
         * Swiss Franc, monetary currency of Switzerland
         */
        CHF, 
        /**
         * Unidades de Formento, monetary currency of Chile
         */
        CLF, 
        /**
         * Yuan Renminbi, monetary currency of China
         */
        CNY, 
        /**
         * Deutsche Mark, monetary currency of Germany
         */
        DEM, 
        /**
         * Spanish Peseta, monetary currency of Spain
         */
        ESP, 
        /**
         * Euro, monetary currency of European Union
         */
        EUR, 
        /**
         * Markka, monetary currency of Finland
         */
        FIM, 
        /**
         * French Franc, monetary currency of France
         */
        FRF, 
        /**
         * Pound Sterling, monetary currency of United Kingdom
         */
        GBP, 
        /**
         * Shekel, monetary currency of Israel
         */
        ILS, 
        /**
         * Indian Rupee, monetary currency of India
         */
        INR, 
        /**
         * Yen, monetary currency of Japan
         */
        JPY, 
        /**
         * Won, monetary currency of Korea (South)
         */
        KRW, 
        /**
         * Mexican Nuevo Peso, monetary currency of Mexico
         */
        MXN, 
        /**
         * Netherlands Guilder, monetary currency of Netherlands
         */
        NLG, 
        /**
         * New Zealand Dollar, monetary currency of New Zealand
         */
        NZD, 
        /**
         * Philippine Peso, monetary currency of Philippines
         */
        PHP, 
        /**
         * Russian Ruble, monetary currency of Russian Federation
         */
        RUR, 
        /**
         * Baht, monetary currency of Thailand
         */
        THB, 
        /**
         * Lira, monetary currency of Turkey
         */
        TRL, 
        /**
         * Taiwan Dollar, monetary currency of Taiwan
         */
        TWD, 
        /**
         * US Dollar, monetary currency of United States
         */
        USD, 
        /**
         * Rand, monetary currency of South Africa
         */
        ZAR, 
        /**
         * added to help the parsers
         */
        NULL;
        public static V3Currency fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ARS".equals(codeString))
          return ARS;
        if ("AUD".equals(codeString))
          return AUD;
        if ("BRL".equals(codeString))
          return BRL;
        if ("CAD".equals(codeString))
          return CAD;
        if ("CHF".equals(codeString))
          return CHF;
        if ("CLF".equals(codeString))
          return CLF;
        if ("CNY".equals(codeString))
          return CNY;
        if ("DEM".equals(codeString))
          return DEM;
        if ("ESP".equals(codeString))
          return ESP;
        if ("EUR".equals(codeString))
          return EUR;
        if ("FIM".equals(codeString))
          return FIM;
        if ("FRF".equals(codeString))
          return FRF;
        if ("GBP".equals(codeString))
          return GBP;
        if ("ILS".equals(codeString))
          return ILS;
        if ("INR".equals(codeString))
          return INR;
        if ("JPY".equals(codeString))
          return JPY;
        if ("KRW".equals(codeString))
          return KRW;
        if ("MXN".equals(codeString))
          return MXN;
        if ("NLG".equals(codeString))
          return NLG;
        if ("NZD".equals(codeString))
          return NZD;
        if ("PHP".equals(codeString))
          return PHP;
        if ("RUR".equals(codeString))
          return RUR;
        if ("THB".equals(codeString))
          return THB;
        if ("TRL".equals(codeString))
          return TRL;
        if ("TWD".equals(codeString))
          return TWD;
        if ("USD".equals(codeString))
          return USD;
        if ("ZAR".equals(codeString))
          return ZAR;
        throw new Exception("Unknown V3Currency code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ARS: return "ARS";
            case AUD: return "AUD";
            case BRL: return "BRL";
            case CAD: return "CAD";
            case CHF: return "CHF";
            case CLF: return "CLF";
            case CNY: return "CNY";
            case DEM: return "DEM";
            case ESP: return "ESP";
            case EUR: return "EUR";
            case FIM: return "FIM";
            case FRF: return "FRF";
            case GBP: return "GBP";
            case ILS: return "ILS";
            case INR: return "INR";
            case JPY: return "JPY";
            case KRW: return "KRW";
            case MXN: return "MXN";
            case NLG: return "NLG";
            case NZD: return "NZD";
            case PHP: return "PHP";
            case RUR: return "RUR";
            case THB: return "THB";
            case TRL: return "TRL";
            case TWD: return "TWD";
            case USD: return "USD";
            case ZAR: return "ZAR";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/v3/Currency";
        }
        public String getDefinition() {
          switch (this) {
            case ARS: return "Argentine Peso, monetary currency of Argentina";
            case AUD: return "Australian Dollar, monetary currency of Australia";
            case BRL: return "Brazilian Real, monetary currency of Brazil";
            case CAD: return "Canadian Dollar, monetary currency of Canada";
            case CHF: return "Swiss Franc, monetary currency of Switzerland";
            case CLF: return "Unidades de Formento, monetary currency of Chile";
            case CNY: return "Yuan Renminbi, monetary currency of China";
            case DEM: return "Deutsche Mark, monetary currency of Germany";
            case ESP: return "Spanish Peseta, monetary currency of Spain";
            case EUR: return "Euro, monetary currency of European Union";
            case FIM: return "Markka, monetary currency of Finland";
            case FRF: return "French Franc, monetary currency of France";
            case GBP: return "Pound Sterling, monetary currency of United Kingdom";
            case ILS: return "Shekel, monetary currency of Israel";
            case INR: return "Indian Rupee, monetary currency of India";
            case JPY: return "Yen, monetary currency of Japan";
            case KRW: return "Won, monetary currency of Korea (South)";
            case MXN: return "Mexican Nuevo Peso, monetary currency of Mexico";
            case NLG: return "Netherlands Guilder, monetary currency of Netherlands";
            case NZD: return "New Zealand Dollar, monetary currency of New Zealand";
            case PHP: return "Philippine Peso, monetary currency of Philippines";
            case RUR: return "Russian Ruble, monetary currency of Russian Federation";
            case THB: return "Baht, monetary currency of Thailand";
            case TRL: return "Lira, monetary currency of Turkey";
            case TWD: return "Taiwan Dollar, monetary currency of Taiwan";
            case USD: return "US Dollar, monetary currency of United States";
            case ZAR: return "Rand, monetary currency of South Africa";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ARS: return "Argentine Peso";
            case AUD: return "Australian Dollar";
            case BRL: return "Brazilian Real";
            case CAD: return "Canadian Dollar";
            case CHF: return "Swiss Franc";
            case CLF: return "Unidades de Formento";
            case CNY: return "Yuan Renminbi";
            case DEM: return "Deutsche Mark";
            case ESP: return "Spanish Peseta";
            case EUR: return "Euro";
            case FIM: return "Markka";
            case FRF: return "French Franc";
            case GBP: return "Pound Sterling";
            case ILS: return "Shekel";
            case INR: return "Indian Rupee";
            case JPY: return "Yen";
            case KRW: return "Won";
            case MXN: return "Mexican Nuevo Peso";
            case NLG: return "Netherlands Guilder";
            case NZD: return "New Zealand Dollar";
            case PHP: return "Philippine Peso";
            case RUR: return "Russian Ruble";
            case THB: return "Baht";
            case TRL: return "Lira";
            case TWD: return "Taiwan Dollar";
            case USD: return "US Dollar";
            case ZAR: return "Rand";
            default: return "?";
          }
    }


}

