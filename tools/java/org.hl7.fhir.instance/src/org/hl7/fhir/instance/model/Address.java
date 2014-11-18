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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * There is a variety of postal address formats defined around the world. This format defines a superset that is the basis for all addresses around the world.
 */
public class Address extends Type {

    public enum AddressUse {
        HOME, // A communication address at a home.
        WORK, // An office address. First choice for business related contacts during business hours.
        TEMP, // A temporary address. The period can provide more detailed information.
        OLD, // This address is no longer in use (or was never correct, but retained for records).
        NULL; // added to help the parsers
        public static AddressUse fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown AddressUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case HOME: return "home";
            case WORK: return "work";
            case TEMP: return "temp";
            case OLD: return "old";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case HOME: return "A communication address at a home.";
            case WORK: return "An office address. First choice for business related contacts during business hours.";
            case TEMP: return "A temporary address. The period can provide more detailed information.";
            case OLD: return "This address is no longer in use (or was never correct, but retained for records).";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case HOME: return "home";
            case WORK: return "work";
            case TEMP: return "temp";
            case OLD: return "old";
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
          return AddressUse.HOME;
        if ("work".equals(codeString))
          return AddressUse.WORK;
        if ("temp".equals(codeString))
          return AddressUse.TEMP;
        if ("old".equals(codeString))
          return AddressUse.OLD;
        throw new Exception("Unknown AddressUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AddressUse.HOME)
        return "home";
      if (code == AddressUse.WORK)
        return "work";
      if (code == AddressUse.TEMP)
        return "temp";
      if (code == AddressUse.OLD)
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
    protected StringType postalCode;

    /**
     * Country - a nation as commonly understood or generally accepted.
     */
    protected StringType country;

    /**
     * Time period when address was/is in use.
     */
    protected Period period;

    private static final long serialVersionUID = 1512502502L;

    public Address() {
      super();
    }

    /**
     * @return {@link #use} (The purpose of this address.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public Enumeration<AddressUse> getUseElement() { 
      return this.use;
    }

    /**
     * @param value {@link #use} (The purpose of this address.). This is the underlying object with id, value and extensions. The accessor "getUse" gives direct access to the value
     */
    public Address setUseElement(Enumeration<AddressUse> value) { 
      this.use = value;
      return this;
    }

    /**
     * @return The purpose of this address.
     */
    public AddressUse getUse() { 
      return this.use == null ? null : this.use.getValue();
    }

    /**
     * @param value The purpose of this address.
     */
    public Address setUse(AddressUse value) { 
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
     * @return {@link #text} (A full text representation of the address.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
     */
    public StringType getTextElement() { 
      return this.text;
    }

    /**
     * @param value {@link #text} (A full text representation of the address.). This is the underlying object with id, value and extensions. The accessor "getText" gives direct access to the value
     */
    public Address setTextElement(StringType value) { 
      this.text = value;
      return this;
    }

    /**
     * @return A full text representation of the address.
     */
    public String getText() { 
      return this.text == null ? null : this.text.getValue();
    }

    /**
     * @param value A full text representation of the address.
     */
    public Address setText(String value) { 
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
     * @return {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    public List<StringType> getLine() { 
      return this.line;
    }

    /**
     * @return {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    // syntactic sugar
    public StringType addLineElement() {//2 
      StringType t = new StringType();
      this.line.add(t);
      return t;
    }

    /**
     * @param value {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    public Address addLine(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.line.add(t);
      return this;
    }

    /**
     * @param value {@link #line} (This component contains the house number, apartment number, street name, street direction, 
P.O. Box number, delivery hints, and similar address information.)
     */
    public boolean hasLine(String value) { 
      for (StringType v : this.line)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #city} (The name of the city, town, village or other community or delivery center.). This is the underlying object with id, value and extensions. The accessor "getCity" gives direct access to the value
     */
    public StringType getCityElement() { 
      return this.city;
    }

    /**
     * @param value {@link #city} (The name of the city, town, village or other community or delivery center.). This is the underlying object with id, value and extensions. The accessor "getCity" gives direct access to the value
     */
    public Address setCityElement(StringType value) { 
      this.city = value;
      return this;
    }

    /**
     * @return The name of the city, town, village or other community or delivery center.
     */
    public String getCity() { 
      return this.city == null ? null : this.city.getValue();
    }

    /**
     * @param value The name of the city, town, village or other community or delivery center.
     */
    public Address setCity(String value) { 
      if (Utilities.noString(value))
        this.city = null;
      else {
        if (this.city == null)
          this.city = new StringType();
        this.city.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #state} (Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).). This is the underlying object with id, value and extensions. The accessor "getState" gives direct access to the value
     */
    public StringType getStateElement() { 
      return this.state;
    }

    /**
     * @param value {@link #state} (Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).). This is the underlying object with id, value and extensions. The accessor "getState" gives direct access to the value
     */
    public Address setStateElement(StringType value) { 
      this.state = value;
      return this;
    }

    /**
     * @return Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
     */
    public String getState() { 
      return this.state == null ? null : this.state.getValue();
    }

    /**
     * @param value Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
     */
    public Address setState(String value) { 
      if (Utilities.noString(value))
        this.state = null;
      else {
        if (this.state == null)
          this.state = new StringType();
        this.state.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #postalCode} (A postal code designating a region defined by the postal service.). This is the underlying object with id, value and extensions. The accessor "getPostalCode" gives direct access to the value
     */
    public StringType getPostalCodeElement() { 
      return this.postalCode;
    }

    /**
     * @param value {@link #postalCode} (A postal code designating a region defined by the postal service.). This is the underlying object with id, value and extensions. The accessor "getPostalCode" gives direct access to the value
     */
    public Address setPostalCodeElement(StringType value) { 
      this.postalCode = value;
      return this;
    }

    /**
     * @return A postal code designating a region defined by the postal service.
     */
    public String getPostalCode() { 
      return this.postalCode == null ? null : this.postalCode.getValue();
    }

    /**
     * @param value A postal code designating a region defined by the postal service.
     */
    public Address setPostalCode(String value) { 
      if (Utilities.noString(value))
        this.postalCode = null;
      else {
        if (this.postalCode == null)
          this.postalCode = new StringType();
        this.postalCode.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #country} (Country - a nation as commonly understood or generally accepted.). This is the underlying object with id, value and extensions. The accessor "getCountry" gives direct access to the value
     */
    public StringType getCountryElement() { 
      return this.country;
    }

    /**
     * @param value {@link #country} (Country - a nation as commonly understood or generally accepted.). This is the underlying object with id, value and extensions. The accessor "getCountry" gives direct access to the value
     */
    public Address setCountryElement(StringType value) { 
      this.country = value;
      return this;
    }

    /**
     * @return Country - a nation as commonly understood or generally accepted.
     */
    public String getCountry() { 
      return this.country == null ? null : this.country.getValue();
    }

    /**
     * @param value Country - a nation as commonly understood or generally accepted.
     */
    public Address setCountry(String value) { 
      if (Utilities.noString(value))
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
        childrenList.add(new Property("postalCode", "string", "A postal code designating a region defined by the postal service.", 0, java.lang.Integer.MAX_VALUE, postalCode));
        childrenList.add(new Property("country", "string", "Country - a nation as commonly understood or generally accepted.", 0, java.lang.Integer.MAX_VALUE, country));
        childrenList.add(new Property("period", "Period", "Time period when address was/is in use.", 0, java.lang.Integer.MAX_VALUE, period));
      }

      public Address copy() {
        Address dst = new Address();
        copyValues(dst);
        dst.use = use == null ? null : use.copy();
        dst.text = text == null ? null : text.copy();
        dst.line = new ArrayList<StringType>();
        for (StringType i : line)
          dst.line.add(i.copy());
        dst.city = city == null ? null : city.copy();
        dst.state = state == null ? null : state.copy();
        dst.postalCode = postalCode == null ? null : postalCode.copy();
        dst.country = country == null ? null : country.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

      protected Address typedCopy() {
        return copy();
      }


}

