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

// Generated on Wed, Oct 23, 2013 23:11+1100 for FHIR v0.12

/**
 * For referring to data content defined in other formats.
 */
public class Attachment extends Type {

    /**
     * Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.
     */
    protected Code contentType;

    /**
     * The human language of the content. The value can be any valid value according to BCP 47.
     */
    protected Code language;

    /**
     * The actual data of the attachment - a sequence of bytes. In XML, represented using base64.
     */
    protected Base64Binary data;

    /**
     * An alternative location where the data can be accessed.
     */
    protected Uri url;

    /**
     * The number of bytes of data that make up this attachment.
     */
    protected Integer size;

    /**
     * The calculated hash of the data using SHA-1. Represented using base64.
     */
    protected Base64Binary hash;

    /**
     * A label or set of text to display in place of the data.
     */
    protected String_ title;

    public Code getContentType() { 
      return this.contentType;
    }

    public void setContentType(Code value) { 
      this.contentType = value;
    }

    public String getContentTypeSimple() { 
      return this.contentType == null ? null : this.contentType.getValue();
    }

    public void setContentTypeSimple(String value) { 
        if (this.contentType == null)
          this.contentType = new Code();
        this.contentType.setValue(value);
    }

    public Code getLanguage() { 
      return this.language;
    }

    public void setLanguage(Code value) { 
      this.language = value;
    }

    public String getLanguageSimple() { 
      return this.language == null ? null : this.language.getValue();
    }

    public void setLanguageSimple(String value) { 
      if (value == null)
        this.language = null;
      else {
        if (this.language == null)
          this.language = new Code();
        this.language.setValue(value);
      }
    }

    public Base64Binary getData() { 
      return this.data;
    }

    public void setData(Base64Binary value) { 
      this.data = value;
    }

    public byte[] getDataSimple() { 
      return this.data == null ? null : this.data.getValue();
    }

    public void setDataSimple(byte[] value) { 
      if (value == null)
        this.data = null;
      else {
        if (this.data == null)
          this.data = new Base64Binary();
        this.data.setValue(value);
      }
    }

    public Uri getUrl() { 
      return this.url;
    }

    public void setUrl(Uri value) { 
      this.url = value;
    }

    public String getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    public void setUrlSimple(String value) { 
      if (value == null)
        this.url = null;
      else {
        if (this.url == null)
          this.url = new Uri();
        this.url.setValue(value);
      }
    }

    public Integer getSize() { 
      return this.size;
    }

    public void setSize(Integer value) { 
      this.size = value;
    }

    public int getSizeSimple() { 
      return this.size == null ? null : this.size.getValue();
    }

    public void setSizeSimple(int value) { 
      if (value == -1)
        this.size = null;
      else {
        if (this.size == null)
          this.size = new Integer();
        this.size.setValue(value);
      }
    }

    public Base64Binary getHash() { 
      return this.hash;
    }

    public void setHash(Base64Binary value) { 
      this.hash = value;
    }

    public byte[] getHashSimple() { 
      return this.hash == null ? null : this.hash.getValue();
    }

    public void setHashSimple(byte[] value) { 
      if (value == null)
        this.hash = null;
      else {
        if (this.hash == null)
          this.hash = new Base64Binary();
        this.hash.setValue(value);
      }
    }

    public String_ getTitle() { 
      return this.title;
    }

    public void setTitle(String_ value) { 
      this.title = value;
    }

    public String getTitleSimple() { 
      return this.title == null ? null : this.title.getValue();
    }

    public void setTitleSimple(String value) { 
      if (value == null)
        this.title = null;
      else {
        if (this.title == null)
          this.title = new String_();
        this.title.setValue(value);
      }
    }

      public Attachment copy() {
        Attachment dst = new Attachment();
        dst.contentType = contentType == null ? null : contentType.copy();
        dst.language = language == null ? null : language.copy();
        dst.data = data == null ? null : data.copy();
        dst.url = url == null ? null : url.copy();
        dst.size = size == null ? null : size.copy();
        dst.hash = hash == null ? null : hash.copy();
        dst.title = title == null ? null : title.copy();
        return dst;
      }

      protected Attachment typedCopy() {
        return copy();
      }


}

