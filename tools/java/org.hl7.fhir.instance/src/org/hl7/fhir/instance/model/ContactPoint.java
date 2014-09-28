package org.hl7.fhir.instance.model;

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

// Generated on Sun, Sep 28, 2014 20:18+1000 for FHIR v0.3.0

import java.util.*;

/**
 * Details for All kinds of technology mediated contact points for a person or organization, including telephone, email, etc.
 */
public class ContactPoint extends Type {

    public enum ContactPointSystem {
        phone, // The value is a telephone number used for voice calls. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        fax, // The value is a fax machine. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        email, // The value is an email address.
        url, // The value is a url. This is intended for various personal contacts including blogs, Twitter, Facebook, etc. Do not use for email addresses.
        Null; // added to help the parsers
        public static ContactPointSystem fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown ContactPointSystem code '"+codeString+"'");
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

  public static class ContactPointSystemEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("phone".equals(codeString))
          return ContactPointSystem.phone;
        if ("fax".equals(codeString))
          return ContactPointSystem.fax;
        if ("email".equals(codeString))
          return ContactPointSystem.email;
        if ("url".equals(codeString))
          return ContactPointSystem.url;
        throw new Exception("Unknown ContactPointSystem code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ContactPointSystem.phone)
        return "phone";
      if (code == ContactPointSystem.fax)
        return "fax";
      if (code == ContactPointSystem.email)
        return "email";
      if (code == ContactPointSystem.url)
        return "url";
      return "?";
      }
    }

    public enum ContactPointUse {
        home, // A communication contact point at a home; attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call. Typically used with urgent cases, or if no other contacts are available.
        work, // An office contact point. First choice for business related contacts during business hours.
        temp, // A temporary contact point. The period can provide more detailed information.
        old, // This contact point is no longer in use (or was never correct, but retained for records).
        mobile, // A telecommunication device that moves and stays with its owner. May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business.
        Null; // added to help the parsers
        public static ContactPointUse fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown ContactPointUse code '"+codeString+"'");
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

  public static class ContactPointUseEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("home".equals(codeString))
          return ContactPointUse.home;
        if ("work".equals(codeString))
          return ContactPointUse.work;
        if ("temp".equals(codeString))
          return ContactPointUse.temp;
        if ("old".equals(codeString))
          return ContactPointUse.old;
        if ("mobile".equals(codeString))
          return ContactPointUse.mobile;
        throw new Exception("Unknown ContactPointUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ContactPointUse.home)
        return "home";
      if (code == ContactPointUse.work)
        return "work";
      if (code == ContactPointUse.temp)
        return "temp";
      if (code == ContactPointUse.old)
        return "old";
      if (code == ContactPointUse.mobile)
        return "mobile";
      return "?";
      }
    }

    /**
     * Telecommunications form for contact point - what communications system is required to make use of the contact.
     */
    protected Enumeration<ContactPointSystem> system;

    /**
     * The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).
     */
    protected StringType value;

    /**
     * Identifies the purpose for the contact point.
     */
    protected Enumeration<ContactPointUse> use;

    /**
     * Time period when the contact point was/is in use.
     */
    protected Period period;

    private static final long serialVersionUID = 1972725348L;

    public ContactPoint() {
      super();
    }

    /**
     * @return {@link #system} (Telecommunications form for contact point - what communications system is required to make use of the contact.)
     */
    public Enumeration<ContactPointSystem> getSystem() { 
      return this.system;
    }

    /**
     * @param value {@link #system} (Telecommunications form for contact point - what communications system is required to make use of the contact.)
     */
    public ContactPoint setSystem(Enumeration<ContactPointSystem> value) { 
      this.system = value;
      return this;
    }

    /**
     * @return Telecommunications form for contact point - what communications system is required to make use of the contact.
     */
    public ContactPointSystem getSystemSimple() { 
      return this.system == null ? null : this.system.getValue();
    }

    /**
     * @param value Telecommunications form for contact point - what communications system is required to make use of the contact.
     */
    public ContactPoint setSystemSimple(ContactPointSystem value) { 
      if (value == null)
        this.system = null;
      else {
        if (this.system == null)
          this.system = new Enumeration<ContactPointSystem>();
        this.system.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #value} (The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).)
     */
    public StringType getValue() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).)
     */
    public ContactPoint setValue(StringType value) { 
      this.value = value;
      return this;
    }

    /**
     * @return The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).
     */
    public String getValueSimple() { 
      return this.value == null ? null : this.value.getValue();
    }

    /**
     * @param value The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).
     */
    public ContactPoint setValueSimple(String value) { 
      if (value == null)
        this.value = null;
      else {
        if (this.value == null)
          this.value = new StringType();
        this.value.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #use} (Identifies the purpose for the contact point.)
     */
    public Enumeration<ContactPointUse> getUse() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (Identifies the purpose for the contact point.)
     */
    public ContactPoint setUse(Enumeration<ContactPointUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return Identifies the purpose for the contact point.
     */
    public ContactPointUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value Identifies the purpose for the contact point.
     */
    public ContactPoint setUseSimple(ContactPointUse value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<ContactPointUse>();
        this.use.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #period} (Time period when the contact point was/is in use.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (Time period when the contact point was/is in use.)
     */
    public ContactPoint setPeriod(Period value) { 
      this.period = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("system", "code", "Telecommunications form for contact point - what communications system is required to make use of the contact.", 0, java.lang.Integer.MAX_VALUE, system));
        childrenList.add(new Property("value", "string", "The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).", 0, java.lang.Integer.MAX_VALUE, value));
        childrenList.add(new Property("use", "code", "Identifies the purpose for the contact point.", 0, java.lang.Integer.MAX_VALUE, use));
        childrenList.add(new Property("period", "Period", "Time period when the contact point was/is in use.", 0, java.lang.Integer.MAX_VALUE, period));
      }

      public ContactPoint copy() {
        ContactPoint dst = new ContactPoint();
        dst.system = system == null ? null : system.copy();
        dst.value = value == null ? null : value.copy();
        dst.use = use == null ? null : use.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

      protected ContactPoint typedCopy() {
        return copy();
      }


}

