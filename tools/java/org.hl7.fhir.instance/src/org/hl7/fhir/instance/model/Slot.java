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
 * (informative) A slot of time on a schedule that may be available for booking appointments.
 */
public class Slot extends DomainResource {

    public enum Slotstatus {
        BUSY, // Indicates that the time interval is busy because one  or more events have been scheduled for that interval.
        FREE, // Indicates that the time interval is free for scheduling.
        BUSYUNAVAILABLE, // Indicates that the time interval is busy and that the interval can not be scheduled.
        BUSYTENTATIVE, // Indicates that the time interval is busy because one or more events have been tentatively scheduled for that interval.
        NULL; // added to help the parsers
        public static Slotstatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("BUSY".equals(codeString))
          return BUSY;
        if ("FREE".equals(codeString))
          return FREE;
        if ("BUSY-UNAVAILABLE".equals(codeString))
          return BUSYUNAVAILABLE;
        if ("BUSY-TENTATIVE".equals(codeString))
          return BUSYTENTATIVE;
        throw new Exception("Unknown Slotstatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case BUSY: return "BUSY";
            case FREE: return "FREE";
            case BUSYUNAVAILABLE: return "BUSY-UNAVAILABLE";
            case BUSYTENTATIVE: return "BUSY-TENTATIVE";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case BUSY: return "Indicates that the time interval is busy because one  or more events have been scheduled for that interval.";
            case FREE: return "Indicates that the time interval is free for scheduling.";
            case BUSYUNAVAILABLE: return "Indicates that the time interval is busy and that the interval can not be scheduled.";
            case BUSYTENTATIVE: return "Indicates that the time interval is busy because one or more events have been tentatively scheduled for that interval.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case BUSY: return "BUSY";
            case FREE: return "FREE";
            case BUSYUNAVAILABLE: return "BUSY-UNAVAILABLE";
            case BUSYTENTATIVE: return "BUSY-TENTATIVE";
            default: return "?";
          }
        }
    }

  public static class SlotstatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("BUSY".equals(codeString))
          return Slotstatus.BUSY;
        if ("FREE".equals(codeString))
          return Slotstatus.FREE;
        if ("BUSY-UNAVAILABLE".equals(codeString))
          return Slotstatus.BUSYUNAVAILABLE;
        if ("BUSY-TENTATIVE".equals(codeString))
          return Slotstatus.BUSYTENTATIVE;
        throw new Exception("Unknown Slotstatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Slotstatus.BUSY)
        return "BUSY";
      if (code == Slotstatus.FREE)
        return "FREE";
      if (code == Slotstatus.BUSYUNAVAILABLE)
        return "BUSY-UNAVAILABLE";
      if (code == Slotstatus.BUSYTENTATIVE)
        return "BUSY-TENTATIVE";
      return "?";
      }
    }

    /**
     * External Ids for this item.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The type of appointments that can be booked into this slot (ideally this would be an identifiable service - which is at a location, rather than the location itself). If provided then this overrides the value provided on the availability resource.
     */
    protected CodeableConcept type;

    /**
     * The availability resource that this slot defines an interval of status information.
     */
    protected Reference availability;

    /**
     * The actual object that is the target of the reference (The availability resource that this slot defines an interval of status information.)
     */
    protected Availability availabilityTarget;

    /**
     * BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.
     */
    protected Enumeration<Slotstatus> freeBusyType;

    /**
     * Date/Time that the slot is to begin.
     */
    protected InstantType start;

    /**
     * Date/Time that the slot is to conclude.
     */
    protected InstantType end;

    /**
     * This slot has already been overbooked, appointments are unlikely to be accepted for this time.
     */
    protected BooleanType overbooked;

    /**
     * Comments on the slot to describe any extended information. Such as custom constraints on the slot.
     */
    protected StringType comment;

    /**
     * When this slot was created, or last revised.
     */
    protected DateTimeType lastModified;

    private static final long serialVersionUID = 1162351429L;

    public Slot() {
      super();
    }

    public Slot(Reference availability, Enumeration<Slotstatus> freeBusyType, InstantType start, InstantType end) {
      super();
      this.availability = availability;
      this.freeBusyType = freeBusyType;
      this.start = start;
      this.end = end;
    }

    /**
     * @return {@link #identifier} (External Ids for this item.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (External Ids for this item.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #type} (The type of appointments that can be booked into this slot (ideally this would be an identifiable service - which is at a location, rather than the location itself). If provided then this overrides the value provided on the availability resource.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The type of appointments that can be booked into this slot (ideally this would be an identifiable service - which is at a location, rather than the location itself). If provided then this overrides the value provided on the availability resource.)
     */
    public Slot setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #availability} (The availability resource that this slot defines an interval of status information.)
     */
    public Reference getAvailability() { 
      return this.availability;
    }

    /**
     * @param value {@link #availability} (The availability resource that this slot defines an interval of status information.)
     */
    public Slot setAvailability(Reference value) { 
      this.availability = value;
      return this;
    }

    /**
     * @return {@link #availability} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The availability resource that this slot defines an interval of status information.)
     */
    public Availability getAvailabilityTarget() { 
      return this.availabilityTarget;
    }

    /**
     * @param value {@link #availability} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The availability resource that this slot defines an interval of status information.)
     */
    public Slot setAvailabilityTarget(Availability value) { 
      this.availabilityTarget = value;
      return this;
    }

    /**
     * @return {@link #freeBusyType} (BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.). This is the underlying object with id, value and extensions. The accessor "getFreeBusyType" gives direct access to the value
     */
    public Enumeration<Slotstatus> getFreeBusyTypeElement() { 
      return this.freeBusyType;
    }

    /**
     * @param value {@link #freeBusyType} (BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.). This is the underlying object with id, value and extensions. The accessor "getFreeBusyType" gives direct access to the value
     */
    public Slot setFreeBusyTypeElement(Enumeration<Slotstatus> value) { 
      this.freeBusyType = value;
      return this;
    }

    /**
     * @return BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.
     */
    public Slotstatus getFreeBusyType() { 
      return this.freeBusyType == null ? null : this.freeBusyType.getValue();
    }

    /**
     * @param value BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.
     */
    public Slot setFreeBusyType(Slotstatus value) { 
        if (this.freeBusyType == null)
          this.freeBusyType = new Enumeration<Slotstatus>();
        this.freeBusyType.setValue(value);
      return this;
    }

    /**
     * @return {@link #start} (Date/Time that the slot is to begin.). This is the underlying object with id, value and extensions. The accessor "getStart" gives direct access to the value
     */
    public InstantType getStartElement() { 
      return this.start;
    }

    /**
     * @param value {@link #start} (Date/Time that the slot is to begin.). This is the underlying object with id, value and extensions. The accessor "getStart" gives direct access to the value
     */
    public Slot setStartElement(InstantType value) { 
      this.start = value;
      return this;
    }

    /**
     * @return Date/Time that the slot is to begin.
     */
    public DateAndTime getStart() { 
      return this.start == null ? null : this.start.getValue();
    }

    /**
     * @param value Date/Time that the slot is to begin.
     */
    public Slot setStart(DateAndTime value) { 
        if (this.start == null)
          this.start = new InstantType();
        this.start.setValue(value);
      return this;
    }

    /**
     * @return {@link #end} (Date/Time that the slot is to conclude.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
     */
    public InstantType getEndElement() { 
      return this.end;
    }

    /**
     * @param value {@link #end} (Date/Time that the slot is to conclude.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
     */
    public Slot setEndElement(InstantType value) { 
      this.end = value;
      return this;
    }

    /**
     * @return Date/Time that the slot is to conclude.
     */
    public DateAndTime getEnd() { 
      return this.end == null ? null : this.end.getValue();
    }

    /**
     * @param value Date/Time that the slot is to conclude.
     */
    public Slot setEnd(DateAndTime value) { 
        if (this.end == null)
          this.end = new InstantType();
        this.end.setValue(value);
      return this;
    }

    /**
     * @return {@link #overbooked} (This slot has already been overbooked, appointments are unlikely to be accepted for this time.). This is the underlying object with id, value and extensions. The accessor "getOverbooked" gives direct access to the value
     */
    public BooleanType getOverbookedElement() { 
      return this.overbooked;
    }

    /**
     * @param value {@link #overbooked} (This slot has already been overbooked, appointments are unlikely to be accepted for this time.). This is the underlying object with id, value and extensions. The accessor "getOverbooked" gives direct access to the value
     */
    public Slot setOverbookedElement(BooleanType value) { 
      this.overbooked = value;
      return this;
    }

    /**
     * @return This slot has already been overbooked, appointments are unlikely to be accepted for this time.
     */
    public boolean getOverbooked() { 
      return this.overbooked == null ? false : this.overbooked.getValue();
    }

    /**
     * @param value This slot has already been overbooked, appointments are unlikely to be accepted for this time.
     */
    public Slot setOverbooked(boolean value) { 
      if (value == false)
        this.overbooked = null;
      else {
        if (this.overbooked == null)
          this.overbooked = new BooleanType();
        this.overbooked.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #comment} (Comments on the slot to describe any extended information. Such as custom constraints on the slot.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public StringType getCommentElement() { 
      return this.comment;
    }

    /**
     * @param value {@link #comment} (Comments on the slot to describe any extended information. Such as custom constraints on the slot.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public Slot setCommentElement(StringType value) { 
      this.comment = value;
      return this;
    }

    /**
     * @return Comments on the slot to describe any extended information. Such as custom constraints on the slot.
     */
    public String getComment() { 
      return this.comment == null ? null : this.comment.getValue();
    }

    /**
     * @param value Comments on the slot to describe any extended information. Such as custom constraints on the slot.
     */
    public Slot setComment(String value) { 
      if (Utilities.noString(value))
        this.comment = null;
      else {
        if (this.comment == null)
          this.comment = new StringType();
        this.comment.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #lastModified} (When this slot was created, or last revised.). This is the underlying object with id, value and extensions. The accessor "getLastModified" gives direct access to the value
     */
    public DateTimeType getLastModifiedElement() { 
      return this.lastModified;
    }

    /**
     * @param value {@link #lastModified} (When this slot was created, or last revised.). This is the underlying object with id, value and extensions. The accessor "getLastModified" gives direct access to the value
     */
    public Slot setLastModifiedElement(DateTimeType value) { 
      this.lastModified = value;
      return this;
    }

    /**
     * @return When this slot was created, or last revised.
     */
    public DateAndTime getLastModified() { 
      return this.lastModified == null ? null : this.lastModified.getValue();
    }

    /**
     * @param value When this slot was created, or last revised.
     */
    public Slot setLastModified(DateAndTime value) { 
      if (value == null)
        this.lastModified = null;
      else {
        if (this.lastModified == null)
          this.lastModified = new DateTimeType();
        this.lastModified.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "External Ids for this item.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "The type of appointments that can be booked into this slot (ideally this would be an identifiable service - which is at a location, rather than the location itself). If provided then this overrides the value provided on the availability resource.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("availability", "Reference(Availability)", "The availability resource that this slot defines an interval of status information.", 0, java.lang.Integer.MAX_VALUE, availability));
        childrenList.add(new Property("freeBusyType", "code", "BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.", 0, java.lang.Integer.MAX_VALUE, freeBusyType));
        childrenList.add(new Property("start", "instant", "Date/Time that the slot is to begin.", 0, java.lang.Integer.MAX_VALUE, start));
        childrenList.add(new Property("end", "instant", "Date/Time that the slot is to conclude.", 0, java.lang.Integer.MAX_VALUE, end));
        childrenList.add(new Property("overbooked", "boolean", "This slot has already been overbooked, appointments are unlikely to be accepted for this time.", 0, java.lang.Integer.MAX_VALUE, overbooked));
        childrenList.add(new Property("comment", "string", "Comments on the slot to describe any extended information. Such as custom constraints on the slot.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("lastModified", "dateTime", "When this slot was created, or last revised.", 0, java.lang.Integer.MAX_VALUE, lastModified));
      }

      public Slot copy() {
        Slot dst = new Slot();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.availability = availability == null ? null : availability.copy();
        dst.freeBusyType = freeBusyType == null ? null : freeBusyType.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        dst.overbooked = overbooked == null ? null : overbooked.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.lastModified = lastModified == null ? null : lastModified.copy();
        return dst;
      }

      protected Slot typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Slot;
   }


}

