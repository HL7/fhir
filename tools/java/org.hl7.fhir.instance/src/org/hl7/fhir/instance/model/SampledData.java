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
     * The Number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.
     */
    protected Integer dimensions;

    /**
     * A series of data points separated by a single space (character u20). The special values "E" (error), "L" (below detection limit) and "U" (above detection limit) can also be used.
     */
    protected String_ data;

    public SampledData() {
      super();
    }

    public Quantity getOrigin() { 
      return this.origin;
    }

    public SampledData setOrigin(Quantity value) { 
      this.origin = value;
      return this;
    }

    public Decimal getPeriod() { 
      return this.period;
    }

    public SampledData setPeriod(Decimal value) { 
      this.period = value;
      return this;
    }

    public BigDecimal getPeriodSimple() { 
      return this.period == null ? null : this.period.getValue();
    }

    public SampledData setPeriodSimple(BigDecimal value) { 
      if (value == null)
        this.period = null;
      else {
        if (this.period == null)
          this.period = new Decimal();
        this.period.setValue(value);
      }
      return this;
    }

    public Decimal getFactor() { 
      return this.factor;
    }

    public SampledData setFactor(Decimal value) { 
      this.factor = value;
      return this;
    }

    public BigDecimal getFactorSimple() { 
      return this.factor == null ? null : this.factor.getValue();
    }

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

    public Decimal getLowerLimit() { 
      return this.lowerLimit;
    }

    public SampledData setLowerLimit(Decimal value) { 
      this.lowerLimit = value;
      return this;
    }

    public BigDecimal getLowerLimitSimple() { 
      return this.lowerLimit == null ? null : this.lowerLimit.getValue();
    }

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

    public Decimal getUpperLimit() { 
      return this.upperLimit;
    }

    public SampledData setUpperLimit(Decimal value) { 
      this.upperLimit = value;
      return this;
    }

    public BigDecimal getUpperLimitSimple() { 
      return this.upperLimit == null ? null : this.upperLimit.getValue();
    }

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

    public Integer getDimensions() { 
      return this.dimensions;
    }

    public SampledData setDimensions(Integer value) { 
      this.dimensions = value;
      return this;
    }

    public int getDimensionsSimple() { 
      return this.dimensions == null ? null : this.dimensions.getValue();
    }

    public SampledData setDimensionsSimple(int value) { 
      if (value == -1)
        this.dimensions = null;
      else {
        if (this.dimensions == null)
          this.dimensions = new Integer();
        this.dimensions.setValue(value);
      }
      return this;
    }

    public String_ getData() { 
      return this.data;
    }

    public SampledData setData(String_ value) { 
      this.data = value;
      return this;
    }

    public String getDataSimple() { 
      return this.data == null ? null : this.data.getValue();
    }

    public SampledData setDataSimple(String value) { 
      if (value == null)
        this.data = null;
      else {
        if (this.data == null)
          this.data = new String_();
        this.data.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("origin", "Quantity", "The base quantity that a measured value of zero represents. In addition, this provides the units of the entire measurement series.", 0, java.lang.Integer.MAX_VALUE, origin));
        childrenList.add(new Property("period", "decimal", "The length of time between sampling times, measured in milliseconds.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("factor", "decimal", "A correction factor that is applied to the sampled data points before they are added to the origin.", 0, java.lang.Integer.MAX_VALUE, factor));
        childrenList.add(new Property("lowerLimit", "decimal", "The lower limit of detection of the measured points. This is needed if any of the data points have the value 'L' (lower than detection limit).", 0, java.lang.Integer.MAX_VALUE, lowerLimit));
        childrenList.add(new Property("upperLimit", "decimal", "The upper limit of detection of the measured points. This is needed if any of the data points have the value 'U' (higher than detection limit).", 0, java.lang.Integer.MAX_VALUE, upperLimit));
        childrenList.add(new Property("dimensions", "integer", "The Number of sample points at each time point. If this value is greater than one, then the dimensions will be interlaced - all the sample points for a point in time will be recorded at once.", 0, java.lang.Integer.MAX_VALUE, dimensions));
        childrenList.add(new Property("data", "string", "A series of data points separated by a single space (character u20). The special values 'E' (error), 'L' (below detection limit) and 'U' (above detection limit) can also be used.", 0, java.lang.Integer.MAX_VALUE, data));
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

