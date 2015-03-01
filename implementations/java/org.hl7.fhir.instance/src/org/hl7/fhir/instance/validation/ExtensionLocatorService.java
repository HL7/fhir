package org.hl7.fhir.instance.validation;

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

import org.hl7.fhir.instance.model.StructureDefinition;



/**
 * This interface is used to provide extension location services for the validator
 * 
 * when it encounters an extension, it asks this server to locate it, or tell it 
 * whether to ignore the extension, or mark it as invalid
 * 
 * @author Grahame
 *
 */
public interface ExtensionLocatorService {

  public enum Status {
    Located, NotAllowed, Unknown
  }

  public class ExtensionLocationResponse {
    private Status status;
    private StructureDefinition definition;
		private String message;
		private String url;

    public ExtensionLocationResponse(String url, Status status, StructureDefinition definition, String message) {
      super();
      this.url = url;
      this.status = status;
      this.definition = definition;
      this.message = message;
    }

    public Status getStatus() {
      return status;
    }

    public StructureDefinition getDefinition() {
      return definition;
    }

		public String getMessage() {
	    return message;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    /**
     * This routine is used when walking into a complex extension. 
     * the non-tail part of the relative URL matches the end of the
     * exiting URL 
     * @param url - the relative URL
     * @return
     * @throws Exception 
     */
    public ExtensionLocationResponse clone(String url) throws Exception {
      if (!this.url.endsWith(url.substring(0, url.lastIndexOf("."))))
        throw new Exception("the relative URL "+url+" cannot be used in the context "+this.url);
      
      return new ExtensionLocationResponse(this.url+"."+url.substring(url.lastIndexOf(".")+1), status, definition, message);
    }
    
  }

  public ExtensionLocationResponse locateExtension(String uri);
}
