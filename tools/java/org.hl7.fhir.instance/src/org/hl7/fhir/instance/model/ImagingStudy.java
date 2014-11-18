package org.hl7.fhir.instance.model;

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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * Representation of the content produced in a DICOM imaging study. A study comprises a set of Series, each of which includes a set of Service-Object Pair Instances (SOP Instances - images or other data) acquired or produced in a common context.  A Series is of only one modality (e.g., X-ray, CT, MR, ultrasound), but a Study may have multiple Series of different modalities.
 */
public class ImagingStudy extends DomainResource {

    public enum ImagingModality {
        AR, // 
        BMD, // 
        BDUS, // 
        EPS, // 
        CR, // 
        CT, // 
        DX, // 
        ECG, // 
        ES, // 
        XC, // 
        GM, // 
        HD, // 
        IO, // 
        IVOCT, // 
        IVUS, // 
        KER, // 
        LEN, // 
        MR, // 
        MG, // 
        NM, // 
        OAM, // 
        OCT, // 
        OPM, // 
        OP, // 
        OPR, // 
        OPT, // 
        OPV, // 
        PX, // 
        PT, // 
        RF, // 
        RG, // 
        SM, // 
        SRF, // 
        US, // 
        VA, // 
        XA, // 
        NULL; // added to help the parsers
        public static ImagingModality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return AR;
        if ("BMD".equals(codeString))
          return BMD;
        if ("BDUS".equals(codeString))
          return BDUS;
        if ("EPS".equals(codeString))
          return EPS;
        if ("CR".equals(codeString))
          return CR;
        if ("CT".equals(codeString))
          return CT;
        if ("DX".equals(codeString))
          return DX;
        if ("ECG".equals(codeString))
          return ECG;
        if ("ES".equals(codeString))
          return ES;
        if ("XC".equals(codeString))
          return XC;
        if ("GM".equals(codeString))
          return GM;
        if ("HD".equals(codeString))
          return HD;
        if ("IO".equals(codeString))
          return IO;
        if ("IVOCT".equals(codeString))
          return IVOCT;
        if ("IVUS".equals(codeString))
          return IVUS;
        if ("KER".equals(codeString))
          return KER;
        if ("LEN".equals(codeString))
          return LEN;
        if ("MR".equals(codeString))
          return MR;
        if ("MG".equals(codeString))
          return MG;
        if ("NM".equals(codeString))
          return NM;
        if ("OAM".equals(codeString))
          return OAM;
        if ("OCT".equals(codeString))
          return OCT;
        if ("OPM".equals(codeString))
          return OPM;
        if ("OP".equals(codeString))
          return OP;
        if ("OPR".equals(codeString))
          return OPR;
        if ("OPT".equals(codeString))
          return OPT;
        if ("OPV".equals(codeString))
          return OPV;
        if ("PX".equals(codeString))
          return PX;
        if ("PT".equals(codeString))
          return PT;
        if ("RF".equals(codeString))
          return RF;
        if ("RG".equals(codeString))
          return RG;
        if ("SM".equals(codeString))
          return SM;
        if ("SRF".equals(codeString))
          return SRF;
        if ("US".equals(codeString))
          return US;
        if ("VA".equals(codeString))
          return VA;
        if ("XA".equals(codeString))
          return XA;
        throw new Exception("Unknown ImagingModality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case AR: return "AR";
            case BMD: return "BMD";
            case BDUS: return "BDUS";
            case EPS: return "EPS";
            case CR: return "CR";
            case CT: return "CT";
            case DX: return "DX";
            case ECG: return "ECG";
            case ES: return "ES";
            case XC: return "XC";
            case GM: return "GM";
            case HD: return "HD";
            case IO: return "IO";
            case IVOCT: return "IVOCT";
            case IVUS: return "IVUS";
            case KER: return "KER";
            case LEN: return "LEN";
            case MR: return "MR";
            case MG: return "MG";
            case NM: return "NM";
            case OAM: return "OAM";
            case OCT: return "OCT";
            case OPM: return "OPM";
            case OP: return "OP";
            case OPR: return "OPR";
            case OPT: return "OPT";
            case OPV: return "OPV";
            case PX: return "PX";
            case PT: return "PT";
            case RF: return "RF";
            case RG: return "RG";
            case SM: return "SM";
            case SRF: return "SRF";
            case US: return "US";
            case VA: return "VA";
            case XA: return "XA";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case AR: return "";
            case BMD: return "";
            case BDUS: return "";
            case EPS: return "";
            case CR: return "";
            case CT: return "";
            case DX: return "";
            case ECG: return "";
            case ES: return "";
            case XC: return "";
            case GM: return "";
            case HD: return "";
            case IO: return "";
            case IVOCT: return "";
            case IVUS: return "";
            case KER: return "";
            case LEN: return "";
            case MR: return "";
            case MG: return "";
            case NM: return "";
            case OAM: return "";
            case OCT: return "";
            case OPM: return "";
            case OP: return "";
            case OPR: return "";
            case OPT: return "";
            case OPV: return "";
            case PX: return "";
            case PT: return "";
            case RF: return "";
            case RG: return "";
            case SM: return "";
            case SRF: return "";
            case US: return "";
            case VA: return "";
            case XA: return "";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case AR: return "AR";
            case BMD: return "BMD";
            case BDUS: return "BDUS";
            case EPS: return "EPS";
            case CR: return "CR";
            case CT: return "CT";
            case DX: return "DX";
            case ECG: return "ECG";
            case ES: return "ES";
            case XC: return "XC";
            case GM: return "GM";
            case HD: return "HD";
            case IO: return "IO";
            case IVOCT: return "IVOCT";
            case IVUS: return "IVUS";
            case KER: return "KER";
            case LEN: return "LEN";
            case MR: return "MR";
            case MG: return "MG";
            case NM: return "NM";
            case OAM: return "OAM";
            case OCT: return "OCT";
            case OPM: return "OPM";
            case OP: return "OP";
            case OPR: return "OPR";
            case OPT: return "OPT";
            case OPV: return "OPV";
            case PX: return "PX";
            case PT: return "PT";
            case RF: return "RF";
            case RG: return "RG";
            case SM: return "SM";
            case SRF: return "SRF";
            case US: return "US";
            case VA: return "VA";
            case XA: return "XA";
            default: return "?";
          }
        }
    }

  public static class ImagingModalityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return ImagingModality.AR;
        if ("BMD".equals(codeString))
          return ImagingModality.BMD;
        if ("BDUS".equals(codeString))
          return ImagingModality.BDUS;
        if ("EPS".equals(codeString))
          return ImagingModality.EPS;
        if ("CR".equals(codeString))
          return ImagingModality.CR;
        if ("CT".equals(codeString))
          return ImagingModality.CT;
        if ("DX".equals(codeString))
          return ImagingModality.DX;
        if ("ECG".equals(codeString))
          return ImagingModality.ECG;
        if ("ES".equals(codeString))
          return ImagingModality.ES;
        if ("XC".equals(codeString))
          return ImagingModality.XC;
        if ("GM".equals(codeString))
          return ImagingModality.GM;
        if ("HD".equals(codeString))
          return ImagingModality.HD;
        if ("IO".equals(codeString))
          return ImagingModality.IO;
        if ("IVOCT".equals(codeString))
          return ImagingModality.IVOCT;
        if ("IVUS".equals(codeString))
          return ImagingModality.IVUS;
        if ("KER".equals(codeString))
          return ImagingModality.KER;
        if ("LEN".equals(codeString))
          return ImagingModality.LEN;
        if ("MR".equals(codeString))
          return ImagingModality.MR;
        if ("MG".equals(codeString))
          return ImagingModality.MG;
        if ("NM".equals(codeString))
          return ImagingModality.NM;
        if ("OAM".equals(codeString))
          return ImagingModality.OAM;
        if ("OCT".equals(codeString))
          return ImagingModality.OCT;
        if ("OPM".equals(codeString))
          return ImagingModality.OPM;
        if ("OP".equals(codeString))
          return ImagingModality.OP;
        if ("OPR".equals(codeString))
          return ImagingModality.OPR;
        if ("OPT".equals(codeString))
          return ImagingModality.OPT;
        if ("OPV".equals(codeString))
          return ImagingModality.OPV;
        if ("PX".equals(codeString))
          return ImagingModality.PX;
        if ("PT".equals(codeString))
          return ImagingModality.PT;
        if ("RF".equals(codeString))
          return ImagingModality.RF;
        if ("RG".equals(codeString))
          return ImagingModality.RG;
        if ("SM".equals(codeString))
          return ImagingModality.SM;
        if ("SRF".equals(codeString))
          return ImagingModality.SRF;
        if ("US".equals(codeString))
          return ImagingModality.US;
        if ("VA".equals(codeString))
          return ImagingModality.VA;
        if ("XA".equals(codeString))
          return ImagingModality.XA;
        throw new Exception("Unknown ImagingModality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ImagingModality.AR)
        return "AR";
      if (code == ImagingModality.BMD)
        return "BMD";
      if (code == ImagingModality.BDUS)
        return "BDUS";
      if (code == ImagingModality.EPS)
        return "EPS";
      if (code == ImagingModality.CR)
        return "CR";
      if (code == ImagingModality.CT)
        return "CT";
      if (code == ImagingModality.DX)
        return "DX";
      if (code == ImagingModality.ECG)
        return "ECG";
      if (code == ImagingModality.ES)
        return "ES";
      if (code == ImagingModality.XC)
        return "XC";
      if (code == ImagingModality.GM)
        return "GM";
      if (code == ImagingModality.HD)
        return "HD";
      if (code == ImagingModality.IO)
        return "IO";
      if (code == ImagingModality.IVOCT)
        return "IVOCT";
      if (code == ImagingModality.IVUS)
        return "IVUS";
      if (code == ImagingModality.KER)
        return "KER";
      if (code == ImagingModality.LEN)
        return "LEN";
      if (code == ImagingModality.MR)
        return "MR";
      if (code == ImagingModality.MG)
        return "MG";
      if (code == ImagingModality.NM)
        return "NM";
      if (code == ImagingModality.OAM)
        return "OAM";
      if (code == ImagingModality.OCT)
        return "OCT";
      if (code == ImagingModality.OPM)
        return "OPM";
      if (code == ImagingModality.OP)
        return "OP";
      if (code == ImagingModality.OPR)
        return "OPR";
      if (code == ImagingModality.OPT)
        return "OPT";
      if (code == ImagingModality.OPV)
        return "OPV";
      if (code == ImagingModality.PX)
        return "PX";
      if (code == ImagingModality.PT)
        return "PT";
      if (code == ImagingModality.RF)
        return "RF";
      if (code == ImagingModality.RG)
        return "RG";
      if (code == ImagingModality.SM)
        return "SM";
      if (code == ImagingModality.SRF)
        return "SRF";
      if (code == ImagingModality.US)
        return "US";
      if (code == ImagingModality.VA)
        return "VA";
      if (code == ImagingModality.XA)
        return "XA";
      return "?";
      }
    }

    public enum InstanceAvailability {
        ONLINE, // Resources are immediately available,.
        OFFLINE, // Resources need to be retrieved by manual intervention.
        NEARLINE, // Resources need to be retrieved from relatively slow media.
        UNAVAILABLE, // Resources cannot be retrieved.
        NULL; // added to help the parsers
        public static InstanceAvailability fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ONLINE".equals(codeString))
          return ONLINE;
        if ("OFFLINE".equals(codeString))
          return OFFLINE;
        if ("NEARLINE".equals(codeString))
          return NEARLINE;
        if ("UNAVAILABLE".equals(codeString))
          return UNAVAILABLE;
        throw new Exception("Unknown InstanceAvailability code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ONLINE: return "ONLINE";
            case OFFLINE: return "OFFLINE";
            case NEARLINE: return "NEARLINE";
            case UNAVAILABLE: return "UNAVAILABLE";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ONLINE: return "Resources are immediately available,.";
            case OFFLINE: return "Resources need to be retrieved by manual intervention.";
            case NEARLINE: return "Resources need to be retrieved from relatively slow media.";
            case UNAVAILABLE: return "Resources cannot be retrieved.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ONLINE: return "ONLINE";
            case OFFLINE: return "OFFLINE";
            case NEARLINE: return "NEARLINE";
            case UNAVAILABLE: return "UNAVAILABLE";
            default: return "?";
          }
        }
    }

  public static class InstanceAvailabilityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ONLINE".equals(codeString))
          return InstanceAvailability.ONLINE;
        if ("OFFLINE".equals(codeString))
          return InstanceAvailability.OFFLINE;
        if ("NEARLINE".equals(codeString))
          return InstanceAvailability.NEARLINE;
        if ("UNAVAILABLE".equals(codeString))
          return InstanceAvailability.UNAVAILABLE;
        throw new Exception("Unknown InstanceAvailability code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == InstanceAvailability.ONLINE)
        return "ONLINE";
      if (code == InstanceAvailability.OFFLINE)
        return "OFFLINE";
      if (code == InstanceAvailability.NEARLINE)
        return "NEARLINE";
      if (code == InstanceAvailability.UNAVAILABLE)
        return "UNAVAILABLE";
      return "?";
      }
    }

    public enum Modality {
        AR, // 
        AU, // 
        BDUS, // 
        BI, // 
        BMD, // 
        CR, // 
        CT, // 
        DG, // 
        DX, // 
        ECG, // 
        EPS, // 
        ES, // 
        GM, // 
        HC, // 
        HD, // 
        IO, // 
        IVOCT, // 
        IVUS, // 
        KER, // 
        KO, // 
        LEN, // 
        LS, // 
        MG, // 
        MR, // 
        NM, // 
        OAM, // 
        OCT, // 
        OP, // 
        OPM, // 
        OPT, // 
        OPV, // 
        OT, // 
        PR, // 
        PT, // 
        PX, // 
        REG, // 
        RF, // 
        RG, // 
        RTDOSE, // 
        RTIMAGE, // 
        RTPLAN, // 
        RTRECORD, // 
        RTSTRUCT, // 
        SEG, // 
        SM, // 
        SMR, // 
        SR, // 
        SRF, // 
        TG, // 
        US, // 
        VA, // 
        XA, // 
        XC, // 
        NULL; // added to help the parsers
        public static Modality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return AR;
        if ("AU".equals(codeString))
          return AU;
        if ("BDUS".equals(codeString))
          return BDUS;
        if ("BI".equals(codeString))
          return BI;
        if ("BMD".equals(codeString))
          return BMD;
        if ("CR".equals(codeString))
          return CR;
        if ("CT".equals(codeString))
          return CT;
        if ("DG".equals(codeString))
          return DG;
        if ("DX".equals(codeString))
          return DX;
        if ("ECG".equals(codeString))
          return ECG;
        if ("EPS".equals(codeString))
          return EPS;
        if ("ES".equals(codeString))
          return ES;
        if ("GM".equals(codeString))
          return GM;
        if ("HC".equals(codeString))
          return HC;
        if ("HD".equals(codeString))
          return HD;
        if ("IO".equals(codeString))
          return IO;
        if ("IVOCT".equals(codeString))
          return IVOCT;
        if ("IVUS".equals(codeString))
          return IVUS;
        if ("KER".equals(codeString))
          return KER;
        if ("KO".equals(codeString))
          return KO;
        if ("LEN".equals(codeString))
          return LEN;
        if ("LS".equals(codeString))
          return LS;
        if ("MG".equals(codeString))
          return MG;
        if ("MR".equals(codeString))
          return MR;
        if ("NM".equals(codeString))
          return NM;
        if ("OAM".equals(codeString))
          return OAM;
        if ("OCT".equals(codeString))
          return OCT;
        if ("OP".equals(codeString))
          return OP;
        if ("OPM".equals(codeString))
          return OPM;
        if ("OPT".equals(codeString))
          return OPT;
        if ("OPV".equals(codeString))
          return OPV;
        if ("OT".equals(codeString))
          return OT;
        if ("PR".equals(codeString))
          return PR;
        if ("PT".equals(codeString))
          return PT;
        if ("PX".equals(codeString))
          return PX;
        if ("REG".equals(codeString))
          return REG;
        if ("RF".equals(codeString))
          return RF;
        if ("RG".equals(codeString))
          return RG;
        if ("RTDOSE".equals(codeString))
          return RTDOSE;
        if ("RTIMAGE".equals(codeString))
          return RTIMAGE;
        if ("RTPLAN".equals(codeString))
          return RTPLAN;
        if ("RTRECORD".equals(codeString))
          return RTRECORD;
        if ("RTSTRUCT".equals(codeString))
          return RTSTRUCT;
        if ("SEG".equals(codeString))
          return SEG;
        if ("SM".equals(codeString))
          return SM;
        if ("SMR".equals(codeString))
          return SMR;
        if ("SR".equals(codeString))
          return SR;
        if ("SRF".equals(codeString))
          return SRF;
        if ("TG".equals(codeString))
          return TG;
        if ("US".equals(codeString))
          return US;
        if ("VA".equals(codeString))
          return VA;
        if ("XA".equals(codeString))
          return XA;
        if ("XC".equals(codeString))
          return XC;
        throw new Exception("Unknown Modality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case AR: return "AR";
            case AU: return "AU";
            case BDUS: return "BDUS";
            case BI: return "BI";
            case BMD: return "BMD";
            case CR: return "CR";
            case CT: return "CT";
            case DG: return "DG";
            case DX: return "DX";
            case ECG: return "ECG";
            case EPS: return "EPS";
            case ES: return "ES";
            case GM: return "GM";
            case HC: return "HC";
            case HD: return "HD";
            case IO: return "IO";
            case IVOCT: return "IVOCT";
            case IVUS: return "IVUS";
            case KER: return "KER";
            case KO: return "KO";
            case LEN: return "LEN";
            case LS: return "LS";
            case MG: return "MG";
            case MR: return "MR";
            case NM: return "NM";
            case OAM: return "OAM";
            case OCT: return "OCT";
            case OP: return "OP";
            case OPM: return "OPM";
            case OPT: return "OPT";
            case OPV: return "OPV";
            case OT: return "OT";
            case PR: return "PR";
            case PT: return "PT";
            case PX: return "PX";
            case REG: return "REG";
            case RF: return "RF";
            case RG: return "RG";
            case RTDOSE: return "RTDOSE";
            case RTIMAGE: return "RTIMAGE";
            case RTPLAN: return "RTPLAN";
            case RTRECORD: return "RTRECORD";
            case RTSTRUCT: return "RTSTRUCT";
            case SEG: return "SEG";
            case SM: return "SM";
            case SMR: return "SMR";
            case SR: return "SR";
            case SRF: return "SRF";
            case TG: return "TG";
            case US: return "US";
            case VA: return "VA";
            case XA: return "XA";
            case XC: return "XC";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case AR: return "";
            case AU: return "";
            case BDUS: return "";
            case BI: return "";
            case BMD: return "";
            case CR: return "";
            case CT: return "";
            case DG: return "";
            case DX: return "";
            case ECG: return "";
            case EPS: return "";
            case ES: return "";
            case GM: return "";
            case HC: return "";
            case HD: return "";
            case IO: return "";
            case IVOCT: return "";
            case IVUS: return "";
            case KER: return "";
            case KO: return "";
            case LEN: return "";
            case LS: return "";
            case MG: return "";
            case MR: return "";
            case NM: return "";
            case OAM: return "";
            case OCT: return "";
            case OP: return "";
            case OPM: return "";
            case OPT: return "";
            case OPV: return "";
            case OT: return "";
            case PR: return "";
            case PT: return "";
            case PX: return "";
            case REG: return "";
            case RF: return "";
            case RG: return "";
            case RTDOSE: return "";
            case RTIMAGE: return "";
            case RTPLAN: return "";
            case RTRECORD: return "";
            case RTSTRUCT: return "";
            case SEG: return "";
            case SM: return "";
            case SMR: return "";
            case SR: return "";
            case SRF: return "";
            case TG: return "";
            case US: return "";
            case VA: return "";
            case XA: return "";
            case XC: return "";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case AR: return "AR";
            case AU: return "AU";
            case BDUS: return "BDUS";
            case BI: return "BI";
            case BMD: return "BMD";
            case CR: return "CR";
            case CT: return "CT";
            case DG: return "DG";
            case DX: return "DX";
            case ECG: return "ECG";
            case EPS: return "EPS";
            case ES: return "ES";
            case GM: return "GM";
            case HC: return "HC";
            case HD: return "HD";
            case IO: return "IO";
            case IVOCT: return "IVOCT";
            case IVUS: return "IVUS";
            case KER: return "KER";
            case KO: return "KO";
            case LEN: return "LEN";
            case LS: return "LS";
            case MG: return "MG";
            case MR: return "MR";
            case NM: return "NM";
            case OAM: return "OAM";
            case OCT: return "OCT";
            case OP: return "OP";
            case OPM: return "OPM";
            case OPT: return "OPT";
            case OPV: return "OPV";
            case OT: return "OT";
            case PR: return "PR";
            case PT: return "PT";
            case PX: return "PX";
            case REG: return "REG";
            case RF: return "RF";
            case RG: return "RG";
            case RTDOSE: return "RTDOSE";
            case RTIMAGE: return "RTIMAGE";
            case RTPLAN: return "RTPLAN";
            case RTRECORD: return "RTRECORD";
            case RTSTRUCT: return "RTSTRUCT";
            case SEG: return "SEG";
            case SM: return "SM";
            case SMR: return "SMR";
            case SR: return "SR";
            case SRF: return "SRF";
            case TG: return "TG";
            case US: return "US";
            case VA: return "VA";
            case XA: return "XA";
            case XC: return "XC";
            default: return "?";
          }
        }
    }

  public static class ModalityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return Modality.AR;
        if ("AU".equals(codeString))
          return Modality.AU;
        if ("BDUS".equals(codeString))
          return Modality.BDUS;
        if ("BI".equals(codeString))
          return Modality.BI;
        if ("BMD".equals(codeString))
          return Modality.BMD;
        if ("CR".equals(codeString))
          return Modality.CR;
        if ("CT".equals(codeString))
          return Modality.CT;
        if ("DG".equals(codeString))
          return Modality.DG;
        if ("DX".equals(codeString))
          return Modality.DX;
        if ("ECG".equals(codeString))
          return Modality.ECG;
        if ("EPS".equals(codeString))
          return Modality.EPS;
        if ("ES".equals(codeString))
          return Modality.ES;
        if ("GM".equals(codeString))
          return Modality.GM;
        if ("HC".equals(codeString))
          return Modality.HC;
        if ("HD".equals(codeString))
          return Modality.HD;
        if ("IO".equals(codeString))
          return Modality.IO;
        if ("IVOCT".equals(codeString))
          return Modality.IVOCT;
        if ("IVUS".equals(codeString))
          return Modality.IVUS;
        if ("KER".equals(codeString))
          return Modality.KER;
        if ("KO".equals(codeString))
          return Modality.KO;
        if ("LEN".equals(codeString))
          return Modality.LEN;
        if ("LS".equals(codeString))
          return Modality.LS;
        if ("MG".equals(codeString))
          return Modality.MG;
        if ("MR".equals(codeString))
          return Modality.MR;
        if ("NM".equals(codeString))
          return Modality.NM;
        if ("OAM".equals(codeString))
          return Modality.OAM;
        if ("OCT".equals(codeString))
          return Modality.OCT;
        if ("OP".equals(codeString))
          return Modality.OP;
        if ("OPM".equals(codeString))
          return Modality.OPM;
        if ("OPT".equals(codeString))
          return Modality.OPT;
        if ("OPV".equals(codeString))
          return Modality.OPV;
        if ("OT".equals(codeString))
          return Modality.OT;
        if ("PR".equals(codeString))
          return Modality.PR;
        if ("PT".equals(codeString))
          return Modality.PT;
        if ("PX".equals(codeString))
          return Modality.PX;
        if ("REG".equals(codeString))
          return Modality.REG;
        if ("RF".equals(codeString))
          return Modality.RF;
        if ("RG".equals(codeString))
          return Modality.RG;
        if ("RTDOSE".equals(codeString))
          return Modality.RTDOSE;
        if ("RTIMAGE".equals(codeString))
          return Modality.RTIMAGE;
        if ("RTPLAN".equals(codeString))
          return Modality.RTPLAN;
        if ("RTRECORD".equals(codeString))
          return Modality.RTRECORD;
        if ("RTSTRUCT".equals(codeString))
          return Modality.RTSTRUCT;
        if ("SEG".equals(codeString))
          return Modality.SEG;
        if ("SM".equals(codeString))
          return Modality.SM;
        if ("SMR".equals(codeString))
          return Modality.SMR;
        if ("SR".equals(codeString))
          return Modality.SR;
        if ("SRF".equals(codeString))
          return Modality.SRF;
        if ("TG".equals(codeString))
          return Modality.TG;
        if ("US".equals(codeString))
          return Modality.US;
        if ("VA".equals(codeString))
          return Modality.VA;
        if ("XA".equals(codeString))
          return Modality.XA;
        if ("XC".equals(codeString))
          return Modality.XC;
        throw new Exception("Unknown Modality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Modality.AR)
        return "AR";
      if (code == Modality.AU)
        return "AU";
      if (code == Modality.BDUS)
        return "BDUS";
      if (code == Modality.BI)
        return "BI";
      if (code == Modality.BMD)
        return "BMD";
      if (code == Modality.CR)
        return "CR";
      if (code == Modality.CT)
        return "CT";
      if (code == Modality.DG)
        return "DG";
      if (code == Modality.DX)
        return "DX";
      if (code == Modality.ECG)
        return "ECG";
      if (code == Modality.EPS)
        return "EPS";
      if (code == Modality.ES)
        return "ES";
      if (code == Modality.GM)
        return "GM";
      if (code == Modality.HC)
        return "HC";
      if (code == Modality.HD)
        return "HD";
      if (code == Modality.IO)
        return "IO";
      if (code == Modality.IVOCT)
        return "IVOCT";
      if (code == Modality.IVUS)
        return "IVUS";
      if (code == Modality.KER)
        return "KER";
      if (code == Modality.KO)
        return "KO";
      if (code == Modality.LEN)
        return "LEN";
      if (code == Modality.LS)
        return "LS";
      if (code == Modality.MG)
        return "MG";
      if (code == Modality.MR)
        return "MR";
      if (code == Modality.NM)
        return "NM";
      if (code == Modality.OAM)
        return "OAM";
      if (code == Modality.OCT)
        return "OCT";
      if (code == Modality.OP)
        return "OP";
      if (code == Modality.OPM)
        return "OPM";
      if (code == Modality.OPT)
        return "OPT";
      if (code == Modality.OPV)
        return "OPV";
      if (code == Modality.OT)
        return "OT";
      if (code == Modality.PR)
        return "PR";
      if (code == Modality.PT)
        return "PT";
      if (code == Modality.PX)
        return "PX";
      if (code == Modality.REG)
        return "REG";
      if (code == Modality.RF)
        return "RF";
      if (code == Modality.RG)
        return "RG";
      if (code == Modality.RTDOSE)
        return "RTDOSE";
      if (code == Modality.RTIMAGE)
        return "RTIMAGE";
      if (code == Modality.RTPLAN)
        return "RTPLAN";
      if (code == Modality.RTRECORD)
        return "RTRECORD";
      if (code == Modality.RTSTRUCT)
        return "RTSTRUCT";
      if (code == Modality.SEG)
        return "SEG";
      if (code == Modality.SM)
        return "SM";
      if (code == Modality.SMR)
        return "SMR";
      if (code == Modality.SR)
        return "SR";
      if (code == Modality.SRF)
        return "SRF";
      if (code == Modality.TG)
        return "TG";
      if (code == Modality.US)
        return "US";
      if (code == Modality.VA)
        return "VA";
      if (code == Modality.XA)
        return "XA";
      if (code == Modality.XC)
        return "XC";
      return "?";
      }
    }

    public static class ImagingStudySeriesComponent extends BackboneElement {
        /**
         * The Numeric identifier of this series in the study.
         */
        protected IntegerType number;

        /**
         * The modality of this series sequence.
         */
        protected Enumeration<Modality> modality;

        /**
         * Formal identifier for this series.
         */
        protected OidType uid;

        /**
         * A description of the series.
         */
        protected StringType description;

        /**
         * Sequence that contains attributes from the.
         */
        protected IntegerType numberOfInstances;

        /**
         * Availability of series (online, offline or nearline).
         */
        protected Enumeration<InstanceAvailability> availability;

        /**
         * WADO-RS URI where Series is available.
         */
        protected UriType url;

        /**
         * Body part examined. See  DICOM Part 16 Annex L for the mapping from DICOM to Snomed.
         */
        protected Coding bodySite;

        /**
         * When the series started.
         */
        protected DateTimeType dateTime;

        /**
         * A single image taken from a patient.
         */
        protected List<ImagingStudySeriesInstanceComponent> instance = new ArrayList<ImagingStudySeriesInstanceComponent>();

        private static final long serialVersionUID = 1575647295L;

      public ImagingStudySeriesComponent() {
        super();
      }

      public ImagingStudySeriesComponent(Enumeration<Modality> modality, OidType uid, IntegerType numberOfInstances) {
        super();
        this.modality = modality;
        this.uid = uid;
        this.numberOfInstances = numberOfInstances;
      }

        /**
         * @return {@link #number} (The Numeric identifier of this series in the study.). This is the underlying object with id, value and extensions. The accessor "getNumber" gives direct access to the value
         */
        public IntegerType getNumberElement() { 
          return this.number;
        }

        /**
         * @param value {@link #number} (The Numeric identifier of this series in the study.). This is the underlying object with id, value and extensions. The accessor "getNumber" gives direct access to the value
         */
        public ImagingStudySeriesComponent setNumberElement(IntegerType value) { 
          this.number = value;
          return this;
        }

        /**
         * @return The Numeric identifier of this series in the study.
         */
        public int getNumber() { 
          return this.number == null ? null : this.number.getValue();
        }

        /**
         * @param value The Numeric identifier of this series in the study.
         */
        public ImagingStudySeriesComponent setNumber(int value) { 
          if (value == -1)
            this.number = null;
          else {
            if (this.number == null)
              this.number = new IntegerType();
            this.number.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #modality} (The modality of this series sequence.). This is the underlying object with id, value and extensions. The accessor "getModality" gives direct access to the value
         */
        public Enumeration<Modality> getModalityElement() { 
          return this.modality;
        }

        /**
         * @param value {@link #modality} (The modality of this series sequence.). This is the underlying object with id, value and extensions. The accessor "getModality" gives direct access to the value
         */
        public ImagingStudySeriesComponent setModalityElement(Enumeration<Modality> value) { 
          this.modality = value;
          return this;
        }

        /**
         * @return The modality of this series sequence.
         */
        public Modality getModality() { 
          return this.modality == null ? null : this.modality.getValue();
        }

        /**
         * @param value The modality of this series sequence.
         */
        public ImagingStudySeriesComponent setModality(Modality value) { 
            if (this.modality == null)
              this.modality = new Enumeration<Modality>();
            this.modality.setValue(value);
          return this;
        }

        /**
         * @return {@link #uid} (Formal identifier for this series.). This is the underlying object with id, value and extensions. The accessor "getUid" gives direct access to the value
         */
        public OidType getUidElement() { 
          return this.uid;
        }

        /**
         * @param value {@link #uid} (Formal identifier for this series.). This is the underlying object with id, value and extensions. The accessor "getUid" gives direct access to the value
         */
        public ImagingStudySeriesComponent setUidElement(OidType value) { 
          this.uid = value;
          return this;
        }

        /**
         * @return Formal identifier for this series.
         */
        public String getUid() { 
          return this.uid == null ? null : this.uid.getValue();
        }

        /**
         * @param value Formal identifier for this series.
         */
        public ImagingStudySeriesComponent setUid(String value) { 
            if (this.uid == null)
              this.uid = new OidType();
            this.uid.setValue(value);
          return this;
        }

        /**
         * @return {@link #description} (A description of the series.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (A description of the series.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public ImagingStudySeriesComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return A description of the series.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value A description of the series.
         */
        public ImagingStudySeriesComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #numberOfInstances} (Sequence that contains attributes from the.). This is the underlying object with id, value and extensions. The accessor "getNumberOfInstances" gives direct access to the value
         */
        public IntegerType getNumberOfInstancesElement() { 
          return this.numberOfInstances;
        }

        /**
         * @param value {@link #numberOfInstances} (Sequence that contains attributes from the.). This is the underlying object with id, value and extensions. The accessor "getNumberOfInstances" gives direct access to the value
         */
        public ImagingStudySeriesComponent setNumberOfInstancesElement(IntegerType value) { 
          this.numberOfInstances = value;
          return this;
        }

        /**
         * @return Sequence that contains attributes from the.
         */
        public int getNumberOfInstances() { 
          return this.numberOfInstances == null ? null : this.numberOfInstances.getValue();
        }

        /**
         * @param value Sequence that contains attributes from the.
         */
        public ImagingStudySeriesComponent setNumberOfInstances(int value) { 
            if (this.numberOfInstances == null)
              this.numberOfInstances = new IntegerType();
            this.numberOfInstances.setValue(value);
          return this;
        }

        /**
         * @return {@link #availability} (Availability of series (online, offline or nearline).). This is the underlying object with id, value and extensions. The accessor "getAvailability" gives direct access to the value
         */
        public Enumeration<InstanceAvailability> getAvailabilityElement() { 
          return this.availability;
        }

        /**
         * @param value {@link #availability} (Availability of series (online, offline or nearline).). This is the underlying object with id, value and extensions. The accessor "getAvailability" gives direct access to the value
         */
        public ImagingStudySeriesComponent setAvailabilityElement(Enumeration<InstanceAvailability> value) { 
          this.availability = value;
          return this;
        }

        /**
         * @return Availability of series (online, offline or nearline).
         */
        public InstanceAvailability getAvailability() { 
          return this.availability == null ? null : this.availability.getValue();
        }

        /**
         * @param value Availability of series (online, offline or nearline).
         */
        public ImagingStudySeriesComponent setAvailability(InstanceAvailability value) { 
          if (value == null)
            this.availability = null;
          else {
            if (this.availability == null)
              this.availability = new Enumeration<InstanceAvailability>();
            this.availability.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #url} (WADO-RS URI where Series is available.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public UriType getUrlElement() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (WADO-RS URI where Series is available.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public ImagingStudySeriesComponent setUrlElement(UriType value) { 
          this.url = value;
          return this;
        }

        /**
         * @return WADO-RS URI where Series is available.
         */
        public String getUrl() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value WADO-RS URI where Series is available.
         */
        public ImagingStudySeriesComponent setUrl(String value) { 
          if (Utilities.noString(value))
            this.url = null;
          else {
            if (this.url == null)
              this.url = new UriType();
            this.url.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #bodySite} (Body part examined. See  DICOM Part 16 Annex L for the mapping from DICOM to Snomed.)
         */
        public Coding getBodySite() { 
          return this.bodySite;
        }

        /**
         * @param value {@link #bodySite} (Body part examined. See  DICOM Part 16 Annex L for the mapping from DICOM to Snomed.)
         */
        public ImagingStudySeriesComponent setBodySite(Coding value) { 
          this.bodySite = value;
          return this;
        }

        /**
         * @return {@link #dateTime} (When the series started.). This is the underlying object with id, value and extensions. The accessor "getDateTime" gives direct access to the value
         */
        public DateTimeType getDateTimeElement() { 
          return this.dateTime;
        }

        /**
         * @param value {@link #dateTime} (When the series started.). This is the underlying object with id, value and extensions. The accessor "getDateTime" gives direct access to the value
         */
        public ImagingStudySeriesComponent setDateTimeElement(DateTimeType value) { 
          this.dateTime = value;
          return this;
        }

        /**
         * @return When the series started.
         */
        public DateAndTime getDateTime() { 
          return this.dateTime == null ? null : this.dateTime.getValue();
        }

        /**
         * @param value When the series started.
         */
        public ImagingStudySeriesComponent setDateTime(DateAndTime value) { 
          if (value == null)
            this.dateTime = null;
          else {
            if (this.dateTime == null)
              this.dateTime = new DateTimeType();
            this.dateTime.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #instance} (A single image taken from a patient.)
         */
        public List<ImagingStudySeriesInstanceComponent> getInstance() { 
          return this.instance;
        }

        /**
         * @return {@link #instance} (A single image taken from a patient.)
         */
    // syntactic sugar
        public ImagingStudySeriesInstanceComponent addInstance() { //3
          ImagingStudySeriesInstanceComponent t = new ImagingStudySeriesInstanceComponent();
          this.instance.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("number", "integer", "The Numeric identifier of this series in the study.", 0, java.lang.Integer.MAX_VALUE, number));
          childrenList.add(new Property("modality", "code", "The modality of this series sequence.", 0, java.lang.Integer.MAX_VALUE, modality));
          childrenList.add(new Property("uid", "oid", "Formal identifier for this series.", 0, java.lang.Integer.MAX_VALUE, uid));
          childrenList.add(new Property("description", "string", "A description of the series.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("numberOfInstances", "integer", "Sequence that contains attributes from the.", 0, java.lang.Integer.MAX_VALUE, numberOfInstances));
          childrenList.add(new Property("availability", "code", "Availability of series (online, offline or nearline).", 0, java.lang.Integer.MAX_VALUE, availability));
          childrenList.add(new Property("url", "uri", "WADO-RS URI where Series is available.", 0, java.lang.Integer.MAX_VALUE, url));
          childrenList.add(new Property("bodySite", "Coding", "Body part examined. See  DICOM Part 16 Annex L for the mapping from DICOM to Snomed.", 0, java.lang.Integer.MAX_VALUE, bodySite));
          childrenList.add(new Property("dateTime", "dateTime", "When the series started.", 0, java.lang.Integer.MAX_VALUE, dateTime));
          childrenList.add(new Property("instance", "", "A single image taken from a patient.", 0, java.lang.Integer.MAX_VALUE, instance));
        }

      public ImagingStudySeriesComponent copy() {
        ImagingStudySeriesComponent dst = new ImagingStudySeriesComponent();
        copyValues(dst);
        dst.number = number == null ? null : number.copy();
        dst.modality = modality == null ? null : modality.copy();
        dst.uid = uid == null ? null : uid.copy();
        dst.description = description == null ? null : description.copy();
        dst.numberOfInstances = numberOfInstances == null ? null : numberOfInstances.copy();
        dst.availability = availability == null ? null : availability.copy();
        dst.url = url == null ? null : url.copy();
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.instance = new ArrayList<ImagingStudySeriesInstanceComponent>();
        for (ImagingStudySeriesInstanceComponent i : instance)
          dst.instance.add(i.copy());
        return dst;
      }

  }

    public static class ImagingStudySeriesInstanceComponent extends BackboneElement {
        /**
         * The number of this image in the series.
         */
        protected IntegerType number;

        /**
         * Formal identifier for this image.
         */
        protected OidType uid;

        /**
         * DICOM Image type.
         */
        protected OidType sopclass;

        /**
         * Type of instance (image etc) (0004,1430).
         */
        protected StringType type;

        /**
         * Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008).
         */
        protected StringType title;

        /**
         * WADO-RS url where image is available.
         */
        protected UriType url;

        /**
         * A FHIR resource with content for this instance.
         */
        protected Reference attachment;

        /**
         * The actual object that is the target of the reference (A FHIR resource with content for this instance.)
         */
        protected Resource attachmentTarget;

        private static final long serialVersionUID = -2008450480L;

      public ImagingStudySeriesInstanceComponent() {
        super();
      }

      public ImagingStudySeriesInstanceComponent(OidType uid, OidType sopclass) {
        super();
        this.uid = uid;
        this.sopclass = sopclass;
      }

        /**
         * @return {@link #number} (The number of this image in the series.). This is the underlying object with id, value and extensions. The accessor "getNumber" gives direct access to the value
         */
        public IntegerType getNumberElement() { 
          return this.number;
        }

        /**
         * @param value {@link #number} (The number of this image in the series.). This is the underlying object with id, value and extensions. The accessor "getNumber" gives direct access to the value
         */
        public ImagingStudySeriesInstanceComponent setNumberElement(IntegerType value) { 
          this.number = value;
          return this;
        }

        /**
         * @return The number of this image in the series.
         */
        public int getNumber() { 
          return this.number == null ? null : this.number.getValue();
        }

        /**
         * @param value The number of this image in the series.
         */
        public ImagingStudySeriesInstanceComponent setNumber(int value) { 
          if (value == -1)
            this.number = null;
          else {
            if (this.number == null)
              this.number = new IntegerType();
            this.number.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #uid} (Formal identifier for this image.). This is the underlying object with id, value and extensions. The accessor "getUid" gives direct access to the value
         */
        public OidType getUidElement() { 
          return this.uid;
        }

        /**
         * @param value {@link #uid} (Formal identifier for this image.). This is the underlying object with id, value and extensions. The accessor "getUid" gives direct access to the value
         */
        public ImagingStudySeriesInstanceComponent setUidElement(OidType value) { 
          this.uid = value;
          return this;
        }

        /**
         * @return Formal identifier for this image.
         */
        public String getUid() { 
          return this.uid == null ? null : this.uid.getValue();
        }

        /**
         * @param value Formal identifier for this image.
         */
        public ImagingStudySeriesInstanceComponent setUid(String value) { 
            if (this.uid == null)
              this.uid = new OidType();
            this.uid.setValue(value);
          return this;
        }

        /**
         * @return {@link #sopclass} (DICOM Image type.). This is the underlying object with id, value and extensions. The accessor "getSopclass" gives direct access to the value
         */
        public OidType getSopclassElement() { 
          return this.sopclass;
        }

        /**
         * @param value {@link #sopclass} (DICOM Image type.). This is the underlying object with id, value and extensions. The accessor "getSopclass" gives direct access to the value
         */
        public ImagingStudySeriesInstanceComponent setSopclassElement(OidType value) { 
          this.sopclass = value;
          return this;
        }

        /**
         * @return DICOM Image type.
         */
        public String getSopclass() { 
          return this.sopclass == null ? null : this.sopclass.getValue();
        }

        /**
         * @param value DICOM Image type.
         */
        public ImagingStudySeriesInstanceComponent setSopclass(String value) { 
            if (this.sopclass == null)
              this.sopclass = new OidType();
            this.sopclass.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (Type of instance (image etc) (0004,1430).). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public StringType getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Type of instance (image etc) (0004,1430).). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public ImagingStudySeriesInstanceComponent setTypeElement(StringType value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Type of instance (image etc) (0004,1430).
         */
        public String getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Type of instance (image etc) (0004,1430).
         */
        public ImagingStudySeriesInstanceComponent setType(String value) { 
          if (Utilities.noString(value))
            this.type = null;
          else {
            if (this.type == null)
              this.type = new StringType();
            this.type.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #title} (Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008).). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
         */
        public StringType getTitleElement() { 
          return this.title;
        }

        /**
         * @param value {@link #title} (Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008).). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
         */
        public ImagingStudySeriesInstanceComponent setTitleElement(StringType value) { 
          this.title = value;
          return this;
        }

        /**
         * @return Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008).
         */
        public String getTitle() { 
          return this.title == null ? null : this.title.getValue();
        }

        /**
         * @param value Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008).
         */
        public ImagingStudySeriesInstanceComponent setTitle(String value) { 
          if (Utilities.noString(value))
            this.title = null;
          else {
            if (this.title == null)
              this.title = new StringType();
            this.title.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #url} (WADO-RS url where image is available.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public UriType getUrlElement() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (WADO-RS url where image is available.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public ImagingStudySeriesInstanceComponent setUrlElement(UriType value) { 
          this.url = value;
          return this;
        }

        /**
         * @return WADO-RS url where image is available.
         */
        public String getUrl() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value WADO-RS url where image is available.
         */
        public ImagingStudySeriesInstanceComponent setUrl(String value) { 
          if (Utilities.noString(value))
            this.url = null;
          else {
            if (this.url == null)
              this.url = new UriType();
            this.url.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #attachment} (A FHIR resource with content for this instance.)
         */
        public Reference getAttachment() { 
          return this.attachment;
        }

        /**
         * @param value {@link #attachment} (A FHIR resource with content for this instance.)
         */
        public ImagingStudySeriesInstanceComponent setAttachment(Reference value) { 
          this.attachment = value;
          return this;
        }

        /**
         * @return {@link #attachment} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A FHIR resource with content for this instance.)
         */
        public Resource getAttachmentTarget() { 
          return this.attachmentTarget;
        }

        /**
         * @param value {@link #attachment} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A FHIR resource with content for this instance.)
         */
        public ImagingStudySeriesInstanceComponent setAttachmentTarget(Resource value) { 
          this.attachmentTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("number", "integer", "The number of this image in the series.", 0, java.lang.Integer.MAX_VALUE, number));
          childrenList.add(new Property("uid", "oid", "Formal identifier for this image.", 0, java.lang.Integer.MAX_VALUE, uid));
          childrenList.add(new Property("sopclass", "oid", "DICOM Image type.", 0, java.lang.Integer.MAX_VALUE, sopclass));
          childrenList.add(new Property("type", "string", "Type of instance (image etc) (0004,1430).", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("title", "string", "Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008).", 0, java.lang.Integer.MAX_VALUE, title));
          childrenList.add(new Property("url", "uri", "WADO-RS url where image is available.", 0, java.lang.Integer.MAX_VALUE, url));
          childrenList.add(new Property("attachment", "Reference(Any)", "A FHIR resource with content for this instance.", 0, java.lang.Integer.MAX_VALUE, attachment));
        }

      public ImagingStudySeriesInstanceComponent copy() {
        ImagingStudySeriesInstanceComponent dst = new ImagingStudySeriesInstanceComponent();
        copyValues(dst);
        dst.number = number == null ? null : number.copy();
        dst.uid = uid == null ? null : uid.copy();
        dst.sopclass = sopclass == null ? null : sopclass.copy();
        dst.type = type == null ? null : type.copy();
        dst.title = title == null ? null : title.copy();
        dst.url = url == null ? null : url.copy();
        dst.attachment = attachment == null ? null : attachment.copy();
        return dst;
      }

  }

    /**
     * Date and Time the study started.
     */
    protected DateTimeType started;

    /**
     * Who the images are of.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (Who the images are of.)
     */
    protected Patient subjectTarget;

    /**
     * Formal identifier for the study.
     */
    protected OidType uid;

    /**
     * Accession Number.
     */
    protected Identifier accession;

    /**
     * Other identifiers for the study.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * A list of the diagnostic orders that resulted in this imaging study being performed.
     */
    protected List<Reference> order = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (A list of the diagnostic orders that resulted in this imaging study being performed.)
     */
    protected List<DiagnosticOrder> orderTarget = new ArrayList<DiagnosticOrder>();


    /**
     * A list of all the Series.ImageModality values that are actual acquisition modalities, i.e. those in the DICOM Context Group 29 (value set OID 1.2.840.10008.6.1.19).
     */
    protected List<Enumeration<ImagingModality>> modalityList = new ArrayList<Enumeration<ImagingModality>>();

    /**
     * The requesting/referring physician.
     */
    protected Reference referrer;

    /**
     * The actual object that is the target of the reference (The requesting/referring physician.)
     */
    protected Practitioner referrerTarget;

    /**
     * Availability of study (online, offline or nearline).
     */
    protected Enumeration<InstanceAvailability> availability;

    /**
     * WADO-RS URI where Study is available.
     */
    protected UriType url;

    /**
     * Number of Series in Study.
     */
    protected IntegerType numberOfSeries;

    /**
     * Number of SOP Instances in Study.
     */
    protected IntegerType numberOfInstances;

    /**
     * Diagnoses etc provided with request.
     */
    protected StringType clinicalInformation;

    /**
     * Type of procedure performed.
     */
    protected List<Coding> procedure = new ArrayList<Coding>();

    /**
     * Who read study and interpreted the images.
     */
    protected Reference interpreter;

    /**
     * The actual object that is the target of the reference (Who read study and interpreted the images.)
     */
    protected Practitioner interpreterTarget;

    /**
     * Institution-generated description or classification of the Study (component) performed.
     */
    protected StringType description;

    /**
     * Each study has one or more series of image instances.
     */
    protected List<ImagingStudySeriesComponent> series = new ArrayList<ImagingStudySeriesComponent>();

    private static final long serialVersionUID = -1673496678L;

    public ImagingStudy() {
      super();
    }

    public ImagingStudy(Reference subject, OidType uid, IntegerType numberOfSeries, IntegerType numberOfInstances) {
      super();
      this.subject = subject;
      this.uid = uid;
      this.numberOfSeries = numberOfSeries;
      this.numberOfInstances = numberOfInstances;
    }

    /**
     * @return {@link #started} (Date and Time the study started.). This is the underlying object with id, value and extensions. The accessor "getStarted" gives direct access to the value
     */
    public DateTimeType getStartedElement() { 
      return this.started;
    }

    /**
     * @param value {@link #started} (Date and Time the study started.). This is the underlying object with id, value and extensions. The accessor "getStarted" gives direct access to the value
     */
    public ImagingStudy setStartedElement(DateTimeType value) { 
      this.started = value;
      return this;
    }

    /**
     * @return Date and Time the study started.
     */
    public DateAndTime getStarted() { 
      return this.started == null ? null : this.started.getValue();
    }

    /**
     * @param value Date and Time the study started.
     */
    public ImagingStudy setStarted(DateAndTime value) { 
      if (value == null)
        this.started = null;
      else {
        if (this.started == null)
          this.started = new DateTimeType();
        this.started.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (Who the images are of.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who the images are of.)
     */
    public ImagingStudy setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who the images are of.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who the images are of.)
     */
    public ImagingStudy setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #uid} (Formal identifier for the study.). This is the underlying object with id, value and extensions. The accessor "getUid" gives direct access to the value
     */
    public OidType getUidElement() { 
      return this.uid;
    }

    /**
     * @param value {@link #uid} (Formal identifier for the study.). This is the underlying object with id, value and extensions. The accessor "getUid" gives direct access to the value
     */
    public ImagingStudy setUidElement(OidType value) { 
      this.uid = value;
      return this;
    }

    /**
     * @return Formal identifier for the study.
     */
    public String getUid() { 
      return this.uid == null ? null : this.uid.getValue();
    }

    /**
     * @param value Formal identifier for the study.
     */
    public ImagingStudy setUid(String value) { 
        if (this.uid == null)
          this.uid = new OidType();
        this.uid.setValue(value);
      return this;
    }

    /**
     * @return {@link #accession} (Accession Number.)
     */
    public Identifier getAccession() { 
      return this.accession;
    }

    /**
     * @param value {@link #accession} (Accession Number.)
     */
    public ImagingStudy setAccession(Identifier value) { 
      this.accession = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Other identifiers for the study.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Other identifiers for the study.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #order} (A list of the diagnostic orders that resulted in this imaging study being performed.)
     */
    public List<Reference> getOrder() { 
      return this.order;
    }

    /**
     * @return {@link #order} (A list of the diagnostic orders that resulted in this imaging study being performed.)
     */
    // syntactic sugar
    public Reference addOrder() { //3
      Reference t = new Reference();
      this.order.add(t);
      return t;
    }

    /**
     * @return {@link #order} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. A list of the diagnostic orders that resulted in this imaging study being performed.)
     */
    public List<DiagnosticOrder> getOrderTarget() { 
      return this.orderTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #order} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. A list of the diagnostic orders that resulted in this imaging study being performed.)
     */
    public DiagnosticOrder addOrderTarget() { 
      DiagnosticOrder r = new DiagnosticOrder();
      this.orderTarget.add(r);
      return r;
    }

    /**
     * @return {@link #modalityList} (A list of all the Series.ImageModality values that are actual acquisition modalities, i.e. those in the DICOM Context Group 29 (value set OID 1.2.840.10008.6.1.19).)
     */
    public List<Enumeration<ImagingModality>> getModalityList() { 
      return this.modalityList;
    }

    /**
     * @return {@link #modalityList} (A list of all the Series.ImageModality values that are actual acquisition modalities, i.e. those in the DICOM Context Group 29 (value set OID 1.2.840.10008.6.1.19).)
     */
    // syntactic sugar
    public Enumeration<ImagingModality> addModalityListElement() {//2 
      Enumeration<ImagingModality> t = new Enumeration<ImagingModality>();
      this.modalityList.add(t);
      return t;
    }

    /**
     * @param value {@link #modalityList} (A list of all the Series.ImageModality values that are actual acquisition modalities, i.e. those in the DICOM Context Group 29 (value set OID 1.2.840.10008.6.1.19).)
     */
    public ImagingStudy addModalityList(ImagingModality value) { //1
      Enumeration<ImagingModality> t = new Enumeration<ImagingModality>();
      t.setValue(value);
      this.modalityList.add(t);
      return this;
    }

    /**
     * @param value {@link #modalityList} (A list of all the Series.ImageModality values that are actual acquisition modalities, i.e. those in the DICOM Context Group 29 (value set OID 1.2.840.10008.6.1.19).)
     */
    public boolean hasModalityList(ImagingModality value) { 
      for (Enumeration<ImagingModality> v : this.modalityList)
        if (v.equals(value)) // code
          return true;
      return false;
    }

    /**
     * @return {@link #referrer} (The requesting/referring physician.)
     */
    public Reference getReferrer() { 
      return this.referrer;
    }

    /**
     * @param value {@link #referrer} (The requesting/referring physician.)
     */
    public ImagingStudy setReferrer(Reference value) { 
      this.referrer = value;
      return this;
    }

    /**
     * @return {@link #referrer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The requesting/referring physician.)
     */
    public Practitioner getReferrerTarget() { 
      return this.referrerTarget;
    }

    /**
     * @param value {@link #referrer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The requesting/referring physician.)
     */
    public ImagingStudy setReferrerTarget(Practitioner value) { 
      this.referrerTarget = value;
      return this;
    }

    /**
     * @return {@link #availability} (Availability of study (online, offline or nearline).). This is the underlying object with id, value and extensions. The accessor "getAvailability" gives direct access to the value
     */
    public Enumeration<InstanceAvailability> getAvailabilityElement() { 
      return this.availability;
    }

    /**
     * @param value {@link #availability} (Availability of study (online, offline or nearline).). This is the underlying object with id, value and extensions. The accessor "getAvailability" gives direct access to the value
     */
    public ImagingStudy setAvailabilityElement(Enumeration<InstanceAvailability> value) { 
      this.availability = value;
      return this;
    }

    /**
     * @return Availability of study (online, offline or nearline).
     */
    public InstanceAvailability getAvailability() { 
      return this.availability == null ? null : this.availability.getValue();
    }

    /**
     * @param value Availability of study (online, offline or nearline).
     */
    public ImagingStudy setAvailability(InstanceAvailability value) { 
      if (value == null)
        this.availability = null;
      else {
        if (this.availability == null)
          this.availability = new Enumeration<InstanceAvailability>();
        this.availability.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #url} (WADO-RS URI where Study is available.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
     */
    public UriType getUrlElement() { 
      return this.url;
    }

    /**
     * @param value {@link #url} (WADO-RS URI where Study is available.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
     */
    public ImagingStudy setUrlElement(UriType value) { 
      this.url = value;
      return this;
    }

    /**
     * @return WADO-RS URI where Study is available.
     */
    public String getUrl() { 
      return this.url == null ? null : this.url.getValue();
    }

    /**
     * @param value WADO-RS URI where Study is available.
     */
    public ImagingStudy setUrl(String value) { 
      if (Utilities.noString(value))
        this.url = null;
      else {
        if (this.url == null)
          this.url = new UriType();
        this.url.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #numberOfSeries} (Number of Series in Study.). This is the underlying object with id, value and extensions. The accessor "getNumberOfSeries" gives direct access to the value
     */
    public IntegerType getNumberOfSeriesElement() { 
      return this.numberOfSeries;
    }

    /**
     * @param value {@link #numberOfSeries} (Number of Series in Study.). This is the underlying object with id, value and extensions. The accessor "getNumberOfSeries" gives direct access to the value
     */
    public ImagingStudy setNumberOfSeriesElement(IntegerType value) { 
      this.numberOfSeries = value;
      return this;
    }

    /**
     * @return Number of Series in Study.
     */
    public int getNumberOfSeries() { 
      return this.numberOfSeries == null ? null : this.numberOfSeries.getValue();
    }

    /**
     * @param value Number of Series in Study.
     */
    public ImagingStudy setNumberOfSeries(int value) { 
        if (this.numberOfSeries == null)
          this.numberOfSeries = new IntegerType();
        this.numberOfSeries.setValue(value);
      return this;
    }

    /**
     * @return {@link #numberOfInstances} (Number of SOP Instances in Study.). This is the underlying object with id, value and extensions. The accessor "getNumberOfInstances" gives direct access to the value
     */
    public IntegerType getNumberOfInstancesElement() { 
      return this.numberOfInstances;
    }

    /**
     * @param value {@link #numberOfInstances} (Number of SOP Instances in Study.). This is the underlying object with id, value and extensions. The accessor "getNumberOfInstances" gives direct access to the value
     */
    public ImagingStudy setNumberOfInstancesElement(IntegerType value) { 
      this.numberOfInstances = value;
      return this;
    }

    /**
     * @return Number of SOP Instances in Study.
     */
    public int getNumberOfInstances() { 
      return this.numberOfInstances == null ? null : this.numberOfInstances.getValue();
    }

    /**
     * @param value Number of SOP Instances in Study.
     */
    public ImagingStudy setNumberOfInstances(int value) { 
        if (this.numberOfInstances == null)
          this.numberOfInstances = new IntegerType();
        this.numberOfInstances.setValue(value);
      return this;
    }

    /**
     * @return {@link #clinicalInformation} (Diagnoses etc provided with request.). This is the underlying object with id, value and extensions. The accessor "getClinicalInformation" gives direct access to the value
     */
    public StringType getClinicalInformationElement() { 
      return this.clinicalInformation;
    }

    /**
     * @param value {@link #clinicalInformation} (Diagnoses etc provided with request.). This is the underlying object with id, value and extensions. The accessor "getClinicalInformation" gives direct access to the value
     */
    public ImagingStudy setClinicalInformationElement(StringType value) { 
      this.clinicalInformation = value;
      return this;
    }

    /**
     * @return Diagnoses etc provided with request.
     */
    public String getClinicalInformation() { 
      return this.clinicalInformation == null ? null : this.clinicalInformation.getValue();
    }

    /**
     * @param value Diagnoses etc provided with request.
     */
    public ImagingStudy setClinicalInformation(String value) { 
      if (Utilities.noString(value))
        this.clinicalInformation = null;
      else {
        if (this.clinicalInformation == null)
          this.clinicalInformation = new StringType();
        this.clinicalInformation.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #procedure} (Type of procedure performed.)
     */
    public List<Coding> getProcedure() { 
      return this.procedure;
    }

    /**
     * @return {@link #procedure} (Type of procedure performed.)
     */
    // syntactic sugar
    public Coding addProcedure() { //3
      Coding t = new Coding();
      this.procedure.add(t);
      return t;
    }

    /**
     * @return {@link #interpreter} (Who read study and interpreted the images.)
     */
    public Reference getInterpreter() { 
      return this.interpreter;
    }

    /**
     * @param value {@link #interpreter} (Who read study and interpreted the images.)
     */
    public ImagingStudy setInterpreter(Reference value) { 
      this.interpreter = value;
      return this;
    }

    /**
     * @return {@link #interpreter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who read study and interpreted the images.)
     */
    public Practitioner getInterpreterTarget() { 
      return this.interpreterTarget;
    }

    /**
     * @param value {@link #interpreter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who read study and interpreted the images.)
     */
    public ImagingStudy setInterpreterTarget(Practitioner value) { 
      this.interpreterTarget = value;
      return this;
    }

    /**
     * @return {@link #description} (Institution-generated description or classification of the Study (component) performed.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (Institution-generated description or classification of the Study (component) performed.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public ImagingStudy setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return Institution-generated description or classification of the Study (component) performed.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value Institution-generated description or classification of the Study (component) performed.
     */
    public ImagingStudy setDescription(String value) { 
      if (Utilities.noString(value))
        this.description = null;
      else {
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #series} (Each study has one or more series of image instances.)
     */
    public List<ImagingStudySeriesComponent> getSeries() { 
      return this.series;
    }

    /**
     * @return {@link #series} (Each study has one or more series of image instances.)
     */
    // syntactic sugar
    public ImagingStudySeriesComponent addSeries() { //3
      ImagingStudySeriesComponent t = new ImagingStudySeriesComponent();
      this.series.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("started", "dateTime", "Date and Time the study started.", 0, java.lang.Integer.MAX_VALUE, started));
        childrenList.add(new Property("subject", "Reference(Patient)", "Who the images are of.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("uid", "oid", "Formal identifier for the study.", 0, java.lang.Integer.MAX_VALUE, uid));
        childrenList.add(new Property("accession", "Identifier", "Accession Number.", 0, java.lang.Integer.MAX_VALUE, accession));
        childrenList.add(new Property("identifier", "Identifier", "Other identifiers for the study.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("order", "Reference(DiagnosticOrder)", "A list of the diagnostic orders that resulted in this imaging study being performed.", 0, java.lang.Integer.MAX_VALUE, order));
        childrenList.add(new Property("modalityList", "code", "A list of all the Series.ImageModality values that are actual acquisition modalities, i.e. those in the DICOM Context Group 29 (value set OID 1.2.840.10008.6.1.19).", 0, java.lang.Integer.MAX_VALUE, modalityList));
        childrenList.add(new Property("referrer", "Reference(Practitioner)", "The requesting/referring physician.", 0, java.lang.Integer.MAX_VALUE, referrer));
        childrenList.add(new Property("availability", "code", "Availability of study (online, offline or nearline).", 0, java.lang.Integer.MAX_VALUE, availability));
        childrenList.add(new Property("url", "uri", "WADO-RS URI where Study is available.", 0, java.lang.Integer.MAX_VALUE, url));
        childrenList.add(new Property("numberOfSeries", "integer", "Number of Series in Study.", 0, java.lang.Integer.MAX_VALUE, numberOfSeries));
        childrenList.add(new Property("numberOfInstances", "integer", "Number of SOP Instances in Study.", 0, java.lang.Integer.MAX_VALUE, numberOfInstances));
        childrenList.add(new Property("clinicalInformation", "string", "Diagnoses etc provided with request.", 0, java.lang.Integer.MAX_VALUE, clinicalInformation));
        childrenList.add(new Property("procedure", "Coding", "Type of procedure performed.", 0, java.lang.Integer.MAX_VALUE, procedure));
        childrenList.add(new Property("interpreter", "Reference(Practitioner)", "Who read study and interpreted the images.", 0, java.lang.Integer.MAX_VALUE, interpreter));
        childrenList.add(new Property("description", "string", "Institution-generated description or classification of the Study (component) performed.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("series", "", "Each study has one or more series of image instances.", 0, java.lang.Integer.MAX_VALUE, series));
      }

      public ImagingStudy copy() {
        ImagingStudy dst = new ImagingStudy();
        copyValues(dst);
        dst.started = started == null ? null : started.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.uid = uid == null ? null : uid.copy();
        dst.accession = accession == null ? null : accession.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.order = new ArrayList<Reference>();
        for (Reference i : order)
          dst.order.add(i.copy());
        dst.modalityList = new ArrayList<Enumeration<ImagingModality>>();
        for (Enumeration<ImagingModality> i : modalityList)
          dst.modalityList.add(i.copy());
        dst.referrer = referrer == null ? null : referrer.copy();
        dst.availability = availability == null ? null : availability.copy();
        dst.url = url == null ? null : url.copy();
        dst.numberOfSeries = numberOfSeries == null ? null : numberOfSeries.copy();
        dst.numberOfInstances = numberOfInstances == null ? null : numberOfInstances.copy();
        dst.clinicalInformation = clinicalInformation == null ? null : clinicalInformation.copy();
        dst.procedure = new ArrayList<Coding>();
        for (Coding i : procedure)
          dst.procedure.add(i.copy());
        dst.interpreter = interpreter == null ? null : interpreter.copy();
        dst.description = description == null ? null : description.copy();
        dst.series = new ArrayList<ImagingStudySeriesComponent>();
        for (ImagingStudySeriesComponent i : series)
          dst.series.add(i.copy());
        return dst;
      }

      protected ImagingStudy typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ImagingStudy;
   }


}

