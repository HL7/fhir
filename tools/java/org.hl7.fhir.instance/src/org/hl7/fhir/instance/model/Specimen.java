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

// Generated on Tue, Jul 2, 2013 18:37+1000 for FHIR v0.09

import java.util.*;

/**
 * Sample for analysis
 */
public class Specimen extends Resource {

    public class SpecimenSourceComponent extends Element {
        /**
         * Parent | Child
         */
        protected Code relationship;

        /**
         * The specimen resource that is the target of this relationship
         */
        protected List<ResourceReference> target = new ArrayList<ResourceReference>();

        public Code getRelationship() { 
          return this.relationship;
        }

        public void setRelationship(Code value) { 
          this.relationship = value;
        }

        public String getRelationshipSimple() { 
          return this.relationship == null ? null : this.relationship.getValue();
        }

        public void setRelationshipSimple(String value) { 
            if (this.relationship == null)
              this.relationship = new Code();
            this.relationship.setValue(value);
        }

        public List<ResourceReference> getTarget() { 
          return this.target;
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
         * Person who collected the specimen
         */
        protected ResourceReference collector;

        /**
         * To communicate any details or issues encountered during the specimen collection procedure.
         */
        protected List<String_> comments = new ArrayList<String_>();

        /**
         * Collection time
         */
        protected DateTime collectedTime;

        /**
         * The quantity of speciment collected
         */
        protected Quantity quantity;

        public ResourceReference getCollector() { 
          return this.collector;
        }

        public void setCollector(ResourceReference value) { 
          this.collector = value;
        }

        public List<String_> getComments() { 
          return this.comments;
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
          if (value == null)
            this.collectedTime = null;
          else {
            if (this.collectedTime == null)
              this.collectedTime = new DateTime();
            this.collectedTime.setValue(value);
          }
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public void setQuantity(Quantity value) { 
          this.quantity = value;
        }

      public SpecimenCollectionComponent copy(Specimen e) {
        SpecimenCollectionComponent dst = e.new SpecimenCollectionComponent();
        dst.collector = collector == null ? null : collector.copy();
        dst.comments = new ArrayList<String_>();
        for (String_ i : comments)
          dst.comments.add(i.copy());
        dst.collectedTime = collectedTime == null ? null : collectedTime.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        return dst;
      }

  }

    /**
     * Id for specimen
     */
    protected Identifier identifier;

    /**
     * Category of specimen material
     */
    protected CodeableConcept type;

    /**
     * Parent specimen from which the focal specimen was a component
     */
    protected List<SpecimenSourceComponent> source = new ArrayList<SpecimenSourceComponent>();

    /**
     * The subject of the report
     */
    protected ResourceReference subject;

    /**
     * The identifier(s) assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.
     */
    protected List<Identifier> accessionIdentifier = new ArrayList<Identifier>();

    /**
     * Details concerning the specimen collection
     */
    protected SpecimenCollectionComponent collection;

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

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public List<Identifier> getAccessionIdentifier() { 
      return this.accessionIdentifier;
    }

    public SpecimenCollectionComponent getCollection() { 
      return this.collection;
    }

    public void setCollection(SpecimenCollectionComponent value) { 
      this.collection = value;
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
        dst.collection = collection == null ? null : collection.copy(dst);
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

