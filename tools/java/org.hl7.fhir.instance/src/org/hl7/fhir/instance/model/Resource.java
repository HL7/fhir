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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A resource that is defined in the FHIR specification
 * 
 */
public abstract class Resource extends BackboneElement {

	/**
	 * @return the type of resource that this is (e.g. for switch statements)
	 */
  public abstract ResourceType getResourceType();

  /**
	 * Text summary of resource, for human interpretation
	 */
	private Narrative text;

  /**
   * The primary/base human language of the content. The value can be any valid value for xml:lang
   */
  private Code language;

  /**
   * Any resources contained in this one (see speification for further details)
   */
  private List<Resource> contained = new ArrayList<Resource>();
	
  /**
   * @return Text summary of resource, for human interpretation
   */
	public Narrative getText() {
		return text;
	}

	/**
	 * @param text Text summary of resource, for human interpretation
	 */
	public void setText(Narrative text) {
		this.text = text;
	}

	/**
	 * @return The primary/base human language of the content. The value can be any valid value for xml:lang
	 */
  public Code getLanguage() { 
    return this.language;
  }

  /**
   * @param value The primary/base human language of the content. The value can be any valid value for xml:lang
   */
  public void setLanguage(Code value) { 
    this.language = value;
  }

	/**
	 * @return The primary/base human language of the content. The value can be any valid value for xml:lang
	 */
  public String getLanguageSimple() { 
    return this.language == null ? null : this.language.getValue();

  }

  /**
   * @param value The primary/base human language of the content. The value can be any valid value for xml:lang
   */
  public void setLanguageSimple(String value) { 
    if (value == null)
      this.language = null;
    else {
      if (this.language == null)
        this.language = new Code();
      this.language.setValue(value);
    }
  }
/**
 * @return Any resources contained in this one (see speification for further details)
 */
  public List<Resource> getContained() {
    return contained;
  }
  
}
