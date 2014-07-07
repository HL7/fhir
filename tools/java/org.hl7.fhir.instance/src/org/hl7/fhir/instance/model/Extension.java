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
 * Optional Extensions Element - found in all resources.
 */
public class Extension extends Element {

    /**
     * Source of the definition for the extension code - a logical name or a URL.
     */
    protected Uri url;

    /**
     * Value of extension - may be a resource or one of a constrained set of the data types (see Extensibility in the spec for list).
     */
    protected org.hl7.fhir.instance.model.Type value;

    private static final long serialVersionUID = -730688340L;

    public Extension() {
      super();
    }

    public Extension(Uri url) {
      super();
      this.url = url;
    }

    /**
     * @return {@link #url} (Source of the definition for the extension code - a logical name or a URL.)
     */
    public Uri getUrl() { 
      return this.url;
    }

    /**
     * @param value {@link #url} (Source of the definition for the extension code - a logical name or a URL.)
     */
    public Extension setUrl(Uri value) { 
      this.url = value;
      return this;
    }

    /**
     * @return Source of the definition for the extension code - a logical name or a URL.
     */
    public String getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    /**
     * @param value Source of the definition for the extension code - a logical name or a URL.
     */
    public Extension setUrlSimple(String value) { 
        if (this.url == null)
          this.url = new Uri();
        this.url.setValue(value);
      return this;
    }

    /**
     * @return {@link #value} (Value of extension - may be a resource or one of a constrained set of the data types (see Extensibility in the spec for list).)
     */
    public org.hl7.fhir.instance.model.Type getValue() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (Value of extension - may be a resource or one of a constrained set of the data types (see Extensibility in the spec for list).)
     */
    public Extension setValue(org.hl7.fhir.instance.model.Type value) { 
      this.value = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("url", "uri", "Source of the definition for the extension code - a logical name or a URL.", 0, java.lang.Integer.MAX_VALUE, url));
        childrenList.add(new Property("value[x]", "*", "Value of extension - may be a resource or one of a constrained set of the data types (see Extensibility in the spec for list).", 0, java.lang.Integer.MAX_VALUE, value));
      }

      public Extension copy() {
        Extension dst = new Extension();
        dst.url = url == null ? null : url.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

      protected Extension typedCopy() {
        return copy();
      }


}

