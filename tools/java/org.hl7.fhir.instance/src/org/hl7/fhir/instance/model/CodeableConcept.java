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
 * A concept that may be defined by a formal reference to a terminology or ontology or may be provided by text.
 */
public class CodeableConcept extends Type {

    /**
     * A reference to a code defined by a terminology system.
     */
    protected List<Coding> coding = new ArrayList<Coding>();

    /**
     * A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user.
     */
    protected StringType text;

    private static final long serialVersionUID = -642628655L;

    public CodeableConcept() {
      super();
    }

    /**
     * @return {@link #coding} (A reference to a code defined by a terminology system.)
     */
    public List<Coding> getCoding() { 
      return this.coding;
    }

    // syntactic sugar
    /**
     * @return {@link #coding} (A reference to a code defined by a terminology system.)
     */
    public Coding addCoding() { 
      Coding t = new Coding();
      this.coding.add(t);
      return t;
    }

    /**
     * @return {@link #text} (A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user.)
     */
    public StringType getText() { 
      return this.text;
    }

    /**
     * @param value {@link #text} (A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user.)
     */
    public CodeableConcept setText(StringType value) { 
      this.text = value;
      return this;
    }

    /**
     * @return A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user.
     */
    public String getTextSimple() { 
      return this.text == null ? null : this.text.getValue();
    }

    /**
     * @param value A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user.
     */
    public CodeableConcept setTextSimple(String value) { 
      if (value == null)
        this.text = null;
      else {
        if (this.text == null)
          this.text = new StringType();
        this.text.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("coding", "Coding", "A reference to a code defined by a terminology system.", 0, java.lang.Integer.MAX_VALUE, coding));
        childrenList.add(new Property("text", "string", "A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user.", 0, java.lang.Integer.MAX_VALUE, text));
      }

      public CodeableConcept copy() {
        CodeableConcept dst = new CodeableConcept();
        dst.coding = new ArrayList<Coding>();
        for (Coding i : coding)
          dst.coding.add(i.copy());
        dst.text = text == null ? null : text.copy();
        return dst;
      }

      protected CodeableConcept typedCopy() {
        return copy();
      }


}

