package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2013, HL7, Inc.
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

// Generated on Tue, Oct 8, 2013 20:20+1100 for FHIR v0.12

import java.util.*;

/**
 * Manifest of a set of images produced in study. The set of images may include every image in the study, or it may be an incomplete sample, such as a list of key images.
 */
public class ImagingStudy extends Resource {

    public enum ImagingModality {
        aR, // 
        bMD, // 
        bDUS, // 
        ePS, // 
        cR, // 
        cT, // 
        dX, // 
        eCG, // 
        eS, // 
        xC, // 
        gM, // 
        hD, // 
        iO, // 
        iVOCT, // 
        iVUS, // 
        kER, // 
        lEN, // 
        mR, // 
        mG, // 
        nM, // 
        oAM, // 
        oCT, // 
        oPM, // 
        oP, // 
        oPR, // 
        oPT, // 
        oPV, // 
        pX, // 
        pT, // 
        rF, // 
        rG, // 
        sM, // 
        sRF, // 
        uS, // 
        vA, // 
        xA, // 
        Null; // added to help the parsers
        public static ImagingModality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return aR;
        if ("BMD".equals(codeString))
          return bMD;
        if ("BDUS".equals(codeString))
          return bDUS;
        if ("EPS".equals(codeString))
          return ePS;
        if ("CR".equals(codeString))
          return cR;
        if ("CT".equals(codeString))
          return cT;
        if ("DX".equals(codeString))
          return dX;
        if ("ECG".equals(codeString))
          return eCG;
        if ("ES".equals(codeString))
          return eS;
        if ("XC".equals(codeString))
          return xC;
        if ("GM".equals(codeString))
          return gM;
        if ("HD".equals(codeString))
          return hD;
        if ("IO".equals(codeString))
          return iO;
        if ("IVOCT".equals(codeString))
          return iVOCT;
        if ("IVUS".equals(codeString))
          return iVUS;
        if ("KER".equals(codeString))
          return kER;
        if ("LEN".equals(codeString))
          return lEN;
        if ("MR".equals(codeString))
          return mR;
        if ("MG".equals(codeString))
          return mG;
        if ("NM".equals(codeString))
          return nM;
        if ("OAM".equals(codeString))
          return oAM;
        if ("OCT".equals(codeString))
          return oCT;
        if ("OPM".equals(codeString))
          return oPM;
        if ("OP".equals(codeString))
          return oP;
        if ("OPR".equals(codeString))
          return oPR;
        if ("OPT".equals(codeString))
          return oPT;
        if ("OPV".equals(codeString))
          return oPV;
        if ("PX".equals(codeString))
          return pX;
        if ("PT".equals(codeString))
          return pT;
        if ("RF".equals(codeString))
          return rF;
        if ("RG".equals(codeString))
          return rG;
        if ("SM".equals(codeString))
          return sM;
        if ("SRF".equals(codeString))
          return sRF;
        if ("US".equals(codeString))
          return uS;
        if ("VA".equals(codeString))
          return vA;
        if ("XA".equals(codeString))
          return xA;
        throw new Exception("Unknown ImagingModality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case aR: return "AR";
            case bMD: return "BMD";
            case bDUS: return "BDUS";
            case ePS: return "EPS";
            case cR: return "CR";
            case cT: return "CT";
            case dX: return "DX";
            case eCG: return "ECG";
            case eS: return "ES";
            case xC: return "XC";
            case gM: return "GM";
            case hD: return "HD";
            case iO: return "IO";
            case iVOCT: return "IVOCT";
            case iVUS: return "IVUS";
            case kER: return "KER";
            case lEN: return "LEN";
            case mR: return "MR";
            case mG: return "MG";
            case nM: return "NM";
            case oAM: return "OAM";
            case oCT: return "OCT";
            case oPM: return "OPM";
            case oP: return "OP";
            case oPR: return "OPR";
            case oPT: return "OPT";
            case oPV: return "OPV";
            case pX: return "PX";
            case pT: return "PT";
            case rF: return "RF";
            case rG: return "RG";
            case sM: return "SM";
            case sRF: return "SRF";
            case uS: return "US";
            case vA: return "VA";
            case xA: return "XA";
            default: return "?";
          }
        }
    }

  public class ImagingModalityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return ImagingModality.aR;
        if ("BMD".equals(codeString))
          return ImagingModality.bMD;
        if ("BDUS".equals(codeString))
          return ImagingModality.bDUS;
        if ("EPS".equals(codeString))
          return ImagingModality.ePS;
        if ("CR".equals(codeString))
          return ImagingModality.cR;
        if ("CT".equals(codeString))
          return ImagingModality.cT;
        if ("DX".equals(codeString))
          return ImagingModality.dX;
        if ("ECG".equals(codeString))
          return ImagingModality.eCG;
        if ("ES".equals(codeString))
          return ImagingModality.eS;
        if ("XC".equals(codeString))
          return ImagingModality.xC;
        if ("GM".equals(codeString))
          return ImagingModality.gM;
        if ("HD".equals(codeString))
          return ImagingModality.hD;
        if ("IO".equals(codeString))
          return ImagingModality.iO;
        if ("IVOCT".equals(codeString))
          return ImagingModality.iVOCT;
        if ("IVUS".equals(codeString))
          return ImagingModality.iVUS;
        if ("KER".equals(codeString))
          return ImagingModality.kER;
        if ("LEN".equals(codeString))
          return ImagingModality.lEN;
        if ("MR".equals(codeString))
          return ImagingModality.mR;
        if ("MG".equals(codeString))
          return ImagingModality.mG;
        if ("NM".equals(codeString))
          return ImagingModality.nM;
        if ("OAM".equals(codeString))
          return ImagingModality.oAM;
        if ("OCT".equals(codeString))
          return ImagingModality.oCT;
        if ("OPM".equals(codeString))
          return ImagingModality.oPM;
        if ("OP".equals(codeString))
          return ImagingModality.oP;
        if ("OPR".equals(codeString))
          return ImagingModality.oPR;
        if ("OPT".equals(codeString))
          return ImagingModality.oPT;
        if ("OPV".equals(codeString))
          return ImagingModality.oPV;
        if ("PX".equals(codeString))
          return ImagingModality.pX;
        if ("PT".equals(codeString))
          return ImagingModality.pT;
        if ("RF".equals(codeString))
          return ImagingModality.rF;
        if ("RG".equals(codeString))
          return ImagingModality.rG;
        if ("SM".equals(codeString))
          return ImagingModality.sM;
        if ("SRF".equals(codeString))
          return ImagingModality.sRF;
        if ("US".equals(codeString))
          return ImagingModality.uS;
        if ("VA".equals(codeString))
          return ImagingModality.vA;
        if ("XA".equals(codeString))
          return ImagingModality.xA;
        throw new Exception("Unknown ImagingModality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ImagingModality.aR)
        return "AR";
      if (code == ImagingModality.bMD)
        return "BMD";
      if (code == ImagingModality.bDUS)
        return "BDUS";
      if (code == ImagingModality.ePS)
        return "EPS";
      if (code == ImagingModality.cR)
        return "CR";
      if (code == ImagingModality.cT)
        return "CT";
      if (code == ImagingModality.dX)
        return "DX";
      if (code == ImagingModality.eCG)
        return "ECG";
      if (code == ImagingModality.eS)
        return "ES";
      if (code == ImagingModality.xC)
        return "XC";
      if (code == ImagingModality.gM)
        return "GM";
      if (code == ImagingModality.hD)
        return "HD";
      if (code == ImagingModality.iO)
        return "IO";
      if (code == ImagingModality.iVOCT)
        return "IVOCT";
      if (code == ImagingModality.iVUS)
        return "IVUS";
      if (code == ImagingModality.kER)
        return "KER";
      if (code == ImagingModality.lEN)
        return "LEN";
      if (code == ImagingModality.mR)
        return "MR";
      if (code == ImagingModality.mG)
        return "MG";
      if (code == ImagingModality.nM)
        return "NM";
      if (code == ImagingModality.oAM)
        return "OAM";
      if (code == ImagingModality.oCT)
        return "OCT";
      if (code == ImagingModality.oPM)
        return "OPM";
      if (code == ImagingModality.oP)
        return "OP";
      if (code == ImagingModality.oPR)
        return "OPR";
      if (code == ImagingModality.oPT)
        return "OPT";
      if (code == ImagingModality.oPV)
        return "OPV";
      if (code == ImagingModality.pX)
        return "PX";
      if (code == ImagingModality.pT)
        return "PT";
      if (code == ImagingModality.rF)
        return "RF";
      if (code == ImagingModality.rG)
        return "RG";
      if (code == ImagingModality.sM)
        return "SM";
      if (code == ImagingModality.sRF)
        return "SRF";
      if (code == ImagingModality.uS)
        return "US";
      if (code == ImagingModality.vA)
        return "VA";
      if (code == ImagingModality.xA)
        return "XA";
      return "?";
      }
    }

    public enum InstanceAvailability {
        oNLINE, // Resources are immediately available,.
        oFFLINE, // Resources need to be retrieved by manual intervention.
        nEARLINE, // Resources need to be retrieved from relatively slow media.
        uNAVAILABLE, // Resources cannot be retrieved.
        Null; // added to help the parsers
        public static InstanceAvailability fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ONLINE".equals(codeString))
          return oNLINE;
        if ("OFFLINE".equals(codeString))
          return oFFLINE;
        if ("NEARLINE".equals(codeString))
          return nEARLINE;
        if ("UNAVAILABLE".equals(codeString))
          return uNAVAILABLE;
        throw new Exception("Unknown InstanceAvailability code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case oNLINE: return "ONLINE";
            case oFFLINE: return "OFFLINE";
            case nEARLINE: return "NEARLINE";
            case uNAVAILABLE: return "UNAVAILABLE";
            default: return "?";
          }
        }
    }

  public class InstanceAvailabilityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ONLINE".equals(codeString))
          return InstanceAvailability.oNLINE;
        if ("OFFLINE".equals(codeString))
          return InstanceAvailability.oFFLINE;
        if ("NEARLINE".equals(codeString))
          return InstanceAvailability.nEARLINE;
        if ("UNAVAILABLE".equals(codeString))
          return InstanceAvailability.uNAVAILABLE;
        throw new Exception("Unknown InstanceAvailability code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == InstanceAvailability.oNLINE)
        return "ONLINE";
      if (code == InstanceAvailability.oFFLINE)
        return "OFFLINE";
      if (code == InstanceAvailability.nEARLINE)
        return "NEARLINE";
      if (code == InstanceAvailability.uNAVAILABLE)
        return "UNAVAILABLE";
      return "?";
      }
    }

    public enum Modality {
        aR, // 
        aU, // 
        bDUS, // 
        bI, // 
        bMD, // 
        cR, // 
        cT, // 
        dG, // 
        dX, // 
        eCG, // 
        ePS, // 
        eS, // 
        gM, // 
        hC, // 
        hD, // 
        iO, // 
        iVOCT, // 
        iVUS, // 
        kER, // 
        kO, // 
        lEN, // 
        lS, // 
        mG, // 
        mR, // 
        nM, // 
        oAM, // 
        oCT, // 
        oP, // 
        oPM, // 
        oPT, // 
        oPV, // 
        oT, // 
        pR, // 
        pT, // 
        pX, // 
        rEG, // 
        rF, // 
        rG, // 
        rTDOSE, // 
        rTIMAGE, // 
        rTPLAN, // 
        rTRECORD, // 
        rTSTRUCT, // 
        sEG, // 
        sM, // 
        sMR, // 
        sR, // 
        sRF, // 
        tG, // 
        uS, // 
        vA, // 
        xA, // 
        xC, // 
        Null; // added to help the parsers
        public static Modality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return aR;
        if ("AU".equals(codeString))
          return aU;
        if ("BDUS".equals(codeString))
          return bDUS;
        if ("BI".equals(codeString))
          return bI;
        if ("BMD".equals(codeString))
          return bMD;
        if ("CR".equals(codeString))
          return cR;
        if ("CT".equals(codeString))
          return cT;
        if ("DG".equals(codeString))
          return dG;
        if ("DX".equals(codeString))
          return dX;
        if ("ECG".equals(codeString))
          return eCG;
        if ("EPS".equals(codeString))
          return ePS;
        if ("ES".equals(codeString))
          return eS;
        if ("GM".equals(codeString))
          return gM;
        if ("HC".equals(codeString))
          return hC;
        if ("HD".equals(codeString))
          return hD;
        if ("IO".equals(codeString))
          return iO;
        if ("IVOCT".equals(codeString))
          return iVOCT;
        if ("IVUS".equals(codeString))
          return iVUS;
        if ("KER".equals(codeString))
          return kER;
        if ("KO".equals(codeString))
          return kO;
        if ("LEN".equals(codeString))
          return lEN;
        if ("LS".equals(codeString))
          return lS;
        if ("MG".equals(codeString))
          return mG;
        if ("MR".equals(codeString))
          return mR;
        if ("NM".equals(codeString))
          return nM;
        if ("OAM".equals(codeString))
          return oAM;
        if ("OCT".equals(codeString))
          return oCT;
        if ("OP".equals(codeString))
          return oP;
        if ("OPM".equals(codeString))
          return oPM;
        if ("OPT".equals(codeString))
          return oPT;
        if ("OPV".equals(codeString))
          return oPV;
        if ("OT".equals(codeString))
          return oT;
        if ("PR".equals(codeString))
          return pR;
        if ("PT".equals(codeString))
          return pT;
        if ("PX".equals(codeString))
          return pX;
        if ("REG".equals(codeString))
          return rEG;
        if ("RF".equals(codeString))
          return rF;
        if ("RG".equals(codeString))
          return rG;
        if ("RTDOSE".equals(codeString))
          return rTDOSE;
        if ("RTIMAGE".equals(codeString))
          return rTIMAGE;
        if ("RTPLAN".equals(codeString))
          return rTPLAN;
        if ("RTRECORD".equals(codeString))
          return rTRECORD;
        if ("RTSTRUCT".equals(codeString))
          return rTSTRUCT;
        if ("SEG".equals(codeString))
          return sEG;
        if ("SM".equals(codeString))
          return sM;
        if ("SMR".equals(codeString))
          return sMR;
        if ("SR".equals(codeString))
          return sR;
        if ("SRF".equals(codeString))
          return sRF;
        if ("TG".equals(codeString))
          return tG;
        if ("US".equals(codeString))
          return uS;
        if ("VA".equals(codeString))
          return vA;
        if ("XA".equals(codeString))
          return xA;
        if ("XC".equals(codeString))
          return xC;
        throw new Exception("Unknown Modality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case aR: return "AR";
            case aU: return "AU";
            case bDUS: return "BDUS";
            case bI: return "BI";
            case bMD: return "BMD";
            case cR: return "CR";
            case cT: return "CT";
            case dG: return "DG";
            case dX: return "DX";
            case eCG: return "ECG";
            case ePS: return "EPS";
            case eS: return "ES";
            case gM: return "GM";
            case hC: return "HC";
            case hD: return "HD";
            case iO: return "IO";
            case iVOCT: return "IVOCT";
            case iVUS: return "IVUS";
            case kER: return "KER";
            case kO: return "KO";
            case lEN: return "LEN";
            case lS: return "LS";
            case mG: return "MG";
            case mR: return "MR";
            case nM: return "NM";
            case oAM: return "OAM";
            case oCT: return "OCT";
            case oP: return "OP";
            case oPM: return "OPM";
            case oPT: return "OPT";
            case oPV: return "OPV";
            case oT: return "OT";
            case pR: return "PR";
            case pT: return "PT";
            case pX: return "PX";
            case rEG: return "REG";
            case rF: return "RF";
            case rG: return "RG";
            case rTDOSE: return "RTDOSE";
            case rTIMAGE: return "RTIMAGE";
            case rTPLAN: return "RTPLAN";
            case rTRECORD: return "RTRECORD";
            case rTSTRUCT: return "RTSTRUCT";
            case sEG: return "SEG";
            case sM: return "SM";
            case sMR: return "SMR";
            case sR: return "SR";
            case sRF: return "SRF";
            case tG: return "TG";
            case uS: return "US";
            case vA: return "VA";
            case xA: return "XA";
            case xC: return "XC";
            default: return "?";
          }
        }
    }

  public class ModalityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return Modality.aR;
        if ("AU".equals(codeString))
          return Modality.aU;
        if ("BDUS".equals(codeString))
          return Modality.bDUS;
        if ("BI".equals(codeString))
          return Modality.bI;
        if ("BMD".equals(codeString))
          return Modality.bMD;
        if ("CR".equals(codeString))
          return Modality.cR;
        if ("CT".equals(codeString))
          return Modality.cT;
        if ("DG".equals(codeString))
          return Modality.dG;
        if ("DX".equals(codeString))
          return Modality.dX;
        if ("ECG".equals(codeString))
          return Modality.eCG;
        if ("EPS".equals(codeString))
          return Modality.ePS;
        if ("ES".equals(codeString))
          return Modality.eS;
        if ("GM".equals(codeString))
          return Modality.gM;
        if ("HC".equals(codeString))
          return Modality.hC;
        if ("HD".equals(codeString))
          return Modality.hD;
        if ("IO".equals(codeString))
          return Modality.iO;
        if ("IVOCT".equals(codeString))
          return Modality.iVOCT;
        if ("IVUS".equals(codeString))
          return Modality.iVUS;
        if ("KER".equals(codeString))
          return Modality.kER;
        if ("KO".equals(codeString))
          return Modality.kO;
        if ("LEN".equals(codeString))
          return Modality.lEN;
        if ("LS".equals(codeString))
          return Modality.lS;
        if ("MG".equals(codeString))
          return Modality.mG;
        if ("MR".equals(codeString))
          return Modality.mR;
        if ("NM".equals(codeString))
          return Modality.nM;
        if ("OAM".equals(codeString))
          return Modality.oAM;
        if ("OCT".equals(codeString))
          return Modality.oCT;
        if ("OP".equals(codeString))
          return Modality.oP;
        if ("OPM".equals(codeString))
          return Modality.oPM;
        if ("OPT".equals(codeString))
          return Modality.oPT;
        if ("OPV".equals(codeString))
          return Modality.oPV;
        if ("OT".equals(codeString))
          return Modality.oT;
        if ("PR".equals(codeString))
          return Modality.pR;
        if ("PT".equals(codeString))
          return Modality.pT;
        if ("PX".equals(codeString))
          return Modality.pX;
        if ("REG".equals(codeString))
          return Modality.rEG;
        if ("RF".equals(codeString))
          return Modality.rF;
        if ("RG".equals(codeString))
          return Modality.rG;
        if ("RTDOSE".equals(codeString))
          return Modality.rTDOSE;
        if ("RTIMAGE".equals(codeString))
          return Modality.rTIMAGE;
        if ("RTPLAN".equals(codeString))
          return Modality.rTPLAN;
        if ("RTRECORD".equals(codeString))
          return Modality.rTRECORD;
        if ("RTSTRUCT".equals(codeString))
          return Modality.rTSTRUCT;
        if ("SEG".equals(codeString))
          return Modality.sEG;
        if ("SM".equals(codeString))
          return Modality.sM;
        if ("SMR".equals(codeString))
          return Modality.sMR;
        if ("SR".equals(codeString))
          return Modality.sR;
        if ("SRF".equals(codeString))
          return Modality.sRF;
        if ("TG".equals(codeString))
          return Modality.tG;
        if ("US".equals(codeString))
          return Modality.uS;
        if ("VA".equals(codeString))
          return Modality.vA;
        if ("XA".equals(codeString))
          return Modality.xA;
        if ("XC".equals(codeString))
          return Modality.xC;
        throw new Exception("Unknown Modality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Modality.aR)
        return "AR";
      if (code == Modality.aU)
        return "AU";
      if (code == Modality.bDUS)
        return "BDUS";
      if (code == Modality.bI)
        return "BI";
      if (code == Modality.bMD)
        return "BMD";
      if (code == Modality.cR)
        return "CR";
      if (code == Modality.cT)
        return "CT";
      if (code == Modality.dG)
        return "DG";
      if (code == Modality.dX)
        return "DX";
      if (code == Modality.eCG)
        return "ECG";
      if (code == Modality.ePS)
        return "EPS";
      if (code == Modality.eS)
        return "ES";
      if (code == Modality.gM)
        return "GM";
      if (code == Modality.hC)
        return "HC";
      if (code == Modality.hD)
        return "HD";
      if (code == Modality.iO)
        return "IO";
      if (code == Modality.iVOCT)
        return "IVOCT";
      if (code == Modality.iVUS)
        return "IVUS";
      if (code == Modality.kER)
        return "KER";
      if (code == Modality.kO)
        return "KO";
      if (code == Modality.lEN)
        return "LEN";
      if (code == Modality.lS)
        return "LS";
      if (code == Modality.mG)
        return "MG";
      if (code == Modality.mR)
        return "MR";
      if (code == Modality.nM)
        return "NM";
      if (code == Modality.oAM)
        return "OAM";
      if (code == Modality.oCT)
        return "OCT";
      if (code == Modality.oP)
        return "OP";
      if (code == Modality.oPM)
        return "OPM";
      if (code == Modality.oPT)
        return "OPT";
      if (code == Modality.oPV)
        return "OPV";
      if (code == Modality.oT)
        return "OT";
      if (code == Modality.pR)
        return "PR";
      if (code == Modality.pT)
        return "PT";
      if (code == Modality.pX)
        return "PX";
      if (code == Modality.rEG)
        return "REG";
      if (code == Modality.rF)
        return "RF";
      if (code == Modality.rG)
        return "RG";
      if (code == Modality.rTDOSE)
        return "RTDOSE";
      if (code == Modality.rTIMAGE)
        return "RTIMAGE";
      if (code == Modality.rTPLAN)
        return "RTPLAN";
      if (code == Modality.rTRECORD)
        return "RTRECORD";
      if (code == Modality.rTSTRUCT)
        return "RTSTRUCT";
      if (code == Modality.sEG)
        return "SEG";
      if (code == Modality.sM)
        return "SM";
      if (code == Modality.sMR)
        return "SMR";
      if (code == Modality.sR)
        return "SR";
      if (code == Modality.sRF)
        return "SRF";
      if (code == Modality.tG)
        return "TG";
      if (code == Modality.uS)
        return "US";
      if (code == Modality.vA)
        return "VA";
      if (code == Modality.xA)
        return "XA";
      if (code == Modality.xC)
        return "XC";
      return "?";
      }
    }

    public class ImagingStudySeriesComponent extends Element {
        /**
         * The number of this series in the overall sequence.
         */
        protected Integer number;

        /**
         * The modality of this series sequence.
         */
        protected Enumeration<Modality> modality;

        /**
         * Formal identifier for this series.
         */
        protected Oid uid;

        /**
         * A description of the series.
         */
        protected String_ description;

        /**
         * Sequence that contains attributes from the.
         */
        protected Integer numberOfInstances;

        /**
         * Availability of series (online, offline or nearlnie).
         */
        protected Enumeration<InstanceAvailability> availability;

        /**
         * WADO-RS URI where Series is available.
         */
        protected Uri url;

        /**
         * Body part examined. See  DICOM Part 16 Annex L for the mapping from DICOM to Snomed.
         */
        protected Coding bodySite;

        /**
         * When the series started.
         */
        protected DateTime dateTime;

        /**
         * A single image taken from a patient.
         */
        protected List<ImagingStudySeriesInstanceComponent> instance = new ArrayList<ImagingStudySeriesInstanceComponent>();

        public Integer getNumber() { 
          return this.number;
        }

        public void setNumber(Integer value) { 
          this.number = value;
        }

        public int getNumberSimple() { 
          return this.number == null ? null : this.number.getValue();
        }

        public void setNumberSimple(int value) { 
          if (value == -1)
            this.number = null;
          else {
            if (this.number == null)
              this.number = new Integer();
            this.number.setValue(value);
          }
        }

        public Enumeration<Modality> getModality() { 
          return this.modality;
        }

        public void setModality(Enumeration<Modality> value) { 
          this.modality = value;
        }

        public Modality getModalitySimple() { 
          return this.modality == null ? null : this.modality.getValue();
        }

        public void setModalitySimple(Modality value) { 
            if (this.modality == null)
              this.modality = new Enumeration<Modality>();
            this.modality.setValue(value);
        }

        public Oid getUid() { 
          return this.uid;
        }

        public void setUid(Oid value) { 
          this.uid = value;
        }

        public String getUidSimple() { 
          return this.uid == null ? null : this.uid.getValue();
        }

        public void setUidSimple(String value) { 
            if (this.uid == null)
              this.uid = new Oid();
            this.uid.setValue(value);
        }

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
        }

        public Integer getNumberOfInstances() { 
          return this.numberOfInstances;
        }

        public void setNumberOfInstances(Integer value) { 
          this.numberOfInstances = value;
        }

        public int getNumberOfInstancesSimple() { 
          return this.numberOfInstances == null ? null : this.numberOfInstances.getValue();
        }

        public void setNumberOfInstancesSimple(int value) { 
            if (this.numberOfInstances == null)
              this.numberOfInstances = new Integer();
            this.numberOfInstances.setValue(value);
        }

        public Enumeration<InstanceAvailability> getAvailability() { 
          return this.availability;
        }

        public void setAvailability(Enumeration<InstanceAvailability> value) { 
          this.availability = value;
        }

        public InstanceAvailability getAvailabilitySimple() { 
          return this.availability == null ? null : this.availability.getValue();
        }

        public void setAvailabilitySimple(InstanceAvailability value) { 
          if (value == null)
            this.availability = null;
          else {
            if (this.availability == null)
              this.availability = new Enumeration<InstanceAvailability>();
            this.availability.setValue(value);
          }
        }

        public Uri getUrl() { 
          return this.url;
        }

        public void setUrl(Uri value) { 
          this.url = value;
        }

        public String getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        public void setUrlSimple(String value) { 
          if (value == null)
            this.url = null;
          else {
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
          }
        }

        public Coding getBodySite() { 
          return this.bodySite;
        }

        public void setBodySite(Coding value) { 
          this.bodySite = value;
        }

        public DateTime getDateTime() { 
          return this.dateTime;
        }

        public void setDateTime(DateTime value) { 
          this.dateTime = value;
        }

        public String getDateTimeSimple() { 
          return this.dateTime == null ? null : this.dateTime.getValue();
        }

        public void setDateTimeSimple(String value) { 
          if (value == null)
            this.dateTime = null;
          else {
            if (this.dateTime == null)
              this.dateTime = new DateTime();
            this.dateTime.setValue(value);
          }
        }

        public List<ImagingStudySeriesInstanceComponent> getInstance() { 
          return this.instance;
        }

    // syntactic sugar
        public ImagingStudySeriesInstanceComponent addInstance() { 
          ImagingStudySeriesInstanceComponent t = new ImagingStudySeriesInstanceComponent();
          this.instance.add(t);
          return t;
        }

      public ImagingStudySeriesComponent copy(ImagingStudy e) {
        ImagingStudySeriesComponent dst = e.new ImagingStudySeriesComponent();
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
          dst.instance.add(i.copy(e));
        return dst;
      }

  }

    public class ImagingStudySeriesInstanceComponent extends Element {
        /**
         * The number of this image in the series.
         */
        protected Integer number;

        /**
         * Formal identifier for this image.
         */
        protected Oid uid;

        /**
         * DICOM Image type.
         */
        protected Oid sopclass;

        /**
         * Type of instance (0004,1430).
         */
        protected String_ type;

        /**
         * Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008).
         */
        protected String_ title;

        /**
         * WADO-RS url where image is available.
         */
        protected Uri url;

        /**
         * A FHIR resource with content for this instance.
         */
        protected ResourceReference attachment;

        public Integer getNumber() { 
          return this.number;
        }

        public void setNumber(Integer value) { 
          this.number = value;
        }

        public int getNumberSimple() { 
          return this.number == null ? null : this.number.getValue();
        }

        public void setNumberSimple(int value) { 
          if (value == -1)
            this.number = null;
          else {
            if (this.number == null)
              this.number = new Integer();
            this.number.setValue(value);
          }
        }

        public Oid getUid() { 
          return this.uid;
        }

        public void setUid(Oid value) { 
          this.uid = value;
        }

        public String getUidSimple() { 
          return this.uid == null ? null : this.uid.getValue();
        }

        public void setUidSimple(String value) { 
            if (this.uid == null)
              this.uid = new Oid();
            this.uid.setValue(value);
        }

        public Oid getSopclass() { 
          return this.sopclass;
        }

        public void setSopclass(Oid value) { 
          this.sopclass = value;
        }

        public String getSopclassSimple() { 
          return this.sopclass == null ? null : this.sopclass.getValue();
        }

        public void setSopclassSimple(String value) { 
            if (this.sopclass == null)
              this.sopclass = new Oid();
            this.sopclass.setValue(value);
        }

        public String_ getType() { 
          return this.type;
        }

        public void setType(String_ value) { 
          this.type = value;
        }

        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(String value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new String_();
            this.type.setValue(value);
          }
        }

        public String_ getTitle() { 
          return this.title;
        }

        public void setTitle(String_ value) { 
          this.title = value;
        }

        public String getTitleSimple() { 
          return this.title == null ? null : this.title.getValue();
        }

        public void setTitleSimple(String value) { 
          if (value == null)
            this.title = null;
          else {
            if (this.title == null)
              this.title = new String_();
            this.title.setValue(value);
          }
        }

        public Uri getUrl() { 
          return this.url;
        }

        public void setUrl(Uri value) { 
          this.url = value;
        }

        public String getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        public void setUrlSimple(String value) { 
          if (value == null)
            this.url = null;
          else {
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
          }
        }

        public ResourceReference getAttachment() { 
          return this.attachment;
        }

        public void setAttachment(ResourceReference value) { 
          this.attachment = value;
        }

      public ImagingStudySeriesInstanceComponent copy(ImagingStudy e) {
        ImagingStudySeriesInstanceComponent dst = e.new ImagingStudySeriesInstanceComponent();
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
     * Date and Time the study took place.
     */
    protected DateTime dateTime;

    /**
     * Who the images are of.
     */
    protected ResourceReference subject;

    /**
     * Formal identifier for the study.
     */
    protected Oid uid;

    /**
     * Accession Number.
     */
    protected Identifier accessionNo;

    /**
     * Other identifiers for the study.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * A list of the diagnostic orders that resulted in this imaging study being performed.
     */
    protected List<ResourceReference> order = new ArrayList<ResourceReference>();

    /**
     * A list of all the Series.ImageModality values that are actual acquisition modalities, i.e. those in the DICOM Context Group 29 (value set OID 1.2.840.10008.6.1.19).
     */
    protected List<Enumeration<ImagingModality>> modality = new ArrayList<Enumeration<ImagingModality>>();

    /**
     * The requesting/referring physician.
     */
    protected ResourceReference referrer;

    /**
     * Availability of study (online, offline or nearlnie).
     */
    protected Enumeration<InstanceAvailability> availability;

    /**
     * WADO-RS URI where Study is available.
     */
    protected Uri url;

    /**
     * Number of Series in Study.
     */
    protected Integer numberOfSeries;

    /**
     * Number of SOP Instances in Study.
     */
    protected Integer numberOfInstances;

    /**
     * Diagnoses etc provided with request.
     */
    protected String_ clinicalInformation;

    /**
     * Type of procedure performed.
     */
    protected List<Coding> procedure = new ArrayList<Coding>();

    /**
     * Who read study and interpreted the images.
     */
    protected ResourceReference interpreter;

    /**
     * Institution-generated description or classification of the Study (component) performed.
     */
    protected String_ description;

    /**
     * Each study has one or more series of image instances.
     */
    protected List<ImagingStudySeriesComponent> series = new ArrayList<ImagingStudySeriesComponent>();

    public DateTime getDateTime() { 
      return this.dateTime;
    }

    public void setDateTime(DateTime value) { 
      this.dateTime = value;
    }

    public String getDateTimeSimple() { 
      return this.dateTime == null ? null : this.dateTime.getValue();
    }

    public void setDateTimeSimple(String value) { 
      if (value == null)
        this.dateTime = null;
      else {
        if (this.dateTime == null)
          this.dateTime = new DateTime();
        this.dateTime.setValue(value);
      }
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public Oid getUid() { 
      return this.uid;
    }

    public void setUid(Oid value) { 
      this.uid = value;
    }

    public String getUidSimple() { 
      return this.uid == null ? null : this.uid.getValue();
    }

    public void setUidSimple(String value) { 
        if (this.uid == null)
          this.uid = new Oid();
        this.uid.setValue(value);
    }

    public Identifier getAccessionNo() { 
      return this.accessionNo;
    }

    public void setAccessionNo(Identifier value) { 
      this.accessionNo = value;
    }

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public List<ResourceReference> getOrder() { 
      return this.order;
    }

    // syntactic sugar
    public ResourceReference addOrder() { 
      ResourceReference t = new ResourceReference();
      this.order.add(t);
      return t;
    }

    public List<Enumeration<ImagingModality>> getModality() { 
      return this.modality;
    }

    // syntactic sugar
    public Enumeration<ImagingModality> addModality() { 
      Enumeration<ImagingModality> t = new Enumeration<ImagingModality>();
      this.modality.add(t);
      return t;
    }

    public Enumeration<ImagingModality> addModalitySimple(ImagingModality value) { 
      Enumeration<ImagingModality> t = new Enumeration<ImagingModality>();
      t.setValue(value);
      this.modality.add(t);
      return t;
    }

    public ResourceReference getReferrer() { 
      return this.referrer;
    }

    public void setReferrer(ResourceReference value) { 
      this.referrer = value;
    }

    public Enumeration<InstanceAvailability> getAvailability() { 
      return this.availability;
    }

    public void setAvailability(Enumeration<InstanceAvailability> value) { 
      this.availability = value;
    }

    public InstanceAvailability getAvailabilitySimple() { 
      return this.availability == null ? null : this.availability.getValue();
    }

    public void setAvailabilitySimple(InstanceAvailability value) { 
      if (value == null)
        this.availability = null;
      else {
        if (this.availability == null)
          this.availability = new Enumeration<InstanceAvailability>();
        this.availability.setValue(value);
      }
    }

    public Uri getUrl() { 
      return this.url;
    }

    public void setUrl(Uri value) { 
      this.url = value;
    }

    public String getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    public void setUrlSimple(String value) { 
      if (value == null)
        this.url = null;
      else {
        if (this.url == null)
          this.url = new Uri();
        this.url.setValue(value);
      }
    }

    public Integer getNumberOfSeries() { 
      return this.numberOfSeries;
    }

    public void setNumberOfSeries(Integer value) { 
      this.numberOfSeries = value;
    }

    public int getNumberOfSeriesSimple() { 
      return this.numberOfSeries == null ? null : this.numberOfSeries.getValue();
    }

    public void setNumberOfSeriesSimple(int value) { 
        if (this.numberOfSeries == null)
          this.numberOfSeries = new Integer();
        this.numberOfSeries.setValue(value);
    }

    public Integer getNumberOfInstances() { 
      return this.numberOfInstances;
    }

    public void setNumberOfInstances(Integer value) { 
      this.numberOfInstances = value;
    }

    public int getNumberOfInstancesSimple() { 
      return this.numberOfInstances == null ? null : this.numberOfInstances.getValue();
    }

    public void setNumberOfInstancesSimple(int value) { 
        if (this.numberOfInstances == null)
          this.numberOfInstances = new Integer();
        this.numberOfInstances.setValue(value);
    }

    public String_ getClinicalInformation() { 
      return this.clinicalInformation;
    }

    public void setClinicalInformation(String_ value) { 
      this.clinicalInformation = value;
    }

    public String getClinicalInformationSimple() { 
      return this.clinicalInformation == null ? null : this.clinicalInformation.getValue();
    }

    public void setClinicalInformationSimple(String value) { 
      if (value == null)
        this.clinicalInformation = null;
      else {
        if (this.clinicalInformation == null)
          this.clinicalInformation = new String_();
        this.clinicalInformation.setValue(value);
      }
    }

    public List<Coding> getProcedure() { 
      return this.procedure;
    }

    // syntactic sugar
    public Coding addProcedure() { 
      Coding t = new Coding();
      this.procedure.add(t);
      return t;
    }

    public ResourceReference getInterpreter() { 
      return this.interpreter;
    }

    public void setInterpreter(ResourceReference value) { 
      this.interpreter = value;
    }

    public String_ getDescription() { 
      return this.description;
    }

    public void setDescription(String_ value) { 
      this.description = value;
    }

    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    public void setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
    }

    public List<ImagingStudySeriesComponent> getSeries() { 
      return this.series;
    }

    // syntactic sugar
    public ImagingStudySeriesComponent addSeries() { 
      ImagingStudySeriesComponent t = new ImagingStudySeriesComponent();
      this.series.add(t);
      return t;
    }

      public ImagingStudy copy() {
        ImagingStudy dst = new ImagingStudy();
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.uid = uid == null ? null : uid.copy();
        dst.accessionNo = accessionNo == null ? null : accessionNo.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.order = new ArrayList<ResourceReference>();
        for (ResourceReference i : order)
          dst.order.add(i.copy());
        dst.modality = new ArrayList<Enumeration<ImagingModality>>();
        for (Enumeration<ImagingModality> i : modality)
          dst.modality.add(i.copy());
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
          dst.series.add(i.copy(dst));
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

