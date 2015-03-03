package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.utilities.Utilities;

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
public class DefinedCode {

  private String id;
	private String code;
	private String definition;
	private String comment;
	private String display;
	private String system;
	private String parent;
	private List<DefinedCode> childCodes;
  private String v2Map;
  private String v3Map;
	private Map<String, String> langs = new HashMap<String, String>();
  private StructureDefinition profile;
	
	public DefinedCode(String code, String definition, String comment) {
		super();
		this.code = code;
		this.definition = definition;
		this.comment = comment;
	}
	
	public DefinedCode() {
		super();
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public boolean hasDefinition() {
		return definition != null && !definition.equals("");
	}
	public boolean hasComment() {
		return comment != null && !comment.equals("");
	}

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public String getSystem() {
    return system;
  }

  public void setSystem(String system) {
    this.system = system;
  }
	
  public boolean hasSystem() {
    return system != null && !system.equals("");
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getParent() {
    return parent;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }

  public boolean hasParent() {
    return !Utilities.noString(parent);
  }

  public List<DefinedCode> getChildCodes() {
    if (childCodes == null)
      childCodes = new ArrayList<DefinedCode>();
    return childCodes;
  }

  public String getV2Map() {
    return v2Map;
  }

  public void setV2Map(String v2Map) {
    this.v2Map = v2Map;
  }

  public String getV3Map() {
    return v3Map;
  }

  public void setV3Map(String v3Map) {
    this.v3Map = v3Map;
  }

  public Map<String, String> getLangs() {
    return langs;
  }


  public StructureDefinition getProfile() {
    return profile;
  }

  public void setProfile(StructureDefinition profile) {
    this.profile = profile;
  }

  public boolean hasCode() {
    return !Utilities.noString(code);
  }

  public boolean hasDisplay() {
    return !Utilities.noString(display);
  }
  
  
  
}
