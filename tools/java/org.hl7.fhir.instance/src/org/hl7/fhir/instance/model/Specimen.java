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

  public class HierarchicalRelationshipTypeEnumFactory implements EnumFactory {
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

    public class SpecimenSourceComponent extends Element {
        /**
         * Whether this relationship is to a parent or to a child.
         */
        protected Enumeration<HierarchicalRelationshipType> relationship;

        /**
         * The specimen resource that is the target of this relationship.
         */
        protected List<ResourceReference> target = new ArrayList<ResourceReference>();

        public Enumeration<HierarchicalRelationshipType> getRelationship() { 
          return this.relationship;
        }

        public void setRelationship(Enumeration<HierarchicalRelationshipType> value) { 
          this.relationship = value;
        }

        public HierarchicalRelationshipType getRelationshipSimple() { 
          return this.relationship == null ? null : this.relationship.getValue();
        }

        public void setRelationshipSimple(HierarchicalRelationshipType value) { 
            if (this.relationship == null)
              this.relationship = new Enumeration<HierarchicalRelationshipType>();
            this.relationship.setValue(value);
        }

        public List<ResourceReference> getTarget() { 
          return this.target;
        }

    // syntactic sugar
        public ResourceReference addTarget() { 
          ResourceReference t = new ResourceReference();
          this.target.add(t);
          return t;
        }

      public SpecimenSourceComponent copy(Specimen e) {
        SpecimenSourceComponent dst = e.new SpecimenSourceComponent();
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.target = new ArrayList<ResourceReference>();
        for (ResourceReference i : target)
          dst.target.add(i.copy());
        return dst;
      }

  }

    public class SpecimenCollectionComponent extends Element {
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

        public ResourceReference getCollector() { 
          return this.collector;
        }

        public void setCollector(ResourceReference value) { 
          this.collector = value;
        }

        public List<String_> getComment() { 
          return this.comment;
        }

    // syntactic sugar
        public String_ addComment() { 
          String_ t = new String_();
          this.comment.add(t);
          return t;
        }

        public String_ addCommentSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.comment.add(t);
          return t;
        }

        public DateTime getCollectedTime() { 
          return this.collectedTime;
        }

        public void setCollectedTime(DateTime value) { 
          this.collectedTime = value;
        }

        public String getCollectedTimeSimple() { 
          return this.collectedTime == null ? null : this.collectedTime.getValue();
        }

        public void setCollectedTimeSimple(String value) { 
            if (this.collectedTime == null)
              this.collectedTime = new DateTime();
            this.collectedTime.setValue(value);
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public void setQuantity(Quantity value) { 
          this.quantity = value;
        }

        public CodeableConcept getMethod() { 
          return this.method;
        }

        public void setMethod(CodeableConcept value) { 
          this.method = value;
        }

        public CodeableConcept getSourceSite() { 
          return this.sourceSite;
        }

        public void setSourceSite(CodeableConcept value) { 
          this.sourceSite = value;
        }

      public SpecimenCollectionComponent copy(Specimen e) {
        SpecimenCollectionComponent dst = e.new SpecimenCollectionComponent();
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

    public class SpecimenTreatmentComponent extends Element {
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

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
        }

        public CodeableConcept getProcedure() { 
          return this.procedure;
        }

        public void setProcedure(CodeableConcept value) { 
          this.procedure = value;
        }

        public List<ResourceReference> getAdditive() { 
          return this.additive;
        }

    // syntactic sugar
        public ResourceReference addAdditive() { 
          ResourceReference t = new ResourceReference();
          this.additive.add(t);
          return t;
        }

      public SpecimenTreatmentComponent copy(Specimen e) {
        SpecimenTreatmentComponent dst = e.new SpecimenTreatmentComponent();
        dst.description = description == null ? null : description.copy();
        dst.procedure = procedure == null ? null : procedure.copy();
        dst.additive = new ArrayList<ResourceReference>();
        for (ResourceReference i : additive)
          dst.additive.add(i.copy());
        return dst;
      }

  }

    public class SpecimenContainerComponent extends Element {
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

        public List<Identifier> getIdentifier() { 
          return this.identifier;
        }

    // syntactic sugar
        public Identifier addIdentifier() { 
          Identifier t = new Identifier();
          this.identifier.add(t);
          return t;
        }

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
        }

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public Quantity getCapacity() { 
          return this.capacity;
        }

        public void setCapacity(Quantity value) { 
          this.capacity = value;
        }

        public Quantity getSpecimenQuantity() { 
          return this.specimenQuantity;
        }

        public void setSpecimenQuantity(Quantity value) { 
          this.specimenQuantity = value;
        }

        public ResourceReference getAdditive() { 
          return this.additive;
        }

        public void setAdditive(ResourceReference value) { 
          this.additive = value;
        }

      public SpecimenContainerComponent copy(Specimen e) {
        SpecimenContainerComponent dst = e.new SpecimenContainerComponent();
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

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
    }

    public List<SpecimenSourceComponent> getSource() { 
      return this.source;
    }

    // syntactic sugar
    public SpecimenSourceComponent addSource() { 
      SpecimenSourceComponent t = new SpecimenSourceComponent();
      this.source.add(t);
      return t;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public List<Identifier> getAccessionIdentifier() { 
      return this.accessionIdentifier;
    }

    // syntactic sugar
    public Identifier addAccessionIdentifier() { 
      Identifier t = new Identifier();
      this.accessionIdentifier.add(t);
      return t;
    }

    public DateTime getReceivedTime() { 
      return this.receivedTime;
    }

    public void setReceivedTime(DateTime value) { 
      this.receivedTime = value;
    }

    public String getReceivedTimeSimple() { 
      return this.receivedTime == null ? null : this.receivedTime.getValue();
    }

    public void setReceivedTimeSimple(String value) { 
      if (value == null)
        this.receivedTime = null;
      else {
        if (this.receivedTime == null)
          this.receivedTime = new DateTime();
        this.receivedTime.setValue(value);
      }
    }

    public SpecimenCollectionComponent getCollection() { 
      return this.collection;
    }

    public void setCollection(SpecimenCollectionComponent value) { 
      this.collection = value;
    }

    public List<SpecimenTreatmentComponent> getTreatment() { 
      return this.treatment;
    }

    // syntactic sugar
    public SpecimenTreatmentComponent addTreatment() { 
      SpecimenTreatmentComponent t = new SpecimenTreatmentComponent();
      this.treatment.add(t);
      return t;
    }

    public List<SpecimenContainerComponent> getContainer() { 
      return this.container;
    }

    // syntactic sugar
    public SpecimenContainerComponent addContainer() { 
      SpecimenContainerComponent t = new SpecimenContainerComponent();
      this.container.add(t);
      return t;
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

