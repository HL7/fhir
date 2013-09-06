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

// Generated on Fri, Sep 6, 2013 22:32+1000 for FHIR v0.11

import java.util.*;

/**
 * A code taken from a short list of codes that are not defined in a formal code system.
 */
public class Choice extends Type {

    public class ChoiceOptionComponent extends Element {
        /**
         * A possible code or value that the user could have chosen.
         */
        protected Code code;

        /**
         * A set of words associated with the code to give it meaning and displayed to the user.
         */
        protected String_ display;

        public Code getCode() { 
          return this.code;
        }

        public void setCode(Code value) { 
          this.code = value;
        }

        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        public void setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
        }

        public String_ getDisplay() { 
          return this.display;
        }

        public void setDisplay(String_ value) { 
          this.display = value;
        }

        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        public void setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new String_();
            this.display.setValue(value);
          }
        }

      public ChoiceOptionComponent copy(Choice e) {
        ChoiceOptionComponent dst = e.new ChoiceOptionComponent();
        dst.code = code == null ? null : code.copy();
        dst.display = display == null ? null : display.copy();
        return dst;
      }

  }

    /**
     * The code or value that the user selected from the list of possible codes.
     */
    protected Code code;

    /**
     * A list of possible values for the code.
     */
    protected List<ChoiceOptionComponent> option = new ArrayList<ChoiceOptionComponent>();

    /**
     * Whether the order of the values has an assigned meaning.
     */
    protected Boolean isOrdered;

    public Code getCode() { 
      return this.code;
    }

    public void setCode(Code value) { 
      this.code = value;
    }

    public String getCodeSimple() { 
      return this.code == null ? null : this.code.getValue();
    }

    public void setCodeSimple(String value) { 
      if (value == null)
        this.code = null;
      else {
        if (this.code == null)
          this.code = new Code();
        this.code.setValue(value);
      }
    }

    public List<ChoiceOptionComponent> getOption() { 
      return this.option;
    }

    public Boolean getIsOrdered() { 
      return this.isOrdered;
    }

    public void setIsOrdered(Boolean value) { 
      this.isOrdered = value;
    }

    public boolean getIsOrderedSimple() { 
      return this.isOrdered == null ? null : this.isOrdered.getValue();
    }

    public void setIsOrderedSimple(boolean value) { 
      if (value == false)
        this.isOrdered = null;
      else {
        if (this.isOrdered == null)
          this.isOrdered = new Boolean();
        this.isOrdered.setValue(value);
      }
    }

      public Choice copy() {
        Choice dst = new Choice();
        dst.code = code == null ? null : code.copy();
        dst.option = new ArrayList<ChoiceOptionComponent>();
        for (ChoiceOptionComponent i : option)
          dst.option.add(i.copy(dst));
        dst.isOrdered = isOrdered == null ? null : isOrdered.copy();
        return dst;
      }

      protected Choice typedCopy() {
        return copy();
      }


}

