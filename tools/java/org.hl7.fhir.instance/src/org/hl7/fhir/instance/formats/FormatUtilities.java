package org.hl7.fhir.instance.formats;

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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.DateAndTime;

public abstract class FormatUtilities {
  protected static final String FHIR_NS = "http://hl7.org/fhir";
  protected static final String ATOM_NS = "http://www.w3.org/2005/Atom";
  protected static final String GDATA_NS = "http://schemas.google.com/g/2005";
 
  protected String toString(String value) {
    return value;
  }
  
  protected String toString(int value) {
    return java.lang.Integer.toString(value);
  }
  
  protected String toString(boolean value) {
    return java.lang.Boolean.toString(value);
  }
  
  protected String toString(BigDecimal value) {
    return value.toString();
  }
  
  protected String toString(URI value) {
    return value.toString();
  }

  public static String toString(byte[] value) {
    byte[] encodeBase64 = Base64.encodeBase64(value);
    return new String(encodeBase64);
  }
  
  protected String toString(DateAndTime value) {
    return value.toString();
  }

	public static boolean isValidId(String tail) {
	  return tail.matches("[A-Za-z0-9\\-\\.]{1,36}");
  }
  



}
