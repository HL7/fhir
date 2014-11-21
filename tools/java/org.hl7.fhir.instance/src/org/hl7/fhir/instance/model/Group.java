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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * Represents a defined collection of entities that may be discussed or acted upon collectively but which are not expected to act collectively and are not formally or legally recognized.  I.e. A collection of entities that isn't an Organization.
 */
public class Group extends DomainResource {

    public enum GroupType {
        PERSON, // Group contains "person" Patient resources.
        ANIMAL, // Group contains "animal" Patient resources.
        PRACTITIONER, // Group contains healthcare practitioner resources.
        DEVICE, // Group contains Device resources.
        MEDICATION, // Group contains Medication resources.
        SUBSTANCE, // Group contains Substance resources.
        NULL; // added to help the parsers
        public static GroupType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("person".equals(codeString))
          return PERSON;
        if ("animal".equals(codeString))
          return ANIMAL;
        if ("practitioner".equals(codeString))
          return PRACTITIONER;
        if ("device".equals(codeString))
          return DEVICE;
        if ("medication".equals(codeString))
          return MEDICATION;
        if ("substance".equals(codeString))
          return SUBSTANCE;
        throw new Exception("Unknown GroupType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PERSON: return "person";
            case ANIMAL: return "animal";
            case PRACTITIONER: return "practitioner";
            case DEVICE: return "device";
            case MEDICATION: return "medication";
            case SUBSTANCE: return "substance";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PERSON: return "Group contains 'person' Patient resources.";
            case ANIMAL: return "Group contains 'animal' Patient resources.";
            case PRACTITIONER: return "Group contains healthcare practitioner resources.";
            case DEVICE: return "Group contains Device resources.";
            case MEDICATION: return "Group contains Medication resources.";
            case SUBSTANCE: return "Group contains Substance resources.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PERSON: return "person";
            case ANIMAL: return "animal";
            case PRACTITIONER: return "practitioner";
            case DEVICE: return "device";
            case MEDICATION: return "medication";
            case SUBSTANCE: return "substance";
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
          return GroupType.PERSON;
        if ("animal".equals(codeString))
          return GroupType.ANIMAL;
        if ("practitioner".equals(codeString))
          return GroupType.PRACTITIONER;
        if ("device".equals(codeString))
          return GroupType.DEVICE;
        if ("medication".equals(codeString))
          return GroupType.MEDICATION;
        if ("substance".equals(codeString))
          return GroupType.SUBSTANCE;
        throw new Exception("Unknown GroupType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == GroupType.PERSON)
        return "person";
      if (code == GroupType.ANIMAL)
        return "animal";
      if (code == GroupType.PRACTITIONER)
        return "practitioner";
      if (code == GroupType.DEVICE)
        return "device";
      if (code == GroupType.MEDICATION)
        return "medication";
      if (code == GroupType.SUBSTANCE)
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
        protected BooleanType exclude;

        private static final long serialVersionUID = 803478031L;

      public GroupCharacteristicComponent() {
        super();
      }

      public GroupCharacteristicComponent(CodeableConcept code, Type value, BooleanType exclude) {
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
         * @return {@link #exclude} (If true, indicates the characteristic is one that is NOT held by members of the group.). This is the underlying object with id, value and extensions. The accessor "getExclude" gives direct access to the value
         */
        public BooleanType getExcludeElement() { 
          return this.exclude;
        }

        /**
         * @param value {@link #exclude} (If true, indicates the characteristic is one that is NOT held by members of the group.). This is the underlying object with id, value and extensions. The accessor "getExclude" gives direct access to the value
         */
        public GroupCharacteristicComponent setExcludeElement(BooleanType value) { 
          this.exclude = value;
          return this;
        }

        /**
         * @return If true, indicates the characteristic is one that is NOT held by members of the group.
         */
        public boolean getExclude() { 
          return this.exclude == null ? false : this.exclude.getValue();
        }

        /**
         * @param value If true, indicates the characteristic is one that is NOT held by members of the group.
         */
        public GroupCharacteristicComponent setExclude(boolean value) { 
            if (this.exclude == null)
              this.exclude = new BooleanType();
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
        copyValues(dst);
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
    protected BooleanType actual;

    /**
     * Provides a specific type of resource the group includes.  E.g. "cow", "syringe", etc.
     */
    protected CodeableConcept code;

    /**
     * A label assigned to the group for human identification and communication.
     */
    protected StringType name;

    /**
     * A count of the number of resource instances that are part of the group.
     */
    protected IntegerType quantity;

    /**
     * Identifies the traits shared by members of the group.
     */
    protected List<GroupCharacteristicComponent> characteristic = new ArrayList<GroupCharacteristicComponent>();

    /**
     * Identifies the resource instances that are members of the group.
     */
    protected List<Reference> member = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Identifies the resource instances that are members of the group.)
     */
    protected List<Resource> memberTarget = new ArrayList<Resource>();


    private static final long serialVersionUID = 1477460940L;

    public Group() {
      super();
    }

    public Group(Enumeration<GroupType> type, BooleanType actual) {
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
     * @return {@link #type} (Identifies the broad classification of the kind of resources the group includes.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Enumeration<GroupType> getTypeElement() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Identifies the broad classification of the kind of resources the group includes.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Group setTypeElement(Enumeration<GroupType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return Identifies the broad classification of the kind of resources the group includes.
     */
    public GroupType getType() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value Identifies the broad classification of the kind of resources the group includes.
     */
    public Group setType(GroupType value) { 
        if (this.type == null)
          this.type = new Enumeration<GroupType>();
        this.type.setValue(value);
      return this;
    }

    /**
     * @return {@link #actual} (If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.). This is the underlying object with id, value and extensions. The accessor "getActual" gives direct access to the value
     */
    public BooleanType getActualElement() { 
      return this.actual;
    }

    /**
     * @param value {@link #actual} (If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.). This is the underlying object with id, value and extensions. The accessor "getActual" gives direct access to the value
     */
    public Group setActualElement(BooleanType value) { 
      this.actual = value;
      return this;
    }

    /**
     * @return If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.
     */
    public boolean getActual() { 
      return this.actual == null ? false : this.actual.getValue();
    }

    /**
     * @param value If true, indicates that the resource refers to a specific group of real individuals.  If false, the group defines a set of intended individuals.
     */
    public Group setActual(boolean value) { 
        if (this.actual == null)
          this.actual = new BooleanType();
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
     * @return {@link #name} (A label assigned to the group for human identification and communication.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A label assigned to the group for human identification and communication.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public Group setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A label assigned to the group for human identification and communication.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A label assigned to the group for human identification and communication.
     */
    public Group setName(String value) { 
      if (Utilities.noString(value))
        this.name = null;
      else {
        if (this.name == null)
          this.name = new StringType();
        this.name.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #quantity} (A count of the number of resource instances that are part of the group.). This is the underlying object with id, value and extensions. The accessor "getQuantity" gives direct access to the value
     */
    public IntegerType getQuantityElement() { 
      return this.quantity;
    }

    /**
     * @param value {@link #quantity} (A count of the number of resource instances that are part of the group.). This is the underlying object with id, value and extensions. The accessor "getQuantity" gives direct access to the value
     */
    public Group setQuantityElement(IntegerType value) { 
      this.quantity = value;
      return this;
    }

    /**
     * @return A count of the number of resource instances that are part of the group.
     */
    public int getQuantity() { 
      return this.quantity == null ? null : this.quantity.getValue();
    }

    /**
     * @param value A count of the number of resource instances that are part of the group.
     */
    public Group setQuantity(int value) { 
      if (value == -1)
        this.quantity = null;
      else {
        if (this.quantity == null)
          this.quantity = new IntegerType();
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

    /**
     * @return {@link #characteristic} (Identifies the traits shared by members of the group.)
     */
    // syntactic sugar
    public GroupCharacteristicComponent addCharacteristic() { //3
      GroupCharacteristicComponent t = new GroupCharacteristicComponent();
      this.characteristic.add(t);
      return t;
    }

    /**
     * @return {@link #member} (Identifies the resource instances that are members of the group.)
     */
    public List<Reference> getMember() { 
      return this.member;
    }

    /**
     * @return {@link #member} (Identifies the resource instances that are members of the group.)
     */
    // syntactic sugar
    public Reference addMember() { //3
      Reference t = new Reference();
      this.member.add(t);
      return t;
    }

    /**
     * @return {@link #member} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Identifies the resource instances that are members of the group.)
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
        childrenList.add(new Property("member", "Reference(Patient|Practitioner|Device|Medication|Substance)", "Identifies the resource instances that are members of the group.", 0, java.lang.Integer.MAX_VALUE, member));
      }

      public Group copy() {
        Group dst = new Group();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.type = type == null ? null : type.copy();
        dst.actual = actual == null ? null : actual.copy();
        dst.code = code == null ? null : code.copy();
        dst.name = name == null ? null : name.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.characteristic = new ArrayList<GroupCharacteristicComponent>();
        for (GroupCharacteristicComponent i : characteristic)
          dst.characteristic.add(i.copy());
        dst.member = new ArrayList<Reference>();
        for (Reference i : member)
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

