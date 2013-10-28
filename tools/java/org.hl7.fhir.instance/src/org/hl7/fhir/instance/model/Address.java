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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * There is a variety of postal address formats defined around the world. This format defines a superset that is the basis for addresses all around the world.
 */
public class Address extends Type {

    public enum AddressUse {
        home, // A communication address at a home.
        work, // An office address. First choice for business related contacts during business hours.
        temp, // A temporary address. The period can provide more detailed information.
        old, // This address is no longer in use (or was never correct, but retained for records).
        Null; // added to help the parsers
        public static AddressUse fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown AddressUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case home: return "home";
            case work: return "work";
            case temp: return "temp";
            case old: return "old";
            default: return "?";
          }
        }
    }

  public static class AddressUseEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("home".equals(codeString))
          return AddressUse.home;
        if ("work".equals(codeString))
          return AddressUse.work;
        if ("temp".equals(codeString))
          return AddressUse.temp;
        if ("old".equals(codeString))
          return AddressUse.old;
        throw new Exception("Unknown AddressUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AddressUse.home)
        return "home";
      if (code == AddressUse.work)
        return "work";
      if (code == AddressUse.temp)
        return "temp";
      if (code == AddressUse.old)
        return "old";
      return "?";
      }
    }

    /**
     * Identifies the intended purpose of this address.
     */
    protected Enumeration<AddressUse> use;

    /**
     * A full text representation of the address.
     */
    protected String_ text;

    /**
     * This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.
     */
    protected List<String_> line = new ArrayList<String_>();

    /**
     * The name of the city, town, village or other community or delivery center.
     */
    protected String_ city;

    /**
     * Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
     */
    protected String_ state;

    /**
     * A postal code designating a region defined by the postal service.
     */
    protected String_ zip;

    /**
     * Country. ISO 3166 3 letter codes can be used in place of a full country name.
     */
    protected String_ country;

    /**
     * Time period when address was/is in use.
     */
    protected Period period;

    public Address() {
      super();
    }

    public Enumeration<AddressUse> getUse() { 
      return this.use;
    }

    public Address setUse(Enumeration<AddressUse> value) { 
      this.use = value;
      return this;
    }

    public AddressUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    public Address setUseSimple(AddressUse value) { 
      if (value == null)
        this.use = null;
      else {
        if (this.use == null)
          this.use = new Enumeration<AddressUse>();
        this.use.setValue(value);
      }
      return this;
    }

    public String_ getText() { 
      return this.text;
    }

    public Address setText(String_ value) { 
      this.text = value;
      return this;
    }

    public String getTextSimple() { 
      return this.text == null ? null : this.text.getValue();
    }

    public Address setTextSimple(String value) { 
      if (value == null)
        this.text = null;
      else {
        if (this.text == null)
          this.text = new String_();
        this.text.setValue(value);
      }
      return this;
    }

    public List<String_> getLine() { 
      return this.line;
    }

    // syntactic sugar
    public String_ addLine() { 
      String_ t = new String_();
      this.line.add(t);
      return t;
    }

    public String_ addLineSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.line.add(t);
      return t;
    }

    public String_ getCity() { 
      return this.city;
    }

    public Address setCity(String_ value) { 
      this.city = value;
      return this;
    }

    public String getCitySimple() { 
      return this.city == null ? null : this.city.getValue();
    }

    public Address setCitySimple(String value) { 
      if (value == null)
        this.city = null;
      else {
        if (this.city == null)
          this.city = new String_();
        this.city.setValue(value);
      }
      return this;
    }

    public String_ getState() { 
      return this.state;
    }

    public Address setState(String_ value) { 
      this.state = value;
      return this;
    }

    public String getStateSimple() { 
      return this.state == null ? null : this.state.getValue();
    }

    public Address setStateSimple(String value) { 
      if (value == null)
        this.state = null;
      else {
        if (this.state == null)
          this.state = new String_();
        this.state.setValue(value);
      }
      return this;
    }

    public String_ getZip() { 
      return this.zip;
    }

    public Address setZip(String_ value) { 
      this.zip = value;
      return this;
    }

    public String getZipSimple() { 
      return this.zip == null ? null : this.zip.getValue();
    }

    public Address setZipSimple(String value) { 
      if (value == null)
        this.zip = null;
      else {
        if (this.zip == null)
          this.zip = new String_();
        this.zip.setValue(value);
      }
      return this;
    }

    public String_ getCountry() { 
      return this.country;
    }

    public Address setCountry(String_ value) { 
      this.country = value;
      return this;
    }

    public String getCountrySimple() { 
      return this.country == null ? null : this.country.getValue();
    }

    public Address setCountrySimple(String value) { 
      if (value == null)
        this.country = null;
      else {
        if (this.country == null)
          this.country = new String_();
        this.country.setValue(value);
      }
      return this;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public Address setPeriod(Period value) { 
      this.period = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("use", "code", "Identifies the intended purpose of this address.", 0, java.lang.Integer.MAX_VALUE, use));
        childrenList.add(new Property("text", "string", "A full text representation of the address.", 0, java.lang.Integer.MAX_VALUE, text));
        childrenList.add(new Property("line", "string", "This component contains the house number, apartment number, street name, street direction, \nP.O. Box number, delivery hints, and similar address information.", 0, java.lang.Integer.MAX_VALUE, line));
        childrenList.add(new Property("city", "string", "The name of the city, town, village or other community or delivery center.", 0, java.lang.Integer.MAX_VALUE, city));
        childrenList.add(new Property("state", "string", "Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).", 0, java.lang.Integer.MAX_VALUE, state));
        childrenList.add(new Property("zip", "string", "A postal code designating a region defined by the postal service.", 0, java.lang.Integer.MAX_VALUE, zip));
        childrenList.add(new Property("country", "string", "Country. ISO 3166 3 letter codes can be used in place of a full country name.", 0, java.lang.Integer.MAX_VALUE, country));
        childrenList.add(new Property("period", "Period", "Time period when address was/is in use.", 0, java.lang.Integer.MAX_VALUE, period));
      }

      public Address copy() {
        Address dst = new Address();
        dst.use = use == null ? null : use.copy();
        dst.text = text == null ? null : text.copy();
        dst.line = new ArrayList<String_>();
        for (String_ i : line)
          dst.line.add(i.copy());
        dst.city = city == null ? null : city.copy();
        dst.state = state == null ? null : state.copy();
        dst.zip = zip == null ? null : zip.copy();
        dst.country = country == null ? null : country.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

      protected Address typedCopy() {
        return copy();
      }


}

