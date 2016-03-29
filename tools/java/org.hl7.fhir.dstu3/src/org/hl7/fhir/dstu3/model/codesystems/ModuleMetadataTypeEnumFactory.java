package org.hl7.fhir.dstu3.model.codesystems;

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

// Generated on Mon, Mar 28, 2016 15:19-0600 for FHIR v1.3.0


import org.hl7.fhir.dstu3.model.EnumFactory;

public class ModuleMetadataTypeEnumFactory implements EnumFactory<ModuleMetadataType> {

  public ModuleMetadataType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("module".equals(codeString))
      return ModuleMetadataType.MODULE;
    if ("library".equals(codeString))
      return ModuleMetadataType.LIBRARY;
    if ("decision-support-rule".equals(codeString))
      return ModuleMetadataType.DECISIONSUPPORTRULE;
    if ("documentation-template".equals(codeString))
      return ModuleMetadataType.DOCUMENTATIONTEMPLATE;
    if ("order-set".equals(codeString))
      return ModuleMetadataType.ORDERSET;
    throw new IllegalArgumentException("Unknown ModuleMetadataType code '"+codeString+"'");
  }

  public String toCode(ModuleMetadataType code) {
    if (code == ModuleMetadataType.MODULE)
      return "module";
    if (code == ModuleMetadataType.LIBRARY)
      return "library";
    if (code == ModuleMetadataType.DECISIONSUPPORTRULE)
      return "decision-support-rule";
    if (code == ModuleMetadataType.DOCUMENTATIONTEMPLATE)
      return "documentation-template";
    if (code == ModuleMetadataType.ORDERSET)
      return "order-set";
    return "?";
  }

    public String toSystem(ModuleMetadataType code) {
      return code.getSystem();
      }

}

