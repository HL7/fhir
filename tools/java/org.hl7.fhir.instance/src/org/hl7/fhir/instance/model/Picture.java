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

// Generated on Fri, Jun 7, 2013 00:21+1000 for FHIR v0.09

/**
 * An Image used in healthcare. The actual pixels maybe inline or provided by direct reference
 */
public class Picture extends Resource {

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

    /**
     * Who/What this image is taken of
     */
    private ResourceReference subject;

    /**
     * When the image was taken
     */
    private DateTime dateTime;

    /**
     * The person who generated the image
     */
    private ResourceReference operator;

    /**
     * Identifier for the image
     */
    private Identifier identifier;

    /**
     * An identifier for the order that is used by the application/system that requested the image to link back to the original context (if there was such a system). This is not the identity of the image, but of the "request for an image to be generated"
     */
    private Identifier accessionNo;

    /**
     * The session in which the picture was taken.
     */
    private Identifier studyId;

    /**
     * The series of images in which this picture was taken
     */
    private Identifier seriesId;

    /**
     * A reference to the method/protocol that was followed when the images were taken
     */
    private CodeableConcept method;

    /**
     * Who asked that this image be collected
     */
    private ResourceReference requester;

    /**
     * Type of the image capturing machinery
     */
    private Enumeration<ImageModality> modality;

    /**
     * Name of the manufacturer
     */
    private String_ deviceName;

    /**
     * Height of the image
     */
    private Integer height;

    /**
     * Width of the image
     */
    private Integer width;

    /**
     * Number of bits of colour (2..32)
     */
    private Integer bits;

    /**
     * Number of frames
     */
    private Integer frames;

    /**
     * Length of time between frames
     */
    private Duration frameDelay;

    /**
     * The name of the imaging view e.g Lateral or Antero-posterior (AP).
     */
    private CodeableConcept view;

    /**
     * Actual picture - reference or data
     */
    private Attachment content;

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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Picture;
   }


}

