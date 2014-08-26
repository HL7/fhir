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
import org.xmlpull.v1.*;

public class XmlParser extends XmlParserBase {

  public XmlParser() {
    super();
  }

  public XmlParser(boolean allowUnknownContent) {
    super();
    setAllowUnknownContent(allowUnknownContent);
  }

  private boolean parseElementContent(int eventType, XmlPullParser xpp, Element res) throws Exception {
    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extension")) 
      res.getExtensions().add(parseExtension(xpp));
    else
      return false;
      
    return true;
  }

  private boolean parseBackboneContent(int eventType, XmlPullParser xpp, BackboneElement res) throws Exception {
    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modifierExtension")) 
      res.getModifierExtensions().add(parseExtension(xpp));
    else
      return parseElementContent(eventType, xpp, res);
      
    return true;
  }

  @SuppressWarnings("unchecked")
  private <E extends Enum<E>> Enumeration<E> parseEnumeration(XmlPullParser xpp, E item, EnumFactory e) throws Exception {
    Enumeration<E> res = new Enumeration<E>();
    parseElementAttributes(xpp, res);
    res.setValue((E) e.fromCode(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private IntegerType parseInteger(XmlPullParser xpp) throws Exception {
    IntegerType res = new IntegerType();
    parseElementAttributes(xpp, res);
    res.setValue(parseIntegerPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DateTimeType parseDateTime(XmlPullParser xpp) throws Exception {
    DateTimeType res = new DateTimeType();
    parseElementAttributes(xpp, res);
    res.setValue(parseDateTimePrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private CodeType parseCode(XmlPullParser xpp) throws Exception {
    CodeType res = new CodeType();
    parseElementAttributes(xpp, res);
    res.setValue(parseCodePrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DateType parseDate(XmlPullParser xpp) throws Exception {
    DateType res = new DateType();
    parseElementAttributes(xpp, res);
    res.setValue(parseDatePrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DecimalType parseDecimal(XmlPullParser xpp) throws Exception {
    DecimalType res = new DecimalType();
    parseElementAttributes(xpp, res);
    res.setValue(parseDecimalPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private UriType parseUri(XmlPullParser xpp) throws Exception {
    UriType res = new UriType();
    parseElementAttributes(xpp, res);
    res.setValue(parseUriPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private IdType parseId(XmlPullParser xpp) throws Exception {
    IdType res = new IdType();
    parseElementAttributes(xpp, res);
    res.setValue(parseIdPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Base64BinaryType parseBase64Binary(XmlPullParser xpp) throws Exception {
    Base64BinaryType res = new Base64BinaryType();
    parseElementAttributes(xpp, res);
    res.setValue(parseBase64BinaryPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private TimeType parseTime(XmlPullParser xpp) throws Exception {
    TimeType res = new TimeType();
    parseElementAttributes(xpp, res);
    res.setValue(parseTimePrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private OidType parseOid(XmlPullParser xpp) throws Exception {
    OidType res = new OidType();
    parseElementAttributes(xpp, res);
    res.setValue(parseOidPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private StringType parseString(XmlPullParser xpp) throws Exception {
    StringType res = new StringType();
    parseElementAttributes(xpp, res);
    res.setValue(parseStringPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private BooleanType parseBoolean(XmlPullParser xpp) throws Exception {
    BooleanType res = new BooleanType();
    parseElementAttributes(xpp, res);
    res.setValue(parseBooleanPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private UuidType parseUuid(XmlPullParser xpp) throws Exception {
    UuidType res = new UuidType();
    parseElementAttributes(xpp, res);
    res.setValue(parseUuidPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private InstantType parseInstant(XmlPullParser xpp) throws Exception {
    InstantType res = new InstantType();
    parseElementAttributes(xpp, res);
    res.setValue(parseInstantPrimitive(xpp.getAttributeValue(null, "value")));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Extension parseExtension(XmlPullParser xpp) throws Exception {
    Extension res = new Extension();
    parseElementAttributes(xpp, res);
    if (xpp.getAttributeValue(null, "url") != null)
        res.setUrlSimple(xpp.getAttributeValue(null, "url"));
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Narrative parseNarrative(XmlPullParser xpp) throws Exception {
    Narrative res = new Narrative();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Narrative.NarrativeStatus.Null, new Narrative.NarrativeStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("div")) {
        res.setDiv(parseXhtml(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Period parsePeriod(XmlPullParser xpp) throws Exception {
    Period res = new Period();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStart(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Coding parseCoding(XmlPullParser xpp) throws Exception {
    Coding res = new Coding();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("primary")) {
        res.setPrimary(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("valueSet")) {
        res.setValueSet(parseResourceReference(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Range parseRange(XmlPullParser xpp) throws Exception {
    Range res = new Range();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("low")) {
        res.setLow(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("high")) {
        res.setHigh(parseQuantity(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Quantity parseQuantity(XmlPullParser xpp) throws Exception {
    Quantity res = new Quantity();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparator(parseEnumeration(xpp, Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Attachment parseAttachment(XmlPullParser xpp) throws Exception {
    Attachment res = new Attachment();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contentType")) {
        res.setContentType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.setLanguage(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("data")) {
        res.setData(parseBase64Binary(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("size")) {
        res.setSize(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("hash")) {
        res.setHash(parseBase64Binary(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Ratio parseRatio(XmlPullParser xpp) throws Exception {
    Ratio res = new Ratio();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numerator")) {
        res.setNumerator(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("denominator")) {
        res.setDenominator(parseQuantity(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SampledData parseSampledData(XmlPullParser xpp) throws Exception {
    SampledData res = new SampledData();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("origin")) {
        res.setOrigin(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("factor")) {
        res.setFactor(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lowerLimit")) {
        res.setLowerLimit(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("upperLimit")) {
        res.setUpperLimit(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dimensions")) {
        res.setDimensions(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("data")) {
        res.setData(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ResourceReference parseResourceReference(XmlPullParser xpp) throws Exception {
    ResourceReference res = new ResourceReference();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private CodeableConcept parseCodeableConcept(XmlPullParser xpp) throws Exception {
    CodeableConcept res = new CodeableConcept();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coding")) {
        res.getCoding().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Identifier parseIdentifier(XmlPullParser xpp) throws Exception {
    Identifier res = new Identifier();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, Identifier.IdentifierUse.Null, new Identifier.IdentifierUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("label")) {
        res.setLabel(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assigner")) {
        res.setAssigner(parseResourceReference(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Age parseAge(XmlPullParser xpp) throws Exception {
    Age res = new Age();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparator(parseEnumeration(xpp, Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Count parseCount(XmlPullParser xpp) throws Exception {
    Count res = new Count();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparator(parseEnumeration(xpp, Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Money parseMoney(XmlPullParser xpp) throws Exception {
    Money res = new Money();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparator(parseEnumeration(xpp, Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Distance parseDistance(XmlPullParser xpp) throws Exception {
    Distance res = new Distance();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparator(parseEnumeration(xpp, Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Duration parseDuration(XmlPullParser xpp) throws Exception {
    Duration res = new Duration();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparator(parseEnumeration(xpp, Quantity.QuantityComparator.Null, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Schedule parseSchedule(XmlPullParser xpp) throws Exception {
    Schedule res = new Schedule();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeat")) {
        res.setRepeat(parseScheduleScheduleRepeatComponent(xpp, res));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Schedule.ScheduleRepeatComponent parseScheduleScheduleRepeatComponent(XmlPullParser xpp, Schedule owner) throws Exception {
    Schedule.ScheduleRepeatComponent res = new Schedule.ScheduleRepeatComponent();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frequency")) {
        res.setFrequency(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("when")) {
        res.setWhen(parseEnumeration(xpp, Schedule.EventTiming.Null, new Schedule.EventTimingEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("duration")) {
        res.setDuration(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseEnumeration(xpp, Schedule.UnitsOfTime.Null, new Schedule.UnitsOfTimeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("count")) {
        res.setCount(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Contact parseContact(XmlPullParser xpp) throws Exception {
    Contact res = new Contact();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseEnumeration(xpp, Contact.ContactSystem.Null, new Contact.ContactSystemEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, Contact.ContactUse.Null, new Contact.ContactUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Address parseAddress(XmlPullParser xpp) throws Exception {
    Address res = new Address();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, Address.AddressUse.Null, new Address.AddressUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("line")) {
        res.getLine().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("city")) {
        res.setCity(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("state")) {
        res.setState(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("zip")) {
        res.setZip(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("country")) {
        res.setCountry(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private HumanName parseHumanName(XmlPullParser xpp) throws Exception {
    HumanName res = new HumanName();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, HumanName.NameUse.Null, new HumanName.NameUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("family")) {
        res.getFamily().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("given")) {
        res.getGiven().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prefix")) {
        res.getPrefix().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("suffix")) {
        res.getSuffix().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private boolean parseResourceContent(int eventType, XmlPullParser xpp, Resource res) throws Exception {
    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) { 
      res.setLanguage(parseCode(xpp));
    } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
      res.setText(parseNarrative(xpp));
    } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contained")) {
      next(xpp);
      nextNoWhitespace(xpp);
      res.getContained().add(parse(xpp));
      if (xpp.getName() == null) {;
        next(xpp);
      };
      if(xpp.getName() != null) {;
        next(xpp);
      };
      nextNoWhitespace(xpp);
    } else
      return parseBackboneContent(eventType, xpp, res);
      
    return true;
  }

  private AdverseReaction parseAdverseReaction(XmlPullParser xpp) throws Exception {
    AdverseReaction res = new AdverseReaction();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("didNotOccurFlag")) {
        res.setDidNotOccurFlag(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recorder")) {
        res.setRecorder(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("symptom")) {
        res.getSymptom().add(parseAdverseReactionAdverseReactionSymptomComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("exposure")) {
        res.getExposure().add(parseAdverseReactionAdverseReactionExposureComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private AdverseReaction.AdverseReactionSymptomComponent parseAdverseReactionAdverseReactionSymptomComponent(XmlPullParser xpp, AdverseReaction owner) throws Exception {
    AdverseReaction.AdverseReactionSymptomComponent res = new AdverseReaction.AdverseReactionSymptomComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverity(parseEnumeration(xpp, AdverseReaction.ReactionSeverity.Null, new AdverseReaction.ReactionSeverityEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private AdverseReaction.AdverseReactionExposureComponent parseAdverseReactionAdverseReactionExposureComponent(XmlPullParser xpp, AdverseReaction owner) throws Exception {
    AdverseReaction.AdverseReactionExposureComponent res = new AdverseReaction.AdverseReactionExposureComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, AdverseReaction.ExposureType.Null, new AdverseReaction.ExposureTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("causalityExpectation")) {
        res.setCausalityExpectation(parseEnumeration(xpp, AdverseReaction.CausalityExpectation.Null, new AdverseReaction.CausalityExpectationEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substance")) {
        res.setSubstance(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Alert parseAlert(XmlPullParser xpp) throws Exception {
    Alert res = new Alert();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Alert.AlertStatus.Null, new Alert.AlertStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNote(parseString(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private AllergyIntolerance parseAllergyIntolerance(XmlPullParser xpp) throws Exception {
    AllergyIntolerance res = new AllergyIntolerance();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("criticality")) {
        res.setCriticality(parseEnumeration(xpp, AllergyIntolerance.Criticality.Null, new AllergyIntolerance.CriticalityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sensitivityType")) {
        res.setSensitivityType(parseEnumeration(xpp, AllergyIntolerance.Sensitivitytype.Null, new AllergyIntolerance.SensitivitytypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recordedDate")) {
        res.setRecordedDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, AllergyIntolerance.Sensitivitystatus.Null, new AllergyIntolerance.SensitivitystatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recorder")) {
        res.setRecorder(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substance")) {
        res.setSubstance(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reaction")) {
        res.getReaction().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sensitivityTest")) {
        res.getSensitivityTest().add(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Appointment parseAppointment(XmlPullParser xpp) throws Exception {
    Appointment res = new Appointment();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStart(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("slot")) {
        res.getSlot().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setComment(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("order")) {
        res.setOrder(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseAppointmentAppointmentParticipantComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastModifiedBy")) {
        res.setLastModifiedBy(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastModified")) {
        res.setLastModified(parseDateTime(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Appointment.AppointmentParticipantComponent parseAppointmentAppointmentParticipantComponent(XmlPullParser xpp, Appointment owner) throws Exception {
    Appointment.AppointmentParticipantComponent res = new Appointment.AppointmentParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("required")) {
        res.setRequired(parseEnumeration(xpp, Appointment.Participantrequired.Null, new Appointment.ParticipantrequiredEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Appointment.Participationstatus.Null, new Appointment.ParticipationstatusEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private AppointmentResponse parseAppointmentResponse(XmlPullParser xpp) throws Exception {
    AppointmentResponse res = new AppointmentResponse();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("appointment")) {
        res.setAppointment(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participantType")) {
        res.getParticipantType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("individual")) {
        res.getIndividual().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participantStatus")) {
        res.setParticipantStatus(parseEnumeration(xpp, AppointmentResponse.Participantstatus.Null, new AppointmentResponse.ParticipantstatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setComment(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStart(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastModifiedBy")) {
        res.setLastModifiedBy(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastModified")) {
        res.setLastModified(parseDateTime(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Availability parseAvailability(XmlPullParser xpp) throws Exception {
    Availability res = new Availability();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("planningHorizon")) {
        res.setPlanningHorizon(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setComment(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastModified")) {
        res.setLastModified(parseDateTime(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private CarePlan parseCarePlan(XmlPullParser xpp) throws Exception {
    CarePlan res = new CarePlan();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, CarePlan.CarePlanStatus.Null, new CarePlan.CarePlanStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modified")) {
        res.setModified(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concern")) {
        res.getConcern().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseCarePlanCarePlanParticipantComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("goal")) {
        res.getGoal().add(parseCarePlanCarePlanGoalComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("activity")) {
        res.getActivity().add(parseCarePlanCarePlanActivityComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotes(parseString(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private CarePlan.CarePlanParticipantComponent parseCarePlanCarePlanParticipantComponent(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.CarePlanParticipantComponent res = new CarePlan.CarePlanParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("member")) {
        res.setMember(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private CarePlan.CarePlanGoalComponent parseCarePlanCarePlanGoalComponent(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.CarePlanGoalComponent res = new CarePlan.CarePlanGoalComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, CarePlan.CarePlanGoalStatus.Null, new CarePlan.CarePlanGoalStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotes(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concern")) {
        res.getConcern().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private CarePlan.CarePlanActivityComponent parseCarePlanCarePlanActivityComponent(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivityComponent res = new CarePlan.CarePlanActivityComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("goal")) {
        res.getGoal().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, CarePlan.CarePlanActivityStatus.Null, new CarePlan.CarePlanActivityStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prohibited")) {
        res.setProhibited(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actionResulting")) {
        res.getActionResulting().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotes(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.setDetail(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("simple")) {
        res.setSimple(parseCarePlanCarePlanActivitySimpleComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private CarePlan.CarePlanActivitySimpleComponent parseCarePlanCarePlanActivitySimpleComponent(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivitySimpleComponent res = new CarePlan.CarePlanActivitySimpleComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseEnumeration(xpp, CarePlan.CarePlanActivityCategory.Null, new CarePlan.CarePlanActivityCategoryEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "timing")) {
        res.setTiming(parseType("timing", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.getPerformer().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.setProduct(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dailyAmount")) {
        res.setDailyAmount(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetails(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Composition parseComposition(XmlPullParser xpp) throws Exception {
    Composition res = new Composition();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("class")) {
        res.setClass_(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Composition.CompositionStatus.Null, new Composition.CompositionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("confidentiality")) {
        res.setConfidentiality(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.getAuthor().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("attester")) {
        res.getAttester().add(parseCompositionCompositionAttesterComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("custodian")) {
        res.setCustodian(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.setEvent(parseCompositionCompositionEventComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("section")) {
        res.getSection().add(parseCompositionSectionComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Composition.CompositionAttesterComponent parseCompositionCompositionAttesterComponent(XmlPullParser xpp, Composition owner) throws Exception {
    Composition.CompositionAttesterComponent res = new Composition.CompositionAttesterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.getMode().add(parseEnumeration(xpp, Composition.CompositionAttestationMode.Null, new Composition.CompositionAttestationModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("time")) {
        res.setTime(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("party")) {
        res.setParty(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Composition.CompositionEventComponent parseCompositionCompositionEventComponent(XmlPullParser xpp, Composition owner) throws Exception {
    Composition.CompositionEventComponent res = new Composition.CompositionEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Composition.SectionComponent parseCompositionSectionComponent(XmlPullParser xpp, Composition owner) throws Exception {
    Composition.SectionComponent res = new Composition.SectionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.setContent(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("section")) {
        res.getSection().add(parseCompositionSectionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ConceptMap parseConceptMap(XmlPullParser xpp) throws Exception {
    ConceptMap res = new ConceptMap();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyright(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, ConceptMap.ValuesetStatus.Null, new ConceptMap.ValuesetStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimental(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "source")) {
        res.setSource(parseType("source", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "target")) {
        res.setTarget(parseType("target", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.getElement().add(parseConceptMapConceptMapElementComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ConceptMap.ConceptMapElementComponent parseConceptMapConceptMapElementComponent(XmlPullParser xpp, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapElementComponent res = new ConceptMap.ConceptMapElementComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codeSystem")) {
        res.setCodeSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dependsOn")) {
        res.getDependsOn().add(parseConceptMapOtherElementComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("map")) {
        res.getMap().add(parseConceptMapConceptMapElementMapComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ConceptMap.OtherElementComponent parseConceptMapOtherElementComponent(XmlPullParser xpp, ConceptMap owner) throws Exception {
    ConceptMap.OtherElementComponent res = new ConceptMap.OtherElementComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.setElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codeSystem")) {
        res.setCodeSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ConceptMap.ConceptMapElementMapComponent parseConceptMapConceptMapElementMapComponent(XmlPullParser xpp, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapElementMapComponent res = new ConceptMap.ConceptMapElementMapComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codeSystem")) {
        res.setCodeSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("equivalence")) {
        res.setEquivalence(parseEnumeration(xpp, ConceptMap.ConceptEquivalence.Null, new ConceptMap.ConceptEquivalenceEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setComments(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.getProduct().add(parseConceptMapOtherElementComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Condition parseCondition(XmlPullParser xpp) throws Exception {
    Condition res = new Condition();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("asserter")) {
        res.setAsserter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateAsserted")) {
        res.setDateAsserted(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Condition.ConditionStatus.Null, new Condition.ConditionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("certainty")) {
        res.setCertainty(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverity(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "onset")) {
        res.setOnset(parseType("onset", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "abatement")) {
        res.setAbatement(parseType("abatement", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("stage")) {
        res.setStage(parseConditionConditionStageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("evidence")) {
        res.getEvidence().add(parseConditionConditionEvidenceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseConditionConditionLocationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relatedItem")) {
        res.getRelatedItem().add(parseConditionConditionRelatedItemComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotes(parseString(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Condition.ConditionStageComponent parseConditionConditionStageComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionStageComponent res = new Condition.ConditionStageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("summary")) {
        res.setSummary(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assessment")) {
        res.getAssessment().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Condition.ConditionEvidenceComponent parseConditionConditionEvidenceComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionEvidenceComponent res = new Condition.ConditionEvidenceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Condition.ConditionLocationComponent parseConditionConditionLocationComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionLocationComponent res = new Condition.ConditionLocationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.setDetail(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Condition.ConditionRelatedItemComponent parseConditionConditionRelatedItemComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionRelatedItemComponent res = new Condition.ConditionRelatedItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Condition.ConditionRelationshipType.Null, new Condition.ConditionRelationshipTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance parseConformance(XmlPullParser xpp) throws Exception {
    Conformance res = new Conformance();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Conformance.ConformanceStatementStatus.Null, new Conformance.ConformanceStatementStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimental(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("software")) {
        res.setSoftware(parseConformanceConformanceSoftwareComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("implementation")) {
        res.setImplementation(parseConformanceConformanceImplementationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fhirVersion")) {
        res.setFhirVersion(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("acceptUnknown")) {
        res.setAcceptUnknown(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("format")) {
        res.getFormat().add(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.getProfile().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rest")) {
        res.getRest().add(parseConformanceConformanceRestComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("messaging")) {
        res.getMessaging().add(parseConformanceConformanceMessagingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("document")) {
        res.getDocument().add(parseConformanceConformanceDocumentComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceSoftwareComponent parseConformanceConformanceSoftwareComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceSoftwareComponent res = new Conformance.ConformanceSoftwareComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("releaseDate")) {
        res.setReleaseDate(parseDateTime(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceImplementationComponent parseConformanceConformanceImplementationComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceImplementationComponent res = new Conformance.ConformanceImplementationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceRestComponent parseConformanceConformanceRestComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestComponent res = new Conformance.ConformanceRestComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, Conformance.RestfulConformanceMode.Null, new Conformance.RestfulConformanceModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("security")) {
        res.setSecurity(parseConformanceConformanceRestSecurityComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("resource")) {
        res.getResource().add(parseConformanceConformanceRestResourceComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interaction")) {
        res.getInteraction().add(parseConformanceSystemInteractionComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operation")) {
        res.getOperation().add(parseConformanceConformanceRestOperationComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentMailbox")) {
        res.getDocumentMailbox().add(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceRestSecurityComponent parseConformanceConformanceRestSecurityComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityComponent res = new Conformance.ConformanceRestSecurityComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("cors")) {
        res.setCors(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.getService().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("certificate")) {
        res.getCertificate().add(parseConformanceConformanceRestSecurityCertificateComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceRestSecurityCertificateComponent parseConformanceConformanceRestSecurityCertificateComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityCertificateComponent res = new Conformance.ConformanceRestSecurityCertificateComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("blob")) {
        res.setBlob(parseBase64Binary(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceRestResourceComponent parseConformanceConformanceRestResourceComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interaction")) {
        res.getInteraction().add(parseConformanceResourceInteractionComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("readHistory")) {
        res.setReadHistory(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("updateCreate")) {
        res.setUpdateCreate(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("searchInclude")) {
        res.getSearchInclude().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("searchParam")) {
        res.getSearchParam().add(parseConformanceConformanceRestResourceSearchParamComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ResourceInteractionComponent parseConformanceResourceInteractionComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ResourceInteractionComponent res = new Conformance.ResourceInteractionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseEnumeration(xpp, Conformance.TypeRestfulInteraction.Null, new Conformance.TypeRestfulInteractionEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceRestResourceSearchParamComponent parseConformanceConformanceRestResourceSearchParamComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceSearchParamComponent res = new Conformance.ConformanceRestResourceSearchParamComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinition(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Conformance.SearchParamType.Null, new Conformance.SearchParamTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.getTarget().add(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("chain")) {
        res.getChain().add(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.SystemInteractionComponent parseConformanceSystemInteractionComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.SystemInteractionComponent res = new Conformance.SystemInteractionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseEnumeration(xpp, Conformance.SystemRestfulInteraction.Null, new Conformance.SystemRestfulInteractionEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceRestOperationComponent parseConformanceConformanceRestOperationComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestOperationComponent res = new Conformance.ConformanceRestOperationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinition(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceMessagingComponent parseConformanceConformanceMessagingComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingComponent res = new Conformance.ConformanceMessagingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpoint(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reliableCache")) {
        res.setReliableCache(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseConformanceConformanceMessagingEventComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceMessagingEventComponent parseConformanceConformanceMessagingEventComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingEventComponent res = new Conformance.ConformanceMessagingEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseEnumeration(xpp, Conformance.MessageSignificanceCategory.Null, new Conformance.MessageSignificanceCategoryEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, Conformance.MessageConformanceEventMode.Null, new Conformance.MessageConformanceEventModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("protocol")) {
        res.getProtocol().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("focus")) {
        res.setFocus(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Conformance.ConformanceDocumentComponent parseConformanceConformanceDocumentComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceDocumentComponent res = new Conformance.ConformanceDocumentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, Conformance.DocumentMode.Null, new Conformance.DocumentModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Contraindication parseContraindication(XmlPullParser xpp) throws Exception {
    Contraindication res = new Contraindication();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverity(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("implicated")) {
        res.getImplicated().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.setDetail(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mitigation")) {
        res.getMitigation().add(parseContraindicationContraindicationMitigationComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Contraindication.ContraindicationMitigationComponent parseContraindicationContraindicationMitigationComponent(XmlPullParser xpp, Contraindication owner) throws Exception {
    Contraindication.ContraindicationMitigationComponent res = new Contraindication.ContraindicationMitigationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.setAction(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DataElement parseDataElement(XmlPullParser xpp) throws Exception {
    DataElement res = new DataElement();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, DataElement.ResourceObservationDefStatus.Null, new DataElement.ResourceObservationDefStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.getCategory().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("question")) {
        res.setQuestion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinition(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setComments(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirements(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("synonym")) {
        res.getSynonym().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "example")) {
        res.setExample(parseType("example", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxLength")) {
        res.setMaxLength(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("binding")) {
        res.setBinding(parseDataElementDataElementBindingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mapping")) {
        res.getMapping().add(parseDataElementDataElementMappingComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DataElement.DataElementBindingComponent parseDataElementDataElementBindingComponent(XmlPullParser xpp, DataElement owner) throws Exception {
    DataElement.DataElementBindingComponent res = new DataElement.DataElementBindingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("isExtensible")) {
        res.setIsExtensible(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("conformance")) {
        res.setConformance(parseEnumeration(xpp, DataElement.BindingConformance.Null, new DataElement.BindingConformanceEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("valueSet")) {
        res.setValueSet(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DataElement.DataElementMappingComponent parseDataElementDataElementMappingComponent(XmlPullParser xpp, DataElement owner) throws Exception {
    DataElement.DataElementMappingComponent res = new DataElement.DataElementMappingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri")) {
        res.setUri(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setComments(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("map")) {
        res.setMap(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Device parseDevice(XmlPullParser xpp) throws Exception {
    Device res = new Device();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufacturer")) {
        res.setManufacturer(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("model")) {
        res.setModel(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expiry")) {
        res.setExpiry(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("udi")) {
        res.setUdi(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lotNumber")) {
        res.setLotNumber(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("owner")) {
        res.setOwner(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DeviceObservationReport parseDeviceObservationReport(XmlPullParser xpp) throws Exception {
    DeviceObservationReport res = new DeviceObservationReport();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instant")) {
        res.setInstant(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("virtualDevice")) {
        res.getVirtualDevice().add(parseDeviceObservationReportDeviceObservationReportVirtualDeviceComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent parseDeviceObservationReportDeviceObservationReportVirtualDeviceComponent(XmlPullParser xpp, DeviceObservationReport owner) throws Exception {
    DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent res = new DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("channel")) {
        res.getChannel().add(parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent(XmlPullParser xpp, DeviceObservationReport owner) throws Exception {
    DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent res = new DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("metric")) {
        res.getMetric().add(parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent parseDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent(XmlPullParser xpp, DeviceObservationReport owner) throws Exception {
    DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent res = new DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("observation")) {
        res.setObservation(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DiagnosticOrder parseDiagnosticOrder(XmlPullParser xpp) throws Exception {
    DiagnosticOrder res = new DiagnosticOrder();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderer")) {
        res.setOrderer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("clinicalNotes")) {
        res.setClinicalNotes(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingInformation")) {
        res.getSupportingInformation().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.getSpecimen().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderPriority.Null, new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.getItem().add(parseDiagnosticOrderDiagnosticOrderItemComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DiagnosticOrder.DiagnosticOrderEventComponent parseDiagnosticOrderDiagnosticOrderEventComponent(XmlPullParser xpp, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderEventComponent res = new DiagnosticOrder.DiagnosticOrderEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTime(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DiagnosticOrder.DiagnosticOrderItemComponent parseDiagnosticOrderDiagnosticOrderItemComponent(XmlPullParser xpp, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderItemComponent res = new DiagnosticOrder.DiagnosticOrderItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.getSpecimen().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.setBodySite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderStatus.Null, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DiagnosticReport parseDiagnosticReport(XmlPullParser xpp) throws Exception {
    DiagnosticReport res = new DiagnosticReport();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, DiagnosticReport.DiagnosticReportStatus.Null, new DiagnosticReport.DiagnosticReportStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issued")) {
        res.setIssued(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.setPerformer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestDetail")) {
        res.getRequestDetail().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceCategory")) {
        res.setServiceCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "diagnostic")) {
        res.setDiagnostic(parseType("diagnostic", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.getSpecimen().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("result")) {
        res.getResult().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("imagingStudy")) {
        res.getImagingStudy().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("image")) {
        res.getImage().add(parseDiagnosticReportDiagnosticReportImageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("conclusion")) {
        res.setConclusion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codedDiagnosis")) {
        res.getCodedDiagnosis().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("presentedForm")) {
        res.getPresentedForm().add(parseAttachment(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DiagnosticReport.DiagnosticReportImageComponent parseDiagnosticReportDiagnosticReportImageComponent(XmlPullParser xpp, DiagnosticReport owner) throws Exception {
    DiagnosticReport.DiagnosticReportImageComponent res = new DiagnosticReport.DiagnosticReportImageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setComment(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.setLink(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DocumentManifest parseDocumentManifest(XmlPullParser xpp) throws Exception {
    DocumentManifest res = new DocumentManifest();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("masterIdentifier")) {
        res.setMasterIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.getSubject().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recipient")) {
        res.getRecipient().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.getAuthor().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreated(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, DocumentManifest.DocumentReferenceStatus.Null, new DocumentManifest.DocumentReferenceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supercedes")) {
        res.setSupercedes(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("confidentiality")) {
        res.setConfidentiality(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.getContent().add(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DocumentReference parseDocumentReference(XmlPullParser xpp) throws Exception {
    DocumentReference res = new DocumentReference();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("masterIdentifier")) {
        res.setMasterIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("class")) {
        res.setClass_(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.getAuthor().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("custodian")) {
        res.setCustodian(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("policyManager")) {
        res.setPolicyManager(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authenticator")) {
        res.setAuthenticator(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreated(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indexed")) {
        res.setIndexed(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, DocumentReference.DocumentReferenceStatus.Null, new DocumentReference.DocumentReferenceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("docStatus")) {
        res.setDocStatus(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relatesTo")) {
        res.getRelatesTo().add(parseDocumentReferenceDocumentReferenceRelatesToComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("confidentiality")) {
        res.getConfidentiality().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("primaryLanguage")) {
        res.setPrimaryLanguage(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mimeType")) {
        res.setMimeType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("format")) {
        res.getFormat().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("size")) {
        res.setSize(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("hash")) {
        res.setHash(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.setService(parseDocumentReferenceDocumentReferenceServiceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("context")) {
        res.setContext(parseDocumentReferenceDocumentReferenceContextComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DocumentReference.DocumentReferenceRelatesToComponent parseDocumentReferenceDocumentReferenceRelatesToComponent(XmlPullParser xpp, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceRelatesToComponent res = new DocumentReference.DocumentReferenceRelatesToComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseEnumeration(xpp, DocumentReference.DocumentRelationshipType.Null, new DocumentReference.DocumentRelationshipTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DocumentReference.DocumentReferenceServiceComponent parseDocumentReferenceDocumentReferenceServiceComponent(XmlPullParser xpp, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceServiceComponent res = new DocumentReference.DocumentReferenceServiceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseDocumentReferenceDocumentReferenceServiceParameterComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DocumentReference.DocumentReferenceServiceParameterComponent parseDocumentReferenceDocumentReferenceServiceParameterComponent(XmlPullParser xpp, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceServiceParameterComponent res = new DocumentReference.DocumentReferenceServiceParameterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private DocumentReference.DocumentReferenceContextComponent parseDocumentReferenceDocumentReferenceContextComponent(XmlPullParser xpp, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceContextComponent res = new DocumentReference.DocumentReferenceContextComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("facilityType")) {
        res.setFacilityType(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Encounter parseEncounter(XmlPullParser xpp) throws Exception {
    Encounter res = new Encounter();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Encounter.EncounterState.Null, new Encounter.EncounterStateEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("class")) {
        res.setClass_(parseEnumeration(xpp, Encounter.EncounterClass.Null, new Encounter.EncounterClassEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseEncounterEncounterParticipantComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fulfills")) {
        res.setFulfills(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("length")) {
        res.setLength(parseDuration(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indication")) {
        res.setIndication(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("hospitalization")) {
        res.setHospitalization(parseEncounterEncounterHospitalizationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseEncounterEncounterLocationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceProvider")) {
        res.setServiceProvider(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("partOf")) {
        res.setPartOf(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Encounter.EncounterParticipantComponent parseEncounterEncounterParticipantComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterParticipantComponent res = new Encounter.EncounterParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("individual")) {
        res.setIndividual(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Encounter.EncounterHospitalizationComponent parseEncounterEncounterHospitalizationComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationComponent res = new Encounter.EncounterHospitalizationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preAdmissionIdentifier")) {
        res.setPreAdmissionIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("origin")) {
        res.setOrigin(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("admitSource")) {
        res.setAdmitSource(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("accomodation")) {
        res.getAccomodation().add(parseEncounterEncounterHospitalizationAccomodationComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("diet")) {
        res.setDiet(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialCourtesy")) {
        res.getSpecialCourtesy().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialArrangement")) {
        res.getSpecialArrangement().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestination(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dischargeDisposition")) {
        res.setDischargeDisposition(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dischargeDiagnosis")) {
        res.setDischargeDiagnosis(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reAdmission")) {
        res.setReAdmission(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Encounter.EncounterHospitalizationAccomodationComponent parseEncounterEncounterHospitalizationAccomodationComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationAccomodationComponent res = new Encounter.EncounterHospitalizationAccomodationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bed")) {
        res.setBed(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Encounter.EncounterLocationComponent parseEncounterEncounterLocationComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterLocationComponent res = new Encounter.EncounterLocationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private FamilyHistory parseFamilyHistory(XmlPullParser xpp) throws Exception {
    FamilyHistory res = new FamilyHistory();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNote(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relation")) {
        res.getRelation().add(parseFamilyHistoryFamilyHistoryRelationComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private FamilyHistory.FamilyHistoryRelationComponent parseFamilyHistoryFamilyHistoryRelationComponent(XmlPullParser xpp, FamilyHistory owner) throws Exception {
    FamilyHistory.FamilyHistoryRelationComponent res = new FamilyHistory.FamilyHistoryRelationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "born")) {
        res.setBorn(parseType("born", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "age")) {
        res.setAge(parseType("age", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "deceased")) {
        res.setDeceased(parseType("deceased", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNote(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.getCondition().add(parseFamilyHistoryFamilyHistoryRelationConditionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private FamilyHistory.FamilyHistoryRelationConditionComponent parseFamilyHistoryFamilyHistoryRelationConditionComponent(XmlPullParser xpp, FamilyHistory owner) throws Exception {
    FamilyHistory.FamilyHistoryRelationConditionComponent res = new FamilyHistory.FamilyHistoryRelationConditionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcome(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "onset")) {
        res.setOnset(parseType("onset", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNote(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Group parseGroup(XmlPullParser xpp) throws Exception {
    Group res = new Group();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Group.GroupType.Null, new Group.GroupTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actual")) {
        res.setActual(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("characteristic")) {
        res.getCharacteristic().add(parseGroupGroupCharacteristicComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("member")) {
        res.getMember().add(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Group.GroupCharacteristicComponent parseGroupGroupCharacteristicComponent(XmlPullParser xpp, Group owner) throws Exception {
    Group.GroupCharacteristicComponent res = new Group.GroupCharacteristicComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("exclude")) {
        res.setExclude(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ImagingStudy parseImagingStudy(XmlPullParser xpp) throws Exception {
    ImagingStudy res = new ImagingStudy();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTime(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUid(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("accessionNo")) {
        res.setAccessionNo(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("order")) {
        res.getOrder().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modality")) {
        res.getModality().add(parseEnumeration(xpp, ImagingStudy.ImagingModality.Null, new ImagingStudy.ImagingModalityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referrer")) {
        res.setReferrer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availability")) {
        res.setAvailability(parseEnumeration(xpp, ImagingStudy.InstanceAvailability.Null, new ImagingStudy.InstanceAvailabilityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfSeries")) {
        res.setNumberOfSeries(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfInstances")) {
        res.setNumberOfInstances(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("clinicalInformation")) {
        res.setClinicalInformation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("procedure")) {
        res.getProcedure().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interpreter")) {
        res.setInterpreter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("series")) {
        res.getSeries().add(parseImagingStudyImagingStudySeriesComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ImagingStudy.ImagingStudySeriesComponent parseImagingStudyImagingStudySeriesComponent(XmlPullParser xpp, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesComponent res = new ImagingStudy.ImagingStudySeriesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("number")) {
        res.setNumber(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modality")) {
        res.setModality(parseEnumeration(xpp, ImagingStudy.Modality.Null, new ImagingStudy.ModalityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUid(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfInstances")) {
        res.setNumberOfInstances(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availability")) {
        res.setAvailability(parseEnumeration(xpp, ImagingStudy.InstanceAvailability.Null, new ImagingStudy.InstanceAvailabilityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.setBodySite(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTime(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instance")) {
        res.getInstance().add(parseImagingStudyImagingStudySeriesInstanceComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ImagingStudy.ImagingStudySeriesInstanceComponent parseImagingStudyImagingStudySeriesInstanceComponent(XmlPullParser xpp, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesInstanceComponent res = new ImagingStudy.ImagingStudySeriesInstanceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("number")) {
        res.setNumber(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUid(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sopclass")) {
        res.setSopclass(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("attachment")) {
        res.setAttachment(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Immunization parseImmunization(XmlPullParser xpp) throws Exception {
    Immunization res = new Immunization();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("vaccineType")) {
        res.setVaccineType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("refusedIndicator")) {
        res.setRefusedIndicator(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reported")) {
        res.setReported(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.setPerformer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requester")) {
        res.setRequester(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufacturer")) {
        res.setManufacturer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lotNumber")) {
        res.setLotNumber(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expirationDate")) {
        res.setExpirationDate(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("route")) {
        res.setRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseQuantity")) {
        res.setDoseQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("explanation")) {
        res.setExplanation(parseImmunizationImmunizationExplanationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reaction")) {
        res.getReaction().add(parseImmunizationImmunizationReactionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("vaccinationProtocol")) {
        res.getVaccinationProtocol().add(parseImmunizationImmunizationVaccinationProtocolComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Immunization.ImmunizationExplanationComponent parseImmunizationImmunizationExplanationComponent(XmlPullParser xpp, Immunization owner) throws Exception {
    Immunization.ImmunizationExplanationComponent res = new Immunization.ImmunizationExplanationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.getReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("refusalReason")) {
        res.getRefusalReason().add(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Immunization.ImmunizationReactionComponent parseImmunizationImmunizationReactionComponent(XmlPullParser xpp, Immunization owner) throws Exception {
    Immunization.ImmunizationReactionComponent res = new Immunization.ImmunizationReactionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.setDetail(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reported")) {
        res.setReported(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Immunization.ImmunizationVaccinationProtocolComponent parseImmunizationImmunizationVaccinationProtocolComponent(XmlPullParser xpp, Immunization owner) throws Exception {
    Immunization.ImmunizationVaccinationProtocolComponent res = new Immunization.ImmunizationVaccinationProtocolComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseSequence")) {
        res.setDoseSequence(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authority")) {
        res.setAuthority(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("series")) {
        res.setSeries(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("seriesDoses")) {
        res.setSeriesDoses(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseTarget")) {
        res.setDoseTarget(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseStatus")) {
        res.setDoseStatus(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseStatusReason")) {
        res.setDoseStatusReason(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ImmunizationRecommendation parseImmunizationRecommendation(XmlPullParser xpp) throws Exception {
    ImmunizationRecommendation res = new ImmunizationRecommendation();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recommendation")) {
        res.getRecommendation().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(XmlPullParser xpp, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("vaccineType")) {
        res.setVaccineType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseNumber")) {
        res.setDoseNumber(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("forecastStatus")) {
        res.setForecastStatus(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateCriterion")) {
        res.getDateCriterion().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("protocol")) {
        res.setProtocol(parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingImmunization")) {
        res.getSupportingImmunization().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingPatientInformation")) {
        res.getSupportingPatientInformation().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(XmlPullParser xpp, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDateTime(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(XmlPullParser xpp, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseSequence")) {
        res.setDoseSequence(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authority")) {
        res.setAuthority(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("series")) {
        res.setSeries(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private List_ parseList_(XmlPullParser xpp) throws Exception {
    List_ res = new List_();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ordered")) {
        res.setOrdered(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, List_.ListMode.Null, new List_.ListModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entry")) {
        res.getEntry().add(parseList_ListEntryComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("emptyReason")) {
        res.setEmptyReason(parseCodeableConcept(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private List_.ListEntryComponent parseList_ListEntryComponent(XmlPullParser xpp, List_ owner) throws Exception {
    List_.ListEntryComponent res = new List_.ListEntryComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("flag")) {
        res.getFlag().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("deleted")) {
        res.setDeleted(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Location parseLocation(XmlPullParser xpp) throws Exception {
    Location res = new Location();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("physicalType")) {
        res.setPhysicalType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("position")) {
        res.setPosition(parseLocationLocationPositionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("managingOrganization")) {
        res.setManagingOrganization(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Location.LocationStatus.Null, new Location.LocationStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("partOf")) {
        res.setPartOf(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, Location.LocationMode.Null, new Location.LocationModeEnumFactory()));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Location.LocationPositionComponent parseLocationLocationPositionComponent(XmlPullParser xpp, Location owner) throws Exception {
    Location.LocationPositionComponent res = new Location.LocationPositionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("longitude")) {
        res.setLongitude(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("latitude")) {
        res.setLatitude(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("altitude")) {
        res.setAltitude(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Media parseMedia(XmlPullParser xpp) throws Exception {
    Media res = new Media();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Media.MediaType.Null, new Media.MediaTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subtype")) {
        res.setSubtype(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTime(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operator")) {
        res.setOperator(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("view")) {
        res.setView(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("deviceName")) {
        res.setDeviceName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("height")) {
        res.setHeight(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("width")) {
        res.setWidth(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frames")) {
        res.setFrames(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("length")) {
        res.setLength(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.setContent(parseAttachment(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Medication parseMedication(XmlPullParser xpp) throws Exception {
    Medication res = new Medication();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("isBrand")) {
        res.setIsBrand(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufacturer")) {
        res.setManufacturer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("kind")) {
        res.setKind(parseEnumeration(xpp, Medication.MedicationKind.Null, new Medication.MedicationKindEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.setProduct(parseMedicationMedicationProductComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("package")) {
        res.setPackage(parseMedicationMedicationPackageComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Medication.MedicationProductComponent parseMedicationMedicationProductComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationProductComponent res = new Medication.MedicationProductComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("form")) {
        res.setForm(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ingredient")) {
        res.getIngredient().add(parseMedicationMedicationProductIngredientComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Medication.MedicationProductIngredientComponent parseMedicationMedicationProductIngredientComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationProductIngredientComponent res = new Medication.MedicationProductIngredientComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseRatio(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Medication.MedicationPackageComponent parseMedicationMedicationPackageComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationPackageComponent res = new Medication.MedicationPackageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("container")) {
        res.setContainer(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.getContent().add(parseMedicationMedicationPackageContentComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Medication.MedicationPackageContentComponent parseMedicationMedicationPackageContentComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationPackageContentComponent res = new Medication.MedicationPackageContentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseQuantity(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationAdministration parseMedicationAdministration(XmlPullParser xpp) throws Exception {
    MedicationAdministration res = new MedicationAdministration();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, MedicationAdministration.MedicationAdminStatus.Null, new MedicationAdministration.MedicationAdminStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("practitioner")) {
        res.setPractitioner(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prescription")) {
        res.setPrescription(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("wasNotGiven")) {
        res.setWasNotGiven(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reasonNotGiven")) {
        res.getReasonNotGiven().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenGiven")) {
        res.setWhenGiven(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("medication")) {
        res.setMedication(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("device")) {
        res.getDevice().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosage")) {
        res.getDosage().add(parseMedicationAdministrationMedicationAdministrationDosageComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationAdministration.MedicationAdministrationDosageComponent parseMedicationAdministrationMedicationAdministrationDosageComponent(XmlPullParser xpp, MedicationAdministration owner) throws Exception {
    MedicationAdministration.MedicationAdministrationDosageComponent res = new MedicationAdministration.MedicationAdministrationDosageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "timing")) {
        res.setTiming(parseType("timing", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "asNeeded")) {
        res.setAsNeeded(parseType("asNeeded", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("route")) {
        res.setRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rate")) {
        res.setRate(parseRatio(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxDosePerPeriod")) {
        res.setMaxDosePerPeriod(parseRatio(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationDispense parseMedicationDispense(XmlPullParser xpp) throws Exception {
    MedicationDispense res = new MedicationDispense();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, MedicationDispense.MedicationDispenseStatus.Null, new MedicationDispense.MedicationDispenseStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispenser")) {
        res.setDispenser(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authorizingPrescription")) {
        res.getAuthorizingPrescription().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispense")) {
        res.getDispense().add(parseMedicationDispenseMedicationDispenseDispenseComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substitution")) {
        res.setSubstitution(parseMedicationDispenseMedicationDispenseSubstitutionComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationDispense.MedicationDispenseDispenseComponent parseMedicationDispenseMedicationDispenseDispenseComponent(XmlPullParser xpp, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseDispenseComponent res = new MedicationDispense.MedicationDispenseDispenseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, MedicationDispense.MedicationDispenseStatus.Null, new MedicationDispense.MedicationDispenseStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("medication")) {
        res.setMedication(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenPrepared")) {
        res.setWhenPrepared(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenHandedOver")) {
        res.setWhenHandedOver(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestination(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receiver")) {
        res.getReceiver().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosage")) {
        res.getDosage().add(parseMedicationDispenseMedicationDispenseDispenseDosageComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationDispense.MedicationDispenseDispenseDosageComponent parseMedicationDispenseMedicationDispenseDispenseDosageComponent(XmlPullParser xpp, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseDispenseDosageComponent res = new MedicationDispense.MedicationDispenseDispenseDosageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additionalInstructions")) {
        res.setAdditionalInstructions(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "timing")) {
        res.setTiming(parseType("timing", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "asNeeded")) {
        res.setAsNeeded(parseType("asNeeded", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("route")) {
        res.setRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rate")) {
        res.setRate(parseRatio(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxDosePerPeriod")) {
        res.setMaxDosePerPeriod(parseRatio(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationDispense.MedicationDispenseSubstitutionComponent parseMedicationDispenseMedicationDispenseSubstitutionComponent(XmlPullParser xpp, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseSubstitutionComponent res = new MedicationDispense.MedicationDispenseSubstitutionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.getReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responsibleParty")) {
        res.getResponsibleParty().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationPrescription parseMedicationPrescription(XmlPullParser xpp) throws Exception {
    MedicationPrescription res = new MedicationPrescription();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateWritten")) {
        res.setDateWritten(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, MedicationPrescription.MedicationPrescriptionStatus.Null, new MedicationPrescription.MedicationPrescriptionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prescriber")) {
        res.setPrescriber(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reason")) {
        res.setReason(parseType("reason", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("medication")) {
        res.setMedication(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosageInstruction")) {
        res.getDosageInstruction().add(parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispense")) {
        res.setDispense(parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substitution")) {
        res.setSubstitution(parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionDosageInstructionComponent parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(XmlPullParser xpp, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDosageInstructionComponent res = new MedicationPrescription.MedicationPrescriptionDosageInstructionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additionalInstructions")) {
        res.setAdditionalInstructions(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "timing")) {
        res.setTiming(parseType("timing", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "asNeeded")) {
        res.setAsNeeded(parseType("asNeeded", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("route")) {
        res.setRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseQuantity")) {
        res.setDoseQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rate")) {
        res.setRate(parseRatio(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxDosePerPeriod")) {
        res.setMaxDosePerPeriod(parseRatio(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionDispenseComponent parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(XmlPullParser xpp, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDispenseComponent res = new MedicationPrescription.MedicationPrescriptionDispenseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("medication")) {
        res.setMedication(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("validityPeriod")) {
        res.setValidityPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfRepeatsAllowed")) {
        res.setNumberOfRepeatsAllowed(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expectedSupplyDuration")) {
        res.setExpectedSupplyDuration(parseDuration(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationPrescription.MedicationPrescriptionSubstitutionComponent parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(XmlPullParser xpp, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionSubstitutionComponent res = new MedicationPrescription.MedicationPrescriptionSubstitutionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationStatement parseMedicationStatement(XmlPullParser xpp) throws Exception {
    MedicationStatement res = new MedicationStatement();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("wasNotGiven")) {
        res.setWasNotGiven(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reasonNotGiven")) {
        res.getReasonNotGiven().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenGiven")) {
        res.setWhenGiven(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("medication")) {
        res.setMedication(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("device")) {
        res.getDevice().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosage")) {
        res.getDosage().add(parseMedicationStatementMedicationStatementDosageComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MedicationStatement.MedicationStatementDosageComponent parseMedicationStatementMedicationStatementDosageComponent(XmlPullParser xpp, MedicationStatement owner) throws Exception {
    MedicationStatement.MedicationStatementDosageComponent res = new MedicationStatement.MedicationStatementDosageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("timing")) {
        res.setTiming(parseSchedule(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "asNeeded")) {
        res.setAsNeeded(parseType("asNeeded", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("route")) {
        res.setRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rate")) {
        res.setRate(parseRatio(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxDosePerPeriod")) {
        res.setMaxDosePerPeriod(parseRatio(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MessageHeader parseMessageHeader(XmlPullParser xpp) throws Exception {
    MessageHeader res = new MessageHeader();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("timestamp")) {
        res.setTimestamp(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.setEvent(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseMessageHeaderMessageHeaderResponseComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseMessageHeaderMessageSourceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.getDestination().add(parseMessageHeaderMessageDestinationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("enterer")) {
        res.setEnterer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receiver")) {
        res.setReceiver(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responsible")) {
        res.setResponsible(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("data")) {
        res.getData().add(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MessageHeader.MessageHeaderResponseComponent parseMessageHeaderMessageHeaderResponseComponent(XmlPullParser xpp, MessageHeader owner) throws Exception {
    MessageHeader.MessageHeaderResponseComponent res = new MessageHeader.MessageHeaderResponseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseEnumeration(xpp, MessageHeader.ResponseCode.Null, new MessageHeader.ResponseCodeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetails(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MessageHeader.MessageSourceComponent parseMessageHeaderMessageSourceComponent(XmlPullParser xpp, MessageHeader owner) throws Exception {
    MessageHeader.MessageSourceComponent res = new MessageHeader.MessageSourceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("software")) {
        res.setSoftware(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.setContact(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpoint(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private MessageHeader.MessageDestinationComponent parseMessageHeaderMessageDestinationComponent(XmlPullParser xpp, MessageHeader owner) throws Exception {
    MessageHeader.MessageDestinationComponent res = new MessageHeader.MessageDestinationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpoint(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Namespace parseNamespace(XmlPullParser xpp) throws Exception {
    Namespace res = new Namespace();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Namespace.NamespaceType.Null, new Namespace.NamespaceTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Namespace.NamespaceStatus.Null, new Namespace.NamespaceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("country")) {
        res.setCountry(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responsible")) {
        res.setResponsible(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("usage")) {
        res.setUsage(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uniqueId")) {
        res.getUniqueId().add(parseNamespaceNamespaceUniqueIdComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.setContact(parseNamespaceNamespaceContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("replacedBy")) {
        res.setReplacedBy(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Namespace.NamespaceUniqueIdComponent parseNamespaceNamespaceUniqueIdComponent(XmlPullParser xpp, Namespace owner) throws Exception {
    Namespace.NamespaceUniqueIdComponent res = new Namespace.NamespaceUniqueIdComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Namespace.NamespaceIdentifierType.Null, new Namespace.NamespaceIdentifierTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preferred")) {
        res.setPreferred(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Namespace.NamespaceContactComponent parseNamespaceNamespaceContactComponent(XmlPullParser xpp, Namespace owner) throws Exception {
    Namespace.NamespaceContactComponent res = new Namespace.NamespaceContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Observation parseObservation(XmlPullParser xpp) throws Exception {
    Observation res = new Observation();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interpretation")) {
        res.setInterpretation(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setComments(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "applies")) {
        res.setApplies(parseType("applies", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issued")) {
        res.setIssued(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Observation.ObservationStatus.Null, new Observation.ObservationStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reliability")) {
        res.setReliability(parseEnumeration(xpp, Observation.ObservationReliability.Null, new Observation.ObservationReliabilityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.setBodySite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.setSpecimen(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.getPerformer().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referenceRange")) {
        res.getReferenceRange().add(parseObservationObservationReferenceRangeComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("related")) {
        res.getRelated().add(parseObservationObservationRelatedComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Observation.ObservationReferenceRangeComponent parseObservationObservationReferenceRangeComponent(XmlPullParser xpp, Observation owner) throws Exception {
    Observation.ObservationReferenceRangeComponent res = new Observation.ObservationReferenceRangeComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("low")) {
        res.setLow(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("high")) {
        res.setHigh(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("meaning")) {
        res.setMeaning(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("age")) {
        res.setAge(parseRange(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Observation.ObservationRelatedComponent parseObservationObservationRelatedComponent(XmlPullParser xpp, Observation owner) throws Exception {
    Observation.ObservationRelatedComponent res = new Observation.ObservationRelatedComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Observation.ObservationRelationshiptypes.Null, new Observation.ObservationRelationshiptypesEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private OperationDefinition parseOperationDefinition(XmlPullParser xpp) throws Exception {
    OperationDefinition res = new OperationDefinition();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, OperationDefinition.ResourceProfileStatus.Null, new OperationDefinition.ResourceProfileStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimental(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("kind")) {
        res.setKind(parseEnumeration(xpp, OperationDefinition.OperationKind.Null, new OperationDefinition.OperationKindEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotes(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBase(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instance")) {
        res.setInstance(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseOperationDefinitionOperationDefinitionParameterComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private OperationDefinition.OperationDefinitionParameterComponent parseOperationDefinitionOperationDefinitionParameterComponent(XmlPullParser xpp, OperationDefinition owner) throws Exception {
    OperationDefinition.OperationDefinitionParameterComponent res = new OperationDefinition.OperationDefinitionParameterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, OperationDefinition.OperationParameterUse.Null, new OperationDefinition.OperationParameterUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("min")) {
        res.setMin(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("max")) {
        res.setMax(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private OperationOutcome parseOperationOutcome(XmlPullParser xpp) throws Exception {
    OperationOutcome res = new OperationOutcome();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issue")) {
        res.getIssue().add(parseOperationOutcomeOperationOutcomeIssueComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private OperationOutcome.OperationOutcomeIssueComponent parseOperationOutcomeOperationOutcomeIssueComponent(XmlPullParser xpp, OperationOutcome owner) throws Exception {
    OperationOutcome.OperationOutcomeIssueComponent res = new OperationOutcome.OperationOutcomeIssueComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverity(parseEnumeration(xpp, OperationOutcome.IssueSeverity.Null, new OperationOutcome.IssueSeverityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetails(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Order parseOrder(XmlPullParser xpp) throws Exception {
    Order res = new Order();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reason")) {
        res.setReason(parseType("reason", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authority")) {
        res.setAuthority(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("when")) {
        res.setWhen(parseOrderOrderWhenComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Order.OrderWhenComponent parseOrderOrderWhenComponent(XmlPullParser xpp, Order owner) throws Exception {
    Order.OrderWhenComponent res = new Order.OrderWhenComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.setSchedule(parseSchedule(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private OrderResponse parseOrderResponse(XmlPullParser xpp) throws Exception {
    OrderResponse res = new OrderResponse();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("who")) {
        res.setWho(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "authority")) {
        res.setAuthority(parseType("authority", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseEnumeration(xpp, OrderResponse.OrderOutcomeCode.Null, new OrderResponse.OrderOutcomeCodeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fulfillment")) {
        res.getFulfillment().add(parseResourceReference(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Organization parseOrganization(XmlPullParser xpp) throws Exception {
    Organization res = new Organization();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("partOf")) {
        res.setPartOf(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseOrganizationOrganizationContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("active")) {
        res.setActive(parseBoolean(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Organization.OrganizationContactComponent parseOrganizationOrganizationContactComponent(XmlPullParser xpp, Organization owner) throws Exception {
    Organization.OrganizationContactComponent res = new Organization.OrganizationContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("purpose")) {
        res.setPurpose(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGender(parseEnumeration(xpp, Organization.AdministrativeGender.Null, new Organization.AdministrativeGenderEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Other parseOther(XmlPullParser xpp) throws Exception {
    Other res = new Other();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreated(parseDate(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Patient parsePatient(XmlPullParser xpp) throws Exception {
    Patient res = new Patient();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.getName().add(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGender(parseEnumeration(xpp, Patient.AdministrativeGender.Null, new Patient.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("birthDate")) {
        res.setBirthDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "deceased")) {
        res.setDeceased(parseType("deceased", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maritalStatus")) {
        res.setMaritalStatus(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "multipleBirth")) {
        res.setMultipleBirth(parseType("multipleBirth", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("photo")) {
        res.getPhoto().add(parseAttachment(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parsePatientContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("animal")) {
        res.setAnimal(parsePatientAnimalComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("communication")) {
        res.getCommunication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("careProvider")) {
        res.getCareProvider().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("managingOrganization")) {
        res.setManagingOrganization(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLink().add(parsePatientPatientLinkComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("active")) {
        res.setActive(parseBoolean(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Patient.ContactComponent parsePatientContactComponent(XmlPullParser xpp, Patient owner) throws Exception {
    Patient.ContactComponent res = new Patient.ContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.getRelationship().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGender(parseEnumeration(xpp, Patient.AdministrativeGender.Null, new Patient.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Patient.AnimalComponent parsePatientAnimalComponent(XmlPullParser xpp, Patient owner) throws Exception {
    Patient.AnimalComponent res = new Patient.AnimalComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("species")) {
        res.setSpecies(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("breed")) {
        res.setBreed(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("genderStatus")) {
        res.setGenderStatus(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Patient.PatientLinkComponent parsePatientPatientLinkComponent(XmlPullParser xpp, Patient owner) throws Exception {
    Patient.PatientLinkComponent res = new Patient.PatientLinkComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("other")) {
        res.setOther(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Patient.LinkType.Null, new Patient.LinkTypeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Practitioner parsePractitioner(XmlPullParser xpp) throws Exception {
    Practitioner res = new Practitioner();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGender(parseEnumeration(xpp, Practitioner.AdministrativeGender.Null, new Practitioner.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("birthDate")) {
        res.setBirthDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("photo")) {
        res.getPhoto().add(parseAttachment(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.getRole().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialty")) {
        res.getSpecialty().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("qualification")) {
        res.getQualification().add(parsePractitionerPractitionerQualificationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("communication")) {
        res.getCommunication().add(parseCodeableConcept(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Practitioner.PractitionerQualificationComponent parsePractitionerPractitionerQualificationComponent(XmlPullParser xpp, Practitioner owner) throws Exception {
    Practitioner.PractitionerQualificationComponent res = new Practitioner.PractitionerQualificationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issuer")) {
        res.setIssuer(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Procedure parseProcedure(XmlPullParser xpp) throws Exception {
    Procedure res = new Procedure();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.getBodySite().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indication")) {
        res.getIndication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.getPerformer().add(parseProcedureProcedurePerformerComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcome(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("report")) {
        res.getReport().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("complication")) {
        res.getComplication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("followUp")) {
        res.setFollowUp(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relatedItem")) {
        res.getRelatedItem().add(parseProcedureProcedureRelatedItemComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotes(parseString(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Procedure.ProcedurePerformerComponent parseProcedureProcedurePerformerComponent(XmlPullParser xpp, Procedure owner) throws Exception {
    Procedure.ProcedurePerformerComponent res = new Procedure.ProcedurePerformerComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("person")) {
        res.setPerson(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Procedure.ProcedureRelatedItemComponent parseProcedureProcedureRelatedItemComponent(XmlPullParser xpp, Procedure owner) throws Exception {
    Procedure.ProcedureRelatedItemComponent res = new Procedure.ProcedureRelatedItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Procedure.ProcedureRelationshipType.Null, new Procedure.ProcedureRelationshipTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile parseProfile(XmlPullParser xpp) throws Exception {
    Profile res = new Profile();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Profile.ResourceProfileStatus.Null, new Profile.ResourceProfileStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimental(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirements(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fhirVersion")) {
        res.setFhirVersion(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mapping")) {
        res.getMapping().add(parseProfileProfileMappingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("structure")) {
        res.getStructure().add(parseProfileProfileStructureComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extensionDefn")) {
        res.getExtensionDefn().add(parseProfileProfileExtensionDefnComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ProfileMappingComponent parseProfileProfileMappingComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ProfileMappingComponent res = new Profile.ProfileMappingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identity")) {
        res.setIdentity(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri")) {
        res.setUri(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setComments(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ProfileStructureComponent parseProfileProfileStructureComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ProfileStructureComponent res = new Profile.ProfileStructureComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBase(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publish")) {
        res.setPublish(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("purpose")) {
        res.setPurpose(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("snapshot")) {
        res.setSnapshot(parseProfileConstraintComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("differential")) {
        res.setDifferential(parseProfileConstraintComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("searchParam")) {
        res.getSearchParam().add(parseProfileProfileStructureSearchParamComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ConstraintComponent parseProfileConstraintComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ConstraintComponent res = new Profile.ConstraintComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.getElement().add(parseProfileElementComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ElementComponent parseProfileElementComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ElementComponent res = new Profile.ElementComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("path")) {
        res.setPath(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("representation")) {
        res.getRepresentation().add(parseEnumeration(xpp, Profile.PropertyRepresentation.Null, new Profile.PropertyRepresentationEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("slicing")) {
        res.setSlicing(parseProfileElementSlicingComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinition(parseProfileElementDefinitionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ElementSlicingComponent parseProfileElementSlicingComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ElementSlicingComponent res = new Profile.ElementSlicingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("discriminator")) {
        res.setDiscriminator(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ordered")) {
        res.setOrdered(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rules")) {
        res.setRules(parseEnumeration(xpp, Profile.ResourceSlicingRules.Null, new Profile.ResourceSlicingRulesEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ElementDefinitionComponent parseProfileElementDefinitionComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ElementDefinitionComponent res = new Profile.ElementDefinitionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("short")) {
        res.setShort(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("formal")) {
        res.setFormal(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setComments(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirements(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("synonym")) {
        res.getSynonym().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("min")) {
        res.setMin(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("max")) {
        res.setMax(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseProfileTypeRefComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("nameReference")) {
        res.setNameReference(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "example")) {
        res.setExample(parseType("example", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxLength")) {
        res.setMaxLength(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.getCondition().add(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("constraint")) {
        res.getConstraint().add(parseProfileElementDefinitionConstraintComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mustSupport")) {
        res.setMustSupport(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("isModifier")) {
        res.setIsModifier(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("binding")) {
        res.setBinding(parseProfileElementDefinitionBindingComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mapping")) {
        res.getMapping().add(parseProfileElementDefinitionMappingComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.TypeRefComponent parseProfileTypeRefComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.TypeRefComponent res = new Profile.TypeRefComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("aggregation")) {
        res.getAggregation().add(parseEnumeration(xpp, Profile.ResourceAggregationMode.Null, new Profile.ResourceAggregationModeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ElementDefinitionConstraintComponent parseProfileElementDefinitionConstraintComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ElementDefinitionConstraintComponent res = new Profile.ElementDefinitionConstraintComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("key")) {
        res.setKey(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverity(parseEnumeration(xpp, Profile.ConstraintSeverity.Null, new Profile.ConstraintSeverityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("human")) {
        res.setHuman(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("xpath")) {
        res.setXpath(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ElementDefinitionBindingComponent parseProfileElementDefinitionBindingComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ElementDefinitionBindingComponent res = new Profile.ElementDefinitionBindingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("isExtensible")) {
        res.setIsExtensible(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("conformance")) {
        res.setConformance(parseEnumeration(xpp, Profile.BindingConformance.Null, new Profile.BindingConformanceEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reference")) {
        res.setReference(parseType("reference", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ElementDefinitionMappingComponent parseProfileElementDefinitionMappingComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ElementDefinitionMappingComponent res = new Profile.ElementDefinitionMappingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identity")) {
        res.setIdentity(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("map")) {
        res.setMap(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ProfileStructureSearchParamComponent parseProfileProfileStructureSearchParamComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ProfileStructureSearchParamComponent res = new Profile.ProfileStructureSearchParamComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Profile.SearchParamType.Null, new Profile.SearchParamTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("xpath")) {
        res.setXpath(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.getTarget().add(parseCode(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Profile.ProfileExtensionDefnComponent parseProfileProfileExtensionDefnComponent(XmlPullParser xpp, Profile owner) throws Exception {
    Profile.ProfileExtensionDefnComponent res = new Profile.ProfileExtensionDefnComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contextType")) {
        res.setContextType(parseEnumeration(xpp, Profile.ExtensionContext.Null, new Profile.ExtensionContextEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("context")) {
        res.getContext().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.getElement().add(parseProfileElementComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Provenance parseProvenance(XmlPullParser xpp) throws Exception {
    Provenance res = new Provenance();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.getTarget().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recorded")) {
        res.setRecorded(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("policy")) {
        res.getPolicy().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("agent")) {
        res.getAgent().add(parseProvenanceProvenanceAgentComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entity")) {
        res.getEntity().add(parseProvenanceProvenanceEntityComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("integritySignature")) {
        res.setIntegritySignature(parseString(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Provenance.ProvenanceAgentComponent parseProvenanceProvenanceAgentComponent(XmlPullParser xpp, Provenance owner) throws Exception {
    Provenance.ProvenanceAgentComponent res = new Provenance.ProvenanceAgentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Provenance.ProvenanceEntityComponent parseProvenanceProvenanceEntityComponent(XmlPullParser xpp, Provenance owner) throws Exception {
    Provenance.ProvenanceEntityComponent res = new Provenance.ProvenanceEntityComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseEnumeration(xpp, Provenance.ProvenanceEntityRole.Null, new Provenance.ProvenanceEntityRoleEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("agent")) {
        res.setAgent(parseProvenanceProvenanceAgentComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Query parseQuery(XmlPullParser xpp) throws Exception {
    Query res = new Query();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseExtension(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseQueryQueryResponseComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Query.QueryResponseComponent parseQueryQueryResponseComponent(XmlPullParser xpp, Query owner) throws Exception {
    Query.QueryResponseComponent res = new Query.QueryResponseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcome(parseEnumeration(xpp, Query.QueryOutcome.Null, new Query.QueryOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("total")) {
        res.setTotal(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseExtension(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("first")) {
        res.getFirst().add(parseExtension(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("previous")) {
        res.getPrevious().add(parseExtension(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("next")) {
        res.getNext().add(parseExtension(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("last")) {
        res.getLast().add(parseExtension(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.getReference().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Questionnaire parseQuestionnaire(XmlPullParser xpp) throws Exception {
    Questionnaire res = new Questionnaire();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Questionnaire.QuestionnaireStatus.Null, new Questionnaire.QuestionnaireStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.setGroup(parseQuestionnaireGroupComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Questionnaire.GroupComponent parseQuestionnaireGroupComponent(XmlPullParser xpp, Questionnaire owner) throws Exception {
    Questionnaire.GroupComponent res = new Questionnaire.GroupComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkId(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("required")) {
        res.setRequired(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeats")) {
        res.setRepeats(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.getGroup().add(parseQuestionnaireGroupComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("question")) {
        res.getQuestion().add(parseQuestionnaireQuestionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Questionnaire.QuestionComponent parseQuestionnaireQuestionComponent(XmlPullParser xpp, Questionnaire owner) throws Exception {
    Questionnaire.QuestionComponent res = new Questionnaire.QuestionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkId(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Questionnaire.AnswerFormat.Null, new Questionnaire.AnswerFormatEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("required")) {
        res.setRequired(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeats")) {
        res.setRepeats(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("options")) {
        res.setOptions(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.getGroup().add(parseQuestionnaireGroupComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private QuestionnaireAnswers parseQuestionnaireAnswers(XmlPullParser xpp) throws Exception {
    QuestionnaireAnswers res = new QuestionnaireAnswers();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("questionnaire")) {
        res.setQuestionnaire(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, QuestionnaireAnswers.QuestionnaireAnswersStatus.Null, new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authored")) {
        res.setAuthored(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.setGroup(parseQuestionnaireAnswersGroupComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private QuestionnaireAnswers.GroupComponent parseQuestionnaireAnswersGroupComponent(XmlPullParser xpp, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.GroupComponent res = new QuestionnaireAnswers.GroupComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkId(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.getGroup().add(parseQuestionnaireAnswersGroupComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("question")) {
        res.getQuestion().add(parseQuestionnaireAnswersQuestionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private QuestionnaireAnswers.QuestionComponent parseQuestionnaireAnswersQuestionComponent(XmlPullParser xpp, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.QuestionComponent res = new QuestionnaireAnswers.QuestionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkId(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("answer")) {
        res.getAnswer().add(parseQuestionnaireAnswersQuestionAnswerComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.getGroup().add(parseQuestionnaireAnswersGroupComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private QuestionnaireAnswers.QuestionAnswerComponent parseQuestionnaireAnswersQuestionAnswerComponent(XmlPullParser xpp, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.QuestionAnswerComponent res = new QuestionnaireAnswers.QuestionAnswerComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ReferralRequest parseReferralRequest(XmlPullParser xpp) throws Exception {
    ReferralRequest res = new ReferralRequest();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, ReferralRequest.Referralstatus.Null, new ReferralRequest.ReferralstatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialty")) {
        res.setSpecialty(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requester")) {
        res.setRequester(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recipient")) {
        res.getRecipient().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateSent")) {
        res.setDateSent(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceRequested")) {
        res.getServiceRequested().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingInformation")) {
        res.getSupportingInformation().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fulfillmentTime")) {
        res.setFulfillmentTime(parsePeriod(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private RelatedPerson parseRelatedPerson(XmlPullParser xpp) throws Exception {
    RelatedPerson res = new RelatedPerson();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGender(parseEnumeration(xpp, RelatedPerson.AdministrativeGender.Null, new RelatedPerson.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("photo")) {
        res.getPhoto().add(parseAttachment(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private RiskAssessment parseRiskAssessment(XmlPullParser xpp) throws Exception {
    RiskAssessment res = new RiskAssessment();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.setCondition(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.setPerformer(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("basis")) {
        res.getBasis().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prediction")) {
        res.getPrediction().add(parseRiskAssessmentRiskAssessmentPredictionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mitigation")) {
        res.setMitigation(parseString(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private RiskAssessment.RiskAssessmentPredictionComponent parseRiskAssessmentRiskAssessmentPredictionComponent(XmlPullParser xpp, RiskAssessment owner) throws Exception {
    RiskAssessment.RiskAssessmentPredictionComponent res = new RiskAssessment.RiskAssessmentPredictionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcome(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "probability")) {
        res.setProbability(parseType("probability", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relativeRisk")) {
        res.setRelativeRisk(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "when")) {
        res.setWhen(parseType("when", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rationale")) {
        res.setRationale(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SecurityEvent parseSecurityEvent(XmlPullParser xpp) throws Exception {
    SecurityEvent res = new SecurityEvent();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.setEvent(parseSecurityEventSecurityEventEventComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseSecurityEventSecurityEventParticipantComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseSecurityEventSecurityEventSourceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("object")) {
        res.getObject().add(parseSecurityEventSecurityEventObjectComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SecurityEvent.SecurityEventEventComponent parseSecurityEventSecurityEventEventComponent(XmlPullParser xpp, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventEventComponent res = new SecurityEvent.SecurityEventEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subtype")) {
        res.getSubtype().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.setAction(parseEnumeration(xpp, SecurityEvent.SecurityEventAction.Null, new SecurityEvent.SecurityEventActionEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTime(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcome(parseEnumeration(xpp, SecurityEvent.SecurityEventOutcome.Null, new SecurityEvent.SecurityEventOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcomeDesc")) {
        res.setOutcomeDesc(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SecurityEvent.SecurityEventParticipantComponent parseSecurityEventSecurityEventParticipantComponent(XmlPullParser xpp, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventParticipantComponent res = new SecurityEvent.SecurityEventParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.getRole().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("userId")) {
        res.setUserId(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("altId")) {
        res.setAltId(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestor")) {
        res.setRequestor(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("media")) {
        res.setMedia(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("network")) {
        res.setNetwork(parseSecurityEventSecurityEventParticipantNetworkComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SecurityEvent.SecurityEventParticipantNetworkComponent parseSecurityEventSecurityEventParticipantNetworkComponent(XmlPullParser xpp, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventParticipantNetworkComponent res = new SecurityEvent.SecurityEventParticipantNetworkComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, SecurityEvent.NetworkType.Null, new SecurityEvent.NetworkTypeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SecurityEvent.SecurityEventSourceComponent parseSecurityEventSecurityEventSourceComponent(XmlPullParser xpp, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventSourceComponent res = new SecurityEvent.SecurityEventSourceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SecurityEvent.SecurityEventObjectComponent parseSecurityEventSecurityEventObjectComponent(XmlPullParser xpp, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventObjectComponent res = new SecurityEvent.SecurityEventObjectComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, SecurityEvent.ObjectType.Null, new SecurityEvent.ObjectTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseEnumeration(xpp, SecurityEvent.ObjectRole.Null, new SecurityEvent.ObjectRoleEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lifecycle")) {
        res.setLifecycle(parseEnumeration(xpp, SecurityEvent.ObjectLifecycle.Null, new SecurityEvent.ObjectLifecycleEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sensitivity")) {
        res.setSensitivity(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("query")) {
        res.setQuery(parseBase64Binary(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseSecurityEventSecurityEventObjectDetailComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private SecurityEvent.SecurityEventObjectDetailComponent parseSecurityEventSecurityEventObjectDetailComponent(XmlPullParser xpp, SecurityEvent owner) throws Exception {
    SecurityEvent.SecurityEventObjectDetailComponent res = new SecurityEvent.SecurityEventObjectDetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseBase64Binary(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Slot parseSlot(XmlPullParser xpp) throws Exception {
    Slot res = new Slot();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availability")) {
        res.setAvailability(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("freeBusyType")) {
        res.setFreeBusyType(parseEnumeration(xpp, Slot.Slotstatus.Null, new Slot.SlotstatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStart(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("overbooked")) {
        res.setOverbooked(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setComment(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastModified")) {
        res.setLastModified(parseDateTime(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Specimen parseSpecimen(XmlPullParser xpp) throws Exception {
    Specimen res = new Specimen();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.getSource().add(parseSpecimenSpecimenSourceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("accessionIdentifier")) {
        res.setAccessionIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receivedTime")) {
        res.setReceivedTime(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("collection")) {
        res.setCollection(parseSpecimenSpecimenCollectionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("treatment")) {
        res.getTreatment().add(parseSpecimenSpecimenTreatmentComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("container")) {
        res.getContainer().add(parseSpecimenSpecimenContainerComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Specimen.SpecimenSourceComponent parseSpecimenSpecimenSourceComponent(XmlPullParser xpp, Specimen owner) throws Exception {
    Specimen.SpecimenSourceComponent res = new Specimen.SpecimenSourceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseEnumeration(xpp, Specimen.HierarchicalRelationshipType.Null, new Specimen.HierarchicalRelationshipTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.getTarget().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Specimen.SpecimenCollectionComponent parseSpecimenSpecimenCollectionComponent(XmlPullParser xpp, Specimen owner) throws Exception {
    Specimen.SpecimenCollectionComponent res = new Specimen.SpecimenCollectionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("collector")) {
        res.setCollector(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.getComment().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "collected")) {
        res.setCollected(parseType("collected", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sourceSite")) {
        res.setSourceSite(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Specimen.SpecimenTreatmentComponent parseSpecimenSpecimenTreatmentComponent(XmlPullParser xpp, Specimen owner) throws Exception {
    Specimen.SpecimenTreatmentComponent res = new Specimen.SpecimenTreatmentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("procedure")) {
        res.setProcedure(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additive")) {
        res.getAdditive().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Specimen.SpecimenContainerComponent parseSpecimenSpecimenContainerComponent(XmlPullParser xpp, Specimen owner) throws Exception {
    Specimen.SpecimenContainerComponent res = new Specimen.SpecimenContainerComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("capacity")) {
        res.setCapacity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimenQuantity")) {
        res.setSpecimenQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additive")) {
        res.setAdditive(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Subscription parseSubscription(XmlPullParser xpp) throws Exception {
    Subscription res = new Subscription();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("criteria")) {
        res.setCriteria(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Subscription.SubscriptionStatus.Null, new Subscription.SubscriptionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("error")) {
        res.setError(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("channel")) {
        res.setChannel(parseSubscriptionSubscriptionChannelComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("tag")) {
        res.getTag().add(parseSubscriptionSubscriptionTagComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Subscription.SubscriptionChannelComponent parseSubscriptionSubscriptionChannelComponent(XmlPullParser xpp, Subscription owner) throws Exception {
    Subscription.SubscriptionChannelComponent res = new Subscription.SubscriptionChannelComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Subscription.SubscriptionChannelType.Null, new Subscription.SubscriptionChannelTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("payload")) {
        res.setPayload(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("header")) {
        res.setHeader(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Subscription.SubscriptionTagComponent parseSubscriptionSubscriptionTagComponent(XmlPullParser xpp, Subscription owner) throws Exception {
    Subscription.SubscriptionTagComponent res = new Subscription.SubscriptionTagComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("term")) {
        res.setTerm(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("scheme")) {
        res.setScheme(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Substance parseSubstance(XmlPullParser xpp) throws Exception {
    Substance res = new Substance();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instance")) {
        res.setInstance(parseSubstanceSubstanceInstanceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ingredient")) {
        res.getIngredient().add(parseSubstanceSubstanceIngredientComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Substance.SubstanceInstanceComponent parseSubstanceSubstanceInstanceComponent(XmlPullParser xpp, Substance owner) throws Exception {
    Substance.SubstanceInstanceComponent res = new Substance.SubstanceInstanceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expiry")) {
        res.setExpiry(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Substance.SubstanceIngredientComponent parseSubstanceSubstanceIngredientComponent(XmlPullParser xpp, Substance owner) throws Exception {
    Substance.SubstanceIngredientComponent res = new Substance.SubstanceIngredientComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseRatio(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substance")) {
        res.setSubstance(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Supply parseSupply(XmlPullParser xpp) throws Exception {
    Supply res = new Supply();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("kind")) {
        res.setKind(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Supply.ValuesetSupplyStatus.Null, new Supply.ValuesetSupplyStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderedItem")) {
        res.setOrderedItem(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispense")) {
        res.getDispense().add(parseSupplySupplyDispenseComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private Supply.SupplyDispenseComponent parseSupplySupplyDispenseComponent(XmlPullParser xpp, Supply owner) throws Exception {
    Supply.SupplyDispenseComponent res = new Supply.SupplyDispenseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Supply.ValuesetSupplyDispenseStatus.Null, new Supply.ValuesetSupplyDispenseStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("suppliedItem")) {
        res.setSuppliedItem(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supplier")) {
        res.setSupplier(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenPrepared")) {
        res.setWhenPrepared(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenHandedOver")) {
        res.setWhenHandedOver(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestination(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receiver")) {
        res.getReceiver().add(parseResourceReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet parseValueSet(XmlPullParser xpp) throws Exception {
    ValueSet res = new ValueSet();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("purpose")) {
        res.setPurpose(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("immutable")) {
        res.setImmutable(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyright(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, ValueSet.ValuesetStatus.Null, new ValueSet.ValuesetStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimental(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extensible")) {
        res.setExtensible(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("stableDate")) {
        res.setStableDate(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("define")) {
        res.setDefine(parseValueSetValueSetDefineComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("compose")) {
        res.setCompose(parseValueSetValueSetComposeComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expansion")) {
        res.setExpansion(parseValueSetValueSetExpansionComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet.ValueSetDefineComponent parseValueSetValueSetDefineComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetDefineComponent res = new ValueSet.ValueSetDefineComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("caseSensitive")) {
        res.setCaseSensitive(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseValueSetValueSetDefineConceptComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet.ValueSetDefineConceptComponent parseValueSetValueSetDefineConceptComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetDefineConceptComponent res = new ValueSet.ValueSetDefineConceptComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("abstract")) {
        res.setAbstract(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinition(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseValueSetValueSetDefineConceptComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet.ValueSetComposeComponent parseValueSetValueSetComposeComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetComposeComponent res = new ValueSet.ValueSetComposeComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("import")) {
        res.getImport().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("include")) {
        res.getInclude().add(parseValueSetConceptSetComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("exclude")) {
        res.getExclude().add(parseValueSetConceptSetComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet.ConceptSetComponent parseValueSetConceptSetComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ConceptSetComponent res = new ValueSet.ConceptSetComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("filter")) {
        res.getFilter().add(parseValueSetConceptSetFilterComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet.ConceptSetFilterComponent parseValueSetConceptSetFilterComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ConceptSetFilterComponent res = new ValueSet.ConceptSetFilterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("property")) {
        res.setProperty(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("op")) {
        res.setOp(parseEnumeration(xpp, ValueSet.FilterOperator.Null, new ValueSet.FilterOperatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseCode(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet.ValueSetExpansionComponent parseValueSetValueSetExpansionComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionComponent res = new ValueSet.ValueSetExpansionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("timestamp")) {
        res.setTimestamp(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contains")) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  private ValueSet.ValueSetExpansionContainsComponent parseValueSetValueSetExpansionContainsComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionContainsComponent res = new ValueSet.ValueSetExpansionContainsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contains")) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  @Override
  protected Resource parseResource(XmlPullParser xpp) throws Exception {
    if (xpp.getName().equals("AdverseReaction"))
      return parseAdverseReaction(xpp);
    else if (xpp.getName().equals("Alert"))
      return parseAlert(xpp);
    else if (xpp.getName().equals("AllergyIntolerance"))
      return parseAllergyIntolerance(xpp);
    else if (xpp.getName().equals("Appointment"))
      return parseAppointment(xpp);
    else if (xpp.getName().equals("AppointmentResponse"))
      return parseAppointmentResponse(xpp);
    else if (xpp.getName().equals("Availability"))
      return parseAvailability(xpp);
    else if (xpp.getName().equals("CarePlan"))
      return parseCarePlan(xpp);
    else if (xpp.getName().equals("Composition"))
      return parseComposition(xpp);
    else if (xpp.getName().equals("ConceptMap"))
      return parseConceptMap(xpp);
    else if (xpp.getName().equals("Condition"))
      return parseCondition(xpp);
    else if (xpp.getName().equals("Conformance"))
      return parseConformance(xpp);
    else if (xpp.getName().equals("Contraindication"))
      return parseContraindication(xpp);
    else if (xpp.getName().equals("DataElement"))
      return parseDataElement(xpp);
    else if (xpp.getName().equals("Device"))
      return parseDevice(xpp);
    else if (xpp.getName().equals("DeviceObservationReport"))
      return parseDeviceObservationReport(xpp);
    else if (xpp.getName().equals("DiagnosticOrder"))
      return parseDiagnosticOrder(xpp);
    else if (xpp.getName().equals("DiagnosticReport"))
      return parseDiagnosticReport(xpp);
    else if (xpp.getName().equals("DocumentManifest"))
      return parseDocumentManifest(xpp);
    else if (xpp.getName().equals("DocumentReference"))
      return parseDocumentReference(xpp);
    else if (xpp.getName().equals("Encounter"))
      return parseEncounter(xpp);
    else if (xpp.getName().equals("FamilyHistory"))
      return parseFamilyHistory(xpp);
    else if (xpp.getName().equals("Group"))
      return parseGroup(xpp);
    else if (xpp.getName().equals("ImagingStudy"))
      return parseImagingStudy(xpp);
    else if (xpp.getName().equals("Immunization"))
      return parseImmunization(xpp);
    else if (xpp.getName().equals("ImmunizationRecommendation"))
      return parseImmunizationRecommendation(xpp);
    else if (xpp.getName().equals("List"))
      return parseList_(xpp);
    else if (xpp.getName().equals("Location"))
      return parseLocation(xpp);
    else if (xpp.getName().equals("Media"))
      return parseMedia(xpp);
    else if (xpp.getName().equals("Medication"))
      return parseMedication(xpp);
    else if (xpp.getName().equals("MedicationAdministration"))
      return parseMedicationAdministration(xpp);
    else if (xpp.getName().equals("MedicationDispense"))
      return parseMedicationDispense(xpp);
    else if (xpp.getName().equals("MedicationPrescription"))
      return parseMedicationPrescription(xpp);
    else if (xpp.getName().equals("MedicationStatement"))
      return parseMedicationStatement(xpp);
    else if (xpp.getName().equals("MessageHeader"))
      return parseMessageHeader(xpp);
    else if (xpp.getName().equals("Namespace"))
      return parseNamespace(xpp);
    else if (xpp.getName().equals("Observation"))
      return parseObservation(xpp);
    else if (xpp.getName().equals("OperationDefinition"))
      return parseOperationDefinition(xpp);
    else if (xpp.getName().equals("OperationOutcome"))
      return parseOperationOutcome(xpp);
    else if (xpp.getName().equals("Order"))
      return parseOrder(xpp);
    else if (xpp.getName().equals("OrderResponse"))
      return parseOrderResponse(xpp);
    else if (xpp.getName().equals("Organization"))
      return parseOrganization(xpp);
    else if (xpp.getName().equals("Other"))
      return parseOther(xpp);
    else if (xpp.getName().equals("Patient"))
      return parsePatient(xpp);
    else if (xpp.getName().equals("Practitioner"))
      return parsePractitioner(xpp);
    else if (xpp.getName().equals("Procedure"))
      return parseProcedure(xpp);
    else if (xpp.getName().equals("Profile"))
      return parseProfile(xpp);
    else if (xpp.getName().equals("Provenance"))
      return parseProvenance(xpp);
    else if (xpp.getName().equals("Query"))
      return parseQuery(xpp);
    else if (xpp.getName().equals("Questionnaire"))
      return parseQuestionnaire(xpp);
    else if (xpp.getName().equals("QuestionnaireAnswers"))
      return parseQuestionnaireAnswers(xpp);
    else if (xpp.getName().equals("ReferralRequest"))
      return parseReferralRequest(xpp);
    else if (xpp.getName().equals("RelatedPerson"))
      return parseRelatedPerson(xpp);
    else if (xpp.getName().equals("RiskAssessment"))
      return parseRiskAssessment(xpp);
    else if (xpp.getName().equals("SecurityEvent"))
      return parseSecurityEvent(xpp);
    else if (xpp.getName().equals("Slot"))
      return parseSlot(xpp);
    else if (xpp.getName().equals("Specimen"))
      return parseSpecimen(xpp);
    else if (xpp.getName().equals("Subscription"))
      return parseSubscription(xpp);
    else if (xpp.getName().equals("Substance"))
      return parseSubstance(xpp);
    else if (xpp.getName().equals("Supply"))
      return parseSupply(xpp);
    else if (xpp.getName().equals("ValueSet"))
      return parseValueSet(xpp);
    else if (xpp.getName().equals("Binary"))
      return parseBinary(xpp);
    throw new Exception("Unknown resource type "+xpp.getName()+"");
  }

  protected Type parseType(String prefix, XmlPullParser xpp) throws Exception {
    if (xpp.getName().equals(prefix+"Period"))
      return parsePeriod(xpp);
    else if (xpp.getName().equals(prefix+"Coding"))
      return parseCoding(xpp);
    else if (xpp.getName().equals(prefix+"Range"))
      return parseRange(xpp);
    else if (xpp.getName().equals(prefix+"Quantity"))
      return parseQuantity(xpp);
    else if (xpp.getName().equals(prefix+"Attachment"))
      return parseAttachment(xpp);
    else if (xpp.getName().equals(prefix+"Ratio"))
      return parseRatio(xpp);
    else if (xpp.getName().equals(prefix+"SampledData"))
      return parseSampledData(xpp);
    else if (xpp.getName().equals(prefix+"Resource"))
      return parseResourceReference(xpp);
    else if (xpp.getName().equals(prefix+"CodeableConcept"))
      return parseCodeableConcept(xpp);
    else if (xpp.getName().equals(prefix+"Identifier"))
      return parseIdentifier(xpp);
    else if (xpp.getName().equals(prefix+"Age"))
      return parseAge(xpp);
    else if (xpp.getName().equals(prefix+"Count"))
      return parseCount(xpp);
    else if (xpp.getName().equals(prefix+"Money"))
      return parseMoney(xpp);
    else if (xpp.getName().equals(prefix+"Distance"))
      return parseDistance(xpp);
    else if (xpp.getName().equals(prefix+"Duration"))
      return parseDuration(xpp);
    else if (xpp.getName().equals(prefix+"Schedule"))
      return parseSchedule(xpp);
    else if (xpp.getName().equals(prefix+"Contact"))
      return parseContact(xpp);
    else if (xpp.getName().equals(prefix+"Address"))
      return parseAddress(xpp);
    else if (xpp.getName().equals(prefix+"HumanName"))
      return parseHumanName(xpp);
    else if (xpp.getName().equals(prefix+"Integer"))
      return parseInteger(xpp);
    else if (xpp.getName().equals(prefix+"DateTime"))
      return parseDateTime(xpp);
    else if (xpp.getName().equals(prefix+"Code"))
      return parseCode(xpp);
    else if (xpp.getName().equals(prefix+"Date"))
      return parseDate(xpp);
    else if (xpp.getName().equals(prefix+"Decimal"))
      return parseDecimal(xpp);
    else if (xpp.getName().equals(prefix+"Uri"))
      return parseUri(xpp);
    else if (xpp.getName().equals(prefix+"Id"))
      return parseId(xpp);
    else if (xpp.getName().equals(prefix+"Base64Binary"))
      return parseBase64Binary(xpp);
    else if (xpp.getName().equals(prefix+"Time"))
      return parseTime(xpp);
    else if (xpp.getName().equals(prefix+"Oid"))
      return parseOid(xpp);
    else if (xpp.getName().equals(prefix+"String"))
      return parseString(xpp);
    else if (xpp.getName().equals(prefix+"Boolean"))
      return parseBoolean(xpp);
    else if (xpp.getName().equals(prefix+"Uuid"))
      return parseUuid(xpp);
    else if (xpp.getName().equals(prefix+"Instant"))
      return parseInstant(xpp);
    throw new Exception("Unknown type "+xpp.getName());
  }

  public Element parseFragment(XmlPullParser xpp, String type) throws Exception {
    if (type.equals("Extension"))
      return parseExtension(xpp);
    else if (type.equals("Narrative"))
      return parseNarrative(xpp);
    else if (type.equals("Period"))
      return parsePeriod(xpp);
    else if (type.equals("Coding"))
      return parseCoding(xpp);
    else if (type.equals("Range"))
      return parseRange(xpp);
    else if (type.equals("Quantity"))
      return parseQuantity(xpp);
    else if (type.equals("Attachment"))
      return parseAttachment(xpp);
    else if (type.equals("Ratio"))
      return parseRatio(xpp);
    else if (type.equals("SampledData"))
      return parseSampledData(xpp);
    else if (type.equals("ResourceReference"))
      return parseResourceReference(xpp);
    else if (type.equals("CodeableConcept"))
      return parseCodeableConcept(xpp);
    else if (type.equals("Identifier"))
      return parseIdentifier(xpp);
    else if (type.equals("Age"))
      return parseAge(xpp);
    else if (type.equals("Count"))
      return parseCount(xpp);
    else if (type.equals("Money"))
      return parseMoney(xpp);
    else if (type.equals("Distance"))
      return parseDistance(xpp);
    else if (type.equals("Duration"))
      return parseDuration(xpp);
    else if (type.equals("Schedule"))
      return parseSchedule(xpp);
    else if (type.equals("Contact"))
      return parseContact(xpp);
    else if (type.equals("Address"))
      return parseAddress(xpp);
    else if (type.equals("HumanName"))
      return parseHumanName(xpp);
    else if (type.equals("AdverseReaction"))
      return parseAdverseReaction(xpp);
    else if (type.equals("Alert"))
      return parseAlert(xpp);
    else if (type.equals("AllergyIntolerance"))
      return parseAllergyIntolerance(xpp);
    else if (type.equals("Appointment"))
      return parseAppointment(xpp);
    else if (type.equals("AppointmentResponse"))
      return parseAppointmentResponse(xpp);
    else if (type.equals("Availability"))
      return parseAvailability(xpp);
    else if (type.equals("CarePlan"))
      return parseCarePlan(xpp);
    else if (type.equals("Composition"))
      return parseComposition(xpp);
    else if (type.equals("ConceptMap"))
      return parseConceptMap(xpp);
    else if (type.equals("Condition"))
      return parseCondition(xpp);
    else if (type.equals("Conformance"))
      return parseConformance(xpp);
    else if (type.equals("Contraindication"))
      return parseContraindication(xpp);
    else if (type.equals("DataElement"))
      return parseDataElement(xpp);
    else if (type.equals("Device"))
      return parseDevice(xpp);
    else if (type.equals("DeviceObservationReport"))
      return parseDeviceObservationReport(xpp);
    else if (type.equals("DiagnosticOrder"))
      return parseDiagnosticOrder(xpp);
    else if (type.equals("DiagnosticReport"))
      return parseDiagnosticReport(xpp);
    else if (type.equals("DocumentManifest"))
      return parseDocumentManifest(xpp);
    else if (type.equals("DocumentReference"))
      return parseDocumentReference(xpp);
    else if (type.equals("Encounter"))
      return parseEncounter(xpp);
    else if (type.equals("FamilyHistory"))
      return parseFamilyHistory(xpp);
    else if (type.equals("Group"))
      return parseGroup(xpp);
    else if (type.equals("ImagingStudy"))
      return parseImagingStudy(xpp);
    else if (type.equals("Immunization"))
      return parseImmunization(xpp);
    else if (type.equals("ImmunizationRecommendation"))
      return parseImmunizationRecommendation(xpp);
    else if (type.equals("List"))
      return parseList_(xpp);
    else if (type.equals("Location"))
      return parseLocation(xpp);
    else if (type.equals("Media"))
      return parseMedia(xpp);
    else if (type.equals("Medication"))
      return parseMedication(xpp);
    else if (type.equals("MedicationAdministration"))
      return parseMedicationAdministration(xpp);
    else if (type.equals("MedicationDispense"))
      return parseMedicationDispense(xpp);
    else if (type.equals("MedicationPrescription"))
      return parseMedicationPrescription(xpp);
    else if (type.equals("MedicationStatement"))
      return parseMedicationStatement(xpp);
    else if (type.equals("MessageHeader"))
      return parseMessageHeader(xpp);
    else if (type.equals("Namespace"))
      return parseNamespace(xpp);
    else if (type.equals("Observation"))
      return parseObservation(xpp);
    else if (type.equals("OperationDefinition"))
      return parseOperationDefinition(xpp);
    else if (type.equals("OperationOutcome"))
      return parseOperationOutcome(xpp);
    else if (type.equals("Order"))
      return parseOrder(xpp);
    else if (type.equals("OrderResponse"))
      return parseOrderResponse(xpp);
    else if (type.equals("Organization"))
      return parseOrganization(xpp);
    else if (type.equals("Other"))
      return parseOther(xpp);
    else if (type.equals("Patient"))
      return parsePatient(xpp);
    else if (type.equals("Practitioner"))
      return parsePractitioner(xpp);
    else if (type.equals("Procedure"))
      return parseProcedure(xpp);
    else if (type.equals("Profile"))
      return parseProfile(xpp);
    else if (type.equals("Provenance"))
      return parseProvenance(xpp);
    else if (type.equals("Query"))
      return parseQuery(xpp);
    else if (type.equals("Questionnaire"))
      return parseQuestionnaire(xpp);
    else if (type.equals("QuestionnaireAnswers"))
      return parseQuestionnaireAnswers(xpp);
    else if (type.equals("ReferralRequest"))
      return parseReferralRequest(xpp);
    else if (type.equals("RelatedPerson"))
      return parseRelatedPerson(xpp);
    else if (type.equals("RiskAssessment"))
      return parseRiskAssessment(xpp);
    else if (type.equals("SecurityEvent"))
      return parseSecurityEvent(xpp);
    else if (type.equals("Slot"))
      return parseSlot(xpp);
    else if (type.equals("Specimen"))
      return parseSpecimen(xpp);
    else if (type.equals("Subscription"))
      return parseSubscription(xpp);
    else if (type.equals("Substance"))
      return parseSubstance(xpp);
    else if (type.equals("Supply"))
      return parseSupply(xpp);
    else if (type.equals("ValueSet"))
      return parseValueSet(xpp);
    else if (type.equals("integer"))
      return parseInteger(xpp);
    else if (type.equals("dateTime"))
      return parseDateTime(xpp);
    else if (type.equals("code"))
      return parseCode(xpp);
    else if (type.equals("date"))
      return parseDate(xpp);
    else if (type.equals("decimal"))
      return parseDecimal(xpp);
    else if (type.equals("uri"))
      return parseUri(xpp);
    else if (type.equals("id"))
      return parseId(xpp);
    else if (type.equals("base64Binary"))
      return parseBase64Binary(xpp);
    else if (type.equals("time"))
      return parseTime(xpp);
    else if (type.equals("oid"))
      return parseOid(xpp);
    else if (type.equals("string"))
      return parseString(xpp);
    else if (type.equals("boolean"))
      return parseBoolean(xpp);
    else if (type.equals("uuid"))
      return parseUuid(xpp);
    else if (type.equals("instant"))
      return parseInstant(xpp);
    throw new Exception("Unknown type "+type);
  }

  private boolean nameIsTypeName(XmlPullParser xpp, String prefix) {
        if (xpp.getName().equals(prefix+"Period"))
      return true;
    if (xpp.getName().equals(prefix+"Coding"))
      return true;
    if (xpp.getName().equals(prefix+"Range"))
      return true;
    if (xpp.getName().equals(prefix+"Quantity"))
      return true;
    if (xpp.getName().equals(prefix+"Attachment"))
      return true;
    if (xpp.getName().equals(prefix+"Ratio"))
      return true;
    if (xpp.getName().equals(prefix+"SampledData"))
      return true;
    if (xpp.getName().equals(prefix+"Resource"))
      return true;
    if (xpp.getName().equals(prefix+"CodeableConcept"))
      return true;
    if (xpp.getName().equals(prefix+"Identifier"))
      return true;
    if (xpp.getName().equals(prefix+"Age"))
      return true;
    if (xpp.getName().equals(prefix+"Count"))
      return true;
    if (xpp.getName().equals(prefix+"Money"))
      return true;
    if (xpp.getName().equals(prefix+"Distance"))
      return true;
    if (xpp.getName().equals(prefix+"Duration"))
      return true;
    if (xpp.getName().equals(prefix+"Schedule"))
      return true;
    if (xpp.getName().equals(prefix+"Contact"))
      return true;
    if (xpp.getName().equals(prefix+"Address"))
      return true;
    if (xpp.getName().equals(prefix+"HumanName"))
      return true;
    if (xpp.getName().equals(prefix+"AdverseReaction"))
      return true;
    if (xpp.getName().equals(prefix+"Alert"))
      return true;
    if (xpp.getName().equals(prefix+"AllergyIntolerance"))
      return true;
    if (xpp.getName().equals(prefix+"Appointment"))
      return true;
    if (xpp.getName().equals(prefix+"AppointmentResponse"))
      return true;
    if (xpp.getName().equals(prefix+"Availability"))
      return true;
    if (xpp.getName().equals(prefix+"CarePlan"))
      return true;
    if (xpp.getName().equals(prefix+"Composition"))
      return true;
    if (xpp.getName().equals(prefix+"ConceptMap"))
      return true;
    if (xpp.getName().equals(prefix+"Condition"))
      return true;
    if (xpp.getName().equals(prefix+"Conformance"))
      return true;
    if (xpp.getName().equals(prefix+"Contraindication"))
      return true;
    if (xpp.getName().equals(prefix+"DataElement"))
      return true;
    if (xpp.getName().equals(prefix+"Device"))
      return true;
    if (xpp.getName().equals(prefix+"DeviceObservationReport"))
      return true;
    if (xpp.getName().equals(prefix+"DiagnosticOrder"))
      return true;
    if (xpp.getName().equals(prefix+"DiagnosticReport"))
      return true;
    if (xpp.getName().equals(prefix+"DocumentManifest"))
      return true;
    if (xpp.getName().equals(prefix+"DocumentReference"))
      return true;
    if (xpp.getName().equals(prefix+"Encounter"))
      return true;
    if (xpp.getName().equals(prefix+"FamilyHistory"))
      return true;
    if (xpp.getName().equals(prefix+"Group"))
      return true;
    if (xpp.getName().equals(prefix+"ImagingStudy"))
      return true;
    if (xpp.getName().equals(prefix+"Immunization"))
      return true;
    if (xpp.getName().equals(prefix+"ImmunizationRecommendation"))
      return true;
    if (xpp.getName().equals(prefix+"List"))
      return true;
    if (xpp.getName().equals(prefix+"Location"))
      return true;
    if (xpp.getName().equals(prefix+"Media"))
      return true;
    if (xpp.getName().equals(prefix+"Medication"))
      return true;
    if (xpp.getName().equals(prefix+"MedicationAdministration"))
      return true;
    if (xpp.getName().equals(prefix+"MedicationDispense"))
      return true;
    if (xpp.getName().equals(prefix+"MedicationPrescription"))
      return true;
    if (xpp.getName().equals(prefix+"MedicationStatement"))
      return true;
    if (xpp.getName().equals(prefix+"MessageHeader"))
      return true;
    if (xpp.getName().equals(prefix+"Namespace"))
      return true;
    if (xpp.getName().equals(prefix+"Observation"))
      return true;
    if (xpp.getName().equals(prefix+"OperationDefinition"))
      return true;
    if (xpp.getName().equals(prefix+"OperationOutcome"))
      return true;
    if (xpp.getName().equals(prefix+"Order"))
      return true;
    if (xpp.getName().equals(prefix+"OrderResponse"))
      return true;
    if (xpp.getName().equals(prefix+"Organization"))
      return true;
    if (xpp.getName().equals(prefix+"Other"))
      return true;
    if (xpp.getName().equals(prefix+"Patient"))
      return true;
    if (xpp.getName().equals(prefix+"Practitioner"))
      return true;
    if (xpp.getName().equals(prefix+"Procedure"))
      return true;
    if (xpp.getName().equals(prefix+"Profile"))
      return true;
    if (xpp.getName().equals(prefix+"Provenance"))
      return true;
    if (xpp.getName().equals(prefix+"Query"))
      return true;
    if (xpp.getName().equals(prefix+"Questionnaire"))
      return true;
    if (xpp.getName().equals(prefix+"QuestionnaireAnswers"))
      return true;
    if (xpp.getName().equals(prefix+"ReferralRequest"))
      return true;
    if (xpp.getName().equals(prefix+"RelatedPerson"))
      return true;
    if (xpp.getName().equals(prefix+"RiskAssessment"))
      return true;
    if (xpp.getName().equals(prefix+"SecurityEvent"))
      return true;
    if (xpp.getName().equals(prefix+"Slot"))
      return true;
    if (xpp.getName().equals(prefix+"Specimen"))
      return true;
    if (xpp.getName().equals(prefix+"Subscription"))
      return true;
    if (xpp.getName().equals(prefix+"Substance"))
      return true;
    if (xpp.getName().equals(prefix+"Supply"))
      return true;
    if (xpp.getName().equals(prefix+"ValueSet"))
      return true;
    if (xpp.getName().equals(prefix+"Integer"))
      return true;
    if (xpp.getName().equals(prefix+"DateTime"))
      return true;
    if (xpp.getName().equals(prefix+"Code"))
      return true;
    if (xpp.getName().equals(prefix+"Date"))
      return true;
    if (xpp.getName().equals(prefix+"Decimal"))
      return true;
    if (xpp.getName().equals(prefix+"Uri"))
      return true;
    if (xpp.getName().equals(prefix+"Id"))
      return true;
    if (xpp.getName().equals(prefix+"Base64Binary"))
      return true;
    if (xpp.getName().equals(prefix+"Time"))
      return true;
    if (xpp.getName().equals(prefix+"Oid"))
      return true;
    if (xpp.getName().equals(prefix+"String"))
      return true;
    if (xpp.getName().equals(prefix+"Boolean"))
      return true;
    if (xpp.getName().equals(prefix+"Uuid"))
      return true;
    if (xpp.getName().equals(prefix+"Instant"))
      return true;
    return false;
  }
}

