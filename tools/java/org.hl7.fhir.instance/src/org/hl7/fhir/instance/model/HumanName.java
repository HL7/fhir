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

// Generated on Fri, Oct 18, 2013 12:16+1100 for FHIR v0.12

import java.util.*;

/**
 * A human's name with the ability to identify parts and usage.
 */
public class HumanName extends Type {

    public enum NameUse {
        usual, // Known as/conventional/the one you normally use.
        official, // The formal name as registered in an official (government) registry, but which name might not be commonly used. May be called "legal name".
        temp, // A temporary name. Name.period can provide more detailed information. This may also be used for temporary names assigned at birth or in emergency situations.
        nickname, // A name that is used to address the person in an informal manner, but is not part of their formal or usual name.
        anonymous, // Anonymous assigned name, alias, or pseudonym (used to protect a person's identity for privacy reasons).
        old, // This name is no longer in use (or was never correct, but retained for records).
        maiden, // A name used prior to marriage. Marriage naming customs vary greatly around the world. This name use is for use by applications that collect and store "maiden" names. Though the concept of maiden name is often gender specific, the use of this term is not gender specific. The use of this term does not imply any particular history for a person's name, nor should the maiden name be determined algorithmically.
        Null; // added to help the parsers
        public static NameUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("usual".equals(codeString))
          return usual;
        if ("official".equals(codeString))
          return official;
        if ("temp".equals(codeString))
          return temp;
        if ("nickname".equals(codeString))
          return nickname;
        if ("anonymous".equals(codeString))
          return anonymous;
        if ("old".equals(codeString))
          return old;
        if ("maiden".equals(codeString))
          return maiden;
        throw new Exception("Unknown NameUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case usual: return "usual";
            case official: return "official";
            case temp: return "temp";
            case nickname: return "nickname";
            case anonymous: return "anonymous";
            case old: return "old";
            case maiden: return "maiden";
            default: return "?";
          }
        }
    }

  public class NameUseEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("usual".equals(codeString))
          return NameUse.usual;
        if ("official".equals(codeString))
          return NameUse.official;
        if ("temp".equals(codeString))
          return NameUse.temp;
        if ("nickname".equals(codeString))
          return NameUse.nickname;
        if ("anonymous".equals(codeString))
          return NameUse.anonymous;
        if ("old".equals(codeString))
          return NameUse.old;
        if ("maiden".equals(codeString))
          return NameUse.maiden;
        throw new Exception("Unknown NameUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NameUse.usual)
        return "usual";
      if (code == NameUse.official)
        return "official";
      if (code == NameUse.temp)
        return "temp";
      if (code == NameUse.nickname)
        return "nickname";
      if (code == NameUse.anonymous)
        return "anonymous";
      if (code == NameUse.old)
        return "old";
      if (code == NameUse.maiden)
        return "maiden";
      return "?";
      }
    }

    /**
     * Identifies the purpose for this name.
     */
    protected Enumeration<NameUse> use;

    /**
     * A full text representation of the name.
     */
    protected String_ text;

    /**
     * Family name, this is the name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
     */
    protected List<String_> family = new ArrayList<String_>();

    /**
     * Given name. NOTE: Not to be called "first name" since given names do not always come first.
     */
    protected List<String_> given = new ArrayList<String_>();

    /**
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the start of the name.
     */
    protected List<String_> prefix = new ArrayList<String_>();

    /**
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the end of the name.
     */
    protected List<String_> suffix = new ArrayList<String_>();

    /**
     * Indicates the period of time when this name was valid for the named person.
     */
    protected Period period;

    public Enumeration<NameUse> getUse() { 
      return this.use;
    }

    public void setUse(Enumeration<NameUse> value) { 
      this.use = value;
    }

    public NameUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    public void setUseSimple(NameUse value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<NameUse>();
        this.use.setValue(value);
      }
    }

    public String_ getText() { 
      return this.text;
    }

    public void setText(String_ value) { 
      this.text = value;
    }

    public String getTextSimple() { 
      return this.text == null ? null : this.text.getValue();
    }

    public void setTextSimple(String value) { 
      if (value == null)
        this.text = null;
      else {
        if (this.text == null)
          this.text = new String_();
        this.text.setValue(value);
      }
    }

    public List<String_> getFamily() { 
      return this.family;
    }

    // syntactic sugar
    public String_ addFamily() { 
      String_ t = new String_();
      this.family.add(t);
      return t;
    }

    public String_ addFamilySimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.family.add(t);
      return t;
    }

    public List<String_> getGiven() { 
      return this.given;
    }

    // syntactic sugar
    public String_ addGiven() { 
      String_ t = new String_();
      this.given.add(t);
      return t;
    }

    public String_ addGivenSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.given.add(t);
      return t;
    }

    public List<String_> getPrefix() { 
      return this.prefix;
    }

    // syntactic sugar
    public String_ addPrefix() { 
      String_ t = new String_();
      this.prefix.add(t);
      return t;
    }

    public String_ addPrefixSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.prefix.add(t);
      return t;
    }

    public List<String_> getSuffix() { 
      return this.suffix;
    }

    // syntactic sugar
    public String_ addSuffix() { 
      String_ t = new String_();
      this.suffix.add(t);
      return t;
    }

    public String_ addSuffixSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.suffix.add(t);
      return t;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

      public HumanName copy() {
        HumanName dst = new HumanName();
        dst.use = use == null ? null : use.copy();
        dst.text = text == null ? null : text.copy();
        dst.family = new ArrayList<String_>();
        for (String_ i : family)
          dst.family.add(i.copy());
        dst.given = new ArrayList<String_>();
        for (String_ i : given)
          dst.given.add(i.copy());
        dst.prefix = new ArrayList<String_>();
        for (String_ i : prefix)
          dst.prefix.add(i.copy());
        dst.suffix = new ArrayList<String_>();
        for (String_ i : suffix)
          dst.suffix.add(i.copy());
        dst.period = period == null ? null : period.copy();
        return dst;
      }

      protected HumanName typedCopy() {
        return copy();
      }


}

