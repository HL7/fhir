package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2013, HL7, Inc.
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

// Generated on Sat, Jan 19, 2013 16:16+1100 for FHIR v0.07

/**
 * An identifier that humans use. This is different from a system identifier because identifiers that humans use are regularly changed or retired due to human intervention and error. Note that a human identifier may be a system identifier on some master system but becomes a human identifier elsewhere due to how it is exchanged between humans. Driver's license numbers are a good example of this. Also, because human mediated identifiers are often invoked as implicit links to external business processes, such identifiers are often associated with multiple different resources. 
 */
public class HumanId extends Type {

    public enum IdentifierUse {
        usual, // the identifier recommended for display and use in real-world interactions
        official, // the identifier considered to be most trusted for the identification of this item
        temp; // A temporary identifier
        public static IdentifierUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("usual".equals(codeString))
          return usual;
        if ("official".equals(codeString))
          return official;
        if ("temp".equals(codeString))
          return temp;
        throw new Exception("Unknown IdentifierUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case usual: return "usual";
            case official: return "official";
            case temp: return "temp";
            default: return "?";
          }
        }
    }

    /**
     * Identifies the use for this identifier, if known

     */
    private IdentifierUse use;

    /**
     * A label for the identifier that can be displayed to a human so they can recognise the identifier
     */
    private String_ label;

    /**
     * The identifier itself
     */
    private Identifier identifier;

    /**
     * Time period during which identifier was valid for use
     */
    private Period period;

    /**
     * Organisation that issued/manages the identifier
     */
    private ResourceReference assigner;

    public IdentifierUse getUse() { 
      return this.use;
    }

    public void setUse(IdentifierUse value) { 
      this.use = value;
    }

    public String_ getLabel() { 
      return this.label;
    }

    public void setLabel(String_ value) { 
      this.label = value;
    }

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

    public ResourceReference getAssigner() { 
      return this.assigner;
    }

    public void setAssigner(ResourceReference value) { 
      this.assigner = value;
    }


}

