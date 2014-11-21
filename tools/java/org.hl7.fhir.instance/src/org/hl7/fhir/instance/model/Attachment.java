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

import org.hl7.fhir.utilities.Utilities;
/**
 * For referring to data content defined in other formats.
 */
public class Attachment extends Type {

    /**
     * Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.
     */
    protected CodeType contentType;

    /**
     * The human language of the content. The value can be any valid value according to BCP 47.
     */
    protected CodeType language;

    /**
     * The actual data of the attachment - a sequence of bytes. In XML, represented using base64.
     */
    protected Base64BinaryType data;

    /**
     * An alternative location where the data can be accessed.
     */
    protected UriType url;

    /**
     * The number of bytes of data that make up this attachment.
     */
    protected IntegerType size;

    /**
     * The calculated hash of the data using SHA-1. Represented using base64.
     */
    protected Base64BinaryType hash;

    /**
     * A label or set of text to display in place of the data.
     */
    protected StringType title;

    private static final long serialVersionUID = 483430116L;

    public Attachment() {
      super();
    }

    /**
     * @return {@link #contentType} (Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.). This is the underlying object with id, value and extensions. The accessor "getContentType" gives direct access to the value
     */
    public CodeType getContentTypeElement() { 
      return this.contentType;
    }

    /**
     * @param value {@link #contentType} (Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.). This is the underlying object with id, value and extensions. The accessor "getContentType" gives direct access to the value
     */
    public Attachment setContentTypeElement(CodeType value) { 
      this.contentType = value;
      return this;
    }

    /**
     * @return Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.
     */
    public String getContentType() { 
      return this.contentType == null ? null : this.contentType.getValue();
    }

    /**
     * @param value Identifies the type of the data in the attachment and allows a method to be chosen to interpret or render the data. Includes mime type parameters such as charset where appropriate.
     */
    public Attachment setContentType(String value) { 
      if (Utilities.noString(value))
        this.contentType = null;
      else {
        if (this.contentType == null)
          this.contentType = new CodeType();
        this.contentType.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #language} (The human language of the content. The value can be any valid value according to BCP 47.). This is the underlying object with id, value and extensions. The accessor "getLanguage" gives direct access to the value
     */
    public CodeType getLanguageElement() { 
      return this.language;
    }

    /**
     * @param value {@link #language} (The human language of the content. The value can be any valid value according to BCP 47.). This is the underlying object with id, value and extensions. The accessor "getLanguage" gives direct access to the value
     */
    public Attachment setLanguageElement(CodeType value) { 
      this.language = value;
      return this;
    }

    /**
     * @return The human language of the content. The value can be any valid value according to BCP 47.
     */
    public String getLanguage() { 
      return this.language == null ? null : this.language.getValue();
    }

    /**
     * @param value The human language of the content. The value can be any valid value according to BCP 47.
     */
    public Attachment setLanguage(String value) { 
      if (Utilities.noString(value))
        this.language = null;
      else {
        if (this.language == null)
          this.language = new CodeType();
        this.language.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #data} (The actual data of the attachment - a sequence of bytes. In XML, represented using base64.). This is the underlying object with id, value and extensions. The accessor "getData" gives direct access to the value
     */
    public Base64BinaryType getDataElement() { 
      return this.data;
    }

    /**
     * @param value {@link #data} (The actual data of the attachment - a sequence of bytes. In XML, represented using base64.). This is the underlying object with id, value and extensions. The accessor "getData" gives direct access to the value
     */
    public Attachment setDataElement(Base64BinaryType value) { 
      this.data = value;
      return this;
    }

    /**
     * @return The actual data of the attachment - a sequence of bytes. In XML, represented using base64.
     */
    public byte[] getData() { 
      return this.data == null ? null : this.data.getValue();
    }

    /**
     * @param value The actual data of the attachment - a sequence of bytes. In XML, represented using base64.
     */
    public Attachment setData(byte[] value) { 
      if (value == null)
        this.data = null;
      else {
        if (this.data == null)
          this.data = new Base64BinaryType();
        this.data.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #url} (An alternative location where the data can be accessed.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
     */
    public UriType getUrlElement() { 
      return this.url;
    }

    /**
     * @param value {@link #url} (An alternative location where the data can be accessed.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
     */
    public Attachment setUrlElement(UriType value) { 
      this.url = value;
      return this;
    }

    /**
     * @return An alternative location where the data can be accessed.
     */
    public String getUrl() { 
      return this.url == null ? null : this.url.getValue();
    }

    /**
     * @param value An alternative location where the data can be accessed.
     */
    public Attachment setUrl(String value) { 
      if (Utilities.noString(value))
        this.url = null;
      else {
        if (this.url == null)
          this.url = new UriType();
        this.url.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #size} (The number of bytes of data that make up this attachment.). This is the underlying object with id, value and extensions. The accessor "getSize" gives direct access to the value
     */
    public IntegerType getSizeElement() { 
      return this.size;
    }

    /**
     * @param value {@link #size} (The number of bytes of data that make up this attachment.). This is the underlying object with id, value and extensions. The accessor "getSize" gives direct access to the value
     */
    public Attachment setSizeElement(IntegerType value) { 
      this.size = value;
      return this;
    }

    /**
     * @return The number of bytes of data that make up this attachment.
     */
    public int getSize() { 
      return this.size == null ? null : this.size.getValue();
    }

    /**
     * @param value The number of bytes of data that make up this attachment.
     */
    public Attachment setSize(int value) { 
      if (value == -1)
        this.size = null;
      else {
        if (this.size == null)
          this.size = new IntegerType();
        this.size.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #hash} (The calculated hash of the data using SHA-1. Represented using base64.). This is the underlying object with id, value and extensions. The accessor "getHash" gives direct access to the value
     */
    public Base64BinaryType getHashElement() { 
      return this.hash;
    }

    /**
     * @param value {@link #hash} (The calculated hash of the data using SHA-1. Represented using base64.). This is the underlying object with id, value and extensions. The accessor "getHash" gives direct access to the value
     */
    public Attachment setHashElement(Base64BinaryType value) { 
      this.hash = value;
      return this;
    }

    /**
     * @return The calculated hash of the data using SHA-1. Represented using base64.
     */
    public byte[] getHash() { 
      return this.hash == null ? null : this.hash.getValue();
    }

    /**
     * @param value The calculated hash of the data using SHA-1. Represented using base64.
     */
    public Attachment setHash(byte[] value) { 
      if (value == null)
        this.hash = null;
      else {
        if (this.hash == null)
          this.hash = new Base64BinaryType();
        this.hash.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #title} (A label or set of text to display in place of the data.). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
     */
    public StringType getTitleElement() { 
      return this.title;
    }

    /**
     * @param value {@link #title} (A label or set of text to display in place of the data.). This is the underlying object with id, value and extensions. The accessor "getTitle" gives direct access to the value
     */
    public Attachment setTitleElement(StringType value) { 
      this.title = value;
      return this;
    }

    /**
     * @return A label or set of text to display in place of the data.
     */
    public String getTitle() { 
      return this.title == null ? null : this.title.getValue();
    }

    /**
     * @param value A label or set of text to display in place of the data.
     */
    public Attachment setTitle(String value) { 
      if (Utilities.noString(value))
        this.title = null;
      else {
        if (this.title == null)
          this.title = new StringType();
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
        copyValues(dst);
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

