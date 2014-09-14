package org.hl7.fhir.definitions.parsers;
import java.io.File;

import org.hl7.fhir.utilities.IniFile;
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

public class BindingNameRegistry {

  private boolean forPublication;
  private IniFile ini;

  public BindingNameRegistry(String srcDir, boolean forPublication) {
    this.forPublication = forPublication;
    ini = new IniFile(srcDir+File.separator+"bindings.ini");
  }

  public String idForName(String name) {
    return idForNameInternal("Binding Names", "Last", name);
  }

  public void commit() {
    if (forPublication) {
      ini.save();
    }
  }

  public String idForQName(String q, String name) {
    return idForNameInternal(q, q, name);
  }
  
  public String idForNameInternal(String q, String k, String name) {
    if (ini.getIntegerProperty(q, name) != null)
      return ini.getIntegerProperty(q, name).toString();
    else if (!forPublication)
      return "0";
    else {
      Integer last;
      if (ini.getIntegerProperty("Key", k) != null)
        last = ini.getIntegerProperty("Key", k)+1;
      else 
        last = 1;
      ini.setIntegerProperty("Key", k, last, null);
      ini.setIntegerProperty(q, name, last, null);
      return last.toString();
    }
  }
}
