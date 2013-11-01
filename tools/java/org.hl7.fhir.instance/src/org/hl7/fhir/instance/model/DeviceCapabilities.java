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

// Generated on Sat, Nov 2, 2013 09:06+1100 for FHIR v0.12

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

  public static class DeviceDataTypeEnumFactory implements EnumFactory {
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

    public static class DeviceCapabilitiesVirtualDeviceComponent extends BackboneElement {
        /**
         * Describes the compartment.
         */
        protected CodeableConcept code;

        /**
         * Groups together physiological measurement data and derived data.
         */
        protected List<DeviceCapabilitiesVirtualDeviceChannelComponent> channel = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelComponent>();

      public DeviceCapabilitiesVirtualDeviceComponent() {
        super();
      }

        /**
         * @return {@link #code} (Describes the compartment.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Describes the compartment.)
         */
        public DeviceCapabilitiesVirtualDeviceComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #channel} (Groups together physiological measurement data and derived data.)
         */
        public List<DeviceCapabilitiesVirtualDeviceChannelComponent> getChannel() { 
          return this.channel;
        }

    // syntactic sugar
        /**
         * @return {@link #channel} (Groups together physiological measurement data and derived data.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelComponent addChannel() { 
          DeviceCapabilitiesVirtualDeviceChannelComponent t = new DeviceCapabilitiesVirtualDeviceChannelComponent();
          this.channel.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the compartment.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("channel", "", "Groups together physiological measurement data and derived data.", 0, java.lang.Integer.MAX_VALUE, channel));
        }

      public DeviceCapabilitiesVirtualDeviceComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceComponent dst = new DeviceCapabilitiesVirtualDeviceComponent();
        dst.code = code == null ? null : code.copy();
        dst.channel = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelComponent>();
        for (DeviceCapabilitiesVirtualDeviceChannelComponent i : channel)
          dst.channel.add(i.copy(e));
        return dst;
      }

  }

    public static class DeviceCapabilitiesVirtualDeviceChannelComponent extends BackboneElement {
        /**
         * Describes the channel.
         */
        protected CodeableConcept code;

        /**
         * A piece of measured or derived data that will be reported by the machine.
         */
        protected List<DeviceCapabilitiesVirtualDeviceChannelMetricComponent> metric = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelMetricComponent>();

      public DeviceCapabilitiesVirtualDeviceChannelComponent() {
        super();
      }

        /**
         * @return {@link #code} (Describes the channel.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Describes the channel.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #metric} (A piece of measured or derived data that will be reported by the machine.)
         */
        public List<DeviceCapabilitiesVirtualDeviceChannelMetricComponent> getMetric() { 
          return this.metric;
        }

    // syntactic sugar
        /**
         * @return {@link #metric} (A piece of measured or derived data that will be reported by the machine.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricComponent addMetric() { 
          DeviceCapabilitiesVirtualDeviceChannelMetricComponent t = new DeviceCapabilitiesVirtualDeviceChannelMetricComponent();
          this.metric.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the channel.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("metric", "", "A piece of measured or derived data that will be reported by the machine.", 0, java.lang.Integer.MAX_VALUE, metric));
        }

      public DeviceCapabilitiesVirtualDeviceChannelComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelComponent dst = new DeviceCapabilitiesVirtualDeviceChannelComponent();
        dst.code = code == null ? null : code.copy();
        dst.metric = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelMetricComponent>();
        for (DeviceCapabilitiesVirtualDeviceChannelMetricComponent i : metric)
          dst.metric.add(i.copy(e));
        return dst;
      }

  }

    public static class DeviceCapabilitiesVirtualDeviceChannelMetricComponent extends BackboneElement {
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

      public DeviceCapabilitiesVirtualDeviceChannelMetricComponent() {
        super();
      }

      public DeviceCapabilitiesVirtualDeviceChannelMetricComponent(CodeableConcept code, String_ key, DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent info) {
        super();
        this.code = code;
        this.key = key;
        this.info = info;
      }

        /**
         * @return {@link #code} (Describes the metrics.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Describes the metrics.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #key} (Used to link to data in device log.)
         */
        public String_ getKey() { 
          return this.key;
        }

        /**
         * @param value {@link #key} (Used to link to data in device log.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricComponent setKey(String_ value) { 
          this.key = value;
          return this;
        }

        /**
         * @return Used to link to data in device log.
         */
        public String getKeySimple() { 
          return this.key == null ? null : this.key.getValue();
        }

        /**
         * @param value Used to link to data in device log.
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricComponent setKeySimple(String value) { 
            if (this.key == null)
              this.key = new String_();
            this.key.setValue(value);
          return this;
        }

        /**
         * @return {@link #info} (How to interpret this metric value.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent getInfo() { 
          return this.info;
        }

        /**
         * @param value {@link #info} (How to interpret this metric value.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricComponent setInfo(DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent value) { 
          this.info = value;
          return this;
        }

        /**
         * @return {@link #facet} (Additional data that qualifies the metric, or contributes to its assessment.)
         */
        public List<DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent> getFacet() { 
          return this.facet;
        }

    // syntactic sugar
        /**
         * @return {@link #facet} (Additional data that qualifies the metric, or contributes to its assessment.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent addFacet() { 
          DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent t = new DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent();
          this.facet.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the metrics.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("key", "string", "Used to link to data in device log.", 0, java.lang.Integer.MAX_VALUE, key));
          childrenList.add(new Property("info", "", "How to interpret this metric value.", 0, java.lang.Integer.MAX_VALUE, info));
          childrenList.add(new Property("facet", "", "Additional data that qualifies the metric, or contributes to its assessment.", 0, java.lang.Integer.MAX_VALUE, facet));
        }

      public DeviceCapabilitiesVirtualDeviceChannelMetricComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelMetricComponent dst = new DeviceCapabilitiesVirtualDeviceChannelMetricComponent();
        dst.code = code == null ? null : code.copy();
        dst.key = key == null ? null : key.copy();
        dst.info = info == null ? null : info.copy(e);
        dst.facet = new ArrayList<DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent>();
        for (DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent i : facet)
          dst.facet.add(i.copy(e));
        return dst;
      }

  }

    public static class DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent extends BackboneElement {
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

      public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent() {
        super();
      }

      public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent(Enumeration<DeviceDataType> type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (Type of data for this metric.)
         */
        public Enumeration<DeviceDataType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Type of data for this metric.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setType(Enumeration<DeviceDataType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Type of data for this metric.
         */
        public DeviceDataType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Type of data for this metric.
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setTypeSimple(DeviceDataType value) { 
            if (this.type == null)
              this.type = new Enumeration<DeviceDataType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #units} (Units for this data item (if a quantity or a range).)
         */
        public String_ getUnits() { 
          return this.units;
        }

        /**
         * @param value {@link #units} (Units for this data item (if a quantity or a range).)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setUnits(String_ value) { 
          this.units = value;
          return this;
        }

        /**
         * @return Units for this data item (if a quantity or a range).
         */
        public String getUnitsSimple() { 
          return this.units == null ? null : this.units.getValue();
        }

        /**
         * @param value Units for this data item (if a quantity or a range).
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setUnitsSimple(String value) { 
          if (value == null)
            this.units = null;
          else {
            if (this.units == null)
              this.units = new String_();
            this.units.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #ucum} (UCUM units (if a quantity or a range).)
         */
        public Code getUcum() { 
          return this.ucum;
        }

        /**
         * @param value {@link #ucum} (UCUM units (if a quantity or a range).)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setUcum(Code value) { 
          this.ucum = value;
          return this;
        }

        /**
         * @return UCUM units (if a quantity or a range).
         */
        public String getUcumSimple() { 
          return this.ucum == null ? null : this.ucum.getValue();
        }

        /**
         * @param value UCUM units (if a quantity or a range).
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setUcumSimple(String value) { 
          if (value == null)
            this.ucum = null;
          else {
            if (this.ucum == null)
              this.ucum = new Code();
            this.ucum.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #template} (A template containing the fixed values for an array output (all the values but the data).)
         */
        public SampledData getTemplate() { 
          return this.template;
        }

        /**
         * @param value {@link #template} (A template containing the fixed values for an array output (all the values but the data).)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setTemplate(SampledData value) { 
          this.template = value;
          return this;
        }

        /**
         * @return {@link #system} (System of the codes, if the type is a Coding.)
         */
        public Uri getSystem() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (System of the codes, if the type is a Coding.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setSystem(Uri value) { 
          this.system = value;
          return this;
        }

        /**
         * @return System of the codes, if the type is a Coding.
         */
        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value System of the codes, if the type is a Coding.
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent setSystemSimple(String value) { 
          if (value == null)
            this.system = null;
          else {
            if (this.system == null)
              this.system = new Uri();
            this.system.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "Type of data for this metric.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("units", "string", "Units for this data item (if a quantity or a range).", 0, java.lang.Integer.MAX_VALUE, units));
          childrenList.add(new Property("ucum", "code", "UCUM units (if a quantity or a range).", 0, java.lang.Integer.MAX_VALUE, ucum));
          childrenList.add(new Property("template", "SampledData", "A template containing the fixed values for an array output (all the values but the data).", 0, java.lang.Integer.MAX_VALUE, template));
          childrenList.add(new Property("system", "uri", "System of the codes, if the type is a Coding.", 0, java.lang.Integer.MAX_VALUE, system));
        }

      public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent dst = new DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent();
        dst.type = type == null ? null : type.copy();
        dst.units = units == null ? null : units.copy();
        dst.ucum = ucum == null ? null : ucum.copy();
        dst.template = template == null ? null : template.copy();
        dst.system = system == null ? null : system.copy();
        return dst;
      }

  }

    public static class DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent extends BackboneElement {
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

      public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent() {
        super();
      }

      public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent(CodeableConcept code, String_ key, DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent info) {
        super();
        this.code = code;
        this.key = key;
        this.info = info;
      }

        /**
         * @return {@link #code} (Describes the facet.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Describes the facet.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #scale} (The factor to apply to the raw values to get the correct value.)
         */
        public Decimal getScale() { 
          return this.scale;
        }

        /**
         * @param value {@link #scale} (The factor to apply to the raw values to get the correct value.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent setScale(Decimal value) { 
          this.scale = value;
          return this;
        }

        /**
         * @return The factor to apply to the raw values to get the correct value.
         */
        public BigDecimal getScaleSimple() { 
          return this.scale == null ? null : this.scale.getValue();
        }

        /**
         * @param value The factor to apply to the raw values to get the correct value.
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent setScaleSimple(BigDecimal value) { 
          if (value == null)
            this.scale = null;
          else {
            if (this.scale == null)
              this.scale = new Decimal();
            this.scale.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #key} (Used to link to data in device log.)
         */
        public String_ getKey() { 
          return this.key;
        }

        /**
         * @param value {@link #key} (Used to link to data in device log.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent setKey(String_ value) { 
          this.key = value;
          return this;
        }

        /**
         * @return Used to link to data in device log.
         */
        public String getKeySimple() { 
          return this.key == null ? null : this.key.getValue();
        }

        /**
         * @param value Used to link to data in device log.
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent setKeySimple(String value) { 
            if (this.key == null)
              this.key = new String_();
            this.key.setValue(value);
          return this;
        }

        /**
         * @return {@link #info} (How to interpret this facet value.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent getInfo() { 
          return this.info;
        }

        /**
         * @param value {@link #info} (How to interpret this facet value.)
         */
        public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent setInfo(DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent value) { 
          this.info = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the facet.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("scale", "decimal", "The factor to apply to the raw values to get the correct value.", 0, java.lang.Integer.MAX_VALUE, scale));
          childrenList.add(new Property("key", "string", "Used to link to data in device log.", 0, java.lang.Integer.MAX_VALUE, key));
          childrenList.add(new Property("info", "@DeviceCapabilities.virtualDevice.channel.metric.info", "How to interpret this facet value.", 0, java.lang.Integer.MAX_VALUE, info));
        }

      public DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent copy(DeviceCapabilities e) {
        DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent dst = new DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent();
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

    public DeviceCapabilities() {
      super();
    }

    /**
     * @return {@link #name} (The name of this device.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (The name of this device.)
     */
    public DeviceCapabilities setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return The name of this device.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value The name of this device.
     */
    public DeviceCapabilities setNameSimple(String value) { 
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
     * @return {@link #type} (The kind of device - what kind of functionality it provides.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The kind of device - what kind of functionality it provides.)
     */
    public DeviceCapabilities setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #manufacturer} (The company that built this device.)
     */
    public String_ getManufacturer() { 
      return this.manufacturer;
    }

    /**
     * @param value {@link #manufacturer} (The company that built this device.)
     */
    public DeviceCapabilities setManufacturer(String_ value) { 
      this.manufacturer = value;
      return this;
    }

    /**
     * @return The company that built this device.
     */
    public String getManufacturerSimple() { 
      return this.manufacturer == null ? null : this.manufacturer.getValue();
    }

    /**
     * @param value The company that built this device.
     */
    public DeviceCapabilities setManufacturerSimple(String value) { 
      if (value == null)
        this.manufacturer = null;
      else {
        if (this.manufacturer == null)
          this.manufacturer = new String_();
        this.manufacturer.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #identity} (Identifies this particular device uniquely.)
     */
    public ResourceReference getIdentity() { 
      return this.identity;
    }

    /**
     * @param value {@link #identity} (Identifies this particular device uniquely.)
     */
    public DeviceCapabilities setIdentity(ResourceReference value) { 
      this.identity = value;
      return this;
    }

    /**
     * @return {@link #virtualDevice} (A medical-related subsystem of a medical device.)
     */
    public List<DeviceCapabilitiesVirtualDeviceComponent> getVirtualDevice() { 
      return this.virtualDevice;
    }

    // syntactic sugar
    /**
     * @return {@link #virtualDevice} (A medical-related subsystem of a medical device.)
     */
    public DeviceCapabilitiesVirtualDeviceComponent addVirtualDevice() { 
      DeviceCapabilitiesVirtualDeviceComponent t = new DeviceCapabilitiesVirtualDeviceComponent();
      this.virtualDevice.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("name", "string", "The name of this device.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("type", "CodeableConcept", "The kind of device - what kind of functionality it provides.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("manufacturer", "string", "The company that built this device.", 0, java.lang.Integer.MAX_VALUE, manufacturer));
        childrenList.add(new Property("identity", "Resource(Device)", "Identifies this particular device uniquely.", 0, java.lang.Integer.MAX_VALUE, identity));
        childrenList.add(new Property("virtualDevice", "", "A medical-related subsystem of a medical device.", 0, java.lang.Integer.MAX_VALUE, virtualDevice));
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

