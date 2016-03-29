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

public class DeviceUseRequestStatusEnumFactory implements EnumFactory<DeviceUseRequestStatus> {

  public DeviceUseRequestStatus fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("proposed".equals(codeString))
      return DeviceUseRequestStatus.PROPOSED;
    if ("planned".equals(codeString))
      return DeviceUseRequestStatus.PLANNED;
    if ("requested".equals(codeString))
      return DeviceUseRequestStatus.REQUESTED;
    if ("received".equals(codeString))
      return DeviceUseRequestStatus.RECEIVED;
    if ("accepted".equals(codeString))
      return DeviceUseRequestStatus.ACCEPTED;
    if ("in-progress".equals(codeString))
      return DeviceUseRequestStatus.INPROGRESS;
    if ("completed".equals(codeString))
      return DeviceUseRequestStatus.COMPLETED;
    if ("suspended".equals(codeString))
      return DeviceUseRequestStatus.SUSPENDED;
    if ("rejected".equals(codeString))
      return DeviceUseRequestStatus.REJECTED;
    if ("aborted".equals(codeString))
      return DeviceUseRequestStatus.ABORTED;
    throw new IllegalArgumentException("Unknown DeviceUseRequestStatus code '"+codeString+"'");
  }

  public String toCode(DeviceUseRequestStatus code) {
    if (code == DeviceUseRequestStatus.PROPOSED)
      return "proposed";
    if (code == DeviceUseRequestStatus.PLANNED)
      return "planned";
    if (code == DeviceUseRequestStatus.REQUESTED)
      return "requested";
    if (code == DeviceUseRequestStatus.RECEIVED)
      return "received";
    if (code == DeviceUseRequestStatus.ACCEPTED)
      return "accepted";
    if (code == DeviceUseRequestStatus.INPROGRESS)
      return "in-progress";
    if (code == DeviceUseRequestStatus.COMPLETED)
      return "completed";
    if (code == DeviceUseRequestStatus.SUSPENDED)
      return "suspended";
    if (code == DeviceUseRequestStatus.REJECTED)
      return "rejected";
    if (code == DeviceUseRequestStatus.ABORTED)
      return "aborted";
    return "?";
  }

    public String toSystem(DeviceUseRequestStatus code) {
      return code.getSystem();
      }

}

