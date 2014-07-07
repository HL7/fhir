package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2014, HL7, Inc.
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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

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
     * The type(s) of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself). (This may be over-ridden by a slot itself).
     */
    protected List<CodeableConcept> type = new ArrayList<CodeableConcept>();

    /**
     * The resource this availability resource is providing availability information for. These are expected to usually be one of Location, Practitioner, Device, Patient or RelatedPerson.
     */
    protected ResourceReference individual;

    /**
     * The actual object that is the target of the reference (The resource this availability resource is providing availability information for. These are expected to usually be one of Location, Practitioner, Device, Patient or RelatedPerson.)
     */
    protected Resource individualTarget;

    /**
     * The period of time that the slots that are attached to this availability resource cover (even if none exist). These  cover the amount of time that an organization's planning horizon; the interval for which they are currently accepting appointments. This does not define a "template" for planning outside these dates.
     */
    protected Period planningHorizon;

    /**
     * Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.
     */
    protected String_ comment;

    /**
     * When this availability was created, or last revised.
     */
    protected DateTime lastModified;

    private static final long serialVersionUID = -1764152897L;

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
     * @return {@link #type} (The type(s) of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself). (This may be over-ridden by a slot itself).)
     */
    public List<CodeableConcept> getType() { 
      return this.type;
    }

    // syntactic sugar
    /**
     * @return {@link #type} (The type(s) of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself). (This may be over-ridden by a slot itself).)
     */
    public CodeableConcept addType() { 
      CodeableConcept t = new CodeableConcept();
      this.type.add(t);
      return t;
    }

    /**
     * @return {@link #individual} (The resource this availability resource is providing availability information for. These are expected to usually be one of Location, Practitioner, Device, Patient or RelatedPerson.)
     */
    public ResourceReference getIndividual() { 
      return this.individual;
    }

    /**
     * @param value {@link #individual} (The resource this availability resource is providing availability information for. These are expected to usually be one of Location, Practitioner, Device, Patient or RelatedPerson.)
     */
    public Availability setIndividual(ResourceReference value) { 
      this.individual = value;
      return this;
    }

    /**
     * @return {@link #individual} (The actual object that is the target of the reference. The resource this availability resource is providing availability information for. These are expected to usually be one of Location, Practitioner, Device, Patient or RelatedPerson.)
     */
    public Resource getIndividualTarget() { 
      return this.individualTarget;
    }

    /**
     * @param value {@link #individual} (The actual object that is the target of the reference. The resource this availability resource is providing availability information for. These are expected to usually be one of Location, Practitioner, Device, Patient or RelatedPerson.)
     */
    public Availability setIndividualTarget(Resource value) { 
      this.individualTarget = value;
      return this;
    }

    /**
     * @return {@link #planningHorizon} (The period of time that the slots that are attached to this availability resource cover (even if none exist). These  cover the amount of time that an organization's planning horizon; the interval for which they are currently accepting appointments. This does not define a "template" for planning outside these dates.)
     */
    public Period getPlanningHorizon() { 
      return this.planningHorizon;
    }

    /**
     * @param value {@link #planningHorizon} (The period of time that the slots that are attached to this availability resource cover (even if none exist). These  cover the amount of time that an organization's planning horizon; the interval for which they are currently accepting appointments. This does not define a "template" for planning outside these dates.)
     */
    public Availability setPlanningHorizon(Period value) { 
      this.planningHorizon = value;
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
     * @return {@link #lastModified} (When this availability was created, or last revised.)
     */
    public DateTime getLastModified() { 
      return this.lastModified;
    }

    /**
     * @param value {@link #lastModified} (When this availability was created, or last revised.)
     */
    public Availability setLastModified(DateTime value) { 
      this.lastModified = value;
      return this;
    }

    /**
     * @return When this availability was created, or last revised.
     */
    public DateAndTime getLastModifiedSimple() { 
      return this.lastModified == null ? null : this.lastModified.getValue();
    }

    /**
     * @param value When this availability was created, or last revised.
     */
    public Availability setLastModifiedSimple(DateAndTime value) { 
      if (value == null)
        this.lastModified = null;
      else {
        if (this.lastModified == null)
          this.lastModified = new DateTime();
        this.lastModified.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "External Ids for this item.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "The type(s) of appointments that can be booked into slots attached to this availability resource (ideally this would be an identifiable service - which is at a location, rather than the location itself). (This may be over-ridden by a slot itself).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("individual", "Resource(Any)", "The resource this availability resource is providing availability information for. These are expected to usually be one of Location, Practitioner, Device, Patient or RelatedPerson.", 0, java.lang.Integer.MAX_VALUE, individual));
        childrenList.add(new Property("planningHorizon", "Period", "The period of time that the slots that are attached to this availability resource cover (even if none exist). These  cover the amount of time that an organization's planning horizon; the interval for which they are currently accepting appointments. This does not define a 'template' for planning outside these dates.", 0, java.lang.Integer.MAX_VALUE, planningHorizon));
        childrenList.add(new Property("comment", "string", "Comments on the availability to describe any extended information. Such as custom constraints on the slot(s) that may be associated.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("lastModified", "dateTime", "When this availability was created, or last revised.", 0, java.lang.Integer.MAX_VALUE, lastModified));
      }

      public Availability copy() {
        Availability dst = new Availability();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.type = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : type)
          dst.type.add(i.copy());
        dst.individual = individual == null ? null : individual.copy();
        dst.planningHorizon = planningHorizon == null ? null : planningHorizon.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.lastModified = lastModified == null ? null : lastModified.copy();
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

