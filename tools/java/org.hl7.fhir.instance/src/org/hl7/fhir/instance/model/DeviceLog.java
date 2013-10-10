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

// Generated on Thu, Oct 10, 2013 11:38+1100 for FHIR v0.12

import java.util.*;

/**
 * A set of raw data produced by a device.
 */
public class DeviceLog extends Resource {

    public enum DeviceValueFlag {
        ok, // the value is valid.
        ongoing, // An early estimate of value; measurement is still occurring.
        early, // An early estimate of value; processing is still occurring.
        questionable, // The observation value should be treated with care (there are reasons for doubting the accuracy of the current value).
        calibrating, // The value has been generated while calibration is occurring.
        error, // the current conditions are invalid, and the value should not be used.
        unknown, // No observation value was available.
        test, // this is test data.
        demo, // this is demo data.
        under, // the value is under accurate measurement limits.
        over, // the value is over accurate measurement limits.
        alarm, // the value is associated with an active alarm condition.
        alarmoff, // the value would be associated with an active alarm, but alarms are turned off.
        Null; // added to help the parsers
        public static DeviceValueFlag fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return ok;
        if ("ongoing".equals(codeString))
          return ongoing;
        if ("early".equals(codeString))
          return early;
        if ("questionable".equals(codeString))
          return questionable;
        if ("calibrating".equals(codeString))
          return calibrating;
        if ("error".equals(codeString))
          return error;
        if ("unknown".equals(codeString))
          return unknown;
        if ("test".equals(codeString))
          return test;
        if ("demo".equals(codeString))
          return demo;
        if ("under".equals(codeString))
          return under;
        if ("over".equals(codeString))
          return over;
        if ("alarm".equals(codeString))
          return alarm;
        if ("alarm-off".equals(codeString))
          return alarmoff;
        throw new Exception("Unknown DeviceValueFlag code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ok: return "ok";
            case ongoing: return "ongoing";
            case early: return "early";
            case questionable: return "questionable";
            case calibrating: return "calibrating";
            case error: return "error";
            case unknown: return "unknown";
            case test: return "test";
            case demo: return "demo";
            case under: return "under";
            case over: return "over";
            case alarm: return "alarm";
            case alarmoff: return "alarm-off";
            default: return "?";
          }
        }
    }

  public class DeviceValueFlagEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return DeviceValueFlag.ok;
        if ("ongoing".equals(codeString))
          return DeviceValueFlag.ongoing;
        if ("early".equals(codeString))
          return DeviceValueFlag.early;
        if ("questionable".equals(codeString))
          return DeviceValueFlag.questionable;
        if ("calibrating".equals(codeString))
          return DeviceValueFlag.calibrating;
        if ("error".equals(codeString))
          return DeviceValueFlag.error;
        if ("unknown".equals(codeString))
          return DeviceValueFlag.unknown;
        if ("test".equals(codeString))
          return DeviceValueFlag.test;
        if ("demo".equals(codeString))
          return DeviceValueFlag.demo;
        if ("under".equals(codeString))
          return DeviceValueFlag.under;
        if ("over".equals(codeString))
          return DeviceValueFlag.over;
        if ("alarm".equals(codeString))
          return DeviceValueFlag.alarm;
        if ("alarm-off".equals(codeString))
          return DeviceValueFlag.alarmoff;
        throw new Exception("Unknown DeviceValueFlag code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DeviceValueFlag.ok)
        return "ok";
      if (code == DeviceValueFlag.ongoing)
        return "ongoing";
      if (code == DeviceValueFlag.early)
        return "early";
      if (code == DeviceValueFlag.questionable)
        return "questionable";
      if (code == DeviceValueFlag.calibrating)
        return "calibrating";
      if (code == DeviceValueFlag.error)
        return "error";
      if (code == DeviceValueFlag.unknown)
        return "unknown";
      if (code == DeviceValueFlag.test)
        return "test";
      if (code == DeviceValueFlag.demo)
        return "demo";
      if (code == DeviceValueFlag.under)
        return "under";
      if (code == DeviceValueFlag.over)
        return "over";
      if (code == DeviceValueFlag.alarm)
        return "alarm";
      if (code == DeviceValueFlag.alarmoff)
        return "alarm-off";
      return "?";
      }
    }

    public class DeviceLogItemComponent extends Element {
        /**
         * Reference to a device capabilities declaration.
         */
        protected String_ key;

        /**
         * The value of the data item, if available. Irrespective of the logical format of the data item, the value is always represented as a string.
         */
        protected String_ value;

        /**
         * Information about the quality of the data etc.
         */
        protected List<Enumeration<DeviceValueFlag>> flag = new ArrayList<Enumeration<DeviceValueFlag>>();

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

        public String_ getValue() { 
          return this.value;
        }

        public void setValue(String_ value) { 
          this.value = value;
        }

        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        public void setValueSimple(String value) { 
          if (value == null)
            this.value = null;
          else {
            if (this.value == null)
              this.value = new String_();
            this.value.setValue(value);
          }
        }

        public List<Enumeration<DeviceValueFlag>> getFlag() { 
          return this.flag;
        }

    // syntactic sugar
        public Enumeration<DeviceValueFlag> addFlag() { 
          Enumeration<DeviceValueFlag> t = new Enumeration<DeviceValueFlag>();
          this.flag.add(t);
          return t;
        }

        public Enumeration<DeviceValueFlag> addFlagSimple(DeviceValueFlag value) { 
          Enumeration<DeviceValueFlag> t = new Enumeration<DeviceValueFlag>();
          t.setValue(value);
          this.flag.add(t);
          return t;
        }

      public DeviceLogItemComponent copy(DeviceLog e) {
        DeviceLogItemComponent dst = e.new DeviceLogItemComponent();
        dst.key = key == null ? null : key.copy();
        dst.value = value == null ? null : value.copy();
        dst.flag = new ArrayList<Enumeration<DeviceValueFlag>>();
        for (Enumeration<DeviceValueFlag> i : flag)
          dst.flag.add(i.copy());
        return dst;
      }

  }

    /**
     * The point in time that the values are reported.
     */
    protected Instant instant;

    /**
     * An explicit reference  to the capabilities.
     */
    protected ResourceReference capabilities;

    /**
     * The subject of the measurement.
     */
    protected ResourceReference subject;

    /**
     * An item of data that the device produces.
     */
    protected List<DeviceLogItemComponent> item = new ArrayList<DeviceLogItemComponent>();

    public Instant getInstant() { 
      return this.instant;
    }

    public void setInstant(Instant value) { 
      this.instant = value;
    }

    public Calendar getInstantSimple() { 
      return this.instant == null ? null : this.instant.getValue();
    }

    public void setInstantSimple(Calendar value) { 
      if (value == null)
        this.instant = null;
      else {
        if (this.instant == null)
          this.instant = new Instant();
        this.instant.setValue(value);
      }
    }

    public ResourceReference getCapabilities() { 
      return this.capabilities;
    }

    public void setCapabilities(ResourceReference value) { 
      this.capabilities = value;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public List<DeviceLogItemComponent> getItem() { 
      return this.item;
    }

    // syntactic sugar
    public DeviceLogItemComponent addItem() { 
      DeviceLogItemComponent t = new DeviceLogItemComponent();
      this.item.add(t);
      return t;
    }

      public DeviceLog copy() {
        DeviceLog dst = new DeviceLog();
        dst.instant = instant == null ? null : instant.copy();
        dst.capabilities = capabilities == null ? null : capabilities.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.item = new ArrayList<DeviceLogItemComponent>();
        for (DeviceLogItemComponent i : item)
          dst.item.add(i.copy(dst));
        return dst;
      }

      protected DeviceLog typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceLog;
   }


}

