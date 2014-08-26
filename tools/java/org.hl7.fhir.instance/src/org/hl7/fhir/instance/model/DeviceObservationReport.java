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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import java.util.*;

/**
 * Describes the data produced by a device at a point in time.
 */
public class DeviceObservationReport extends Resource {

    public static class DeviceObservationReportVirtualDeviceComponent extends BackboneElement {
        /**
         * Describes the compartment.
         */
        protected CodeableConcept code;

        /**
         * Groups together physiological measurement data and derived data.
         */
        protected List<DeviceObservationReportVirtualDeviceChannelComponent> channel = new ArrayList<DeviceObservationReportVirtualDeviceChannelComponent>();

        private static final long serialVersionUID = -396624204L;

      public DeviceObservationReportVirtualDeviceComponent() {
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
        public DeviceObservationReportVirtualDeviceComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #channel} (Groups together physiological measurement data and derived data.)
         */
        public List<DeviceObservationReportVirtualDeviceChannelComponent> getChannel() { 
          return this.channel;
        }

    // syntactic sugar
        /**
         * @return {@link #channel} (Groups together physiological measurement data and derived data.)
         */
        public DeviceObservationReportVirtualDeviceChannelComponent addChannel() { 
          DeviceObservationReportVirtualDeviceChannelComponent t = new DeviceObservationReportVirtualDeviceChannelComponent();
          this.channel.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the compartment.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("channel", "", "Groups together physiological measurement data and derived data.", 0, java.lang.Integer.MAX_VALUE, channel));
        }

      public DeviceObservationReportVirtualDeviceComponent copy() {
        DeviceObservationReportVirtualDeviceComponent dst = new DeviceObservationReportVirtualDeviceComponent();
        dst.code = code == null ? null : code.copy();
        dst.channel = new ArrayList<DeviceObservationReportVirtualDeviceChannelComponent>();
        for (DeviceObservationReportVirtualDeviceChannelComponent i : channel)
          dst.channel.add(i.copy());
        return dst;
      }

  }

    public static class DeviceObservationReportVirtualDeviceChannelComponent extends BackboneElement {
        /**
         * Describes the channel.
         */
        protected CodeableConcept code;

        /**
         * A piece of measured or derived data that is reported by the machine.
         */
        protected List<DeviceObservationReportVirtualDeviceChannelMetricComponent> metric = new ArrayList<DeviceObservationReportVirtualDeviceChannelMetricComponent>();

        private static final long serialVersionUID = 1868788989L;

      public DeviceObservationReportVirtualDeviceChannelComponent() {
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
        public DeviceObservationReportVirtualDeviceChannelComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #metric} (A piece of measured or derived data that is reported by the machine.)
         */
        public List<DeviceObservationReportVirtualDeviceChannelMetricComponent> getMetric() { 
          return this.metric;
        }

    // syntactic sugar
        /**
         * @return {@link #metric} (A piece of measured or derived data that is reported by the machine.)
         */
        public DeviceObservationReportVirtualDeviceChannelMetricComponent addMetric() { 
          DeviceObservationReportVirtualDeviceChannelMetricComponent t = new DeviceObservationReportVirtualDeviceChannelMetricComponent();
          this.metric.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Describes the channel.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("metric", "", "A piece of measured or derived data that is reported by the machine.", 0, java.lang.Integer.MAX_VALUE, metric));
        }

      public DeviceObservationReportVirtualDeviceChannelComponent copy() {
        DeviceObservationReportVirtualDeviceChannelComponent dst = new DeviceObservationReportVirtualDeviceChannelComponent();
        dst.code = code == null ? null : code.copy();
        dst.metric = new ArrayList<DeviceObservationReportVirtualDeviceChannelMetricComponent>();
        for (DeviceObservationReportVirtualDeviceChannelMetricComponent i : metric)
          dst.metric.add(i.copy());
        return dst;
      }

  }

    public static class DeviceObservationReportVirtualDeviceChannelMetricComponent extends BackboneElement {
        /**
         * The data for the metric.
         */
        protected ResourceReference observation;

        /**
         * The actual object that is the target of the reference (The data for the metric.)
         */
        protected Observation observationTarget;

        private static final long serialVersionUID = -753705470L;

      public DeviceObservationReportVirtualDeviceChannelMetricComponent() {
        super();
      }

      public DeviceObservationReportVirtualDeviceChannelMetricComponent(ResourceReference observation) {
        super();
        this.observation = observation;
      }

        /**
         * @return {@link #observation} (The data for the metric.)
         */
        public ResourceReference getObservation() { 
          return this.observation;
        }

        /**
         * @param value {@link #observation} (The data for the metric.)
         */
        public DeviceObservationReportVirtualDeviceChannelMetricComponent setObservation(ResourceReference value) { 
          this.observation = value;
          return this;
        }

        /**
         * @return {@link #observation} (The actual object that is the target of the reference. The data for the metric.)
         */
        public Observation getObservationTarget() { 
          return this.observationTarget;
        }

        /**
         * @param value {@link #observation} (The actual object that is the target of the reference. The data for the metric.)
         */
        public DeviceObservationReportVirtualDeviceChannelMetricComponent setObservationTarget(Observation value) { 
          this.observationTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("observation", "Resource(Observation)", "The data for the metric.", 0, java.lang.Integer.MAX_VALUE, observation));
        }

      public DeviceObservationReportVirtualDeviceChannelMetricComponent copy() {
        DeviceObservationReportVirtualDeviceChannelMetricComponent dst = new DeviceObservationReportVirtualDeviceChannelMetricComponent();
        dst.observation = observation == null ? null : observation.copy();
        return dst;
      }

  }

    /**
     * The point in time that the values are reported.
     */
    protected InstantType instant;

    /**
     * An identifier assigned to this observation bu the source device that made the observation.
     */
    protected Identifier identifier;

    /**
     * Identification information for the device that is the source of the data.
     */
    protected ResourceReference source;

    /**
     * The actual object that is the target of the reference (Identification information for the device that is the source of the data.)
     */
    protected Device sourceTarget;

    /**
     * The subject of the measurement.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The subject of the measurement.)
     */
    protected Resource subjectTarget;

    /**
     * A medical-related subsystem of a medical device.
     */
    protected List<DeviceObservationReportVirtualDeviceComponent> virtualDevice = new ArrayList<DeviceObservationReportVirtualDeviceComponent>();

    private static final long serialVersionUID = -1068505466L;

    public DeviceObservationReport() {
      super();
    }

    public DeviceObservationReport(InstantType instant, ResourceReference source) {
      super();
      this.instant = instant;
      this.source = source;
    }

    /**
     * @return {@link #instant} (The point in time that the values are reported.)
     */
    public InstantType getInstant() { 
      return this.instant;
    }

    /**
     * @param value {@link #instant} (The point in time that the values are reported.)
     */
    public DeviceObservationReport setInstant(InstantType value) { 
      this.instant = value;
      return this;
    }

    /**
     * @return The point in time that the values are reported.
     */
    public DateAndTime getInstantSimple() { 
      return this.instant == null ? null : this.instant.getValue();
    }

    /**
     * @param value The point in time that the values are reported.
     */
    public DeviceObservationReport setInstantSimple(DateAndTime value) { 
        if (this.instant == null)
          this.instant = new InstantType();
        this.instant.setValue(value);
      return this;
    }

    /**
     * @return {@link #identifier} (An identifier assigned to this observation bu the source device that made the observation.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (An identifier assigned to this observation bu the source device that made the observation.)
     */
    public DeviceObservationReport setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #source} (Identification information for the device that is the source of the data.)
     */
    public ResourceReference getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (Identification information for the device that is the source of the data.)
     */
    public DeviceObservationReport setSource(ResourceReference value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #source} (The actual object that is the target of the reference. Identification information for the device that is the source of the data.)
     */
    public Device getSourceTarget() { 
      return this.sourceTarget;
    }

    /**
     * @param value {@link #source} (The actual object that is the target of the reference. Identification information for the device that is the source of the data.)
     */
    public DeviceObservationReport setSourceTarget(Device value) { 
      this.sourceTarget = value;
      return this;
    }

    /**
     * @return {@link #subject} (The subject of the measurement.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The subject of the measurement.)
     */
    public DeviceObservationReport setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. The subject of the measurement.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The subject of the measurement.)
     */
    public DeviceObservationReport setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #virtualDevice} (A medical-related subsystem of a medical device.)
     */
    public List<DeviceObservationReportVirtualDeviceComponent> getVirtualDevice() { 
      return this.virtualDevice;
    }

    // syntactic sugar
    /**
     * @return {@link #virtualDevice} (A medical-related subsystem of a medical device.)
     */
    public DeviceObservationReportVirtualDeviceComponent addVirtualDevice() { 
      DeviceObservationReportVirtualDeviceComponent t = new DeviceObservationReportVirtualDeviceComponent();
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

      public DeviceObservationReport copy() {
        DeviceObservationReport dst = new DeviceObservationReport();
        dst.instant = instant == null ? null : instant.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.source = source == null ? null : source.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.virtualDevice = new ArrayList<DeviceObservationReportVirtualDeviceComponent>();
        for (DeviceObservationReportVirtualDeviceComponent i : virtualDevice)
          dst.virtualDevice.add(i.copy());
        return dst;
      }

      protected DeviceObservationReport typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceObservationReport;
   }


}

