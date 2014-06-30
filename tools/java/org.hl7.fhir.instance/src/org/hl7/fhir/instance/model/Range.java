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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

import java.util.*;

/**
 * A set of ordered Quantities defined by a low and high limit.
 */
public class Range extends Type {

    /**
     * The low limit. The boundary is inclusive.
     */
    protected Quantity low;

    /**
     * The high limit. The boundary is inclusive.
     */
    protected Quantity high;

    private static final long serialVersionUID = -474933350L;

    public Range() {
      super();
    }

    /**
     * @return {@link #low} (The low limit. The boundary is inclusive.)
     */
    public Quantity getLow() { 
      return this.low;
    }

    /**
     * @param value {@link #low} (The low limit. The boundary is inclusive.)
     */
    public Range setLow(Quantity value) { 
      this.low = value;
      return this;
    }

    /**
     * @return {@link #high} (The high limit. The boundary is inclusive.)
     */
    public Quantity getHigh() { 
      return this.high;
    }

    /**
     * @param value {@link #high} (The high limit. The boundary is inclusive.)
     */
    public Range setHigh(Quantity value) { 
      this.high = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("low", "Quantity", "The low limit. The boundary is inclusive.", 0, java.lang.Integer.MAX_VALUE, low));
        childrenList.add(new Property("high", "Quantity", "The high limit. The boundary is inclusive.", 0, java.lang.Integer.MAX_VALUE, high));
      }

      public Range copy() {
        Range dst = new Range();
        dst.low = low == null ? null : low.copy();
        dst.high = high == null ? null : high.copy();
        return dst;
      }

      protected Range typedCopy() {
        return copy();
      }


}

