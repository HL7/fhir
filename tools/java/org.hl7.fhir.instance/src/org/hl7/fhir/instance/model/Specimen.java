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
        /**
         * The actual objects that are the target of the reference (The specimen resource that is the target of this relationship.)
         */
        protected List<Specimen> targetTarget = new ArrayList<Specimen>();


        private static final long serialVersionUID = 118968671L;

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

        /**
         * @return {@link #target} (The actual objects that are the target of the reference. The specimen resource that is the target of this relationship.)
         */
        public List<Specimen> getTargetTarget() { 
          return this.targetTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #target} (Add an actual object that is the target of the reference. The specimen resource that is the target of this relationship.)
         */
        public Specimen addTargetTarget() { 
          Specimen r = new Specimen();
          this.targetTarget.add(r);
          return r;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relationship", "code", "Whether this relationship is to a parent or to a child.", 0, java.lang.Integer.MAX_VALUE, relationship));
          childrenList.add(new Property("target", "Resource(Specimen)", "The specimen resource that is the target of this relationship.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public SpecimenSourceComponent copy() {
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
         * The actual object that is the target of the reference (Person who collected the specimen.)
         */
        protected Practitioner collectorTarget;

        /**
         * To communicate any details or issues encountered during the specimen collection procedure.
         */
        protected List<String_> comment = new ArrayList<String_>();

        /**
         * Time when specimen was collected from subject - the physiologically relevant time.
         */
        protected Type collected;

        /**
         * The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.
         */
        protected Quantity quantity;

        /**
         * A coded value specifying the technique that is used to perform the procedure.
         */
        protected CodeableConcept method;

        /**
         * Anatomical location from which the specimen should be collected (if subject is a patient). This element is not used for environmental specimens.
         */
        protected CodeableConcept sourceSite;

        private static final long serialVersionUID = -558642016L;

      public SpecimenCollectionComponent() {
        super();
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
         * @return {@link #collector} (The actual object that is the target of the reference. Person who collected the specimen.)
         */
        public Practitioner getCollectorTarget() { 
          return this.collectorTarget;
        }

        /**
         * @param value {@link #collector} (The actual object that is the target of the reference. Person who collected the specimen.)
         */
        public SpecimenCollectionComponent setCollectorTarget(Practitioner value) { 
          this.collectorTarget = value;
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
         * @param value {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
        public boolean hasCommentSimple(String value) { 
          for (String_ v : this.comment)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #collected} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public Type getCollected() { 
          return this.collected;
        }

        /**
         * @param value {@link #collected} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public SpecimenCollectionComponent setCollected(Type value) { 
          this.collected = value;
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
         * @return {@link #sourceSite} (Anatomical location from which the specimen should be collected (if subject is a patient). This element is not used for environmental specimens.)
         */
        public CodeableConcept getSourceSite() { 
          return this.sourceSite;
        }

        /**
         * @param value {@link #sourceSite} (Anatomical location from which the specimen should be collected (if subject is a patient). This element is not used for environmental specimens.)
         */
        public SpecimenCollectionComponent setSourceSite(CodeableConcept value) { 
          this.sourceSite = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("collector", "Resource(Practitioner)", "Person who collected the specimen.", 0, java.lang.Integer.MAX_VALUE, collector));
          childrenList.add(new Property("comment", "string", "To communicate any details or issues encountered during the specimen collection procedure.", 0, java.lang.Integer.MAX_VALUE, comment));
          childrenList.add(new Property("collected[x]", "dateTime|Period", "Time when specimen was collected from subject - the physiologically relevant time.", 0, java.lang.Integer.MAX_VALUE, collected));
          childrenList.add(new Property("quantity", "Quantity", "The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("method", "CodeableConcept", "A coded value specifying the technique that is used to perform the procedure.", 0, java.lang.Integer.MAX_VALUE, method));
          childrenList.add(new Property("sourceSite", "CodeableConcept", "Anatomical location from which the specimen should be collected (if subject is a patient). This element is not used for environmental specimens.", 0, java.lang.Integer.MAX_VALUE, sourceSite));
        }

      public SpecimenCollectionComponent copy() {
        SpecimenCollectionComponent dst = new SpecimenCollectionComponent();
        dst.collector = collector == null ? null : collector.copy();
        dst.comment = new ArrayList<String_>();
        for (String_ i : comment)
          dst.comment.add(i.copy());
        dst.collected = collected == null ? null : collected.copy();
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
         * Material used in the processing step.
         */
        protected List<ResourceReference> additive = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Material used in the processing step.)
         */
        protected List<Substance> additiveTarget = new ArrayList<Substance>();


        private static final long serialVersionUID = -889950937L;

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
         * @return {@link #additive} (Material used in the processing step.)
         */
        public List<ResourceReference> getAdditive() { 
          return this.additive;
        }

    // syntactic sugar
        /**
         * @return {@link #additive} (Material used in the processing step.)
         */
        public ResourceReference addAdditive() { 
          ResourceReference t = new ResourceReference();
          this.additive.add(t);
          return t;
        }

        /**
         * @return {@link #additive} (The actual objects that are the target of the reference. Material used in the processing step.)
         */
        public List<Substance> getAdditiveTarget() { 
          return this.additiveTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #additive} (Add an actual object that is the target of the reference. Material used in the processing step.)
         */
        public Substance addAdditiveTarget() { 
          Substance r = new Substance();
          this.additiveTarget.add(r);
          return r;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("description", "string", "Textual description of procedure.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("procedure", "CodeableConcept", "A coded value specifying the procedure used to process the specimen.", 0, java.lang.Integer.MAX_VALUE, procedure));
          childrenList.add(new Property("additive", "Resource(Substance)", "Material used in the processing step.", 0, java.lang.Integer.MAX_VALUE, additive));
        }

      public SpecimenTreatmentComponent copy() {
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
         * Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.
         */
        protected List<Identifier> identifier = new ArrayList<Identifier>();

        /**
         * Textual description of the container.
         */
        protected String_ description;

        /**
         * The type of container associated with the specimen (e.g. slide, aliquot, etc).
         */
        protected CodeableConcept type;

        /**
         * The capacity (volume or other measure) the container may contain.
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

        /**
         * The actual object that is the target of the reference (Additive associated with the container.)
         */
        protected Substance additiveTarget;

        private static final long serialVersionUID = -1340176830L;

      public SpecimenContainerComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.)
         */
        public List<Identifier> getIdentifier() { 
          return this.identifier;
        }

    // syntactic sugar
        /**
         * @return {@link #identifier} (Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.)
         */
        public Identifier addIdentifier() { 
          Identifier t = new Identifier();
          this.identifier.add(t);
          return t;
        }

        /**
         * @return {@link #description} (Textual description of the container.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Textual description of the container.)
         */
        public SpecimenContainerComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Textual description of the container.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Textual description of the container.
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
         * @return {@link #type} (The type of container associated with the specimen (e.g. slide, aliquot, etc).)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of container associated with the specimen (e.g. slide, aliquot, etc).)
         */
        public SpecimenContainerComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #capacity} (The capacity (volume or other measure) the container may contain.)
         */
        public Quantity getCapacity() { 
          return this.capacity;
        }

        /**
         * @param value {@link #capacity} (The capacity (volume or other measure) the container may contain.)
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

        /**
         * @return {@link #additive} (The actual object that is the target of the reference. Additive associated with the container.)
         */
        public Substance getAdditiveTarget() { 
          return this.additiveTarget;
        }

        /**
         * @param value {@link #additive} (The actual object that is the target of the reference. Additive associated with the container.)
         */
        public SpecimenContainerComponent setAdditiveTarget(Substance value) { 
          this.additiveTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("description", "string", "Textual description of the container.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("type", "CodeableConcept", "The type of container associated with the specimen (e.g. slide, aliquot, etc).", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("capacity", "Quantity", "The capacity (volume or other measure) the container may contain.", 0, java.lang.Integer.MAX_VALUE, capacity));
          childrenList.add(new Property("specimenQuantity", "Quantity", "The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.", 0, java.lang.Integer.MAX_VALUE, specimenQuantity));
          childrenList.add(new Property("additive", "Resource(Substance)", "Additive associated with the container.", 0, java.lang.Integer.MAX_VALUE, additive));
        }

      public SpecimenContainerComponent copy() {
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
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Kind of material that forms the specimen.
     */
    protected CodeableConcept type;

    /**
     * Parent specimen from which the focal specimen was a component.
     */
    protected List<SpecimenSourceComponent> source = new ArrayList<SpecimenSourceComponent>();

    /**
     * Where the specimen came from. This may be the patient(s) or from the environment or  a device.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    protected Resource subjectTarget;

    /**
     * The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.
     */
    protected Identifier accessionIdentifier;

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
     * The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.
     */
    protected List<SpecimenContainerComponent> container = new ArrayList<SpecimenContainerComponent>();

    private static final long serialVersionUID = 1782045310L;

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
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Id for specimen.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #type} (Kind of material that forms the specimen.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Kind of material that forms the specimen.)
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
     * @return {@link #subject} (Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    public Specimen setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    public Specimen setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #accessionIdentifier} (The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.)
     */
    public Identifier getAccessionIdentifier() { 
      return this.accessionIdentifier;
    }

    /**
     * @param value {@link #accessionIdentifier} (The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.)
     */
    public Specimen setAccessionIdentifier(Identifier value) { 
      this.accessionIdentifier = value;
      return this;
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
    public DateAndTime getReceivedTimeSimple() { 
      return this.receivedTime == null ? null : this.receivedTime.getValue();
    }

    /**
     * @param value Time when specimen was received for processing or testing.
     */
    public Specimen setReceivedTimeSimple(DateAndTime value) { 
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
     * @return {@link #container} (The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.)
     */
    public List<SpecimenContainerComponent> getContainer() { 
      return this.container;
    }

    // syntactic sugar
    /**
     * @return {@link #container} (The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.)
     */
    public SpecimenContainerComponent addContainer() { 
      SpecimenContainerComponent t = new SpecimenContainerComponent();
      this.container.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Id for specimen.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "Kind of material that forms the specimen.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("source", "", "Parent specimen from which the focal specimen was a component.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device|Substance)", "Where the specimen came from. This may be the patient(s) or from the environment or  a device.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("accessionIdentifier", "Identifier", "The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.", 0, java.lang.Integer.MAX_VALUE, accessionIdentifier));
        childrenList.add(new Property("receivedTime", "dateTime", "Time when specimen was received for processing or testing.", 0, java.lang.Integer.MAX_VALUE, receivedTime));
        childrenList.add(new Property("collection", "", "Details concerning the specimen collection.", 0, java.lang.Integer.MAX_VALUE, collection));
        childrenList.add(new Property("treatment", "", "Details concerning treatment and processing steps for the specimen.", 0, java.lang.Integer.MAX_VALUE, treatment));
        childrenList.add(new Property("container", "", "The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.", 0, java.lang.Integer.MAX_VALUE, container));
      }

      public Specimen copy() {
        Specimen dst = new Specimen();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.source = new ArrayList<SpecimenSourceComponent>();
        for (SpecimenSourceComponent i : source)
          dst.source.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.accessionIdentifier = accessionIdentifier == null ? null : accessionIdentifier.copy();
        dst.receivedTime = receivedTime == null ? null : receivedTime.copy();
        dst.collection = collection == null ? null : collection.copy();
        dst.treatment = new ArrayList<SpecimenTreatmentComponent>();
        for (SpecimenTreatmentComponent i : treatment)
          dst.treatment.add(i.copy());
        dst.container = new ArrayList<SpecimenContainerComponent>();
        for (SpecimenContainerComponent i : container)
          dst.container.add(i.copy());
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

