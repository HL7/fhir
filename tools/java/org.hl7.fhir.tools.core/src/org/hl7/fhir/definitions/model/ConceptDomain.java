package org.hl7.fhir.definitions.model;
/*
Copyright (c) 2011+, HL7, Inc
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
import java.util.ArrayList;
import java.util.List;

/**
 * A concept domain - a use of terminology in FHIR.
 * A concept domain has a name, a definition, and information about what is in it (bindingType and binding/details, and maybe a list of defined codes) 
 * 
 * Technically, a concept domain is a facade on the full model in the core principles
 * 
 * @author Grahame
 *
 */
public class ConceptDomain {
  
  public enum Binding {
    Unbound,
    CodeList, 
    ValueSet,
    Reference,
    Special
  }
  
  public enum BindingStrength {
    Unstated,
    Required,
    Preferred,
    Suggested
  }

  
  private String id;
  private String name;
	private String definition;
	private Binding binding;
  private BindingStrength bindingStrength;
	private String reference;
	private String description;
	private String source; // for useful error messages during build
	
  private List<DefinedCode> codes = new ArrayList<DefinedCode>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDefinition() {
    return definition;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public Binding getBinding() {
    return binding;
  }

  public void setBinding(Binding binding) {
    this.binding = binding;
  }

  public BindingStrength getBindingStrength() {
    return bindingStrength;
  }

  public void setBindingStrength(BindingStrength bindingStrength) {
    this.bindingStrength = bindingStrength;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<DefinedCode> getCodes() {
    return codes;
  }
	
	public boolean hasReference() {
	  return !(reference == null || reference.equals(""));
	}

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

}
