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
 * A supply - a  request for something, and provision of what is supplied.
 */
public class Supply extends DomainResource {

    public enum ValuesetSupplyStatus {
        REQUESTED, // Supply has been requested, but not dispensed.
        DISPENSED, // Supply is part of a pharmacy order and has been dispensed.
        RECEIVED, // Supply has been received by the requestor.
        FAILED, // The supply will not be completed because the supplier was unable or unwilling to supply the item.
        CANCELLED, // The orderer of the supply cancelled the request.
        NULL; // added to help the parsers
        public static ValuesetSupplyStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return REQUESTED;
        if ("dispensed".equals(codeString))
          return DISPENSED;
        if ("received".equals(codeString))
          return RECEIVED;
        if ("failed".equals(codeString))
          return FAILED;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        throw new Exception("Unknown ValuesetSupplyStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REQUESTED: return "requested";
            case DISPENSED: return "dispensed";
            case RECEIVED: return "received";
            case FAILED: return "failed";
            case CANCELLED: return "cancelled";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REQUESTED: return "Supply has been requested, but not dispensed.";
            case DISPENSED: return "Supply is part of a pharmacy order and has been dispensed.";
            case RECEIVED: return "Supply has been received by the requestor.";
            case FAILED: return "The supply will not be completed because the supplier was unable or unwilling to supply the item.";
            case CANCELLED: return "The orderer of the supply cancelled the request.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REQUESTED: return "Requested";
            case DISPENSED: return "Dispensed";
            case RECEIVED: return "Received";
            case FAILED: return "Failed";
            case CANCELLED: return "Cancelled";
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
          return ValuesetSupplyStatus.REQUESTED;
        if ("dispensed".equals(codeString))
          return ValuesetSupplyStatus.DISPENSED;
        if ("received".equals(codeString))
          return ValuesetSupplyStatus.RECEIVED;
        if ("failed".equals(codeString))
          return ValuesetSupplyStatus.FAILED;
        if ("cancelled".equals(codeString))
          return ValuesetSupplyStatus.CANCELLED;
        throw new Exception("Unknown ValuesetSupplyStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ValuesetSupplyStatus.REQUESTED)
        return "requested";
      if (code == ValuesetSupplyStatus.DISPENSED)
        return "dispensed";
      if (code == ValuesetSupplyStatus.RECEIVED)
        return "received";
      if (code == ValuesetSupplyStatus.FAILED)
        return "failed";
      if (code == ValuesetSupplyStatus.CANCELLED)
        return "cancelled";
      return "?";
      }
    }

    public enum ValuesetSupplyDispenseStatus {
        INPROGRESS, // Supply has been requested, but not dispensed.
        DISPENSED, // Supply is part of a pharmacy order and has been dispensed.
        ABANDONED, // Dispensing was not completed.
        NULL; // added to help the parsers
        public static ValuesetSupplyDispenseStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return INPROGRESS;
        if ("dispensed".equals(codeString))
          return DISPENSED;
        if ("abandoned".equals(codeString))
          return ABANDONED;
        throw new Exception("Unknown ValuesetSupplyDispenseStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case INPROGRESS: return "in progress";
            case DISPENSED: return "dispensed";
            case ABANDONED: return "abandoned";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case INPROGRESS: return "Supply has been requested, but not dispensed.";
            case DISPENSED: return "Supply is part of a pharmacy order and has been dispensed.";
            case ABANDONED: return "Dispensing was not completed.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case INPROGRESS: return "In Progress";
            case DISPENSED: return "Dispensed";
            case ABANDONED: return "Abandoned";
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
          return ValuesetSupplyDispenseStatus.INPROGRESS;
        if ("dispensed".equals(codeString))
          return ValuesetSupplyDispenseStatus.DISPENSED;
        if ("abandoned".equals(codeString))
          return ValuesetSupplyDispenseStatus.ABANDONED;
        throw new Exception("Unknown ValuesetSupplyDispenseStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ValuesetSupplyDispenseStatus.INPROGRESS)
        return "in progress";
      if (code == ValuesetSupplyDispenseStatus.DISPENSED)
        return "dispensed";
      if (code == ValuesetSupplyDispenseStatus.ABANDONED)
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
         * Identifies the medication or substance or device being dispensed. This is either a link to a resource representing the details of the item or a simple attribute carrying a code that identifies the item from a known list.
         */
        protected Reference suppliedItem;

        /**
         * The actual object that is the target of the reference (Identifies the medication or substance or device being dispensed. This is either a link to a resource representing the details of the item or a simple attribute carrying a code that identifies the item from a known list.)
         */
        protected Resource suppliedItemTarget;

        /**
         * The individual responsible for dispensing the medication, supplier or device.
         */
        protected Reference supplier;

        /**
         * The actual object that is the target of the reference (The individual responsible for dispensing the medication, supplier or device.)
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
        protected Reference destination;

        /**
         * The actual object that is the target of the reference (Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        protected Location destinationTarget;

        /**
         * Identifies the person who picked up the Supply.
         */
        protected List<Reference> receiver = new ArrayList<Reference>();
        /**
         * The actual objects that are the target of the reference (Identifies the person who picked up the Supply.)
         */
        protected List<Practitioner> receiverTarget = new ArrayList<Practitioner>();


        private static final long serialVersionUID = -476007340L;

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
         * @return {@link #status} (A code specifying the state of the dispense event.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public Enumeration<ValuesetSupplyDispenseStatus> getStatusElement() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (A code specifying the state of the dispense event.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public SupplyDispenseComponent setStatusElement(Enumeration<ValuesetSupplyDispenseStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return A code specifying the state of the dispense event.
         */
        public ValuesetSupplyDispenseStatus getStatus() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value A code specifying the state of the dispense event.
         */
        public SupplyDispenseComponent setStatus(ValuesetSupplyDispenseStatus value) { 
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
         * @return {@link #suppliedItem} (Identifies the medication or substance or device being dispensed. This is either a link to a resource representing the details of the item or a simple attribute carrying a code that identifies the item from a known list.)
         */
        public Reference getSuppliedItem() { 
          return this.suppliedItem;
        }

        /**
         * @param value {@link #suppliedItem} (Identifies the medication or substance or device being dispensed. This is either a link to a resource representing the details of the item or a simple attribute carrying a code that identifies the item from a known list.)
         */
        public SupplyDispenseComponent setSuppliedItem(Reference value) { 
          this.suppliedItem = value;
          return this;
        }

        /**
         * @return {@link #suppliedItem} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identifies the medication or substance or device being dispensed. This is either a link to a resource representing the details of the item or a simple attribute carrying a code that identifies the item from a known list.)
         */
        public Resource getSuppliedItemTarget() { 
          return this.suppliedItemTarget;
        }

        /**
         * @param value {@link #suppliedItem} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identifies the medication or substance or device being dispensed. This is either a link to a resource representing the details of the item or a simple attribute carrying a code that identifies the item from a known list.)
         */
        public SupplyDispenseComponent setSuppliedItemTarget(Resource value) { 
          this.suppliedItemTarget = value;
          return this;
        }

        /**
         * @return {@link #supplier} (The individual responsible for dispensing the medication, supplier or device.)
         */
        public Reference getSupplier() { 
          return this.supplier;
        }

        /**
         * @param value {@link #supplier} (The individual responsible for dispensing the medication, supplier or device.)
         */
        public SupplyDispenseComponent setSupplier(Reference value) { 
          this.supplier = value;
          return this;
        }

        /**
         * @return {@link #supplier} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The individual responsible for dispensing the medication, supplier or device.)
         */
        public Practitioner getSupplierTarget() { 
          return this.supplierTarget;
        }

        /**
         * @param value {@link #supplier} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The individual responsible for dispensing the medication, supplier or device.)
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
        public Reference getDestination() { 
          return this.destination;
        }

        /**
         * @param value {@link #destination} (Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        public SupplyDispenseComponent setDestination(Reference value) { 
          this.destination = value;
          return this;
        }

        /**
         * @return {@link #destination} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        public Location getDestinationTarget() { 
          return this.destinationTarget;
        }

        /**
         * @param value {@link #destination} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identification of the facility/location where the Supply was shipped to, as part of the dispense event.)
         */
        public SupplyDispenseComponent setDestinationTarget(Location value) { 
          this.destinationTarget = value;
          return this;
        }

        /**
         * @return {@link #receiver} (Identifies the person who picked up the Supply.)
         */
        public List<Reference> getReceiver() { 
          return this.receiver;
        }

        /**
         * @return {@link #receiver} (Identifies the person who picked up the Supply.)
         */
    // syntactic sugar
        public Reference addReceiver() { //3
          Reference t = new Reference();
          this.receiver.add(t);
          return t;
        }

        /**
         * @return {@link #receiver} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Identifies the person who picked up the Supply.)
         */
        public List<Practitioner> getReceiverTarget() { 
          return this.receiverTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #receiver} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. Identifies the person who picked up the Supply.)
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
          childrenList.add(new Property("suppliedItem", "Reference(Medication|Substance|Device)", "Identifies the medication or substance or device being dispensed. This is either a link to a resource representing the details of the item or a simple attribute carrying a code that identifies the item from a known list.", 0, java.lang.Integer.MAX_VALUE, suppliedItem));
          childrenList.add(new Property("supplier", "Reference(Practitioner)", "The individual responsible for dispensing the medication, supplier or device.", 0, java.lang.Integer.MAX_VALUE, supplier));
          childrenList.add(new Property("whenPrepared", "Period", "The time the dispense event occurred.", 0, java.lang.Integer.MAX_VALUE, whenPrepared));
          childrenList.add(new Property("whenHandedOver", "Period", "The time the dispensed item was sent or handed to the patient (or agent).", 0, java.lang.Integer.MAX_VALUE, whenHandedOver));
          childrenList.add(new Property("destination", "Reference(Location)", "Identification of the facility/location where the Supply was shipped to, as part of the dispense event.", 0, java.lang.Integer.MAX_VALUE, destination));
          childrenList.add(new Property("receiver", "Reference(Practitioner)", "Identifies the person who picked up the Supply.", 0, java.lang.Integer.MAX_VALUE, receiver));
        }

      public SupplyDispenseComponent copy() {
        SupplyDispenseComponent dst = new SupplyDispenseComponent();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.type = type == null ? null : type.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.suppliedItem = suppliedItem == null ? null : suppliedItem.copy();
        dst.supplier = supplier == null ? null : supplier.copy();
        dst.whenPrepared = whenPrepared == null ? null : whenPrepared.copy();
        dst.whenHandedOver = whenHandedOver == null ? null : whenHandedOver.copy();
        dst.destination = destination == null ? null : destination.copy();
        dst.receiver = new ArrayList<Reference>();
        for (Reference i : receiver)
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
    protected Reference orderedItem;

    /**
     * The actual object that is the target of the reference (The item that is requested to be supplied.)
     */
    protected Resource orderedItemTarget;

    /**
     * A link to a resource representing the person whom the ordered item is for.
     */
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (A link to a resource representing the person whom the ordered item is for.)
     */
    protected Patient patientTarget;

    /**
     * Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.
     */
    protected List<SupplyDispenseComponent> dispense = new ArrayList<SupplyDispenseComponent>();

    private static final long serialVersionUID = 2134953033L;

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
     * @return {@link #status} (Status of the supply request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ValuesetSupplyStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Status of the supply request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Supply setStatusElement(Enumeration<ValuesetSupplyStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Status of the supply request.
     */
    public ValuesetSupplyStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Status of the supply request.
     */
    public Supply setStatus(ValuesetSupplyStatus value) { 
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
    public Reference getOrderedItem() { 
      return this.orderedItem;
    }

    /**
     * @param value {@link #orderedItem} (The item that is requested to be supplied.)
     */
    public Supply setOrderedItem(Reference value) { 
      this.orderedItem = value;
      return this;
    }

    /**
     * @return {@link #orderedItem} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The item that is requested to be supplied.)
     */
    public Resource getOrderedItemTarget() { 
      return this.orderedItemTarget;
    }

    /**
     * @param value {@link #orderedItem} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The item that is requested to be supplied.)
     */
    public Supply setOrderedItemTarget(Resource value) { 
      this.orderedItemTarget = value;
      return this;
    }

    /**
     * @return {@link #patient} (A link to a resource representing the person whom the ordered item is for.)
     */
    public Reference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (A link to a resource representing the person whom the ordered item is for.)
     */
    public Supply setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A link to a resource representing the person whom the ordered item is for.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A link to a resource representing the person whom the ordered item is for.)
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

    /**
     * @return {@link #dispense} (Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.)
     */
    // syntactic sugar
    public SupplyDispenseComponent addDispense() { //3
      SupplyDispenseComponent t = new SupplyDispenseComponent();
      this.dispense.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("kind", "CodeableConcept", "Category of supply, e.g.  central, non-stock, etc. This is used to support work flows associated with the supply process.", 0, java.lang.Integer.MAX_VALUE, kind));
        childrenList.add(new Property("identifier", "Identifier", "Unique identifier for this supply request.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "Status of the supply request.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("orderedItem", "Reference(Medication|Substance|Device)", "The item that is requested to be supplied.", 0, java.lang.Integer.MAX_VALUE, orderedItem));
        childrenList.add(new Property("patient", "Reference(Patient)", "A link to a resource representing the person whom the ordered item is for.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("dispense", "", "Indicates the details of the dispense event such as the days supply and quantity of a supply dispensed.", 0, java.lang.Integer.MAX_VALUE, dispense));
      }

      public Supply copy() {
        Supply dst = new Supply();
        copyValues(dst);
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

