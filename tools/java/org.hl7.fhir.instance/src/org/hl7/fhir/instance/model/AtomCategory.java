package org.hl7.fhir.instance.model;

/*
Copyright (c) 2011-2013, HL7, Inc
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

/**
 * A category in an Atom Entry or Feed
 * 
 * Used in the FHIR specification to carry tags
 */
public class AtomCategory {

	/**
	 * The scheme. If this has a value of http://hl7.org/fhir/tag or http://hl7.org/fhir/tag/profile, it is a FHIR tag. Otherwise it's something else
	 */
  private String scheme;
  
  /**
   * the value of the tag or category
   */
  private String term;
  
  /**
   * human readable description of the category
   */
  private String label;
  
  
	public AtomCategory(String scheme, String term, String label) {
	  super();
	  this.scheme = scheme;
	  this.term = term;
	  this.label = label;
  }
	
	/**
	 * @return The scheme. If this has a value of http://hl7.org/fhir/tag or http://hl7.org/fhir/tag/profile, it is a FHIR tag. Otherwise it's something else
	 */
	public String getScheme() {
		return scheme;
	}
	/**
	 * @param scheme The scheme. If this has a value of http://hl7.org/fhir/tag or http://hl7.org/fhir/tag/profile, it is a FHIR tag. Otherwise it's something else
	 */
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	/**
	 * @return the value of the tag or category
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term the value of the tag or category
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	
	/**
	 * @return human readable description of the category
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label human readable description of the category
	 */
	public void setLabel(String label) {
		this.label = label;
	}
  
}
