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

import java.math.*;
/**
 * A series of measurements taken by a device, with upper and lower limits. There may be more than one dimension in the data.
 */
public class SampledData extends Type {

    /**
     * The base quantity that a measured value of zero represents. In addition, this provides the units of the entire measurement series.
     */
    protected Quantity origin;

    /**
     * The length of time between sampling times, measured in milliseconds.
     */
    protected Decimal period;

    /**
     * A correction factor that is applied to the sampled data points before they are added to the origin.
     */
    protected Decimal factor;

    /**
     * The lower limit of detection of the measured points. This is needed if any of the data points have the value "L" (lower than detection limit).
     */
    protected Decimal lowerLimit;

    /**
     * The upper limit of detection of the measured points. This is needed if any of the data points have the value "U" (higher than detection limit).
     */
    protected Decimal upperLimit;

    /**
     * The number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.
     */
    protected Integer dimensions;

    /**
     * A series of data points which are decimal values separated by a single space (character u20). The special values "E" (error), "L" (below detection limit) and "U" (above detection limit) can also be used in place of a decimal value.
     */
    protected String_ data;

    private static final long serialVersionUID = 1739399349L;

    public SampledData() {
      super();
    }

    public SampledData(Quantity origin, Decimal period, Integer dimensions, String_ data) {
      super();
      this.origin = origin;
      this.period = period;
      this.dimensions = dimensions;
      this.data = data;
    }

    /**
     * @return {@link #origin} (The base quantity that a measured value of zero represents. In addition, this provides the units of the entire measurement series.)
     */
    public Quantity getOrigin() { 
      return this.origin;
    }

    /**
     * @param value {@link #origin} (The base quantity that a measured value of zero represents. In addition, this provides the units of the entire measurement series.)
     */
    public SampledData setOrigin(Quantity value) { 
      this.origin = value;
      return this;
    }

    /**
     * @return {@link #period} (The length of time between sampling times, measured in milliseconds.)
     */
    public Decimal getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (The length of time between sampling times, measured in milliseconds.)
     */
    public SampledData setPeriod(Decimal value) { 
      this.period = value;
      return this;
    }

    /**
     * @return The length of time between sampling times, measured in milliseconds.
     */
    public BigDecimal getPeriodSimple() { 
      return this.period == null ? null : this.period.getValue();
    }

    /**
     * @param value The length of time between sampling times, measured in milliseconds.
     */
    public SampledData setPeriodSimple(BigDecimal value) { 
        if (this.period == null)
          this.period = new Decimal();
        this.period.setValue(value);
      return this;
    }

    /**
     * @return {@link #factor} (A correction factor that is applied to the sampled data points before they are added to the origin.)
     */
    public Decimal getFactor() { 
      return this.factor;
    }

    /**
     * @param value {@link #factor} (A correction factor that is applied to the sampled data points before they are added to the origin.)
     */
    public SampledData setFactor(Decimal value) { 
      this.factor = value;
      return this;
    }

    /**
     * @return A correction factor that is applied to the sampled data points before they are added to the origin.
     */
    public BigDecimal getFactorSimple() { 
      return this.factor == null ? null : this.factor.getValue();
    }

    /**
     * @param value A correction factor that is applied to the sampled data points before they are added to the origin.
     */
    public SampledData setFactorSimple(BigDecimal value) { 
      if (value == null)
        this.factor = null;
      else {
        if (this.factor == null)
          this.factor = new Decimal();
        this.factor.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #lowerLimit} (The lower limit of detection of the measured points. This is needed if any of the data points have the value "L" (lower than detection limit).)
     */
    public Decimal getLowerLimit() { 
      return this.lowerLimit;
    }

    /**
     * @param value {@link #lowerLimit} (The lower limit of detection of the measured points. This is needed if any of the data points have the value "L" (lower than detection limit).)
     */
    public SampledData setLowerLimit(Decimal value) { 
      this.lowerLimit = value;
      return this;
    }

    /**
     * @return The lower limit of detection of the measured points. This is needed if any of the data points have the value "L" (lower than detection limit).
     */
    public BigDecimal getLowerLimitSimple() { 
      return this.lowerLimit == null ? null : this.lowerLimit.getValue();
    }

    /**
     * @param value The lower limit of detection of the measured points. This is needed if any of the data points have the value "L" (lower than detection limit).
     */
    public SampledData setLowerLimitSimple(BigDecimal value) { 
      if (value == null)
        this.lowerLimit = null;
      else {
        if (this.lowerLimit == null)
          this.lowerLimit = new Decimal();
        this.lowerLimit.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #upperLimit} (The upper limit of detection of the measured points. This is needed if any of the data points have the value "U" (higher than detection limit).)
     */
    public Decimal getUpperLimit() { 
      return this.upperLimit;
    }

    /**
     * @param value {@link #upperLimit} (The upper limit of detection of the measured points. This is needed if any of the data points have the value "U" (higher than detection limit).)
     */
    public SampledData setUpperLimit(Decimal value) { 
      this.upperLimit = value;
      return this;
    }

    /**
     * @return The upper limit of detection of the measured points. This is needed if any of the data points have the value "U" (higher than detection limit).
     */
    public BigDecimal getUpperLimitSimple() { 
      return this.upperLimit == null ? null : this.upperLimit.getValue();
    }

    /**
     * @param value The upper limit of detection of the measured points. This is needed if any of the data points have the value "U" (higher than detection limit).
     */
    public SampledData setUpperLimitSimple(BigDecimal value) { 
      if (value == null)
        this.upperLimit = null;
      else {
        if (this.upperLimit == null)
          this.upperLimit = new Decimal();
        this.upperLimit.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #dimensions} (The number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.)
     */
    public Integer getDimensions() { 
      return this.dimensions;
    }

    /**
     * @param value {@link #dimensions} (The number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.)
     */
    public SampledData setDimensions(Integer value) { 
      this.dimensions = value;
      return this;
    }

    /**
     * @return The number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.
     */
    public int getDimensionsSimple() { 
      return this.dimensions == null ? null : this.dimensions.getValue();
    }

    /**
     * @param value The number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.
     */
    public SampledData setDimensionsSimple(int value) { 
        if (this.dimensions == null)
          this.dimensions = new Integer();
        this.dimensions.setValue(value);
      return this;
    }

    /**
     * @return {@link #data} (A series of data points which are decimal values separated by a single space (character u20). The special values "E" (error), "L" (below detection limit) and "U" (above detection limit) can also be used in place of a decimal value.)
     */
    public String_ getData() { 
      return this.data;
    }

    /**
     * @param value {@link #data} (A series of data points which are decimal values separated by a single space (character u20). The special values "E" (error), "L" (below detection limit) and "U" (above detection limit) can also be used in place of a decimal value.)
     */
    public SampledData setData(String_ value) { 
      this.data = value;
      return this;
    }

    /**
     * @return A series of data points which are decimal values separated by a single space (character u20). The special values "E" (error), "L" (below detection limit) and "U" (above detection limit) can also be used in place of a decimal value.
     */
    public String getDataSimple() { 
      return this.data == null ? null : this.data.getValue();
    }

    /**
     * @param value A series of data points which are decimal values separated by a single space (character u20). The special values "E" (error), "L" (below detection limit) and "U" (above detection limit) can also be used in place of a decimal value.
     */
    public SampledData setDataSimple(String value) { 
        if (this.data == null)
          this.data = new String_();
        this.data.setValue(value);
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("origin", "Quantity", "The base quantity that a measured value of zero represents. In addition, this provides the units of the entire measurement series.", 0, java.lang.Integer.MAX_VALUE, origin));
        childrenList.add(new Property("period", "decimal", "The length of time between sampling times, measured in milliseconds.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("factor", "decimal", "A correction factor that is applied to the sampled data points before they are added to the origin.", 0, java.lang.Integer.MAX_VALUE, factor));
        childrenList.add(new Property("lowerLimit", "decimal", "The lower limit of detection of the measured points. This is needed if any of the data points have the value 'L' (lower than detection limit).", 0, java.lang.Integer.MAX_VALUE, lowerLimit));
        childrenList.add(new Property("upperLimit", "decimal", "The upper limit of detection of the measured points. This is needed if any of the data points have the value 'U' (higher than detection limit).", 0, java.lang.Integer.MAX_VALUE, upperLimit));
        childrenList.add(new Property("dimensions", "integer", "The number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.", 0, java.lang.Integer.MAX_VALUE, dimensions));
        childrenList.add(new Property("data", "string", "A series of data points which are decimal values separated by a single space (character u20). The special values 'E' (error), 'L' (below detection limit) and 'U' (above detection limit) can also be used in place of a decimal value.", 0, java.lang.Integer.MAX_VALUE, data));
      }

      public SampledData copy() {
        SampledData dst = new SampledData();
        dst.origin = origin == null ? null : origin.copy();
        dst.period = period == null ? null : period.copy();
        dst.factor = factor == null ? null : factor.copy();
        dst.lowerLimit = lowerLimit == null ? null : lowerLimit.copy();
        dst.upperLimit = upperLimit == null ? null : upperLimit.copy();
        dst.dimensions = dimensions == null ? null : dimensions.copy();
        dst.data = data == null ? null : data.copy();
        return dst;
      }

      protected SampledData typedCopy() {
        return copy();
      }


}

