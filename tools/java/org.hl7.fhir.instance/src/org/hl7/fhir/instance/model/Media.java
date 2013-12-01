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

// Generated on Sun, Dec 1, 2013 22:52+1100 for FHIR v0.12

import java.util.*;

/**
 * A photo, video, or audio recording acquired or used in healthcare. The actual content maybe inline or provided by direct reference.
 */
public class Media extends Resource {

    public enum MediaType {
        photo, // The media consists of one or more unmoving images, including photographs, computer-generated graphs and charts, and scanned documents.
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

  public static class MediaTypeEnumFactory implements EnumFactory {
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
     * The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single iamge, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.
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

    public Media() {
      super();
    }

    public Media(Enumeration<MediaType> type, Attachment content) {
      super();
      this.type = type;
      this.content = content;
    }

    /**
     * @return {@link #type} (Whether the media is a photo (still image), an audio recording, or a video recording.)
     */
    public Enumeration<MediaType> getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Whether the media is a photo (still image), an audio recording, or a video recording.)
     */
    public Media setType(Enumeration<MediaType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return Whether the media is a photo (still image), an audio recording, or a video recording.
     */
    public MediaType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value Whether the media is a photo (still image), an audio recording, or a video recording.
     */
    public Media setTypeSimple(MediaType value) { 
        if (this.type == null)
          this.type = new Enumeration<MediaType>();
        this.type.setValue(value);
      return this;
    }

    /**
     * @return {@link #subtype} (Details of the type of the media - usually, how it was acquired (what type of device). If images sourced from a DICOM system, are wrapped in a Media resource, then this is the modality.)
     */
    public CodeableConcept getSubtype() { 
      return this.subtype;
    }

    /**
     * @param value {@link #subtype} (Details of the type of the media - usually, how it was acquired (what type of device). If images sourced from a DICOM system, are wrapped in a Media resource, then this is the modality.)
     */
    public Media setSubtype(CodeableConcept value) { 
      this.subtype = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Identifiers associated with the image - these may include identifiers for the image itself, identifiers for the context of its collection (e.g. series ids) and context ids such as accession numbers or other workflow identifiers.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifiers associated with the image - these may include identifiers for the image itself, identifiers for the context of its collection (e.g. series ids) and context ids such as accession numbers or other workflow identifiers.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #dateTime} (When the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the end of the recording.)
     */
    public DateTime getDateTime() { 
      return this.dateTime;
    }

    /**
     * @param value {@link #dateTime} (When the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the end of the recording.)
     */
    public Media setDateTime(DateTime value) { 
      this.dateTime = value;
      return this;
    }

    /**
     * @return When the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the end of the recording.
     */
    public String getDateTimeSimple() { 
      return this.dateTime == null ? null : this.dateTime.getValue();
    }

    /**
     * @param value When the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the end of the recording.
     */
    public Media setDateTimeSimple(String value) { 
      if (value == null)
        this.dateTime = null;
      else {
        if (this.dateTime == null)
          this.dateTime = new DateTime();
        this.dateTime.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (Who/What this Media is a record of.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who/What this Media is a record of.)
     */
    public Media setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #operator} (The person who administered the collection of the image.)
     */
    public ResourceReference getOperator() { 
      return this.operator;
    }

    /**
     * @param value {@link #operator} (The person who administered the collection of the image.)
     */
    public Media setOperator(ResourceReference value) { 
      this.operator = value;
      return this;
    }

    /**
     * @return {@link #view} (The name of the imaging view e.g Lateral or Antero-posterior (AP).)
     */
    public CodeableConcept getView() { 
      return this.view;
    }

    /**
     * @param value {@link #view} (The name of the imaging view e.g Lateral or Antero-posterior (AP).)
     */
    public Media setView(CodeableConcept value) { 
      this.view = value;
      return this;
    }

    /**
     * @return {@link #deviceName} (The name of the device / manufacturer of the device  that was used to make the recording.)
     */
    public String_ getDeviceName() { 
      return this.deviceName;
    }

    /**
     * @param value {@link #deviceName} (The name of the device / manufacturer of the device  that was used to make the recording.)
     */
    public Media setDeviceName(String_ value) { 
      this.deviceName = value;
      return this;
    }

    /**
     * @return The name of the device / manufacturer of the device  that was used to make the recording.
     */
    public String getDeviceNameSimple() { 
      return this.deviceName == null ? null : this.deviceName.getValue();
    }

    /**
     * @param value The name of the device / manufacturer of the device  that was used to make the recording.
     */
    public Media setDeviceNameSimple(String value) { 
      if (value == null)
        this.deviceName = null;
      else {
        if (this.deviceName == null)
          this.deviceName = new String_();
        this.deviceName.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #height} (Height of the image in pixels(photo/video).)
     */
    public Integer getHeight() { 
      return this.height;
    }

    /**
     * @param value {@link #height} (Height of the image in pixels(photo/video).)
     */
    public Media setHeight(Integer value) { 
      this.height = value;
      return this;
    }

    /**
     * @return Height of the image in pixels(photo/video).
     */
    public int getHeightSimple() { 
      return this.height == null ? null : this.height.getValue();
    }

    /**
     * @param value Height of the image in pixels(photo/video).
     */
    public Media setHeightSimple(int value) { 
      if (value == -1)
        this.height = null;
      else {
        if (this.height == null)
          this.height = new Integer();
        this.height.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #width} (Width of the image in pixels (photo/video).)
     */
    public Integer getWidth() { 
      return this.width;
    }

    /**
     * @param value {@link #width} (Width of the image in pixels (photo/video).)
     */
    public Media setWidth(Integer value) { 
      this.width = value;
      return this;
    }

    /**
     * @return Width of the image in pixels (photo/video).
     */
    public int getWidthSimple() { 
      return this.width == null ? null : this.width.getValue();
    }

    /**
     * @param value Width of the image in pixels (photo/video).
     */
    public Media setWidthSimple(int value) { 
      if (value == -1)
        this.width = null;
      else {
        if (this.width == null)
          this.width = new Integer();
        this.width.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #frames} (The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single iamge, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.)
     */
    public Integer getFrames() { 
      return this.frames;
    }

    /**
     * @param value {@link #frames} (The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single iamge, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.)
     */
    public Media setFrames(Integer value) { 
      this.frames = value;
      return this;
    }

    /**
     * @return The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single iamge, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.
     */
    public int getFramesSimple() { 
      return this.frames == null ? null : this.frames.getValue();
    }

    /**
     * @param value The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single iamge, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.
     */
    public Media setFramesSimple(int value) { 
      if (value == -1)
        this.frames = null;
      else {
        if (this.frames == null)
          this.frames = new Integer();
        this.frames.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #length} (The length of the recording in seconds - for audio and video.)
     */
    public Integer getLength() { 
      return this.length;
    }

    /**
     * @param value {@link #length} (The length of the recording in seconds - for audio and video.)
     */
    public Media setLength(Integer value) { 
      this.length = value;
      return this;
    }

    /**
     * @return The length of the recording in seconds - for audio and video.
     */
    public int getLengthSimple() { 
      return this.length == null ? null : this.length.getValue();
    }

    /**
     * @param value The length of the recording in seconds - for audio and video.
     */
    public Media setLengthSimple(int value) { 
      if (value == -1)
        this.length = null;
      else {
        if (this.length == null)
          this.length = new Integer();
        this.length.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #content} (The actual content of the media - inline or by direct reference to the media source file.)
     */
    public Attachment getContent() { 
      return this.content;
    }

    /**
     * @param value {@link #content} (The actual content of the media - inline or by direct reference to the media source file.)
     */
    public Media setContent(Attachment value) { 
      this.content = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("type", "code", "Whether the media is a photo (still image), an audio recording, or a video recording.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("subtype", "CodeableConcept", "Details of the type of the media - usually, how it was acquired (what type of device). If images sourced from a DICOM system, are wrapped in a Media resource, then this is the modality.", 0, java.lang.Integer.MAX_VALUE, subtype));
        childrenList.add(new Property("identifier", "Identifier", "Identifiers associated with the image - these may include identifiers for the image itself, identifiers for the context of its collection (e.g. series ids) and context ids such as accession numbers or other workflow identifiers.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("dateTime", "dateTime", "When the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the end of the recording.", 0, java.lang.Integer.MAX_VALUE, dateTime));
        childrenList.add(new Property("subject", "Resource(Patient|Practitioner|Group|Device|Specimen)", "Who/What this Media is a record of.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("operator", "Resource(Practitioner)", "The person who administered the collection of the image.", 0, java.lang.Integer.MAX_VALUE, operator));
        childrenList.add(new Property("view", "CodeableConcept", "The name of the imaging view e.g Lateral or Antero-posterior (AP).", 0, java.lang.Integer.MAX_VALUE, view));
        childrenList.add(new Property("deviceName", "string", "The name of the device / manufacturer of the device  that was used to make the recording.", 0, java.lang.Integer.MAX_VALUE, deviceName));
        childrenList.add(new Property("height", "integer", "Height of the image in pixels(photo/video).", 0, java.lang.Integer.MAX_VALUE, height));
        childrenList.add(new Property("width", "integer", "Width of the image in pixels (photo/video).", 0, java.lang.Integer.MAX_VALUE, width));
        childrenList.add(new Property("frames", "integer", "The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single iamge, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.", 0, java.lang.Integer.MAX_VALUE, frames));
        childrenList.add(new Property("length", "integer", "The length of the recording in seconds - for audio and video.", 0, java.lang.Integer.MAX_VALUE, length));
        childrenList.add(new Property("content", "Attachment", "The actual content of the media - inline or by direct reference to the media source file.", 0, java.lang.Integer.MAX_VALUE, content));
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

