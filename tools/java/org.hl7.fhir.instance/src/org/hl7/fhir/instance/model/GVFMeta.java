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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11

import java.util.*;

/**
 * Pragmas from a GVF.
 */
public class GVFMeta extends Resource {

    public enum GvfVersion {
        v101, // Version 1.01.
        v102, // Version 1.02.
        v103, // Version 1.03.
        v104, // Version 1.04.
        v105, // Version 1.05.
        v106, // Version 1.06.
        Null; // added to help the parsers
        public static GvfVersion fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("v1_01".equals(codeString))
          return v101;
        if ("v1_02".equals(codeString))
          return v102;
        if ("v1_03".equals(codeString))
          return v103;
        if ("v1_04".equals(codeString))
          return v104;
        if ("v1_05".equals(codeString))
          return v105;
        if ("v1_06".equals(codeString))
          return v106;
        throw new Exception("Unknown GvfVersion code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case v101: return "v1_01";
            case v102: return "v1_02";
            case v103: return "v1_03";
            case v104: return "v1_04";
            case v105: return "v1_05";
            case v106: return "v1_06";
            default: return "?";
          }
        }
    }

  public class GvfVersionEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("v1_01".equals(codeString))
          return GvfVersion.v101;
        if ("v1_02".equals(codeString))
          return GvfVersion.v102;
        if ("v1_03".equals(codeString))
          return GvfVersion.v103;
        if ("v1_04".equals(codeString))
          return GvfVersion.v104;
        if ("v1_05".equals(codeString))
          return GvfVersion.v105;
        if ("v1_06".equals(codeString))
          return GvfVersion.v106;
        throw new Exception("Unknown GvfVersion code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == GvfVersion.v101)
        return "v1_01";
      if (code == GvfVersion.v102)
        return "v1_02";
      if (code == GvfVersion.v103)
        return "v1_03";
      if (code == GvfVersion.v104)
        return "v1_04";
      if (code == GvfVersion.v105)
        return "v1_05";
      if (code == GvfVersion.v106)
        return "v1_06";
      return "?";
      }
    }

    public enum Population {
        cHG, // Han Chinese in Beijing, China.
        jPT, // Japanese in Tokyo, Japan.
        cHS, // Han Chinese South.
        cDX, // Chinese Dai in Xishuangbanna, China.
        kHV, // Kinh in Ho Chi Minh City, Vietnam.
        cHD, // Chinese in Denver, Colorado (pilot 3 only).
        cEU, // Utah residents (CEPH) with Northern and Western European ancestry.
        tSI, // Toscani in Italia.
        gBR, // British in England and Scotland.
        fIN, // Finishs in Finland.
        iBS, // Iberian populations in Spain.
        yRI, // Yoruba in Ibadan, Nigeria.
        lWK, // Luhya in Webuye, Kenya.
        gWD, // Gambian in Western Division, The Gambia.
        mSL, // Mende in Sierra Leone.
        eSN, // Esan in Nigeria.
        aSW, // African Ancestry in Southwest US.
        aCB, // African Caribbean in Barbados.
        mXL, // Mexican Ancestry in Los Angeles, California.
        pUR, // Puerto Rican in Puerto Rico.
        cLM, // Colombian in Medellin, Colombia.
        pEL, // Peruvian in Lima, Peru.
        gIH, // Gujarati Indian in Houston, TX.
        pJL, // Punjabi in Lahore, Pakistan.
        bEB, // Belgali in Bangladesh.
        sTU, // Sri Lankan Tamil in the UK.
        iTU, // Indian Telugu in the UK.
        Null; // added to help the parsers
        public static Population fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("CHG".equals(codeString))
          return cHG;
        if ("JPT".equals(codeString))
          return jPT;
        if ("CHS".equals(codeString))
          return cHS;
        if ("CDX".equals(codeString))
          return cDX;
        if ("KHV".equals(codeString))
          return kHV;
        if ("CHD".equals(codeString))
          return cHD;
        if ("CEU".equals(codeString))
          return cEU;
        if ("TSI".equals(codeString))
          return tSI;
        if ("GBR".equals(codeString))
          return gBR;
        if ("FIN".equals(codeString))
          return fIN;
        if ("IBS".equals(codeString))
          return iBS;
        if ("YRI".equals(codeString))
          return yRI;
        if ("LWK".equals(codeString))
          return lWK;
        if ("GWD".equals(codeString))
          return gWD;
        if ("MSL".equals(codeString))
          return mSL;
        if ("ESN".equals(codeString))
          return eSN;
        if ("ASW".equals(codeString))
          return aSW;
        if ("ACB".equals(codeString))
          return aCB;
        if ("MXL".equals(codeString))
          return mXL;
        if ("PUR".equals(codeString))
          return pUR;
        if ("CLM".equals(codeString))
          return cLM;
        if ("PEL".equals(codeString))
          return pEL;
        if ("GIH".equals(codeString))
          return gIH;
        if ("PJL".equals(codeString))
          return pJL;
        if ("BEB".equals(codeString))
          return bEB;
        if ("STU".equals(codeString))
          return sTU;
        if ("ITU".equals(codeString))
          return iTU;
        throw new Exception("Unknown Population code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case cHG: return "CHG";
            case jPT: return "JPT";
            case cHS: return "CHS";
            case cDX: return "CDX";
            case kHV: return "KHV";
            case cHD: return "CHD";
            case cEU: return "CEU";
            case tSI: return "TSI";
            case gBR: return "GBR";
            case fIN: return "FIN";
            case iBS: return "IBS";
            case yRI: return "YRI";
            case lWK: return "LWK";
            case gWD: return "GWD";
            case mSL: return "MSL";
            case eSN: return "ESN";
            case aSW: return "ASW";
            case aCB: return "ACB";
            case mXL: return "MXL";
            case pUR: return "PUR";
            case cLM: return "CLM";
            case pEL: return "PEL";
            case gIH: return "GIH";
            case pJL: return "PJL";
            case bEB: return "BEB";
            case sTU: return "STU";
            case iTU: return "ITU";
            default: return "?";
          }
        }
    }

  public class PopulationEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("CHG".equals(codeString))
          return Population.cHG;
        if ("JPT".equals(codeString))
          return Population.jPT;
        if ("CHS".equals(codeString))
          return Population.cHS;
        if ("CDX".equals(codeString))
          return Population.cDX;
        if ("KHV".equals(codeString))
          return Population.kHV;
        if ("CHD".equals(codeString))
          return Population.cHD;
        if ("CEU".equals(codeString))
          return Population.cEU;
        if ("TSI".equals(codeString))
          return Population.tSI;
        if ("GBR".equals(codeString))
          return Population.gBR;
        if ("FIN".equals(codeString))
          return Population.fIN;
        if ("IBS".equals(codeString))
          return Population.iBS;
        if ("YRI".equals(codeString))
          return Population.yRI;
        if ("LWK".equals(codeString))
          return Population.lWK;
        if ("GWD".equals(codeString))
          return Population.gWD;
        if ("MSL".equals(codeString))
          return Population.mSL;
        if ("ESN".equals(codeString))
          return Population.eSN;
        if ("ASW".equals(codeString))
          return Population.aSW;
        if ("ACB".equals(codeString))
          return Population.aCB;
        if ("MXL".equals(codeString))
          return Population.mXL;
        if ("PUR".equals(codeString))
          return Population.pUR;
        if ("CLM".equals(codeString))
          return Population.cLM;
        if ("PEL".equals(codeString))
          return Population.pEL;
        if ("GIH".equals(codeString))
          return Population.gIH;
        if ("PJL".equals(codeString))
          return Population.pJL;
        if ("BEB".equals(codeString))
          return Population.bEB;
        if ("STU".equals(codeString))
          return Population.sTU;
        if ("ITU".equals(codeString))
          return Population.iTU;
        throw new Exception("Unknown Population code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Population.cHG)
        return "CHG";
      if (code == Population.jPT)
        return "JPT";
      if (code == Population.cHS)
        return "CHS";
      if (code == Population.cDX)
        return "CDX";
      if (code == Population.kHV)
        return "KHV";
      if (code == Population.cHD)
        return "CHD";
      if (code == Population.cEU)
        return "CEU";
      if (code == Population.tSI)
        return "TSI";
      if (code == Population.gBR)
        return "GBR";
      if (code == Population.fIN)
        return "FIN";
      if (code == Population.iBS)
        return "IBS";
      if (code == Population.yRI)
        return "YRI";
      if (code == Population.lWK)
        return "LWK";
      if (code == Population.gWD)
        return "GWD";
      if (code == Population.mSL)
        return "MSL";
      if (code == Population.eSN)
        return "ESN";
      if (code == Population.aSW)
        return "ASW";
      if (code == Population.aCB)
        return "ACB";
      if (code == Population.mXL)
        return "MXL";
      if (code == Population.pUR)
        return "PUR";
      if (code == Population.cLM)
        return "CLM";
      if (code == Population.pEL)
        return "PEL";
      if (code == Population.gIH)
        return "GIH";
      if (code == Population.pJL)
        return "PJL";
      if (code == Population.bEB)
        return "BEB";
      if (code == Population.sTU)
        return "STU";
      if (code == Population.iTU)
        return "ITU";
      return "?";
      }
    }

    public enum PlatformClass {
        sRS, // Short read sequencing.
        sMS, // Single molecule sequencing.
        capillary, // Capikllary sequencing.
        dNACHIP, // DNA micorarray SNP detection.
        Null; // added to help the parsers
        public static PlatformClass fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("SRS".equals(codeString))
          return sRS;
        if ("SMS".equals(codeString))
          return sMS;
        if ("capillary".equals(codeString))
          return capillary;
        if ("DNA_CHIP".equals(codeString))
          return dNACHIP;
        throw new Exception("Unknown PlatformClass code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case sRS: return "SRS";
            case sMS: return "SMS";
            case capillary: return "capillary";
            case dNACHIP: return "DNA_CHIP";
            default: return "?";
          }
        }
    }

  public class PlatformClassEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("SRS".equals(codeString))
          return PlatformClass.sRS;
        if ("SMS".equals(codeString))
          return PlatformClass.sMS;
        if ("capillary".equals(codeString))
          return PlatformClass.capillary;
        if ("DNA_CHIP".equals(codeString))
          return PlatformClass.dNACHIP;
        throw new Exception("Unknown PlatformClass code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == PlatformClass.sRS)
        return "SRS";
      if (code == PlatformClass.sMS)
        return "SMS";
      if (code == PlatformClass.capillary)
        return "capillary";
      if (code == PlatformClass.dNACHIP)
        return "DNA_CHIP";
      return "?";
      }
    }

    public enum PlatformName {
        illumina, // Illumina platform in general.
        illuminaGa, // Illumina GA.
        illuminaGaii, // Illumina GAII.
        illuminaGaiix, // Illumina GAIIx.
        illuminaHiseq, // Illumina HiSeq.
        illuminaMiseq, // Illumina MiSeq.
        solid, // ABI SOLiD.
        completeGenomics, // Complete Genomics.
        ionTorrent, // Ion Torrent.
        ls454, // 454 Life Sciences Pyrosequencing.
        helicos, // Helicos BioSciences tSMS.
        pacbioRs, // Pacific Biosciences PACBIO RS.
        affyHs6, // Affymetrix Human SNP Array 6.0.
        affyHs5, // Human SNP Array 5.0.
        humanomni258, // Illumina BeadChip HumanOmni2.5-8.
        humanomni1s8, // Illumina BeadChip HumanOmni1S-8.
        humanomni1Quad, // Illumina BeadChip HumanOmni1-Quad.
        Null; // added to help the parsers
        public static PlatformName fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("illumina".equals(codeString))
          return illumina;
        if ("illumina_ga".equals(codeString))
          return illuminaGa;
        if ("illumina_gaii".equals(codeString))
          return illuminaGaii;
        if ("illumina_gaiix".equals(codeString))
          return illuminaGaiix;
        if ("illumina_hiseq".equals(codeString))
          return illuminaHiseq;
        if ("illumina_miseq".equals(codeString))
          return illuminaMiseq;
        if ("solid".equals(codeString))
          return solid;
        if ("complete_genomics".equals(codeString))
          return completeGenomics;
        if ("ion_torrent".equals(codeString))
          return ionTorrent;
        if ("ls_454".equals(codeString))
          return ls454;
        if ("helicos".equals(codeString))
          return helicos;
        if ("pacbio_rs".equals(codeString))
          return pacbioRs;
        if ("affy_hs_6".equals(codeString))
          return affyHs6;
        if ("affy_hs_5".equals(codeString))
          return affyHs5;
        if ("humanomni25_8".equals(codeString))
          return humanomni258;
        if ("humanomni1s_8".equals(codeString))
          return humanomni1s8;
        if ("humanomni1_quad".equals(codeString))
          return humanomni1Quad;
        throw new Exception("Unknown PlatformName code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case illumina: return "illumina";
            case illuminaGa: return "illumina_ga";
            case illuminaGaii: return "illumina_gaii";
            case illuminaGaiix: return "illumina_gaiix";
            case illuminaHiseq: return "illumina_hiseq";
            case illuminaMiseq: return "illumina_miseq";
            case solid: return "solid";
            case completeGenomics: return "complete_genomics";
            case ionTorrent: return "ion_torrent";
            case ls454: return "ls_454";
            case helicos: return "helicos";
            case pacbioRs: return "pacbio_rs";
            case affyHs6: return "affy_hs_6";
            case affyHs5: return "affy_hs_5";
            case humanomni258: return "humanomni25_8";
            case humanomni1s8: return "humanomni1s_8";
            case humanomni1Quad: return "humanomni1_quad";
            default: return "?";
          }
        }
    }

  public class PlatformNameEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("illumina".equals(codeString))
          return PlatformName.illumina;
        if ("illumina_ga".equals(codeString))
          return PlatformName.illuminaGa;
        if ("illumina_gaii".equals(codeString))
          return PlatformName.illuminaGaii;
        if ("illumina_gaiix".equals(codeString))
          return PlatformName.illuminaGaiix;
        if ("illumina_hiseq".equals(codeString))
          return PlatformName.illuminaHiseq;
        if ("illumina_miseq".equals(codeString))
          return PlatformName.illuminaMiseq;
        if ("solid".equals(codeString))
          return PlatformName.solid;
        if ("complete_genomics".equals(codeString))
          return PlatformName.completeGenomics;
        if ("ion_torrent".equals(codeString))
          return PlatformName.ionTorrent;
        if ("ls_454".equals(codeString))
          return PlatformName.ls454;
        if ("helicos".equals(codeString))
          return PlatformName.helicos;
        if ("pacbio_rs".equals(codeString))
          return PlatformName.pacbioRs;
        if ("affy_hs_6".equals(codeString))
          return PlatformName.affyHs6;
        if ("affy_hs_5".equals(codeString))
          return PlatformName.affyHs5;
        if ("humanomni25_8".equals(codeString))
          return PlatformName.humanomni258;
        if ("humanomni1s_8".equals(codeString))
          return PlatformName.humanomni1s8;
        if ("humanomni1_quad".equals(codeString))
          return PlatformName.humanomni1Quad;
        throw new Exception("Unknown PlatformName code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == PlatformName.illumina)
        return "illumina";
      if (code == PlatformName.illuminaGa)
        return "illumina_ga";
      if (code == PlatformName.illuminaGaii)
        return "illumina_gaii";
      if (code == PlatformName.illuminaGaiix)
        return "illumina_gaiix";
      if (code == PlatformName.illuminaHiseq)
        return "illumina_hiseq";
      if (code == PlatformName.illuminaMiseq)
        return "illumina_miseq";
      if (code == PlatformName.solid)
        return "solid";
      if (code == PlatformName.completeGenomics)
        return "complete_genomics";
      if (code == PlatformName.ionTorrent)
        return "ion_torrent";
      if (code == PlatformName.ls454)
        return "ls_454";
      if (code == PlatformName.helicos)
        return "helicos";
      if (code == PlatformName.pacbioRs)
        return "pacbio_rs";
      if (code == PlatformName.affyHs6)
        return "affy_hs_6";
      if (code == PlatformName.affyHs5)
        return "affy_hs_5";
      if (code == PlatformName.humanomni258)
        return "humanomni25_8";
      if (code == PlatformName.humanomni1s8)
        return "humanomni1s_8";
      if (code == PlatformName.humanomni1Quad)
        return "humanomni1_quad";
      return "?";
      }
    }

    public enum PlatformReadType {
        fragment, // Single-end sequenced fragments.
        pair, // Standard paired-end library.
        Null; // added to help the parsers
        public static PlatformReadType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("fragment".equals(codeString))
          return fragment;
        if ("pair".equals(codeString))
          return pair;
        throw new Exception("Unknown PlatformReadType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case fragment: return "fragment";
            case pair: return "pair";
            default: return "?";
          }
        }
    }

  public class PlatformReadTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("fragment".equals(codeString))
          return PlatformReadType.fragment;
        if ("pair".equals(codeString))
          return PlatformReadType.pair;
        throw new Exception("Unknown PlatformReadType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == PlatformReadType.fragment)
        return "fragment";
      if (code == PlatformReadType.pair)
        return "pair";
      return "?";
      }
    }

    public enum SequencingScope {
        wholeGenome, // Whole genome sequencing.
        wholeExome, // Whole exome sequencing.
        targetedCapture, // Targeted capture.
        Null; // added to help the parsers
        public static SequencingScope fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("whole_genome".equals(codeString))
          return wholeGenome;
        if ("whole_exome".equals(codeString))
          return wholeExome;
        if ("targeted_capture".equals(codeString))
          return targetedCapture;
        throw new Exception("Unknown SequencingScope code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case wholeGenome: return "whole_genome";
            case wholeExome: return "whole_exome";
            case targetedCapture: return "targeted_capture";
            default: return "?";
          }
        }
    }

  public class SequencingScopeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("whole_genome".equals(codeString))
          return SequencingScope.wholeGenome;
        if ("whole_exome".equals(codeString))
          return SequencingScope.wholeExome;
        if ("targeted_capture".equals(codeString))
          return SequencingScope.targetedCapture;
        throw new Exception("Unknown SequencingScope code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SequencingScope.wholeGenome)
        return "whole_genome";
      if (code == SequencingScope.wholeExome)
        return "whole_exome";
      if (code == SequencingScope.targetedCapture)
        return "targeted_capture";
      return "?";
      }
    }

    public enum CaptureMethod {
        truseqExome, // TruSeq exome.
        targetseq, // TargetSeq.
        sureselectExome, // SureSelect exome.
        sureselectCustom, // SureSelect custom.
        nimblegenExome, // NimbleGen exome.
        nimblegenCustom, // NimbleGen custom.
        otherExome, // Other exome.
        otherCustom, // Other custom.
        Null; // added to help the parsers
        public static CaptureMethod fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("truseq_exome".equals(codeString))
          return truseqExome;
        if ("targetseq".equals(codeString))
          return targetseq;
        if ("sureselect_exome".equals(codeString))
          return sureselectExome;
        if ("sureselect_custom".equals(codeString))
          return sureselectCustom;
        if ("nimblegen_exome".equals(codeString))
          return nimblegenExome;
        if ("nimblegen_custom".equals(codeString))
          return nimblegenCustom;
        if ("other_exome".equals(codeString))
          return otherExome;
        if ("other_custom".equals(codeString))
          return otherCustom;
        throw new Exception("Unknown CaptureMethod code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case truseqExome: return "truseq_exome";
            case targetseq: return "targetseq";
            case sureselectExome: return "sureselect_exome";
            case sureselectCustom: return "sureselect_custom";
            case nimblegenExome: return "nimblegen_exome";
            case nimblegenCustom: return "nimblegen_custom";
            case otherExome: return "other_exome";
            case otherCustom: return "other_custom";
            default: return "?";
          }
        }
    }

  public class CaptureMethodEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("truseq_exome".equals(codeString))
          return CaptureMethod.truseqExome;
        if ("targetseq".equals(codeString))
          return CaptureMethod.targetseq;
        if ("sureselect_exome".equals(codeString))
          return CaptureMethod.sureselectExome;
        if ("sureselect_custom".equals(codeString))
          return CaptureMethod.sureselectCustom;
        if ("nimblegen_exome".equals(codeString))
          return CaptureMethod.nimblegenExome;
        if ("nimblegen_custom".equals(codeString))
          return CaptureMethod.nimblegenCustom;
        if ("other_exome".equals(codeString))
          return CaptureMethod.otherExome;
        if ("other_custom".equals(codeString))
          return CaptureMethod.otherCustom;
        throw new Exception("Unknown CaptureMethod code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CaptureMethod.truseqExome)
        return "truseq_exome";
      if (code == CaptureMethod.targetseq)
        return "targetseq";
      if (code == CaptureMethod.sureselectExome)
        return "sureselect_exome";
      if (code == CaptureMethod.sureselectCustom)
        return "sureselect_custom";
      if (code == CaptureMethod.nimblegenExome)
        return "nimblegen_exome";
      if (code == CaptureMethod.nimblegenCustom)
        return "nimblegen_custom";
      if (code == CaptureMethod.otherExome)
        return "other_exome";
      if (code == CaptureMethod.otherCustom)
        return "other_custom";
      return "?";
      }
    }

    public enum Source {
        prenatal, // Prenatal.
        germline, // germline.
        somatic, // Somatic.
        Null; // added to help the parsers
        public static Source fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("prenatal".equals(codeString))
          return prenatal;
        if ("germline".equals(codeString))
          return germline;
        if ("somatic".equals(codeString))
          return somatic;
        throw new Exception("Unknown Source code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case prenatal: return "prenatal";
            case germline: return "germline";
            case somatic: return "somatic";
            default: return "?";
          }
        }
    }

  public class SourceEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("prenatal".equals(codeString))
          return Source.prenatal;
        if ("germline".equals(codeString))
          return Source.germline;
        if ("somatic".equals(codeString))
          return Source.somatic;
        throw new Exception("Unknown Source code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Source.prenatal)
        return "prenatal";
      if (code == Source.germline)
        return "germline";
      if (code == Source.somatic)
        return "somatic";
      return "?";
      }
    }

    public class GVFMetaSubjectComponent extends Element {
        /**
         * Identity of the subejct.
         */
        protected ResourceReference patient;

        /**
         * Id of individual field of the file that correspond to the subject.
         */
        protected String_ fieldId;

        public ResourceReference getPatient() { 
          return this.patient;
        }

        public void setPatient(ResourceReference value) { 
          this.patient = value;
        }

        public String_ getFieldId() { 
          return this.fieldId;
        }

        public void setFieldId(String_ value) { 
          this.fieldId = value;
        }

        public String getFieldIdSimple() { 
          return this.fieldId == null ? null : this.fieldId.getValue();
        }

        public void setFieldIdSimple(String value) { 
          if (value == null)
            this.fieldId = null;
          else {
            if (this.fieldId == null)
              this.fieldId = new String_();
            this.fieldId.setValue(value);
          }
        }

      public GVFMetaSubjectComponent copy(GVFMeta e) {
        GVFMetaSubjectComponent dst = e.new GVFMetaSubjectComponent();
        dst.patient = patient == null ? null : patient.copy();
        dst.fieldId = fieldId == null ? null : fieldId.copy();
        return dst;
      }

  }

    public class GVFMetaPlatformComponent extends Element {
        /**
         * Class of the sequencing platform.
         */
        protected Enumeration<PlatformClass> class_;

        /**
         * Version of the platform being used.
         */
        protected String_ version;

        /**
         * Name of the platform being used.
         */
        protected Enumeration<PlatformName> name;

        /**
         * Id of the platfrom being used.
         */
        protected String_ identity;

        /**
         * Read length of the technology.
         */
        protected Integer readLength;

        /**
         * Read type of the technology.
         */
        protected Enumeration<PlatformReadType> readType;

        /**
         * Read pair span of the technology.
         */
        protected Integer readPairSpan;

        /**
         * Average coverage of the technology.
         */
        protected Integer averageCoverage;

        public Enumeration<PlatformClass> getClass_() { 
          return this.class_;
        }

        public void setClass_(Enumeration<PlatformClass> value) { 
          this.class_ = value;
        }

        public PlatformClass getClass_Simple() { 
          return this.class_ == null ? null : this.class_.getValue();
        }

        public void setClass_Simple(PlatformClass value) { 
          if (value == null)
            this.class_ = null;
          else {
            if (this.class_ == null)
              this.class_ = new Enumeration<PlatformClass>();
            this.class_.setValue(value);
          }
        }

        public String_ getVersion() { 
          return this.version;
        }

        public void setVersion(String_ value) { 
          this.version = value;
        }

        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        public void setVersionSimple(String value) { 
          if (value == null)
            this.version = null;
          else {
            if (this.version == null)
              this.version = new String_();
            this.version.setValue(value);
          }
        }

        public Enumeration<PlatformName> getName() { 
          return this.name;
        }

        public void setName(Enumeration<PlatformName> value) { 
          this.name = value;
        }

        public PlatformName getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(PlatformName value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new Enumeration<PlatformName>();
            this.name.setValue(value);
          }
        }

        public String_ getIdentity() { 
          return this.identity;
        }

        public void setIdentity(String_ value) { 
          this.identity = value;
        }

        public String getIdentitySimple() { 
          return this.identity == null ? null : this.identity.getValue();
        }

        public void setIdentitySimple(String value) { 
          if (value == null)
            this.identity = null;
          else {
            if (this.identity == null)
              this.identity = new String_();
            this.identity.setValue(value);
          }
        }

        public Integer getReadLength() { 
          return this.readLength;
        }

        public void setReadLength(Integer value) { 
          this.readLength = value;
        }

        public int getReadLengthSimple() { 
          return this.readLength == null ? null : this.readLength.getValue();
        }

        public void setReadLengthSimple(int value) { 
          if (value == -1)
            this.readLength = null;
          else {
            if (this.readLength == null)
              this.readLength = new Integer();
            this.readLength.setValue(value);
          }
        }

        public Enumeration<PlatformReadType> getReadType() { 
          return this.readType;
        }

        public void setReadType(Enumeration<PlatformReadType> value) { 
          this.readType = value;
        }

        public PlatformReadType getReadTypeSimple() { 
          return this.readType == null ? null : this.readType.getValue();
        }

        public void setReadTypeSimple(PlatformReadType value) { 
          if (value == null)
            this.readType = null;
          else {
            if (this.readType == null)
              this.readType = new Enumeration<PlatformReadType>();
            this.readType.setValue(value);
          }
        }

        public Integer getReadPairSpan() { 
          return this.readPairSpan;
        }

        public void setReadPairSpan(Integer value) { 
          this.readPairSpan = value;
        }

        public int getReadPairSpanSimple() { 
          return this.readPairSpan == null ? null : this.readPairSpan.getValue();
        }

        public void setReadPairSpanSimple(int value) { 
          if (value == -1)
            this.readPairSpan = null;
          else {
            if (this.readPairSpan == null)
              this.readPairSpan = new Integer();
            this.readPairSpan.setValue(value);
          }
        }

        public Integer getAverageCoverage() { 
          return this.averageCoverage;
        }

        public void setAverageCoverage(Integer value) { 
          this.averageCoverage = value;
        }

        public int getAverageCoverageSimple() { 
          return this.averageCoverage == null ? null : this.averageCoverage.getValue();
        }

        public void setAverageCoverageSimple(int value) { 
          if (value == -1)
            this.averageCoverage = null;
          else {
            if (this.averageCoverage == null)
              this.averageCoverage = new Integer();
            this.averageCoverage.setValue(value);
          }
        }

      public GVFMetaPlatformComponent copy(GVFMeta e) {
        GVFMetaPlatformComponent dst = e.new GVFMetaPlatformComponent();
        dst.class_ = class_ == null ? null : class_.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.identity = identity == null ? null : identity.copy();
        dst.readLength = readLength == null ? null : readLength.copy();
        dst.readType = readType == null ? null : readType.copy();
        dst.readPairSpan = readPairSpan == null ? null : readPairSpan.copy();
        dst.averageCoverage = averageCoverage == null ? null : averageCoverage.copy();
        return dst;
      }

  }

    /**
     * Subject being described by the file.
     */
    protected List<GVFMetaSubjectComponent> subject = new ArrayList<GVFMetaSubjectComponent>();

    /**
     * GVF file from which data of the resource is extracted.
     */
    protected Attachment sourceFile;

    /**
     * Valid version of the GVF file.
     */
    protected Enumeration<GvfVersion> gvfVersion;

    /**
     * URL to FASTA file used as reference assembly.
     */
    protected Uri referenceFasta;

    /**
     * GFF3 file containing feature being described in the file.
     */
    protected Uri featureGFF3;

    /**
     * Date when the file is updated.
     */
    protected Date fileDate;

    /**
     * Id of individual being described in the file.
     */
    protected List<String_> individual = new ArrayList<String_>();

    /**
     * Code for population which the individual can be categorized into.
     */
    protected Enumeration<Population> population;

    /**
     * Technology platform used in the sequencing.
     */
    protected GVFMetaPlatformComponent platform;

    /**
     * Scope of the sequencing.
     */
    protected Enumeration<SequencingScope> sequencingScope;

    /**
     * Capture method used in the sequencing.
     */
    protected Enumeration<CaptureMethod> captureMethod;

    /**
     * Region captured in the file.
     */
    protected Uri captureRegions;

    /**
     * Sequence alignment algorithm/pipline used.
     */
    protected String_ sequenceAlignment;

    /**
     * Pipline used for variant calling.
     */
    protected String_ variantCalling;

    /**
     * Description of sample used in the sequencing.
     */
    protected String_ sampleDescription;

    /**
     * Source of the sample.
     */
    protected Enumeration<Source> genomicSource;

    public List<GVFMetaSubjectComponent> getSubject() { 
      return this.subject;
    }

    // syntactic sugar
    public GVFMetaSubjectComponent addSubject() { 
      GVFMetaSubjectComponent t = new GVFMetaSubjectComponent();
      this.subject.add(t);
      return t;
    }

    public Attachment getSourceFile() { 
      return this.sourceFile;
    }

    public void setSourceFile(Attachment value) { 
      this.sourceFile = value;
    }

    public Enumeration<GvfVersion> getGvfVersion() { 
      return this.gvfVersion;
    }

    public void setGvfVersion(Enumeration<GvfVersion> value) { 
      this.gvfVersion = value;
    }

    public GvfVersion getGvfVersionSimple() { 
      return this.gvfVersion == null ? null : this.gvfVersion.getValue();
    }

    public void setGvfVersionSimple(GvfVersion value) { 
      if (value == null)
        this.gvfVersion = null;
      else {
        if (this.gvfVersion == null)
          this.gvfVersion = new Enumeration<GvfVersion>();
        this.gvfVersion.setValue(value);
      }
    }

    public Uri getReferenceFasta() { 
      return this.referenceFasta;
    }

    public void setReferenceFasta(Uri value) { 
      this.referenceFasta = value;
    }

    public String getReferenceFastaSimple() { 
      return this.referenceFasta == null ? null : this.referenceFasta.getValue();
    }

    public void setReferenceFastaSimple(String value) { 
      if (value == null)
        this.referenceFasta = null;
      else {
        if (this.referenceFasta == null)
          this.referenceFasta = new Uri();
        this.referenceFasta.setValue(value);
      }
    }

    public Uri getFeatureGFF3() { 
      return this.featureGFF3;
    }

    public void setFeatureGFF3(Uri value) { 
      this.featureGFF3 = value;
    }

    public String getFeatureGFF3Simple() { 
      return this.featureGFF3 == null ? null : this.featureGFF3.getValue();
    }

    public void setFeatureGFF3Simple(String value) { 
      if (value == null)
        this.featureGFF3 = null;
      else {
        if (this.featureGFF3 == null)
          this.featureGFF3 = new Uri();
        this.featureGFF3.setValue(value);
      }
    }

    public Date getFileDate() { 
      return this.fileDate;
    }

    public void setFileDate(Date value) { 
      this.fileDate = value;
    }

    public String getFileDateSimple() { 
      return this.fileDate == null ? null : this.fileDate.getValue();
    }

    public void setFileDateSimple(String value) { 
      if (value == null)
        this.fileDate = null;
      else {
        if (this.fileDate == null)
          this.fileDate = new Date();
        this.fileDate.setValue(value);
      }
    }

    public List<String_> getIndividual() { 
      return this.individual;
    }

    // syntactic sugar
    public String_ addIndividual() { 
      String_ t = new String_();
      this.individual.add(t);
      return t;
    }

    public String_ addIndividualSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.individual.add(t);
      return t;
    }

    public Enumeration<Population> getPopulation() { 
      return this.population;
    }

    public void setPopulation(Enumeration<Population> value) { 
      this.population = value;
    }

    public Population getPopulationSimple() { 
      return this.population == null ? null : this.population.getValue();
    }

    public void setPopulationSimple(Population value) { 
      if (value == null)
        this.population = null;
      else {
        if (this.population == null)
          this.population = new Enumeration<Population>();
        this.population.setValue(value);
      }
    }

    public GVFMetaPlatformComponent getPlatform() { 
      return this.platform;
    }

    public void setPlatform(GVFMetaPlatformComponent value) { 
      this.platform = value;
    }

    public Enumeration<SequencingScope> getSequencingScope() { 
      return this.sequencingScope;
    }

    public void setSequencingScope(Enumeration<SequencingScope> value) { 
      this.sequencingScope = value;
    }

    public SequencingScope getSequencingScopeSimple() { 
      return this.sequencingScope == null ? null : this.sequencingScope.getValue();
    }

    public void setSequencingScopeSimple(SequencingScope value) { 
      if (value == null)
        this.sequencingScope = null;
      else {
        if (this.sequencingScope == null)
          this.sequencingScope = new Enumeration<SequencingScope>();
        this.sequencingScope.setValue(value);
      }
    }

    public Enumeration<CaptureMethod> getCaptureMethod() { 
      return this.captureMethod;
    }

    public void setCaptureMethod(Enumeration<CaptureMethod> value) { 
      this.captureMethod = value;
    }

    public CaptureMethod getCaptureMethodSimple() { 
      return this.captureMethod == null ? null : this.captureMethod.getValue();
    }

    public void setCaptureMethodSimple(CaptureMethod value) { 
      if (value == null)
        this.captureMethod = null;
      else {
        if (this.captureMethod == null)
          this.captureMethod = new Enumeration<CaptureMethod>();
        this.captureMethod.setValue(value);
      }
    }

    public Uri getCaptureRegions() { 
      return this.captureRegions;
    }

    public void setCaptureRegions(Uri value) { 
      this.captureRegions = value;
    }

    public String getCaptureRegionsSimple() { 
      return this.captureRegions == null ? null : this.captureRegions.getValue();
    }

    public void setCaptureRegionsSimple(String value) { 
      if (value == null)
        this.captureRegions = null;
      else {
        if (this.captureRegions == null)
          this.captureRegions = new Uri();
        this.captureRegions.setValue(value);
      }
    }

    public String_ getSequenceAlignment() { 
      return this.sequenceAlignment;
    }

    public void setSequenceAlignment(String_ value) { 
      this.sequenceAlignment = value;
    }

    public String getSequenceAlignmentSimple() { 
      return this.sequenceAlignment == null ? null : this.sequenceAlignment.getValue();
    }

    public void setSequenceAlignmentSimple(String value) { 
      if (value == null)
        this.sequenceAlignment = null;
      else {
        if (this.sequenceAlignment == null)
          this.sequenceAlignment = new String_();
        this.sequenceAlignment.setValue(value);
      }
    }

    public String_ getVariantCalling() { 
      return this.variantCalling;
    }

    public void setVariantCalling(String_ value) { 
      this.variantCalling = value;
    }

    public String getVariantCallingSimple() { 
      return this.variantCalling == null ? null : this.variantCalling.getValue();
    }

    public void setVariantCallingSimple(String value) { 
      if (value == null)
        this.variantCalling = null;
      else {
        if (this.variantCalling == null)
          this.variantCalling = new String_();
        this.variantCalling.setValue(value);
      }
    }

    public String_ getSampleDescription() { 
      return this.sampleDescription;
    }

    public void setSampleDescription(String_ value) { 
      this.sampleDescription = value;
    }

    public String getSampleDescriptionSimple() { 
      return this.sampleDescription == null ? null : this.sampleDescription.getValue();
    }

    public void setSampleDescriptionSimple(String value) { 
      if (value == null)
        this.sampleDescription = null;
      else {
        if (this.sampleDescription == null)
          this.sampleDescription = new String_();
        this.sampleDescription.setValue(value);
      }
    }

    public Enumeration<Source> getGenomicSource() { 
      return this.genomicSource;
    }

    public void setGenomicSource(Enumeration<Source> value) { 
      this.genomicSource = value;
    }

    public Source getGenomicSourceSimple() { 
      return this.genomicSource == null ? null : this.genomicSource.getValue();
    }

    public void setGenomicSourceSimple(Source value) { 
      if (value == null)
        this.genomicSource = null;
      else {
        if (this.genomicSource == null)
          this.genomicSource = new Enumeration<Source>();
        this.genomicSource.setValue(value);
      }
    }

      public GVFMeta copy() {
        GVFMeta dst = new GVFMeta();
        dst.subject = new ArrayList<GVFMetaSubjectComponent>();
        for (GVFMetaSubjectComponent i : subject)
          dst.subject.add(i.copy(dst));
        dst.sourceFile = sourceFile == null ? null : sourceFile.copy();
        dst.gvfVersion = gvfVersion == null ? null : gvfVersion.copy();
        dst.referenceFasta = referenceFasta == null ? null : referenceFasta.copy();
        dst.featureGFF3 = featureGFF3 == null ? null : featureGFF3.copy();
        dst.fileDate = fileDate == null ? null : fileDate.copy();
        dst.individual = new ArrayList<String_>();
        for (String_ i : individual)
          dst.individual.add(i.copy());
        dst.population = population == null ? null : population.copy();
        dst.platform = platform == null ? null : platform.copy(dst);
        dst.sequencingScope = sequencingScope == null ? null : sequencingScope.copy();
        dst.captureMethod = captureMethod == null ? null : captureMethod.copy();
        dst.captureRegions = captureRegions == null ? null : captureRegions.copy();
        dst.sequenceAlignment = sequenceAlignment == null ? null : sequenceAlignment.copy();
        dst.variantCalling = variantCalling == null ? null : variantCalling.copy();
        dst.sampleDescription = sampleDescription == null ? null : sampleDescription.copy();
        dst.genomicSource = genomicSource == null ? null : genomicSource.copy();
        return dst;
      }

      protected GVFMeta typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.GVFMeta;
   }


}

