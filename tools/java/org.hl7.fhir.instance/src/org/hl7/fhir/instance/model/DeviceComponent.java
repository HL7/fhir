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
 * Describes the characteristics, operational status and capabilities of a medical-related component of a medical device.
 */
public class DeviceComponent extends DomainResource {

    public static class DeviceComponentProductionSpecificationComponent extends BackboneElement {
        /**
         * Describes the specification type, such as, serial number, part number, hardware revision, software revision, etc.
         */
        protected CodeableConcept specType;

        /**
         * Describes the internal component unique identification. This is a provision for manufacture specific standard components using a private OID. 11073-10101 has a partition for private OID semantic that the manufacture can make use of.
         */
        protected Identifier componentId;

        /**
         * Describes the printable string defining the component.
         */
        protected StringType productionSpec;

        private static final long serialVersionUID = -1476597516L;

      public DeviceComponentProductionSpecificationComponent() {
        super();
      }

        /**
         * @return {@link #specType} (Describes the specification type, such as, serial number, part number, hardware revision, software revision, etc.)
         */
        public CodeableConcept getSpecType() { 
          return this.specType;
        }

        /**
         * @param value {@link #specType} (Describes the specification type, such as, serial number, part number, hardware revision, software revision, etc.)
         */
        public DeviceComponentProductionSpecificationComponent setSpecType(CodeableConcept value) { 
          this.specType = value;
          return this;
        }

        /**
         * @return {@link #componentId} (Describes the internal component unique identification. This is a provision for manufacture specific standard components using a private OID. 11073-10101 has a partition for private OID semantic that the manufacture can make use of.)
         */
        public Identifier getComponentId() { 
          return this.componentId;
        }

        /**
         * @param value {@link #componentId} (Describes the internal component unique identification. This is a provision for manufacture specific standard components using a private OID. 11073-10101 has a partition for private OID semantic that the manufacture can make use of.)
         */
        public DeviceComponentProductionSpecificationComponent setComponentId(Identifier value) { 
          this.componentId = value;
          return this;
        }

        /**
         * @return {@link #productionSpec} (Describes the printable string defining the component.). This is the underlying object with id, value and extensions. The accessor "getProductionSpec" gives direct access to the value
         */
        public StringType getProductionSpecElement() { 
          return this.productionSpec;
        }

        /**
         * @param value {@link #productionSpec} (Describes the printable string defining the component.). This is the underlying object with id, value and extensions. The accessor "getProductionSpec" gives direct access to the value
         */
        public DeviceComponentProductionSpecificationComponent setProductionSpecElement(StringType value) { 
          this.productionSpec = value;
          return this;
        }

        /**
         * @return Describes the printable string defining the component.
         */
        public String getProductionSpec() { 
          return this.productionSpec == null ? null : this.productionSpec.getValue();
        }

        /**
         * @param value Describes the printable string defining the component.
         */
        public DeviceComponentProductionSpecificationComponent setProductionSpec(String value) { 
          if (Utilities.noString(value))
            this.productionSpec = null;
          else {
            if (this.productionSpec == null)
              this.productionSpec = new StringType();
            this.productionSpec.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("specType", "CodeableConcept", "Describes the specification type, such as, serial number, part number, hardware revision, software revision, etc.", 0, java.lang.Integer.MAX_VALUE, specType));
          childrenList.add(new Property("componentId", "Identifier", "Describes the internal component unique identification. This is a provision for manufacture specific standard components using a private OID. 11073-10101 has a partition for private OID semantic that the manufacture can make use of.", 0, java.lang.Integer.MAX_VALUE, componentId));
          childrenList.add(new Property("productionSpec", "string", "Describes the printable string defining the component.", 0, java.lang.Integer.MAX_VALUE, productionSpec));
        }

      public DeviceComponentProductionSpecificationComponent copy() {
        DeviceComponentProductionSpecificationComponent dst = new DeviceComponentProductionSpecificationComponent();
        copyValues(dst);
        dst.specType = specType == null ? null : specType.copy();
        dst.componentId = componentId == null ? null : componentId.copy();
        dst.productionSpec = productionSpec == null ? null : productionSpec.copy();
        return dst;
      }

  }

    /**
     * Describes the specific component type as defined in the object-oriented or metric nomenclature partition.
     */
    protected CodeableConcept type;

    /**
     * Describes the local assigned unique identification by the software. For example: handle ID.
     */
    protected Identifier identifier;

    /**
     * Describes the timestamp for the most recent system change which includes device configuration or setting change.
     */
    protected InstantType lastSystemChange;

    /**
     * Describes the link to the source Device that contains administrative device information such as manufacture, serial number, etc.
     */
    protected Reference source;

    /**
     * The actual object that is the target of the reference (Describes the link to the source Device that contains administrative device information such as manufacture, serial number, etc.)
     */
    protected Device sourceTarget;

    /**
     * Describes the link to the parent resource. For example: Channel is linked to its VMD parent.
     */
    protected Reference parent;

    /**
     * The actual object that is the target of the reference (Describes the link to the parent resource. For example: Channel is linked to its VMD parent.)
     */
    protected DeviceComponent parentTarget;

    /**
     * Indicates current operational status of the device. For example: On, Off, Standby, etc.
     */
    protected List<CodeableConcept> operationalStatus = new ArrayList<CodeableConcept>();

    /**
     * Describes the parameter group supported by the current device component that is based on some nomenclature, e.g., cardiovascular.
     */
    protected CodeableConcept parameterGroup;

    /**
     * Describes the physical principle of the measurement. For example: thermal, chemical, acoustical, etc.
     */
    protected CodeableConcept measurementPrinciple;

    /**
     * Describes the production specification such as component revision, serial number, etc.
     */
    protected List<DeviceComponentProductionSpecificationComponent> productionSpecification = new ArrayList<DeviceComponentProductionSpecificationComponent>();

    /**
     * Describes the language code for the human-readable text string produced by the device. This language code will follow the IETF language tag. Example: en-US.
     */
    protected CodeableConcept languageCode;

    private static final long serialVersionUID = 1891871090L;

    public DeviceComponent() {
      super();
    }

    public DeviceComponent(CodeableConcept type, Identifier identifier, InstantType lastSystemChange) {
      super();
      this.type = type;
      this.identifier = identifier;
      this.lastSystemChange = lastSystemChange;
    }

    /**
     * @return {@link #type} (Describes the specific component type as defined in the object-oriented or metric nomenclature partition.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Describes the specific component type as defined in the object-oriented or metric nomenclature partition.)
     */
    public DeviceComponent setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Describes the local assigned unique identification by the software. For example: handle ID.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Describes the local assigned unique identification by the software. For example: handle ID.)
     */
    public DeviceComponent setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #lastSystemChange} (Describes the timestamp for the most recent system change which includes device configuration or setting change.). This is the underlying object with id, value and extensions. The accessor "getLastSystemChange" gives direct access to the value
     */
    public InstantType getLastSystemChangeElement() { 
      return this.lastSystemChange;
    }

    /**
     * @param value {@link #lastSystemChange} (Describes the timestamp for the most recent system change which includes device configuration or setting change.). This is the underlying object with id, value and extensions. The accessor "getLastSystemChange" gives direct access to the value
     */
    public DeviceComponent setLastSystemChangeElement(InstantType value) { 
      this.lastSystemChange = value;
      return this;
    }

    /**
     * @return Describes the timestamp for the most recent system change which includes device configuration or setting change.
     */
    public DateAndTime getLastSystemChange() { 
      return this.lastSystemChange == null ? null : this.lastSystemChange.getValue();
    }

    /**
     * @param value Describes the timestamp for the most recent system change which includes device configuration or setting change.
     */
    public DeviceComponent setLastSystemChange(DateAndTime value) { 
        if (this.lastSystemChange == null)
          this.lastSystemChange = new InstantType();
        this.lastSystemChange.setValue(value);
      return this;
    }

    /**
     * @return {@link #source} (Describes the link to the source Device that contains administrative device information such as manufacture, serial number, etc.)
     */
    public Reference getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (Describes the link to the source Device that contains administrative device information such as manufacture, serial number, etc.)
     */
    public DeviceComponent setSource(Reference value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #source} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Describes the link to the source Device that contains administrative device information such as manufacture, serial number, etc.)
     */
    public Device getSourceTarget() { 
      return this.sourceTarget;
    }

    /**
     * @param value {@link #source} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Describes the link to the source Device that contains administrative device information such as manufacture, serial number, etc.)
     */
    public DeviceComponent setSourceTarget(Device value) { 
      this.sourceTarget = value;
      return this;
    }

    /**
     * @return {@link #parent} (Describes the link to the parent resource. For example: Channel is linked to its VMD parent.)
     */
    public Reference getParent() { 
      return this.parent;
    }

    /**
     * @param value {@link #parent} (Describes the link to the parent resource. For example: Channel is linked to its VMD parent.)
     */
    public DeviceComponent setParent(Reference value) { 
      this.parent = value;
      return this;
    }

    /**
     * @return {@link #parent} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Describes the link to the parent resource. For example: Channel is linked to its VMD parent.)
     */
    public DeviceComponent getParentTarget() { 
      return this.parentTarget;
    }

    /**
     * @param value {@link #parent} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Describes the link to the parent resource. For example: Channel is linked to its VMD parent.)
     */
    public DeviceComponent setParentTarget(DeviceComponent value) { 
      this.parentTarget = value;
      return this;
    }

    /**
     * @return {@link #operationalStatus} (Indicates current operational status of the device. For example: On, Off, Standby, etc.)
     */
    public List<CodeableConcept> getOperationalStatus() { 
      return this.operationalStatus;
    }

    /**
     * @return {@link #operationalStatus} (Indicates current operational status of the device. For example: On, Off, Standby, etc.)
     */
    // syntactic sugar
    public CodeableConcept addOperationalStatus() { //3
      CodeableConcept t = new CodeableConcept();
      this.operationalStatus.add(t);
      return t;
    }

    /**
     * @return {@link #parameterGroup} (Describes the parameter group supported by the current device component that is based on some nomenclature, e.g., cardiovascular.)
     */
    public CodeableConcept getParameterGroup() { 
      return this.parameterGroup;
    }

    /**
     * @param value {@link #parameterGroup} (Describes the parameter group supported by the current device component that is based on some nomenclature, e.g., cardiovascular.)
     */
    public DeviceComponent setParameterGroup(CodeableConcept value) { 
      this.parameterGroup = value;
      return this;
    }

    /**
     * @return {@link #measurementPrinciple} (Describes the physical principle of the measurement. For example: thermal, chemical, acoustical, etc.)
     */
    public CodeableConcept getMeasurementPrinciple() { 
      return this.measurementPrinciple;
    }

    /**
     * @param value {@link #measurementPrinciple} (Describes the physical principle of the measurement. For example: thermal, chemical, acoustical, etc.)
     */
    public DeviceComponent setMeasurementPrinciple(CodeableConcept value) { 
      this.measurementPrinciple = value;
      return this;
    }

    /**
     * @return {@link #productionSpecification} (Describes the production specification such as component revision, serial number, etc.)
     */
    public List<DeviceComponentProductionSpecificationComponent> getProductionSpecification() { 
      return this.productionSpecification;
    }

    /**
     * @return {@link #productionSpecification} (Describes the production specification such as component revision, serial number, etc.)
     */
    // syntactic sugar
    public DeviceComponentProductionSpecificationComponent addProductionSpecification() { //3
      DeviceComponentProductionSpecificationComponent t = new DeviceComponentProductionSpecificationComponent();
      this.productionSpecification.add(t);
      return t;
    }

    /**
     * @return {@link #languageCode} (Describes the language code for the human-readable text string produced by the device. This language code will follow the IETF language tag. Example: en-US.)
     */
    public CodeableConcept getLanguageCode() { 
      return this.languageCode;
    }

    /**
     * @param value {@link #languageCode} (Describes the language code for the human-readable text string produced by the device. This language code will follow the IETF language tag. Example: en-US.)
     */
    public DeviceComponent setLanguageCode(CodeableConcept value) { 
      this.languageCode = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("type", "CodeableConcept", "Describes the specific component type as defined in the object-oriented or metric nomenclature partition.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("identifier", "Identifier", "Describes the local assigned unique identification by the software. For example: handle ID.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("lastSystemChange", "instant", "Describes the timestamp for the most recent system change which includes device configuration or setting change.", 0, java.lang.Integer.MAX_VALUE, lastSystemChange));
        childrenList.add(new Property("source", "Reference(Device)", "Describes the link to the source Device that contains administrative device information such as manufacture, serial number, etc.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("parent", "Reference(DeviceComponent)", "Describes the link to the parent resource. For example: Channel is linked to its VMD parent.", 0, java.lang.Integer.MAX_VALUE, parent));
        childrenList.add(new Property("operationalStatus", "CodeableConcept", "Indicates current operational status of the device. For example: On, Off, Standby, etc.", 0, java.lang.Integer.MAX_VALUE, operationalStatus));
        childrenList.add(new Property("parameterGroup", "CodeableConcept", "Describes the parameter group supported by the current device component that is based on some nomenclature, e.g., cardiovascular.", 0, java.lang.Integer.MAX_VALUE, parameterGroup));
        childrenList.add(new Property("measurementPrinciple", "CodeableConcept", "Describes the physical principle of the measurement. For example: thermal, chemical, acoustical, etc.", 0, java.lang.Integer.MAX_VALUE, measurementPrinciple));
        childrenList.add(new Property("productionSpecification", "", "Describes the production specification such as component revision, serial number, etc.", 0, java.lang.Integer.MAX_VALUE, productionSpecification));
        childrenList.add(new Property("languageCode", "CodeableConcept", "Describes the language code for the human-readable text string produced by the device. This language code will follow the IETF language tag. Example: en-US.", 0, java.lang.Integer.MAX_VALUE, languageCode));
      }

      public DeviceComponent copy() {
        DeviceComponent dst = new DeviceComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.lastSystemChange = lastSystemChange == null ? null : lastSystemChange.copy();
        dst.source = source == null ? null : source.copy();
        dst.parent = parent == null ? null : parent.copy();
        dst.operationalStatus = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : operationalStatus)
          dst.operationalStatus.add(i.copy());
        dst.parameterGroup = parameterGroup == null ? null : parameterGroup.copy();
        dst.measurementPrinciple = measurementPrinciple == null ? null : measurementPrinciple.copy();
        dst.productionSpecification = new ArrayList<DeviceComponentProductionSpecificationComponent>();
        for (DeviceComponentProductionSpecificationComponent i : productionSpecification)
          dst.productionSpecification.add(i.copy());
        dst.languageCode = languageCode == null ? null : languageCode.copy();
        return dst;
      }

      protected DeviceComponent typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceComponent;
   }


}

