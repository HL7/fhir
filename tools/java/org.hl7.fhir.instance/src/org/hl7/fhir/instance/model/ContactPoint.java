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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * Details for All kinds of technology mediated contact points for a person or organization, including telephone, email, etc.
 */
public class ContactPoint extends Type {

    public enum ContactPointSystem {
        PHONE, // The value is a telephone number used for voice calls. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        FAX, // The value is a fax machine. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        EMAIL, // The value is an email address.
        URL, // The value is a url. This is intended for various personal contacts including blogs, Twitter, Facebook, etc. Do not use for email addresses.
        NULL; // added to help the parsers
        public static ContactPointSystem fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("phone".equals(codeString))
          return PHONE;
        if ("fax".equals(codeString))
          return FAX;
        if ("email".equals(codeString))
          return EMAIL;
        if ("url".equals(codeString))
          return URL;
        throw new Exception("Unknown ContactPointSystem code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PHONE: return "phone";
            case FAX: return "fax";
            case EMAIL: return "email";
            case URL: return "url";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PHONE: return "The value is a telephone number used for voice calls. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.";
            case FAX: return "The value is a fax machine. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.";
            case EMAIL: return "The value is an email address.";
            case URL: return "The value is a url. This is intended for various personal contacts including blogs, Twitter, Facebook, etc. Do not use for email addresses.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PHONE: return "phone";
            case FAX: return "fax";
            case EMAIL: return "email";
            case URL: return "url";
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
          return ContactPointSystem.PHONE;
        if ("fax".equals(codeString))
          return ContactPointSystem.FAX;
        if ("email".equals(codeString))
          return ContactPointSystem.EMAIL;
        if ("url".equals(codeString))
          return ContactPointSystem.URL;
        throw new Exception("Unknown ContactPointSystem code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ContactPointSystem.PHONE)
        return "phone";
      if (code == ContactPointSystem.FAX)
        return "fax";
      if (code == ContactPointSystem.EMAIL)
        return "email";
      if (code == ContactPointSystem.URL)
        return "url";
      return "?";
      }
    }

    public enum ContactPointUse {
        HOME, // A communication contact point at a home; attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call. Typically used with urgent cases, or if no other contacts are available.
        WORK, // An office contact point. First choice for business related contacts during business hours.
        TEMP, // A temporary contact point. The period can provide more detailed information.
        OLD, // This contact point is no longer in use (or was never correct, but retained for records).
        MOBILE, // A telecommunication device that moves and stays with its owner. May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business.
        NULL; // added to help the parsers
        public static ContactPointUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("home".equals(codeString))
          return HOME;
        if ("work".equals(codeString))
          return WORK;
        if ("temp".equals(codeString))
          return TEMP;
        if ("old".equals(codeString))
          return OLD;
        if ("mobile".equals(codeString))
          return MOBILE;
        throw new Exception("Unknown ContactPointUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case HOME: return "home";
            case WORK: return "work";
            case TEMP: return "temp";
            case OLD: return "old";
            case MOBILE: return "mobile";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case HOME: return "A communication contact point at a home; attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call. Typically used with urgent cases, or if no other contacts are available.";
            case WORK: return "An office contact point. First choice for business related contacts during business hours.";
            case TEMP: return "A temporary contact point. The period can provide more detailed information.";
            case OLD: return "This contact point is no longer in use (or was never correct, but retained for records).";
            case MOBILE: return "A telecommunication device that moves and stays with its owner. May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case HOME: return "home";
            case WORK: return "work";
            case TEMP: return "temp";
            case OLD: return "old";
            case MOBILE: return "mobile";
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
          return ContactPointUse.HOME;
        if ("work".equals(codeString))
          return ContactPointUse.WORK;
        if ("temp".equals(codeString))
          return ContactPointUse.TEMP;
        if ("old".equals(codeString))
          return ContactPointUse.OLD;
        if ("mobile".equals(codeString))
          return ContactPointUse.MOBILE;
        throw new Exception("Unknown ContactPointUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ContactPointUse.HOME)
        return "home";
      if (code == ContactPointUse.WORK)
        return "work";
      if (code == ContactPointUse.TEMP)
        return "temp";
      if (code == ContactPointUse.OLD)
        return "old";
      if (code == ContactPointUse.MOBILE)
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
     * @return {@link #system} (Telecommunications form for contact point - what communications system is required to make use of the contact.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
     */
    public Enumeration<ContactPointSystem> getSystemElement() { 
      return this.system;
    }

    /**
     * @param value {@link #system} (Telecommunications form for contact point - what communications system is required to make use of the contact.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
     */
    public ContactPoint setSystemElement(Enumeration<ContactPointSystem> value) { 
      this.system = value;
      return this;
    }

    /**
     * @return Telecommunications form for contact point - what communications system is required to make use of the contact.
     */
    public ContactPointSystem getSystem() { 
      return this.system == null ? null : this.system.getValue();
    }

    /**
     * @param value Telecommunications form for contact point - what communications system is required to make use of the contact.
     */
    public ContactPoint setSystem(ContactPointSystem value) { 
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
     * @return {@link #value} (The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
     */
    public StringType getValueElement() { 
      return this.value;
    }

    /**
     * @param value {@link #value} (The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
     */
    public ContactPoint setValueElement(StringType value) { 
      this.value = value;
      return this;
    }

    /**
     * @return The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).
     */
    public String getValue() { 
      return this.value == null ? null : this.value.getValue();
    }

    /**
     * @param value The actual contact point details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).
     */
    public ContactPoint setValue(String value) { 
      if (Utilities.noString(value))
        this.value = null;
      else {
        if (this.value == null)
          this.value = new StringType();
        this.value.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #use} (Identifies the purpose for the contact point.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public Enumeration<ContactPointUse> getUseElement() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (Identifies the purpose for the contact point.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public ContactPoint setUseElement(Enumeration<ContactPointUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return Identifies the purpose for the contact point.
     */
    public ContactPointUse getUse() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value Identifies the purpose for the contact point.
     */
    public ContactPoint setUse(ContactPointUse value) { 
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
        copyValues(dst);
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

