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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

/**
 * A reference from one resource to another.
 */
public class ResourceReference extends Type {

    /**
     * The name of one of the resource types defined in this specification to identify the type of the resource being referenced.
     */
    protected Code type;

    /**
     * A reference to a location at which the other resource is found. The reference may a relative reference, in which case it is relative to the service base URL, or an absolute URL that resolves to the location where the resource is found. The reference may be version specific or not. If the reference is not to a FHIR RESTful server, then it should be assumed to be version specific. Internal fragment references (start with '#') refer to contained resources.
     */
    protected String_ reference;

    /**
     * Plain text narrative that identifies the resource in addition to the resource reference.
     */
    protected String_ display;

    public Code getType() { 
      return this.type;
    }

    public void setType(Code value) { 
      this.type = value;
    }

    public String getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    public void setTypeSimple(String value) { 
      if (value == null)
        this.type = null;
      else {
        if (this.type == null)
          this.type = new Code();
        this.type.setValue(value);
      }
    }

    public String_ getReference() { 
      return this.reference;
    }

    public void setReference(String_ value) { 
      this.reference = value;
    }

    public String getReferenceSimple() { 
      return this.reference == null ? null : this.reference.getValue();
    }

    public void setReferenceSimple(String value) { 
      if (value == null)
        this.reference = null;
      else {
        if (this.reference == null)
          this.reference = new String_();
        this.reference.setValue(value);
      }
    }

    public String_ getDisplay() { 
      return this.display;
    }

    public void setDisplay(String_ value) { 
      this.display = value;
    }

    public String getDisplaySimple() { 
      return this.display == null ? null : this.display.getValue();
    }

    public void setDisplaySimple(String value) { 
      if (value == null)
        this.display = null;
      else {
        if (this.display == null)
          this.display = new String_();
        this.display.setValue(value);
      }
    }

      public ResourceReference copy() {
        ResourceReference dst = new ResourceReference();
        dst.type = type == null ? null : type.copy();
        dst.reference = reference == null ? null : reference.copy();
        dst.display = display == null ? null : display.copy();
        return dst;
      }

      protected ResourceReference typedCopy() {
        return copy();
      }


}

