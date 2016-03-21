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

// Generated on Mon, Mar 21, 2016 11:55+1100 for FHIR v1.3.0

import java.util.*;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
/**
 * This resource allows for the definition of an order set as a sharable, consumable, and executable artifact in support of clinical decision support.
 */
@ResourceDef(name="OrderSet", profile="http://hl7.org/fhir/Profile/OrderSet")
public class OrderSet extends DomainResource {

    /**
     * A reference to a ModuleMetadata resource containing metadata for the orderset.
     */
    @Child(name = "moduleMetadata", type = {ModuleMetadata.class}, order=0, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="The metadata for the orderset", formalDefinition="A reference to a ModuleMetadata resource containing metadata for the orderset." )
    protected ModuleMetadata moduleMetadata;

    /**
     * A reference to a Library resource containing any formal logic used by the orderset.
     */
    @Child(name = "library", type = {Library.class}, order=1, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Logic used by the orderset", formalDefinition="A reference to a Library resource containing any formal logic used by the orderset." )
    protected List<Reference> library;
    /**
     * The actual objects that are the target of the reference (A reference to a Library resource containing any formal logic used by the orderset.)
     */
    protected List<Library> libraryTarget;


    /**
     * The definition of the actions that make up the order set.
     */
    @Child(name = "action", type = {ActionDefinition.class}, order=2, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="", formalDefinition="The definition of the actions that make up the order set." )
    protected List<ActionDefinition> action;

    private static final long serialVersionUID = -728217200L;

  /**
   * Constructor
   */
    public OrderSet() {
      super();
    }

    /**
     * @return {@link #moduleMetadata} (A reference to a ModuleMetadata resource containing metadata for the orderset.)
     */
    public ModuleMetadata getModuleMetadata() { 
      if (this.moduleMetadata == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create OrderSet.moduleMetadata");
        else if (Configuration.doAutoCreate())
          this.moduleMetadata = new ModuleMetadata(); // cc
      return this.moduleMetadata;
    }

    public boolean hasModuleMetadata() { 
      return this.moduleMetadata != null && !this.moduleMetadata.isEmpty();
    }

    /**
     * @param value {@link #moduleMetadata} (A reference to a ModuleMetadata resource containing metadata for the orderset.)
     */
    public OrderSet setModuleMetadata(ModuleMetadata value) { 
      this.moduleMetadata = value;
      return this;
    }

    /**
     * @return {@link #library} (A reference to a Library resource containing any formal logic used by the orderset.)
     */
    public List<Reference> getLibrary() { 
      if (this.library == null)
        this.library = new ArrayList<Reference>();
      return this.library;
    }

    public boolean hasLibrary() { 
      if (this.library == null)
        return false;
      for (Reference item : this.library)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #library} (A reference to a Library resource containing any formal logic used by the orderset.)
     */
    // syntactic sugar
    public Reference addLibrary() { //3
      Reference t = new Reference();
      if (this.library == null)
        this.library = new ArrayList<Reference>();
      this.library.add(t);
      return t;
    }

    // syntactic sugar
    public OrderSet addLibrary(Reference t) { //3
      if (t == null)
        return this;
      if (this.library == null)
        this.library = new ArrayList<Reference>();
      this.library.add(t);
      return this;
    }

    /**
     * @return {@link #library} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. A reference to a Library resource containing any formal logic used by the orderset.)
     */
    public List<Library> getLibraryTarget() { 
      if (this.libraryTarget == null)
        this.libraryTarget = new ArrayList<Library>();
      return this.libraryTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #library} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. A reference to a Library resource containing any formal logic used by the orderset.)
     */
    public Library addLibraryTarget() { 
      Library r = new Library();
      if (this.libraryTarget == null)
        this.libraryTarget = new ArrayList<Library>();
      this.libraryTarget.add(r);
      return r;
    }

    /**
     * @return {@link #action} (The definition of the actions that make up the order set.)
     */
    public List<ActionDefinition> getAction() { 
      if (this.action == null)
        this.action = new ArrayList<ActionDefinition>();
      return this.action;
    }

    public boolean hasAction() { 
      if (this.action == null)
        return false;
      for (ActionDefinition item : this.action)
        if (!item.isEmpty())
          return true;
      return false;
    }

    /**
     * @return {@link #action} (The definition of the actions that make up the order set.)
     */
    // syntactic sugar
    public ActionDefinition addAction() { //3
      ActionDefinition t = new ActionDefinition();
      if (this.action == null)
        this.action = new ArrayList<ActionDefinition>();
      this.action.add(t);
      return t;
    }

    // syntactic sugar
    public OrderSet addAction(ActionDefinition t) { //3
      if (t == null)
        return this;
      if (this.action == null)
        this.action = new ArrayList<ActionDefinition>();
      this.action.add(t);
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("moduleMetadata", "ModuleMetadata", "A reference to a ModuleMetadata resource containing metadata for the orderset.", 0, java.lang.Integer.MAX_VALUE, moduleMetadata));
        childrenList.add(new Property("library", "Reference(Library)", "A reference to a Library resource containing any formal logic used by the orderset.", 0, java.lang.Integer.MAX_VALUE, library));
        childrenList.add(new Property("action", "ActionDefinition", "The definition of the actions that make up the order set.", 0, java.lang.Integer.MAX_VALUE, action));
      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("moduleMetadata"))
          this.moduleMetadata = castToModuleMetadata(value); // ModuleMetadata
        else if (name.equals("library"))
          this.getLibrary().add(castToReference(value));
        else if (name.equals("action"))
          this.getAction().add(castToActionDefinition(value));
        else
          super.setProperty(name, value);
      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("moduleMetadata")) {
          this.moduleMetadata = new ModuleMetadata();
          return this.moduleMetadata;
        }
        else if (name.equals("library")) {
          return addLibrary();
        }
        else if (name.equals("action")) {
          return addAction();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "OrderSet";

  }

      public OrderSet copy() {
        OrderSet dst = new OrderSet();
        copyValues(dst);
        dst.moduleMetadata = moduleMetadata == null ? null : moduleMetadata.copy();
        if (library != null) {
          dst.library = new ArrayList<Reference>();
          for (Reference i : library)
            dst.library.add(i.copy());
        };
        if (action != null) {
          dst.action = new ArrayList<ActionDefinition>();
          for (ActionDefinition i : action)
            dst.action.add(i.copy());
        };
        return dst;
      }

      protected OrderSet typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof OrderSet))
          return false;
        OrderSet o = (OrderSet) other;
        return compareDeep(moduleMetadata, o.moduleMetadata, true) && compareDeep(library, o.library, true)
           && compareDeep(action, o.action, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof OrderSet))
          return false;
        OrderSet o = (OrderSet) other;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && (moduleMetadata == null || moduleMetadata.isEmpty()) && (library == null || library.isEmpty())
           && (action == null || action.isEmpty());
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.OrderSet;
   }


}

