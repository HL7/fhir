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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11

/**
 * All kinds of technology mediated contact details for a person or organisation, including telephone, email, etc.
 */
public class Contact extends Type {

    public enum ContactSystem {
        phone, // The value is a telephone number used for voice calls. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        fax, // The value is a fax machine. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        email, // The value is an email address.
        url, // The value is a url. This is intended for various personal contacts including blogs, Twitter, Facebook, etc. Do not use for email addresses.
        Null; // added to help the parsers
        public static ContactSystem fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("phone".equals(codeString))
          return phone;
        if ("fax".equals(codeString))
          return fax;
        if ("email".equals(codeString))
          return email;
        if ("url".equals(codeString))
          return url;
        throw new Exception("Unknown ContactSystem code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case phone: return "phone";
            case fax: return "fax";
            case email: return "email";
            case url: return "url";
            default: return "?";
          }
        }
    }

  public class ContactSystemEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("phone".equals(codeString))
          return ContactSystem.phone;
        if ("fax".equals(codeString))
          return ContactSystem.fax;
        if ("email".equals(codeString))
          return ContactSystem.email;
        if ("url".equals(codeString))
          return ContactSystem.url;
        throw new Exception("Unknown ContactSystem code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ContactSystem.phone)
        return "phone";
      if (code == ContactSystem.fax)
        return "fax";
      if (code == ContactSystem.email)
        return "email";
      if (code == ContactSystem.url)
        return "url";
      return "?";
      }
    }

    public enum ContactUse {
        home, // A communication contact at a home; attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call. Typically used with urgent cases, or if no other contacts are available.
        work, // An office contact. First choice for business related contacts during business hours.
        temp, // A temporary contact. The period can provide more detailed information.
        old, // This contact is no longer in use (or was never correct, but retained for records).
        mobile, // A telecommunication device that moves and stays with its owner. May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business.
        Null; // added to help the parsers
        public static ContactUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("home".equals(codeString))
          return home;
        if ("work".equals(codeString))
          return work;
        if ("temp".equals(codeString))
          return temp;
        if ("old".equals(codeString))
          return old;
        if ("mobile".equals(codeString))
          return mobile;
        throw new Exception("Unknown ContactUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case home: return "home";
            case work: return "work";
            case temp: return "temp";
            case old: return "old";
            case mobile: return "mobile";
            default: return "?";
          }
        }
    }

  public class ContactUseEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("home".equals(codeString))
          return ContactUse.home;
        if ("work".equals(codeString))
          return ContactUse.work;
        if ("temp".equals(codeString))
          return ContactUse.temp;
        if ("old".equals(codeString))
          return ContactUse.old;
        if ("mobile".equals(codeString))
          return ContactUse.mobile;
        throw new Exception("Unknown ContactUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ContactUse.home)
        return "home";
      if (code == ContactUse.work)
        return "work";
      if (code == ContactUse.temp)
        return "temp";
      if (code == ContactUse.old)
        return "old";
      if (code == ContactUse.mobile)
        return "mobile";
      return "?";
      }
    }

    /**
     * Telecommunications form for contact - what communications system is required to make use of the contact.
     */
    protected Enumeration<ContactSystem> system;

    /**
     * The actual contact details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).
     */
    protected String_ value;

    /**
     * Identifies the context for the address.
     */
    protected Enumeration<ContactUse> use;

    /**
     * Time period when the contact was/is in use.
     */
    protected Period period;

    public Enumeration<ContactSystem> getSystem() { 
      return this.system;
    }

    public void setSystem(Enumeration<ContactSystem> value) { 
      this.system = value;
    }

    public ContactSystem getSystemSimple() { 
      return this.system == null ? null : this.system.getValue();
    }

    public void setSystemSimple(ContactSystem value) { 
      if (value == null)
        this.system = null;
      else {
        if (this.system == null)
          this.system = new Enumeration<ContactSystem>();
        this.system.setValue(value);
      }
    }

    public String_ getValue() { 
      return this.value;
    }

    public void setValue(String_ value) { 
      this.value = value;
    }

    public String getValueSimple() { 
      return this.value == null ? null : this.value.getValue();
    }

    public void setValueSimple(String value) { 
      if (value == null)
        this.value = null;
      else {
        if (this.value == null)
          this.value = new String_();
        this.value.setValue(value);
      }
    }

    public Enumeration<ContactUse> getUse() { 
      return this.use;
    }

    public void setUse(Enumeration<ContactUse> value) { 
      this.use = value;
    }

    public ContactUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    public void setUseSimple(ContactUse value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<ContactUse>();
        this.use.setValue(value);
      }
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

      public Contact copy() {
        Contact dst = new Contact();
        dst.system = system == null ? null : system.copy();
        dst.value = value == null ? null : value.copy();
        dst.use = use == null ? null : use.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

      protected Contact typedCopy() {
        return copy();
      }


}

