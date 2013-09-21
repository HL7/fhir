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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

import java.util.*;

/**
 * A user authorised to use the system.
 */
public class User extends Resource {

    public enum UserLevel {
        patient, // 
        provider, // 
        privileged, // 
        administrator, // 
        Null; // added to help the parsers
        public static UserLevel fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("patient".equals(codeString))
          return patient;
        if ("provider".equals(codeString))
          return provider;
        if ("privileged".equals(codeString))
          return privileged;
        if ("administrator".equals(codeString))
          return administrator;
        throw new Exception("Unknown UserLevel code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case patient: return "patient";
            case provider: return "provider";
            case privileged: return "privileged";
            case administrator: return "administrator";
            default: return "?";
          }
        }
    }

  public class UserLevelEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("patient".equals(codeString))
          return UserLevel.patient;
        if ("provider".equals(codeString))
          return UserLevel.provider;
        if ("privileged".equals(codeString))
          return UserLevel.privileged;
        if ("administrator".equals(codeString))
          return UserLevel.administrator;
        throw new Exception("Unknown UserLevel code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == UserLevel.patient)
        return "patient";
      if (code == UserLevel.provider)
        return "provider";
      if (code == UserLevel.privileged)
        return "privileged";
      if (code == UserLevel.administrator)
        return "administrator";
      return "?";
      }
    }

    /**
     * The name of this user.
     */
    protected HumanName name;

    /**
     * Which system authenticates the user. Blanks = internally authenticated.
     */
    protected Uri provider;

    /**
     * The login by which this user is known.
     */
    protected String_ login;

    /**
     * If internal login, the password hash (SHA 256, Hex, lowercase).
     */
    protected String_ password;

    /**
     * The level access for this user.
     */
    protected Enumeration<UserLevel> level;

    /**
     * How long a session lasts for.
     */
    protected Integer sessionLength;

    /**
     * Contact details for the user.
     */
    protected List<Contact> contact = new ArrayList<Contact>();

    /**
     * Patient compartments the user has access to (if level is patient/family).
     */
    protected List<ResourceReference> patient = new ArrayList<ResourceReference>();

    public HumanName getName() { 
      return this.name;
    }

    public void setName(HumanName value) { 
      this.name = value;
    }

    public Uri getProvider() { 
      return this.provider;
    }

    public void setProvider(Uri value) { 
      this.provider = value;
    }

    public String getProviderSimple() { 
      return this.provider == null ? null : this.provider.getValue();
    }

    public void setProviderSimple(String value) { 
      if (value == null)
        this.provider = null;
      else {
        if (this.provider == null)
          this.provider = new Uri();
        this.provider.setValue(value);
      }
    }

    public String_ getLogin() { 
      return this.login;
    }

    public void setLogin(String_ value) { 
      this.login = value;
    }

    public String getLoginSimple() { 
      return this.login == null ? null : this.login.getValue();
    }

    public void setLoginSimple(String value) { 
        if (this.login == null)
          this.login = new String_();
        this.login.setValue(value);
    }

    public String_ getPassword() { 
      return this.password;
    }

    public void setPassword(String_ value) { 
      this.password = value;
    }

    public String getPasswordSimple() { 
      return this.password == null ? null : this.password.getValue();
    }

    public void setPasswordSimple(String value) { 
      if (value == null)
        this.password = null;
      else {
        if (this.password == null)
          this.password = new String_();
        this.password.setValue(value);
      }
    }

    public Enumeration<UserLevel> getLevel() { 
      return this.level;
    }

    public void setLevel(Enumeration<UserLevel> value) { 
      this.level = value;
    }

    public UserLevel getLevelSimple() { 
      return this.level == null ? null : this.level.getValue();
    }

    public void setLevelSimple(UserLevel value) { 
        if (this.level == null)
          this.level = new Enumeration<UserLevel>();
        this.level.setValue(value);
    }

    public Integer getSessionLength() { 
      return this.sessionLength;
    }

    public void setSessionLength(Integer value) { 
      this.sessionLength = value;
    }

    public int getSessionLengthSimple() { 
      return this.sessionLength == null ? null : this.sessionLength.getValue();
    }

    public void setSessionLengthSimple(int value) { 
      if (value == -1)
        this.sessionLength = null;
      else {
        if (this.sessionLength == null)
          this.sessionLength = new Integer();
        this.sessionLength.setValue(value);
      }
    }

    public List<Contact> getContact() { 
      return this.contact;
    }

    // syntactic sugar
    public Contact addContact() { 
      Contact t = new Contact();
      this.contact.add(t);
      return t;
    }

    public List<ResourceReference> getPatient() { 
      return this.patient;
    }

    // syntactic sugar
    public ResourceReference addPatient() { 
      ResourceReference t = new ResourceReference();
      this.patient.add(t);
      return t;
    }

      public User copy() {
        User dst = new User();
        dst.name = name == null ? null : name.copy();
        dst.provider = provider == null ? null : provider.copy();
        dst.login = login == null ? null : login.copy();
        dst.password = password == null ? null : password.copy();
        dst.level = level == null ? null : level.copy();
        dst.sessionLength = sessionLength == null ? null : sessionLength.copy();
        dst.contact = new ArrayList<Contact>();
        for (Contact i : contact)
          dst.contact.add(i.copy());
        dst.patient = new ArrayList<ResourceReference>();
        for (ResourceReference i : patient)
          dst.patient.add(i.copy());
        return dst;
      }

      protected User typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.User;
   }


}

