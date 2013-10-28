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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * Describes the data produced by a device at a point in time.
 */
public class DeviceData extends Resource {

    public static class DeviceDataVirtualDeviceComponent extends BackboneElement {
        /**
         * Describes the compartment.
         */
        protected CodeableConcept code;

        /**
         * Groups together physiological measurement data and derived data.
         */
        protected List<DeviceDataVirtualDeviceChannelComponent> channel = new ArrayList<DeviceDataVirtualDeviceChannelComponent>();

      public DeviceDataVirtualDeviceComponent() {
        super();
      }

        public CodeableConcept getCode() { 
          return this.code;
        }

        public DeviceDataVirtualDeviceComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        public List<DeviceDataVirtualDeviceChannelComponent> getChannel() { 
          return this.channel;
        }

    // syntactic sugar
        public DeviceDataVirtualDeviceChannelComponent addChannel() { 
          DeviceDataVirtualDeviceChannelComponent t = new DeviceDataVirtualDeviceChannelComponent();
          this.channel.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the compartment.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("channel", "", "Groups together physiological measurement data and derived data.", 0, java.lang.Integer.MAX_VALUE, channel));
        }

      public DeviceDataVirtualDeviceComponent copy(DeviceData e) {
        DeviceDataVirtualDeviceComponent dst = new DeviceDataVirtualDeviceComponent();
        dst.code = code == null ? null : code.copy();
        dst.channel = new ArrayList<DeviceDataVirtualDeviceChannelComponent>();
        for (DeviceDataVirtualDeviceChannelComponent i : channel)
          dst.channel.add(i.copy(e));
        return dst;
      }

  }

    public static class DeviceDataVirtualDeviceChannelComponent extends BackboneElement {
        /**
         * Describes the channel.
         */
        protected CodeableConcept code;

        /**
         * A piece of measured or derived data that is reported by the machine.
         */
        protected List<DeviceDataVirtualDeviceChannelMetricComponent> metric = new ArrayList<DeviceDataVirtualDeviceChannelMetricComponent>();

      public DeviceDataVirtualDeviceChannelComponent() {
        super();
      }

        public CodeableConcept getCode() { 
          return this.code;
        }

        public DeviceDataVirtualDeviceChannelComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        public List<DeviceDataVirtualDeviceChannelMetricComponent> getMetric() { 
          return this.metric;
        }

    // syntactic sugar
        public DeviceDataVirtualDeviceChannelMetricComponent addMetric() { 
          DeviceDataVirtualDeviceChannelMetricComponent t = new DeviceDataVirtualDeviceChannelMetricComponent();
          this.metric.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the channel.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("metric", "", "A piece of measured or derived data that is reported by the machine.", 0, java.lang.Integer.MAX_VALUE, metric));
        }

      public DeviceDataVirtualDeviceChannelComponent copy(DeviceData e) {
        DeviceDataVirtualDeviceChannelComponent dst = new DeviceDataVirtualDeviceChannelComponent();
        dst.code = code == null ? null : code.copy();
        dst.metric = new ArrayList<DeviceDataVirtualDeviceChannelMetricComponent>();
        for (DeviceDataVirtualDeviceChannelMetricComponent i : metric)
          dst.metric.add(i.copy(e));
        return dst;
      }

  }

    public static class DeviceDataVirtualDeviceChannelMetricComponent extends BackboneElement {
        /**
         * The data for the metric.
         */
        protected ResourceReference observation;

      public DeviceDataVirtualDeviceChannelMetricComponent() {
        super();
      }

        public ResourceReference getObservation() { 
          return this.observation;
        }

        public DeviceDataVirtualDeviceChannelMetricComponent setObservation(ResourceReference value) { 
          this.observation = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("observation", "Resource(Observation)", "The data for the metric.", 0, java.lang.Integer.MAX_VALUE, observation));
        }

      public DeviceDataVirtualDeviceChannelMetricComponent copy(DeviceData e) {
        DeviceDataVirtualDeviceChannelMetricComponent dst = new DeviceDataVirtualDeviceChannelMetricComponent();
        dst.observation = observation == null ? null : observation.copy();
        return dst;
      }

  }

    /**
     * The point in time that the values are reported.
     */
    protected Instant instant;

    /**
     * An identifier assigned to this observation bu the source device that made the observation.
     */
    protected Identifier identifier;

    /**
     * Identification information for the device that is the source of the data.
     */
    protected ResourceReference source;

    /**
     * The subject of the measurement.
     */
    protected ResourceReference subject;

    /**
     * A medical-related subsystem of a medical device.
     */
    protected List<DeviceDataVirtualDeviceComponent> virtualDevice = new ArrayList<DeviceDataVirtualDeviceComponent>();

    public DeviceData() {
      super();
    }

    public DeviceData(Instant instant, ResourceReference source) {
      super();
      this.instant = instant;
      this.source = source;
    }

    public Instant getInstant() { 
      return this.instant;
    }

    public DeviceData setInstant(Instant value) { 
      this.instant = value;
      return this;
    }

    public Calendar getInstantSimple() { 
      return this.instant == null ? null : this.instant.getValue();
    }

    public DeviceData setInstantSimple(Calendar value) { 
        if (this.instant == null)
          this.instant = new Instant();
        this.instant.setValue(value);
      return this;
    }

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public DeviceData setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    public ResourceReference getSource() { 
      return this.source;
    }

    public DeviceData setSource(ResourceReference value) { 
      this.source = value;
      return this;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public DeviceData setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    public List<DeviceDataVirtualDeviceComponent> getVirtualDevice() { 
      return this.virtualDevice;
    }

    // syntactic sugar
    public DeviceDataVirtualDeviceComponent addVirtualDevice() { 
      DeviceDataVirtualDeviceComponent t = new DeviceDataVirtualDeviceComponent();
      this.virtualDevice.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("instant", "instant", "The point in time that the values are reported.", 0, java.lang.Integer.MAX_VALUE, instant));
        childrenList.add(new Property("identifier", "Identifier", "An identifier assigned to this observation bu the source device that made the observation.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("source", "Resource(Device)", "Identification information for the device that is the source of the data.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("subject", "Resource(Patient|Device|Location)", "The subject of the measurement.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("virtualDevice", "", "A medical-related subsystem of a medical device.", 0, java.lang.Integer.MAX_VALUE, virtualDevice));
      }

      public DeviceData copy() {
        DeviceData dst = new DeviceData();
        dst.instant = instant == null ? null : instant.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.source = source == null ? null : source.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.virtualDevice = new ArrayList<DeviceDataVirtualDeviceComponent>();
        for (DeviceDataVirtualDeviceComponent i : virtualDevice)
          dst.virtualDevice.add(i.copy(dst));
        return dst;
      }

      protected DeviceData typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceData;
   }


}

