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
 * A photo, video, or audio recording acquired or used in healthcare. The actual content may be inline or provided by direct reference.
 */
public class Media extends DomainResource {

    public enum MediaType {
        PHOTO, // The media consists of one or more unmoving images, including photographs, computer-generated graphs and charts, and scanned documents.
        VIDEO, // The media consists of a series of frames that capture a moving image.
        AUDIO, // The media consists of a sound recording.
        NULL; // added to help the parsers
        public static MediaType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("photo".equals(codeString))
          return PHOTO;
        if ("video".equals(codeString))
          return VIDEO;
        if ("audio".equals(codeString))
          return AUDIO;
        throw new Exception("Unknown MediaType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PHOTO: return "photo";
            case VIDEO: return "video";
            case AUDIO: return "audio";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PHOTO: return "The media consists of one or more unmoving images, including photographs, computer-generated graphs and charts, and scanned documents.";
            case VIDEO: return "The media consists of a series of frames that capture a moving image.";
            case AUDIO: return "The media consists of a sound recording.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PHOTO: return "photo";
            case VIDEO: return "video";
            case AUDIO: return "audio";
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
          return MediaType.PHOTO;
        if ("video".equals(codeString))
          return MediaType.VIDEO;
        if ("audio".equals(codeString))
          return MediaType.AUDIO;
        throw new Exception("Unknown MediaType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MediaType.PHOTO)
        return "photo";
      if (code == MediaType.VIDEO)
        return "video";
      if (code == MediaType.AUDIO)
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
     * The date/time when the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the start of the recording.
     */
    protected DateTimeType created;

    /**
     * Who/What this Media is a record of.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (Who/What this Media is a record of.)
     */
    protected Resource subjectTarget;

    /**
     * The person who administered the collection of the image.
     */
    protected Reference operator;

    /**
     * The actual object that is the target of the reference (The person who administered the collection of the image.)
     */
    protected Practitioner operatorTarget;

    /**
     * The name of the imaging view e.g Lateral or Antero-posterior (AP).
     */
    protected CodeableConcept view;

    /**
     * The name of the device / manufacturer of the device  that was used to make the recording.
     */
    protected StringType deviceName;

    /**
     * Height of the image in pixels(photo/video).
     */
    protected IntegerType height;

    /**
     * Width of the image in pixels (photo/video).
     */
    protected IntegerType width;

    /**
     * The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single image, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.
     */
    protected IntegerType frames;

    /**
     * The duration of the recording in seconds - for audio and video.
     */
    protected IntegerType duration;

    /**
     * The actual content of the media - inline or by direct reference to the media source file.
     */
    protected Attachment content;

    private static final long serialVersionUID = 2120969484L;

    public Media() {
      super();
    }

    public Media(Enumeration<MediaType> type, Attachment content) {
      super();
      this.type = type;
      this.content = content;
    }

    /**
     * @return {@link #type} (Whether the media is a photo (still image), an audio recording, or a video recording.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Enumeration<MediaType> getTypeElement() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Whether the media is a photo (still image), an audio recording, or a video recording.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Media setTypeElement(Enumeration<MediaType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return Whether the media is a photo (still image), an audio recording, or a video recording.
     */
    public MediaType getType() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value Whether the media is a photo (still image), an audio recording, or a video recording.
     */
    public Media setType(MediaType value) { 
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

    /**
     * @return {@link #identifier} (Identifiers associated with the image - these may include identifiers for the image itself, identifiers for the context of its collection (e.g. series ids) and context ids such as accession numbers or other workflow identifiers.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #created} (The date/time when the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the start of the recording.). This is the underlying object with id, value and extensions. The accessor "getCreated" gives direct access to the value
     */
    public DateTimeType getCreatedElement() { 
      return this.created;
    }

    /**
     * @param value {@link #created} (The date/time when the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the start of the recording.). This is the underlying object with id, value and extensions. The accessor "getCreated" gives direct access to the value
     */
    public Media setCreatedElement(DateTimeType value) { 
      this.created = value;
      return this;
    }

    /**
     * @return The date/time when the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the start of the recording.
     */
    public DateAndTime getCreated() { 
      return this.created == null ? null : this.created.getValue();
    }

    /**
     * @param value The date/time when the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the start of the recording.
     */
    public Media setCreated(DateAndTime value) { 
      if (value == null)
        this.created = null;
      else {
        if (this.created == null)
          this.created = new DateTimeType();
        this.created.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (Who/What this Media is a record of.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who/What this Media is a record of.)
     */
    public Media setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who/What this Media is a record of.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who/What this Media is a record of.)
     */
    public Media setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #operator} (The person who administered the collection of the image.)
     */
    public Reference getOperator() { 
      return this.operator;
    }

    /**
     * @param value {@link #operator} (The person who administered the collection of the image.)
     */
    public Media setOperator(Reference value) { 
      this.operator = value;
      return this;
    }

    /**
     * @return {@link #operator} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person who administered the collection of the image.)
     */
    public Practitioner getOperatorTarget() { 
      return this.operatorTarget;
    }

    /**
     * @param value {@link #operator} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person who administered the collection of the image.)
     */
    public Media setOperatorTarget(Practitioner value) { 
      this.operatorTarget = value;
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
     * @return {@link #deviceName} (The name of the device / manufacturer of the device  that was used to make the recording.). This is the underlying object with id, value and extensions. The accessor "getDeviceName" gives direct access to the value
     */
    public StringType getDeviceNameElement() { 
      return this.deviceName;
    }

    /**
     * @param value {@link #deviceName} (The name of the device / manufacturer of the device  that was used to make the recording.). This is the underlying object with id, value and extensions. The accessor "getDeviceName" gives direct access to the value
     */
    public Media setDeviceNameElement(StringType value) { 
      this.deviceName = value;
      return this;
    }

    /**
     * @return The name of the device / manufacturer of the device  that was used to make the recording.
     */
    public String getDeviceName() { 
      return this.deviceName == null ? null : this.deviceName.getValue();
    }

    /**
     * @param value The name of the device / manufacturer of the device  that was used to make the recording.
     */
    public Media setDeviceName(String value) { 
      if (Utilities.noString(value))
        this.deviceName = null;
      else {
        if (this.deviceName == null)
          this.deviceName = new StringType();
        this.deviceName.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #height} (Height of the image in pixels(photo/video).). This is the underlying object with id, value and extensions. The accessor "getHeight" gives direct access to the value
     */
    public IntegerType getHeightElement() { 
      return this.height;
    }

    /**
     * @param value {@link #height} (Height of the image in pixels(photo/video).). This is the underlying object with id, value and extensions. The accessor "getHeight" gives direct access to the value
     */
    public Media setHeightElement(IntegerType value) { 
      this.height = value;
      return this;
    }

    /**
     * @return Height of the image in pixels(photo/video).
     */
    public int getHeight() { 
      return this.height == null ? null : this.height.getValue();
    }

    /**
     * @param value Height of the image in pixels(photo/video).
     */
    public Media setHeight(int value) { 
      if (value == -1)
        this.height = null;
      else {
        if (this.height == null)
          this.height = new IntegerType();
        this.height.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #width} (Width of the image in pixels (photo/video).). This is the underlying object with id, value and extensions. The accessor "getWidth" gives direct access to the value
     */
    public IntegerType getWidthElement() { 
      return this.width;
    }

    /**
     * @param value {@link #width} (Width of the image in pixels (photo/video).). This is the underlying object with id, value and extensions. The accessor "getWidth" gives direct access to the value
     */
    public Media setWidthElement(IntegerType value) { 
      this.width = value;
      return this;
    }

    /**
     * @return Width of the image in pixels (photo/video).
     */
    public int getWidth() { 
      return this.width == null ? null : this.width.getValue();
    }

    /**
     * @param value Width of the image in pixels (photo/video).
     */
    public Media setWidth(int value) { 
      if (value == -1)
        this.width = null;
      else {
        if (this.width == null)
          this.width = new IntegerType();
        this.width.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #frames} (The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single image, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.). This is the underlying object with id, value and extensions. The accessor "getFrames" gives direct access to the value
     */
    public IntegerType getFramesElement() { 
      return this.frames;
    }

    /**
     * @param value {@link #frames} (The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single image, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.). This is the underlying object with id, value and extensions. The accessor "getFrames" gives direct access to the value
     */
    public Media setFramesElement(IntegerType value) { 
      this.frames = value;
      return this;
    }

    /**
     * @return The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single image, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.
     */
    public int getFrames() { 
      return this.frames == null ? null : this.frames.getValue();
    }

    /**
     * @param value The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single image, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.
     */
    public Media setFrames(int value) { 
      if (value == -1)
        this.frames = null;
      else {
        if (this.frames == null)
          this.frames = new IntegerType();
        this.frames.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #duration} (The duration of the recording in seconds - for audio and video.). This is the underlying object with id, value and extensions. The accessor "getDuration" gives direct access to the value
     */
    public IntegerType getDurationElement() { 
      return this.duration;
    }

    /**
     * @param value {@link #duration} (The duration of the recording in seconds - for audio and video.). This is the underlying object with id, value and extensions. The accessor "getDuration" gives direct access to the value
     */
    public Media setDurationElement(IntegerType value) { 
      this.duration = value;
      return this;
    }

    /**
     * @return The duration of the recording in seconds - for audio and video.
     */
    public int getDuration() { 
      return this.duration == null ? null : this.duration.getValue();
    }

    /**
     * @param value The duration of the recording in seconds - for audio and video.
     */
    public Media setDuration(int value) { 
      if (value == -1)
        this.duration = null;
      else {
        if (this.duration == null)
          this.duration = new IntegerType();
        this.duration.setValue(value);
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
        childrenList.add(new Property("created", "dateTime", "The date/time when the media was originally recorded. For video and audio, if the length of the recording is not insignificant, this is the start of the recording.", 0, java.lang.Integer.MAX_VALUE, created));
        childrenList.add(new Property("subject", "Reference(Patient|Practitioner|Group|Device|Specimen)", "Who/What this Media is a record of.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("operator", "Reference(Practitioner)", "The person who administered the collection of the image.", 0, java.lang.Integer.MAX_VALUE, operator));
        childrenList.add(new Property("view", "CodeableConcept", "The name of the imaging view e.g Lateral or Antero-posterior (AP).", 0, java.lang.Integer.MAX_VALUE, view));
        childrenList.add(new Property("deviceName", "string", "The name of the device / manufacturer of the device  that was used to make the recording.", 0, java.lang.Integer.MAX_VALUE, deviceName));
        childrenList.add(new Property("height", "integer", "Height of the image in pixels(photo/video).", 0, java.lang.Integer.MAX_VALUE, height));
        childrenList.add(new Property("width", "integer", "Width of the image in pixels (photo/video).", 0, java.lang.Integer.MAX_VALUE, width));
        childrenList.add(new Property("frames", "integer", "The number of frames in a photo. This is used with a multi-page fax, or an imaging acquisition context that takes multiple slices in a single image, or an animated gif. If there is more than one frame, this SHALL have a value in order to alert interface software that a multi-frame capable rendering widget is required.", 0, java.lang.Integer.MAX_VALUE, frames));
        childrenList.add(new Property("duration", "integer", "The duration of the recording in seconds - for audio and video.", 0, java.lang.Integer.MAX_VALUE, duration));
        childrenList.add(new Property("content", "Attachment", "The actual content of the media - inline or by direct reference to the media source file.", 0, java.lang.Integer.MAX_VALUE, content));
      }

      public Media copy() {
        Media dst = new Media();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.subtype = subtype == null ? null : subtype.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.created = created == null ? null : created.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.operator = operator == null ? null : operator.copy();
        dst.view = view == null ? null : view.copy();
        dst.deviceName = deviceName == null ? null : deviceName.copy();
        dst.height = height == null ? null : height.copy();
        dst.width = width == null ? null : width.copy();
        dst.frames = frames == null ? null : frames.copy();
        dst.duration = duration == null ? null : duration.copy();
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

