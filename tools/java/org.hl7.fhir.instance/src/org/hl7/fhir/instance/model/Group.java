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

// Generated on Tue, May 21, 2013 08:39+1000 for FHIR v0.09

import java.util.*;

/**
 * Represents a defined collection of entities that may be discussed or acted upon collectively but which are not expected to act collectively and are not formally or legally recognized.  I.e. A collection of entities that isn't an Organization
 */
public class Group extends Resource {

    public enum GroupType {
        person, // Group contains "person" Patient resources
        animal, // Group contains "animal" Patient resources
        device, // Group contains Device resources
        medication, // Group contains Medication resources
        substance, // Group contains Substance resources
        Null; // added to help the parsers
        public static GroupType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("person".equals(codeString))
          return person;
        if ("animal".equals(codeString))
          return animal;
        if ("device".equals(codeString))
          return device;
        if ("medication".equals(codeString))
          return medication;
        if ("substance".equals(codeString))
          return substance;
        throw new Exception("Unknown GroupType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case person: return "person";
            case animal: return "animal";
            case device: return "device";
            case medication: return "medication";
            case substance: return "substance";
            default: return "?";
          }
        }
    }

  public class GroupTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("person".equals(codeString))
          return GroupType.person;
        if ("animal".equals(codeString))
          return GroupType.animal;
        if ("device".equals(codeString))
          return GroupType.device;
        if ("medication".equals(codeString))
          return GroupType.medication;
        if ("substance".equals(codeString))
          return GroupType.substance;
        throw new Exception("Unknown GroupType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == GroupType.person)
        return "person";
      if (code == GroupType.animal)
        return "animal";
      if (code == GroupType.device)
        return "device";
      if (code == GroupType.medication)
        return "medication";
      if (code == GroupType.substance)
        return "substance";
      return "?";
      }
    }

    public class GroupCharacteristicComponent extends Element {
        /**
         * Identifies the kind of trait being asserted
         */
        private CodeableConcept type;

        /**
         * The value of the trait that holds (or does not hold - see 'exclude') for members of the group
         */
        private Type value;

        /**
         * If true, indicates the characteristic is one that is NOT held by members of the group
         */
        private Boolean exclude;

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public Type getValue() { 
          return this.value;
        }

        public void setValue(Type value) { 
          this.value = value;
        }

        public Boolean getExclude() { 
          return this.exclude;
        }

        public void setExclude(Boolean value) { 
          this.exclude = value;
        }

        public boolean getExcludeSimple() { 
          return this.exclude == null ? null : this.exclude.getValue();
        }

        public void setExcludeSimple(boolean value) { 
            if (this.exclude == null)
              this.exclude = new Boolean();
            this.exclude.setValue(value);
        }

  }

    /**
     * A unique business identifier for this group
     */
    private Identifier identifier;

    /**
     * Identifies the broad classification of the kind of resources the group includes
     */
    private Enumeration<GroupType> type;

    /**
     * If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals
     */
    private Boolean actual;

    /**
     * Provides a specific type of resource the group includes.  E.g. "cow", "syringe", etc.
     */
    private CodeableConcept code;

    /**
     * A label assigned to the group for human identification and communication
     */
    private String_ name;

    /**
     * A count of the number of resource instances that are part of the group
     */
    private Integer quantity;

    /**
     * Identifies the traits shared by members of the group
     */
    private List<GroupCharacteristicComponent> characteristic = new ArrayList<GroupCharacteristicComponent>();

    /**
     * Identifies the resource instances that are members of the group.
     */
    private List<ResourceReference> member = new ArrayList<ResourceReference>();

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public Enumeration<GroupType> getType() { 
      return this.type;
    }

    public void setType(Enumeration<GroupType> value) { 
      this.type = value;
    }

    public GroupType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    public void setTypeSimple(GroupType value) { 
        if (this.type == null)
          this.type = new Enumeration<GroupType>();
        this.type.setValue(value);
    }

    public Boolean getActual() { 
      return this.actual;
    }

    public void setActual(Boolean value) { 
      this.actual = value;
    }

    public boolean getActualSimple() { 
      return this.actual == null ? null : this.actual.getValue();
    }

    public void setActualSimple(boolean value) { 
        if (this.actual == null)
          this.actual = new Boolean();
        this.actual.setValue(value);
    }

    public CodeableConcept getCode() { 
      return this.code;
    }

    public void setCode(CodeableConcept value) { 
      this.code = value;
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
      if (value == null)
        this.name = null;
      else {
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      }
    }

    public Integer getQuantity() { 
      return this.quantity;
    }

    public void setQuantity(Integer value) { 
      this.quantity = value;
    }

    public int getQuantitySimple() { 
      return this.quantity == null ? null : this.quantity.getValue();
    }

    public void setQuantitySimple(int value) { 
      if (value == -1)
        this.quantity = null;
      else {
        if (this.quantity == null)
          this.quantity = new Integer();
        this.quantity.setValue(value);
      }
    }

    public List<GroupCharacteristicComponent> getCharacteristic() { 
      return this.characteristic;
    }

    public List<ResourceReference> getMember() { 
      return this.member;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Group;
   }


}

