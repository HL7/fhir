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

public class QicoreCommunicationMediumEnumFactory implements EnumFactory<QicoreCommunicationMedium> {

  public QicoreCommunicationMedium fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("unspecified".equals(codeString))
      return QicoreCommunicationMedium.UNSPECIFIED;
    if ("telephone".equals(codeString))
      return QicoreCommunicationMedium.TELEPHONE;
    if ("fax".equals(codeString))
      return QicoreCommunicationMedium.FAX;
    if ("device".equals(codeString))
      return QicoreCommunicationMedium.DEVICE;
    if ("video".equals(codeString))
      return QicoreCommunicationMedium.VIDEO;
    if ("voicemail".equals(codeString))
      return QicoreCommunicationMedium.VOICEMAIL;
    if ("text".equals(codeString))
      return QicoreCommunicationMedium.TEXT;
    if ("social-media".equals(codeString))
      return QicoreCommunicationMedium.SOCIALMEDIA;
    if ("in-person".equals(codeString))
      return QicoreCommunicationMedium.INPERSON;
    if ("mail".equals(codeString))
      return QicoreCommunicationMedium.MAIL;
    if ("email".equals(codeString))
      return QicoreCommunicationMedium.EMAIL;
    if ("portal".equals(codeString))
      return QicoreCommunicationMedium.PORTAL;
    throw new IllegalArgumentException("Unknown QicoreCommunicationMedium code '"+codeString+"'");
  }

  public String toCode(QicoreCommunicationMedium code) {
    if (code == QicoreCommunicationMedium.UNSPECIFIED)
      return "unspecified";
    if (code == QicoreCommunicationMedium.TELEPHONE)
      return "telephone";
    if (code == QicoreCommunicationMedium.FAX)
      return "fax";
    if (code == QicoreCommunicationMedium.DEVICE)
      return "device";
    if (code == QicoreCommunicationMedium.VIDEO)
      return "video";
    if (code == QicoreCommunicationMedium.VOICEMAIL)
      return "voicemail";
    if (code == QicoreCommunicationMedium.TEXT)
      return "text";
    if (code == QicoreCommunicationMedium.SOCIALMEDIA)
      return "social-media";
    if (code == QicoreCommunicationMedium.INPERSON)
      return "in-person";
    if (code == QicoreCommunicationMedium.MAIL)
      return "mail";
    if (code == QicoreCommunicationMedium.EMAIL)
      return "email";
    if (code == QicoreCommunicationMedium.PORTAL)
      return "portal";
    return "?";
  }

    public String toSystem(QicoreCommunicationMedium code) {
      return code.getSystem();
      }

}

