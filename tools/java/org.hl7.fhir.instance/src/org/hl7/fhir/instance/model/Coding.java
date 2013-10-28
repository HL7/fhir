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

    public Coding() {
      super();
    }

    public Uri getSystem() { 
      return this.system;
    }

    public Coding setSystem(Uri value) { 
      this.system = value;
      return this;
    }

    public String getSystemSimple() { 
      return this.system == null ? null : this.system.getValue();
    }

    public Coding setSystemSimple(String value) { 
      if (value == null)
        this.system = null;
      else {
        if (this.system == null)
          this.system = new Uri();
        this.system.setValue(value);
      }
      return this;
    }

    public String_ getVersion() { 
      return this.version;
    }

    public Coding setVersion(String_ value) { 
      this.version = value;
      return this;
    }

    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    public Coding setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new String_();
        this.version.setValue(value);
      }
      return this;
    }

    public Code getCode() { 
      return this.code;
    }

    public Coding setCode(Code value) { 
      this.code = value;
      return this;
    }

    public String getCodeSimple() { 
      return this.code == null ? null : this.code.getValue();
    }

    public Coding setCodeSimple(String value) { 
      if (value == null)
        this.code = null;
      else {
        if (this.code == null)
          this.code = new Code();
        this.code.setValue(value);
      }
      return this;
    }

    public String_ getDisplay() { 
      return this.display;
    }

    public Coding setDisplay(String_ value) { 
      this.display = value;
      return this;
    }

    public String getDisplaySimple() { 
      return this.display == null ? null : this.display.getValue();
    }

    public Coding setDisplaySimple(String value) { 
      if (value == null)
        this.display = null;
      else {
        if (this.display == null)
          this.display = new String_();
        this.display.setValue(value);
      }
      return this;
    }

    public Boolean getPrimary() { 
      return this.primary;
    }

    public Coding setPrimary(Boolean value) { 
      this.primary = value;
      return this;
    }

    public boolean getPrimarySimple() { 
      return this.primary == null ? null : this.primary.getValue();
    }

    public Coding setPrimarySimple(boolean value) { 
      if (value == false)
        this.primary = null;
      else {
        if (this.primary == null)
          this.primary = new Boolean();
        this.primary.setValue(value);
      }
      return this;
    }

    public ResourceReference getValueSet() { 
      return this.valueSet;
    }

    public Coding setValueSet(ResourceReference value) { 
      this.valueSet = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("system", "uri", "The identification of the code system that defines the meaning of the symbol in the code.", 0, java.lang.Integer.MAX_VALUE, system));
        childrenList.add(new Property("version", "string", "The version of the code system which was used when choosing this code. Note that a well-maintained code system does not need the version reported, because the meaning of codes is consistent across versions. However this cannot consistently be assured. and when it is not, the version SHOULD be exchanged.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("code", "code", "A symbol in syntax defined by the system. The symbol may be a predefined code or an expression in a syntax defined by the coding system (e.g. post-coordination).", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("display", "string", "A representation of the meaning of the code in the system, following the rules laid out by the system.", 0, java.lang.Integer.MAX_VALUE, display));
        childrenList.add(new Property("primary", "boolean", "Indicates that this code was chosen by a user directly - i.e. off a pick list of available codes.", 0, java.lang.Integer.MAX_VALUE, primary));
        childrenList.add(new Property("valueSet", "Resource(ValueSet)", "The set of possible coded values this coding was chosen from or constrained by.", 0, java.lang.Integer.MAX_VALUE, valueSet));
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

