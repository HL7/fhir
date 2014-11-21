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
 * A human's name with the ability to identify parts and usage.
 */
public class HumanName extends Type {

    public enum NameUse {
        USUAL, // Known as/conventional/the one you normally use.
        OFFICIAL, // The formal name as registered in an official (government) registry, but which name might not be commonly used. May be called "legal name".
        TEMP, // A temporary name. Name.period can provide more detailed information. This may also be used for temporary names assigned at birth or in emergency situations.
        NICKNAME, // A name that is used to address the person in an informal manner, but is not part of their formal or usual name.
        ANONYMOUS, // Anonymous assigned name, alias, or pseudonym (used to protect a person's identity for privacy reasons).
        OLD, // This name is no longer in use (or was never correct, but retained for records).
        MAIDEN, // A name used prior to marriage. Marriage naming customs vary greatly around the world. This name use is for use by applications that collect and store "maiden" names. Though the concept of maiden name is often gender specific, the use of this term is not gender specific. The use of this term does not imply any particular history for a person's name, nor should the maiden name be determined algorithmically.
        NULL; // added to help the parsers
        public static NameUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("usual".equals(codeString))
          return USUAL;
        if ("official".equals(codeString))
          return OFFICIAL;
        if ("temp".equals(codeString))
          return TEMP;
        if ("nickname".equals(codeString))
          return NICKNAME;
        if ("anonymous".equals(codeString))
          return ANONYMOUS;
        if ("old".equals(codeString))
          return OLD;
        if ("maiden".equals(codeString))
          return MAIDEN;
        throw new Exception("Unknown NameUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case USUAL: return "usual";
            case OFFICIAL: return "official";
            case TEMP: return "temp";
            case NICKNAME: return "nickname";
            case ANONYMOUS: return "anonymous";
            case OLD: return "old";
            case MAIDEN: return "maiden";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case USUAL: return "Known as/conventional/the one you normally use.";
            case OFFICIAL: return "The formal name as registered in an official (government) registry, but which name might not be commonly used. May be called 'legal name'.";
            case TEMP: return "A temporary name. Name.period can provide more detailed information. This may also be used for temporary names assigned at birth or in emergency situations.";
            case NICKNAME: return "A name that is used to address the person in an informal manner, but is not part of their formal or usual name.";
            case ANONYMOUS: return "Anonymous assigned name, alias, or pseudonym (used to protect a person's identity for privacy reasons).";
            case OLD: return "This name is no longer in use (or was never correct, but retained for records).";
            case MAIDEN: return "A name used prior to marriage. Marriage naming customs vary greatly around the world. This name use is for use by applications that collect and store 'maiden' names. Though the concept of maiden name is often gender specific, the use of this term is not gender specific. The use of this term does not imply any particular history for a person's name, nor should the maiden name be determined algorithmically.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case USUAL: return "usual";
            case OFFICIAL: return "official";
            case TEMP: return "temp";
            case NICKNAME: return "nickname";
            case ANONYMOUS: return "anonymous";
            case OLD: return "old";
            case MAIDEN: return "maiden";
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
          return NameUse.USUAL;
        if ("official".equals(codeString))
          return NameUse.OFFICIAL;
        if ("temp".equals(codeString))
          return NameUse.TEMP;
        if ("nickname".equals(codeString))
          return NameUse.NICKNAME;
        if ("anonymous".equals(codeString))
          return NameUse.ANONYMOUS;
        if ("old".equals(codeString))
          return NameUse.OLD;
        if ("maiden".equals(codeString))
          return NameUse.MAIDEN;
        throw new Exception("Unknown NameUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NameUse.USUAL)
        return "usual";
      if (code == NameUse.OFFICIAL)
        return "official";
      if (code == NameUse.TEMP)
        return "temp";
      if (code == NameUse.NICKNAME)
        return "nickname";
      if (code == NameUse.ANONYMOUS)
        return "anonymous";
      if (code == NameUse.OLD)
        return "old";
      if (code == NameUse.MAIDEN)
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
    protected StringType text;

    /**
     * The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
     */
    protected List<StringType> family = new ArrayList<StringType>();

    /**
     * Given name.
     */
    protected List<StringType> given = new ArrayList<StringType>();

    /**
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.
     */
    protected List<StringType> prefix = new ArrayList<StringType>();

    /**
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.
     */
    protected List<StringType> suffix = new ArrayList<StringType>();

    /**
     * Indicates the period of time when this name was valid for the named person.
     */
    protected Period period;

    private static final long serialVersionUID = -512452810L;

    public HumanName() {
      super();
    }

    /**
     * @return {@link #use} (Identifies the purpose for this name.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public Enumeration<NameUse> getUseElement() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (Identifies the purpose for this name.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public HumanName setUseElement(Enumeration<NameUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return Identifies the purpose for this name.
     */
    public NameUse getUse() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value Identifies the purpose for this name.
     */
    public HumanName setUse(NameUse value) { 
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
     * @return {@link #text} (A full text representation of the name.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
     */
    public StringType getTextElement() { 
      return this.text;
    }

    /**
     * @param value {@link #text} (A full text representation of the name.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
     */
    public HumanName setTextElement(StringType value) { 
      this.text = value;
      return this;
    }

    /**
     * @return A full text representation of the name.
     */
    public String getText() { 
      return this.text == null ? null : this.text.getValue();
    }

    /**
     * @param value A full text representation of the name.
     */
    public HumanName setText(String value) { 
      if (Utilities.noString(value))
        this.text = null;
      else {
        if (this.text == null)
          this.text = new StringType();
        this.text.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    public List<StringType> getFamily() { 
      return this.family;
    }

    /**
     * @return {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    // syntactic sugar
    public StringType addFamilyElement() {//2 
      StringType t = new StringType();
      this.family.add(t);
      return t;
    }

    /**
     * @param value {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    public HumanName addFamily(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.family.add(t);
      return this;
    }

    /**
     * @param value {@link #family} (The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.)
     */
    public boolean hasFamily(String value) { 
      for (StringType v : this.family)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #given} (Given name.)
     */
    public List<StringType> getGiven() { 
      return this.given;
    }

    /**
     * @return {@link #given} (Given name.)
     */
    // syntactic sugar
    public StringType addGivenElement() {//2 
      StringType t = new StringType();
      this.given.add(t);
      return t;
    }

    /**
     * @param value {@link #given} (Given name.)
     */
    public HumanName addGiven(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.given.add(t);
      return this;
    }

    /**
     * @param value {@link #given} (Given name.)
     */
    public boolean hasGiven(String value) { 
      for (StringType v : this.given)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    public List<StringType> getPrefix() { 
      return this.prefix;
    }

    /**
     * @return {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    // syntactic sugar
    public StringType addPrefixElement() {//2 
      StringType t = new StringType();
      this.prefix.add(t);
      return t;
    }

    /**
     * @param value {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    public HumanName addPrefix(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.prefix.add(t);
      return this;
    }

    /**
     * @param value {@link #prefix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name.)
     */
    public boolean hasPrefix(String value) { 
      for (StringType v : this.prefix)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    public List<StringType> getSuffix() { 
      return this.suffix;
    }

    /**
     * @return {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    // syntactic sugar
    public StringType addSuffixElement() {//2 
      StringType t = new StringType();
      this.suffix.add(t);
      return t;
    }

    /**
     * @param value {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    public HumanName addSuffix(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.suffix.add(t);
      return this;
    }

    /**
     * @param value {@link #suffix} (Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name.)
     */
    public boolean hasSuffix(String value) { 
      for (StringType v : this.suffix)
        if (v.equals(value)) // string
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
        copyValues(dst);
        dst.use = use == null ? null : use.copy();
        dst.text = text == null ? null : text.copy();
        dst.family = new ArrayList<StringType>();
        for (StringType i : family)
          dst.family.add(i.copy());
        dst.given = new ArrayList<StringType>();
        for (StringType i : given)
          dst.given.add(i.copy());
        dst.prefix = new ArrayList<StringType>();
        for (StringType i : prefix)
          dst.prefix.add(i.copy());
        dst.suffix = new ArrayList<StringType>();
        for (StringType i : suffix)
          dst.suffix.add(i.copy());
        dst.period = period == null ? null : period.copy();
        return dst;
      }

      protected HumanName typedCopy() {
        return copy();
      }


}

