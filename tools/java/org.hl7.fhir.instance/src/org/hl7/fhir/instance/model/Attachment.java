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

    private static final long serialVersionUID = 1845468239L;

    public Attachment() {
      super();
    }

    public Attachment(Code contentType) {
      super();
      this.contentType = contentType;
    }

    /**
     * @return {@link #contentType} (Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.)
     */
    public Code getContentType() { 
      return this.contentType;
    }

    /**
     * @param value {@link #contentType} (Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.)
     */
    public Attachment setContentType(Code value) { 
      this.contentType = value;
      return this;
    }

    /**
     * @return Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.
     */
    public String getContentTypeSimple() { 
      return this.contentType == null ? null : this.contentType.getValue();
    }

    /**
     * @param value Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.
     */
    public Attachment setContentTypeSimple(String value) { 
        if (this.contentType == null)
          this.contentType = new Code();
        this.contentType.setValue(value);
      return this;
    }

    /**
     * @return {@link #language} (The human language of the content. The value can be any valid value according to BCP 47.)
     */
    public Code getLanguage() { 
      return this.language;
    }

    /**
     * @param value {@link #language} (The human language of the content. The value can be any valid value according to BCP 47.)
     */
    public Attachment setLanguage(Code value) { 
      this.language = value;
      return this;
    }

    /**
     * @return The human language of the content. The value can be any valid value according to BCP 47.
     */
    public String getLanguageSimple() { 
      return this.language == null ? null : this.language.getValue();
    }

    /**
     * @param value The human language of the content. The value can be any valid value according to BCP 47.
     */
    public Attachment setLanguageSimple(String value) { 
      if (value == null)
        this.language = null;
      else {
        if (this.language == null)
          this.language = new Code();
        this.language.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #data} (The actual data of the attachment - a sequence of bytes. In XML, represented using base64.)
     */
    public Base64Binary getData() { 
      return this.data;
    }

    /**
     * @param value {@link #data} (The actual data of the attachment - a sequence of bytes. In XML, represented using base64.)
     */
    public Attachment setData(Base64Binary value) { 
      this.data = value;
      return this;
    }

    /**
     * @return The actual data of the attachment - a sequence of bytes. In XML, represented using base64.
     */
    public byte[] getDataSimple() { 
      return this.data == null ? null : this.data.getValue();
    }

    /**
     * @param value The actual data of the attachment - a sequence of bytes. In XML, represented using base64.
     */
    public Attachment setDataSimple(byte[] value) { 
      if (value == null)
        this.data = null;
      else {
        if (this.data == null)
          this.data = new Base64Binary();
        this.data.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #url} (An alternative location where the data can be accessed.)
     */
    public Uri getUrl() { 
      return this.url;
    }

    /**
     * @param value {@link #url} (An alternative location where the data can be accessed.)
     */
    public Attachment setUrl(Uri value) { 
      this.url = value;
      return this;
    }

    /**
     * @return An alternative location where the data can be accessed.
     */
    public String getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    /**
     * @param value An alternative location where the data can be accessed.
     */
    public Attachment setUrlSimple(String value) { 
      if (value == null)
        this.url = null;
      else {
        if (this.url == null)
          this.url = new Uri();
        this.url.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #size} (The number of bytes of data that make up this attachment.)
     */
    public Integer getSize() { 
      return this.size;
    }

    /**
     * @param value {@link #size} (The number of bytes of data that make up this attachment.)
     */
    public Attachment setSize(Integer value) { 
      this.size = value;
      return this;
    }

    /**
     * @return The number of bytes of data that make up this attachment.
     */
    public int getSizeSimple() { 
      return this.size == null ? null : this.size.getValue();
    }

    /**
     * @param value The number of bytes of data that make up this attachment.
     */
    public Attachment setSizeSimple(int value) { 
      if (value == -1)
        this.size = null;
      else {
        if (this.size == null)
          this.size = new Integer();
        this.size.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #hash} (The calculated hash of the data using SHA-1. Represented using base64.)
     */
    public Base64Binary getHash() { 
      return this.hash;
    }

    /**
     * @param value {@link #hash} (The calculated hash of the data using SHA-1. Represented using base64.)
     */
    public Attachment setHash(Base64Binary value) { 
      this.hash = value;
      return this;
    }

    /**
     * @return The calculated hash of the data using SHA-1. Represented using base64.
     */
    public byte[] getHashSimple() { 
      return this.hash == null ? null : this.hash.getValue();
    }

    /**
     * @param value The calculated hash of the data using SHA-1. Represented using base64.
     */
    public Attachment setHashSimple(byte[] value) { 
      if (value == null)
        this.hash = null;
      else {
        if (this.hash == null)
          this.hash = new Base64Binary();
        this.hash.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #title} (A label or set of text to display in place of the data.)
     */
    public String_ getTitle() { 
      return this.title;
    }

    /**
     * @param value {@link #title} (A label or set of text to display in place of the data.)
     */
    public Attachment setTitle(String_ value) { 
      this.title = value;
      return this;
    }

    /**
     * @return A label or set of text to display in place of the data.
     */
    public String getTitleSimple() { 
      return this.title == null ? null : this.title.getValue();
    }

    /**
     * @param value A label or set of text to display in place of the data.
     */
    public Attachment setTitleSimple(String value) { 
      if (value == null)
        this.title = null;
      else {
        if (this.title == null)
          this.title = new String_();
        this.title.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("contentType", "code", "Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.", 0, java.lang.Integer.MAX_VALUE, contentType));
        childrenList.add(new Property("language", "code", "The human language of the content. The value can be any valid value according to BCP 47.", 0, java.lang.Integer.MAX_VALUE, language));
        childrenList.add(new Property("data", "base64Binary", "The actual data of the attachment - a sequence of bytes. In XML, represented using base64.", 0, java.lang.Integer.MAX_VALUE, data));
        childrenList.add(new Property("url", "uri", "An alternative location where the data can be accessed.", 0, java.lang.Integer.MAX_VALUE, url));
        childrenList.add(new Property("size", "integer", "The number of bytes of data that make up this attachment.", 0, java.lang.Integer.MAX_VALUE, size));
        childrenList.add(new Property("hash", "base64Binary", "The calculated hash of the data using SHA-1. Represented using base64.", 0, java.lang.Integer.MAX_VALUE, hash));
        childrenList.add(new Property("title", "string", "A label or set of text to display in place of the data.", 0, java.lang.Integer.MAX_VALUE, title));
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

