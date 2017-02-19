package org.hl7.fhir.dstu3.model;

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

// Generated on Sat, Feb 18, 2017 17:12-0500 for FHIR v1.9.0

import java.util.*;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.exceptions.FHIRException;
/**
 * A document that bundles a set of catalog entries. A catalog entry contains metadata about an item and a pointer to the item’s representative resource. The item is an entity that can be ordered or consulted from a catalog: Medications, devices, lab services, organizations...
The catalog resource provides the data necessary for a synchronization of the item data – e.g. the version or last update date which allows systems to obtain differential updates. 
The catalog does not replicate the content of the item, since that is expected to be in the resource that is referenced. There is however some metadata that is important for the catalog synchronization and not in the “clinical” resource. Examples are different classifications and related identifiers, or packaging information, or device components, or different characteristics.
 */
@ResourceDef(name="Catalog", profile="http://hl7.org/fhir/Profile/Catalog")
public class Catalog extends DomainResource {

    @Block()
    public static class CatalogDocumentComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Used for presenting.
         */
        @Child(name = "status", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Status of the catalog document: pre-submission, pending, approved, draft", formalDefinition="Used for presenting." )
        protected CodeableConcept status;

        /**
         * The entity that is issuing (sending, submitting, publishing) the catalog.
         */
        @Child(name = "provider", type = {Organization.class}, order=2, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The entity that is issuing (sending, submitting, publishing) the catalog", formalDefinition="The entity that is issuing (sending, submitting, publishing) the catalog." )
        protected Reference provider;

        /**
         * The actual object that is the target of the reference (The entity that is issuing (sending, submitting, publishing) the catalog.)
         */
        protected Organization providerTarget;

        /**
         * For example FormularyOnly items, or Full Catalog, or SingleSubmission, or others.
         */
        @Child(name = "contentType", type = {CodeableConcept.class}, order=3, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The type of content in the document", formalDefinition="For example FormularyOnly items, or Full Catalog, or SingleSubmission, or others." )
        protected CodeableConcept contentType;

        /**
         * Used to define a full update, or appending information (e.g. sending the catalog of substances does not replace the catalog of medications, but rather updates the characteristics).
         */
        @Child(name = "updateMode", type = {CodeableConcept.class}, order=4, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="How the content is intended to be used - overwriting, appending, complementing existing items", formalDefinition="Used to define a full update, or appending information (e.g. sending the catalog of substances does not replace the catalog of medications, but rather updates the characteristics)." )
        protected CodeableConcept updateMode;

        /**
         * Uniquely identifies the catalog for archiving, versioning, duplicate checking, etc.
         */
        @Child(name = "identifier", type = {Identifier.class}, order=5, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Unique identifier for the catalog document", formalDefinition="Uniquely identifies the catalog for archiving, versioning, duplicate checking, etc." )
        protected Identifier identifier;

        /**
         * To support versioning and deciding when to overwrite /update content.
         */
        @Child(name = "contentVersion", type = {Identifier.class}, order=6, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The version of the bundle that is being transmitted", formalDefinition="To support versioning and deciding when to overwrite /update content." )
        protected Identifier contentVersion;

        /**
         * The date when the catalog document is issued.
         */
        @Child(name = "issueDate", type = {DateTimeType.class}, order=7, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The date when the catalog document is issued", formalDefinition="The date when the catalog document is issued." )
        protected DateTimeType issueDate;

        /**
         * To support delayed or timed activation of items, e.g. "next year this product will be available".
         */
        @Child(name = "validFrom", type = {DateTimeType.class}, order=8, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The date from which the catalog content is expected to be active", formalDefinition="To support delayed or timed activation of items, e.g. \"next year this product will be available\"." )
        protected DateTimeType validFrom;

        /**
         * To support delayed or timed deactivation of items, e.g. "next year this product will no longer be available".
         */
        @Child(name = "validTo", type = {DateTimeType.class}, order=9, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The date until which the catalog content is expected to be active", formalDefinition="To support delayed or timed deactivation of items, e.g. \"next year this product will no longer be available\"." )
        protected DateTimeType validTo;

        private static final long serialVersionUID = -2040712420L;

    /**
     * Constructor
     */
      public CatalogDocumentComponent() {
        super();
      }

    /**
     * Constructor
     */
      public CatalogDocumentComponent(CodeableConcept status, Reference provider, CodeableConcept contentType, CodeableConcept updateMode) {
        super();
        this.status = status;
        this.provider = provider;
        this.contentType = contentType;
        this.updateMode = updateMode;
      }

        /**
         * @return {@link #status} (Used for presenting.)
         */
        public CodeableConcept getStatus() { 
          if (this.status == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.status");
            else if (Configuration.doAutoCreate())
              this.status = new CodeableConcept(); // cc
          return this.status;
        }

        public boolean hasStatus() { 
          return this.status != null && !this.status.isEmpty();
        }

        /**
         * @param value {@link #status} (Used for presenting.)
         */
        public CatalogDocumentComponent setStatus(CodeableConcept value) { 
          this.status = value;
          return this;
        }

        /**
         * @return {@link #provider} (The entity that is issuing (sending, submitting, publishing) the catalog.)
         */
        public Reference getProvider() { 
          if (this.provider == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.provider");
            else if (Configuration.doAutoCreate())
              this.provider = new Reference(); // cc
          return this.provider;
        }

        public boolean hasProvider() { 
          return this.provider != null && !this.provider.isEmpty();
        }

        /**
         * @param value {@link #provider} (The entity that is issuing (sending, submitting, publishing) the catalog.)
         */
        public CatalogDocumentComponent setProvider(Reference value) { 
          this.provider = value;
          return this;
        }

        /**
         * @return {@link #provider} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The entity that is issuing (sending, submitting, publishing) the catalog.)
         */
        public Organization getProviderTarget() { 
          if (this.providerTarget == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.provider");
            else if (Configuration.doAutoCreate())
              this.providerTarget = new Organization(); // aa
          return this.providerTarget;
        }

        /**
         * @param value {@link #provider} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The entity that is issuing (sending, submitting, publishing) the catalog.)
         */
        public CatalogDocumentComponent setProviderTarget(Organization value) { 
          this.providerTarget = value;
          return this;
        }

        /**
         * @return {@link #contentType} (For example FormularyOnly items, or Full Catalog, or SingleSubmission, or others.)
         */
        public CodeableConcept getContentType() { 
          if (this.contentType == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.contentType");
            else if (Configuration.doAutoCreate())
              this.contentType = new CodeableConcept(); // cc
          return this.contentType;
        }

        public boolean hasContentType() { 
          return this.contentType != null && !this.contentType.isEmpty();
        }

        /**
         * @param value {@link #contentType} (For example FormularyOnly items, or Full Catalog, or SingleSubmission, or others.)
         */
        public CatalogDocumentComponent setContentType(CodeableConcept value) { 
          this.contentType = value;
          return this;
        }

        /**
         * @return {@link #updateMode} (Used to define a full update, or appending information (e.g. sending the catalog of substances does not replace the catalog of medications, but rather updates the characteristics).)
         */
        public CodeableConcept getUpdateMode() { 
          if (this.updateMode == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.updateMode");
            else if (Configuration.doAutoCreate())
              this.updateMode = new CodeableConcept(); // cc
          return this.updateMode;
        }

        public boolean hasUpdateMode() { 
          return this.updateMode != null && !this.updateMode.isEmpty();
        }

        /**
         * @param value {@link #updateMode} (Used to define a full update, or appending information (e.g. sending the catalog of substances does not replace the catalog of medications, but rather updates the characteristics).)
         */
        public CatalogDocumentComponent setUpdateMode(CodeableConcept value) { 
          this.updateMode = value;
          return this;
        }

        /**
         * @return {@link #identifier} (Uniquely identifies the catalog for archiving, versioning, duplicate checking, etc.)
         */
        public Identifier getIdentifier() { 
          if (this.identifier == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.identifier");
            else if (Configuration.doAutoCreate())
              this.identifier = new Identifier(); // cc
          return this.identifier;
        }

        public boolean hasIdentifier() { 
          return this.identifier != null && !this.identifier.isEmpty();
        }

        /**
         * @param value {@link #identifier} (Uniquely identifies the catalog for archiving, versioning, duplicate checking, etc.)
         */
        public CatalogDocumentComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #contentVersion} (To support versioning and deciding when to overwrite /update content.)
         */
        public Identifier getContentVersion() { 
          if (this.contentVersion == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.contentVersion");
            else if (Configuration.doAutoCreate())
              this.contentVersion = new Identifier(); // cc
          return this.contentVersion;
        }

        public boolean hasContentVersion() { 
          return this.contentVersion != null && !this.contentVersion.isEmpty();
        }

        /**
         * @param value {@link #contentVersion} (To support versioning and deciding when to overwrite /update content.)
         */
        public CatalogDocumentComponent setContentVersion(Identifier value) { 
          this.contentVersion = value;
          return this;
        }

        /**
         * @return {@link #issueDate} (The date when the catalog document is issued.). This is the underlying object with id, value and extensions. The accessor "getIssueDate" gives direct access to the value
         */
        public DateTimeType getIssueDateElement() { 
          if (this.issueDate == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.issueDate");
            else if (Configuration.doAutoCreate())
              this.issueDate = new DateTimeType(); // bb
          return this.issueDate;
        }

        public boolean hasIssueDateElement() { 
          return this.issueDate != null && !this.issueDate.isEmpty();
        }

        public boolean hasIssueDate() { 
          return this.issueDate != null && !this.issueDate.isEmpty();
        }

        /**
         * @param value {@link #issueDate} (The date when the catalog document is issued.). This is the underlying object with id, value and extensions. The accessor "getIssueDate" gives direct access to the value
         */
        public CatalogDocumentComponent setIssueDateElement(DateTimeType value) { 
          this.issueDate = value;
          return this;
        }

        /**
         * @return The date when the catalog document is issued.
         */
        public Date getIssueDate() { 
          return this.issueDate == null ? null : this.issueDate.getValue();
        }

        /**
         * @param value The date when the catalog document is issued.
         */
        public CatalogDocumentComponent setIssueDate(Date value) { 
          if (value == null)
            this.issueDate = null;
          else {
            if (this.issueDate == null)
              this.issueDate = new DateTimeType();
            this.issueDate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #validFrom} (To support delayed or timed activation of items, e.g. "next year this product will be available".). This is the underlying object with id, value and extensions. The accessor "getValidFrom" gives direct access to the value
         */
        public DateTimeType getValidFromElement() { 
          if (this.validFrom == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.validFrom");
            else if (Configuration.doAutoCreate())
              this.validFrom = new DateTimeType(); // bb
          return this.validFrom;
        }

        public boolean hasValidFromElement() { 
          return this.validFrom != null && !this.validFrom.isEmpty();
        }

        public boolean hasValidFrom() { 
          return this.validFrom != null && !this.validFrom.isEmpty();
        }

        /**
         * @param value {@link #validFrom} (To support delayed or timed activation of items, e.g. "next year this product will be available".). This is the underlying object with id, value and extensions. The accessor "getValidFrom" gives direct access to the value
         */
        public CatalogDocumentComponent setValidFromElement(DateTimeType value) { 
          this.validFrom = value;
          return this;
        }

        /**
         * @return To support delayed or timed activation of items, e.g. "next year this product will be available".
         */
        public Date getValidFrom() { 
          return this.validFrom == null ? null : this.validFrom.getValue();
        }

        /**
         * @param value To support delayed or timed activation of items, e.g. "next year this product will be available".
         */
        public CatalogDocumentComponent setValidFrom(Date value) { 
          if (value == null)
            this.validFrom = null;
          else {
            if (this.validFrom == null)
              this.validFrom = new DateTimeType();
            this.validFrom.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #validTo} (To support delayed or timed deactivation of items, e.g. "next year this product will no longer be available".). This is the underlying object with id, value and extensions. The accessor "getValidTo" gives direct access to the value
         */
        public DateTimeType getValidToElement() { 
          if (this.validTo == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogDocumentComponent.validTo");
            else if (Configuration.doAutoCreate())
              this.validTo = new DateTimeType(); // bb
          return this.validTo;
        }

        public boolean hasValidToElement() { 
          return this.validTo != null && !this.validTo.isEmpty();
        }

        public boolean hasValidTo() { 
          return this.validTo != null && !this.validTo.isEmpty();
        }

        /**
         * @param value {@link #validTo} (To support delayed or timed deactivation of items, e.g. "next year this product will no longer be available".). This is the underlying object with id, value and extensions. The accessor "getValidTo" gives direct access to the value
         */
        public CatalogDocumentComponent setValidToElement(DateTimeType value) { 
          this.validTo = value;
          return this;
        }

        /**
         * @return To support delayed or timed deactivation of items, e.g. "next year this product will no longer be available".
         */
        public Date getValidTo() { 
          return this.validTo == null ? null : this.validTo.getValue();
        }

        /**
         * @param value To support delayed or timed deactivation of items, e.g. "next year this product will no longer be available".
         */
        public CatalogDocumentComponent setValidTo(Date value) { 
          if (value == null)
            this.validTo = null;
          else {
            if (this.validTo == null)
              this.validTo = new DateTimeType();
            this.validTo.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("status", "CodeableConcept", "Used for presenting.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("provider", "Reference(Organization)", "The entity that is issuing (sending, submitting, publishing) the catalog.", 0, java.lang.Integer.MAX_VALUE, provider));
          childrenList.add(new Property("contentType", "CodeableConcept", "For example FormularyOnly items, or Full Catalog, or SingleSubmission, or others.", 0, java.lang.Integer.MAX_VALUE, contentType));
          childrenList.add(new Property("updateMode", "CodeableConcept", "Used to define a full update, or appending information (e.g. sending the catalog of substances does not replace the catalog of medications, but rather updates the characteristics).", 0, java.lang.Integer.MAX_VALUE, updateMode));
          childrenList.add(new Property("identifier", "Identifier", "Uniquely identifies the catalog for archiving, versioning, duplicate checking, etc.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("contentVersion", "Identifier", "To support versioning and deciding when to overwrite /update content.", 0, java.lang.Integer.MAX_VALUE, contentVersion));
          childrenList.add(new Property("issueDate", "dateTime", "The date when the catalog document is issued.", 0, java.lang.Integer.MAX_VALUE, issueDate));
          childrenList.add(new Property("validFrom", "dateTime", "To support delayed or timed activation of items, e.g. \"next year this product will be available\".", 0, java.lang.Integer.MAX_VALUE, validFrom));
          childrenList.add(new Property("validTo", "dateTime", "To support delayed or timed deactivation of items, e.g. \"next year this product will no longer be available\".", 0, java.lang.Integer.MAX_VALUE, validTo));
        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // CodeableConcept
        case -987494927: /*provider*/ return this.provider == null ? new Base[0] : new Base[] {this.provider}; // Reference
        case -389131437: /*contentType*/ return this.contentType == null ? new Base[0] : new Base[] {this.contentType}; // CodeableConcept
        case -296134132: /*updateMode*/ return this.updateMode == null ? new Base[0] : new Base[] {this.updateMode}; // CodeableConcept
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : new Base[] {this.identifier}; // Identifier
        case 706885151: /*contentVersion*/ return this.contentVersion == null ? new Base[0] : new Base[] {this.contentVersion}; // Identifier
        case 184285223: /*issueDate*/ return this.issueDate == null ? new Base[0] : new Base[] {this.issueDate}; // DateTimeType
        case -1110590010: /*validFrom*/ return this.validFrom == null ? new Base[0] : new Base[] {this.validFrom}; // DateTimeType
        case 231246743: /*validTo*/ return this.validTo == null ? new Base[0] : new Base[] {this.validTo}; // DateTimeType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -892481550: // status
          this.status = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -987494927: // provider
          this.provider = castToReference(value); // Reference
          return value;
        case -389131437: // contentType
          this.contentType = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -296134132: // updateMode
          this.updateMode = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1618432855: // identifier
          this.identifier = castToIdentifier(value); // Identifier
          return value;
        case 706885151: // contentVersion
          this.contentVersion = castToIdentifier(value); // Identifier
          return value;
        case 184285223: // issueDate
          this.issueDate = castToDateTime(value); // DateTimeType
          return value;
        case -1110590010: // validFrom
          this.validFrom = castToDateTime(value); // DateTimeType
          return value;
        case 231246743: // validTo
          this.validTo = castToDateTime(value); // DateTimeType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("status")) {
          this.status = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("provider")) {
          this.provider = castToReference(value); // Reference
        } else if (name.equals("contentType")) {
          this.contentType = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("updateMode")) {
          this.updateMode = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("identifier")) {
          this.identifier = castToIdentifier(value); // Identifier
        } else if (name.equals("contentVersion")) {
          this.contentVersion = castToIdentifier(value); // Identifier
        } else if (name.equals("issueDate")) {
          this.issueDate = castToDateTime(value); // DateTimeType
        } else if (name.equals("validFrom")) {
          this.validFrom = castToDateTime(value); // DateTimeType
        } else if (name.equals("validTo")) {
          this.validTo = castToDateTime(value); // DateTimeType
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -892481550:  return getStatus(); 
        case -987494927:  return getProvider(); 
        case -389131437:  return getContentType(); 
        case -296134132:  return getUpdateMode(); 
        case -1618432855:  return getIdentifier(); 
        case 706885151:  return getContentVersion(); 
        case 184285223:  return getIssueDateElement();
        case -1110590010:  return getValidFromElement();
        case 231246743:  return getValidToElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -892481550: /*status*/ return new String[] {"CodeableConcept"};
        case -987494927: /*provider*/ return new String[] {"Reference"};
        case -389131437: /*contentType*/ return new String[] {"CodeableConcept"};
        case -296134132: /*updateMode*/ return new String[] {"CodeableConcept"};
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case 706885151: /*contentVersion*/ return new String[] {"Identifier"};
        case 184285223: /*issueDate*/ return new String[] {"dateTime"};
        case -1110590010: /*validFrom*/ return new String[] {"dateTime"};
        case 231246743: /*validTo*/ return new String[] {"dateTime"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("status")) {
          this.status = new CodeableConcept();
          return this.status;
        }
        else if (name.equals("provider")) {
          this.provider = new Reference();
          return this.provider;
        }
        else if (name.equals("contentType")) {
          this.contentType = new CodeableConcept();
          return this.contentType;
        }
        else if (name.equals("updateMode")) {
          this.updateMode = new CodeableConcept();
          return this.updateMode;
        }
        else if (name.equals("identifier")) {
          this.identifier = new Identifier();
          return this.identifier;
        }
        else if (name.equals("contentVersion")) {
          this.contentVersion = new Identifier();
          return this.contentVersion;
        }
        else if (name.equals("issueDate")) {
          throw new FHIRException("Cannot call addChild on a primitive type Catalog.issueDate");
        }
        else if (name.equals("validFrom")) {
          throw new FHIRException("Cannot call addChild on a primitive type Catalog.validFrom");
        }
        else if (name.equals("validTo")) {
          throw new FHIRException("Cannot call addChild on a primitive type Catalog.validTo");
        }
        else
          return super.addChild(name);
      }

      public CatalogDocumentComponent copy() {
        CatalogDocumentComponent dst = new CatalogDocumentComponent();
        copyValues(dst);
        dst.status = status == null ? null : status.copy();
        dst.provider = provider == null ? null : provider.copy();
        dst.contentType = contentType == null ? null : contentType.copy();
        dst.updateMode = updateMode == null ? null : updateMode.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.contentVersion = contentVersion == null ? null : contentVersion.copy();
        dst.issueDate = issueDate == null ? null : issueDate.copy();
        dst.validFrom = validFrom == null ? null : validFrom.copy();
        dst.validTo = validTo == null ? null : validTo.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof CatalogDocumentComponent))
          return false;
        CatalogDocumentComponent o = (CatalogDocumentComponent) other;
        return compareDeep(status, o.status, true) && compareDeep(provider, o.provider, true) && compareDeep(contentType, o.contentType, true)
           && compareDeep(updateMode, o.updateMode, true) && compareDeep(identifier, o.identifier, true) && compareDeep(contentVersion, o.contentVersion, true)
           && compareDeep(issueDate, o.issueDate, true) && compareDeep(validFrom, o.validFrom, true) && compareDeep(validTo, o.validTo, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof CatalogDocumentComponent))
          return false;
        CatalogDocumentComponent o = (CatalogDocumentComponent) other;
        return compareValues(issueDate, o.issueDate, true) && compareValues(validFrom, o.validFrom, true) && compareValues(validTo, o.validTo, true)
          ;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(status, provider, contentType
          , updateMode, identifier, contentVersion, issueDate, validFrom, validTo);
      }

  public String fhirType() {
    return "Catalog.document";

  }

  }

    @Block()
    public static class CatalogEntryComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The type of item - medication, device, service, protocol or other.
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=1, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The type of item - medication, device, service, protocol or other", formalDefinition="The type of item - medication, device, service, protocol or other." )
        protected CodeableConcept type;

        /**
         * Content of the catalog.
         */
        @Child(name = "referencedItem", type = {Medication.class, Device.class, Procedure.class, CarePlan.class, Organization.class, Practitioner.class, HealthcareService.class, ServiceDefinition.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The item itself", formalDefinition="Content of the catalog." )
        protected Reference referencedItem;

        /**
         * The actual object that is the target of the reference (Content of the catalog.)
         */
        protected Resource referencedItemTarget;

        /**
         * Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.
         */
        @Child(name = "identifier", type = {Identifier.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Unique identifier of the catalog item", formalDefinition="Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code." )
        protected Identifier identifier;

        /**
         * Used in supporting related concepts, e.g. NDC to RxNorm.
         */
        @Child(name = "additionalIdentifier", type = {Identifier.class}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Any additional identifier(s) for the catalog item, in the same granularity or concept", formalDefinition="Used in supporting related concepts, e.g. NDC to RxNorm." )
        protected List<Identifier> additionalIdentifier;

        /**
         * Classes of devices, or ATC for medication.
         */
        @Child(name = "classification", type = {Identifier.class}, order=5, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Classification (category or class) of the item entry", formalDefinition="Classes of devices, or ATC for medication." )
        protected List<Identifier> classification;

        /**
         * Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.
         */
        @Child(name = "status", type = {CodeableConcept.class}, order=6, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The status of the item, e.g. active, approved…", formalDefinition="Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable." )
        protected CodeableConcept status;

        /**
         * The date from which this catalog entry is expected to be active.
         */
        @Child(name = "validFrom", type = {DateTimeType.class}, order=7, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The date from which this catalog entry is expected to be active", formalDefinition="The date from which this catalog entry is expected to be active." )
        protected DateTimeType validFrom;

        /**
         * The date until which this catalog entry is expected to be active.
         */
        @Child(name = "validTo", type = {DateTimeType.class}, order=8, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The date until which this catalog entry is expected to be active", formalDefinition="The date until which this catalog entry is expected to be active." )
        protected DateTimeType validTo;

        /**
         * Perhaps not needed.
         */
        @Child(name = "lastUpdated", type = {DateTimeType.class}, order=9, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Perhaps not needed", formalDefinition="Perhaps not needed." )
        protected DateTimeType lastUpdated;

        /**
         * Used for examplefor Out of Formulary, or any specifics.
         */
        @Child(name = "additionalCharacteristic", type = {CodeableConcept.class}, order=10, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Additional characteristics of the catalog entry", formalDefinition="Used for examplefor Out of Formulary, or any specifics." )
        protected List<CodeableConcept> additionalCharacteristic;

        /**
         * User for example for ATC classification, or.
         */
        @Child(name = "additionalClassification", type = {CodeableConcept.class}, order=11, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Additional classification of the catalog entry", formalDefinition="User for example for ATC classification, or." )
        protected List<CodeableConcept> additionalClassification;

        /**
         * Used for example,  to point to a substance, or to a device used to administer a medication.
         */
        @Child(name = "relatedItem", type = {}, order=12, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="An item that this catalog entry is related to", formalDefinition="Used for example,  to point to a substance, or to a device used to administer a medication." )
        protected List<CatalogEntryRelatedItemComponent> relatedItem;

        private static final long serialVersionUID = -1856672806L;

    /**
     * Constructor
     */
      public CatalogEntryComponent() {
        super();
      }

        /**
         * @return {@link #type} (The type of item - medication, device, service, protocol or other.)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (The type of item - medication, device, service, protocol or other.)
         */
        public CatalogEntryComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #referencedItem} (Content of the catalog.)
         */
        public Reference getReferencedItem() { 
          if (this.referencedItem == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryComponent.referencedItem");
            else if (Configuration.doAutoCreate())
              this.referencedItem = new Reference(); // cc
          return this.referencedItem;
        }

        public boolean hasReferencedItem() { 
          return this.referencedItem != null && !this.referencedItem.isEmpty();
        }

        /**
         * @param value {@link #referencedItem} (Content of the catalog.)
         */
        public CatalogEntryComponent setReferencedItem(Reference value) { 
          this.referencedItem = value;
          return this;
        }

        /**
         * @return {@link #referencedItem} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Content of the catalog.)
         */
        public Resource getReferencedItemTarget() { 
          return this.referencedItemTarget;
        }

        /**
         * @param value {@link #referencedItem} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Content of the catalog.)
         */
        public CatalogEntryComponent setReferencedItemTarget(Resource value) { 
          this.referencedItemTarget = value;
          return this;
        }

        /**
         * @return {@link #identifier} (Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.)
         */
        public Identifier getIdentifier() { 
          if (this.identifier == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryComponent.identifier");
            else if (Configuration.doAutoCreate())
              this.identifier = new Identifier(); // cc
          return this.identifier;
        }

        public boolean hasIdentifier() { 
          return this.identifier != null && !this.identifier.isEmpty();
        }

        /**
         * @param value {@link #identifier} (Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.)
         */
        public CatalogEntryComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #additionalIdentifier} (Used in supporting related concepts, e.g. NDC to RxNorm.)
         */
        public List<Identifier> getAdditionalIdentifier() { 
          if (this.additionalIdentifier == null)
            this.additionalIdentifier = new ArrayList<Identifier>();
          return this.additionalIdentifier;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public CatalogEntryComponent setAdditionalIdentifier(List<Identifier> theAdditionalIdentifier) { 
          this.additionalIdentifier = theAdditionalIdentifier;
          return this;
        }

        public boolean hasAdditionalIdentifier() { 
          if (this.additionalIdentifier == null)
            return false;
          for (Identifier item : this.additionalIdentifier)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Identifier addAdditionalIdentifier() { //3
          Identifier t = new Identifier();
          if (this.additionalIdentifier == null)
            this.additionalIdentifier = new ArrayList<Identifier>();
          this.additionalIdentifier.add(t);
          return t;
        }

        public CatalogEntryComponent addAdditionalIdentifier(Identifier t) { //3
          if (t == null)
            return this;
          if (this.additionalIdentifier == null)
            this.additionalIdentifier = new ArrayList<Identifier>();
          this.additionalIdentifier.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #additionalIdentifier}, creating it if it does not already exist
         */
        public Identifier getAdditionalIdentifierFirstRep() { 
          if (getAdditionalIdentifier().isEmpty()) {
            addAdditionalIdentifier();
          }
          return getAdditionalIdentifier().get(0);
        }

        /**
         * @return {@link #classification} (Classes of devices, or ATC for medication.)
         */
        public List<Identifier> getClassification() { 
          if (this.classification == null)
            this.classification = new ArrayList<Identifier>();
          return this.classification;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public CatalogEntryComponent setClassification(List<Identifier> theClassification) { 
          this.classification = theClassification;
          return this;
        }

        public boolean hasClassification() { 
          if (this.classification == null)
            return false;
          for (Identifier item : this.classification)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Identifier addClassification() { //3
          Identifier t = new Identifier();
          if (this.classification == null)
            this.classification = new ArrayList<Identifier>();
          this.classification.add(t);
          return t;
        }

        public CatalogEntryComponent addClassification(Identifier t) { //3
          if (t == null)
            return this;
          if (this.classification == null)
            this.classification = new ArrayList<Identifier>();
          this.classification.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #classification}, creating it if it does not already exist
         */
        public Identifier getClassificationFirstRep() { 
          if (getClassification().isEmpty()) {
            addClassification();
          }
          return getClassification().get(0);
        }

        /**
         * @return {@link #status} (Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.)
         */
        public CodeableConcept getStatus() { 
          if (this.status == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryComponent.status");
            else if (Configuration.doAutoCreate())
              this.status = new CodeableConcept(); // cc
          return this.status;
        }

        public boolean hasStatus() { 
          return this.status != null && !this.status.isEmpty();
        }

        /**
         * @param value {@link #status} (Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.)
         */
        public CatalogEntryComponent setStatus(CodeableConcept value) { 
          this.status = value;
          return this;
        }

        /**
         * @return {@link #validFrom} (The date from which this catalog entry is expected to be active.). This is the underlying object with id, value and extensions. The accessor "getValidFrom" gives direct access to the value
         */
        public DateTimeType getValidFromElement() { 
          if (this.validFrom == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryComponent.validFrom");
            else if (Configuration.doAutoCreate())
              this.validFrom = new DateTimeType(); // bb
          return this.validFrom;
        }

        public boolean hasValidFromElement() { 
          return this.validFrom != null && !this.validFrom.isEmpty();
        }

        public boolean hasValidFrom() { 
          return this.validFrom != null && !this.validFrom.isEmpty();
        }

        /**
         * @param value {@link #validFrom} (The date from which this catalog entry is expected to be active.). This is the underlying object with id, value and extensions. The accessor "getValidFrom" gives direct access to the value
         */
        public CatalogEntryComponent setValidFromElement(DateTimeType value) { 
          this.validFrom = value;
          return this;
        }

        /**
         * @return The date from which this catalog entry is expected to be active.
         */
        public Date getValidFrom() { 
          return this.validFrom == null ? null : this.validFrom.getValue();
        }

        /**
         * @param value The date from which this catalog entry is expected to be active.
         */
        public CatalogEntryComponent setValidFrom(Date value) { 
          if (value == null)
            this.validFrom = null;
          else {
            if (this.validFrom == null)
              this.validFrom = new DateTimeType();
            this.validFrom.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #validTo} (The date until which this catalog entry is expected to be active.). This is the underlying object with id, value and extensions. The accessor "getValidTo" gives direct access to the value
         */
        public DateTimeType getValidToElement() { 
          if (this.validTo == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryComponent.validTo");
            else if (Configuration.doAutoCreate())
              this.validTo = new DateTimeType(); // bb
          return this.validTo;
        }

        public boolean hasValidToElement() { 
          return this.validTo != null && !this.validTo.isEmpty();
        }

        public boolean hasValidTo() { 
          return this.validTo != null && !this.validTo.isEmpty();
        }

        /**
         * @param value {@link #validTo} (The date until which this catalog entry is expected to be active.). This is the underlying object with id, value and extensions. The accessor "getValidTo" gives direct access to the value
         */
        public CatalogEntryComponent setValidToElement(DateTimeType value) { 
          this.validTo = value;
          return this;
        }

        /**
         * @return The date until which this catalog entry is expected to be active.
         */
        public Date getValidTo() { 
          return this.validTo == null ? null : this.validTo.getValue();
        }

        /**
         * @param value The date until which this catalog entry is expected to be active.
         */
        public CatalogEntryComponent setValidTo(Date value) { 
          if (value == null)
            this.validTo = null;
          else {
            if (this.validTo == null)
              this.validTo = new DateTimeType();
            this.validTo.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #lastUpdated} (Perhaps not needed.). This is the underlying object with id, value and extensions. The accessor "getLastUpdated" gives direct access to the value
         */
        public DateTimeType getLastUpdatedElement() { 
          if (this.lastUpdated == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryComponent.lastUpdated");
            else if (Configuration.doAutoCreate())
              this.lastUpdated = new DateTimeType(); // bb
          return this.lastUpdated;
        }

        public boolean hasLastUpdatedElement() { 
          return this.lastUpdated != null && !this.lastUpdated.isEmpty();
        }

        public boolean hasLastUpdated() { 
          return this.lastUpdated != null && !this.lastUpdated.isEmpty();
        }

        /**
         * @param value {@link #lastUpdated} (Perhaps not needed.). This is the underlying object with id, value and extensions. The accessor "getLastUpdated" gives direct access to the value
         */
        public CatalogEntryComponent setLastUpdatedElement(DateTimeType value) { 
          this.lastUpdated = value;
          return this;
        }

        /**
         * @return Perhaps not needed.
         */
        public Date getLastUpdated() { 
          return this.lastUpdated == null ? null : this.lastUpdated.getValue();
        }

        /**
         * @param value Perhaps not needed.
         */
        public CatalogEntryComponent setLastUpdated(Date value) { 
          if (value == null)
            this.lastUpdated = null;
          else {
            if (this.lastUpdated == null)
              this.lastUpdated = new DateTimeType();
            this.lastUpdated.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #additionalCharacteristic} (Used for examplefor Out of Formulary, or any specifics.)
         */
        public List<CodeableConcept> getAdditionalCharacteristic() { 
          if (this.additionalCharacteristic == null)
            this.additionalCharacteristic = new ArrayList<CodeableConcept>();
          return this.additionalCharacteristic;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public CatalogEntryComponent setAdditionalCharacteristic(List<CodeableConcept> theAdditionalCharacteristic) { 
          this.additionalCharacteristic = theAdditionalCharacteristic;
          return this;
        }

        public boolean hasAdditionalCharacteristic() { 
          if (this.additionalCharacteristic == null)
            return false;
          for (CodeableConcept item : this.additionalCharacteristic)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addAdditionalCharacteristic() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.additionalCharacteristic == null)
            this.additionalCharacteristic = new ArrayList<CodeableConcept>();
          this.additionalCharacteristic.add(t);
          return t;
        }

        public CatalogEntryComponent addAdditionalCharacteristic(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.additionalCharacteristic == null)
            this.additionalCharacteristic = new ArrayList<CodeableConcept>();
          this.additionalCharacteristic.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #additionalCharacteristic}, creating it if it does not already exist
         */
        public CodeableConcept getAdditionalCharacteristicFirstRep() { 
          if (getAdditionalCharacteristic().isEmpty()) {
            addAdditionalCharacteristic();
          }
          return getAdditionalCharacteristic().get(0);
        }

        /**
         * @return {@link #additionalClassification} (User for example for ATC classification, or.)
         */
        public List<CodeableConcept> getAdditionalClassification() { 
          if (this.additionalClassification == null)
            this.additionalClassification = new ArrayList<CodeableConcept>();
          return this.additionalClassification;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public CatalogEntryComponent setAdditionalClassification(List<CodeableConcept> theAdditionalClassification) { 
          this.additionalClassification = theAdditionalClassification;
          return this;
        }

        public boolean hasAdditionalClassification() { 
          if (this.additionalClassification == null)
            return false;
          for (CodeableConcept item : this.additionalClassification)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addAdditionalClassification() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.additionalClassification == null)
            this.additionalClassification = new ArrayList<CodeableConcept>();
          this.additionalClassification.add(t);
          return t;
        }

        public CatalogEntryComponent addAdditionalClassification(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.additionalClassification == null)
            this.additionalClassification = new ArrayList<CodeableConcept>();
          this.additionalClassification.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #additionalClassification}, creating it if it does not already exist
         */
        public CodeableConcept getAdditionalClassificationFirstRep() { 
          if (getAdditionalClassification().isEmpty()) {
            addAdditionalClassification();
          }
          return getAdditionalClassification().get(0);
        }

        /**
         * @return {@link #relatedItem} (Used for example,  to point to a substance, or to a device used to administer a medication.)
         */
        public List<CatalogEntryRelatedItemComponent> getRelatedItem() { 
          if (this.relatedItem == null)
            this.relatedItem = new ArrayList<CatalogEntryRelatedItemComponent>();
          return this.relatedItem;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public CatalogEntryComponent setRelatedItem(List<CatalogEntryRelatedItemComponent> theRelatedItem) { 
          this.relatedItem = theRelatedItem;
          return this;
        }

        public boolean hasRelatedItem() { 
          if (this.relatedItem == null)
            return false;
          for (CatalogEntryRelatedItemComponent item : this.relatedItem)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CatalogEntryRelatedItemComponent addRelatedItem() { //3
          CatalogEntryRelatedItemComponent t = new CatalogEntryRelatedItemComponent();
          if (this.relatedItem == null)
            this.relatedItem = new ArrayList<CatalogEntryRelatedItemComponent>();
          this.relatedItem.add(t);
          return t;
        }

        public CatalogEntryComponent addRelatedItem(CatalogEntryRelatedItemComponent t) { //3
          if (t == null)
            return this;
          if (this.relatedItem == null)
            this.relatedItem = new ArrayList<CatalogEntryRelatedItemComponent>();
          this.relatedItem.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #relatedItem}, creating it if it does not already exist
         */
        public CatalogEntryRelatedItemComponent getRelatedItemFirstRep() { 
          if (getRelatedItem().isEmpty()) {
            addRelatedItem();
          }
          return getRelatedItem().get(0);
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "The type of item - medication, device, service, protocol or other.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("referencedItem", "Reference(Medication|Device|Procedure|CarePlan|Organization|Practitioner|HealthcareService|ServiceDefinition)", "Content of the catalog.", 0, java.lang.Integer.MAX_VALUE, referencedItem));
          childrenList.add(new Property("identifier", "Identifier", "Used in supporting different identifiers for the same product, e.g. manufacturer code and retailer code.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("additionalIdentifier", "Identifier", "Used in supporting related concepts, e.g. NDC to RxNorm.", 0, java.lang.Integer.MAX_VALUE, additionalIdentifier));
          childrenList.add(new Property("classification", "Identifier", "Classes of devices, or ATC for medication.", 0, java.lang.Integer.MAX_VALUE, classification));
          childrenList.add(new Property("status", "CodeableConcept", "Used to support catalog exchange even for unsupported products, e.g. getting list of medications even if not prescribable.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("validFrom", "dateTime", "The date from which this catalog entry is expected to be active.", 0, java.lang.Integer.MAX_VALUE, validFrom));
          childrenList.add(new Property("validTo", "dateTime", "The date until which this catalog entry is expected to be active.", 0, java.lang.Integer.MAX_VALUE, validTo));
          childrenList.add(new Property("lastUpdated", "dateTime", "Perhaps not needed.", 0, java.lang.Integer.MAX_VALUE, lastUpdated));
          childrenList.add(new Property("additionalCharacteristic", "CodeableConcept", "Used for examplefor Out of Formulary, or any specifics.", 0, java.lang.Integer.MAX_VALUE, additionalCharacteristic));
          childrenList.add(new Property("additionalClassification", "CodeableConcept", "User for example for ATC classification, or.", 0, java.lang.Integer.MAX_VALUE, additionalClassification));
          childrenList.add(new Property("relatedItem", "", "Used for example,  to point to a substance, or to a device used to administer a medication.", 0, java.lang.Integer.MAX_VALUE, relatedItem));
        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -1896630996: /*referencedItem*/ return this.referencedItem == null ? new Base[0] : new Base[] {this.referencedItem}; // Reference
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : new Base[] {this.identifier}; // Identifier
        case 1195162672: /*additionalIdentifier*/ return this.additionalIdentifier == null ? new Base[0] : this.additionalIdentifier.toArray(new Base[this.additionalIdentifier.size()]); // Identifier
        case 382350310: /*classification*/ return this.classification == null ? new Base[0] : this.classification.toArray(new Base[this.classification.size()]); // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // CodeableConcept
        case -1110590010: /*validFrom*/ return this.validFrom == null ? new Base[0] : new Base[] {this.validFrom}; // DateTimeType
        case 231246743: /*validTo*/ return this.validTo == null ? new Base[0] : new Base[] {this.validTo}; // DateTimeType
        case 1649733957: /*lastUpdated*/ return this.lastUpdated == null ? new Base[0] : new Base[] {this.lastUpdated}; // DateTimeType
        case -1638369886: /*additionalCharacteristic*/ return this.additionalCharacteristic == null ? new Base[0] : this.additionalCharacteristic.toArray(new Base[this.additionalCharacteristic.size()]); // CodeableConcept
        case -1622333459: /*additionalClassification*/ return this.additionalClassification == null ? new Base[0] : this.additionalClassification.toArray(new Base[this.additionalClassification.size()]); // CodeableConcept
        case 1112702430: /*relatedItem*/ return this.relatedItem == null ? new Base[0] : this.relatedItem.toArray(new Base[this.relatedItem.size()]); // CatalogEntryRelatedItemComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1896630996: // referencedItem
          this.referencedItem = castToReference(value); // Reference
          return value;
        case -1618432855: // identifier
          this.identifier = castToIdentifier(value); // Identifier
          return value;
        case 1195162672: // additionalIdentifier
          this.getAdditionalIdentifier().add(castToIdentifier(value)); // Identifier
          return value;
        case 382350310: // classification
          this.getClassification().add(castToIdentifier(value)); // Identifier
          return value;
        case -892481550: // status
          this.status = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1110590010: // validFrom
          this.validFrom = castToDateTime(value); // DateTimeType
          return value;
        case 231246743: // validTo
          this.validTo = castToDateTime(value); // DateTimeType
          return value;
        case 1649733957: // lastUpdated
          this.lastUpdated = castToDateTime(value); // DateTimeType
          return value;
        case -1638369886: // additionalCharacteristic
          this.getAdditionalCharacteristic().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -1622333459: // additionalClassification
          this.getAdditionalClassification().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case 1112702430: // relatedItem
          this.getRelatedItem().add((CatalogEntryRelatedItemComponent) value); // CatalogEntryRelatedItemComponent
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("referencedItem")) {
          this.referencedItem = castToReference(value); // Reference
        } else if (name.equals("identifier")) {
          this.identifier = castToIdentifier(value); // Identifier
        } else if (name.equals("additionalIdentifier")) {
          this.getAdditionalIdentifier().add(castToIdentifier(value));
        } else if (name.equals("classification")) {
          this.getClassification().add(castToIdentifier(value));
        } else if (name.equals("status")) {
          this.status = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("validFrom")) {
          this.validFrom = castToDateTime(value); // DateTimeType
        } else if (name.equals("validTo")) {
          this.validTo = castToDateTime(value); // DateTimeType
        } else if (name.equals("lastUpdated")) {
          this.lastUpdated = castToDateTime(value); // DateTimeType
        } else if (name.equals("additionalCharacteristic")) {
          this.getAdditionalCharacteristic().add(castToCodeableConcept(value));
        } else if (name.equals("additionalClassification")) {
          this.getAdditionalClassification().add(castToCodeableConcept(value));
        } else if (name.equals("relatedItem")) {
          this.getRelatedItem().add((CatalogEntryRelatedItemComponent) value);
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610:  return getType(); 
        case -1896630996:  return getReferencedItem(); 
        case -1618432855:  return getIdentifier(); 
        case 1195162672:  return addAdditionalIdentifier(); 
        case 382350310:  return addClassification(); 
        case -892481550:  return getStatus(); 
        case -1110590010:  return getValidFromElement();
        case 231246743:  return getValidToElement();
        case 1649733957:  return getLastUpdatedElement();
        case -1638369886:  return addAdditionalCharacteristic(); 
        case -1622333459:  return addAdditionalClassification(); 
        case 1112702430:  return addRelatedItem(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -1896630996: /*referencedItem*/ return new String[] {"Reference"};
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case 1195162672: /*additionalIdentifier*/ return new String[] {"Identifier"};
        case 382350310: /*classification*/ return new String[] {"Identifier"};
        case -892481550: /*status*/ return new String[] {"CodeableConcept"};
        case -1110590010: /*validFrom*/ return new String[] {"dateTime"};
        case 231246743: /*validTo*/ return new String[] {"dateTime"};
        case 1649733957: /*lastUpdated*/ return new String[] {"dateTime"};
        case -1638369886: /*additionalCharacteristic*/ return new String[] {"CodeableConcept"};
        case -1622333459: /*additionalClassification*/ return new String[] {"CodeableConcept"};
        case 1112702430: /*relatedItem*/ return new String[] {};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("referencedItem")) {
          this.referencedItem = new Reference();
          return this.referencedItem;
        }
        else if (name.equals("identifier")) {
          this.identifier = new Identifier();
          return this.identifier;
        }
        else if (name.equals("additionalIdentifier")) {
          return addAdditionalIdentifier();
        }
        else if (name.equals("classification")) {
          return addClassification();
        }
        else if (name.equals("status")) {
          this.status = new CodeableConcept();
          return this.status;
        }
        else if (name.equals("validFrom")) {
          throw new FHIRException("Cannot call addChild on a primitive type Catalog.validFrom");
        }
        else if (name.equals("validTo")) {
          throw new FHIRException("Cannot call addChild on a primitive type Catalog.validTo");
        }
        else if (name.equals("lastUpdated")) {
          throw new FHIRException("Cannot call addChild on a primitive type Catalog.lastUpdated");
        }
        else if (name.equals("additionalCharacteristic")) {
          return addAdditionalCharacteristic();
        }
        else if (name.equals("additionalClassification")) {
          return addAdditionalClassification();
        }
        else if (name.equals("relatedItem")) {
          return addRelatedItem();
        }
        else
          return super.addChild(name);
      }

      public CatalogEntryComponent copy() {
        CatalogEntryComponent dst = new CatalogEntryComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.referencedItem = referencedItem == null ? null : referencedItem.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        if (additionalIdentifier != null) {
          dst.additionalIdentifier = new ArrayList<Identifier>();
          for (Identifier i : additionalIdentifier)
            dst.additionalIdentifier.add(i.copy());
        };
        if (classification != null) {
          dst.classification = new ArrayList<Identifier>();
          for (Identifier i : classification)
            dst.classification.add(i.copy());
        };
        dst.status = status == null ? null : status.copy();
        dst.validFrom = validFrom == null ? null : validFrom.copy();
        dst.validTo = validTo == null ? null : validTo.copy();
        dst.lastUpdated = lastUpdated == null ? null : lastUpdated.copy();
        if (additionalCharacteristic != null) {
          dst.additionalCharacteristic = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : additionalCharacteristic)
            dst.additionalCharacteristic.add(i.copy());
        };
        if (additionalClassification != null) {
          dst.additionalClassification = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : additionalClassification)
            dst.additionalClassification.add(i.copy());
        };
        if (relatedItem != null) {
          dst.relatedItem = new ArrayList<CatalogEntryRelatedItemComponent>();
          for (CatalogEntryRelatedItemComponent i : relatedItem)
            dst.relatedItem.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof CatalogEntryComponent))
          return false;
        CatalogEntryComponent o = (CatalogEntryComponent) other;
        return compareDeep(type, o.type, true) && compareDeep(referencedItem, o.referencedItem, true) && compareDeep(identifier, o.identifier, true)
           && compareDeep(additionalIdentifier, o.additionalIdentifier, true) && compareDeep(classification, o.classification, true)
           && compareDeep(status, o.status, true) && compareDeep(validFrom, o.validFrom, true) && compareDeep(validTo, o.validTo, true)
           && compareDeep(lastUpdated, o.lastUpdated, true) && compareDeep(additionalCharacteristic, o.additionalCharacteristic, true)
           && compareDeep(additionalClassification, o.additionalClassification, true) && compareDeep(relatedItem, o.relatedItem, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof CatalogEntryComponent))
          return false;
        CatalogEntryComponent o = (CatalogEntryComponent) other;
        return compareValues(validFrom, o.validFrom, true) && compareValues(validTo, o.validTo, true) && compareValues(lastUpdated, o.lastUpdated, true)
          ;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(type, referencedItem, identifier
          , additionalIdentifier, classification, status, validFrom, validTo, lastUpdated
          , additionalCharacteristic, additionalClassification, relatedItem);
      }

  public String fhirType() {
    return "Catalog.entry";

  }

  }

    @Block()
    public static class CatalogEntryRelatedItemComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.
         */
        @Child(name = "relationtype", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The type of relation to the related item", formalDefinition="The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc." )
        protected CodeableConcept relationtype;

        /**
         * The type of related item.
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The type of related item - medication, devices…", formalDefinition="The type of related item." )
        protected CodeableConcept type;

        /**
         * The reference to the related item.
         */
        @Child(name = "identifier", type = {Medication.class, Device.class, Procedure.class, CarePlan.class, Organization.class, Practitioner.class, HealthcareService.class, ServiceDefinition.class}, order=3, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The reference to the related item", formalDefinition="The reference to the related item." )
        protected Reference identifier;

        /**
         * The actual object that is the target of the reference (The reference to the related item.)
         */
        protected Resource identifierTarget;

        private static final long serialVersionUID = 2049489802L;

    /**
     * Constructor
     */
      public CatalogEntryRelatedItemComponent() {
        super();
      }

    /**
     * Constructor
     */
      public CatalogEntryRelatedItemComponent(CodeableConcept relationtype, Reference identifier) {
        super();
        this.relationtype = relationtype;
        this.identifier = identifier;
      }

        /**
         * @return {@link #relationtype} (The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.)
         */
        public CodeableConcept getRelationtype() { 
          if (this.relationtype == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryRelatedItemComponent.relationtype");
            else if (Configuration.doAutoCreate())
              this.relationtype = new CodeableConcept(); // cc
          return this.relationtype;
        }

        public boolean hasRelationtype() { 
          return this.relationtype != null && !this.relationtype.isEmpty();
        }

        /**
         * @param value {@link #relationtype} (The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.)
         */
        public CatalogEntryRelatedItemComponent setRelationtype(CodeableConcept value) { 
          this.relationtype = value;
          return this;
        }

        /**
         * @return {@link #type} (The type of related item.)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryRelatedItemComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (The type of related item.)
         */
        public CatalogEntryRelatedItemComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #identifier} (The reference to the related item.)
         */
        public Reference getIdentifier() { 
          if (this.identifier == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CatalogEntryRelatedItemComponent.identifier");
            else if (Configuration.doAutoCreate())
              this.identifier = new Reference(); // cc
          return this.identifier;
        }

        public boolean hasIdentifier() { 
          return this.identifier != null && !this.identifier.isEmpty();
        }

        /**
         * @param value {@link #identifier} (The reference to the related item.)
         */
        public CatalogEntryRelatedItemComponent setIdentifier(Reference value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #identifier} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The reference to the related item.)
         */
        public Resource getIdentifierTarget() { 
          return this.identifierTarget;
        }

        /**
         * @param value {@link #identifier} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The reference to the related item.)
         */
        public CatalogEntryRelatedItemComponent setIdentifierTarget(Resource value) { 
          this.identifierTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relationtype", "CodeableConcept", "The type of relation to the related item: child, parent, packageContent, containerPackage, usedIn, uses, requires, etc.", 0, java.lang.Integer.MAX_VALUE, relationtype));
          childrenList.add(new Property("type", "CodeableConcept", "The type of related item.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("identifier", "Reference(Medication|Device|Procedure|CarePlan|Organization|Practitioner|HealthcareService|ServiceDefinition)", "The reference to the related item.", 0, java.lang.Integer.MAX_VALUE, identifier));
        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -261805258: /*relationtype*/ return this.relationtype == null ? new Base[0] : new Base[] {this.relationtype}; // CodeableConcept
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : new Base[] {this.identifier}; // Reference
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -261805258: // relationtype
          this.relationtype = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1618432855: // identifier
          this.identifier = castToReference(value); // Reference
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("relationtype")) {
          this.relationtype = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("identifier")) {
          this.identifier = castToReference(value); // Reference
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -261805258:  return getRelationtype(); 
        case 3575610:  return getType(); 
        case -1618432855:  return getIdentifier(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -261805258: /*relationtype*/ return new String[] {"CodeableConcept"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -1618432855: /*identifier*/ return new String[] {"Reference"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("relationtype")) {
          this.relationtype = new CodeableConcept();
          return this.relationtype;
        }
        else if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("identifier")) {
          this.identifier = new Reference();
          return this.identifier;
        }
        else
          return super.addChild(name);
      }

      public CatalogEntryRelatedItemComponent copy() {
        CatalogEntryRelatedItemComponent dst = new CatalogEntryRelatedItemComponent();
        copyValues(dst);
        dst.relationtype = relationtype == null ? null : relationtype.copy();
        dst.type = type == null ? null : type.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof CatalogEntryRelatedItemComponent))
          return false;
        CatalogEntryRelatedItemComponent o = (CatalogEntryRelatedItemComponent) other;
        return compareDeep(relationtype, o.relationtype, true) && compareDeep(type, o.type, true) && compareDeep(identifier, o.identifier, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof CatalogEntryRelatedItemComponent))
          return false;
        CatalogEntryRelatedItemComponent o = (CatalogEntryRelatedItemComponent) other;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(relationtype, type, identifier
          );
      }

  public String fhirType() {
    return "Catalog.entry.relatedItem";

  }

  }

    /**
     * Unique for each resource instance.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Unique identifier for the  catalog resource", formalDefinition="Unique for each resource instance." )
    protected Identifier identifier;

    /**
     * Properties of the document - authorship, versions, etc.
     */
    @Child(name = "document", type = {}, order=1, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Properties of the document - authorship, versions, etc", formalDefinition="Properties of the document - authorship, versions, etc." )
    protected CatalogDocumentComponent document;

    /**
     * Each item of the catalog.
     */
    @Child(name = "entry", type = {}, order=2, min=1, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Each item of the catalog", formalDefinition="Each item of the catalog." )
    protected List<CatalogEntryComponent> entry;

    private static final long serialVersionUID = 1267546114L;

  /**
   * Constructor
   */
    public Catalog() {
      super();
    }

  /**
   * Constructor
   */
    public Catalog(Identifier identifier, CatalogDocumentComponent document) {
      super();
      this.identifier = identifier;
      this.document = document;
    }

    /**
     * @return {@link #identifier} (Unique for each resource instance.)
     */
    public Identifier getIdentifier() { 
      if (this.identifier == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Catalog.identifier");
        else if (Configuration.doAutoCreate())
          this.identifier = new Identifier(); // cc
      return this.identifier;
    }

    public boolean hasIdentifier() { 
      return this.identifier != null && !this.identifier.isEmpty();
    }

    /**
     * @param value {@link #identifier} (Unique for each resource instance.)
     */
    public Catalog setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #document} (Properties of the document - authorship, versions, etc.)
     */
    public CatalogDocumentComponent getDocument() { 
      if (this.document == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Catalog.document");
        else if (Configuration.doAutoCreate())
          this.document = new CatalogDocumentComponent(); // cc
      return this.document;
    }

    public boolean hasDocument() { 
      return this.document != null && !this.document.isEmpty();
    }

    /**
     * @param value {@link #document} (Properties of the document - authorship, versions, etc.)
     */
    public Catalog setDocument(CatalogDocumentComponent value) { 
      this.document = value;
      return this;
    }

    /**
     * @return {@link #entry} (Each item of the catalog.)
     */
    public List<CatalogEntryComponent> getEntry() { 
      if (this.entry == null)
        this.entry = new ArrayList<CatalogEntryComponent>();
      return this.entry;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Catalog setEntry(List<CatalogEntryComponent> theEntry) { 
      this.entry = theEntry;
      return this;
    }

    public boolean hasEntry() { 
      if (this.entry == null)
        return false;
      for (CatalogEntryComponent item : this.entry)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CatalogEntryComponent addEntry() { //3
      CatalogEntryComponent t = new CatalogEntryComponent();
      if (this.entry == null)
        this.entry = new ArrayList<CatalogEntryComponent>();
      this.entry.add(t);
      return t;
    }

    public Catalog addEntry(CatalogEntryComponent t) { //3
      if (t == null)
        return this;
      if (this.entry == null)
        this.entry = new ArrayList<CatalogEntryComponent>();
      this.entry.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #entry}, creating it if it does not already exist
     */
    public CatalogEntryComponent getEntryFirstRep() { 
      if (getEntry().isEmpty()) {
        addEntry();
      }
      return getEntry().get(0);
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Unique for each resource instance.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("document", "", "Properties of the document - authorship, versions, etc.", 0, java.lang.Integer.MAX_VALUE, document));
        childrenList.add(new Property("entry", "", "Each item of the catalog.", 0, java.lang.Integer.MAX_VALUE, entry));
      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : new Base[] {this.identifier}; // Identifier
        case 861720859: /*document*/ return this.document == null ? new Base[0] : new Base[] {this.document}; // CatalogDocumentComponent
        case 96667762: /*entry*/ return this.entry == null ? new Base[0] : this.entry.toArray(new Base[this.entry.size()]); // CatalogEntryComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.identifier = castToIdentifier(value); // Identifier
          return value;
        case 861720859: // document
          this.document = (CatalogDocumentComponent) value; // CatalogDocumentComponent
          return value;
        case 96667762: // entry
          this.getEntry().add((CatalogEntryComponent) value); // CatalogEntryComponent
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.identifier = castToIdentifier(value); // Identifier
        } else if (name.equals("document")) {
          this.document = (CatalogDocumentComponent) value; // CatalogDocumentComponent
        } else if (name.equals("entry")) {
          this.getEntry().add((CatalogEntryComponent) value);
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return getIdentifier(); 
        case 861720859:  return getDocument(); 
        case 96667762:  return addEntry(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case 861720859: /*document*/ return new String[] {};
        case 96667762: /*entry*/ return new String[] {};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          this.identifier = new Identifier();
          return this.identifier;
        }
        else if (name.equals("document")) {
          this.document = new CatalogDocumentComponent();
          return this.document;
        }
        else if (name.equals("entry")) {
          return addEntry();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "Catalog";

  }

      public Catalog copy() {
        Catalog dst = new Catalog();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.document = document == null ? null : document.copy();
        if (entry != null) {
          dst.entry = new ArrayList<CatalogEntryComponent>();
          for (CatalogEntryComponent i : entry)
            dst.entry.add(i.copy());
        };
        return dst;
      }

      protected Catalog typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof Catalog))
          return false;
        Catalog o = (Catalog) other;
        return compareDeep(identifier, o.identifier, true) && compareDeep(document, o.document, true) && compareDeep(entry, o.entry, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof Catalog))
          return false;
        Catalog o = (Catalog) other;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, document, entry
          );
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Catalog;
   }

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>Unique identifier for the  catalog resource</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Catalog.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="Catalog.identifier", description="Unique identifier for the  catalog resource", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>Unique identifier for the  catalog resource</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Catalog.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);


}

