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

// Generated on Wed, Jan 22, 2014 22:45-0600 for FHIR v0.12

import java.util.*;

/**
 * (informative) A slot of time that may be available for booking appointments.
 */
public class Slot extends Resource {

    public enum Slotstatus {
        bUSY, // Indicates that the time interval is busy because one  or more events have been scheduled for that interval.
        fREE, // Indicates that the time interval is free for scheduling.
        bUSYUNAVAILABLE, // Indicates that the time interval is busy and that the interval can not be scheduled.
        bUSYTENTATIVE, // Indicates that the time interval is busy because one or more events have been tentatively scheduled for that interval.
        Null; // added to help the parsers
        public static Slotstatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("BUSY".equals(codeString))
          return bUSY;
        if ("FREE".equals(codeString))
          return fREE;
        if ("BUSY-UNAVAILABLE".equals(codeString))
          return bUSYUNAVAILABLE;
        if ("BUSY-TENTATIVE".equals(codeString))
          return bUSYTENTATIVE;
        throw new Exception("Unknown Slotstatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case bUSY: return "BUSY";
            case fREE: return "FREE";
            case bUSYUNAVAILABLE: return "BUSY-UNAVAILABLE";
            case bUSYTENTATIVE: return "BUSY-TENTATIVE";
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
          return Slotstatus.bUSY;
        if ("FREE".equals(codeString))
          return Slotstatus.fREE;
        if ("BUSY-UNAVAILABLE".equals(codeString))
          return Slotstatus.bUSYUNAVAILABLE;
        if ("BUSY-TENTATIVE".equals(codeString))
          return Slotstatus.bUSYTENTATIVE;
        throw new Exception("Unknown Slotstatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Slotstatus.bUSY)
        return "BUSY";
      if (code == Slotstatus.fREE)
        return "FREE";
      if (code == Slotstatus.bUSYUNAVAILABLE)
        return "BUSY-UNAVAILABLE";
      if (code == Slotstatus.bUSYTENTATIVE)
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
    protected ResourceReference availability;

    /**
     * BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.
     */
    protected Enumeration<Slotstatus> freeBusyType;

    /**
     * Date/Time that the slot is to begin.
     */
    protected Instant start;

    /**
     * Date/Time that the slot is to conclude.
     */
    protected Instant end;

    /**
     * Comments on the slot to describe any extended information. Such as custom constraints on the slot.
     */
    protected String_ comment;

    /**
     * Who authored the slot.
     */
    protected ResourceReference author;

    /**
     * When this slot was created, or last revised.
     */
    protected DateTime authorDate;

    public Slot() {
      super();
    }

    public Slot(ResourceReference availability, Enumeration<Slotstatus> freeBusyType, Instant start, Instant end) {
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

    // syntactic sugar
    /**
     * @return {@link #identifier} (External Ids for this item.)
     */
    public Identifier addIdentifier() { 
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
    public ResourceReference getAvailability() { 
      return this.availability;
    }

    /**
     * @param value {@link #availability} (The availability resource that this slot defines an interval of status information.)
     */
    public Slot setAvailability(ResourceReference value) { 
      this.availability = value;
      return this;
    }

    /**
     * @return {@link #freeBusyType} (BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.)
     */
    public Enumeration<Slotstatus> getFreeBusyType() { 
      return this.freeBusyType;
    }

    /**
     * @param value {@link #freeBusyType} (BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.)
     */
    public Slot setFreeBusyType(Enumeration<Slotstatus> value) { 
      this.freeBusyType = value;
      return this;
    }

    /**
     * @return BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.
     */
    public Slotstatus getFreeBusyTypeSimple() { 
      return this.freeBusyType == null ? null : this.freeBusyType.getValue();
    }

    /**
     * @param value BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.
     */
    public Slot setFreeBusyTypeSimple(Slotstatus value) { 
        if (this.freeBusyType == null)
          this.freeBusyType = new Enumeration<Slotstatus>();
        this.freeBusyType.setValue(value);
      return this;
    }

    /**
     * @return {@link #start} (Date/Time that the slot is to begin.)
     */
    public Instant getStart() { 
      return this.start;
    }

    /**
     * @param value {@link #start} (Date/Time that the slot is to begin.)
     */
    public Slot setStart(Instant value) { 
      this.start = value;
      return this;
    }

    /**
     * @return Date/Time that the slot is to begin.
     */
    public DateAndTime getStartSimple() { 
      return this.start == null ? null : this.start.getValue();
    }

    /**
     * @param value Date/Time that the slot is to begin.
     */
    public Slot setStartSimple(DateAndTime value) { 
        if (this.start == null)
          this.start = new Instant();
        this.start.setValue(value);
      return this;
    }

    /**
     * @return {@link #end} (Date/Time that the slot is to conclude.)
     */
    public Instant getEnd() { 
      return this.end;
    }

    /**
     * @param value {@link #end} (Date/Time that the slot is to conclude.)
     */
    public Slot setEnd(Instant value) { 
      this.end = value;
      return this;
    }

    /**
     * @return Date/Time that the slot is to conclude.
     */
    public DateAndTime getEndSimple() { 
      return this.end == null ? null : this.end.getValue();
    }

    /**
     * @param value Date/Time that the slot is to conclude.
     */
    public Slot setEndSimple(DateAndTime value) { 
        if (this.end == null)
          this.end = new Instant();
        this.end.setValue(value);
      return this;
    }

    /**
     * @return {@link #comment} (Comments on the slot to describe any extended information. Such as custom constraints on the slot.)
     */
    public String_ getComment() { 
      return this.comment;
    }

    /**
     * @param value {@link #comment} (Comments on the slot to describe any extended information. Such as custom constraints on the slot.)
     */
    public Slot setComment(String_ value) { 
      this.comment = value;
      return this;
    }

    /**
     * @return Comments on the slot to describe any extended information. Such as custom constraints on the slot.
     */
    public String getCommentSimple() { 
      return this.comment == null ? null : this.comment.getValue();
    }

    /**
     * @param value Comments on the slot to describe any extended information. Such as custom constraints on the slot.
     */
    public Slot setCommentSimple(String value) { 
      if (value == null)
        this.comment = null;
      else {
        if (this.comment == null)
          this.comment = new String_();
        this.comment.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #author} (Who authored the slot.)
     */
    public ResourceReference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (Who authored the slot.)
     */
    public Slot setAuthor(ResourceReference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #authorDate} (When this slot was created, or last revised.)
     */
    public DateTime getAuthorDate() { 
      return this.authorDate;
    }

    /**
     * @param value {@link #authorDate} (When this slot was created, or last revised.)
     */
    public Slot setAuthorDate(DateTime value) { 
      this.authorDate = value;
      return this;
    }

    /**
     * @return When this slot was created, or last revised.
     */
    public DateAndTime getAuthorDateSimple() { 
      return this.authorDate == null ? null : this.authorDate.getValue();
    }

    /**
     * @param value When this slot was created, or last revised.
     */
    public Slot setAuthorDateSimple(DateAndTime value) { 
      if (value == null)
        this.authorDate = null;
      else {
        if (this.authorDate == null)
          this.authorDate = new DateTime();
        this.authorDate.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "External Ids for this item.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "The type of appointments that can be booked into this slot (ideally this would be an identifiable service - which is at a location, rather than the location itself). If provided then this overrides the value provided on the availability resource.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("availability", "Resource(Availability)", "The availability resource that this slot defines an interval of status information.", 0, java.lang.Integer.MAX_VALUE, availability));
        childrenList.add(new Property("freeBusyType", "code", "BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE.", 0, java.lang.Integer.MAX_VALUE, freeBusyType));
        childrenList.add(new Property("start", "instant", "Date/Time that the slot is to begin.", 0, java.lang.Integer.MAX_VALUE, start));
        childrenList.add(new Property("end", "instant", "Date/Time that the slot is to conclude.", 0, java.lang.Integer.MAX_VALUE, end));
        childrenList.add(new Property("comment", "string", "Comments on the slot to describe any extended information. Such as custom constraints on the slot.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("author", "Resource(Practitioner|Patient|RelatedPerson)", "Who authored the slot.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("authorDate", "dateTime", "When this slot was created, or last revised.", 0, java.lang.Integer.MAX_VALUE, authorDate));
      }

      public Slot copy() {
        Slot dst = new Slot();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.availability = availability == null ? null : availability.copy();
        dst.freeBusyType = freeBusyType == null ? null : freeBusyType.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.author = author == null ? null : author.copy();
        dst.authorDate = authorDate == null ? null : authorDate.copy();
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

