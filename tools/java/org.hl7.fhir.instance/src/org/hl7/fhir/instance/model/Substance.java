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

// Generated on Wed, Oct 23, 2013 23:11+1100 for FHIR v0.12

import java.util.*;

/**
 * A homogeneous material with a definite composition used in healthcare.
 */
public class Substance extends Resource {

    /**
     * Identifier of the substance.
     */
    protected Identifier identifier;

    /**
     * Name of the substance.
     */
    protected String_ name;

    /**
     * Type of the substance.
     */
    protected CodeableConcept type;

    /**
     * Description of the substance.
     */
    protected String_ description;

    /**
     * Substance status.
     */
    protected CodeableConcept status;

    /**
     * When the substance is active or effective.
     */
    protected Period effectiveTime;

    /**
     * The amount of the substance.
     */
    protected Quantity quantity;

    /**
     * A substance can be composed of other substances.
     */
    protected List<ResourceReference> ingredient = new ArrayList<ResourceReference>();

    /**
     * Indicates whether the substance quantity (used for ingredients) are absolute values or values relative to each other (percentages).
     */
    protected CodeableConcept quantityMode;

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public String_ getName() { 
      return this.name;
    }

    public void setName(String_ value) { 
      this.name = value;
    }

    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    public void setNameSimple(String value) { 
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
    }

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
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

    public CodeableConcept getStatus() { 
      return this.status;
    }

    public void setStatus(CodeableConcept value) { 
      this.status = value;
    }

    public Period getEffectiveTime() { 
      return this.effectiveTime;
    }

    public void setEffectiveTime(Period value) { 
      this.effectiveTime = value;
    }

    public Quantity getQuantity() { 
      return this.quantity;
    }

    public void setQuantity(Quantity value) { 
      this.quantity = value;
    }

    public List<ResourceReference> getIngredient() { 
      return this.ingredient;
    }

    // syntactic sugar
    public ResourceReference addIngredient() { 
      ResourceReference t = new ResourceReference();
      this.ingredient.add(t);
      return t;
    }

    public CodeableConcept getQuantityMode() { 
      return this.quantityMode;
    }

    public void setQuantityMode(CodeableConcept value) { 
      this.quantityMode = value;
    }

      public Substance copy() {
        Substance dst = new Substance();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.name = name == null ? null : name.copy();
        dst.type = type == null ? null : type.copy();
        dst.description = description == null ? null : description.copy();
        dst.status = status == null ? null : status.copy();
        dst.effectiveTime = effectiveTime == null ? null : effectiveTime.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.ingredient = new ArrayList<ResourceReference>();
        for (ResourceReference i : ingredient)
          dst.ingredient.add(i.copy());
        dst.quantityMode = quantityMode == null ? null : quantityMode.copy();
        return dst;
      }

      protected Substance typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Substance;
   }


}

