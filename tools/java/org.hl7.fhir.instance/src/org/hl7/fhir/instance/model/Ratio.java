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
 * A relationship of two Quantity values - expressed as a numerator and a denominator.
 */
public class Ratio extends Type {

    /**
     * The value of the numerator.
     */
    protected Quantity numerator;

    /**
     * The value of the denominator.
     */
    protected Quantity denominator;

    private static final long serialVersionUID = 479922563L;

    public Ratio() {
      super();
    }

    /**
     * @return {@link #numerator} (The value of the numerator.)
     */
    public Quantity getNumerator() { 
      return this.numerator;
    }

    /**
     * @param value {@link #numerator} (The value of the numerator.)
     */
    public Ratio setNumerator(Quantity value) { 
      this.numerator = value;
      return this;
    }

    /**
     * @return {@link #denominator} (The value of the denominator.)
     */
    public Quantity getDenominator() { 
      return this.denominator;
    }

    /**
     * @param value {@link #denominator} (The value of the denominator.)
     */
    public Ratio setDenominator(Quantity value) { 
      this.denominator = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("numerator", "Quantity", "The value of the numerator.", 0, java.lang.Integer.MAX_VALUE, numerator));
        childrenList.add(new Property("denominator", "Quantity", "The value of the denominator.", 0, java.lang.Integer.MAX_VALUE, denominator));
      }

      public Ratio copy() {
        Ratio dst = new Ratio();
        dst.numerator = numerator == null ? null : numerator.copy();
        dst.denominator = denominator == null ? null : denominator.copy();
        return dst;
      }

      protected Ratio typedCopy() {
        return copy();
      }


}

