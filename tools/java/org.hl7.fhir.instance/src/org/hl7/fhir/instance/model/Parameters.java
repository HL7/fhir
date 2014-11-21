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
 * This special resource type is used to represent [operation](operations.html] request and response. It has no other use, and there is no RESTful end=point associated with it.
 */
public class Parameters extends Resource {

    public static class ParametersParameterComponent extends BackboneElement {
        /**
         * The name of the parameter (reference to the operation definition).
         */
        protected StringType name;

        /**
         * If the parameter is a data type.
         */
        protected org.hl7.fhir.instance.model.Type value;

        /**
         * If the parameter is a whole resource.
         */
        protected Resource resource;

        private static final long serialVersionUID = 1120601371L;

      public ParametersParameterComponent() {
        super();
      }

      public ParametersParameterComponent(StringType name) {
        super();
        this.name = name;
      }

        /**
         * @return {@link #name} (The name of the parameter (reference to the operation definition).). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of the parameter (reference to the operation definition).). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public ParametersParameterComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the parameter (reference to the operation definition).
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the parameter (reference to the operation definition).
         */
        public ParametersParameterComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #value} (If the parameter is a data type.)
         */
        public org.hl7.fhir.instance.model.Type getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (If the parameter is a data type.)
         */
        public ParametersParameterComponent setValue(org.hl7.fhir.instance.model.Type value) { 
          this.value = value;
          return this;
        }

        /**
         * @return {@link #resource} (If the parameter is a whole resource.)
         */
        public Resource getResource() { 
          return this.resource;
        }

        /**
         * @param value {@link #resource} (If the parameter is a whole resource.)
         */
        public ParametersParameterComponent setResource(Resource value) { 
          this.resource = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of the parameter (reference to the operation definition).", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("value[x]", "*", "If the parameter is a data type.", 0, java.lang.Integer.MAX_VALUE, value));
          childrenList.add(new Property("resource", "Resource", "If the parameter is a whole resource.", 0, java.lang.Integer.MAX_VALUE, resource));
        }

      public ParametersParameterComponent copy() {
        ParametersParameterComponent dst = new ParametersParameterComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.value = value == null ? null : value.copy();
        dst.resource = resource == null ? null : resource.copy();
        return dst;
      }

  }

    /**
     * A parameter passed to or received from the operation.
     */
    protected List<ParametersParameterComponent> parameter = new ArrayList<ParametersParameterComponent>();

    private static final long serialVersionUID = -648184030L;

    public Parameters() {
      super();
    }

    /**
     * @return {@link #parameter} (A parameter passed to or received from the operation.)
     */
    public List<ParametersParameterComponent> getParameter() { 
      return this.parameter;
    }

    /**
     * @return {@link #parameter} (A parameter passed to or received from the operation.)
     */
    // syntactic sugar
    public ParametersParameterComponent addParameter() { //3
      ParametersParameterComponent t = new ParametersParameterComponent();
      this.parameter.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("parameter", "", "A parameter passed to or received from the operation.", 0, java.lang.Integer.MAX_VALUE, parameter));
      }

      public Parameters copy() {
        Parameters dst = new Parameters();
        copyValues(dst);
        dst.parameter = new ArrayList<ParametersParameterComponent>();
        for (ParametersParameterComponent i : parameter)
          dst.parameter.add(i.copy());
        return dst;
      }

      protected Parameters typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Parameters;
   }


}

