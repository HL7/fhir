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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

import java.util.*;

/**
 * A supply - a  request for something, and provision of what is supplied.
 */
public class Supply extends Resource {

    public enum ValuesetSupplyStatus {
        requested, // Supply has been requested, but not dispensed.
        dispensed, // Supply is part of a pharmacy order and has been dispensed.
        received, // Supply has been received by the requestor.
        failed, // The supply will not be completed because the supplier was unable or unwilling to supply the item.
        cancelled, // The orderer of the supply cancelled the request.
        Null; // added to help the parsers
        public static ValuesetSupplyStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return requested;
        if ("dispensed".equals(codeString))
          return dispensed;
        if ("received".equals(codeString))
          return received;
        if ("failed".equals(codeString))
          return failed;
        if ("cancelled".equals(codeString))
          return cancelled;
        throw new Exception("Unknown ValuesetSupplyStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case requested: return "requested";
            case dispensed: return "dispensed";
            case received: return "received";
            case failed: return "failed";
            case cancelled: return "cancelled";
            default: return "?";
          }
        }
    }

  public static class ValuesetSupplyStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return ValuesetSupplyStatus.requested;
        if ("dispensed".equals(codeString))
          return ValuesetSupplyStatus.dispensed;
        if ("received".equals(codeString))
          return ValuesetSupplyStatus.received;
        if ("failed".equals(codeString))
          return ValuesetSupplyStatus.failed;
        if ("cancelled".equals(codeString))
          return ValuesetSupplyStatus.cancelled;
        throw new Exception("Unknown ValuesetSupplyStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ValuesetSupplyStatus.requested)
        return "requested";
      if (code == ValuesetSupplyStatus.dispensed)
        return "dispensed";
      if (code == ValuesetSupplyStatus.received)
        return "received";
      if (code == ValuesetSupplyStatus.failed)
        return "failed";
      if (code == ValuesetSupplyStatus.cancelled)
        return "cancelled";
      return "?";
      }
    }

    public enum ValuesetSupplyDispenseStatus {
        inProgress, // Supply has been requested, but not dispensed.
        dispensed, // Supply is part of a pharmacy order and has been dispensed.
        abandoned, // Dispensing was not completed.
        Null; // added to help the parsers
        public static ValuesetSupplyDispenseStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return inProgress;
        if ("dispensed".equals(codeString))
          return dispensed;
        if ("abandoned".equals(codeString))
          return abandoned;
        throw new Exception("Unknown ValuesetSupplyDispenseStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case inProgress: return "in progress";
            case dispensed: return "dispensed";
            case abandoned: return "abandoned";
            default: return "?";
          }
        }
    }

  public static class ValuesetSupplyDispenseStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return ValuesetSupplyDispenseStatus.inProgress;
        if ("dispensed".equals(codeString))
          return ValuesetSupplyDispenseStatus.dispensed;
        if ("abandoned".equals(codeString))
          return ValuesetSupplyDispenseStatus.abandoned;
        throw new Exception("Unknown ValuesetSupplyDispenseStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ValuesetSupplyDispenseStatus.inProgress)
        return "in progress";
      if (code == ValuesetSupplyDispenseStatus.dispensed)
        return "dispensed";
      if (code == ValuesetSupplyDispenseStatus.abandoned)
        return "abandoned";
      return "?";
      }
    }

    public static class SupplyDispenseComponent extends BackboneElement {
        /**
         * Identifier assigned by the dispensing facility when the dispense occurs.
         */
        protected Identifier identifier;

        /**
         * A code specifying the state of the dispense event.
         */
        protected Enumeration<ValuesetSupplyDispenseStatus> status;

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
         * The actual object that is the target of the reference (Identifies the medication or substance being dispensed. This is either a link to a resource representing the details of the medication or substance or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        protected Resource suppliedItemTarget;

        /**
         * The individual responsible for dispensing the medication.
         */
        protected ResourceReference supplier;

        /**
         * The actual object that is the target of the reference (The individual responsible for dispensing the medication.)
         */
        protected Practitioner supplierTarget;

        /**
         * The time the dispense event occurred.
         */
        protected Period whenPrepared;

        /**
         * The time the dispensed item was sent or handed to the patient (or agent).
         */
        protected Period whenHandedOver;

        /**
         * Identification of the facility/location where the Supply was shipped to, as part of the dispense event.
         */
        protected ResourceReference destination;

        /**
         * The actual object that is the target of the reference (Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        protected Location destinationTarget;

        /**
         * Identifies the person who picked up the Supply.
         */
        protected List<ResourceReference> receiver = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Identifies the person who picked up the Supply.)
         */
        protected List<Practitioner> receiverTarget = new ArrayList<Practitioner>();


        private static final long serialVersionUID = 1248640970L;

      public SupplyDispenseComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Identifier assigned by the dispensing facility when the dispense occurs.)
         */
        public Identifier getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (Identifier assigned by the dispensing facility when the dispense occurs.)
         */
        public SupplyDispenseComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #status} (A code specifying the state of the dispense event.)
         */
        public Enumeration<ValuesetSupplyDispenseStatus> getStatus() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (A code specifying the state of the dispense event.)
         */
        public SupplyDispenseComponent setStatus(Enumeration<ValuesetSupplyDispenseStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return A code specifying the state of the dispense event.
         */
        public ValuesetSupplyDispenseStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value A code specifying the state of the dispense event.
         */
        public SupplyDispenseComponent setStatusSimple(ValuesetSupplyDispenseStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<ValuesetSupplyDispenseStatus>();
            this.status.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.)
         */
        public SupplyDispenseComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The amount of supply that has been dispensed. Includes unit of measure.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount of supply that has been dispensed. Includes unit of measure.)
         */
        public SupplyDispenseComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #suppliedItem} (Identifies the medication or substance being dispensed. This is either a link to a resource representing the details of the medication or substance or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public ResourceReference getSuppliedItem() { 
          return this.suppliedItem;
        }

        /**
         * @param value {@link #suppliedItem} (Identifies the medication or substance being dispensed. This is either a link to a resource representing the details of the medication or substance or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public SupplyDispenseComponent setSuppliedItem(ResourceReference value) { 
          this.suppliedItem = value;
          return this;
        }

        /**
         * @return {@link #suppliedItem} (The actual object that is the target of the reference. Identifies the medication or substance being dispensed. This is either a link to a resource representing the details of the medication or substance or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public Resource getSuppliedItemTarget() { 
          return this.suppliedItemTarget;
        }

        /**
         * @param value {@link #suppliedItem} (The actual object that is the target of the reference. Identifies the medication or substance being dispensed. This is either a link to a resource representing the details of the medication or substance or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public SupplyDispenseComponent setSuppliedItemTarget(Resource value) { 
          this.suppliedItemTarget = value;
          return this;
        }

        /**
         * @return {@link #supplier} (The individual responsible for dispensing the medication.)
         */
        public ResourceReference getSupplier() { 
          return this.supplier;
        }

        /**
         * @param value {@link #supplier} (The individual responsible for dispensing the medication.)
         */
        public SupplyDispenseComponent setSupplier(ResourceReference value) { 
          this.supplier = value;
          return this;
        }

        /**
         * @return {@link #supplier} (The actual object that is the target of the reference. The individual responsible for dispensing the medication.)
         */
        public Practitioner getSupplierTarget() { 
          return this.supplierTarget;
        }

        /**
         * @param value {@link #supplier} (The actual object that is the target of the reference. The individual responsible for dispensing the medication.)
         */
        public SupplyDispenseComponent setSupplierTarget(Practitioner value) { 
          this.supplierTarget = value;
          return this;
        }

        /**
         * @return {@link #whenPrepared} (The time the dispense event occurred.)
         */
        public Period getWhenPrepared() { 
          return this.whenPrepared;
        }

        /**
         * @param value {@link #whenPrepared} (The time the dispense event occurred.)
         */
        public SupplyDispenseComponent setWhenPrepared(Period value) { 
          this.whenPrepared = value;
          return this;
        }

        /**
         * @return {@link #whenHandedOver} (The time the dispensed item was sent or handed to the patient (or agent).)
         */
        public Period getWhenHandedOver() { 
          return this.whenHandedOver;
        }

        /**
         * @param value {@link #whenHandedOver} (The time the dispensed item was sent or handed to the patient (or agent).)
         */
        public SupplyDispenseComponent setWhenHandedOver(Period value) { 
          this.whenHandedOver = value;
          return this;
        }

        /**
         * @return {@link #destination} (Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        public ResourceReference getDestination() { 
          return this.destination;
        }

        /**
         * @param value {@link #destination} (Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        public SupplyDispenseComponent setDestination(ResourceReference value) { 
          this.destination = value;
          return this;
        }

        /**
         * @return {@link #destination} (The actual object that is the target of the reference. Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        public Location getDestinationTarget() { 
          return this.destinationTarget;
        }

        /**
         * @param value {@link #destination} (The actual object that is the target of the reference. Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        public SupplyDispenseComponent setDestinationTarget(Location value) { 
          this.destinationTarget = value;
          return this;
        }

        /**
         * @return {@link #receiver} (Identifies the person who picked up the Supply.)
         */
        public List<ResourceReference> getReceiver() { 
          return this.receiver;
        }

    // syntactic sugar
        /**
         * @return {@link #receiver} (Identifies the person who picked up the Supply.)
         */
        public ResourceReference addReceiver() { 
          ResourceReference t = new ResourceReference();
          this.receiver.add(t);
          return t;
        }

        /**
         * @return {@link #receiver} (The actual objects that are the target of the reference. Identifies the person who picked up the Supply.)
         */
        public List<Practitioner> getReceiverTarget() { 
          return this.receiverTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #receiver} (Add an actual object that is the target of the reference. Identifies the person who picked up the Supply.)
         */
        public Practitioner addReceiverTarget() { 
          Practitioner r = new Practitioner();
          this.receiverTarget.add(r);
          return r;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Identifier assigned by the dispensing facility when the dispense occurs.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("status", "code", "A code specifying the state of the dispense event.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("type", "CodeableConcept", "Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("quantity", "Quantity", "The amount of supply that has been dispensed. Includes unit of measure.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("suppliedItem", "Resource(Medication|Substance|Device)", "Identifies the medication or substance being dispensed. This is either a link to a resource representing the details of the medication or substance or a simple attribute carrying a code that identifies the medication from a known list of medications.", 0, java.lang.Integer.MAX_VALUE, suppliedItem));
          childrenList.add(new Property("supplier", "Resource(Practitioner)", "The individual responsible for dispensing the medication.", 0, java.lang.Integer.MAX_VALUE, supplier));
          childrenList.add(new Property("whenPrepared", "Period", "The time the dispense event occurred.", 0, java.lang.Integer.MAX_VALUE, whenPrepared));
          childrenList.add(new Property("whenHandedOver", "Period", "The time the dispensed item was sent or handed to the patient (or agent).", 0, java.lang.Integer.MAX_VALUE, whenHandedOver));
          childrenList.add(new Property("destination", "Resource(Location)", "Identification of the facility/location where the Supply was shipped to, as part of the dispense event.", 0, java.lang.Integer.MAX_VALUE, destination));
          childrenList.add(new Property("receiver", "Resource(Practitioner)", "Identifies the person who picked up the Supply.", 0, java.lang.Integer.MAX_VALUE, receiver));
        }

      public SupplyDispenseComponent copy() {
        SupplyDispenseComponent dst = new SupplyDispenseComponent();
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
     * Category of supply, e.g.  central, non-stock, etc. This is used to support work flows associated with the supply process.
     */
    protected CodeableConcept kind;

    /**
     * Unique identifier for this supply request.
     */
    protected Identifier identifier;

    /**
     * Status of the supply request.
     */
    protected Enumeration<ValuesetSupplyStatus> status;

    /**
     * The item that is requested to be supplied.
     */
    protected ResourceReference orderedItem;

    /**
     * The actual object that is the target of the reference (The item that is requested to be supplied.)
     */
    protected Resource orderedItemTarget;

    /**
     * A link to a resource representing the person whom the ordered item is for.
     */
    protected ResourceReference patient;

    /**
     * The actual object that is the target of the reference (A link to a resource representing the person whom the ordered item is for.)
     */
    protected Patient patientTarget;

    /**
     * Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.
     */
    protected List<SupplyDispenseComponent> dispense = new ArrayList<SupplyDispenseComponent>();

    private static final long serialVersionUID = 487202825L;

    public Supply() {
      super();
    }

    /**
     * @return {@link #kind} (Category of supply, e.g.  central, non-stock, etc. This is used to support work flows associated with the supply process.)
     */
    public CodeableConcept getKind() { 
      return this.kind;
    }

    /**
     * @param value {@link #kind} (Category of supply, e.g.  central, non-stock, etc. This is used to support work flows associated with the supply process.)
     */
    public Supply setKind(CodeableConcept value) { 
      this.kind = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Unique identifier for this supply request.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Unique identifier for this supply request.)
     */
    public Supply setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #status} (Status of the supply request.)
     */
    public Enumeration<ValuesetSupplyStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Status of the supply request.)
     */
    public Supply setStatus(Enumeration<ValuesetSupplyStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Status of the supply request.
     */
    public ValuesetSupplyStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Status of the supply request.
     */
    public Supply setStatusSimple(ValuesetSupplyStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<ValuesetSupplyStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #orderedItem} (The item that is requested to be supplied.)
     */
    public ResourceReference getOrderedItem() { 
      return this.orderedItem;
    }

    /**
     * @param value {@link #orderedItem} (The item that is requested to be supplied.)
     */
    public Supply setOrderedItem(ResourceReference value) { 
      this.orderedItem = value;
      return this;
    }

    /**
     * @return {@link #orderedItem} (The actual object that is the target of the reference. The item that is requested to be supplied.)
     */
    public Resource getOrderedItemTarget() { 
      return this.orderedItemTarget;
    }

    /**
     * @param value {@link #orderedItem} (The actual object that is the target of the reference. The item that is requested to be supplied.)
     */
    public Supply setOrderedItemTarget(Resource value) { 
      this.orderedItemTarget = value;
      return this;
    }

    /**
     * @return {@link #patient} (A link to a resource representing the person whom the ordered item is for.)
     */
    public ResourceReference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (A link to a resource representing the person whom the ordered item is for.)
     */
    public Supply setPatient(ResourceReference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} (The actual object that is the target of the reference. A link to a resource representing the person whom the ordered item is for.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} (The actual object that is the target of the reference. A link to a resource representing the person whom the ordered item is for.)
     */
    public Supply setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #dispense} (Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.)
     */
    public List<SupplyDispenseComponent> getDispense() { 
      return this.dispense;
    }

    // syntactic sugar
    /**
     * @return {@link #dispense} (Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.)
     */
    public SupplyDispenseComponent addDispense() { 
      SupplyDispenseComponent t = new SupplyDispenseComponent();
      this.dispense.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("kind", "CodeableConcept", "Category of supply, e.g.  central, non-stock, etc. This is used to support work flows associated with the supply process.", 0, java.lang.Integer.MAX_VALUE, kind));
        childrenList.add(new Property("identifier", "Identifier", "Unique identifier for this supply request.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "Status of the supply request.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("orderedItem", "Resource(Medication|Substance|Device)", "The item that is requested to be supplied.", 0, java.lang.Integer.MAX_VALUE, orderedItem));
        childrenList.add(new Property("patient", "Resource(Patient)", "A link to a resource representing the person whom the ordered item is for.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("dispense", "", "Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.", 0, java.lang.Integer.MAX_VALUE, dispense));
      }

      public Supply copy() {
        Supply dst = new Supply();
        dst.kind = kind == null ? null : kind.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.orderedItem = orderedItem == null ? null : orderedItem.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.dispense = new ArrayList<SupplyDispenseComponent>();
        for (SupplyDispenseComponent i : dispense)
          dst.dispense.add(i.copy());
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

