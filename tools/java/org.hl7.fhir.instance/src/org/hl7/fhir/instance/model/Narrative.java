package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011+, HL7, Inc.
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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import org.hl7.fhir.utilities.Utilities;
/**
 * A human-readable formatted text, including images.
 */
public class Narrative extends Element {

    public enum NarrativeStatus {
        GENERATED, // The contents of the narrative are entirely generated from the structured data in the resource.
        EXTENSIONS, // The contents of the narrative are entirely generated from the structured data in the resource and some of the content is generated from extensions.
        ADDITIONAL, // The contents of the narrative contain additional information not found in the structured data.
        EMPTY, // the contents of the narrative are some equivalent of "No human-readable text provided for this resource".
        NULL; // added to help the parsers
        public static NarrativeStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("generated".equals(codeString))
          return GENERATED;
        if ("extensions".equals(codeString))
          return EXTENSIONS;
        if ("additional".equals(codeString))
          return ADDITIONAL;
        if ("empty".equals(codeString))
          return EMPTY;
        throw new Exception("Unknown NarrativeStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case GENERATED: return "generated";
            case EXTENSIONS: return "extensions";
            case ADDITIONAL: return "additional";
            case EMPTY: return "empty";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case GENERATED: return "The contents of the narrative are entirely generated from the structured data in the resource.";
            case EXTENSIONS: return "The contents of the narrative are entirely generated from the structured data in the resource and some of the content is generated from extensions.";
            case ADDITIONAL: return "The contents of the narrative contain additional information not found in the structured data.";
            case EMPTY: return "the contents of the narrative are some equivalent of 'No human-readable text provided for this resource'.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case GENERATED: return "generated";
            case EXTENSIONS: return "extensions";
            case ADDITIONAL: return "additional";
            case EMPTY: return "empty";
            default: return "?";
          }
        }
    }

  public static class NarrativeStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("generated".equals(codeString))
          return NarrativeStatus.GENERATED;
        if ("extensions".equals(codeString))
          return NarrativeStatus.EXTENSIONS;
        if ("additional".equals(codeString))
          return NarrativeStatus.ADDITIONAL;
        if ("empty".equals(codeString))
          return NarrativeStatus.EMPTY;
        throw new Exception("Unknown NarrativeStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NarrativeStatus.GENERATED)
        return "generated";
      if (code == NarrativeStatus.EXTENSIONS)
        return "extensions";
      if (code == NarrativeStatus.ADDITIONAL)
        return "additional";
      if (code == NarrativeStatus.EMPTY)
        return "empty";
      return "?";
      }
    }

    /**
     * The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data.
     */
    protected Enumeration<NarrativeStatus> status;

    /**
     * The actual narrative content, a stripped down version of XHTML.
     */
    protected XhtmlNode div;

    private static final long serialVersionUID = 1463852859L;

    public Narrative() {
      super();
    }

    public Narrative(Enumeration<NarrativeStatus> status, XhtmlNode div) {
      super();
      this.status = status;
      this.div = div;
    }

    /**
     * @return {@link #status} (The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<NarrativeStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Narrative setStatusElement(Enumeration<NarrativeStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data.
     */
    public NarrativeStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data.
     */
    public Narrative setStatus(NarrativeStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<NarrativeStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #div} (The actual narrative content, a stripped down version of XHTML.)
     */
    public XhtmlNode getDiv() { 
      return this.div;
    }

    /**
     * @param value {@link #div} (The actual narrative content, a stripped down version of XHTML.)
     */
    public Narrative setDiv(XhtmlNode value) { 
      this.div = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("status", "code", "The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data.", 0, java.lang.Integer.MAX_VALUE, status));
      }

      public Narrative copy() {
        Narrative dst = new Narrative();
        copyValues(dst);
        dst.status = status == null ? null : status.copy();
        dst.div = div == null ? null : div.copy();
        return dst;
      }

      protected Narrative typedCopy() {
        return copy();
      }


}

