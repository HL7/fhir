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

// Generated on Fri, May 31, 2013 07:30+1000 for FHIR v0.09

import java.util.*;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

/**
 * A human readable formatted text, including images
 */
public class Narrative extends Element {

    public enum NarrativeStatus {
        generated, // The contents of the narrative are entirely generated from the structured data in the resource.
        extensions, // The contents of the narrative are entirely generated from the structured data in the resource and some of the content is generated from extensions
        additional, // The contents of the narrative contain additional information not found in the structured data
        empty, // the contents of the narrative are some equivalent of "No human readable text provided for this resource"
        Null; // added to help the parsers
        public static NarrativeStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("generated".equals(codeString))
          return generated;
        if ("extensions".equals(codeString))
          return extensions;
        if ("additional".equals(codeString))
          return additional;
        if ("empty".equals(codeString))
          return empty;
        throw new Exception("Unknown NarrativeStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case generated: return "generated";
            case extensions: return "extensions";
            case additional: return "additional";
            case empty: return "empty";
            default: return "?";
          }
        }
    }

  public class NarrativeStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("generated".equals(codeString))
          return NarrativeStatus.generated;
        if ("extensions".equals(codeString))
          return NarrativeStatus.extensions;
        if ("additional".equals(codeString))
          return NarrativeStatus.additional;
        if ("empty".equals(codeString))
          return NarrativeStatus.empty;
        throw new Exception("Unknown NarrativeStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NarrativeStatus.generated)
        return "generated";
      if (code == NarrativeStatus.extensions)
        return "extensions";
      if (code == NarrativeStatus.additional)
        return "additional";
      if (code == NarrativeStatus.empty)
        return "empty";
      return "?";
      }
    }

    public class NarrativeBlobComponent extends Element {
        /**
         * Mime type of the binary content
         */
        private Code mimeType;

        /**
         * base64 data for the binary attachment
         */
        private Base64Binary content;

        public Code getMimeType() { 
          return this.mimeType;
        }

        public void setMimeType(Code value) { 
          this.mimeType = value;
        }

        public String getMimeTypeSimple() { 
          return this.mimeType == null ? null : this.mimeType.getValue();
        }

        public void setMimeTypeSimple(String value) { 
            if (this.mimeType == null)
              this.mimeType = new Code();
            this.mimeType.setValue(value);
        }

        public Base64Binary getContent() { 
          return this.content;
        }

        public void setContent(Base64Binary value) { 
          this.content = value;
        }

        public byte[] getContentSimple() { 
          return this.content == null ? null : this.content.getValue();
        }

        public void setContentSimple(byte[] value) { 
            if (this.content == null)
              this.content = new Base64Binary();
            this.content.setValue(value);
        }

  }

    /**
     * The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data
     */
    private Enumeration<NarrativeStatus> status;

    /**
     * The actual narrative content, a stripped down version of XHTML
     */
    private XhtmlNode div;

    /**
     * An image, stylesheet, or other resource referred to directly in the xhtml
     */
    private List<NarrativeBlobComponent> blob = new ArrayList<NarrativeBlobComponent>();

    public Enumeration<NarrativeStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<NarrativeStatus> value) { 
      this.status = value;
    }

    public NarrativeStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(NarrativeStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<NarrativeStatus>();
        this.status.setValue(value);
    }

    public XhtmlNode getDiv() { 
      return this.div;
    }

    public void setDiv(XhtmlNode value) { 
      this.div = value;
    }

    public List<NarrativeBlobComponent> getBlob() { 
      return this.blob;
    }


}

