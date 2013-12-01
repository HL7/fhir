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
 * Sample for analysis.
 */
public class Specimen extends Resource {

    public enum HierarchicalRelationshipType {
        parent, // The target resource is the parent of the focal specimen resource.
        child, // The target resource is the child of the focal specimen resource.
        Null; // added to help the parsers
        public static HierarchicalRelationshipType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("parent".equals(codeString))
          return parent;
        if ("child".equals(codeString))
          return child;
        throw new Exception("Unknown HierarchicalRelationshipType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case parent: return "parent";
            case child: return "child";
            default: return "?";
          }
        }
    }

  public static class HierarchicalRelationshipTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("parent".equals(codeString))
          return HierarchicalRelationshipType.parent;
        if ("child".equals(codeString))
          return HierarchicalRelationshipType.child;
        throw new Exception("Unknown HierarchicalRelationshipType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == HierarchicalRelationshipType.parent)
        return "parent";
      if (code == HierarchicalRelationshipType.child)
        return "child";
      return "?";
      }
    }

    public static class SpecimenSourceComponent extends BackboneElement {
        /**
         * Whether this relationship is to a parent or to a child.
         */
        protected Enumeration<HierarchicalRelationshipType> relationship;

        /**
         * The specimen resource that is the target of this relationship.
         */
        protected List<ResourceReference> target = new ArrayList<ResourceReference>();

      public SpecimenSourceComponent() {
        super();
      }

      public SpecimenSourceComponent(Enumeration<HierarchicalRelationshipType> relationship) {
        super();
        this.relationship = relationship;
      }

        /**
         * @return {@link #relationship} (Whether this relationship is to a parent or to a child.)
         */
        public Enumeration<HierarchicalRelationshipType> getRelationship() { 
          return this.relationship;
        }

        /**
         * @param value {@link #relationship} (Whether this relationship is to a parent or to a child.)
         */
        public SpecimenSourceComponent setRelationship(Enumeration<HierarchicalRelationshipType> value) { 
          this.relationship = value;
          return this;
        }

        /**
         * @return Whether this relationship is to a parent or to a child.
         */
        public HierarchicalRelationshipType getRelationshipSimple() { 
          return this.relationship == null ? null : this.relationship.getValue();
        }

        /**
         * @param value Whether this relationship is to a parent or to a child.
         */
        public SpecimenSourceComponent setRelationshipSimple(HierarchicalRelationshipType value) { 
            if (this.relationship == null)
              this.relationship = new Enumeration<HierarchicalRelationshipType>();
            this.relationship.setValue(value);
          return this;
        }

        /**
         * @return {@link #target} (The specimen resource that is the target of this relationship.)
         */
        public List<ResourceReference> getTarget() { 
          return this.target;
        }

    // syntactic sugar
        /**
         * @return {@link #target} (The specimen resource that is the target of this relationship.)
         */
        public ResourceReference addTarget() { 
          ResourceReference t = new ResourceReference();
          this.target.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relationship", "code", "Whether this relationship is to a parent or to a child.", 0, java.lang.Integer.MAX_VALUE, relationship));
          childrenList.add(new Property("target", "Resource(Specimen)", "The specimen resource that is the target of this relationship.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public SpecimenSourceComponent copy(Specimen e) {
        SpecimenSourceComponent dst = new SpecimenSourceComponent();
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.target = new ArrayList<ResourceReference>();
        for (ResourceReference i : target)
          dst.target.add(i.copy());
        return dst;
      }

  }

    public static class SpecimenCollectionComponent extends BackboneElement {
        /**
         * Person who collected the specimen.
         */
        protected ResourceReference collector;

        /**
         * To communicate any details or issues encountered during the specimen collection procedure.
         */
        protected List<String_> comment = new ArrayList<String_>();

        /**
         * Time when specimen was collected from subject - the physiologically relevant time.
         */
        protected DateTime collectedTime;

        /**
         * The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.
         */
        protected Quantity quantity;

        /**
         * A coded value specifying the technique that is used to perform the procedure.
         */
        protected CodeableConcept method;

        /**
         * Anatomical location from which the specimen should be collected (if subject is a patient).
         */
        protected CodeableConcept sourceSite;

      public SpecimenCollectionComponent() {
        super();
      }

      public SpecimenCollectionComponent(DateTime collectedTime) {
        super();
        this.collectedTime = collectedTime;
      }

        /**
         * @return {@link #collector} (Person who collected the specimen.)
         */
        public ResourceReference getCollector() { 
          return this.collector;
        }

        /**
         * @param value {@link #collector} (Person who collected the specimen.)
         */
        public SpecimenCollectionComponent setCollector(ResourceReference value) { 
          this.collector = value;
          return this;
        }

        /**
         * @return {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
        public List<String_> getComment() { 
          return this.comment;
        }

    // syntactic sugar
        /**
         * @return {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
        public String_ addComment() { 
          String_ t = new String_();
          this.comment.add(t);
          return t;
        }

        /**
         * @param value {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
        public String_ addCommentSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.comment.add(t);
          return t;
        }

        /**
         * @return {@link #collectedTime} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public DateTime getCollectedTime() { 
          return this.collectedTime;
        }

        /**
         * @param value {@link #collectedTime} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public SpecimenCollectionComponent setCollectedTime(DateTime value) { 
          this.collectedTime = value;
          return this;
        }

        /**
         * @return Time when specimen was collected from subject - the physiologically relevant time.
         */
        public String getCollectedTimeSimple() { 
          return this.collectedTime == null ? null : this.collectedTime.getValue();
        }

        /**
         * @param value Time when specimen was collected from subject - the physiologically relevant time.
         */
        public SpecimenCollectionComponent setCollectedTimeSimple(String value) { 
            if (this.collectedTime == null)
              this.collectedTime = new DateTime();
            this.collectedTime.setValue(value);
          return this;
        }

        /**
         * @return {@link #quantity} (The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.)
         */
        public SpecimenCollectionComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #method} (A coded value specifying the technique that is used to perform the procedure.)
         */
        public CodeableConcept getMethod() { 
          return this.method;
        }

        /**
         * @param value {@link #method} (A coded value specifying the technique that is used to perform the procedure.)
         */
        public SpecimenCollectionComponent setMethod(CodeableConcept value) { 
          this.method = value;
          return this;
        }

        /**
         * @return {@link #sourceSite} (Anatomical location from which the specimen should be collected (if subject is a patient).)
         */
        public CodeableConcept getSourceSite() { 
          return this.sourceSite;
        }

        /**
         * @param value {@link #sourceSite} (Anatomical location from which the specimen should be collected (if subject is a patient).)
         */
        public SpecimenCollectionComponent setSourceSite(CodeableConcept value) { 
          this.sourceSite = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("collector", "Resource(Practitioner)", "Person who collected the specimen.", 0, java.lang.Integer.MAX_VALUE, collector));
          childrenList.add(new Property("comment", "string", "To communicate any details or issues encountered during the specimen collection procedure.", 0, java.lang.Integer.MAX_VALUE, comment));
          childrenList.add(new Property("collectedTime", "dateTime", "Time when specimen was collected from subject - the physiologically relevant time.", 0, java.lang.Integer.MAX_VALUE, collectedTime));
          childrenList.add(new Property("quantity", "Quantity", "The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("method", "CodeableConcept", "A coded value specifying the technique that is used to perform the procedure.", 0, java.lang.Integer.MAX_VALUE, method));
          childrenList.add(new Property("sourceSite", "CodeableConcept", "Anatomical location from which the specimen should be collected (if subject is a patient).", 0, java.lang.Integer.MAX_VALUE, sourceSite));
        }

      public SpecimenCollectionComponent copy(Specimen e) {
        SpecimenCollectionComponent dst = new SpecimenCollectionComponent();
        dst.collector = collector == null ? null : collector.copy();
        dst.comment = new ArrayList<String_>();
        for (String_ i : comment)
          dst.comment.add(i.copy());
        dst.collectedTime = collectedTime == null ? null : collectedTime.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.method = method == null ? null : method.copy();
        dst.sourceSite = sourceSite == null ? null : sourceSite.copy();
        return dst;
      }

  }

    public static class SpecimenTreatmentComponent extends BackboneElement {
        /**
         * Textual description of procedure.
         */
        protected String_ description;

        /**
         * A coded value specifying the procedure used to process the specimen.
         */
        protected CodeableConcept procedure;

        /**
         * Specimen additive.
         */
        protected List<ResourceReference> additive = new ArrayList<ResourceReference>();

      public SpecimenTreatmentComponent() {
        super();
      }

        /**
         * @return {@link #description} (Textual description of procedure.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Textual description of procedure.)
         */
        public SpecimenTreatmentComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Textual description of procedure.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Textual description of procedure.
         */
        public SpecimenTreatmentComponent setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #procedure} (A coded value specifying the procedure used to process the specimen.)
         */
        public CodeableConcept getProcedure() { 
          return this.procedure;
        }

        /**
         * @param value {@link #procedure} (A coded value specifying the procedure used to process the specimen.)
         */
        public SpecimenTreatmentComponent setProcedure(CodeableConcept value) { 
          this.procedure = value;
          return this;
        }

        /**
         * @return {@link #additive} (Specimen additive.)
         */
        public List<ResourceReference> getAdditive() { 
          return this.additive;
        }

    // syntactic sugar
        /**
         * @return {@link #additive} (Specimen additive.)
         */
        public ResourceReference addAdditive() { 
          ResourceReference t = new ResourceReference();
          this.additive.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("description", "string", "Textual description of procedure.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("procedure", "CodeableConcept", "A coded value specifying the procedure used to process the specimen.", 0, java.lang.Integer.MAX_VALUE, procedure));
          childrenList.add(new Property("additive", "Resource(Substance)", "Specimen additive.", 0, java.lang.Integer.MAX_VALUE, additive));
        }

      public SpecimenTreatmentComponent copy(Specimen e) {
        SpecimenTreatmentComponent dst = new SpecimenTreatmentComponent();
        dst.description = description == null ? null : description.copy();
        dst.procedure = procedure == null ? null : procedure.copy();
        dst.additive = new ArrayList<ResourceReference>();
        for (ResourceReference i : additive)
          dst.additive.add(i.copy());
        return dst;
      }

  }

    public static class SpecimenContainerComponent extends BackboneElement {
        /**
         * Id for container. There may be muliple; a manufacturer's bar code, lab assigned identifier, etc.
         */
        protected List<Identifier> identifier = new ArrayList<Identifier>();

        /**
         * Textual description of container.
         */
        protected String_ description;

        /**
         * The type of container associated with the specimen (eg slide, aliquot, etc).
         */
        protected CodeableConcept type;

        /**
         * The capacity (volume or other measure the container may contain.
         */
        protected Quantity capacity;

        /**
         * The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.
         */
        protected Quantity specimenQuantity;

        /**
         * Additive associated with the container.
         */
        protected ResourceReference additive;

      public SpecimenContainerComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Id for container. There may be muliple; a manufacturer's bar code, lab assigned identifier, etc.)
         */
        public List<Identifier> getIdentifier() { 
          return this.identifier;
        }

    // syntactic sugar
        /**
         * @return {@link #identifier} (Id for container. There may be muliple; a manufacturer's bar code, lab assigned identifier, etc.)
         */
        public Identifier addIdentifier() { 
          Identifier t = new Identifier();
          this.identifier.add(t);
          return t;
        }

        /**
         * @return {@link #description} (Textual description of container.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Textual description of container.)
         */
        public SpecimenContainerComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Textual description of container.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Textual description of container.
         */
        public SpecimenContainerComponent setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The type of container associated with the specimen (eg slide, aliquot, etc).)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of container associated with the specimen (eg slide, aliquot, etc).)
         */
        public SpecimenContainerComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #capacity} (The capacity (volume or other measure the container may contain.)
         */
        public Quantity getCapacity() { 
          return this.capacity;
        }

        /**
         * @param value {@link #capacity} (The capacity (volume or other measure the container may contain.)
         */
        public SpecimenContainerComponent setCapacity(Quantity value) { 
          this.capacity = value;
          return this;
        }

        /**
         * @return {@link #specimenQuantity} (The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.)
         */
        public Quantity getSpecimenQuantity() { 
          return this.specimenQuantity;
        }

        /**
         * @param value {@link #specimenQuantity} (The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.)
         */
        public SpecimenContainerComponent setSpecimenQuantity(Quantity value) { 
          this.specimenQuantity = value;
          return this;
        }

        /**
         * @return {@link #additive} (Additive associated with the container.)
         */
        public ResourceReference getAdditive() { 
          return this.additive;
        }

        /**
         * @param value {@link #additive} (Additive associated with the container.)
         */
        public SpecimenContainerComponent setAdditive(ResourceReference value) { 
          this.additive = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Id for container. There may be muliple; a manufacturer's bar code, lab assigned identifier, etc.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("description", "string", "Textual description of container.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("type", "CodeableConcept", "The type of container associated with the specimen (eg slide, aliquot, etc).", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("capacity", "Quantity", "The capacity (volume or other measure the container may contain.", 0, java.lang.Integer.MAX_VALUE, capacity));
          childrenList.add(new Property("specimenQuantity", "Quantity", "The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.", 0, java.lang.Integer.MAX_VALUE, specimenQuantity));
          childrenList.add(new Property("additive", "Resource(Substance)", "Additive associated with the container.", 0, java.lang.Integer.MAX_VALUE, additive));
        }

      public SpecimenContainerComponent copy(Specimen e) {
        SpecimenContainerComponent dst = new SpecimenContainerComponent();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.type = type == null ? null : type.copy();
        dst.capacity = capacity == null ? null : capacity.copy();
        dst.specimenQuantity = specimenQuantity == null ? null : specimenQuantity.copy();
        dst.additive = additive == null ? null : additive.copy();
        return dst;
      }

  }

    /**
     * Id for specimen.
     */
    protected Identifier identifier;

    /**
     * The type of the specimen. This is sometimes called the "matrix".
     */
    protected CodeableConcept type;

    /**
     * Parent specimen from which the focal specimen was a component.
     */
    protected List<SpecimenSourceComponent> source = new ArrayList<SpecimenSourceComponent>();

    /**
     * The subject of the report.
     */
    protected ResourceReference subject;

    /**
     * The identifier(s) assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.
     */
    protected List<Identifier> accessionIdentifier = new ArrayList<Identifier>();

    /**
     * Time when specimen was received for processing or testing.
     */
    protected DateTime receivedTime;

    /**
     * Details concerning the specimen collection.
     */
    protected SpecimenCollectionComponent collection;

    /**
     * Details concerning treatment and processing steps for the specimen.
     */
    protected List<SpecimenTreatmentComponent> treatment = new ArrayList<SpecimenTreatmentComponent>();

    /**
     * The container holding the specimen. May be recursive; ie blood in tube in tray in rack.
     */
    protected List<SpecimenContainerComponent> container = new ArrayList<SpecimenContainerComponent>();

    public Specimen() {
      super();
    }

    public Specimen(ResourceReference subject, SpecimenCollectionComponent collection) {
      super();
      this.subject = subject;
      this.collection = collection;
    }

    /**
     * @return {@link #identifier} (Id for specimen.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Id for specimen.)
     */
    public Specimen setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #type} (The type of the specimen. This is sometimes called the "matrix".)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The type of the specimen. This is sometimes called the "matrix".)
     */
    public Specimen setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #source} (Parent specimen from which the focal specimen was a component.)
     */
    public List<SpecimenSourceComponent> getSource() { 
      return this.source;
    }

    // syntactic sugar
    /**
     * @return {@link #source} (Parent specimen from which the focal specimen was a component.)
     */
    public SpecimenSourceComponent addSource() { 
      SpecimenSourceComponent t = new SpecimenSourceComponent();
      this.source.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (The subject of the report.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The subject of the report.)
     */
    public Specimen setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #accessionIdentifier} (The identifier(s) assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.)
     */
    public List<Identifier> getAccessionIdentifier() { 
      return this.accessionIdentifier;
    }

    // syntactic sugar
    /**
     * @return {@link #accessionIdentifier} (The identifier(s) assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.)
     */
    public Identifier addAccessionIdentifier() { 
      Identifier t = new Identifier();
      this.accessionIdentifier.add(t);
      return t;
    }

    /**
     * @return {@link #receivedTime} (Time when specimen was received for processing or testing.)
     */
    public DateTime getReceivedTime() { 
      return this.receivedTime;
    }

    /**
     * @param value {@link #receivedTime} (Time when specimen was received for processing or testing.)
     */
    public Specimen setReceivedTime(DateTime value) { 
      this.receivedTime = value;
      return this;
    }

    /**
     * @return Time when specimen was received for processing or testing.
     */
    public String getReceivedTimeSimple() { 
      return this.receivedTime == null ? null : this.receivedTime.getValue();
    }

    /**
     * @param value Time when specimen was received for processing or testing.
     */
    public Specimen setReceivedTimeSimple(String value) { 
      if (value == null)
        this.receivedTime = null;
      else {
        if (this.receivedTime == null)
          this.receivedTime = new DateTime();
        this.receivedTime.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #collection} (Details concerning the specimen collection.)
     */
    public SpecimenCollectionComponent getCollection() { 
      return this.collection;
    }

    /**
     * @param value {@link #collection} (Details concerning the specimen collection.)
     */
    public Specimen setCollection(SpecimenCollectionComponent value) { 
      this.collection = value;
      return this;
    }

    /**
     * @return {@link #treatment} (Details concerning treatment and processing steps for the specimen.)
     */
    public List<SpecimenTreatmentComponent> getTreatment() { 
      return this.treatment;
    }

    // syntactic sugar
    /**
     * @return {@link #treatment} (Details concerning treatment and processing steps for the specimen.)
     */
    public SpecimenTreatmentComponent addTreatment() { 
      SpecimenTreatmentComponent t = new SpecimenTreatmentComponent();
      this.treatment.add(t);
      return t;
    }

    /**
     * @return {@link #container} (The container holding the specimen. May be recursive; ie blood in tube in tray in rack.)
     */
    public List<SpecimenContainerComponent> getContainer() { 
      return this.container;
    }

    // syntactic sugar
    /**
     * @return {@link #container} (The container holding the specimen. May be recursive; ie blood in tube in tray in rack.)
     */
    public SpecimenContainerComponent addContainer() { 
      SpecimenContainerComponent t = new SpecimenContainerComponent();
      this.container.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Id for specimen.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "The type of the specimen. This is sometimes called the 'matrix'.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("source", "", "Parent specimen from which the focal specimen was a component.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device|Substance)", "The subject of the report.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("accessionIdentifier", "Identifier", "The identifier(s) assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.", 0, java.lang.Integer.MAX_VALUE, accessionIdentifier));
        childrenList.add(new Property("receivedTime", "dateTime", "Time when specimen was received for processing or testing.", 0, java.lang.Integer.MAX_VALUE, receivedTime));
        childrenList.add(new Property("collection", "", "Details concerning the specimen collection.", 0, java.lang.Integer.MAX_VALUE, collection));
        childrenList.add(new Property("treatment", "", "Details concerning treatment and processing steps for the specimen.", 0, java.lang.Integer.MAX_VALUE, treatment));
        childrenList.add(new Property("container", "", "The container holding the specimen. May be recursive; ie blood in tube in tray in rack.", 0, java.lang.Integer.MAX_VALUE, container));
      }

      public Specimen copy() {
        Specimen dst = new Specimen();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = type == null ? null : type.copy();
        dst.source = new ArrayList<SpecimenSourceComponent>();
        for (SpecimenSourceComponent i : source)
          dst.source.add(i.copy(dst));
        dst.subject = subject == null ? null : subject.copy();
        dst.accessionIdentifier = new ArrayList<Identifier>();
        for (Identifier i : accessionIdentifier)
          dst.accessionIdentifier.add(i.copy());
        dst.receivedTime = receivedTime == null ? null : receivedTime.copy();
        dst.collection = collection == null ? null : collection.copy(dst);
        dst.treatment = new ArrayList<SpecimenTreatmentComponent>();
        for (SpecimenTreatmentComponent i : treatment)
          dst.treatment.add(i.copy(dst));
        dst.container = new ArrayList<SpecimenContainerComponent>();
        for (SpecimenContainerComponent i : container)
          dst.container.add(i.copy(dst));
        return dst;
      }

      protected Specimen typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Specimen;
   }


}

