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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import java.util.*;

/**
 * There is a variety of postal address formats defined around the world. This format defines a superset that is the basis for all addresses around the world.
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
     * The purpose of this address.
     */
    protected Enumeration<AddressUse> use;

    /**
     * A full text representation of the address.
     */
    protected StringType text;

    /**
     * This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.
     */
    protected List<StringType> line = new ArrayList<StringType>();

    /**
     * The name of the city, town, village or other community or delivery center.
     */
    protected StringType city;

    /**
     * Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
     */
    protected StringType state;

    /**
     * A postal code designating a region defined by the postal service.
     */
    protected StringType zip;

    /**
     * Country - a nation as commonly understood or generally accepted.
     */
    protected StringType country;

    /**
     * Time period when address was/is in use.
     */
    protected Period period;

    private static final long serialVersionUID = 1214286881L;

    public Address() {
      super();
    }

    /**
     * @return {@link #use} (The purpose of this address.)
     */
    public Enumeration<AddressUse> getUse() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (The purpose of this address.)
     */
    public Address setUse(Enumeration<AddressUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return The purpose of this address.
     */
    public AddressUse getUseSimple() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value The purpose of this address.
     */
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

    /**
     * @return {@link #text} (A full text representation of the address.)
     */
    public StringType getText() { 
      return this.text;
    }

    /**
     * @param value {@link #text} (A full text representation of the address.)
     */
    public Address setText(StringType value) { 
      this.text = value;
      return this;
    }

    /**
     * @return A full text representation of the address.
     */
    public String getTextSimple() { 
      return this.text == null ? null : this.text.getValue();
    }

    /**
     * @param value A full text representation of the address.
     */
    public Address setTextSimple(String value) { 
      if (value == null)
        this.text = null;
      else {
        if (this.text == null)
          this.text = new StringType();
        this.text.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    public List<StringType> getLine() { 
      return this.line;
    }

    // syntactic sugar
    /**
     * @return {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    public StringType addLine() { 
      StringType t = new StringType();
      this.line.add(t);
      return t;
    }

    /**
     * @param value {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    public StringType addLineSimple(String value) { 
      StringType t = new StringType();
      t.setValue(value);
      this.line.add(t);
      return t;
    }

    /**
     * @param value {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    public boolean hasLineSimple(String value) { 
      for (StringType v : this.line)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #city} (The name of the city, town, village or other community or delivery center.)
     */
    public StringType getCity() { 
      return this.city;
    }

    /**
     * @param value {@link #city} (The name of the city, town, village or other community or delivery center.)
     */
    public Address setCity(StringType value) { 
      this.city = value;
      return this;
    }

    /**
     * @return The name of the city, town, village or other community or delivery center.
     */
    public String getCitySimple() { 
      return this.city == null ? null : this.city.getValue();
    }

    /**
     * @param value The name of the city, town, village or other community or delivery center.
     */
    public Address setCitySimple(String value) { 
      if (value == null)
        this.city = null;
      else {
        if (this.city == null)
          this.city = new StringType();
        this.city.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #state} (Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).)
     */
    public StringType getState() { 
      return this.state;
    }

    /**
     * @param value {@link #state} (Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).)
     */
    public Address setState(StringType value) { 
      this.state = value;
      return this;
    }

    /**
     * @return Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
     */
    public String getStateSimple() { 
      return this.state == null ? null : this.state.getValue();
    }

    /**
     * @param value Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
     */
    public Address setStateSimple(String value) { 
      if (value == null)
        this.state = null;
      else {
        if (this.state == null)
          this.state = new StringType();
        this.state.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #zip} (A postal code designating a region defined by the postal service.)
     */
    public StringType getZip() { 
      return this.zip;
    }

    /**
     * @param value {@link #zip} (A postal code designating a region defined by the postal service.)
     */
    public Address setZip(StringType value) { 
      this.zip = value;
      return this;
    }

    /**
     * @return A postal code designating a region defined by the postal service.
     */
    public String getZipSimple() { 
      return this.zip == null ? null : this.zip.getValue();
    }

    /**
     * @param value A postal code designating a region defined by the postal service.
     */
    public Address setZipSimple(String value) { 
      if (value == null)
        this.zip = null;
      else {
        if (this.zip == null)
          this.zip = new StringType();
        this.zip.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #country} (Country - a nation as commonly understood or generally accepted.)
     */
    public StringType getCountry() { 
      return this.country;
    }

    /**
     * @param value {@link #country} (Country - a nation as commonly understood or generally accepted.)
     */
    public Address setCountry(StringType value) { 
      this.country = value;
      return this;
    }

    /**
     * @return Country - a nation as commonly understood or generally accepted.
     */
    public String getCountrySimple() { 
      return this.country == null ? null : this.country.getValue();
    }

    /**
     * @param value Country - a nation as commonly understood or generally accepted.
     */
    public Address setCountrySimple(String value) { 
      if (value == null)
        this.country = null;
      else {
        if (this.country == null)
          this.country = new StringType();
        this.country.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #period} (Time period when address was/is in use.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (Time period when address was/is in use.)
     */
    public Address setPeriod(Period value) { 
      this.period = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("use", "code", "The purpose of this address.", 0, java.lang.Integer.MAX_VALUE, use));
        childrenList.add(new Property("text", "string", "A full text representation of the address.", 0, java.lang.Integer.MAX_VALUE, text));
        childrenList.add(new Property("line", "string", "This component contains the house number, apartment number, street name, street direction, \nP.O. Box number, delivery hints, and similar address information.", 0, java.lang.Integer.MAX_VALUE, line));
        childrenList.add(new Property("city", "string", "The name of the city, town, village or other community or delivery center.", 0, java.lang.Integer.MAX_VALUE, city));
        childrenList.add(new Property("state", "string", "Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).", 0, java.lang.Integer.MAX_VALUE, state));
        childrenList.add(new Property("zip", "string", "A postal code designating a region defined by the postal service.", 0, java.lang.Integer.MAX_VALUE, zip));
        childrenList.add(new Property("country", "string", "Country - a nation as commonly understood or generally accepted.", 0, java.lang.Integer.MAX_VALUE, country));
        childrenList.add(new Property("period", "Period", "Time period when address was/is in use.", 0, java.lang.Integer.MAX_VALUE, period));
      }

      public Address copy() {
        Address dst = new Address();
        dst.use = use == null ? null : use.copy();
        dst.text = text == null ? null : text.copy();
        dst.line = new ArrayList<StringType>();
        for (StringType i : line)
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

