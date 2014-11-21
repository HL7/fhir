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
 * Base Resource for everything.
 */
public abstract class Resource extends Base {

    public static class ResourceMetaComponent extends BackboneElement {
        /**
         * The version specific identifier, as it appears in the version portion of the url. This values changes when the resource is created, updated, or deleted.
         */
        protected IdType versionId;

        /**
         * When the resource last changed - e.g. when the version changed.
         */
        protected InstantType lastUpdated;

        /**
         * A list of profiles that this resource claims to conform to. The URL is a reference to Profile.url.
         */
        protected List<UriType> profile = new ArrayList<UriType>();

        /**
         * Security labels applied to this resource. These tags connect specific resources to the overall security policy and infrastructure.
         */
        protected List<Coding> security = new ArrayList<Coding>();

        /**
         * Tags applied to this resource. Tags are intended to to be used to identify and relate resources to process and workflow, and applications are not required to consider the tags when interpreting the meaning of a resource.
         */
        protected List<Coding> tag = new ArrayList<Coding>();

        private static final long serialVersionUID = 136876122L;

      public ResourceMetaComponent() {
        super();
      }

        /**
         * @return {@link #versionId} (The version specific identifier, as it appears in the version portion of the url. This values changes when the resource is created, updated, or deleted.). This is the underlying object with id, value and extensions. The accessor "getVersionId" gives direct access to the value
         */
        public IdType getVersionIdElement() { 
          return this.versionId;
        }

        /**
         * @param value {@link #versionId} (The version specific identifier, as it appears in the version portion of the url. This values changes when the resource is created, updated, or deleted.). This is the underlying object with id, value and extensions. The accessor "getVersionId" gives direct access to the value
         */
        public ResourceMetaComponent setVersionIdElement(IdType value) { 
          this.versionId = value;
          return this;
        }

        /**
         * @return The version specific identifier, as it appears in the version portion of the url. This values changes when the resource is created, updated, or deleted.
         */
        public String getVersionId() { 
          return this.versionId == null ? null : this.versionId.getValue();
        }

        /**
         * @param value The version specific identifier, as it appears in the version portion of the url. This values changes when the resource is created, updated, or deleted.
         */
        public ResourceMetaComponent setVersionId(String value) { 
          if (Utilities.noString(value))
            this.versionId = null;
          else {
            if (this.versionId == null)
              this.versionId = new IdType();
            this.versionId.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #lastUpdated} (When the resource last changed - e.g. when the version changed.). This is the underlying object with id, value and extensions. The accessor "getLastUpdated" gives direct access to the value
         */
        public InstantType getLastUpdatedElement() { 
          return this.lastUpdated;
        }

        /**
         * @param value {@link #lastUpdated} (When the resource last changed - e.g. when the version changed.). This is the underlying object with id, value and extensions. The accessor "getLastUpdated" gives direct access to the value
         */
        public ResourceMetaComponent setLastUpdatedElement(InstantType value) { 
          this.lastUpdated = value;
          return this;
        }

        /**
         * @return When the resource last changed - e.g. when the version changed.
         */
        public DateAndTime getLastUpdated() { 
          return this.lastUpdated == null ? null : this.lastUpdated.getValue();
        }

        /**
         * @param value When the resource last changed - e.g. when the version changed.
         */
        public ResourceMetaComponent setLastUpdated(DateAndTime value) { 
          if (value == null)
            this.lastUpdated = null;
          else {
            if (this.lastUpdated == null)
              this.lastUpdated = new InstantType();
            this.lastUpdated.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #profile} (A list of profiles that this resource claims to conform to. The URL is a reference to Profile.url.)
         */
        public List<UriType> getProfile() { 
          return this.profile;
        }

        /**
         * @return {@link #profile} (A list of profiles that this resource claims to conform to. The URL is a reference to Profile.url.)
         */
    // syntactic sugar
        public UriType addProfileElement() {//2 
          UriType t = new UriType();
          this.profile.add(t);
          return t;
        }

        /**
         * @param value {@link #profile} (A list of profiles that this resource claims to conform to. The URL is a reference to Profile.url.)
         */
        public ResourceMetaComponent addProfile(String value) { //1
          UriType t = new UriType();
          t.setValue(value);
          this.profile.add(t);
          return this;
        }

        /**
         * @param value {@link #profile} (A list of profiles that this resource claims to conform to. The URL is a reference to Profile.url.)
         */
        public boolean hasProfile(String value) { 
          for (UriType v : this.profile)
            if (v.equals(value)) // uri
              return true;
          return false;
        }

        /**
         * @return {@link #security} (Security labels applied to this resource. These tags connect specific resources to the overall security policy and infrastructure.)
         */
        public List<Coding> getSecurity() { 
          return this.security;
        }

        /**
         * @return {@link #security} (Security labels applied to this resource. These tags connect specific resources to the overall security policy and infrastructure.)
         */
    // syntactic sugar
        public Coding addSecurity() { //3
          Coding t = new Coding();
          this.security.add(t);
          return t;
        }

        /**
         * @return {@link #tag} (Tags applied to this resource. Tags are intended to to be used to identify and relate resources to process and workflow, and applications are not required to consider the tags when interpreting the meaning of a resource.)
         */
        public List<Coding> getTag() { 
          return this.tag;
        }

        /**
         * @return {@link #tag} (Tags applied to this resource. Tags are intended to to be used to identify and relate resources to process and workflow, and applications are not required to consider the tags when interpreting the meaning of a resource.)
         */
    // syntactic sugar
        public Coding addTag() { //3
          Coding t = new Coding();
          this.tag.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("versionId", "id", "The version specific identifier, as it appears in the version portion of the url. This values changes when the resource is created, updated, or deleted.", 0, java.lang.Integer.MAX_VALUE, versionId));
          childrenList.add(new Property("lastUpdated", "instant", "When the resource last changed - e.g. when the version changed.", 0, java.lang.Integer.MAX_VALUE, lastUpdated));
          childrenList.add(new Property("profile", "uri", "A list of profiles that this resource claims to conform to. The URL is a reference to Profile.url.", 0, java.lang.Integer.MAX_VALUE, profile));
          childrenList.add(new Property("security", "Coding", "Security labels applied to this resource. These tags connect specific resources to the overall security policy and infrastructure.", 0, java.lang.Integer.MAX_VALUE, security));
          childrenList.add(new Property("tag", "Coding", "Tags applied to this resource. Tags are intended to to be used to identify and relate resources to process and workflow, and applications are not required to consider the tags when interpreting the meaning of a resource.", 0, java.lang.Integer.MAX_VALUE, tag));
        }

      public ResourceMetaComponent copy() {
        ResourceMetaComponent dst = new ResourceMetaComponent();
        copyValues(dst);
        dst.versionId = versionId == null ? null : versionId.copy();
        dst.lastUpdated = lastUpdated == null ? null : lastUpdated.copy();
        dst.profile = new ArrayList<UriType>();
        for (UriType i : profile)
          dst.profile.add(i.copy());
        dst.security = new ArrayList<Coding>();
        for (Coding i : security)
          dst.security.add(i.copy());
        dst.tag = new ArrayList<Coding>();
        for (Coding i : tag)
          dst.tag.add(i.copy());
        return dst;
      }

  }

    /**
     * The logical id of the resource, as used in the url for the resoure. Once assigned, this value never changes.
     */
    protected IdType id;

    /**
     * The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content may not always be associated with version changes to the resource.
     */
    protected ResourceMetaComponent meta;

    /**
     * A reference to a set of rules that were followed when the resource was constructed, and which must be understood when processing the content.
     */
    protected UriType implicitRules;

    /**
     * The base language in which the resource is written.
     */
    protected CodeType language;

    private static final long serialVersionUID = -519506254L;

    public Resource() {
      super();
    }

    /**
     * @return {@link #id} (The logical id of the resource, as used in the url for the resoure. Once assigned, this value never changes.). This is the underlying object with id, value and extensions. The accessor "getId" gives direct access to the value
     */
    public IdType getIdElement() { 
      return this.id;
    }

    /**
     * @param value {@link #id} (The logical id of the resource, as used in the url for the resoure. Once assigned, this value never changes.). This is the underlying object with id, value and extensions. The accessor "getId" gives direct access to the value
     */
    public Resource setIdElement(IdType value) { 
      this.id = value;
      return this;
    }

    /**
     * @return The logical id of the resource, as used in the url for the resoure. Once assigned, this value never changes.
     */
    public String getId() { 
      return this.id == null ? null : this.id.getValue();
    }

    /**
     * @param value The logical id of the resource, as used in the url for the resoure. Once assigned, this value never changes.
     */
    public Resource setId(String value) { 
      if (Utilities.noString(value))
        this.id = null;
      else {
        if (this.id == null)
          this.id = new IdType();
        this.id.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #meta} (The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content may not always be associated with version changes to the resource.)
     */
    public ResourceMetaComponent getMeta() { 
      return this.meta;
    }

    /**
     * @param value {@link #meta} (The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content may not always be associated with version changes to the resource.)
     */
    public Resource setMeta(ResourceMetaComponent value) { 
      this.meta = value;
      return this;
    }

    /**
     * @return {@link #implicitRules} (A reference to a set of rules that were followed when the resource was constructed, and which must be understood when processing the content.). This is the underlying object with id, value and extensions. The accessor "getImplicitRules" gives direct access to the value
     */
    public UriType getImplicitRulesElement() { 
      return this.implicitRules;
    }

    /**
     * @param value {@link #implicitRules} (A reference to a set of rules that were followed when the resource was constructed, and which must be understood when processing the content.). This is the underlying object with id, value and extensions. The accessor "getImplicitRules" gives direct access to the value
     */
    public Resource setImplicitRulesElement(UriType value) { 
      this.implicitRules = value;
      return this;
    }

    /**
     * @return A reference to a set of rules that were followed when the resource was constructed, and which must be understood when processing the content.
     */
    public String getImplicitRules() { 
      return this.implicitRules == null ? null : this.implicitRules.getValue();
    }

    /**
     * @param value A reference to a set of rules that were followed when the resource was constructed, and which must be understood when processing the content.
     */
    public Resource setImplicitRules(String value) { 
      if (Utilities.noString(value))
        this.implicitRules = null;
      else {
        if (this.implicitRules == null)
          this.implicitRules = new UriType();
        this.implicitRules.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #language} (The base language in which the resource is written.). This is the underlying object with id, value and extensions. The accessor "getLanguage" gives direct access to the value
     */
    public CodeType getLanguageElement() { 
      return this.language;
    }

    /**
     * @param value {@link #language} (The base language in which the resource is written.). This is the underlying object with id, value and extensions. The accessor "getLanguage" gives direct access to the value
     */
    public Resource setLanguageElement(CodeType value) { 
      this.language = value;
      return this;
    }

    /**
     * @return The base language in which the resource is written.
     */
    public String getLanguage() { 
      return this.language == null ? null : this.language.getValue();
    }

    /**
     * @param value The base language in which the resource is written.
     */
    public Resource setLanguage(String value) { 
      if (Utilities.noString(value))
        this.language = null;
      else {
        if (this.language == null)
          this.language = new CodeType();
        this.language.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        childrenList.add(new Property("id", "id", "The logical id of the resource, as used in the url for the resoure. Once assigned, this value never changes.", 0, java.lang.Integer.MAX_VALUE, id));
        childrenList.add(new Property("meta", "", "The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content may not always be associated with version changes to the resource.", 0, java.lang.Integer.MAX_VALUE, meta));
        childrenList.add(new Property("implicitRules", "uri", "A reference to a set of rules that were followed when the resource was constructed, and which must be understood when processing the content.", 0, java.lang.Integer.MAX_VALUE, implicitRules));
        childrenList.add(new Property("language", "code", "The base language in which the resource is written.", 0, java.lang.Integer.MAX_VALUE, language));
      }

      public abstract Resource copy();

      public void copyValues(Resource dst) {
        dst.id = id == null ? null : id.copy();
        dst.meta = meta == null ? null : meta.copy();
        dst.implicitRules = implicitRules == null ? null : implicitRules.copy();
        dst.language = language == null ? null : language.copy();
      }

 public abstract ResourceType getResourceType();

}

