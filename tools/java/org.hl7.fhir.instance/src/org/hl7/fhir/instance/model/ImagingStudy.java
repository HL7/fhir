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

// Generated on Wed, May 15, 2013 09:11+1000 for FHIR v0.09

import java.util.*;

import java.net.*;
/**
 * Manifest of a set of images produced in study. The set of images may include every image in the study, or it may be an incomplete sample, such as a list of key images
 */
public class ImagingStudy extends Resource {

    public enum InstanceAvailability {
        oNLINE, // Resources are immediately available,
        oFFLINE, // Resources need to be retrieved by manual intervention
        nEARLINE, // Resources need to be retrieved from relatively slow media
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

    public enum ImageModality {
        aR, // Autorefraction
        aU, // Audio
        bDUS, // Bone Densitometry (Ultrasound)
        bI, // Biomagnetic Imaging
        bMD, // Bone Densitometry (X-Ray)
        cR, // Computed Radiography
        cT, // Computed Tomography
        dG, // Diaphanography
        dIA, // Diagram / Hand drawn image
        dOC, // Document
        dX, // Digital Radiography
        eCG, // Electrocardiograms
        eM, // Electron Microscope
        ePS, // Cardiac Electrophysiology
        eS, // Endoscopy
        fID, // Fiducials
        gM, // General Microscopy
        hC, // Hard Copy
        hD, // Hemodynamic Waveform
        iO, // Intra-oral Radiography
        iOL, // Intraocular Lens Data
        iVOCT, // Intravascular Optical Coherence Tomography
        iVUS, // Intravascular Ultrasound
        kO, // Key Object Selection
        kER, // Keratometry
        lEN, // Lensometry
        lS, // Laser Surface Scan
        mG, // Mammography
        mR, // Magnetic Resonance
        nM, // Nuclear Medicine
        oAM, // Opthalmic Axial Measurements
        oCT, // Optical Coherence Tomography
        oPM, // Ophthalmic Mapping
        oPT, // Opthalmic Tomography
        oPV, // Ophthalmic Visual Field
        oT, // Other
        pLAN, // Plan
        pR, // Presentation State
        pT, // Positron Emission Tomography (PET)
        pX, // Panoramic X-Ray
        rEG, // Registration
        rESP, // Respiratory Waveform
        rF, // Radio Fluoroscopy
        rG, // Radiographic Imaging (conventional film screen)
        rTDOSE, // Radiotherapy Dose
        rTIMAGE, // Radiotherapy Image
        rTPLAN, // Radiotherapy Plan (a.k.a. RTPLAN)
        rTRECORD, // RT Treatment Record
        rTSTRUCT, // Radiotherapy Structure Set (a.k.a. RTSTRUCT)
        sC, // Secondary Capture
        sEG, // Segmentation
        sM, // Slide Microscopy
        sMR, // Stereometric Relationship
        sR, // SR Document
        sRF, // Subjective Refraction
        tG, // Thermography
        uS, // Ultrasound
        vA, // Visual Acuity
        vL, // Visible Light
        xA, // X-Ray Angiography
        xC, // External Camera (Photography)
        Null; // added to help the parsers
        public static ImageModality fromCode(String codeString) throws Exception {
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
        if ("DIA".equals(codeString))
          return dIA;
        if ("DOC".equals(codeString))
          return dOC;
        if ("DX".equals(codeString))
          return dX;
        if ("ECG".equals(codeString))
          return eCG;
        if ("EM".equals(codeString))
          return eM;
        if ("EPS".equals(codeString))
          return ePS;
        if ("ES".equals(codeString))
          return eS;
        if ("FID".equals(codeString))
          return fID;
        if ("GM".equals(codeString))
          return gM;
        if ("HC".equals(codeString))
          return hC;
        if ("HD".equals(codeString))
          return hD;
        if ("IO".equals(codeString))
          return iO;
        if ("IOL".equals(codeString))
          return iOL;
        if ("IVOCT".equals(codeString))
          return iVOCT;
        if ("IVUS".equals(codeString))
          return iVUS;
        if ("KO".equals(codeString))
          return kO;
        if ("KER".equals(codeString))
          return kER;
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
        if ("OPM".equals(codeString))
          return oPM;
        if ("OPT".equals(codeString))
          return oPT;
        if ("OPV".equals(codeString))
          return oPV;
        if ("OT".equals(codeString))
          return oT;
        if ("PLAN".equals(codeString))
          return pLAN;
        if ("PR".equals(codeString))
          return pR;
        if ("PT".equals(codeString))
          return pT;
        if ("PX".equals(codeString))
          return pX;
        if ("REG".equals(codeString))
          return rEG;
        if ("RESP".equals(codeString))
          return rESP;
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
        if ("SC".equals(codeString))
          return sC;
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
        if ("VL".equals(codeString))
          return vL;
        if ("XA".equals(codeString))
          return xA;
        if ("XC".equals(codeString))
          return xC;
        throw new Exception("Unknown ImageModality code '"+codeString+"'");
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
            case dIA: return "DIA";
            case dOC: return "DOC";
            case dX: return "DX";
            case eCG: return "ECG";
            case eM: return "EM";
            case ePS: return "EPS";
            case eS: return "ES";
            case fID: return "FID";
            case gM: return "GM";
            case hC: return "HC";
            case hD: return "HD";
            case iO: return "IO";
            case iOL: return "IOL";
            case iVOCT: return "IVOCT";
            case iVUS: return "IVUS";
            case kO: return "KO";
            case kER: return "KER";
            case lEN: return "LEN";
            case lS: return "LS";
            case mG: return "MG";
            case mR: return "MR";
            case nM: return "NM";
            case oAM: return "OAM";
            case oCT: return "OCT";
            case oPM: return "OPM";
            case oPT: return "OPT";
            case oPV: return "OPV";
            case oT: return "OT";
            case pLAN: return "PLAN";
            case pR: return "PR";
            case pT: return "PT";
            case pX: return "PX";
            case rEG: return "REG";
            case rESP: return "RESP";
            case rF: return "RF";
            case rG: return "RG";
            case rTDOSE: return "RTDOSE";
            case rTIMAGE: return "RTIMAGE";
            case rTPLAN: return "RTPLAN";
            case rTRECORD: return "RTRECORD";
            case rTSTRUCT: return "RTSTRUCT";
            case sC: return "SC";
            case sEG: return "SEG";
            case sM: return "SM";
            case sMR: return "SMR";
            case sR: return "SR";
            case sRF: return "SRF";
            case tG: return "TG";
            case uS: return "US";
            case vA: return "VA";
            case vL: return "VL";
            case xA: return "XA";
            case xC: return "XC";
            default: return "?";
          }
        }
    }

  public class ImageModalityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("AR".equals(codeString))
          return ImageModality.aR;
        if ("AU".equals(codeString))
          return ImageModality.aU;
        if ("BDUS".equals(codeString))
          return ImageModality.bDUS;
        if ("BI".equals(codeString))
          return ImageModality.bI;
        if ("BMD".equals(codeString))
          return ImageModality.bMD;
        if ("CR".equals(codeString))
          return ImageModality.cR;
        if ("CT".equals(codeString))
          return ImageModality.cT;
        if ("DG".equals(codeString))
          return ImageModality.dG;
        if ("DIA".equals(codeString))
          return ImageModality.dIA;
        if ("DOC".equals(codeString))
          return ImageModality.dOC;
        if ("DX".equals(codeString))
          return ImageModality.dX;
        if ("ECG".equals(codeString))
          return ImageModality.eCG;
        if ("EM".equals(codeString))
          return ImageModality.eM;
        if ("EPS".equals(codeString))
          return ImageModality.ePS;
        if ("ES".equals(codeString))
          return ImageModality.eS;
        if ("FID".equals(codeString))
          return ImageModality.fID;
        if ("GM".equals(codeString))
          return ImageModality.gM;
        if ("HC".equals(codeString))
          return ImageModality.hC;
        if ("HD".equals(codeString))
          return ImageModality.hD;
        if ("IO".equals(codeString))
          return ImageModality.iO;
        if ("IOL".equals(codeString))
          return ImageModality.iOL;
        if ("IVOCT".equals(codeString))
          return ImageModality.iVOCT;
        if ("IVUS".equals(codeString))
          return ImageModality.iVUS;
        if ("KO".equals(codeString))
          return ImageModality.kO;
        if ("KER".equals(codeString))
          return ImageModality.kER;
        if ("LEN".equals(codeString))
          return ImageModality.lEN;
        if ("LS".equals(codeString))
          return ImageModality.lS;
        if ("MG".equals(codeString))
          return ImageModality.mG;
        if ("MR".equals(codeString))
          return ImageModality.mR;
        if ("NM".equals(codeString))
          return ImageModality.nM;
        if ("OAM".equals(codeString))
          return ImageModality.oAM;
        if ("OCT".equals(codeString))
          return ImageModality.oCT;
        if ("OPM".equals(codeString))
          return ImageModality.oPM;
        if ("OPT".equals(codeString))
          return ImageModality.oPT;
        if ("OPV".equals(codeString))
          return ImageModality.oPV;
        if ("OT".equals(codeString))
          return ImageModality.oT;
        if ("PLAN".equals(codeString))
          return ImageModality.pLAN;
        if ("PR".equals(codeString))
          return ImageModality.pR;
        if ("PT".equals(codeString))
          return ImageModality.pT;
        if ("PX".equals(codeString))
          return ImageModality.pX;
        if ("REG".equals(codeString))
          return ImageModality.rEG;
        if ("RESP".equals(codeString))
          return ImageModality.rESP;
        if ("RF".equals(codeString))
          return ImageModality.rF;
        if ("RG".equals(codeString))
          return ImageModality.rG;
        if ("RTDOSE".equals(codeString))
          return ImageModality.rTDOSE;
        if ("RTIMAGE".equals(codeString))
          return ImageModality.rTIMAGE;
        if ("RTPLAN".equals(codeString))
          return ImageModality.rTPLAN;
        if ("RTRECORD".equals(codeString))
          return ImageModality.rTRECORD;
        if ("RTSTRUCT".equals(codeString))
          return ImageModality.rTSTRUCT;
        if ("SC".equals(codeString))
          return ImageModality.sC;
        if ("SEG".equals(codeString))
          return ImageModality.sEG;
        if ("SM".equals(codeString))
          return ImageModality.sM;
        if ("SMR".equals(codeString))
          return ImageModality.sMR;
        if ("SR".equals(codeString))
          return ImageModality.sR;
        if ("SRF".equals(codeString))
          return ImageModality.sRF;
        if ("TG".equals(codeString))
          return ImageModality.tG;
        if ("US".equals(codeString))
          return ImageModality.uS;
        if ("VA".equals(codeString))
          return ImageModality.vA;
        if ("VL".equals(codeString))
          return ImageModality.vL;
        if ("XA".equals(codeString))
          return ImageModality.xA;
        if ("XC".equals(codeString))
          return ImageModality.xC;
        throw new Exception("Unknown ImageModality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ImageModality.aR)
        return "AR";
      if (code == ImageModality.aU)
        return "AU";
      if (code == ImageModality.bDUS)
        return "BDUS";
      if (code == ImageModality.bI)
        return "BI";
      if (code == ImageModality.bMD)
        return "BMD";
      if (code == ImageModality.cR)
        return "CR";
      if (code == ImageModality.cT)
        return "CT";
      if (code == ImageModality.dG)
        return "DG";
      if (code == ImageModality.dIA)
        return "DIA";
      if (code == ImageModality.dOC)
        return "DOC";
      if (code == ImageModality.dX)
        return "DX";
      if (code == ImageModality.eCG)
        return "ECG";
      if (code == ImageModality.eM)
        return "EM";
      if (code == ImageModality.ePS)
        return "EPS";
      if (code == ImageModality.eS)
        return "ES";
      if (code == ImageModality.fID)
        return "FID";
      if (code == ImageModality.gM)
        return "GM";
      if (code == ImageModality.hC)
        return "HC";
      if (code == ImageModality.hD)
        return "HD";
      if (code == ImageModality.iO)
        return "IO";
      if (code == ImageModality.iOL)
        return "IOL";
      if (code == ImageModality.iVOCT)
        return "IVOCT";
      if (code == ImageModality.iVUS)
        return "IVUS";
      if (code == ImageModality.kO)
        return "KO";
      if (code == ImageModality.kER)
        return "KER";
      if (code == ImageModality.lEN)
        return "LEN";
      if (code == ImageModality.lS)
        return "LS";
      if (code == ImageModality.mG)
        return "MG";
      if (code == ImageModality.mR)
        return "MR";
      if (code == ImageModality.nM)
        return "NM";
      if (code == ImageModality.oAM)
        return "OAM";
      if (code == ImageModality.oCT)
        return "OCT";
      if (code == ImageModality.oPM)
        return "OPM";
      if (code == ImageModality.oPT)
        return "OPT";
      if (code == ImageModality.oPV)
        return "OPV";
      if (code == ImageModality.oT)
        return "OT";
      if (code == ImageModality.pLAN)
        return "PLAN";
      if (code == ImageModality.pR)
        return "PR";
      if (code == ImageModality.pT)
        return "PT";
      if (code == ImageModality.pX)
        return "PX";
      if (code == ImageModality.rEG)
        return "REG";
      if (code == ImageModality.rESP)
        return "RESP";
      if (code == ImageModality.rF)
        return "RF";
      if (code == ImageModality.rG)
        return "RG";
      if (code == ImageModality.rTDOSE)
        return "RTDOSE";
      if (code == ImageModality.rTIMAGE)
        return "RTIMAGE";
      if (code == ImageModality.rTPLAN)
        return "RTPLAN";
      if (code == ImageModality.rTRECORD)
        return "RTRECORD";
      if (code == ImageModality.rTSTRUCT)
        return "RTSTRUCT";
      if (code == ImageModality.sC)
        return "SC";
      if (code == ImageModality.sEG)
        return "SEG";
      if (code == ImageModality.sM)
        return "SM";
      if (code == ImageModality.sMR)
        return "SMR";
      if (code == ImageModality.sR)
        return "SR";
      if (code == ImageModality.sRF)
        return "SRF";
      if (code == ImageModality.tG)
        return "TG";
      if (code == ImageModality.uS)
        return "US";
      if (code == ImageModality.vA)
        return "VA";
      if (code == ImageModality.vL)
        return "VL";
      if (code == ImageModality.xA)
        return "XA";
      if (code == ImageModality.xC)
        return "XC";
      return "?";
      }
    }

    public class ImagingStudySeriesComponent extends Element {
        /**
         * The number of this series in the overall sequence
         */
        private Integer number;

        /**
         * The modality of this sequence
         */
        private Enumeration<ImageModality> modality;

        /**
         * Formal identifier for this series
         */
        private Oid uid;

        /**
         * A description of the series
         */
        private String_ description;

        /**
         * Sequence that contains attributes from the
         */
        private Integer numberOfInstances;

        /**
         * Availability of series (online, offline or nearlnie)
         */
        private Enumeration<InstanceAvailability> availability;

        /**
         * WADO-RS URI where Series is available
         */
        private Uri url;

        /**
         * WADO-RS URI where Series is available
         */
        private Oid locationUID;

        /**
         * Body part examined
         */
        private Coding bodySite;

        /**
         * When the series started
         */
        private DateTime dateTime;

        /**
         * A single image taken from a patient
         */
        private List<ImagingStudySeriesInstanceComponent> instance = new ArrayList<ImagingStudySeriesInstanceComponent>();

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

        public Enumeration<ImageModality> getModality() { 
          return this.modality;
        }

        public void setModality(Enumeration<ImageModality> value) { 
          this.modality = value;
        }

        public ImageModality getModalitySimple() { 
          return this.modality == null ? null : this.modality.getValue();
        }

        public void setModalitySimple(ImageModality value) { 
            if (this.modality == null)
              this.modality = new Enumeration<ImageModality>();
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

        public URI getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        public void setUrlSimple(URI value) { 
          if (value == null)
            this.url = null;
          else {
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
          }
        }

        public Oid getLocationUID() { 
          return this.locationUID;
        }

        public void setLocationUID(Oid value) { 
          this.locationUID = value;
        }

        public String getLocationUIDSimple() { 
          return this.locationUID == null ? null : this.locationUID.getValue();
        }

        public void setLocationUIDSimple(String value) { 
          if (value == null)
            this.locationUID = null;
          else {
            if (this.locationUID == null)
              this.locationUID = new Oid();
            this.locationUID.setValue(value);
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

  }

    public class ImagingStudySeriesInstanceComponent extends Element {
        /**
         * The number of this image in the series
         */
        private Integer number;

        /**
         * Formal identifier for this image
         */
        private Oid uid;

        /**
         * DICOM Image type
         */
        private Oid sopclass;

        /**
         * Description to be provided
         */
        private String_ title;

        /**
         * Rows (0028,0010)
         */
        private Integer rows;

        /**
         * Columns (0028,0011)
         */
        private Integer columns;

        /**
         * Bits Allocated (0028,0100)
         */
        private Integer bitsAllocated;

        /**
         * Number of Frames (0028,0008)
         */
        private Integer numberOfFrames;

        /**
         * Availability of instance (online, offline or nearlnie)
         */
        private Enumeration<InstanceAvailability> availability;

        /**
         * WADO url where image is available
         */
        private Uri url;

        /**
         * When this image was taken
         */
        private DateTime dateTime;

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

        public Integer getRows() { 
          return this.rows;
        }

        public void setRows(Integer value) { 
          this.rows = value;
        }

        public int getRowsSimple() { 
          return this.rows == null ? null : this.rows.getValue();
        }

        public void setRowsSimple(int value) { 
          if (value == -1)
            this.rows = null;
          else {
            if (this.rows == null)
              this.rows = new Integer();
            this.rows.setValue(value);
          }
        }

        public Integer getColumns() { 
          return this.columns;
        }

        public void setColumns(Integer value) { 
          this.columns = value;
        }

        public int getColumnsSimple() { 
          return this.columns == null ? null : this.columns.getValue();
        }

        public void setColumnsSimple(int value) { 
          if (value == -1)
            this.columns = null;
          else {
            if (this.columns == null)
              this.columns = new Integer();
            this.columns.setValue(value);
          }
        }

        public Integer getBitsAllocated() { 
          return this.bitsAllocated;
        }

        public void setBitsAllocated(Integer value) { 
          this.bitsAllocated = value;
        }

        public int getBitsAllocatedSimple() { 
          return this.bitsAllocated == null ? null : this.bitsAllocated.getValue();
        }

        public void setBitsAllocatedSimple(int value) { 
          if (value == -1)
            this.bitsAllocated = null;
          else {
            if (this.bitsAllocated == null)
              this.bitsAllocated = new Integer();
            this.bitsAllocated.setValue(value);
          }
        }

        public Integer getNumberOfFrames() { 
          return this.numberOfFrames;
        }

        public void setNumberOfFrames(Integer value) { 
          this.numberOfFrames = value;
        }

        public int getNumberOfFramesSimple() { 
          return this.numberOfFrames == null ? null : this.numberOfFrames.getValue();
        }

        public void setNumberOfFramesSimple(int value) { 
          if (value == -1)
            this.numberOfFrames = null;
          else {
            if (this.numberOfFrames == null)
              this.numberOfFrames = new Integer();
            this.numberOfFrames.setValue(value);
          }
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

        public URI getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        public void setUrlSimple(URI value) { 
          if (value == null)
            this.url = null;
          else {
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
          }
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

  }

    /**
     * Date and Time the study took place
     */
    private DateTime dateTime;

    /**
     * Who the images are of
     */
    private ResourceReference subject;

    /**
     * Formal identifier for the study
     */
    private Oid uid;

    /**
     * Accession Number
     */
    private Identifier accessionNo;

    /**
     * Other identifiers for the study
     */
    private List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The requesting/referring physician
     */
    private ResourceReference referrer;

    /**
     * Availability of study (online, offline or nearlnie)
     */
    private Enumeration<InstanceAvailability> availability;

    /**
     * WADO-RS URI where Study is available
     */
    private Uri url;

    /**
     * Number of Series in Study
     */
    private Integer numberOfSeries;

    /**
     * Number of SOP Instances in Study
     */
    private Integer numberOfInstances;

    /**
     * Diagnoses etc provided with request
     */
    private String_ clinicalInformation;

    /**
     * Type of procedure performed
     */
    private List<Coding> procedure = new ArrayList<Coding>();

    /**
     * Who read study and interpreted the images
     */
    private ResourceReference interpreter;

    /**
     * Institution-generated description or classification of the Study (component) performed
     */
    private String_ description;

    /**
     * Each study has one or more series of image instances
     */
    private List<ImagingStudySeriesComponent> series = new ArrayList<ImagingStudySeriesComponent>();

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

    public URI getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    public void setUrlSimple(URI value) { 
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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ImagingStudy;
   }


}

