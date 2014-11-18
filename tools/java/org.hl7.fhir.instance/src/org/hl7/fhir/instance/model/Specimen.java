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
 * Sample for analysis.
 */
public class Specimen extends DomainResource {

    public enum HierarchicalRelationshipType {
        PARENT, // The target resource is the parent of the focal specimen resource.
        CHILD, // The target resource is the child of the focal specimen resource.
        NULL; // added to help the parsers
        public static HierarchicalRelationshipType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("parent".equals(codeString))
          return PARENT;
        if ("child".equals(codeString))
          return CHILD;
        throw new Exception("Unknown HierarchicalRelationshipType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PARENT: return "parent";
            case CHILD: return "child";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PARENT: return "The target resource is the parent of the focal specimen resource.";
            case CHILD: return "The target resource is the child of the focal specimen resource.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PARENT: return "Parent";
            case CHILD: return "Child";
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
          return HierarchicalRelationshipType.PARENT;
        if ("child".equals(codeString))
          return HierarchicalRelationshipType.CHILD;
        throw new Exception("Unknown HierarchicalRelationshipType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == HierarchicalRelationshipType.PARENT)
        return "parent";
      if (code == HierarchicalRelationshipType.CHILD)
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
        protected List<Reference> target = new ArrayList<Reference>();
        /**
         * The actual objects that are the target of the reference (The specimen resource that is the target of this relationship.)
         */
        protected List<Specimen> targetTarget = new ArrayList<Specimen>();


        private static final long serialVersionUID = -452220997L;

      public SpecimenSourceComponent() {
        super();
      }

      public SpecimenSourceComponent(Enumeration<HierarchicalRelationshipType> relationship) {
        super();
        this.relationship = relationship;
      }

        /**
         * @return {@link #relationship} (Whether this relationship is to a parent or to a child.). This is the underlying object with id, value and extensions. The accessor "getRelationship" gives direct access to the value
         */
        public Enumeration<HierarchicalRelationshipType> getRelationshipElement() { 
          return this.relationship;
        }

        /**
         * @param value {@link #relationship} (Whether this relationship is to a parent or to a child.). This is the underlying object with id, value and extensions. The accessor "getRelationship" gives direct access to the value
         */
        public SpecimenSourceComponent setRelationshipElement(Enumeration<HierarchicalRelationshipType> value) { 
          this.relationship = value;
          return this;
        }

        /**
         * @return Whether this relationship is to a parent or to a child.
         */
        public HierarchicalRelationshipType getRelationship() { 
          return this.relationship == null ? null : this.relationship.getValue();
        }

        /**
         * @param value Whether this relationship is to a parent or to a child.
         */
        public SpecimenSourceComponent setRelationship(HierarchicalRelationshipType value) { 
            if (this.relationship == null)
              this.relationship = new Enumeration<HierarchicalRelationshipType>();
            this.relationship.setValue(value);
          return this;
        }

        /**
         * @return {@link #target} (The specimen resource that is the target of this relationship.)
         */
        public List<Reference> getTarget() { 
          return this.target;
        }

        /**
         * @return {@link #target} (The specimen resource that is the target of this relationship.)
         */
    // syntactic sugar
        public Reference addTarget() { //3
          Reference t = new Reference();
          this.target.add(t);
          return t;
        }

        /**
         * @return {@link #target} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The specimen resource that is the target of this relationship.)
         */
        public List<Specimen> getTargetTarget() { 
          return this.targetTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #target} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. The specimen resource that is the target of this relationship.)
         */
        public Specimen addTargetTarget() { 
          Specimen r = new Specimen();
          this.targetTarget.add(r);
          return r;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relationship", "code", "Whether this relationship is to a parent or to a child.", 0, java.lang.Integer.MAX_VALUE, relationship));
          childrenList.add(new Property("target", "Reference(Specimen)", "The specimen resource that is the target of this relationship.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public SpecimenSourceComponent copy() {
        SpecimenSourceComponent dst = new SpecimenSourceComponent();
        copyValues(dst);
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.target = new ArrayList<Reference>();
        for (Reference i : target)
          dst.target.add(i.copy());
        return dst;
      }

  }

    public static class SpecimenCollectionComponent extends BackboneElement {
        /**
         * Person who collected the specimen.
         */
        protected Reference collector;

        /**
         * The actual object that is the target of the reference (Person who collected the specimen.)
         */
        protected Practitioner collectorTarget;

        /**
         * To communicate any details or issues encountered during the specimen collection procedure.
         */
        protected List<StringType> comment = new ArrayList<StringType>();

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

        private static final long serialVersionUID = 2038407490L;

      public SpecimenCollectionComponent() {
        super();
      }

        /**
         * @return {@link #collector} (Person who collected the specimen.)
         */
        public Reference getCollector() { 
          return this.collector;
        }

        /**
         * @param value {@link #collector} (Person who collected the specimen.)
         */
        public SpecimenCollectionComponent setCollector(Reference value) { 
          this.collector = value;
          return this;
        }

        /**
         * @return {@link #collector} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Person who collected the specimen.)
         */
        public Practitioner getCollectorTarget() { 
          return this.collectorTarget;
        }

        /**
         * @param value {@link #collector} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Person who collected the specimen.)
         */
        public SpecimenCollectionComponent setCollectorTarget(Practitioner value) { 
          this.collectorTarget = value;
          return this;
        }

        /**
         * @return {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
        public List<StringType> getComment() { 
          return this.comment;
        }

        /**
         * @return {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
    // syntactic sugar
        public StringType addCommentElement() {//2 
          StringType t = new StringType();
          this.comment.add(t);
          return t;
        }

        /**
         * @param value {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
        public SpecimenCollectionComponent addComment(String value) { //1
          StringType t = new StringType();
          t.setValue(value);
          this.comment.add(t);
          return this;
        }

        /**
         * @param value {@link #comment} (To communicate any details or issues encountered during the specimen collection procedure.)
         */
        public boolean hasComment(String value) { 
          for (StringType v : this.comment)
            if (v.equals(value)) // string
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
          childrenList.add(new Property("collector", "Reference(Practitioner)", "Person who collected the specimen.", 0, java.lang.Integer.MAX_VALUE, collector));
          childrenList.add(new Property("comment", "string", "To communicate any details or issues encountered during the specimen collection procedure.", 0, java.lang.Integer.MAX_VALUE, comment));
          childrenList.add(new Property("collected[x]", "dateTime|Period", "Time when specimen was collected from subject - the physiologically relevant time.", 0, java.lang.Integer.MAX_VALUE, collected));
          childrenList.add(new Property("quantity", "Quantity", "The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("method", "CodeableConcept", "A coded value specifying the technique that is used to perform the procedure.", 0, java.lang.Integer.MAX_VALUE, method));
          childrenList.add(new Property("sourceSite", "CodeableConcept", "Anatomical location from which the specimen should be collected (if subject is a patient). This element is not used for environmental specimens.", 0, java.lang.Integer.MAX_VALUE, sourceSite));
        }

      public SpecimenCollectionComponent copy() {
        SpecimenCollectionComponent dst = new SpecimenCollectionComponent();
        copyValues(dst);
        dst.collector = collector == null ? null : collector.copy();
        dst.comment = new ArrayList<StringType>();
        for (StringType i : comment)
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
        protected StringType description;

        /**
         * A coded value specifying the procedure used to process the specimen.
         */
        protected CodeableConcept procedure;

        /**
         * Material used in the processing step.
         */
        protected List<Reference> additive = new ArrayList<Reference>();
        /**
         * The actual objects that are the target of the reference (Material used in the processing step.)
         */
        protected List<Substance> additiveTarget = new ArrayList<Substance>();


        private static final long serialVersionUID = -402002686L;

      public SpecimenTreatmentComponent() {
        super();
      }

        /**
         * @return {@link #description} (Textual description of procedure.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Textual description of procedure.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public SpecimenTreatmentComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Textual description of procedure.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Textual description of procedure.
         */
        public SpecimenTreatmentComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
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
        public List<Reference> getAdditive() { 
          return this.additive;
        }

        /**
         * @return {@link #additive} (Material used in the processing step.)
         */
    // syntactic sugar
        public Reference addAdditive() { //3
          Reference t = new Reference();
          this.additive.add(t);
          return t;
        }

        /**
         * @return {@link #additive} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Material used in the processing step.)
         */
        public List<Substance> getAdditiveTarget() { 
          return this.additiveTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #additive} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. Material used in the processing step.)
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
          childrenList.add(new Property("additive", "Reference(Substance)", "Material used in the processing step.", 0, java.lang.Integer.MAX_VALUE, additive));
        }

      public SpecimenTreatmentComponent copy() {
        SpecimenTreatmentComponent dst = new SpecimenTreatmentComponent();
        copyValues(dst);
        dst.description = description == null ? null : description.copy();
        dst.procedure = procedure == null ? null : procedure.copy();
        dst.additive = new ArrayList<Reference>();
        for (Reference i : additive)
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
        protected StringType description;

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
         * Introduced substance to preserve, maintain or enhance the specimen. examples: Formalin, Citrate, EDTA.
         */
        protected Type additive;

        private static final long serialVersionUID = 1823665165L;

      public SpecimenContainerComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.)
         */
        public List<Identifier> getIdentifier() { 
          return this.identifier;
        }

        /**
         * @return {@link #identifier} (Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.)
         */
    // syntactic sugar
        public Identifier addIdentifier() { //3
          Identifier t = new Identifier();
          this.identifier.add(t);
          return t;
        }

        /**
         * @return {@link #description} (Textual description of the container.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Textual description of the container.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public SpecimenContainerComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Textual description of the container.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Textual description of the container.
         */
        public SpecimenContainerComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
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
         * @return {@link #additive} (Introduced substance to preserve, maintain or enhance the specimen. examples: Formalin, Citrate, EDTA.)
         */
        public Type getAdditive() { 
          return this.additive;
        }

        /**
         * @param value {@link #additive} (Introduced substance to preserve, maintain or enhance the specimen. examples: Formalin, Citrate, EDTA.)
         */
        public SpecimenContainerComponent setAdditive(Type value) { 
          this.additive = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("description", "string", "Textual description of the container.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("type", "CodeableConcept", "The type of container associated with the specimen (e.g. slide, aliquot, etc).", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("capacity", "Quantity", "The capacity (volume or other measure) the container may contain.", 0, java.lang.Integer.MAX_VALUE, capacity));
          childrenList.add(new Property("specimenQuantity", "Quantity", "The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.", 0, java.lang.Integer.MAX_VALUE, specimenQuantity));
          childrenList.add(new Property("additive[x]", "CodeableConcept|Reference(Substance)", "Introduced substance to preserve, maintain or enhance the specimen. examples: Formalin, Citrate, EDTA.", 0, java.lang.Integer.MAX_VALUE, additive));
        }

      public SpecimenContainerComponent copy() {
        SpecimenContainerComponent dst = new SpecimenContainerComponent();
        copyValues(dst);
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
    protected Reference subject;

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
    protected DateTimeType receivedTime;

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

    private static final long serialVersionUID = 257946834L;

    public Specimen() {
      super();
    }

    public Specimen(Reference subject) {
      super();
      this.subject = subject;
    }

    /**
     * @return {@link #identifier} (Id for specimen.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Id for specimen.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
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

    /**
     * @return {@link #source} (Parent specimen from which the focal specimen was a component.)
     */
    // syntactic sugar
    public SpecimenSourceComponent addSource() { //3
      SpecimenSourceComponent t = new SpecimenSourceComponent();
      this.source.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    public Specimen setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Where the specimen came from. This may be the patient(s) or from the environment or  a device.)
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
     * @return {@link #receivedTime} (Time when specimen was received for processing or testing.). This is the underlying object with id, value and extensions. The accessor "getReceivedTime" gives direct access to the value
     */
    public DateTimeType getReceivedTimeElement() { 
      return this.receivedTime;
    }

    /**
     * @param value {@link #receivedTime} (Time when specimen was received for processing or testing.). This is the underlying object with id, value and extensions. The accessor "getReceivedTime" gives direct access to the value
     */
    public Specimen setReceivedTimeElement(DateTimeType value) { 
      this.receivedTime = value;
      return this;
    }

    /**
     * @return Time when specimen was received for processing or testing.
     */
    public DateAndTime getReceivedTime() { 
      return this.receivedTime == null ? null : this.receivedTime.getValue();
    }

    /**
     * @param value Time when specimen was received for processing or testing.
     */
    public Specimen setReceivedTime(DateAndTime value) { 
      if (value == null)
        this.receivedTime = null;
      else {
        if (this.receivedTime == null)
          this.receivedTime = new DateTimeType();
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

    /**
     * @return {@link #treatment} (Details concerning treatment and processing steps for the specimen.)
     */
    // syntactic sugar
    public SpecimenTreatmentComponent addTreatment() { //3
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

    /**
     * @return {@link #container} (The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.)
     */
    // syntactic sugar
    public SpecimenContainerComponent addContainer() { //3
      SpecimenContainerComponent t = new SpecimenContainerComponent();
      this.container.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Id for specimen.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "Kind of material that forms the specimen.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("source", "", "Parent specimen from which the focal specimen was a component.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("subject", "Reference(Patient|Group|Device|Substance)", "Where the specimen came from. This may be the patient(s) or from the environment or  a device.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("accessionIdentifier", "Identifier", "The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.", 0, java.lang.Integer.MAX_VALUE, accessionIdentifier));
        childrenList.add(new Property("receivedTime", "dateTime", "Time when specimen was received for processing or testing.", 0, java.lang.Integer.MAX_VALUE, receivedTime));
        childrenList.add(new Property("collection", "", "Details concerning the specimen collection.", 0, java.lang.Integer.MAX_VALUE, collection));
        childrenList.add(new Property("treatment", "", "Details concerning treatment and processing steps for the specimen.", 0, java.lang.Integer.MAX_VALUE, treatment));
        childrenList.add(new Property("container", "", "The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.", 0, java.lang.Integer.MAX_VALUE, container));
      }

      public Specimen copy() {
        Specimen dst = new Specimen();
        copyValues(dst);
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

