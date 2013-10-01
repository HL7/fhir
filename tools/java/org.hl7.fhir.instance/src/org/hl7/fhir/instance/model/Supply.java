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

// Generated on Tue, Oct 1, 2013 21:45+1000 for FHIR v0.11

import java.util.*;

/**
 * A supply -  request and provision.
 */
public class Supply extends Resource {

    public class SupplyDispenseComponent extends Element {
        /**
         * Identifier assigned by the dispensing facility.   This is an identifier assigned outside FHIR.
         */
        protected Identifier identifier;

        /**
         * A code specifying the state of the dispense event.
         */
        protected Code status;

        /**
         * Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.
         */
        protected CodeableConcept type;

        /**
         * The amount of supply that has been dispensed. Includes unit of measure.
         */
        protected Quantity quantity;

        /**
         * Identifies the medication or substance being dispensed. This is either a link to a resource representing the details of the medication or substance or a simple attribute carrying a code that identifies the medication from a known list of medications.
         */
        protected ResourceReference suppliedItem;

        /**
         * The individual reponsible for dispensing the medication.
         */
        protected ResourceReference supplier;

        /**
         * The time the dispense event occurred.
         */
        protected Period whenPrepared;

        /**
         * The time the dispense event occurred.
         */
        protected Period whenHandedOver;

        /**
         * Identification of the facility/location where the Supply was shipped to, as part of the dispense event.
         */
        protected ResourceReference destination;

        /**
         * Identifies the person who picked up the Supply.
         */
        protected List<ResourceReference> receiver = new ArrayList<ResourceReference>();

        public Identifier getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Identifier value) { 
          this.identifier = value;
        }

        public Code getStatus() { 
          return this.status;
        }

        public void setStatus(Code value) { 
          this.status = value;
        }

        public String getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        public void setStatusSimple(String value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Code();
            this.status.setValue(value);
          }
        }

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public void setQuantity(Quantity value) { 
          this.quantity = value;
        }

        public ResourceReference getSuppliedItem() { 
          return this.suppliedItem;
        }

        public void setSuppliedItem(ResourceReference value) { 
          this.suppliedItem = value;
        }

        public ResourceReference getSupplier() { 
          return this.supplier;
        }

        public void setSupplier(ResourceReference value) { 
          this.supplier = value;
        }

        public Period getWhenPrepared() { 
          return this.whenPrepared;
        }

        public void setWhenPrepared(Period value) { 
          this.whenPrepared = value;
        }

        public Period getWhenHandedOver() { 
          return this.whenHandedOver;
        }

        public void setWhenHandedOver(Period value) { 
          this.whenHandedOver = value;
        }

        public ResourceReference getDestination() { 
          return this.destination;
        }

        public void setDestination(ResourceReference value) { 
          this.destination = value;
        }

        public List<ResourceReference> getReceiver() { 
          return this.receiver;
        }

    // syntactic sugar
        public ResourceReference addReceiver() { 
          ResourceReference t = new ResourceReference();
          this.receiver.add(t);
          return t;
        }

      public SupplyDispenseComponent copy(Supply e) {
        SupplyDispenseComponent dst = e.new SupplyDispenseComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.type = type == null ? null : type.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.suppliedItem = suppliedItem == null ? null : suppliedItem.copy();
        dst.supplier = supplier == null ? null : supplier.copy();
        dst.whenPrepared = whenPrepared == null ? null : whenPrepared.copy();
        dst.whenHandedOver = whenHandedOver == null ? null : whenHandedOver.copy();
        dst.destination = destination == null ? null : destination.copy();
        dst.receiver = new ArrayList<ResourceReference>();
        for (ResourceReference i : receiver)
          dst.receiver.add(i.copy());
        return dst;
      }

  }

    /**
     * Category of supply.
     */
    protected CodeableConcept name;

    /**
     * Unique identifier for this kind of supply.
     */
    protected Identifier identifier;

    /**
     * Status of the supply.
     */
    protected Code status;

    /**
     * The item which was ordered.
     */
    protected ResourceReference orderedItem;

    /**
     * A link to a resource representing the person to whom the medication will be given.
     */
    protected ResourceReference patient;

    /**
     * Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.
     */
    protected List<SupplyDispenseComponent> dispense = new ArrayList<SupplyDispenseComponent>();

    public CodeableConcept getName() { 
      return this.name;
    }

    public void setName(CodeableConcept value) { 
      this.name = value;
    }

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public Code getStatus() { 
      return this.status;
    }

    public void setStatus(Code value) { 
      this.status = value;
    }

    public String getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(String value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Code();
        this.status.setValue(value);
      }
    }

    public ResourceReference getOrderedItem() { 
      return this.orderedItem;
    }

    public void setOrderedItem(ResourceReference value) { 
      this.orderedItem = value;
    }

    public ResourceReference getPatient() { 
      return this.patient;
    }

    public void setPatient(ResourceReference value) { 
      this.patient = value;
    }

    public List<SupplyDispenseComponent> getDispense() { 
      return this.dispense;
    }

    // syntactic sugar
    public SupplyDispenseComponent addDispense() { 
      SupplyDispenseComponent t = new SupplyDispenseComponent();
      this.dispense.add(t);
      return t;
    }

      public Supply copy() {
        Supply dst = new Supply();
        dst.name = name == null ? null : name.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.orderedItem = orderedItem == null ? null : orderedItem.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.dispense = new ArrayList<SupplyDispenseComponent>();
        for (SupplyDispenseComponent i : dispense)
          dst.dispense.add(i.copy(dst));
        return dst;
      }

      protected Supply typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Supply;
   }


}

