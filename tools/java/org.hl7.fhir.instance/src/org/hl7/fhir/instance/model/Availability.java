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

// Generated on Thu, Jan 23, 2014 11:33-0600 for FHIR v0.12

import java.util.*;

/**
 * (informative) A container for slot(s) of time that may be available for booking appointments.
 */
public class Availability extends Resource {

    /**
     * External Ids for this item.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The type of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself) - change to CodeableConcept.
     */
    protected CodeableConcept type;

    /**
     * The type of resource this availability resource is providing availability information for.
     */
    protected ResourceReference individual;

    /**
     * The period of time that the slots that are attached to this availability resource cover (even if none exist).
     */
    protected Period period;

    /**
     * Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.
     */
    protected String_ comment;

    /**
     * Who authored the availability.
     */
    protected ResourceReference author;

    /**
     * When this availability was created, or last revised.
     */
    protected DateTime authorDate;

    public Availability() {
      super();
    }

    public Availability(ResourceReference individual) {
      super();
      this.individual = individual;
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
     * @return {@link #type} (The type of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself) - change to CodeableConcept.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The type of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself) - change to CodeableConcept.)
     */
    public Availability setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #individual} (The type of resource this availability resource is providing availability information for.)
     */
    public ResourceReference getIndividual() { 
      return this.individual;
    }

    /**
     * @param value {@link #individual} (The type of resource this availability resource is providing availability information for.)
     */
    public Availability setIndividual(ResourceReference value) { 
      this.individual = value;
      return this;
    }

    /**
     * @return {@link #period} (The period of time that the slots that are attached to this availability resource cover (even if none exist).)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (The period of time that the slots that are attached to this availability resource cover (even if none exist).)
     */
    public Availability setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #comment} (Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.)
     */
    public String_ getComment() { 
      return this.comment;
    }

    /**
     * @param value {@link #comment} (Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.)
     */
    public Availability setComment(String_ value) { 
      this.comment = value;
      return this;
    }

    /**
     * @return Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.
     */
    public String getCommentSimple() { 
      return this.comment == null ? null : this.comment.getValue();
    }

    /**
     * @param value Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.
     */
    public Availability setCommentSimple(String value) { 
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
     * @return {@link #author} (Who authored the availability.)
     */
    public ResourceReference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (Who authored the availability.)
     */
    public Availability setAuthor(ResourceReference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #authorDate} (When this availability was created, or last revised.)
     */
    public DateTime getAuthorDate() { 
      return this.authorDate;
    }

    /**
     * @param value {@link #authorDate} (When this availability was created, or last revised.)
     */
    public Availability setAuthorDate(DateTime value) { 
      this.authorDate = value;
      return this;
    }

    /**
     * @return When this availability was created, or last revised.
     */
    public DateAndTime getAuthorDateSimple() { 
      return this.authorDate == null ? null : this.authorDate.getValue();
    }

    /**
     * @param value When this availability was created, or last revised.
     */
    public Availability setAuthorDateSimple(DateAndTime value) { 
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
        childrenList.add(new Property("type", "CodeableConcept", "The type of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself) - change to CodeableConcept.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("individual", "Resource(Location|Practitioner|Device|Patient|RelatedPerson)", "The type of resource this availability resource is providing availability information for.", 0, java.lang.Integer.MAX_VALUE, individual));
        childrenList.add(new Property("period", "Period", "The period of time that the slots that are attached to this availability resource cover (even if none exist).", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("comment", "string", "Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("author", "Resource(Practitioner|Patient|RelatedPerson)", "Who authored the availability.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("authorDate", "dateTime", "When this availability was created, or last revised.", 0, java.lang.Integer.MAX_VALUE, authorDate));
      }

      public Availability copy() {
        Availability dst = new Availability();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.individual = individual == null ? null : individual.copy();
        dst.period = period == null ? null : period.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.author = author == null ? null : author.copy();
        dst.authorDate = authorDate == null ? null : authorDate.copy();
        return dst;
      }

      protected Availability typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Availability;
   }


}

