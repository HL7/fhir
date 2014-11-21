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

import java.math.*;
import org.hl7.fhir.utilities.Utilities;
/**
 * Specifies an event that may occur multiple times. Timing schedules are used for to record when things are expected or requested to occur.
 */
public class Timing extends Type {

    public enum EventTiming {
        HS, // event occurs [duration] before the hour of sleep (or trying to).
        WAKE, // event occurs [duration] after waking.
        AC, // event occurs [duration] before a meal (from the Latin ante cibus).
        ACM, // event occurs [duration] before breakfast (from the Latin ante cibus matutinus).
        ACD, // event occurs [duration] before lunch (from the Latin ante cibus diurnus).
        ACV, // event occurs [duration] before dinner (from the Latin ante cibus vespertinus).
        PC, // event occurs [duration] after a meal (from the Latin post cibus).
        PCM, // event occurs [duration] after breakfast (from the Latin post cibus matutinus).
        PCD, // event occurs [duration] after lunch (from the Latin post cibus diurnus).
        PCV, // event occurs [duration] after dinner (from the Latin post cibus vespertinus).
        NULL; // added to help the parsers
        public static EventTiming fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("HS".equals(codeString))
          return HS;
        if ("WAKE".equals(codeString))
          return WAKE;
        if ("AC".equals(codeString))
          return AC;
        if ("ACM".equals(codeString))
          return ACM;
        if ("ACD".equals(codeString))
          return ACD;
        if ("ACV".equals(codeString))
          return ACV;
        if ("PC".equals(codeString))
          return PC;
        if ("PCM".equals(codeString))
          return PCM;
        if ("PCD".equals(codeString))
          return PCD;
        if ("PCV".equals(codeString))
          return PCV;
        throw new Exception("Unknown EventTiming code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case HS: return "HS";
            case WAKE: return "WAKE";
            case AC: return "AC";
            case ACM: return "ACM";
            case ACD: return "ACD";
            case ACV: return "ACV";
            case PC: return "PC";
            case PCM: return "PCM";
            case PCD: return "PCD";
            case PCV: return "PCV";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case HS: return "event occurs [duration] before the hour of sleep (or trying to).";
            case WAKE: return "event occurs [duration] after waking.";
            case AC: return "event occurs [duration] before a meal (from the Latin ante cibus).";
            case ACM: return "event occurs [duration] before breakfast (from the Latin ante cibus matutinus).";
            case ACD: return "event occurs [duration] before lunch (from the Latin ante cibus diurnus).";
            case ACV: return "event occurs [duration] before dinner (from the Latin ante cibus vespertinus).";
            case PC: return "event occurs [duration] after a meal (from the Latin post cibus).";
            case PCM: return "event occurs [duration] after breakfast (from the Latin post cibus matutinus).";
            case PCD: return "event occurs [duration] after lunch (from the Latin post cibus diurnus).";
            case PCV: return "event occurs [duration] after dinner (from the Latin post cibus vespertinus).";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case HS: return "HS";
            case WAKE: return "WAKE";
            case AC: return "AC";
            case ACM: return "ACM";
            case ACD: return "ACD";
            case ACV: return "ACV";
            case PC: return "PC";
            case PCM: return "PCM";
            case PCD: return "PCD";
            case PCV: return "PCV";
            default: return "?";
          }
        }
    }

  public static class EventTimingEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("HS".equals(codeString))
          return EventTiming.HS;
        if ("WAKE".equals(codeString))
          return EventTiming.WAKE;
        if ("AC".equals(codeString))
          return EventTiming.AC;
        if ("ACM".equals(codeString))
          return EventTiming.ACM;
        if ("ACD".equals(codeString))
          return EventTiming.ACD;
        if ("ACV".equals(codeString))
          return EventTiming.ACV;
        if ("PC".equals(codeString))
          return EventTiming.PC;
        if ("PCM".equals(codeString))
          return EventTiming.PCM;
        if ("PCD".equals(codeString))
          return EventTiming.PCD;
        if ("PCV".equals(codeString))
          return EventTiming.PCV;
        throw new Exception("Unknown EventTiming code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == EventTiming.HS)
        return "HS";
      if (code == EventTiming.WAKE)
        return "WAKE";
      if (code == EventTiming.AC)
        return "AC";
      if (code == EventTiming.ACM)
        return "ACM";
      if (code == EventTiming.ACD)
        return "ACD";
      if (code == EventTiming.ACV)
        return "ACV";
      if (code == EventTiming.PC)
        return "PC";
      if (code == EventTiming.PCM)
        return "PCM";
      if (code == EventTiming.PCD)
        return "PCD";
      if (code == EventTiming.PCV)
        return "PCV";
      return "?";
      }
    }

    public enum UnitsOfTime {
        S, // second.
        MIN, // minute.
        H, // hour.
        D, // day.
        WK, // week.
        MO, // month.
        A, // year.
        NULL; // added to help the parsers
        public static UnitsOfTime fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("s".equals(codeString))
          return S;
        if ("min".equals(codeString))
          return MIN;
        if ("h".equals(codeString))
          return H;
        if ("d".equals(codeString))
          return D;
        if ("wk".equals(codeString))
          return WK;
        if ("mo".equals(codeString))
          return MO;
        if ("a".equals(codeString))
          return A;
        throw new Exception("Unknown UnitsOfTime code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case S: return "s";
            case MIN: return "min";
            case H: return "h";
            case D: return "d";
            case WK: return "wk";
            case MO: return "mo";
            case A: return "a";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case S: return "second.";
            case MIN: return "minute.";
            case H: return "hour.";
            case D: return "day.";
            case WK: return "week.";
            case MO: return "month.";
            case A: return "year.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case S: return "s";
            case MIN: return "min";
            case H: return "h";
            case D: return "d";
            case WK: return "wk";
            case MO: return "mo";
            case A: return "a";
            default: return "?";
          }
        }
    }

  public static class UnitsOfTimeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("s".equals(codeString))
          return UnitsOfTime.S;
        if ("min".equals(codeString))
          return UnitsOfTime.MIN;
        if ("h".equals(codeString))
          return UnitsOfTime.H;
        if ("d".equals(codeString))
          return UnitsOfTime.D;
        if ("wk".equals(codeString))
          return UnitsOfTime.WK;
        if ("mo".equals(codeString))
          return UnitsOfTime.MO;
        if ("a".equals(codeString))
          return UnitsOfTime.A;
        throw new Exception("Unknown UnitsOfTime code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == UnitsOfTime.S)
        return "s";
      if (code == UnitsOfTime.MIN)
        return "min";
      if (code == UnitsOfTime.H)
        return "h";
      if (code == UnitsOfTime.D)
        return "d";
      if (code == UnitsOfTime.WK)
        return "wk";
      if (code == UnitsOfTime.MO)
        return "mo";
      if (code == UnitsOfTime.A)
        return "a";
      return "?";
      }
    }

    public static class TimingRepeatComponent extends Element {
        /**
         * Indicates how often the event should occur.
         */
        protected IntegerType frequency;

        /**
         * Identifies the occurrence of daily life that determines timing.
         */
        protected Enumeration<EventTiming> when;

        /**
         * How long each repetition should last.
         */
        protected DecimalType duration;

        /**
         * The units of time for the duration.
         */
        protected Enumeration<UnitsOfTime> units;

        /**
         * A total count of the desired number of repetitions.
         */
        protected IntegerType count;

        /**
         * When to stop repeating the timing schedule.
         */
        protected DateTimeType end;

        private static final long serialVersionUID = -615844988L;

      public TimingRepeatComponent() {
        super();
      }

      public TimingRepeatComponent(DecimalType duration, Enumeration<UnitsOfTime> units) {
        super();
        this.duration = duration;
        this.units = units;
      }

        /**
         * @return {@link #frequency} (Indicates how often the event should occur.). This is the underlying object with id, value and extensions. The accessor "getFrequency" gives direct access to the value
         */
        public IntegerType getFrequencyElement() { 
          return this.frequency;
        }

        /**
         * @param value {@link #frequency} (Indicates how often the event should occur.). This is the underlying object with id, value and extensions. The accessor "getFrequency" gives direct access to the value
         */
        public TimingRepeatComponent setFrequencyElement(IntegerType value) { 
          this.frequency = value;
          return this;
        }

        /**
         * @return Indicates how often the event should occur.
         */
        public int getFrequency() { 
          return this.frequency == null ? null : this.frequency.getValue();
        }

        /**
         * @param value Indicates how often the event should occur.
         */
        public TimingRepeatComponent setFrequency(int value) { 
          if (value == -1)
            this.frequency = null;
          else {
            if (this.frequency == null)
              this.frequency = new IntegerType();
            this.frequency.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #when} (Identifies the occurrence of daily life that determines timing.). This is the underlying object with id, value and extensions. The accessor "getWhen" gives direct access to the value
         */
        public Enumeration<EventTiming> getWhenElement() { 
          return this.when;
        }

        /**
         * @param value {@link #when} (Identifies the occurrence of daily life that determines timing.). This is the underlying object with id, value and extensions. The accessor "getWhen" gives direct access to the value
         */
        public TimingRepeatComponent setWhenElement(Enumeration<EventTiming> value) { 
          this.when = value;
          return this;
        }

        /**
         * @return Identifies the occurrence of daily life that determines timing.
         */
        public EventTiming getWhen() { 
          return this.when == null ? null : this.when.getValue();
        }

        /**
         * @param value Identifies the occurrence of daily life that determines timing.
         */
        public TimingRepeatComponent setWhen(EventTiming value) { 
          if (value == null)
            this.when = null;
          else {
            if (this.when == null)
              this.when = new Enumeration<EventTiming>();
            this.when.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #duration} (How long each repetition should last.). This is the underlying object with id, value and extensions. The accessor "getDuration" gives direct access to the value
         */
        public DecimalType getDurationElement() { 
          return this.duration;
        }

        /**
         * @param value {@link #duration} (How long each repetition should last.). This is the underlying object with id, value and extensions. The accessor "getDuration" gives direct access to the value
         */
        public TimingRepeatComponent setDurationElement(DecimalType value) { 
          this.duration = value;
          return this;
        }

        /**
         * @return How long each repetition should last.
         */
        public BigDecimal getDuration() { 
          return this.duration == null ? null : this.duration.getValue();
        }

        /**
         * @param value How long each repetition should last.
         */
        public TimingRepeatComponent setDuration(BigDecimal value) { 
            if (this.duration == null)
              this.duration = new DecimalType();
            this.duration.setValue(value);
          return this;
        }

        /**
         * @return {@link #units} (The units of time for the duration.). This is the underlying object with id, value and extensions. The accessor "getUnits" gives direct access to the value
         */
        public Enumeration<UnitsOfTime> getUnitsElement() { 
          return this.units;
        }

        /**
         * @param value {@link #units} (The units of time for the duration.). This is the underlying object with id, value and extensions. The accessor "getUnits" gives direct access to the value
         */
        public TimingRepeatComponent setUnitsElement(Enumeration<UnitsOfTime> value) { 
          this.units = value;
          return this;
        }

        /**
         * @return The units of time for the duration.
         */
        public UnitsOfTime getUnits() { 
          return this.units == null ? null : this.units.getValue();
        }

        /**
         * @param value The units of time for the duration.
         */
        public TimingRepeatComponent setUnits(UnitsOfTime value) { 
            if (this.units == null)
              this.units = new Enumeration<UnitsOfTime>();
            this.units.setValue(value);
          return this;
        }

        /**
         * @return {@link #count} (A total count of the desired number of repetitions.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public IntegerType getCountElement() { 
          return this.count;
        }

        /**
         * @param value {@link #count} (A total count of the desired number of repetitions.). This is the underlying object with id, value and extensions. The accessor "getCount" gives direct access to the value
         */
        public TimingRepeatComponent setCountElement(IntegerType value) { 
          this.count = value;
          return this;
        }

        /**
         * @return A total count of the desired number of repetitions.
         */
        public int getCount() { 
          return this.count == null ? null : this.count.getValue();
        }

        /**
         * @param value A total count of the desired number of repetitions.
         */
        public TimingRepeatComponent setCount(int value) { 
          if (value == -1)
            this.count = null;
          else {
            if (this.count == null)
              this.count = new IntegerType();
            this.count.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #end} (When to stop repeating the timing schedule.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
         */
        public DateTimeType getEndElement() { 
          return this.end;
        }

        /**
         * @param value {@link #end} (When to stop repeating the timing schedule.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
         */
        public TimingRepeatComponent setEndElement(DateTimeType value) { 
          this.end = value;
          return this;
        }

        /**
         * @return When to stop repeating the timing schedule.
         */
        public DateAndTime getEnd() { 
          return this.end == null ? null : this.end.getValue();
        }

        /**
         * @param value When to stop repeating the timing schedule.
         */
        public TimingRepeatComponent setEnd(DateAndTime value) { 
          if (value == null)
            this.end = null;
          else {
            if (this.end == null)
              this.end = new DateTimeType();
            this.end.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("frequency", "integer", "Indicates how often the event should occur.", 0, java.lang.Integer.MAX_VALUE, frequency));
          childrenList.add(new Property("when", "code", "Identifies the occurrence of daily life that determines timing.", 0, java.lang.Integer.MAX_VALUE, when));
          childrenList.add(new Property("duration", "decimal", "How long each repetition should last.", 0, java.lang.Integer.MAX_VALUE, duration));
          childrenList.add(new Property("units", "code", "The units of time for the duration.", 0, java.lang.Integer.MAX_VALUE, units));
          childrenList.add(new Property("count", "integer", "A total count of the desired number of repetitions.", 0, java.lang.Integer.MAX_VALUE, count));
          childrenList.add(new Property("end", "dateTime", "When to stop repeating the timing schedule.", 0, java.lang.Integer.MAX_VALUE, end));
        }

      public TimingRepeatComponent copy() {
        TimingRepeatComponent dst = new TimingRepeatComponent();
        copyValues(dst);
        dst.frequency = frequency == null ? null : frequency.copy();
        dst.when = when == null ? null : when.copy();
        dst.duration = duration == null ? null : duration.copy();
        dst.units = units == null ? null : units.copy();
        dst.count = count == null ? null : count.copy();
        dst.end = end == null ? null : end.copy();
        return dst;
      }

  }

    /**
     * Identifies specific time periods when the event should occur.
     */
    protected List<Period> event = new ArrayList<Period>();

    /**
     * Identifies a repeating pattern to the intended time periods.
     */
    protected TimingRepeatComponent repeat;

    private static final long serialVersionUID = 1142511916L;

    public Timing() {
      super();
    }

    /**
     * @return {@link #event} (Identifies specific time periods when the event should occur.)
     */
    public List<Period> getEvent() { 
      return this.event;
    }

    /**
     * @return {@link #event} (Identifies specific time periods when the event should occur.)
     */
    // syntactic sugar
    public Period addEvent() { //3
      Period t = new Period();
      this.event.add(t);
      return t;
    }

    /**
     * @return {@link #repeat} (Identifies a repeating pattern to the intended time periods.)
     */
    public TimingRepeatComponent getRepeat() { 
      return this.repeat;
    }

    /**
     * @param value {@link #repeat} (Identifies a repeating pattern to the intended time periods.)
     */
    public Timing setRepeat(TimingRepeatComponent value) { 
      this.repeat = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("event", "Period", "Identifies specific time periods when the event should occur.", 0, java.lang.Integer.MAX_VALUE, event));
        childrenList.add(new Property("repeat", "", "Identifies a repeating pattern to the intended time periods.", 0, java.lang.Integer.MAX_VALUE, repeat));
      }

      public Timing copy() {
        Timing dst = new Timing();
        copyValues(dst);
        dst.event = new ArrayList<Period>();
        for (Period i : event)
          dst.event.add(i.copy());
        dst.repeat = repeat == null ? null : repeat.copy();
        return dst;
      }

      protected Timing typedCopy() {
        return copy();
      }


}

