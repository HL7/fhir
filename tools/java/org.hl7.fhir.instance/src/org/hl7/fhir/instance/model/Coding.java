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

/**
 * A reference to a code defined by a terminology system.
 */
public class Coding extends Type {

    /**
     * The identification of the code system that defines the meaning of the symbol in the code.
     */
    protected Uri system;

    /**
     * The version of the code system which was used when choosing this code. Note that a well-maintained code system does not need the version reported, because the meaning of codes is consistent across versions. However this cannot consistently be assured. and when it is not, the version SHOULD be exchanged.
     */
    protected String_ version;

    /**
     * A symbol in syntax defined by the system. The symbol may be a predefined code or an expression in a syntax defined by the coding system (e.g. post-coordination).
     */
    protected Code code;

    /**
     * A representation of the meaning of the code in the system, following the rules laid out by the system.
     */
    protected String_ display;

    /**
     * Indicates that this code was chosen by a user directly - i.e. off a pick list of available codes.
     */
    protected Boolean primary;

    /**
     * The set of possible coded values this coding was chosen from or constrained by.
     */
    protected ResourceReference valueSet;

    public Uri getSystem() { 
      return this.system;
    }

    public void setSystem(Uri value) { 
      this.system = value;
    }

    public String getSystemSimple() { 
      return this.system == null ? null : this.system.getValue();
    }

    public void setSystemSimple(String value) { 
      if (value == null)
        this.system = null;
      else {
        if (this.system == null)
          this.system = new Uri();
        this.system.setValue(value);
      }
    }

    public String_ getVersion() { 
      return this.version;
    }

    public void setVersion(String_ value) { 
      this.version = value;
    }

    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    public void setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new String_();
        this.version.setValue(value);
      }
    }

    public Code getCode() { 
      return this.code;
    }

    public void setCode(Code value) { 
      this.code = value;
    }

    public String getCodeSimple() { 
      return this.code == null ? null : this.code.getValue();
    }

    public void setCodeSimple(String value) { 
      if (value == null)
        this.code = null;
      else {
        if (this.code == null)
          this.code = new Code();
        this.code.setValue(value);
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

    public Boolean getPrimary() { 
      return this.primary;
    }

    public void setPrimary(Boolean value) { 
      this.primary = value;
    }

    public boolean getPrimarySimple() { 
      return this.primary == null ? null : this.primary.getValue();
    }

    public void setPrimarySimple(boolean value) { 
      if (value == false)
        this.primary = null;
      else {
        if (this.primary == null)
          this.primary = new Boolean();
        this.primary.setValue(value);
      }
    }

    public ResourceReference getValueSet() { 
      return this.valueSet;
    }

    public void setValueSet(ResourceReference value) { 
      this.valueSet = value;
    }

      public Coding copy() {
        Coding dst = new Coding();
        dst.system = system == null ? null : system.copy();
        dst.version = version == null ? null : version.copy();
        dst.code = code == null ? null : code.copy();
        dst.display = display == null ? null : display.copy();
        dst.primary = primary == null ? null : primary.copy();
        dst.valueSet = valueSet == null ? null : valueSet.copy();
        return dst;
      }

      protected Coding typedCopy() {
        return copy();
      }


}

