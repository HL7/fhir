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

// Generated on Fri, Jun 7, 2013 00:21+1000 for FHIR v0.09

import java.util.*;

/**
 * A description of an individual who is involved in healthcare processes. The individual may be a patient, a practitioner, or related to a patient in some way or other.
 */
public class Demographics extends Type {

    public class DemographicsLanguageComponent extends Element {
        /**
         * The ISO-639-1 alpha 2 code in lower case for the language, optionally followed by a hyphen and the ISO-3166-1 alpha 2 code for the region in upper case. E.g. "en" for English, or "en-US" for American English versus "en-EN" for England English
         */
        private CodeableConcept language;

        /**
         * A value representing the person's method of expression of this language. Examples: expressed spoken, expressed written, expressed signed, received spoken, received written, received signed
         */
        private CodeableConcept mode;

        /**
         * A code that describes how well the language is spoken
         */
        private CodeableConcept proficiencyLevel;

        /**
         * Indicates whether or not the Person prefers this language (over other languages he masters up a certain level)
         */
        private Boolean preference;

        public CodeableConcept getLanguage() { 
          return this.language;
        }

        public void setLanguage(CodeableConcept value) { 
          this.language = value;
        }

        public CodeableConcept getMode() { 
          return this.mode;
        }

        public void setMode(CodeableConcept value) { 
          this.mode = value;
        }

        public CodeableConcept getProficiencyLevel() { 
          return this.proficiencyLevel;
        }

        public void setProficiencyLevel(CodeableConcept value) { 
          this.proficiencyLevel = value;
        }

        public Boolean getPreference() { 
          return this.preference;
        }

        public void setPreference(Boolean value) { 
          this.preference = value;
        }

        public boolean getPreferenceSimple() { 
          return this.preference == null ? null : this.preference.getValue();
        }

        public void setPreferenceSimple(boolean value) { 
          if (value == false)
            this.preference = null;
          else {
            if (this.preference == null)
              this.preference = new Boolean();
            this.preference.setValue(value);
          }
        }

  }

    /**
     * Identifier for a natural person, in contrast to the identifier in a specific context.
     */
    private List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * A name associated with the individual.
     */
    private List<HumanName> name = new ArrayList<HumanName>();

    /**
     * A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.
     */
    private List<Contact> telecom = new ArrayList<Contact>();

    /**
     * Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.
     */
    private Coding gender;

    /**
     * The date and time of birth for the individual
     */
    private DateTime birthDate;

    /**
     * Indicates if the individual is deceased or not
     */
    private Boolean deceased;

    /**
     * Addresses for the individual
     */
    private List<Address> address = new ArrayList<Address>();

    /**
     * Image of the person
     */
    private List<ResourceReference> photo = new ArrayList<ResourceReference>();

    /**
     * This field contains a patient's most recent marital (civil) status.
     */
    private CodeableConcept maritalStatus;

    /**
     * A language spoken by the person, with proficiency
     */
    private List<DemographicsLanguageComponent> language = new ArrayList<DemographicsLanguageComponent>();

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    public List<HumanName> getName() { 
      return this.name;
    }

    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    public Coding getGender() { 
      return this.gender;
    }

    public void setGender(Coding value) { 
      this.gender = value;
    }

    public DateTime getBirthDate() { 
      return this.birthDate;
    }

    public void setBirthDate(DateTime value) { 
      this.birthDate = value;
    }

    public String getBirthDateSimple() { 
      return this.birthDate == null ? null : this.birthDate.getValue();
    }

    public void setBirthDateSimple(String value) { 
      if (value == null)
        this.birthDate = null;
      else {
        if (this.birthDate == null)
          this.birthDate = new DateTime();
        this.birthDate.setValue(value);
      }
    }

    public Boolean getDeceased() { 
      return this.deceased;
    }

    public void setDeceased(Boolean value) { 
      this.deceased = value;
    }

    public boolean getDeceasedSimple() { 
      return this.deceased == null ? null : this.deceased.getValue();
    }

    public void setDeceasedSimple(boolean value) { 
      if (value == false)
        this.deceased = null;
      else {
        if (this.deceased == null)
          this.deceased = new Boolean();
        this.deceased.setValue(value);
      }
    }

    public List<Address> getAddress() { 
      return this.address;
    }

    public List<ResourceReference> getPhoto() { 
      return this.photo;
    }

    public CodeableConcept getMaritalStatus() { 
      return this.maritalStatus;
    }

    public void setMaritalStatus(CodeableConcept value) { 
      this.maritalStatus = value;
    }

    public List<DemographicsLanguageComponent> getLanguage() { 
      return this.language;
    }


}

