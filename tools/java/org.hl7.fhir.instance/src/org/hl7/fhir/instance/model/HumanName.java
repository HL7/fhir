package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2014, HL7, Inc.
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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

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

  public static class NameUseEnumFactory implements EnumFactory {
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
     * The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
     */
    protected List<String_> family = new ArrayList<String_>();

    /**
     * Given name.
     */
    protected List<String_> given = new ArrayList<String_>();

    /**
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.
     */
    protected List<String_> prefix = new ArrayList<String_>();

    /**
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.
     */
    protected List<String_> suffix = new ArrayList<String_>();

    /**
     * Indicates the period of time when this name was valid for the named person.
     */
    protected Period period;

    private static final long serialVersionUID = -1691515899L;

    public HumanName() {
      super();
    }

    /**
     * @return {@link #use} (Identifies the purpose for this name.)
     */
    public Enumeration<NameUse> getUse() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (Identifies the purpose for this name.)
     */
    public HumanName setUse(Enumeration<NameUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return Identifies the purpose for this name.
     */
    public NameUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value Identifies the purpose for this name.
     */
    public HumanName setUseSimple(NameUse value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<NameUse>();
        this.use.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #text} (A full text representation of the name.)
     */
    public String_ getText() { 
      return this.text;
    }

    /**
     * @param value {@link #text} (A full text representation of the name.)
     */
    public HumanName setText(String_ value) { 
      this.text = value;
      return this;
    }

    /**
     * @return A full text representation of the name.
     */
    public String getTextSimple() { 
      return this.text == null ? null : this.text.getValue();
    }

    /**
     * @param value A full text representation of the name.
     */
    public HumanName setTextSimple(String value) { 
      if (value == null)
        this.text = null;
      else {
        if (this.text == null)
          this.text = new String_();
        this.text.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    public List<String_> getFamily() { 
      return this.family;
    }

    // syntactic sugar
    /**
     * @return {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    public String_ addFamily() { 
      String_ t = new String_();
      this.family.add(t);
      return t;
    }

    /**
     * @param value {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    public String_ addFamilySimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.family.add(t);
      return t;
    }

    /**
     * @param value {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    public boolean hasFamilySimple(String value) { 
      for (String_ v : this.family)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #given} (Given name.)
     */
    public List<String_> getGiven() { 
      return this.given;
    }

    // syntactic sugar
    /**
     * @return {@link #given} (Given name.)
     */
    public String_ addGiven() { 
      String_ t = new String_();
      this.given.add(t);
      return t;
    }

    /**
     * @param value {@link #given} (Given name.)
     */
    public String_ addGivenSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.given.add(t);
      return t;
    }

    /**
     * @param value {@link #given} (Given name.)
     */
    public boolean hasGivenSimple(String value) { 
      for (String_ v : this.given)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    public List<String_> getPrefix() { 
      return this.prefix;
    }

    // syntactic sugar
    /**
     * @return {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    public String_ addPrefix() { 
      String_ t = new String_();
      this.prefix.add(t);
      return t;
    }

    /**
     * @param value {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    public String_ addPrefixSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.prefix.add(t);
      return t;
    }

    /**
     * @param value {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    public boolean hasPrefixSimple(String value) { 
      for (String_ v : this.prefix)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    public List<String_> getSuffix() { 
      return this.suffix;
    }

    // syntactic sugar
    /**
     * @return {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    public String_ addSuffix() { 
      String_ t = new String_();
      this.suffix.add(t);
      return t;
    }

    /**
     * @param value {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    public String_ addSuffixSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.suffix.add(t);
      return t;
    }

    /**
     * @param value {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    public boolean hasSuffixSimple(String value) { 
      for (String_ v : this.suffix)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #period} (Indicates the period of time when this name was valid for the named person.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (Indicates the period of time when this name was valid for the named person.)
     */
    public HumanName setPeriod(Period value) { 
      this.period = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("use", "code", "Identifies the purpose for this name.", 0, java.lang.Integer.MAX_VALUE, use));
        childrenList.add(new Property("text", "string", "A full text representation of the name.", 0, java.lang.Integer.MAX_VALUE, text));
        childrenList.add(new Property("family", "string", "The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.", 0, java.lang.Integer.MAX_VALUE, family));
        childrenList.add(new Property("given", "string", "Given name.", 0, java.lang.Integer.MAX_VALUE, given));
        childrenList.add(new Property("prefix", "string", "Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.", 0, java.lang.Integer.MAX_VALUE, prefix));
        childrenList.add(new Property("suffix", "string", "Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.", 0, java.lang.Integer.MAX_VALUE, suffix));
        childrenList.add(new Property("period", "Period", "Indicates the period of time when this name was valid for the named person.", 0, java.lang.Integer.MAX_VALUE, period));
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

