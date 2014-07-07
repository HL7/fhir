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

/**
 * A time period defined by a start and end date and optionally time.
 */
public class Period extends Type {

    /**
     * The start of the period. The boundary is inclusive.
     */
    protected DateTime start;

    /**
     * The end of the period. If the end of the period is missing, it means that the period is ongoing.
     */
    protected DateTime end;

    private static final long serialVersionUID = -1935473081L;

    public Period() {
      super();
    }

    /**
     * @return {@link #start} (The start of the period. The boundary is inclusive.)
     */
    public DateTime getStart() { 
      return this.start;
    }

    /**
     * @param value {@link #start} (The start of the period. The boundary is inclusive.)
     */
    public Period setStart(DateTime value) { 
      this.start = value;
      return this;
    }

    /**
     * @return The start of the period. The boundary is inclusive.
     */
    public DateAndTime getStartSimple() { 
      return this.start == null ? null : this.start.getValue();
    }

    /**
     * @param value The start of the period. The boundary is inclusive.
     */
    public Period setStartSimple(DateAndTime value) { 
      if (value == null)
        this.start = null;
      else {
        if (this.start == null)
          this.start = new DateTime();
        this.start.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #end} (The end of the period. If the end of the period is missing, it means that the period is ongoing.)
     */
    public DateTime getEnd() { 
      return this.end;
    }

    /**
     * @param value {@link #end} (The end of the period. If the end of the period is missing, it means that the period is ongoing.)
     */
    public Period setEnd(DateTime value) { 
      this.end = value;
      return this;
    }

    /**
     * @return The end of the period. If the end of the period is missing, it means that the period is ongoing.
     */
    public DateAndTime getEndSimple() { 
      return this.end == null ? null : this.end.getValue();
    }

    /**
     * @param value The end of the period. If the end of the period is missing, it means that the period is ongoing.
     */
    public Period setEndSimple(DateAndTime value) { 
      if (value == null)
        this.end = null;
      else {
        if (this.end == null)
          this.end = new DateTime();
        this.end.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("start", "dateTime", "The start of the period. The boundary is inclusive.", 0, java.lang.Integer.MAX_VALUE, start));
        childrenList.add(new Property("end", "dateTime", "The end of the period. If the end of the period is missing, it means that the period is ongoing.", 0, java.lang.Integer.MAX_VALUE, end));
      }

      public Period copy() {
        Period dst = new Period();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        return dst;
      }

      protected Period typedCopy() {
        return copy();
      }


}

