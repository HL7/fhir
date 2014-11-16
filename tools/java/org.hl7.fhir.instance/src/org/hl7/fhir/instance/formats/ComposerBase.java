package org.hl7.fhir.instance.formats;

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

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.Resource.ResourceMetaComponent;

public abstract class ComposerBase extends FormatUtilities implements Composer {

  protected String xhtmlMessage;
  
  @Override
  public void setSuppressXhtml(String message) {
    xhtmlMessage = message;    
  }

  public String composeString(Resource resource, boolean pretty) throws Exception {
    return new String(composeBytes(resource, pretty));
  }

  public byte[] composeBytes(Resource resource, boolean pretty) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    compose(bytes, resource, true);
    return bytes.toByteArray();
  }

  public String composeString(ResourceMetaComponent meta, boolean pretty) throws Exception {
    return new String(composeBytes(meta, pretty));
  }

  public byte[] composeBytes(ResourceMetaComponent meta, boolean pretty) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    compose(bytes, meta, true);
    return bytes.toByteArray();
  }

  public String composeString(Type type, boolean pretty) throws Exception {
    return new String(composeBytes(type, pretty));
  }

  public byte[] composeBytes(Type type, boolean pretty) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    compose(bytes, type, true);
    return bytes.toByteArray();
  }


}
