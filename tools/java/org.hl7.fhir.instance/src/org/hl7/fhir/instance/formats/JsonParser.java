package org.hl7.fhir.instance.formats;

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

import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.Base64BinaryType;
import org.hl7.fhir.instance.model.TimeType;
import org.hl7.fhir.instance.model.OidType;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.UuidType;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.utilities.Utilities;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class JsonParser extends JsonParserBase {

  protected void parseElementProperties(JsonObject json, Element element) throws Exception {
    super.parseElementProperties(json, element);
    if (json != null && json.has("extension")) {
      JsonArray array = json.getAsJsonArray("extension");
      for (int i = 0; i < array.size(); i++) {
        element.getExtensions().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
  }

  protected void parseBackboneProperties(JsonObject json, BackboneElement element) throws Exception {
    parseElementProperties(json, element);
    if (json != null && json.has("modifierExtension")) {
      JsonArray array = json.getAsJsonArray("modifierExtension");
      for (int i = 0; i < array.size(); i++) {
        element.getModifierExtensions().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
  }

  protected void parseTypeProperties(JsonObject json, Element element) throws Exception {
    parseElementProperties(json, element);
  }

  @SuppressWarnings("unchecked")
  private <E extends Enum<E>> Enumeration<E> parseEnumeration(String s, E item, EnumFactory e) throws Exception {
    Enumeration<E> res = new Enumeration<E>();
    if (s != null)
      res.setValue((E) e.fromCode(s));
    return res;
  }

  private IntegerType parseInteger(java.lang.Long v) throws Exception {
    IntegerType res = new IntegerType();
    if (v != null)
      res.setValue(parseIntegerPrimitive(v));
    return res;
  }

  private DateTimeType parseDateTime(String v) throws Exception {
    DateTimeType res = new DateTimeType();
    if (v != null)
      res.setValue(parseDateTimePrimitive(v));
    return res;
  }

  private CodeType parseCode(String v) throws Exception {
    CodeType res = new CodeType();
    if (v != null)
      res.setValue(parseCodePrimitive(v));
    return res;
  }

  private DateType parseDate(String v) throws Exception {
    DateType res = new DateType();
    if (v != null)
      res.setValue(parseDatePrimitive(v));
    return res;
  }

  private DecimalType parseDecimal(java.math.BigDecimal v) throws Exception {
    DecimalType res = new DecimalType();
    if (v != null)
      res.setValue(parseDecimalPrimitive(v));
    return res;
  }

  private UriType parseUri(String v) throws Exception {
    UriType res = new UriType();
    if (v != null)
      res.setValue(parseUriPrimitive(v));
    return res;
  }

  private IdType parseId(String v) throws Exception {
    IdType res = new IdType();
    if (v != null)
      res.setValue(parseIdPrimitive(v));
    return res;
  }

  private Base64BinaryType parseBase64Binary(String v) throws Exception {
    Base64BinaryType res = new Base64BinaryType();
    if (v != null)
      res.setValue(parseBase64BinaryPrimitive(v));
    return res;
  }

  private TimeType parseTime(String v) throws Exception {
    TimeType res = new TimeType();
    if (v != null)
      res.setValue(parseTimePrimitive(v));
    return res;
  }

  private OidType parseOid(String v) throws Exception {
    OidType res = new OidType();
    if (v != null)
      res.setValue(parseOidPrimitive(v));
    return res;
  }

  private StringType parseString(String v) throws Exception {
    StringType res = new StringType();
    if (v != null)
      res.setValue(parseStringPrimitive(v));
    return res;
  }

  private BooleanType parseBoolean(java.lang.Boolean v) throws Exception {
    BooleanType res = new BooleanType();
    if (v != null)
      res.setValue(parseBooleanPrimitive(v));
    return res;
  }

  private UuidType parseUuid(String v) throws Exception {
    UuidType res = new UuidType();
    if (v != null)
      res.setValue(parseUuidPrimitive(v));
    return res;
  }

  private InstantType parseInstant(String v) throws Exception {
    InstantType res = new InstantType();
    if (v != null)
      res.setValue(parseInstantPrimitive(v));
    return res;
  }

  private Extension parseExtension(JsonObject json) throws Exception {
    Extension res = new Extension();
    parseElementProperties(json, res);
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    return res;
  }

  private Narrative parseNarrative(JsonObject json) throws Exception {
    Narrative res = new Narrative();
    parseElementProperties(json, res);
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Narrative.NarrativeStatus.Null, new Narrative.NarrativeStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("div"))
      res.setDiv(parseXhtml(json.get("div").getAsString()));
    return res;
  }

  private Period parsePeriod(JsonObject json) throws Exception {
    Period res = new Period();
    parseTypeProperties(json, res);
    if (json.has("start"))
      res.setStart(parseDateTime(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStart());
    if (json.has("end"))
      res.setEnd(parseDateTime(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEnd());
    return res;
  }

  private Coding parseCoding(JsonObject json) throws Exception {
    Coding res = new Coding();
    parseTypeProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("display"))
      res.setDisplay(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplay());
    if (json.has("primary"))
      res.setPrimary(parseBoolean(json.get("primary").getAsBoolean()));
    if (json.has("_primary"))
      parseElementProperties(json.getAsJsonObject("_primary"), res.getPrimary());
    if (json.has("valueSet"))
      res.setValueSet(parseResourceReference(json.getAsJsonObject("valueSet")));
    return res;
  }

  private Range parseRange(JsonObject json) throws Exception {
    Range res = new Range();
    parseTypeProperties(json, res);
    if (json.has("low"))
      res.setLow(parseQuantity(json.getAsJsonObject("low")));
    if (json.has("high"))
      res.setHigh(parseQuantity(json.getAsJsonObject("high")));
    return res;
  }

  private Quantity parseQuantity(JsonObject json) throws Exception {
    Quantity res = new Quantity();
    parseTypeProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    return res;
  }

  private Attachment parseAttachment(JsonObject json) throws Exception {
    Attachment res = new Attachment();
    parseTypeProperties(json, res);
    if (json.has("contentType"))
      res.setContentType(parseCode(json.get("contentType").getAsString()));
    if (json.has("_contentType"))
      parseElementProperties(json.getAsJsonObject("_contentType"), res.getContentType());
    if (json.has("language"))
      res.setLanguage(parseCode(json.get("language").getAsString()));
    if (json.has("_language"))
      parseElementProperties(json.getAsJsonObject("_language"), res.getLanguage());
    if (json.has("data"))
      res.setData(parseBase64Binary(json.get("data").getAsString()));
    if (json.has("_data"))
      parseElementProperties(json.getAsJsonObject("_data"), res.getData());
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    if (json.has("size"))
      res.setSize(parseInteger(json.get("size").getAsLong()));
    if (json.has("_size"))
      parseElementProperties(json.getAsJsonObject("_size"), res.getSize());
    if (json.has("hash"))
      res.setHash(parseBase64Binary(json.get("hash").getAsString()));
    if (json.has("_hash"))
      parseElementProperties(json.getAsJsonObject("_hash"), res.getHash());
    if (json.has("title"))
      res.setTitle(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitle());
    return res;
  }

  private Ratio parseRatio(JsonObject json) throws Exception {
    Ratio res = new Ratio();
    parseTypeProperties(json, res);
    if (json.has("numerator"))
      res.setNumerator(parseQuantity(json.getAsJsonObject("numerator")));
    if (json.has("denominator"))
      res.setDenominator(parseQuantity(json.getAsJsonObject("denominator")));
    return res;
  }

  private SampledData parseSampledData(JsonObject json) throws Exception {
    SampledData res = new SampledData();
    parseTypeProperties(json, res);
    if (json.has("origin"))
      res.setOrigin(parseQuantity(json.getAsJsonObject("origin")));
    if (json.has("period"))
      res.setPeriod(parseDecimal(json.get("period").getAsBigDecimal()));
    if (json.has("_period"))
      parseElementProperties(json.getAsJsonObject("_period"), res.getPeriod());
    if (json.has("factor"))
      res.setFactor(parseDecimal(json.get("factor").getAsBigDecimal()));
    if (json.has("_factor"))
      parseElementProperties(json.getAsJsonObject("_factor"), res.getFactor());
    if (json.has("lowerLimit"))
      res.setLowerLimit(parseDecimal(json.get("lowerLimit").getAsBigDecimal()));
    if (json.has("_lowerLimit"))
      parseElementProperties(json.getAsJsonObject("_lowerLimit"), res.getLowerLimit());
    if (json.has("upperLimit"))
      res.setUpperLimit(parseDecimal(json.get("upperLimit").getAsBigDecimal()));
    if (json.has("_upperLimit"))
      parseElementProperties(json.getAsJsonObject("_upperLimit"), res.getUpperLimit());
    if (json.has("dimensions"))
      res.setDimensions(parseInteger(json.get("dimensions").getAsLong()));
    if (json.has("_dimensions"))
      parseElementProperties(json.getAsJsonObject("_dimensions"), res.getDimensions());
    if (json.has("data"))
      res.setData(parseString(json.get("data").getAsString()));
    if (json.has("_data"))
      parseElementProperties(json.getAsJsonObject("_data"), res.getData());
    return res;
  }

  private ResourceReference parseResourceReference(JsonObject json) throws Exception {
    ResourceReference res = new ResourceReference();
    parseTypeProperties(json, res);
    if (json.has("reference"))
      res.setReference(parseString(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReference());
    if (json.has("display"))
      res.setDisplay(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplay());
    return res;
  }

  private CodeableConcept parseCodeableConcept(JsonObject json) throws Exception {
    CodeableConcept res = new CodeableConcept();
    parseTypeProperties(json, res);
    if (json.has("coding")) {
      JsonArray array = json.getAsJsonArray("coding");
      for (int i = 0; i < array.size(); i++) {
        res.getCoding().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    return res;
  }

  private Identifier parseIdentifier(JsonObject json) throws Exception {
    Identifier res = new Identifier();
    parseTypeProperties(json, res);
    if (json.has("use"))
      res.setUse(parseEnumeration(json.get("use").getAsString(), Identifier.IdentifierUse.Null, new Identifier.IdentifierUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUse());
    if (json.has("label"))
      res.setLabel(parseString(json.get("label").getAsString()));
    if (json.has("_label"))
      parseElementProperties(json.getAsJsonObject("_label"), res.getLabel());
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("value"))
      res.setValue(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("assigner"))
      res.setAssigner(parseResourceReference(json.getAsJsonObject("assigner")));
    return res;
  }

  private Age parseAge(JsonObject json) throws Exception {
    Age res = new Age();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    return res;
  }

  private Count parseCount(JsonObject json) throws Exception {
    Count res = new Count();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    return res;
  }

  private Money parseMoney(JsonObject json) throws Exception {
    Money res = new Money();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    return res;
  }

  private Distance parseDistance(JsonObject json) throws Exception {
    Distance res = new Distance();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    return res;
  }

  private Duration parseDuration(JsonObject json) throws Exception {
    Duration res = new Duration();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValue(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("comparator"))
      res.setComparator(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparator());
    if (json.has("units"))
      res.setUnits(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnits());
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    return res;
  }

  private Schedule parseSchedule(JsonObject json) throws Exception {
    Schedule res = new Schedule();
    parseElementProperties(json, res);
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parsePeriod(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("repeat"))
      res.setRepeat(parseScheduleScheduleRepeatComponent(json.getAsJsonObject("repeat"), res));
    return res;
  }

  private Schedule.ScheduleRepeatComponent parseScheduleScheduleRepeatComponent(JsonObject json, Schedule owner) throws Exception {
    Schedule.ScheduleRepeatComponent res = new Schedule.ScheduleRepeatComponent();
    parseElementProperties(json, res);
    if (json.has("frequency"))
      res.setFrequency(parseInteger(json.get("frequency").getAsLong()));
    if (json.has("_frequency"))
      parseElementProperties(json.getAsJsonObject("_frequency"), res.getFrequency());
    if (json.has("when"))
      res.setWhen(parseEnumeration(json.get("when").getAsString(), Schedule.EventTiming.Null, new Schedule.EventTimingEnumFactory()));
    if (json.has("_when"))
      parseElementProperties(json.getAsJsonObject("_when"), res.getWhen());
    if (json.has("duration"))
      res.setDuration(parseDecimal(json.get("duration").getAsBigDecimal()));
    if (json.has("_duration"))
      parseElementProperties(json.getAsJsonObject("_duration"), res.getDuration());
    if (json.has("units"))
      res.setUnits(parseEnumeration(json.get("units").getAsString(), Schedule.UnitsOfTime.Null, new Schedule.UnitsOfTimeEnumFactory()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnits());
    if (json.has("count"))
      res.setCount(parseInteger(json.get("count").getAsLong()));
    if (json.has("_count"))
      parseElementProperties(json.getAsJsonObject("_count"), res.getCount());
    if (json.has("end"))
      res.setEnd(parseDateTime(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEnd());
    return res;
  }

  private Contact parseContact(JsonObject json) throws Exception {
    Contact res = new Contact();
    parseElementProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseEnumeration(json.get("system").getAsString(), Contact.ContactSystem.Null, new Contact.ContactSystemEnumFactory()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("value"))
      res.setValue(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("use"))
      res.setUse(parseEnumeration(json.get("use").getAsString(), Contact.ContactUse.Null, new Contact.ContactUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUse());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private Address parseAddress(JsonObject json) throws Exception {
    Address res = new Address();
    parseElementProperties(json, res);
    if (json.has("use"))
      res.setUse(parseEnumeration(json.get("use").getAsString(), Address.AddressUse.Null, new Address.AddressUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUse());
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    if (json.has("line")) {
      JsonArray array = json.getAsJsonArray("line");
      for (int i = 0; i < array.size(); i++) {
        res.getLine().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_line")) {
      JsonArray array = json.getAsJsonArray("_line");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getLine().size())
          res.getLine().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getLine().get(i));
      }
    };
    if (json.has("city"))
      res.setCity(parseString(json.get("city").getAsString()));
    if (json.has("_city"))
      parseElementProperties(json.getAsJsonObject("_city"), res.getCity());
    if (json.has("state"))
      res.setState(parseString(json.get("state").getAsString()));
    if (json.has("_state"))
      parseElementProperties(json.getAsJsonObject("_state"), res.getState());
    if (json.has("zip"))
      res.setZip(parseString(json.get("zip").getAsString()));
    if (json.has("_zip"))
      parseElementProperties(json.getAsJsonObject("_zip"), res.getZip());
    if (json.has("country"))
      res.setCountry(parseString(json.get("country").getAsString()));
    if (json.has("_country"))
      parseElementProperties(json.getAsJsonObject("_country"), res.getCountry());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private HumanName parseHumanName(JsonObject json) throws Exception {
    HumanName res = new HumanName();
    parseElementProperties(json, res);
    if (json.has("use"))
      res.setUse(parseEnumeration(json.get("use").getAsString(), HumanName.NameUse.Null, new HumanName.NameUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUse());
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    if (json.has("family")) {
      JsonArray array = json.getAsJsonArray("family");
      for (int i = 0; i < array.size(); i++) {
        res.getFamily().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_family")) {
      JsonArray array = json.getAsJsonArray("_family");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getFamily().size())
          res.getFamily().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getFamily().get(i));
      }
    };
    if (json.has("given")) {
      JsonArray array = json.getAsJsonArray("given");
      for (int i = 0; i < array.size(); i++) {
        res.getGiven().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_given")) {
      JsonArray array = json.getAsJsonArray("_given");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getGiven().size())
          res.getGiven().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getGiven().get(i));
      }
    };
    if (json.has("prefix")) {
      JsonArray array = json.getAsJsonArray("prefix");
      for (int i = 0; i < array.size(); i++) {
        res.getPrefix().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_prefix")) {
      JsonArray array = json.getAsJsonArray("_prefix");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getPrefix().size())
          res.getPrefix().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getPrefix().get(i));
      }
    };
    if (json.has("suffix")) {
      JsonArray array = json.getAsJsonArray("suffix");
      for (int i = 0; i < array.size(); i++) {
        res.getSuffix().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_suffix")) {
      JsonArray array = json.getAsJsonArray("_suffix");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getSuffix().size())
          res.getSuffix().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getSuffix().get(i));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  protected void parseResourceProperties(JsonObject json, Resource res) throws Exception {
    parseBackboneProperties(json, res); 
    if (json.has("language"))
      res.setLanguage(parseCode(json.get("language").getAsString()));
    if (json.has("_language"))
      parseElementProperties(json.getAsJsonObject("_language"), res.getLanguage());
    if (json.has("text"))
      res.setText(parseNarrative(json.getAsJsonObject("text")));
    if (json.has("contained")) {
      JsonArray array = json.getAsJsonArray("contained");
      for (int i = 0; i < array.size(); i++) {
        res.getContained().add(parseResource(array.get(i).getAsJsonObject()));
      }
    };
  }

  private AdverseReaction parseAdverseReaction(JsonObject json) throws Exception {
    AdverseReaction res = new AdverseReaction();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("didNotOccurFlag"))
      res.setDidNotOccurFlag(parseBoolean(json.get("didNotOccurFlag").getAsBoolean()));
    if (json.has("_didNotOccurFlag"))
      parseElementProperties(json.getAsJsonObject("_didNotOccurFlag"), res.getDidNotOccurFlag());
    if (json.has("recorder"))
      res.setRecorder(parseResourceReference(json.getAsJsonObject("recorder")));
    if (json.has("symptom")) {
      JsonArray array = json.getAsJsonArray("symptom");
      for (int i = 0; i < array.size(); i++) {
        res.getSymptom().add(parseAdverseReactionAdverseReactionSymptomComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("exposure")) {
      JsonArray array = json.getAsJsonArray("exposure");
      for (int i = 0; i < array.size(); i++) {
        res.getExposure().add(parseAdverseReactionAdverseReactionExposureComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private AdverseReaction.AdverseReactionSymptomComponent parseAdverseReactionAdverseReactionSymptomComponent(JsonObject json, AdverseReaction owner) throws Exception {
    AdverseReaction.AdverseReactionSymptomComponent res = new AdverseReaction.AdverseReactionSymptomComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("severity"))
      res.setSeverity(parseEnumeration(json.get("severity").getAsString(), AdverseReaction.ReactionSeverity.Null, new AdverseReaction.ReactionSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverity());
    return res;
  }

  private AdverseReaction.AdverseReactionExposureComponent parseAdverseReactionAdverseReactionExposureComponent(JsonObject json, AdverseReaction owner) throws Exception {
    AdverseReaction.AdverseReactionExposureComponent res = new AdverseReaction.AdverseReactionExposureComponent();
    parseBackboneProperties(json, res);
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), AdverseReaction.ExposureType.Null, new AdverseReaction.ExposureTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("causalityExpectation"))
      res.setCausalityExpectation(parseEnumeration(json.get("causalityExpectation").getAsString(), AdverseReaction.CausalityExpectation.Null, new AdverseReaction.CausalityExpectationEnumFactory()));
    if (json.has("_causalityExpectation"))
      parseElementProperties(json.getAsJsonObject("_causalityExpectation"), res.getCausalityExpectation());
    if (json.has("substance"))
      res.setSubstance(parseResourceReference(json.getAsJsonObject("substance")));
    return res;
  }

  private Alert parseAlert(JsonObject json) throws Exception {
    Alert res = new Alert();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Alert.AlertStatus.Null, new Alert.AlertStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getAsJsonObject("author")));
    if (json.has("note"))
      res.setNote(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNote());
    return res;
  }

  private AllergyIntolerance parseAllergyIntolerance(JsonObject json) throws Exception {
    AllergyIntolerance res = new AllergyIntolerance();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("criticality"))
      res.setCriticality(parseEnumeration(json.get("criticality").getAsString(), AllergyIntolerance.Criticality.Null, new AllergyIntolerance.CriticalityEnumFactory()));
    if (json.has("_criticality"))
      parseElementProperties(json.getAsJsonObject("_criticality"), res.getCriticality());
    if (json.has("sensitivityType"))
      res.setSensitivityType(parseEnumeration(json.get("sensitivityType").getAsString(), AllergyIntolerance.Sensitivitytype.Null, new AllergyIntolerance.SensitivitytypeEnumFactory()));
    if (json.has("_sensitivityType"))
      parseElementProperties(json.getAsJsonObject("_sensitivityType"), res.getSensitivityType());
    if (json.has("recordedDate"))
      res.setRecordedDate(parseDateTime(json.get("recordedDate").getAsString()));
    if (json.has("_recordedDate"))
      parseElementProperties(json.getAsJsonObject("_recordedDate"), res.getRecordedDate());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), AllergyIntolerance.Sensitivitystatus.Null, new AllergyIntolerance.SensitivitystatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("recorder"))
      res.setRecorder(parseResourceReference(json.getAsJsonObject("recorder")));
    if (json.has("substance"))
      res.setSubstance(parseResourceReference(json.getAsJsonObject("substance")));
    if (json.has("reaction")) {
      JsonArray array = json.getAsJsonArray("reaction");
      for (int i = 0; i < array.size(); i++) {
        res.getReaction().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("sensitivityTest")) {
      JsonArray array = json.getAsJsonArray("sensitivityTest");
      for (int i = 0; i < array.size(); i++) {
        res.getSensitivityTest().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Appointment parseAppointment(JsonObject json) throws Exception {
    Appointment res = new Appointment();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("priority"))
      res.setPriority(parseInteger(json.get("priority").getAsLong()));
    if (json.has("_priority"))
      parseElementProperties(json.getAsJsonObject("_priority"), res.getPriority());
    if (json.has("status"))
      res.setStatus(parseCode(json.get("status").getAsString()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("start"))
      res.setStart(parseInstant(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStart());
    if (json.has("end"))
      res.setEnd(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEnd());
    if (json.has("slot")) {
      JsonArray array = json.getAsJsonArray("slot");
      for (int i = 0; i < array.size(); i++) {
        res.getSlot().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getAsJsonObject("location")));
    if (json.has("comment"))
      res.setComment(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getComment());
    if (json.has("order"))
      res.setOrder(parseResourceReference(json.getAsJsonObject("order")));
    if (json.has("participant")) {
      JsonArray array = json.getAsJsonArray("participant");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipant().add(parseAppointmentAppointmentParticipantComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("lastModifiedBy"))
      res.setLastModifiedBy(parseResourceReference(json.getAsJsonObject("lastModifiedBy")));
    if (json.has("lastModified"))
      res.setLastModified(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModified());
    return res;
  }

  private Appointment.AppointmentParticipantComponent parseAppointmentAppointmentParticipantComponent(JsonObject json, Appointment owner) throws Exception {
    Appointment.AppointmentParticipantComponent res = new Appointment.AppointmentParticipantComponent();
    parseBackboneProperties(json, res);
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("actor"))
      res.setActor(parseResourceReference(json.getAsJsonObject("actor")));
    if (json.has("required"))
      res.setRequired(parseEnumeration(json.get("required").getAsString(), Appointment.Participantrequired.Null, new Appointment.ParticipantrequiredEnumFactory()));
    if (json.has("_required"))
      parseElementProperties(json.getAsJsonObject("_required"), res.getRequired());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Appointment.Participationstatus.Null, new Appointment.ParticipationstatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    return res;
  }

  private AppointmentResponse parseAppointmentResponse(JsonObject json) throws Exception {
    AppointmentResponse res = new AppointmentResponse();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("appointment"))
      res.setAppointment(parseResourceReference(json.getAsJsonObject("appointment")));
    if (json.has("participantType")) {
      JsonArray array = json.getAsJsonArray("participantType");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipantType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("individual")) {
      JsonArray array = json.getAsJsonArray("individual");
      for (int i = 0; i < array.size(); i++) {
        res.getIndividual().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("participantStatus"))
      res.setParticipantStatus(parseEnumeration(json.get("participantStatus").getAsString(), AppointmentResponse.Participantstatus.Null, new AppointmentResponse.ParticipantstatusEnumFactory()));
    if (json.has("_participantStatus"))
      parseElementProperties(json.getAsJsonObject("_participantStatus"), res.getParticipantStatus());
    if (json.has("comment"))
      res.setComment(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getComment());
    if (json.has("start"))
      res.setStart(parseInstant(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStart());
    if (json.has("end"))
      res.setEnd(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEnd());
    if (json.has("lastModifiedBy"))
      res.setLastModifiedBy(parseResourceReference(json.getAsJsonObject("lastModifiedBy")));
    if (json.has("lastModified"))
      res.setLastModified(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModified());
    return res;
  }

  private Availability parseAvailability(JsonObject json) throws Exception {
    Availability res = new Availability();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("actor"))
      res.setActor(parseResourceReference(json.getAsJsonObject("actor")));
    if (json.has("planningHorizon"))
      res.setPlanningHorizon(parsePeriod(json.getAsJsonObject("planningHorizon")));
    if (json.has("comment"))
      res.setComment(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getComment());
    if (json.has("lastModified"))
      res.setLastModified(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModified());
    return res;
  }

  private CarePlan parseCarePlan(JsonObject json) throws Exception {
    CarePlan res = new CarePlan();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), CarePlan.CarePlanStatus.Null, new CarePlan.CarePlanStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("modified"))
      res.setModified(parseDateTime(json.get("modified").getAsString()));
    if (json.has("_modified"))
      parseElementProperties(json.getAsJsonObject("_modified"), res.getModified());
    if (json.has("concern")) {
      JsonArray array = json.getAsJsonArray("concern");
      for (int i = 0; i < array.size(); i++) {
        res.getConcern().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("participant")) {
      JsonArray array = json.getAsJsonArray("participant");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipant().add(parseCarePlanCarePlanParticipantComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("goal")) {
      JsonArray array = json.getAsJsonArray("goal");
      for (int i = 0; i < array.size(); i++) {
        res.getGoal().add(parseCarePlanCarePlanGoalComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("activity")) {
      JsonArray array = json.getAsJsonArray("activity");
      for (int i = 0; i < array.size(); i++) {
        res.getActivity().add(parseCarePlanCarePlanActivityComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotes());
    return res;
  }

  private CarePlan.CarePlanParticipantComponent parseCarePlanCarePlanParticipantComponent(JsonObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanParticipantComponent res = new CarePlan.CarePlanParticipantComponent();
    parseBackboneProperties(json, res);
    if (json.has("role"))
      res.setRole(parseCodeableConcept(json.getAsJsonObject("role")));
    if (json.has("member"))
      res.setMember(parseResourceReference(json.getAsJsonObject("member")));
    return res;
  }

  private CarePlan.CarePlanGoalComponent parseCarePlanCarePlanGoalComponent(JsonObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanGoalComponent res = new CarePlan.CarePlanGoalComponent();
    parseBackboneProperties(json, res);
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), CarePlan.CarePlanGoalStatus.Null, new CarePlan.CarePlanGoalStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("notes"))
      res.setNotes(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotes());
    if (json.has("concern")) {
      JsonArray array = json.getAsJsonArray("concern");
      for (int i = 0; i < array.size(); i++) {
        res.getConcern().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private CarePlan.CarePlanActivityComponent parseCarePlanCarePlanActivityComponent(JsonObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivityComponent res = new CarePlan.CarePlanActivityComponent();
    parseBackboneProperties(json, res);
    if (json.has("goal")) {
      JsonArray array = json.getAsJsonArray("goal");
      for (int i = 0; i < array.size(); i++) {
        res.getGoal().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_goal")) {
      JsonArray array = json.getAsJsonArray("_goal");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getGoal().size())
          res.getGoal().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getGoal().get(i));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), CarePlan.CarePlanActivityStatus.Null, new CarePlan.CarePlanActivityStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("prohibited"))
      res.setProhibited(parseBoolean(json.get("prohibited").getAsBoolean()));
    if (json.has("_prohibited"))
      parseElementProperties(json.getAsJsonObject("_prohibited"), res.getProhibited());
    if (json.has("actionResulting")) {
      JsonArray array = json.getAsJsonArray("actionResulting");
      for (int i = 0; i < array.size(); i++) {
        res.getActionResulting().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotes());
    if (json.has("detail"))
      res.setDetail(parseResourceReference(json.getAsJsonObject("detail")));
    if (json.has("simple"))
      res.setSimple(parseCarePlanCarePlanActivitySimpleComponent(json.getAsJsonObject("simple"), owner));
    return res;
  }

  private CarePlan.CarePlanActivitySimpleComponent parseCarePlanCarePlanActivitySimpleComponent(JsonObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivitySimpleComponent res = new CarePlan.CarePlanActivitySimpleComponent();
    parseBackboneProperties(json, res);
    if (json.has("category"))
      res.setCategory(parseEnumeration(json.get("category").getAsString(), CarePlan.CarePlanActivityCategory.Null, new CarePlan.CarePlanActivityCategoryEnumFactory()));
    if (json.has("_category"))
      parseElementProperties(json.getAsJsonObject("_category"), res.getCategory());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getAsJsonObject("location")));
    if (json.has("performer")) {
      JsonArray array = json.getAsJsonArray("performer");
      for (int i = 0; i < array.size(); i++) {
        res.getPerformer().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("product"))
      res.setProduct(parseResourceReference(json.getAsJsonObject("product")));
    if (json.has("dailyAmount"))
      res.setDailyAmount(parseQuantity(json.getAsJsonObject("dailyAmount")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("details"))
      res.setDetails(parseString(json.get("details").getAsString()));
    if (json.has("_details"))
      parseElementProperties(json.getAsJsonObject("_details"), res.getDetails());
    return res;
  }

  private Composition parseComposition(JsonObject json) throws Exception {
    Composition res = new Composition();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("class"))
      res.setClass_(parseCodeableConcept(json.getAsJsonObject("class")));
    if (json.has("title"))
      res.setTitle(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitle());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Composition.CompositionStatus.Null, new Composition.CompositionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("confidentiality"))
      res.setConfidentiality(parseCoding(json.getAsJsonObject("confidentiality")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("author")) {
      JsonArray array = json.getAsJsonArray("author");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthor().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("attester")) {
      JsonArray array = json.getAsJsonArray("attester");
      for (int i = 0; i < array.size(); i++) {
        res.getAttester().add(parseCompositionCompositionAttesterComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("custodian"))
      res.setCustodian(parseResourceReference(json.getAsJsonObject("custodian")));
    if (json.has("event"))
      res.setEvent(parseCompositionCompositionEventComponent(json.getAsJsonObject("event"), res));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("section")) {
      JsonArray array = json.getAsJsonArray("section");
      for (int i = 0; i < array.size(); i++) {
        res.getSection().add(parseCompositionSectionComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Composition.CompositionAttesterComponent parseCompositionCompositionAttesterComponent(JsonObject json, Composition owner) throws Exception {
    Composition.CompositionAttesterComponent res = new Composition.CompositionAttesterComponent();
    parseBackboneProperties(json, res);
    if (json.has("mode")) {
      JsonArray array = json.getAsJsonArray("mode");
      for (int i = 0; i < array.size(); i++) {
        res.getMode().add(parseEnumeration(array.get(i).getAsString(), Composition.CompositionAttestationMode.Null, new Composition.CompositionAttestationModeEnumFactory()));
      }
    };
    if (json.has("_mode")) {
      JsonArray array = json.getAsJsonArray("_mode");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getMode().size())
          res.getMode().add(parseEnumeration(null, Composition.CompositionAttestationMode.Null, new Composition.CompositionAttestationModeEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getMode().get(i));
      }
    };
    if (json.has("time"))
      res.setTime(parseDateTime(json.get("time").getAsString()));
    if (json.has("_time"))
      parseElementProperties(json.getAsJsonObject("_time"), res.getTime());
    if (json.has("party"))
      res.setParty(parseResourceReference(json.getAsJsonObject("party")));
    return res;
  }

  private Composition.CompositionEventComponent parseCompositionCompositionEventComponent(JsonObject json, Composition owner) throws Exception {
    Composition.CompositionEventComponent res = new Composition.CompositionEventComponent();
    parseBackboneProperties(json, res);
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Composition.SectionComponent parseCompositionSectionComponent(JsonObject json, Composition owner) throws Exception {
    Composition.SectionComponent res = new Composition.SectionComponent();
    parseBackboneProperties(json, res);
    if (json.has("title"))
      res.setTitle(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitle());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("content"))
      res.setContent(parseResourceReference(json.getAsJsonObject("content")));
    if (json.has("section")) {
      JsonArray array = json.getAsJsonArray("section");
      for (int i = 0; i < array.size(); i++) {
        res.getSection().add(parseCompositionSectionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ConceptMap parseConceptMap(JsonObject json) throws Exception {
    ConceptMap res = new ConceptMap();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("copyright"))
      res.setCopyright(parseString(json.get("copyright").getAsString()));
    if (json.has("_copyright"))
      parseElementProperties(json.getAsJsonObject("_copyright"), res.getCopyright());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), ConceptMap.ValuesetStatus.Null, new ConceptMap.ValuesetStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimental());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    Type source = parseType("source", json);
    if (source != null)
      res.setSource(source);
    Type target = parseType("target", json);
    if (target != null)
      res.setTarget(target);
    if (json.has("element")) {
      JsonArray array = json.getAsJsonArray("element");
      for (int i = 0; i < array.size(); i++) {
        res.getElement().add(parseConceptMapConceptMapElementComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private ConceptMap.ConceptMapElementComponent parseConceptMapConceptMapElementComponent(JsonObject json, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapElementComponent res = new ConceptMap.ConceptMapElementComponent();
    parseBackboneProperties(json, res);
    if (json.has("codeSystem"))
      res.setCodeSystem(parseUri(json.get("codeSystem").getAsString()));
    if (json.has("_codeSystem"))
      parseElementProperties(json.getAsJsonObject("_codeSystem"), res.getCodeSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("dependsOn")) {
      JsonArray array = json.getAsJsonArray("dependsOn");
      for (int i = 0; i < array.size(); i++) {
        res.getDependsOn().add(parseConceptMapOtherElementComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("map")) {
      JsonArray array = json.getAsJsonArray("map");
      for (int i = 0; i < array.size(); i++) {
        res.getMap().add(parseConceptMapConceptMapElementMapComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ConceptMap.OtherElementComponent parseConceptMapOtherElementComponent(JsonObject json, ConceptMap owner) throws Exception {
    ConceptMap.OtherElementComponent res = new ConceptMap.OtherElementComponent();
    parseBackboneProperties(json, res);
    if (json.has("element"))
      res.setElement(parseUri(json.get("element").getAsString()));
    if (json.has("_element"))
      parseElementProperties(json.getAsJsonObject("_element"), res.getElement());
    if (json.has("codeSystem"))
      res.setCodeSystem(parseUri(json.get("codeSystem").getAsString()));
    if (json.has("_codeSystem"))
      parseElementProperties(json.getAsJsonObject("_codeSystem"), res.getCodeSystem());
    if (json.has("code"))
      res.setCode(parseString(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    return res;
  }

  private ConceptMap.ConceptMapElementMapComponent parseConceptMapConceptMapElementMapComponent(JsonObject json, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapElementMapComponent res = new ConceptMap.ConceptMapElementMapComponent();
    parseBackboneProperties(json, res);
    if (json.has("codeSystem"))
      res.setCodeSystem(parseUri(json.get("codeSystem").getAsString()));
    if (json.has("_codeSystem"))
      parseElementProperties(json.getAsJsonObject("_codeSystem"), res.getCodeSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("equivalence"))
      res.setEquivalence(parseEnumeration(json.get("equivalence").getAsString(), ConceptMap.ConceptEquivalence.Null, new ConceptMap.ConceptEquivalenceEnumFactory()));
    if (json.has("_equivalence"))
      parseElementProperties(json.getAsJsonObject("_equivalence"), res.getEquivalence());
    if (json.has("comments"))
      res.setComments(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getComments());
    if (json.has("product")) {
      JsonArray array = json.getAsJsonArray("product");
      for (int i = 0; i < array.size(); i++) {
        res.getProduct().add(parseConceptMapOtherElementComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Condition parseCondition(JsonObject json) throws Exception {
    Condition res = new Condition();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("asserter"))
      res.setAsserter(parseResourceReference(json.getAsJsonObject("asserter")));
    if (json.has("dateAsserted"))
      res.setDateAsserted(parseDate(json.get("dateAsserted").getAsString()));
    if (json.has("_dateAsserted"))
      parseElementProperties(json.getAsJsonObject("_dateAsserted"), res.getDateAsserted());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Condition.ConditionStatus.Null, new Condition.ConditionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("certainty"))
      res.setCertainty(parseCodeableConcept(json.getAsJsonObject("certainty")));
    if (json.has("severity"))
      res.setSeverity(parseCodeableConcept(json.getAsJsonObject("severity")));
    Type onset = parseType("onset", json);
    if (onset != null)
      res.setOnset(onset);
    Type abatement = parseType("abatement", json);
    if (abatement != null)
      res.setAbatement(abatement);
    if (json.has("stage"))
      res.setStage(parseConditionConditionStageComponent(json.getAsJsonObject("stage"), res));
    if (json.has("evidence")) {
      JsonArray array = json.getAsJsonArray("evidence");
      for (int i = 0; i < array.size(); i++) {
        res.getEvidence().add(parseConditionConditionEvidenceComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("location")) {
      JsonArray array = json.getAsJsonArray("location");
      for (int i = 0; i < array.size(); i++) {
        res.getLocation().add(parseConditionConditionLocationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("relatedItem")) {
      JsonArray array = json.getAsJsonArray("relatedItem");
      for (int i = 0; i < array.size(); i++) {
        res.getRelatedItem().add(parseConditionConditionRelatedItemComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotes());
    return res;
  }

  private Condition.ConditionStageComponent parseConditionConditionStageComponent(JsonObject json, Condition owner) throws Exception {
    Condition.ConditionStageComponent res = new Condition.ConditionStageComponent();
    parseBackboneProperties(json, res);
    if (json.has("summary"))
      res.setSummary(parseCodeableConcept(json.getAsJsonObject("summary")));
    if (json.has("assessment")) {
      JsonArray array = json.getAsJsonArray("assessment");
      for (int i = 0; i < array.size(); i++) {
        res.getAssessment().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Condition.ConditionEvidenceComponent parseConditionConditionEvidenceComponent(JsonObject json, Condition owner) throws Exception {
    Condition.ConditionEvidenceComponent res = new Condition.ConditionEvidenceComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Condition.ConditionLocationComponent parseConditionConditionLocationComponent(JsonObject json, Condition owner) throws Exception {
    Condition.ConditionLocationComponent res = new Condition.ConditionLocationComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("detail"))
      res.setDetail(parseString(json.get("detail").getAsString()));
    if (json.has("_detail"))
      parseElementProperties(json.getAsJsonObject("_detail"), res.getDetail());
    return res;
  }

  private Condition.ConditionRelatedItemComponent parseConditionConditionRelatedItemComponent(JsonObject json, Condition owner) throws Exception {
    Condition.ConditionRelatedItemComponent res = new Condition.ConditionRelatedItemComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Condition.ConditionRelationshipType.Null, new Condition.ConditionRelationshipTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getAsJsonObject("target")));
    return res;
  }

  private Conformance parseConformance(JsonObject json) throws Exception {
    Conformance res = new Conformance();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Conformance.ConformanceStatementStatus.Null, new Conformance.ConformanceStatementStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimental());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("software"))
      res.setSoftware(parseConformanceConformanceSoftwareComponent(json.getAsJsonObject("software"), res));
    if (json.has("implementation"))
      res.setImplementation(parseConformanceConformanceImplementationComponent(json.getAsJsonObject("implementation"), res));
    if (json.has("fhirVersion"))
      res.setFhirVersion(parseId(json.get("fhirVersion").getAsString()));
    if (json.has("_fhirVersion"))
      parseElementProperties(json.getAsJsonObject("_fhirVersion"), res.getFhirVersion());
    if (json.has("acceptUnknown"))
      res.setAcceptUnknown(parseBoolean(json.get("acceptUnknown").getAsBoolean()));
    if (json.has("_acceptUnknown"))
      parseElementProperties(json.getAsJsonObject("_acceptUnknown"), res.getAcceptUnknown());
    if (json.has("format")) {
      JsonArray array = json.getAsJsonArray("format");
      for (int i = 0; i < array.size(); i++) {
        res.getFormat().add(parseCode(array.get(i).getAsString()));
      }
    };
    if (json.has("_format")) {
      JsonArray array = json.getAsJsonArray("_format");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getFormat().size())
          res.getFormat().add(parseCode(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getFormat().get(i));
      }
    };
    if (json.has("profile")) {
      JsonArray array = json.getAsJsonArray("profile");
      for (int i = 0; i < array.size(); i++) {
        res.getProfile().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("rest")) {
      JsonArray array = json.getAsJsonArray("rest");
      for (int i = 0; i < array.size(); i++) {
        res.getRest().add(parseConformanceConformanceRestComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("messaging")) {
      JsonArray array = json.getAsJsonArray("messaging");
      for (int i = 0; i < array.size(); i++) {
        res.getMessaging().add(parseConformanceConformanceMessagingComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("document")) {
      JsonArray array = json.getAsJsonArray("document");
      for (int i = 0; i < array.size(); i++) {
        res.getDocument().add(parseConformanceConformanceDocumentComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Conformance.ConformanceSoftwareComponent parseConformanceConformanceSoftwareComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceSoftwareComponent res = new Conformance.ConformanceSoftwareComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("releaseDate"))
      res.setReleaseDate(parseDateTime(json.get("releaseDate").getAsString()));
    if (json.has("_releaseDate"))
      parseElementProperties(json.getAsJsonObject("_releaseDate"), res.getReleaseDate());
    return res;
  }

  private Conformance.ConformanceImplementationComponent parseConformanceConformanceImplementationComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceImplementationComponent res = new Conformance.ConformanceImplementationComponent();
    parseBackboneProperties(json, res);
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    return res;
  }

  private Conformance.ConformanceRestComponent parseConformanceConformanceRestComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestComponent res = new Conformance.ConformanceRestComponent();
    parseBackboneProperties(json, res);
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.get("mode").getAsString(), Conformance.RestfulConformanceMode.Null, new Conformance.RestfulConformanceModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getMode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    if (json.has("security"))
      res.setSecurity(parseConformanceConformanceRestSecurityComponent(json.getAsJsonObject("security"), owner));
    if (json.has("resource")) {
      JsonArray array = json.getAsJsonArray("resource");
      for (int i = 0; i < array.size(); i++) {
        res.getResource().add(parseConformanceConformanceRestResourceComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("interaction")) {
      JsonArray array = json.getAsJsonArray("interaction");
      for (int i = 0; i < array.size(); i++) {
        res.getInteraction().add(parseConformanceSystemInteractionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("operation")) {
      JsonArray array = json.getAsJsonArray("operation");
      for (int i = 0; i < array.size(); i++) {
        res.getOperation().add(parseConformanceConformanceRestOperationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("documentMailbox")) {
      JsonArray array = json.getAsJsonArray("documentMailbox");
      for (int i = 0; i < array.size(); i++) {
        res.getDocumentMailbox().add(parseUri(array.get(i).getAsString()));
      }
    };
    if (json.has("_documentMailbox")) {
      JsonArray array = json.getAsJsonArray("_documentMailbox");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getDocumentMailbox().size())
          res.getDocumentMailbox().add(parseUri(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getDocumentMailbox().get(i));
      }
    };
    return res;
  }

  private Conformance.ConformanceRestSecurityComponent parseConformanceConformanceRestSecurityComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityComponent res = new Conformance.ConformanceRestSecurityComponent();
    parseBackboneProperties(json, res);
    if (json.has("cors"))
      res.setCors(parseBoolean(json.get("cors").getAsBoolean()));
    if (json.has("_cors"))
      parseElementProperties(json.getAsJsonObject("_cors"), res.getCors());
    if (json.has("service")) {
      JsonArray array = json.getAsJsonArray("service");
      for (int i = 0; i < array.size(); i++) {
        res.getService().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("certificate")) {
      JsonArray array = json.getAsJsonArray("certificate");
      for (int i = 0; i < array.size(); i++) {
        res.getCertificate().add(parseConformanceConformanceRestSecurityCertificateComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Conformance.ConformanceRestSecurityCertificateComponent parseConformanceConformanceRestSecurityCertificateComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityCertificateComponent res = new Conformance.ConformanceRestSecurityCertificateComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("blob"))
      res.setBlob(parseBase64Binary(json.get("blob").getAsString()));
    if (json.has("_blob"))
      parseElementProperties(json.getAsJsonObject("_blob"), res.getBlob());
    return res;
  }

  private Conformance.ConformanceRestResourceComponent parseConformanceConformanceRestResourceComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("profile"))
      res.setProfile(parseResourceReference(json.getAsJsonObject("profile")));
    if (json.has("interaction")) {
      JsonArray array = json.getAsJsonArray("interaction");
      for (int i = 0; i < array.size(); i++) {
        res.getInteraction().add(parseConformanceResourceInteractionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("readHistory"))
      res.setReadHistory(parseBoolean(json.get("readHistory").getAsBoolean()));
    if (json.has("_readHistory"))
      parseElementProperties(json.getAsJsonObject("_readHistory"), res.getReadHistory());
    if (json.has("updateCreate"))
      res.setUpdateCreate(parseBoolean(json.get("updateCreate").getAsBoolean()));
    if (json.has("_updateCreate"))
      parseElementProperties(json.getAsJsonObject("_updateCreate"), res.getUpdateCreate());
    if (json.has("searchInclude")) {
      JsonArray array = json.getAsJsonArray("searchInclude");
      for (int i = 0; i < array.size(); i++) {
        res.getSearchInclude().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_searchInclude")) {
      JsonArray array = json.getAsJsonArray("_searchInclude");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getSearchInclude().size())
          res.getSearchInclude().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getSearchInclude().get(i));
      }
    };
    if (json.has("searchParam")) {
      JsonArray array = json.getAsJsonArray("searchParam");
      for (int i = 0; i < array.size(); i++) {
        res.getSearchParam().add(parseConformanceConformanceRestResourceSearchParamComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Conformance.ResourceInteractionComponent parseConformanceResourceInteractionComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ResourceInteractionComponent res = new Conformance.ResourceInteractionComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseEnumeration(json.get("code").getAsString(), Conformance.TypeRestfulInteraction.Null, new Conformance.TypeRestfulInteractionEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    return res;
  }

  private Conformance.ConformanceRestResourceSearchParamComponent parseConformanceConformanceRestResourceSearchParamComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceSearchParamComponent res = new Conformance.ConformanceRestResourceSearchParamComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("definition"))
      res.setDefinition(parseUri(json.get("definition").getAsString()));
    if (json.has("_definition"))
      parseElementProperties(json.getAsJsonObject("_definition"), res.getDefinition());
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Conformance.SearchParamType.Null, new Conformance.SearchParamTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    if (json.has("target")) {
      JsonArray array = json.getAsJsonArray("target");
      for (int i = 0; i < array.size(); i++) {
        res.getTarget().add(parseCode(array.get(i).getAsString()));
      }
    };
    if (json.has("_target")) {
      JsonArray array = json.getAsJsonArray("_target");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getTarget().size())
          res.getTarget().add(parseCode(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getTarget().get(i));
      }
    };
    if (json.has("chain")) {
      JsonArray array = json.getAsJsonArray("chain");
      for (int i = 0; i < array.size(); i++) {
        res.getChain().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_chain")) {
      JsonArray array = json.getAsJsonArray("_chain");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getChain().size())
          res.getChain().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getChain().get(i));
      }
    };
    return res;
  }

  private Conformance.SystemInteractionComponent parseConformanceSystemInteractionComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.SystemInteractionComponent res = new Conformance.SystemInteractionComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseEnumeration(json.get("code").getAsString(), Conformance.SystemRestfulInteraction.Null, new Conformance.SystemRestfulInteractionEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    return res;
  }

  private Conformance.ConformanceRestOperationComponent parseConformanceConformanceRestOperationComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestOperationComponent res = new Conformance.ConformanceRestOperationComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("definition"))
      res.setDefinition(parseResourceReference(json.getAsJsonObject("definition")));
    return res;
  }

  private Conformance.ConformanceMessagingComponent parseConformanceConformanceMessagingComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingComponent res = new Conformance.ConformanceMessagingComponent();
    parseBackboneProperties(json, res);
    if (json.has("endpoint"))
      res.setEndpoint(parseUri(json.get("endpoint").getAsString()));
    if (json.has("_endpoint"))
      parseElementProperties(json.getAsJsonObject("_endpoint"), res.getEndpoint());
    if (json.has("reliableCache"))
      res.setReliableCache(parseInteger(json.get("reliableCache").getAsLong()));
    if (json.has("_reliableCache"))
      parseElementProperties(json.getAsJsonObject("_reliableCache"), res.getReliableCache());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parseConformanceConformanceMessagingEventComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Conformance.ConformanceMessagingEventComponent parseConformanceConformanceMessagingEventComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingEventComponent res = new Conformance.ConformanceMessagingEventComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCoding(json.getAsJsonObject("code")));
    if (json.has("category"))
      res.setCategory(parseEnumeration(json.get("category").getAsString(), Conformance.MessageSignificanceCategory.Null, new Conformance.MessageSignificanceCategoryEnumFactory()));
    if (json.has("_category"))
      parseElementProperties(json.getAsJsonObject("_category"), res.getCategory());
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.get("mode").getAsString(), Conformance.MessageConformanceEventMode.Null, new Conformance.MessageConformanceEventModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getMode());
    if (json.has("protocol")) {
      JsonArray array = json.getAsJsonArray("protocol");
      for (int i = 0; i < array.size(); i++) {
        res.getProtocol().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("focus"))
      res.setFocus(parseCode(json.get("focus").getAsString()));
    if (json.has("_focus"))
      parseElementProperties(json.getAsJsonObject("_focus"), res.getFocus());
    if (json.has("request"))
      res.setRequest(parseResourceReference(json.getAsJsonObject("request")));
    if (json.has("response"))
      res.setResponse(parseResourceReference(json.getAsJsonObject("response")));
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    return res;
  }

  private Conformance.ConformanceDocumentComponent parseConformanceConformanceDocumentComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceDocumentComponent res = new Conformance.ConformanceDocumentComponent();
    parseBackboneProperties(json, res);
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.get("mode").getAsString(), Conformance.DocumentMode.Null, new Conformance.DocumentModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getMode());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    if (json.has("profile"))
      res.setProfile(parseResourceReference(json.getAsJsonObject("profile")));
    return res;
  }

  private Contraindication parseContraindication(JsonObject json) throws Exception {
    Contraindication res = new Contraindication();
    parseResourceProperties(json, res);
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("severity"))
      res.setSeverity(parseCode(json.get("severity").getAsString()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverity());
    if (json.has("implicated")) {
      JsonArray array = json.getAsJsonArray("implicated");
      for (int i = 0; i < array.size(); i++) {
        res.getImplicated().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("detail"))
      res.setDetail(parseString(json.get("detail").getAsString()));
    if (json.has("_detail"))
      parseElementProperties(json.getAsJsonObject("_detail"), res.getDetail());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getAsJsonObject("author")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("reference"))
      res.setReference(parseUri(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReference());
    if (json.has("mitigation")) {
      JsonArray array = json.getAsJsonArray("mitigation");
      for (int i = 0; i < array.size(); i++) {
        res.getMitigation().add(parseContraindicationContraindicationMitigationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Contraindication.ContraindicationMitigationComponent parseContraindicationContraindicationMitigationComponent(JsonObject json, Contraindication owner) throws Exception {
    Contraindication.ContraindicationMitigationComponent res = new Contraindication.ContraindicationMitigationComponent();
    parseBackboneProperties(json, res);
    if (json.has("action"))
      res.setAction(parseCodeableConcept(json.getAsJsonObject("action")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getAsJsonObject("author")));
    return res;
  }

  private DataElement parseDataElement(JsonObject json) throws Exception {
    DataElement res = new DataElement();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), DataElement.ResourceObservationDefStatus.Null, new DataElement.ResourceObservationDefStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("category")) {
      JsonArray array = json.getAsJsonArray("category");
      for (int i = 0; i < array.size(); i++) {
        res.getCategory().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("question"))
      res.setQuestion(parseString(json.get("question").getAsString()));
    if (json.has("_question"))
      parseElementProperties(json.getAsJsonObject("_question"), res.getQuestion());
    if (json.has("definition"))
      res.setDefinition(parseString(json.get("definition").getAsString()));
    if (json.has("_definition"))
      parseElementProperties(json.getAsJsonObject("_definition"), res.getDefinition());
    if (json.has("comments"))
      res.setComments(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getComments());
    if (json.has("requirements"))
      res.setRequirements(parseString(json.get("requirements").getAsString()));
    if (json.has("_requirements"))
      parseElementProperties(json.getAsJsonObject("_requirements"), res.getRequirements());
    if (json.has("synonym")) {
      JsonArray array = json.getAsJsonArray("synonym");
      for (int i = 0; i < array.size(); i++) {
        res.getSynonym().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_synonym")) {
      JsonArray array = json.getAsJsonArray("_synonym");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getSynonym().size())
          res.getSynonym().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getSynonym().get(i));
      }
    };
    if (json.has("type"))
      res.setType(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    Type example = parseType("example", json);
    if (example != null)
      res.setExample(example);
    if (json.has("maxLength"))
      res.setMaxLength(parseInteger(json.get("maxLength").getAsLong()));
    if (json.has("_maxLength"))
      parseElementProperties(json.getAsJsonObject("_maxLength"), res.getMaxLength());
    if (json.has("units"))
      res.setUnits(parseCodeableConcept(json.getAsJsonObject("units")));
    if (json.has("binding"))
      res.setBinding(parseDataElementDataElementBindingComponent(json.getAsJsonObject("binding"), res));
    if (json.has("mapping")) {
      JsonArray array = json.getAsJsonArray("mapping");
      for (int i = 0; i < array.size(); i++) {
        res.getMapping().add(parseDataElementDataElementMappingComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private DataElement.DataElementBindingComponent parseDataElementDataElementBindingComponent(JsonObject json, DataElement owner) throws Exception {
    DataElement.DataElementBindingComponent res = new DataElement.DataElementBindingComponent();
    parseBackboneProperties(json, res);
    if (json.has("isExtensible"))
      res.setIsExtensible(parseBoolean(json.get("isExtensible").getAsBoolean()));
    if (json.has("_isExtensible"))
      parseElementProperties(json.getAsJsonObject("_isExtensible"), res.getIsExtensible());
    if (json.has("conformance"))
      res.setConformance(parseEnumeration(json.get("conformance").getAsString(), DataElement.BindingConformance.Null, new DataElement.BindingConformanceEnumFactory()));
    if (json.has("_conformance"))
      parseElementProperties(json.getAsJsonObject("_conformance"), res.getConformance());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("valueSet"))
      res.setValueSet(parseResourceReference(json.getAsJsonObject("valueSet")));
    return res;
  }

  private DataElement.DataElementMappingComponent parseDataElementDataElementMappingComponent(JsonObject json, DataElement owner) throws Exception {
    DataElement.DataElementMappingComponent res = new DataElement.DataElementMappingComponent();
    parseBackboneProperties(json, res);
    if (json.has("uri"))
      res.setUri(parseUri(json.get("uri").getAsString()));
    if (json.has("_uri"))
      parseElementProperties(json.getAsJsonObject("_uri"), res.getUri());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("comments"))
      res.setComments(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getComments());
    if (json.has("map"))
      res.setMap(parseString(json.get("map").getAsString()));
    if (json.has("_map"))
      parseElementProperties(json.getAsJsonObject("_map"), res.getMap());
    return res;
  }

  private Device parseDevice(JsonObject json) throws Exception {
    Device res = new Device();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("manufacturer"))
      res.setManufacturer(parseString(json.get("manufacturer").getAsString()));
    if (json.has("_manufacturer"))
      parseElementProperties(json.getAsJsonObject("_manufacturer"), res.getManufacturer());
    if (json.has("model"))
      res.setModel(parseString(json.get("model").getAsString()));
    if (json.has("_model"))
      parseElementProperties(json.getAsJsonObject("_model"), res.getModel());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("expiry"))
      res.setExpiry(parseDate(json.get("expiry").getAsString()));
    if (json.has("_expiry"))
      parseElementProperties(json.getAsJsonObject("_expiry"), res.getExpiry());
    if (json.has("udi"))
      res.setUdi(parseString(json.get("udi").getAsString()));
    if (json.has("_udi"))
      parseElementProperties(json.getAsJsonObject("_udi"), res.getUdi());
    if (json.has("lotNumber"))
      res.setLotNumber(parseString(json.get("lotNumber").getAsString()));
    if (json.has("_lotNumber"))
      parseElementProperties(json.getAsJsonObject("_lotNumber"), res.getLotNumber());
    if (json.has("owner"))
      res.setOwner(parseResourceReference(json.getAsJsonObject("owner")));
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getAsJsonObject("location")));
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("contact")) {
      JsonArray array = json.getAsJsonArray("contact");
      for (int i = 0; i < array.size(); i++) {
        res.getContact().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    return res;
  }

  private DeviceObservationReport parseDeviceObservationReport(JsonObject json) throws Exception {
    DeviceObservationReport res = new DeviceObservationReport();
    parseResourceProperties(json, res);
    if (json.has("instant"))
      res.setInstant(parseInstant(json.get("instant").getAsString()));
    if (json.has("_instant"))
      parseElementProperties(json.getAsJsonObject("_instant"), res.getInstant());
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getAsJsonObject("source")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("virtualDevice")) {
      JsonArray array = json.getAsJsonArray("virtualDevice");
      for (int i = 0; i < array.size(); i++) {
        res.getVirtualDevice().add(parseDeviceObservationReportDeviceObservationReportVirtualDeviceComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent parseDeviceObservationReportDeviceObservationReportVirtualDeviceComponent(JsonObject json, DeviceObservationReport owner) throws Exception {
    DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent res = new DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("channel")) {
      JsonArray array = json.getAsJsonArray("channel");
      for (int i = 0; i < array.size(); i++) {
        res.getChannel().add(parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent(JsonObject json, DeviceObservationReport owner) throws Exception {
    DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent res = new DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("metric")) {
      JsonArray array = json.getAsJsonArray("metric");
      for (int i = 0; i < array.size(); i++) {
        res.getMetric().add(parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent(JsonObject json, DeviceObservationReport owner) throws Exception {
    DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent res = new DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent();
    parseBackboneProperties(json, res);
    if (json.has("observation"))
      res.setObservation(parseResourceReference(json.getAsJsonObject("observation")));
    return res;
  }

  private DiagnosticOrder parseDiagnosticOrder(JsonObject json) throws Exception {
    DiagnosticOrder res = new DiagnosticOrder();
    parseResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("orderer"))
      res.setOrderer(parseResourceReference(json.getAsJsonObject("orderer")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("clinicalNotes"))
      res.setClinicalNotes(parseString(json.get("clinicalNotes").getAsString()));
    if (json.has("_clinicalNotes"))
      parseElementProperties(json.getAsJsonObject("_clinicalNotes"), res.getClinicalNotes());
    if (json.has("supportingInformation")) {
      JsonArray array = json.getAsJsonArray("supportingInformation");
      for (int i = 0; i < array.size(); i++) {
        res.getSupportingInformation().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("specimen")) {
      JsonArray array = json.getAsJsonArray("specimen");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecimen().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("priority"))
      res.setPriority(parseEnumeration(json.get("priority").getAsString(), DiagnosticOrder.DiagnosticOrderPriority.Null, new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory()));
    if (json.has("_priority"))
      parseElementProperties(json.getAsJsonObject("_priority"), res.getPriority());
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("item")) {
      JsonArray array = json.getAsJsonArray("item");
      for (int i = 0; i < array.size(); i++) {
        res.getItem().add(parseDiagnosticOrderDiagnosticOrderItemComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private DiagnosticOrder.DiagnosticOrderEventComponent parseDiagnosticOrderDiagnosticOrderEventComponent(JsonObject json, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderEventComponent res = new DiagnosticOrder.DiagnosticOrderEventComponent();
    parseBackboneProperties(json, res);
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("description"))
      res.setDescription(parseCodeableConcept(json.getAsJsonObject("description")));
    if (json.has("dateTime"))
      res.setDateTime(parseDateTime(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTime());
    if (json.has("actor"))
      res.setActor(parseResourceReference(json.getAsJsonObject("actor")));
    return res;
  }

  private DiagnosticOrder.DiagnosticOrderItemComponent parseDiagnosticOrderDiagnosticOrderItemComponent(JsonObject json, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderItemComponent res = new DiagnosticOrder.DiagnosticOrderItemComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("specimen")) {
      JsonArray array = json.getAsJsonArray("specimen");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecimen().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("bodySite"))
      res.setBodySite(parseCodeableConcept(json.getAsJsonObject("bodySite")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private DiagnosticReport parseDiagnosticReport(JsonObject json) throws Exception {
    DiagnosticReport res = new DiagnosticReport();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getAsJsonObject("name")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), DiagnosticReport.DiagnosticReportStatus.Null, new DiagnosticReport.DiagnosticReportStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("issued"))
      res.setIssued(parseDateTime(json.get("issued").getAsString()));
    if (json.has("_issued"))
      parseElementProperties(json.getAsJsonObject("_issued"), res.getIssued());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("performer"))
      res.setPerformer(parseResourceReference(json.getAsJsonObject("performer")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("requestDetail")) {
      JsonArray array = json.getAsJsonArray("requestDetail");
      for (int i = 0; i < array.size(); i++) {
        res.getRequestDetail().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("serviceCategory"))
      res.setServiceCategory(parseCodeableConcept(json.getAsJsonObject("serviceCategory")));
    Type diagnostic = parseType("diagnostic", json);
    if (diagnostic != null)
      res.setDiagnostic(diagnostic);
    if (json.has("specimen")) {
      JsonArray array = json.getAsJsonArray("specimen");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecimen().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("result")) {
      JsonArray array = json.getAsJsonArray("result");
      for (int i = 0; i < array.size(); i++) {
        res.getResult().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("imagingStudy")) {
      JsonArray array = json.getAsJsonArray("imagingStudy");
      for (int i = 0; i < array.size(); i++) {
        res.getImagingStudy().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("image")) {
      JsonArray array = json.getAsJsonArray("image");
      for (int i = 0; i < array.size(); i++) {
        res.getImage().add(parseDiagnosticReportDiagnosticReportImageComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("conclusion"))
      res.setConclusion(parseString(json.get("conclusion").getAsString()));
    if (json.has("_conclusion"))
      parseElementProperties(json.getAsJsonObject("_conclusion"), res.getConclusion());
    if (json.has("codedDiagnosis")) {
      JsonArray array = json.getAsJsonArray("codedDiagnosis");
      for (int i = 0; i < array.size(); i++) {
        res.getCodedDiagnosis().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("presentedForm")) {
      JsonArray array = json.getAsJsonArray("presentedForm");
      for (int i = 0; i < array.size(); i++) {
        res.getPresentedForm().add(parseAttachment(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private DiagnosticReport.DiagnosticReportImageComponent parseDiagnosticReportDiagnosticReportImageComponent(JsonObject json, DiagnosticReport owner) throws Exception {
    DiagnosticReport.DiagnosticReportImageComponent res = new DiagnosticReport.DiagnosticReportImageComponent();
    parseBackboneProperties(json, res);
    if (json.has("comment"))
      res.setComment(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getComment());
    if (json.has("link"))
      res.setLink(parseResourceReference(json.getAsJsonObject("link")));
    return res;
  }

  private DocumentManifest parseDocumentManifest(JsonObject json) throws Exception {
    DocumentManifest res = new DocumentManifest();
    parseResourceProperties(json, res);
    if (json.has("masterIdentifier"))
      res.setMasterIdentifier(parseIdentifier(json.getAsJsonObject("masterIdentifier")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject")) {
      JsonArray array = json.getAsJsonArray("subject");
      for (int i = 0; i < array.size(); i++) {
        res.getSubject().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("recipient")) {
      JsonArray array = json.getAsJsonArray("recipient");
      for (int i = 0; i < array.size(); i++) {
        res.getRecipient().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("author")) {
      JsonArray array = json.getAsJsonArray("author");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthor().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("created"))
      res.setCreated(parseDateTime(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreated());
    if (json.has("source"))
      res.setSource(parseUri(json.get("source").getAsString()));
    if (json.has("_source"))
      parseElementProperties(json.getAsJsonObject("_source"), res.getSource());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), DocumentManifest.DocumentReferenceStatus.Null, new DocumentManifest.DocumentReferenceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("supercedes"))
      res.setSupercedes(parseResourceReference(json.getAsJsonObject("supercedes")));
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("confidentiality"))
      res.setConfidentiality(parseCodeableConcept(json.getAsJsonObject("confidentiality")));
    if (json.has("content")) {
      JsonArray array = json.getAsJsonArray("content");
      for (int i = 0; i < array.size(); i++) {
        res.getContent().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private DocumentReference parseDocumentReference(JsonObject json) throws Exception {
    DocumentReference res = new DocumentReference();
    parseResourceProperties(json, res);
    if (json.has("masterIdentifier"))
      res.setMasterIdentifier(parseIdentifier(json.getAsJsonObject("masterIdentifier")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("class"))
      res.setClass_(parseCodeableConcept(json.getAsJsonObject("class")));
    if (json.has("author")) {
      JsonArray array = json.getAsJsonArray("author");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthor().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("custodian"))
      res.setCustodian(parseResourceReference(json.getAsJsonObject("custodian")));
    if (json.has("policyManager"))
      res.setPolicyManager(parseUri(json.get("policyManager").getAsString()));
    if (json.has("_policyManager"))
      parseElementProperties(json.getAsJsonObject("_policyManager"), res.getPolicyManager());
    if (json.has("authenticator"))
      res.setAuthenticator(parseResourceReference(json.getAsJsonObject("authenticator")));
    if (json.has("created"))
      res.setCreated(parseDateTime(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreated());
    if (json.has("indexed"))
      res.setIndexed(parseInstant(json.get("indexed").getAsString()));
    if (json.has("_indexed"))
      parseElementProperties(json.getAsJsonObject("_indexed"), res.getIndexed());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), DocumentReference.DocumentReferenceStatus.Null, new DocumentReference.DocumentReferenceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("docStatus"))
      res.setDocStatus(parseCodeableConcept(json.getAsJsonObject("docStatus")));
    if (json.has("relatesTo")) {
      JsonArray array = json.getAsJsonArray("relatesTo");
      for (int i = 0; i < array.size(); i++) {
        res.getRelatesTo().add(parseDocumentReferenceDocumentReferenceRelatesToComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("confidentiality")) {
      JsonArray array = json.getAsJsonArray("confidentiality");
      for (int i = 0; i < array.size(); i++) {
        res.getConfidentiality().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("primaryLanguage"))
      res.setPrimaryLanguage(parseCode(json.get("primaryLanguage").getAsString()));
    if (json.has("_primaryLanguage"))
      parseElementProperties(json.getAsJsonObject("_primaryLanguage"), res.getPrimaryLanguage());
    if (json.has("mimeType"))
      res.setMimeType(parseCode(json.get("mimeType").getAsString()));
    if (json.has("_mimeType"))
      parseElementProperties(json.getAsJsonObject("_mimeType"), res.getMimeType());
    if (json.has("format")) {
      JsonArray array = json.getAsJsonArray("format");
      for (int i = 0; i < array.size(); i++) {
        res.getFormat().add(parseUri(array.get(i).getAsString()));
      }
    };
    if (json.has("_format")) {
      JsonArray array = json.getAsJsonArray("_format");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getFormat().size())
          res.getFormat().add(parseUri(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getFormat().get(i));
      }
    };
    if (json.has("size"))
      res.setSize(parseInteger(json.get("size").getAsLong()));
    if (json.has("_size"))
      parseElementProperties(json.getAsJsonObject("_size"), res.getSize());
    if (json.has("hash"))
      res.setHash(parseString(json.get("hash").getAsString()));
    if (json.has("_hash"))
      parseElementProperties(json.getAsJsonObject("_hash"), res.getHash());
    if (json.has("location"))
      res.setLocation(parseUri(json.get("location").getAsString()));
    if (json.has("_location"))
      parseElementProperties(json.getAsJsonObject("_location"), res.getLocation());
    if (json.has("service"))
      res.setService(parseDocumentReferenceDocumentReferenceServiceComponent(json.getAsJsonObject("service"), res));
    if (json.has("context"))
      res.setContext(parseDocumentReferenceDocumentReferenceContextComponent(json.getAsJsonObject("context"), res));
    return res;
  }

  private DocumentReference.DocumentReferenceRelatesToComponent parseDocumentReferenceDocumentReferenceRelatesToComponent(JsonObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceRelatesToComponent res = new DocumentReference.DocumentReferenceRelatesToComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseEnumeration(json.get("code").getAsString(), DocumentReference.DocumentRelationshipType.Null, new DocumentReference.DocumentRelationshipTypeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getAsJsonObject("target")));
    return res;
  }

  private DocumentReference.DocumentReferenceServiceComponent parseDocumentReferenceDocumentReferenceServiceComponent(JsonObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceServiceComponent res = new DocumentReference.DocumentReferenceServiceComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("address"))
      res.setAddress(parseString(json.get("address").getAsString()));
    if (json.has("_address"))
      parseElementProperties(json.getAsJsonObject("_address"), res.getAddress());
    if (json.has("parameter")) {
      JsonArray array = json.getAsJsonArray("parameter");
      for (int i = 0; i < array.size(); i++) {
        res.getParameter().add(parseDocumentReferenceDocumentReferenceServiceParameterComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private DocumentReference.DocumentReferenceServiceParameterComponent parseDocumentReferenceDocumentReferenceServiceParameterComponent(JsonObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceServiceParameterComponent res = new DocumentReference.DocumentReferenceServiceParameterComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("value"))
      res.setValue(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    return res;
  }

  private DocumentReference.DocumentReferenceContextComponent parseDocumentReferenceDocumentReferenceContextComponent(JsonObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceContextComponent res = new DocumentReference.DocumentReferenceContextComponent();
    parseBackboneProperties(json, res);
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("facilityType"))
      res.setFacilityType(parseCodeableConcept(json.getAsJsonObject("facilityType")));
    return res;
  }

  private Encounter parseEncounter(JsonObject json) throws Exception {
    Encounter res = new Encounter();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Encounter.EncounterState.Null, new Encounter.EncounterStateEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("class"))
      res.setClass_(parseEnumeration(json.get("class").getAsString(), Encounter.EncounterClass.Null, new Encounter.EncounterClassEnumFactory()));
    if (json.has("_class"))
      parseElementProperties(json.getAsJsonObject("_class"), res.getClass_());
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("participant")) {
      JsonArray array = json.getAsJsonArray("participant");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipant().add(parseEncounterEncounterParticipantComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("fulfills"))
      res.setFulfills(parseResourceReference(json.getAsJsonObject("fulfills")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("length"))
      res.setLength(parseDuration(json.getAsJsonObject("length")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("indication"))
      res.setIndication(parseResourceReference(json.getAsJsonObject("indication")));
    if (json.has("priority"))
      res.setPriority(parseCodeableConcept(json.getAsJsonObject("priority")));
    if (json.has("hospitalization"))
      res.setHospitalization(parseEncounterEncounterHospitalizationComponent(json.getAsJsonObject("hospitalization"), res));
    if (json.has("location")) {
      JsonArray array = json.getAsJsonArray("location");
      for (int i = 0; i < array.size(); i++) {
        res.getLocation().add(parseEncounterEncounterLocationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("serviceProvider"))
      res.setServiceProvider(parseResourceReference(json.getAsJsonObject("serviceProvider")));
    if (json.has("partOf"))
      res.setPartOf(parseResourceReference(json.getAsJsonObject("partOf")));
    return res;
  }

  private Encounter.EncounterParticipantComponent parseEncounterEncounterParticipantComponent(JsonObject json, Encounter owner) throws Exception {
    Encounter.EncounterParticipantComponent res = new Encounter.EncounterParticipantComponent();
    parseBackboneProperties(json, res);
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("individual"))
      res.setIndividual(parseResourceReference(json.getAsJsonObject("individual")));
    return res;
  }

  private Encounter.EncounterHospitalizationComponent parseEncounterEncounterHospitalizationComponent(JsonObject json, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationComponent res = new Encounter.EncounterHospitalizationComponent();
    parseBackboneProperties(json, res);
    if (json.has("preAdmissionIdentifier"))
      res.setPreAdmissionIdentifier(parseIdentifier(json.getAsJsonObject("preAdmissionIdentifier")));
    if (json.has("origin"))
      res.setOrigin(parseResourceReference(json.getAsJsonObject("origin")));
    if (json.has("admitSource"))
      res.setAdmitSource(parseCodeableConcept(json.getAsJsonObject("admitSource")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("accomodation")) {
      JsonArray array = json.getAsJsonArray("accomodation");
      for (int i = 0; i < array.size(); i++) {
        res.getAccomodation().add(parseEncounterEncounterHospitalizationAccomodationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("diet"))
      res.setDiet(parseCodeableConcept(json.getAsJsonObject("diet")));
    if (json.has("specialCourtesy")) {
      JsonArray array = json.getAsJsonArray("specialCourtesy");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecialCourtesy().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("specialArrangement")) {
      JsonArray array = json.getAsJsonArray("specialArrangement");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecialArrangement().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("destination"))
      res.setDestination(parseResourceReference(json.getAsJsonObject("destination")));
    if (json.has("dischargeDisposition"))
      res.setDischargeDisposition(parseCodeableConcept(json.getAsJsonObject("dischargeDisposition")));
    if (json.has("dischargeDiagnosis"))
      res.setDischargeDiagnosis(parseResourceReference(json.getAsJsonObject("dischargeDiagnosis")));
    if (json.has("reAdmission"))
      res.setReAdmission(parseBoolean(json.get("reAdmission").getAsBoolean()));
    if (json.has("_reAdmission"))
      parseElementProperties(json.getAsJsonObject("_reAdmission"), res.getReAdmission());
    return res;
  }

  private Encounter.EncounterHospitalizationAccomodationComponent parseEncounterEncounterHospitalizationAccomodationComponent(JsonObject json, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationAccomodationComponent res = new Encounter.EncounterHospitalizationAccomodationComponent();
    parseBackboneProperties(json, res);
    if (json.has("bed"))
      res.setBed(parseResourceReference(json.getAsJsonObject("bed")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private Encounter.EncounterLocationComponent parseEncounterEncounterLocationComponent(JsonObject json, Encounter owner) throws Exception {
    Encounter.EncounterLocationComponent res = new Encounter.EncounterLocationComponent();
    parseBackboneProperties(json, res);
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getAsJsonObject("location")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private FamilyHistory parseFamilyHistory(JsonObject json) throws Exception {
    FamilyHistory res = new FamilyHistory();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("note"))
      res.setNote(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNote());
    if (json.has("relation")) {
      JsonArray array = json.getAsJsonArray("relation");
      for (int i = 0; i < array.size(); i++) {
        res.getRelation().add(parseFamilyHistoryFamilyHistoryRelationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private FamilyHistory.FamilyHistoryRelationComponent parseFamilyHistoryFamilyHistoryRelationComponent(JsonObject json, FamilyHistory owner) throws Exception {
    FamilyHistory.FamilyHistoryRelationComponent res = new FamilyHistory.FamilyHistoryRelationComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("relationship"))
      res.setRelationship(parseCodeableConcept(json.getAsJsonObject("relationship")));
    Type born = parseType("born", json);
    if (born != null)
      res.setBorn(born);
    Type age = parseType("age", json);
    if (age != null)
      res.setAge(age);
    Type deceased = parseType("deceased", json);
    if (deceased != null)
      res.setDeceased(deceased);
    if (json.has("note"))
      res.setNote(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNote());
    if (json.has("condition")) {
      JsonArray array = json.getAsJsonArray("condition");
      for (int i = 0; i < array.size(); i++) {
        res.getCondition().add(parseFamilyHistoryFamilyHistoryRelationConditionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private FamilyHistory.FamilyHistoryRelationConditionComponent parseFamilyHistoryFamilyHistoryRelationConditionComponent(JsonObject json, FamilyHistory owner) throws Exception {
    FamilyHistory.FamilyHistoryRelationConditionComponent res = new FamilyHistory.FamilyHistoryRelationConditionComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("outcome"))
      res.setOutcome(parseCodeableConcept(json.getAsJsonObject("outcome")));
    Type onset = parseType("onset", json);
    if (onset != null)
      res.setOnset(onset);
    if (json.has("note"))
      res.setNote(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNote());
    return res;
  }

  private Group parseGroup(JsonObject json) throws Exception {
    Group res = new Group();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Group.GroupType.Null, new Group.GroupTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("actual"))
      res.setActual(parseBoolean(json.get("actual").getAsBoolean()));
    if (json.has("_actual"))
      parseElementProperties(json.getAsJsonObject("_actual"), res.getActual());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("quantity"))
      res.setQuantity(parseInteger(json.get("quantity").getAsLong()));
    if (json.has("_quantity"))
      parseElementProperties(json.getAsJsonObject("_quantity"), res.getQuantity());
    if (json.has("characteristic")) {
      JsonArray array = json.getAsJsonArray("characteristic");
      for (int i = 0; i < array.size(); i++) {
        res.getCharacteristic().add(parseGroupGroupCharacteristicComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("member")) {
      JsonArray array = json.getAsJsonArray("member");
      for (int i = 0; i < array.size(); i++) {
        res.getMember().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Group.GroupCharacteristicComponent parseGroupGroupCharacteristicComponent(JsonObject json, Group owner) throws Exception {
    Group.GroupCharacteristicComponent res = new Group.GroupCharacteristicComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    if (json.has("exclude"))
      res.setExclude(parseBoolean(json.get("exclude").getAsBoolean()));
    if (json.has("_exclude"))
      parseElementProperties(json.getAsJsonObject("_exclude"), res.getExclude());
    return res;
  }

  private ImagingStudy parseImagingStudy(JsonObject json) throws Exception {
    ImagingStudy res = new ImagingStudy();
    parseResourceProperties(json, res);
    if (json.has("dateTime"))
      res.setDateTime(parseDateTime(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTime());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("uid"))
      res.setUid(parseOid(json.get("uid").getAsString()));
    if (json.has("_uid"))
      parseElementProperties(json.getAsJsonObject("_uid"), res.getUid());
    if (json.has("accessionNo"))
      res.setAccessionNo(parseIdentifier(json.getAsJsonObject("accessionNo")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("order")) {
      JsonArray array = json.getAsJsonArray("order");
      for (int i = 0; i < array.size(); i++) {
        res.getOrder().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("modality")) {
      JsonArray array = json.getAsJsonArray("modality");
      for (int i = 0; i < array.size(); i++) {
        res.getModality().add(parseEnumeration(array.get(i).getAsString(), ImagingStudy.ImagingModality.Null, new ImagingStudy.ImagingModalityEnumFactory()));
      }
    };
    if (json.has("_modality")) {
      JsonArray array = json.getAsJsonArray("_modality");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getModality().size())
          res.getModality().add(parseEnumeration(null, ImagingStudy.ImagingModality.Null, new ImagingStudy.ImagingModalityEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getModality().get(i));
      }
    };
    if (json.has("referrer"))
      res.setReferrer(parseResourceReference(json.getAsJsonObject("referrer")));
    if (json.has("availability"))
      res.setAvailability(parseEnumeration(json.get("availability").getAsString(), ImagingStudy.InstanceAvailability.Null, new ImagingStudy.InstanceAvailabilityEnumFactory()));
    if (json.has("_availability"))
      parseElementProperties(json.getAsJsonObject("_availability"), res.getAvailability());
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    if (json.has("numberOfSeries"))
      res.setNumberOfSeries(parseInteger(json.get("numberOfSeries").getAsLong()));
    if (json.has("_numberOfSeries"))
      parseElementProperties(json.getAsJsonObject("_numberOfSeries"), res.getNumberOfSeries());
    if (json.has("numberOfInstances"))
      res.setNumberOfInstances(parseInteger(json.get("numberOfInstances").getAsLong()));
    if (json.has("_numberOfInstances"))
      parseElementProperties(json.getAsJsonObject("_numberOfInstances"), res.getNumberOfInstances());
    if (json.has("clinicalInformation"))
      res.setClinicalInformation(parseString(json.get("clinicalInformation").getAsString()));
    if (json.has("_clinicalInformation"))
      parseElementProperties(json.getAsJsonObject("_clinicalInformation"), res.getClinicalInformation());
    if (json.has("procedure")) {
      JsonArray array = json.getAsJsonArray("procedure");
      for (int i = 0; i < array.size(); i++) {
        res.getProcedure().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("interpreter"))
      res.setInterpreter(parseResourceReference(json.getAsJsonObject("interpreter")));
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("series")) {
      JsonArray array = json.getAsJsonArray("series");
      for (int i = 0; i < array.size(); i++) {
        res.getSeries().add(parseImagingStudyImagingStudySeriesComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private ImagingStudy.ImagingStudySeriesComponent parseImagingStudyImagingStudySeriesComponent(JsonObject json, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesComponent res = new ImagingStudy.ImagingStudySeriesComponent();
    parseBackboneProperties(json, res);
    if (json.has("number"))
      res.setNumber(parseInteger(json.get("number").getAsLong()));
    if (json.has("_number"))
      parseElementProperties(json.getAsJsonObject("_number"), res.getNumber());
    if (json.has("modality"))
      res.setModality(parseEnumeration(json.get("modality").getAsString(), ImagingStudy.Modality.Null, new ImagingStudy.ModalityEnumFactory()));
    if (json.has("_modality"))
      parseElementProperties(json.getAsJsonObject("_modality"), res.getModality());
    if (json.has("uid"))
      res.setUid(parseOid(json.get("uid").getAsString()));
    if (json.has("_uid"))
      parseElementProperties(json.getAsJsonObject("_uid"), res.getUid());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("numberOfInstances"))
      res.setNumberOfInstances(parseInteger(json.get("numberOfInstances").getAsLong()));
    if (json.has("_numberOfInstances"))
      parseElementProperties(json.getAsJsonObject("_numberOfInstances"), res.getNumberOfInstances());
    if (json.has("availability"))
      res.setAvailability(parseEnumeration(json.get("availability").getAsString(), ImagingStudy.InstanceAvailability.Null, new ImagingStudy.InstanceAvailabilityEnumFactory()));
    if (json.has("_availability"))
      parseElementProperties(json.getAsJsonObject("_availability"), res.getAvailability());
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    if (json.has("bodySite"))
      res.setBodySite(parseCoding(json.getAsJsonObject("bodySite")));
    if (json.has("dateTime"))
      res.setDateTime(parseDateTime(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTime());
    if (json.has("instance")) {
      JsonArray array = json.getAsJsonArray("instance");
      for (int i = 0; i < array.size(); i++) {
        res.getInstance().add(parseImagingStudyImagingStudySeriesInstanceComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ImagingStudy.ImagingStudySeriesInstanceComponent parseImagingStudyImagingStudySeriesInstanceComponent(JsonObject json, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesInstanceComponent res = new ImagingStudy.ImagingStudySeriesInstanceComponent();
    parseBackboneProperties(json, res);
    if (json.has("number"))
      res.setNumber(parseInteger(json.get("number").getAsLong()));
    if (json.has("_number"))
      parseElementProperties(json.getAsJsonObject("_number"), res.getNumber());
    if (json.has("uid"))
      res.setUid(parseOid(json.get("uid").getAsString()));
    if (json.has("_uid"))
      parseElementProperties(json.getAsJsonObject("_uid"), res.getUid());
    if (json.has("sopclass"))
      res.setSopclass(parseOid(json.get("sopclass").getAsString()));
    if (json.has("_sopclass"))
      parseElementProperties(json.getAsJsonObject("_sopclass"), res.getSopclass());
    if (json.has("type"))
      res.setType(parseString(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("title"))
      res.setTitle(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitle());
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    if (json.has("attachment"))
      res.setAttachment(parseResourceReference(json.getAsJsonObject("attachment")));
    return res;
  }

  private Immunization parseImmunization(JsonObject json) throws Exception {
    Immunization res = new Immunization();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("vaccineType"))
      res.setVaccineType(parseCodeableConcept(json.getAsJsonObject("vaccineType")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("refusedIndicator"))
      res.setRefusedIndicator(parseBoolean(json.get("refusedIndicator").getAsBoolean()));
    if (json.has("_refusedIndicator"))
      parseElementProperties(json.getAsJsonObject("_refusedIndicator"), res.getRefusedIndicator());
    if (json.has("reported"))
      res.setReported(parseBoolean(json.get("reported").getAsBoolean()));
    if (json.has("_reported"))
      parseElementProperties(json.getAsJsonObject("_reported"), res.getReported());
    if (json.has("performer"))
      res.setPerformer(parseResourceReference(json.getAsJsonObject("performer")));
    if (json.has("requester"))
      res.setRequester(parseResourceReference(json.getAsJsonObject("requester")));
    if (json.has("manufacturer"))
      res.setManufacturer(parseResourceReference(json.getAsJsonObject("manufacturer")));
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getAsJsonObject("location")));
    if (json.has("lotNumber"))
      res.setLotNumber(parseString(json.get("lotNumber").getAsString()));
    if (json.has("_lotNumber"))
      parseElementProperties(json.getAsJsonObject("_lotNumber"), res.getLotNumber());
    if (json.has("expirationDate"))
      res.setExpirationDate(parseDate(json.get("expirationDate").getAsString()));
    if (json.has("_expirationDate"))
      parseElementProperties(json.getAsJsonObject("_expirationDate"), res.getExpirationDate());
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getAsJsonObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getAsJsonObject("route")));
    if (json.has("doseQuantity"))
      res.setDoseQuantity(parseQuantity(json.getAsJsonObject("doseQuantity")));
    if (json.has("explanation"))
      res.setExplanation(parseImmunizationImmunizationExplanationComponent(json.getAsJsonObject("explanation"), res));
    if (json.has("reaction")) {
      JsonArray array = json.getAsJsonArray("reaction");
      for (int i = 0; i < array.size(); i++) {
        res.getReaction().add(parseImmunizationImmunizationReactionComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("vaccinationProtocol")) {
      JsonArray array = json.getAsJsonArray("vaccinationProtocol");
      for (int i = 0; i < array.size(); i++) {
        res.getVaccinationProtocol().add(parseImmunizationImmunizationVaccinationProtocolComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Immunization.ImmunizationExplanationComponent parseImmunizationImmunizationExplanationComponent(JsonObject json, Immunization owner) throws Exception {
    Immunization.ImmunizationExplanationComponent res = new Immunization.ImmunizationExplanationComponent();
    parseBackboneProperties(json, res);
    if (json.has("reason")) {
      JsonArray array = json.getAsJsonArray("reason");
      for (int i = 0; i < array.size(); i++) {
        res.getReason().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("refusalReason")) {
      JsonArray array = json.getAsJsonArray("refusalReason");
      for (int i = 0; i < array.size(); i++) {
        res.getRefusalReason().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Immunization.ImmunizationReactionComponent parseImmunizationImmunizationReactionComponent(JsonObject json, Immunization owner) throws Exception {
    Immunization.ImmunizationReactionComponent res = new Immunization.ImmunizationReactionComponent();
    parseBackboneProperties(json, res);
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("detail"))
      res.setDetail(parseResourceReference(json.getAsJsonObject("detail")));
    if (json.has("reported"))
      res.setReported(parseBoolean(json.get("reported").getAsBoolean()));
    if (json.has("_reported"))
      parseElementProperties(json.getAsJsonObject("_reported"), res.getReported());
    return res;
  }

  private Immunization.ImmunizationVaccinationProtocolComponent parseImmunizationImmunizationVaccinationProtocolComponent(JsonObject json, Immunization owner) throws Exception {
    Immunization.ImmunizationVaccinationProtocolComponent res = new Immunization.ImmunizationVaccinationProtocolComponent();
    parseBackboneProperties(json, res);
    if (json.has("doseSequence"))
      res.setDoseSequence(parseInteger(json.get("doseSequence").getAsLong()));
    if (json.has("_doseSequence"))
      parseElementProperties(json.getAsJsonObject("_doseSequence"), res.getDoseSequence());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("authority"))
      res.setAuthority(parseResourceReference(json.getAsJsonObject("authority")));
    if (json.has("series"))
      res.setSeries(parseString(json.get("series").getAsString()));
    if (json.has("_series"))
      parseElementProperties(json.getAsJsonObject("_series"), res.getSeries());
    if (json.has("seriesDoses"))
      res.setSeriesDoses(parseInteger(json.get("seriesDoses").getAsLong()));
    if (json.has("_seriesDoses"))
      parseElementProperties(json.getAsJsonObject("_seriesDoses"), res.getSeriesDoses());
    if (json.has("doseTarget"))
      res.setDoseTarget(parseCodeableConcept(json.getAsJsonObject("doseTarget")));
    if (json.has("doseStatus"))
      res.setDoseStatus(parseCodeableConcept(json.getAsJsonObject("doseStatus")));
    if (json.has("doseStatusReason"))
      res.setDoseStatusReason(parseCodeableConcept(json.getAsJsonObject("doseStatusReason")));
    return res;
  }

  private ImmunizationRecommendation parseImmunizationRecommendation(JsonObject json) throws Exception {
    ImmunizationRecommendation res = new ImmunizationRecommendation();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("recommendation")) {
      JsonArray array = json.getAsJsonArray("recommendation");
      for (int i = 0; i < array.size(); i++) {
        res.getRecommendation().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(JsonObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent();
    parseBackboneProperties(json, res);
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("vaccineType"))
      res.setVaccineType(parseCodeableConcept(json.getAsJsonObject("vaccineType")));
    if (json.has("doseNumber"))
      res.setDoseNumber(parseInteger(json.get("doseNumber").getAsLong()));
    if (json.has("_doseNumber"))
      parseElementProperties(json.getAsJsonObject("_doseNumber"), res.getDoseNumber());
    if (json.has("forecastStatus"))
      res.setForecastStatus(parseCodeableConcept(json.getAsJsonObject("forecastStatus")));
    if (json.has("dateCriterion")) {
      JsonArray array = json.getAsJsonArray("dateCriterion");
      for (int i = 0; i < array.size(); i++) {
        res.getDateCriterion().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("protocol"))
      res.setProtocol(parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(json.getAsJsonObject("protocol"), owner));
    if (json.has("supportingImmunization")) {
      JsonArray array = json.getAsJsonArray("supportingImmunization");
      for (int i = 0; i < array.size(); i++) {
        res.getSupportingImmunization().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("supportingPatientInformation")) {
      JsonArray array = json.getAsJsonArray("supportingPatientInformation");
      for (int i = 0; i < array.size(); i++) {
        res.getSupportingPatientInformation().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(JsonObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("value"))
      res.setValue(parseDateTime(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(JsonObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent();
    parseBackboneProperties(json, res);
    if (json.has("doseSequence"))
      res.setDoseSequence(parseInteger(json.get("doseSequence").getAsLong()));
    if (json.has("_doseSequence"))
      parseElementProperties(json.getAsJsonObject("_doseSequence"), res.getDoseSequence());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("authority"))
      res.setAuthority(parseResourceReference(json.getAsJsonObject("authority")));
    if (json.has("series"))
      res.setSeries(parseString(json.get("series").getAsString()));
    if (json.has("_series"))
      parseElementProperties(json.getAsJsonObject("_series"), res.getSeries());
    return res;
  }

  private List_ parseList_(JsonObject json) throws Exception {
    List_ res = new List_();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getAsJsonObject("source")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("ordered"))
      res.setOrdered(parseBoolean(json.get("ordered").getAsBoolean()));
    if (json.has("_ordered"))
      parseElementProperties(json.getAsJsonObject("_ordered"), res.getOrdered());
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.get("mode").getAsString(), List_.ListMode.Null, new List_.ListModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getMode());
    if (json.has("entry")) {
      JsonArray array = json.getAsJsonArray("entry");
      for (int i = 0; i < array.size(); i++) {
        res.getEntry().add(parseList_ListEntryComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("emptyReason"))
      res.setEmptyReason(parseCodeableConcept(json.getAsJsonObject("emptyReason")));
    return res;
  }

  private List_.ListEntryComponent parseList_ListEntryComponent(JsonObject json, List_ owner) throws Exception {
    List_.ListEntryComponent res = new List_.ListEntryComponent();
    parseBackboneProperties(json, res);
    if (json.has("flag")) {
      JsonArray array = json.getAsJsonArray("flag");
      for (int i = 0; i < array.size(); i++) {
        res.getFlag().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("deleted"))
      res.setDeleted(parseBoolean(json.get("deleted").getAsBoolean()));
    if (json.has("_deleted"))
      parseElementProperties(json.getAsJsonObject("_deleted"), res.getDeleted());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("item"))
      res.setItem(parseResourceReference(json.getAsJsonObject("item")));
    return res;
  }

  private Location parseLocation(JsonObject json) throws Exception {
    Location res = new Location();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getAsJsonObject("address")));
    if (json.has("physicalType"))
      res.setPhysicalType(parseCodeableConcept(json.getAsJsonObject("physicalType")));
    if (json.has("position"))
      res.setPosition(parseLocationLocationPositionComponent(json.getAsJsonObject("position"), res));
    if (json.has("managingOrganization"))
      res.setManagingOrganization(parseResourceReference(json.getAsJsonObject("managingOrganization")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Location.LocationStatus.Null, new Location.LocationStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("partOf"))
      res.setPartOf(parseResourceReference(json.getAsJsonObject("partOf")));
    if (json.has("mode"))
      res.setMode(parseEnumeration(json.get("mode").getAsString(), Location.LocationMode.Null, new Location.LocationModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getMode());
    return res;
  }

  private Location.LocationPositionComponent parseLocationLocationPositionComponent(JsonObject json, Location owner) throws Exception {
    Location.LocationPositionComponent res = new Location.LocationPositionComponent();
    parseBackboneProperties(json, res);
    if (json.has("longitude"))
      res.setLongitude(parseDecimal(json.get("longitude").getAsBigDecimal()));
    if (json.has("_longitude"))
      parseElementProperties(json.getAsJsonObject("_longitude"), res.getLongitude());
    if (json.has("latitude"))
      res.setLatitude(parseDecimal(json.get("latitude").getAsBigDecimal()));
    if (json.has("_latitude"))
      parseElementProperties(json.getAsJsonObject("_latitude"), res.getLatitude());
    if (json.has("altitude"))
      res.setAltitude(parseDecimal(json.get("altitude").getAsBigDecimal()));
    if (json.has("_altitude"))
      parseElementProperties(json.getAsJsonObject("_altitude"), res.getAltitude());
    return res;
  }

  private Media parseMedia(JsonObject json) throws Exception {
    Media res = new Media();
    parseResourceProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Media.MediaType.Null, new Media.MediaTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("subtype"))
      res.setSubtype(parseCodeableConcept(json.getAsJsonObject("subtype")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("dateTime"))
      res.setDateTime(parseDateTime(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTime());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("operator"))
      res.setOperator(parseResourceReference(json.getAsJsonObject("operator")));
    if (json.has("view"))
      res.setView(parseCodeableConcept(json.getAsJsonObject("view")));
    if (json.has("deviceName"))
      res.setDeviceName(parseString(json.get("deviceName").getAsString()));
    if (json.has("_deviceName"))
      parseElementProperties(json.getAsJsonObject("_deviceName"), res.getDeviceName());
    if (json.has("height"))
      res.setHeight(parseInteger(json.get("height").getAsLong()));
    if (json.has("_height"))
      parseElementProperties(json.getAsJsonObject("_height"), res.getHeight());
    if (json.has("width"))
      res.setWidth(parseInteger(json.get("width").getAsLong()));
    if (json.has("_width"))
      parseElementProperties(json.getAsJsonObject("_width"), res.getWidth());
    if (json.has("frames"))
      res.setFrames(parseInteger(json.get("frames").getAsLong()));
    if (json.has("_frames"))
      parseElementProperties(json.getAsJsonObject("_frames"), res.getFrames());
    if (json.has("length"))
      res.setLength(parseInteger(json.get("length").getAsLong()));
    if (json.has("_length"))
      parseElementProperties(json.getAsJsonObject("_length"), res.getLength());
    if (json.has("content"))
      res.setContent(parseAttachment(json.getAsJsonObject("content")));
    return res;
  }

  private Medication parseMedication(JsonObject json) throws Exception {
    Medication res = new Medication();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("isBrand"))
      res.setIsBrand(parseBoolean(json.get("isBrand").getAsBoolean()));
    if (json.has("_isBrand"))
      parseElementProperties(json.getAsJsonObject("_isBrand"), res.getIsBrand());
    if (json.has("manufacturer"))
      res.setManufacturer(parseResourceReference(json.getAsJsonObject("manufacturer")));
    if (json.has("kind"))
      res.setKind(parseEnumeration(json.get("kind").getAsString(), Medication.MedicationKind.Null, new Medication.MedicationKindEnumFactory()));
    if (json.has("_kind"))
      parseElementProperties(json.getAsJsonObject("_kind"), res.getKind());
    if (json.has("product"))
      res.setProduct(parseMedicationMedicationProductComponent(json.getAsJsonObject("product"), res));
    if (json.has("package"))
      res.setPackage(parseMedicationMedicationPackageComponent(json.getAsJsonObject("package"), res));
    return res;
  }

  private Medication.MedicationProductComponent parseMedicationMedicationProductComponent(JsonObject json, Medication owner) throws Exception {
    Medication.MedicationProductComponent res = new Medication.MedicationProductComponent();
    parseBackboneProperties(json, res);
    if (json.has("form"))
      res.setForm(parseCodeableConcept(json.getAsJsonObject("form")));
    if (json.has("ingredient")) {
      JsonArray array = json.getAsJsonArray("ingredient");
      for (int i = 0; i < array.size(); i++) {
        res.getIngredient().add(parseMedicationMedicationProductIngredientComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Medication.MedicationProductIngredientComponent parseMedicationMedicationProductIngredientComponent(JsonObject json, Medication owner) throws Exception {
    Medication.MedicationProductIngredientComponent res = new Medication.MedicationProductIngredientComponent();
    parseBackboneProperties(json, res);
    if (json.has("item"))
      res.setItem(parseResourceReference(json.getAsJsonObject("item")));
    if (json.has("amount"))
      res.setAmount(parseRatio(json.getAsJsonObject("amount")));
    return res;
  }

  private Medication.MedicationPackageComponent parseMedicationMedicationPackageComponent(JsonObject json, Medication owner) throws Exception {
    Medication.MedicationPackageComponent res = new Medication.MedicationPackageComponent();
    parseBackboneProperties(json, res);
    if (json.has("container"))
      res.setContainer(parseCodeableConcept(json.getAsJsonObject("container")));
    if (json.has("content")) {
      JsonArray array = json.getAsJsonArray("content");
      for (int i = 0; i < array.size(); i++) {
        res.getContent().add(parseMedicationMedicationPackageContentComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Medication.MedicationPackageContentComponent parseMedicationMedicationPackageContentComponent(JsonObject json, Medication owner) throws Exception {
    Medication.MedicationPackageContentComponent res = new Medication.MedicationPackageContentComponent();
    parseBackboneProperties(json, res);
    if (json.has("item"))
      res.setItem(parseResourceReference(json.getAsJsonObject("item")));
    if (json.has("amount"))
      res.setAmount(parseQuantity(json.getAsJsonObject("amount")));
    return res;
  }

  private MedicationAdministration parseMedicationAdministration(JsonObject json) throws Exception {
    MedicationAdministration res = new MedicationAdministration();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), MedicationAdministration.MedicationAdminStatus.Null, new MedicationAdministration.MedicationAdminStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("practitioner"))
      res.setPractitioner(parseResourceReference(json.getAsJsonObject("practitioner")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("prescription"))
      res.setPrescription(parseResourceReference(json.getAsJsonObject("prescription")));
    if (json.has("wasNotGiven"))
      res.setWasNotGiven(parseBoolean(json.get("wasNotGiven").getAsBoolean()));
    if (json.has("_wasNotGiven"))
      parseElementProperties(json.getAsJsonObject("_wasNotGiven"), res.getWasNotGiven());
    if (json.has("reasonNotGiven")) {
      JsonArray array = json.getAsJsonArray("reasonNotGiven");
      for (int i = 0; i < array.size(); i++) {
        res.getReasonNotGiven().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("whenGiven"))
      res.setWhenGiven(parsePeriod(json.getAsJsonObject("whenGiven")));
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getAsJsonObject("medication")));
    if (json.has("device")) {
      JsonArray array = json.getAsJsonArray("device");
      for (int i = 0; i < array.size(); i++) {
        res.getDevice().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("dosage")) {
      JsonArray array = json.getAsJsonArray("dosage");
      for (int i = 0; i < array.size(); i++) {
        res.getDosage().add(parseMedicationAdministrationMedicationAdministrationDosageComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private MedicationAdministration.MedicationAdministrationDosageComponent parseMedicationAdministrationMedicationAdministrationDosageComponent(JsonObject json, MedicationAdministration owner) throws Exception {
    MedicationAdministration.MedicationAdministrationDosageComponent res = new MedicationAdministration.MedicationAdministrationDosageComponent();
    parseBackboneProperties(json, res);
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    Type asNeeded = parseType("asNeeded", json);
    if (asNeeded != null)
      res.setAsNeeded(asNeeded);
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getAsJsonObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getAsJsonObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getAsJsonObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getAsJsonObject("maxDosePerPeriod")));
    return res;
  }

  private MedicationDispense parseMedicationDispense(JsonObject json) throws Exception {
    MedicationDispense res = new MedicationDispense();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), MedicationDispense.MedicationDispenseStatus.Null, new MedicationDispense.MedicationDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("dispenser"))
      res.setDispenser(parseResourceReference(json.getAsJsonObject("dispenser")));
    if (json.has("authorizingPrescription")) {
      JsonArray array = json.getAsJsonArray("authorizingPrescription");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthorizingPrescription().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("dispense")) {
      JsonArray array = json.getAsJsonArray("dispense");
      for (int i = 0; i < array.size(); i++) {
        res.getDispense().add(parseMedicationDispenseMedicationDispenseDispenseComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("substitution"))
      res.setSubstitution(parseMedicationDispenseMedicationDispenseSubstitutionComponent(json.getAsJsonObject("substitution"), res));
    return res;
  }

  private MedicationDispense.MedicationDispenseDispenseComponent parseMedicationDispenseMedicationDispenseDispenseComponent(JsonObject json, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseDispenseComponent res = new MedicationDispense.MedicationDispenseDispenseComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), MedicationDispense.MedicationDispenseStatus.Null, new MedicationDispense.MedicationDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getAsJsonObject("medication")));
    if (json.has("whenPrepared"))
      res.setWhenPrepared(parseDateTime(json.get("whenPrepared").getAsString()));
    if (json.has("_whenPrepared"))
      parseElementProperties(json.getAsJsonObject("_whenPrepared"), res.getWhenPrepared());
    if (json.has("whenHandedOver"))
      res.setWhenHandedOver(parseDateTime(json.get("whenHandedOver").getAsString()));
    if (json.has("_whenHandedOver"))
      parseElementProperties(json.getAsJsonObject("_whenHandedOver"), res.getWhenHandedOver());
    if (json.has("destination"))
      res.setDestination(parseResourceReference(json.getAsJsonObject("destination")));
    if (json.has("receiver")) {
      JsonArray array = json.getAsJsonArray("receiver");
      for (int i = 0; i < array.size(); i++) {
        res.getReceiver().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("dosage")) {
      JsonArray array = json.getAsJsonArray("dosage");
      for (int i = 0; i < array.size(); i++) {
        res.getDosage().add(parseMedicationDispenseMedicationDispenseDispenseDosageComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private MedicationDispense.MedicationDispenseDispenseDosageComponent parseMedicationDispenseMedicationDispenseDispenseDosageComponent(JsonObject json, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseDispenseDosageComponent res = new MedicationDispense.MedicationDispenseDispenseDosageComponent();
    parseBackboneProperties(json, res);
    if (json.has("additionalInstructions"))
      res.setAdditionalInstructions(parseCodeableConcept(json.getAsJsonObject("additionalInstructions")));
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    Type asNeeded = parseType("asNeeded", json);
    if (asNeeded != null)
      res.setAsNeeded(asNeeded);
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getAsJsonObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getAsJsonObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getAsJsonObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getAsJsonObject("maxDosePerPeriod")));
    return res;
  }

  private MedicationDispense.MedicationDispenseSubstitutionComponent parseMedicationDispenseMedicationDispenseSubstitutionComponent(JsonObject json, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseSubstitutionComponent res = new MedicationDispense.MedicationDispenseSubstitutionComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("reason")) {
      JsonArray array = json.getAsJsonArray("reason");
      for (int i = 0; i < array.size(); i++) {
        res.getReason().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("responsibleParty")) {
      JsonArray array = json.getAsJsonArray("responsibleParty");
      for (int i = 0; i < array.size(); i++) {
        res.getResponsibleParty().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private MedicationPrescription parseMedicationPrescription(JsonObject json) throws Exception {
    MedicationPrescription res = new MedicationPrescription();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("dateWritten"))
      res.setDateWritten(parseDateTime(json.get("dateWritten").getAsString()));
    if (json.has("_dateWritten"))
      parseElementProperties(json.getAsJsonObject("_dateWritten"), res.getDateWritten());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), MedicationPrescription.MedicationPrescriptionStatus.Null, new MedicationPrescription.MedicationPrescriptionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("prescriber"))
      res.setPrescriber(parseResourceReference(json.getAsJsonObject("prescriber")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    Type reason = parseType("reason", json);
    if (reason != null)
      res.setReason(reason);
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getAsJsonObject("medication")));
    if (json.has("dosageInstruction")) {
      JsonArray array = json.getAsJsonArray("dosageInstruction");
      for (int i = 0; i < array.size(); i++) {
        res.getDosageInstruction().add(parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("dispense"))
      res.setDispense(parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(json.getAsJsonObject("dispense"), res));
    if (json.has("substitution"))
      res.setSubstitution(parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(json.getAsJsonObject("substitution"), res));
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionDosageInstructionComponent parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(JsonObject json, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDosageInstructionComponent res = new MedicationPrescription.MedicationPrescriptionDosageInstructionComponent();
    parseBackboneProperties(json, res);
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    if (json.has("additionalInstructions"))
      res.setAdditionalInstructions(parseCodeableConcept(json.getAsJsonObject("additionalInstructions")));
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    Type asNeeded = parseType("asNeeded", json);
    if (asNeeded != null)
      res.setAsNeeded(asNeeded);
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getAsJsonObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getAsJsonObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("doseQuantity"))
      res.setDoseQuantity(parseQuantity(json.getAsJsonObject("doseQuantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getAsJsonObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getAsJsonObject("maxDosePerPeriod")));
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionDispenseComponent parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(JsonObject json, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDispenseComponent res = new MedicationPrescription.MedicationPrescriptionDispenseComponent();
    parseBackboneProperties(json, res);
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getAsJsonObject("medication")));
    if (json.has("validityPeriod"))
      res.setValidityPeriod(parsePeriod(json.getAsJsonObject("validityPeriod")));
    if (json.has("numberOfRepeatsAllowed"))
      res.setNumberOfRepeatsAllowed(parseInteger(json.get("numberOfRepeatsAllowed").getAsLong()));
    if (json.has("_numberOfRepeatsAllowed"))
      parseElementProperties(json.getAsJsonObject("_numberOfRepeatsAllowed"), res.getNumberOfRepeatsAllowed());
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("expectedSupplyDuration"))
      res.setExpectedSupplyDuration(parseDuration(json.getAsJsonObject("expectedSupplyDuration")));
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionSubstitutionComponent parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(JsonObject json, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionSubstitutionComponent res = new MedicationPrescription.MedicationPrescriptionSubstitutionComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    return res;
  }

  private MedicationStatement parseMedicationStatement(JsonObject json) throws Exception {
    MedicationStatement res = new MedicationStatement();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("wasNotGiven"))
      res.setWasNotGiven(parseBoolean(json.get("wasNotGiven").getAsBoolean()));
    if (json.has("_wasNotGiven"))
      parseElementProperties(json.getAsJsonObject("_wasNotGiven"), res.getWasNotGiven());
    if (json.has("reasonNotGiven")) {
      JsonArray array = json.getAsJsonArray("reasonNotGiven");
      for (int i = 0; i < array.size(); i++) {
        res.getReasonNotGiven().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("whenGiven"))
      res.setWhenGiven(parsePeriod(json.getAsJsonObject("whenGiven")));
    if (json.has("medication"))
      res.setMedication(parseResourceReference(json.getAsJsonObject("medication")));
    if (json.has("device")) {
      JsonArray array = json.getAsJsonArray("device");
      for (int i = 0; i < array.size(); i++) {
        res.getDevice().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("dosage")) {
      JsonArray array = json.getAsJsonArray("dosage");
      for (int i = 0; i < array.size(); i++) {
        res.getDosage().add(parseMedicationStatementMedicationStatementDosageComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private MedicationStatement.MedicationStatementDosageComponent parseMedicationStatementMedicationStatementDosageComponent(JsonObject json, MedicationStatement owner) throws Exception {
    MedicationStatement.MedicationStatementDosageComponent res = new MedicationStatement.MedicationStatementDosageComponent();
    parseBackboneProperties(json, res);
    if (json.has("timing"))
      res.setTiming(parseSchedule(json.getAsJsonObject("timing")));
    Type asNeeded = parseType("asNeeded", json);
    if (asNeeded != null)
      res.setAsNeeded(asNeeded);
    if (json.has("site"))
      res.setSite(parseCodeableConcept(json.getAsJsonObject("site")));
    if (json.has("route"))
      res.setRoute(parseCodeableConcept(json.getAsJsonObject("route")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("rate"))
      res.setRate(parseRatio(json.getAsJsonObject("rate")));
    if (json.has("maxDosePerPeriod"))
      res.setMaxDosePerPeriod(parseRatio(json.getAsJsonObject("maxDosePerPeriod")));
    return res;
  }

  private MessageHeader parseMessageHeader(JsonObject json) throws Exception {
    MessageHeader res = new MessageHeader();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseId(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("timestamp"))
      res.setTimestamp(parseInstant(json.get("timestamp").getAsString()));
    if (json.has("_timestamp"))
      parseElementProperties(json.getAsJsonObject("_timestamp"), res.getTimestamp());
    if (json.has("event"))
      res.setEvent(parseCoding(json.getAsJsonObject("event")));
    if (json.has("response"))
      res.setResponse(parseMessageHeaderMessageHeaderResponseComponent(json.getAsJsonObject("response"), res));
    if (json.has("source"))
      res.setSource(parseMessageHeaderMessageSourceComponent(json.getAsJsonObject("source"), res));
    if (json.has("destination")) {
      JsonArray array = json.getAsJsonArray("destination");
      for (int i = 0; i < array.size(); i++) {
        res.getDestination().add(parseMessageHeaderMessageDestinationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("enterer"))
      res.setEnterer(parseResourceReference(json.getAsJsonObject("enterer")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getAsJsonObject("author")));
    if (json.has("receiver"))
      res.setReceiver(parseResourceReference(json.getAsJsonObject("receiver")));
    if (json.has("responsible"))
      res.setResponsible(parseResourceReference(json.getAsJsonObject("responsible")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("data")) {
      JsonArray array = json.getAsJsonArray("data");
      for (int i = 0; i < array.size(); i++) {
        res.getData().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private MessageHeader.MessageHeaderResponseComponent parseMessageHeaderMessageHeaderResponseComponent(JsonObject json, MessageHeader owner) throws Exception {
    MessageHeader.MessageHeaderResponseComponent res = new MessageHeader.MessageHeaderResponseComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseId(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("code"))
      res.setCode(parseEnumeration(json.get("code").getAsString(), MessageHeader.ResponseCode.Null, new MessageHeader.ResponseCodeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("details"))
      res.setDetails(parseResourceReference(json.getAsJsonObject("details")));
    return res;
  }

  private MessageHeader.MessageSourceComponent parseMessageHeaderMessageSourceComponent(JsonObject json, MessageHeader owner) throws Exception {
    MessageHeader.MessageSourceComponent res = new MessageHeader.MessageSourceComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("software"))
      res.setSoftware(parseString(json.get("software").getAsString()));
    if (json.has("_software"))
      parseElementProperties(json.getAsJsonObject("_software"), res.getSoftware());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("contact"))
      res.setContact(parseContact(json.getAsJsonObject("contact")));
    if (json.has("endpoint"))
      res.setEndpoint(parseUri(json.get("endpoint").getAsString()));
    if (json.has("_endpoint"))
      parseElementProperties(json.getAsJsonObject("_endpoint"), res.getEndpoint());
    return res;
  }

  private MessageHeader.MessageDestinationComponent parseMessageHeaderMessageDestinationComponent(JsonObject json, MessageHeader owner) throws Exception {
    MessageHeader.MessageDestinationComponent res = new MessageHeader.MessageDestinationComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getAsJsonObject("target")));
    if (json.has("endpoint"))
      res.setEndpoint(parseUri(json.get("endpoint").getAsString()));
    if (json.has("_endpoint"))
      parseElementProperties(json.getAsJsonObject("_endpoint"), res.getEndpoint());
    return res;
  }

  private Namespace parseNamespace(JsonObject json) throws Exception {
    Namespace res = new Namespace();
    parseResourceProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Namespace.NamespaceType.Null, new Namespace.NamespaceTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Namespace.NamespaceStatus.Null, new Namespace.NamespaceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("country"))
      res.setCountry(parseCode(json.get("country").getAsString()));
    if (json.has("_country"))
      parseElementProperties(json.getAsJsonObject("_country"), res.getCountry());
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("responsible"))
      res.setResponsible(parseString(json.get("responsible").getAsString()));
    if (json.has("_responsible"))
      parseElementProperties(json.getAsJsonObject("_responsible"), res.getResponsible());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("usage"))
      res.setUsage(parseString(json.get("usage").getAsString()));
    if (json.has("_usage"))
      parseElementProperties(json.getAsJsonObject("_usage"), res.getUsage());
    if (json.has("uniqueId")) {
      JsonArray array = json.getAsJsonArray("uniqueId");
      for (int i = 0; i < array.size(); i++) {
        res.getUniqueId().add(parseNamespaceNamespaceUniqueIdComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("contact"))
      res.setContact(parseNamespaceNamespaceContactComponent(json.getAsJsonObject("contact"), res));
    if (json.has("replacedBy"))
      res.setReplacedBy(parseResourceReference(json.getAsJsonObject("replacedBy")));
    return res;
  }

  private Namespace.NamespaceUniqueIdComponent parseNamespaceNamespaceUniqueIdComponent(JsonObject json, Namespace owner) throws Exception {
    Namespace.NamespaceUniqueIdComponent res = new Namespace.NamespaceUniqueIdComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Namespace.NamespaceIdentifierType.Null, new Namespace.NamespaceIdentifierTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("value"))
      res.setValue(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    if (json.has("preferred"))
      res.setPreferred(parseBoolean(json.get("preferred").getAsBoolean()));
    if (json.has("_preferred"))
      parseElementProperties(json.getAsJsonObject("_preferred"), res.getPreferred());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private Namespace.NamespaceContactComponent parseNamespaceNamespaceContactComponent(JsonObject json, Namespace owner) throws Exception {
    Namespace.NamespaceContactComponent res = new Namespace.NamespaceContactComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseHumanName(json.getAsJsonObject("name")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Observation parseObservation(JsonObject json) throws Exception {
    Observation res = new Observation();
    parseResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getAsJsonObject("name")));
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    if (json.has("interpretation"))
      res.setInterpretation(parseCodeableConcept(json.getAsJsonObject("interpretation")));
    if (json.has("comments"))
      res.setComments(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getComments());
    Type applies = parseType("applies", json);
    if (applies != null)
      res.setApplies(applies);
    if (json.has("issued"))
      res.setIssued(parseInstant(json.get("issued").getAsString()));
    if (json.has("_issued"))
      parseElementProperties(json.getAsJsonObject("_issued"), res.getIssued());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Observation.ObservationStatus.Null, new Observation.ObservationStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("reliability"))
      res.setReliability(parseEnumeration(json.get("reliability").getAsString(), Observation.ObservationReliability.Null, new Observation.ObservationReliabilityEnumFactory()));
    if (json.has("_reliability"))
      parseElementProperties(json.getAsJsonObject("_reliability"), res.getReliability());
    if (json.has("bodySite"))
      res.setBodySite(parseCodeableConcept(json.getAsJsonObject("bodySite")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("specimen"))
      res.setSpecimen(parseResourceReference(json.getAsJsonObject("specimen")));
    if (json.has("performer")) {
      JsonArray array = json.getAsJsonArray("performer");
      for (int i = 0; i < array.size(); i++) {
        res.getPerformer().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("referenceRange")) {
      JsonArray array = json.getAsJsonArray("referenceRange");
      for (int i = 0; i < array.size(); i++) {
        res.getReferenceRange().add(parseObservationObservationReferenceRangeComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("related")) {
      JsonArray array = json.getAsJsonArray("related");
      for (int i = 0; i < array.size(); i++) {
        res.getRelated().add(parseObservationObservationRelatedComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Observation.ObservationReferenceRangeComponent parseObservationObservationReferenceRangeComponent(JsonObject json, Observation owner) throws Exception {
    Observation.ObservationReferenceRangeComponent res = new Observation.ObservationReferenceRangeComponent();
    parseBackboneProperties(json, res);
    if (json.has("low"))
      res.setLow(parseQuantity(json.getAsJsonObject("low")));
    if (json.has("high"))
      res.setHigh(parseQuantity(json.getAsJsonObject("high")));
    if (json.has("meaning"))
      res.setMeaning(parseCodeableConcept(json.getAsJsonObject("meaning")));
    if (json.has("age"))
      res.setAge(parseRange(json.getAsJsonObject("age")));
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    return res;
  }

  private Observation.ObservationRelatedComponent parseObservationObservationRelatedComponent(JsonObject json, Observation owner) throws Exception {
    Observation.ObservationRelatedComponent res = new Observation.ObservationRelatedComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Observation.ObservationRelationshiptypes.Null, new Observation.ObservationRelationshiptypesEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getAsJsonObject("target")));
    return res;
  }

  private OperationDefinition parseOperationDefinition(JsonObject json) throws Exception {
    OperationDefinition res = new OperationDefinition();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseUri(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("title"))
      res.setTitle(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitle());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), OperationDefinition.ResourceProfileStatus.Null, new OperationDefinition.ResourceProfileStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimental());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("kind"))
      res.setKind(parseEnumeration(json.get("kind").getAsString(), OperationDefinition.OperationKind.Null, new OperationDefinition.OperationKindEnumFactory()));
    if (json.has("_kind"))
      parseElementProperties(json.getAsJsonObject("_kind"), res.getKind());
    if (json.has("name"))
      res.setName(parseCode(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("notes"))
      res.setNotes(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotes());
    if (json.has("base"))
      res.setBase(parseResourceReference(json.getAsJsonObject("base")));
    if (json.has("system"))
      res.setSystem(parseBoolean(json.get("system").getAsBoolean()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCode(array.get(i).getAsString()));
      }
    };
    if (json.has("_type")) {
      JsonArray array = json.getAsJsonArray("_type");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getType().size())
          res.getType().add(parseCode(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getType().get(i));
      }
    };
    if (json.has("instance"))
      res.setInstance(parseBoolean(json.get("instance").getAsBoolean()));
    if (json.has("_instance"))
      parseElementProperties(json.getAsJsonObject("_instance"), res.getInstance());
    if (json.has("parameter")) {
      JsonArray array = json.getAsJsonArray("parameter");
      for (int i = 0; i < array.size(); i++) {
        res.getParameter().add(parseOperationDefinitionOperationDefinitionParameterComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private OperationDefinition.OperationDefinitionParameterComponent parseOperationDefinitionOperationDefinitionParameterComponent(JsonObject json, OperationDefinition owner) throws Exception {
    OperationDefinition.OperationDefinitionParameterComponent res = new OperationDefinition.OperationDefinitionParameterComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseCode(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("use"))
      res.setUse(parseEnumeration(json.get("use").getAsString(), OperationDefinition.OperationParameterUse.Null, new OperationDefinition.OperationParameterUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUse());
    if (json.has("min"))
      res.setMin(parseInteger(json.get("min").getAsLong()));
    if (json.has("_min"))
      parseElementProperties(json.getAsJsonObject("_min"), res.getMin());
    if (json.has("max"))
      res.setMax(parseString(json.get("max").getAsString()));
    if (json.has("_max"))
      parseElementProperties(json.getAsJsonObject("_max"), res.getMax());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("profile"))
      res.setProfile(parseResourceReference(json.getAsJsonObject("profile")));
    return res;
  }

  private OperationOutcome parseOperationOutcome(JsonObject json) throws Exception {
    OperationOutcome res = new OperationOutcome();
    parseResourceProperties(json, res);
    if (json.has("issue")) {
      JsonArray array = json.getAsJsonArray("issue");
      for (int i = 0; i < array.size(); i++) {
        res.getIssue().add(parseOperationOutcomeOperationOutcomeIssueComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private OperationOutcome.OperationOutcomeIssueComponent parseOperationOutcomeOperationOutcomeIssueComponent(JsonObject json, OperationOutcome owner) throws Exception {
    OperationOutcome.OperationOutcomeIssueComponent res = new OperationOutcome.OperationOutcomeIssueComponent();
    parseBackboneProperties(json, res);
    if (json.has("severity"))
      res.setSeverity(parseEnumeration(json.get("severity").getAsString(), OperationOutcome.IssueSeverity.Null, new OperationOutcome.IssueSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverity());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("details"))
      res.setDetails(parseString(json.get("details").getAsString()));
    if (json.has("_details"))
      parseElementProperties(json.getAsJsonObject("_details"), res.getDetails());
    if (json.has("location")) {
      JsonArray array = json.getAsJsonArray("location");
      for (int i = 0; i < array.size(); i++) {
        res.getLocation().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_location")) {
      JsonArray array = json.getAsJsonArray("_location");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getLocation().size())
          res.getLocation().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getLocation().get(i));
      }
    };
    return res;
  }

  private Order parseOrder(JsonObject json) throws Exception {
    Order res = new Order();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getAsJsonObject("source")));
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getAsJsonObject("target")));
    Type reason = parseType("reason", json);
    if (reason != null)
      res.setReason(reason);
    if (json.has("authority"))
      res.setAuthority(parseResourceReference(json.getAsJsonObject("authority")));
    if (json.has("when"))
      res.setWhen(parseOrderOrderWhenComponent(json.getAsJsonObject("when"), res));
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Order.OrderWhenComponent parseOrderOrderWhenComponent(JsonObject json, Order owner) throws Exception {
    Order.OrderWhenComponent res = new Order.OrderWhenComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("schedule"))
      res.setSchedule(parseSchedule(json.getAsJsonObject("schedule")));
    return res;
  }

  private OrderResponse parseOrderResponse(JsonObject json) throws Exception {
    OrderResponse res = new OrderResponse();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("request"))
      res.setRequest(parseResourceReference(json.getAsJsonObject("request")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("who"))
      res.setWho(parseResourceReference(json.getAsJsonObject("who")));
    Type authority = parseType("authority", json);
    if (authority != null)
      res.setAuthority(authority);
    if (json.has("code"))
      res.setCode(parseEnumeration(json.get("code").getAsString(), OrderResponse.OrderOutcomeCode.Null, new OrderResponse.OrderOutcomeCodeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("fulfillment")) {
      JsonArray array = json.getAsJsonArray("fulfillment");
      for (int i = 0; i < array.size(); i++) {
        res.getFulfillment().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Organization parseOrganization(JsonObject json) throws Exception {
    Organization res = new Organization();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address")) {
      JsonArray array = json.getAsJsonArray("address");
      for (int i = 0; i < array.size(); i++) {
        res.getAddress().add(parseAddress(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("partOf"))
      res.setPartOf(parseResourceReference(json.getAsJsonObject("partOf")));
    if (json.has("contact")) {
      JsonArray array = json.getAsJsonArray("contact");
      for (int i = 0; i < array.size(); i++) {
        res.getContact().add(parseOrganizationOrganizationContactComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("location")) {
      JsonArray array = json.getAsJsonArray("location");
      for (int i = 0; i < array.size(); i++) {
        res.getLocation().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("active"))
      res.setActive(parseBoolean(json.get("active").getAsBoolean()));
    if (json.has("_active"))
      parseElementProperties(json.getAsJsonObject("_active"), res.getActive());
    return res;
  }

  private Organization.OrganizationContactComponent parseOrganizationOrganizationContactComponent(JsonObject json, Organization owner) throws Exception {
    Organization.OrganizationContactComponent res = new Organization.OrganizationContactComponent();
    parseBackboneProperties(json, res);
    if (json.has("purpose"))
      res.setPurpose(parseCodeableConcept(json.getAsJsonObject("purpose")));
    if (json.has("name"))
      res.setName(parseHumanName(json.getAsJsonObject("name")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getAsJsonObject("address")));
    if (json.has("gender"))
      res.setGender(parseEnumeration(json.get("gender").getAsString(), Organization.AdministrativeGender.Null, new Organization.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGender());
    return res;
  }

  private Other parseOther(JsonObject json) throws Exception {
    Other res = new Other();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getAsJsonObject("author")));
    if (json.has("created"))
      res.setCreated(parseDate(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreated());
    return res;
  }

  private Patient parsePatient(JsonObject json) throws Exception {
    Patient res = new Patient();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name")) {
      JsonArray array = json.getAsJsonArray("name");
      for (int i = 0; i < array.size(); i++) {
        res.getName().add(parseHumanName(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("gender"))
      res.setGender(parseEnumeration(json.get("gender").getAsString(), Patient.AdministrativeGender.Null, new Patient.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGender());
    if (json.has("birthDate"))
      res.setBirthDate(parseDateTime(json.get("birthDate").getAsString()));
    if (json.has("_birthDate"))
      parseElementProperties(json.getAsJsonObject("_birthDate"), res.getBirthDate());
    Type deceased = parseType("deceased", json);
    if (deceased != null)
      res.setDeceased(deceased);
    if (json.has("address")) {
      JsonArray array = json.getAsJsonArray("address");
      for (int i = 0; i < array.size(); i++) {
        res.getAddress().add(parseAddress(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("maritalStatus"))
      res.setMaritalStatus(parseCodeableConcept(json.getAsJsonObject("maritalStatus")));
    Type multipleBirth = parseType("multipleBirth", json);
    if (multipleBirth != null)
      res.setMultipleBirth(multipleBirth);
    if (json.has("photo")) {
      JsonArray array = json.getAsJsonArray("photo");
      for (int i = 0; i < array.size(); i++) {
        res.getPhoto().add(parseAttachment(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("contact")) {
      JsonArray array = json.getAsJsonArray("contact");
      for (int i = 0; i < array.size(); i++) {
        res.getContact().add(parsePatientContactComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("animal"))
      res.setAnimal(parsePatientAnimalComponent(json.getAsJsonObject("animal"), res));
    if (json.has("communication")) {
      JsonArray array = json.getAsJsonArray("communication");
      for (int i = 0; i < array.size(); i++) {
        res.getCommunication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("careProvider")) {
      JsonArray array = json.getAsJsonArray("careProvider");
      for (int i = 0; i < array.size(); i++) {
        res.getCareProvider().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("managingOrganization"))
      res.setManagingOrganization(parseResourceReference(json.getAsJsonObject("managingOrganization")));
    if (json.has("link")) {
      JsonArray array = json.getAsJsonArray("link");
      for (int i = 0; i < array.size(); i++) {
        res.getLink().add(parsePatientPatientLinkComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("active"))
      res.setActive(parseBoolean(json.get("active").getAsBoolean()));
    if (json.has("_active"))
      parseElementProperties(json.getAsJsonObject("_active"), res.getActive());
    return res;
  }

  private Patient.ContactComponent parsePatientContactComponent(JsonObject json, Patient owner) throws Exception {
    Patient.ContactComponent res = new Patient.ContactComponent();
    parseBackboneProperties(json, res);
    if (json.has("relationship")) {
      JsonArray array = json.getAsJsonArray("relationship");
      for (int i = 0; i < array.size(); i++) {
        res.getRelationship().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name"))
      res.setName(parseHumanName(json.getAsJsonObject("name")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getAsJsonObject("address")));
    if (json.has("gender"))
      res.setGender(parseEnumeration(json.get("gender").getAsString(), Patient.AdministrativeGender.Null, new Patient.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGender());
    if (json.has("organization"))
      res.setOrganization(parseResourceReference(json.getAsJsonObject("organization")));
    return res;
  }

  private Patient.AnimalComponent parsePatientAnimalComponent(JsonObject json, Patient owner) throws Exception {
    Patient.AnimalComponent res = new Patient.AnimalComponent();
    parseBackboneProperties(json, res);
    if (json.has("species"))
      res.setSpecies(parseCodeableConcept(json.getAsJsonObject("species")));
    if (json.has("breed"))
      res.setBreed(parseCodeableConcept(json.getAsJsonObject("breed")));
    if (json.has("genderStatus"))
      res.setGenderStatus(parseCodeableConcept(json.getAsJsonObject("genderStatus")));
    return res;
  }

  private Patient.PatientLinkComponent parsePatientPatientLinkComponent(JsonObject json, Patient owner) throws Exception {
    Patient.PatientLinkComponent res = new Patient.PatientLinkComponent();
    parseBackboneProperties(json, res);
    if (json.has("other"))
      res.setOther(parseResourceReference(json.getAsJsonObject("other")));
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Patient.LinkType.Null, new Patient.LinkTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    return res;
  }

  private Practitioner parsePractitioner(JsonObject json) throws Exception {
    Practitioner res = new Practitioner();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name"))
      res.setName(parseHumanName(json.getAsJsonObject("name")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address")) {
      JsonArray array = json.getAsJsonArray("address");
      for (int i = 0; i < array.size(); i++) {
        res.getAddress().add(parseAddress(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("gender"))
      res.setGender(parseEnumeration(json.get("gender").getAsString(), Practitioner.AdministrativeGender.Null, new Practitioner.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGender());
    if (json.has("birthDate"))
      res.setBirthDate(parseDateTime(json.get("birthDate").getAsString()));
    if (json.has("_birthDate"))
      parseElementProperties(json.getAsJsonObject("_birthDate"), res.getBirthDate());
    if (json.has("photo")) {
      JsonArray array = json.getAsJsonArray("photo");
      for (int i = 0; i < array.size(); i++) {
        res.getPhoto().add(parseAttachment(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("organization"))
      res.setOrganization(parseResourceReference(json.getAsJsonObject("organization")));
    if (json.has("role")) {
      JsonArray array = json.getAsJsonArray("role");
      for (int i = 0; i < array.size(); i++) {
        res.getRole().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("specialty")) {
      JsonArray array = json.getAsJsonArray("specialty");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecialty().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("location")) {
      JsonArray array = json.getAsJsonArray("location");
      for (int i = 0; i < array.size(); i++) {
        res.getLocation().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("qualification")) {
      JsonArray array = json.getAsJsonArray("qualification");
      for (int i = 0; i < array.size(); i++) {
        res.getQualification().add(parsePractitionerPractitionerQualificationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("communication")) {
      JsonArray array = json.getAsJsonArray("communication");
      for (int i = 0; i < array.size(); i++) {
        res.getCommunication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Practitioner.PractitionerQualificationComponent parsePractitionerPractitionerQualificationComponent(JsonObject json, Practitioner owner) throws Exception {
    Practitioner.PractitionerQualificationComponent res = new Practitioner.PractitionerQualificationComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("issuer"))
      res.setIssuer(parseResourceReference(json.getAsJsonObject("issuer")));
    return res;
  }

  private Procedure parseProcedure(JsonObject json) throws Exception {
    Procedure res = new Procedure();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("bodySite")) {
      JsonArray array = json.getAsJsonArray("bodySite");
      for (int i = 0; i < array.size(); i++) {
        res.getBodySite().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("indication")) {
      JsonArray array = json.getAsJsonArray("indication");
      for (int i = 0; i < array.size(); i++) {
        res.getIndication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("performer")) {
      JsonArray array = json.getAsJsonArray("performer");
      for (int i = 0; i < array.size(); i++) {
        res.getPerformer().add(parseProcedureProcedurePerformerComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("date"))
      res.setDate(parsePeriod(json.getAsJsonObject("date")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("outcome"))
      res.setOutcome(parseString(json.get("outcome").getAsString()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcome());
    if (json.has("report")) {
      JsonArray array = json.getAsJsonArray("report");
      for (int i = 0; i < array.size(); i++) {
        res.getReport().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("complication")) {
      JsonArray array = json.getAsJsonArray("complication");
      for (int i = 0; i < array.size(); i++) {
        res.getComplication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("followUp"))
      res.setFollowUp(parseString(json.get("followUp").getAsString()));
    if (json.has("_followUp"))
      parseElementProperties(json.getAsJsonObject("_followUp"), res.getFollowUp());
    if (json.has("relatedItem")) {
      JsonArray array = json.getAsJsonArray("relatedItem");
      for (int i = 0; i < array.size(); i++) {
        res.getRelatedItem().add(parseProcedureProcedureRelatedItemComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("notes"))
      res.setNotes(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotes());
    return res;
  }

  private Procedure.ProcedurePerformerComponent parseProcedureProcedurePerformerComponent(JsonObject json, Procedure owner) throws Exception {
    Procedure.ProcedurePerformerComponent res = new Procedure.ProcedurePerformerComponent();
    parseBackboneProperties(json, res);
    if (json.has("person"))
      res.setPerson(parseResourceReference(json.getAsJsonObject("person")));
    if (json.has("role"))
      res.setRole(parseCodeableConcept(json.getAsJsonObject("role")));
    return res;
  }

  private Procedure.ProcedureRelatedItemComponent parseProcedureProcedureRelatedItemComponent(JsonObject json, Procedure owner) throws Exception {
    Procedure.ProcedureRelatedItemComponent res = new Procedure.ProcedureRelatedItemComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Procedure.ProcedureRelationshipType.Null, new Procedure.ProcedureRelationshipTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("target"))
      res.setTarget(parseResourceReference(json.getAsJsonObject("target")));
    return res;
  }

  private Profile parseProfile(JsonObject json) throws Exception {
    Profile res = new Profile();
    parseResourceProperties(json, res);
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Profile.ResourceProfileStatus.Null, new Profile.ResourceProfileStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimental());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("requirements"))
      res.setRequirements(parseString(json.get("requirements").getAsString()));
    if (json.has("_requirements"))
      parseElementProperties(json.getAsJsonObject("_requirements"), res.getRequirements());
    if (json.has("fhirVersion"))
      res.setFhirVersion(parseId(json.get("fhirVersion").getAsString()));
    if (json.has("_fhirVersion"))
      parseElementProperties(json.getAsJsonObject("_fhirVersion"), res.getFhirVersion());
    if (json.has("mapping")) {
      JsonArray array = json.getAsJsonArray("mapping");
      for (int i = 0; i < array.size(); i++) {
        res.getMapping().add(parseProfileProfileMappingComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("structure")) {
      JsonArray array = json.getAsJsonArray("structure");
      for (int i = 0; i < array.size(); i++) {
        res.getStructure().add(parseProfileProfileStructureComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("extensionDefn")) {
      JsonArray array = json.getAsJsonArray("extensionDefn");
      for (int i = 0; i < array.size(); i++) {
        res.getExtensionDefn().add(parseProfileProfileExtensionDefnComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Profile.ProfileMappingComponent parseProfileProfileMappingComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ProfileMappingComponent res = new Profile.ProfileMappingComponent();
    parseBackboneProperties(json, res);
    if (json.has("identity"))
      res.setIdentity(parseId(json.get("identity").getAsString()));
    if (json.has("_identity"))
      parseElementProperties(json.getAsJsonObject("_identity"), res.getIdentity());
    if (json.has("uri"))
      res.setUri(parseUri(json.get("uri").getAsString()));
    if (json.has("_uri"))
      parseElementProperties(json.getAsJsonObject("_uri"), res.getUri());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("comments"))
      res.setComments(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getComments());
    return res;
  }

  private Profile.ProfileStructureComponent parseProfileProfileStructureComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ProfileStructureComponent res = new Profile.ProfileStructureComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("base"))
      res.setBase(parseUri(json.get("base").getAsString()));
    if (json.has("_base"))
      parseElementProperties(json.getAsJsonObject("_base"), res.getBase());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("publish"))
      res.setPublish(parseBoolean(json.get("publish").getAsBoolean()));
    if (json.has("_publish"))
      parseElementProperties(json.getAsJsonObject("_publish"), res.getPublish());
    if (json.has("purpose"))
      res.setPurpose(parseString(json.get("purpose").getAsString()));
    if (json.has("_purpose"))
      parseElementProperties(json.getAsJsonObject("_purpose"), res.getPurpose());
    if (json.has("snapshot"))
      res.setSnapshot(parseProfileConstraintComponent(json.getAsJsonObject("snapshot"), owner));
    if (json.has("differential"))
      res.setDifferential(parseProfileConstraintComponent(json.getAsJsonObject("differential"), owner));
    if (json.has("searchParam")) {
      JsonArray array = json.getAsJsonArray("searchParam");
      for (int i = 0; i < array.size(); i++) {
        res.getSearchParam().add(parseProfileProfileStructureSearchParamComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Profile.ConstraintComponent parseProfileConstraintComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ConstraintComponent res = new Profile.ConstraintComponent();
    parseBackboneProperties(json, res);
    if (json.has("element")) {
      JsonArray array = json.getAsJsonArray("element");
      for (int i = 0; i < array.size(); i++) {
        res.getElement().add(parseProfileElementComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Profile.ElementComponent parseProfileElementComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ElementComponent res = new Profile.ElementComponent();
    parseBackboneProperties(json, res);
    if (json.has("path"))
      res.setPath(parseString(json.get("path").getAsString()));
    if (json.has("_path"))
      parseElementProperties(json.getAsJsonObject("_path"), res.getPath());
    if (json.has("representation")) {
      JsonArray array = json.getAsJsonArray("representation");
      for (int i = 0; i < array.size(); i++) {
        res.getRepresentation().add(parseEnumeration(array.get(i).getAsString(), Profile.PropertyRepresentation.Null, new Profile.PropertyRepresentationEnumFactory()));
      }
    };
    if (json.has("_representation")) {
      JsonArray array = json.getAsJsonArray("_representation");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getRepresentation().size())
          res.getRepresentation().add(parseEnumeration(null, Profile.PropertyRepresentation.Null, new Profile.PropertyRepresentationEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getRepresentation().get(i));
      }
    };
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("slicing"))
      res.setSlicing(parseProfileElementSlicingComponent(json.getAsJsonObject("slicing"), owner));
    if (json.has("definition"))
      res.setDefinition(parseProfileElementDefinitionComponent(json.getAsJsonObject("definition"), owner));
    return res;
  }

  private Profile.ElementSlicingComponent parseProfileElementSlicingComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ElementSlicingComponent res = new Profile.ElementSlicingComponent();
    parseBackboneProperties(json, res);
    if (json.has("discriminator"))
      res.setDiscriminator(parseId(json.get("discriminator").getAsString()));
    if (json.has("_discriminator"))
      parseElementProperties(json.getAsJsonObject("_discriminator"), res.getDiscriminator());
    if (json.has("ordered"))
      res.setOrdered(parseBoolean(json.get("ordered").getAsBoolean()));
    if (json.has("_ordered"))
      parseElementProperties(json.getAsJsonObject("_ordered"), res.getOrdered());
    if (json.has("rules"))
      res.setRules(parseEnumeration(json.get("rules").getAsString(), Profile.ResourceSlicingRules.Null, new Profile.ResourceSlicingRulesEnumFactory()));
    if (json.has("_rules"))
      parseElementProperties(json.getAsJsonObject("_rules"), res.getRules());
    return res;
  }

  private Profile.ElementDefinitionComponent parseProfileElementDefinitionComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionComponent res = new Profile.ElementDefinitionComponent();
    parseBackboneProperties(json, res);
    if (json.has("short"))
      res.setShort(parseString(json.get("short").getAsString()));
    if (json.has("_short"))
      parseElementProperties(json.getAsJsonObject("_short"), res.getShort());
    if (json.has("formal"))
      res.setFormal(parseString(json.get("formal").getAsString()));
    if (json.has("_formal"))
      parseElementProperties(json.getAsJsonObject("_formal"), res.getFormal());
    if (json.has("comments"))
      res.setComments(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getComments());
    if (json.has("requirements"))
      res.setRequirements(parseString(json.get("requirements").getAsString()));
    if (json.has("_requirements"))
      parseElementProperties(json.getAsJsonObject("_requirements"), res.getRequirements());
    if (json.has("synonym")) {
      JsonArray array = json.getAsJsonArray("synonym");
      for (int i = 0; i < array.size(); i++) {
        res.getSynonym().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_synonym")) {
      JsonArray array = json.getAsJsonArray("_synonym");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getSynonym().size())
          res.getSynonym().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getSynonym().get(i));
      }
    };
    if (json.has("min"))
      res.setMin(parseInteger(json.get("min").getAsLong()));
    if (json.has("_min"))
      parseElementProperties(json.getAsJsonObject("_min"), res.getMin());
    if (json.has("max"))
      res.setMax(parseString(json.get("max").getAsString()));
    if (json.has("_max"))
      parseElementProperties(json.getAsJsonObject("_max"), res.getMax());
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseProfileTypeRefComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("nameReference"))
      res.setNameReference(parseString(json.get("nameReference").getAsString()));
    if (json.has("_nameReference"))
      parseElementProperties(json.getAsJsonObject("_nameReference"), res.getNameReference());
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    Type example = parseType("example", json);
    if (example != null)
      res.setExample(example);
    if (json.has("maxLength"))
      res.setMaxLength(parseInteger(json.get("maxLength").getAsLong()));
    if (json.has("_maxLength"))
      parseElementProperties(json.getAsJsonObject("_maxLength"), res.getMaxLength());
    if (json.has("condition")) {
      JsonArray array = json.getAsJsonArray("condition");
      for (int i = 0; i < array.size(); i++) {
        res.getCondition().add(parseId(array.get(i).getAsString()));
      }
    };
    if (json.has("_condition")) {
      JsonArray array = json.getAsJsonArray("_condition");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getCondition().size())
          res.getCondition().add(parseId(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getCondition().get(i));
      }
    };
    if (json.has("constraint")) {
      JsonArray array = json.getAsJsonArray("constraint");
      for (int i = 0; i < array.size(); i++) {
        res.getConstraint().add(parseProfileElementDefinitionConstraintComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("mustSupport"))
      res.setMustSupport(parseBoolean(json.get("mustSupport").getAsBoolean()));
    if (json.has("_mustSupport"))
      parseElementProperties(json.getAsJsonObject("_mustSupport"), res.getMustSupport());
    if (json.has("isModifier"))
      res.setIsModifier(parseBoolean(json.get("isModifier").getAsBoolean()));
    if (json.has("_isModifier"))
      parseElementProperties(json.getAsJsonObject("_isModifier"), res.getIsModifier());
    if (json.has("binding"))
      res.setBinding(parseProfileElementDefinitionBindingComponent(json.getAsJsonObject("binding"), owner));
    if (json.has("mapping")) {
      JsonArray array = json.getAsJsonArray("mapping");
      for (int i = 0; i < array.size(); i++) {
        res.getMapping().add(parseProfileElementDefinitionMappingComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Profile.TypeRefComponent parseProfileTypeRefComponent(JsonObject json, Profile owner) throws Exception {
    Profile.TypeRefComponent res = new Profile.TypeRefComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("profile"))
      res.setProfile(parseUri(json.get("profile").getAsString()));
    if (json.has("_profile"))
      parseElementProperties(json.getAsJsonObject("_profile"), res.getProfile());
    if (json.has("aggregation")) {
      JsonArray array = json.getAsJsonArray("aggregation");
      for (int i = 0; i < array.size(); i++) {
        res.getAggregation().add(parseEnumeration(array.get(i).getAsString(), Profile.ResourceAggregationMode.Null, new Profile.ResourceAggregationModeEnumFactory()));
      }
    };
    if (json.has("_aggregation")) {
      JsonArray array = json.getAsJsonArray("_aggregation");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getAggregation().size())
          res.getAggregation().add(parseEnumeration(null, Profile.ResourceAggregationMode.Null, new Profile.ResourceAggregationModeEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getAggregation().get(i));
      }
    };
    return res;
  }

  private Profile.ElementDefinitionConstraintComponent parseProfileElementDefinitionConstraintComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionConstraintComponent res = new Profile.ElementDefinitionConstraintComponent();
    parseBackboneProperties(json, res);
    if (json.has("key"))
      res.setKey(parseId(json.get("key").getAsString()));
    if (json.has("_key"))
      parseElementProperties(json.getAsJsonObject("_key"), res.getKey());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("severity"))
      res.setSeverity(parseEnumeration(json.get("severity").getAsString(), Profile.ConstraintSeverity.Null, new Profile.ConstraintSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverity());
    if (json.has("human"))
      res.setHuman(parseString(json.get("human").getAsString()));
    if (json.has("_human"))
      parseElementProperties(json.getAsJsonObject("_human"), res.getHuman());
    if (json.has("xpath"))
      res.setXpath(parseString(json.get("xpath").getAsString()));
    if (json.has("_xpath"))
      parseElementProperties(json.getAsJsonObject("_xpath"), res.getXpath());
    return res;
  }

  private Profile.ElementDefinitionBindingComponent parseProfileElementDefinitionBindingComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionBindingComponent res = new Profile.ElementDefinitionBindingComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("isExtensible"))
      res.setIsExtensible(parseBoolean(json.get("isExtensible").getAsBoolean()));
    if (json.has("_isExtensible"))
      parseElementProperties(json.getAsJsonObject("_isExtensible"), res.getIsExtensible());
    if (json.has("conformance"))
      res.setConformance(parseEnumeration(json.get("conformance").getAsString(), Profile.BindingConformance.Null, new Profile.BindingConformanceEnumFactory()));
    if (json.has("_conformance"))
      parseElementProperties(json.getAsJsonObject("_conformance"), res.getConformance());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    Type reference = parseType("reference", json);
    if (reference != null)
      res.setReference(reference);
    return res;
  }

  private Profile.ElementDefinitionMappingComponent parseProfileElementDefinitionMappingComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ElementDefinitionMappingComponent res = new Profile.ElementDefinitionMappingComponent();
    parseBackboneProperties(json, res);
    if (json.has("identity"))
      res.setIdentity(parseId(json.get("identity").getAsString()));
    if (json.has("_identity"))
      parseElementProperties(json.getAsJsonObject("_identity"), res.getIdentity());
    if (json.has("map"))
      res.setMap(parseString(json.get("map").getAsString()));
    if (json.has("_map"))
      parseElementProperties(json.getAsJsonObject("_map"), res.getMap());
    return res;
  }

  private Profile.ProfileStructureSearchParamComponent parseProfileProfileStructureSearchParamComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ProfileStructureSearchParamComponent res = new Profile.ProfileStructureSearchParamComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Profile.SearchParamType.Null, new Profile.SearchParamTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("documentation"))
      res.setDocumentation(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentation());
    if (json.has("xpath"))
      res.setXpath(parseString(json.get("xpath").getAsString()));
    if (json.has("_xpath"))
      parseElementProperties(json.getAsJsonObject("_xpath"), res.getXpath());
    if (json.has("target")) {
      JsonArray array = json.getAsJsonArray("target");
      for (int i = 0; i < array.size(); i++) {
        res.getTarget().add(parseCode(array.get(i).getAsString()));
      }
    };
    if (json.has("_target")) {
      JsonArray array = json.getAsJsonArray("_target");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getTarget().size())
          res.getTarget().add(parseCode(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getTarget().get(i));
      }
    };
    return res;
  }

  private Profile.ProfileExtensionDefnComponent parseProfileProfileExtensionDefnComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ProfileExtensionDefnComponent res = new Profile.ProfileExtensionDefnComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("display"))
      res.setDisplay(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplay());
    if (json.has("contextType"))
      res.setContextType(parseEnumeration(json.get("contextType").getAsString(), Profile.ExtensionContext.Null, new Profile.ExtensionContextEnumFactory()));
    if (json.has("_contextType"))
      parseElementProperties(json.getAsJsonObject("_contextType"), res.getContextType());
    if (json.has("context")) {
      JsonArray array = json.getAsJsonArray("context");
      for (int i = 0; i < array.size(); i++) {
        res.getContext().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_context")) {
      JsonArray array = json.getAsJsonArray("_context");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getContext().size())
          res.getContext().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getContext().get(i));
      }
    };
    if (json.has("element")) {
      JsonArray array = json.getAsJsonArray("element");
      for (int i = 0; i < array.size(); i++) {
        res.getElement().add(parseProfileElementComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Provenance parseProvenance(JsonObject json) throws Exception {
    Provenance res = new Provenance();
    parseResourceProperties(json, res);
    if (json.has("target")) {
      JsonArray array = json.getAsJsonArray("target");
      for (int i = 0; i < array.size(); i++) {
        res.getTarget().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("recorded"))
      res.setRecorded(parseInstant(json.get("recorded").getAsString()));
    if (json.has("_recorded"))
      parseElementProperties(json.getAsJsonObject("_recorded"), res.getRecorded());
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("location"))
      res.setLocation(parseResourceReference(json.getAsJsonObject("location")));
    if (json.has("policy")) {
      JsonArray array = json.getAsJsonArray("policy");
      for (int i = 0; i < array.size(); i++) {
        res.getPolicy().add(parseUri(array.get(i).getAsString()));
      }
    };
    if (json.has("_policy")) {
      JsonArray array = json.getAsJsonArray("_policy");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getPolicy().size())
          res.getPolicy().add(parseUri(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getPolicy().get(i));
      }
    };
    if (json.has("agent")) {
      JsonArray array = json.getAsJsonArray("agent");
      for (int i = 0; i < array.size(); i++) {
        res.getAgent().add(parseProvenanceProvenanceAgentComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("entity")) {
      JsonArray array = json.getAsJsonArray("entity");
      for (int i = 0; i < array.size(); i++) {
        res.getEntity().add(parseProvenanceProvenanceEntityComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("integritySignature"))
      res.setIntegritySignature(parseString(json.get("integritySignature").getAsString()));
    if (json.has("_integritySignature"))
      parseElementProperties(json.getAsJsonObject("_integritySignature"), res.getIntegritySignature());
    return res;
  }

  private Provenance.ProvenanceAgentComponent parseProvenanceProvenanceAgentComponent(JsonObject json, Provenance owner) throws Exception {
    Provenance.ProvenanceAgentComponent res = new Provenance.ProvenanceAgentComponent();
    parseBackboneProperties(json, res);
    if (json.has("role"))
      res.setRole(parseCoding(json.getAsJsonObject("role")));
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("reference"))
      res.setReference(parseUri(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReference());
    if (json.has("display"))
      res.setDisplay(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplay());
    return res;
  }

  private Provenance.ProvenanceEntityComponent parseProvenanceProvenanceEntityComponent(JsonObject json, Provenance owner) throws Exception {
    Provenance.ProvenanceEntityComponent res = new Provenance.ProvenanceEntityComponent();
    parseBackboneProperties(json, res);
    if (json.has("role"))
      res.setRole(parseEnumeration(json.get("role").getAsString(), Provenance.ProvenanceEntityRole.Null, new Provenance.ProvenanceEntityRoleEnumFactory()));
    if (json.has("_role"))
      parseElementProperties(json.getAsJsonObject("_role"), res.getRole());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("reference"))
      res.setReference(parseUri(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReference());
    if (json.has("display"))
      res.setDisplay(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplay());
    if (json.has("agent"))
      res.setAgent(parseProvenanceProvenanceAgentComponent(json.getAsJsonObject("agent"), owner));
    return res;
  }

  private Query parseQuery(JsonObject json) throws Exception {
    Query res = new Query();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseUri(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("parameter")) {
      JsonArray array = json.getAsJsonArray("parameter");
      for (int i = 0; i < array.size(); i++) {
        res.getParameter().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("response"))
      res.setResponse(parseQueryQueryResponseComponent(json.getAsJsonObject("response"), res));
    return res;
  }

  private Query.QueryResponseComponent parseQueryQueryResponseComponent(JsonObject json, Query owner) throws Exception {
    Query.QueryResponseComponent res = new Query.QueryResponseComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseUri(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("outcome"))
      res.setOutcome(parseEnumeration(json.get("outcome").getAsString(), Query.QueryOutcome.Null, new Query.QueryOutcomeEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcome());
    if (json.has("total"))
      res.setTotal(parseInteger(json.get("total").getAsLong()));
    if (json.has("_total"))
      parseElementProperties(json.getAsJsonObject("_total"), res.getTotal());
    if (json.has("parameter")) {
      JsonArray array = json.getAsJsonArray("parameter");
      for (int i = 0; i < array.size(); i++) {
        res.getParameter().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("first")) {
      JsonArray array = json.getAsJsonArray("first");
      for (int i = 0; i < array.size(); i++) {
        res.getFirst().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("previous")) {
      JsonArray array = json.getAsJsonArray("previous");
      for (int i = 0; i < array.size(); i++) {
        res.getPrevious().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("next")) {
      JsonArray array = json.getAsJsonArray("next");
      for (int i = 0; i < array.size(); i++) {
        res.getNext().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("last")) {
      JsonArray array = json.getAsJsonArray("last");
      for (int i = 0; i < array.size(); i++) {
        res.getLast().add(parseExtension(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("reference")) {
      JsonArray array = json.getAsJsonArray("reference");
      for (int i = 0; i < array.size(); i++) {
        res.getReference().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Questionnaire parseQuestionnaire(JsonObject json) throws Exception {
    Questionnaire res = new Questionnaire();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Questionnaire.QuestionnaireStatus.Null, new Questionnaire.QuestionnaireStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisher());
    if (json.has("group"))
      res.setGroup(parseQuestionnaireGroupComponent(json.getAsJsonObject("group"), res));
    return res;
  }

  private Questionnaire.GroupComponent parseQuestionnaireGroupComponent(JsonObject json, Questionnaire owner) throws Exception {
    Questionnaire.GroupComponent res = new Questionnaire.GroupComponent();
    parseBackboneProperties(json, res);
    if (json.has("linkId"))
      res.setLinkId(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkId());
    if (json.has("title"))
      res.setTitle(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitle());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    if (json.has("required"))
      res.setRequired(parseBoolean(json.get("required").getAsBoolean()));
    if (json.has("_required"))
      parseElementProperties(json.getAsJsonObject("_required"), res.getRequired());
    if (json.has("repeats"))
      res.setRepeats(parseBoolean(json.get("repeats").getAsBoolean()));
    if (json.has("_repeats"))
      parseElementProperties(json.getAsJsonObject("_repeats"), res.getRepeats());
    if (json.has("group")) {
      JsonArray array = json.getAsJsonArray("group");
      for (int i = 0; i < array.size(); i++) {
        res.getGroup().add(parseQuestionnaireGroupComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("question")) {
      JsonArray array = json.getAsJsonArray("question");
      for (int i = 0; i < array.size(); i++) {
        res.getQuestion().add(parseQuestionnaireQuestionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private Questionnaire.QuestionComponent parseQuestionnaireQuestionComponent(JsonObject json, Questionnaire owner) throws Exception {
    Questionnaire.QuestionComponent res = new Questionnaire.QuestionComponent();
    parseBackboneProperties(json, res);
    if (json.has("linkId"))
      res.setLinkId(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkId());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Questionnaire.AnswerFormat.Null, new Questionnaire.AnswerFormatEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("required"))
      res.setRequired(parseBoolean(json.get("required").getAsBoolean()));
    if (json.has("_required"))
      parseElementProperties(json.getAsJsonObject("_required"), res.getRequired());
    if (json.has("repeats"))
      res.setRepeats(parseBoolean(json.get("repeats").getAsBoolean()));
    if (json.has("_repeats"))
      parseElementProperties(json.getAsJsonObject("_repeats"), res.getRepeats());
    if (json.has("options"))
      res.setOptions(parseResourceReference(json.getAsJsonObject("options")));
    if (json.has("group")) {
      JsonArray array = json.getAsJsonArray("group");
      for (int i = 0; i < array.size(); i++) {
        res.getGroup().add(parseQuestionnaireGroupComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private QuestionnaireAnswers parseQuestionnaireAnswers(JsonObject json) throws Exception {
    QuestionnaireAnswers res = new QuestionnaireAnswers();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("questionnaire"))
      res.setQuestionnaire(parseResourceReference(json.getAsJsonObject("questionnaire")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), QuestionnaireAnswers.QuestionnaireAnswersStatus.Null, new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseResourceReference(json.getAsJsonObject("author")));
    if (json.has("authored"))
      res.setAuthored(parseDateTime(json.get("authored").getAsString()));
    if (json.has("_authored"))
      parseElementProperties(json.getAsJsonObject("_authored"), res.getAuthored());
    if (json.has("source"))
      res.setSource(parseResourceReference(json.getAsJsonObject("source")));
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("group"))
      res.setGroup(parseQuestionnaireAnswersGroupComponent(json.getAsJsonObject("group"), res));
    return res;
  }

  private QuestionnaireAnswers.GroupComponent parseQuestionnaireAnswersGroupComponent(JsonObject json, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.GroupComponent res = new QuestionnaireAnswers.GroupComponent();
    parseBackboneProperties(json, res);
    if (json.has("linkId"))
      res.setLinkId(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkId());
    if (json.has("title"))
      res.setTitle(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitle());
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("group")) {
      JsonArray array = json.getAsJsonArray("group");
      for (int i = 0; i < array.size(); i++) {
        res.getGroup().add(parseQuestionnaireAnswersGroupComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("question")) {
      JsonArray array = json.getAsJsonArray("question");
      for (int i = 0; i < array.size(); i++) {
        res.getQuestion().add(parseQuestionnaireAnswersQuestionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private QuestionnaireAnswers.QuestionComponent parseQuestionnaireAnswersQuestionComponent(JsonObject json, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.QuestionComponent res = new QuestionnaireAnswers.QuestionComponent();
    parseBackboneProperties(json, res);
    if (json.has("linkId"))
      res.setLinkId(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkId());
    if (json.has("text"))
      res.setText(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getText());
    if (json.has("answer")) {
      JsonArray array = json.getAsJsonArray("answer");
      for (int i = 0; i < array.size(); i++) {
        res.getAnswer().add(parseQuestionnaireAnswersQuestionAnswerComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("group")) {
      JsonArray array = json.getAsJsonArray("group");
      for (int i = 0; i < array.size(); i++) {
        res.getGroup().add(parseQuestionnaireAnswersGroupComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private QuestionnaireAnswers.QuestionAnswerComponent parseQuestionnaireAnswersQuestionAnswerComponent(JsonObject json, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.QuestionAnswerComponent res = new QuestionnaireAnswers.QuestionAnswerComponent();
    parseBackboneProperties(json, res);
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    return res;
  }

  private ReferralRequest parseReferralRequest(JsonObject json) throws Exception {
    ReferralRequest res = new ReferralRequest();
    parseResourceProperties(json, res);
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), ReferralRequest.Referralstatus.Null, new ReferralRequest.ReferralstatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("specialty"))
      res.setSpecialty(parseCodeableConcept(json.getAsJsonObject("specialty")));
    if (json.has("priority"))
      res.setPriority(parseCodeableConcept(json.getAsJsonObject("priority")));
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("requester"))
      res.setRequester(parseResourceReference(json.getAsJsonObject("requester")));
    if (json.has("recipient")) {
      JsonArray array = json.getAsJsonArray("recipient");
      for (int i = 0; i < array.size(); i++) {
        res.getRecipient().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseResourceReference(json.getAsJsonObject("encounter")));
    if (json.has("dateSent"))
      res.setDateSent(parseDateTime(json.get("dateSent").getAsString()));
    if (json.has("_dateSent"))
      parseElementProperties(json.getAsJsonObject("_dateSent"), res.getDateSent());
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("serviceRequested")) {
      JsonArray array = json.getAsJsonArray("serviceRequested");
      for (int i = 0; i < array.size(); i++) {
        res.getServiceRequested().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("supportingInformation")) {
      JsonArray array = json.getAsJsonArray("supportingInformation");
      for (int i = 0; i < array.size(); i++) {
        res.getSupportingInformation().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("fulfillmentTime"))
      res.setFulfillmentTime(parsePeriod(json.getAsJsonObject("fulfillmentTime")));
    return res;
  }

  private RelatedPerson parseRelatedPerson(JsonObject json) throws Exception {
    RelatedPerson res = new RelatedPerson();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("relationship"))
      res.setRelationship(parseCodeableConcept(json.getAsJsonObject("relationship")));
    if (json.has("name"))
      res.setName(parseHumanName(json.getAsJsonObject("name")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("gender"))
      res.setGender(parseEnumeration(json.get("gender").getAsString(), RelatedPerson.AdministrativeGender.Null, new RelatedPerson.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGender());
    if (json.has("address"))
      res.setAddress(parseAddress(json.getAsJsonObject("address")));
    if (json.has("photo")) {
      JsonArray array = json.getAsJsonArray("photo");
      for (int i = 0; i < array.size(); i++) {
        res.getPhoto().add(parseAttachment(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private RiskAssessment parseRiskAssessment(JsonObject json) throws Exception {
    RiskAssessment res = new RiskAssessment();
    parseResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("condition"))
      res.setCondition(parseResourceReference(json.getAsJsonObject("condition")));
    if (json.has("performer"))
      res.setPerformer(parseResourceReference(json.getAsJsonObject("performer")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("basis")) {
      JsonArray array = json.getAsJsonArray("basis");
      for (int i = 0; i < array.size(); i++) {
        res.getBasis().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("prediction")) {
      JsonArray array = json.getAsJsonArray("prediction");
      for (int i = 0; i < array.size(); i++) {
        res.getPrediction().add(parseRiskAssessmentRiskAssessmentPredictionComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("mitigation"))
      res.setMitigation(parseString(json.get("mitigation").getAsString()));
    if (json.has("_mitigation"))
      parseElementProperties(json.getAsJsonObject("_mitigation"), res.getMitigation());
    return res;
  }

  private RiskAssessment.RiskAssessmentPredictionComponent parseRiskAssessmentRiskAssessmentPredictionComponent(JsonObject json, RiskAssessment owner) throws Exception {
    RiskAssessment.RiskAssessmentPredictionComponent res = new RiskAssessment.RiskAssessmentPredictionComponent();
    parseBackboneProperties(json, res);
    if (json.has("outcome"))
      res.setOutcome(parseCodeableConcept(json.getAsJsonObject("outcome")));
    Type probability = parseType("probability", json);
    if (probability != null)
      res.setProbability(probability);
    if (json.has("relativeRisk"))
      res.setRelativeRisk(parseDecimal(json.get("relativeRisk").getAsBigDecimal()));
    if (json.has("_relativeRisk"))
      parseElementProperties(json.getAsJsonObject("_relativeRisk"), res.getRelativeRisk());
    Type when = parseType("when", json);
    if (when != null)
      res.setWhen(when);
    if (json.has("rationale"))
      res.setRationale(parseString(json.get("rationale").getAsString()));
    if (json.has("_rationale"))
      parseElementProperties(json.getAsJsonObject("_rationale"), res.getRationale());
    return res;
  }

  private SecurityEvent parseSecurityEvent(JsonObject json) throws Exception {
    SecurityEvent res = new SecurityEvent();
    parseResourceProperties(json, res);
    if (json.has("event"))
      res.setEvent(parseSecurityEventSecurityEventEventComponent(json.getAsJsonObject("event"), res));
    if (json.has("participant")) {
      JsonArray array = json.getAsJsonArray("participant");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipant().add(parseSecurityEventSecurityEventParticipantComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("source"))
      res.setSource(parseSecurityEventSecurityEventSourceComponent(json.getAsJsonObject("source"), res));
    if (json.has("object")) {
      JsonArray array = json.getAsJsonArray("object");
      for (int i = 0; i < array.size(); i++) {
        res.getObject().add(parseSecurityEventSecurityEventObjectComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private SecurityEvent.SecurityEventEventComponent parseSecurityEventSecurityEventEventComponent(JsonObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventEventComponent res = new SecurityEvent.SecurityEventEventComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("subtype")) {
      JsonArray array = json.getAsJsonArray("subtype");
      for (int i = 0; i < array.size(); i++) {
        res.getSubtype().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("action"))
      res.setAction(parseEnumeration(json.get("action").getAsString(), SecurityEvent.SecurityEventAction.Null, new SecurityEvent.SecurityEventActionEnumFactory()));
    if (json.has("_action"))
      parseElementProperties(json.getAsJsonObject("_action"), res.getAction());
    if (json.has("dateTime"))
      res.setDateTime(parseInstant(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTime());
    if (json.has("outcome"))
      res.setOutcome(parseEnumeration(json.get("outcome").getAsString(), SecurityEvent.SecurityEventOutcome.Null, new SecurityEvent.SecurityEventOutcomeEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcome());
    if (json.has("outcomeDesc"))
      res.setOutcomeDesc(parseString(json.get("outcomeDesc").getAsString()));
    if (json.has("_outcomeDesc"))
      parseElementProperties(json.getAsJsonObject("_outcomeDesc"), res.getOutcomeDesc());
    return res;
  }

  private SecurityEvent.SecurityEventParticipantComponent parseSecurityEventSecurityEventParticipantComponent(JsonObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventParticipantComponent res = new SecurityEvent.SecurityEventParticipantComponent();
    parseBackboneProperties(json, res);
    if (json.has("role")) {
      JsonArray array = json.getAsJsonArray("role");
      for (int i = 0; i < array.size(); i++) {
        res.getRole().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("reference"))
      res.setReference(parseResourceReference(json.getAsJsonObject("reference")));
    if (json.has("userId"))
      res.setUserId(parseString(json.get("userId").getAsString()));
    if (json.has("_userId"))
      parseElementProperties(json.getAsJsonObject("_userId"), res.getUserId());
    if (json.has("altId"))
      res.setAltId(parseString(json.get("altId").getAsString()));
    if (json.has("_altId"))
      parseElementProperties(json.getAsJsonObject("_altId"), res.getAltId());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("requestor"))
      res.setRequestor(parseBoolean(json.get("requestor").getAsBoolean()));
    if (json.has("_requestor"))
      parseElementProperties(json.getAsJsonObject("_requestor"), res.getRequestor());
    if (json.has("media"))
      res.setMedia(parseCoding(json.getAsJsonObject("media")));
    if (json.has("network"))
      res.setNetwork(parseSecurityEventSecurityEventParticipantNetworkComponent(json.getAsJsonObject("network"), owner));
    return res;
  }

  private SecurityEvent.SecurityEventParticipantNetworkComponent parseSecurityEventSecurityEventParticipantNetworkComponent(JsonObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventParticipantNetworkComponent res = new SecurityEvent.SecurityEventParticipantNetworkComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), SecurityEvent.NetworkType.Null, new SecurityEvent.NetworkTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    return res;
  }

  private SecurityEvent.SecurityEventSourceComponent parseSecurityEventSecurityEventSourceComponent(JsonObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventSourceComponent res = new SecurityEvent.SecurityEventSourceComponent();
    parseBackboneProperties(json, res);
    if (json.has("site"))
      res.setSite(parseString(json.get("site").getAsString()));
    if (json.has("_site"))
      parseElementProperties(json.getAsJsonObject("_site"), res.getSite());
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private SecurityEvent.SecurityEventObjectComponent parseSecurityEventSecurityEventObjectComponent(JsonObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventObjectComponent res = new SecurityEvent.SecurityEventObjectComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("reference"))
      res.setReference(parseResourceReference(json.getAsJsonObject("reference")));
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), SecurityEvent.ObjectType.Null, new SecurityEvent.ObjectTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("role"))
      res.setRole(parseEnumeration(json.get("role").getAsString(), SecurityEvent.ObjectRole.Null, new SecurityEvent.ObjectRoleEnumFactory()));
    if (json.has("_role"))
      parseElementProperties(json.getAsJsonObject("_role"), res.getRole());
    if (json.has("lifecycle"))
      res.setLifecycle(parseEnumeration(json.get("lifecycle").getAsString(), SecurityEvent.ObjectLifecycle.Null, new SecurityEvent.ObjectLifecycleEnumFactory()));
    if (json.has("_lifecycle"))
      parseElementProperties(json.getAsJsonObject("_lifecycle"), res.getLifecycle());
    if (json.has("sensitivity"))
      res.setSensitivity(parseCodeableConcept(json.getAsJsonObject("sensitivity")));
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("query"))
      res.setQuery(parseBase64Binary(json.get("query").getAsString()));
    if (json.has("_query"))
      parseElementProperties(json.getAsJsonObject("_query"), res.getQuery());
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseSecurityEventSecurityEventObjectDetailComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private SecurityEvent.SecurityEventObjectDetailComponent parseSecurityEventSecurityEventObjectDetailComponent(JsonObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventObjectDetailComponent res = new SecurityEvent.SecurityEventObjectDetailComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseString(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("value"))
      res.setValue(parseBase64Binary(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    return res;
  }

  private Slot parseSlot(JsonObject json) throws Exception {
    Slot res = new Slot();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("availability"))
      res.setAvailability(parseResourceReference(json.getAsJsonObject("availability")));
    if (json.has("freeBusyType"))
      res.setFreeBusyType(parseEnumeration(json.get("freeBusyType").getAsString(), Slot.Slotstatus.Null, new Slot.SlotstatusEnumFactory()));
    if (json.has("_freeBusyType"))
      parseElementProperties(json.getAsJsonObject("_freeBusyType"), res.getFreeBusyType());
    if (json.has("start"))
      res.setStart(parseInstant(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStart());
    if (json.has("end"))
      res.setEnd(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEnd());
    if (json.has("overbooked"))
      res.setOverbooked(parseBoolean(json.get("overbooked").getAsBoolean()));
    if (json.has("_overbooked"))
      parseElementProperties(json.getAsJsonObject("_overbooked"), res.getOverbooked());
    if (json.has("comment"))
      res.setComment(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getComment());
    if (json.has("lastModified"))
      res.setLastModified(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModified());
    return res;
  }

  private Specimen parseSpecimen(JsonObject json) throws Exception {
    Specimen res = new Specimen();
    parseResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("source")) {
      JsonArray array = json.getAsJsonArray("source");
      for (int i = 0; i < array.size(); i++) {
        res.getSource().add(parseSpecimenSpecimenSourceComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseResourceReference(json.getAsJsonObject("subject")));
    if (json.has("accessionIdentifier"))
      res.setAccessionIdentifier(parseIdentifier(json.getAsJsonObject("accessionIdentifier")));
    if (json.has("receivedTime"))
      res.setReceivedTime(parseDateTime(json.get("receivedTime").getAsString()));
    if (json.has("_receivedTime"))
      parseElementProperties(json.getAsJsonObject("_receivedTime"), res.getReceivedTime());
    if (json.has("collection"))
      res.setCollection(parseSpecimenSpecimenCollectionComponent(json.getAsJsonObject("collection"), res));
    if (json.has("treatment")) {
      JsonArray array = json.getAsJsonArray("treatment");
      for (int i = 0; i < array.size(); i++) {
        res.getTreatment().add(parseSpecimenSpecimenTreatmentComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("container")) {
      JsonArray array = json.getAsJsonArray("container");
      for (int i = 0; i < array.size(); i++) {
        res.getContainer().add(parseSpecimenSpecimenContainerComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Specimen.SpecimenSourceComponent parseSpecimenSpecimenSourceComponent(JsonObject json, Specimen owner) throws Exception {
    Specimen.SpecimenSourceComponent res = new Specimen.SpecimenSourceComponent();
    parseBackboneProperties(json, res);
    if (json.has("relationship"))
      res.setRelationship(parseEnumeration(json.get("relationship").getAsString(), Specimen.HierarchicalRelationshipType.Null, new Specimen.HierarchicalRelationshipTypeEnumFactory()));
    if (json.has("_relationship"))
      parseElementProperties(json.getAsJsonObject("_relationship"), res.getRelationship());
    if (json.has("target")) {
      JsonArray array = json.getAsJsonArray("target");
      for (int i = 0; i < array.size(); i++) {
        res.getTarget().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Specimen.SpecimenCollectionComponent parseSpecimenSpecimenCollectionComponent(JsonObject json, Specimen owner) throws Exception {
    Specimen.SpecimenCollectionComponent res = new Specimen.SpecimenCollectionComponent();
    parseBackboneProperties(json, res);
    if (json.has("collector"))
      res.setCollector(parseResourceReference(json.getAsJsonObject("collector")));
    if (json.has("comment")) {
      JsonArray array = json.getAsJsonArray("comment");
      for (int i = 0; i < array.size(); i++) {
        res.getComment().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_comment")) {
      JsonArray array = json.getAsJsonArray("_comment");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getComment().size())
          res.getComment().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getComment().get(i));
      }
    };
    Type collected = parseType("collected", json);
    if (collected != null)
      res.setCollected(collected);
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("sourceSite"))
      res.setSourceSite(parseCodeableConcept(json.getAsJsonObject("sourceSite")));
    return res;
  }

  private Specimen.SpecimenTreatmentComponent parseSpecimenSpecimenTreatmentComponent(JsonObject json, Specimen owner) throws Exception {
    Specimen.SpecimenTreatmentComponent res = new Specimen.SpecimenTreatmentComponent();
    parseBackboneProperties(json, res);
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("procedure"))
      res.setProcedure(parseCodeableConcept(json.getAsJsonObject("procedure")));
    if (json.has("additive")) {
      JsonArray array = json.getAsJsonArray("additive");
      for (int i = 0; i < array.size(); i++) {
        res.getAdditive().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Specimen.SpecimenContainerComponent parseSpecimenSpecimenContainerComponent(JsonObject json, Specimen owner) throws Exception {
    Specimen.SpecimenContainerComponent res = new Specimen.SpecimenContainerComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("capacity"))
      res.setCapacity(parseQuantity(json.getAsJsonObject("capacity")));
    if (json.has("specimenQuantity"))
      res.setSpecimenQuantity(parseQuantity(json.getAsJsonObject("specimenQuantity")));
    if (json.has("additive"))
      res.setAdditive(parseResourceReference(json.getAsJsonObject("additive")));
    return res;
  }

  private Subscription parseSubscription(JsonObject json) throws Exception {
    Subscription res = new Subscription();
    parseResourceProperties(json, res);
    if (json.has("criteria"))
      res.setCriteria(parseString(json.get("criteria").getAsString()));
    if (json.has("_criteria"))
      parseElementProperties(json.getAsJsonObject("_criteria"), res.getCriteria());
    if (json.has("contact")) {
      JsonArray array = json.getAsJsonArray("contact");
      for (int i = 0; i < array.size(); i++) {
        res.getContact().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("reason"))
      res.setReason(parseString(json.get("reason").getAsString()));
    if (json.has("_reason"))
      parseElementProperties(json.getAsJsonObject("_reason"), res.getReason());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Subscription.SubscriptionStatus.Null, new Subscription.SubscriptionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("error"))
      res.setError(parseString(json.get("error").getAsString()));
    if (json.has("_error"))
      parseElementProperties(json.getAsJsonObject("_error"), res.getError());
    if (json.has("channel"))
      res.setChannel(parseSubscriptionSubscriptionChannelComponent(json.getAsJsonObject("channel"), res));
    if (json.has("end"))
      res.setEnd(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEnd());
    if (json.has("tag")) {
      JsonArray array = json.getAsJsonArray("tag");
      for (int i = 0; i < array.size(); i++) {
        res.getTag().add(parseSubscriptionSubscriptionTagComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Subscription.SubscriptionChannelComponent parseSubscriptionSubscriptionChannelComponent(JsonObject json, Subscription owner) throws Exception {
    Subscription.SubscriptionChannelComponent res = new Subscription.SubscriptionChannelComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseEnumeration(json.get("type").getAsString(), Subscription.SubscriptionChannelType.Null, new Subscription.SubscriptionChannelTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getType());
    if (json.has("url"))
      res.setUrl(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrl());
    if (json.has("payload"))
      res.setPayload(parseString(json.get("payload").getAsString()));
    if (json.has("_payload"))
      parseElementProperties(json.getAsJsonObject("_payload"), res.getPayload());
    if (json.has("header"))
      res.setHeader(parseString(json.get("header").getAsString()));
    if (json.has("_header"))
      parseElementProperties(json.getAsJsonObject("_header"), res.getHeader());
    return res;
  }

  private Subscription.SubscriptionTagComponent parseSubscriptionSubscriptionTagComponent(JsonObject json, Subscription owner) throws Exception {
    Subscription.SubscriptionTagComponent res = new Subscription.SubscriptionTagComponent();
    parseBackboneProperties(json, res);
    if (json.has("term"))
      res.setTerm(parseUri(json.get("term").getAsString()));
    if (json.has("_term"))
      parseElementProperties(json.getAsJsonObject("_term"), res.getTerm());
    if (json.has("scheme"))
      res.setScheme(parseUri(json.get("scheme").getAsString()));
    if (json.has("_scheme"))
      parseElementProperties(json.getAsJsonObject("_scheme"), res.getScheme());
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    return res;
  }

  private Substance parseSubstance(JsonObject json) throws Exception {
    Substance res = new Substance();
    parseResourceProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("instance"))
      res.setInstance(parseSubstanceSubstanceInstanceComponent(json.getAsJsonObject("instance"), res));
    if (json.has("ingredient")) {
      JsonArray array = json.getAsJsonArray("ingredient");
      for (int i = 0; i < array.size(); i++) {
        res.getIngredient().add(parseSubstanceSubstanceIngredientComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Substance.SubstanceInstanceComponent parseSubstanceSubstanceInstanceComponent(JsonObject json, Substance owner) throws Exception {
    Substance.SubstanceInstanceComponent res = new Substance.SubstanceInstanceComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("expiry"))
      res.setExpiry(parseDateTime(json.get("expiry").getAsString()));
    if (json.has("_expiry"))
      parseElementProperties(json.getAsJsonObject("_expiry"), res.getExpiry());
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    return res;
  }

  private Substance.SubstanceIngredientComponent parseSubstanceSubstanceIngredientComponent(JsonObject json, Substance owner) throws Exception {
    Substance.SubstanceIngredientComponent res = new Substance.SubstanceIngredientComponent();
    parseBackboneProperties(json, res);
    if (json.has("quantity"))
      res.setQuantity(parseRatio(json.getAsJsonObject("quantity")));
    if (json.has("substance"))
      res.setSubstance(parseResourceReference(json.getAsJsonObject("substance")));
    return res;
  }

  private Supply parseSupply(JsonObject json) throws Exception {
    Supply res = new Supply();
    parseResourceProperties(json, res);
    if (json.has("kind"))
      res.setKind(parseCodeableConcept(json.getAsJsonObject("kind")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Supply.ValuesetSupplyStatus.Null, new Supply.ValuesetSupplyStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("orderedItem"))
      res.setOrderedItem(parseResourceReference(json.getAsJsonObject("orderedItem")));
    if (json.has("patient"))
      res.setPatient(parseResourceReference(json.getAsJsonObject("patient")));
    if (json.has("dispense")) {
      JsonArray array = json.getAsJsonArray("dispense");
      for (int i = 0; i < array.size(); i++) {
        res.getDispense().add(parseSupplySupplyDispenseComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Supply.SupplyDispenseComponent parseSupplySupplyDispenseComponent(JsonObject json, Supply owner) throws Exception {
    Supply.SupplyDispenseComponent res = new Supply.SupplyDispenseComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), Supply.ValuesetSupplyDispenseStatus.Null, new Supply.ValuesetSupplyDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("suppliedItem"))
      res.setSuppliedItem(parseResourceReference(json.getAsJsonObject("suppliedItem")));
    if (json.has("supplier"))
      res.setSupplier(parseResourceReference(json.getAsJsonObject("supplier")));
    if (json.has("whenPrepared"))
      res.setWhenPrepared(parsePeriod(json.getAsJsonObject("whenPrepared")));
    if (json.has("whenHandedOver"))
      res.setWhenHandedOver(parsePeriod(json.getAsJsonObject("whenHandedOver")));
    if (json.has("destination"))
      res.setDestination(parseResourceReference(json.getAsJsonObject("destination")));
    if (json.has("receiver")) {
      JsonArray array = json.getAsJsonArray("receiver");
      for (int i = 0; i < array.size(); i++) {
        res.getReceiver().add(parseResourceReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private ValueSet parseValueSet(JsonObject json) throws Exception {
    ValueSet res = new ValueSet();
    parseResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifier());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("name"))
      res.setName(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getName());
    if (json.has("purpose"))
      res.setPurpose(parseString(json.get("purpose").getAsString()));
    if (json.has("_purpose"))
      parseElementProperties(json.getAsJsonObject("_purpose"), res.getPurpose());
    if (json.has("immutable"))
      res.setImmutable(parseBoolean(json.get("immutable").getAsBoolean()));
    if (json.has("_immutable"))
      parseElementProperties(json.getAsJsonObject("_immutable"), res.getImmutable());
    if (json.has("publisher"))
      res.setPublisher(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisher());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContact(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescription(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescription());
    if (json.has("copyright"))
      res.setCopyright(parseString(json.get("copyright").getAsString()));
    if (json.has("_copyright"))
      parseElementProperties(json.getAsJsonObject("_copyright"), res.getCopyright());
    if (json.has("status"))
      res.setStatus(parseEnumeration(json.get("status").getAsString(), ValueSet.ValuesetStatus.Null, new ValueSet.ValuesetStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatus());
    if (json.has("experimental"))
      res.setExperimental(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimental());
    if (json.has("extensible"))
      res.setExtensible(parseBoolean(json.get("extensible").getAsBoolean()));
    if (json.has("_extensible"))
      parseElementProperties(json.getAsJsonObject("_extensible"), res.getExtensible());
    if (json.has("date"))
      res.setDate(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDate());
    if (json.has("stableDate"))
      res.setStableDate(parseDate(json.get("stableDate").getAsString()));
    if (json.has("_stableDate"))
      parseElementProperties(json.getAsJsonObject("_stableDate"), res.getStableDate());
    if (json.has("define"))
      res.setDefine(parseValueSetValueSetDefineComponent(json.getAsJsonObject("define"), res));
    if (json.has("compose"))
      res.setCompose(parseValueSetValueSetComposeComponent(json.getAsJsonObject("compose"), res));
    if (json.has("expansion"))
      res.setExpansion(parseValueSetValueSetExpansionComponent(json.getAsJsonObject("expansion"), res));
    return res;
  }

  private ValueSet.ValueSetDefineComponent parseValueSetValueSetDefineComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetDefineComponent res = new ValueSet.ValueSetDefineComponent();
    parseBackboneProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("caseSensitive"))
      res.setCaseSensitive(parseBoolean(json.get("caseSensitive").getAsBoolean()));
    if (json.has("_caseSensitive"))
      parseElementProperties(json.getAsJsonObject("_caseSensitive"), res.getCaseSensitive());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseValueSetValueSetDefineConceptComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ValueSetDefineConceptComponent parseValueSetValueSetDefineConceptComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetDefineConceptComponent res = new ValueSet.ValueSetDefineConceptComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("abstract"))
      res.setAbstract(parseBoolean(json.get("abstract").getAsBoolean()));
    if (json.has("_abstract"))
      parseElementProperties(json.getAsJsonObject("_abstract"), res.getAbstract());
    if (json.has("display"))
      res.setDisplay(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplay());
    if (json.has("definition"))
      res.setDefinition(parseString(json.get("definition").getAsString()));
    if (json.has("_definition"))
      parseElementProperties(json.getAsJsonObject("_definition"), res.getDefinition());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseValueSetValueSetDefineConceptComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ValueSetComposeComponent parseValueSetValueSetComposeComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetComposeComponent res = new ValueSet.ValueSetComposeComponent();
    parseBackboneProperties(json, res);
    if (json.has("import")) {
      JsonArray array = json.getAsJsonArray("import");
      for (int i = 0; i < array.size(); i++) {
        res.getImport().add(parseUri(array.get(i).getAsString()));
      }
    };
    if (json.has("_import")) {
      JsonArray array = json.getAsJsonArray("_import");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getImport().size())
          res.getImport().add(parseUri(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getImport().get(i));
      }
    };
    if (json.has("include")) {
      JsonArray array = json.getAsJsonArray("include");
      for (int i = 0; i < array.size(); i++) {
        res.getInclude().add(parseValueSetConceptSetComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("exclude")) {
      JsonArray array = json.getAsJsonArray("exclude");
      for (int i = 0; i < array.size(); i++) {
        res.getExclude().add(parseValueSetConceptSetComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ConceptSetComponent parseValueSetConceptSetComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptSetComponent res = new ValueSet.ConceptSetComponent();
    parseBackboneProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("version"))
      res.setVersion(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersion());
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCode(array.get(i).getAsString()));
      }
    };
    if (json.has("_code")) {
      JsonArray array = json.getAsJsonArray("_code");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getCode().size())
          res.getCode().add(parseCode(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getCode().get(i));
      }
    };
    if (json.has("filter")) {
      JsonArray array = json.getAsJsonArray("filter");
      for (int i = 0; i < array.size(); i++) {
        res.getFilter().add(parseValueSetConceptSetFilterComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ConceptSetFilterComponent parseValueSetConceptSetFilterComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptSetFilterComponent res = new ValueSet.ConceptSetFilterComponent();
    parseBackboneProperties(json, res);
    if (json.has("property"))
      res.setProperty(parseCode(json.get("property").getAsString()));
    if (json.has("_property"))
      parseElementProperties(json.getAsJsonObject("_property"), res.getProperty());
    if (json.has("op"))
      res.setOp(parseEnumeration(json.get("op").getAsString(), ValueSet.FilterOperator.Null, new ValueSet.FilterOperatorEnumFactory()));
    if (json.has("_op"))
      parseElementProperties(json.getAsJsonObject("_op"), res.getOp());
    if (json.has("value"))
      res.setValue(parseCode(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValue());
    return res;
  }

  private ValueSet.ValueSetExpansionComponent parseValueSetValueSetExpansionComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionComponent res = new ValueSet.ValueSetExpansionComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("timestamp"))
      res.setTimestamp(parseInstant(json.get("timestamp").getAsString()));
    if (json.has("_timestamp"))
      parseElementProperties(json.getAsJsonObject("_timestamp"), res.getTimestamp());
    if (json.has("contains")) {
      JsonArray array = json.getAsJsonArray("contains");
      for (int i = 0; i < array.size(); i++) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ValueSetExpansionContainsComponent parseValueSetValueSetExpansionContainsComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionContainsComponent res = new ValueSet.ValueSetExpansionContainsComponent();
    parseBackboneProperties(json, res);
    if (json.has("system"))
      res.setSystem(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystem());
    if (json.has("code"))
      res.setCode(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCode());
    if (json.has("display"))
      res.setDisplay(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplay());
    if (json.has("contains")) {
      JsonArray array = json.getAsJsonArray("contains");
      for (int i = 0; i < array.size(); i++) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  @Override
  protected Resource parseResource(JsonObject json) throws Exception {
    String t = json.get("resourceType").getAsString();
    if (Utilities.noString(t))
      throw new Exception("Unable to find resource type - maybe not a FHIR resource?");
    if (t.equals("AdverseReaction"))
      return parseAdverseReaction(json);
    else if (t.equals("Alert"))
      return parseAlert(json);
    else if (t.equals("AllergyIntolerance"))
      return parseAllergyIntolerance(json);
    else if (t.equals("Appointment"))
      return parseAppointment(json);
    else if (t.equals("AppointmentResponse"))
      return parseAppointmentResponse(json);
    else if (t.equals("Availability"))
      return parseAvailability(json);
    else if (t.equals("CarePlan"))
      return parseCarePlan(json);
    else if (t.equals("Composition"))
      return parseComposition(json);
    else if (t.equals("ConceptMap"))
      return parseConceptMap(json);
    else if (t.equals("Condition"))
      return parseCondition(json);
    else if (t.equals("Conformance"))
      return parseConformance(json);
    else if (t.equals("Contraindication"))
      return parseContraindication(json);
    else if (t.equals("DataElement"))
      return parseDataElement(json);
    else if (t.equals("Device"))
      return parseDevice(json);
    else if (t.equals("DeviceObservationReport"))
      return parseDeviceObservationReport(json);
    else if (t.equals("DiagnosticOrder"))
      return parseDiagnosticOrder(json);
    else if (t.equals("DiagnosticReport"))
      return parseDiagnosticReport(json);
    else if (t.equals("DocumentManifest"))
      return parseDocumentManifest(json);
    else if (t.equals("DocumentReference"))
      return parseDocumentReference(json);
    else if (t.equals("Encounter"))
      return parseEncounter(json);
    else if (t.equals("FamilyHistory"))
      return parseFamilyHistory(json);
    else if (t.equals("Group"))
      return parseGroup(json);
    else if (t.equals("ImagingStudy"))
      return parseImagingStudy(json);
    else if (t.equals("Immunization"))
      return parseImmunization(json);
    else if (t.equals("ImmunizationRecommendation"))
      return parseImmunizationRecommendation(json);
    else if (t.equals("List"))
      return parseList_(json);
    else if (t.equals("Location"))
      return parseLocation(json);
    else if (t.equals("Media"))
      return parseMedia(json);
    else if (t.equals("Medication"))
      return parseMedication(json);
    else if (t.equals("MedicationAdministration"))
      return parseMedicationAdministration(json);
    else if (t.equals("MedicationDispense"))
      return parseMedicationDispense(json);
    else if (t.equals("MedicationPrescription"))
      return parseMedicationPrescription(json);
    else if (t.equals("MedicationStatement"))
      return parseMedicationStatement(json);
    else if (t.equals("MessageHeader"))
      return parseMessageHeader(json);
    else if (t.equals("Namespace"))
      return parseNamespace(json);
    else if (t.equals("Observation"))
      return parseObservation(json);
    else if (t.equals("OperationDefinition"))
      return parseOperationDefinition(json);
    else if (t.equals("OperationOutcome"))
      return parseOperationOutcome(json);
    else if (t.equals("Order"))
      return parseOrder(json);
    else if (t.equals("OrderResponse"))
      return parseOrderResponse(json);
    else if (t.equals("Organization"))
      return parseOrganization(json);
    else if (t.equals("Other"))
      return parseOther(json);
    else if (t.equals("Patient"))
      return parsePatient(json);
    else if (t.equals("Practitioner"))
      return parsePractitioner(json);
    else if (t.equals("Procedure"))
      return parseProcedure(json);
    else if (t.equals("Profile"))
      return parseProfile(json);
    else if (t.equals("Provenance"))
      return parseProvenance(json);
    else if (t.equals("Query"))
      return parseQuery(json);
    else if (t.equals("Questionnaire"))
      return parseQuestionnaire(json);
    else if (t.equals("QuestionnaireAnswers"))
      return parseQuestionnaireAnswers(json);
    else if (t.equals("ReferralRequest"))
      return parseReferralRequest(json);
    else if (t.equals("RelatedPerson"))
      return parseRelatedPerson(json);
    else if (t.equals("RiskAssessment"))
      return parseRiskAssessment(json);
    else if (t.equals("SecurityEvent"))
      return parseSecurityEvent(json);
    else if (t.equals("Slot"))
      return parseSlot(json);
    else if (t.equals("Specimen"))
      return parseSpecimen(json);
    else if (t.equals("Subscription"))
      return parseSubscription(json);
    else if (t.equals("Substance"))
      return parseSubstance(json);
    else if (t.equals("Supply"))
      return parseSupply(json);
    else if (t.equals("ValueSet"))
      return parseValueSet(json);
    else if (t.equals("Binary"))
      return parseBinary(json);
    throw new Exception("Unknown.Unrecognised resource type");
  }

  protected Type parseType(String prefix, JsonObject json) throws Exception {
    if (json.has(prefix+"Period"))
      return parsePeriod(json.getAsJsonObject(prefix+"Period"));
    else if (json.has(prefix+"Coding"))
      return parseCoding(json.getAsJsonObject(prefix+"Coding"));
    else if (json.has(prefix+"Range"))
      return parseRange(json.getAsJsonObject(prefix+"Range"));
    else if (json.has(prefix+"Quantity"))
      return parseQuantity(json.getAsJsonObject(prefix+"Quantity"));
    else if (json.has(prefix+"Attachment"))
      return parseAttachment(json.getAsJsonObject(prefix+"Attachment"));
    else if (json.has(prefix+"Ratio"))
      return parseRatio(json.getAsJsonObject(prefix+"Ratio"));
    else if (json.has(prefix+"SampledData"))
      return parseSampledData(json.getAsJsonObject(prefix+"SampledData"));
    else if (json.has(prefix+"Resource"))
      return parseResourceReference(json.getAsJsonObject(prefix+"Resource"));
    else if (json.has(prefix+"CodeableConcept"))
      return parseCodeableConcept(json.getAsJsonObject(prefix+"CodeableConcept"));
    else if (json.has(prefix+"Identifier"))
      return parseIdentifier(json.getAsJsonObject(prefix+"Identifier"));
    else if (json.has(prefix+"Age"))
      return parseAge(json.getAsJsonObject(prefix+"Age"));
    else if (json.has(prefix+"Count"))
      return parseCount(json.getAsJsonObject(prefix+"Count"));
    else if (json.has(prefix+"Money"))
      return parseMoney(json.getAsJsonObject(prefix+"Money"));
    else if (json.has(prefix+"Distance"))
      return parseDistance(json.getAsJsonObject(prefix+"Distance"));
    else if (json.has(prefix+"Duration"))
      return parseDuration(json.getAsJsonObject(prefix+"Duration"));
    else if (json.has(prefix+"Schedule"))
      return parseSchedule(json.getAsJsonObject(prefix+"Schedule"));
    else if (json.has(prefix+"Contact"))
      return parseContact(json.getAsJsonObject(prefix+"Contact"));
    else if (json.has(prefix+"Address"))
      return parseAddress(json.getAsJsonObject(prefix+"Address"));
    else if (json.has(prefix+"HumanName"))
      return parseHumanName(json.getAsJsonObject(prefix+"HumanName"));
    else if (json.has(prefix+"Integer") || json.has("_"+prefix+"Integer")) {
      Type t = json.has(prefix+"Integer") ? parseInteger(json.get(prefix+"Integer").getAsLong()) : new IntegerType();
      if (json.has("_"+prefix+"Integer"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Integer"), t);
      return t;
    }
    else if (json.has(prefix+"DateTime") || json.has("_"+prefix+"DateTime")) {
      Type t = json.has(prefix+"DateTime") ? parseDateTime(json.get(prefix+"DateTime").getAsString()) : new DateTimeType();
      if (json.has("_"+prefix+"DateTime"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"DateTime"), t);
      return t;
    }
    else if (json.has(prefix+"Code") || json.has("_"+prefix+"Code")) {
      Type t = json.has(prefix+"Code") ? parseCode(json.get(prefix+"Code").getAsString()) : new CodeType();
      if (json.has("_"+prefix+"Code"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Code"), t);
      return t;
    }
    else if (json.has(prefix+"Date") || json.has("_"+prefix+"Date")) {
      Type t = json.has(prefix+"Date") ? parseDate(json.get(prefix+"Date").getAsString()) : new DateType();
      if (json.has("_"+prefix+"Date"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Date"), t);
      return t;
    }
    else if (json.has(prefix+"Decimal") || json.has("_"+prefix+"Decimal")) {
      Type t = json.has(prefix+"Decimal") ? parseDecimal(json.get(prefix+"Decimal").getAsBigDecimal()) : new DecimalType();
      if (json.has("_"+prefix+"Decimal"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Decimal"), t);
      return t;
    }
    else if (json.has(prefix+"Uri") || json.has("_"+prefix+"Uri")) {
      Type t = json.has(prefix+"Uri") ? parseUri(json.get(prefix+"Uri").getAsString()) : new UriType();
      if (json.has("_"+prefix+"Uri"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Uri"), t);
      return t;
    }
    else if (json.has(prefix+"Id") || json.has("_"+prefix+"Id")) {
      Type t = json.has(prefix+"Id") ? parseId(json.get(prefix+"Id").getAsString()) : new IdType();
      if (json.has("_"+prefix+"Id"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Id"), t);
      return t;
    }
    else if (json.has(prefix+"Base64Binary") || json.has("_"+prefix+"Base64Binary")) {
      Type t = json.has(prefix+"Base64Binary") ? parseBase64Binary(json.get(prefix+"Base64Binary").getAsString()) : new Base64BinaryType();
      if (json.has("_"+prefix+"Base64Binary"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Base64Binary"), t);
      return t;
    }
    else if (json.has(prefix+"Time") || json.has("_"+prefix+"Time")) {
      Type t = json.has(prefix+"Time") ? parseTime(json.get(prefix+"Time").getAsString()) : new TimeType();
      if (json.has("_"+prefix+"Time"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Time"), t);
      return t;
    }
    else if (json.has(prefix+"Oid") || json.has("_"+prefix+"Oid")) {
      Type t = json.has(prefix+"Oid") ? parseOid(json.get(prefix+"Oid").getAsString()) : new OidType();
      if (json.has("_"+prefix+"Oid"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Oid"), t);
      return t;
    }
    else if (json.has(prefix+"String") || json.has("_"+prefix+"String")) {
      Type t = json.has(prefix+"String") ? parseString(json.get(prefix+"String").getAsString()) : new StringType();
      if (json.has("_"+prefix+"String"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"String"), t);
      return t;
    }
    else if (json.has(prefix+"Boolean") || json.has("_"+prefix+"Boolean")) {
      Type t = json.has(prefix+"Boolean") ? parseBoolean(json.get(prefix+"Boolean").getAsBoolean()) : new BooleanType();
      if (json.has("_"+prefix+"Boolean"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Boolean"), t);
      return t;
    }
    else if (json.has(prefix+"Uuid") || json.has("_"+prefix+"Uuid")) {
      Type t = json.has(prefix+"Uuid") ? parseUuid(json.get(prefix+"Uuid").getAsString()) : new UuidType();
      if (json.has("_"+prefix+"Uuid"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Uuid"), t);
      return t;
    }
    else if (json.has(prefix+"Instant") || json.has("_"+prefix+"Instant")) {
      Type t = json.has(prefix+"Instant") ? parseInstant(json.get(prefix+"Instant").getAsString()) : new InstantType();
      if (json.has("_"+prefix+"Instant"))
        parseElementProperties(json.getAsJsonObject("_"+prefix+"Instant"), t);
      return t;
    }
    return null;
  }

}

