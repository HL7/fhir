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

public class SelectionBehaviorEnumFactory implements EnumFactory<SelectionBehavior> {

  public SelectionBehavior fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("any".equals(codeString))
      return SelectionBehavior.ANY;
    if ("all".equals(codeString))
      return SelectionBehavior.ALL;
    if ("all-or-none".equals(codeString))
      return SelectionBehavior.ALLORNONE;
    if ("exactly-one".equals(codeString))
      return SelectionBehavior.EXACTLYONE;
    if ("at-most-one".equals(codeString))
      return SelectionBehavior.ATMOSTONE;
    if ("one-or-more".equals(codeString))
      return SelectionBehavior.ONEORMORE;
    throw new IllegalArgumentException("Unknown SelectionBehavior code '"+codeString+"'");
  }

  public String toCode(SelectionBehavior code) {
    if (code == SelectionBehavior.ANY)
      return "any";
    if (code == SelectionBehavior.ALL)
      return "all";
    if (code == SelectionBehavior.ALLORNONE)
      return "all-or-none";
    if (code == SelectionBehavior.EXACTLYONE)
      return "exactly-one";
    if (code == SelectionBehavior.ATMOSTONE)
      return "at-most-one";
    if (code == SelectionBehavior.ONEORMORE)
      return "one-or-more";
    return "?";
  }

    public String toSystem(SelectionBehavior code) {
      return code.getSystem();
      }

}

