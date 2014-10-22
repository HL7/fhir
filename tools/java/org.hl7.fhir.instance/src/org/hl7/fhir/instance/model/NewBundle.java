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

// Generated on Wed, Oct 22, 2014 17:27+1100 for FHIR v0.3.0

import java.util.*;

/**
 * Teest Bundle.
 */
public class NewBundle extends Resource {

    public static class NewBundleLinkComponent extends BackboneElement {
        /**
         * A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].
         */
        protected StringType relation;

        /**
         * The reference details for the link.
         */
        protected UriType url;

        private static final long serialVersionUID = -1010386066L;

      public NewBundleLinkComponent() {
        super();
      }

      public NewBundleLinkComponent(StringType relation, UriType url) {
        super();
        this.relation = relation;
        this.url = url;
      }

        /**
         * @return {@link #relation} (A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].). This is the underlying object with id, value and extensions. The accessor "getRelation" gives direct access to the value
         */
        public StringType getRelationElement() { 
          return this.relation;
        }

        /**
         * @param value {@link #relation} (A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].). This is the underlying object with id, value and extensions. The accessor "getRelation" gives direct access to the value
         */
        public NewBundleLinkComponent setRelationElement(StringType value) { 
          this.relation = value;
          return this;
        }

        /**
         * @return A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].
         */
        public String getRelation() { 
          return this.relation == null ? null : this.relation.getValue();
        }

        /**
         * @param value A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].
         */
        public NewBundleLinkComponent setRelation(String value) { 
            if (this.relation == null)
              this.relation = new StringType();
            this.relation.setValue(value);
          return this;
        }

        /**
         * @return {@link #url} (The reference details for the link.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public UriType getUrlElement() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (The reference details for the link.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public NewBundleLinkComponent setUrlElement(UriType value) { 
          this.url = value;
          return this;
        }

        /**
         * @return The reference details for the link.
         */
        public String getUrl() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value The reference details for the link.
         */
        public NewBundleLinkComponent setUrl(String value) { 
            if (this.url == null)
              this.url = new UriType();
            this.url.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relation", "string", "A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].", 0, java.lang.Integer.MAX_VALUE, relation));
          childrenList.add(new Property("url", "uri", "The reference details for the link.", 0, java.lang.Integer.MAX_VALUE, url));
        }

      public NewBundleLinkComponent copy() {
        NewBundleLinkComponent dst = new NewBundleLinkComponent();
        dst.relation = relation == null ? null : relation.copy();
        dst.url = url == null ? null : url.copy();
        return dst;
      }

  }

    /**
     * The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).
     */
    protected UriType base;

    /**
     * The total number of matches for the search.
     */
    protected IntegerType total;

    /**
     * A series of links that provide context to this bundle.
     */
    protected List<NewBundleLinkComponent> link = new ArrayList<NewBundleLinkComponent>();

    /**
     * Resources that are contained in this bundle.
     */
    protected List<Resource> item = new ArrayList<Resource>();

    /**
     * XML Digital Signature - base64 encoded.
     */
    protected Base64BinaryType signature;

    private static final long serialVersionUID = -838562462L;

    public NewBundle() {
      super();
    }

    /**
     * @return {@link #base} (The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
     */
    public UriType getBaseElement() { 
      return this.base;
    }

    /**
     * @param value {@link #base} (The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
     */
    public NewBundle setBaseElement(UriType value) { 
      this.base = value;
      return this;
    }

    /**
     * @return The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).
     */
    public String getBase() { 
      return this.base == null ? null : this.base.getValue();
    }

    /**
     * @param value The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).
     */
    public NewBundle setBase(String value) { 
      if (value == null)
        this.base = null;
      else {
        if (this.base == null)
          this.base = new UriType();
        this.base.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #total} (The total number of matches for the search.). This is the underlying object with id, value and extensions. The accessor "getTotal" gives direct access to the value
     */
    public IntegerType getTotalElement() { 
      return this.total;
    }

    /**
     * @param value {@link #total} (The total number of matches for the search.). This is the underlying object with id, value and extensions. The accessor "getTotal" gives direct access to the value
     */
    public NewBundle setTotalElement(IntegerType value) { 
      this.total = value;
      return this;
    }

    /**
     * @return The total number of matches for the search.
     */
    public int getTotal() { 
      return this.total == null ? null : this.total.getValue();
    }

    /**
     * @param value The total number of matches for the search.
     */
    public NewBundle setTotal(int value) { 
      if (value == -1)
        this.total = null;
      else {
        if (this.total == null)
          this.total = new IntegerType();
        this.total.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #link} (A series of links that provide context to this bundle.)
     */
    public List<NewBundleLinkComponent> getLink() { 
      return this.link;
    }

    /**
     * @return {@link #link} (A series of links that provide context to this bundle.)
     */
    // syntactic sugar
    public NewBundleLinkComponent addLink() { 
      NewBundleLinkComponent t = new NewBundleLinkComponent();
      this.link.add(t);
      return t;
    }

    /**
     * @return {@link #item} (Resources that are contained in this bundle.)
     */
    public List<Resource> getItem() { 
      return this.item;
    }

    /**
     * @return {@link #item} (Resources that are contained in this bundle.)
     */
    /**
     * @return {@link #signature} (XML Digital Signature - base64 encoded.). This is the underlying object with id, value and extensions. The accessor "getSignature" gives direct access to the value
     */
    public Base64BinaryType getSignatureElement() { 
      return this.signature;
    }

    /**
     * @param value {@link #signature} (XML Digital Signature - base64 encoded.). This is the underlying object with id, value and extensions. The accessor "getSignature" gives direct access to the value
     */
    public NewBundle setSignatureElement(Base64BinaryType value) { 
      this.signature = value;
      return this;
    }

    /**
     * @return XML Digital Signature - base64 encoded.
     */
    public byte[] getSignature() { 
      return this.signature == null ? null : this.signature.getValue();
    }

    /**
     * @param value XML Digital Signature - base64 encoded.
     */
    public NewBundle setSignature(byte[] value) { 
      if (value == null)
        this.signature = null;
      else {
        if (this.signature == null)
          this.signature = new Base64BinaryType();
        this.signature.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("base", "uri", "The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).", 0, java.lang.Integer.MAX_VALUE, base));
        childrenList.add(new Property("total", "integer", "The total number of matches for the search.", 0, java.lang.Integer.MAX_VALUE, total));
        childrenList.add(new Property("link", "", "A series of links that provide context to this bundle.", 0, java.lang.Integer.MAX_VALUE, link));
        childrenList.add(new Property("item", "Resource", "Resources that are contained in this bundle.", 0, java.lang.Integer.MAX_VALUE, item));
        childrenList.add(new Property("signature", "base64Binary", "XML Digital Signature - base64 encoded.", 0, java.lang.Integer.MAX_VALUE, signature));
      }

      public NewBundle copy() {
        NewBundle dst = new NewBundle();
        dst.base = base == null ? null : base.copy();
        dst.total = total == null ? null : total.copy();
        dst.link = new ArrayList<NewBundleLinkComponent>();
        for (NewBundleLinkComponent i : link)
          dst.link.add(i.copy());
        dst.item = new ArrayList<Resource>();
        for (Resource i : item)
          dst.item.add(i.copy());
        dst.signature = signature == null ? null : signature.copy();
        return dst;
      }

      protected NewBundle typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.NewBundle;
   }


}

