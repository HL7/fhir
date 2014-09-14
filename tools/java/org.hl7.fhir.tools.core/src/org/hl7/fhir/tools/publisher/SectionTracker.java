package org.hl7.fhir.tools.publisher;


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

public class SectionTracker {
  String workingPrefix;
  String prefix;
 
  int[] count = {0, 0, 0, 0, 0, 0, 0}; // the first value is never used, but it makes the indexes effectively 1 based 
  int level = 0;
  int offset = -1;
  
  public SectionTracker(String prefix) {
    super();
    this.prefix = prefix;
  }
  
  public String getIndex(int h) throws Exception {
    if (h < 1 || h > 6)
      throw new Exception("invalid value for html header level");

    if (offset == -1) {
      offset = h;
      level = offset;
      count[level]++;
      return workingPrefix;
    }
    while (h < level) {
      count[level] = 0;
      level--;  
      if (level < offset)
        throw new Exception("logical error in html structure - inner heading has lower index than initial heading");
    }
    while (h > level) {
      level++;
      count[level] = 0;
    }
    count[level]++;
    String n = "";
    for (int i = offset+1; i <= level; i++) 
      n = n + "."+ Integer.toString(count[i]); 
    return workingPrefix + n;
  }

  public void start(String id) {
    if (prefix != null && prefix.endsWith(".X")) {
      workingPrefix = prefix.substring(0, prefix.length()-2)+"."+id;
    } else 
      workingPrefix = prefix;
    
  }
  
}
