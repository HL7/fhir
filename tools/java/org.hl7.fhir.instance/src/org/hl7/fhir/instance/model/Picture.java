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

// Generated on Mon, Aug 5, 2013 12:50+1000 for FHIR v0.10

/**
 * An Image used in healthcare. The actual pixels maybe inline or provided by direct reference.
 */
public class Picture extends Resource {

    public enum PictureType {
        dIA, // Hand-drawn diagram.
        aR, // Autorefraction.
        aU, // Audio.
        bDUS, // Bone Densitometry (Ultrasound).
        bI, // Biomagnetic Imaging.
        bMD, // Bone Densitometry (X-Ray).
        cR, // Computed Radiography.
        cT, // Computed Tomography.
        dG, // Diaphanography.
        dOC, // Document.
        dX, // Digital Radiography.
        eCG, // Electrocardiograms.
        eM, // Electron Microscope.
        ePS, // Cardiac Electrophysiology.
        eS, // Endoscopy.
        fID, // Fiducials.
        gM, // General Microscopy.
        hC, // Hard Copy.
        hD, // Hemodynamic Waveform.
        iO, // Intra-oral Radiography.
        iOL, // Intraocular Lens Data.
        iVOCT, // Intravascular Optical Coherence Tomography.
        iVUS, // Intravascular Ultrasound.
        kO, // Key Object Selection.
        kER, // Keratometry.
        lEN, // Lensometry.
        lS, // Laser Surface Scan.
        mG, // Mammography.
        mR, // Magnetic Resonance.
        nM, // Nuclear Medicine.
        oAM, // Opthalmic Axial Measurements.
        oCT, // Optical Coherence Tomography.
        oPM, // Ophthalmic Mapping.
        oPT, // Opthalmic Tomography.
        oPV, // Ophthalmic Visual Field.
        oT, // Other.
        pLAN, // Plan.
        pR, // Presentation State.
        pT, // Positron Emission Tomography (PET).
        pX, // Panoramic X-Ray.
        rEG, // Registration.
        rESP, // Respiratory Waveform.
        rF, // Radio Fluoroscopy.
        rG, // Radiographic Imaging (conventional film screen).
        rTDOSE, // Radiotherapy Dose.
        rTIMAGE, // Radiotherapy Image.
        rTPLAN, // Radiotherapy Plan (a.k.a. RTPLAN).
        rTRECORD, // RT Treatment Record.
        rTSTRUCT, // Radiotherapy Structure Set (a.k.a. RTSTRUCT).
        sC, // Secondary Capture.
        sEG, // Segmentation.
        sM, // Slide Microscopy.
        sMR, // Stereometric Relationship.
        sR, // SR Document.
        sRF, // Subjective Refraction.
        tG, // Thermography.
        uS, // Ultrasound.
        vA, // Visual Acuity.
        vL, // Visible Light.
        xA, // X-Ray Angiography.
        xC, // External Camera (Photography).
        Null; // added to help the parsers
        public static PictureType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("DIA".equals(codeString))
          return dIA;
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
        throw new Exception("Unknown PictureType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case dIA: return "DIA";
            case aR: return "AR";
            case aU: return "AU";
            case bDUS: return "BDUS";
            case bI: return "BI";
            case bMD: return "BMD";
            case cR: return "CR";
            case cT: return "CT";
            case dG: return "DG";
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

  public class PictureTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("DIA".equals(codeString))
          return PictureType.dIA;
        if ("AR".equals(codeString))
          return PictureType.aR;
        if ("AU".equals(codeString))
          return PictureType.aU;
        if ("BDUS".equals(codeString))
          return PictureType.bDUS;
        if ("BI".equals(codeString))
          return PictureType.bI;
        if ("BMD".equals(codeString))
          return PictureType.bMD;
        if ("CR".equals(codeString))
          return PictureType.cR;
        if ("CT".equals(codeString))
          return PictureType.cT;
        if ("DG".equals(codeString))
          return PictureType.dG;
        if ("DOC".equals(codeString))
          return PictureType.dOC;
        if ("DX".equals(codeString))
          return PictureType.dX;
        if ("ECG".equals(codeString))
          return PictureType.eCG;
        if ("EM".equals(codeString))
          return PictureType.eM;
        if ("EPS".equals(codeString))
          return PictureType.ePS;
        if ("ES".equals(codeString))
          return PictureType.eS;
        if ("FID".equals(codeString))
          return PictureType.fID;
        if ("GM".equals(codeString))
          return PictureType.gM;
        if ("HC".equals(codeString))
          return PictureType.hC;
        if ("HD".equals(codeString))
          return PictureType.hD;
        if ("IO".equals(codeString))
          return PictureType.iO;
        if ("IOL".equals(codeString))
          return PictureType.iOL;
        if ("IVOCT".equals(codeString))
          return PictureType.iVOCT;
        if ("IVUS".equals(codeString))
          return PictureType.iVUS;
        if ("KO".equals(codeString))
          return PictureType.kO;
        if ("KER".equals(codeString))
          return PictureType.kER;
        if ("LEN".equals(codeString))
          return PictureType.lEN;
        if ("LS".equals(codeString))
          return PictureType.lS;
        if ("MG".equals(codeString))
          return PictureType.mG;
        if ("MR".equals(codeString))
          return PictureType.mR;
        if ("NM".equals(codeString))
          return PictureType.nM;
        if ("OAM".equals(codeString))
          return PictureType.oAM;
        if ("OCT".equals(codeString))
          return PictureType.oCT;
        if ("OPM".equals(codeString))
          return PictureType.oPM;
        if ("OPT".equals(codeString))
          return PictureType.oPT;
        if ("OPV".equals(codeString))
          return PictureType.oPV;
        if ("OT".equals(codeString))
          return PictureType.oT;
        if ("PLAN".equals(codeString))
          return PictureType.pLAN;
        if ("PR".equals(codeString))
          return PictureType.pR;
        if ("PT".equals(codeString))
          return PictureType.pT;
        if ("PX".equals(codeString))
          return PictureType.pX;
        if ("REG".equals(codeString))
          return PictureType.rEG;
        if ("RESP".equals(codeString))
          return PictureType.rESP;
        if ("RF".equals(codeString))
          return PictureType.rF;
        if ("RG".equals(codeString))
          return PictureType.rG;
        if ("RTDOSE".equals(codeString))
          return PictureType.rTDOSE;
        if ("RTIMAGE".equals(codeString))
          return PictureType.rTIMAGE;
        if ("RTPLAN".equals(codeString))
          return PictureType.rTPLAN;
        if ("RTRECORD".equals(codeString))
          return PictureType.rTRECORD;
        if ("RTSTRUCT".equals(codeString))
          return PictureType.rTSTRUCT;
        if ("SC".equals(codeString))
          return PictureType.sC;
        if ("SEG".equals(codeString))
          return PictureType.sEG;
        if ("SM".equals(codeString))
          return PictureType.sM;
        if ("SMR".equals(codeString))
          return PictureType.sMR;
        if ("SR".equals(codeString))
          return PictureType.sR;
        if ("SRF".equals(codeString))
          return PictureType.sRF;
        if ("TG".equals(codeString))
          return PictureType.tG;
        if ("US".equals(codeString))
          return PictureType.uS;
        if ("VA".equals(codeString))
          return PictureType.vA;
        if ("VL".equals(codeString))
          return PictureType.vL;
        if ("XA".equals(codeString))
          return PictureType.xA;
        if ("XC".equals(codeString))
          return PictureType.xC;
        throw new Exception("Unknown PictureType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == PictureType.dIA)
        return "DIA";
      if (code == PictureType.aR)
        return "AR";
      if (code == PictureType.aU)
        return "AU";
      if (code == PictureType.bDUS)
        return "BDUS";
      if (code == PictureType.bI)
        return "BI";
      if (code == PictureType.bMD)
        return "BMD";
      if (code == PictureType.cR)
        return "CR";
      if (code == PictureType.cT)
        return "CT";
      if (code == PictureType.dG)
        return "DG";
      if (code == PictureType.dOC)
        return "DOC";
      if (code == PictureType.dX)
        return "DX";
      if (code == PictureType.eCG)
        return "ECG";
      if (code == PictureType.eM)
        return "EM";
      if (code == PictureType.ePS)
        return "EPS";
      if (code == PictureType.eS)
        return "ES";
      if (code == PictureType.fID)
        return "FID";
      if (code == PictureType.gM)
        return "GM";
      if (code == PictureType.hC)
        return "HC";
      if (code == PictureType.hD)
        return "HD";
      if (code == PictureType.iO)
        return "IO";
      if (code == PictureType.iOL)
        return "IOL";
      if (code == PictureType.iVOCT)
        return "IVOCT";
      if (code == PictureType.iVUS)
        return "IVUS";
      if (code == PictureType.kO)
        return "KO";
      if (code == PictureType.kER)
        return "KER";
      if (code == PictureType.lEN)
        return "LEN";
      if (code == PictureType.lS)
        return "LS";
      if (code == PictureType.mG)
        return "MG";
      if (code == PictureType.mR)
        return "MR";
      if (code == PictureType.nM)
        return "NM";
      if (code == PictureType.oAM)
        return "OAM";
      if (code == PictureType.oCT)
        return "OCT";
      if (code == PictureType.oPM)
        return "OPM";
      if (code == PictureType.oPT)
        return "OPT";
      if (code == PictureType.oPV)
        return "OPV";
      if (code == PictureType.oT)
        return "OT";
      if (code == PictureType.pLAN)
        return "PLAN";
      if (code == PictureType.pR)
        return "PR";
      if (code == PictureType.pT)
        return "PT";
      if (code == PictureType.pX)
        return "PX";
      if (code == PictureType.rEG)
        return "REG";
      if (code == PictureType.rESP)
        return "RESP";
      if (code == PictureType.rF)
        return "RF";
      if (code == PictureType.rG)
        return "RG";
      if (code == PictureType.rTDOSE)
        return "RTDOSE";
      if (code == PictureType.rTIMAGE)
        return "RTIMAGE";
      if (code == PictureType.rTPLAN)
        return "RTPLAN";
      if (code == PictureType.rTRECORD)
        return "RTRECORD";
      if (code == PictureType.rTSTRUCT)
        return "RTSTRUCT";
      if (code == PictureType.sC)
        return "SC";
      if (code == PictureType.sEG)
        return "SEG";
      if (code == PictureType.sM)
        return "SM";
      if (code == PictureType.sMR)
        return "SMR";
      if (code == PictureType.sR)
        return "SR";
      if (code == PictureType.sRF)
        return "SRF";
      if (code == PictureType.tG)
        return "TG";
      if (code == PictureType.uS)
        return "US";
      if (code == PictureType.vA)
        return "VA";
      if (code == PictureType.vL)
        return "VL";
      if (code == PictureType.xA)
        return "XA";
      if (code == PictureType.xC)
        return "XC";
      return "?";
      }
    }

    /**
     * Who/What this image is taken of.
     */
    protected ResourceReference subject;

    /**
     * When the image was taken.
     */
    protected DateTime dateTime;

    /**
     * The person who generated the image.
     */
    protected ResourceReference operator;

    /**
     * Identifier for the image.
     */
    protected Identifier identifier;

    /**
     * An identifier for the order that is used by the application/system that requested the image to link back to the original context (if there was such a system). This is not the identity of the image, but of the "request for an image to be generated".
     */
    protected Identifier accessionNo;

    /**
     * The session in which the picture was taken.
     */
    protected Identifier studyId;

    /**
     * The series of images in which this picture was taken.
     */
    protected Identifier seriesId;

    /**
     * A reference to the method/protocol that was followed when the images were taken.
     */
    protected CodeableConcept method;

    /**
     * Who asked that this image be collected.
     */
    protected ResourceReference requester;

    /**
     * Type of the image capturing machinery.
     */
    protected Enumeration<PictureType> modality;

    /**
     * Name of the manufacturer.
     */
    protected String_ deviceName;

    /**
     * Height of the image.
     */
    protected Integer height;

    /**
     * Width of the image.
     */
    protected Integer width;

    /**
     * Number of bits of colour (2..32).
     */
    protected Integer bits;

    /**
     * Number of frames.
     */
    protected Integer frames;

    /**
     * Length of time between frames.
     */
    protected Duration frameDelay;

    /**
     * The name of the imaging view e.g Lateral or Antero-posterior (AP).
     */
    protected CodeableConcept view;

    /**
     * Actual picture - reference or data.
     */
    protected Attachment content;

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
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

    public ResourceReference getOperator() { 
      return this.operator;
    }

    public void setOperator(ResourceReference value) { 
      this.operator = value;
    }

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public Identifier getAccessionNo() { 
      return this.accessionNo;
    }

    public void setAccessionNo(Identifier value) { 
      this.accessionNo = value;
    }

    public Identifier getStudyId() { 
      return this.studyId;
    }

    public void setStudyId(Identifier value) { 
      this.studyId = value;
    }

    public Identifier getSeriesId() { 
      return this.seriesId;
    }

    public void setSeriesId(Identifier value) { 
      this.seriesId = value;
    }

    public CodeableConcept getMethod() { 
      return this.method;
    }

    public void setMethod(CodeableConcept value) { 
      this.method = value;
    }

    public ResourceReference getRequester() { 
      return this.requester;
    }

    public void setRequester(ResourceReference value) { 
      this.requester = value;
    }

    public Enumeration<PictureType> getModality() { 
      return this.modality;
    }

    public void setModality(Enumeration<PictureType> value) { 
      this.modality = value;
    }

    public PictureType getModalitySimple() { 
      return this.modality == null ? null : this.modality.getValue();
    }

    public void setModalitySimple(PictureType value) { 
        if (this.modality == null)
          this.modality = new Enumeration<PictureType>();
        this.modality.setValue(value);
    }

    public String_ getDeviceName() { 
      return this.deviceName;
    }

    public void setDeviceName(String_ value) { 
      this.deviceName = value;
    }

    public String getDeviceNameSimple() { 
      return this.deviceName == null ? null : this.deviceName.getValue();
    }

    public void setDeviceNameSimple(String value) { 
      if (value == null)
        this.deviceName = null;
      else {
        if (this.deviceName == null)
          this.deviceName = new String_();
        this.deviceName.setValue(value);
      }
    }

    public Integer getHeight() { 
      return this.height;
    }

    public void setHeight(Integer value) { 
      this.height = value;
    }

    public int getHeightSimple() { 
      return this.height == null ? null : this.height.getValue();
    }

    public void setHeightSimple(int value) { 
      if (value == -1)
        this.height = null;
      else {
        if (this.height == null)
          this.height = new Integer();
        this.height.setValue(value);
      }
    }

    public Integer getWidth() { 
      return this.width;
    }

    public void setWidth(Integer value) { 
      this.width = value;
    }

    public int getWidthSimple() { 
      return this.width == null ? null : this.width.getValue();
    }

    public void setWidthSimple(int value) { 
      if (value == -1)
        this.width = null;
      else {
        if (this.width == null)
          this.width = new Integer();
        this.width.setValue(value);
      }
    }

    public Integer getBits() { 
      return this.bits;
    }

    public void setBits(Integer value) { 
      this.bits = value;
    }

    public int getBitsSimple() { 
      return this.bits == null ? null : this.bits.getValue();
    }

    public void setBitsSimple(int value) { 
      if (value == -1)
        this.bits = null;
      else {
        if (this.bits == null)
          this.bits = new Integer();
        this.bits.setValue(value);
      }
    }

    public Integer getFrames() { 
      return this.frames;
    }

    public void setFrames(Integer value) { 
      this.frames = value;
    }

    public int getFramesSimple() { 
      return this.frames == null ? null : this.frames.getValue();
    }

    public void setFramesSimple(int value) { 
      if (value == -1)
        this.frames = null;
      else {
        if (this.frames == null)
          this.frames = new Integer();
        this.frames.setValue(value);
      }
    }

    public Duration getFrameDelay() { 
      return this.frameDelay;
    }

    public void setFrameDelay(Duration value) { 
      this.frameDelay = value;
    }

    public CodeableConcept getView() { 
      return this.view;
    }

    public void setView(CodeableConcept value) { 
      this.view = value;
    }

    public Attachment getContent() { 
      return this.content;
    }

    public void setContent(Attachment value) { 
      this.content = value;
    }

      public Picture copy() {
        Picture dst = new Picture();
        dst.subject = subject == null ? null : subject.copy();
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.operator = operator == null ? null : operator.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.accessionNo = accessionNo == null ? null : accessionNo.copy();
        dst.studyId = studyId == null ? null : studyId.copy();
        dst.seriesId = seriesId == null ? null : seriesId.copy();
        dst.method = method == null ? null : method.copy();
        dst.requester = requester == null ? null : requester.copy();
        dst.modality = modality == null ? null : modality.copy();
        dst.deviceName = deviceName == null ? null : deviceName.copy();
        dst.height = height == null ? null : height.copy();
        dst.width = width == null ? null : width.copy();
        dst.bits = bits == null ? null : bits.copy();
        dst.frames = frames == null ? null : frames.copy();
        dst.frameDelay = frameDelay == null ? null : frameDelay.copy();
        dst.view = view == null ? null : view.copy();
        dst.content = content == null ? null : content.copy();
        return dst;
      }

      protected Picture typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Picture;
   }


}

