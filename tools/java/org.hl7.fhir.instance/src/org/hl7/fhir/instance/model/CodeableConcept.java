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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11

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
     * A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user or concept.
     */
    protected String_ text;

    public List<Coding> getCoding() { 
      return this.coding;
    }

    // syntactic sugar
    public Coding addCoding() { 
      Coding t = new Coding();
      this.coding.add(t);
      return t;
    }

    public String_ getText() { 
      return this.text;
    }

    public void setText(String_ value) { 
      this.text = value;
    }

    public String getTextSimple() { 
      return this.text == null ? null : this.text.getValue();
    }

    public void setTextSimple(String value) { 
      if (value == null)
        this.text = null;
      else {
        if (this.text == null)
          this.text = new String_();
        this.text.setValue(value);
      }
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

