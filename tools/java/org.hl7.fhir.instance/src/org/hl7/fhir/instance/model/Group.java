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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

import java.util.*;

/**
 * Represents a defined collection of entities that may be discussed or acted upon collectively but which are not expected to act collectively and are not formally or legally recognized.  I.e. A collection of entities that isn't an Organization.
 */
public class Group extends Resource {

    public enum GroupType {
        person, // Group contains "person" Patient resources.
        animal, // Group contains "animal" Patient resources.
        practitioner, // Group contains healthcare practitioner resources.
        device, // Group contains Device resources.
        medication, // Group contains Medication resources.
        substance, // Group contains Substance resources.
        Null; // added to help the parsers
        public static GroupType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("person".equals(codeString))
          return person;
        if ("animal".equals(codeString))
          return animal;
        if ("practitioner".equals(codeString))
          return practitioner;
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
            case practitioner: return "practitioner";
            case device: return "device";
            case medication: return "medication";
            case substance: return "substance";
            default: return "?";
          }
        }
    }

  public static class GroupTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("person".equals(codeString))
          return GroupType.person;
        if ("animal".equals(codeString))
          return GroupType.animal;
        if ("practitioner".equals(codeString))
          return GroupType.practitioner;
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
      if (code == GroupType.practitioner)
        return "practitioner";
      if (code == GroupType.device)
        return "device";
      if (code == GroupType.medication)
        return "medication";
      if (code == GroupType.substance)
        return "substance";
      return "?";
      }
    }

    public static class GroupCharacteristicComponent extends BackboneElement {
        /**
         * A code that identifies the kind of trait being asserted.
         */
        protected CodeableConcept code;

        /**
         * The value of the trait that holds (or does not hold - see 'exclude') for members of the group.
         */
        protected Type value;

        /**
         * If true, indicates the characteristic is one that is NOT held by members of the group.
         */
        protected Boolean exclude;

        private static final long serialVersionUID = 1508824873L;

      public GroupCharacteristicComponent() {
        super();
      }

      public GroupCharacteristicComponent(CodeableConcept code, Type value, Boolean exclude) {
        super();
        this.code = code;
        this.value = value;
        this.exclude = exclude;
      }

        /**
         * @return {@link #code} (A code that identifies the kind of trait being asserted.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A code that identifies the kind of trait being asserted.)
         */
        public GroupCharacteristicComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #value} (The value of the trait that holds (or does not hold - see 'exclude') for members of the group.)
         */
        public Type getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (The value of the trait that holds (or does not hold - see 'exclude') for members of the group.)
         */
        public GroupCharacteristicComponent setValue(Type value) { 
          this.value = value;
          return this;
        }

        /**
         * @return {@link #exclude} (If true, indicates the characteristic is one that is NOT held by members of the group.)
         */
        public Boolean getExclude() { 
          return this.exclude;
        }

        /**
         * @param value {@link #exclude} (If true, indicates the characteristic is one that is NOT held by members of the group.)
         */
        public GroupCharacteristicComponent setExclude(Boolean value) { 
          this.exclude = value;
          return this;
        }

        /**
         * @return If true, indicates the characteristic is one that is NOT held by members of the group.
         */
        public boolean getExcludeSimple() { 
          return this.exclude == null ? false : this.exclude.getValue();
        }

        /**
         * @param value If true, indicates the characteristic is one that is NOT held by members of the group.
         */
        public GroupCharacteristicComponent setExcludeSimple(boolean value) { 
            if (this.exclude == null)
              this.exclude = new Boolean();
            this.exclude.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "A code that identifies the kind of trait being asserted.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("value[x]", "CodeableConcept|boolean|Quantity|Range", "The value of the trait that holds (or does not hold - see 'exclude') for members of the group.", 0, java.lang.Integer.MAX_VALUE, value));
          childrenList.add(new Property("exclude", "boolean", "If true, indicates the characteristic is one that is NOT held by members of the group.", 0, java.lang.Integer.MAX_VALUE, exclude));
        }

      public GroupCharacteristicComponent copy() {
        GroupCharacteristicComponent dst = new GroupCharacteristicComponent();
        dst.code = code == null ? null : code.copy();
        dst.value = value == null ? null : value.copy();
        dst.exclude = exclude == null ? null : exclude.copy();
        return dst;
      }

  }

    /**
     * A unique business identifier for this group.
     */
    protected Identifier identifier;

    /**
     * Identifies the broad classification of the kind of resources the group includes.
     */
    protected Enumeration<GroupType> type;

    /**
     * If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.
     */
    protected Boolean actual;

    /**
     * Provides a specific type of resource the group includes.  E.g. "cow", "syringe", etc.
     */
    protected CodeableConcept code;

    /**
     * A label assigned to the group for human identification and communication.
     */
    protected String_ name;

    /**
     * A count of the number of resource instances that are part of the group.
     */
    protected Integer quantity;

    /**
     * Identifies the traits shared by members of the group.
     */
    protected List<GroupCharacteristicComponent> characteristic = new ArrayList<GroupCharacteristicComponent>();

    /**
     * Identifies the resource instances that are members of the group.
     */
    protected List<ResourceReference> member = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Identifies the resource instances that are members of the group.)
     */
    protected List<Resource> memberTarget = new ArrayList<Resource>();


    private static final long serialVersionUID = 121382617L;

    public Group() {
      super();
    }

    public Group(Enumeration<GroupType> type, Boolean actual) {
      super();
      this.type = type;
      this.actual = actual;
    }

    /**
     * @return {@link #identifier} (A unique business identifier for this group.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (A unique business identifier for this group.)
     */
    public Group setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #type} (Identifies the broad classification of the kind of resources the group includes.)
     */
    public Enumeration<GroupType> getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Identifies the broad classification of the kind of resources the group includes.)
     */
    public Group setType(Enumeration<GroupType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return Identifies the broad classification of the kind of resources the group includes.
     */
    public GroupType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value Identifies the broad classification of the kind of resources the group includes.
     */
    public Group setTypeSimple(GroupType value) { 
        if (this.type == null)
          this.type = new Enumeration<GroupType>();
        this.type.setValue(value);
      return this;
    }

    /**
     * @return {@link #actual} (If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.)
     */
    public Boolean getActual() { 
      return this.actual;
    }

    /**
     * @param value {@link #actual} (If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.)
     */
    public Group setActual(Boolean value) { 
      this.actual = value;
      return this;
    }

    /**
     * @return If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.
     */
    public boolean getActualSimple() { 
      return this.actual == null ? false : this.actual.getValue();
    }

    /**
     * @param value If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.
     */
    public Group setActualSimple(boolean value) { 
        if (this.actual == null)
          this.actual = new Boolean();
        this.actual.setValue(value);
      return this;
    }

    /**
     * @return {@link #code} (Provides a specific type of resource the group includes.  E.g. "cow", "syringe", etc.)
     */
    public CodeableConcept getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (Provides a specific type of resource the group includes.  E.g. "cow", "syringe", etc.)
     */
    public Group setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    /**
     * @return {@link #name} (A label assigned to the group for human identification and communication.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A label assigned to the group for human identification and communication.)
     */
    public Group setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A label assigned to the group for human identification and communication.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A label assigned to the group for human identification and communication.
     */
    public Group setNameSimple(String value) { 
      if (value == null)
        this.name = null;
      else {
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #quantity} (A count of the number of resource instances that are part of the group.)
     */
    public Integer getQuantity() { 
      return this.quantity;
    }

    /**
     * @param value {@link #quantity} (A count of the number of resource instances that are part of the group.)
     */
    public Group setQuantity(Integer value) { 
      this.quantity = value;
      return this;
    }

    /**
     * @return A count of the number of resource instances that are part of the group.
     */
    public int getQuantitySimple() { 
      return this.quantity == null ? null : this.quantity.getValue();
    }

    /**
     * @param value A count of the number of resource instances that are part of the group.
     */
    public Group setQuantitySimple(int value) { 
      if (value == -1)
        this.quantity = null;
      else {
        if (this.quantity == null)
          this.quantity = new Integer();
        this.quantity.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #characteristic} (Identifies the traits shared by members of the group.)
     */
    public List<GroupCharacteristicComponent> getCharacteristic() { 
      return this.characteristic;
    }

    // syntactic sugar
    /**
     * @return {@link #characteristic} (Identifies the traits shared by members of the group.)
     */
    public GroupCharacteristicComponent addCharacteristic() { 
      GroupCharacteristicComponent t = new GroupCharacteristicComponent();
      this.characteristic.add(t);
      return t;
    }

    /**
     * @return {@link #member} (Identifies the resource instances that are members of the group.)
     */
    public List<ResourceReference> getMember() { 
      return this.member;
    }

    // syntactic sugar
    /**
     * @return {@link #member} (Identifies the resource instances that are members of the group.)
     */
    public ResourceReference addMember() { 
      ResourceReference t = new ResourceReference();
      this.member.add(t);
      return t;
    }

    /**
     * @return {@link #member} (The actual objects that are the target of the reference. Identifies the resource instances that are members of the group.)
     */
    public List<Resource> getMemberTarget() { 
      return this.memberTarget;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "A unique business identifier for this group.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "code", "Identifies the broad classification of the kind of resources the group includes.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("actual", "boolean", "If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.", 0, java.lang.Integer.MAX_VALUE, actual));
        childrenList.add(new Property("code", "CodeableConcept", "Provides a specific type of resource the group includes.  E.g. 'cow', 'syringe', etc.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("name", "string", "A label assigned to the group for human identification and communication.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("quantity", "integer", "A count of the number of resource instances that are part of the group.", 0, java.lang.Integer.MAX_VALUE, quantity));
        childrenList.add(new Property("characteristic", "", "Identifies the traits shared by members of the group.", 0, java.lang.Integer.MAX_VALUE, characteristic));
        childrenList.add(new Property("member", "Resource(Patient|Practitioner|Device|Medication|Substance)", "Identifies the resource instances that are members of the group.", 0, java.lang.Integer.MAX_VALUE, member));
      }

      public Group copy() {
        Group dst = new Group();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = type == null ? null : type.copy();
        dst.actual = actual == null ? null : actual.copy();
        dst.code = code == null ? null : code.copy();
        dst.name = name == null ? null : name.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.characteristic = new ArrayList<GroupCharacteristicComponent>();
        for (GroupCharacteristicComponent i : characteristic)
          dst.characteristic.add(i.copy());
        dst.member = new ArrayList<ResourceReference>();
        for (ResourceReference i : member)
          dst.member.add(i.copy());
        return dst;
      }

      protected Group typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Group;
   }


}

