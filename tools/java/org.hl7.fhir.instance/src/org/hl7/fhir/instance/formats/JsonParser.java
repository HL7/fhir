package org.hl7.fhir.instance.formats;

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

import org.hl7.fhir.instance.model.Integer;
import org.hl7.fhir.instance.model.DateTime;
import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.Date;
import org.hl7.fhir.instance.model.Decimal;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.Id;
import org.hl7.fhir.instance.model.Base64Binary;
import org.hl7.fhir.instance.model.Oid;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.Uuid;
import org.hl7.fhir.instance.model.Instant;
import org.hl7.fhir.instance.model.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class JsonParser extends JsonParserBase {

  protected void parseElementProperties(JSONObject json, Element element) throws Exception {
    super.parseElementProperties(json, element);
    if (json != null && json.has("extension")) {
      JSONArray array = json.getJSONArray("extension");
      for (int i = 0; i < array.length(); i++) {
        element.getExtensions().add(parseExtension(array.getJSONObject(i)));
      }
    };
  }

  protected void parseTypeProperties(JSONObject json, Element element) throws Exception {
    parseElementProperties(json, element);
  }

  @SuppressWarnings("unchecked")
  private <E extends Enum<E>> Enumeration<E> parseEnumeration(String s, E item, EnumFactory e) throws Exception {
    Enumeration<E> res = new Enumeration<E>();
    if (s != null)
      res.setValue((E) e.fromCode(s));
    return res;
  }

  private Integer parseInteger(String v) throws Exception {
    Integer res = new Integer();
    if (v != null)
      res.setValue(parseIntegerPrimitive(v));
    return res;
  }

  private DateTime parseDateTime(String v) throws Exception {
    DateTime res = new DateTime();
    if (v != null)
      res.setValue(parseDateTimePrimitive(v));
    return res;
  }

  private Code parseCode(String v) throws Exception {
    Code res = new Code();
    if (v != null)
      res.setValue(parseCodePrimitive(v));
    return res;
  }

  private Date parseDate(String v) throws Exception {
    Date res = new Date();
    if (v != null)
      res.setValue(parseDatePrimitive(v));
    return res;
  }

  private Decimal parseDecimal(String v) throws Exception {
    Decimal res = new Decimal();
    if (v != null)
      res.setValue(parseDecimalPrimitive(v));
    return res;
  }

  private Uri parseUri(String v) throws Exception {
    Uri res = new Uri();
    if (v != null)
      res.setValue(parseUriPrimitive(v));
    return res;
  }

  private Id parseId(String v) throws Exception {
    Id res = new Id();
    if (v != null)
      res.setValue(parseIdPrimitive(v));
    return res;
  }

  private Base64Binary parseBase64Binary(String v) throws Exception {
    Base64Binary res = new Base64Binary();
    if (v != null)
      res.setValue(parseBase64BinaryPrimitive(v));
    return res;
  }

  private Oid parseOid(String v) throws Exception {
    Oid res = new Oid();
    if (v != null)
      res.setValue(parseOidPrimitive(v));
    return res;
  }

  private String_ parseString(String v) throws Exception {
    String_ res = new String_();
    if (v != null)
      res.setValue(parseStringPrimitive(v));
    return res;
  }

  private Boolean parseBoolean(java.lang.Boolean v) throws Exception {
    Boolean res = new Boolean();
    if (v != null)
      res.setValue(parseBooleanPrimitive(v));
    return res;
  }

  private Uuid parseUuid(String v) throws Exception {
    Uuid res = new Uuid();
    if (v != null)
      res.setValue(parseUuidPrimitive(v));
    return res;
  }

  private Instant parseInstant(String v) throws Exception {
    Instant res = new Instant();
    if (v != null)
      res.setValue(parseInstantPrimitive(v));
    return res;
  }

  private Extension parseExtension(JSONObject json) throws Exception {
    Extension res = new Extension();
    parseElementProperties(json, res);
    if (json.has("url"))
      res.setUrl(parseUri(json.getString("url")));
    if (json.has("_url"))
      parseElementProperties(json.getJSONObject("_url"), res.getUrl());
    if (json.has("isModifier"))
      res.setIsModifier(parseBoolean(json.getBoolean("isModifier")));
    if (json.has("_isModifier"))
      parseElementProperties(json.getJSONObject("_isModifier"), res.getIsModifier());
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    return res;
  }

  private Narrative parseNarrative(JSONObject json) throws Exception {
    Narrative res = new Narrative();
    parseElementProperties(json, res);
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Narrative.NarrativeStatus.Null, new Narrative().new NarrativeStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("div"))
      res.setDiv(parseXhtml(json.getString("div")));
    return res;
  }

  private Period parsePeriod(JSONObject json) throws Exception {
    Period res = new Period();
    parseTypeProperties(json, res);
    if (json.has("start"))
      res.setStart(parseDateTime(json.getString("start")));
    if (json.has("_start"))
      parseElementProperties(json.getJSONObject("_start"), res.getStart());
    if (json.has("end"))
      res.setEnd(parseDateTime(json.getString("end")));
    if (json.has("_end"))
      parseElementProperties(json.getJSONObject("_end"), res.getEnd());
    return res;
  }

  private Coding parseCoding(JSONObject json) throws Exception {
    Coding res = new Coding();
    parseTypeProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("display"))
      res.setDisplay(parseString(json.getString("display")));
    if (json.has("_display"))
      parseElementProperties(json.getJSONObject("_display"), res.getDisplay());
    if (json.has("primary"))
      res.setPrimary(parseBoolean(json.getBoolean("primary")));
    if (json.has("_primary"))
      parseElementProperties(json.getJSONObject("_primary"), res.getPrimary());
    if (json.has("valueSet"))
      res.setValueSet(parseResourceReference(json.getJSONObject("valueSet")));
    return res;
  }

  private Range parseRange(JSONObject json) throws Exception {
    Range res = new Range();
    parseTypeProperties(json, res);
    if (json.has("low"))
      res.setLow(parseQuantity(json.getJSONObject("low")));
    if (json.has("high"))
      res.setHigh(parseQuantity(json.getJSONObject("high")));
    return res;
  }

  private Quantity parseQuantity(JSONObject json) throws Exception {
    Quantity res = new Quantity();
    parseTypeProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.getString("comparator"), Quantity.QuantityComparator.Null, new Quantity().new QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getJSONObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.getString("units")));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    return res;
  }

  private Attachment parseAttachment(JSONObject json) throws Exception {
    Attachment res = new Attachment();
    parseTypeProperties(json, res);
    if (json.has("contentType"))
      res.setContentType(parseCode(json.getString("contentType")));
    if (json.has("_contentType"))
      parseElementProperties(json.getJSONObject("_contentType"), res.getContentType());
    if (json.has("language"))
      res.setLanguage(parseCode(json.getString("language")));
    if (json.has("_language"))
      parseElementProperties(json.getJSONObject("_language"), res.getLanguage());
    if (json.has("data"))
      res.setData(parseBase64Binary(json.getString("data")));
    if (json.has("_data"))
      parseElementProperties(json.getJSONObject("_data"), res.getData());
    if (json.has("url"))
      res.setUrl(parseUri(json.getString("url")));
    if (json.has("_url"))
      parseElementProperties(json.getJSONObject("_url"), res.getUrl());
    if (json.has("size"))
      res.setSize(parseInteger(json.getString("size")));
    if (json.has("_size"))
      parseElementProperties(json.getJSONObject("_size"), res.getSize());
    if (json.has("hash"))
      res.setHash(parseBase64Binary(json.getString("hash")));
    if (json.has("_hash"))
      parseElementProperties(json.getJSONObject("_hash"), res.getHash());
    if (json.has("title"))
      res.setTitle(parseString(json.getString("title")));
    if (json.has("_title"))
      parseElementProperties(json.getJSONObject("_title"), res.getTitle());
    return res;
  }

  private Ratio parseRatio(JSONObject json) throws Exception {
    Ratio res = new Ratio();
    parseTypeProperties(json, res);
    if (json.has("numerator"))
      res.setNumerator(parseQuantity(json.getJSONObject("numerator")));
    if (json.has("denominator"))
      res.setDenominator(parseQuantity(json.getJSONObject("denominator")));
    return res;
  }

  private SampledData parseSampledData(JSONObject json) throws Exception {
    SampledData res = new SampledData();
    parseTypeProperties(json, res);
    if (json.has("origin"))
      res.setOrigin(parseQuantity(json.getJSONObject("origin")));
    if (json.has("period"))
      res.setPeriod(parseDecimal(json.getString("period")));
    if (json.has("_period"))
      parseElementProperties(json.getJSONObject("_period"), res.getPeriod());
    if (json.has("factor"))
      res.setFactor(parseDecimal(json.getString("factor")));
    if (json.has("_factor"))
      parseElementProperties(json.getJSONObject("_factor"), res.getFactor());
    if (json.has("lowerLimit"))
      res.setLowerLimit(parseDecimal(json.getString("lowerLimit")));
    if (json.has("_lowerLimit"))
      parseElementProperties(json.getJSONObject("_lowerLimit"), res.getLowerLimit());
    if (json.has("upperLimit"))
      res.setUpperLimit(parseDecimal(json.getString("upperLimit")));
    if (json.has("_upperLimit"))
      parseElementProperties(json.getJSONObject("_upperLimit"), res.getUpperLimit());
    if (json.has("dimensions"))
      res.setDimensions(parseInteger(json.getString("dimensions")));
    if (json.has("_dimensions"))
      parseElementProperties(json.getJSONObject("_dimensions"), res.getDimensions());
    if (json.has("data"))
      res.setData(parseString(json.getString("data")));
    if (json.has("_data"))
      parseElementProperties(json.getJSONObject("_data"), res.getData());
    return res;
  }

  private ResourceReference parseResourceReference(JSONObject json) throws Exception {
    ResourceReference res = new ResourceReference();
    parseTypeProperties(json, res);
    if (json.has("reference"))
      res.setReference(parseString(json.getString("reference")));
    if (json.has("_reference"))
      parseElementProperties(json.getJSONObject("_reference"), res.getReference());
    if (json.has("display"))
      res.setDisplay(parseString(json.getString("display")));
    if (json.has("_display"))
      parseElementProperties(json.getJSONObject("_display"), res.getDisplay());
    return res;
  }

  private CodeableConcept parseCodeableConcept(JSONObject json) throws Exception {
    CodeableConcept res = new CodeableConcept();
    parseTypeProperties(json, res);
    if (json.has("coding")) {
      JSONArray array = json.getJSONArray("coding");
      for (int i = 0; i < array.length(); i++) {
        res.getCoding().add(parseCoding(array.getJSONObject(i)));
      }
    };
    if (json.has("text"))
      res.setText(parseString(json.getString("text")));
    if (json.has("_text"))
      parseElementProperties(json.getJSONObject("_text"), res.getText());
    return res;
  }

  private Identifier parseIdentifier(JSONObject json) throws Exception {
    Identifier res = new Identifier();
    parseTypeProperties(json, res);
    if (json.has("use"))
      res.setUse(parseEnumeration(json.getString("use"), Identifier.IdentifierUse.Null, new Identifier().new IdentifierUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getJSONObject("_use"), res.getUse());
    if (json.has("label"))
      res.setLabel(parseString(json.getString("label")));
    if (json.has("_label"))
      parseElementProperties(json.getJSONObject("_label"), res.getLabel());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("value"))
      res.setValue(parseString(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("assigner"))
      res.setAssigner(parseResourceReference(json.getJSONObject("assigner")));
    return res;
  }

  private Age parseAge(JSONObject json) throws Exception {
    Age res = new Age();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.getString("comparator"), Quantity.QuantityComparator.Null, new Quantity().new QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getJSONObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.getString("units")));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    return res;
  }

  private Count parseCount(JSONObject json) throws Exception {
    Count res = new Count();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.getString("comparator"), Quantity.QuantityComparator.Null, new Quantity().new QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getJSONObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.getString("units")));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    return res;
  }

  private Money parseMoney(JSONObject json) throws Exception {
    Money res = new Money();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.getString("comparator"), Quantity.QuantityComparator.Null, new Quantity().new QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getJSONObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.getString("units")));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    return res;
  }

  private Distance parseDistance(JSONObject json) throws Exception {
    Distance res = new Distance();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.getString("comparator"), Quantity.QuantityComparator.Null, new Quantity().new QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getJSONObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.getString("units")));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    return res;
  }

  private Duration parseDuration(JSONObject json) throws Exception {
    Duration res = new Duration();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.getString("comparator"), Quantity.QuantityComparator.Null, new Quantity().new QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getJSONObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.getString("units")));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    return res;
  }

  private Schedule parseSchedule(JSONObject json) throws Exception {
    Schedule res = new Schedule();
    parseElementProperties(json, res);
    if (json.has("event")) {
      JSONArray array = json.getJSONArray("event");
      for (int i = 0; i < array.length(); i++) {
        res.getEvent().add(parsePeriod(array.getJSONObject(i)));
      }
    };
    if (json.has("repeat"))
      res.setRepeat(parseScheduleScheduleRepeatComponent(json.getJSONObject("repeat"), res));
    return res;
  }

  private Schedule.ScheduleRepeatComponent parseScheduleScheduleRepeatComponent(JSONObject json, Schedule owner) throws Exception {
    Schedule.ScheduleRepeatComponent res = owner.new ScheduleRepeatComponent();
    parseElementProperties(json, res);
    if (json.has("frequency"))
      res.setFrequency(parseInteger(json.getString("frequency")));
    if (json.has("_frequency"))
      parseElementProperties(json.getJSONObject("_frequency"), res.getFrequency());
    if (json.has("when"))
      res.setWhen(parseEnumeration(json.getString("when"), Schedule.EventTiming.Null, new Schedule().new EventTimingEnumFactory()));
    if (json.has("_when"))
      parseElementProperties(json.getJSONObject("_when"), res.getWhen());
    if (json.has("duration"))
      res.setDuration(parseDecimal(json.getString("duration")));
    if (json.has("_duration"))
      parseElementProperties(json.getJSONObject("_duration"), res.getDuration());
    if (json.has("units"))
      res.setUnits(parseEnumeration(json.getString("units"), Schedule.UnitsOfTime.Null, new Schedule().new UnitsOfTimeEnumFactory()));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("count"))
      res.setCount(parseInteger(json.getString("count")));
    if (json.has("_count"))
      parseElementProperties(json.getJSONObject("_count"), res.getCount());
    if (json.has("end"))
      res.setEnd(parseDateTime(json.getString("end")));
    if (json.has("_end"))
      parseElementProperties(json.getJSONObject("_end"), res.getEnd());
    return res;
  }

  private Contact parseContact(JSONObject json) throws Exception {
    Contact res = new Contact();
    parseElementProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseEnumeration(json.getString("system"), Contact.ContactSystem.Null, new Contact().new ContactSystemEnumFactory()));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("value"))
      res.setValue(parseString(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("use"))
      res.setUse(parseEnumeration(json.getString("use"), Contact.ContactUse.Null, new Contact().new ContactUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getJSONObject("_use"), res.getUse());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    return res;
  }

  private Address parseAddress(JSONObject json) throws Exception {
    Address res = new Address();
    parseElementProperties(json, res);
    if (json.has("use"))
      res.setUse(parseEnumeration(json.getString("use"), Address.AddressUse.Null, new Address().new AddressUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getJSONObject("_use"), res.getUse());
    if (json.has("text"))
      res.setText(parseString(json.getString("text")));
    if (json.has("_text"))
      parseElementProperties(json.getJSONObject("_text"), res.getText());
    if (json.has("line")) {
      JSONArray array = json.getJSONArray("line");
      for (int i = 0; i < array.length(); i++) {
        res.getLine().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_line")) {
      JSONArray array = json.getJSONArray("_line");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getLine().size())
          res.getLine().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getLine().get(i));
      }
    };
    if (json.has("city"))
      res.setCity(parseString(json.getString("city")));
    if (json.has("_city"))
      parseElementProperties(json.getJSONObject("_city"), res.getCity());
    if (json.has("state"))
      res.setState(parseString(json.getString("state")));
    if (json.has("_state"))
      parseElementProperties(json.getJSONObject("_state"), res.getState());
    if (json.has("zip"))
      res.setZip(parseString(json.getString("zip")));
    if (json.has("_zip"))
      parseElementProperties(json.getJSONObject("_zip"), res.getZip());
    if (json.has("country"))
      res.setCountry(parseString(json.getString("country")));
    if (json.has("_country"))
      parseElementProperties(json.getJSONObject("_country"), res.getCountry());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    return res;
  }

  private HumanName parseHumanName(JSONObject json) throws Exception {
    HumanName res = new HumanName();
    parseElementProperties(json, res);
    if (json.has("use"))
      res.setUse(parseEnumeration(json.getString("use"), HumanName.NameUse.Null, new HumanName().new NameUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getJSONObject("_use"), res.getUse());
    if (json.has("text"))
      res.setText(parseString(json.getString("text")));
    if (json.has("_text"))
      parseElementProperties(json.getJSONObject("_text"), res.getText());
    if (json.has("family")) {
      JSONArray array = json.getJSONArray("family");
      for (int i = 0; i < array.length(); i++) {
        res.getFamily().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_family")) {
      JSONArray array = json.getJSONArray("_family");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getFamily().size())
          res.getFamily().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getFamily().get(i));
      }
    };
    if (json.has("given")) {
      JSONArray array = json.getJSONArray("given");
      for (int i = 0; i < array.length(); i++) {
        res.getGiven().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_given")) {
      JSONArray array = json.getJSONArray("_given");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getGiven().size())
          res.getGiven().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getGiven().get(i));
      }
    };
    if (json.has("prefix")) {
      JSONArray array = json.getJSONArray("prefix");
      for (int i = 0; i < array.length(); i++) {
        res.getPrefix().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_prefix")) {
      JSONArray array = json.getJSONArray("_prefix");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getPrefix().size())
          res.getPrefix().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getPrefix().get(i));
      }
    };
    if (json.has("suffix")) {
      JSONArray array = json.getJSONArray("suffix");
      for (int i = 0; i < array.length(); i++) {
        res.getSuffix().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_suffix")) {
      JSONArray array = json.getJSONArray("_suffix");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getSuffix().size())
          res.getSuffix().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getSuffix().get(i));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    return res;
  }

  protected void parseResourceProperties(JSONObject json, Resource res) throws Exception {
    parseElementProperties(json, res); 
    if (json.has("language"))
      res.setLanguage(parseCode(json.getString("language")));
    if (json.has("_language"))
      parseElementProperties(json.getJSONObject("_language"), res.getLanguage());
    if (json.has("text"))
      res.setText(parseNarrative(json.getJSONObject("text")));
    if (json.has("contained")) {
      JSONArray array = json.getJSONArray("contained");
      for (int i = 0; i < array.length(); i++) {
        res.getContained().add(parseResource(array.getJSONObject(i)));
      }
    };
  }

  private AdverseReaction parseAdverseReaction(JSONObject json) throws Exception {
    AdverseReaction res = new AdverseReaction();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("reactionDate"))
      res.setReactionDate(parseDateTime(json.getString("reactionDate")));
    if (json.has("_reactionDate"))
      parseElementProperties(json.getJSONObject("_reactionDate"), res.getReactionDate());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("didNotOccurFlag"))
      res.setDidNotOccurFlag(parseBoolean(json.getBoolean("didNotOccurFlag")));
    if (json.has("_didNotOccurFlag"))
      parseElementProperties(json.getJSONObject("_didNotOccurFlag"), res.getDidNotOccurFlag());
    if (json.has("recorder"))
      res.setRecorder(parseResourceReference(json.getJSONObject("recorder")));
    if (json.has("symptom")) {
      JSONArray array = json.getJSONArray("symptom");
      for (int i = 0; i < array.length(); i++) {
        res.getSymptom().add(parseAdverseReactionAdverseReactionSymptomComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("exposure")) {
      JSONArray array = json.getJSONArray("exposure");
      for (int i = 0; i < array.length(); i++) {
        res.getExposure().add(parseAdverseReactionAdverseReactionExposureComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private AdverseReaction.AdverseReactionSymptomComponent parseAdverseReactionAdverseReactionSymptomComponent(JSONObject json, AdverseReaction owner) throws Exception {
    AdverseReaction.AdverseReactionSymptomComponent res = owner.new AdverseReactionSymptomComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("severity"))
      res.setSeverity(parseEnumeration(json.getString("severity"), AdverseReaction.ReactionSeverity.Null, new AdverseReaction().new ReactionSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getJSONObject("_severity"), res.getSeverity());
    return res;
  }

  private AdverseReaction.AdverseReactionExposureComponent parseAdverseReactionAdverseReactionExposureComponent(JSONObject json, AdverseReaction owner) throws Exception {
    AdverseReaction.AdverseReactionExposureComponent res = owner.new AdverseReactionExposureComponent();
    parseElementProperties(json, res);
    if (json.has("exposureDate"))
      res.setExposureDate(parseDateTime(json.getString("exposureDate")));
    if (json.has("_exposureDate"))
      parseElementProperties(json.getJSONObject("_exposureDate"), res.getExposureDate());
    if (json.has("exposureType"))
      res.setExposureType(parseEnumeration(json.getString("exposureType"), AdverseReaction.ExposureType.Null, new AdverseReaction().new ExposureTypeEnumFactory()));
    if (json.has("_exposureType"))
      parseElementProperties(json.getJSONObject("_exposureType"), res.getExposureType());
    if (json.has("causalityExpectation"))
      res.setCausalityExpectation(parseEnumeration(json.getString("causalityExpectation"), AdverseReaction.CausalityExpectation.Null, new AdverseReaction().new CausalityExpectationEnumFactory()));
    if (json.has("_causalityExpectation"))
      parseElementProperties(json.getJSONObject("_causalityExpectation"), res.getCausalityExpectation());
    if (json.has("substance"))
      res.setSubstance(parseResourceReference(json.getJSONObject("substance")));
    return res;
  }

  private Alert parseAlert(JSONObject json) throws Exception {
    Alert res = new Alert();
    parseResourceProperties(json, res);
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getJSONObject("category")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Alert.AlertStatus.Null, new Alert().new AlertStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getJSONObject("author")));
    if (json.has("note"))
      res.setNote(parseString(json.getString("note")));
    if (json.has("_note"))
      parseElementProperties(json.getJSONObject("_note"), res.getNote());
    return res;
  }

  private AllergyIntolerance parseAllergyIntolerance(JSONObject json) throws Exception {
    AllergyIntolerance res = new AllergyIntolerance();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("criticality"))
      res.setCriticality(parseEnumeration(json.getString("criticality"), AllergyIntolerance.Criticality.Null, new AllergyIntolerance().new CriticalityEnumFactory()));
    if (json.has("_criticality"))
      parseElementProperties(json.getJSONObject("_criticality"), res.getCriticality());
    if (json.has("sensitivityType"))
      res.setSensitivityType(parseEnumeration(json.getString("sensitivityType"), AllergyIntolerance.Sensitivitytype.Null, new AllergyIntolerance().new SensitivitytypeEnumFactory()));
    if (json.has("_sensitivityType"))
      parseElementProperties(json.getJSONObject("_sensitivityType"), res.getSensitivityType());
    if (json.has("recordedDate"))
      res.setRecordedDate(parseDateTime(json.getString("recordedDate")));
    if (json.has("_recordedDate"))
      parseElementProperties(json.getJSONObject("_recordedDate"), res.getRecordedDate());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), AllergyIntolerance.Sensitivitystatus.Null, new AllergyIntolerance().new SensitivitystatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("recorder"))
      res.setRecorder(parseResourceReference(json.getJSONObject("recorder")));
    if (json.has("substance"))
      res.setSubstance(parseResourceReference(json.getJSONObject("substance")));
    if (json.has("reaction")) {
      JSONArray array = json.getJSONArray("reaction");
      for (int i = 0; i < array.length(); i++) {
        res.getReaction().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("sensitivityTest")) {
      JSONArray array = json.getJSONArray("sensitivityTest");
      for (int i = 0; i < array.length(); i++) {
        res.getSensitivityTest().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private CarePlan parseCarePlan(JSONObject json) throws Exception {
    CarePlan res = new CarePlan();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), CarePlan.CarePlanStatus.Null, new CarePlan().new CarePlanStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("modified"))
      res.setModified(parseDateTime(json.getString("modified")));
    if (json.has("_modified"))
      parseElementProperties(json.getJSONObject("_modified"), res.getModified());
    if (json.has("concern")) {
      JSONArray array = json.getJSONArray("concern");
      for (int i = 0; i < array.length(); i++) {
        res.getConcern().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("participant")) {
      JSONArray array = json.getJSONArray("participant");
      for (int i = 0; i < array.length(); i++) {
        res.getParticipant().add(parseCarePlanCarePlanParticipantComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("goal")) {
      JSONArray array = json.getJSONArray("goal");
      for (int i = 0; i < array.length(); i++) {
        res.getGoal().add(parseCarePlanCarePlanGoalComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("activity")) {
      JSONArray array = json.getJSONArray("activity");
      for (int i = 0; i < array.length(); i++) {
        res.getActivity().add(parseCarePlanCarePlanActivityComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.getString("notes")));
    if (json.has("_notes"))
      parseElementProperties(json.getJSONObject("_notes"), res.getNotes());
    return res;
  }

  private CarePlan.CarePlanParticipantComponent parseCarePlanCarePlanParticipantComponent(JSONObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanParticipantComponent res = owner.new CarePlanParticipantComponent();
    parseElementProperties(json, res);
    if (json.has("role"))
      res.setRole(parseCodeableConcept(json.getJSONObject("role")));
    if (json.has("member"))
      res.setMember(parseResourceReference(json.getJSONObject("member")));
    return res;
  }

  private CarePlan.CarePlanGoalComponent parseCarePlanCarePlanGoalComponent(JSONObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanGoalComponent res = owner.new CarePlanGoalComponent();
    parseElementProperties(json, res);
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), CarePlan.CarePlanGoalStatus.Null, new CarePlan().new CarePlanGoalStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("notes"))
      res.setNotes(parseString(json.getString("notes")));
    if (json.has("_notes"))
      parseElementProperties(json.getJSONObject("_notes"), res.getNotes());
    if (json.has("concern")) {
      JSONArray array = json.getJSONArray("concern");
      for (int i = 0; i < array.length(); i++) {
        res.getConcern().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private CarePlan.CarePlanActivityComponent parseCarePlanCarePlanActivityComponent(JSONObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivityComponent res = owner.new CarePlanActivityComponent();
    parseElementProperties(json, res);
    if (json.has("goal")) {
      JSONArray array = json.getJSONArray("goal");
      for (int i = 0; i < array.length(); i++) {
        res.getGoal().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_goal")) {
      JSONArray array = json.getJSONArray("_goal");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getGoal().size())
          res.getGoal().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getGoal().get(i));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), CarePlan.CarePlanActivityStatus.Null, new CarePlan().new CarePlanActivityStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("prohibited"))
      res.setProhibited(parseBoolean(json.getBoolean("prohibited")));
    if (json.has("_prohibited"))
      parseElementProperties(json.getJSONObject("_prohibited"), res.getProhibited());
    if (json.has("actionResulting")) {
      JSONArray array = json.getJSONArray("actionResulting");
      for (int i = 0; i < array.length(); i++) {
        res.getActionResulting().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.getString("notes")));
    if (json.has("_notes"))
      parseElementProperties(json.getJSONObject("_notes"), res.getNotes());
    if (json.has("detail"))
      res.setDetail(parseResourceReference(json.getJSONObject("detail")));
    if (json.has("simple"))
      res.setSimple(parseCarePlanCarePlanActivitySimpleComponent(json.getJSONObject("simple"), owner));
    return res;
  }

  private CarePlan.CarePlanActivitySimpleComponent parseCarePlanCarePlanActivitySimpleComponent(JSONObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivitySimpleComponent res = owner.new CarePlanActivitySimpleComponent();
    parseElementProperties(json, res);
    if (json.has("category"))
      res.setCategory(parseEnumeration(json.getString("category"), CarePlan.CarePlanActivityCategory.Null, new CarePlan().new CarePlanActivityCategoryEnumFactory()));
    if (json.has("_category"))
      parseElementProperties(json.getJSONObject("_category"), res.getCategory());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getJSONObject("location")));
    if (json.has("performer")) {
      JSONArray array = json.getJSONArray("performer");
      for (int i = 0; i < array.length(); i++) {
        res.getPerformer().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("product"))
      res.setProduct(parseResourceReference(json.getJSONObject("product")));
    if (json.has("dailyAmount"))
      res.setDailyAmount(parseQuantity(json.getJSONObject("dailyAmount")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("details"))
      res.setDetails(parseString(json.getString("details")));
    if (json.has("_details"))
      parseElementProperties(json.getJSONObject("_details"), res.getDetails());
    return res;
  }

  private ConceptMap parseConceptMap(JSONObject json) throws Exception {
    ConceptMap res = new ConceptMap();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.getString("publisher")));
    if (json.has("_publisher"))
      parseElementProperties(json.getJSONObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("copyright"))
      res.setCopyright(parseString(json.getString("copyright")));
    if (json.has("_copyright"))
      parseElementProperties(json.getJSONObject("_copyright"), res.getCopyright());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), ConceptMap.ValuesetStatus.Null, new ConceptMap().new ValuesetStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.getBoolean("experimental")));
    if (json.has("_experimental"))
      parseElementProperties(json.getJSONObject("_experimental"), res.getExperimental());
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getJSONObject("source")));
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getJSONObject("target")));
    if (json.has("concept")) {
      JSONArray array = json.getJSONArray("concept");
      for (int i = 0; i < array.length(); i++) {
        res.getConcept().add(parseConceptMapConceptMapConceptComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private ConceptMap.ConceptMapConceptComponent parseConceptMapConceptMapConceptComponent(JSONObject json, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapConceptComponent res = owner.new ConceptMapConceptComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("map")) {
      JSONArray array = json.getJSONArray("map");
      for (int i = 0; i < array.length(); i++) {
        res.getMap().add(parseConceptMapConceptMapConceptMapComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("concept")) {
      JSONArray array = json.getJSONArray("concept");
      for (int i = 0; i < array.length(); i++) {
        res.getConcept().add(parseConceptMapConceptMapConceptComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private ConceptMap.ConceptMapConceptMapComponent parseConceptMapConceptMapConceptMapComponent(JSONObject json, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapConceptMapComponent res = owner.new ConceptMapConceptMapComponent();
    parseElementProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseString(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("equivalence"))
      res.setEquivalence(parseEnumeration(json.getString("equivalence"), ConceptMap.ConceptEquivalence.Null, new ConceptMap().new ConceptEquivalenceEnumFactory()));
    if (json.has("_equivalence"))
      parseElementProperties(json.getJSONObject("_equivalence"), res.getEquivalence());
    if (json.has("comments"))
      res.setComments(parseString(json.getString("comments")));
    if (json.has("_comments"))
      parseElementProperties(json.getJSONObject("_comments"), res.getComments());
    return res;
  }

  private Condition parseCondition(JSONObject json) throws Exception {
    Condition res = new Condition();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    if (json.has("asserter"))
      res.setAsserter(parseResourceReference(json.getJSONObject("asserter")));
    if (json.has("dateAsserted"))
      res.setDateAsserted(parseDate(json.getString("dateAsserted")));
    if (json.has("_dateAsserted"))
      parseElementProperties(json.getJSONObject("_dateAsserted"), res.getDateAsserted());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getJSONObject("category")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Condition.ConditionStatus.Null, new Condition().new ConditionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("certainty"))
      res.setCertainty(parseCodeableConcept(json.getJSONObject("certainty")));
    if (json.has("severity"))
      res.setSeverity(parseCodeableConcept(json.getJSONObject("severity")));
    Type onset = parseType("onset", json);
    if (onset != null)
      res.setOnset(onset);
    Type abatement = parseType("abatement", json);
    if (abatement != null)
      res.setAbatement(abatement);
    if (json.has("stage"))
      res.setStage(parseConditionConditionStageComponent(json.getJSONObject("stage"), res));
    if (json.has("evidence")) {
      JSONArray array = json.getJSONArray("evidence");
      for (int i = 0; i < array.length(); i++) {
        res.getEvidence().add(parseConditionConditionEvidenceComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("location")) {
      JSONArray array = json.getJSONArray("location");
      for (int i = 0; i < array.length(); i++) {
        res.getLocation().add(parseConditionConditionLocationComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("relatedItem")) {
      JSONArray array = json.getJSONArray("relatedItem");
      for (int i = 0; i < array.length(); i++) {
        res.getRelatedItem().add(parseConditionConditionRelatedItemComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.getString("notes")));
    if (json.has("_notes"))
      parseElementProperties(json.getJSONObject("_notes"), res.getNotes());
    return res;
  }

  private Condition.ConditionStageComponent parseConditionConditionStageComponent(JSONObject json, Condition owner) throws Exception {
    Condition.ConditionStageComponent res = owner.new ConditionStageComponent();
    parseElementProperties(json, res);
    if (json.has("summary"))
      res.setSummary(parseCodeableConcept(json.getJSONObject("summary")));
    if (json.has("assessment")) {
      JSONArray array = json.getJSONArray("assessment");
      for (int i = 0; i < array.length(); i++) {
        res.getAssessment().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Condition.ConditionEvidenceComponent parseConditionConditionEvidenceComponent(JSONObject json, Condition owner) throws Exception {
    Condition.ConditionEvidenceComponent res = owner.new ConditionEvidenceComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("detail")) {
      JSONArray array = json.getJSONArray("detail");
      for (int i = 0; i < array.length(); i++) {
        res.getDetail().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Condition.ConditionLocationComponent parseConditionConditionLocationComponent(JSONObject json, Condition owner) throws Exception {
    Condition.ConditionLocationComponent res = owner.new ConditionLocationComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("detail"))
      res.setDetail(parseString(json.getString("detail")));
    if (json.has("_detail"))
      parseElementProperties(json.getJSONObject("_detail"), res.getDetail());
    return res;
  }

  private Condition.ConditionRelatedItemComponent parseConditionConditionRelatedItemComponent(JSONObject json, Condition owner) throws Exception {
    Condition.ConditionRelatedItemComponent res = owner.new ConditionRelatedItemComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), Condition.ConditionRelationshipType.Null, new Condition().new ConditionRelationshipTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getJSONObject("target")));
    return res;
  }

  private Conformance parseConformance(JSONObject json) throws Exception {
    Conformance res = new Conformance();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.getString("publisher")));
    if (json.has("_publisher"))
      parseElementProperties(json.getJSONObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Conformance.ConformanceStatementStatus.Null, new Conformance().new ConformanceStatementStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.getBoolean("experimental")));
    if (json.has("_experimental"))
      parseElementProperties(json.getJSONObject("_experimental"), res.getExperimental());
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("software"))
      res.setSoftware(parseConformanceConformanceSoftwareComponent(json.getJSONObject("software"), res));
    if (json.has("implementation"))
      res.setImplementation(parseConformanceConformanceImplementationComponent(json.getJSONObject("implementation"), res));
    if (json.has("fhirVersion"))
      res.setFhirVersion(parseId(json.getString("fhirVersion")));
    if (json.has("_fhirVersion"))
      parseElementProperties(json.getJSONObject("_fhirVersion"), res.getFhirVersion());
    if (json.has("acceptUnknown"))
      res.setAcceptUnknown(parseBoolean(json.getBoolean("acceptUnknown")));
    if (json.has("_acceptUnknown"))
      parseElementProperties(json.getJSONObject("_acceptUnknown"), res.getAcceptUnknown());
    if (json.has("format")) {
      JSONArray array = json.getJSONArray("format");
      for (int i = 0; i < array.length(); i++) {
        res.getFormat().add(parseCode(array.getString(i)));
      }
    };
    if (json.has("_format")) {
      JSONArray array = json.getJSONArray("_format");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getFormat().size())
          res.getFormat().add(parseCode(null));
        parseElementProperties(array.getJSONObject(i), res.getFormat().get(i));
      }
    };
    if (json.has("profile")) {
      JSONArray array = json.getJSONArray("profile");
      for (int i = 0; i < array.length(); i++) {
        res.getProfile().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("rest")) {
      JSONArray array = json.getJSONArray("rest");
      for (int i = 0; i < array.length(); i++) {
        res.getRest().add(parseConformanceConformanceRestComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("messaging")) {
      JSONArray array = json.getJSONArray("messaging");
      for (int i = 0; i < array.length(); i++) {
        res.getMessaging().add(parseConformanceConformanceMessagingComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("document")) {
      JSONArray array = json.getJSONArray("document");
      for (int i = 0; i < array.length(); i++) {
        res.getDocument().add(parseConformanceConformanceDocumentComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private Conformance.ConformanceSoftwareComponent parseConformanceConformanceSoftwareComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceSoftwareComponent res = owner.new ConformanceSoftwareComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("releaseDate"))
      res.setReleaseDate(parseDateTime(json.getString("releaseDate")));
    if (json.has("_releaseDate"))
      parseElementProperties(json.getJSONObject("_releaseDate"), res.getReleaseDate());
    return res;
  }

  private Conformance.ConformanceImplementationComponent parseConformanceConformanceImplementationComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceImplementationComponent res = owner.new ConformanceImplementationComponent();
    parseElementProperties(json, res);
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("url"))
      res.setUrl(parseUri(json.getString("url")));
    if (json.has("_url"))
      parseElementProperties(json.getJSONObject("_url"), res.getUrl());
    return res;
  }

  private Conformance.ConformanceRestComponent parseConformanceConformanceRestComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestComponent res = owner.new ConformanceRestComponent();
    parseElementProperties(json, res);
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.getString("mode"), Conformance.RestfulConformanceMode.Null, new Conformance().new RestfulConformanceModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getJSONObject("_mode"), res.getMode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    if (json.has("security"))
      res.setSecurity(parseConformanceConformanceRestSecurityComponent(json.getJSONObject("security"), owner));
    if (json.has("resource")) {
      JSONArray array = json.getJSONArray("resource");
      for (int i = 0; i < array.length(); i++) {
        res.getResource().add(parseConformanceConformanceRestResourceComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("operation")) {
      JSONArray array = json.getJSONArray("operation");
      for (int i = 0; i < array.length(); i++) {
        res.getOperation().add(parseConformanceConformanceRestOperationComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("query")) {
      JSONArray array = json.getJSONArray("query");
      for (int i = 0; i < array.length(); i++) {
        res.getQuery().add(parseConformanceConformanceRestQueryComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Conformance.ConformanceRestSecurityComponent parseConformanceConformanceRestSecurityComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityComponent res = owner.new ConformanceRestSecurityComponent();
    parseElementProperties(json, res);
    if (json.has("cors"))
      res.setCors(parseBoolean(json.getBoolean("cors")));
    if (json.has("_cors"))
      parseElementProperties(json.getJSONObject("_cors"), res.getCors());
    if (json.has("service")) {
      JSONArray array = json.getJSONArray("service");
      for (int i = 0; i < array.length(); i++) {
        res.getService().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("certificate")) {
      JSONArray array = json.getJSONArray("certificate");
      for (int i = 0; i < array.length(); i++) {
        res.getCertificate().add(parseConformanceConformanceRestSecurityCertificateComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Conformance.ConformanceRestSecurityCertificateComponent parseConformanceConformanceRestSecurityCertificateComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityCertificateComponent res = owner.new ConformanceRestSecurityCertificateComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCode(json.getString("type")));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("blob"))
      res.setBlob(parseBase64Binary(json.getString("blob")));
    if (json.has("_blob"))
      parseElementProperties(json.getJSONObject("_blob"), res.getBlob());
    return res;
  }

  private Conformance.ConformanceRestResourceComponent parseConformanceConformanceRestResourceComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceComponent res = owner.new ConformanceRestResourceComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCode(json.getString("type")));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("profile"))
      res.setProfile(parseResourceReference(json.getJSONObject("profile")));
    if (json.has("operation")) {
      JSONArray array = json.getJSONArray("operation");
      for (int i = 0; i < array.length(); i++) {
        res.getOperation().add(parseConformanceConformanceRestResourceOperationComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("readHistory"))
      res.setReadHistory(parseBoolean(json.getBoolean("readHistory")));
    if (json.has("_readHistory"))
      parseElementProperties(json.getJSONObject("_readHistory"), res.getReadHistory());
    if (json.has("updateCreate"))
      res.setUpdateCreate(parseBoolean(json.getBoolean("updateCreate")));
    if (json.has("_updateCreate"))
      parseElementProperties(json.getJSONObject("_updateCreate"), res.getUpdateCreate());
    if (json.has("searchInclude")) {
      JSONArray array = json.getJSONArray("searchInclude");
      for (int i = 0; i < array.length(); i++) {
        res.getSearchInclude().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_searchInclude")) {
      JSONArray array = json.getJSONArray("_searchInclude");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getSearchInclude().size())
          res.getSearchInclude().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getSearchInclude().get(i));
      }
    };
    if (json.has("searchParam")) {
      JSONArray array = json.getJSONArray("searchParam");
      for (int i = 0; i < array.length(); i++) {
        res.getSearchParam().add(parseConformanceConformanceRestResourceSearchParamComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Conformance.ConformanceRestResourceOperationComponent parseConformanceConformanceRestResourceOperationComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceOperationComponent res = owner.new ConformanceRestResourceOperationComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseEnumeration(json.getString("code"), Conformance.TypeRestfulOperation.Null, new Conformance().new TypeRestfulOperationEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    return res;
  }

  private Conformance.ConformanceRestResourceSearchParamComponent parseConformanceConformanceRestResourceSearchParamComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceSearchParamComponent res = owner.new ConformanceRestResourceSearchParamComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("source"))
      res.setSource(parseUri(json.getString("source")));
    if (json.has("_source"))
      parseElementProperties(json.getJSONObject("_source"), res.getSource());
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), Conformance.SearchParamType.Null, new Conformance().new SearchParamTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    if (json.has("xpath"))
      res.setXpath(parseString(json.getString("xpath")));
    if (json.has("_xpath"))
      parseElementProperties(json.getJSONObject("_xpath"), res.getXpath());
    if (json.has("target")) {
      JSONArray array = json.getJSONArray("target");
      for (int i = 0; i < array.length(); i++) {
        res.getTarget().add(parseCode(array.getString(i)));
      }
    };
    if (json.has("_target")) {
      JSONArray array = json.getJSONArray("_target");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getTarget().size())
          res.getTarget().add(parseCode(null));
        parseElementProperties(array.getJSONObject(i), res.getTarget().get(i));
      }
    };
    if (json.has("chain")) {
      JSONArray array = json.getJSONArray("chain");
      for (int i = 0; i < array.length(); i++) {
        res.getChain().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_chain")) {
      JSONArray array = json.getJSONArray("_chain");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getChain().size())
          res.getChain().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getChain().get(i));
      }
    };
    return res;
  }

  private Conformance.ConformanceRestOperationComponent parseConformanceConformanceRestOperationComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestOperationComponent res = owner.new ConformanceRestOperationComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseEnumeration(json.getString("code"), Conformance.SystemRestfulOperation.Null, new Conformance().new SystemRestfulOperationEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    return res;
  }

  private Conformance.ConformanceRestQueryComponent parseConformanceConformanceRestQueryComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestQueryComponent res = owner.new ConformanceRestQueryComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    if (json.has("parameter")) {
      JSONArray array = json.getJSONArray("parameter");
      for (int i = 0; i < array.length(); i++) {
        res.getParameter().add(parseConformanceConformanceRestResourceSearchParamComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Conformance.ConformanceMessagingComponent parseConformanceConformanceMessagingComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingComponent res = owner.new ConformanceMessagingComponent();
    parseElementProperties(json, res);
    if (json.has("endpoint"))
      res.setEndpoint(parseUri(json.getString("endpoint")));
    if (json.has("_endpoint"))
      parseElementProperties(json.getJSONObject("_endpoint"), res.getEndpoint());
    if (json.has("reliableCache"))
      res.setReliableCache(parseInteger(json.getString("reliableCache")));
    if (json.has("_reliableCache"))
      parseElementProperties(json.getJSONObject("_reliableCache"), res.getReliableCache());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    if (json.has("event")) {
      JSONArray array = json.getJSONArray("event");
      for (int i = 0; i < array.length(); i++) {
        res.getEvent().add(parseConformanceConformanceMessagingEventComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Conformance.ConformanceMessagingEventComponent parseConformanceConformanceMessagingEventComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingEventComponent res = owner.new ConformanceMessagingEventComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCoding(json.getJSONObject("code")));
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.getString("mode"), Conformance.MessageConformanceEventMode.Null, new Conformance().new MessageConformanceEventModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getJSONObject("_mode"), res.getMode());
    if (json.has("protocol")) {
      JSONArray array = json.getJSONArray("protocol");
      for (int i = 0; i < array.length(); i++) {
        res.getProtocol().add(parseCoding(array.getJSONObject(i)));
      }
    };
    if (json.has("focus"))
      res.setFocus(parseCode(json.getString("focus")));
    if (json.has("_focus"))
      parseElementProperties(json.getJSONObject("_focus"), res.getFocus());
    if (json.has("request"))
      res.setRequest(parseResourceReference(json.getJSONObject("request")));
    if (json.has("response"))
      res.setResponse(parseResourceReference(json.getJSONObject("response")));
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    return res;
  }

  private Conformance.ConformanceDocumentComponent parseConformanceConformanceDocumentComponent(JSONObject json, Conformance owner) throws Exception {
    Conformance.ConformanceDocumentComponent res = owner.new ConformanceDocumentComponent();
    parseElementProperties(json, res);
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.getString("mode"), Conformance.DocumentMode.Null, new Conformance().new DocumentModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getJSONObject("_mode"), res.getMode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.getString("documentation")));
    if (json.has("_documentation"))
      parseElementProperties(json.getJSONObject("_documentation"), res.getDocumentation());
    if (json.has("profile"))
      res.setProfile(parseResourceReference(json.getJSONObject("profile")));
    return res;
  }

  private Device parseDevice(JSONObject json) throws Exception {
    Device res = new Device();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("manufacturer"))
      res.setManufacturer(parseString(json.getString("manufacturer")));
    if (json.has("_manufacturer"))
      parseElementProperties(json.getJSONObject("_manufacturer"), res.getManufacturer());
    if (json.has("model"))
      res.setModel(parseString(json.getString("model")));
    if (json.has("_model"))
      parseElementProperties(json.getJSONObject("_model"), res.getModel());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("expiry"))
      res.setExpiry(parseDate(json.getString("expiry")));
    if (json.has("_expiry"))
      parseElementProperties(json.getJSONObject("_expiry"), res.getExpiry());
    if (json.has("udi"))
      res.setUdi(parseString(json.getString("udi")));
    if (json.has("_udi"))
      parseElementProperties(json.getJSONObject("_udi"), res.getUdi());
    if (json.has("lotNumber"))
      res.setLotNumber(parseString(json.getString("lotNumber")));
    if (json.has("_lotNumber"))
      parseElementProperties(json.getJSONObject("_lotNumber"), res.getLotNumber());
    if (json.has("owner"))
      res.setOwner(parseResourceReference(json.getJSONObject("owner")));
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getJSONObject("location")));
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("contact")) {
      JSONArray array = json.getJSONArray("contact");
      for (int i = 0; i < array.length(); i++) {
        res.getContact().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("url"))
      res.setUrl(parseUri(json.getString("url")));
    if (json.has("_url"))
      parseElementProperties(json.getJSONObject("_url"), res.getUrl());
    return res;
  }

  private DeviceCapabilities parseDeviceCapabilities(JSONObject json) throws Exception {
    DeviceCapabilities res = new DeviceCapabilities();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("manufacturer"))
      res.setManufacturer(parseString(json.getString("manufacturer")));
    if (json.has("_manufacturer"))
      parseElementProperties(json.getJSONObject("_manufacturer"), res.getManufacturer());
    if (json.has("identity"))
      res.setIdentity(parseResourceReference(json.getJSONObject("identity")));
    if (json.has("virtualDevice")) {
      JSONArray array = json.getJSONArray("virtualDevice");
      for (int i = 0; i < array.length(); i++) {
        res.getVirtualDevice().add(parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private DeviceCapabilities.DeviceCapabilitiesVirtualDeviceComponent parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceComponent(JSONObject json, DeviceCapabilities owner) throws Exception {
    DeviceCapabilities.DeviceCapabilitiesVirtualDeviceComponent res = owner.new DeviceCapabilitiesVirtualDeviceComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("channel")) {
      JSONArray array = json.getJSONArray("channel");
      for (int i = 0; i < array.length(); i++) {
        res.getChannel().add(parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelComponent parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelComponent(JSONObject json, DeviceCapabilities owner) throws Exception {
    DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelComponent res = owner.new DeviceCapabilitiesVirtualDeviceChannelComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("metric")) {
      JSONArray array = json.getJSONArray("metric");
      for (int i = 0; i < array.length(); i++) {
        res.getMetric().add(parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricComponent parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricComponent(JSONObject json, DeviceCapabilities owner) throws Exception {
    DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricComponent res = owner.new DeviceCapabilitiesVirtualDeviceChannelMetricComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("key"))
      res.setKey(parseString(json.getString("key")));
    if (json.has("_key"))
      parseElementProperties(json.getJSONObject("_key"), res.getKey());
    if (json.has("info"))
      res.setInfo(parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent(json.getJSONObject("info"), owner));
    if (json.has("facet")) {
      JSONArray array = json.getJSONArray("facet");
      for (int i = 0; i < array.length(); i++) {
        res.getFacet().add(parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent(JSONObject json, DeviceCapabilities owner) throws Exception {
    DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent res = owner.new DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), DeviceCapabilities.DeviceDataType.Null, new DeviceCapabilities().new DeviceDataTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("units"))
      res.setUnits(parseString(json.getString("units")));
    if (json.has("_units"))
      parseElementProperties(json.getJSONObject("_units"), res.getUnits());
    if (json.has("ucum"))
      res.setUcum(parseCode(json.getString("ucum")));
    if (json.has("_ucum"))
      parseElementProperties(json.getJSONObject("_ucum"), res.getUcum());
    if (json.has("template"))
      res.setTemplate(parseSampledData(json.getJSONObject("template")));
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    return res;
  }

  private DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent(JSONObject json, DeviceCapabilities owner) throws Exception {
    DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent res = owner.new DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("scale"))
      res.setScale(parseDecimal(json.getString("scale")));
    if (json.has("_scale"))
      parseElementProperties(json.getJSONObject("_scale"), res.getScale());
    if (json.has("key"))
      res.setKey(parseString(json.getString("key")));
    if (json.has("_key"))
      parseElementProperties(json.getJSONObject("_key"), res.getKey());
    if (json.has("info"))
      res.setInfo(parseDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent(json.getJSONObject("info"), owner));
    return res;
  }

  private DeviceData parseDeviceData(JSONObject json) throws Exception {
    DeviceData res = new DeviceData();
    parseResourceProperties(json, res);
    if (json.has("instant"))
      res.setInstant(parseInstant(json.getString("instant")));
    if (json.has("_instant"))
      parseElementProperties(json.getJSONObject("_instant"), res.getInstant());
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getJSONObject("source")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("virtualDevice")) {
      JSONArray array = json.getJSONArray("virtualDevice");
      for (int i = 0; i < array.length(); i++) {
        res.getVirtualDevice().add(parseDeviceDataDeviceDataVirtualDeviceComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private DeviceData.DeviceDataVirtualDeviceComponent parseDeviceDataDeviceDataVirtualDeviceComponent(JSONObject json, DeviceData owner) throws Exception {
    DeviceData.DeviceDataVirtualDeviceComponent res = owner.new DeviceDataVirtualDeviceComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("channel")) {
      JSONArray array = json.getJSONArray("channel");
      for (int i = 0; i < array.length(); i++) {
        res.getChannel().add(parseDeviceDataDeviceDataVirtualDeviceChannelComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DeviceData.DeviceDataVirtualDeviceChannelComponent parseDeviceDataDeviceDataVirtualDeviceChannelComponent(JSONObject json, DeviceData owner) throws Exception {
    DeviceData.DeviceDataVirtualDeviceChannelComponent res = owner.new DeviceDataVirtualDeviceChannelComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("metric")) {
      JSONArray array = json.getJSONArray("metric");
      for (int i = 0; i < array.length(); i++) {
        res.getMetric().add(parseDeviceDataDeviceDataVirtualDeviceChannelMetricComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DeviceData.DeviceDataVirtualDeviceChannelMetricComponent parseDeviceDataDeviceDataVirtualDeviceChannelMetricComponent(JSONObject json, DeviceData owner) throws Exception {
    DeviceData.DeviceDataVirtualDeviceChannelMetricComponent res = owner.new DeviceDataVirtualDeviceChannelMetricComponent();
    parseElementProperties(json, res);
    if (json.has("observation"))
      res.setObservation(parseResourceReference(json.getJSONObject("observation")));
    return res;
  }

  private DeviceLog parseDeviceLog(JSONObject json) throws Exception {
    DeviceLog res = new DeviceLog();
    parseResourceProperties(json, res);
    if (json.has("instant"))
      res.setInstant(parseInstant(json.getString("instant")));
    if (json.has("_instant"))
      parseElementProperties(json.getJSONObject("_instant"), res.getInstant());
    if (json.has("capabilities"))
      res.setCapabilities(parseResourceReference(json.getJSONObject("capabilities")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("item")) {
      JSONArray array = json.getJSONArray("item");
      for (int i = 0; i < array.length(); i++) {
        res.getItem().add(parseDeviceLogDeviceLogItemComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private DeviceLog.DeviceLogItemComponent parseDeviceLogDeviceLogItemComponent(JSONObject json, DeviceLog owner) throws Exception {
    DeviceLog.DeviceLogItemComponent res = owner.new DeviceLogItemComponent();
    parseElementProperties(json, res);
    if (json.has("key"))
      res.setKey(parseString(json.getString("key")));
    if (json.has("_key"))
      parseElementProperties(json.getJSONObject("_key"), res.getKey());
    if (json.has("value"))
      res.setValue(parseString(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    if (json.has("flag")) {
      JSONArray array = json.getJSONArray("flag");
      for (int i = 0; i < array.length(); i++) {
        res.getFlag().add(parseEnumeration(array.getString(i), DeviceLog.DeviceValueFlag.Null, new DeviceLog().new DeviceValueFlagEnumFactory()));
      }
    };
    if (json.has("_flag")) {
      JSONArray array = json.getJSONArray("_flag");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getFlag().size())
          res.getFlag().add(parseEnumeration(null, DeviceLog.DeviceValueFlag.Null, new DeviceLog().new DeviceValueFlagEnumFactory()));
        parseElementProperties(array.getJSONObject(i), res.getFlag().get(i));
      }
    };
    return res;
  }

  private DeviceObservation parseDeviceObservation(JSONObject json) throws Exception {
    DeviceObservation res = new DeviceObservation();
    parseResourceProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("issued"))
      res.setIssued(parseInstant(json.getString("issued")));
    if (json.has("_issued"))
      parseElementProperties(json.getJSONObject("_issued"), res.getIssued());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("device"))
      res.setDevice(parseResourceReference(json.getJSONObject("device")));
    if (json.has("measurement")) {
      JSONArray array = json.getJSONArray("measurement");
      for (int i = 0; i < array.length(); i++) {
        res.getMeasurement().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private DiagnosticOrder parseDiagnosticOrder(JSONObject json) throws Exception {
    DiagnosticOrder res = new DiagnosticOrder();
    parseResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("orderer"))
      res.setOrderer(parseResourceReference(json.getJSONObject("orderer")));
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    if (json.has("clinicalNotes"))
      res.setClinicalNotes(parseString(json.getString("clinicalNotes")));
    if (json.has("_clinicalNotes"))
      parseElementProperties(json.getJSONObject("_clinicalNotes"), res.getClinicalNotes());
    if (json.has("specimen")) {
      JSONArray array = json.getJSONArray("specimen");
      for (int i = 0; i < array.length(); i++) {
        res.getSpecimen().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("priority"))
      res.setPriority(parseEnumeration(json.getString("priority"), DiagnosticOrder.DiagnosticOrderPriority.Null, new DiagnosticOrder().new DiagnosticOrderPriorityEnumFactory()));
    if (json.has("_priority"))
      parseElementProperties(json.getJSONObject("_priority"), res.getPriority());
    if (json.has("event")) {
      JSONArray array = json.getJSONArray("event");
      for (int i = 0; i < array.length(); i++) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("item")) {
      JSONArray array = json.getJSONArray("item");
      for (int i = 0; i < array.length(); i++) {
        res.getItem().add(parseDiagnosticOrderDiagnosticOrderItemComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private DiagnosticOrder.DiagnosticOrderEventComponent parseDiagnosticOrderDiagnosticOrderEventComponent(JSONObject json, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderEventComponent res = owner.new DiagnosticOrderEventComponent();
    parseElementProperties(json, res);
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("actor"))
      res.setActor(parseResourceReference(json.getJSONObject("actor")));
    return res;
  }

  private DiagnosticOrder.DiagnosticOrderItemComponent parseDiagnosticOrderDiagnosticOrderItemComponent(JSONObject json, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderItemComponent res = owner.new DiagnosticOrderItemComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("specimen")) {
      JSONArray array = json.getJSONArray("specimen");
      for (int i = 0; i < array.length(); i++) {
        res.getSpecimen().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("bodySite"))
      res.setBodySite(parseCodeableConcept(json.getJSONObject("bodySite")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("event")) {
      JSONArray array = json.getJSONArray("event");
      for (int i = 0; i < array.length(); i++) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DiagnosticReport parseDiagnosticReport(JSONObject json) throws Exception {
    DiagnosticReport res = new DiagnosticReport();
    parseResourceProperties(json, res);
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), DiagnosticReport.ObservationStatus.Null, new DiagnosticReport().new ObservationStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("issued"))
      res.setIssued(parseDateTime(json.getString("issued")));
    if (json.has("_issued"))
      parseElementProperties(json.getJSONObject("_issued"), res.getIssued());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("performer"))
      res.setPerformer(parseResourceReference(json.getJSONObject("performer")));
    if (json.has("reportId"))
      res.setReportId(parseIdentifier(json.getJSONObject("reportId")));
    if (json.has("requestDetail")) {
      JSONArray array = json.getJSONArray("requestDetail");
      for (int i = 0; i < array.length(); i++) {
        res.getRequestDetail().add(parseDiagnosticReportDiagnosticReportRequestDetailComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("serviceCategory"))
      res.setServiceCategory(parseCodeableConcept(json.getJSONObject("serviceCategory")));
    Type diagnostic = parseType("diagnostic", json);
    if (diagnostic != null)
      res.setDiagnostic(diagnostic);
    if (json.has("results"))
      res.setResults(parseDiagnosticReportResultGroupComponent(json.getJSONObject("results"), res));
    if (json.has("image")) {
      JSONArray array = json.getJSONArray("image");
      for (int i = 0; i < array.length(); i++) {
        res.getImage().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("conclusion"))
      res.setConclusion(parseString(json.getString("conclusion")));
    if (json.has("_conclusion"))
      parseElementProperties(json.getJSONObject("_conclusion"), res.getConclusion());
    if (json.has("codedDiagnosis")) {
      JSONArray array = json.getJSONArray("codedDiagnosis");
      for (int i = 0; i < array.length(); i++) {
        res.getCodedDiagnosis().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("representation")) {
      JSONArray array = json.getJSONArray("representation");
      for (int i = 0; i < array.length(); i++) {
        res.getRepresentation().add(parseAttachment(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private DiagnosticReport.DiagnosticReportRequestDetailComponent parseDiagnosticReportDiagnosticReportRequestDetailComponent(JSONObject json, DiagnosticReport owner) throws Exception {
    DiagnosticReport.DiagnosticReportRequestDetailComponent res = owner.new DiagnosticReportRequestDetailComponent();
    parseElementProperties(json, res);
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    if (json.has("requestOrderId"))
      res.setRequestOrderId(parseIdentifier(json.getJSONObject("requestOrderId")));
    if (json.has("receiverOrderId"))
      res.setReceiverOrderId(parseIdentifier(json.getJSONObject("receiverOrderId")));
    if (json.has("requestTest")) {
      JSONArray array = json.getJSONArray("requestTest");
      for (int i = 0; i < array.length(); i++) {
        res.getRequestTest().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("bodySite"))
      res.setBodySite(parseCodeableConcept(json.getJSONObject("bodySite")));
    if (json.has("requester"))
      res.setRequester(parseResourceReference(json.getJSONObject("requester")));
    if (json.has("clinicalInfo"))
      res.setClinicalInfo(parseString(json.getString("clinicalInfo")));
    if (json.has("_clinicalInfo"))
      parseElementProperties(json.getJSONObject("_clinicalInfo"), res.getClinicalInfo());
    return res;
  }

  private DiagnosticReport.ResultGroupComponent parseDiagnosticReportResultGroupComponent(JSONObject json, DiagnosticReport owner) throws Exception {
    DiagnosticReport.ResultGroupComponent res = owner.new ResultGroupComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getJSONObject("name")));
    if (json.has("specimen"))
      res.setSpecimen(parseResourceReference(json.getJSONObject("specimen")));
    if (json.has("group")) {
      JSONArray array = json.getJSONArray("group");
      for (int i = 0; i < array.length(); i++) {
        res.getGroup().add(parseDiagnosticReportResultGroupComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("result")) {
      JSONArray array = json.getJSONArray("result");
      for (int i = 0; i < array.length(); i++) {
        res.getResult().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Document parseDocument(JSONObject json) throws Exception {
    Document res = new Document();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("versionIdentifier"))
      res.setVersionIdentifier(parseIdentifier(json.getJSONObject("versionIdentifier")));
    if (json.has("created"))
      res.setCreated(parseInstant(json.getString("created")));
    if (json.has("_created"))
      parseElementProperties(json.getJSONObject("_created"), res.getCreated());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("subtype"))
      res.setSubtype(parseCodeableConcept(json.getJSONObject("subtype")));
    if (json.has("title"))
      res.setTitle(parseString(json.getString("title")));
    if (json.has("_title"))
      parseElementProperties(json.getJSONObject("_title"), res.getTitle());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Document.DocumentStatus.Null, new Document().new DocumentStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("confidentiality"))
      res.setConfidentiality(parseCoding(json.getJSONObject("confidentiality")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("author")) {
      JSONArray array = json.getJSONArray("author");
      for (int i = 0; i < array.length(); i++) {
        res.getAuthor().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("attester")) {
      JSONArray array = json.getJSONArray("attester");
      for (int i = 0; i < array.length(); i++) {
        res.getAttester().add(parseDocumentDocumentAttesterComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("custodian"))
      res.setCustodian(parseResourceReference(json.getJSONObject("custodian")));
    if (json.has("event"))
      res.setEvent(parseDocumentDocumentEventComponent(json.getJSONObject("event"), res));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    if (json.has("replaces"))
      res.setReplaces(parseId(json.getString("replaces")));
    if (json.has("_replaces"))
      parseElementProperties(json.getJSONObject("_replaces"), res.getReplaces());
    if (json.has("provenance")) {
      JSONArray array = json.getJSONArray("provenance");
      for (int i = 0; i < array.length(); i++) {
        res.getProvenance().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("stylesheet"))
      res.setStylesheet(parseAttachment(json.getJSONObject("stylesheet")));
    if (json.has("representation"))
      res.setRepresentation(parseAttachment(json.getJSONObject("representation")));
    if (json.has("section")) {
      JSONArray array = json.getJSONArray("section");
      for (int i = 0; i < array.length(); i++) {
        res.getSection().add(parseDocumentSectionComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private Document.DocumentAttesterComponent parseDocumentDocumentAttesterComponent(JSONObject json, Document owner) throws Exception {
    Document.DocumentAttesterComponent res = owner.new DocumentAttesterComponent();
    parseElementProperties(json, res);
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.getString("mode"), Document.DocumentAttestationMode.Null, new Document().new DocumentAttestationModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getJSONObject("_mode"), res.getMode());
    if (json.has("time"))
      res.setTime(parseDateTime(json.getString("time")));
    if (json.has("_time"))
      parseElementProperties(json.getJSONObject("_time"), res.getTime());
    if (json.has("party"))
      res.setParty(parseResourceReference(json.getJSONObject("party")));
    return res;
  }

  private Document.DocumentEventComponent parseDocumentDocumentEventComponent(JSONObject json, Document owner) throws Exception {
    Document.DocumentEventComponent res = owner.new DocumentEventComponent();
    parseElementProperties(json, res);
    if (json.has("code")) {
      JSONArray array = json.getJSONArray("code");
      for (int i = 0; i < array.length(); i++) {
        res.getCode().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("detail")) {
      JSONArray array = json.getJSONArray("detail");
      for (int i = 0; i < array.length(); i++) {
        res.getDetail().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Document.SectionComponent parseDocumentSectionComponent(JSONObject json, Document owner) throws Exception {
    Document.SectionComponent res = owner.new SectionComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("content"))
      res.setContent(parseResourceReference(json.getJSONObject("content")));
    if (json.has("section")) {
      JSONArray array = json.getJSONArray("section");
      for (int i = 0; i < array.length(); i++) {
        res.getSection().add(parseDocumentSectionComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DocumentManifest parseDocumentManifest(JSONObject json) throws Exception {
    DocumentManifest res = new DocumentManifest();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("subject")) {
      JSONArray array = json.getJSONArray("subject");
      for (int i = 0; i < array.length(); i++) {
        res.getSubject().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("recipient")) {
      JSONArray array = json.getJSONArray("recipient");
      for (int i = 0; i < array.length(); i++) {
        res.getRecipient().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("author")) {
      JSONArray array = json.getJSONArray("author");
      for (int i = 0; i < array.length(); i++) {
        res.getAuthor().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("created"))
      res.setCreated(parseDateTime(json.getString("created")));
    if (json.has("_created"))
      parseElementProperties(json.getJSONObject("_created"), res.getCreated());
    if (json.has("source"))
      res.setSource(parseUri(json.getString("source")));
    if (json.has("_source"))
      parseElementProperties(json.getJSONObject("_source"), res.getSource());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), DocumentManifest.DocumentReferenceStatus.Null, new DocumentManifest().new DocumentReferenceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("supercedes"))
      res.setSupercedes(parseResourceReference(json.getJSONObject("supercedes")));
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("confidentiality"))
      res.setConfidentiality(parseCodeableConcept(json.getJSONObject("confidentiality")));
    if (json.has("content")) {
      JSONArray array = json.getJSONArray("content");
      for (int i = 0; i < array.length(); i++) {
        res.getContent().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private DocumentReference parseDocumentReference(JSONObject json) throws Exception {
    DocumentReference res = new DocumentReference();
    parseResourceProperties(json, res);
    if (json.has("masterIdentifier"))
      res.setMasterIdentifier(parseIdentifier(json.getJSONObject("masterIdentifier")));
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("subtype"))
      res.setSubtype(parseCodeableConcept(json.getJSONObject("subtype")));
    if (json.has("author")) {
      JSONArray array = json.getJSONArray("author");
      for (int i = 0; i < array.length(); i++) {
        res.getAuthor().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("custodian"))
      res.setCustodian(parseResourceReference(json.getJSONObject("custodian")));
    if (json.has("policyManager"))
      res.setPolicyManager(parseUri(json.getString("policyManager")));
    if (json.has("_policyManager"))
      parseElementProperties(json.getJSONObject("_policyManager"), res.getPolicyManager());
    if (json.has("authenticator"))
      res.setAuthenticator(parseResourceReference(json.getJSONObject("authenticator")));
    if (json.has("created"))
      res.setCreated(parseDateTime(json.getString("created")));
    if (json.has("_created"))
      parseElementProperties(json.getJSONObject("_created"), res.getCreated());
    if (json.has("indexed"))
      res.setIndexed(parseInstant(json.getString("indexed")));
    if (json.has("_indexed"))
      parseElementProperties(json.getJSONObject("_indexed"), res.getIndexed());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), DocumentReference.DocumentReferenceStatus.Null, new DocumentReference().new DocumentReferenceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("docStatus"))
      res.setDocStatus(parseCodeableConcept(json.getJSONObject("docStatus")));
    if (json.has("supercedes"))
      res.setSupercedes(parseResourceReference(json.getJSONObject("supercedes")));
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("confidentiality")) {
      JSONArray array = json.getJSONArray("confidentiality");
      for (int i = 0; i < array.length(); i++) {
        res.getConfidentiality().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("primaryLanguage"))
      res.setPrimaryLanguage(parseCode(json.getString("primaryLanguage")));
    if (json.has("_primaryLanguage"))
      parseElementProperties(json.getJSONObject("_primaryLanguage"), res.getPrimaryLanguage());
    if (json.has("mimeType"))
      res.setMimeType(parseCode(json.getString("mimeType")));
    if (json.has("_mimeType"))
      parseElementProperties(json.getJSONObject("_mimeType"), res.getMimeType());
    if (json.has("format"))
      res.setFormat(parseCodeableConcept(json.getJSONObject("format")));
    if (json.has("size"))
      res.setSize(parseInteger(json.getString("size")));
    if (json.has("_size"))
      parseElementProperties(json.getJSONObject("_size"), res.getSize());
    if (json.has("hash"))
      res.setHash(parseString(json.getString("hash")));
    if (json.has("_hash"))
      parseElementProperties(json.getJSONObject("_hash"), res.getHash());
    if (json.has("location"))
      res.setLocation(parseUri(json.getString("location")));
    if (json.has("_location"))
      parseElementProperties(json.getJSONObject("_location"), res.getLocation());
    if (json.has("service"))
      res.setService(parseDocumentReferenceDocumentReferenceServiceComponent(json.getJSONObject("service"), res));
    if (json.has("context"))
      res.setContext(parseDocumentReferenceDocumentReferenceContextComponent(json.getJSONObject("context"), res));
    return res;
  }

  private DocumentReference.DocumentReferenceServiceComponent parseDocumentReferenceDocumentReferenceServiceComponent(JSONObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceServiceComponent res = owner.new DocumentReferenceServiceComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("address"))
      res.setAddress(parseString(json.getString("address")));
    if (json.has("_address"))
      parseElementProperties(json.getJSONObject("_address"), res.getAddress());
    if (json.has("parameter")) {
      JSONArray array = json.getJSONArray("parameter");
      for (int i = 0; i < array.length(); i++) {
        res.getParameter().add(parseDocumentReferenceDocumentReferenceServiceParameterComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private DocumentReference.DocumentReferenceServiceParameterComponent parseDocumentReferenceDocumentReferenceServiceParameterComponent(JSONObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceServiceParameterComponent res = owner.new DocumentReferenceServiceParameterComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("value"))
      res.setValue(parseString(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    return res;
  }

  private DocumentReference.DocumentReferenceContextComponent parseDocumentReferenceDocumentReferenceContextComponent(JSONObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceContextComponent res = owner.new DocumentReferenceContextComponent();
    parseElementProperties(json, res);
    if (json.has("code")) {
      JSONArray array = json.getJSONArray("code");
      for (int i = 0; i < array.length(); i++) {
        res.getCode().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("facilityType"))
      res.setFacilityType(parseCodeableConcept(json.getJSONObject("facilityType")));
    return res;
  }

  private Encounter parseEncounter(JSONObject json) throws Exception {
    Encounter res = new Encounter();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Encounter.EncounterState.Null, new Encounter().new EncounterStateEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("class"))
      res.setClass_(parseEnumeration(json.getString("class"), Encounter.EncounterClass.Null, new Encounter().new EncounterClassEnumFactory()));
    if (json.has("_class"))
      parseElementProperties(json.getJSONObject("_class"), res.getClass_());
    if (json.has("type")) {
      JSONArray array = json.getJSONArray("type");
      for (int i = 0; i < array.length(); i++) {
        res.getType().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("participant")) {
      JSONArray array = json.getJSONArray("participant");
      for (int i = 0; i < array.length(); i++) {
        res.getParticipant().add(parseEncounterEncounterParticipantComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("fulfills"))
      res.setFulfills(parseResourceReference(json.getJSONObject("fulfills")));
    if (json.has("start"))
      res.setStart(parseDateTime(json.getString("start")));
    if (json.has("_start"))
      parseElementProperties(json.getJSONObject("_start"), res.getStart());
    if (json.has("length"))
      res.setLength(parseDuration(json.getJSONObject("length")));
    Type reason = parseType("reason", json);
    if (reason != null)
      res.setReason(reason);
    if (json.has("indication"))
      res.setIndication(parseResourceReference(json.getJSONObject("indication")));
    if (json.has("priority"))
      res.setPriority(parseCodeableConcept(json.getJSONObject("priority")));
    if (json.has("hospitalization"))
      res.setHospitalization(parseEncounterEncounterHospitalizationComponent(json.getJSONObject("hospitalization"), res));
    if (json.has("location")) {
      JSONArray array = json.getJSONArray("location");
      for (int i = 0; i < array.length(); i++) {
        res.getLocation().add(parseEncounterEncounterLocationComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("serviceProvider"))
      res.setServiceProvider(parseResourceReference(json.getJSONObject("serviceProvider")));
    if (json.has("partOf"))
      res.setPartOf(parseResourceReference(json.getJSONObject("partOf")));
    return res;
  }

  private Encounter.EncounterParticipantComponent parseEncounterEncounterParticipantComponent(JSONObject json, Encounter owner) throws Exception {
    Encounter.EncounterParticipantComponent res = owner.new EncounterParticipantComponent();
    parseElementProperties(json, res);
    if (json.has("type")) {
      JSONArray array = json.getJSONArray("type");
      for (int i = 0; i < array.length(); i++) {
        res.getType().add(parseEnumeration(array.getString(i), Encounter.ParticipantType.Null, new Encounter().new ParticipantTypeEnumFactory()));
      }
    };
    if (json.has("_type")) {
      JSONArray array = json.getJSONArray("_type");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getType().size())
          res.getType().add(parseEnumeration(null, Encounter.ParticipantType.Null, new Encounter().new ParticipantTypeEnumFactory()));
        parseElementProperties(array.getJSONObject(i), res.getType().get(i));
      }
    };
    if (json.has("practitioner"))
      res.setPractitioner(parseResourceReference(json.getJSONObject("practitioner")));
    return res;
  }

  private Encounter.EncounterHospitalizationComponent parseEncounterEncounterHospitalizationComponent(JSONObject json, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationComponent res = owner.new EncounterHospitalizationComponent();
    parseElementProperties(json, res);
    if (json.has("preAdmissionIdentifier"))
      res.setPreAdmissionIdentifier(parseIdentifier(json.getJSONObject("preAdmissionIdentifier")));
    if (json.has("origin"))
      res.setOrigin(parseResourceReference(json.getJSONObject("origin")));
    if (json.has("admitSource"))
      res.setAdmitSource(parseCodeableConcept(json.getJSONObject("admitSource")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("accomodation")) {
      JSONArray array = json.getJSONArray("accomodation");
      for (int i = 0; i < array.length(); i++) {
        res.getAccomodation().add(parseEncounterEncounterHospitalizationAccomodationComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("diet"))
      res.setDiet(parseCodeableConcept(json.getJSONObject("diet")));
    if (json.has("specialCourtesy")) {
      JSONArray array = json.getJSONArray("specialCourtesy");
      for (int i = 0; i < array.length(); i++) {
        res.getSpecialCourtesy().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("specialArrangement")) {
      JSONArray array = json.getJSONArray("specialArrangement");
      for (int i = 0; i < array.length(); i++) {
        res.getSpecialArrangement().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("destination"))
      res.setDestination(parseResourceReference(json.getJSONObject("destination")));
    if (json.has("dischargeDisposition"))
      res.setDischargeDisposition(parseCodeableConcept(json.getJSONObject("dischargeDisposition")));
    if (json.has("reAdmission"))
      res.setReAdmission(parseBoolean(json.getBoolean("reAdmission")));
    if (json.has("_reAdmission"))
      parseElementProperties(json.getJSONObject("_reAdmission"), res.getReAdmission());
    return res;
  }

  private Encounter.EncounterHospitalizationAccomodationComponent parseEncounterEncounterHospitalizationAccomodationComponent(JSONObject json, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationAccomodationComponent res = owner.new EncounterHospitalizationAccomodationComponent();
    parseElementProperties(json, res);
    if (json.has("bed"))
      res.setBed(parseResourceReference(json.getJSONObject("bed")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    return res;
  }

  private Encounter.EncounterLocationComponent parseEncounterEncounterLocationComponent(JSONObject json, Encounter owner) throws Exception {
    Encounter.EncounterLocationComponent res = owner.new EncounterLocationComponent();
    parseElementProperties(json, res);
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getJSONObject("location")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    return res;
  }

  private FamilyHistory parseFamilyHistory(JSONObject json) throws Exception {
    FamilyHistory res = new FamilyHistory();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("note"))
      res.setNote(parseString(json.getString("note")));
    if (json.has("_note"))
      parseElementProperties(json.getJSONObject("_note"), res.getNote());
    if (json.has("relation")) {
      JSONArray array = json.getJSONArray("relation");
      for (int i = 0; i < array.length(); i++) {
        res.getRelation().add(parseFamilyHistoryFamilyHistoryRelationComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private FamilyHistory.FamilyHistoryRelationComponent parseFamilyHistoryFamilyHistoryRelationComponent(JSONObject json, FamilyHistory owner) throws Exception {
    FamilyHistory.FamilyHistoryRelationComponent res = owner.new FamilyHistoryRelationComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("relationship"))
      res.setRelationship(parseCodeableConcept(json.getJSONObject("relationship")));
    Type deceased = parseType("deceased", json);
    if (deceased != null)
      res.setDeceased(deceased);
    if (json.has("note"))
      res.setNote(parseString(json.getString("note")));
    if (json.has("_note"))
      parseElementProperties(json.getJSONObject("_note"), res.getNote());
    if (json.has("condition")) {
      JSONArray array = json.getJSONArray("condition");
      for (int i = 0; i < array.length(); i++) {
        res.getCondition().add(parseFamilyHistoryFamilyHistoryRelationConditionComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private FamilyHistory.FamilyHistoryRelationConditionComponent parseFamilyHistoryFamilyHistoryRelationConditionComponent(JSONObject json, FamilyHistory owner) throws Exception {
    FamilyHistory.FamilyHistoryRelationConditionComponent res = owner.new FamilyHistoryRelationConditionComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("outcome"))
      res.setOutcome(parseCodeableConcept(json.getJSONObject("outcome")));
    Type onset = parseType("onset", json);
    if (onset != null)
      res.setOnset(onset);
    if (json.has("note"))
      res.setNote(parseString(json.getString("note")));
    if (json.has("_note"))
      parseElementProperties(json.getJSONObject("_note"), res.getNote());
    return res;
  }

  private Group parseGroup(JSONObject json) throws Exception {
    Group res = new Group();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), Group.GroupType.Null, new Group().new GroupTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("actual"))
      res.setActual(parseBoolean(json.getBoolean("actual")));
    if (json.has("_actual"))
      parseElementProperties(json.getJSONObject("_actual"), res.getActual());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("quantity"))
      res.setQuantity(parseInteger(json.getString("quantity")));
    if (json.has("_quantity"))
      parseElementProperties(json.getJSONObject("_quantity"), res.getQuantity());
    if (json.has("characteristic")) {
      JSONArray array = json.getJSONArray("characteristic");
      for (int i = 0; i < array.length(); i++) {
        res.getCharacteristic().add(parseGroupGroupCharacteristicComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("member")) {
      JSONArray array = json.getJSONArray("member");
      for (int i = 0; i < array.length(); i++) {
        res.getMember().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Group.GroupCharacteristicComponent parseGroupGroupCharacteristicComponent(JSONObject json, Group owner) throws Exception {
    Group.GroupCharacteristicComponent res = owner.new GroupCharacteristicComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    if (json.has("exclude"))
      res.setExclude(parseBoolean(json.getBoolean("exclude")));
    if (json.has("_exclude"))
      parseElementProperties(json.getJSONObject("_exclude"), res.getExclude());
    return res;
  }

  private ImagingStudy parseImagingStudy(JSONObject json) throws Exception {
    ImagingStudy res = new ImagingStudy();
    parseResourceProperties(json, res);
    if (json.has("dateTime"))
      res.setDateTime(parseDateTime(json.getString("dateTime")));
    if (json.has("_dateTime"))
      parseElementProperties(json.getJSONObject("_dateTime"), res.getDateTime());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("uid"))
      res.setUid(parseOid(json.getString("uid")));
    if (json.has("_uid"))
      parseElementProperties(json.getJSONObject("_uid"), res.getUid());
    if (json.has("accessionNo"))
      res.setAccessionNo(parseIdentifier(json.getJSONObject("accessionNo")));
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("order")) {
      JSONArray array = json.getJSONArray("order");
      for (int i = 0; i < array.length(); i++) {
        res.getOrder().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("modality")) {
      JSONArray array = json.getJSONArray("modality");
      for (int i = 0; i < array.length(); i++) {
        res.getModality().add(parseEnumeration(array.getString(i), ImagingStudy.ImagingModality.Null, new ImagingStudy().new ImagingModalityEnumFactory()));
      }
    };
    if (json.has("_modality")) {
      JSONArray array = json.getJSONArray("_modality");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getModality().size())
          res.getModality().add(parseEnumeration(null, ImagingStudy.ImagingModality.Null, new ImagingStudy().new ImagingModalityEnumFactory()));
        parseElementProperties(array.getJSONObject(i), res.getModality().get(i));
      }
    };
    if (json.has("referrer"))
      res.setReferrer(parseResourceReference(json.getJSONObject("referrer")));
    if (json.has("availability"))
      res.setAvailability(parseEnumeration(json.getString("availability"), ImagingStudy.InstanceAvailability.Null, new ImagingStudy().new InstanceAvailabilityEnumFactory()));
    if (json.has("_availability"))
      parseElementProperties(json.getJSONObject("_availability"), res.getAvailability());
    if (json.has("url"))
      res.setUrl(parseUri(json.getString("url")));
    if (json.has("_url"))
      parseElementProperties(json.getJSONObject("_url"), res.getUrl());
    if (json.has("numberOfSeries"))
      res.setNumberOfSeries(parseInteger(json.getString("numberOfSeries")));
    if (json.has("_numberOfSeries"))
      parseElementProperties(json.getJSONObject("_numberOfSeries"), res.getNumberOfSeries());
    if (json.has("numberOfInstances"))
      res.setNumberOfInstances(parseInteger(json.getString("numberOfInstances")));
    if (json.has("_numberOfInstances"))
      parseElementProperties(json.getJSONObject("_numberOfInstances"), res.getNumberOfInstances());
    if (json.has("clinicalInformation"))
      res.setClinicalInformation(parseString(json.getString("clinicalInformation")));
    if (json.has("_clinicalInformation"))
      parseElementProperties(json.getJSONObject("_clinicalInformation"), res.getClinicalInformation());
    if (json.has("procedure")) {
      JSONArray array = json.getJSONArray("procedure");
      for (int i = 0; i < array.length(); i++) {
        res.getProcedure().add(parseCoding(array.getJSONObject(i)));
      }
    };
    if (json.has("interpreter"))
      res.setInterpreter(parseResourceReference(json.getJSONObject("interpreter")));
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("series")) {
      JSONArray array = json.getJSONArray("series");
      for (int i = 0; i < array.length(); i++) {
        res.getSeries().add(parseImagingStudyImagingStudySeriesComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private ImagingStudy.ImagingStudySeriesComponent parseImagingStudyImagingStudySeriesComponent(JSONObject json, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesComponent res = owner.new ImagingStudySeriesComponent();
    parseElementProperties(json, res);
    if (json.has("number"))
      res.setNumber(parseInteger(json.getString("number")));
    if (json.has("_number"))
      parseElementProperties(json.getJSONObject("_number"), res.getNumber());
    if (json.has("modality"))
      res.setModality(parseEnumeration(json.getString("modality"), ImagingStudy.Modality.Null, new ImagingStudy().new ModalityEnumFactory()));
    if (json.has("_modality"))
      parseElementProperties(json.getJSONObject("_modality"), res.getModality());
    if (json.has("uid"))
      res.setUid(parseOid(json.getString("uid")));
    if (json.has("_uid"))
      parseElementProperties(json.getJSONObject("_uid"), res.getUid());
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("numberOfInstances"))
      res.setNumberOfInstances(parseInteger(json.getString("numberOfInstances")));
    if (json.has("_numberOfInstances"))
      parseElementProperties(json.getJSONObject("_numberOfInstances"), res.getNumberOfInstances());
    if (json.has("availability"))
      res.setAvailability(parseEnumeration(json.getString("availability"), ImagingStudy.InstanceAvailability.Null, new ImagingStudy().new InstanceAvailabilityEnumFactory()));
    if (json.has("_availability"))
      parseElementProperties(json.getJSONObject("_availability"), res.getAvailability());
    if (json.has("url"))
      res.setUrl(parseUri(json.getString("url")));
    if (json.has("_url"))
      parseElementProperties(json.getJSONObject("_url"), res.getUrl());
    if (json.has("bodySite"))
      res.setBodySite(parseCoding(json.getJSONObject("bodySite")));
    if (json.has("dateTime"))
      res.setDateTime(parseDateTime(json.getString("dateTime")));
    if (json.has("_dateTime"))
      parseElementProperties(json.getJSONObject("_dateTime"), res.getDateTime());
    if (json.has("instance")) {
      JSONArray array = json.getJSONArray("instance");
      for (int i = 0; i < array.length(); i++) {
        res.getInstance().add(parseImagingStudyImagingStudySeriesInstanceComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private ImagingStudy.ImagingStudySeriesInstanceComponent parseImagingStudyImagingStudySeriesInstanceComponent(JSONObject json, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesInstanceComponent res = owner.new ImagingStudySeriesInstanceComponent();
    parseElementProperties(json, res);
    if (json.has("number"))
      res.setNumber(parseInteger(json.getString("number")));
    if (json.has("_number"))
      parseElementProperties(json.getJSONObject("_number"), res.getNumber());
    if (json.has("uid"))
      res.setUid(parseOid(json.getString("uid")));
    if (json.has("_uid"))
      parseElementProperties(json.getJSONObject("_uid"), res.getUid());
    if (json.has("sopclass"))
      res.setSopclass(parseOid(json.getString("sopclass")));
    if (json.has("_sopclass"))
      parseElementProperties(json.getJSONObject("_sopclass"), res.getSopclass());
    if (json.has("type"))
      res.setType(parseString(json.getString("type")));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("title"))
      res.setTitle(parseString(json.getString("title")));
    if (json.has("_title"))
      parseElementProperties(json.getJSONObject("_title"), res.getTitle());
    if (json.has("url"))
      res.setUrl(parseUri(json.getString("url")));
    if (json.has("_url"))
      parseElementProperties(json.getJSONObject("_url"), res.getUrl());
    if (json.has("attachment"))
      res.setAttachment(parseResourceReference(json.getJSONObject("attachment")));
    return res;
  }

  private Immunization parseImmunization(JSONObject json) throws Exception {
    Immunization res = new Immunization();
    parseResourceProperties(json, res);
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("vaccineType"))
      res.setVaccineType(parseCodeableConcept(json.getJSONObject("vaccineType")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("refusedIndicator"))
      res.setRefusedIndicator(parseBoolean(json.getBoolean("refusedIndicator")));
    if (json.has("_refusedIndicator"))
      parseElementProperties(json.getJSONObject("_refusedIndicator"), res.getRefusedIndicator());
    if (json.has("reported"))
      res.setReported(parseBoolean(json.getBoolean("reported")));
    if (json.has("_reported"))
      parseElementProperties(json.getJSONObject("_reported"), res.getReported());
    if (json.has("performer"))
      res.setPerformer(parseResourceReference(json.getJSONObject("performer")));
    if (json.has("requester"))
      res.setRequester(parseResourceReference(json.getJSONObject("requester")));
    if (json.has("manufacturer"))
      res.setManufacturer(parseResourceReference(json.getJSONObject("manufacturer")));
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getJSONObject("location")));
    if (json.has("lotNumber"))
      res.setLotNumber(parseString(json.getString("lotNumber")));
    if (json.has("_lotNumber"))
      parseElementProperties(json.getJSONObject("_lotNumber"), res.getLotNumber());
    if (json.has("expirationDate"))
      res.setExpirationDate(parseDate(json.getString("expirationDate")));
    if (json.has("_expirationDate"))
      parseElementProperties(json.getJSONObject("_expirationDate"), res.getExpirationDate());
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getJSONObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getJSONObject("route")));
    if (json.has("doseQuantity"))
      res.setDoseQuantity(parseQuantity(json.getJSONObject("doseQuantity")));
    if (json.has("explanation"))
      res.setExplanation(parseImmunizationImmunizationExplanationComponent(json.getJSONObject("explanation"), res));
    if (json.has("reaction")) {
      JSONArray array = json.getJSONArray("reaction");
      for (int i = 0; i < array.length(); i++) {
        res.getReaction().add(parseImmunizationImmunizationReactionComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("vaccinationProtocol"))
      res.setVaccinationProtocol(parseImmunizationImmunizationVaccinationProtocolComponent(json.getJSONObject("vaccinationProtocol"), res));
    return res;
  }

  private Immunization.ImmunizationExplanationComponent parseImmunizationImmunizationExplanationComponent(JSONObject json, Immunization owner) throws Exception {
    Immunization.ImmunizationExplanationComponent res = owner.new ImmunizationExplanationComponent();
    parseElementProperties(json, res);
    if (json.has("reason")) {
      JSONArray array = json.getJSONArray("reason");
      for (int i = 0; i < array.length(); i++) {
        res.getReason().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("refusalReason")) {
      JSONArray array = json.getJSONArray("refusalReason");
      for (int i = 0; i < array.length(); i++) {
        res.getRefusalReason().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Immunization.ImmunizationReactionComponent parseImmunizationImmunizationReactionComponent(JSONObject json, Immunization owner) throws Exception {
    Immunization.ImmunizationReactionComponent res = owner.new ImmunizationReactionComponent();
    parseElementProperties(json, res);
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("detail"))
      res.setDetail(parseResourceReference(json.getJSONObject("detail")));
    if (json.has("reported"))
      res.setReported(parseBoolean(json.getBoolean("reported")));
    if (json.has("_reported"))
      parseElementProperties(json.getJSONObject("_reported"), res.getReported());
    return res;
  }

  private Immunization.ImmunizationVaccinationProtocolComponent parseImmunizationImmunizationVaccinationProtocolComponent(JSONObject json, Immunization owner) throws Exception {
    Immunization.ImmunizationVaccinationProtocolComponent res = owner.new ImmunizationVaccinationProtocolComponent();
    parseElementProperties(json, res);
    if (json.has("doseSequence"))
      res.setDoseSequence(parseInteger(json.getString("doseSequence")));
    if (json.has("_doseSequence"))
      parseElementProperties(json.getJSONObject("_doseSequence"), res.getDoseSequence());
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("authority"))
      res.setAuthority(parseResourceReference(json.getJSONObject("authority")));
    if (json.has("series"))
      res.setSeries(parseString(json.getString("series")));
    if (json.has("_series"))
      parseElementProperties(json.getJSONObject("_series"), res.getSeries());
    if (json.has("seriesDoses"))
      res.setSeriesDoses(parseInteger(json.getString("seriesDoses")));
    if (json.has("_seriesDoses"))
      parseElementProperties(json.getJSONObject("_seriesDoses"), res.getSeriesDoses());
    if (json.has("doseTarget"))
      res.setDoseTarget(parseCodeableConcept(json.getJSONObject("doseTarget")));
    if (json.has("doseStatus"))
      res.setDoseStatus(parseCodeableConcept(json.getJSONObject("doseStatus")));
    if (json.has("doseStatusReason"))
      res.setDoseStatusReason(parseCodeableConcept(json.getJSONObject("doseStatusReason")));
    return res;
  }

  private ImmunizationRecommendation parseImmunizationRecommendation(JSONObject json) throws Exception {
    ImmunizationRecommendation res = new ImmunizationRecommendation();
    parseResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("recommendation")) {
      JSONArray array = json.getJSONArray("recommendation");
      for (int i = 0; i < array.length(); i++) {
        res.getRecommendation().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(JSONObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent res = owner.new ImmunizationRecommendationRecommendationComponent();
    parseElementProperties(json, res);
    if (json.has("recommendationDate"))
      res.setRecommendationDate(parseDateTime(json.getString("recommendationDate")));
    if (json.has("_recommendationDate"))
      parseElementProperties(json.getJSONObject("_recommendationDate"), res.getRecommendationDate());
    if (json.has("vaccineType"))
      res.setVaccineType(parseCodeableConcept(json.getJSONObject("vaccineType")));
    if (json.has("doseNumber"))
      res.setDoseNumber(parseInteger(json.getString("doseNumber")));
    if (json.has("_doseNumber"))
      parseElementProperties(json.getJSONObject("_doseNumber"), res.getDoseNumber());
    if (json.has("forecastStatus"))
      res.setForecastStatus(parseEnumeration(json.getString("forecastStatus"), ImmunizationRecommendation.ImmunizationForecastStatus.Null, new ImmunizationRecommendation().new ImmunizationForecastStatusEnumFactory()));
    if (json.has("_forecastStatus"))
      parseElementProperties(json.getJSONObject("_forecastStatus"), res.getForecastStatus());
    if (json.has("dateCriterion")) {
      JSONArray array = json.getJSONArray("dateCriterion");
      for (int i = 0; i < array.length(); i++) {
        res.getDateCriterion().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("protocol"))
      res.setProtocol(parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(json.getJSONObject("protocol"), owner));
    if (json.has("supportingImmunization")) {
      JSONArray array = json.getJSONArray("supportingImmunization");
      for (int i = 0; i < array.length(); i++) {
        res.getSupportingImmunization().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("supportingAdverseEventReport")) {
      JSONArray array = json.getJSONArray("supportingAdverseEventReport");
      for (int i = 0; i < array.length(); i++) {
        res.getSupportingAdverseEventReport().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("supportingPatientObservation")) {
      JSONArray array = json.getJSONArray("supportingPatientObservation");
      for (int i = 0; i < array.length(); i++) {
        res.getSupportingPatientObservation().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(JSONObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent res = owner.new ImmunizationRecommendationRecommendationDateCriterionComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("value"))
      res.setValue(parseDateTime(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(JSONObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent res = owner.new ImmunizationRecommendationRecommendationProtocolComponent();
    parseElementProperties(json, res);
    if (json.has("doseSequence"))
      res.setDoseSequence(parseInteger(json.getString("doseSequence")));
    if (json.has("_doseSequence"))
      parseElementProperties(json.getJSONObject("_doseSequence"), res.getDoseSequence());
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("authority"))
      res.setAuthority(parseResourceReference(json.getJSONObject("authority")));
    if (json.has("series"))
      res.setSeries(parseString(json.getString("series")));
    if (json.has("_series"))
      parseElementProperties(json.getJSONObject("_series"), res.getSeries());
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent(JSONObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent res = owner.new ImmunizationRecommendationRecommendationSupportingAdverseEventReportComponent();
    parseElementProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseId(array.getString(i)));
      }
    };
    if (json.has("_identifier")) {
      JSONArray array = json.getJSONArray("_identifier");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getIdentifier().size())
          res.getIdentifier().add(parseId(null));
        parseElementProperties(array.getJSONObject(i), res.getIdentifier().get(i));
      }
    };
    if (json.has("reportType"))
      res.setReportType(parseCodeableConcept(json.getJSONObject("reportType")));
    if (json.has("reportDate"))
      res.setReportDate(parseDateTime(json.getString("reportDate")));
    if (json.has("_reportDate"))
      parseElementProperties(json.getJSONObject("_reportDate"), res.getReportDate());
    if (json.has("text"))
      res.setText(parseString(json.getString("text")));
    if (json.has("_text"))
      parseElementProperties(json.getJSONObject("_text"), res.getText());
    if (json.has("reaction")) {
      JSONArray array = json.getJSONArray("reaction");
      for (int i = 0; i < array.length(); i++) {
        res.getReaction().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private List_ parseList_(JSONObject json) throws Exception {
    List_ res = new List_();
    parseResourceProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getJSONObject("source")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("ordered"))
      res.setOrdered(parseBoolean(json.getBoolean("ordered")));
    if (json.has("_ordered"))
      parseElementProperties(json.getJSONObject("_ordered"), res.getOrdered());
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.getString("mode"), List_.ListMode.Null, new List_().new ListModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getJSONObject("_mode"), res.getMode());
    if (json.has("entry")) {
      JSONArray array = json.getJSONArray("entry");
      for (int i = 0; i < array.length(); i++) {
        res.getEntry().add(parseList_ListEntryComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("emptyReason"))
      res.setEmptyReason(parseCodeableConcept(json.getJSONObject("emptyReason")));
    return res;
  }

  private List_.ListEntryComponent parseList_ListEntryComponent(JSONObject json, List_ owner) throws Exception {
    List_.ListEntryComponent res = owner.new ListEntryComponent();
    parseElementProperties(json, res);
    if (json.has("flag")) {
      JSONArray array = json.getJSONArray("flag");
      for (int i = 0; i < array.length(); i++) {
        res.getFlag().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("deleted"))
      res.setDeleted(parseBoolean(json.getBoolean("deleted")));
    if (json.has("_deleted"))
      parseElementProperties(json.getJSONObject("_deleted"), res.getDeleted());
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("item"))
      res.setItem(parseResourceReference(json.getJSONObject("item")));
    return res;
  }

  private Location parseLocation(JSONObject json) throws Exception {
    Location res = new Location();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("type")) {
      JSONArray array = json.getJSONArray("type");
      for (int i = 0; i < array.length(); i++) {
        res.getType().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("telecom"))
      res.setTelecom(parseContact(json.getJSONObject("telecom")));
    if (json.has("address"))
      res.setAddress(parseAddress(json.getJSONObject("address")));
    if (json.has("position"))
      res.setPosition(parseLocationLocationPositionComponent(json.getJSONObject("position"), res));
    if (json.has("provider"))
      res.setProvider(parseResourceReference(json.getJSONObject("provider")));
    if (json.has("active"))
      res.setActive(parseBoolean(json.getBoolean("active")));
    if (json.has("_active"))
      parseElementProperties(json.getJSONObject("_active"), res.getActive());
    if (json.has("partOf"))
      res.setPartOf(parseResourceReference(json.getJSONObject("partOf")));
    return res;
  }

  private Location.LocationPositionComponent parseLocationLocationPositionComponent(JSONObject json, Location owner) throws Exception {
    Location.LocationPositionComponent res = owner.new LocationPositionComponent();
    parseElementProperties(json, res);
    if (json.has("longitude"))
      res.setLongitude(parseDecimal(json.getString("longitude")));
    if (json.has("_longitude"))
      parseElementProperties(json.getJSONObject("_longitude"), res.getLongitude());
    if (json.has("latitude"))
      res.setLatitude(parseDecimal(json.getString("latitude")));
    if (json.has("_latitude"))
      parseElementProperties(json.getJSONObject("_latitude"), res.getLatitude());
    if (json.has("altitude"))
      res.setAltitude(parseDecimal(json.getString("altitude")));
    if (json.has("_altitude"))
      parseElementProperties(json.getJSONObject("_altitude"), res.getAltitude());
    return res;
  }

  private Media parseMedia(JSONObject json) throws Exception {
    Media res = new Media();
    parseResourceProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), Media.MediaType.Null, new Media().new MediaTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("subtype"))
      res.setSubtype(parseCodeableConcept(json.getJSONObject("subtype")));
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("dateTime"))
      res.setDateTime(parseDateTime(json.getString("dateTime")));
    if (json.has("_dateTime"))
      parseElementProperties(json.getJSONObject("_dateTime"), res.getDateTime());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("operator"))
      res.setOperator(parseResourceReference(json.getJSONObject("operator")));
    if (json.has("view"))
      res.setView(parseCodeableConcept(json.getJSONObject("view")));
    if (json.has("deviceName"))
      res.setDeviceName(parseString(json.getString("deviceName")));
    if (json.has("_deviceName"))
      parseElementProperties(json.getJSONObject("_deviceName"), res.getDeviceName());
    if (json.has("height"))
      res.setHeight(parseInteger(json.getString("height")));
    if (json.has("_height"))
      parseElementProperties(json.getJSONObject("_height"), res.getHeight());
    if (json.has("width"))
      res.setWidth(parseInteger(json.getString("width")));
    if (json.has("_width"))
      parseElementProperties(json.getJSONObject("_width"), res.getWidth());
    if (json.has("frames"))
      res.setFrames(parseInteger(json.getString("frames")));
    if (json.has("_frames"))
      parseElementProperties(json.getJSONObject("_frames"), res.getFrames());
    if (json.has("length"))
      res.setLength(parseInteger(json.getString("length")));
    if (json.has("_length"))
      parseElementProperties(json.getJSONObject("_length"), res.getLength());
    if (json.has("content"))
      res.setContent(parseAttachment(json.getJSONObject("content")));
    return res;
  }

  private Medication parseMedication(JSONObject json) throws Exception {
    Medication res = new Medication();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("isBrand"))
      res.setIsBrand(parseBoolean(json.getBoolean("isBrand")));
    if (json.has("_isBrand"))
      parseElementProperties(json.getJSONObject("_isBrand"), res.getIsBrand());
    if (json.has("manufacturer"))
      res.setManufacturer(parseResourceReference(json.getJSONObject("manufacturer")));
    if (json.has("kind"))
      res.setKind(parseEnumeration(json.getString("kind"), Medication.MedicationKind.Null, new Medication().new MedicationKindEnumFactory()));
    if (json.has("_kind"))
      parseElementProperties(json.getJSONObject("_kind"), res.getKind());
    if (json.has("product"))
      res.setProduct(parseMedicationMedicationProductComponent(json.getJSONObject("product"), res));
    if (json.has("package"))
      res.setPackage(parseMedicationMedicationPackageComponent(json.getJSONObject("package"), res));
    return res;
  }

  private Medication.MedicationProductComponent parseMedicationMedicationProductComponent(JSONObject json, Medication owner) throws Exception {
    Medication.MedicationProductComponent res = owner.new MedicationProductComponent();
    parseElementProperties(json, res);
    if (json.has("form"))
      res.setForm(parseCodeableConcept(json.getJSONObject("form")));
    if (json.has("ingredient")) {
      JSONArray array = json.getJSONArray("ingredient");
      for (int i = 0; i < array.length(); i++) {
        res.getIngredient().add(parseMedicationMedicationProductIngredientComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Medication.MedicationProductIngredientComponent parseMedicationMedicationProductIngredientComponent(JSONObject json, Medication owner) throws Exception {
    Medication.MedicationProductIngredientComponent res = owner.new MedicationProductIngredientComponent();
    parseElementProperties(json, res);
    if (json.has("item"))
      res.setItem(parseResourceReference(json.getJSONObject("item")));
    if (json.has("amount"))
      res.setAmount(parseRatio(json.getJSONObject("amount")));
    return res;
  }

  private Medication.MedicationPackageComponent parseMedicationMedicationPackageComponent(JSONObject json, Medication owner) throws Exception {
    Medication.MedicationPackageComponent res = owner.new MedicationPackageComponent();
    parseElementProperties(json, res);
    if (json.has("container"))
      res.setContainer(parseCodeableConcept(json.getJSONObject("container")));
    if (json.has("content")) {
      JSONArray array = json.getJSONArray("content");
      for (int i = 0; i < array.length(); i++) {
        res.getContent().add(parseMedicationMedicationPackageContentComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Medication.MedicationPackageContentComponent parseMedicationMedicationPackageContentComponent(JSONObject json, Medication owner) throws Exception {
    Medication.MedicationPackageContentComponent res = owner.new MedicationPackageContentComponent();
    parseElementProperties(json, res);
    if (json.has("item"))
      res.setItem(parseResourceReference(json.getJSONObject("item")));
    if (json.has("amount"))
      res.setAmount(parseQuantity(json.getJSONObject("amount")));
    return res;
  }

  private MedicationAdministration parseMedicationAdministration(JSONObject json) throws Exception {
    MedicationAdministration res = new MedicationAdministration();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), MedicationAdministration.MedicationAdminStatus.Null, new MedicationAdministration().new MedicationAdminStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("practitioner"))
      res.setPractitioner(parseResourceReference(json.getJSONObject("practitioner")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    if (json.has("prescription"))
      res.setPrescription(parseResourceReference(json.getJSONObject("prescription")));
    if (json.has("wasNotGiven"))
      res.setWasNotGiven(parseBoolean(json.getBoolean("wasNotGiven")));
    if (json.has("_wasNotGiven"))
      parseElementProperties(json.getJSONObject("_wasNotGiven"), res.getWasNotGiven());
    if (json.has("reasonNotGiven")) {
      JSONArray array = json.getJSONArray("reasonNotGiven");
      for (int i = 0; i < array.length(); i++) {
        res.getReasonNotGiven().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("whenGiven"))
      res.setWhenGiven(parsePeriod(json.getJSONObject("whenGiven")));
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getJSONObject("medication")));
    if (json.has("administrationDevice")) {
      JSONArray array = json.getJSONArray("administrationDevice");
      for (int i = 0; i < array.length(); i++) {
        res.getAdministrationDevice().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("dosage")) {
      JSONArray array = json.getJSONArray("dosage");
      for (int i = 0; i < array.length(); i++) {
        res.getDosage().add(parseMedicationAdministrationMedicationAdministrationDosageComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private MedicationAdministration.MedicationAdministrationDosageComponent parseMedicationAdministrationMedicationAdministrationDosageComponent(JSONObject json, MedicationAdministration owner) throws Exception {
    MedicationAdministration.MedicationAdministrationDosageComponent res = owner.new MedicationAdministrationDosageComponent();
    parseElementProperties(json, res);
    if (json.has("timing"))
      res.setTiming(parseSchedule(json.getJSONObject("timing")));
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getJSONObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getJSONObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getJSONObject("method")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getJSONObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getJSONObject("maxDosePerPeriod")));
    return res;
  }

  private MedicationDispense parseMedicationDispense(JSONObject json) throws Exception {
    MedicationDispense res = new MedicationDispense();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), MedicationDispense.MedicationDispenseStatus.Null, new MedicationDispense().new MedicationDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("dispenser"))
      res.setDispenser(parseResourceReference(json.getJSONObject("dispenser")));
    if (json.has("authorizingPrescription")) {
      JSONArray array = json.getJSONArray("authorizingPrescription");
      for (int i = 0; i < array.length(); i++) {
        res.getAuthorizingPrescription().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("dispense")) {
      JSONArray array = json.getJSONArray("dispense");
      for (int i = 0; i < array.length(); i++) {
        res.getDispense().add(parseMedicationDispenseMedicationDispenseDispenseComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("substitution"))
      res.setSubstitution(parseMedicationDispenseMedicationDispenseSubstitutionComponent(json.getJSONObject("substitution"), res));
    return res;
  }

  private MedicationDispense.MedicationDispenseDispenseComponent parseMedicationDispenseMedicationDispenseDispenseComponent(JSONObject json, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseDispenseComponent res = owner.new MedicationDispenseDispenseComponent();
    parseElementProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), MedicationDispense.MedicationDispenseStatus.Null, new MedicationDispense().new MedicationDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getJSONObject("medication")));
    if (json.has("whenPrepared"))
      res.setWhenPrepared(parsePeriod(json.getJSONObject("whenPrepared")));
    if (json.has("whenHandedOver"))
      res.setWhenHandedOver(parsePeriod(json.getJSONObject("whenHandedOver")));
    if (json.has("destination"))
      res.setDestination(parseResourceReference(json.getJSONObject("destination")));
    if (json.has("receiver")) {
      JSONArray array = json.getJSONArray("receiver");
      for (int i = 0; i < array.length(); i++) {
        res.getReceiver().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("dosage")) {
      JSONArray array = json.getJSONArray("dosage");
      for (int i = 0; i < array.length(); i++) {
        res.getDosage().add(parseMedicationDispenseMedicationDispenseDispenseDosageComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private MedicationDispense.MedicationDispenseDispenseDosageComponent parseMedicationDispenseMedicationDispenseDispenseDosageComponent(JSONObject json, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseDispenseDosageComponent res = owner.new MedicationDispenseDispenseDosageComponent();
    parseElementProperties(json, res);
    Type additionalInstructions = parseType("additionalInstructions", json);
    if (additionalInstructions != null)
      res.setAdditionalInstructions(additionalInstructions);
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getJSONObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getJSONObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getJSONObject("method")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getJSONObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getJSONObject("maxDosePerPeriod")));
    return res;
  }

  private MedicationDispense.MedicationDispenseSubstitutionComponent parseMedicationDispenseMedicationDispenseSubstitutionComponent(JSONObject json, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseSubstitutionComponent res = owner.new MedicationDispenseSubstitutionComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("reason")) {
      JSONArray array = json.getJSONArray("reason");
      for (int i = 0; i < array.length(); i++) {
        res.getReason().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("responsibleParty")) {
      JSONArray array = json.getJSONArray("responsibleParty");
      for (int i = 0; i < array.length(); i++) {
        res.getResponsibleParty().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private MedicationPrescription parseMedicationPrescription(JSONObject json) throws Exception {
    MedicationPrescription res = new MedicationPrescription();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("dateWritten"))
      res.setDateWritten(parseDateTime(json.getString("dateWritten")));
    if (json.has("_dateWritten"))
      parseElementProperties(json.getJSONObject("_dateWritten"), res.getDateWritten());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), MedicationPrescription.MedicationPrescriptionStatus.Null, new MedicationPrescription().new MedicationPrescriptionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("prescriber"))
      res.setPrescriber(parseResourceReference(json.getJSONObject("prescriber")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    Type reasonForPrescribing = parseType("reasonForPrescribing", json);
    if (reasonForPrescribing != null)
      res.setReasonForPrescribing(reasonForPrescribing);
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getJSONObject("medication")));
    if (json.has("dosageInstruction")) {
      JSONArray array = json.getJSONArray("dosageInstruction");
      for (int i = 0; i < array.length(); i++) {
        res.getDosageInstruction().add(parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("dispense"))
      res.setDispense(parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(json.getJSONObject("dispense"), res));
    if (json.has("substitution"))
      res.setSubstitution(parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(json.getJSONObject("substitution"), res));
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionDosageInstructionComponent parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(JSONObject json, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDosageInstructionComponent res = owner.new MedicationPrescriptionDosageInstructionComponent();
    parseElementProperties(json, res);
    if (json.has("dosageInstructionsText"))
      res.setDosageInstructionsText(parseString(json.getString("dosageInstructionsText")));
    if (json.has("_dosageInstructionsText"))
      parseElementProperties(json.getJSONObject("_dosageInstructionsText"), res.getDosageInstructionsText());
    Type additionalInstructions = parseType("additionalInstructions", json);
    if (additionalInstructions != null)
      res.setAdditionalInstructions(additionalInstructions);
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getJSONObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getJSONObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getJSONObject("method")));
    if (json.has("doseQuantity"))
      res.setDoseQuantity(parseQuantity(json.getJSONObject("doseQuantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getJSONObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getJSONObject("maxDosePerPeriod")));
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionDispenseComponent parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(JSONObject json, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDispenseComponent res = owner.new MedicationPrescriptionDispenseComponent();
    parseElementProperties(json, res);
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getJSONObject("medication")));
    if (json.has("validityPeriod"))
      res.setValidityPeriod(parsePeriod(json.getJSONObject("validityPeriod")));
    if (json.has("numberOfRepeatsAllowed"))
      res.setNumberOfRepeatsAllowed(parseInteger(json.getString("numberOfRepeatsAllowed")));
    if (json.has("_numberOfRepeatsAllowed"))
      parseElementProperties(json.getJSONObject("_numberOfRepeatsAllowed"), res.getNumberOfRepeatsAllowed());
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("expectedSupplyDuration"))
      res.setExpectedSupplyDuration(parseDuration(json.getJSONObject("expectedSupplyDuration")));
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionSubstitutionComponent parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(JSONObject json, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionSubstitutionComponent res = owner.new MedicationPrescriptionSubstitutionComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getJSONObject("reason")));
    return res;
  }

  private MedicationStatement parseMedicationStatement(JSONObject json) throws Exception {
    MedicationStatement res = new MedicationStatement();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("wasNotGiven"))
      res.setWasNotGiven(parseBoolean(json.getBoolean("wasNotGiven")));
    if (json.has("_wasNotGiven"))
      parseElementProperties(json.getJSONObject("_wasNotGiven"), res.getWasNotGiven());
    if (json.has("reasonNotGiven")) {
      JSONArray array = json.getJSONArray("reasonNotGiven");
      for (int i = 0; i < array.length(); i++) {
        res.getReasonNotGiven().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("whenGiven"))
      res.setWhenGiven(parsePeriod(json.getJSONObject("whenGiven")));
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getJSONObject("medication")));
    if (json.has("administrationDevice")) {
      JSONArray array = json.getJSONArray("administrationDevice");
      for (int i = 0; i < array.length(); i++) {
        res.getAdministrationDevice().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("dosage")) {
      JSONArray array = json.getJSONArray("dosage");
      for (int i = 0; i < array.length(); i++) {
        res.getDosage().add(parseMedicationStatementMedicationStatementDosageComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private MedicationStatement.MedicationStatementDosageComponent parseMedicationStatementMedicationStatementDosageComponent(JSONObject json, MedicationStatement owner) throws Exception {
    MedicationStatement.MedicationStatementDosageComponent res = owner.new MedicationStatementDosageComponent();
    parseElementProperties(json, res);
    if (json.has("timing"))
      res.setTiming(parseSchedule(json.getJSONObject("timing")));
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getJSONObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getJSONObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getJSONObject("method")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getJSONObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getJSONObject("maxDosePerPeriod")));
    return res;
  }

  private Message parseMessage(JSONObject json) throws Exception {
    Message res = new Message();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseId(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("timestamp"))
      res.setTimestamp(parseInstant(json.getString("timestamp")));
    if (json.has("_timestamp"))
      parseElementProperties(json.getJSONObject("_timestamp"), res.getTimestamp());
    if (json.has("event"))
      res.setEvent(parseCoding(json.getJSONObject("event")));
    if (json.has("response"))
      res.setResponse(parseMessageMessageResponseComponent(json.getJSONObject("response"), res));
    if (json.has("source"))
      res.setSource(parseMessageMessageSourceComponent(json.getJSONObject("source"), res));
    if (json.has("destination")) {
      JSONArray array = json.getJSONArray("destination");
      for (int i = 0; i < array.length(); i++) {
        res.getDestination().add(parseMessageMessageDestinationComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("enterer"))
      res.setEnterer(parseResourceReference(json.getJSONObject("enterer")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getJSONObject("author")));
    if (json.has("receiver"))
      res.setReceiver(parseResourceReference(json.getJSONObject("receiver")));
    if (json.has("responsible"))
      res.setResponsible(parseResourceReference(json.getJSONObject("responsible")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getJSONObject("reason")));
    if (json.has("data")) {
      JSONArray array = json.getJSONArray("data");
      for (int i = 0; i < array.length(); i++) {
        res.getData().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Message.MessageResponseComponent parseMessageMessageResponseComponent(JSONObject json, Message owner) throws Exception {
    Message.MessageResponseComponent res = owner.new MessageResponseComponent();
    parseElementProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseId(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("code"))
      res.setCode(parseEnumeration(json.getString("code"), Message.ResponseCode.Null, new Message().new ResponseCodeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("details"))
      res.setDetails(parseResourceReference(json.getJSONObject("details")));
    return res;
  }

  private Message.MessageSourceComponent parseMessageMessageSourceComponent(JSONObject json, Message owner) throws Exception {
    Message.MessageSourceComponent res = owner.new MessageSourceComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("software"))
      res.setSoftware(parseString(json.getString("software")));
    if (json.has("_software"))
      parseElementProperties(json.getJSONObject("_software"), res.getSoftware());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("contact"))
      res.setContact(parseContact(json.getJSONObject("contact")));
    if (json.has("endpoint"))
      res.setEndpoint(parseUri(json.getString("endpoint")));
    if (json.has("_endpoint"))
      parseElementProperties(json.getJSONObject("_endpoint"), res.getEndpoint());
    return res;
  }

  private Message.MessageDestinationComponent parseMessageMessageDestinationComponent(JSONObject json, Message owner) throws Exception {
    Message.MessageDestinationComponent res = owner.new MessageDestinationComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getJSONObject("target")));
    if (json.has("endpoint"))
      res.setEndpoint(parseUri(json.getString("endpoint")));
    if (json.has("_endpoint"))
      parseElementProperties(json.getJSONObject("_endpoint"), res.getEndpoint());
    return res;
  }

  private Observation parseObservation(JSONObject json) throws Exception {
    Observation res = new Observation();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getJSONObject("name")));
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    if (json.has("interpretation"))
      res.setInterpretation(parseCodeableConcept(json.getJSONObject("interpretation")));
    if (json.has("comments"))
      res.setComments(parseString(json.getString("comments")));
    if (json.has("_comments"))
      parseElementProperties(json.getJSONObject("_comments"), res.getComments());
    Type applies = parseType("applies", json);
    if (applies != null)
      res.setApplies(applies);
    if (json.has("issued"))
      res.setIssued(parseInstant(json.getString("issued")));
    if (json.has("_issued"))
      parseElementProperties(json.getJSONObject("_issued"), res.getIssued());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Observation.ObservationStatus.Null, new Observation().new ObservationStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("reliability"))
      res.setReliability(parseEnumeration(json.getString("reliability"), Observation.ObservationReliability.Null, new Observation().new ObservationReliabilityEnumFactory()));
    if (json.has("_reliability"))
      parseElementProperties(json.getJSONObject("_reliability"), res.getReliability());
    if (json.has("bodySite"))
      res.setBodySite(parseCodeableConcept(json.getJSONObject("bodySite")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getJSONObject("method")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("performer"))
      res.setPerformer(parseResourceReference(json.getJSONObject("performer")));
    if (json.has("referenceRange")) {
      JSONArray array = json.getJSONArray("referenceRange");
      for (int i = 0; i < array.length(); i++) {
        res.getReferenceRange().add(parseObservationObservationReferenceRangeComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("component")) {
      JSONArray array = json.getJSONArray("component");
      for (int i = 0; i < array.length(); i++) {
        res.getComponent().add(parseObservationObservationComponentComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private Observation.ObservationReferenceRangeComponent parseObservationObservationReferenceRangeComponent(JSONObject json, Observation owner) throws Exception {
    Observation.ObservationReferenceRangeComponent res = owner.new ObservationReferenceRangeComponent();
    parseElementProperties(json, res);
    if (json.has("meaning"))
      res.setMeaning(parseCodeableConcept(json.getJSONObject("meaning")));
    Type range = parseType("range", json);
    if (range != null)
      res.setRange(range);
    return res;
  }

  private Observation.ObservationComponentComponent parseObservationObservationComponentComponent(JSONObject json, Observation owner) throws Exception {
    Observation.ObservationComponentComponent res = owner.new ObservationComponentComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getJSONObject("name")));
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    return res;
  }

  private OperationOutcome parseOperationOutcome(JSONObject json) throws Exception {
    OperationOutcome res = new OperationOutcome();
    parseResourceProperties(json, res);
    if (json.has("issue")) {
      JSONArray array = json.getJSONArray("issue");
      for (int i = 0; i < array.length(); i++) {
        res.getIssue().add(parseOperationOutcomeOperationOutcomeIssueComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private OperationOutcome.OperationOutcomeIssueComponent parseOperationOutcomeOperationOutcomeIssueComponent(JSONObject json, OperationOutcome owner) throws Exception {
    OperationOutcome.OperationOutcomeIssueComponent res = owner.new OperationOutcomeIssueComponent();
    parseElementProperties(json, res);
    if (json.has("severity"))
      res.setSeverity(parseEnumeration(json.getString("severity"), OperationOutcome.IssueSeverity.Null, new OperationOutcome().new IssueSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getJSONObject("_severity"), res.getSeverity());
    if (json.has("type"))
      res.setType(parseCoding(json.getJSONObject("type")));
    if (json.has("details"))
      res.setDetails(parseString(json.getString("details")));
    if (json.has("_details"))
      parseElementProperties(json.getJSONObject("_details"), res.getDetails());
    if (json.has("location")) {
      JSONArray array = json.getJSONArray("location");
      for (int i = 0; i < array.length(); i++) {
        res.getLocation().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_location")) {
      JSONArray array = json.getJSONArray("_location");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getLocation().size())
          res.getLocation().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getLocation().get(i));
      }
    };
    return res;
  }

  private Order parseOrder(JSONObject json) throws Exception {
    Order res = new Order();
    parseResourceProperties(json, res);
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getJSONObject("source")));
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getJSONObject("target")));
    if (json.has("reason"))
      res.setReason(parseString(json.getString("reason")));
    if (json.has("_reason"))
      parseElementProperties(json.getJSONObject("_reason"), res.getReason());
    if (json.has("authority"))
      res.setAuthority(parseResourceReference(json.getJSONObject("authority")));
    if (json.has("when"))
      res.setWhen(parseOrderOrderWhenComponent(json.getJSONObject("when"), res));
    if (json.has("detail")) {
      JSONArray array = json.getJSONArray("detail");
      for (int i = 0; i < array.length(); i++) {
        res.getDetail().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Order.OrderWhenComponent parseOrderOrderWhenComponent(JSONObject json, Order owner) throws Exception {
    Order.OrderWhenComponent res = owner.new OrderWhenComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("schedule"))
      res.setSchedule(parseSchedule(json.getJSONObject("schedule")));
    return res;
  }

  private OrderResponse parseOrderResponse(JSONObject json) throws Exception {
    OrderResponse res = new OrderResponse();
    parseResourceProperties(json, res);
    if (json.has("request"))
      res.setRequest(parseResourceReference(json.getJSONObject("request")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("who"))
      res.setWho(parseResourceReference(json.getJSONObject("who")));
    if (json.has("authority"))
      res.setAuthority(parseResourceReference(json.getJSONObject("authority")));
    if (json.has("cost"))
      res.setCost(parseMoney(json.getJSONObject("cost")));
    if (json.has("code"))
      res.setCode(parseEnumeration(json.getString("code"), OrderResponse.OrderOutcomeCode.Null, new OrderResponse().new OrderOutcomeCodeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("fulfillment")) {
      JSONArray array = json.getJSONArray("fulfillment");
      for (int i = 0; i < array.length(); i++) {
        res.getFulfillment().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Organization parseOrganization(JSONObject json) throws Exception {
    Organization res = new Organization();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("address")) {
      JSONArray array = json.getJSONArray("address");
      for (int i = 0; i < array.length(); i++) {
        res.getAddress().add(parseAddress(array.getJSONObject(i)));
      }
    };
    if (json.has("partOf"))
      res.setPartOf(parseResourceReference(json.getJSONObject("partOf")));
    if (json.has("contact")) {
      JSONArray array = json.getJSONArray("contact");
      for (int i = 0; i < array.length(); i++) {
        res.getContact().add(parseOrganizationOrganizationContactComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("active"))
      res.setActive(parseBoolean(json.getBoolean("active")));
    if (json.has("_active"))
      parseElementProperties(json.getJSONObject("_active"), res.getActive());
    return res;
  }

  private Organization.OrganizationContactComponent parseOrganizationOrganizationContactComponent(JSONObject json, Organization owner) throws Exception {
    Organization.OrganizationContactComponent res = owner.new OrganizationContactComponent();
    parseElementProperties(json, res);
    if (json.has("purpose"))
      res.setPurpose(parseCodeableConcept(json.getJSONObject("purpose")));
    if (json.has("name"))
      res.setName(parseHumanName(json.getJSONObject("name")));
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getJSONObject("address")));
    if (json.has("gender"))
      res.setGender(parseCodeableConcept(json.getJSONObject("gender")));
    return res;
  }

  private Other parseOther(JSONObject json) throws Exception {
    Other res = new Other();
    parseResourceProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getJSONObject("author")));
    if (json.has("created"))
      res.setCreated(parseDate(json.getString("created")));
    if (json.has("_created"))
      parseElementProperties(json.getJSONObject("_created"), res.getCreated());
    return res;
  }

  private Patient parsePatient(JSONObject json) throws Exception {
    Patient res = new Patient();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("name")) {
      JSONArray array = json.getJSONArray("name");
      for (int i = 0; i < array.length(); i++) {
        res.getName().add(parseHumanName(array.getJSONObject(i)));
      }
    };
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("gender"))
      res.setGender(parseCodeableConcept(json.getJSONObject("gender")));
    if (json.has("birthDate"))
      res.setBirthDate(parseDateTime(json.getString("birthDate")));
    if (json.has("_birthDate"))
      parseElementProperties(json.getJSONObject("_birthDate"), res.getBirthDate());
    Type deceased = parseType("deceased", json);
    if (deceased != null)
      res.setDeceased(deceased);
    if (json.has("address")) {
      JSONArray array = json.getJSONArray("address");
      for (int i = 0; i < array.length(); i++) {
        res.getAddress().add(parseAddress(array.getJSONObject(i)));
      }
    };
    if (json.has("maritalStatus"))
      res.setMaritalStatus(parseCodeableConcept(json.getJSONObject("maritalStatus")));
    Type multipleBirth = parseType("multipleBirth", json);
    if (multipleBirth != null)
      res.setMultipleBirth(multipleBirth);
    if (json.has("photo")) {
      JSONArray array = json.getJSONArray("photo");
      for (int i = 0; i < array.length(); i++) {
        res.getPhoto().add(parseAttachment(array.getJSONObject(i)));
      }
    };
    if (json.has("contact")) {
      JSONArray array = json.getJSONArray("contact");
      for (int i = 0; i < array.length(); i++) {
        res.getContact().add(parsePatientContactComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("animal"))
      res.setAnimal(parsePatientAnimalComponent(json.getJSONObject("animal"), res));
    if (json.has("communication")) {
      JSONArray array = json.getJSONArray("communication");
      for (int i = 0; i < array.length(); i++) {
        res.getCommunication().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("provider"))
      res.setProvider(parseResourceReference(json.getJSONObject("provider")));
    if (json.has("link")) {
      JSONArray array = json.getJSONArray("link");
      for (int i = 0; i < array.length(); i++) {
        res.getLink().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("active"))
      res.setActive(parseBoolean(json.getBoolean("active")));
    if (json.has("_active"))
      parseElementProperties(json.getJSONObject("_active"), res.getActive());
    return res;
  }

  private Patient.ContactComponent parsePatientContactComponent(JSONObject json, Patient owner) throws Exception {
    Patient.ContactComponent res = owner.new ContactComponent();
    parseElementProperties(json, res);
    if (json.has("relationship")) {
      JSONArray array = json.getJSONArray("relationship");
      for (int i = 0; i < array.length(); i++) {
        res.getRelationship().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("name"))
      res.setName(parseHumanName(json.getJSONObject("name")));
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getJSONObject("address")));
    if (json.has("gender"))
      res.setGender(parseCodeableConcept(json.getJSONObject("gender")));
    if (json.has("organization"))
      res.setOrganization(parseResourceReference(json.getJSONObject("organization")));
    return res;
  }

  private Patient.AnimalComponent parsePatientAnimalComponent(JSONObject json, Patient owner) throws Exception {
    Patient.AnimalComponent res = owner.new AnimalComponent();
    parseElementProperties(json, res);
    if (json.has("species"))
      res.setSpecies(parseCodeableConcept(json.getJSONObject("species")));
    if (json.has("breed"))
      res.setBreed(parseCodeableConcept(json.getJSONObject("breed")));
    if (json.has("genderStatus"))
      res.setGenderStatus(parseCodeableConcept(json.getJSONObject("genderStatus")));
    return res;
  }

  private Practitioner parsePractitioner(JSONObject json) throws Exception {
    Practitioner res = new Practitioner();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("name"))
      res.setName(parseHumanName(json.getJSONObject("name")));
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getJSONObject("address")));
    if (json.has("gender"))
      res.setGender(parseCodeableConcept(json.getJSONObject("gender")));
    if (json.has("birthDate"))
      res.setBirthDate(parseDateTime(json.getString("birthDate")));
    if (json.has("_birthDate"))
      parseElementProperties(json.getJSONObject("_birthDate"), res.getBirthDate());
    if (json.has("photo")) {
      JSONArray array = json.getJSONArray("photo");
      for (int i = 0; i < array.length(); i++) {
        res.getPhoto().add(parseAttachment(array.getJSONObject(i)));
      }
    };
    if (json.has("organization"))
      res.setOrganization(parseResourceReference(json.getJSONObject("organization")));
    if (json.has("role")) {
      JSONArray array = json.getJSONArray("role");
      for (int i = 0; i < array.length(); i++) {
        res.getRole().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("specialty")) {
      JSONArray array = json.getJSONArray("specialty");
      for (int i = 0; i < array.length(); i++) {
        res.getSpecialty().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("qualification")) {
      JSONArray array = json.getJSONArray("qualification");
      for (int i = 0; i < array.length(); i++) {
        res.getQualification().add(parsePractitionerPractitionerQualificationComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("communication")) {
      JSONArray array = json.getJSONArray("communication");
      for (int i = 0; i < array.length(); i++) {
        res.getCommunication().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Practitioner.PractitionerQualificationComponent parsePractitionerPractitionerQualificationComponent(JSONObject json, Practitioner owner) throws Exception {
    Practitioner.PractitionerQualificationComponent res = owner.new PractitionerQualificationComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getJSONObject("code")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("issuer"))
      res.setIssuer(parseResourceReference(json.getJSONObject("issuer")));
    return res;
  }

  private Procedure parseProcedure(JSONObject json) throws Exception {
    Procedure res = new Procedure();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("bodySite")) {
      JSONArray array = json.getJSONArray("bodySite");
      for (int i = 0; i < array.length(); i++) {
        res.getBodySite().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("indication")) {
      JSONArray array = json.getJSONArray("indication");
      for (int i = 0; i < array.length(); i++) {
        res.getIndication().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("performer")) {
      JSONArray array = json.getJSONArray("performer");
      for (int i = 0; i < array.length(); i++) {
        res.getPerformer().add(parseProcedureProcedurePerformerComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("date"))
      res.setDate(parsePeriod(json.getJSONObject("date")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    if (json.has("outcome"))
      res.setOutcome(parseString(json.getString("outcome")));
    if (json.has("_outcome"))
      parseElementProperties(json.getJSONObject("_outcome"), res.getOutcome());
    if (json.has("report")) {
      JSONArray array = json.getJSONArray("report");
      for (int i = 0; i < array.length(); i++) {
        res.getReport().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("complication")) {
      JSONArray array = json.getJSONArray("complication");
      for (int i = 0; i < array.length(); i++) {
        res.getComplication().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("followUp"))
      res.setFollowUp(parseString(json.getString("followUp")));
    if (json.has("_followUp"))
      parseElementProperties(json.getJSONObject("_followUp"), res.getFollowUp());
    if (json.has("relatedItem")) {
      JSONArray array = json.getJSONArray("relatedItem");
      for (int i = 0; i < array.length(); i++) {
        res.getRelatedItem().add(parseProcedureProcedureRelatedItemComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.getString("notes")));
    if (json.has("_notes"))
      parseElementProperties(json.getJSONObject("_notes"), res.getNotes());
    return res;
  }

  private Procedure.ProcedurePerformerComponent parseProcedureProcedurePerformerComponent(JSONObject json, Procedure owner) throws Exception {
    Procedure.ProcedurePerformerComponent res = owner.new ProcedurePerformerComponent();
    parseElementProperties(json, res);
    if (json.has("person"))
      res.setPerson(parseResourceReference(json.getJSONObject("person")));
    if (json.has("role"))
      res.setRole(parseCodeableConcept(json.getJSONObject("role")));
    return res;
  }

  private Procedure.ProcedureRelatedItemComponent parseProcedureProcedureRelatedItemComponent(JSONObject json, Procedure owner) throws Exception {
    Procedure.ProcedureRelatedItemComponent res = owner.new ProcedureRelatedItemComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), Procedure.ProcedureRelationshipType.Null, new Procedure().new ProcedureRelationshipTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getJSONObject("target")));
    return res;
  }

  private Profile parseProfile(JSONObject json) throws Exception {
    Profile res = new Profile();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.getString("publisher")));
    if (json.has("_publisher"))
      parseElementProperties(json.getJSONObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("code")) {
      JSONArray array = json.getJSONArray("code");
      for (int i = 0; i < array.length(); i++) {
        res.getCode().add(parseCoding(array.getJSONObject(i)));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Profile.ResourceProfileStatus.Null, new Profile().new ResourceProfileStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.getBoolean("experimental")));
    if (json.has("_experimental"))
      parseElementProperties(json.getJSONObject("_experimental"), res.getExperimental());
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("fhirVersion"))
      res.setFhirVersion(parseId(json.getString("fhirVersion")));
    if (json.has("_fhirVersion"))
      parseElementProperties(json.getJSONObject("_fhirVersion"), res.getFhirVersion());
    if (json.has("structure")) {
      JSONArray array = json.getJSONArray("structure");
      for (int i = 0; i < array.length(); i++) {
        res.getStructure().add(parseProfileProfileStructureComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("extensionDefn")) {
      JSONArray array = json.getJSONArray("extensionDefn");
      for (int i = 0; i < array.length(); i++) {
        res.getExtensionDefn().add(parseProfileProfileExtensionDefnComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private Profile.ProfileStructureComponent parseProfileProfileStructureComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ProfileStructureComponent res = owner.new ProfileStructureComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCode(json.getString("type")));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("publish"))
      res.setPublish(parseBoolean(json.getBoolean("publish")));
    if (json.has("_publish"))
      parseElementProperties(json.getJSONObject("_publish"), res.getPublish());
    if (json.has("purpose"))
      res.setPurpose(parseString(json.getString("purpose")));
    if (json.has("_purpose"))
      parseElementProperties(json.getJSONObject("_purpose"), res.getPurpose());
    if (json.has("element")) {
      JSONArray array = json.getJSONArray("element");
      for (int i = 0; i < array.length(); i++) {
        res.getElement().add(parseProfileElementComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Profile.ElementComponent parseProfileElementComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ElementComponent res = owner.new ElementComponent();
    parseElementProperties(json, res);
    if (json.has("path"))
      res.setPath(parseString(json.getString("path")));
    if (json.has("_path"))
      parseElementProperties(json.getJSONObject("_path"), res.getPath());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("slicing"))
      res.setSlicing(parseProfileElementSlicingComponent(json.getJSONObject("slicing"), owner));
    if (json.has("definition"))
      res.setDefinition(parseProfileElementDefinitionComponent(json.getJSONObject("definition"), owner));
    return res;
  }

  private Profile.ElementSlicingComponent parseProfileElementSlicingComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ElementSlicingComponent res = owner.new ElementSlicingComponent();
    parseElementProperties(json, res);
    if (json.has("discriminator"))
      res.setDiscriminator(parseId(json.getString("discriminator")));
    if (json.has("_discriminator"))
      parseElementProperties(json.getJSONObject("_discriminator"), res.getDiscriminator());
    if (json.has("ordered"))
      res.setOrdered(parseBoolean(json.getBoolean("ordered")));
    if (json.has("_ordered"))
      parseElementProperties(json.getJSONObject("_ordered"), res.getOrdered());
    if (json.has("rules"))
      res.setRules(parseEnumeration(json.getString("rules"), Profile.ResourceSlicingRules.Null, new Profile().new ResourceSlicingRulesEnumFactory()));
    if (json.has("_rules"))
      parseElementProperties(json.getJSONObject("_rules"), res.getRules());
    return res;
  }

  private Profile.ElementDefinitionComponent parseProfileElementDefinitionComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionComponent res = owner.new ElementDefinitionComponent();
    parseElementProperties(json, res);
    if (json.has("short"))
      res.setShort(parseString(json.getString("short")));
    if (json.has("_short"))
      parseElementProperties(json.getJSONObject("_short"), res.getShort());
    if (json.has("formal"))
      res.setFormal(parseString(json.getString("formal")));
    if (json.has("_formal"))
      parseElementProperties(json.getJSONObject("_formal"), res.getFormal());
    if (json.has("comments"))
      res.setComments(parseString(json.getString("comments")));
    if (json.has("_comments"))
      parseElementProperties(json.getJSONObject("_comments"), res.getComments());
    if (json.has("requirements"))
      res.setRequirements(parseString(json.getString("requirements")));
    if (json.has("_requirements"))
      parseElementProperties(json.getJSONObject("_requirements"), res.getRequirements());
    if (json.has("synonym")) {
      JSONArray array = json.getJSONArray("synonym");
      for (int i = 0; i < array.length(); i++) {
        res.getSynonym().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_synonym")) {
      JSONArray array = json.getJSONArray("_synonym");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getSynonym().size())
          res.getSynonym().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getSynonym().get(i));
      }
    };
    if (json.has("min"))
      res.setMin(parseInteger(json.getString("min")));
    if (json.has("_min"))
      parseElementProperties(json.getJSONObject("_min"), res.getMin());
    if (json.has("max"))
      res.setMax(parseString(json.getString("max")));
    if (json.has("_max"))
      parseElementProperties(json.getJSONObject("_max"), res.getMax());
    if (json.has("type")) {
      JSONArray array = json.getJSONArray("type");
      for (int i = 0; i < array.length(); i++) {
        res.getType().add(parseProfileTypeRefComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("nameReference"))
      res.setNameReference(parseString(json.getString("nameReference")));
    if (json.has("_nameReference"))
      parseElementProperties(json.getJSONObject("_nameReference"), res.getNameReference());
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    Type example = parseType("example", json);
    if (example != null)
      res.setExample(example);
    if (json.has("maxLength"))
      res.setMaxLength(parseInteger(json.getString("maxLength")));
    if (json.has("_maxLength"))
      parseElementProperties(json.getJSONObject("_maxLength"), res.getMaxLength());
    if (json.has("condition")) {
      JSONArray array = json.getJSONArray("condition");
      for (int i = 0; i < array.length(); i++) {
        res.getCondition().add(parseId(array.getString(i)));
      }
    };
    if (json.has("_condition")) {
      JSONArray array = json.getJSONArray("_condition");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getCondition().size())
          res.getCondition().add(parseId(null));
        parseElementProperties(array.getJSONObject(i), res.getCondition().get(i));
      }
    };
    if (json.has("constraint")) {
      JSONArray array = json.getJSONArray("constraint");
      for (int i = 0; i < array.length(); i++) {
        res.getConstraint().add(parseProfileElementDefinitionConstraintComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("mustSupport"))
      res.setMustSupport(parseBoolean(json.getBoolean("mustSupport")));
    if (json.has("_mustSupport"))
      parseElementProperties(json.getJSONObject("_mustSupport"), res.getMustSupport());
    if (json.has("isModifier"))
      res.setIsModifier(parseBoolean(json.getBoolean("isModifier")));
    if (json.has("_isModifier"))
      parseElementProperties(json.getJSONObject("_isModifier"), res.getIsModifier());
    if (json.has("binding"))
      res.setBinding(parseProfileElementDefinitionBindingComponent(json.getJSONObject("binding"), owner));
    if (json.has("mapping")) {
      JSONArray array = json.getJSONArray("mapping");
      for (int i = 0; i < array.length(); i++) {
        res.getMapping().add(parseProfileElementDefinitionMappingComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private Profile.TypeRefComponent parseProfileTypeRefComponent(JSONObject json, Profile owner) throws Exception {
    Profile.TypeRefComponent res = owner.new TypeRefComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("profile"))
      res.setProfile(parseUri(json.getString("profile")));
    if (json.has("_profile"))
      parseElementProperties(json.getJSONObject("_profile"), res.getProfile());
    if (json.has("aggregation")) {
      JSONArray array = json.getJSONArray("aggregation");
      for (int i = 0; i < array.length(); i++) {
        res.getAggregation().add(parseEnumeration(array.getString(i), Profile.ResourceAggregationMode.Null, new Profile().new ResourceAggregationModeEnumFactory()));
      }
    };
    if (json.has("_aggregation")) {
      JSONArray array = json.getJSONArray("_aggregation");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getAggregation().size())
          res.getAggregation().add(parseEnumeration(null, Profile.ResourceAggregationMode.Null, new Profile().new ResourceAggregationModeEnumFactory()));
        parseElementProperties(array.getJSONObject(i), res.getAggregation().get(i));
      }
    };
    return res;
  }

  private Profile.ElementDefinitionConstraintComponent parseProfileElementDefinitionConstraintComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionConstraintComponent res = owner.new ElementDefinitionConstraintComponent();
    parseElementProperties(json, res);
    if (json.has("key"))
      res.setKey(parseId(json.getString("key")));
    if (json.has("_key"))
      parseElementProperties(json.getJSONObject("_key"), res.getKey());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("severity"))
      res.setSeverity(parseEnumeration(json.getString("severity"), Profile.ConstraintSeverity.Null, new Profile().new ConstraintSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getJSONObject("_severity"), res.getSeverity());
    if (json.has("human"))
      res.setHuman(parseString(json.getString("human")));
    if (json.has("_human"))
      parseElementProperties(json.getJSONObject("_human"), res.getHuman());
    if (json.has("xpath"))
      res.setXpath(parseString(json.getString("xpath")));
    if (json.has("_xpath"))
      parseElementProperties(json.getJSONObject("_xpath"), res.getXpath());
    return res;
  }

  private Profile.ElementDefinitionBindingComponent parseProfileElementDefinitionBindingComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionBindingComponent res = owner.new ElementDefinitionBindingComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("isExtensible"))
      res.setIsExtensible(parseBoolean(json.getBoolean("isExtensible")));
    if (json.has("_isExtensible"))
      parseElementProperties(json.getJSONObject("_isExtensible"), res.getIsExtensible());
    if (json.has("conformance"))
      res.setConformance(parseEnumeration(json.getString("conformance"), Profile.BindingConformance.Null, new Profile().new BindingConformanceEnumFactory()));
    if (json.has("_conformance"))
      parseElementProperties(json.getJSONObject("_conformance"), res.getConformance());
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    Type reference = parseType("reference", json);
    if (reference != null)
      res.setReference(reference);
    return res;
  }

  private Profile.ElementDefinitionMappingComponent parseProfileElementDefinitionMappingComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionMappingComponent res = owner.new ElementDefinitionMappingComponent();
    parseElementProperties(json, res);
    if (json.has("target"))
      res.setTarget(parseUri(json.getString("target")));
    if (json.has("_target"))
      parseElementProperties(json.getJSONObject("_target"), res.getTarget());
    if (json.has("map"))
      res.setMap(parseString(json.getString("map")));
    if (json.has("_map"))
      parseElementProperties(json.getJSONObject("_map"), res.getMap());
    return res;
  }

  private Profile.ProfileExtensionDefnComponent parseProfileProfileExtensionDefnComponent(JSONObject json, Profile owner) throws Exception {
    Profile.ProfileExtensionDefnComponent res = owner.new ProfileExtensionDefnComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("display"))
      res.setDisplay(parseString(json.getString("display")));
    if (json.has("_display"))
      parseElementProperties(json.getJSONObject("_display"), res.getDisplay());
    if (json.has("contextType"))
      res.setContextType(parseEnumeration(json.getString("contextType"), Profile.ExtensionContext.Null, new Profile().new ExtensionContextEnumFactory()));
    if (json.has("_contextType"))
      parseElementProperties(json.getJSONObject("_contextType"), res.getContextType());
    if (json.has("context")) {
      JSONArray array = json.getJSONArray("context");
      for (int i = 0; i < array.length(); i++) {
        res.getContext().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_context")) {
      JSONArray array = json.getJSONArray("_context");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getContext().size())
          res.getContext().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getContext().get(i));
      }
    };
    if (json.has("definition"))
      res.setDefinition(parseProfileElementDefinitionComponent(json.getJSONObject("definition"), owner));
    return res;
  }

  private Provenance parseProvenance(JSONObject json) throws Exception {
    Provenance res = new Provenance();
    parseResourceProperties(json, res);
    if (json.has("target")) {
      JSONArray array = json.getJSONArray("target");
      for (int i = 0; i < array.length(); i++) {
        res.getTarget().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getJSONObject("period")));
    if (json.has("recorded"))
      res.setRecorded(parseInstant(json.getString("recorded")));
    if (json.has("_recorded"))
      parseElementProperties(json.getJSONObject("_recorded"), res.getRecorded());
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getJSONObject("reason")));
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getJSONObject("location")));
    if (json.has("policy")) {
      JSONArray array = json.getJSONArray("policy");
      for (int i = 0; i < array.length(); i++) {
        res.getPolicy().add(parseUri(array.getString(i)));
      }
    };
    if (json.has("_policy")) {
      JSONArray array = json.getJSONArray("_policy");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getPolicy().size())
          res.getPolicy().add(parseUri(null));
        parseElementProperties(array.getJSONObject(i), res.getPolicy().get(i));
      }
    };
    if (json.has("agent")) {
      JSONArray array = json.getJSONArray("agent");
      for (int i = 0; i < array.length(); i++) {
        res.getAgent().add(parseProvenanceProvenanceAgentComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("entity")) {
      JSONArray array = json.getJSONArray("entity");
      for (int i = 0; i < array.length(); i++) {
        res.getEntity().add(parseProvenanceProvenanceEntityComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("signature"))
      res.setSignature(parseString(json.getString("signature")));
    if (json.has("_signature"))
      parseElementProperties(json.getJSONObject("_signature"), res.getSignature());
    return res;
  }

  private Provenance.ProvenanceAgentComponent parseProvenanceProvenanceAgentComponent(JSONObject json, Provenance owner) throws Exception {
    Provenance.ProvenanceAgentComponent res = owner.new ProvenanceAgentComponent();
    parseElementProperties(json, res);
    if (json.has("role"))
      res.setRole(parseCoding(json.getJSONObject("role")));
    if (json.has("type"))
      res.setType(parseCoding(json.getJSONObject("type")));
    if (json.has("reference"))
      res.setReference(parseUri(json.getString("reference")));
    if (json.has("_reference"))
      parseElementProperties(json.getJSONObject("_reference"), res.getReference());
    if (json.has("display"))
      res.setDisplay(parseString(json.getString("display")));
    if (json.has("_display"))
      parseElementProperties(json.getJSONObject("_display"), res.getDisplay());
    return res;
  }

  private Provenance.ProvenanceEntityComponent parseProvenanceProvenanceEntityComponent(JSONObject json, Provenance owner) throws Exception {
    Provenance.ProvenanceEntityComponent res = owner.new ProvenanceEntityComponent();
    parseElementProperties(json, res);
    if (json.has("role"))
      res.setRole(parseEnumeration(json.getString("role"), Provenance.ProvenanceEntityRole.Null, new Provenance().new ProvenanceEntityRoleEnumFactory()));
    if (json.has("_role"))
      parseElementProperties(json.getJSONObject("_role"), res.getRole());
    if (json.has("type"))
      res.setType(parseCoding(json.getJSONObject("type")));
    if (json.has("reference"))
      res.setReference(parseUri(json.getString("reference")));
    if (json.has("_reference"))
      parseElementProperties(json.getJSONObject("_reference"), res.getReference());
    if (json.has("display"))
      res.setDisplay(parseString(json.getString("display")));
    if (json.has("_display"))
      parseElementProperties(json.getJSONObject("_display"), res.getDisplay());
    if (json.has("agent"))
      res.setAgent(parseProvenanceProvenanceAgentComponent(json.getJSONObject("agent"), owner));
    return res;
  }

  private Query parseQuery(JSONObject json) throws Exception {
    Query res = new Query();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseUri(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("parameter")) {
      JSONArray array = json.getJSONArray("parameter");
      for (int i = 0; i < array.length(); i++) {
        res.getParameter().add(parseExtension(array.getJSONObject(i)));
      }
    };
    if (json.has("response"))
      res.setResponse(parseQueryQueryResponseComponent(json.getJSONObject("response"), res));
    return res;
  }

  private Query.QueryResponseComponent parseQueryQueryResponseComponent(JSONObject json, Query owner) throws Exception {
    Query.QueryResponseComponent res = owner.new QueryResponseComponent();
    parseElementProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseUri(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("outcome"))
      res.setOutcome(parseEnumeration(json.getString("outcome"), Query.QueryOutcome.Null, new Query().new QueryOutcomeEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getJSONObject("_outcome"), res.getOutcome());
    if (json.has("total"))
      res.setTotal(parseInteger(json.getString("total")));
    if (json.has("_total"))
      parseElementProperties(json.getJSONObject("_total"), res.getTotal());
    if (json.has("parameter")) {
      JSONArray array = json.getJSONArray("parameter");
      for (int i = 0; i < array.length(); i++) {
        res.getParameter().add(parseExtension(array.getJSONObject(i)));
      }
    };
    if (json.has("first")) {
      JSONArray array = json.getJSONArray("first");
      for (int i = 0; i < array.length(); i++) {
        res.getFirst().add(parseExtension(array.getJSONObject(i)));
      }
    };
    if (json.has("previous")) {
      JSONArray array = json.getJSONArray("previous");
      for (int i = 0; i < array.length(); i++) {
        res.getPrevious().add(parseExtension(array.getJSONObject(i)));
      }
    };
    if (json.has("next")) {
      JSONArray array = json.getJSONArray("next");
      for (int i = 0; i < array.length(); i++) {
        res.getNext().add(parseExtension(array.getJSONObject(i)));
      }
    };
    if (json.has("last")) {
      JSONArray array = json.getJSONArray("last");
      for (int i = 0; i < array.length(); i++) {
        res.getLast().add(parseExtension(array.getJSONObject(i)));
      }
    };
    if (json.has("reference")) {
      JSONArray array = json.getJSONArray("reference");
      for (int i = 0; i < array.length(); i++) {
        res.getReference().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Questionnaire parseQuestionnaire(JSONObject json) throws Exception {
    Questionnaire res = new Questionnaire();
    parseResourceProperties(json, res);
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), Questionnaire.ObservationStatus.Null, new Questionnaire().new ObservationStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("authored"))
      res.setAuthored(parseDateTime(json.getString("authored")));
    if (json.has("_authored"))
      parseElementProperties(json.getJSONObject("_authored"), res.getAuthored());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getJSONObject("author")));
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getJSONObject("source")));
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getJSONObject("name")));
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getJSONObject("encounter")));
    if (json.has("question")) {
      JSONArray array = json.getJSONArray("question");
      for (int i = 0; i < array.length(); i++) {
        res.getQuestion().add(parseQuestionnaireQuestionComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("group")) {
      JSONArray array = json.getJSONArray("group");
      for (int i = 0; i < array.length(); i++) {
        res.getGroup().add(parseQuestionnaireGroupComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private Questionnaire.QuestionComponent parseQuestionnaireQuestionComponent(JSONObject json, Questionnaire owner) throws Exception {
    Questionnaire.QuestionComponent res = owner.new QuestionComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getJSONObject("name")));
    if (json.has("text"))
      res.setText(parseString(json.getString("text")));
    if (json.has("_text"))
      parseElementProperties(json.getJSONObject("_text"), res.getText());
    Type answer = parseType("answer", json);
    if (answer != null)
      res.setAnswer(answer);
    if (json.has("choice")) {
      JSONArray array = json.getJSONArray("choice");
      for (int i = 0; i < array.length(); i++) {
        res.getChoice().add(parseCoding(array.getJSONObject(i)));
      }
    };
    Type options = parseType("options", json);
    if (options != null)
      res.setOptions(options);
    Type data = parseType("data", json);
    if (data != null)
      res.setData(data);
    if (json.has("remarks"))
      res.setRemarks(parseString(json.getString("remarks")));
    if (json.has("_remarks"))
      parseElementProperties(json.getJSONObject("_remarks"), res.getRemarks());
    return res;
  }

  private Questionnaire.GroupComponent parseQuestionnaireGroupComponent(JSONObject json, Questionnaire owner) throws Exception {
    Questionnaire.GroupComponent res = owner.new GroupComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getJSONObject("name")));
    if (json.has("header"))
      res.setHeader(parseString(json.getString("header")));
    if (json.has("_header"))
      parseElementProperties(json.getJSONObject("_header"), res.getHeader());
    if (json.has("text"))
      res.setText(parseString(json.getString("text")));
    if (json.has("_text"))
      parseElementProperties(json.getJSONObject("_text"), res.getText());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("question")) {
      JSONArray array = json.getJSONArray("question");
      for (int i = 0; i < array.length(); i++) {
        res.getQuestion().add(parseQuestionnaireQuestionComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("group")) {
      JSONArray array = json.getJSONArray("group");
      for (int i = 0; i < array.length(); i++) {
        res.getGroup().add(parseQuestionnaireGroupComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private RelatedPerson parseRelatedPerson(JSONObject json) throws Exception {
    RelatedPerson res = new RelatedPerson();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("relationship"))
      res.setRelationship(parseCodeableConcept(json.getJSONObject("relationship")));
    if (json.has("name"))
      res.setName(parseHumanName(json.getJSONObject("name")));
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("gender"))
      res.setGender(parseCodeableConcept(json.getJSONObject("gender")));
    if (json.has("address"))
      res.setAddress(parseAddress(json.getJSONObject("address")));
    if (json.has("photo")) {
      JSONArray array = json.getJSONArray("photo");
      for (int i = 0; i < array.length(); i++) {
        res.getPhoto().add(parseAttachment(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private SecurityEvent parseSecurityEvent(JSONObject json) throws Exception {
    SecurityEvent res = new SecurityEvent();
    parseResourceProperties(json, res);
    if (json.has("event"))
      res.setEvent(parseSecurityEventSecurityEventEventComponent(json.getJSONObject("event"), res));
    if (json.has("participant")) {
      JSONArray array = json.getJSONArray("participant");
      for (int i = 0; i < array.length(); i++) {
        res.getParticipant().add(parseSecurityEventSecurityEventParticipantComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("source"))
      res.setSource(parseSecurityEventSecurityEventSourceComponent(json.getJSONObject("source"), res));
    if (json.has("object")) {
      JSONArray array = json.getJSONArray("object");
      for (int i = 0; i < array.length(); i++) {
        res.getObject().add(parseSecurityEventSecurityEventObjectComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private SecurityEvent.SecurityEventEventComponent parseSecurityEventSecurityEventEventComponent(JSONObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventEventComponent res = owner.new SecurityEventEventComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("subtype")) {
      JSONArray array = json.getJSONArray("subtype");
      for (int i = 0; i < array.length(); i++) {
        res.getSubtype().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("action"))
      res.setAction(parseEnumeration(json.getString("action"), SecurityEvent.SecurityEventAction.Null, new SecurityEvent().new SecurityEventActionEnumFactory()));
    if (json.has("_action"))
      parseElementProperties(json.getJSONObject("_action"), res.getAction());
    if (json.has("dateTime"))
      res.setDateTime(parseInstant(json.getString("dateTime")));
    if (json.has("_dateTime"))
      parseElementProperties(json.getJSONObject("_dateTime"), res.getDateTime());
    if (json.has("outcome"))
      res.setOutcome(parseEnumeration(json.getString("outcome"), SecurityEvent.SecurityEventOutcome.Null, new SecurityEvent().new SecurityEventOutcomeEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getJSONObject("_outcome"), res.getOutcome());
    if (json.has("outcomeDesc"))
      res.setOutcomeDesc(parseString(json.getString("outcomeDesc")));
    if (json.has("_outcomeDesc"))
      parseElementProperties(json.getJSONObject("_outcomeDesc"), res.getOutcomeDesc());
    return res;
  }

  private SecurityEvent.SecurityEventParticipantComponent parseSecurityEventSecurityEventParticipantComponent(JSONObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventParticipantComponent res = owner.new SecurityEventParticipantComponent();
    parseElementProperties(json, res);
    if (json.has("role")) {
      JSONArray array = json.getJSONArray("role");
      for (int i = 0; i < array.length(); i++) {
        res.getRole().add(parseCodeableConcept(array.getJSONObject(i)));
      }
    };
    if (json.has("reference"))
      res.setReference(parseResourceReference(json.getJSONObject("reference")));
    if (json.has("userId"))
      res.setUserId(parseString(json.getString("userId")));
    if (json.has("_userId"))
      parseElementProperties(json.getJSONObject("_userId"), res.getUserId());
    if (json.has("authId"))
      res.setAuthId(parseString(json.getString("authId")));
    if (json.has("_authId"))
      parseElementProperties(json.getJSONObject("_authId"), res.getAuthId());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("requestor"))
      res.setRequestor(parseBoolean(json.getBoolean("requestor")));
    if (json.has("_requestor"))
      parseElementProperties(json.getJSONObject("_requestor"), res.getRequestor());
    if (json.has("media"))
      res.setMedia(parseCoding(json.getJSONObject("media")));
    if (json.has("network"))
      res.setNetwork(parseSecurityEventSecurityEventParticipantNetworkComponent(json.getJSONObject("network"), owner));
    return res;
  }

  private SecurityEvent.SecurityEventParticipantNetworkComponent parseSecurityEventSecurityEventParticipantNetworkComponent(JSONObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventParticipantNetworkComponent res = owner.new SecurityEventParticipantNetworkComponent();
    parseElementProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), SecurityEvent.NetworkType.Null, new SecurityEvent().new NetworkTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    return res;
  }

  private SecurityEvent.SecurityEventSourceComponent parseSecurityEventSecurityEventSourceComponent(JSONObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventSourceComponent res = owner.new SecurityEventSourceComponent();
    parseElementProperties(json, res);
    if (json.has("site"))
      res.setSite(parseString(json.getString("site")));
    if (json.has("_site"))
      parseElementProperties(json.getJSONObject("_site"), res.getSite());
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("type")) {
      JSONArray array = json.getJSONArray("type");
      for (int i = 0; i < array.length(); i++) {
        res.getType().add(parseCoding(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private SecurityEvent.SecurityEventObjectComponent parseSecurityEventSecurityEventObjectComponent(JSONObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventObjectComponent res = owner.new SecurityEventObjectComponent();
    parseElementProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("reference"))
      res.setReference(parseResourceReference(json.getJSONObject("reference")));
    if (json.has("type"))
      res.setType(parseEnumeration(json.getString("type"), SecurityEvent.ObjectType.Null, new SecurityEvent().new ObjectTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("role"))
      res.setRole(parseEnumeration(json.getString("role"), SecurityEvent.ObjectRole.Null, new SecurityEvent().new ObjectRoleEnumFactory()));
    if (json.has("_role"))
      parseElementProperties(json.getJSONObject("_role"), res.getRole());
    if (json.has("lifecycle"))
      res.setLifecycle(parseEnumeration(json.getString("lifecycle"), SecurityEvent.ObjectLifecycle.Null, new SecurityEvent().new ObjectLifecycleEnumFactory()));
    if (json.has("_lifecycle"))
      parseElementProperties(json.getJSONObject("_lifecycle"), res.getLifecycle());
    if (json.has("sensitivity"))
      res.setSensitivity(parseCodeableConcept(json.getJSONObject("sensitivity")));
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("query"))
      res.setQuery(parseBase64Binary(json.getString("query")));
    if (json.has("_query"))
      parseElementProperties(json.getJSONObject("_query"), res.getQuery());
    if (json.has("detail")) {
      JSONArray array = json.getJSONArray("detail");
      for (int i = 0; i < array.length(); i++) {
        res.getDetail().add(parseSecurityEventSecurityEventObjectDetailComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private SecurityEvent.SecurityEventObjectDetailComponent parseSecurityEventSecurityEventObjectDetailComponent(JSONObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventObjectDetailComponent res = owner.new SecurityEventObjectDetailComponent();
    parseElementProperties(json, res);
    if (json.has("type"))
      res.setType(parseString(json.getString("type")));
    if (json.has("_type"))
      parseElementProperties(json.getJSONObject("_type"), res.getType());
    if (json.has("value"))
      res.setValue(parseBase64Binary(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    return res;
  }

  private Specimen parseSpecimen(JSONObject json) throws Exception {
    Specimen res = new Specimen();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("source")) {
      JSONArray array = json.getJSONArray("source");
      for (int i = 0; i < array.length(); i++) {
        res.getSource().add(parseSpecimenSpecimenSourceComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getJSONObject("subject")));
    if (json.has("accessionIdentifier")) {
      JSONArray array = json.getJSONArray("accessionIdentifier");
      for (int i = 0; i < array.length(); i++) {
        res.getAccessionIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("receivedTime"))
      res.setReceivedTime(parseDateTime(json.getString("receivedTime")));
    if (json.has("_receivedTime"))
      parseElementProperties(json.getJSONObject("_receivedTime"), res.getReceivedTime());
    if (json.has("collection"))
      res.setCollection(parseSpecimenSpecimenCollectionComponent(json.getJSONObject("collection"), res));
    if (json.has("treatment")) {
      JSONArray array = json.getJSONArray("treatment");
      for (int i = 0; i < array.length(); i++) {
        res.getTreatment().add(parseSpecimenSpecimenTreatmentComponent(array.getJSONObject(i), res));
      }
    };
    if (json.has("container")) {
      JSONArray array = json.getJSONArray("container");
      for (int i = 0; i < array.length(); i++) {
        res.getContainer().add(parseSpecimenSpecimenContainerComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private Specimen.SpecimenSourceComponent parseSpecimenSpecimenSourceComponent(JSONObject json, Specimen owner) throws Exception {
    Specimen.SpecimenSourceComponent res = owner.new SpecimenSourceComponent();
    parseElementProperties(json, res);
    if (json.has("relationship"))
      res.setRelationship(parseEnumeration(json.getString("relationship"), Specimen.HierarchicalRelationshipType.Null, new Specimen().new HierarchicalRelationshipTypeEnumFactory()));
    if (json.has("_relationship"))
      parseElementProperties(json.getJSONObject("_relationship"), res.getRelationship());
    if (json.has("target")) {
      JSONArray array = json.getJSONArray("target");
      for (int i = 0; i < array.length(); i++) {
        res.getTarget().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Specimen.SpecimenCollectionComponent parseSpecimenSpecimenCollectionComponent(JSONObject json, Specimen owner) throws Exception {
    Specimen.SpecimenCollectionComponent res = owner.new SpecimenCollectionComponent();
    parseElementProperties(json, res);
    if (json.has("collector"))
      res.setCollector(parseResourceReference(json.getJSONObject("collector")));
    if (json.has("comment")) {
      JSONArray array = json.getJSONArray("comment");
      for (int i = 0; i < array.length(); i++) {
        res.getComment().add(parseString(array.getString(i)));
      }
    };
    if (json.has("_comment")) {
      JSONArray array = json.getJSONArray("_comment");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getComment().size())
          res.getComment().add(parseString(null));
        parseElementProperties(array.getJSONObject(i), res.getComment().get(i));
      }
    };
    if (json.has("collectedTime"))
      res.setCollectedTime(parseDateTime(json.getString("collectedTime")));
    if (json.has("_collectedTime"))
      parseElementProperties(json.getJSONObject("_collectedTime"), res.getCollectedTime());
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getJSONObject("method")));
    if (json.has("sourceSite"))
      res.setSourceSite(parseCodeableConcept(json.getJSONObject("sourceSite")));
    return res;
  }

  private Specimen.SpecimenTreatmentComponent parseSpecimenSpecimenTreatmentComponent(JSONObject json, Specimen owner) throws Exception {
    Specimen.SpecimenTreatmentComponent res = owner.new SpecimenTreatmentComponent();
    parseElementProperties(json, res);
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("procedure"))
      res.setProcedure(parseCodeableConcept(json.getJSONObject("procedure")));
    if (json.has("additive")) {
      JSONArray array = json.getJSONArray("additive");
      for (int i = 0; i < array.length(); i++) {
        res.getAdditive().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private Specimen.SpecimenContainerComponent parseSpecimenSpecimenContainerComponent(JSONObject json, Specimen owner) throws Exception {
    Specimen.SpecimenContainerComponent res = owner.new SpecimenContainerComponent();
    parseElementProperties(json, res);
    if (json.has("identifier")) {
      JSONArray array = json.getJSONArray("identifier");
      for (int i = 0; i < array.length(); i++) {
        res.getIdentifier().add(parseIdentifier(array.getJSONObject(i)));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("capacity"))
      res.setCapacity(parseQuantity(json.getJSONObject("capacity")));
    if (json.has("specimenQuantity"))
      res.setSpecimenQuantity(parseQuantity(json.getJSONObject("specimenQuantity")));
    if (json.has("additive"))
      res.setAdditive(parseResourceReference(json.getJSONObject("additive")));
    return res;
  }

  private Substance parseSubstance(JSONObject json) throws Exception {
    Substance res = new Substance();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("status"))
      res.setStatus(parseCodeableConcept(json.getJSONObject("status")));
    if (json.has("effectiveTime"))
      res.setEffectiveTime(parsePeriod(json.getJSONObject("effectiveTime")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("ingredient")) {
      JSONArray array = json.getJSONArray("ingredient");
      for (int i = 0; i < array.length(); i++) {
        res.getIngredient().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    if (json.has("quantityMode"))
      res.setQuantityMode(parseCodeableConcept(json.getJSONObject("quantityMode")));
    return res;
  }

  private Supply parseSupply(JSONObject json) throws Exception {
    Supply res = new Supply();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getJSONObject("name")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseCode(json.getString("status")));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("orderedItem"))
      res.setOrderedItem(parseResourceReference(json.getJSONObject("orderedItem")));
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getJSONObject("patient")));
    if (json.has("dispense")) {
      JSONArray array = json.getJSONArray("dispense");
      for (int i = 0; i < array.length(); i++) {
        res.getDispense().add(parseSupplySupplyDispenseComponent(array.getJSONObject(i), res));
      }
    };
    return res;
  }

  private Supply.SupplyDispenseComponent parseSupplySupplyDispenseComponent(JSONObject json, Supply owner) throws Exception {
    Supply.SupplyDispenseComponent res = owner.new SupplyDispenseComponent();
    parseElementProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseCode(json.getString("status")));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getJSONObject("type")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getJSONObject("quantity")));
    if (json.has("suppliedItem"))
      res.setSuppliedItem(parseResourceReference(json.getJSONObject("suppliedItem")));
    if (json.has("supplier"))
      res.setSupplier(parseResourceReference(json.getJSONObject("supplier")));
    if (json.has("whenPrepared"))
      res.setWhenPrepared(parsePeriod(json.getJSONObject("whenPrepared")));
    if (json.has("whenHandedOver"))
      res.setWhenHandedOver(parsePeriod(json.getJSONObject("whenHandedOver")));
    if (json.has("destination"))
      res.setDestination(parseResourceReference(json.getJSONObject("destination")));
    if (json.has("receiver")) {
      JSONArray array = json.getJSONArray("receiver");
      for (int i = 0; i < array.length(); i++) {
        res.getReceiver().add(parseResourceReference(array.getJSONObject(i)));
      }
    };
    return res;
  }

  private ValueSet parseValueSet(JSONObject json) throws Exception {
    ValueSet res = new ValueSet();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.getString("identifier")));
    if (json.has("_identifier"))
      parseElementProperties(json.getJSONObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.getString("name")));
    if (json.has("_name"))
      parseElementProperties(json.getJSONObject("_name"), res.getName());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.getString("publisher")));
    if (json.has("_publisher"))
      parseElementProperties(json.getJSONObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JSONArray array = json.getJSONArray("telecom");
      for (int i = 0; i < array.length(); i++) {
        res.getTelecom().add(parseContact(array.getJSONObject(i)));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.getString("description")));
    if (json.has("_description"))
      parseElementProperties(json.getJSONObject("_description"), res.getDescription());
    if (json.has("copyright"))
      res.setCopyright(parseString(json.getString("copyright")));
    if (json.has("_copyright"))
      parseElementProperties(json.getJSONObject("_copyright"), res.getCopyright());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.getString("status"), ValueSet.ValuesetStatus.Null, new ValueSet().new ValuesetStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getJSONObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.getBoolean("experimental")));
    if (json.has("_experimental"))
      parseElementProperties(json.getJSONObject("_experimental"), res.getExperimental());
    if (json.has("extensible"))
      res.setExtensible(parseBoolean(json.getBoolean("extensible")));
    if (json.has("_extensible"))
      parseElementProperties(json.getJSONObject("_extensible"), res.getExtensible());
    if (json.has("date"))
      res.setDate(parseDateTime(json.getString("date")));
    if (json.has("_date"))
      parseElementProperties(json.getJSONObject("_date"), res.getDate());
    if (json.has("define"))
      res.setDefine(parseValueSetValueSetDefineComponent(json.getJSONObject("define"), res));
    if (json.has("compose"))
      res.setCompose(parseValueSetValueSetComposeComponent(json.getJSONObject("compose"), res));
    if (json.has("expansion"))
      res.setExpansion(parseValueSetValueSetExpansionComponent(json.getJSONObject("expansion"), res));
    return res;
  }

  private ValueSet.ValueSetDefineComponent parseValueSetValueSetDefineComponent(JSONObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetDefineComponent res = owner.new ValueSetDefineComponent();
    parseElementProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("caseSensitive"))
      res.setCaseSensitive(parseBoolean(json.getBoolean("caseSensitive")));
    if (json.has("_caseSensitive"))
      parseElementProperties(json.getJSONObject("_caseSensitive"), res.getCaseSensitive());
    if (json.has("concept")) {
      JSONArray array = json.getJSONArray("concept");
      for (int i = 0; i < array.length(); i++) {
        res.getConcept().add(parseValueSetValueSetDefineConceptComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private ValueSet.ValueSetDefineConceptComponent parseValueSetValueSetDefineConceptComponent(JSONObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetDefineConceptComponent res = owner.new ValueSetDefineConceptComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("abstract"))
      res.setAbstract(parseBoolean(json.getBoolean("abstract")));
    if (json.has("_abstract"))
      parseElementProperties(json.getJSONObject("_abstract"), res.getAbstract());
    if (json.has("display"))
      res.setDisplay(parseString(json.getString("display")));
    if (json.has("_display"))
      parseElementProperties(json.getJSONObject("_display"), res.getDisplay());
    if (json.has("definition"))
      res.setDefinition(parseString(json.getString("definition")));
    if (json.has("_definition"))
      parseElementProperties(json.getJSONObject("_definition"), res.getDefinition());
    if (json.has("concept")) {
      JSONArray array = json.getJSONArray("concept");
      for (int i = 0; i < array.length(); i++) {
        res.getConcept().add(parseValueSetValueSetDefineConceptComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private ValueSet.ValueSetComposeComponent parseValueSetValueSetComposeComponent(JSONObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetComposeComponent res = owner.new ValueSetComposeComponent();
    parseElementProperties(json, res);
    if (json.has("import")) {
      JSONArray array = json.getJSONArray("import");
      for (int i = 0; i < array.length(); i++) {
        res.getImport().add(parseUri(array.getString(i)));
      }
    };
    if (json.has("_import")) {
      JSONArray array = json.getJSONArray("_import");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getImport().size())
          res.getImport().add(parseUri(null));
        parseElementProperties(array.getJSONObject(i), res.getImport().get(i));
      }
    };
    if (json.has("include")) {
      JSONArray array = json.getJSONArray("include");
      for (int i = 0; i < array.length(); i++) {
        res.getInclude().add(parseValueSetConceptSetComponent(array.getJSONObject(i), owner));
      }
    };
    if (json.has("exclude")) {
      JSONArray array = json.getJSONArray("exclude");
      for (int i = 0; i < array.length(); i++) {
        res.getExclude().add(parseValueSetConceptSetComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private ValueSet.ConceptSetComponent parseValueSetConceptSetComponent(JSONObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptSetComponent res = owner.new ConceptSetComponent();
    parseElementProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("version"))
      res.setVersion(parseString(json.getString("version")));
    if (json.has("_version"))
      parseElementProperties(json.getJSONObject("_version"), res.getVersion());
    if (json.has("code")) {
      JSONArray array = json.getJSONArray("code");
      for (int i = 0; i < array.length(); i++) {
        res.getCode().add(parseCode(array.getString(i)));
      }
    };
    if (json.has("_code")) {
      JSONArray array = json.getJSONArray("_code");
      for (int i = 0; i < array.length(); i++) {
        if (i == res.getCode().size())
          res.getCode().add(parseCode(null));
        parseElementProperties(array.getJSONObject(i), res.getCode().get(i));
      }
    };
    if (json.has("filter")) {
      JSONArray array = json.getJSONArray("filter");
      for (int i = 0; i < array.length(); i++) {
        res.getFilter().add(parseValueSetConceptSetFilterComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private ValueSet.ConceptSetFilterComponent parseValueSetConceptSetFilterComponent(JSONObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptSetFilterComponent res = owner.new ConceptSetFilterComponent();
    parseElementProperties(json, res);
    if (json.has("property"))
      res.setProperty(parseCode(json.getString("property")));
    if (json.has("_property"))
      parseElementProperties(json.getJSONObject("_property"), res.getProperty());
    if (json.has("op"))
      res.setOp(parseEnumeration(json.getString("op"), ValueSet.FilterOperator.Null, new ValueSet().new FilterOperatorEnumFactory()));
    if (json.has("_op"))
      parseElementProperties(json.getJSONObject("_op"), res.getOp());
    if (json.has("value"))
      res.setValue(parseCode(json.getString("value")));
    if (json.has("_value"))
      parseElementProperties(json.getJSONObject("_value"), res.getValue());
    return res;
  }

  private ValueSet.ValueSetExpansionComponent parseValueSetValueSetExpansionComponent(JSONObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionComponent res = owner.new ValueSetExpansionComponent();
    parseElementProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getJSONObject("identifier")));
    if (json.has("timestamp"))
      res.setTimestamp(parseInstant(json.getString("timestamp")));
    if (json.has("_timestamp"))
      parseElementProperties(json.getJSONObject("_timestamp"), res.getTimestamp());
    if (json.has("contains")) {
      JSONArray array = json.getJSONArray("contains");
      for (int i = 0; i < array.length(); i++) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  private ValueSet.ValueSetExpansionContainsComponent parseValueSetValueSetExpansionContainsComponent(JSONObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionContainsComponent res = owner.new ValueSetExpansionContainsComponent();
    parseElementProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.getString("system")));
    if (json.has("_system"))
      parseElementProperties(json.getJSONObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.getString("code")));
    if (json.has("_code"))
      parseElementProperties(json.getJSONObject("_code"), res.getCode());
    if (json.has("display"))
      res.setDisplay(parseString(json.getString("display")));
    if (json.has("_display"))
      parseElementProperties(json.getJSONObject("_display"), res.getDisplay());
    if (json.has("contains")) {
      JSONArray array = json.getJSONArray("contains");
      for (int i = 0; i < array.length(); i++) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(array.getJSONObject(i), owner));
      }
    };
    return res;
  }

  @Override
  protected Resource parseResource(JSONObject json) throws Exception {
    if (json.has("AdverseReaction"))
      return parseAdverseReaction(json.getJSONObject("AdverseReaction"));
    else if (json.has("Alert"))
      return parseAlert(json.getJSONObject("Alert"));
    else if (json.has("AllergyIntolerance"))
      return parseAllergyIntolerance(json.getJSONObject("AllergyIntolerance"));
    else if (json.has("CarePlan"))
      return parseCarePlan(json.getJSONObject("CarePlan"));
    else if (json.has("ConceptMap"))
      return parseConceptMap(json.getJSONObject("ConceptMap"));
    else if (json.has("Condition"))
      return parseCondition(json.getJSONObject("Condition"));
    else if (json.has("Conformance"))
      return parseConformance(json.getJSONObject("Conformance"));
    else if (json.has("Device"))
      return parseDevice(json.getJSONObject("Device"));
    else if (json.has("DeviceCapabilities"))
      return parseDeviceCapabilities(json.getJSONObject("DeviceCapabilities"));
    else if (json.has("DeviceData"))
      return parseDeviceData(json.getJSONObject("DeviceData"));
    else if (json.has("DeviceLog"))
      return parseDeviceLog(json.getJSONObject("DeviceLog"));
    else if (json.has("DeviceObservation"))
      return parseDeviceObservation(json.getJSONObject("DeviceObservation"));
    else if (json.has("DiagnosticOrder"))
      return parseDiagnosticOrder(json.getJSONObject("DiagnosticOrder"));
    else if (json.has("DiagnosticReport"))
      return parseDiagnosticReport(json.getJSONObject("DiagnosticReport"));
    else if (json.has("Document"))
      return parseDocument(json.getJSONObject("Document"));
    else if (json.has("DocumentManifest"))
      return parseDocumentManifest(json.getJSONObject("DocumentManifest"));
    else if (json.has("DocumentReference"))
      return parseDocumentReference(json.getJSONObject("DocumentReference"));
    else if (json.has("Encounter"))
      return parseEncounter(json.getJSONObject("Encounter"));
    else if (json.has("FamilyHistory"))
      return parseFamilyHistory(json.getJSONObject("FamilyHistory"));
    else if (json.has("Group"))
      return parseGroup(json.getJSONObject("Group"));
    else if (json.has("ImagingStudy"))
      return parseImagingStudy(json.getJSONObject("ImagingStudy"));
    else if (json.has("Immunization"))
      return parseImmunization(json.getJSONObject("Immunization"));
    else if (json.has("ImmunizationRecommendation"))
      return parseImmunizationRecommendation(json.getJSONObject("ImmunizationRecommendation"));
    else if (json.has("List"))
      return parseList_(json.getJSONObject("List"));
    else if (json.has("Location"))
      return parseLocation(json.getJSONObject("Location"));
    else if (json.has("Media"))
      return parseMedia(json.getJSONObject("Media"));
    else if (json.has("Medication"))
      return parseMedication(json.getJSONObject("Medication"));
    else if (json.has("MedicationAdministration"))
      return parseMedicationAdministration(json.getJSONObject("MedicationAdministration"));
    else if (json.has("MedicationDispense"))
      return parseMedicationDispense(json.getJSONObject("MedicationDispense"));
    else if (json.has("MedicationPrescription"))
      return parseMedicationPrescription(json.getJSONObject("MedicationPrescription"));
    else if (json.has("MedicationStatement"))
      return parseMedicationStatement(json.getJSONObject("MedicationStatement"));
    else if (json.has("Message"))
      return parseMessage(json.getJSONObject("Message"));
    else if (json.has("Observation"))
      return parseObservation(json.getJSONObject("Observation"));
    else if (json.has("OperationOutcome"))
      return parseOperationOutcome(json.getJSONObject("OperationOutcome"));
    else if (json.has("Order"))
      return parseOrder(json.getJSONObject("Order"));
    else if (json.has("OrderResponse"))
      return parseOrderResponse(json.getJSONObject("OrderResponse"));
    else if (json.has("Organization"))
      return parseOrganization(json.getJSONObject("Organization"));
    else if (json.has("Other"))
      return parseOther(json.getJSONObject("Other"));
    else if (json.has("Patient"))
      return parsePatient(json.getJSONObject("Patient"));
    else if (json.has("Practitioner"))
      return parsePractitioner(json.getJSONObject("Practitioner"));
    else if (json.has("Procedure"))
      return parseProcedure(json.getJSONObject("Procedure"));
    else if (json.has("Profile"))
      return parseProfile(json.getJSONObject("Profile"));
    else if (json.has("Provenance"))
      return parseProvenance(json.getJSONObject("Provenance"));
    else if (json.has("Query"))
      return parseQuery(json.getJSONObject("Query"));
    else if (json.has("Questionnaire"))
      return parseQuestionnaire(json.getJSONObject("Questionnaire"));
    else if (json.has("RelatedPerson"))
      return parseRelatedPerson(json.getJSONObject("RelatedPerson"));
    else if (json.has("SecurityEvent"))
      return parseSecurityEvent(json.getJSONObject("SecurityEvent"));
    else if (json.has("Specimen"))
      return parseSpecimen(json.getJSONObject("Specimen"));
    else if (json.has("Substance"))
      return parseSubstance(json.getJSONObject("Substance"));
    else if (json.has("Supply"))
      return parseSupply(json.getJSONObject("Supply"));
    else if (json.has("ValueSet"))
      return parseValueSet(json.getJSONObject("ValueSet"));
    else if (json.has("Binary"))
      return parseBinary(json.getJSONObject("Binary"));
    throw new Exception("Unknown.Unrecognised resource type");
  }

  protected Type parseType(String prefix, JSONObject json) throws Exception {
    if (json.has(prefix+"Period"))
      return parsePeriod(json.getJSONObject(prefix+"Period"));
    else if (json.has(prefix+"Coding"))
      return parseCoding(json.getJSONObject(prefix+"Coding"));
    else if (json.has(prefix+"Range"))
      return parseRange(json.getJSONObject(prefix+"Range"));
    else if (json.has(prefix+"Quantity"))
      return parseQuantity(json.getJSONObject(prefix+"Quantity"));
    else if (json.has(prefix+"Attachment"))
      return parseAttachment(json.getJSONObject(prefix+"Attachment"));
    else if (json.has(prefix+"Ratio"))
      return parseRatio(json.getJSONObject(prefix+"Ratio"));
    else if (json.has(prefix+"SampledData"))
      return parseSampledData(json.getJSONObject(prefix+"SampledData"));
    else if (json.has(prefix+"Resource"))
      return parseResourceReference(json.getJSONObject(prefix+"Resource"));
    else if (json.has(prefix+"CodeableConcept"))
      return parseCodeableConcept(json.getJSONObject(prefix+"CodeableConcept"));
    else if (json.has(prefix+"Identifier"))
      return parseIdentifier(json.getJSONObject(prefix+"Identifier"));
    else if (json.has(prefix+"Age"))
      return parseAge(json.getJSONObject(prefix+"Age"));
    else if (json.has(prefix+"Count"))
      return parseCount(json.getJSONObject(prefix+"Count"));
    else if (json.has(prefix+"Money"))
      return parseMoney(json.getJSONObject(prefix+"Money"));
    else if (json.has(prefix+"Distance"))
      return parseDistance(json.getJSONObject(prefix+"Distance"));
    else if (json.has(prefix+"Duration"))
      return parseDuration(json.getJSONObject(prefix+"Duration"));
    else if (json.has(prefix+"Schedule"))
      return parseSchedule(json.getJSONObject(prefix+"Schedule"));
    else if (json.has(prefix+"Contact"))
      return parseContact(json.getJSONObject(prefix+"Contact"));
    else if (json.has(prefix+"Address"))
      return parseAddress(json.getJSONObject(prefix+"Address"));
    else if (json.has(prefix+"HumanName"))
      return parseHumanName(json.getJSONObject(prefix+"HumanName"));
    else if (json.has(prefix+"Integer") || json.has("_"+prefix+"Integer")) {
      Type t = parseInteger(json.getString(prefix+"Integer"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Integer"), t);
      return t;
    }
    else if (json.has(prefix+"DateTime") || json.has("_"+prefix+"DateTime")) {
      Type t = parseDateTime(json.getString(prefix+"DateTime"));
      parseElementProperties(json.getJSONObject("_"+prefix+"DateTime"), t);
      return t;
    }
    else if (json.has(prefix+"Code") || json.has("_"+prefix+"Code")) {
      Type t = parseCode(json.getString(prefix+"Code"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Code"), t);
      return t;
    }
    else if (json.has(prefix+"Date") || json.has("_"+prefix+"Date")) {
      Type t = parseDate(json.getString(prefix+"Date"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Date"), t);
      return t;
    }
    else if (json.has(prefix+"Decimal") || json.has("_"+prefix+"Decimal")) {
      Type t = parseDecimal(json.getString(prefix+"Decimal"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Decimal"), t);
      return t;
    }
    else if (json.has(prefix+"Uri") || json.has("_"+prefix+"Uri")) {
      Type t = parseUri(json.getString(prefix+"Uri"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Uri"), t);
      return t;
    }
    else if (json.has(prefix+"Id") || json.has("_"+prefix+"Id")) {
      Type t = parseId(json.getString(prefix+"Id"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Id"), t);
      return t;
    }
    else if (json.has(prefix+"Base64Binary") || json.has("_"+prefix+"Base64Binary")) {
      Type t = parseBase64Binary(json.getString(prefix+"Base64Binary"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Base64Binary"), t);
      return t;
    }
    else if (json.has(prefix+"Oid") || json.has("_"+prefix+"Oid")) {
      Type t = parseOid(json.getString(prefix+"Oid"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Oid"), t);
      return t;
    }
    else if (json.has(prefix+"String") || json.has("_"+prefix+"String")) {
      Type t = parseString(json.getString(prefix+"String"));
      parseElementProperties(json.getJSONObject("_"+prefix+"String"), t);
      return t;
    }
    else if (json.has(prefix+"Boolean") || json.has("_"+prefix+"Boolean")) {
      Type t = parseBoolean(json.getBoolean(prefix+"Boolean"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Boolean"), t);
      return t;
    }
    else if (json.has(prefix+"Uuid") || json.has("_"+prefix+"Uuid")) {
      Type t = parseUuid(json.getString(prefix+"Uuid"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Uuid"), t);
      return t;
    }
    else if (json.has(prefix+"Instant") || json.has("_"+prefix+"Instant")) {
      Type t = parseInstant(json.getString(prefix+"Instant"));
      parseElementProperties(json.getJSONObject("_"+prefix+"Instant"), t);
      return t;
    }
    return null;
  }

  private boolean hasTypeName(JSONObject json, String prefix) {
        if (json.has(prefix+"Period"))
      return true;
    if (json.has(prefix+"Coding"))
      return true;
    if (json.has(prefix+"Range"))
      return true;
    if (json.has(prefix+"Quantity"))
      return true;
    if (json.has(prefix+"Attachment"))
      return true;
    if (json.has(prefix+"Ratio"))
      return true;
    if (json.has(prefix+"SampledData"))
      return true;
    if (json.has(prefix+"Resource"))
      return true;
    if (json.has(prefix+"CodeableConcept"))
      return true;
    if (json.has(prefix+"Identifier"))
      return true;
    if (json.has(prefix+"Age"))
      return true;
    if (json.has(prefix+"Count"))
      return true;
    if (json.has(prefix+"Money"))
      return true;
    if (json.has(prefix+"Distance"))
      return true;
    if (json.has(prefix+"Duration"))
      return true;
    if (json.has(prefix+"Schedule"))
      return true;
    if (json.has(prefix+"Contact"))
      return true;
    if (json.has(prefix+"Address"))
      return true;
    if (json.has(prefix+"HumanName"))
      return true;
    if (json.has(prefix+"AdverseReaction"))
      return true;
    if (json.has(prefix+"Alert"))
      return true;
    if (json.has(prefix+"AllergyIntolerance"))
      return true;
    if (json.has(prefix+"CarePlan"))
      return true;
    if (json.has(prefix+"ConceptMap"))
      return true;
    if (json.has(prefix+"Condition"))
      return true;
    if (json.has(prefix+"Conformance"))
      return true;
    if (json.has(prefix+"Device"))
      return true;
    if (json.has(prefix+"DeviceCapabilities"))
      return true;
    if (json.has(prefix+"DeviceData"))
      return true;
    if (json.has(prefix+"DeviceLog"))
      return true;
    if (json.has(prefix+"DeviceObservation"))
      return true;
    if (json.has(prefix+"DiagnosticOrder"))
      return true;
    if (json.has(prefix+"DiagnosticReport"))
      return true;
    if (json.has(prefix+"Document"))
      return true;
    if (json.has(prefix+"DocumentManifest"))
      return true;
    if (json.has(prefix+"DocumentReference"))
      return true;
    if (json.has(prefix+"Encounter"))
      return true;
    if (json.has(prefix+"FamilyHistory"))
      return true;
    if (json.has(prefix+"Group"))
      return true;
    if (json.has(prefix+"ImagingStudy"))
      return true;
    if (json.has(prefix+"Immunization"))
      return true;
    if (json.has(prefix+"ImmunizationRecommendation"))
      return true;
    if (json.has(prefix+"List"))
      return true;
    if (json.has(prefix+"Location"))
      return true;
    if (json.has(prefix+"Media"))
      return true;
    if (json.has(prefix+"Medication"))
      return true;
    if (json.has(prefix+"MedicationAdministration"))
      return true;
    if (json.has(prefix+"MedicationDispense"))
      return true;
    if (json.has(prefix+"MedicationPrescription"))
      return true;
    if (json.has(prefix+"MedicationStatement"))
      return true;
    if (json.has(prefix+"Message"))
      return true;
    if (json.has(prefix+"Observation"))
      return true;
    if (json.has(prefix+"OperationOutcome"))
      return true;
    if (json.has(prefix+"Order"))
      return true;
    if (json.has(prefix+"OrderResponse"))
      return true;
    if (json.has(prefix+"Organization"))
      return true;
    if (json.has(prefix+"Other"))
      return true;
    if (json.has(prefix+"Patient"))
      return true;
    if (json.has(prefix+"Practitioner"))
      return true;
    if (json.has(prefix+"Procedure"))
      return true;
    if (json.has(prefix+"Profile"))
      return true;
    if (json.has(prefix+"Provenance"))
      return true;
    if (json.has(prefix+"Query"))
      return true;
    if (json.has(prefix+"Questionnaire"))
      return true;
    if (json.has(prefix+"RelatedPerson"))
      return true;
    if (json.has(prefix+"SecurityEvent"))
      return true;
    if (json.has(prefix+"Specimen"))
      return true;
    if (json.has(prefix+"Substance"))
      return true;
    if (json.has(prefix+"Supply"))
      return true;
    if (json.has(prefix+"ValueSet"))
      return true;
    if (json.has(prefix+"Integer") || json.has("_"+prefix+"Integer"))
      return true;
    if (json.has(prefix+"DateTime") || json.has("_"+prefix+"DateTime"))
      return true;
    if (json.has(prefix+"Code") || json.has("_"+prefix+"Code"))
      return true;
    if (json.has(prefix+"Date") || json.has("_"+prefix+"Date"))
      return true;
    if (json.has(prefix+"Decimal") || json.has("_"+prefix+"Decimal"))
      return true;
    if (json.has(prefix+"Uri") || json.has("_"+prefix+"Uri"))
      return true;
    if (json.has(prefix+"Id") || json.has("_"+prefix+"Id"))
      return true;
    if (json.has(prefix+"Base64Binary") || json.has("_"+prefix+"Base64Binary"))
      return true;
    if (json.has(prefix+"Oid") || json.has("_"+prefix+"Oid"))
      return true;
    if (json.has(prefix+"String") || json.has("_"+prefix+"String"))
      return true;
    if (json.has(prefix+"Boolean") || json.has("_"+prefix+"Boolean"))
      return true;
    if (json.has(prefix+"Uuid") || json.has("_"+prefix+"Uuid"))
      return true;
    if (json.has(prefix+"Instant") || json.has("_"+prefix+"Instant"))
      return true;
    return false;
  }
}

