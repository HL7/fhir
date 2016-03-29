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

public class OrderStatusEnumFactory implements EnumFactory<OrderStatus> {

  public OrderStatus fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("pending".equals(codeString))
      return OrderStatus.PENDING;
    if ("review".equals(codeString))
      return OrderStatus.REVIEW;
    if ("rejected".equals(codeString))
      return OrderStatus.REJECTED;
    if ("error".equals(codeString))
      return OrderStatus.ERROR;
    if ("accepted".equals(codeString))
      return OrderStatus.ACCEPTED;
    if ("cancelled".equals(codeString))
      return OrderStatus.CANCELLED;
    if ("replaced".equals(codeString))
      return OrderStatus.REPLACED;
    if ("aborted".equals(codeString))
      return OrderStatus.ABORTED;
    if ("completed".equals(codeString))
      return OrderStatus.COMPLETED;
    throw new IllegalArgumentException("Unknown OrderStatus code '"+codeString+"'");
  }

  public String toCode(OrderStatus code) {
    if (code == OrderStatus.PENDING)
      return "pending";
    if (code == OrderStatus.REVIEW)
      return "review";
    if (code == OrderStatus.REJECTED)
      return "rejected";
    if (code == OrderStatus.ERROR)
      return "error";
    if (code == OrderStatus.ACCEPTED)
      return "accepted";
    if (code == OrderStatus.CANCELLED)
      return "cancelled";
    if (code == OrderStatus.REPLACED)
      return "replaced";
    if (code == OrderStatus.ABORTED)
      return "aborted";
    if (code == OrderStatus.COMPLETED)
      return "completed";
    return "?";
  }

    public String toSystem(OrderStatus code) {
      return code.getSystem();
      }

}

