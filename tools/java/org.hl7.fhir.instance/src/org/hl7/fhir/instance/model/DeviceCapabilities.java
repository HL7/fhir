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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11

import java.util.*;

import java.math.*;
/**
 * Describes the set of data produced by a device.
 */
public class DeviceCapabilities extends Resource {

    public enum DeviceDataType {
        quantity, // The data item is a quantity. The string value should be merged with the units, and the ucum value if provided, to create a valid quantity.
        range, // The data item is a range. The string value should be split about the " - " into low and high, with the units and ucum (if provided) filling out the low and high quantities.
        coding, // The data item is a code (i.e. true/false etc). The value should be built into a valid coding by filling out the system element provided.
        array, // The data item is an Array (a sequence of sample measures, which must be merged with the Array template).
        string, // The data item is a simple string.
        Null; // added to help the parsers
        public static DeviceDataType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("Quantity".equals(codeString))
          return quantity;
        if ("Range".equals(codeString))
          return range;
        if ("Coding".equals(codeString))
          return coding;
        if ("Array".equals(codeString))
          return array;
        if ("string".equals(codeString))
          return string;
        throw new Exception("Unknown DeviceDataType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case quantity: return "Quantity";
            case range: return "Range";
            case coding: return "Coding";
            case array: return "Array";
            case string: return "string";
            default: return "?";
          }
        }
    }

  public class DeviceDataTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("Quantity".equals(codeString))
          return DeviceDataType.quantity;
        if ("Range".equals(codeString))
          return DeviceDataType.range;
        if ("Coding".equals(codeString))
          return DeviceDataType.coding;
        if ("Array".equals(codeString))
          return DeviceDataType.array;
        if ("string".equals(codeString))
          return DeviceDataType.string;
        throw new Exception("Unknown DeviceDataType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DeviceDataType.quantity)
        return "Quantity";
      if (code == DeviceDataType.range)
        return "Range";
      if (code == DeviceDataType.coding)
        return "Coding";
      if (code == DeviceDataType.array)
        return "Array";
      if (code == DeviceDataType.string)
        return "string";
      return "?";
      }
    }

    public class DeviceCapabilitiesVirtualDeviceComponent extends Element {
        /**
         * Describes the compartment.
         */
        protected CodeableConcept code;

        /**
         * Groups together physiological measurement data and derived data.
         */
        protected List<DeviceCapabilitiesVirtualDeviceChannelComponent> channel = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelComponent>();

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public List<DeviceCapabilitiesVirtualDeviceChannelComponent> getChannel() { 
          return this.channel;
        }

    // syntactic sugar
        public DeviceCapabilitiesVirtualDeviceChannelComponent addChannel() { 
          DeviceCapabilitiesVirtualDeviceChannelComponent t = new DeviceCapabilitiesVirtualDeviceChannelComponent();
          this.channel.add(t);
          return t;
        }

      public DeviceCapabilitiesVirtualDeviceComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceComponent dst = e.new DeviceCapabilitiesVirtualDeviceComponent();
        dst.code = code == null ? null : code.copy();
        dst.channel = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelComponent>();
        for (DeviceCapabilitiesVirtualDeviceChannelComponent i : channel)
          dst.channel.add(i.copy(e));
        return dst;
      }

  }

    public class DeviceCapabilitiesVirtualDeviceChannelComponent extends Element {
        /**
         * Describes the channel.
         */
        protected CodeableConcept code;

        /**
         * A piece of measured or derived data that will be reported by the machine.
         */
        protected List<DeviceCapabilitiesVirtualDeviceChannelMetricComponent> metric = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelMetricComponent>();

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public List<DeviceCapabilitiesVirtualDeviceChannelMetricComponent> getMetric() { 
          return this.metric;
        }

    // syntactic sugar
        public DeviceCapabilitiesVirtualDeviceChannelMetricComponent addMetric() { 
          DeviceCapabilitiesVirtualDeviceChannelMetricComponent t = new DeviceCapabilitiesVirtualDeviceChannelMetricComponent();
          this.metric.add(t);
          return t;
        }

      public DeviceCapabilitiesVirtualDeviceChannelComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelComponent dst = e.new DeviceCapabilitiesVirtualDeviceChannelComponent();
        dst.code = code == null ? null : code.copy();
        dst.metric = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelMetricComponent>();
        for (DeviceCapabilitiesVirtualDeviceChannelMetricComponent i : metric)
          dst.metric.add(i.copy(e));
        return dst;
      }

  }

    public class DeviceCapabilitiesVirtualDeviceChannelMetricComponent extends Element {
        /**
         * Describes the metrics.
         */
        protected CodeableConcept code;

        /**
         * Used to link to data in device log.
         */
        protected String_ key;

        /**
         * How to interpret this metric value.
         */
        protected DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent info;

        /**
         * Additional data that qualifies the metric, or contributes to its assessment.
         */
        protected List<DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent> facet = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent>();

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public String_ getKey() { 
          return this.key;
        }

        public void setKey(String_ value) { 
          this.key = value;
        }

        public String getKeySimple() { 
          return this.key == null ? null : this.key.getValue();
        }

        public void setKeySimple(String value) { 
            if (this.key == null)
              this.key = new String_();
            this.key.setValue(value);
        }

        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent getInfo() { 
          return this.info;
        }

        public void setInfo(DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent value) { 
          this.info = value;
        }

        public List<DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent> getFacet() { 
          return this.facet;
        }

    // syntactic sugar
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent addFacet() { 
          DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent t = new DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent();
          this.facet.add(t);
          return t;
        }

      public DeviceCapabilitiesVirtualDeviceChannelMetricComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelMetricComponent dst = e.new DeviceCapabilitiesVirtualDeviceChannelMetricComponent();
        dst.code = code == null ? null : code.copy();
        dst.key = key == null ? null : key.copy();
        dst.info = info == null ? null : info.copy(e);
        dst.facet = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent>();
        for (DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent i : facet)
          dst.facet.add(i.copy(e));
        return dst;
      }

  }

    public class DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent extends Element {
        /**
         * Type of data for this metric.
         */
        protected Enumeration<DeviceDataType> type;

        /**
         * Units for this data item (if a quantity or a range).
         */
        protected String_ units;

        /**
         * UCUM units (if a quantity or a range).
         */
        protected Code ucum;

        /**
         * A template containing the fixed values for an array output (all the values but the data).
         */
        protected SampledData template;

        /**
         * System of the codes, if the type is a Coding.
         */
        protected Uri system;

        public Enumeration<DeviceDataType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<DeviceDataType> value) { 
          this.type = value;
        }

        public DeviceDataType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(DeviceDataType value) { 
            if (this.type == null)
              this.type = new Enumeration<DeviceDataType>();
            this.type.setValue(value);
        }

        public String_ getUnits() { 
          return this.units;
        }

        public void setUnits(String_ value) { 
          this.units = value;
        }

        public String getUnitsSimple() { 
          return this.units == null ? null : this.units.getValue();
        }

        public void setUnitsSimple(String value) { 
          if (value == null)
            this.units = null;
          else {
            if (this.units == null)
              this.units = new String_();
            this.units.setValue(value);
          }
        }

        public Code getUcum() { 
          return this.ucum;
        }

        public void setUcum(Code value) { 
          this.ucum = value;
        }

        public String getUcumSimple() { 
          return this.ucum == null ? null : this.ucum.getValue();
        }

        public void setUcumSimple(String value) { 
          if (value == null)
            this.ucum = null;
          else {
            if (this.ucum == null)
              this.ucum = new Code();
            this.ucum.setValue(value);
          }
        }

        public SampledData getTemplate() { 
          return this.template;
        }

        public void setTemplate(SampledData value) { 
          this.template = value;
        }

        public Uri getSystem() { 
          return this.system;
        }

        public void setSystem(Uri value) { 
          this.system = value;
        }

        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        public void setSystemSimple(String value) { 
          if (value == null)
            this.system = null;
          else {
            if (this.system == null)
              this.system = new Uri();
            this.system.setValue(value);
          }
        }

      public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent dst = e.new DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent();
        dst.type = type == null ? null : type.copy();
        dst.units = units == null ? null : units.copy();
        dst.ucum = ucum == null ? null : ucum.copy();
        dst.template = template == null ? null : template.copy();
        dst.system = system == null ? null : system.copy();
        return dst;
      }

  }

    public class DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent extends Element {
        /**
         * Describes the facet.
         */
        protected CodeableConcept code;

        /**
         * The factor to apply to the raw values to get the correct value.
         */
        protected Decimal scale;

        /**
         * Used to link to data in device log.
         */
        protected String_ key;

        /**
         * How to interpret this facet value.
         */
        protected DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent info;

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public Decimal getScale() { 
          return this.scale;
        }

        public void setScale(Decimal value) { 
          this.scale = value;
        }

        public BigDecimal getScaleSimple() { 
          return this.scale == null ? null : this.scale.getValue();
        }

        public void setScaleSimple(BigDecimal value) { 
          if (value == null)
            this.scale = null;
          else {
            if (this.scale == null)
              this.scale = new Decimal();
            this.scale.setValue(value);
          }
        }

        public String_ getKey() { 
          return this.key;
        }

        public void setKey(String_ value) { 
          this.key = value;
        }

        public String getKeySimple() { 
          return this.key == null ? null : this.key.getValue();
        }

        public void setKeySimple(String value) { 
            if (this.key == null)
              this.key = new String_();
            this.key.setValue(value);
        }

        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent getInfo() { 
          return this.info;
        }

        public void setInfo(DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent value) { 
          this.info = value;
        }

      public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent dst = e.new DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent();
        dst.code = code == null ? null : code.copy();
        dst.scale = scale == null ? null : scale.copy();
        dst.key = key == null ? null : key.copy();
        dst.info = info == null ? null : info.copy(e);
        return dst;
      }

  }

    /**
     * The name of this device.
     */
    protected String_ name;

    /**
     * The kind of device - what kind of functionality it provides.
     */
    protected CodeableConcept type;

    /**
     * The company that built this device.
     */
    protected String_ manufacturer;

    /**
     * Identifies this particular device uniquely.
     */
    protected ResourceReference identity;

    /**
     * A medical-related subsystem of a medical device.
     */
    protected List<DeviceCapabilitiesVirtualDeviceComponent> virtualDevice = new ArrayList<DeviceCapabilitiesVirtualDeviceComponent>();

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

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
    }

    public String_ getManufacturer() { 
      return this.manufacturer;
    }

    public void setManufacturer(String_ value) { 
      this.manufacturer = value;
    }

    public String getManufacturerSimple() { 
      return this.manufacturer == null ? null : this.manufacturer.getValue();
    }

    public void setManufacturerSimple(String value) { 
      if (value == null)
        this.manufacturer = null;
      else {
        if (this.manufacturer == null)
          this.manufacturer = new String_();
        this.manufacturer.setValue(value);
      }
    }

    public ResourceReference getIdentity() { 
      return this.identity;
    }

    public void setIdentity(ResourceReference value) { 
      this.identity = value;
    }

    public List<DeviceCapabilitiesVirtualDeviceComponent> getVirtualDevice() { 
      return this.virtualDevice;
    }

    // syntactic sugar
    public DeviceCapabilitiesVirtualDeviceComponent addVirtualDevice() { 
      DeviceCapabilitiesVirtualDeviceComponent t = new DeviceCapabilitiesVirtualDeviceComponent();
      this.virtualDevice.add(t);
      return t;
    }

      public DeviceCapabilities copy() {
        DeviceCapabilities dst = new DeviceCapabilities();
        dst.name = name == null ? null : name.copy();
        dst.type = type == null ? null : type.copy();
        dst.manufacturer = manufacturer == null ? null : manufacturer.copy();
        dst.identity = identity == null ? null : identity.copy();
        dst.virtualDevice = new ArrayList<DeviceCapabilitiesVirtualDeviceComponent>();
        for (DeviceCapabilitiesVirtualDeviceComponent i : virtualDevice)
          dst.virtualDevice.add(i.copy(dst));
        return dst;
      }

      protected DeviceCapabilities typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceCapabilities;
   }


}

