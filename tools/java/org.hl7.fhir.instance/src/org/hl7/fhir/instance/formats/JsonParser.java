package org.hl7.fhir.instance.formats;

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
    parseExtensions(json, element.getExtension(), false);
  }

  protected void parseBackboneProperties(JsonObject json, BackboneElement element) throws Exception {
    parseElementProperties(json, element);
    if (json != null && json.has("modifier")) {
      JsonObject obj = json.getAsJsonObject("modifier");
      parseExtensions(obj, element.getModifierExtension(), false);
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
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    return res;
  }

  private Narrative parseNarrative(JsonObject json) throws Exception {
    Narrative res = new Narrative();
    parseElementProperties(json, res);
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Narrative.NarrativeStatus.NULL, new Narrative.NarrativeStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("div"))
      res.setDiv(parseXhtml(json.get("div").getAsString()));
    return res;
  }

  private Period parsePeriod(JsonObject json) throws Exception {
    Period res = new Period();
    parseTypeProperties(json, res);
    if (json.has("start"))
      res.setStartElement(parseDateTime(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStartElement());
    if (json.has("end"))
      res.setEndElement(parseDateTime(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEndElement());
    return res;
  }

  private Coding parseCoding(JsonObject json) throws Exception {
    Coding res = new Coding();
    parseTypeProperties(json, res);
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
    if (json.has("primary"))
      res.setPrimaryElement(parseBoolean(json.get("primary").getAsBoolean()));
    if (json.has("_primary"))
      parseElementProperties(json.getAsJsonObject("_primary"), res.getPrimaryElement());
    if (json.has("valueSet"))
      res.setValueSet(parseReference(json.getAsJsonObject("valueSet")));
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
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("comparator"))
      res.setComparatorElement(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparatorElement());
    if (json.has("units"))
      res.setUnitsElement(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnitsElement());
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    return res;
  }

  private Attachment parseAttachment(JsonObject json) throws Exception {
    Attachment res = new Attachment();
    parseTypeProperties(json, res);
    if (json.has("contentType"))
      res.setContentTypeElement(parseCode(json.get("contentType").getAsString()));
    if (json.has("_contentType"))
      parseElementProperties(json.getAsJsonObject("_contentType"), res.getContentTypeElement());
    if (json.has("language"))
      res.setLanguageElement(parseCode(json.get("language").getAsString()));
    if (json.has("_language"))
      parseElementProperties(json.getAsJsonObject("_language"), res.getLanguageElement());
    if (json.has("data"))
      res.setDataElement(parseBase64Binary(json.get("data").getAsString()));
    if (json.has("_data"))
      parseElementProperties(json.getAsJsonObject("_data"), res.getDataElement());
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    if (json.has("size"))
      res.setSizeElement(parseInteger(json.get("size").getAsLong()));
    if (json.has("_size"))
      parseElementProperties(json.getAsJsonObject("_size"), res.getSizeElement());
    if (json.has("hash"))
      res.setHashElement(parseBase64Binary(json.get("hash").getAsString()));
    if (json.has("_hash"))
      parseElementProperties(json.getAsJsonObject("_hash"), res.getHashElement());
    if (json.has("title"))
      res.setTitleElement(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitleElement());
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
      res.setPeriodElement(parseDecimal(json.get("period").getAsBigDecimal()));
    if (json.has("_period"))
      parseElementProperties(json.getAsJsonObject("_period"), res.getPeriodElement());
    if (json.has("factor"))
      res.setFactorElement(parseDecimal(json.get("factor").getAsBigDecimal()));
    if (json.has("_factor"))
      parseElementProperties(json.getAsJsonObject("_factor"), res.getFactorElement());
    if (json.has("lowerLimit"))
      res.setLowerLimitElement(parseDecimal(json.get("lowerLimit").getAsBigDecimal()));
    if (json.has("_lowerLimit"))
      parseElementProperties(json.getAsJsonObject("_lowerLimit"), res.getLowerLimitElement());
    if (json.has("upperLimit"))
      res.setUpperLimitElement(parseDecimal(json.get("upperLimit").getAsBigDecimal()));
    if (json.has("_upperLimit"))
      parseElementProperties(json.getAsJsonObject("_upperLimit"), res.getUpperLimitElement());
    if (json.has("dimensions"))
      res.setDimensionsElement(parseInteger(json.get("dimensions").getAsLong()));
    if (json.has("_dimensions"))
      parseElementProperties(json.getAsJsonObject("_dimensions"), res.getDimensionsElement());
    if (json.has("data"))
      res.setDataElement(parseString(json.get("data").getAsString()));
    if (json.has("_data"))
      parseElementProperties(json.getAsJsonObject("_data"), res.getDataElement());
    return res;
  }

  private Reference parseReference(JsonObject json) throws Exception {
    Reference res = new Reference();
    parseTypeProperties(json, res);
    if (json.has("reference"))
      res.setReferenceElement(parseString(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReferenceElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
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
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    return res;
  }

  private Identifier parseIdentifier(JsonObject json) throws Exception {
    Identifier res = new Identifier();
    parseTypeProperties(json, res);
    if (json.has("use"))
      res.setUseElement(parseEnumeration(json.get("use").getAsString(), Identifier.IdentifierUse.NULL, new Identifier.IdentifierUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUseElement());
    if (json.has("label"))
      res.setLabelElement(parseString(json.get("label").getAsString()));
    if (json.has("_label"))
      parseElementProperties(json.getAsJsonObject("_label"), res.getLabelElement());
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("value"))
      res.setValueElement(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("assigner"))
      res.setAssigner(parseReference(json.getAsJsonObject("assigner")));
    return res;
  }

  private Age parseAge(JsonObject json) throws Exception {
    Age res = new Age();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("comparator"))
      res.setComparatorElement(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparatorElement());
    if (json.has("units"))
      res.setUnitsElement(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnitsElement());
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    return res;
  }

  private Count parseCount(JsonObject json) throws Exception {
    Count res = new Count();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("comparator"))
      res.setComparatorElement(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparatorElement());
    if (json.has("units"))
      res.setUnitsElement(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnitsElement());
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    return res;
  }

  private Money parseMoney(JsonObject json) throws Exception {
    Money res = new Money();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("comparator"))
      res.setComparatorElement(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparatorElement());
    if (json.has("units"))
      res.setUnitsElement(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnitsElement());
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    return res;
  }

  private Distance parseDistance(JsonObject json) throws Exception {
    Distance res = new Distance();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("comparator"))
      res.setComparatorElement(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparatorElement());
    if (json.has("units"))
      res.setUnitsElement(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnitsElement());
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    return res;
  }

  private Duration parseDuration(JsonObject json) throws Exception {
    Duration res = new Duration();
    parseElementProperties(json, res);
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("comparator"))
      res.setComparatorElement(parseEnumeration(json.get("comparator").getAsString(), Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
    if (json.has("_comparator"))
      parseElementProperties(json.getAsJsonObject("_comparator"), res.getComparatorElement());
    if (json.has("units"))
      res.setUnitsElement(parseString(json.get("units").getAsString()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnitsElement());
    if (json.has("system"))
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    return res;
  }

  private ElementDefinition parseElementDefinition(JsonObject json) throws Exception {
    ElementDefinition res = new ElementDefinition();
    parseElementProperties(json, res);
    if (json.has("path"))
      res.setPathElement(parseString(json.get("path").getAsString()));
    if (json.has("_path"))
      parseElementProperties(json.getAsJsonObject("_path"), res.getPathElement());
    if (json.has("representation")) {
      JsonArray array = json.getAsJsonArray("representation");
      for (int i = 0; i < array.size(); i++) {
        res.getRepresentation().add(parseEnumeration(array.get(i).getAsString(), ElementDefinition.PropertyRepresentation.NULL, new ElementDefinition.PropertyRepresentationEnumFactory()));
      }
    };
    if (json.has("_representation")) {
      JsonArray array = json.getAsJsonArray("_representation");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getRepresentation().size())
          res.getRepresentation().add(parseEnumeration(null, ElementDefinition.PropertyRepresentation.NULL, new ElementDefinition.PropertyRepresentationEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getRepresentation().get(i));
      }
    };
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("slicing"))
      res.setSlicing(parseElementDefinitionElementDefinitionSlicingComponent(json.getAsJsonObject("slicing"), res));
    if (json.has("short"))
      res.setShortElement(parseString(json.get("short").getAsString()));
    if (json.has("_short"))
      parseElementProperties(json.getAsJsonObject("_short"), res.getShortElement());
    if (json.has("formal"))
      res.setFormalElement(parseString(json.get("formal").getAsString()));
    if (json.has("_formal"))
      parseElementProperties(json.getAsJsonObject("_formal"), res.getFormalElement());
    if (json.has("comments"))
      res.setCommentsElement(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getCommentsElement());
    if (json.has("requirements"))
      res.setRequirementsElement(parseString(json.get("requirements").getAsString()));
    if (json.has("_requirements"))
      parseElementProperties(json.getAsJsonObject("_requirements"), res.getRequirementsElement());
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
      res.setMinElement(parseInteger(json.get("min").getAsLong()));
    if (json.has("_min"))
      parseElementProperties(json.getAsJsonObject("_min"), res.getMinElement());
    if (json.has("max"))
      res.setMaxElement(parseString(json.get("max").getAsString()));
    if (json.has("_max"))
      parseElementProperties(json.getAsJsonObject("_max"), res.getMaxElement());
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseElementDefinitionTypeRefComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("nameReference"))
      res.setNameReferenceElement(parseString(json.get("nameReference").getAsString()));
    if (json.has("_nameReference"))
      parseElementProperties(json.getAsJsonObject("_nameReference"), res.getNameReferenceElement());
    Type fixed = parseType("fixed", json);
    if (fixed != null)
      res.setFixed(fixed);
    Type pattern = parseType("pattern", json);
    if (pattern != null)
      res.setPattern(pattern);
    Type example = parseType("example", json);
    if (example != null)
      res.setExample(example);
    if (json.has("maxLength"))
      res.setMaxLengthElement(parseInteger(json.get("maxLength").getAsLong()));
    if (json.has("_maxLength"))
      parseElementProperties(json.getAsJsonObject("_maxLength"), res.getMaxLengthElement());
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
        res.getConstraint().add(parseElementDefinitionElementDefinitionConstraintComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("mustSupport"))
      res.setMustSupportElement(parseBoolean(json.get("mustSupport").getAsBoolean()));
    if (json.has("_mustSupport"))
      parseElementProperties(json.getAsJsonObject("_mustSupport"), res.getMustSupportElement());
    if (json.has("isModifier"))
      res.setIsModifierElement(parseBoolean(json.get("isModifier").getAsBoolean()));
    if (json.has("_isModifier"))
      parseElementProperties(json.getAsJsonObject("_isModifier"), res.getIsModifierElement());
    if (json.has("isSummary"))
      res.setIsSummaryElement(parseBoolean(json.get("isSummary").getAsBoolean()));
    if (json.has("_isSummary"))
      parseElementProperties(json.getAsJsonObject("_isSummary"), res.getIsSummaryElement());
    if (json.has("binding"))
      res.setBinding(parseElementDefinitionElementDefinitionBindingComponent(json.getAsJsonObject("binding"), res));
    if (json.has("mapping")) {
      JsonArray array = json.getAsJsonArray("mapping");
      for (int i = 0; i < array.size(); i++) {
        res.getMapping().add(parseElementDefinitionElementDefinitionMappingComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private ElementDefinition.ElementDefinitionSlicingComponent parseElementDefinitionElementDefinitionSlicingComponent(JsonObject json, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionSlicingComponent res = new ElementDefinition.ElementDefinitionSlicingComponent();
    parseElementProperties(json, res);
    if (json.has("discriminator")) {
      JsonArray array = json.getAsJsonArray("discriminator");
      for (int i = 0; i < array.size(); i++) {
        res.getDiscriminator().add(parseId(array.get(i).getAsString()));
      }
    };
    if (json.has("_discriminator")) {
      JsonArray array = json.getAsJsonArray("_discriminator");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getDiscriminator().size())
          res.getDiscriminator().add(parseId(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getDiscriminator().get(i));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("ordered"))
      res.setOrderedElement(parseBoolean(json.get("ordered").getAsBoolean()));
    if (json.has("_ordered"))
      parseElementProperties(json.getAsJsonObject("_ordered"), res.getOrderedElement());
    if (json.has("rules"))
      res.setRulesElement(parseEnumeration(json.get("rules").getAsString(), ElementDefinition.ResourceSlicingRules.NULL, new ElementDefinition.ResourceSlicingRulesEnumFactory()));
    if (json.has("_rules"))
      parseElementProperties(json.getAsJsonObject("_rules"), res.getRulesElement());
    return res;
  }

  private ElementDefinition.TypeRefComponent parseElementDefinitionTypeRefComponent(JsonObject json, ElementDefinition owner) throws Exception {
    ElementDefinition.TypeRefComponent res = new ElementDefinition.TypeRefComponent();
    parseElementProperties(json, res);
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("profile"))
      res.setProfileElement(parseUri(json.get("profile").getAsString()));
    if (json.has("_profile"))
      parseElementProperties(json.getAsJsonObject("_profile"), res.getProfileElement());
    if (json.has("aggregation")) {
      JsonArray array = json.getAsJsonArray("aggregation");
      for (int i = 0; i < array.size(); i++) {
        res.getAggregation().add(parseEnumeration(array.get(i).getAsString(), ElementDefinition.ResourceAggregationMode.NULL, new ElementDefinition.ResourceAggregationModeEnumFactory()));
      }
    };
    if (json.has("_aggregation")) {
      JsonArray array = json.getAsJsonArray("_aggregation");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getAggregation().size())
          res.getAggregation().add(parseEnumeration(null, ElementDefinition.ResourceAggregationMode.NULL, new ElementDefinition.ResourceAggregationModeEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getAggregation().get(i));
      }
    };
    return res;
  }

  private ElementDefinition.ElementDefinitionConstraintComponent parseElementDefinitionElementDefinitionConstraintComponent(JsonObject json, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionConstraintComponent res = new ElementDefinition.ElementDefinitionConstraintComponent();
    parseElementProperties(json, res);
    if (json.has("key"))
      res.setKeyElement(parseId(json.get("key").getAsString()));
    if (json.has("_key"))
      parseElementProperties(json.getAsJsonObject("_key"), res.getKeyElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("severity"))
      res.setSeverityElement(parseEnumeration(json.get("severity").getAsString(), ElementDefinition.ConstraintSeverity.NULL, new ElementDefinition.ConstraintSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverityElement());
    if (json.has("human"))
      res.setHumanElement(parseString(json.get("human").getAsString()));
    if (json.has("_human"))
      parseElementProperties(json.getAsJsonObject("_human"), res.getHumanElement());
    if (json.has("xpath"))
      res.setXpathElement(parseString(json.get("xpath").getAsString()));
    if (json.has("_xpath"))
      parseElementProperties(json.getAsJsonObject("_xpath"), res.getXpathElement());
    return res;
  }

  private ElementDefinition.ElementDefinitionBindingComponent parseElementDefinitionElementDefinitionBindingComponent(JsonObject json, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionBindingComponent res = new ElementDefinition.ElementDefinitionBindingComponent();
    parseElementProperties(json, res);
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("isExtensible"))
      res.setIsExtensibleElement(parseBoolean(json.get("isExtensible").getAsBoolean()));
    if (json.has("_isExtensible"))
      parseElementProperties(json.getAsJsonObject("_isExtensible"), res.getIsExtensibleElement());
    if (json.has("conformance"))
      res.setConformanceElement(parseEnumeration(json.get("conformance").getAsString(), ElementDefinition.BindingConformance.NULL, new ElementDefinition.BindingConformanceEnumFactory()));
    if (json.has("_conformance"))
      parseElementProperties(json.getAsJsonObject("_conformance"), res.getConformanceElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    Type reference = parseType("reference", json);
    if (reference != null)
      res.setReference(reference);
    return res;
  }

  private ElementDefinition.ElementDefinitionMappingComponent parseElementDefinitionElementDefinitionMappingComponent(JsonObject json, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionMappingComponent res = new ElementDefinition.ElementDefinitionMappingComponent();
    parseElementProperties(json, res);
    if (json.has("identity"))
      res.setIdentityElement(parseId(json.get("identity").getAsString()));
    if (json.has("_identity"))
      parseElementProperties(json.getAsJsonObject("_identity"), res.getIdentityElement());
    if (json.has("map"))
      res.setMapElement(parseString(json.get("map").getAsString()));
    if (json.has("_map"))
      parseElementProperties(json.getAsJsonObject("_map"), res.getMapElement());
    return res;
  }

  private Timing parseTiming(JsonObject json) throws Exception {
    Timing res = new Timing();
    parseElementProperties(json, res);
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parsePeriod(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("repeat"))
      res.setRepeat(parseTimingTimingRepeatComponent(json.getAsJsonObject("repeat"), res));
    return res;
  }

  private Timing.TimingRepeatComponent parseTimingTimingRepeatComponent(JsonObject json, Timing owner) throws Exception {
    Timing.TimingRepeatComponent res = new Timing.TimingRepeatComponent();
    parseElementProperties(json, res);
    if (json.has("frequency"))
      res.setFrequencyElement(parseInteger(json.get("frequency").getAsLong()));
    if (json.has("_frequency"))
      parseElementProperties(json.getAsJsonObject("_frequency"), res.getFrequencyElement());
    if (json.has("when"))
      res.setWhenElement(parseEnumeration(json.get("when").getAsString(), Timing.EventTiming.NULL, new Timing.EventTimingEnumFactory()));
    if (json.has("_when"))
      parseElementProperties(json.getAsJsonObject("_when"), res.getWhenElement());
    if (json.has("duration"))
      res.setDurationElement(parseDecimal(json.get("duration").getAsBigDecimal()));
    if (json.has("_duration"))
      parseElementProperties(json.getAsJsonObject("_duration"), res.getDurationElement());
    if (json.has("units"))
      res.setUnitsElement(parseEnumeration(json.get("units").getAsString(), Timing.UnitsOfTime.NULL, new Timing.UnitsOfTimeEnumFactory()));
    if (json.has("_units"))
      parseElementProperties(json.getAsJsonObject("_units"), res.getUnitsElement());
    if (json.has("count"))
      res.setCountElement(parseInteger(json.get("count").getAsLong()));
    if (json.has("_count"))
      parseElementProperties(json.getAsJsonObject("_count"), res.getCountElement());
    if (json.has("end"))
      res.setEndElement(parseDateTime(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEndElement());
    return res;
  }

  private Address parseAddress(JsonObject json) throws Exception {
    Address res = new Address();
    parseElementProperties(json, res);
    if (json.has("use"))
      res.setUseElement(parseEnumeration(json.get("use").getAsString(), Address.AddressUse.NULL, new Address.AddressUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUseElement());
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
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
      res.setCityElement(parseString(json.get("city").getAsString()));
    if (json.has("_city"))
      parseElementProperties(json.getAsJsonObject("_city"), res.getCityElement());
    if (json.has("state"))
      res.setStateElement(parseString(json.get("state").getAsString()));
    if (json.has("_state"))
      parseElementProperties(json.getAsJsonObject("_state"), res.getStateElement());
    if (json.has("postalCode"))
      res.setPostalCodeElement(parseString(json.get("postalCode").getAsString()));
    if (json.has("_postalCode"))
      parseElementProperties(json.getAsJsonObject("_postalCode"), res.getPostalCodeElement());
    if (json.has("country"))
      res.setCountryElement(parseString(json.get("country").getAsString()));
    if (json.has("_country"))
      parseElementProperties(json.getAsJsonObject("_country"), res.getCountryElement());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private HumanName parseHumanName(JsonObject json) throws Exception {
    HumanName res = new HumanName();
    parseElementProperties(json, res);
    if (json.has("use"))
      res.setUseElement(parseEnumeration(json.get("use").getAsString(), HumanName.NameUse.NULL, new HumanName.NameUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUseElement());
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
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

  private ContactPoint parseContactPoint(JsonObject json) throws Exception {
    ContactPoint res = new ContactPoint();
    parseElementProperties(json, res);
    if (json.has("system"))
      res.setSystemElement(parseEnumeration(json.get("system").getAsString(), ContactPoint.ContactPointSystem.NULL, new ContactPoint.ContactPointSystemEnumFactory()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("value"))
      res.setValueElement(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("use"))
      res.setUseElement(parseEnumeration(json.get("use").getAsString(), ContactPoint.ContactPointUse.NULL, new ContactPoint.ContactPointUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUseElement());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private void parseResourceProperties(JsonObject json, Resource res) throws Exception {
    if (json.has("id"))
      res.setIdElement(parseId(json.get("id").getAsString()));
    if (json.has("_id"))
      parseElementProperties(json.getAsJsonObject("_id"), res.getIdElement());
    if (json.has("meta"))
      res.setMeta(parseResourceResourceMetaComponent(json.getAsJsonObject("meta"), res));
    if (json.has("implicitRules"))
      res.setImplicitRulesElement(parseUri(json.get("implicitRules").getAsString()));
    if (json.has("_implicitRules"))
      parseElementProperties(json.getAsJsonObject("_implicitRules"), res.getImplicitRulesElement());
    if (json.has("language"))
      res.setLanguageElement(parseCode(json.get("language").getAsString()));
    if (json.has("_language"))
      parseElementProperties(json.getAsJsonObject("_language"), res.getLanguageElement());
  }

  private Resource.ResourceMetaComponent parseResourceResourceMetaComponent(JsonObject json, Resource owner) throws Exception {
    Resource.ResourceMetaComponent res = new Resource.ResourceMetaComponent();
    parseBackboneProperties(json, res);
    if (json.has("versionId"))
      res.setVersionIdElement(parseId(json.get("versionId").getAsString()));
    if (json.has("_versionId"))
      parseElementProperties(json.getAsJsonObject("_versionId"), res.getVersionIdElement());
    if (json.has("lastUpdated"))
      res.setLastUpdatedElement(parseInstant(json.get("lastUpdated").getAsString()));
    if (json.has("_lastUpdated"))
      parseElementProperties(json.getAsJsonObject("_lastUpdated"), res.getLastUpdatedElement());
    if (json.has("profile")) {
      JsonArray array = json.getAsJsonArray("profile");
      for (int i = 0; i < array.size(); i++) {
        res.getProfile().add(parseUri(array.get(i).getAsString()));
      }
    };
    if (json.has("_profile")) {
      JsonArray array = json.getAsJsonArray("_profile");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getProfile().size())
          res.getProfile().add(parseUri(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getProfile().get(i));
      }
    };
    if (json.has("security")) {
      JsonArray array = json.getAsJsonArray("security");
      for (int i = 0; i < array.size(); i++) {
        res.getSecurity().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("tag")) {
      JsonArray array = json.getAsJsonArray("tag");
      for (int i = 0; i < array.size(); i++) {
        res.getTag().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private void parseDomainResourceProperties(JsonObject json, DomainResource res) throws Exception {
    parseResourceProperties(json, res);
    if (json.has("text"))
      res.setText(parseNarrative(json.getAsJsonObject("text")));
    if (json.has("contained")) {
      JsonArray array = json.getAsJsonArray("contained");
      for (int i = 0; i < array.size(); i++) {
        res.getContained().add(parseResource(array.get(i).getAsJsonObject()));
      }
    };
    parseExtensions(json, res.getExtension(), false);
    if (json != null && json.has("modifier")) {
      JsonObject obj = json.getAsJsonObject("modifier");
      parseExtensions(obj, res.getModifierExtension(), false);
    };
  }

  private Alert parseAlert(JsonObject json) throws Exception {
    Alert res = new Alert();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Alert.AlertStatus.NULL, new Alert.AlertStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    if (json.has("note"))
      res.setNoteElement(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNoteElement());
    return res;
  }

  private AllergyIntolerance parseAllergyIntolerance(JsonObject json) throws Exception {
    AllergyIntolerance res = new AllergyIntolerance();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("recordedDate"))
      res.setRecordedDateElement(parseDateTime(json.get("recordedDate").getAsString()));
    if (json.has("_recordedDate"))
      parseElementProperties(json.getAsJsonObject("_recordedDate"), res.getRecordedDateElement());
    if (json.has("recorder"))
      res.setRecorder(parseReference(json.getAsJsonObject("recorder")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("substance"))
      res.setSubstance(parseCodeableConcept(json.getAsJsonObject("substance")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), AllergyIntolerance.AllergyIntoleranceStatus.NULL, new AllergyIntolerance.AllergyIntoleranceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("criticality"))
      res.setCriticalityElement(parseEnumeration(json.get("criticality").getAsString(), AllergyIntolerance.AllergyIntoleranceCriticality.NULL, new AllergyIntolerance.AllergyIntoleranceCriticalityEnumFactory()));
    if (json.has("_criticality"))
      parseElementProperties(json.getAsJsonObject("_criticality"), res.getCriticalityElement());
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), AllergyIntolerance.AllergyIntoleranceType.NULL, new AllergyIntolerance.AllergyIntoleranceTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("category"))
      res.setCategoryElement(parseEnumeration(json.get("category").getAsString(), AllergyIntolerance.AllergyIntoleranceCategory.NULL, new AllergyIntolerance.AllergyIntoleranceCategoryEnumFactory()));
    if (json.has("_category"))
      parseElementProperties(json.getAsJsonObject("_category"), res.getCategoryElement());
    if (json.has("lastOccurence"))
      res.setLastOccurenceElement(parseDateTime(json.get("lastOccurence").getAsString()));
    if (json.has("_lastOccurence"))
      parseElementProperties(json.getAsJsonObject("_lastOccurence"), res.getLastOccurenceElement());
    if (json.has("comment"))
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parseAllergyIntoleranceAllergyIntoleranceEventComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private AllergyIntolerance.AllergyIntoleranceEventComponent parseAllergyIntoleranceAllergyIntoleranceEventComponent(JsonObject json, AllergyIntolerance owner) throws Exception {
    AllergyIntolerance.AllergyIntoleranceEventComponent res = new AllergyIntolerance.AllergyIntoleranceEventComponent();
    parseBackboneProperties(json, res);
    if (json.has("substance"))
      res.setSubstance(parseCodeableConcept(json.getAsJsonObject("substance")));
    if (json.has("certainty"))
      res.setCertaintyElement(parseEnumeration(json.get("certainty").getAsString(), AllergyIntolerance.ReactionEventCertainty.NULL, new AllergyIntolerance.ReactionEventCertaintyEnumFactory()));
    if (json.has("_certainty"))
      parseElementProperties(json.getAsJsonObject("_certainty"), res.getCertaintyElement());
    if (json.has("manifestation")) {
      JsonArray array = json.getAsJsonArray("manifestation");
      for (int i = 0; i < array.size(); i++) {
        res.getManifestation().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("onset"))
      res.setOnsetElement(parseDateTime(json.get("onset").getAsString()));
    if (json.has("_onset"))
      parseElementProperties(json.getAsJsonObject("_onset"), res.getOnsetElement());
    if (json.has("duration"))
      res.setDuration(parseDuration(json.getAsJsonObject("duration")));
    if (json.has("severity"))
      res.setSeverityElement(parseEnumeration(json.get("severity").getAsString(), AllergyIntolerance.ReactionEventSeverity.NULL, new AllergyIntolerance.ReactionEventSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverityElement());
    if (json.has("exposureRoute"))
      res.setExposureRoute(parseCodeableConcept(json.getAsJsonObject("exposureRoute")));
    if (json.has("comment"))
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    return res;
  }

  private Appointment parseAppointment(JsonObject json) throws Exception {
    Appointment res = new Appointment();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("priority"))
      res.setPriorityElement(parseInteger(json.get("priority").getAsLong()));
    if (json.has("_priority"))
      parseElementProperties(json.getAsJsonObject("_priority"), res.getPriorityElement());
    if (json.has("status"))
      res.setStatusElement(parseCode(json.get("status").getAsString()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("start"))
      res.setStartElement(parseInstant(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStartElement());
    if (json.has("end"))
      res.setEndElement(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEndElement());
    if (json.has("slot")) {
      JsonArray array = json.getAsJsonArray("slot");
      for (int i = 0; i < array.size(); i++) {
        res.getSlot().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("location"))
      res.setLocation(parseReference(json.getAsJsonObject("location")));
    if (json.has("comment"))
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    if (json.has("order"))
      res.setOrder(parseReference(json.getAsJsonObject("order")));
    if (json.has("participant")) {
      JsonArray array = json.getAsJsonArray("participant");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipant().add(parseAppointmentAppointmentParticipantComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("lastModifiedBy"))
      res.setLastModifiedBy(parseReference(json.getAsJsonObject("lastModifiedBy")));
    if (json.has("lastModified"))
      res.setLastModifiedElement(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModifiedElement());
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
      res.setActor(parseReference(json.getAsJsonObject("actor")));
    if (json.has("required"))
      res.setRequiredElement(parseEnumeration(json.get("required").getAsString(), Appointment.Participantrequired.NULL, new Appointment.ParticipantrequiredEnumFactory()));
    if (json.has("_required"))
      parseElementProperties(json.getAsJsonObject("_required"), res.getRequiredElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Appointment.Participationstatus.NULL, new Appointment.ParticipationstatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    return res;
  }

  private AppointmentResponse parseAppointmentResponse(JsonObject json) throws Exception {
    AppointmentResponse res = new AppointmentResponse();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("appointment"))
      res.setAppointment(parseReference(json.getAsJsonObject("appointment")));
    if (json.has("participantType")) {
      JsonArray array = json.getAsJsonArray("participantType");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipantType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("individual")) {
      JsonArray array = json.getAsJsonArray("individual");
      for (int i = 0; i < array.size(); i++) {
        res.getIndividual().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("participantStatus"))
      res.setParticipantStatusElement(parseEnumeration(json.get("participantStatus").getAsString(), AppointmentResponse.Participantstatus.NULL, new AppointmentResponse.ParticipantstatusEnumFactory()));
    if (json.has("_participantStatus"))
      parseElementProperties(json.getAsJsonObject("_participantStatus"), res.getParticipantStatusElement());
    if (json.has("comment"))
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    if (json.has("start"))
      res.setStartElement(parseInstant(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStartElement());
    if (json.has("end"))
      res.setEndElement(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEndElement());
    if (json.has("lastModifiedBy"))
      res.setLastModifiedBy(parseReference(json.getAsJsonObject("lastModifiedBy")));
    if (json.has("lastModified"))
      res.setLastModifiedElement(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModifiedElement());
    return res;
  }

  private Availability parseAvailability(JsonObject json) throws Exception {
    Availability res = new Availability();
    parseDomainResourceProperties(json, res);
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
      res.setActor(parseReference(json.getAsJsonObject("actor")));
    if (json.has("planningHorizon"))
      res.setPlanningHorizon(parsePeriod(json.getAsJsonObject("planningHorizon")));
    if (json.has("comment"))
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    if (json.has("lastModified"))
      res.setLastModifiedElement(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModifiedElement());
    return res;
  }

  private Basic parseBasic(JsonObject json) throws Exception {
    Basic res = new Basic();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    if (json.has("created"))
      res.setCreatedElement(parseDate(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreatedElement());
    return res;
  }

  private Binary parseBinary(JsonObject json) throws Exception {
    Binary res = new Binary();
    parseResourceProperties(json, res);
    if (json.has("contentType"))
      res.setContentTypeElement(parseCode(json.get("contentType").getAsString()));
    if (json.has("_contentType"))
      parseElementProperties(json.getAsJsonObject("_contentType"), res.getContentTypeElement());
    if (json.has("content"))
      res.setContentElement(parseBase64Binary(json.get("content").getAsString()));
    if (json.has("_content"))
      parseElementProperties(json.getAsJsonObject("_content"), res.getContentElement());
    return res;
  }

  private Bundle parseBundle(JsonObject json) throws Exception {
    Bundle res = new Bundle();
    parseResourceProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Bundle.BundleType.NULL, new Bundle.BundleTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("base"))
      res.setBaseElement(parseUri(json.get("base").getAsString()));
    if (json.has("_base"))
      parseElementProperties(json.getAsJsonObject("_base"), res.getBaseElement());
    if (json.has("total"))
      res.setTotalElement(parseInteger(json.get("total").getAsLong()));
    if (json.has("_total"))
      parseElementProperties(json.getAsJsonObject("_total"), res.getTotalElement());
    if (json.has("link")) {
      JsonArray array = json.getAsJsonArray("link");
      for (int i = 0; i < array.size(); i++) {
        res.getLink().add(parseBundleBundleLinkComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("entry")) {
      JsonArray array = json.getAsJsonArray("entry");
      for (int i = 0; i < array.size(); i++) {
        res.getEntry().add(parseBundleBundleEntryComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("signature"))
      res.setSignatureElement(parseBase64Binary(json.get("signature").getAsString()));
    if (json.has("_signature"))
      parseElementProperties(json.getAsJsonObject("_signature"), res.getSignatureElement());
    return res;
  }

  private Bundle.BundleLinkComponent parseBundleBundleLinkComponent(JsonObject json, Bundle owner) throws Exception {
    Bundle.BundleLinkComponent res = new Bundle.BundleLinkComponent();
    parseBackboneProperties(json, res);
    if (json.has("relation"))
      res.setRelationElement(parseString(json.get("relation").getAsString()));
    if (json.has("_relation"))
      parseElementProperties(json.getAsJsonObject("_relation"), res.getRelationElement());
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    return res;
  }

  private Bundle.BundleEntryComponent parseBundleBundleEntryComponent(JsonObject json, Bundle owner) throws Exception {
    Bundle.BundleEntryComponent res = new Bundle.BundleEntryComponent();
    parseBackboneProperties(json, res);
    if (json.has("base"))
      res.setBaseElement(parseUri(json.get("base").getAsString()));
    if (json.has("_base"))
      parseElementProperties(json.getAsJsonObject("_base"), res.getBaseElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Bundle.BundleEntryStatus.NULL, new Bundle.BundleEntryStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("search"))
      res.setSearchElement(parseUri(json.get("search").getAsString()));
    if (json.has("_search"))
      parseElementProperties(json.getAsJsonObject("_search"), res.getSearchElement());
    if (json.has("score"))
      res.setScoreElement(parseDecimal(json.get("score").getAsBigDecimal()));
    if (json.has("_score"))
      parseElementProperties(json.getAsJsonObject("_score"), res.getScoreElement());
    if (json.has("deleted"))
      res.setDeleted(parseBundleBundleEntryDeletedComponent(json.getAsJsonObject("deleted"), owner));
    if (json.has("resource"))
      res.setResource(parseResource(json.getAsJsonObject("resource")));
    return res;
  }

  private Bundle.BundleEntryDeletedComponent parseBundleBundleEntryDeletedComponent(JsonObject json, Bundle owner) throws Exception {
    Bundle.BundleEntryDeletedComponent res = new Bundle.BundleEntryDeletedComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("id"))
      res.setIdElement(parseId(json.get("id").getAsString()));
    if (json.has("_id"))
      parseElementProperties(json.getAsJsonObject("_id"), res.getIdElement());
    if (json.has("instant"))
      res.setInstantElement(parseInstant(json.get("instant").getAsString()));
    if (json.has("_instant"))
      parseElementProperties(json.getAsJsonObject("_instant"), res.getInstantElement());
    return res;
  }

  private CarePlan parseCarePlan(JsonObject json) throws Exception {
    CarePlan res = new CarePlan();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), CarePlan.CarePlanStatus.NULL, new CarePlan.CarePlanStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("modified"))
      res.setModifiedElement(parseDateTime(json.get("modified").getAsString()));
    if (json.has("_modified"))
      parseElementProperties(json.getAsJsonObject("_modified"), res.getModifiedElement());
    if (json.has("concern")) {
      JsonArray array = json.getAsJsonArray("concern");
      for (int i = 0; i < array.size(); i++) {
        res.getConcern().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setNotesElement(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotesElement());
    return res;
  }

  private CarePlan.CarePlanParticipantComponent parseCarePlanCarePlanParticipantComponent(JsonObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanParticipantComponent res = new CarePlan.CarePlanParticipantComponent();
    parseBackboneProperties(json, res);
    if (json.has("role"))
      res.setRole(parseCodeableConcept(json.getAsJsonObject("role")));
    if (json.has("member"))
      res.setMember(parseReference(json.getAsJsonObject("member")));
    return res;
  }

  private CarePlan.CarePlanGoalComponent parseCarePlanCarePlanGoalComponent(JsonObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanGoalComponent res = new CarePlan.CarePlanGoalComponent();
    parseBackboneProperties(json, res);
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), CarePlan.CarePlanGoalStatus.NULL, new CarePlan.CarePlanGoalStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("notes"))
      res.setNotesElement(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotesElement());
    if (json.has("concern")) {
      JsonArray array = json.getAsJsonArray("concern");
      for (int i = 0; i < array.size(); i++) {
        res.getConcern().add(parseReference(array.get(i).getAsJsonObject()));
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
        res.getGoal().add(parseUri(array.get(i).getAsString()));
      }
    };
    if (json.has("_goal")) {
      JsonArray array = json.getAsJsonArray("_goal");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getGoal().size())
          res.getGoal().add(parseUri(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getGoal().get(i));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), CarePlan.CarePlanActivityStatus.NULL, new CarePlan.CarePlanActivityStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("prohibited"))
      res.setProhibitedElement(parseBoolean(json.get("prohibited").getAsBoolean()));
    if (json.has("_prohibited"))
      parseElementProperties(json.getAsJsonObject("_prohibited"), res.getProhibitedElement());
    if (json.has("actionResulting")) {
      JsonArray array = json.getAsJsonArray("actionResulting");
      for (int i = 0; i < array.size(); i++) {
        res.getActionResulting().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("notes"))
      res.setNotesElement(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotesElement());
    if (json.has("detail"))
      res.setDetail(parseReference(json.getAsJsonObject("detail")));
    if (json.has("simple"))
      res.setSimple(parseCarePlanCarePlanActivitySimpleComponent(json.getAsJsonObject("simple"), owner));
    return res;
  }

  private CarePlan.CarePlanActivitySimpleComponent parseCarePlanCarePlanActivitySimpleComponent(JsonObject json, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivitySimpleComponent res = new CarePlan.CarePlanActivitySimpleComponent();
    parseBackboneProperties(json, res);
    if (json.has("category"))
      res.setCategoryElement(parseEnumeration(json.get("category").getAsString(), CarePlan.CarePlanActivityCategory.NULL, new CarePlan.CarePlanActivityCategoryEnumFactory()));
    if (json.has("_category"))
      parseElementProperties(json.getAsJsonObject("_category"), res.getCategoryElement());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    Type scheduled = parseType("scheduled", json);
    if (scheduled != null)
      res.setScheduled(scheduled);
    if (json.has("location"))
      res.setLocation(parseReference(json.getAsJsonObject("location")));
    if (json.has("performer")) {
      JsonArray array = json.getAsJsonArray("performer");
      for (int i = 0; i < array.size(); i++) {
        res.getPerformer().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("product"))
      res.setProduct(parseReference(json.getAsJsonObject("product")));
    if (json.has("dailyAmount"))
      res.setDailyAmount(parseQuantity(json.getAsJsonObject("dailyAmount")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("details"))
      res.setDetailsElement(parseString(json.get("details").getAsString()));
    if (json.has("_details"))
      parseElementProperties(json.getAsJsonObject("_details"), res.getDetailsElement());
    return res;
  }

  private ClaimResponse parseClaimResponse(JsonObject json) throws Exception {
    ClaimResponse res = new ClaimResponse();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("request"))
      res.setRequest(parseReference(json.getAsJsonObject("request")));
    if (json.has("requestIdentifier")) {
      JsonArray array = json.getAsJsonArray("requestIdentifier");
      for (int i = 0; i < array.size(); i++) {
        res.getRequestIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("ruleset"))
      res.setRuleset(parseCoding(json.getAsJsonObject("ruleset")));
    if (json.has("originalRuleset"))
      res.setOriginalRuleset(parseCoding(json.getAsJsonObject("originalRuleset")));
    if (json.has("date"))
      res.setDateElement(parseDate(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
    if (json.has("requestProvider"))
      res.setRequestProvider(parseReference(json.getAsJsonObject("requestProvider")));
    if (json.has("requestOrganization"))
      res.setRequestOrganization(parseReference(json.getAsJsonObject("requestOrganization")));
    if (json.has("outcome"))
      res.setOutcomeElement(parseEnumeration(json.get("outcome").getAsString(), ClaimResponse.RSLink.NULL, new ClaimResponse.RSLinkEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcomeElement());
    if (json.has("disposition"))
      res.setDispositionElement(parseString(json.get("disposition").getAsString()));
    if (json.has("_disposition"))
      parseElementProperties(json.getAsJsonObject("_disposition"), res.getDispositionElement());
    if (json.has("payeeType"))
      res.setPayeeType(parseCoding(json.getAsJsonObject("payeeType")));
    if (json.has("item")) {
      JsonArray array = json.getAsJsonArray("item");
      for (int i = 0; i < array.size(); i++) {
        res.getItem().add(parseClaimResponseItemsComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("additem")) {
      JsonArray array = json.getAsJsonArray("additem");
      for (int i = 0; i < array.size(); i++) {
        res.getAdditem().add(parseClaimResponseAddedItemComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("error")) {
      JsonArray array = json.getAsJsonArray("error");
      for (int i = 0; i < array.size(); i++) {
        res.getError().add(parseClaimResponseErrorsComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("totalCost"))
      res.setTotalCost(parseMoney(json.getAsJsonObject("totalCost")));
    if (json.has("unallocDeductable"))
      res.setUnallocDeductable(parseMoney(json.getAsJsonObject("unallocDeductable")));
    if (json.has("totalBenefit"))
      res.setTotalBenefit(parseMoney(json.getAsJsonObject("totalBenefit")));
    if (json.has("paymentAdjustment"))
      res.setPaymentAdjustment(parseMoney(json.getAsJsonObject("paymentAdjustment")));
    if (json.has("paymentAdjustmentReason"))
      res.setPaymentAdjustmentReason(parseCoding(json.getAsJsonObject("paymentAdjustmentReason")));
    if (json.has("paymentDate"))
      res.setPaymentDateElement(parseDate(json.get("paymentDate").getAsString()));
    if (json.has("_paymentDate"))
      parseElementProperties(json.getAsJsonObject("_paymentDate"), res.getPaymentDateElement());
    if (json.has("paymentAmount"))
      res.setPaymentAmount(parseMoney(json.getAsJsonObject("paymentAmount")));
    if (json.has("paymentRef"))
      res.setPaymentRef(parseIdentifier(json.getAsJsonObject("paymentRef")));
    if (json.has("reserved"))
      res.setReserved(parseCoding(json.getAsJsonObject("reserved")));
    if (json.has("form"))
      res.setForm(parseCoding(json.getAsJsonObject("form")));
    if (json.has("note")) {
      JsonArray array = json.getAsJsonArray("note");
      for (int i = 0; i < array.size(); i++) {
        res.getNote().add(parseClaimResponseNotesComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private ClaimResponse.ItemsComponent parseClaimResponseItemsComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.ItemsComponent res = new ClaimResponse.ItemsComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequenceLinkId"))
      res.setSequenceLinkIdElement(parseInteger(json.get("sequenceLinkId").getAsLong()));
    if (json.has("_sequenceLinkId"))
      parseElementProperties(json.getAsJsonObject("_sequenceLinkId"), res.getSequenceLinkIdElement());
    if (json.has("noteNumber")) {
      JsonArray array = json.getAsJsonArray("noteNumber");
      for (int i = 0; i < array.size(); i++) {
        res.getNoteNumber().add(parseInteger(array.get(i).getAsLong()));
      }
    };
    if (json.has("_noteNumber")) {
      JsonArray array = json.getAsJsonArray("_noteNumber");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getNoteNumber().size())
          res.getNoteNumber().add(parseInteger(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getNoteNumber().get(i));
      }
    };
    if (json.has("adjudication")) {
      JsonArray array = json.getAsJsonArray("adjudication");
      for (int i = 0; i < array.size(); i++) {
        res.getAdjudication().add(parseClaimResponseItemAdjudicationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseClaimResponseItemDetailComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ClaimResponse.ItemAdjudicationComponent parseClaimResponseItemAdjudicationComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.ItemAdjudicationComponent res = new ClaimResponse.ItemAdjudicationComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCoding(json.getAsJsonObject("code")));
    if (json.has("amount"))
      res.setAmount(parseMoney(json.getAsJsonObject("amount")));
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private ClaimResponse.ItemDetailComponent parseClaimResponseItemDetailComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.ItemDetailComponent res = new ClaimResponse.ItemDetailComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequenceLinkId"))
      res.setSequenceLinkIdElement(parseInteger(json.get("sequenceLinkId").getAsLong()));
    if (json.has("_sequenceLinkId"))
      parseElementProperties(json.getAsJsonObject("_sequenceLinkId"), res.getSequenceLinkIdElement());
    if (json.has("adjudication")) {
      JsonArray array = json.getAsJsonArray("adjudication");
      for (int i = 0; i < array.size(); i++) {
        res.getAdjudication().add(parseClaimResponseDetailAdjudicationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("subdetail")) {
      JsonArray array = json.getAsJsonArray("subdetail");
      for (int i = 0; i < array.size(); i++) {
        res.getSubdetail().add(parseClaimResponseItemSubdetailComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ClaimResponse.DetailAdjudicationComponent parseClaimResponseDetailAdjudicationComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.DetailAdjudicationComponent res = new ClaimResponse.DetailAdjudicationComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCoding(json.getAsJsonObject("code")));
    if (json.has("amount"))
      res.setAmount(parseMoney(json.getAsJsonObject("amount")));
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private ClaimResponse.ItemSubdetailComponent parseClaimResponseItemSubdetailComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.ItemSubdetailComponent res = new ClaimResponse.ItemSubdetailComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequenceLinkId"))
      res.setSequenceLinkIdElement(parseInteger(json.get("sequenceLinkId").getAsLong()));
    if (json.has("_sequenceLinkId"))
      parseElementProperties(json.getAsJsonObject("_sequenceLinkId"), res.getSequenceLinkIdElement());
    if (json.has("adjudication")) {
      JsonArray array = json.getAsJsonArray("adjudication");
      for (int i = 0; i < array.size(); i++) {
        res.getAdjudication().add(parseClaimResponseSubdetailAdjudicationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ClaimResponse.SubdetailAdjudicationComponent parseClaimResponseSubdetailAdjudicationComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.SubdetailAdjudicationComponent res = new ClaimResponse.SubdetailAdjudicationComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCoding(json.getAsJsonObject("code")));
    if (json.has("amount"))
      res.setAmount(parseMoney(json.getAsJsonObject("amount")));
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private ClaimResponse.AddedItemComponent parseClaimResponseAddedItemComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemComponent res = new ClaimResponse.AddedItemComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequenceLinkId")) {
      JsonArray array = json.getAsJsonArray("sequenceLinkId");
      for (int i = 0; i < array.size(); i++) {
        res.getSequenceLinkId().add(parseInteger(array.get(i).getAsLong()));
      }
    };
    if (json.has("_sequenceLinkId")) {
      JsonArray array = json.getAsJsonArray("_sequenceLinkId");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getSequenceLinkId().size())
          res.getSequenceLinkId().add(parseInteger(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getSequenceLinkId().get(i));
      }
    };
    if (json.has("service"))
      res.setService(parseCoding(json.getAsJsonObject("service")));
    if (json.has("fee"))
      res.setFee(parseMoney(json.getAsJsonObject("fee")));
    if (json.has("noteNumberLinkId")) {
      JsonArray array = json.getAsJsonArray("noteNumberLinkId");
      for (int i = 0; i < array.size(); i++) {
        res.getNoteNumberLinkId().add(parseInteger(array.get(i).getAsLong()));
      }
    };
    if (json.has("_noteNumberLinkId")) {
      JsonArray array = json.getAsJsonArray("_noteNumberLinkId");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getNoteNumberLinkId().size())
          res.getNoteNumberLinkId().add(parseInteger(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getNoteNumberLinkId().get(i));
      }
    };
    if (json.has("adjudication")) {
      JsonArray array = json.getAsJsonArray("adjudication");
      for (int i = 0; i < array.size(); i++) {
        res.getAdjudication().add(parseClaimResponseAddedItemAdjudicationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseClaimResponseAddedItemsDetailComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ClaimResponse.AddedItemAdjudicationComponent parseClaimResponseAddedItemAdjudicationComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemAdjudicationComponent res = new ClaimResponse.AddedItemAdjudicationComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCoding(json.getAsJsonObject("code")));
    if (json.has("amount"))
      res.setAmount(parseMoney(json.getAsJsonObject("amount")));
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private ClaimResponse.AddedItemsDetailComponent parseClaimResponseAddedItemsDetailComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemsDetailComponent res = new ClaimResponse.AddedItemsDetailComponent();
    parseBackboneProperties(json, res);
    if (json.has("service"))
      res.setService(parseCoding(json.getAsJsonObject("service")));
    if (json.has("fee"))
      res.setFee(parseMoney(json.getAsJsonObject("fee")));
    if (json.has("adjudication")) {
      JsonArray array = json.getAsJsonArray("adjudication");
      for (int i = 0; i < array.size(); i++) {
        res.getAdjudication().add(parseClaimResponseAddedItemDetailAdjudicationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ClaimResponse.AddedItemDetailAdjudicationComponent parseClaimResponseAddedItemDetailAdjudicationComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemDetailAdjudicationComponent res = new ClaimResponse.AddedItemDetailAdjudicationComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCode(parseCoding(json.getAsJsonObject("code")));
    if (json.has("amount"))
      res.setAmount(parseMoney(json.getAsJsonObject("amount")));
    if (json.has("value"))
      res.setValueElement(parseDecimal(json.get("value").getAsBigDecimal()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private ClaimResponse.ErrorsComponent parseClaimResponseErrorsComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.ErrorsComponent res = new ClaimResponse.ErrorsComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequenceLinkId"))
      res.setSequenceLinkIdElement(parseInteger(json.get("sequenceLinkId").getAsLong()));
    if (json.has("_sequenceLinkId"))
      parseElementProperties(json.getAsJsonObject("_sequenceLinkId"), res.getSequenceLinkIdElement());
    if (json.has("detailSequenceLinkId"))
      res.setDetailSequenceLinkIdElement(parseInteger(json.get("detailSequenceLinkId").getAsLong()));
    if (json.has("_detailSequenceLinkId"))
      parseElementProperties(json.getAsJsonObject("_detailSequenceLinkId"), res.getDetailSequenceLinkIdElement());
    if (json.has("subdetailSequenceLinkId"))
      res.setSubdetailSequenceLinkIdElement(parseInteger(json.get("subdetailSequenceLinkId").getAsLong()));
    if (json.has("_subdetailSequenceLinkId"))
      parseElementProperties(json.getAsJsonObject("_subdetailSequenceLinkId"), res.getSubdetailSequenceLinkIdElement());
    if (json.has("code"))
      res.setCode(parseCoding(json.getAsJsonObject("code")));
    return res;
  }

  private ClaimResponse.NotesComponent parseClaimResponseNotesComponent(JsonObject json, ClaimResponse owner) throws Exception {
    ClaimResponse.NotesComponent res = new ClaimResponse.NotesComponent();
    parseBackboneProperties(json, res);
    if (json.has("number"))
      res.setNumberElement(parseInteger(json.get("number").getAsLong()));
    if (json.has("_number"))
      parseElementProperties(json.getAsJsonObject("_number"), res.getNumberElement());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    return res;
  }

  private CommunicationRequest parseCommunicationRequest(JsonObject json) throws Exception {
    CommunicationRequest res = new CommunicationRequest();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("sender"))
      res.setSender(parseReference(json.getAsJsonObject("sender")));
    if (json.has("recipient")) {
      JsonArray array = json.getAsJsonArray("recipient");
      for (int i = 0; i < array.size(); i++) {
        res.getRecipient().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("messagePart")) {
      JsonArray array = json.getAsJsonArray("messagePart");
      for (int i = 0; i < array.size(); i++) {
        res.getMessagePart().add(parseCommunicationRequestCommunicationRequestMessagePartComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("medium")) {
      JsonArray array = json.getAsJsonArray("medium");
      for (int i = 0; i < array.size(); i++) {
        res.getMedium().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("requester"))
      res.setRequester(parseReference(json.getAsJsonObject("requester")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), CommunicationRequest.CommunicationRequestStatus.NULL, new CommunicationRequest.CommunicationRequestStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), CommunicationRequest.CommunicationRequestMode.NULL, new CommunicationRequest.CommunicationRequestModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("scheduledTime"))
      res.setScheduledTimeElement(parseDateTime(json.get("scheduledTime").getAsString()));
    if (json.has("_scheduledTime"))
      parseElementProperties(json.getAsJsonObject("_scheduledTime"), res.getScheduledTimeElement());
    if (json.has("indication")) {
      JsonArray array = json.getAsJsonArray("indication");
      for (int i = 0; i < array.size(); i++) {
        res.getIndication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("orderedOn"))
      res.setOrderedOnElement(parseDateTime(json.get("orderedOn").getAsString()));
    if (json.has("_orderedOn"))
      parseElementProperties(json.getAsJsonObject("_orderedOn"), res.getOrderedOnElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("priority"))
      res.setPriority(parseCodeableConcept(json.getAsJsonObject("priority")));
    return res;
  }

  private CommunicationRequest.CommunicationRequestMessagePartComponent parseCommunicationRequestCommunicationRequestMessagePartComponent(JsonObject json, CommunicationRequest owner) throws Exception {
    CommunicationRequest.CommunicationRequestMessagePartComponent res = new CommunicationRequest.CommunicationRequestMessagePartComponent();
    parseBackboneProperties(json, res);
    Type content = parseType("content", json);
    if (content != null)
      res.setContent(content);
    return res;
  }

  private Composition parseComposition(JsonObject json) throws Exception {
    Composition res = new Composition();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("class"))
      res.setClass_(parseCodeableConcept(json.getAsJsonObject("class")));
    if (json.has("title"))
      res.setTitleElement(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitleElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Composition.CompositionStatus.NULL, new Composition.CompositionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("confidentiality"))
      res.setConfidentiality(parseCoding(json.getAsJsonObject("confidentiality")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("author")) {
      JsonArray array = json.getAsJsonArray("author");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthor().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("attester")) {
      JsonArray array = json.getAsJsonArray("attester");
      for (int i = 0; i < array.size(); i++) {
        res.getAttester().add(parseCompositionCompositionAttesterComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("custodian"))
      res.setCustodian(parseReference(json.getAsJsonObject("custodian")));
    if (json.has("event")) {
      JsonArray array = json.getAsJsonArray("event");
      for (int i = 0; i < array.size(); i++) {
        res.getEvent().add(parseCompositionCompositionEventComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
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
        res.getMode().add(parseEnumeration(array.get(i).getAsString(), Composition.CompositionAttestationMode.NULL, new Composition.CompositionAttestationModeEnumFactory()));
      }
    };
    if (json.has("_mode")) {
      JsonArray array = json.getAsJsonArray("_mode");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getMode().size())
          res.getMode().add(parseEnumeration(null, Composition.CompositionAttestationMode.NULL, new Composition.CompositionAttestationModeEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getMode().get(i));
      }
    };
    if (json.has("time"))
      res.setTimeElement(parseDateTime(json.get("time").getAsString()));
    if (json.has("_time"))
      parseElementProperties(json.getAsJsonObject("_time"), res.getTimeElement());
    if (json.has("party"))
      res.setParty(parseReference(json.getAsJsonObject("party")));
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
        res.getDetail().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Composition.SectionComponent parseCompositionSectionComponent(JsonObject json, Composition owner) throws Exception {
    Composition.SectionComponent res = new Composition.SectionComponent();
    parseBackboneProperties(json, res);
    if (json.has("title"))
      res.setTitleElement(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitleElement());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("section")) {
      JsonArray array = json.getAsJsonArray("section");
      for (int i = 0; i < array.size(); i++) {
        res.getSection().add(parseCompositionSectionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("content"))
      res.setContent(parseReference(json.getAsJsonObject("content")));
    return res;
  }

  private ConceptMap parseConceptMap(JsonObject json) throws Exception {
    ConceptMap res = new ConceptMap();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifierElement(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("copyright"))
      res.setCopyrightElement(parseString(json.get("copyright").getAsString()));
    if (json.has("_copyright"))
      parseElementProperties(json.getAsJsonObject("_copyright"), res.getCopyrightElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), ConceptMap.ValuesetStatus.NULL, new ConceptMap.ValuesetStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("experimental"))
      res.setExperimentalElement(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimentalElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
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
      res.setCodeSystemElement(parseUri(json.get("codeSystem").getAsString()));
    if (json.has("_codeSystem"))
      parseElementProperties(json.getAsJsonObject("_codeSystem"), res.getCodeSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
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
      res.setElementElement(parseUri(json.get("element").getAsString()));
    if (json.has("_element"))
      parseElementProperties(json.getAsJsonObject("_element"), res.getElementElement());
    if (json.has("codeSystem"))
      res.setCodeSystemElement(parseUri(json.get("codeSystem").getAsString()));
    if (json.has("_codeSystem"))
      parseElementProperties(json.getAsJsonObject("_codeSystem"), res.getCodeSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseString(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    return res;
  }

  private ConceptMap.ConceptMapElementMapComponent parseConceptMapConceptMapElementMapComponent(JsonObject json, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapElementMapComponent res = new ConceptMap.ConceptMapElementMapComponent();
    parseBackboneProperties(json, res);
    if (json.has("codeSystem"))
      res.setCodeSystemElement(parseUri(json.get("codeSystem").getAsString()));
    if (json.has("_codeSystem"))
      parseElementProperties(json.getAsJsonObject("_codeSystem"), res.getCodeSystemElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("equivalence"))
      res.setEquivalenceElement(parseEnumeration(json.get("equivalence").getAsString(), ConceptMap.ConceptEquivalence.NULL, new ConceptMap.ConceptEquivalenceEnumFactory()));
    if (json.has("_equivalence"))
      parseElementProperties(json.getAsJsonObject("_equivalence"), res.getEquivalenceElement());
    if (json.has("comments"))
      res.setCommentsElement(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getCommentsElement());
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
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("asserter"))
      res.setAsserter(parseReference(json.getAsJsonObject("asserter")));
    if (json.has("dateAsserted"))
      res.setDateAssertedElement(parseDate(json.get("dateAsserted").getAsString()));
    if (json.has("_dateAsserted"))
      parseElementProperties(json.getAsJsonObject("_dateAsserted"), res.getDateAssertedElement());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Condition.ConditionStatus.NULL, new Condition.ConditionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
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
    if (json.has("dueTo")) {
      JsonArray array = json.getAsJsonArray("dueTo");
      for (int i = 0; i < array.size(); i++) {
        res.getDueTo().add(parseConditionConditionDueToComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("occurredFollowing")) {
      JsonArray array = json.getAsJsonArray("occurredFollowing");
      for (int i = 0; i < array.size(); i++) {
        res.getOccurredFollowing().add(parseConditionConditionOccurredFollowingComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("notes"))
      res.setNotesElement(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotesElement());
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
        res.getAssessment().add(parseReference(array.get(i).getAsJsonObject()));
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
        res.getDetail().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setDetailElement(parseString(json.get("detail").getAsString()));
    if (json.has("_detail"))
      parseElementProperties(json.getAsJsonObject("_detail"), res.getDetailElement());
    return res;
  }

  private Condition.ConditionDueToComponent parseConditionConditionDueToComponent(JsonObject json, Condition owner) throws Exception {
    Condition.ConditionDueToComponent res = new Condition.ConditionDueToComponent();
    parseBackboneProperties(json, res);
    if (json.has("codeableConcept"))
      res.setCodeableConcept(parseCodeableConcept(json.getAsJsonObject("codeableConcept")));
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    return res;
  }

  private Condition.ConditionOccurredFollowingComponent parseConditionConditionOccurredFollowingComponent(JsonObject json, Condition owner) throws Exception {
    Condition.ConditionOccurredFollowingComponent res = new Condition.ConditionOccurredFollowingComponent();
    parseBackboneProperties(json, res);
    if (json.has("codeableConcept"))
      res.setCodeableConcept(parseCodeableConcept(json.getAsJsonObject("codeableConcept")));
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    return res;
  }

  private Conformance parseConformance(JsonObject json) throws Exception {
    Conformance res = new Conformance();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifierElement(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Conformance.ConformanceStatementStatus.NULL, new Conformance.ConformanceStatementStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("experimental"))
      res.setExperimentalElement(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimentalElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("software"))
      res.setSoftware(parseConformanceConformanceSoftwareComponent(json.getAsJsonObject("software"), res));
    if (json.has("implementation"))
      res.setImplementation(parseConformanceConformanceImplementationComponent(json.getAsJsonObject("implementation"), res));
    if (json.has("fhirVersion"))
      res.setFhirVersionElement(parseId(json.get("fhirVersion").getAsString()));
    if (json.has("_fhirVersion"))
      parseElementProperties(json.getAsJsonObject("_fhirVersion"), res.getFhirVersionElement());
    if (json.has("acceptUnknown"))
      res.setAcceptUnknownElement(parseBoolean(json.get("acceptUnknown").getAsBoolean()));
    if (json.has("_acceptUnknown"))
      parseElementProperties(json.getAsJsonObject("_acceptUnknown"), res.getAcceptUnknownElement());
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
        res.getProfile().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("releaseDate"))
      res.setReleaseDateElement(parseDateTime(json.get("releaseDate").getAsString()));
    if (json.has("_releaseDate"))
      parseElementProperties(json.getAsJsonObject("_releaseDate"), res.getReleaseDateElement());
    return res;
  }

  private Conformance.ConformanceImplementationComponent parseConformanceConformanceImplementationComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceImplementationComponent res = new Conformance.ConformanceImplementationComponent();
    parseBackboneProperties(json, res);
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    return res;
  }

  private Conformance.ConformanceRestComponent parseConformanceConformanceRestComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestComponent res = new Conformance.ConformanceRestComponent();
    parseBackboneProperties(json, res);
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), Conformance.RestfulConformanceMode.NULL, new Conformance.RestfulConformanceModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
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
      res.setCorsElement(parseBoolean(json.get("cors").getAsBoolean()));
    if (json.has("_cors"))
      parseElementProperties(json.getAsJsonObject("_cors"), res.getCorsElement());
    if (json.has("service")) {
      JsonArray array = json.getAsJsonArray("service");
      for (int i = 0; i < array.size(); i++) {
        res.getService().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
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
      res.setTypeElement(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("blob"))
      res.setBlobElement(parseBase64Binary(json.get("blob").getAsString()));
    if (json.has("_blob"))
      parseElementProperties(json.getAsJsonObject("_blob"), res.getBlobElement());
    return res;
  }

  private Conformance.ConformanceRestResourceComponent parseConformanceConformanceRestResourceComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("profile"))
      res.setProfile(parseReference(json.getAsJsonObject("profile")));
    if (json.has("interaction")) {
      JsonArray array = json.getAsJsonArray("interaction");
      for (int i = 0; i < array.size(); i++) {
        res.getInteraction().add(parseConformanceResourceInteractionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("versioning"))
      res.setVersioningElement(parseEnumeration(json.get("versioning").getAsString(), Conformance.VersioningPolicy.NULL, new Conformance.VersioningPolicyEnumFactory()));
    if (json.has("_versioning"))
      parseElementProperties(json.getAsJsonObject("_versioning"), res.getVersioningElement());
    if (json.has("readHistory"))
      res.setReadHistoryElement(parseBoolean(json.get("readHistory").getAsBoolean()));
    if (json.has("_readHistory"))
      parseElementProperties(json.getAsJsonObject("_readHistory"), res.getReadHistoryElement());
    if (json.has("updateCreate"))
      res.setUpdateCreateElement(parseBoolean(json.get("updateCreate").getAsBoolean()));
    if (json.has("_updateCreate"))
      parseElementProperties(json.getAsJsonObject("_updateCreate"), res.getUpdateCreateElement());
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
      res.setCodeElement(parseEnumeration(json.get("code").getAsString(), Conformance.TypeRestfulInteraction.NULL, new Conformance.TypeRestfulInteractionEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
    return res;
  }

  private Conformance.ConformanceRestResourceSearchParamComponent parseConformanceConformanceRestResourceSearchParamComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceSearchParamComponent res = new Conformance.ConformanceRestResourceSearchParamComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("definition"))
      res.setDefinitionElement(parseUri(json.get("definition").getAsString()));
    if (json.has("_definition"))
      parseElementProperties(json.getAsJsonObject("_definition"), res.getDefinitionElement());
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Conformance.SearchParamType.NULL, new Conformance.SearchParamTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
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
      res.setCodeElement(parseEnumeration(json.get("code").getAsString(), Conformance.SystemRestfulInteraction.NULL, new Conformance.SystemRestfulInteractionEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
    return res;
  }

  private Conformance.ConformanceRestOperationComponent parseConformanceConformanceRestOperationComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceRestOperationComponent res = new Conformance.ConformanceRestOperationComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("definition"))
      res.setDefinition(parseReference(json.getAsJsonObject("definition")));
    return res;
  }

  private Conformance.ConformanceMessagingComponent parseConformanceConformanceMessagingComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingComponent res = new Conformance.ConformanceMessagingComponent();
    parseBackboneProperties(json, res);
    if (json.has("endpoint"))
      res.setEndpointElement(parseUri(json.get("endpoint").getAsString()));
    if (json.has("_endpoint"))
      parseElementProperties(json.getAsJsonObject("_endpoint"), res.getEndpointElement());
    if (json.has("reliableCache"))
      res.setReliableCacheElement(parseInteger(json.get("reliableCache").getAsLong()));
    if (json.has("_reliableCache"))
      parseElementProperties(json.getAsJsonObject("_reliableCache"), res.getReliableCacheElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
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
      res.setCategoryElement(parseEnumeration(json.get("category").getAsString(), Conformance.MessageSignificanceCategory.NULL, new Conformance.MessageSignificanceCategoryEnumFactory()));
    if (json.has("_category"))
      parseElementProperties(json.getAsJsonObject("_category"), res.getCategoryElement());
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), Conformance.MessageConformanceEventMode.NULL, new Conformance.MessageConformanceEventModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
    if (json.has("protocol")) {
      JsonArray array = json.getAsJsonArray("protocol");
      for (int i = 0; i < array.size(); i++) {
        res.getProtocol().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("focus"))
      res.setFocusElement(parseCode(json.get("focus").getAsString()));
    if (json.has("_focus"))
      parseElementProperties(json.getAsJsonObject("_focus"), res.getFocusElement());
    if (json.has("request"))
      res.setRequest(parseReference(json.getAsJsonObject("request")));
    if (json.has("response"))
      res.setResponse(parseReference(json.getAsJsonObject("response")));
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
    return res;
  }

  private Conformance.ConformanceDocumentComponent parseConformanceConformanceDocumentComponent(JsonObject json, Conformance owner) throws Exception {
    Conformance.ConformanceDocumentComponent res = new Conformance.ConformanceDocumentComponent();
    parseBackboneProperties(json, res);
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), Conformance.DocumentMode.NULL, new Conformance.DocumentModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
    if (json.has("profile"))
      res.setProfile(parseReference(json.getAsJsonObject("profile")));
    return res;
  }

  private Contract parseContract(JsonObject json) throws Exception {
    Contract res = new Contract();
    parseDomainResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("subtype"))
      res.setSubtype(parseCodeableConcept(json.getAsJsonObject("subtype")));
    if (json.has("issued"))
      res.setIssuedElement(parseDateTime(json.get("issued").getAsString()));
    if (json.has("_issued"))
      parseElementProperties(json.getAsJsonObject("_issued"), res.getIssuedElement());
    if (json.has("applies"))
      res.setApplies(parsePeriod(json.getAsJsonObject("applies")));
    if (json.has("author")) {
      JsonArray array = json.getAsJsonArray("author");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthor().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("grantor")) {
      JsonArray array = json.getAsJsonArray("grantor");
      for (int i = 0; i < array.size(); i++) {
        res.getGrantor().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("grantee")) {
      JsonArray array = json.getAsJsonArray("grantee");
      for (int i = 0; i < array.size(); i++) {
        res.getGrantee().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("witness")) {
      JsonArray array = json.getAsJsonArray("witness");
      for (int i = 0; i < array.size(); i++) {
        res.getWitness().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("term")) {
      JsonArray array = json.getAsJsonArray("term");
      for (int i = 0; i < array.size(); i++) {
        res.getTerm().add(parseContractContractTermComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("friendly"))
      res.setFriendly(parseAttachment(json.getAsJsonObject("friendly")));
    if (json.has("legal"))
      res.setLegal(parseAttachment(json.getAsJsonObject("legal")));
    if (json.has("rule"))
      res.setRule(parseAttachment(json.getAsJsonObject("rule")));
    return res;
  }

  private Contract.ContractTermComponent parseContractContractTermComponent(JsonObject json, Contract owner) throws Exception {
    Contract.ContractTermComponent res = new Contract.ContractTermComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("subtype"))
      res.setSubtype(parseCodeableConcept(json.getAsJsonObject("subtype")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    return res;
  }

  private Contraindication parseContraindication(JsonObject json) throws Exception {
    Contraindication res = new Contraindication();
    parseDomainResourceProperties(json, res);
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("severity"))
      res.setSeverityElement(parseCode(json.get("severity").getAsString()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverityElement());
    if (json.has("implicated")) {
      JsonArray array = json.getAsJsonArray("implicated");
      for (int i = 0; i < array.size(); i++) {
        res.getImplicated().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("detail"))
      res.setDetailElement(parseString(json.get("detail").getAsString()));
    if (json.has("_detail"))
      parseElementProperties(json.getAsJsonObject("_detail"), res.getDetailElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("reference"))
      res.setReferenceElement(parseUri(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReferenceElement());
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
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    return res;
  }

  private Coverage parseCoverage(JsonObject json) throws Exception {
    Coverage res = new Coverage();
    parseDomainResourceProperties(json, res);
    if (json.has("issuer"))
      res.setIssuer(parseReference(json.getAsJsonObject("issuer")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("group"))
      res.setGroupElement(parseString(json.get("group").getAsString()));
    if (json.has("_group"))
      parseElementProperties(json.getAsJsonObject("_group"), res.getGroupElement());
    if (json.has("plan"))
      res.setPlanElement(parseString(json.get("plan").getAsString()));
    if (json.has("_plan"))
      parseElementProperties(json.getAsJsonObject("_plan"), res.getPlanElement());
    if (json.has("subplan"))
      res.setSubplanElement(parseString(json.get("subplan").getAsString()));
    if (json.has("_subplan"))
      parseElementProperties(json.getAsJsonObject("_subplan"), res.getSubplanElement());
    if (json.has("dependent"))
      res.setDependentElement(parseInteger(json.get("dependent").getAsLong()));
    if (json.has("_dependent"))
      parseElementProperties(json.getAsJsonObject("_dependent"), res.getDependentElement());
    if (json.has("sequence"))
      res.setSequenceElement(parseInteger(json.get("sequence").getAsLong()));
    if (json.has("_sequence"))
      parseElementProperties(json.getAsJsonObject("_sequence"), res.getSequenceElement());
    if (json.has("subscriber"))
      res.setSubscriber(parseReference(json.getAsJsonObject("subscriber")));
    if (json.has("network"))
      res.setNetwork(parseIdentifier(json.getAsJsonObject("network")));
    if (json.has("contract")) {
      JsonArray array = json.getAsJsonArray("contract");
      for (int i = 0; i < array.size(); i++) {
        res.getContract().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private DataElement parseDataElement(JsonObject json) throws Exception {
    DataElement res = new DataElement();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DataElement.ResourceObservationDefStatus.NULL, new DataElement.ResourceObservationDefStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
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
      res.setQuestionElement(parseString(json.get("question").getAsString()));
    if (json.has("_question"))
      parseElementProperties(json.getAsJsonObject("_question"), res.getQuestionElement());
    if (json.has("definition"))
      res.setDefinitionElement(parseString(json.get("definition").getAsString()));
    if (json.has("_definition"))
      parseElementProperties(json.getAsJsonObject("_definition"), res.getDefinitionElement());
    if (json.has("comments"))
      res.setCommentsElement(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getCommentsElement());
    if (json.has("requirements"))
      res.setRequirementsElement(parseString(json.get("requirements").getAsString()));
    if (json.has("_requirements"))
      parseElementProperties(json.getAsJsonObject("_requirements"), res.getRequirementsElement());
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
      res.setTypeElement(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    Type example = parseType("example", json);
    if (example != null)
      res.setExample(example);
    if (json.has("maxLength"))
      res.setMaxLengthElement(parseInteger(json.get("maxLength").getAsLong()));
    if (json.has("_maxLength"))
      parseElementProperties(json.getAsJsonObject("_maxLength"), res.getMaxLengthElement());
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
      res.setIsExtensibleElement(parseBoolean(json.get("isExtensible").getAsBoolean()));
    if (json.has("_isExtensible"))
      parseElementProperties(json.getAsJsonObject("_isExtensible"), res.getIsExtensibleElement());
    if (json.has("conformance"))
      res.setConformanceElement(parseEnumeration(json.get("conformance").getAsString(), DataElement.BindingConformance.NULL, new DataElement.BindingConformanceEnumFactory()));
    if (json.has("_conformance"))
      parseElementProperties(json.getAsJsonObject("_conformance"), res.getConformanceElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("valueSet"))
      res.setValueSet(parseReference(json.getAsJsonObject("valueSet")));
    return res;
  }

  private DataElement.DataElementMappingComponent parseDataElementDataElementMappingComponent(JsonObject json, DataElement owner) throws Exception {
    DataElement.DataElementMappingComponent res = new DataElement.DataElementMappingComponent();
    parseBackboneProperties(json, res);
    if (json.has("uri"))
      res.setUriElement(parseUri(json.get("uri").getAsString()));
    if (json.has("_uri"))
      parseElementProperties(json.getAsJsonObject("_uri"), res.getUriElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("comments"))
      res.setCommentsElement(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getCommentsElement());
    if (json.has("map"))
      res.setMapElement(parseString(json.get("map").getAsString()));
    if (json.has("_map"))
      parseElementProperties(json.getAsJsonObject("_map"), res.getMapElement());
    return res;
  }

  private Device parseDevice(JsonObject json) throws Exception {
    Device res = new Device();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("manufacturer"))
      res.setManufacturerElement(parseString(json.get("manufacturer").getAsString()));
    if (json.has("_manufacturer"))
      parseElementProperties(json.getAsJsonObject("_manufacturer"), res.getManufacturerElement());
    if (json.has("model"))
      res.setModelElement(parseString(json.get("model").getAsString()));
    if (json.has("_model"))
      parseElementProperties(json.getAsJsonObject("_model"), res.getModelElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("expiry"))
      res.setExpiryElement(parseDate(json.get("expiry").getAsString()));
    if (json.has("_expiry"))
      parseElementProperties(json.getAsJsonObject("_expiry"), res.getExpiryElement());
    if (json.has("udi"))
      res.setUdiElement(parseString(json.get("udi").getAsString()));
    if (json.has("_udi"))
      parseElementProperties(json.getAsJsonObject("_udi"), res.getUdiElement());
    if (json.has("lotNumber"))
      res.setLotNumberElement(parseString(json.get("lotNumber").getAsString()));
    if (json.has("_lotNumber"))
      parseElementProperties(json.getAsJsonObject("_lotNumber"), res.getLotNumberElement());
    if (json.has("owner"))
      res.setOwner(parseReference(json.getAsJsonObject("owner")));
    if (json.has("location"))
      res.setLocation(parseReference(json.getAsJsonObject("location")));
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("contact")) {
      JsonArray array = json.getAsJsonArray("contact");
      for (int i = 0; i < array.size(); i++) {
        res.getContact().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    return res;
  }

  private DeviceComponent parseDeviceComponent(JsonObject json) throws Exception {
    DeviceComponent res = new DeviceComponent();
    parseDomainResourceProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("lastSystemChange"))
      res.setLastSystemChangeElement(parseInstant(json.get("lastSystemChange").getAsString()));
    if (json.has("_lastSystemChange"))
      parseElementProperties(json.getAsJsonObject("_lastSystemChange"), res.getLastSystemChangeElement());
    if (json.has("source"))
      res.setSource(parseReference(json.getAsJsonObject("source")));
    if (json.has("parent"))
      res.setParent(parseReference(json.getAsJsonObject("parent")));
    if (json.has("operationalStatus")) {
      JsonArray array = json.getAsJsonArray("operationalStatus");
      for (int i = 0; i < array.size(); i++) {
        res.getOperationalStatus().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("parameterGroup"))
      res.setParameterGroup(parseCodeableConcept(json.getAsJsonObject("parameterGroup")));
    if (json.has("measurementPrinciple"))
      res.setMeasurementPrinciple(parseCodeableConcept(json.getAsJsonObject("measurementPrinciple")));
    if (json.has("productionSpecification")) {
      JsonArray array = json.getAsJsonArray("productionSpecification");
      for (int i = 0; i < array.size(); i++) {
        res.getProductionSpecification().add(parseDeviceComponentDeviceComponentProductionSpecificationComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("languageCode"))
      res.setLanguageCode(parseCodeableConcept(json.getAsJsonObject("languageCode")));
    return res;
  }

  private DeviceComponent.DeviceComponentProductionSpecificationComponent parseDeviceComponentDeviceComponentProductionSpecificationComponent(JsonObject json, DeviceComponent owner) throws Exception {
    DeviceComponent.DeviceComponentProductionSpecificationComponent res = new DeviceComponent.DeviceComponentProductionSpecificationComponent();
    parseBackboneProperties(json, res);
    if (json.has("specType"))
      res.setSpecType(parseCodeableConcept(json.getAsJsonObject("specType")));
    if (json.has("componentId"))
      res.setComponentId(parseIdentifier(json.getAsJsonObject("componentId")));
    if (json.has("productionSpec"))
      res.setProductionSpecElement(parseString(json.get("productionSpec").getAsString()));
    if (json.has("_productionSpec"))
      parseElementProperties(json.getAsJsonObject("_productionSpec"), res.getProductionSpecElement());
    return res;
  }

  private DeviceUseRequest parseDeviceUseRequest(JsonObject json) throws Exception {
    DeviceUseRequest res = new DeviceUseRequest();
    parseDomainResourceProperties(json, res);
    if (json.has("bodySite")) {
      JsonArray array = json.getAsJsonArray("bodySite");
      for (int i = 0; i < array.size(); i++) {
        res.getBodySite().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DeviceUseRequest.DeviceUseRequestStatus.NULL, new DeviceUseRequest.DeviceUseRequestStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), DeviceUseRequest.DeviceUseRequestMode.NULL, new DeviceUseRequest.DeviceUseRequestModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
    if (json.has("device"))
      res.setDevice(parseReference(json.getAsJsonObject("device")));
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("indication")) {
      JsonArray array = json.getAsJsonArray("indication");
      for (int i = 0; i < array.size(); i++) {
        res.getIndication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("notes")) {
      JsonArray array = json.getAsJsonArray("notes");
      for (int i = 0; i < array.size(); i++) {
        res.getNotes().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_notes")) {
      JsonArray array = json.getAsJsonArray("_notes");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getNotes().size())
          res.getNotes().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getNotes().get(i));
      }
    };
    if (json.has("prnReason")) {
      JsonArray array = json.getAsJsonArray("prnReason");
      for (int i = 0; i < array.size(); i++) {
        res.getPrnReason().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("orderedOn"))
      res.setOrderedOnElement(parseDateTime(json.get("orderedOn").getAsString()));
    if (json.has("_orderedOn"))
      parseElementProperties(json.getAsJsonObject("_orderedOn"), res.getOrderedOnElement());
    if (json.has("recordedOn"))
      res.setRecordedOnElement(parseDateTime(json.get("recordedOn").getAsString()));
    if (json.has("_recordedOn"))
      parseElementProperties(json.getAsJsonObject("_recordedOn"), res.getRecordedOnElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    if (json.has("priority"))
      res.setPriorityElement(parseEnumeration(json.get("priority").getAsString(), DeviceUseRequest.DeviceUseRequestPriority.NULL, new DeviceUseRequest.DeviceUseRequestPriorityEnumFactory()));
    if (json.has("_priority"))
      parseElementProperties(json.getAsJsonObject("_priority"), res.getPriorityElement());
    return res;
  }

  private DeviceUseStatement parseDeviceUseStatement(JsonObject json) throws Exception {
    DeviceUseStatement res = new DeviceUseStatement();
    parseDomainResourceProperties(json, res);
    if (json.has("bodySite")) {
      JsonArray array = json.getAsJsonArray("bodySite");
      for (int i = 0; i < array.size(); i++) {
        res.getBodySite().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("whenUsed"))
      res.setWhenUsed(parsePeriod(json.getAsJsonObject("whenUsed")));
    if (json.has("device"))
      res.setDevice(parseReference(json.getAsJsonObject("device")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("indication")) {
      JsonArray array = json.getAsJsonArray("indication");
      for (int i = 0; i < array.size(); i++) {
        res.getIndication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("notes")) {
      JsonArray array = json.getAsJsonArray("notes");
      for (int i = 0; i < array.size(); i++) {
        res.getNotes().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_notes")) {
      JsonArray array = json.getAsJsonArray("_notes");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getNotes().size())
          res.getNotes().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getNotes().get(i));
      }
    };
    if (json.has("recordedOn"))
      res.setRecordedOnElement(parseDateTime(json.get("recordedOn").getAsString()));
    if (json.has("_recordedOn"))
      parseElementProperties(json.getAsJsonObject("_recordedOn"), res.getRecordedOnElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    return res;
  }

  private DiagnosticOrder parseDiagnosticOrder(JsonObject json) throws Exception {
    DiagnosticOrder res = new DiagnosticOrder();
    parseDomainResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("orderer"))
      res.setOrderer(parseReference(json.getAsJsonObject("orderer")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("clinicalNotes"))
      res.setClinicalNotesElement(parseString(json.get("clinicalNotes").getAsString()));
    if (json.has("_clinicalNotes"))
      parseElementProperties(json.getAsJsonObject("_clinicalNotes"), res.getClinicalNotesElement());
    if (json.has("supportingInformation")) {
      JsonArray array = json.getAsJsonArray("supportingInformation");
      for (int i = 0; i < array.size(); i++) {
        res.getSupportingInformation().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("specimen")) {
      JsonArray array = json.getAsJsonArray("specimen");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecimen().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DiagnosticOrder.DiagnosticOrderStatus.NULL, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("priority"))
      res.setPriorityElement(parseEnumeration(json.get("priority").getAsString(), DiagnosticOrder.DiagnosticOrderPriority.NULL, new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory()));
    if (json.has("_priority"))
      parseElementProperties(json.getAsJsonObject("_priority"), res.getPriorityElement());
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
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DiagnosticOrder.DiagnosticOrderStatus.NULL, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("description"))
      res.setDescription(parseCodeableConcept(json.getAsJsonObject("description")));
    if (json.has("dateTime"))
      res.setDateTimeElement(parseDateTime(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTimeElement());
    if (json.has("actor"))
      res.setActor(parseReference(json.getAsJsonObject("actor")));
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
        res.getSpecimen().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("bodySite"))
      res.setBodySite(parseCodeableConcept(json.getAsJsonObject("bodySite")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DiagnosticOrder.DiagnosticOrderStatus.NULL, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
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
    parseDomainResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getAsJsonObject("name")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DiagnosticReport.DiagnosticReportStatus.NULL, new DiagnosticReport.DiagnosticReportStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("issued"))
      res.setIssuedElement(parseDateTime(json.get("issued").getAsString()));
    if (json.has("_issued"))
      parseElementProperties(json.getAsJsonObject("_issued"), res.getIssuedElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("performer"))
      res.setPerformer(parseReference(json.getAsJsonObject("performer")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("requestDetail")) {
      JsonArray array = json.getAsJsonArray("requestDetail");
      for (int i = 0; i < array.size(); i++) {
        res.getRequestDetail().add(parseReference(array.get(i).getAsJsonObject()));
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
        res.getSpecimen().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("result")) {
      JsonArray array = json.getAsJsonArray("result");
      for (int i = 0; i < array.size(); i++) {
        res.getResult().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("imagingStudy")) {
      JsonArray array = json.getAsJsonArray("imagingStudy");
      for (int i = 0; i < array.size(); i++) {
        res.getImagingStudy().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("image")) {
      JsonArray array = json.getAsJsonArray("image");
      for (int i = 0; i < array.size(); i++) {
        res.getImage().add(parseDiagnosticReportDiagnosticReportImageComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("conclusion"))
      res.setConclusionElement(parseString(json.get("conclusion").getAsString()));
    if (json.has("_conclusion"))
      parseElementProperties(json.getAsJsonObject("_conclusion"), res.getConclusionElement());
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
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    if (json.has("link"))
      res.setLink(parseReference(json.getAsJsonObject("link")));
    return res;
  }

  private DocumentManifest parseDocumentManifest(JsonObject json) throws Exception {
    DocumentManifest res = new DocumentManifest();
    parseDomainResourceProperties(json, res);
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
        res.getSubject().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("recipient")) {
      JsonArray array = json.getAsJsonArray("recipient");
      for (int i = 0; i < array.size(); i++) {
        res.getRecipient().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("author")) {
      JsonArray array = json.getAsJsonArray("author");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthor().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("created"))
      res.setCreatedElement(parseDateTime(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreatedElement());
    if (json.has("source"))
      res.setSourceElement(parseUri(json.get("source").getAsString()));
    if (json.has("_source"))
      parseElementProperties(json.getAsJsonObject("_source"), res.getSourceElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DocumentManifest.DocumentReferenceStatus.NULL, new DocumentManifest.DocumentReferenceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("supercedes"))
      res.setSupercedes(parseReference(json.getAsJsonObject("supercedes")));
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("confidentiality"))
      res.setConfidentiality(parseCodeableConcept(json.getAsJsonObject("confidentiality")));
    if (json.has("content")) {
      JsonArray array = json.getAsJsonArray("content");
      for (int i = 0; i < array.size(); i++) {
        res.getContent().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private DocumentReference parseDocumentReference(JsonObject json) throws Exception {
    DocumentReference res = new DocumentReference();
    parseDomainResourceProperties(json, res);
    if (json.has("masterIdentifier"))
      res.setMasterIdentifier(parseIdentifier(json.getAsJsonObject("masterIdentifier")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("class"))
      res.setClass_(parseCodeableConcept(json.getAsJsonObject("class")));
    if (json.has("author")) {
      JsonArray array = json.getAsJsonArray("author");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthor().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("custodian"))
      res.setCustodian(parseReference(json.getAsJsonObject("custodian")));
    if (json.has("policyManager"))
      res.setPolicyManagerElement(parseUri(json.get("policyManager").getAsString()));
    if (json.has("_policyManager"))
      parseElementProperties(json.getAsJsonObject("_policyManager"), res.getPolicyManagerElement());
    if (json.has("authenticator"))
      res.setAuthenticator(parseReference(json.getAsJsonObject("authenticator")));
    if (json.has("created"))
      res.setCreatedElement(parseDateTime(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreatedElement());
    if (json.has("indexed"))
      res.setIndexedElement(parseInstant(json.get("indexed").getAsString()));
    if (json.has("_indexed"))
      parseElementProperties(json.getAsJsonObject("_indexed"), res.getIndexedElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), DocumentReference.DocumentReferenceStatus.NULL, new DocumentReference.DocumentReferenceStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("docStatus"))
      res.setDocStatus(parseCodeableConcept(json.getAsJsonObject("docStatus")));
    if (json.has("relatesTo")) {
      JsonArray array = json.getAsJsonArray("relatesTo");
      for (int i = 0; i < array.size(); i++) {
        res.getRelatesTo().add(parseDocumentReferenceDocumentReferenceRelatesToComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("confidentiality")) {
      JsonArray array = json.getAsJsonArray("confidentiality");
      for (int i = 0; i < array.size(); i++) {
        res.getConfidentiality().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("primaryLanguage"))
      res.setPrimaryLanguageElement(parseCode(json.get("primaryLanguage").getAsString()));
    if (json.has("_primaryLanguage"))
      parseElementProperties(json.getAsJsonObject("_primaryLanguage"), res.getPrimaryLanguageElement());
    if (json.has("mimeType"))
      res.setMimeTypeElement(parseCode(json.get("mimeType").getAsString()));
    if (json.has("_mimeType"))
      parseElementProperties(json.getAsJsonObject("_mimeType"), res.getMimeTypeElement());
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
      res.setSizeElement(parseInteger(json.get("size").getAsLong()));
    if (json.has("_size"))
      parseElementProperties(json.getAsJsonObject("_size"), res.getSizeElement());
    if (json.has("hash"))
      res.setHashElement(parseBase64Binary(json.get("hash").getAsString()));
    if (json.has("_hash"))
      parseElementProperties(json.getAsJsonObject("_hash"), res.getHashElement());
    if (json.has("location"))
      res.setLocationElement(parseUri(json.get("location").getAsString()));
    if (json.has("_location"))
      parseElementProperties(json.getAsJsonObject("_location"), res.getLocationElement());
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
      res.setCodeElement(parseEnumeration(json.get("code").getAsString(), DocumentReference.DocumentRelationshipType.NULL, new DocumentReference.DocumentRelationshipTypeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    return res;
  }

  private DocumentReference.DocumentReferenceServiceComponent parseDocumentReferenceDocumentReferenceServiceComponent(JsonObject json, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceServiceComponent res = new DocumentReference.DocumentReferenceServiceComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("address"))
      res.setAddressElement(parseString(json.get("address").getAsString()));
    if (json.has("_address"))
      parseElementProperties(json.getAsJsonObject("_address"), res.getAddressElement());
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
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("value"))
      res.setValueElement(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
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

  private Eligibility parseEligibility(JsonObject json) throws Exception {
    Eligibility res = new Eligibility();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("ruleset"))
      res.setRuleset(parseCoding(json.getAsJsonObject("ruleset")));
    if (json.has("originalRuleset"))
      res.setOriginalRuleset(parseCoding(json.getAsJsonObject("originalRuleset")));
    if (json.has("date"))
      res.setDateElement(parseDate(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    if (json.has("provider"))
      res.setProvider(parseReference(json.getAsJsonObject("provider")));
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
    return res;
  }

  private Encounter parseEncounter(JsonObject json) throws Exception {
    Encounter res = new Encounter();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Encounter.EncounterState.NULL, new Encounter.EncounterStateEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("class"))
      res.setClass_Element(parseEnumeration(json.get("class").getAsString(), Encounter.EncounterClass.NULL, new Encounter.EncounterClassEnumFactory()));
    if (json.has("_class"))
      parseElementProperties(json.getAsJsonObject("_class"), res.getClass_Element());
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("participant")) {
      JsonArray array = json.getAsJsonArray("participant");
      for (int i = 0; i < array.size(); i++) {
        res.getParticipant().add(parseEncounterEncounterParticipantComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("fulfills"))
      res.setFulfills(parseReference(json.getAsJsonObject("fulfills")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("length"))
      res.setLength(parseDuration(json.getAsJsonObject("length")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("indication"))
      res.setIndication(parseReference(json.getAsJsonObject("indication")));
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
      res.setServiceProvider(parseReference(json.getAsJsonObject("serviceProvider")));
    if (json.has("partOf"))
      res.setPartOf(parseReference(json.getAsJsonObject("partOf")));
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
      res.setIndividual(parseReference(json.getAsJsonObject("individual")));
    return res;
  }

  private Encounter.EncounterHospitalizationComponent parseEncounterEncounterHospitalizationComponent(JsonObject json, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationComponent res = new Encounter.EncounterHospitalizationComponent();
    parseBackboneProperties(json, res);
    if (json.has("preAdmissionIdentifier"))
      res.setPreAdmissionIdentifier(parseIdentifier(json.getAsJsonObject("preAdmissionIdentifier")));
    if (json.has("origin"))
      res.setOrigin(parseReference(json.getAsJsonObject("origin")));
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
      res.setDestination(parseReference(json.getAsJsonObject("destination")));
    if (json.has("dischargeDisposition"))
      res.setDischargeDisposition(parseCodeableConcept(json.getAsJsonObject("dischargeDisposition")));
    if (json.has("dischargeDiagnosis"))
      res.setDischargeDiagnosis(parseReference(json.getAsJsonObject("dischargeDiagnosis")));
    if (json.has("reAdmission"))
      res.setReAdmissionElement(parseBoolean(json.get("reAdmission").getAsBoolean()));
    if (json.has("_reAdmission"))
      parseElementProperties(json.getAsJsonObject("_reAdmission"), res.getReAdmissionElement());
    return res;
  }

  private Encounter.EncounterHospitalizationAccomodationComponent parseEncounterEncounterHospitalizationAccomodationComponent(JsonObject json, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationAccomodationComponent res = new Encounter.EncounterHospitalizationAccomodationComponent();
    parseBackboneProperties(json, res);
    if (json.has("bed"))
      res.setBed(parseReference(json.getAsJsonObject("bed")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private Encounter.EncounterLocationComponent parseEncounterEncounterLocationComponent(JsonObject json, Encounter owner) throws Exception {
    Encounter.EncounterLocationComponent res = new Encounter.EncounterLocationComponent();
    parseBackboneProperties(json, res);
    if (json.has("location"))
      res.setLocation(parseReference(json.getAsJsonObject("location")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private ExplanationOfBenefit parseExplanationOfBenefit(JsonObject json) throws Exception {
    ExplanationOfBenefit res = new ExplanationOfBenefit();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("request"))
      res.setRequest(parseReference(json.getAsJsonObject("request")));
    if (json.has("requestIdentifier")) {
      JsonArray array = json.getAsJsonArray("requestIdentifier");
      for (int i = 0; i < array.size(); i++) {
        res.getRequestIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("outcome"))
      res.setOutcomeElement(parseEnumeration(json.get("outcome").getAsString(), ExplanationOfBenefit.RSLink.NULL, new ExplanationOfBenefit.RSLinkEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcomeElement());
    if (json.has("disposition"))
      res.setDispositionElement(parseString(json.get("disposition").getAsString()));
    if (json.has("_disposition"))
      parseElementProperties(json.getAsJsonObject("_disposition"), res.getDispositionElement());
    if (json.has("ruleset"))
      res.setRuleset(parseCoding(json.getAsJsonObject("ruleset")));
    if (json.has("originalRuleset"))
      res.setOriginalRuleset(parseCoding(json.getAsJsonObject("originalRuleset")));
    if (json.has("date"))
      res.setDateElement(parseDate(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
    if (json.has("requestProvider"))
      res.setRequestProvider(parseReference(json.getAsJsonObject("requestProvider")));
    if (json.has("requestOrganization"))
      res.setRequestOrganization(parseReference(json.getAsJsonObject("requestOrganization")));
    return res;
  }

  private ExtensionDefinition parseExtensionDefinition(JsonObject json) throws Exception {
    ExtensionDefinition res = new ExtensionDefinition();
    parseDomainResourceProperties(json, res);
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), ExtensionDefinition.ResourceProfileStatus.NULL, new ExtensionDefinition.ResourceProfileStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("experimental"))
      res.setExperimentalElement(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimentalElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("requirements"))
      res.setRequirementsElement(parseString(json.get("requirements").getAsString()));
    if (json.has("_requirements"))
      parseElementProperties(json.getAsJsonObject("_requirements"), res.getRequirementsElement());
    if (json.has("mapping")) {
      JsonArray array = json.getAsJsonArray("mapping");
      for (int i = 0; i < array.size(); i++) {
        res.getMapping().add(parseExtensionDefinitionExtensionDefinitionMappingComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("contextType"))
      res.setContextTypeElement(parseEnumeration(json.get("contextType").getAsString(), ExtensionDefinition.ExtensionContext.NULL, new ExtensionDefinition.ExtensionContextEnumFactory()));
    if (json.has("_contextType"))
      parseElementProperties(json.getAsJsonObject("_contextType"), res.getContextTypeElement());
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
        res.getElement().add(parseElementDefinition(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private ExtensionDefinition.ExtensionDefinitionMappingComponent parseExtensionDefinitionExtensionDefinitionMappingComponent(JsonObject json, ExtensionDefinition owner) throws Exception {
    ExtensionDefinition.ExtensionDefinitionMappingComponent res = new ExtensionDefinition.ExtensionDefinitionMappingComponent();
    parseBackboneProperties(json, res);
    if (json.has("identity"))
      res.setIdentityElement(parseId(json.get("identity").getAsString()));
    if (json.has("_identity"))
      parseElementProperties(json.getAsJsonObject("_identity"), res.getIdentityElement());
    if (json.has("uri"))
      res.setUriElement(parseUri(json.get("uri").getAsString()));
    if (json.has("_uri"))
      parseElementProperties(json.getAsJsonObject("_uri"), res.getUriElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("comments"))
      res.setCommentsElement(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getCommentsElement());
    return res;
  }

  private FamilyHistory parseFamilyHistory(JsonObject json) throws Exception {
    FamilyHistory res = new FamilyHistory();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("note"))
      res.setNoteElement(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNoteElement());
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
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
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
      res.setNoteElement(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNoteElement());
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
      res.setNoteElement(parseString(json.get("note").getAsString()));
    if (json.has("_note"))
      parseElementProperties(json.getAsJsonObject("_note"), res.getNoteElement());
    return res;
  }

  private Group parseGroup(JsonObject json) throws Exception {
    Group res = new Group();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Group.GroupType.NULL, new Group.GroupTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("actual"))
      res.setActualElement(parseBoolean(json.get("actual").getAsBoolean()));
    if (json.has("_actual"))
      parseElementProperties(json.getAsJsonObject("_actual"), res.getActualElement());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("quantity"))
      res.setQuantityElement(parseInteger(json.get("quantity").getAsLong()));
    if (json.has("_quantity"))
      parseElementProperties(json.getAsJsonObject("_quantity"), res.getQuantityElement());
    if (json.has("characteristic")) {
      JsonArray array = json.getAsJsonArray("characteristic");
      for (int i = 0; i < array.size(); i++) {
        res.getCharacteristic().add(parseGroupGroupCharacteristicComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("member")) {
      JsonArray array = json.getAsJsonArray("member");
      for (int i = 0; i < array.size(); i++) {
        res.getMember().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setExcludeElement(parseBoolean(json.get("exclude").getAsBoolean()));
    if (json.has("_exclude"))
      parseElementProperties(json.getAsJsonObject("_exclude"), res.getExcludeElement());
    return res;
  }

  private HealthcareService parseHealthcareService(JsonObject json) throws Exception {
    HealthcareService res = new HealthcareService();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("location"))
      res.setLocation(parseReference(json.getAsJsonObject("location")));
    if (json.has("serviceCategory"))
      res.setServiceCategory(parseCodeableConcept(json.getAsJsonObject("serviceCategory")));
    if (json.has("serviceType")) {
      JsonArray array = json.getAsJsonArray("serviceType");
      for (int i = 0; i < array.size(); i++) {
        res.getServiceType().add(parseHealthcareServiceServiceTypeComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("serviceName"))
      res.setServiceNameElement(parseString(json.get("serviceName").getAsString()));
    if (json.has("_serviceName"))
      parseElementProperties(json.getAsJsonObject("_serviceName"), res.getServiceNameElement());
    if (json.has("comment"))
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    if (json.has("extraDetails"))
      res.setExtraDetailsElement(parseString(json.get("extraDetails").getAsString()));
    if (json.has("_extraDetails"))
      parseElementProperties(json.getAsJsonObject("_extraDetails"), res.getExtraDetailsElement());
    if (json.has("freeProvisionCode"))
      res.setFreeProvisionCode(parseCodeableConcept(json.getAsJsonObject("freeProvisionCode")));
    if (json.has("eligibility"))
      res.setEligibility(parseCodeableConcept(json.getAsJsonObject("eligibility")));
    if (json.has("eligibilityNote"))
      res.setEligibilityNoteElement(parseString(json.get("eligibilityNote").getAsString()));
    if (json.has("_eligibilityNote"))
      parseElementProperties(json.getAsJsonObject("_eligibilityNote"), res.getEligibilityNoteElement());
    if (json.has("appointmentRequired"))
      res.setAppointmentRequired(parseCodeableConcept(json.getAsJsonObject("appointmentRequired")));
    if (json.has("imageURI"))
      res.setImageURIElement(parseUri(json.get("imageURI").getAsString()));
    if (json.has("_imageURI"))
      parseElementProperties(json.getAsJsonObject("_imageURI"), res.getImageURIElement());
    if (json.has("availableTime")) {
      JsonArray array = json.getAsJsonArray("availableTime");
      for (int i = 0; i < array.size(); i++) {
        res.getAvailableTime().add(parseHealthcareServiceHealthcareServiceAvailableTimeComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("notAvailableTime")) {
      JsonArray array = json.getAsJsonArray("notAvailableTime");
      for (int i = 0; i < array.size(); i++) {
        res.getNotAvailableTime().add(parseHealthcareServiceHealthcareServiceNotAvailableTimeComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("availabilityExceptions"))
      res.setAvailabilityExceptionsElement(parseString(json.get("availabilityExceptions").getAsString()));
    if (json.has("_availabilityExceptions"))
      parseElementProperties(json.getAsJsonObject("_availabilityExceptions"), res.getAvailabilityExceptionsElement());
    if (json.has("publicKey"))
      res.setPublicKeyElement(parseString(json.get("publicKey").getAsString()));
    if (json.has("_publicKey"))
      parseElementProperties(json.getAsJsonObject("_publicKey"), res.getPublicKeyElement());
    if (json.has("programName")) {
      JsonArray array = json.getAsJsonArray("programName");
      for (int i = 0; i < array.size(); i++) {
        res.getProgramName().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_programName")) {
      JsonArray array = json.getAsJsonArray("_programName");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getProgramName().size())
          res.getProgramName().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getProgramName().get(i));
      }
    };
    if (json.has("contactPoint")) {
      JsonArray array = json.getAsJsonArray("contactPoint");
      for (int i = 0; i < array.size(); i++) {
        res.getContactPoint().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("characteristic")) {
      JsonArray array = json.getAsJsonArray("characteristic");
      for (int i = 0; i < array.size(); i++) {
        res.getCharacteristic().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("referralMethod")) {
      JsonArray array = json.getAsJsonArray("referralMethod");
      for (int i = 0; i < array.size(); i++) {
        res.getReferralMethod().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("setting")) {
      JsonArray array = json.getAsJsonArray("setting");
      for (int i = 0; i < array.size(); i++) {
        res.getSetting().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("targetGroup")) {
      JsonArray array = json.getAsJsonArray("targetGroup");
      for (int i = 0; i < array.size(); i++) {
        res.getTargetGroup().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("coverageArea")) {
      JsonArray array = json.getAsJsonArray("coverageArea");
      for (int i = 0; i < array.size(); i++) {
        res.getCoverageArea().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("catchmentArea")) {
      JsonArray array = json.getAsJsonArray("catchmentArea");
      for (int i = 0; i < array.size(); i++) {
        res.getCatchmentArea().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("serviceCode")) {
      JsonArray array = json.getAsJsonArray("serviceCode");
      for (int i = 0; i < array.size(); i++) {
        res.getServiceCode().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private HealthcareService.ServiceTypeComponent parseHealthcareServiceServiceTypeComponent(JsonObject json, HealthcareService owner) throws Exception {
    HealthcareService.ServiceTypeComponent res = new HealthcareService.ServiceTypeComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("specialty")) {
      JsonArray array = json.getAsJsonArray("specialty");
      for (int i = 0; i < array.size(); i++) {
        res.getSpecialty().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private HealthcareService.HealthcareServiceAvailableTimeComponent parseHealthcareServiceHealthcareServiceAvailableTimeComponent(JsonObject json, HealthcareService owner) throws Exception {
    HealthcareService.HealthcareServiceAvailableTimeComponent res = new HealthcareService.HealthcareServiceAvailableTimeComponent();
    parseBackboneProperties(json, res);
    if (json.has("daysOfWeek")) {
      JsonArray array = json.getAsJsonArray("daysOfWeek");
      for (int i = 0; i < array.size(); i++) {
        res.getDaysOfWeek().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("allDay"))
      res.setAllDayElement(parseBoolean(json.get("allDay").getAsBoolean()));
    if (json.has("_allDay"))
      parseElementProperties(json.getAsJsonObject("_allDay"), res.getAllDayElement());
    if (json.has("availableStartTime"))
      res.setAvailableStartTimeElement(parseDateTime(json.get("availableStartTime").getAsString()));
    if (json.has("_availableStartTime"))
      parseElementProperties(json.getAsJsonObject("_availableStartTime"), res.getAvailableStartTimeElement());
    if (json.has("availableEndTime"))
      res.setAvailableEndTimeElement(parseDateTime(json.get("availableEndTime").getAsString()));
    if (json.has("_availableEndTime"))
      parseElementProperties(json.getAsJsonObject("_availableEndTime"), res.getAvailableEndTimeElement());
    return res;
  }

  private HealthcareService.HealthcareServiceNotAvailableTimeComponent parseHealthcareServiceHealthcareServiceNotAvailableTimeComponent(JsonObject json, HealthcareService owner) throws Exception {
    HealthcareService.HealthcareServiceNotAvailableTimeComponent res = new HealthcareService.HealthcareServiceNotAvailableTimeComponent();
    parseBackboneProperties(json, res);
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("startDate"))
      res.setStartDateElement(parseDateTime(json.get("startDate").getAsString()));
    if (json.has("_startDate"))
      parseElementProperties(json.getAsJsonObject("_startDate"), res.getStartDateElement());
    if (json.has("endDate"))
      res.setEndDateElement(parseDateTime(json.get("endDate").getAsString()));
    if (json.has("_endDate"))
      parseElementProperties(json.getAsJsonObject("_endDate"), res.getEndDateElement());
    return res;
  }

  private ImagingStudy parseImagingStudy(JsonObject json) throws Exception {
    ImagingStudy res = new ImagingStudy();
    parseDomainResourceProperties(json, res);
    if (json.has("started"))
      res.setStartedElement(parseDateTime(json.get("started").getAsString()));
    if (json.has("_started"))
      parseElementProperties(json.getAsJsonObject("_started"), res.getStartedElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("uid"))
      res.setUidElement(parseOid(json.get("uid").getAsString()));
    if (json.has("_uid"))
      parseElementProperties(json.getAsJsonObject("_uid"), res.getUidElement());
    if (json.has("accession"))
      res.setAccession(parseIdentifier(json.getAsJsonObject("accession")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("order")) {
      JsonArray array = json.getAsJsonArray("order");
      for (int i = 0; i < array.size(); i++) {
        res.getOrder().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("modalityList")) {
      JsonArray array = json.getAsJsonArray("modalityList");
      for (int i = 0; i < array.size(); i++) {
        res.getModalityList().add(parseEnumeration(array.get(i).getAsString(), ImagingStudy.ImagingModality.NULL, new ImagingStudy.ImagingModalityEnumFactory()));
      }
    };
    if (json.has("_modalityList")) {
      JsonArray array = json.getAsJsonArray("_modalityList");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getModalityList().size())
          res.getModalityList().add(parseEnumeration(null, ImagingStudy.ImagingModality.NULL, new ImagingStudy.ImagingModalityEnumFactory()));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getModalityList().get(i));
      }
    };
    if (json.has("referrer"))
      res.setReferrer(parseReference(json.getAsJsonObject("referrer")));
    if (json.has("availability"))
      res.setAvailabilityElement(parseEnumeration(json.get("availability").getAsString(), ImagingStudy.InstanceAvailability.NULL, new ImagingStudy.InstanceAvailabilityEnumFactory()));
    if (json.has("_availability"))
      parseElementProperties(json.getAsJsonObject("_availability"), res.getAvailabilityElement());
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    if (json.has("numberOfSeries"))
      res.setNumberOfSeriesElement(parseInteger(json.get("numberOfSeries").getAsLong()));
    if (json.has("_numberOfSeries"))
      parseElementProperties(json.getAsJsonObject("_numberOfSeries"), res.getNumberOfSeriesElement());
    if (json.has("numberOfInstances"))
      res.setNumberOfInstancesElement(parseInteger(json.get("numberOfInstances").getAsLong()));
    if (json.has("_numberOfInstances"))
      parseElementProperties(json.getAsJsonObject("_numberOfInstances"), res.getNumberOfInstancesElement());
    if (json.has("clinicalInformation"))
      res.setClinicalInformationElement(parseString(json.get("clinicalInformation").getAsString()));
    if (json.has("_clinicalInformation"))
      parseElementProperties(json.getAsJsonObject("_clinicalInformation"), res.getClinicalInformationElement());
    if (json.has("procedure")) {
      JsonArray array = json.getAsJsonArray("procedure");
      for (int i = 0; i < array.size(); i++) {
        res.getProcedure().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("interpreter"))
      res.setInterpreter(parseReference(json.getAsJsonObject("interpreter")));
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
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
      res.setNumberElement(parseInteger(json.get("number").getAsLong()));
    if (json.has("_number"))
      parseElementProperties(json.getAsJsonObject("_number"), res.getNumberElement());
    if (json.has("modality"))
      res.setModalityElement(parseEnumeration(json.get("modality").getAsString(), ImagingStudy.Modality.NULL, new ImagingStudy.ModalityEnumFactory()));
    if (json.has("_modality"))
      parseElementProperties(json.getAsJsonObject("_modality"), res.getModalityElement());
    if (json.has("uid"))
      res.setUidElement(parseOid(json.get("uid").getAsString()));
    if (json.has("_uid"))
      parseElementProperties(json.getAsJsonObject("_uid"), res.getUidElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("numberOfInstances"))
      res.setNumberOfInstancesElement(parseInteger(json.get("numberOfInstances").getAsLong()));
    if (json.has("_numberOfInstances"))
      parseElementProperties(json.getAsJsonObject("_numberOfInstances"), res.getNumberOfInstancesElement());
    if (json.has("availability"))
      res.setAvailabilityElement(parseEnumeration(json.get("availability").getAsString(), ImagingStudy.InstanceAvailability.NULL, new ImagingStudy.InstanceAvailabilityEnumFactory()));
    if (json.has("_availability"))
      parseElementProperties(json.getAsJsonObject("_availability"), res.getAvailabilityElement());
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    if (json.has("bodySite"))
      res.setBodySite(parseCoding(json.getAsJsonObject("bodySite")));
    if (json.has("dateTime"))
      res.setDateTimeElement(parseDateTime(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTimeElement());
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
      res.setNumberElement(parseInteger(json.get("number").getAsLong()));
    if (json.has("_number"))
      parseElementProperties(json.getAsJsonObject("_number"), res.getNumberElement());
    if (json.has("uid"))
      res.setUidElement(parseOid(json.get("uid").getAsString()));
    if (json.has("_uid"))
      parseElementProperties(json.getAsJsonObject("_uid"), res.getUidElement());
    if (json.has("sopclass"))
      res.setSopclassElement(parseOid(json.get("sopclass").getAsString()));
    if (json.has("_sopclass"))
      parseElementProperties(json.getAsJsonObject("_sopclass"), res.getSopclassElement());
    if (json.has("type"))
      res.setTypeElement(parseString(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("title"))
      res.setTitleElement(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitleElement());
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    if (json.has("attachment"))
      res.setAttachment(parseReference(json.getAsJsonObject("attachment")));
    return res;
  }

  private Immunization parseImmunization(JsonObject json) throws Exception {
    Immunization res = new Immunization();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("vaccineType"))
      res.setVaccineType(parseCodeableConcept(json.getAsJsonObject("vaccineType")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("refusedIndicator"))
      res.setRefusedIndicatorElement(parseBoolean(json.get("refusedIndicator").getAsBoolean()));
    if (json.has("_refusedIndicator"))
      parseElementProperties(json.getAsJsonObject("_refusedIndicator"), res.getRefusedIndicatorElement());
    if (json.has("reported"))
      res.setReportedElement(parseBoolean(json.get("reported").getAsBoolean()));
    if (json.has("_reported"))
      parseElementProperties(json.getAsJsonObject("_reported"), res.getReportedElement());
    if (json.has("performer"))
      res.setPerformer(parseReference(json.getAsJsonObject("performer")));
    if (json.has("requester"))
      res.setRequester(parseReference(json.getAsJsonObject("requester")));
    if (json.has("manufacturer"))
      res.setManufacturer(parseReference(json.getAsJsonObject("manufacturer")));
    if (json.has("location"))
      res.setLocation(parseReference(json.getAsJsonObject("location")));
    if (json.has("lotNumber"))
      res.setLotNumberElement(parseString(json.get("lotNumber").getAsString()));
    if (json.has("_lotNumber"))
      parseElementProperties(json.getAsJsonObject("_lotNumber"), res.getLotNumberElement());
    if (json.has("expirationDate"))
      res.setExpirationDateElement(parseDate(json.get("expirationDate").getAsString()));
    if (json.has("_expirationDate"))
      parseElementProperties(json.getAsJsonObject("_expirationDate"), res.getExpirationDateElement());
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
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("detail"))
      res.setDetail(parseReference(json.getAsJsonObject("detail")));
    if (json.has("reported"))
      res.setReportedElement(parseBoolean(json.get("reported").getAsBoolean()));
    if (json.has("_reported"))
      parseElementProperties(json.getAsJsonObject("_reported"), res.getReportedElement());
    return res;
  }

  private Immunization.ImmunizationVaccinationProtocolComponent parseImmunizationImmunizationVaccinationProtocolComponent(JsonObject json, Immunization owner) throws Exception {
    Immunization.ImmunizationVaccinationProtocolComponent res = new Immunization.ImmunizationVaccinationProtocolComponent();
    parseBackboneProperties(json, res);
    if (json.has("doseSequence"))
      res.setDoseSequenceElement(parseInteger(json.get("doseSequence").getAsLong()));
    if (json.has("_doseSequence"))
      parseElementProperties(json.getAsJsonObject("_doseSequence"), res.getDoseSequenceElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("authority"))
      res.setAuthority(parseReference(json.getAsJsonObject("authority")));
    if (json.has("series"))
      res.setSeriesElement(parseString(json.get("series").getAsString()));
    if (json.has("_series"))
      parseElementProperties(json.getAsJsonObject("_series"), res.getSeriesElement());
    if (json.has("seriesDoses"))
      res.setSeriesDosesElement(parseInteger(json.get("seriesDoses").getAsLong()));
    if (json.has("_seriesDoses"))
      parseElementProperties(json.getAsJsonObject("_seriesDoses"), res.getSeriesDosesElement());
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
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
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
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("vaccineType"))
      res.setVaccineType(parseCodeableConcept(json.getAsJsonObject("vaccineType")));
    if (json.has("doseNumber"))
      res.setDoseNumberElement(parseInteger(json.get("doseNumber").getAsLong()));
    if (json.has("_doseNumber"))
      parseElementProperties(json.getAsJsonObject("_doseNumber"), res.getDoseNumberElement());
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
        res.getSupportingImmunization().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("supportingPatientInformation")) {
      JsonArray array = json.getAsJsonArray("supportingPatientInformation");
      for (int i = 0; i < array.size(); i++) {
        res.getSupportingPatientInformation().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setValueElement(parseDateTime(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(JsonObject json, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent();
    parseBackboneProperties(json, res);
    if (json.has("doseSequence"))
      res.setDoseSequenceElement(parseInteger(json.get("doseSequence").getAsLong()));
    if (json.has("_doseSequence"))
      parseElementProperties(json.getAsJsonObject("_doseSequence"), res.getDoseSequenceElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("authority"))
      res.setAuthority(parseReference(json.getAsJsonObject("authority")));
    if (json.has("series"))
      res.setSeriesElement(parseString(json.get("series").getAsString()));
    if (json.has("_series"))
      parseElementProperties(json.getAsJsonObject("_series"), res.getSeriesElement());
    return res;
  }

  private List_ parseList_(JsonObject json) throws Exception {
    List_ res = new List_();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("source"))
      res.setSource(parseReference(json.getAsJsonObject("source")));
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("ordered"))
      res.setOrderedElement(parseBoolean(json.get("ordered").getAsBoolean()));
    if (json.has("_ordered"))
      parseElementProperties(json.getAsJsonObject("_ordered"), res.getOrderedElement());
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), List_.ListMode.NULL, new List_.ListModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
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
      res.setDeletedElement(parseBoolean(json.get("deleted").getAsBoolean()));
    if (json.has("_deleted"))
      parseElementProperties(json.getAsJsonObject("_deleted"), res.getDeletedElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("item"))
      res.setItem(parseReference(json.getAsJsonObject("item")));
    return res;
  }

  private Location parseLocation(JsonObject json) throws Exception {
    Location res = new Location();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getAsJsonObject("address")));
    if (json.has("physicalType"))
      res.setPhysicalType(parseCodeableConcept(json.getAsJsonObject("physicalType")));
    if (json.has("position"))
      res.setPosition(parseLocationLocationPositionComponent(json.getAsJsonObject("position"), res));
    if (json.has("managingOrganization"))
      res.setManagingOrganization(parseReference(json.getAsJsonObject("managingOrganization")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Location.LocationStatus.NULL, new Location.LocationStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("partOf"))
      res.setPartOf(parseReference(json.getAsJsonObject("partOf")));
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), Location.LocationMode.NULL, new Location.LocationModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
    return res;
  }

  private Location.LocationPositionComponent parseLocationLocationPositionComponent(JsonObject json, Location owner) throws Exception {
    Location.LocationPositionComponent res = new Location.LocationPositionComponent();
    parseBackboneProperties(json, res);
    if (json.has("longitude"))
      res.setLongitudeElement(parseDecimal(json.get("longitude").getAsBigDecimal()));
    if (json.has("_longitude"))
      parseElementProperties(json.getAsJsonObject("_longitude"), res.getLongitudeElement());
    if (json.has("latitude"))
      res.setLatitudeElement(parseDecimal(json.get("latitude").getAsBigDecimal()));
    if (json.has("_latitude"))
      parseElementProperties(json.getAsJsonObject("_latitude"), res.getLatitudeElement());
    if (json.has("altitude"))
      res.setAltitudeElement(parseDecimal(json.get("altitude").getAsBigDecimal()));
    if (json.has("_altitude"))
      parseElementProperties(json.getAsJsonObject("_altitude"), res.getAltitudeElement());
    return res;
  }

  private Media parseMedia(JsonObject json) throws Exception {
    Media res = new Media();
    parseDomainResourceProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Media.MediaType.NULL, new Media.MediaTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("subtype"))
      res.setSubtype(parseCodeableConcept(json.getAsJsonObject("subtype")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("created"))
      res.setCreatedElement(parseDateTime(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreatedElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("operator"))
      res.setOperator(parseReference(json.getAsJsonObject("operator")));
    if (json.has("view"))
      res.setView(parseCodeableConcept(json.getAsJsonObject("view")));
    if (json.has("deviceName"))
      res.setDeviceNameElement(parseString(json.get("deviceName").getAsString()));
    if (json.has("_deviceName"))
      parseElementProperties(json.getAsJsonObject("_deviceName"), res.getDeviceNameElement());
    if (json.has("height"))
      res.setHeightElement(parseInteger(json.get("height").getAsLong()));
    if (json.has("_height"))
      parseElementProperties(json.getAsJsonObject("_height"), res.getHeightElement());
    if (json.has("width"))
      res.setWidthElement(parseInteger(json.get("width").getAsLong()));
    if (json.has("_width"))
      parseElementProperties(json.getAsJsonObject("_width"), res.getWidthElement());
    if (json.has("frames"))
      res.setFramesElement(parseInteger(json.get("frames").getAsLong()));
    if (json.has("_frames"))
      parseElementProperties(json.getAsJsonObject("_frames"), res.getFramesElement());
    if (json.has("duration"))
      res.setDurationElement(parseInteger(json.get("duration").getAsLong()));
    if (json.has("_duration"))
      parseElementProperties(json.getAsJsonObject("_duration"), res.getDurationElement());
    if (json.has("content"))
      res.setContent(parseAttachment(json.getAsJsonObject("content")));
    return res;
  }

  private Medication parseMedication(JsonObject json) throws Exception {
    Medication res = new Medication();
    parseDomainResourceProperties(json, res);
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("isBrand"))
      res.setIsBrandElement(parseBoolean(json.get("isBrand").getAsBoolean()));
    if (json.has("_isBrand"))
      parseElementProperties(json.getAsJsonObject("_isBrand"), res.getIsBrandElement());
    if (json.has("manufacturer"))
      res.setManufacturer(parseReference(json.getAsJsonObject("manufacturer")));
    if (json.has("kind"))
      res.setKindElement(parseEnumeration(json.get("kind").getAsString(), Medication.MedicationKind.NULL, new Medication.MedicationKindEnumFactory()));
    if (json.has("_kind"))
      parseElementProperties(json.getAsJsonObject("_kind"), res.getKindElement());
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
      res.setItem(parseReference(json.getAsJsonObject("item")));
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
      res.setItem(parseReference(json.getAsJsonObject("item")));
    if (json.has("amount"))
      res.setAmount(parseQuantity(json.getAsJsonObject("amount")));
    return res;
  }

  private MedicationAdministration parseMedicationAdministration(JsonObject json) throws Exception {
    MedicationAdministration res = new MedicationAdministration();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), MedicationAdministration.MedicationAdminStatus.NULL, new MedicationAdministration.MedicationAdminStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("practitioner"))
      res.setPractitioner(parseReference(json.getAsJsonObject("practitioner")));
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("prescription"))
      res.setPrescription(parseReference(json.getAsJsonObject("prescription")));
    if (json.has("wasNotGiven"))
      res.setWasNotGivenElement(parseBoolean(json.get("wasNotGiven").getAsBoolean()));
    if (json.has("_wasNotGiven"))
      parseElementProperties(json.getAsJsonObject("_wasNotGiven"), res.getWasNotGivenElement());
    if (json.has("reasonNotGiven")) {
      JsonArray array = json.getAsJsonArray("reasonNotGiven");
      for (int i = 0; i < array.size(); i++) {
        res.getReasonNotGiven().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    Type effectiveTime = parseType("effectiveTime", json);
    if (effectiveTime != null)
      res.setEffectiveTime(effectiveTime);
    if (json.has("medication"))
      res.setMedication(parseReference(json.getAsJsonObject("medication")));
    if (json.has("device")) {
      JsonArray array = json.getAsJsonArray("device");
      for (int i = 0; i < array.size(); i++) {
        res.getDevice().add(parseReference(array.get(i).getAsJsonObject()));
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
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), MedicationDispense.MedicationDispenseStatus.NULL, new MedicationDispense.MedicationDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("dispenser"))
      res.setDispenser(parseReference(json.getAsJsonObject("dispenser")));
    if (json.has("authorizingPrescription")) {
      JsonArray array = json.getAsJsonArray("authorizingPrescription");
      for (int i = 0; i < array.size(); i++) {
        res.getAuthorizingPrescription().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), MedicationDispense.MedicationDispenseStatus.NULL, new MedicationDispense.MedicationDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("medication"))
      res.setMedication(parseReference(json.getAsJsonObject("medication")));
    if (json.has("whenPrepared"))
      res.setWhenPreparedElement(parseDateTime(json.get("whenPrepared").getAsString()));
    if (json.has("_whenPrepared"))
      parseElementProperties(json.getAsJsonObject("_whenPrepared"), res.getWhenPreparedElement());
    if (json.has("whenHandedOver"))
      res.setWhenHandedOverElement(parseDateTime(json.get("whenHandedOver").getAsString()));
    if (json.has("_whenHandedOver"))
      parseElementProperties(json.getAsJsonObject("_whenHandedOver"), res.getWhenHandedOverElement());
    if (json.has("destination"))
      res.setDestination(parseReference(json.getAsJsonObject("destination")));
    if (json.has("receiver")) {
      JsonArray array = json.getAsJsonArray("receiver");
      for (int i = 0; i < array.size(); i++) {
        res.getReceiver().add(parseReference(array.get(i).getAsJsonObject()));
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
    Type schedule = parseType("schedule", json);
    if (schedule != null)
      res.setSchedule(schedule);
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
        res.getResponsibleParty().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private MedicationPrescription parseMedicationPrescription(JsonObject json) throws Exception {
    MedicationPrescription res = new MedicationPrescription();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("dateWritten"))
      res.setDateWrittenElement(parseDateTime(json.get("dateWritten").getAsString()));
    if (json.has("_dateWritten"))
      parseElementProperties(json.getAsJsonObject("_dateWritten"), res.getDateWrittenElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), MedicationPrescription.MedicationPrescriptionStatus.NULL, new MedicationPrescription.MedicationPrescriptionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("prescriber"))
      res.setPrescriber(parseReference(json.getAsJsonObject("prescriber")));
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    Type reason = parseType("reason", json);
    if (reason != null)
      res.setReason(reason);
    if (json.has("medication"))
      res.setMedication(parseReference(json.getAsJsonObject("medication")));
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
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    if (json.has("additionalInstructions"))
      res.setAdditionalInstructions(parseCodeableConcept(json.getAsJsonObject("additionalInstructions")));
    Type scheduled = parseType("scheduled", json);
    if (scheduled != null)
      res.setScheduled(scheduled);
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
      res.setMedication(parseReference(json.getAsJsonObject("medication")));
    if (json.has("validityPeriod"))
      res.setValidityPeriod(parsePeriod(json.getAsJsonObject("validityPeriod")));
    if (json.has("numberOfRepeatsAllowed"))
      res.setNumberOfRepeatsAllowedElement(parseInteger(json.get("numberOfRepeatsAllowed").getAsLong()));
    if (json.has("_numberOfRepeatsAllowed"))
      parseElementProperties(json.getAsJsonObject("_numberOfRepeatsAllowed"), res.getNumberOfRepeatsAllowedElement());
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
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("wasNotGiven"))
      res.setWasNotGivenElement(parseBoolean(json.get("wasNotGiven").getAsBoolean()));
    if (json.has("_wasNotGiven"))
      parseElementProperties(json.getAsJsonObject("_wasNotGiven"), res.getWasNotGivenElement());
    if (json.has("reasonNotGiven")) {
      JsonArray array = json.getAsJsonArray("reasonNotGiven");
      for (int i = 0; i < array.size(); i++) {
        res.getReasonNotGiven().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("whenGiven"))
      res.setWhenGiven(parsePeriod(json.getAsJsonObject("whenGiven")));
    if (json.has("medication"))
      res.setMedication(parseReference(json.getAsJsonObject("medication")));
    if (json.has("device")) {
      JsonArray array = json.getAsJsonArray("device");
      for (int i = 0; i < array.size(); i++) {
        res.getDevice().add(parseReference(array.get(i).getAsJsonObject()));
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
    if (json.has("schedule"))
      res.setSchedule(parseTiming(json.getAsJsonObject("schedule")));
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
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifierElement(parseId(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("timestamp"))
      res.setTimestampElement(parseInstant(json.get("timestamp").getAsString()));
    if (json.has("_timestamp"))
      parseElementProperties(json.getAsJsonObject("_timestamp"), res.getTimestampElement());
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
      res.setEnterer(parseReference(json.getAsJsonObject("enterer")));
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    if (json.has("receiver"))
      res.setReceiver(parseReference(json.getAsJsonObject("receiver")));
    if (json.has("responsible"))
      res.setResponsible(parseReference(json.getAsJsonObject("responsible")));
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("data")) {
      JsonArray array = json.getAsJsonArray("data");
      for (int i = 0; i < array.size(); i++) {
        res.getData().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private MessageHeader.MessageHeaderResponseComponent parseMessageHeaderMessageHeaderResponseComponent(JsonObject json, MessageHeader owner) throws Exception {
    MessageHeader.MessageHeaderResponseComponent res = new MessageHeader.MessageHeaderResponseComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifierElement(parseId(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("code"))
      res.setCodeElement(parseEnumeration(json.get("code").getAsString(), MessageHeader.ResponseCode.NULL, new MessageHeader.ResponseCodeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("details"))
      res.setDetails(parseReference(json.getAsJsonObject("details")));
    return res;
  }

  private MessageHeader.MessageSourceComponent parseMessageHeaderMessageSourceComponent(JsonObject json, MessageHeader owner) throws Exception {
    MessageHeader.MessageSourceComponent res = new MessageHeader.MessageSourceComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("software"))
      res.setSoftwareElement(parseString(json.get("software").getAsString()));
    if (json.has("_software"))
      parseElementProperties(json.getAsJsonObject("_software"), res.getSoftwareElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("contact"))
      res.setContact(parseContactPoint(json.getAsJsonObject("contact")));
    if (json.has("endpoint"))
      res.setEndpointElement(parseUri(json.get("endpoint").getAsString()));
    if (json.has("_endpoint"))
      parseElementProperties(json.getAsJsonObject("_endpoint"), res.getEndpointElement());
    return res;
  }

  private MessageHeader.MessageDestinationComponent parseMessageHeaderMessageDestinationComponent(JsonObject json, MessageHeader owner) throws Exception {
    MessageHeader.MessageDestinationComponent res = new MessageHeader.MessageDestinationComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    if (json.has("endpoint"))
      res.setEndpointElement(parseUri(json.get("endpoint").getAsString()));
    if (json.has("_endpoint"))
      parseElementProperties(json.getAsJsonObject("_endpoint"), res.getEndpointElement());
    return res;
  }

  private NamingSystem parseNamingSystem(JsonObject json) throws Exception {
    NamingSystem res = new NamingSystem();
    parseDomainResourceProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), NamingSystem.NamingsystemType.NULL, new NamingSystem.NamingsystemTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), NamingSystem.NamingsystemStatus.NULL, new NamingSystem.NamingsystemStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("country"))
      res.setCountryElement(parseCode(json.get("country").getAsString()));
    if (json.has("_country"))
      parseElementProperties(json.getAsJsonObject("_country"), res.getCountryElement());
    if (json.has("category"))
      res.setCategory(parseCodeableConcept(json.getAsJsonObject("category")));
    if (json.has("responsible"))
      res.setResponsibleElement(parseString(json.get("responsible").getAsString()));
    if (json.has("_responsible"))
      parseElementProperties(json.getAsJsonObject("_responsible"), res.getResponsibleElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("usage"))
      res.setUsageElement(parseString(json.get("usage").getAsString()));
    if (json.has("_usage"))
      parseElementProperties(json.getAsJsonObject("_usage"), res.getUsageElement());
    if (json.has("uniqueId")) {
      JsonArray array = json.getAsJsonArray("uniqueId");
      for (int i = 0; i < array.size(); i++) {
        res.getUniqueId().add(parseNamingSystemNamingSystemUniqueIdComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("contact"))
      res.setContact(parseNamingSystemNamingSystemContactComponent(json.getAsJsonObject("contact"), res));
    if (json.has("replacedBy"))
      res.setReplacedBy(parseReference(json.getAsJsonObject("replacedBy")));
    return res;
  }

  private NamingSystem.NamingSystemUniqueIdComponent parseNamingSystemNamingSystemUniqueIdComponent(JsonObject json, NamingSystem owner) throws Exception {
    NamingSystem.NamingSystemUniqueIdComponent res = new NamingSystem.NamingSystemUniqueIdComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), NamingSystem.NamingsystemIdentifierType.NULL, new NamingSystem.NamingsystemIdentifierTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("value"))
      res.setValueElement(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    if (json.has("preferred"))
      res.setPreferredElement(parseBoolean(json.get("preferred").getAsBoolean()));
    if (json.has("_preferred"))
      parseElementProperties(json.getAsJsonObject("_preferred"), res.getPreferredElement());
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    return res;
  }

  private NamingSystem.NamingSystemContactComponent parseNamingSystemNamingSystemContactComponent(JsonObject json, NamingSystem owner) throws Exception {
    NamingSystem.NamingSystemContactComponent res = new NamingSystem.NamingSystemContactComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setName(parseHumanName(json.getAsJsonObject("name")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private NutritionOrder parseNutritionOrder(JsonObject json) throws Exception {
    NutritionOrder res = new NutritionOrder();
    parseDomainResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("orderer"))
      res.setOrderer(parseReference(json.getAsJsonObject("orderer")));
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("dateTime"))
      res.setDateTimeElement(parseDateTime(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTimeElement());
    if (json.has("allergyIntolerance")) {
      JsonArray array = json.getAsJsonArray("allergyIntolerance");
      for (int i = 0; i < array.size(); i++) {
        res.getAllergyIntolerance().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("foodPreferenceModifier")) {
      JsonArray array = json.getAsJsonArray("foodPreferenceModifier");
      for (int i = 0; i < array.size(); i++) {
        res.getFoodPreferenceModifier().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("excludeFoodModifier")) {
      JsonArray array = json.getAsJsonArray("excludeFoodModifier");
      for (int i = 0; i < array.size(); i++) {
        res.getExcludeFoodModifier().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("item")) {
      JsonArray array = json.getAsJsonArray("item");
      for (int i = 0; i < array.size(); i++) {
        res.getItem().add(parseNutritionOrderNutritionOrderItemComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), NutritionOrder.NutritionOrderStatus.NULL, new NutritionOrder.NutritionOrderStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    return res;
  }

  private NutritionOrder.NutritionOrderItemComponent parseNutritionOrderNutritionOrderItemComponent(JsonObject json, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderItemComponent res = new NutritionOrder.NutritionOrderItemComponent();
    parseBackboneProperties(json, res);
    Type scheduled = parseType("scheduled", json);
    if (scheduled != null)
      res.setScheduled(scheduled);
    if (json.has("isInEffect"))
      res.setIsInEffectElement(parseBoolean(json.get("isInEffect").getAsBoolean()));
    if (json.has("_isInEffect"))
      parseElementProperties(json.getAsJsonObject("_isInEffect"), res.getIsInEffectElement());
    if (json.has("oralDiet"))
      res.setOralDiet(parseNutritionOrderNutritionOrderItemOralDietComponent(json.getAsJsonObject("oralDiet"), owner));
    if (json.has("supplement"))
      res.setSupplement(parseNutritionOrderNutritionOrderItemSupplementComponent(json.getAsJsonObject("supplement"), owner));
    if (json.has("enteralFormula"))
      res.setEnteralFormula(parseNutritionOrderNutritionOrderItemEnteralFormulaComponent(json.getAsJsonObject("enteralFormula"), owner));
    return res;
  }

  private NutritionOrder.NutritionOrderItemOralDietComponent parseNutritionOrderNutritionOrderItemOralDietComponent(JsonObject json, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderItemOralDietComponent res = new NutritionOrder.NutritionOrderItemOralDietComponent();
    parseBackboneProperties(json, res);
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("nutrients")) {
      JsonArray array = json.getAsJsonArray("nutrients");
      for (int i = 0; i < array.size(); i++) {
        res.getNutrients().add(parseNutritionOrderNutritionOrderItemOralDietNutrientsComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("texture")) {
      JsonArray array = json.getAsJsonArray("texture");
      for (int i = 0; i < array.size(); i++) {
        res.getTexture().add(parseNutritionOrderNutritionOrderItemOralDietTextureComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("fluidConsistencyType")) {
      JsonArray array = json.getAsJsonArray("fluidConsistencyType");
      for (int i = 0; i < array.size(); i++) {
        res.getFluidConsistencyType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    return res;
  }

  private NutritionOrder.NutritionOrderItemOralDietNutrientsComponent parseNutritionOrderNutritionOrderItemOralDietNutrientsComponent(JsonObject json, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderItemOralDietNutrientsComponent res = new NutritionOrder.NutritionOrderItemOralDietNutrientsComponent();
    parseBackboneProperties(json, res);
    if (json.has("modifier"))
      res.setModifier(parseCodeableConcept(json.getAsJsonObject("modifier")));
    Type amount = parseType("amount", json);
    if (amount != null)
      res.setAmount(amount);
    return res;
  }

  private NutritionOrder.NutritionOrderItemOralDietTextureComponent parseNutritionOrderNutritionOrderItemOralDietTextureComponent(JsonObject json, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderItemOralDietTextureComponent res = new NutritionOrder.NutritionOrderItemOralDietTextureComponent();
    parseBackboneProperties(json, res);
    if (json.has("modifier"))
      res.setModifier(parseCodeableConcept(json.getAsJsonObject("modifier")));
    if (json.has("foodType"))
      res.setFoodType(parseCodeableConcept(json.getAsJsonObject("foodType")));
    return res;
  }

  private NutritionOrder.NutritionOrderItemSupplementComponent parseNutritionOrderNutritionOrderItemSupplementComponent(JsonObject json, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderItemSupplementComponent res = new NutritionOrder.NutritionOrderItemSupplementComponent();
    parseBackboneProperties(json, res);
    if (json.has("type")) {
      JsonArray array = json.getAsJsonArray("type");
      for (int i = 0; i < array.size(); i++) {
        res.getType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    return res;
  }

  private NutritionOrder.NutritionOrderItemEnteralFormulaComponent parseNutritionOrderNutritionOrderItemEnteralFormulaComponent(JsonObject json, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderItemEnteralFormulaComponent res = new NutritionOrder.NutritionOrderItemEnteralFormulaComponent();
    parseBackboneProperties(json, res);
    if (json.has("baseFormulaType"))
      res.setBaseFormulaType(parseCodeableConcept(json.getAsJsonObject("baseFormulaType")));
    if (json.has("additiveType")) {
      JsonArray array = json.getAsJsonArray("additiveType");
      for (int i = 0; i < array.size(); i++) {
        res.getAdditiveType().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("caloricDensity")) {
      JsonArray array = json.getAsJsonArray("caloricDensity");
      for (int i = 0; i < array.size(); i++) {
        res.getCaloricDensity().add(parseQuantity(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("routeofAdministration")) {
      JsonArray array = json.getAsJsonArray("routeofAdministration");
      for (int i = 0; i < array.size(); i++) {
        res.getRouteofAdministration().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("rate")) {
      JsonArray array = json.getAsJsonArray("rate");
      for (int i = 0; i < array.size(); i++) {
        res.getRate().add(parseQuantity(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("baseFormulaName"))
      res.setBaseFormulaNameElement(parseString(json.get("baseFormulaName").getAsString()));
    if (json.has("_baseFormulaName"))
      parseElementProperties(json.getAsJsonObject("_baseFormulaName"), res.getBaseFormulaNameElement());
    return res;
  }

  private Observation parseObservation(JsonObject json) throws Exception {
    Observation res = new Observation();
    parseDomainResourceProperties(json, res);
    if (json.has("name"))
      res.setName(parseCodeableConcept(json.getAsJsonObject("name")));
    Type value = parseType("value", json);
    if (value != null)
      res.setValue(value);
    if (json.has("dataAbsentReason"))
      res.setDataAbsentReasonElement(parseEnumeration(json.get("dataAbsentReason").getAsString(), Observation.DataAbsentReason.NULL, new Observation.DataAbsentReasonEnumFactory()));
    if (json.has("_dataAbsentReason"))
      parseElementProperties(json.getAsJsonObject("_dataAbsentReason"), res.getDataAbsentReasonElement());
    if (json.has("interpretation"))
      res.setInterpretation(parseCodeableConcept(json.getAsJsonObject("interpretation")));
    if (json.has("comments"))
      res.setCommentsElement(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getCommentsElement());
    Type applies = parseType("applies", json);
    if (applies != null)
      res.setApplies(applies);
    if (json.has("issued"))
      res.setIssuedElement(parseInstant(json.get("issued").getAsString()));
    if (json.has("_issued"))
      parseElementProperties(json.getAsJsonObject("_issued"), res.getIssuedElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Observation.ObservationStatus.NULL, new Observation.ObservationStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("reliability"))
      res.setReliabilityElement(parseEnumeration(json.get("reliability").getAsString(), Observation.ObservationReliability.NULL, new Observation.ObservationReliabilityEnumFactory()));
    if (json.has("_reliability"))
      parseElementProperties(json.getAsJsonObject("_reliability"), res.getReliabilityElement());
    if (json.has("bodySite"))
      res.setBodySite(parseCodeableConcept(json.getAsJsonObject("bodySite")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("specimen"))
      res.setSpecimen(parseReference(json.getAsJsonObject("specimen")));
    if (json.has("performer")) {
      JsonArray array = json.getAsJsonArray("performer");
      for (int i = 0; i < array.size(); i++) {
        res.getPerformer().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
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
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    return res;
  }

  private Observation.ObservationRelatedComponent parseObservationObservationRelatedComponent(JsonObject json, Observation owner) throws Exception {
    Observation.ObservationRelatedComponent res = new Observation.ObservationRelatedComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Observation.ObservationRelationshiptypes.NULL, new Observation.ObservationRelationshiptypesEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    return res;
  }

  private OperationDefinition parseOperationDefinition(JsonObject json) throws Exception {
    OperationDefinition res = new OperationDefinition();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifierElement(parseUri(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("title"))
      res.setTitleElement(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitleElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), OperationDefinition.ResourceProfileStatus.NULL, new OperationDefinition.ResourceProfileStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("experimental"))
      res.setExperimentalElement(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimentalElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("kind"))
      res.setKindElement(parseEnumeration(json.get("kind").getAsString(), OperationDefinition.OperationKind.NULL, new OperationDefinition.OperationKindEnumFactory()));
    if (json.has("_kind"))
      parseElementProperties(json.getAsJsonObject("_kind"), res.getKindElement());
    if (json.has("name"))
      res.setNameElement(parseCode(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("notes"))
      res.setNotesElement(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotesElement());
    if (json.has("base"))
      res.setBase(parseReference(json.getAsJsonObject("base")));
    if (json.has("system"))
      res.setSystemElement(parseBoolean(json.get("system").getAsBoolean()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
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
      res.setInstanceElement(parseBoolean(json.get("instance").getAsBoolean()));
    if (json.has("_instance"))
      parseElementProperties(json.getAsJsonObject("_instance"), res.getInstanceElement());
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
      res.setNameElement(parseCode(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("use"))
      res.setUseElement(parseEnumeration(json.get("use").getAsString(), OperationDefinition.OperationParameterUse.NULL, new OperationDefinition.OperationParameterUseEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUseElement());
    if (json.has("min"))
      res.setMinElement(parseInteger(json.get("min").getAsLong()));
    if (json.has("_min"))
      parseElementProperties(json.getAsJsonObject("_min"), res.getMinElement());
    if (json.has("max"))
      res.setMaxElement(parseString(json.get("max").getAsString()));
    if (json.has("_max"))
      parseElementProperties(json.getAsJsonObject("_max"), res.getMaxElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("profile"))
      res.setProfile(parseReference(json.getAsJsonObject("profile")));
    return res;
  }

  private OperationOutcome parseOperationOutcome(JsonObject json) throws Exception {
    OperationOutcome res = new OperationOutcome();
    parseDomainResourceProperties(json, res);
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
      res.setSeverityElement(parseEnumeration(json.get("severity").getAsString(), OperationOutcome.IssueSeverity.NULL, new OperationOutcome.IssueSeverityEnumFactory()));
    if (json.has("_severity"))
      parseElementProperties(json.getAsJsonObject("_severity"), res.getSeverityElement());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("details"))
      res.setDetailsElement(parseString(json.get("details").getAsString()));
    if (json.has("_details"))
      parseElementProperties(json.getAsJsonObject("_details"), res.getDetailsElement());
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

  private OralHealthClaim parseOralHealthClaim(JsonObject json) throws Exception {
    OralHealthClaim res = new OralHealthClaim();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("ruleset"))
      res.setRuleset(parseCoding(json.getAsJsonObject("ruleset")));
    if (json.has("originalRuleset"))
      res.setOriginalRuleset(parseCoding(json.getAsJsonObject("originalRuleset")));
    if (json.has("date"))
      res.setDateElement(parseDate(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    if (json.has("provider"))
      res.setProvider(parseReference(json.getAsJsonObject("provider")));
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
    if (json.has("use"))
      res.setUseElement(parseEnumeration(json.get("use").getAsString(), OralHealthClaim.UseLink.NULL, new OralHealthClaim.UseLinkEnumFactory()));
    if (json.has("_use"))
      parseElementProperties(json.getAsJsonObject("_use"), res.getUseElement());
    if (json.has("priority"))
      res.setPriority(parseCoding(json.getAsJsonObject("priority")));
    if (json.has("fundsReserve"))
      res.setFundsReserve(parseCoding(json.getAsJsonObject("fundsReserve")));
    if (json.has("enterer"))
      res.setEnterer(parseReference(json.getAsJsonObject("enterer")));
    if (json.has("facility"))
      res.setFacility(parseReference(json.getAsJsonObject("facility")));
    if (json.has("payee"))
      res.setPayee(parseOralHealthClaimPayeeComponent(json.getAsJsonObject("payee"), res));
    if (json.has("referral"))
      res.setReferral(parseReference(json.getAsJsonObject("referral")));
    if (json.has("diagnosis")) {
      JsonArray array = json.getAsJsonArray("diagnosis");
      for (int i = 0; i < array.size(); i++) {
        res.getDiagnosis().add(parseOralHealthClaimDiagnosisComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("condition")) {
      JsonArray array = json.getAsJsonArray("condition");
      for (int i = 0; i < array.size(); i++) {
        res.getCondition().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("coverage")) {
      JsonArray array = json.getAsJsonArray("coverage");
      for (int i = 0; i < array.size(); i++) {
        res.getCoverage().add(parseOralHealthClaimCoverageComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("exception")) {
      JsonArray array = json.getAsJsonArray("exception");
      for (int i = 0; i < array.size(); i++) {
        res.getException().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("school"))
      res.setSchoolElement(parseString(json.get("school").getAsString()));
    if (json.has("_school"))
      parseElementProperties(json.getAsJsonObject("_school"), res.getSchoolElement());
    if (json.has("accident"))
      res.setAccidentElement(parseDate(json.get("accident").getAsString()));
    if (json.has("_accident"))
      parseElementProperties(json.getAsJsonObject("_accident"), res.getAccidentElement());
    if (json.has("accidentType"))
      res.setAccidentType(parseCoding(json.getAsJsonObject("accidentType")));
    if (json.has("interventionException")) {
      JsonArray array = json.getAsJsonArray("interventionException");
      for (int i = 0; i < array.size(); i++) {
        res.getInterventionException().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("missingteeth")) {
      JsonArray array = json.getAsJsonArray("missingteeth");
      for (int i = 0; i < array.size(); i++) {
        res.getMissingteeth().add(parseOralHealthClaimMissingTeethComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("orthoPlan"))
      res.setOrthoPlan(parseOralHealthClaimOrthodonticPlanComponent(json.getAsJsonObject("orthoPlan"), res));
    if (json.has("item")) {
      JsonArray array = json.getAsJsonArray("item");
      for (int i = 0; i < array.size(); i++) {
        res.getItem().add(parseOralHealthClaimItemsComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("additionalMaterials")) {
      JsonArray array = json.getAsJsonArray("additionalMaterials");
      for (int i = 0; i < array.size(); i++) {
        res.getAdditionalMaterials().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private OralHealthClaim.PayeeComponent parseOralHealthClaimPayeeComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.PayeeComponent res = new OralHealthClaim.PayeeComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("provider"))
      res.setProvider(parseReference(json.getAsJsonObject("provider")));
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
    if (json.has("person"))
      res.setPerson(parseReference(json.getAsJsonObject("person")));
    return res;
  }

  private OralHealthClaim.DiagnosisComponent parseOralHealthClaimDiagnosisComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.DiagnosisComponent res = new OralHealthClaim.DiagnosisComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequence"))
      res.setSequenceElement(parseInteger(json.get("sequence").getAsLong()));
    if (json.has("_sequence"))
      parseElementProperties(json.getAsJsonObject("_sequence"), res.getSequenceElement());
    if (json.has("diagnosis"))
      res.setDiagnosis(parseCoding(json.getAsJsonObject("diagnosis")));
    return res;
  }

  private OralHealthClaim.CoverageComponent parseOralHealthClaimCoverageComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.CoverageComponent res = new OralHealthClaim.CoverageComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequence"))
      res.setSequenceElement(parseInteger(json.get("sequence").getAsLong()));
    if (json.has("_sequence"))
      parseElementProperties(json.getAsJsonObject("_sequence"), res.getSequenceElement());
    if (json.has("focal"))
      res.setFocalElement(parseBoolean(json.get("focal").getAsBoolean()));
    if (json.has("_focal"))
      parseElementProperties(json.getAsJsonObject("_focal"), res.getFocalElement());
    if (json.has("coverage"))
      res.setCoverage(parseReference(json.getAsJsonObject("coverage")));
    if (json.has("businessArrangement"))
      res.setBusinessArrangementElement(parseString(json.get("businessArrangement").getAsString()));
    if (json.has("_businessArrangement"))
      parseElementProperties(json.getAsJsonObject("_businessArrangement"), res.getBusinessArrangementElement());
    if (json.has("relationship"))
      res.setRelationship(parseCoding(json.getAsJsonObject("relationship")));
    if (json.has("preauthref")) {
      JsonArray array = json.getAsJsonArray("preauthref");
      for (int i = 0; i < array.size(); i++) {
        res.getPreauthref().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_preauthref")) {
      JsonArray array = json.getAsJsonArray("_preauthref");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getPreauthref().size())
          res.getPreauthref().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getPreauthref().get(i));
      }
    };
    if (json.has("claimResponse"))
      res.setClaimResponse(parseReference(json.getAsJsonObject("claimResponse")));
    if (json.has("originalRuleset"))
      res.setOriginalRuleset(parseCoding(json.getAsJsonObject("originalRuleset")));
    return res;
  }

  private OralHealthClaim.MissingTeethComponent parseOralHealthClaimMissingTeethComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.MissingTeethComponent res = new OralHealthClaim.MissingTeethComponent();
    parseBackboneProperties(json, res);
    if (json.has("tooth"))
      res.setTooth(parseCoding(json.getAsJsonObject("tooth")));
    if (json.has("reason"))
      res.setReason(parseCoding(json.getAsJsonObject("reason")));
    if (json.has("extractiondate"))
      res.setExtractiondateElement(parseDate(json.get("extractiondate").getAsString()));
    if (json.has("_extractiondate"))
      parseElementProperties(json.getAsJsonObject("_extractiondate"), res.getExtractiondateElement());
    return res;
  }

  private OralHealthClaim.OrthodonticPlanComponent parseOralHealthClaimOrthodonticPlanComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.OrthodonticPlanComponent res = new OralHealthClaim.OrthodonticPlanComponent();
    parseBackboneProperties(json, res);
    if (json.has("start"))
      res.setStartElement(parseDate(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStartElement());
    if (json.has("examFee"))
      res.setExamFee(parseMoney(json.getAsJsonObject("examFee")));
    if (json.has("diagnosticFee"))
      res.setDiagnosticFee(parseMoney(json.getAsJsonObject("diagnosticFee")));
    if (json.has("initialPayment"))
      res.setInitialPayment(parseMoney(json.getAsJsonObject("initialPayment")));
    if (json.has("durationMonths"))
      res.setDurationMonthsElement(parseInteger(json.get("durationMonths").getAsLong()));
    if (json.has("_durationMonths"))
      parseElementProperties(json.getAsJsonObject("_durationMonths"), res.getDurationMonthsElement());
    if (json.has("paymentCount"))
      res.setPaymentCountElement(parseInteger(json.get("paymentCount").getAsLong()));
    if (json.has("_paymentCount"))
      parseElementProperties(json.getAsJsonObject("_paymentCount"), res.getPaymentCountElement());
    if (json.has("periodicPayment"))
      res.setPeriodicPayment(parseMoney(json.getAsJsonObject("periodicPayment")));
    return res;
  }

  private OralHealthClaim.ItemsComponent parseOralHealthClaimItemsComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.ItemsComponent res = new OralHealthClaim.ItemsComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequence"))
      res.setSequenceElement(parseInteger(json.get("sequence").getAsLong()));
    if (json.has("_sequence"))
      parseElementProperties(json.getAsJsonObject("_sequence"), res.getSequenceElement());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("provider"))
      res.setProvider(parseReference(json.getAsJsonObject("provider")));
    if (json.has("service"))
      res.setService(parseCoding(json.getAsJsonObject("service")));
    if (json.has("serviceDate"))
      res.setServiceDateElement(parseDate(json.get("serviceDate").getAsString()));
    if (json.has("_serviceDate"))
      parseElementProperties(json.getAsJsonObject("_serviceDate"), res.getServiceDateElement());
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("unitPrice"))
      res.setUnitPrice(parseMoney(json.getAsJsonObject("unitPrice")));
    if (json.has("factor"))
      res.setFactorElement(parseDecimal(json.get("factor").getAsBigDecimal()));
    if (json.has("_factor"))
      parseElementProperties(json.getAsJsonObject("_factor"), res.getFactorElement());
    if (json.has("points"))
      res.setPointsElement(parseDecimal(json.get("points").getAsBigDecimal()));
    if (json.has("_points"))
      parseElementProperties(json.getAsJsonObject("_points"), res.getPointsElement());
    if (json.has("net"))
      res.setNet(parseMoney(json.getAsJsonObject("net")));
    if (json.has("udi"))
      res.setUdi(parseCoding(json.getAsJsonObject("udi")));
    if (json.has("bodySite"))
      res.setBodySite(parseCoding(json.getAsJsonObject("bodySite")));
    if (json.has("subsite")) {
      JsonArray array = json.getAsJsonArray("subsite");
      for (int i = 0; i < array.size(); i++) {
        res.getSubsite().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("modifier")) {
      JsonArray array = json.getAsJsonArray("modifier");
      for (int i = 0; i < array.size(); i++) {
        res.getModifier().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseOralHealthClaimDetailComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("prosthesis"))
      res.setProsthesis(parseOralHealthClaimProsthesisComponent(json.getAsJsonObject("prosthesis"), owner));
    return res;
  }

  private OralHealthClaim.DetailComponent parseOralHealthClaimDetailComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.DetailComponent res = new OralHealthClaim.DetailComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequence"))
      res.setSequenceElement(parseInteger(json.get("sequence").getAsLong()));
    if (json.has("_sequence"))
      parseElementProperties(json.getAsJsonObject("_sequence"), res.getSequenceElement());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("service"))
      res.setService(parseCoding(json.getAsJsonObject("service")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("unitPrice"))
      res.setUnitPrice(parseMoney(json.getAsJsonObject("unitPrice")));
    if (json.has("factor"))
      res.setFactorElement(parseDecimal(json.get("factor").getAsBigDecimal()));
    if (json.has("_factor"))
      parseElementProperties(json.getAsJsonObject("_factor"), res.getFactorElement());
    if (json.has("points"))
      res.setPointsElement(parseDecimal(json.get("points").getAsBigDecimal()));
    if (json.has("_points"))
      parseElementProperties(json.getAsJsonObject("_points"), res.getPointsElement());
    if (json.has("net"))
      res.setNet(parseMoney(json.getAsJsonObject("net")));
    if (json.has("udi"))
      res.setUdi(parseCoding(json.getAsJsonObject("udi")));
    if (json.has("subDetail")) {
      JsonArray array = json.getAsJsonArray("subDetail");
      for (int i = 0; i < array.size(); i++) {
        res.getSubDetail().add(parseOralHealthClaimSubDetailComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private OralHealthClaim.SubDetailComponent parseOralHealthClaimSubDetailComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.SubDetailComponent res = new OralHealthClaim.SubDetailComponent();
    parseBackboneProperties(json, res);
    if (json.has("sequence"))
      res.setSequenceElement(parseInteger(json.get("sequence").getAsLong()));
    if (json.has("_sequence"))
      parseElementProperties(json.getAsJsonObject("_sequence"), res.getSequenceElement());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("service"))
      res.setService(parseCoding(json.getAsJsonObject("service")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("unitPrice"))
      res.setUnitPrice(parseMoney(json.getAsJsonObject("unitPrice")));
    if (json.has("factor"))
      res.setFactorElement(parseDecimal(json.get("factor").getAsBigDecimal()));
    if (json.has("_factor"))
      parseElementProperties(json.getAsJsonObject("_factor"), res.getFactorElement());
    if (json.has("points"))
      res.setPointsElement(parseDecimal(json.get("points").getAsBigDecimal()));
    if (json.has("_points"))
      parseElementProperties(json.getAsJsonObject("_points"), res.getPointsElement());
    if (json.has("net"))
      res.setNet(parseMoney(json.getAsJsonObject("net")));
    if (json.has("udi"))
      res.setUdi(parseCoding(json.getAsJsonObject("udi")));
    return res;
  }

  private OralHealthClaim.ProsthesisComponent parseOralHealthClaimProsthesisComponent(JsonObject json, OralHealthClaim owner) throws Exception {
    OralHealthClaim.ProsthesisComponent res = new OralHealthClaim.ProsthesisComponent();
    parseBackboneProperties(json, res);
    if (json.has("initial"))
      res.setInitialElement(parseBoolean(json.get("initial").getAsBoolean()));
    if (json.has("_initial"))
      parseElementProperties(json.getAsJsonObject("_initial"), res.getInitialElement());
    if (json.has("priorDate"))
      res.setPriorDateElement(parseDate(json.get("priorDate").getAsString()));
    if (json.has("_priorDate"))
      parseElementProperties(json.getAsJsonObject("_priorDate"), res.getPriorDateElement());
    if (json.has("priorMaterial"))
      res.setPriorMaterial(parseCoding(json.getAsJsonObject("priorMaterial")));
    return res;
  }

  private Order parseOrder(JsonObject json) throws Exception {
    Order res = new Order();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("source"))
      res.setSource(parseReference(json.getAsJsonObject("source")));
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    Type reason = parseType("reason", json);
    if (reason != null)
      res.setReason(reason);
    if (json.has("authority"))
      res.setAuthority(parseReference(json.getAsJsonObject("authority")));
    if (json.has("when"))
      res.setWhen(parseOrderOrderWhenComponent(json.getAsJsonObject("when"), res));
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setSchedule(parseTiming(json.getAsJsonObject("schedule")));
    return res;
  }

  private OrderResponse parseOrderResponse(JsonObject json) throws Exception {
    OrderResponse res = new OrderResponse();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("request"))
      res.setRequest(parseReference(json.getAsJsonObject("request")));
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("who"))
      res.setWho(parseReference(json.getAsJsonObject("who")));
    Type authority = parseType("authority", json);
    if (authority != null)
      res.setAuthority(authority);
    if (json.has("code"))
      res.setCodeElement(parseEnumeration(json.get("code").getAsString(), OrderResponse.OrderOutcomeCode.NULL, new OrderResponse.OrderOutcomeCodeEnumFactory()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("fulfillment")) {
      JsonArray array = json.getAsJsonArray("fulfillment");
      for (int i = 0; i < array.size(); i++) {
        res.getFulfillment().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Organization parseOrganization(JsonObject json) throws Exception {
    Organization res = new Organization();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address")) {
      JsonArray array = json.getAsJsonArray("address");
      for (int i = 0; i < array.size(); i++) {
        res.getAddress().add(parseAddress(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("partOf"))
      res.setPartOf(parseReference(json.getAsJsonObject("partOf")));
    if (json.has("contact")) {
      JsonArray array = json.getAsJsonArray("contact");
      for (int i = 0; i < array.size(); i++) {
        res.getContact().add(parseOrganizationOrganizationContactComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("location")) {
      JsonArray array = json.getAsJsonArray("location");
      for (int i = 0; i < array.size(); i++) {
        res.getLocation().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("active"))
      res.setActiveElement(parseBoolean(json.get("active").getAsBoolean()));
    if (json.has("_active"))
      parseElementProperties(json.getAsJsonObject("_active"), res.getActiveElement());
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
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getAsJsonObject("address")));
    if (json.has("gender"))
      res.setGenderElement(parseEnumeration(json.get("gender").getAsString(), Organization.AdministrativeGender.NULL, new Organization.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGenderElement());
    return res;
  }

  private Other parseOther(JsonObject json) throws Exception {
    Other res = new Other();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("code"))
      res.setCode(parseCodeableConcept(json.getAsJsonObject("code")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    if (json.has("created"))
      res.setCreatedElement(parseDate(json.get("created").getAsString()));
    if (json.has("_created"))
      parseElementProperties(json.getAsJsonObject("_created"), res.getCreatedElement());
    return res;
  }

  private Patient parsePatient(JsonObject json) throws Exception {
    Patient res = new Patient();
    parseDomainResourceProperties(json, res);
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
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("gender"))
      res.setGenderElement(parseEnumeration(json.get("gender").getAsString(), Patient.AdministrativeGender.NULL, new Patient.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGenderElement());
    if (json.has("birthDate"))
      res.setBirthDateElement(parseDateTime(json.get("birthDate").getAsString()));
    if (json.has("_birthDate"))
      parseElementProperties(json.getAsJsonObject("_birthDate"), res.getBirthDateElement());
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
        res.getCareProvider().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("managingOrganization"))
      res.setManagingOrganization(parseReference(json.getAsJsonObject("managingOrganization")));
    if (json.has("link")) {
      JsonArray array = json.getAsJsonArray("link");
      for (int i = 0; i < array.size(); i++) {
        res.getLink().add(parsePatientPatientLinkComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("active"))
      res.setActiveElement(parseBoolean(json.get("active").getAsBoolean()));
    if (json.has("_active"))
      parseElementProperties(json.getAsJsonObject("_active"), res.getActiveElement());
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
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address"))
      res.setAddress(parseAddress(json.getAsJsonObject("address")));
    if (json.has("gender"))
      res.setGenderElement(parseEnumeration(json.get("gender").getAsString(), Patient.AdministrativeGender.NULL, new Patient.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGenderElement());
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
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
      res.setOther(parseReference(json.getAsJsonObject("other")));
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Patient.LinkType.NULL, new Patient.LinkTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    return res;
  }

  private Practitioner parsePractitioner(JsonObject json) throws Exception {
    Practitioner res = new Practitioner();
    parseDomainResourceProperties(json, res);
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
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("address")) {
      JsonArray array = json.getAsJsonArray("address");
      for (int i = 0; i < array.size(); i++) {
        res.getAddress().add(parseAddress(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("gender"))
      res.setGenderElement(parseEnumeration(json.get("gender").getAsString(), Practitioner.AdministrativeGender.NULL, new Practitioner.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGenderElement());
    if (json.has("birthDate"))
      res.setBirthDateElement(parseDateTime(json.get("birthDate").getAsString()));
    if (json.has("_birthDate"))
      parseElementProperties(json.getAsJsonObject("_birthDate"), res.getBirthDateElement());
    if (json.has("photo")) {
      JsonArray array = json.getAsJsonArray("photo");
      for (int i = 0; i < array.size(); i++) {
        res.getPhoto().add(parseAttachment(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
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
        res.getLocation().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setIssuer(parseReference(json.getAsJsonObject("issuer")));
    return res;
  }

  private Procedure parseProcedure(JsonObject json) throws Exception {
    Procedure res = new Procedure();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
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
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("outcome"))
      res.setOutcomeElement(parseString(json.get("outcome").getAsString()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcomeElement());
    if (json.has("report")) {
      JsonArray array = json.getAsJsonArray("report");
      for (int i = 0; i < array.size(); i++) {
        res.getReport().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("complication")) {
      JsonArray array = json.getAsJsonArray("complication");
      for (int i = 0; i < array.size(); i++) {
        res.getComplication().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("followUp"))
      res.setFollowUpElement(parseString(json.get("followUp").getAsString()));
    if (json.has("_followUp"))
      parseElementProperties(json.getAsJsonObject("_followUp"), res.getFollowUpElement());
    if (json.has("relatedItem")) {
      JsonArray array = json.getAsJsonArray("relatedItem");
      for (int i = 0; i < array.size(); i++) {
        res.getRelatedItem().add(parseProcedureProcedureRelatedItemComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("notes"))
      res.setNotesElement(parseString(json.get("notes").getAsString()));
    if (json.has("_notes"))
      parseElementProperties(json.getAsJsonObject("_notes"), res.getNotesElement());
    return res;
  }

  private Procedure.ProcedurePerformerComponent parseProcedureProcedurePerformerComponent(JsonObject json, Procedure owner) throws Exception {
    Procedure.ProcedurePerformerComponent res = new Procedure.ProcedurePerformerComponent();
    parseBackboneProperties(json, res);
    if (json.has("person"))
      res.setPerson(parseReference(json.getAsJsonObject("person")));
    if (json.has("role"))
      res.setRole(parseCodeableConcept(json.getAsJsonObject("role")));
    return res;
  }

  private Procedure.ProcedureRelatedItemComponent parseProcedureProcedureRelatedItemComponent(JsonObject json, Procedure owner) throws Exception {
    Procedure.ProcedureRelatedItemComponent res = new Procedure.ProcedureRelatedItemComponent();
    parseBackboneProperties(json, res);
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Procedure.ProcedureRelationshipType.NULL, new Procedure.ProcedureRelationshipTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    return res;
  }

  private ProcedureRequest parseProcedureRequest(JsonObject json) throws Exception {
    ProcedureRequest res = new ProcedureRequest();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
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
    Type timing = parseType("timing", json);
    if (timing != null)
      res.setTiming(timing);
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("performer"))
      res.setPerformer(parseReference(json.getAsJsonObject("performer")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), ProcedureRequest.ProcedureRequestStatus.NULL, new ProcedureRequest.ProcedureRequestStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("mode"))
      res.setModeElement(parseEnumeration(json.get("mode").getAsString(), ProcedureRequest.ProcedureRequestMode.NULL, new ProcedureRequest.ProcedureRequestModeEnumFactory()));
    if (json.has("_mode"))
      parseElementProperties(json.getAsJsonObject("_mode"), res.getModeElement());
    if (json.has("notes")) {
      JsonArray array = json.getAsJsonArray("notes");
      for (int i = 0; i < array.size(); i++) {
        res.getNotes().add(parseString(array.get(i).getAsString()));
      }
    };
    if (json.has("_notes")) {
      JsonArray array = json.getAsJsonArray("_notes");
      for (int i = 0; i < array.size(); i++) {
        if (i == res.getNotes().size())
          res.getNotes().add(parseString(null));
        if (array.get(i) instanceof JsonObject) 
          parseElementProperties(array.get(i).getAsJsonObject(), res.getNotes().get(i));
      }
    };
    Type asNeeded = parseType("asNeeded", json);
    if (asNeeded != null)
      res.setAsNeeded(asNeeded);
    if (json.has("orderedOn"))
      res.setOrderedOnElement(parseDateTime(json.get("orderedOn").getAsString()));
    if (json.has("_orderedOn"))
      parseElementProperties(json.getAsJsonObject("_orderedOn"), res.getOrderedOnElement());
    if (json.has("orderer"))
      res.setOrderer(parseReference(json.getAsJsonObject("orderer")));
    if (json.has("priority"))
      res.setPriorityElement(parseEnumeration(json.get("priority").getAsString(), ProcedureRequest.ProcedureRequestPriority.NULL, new ProcedureRequest.ProcedureRequestPriorityEnumFactory()));
    if (json.has("_priority"))
      parseElementProperties(json.getAsJsonObject("_priority"), res.getPriorityElement());
    return res;
  }

  private Profile parseProfile(JsonObject json) throws Exception {
    Profile res = new Profile();
    parseDomainResourceProperties(json, res);
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("code")) {
      JsonArray array = json.getAsJsonArray("code");
      for (int i = 0; i < array.size(); i++) {
        res.getCode().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Profile.ResourceProfileStatus.NULL, new Profile.ResourceProfileStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("experimental"))
      res.setExperimentalElement(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimentalElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("requirements"))
      res.setRequirementsElement(parseString(json.get("requirements").getAsString()));
    if (json.has("_requirements"))
      parseElementProperties(json.getAsJsonObject("_requirements"), res.getRequirementsElement());
    if (json.has("fhirVersion"))
      res.setFhirVersionElement(parseId(json.get("fhirVersion").getAsString()));
    if (json.has("_fhirVersion"))
      parseElementProperties(json.getAsJsonObject("_fhirVersion"), res.getFhirVersionElement());
    if (json.has("mapping")) {
      JsonArray array = json.getAsJsonArray("mapping");
      for (int i = 0; i < array.size(); i++) {
        res.getMapping().add(parseProfileProfileMappingComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("type"))
      res.setTypeElement(parseCode(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("base"))
      res.setBaseElement(parseUri(json.get("base").getAsString()));
    if (json.has("_base"))
      parseElementProperties(json.getAsJsonObject("_base"), res.getBaseElement());
    if (json.has("snapshot"))
      res.setSnapshot(parseProfileConstraintComponent(json.getAsJsonObject("snapshot"), res));
    if (json.has("differential"))
      res.setDifferential(parseProfileConstraintComponent(json.getAsJsonObject("differential"), res));
    if (json.has("searchParam")) {
      JsonArray array = json.getAsJsonArray("searchParam");
      for (int i = 0; i < array.size(); i++) {
        res.getSearchParam().add(parseProfileProfileSearchParamComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private Profile.ProfileMappingComponent parseProfileProfileMappingComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ProfileMappingComponent res = new Profile.ProfileMappingComponent();
    parseBackboneProperties(json, res);
    if (json.has("identity"))
      res.setIdentityElement(parseId(json.get("identity").getAsString()));
    if (json.has("_identity"))
      parseElementProperties(json.getAsJsonObject("_identity"), res.getIdentityElement());
    if (json.has("uri"))
      res.setUriElement(parseUri(json.get("uri").getAsString()));
    if (json.has("_uri"))
      parseElementProperties(json.getAsJsonObject("_uri"), res.getUriElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("comments"))
      res.setCommentsElement(parseString(json.get("comments").getAsString()));
    if (json.has("_comments"))
      parseElementProperties(json.getAsJsonObject("_comments"), res.getCommentsElement());
    return res;
  }

  private Profile.ConstraintComponent parseProfileConstraintComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ConstraintComponent res = new Profile.ConstraintComponent();
    parseBackboneProperties(json, res);
    if (json.has("element")) {
      JsonArray array = json.getAsJsonArray("element");
      for (int i = 0; i < array.size(); i++) {
        res.getElement().add(parseElementDefinition(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Profile.ProfileSearchParamComponent parseProfileProfileSearchParamComponent(JsonObject json, Profile owner) throws Exception {
    Profile.ProfileSearchParamComponent res = new Profile.ProfileSearchParamComponent();
    parseBackboneProperties(json, res);
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Profile.SearchParamType.NULL, new Profile.SearchParamTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("documentation"))
      res.setDocumentationElement(parseString(json.get("documentation").getAsString()));
    if (json.has("_documentation"))
      parseElementProperties(json.getAsJsonObject("_documentation"), res.getDocumentationElement());
    if (json.has("xpath"))
      res.setXpathElement(parseString(json.get("xpath").getAsString()));
    if (json.has("_xpath"))
      parseElementProperties(json.getAsJsonObject("_xpath"), res.getXpathElement());
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

  private Provenance parseProvenance(JsonObject json) throws Exception {
    Provenance res = new Provenance();
    parseDomainResourceProperties(json, res);
    if (json.has("target")) {
      JsonArray array = json.getAsJsonArray("target");
      for (int i = 0; i < array.size(); i++) {
        res.getTarget().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("period"))
      res.setPeriod(parsePeriod(json.getAsJsonObject("period")));
    if (json.has("recorded"))
      res.setRecordedElement(parseInstant(json.get("recorded").getAsString()));
    if (json.has("_recorded"))
      parseElementProperties(json.getAsJsonObject("_recorded"), res.getRecordedElement());
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("location"))
      res.setLocation(parseReference(json.getAsJsonObject("location")));
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
      res.setIntegritySignatureElement(parseString(json.get("integritySignature").getAsString()));
    if (json.has("_integritySignature"))
      parseElementProperties(json.getAsJsonObject("_integritySignature"), res.getIntegritySignatureElement());
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
      res.setReferenceElement(parseUri(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReferenceElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
    return res;
  }

  private Provenance.ProvenanceEntityComponent parseProvenanceProvenanceEntityComponent(JsonObject json, Provenance owner) throws Exception {
    Provenance.ProvenanceEntityComponent res = new Provenance.ProvenanceEntityComponent();
    parseBackboneProperties(json, res);
    if (json.has("role"))
      res.setRoleElement(parseEnumeration(json.get("role").getAsString(), Provenance.ProvenanceEntityRole.NULL, new Provenance.ProvenanceEntityRoleEnumFactory()));
    if (json.has("_role"))
      parseElementProperties(json.getAsJsonObject("_role"), res.getRoleElement());
    if (json.has("type"))
      res.setType(parseCoding(json.getAsJsonObject("type")));
    if (json.has("reference"))
      res.setReferenceElement(parseUri(json.get("reference").getAsString()));
    if (json.has("_reference"))
      parseElementProperties(json.getAsJsonObject("_reference"), res.getReferenceElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
    if (json.has("agent"))
      res.setAgent(parseProvenanceProvenanceAgentComponent(json.getAsJsonObject("agent"), owner));
    return res;
  }

  private Query parseQuery(JsonObject json) throws Exception {
    Query res = new Query();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifierElement(parseUri(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
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
      res.setIdentifierElement(parseUri(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("outcome"))
      res.setOutcomeElement(parseEnumeration(json.get("outcome").getAsString(), Query.QueryOutcome.NULL, new Query.QueryOutcomeEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcomeElement());
    if (json.has("total"))
      res.setTotalElement(parseInteger(json.get("total").getAsLong()));
    if (json.has("_total"))
      parseElementProperties(json.getAsJsonObject("_total"), res.getTotalElement());
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
        res.getReference().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Questionnaire parseQuestionnaire(JsonObject json) throws Exception {
    Questionnaire res = new Questionnaire();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Questionnaire.QuestionnaireStatus.NULL, new Questionnaire.QuestionnaireStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("group"))
      res.setGroup(parseQuestionnaireGroupComponent(json.getAsJsonObject("group"), res));
    return res;
  }

  private Questionnaire.GroupComponent parseQuestionnaireGroupComponent(JsonObject json, Questionnaire owner) throws Exception {
    Questionnaire.GroupComponent res = new Questionnaire.GroupComponent();
    parseBackboneProperties(json, res);
    if (json.has("linkId"))
      res.setLinkIdElement(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkIdElement());
    if (json.has("title"))
      res.setTitleElement(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitleElement());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    if (json.has("required"))
      res.setRequiredElement(parseBoolean(json.get("required").getAsBoolean()));
    if (json.has("_required"))
      parseElementProperties(json.getAsJsonObject("_required"), res.getRequiredElement());
    if (json.has("repeats"))
      res.setRepeatsElement(parseBoolean(json.get("repeats").getAsBoolean()));
    if (json.has("_repeats"))
      parseElementProperties(json.getAsJsonObject("_repeats"), res.getRepeatsElement());
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
      res.setLinkIdElement(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkIdElement());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseCoding(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Questionnaire.AnswerFormat.NULL, new Questionnaire.AnswerFormatEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("required"))
      res.setRequiredElement(parseBoolean(json.get("required").getAsBoolean()));
    if (json.has("_required"))
      parseElementProperties(json.getAsJsonObject("_required"), res.getRequiredElement());
    if (json.has("repeats"))
      res.setRepeatsElement(parseBoolean(json.get("repeats").getAsBoolean()));
    if (json.has("_repeats"))
      parseElementProperties(json.getAsJsonObject("_repeats"), res.getRepeatsElement());
    if (json.has("options"))
      res.setOptions(parseReference(json.getAsJsonObject("options")));
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
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("questionnaire"))
      res.setQuestionnaire(parseReference(json.getAsJsonObject("questionnaire")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), QuestionnaireAnswers.QuestionnaireAnswersStatus.NULL, new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    if (json.has("authored"))
      res.setAuthoredElement(parseDateTime(json.get("authored").getAsString()));
    if (json.has("_authored"))
      parseElementProperties(json.getAsJsonObject("_authored"), res.getAuthoredElement());
    if (json.has("source"))
      res.setSource(parseReference(json.getAsJsonObject("source")));
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("group"))
      res.setGroup(parseQuestionnaireAnswersGroupComponent(json.getAsJsonObject("group"), res));
    return res;
  }

  private QuestionnaireAnswers.GroupComponent parseQuestionnaireAnswersGroupComponent(JsonObject json, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.GroupComponent res = new QuestionnaireAnswers.GroupComponent();
    parseBackboneProperties(json, res);
    if (json.has("linkId"))
      res.setLinkIdElement(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkIdElement());
    if (json.has("title"))
      res.setTitleElement(parseString(json.get("title").getAsString()));
    if (json.has("_title"))
      parseElementProperties(json.getAsJsonObject("_title"), res.getTitleElement());
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
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
      res.setLinkIdElement(parseString(json.get("linkId").getAsString()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkIdElement());
    if (json.has("text"))
      res.setTextElement(parseString(json.get("text").getAsString()));
    if (json.has("_text"))
      parseElementProperties(json.getAsJsonObject("_text"), res.getTextElement());
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
    parseDomainResourceProperties(json, res);
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), ReferralRequest.Referralstatus.NULL, new ReferralRequest.ReferralstatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
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
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("requester"))
      res.setRequester(parseReference(json.getAsJsonObject("requester")));
    if (json.has("recipient")) {
      JsonArray array = json.getAsJsonArray("recipient");
      for (int i = 0; i < array.size(); i++) {
        res.getRecipient().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("encounter"))
      res.setEncounter(parseReference(json.getAsJsonObject("encounter")));
    if (json.has("dateSent"))
      res.setDateSentElement(parseDateTime(json.get("dateSent").getAsString()));
    if (json.has("_dateSent"))
      parseElementProperties(json.getAsJsonObject("_dateSent"), res.getDateSentElement());
    if (json.has("reason"))
      res.setReason(parseCodeableConcept(json.getAsJsonObject("reason")));
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("serviceRequested")) {
      JsonArray array = json.getAsJsonArray("serviceRequested");
      for (int i = 0; i < array.size(); i++) {
        res.getServiceRequested().add(parseCodeableConcept(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("supportingInformation")) {
      JsonArray array = json.getAsJsonArray("supportingInformation");
      for (int i = 0; i < array.size(); i++) {
        res.getSupportingInformation().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("fulfillmentTime"))
      res.setFulfillmentTime(parsePeriod(json.getAsJsonObject("fulfillmentTime")));
    return res;
  }

  private RelatedPerson parseRelatedPerson(JsonObject json) throws Exception {
    RelatedPerson res = new RelatedPerson();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
    if (json.has("relationship"))
      res.setRelationship(parseCodeableConcept(json.getAsJsonObject("relationship")));
    if (json.has("name"))
      res.setName(parseHumanName(json.getAsJsonObject("name")));
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("gender"))
      res.setGenderElement(parseEnumeration(json.get("gender").getAsString(), RelatedPerson.AdministrativeGender.NULL, new RelatedPerson.AdministrativeGenderEnumFactory()));
    if (json.has("_gender"))
      parseElementProperties(json.getAsJsonObject("_gender"), res.getGenderElement());
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
    parseDomainResourceProperties(json, res);
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("condition"))
      res.setCondition(parseReference(json.getAsJsonObject("condition")));
    if (json.has("performer"))
      res.setPerformer(parseReference(json.getAsJsonObject("performer")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("method"))
      res.setMethod(parseCodeableConcept(json.getAsJsonObject("method")));
    if (json.has("basis")) {
      JsonArray array = json.getAsJsonArray("basis");
      for (int i = 0; i < array.size(); i++) {
        res.getBasis().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("prediction")) {
      JsonArray array = json.getAsJsonArray("prediction");
      for (int i = 0; i < array.size(); i++) {
        res.getPrediction().add(parseRiskAssessmentRiskAssessmentPredictionComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    if (json.has("mitigation"))
      res.setMitigationElement(parseString(json.get("mitigation").getAsString()));
    if (json.has("_mitigation"))
      parseElementProperties(json.getAsJsonObject("_mitigation"), res.getMitigationElement());
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
      res.setRelativeRiskElement(parseDecimal(json.get("relativeRisk").getAsBigDecimal()));
    if (json.has("_relativeRisk"))
      parseElementProperties(json.getAsJsonObject("_relativeRisk"), res.getRelativeRiskElement());
    Type when = parseType("when", json);
    if (when != null)
      res.setWhen(when);
    if (json.has("rationale"))
      res.setRationaleElement(parseString(json.get("rationale").getAsString()));
    if (json.has("_rationale"))
      parseElementProperties(json.getAsJsonObject("_rationale"), res.getRationaleElement());
    return res;
  }

  private SecurityEvent parseSecurityEvent(JsonObject json) throws Exception {
    SecurityEvent res = new SecurityEvent();
    parseDomainResourceProperties(json, res);
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
      res.setActionElement(parseEnumeration(json.get("action").getAsString(), SecurityEvent.SecurityEventAction.NULL, new SecurityEvent.SecurityEventActionEnumFactory()));
    if (json.has("_action"))
      parseElementProperties(json.getAsJsonObject("_action"), res.getActionElement());
    if (json.has("dateTime"))
      res.setDateTimeElement(parseInstant(json.get("dateTime").getAsString()));
    if (json.has("_dateTime"))
      parseElementProperties(json.getAsJsonObject("_dateTime"), res.getDateTimeElement());
    if (json.has("outcome"))
      res.setOutcomeElement(parseEnumeration(json.get("outcome").getAsString(), SecurityEvent.SecurityEventOutcome.NULL, new SecurityEvent.SecurityEventOutcomeEnumFactory()));
    if (json.has("_outcome"))
      parseElementProperties(json.getAsJsonObject("_outcome"), res.getOutcomeElement());
    if (json.has("outcomeDesc"))
      res.setOutcomeDescElement(parseString(json.get("outcomeDesc").getAsString()));
    if (json.has("_outcomeDesc"))
      parseElementProperties(json.getAsJsonObject("_outcomeDesc"), res.getOutcomeDescElement());
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
      res.setReference(parseReference(json.getAsJsonObject("reference")));
    if (json.has("userId"))
      res.setUserIdElement(parseString(json.get("userId").getAsString()));
    if (json.has("_userId"))
      parseElementProperties(json.getAsJsonObject("_userId"), res.getUserIdElement());
    if (json.has("altId"))
      res.setAltIdElement(parseString(json.get("altId").getAsString()));
    if (json.has("_altId"))
      parseElementProperties(json.getAsJsonObject("_altId"), res.getAltIdElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("requestor"))
      res.setRequestorElement(parseBoolean(json.get("requestor").getAsBoolean()));
    if (json.has("_requestor"))
      parseElementProperties(json.getAsJsonObject("_requestor"), res.getRequestorElement());
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
      res.setIdentifierElement(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), SecurityEvent.NetworkType.NULL, new SecurityEvent.NetworkTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    return res;
  }

  private SecurityEvent.SecurityEventSourceComponent parseSecurityEventSecurityEventSourceComponent(JsonObject json, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventSourceComponent res = new SecurityEvent.SecurityEventSourceComponent();
    parseBackboneProperties(json, res);
    if (json.has("site"))
      res.setSiteElement(parseString(json.get("site").getAsString()));
    if (json.has("_site"))
      parseElementProperties(json.getAsJsonObject("_site"), res.getSiteElement());
    if (json.has("identifier"))
      res.setIdentifierElement(parseString(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
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
      res.setReference(parseReference(json.getAsJsonObject("reference")));
    if (json.has("type"))
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), SecurityEvent.ObjectType.NULL, new SecurityEvent.ObjectTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("role"))
      res.setRoleElement(parseEnumeration(json.get("role").getAsString(), SecurityEvent.ObjectRole.NULL, new SecurityEvent.ObjectRoleEnumFactory()));
    if (json.has("_role"))
      parseElementProperties(json.getAsJsonObject("_role"), res.getRoleElement());
    if (json.has("lifecycle"))
      res.setLifecycleElement(parseEnumeration(json.get("lifecycle").getAsString(), SecurityEvent.ObjectLifecycle.NULL, new SecurityEvent.ObjectLifecycleEnumFactory()));
    if (json.has("_lifecycle"))
      parseElementProperties(json.getAsJsonObject("_lifecycle"), res.getLifecycleElement());
    if (json.has("sensitivity"))
      res.setSensitivity(parseCodeableConcept(json.getAsJsonObject("sensitivity")));
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("query"))
      res.setQueryElement(parseBase64Binary(json.get("query").getAsString()));
    if (json.has("_query"))
      parseElementProperties(json.getAsJsonObject("_query"), res.getQueryElement());
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
      res.setTypeElement(parseString(json.get("type").getAsString()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("value"))
      res.setValueElement(parseBase64Binary(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private Slot parseSlot(JsonObject json) throws Exception {
    Slot res = new Slot();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("availability"))
      res.setAvailability(parseReference(json.getAsJsonObject("availability")));
    if (json.has("freeBusyType"))
      res.setFreeBusyTypeElement(parseEnumeration(json.get("freeBusyType").getAsString(), Slot.Slotstatus.NULL, new Slot.SlotstatusEnumFactory()));
    if (json.has("_freeBusyType"))
      parseElementProperties(json.getAsJsonObject("_freeBusyType"), res.getFreeBusyTypeElement());
    if (json.has("start"))
      res.setStartElement(parseInstant(json.get("start").getAsString()));
    if (json.has("_start"))
      parseElementProperties(json.getAsJsonObject("_start"), res.getStartElement());
    if (json.has("end"))
      res.setEndElement(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEndElement());
    if (json.has("overbooked"))
      res.setOverbookedElement(parseBoolean(json.get("overbooked").getAsBoolean()));
    if (json.has("_overbooked"))
      parseElementProperties(json.getAsJsonObject("_overbooked"), res.getOverbookedElement());
    if (json.has("comment"))
      res.setCommentElement(parseString(json.get("comment").getAsString()));
    if (json.has("_comment"))
      parseElementProperties(json.getAsJsonObject("_comment"), res.getCommentElement());
    if (json.has("lastModified"))
      res.setLastModifiedElement(parseDateTime(json.get("lastModified").getAsString()));
    if (json.has("_lastModified"))
      parseElementProperties(json.getAsJsonObject("_lastModified"), res.getLastModifiedElement());
    return res;
  }

  private Specimen parseSpecimen(JsonObject json) throws Exception {
    Specimen res = new Specimen();
    parseDomainResourceProperties(json, res);
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
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("accessionIdentifier"))
      res.setAccessionIdentifier(parseIdentifier(json.getAsJsonObject("accessionIdentifier")));
    if (json.has("receivedTime"))
      res.setReceivedTimeElement(parseDateTime(json.get("receivedTime").getAsString()));
    if (json.has("_receivedTime"))
      parseElementProperties(json.getAsJsonObject("_receivedTime"), res.getReceivedTimeElement());
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
      res.setRelationshipElement(parseEnumeration(json.get("relationship").getAsString(), Specimen.HierarchicalRelationshipType.NULL, new Specimen.HierarchicalRelationshipTypeEnumFactory()));
    if (json.has("_relationship"))
      parseElementProperties(json.getAsJsonObject("_relationship"), res.getRelationshipElement());
    if (json.has("target")) {
      JsonArray array = json.getAsJsonArray("target");
      for (int i = 0; i < array.size(); i++) {
        res.getTarget().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private Specimen.SpecimenCollectionComponent parseSpecimenSpecimenCollectionComponent(JsonObject json, Specimen owner) throws Exception {
    Specimen.SpecimenCollectionComponent res = new Specimen.SpecimenCollectionComponent();
    parseBackboneProperties(json, res);
    if (json.has("collector"))
      res.setCollector(parseReference(json.getAsJsonObject("collector")));
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
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("procedure"))
      res.setProcedure(parseCodeableConcept(json.getAsJsonObject("procedure")));
    if (json.has("additive")) {
      JsonArray array = json.getAsJsonArray("additive");
      for (int i = 0; i < array.size(); i++) {
        res.getAdditive().add(parseReference(array.get(i).getAsJsonObject()));
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
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("capacity"))
      res.setCapacity(parseQuantity(json.getAsJsonObject("capacity")));
    if (json.has("specimenQuantity"))
      res.setSpecimenQuantity(parseQuantity(json.getAsJsonObject("specimenQuantity")));
    Type additive = parseType("additive", json);
    if (additive != null)
      res.setAdditive(additive);
    return res;
  }

  private Subscription parseSubscription(JsonObject json) throws Exception {
    Subscription res = new Subscription();
    parseDomainResourceProperties(json, res);
    if (json.has("criteria"))
      res.setCriteriaElement(parseString(json.get("criteria").getAsString()));
    if (json.has("_criteria"))
      parseElementProperties(json.getAsJsonObject("_criteria"), res.getCriteriaElement());
    if (json.has("contact")) {
      JsonArray array = json.getAsJsonArray("contact");
      for (int i = 0; i < array.size(); i++) {
        res.getContact().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("reason"))
      res.setReasonElement(parseString(json.get("reason").getAsString()));
    if (json.has("_reason"))
      parseElementProperties(json.getAsJsonObject("_reason"), res.getReasonElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Subscription.SubscriptionStatus.NULL, new Subscription.SubscriptionStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("error"))
      res.setErrorElement(parseString(json.get("error").getAsString()));
    if (json.has("_error"))
      parseElementProperties(json.getAsJsonObject("_error"), res.getErrorElement());
    if (json.has("channel"))
      res.setChannel(parseSubscriptionSubscriptionChannelComponent(json.getAsJsonObject("channel"), res));
    if (json.has("end"))
      res.setEndElement(parseInstant(json.get("end").getAsString()));
    if (json.has("_end"))
      parseElementProperties(json.getAsJsonObject("_end"), res.getEndElement());
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
      res.setTypeElement(parseEnumeration(json.get("type").getAsString(), Subscription.SubscriptionChannelType.NULL, new Subscription.SubscriptionChannelTypeEnumFactory()));
    if (json.has("_type"))
      parseElementProperties(json.getAsJsonObject("_type"), res.getTypeElement());
    if (json.has("url"))
      res.setUrlElement(parseUri(json.get("url").getAsString()));
    if (json.has("_url"))
      parseElementProperties(json.getAsJsonObject("_url"), res.getUrlElement());
    if (json.has("payload"))
      res.setPayloadElement(parseString(json.get("payload").getAsString()));
    if (json.has("_payload"))
      parseElementProperties(json.getAsJsonObject("_payload"), res.getPayloadElement());
    if (json.has("header"))
      res.setHeaderElement(parseString(json.get("header").getAsString()));
    if (json.has("_header"))
      parseElementProperties(json.getAsJsonObject("_header"), res.getHeaderElement());
    return res;
  }

  private Subscription.SubscriptionTagComponent parseSubscriptionSubscriptionTagComponent(JsonObject json, Subscription owner) throws Exception {
    Subscription.SubscriptionTagComponent res = new Subscription.SubscriptionTagComponent();
    parseBackboneProperties(json, res);
    if (json.has("term"))
      res.setTermElement(parseUri(json.get("term").getAsString()));
    if (json.has("_term"))
      parseElementProperties(json.getAsJsonObject("_term"), res.getTermElement());
    if (json.has("scheme"))
      res.setSchemeElement(parseUri(json.get("scheme").getAsString()));
    if (json.has("_scheme"))
      parseElementProperties(json.getAsJsonObject("_scheme"), res.getSchemeElement());
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    return res;
  }

  private Substance parseSubstance(JsonObject json) throws Exception {
    Substance res = new Substance();
    parseDomainResourceProperties(json, res);
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
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
      res.setExpiryElement(parseDateTime(json.get("expiry").getAsString()));
    if (json.has("_expiry"))
      parseElementProperties(json.getAsJsonObject("_expiry"), res.getExpiryElement());
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
      res.setSubstance(parseReference(json.getAsJsonObject("substance")));
    return res;
  }

  private Supply parseSupply(JsonObject json) throws Exception {
    Supply res = new Supply();
    parseDomainResourceProperties(json, res);
    if (json.has("kind"))
      res.setKind(parseCodeableConcept(json.getAsJsonObject("kind")));
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Supply.ValuesetSupplyStatus.NULL, new Supply.ValuesetSupplyStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("orderedItem"))
      res.setOrderedItem(parseReference(json.getAsJsonObject("orderedItem")));
    if (json.has("patient"))
      res.setPatient(parseReference(json.getAsJsonObject("patient")));
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
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), Supply.ValuesetSupplyDispenseStatus.NULL, new Supply.ValuesetSupplyDispenseStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("type"))
      res.setType(parseCodeableConcept(json.getAsJsonObject("type")));
    if (json.has("quantity"))
      res.setQuantity(parseQuantity(json.getAsJsonObject("quantity")));
    if (json.has("suppliedItem"))
      res.setSuppliedItem(parseReference(json.getAsJsonObject("suppliedItem")));
    if (json.has("supplier"))
      res.setSupplier(parseReference(json.getAsJsonObject("supplier")));
    if (json.has("whenPrepared"))
      res.setWhenPrepared(parsePeriod(json.getAsJsonObject("whenPrepared")));
    if (json.has("whenHandedOver"))
      res.setWhenHandedOver(parsePeriod(json.getAsJsonObject("whenHandedOver")));
    if (json.has("destination"))
      res.setDestination(parseReference(json.getAsJsonObject("destination")));
    if (json.has("receiver")) {
      JsonArray array = json.getAsJsonArray("receiver");
      for (int i = 0; i < array.size(); i++) {
        res.getReceiver().add(parseReference(array.get(i).getAsJsonObject()));
      }
    };
    return res;
  }

  private SupportingDocumentation parseSupportingDocumentation(JsonObject json) throws Exception {
    SupportingDocumentation res = new SupportingDocumentation();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier")) {
      JsonArray array = json.getAsJsonArray("identifier");
      for (int i = 0; i < array.size(); i++) {
        res.getIdentifier().add(parseIdentifier(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("ruleset"))
      res.setRuleset(parseCoding(json.getAsJsonObject("ruleset")));
    if (json.has("originalRuleset"))
      res.setOriginalRuleset(parseCoding(json.getAsJsonObject("originalRuleset")));
    if (json.has("date"))
      res.setDateElement(parseDate(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("target"))
      res.setTarget(parseReference(json.getAsJsonObject("target")));
    if (json.has("provider"))
      res.setProvider(parseReference(json.getAsJsonObject("provider")));
    if (json.has("organization"))
      res.setOrganization(parseReference(json.getAsJsonObject("organization")));
    if (json.has("requestIdentifier"))
      res.setRequestIdentifier(parseIdentifier(json.getAsJsonObject("requestIdentifier")));
    if (json.has("request"))
      res.setRequest(parseReference(json.getAsJsonObject("request")));
    if (json.has("responseIdentifier"))
      res.setResponseIdentifier(parseIdentifier(json.getAsJsonObject("responseIdentifier")));
    if (json.has("response"))
      res.setResponse(parseReference(json.getAsJsonObject("response")));
    if (json.has("author"))
      res.setAuthor(parseReference(json.getAsJsonObject("author")));
    if (json.has("subject"))
      res.setSubject(parseReference(json.getAsJsonObject("subject")));
    if (json.has("detail")) {
      JsonArray array = json.getAsJsonArray("detail");
      for (int i = 0; i < array.size(); i++) {
        res.getDetail().add(parseSupportingDocumentationSupportingDocumentationDetailComponent(array.get(i).getAsJsonObject(), res));
      }
    };
    return res;
  }

  private SupportingDocumentation.SupportingDocumentationDetailComponent parseSupportingDocumentationSupportingDocumentationDetailComponent(JsonObject json, SupportingDocumentation owner) throws Exception {
    SupportingDocumentation.SupportingDocumentationDetailComponent res = new SupportingDocumentation.SupportingDocumentationDetailComponent();
    parseBackboneProperties(json, res);
    if (json.has("linkId"))
      res.setLinkIdElement(parseInteger(json.get("linkId").getAsLong()));
    if (json.has("_linkId"))
      parseElementProperties(json.getAsJsonObject("_linkId"), res.getLinkIdElement());
    Type content = parseType("content", json);
    if (content != null)
      res.setContent(content);
    if (json.has("date"))
      res.setDateElement(parseDate(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    return res;
  }

  private ValueSet parseValueSet(JsonObject json) throws Exception {
    ValueSet res = new ValueSet();
    parseDomainResourceProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifierElement(parseUri(json.get("identifier").getAsString()));
    if (json.has("_identifier"))
      parseElementProperties(json.getAsJsonObject("_identifier"), res.getIdentifierElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("name"))
      res.setNameElement(parseString(json.get("name").getAsString()));
    if (json.has("_name"))
      parseElementProperties(json.getAsJsonObject("_name"), res.getNameElement());
    if (json.has("purpose"))
      res.setPurposeElement(parseString(json.get("purpose").getAsString()));
    if (json.has("_purpose"))
      parseElementProperties(json.getAsJsonObject("_purpose"), res.getPurposeElement());
    if (json.has("immutable"))
      res.setImmutableElement(parseBoolean(json.get("immutable").getAsBoolean()));
    if (json.has("_immutable"))
      parseElementProperties(json.getAsJsonObject("_immutable"), res.getImmutableElement());
    if (json.has("publisher"))
      res.setPublisherElement(parseString(json.get("publisher").getAsString()));
    if (json.has("_publisher"))
      parseElementProperties(json.getAsJsonObject("_publisher"), res.getPublisherElement());
    if (json.has("telecom")) {
      JsonArray array = json.getAsJsonArray("telecom");
      for (int i = 0; i < array.size(); i++) {
        res.getTelecom().add(parseContactPoint(array.get(i).getAsJsonObject()));
      }
    };
    if (json.has("description"))
      res.setDescriptionElement(parseString(json.get("description").getAsString()));
    if (json.has("_description"))
      parseElementProperties(json.getAsJsonObject("_description"), res.getDescriptionElement());
    if (json.has("copyright"))
      res.setCopyrightElement(parseString(json.get("copyright").getAsString()));
    if (json.has("_copyright"))
      parseElementProperties(json.getAsJsonObject("_copyright"), res.getCopyrightElement());
    if (json.has("status"))
      res.setStatusElement(parseEnumeration(json.get("status").getAsString(), ValueSet.ValuesetStatus.NULL, new ValueSet.ValuesetStatusEnumFactory()));
    if (json.has("_status"))
      parseElementProperties(json.getAsJsonObject("_status"), res.getStatusElement());
    if (json.has("experimental"))
      res.setExperimentalElement(parseBoolean(json.get("experimental").getAsBoolean()));
    if (json.has("_experimental"))
      parseElementProperties(json.getAsJsonObject("_experimental"), res.getExperimentalElement());
    if (json.has("extensible"))
      res.setExtensibleElement(parseBoolean(json.get("extensible").getAsBoolean()));
    if (json.has("_extensible"))
      parseElementProperties(json.getAsJsonObject("_extensible"), res.getExtensibleElement());
    if (json.has("date"))
      res.setDateElement(parseDateTime(json.get("date").getAsString()));
    if (json.has("_date"))
      parseElementProperties(json.getAsJsonObject("_date"), res.getDateElement());
    if (json.has("stableDate"))
      res.setStableDateElement(parseDate(json.get("stableDate").getAsString()));
    if (json.has("_stableDate"))
      parseElementProperties(json.getAsJsonObject("_stableDate"), res.getStableDateElement());
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
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("caseSensitive"))
      res.setCaseSensitiveElement(parseBoolean(json.get("caseSensitive").getAsBoolean()));
    if (json.has("_caseSensitive"))
      parseElementProperties(json.getAsJsonObject("_caseSensitive"), res.getCaseSensitiveElement());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseValueSetConceptDefinitionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ConceptDefinitionComponent parseValueSetConceptDefinitionComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptDefinitionComponent res = new ValueSet.ConceptDefinitionComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("abstract"))
      res.setAbstractElement(parseBoolean(json.get("abstract").getAsBoolean()));
    if (json.has("_abstract"))
      parseElementProperties(json.getAsJsonObject("_abstract"), res.getAbstractElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
    if (json.has("definition"))
      res.setDefinitionElement(parseString(json.get("definition").getAsString()));
    if (json.has("_definition"))
      parseElementProperties(json.getAsJsonObject("_definition"), res.getDefinitionElement());
    if (json.has("designation")) {
      JsonArray array = json.getAsJsonArray("designation");
      for (int i = 0; i < array.size(); i++) {
        res.getDesignation().add(parseValueSetConceptDefinitionDesignationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseValueSetConceptDefinitionComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ConceptDefinitionDesignationComponent parseValueSetConceptDefinitionDesignationComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptDefinitionDesignationComponent res = new ValueSet.ConceptDefinitionDesignationComponent();
    parseBackboneProperties(json, res);
    if (json.has("language"))
      res.setLanguageElement(parseCode(json.get("language").getAsString()));
    if (json.has("_language"))
      parseElementProperties(json.getAsJsonObject("_language"), res.getLanguageElement());
    if (json.has("use"))
      res.setUse(parseCoding(json.getAsJsonObject("use")));
    if (json.has("value"))
      res.setValueElement(parseString(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
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
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("concept")) {
      JsonArray array = json.getAsJsonArray("concept");
      for (int i = 0; i < array.size(); i++) {
        res.getConcept().add(parseValueSetConceptReferenceComponent(array.get(i).getAsJsonObject(), owner));
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

  private ValueSet.ConceptReferenceComponent parseValueSetConceptReferenceComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptReferenceComponent res = new ValueSet.ConceptReferenceComponent();
    parseBackboneProperties(json, res);
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
    if (json.has("designation")) {
      JsonArray array = json.getAsJsonArray("designation");
      for (int i = 0; i < array.size(); i++) {
        res.getDesignation().add(parseValueSetConceptDefinitionDesignationComponent(array.get(i).getAsJsonObject(), owner));
      }
    };
    return res;
  }

  private ValueSet.ConceptSetFilterComponent parseValueSetConceptSetFilterComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ConceptSetFilterComponent res = new ValueSet.ConceptSetFilterComponent();
    parseBackboneProperties(json, res);
    if (json.has("property"))
      res.setPropertyElement(parseCode(json.get("property").getAsString()));
    if (json.has("_property"))
      parseElementProperties(json.getAsJsonObject("_property"), res.getPropertyElement());
    if (json.has("op"))
      res.setOpElement(parseEnumeration(json.get("op").getAsString(), ValueSet.FilterOperator.NULL, new ValueSet.FilterOperatorEnumFactory()));
    if (json.has("_op"))
      parseElementProperties(json.getAsJsonObject("_op"), res.getOpElement());
    if (json.has("value"))
      res.setValueElement(parseCode(json.get("value").getAsString()));
    if (json.has("_value"))
      parseElementProperties(json.getAsJsonObject("_value"), res.getValueElement());
    return res;
  }

  private ValueSet.ValueSetExpansionComponent parseValueSetValueSetExpansionComponent(JsonObject json, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionComponent res = new ValueSet.ValueSetExpansionComponent();
    parseBackboneProperties(json, res);
    if (json.has("identifier"))
      res.setIdentifier(parseIdentifier(json.getAsJsonObject("identifier")));
    if (json.has("timestamp"))
      res.setTimestampElement(parseDateTime(json.get("timestamp").getAsString()));
    if (json.has("_timestamp"))
      parseElementProperties(json.getAsJsonObject("_timestamp"), res.getTimestampElement());
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
      res.setSystemElement(parseUri(json.get("system").getAsString()));
    if (json.has("_system"))
      parseElementProperties(json.getAsJsonObject("_system"), res.getSystemElement());
    if (json.has("abstract"))
      res.setAbstractElement(parseBoolean(json.get("abstract").getAsBoolean()));
    if (json.has("_abstract"))
      parseElementProperties(json.getAsJsonObject("_abstract"), res.getAbstractElement());
    if (json.has("version"))
      res.setVersionElement(parseString(json.get("version").getAsString()));
    if (json.has("_version"))
      parseElementProperties(json.getAsJsonObject("_version"), res.getVersionElement());
    if (json.has("code"))
      res.setCodeElement(parseCode(json.get("code").getAsString()));
    if (json.has("_code"))
      parseElementProperties(json.getAsJsonObject("_code"), res.getCodeElement());
    if (json.has("display"))
      res.setDisplayElement(parseString(json.get("display").getAsString()));
    if (json.has("_display"))
      parseElementProperties(json.getAsJsonObject("_display"), res.getDisplayElement());
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
    if (t.equals("Alert"))
      return parseAlert(json);
    else if (t.equals("AllergyIntolerance"))
      return parseAllergyIntolerance(json);
    else if (t.equals("Appointment"))
      return parseAppointment(json);
    else if (t.equals("AppointmentResponse"))
      return parseAppointmentResponse(json);
    else if (t.equals("Availability"))
      return parseAvailability(json);
    else if (t.equals("Basic"))
      return parseBasic(json);
    else if (t.equals("Binary"))
      return parseBinary(json);
    else if (t.equals("Bundle"))
      return parseBundle(json);
    else if (t.equals("CarePlan"))
      return parseCarePlan(json);
    else if (t.equals("ClaimResponse"))
      return parseClaimResponse(json);
    else if (t.equals("CommunicationRequest"))
      return parseCommunicationRequest(json);
    else if (t.equals("Composition"))
      return parseComposition(json);
    else if (t.equals("ConceptMap"))
      return parseConceptMap(json);
    else if (t.equals("Condition"))
      return parseCondition(json);
    else if (t.equals("Conformance"))
      return parseConformance(json);
    else if (t.equals("Contract"))
      return parseContract(json);
    else if (t.equals("Contraindication"))
      return parseContraindication(json);
    else if (t.equals("Coverage"))
      return parseCoverage(json);
    else if (t.equals("DataElement"))
      return parseDataElement(json);
    else if (t.equals("Device"))
      return parseDevice(json);
    else if (t.equals("DeviceComponent"))
      return parseDeviceComponent(json);
    else if (t.equals("DeviceUseRequest"))
      return parseDeviceUseRequest(json);
    else if (t.equals("DeviceUseStatement"))
      return parseDeviceUseStatement(json);
    else if (t.equals("DiagnosticOrder"))
      return parseDiagnosticOrder(json);
    else if (t.equals("DiagnosticReport"))
      return parseDiagnosticReport(json);
    else if (t.equals("DocumentManifest"))
      return parseDocumentManifest(json);
    else if (t.equals("DocumentReference"))
      return parseDocumentReference(json);
    else if (t.equals("Eligibility"))
      return parseEligibility(json);
    else if (t.equals("Encounter"))
      return parseEncounter(json);
    else if (t.equals("ExplanationOfBenefit"))
      return parseExplanationOfBenefit(json);
    else if (t.equals("ExtensionDefinition"))
      return parseExtensionDefinition(json);
    else if (t.equals("FamilyHistory"))
      return parseFamilyHistory(json);
    else if (t.equals("Group"))
      return parseGroup(json);
    else if (t.equals("HealthcareService"))
      return parseHealthcareService(json);
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
    else if (t.equals("NamingSystem"))
      return parseNamingSystem(json);
    else if (t.equals("NutritionOrder"))
      return parseNutritionOrder(json);
    else if (t.equals("Observation"))
      return parseObservation(json);
    else if (t.equals("OperationDefinition"))
      return parseOperationDefinition(json);
    else if (t.equals("OperationOutcome"))
      return parseOperationOutcome(json);
    else if (t.equals("OralHealthClaim"))
      return parseOralHealthClaim(json);
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
    else if (t.equals("ProcedureRequest"))
      return parseProcedureRequest(json);
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
    else if (t.equals("SupportingDocumentation"))
      return parseSupportingDocumentation(json);
    else if (t.equals("ValueSet"))
      return parseValueSet(json);
    else if (t.equals("Binary"))
      return parseBinary(json);
    throw new Exception("Unknown.Unrecognised resource type '"+t+"' (in property 'resourceType')");
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
    else if (json.has(prefix+"Reference"))
      return parseReference(json.getAsJsonObject(prefix+"Reference"));
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
    else if (json.has(prefix+"ElementDefinition"))
      return parseElementDefinition(json.getAsJsonObject(prefix+"ElementDefinition"));
    else if (json.has(prefix+"Timing"))
      return parseTiming(json.getAsJsonObject(prefix+"Timing"));
    else if (json.has(prefix+"Address"))
      return parseAddress(json.getAsJsonObject(prefix+"Address"));
    else if (json.has(prefix+"HumanName"))
      return parseHumanName(json.getAsJsonObject(prefix+"HumanName"));
    else if (json.has(prefix+"ContactPoint"))
      return parseContactPoint(json.getAsJsonObject(prefix+"ContactPoint"));
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

  protected Type parseType(JsonObject json, String type) throws Exception {
    if (type.equals("Period"))
      return parsePeriod(json);
    else if (type.equals("Coding"))
      return parseCoding(json);
    else if (type.equals("Range"))
      return parseRange(json);
    else if (type.equals("Quantity"))
      return parseQuantity(json);
    else if (type.equals("Attachment"))
      return parseAttachment(json);
    else if (type.equals("Ratio"))
      return parseRatio(json);
    else if (type.equals("SampledData"))
      return parseSampledData(json);
    else if (type.equals("Reference"))
      return parseReference(json);
    else if (type.equals("CodeableConcept"))
      return parseCodeableConcept(json);
    else if (type.equals("Identifier"))
      return parseIdentifier(json);
    else if (type.equals("Age"))
      return parseAge(json);
    else if (type.equals("Count"))
      return parseCount(json);
    else if (type.equals("Money"))
      return parseMoney(json);
    else if (type.equals("Distance"))
      return parseDistance(json);
    else if (type.equals("Duration"))
      return parseDuration(json);
    else if (type.equals("ElementDefinition"))
      return parseElementDefinition(json);
    else if (type.equals("Timing"))
      return parseTiming(json);
    else if (type.equals("Address"))
      return parseAddress(json);
    else if (type.equals("HumanName"))
      return parseHumanName(json);
    else if (type.equals("ContactPoint"))
      return parseContactPoint(json);
    throw new Exception("Unknown Type "+type);
  }

  protected boolean hasTypeName(JsonObject json, String prefix) {
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
    if (json.has(prefix+"Reference"))
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
    if (json.has(prefix+"ElementDefinition"))
      return true;
    if (json.has(prefix+"Timing"))
      return true;
    if (json.has(prefix+"Address"))
      return true;
    if (json.has(prefix+"HumanName"))
      return true;
    if (json.has(prefix+"ContactPoint"))
      return true;
    if (json.has(prefix+"Alert"))
      return true;
    if (json.has(prefix+"AllergyIntolerance"))
      return true;
    if (json.has(prefix+"Appointment"))
      return true;
    if (json.has(prefix+"AppointmentResponse"))
      return true;
    if (json.has(prefix+"Availability"))
      return true;
    if (json.has(prefix+"Basic"))
      return true;
    if (json.has(prefix+"Binary"))
      return true;
    if (json.has(prefix+"Bundle"))
      return true;
    if (json.has(prefix+"CarePlan"))
      return true;
    if (json.has(prefix+"ClaimResponse"))
      return true;
    if (json.has(prefix+"CommunicationRequest"))
      return true;
    if (json.has(prefix+"Composition"))
      return true;
    if (json.has(prefix+"ConceptMap"))
      return true;
    if (json.has(prefix+"Condition"))
      return true;
    if (json.has(prefix+"Conformance"))
      return true;
    if (json.has(prefix+"Contract"))
      return true;
    if (json.has(prefix+"Contraindication"))
      return true;
    if (json.has(prefix+"Coverage"))
      return true;
    if (json.has(prefix+"DataElement"))
      return true;
    if (json.has(prefix+"Device"))
      return true;
    if (json.has(prefix+"DeviceComponent"))
      return true;
    if (json.has(prefix+"DeviceUseRequest"))
      return true;
    if (json.has(prefix+"DeviceUseStatement"))
      return true;
    if (json.has(prefix+"DiagnosticOrder"))
      return true;
    if (json.has(prefix+"DiagnosticReport"))
      return true;
    if (json.has(prefix+"DocumentManifest"))
      return true;
    if (json.has(prefix+"DocumentReference"))
      return true;
    if (json.has(prefix+"Eligibility"))
      return true;
    if (json.has(prefix+"Encounter"))
      return true;
    if (json.has(prefix+"ExplanationOfBenefit"))
      return true;
    if (json.has(prefix+"ExtensionDefinition"))
      return true;
    if (json.has(prefix+"FamilyHistory"))
      return true;
    if (json.has(prefix+"Group"))
      return true;
    if (json.has(prefix+"HealthcareService"))
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
    if (json.has(prefix+"MessageHeader"))
      return true;
    if (json.has(prefix+"NamingSystem"))
      return true;
    if (json.has(prefix+"NutritionOrder"))
      return true;
    if (json.has(prefix+"Observation"))
      return true;
    if (json.has(prefix+"OperationDefinition"))
      return true;
    if (json.has(prefix+"OperationOutcome"))
      return true;
    if (json.has(prefix+"OralHealthClaim"))
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
    if (json.has(prefix+"ProcedureRequest"))
      return true;
    if (json.has(prefix+"Profile"))
      return true;
    if (json.has(prefix+"Provenance"))
      return true;
    if (json.has(prefix+"Query"))
      return true;
    if (json.has(prefix+"Questionnaire"))
      return true;
    if (json.has(prefix+"QuestionnaireAnswers"))
      return true;
    if (json.has(prefix+"ReferralRequest"))
      return true;
    if (json.has(prefix+"RelatedPerson"))
      return true;
    if (json.has(prefix+"RiskAssessment"))
      return true;
    if (json.has(prefix+"SecurityEvent"))
      return true;
    if (json.has(prefix+"Slot"))
      return true;
    if (json.has(prefix+"Specimen"))
      return true;
    if (json.has(prefix+"Subscription"))
      return true;
    if (json.has(prefix+"Substance"))
      return true;
    if (json.has(prefix+"Supply"))
      return true;
    if (json.has(prefix+"SupportingDocumentation"))
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
    if (json.has(prefix+"Time") || json.has("_"+prefix+"Time"))
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

