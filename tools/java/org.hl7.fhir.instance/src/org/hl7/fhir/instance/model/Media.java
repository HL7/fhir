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

// Generated on Fri, Sep 6, 2013 22:32+1000 for FHIR v0.11

import java.util.*;

/**
 * A photo, video, or audio recording acquired or used in healthcare. The actual content maybe inline or provided by direct reference.
 */
public class Media extends Resource {

    public enum MediaType {
        photo, // The media consists of one or more unmoving images.
        video, // The media consists of a series of frames that capture a moving image.
        audio, // The media consists of a sound recording.
        Null; // added to help the parsers
        public static MediaType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("photo".equals(codeString))
          return photo;
        if ("video".equals(codeString))
          return video;
        if ("audio".equals(codeString))
          return audio;
        throw new Exception("Unknown MediaType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case photo: return "photo";
            case video: return "video";
            case audio: return "audio";
            default: return "?";
          }
        }
    }

  public class MediaTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("photo".equals(codeString))
          return MediaType.photo;
        if ("video".equals(codeString))
          return MediaType.video;
        if ("audio".equals(codeString))
          return MediaType.audio;
        throw new Exception("Unknown MediaType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MediaType.photo)
        return "photo";
      if (code == MediaType.video)
        return "video";
      if (code == MediaType.audio)
        return "audio";
      return "?";
      }
    }

    /**
     * Whether the media is a photo (still image), an audio recording, or a video recording.
     */
    protected Enumeration<MediaType> type;

    /**
     * Details of the type of the media - usually, how it was acquired (what type of device). If images sourced from a DICOM system, are wrapped in a Media resource, then this is the modality.
     */
    protected CodeableConcept subtype;

    /**
     * Identifiers associated with the image - these may include identifiers for the image itself, identifiers for the context of its collection (e.g. series ids) and context ids such as accession numbers or other workflow identifiers.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * When the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the end of the recording.
     */
    protected DateTime dateTime;

    /**
     * Who/What this Media is a record of.
     */
    protected ResourceReference subject;

    /**
     * Who requested that this image be collected.
     */
    protected ResourceReference requester;

    /**
     * The person who administered the collection of the image.
     */
    protected ResourceReference operator;

    /**
     * The name of the imaging view e.g Lateral or Antero-posterior (AP).
     */
    protected CodeableConcept view;

    /**
     * The name of the device / manufacturer of the device  that was used to make the recording.
     */
    protected String_ deviceName;

    /**
     * Height of the image in pixels(photo/video).
     */
    protected Integer height;

    /**
     * Width of the image in pixels (photo/video).
     */
    protected Integer width;

    /**
     * The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single iamge, or an animated gif. If there is more than one frame, this must have a value in order to alert interface software that a multi-frame capable rendering widget is required.
     */
    protected Integer frames;

    /**
     * The length of the recording in seconds - for audio and video.
     */
    protected Integer length;

    /**
     * The actual content of the media - inline or by direct reference to the media source file.
     */
    protected Attachment content;

    public Enumeration<MediaType> getType() { 
      return this.type;
    }

    public void setType(Enumeration<MediaType> value) { 
      this.type = value;
    }

    public MediaType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    public void setTypeSimple(MediaType value) { 
        if (this.type == null)
          this.type = new Enumeration<MediaType>();
        this.type.setValue(value);
    }

    public CodeableConcept getSubtype() { 
      return this.subtype;
    }

    public void setSubtype(CodeableConcept value) { 
      this.subtype = value;
    }

    public List<Identifier> getIdentifier() { 
      return this.identifier;
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

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getRequester() { 
      return this.requester;
    }

    public void setRequester(ResourceReference value) { 
      this.requester = value;
    }

    public ResourceReference getOperator() { 
      return this.operator;
    }

    public void setOperator(ResourceReference value) { 
      this.operator = value;
    }

    public CodeableConcept getView() { 
      return this.view;
    }

    public void setView(CodeableConcept value) { 
      this.view = value;
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

    public Integer getLength() { 
      return this.length;
    }

    public void setLength(Integer value) { 
      this.length = value;
    }

    public int getLengthSimple() { 
      return this.length == null ? null : this.length.getValue();
    }

    public void setLengthSimple(int value) { 
      if (value == -1)
        this.length = null;
      else {
        if (this.length == null)
          this.length = new Integer();
        this.length.setValue(value);
      }
    }

    public Attachment getContent() { 
      return this.content;
    }

    public void setContent(Attachment value) { 
      this.content = value;
    }

      public Media copy() {
        Media dst = new Media();
        dst.type = type == null ? null : type.copy();
        dst.subtype = subtype == null ? null : subtype.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.requester = requester == null ? null : requester.copy();
        dst.operator = operator == null ? null : operator.copy();
        dst.view = view == null ? null : view.copy();
        dst.deviceName = deviceName == null ? null : deviceName.copy();
        dst.height = height == null ? null : height.copy();
        dst.width = width == null ? null : width.copy();
        dst.frames = frames == null ? null : frames.copy();
        dst.length = length == null ? null : length.copy();
        dst.content = content == null ? null : content.copy();
        return dst;
      }

      protected Media typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Media;
   }


}

